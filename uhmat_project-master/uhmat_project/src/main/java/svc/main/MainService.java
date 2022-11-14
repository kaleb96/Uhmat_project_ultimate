package svc.main;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewCategoryDAO;
import db.JdbcUtil;
import vo.ReviewBoardDTO;

public class MainService {

	public ArrayList<ReviewBoardDTO> getBoardList() {
		System.out.println("ReviewListService - getBoardList()");
		
		// 리턴할 데이터를 저장할 변수 선언
		ArrayList<ReviewBoardDTO> reviewList = null;
		
		// Connectino 객체 가져오기
		Connection con = JdbcUtil.getConnection();
		
		// ReviewCategoryDAO 객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		// dao에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// 게시물 목록 조회 호출
		reviewList = dao.selectMainReviewBoardList();
		
		// Connection 객체 반환
		JdbcUtil.close(con);
		
		return reviewList;
	}

}
