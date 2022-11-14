package action.serviceCenter;

import java.io.*;

import javax.servlet.http.*;

import action.Action;
import svc.*;
import svc.serviceCenter.FAQDetailReplyService;
import vo.*;

public class FAQDetailReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("FAQDetailReplyAction - execute");
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String nickname = request.getParameter("nickname");
		String answer = request.getParameter("answer"); 

//		System.out.println("answer : " + request.getParameter("answer"));
//		System.out.println("nicknaem : " + request.getParameter("nickname"));
//		System.out.println(Integer.parseInt(request.getParameter("pageNum")));
//		System.out.println(Integer.parseInt(request.getParameter("idx")));

		FAQDetailReplyService service = new FAQDetailReplyService();
		
		boolean isWriteReplySuccess = service.registFAQDetailReply(idx, nickname, answer);
//		System.out.println("isWriteReplySuccess : " + isWriteReplySuccess);
		
		if(!isWriteReplySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 쓰기 실패')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("FAQDetail.sc?idx=" + idx + "&pageNum=" + pageNum);
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
