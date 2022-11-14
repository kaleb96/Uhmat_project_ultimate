package action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import mail.GenerateUserAuthenticationCode;
import svc.member.MemberGoogleJoinProService;
import svc.member.MemberKakaoJoinProService;
import util.SHA256;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberKakaoJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		 String email= request.getParameter("email");
		 String nickname=request.getParameter("nickname");
		 String api_id=request.getParameter("api_id");
		 
		 GenerateUserAuthenticationCode genAuthCode = new GenerateUserAuthenticationCode(10);
		 String rawPasswd = genAuthCode.getAuthCode();
		 String passwd= SHA256.encodeSha256(rawPasswd);
		 MemberDTO member = new MemberDTO();
		 member.setEmail(email);
		 member.setName(null);
		 member.setPasswd(passwd);
		 member.setNickname("카카오"+nickname);
		 member.setAuth_status("Y");
		 member.setApi_id(api_id);
		 System.out.println(email);
		  
		 
		 MemberKakaoJoinProService service = new MemberKakaoJoinProService();
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
