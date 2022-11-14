package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.RecipeReplyWriteService;
import vo.ActionForward;
import vo.RecipeReplyDTO;

public class RecipeReplyWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("RecipeReplyWriteAction");
		
		ActionForward forward = null;
		
		RecipeReplyDTO recipeReply = new RecipeReplyDTO();
		 int idx = Integer.parseInt(request.getParameter("idx"));
//		 String nickname = request.getParameter("nickname");
		 recipeReply.setIdx(Integer.parseInt(request.getParameter("idx")));
		 recipeReply.setNickname(request.getParameter("nickname"));
		 recipeReply.setContent(request.getParameter("content"));
		 recipeReply.setBoard_idx(idx);
		 System.out.println(recipeReply);
		 
		//댓글쓰기
	    // RecipeReplyWriteService 의 replyRecip() 메서드를 호출하여 답글 등록 작업 요청
	    // => 파라미터 : RecipeReplyDTO 객체   리턴타입 : boolean(isReplySuccess)
	    RecipeReplyWriteService service = new RecipeReplyWriteService();
	    boolean isReplySuccess = service.replyRecipe(recipeReply);
	    
	 // 답글 등록 작업 요청 처리 결과 판별
	      if(!isReplySuccess) {
	         response.setContentType("text/html; charset=UTF-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>");
	         out.println("alert('댓글 등록 실패!')");
	         out.println("history.back()");
	         out.println("</script>");
	      } else {
	         forward = new ActionForward();
	         forward.setPath("RecipeDetail.co?idx="+ idx +"&pageNum=" +request.getParameter("pageNum"));
	         forward.setRedirect(true);
	      }
		 
		return forward;
	}

}
