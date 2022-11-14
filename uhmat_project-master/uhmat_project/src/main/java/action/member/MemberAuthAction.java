package action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.member.MemberAuthService;
import vo.ActionForward;
import vo.AuthInfoDTO;

public class MemberAuthAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		AuthInfoDTO authInfo =new AuthInfoDTO();
		authInfo.setEmail(request.getParameter("email"));	
		authInfo.setAuthCode(request.getParameter("authCode"));
		System.out.println(authInfo.getEmail()+" "+authInfo.getAuthCode());
				 
		MemberAuthService service =new MemberAuthService();
		boolean isAuthenticationSuccess =service.isAuthentication(authInfo);
		
		if(!isAuthenticationSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('인증에 실패했습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('인증에 성공했습니다.')");
			out.println("location.href='MemberLogin.me'");
			out.println("</script>");
//			forward = new ActionForward();
//			forward.setPath("Main.auth");
//			forward.setRedirect(true);
		}
		return forward;
	}

}
