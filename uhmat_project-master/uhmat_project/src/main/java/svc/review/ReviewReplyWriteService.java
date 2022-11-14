package svc.review;

import java.sql.*;

import dao.*;

import static db.JdbcUtil.*;

public class ReviewReplyWriteService {

	public boolean registReply(int board_idx, String nickname, String content) {
		
		System.out.println("ReviewReplyWriteService - registReply()");
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount = dao.insertReply(board_idx, nickname, content);
		
		if(insertCount > 0) {
			
			commit(con);
			
			isWriteSuccess = true;
			
		} else {
			
			rollback(con);
		}
		
		close(con);
		
		return isWriteSuccess;
	}

}
