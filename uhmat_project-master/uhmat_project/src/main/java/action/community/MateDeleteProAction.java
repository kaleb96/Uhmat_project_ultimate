package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.MateDeleteProService;
import vo.ActionForward;

public class MateDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MateDeleteProAction");
		
		ActionForward forward = null;
		int idx = Integer.parseInt(request.getParameter("idx"));
		
//		System.out.println(idx);
		
		// MateDeleteProService - isDeleteMate() 메서드를 호출하여 삭제 요청
		// => 파라미터 : 글번호    리턴타입 : boolean(isDeleteSuccess)
		MateDeleteProService service = new MateDeleteProService();
		boolean isDeleteSuccess = service.deleteMate(idx);
		
		// 게시판 삭제하면 댓글도 삭제
		service.deleteMateReply(idx);
		
		if(!isDeleteSuccess) { // 게시글 삭제 실패
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else { // 게시글 삭제 성공 , 댓글 삭제 로직 구현
			
			// 게시물 삭제시 댓글까지 삭제
//			service = new MateDeleteProService();
//			boolean withDeleteReply = service.withDeleteReply(idx);
			
			
			forward = new ActionForward();
			forward.setPath("MateList.co?pageNum=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
			
		}
		
		
		return forward;
	}

}
