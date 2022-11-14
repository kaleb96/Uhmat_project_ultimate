package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.RecipeRereplyWriteService;
import vo.ActionForward;
import vo.RecipeReplyDTO;

public class RecipeRereplyWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("RecipeRereplyWriteAction");
		
		ActionForward forward = null;
		
		RecipeReplyDTO recipeRereply = new RecipeReplyDTO();
		int reply_idx = Integer.parseInt(request.getParameter("idx"));
		System.out.println("reply_idx - " + reply_idx);
		recipeRereply.setBoard_idx(Integer.parseInt(request.getParameter("board_idx")));
		recipeRereply.setContent(request.getParameter("content"));
		recipeRereply.setNickname(request.getParameter("nickname"));
//		System.out.println(request.getParameter("re_lev"));
//		System.out.println(request.getParameter("re_seq"));
		recipeRereply.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		recipeRereply.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		recipeRereply.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		System.out.println("recipeRereply - " + recipeRereply);
		
		RecipeRereplyWriteService service = new RecipeRereplyWriteService();
		boolean isRecipeRereplySuccess = service.writeRecipeRereply(recipeRereply);
		
		if(!isRecipeRereplySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답글 작성 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("RecipeDetail.co?idx=" + request.getParameter("board_idx") + "&pageNum=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
