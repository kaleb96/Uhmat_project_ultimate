package dao;

import java.sql.*;
import java.util.*;

import db.*;
import vo.*;

public class FAQDAO {
	// 싱글톤
	private static FAQDAO instance = new FAQDAO();
	public FAQDAO ()	{}
	public static  FAQDAO getInstance() {
		return instance;
	}

	// 외부로부터 Connection 객체를 전달받아 관리하기 위해
	// Connection 타입 멤버변수와 Setter 메서드 정의
	private Connection con;
	public final void setConnection(Connection con) {
		this.con = con;
	}
	

	public int insertFAQ(FAQDTO faq) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 1;
		
		try {
			String sql = "SELECT MAX(idx) FROM faqboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1; // 조회된 가장 큰 번호 + 1 값을 새 글 번호로 저장
			}
			
			// 전달받은 데이터를 board 테이블에 INSERT
			sql = "INSERT INTO faqboard VALUES (?,?,?,?,now(),?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, faq.getNickname());
			pstmt.setString(3, faq.getSubject());
			pstmt.setString(4, faq.getContent());
			pstmt.setString(5, faq.getReal_File());
			pstmt.setString(6, faq.getOriginal_File());
			pstmt.setInt(7, faq.getReadcount());
			pstmt.setString(8, faq.getCategory());
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return insertCount;
	}
	public FAQDTO selectFAQ(int idx) {
//		System.out.println("FAQDAO - selectFAQ");
		FAQDTO faq = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM faqboard WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				faq = new FAQDTO();
				faq.setContent(rs.getString("content"));
				faq.setDate(rs.getDate("date"));
				faq.setIdx(rs.getInt("idx"));
				faq.setNickname(rs.getString("nickname"));
				faq.setOriginal_File(rs.getString("original_File"));
				faq.setReal_File(rs.getString("real_File"));
				faq.setSubject(rs.getString("subject"));
				faq.setCategory(rs.getString("category"));
				faq.setReadcount(rs.getInt("readcount"));
			}
//			System.out.println("faq");
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return faq;
	}
	public int updateFAQ(FAQDTO faq) {
//		System.out.println("FAQDAO - updateNotice");
		int updateCount = 0;	
		PreparedStatement pstmt = null;
		
		try {

			String sql = "UPDATE faqboard SET nickname=?, subject=?, content=?, category=?, original_File=?, real_File=? WHERE idx=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, faq.getNickname());
			pstmt.setString(2, faq.getSubject());
			pstmt.setString(3, faq.getContent());
			pstmt.setString(4, faq.getCategory());
			pstmt.setString(5, faq.getOriginal_File());
			pstmt.setString(6, faq.getReal_File());
			pstmt.setInt(7, faq.getIdx());
			updateCount = pstmt.executeUpdate();
			System.out.println(updateCount);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}
	public boolean deleteFAQ(int idx) {
		boolean deleteSuccess = false;
		int deleteCount =0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM faqboard WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			deleteCount = pstmt.executeUpdate();
			
			if(deleteCount > 0 ) {
				deleteSuccess = true;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return deleteSuccess;
	}
	public void updateReadCount(int idx) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE faqboard SET readcount = readcount + 1 WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
			
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}


	public int selectFAQCategoryListcount(String category) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM faqboard WHERE category LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+ category +"%");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			System.out.println("listCount : " + listCount);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<FAQDTO> selectFAQCategory(int pageNum, int listLimit, String category) {
//		System.out.println("FAQDAO - selectFAQCategory");
		ArrayList<FAQDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum- 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM faqboard WHERE category LIKE ? ORDER BY idx DESC LIMIT ?,? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+ category +"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<FAQDTO>();
			
			while(rs.next()) {
				FAQDTO faq = new FAQDTO();
				faq.setContent(rs.getString("content"));
				faq.setDate(rs.getDate("date"));
				faq.setIdx(rs.getInt("idx"));
				faq.setNickname(rs.getString("nickname"));
				faq.setOriginal_File(rs.getString("original_File"));
				faq.setReal_File(rs.getString("real_File"));
				faq.setSubject(rs.getString("subject"));
				faq.setCategory(rs.getString("category"));
				faq.setReadcount(rs.getInt("readcount"));
				faq.setCategory(rs.getString("category"));
				
				list.add(faq);
				
			}
			System.out.println("list : " + list);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;

	}
	public int selectAnythingListcount(String keyword) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM faqboard WHERE subject LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%" );
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			System.out.println("listCount : " + listCount);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<FAQDTO> selectAnythingList(int pageNum, int listLimit, String keyword) {
//		System.out.println("FAQDAO - selectAnythingList");
		ArrayList<FAQDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum- 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM faqboard WHERE subject LIKE ? ORDER BY idx DESC LIMIT ?,? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<FAQDTO>();
			
			while(rs.next()) {
				FAQDTO faq = new FAQDTO();
				faq.setContent(rs.getString("content"));
				faq.setDate(rs.getDate("date"));
				faq.setIdx(rs.getInt("idx"));
				faq.setNickname(rs.getString("nickname"));
				faq.setOriginal_File(rs.getString("original_File"));
				faq.setReal_File(rs.getString("real_File"));
				faq.setSubject(rs.getString("subject"));
				faq.setCategory(rs.getString("category"));
				faq.setReadcount(rs.getInt("readcount"));
				faq.setCategory(rs.getString("category"));
				
				list.add(faq);
				
			}
			System.out.println("list : " + list);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}
	
	public ArrayList<FAQDTO> selectSVFAQList() {
		ArrayList<FAQDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT * FROM faqboard ORDER BY idx DESC LIMIT 5";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<FAQDTO>();
			
			while(rs.next()) {
				FAQDTO faq = new FAQDTO();
				faq.setContent(rs.getString("content"));
				faq.setDate(rs.getDate("date"));
				faq.setIdx(rs.getInt("idx"));
				faq.setNickname(rs.getString("nickname"));
				faq.setOriginal_File(rs.getString("original_File"));
				faq.setReal_File(rs.getString("real_File"));
				faq.setSubject(rs.getString("subject"));
				faq.setCategory(rs.getString("category"));
				faq.setReadcount(rs.getInt("readcount"));
				faq.setCategory(rs.getString("category"));
				
				list.add(faq);
				
			}
			System.out.println("list : " + list);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}

}
