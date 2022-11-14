package action.restaurant;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.restaurant.RestaurantDetailService;
import vo.*;

public class RestaurantDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("RestaurantDetailAction");

		RestaurantDetailService service = new RestaurantDetailService();
		RestaurantInfoDTO dto = service.selectDetail(request.getParameter("resName"));
		
		String[] opentime = dto.getOpentime().split(",");
		for(int i=0;i<opentime.length;i++) {
//			System.out.println(i+", opentime: "+opentime[i]);
			if(opentime[i].equals("~")) {
				opentime[i]="휴무";
			}
		}
		
		request.setAttribute("mon", opentime[0] );
		request.setAttribute("tue", opentime[1] );
		request.setAttribute("wed", opentime[2] );
		request.setAttribute("thu", opentime[3] );
		request.setAttribute("fri", opentime[4] );
		request.setAttribute("sat", opentime[5] );
		request.setAttribute("sun", opentime[6] );
		request.setAttribute("resInfo", dto);
		
		//지도 정보도 같이 request 객체에 저장
		MapDTO map = service.getMapInfo(request.getParameter("resName"));
		request.setAttribute("map", map);
		
		forward = new ActionForward();
		forward.setPath("food/restaurant/res_detail.jsp");
		forward.setRedirect(false);

		
		return forward;
	}

}
