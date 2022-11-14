package action.community;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.RecipeDetailService;
import vo.ActionForward;
import vo.RecipeDTO;

public class RecipeModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("레시피수정폼액션 - RecipeModifyFormAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));

		// 레시피글 수정에 필요한 게시물 상세 정보 조회
		// 레시피글 상세 정보를 리턴받아 recipe_modify.jsp 페이지로 포워딩
		RecipeDetailService service = new RecipeDetailService();
		RecipeDTO recipe = service.getRecipe(idx);
		
		request.setAttribute("recipe", recipe);

		forward = new ActionForward();
		forward.setPath("community/recipe/recipe_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
