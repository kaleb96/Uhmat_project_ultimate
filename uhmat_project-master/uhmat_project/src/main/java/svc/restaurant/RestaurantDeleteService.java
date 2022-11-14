package svc.restaurant;

import java.io.IOException;
import java.nio.file.*;
import java.sql.Connection;

import dao.RestaurantDAO;

import static db.JdbcUtil.*;

public class RestaurantDeleteService {

	public boolean deleteRestaurantInfo(String resName, String filePath, String fileName) {	//식당의 정보 삭제 하는 메서드
		boolean isdeleteSuccess = false;
		System.out.println("RestaurantDeleteService - deleteRestaurantInfo()");
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		
		int deleteCount = dao.deleteRestaurantInfo(resName);
		
		if(deleteCount>0) {
			Path path = Paths.get(filePath + "/" + fileName);
			try {
				Files.deleteIfExists(path);
				System.out.println("fileDelete is success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("fileDelete is failed");
			}
			commit(con);
			isdeleteSuccess = true;
		}else {
			rollback(con);
		}
		
		close(con);
		return isdeleteSuccess;
	}

	public String bringPhoto(String resName) {	//삭제할 파일 이름을 가져오는 메서드
		System.out.println("RestaurantDeleteService - bringPhoto");
		String photo = "";
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		photo = dao.selectPhoto(resName);
		
		close(con);
		return photo;
	}

	//지도 위치 정보를 삭제하는 메서드
	public void deleteMapInfo(String resName) {
		System.out.println("RestaurantDeleteService - deleteMapInfo");
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		dao.deleteMapInfo(resName);
		
		close(con);
	}

}
