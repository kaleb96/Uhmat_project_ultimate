package action.member;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import action.Action;
import mail.GenerateUserAuthenticationCode;
import svc.member.MemberKakaoJoinProService;
import svc.member.MemberNaverLoginService;
import util.SHA256;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberNaverLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		MemberDTO member = new MemberDTO();
		System.out.println("MemberNaverLoginAction");
		String clientId = "LQgI_KqqDNAMZNve6EbO";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "QVWfBxiqNK";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI = URLEncoder.encode("http://localhost:8080/uhmat_project/MemberNaverLogin", "UTF-8");
	    String apiURL;
	    String api_id = "";
		String email = "";
		String name = "";
		String nickName = "";
		String gender = "";
		String age = "";
		String birthday = "";
		String birthyear = "";
	
		String mobile = "";
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";

	    System.out.println("apiURL="+apiURL+"\n");
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.print("responseCode="+responseCode+"\n");
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	    		System.out.println(res.toString()+"\n");
	    	  JSONParser parsing = new JSONParser();
	    		Object obj = parsing.parse(res.toString());
	    		JSONObject jsonObj = (JSONObject)obj;
	    		access_token = (String)jsonObj.get("access_token");
	    		refresh_token = (String)jsonObj.get("refresh_token");
	    		String header = "Bearer " + access_token;
	    	
	    		if(access_token != null) { // access_token을 잘 받아왔다면
	    			try {
	    				String apiurl = "https://openapi.naver.com/v1/nid/me";
	    				URL url2 = new URL(apiurl);
	    				HttpURLConnection con2 = (HttpURLConnection)url2.openConnection();
	    				con2.setRequestMethod("GET");
	    				con2.setRequestProperty("Authorization", header);
	    				int responseCode2 = con.getResponseCode();
	    				BufferedReader br2;
	    				if(responseCode2==200) { // 정상 호출
	    				 br2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
	    				} else {  // 에러 발생
	    				br2 = new BufferedReader(new InputStreamReader(con2.getErrorStream()));
	    				}
	    				String inputLine2;
	    				StringBuffer res2 = new StringBuffer();
	    				 while ((inputLine2 = br2.readLine()) != null) {
	    				res2.append(inputLine2);
	    				}
	    				br2.close();
	    				if(responseCode2==200) { // 정상 호출
	    				
	    					System.out.println(res2.toString()+"\n");
	    					JSONParser parsing2 = new JSONParser();
	    			    	Object obj2 = parsing2.parse(res2.toString());
	    			    	JSONObject jsonObj2 = (JSONObject)obj2;
	    					JSONObject resObj2 = (JSONObject)jsonObj2.get("response");	        
	    		    		 api_id = (String)resObj2.get("id");
	    		    		 email = (String)resObj2.get("email");
	    		    		 name = (String)resObj2.get("name");
	    		    		 nickName = (String)resObj2.get("nickname");
	    		    		 gender = (String)resObj2.get("gender");
	    		    		 age = (String)resObj2.get("age");
	    		    		 birthday = (String)resObj2.get("birthday");
	    		    		 birthyear = (String)resObj2.get("birthyear");
	    		    		 
	    		    		 mobile = (String)resObj2.get("mobile");
	    		    		
	    		    		System.out.println("네이버코드 = "+api_id+ " 이메일 :"+ email 
	    		    				+" 이름 : "+ name+" 닉네임 : "+nickName+" 성별 : "+ gender+" 나이 : "+age
	    		    				+" 생일 : "+ birthyear+" 생일년도 : "+birthday+" 핸드폰 : "+ mobile);
		    				}
	    			
	    		    } catch (Exception e) {
	    		    	e.printStackTrace();
	    		    }
	    		}
	    		
	    	
	    	
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	    GenerateUserAuthenticationCode genAuthCode = new GenerateUserAuthenticationCode(10);
		 String rawPasswd = genAuthCode.getAuthCode();
		 String passwd= SHA256.encodeSha256(rawPasswd);
	    Date birthdate= Date.valueOf(birthyear+"-"+birthday);
		 member.setEmail(email);
		 member.setName(name);
		 member.setPasswd(passwd);
		 member.setNickname("네이버로그인"+nickName);
		 member.setBirthdate(birthdate);
		 member.setAuth_status("Y");
		 member.setApi_id(api_id);
		 System.out.println(email);
	   
		 MemberNaverLoginService service = new MemberNaverLoginService();
		 boolean isApiSuccess =service.checkApiId(member);
		
		 if(!isApiSuccess) {
			 boolean isJoinSuccess = service.joinMember(member);
			 if(!isJoinSuccess) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('이메일 존재 하거나 탈퇴 된 이메일입니다..')");
					out.println("history.back()");
					out.println("</script>");
				} else {
					 boolean isLoginSuccess = service.loginMember(member);
					if(!isLoginSuccess) {
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.println("<script>");
						out.println("alert('로그인실패')");
						out.println("history.back()");
						out.println("</script>");
					} else {
						HttpSession session = request.getSession();
						session.setAttribute("sNickName", member.getNickname());
						
						forward = new ActionForward();
						forward.setPath("main.jsp");
						forward.setRedirect(true);
					}
				}
		}
		else {
			 boolean isLoginSuccess = service.loginMember(member);
				if(!isLoginSuccess) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('로그인실패')");
					out.println("history.back()");
					out.println("</script>");
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("sNickName", member.getNickname());
					
					forward = new ActionForward();
					forward.setPath("main.jsp");
					forward.setRedirect(true);
				}
		}	
		return forward;
	}

}
