package action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.member.MemberPasswordModifyProService;
import util.SHA256;
import vo.ActionForward;

public class MemberPasswordModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String email = request.getParameter("email");
		String nickname=request.getParameter("nickname");
		String rawpasswd= request.getParameter("passwd");
		String rawalterPasswd =request.getParameter("alterPassword");
		MemberPasswordModifyProService service = new MemberPasswordModifyProService();
		String alterPasswd= SHA256.encodeSha256(rawalterPasswd);
		boolean alterPasswdCheck = service.alterPasswdCheck(email, alterPasswd,nickname);
		
		
		
		if(alterPasswdCheck) {
		String passwd= SHA256.encodeSha256(rawpasswd);
		
		boolean isModifyPasswordSuccess = service.modifyPassword(email, passwd,nickname);
		
		if(!isModifyPasswordSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('새로운 패스워드 등록 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('새로운 패스워드 등록 성공!')");
			out.println("</script>");
			forward = new ActionForward();
			forward.setPath("MemberLogin.me");
			forward.setRedirect(true);
		}
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('(임시 또는 현재) 비밀번호가 다릅니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	

		return forward;
	}

}
