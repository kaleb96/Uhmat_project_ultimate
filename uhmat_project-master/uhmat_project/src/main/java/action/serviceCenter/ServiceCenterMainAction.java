package action.serviceCenter;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.serviceCenter.ServiceCenterMainService;
import vo.ActionForward;
import vo.FAQDTO;
import vo.NoticeDTO;

public class ServiceCenterMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ServiceCenterMainService service = new ServiceCenterMainService();
		
		
		ArrayList<FAQDTO> faq5 = service.getSVFAQList(); 
		
		ArrayList<NoticeDTO> notice5 = service.getSVNoticeList();
		
		request.setAttribute("faq5", faq5);
		request.setAttribute("notice5", notice5);
		
		forward = new ActionForward();
		forward.setPath("serviceCenter/serviceCenterMain.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
