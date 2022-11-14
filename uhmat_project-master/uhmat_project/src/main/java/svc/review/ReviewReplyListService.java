package svc.review;

import java.sql.*;
import java.util.*;

import dao.*;

import static db.JdbcUtil.*;
import vo.*;

public class ReviewReplyListService {

	public static ArrayList<ReviewReplyDTO> getReplyList(int board_idx) {
		System.out.println("ReviewReplyListService - getReplyList");
		
		ArrayList<ReviewReplyDTO> replyList = null;
		
		Connection con = getConnection();
		
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		dao.setConnection(con);
		
		replyList = dao.selectReivewReplyList(board_idx);
		
		close(con);
		return replyList;
	}	

}
