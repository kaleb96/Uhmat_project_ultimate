package action.main;

import java.util.*;

import javax.servlet.http.*;

import action.*;
import svc.main.*;
import vo.*;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MainAction - execute");
		ActionForward forward = null;
		
		MainService service = new MainService();
		
		ArrayList<ReviewBoardDTO> reviewList = service.getBoardList();
		System.out.println(reviewList);
		request.setAttribute("reviewList", reviewList);
		
		forward = new ActionForward();
		forward.setPath("main.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
