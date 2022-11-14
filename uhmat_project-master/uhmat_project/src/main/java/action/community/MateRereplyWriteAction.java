package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.MateRereplyWriteService;
import vo.ActionForward;
import vo.MateReplyDTO;

public class MateRereplyWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MateRereplyWriteAction");
		
		ActionForward forward = null;
		
		MateReplyDTO mateRereply = new MateReplyDTO();
		int reply_idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println("reply_idx - " + reply_idx);
		mateRereply.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));
		mateRereply.setContent(request.getParameter("content"));
		mateRereply.setNickname(request.getParameter("nickname"));
//		System.out.println(request.getParameter("re_lev"));
//		System.out.println(request.getParameter("re_seq"));
		mateRereply.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		mateRereply.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		mateRereply.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		
		System.out.println("mateRereply - " + mateRereply);
		
		MateRereplyWriteService service = new MateRereplyWriteService();
		boolean isMateRereplySuccess = service.writeMateRereply(mateRereply);
		
		if(!isMateRereplySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답글 작성 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("MateDetail.co?idx=" + request.getParameter("board_idx") + "&pageNum=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
