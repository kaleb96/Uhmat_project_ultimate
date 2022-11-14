package action.serviceCenter;

import java.util.*;

import javax.servlet.http.*;

import action.Action;
import svc.*;
import svc.serviceCenter.LiveTalkService;
import vo.*;

public class LiveTalkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		LiveTalkService service = new LiveTalkService();
		
		ArrayList<LiveTalkDTO> list = service.selectTalkLive();
		request.setAttribute("list", list);
//		System.out.println("Action의 list : " + list);
		
		forward = new ActionForward();
		forward.setPath("serviceCenter/livetalk/LiveTalk.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
