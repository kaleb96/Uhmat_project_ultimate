package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import action.Action;
import action.admin.AdminDeleteMemberAction;
import action.admin.AdminManageMemberDetailAction;
import action.admin.AdminManageMemberListAction;
import action.admin.AllBoardListAction;
import action.admin.adminFAQDetailAction;
import action.admin.adminMateDetailAction;
import action.admin.adminNoticeDetailAction;
import action.admin.adminRecipeDetailAction;
import action.admin.adminTmiDetailAction;
import action.admin.allBoardDeleteAction;
import vo.ActionForward;

@WebServlet("*.ad")
public class AdminFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// POST 방식 요청에 대한 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// 서블릿 주소 추출
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		// Action 클래스 인스턴스들을 공통으로 관리하는 Action 타입 변수 선언
		Action action = null;
		// 포워딩 정보를 관리하는 ActionForward 타입 변수 선언
		ActionForward forward = null;
		
		if (command.equals("/AdminMain.ad")) {
			forward = new ActionForward();
			forward.setPath("admin/adminView.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/Admin.ad")) {
			forward = new ActionForward();
			forward.setPath("admin/adminTop.jsp");
			forward.setRedirect(false);
		
		} else if(command.equals("/AdminManageMemberList.ad")) {
			try {
				action = new AdminManageMemberListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/AdminManageMemberDetail.ad")) {
			try {
				action = new AdminManageMemberDetailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} else if(command.equals("/AdminDeleteMember.ad")) {
			try {
				action = new AdminDeleteMemberAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//-------------------------------------------------------------------
		else if (command.equals("/AllBoardListForm.ad")) {
			try {
				action = new AllBoardListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 회원가입 폼 (DB)처리
		else if (command.equals("/AllBoardList.ad")) {
			try {
				action = new AllBoardListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String gson = new Gson().toJson(request.getAttribute("list"));
			System.out.println(request.getAttribute("list"));
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(gson);
		}else if(command.equals("/MateDetail.ad")) {
			action = new adminMateDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateDetailAction 오류 - " + e.getMessage());

				e.printStackTrace();

			}
		}else if(command.equals("/TmiDetail.ad")) {
			System.out.println("---------------------------------------------");
			System.out.println("게시물 상세 내용 조회");
			try {
				action = new adminTmiDetailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiDetailAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		}else if(command.equals("/RecipeDetail.ad")) {
			action = new adminRecipeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeDetailAction 오류 - " + e.getMessage());
			}
		}else if(command.equals("/NoticeDetail.ad")) {
			try {
				action = new adminNoticeDetailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/FAQDetail.ad")) { 
			try {
				action = new adminFAQDetailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/AllBoardDelete.ad")) { 
			try {
				action = new allBoardDeleteAction();
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
