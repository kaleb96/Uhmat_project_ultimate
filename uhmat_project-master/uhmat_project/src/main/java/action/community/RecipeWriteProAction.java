package action.community;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.community.RecipeWriteProService;
import vo.ActionForward;
import vo.RecipeDTO;

public class RecipeWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("RecipeWriteProAction");
		
		ActionForward forward = null;
		
		// 파일 업로드 처리를 위해 MultipartRequest 객체 활용(cos.jar 라이브러리 필요)
		// 1. 업로드 파일 위치(이클립스 프로젝트 상의 경로) 저장
		String uploadPath = "upload/recipe_upload"; // 가상의 폴더명
		
		// 2. 업로드 파일 크기를 제한하기 위한 정수 형태의 값 지정(10MB 제한)
		int fileSize = 1024 * 1024 * 10*7; // byte(1) -> KB(1024Byte) -> MB(1024KB) -> 10MB 단위 변환
				
		// 3. 현재 프로젝트(서블릿)를 처리하는 객체인 서블릿 컨텍스트 객체 얻어오기
		ServletContext context = request.getServletContext();
				
		// 4. 업로드 파일이 저장되는 실제 경로를 얻어오기
		// => ServletContext 객체의 getRealPath() 메서드 호출
		String realPath = context.getRealPath(uploadPath); // 가상의 업로드 폴더명을 파라미터로 전달
//		System.out.println(realPath);
		//D:\workspace_jsp1\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\ uhmat_project\recipe_upload
		
		// 5. MultipartRequest 객체 생성
		// => 생성자 파라미터로 파일 업로드에 필요한 각종 파라미터를 전달
		MultipartRequest multi = new MultipartRequest(
			request, // 1) 실제 요청 정보가 포함된 request 객체
			realPath, // 2) 실제 업로드 폴더 경로
			fileSize, // 3) 업로드 파일 크기
			"UTF-8", // 4) 파일명에 대한 인코딩 방식(한글 처리 등이 필요하므로 UTF-8 지정)
			new DefaultFileRenamePolicy() // 5) 중복 파일명에 대한 처리를 담당하는 객체(파일명 뒤에 숫자 1 부터 차례대로 부여)
		);
		// => 객체 생성되는 시점에 이미 업로드 파일은 실제 폴더에 업로드 처리 완료됨
		
		// 6. MultipartRequest 객체의 getParameter() 메서드를 호출하여
		//    폼 파라미터 데이터 가져와서 RecipeDTO 객체(recipe)에 저장
		RecipeDTO recipe = new RecipeDTO();
		recipe.setNickname(multi.getParameter("nickname"));
		recipe.setSubject(multi.getParameter("subject"));
		recipe.setContent(multi.getParameter("content"));
		recipe.setOriginal_File1(multi.getOriginalFileName("file1"));
		recipe.setReal_File1(multi.getFilesystemName("file1"));
		recipe.setOriginal_File2(multi.getOriginalFileName("file2"));
		recipe.setReal_File2(multi.getFilesystemName("file2"));
		recipe.setOriginal_File3(multi.getOriginalFileName("file3"));
		recipe.setReal_File3(multi.getFilesystemName("file3"));
		recipe.setOriginal_File4(multi.getOriginalFileName("file4"));
		recipe.setReal_File4(multi.getFilesystemName("file4"));
		recipe.setOriginal_File5(multi.getOriginalFileName("file5"));
		recipe.setReal_File5(multi.getFilesystemName("file5"));
		
//		System.out.println(multi.getOriginalFileName("file2") + " / " + multi.getFilesystemName("file2"));
		
		// --------------------------------------------------------------
		// 실제 비즈니스 작업 요청을 수행할 RecipeWriteProService 클래스의 인스턴스 생성 후
		// registRecipe() 메서드를 호출하여 글쓰기 작업 요청
		// => 파라미터 : RecipeDTO 객체   리턴타입 : boolean(isWriteSuccess)
		RecipeWriteProService service = new RecipeWriteProService();
		boolean isWriteSuccess = service.registRecipe(recipe);
		
		// Service 클래스로부터 글쓰기 작업 요청 처리 결과를 전달받아 성공/실패 여부 판별
		if(!isWriteSuccess) { // 글쓰기 실패 시
					
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 쓰기 실패!')");
			out.println("history.back()");
			out.println("</script>");
					
		} else { // 글쓰기 성공 시
			// 글목록 조회 비즈니스 로직을 수행하기 위한 RecipeList.co 서블릿 주소 요청
			// => 새로운 서비스에 대한 요청이므로 Redirect 방식 포워딩
			forward = new ActionForward();
			forward.setPath("RecipeList.co");
			forward.setRedirect(true);
		}
		
		return forward;
	}
	
	

}
