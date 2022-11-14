package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.serviceCenter.FAQDetailService;
import vo.ActionForward;
import vo.FAQDTO;
import vo.FAQReplyDTO;

public class adminFAQDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("FAQDetailAction - execute");
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String title=request.getParameter("title");
//		System.out.println("idx : " + idx);
		
		FAQDetailService service = new FAQDetailService();
		
		service.increaseReadcount(idx);
		
		FAQDTO faq = service.getFAQ(idx);
		
		FAQReplyDTO reply = service.getFAQReply(idx); 
//		System.out.println("reply : " + reply);
		
		request.setAttribute("faq", faq);
		request.setAttribute("reply", reply);
		
		forward = new ActionForward();
		forward.setPath("admin/faqDetail.jsp?title="+title);
		forward.setRedirect(false);
		
		return forward;
	}
}
