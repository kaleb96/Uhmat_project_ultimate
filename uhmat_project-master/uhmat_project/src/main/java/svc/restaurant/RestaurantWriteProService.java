package svc.restaurant;

import java.sql.Connection;

import dao.RestaurantDAO;

import static db.JdbcUtil.*;
import vo.RestaurantInfoDTO;

public class RestaurantWriteProService {

	public boolean insertResInfo(RestaurantInfoDTO dto) {
		System.out.println("RestaurantWriteProService-insertResInfo");
		boolean isInsertSuccess = false;
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		int insertCount = dao.insertResInfo(dto);
		
		if(insertCount>0) {
			commit(con);
			isInsertSuccess = true;
		}else {
			rollback(con); 
		}
		close(con);
		return isInsertSuccess;
	}

}
