package action.community;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.TmiDetailService;
import vo.ActionForward;
import vo.CommunityTmiDTO;
import vo.TmiReplyDTO;


public class TmiDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TmiDetailAction");
		
		ActionForward forward = null;
		
		// request 객체를 통해 전달받은 파라미터(idx)를 가져옵니다.
		int idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println(idx);
		
		// getTmiBoard() 메서드 호출하여 글 상세정보 조회 요청
		// => 파라미터 : 글번호(idx), 리턴타입 : CommunityDTO(tmiBoard)
		TmiDetailService service = new TmiDetailService();
		CommunityTmiDTO tmiBoard = service.getTmiBoard(idx);
		
		// TmiDetailService 인스턴스 생성 후 increaseReadcount() 메서드 호출하여 조회수 증가 요청
		// => 파라미터 : 글번호(idx), 리턴타입 : void
		service.tmiIncreaseReadcount(idx);
		

		// 댓글 리스트 작업
		ArrayList<TmiReplyDTO> tmiReplyList = service.getTmiReplyList(idx);
		System.out.println("TmiDetailAction(댓글 리스트)" + tmiReplyList);
		
		// 답글 리스트 작업
//		ArrayList<TmiReplyDTO> tmiRereplyList = service.getTmiRereplyList(idx);
//		System.out.println("TmiDetailAction(답글 리스트)" + tmiRereplyList);

		// 조회결과(1개 게시물 정보 = CommunityTmiDTO 객체)를 request 객체에 저장
		request.setAttribute("tmiBoard", tmiBoard);
//		System.out.println(tmiBoard.getIdx());
		
		// 댓글 조회 결과
		request.setAttribute("tmiReplyList", tmiReplyList);

		// ActionForward 객체를 활용하여 community 디렉토리의 tmiDetail.jsp 페이지 포워딩 설정
		// => Dispatcher 방식
		forward = new ActionForward();
		forward.setPath("community/tmi/tmiDetail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
