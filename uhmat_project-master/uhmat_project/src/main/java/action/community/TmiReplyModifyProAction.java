package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.TmiReplyModifyProService;
import vo.ActionForward;
import vo.TmiReplyDTO;

public class TmiReplyModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TmiReplyModifyProAction !");
		
		ActionForward forward = null;
		
		// TmiReplyDTO 객체 저장
		TmiReplyDTO tmiReply = new TmiReplyDTO();
		tmiReply.setIdx(Integer.parseInt(request.getParameter("idx")));
		tmiReply.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));
		tmiReply.setNickname(request.getParameter("nickname"));
		tmiReply.setContent(request.getParameter("content"));
		
		// modifyTmiReply() 메서드를 호출하여 댓글 수정 작업 요청
		TmiReplyModifyProService service = new TmiReplyModifyProService();
		boolean isTmiReplyWriter = service.isTmiReplyWriter(tmiReply.getIdx(), tmiReply.getNickname());
		
		
		// 댓글 수정 작업 요청 처리 결과를 전달받아 성공/실패 여부 판별
		if(!isTmiReplyWriter) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 수정 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
		} else { // 닉네임이 일치할 경우
			// TmiReplyModifyProService 의 modifyTmiReply() 메서드 호출하여 글수정 작업 요청
			boolean isTmiReplyModifySuccess = service.modifyTmiReply(tmiReply);
			
			if(!isTmiReplyModifySuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('댓글 수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
			// 글 수정 작업 결과 판별
			forward = new ActionForward();
			forward.setPath("TmiDetail.co?idx=" + Integer.parseInt(request.getParameter("board_idx")) + "&pageNum=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
			}
		}
		
		return forward;
		
	}

}
