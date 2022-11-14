package svc.review;
import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewCategoryDAO;
import vo.ReviewBoardDTO;
public class ReviewListService {

	public int getListCount() {
		
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
		listCount = dao.selectReviewListCount();
		
		
		//6. Connection 객체 반환
		close(con);
		
		// 7. 조회 결과 리턴
		return listCount;
	}

	public static ArrayList<ReviewBoardDTO> getBoardList(int pageNum, int listLimit, String targetTag) {
		System.out.println("ReviewListService - getBoardList(int pageNum, int listLimit, String targetTag)");
		
		// 리턴할 데이터를 저장할 변수 선언
		ArrayList<ReviewBoardDTO> reviewList = null;
		
		// Connectino 객체 가져오기
		Connection con = getConnection();
		
		// ReviewCategoryDAO 객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		// dao에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// 게시물 목록 조회 호출
		if(targetTag.equals("")) {
			
			reviewList = dao.selectReviewBoardList(pageNum, listLimit);
			
		} else {
			
			reviewList = dao.selectReviewBoardList(pageNum, listLimit, targetTag);
		}
		// Connection 객체 반환
		close(con);
		
		return reviewList;
	}

	public static ArrayList<ReviewBoardDTO> getBoardList(String resName, int pageNum, int listLimit) {
		System.out.println("ReviewListService - getBoardList(String resName, int pageNum, int listLimit)");
		
		// 리턴할 데이터를 저장할 변수 선언
		ArrayList<ReviewBoardDTO> reviewList = null;
		
		// Connectino 객체 가져오기
		Connection con = getConnection();
		
		// ReviewCategoryDAO 객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		// dao에 Connection 객체 전달하기
		dao.setConnection(con);		
		
		//식당으로 검색된 리뷰
		reviewList = dao.selectReviewBoardList(resName, pageNum, listLimit);
		
		close(con);
		return reviewList;
	}

	public ArrayList<ReviewBoardDTO> getBestLikeBoardList(int pageNum, int listLimit,String targetTag) {
		
		// 리턴할 데이터를 저장할 변수 선언
		ArrayList<ReviewBoardDTO> reviewList = null;
		
		// Connectino 객체 가져오기
		Connection con = getConnection();
		
		// ReviewCategoryDAO 객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		// dao에 Connection 객체 전달하기
		dao.setConnection(con);		
		
		//식당으로 검색된 리뷰
		reviewList = dao.selectReviewBestLikeBoardList(pageNum, listLimit,targetTag);
		
		close(con);
		return reviewList;
	}

	public ArrayList<ReviewBoardDTO> getRecentBoardList(int pageNum, int listLimit, String targetTag) {
		// 리턴할 데이터를 저장할 변수 선언
				ArrayList<ReviewBoardDTO> reviewList = null;
				
				// Connectino 객체 가져오기
				Connection con = getConnection();
				
				// ReviewCategoryDAO 객체 가져오기
				ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
				// dao에 Connection 객체 전달하기
				dao.setConnection(con);		
				
				//식당으로 검색된 리뷰
				reviewList = dao.selectRecentReview(pageNum, listLimit,targetTag);
				
				close(con);
				return reviewList;
	}
	
}
