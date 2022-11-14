package action.main;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.review.ReviewListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ReviewBoardDTO;

public class ReviewBestAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReviewBestAction");
		ActionForward forward = null;
		
		
		//페이징 처리를 위한 변수 선언
		int pageNum = 1;
		int listLimit = 10;
		int pageLimit = 10;
		
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		String targetTag = "";
		
		if(request.getParameter("targetTag") != null) {
			
			targetTag = request.getParameter("targetTag");
		}	
		
		//페이징 처리에 필요한 전체 게시물 갯수 조회 작업
		
		ReviewListService reviewListService = new ReviewListService();
		int listCount = reviewListService.getListCount();
		
		System.out.println("전체 게시물 수 : " + listCount);
		
		//페이징 처리를 위한 계산 작업
		// 1.현재 페이지에서 표시할 전체 페이지 수 계산
		int maxPage = (int)Math.ceil((double)listCount/ listLimit);
		
		// 2. 현재 펭지에서 보여줄 시작 페이지 번호
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;
		
		// 3. 현재 페이지에서 보여줄 끝 페이지 번호
		int endPage = startPage + pageLimit - 1;
		
		// 4. 끝페이지를 총 페이지 수로 대체
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이징 처리 정보를 pageInfo 객체에 저장
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
		// 최다 좋아요 리뷰 
		ArrayList<ReviewBoardDTO> reviewBestLikeList = null;
		reviewBestLikeList = reviewListService.getBestLikeBoardList(pageNum,listLimit,targetTag);
		
//		// 최고 식당 리뷰
//		ArrayList<RestaurantInfoDTO> reviewUhmatList = null;
//		reviewUhmatList = service.getBestResBoardList(pageNum,listLimit);
		
		request.setAttribute("pageInfo", pageInfo);
		// 최다 좋아요 리뷰 리턴
		request.setAttribute("reviewList", reviewBestLikeList);
//		request.setAttribute("reviewUhmatList", reviewUhmatList);
//		request.setAttribute("reviewBestLikeList", resBestList);
//		request.setAttribute("reviewBestLikeList", resUhmatList);
		
		forward = new ActionForward();
		forward.setPath("food/review/reviewList.jsp?tagTag=\" + targetTag");
		forward.setRedirect(false);
		
		return forward;
	}

}
