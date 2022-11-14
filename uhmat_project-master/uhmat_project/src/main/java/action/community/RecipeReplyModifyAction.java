package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.RecipeReplyModifyProService;
import vo.ActionForward;

public class RecipeReplyModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("RecipeReplyModifyAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int reply_idx = Integer.parseInt(request.getParameter("reply_idx"));
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		System.out.println("reply_idx - " + reply_idx + ", nickname - " + nickname);
		
		RecipeReplyModifyProService service = new RecipeReplyModifyProService();
		boolean isModifySuccess = service.isModifyReplyRecipe(reply_idx, nickname, content);
		
		if(!isModifySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 수정 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("RecipeDetail.co?pageNum=" + request.getParameter("pageNum") + "&idx=" + idx);
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
