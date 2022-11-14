package svc.restaurant;

import java.sql.*;

import dao.*;

import static db.JdbcUtil.*;
import vo.*;

public class MapWriteProService {

	public void insertMapInfo(MapDTO map) {
		System.out.println("MapWriteProService-insertMapInfo");
		System.out.println(map);
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		int insertCount = dao.insertMapInfo(map);
		if(insertCount>0) {
			commit(con);
			System.out.println("지도 입력 성공!");
		}else {
			rollback(con);
			System.out.println("지도 입력 실패!");
		}
		
		close(con);
	}

}
