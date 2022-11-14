package action.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import svc.member.MemberDetailService;
import vo.MemberDTO;

public class MemberDetailListAction {

	
	
	public MemberDTO execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberDetailListAction");

		
		String email = request.getParameter("email");
		System.out.println(email);

		MemberDetailService service = new MemberDetailService();

		MemberDTO member = service.getMember(email);
		
		return member;
	}

}
