package action.community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.TmiDetailService;
import vo.ActionForward;
import vo.TmiReplyDTO;

public class TmiRereplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("답글 작성 - TmiRereplyFormAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		// 답글 작성에 필요한 댓글 상세 정보 조회를 위해
		// 기존에 정의된 TmiDetailService 클래스의 getBoard() 메서드를 호출하여
		// 댓글 상세 정보를 리턴받아 qna_board_reply.jsp 페이지로 포워딩
		// => 단, 조회수 증가 작업은 수행하지 않음
		TmiDetailService service = new TmiDetailService();
		
		// 답글(대댓글) 구분을 위해 tmiRereply로 변수명 설정!
		TmiReplyDTO tmiRereply = service.getTmiReply(idx);
//		TmiReplyDTO tmiReply = service.getTmiReply(idx);
//		System.out.println("답글폼액션 - " + tmiRereply);
		
		request.setAttribute("tmiRereply", tmiRereply);
//		request.setAttribute("tmiReply", tmiReply);
		// "tmiRereply"라는 이름으로 tmiReply에 저장한 값을 넘겨줍니다.
		
		forward = new ActionForward();
		forward.setPath("community/tmi/tmiRereply.jsp?idx=" + idx);
		forward.setRedirect(false);
		
		
		return forward;
	}

}
