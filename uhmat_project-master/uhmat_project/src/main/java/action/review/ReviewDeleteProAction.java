package action.review;


import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.*;

import action.Action;
import svc.*;
import svc.review.ReviewDeleteProService;
import vo.*;

public class ReviewDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		System.out.println("ReviewDeleteProAction");
		ActionForward forward = null;
		

		// 전달받은 파라미터 받기
		int idx = Integer.parseInt(request.getParameter("idx"));
		String fileName = request.getParameter("fileName");
		String filePath =  request.getServletContext().getRealPath("/upload");
		System.out.println(filePath);
		String pass = request.getParameter("pass");
		System.out.println(idx);
		System.out.println(pass);
		
		ReviewDeleteProService service = new ReviewDeleteProService();
		boolean isWriter = service.isWriter(pass);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(!isWriter) {

			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.!')");
			out.println("history.back()");
			out.println("</script>");
		
		} else {
			
			boolean isDeleteSuccess = service.isDeleteSuccess(idx, filePath, fileName);
			
			if(!isDeleteSuccess) {
				out.println("<script>");
				out.println("alert('삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
				
			} else {
				
				forward = new ActionForward();
//				forward.setPath("ReviewList.re");
				forward.setPath("ReviewList.re?pageNum=" + request.getParameter("pageNum")); 
//				페이징 처리시 필요
				forward.setRedirect(true);
			}
		}
		
		return forward;

	}

}
