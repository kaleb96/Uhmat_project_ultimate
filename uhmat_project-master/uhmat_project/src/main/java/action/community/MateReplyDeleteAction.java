package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.MateReplyDeleteService;
import vo.ActionForward;

public class MateReplyDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("MateReplyDeleteAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int reply_idx = Integer.parseInt(request.getParameter("reply_idx"));
		String nickname = request.getParameter("nickname");
		System.out.println(idx);
		System.out.println(reply_idx);
		System.out.println(nickname);
		
		// MateReplyDeleteService - isDeleteReplyMate() 메서드를 호출하여 삭제 요청
		// => 파라미터 : 글번호    리턴타입 : boolean(isDeleteSuccess)
		MateReplyDeleteService service = new MateReplyDeleteService();
		boolean isDeleteSuccess = service.isDeleteReplyMate(reply_idx, nickname);
		System.out.println("action" + isDeleteSuccess);
		
		if(!isDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("MateDetail.co?pageNum=" + request.getParameter("pageNum") + "&idx=" + idx);
			forward.setRedirect(true);
		}
		
		return forward;
		
	}

}
