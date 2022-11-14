package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.member.MemberCheckDuplicateEmailService;
import vo.ActionForward;

public class MemberChechDuplicateEmailAction  {


	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	System.out.println("MemberChechDuplicateEmailAction");
		
		ActionForward forward = null; 
		
		String email = request.getParameter("email");

		// MemberCheckDuplicateIdService 의 checkDuplicateId() 메서드 호출하여 아이디 검색 요청
		// => 파라미터 : 아이디(id)   리턴타입 : boolean(isDuplicate)
		MemberCheckDuplicateEmailService service = new MemberCheckDuplicateEmailService();
		boolean isDuplicate = service.checkDuplicateEmail(email);
		

		// ActionForward 객체를 사용하여 CheckDuplicateId.me 서블릿 주소 요청
		// => 파라미터로 아이디와 검색결과 전달

	
		
		return isDuplicate;
	
	}

}
