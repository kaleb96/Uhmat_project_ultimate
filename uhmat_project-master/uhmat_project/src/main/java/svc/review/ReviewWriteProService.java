package svc.review;

import vo.*;

import static db.JdbcUtil.*;


import java.sql.*;

import dao.*;
public class ReviewWriteProService {

	public boolean registBoard(ReviewBoardDTO dto) {
		System.out.println("ReviewWriteProService - registBoard()");
		boolean isWriteSuccess = false;
		
		// 1. 커넥션 풀 가져오기
		Connection con = getConnection();
		
		// 2. DAO 객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		// 3. DAO 객체에 connection pool 전달하기
		dao.setConnection(con);
		
		// 4. DAO  메서드 호출하기
		int insertCount = dao.insertReview(dto);
		
		// 5. 결과에 따른 트랜잭션 처리하기
		if(insertCount > 0) {
			//성공시 커밋 & isWriteSuccess 를 true로 변경
			commit(con);
			isWriteSuccess = true;
			
		} else {
			// 실패시 rollback;
			rollback(con);
		}
		// 6. 커넥션 풀 반환
			close(con);
		
		return isWriteSuccess;
	}

	public boolean registTag(String tag) {
		System.out.println("ReviewWriteProService - registTag()");
		boolean isTagWrite = false;
		
		// 1. 커넥션 풀 가져오기
		Connection con = getConnection();
		
		// 2. DAO 객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		// 3. DAO 객체에 connection pool 전달하기
		dao.setConnection(con);
		
		// 4. DAO  메서드 호출하기
		int insertCount = dao.insertTag(tag);
		
		// 5. 결과에 따른 트랜잭션 처리하기
		if(insertCount > 0) {
			//성공시 커밋 & isWriteSuccess 를 true로 변경
			commit(con);
			isTagWrite = true;
			
		} else {
			// 실패시 rollback;
			rollback(con);
		}
		// 6. 커넥션 풀 반환
			close(con);
		
		return isTagWrite;
	}

	
}
