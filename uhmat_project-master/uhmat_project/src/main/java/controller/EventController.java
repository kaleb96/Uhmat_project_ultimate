package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;


@WebServlet("*.ev")
public class EventController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CommunityFrontController");
		
		// POST 방식 요청에 대한 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿 주소 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// Action 클래스 인스턴스들을 공통으로 관리하는 Action 타입 변수 선언
		Action action = null;
		// 포워딩 정보를 관리하는 ActionForward 타입 변수 선언
		ActionForward forward = null;
		
		if(command.equals("/Roulette.ev")) {
			forward = new ActionForward();
			forward.setPath("event/roulette/roulette.jsp");
			forward.setRedirect(false);
		}
		// ------------------------------------------------------------------
		// ActionFoward 객체에 저장된 포워딩 정보에 따른 포워딩 작업을 수행하기 위한 공통코드 작성
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doProcess(request, response);
	}
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doProcess(request, response);
	}

}
