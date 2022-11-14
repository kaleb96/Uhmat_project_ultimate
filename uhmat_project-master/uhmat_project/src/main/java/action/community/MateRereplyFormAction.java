package action.community;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.MateRereplyFormService;
import vo.ActionForward;
import vo.MateReplyDTO;

public class MateRereplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MateRereplyFormAction");
		
		ActionForward forward = null;
		
		MateRereplyFormService service = new MateRereplyFormService();
		MateReplyDTO mateReply = service.selectReply(Integer.parseInt(request.getParameter("reply_idx")));
		
		request.setAttribute("mateReply", mateReply);
		
		forward = new ActionForward();
		forward.setPath("community/mate/mate_RereplyForm.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
