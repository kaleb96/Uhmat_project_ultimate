package action.community;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.MateDetailService;
import vo.ActionForward;
import vo.MateDTO;
import vo.MateReplyDTO;

public class MateDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MateDetailAction");
//		System.out.println("깃 커밋 안 올라가져서 테스트");
		
		ActionForward forward = null;
		
		// request 객체를 통해 전달받은 파라미터(idx) 가져오기
		int idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println("idx: " +idx);
		
		// MateDetailService 인스턴스 생성 후 increaseReadcount() 메서드 호출하여 조회수 증가 요청
		// => 파라미터 : 글번호(idx)   리턴타입 : void
		MateDetailService service = new MateDetailService();
		service.increaseReadcount(idx);
		
		// getMate() 메서드 호출하여 글 상세정보 조회 요청
		// => 파라미터 : 글번호(idx)   리턴타입 : MateDTO(mate)
		MateDTO mate = service.getMate(idx);
		
		// 댓글 리스트
		ArrayList<MateReplyDTO> mateReplyList = service.getMateReply(idx);
		
		// 조회 결과(1개 게시물 정보 = MateDTO 객체)를 request 객체에 저장
		request.setAttribute("mate", mate);
		
		request.setAttribute("mateReplyList", mateReplyList);
		
		// ActionForward 객체를 활용하여 mate 디렉토리의 mate_view.jsp 페이지 포워딩 설정
		forward = new ActionForward();
		forward.setPath("community/mate/mate_view.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
