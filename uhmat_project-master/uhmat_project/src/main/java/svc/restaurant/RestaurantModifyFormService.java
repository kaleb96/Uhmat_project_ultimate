package svc.restaurant;

import java.sql.Connection;

import dao.RestaurantDAO;

import static db.JdbcUtil.*;

import vo.MapDTO;
import vo.RestaurantInfoDTO;

public class RestaurantModifyFormService {

	public RestaurantInfoDTO getRestaurantInfo(String resName) {	//식당 정보를 들고옴
		System.out.println("RestaurantModifyFormService - getRestaurantInfo()");
		RestaurantInfoDTO dto = null;
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
