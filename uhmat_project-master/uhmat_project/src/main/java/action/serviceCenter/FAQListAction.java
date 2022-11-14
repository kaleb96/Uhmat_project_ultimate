package action.serviceCenter;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.serviceCenter.FAQListService;
import vo.ActionForward;
import vo.FAQDTO;
import vo.PageInfo;

public class FAQListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FAQListAction-execute");
		ActionForward forward = null;

		// 페이징 처리를 위한 변수 선언
		int pageNum = 1; // 현재페이지 번호
		int listLimit = 10; // 한 페이지 당 표시할 게시물 수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 수
		
		String keyword = "";
		if(request.getParameter("keyword") != null) {
			keyword = request.getParameter("keyword");
		}
		
		// 단, URL 파라미터로 현재 페이지번호(pageNum) 가 전달됐을 경우 가져와서 변수에 저장
		if(request.getParameter("pageNum") != null){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		//페이징 처리에 필요한 전체 게시물 갯수 조회 작업 요청
		FAQListService service = new FAQListService();
		
		int listCount = 0;
		
		listCount = service.getListSelectCount(keyword);

//		System.out.println("전체 게시물 수 " + listCount);
		
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
		
		//--------------------------------------------
		// BoardListService 객체의 getBoardList() 메서드를 호출하여 게시물 목록 가져오기
		// => 파라미터 : 현재 페이지번호(pageNum), 페이지 당 게시물 수(listLimit) 
		// => 리턴타입 : ArrayList<BoardDTO>(boardList)
		
		
		// 페이징 처리 정보를 PageInfo 객체에 저장
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
		
		ArrayList<FAQDTO> list = service.getFAQList(pageNum, listLimit, keyword);
//		System.out.println("list : " + list);
//		System.out.println(pageInfo);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		
		forward = new ActionForward();
		forward.setPath("serviceCenter/faq/faqlist.jsp?keyword="+keyword);
		forward.setRedirect(false);
		
		return forward;
	}

}
