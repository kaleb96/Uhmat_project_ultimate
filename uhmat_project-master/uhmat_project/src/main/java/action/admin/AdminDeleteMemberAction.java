package action.admin;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import action.*;
import svc.admin.*;
import vo.*;

public class AdminDeleteMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String email = request.getParameter("email");
		System.out.println("email : " + email);
		
		AdminDeleteMemberService service = new AdminDeleteMemberService();
		
		int deleteCount = service.deleteMember(email);
		
		if(deleteCount > 0 ) {
			
			int pageNum = 1; // 현재페이지 번호
			int listLimit = 10; // 한 페이지 당 표시할 게시물 수
			int pageLimit = 10; // 한 페이지 당 표시할 페이지 목록 수
			
			email = "";
			if(request.getParameter("email") != null) {
				email = request.getParameter("email");
			}
			System.out.println("email : " + email);
			
			String keyword = "";
			if(request.getParameter("keyword") != null) {
				keyword = request.getParameter("keyword");
			}
			// 단, URL 파라미터로 현재 페이지번호(pageNum) 가 전달됐을 경우 가져와서 변수에 저장
			if(request.getParameter("pageNum") != null){
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			
			AdminManageMemberListService service2 = new AdminManageMemberListService();
			
			int listCount = 0;
			
			listCount = service2.getMemberListSelectCount(keyword);

//			System.out.println("전체 게시물 수 " + listCount);
			
			// 1. 현재 페이지에서 표시할 전체 페이지 수 계산
			int maxPage = (int)Math.ceil((double)listCount / listLimit);

			// 2. 현재 페이지에서 보여줄 시작페이지 번호(1, 11, 21, ...)  - 알면 좋음
			int startPage = ((int)((double)pageNum / pageLimit + 0.9) - 1 ) * pageLimit + 1;

			// 3. 현재 페이지에서 보여줄 끝 페이지 번호(10,20, 30, ~~~)
			int endPage = startPage + pageLimit - 1;
			
			// 4.
			if(endPage > maxPage){
				endPage = maxPage;
			}
			// 페이징 처리 정보를 PageInfo 객체에 저장
			PageInfo pageInfo = new PageInfo(pageNum, maxPage, startPage, endPage, listCount);
			
			ArrayList<MemberDTO> list = service2.getMemberList(pageNum, listLimit, keyword);
//			System.out.println("AdminManageMemberListAction의 list : " + list);
			
			MemberDTO memberDetail = service2.getMember(email);
//			System.out.println("memberDetail: " + memberDetail);
			request.setAttribute("memberDetail", memberDetail);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			
			forward = new ActionForward();
			forward.setPath("admin/adminManageMember.jsp");
			forward.setRedirect(false);
			
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
