package action.review;


import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import svc.review.ReviewModifyProService;
import vo.ActionForward;
import vo.ReviewBoardDTO;


public class ReviewModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("ReviewModifyProAction");
		ActionForward forward = null;
		
		
		/* 
		 * 글 수정 방법
		 * 1. 로그인 세션 값을 받는다.
		 * 2. 회원 닉네임/이메일 값에 등록된 비밀번호와 일치하는 비밀번호를 찾는다.
		 * 3. 비밀번호가 일치할경우 -> 글 수정을 진행
		 * 4. 비밀번호가 일치하지 않을 경우 -> alert("비밀번호가 일치하지 않습니다.")
		 * 5. 
		 */

		// 1. 업로드 파일 위치(이클립스 프로젝트 상의 경로) 저장
		String uploadPath = "upload"; // 가상의 폴더명
				
		// 2. 업로드 파일 크기를 제한하기 위한 정수 형태의 값 지정(10MB 제한)
		int fileSize = 1024 * 1024 * 10*2; // byte(1) -> KB(1024Byte) -> MB(1024KB) -> 10MB 단위 변환
				
		// 3. 현재 프로젝트(서블릿)를 처리하는 객체인 서블릿 컨텍스트 객체 얻어오기
		ServletContext context = request.getServletContext();
				
		// 4. 업로드 파일이 저장되는 실제 경로를 얻어오기
		String realPath = context.getRealPath(uploadPath); // 가상의 업로드 폴더명을 파라미터로 전달

		// 5. MultipartRequest 객체 생성
		MultipartRequest multi = new MultipartRequest(
					request, // 1) 실제 요청 정보가 포함된 request 객체
					realPath, // 2) 실제 업로드 폴더 경로
					fileSize, // 3) 업로드 파일 크기
					"UTF-8", // 4) 파일명에 대한 인코딩 방식(한글 처리 등이 필요하므로 UTF-8 지정)
					new DefaultFileRenamePolicy() // 5) 중복 파일명에 대한 처리를 담당하는 객체(파일명 뒤에 숫자 1 부터 차례대로 부여)
				);
		

		String originPath = multi.getParameter("path");

		ReviewBoardDTO dto = new ReviewBoardDTO();
		dto.setIdx(Integer.parseInt(multi.getParameter("idx")));
		dto.setNickname(multi.getParameter("nickname"));
		dto.setSubject(multi.getParameter("subject"));
		dto.setRes_name(multi.getParameter("res_name"));
		dto.setRating(Float.parseFloat(multi.getParameter("rating")));
		dto.setContent(multi.getParameter("content"));

//		dto.setPhoto(multi.getOriginalFileName("photo"));
		dto.setPhoto(multi.getFilesystemName("photo"));
		int idx = dto.getIdx();
		String tag = multi.getParameter("tag");
		ReviewModifyProService service = new ReviewModifyProService();
		boolean isModifySuccess = service.modifyReview(dto, originPath, realPath);
		boolean isTagWrite = service.registTag(idx, tag);
		
		// 글 수정 작업 결과 판별
		// 실패 시 자바스크립트를 사용하여 "글 수정 실패!" 출력 후 이전페이지로 돌아가기
		// 성공 시 ActionForward 객체 생성하여 BoardDetail.bo 페이지 요청
		// => 파라미터 : 글번호, 페이지번호
		if(!(isModifySuccess && isTagWrite)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 수정 실패!')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();

//			forward.setPath("ReviewDetail.re?idx=" + dto.getIdx());
			forward.setPath("ReviewDetail.re?idx=" + dto.getIdx() + "&pageNum=" + multi.getParameter("pageNum"));

			forward.setRedirect(true);
		}
		return forward;
	}
}


