package action.community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.RecipeRereplyFormService;
import vo.ActionForward;
import vo.RecipeReplyDTO;

public class RecipeRereplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("RecipeRereplyFormAction");
		
		ActionForward forward = null;
		
		RecipeRereplyFormService service = new RecipeRereplyFormService();
		RecipeReplyDTO recipeRereply = service.selectReply(Integer.parseInt(request.getParameter("reply_idx")));
		
		request.setAttribute("recipeRereply", recipeRereply);
		
		forward = new ActionForward();
		forward.setPath("community/recipe/recipe_RereplyForm.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
