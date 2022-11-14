package action.serviceCenter;

import java.util.*;

import javax.servlet.http.*;

import action.Action;
import svc.*;
import svc.serviceCenter.FAQDetailService;
import vo.*;

public class FAQDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("FAQDetailAction - execute");
		ActionForward forward = null;
		
		String keyword = "";
		if(request.getParameter("keyword") != null) {
			keyword = request.getParameter("keyword");
		}
		
		int idx = Integer.parseInt(request.getParameter("idx"));
//		System.out.println("idx : " + idx);
		
		FAQDetailService service = new FAQDetailService();
		
		service.increaseReadcount(idx);
		
		FAQDTO faq = service.getFAQ(idx);
		
		FAQReplyDTO reply = service.getFAQReply(idx); 
//		System.out.println("reply : " + reply);
		
		request.setAttribute("faq", faq);
		request.setAttribute("reply", reply);
		
		forward = new ActionForward();
		forward.setPath("serviceCenter/faq/faqDetail.jsp?keyword="+ keyword);
		forward.setRedirect(false);
		
		return forward;
	}

}
