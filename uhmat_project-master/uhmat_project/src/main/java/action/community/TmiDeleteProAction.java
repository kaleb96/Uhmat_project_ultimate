package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.TmiDeleteProService;
import vo.ActionForward;

public class TmiDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TmiDeleteProAction");
		
		ActionForward forward = null;
		
		// request 객체를 통해 전달받은 파라미터(idx, nickname) 가져오기
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// TmiDeleteProService - isTmiWriter() 메서드를 호출하여 삭제 권한 판별
		// => 파라미터 : 글번호, 닉네임		리턴타입 : boolean(isTmiWriter)
		// => 작업 내용은 TmiModifyService의 isTmiWriter()와 동일합니다.
		TmiDeleteProService service = new TmiDeleteProService();
		boolean isDeleteSuccess = service.deleteTmi(idx);
		
		// 게시판 삭제하면 댓글도 삭제
		service.deleteTmiBoard(idx);
		
		// 삭제 권한 판별 결과에 따른 작업 수행
		if(!isDeleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 불가능!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("TmiList.co?idx=" + idx + "&pageNum=" + request.getParameter("pageNum"));
			forward.setRedirect(true);
			
		}
		
		return forward;
	}

}
