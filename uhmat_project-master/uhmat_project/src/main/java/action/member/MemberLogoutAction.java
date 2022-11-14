package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import vo.ActionForward;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberLogoutAction");
		
		ActionForward forward = null;
		
		// 세션 객체 가져와서 초기화
		HttpSession session = request.getSession();
		if(session!=null) {
		session.invalidate();
		forward = new ActionForward();
		forward.setPath("main.jsp"); 
		forward.setRedirect(true);
		}else {
		
		// 메인페이지 포워딩
		forward = new ActionForward();
		forward.setPath("main.jsp"); 
		forward.setRedirect(true);
		}
		return forward;
	}

}
