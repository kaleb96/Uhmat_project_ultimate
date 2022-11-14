package action.serviceCenter;


import java.io.*;
import java.security.Provider.*;
import java.util.*;

import javax.servlet.http.*;

import action.Action;
import svc.*;
import svc.serviceCenter.LiveTalkService;
import vo.*;

public class LiveTalkProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String talk = request.getParameter("talk");
		String nickname = request.getParameter("nickname");
		System.out.println(talk);
		
		LiveTalkService service = new LiveTalkService();
		
		boolean insertOk = service.insertTalkLive(talk, nickname);
		
		if(!insertOk) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('라이브 댓글 작동하지 않음')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			ArrayList<LiveTalkDTO> list = service.selectTalkLive();
			request.setAttribute("list", list);
			System.out.println("Action의 list : " + list);
			forward = new ActionForward();
			forward.setPath("serviceCenter/livetalk/LiveTalk.jsp");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
