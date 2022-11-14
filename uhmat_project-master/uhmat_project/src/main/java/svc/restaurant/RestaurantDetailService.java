package svc.restaurant;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.RestaurantDAO;
import vo.*;

public class RestaurantDetailService {

	public RestaurantInfoDTO selectDetail(String resName) {
		RestaurantInfoDTO dto = null;
		System.out.println("RestaurantDetailService-selectDetail");
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance(); 
		dao.setConnection(con);
		dto = dao.getRestaurantInfo(resName);
		
		close(con); 
		return dto;
	}

	public MapDTO getMapInfo(String resName) {	//지도 위치 정보를 들고옴
		System.out.println("RestaurantModifyFormService - getMapInfo()");
		MapDTO dto = new MapDTO();
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		dto = dao.getMapInfo(resName);
		
		close(con);
		return dto;
	}
}
