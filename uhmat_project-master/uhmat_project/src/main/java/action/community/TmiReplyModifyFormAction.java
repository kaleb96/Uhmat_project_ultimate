package action.community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.TmiReplyModifyService;
import vo.ActionForward;
import vo.TmiReplyDTO;

public class TmiReplyModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TmiReplyModifyFormAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
//		System.out.println(idx);
		
		TmiReplyModifyService service = new TmiReplyModifyService();
		TmiReplyDTO tmiReply = service.getTmiReply(idx);
		
		request.setAttribute("tmiReply", tmiReply);
		
		forward = new ActionForward();
		forward.setPath("community/tmi/tmiReplyModify.jsp?idx=" + idx + "&pageNum=" + request.getParameter("pageNum"));
		forward.setRedirect(false);
		
		return forward;
	}

}
