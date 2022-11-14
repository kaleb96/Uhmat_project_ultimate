package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import action.Action;
import action.member.MemberAuthAction;
import action.member.MemberChechDuplicateEmailAction;
import action.member.MemberChechDuplicateNickNameAction;
import action.member.MemberDetailFormAction;
import action.member.MemberDetailListAction;
import action.member.MemberDetailModifyProAction;
import action.member.MemberFindPasswordProAction;
import action.member.MemberGoogleJoinProAction;
import action.member.MemberJoinProAction;
import action.member.MemberKakaoJoinProAction;
import action.member.MemberLoginProAction;
import action.member.MemberLogoutAction;
import action.member.MemberPasswordModifyProAction;
import action.member.MemberSendAuthMailAction;
import action.member.MemberSendPasswordMailAction;
import vo.ActionForward;
import vo.MemberDTO;

@WebServlet("*.cp")
public class CompanyController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getServletPath();
		System.out.println(command);
		ActionForward forward = null;
		Action action = null;
		
		// 회사소개 
		if(command.equals("/CompanyDetail.cp")) {
			forward = new ActionForward();
			forward.setPath("/company/companyDetail.jsp");
			forward.setRedirect(false);	
		}
		
		// 어맛소개
		if(command.equals("/UhmatDetail.cp")) {
			forward = new ActionForward();
			forward.setPath("/company/uhmatDetail.jsp");
			forward.setRedirect(false);
		}
		
		// 개발자소개
		if(command.equals("/DeveloperDetail.cp")) {
			forward = new ActionForward();
			forward.setPath("/company/developerDetail.jsp");
			forward.setRedirect(false);
		}
		
		// 이용약관
		if(command.equals("/Policy.cp")) {
			forward = new ActionForward();
			forward.setPath("/company/policy.jsp");
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
