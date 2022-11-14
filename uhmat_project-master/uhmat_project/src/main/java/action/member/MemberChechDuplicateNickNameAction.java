package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import svc.member.MemberCheckDuplicateNickNameService;
import vo.ActionForward;

public class MemberChechDuplicateNickNameAction  {

	

	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberChechDuplicateNickNameAction");
		 
		ActionForward forward = null; 
		String nickName = request.getParameter("nickName");
		System.out.println(nickName);
		// MemberCheckDuplicateIdService 의 checkDuplicateId() 메서드 호출하여 아이디 검색 요청
		// => 파라미터 : 아이디(id)   리턴타입 : boolean(isDuplicate)
		MemberCheckDuplicateNickNameService service = new MemberCheckDuplicateNickNameService();
		boolean isDuplicate = service.checkDuplicateNickName(nickName);
		

		// ActionForward 객체를 사용하여 CheckDuplicateId.me 서블릿 주소 요청
		// => 파라미터로 아이디와 검색결과 전달

	
		
		return isDuplicate;
	}

}
