package action.review;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.review.ReviewLikeService;
import vo.ActionForward;

public class ReviewLikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("ReviewLikeAction.re");
		
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		String name = (String)session.getAttribute("sNickName");
		System.out.println("session name: "+name);
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/html; charset=UTF-8");
		
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String nickname = (String)session.getAttribute("sNickName"); //session id 받는 곳
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		System.out.println(idx + nickname + pageNum);
		
		ReviewLikeService service = new ReviewLikeService();
		boolean isModifySuccess = service.modifyLike(idx, nickname);
		
		out = response.getWriter();
		
		if(!isModifySuccess) {
			out.println("<script>");
			out.println("alert('좋아요 수정 실패!')");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("ReviewDetail.re?idx=" + idx + "&pageNum=" + pageNum);
			forward.setRedirect(true);
		}
		return forward;
	}

}
