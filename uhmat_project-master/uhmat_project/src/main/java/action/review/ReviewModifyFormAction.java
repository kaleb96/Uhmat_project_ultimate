package action.review;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.review.ReviewDetailService;
import vo.ActionForward;
import vo.ReviewBoardDTO;

public class ReviewModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("ReviewModifyFormAction");
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		ReviewDetailService service = new ReviewDetailService();
		ReviewBoardDTO dto = service.getReviewBoard(idx);
		
		//세션을 확인 후 권한이 있는지 확인
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("sNickName");
		System.out.println("session name: "+name);
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/html; charset=UTF-8");
		if(name == null) {
			System.out.println("돌아갸야댐!!");
			out.print("<script>alert('리뷰 수정 권한 없음!');history.back();</script>");
		}
		
		if(!name.equals(dto.getNickname())) {
			System.out.println("돌아갸야댐!!");
			out.print("<script>alert('리뷰 수정 권한 없음!');history.back();</script>");
		}
		
		request.setAttribute("dto", dto);
		request.setAttribute("originPath", dto.getPhoto());

		forward = new ActionForward();
		forward.setPath("food/review/reviewModifyForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
