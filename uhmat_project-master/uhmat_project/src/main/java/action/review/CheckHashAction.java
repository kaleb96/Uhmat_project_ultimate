package action.review;

import java.io.*;

import javax.servlet.http.*;

import action.Action;
import svc.*;
import svc.review.CheckHashService;
import vo.*;

public class CheckHashAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("CheckHashAction");
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("UTF-8");
		String hash = request.getParameter("hash");
		
		CheckHashService service = new CheckHashService();
		
		boolean checkHashSuccess = service.checkHash(hash);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(!checkHashSuccess) {
			out.println("<script>");
			out.println("alert('좋아요 수정 실패!')");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("ReviewWriteForm.re?hash=" + hash);
			forward.setRedirect(true);
		}
		return forward;
	}

}
