package action.serviceCenter;

import java.io.*;

import javax.servlet.http.*;

import action.Action;
import svc.*;
import svc.serviceCenter.FAQDeleteService;
import svc.serviceCenter.FAQDetailService;
import vo.*;

public class FAQDetailReplyDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		FAQDeleteService service = new FAQDeleteService();
		
		boolean deleteReplySuccess = service.removeReplyFAQ(idx);
		
		if(!deleteReplySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
			
		} else {
			FAQDetailService service2 = new FAQDetailService();
			
			service2.increaseReadcount(idx);
			
			FAQDTO faq = service2.getFAQ(idx);
			
			FAQReplyDTO reply = service2.getFAQReply(idx); 
//			System.out.println("reply : " + reply);
			
			request.setAttribute("faq", faq);
			request.setAttribute("reply", reply);
			
			forward = new ActionForward();
			forward.setPath("serviceCenter/faq/faqDetail.jsp");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
