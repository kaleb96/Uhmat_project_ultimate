package action.restaurant;

import java.io.PrintWriter;

import javax.servlet.http.*;

import action.Action;
import svc.restaurant.RestaurantDeleteService;
import vo.*;

public class RestaurantDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("RestaurantDeleteAction");
		
		//세션을 확인 후 권한이 있는지 확인
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("sNickName");
		System.out.println("session name: "+name);
		PrintWriter out = response.getWriter(); 
		response.setContentType("text/html; charset=UTF-8");
		if(name == null) {
			System.out.println("돌아갸야댐!!");

			out.print("<script>alert('식당 수정 권한 없음!');history.back();</script>");
		}
		
		if(!name.equals("admin")) {
			System.out.println("돌아갸야댐!!");
			out.print("<script>alert('식당 수정 권한 없음!');history.back();</script>");
			return forward;
		}
		
		RestaurantDeleteService service = new RestaurantDeleteService();
		String resName = request.getParameter("resName");
		String photo = service.bringPhoto(resName);
		String filePath =  request.getServletContext().getRealPath("/upload");
		boolean isDeleteSuccess = service.deleteRestaurantInfo(resName,filePath, photo);
		
		if(isDeleteSuccess) {
			//성공시 지도 정보도 같이 삭제
			service.deleteMapInfo(resName);
			forward = new ActionForward();
			forward.setPath("restaurantList.re");
			forward.setRedirect(true);
		}else {
			out = response.getWriter();
			response.setContentType("text/html; charset=Utf-8");
			out.print("<script>alert('식당 삭제 실패');history.back();</script>");
		}
		return forward;
	}

}
