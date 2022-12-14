package action.community;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.RecipeDeleteService;
import vo.ActionForward;

public class RecipeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("RecipeDeleteAction");
		
		ActionForward forward = null;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String nickname = request.getParameter("nickname");
		System.out.println("idx : " + idx);
		
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
		String path = request.getServletContext().getRealPath("upload/recipe_upload");
		File folder = new File(path);
		System.out.println(path);
		
//		String filePath = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload\\" + file1;
		String filePath = request.getServletContext().getRealPath("upload/recipe_upload/" + file1);
		File deleteFile = new File(filePath);
		
//		String filePath2 = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload\\" + file2;
		String filePath2 = request.getServletContext().getRealPath("upload/recipe_upload/" + file2);
		File deleteFile2 = new File(filePath2);
		
//		String filePath3 = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload\\" + file3;
		String filePath3 = request.getServletContext().getRealPath("upload/recipe_upload/" + file3);
		File deleteFile3 = new File(filePath3);
		
//		String filePath4 = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload\\" + file4;
		String filePath4 = request.getServletContext().getRealPath("upload/recipe_upload/" + file4);
		File deleteFile4 = new File(filePath4);
		
//		String filePath5 = "D:\\workspace_jsp1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\uhmat_project\\recipe_upload\\" + file5;
		String filePath5 = request.getServletContext().getRealPath("upload/recipe_upload/" + file5);
		File deleteFile5 = new File(filePath5);
		
		// RecipeDeleteProService - isDeleteMate() ???????????? ???????????? ?????? ??????
		// => ???????????? : ?????????, ?????????    ???????????? : boolean(isDeleteSuccess)
		RecipeDeleteService service = new RecipeDeleteService();
		boolean isDeleteSuccess = service.deleteRecipe(idx, nickname);
		
		// ????????? ???????????? ????????? ??????
//		service.deleteRecipeReply(idx);
		
		if(!isDeleteSuccess) { // ????????? ?????? ??????
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('?????? ??????!')");
			out.println("history.back()");
			out.println("</script>");
		} else { // ????????? ?????? ?????? 
			
			if(deleteFile.exists()) {
				deleteFile.delete();
				System.out.println("??????1 ??????");
				
				if(deleteFile2.exists()) {
					deleteFile2.delete();
					System.out.println("??????2 ??????");
					
					if(deleteFile3.exists()) {
						deleteFile3.delete();
						System.out.println("??????3 ??????");
						
						if(deleteFile4.exists()) {
							deleteFile4.delete();
							System.out.println("??????4 ??????");
							
							if(deleteFile5.exists()) {
								deleteFile5.delete();
								System.out.println("??????5 ??????");
								
							} 
//							else {
//								System.out.println("??????5??? ???????????? ????????????");
//							}
							
						} 
//						else {
//							System.out.println("??????4??? ???????????? ????????????");
//						}
						
					} 
//					else {
//						System.out.println("??????3??? ???????????? ????????????");
//					}
					
				}
//				else {
//					System.out.println("??????2??? ???????????? ????????????");
//				}
				
				// recipe_upload??? ?????? ??????
				if(!folder.exists()) {
					folder.mkdir(); //????????? ????????????
					System.out.println("recipe_upload ????????? ?????????????????????");
				} else {
					System.out.println("?????? ?????? ???????????????");
				}
				
				forward = new ActionForward();
				forward.setPath("RecipeList.co?pageNum=" + request.getParameter("pageNum"));
				forward.setRedirect(true);
				
			} else {
				System.out.println("????????? ???????????? ????????????");
				
				
			}
				
				
			
			
		}
		return forward;
	}

}

