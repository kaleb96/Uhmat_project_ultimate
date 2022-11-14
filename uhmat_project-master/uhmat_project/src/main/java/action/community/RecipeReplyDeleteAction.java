package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.RecipeReplyDeleteProService;
import vo.ActionForward;

public class RecipeReplyDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("RecipeReplyDeleteAction");
		ActionForward forward = null;
		
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int reply_idx= Integer.parseInt(request.getParameter("reply_idx"));
		String nickname = request.getParameter("nickname");
//		System.out.println(idx);
//		System.out.println(board_idx);
//		System.out.println(nickname);
		
		// TmiReplyDeleteProService - isDeleteTmiReply() 메서드를 호출하여 삭제를 요청
		RecipeReplyDeleteProService service = new RecipeReplyDeleteProService();
		boolean isRecipeReplyDeleteSuccess = service.isDeleteRecipeReply(reply_idx, nickname);
		System.out.println("RecipeReplyDeleteAction - " + isRecipeReplyDeleteSuccess);
		
		if(!isRecipeReplyDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("RecipeDetail.co?idx=" + request.getParameter("idx") + "&pageNum=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
		}
		
		
		return forward;
	}

}
