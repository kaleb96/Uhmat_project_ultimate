package svc.restaurant;

import java.sql.*;

import dao.*;

import static db.JdbcUtil.*;
import vo.*;

public class MapModifyProService {

	public void modifyMapInfo(MapDTO map) {	//지도의 위치까지 같이 수정!
		System.out.println("MapModifyProService - modifyMapInfo()");
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		int updateCount = dao.updateMapInfo(map);
		if(updateCount >0) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
	}

}
