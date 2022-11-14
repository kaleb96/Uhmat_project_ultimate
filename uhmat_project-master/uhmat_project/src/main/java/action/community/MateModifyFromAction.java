package action.community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.MateDetailService;
import vo.ActionForward;
import vo.MateDTO;

public class MateModifyFromAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MateModifyFromAction");
		
		ActionForward forward = null;
		
		// 글 수정에 필요한 글번호 파라미터 가져오기
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 글 수정에 필요한 게시물 상세 정보 조회를 위해
		// 기존에 정의된 MateDetailService 클래스의 getMate() 메서드를 호출하여
		// 게시물 상세 정보를 리턴받아 mate_modify.jsp 페이지로 포워딩
		MateDetailService service = new MateDetailService();
		MateDTO mate = service.getMate(idx);
		
		// 리턴받은 게시물 정보(MateDTO 객체)를 request 객체에 저장
		request.setAttribute("mate", mate);
		
		// board 디렉토리의 mate_modify.jsp 페이지로 포워딩
		forward = new ActionForward();
		forward.setPath("community/mate/mate_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
