package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.main.ReviewBestAction;
import action.main.ReviewRecentAction;
import action.main.UhmatSearchAction;
import vo.ActionForward;

/**
 * Servlet implementation class MainFrontController
 */
@WebServlet("*.ma")
public class MainFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getServletPath();
		System.out.println(command);
		ActionForward forward = null;
		Action action = null;
		// 최다 좋아요 리뷰
		if(command.equals("/ReviewBest.ma")) {
			try {
				action = new ReviewBestAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/ReviewRecent.ma")) {
			action = new ReviewRecentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}
	
	}
	
				
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
