package action.member;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import action.Action;
import svc.member.MemberJoinProService;
import util.SHA256;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
	
		 String email= request.getParameter("email");
		 String name=request.getParameter("name");
	
		 String rawPasswd = request.getParameter("passwd");
		 String passwd= SHA256.encodeSha256(rawPasswd);
		 String nickName= request.getParameter("nickName");
		 Date birthdate= Date.valueOf(request.getParameter("birth"));
		 String postCode= request.getParameter("postCode");
		 String address1=request.getParameter("address1");
		 String address2=request.getParameter("address2");
		 MemberDTO member = new MemberDTO();
		 member.setEmail(email);
		 member.setName(name);
		 member.setPasswd(passwd);
		 member.setNickname(nickName);
		 member.setBirthdate(birthdate);
		 member.setPostCode(postCode);
		 member.setAddress1(address1); 
		 member.setAddress2(address2);
		 member.setAuth_status("N");
		 System.out.println(member.toString());
		 MemberJoinProService service = new MemberJoinProService();
		 boolean isJoinSuccess = service.joinMember(member);
		 if(!isJoinSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원 가입 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				// 가입 성공 시 인증 메일 발송을 위한 서블릿 주소 요청(파라미터 : 아이디, 이메일)
				forward = new ActionForward();
				forward.setPath("SendAuthMail.me?email=" + member.getEmail());
				forward.setRedirect(true);
			}
			
			return forward;

	}

}
