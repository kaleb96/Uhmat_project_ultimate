package svc.restaurant;

import java.sql.*;
import static db.JdbcUtil.*;
import java.util.*;

import dao.*;
import vo.*;

public class RestaurantListService {
	public int getListCount() { //전체 목록의 개수를 가져오는 메서드
		System.out.println("RestaurantListService - getListCount");
		Connection con = getConnection();
		int listCount = 0;
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		
		listCount = dao.selectListCount();
		close(con);
		return listCount;
	}
	
	public int getListCount(String category) {
		System.out.println("RestaurantListService - getListCount(category)");
		Connection con = getConnection();
		int listCount = 0;
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		
		listCount = dao.selectListCount(category);
		close(con);
		return listCount;
	}
	
	public int getListCount(String category, String keyword) {
		System.out.println("RestaurantListService - getListCount(category+keyword)");
		Connection con = getConnection();
		int listCount = 0;
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		
		listCount = dao.selectListCount(category,keyword); 
		close(con);
		return listCount;
	}

	public int getListCount(int i, String keyword) {
		System.out.println("RestaurantListService - getListCount(keyword)");
		Connection con = getConnection();
		int listCount = 0;
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		
		listCount = dao.selectListCount(i,keyword);
		close(con);
		return listCount;
	}
	
	public List<RestaurantInfoDTO> selelctRestaurantList(int startPage, int listLimit) {	//글목록을 정해진 개수만큼 불러오는 메서드
		System.out.println("RestaurantListService-selelctRestaurantList");
		List<RestaurantInfoDTO> list = null;
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		list = dao.selectRestaurantList(startPage, listLimit);
		
		close(con);
		return list;
	}


	public List<RestaurantInfoDTO> selelctRestaurantList(int pageNum, int listLimit, String category) {
		System.out.println("RestaurantListService-selelctRestaurantList");
		List<RestaurantInfoDTO> list = null;
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		list = dao.selectRestaurantList(pageNum, listLimit,category);
		
		close(con);
		return list;
	}

	public List<RestaurantInfoDTO> selelctRestaurantList(int pageNum, int listLimit, String category, String keyword) {
		System.out.println("RestaurantListService-selelctRestaurantList(int pageNum, int listLimit, String category, String keyword)");
		List<RestaurantInfoDTO> list = null;
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		list = dao.selectRestaurantList(pageNum, listLimit,category, keyword);
		
		close(con);
		return list;
	}

	public List<RestaurantInfoDTO> selelctRestaurantList(int pageNum, String keyword, int listLimit) {
		System.out.println("RestaurantListService-selelctRestaurantList(int pageNum, String keyword, int listLimit)");
		List<RestaurantInfoDTO> list = null;
		Connection con = getConnection();
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		list = dao.selectRestaurantList(pageNum, keyword, listLimit);
		
		close(con);
		return list;
	}

	public boolean checkRestaurant(String resName) {
		System.out.println("RestaurantListService-checkRestaurant()");
		Connection con = getConnection();
		boolean isRes = false;
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		isRes = dao.selectRestaurantList(resName);
		
		close(con);
		return isRes;
	}

	public List<RestaurantInfoDTO> selectBestRes(int pageNum, int listLimit) {
		System.out.println("RestaurantListService - selectBestRes()");
		Connection con = getConnection();
		List<RestaurantInfoDTO> list = null;
		RestaurantDAO dao = RestaurantDAO.getInstance();
		dao.setConnection(con);
		
		list = dao.selectResBoardList(pageNum, listLimit);
		close(con);		
		
		return list;
	}

	
}
