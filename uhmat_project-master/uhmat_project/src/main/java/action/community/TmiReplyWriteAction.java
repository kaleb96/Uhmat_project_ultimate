package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.TmiReplyWriteService;
import vo.ActionForward;
import vo.TmiReplyDTO;

public class TmiReplyWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TmiReplyWriteAction");
		
		ActionForward forward = null;
		
		
		// 실제 비즈니스 작업 요청을 수행할 TmiWriteProService 클래스의 인스턴스를 생성
		TmiReplyWriteService service = new TmiReplyWriteService();
		
		// TmiReplyDTO 객체 저장
		TmiReplyDTO tmiReply = new TmiReplyDTO();
		int idx = Integer.parseInt(request.getParameter("idx"));
//		tmiReply.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));
		tmiReply.setBoard_idx(idx);
		tmiReply.setNickname(request.getParameter("nickname"));
		tmiReply.setContent(request.getParameter("content"));
		System.out.println(tmiReply);
		
		// writeTmiReply() 메서드를 호출하여 글 쓰기 작업 요청
		boolean isTmiReplyWriteSuccess = service.writeTmiReply(tmiReply);
		
		// Service 클래스로부터 댓글쓰기 작업 요청 처리 결과를 전달받아 성공/실패 여부를 판별
		if(!isTmiReplyWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 작성 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("TmiDetail.co?idx=" + idx + "&pageNum=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
