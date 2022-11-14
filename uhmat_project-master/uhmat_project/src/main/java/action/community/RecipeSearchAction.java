package action.community;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.RecipeSearchService;
import vo.ActionForward;
import vo.PageInfo;
import vo.RecipeDTO;

public class RecipeSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("RecipeSearchAction");
		ActionForward forward = null;
		
		String keyword = request.getParameter("keyword");
		System.out.println("keyword : " + keyword);
		
		int pageNum = 1; // 현재페이지 번호
		int listLimit = 10; // 한 페이지 당 표시할 게시물 수
		int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 수

		// 단, URL 파라미터로 현재 페이지번호(pageNum) 가 전달됐을 경우 가져와서 변수에 저장
		if(request.getParameter("pageNum") != null){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		RecipeSearchService service = new RecipeSearchService();
		int listCount = service.recipeSearchListCount(keyword);
		System.out.println("listCount(action) : " + listCount);
		
		// 1. 현재 페이지에서 표시할 전체 페이지 수 계산
		int maxPage = (int)Math.ceil((double)listCount / listLimit);

		// 2. 현재 페이지에서 보여줄 시작페이지 번호(1, 11, 21, ...)  - 알면 좋음
		int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1 ) * pageLimit + 1;

		// 3. 현재 페이지에서 보여줄 끝 페이지 번호(10,20, 30, ~~~)
		int endPage = startPage + pageLimit - 1;
				
		// 4.
		if(endPage > maxPage){
			endPage = maxPage;
		}
				
		PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
		
		ArrayList<RecipeDTO> recipeSearchList = service.recipeSearchList(pageNum, listLimit, keyword);
		System.out.println("recipeSearchList : " + recipeSearchList);
		
		request.setAttribute("recipeSearchPageInfo", pageInfo);
		request.setAttribute("recipeSearchList", recipeSearchList);
		
		forward = new ActionForward();
		forward.setPath("community/recipe/recipe_list.jsp?keyword="+ keyword);
		forward.setRedirect(false);
		
		return forward;
	}

}
