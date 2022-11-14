package controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import action.*;
import action.serviceCenter.*;
import vo.*;

@WebServlet("*.sc")
public class ServiceCenterFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("ServiceCenterFrontController");
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		System.out.println("command : " + command );
		ActionForward forward = null;
		Action action = null;
		
		// --------------------- Notice -------------------------------------
		if (command.equals("/NoticeList.sc")) {
			try {
				action = new NoticeListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeWriteForm.sc")) {
			
			forward = new ActionForward();
			forward.setPath("serviceCenter/notice/noticeWriteForm.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/NoticeWritePro.sc")) {
			try {
				action = new NoticeWriteProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeDetail.sc")) {
			try {
				action = new NoticeDetailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeModifyForm.sc")) {
			try {
				action = new NoticeModifyFormAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeModify.sc")) {
			try {
				action = new NoticeModifyProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/NoticeDelete.sc")) {
				try {
					action = new NoticeDeleteAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			
				
		//------------------Notice 카테고리 -----------------------
			
		 } else if(command.equals("/NoticelistCategory.sc")) {
				try {
					action = new NoticelistCategoryAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
		// --------------------FAQ ------------------------------------
	    } else if (command.equals("/FAQList.sc")) {
			try {
				action = new FAQListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/FAQWriteForm.sc")) {
			forward = new ActionForward();
			forward.setPath("serviceCenter/faq/faqWriteForm.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/FAQWritePro.sc")) {
			try {
				action = new FAQWriteProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/FAQDetail.sc")) {
			try {
				action = new FAQDetailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/FAQModifyForm.sc")) {
			try {
				action = new FAQModifyFormAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/FAQModify.sc")) {
			try {
				action = new FAQModifyProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/FAQDelete.sc")) {
				try {
					action = new FAQDeleteAction();
					forward = action.execute(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		//------------FAQList에서 Category 선택했을 때--------------------------------------
		} else if(command.equals("/FAQlistCategory.sc")) {
			try {
				action = new FAQlistCategoryAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		
		//---------------------관리자가 답변-----------------------------------------------
		// 관리자 작성

		} else if(command.equals("/FAQDetailReply.sc")) {
			try {
				action = new FAQDetailReplyAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		// 관리자 삭제	
		} else if(command.equals("/FAQDetailReplyDelete.sc")) {
			try {
				action = new FAQDetailReplyDeleteAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//--------------------실시간 채팅-----------------------
		
		else if(command.equals("/LiveTalkList.sc")) {
			try {
				action = new LiveTalkAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if(command.equals("/LiveTalkPro.sc")) {
			try {
				action = new LiveTalkProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 서비스센터 메인 호출하는 서블릿
		
		else if(command.equals("/ServiceCenterMain.sc")) {
			try {
				action = new ServiceCenterMainAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		
		//--------------------------------------------------------------------
		
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
