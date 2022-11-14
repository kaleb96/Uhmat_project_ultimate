package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.TmiModifyProService;
import vo.ActionForward;
import vo.CommunityTmiDTO;

public class TmiModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TmiModifyProAction");
		
		ActionForward forward = null;
		
		// CommunityTmiDTO에서 파라미터를 통해 변수 저장
		CommunityTmiDTO tmiBoard = new CommunityTmiDTO();
		tmiBoard.setIdx(Integer.parseInt(request.getParameter("idx")));
		tmiBoard.setNickname(request.getParameter("nickname"));
		tmiBoard.setSubject(request.getParameter("subject"));
		tmiBoard.setContent(request.getParameter("content"));
		System.out.println(tmiBoard);
		
		// 게시물 수정 권한 판별을 위해 전달받은 파라미터 중 닉네임 비교
		// => TmiModifyProService의 isTmiWriter() 메서드를 호출
		//	  파라미터 : 글번호, 닉네임		리턴타입 : boolean(isTmiWriter)

		TmiModifyProService service = new TmiModifyProService();
		boolean isTmiWriter = service.isTmiWriter(tmiBoard.getIdx(), tmiBoard.getNickname());
		
		// 수정 가능 여부 판별(isTmiWriter 변수값을 판별)
		// => 닉네임이 일치하지 않았을 경우(= isTmiWriter가 false)
		//	  자바스크립트를 사용하여 "수정 불가능!" 출력 후 이전페이지로 돌아가기
		// => 아니면, 수정 가능! 출력
		if(!isTmiWriter) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 불가능!')");
			out.println("history.back()");
			out.println("</script>");
		} else { // 닉네임이 일치하는 경우
			// TmiModifyProService의 modifyTmiBoard() 메서드 호출하여 글수정 작업 요청
			// => 파라미터 : CommunityTmiDTO 객체 		리턴타입 : boolean(isTmiModifySuccess)
			boolean isTmiModifySuccess = service.ModifyTmiBoard(tmiBoard);
			
			// 글 수정 작업 결과 판별
			// 실패 시 자바스크립트를 사용하여 "글 수정 실패!" 출력 후 이전페이지로 돌아가기
			// 성공 시 ActionFowrad 객체를 생성하여 TmiDetail.co 페이지 요청
			// => 파라미터 : 글번호, 페이지번호
			if(!isTmiModifySuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('글 수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setPath("TmiDetail.co?idx=" + tmiBoard.getIdx() + "&pageNum=" + request.getParameter("pageNum"));
				forward.setRedirect(true);
				}
			
		}
		
		return forward;
	}

}
