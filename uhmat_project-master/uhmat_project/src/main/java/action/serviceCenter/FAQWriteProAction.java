package action.serviceCenter;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.serviceCenter.FAQWriteProService;
import vo.ActionForward;
import vo.FAQDTO;

public class FAQWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FAQWriteProAction - execute");
		ActionForward forward = null;
		
		String uploadPath = "upload";
		
		// 업로드 파일 크기를 제한하기 위한 정수 형태의 값 지정 (10MB 제한)
		int fileSize = 1024 * 1024 * 10 ; //
		
		// 현재 프로젝트(서블릿)를 처리하는 객체인 서블릿 컨텍스트 객체 얻어오기 
		ServletContext context = request.getServletContext();
		
		// 업로드 파일이 저장되는 실제 경로를 얻어오기
		// => ServletContext 객체의 getRealPath() 메서드 호출
		String realPath = context.getRealPath(uploadPath); // 가상의 업로드 폴더명을 파라미터로 전달
//		System.out.println(realPath);
		// 실제 업로드 될 폴더 위치 ( 주의! 워크스페이스 내의 프로젝트 폴더에 있는 upload 폴더는 가상의 폴더)
		
		// MutlipartRequest 객체 생성
		// => 생성자 파라미터로 파일 업로드에 필요한 각종 파라미터를 전달
		MultipartRequest multi = new MultipartRequest(
				request, // 1) 실제 요청 정보가 포함된 request 객체
				realPath, // 2) 실제 업로드 폴더 경로
				fileSize, // 3) 업로드 파일 크기
				"UTF-8", // 4) 파일명에 대한 인코딩 방식(한글 처리 등이 필요하므로 UTF-8 지정)
				new DefaultFileRenamePolicy() // 5) 중복 파일명에 대한 처리를 담당하는 객체(파일명 뒤에 숫자 1부터 차례대로 부여)
		);
		
		// NoticeDTO에 가져온 값을 저장
		FAQDTO faq = new FAQDTO();
		faq.setNickname(multi.getParameter("nickname"));
		faq.setSubject(multi.getParameter("subject"));
		faq.setContent(multi.getParameter("content"));
		faq.setOriginal_File(multi.getOriginalFileName("file"));
		faq.setReal_File(multi.getFilesystemName("file"));
		faq.setCategory(multi.getParameter("category"));
//		System.out.println("faq : " + faq);
		
		FAQWriteProService service = new FAQWriteProService();
		
		boolean isWriteSuccess = service.registFAQ(faq);
		
		if(!isWriteSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 쓰기 실패')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("FAQList.sc");
			forward.setRedirect(true);
		}
		return forward;
	}

}
