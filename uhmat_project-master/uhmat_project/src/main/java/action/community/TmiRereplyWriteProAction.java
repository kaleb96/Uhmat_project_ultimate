package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.TmiRereplyWriteProService;
import vo.ActionForward;
import vo.TmiReplyDTO;

public class TmiRereplyWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("답글 액션 - TmiRereplyWriteProAction");
		
		ActionForward forward = null;
		
		TmiRereplyWriteProService service = new TmiRereplyWriteProService();
		
		// TmiReplyDTO 객체 저장
		TmiReplyDTO tmiRereply = new TmiReplyDTO();
		int idx = Integer.parseInt(request.getParameter("idx"));
		tmiRereply.setIdx(idx);
		tmiRereply.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));
		tmiRereply.setNickname(request.getParameter("nickname"));
		tmiRereply.setContent(request.getParameter("content"));
		tmiRereply.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		tmiRereply.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		tmiRereply.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		System.out.println("답글 액션 tmiRereply 인스턴스 - " + tmiRereply);
		
		boolean isTmiRereplyWriteSuccess = service.writeTmiRereply(tmiRereply);
		
		if(!isTmiRereplyWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답글 작성 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("TmiDetail.co?idx="+ Integer.parseInt(request.getParameter("board_idx")) + "&pageNum=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
		}
		
		
		return forward;
	}

}
