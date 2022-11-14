package action.serviceCenter;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;

import action.Action;
import svc.serviceCenter.NoticeDeleteService;
import vo.ActionForward;

public class NoticeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		String real_File = request.getParameter("real_File");
		
		String path = request.getServletContext().getRealPath("/upload");
		File folder = new File(path);
		
		String filePath = request.getServletContext().getRealPath("/upload/" + real_File);
		File deleteFile = new File(filePath);
		
		NoticeDeleteService service = new NoticeDeleteService();
		boolean deleteSuccess = service.removeNotice(idx);
		
		if(!deleteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
			
		} else {
			if(deleteFile.exists()) {
				deleteFile.delete();
			}
			
			forward = new ActionForward();
			forward.setPath("NoticeList.sc");
			forward.setRedirect(true);
		}
		
		
		return forward;
	}

}
