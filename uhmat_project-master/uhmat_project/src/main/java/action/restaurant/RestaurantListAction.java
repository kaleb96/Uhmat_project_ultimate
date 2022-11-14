package action.restaurant;

import java.util.*;

import javax.servlet.http.*;

import action.Action;
import svc.*;
import svc.restaurant.RestaurantListService;
import vo.*;

public class RestaurantListAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("RestaurantListAction");
		String category = request.getParameter("category"); 	//카테고리 검색시 가져오기
		String keyword = request.getParameter("keyword");
		System.out.println("==============================");
		System.out.println("category: "+category);
		System.out.println("keyword: "+keyword);
		RestaurantListService service = new RestaurantListService();
		//1. 페이지 계산하는 메서드
		int listCount = 0;
		
		/*
		 * 1.검색어와 카테고리 2다 존재
		 * 2. 검색어만 존재
		 * 3. 카테고리만 존재
		 * 4. 전체 선택 (둘다 없음)
		 */
		if(category!=null && keyword!=null) {
			listCount = service.getListCount(category,keyword);
		}
		else if(category!=null) {
			listCount = service.getListCount(category);
		}else if(keyword!=null){
			listCount = service.getListCount(1,keyword);	//카테고리와 다른 메서드 오버라이딩 하기 위해 임의의 파라미터 1 전달
		}else {
			listCount = service.getListCount();
		}
		int pageNum = 1; // 현재 페이지 번호(기본값 1 페이지로 설정)
		int listLimit = 10; // 한 페이지 당 표시할 게시물 수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 수
		System.out.println("===================================");
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum")); // String -> int 변환
		}
		
		System.out.println("전체 게시물 수 : " + listCount);
		// -------------------------------------------------------------------------------------
		// 페이징 처리를 위한 계산 작업
		// 1. 현재 페이지에서 표시할 전체 페이지 수 계산
		int maxPage = (int)Math.ceil((double)listCount / listLimit);

		// 2. 현재 페이지에서 보여줄 시작 페이지 번호(1, 11, 21 등의 시작 번호) 계산
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1) * pageLimit + 1;

		// 3. 현재 페이지에서 보여줄 끝 페이지 번호(10, 20, 30 등의 끝 번호) 계산
		int endPage = startPage + pageLimit - 1;

		// 4. 만약, 끝 페이지(endPage)가 현재 페이지에서 표시할 총 페이지 수(maxPage)보다 클 경우
		// 끝 페이지 번호를 총 페이지 수로 대체
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		System.out.println("pageNum : "+pageNum);
		System.out.println("startPage: "+startPage);
		System.out.println("listLimit: "+listLimit);
		System.out.println("endPage: "+endPage);
		//2.페이지 개수만큼의 글 목록을 가져오기 
		
		List<RestaurantInfoDTO> list = null;
		//카테고리로 검색될 경우
		
		if(request.getParameter("bestRes")!=null){
			list = service.selectBestRes(pageNum,listLimit);
		}else if(category != null) {
			if(keyword!=null) {	//검색어 있을 때
				list = service.selelctRestaurantList(pageNum,listLimit,category,keyword);
				System.out.println("카테고리+ 키워드로 검색됨!");
			}else {
				list = service.selelctRestaurantList(pageNum,listLimit,category);
				System.out.println("카테고리 "+category+"로 검색됨!");				
			}
		}else { //전체 조회일 경우
			if(keyword!=null) {	//검색어 있을 때
				list = service.selelctRestaurantList(pageNum,keyword,listLimit);
				System.out.println("키워드로 검색됨!");
			}else {
				list = service.selelctRestaurantList(pageNum,listLimit);	
				System.out.println("전체 검색됨!");				
			}
		}
		
		
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("restaurantInfo", list);
		forward = new ActionForward();
		forward.setPath("food/restaurant/res_list.jsp?pageNum="+pageNum);
		forward.setRedirect(false);
		return forward;
	}
	
	

}
