package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class MemberFindPasswordProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String email = request.getParameter("email");
		System.out.println(email);
		forward = new ActionForward();
		forward.setPath("SendPasswordMail.me?email=" + email);
		forward.setRedirect(true);

		return forward;
	}

}
