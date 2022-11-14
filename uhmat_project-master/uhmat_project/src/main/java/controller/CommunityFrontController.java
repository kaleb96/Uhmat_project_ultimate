package controller;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.community.MateDeleteProAction;
import action.community.MateDetailAction;
import action.community.MateListAction;
import action.community.MateModifyFromAction;
import action.community.MateModifyProAction;
import action.community.MateReplyDeleteAction;
import action.community.MateReplyModifyAction;
import action.community.MateReplyWriteAction;
import action.community.MateRereplyFormAction;
import action.community.MateRereplyWriteAction;
import action.community.MateWriteProAction;
import action.community.RecipeDeleteAction;
import action.community.RecipeDetailAction;
import action.community.RecipeListAction;
import action.community.RecipeModifyFormAction;
import action.community.RecipeModifyProAction;
import action.community.RecipeReplyDeleteAction;
import action.community.RecipeReplyModifyAction;
import action.community.RecipeReplyWriteAction;
import action.community.RecipeRereplyFormAction;
import action.community.RecipeRereplyWriteAction;
import action.community.RecipeSearchAction;
import action.community.RecipeWriteProAction;
import action.community.TmiDeleteProAction;
import action.community.TmiDetailAction;
import action.community.TmiListAction;
import action.community.TmiModifyFormAction;
import action.community.TmiModifyProAction;
import action.community.TmiReplyDeleteAction;
import action.community.TmiReplyModifyFormAction;
import action.community.TmiReplyModifyProAction;
import action.community.TmiReplyWriteAction;
import action.community.TmiRereplyFormAction;
import action.community.TmiRereplyWriteProAction;
import action.community.TmiWriteProAction;
import vo.ActionForward;


// 어맛커뮤니티의 FrontController
/*
 * 1. 리스트
 * 2. 글 등록
 * 3. 글 상세페이지
 * 4. 글 수정
 * 5. 글 삭제
 * 6. 댓글
 * 7. 답글 (?)
 */

@WebServlet("*.co")

public class CommunityFrontController extends HttpServlet {
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
		

		// ----------------------------------------------------------------------------
		// 추출된 서블릿 주소를 if문을 사용하여 판별하고 각 주소에 따른 액션(작업) 요청
		// 리스트를 요청하는 서블릿(/MateListForm.co) 요청
		if(command.equals("/MateList.co")) {
			action = new MateListAction();

			try {
				action = new MateListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiListAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		// -----------------------------------------------------------------	
		// 글쓰기 폼을 요청하는 서블릿(/MateWriteForm.co) 요청
		} else if(command.equals("/MateWriteForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/mate/mate_write.jsp");
			forward.setRedirect(false); // Dispatcher 방식(생략 가능)
			

		} else if(command.equals("/MateWritePro.co")) {

			action = new MateWriteProAction();
			try {

				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateWriteProAction 오류 - " + e.getMessage());

				e.printStackTrace();

			}
		// ------------------------------------------------------------------
		// 글 디테일 뷰를 요청하는 서블릿(/MateDetail.co) 요청
		} else if(command.equals("/MateDetail.co")) {
			action = new MateDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateDetailAction 오류 - " + e.getMessage());

				e.printStackTrace();

			}
			
		// -----------------------------------------------------------------------
		// 글 수정에 필요한 게시물 조회 요청하는 서블릿(/MateModifyForm.co) 요청
		} else if(command.equals("/MateModifyForm.co")) {
			action = new MateModifyFromAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateModifyFromAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
					
		} else if(command.equals("/MateModifyPro.co")) {
			action = new MateModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateModifyProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		// --------------------------------------------------------------------------
		// 글 삭제 폼 이동
		} else if(command.equals("/MateDeleteForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/mate/mate_delete.jsp");
			forward.setRedirect(false);
			

		} else if(command.equals("/MateDeletePro.co")) {
			action = new MateDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateDeleteProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		// -----------------------------------------------------------------------------
		// 댓글 폼 요청
		} else if(command.equals("/MateReplyForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/mate/mate_replyForm.jsp");
			forward.setRedirect(false);
		// 댓글 쓰기
		} else if(command.equals("/MateReplyWrite.co")){
			action = new MateReplyWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("MateReplyProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		// ------------------------------------------------------
		// 댓글 리스트
		} else if(command.equals("/MateReplyList.co")) {
			action = new MateDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println(" MateDetailAction - reply 오류 - " + e.getMessage());
				e.printStackTrace();
			}	
		// ---------------------------------------------------------------	
		// 댓글 수정	
		} else if(command.equals("/MateReplyModifyForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/mate/mate_replyModify.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/MateReplyModifyPro.co")) {
			action = new MateReplyModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		// --------------------------------------------------------------------
		// 댓글 삭제
		} else if(command.equals("/MateReplyDeleteForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/mate/mate_replyDelete.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/MateReplyDeletePro.co")) {
			action = new MateReplyDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println(" MateReplyDeleteAction - reply 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		// ----------------------------------------------------------------------
		// 대댓글 글쓰기
		} else if(command.equals("/MateRereplyForm.co")) {
//				forward = new ActionForward();
//				forward.setPath("community/mate/mate_RereplyForm.jsp");
//				forward.setRedirect(false);
			action = new MateRereplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		} else if(command.equals("/MateRereplyWrite.co")) {
			action = new MateRereplyWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(" MateRereplyWriteAction 오류 - " + e.getMessage());
			}
		}
		//-------------------------------------------------
		// Tmi 서블릿 요청 시작
		 else if (command.equals("/TmiList.co")) {
			System.out.println("---------------------------------------------");
			System.out.println("게시물 전체목록과 검색기능 조회");
			try {
				action = new TmiListAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiListAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		} else if(command.equals("/TmiWriteForm.co")) {
			System.out.println("---------------------------------------------");
			System.out.println("게시물 작성 작업");
			forward = new ActionForward();
			forward.setPath("community/tmi/tmiWrite.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/TmiWritePro.co")) {
			System.out.println("---------------------------------------------");
			System.out.println("게시물 작성 요청");
			try {
				action = new TmiWriteProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiWriteProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		} else if(command.equals("/TmiDetail.co")) {
			System.out.println("---------------------------------------------");
			System.out.println("게시물 상세 내용 조회");
			try {
				action = new TmiDetailAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiDetailAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		} else if(command.equals("/TmiModifyForm.co")) {
			System.out.println("게시물 수정 조회");

			try {
				action = new TmiModifyFormAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiModifyFormAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}

			
		} else if(command.equals("/TmiModifyPro.co")) {
			System.out.println("게시글 수정 요청");

			try {
				action = new TmiModifyProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiModifyProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
			
		} else if(command.equals("/TmiDeleteForm.co")) {
			System.out.println("게시물 삭제 조회");
			forward = new ActionForward();
			forward.setPath("community/tmi/tmiDelete.jsp");
			forward.setRedirect(false);
			
		}  else if(command.equals("/TmiDeletePro.co")) {
			System.out.println("게시물 삭제 요청");
			try {
				action = new TmiDeleteProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiDeleteProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			} 
		// 댓글 폼 요청
		} else if(command.equals("/TmiReplyForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/tmi/tmiReplyForm.jsp");
			forward.setRedirect(false);
		// 댓글 쓰기
		} else if(command.equals("/TmiReplyWrite.co")) {
			System.out.println("댓글 작성 작업");
			try {
				action = new TmiReplyWriteAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiReplyProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		} 
		
//		else if(command.equals("/TmiReplyList.co")) {
//			System.out.println("댓글 리스트 조회");
//			
//			// Tmi 상세 내용 페이지에서 댓글 리스트 작업 처리
//			try {
//				action = new TmiDetailAction();
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				System.out.println("TmiDetailAction(댓글리스트) 오류 - " + e.getMessage());
//				e.printStackTrace();
//			}
//		} 
		
		else if(command.equals("/TmiReplyModifyForm.co")) {
			System.out.println("댓글 수정 작업");
			try {
				action = new TmiReplyModifyFormAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiReplyModifyFormAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		}
		else if(command.equals("/TmiReplyModifyPro.co")) {
			System.out.println("댓글 수정 요청 작업");
			try {
				action = new TmiReplyModifyProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("TmiReplyModifyProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		} else if(command.equals("/TmiReplyDeleteForm.co")) {
			System.out.println("댓글 삭제 작업");
			forward = new ActionForward();
			forward.setPath("/community/tmi/tmiReplyDelete.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/TmiReplyDeletePro.co")) {
			System.out.println("댓글 삭제 요청 작업");
			try {
				action = new TmiReplyDeleteAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("TmiReplyDeleteAction 오류 - " + e.getMessage());
			}
		} else if(command.equals("/TmiRereplyWriteForm.co")) {
			System.out.println("답글 작성 작업");
			try {
				action = new TmiRereplyFormAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("TmiRereplyWriteForm 오류 - " + e.getMessage());
			}
		} else if(command.equals("/TmiRereplyWritePro.co")) {
			System.out.println("답글 작성 작업 요청");
			try {
				action = new TmiRereplyWriteProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("TmiRereplyWriteProAction 오류 - " + e.getMessage());
			}
//		} 
		
//		else if(command.equals("/TmiRereplyList.co")) {
//			System.out.println("답글 리스트 조회");
//			
//			// Tmi 상세 내용 페이지에서 답글 리스트 작업 처리
//			try {
//				action = new TmiDetailAction();
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				System.out.println("TmiDetailAction(답글리스트) 오류 - " + e.getMessage());
//				e.printStackTrace();
//			}
		// ==============================================================================================
			
		// 레시피 시작
		// 레시피 글쓰기
		} else if(command.equals("/RecipeWriteForm.co")) {
			forward = new ActionForward();
			forward.setPath("community/recipe/recipe_write.jsp");
			forward.setRedirect(false); // Dispatcher 방식(생략 가능)
			
		} else if(command.equals("/RecipeWritePro.co")) {
			action = new RecipeWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("RecipeWriteProAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		// ----------------------------------------------------------------------------
		// 레시피 리스트
		} else if(command.equals("/RecipeList.co")) {
			action = new RecipeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeListAction 오류 - " + e.getMessage());
			}
		// -----------------------------------------------------------------------------
		// 레시피 상세페이지
		} else if(command.equals("/RecipeDetail.co")) {
			action = new RecipeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeDetailAction 오류 - " + e.getMessage());
			}
		} else if(command.equals("/RecipeModifyForm.co")) {
			System.out.println("레시피글 수정 작업");
			try {
				action = new RecipeModifyFormAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeModifyFormAction 오류 - " + e.getMessage());
			}
		} else if(command.equals("/RecipeModifyPro.co")) {
			System.out.println("레시피글 수정 작업 요청");
			try {
				action = new RecipeModifyProAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeModifyProAction 오류 - " + e.getMessage());
			}
			
		} else if(command.equals("/RecipeDeleteForm.co")){
			System.out.println("레시피글 삭제 작업");
			forward = new ActionForward();
			forward.setPath("community/recipe/recipe_delete.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/RecipeDeletePro.co")) {
			System.out.println("레시피글 삭제 작업 요청");
			action = new RecipeDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeDeleteAction 오류 - " + e.getMessage());
			}
		// -------------------------------------------------------------------------------
		// 레시피 댓글
		} else if(command.equals("/RecipeReplyForm.co")) {
			System.out.println(request.getParameter("레시피댓글 작성 작업"));
			forward = new ActionForward();
			forward.setPath("community/recipe/recipe_replyForm.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/RecipeReplyWrite.co")){
			System.out.println(request.getParameter("레시피댓글 작성 작업 요청"));
			action = new RecipeReplyWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("RecipeReplyWriteAction 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		}
		// ------------------------------------------------------
		// 레시피 댓글 리스트
		else if(command.equals("/RecipeReplyList.co")) {
			System.out.println(request.getParameter("레시피댓글 리스트 작업"));
			action = new RecipeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println(" RecipeDetailAction - reply 오류 - " + e.getMessage());
				e.printStackTrace();
			}
		}
		// ---------------------------------------------------------------	
		// 레시피 댓글 수정	
		else if(command.equals("/RecipeReplyModifyForm.co")) {
			System.out.println(request.getParameter("레시피댓글 수정 작업"));
			forward = new ActionForward();
			forward.setPath("community/recipe/recipe_replyModify.jsp");
			forward.setRedirect(false);
			
		} else if(command.equals("/RecipeReplyModifyPro.co")) {
			action = new RecipeReplyModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeReplyModifyAction - reply 오류 - " + e.getMessage());
			}
		// --------------------------------------------------------------------
		// 레시피 댓글 삭제
		} else if(command.equals("/RecipeReplyDeleteForm.co")) {
			System.out.println("레시피댓글 삭제 작업");
			forward = new ActionForward();
			forward.setPath("/community/recipe/recipe_replyDelete.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/RecipeReplyDeletePro.co")) {
			System.out.println("레시피댓글 삭제 요청 작업");
			try {
				action = new RecipeReplyDeleteAction();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeReplyDeleteAction 오류 - " + e.getMessage());
			}
		} else if(command.equals("/RecipeRereplyForm.co")) {
			System.out.println("레시피 대댓글 작성 작업");
			action = new RecipeRereplyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(command.equals("/RecipeRereplyWrite.co")) {
			System.out.println("레시피 대댓글 작성 작업 요청");
			action = new RecipeRereplyWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeRereplyWriteAction 오류 - " + e.getMessage());
			}
		// ------------------------------------------------------------------------------
		// 레시피 검색	
		} else if(command.equals("/RecipeSearch.co")) {
			action = new RecipeSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("RecipeSearchAction 오류 - " + e.getMessage());
			}
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

