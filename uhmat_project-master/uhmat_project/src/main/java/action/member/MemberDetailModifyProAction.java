package action.member;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.member.MemberDetailModifyProService;
import vo.ActionForward;
import vo.MemberDTO;

public class MemberDetailModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		 String email= request.getParameter("email");
		 String name=request.getParameter("name");
		 String nickName= request.getParameter("nickName");
		 String newNickName=request.getParameter("newNickName");
		 Date birthdate= Date.valueOf(request.getParameter("birth"));
		 String postCode= request.getParameter("postCode");
		 String address1=request.getParameter("address1");
		 String address2=request.getParameter("address2");
		 String icon=request.getParameter("icon");
		 HttpSession session =request.getSession();
		 MemberDTO member = new MemberDTO();
		 if(session.getAttribute("sNickName").equals(nickName) ) {
		
		 member.setEmail(email);
		 member.setName(name);
		 member.setNickname(newNickName);
		 member.setBirthdate(birthdate);
		 member.setPostCode(postCode);
		 member.setAddress1(address1); 
		 member.setAddress2(address2);
		 member.setIcon(icon);
		 MemberDetailModifyProService service = new MemberDetailModifyProService();
		 boolean isMotifyMemberSuccess = service.motifyMember(member);
		 if(!isMotifyMemberSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원 수정 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				// 가입 성공 시 인증 메일 발송을 위한 서블릿 주소 요청(파라미터 : 아이디, 이메일)
				session.setAttribute("sNickName",  member.getNickname());
				forward = new ActionForward();
				forward.setPath("main.jsp");
				forward.setRedirect(false);
			}
		 }else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('권한없음!')");
				out.println("history.back()");
				out.println("</script>");
		 }
			return forward;
	}

}