package action.admin;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.allBoardDeleteService;
import vo.ActionForward;

public class allBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		int idx = Integer.parseInt(request.getParameter("idx"));
		String title= request.getParameter("title");
		
		
		if(title== "Recipe") {
		String file1 = request.getParameter("file1");
		System.out.println("file1 : " + file1);
		String file2 = request.getParameter("file2");
		System.out.println("file2 : " + file2);
		String file3 = request.getParameter("file3");
		System.out.println("file3 : " + file3);
		String file4 = request.getParameter("file4");
		System.out.println("file4 : " + file4);
		String file5 = request.getParameter("file5");
		System.out.println("file5 : " + file5);
//		String path = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload";
		String path = request.getServletContext().getRealPath("./recipe_upload");
		File folder = new File(path);
		System.out.println(path);
		
//		String filePath = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload\\" + file1;
		String filePath = request.getServletContext().getRealPath("./recipe_upload/" + file1);
		File deleteFile = new File(filePath);
		
//		String filePath2 = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload\\" + file2;
		String filePath2 = request.getServletContext().getRealPath("./recipe_upload/" + file2);
		File deleteFile2 = new File(filePath2);
		
//		String filePath3 = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload\\" + file3;
		String filePath3 = request.getServletContext().getRealPath("./recipe_upload/" + file3);
		File deleteFile3 = new File(filePath3);
		
//		String filePath4 = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload\\" + file4;
		String filePath4 = request.getServletContext().getRealPath("./recipe_upload/" + file4);
		File deleteFile4 = new File(filePath4);
		
//		String filePath5 = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload\\" + file5;
		String filePath5 = request.getServletContext().getRealPath("./recipe_upload/" + file5);
		File deleteFile5 = new File(filePath5);
		}
		
		
		allBoardDeleteService service=new allBoardDeleteService();
		boolean isDeleteSuccess = service.deleteBoard(idx,title);
		
		
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
			forward.setPath("AllBoardList.ad?pageNum=" + request.getParameter("pageNum")+"&title="+title);
			forward.setRedirect(true);
			
		}
		
		
		return forward;
	}

}
