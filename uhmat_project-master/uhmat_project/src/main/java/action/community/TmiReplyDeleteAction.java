package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.TmiReplyDeleteProService;
import vo.ActionForward;

public class TmiReplyDeleteAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TmiReplyDeleteAction");
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int board_idx = Integer.parseInt(request.getParameter("board_idx"));
		String nickname = request.getParameter("nickname");
//		System.out.println(idx);
//		System.out.println(board_idx);
//		System.out.println(nickname);
		
		// TmiReplyDeleteProService - isDeleteTmiReply() 메서드를 호출하여 삭제를 요청
		TmiReplyDeleteProService service = new TmiReplyDeleteProService();
		boolean isTmiReplyDeleteSuccess = service.isDeleteTmiReply(idx, nickname);
		System.out.println("TmiReplyDeleteAction - " + isTmiReplyDeleteSuccess);
		
		if(!isTmiReplyDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("TmiDetail.co?idx=" + request.getParameter("board_idx") + "&pageNum=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
		}
		
		
		return forward;
		
	}
}
