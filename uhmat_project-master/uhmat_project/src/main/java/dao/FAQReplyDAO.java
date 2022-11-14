package dao;

import java.sql.*;
import java.util.*;

import db.*;
import vo.*;

public class FAQReplyDAO {
	// 싱글톤
	private static FAQReplyDAO instance = new FAQReplyDAO();
	public FAQReplyDAO ()	{}
	public static  FAQReplyDAO getInstance() {
		return instance;
	}

	// 외부로부터 Connection 객체를 전달받아 관리하기 위해
	// Connection 타입 멤버변수와 Setter 메서드 정의
	private Connection con;
	public final void setConnection(Connection con) {
		this.con = con;
	}
	public boolean insertFAQDetailReply(int idx, String nickname, String answer) {
		boolean isWriteReplySuccess = false;
		int insertCount = 0;
		int board_idx = 1;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "INSERT INTO faq_reply VALUES(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, nickname);
			pstmt.setString(3, answer);
			

			insertCount = pstmt.executeUpdate();
			
			if(insertCount > 0) {
				isWriteReplySuccess = true;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return isWriteReplySuccess;
	}
	public FAQReplyDTO selectReplyList(int idx) {
//		System.out.println("selectReplyList");
		FAQReplyDTO reply = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM faq_reply WHERE board_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				reply = new FAQReplyDTO();
				reply.setAnswer(rs.getString("answer"));
				reply.setBoard_idx(rs.getInt("board_idx"));
				reply.setNickname(rs.getString("nickname"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return reply;
	}
	

	public boolean deleteReplyFAQ(int idx) {
		boolean deleteSuccess = false;
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM faq_reply WHERE board_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			deleteCount = pstmt.executeUpdate();
			
			if(deleteCount > 0) {
				deleteSuccess = true;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return deleteSuccess;
	}

	public boolean selectFAQReply(int idx) {
		boolean selectFAQReply = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM faq_reply WHERE board_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				selectFAQReply = true;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return selectFAQReply;
	}

}
