package action.community;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.MateListProService;
import vo.ActionForward;
import vo.MateDTO;
import vo.PageInfo;

public class MateListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MateListAction");
		
		ActionForward forward = null;
		
		// 검색기능도 함께 수행할 keyword 저장!
		String keyword = "";
		System.out.println("검색액션 키워드 - " + keyword);
		
		// 페이징 처리를 위한 변수 선언
		int pageNum = 1; // 현재 페이지 번호(기본값 1 페이지로 설정)
		int listLimit = 10; // 한 페이지 당 표시할 게시물 수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 수

		// 단, URL 파라미터로 현재 페이지번호(pageNum) 가 전달됐을 경우 가져와서 변수에 저장
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum")); // String -> int 변환
		}
		
		// 키워드가 ""이 아닐때 키워드를 가져와 변수에 저장!
		if(request.getParameter("keyword") != null) {
			keyword  = request.getParameter("keyword");
		}
		
		// 페이징 처리에 필요한 전체 게시물 갯수 조회 작업 요청
		// MateListProService 클래스 인스턴스 생성 후 mateCount() 메서드 호출하여 총 게시물 수 조회
		// => 파라미터 : 없음     리턴타입 : int(listCount)
		MateListProService service = new MateListProService();
		int listCount = service.mateCount(keyword);
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
				
		// 페이징 처리 정보를 PageInfo 객체에 저장
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
				
		// -------------------------------------------------------------------------------------
		// MateListProService 객체의 getMateList() 메서드를 호출하여 게시물 목록 가져오기
		// => 파라미터 : 현재 페이지번호(pageNum), 페이지 당 게시물 수(listLimit)
		// => 리턴타입 : ArrayList<MateDTO>(mateList)
		ArrayList<MateDTO> mateList = service.getMateList(keyword, pageNum, listLimit);
		
		// 뷰페이지(jsp)에서 사용할 데이터가 저장된 객체들을 전달하기 위해
		// request 객체의 setAttribute() 메서드를 호출하여 객체 저장
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("mateList", mateList);
		
		// ActionForward 객체 생성하여 포워딩 정보 저장
		// => board 디렉토리 내의 qna_board_list.jsp 페이지 지정
		// => URL 및 request 객체 유지한 채 포워딩을 위해 Dispatcher 방식 지정
		forward = new ActionForward();
		forward.setPath("community/mate/mate_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
