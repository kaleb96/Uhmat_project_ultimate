package svc.restaurant;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.io.*;
import java.nio.file.*;
import java.sql.*;

import dao.*;
import vo.*;

public class RestaurantModifyProService {

	public boolean modifyResInfo( RestaurantInfoDTO dto) {	//수정된 정보들을 업데이트 하는 메서드 (사진이 바뀌지 않은 경우)
		System.out.println("RestaurantModifyProService - modifyResInfo()");
		boolean isModifySuccess = false;
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		
		int modifyCount = dao.modifyResInfo(dto);
		
		if(modifyCount>0) {
			commit(con);
			isModifySuccess = true;
		}else {
			rollback(con);
		}
		return isModifySuccess;
	}

	public String bringPhoto(String resName) {	//사진 수정하는 작업에 앞서 기존 사진을 가져오는 메서드
		System.out.println("RestaurantModifyProService - bringPhoto");
		String photo = "";
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		photo = dao.selectPhoto(resName);
		
		close(con);
		return photo;
	}

	//메서드 오버라이딩을 통해서 수정될 사진의 기존 사진을 삭제하는 메서드
	public boolean modifyResInfo(RestaurantInfoDTO dto, String filePath, String photo) { //수정된 정보들을 업데이트 하는 메서드2
		System.out.println("RestaurantModifyProService - modifyResInfo2()");
		boolean isModifySuccess = false;
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		int modifyCount = dao.modifyResInfo(dto);
		if(modifyCount>0) {
			Path path = Paths.get(filePath + "/" + photo);
			try {
				Files.deleteIfExists(path);
				System.out.println("fileDelete is success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("fileDelete is failed");
			}
			commit(con);
			isModifySuccess = true;
		}else {
			rollback(con);
		}
		return isModifySuccess;
	}

}
