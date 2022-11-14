package action.review;


import java.util.*;

import javax.servlet.http.*;

import action.Action;
import svc.*;
import svc.review.ReviewDetailService;
import vo.*;


public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("ReviewDetailAction");
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int pageNum=1;
		if(request.getParameter("pageNum")=="") {
		}else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));			
		}
		
		ReviewDetailService service = new ReviewDetailService();
		ReviewBoardDTO dto = service.getReviewBoard(idx);
		ArrayList<ReviewReplyDTO> replyList = ReviewDetailService.getDetailReply(idx);
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("replyList", replyList);
		HttpSession session = request.getSession();
		session.setAttribute("reviewer", dto.getNickname());
		
		request.setAttribute("pageNum", pageNum);
		System.out.println(dto);
		forward = new ActionForward();
		
		forward.setPath("food/review/reviewDetailView.jsp");

		forward.setRedirect(false);
		return forward;

	}

}
