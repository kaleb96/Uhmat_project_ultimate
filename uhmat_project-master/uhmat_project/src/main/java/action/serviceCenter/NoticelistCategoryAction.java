package action.serviceCenter;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.serviceCenter.NoticelistCategoryService;
import vo.ActionForward;
import vo.NoticeDTO;
import vo.PageInfo;

public class NoticelistCategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String category ="";
		if(request.getParameter("name")!= null) {
			category = request.getParameter("name");
		}
//		System.out.println("category : " + category);
		
		// 페이징 처리를 위한 변수 선언
		int pageNum = 1; // 현재페이지 번호
		int listLimit = 10; // 한 페이지 당 표시할 게시물 수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 수

		// 단, URL 파라미터로 현재 페이지번호(pageNum) 가 전달됐을 경우 가져와서 변수에 저장
		if(request.getParameter("pageNum") != null){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		NoticelistCategoryService service = new NoticelistCategoryService();
		
		int listCount = service.getListCount(category);
		
		// 1. 현재 페이지에서 표시할 전체 페이지 수 계산
		int maxPage = (int)Math.ceil((double)listCount / listLimit);

		// 2. 현재 페이지에서 보여줄 시작페이지 번호(1, 11, 21, ...)  - 알면 좋음
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1 ) * pageLimit + 1;

		// 3. 현재 페이지에서 보여줄 끝 페이지 번호(10,20, 30, ~~~)
		int endPage = startPage + pageLimit - 1;
		
		// 4.
		if(endPage > maxPage){
			endPage = maxPage;
		}
		// 페이징 처리 정보를 PageInfo 객체에 저장
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
		
		//--------------------------------------------
		// BoardListService 객체의 getBoardList() 메서드를 호출하여 게시물 목록 가져오기
		// => 파라미터 : 현재 페이지번호(pageNum), 페이지 당 게시물 수(listLimit) 
		// => 리턴타입 : ArrayList<BoardDTO>(boardList)
		ArrayList<NoticeDTO> categoryList = service.selectNoticeCategorylist(pageNum, listLimit, category);
//		System.out.println("Action의 list : "+ categoryList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", categoryList);
		
		forward = new ActionForward();
		forward.setPath("serviceCenter/notice/noticelist.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
