package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import action.Action;
import action.member.MemberAuthAction;
import action.member.MemberBoardListAction;
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

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getServletPath();
		System.out.println(command);
		ActionForward forward = null;
		Action action = null;
	
		// 회원가입 폼
		if (command.equals("/MemberJoinForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/joinForm.jsp");
			forward.setRedirect(false);
		}
		// 회원가입 폼 (DB)처리
		else if (command.equals("/MemberJoinPro.me")) {
			try {
				action = new MemberJoinProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 회원가입 중 닉네임 중복처리
		else if (command.equals("/CheckDuplicateNickName.me")) {

			try {
				PrintWriter out = response.getWriter();
				boolean checkNick = true;
				try {
					MemberChechDuplicateNickNameAction nickNameCheckaction = new MemberChechDuplicateNickNameAction();
					checkNick = nickNameCheckaction.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (checkNick) {
					out.print("not-usable");
				} else {
					out.print("usable");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원가입 중 이메일 중복처리
		else if (command.equals("/CheckDuplicateEmail.me")) {
			boolean checkEmail = true;
			PrintWriter out = response.getWriter();
			try {
				MemberChechDuplicateEmailAction emailCheckaction = new MemberChechDuplicateEmailAction();
				checkEmail = emailCheckaction.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (checkEmail) {
				out.print("not-usable");
			} else {
				out.print("usable");
			}
		}
		// 회원가입 중 이메일 인증 보내기
		else if (command.equals("/SendAuthMail.me")) {
			try {
				action = new MemberSendAuthMailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 로그인폼
		else if (command.equals("/MemberLoginForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/loginForm.jsp");
			forward.setRedirect(false);
		}
		// 로그인시 처리
		else if (command.equals("/MemberLoginPro.me")) {
			try {
				action = new MemberLoginProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // 로그인시 이메일 인증 확인
		else if (command.equals("/MemberAuth.me")) {
			action = new MemberAuthAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 로그아웃 처리
		else if (command.equals("/MemberLogout.me")) {
			try {
				action = new MemberLogoutAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 간편로그인 중 구글 폼
		else if (command.equals("/MemberGoogleForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/login.jsp");
			forward.setRedirect(false);
		}
		// 간편로그인 중 구글 처리
		else if (command.equals("/MemberGoogleJoinPro.me")) {
			try {
				action = new MemberGoogleJoinProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 간편로그인 중 카카오 폼
		else if (command.equals("/MemberKakaoForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/kakao.jsp");
			forward.setRedirect(false);
		}
		// 간편로그인 중 카카오 처리
		else if (command.equals("/MemberKakaoJoinPro.me")) {
			try {
				action = new MemberKakaoJoinProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 간편로그인 중 네이버 폼
		else if (command.equals("/MemberNaverForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/naverlogin.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MemberLogin.me")) {
			forward = new ActionForward();
			forward.setPath("/member/login.jsp");
			forward.setRedirect(false);
		}
	
			// 패스워드 찾기 폼
		else if (command.equals("/MemberFindPasswordForm.me")) {
				forward = new ActionForward();
				forward.setPath("/member/findPassword.jsp");
				forward.setRedirect(false);
			}
			// 패스워드 찾기 처리
			else if (command.equals("/MemberFindPasswordPro.me")) {

				try {
					action = new MemberFindPasswordProAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			// 새로운 패스워드 메일 전송
			else if (command.equals("/SendPasswordMail.me")) {
				try {
					action = new MemberSendPasswordMailAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 비밀번호 변경 폼
			else if (command.equals("/MemberPasswordModifyForm.me")) {
				forward = new ActionForward();
				forward.setPath("/member/passwordModify.jsp");
				forward.setRedirect(false);
			}
			// 비밀번호 변경 처리
			else if (command.equals("/MemberPasswordModifyPro.me")) {
				try {
					action = new MemberPasswordModifyProAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		HttpSession session = request.getSession();
		if (session.getAttribute("sNickName") != null) {
			// 사용자 정보 폼
			 if (command.equals("/MemberDetailForm.me")) {

				try {
					action = new MemberDetailFormAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			// 사용자 정보 수정 처리
			else if (command.equals("/MemberDetailModifyPro.me")) {
				try {
					action = new MemberDetailModifyProAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/MemberDetailList.me")) {
				MemberDTO member = null;
				try {
					MemberDetailListAction list = new MemberDetailListAction();
					member = list.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JSONObject jobj = new JSONObject();
				List<MemberDTO> list = new ArrayList<MemberDTO>();
				list.add(member);
				String gson = new Gson().toJson(list);
				System.out.println(list);
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(gson);
			} else if (command.equals("/MemberBoardList.me")) {
				try {
					action = new MemberBoardListAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
