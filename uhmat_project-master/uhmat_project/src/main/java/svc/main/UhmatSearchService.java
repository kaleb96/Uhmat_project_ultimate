package svc.main;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.*;
import java.util.*;

import dao.*;
import db.*;
import vo.*;

public class UhmatSearchService {



	public ArrayList<ReviewBoardDTO> getSearhReviewBoard(String search) {
		ArrayList<ReviewBoardDTO> reviewList = null;
		
		// Connectino 객체 가져오기
		Connection con = getConnection();
		
		// ReviewCategoryDAO 객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		// dao에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// 게시물 목록 조회 호출
		reviewList = dao.selectMainReviewBoardList(search); 
		
		// Connection 객체 반환
		close(con);
		
		return reviewList;
		
		
	}

	public ArrayList<RestaurantInfoDTO> getSearchRestaurantInfo(String search) {
		ArrayList<RestaurantInfoDTO> list = null;
		
		Connection con = getConnection();
		
		RestaurantDAO dao = RestaurantDAO.getInstance();
		
		dao.setConnection(con);
		
		list = dao.selectMainRestaurantList(search);
		
		return list;
	}

	public int getListCount(String search) {
		System.out.println("ReviewListService - getListCount()");
		// 전체 게시물 갯수를 조회하는 작업을 요청하는 getListCount() 메서드
		int listCount = 0;
		
		//  Connection 객체 가져오기
		Connection con = getConnection();
		
		// ReviewCategoryDAO 객체 가져오지
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		// dao 객체에 Connection 객체 전달
		dao.setConnection(con);
		
		//5. dao 객체의 selectListCount() 메서드를 호출하여 전체 게시물 수 조회
		listCount = dao.selectResearchReviewListCount(search);
		
		
		//6. Connection 객체 반환
		close(con);
		
		// 7. 조회 결과 리턴
		return listCount;
	}



}
