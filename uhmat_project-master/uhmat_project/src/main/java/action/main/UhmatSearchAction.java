package action.main;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.main.UhmatSearchService;
import vo.ActionForward;
import vo.CommunityTmiDTO;
import vo.FAQDTO;
import vo.MateDTO;
import vo.NoticeDTO;
import vo.RestaurantInfoDTO;
import vo.ReviewBoardDTO;

public class UhmatSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String search = request.getParameter("search");
		System.out.println("search : " + search);
		
		UhmatSearchService service = new  UhmatSearchService();
		
		ArrayList<ReviewBoardDTO> reviewBoard= service.getSearhReviewBoard(search); 
		ArrayList<RestaurantInfoDTO> restaurantBoard = service.getSearchRestaurantInfo(search);
		System.out.println("reviewBoard : " + reviewBoard + ", restaurantBoard : " + restaurantBoard);
		
		request.setAttribute("reviewBoard", reviewBoard);
		request.setAttribute("restaurantBoard", restaurantBoard);
		
		forward = new ActionForward();
		forward.setPath("searchResult.jsp");
		forward.setRedirect(false);
				
		return forward;
	}

}
