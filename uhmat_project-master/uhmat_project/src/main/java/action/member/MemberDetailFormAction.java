package action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.member.MemberDetailService;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberDetailFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberDetailFormAction");

		ActionForward forward = null;
		HttpSession session= request.getSession();
		String nickName = request.getParameter("nickName");
		System.out.println(nickName);
		System.out.println(session.getAttribute("sNickName"));
		if(session.getAttribute("sNickName").equals(nickName) ) {
		
		
		MemberDetailService service = new MemberDetailService();

		MemberDTO member = service.getMember(nickName);

		request.setAttribute("member", member);

		forward = new ActionForward();
		forward.setPath("member/detail.jsp");
		forward.setRedirect(false);
		}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('권한 없음')");
				out.println("history.back()");
				out.println("</script>");
		}
		return forward;
	}

}
