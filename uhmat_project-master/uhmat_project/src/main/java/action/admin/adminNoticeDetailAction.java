package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.serviceCenter.NoticeDetailService;
import vo.ActionForward;
import vo.NoticeDTO;

public class adminNoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		int idx = Integer.parseInt(request.getParameter("idx"));
		String title=request.getParameter("title");
		NoticeDetailService service = new NoticeDetailService();
		
		NoticeDTO notice = service.getNotice(idx);
		
		request.setAttribute("notice", notice);
		
		forward = new ActionForward();
		forward.setPath("admin/noticeDetail.jsp?title"+title);
		forward.setRedirect(false);
		
		return forward;
	}

}
