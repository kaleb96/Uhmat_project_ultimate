package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.main.UhmatSearchAction;
import vo.ActionForward;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/")
public class MainController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getServletPath();
		System.out.println(command);
		ActionForward forward = null;
		Action action = null;
		
		
		// 사이드바 공지사항 이동
		if (command.equals("NoticeList.sc")) {
			forward = new ActionForward();
			forward.setPath("/serviceCenter/notice/noticelist.jsp");
			forward.setRedirect(false);
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

}
