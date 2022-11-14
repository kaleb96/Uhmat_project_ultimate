package action.community;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.TmiWriteProService;
import vo.ActionForward;
import vo.CommunityTmiDTO;

public class TmiWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("TmiWriteProAction");
		
		ActionForward forward = null;
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		System.out.println(timestamp);
		
		// 생성한 timestamp 출력 
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 
		System.out.println(sdf.format(timestamp));
		
		// CommunityTmiDTO 객체 저장
		CommunityTmiDTO tmiBoard = new CommunityTmiDTO();
		tmiBoard.setNickname(request.getParameter("nickname"));
		tmiBoard.setSubject(request.getParameter("subject"));
		tmiBoard.setContent(request.getParameter("content"));
		tmiBoard.setDate(timestamp);
		
		// 실제 비즈니스 작업 요청을 수행할 TmiWriteProService 클래스의 인스턴스를 생성 후
		// registTmiBoard() 메서드를 호출하여 글 쓰기 작업을 요청합니다.
		// => 파라미터 : CommunityTmiDTO 객체, 리턴타입 : boolean(isWriteSuccess)
		TmiWriteProService service = new TmiWriteProService();
		boolean isTmiWriteSuccess = service.registTmiBoard(tmiBoard);
		
		// Service 클래스로부터 글쓰기 작업 요청 처리 결과를 전달받아 성공/실패 여부를 판별
		if(!isTmiWriteSuccess) { // 글쓰기 실패 시
			// 자바스크립트를 통해 "글쓰기 실패!"를 출력 후 이전페이지로 돌아가기 
			// => JSP 페이지에서는 out.println() 메서드를 통해 HTML 코드 등을 출력하지만
			//	  자바 클래스에서는 response 객체를 통해 문서 타입 설정 및 출력 객체를 가져와서
			//	  웹브라우저에 HTML 코드를 출력해야합니다.
			// 1) response 객체의 setContentType() 메서드를 호출하여 응답 문서 타입(ContentType)을 지정합니다.
			response.setContentType("text/html; charset=UTF-8");
						
			// 2) response 객체의 getWriter() 메서드를 호출하여 출력 스트림(PrintWriter) 객체를 리턴받습니다.
			PrintWriter out = response.getWriter();
						
			// 3) PrintWriter(out) 객체의 객체의 println() 메서드를 호출하여 출력할 HTML 태그를 작성합니다.
			out.println("<script>");
			out.println("alert('글쓰기에 실패하셨습니다!')");
			out.println("history.back()");
			out.println("</script>");
			// 주의 ! 응답 데이터에 HTML 코드를 저장한 채로 ActionForward 객체가 null 값이 리턴됩니다.
			// => 따라서, FrontCtroller 에서는 별도의 포워딩 작업을 수행하지 않으며
			// 	  대신 응답 객체(reponse)에 있는 HTML 태그(자바스크립트)가 실행됩니다.
					
		} else { // 글 쓰기 성공 시
			// 글 목록 조회 비즈니스 로직을 수행하기 위한 BoardList.bo 서블릿 주소를 요청합니다.
			// => 새로운 서비스에 대한 요청이므로 Redirect 방식으로 포워딩합니다.
			forward = new ActionForward();
			forward.setPath("TmiList.co");
			forward.setRedirect(true);
		}
		
		return forward;
	}

}
