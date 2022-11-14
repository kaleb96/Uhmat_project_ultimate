package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.MateWriteProService;
import vo.ActionForward;
import vo.MateDTO;

public class MateWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MateWriteProAction");
//		System.out.println("깃 커밋 안 올라가져서 테스트");
		
		// 포워딩 정보를 저장하는 ActionForward 타입 변수 선언
		ActionForward forward = null;
		
		MateDTO mate = new MateDTO();
//		mate.setIdx(Integer.parseInt(request.getParameter("idx")));
		mate.setNickname(request.getParameter("nickname"));
		mate.setSubject(request.getParameter("subject"));
		mate.setContent(request.getParameter("content"));
		System.out.println(mate);
		
		
		MateWriteProService service = new MateWriteProService();
		// 실제 비즈니스 작업 요청을 수행할 MateWriteProService 클래스의 인스턴스 생성 후
		// registMate() 메서드를 호출하여 글쓰기 작업 요청
		// => 파라미터 : MateDTO 객체   리턴타입 : boolean(isWriteSuccess)
		boolean isWriteSuccess = service.registMate(mate);
		
		// Service 클래스로부터 글쓰기 작업 요청 처리 결과를 전달받아 성공/실패 여부 판별
		if(!isWriteSuccess) { // 글쓰기 실패 시
			// 자바스크립트를 통해 "글쓰기 실패!" 를 출력 후 이전페이지로 돌아가기
			response.setContentType("text/html; charset=UTF-8");
			// 2. response 객체의 getWriter() 메서드를 호출하여 출력 스트림(PrintWriter) 객체 리턴받기
			PrintWriter out = response.getWriter();
			// 3. PrintWriter(out) 객체의 println() 메서드를 호출하여 출력할 HTML 태그 작성
			out.println("<script>");
			out.println("alert('글 쓰기 실패!')");
			out.println("history.back()");
			out.println("</script>");
					
		} else { // 글쓰기 성공 시
			// 글목록 조회 비즈니스 로직을 수행하기 위한 MateList.mate 서블릿 주소 요청
			forward = new ActionForward();
			forward.setPath("MateList.co");
			forward.setRedirect(true);
		}
		
		
		return forward;
	}

}
