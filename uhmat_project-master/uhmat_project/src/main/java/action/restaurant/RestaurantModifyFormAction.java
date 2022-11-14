package action.restaurant;

import java.io.PrintWriter;

import javax.servlet.http.*;

import action.Action;
import svc.restaurant.RestaurantModifyFormService;
import vo.*;

public class RestaurantModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("RestaurantModifyFormAction");
		RestaurantModifyFormService service = new RestaurantModifyFormService();
		
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
		
		
		
		//DAO에서 식당 정보와 식당 위치정보를 불러옴
		String resName = request.getParameter("resName");
		RestaurantInfoDTO dto = service.getRestaurantInfo(resName); 
		MapDTO map = service.getMapInfo(resName);
		
		//지도 위치정보와 식당 정보를 request 객체에 저장하여 jsp 파일로 전송
		request.setAttribute("map", map);
		request.setAttribute("resInfo", dto);
		
		
		forward = new ActionForward();
		forward.setPath("food/restaurant/res_modify.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
