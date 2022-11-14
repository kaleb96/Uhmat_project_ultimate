package action.community;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.community.RecipeDetailService;
import svc.community.RecipeModifyProService;
import vo.ActionForward;
import vo.RecipeDTO;

public class RecipeModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("레시피글 수정 작업 요청 액션 - RecipeModifyProAction");

		ActionForward forward = null;
		
		RecipeDTO recipe = new RecipeDTO();

		// -----------------------------------------------------------------

		// 파일 업로드 처리를 위해 MultipartRequest 객체 활용(cos.jar 라이브러리 필요)
		// 1. 업로드 파일 위치(이클립스 프로젝트 상의 경로) 저장
		String uploadPath = "upload/recipe_upload"; // 가상의 폴더명

		// 2. 업로드 파일 크기를 제한하기 위한 정수 형태의 값 지정(10MB 제한)
		int fileSize = 1024 * 1024 * 10; // byte(1) -> KB(1024Byte) -> MB(1024KB) -> 10MB 단위 변환

		// 3. 현재 프로젝트(서블릿)를 처리하는 객체인 서블릿 컨텍스트 객체 얻어오기
		ServletContext context = request.getServletContext();

		// 4. 업로드 파일이 저장되는 실제 경로를 얻어오기
		// => ServletContext 객체의 getRealPath() 메서드 호출
		String realPath = context.getRealPath(uploadPath); // 가상의 업로드 폴더명을 파라미터로 전달
//		System.out.println(realPath);
		// D:\workspace_uhmat\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\
		// uhmat_project\recipe_upload

		// 5. MultipartRequest 객체 생성
		// => 생성자 파라미터로 파일 업로드에 필요한 각종 파라미터를 전달
		MultipartRequest multi = new MultipartRequest(
				request, // 1) 실제 요청 정보가 포함된 request 객체
				realPath, // 2) 실제 업로드 폴더 경로
				fileSize, // 3) 업로드 파일 크기
				"UTF-8", // 4) 파일명에 대한 인코딩 방식(한글 처리 등이 필요하므로 UTF-8 지정)
				new DefaultFileRenamePolicy() // 5) 중복 파일명에 대한 처리를 담당하는 객체(파일명 뒤에 숫자 1 부터 차례대로 부여)
		);
		
		// 기존의 레시피의 글정보와 업로드된 파일 가져와서 filePat에 저장합니다.
		int idx = Integer.parseInt(multi.getParameter("idx"));
		String filePath = request.getServletContext().getRealPath("upload/recipe_upload");

		RecipeDetailService service = new RecipeDetailService();
		recipe = service.getRecipe(idx);
		
		
		// 체크박스의 체크가 되어 있다면 변경이 가능하게 하고, Path path를 통해 기존의 파일명과 수정할 파일명을 확인합니다.
		// 기존파일을 삭제 시키고, 바뀐 real_File, original_File을 가져옵니다.
		if (multi.getParameter("imgCheck1") != null) {
				System.out.println("imgCheck1 - 수정 ");
				try {
					Path path = Paths.get(filePath + "/" + recipe.getReal_File1());
					Files.deleteIfExists(path);
					System.out.println("file Delete is success");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					recipe.setOriginal_File1(multi.getOriginalFileName("file1"));
					recipe.setReal_File1(multi.getFilesystemName("file1"));
				}
		// 체크박스가 체크 되어 있지않다면, 수정을 하지 않는 작업이고
		// 원래 업로드한 파일을 그대로 사용합니다.
		// 파일이 있지않다면 null값으로 가져옵니다.
		} else {
			System.out.println("imgCheck1 - 수정 X");
			recipe.setOriginal_File1(recipe.getOriginal_File1());
			recipe.setReal_File1(recipe.getReal_File1());
		}
		
		// imgCheck1의 과정과 동일한 imgCheck2 확인 작업
		if (multi.getParameter("imgCheck2") != null) {
			System.out.println("imgCheck2 - 수정 ");
				try {
					Path path = Paths.get(filePath + "/" + recipe.getReal_File2());
					Files.deleteIfExists(path);
					 System.out.println("file Delete is success");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					recipe.setOriginal_File2(multi.getOriginalFileName("file2"));
					recipe.setReal_File2(multi.getFilesystemName("file2"));
				}

		} else {
			System.out.println("imgCheck2 - 수정 X");
			recipe.setOriginal_File2(recipe.getOriginal_File2());
			recipe.setReal_File2(recipe.getReal_File2());
		}

		// imgCheck1의 과정과 동일한 imgCheck3 확인 작업
		if (multi.getParameter("imgCheck3") != null) {
			System.out.println("imgCheck3 - 수정 ");
				try {
					Path path = Paths.get(filePath + "/" + recipe.getReal_File3());
					Files.deleteIfExists(path);
					System.out.println("fileDelete is success");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					recipe.setOriginal_File3(multi.getOriginalFileName("file3"));
					recipe.setReal_File3(multi.getFilesystemName("file3"));
				}
			
		} else {
			System.out.println("imgCheck3 - 수정 X");
			recipe.setOriginal_File3(recipe.getOriginal_File3());
			recipe.setReal_File3(recipe.getReal_File3());
		}

		// imgCheck1의 과정과 동일한 imgCheck4 확인 작업
		if (multi.getParameter("imgCheck4") != null) {
			System.out.println("imgCheck4 - 수정 ");
				try {
					Path path = Paths.get(filePath + "/" + recipe.getReal_File4());
					Files.deleteIfExists(path);
					System.out.println("fileDelete is success");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					recipe.setOriginal_File4(multi.getOriginalFileName("file4"));
					recipe.setReal_File4(multi.getFilesystemName("file4"));
				}
			
		} else {
			System.out.println("imgCheck4 - 수정 X");
			recipe.setOriginal_File4(recipe.getOriginal_File4());
			recipe.setReal_File4(recipe.getReal_File4());
		}
		
		// imgCheck1의 과정과 동일한 imgCheck5 확인 작업
		if (multi.getParameter("imgCheck5") != null) {
			System.out.println("imgCheck5 - 수정 ");
				try {
					Path path = Paths.get(filePath + "/" + recipe.getReal_File5());
					Files.deleteIfExists(path);
					System.out.println("fileDelete is success");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					recipe.setOriginal_File5(multi.getOriginalFileName("file5"));
					recipe.setReal_File5(multi.getFilesystemName("file5"));
				}
				
		} else {
			System.out.println("imgCheck5 - 수정 X");
			recipe.setOriginal_File5(recipe.getOriginal_File5());
			recipe.setReal_File5(recipe.getReal_File5());
		}

		// ----------------------------------------------------------------------------------------------

		recipe.setIdx(Integer.parseInt(multi.getParameter("idx")));
		recipe.setNickname(multi.getParameter("nickname"));
		recipe.setSubject(multi.getParameter("subject"));
		recipe.setContent(multi.getParameter("content"));

		System.out.println("레시피프로액션 - " + recipe);

		// ====================================================================

		RecipeModifyProService service2 = new RecipeModifyProService();
		boolean isModifySuccess = service2.modifyRecipe(recipe);

		if (!isModifySuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 수정 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("RecipeDetail.co?idx=" + Integer.parseInt(multi.getParameter("idx")) + "&pageNum="
					+ multi.getParameter("pageNum"));
			forward.setRedirect(true);
		}

		return forward;
	}

}
