package action.community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.TmiDetailService;
import vo.ActionForward;
import vo.CommunityTmiDTO;

public class TmiModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TmiModifyFormAction");
		
		ActionForward forward = null;
		
		// 글 수정에 필요한 글번호 파라미터로 가져오기
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 글 수정에 필요한 tmi 게시물 상세 정보 조회를 위해
		// 기존의 정의된 TmiDetailService 클래스의 getTmiBoard() 메서드를 호출
		
		// getTmiBoard() 메서드 호출하여 글 상세정보 조회 요청
		// => 파라미터 : 글번호(idx), 리턴타입 : CommunityDTO(tmiBoard)
		TmiDetailService service = new TmiDetailService();
		CommunityTmiDTO tmiBoard = service.getTmiBoard(idx);
		
		// 리턴받은 게시물 정보(CommunityDTO 객체)를 request 객체에 저장
		request.setAttribute("tmiBoard", tmiBoard);
		System.out.println(tmiBoard.getIdx());
		
		// tmiBoard 디렉토리의 tmiModify.jsp 페이지로 포워딩
		forward = new ActionForward();
		forward.setPath("community/tmi/tmiModify.jsp?idx=" + idx);
		forward.setRedirect(false);
		
		return forward;
	}

}
