package action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.member.MemberLoginProService;
import util.SHA256;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLoginProAction");
		
		ActionForward forward = null;
		
//		String email = request.getParameter("email");
		String rawPasswd = request.getParameter("passwd");
		 String passwd= SHA256.encodeSha256(rawPasswd);
		MemberDTO member = new MemberDTO();
		member.setEmail(request.getParameter("email"));
		member.setPasswd(passwd);
	
		MemberLoginProService service = new MemberLoginProService();
		
		boolean isLoginSuccess = service.loginMember(member);
		
		
			
			if(!isLoginSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디 또는 패스워드가 틀렸습니다.')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				boolean isAuthenticatedUser =service.isAuthenticatedUser(member);
				if(!isAuthenticatedUser) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('인증코드를 확인 하세요')");
					out.println("history.back()");
					out.println("</script>");
				} else {
					member = service.getMember(request.getParameter("email"));
				HttpSession session = request.getSession();
				session.setAttribute("sNickName", member.getNickname());
				
				System.out.println(member.getNickname());
				forward = new ActionForward();
				forward.setPath("main.jsp");
				forward.setRedirect(true);
			}
		}
	
		
		return forward;
	}

}
