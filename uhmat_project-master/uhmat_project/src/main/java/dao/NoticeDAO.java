package dao;

import java.sql.*;
import java.util.*;

import db.*;
import vo.*;

public class NoticeDAO {
	// 싱글톤
	private static NoticeDAO instance = new NoticeDAO();
	public NoticeDAO ()	{}
	public static  NoticeDAO getInstance() {
		return instance;
	}

	// 외부로부터 Connection 객체를 전달받아 관리하기 위해
	// Connection 타입 멤버변수와 Setter 메서드 정의
	private Connection con;
	public final void setConnection(Connection con) {
		this.con = con;
	}
	
	

	public int insertNotice(NoticeDTO notice) {
//		System.out.println("NoticeDAO - insertNotice");
		int insertCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 1;
		
		try {
			String sql = "SELECT MAX(idx) FROM noticeboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1; // 조회된 가장 큰 번호 + 1 값을 새 글 번호로 저장
			}
			
			// 전달받은 데이터를 board 테이블에 INSERT
			sql = "INSERT INTO noticeboard VALUES (?,?,?,?,now(),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, notice.getNickname());
			pstmt.setString(3, notice.getSubject());
			pstmt.setString(4, notice.getContent());
			pstmt.setString(5, notice.getReal_File());
			pstmt.setString(6, notice.getOriginal_File());
			pstmt.setString(7, notice.getCategory());
			
			insertCount = pstmt.executeUpdate();
//			System.out.println("insertCount : " + insertCount);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return insertCount;
	}
	public NoticeDTO selectNotice(int idx) {
//		System.out.println("NoticeDAO - selectNotice");
		NoticeDTO notice = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM noticeboard WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new NoticeDTO();
				notice.setContent(rs.getString("content"));
				notice.setDate(rs.getDate("date"));
				notice.setIdx(rs.getInt("idx"));
				notice.setNickname(rs.getString("nickname"));
				notice.setOriginal_File(rs.getString("original_File"));
				notice.setReal_File(rs.getString("real_File"));
				notice.setSubject(rs.getString("subject"));
				notice.setCategory(rs.getString("category"));
			}
//			System.out.println("notice");
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return notice;
	}
	public int updateNotice(NoticeDTO notice) {
//		System.out.println("NoticeDAO - updateNotice");
		int updateCount = 0;	
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE noticeboard SET nickname=?, subject=?, content=?, category=?, original_File=?, real_File=? WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getNickname());
			pstmt.setString(2, notice.getSubject());
			pstmt.setString(3, notice.getContent());
			pstmt.setString(4, notice.getCategory());
			pstmt.setString(5, notice.getOriginal_File());
			pstmt.setString(6, notice.getReal_File());
			pstmt.setInt(7, notice.getIdx());
			
			updateCount = pstmt.executeUpdate();
//			System.out.println(updateCount);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return updateCount;
	}
	public boolean deleteNotice(int idx) {
//		System.out.println("NoticeDAO - deleteNotice ");
		boolean deleteSuccess = false;
		int deleteCount =0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM noticeboard WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			deleteCount = pstmt.executeUpdate();
			
//			System.out.println("deleteCount : " + deleteCount);
			
			if(deleteCount > 0 ) {
				deleteSuccess = true;
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생!  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return deleteSuccess;
	}
	
	public int selectAnythingListcount(String keyword) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM noticeboard WHERE subject LIKE ?";
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
	
	public ArrayList<NoticeDTO> selectAnythingList(int pageNum, int listLimit, String keyword) {
		ArrayList<NoticeDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum- 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM noticeboard WHERE subject LIKE ? ORDER BY idx DESC LIMIT ?,? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<NoticeDTO>();
			
			while(rs.next()) {
				NoticeDTO notice = new NoticeDTO();
				notice.setContent(rs.getString("content"));
				notice.setDate(rs.getDate("date"));
				notice.setIdx(rs.getInt("idx"));
				notice.setNickname(rs.getString("nickname"));
				notice.setOriginal_File(rs.getString("original_File"));
				notice.setReal_File(rs.getString("real_File"));
				notice.setSubject(rs.getString("subject"));
				notice.setCategory(rs.getString("category"));
				
				list.add(notice);
				
			}
//			System.out.println("list : " + list);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}
	public int selectNoticeCategoryListcount(String category) {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM noticeboard WHERE category LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+ category +"%");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
	//		System.out.println("listCount : " + listCount);
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
		return listCount;
	}
	public ArrayList<NoticeDTO> selectNoticeCategory(int pageNum, int listLimit, String category) {
		ArrayList<NoticeDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (pageNum- 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM noticeboard WHERE category LIKE ? ORDER BY idx DESC LIMIT ?,? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+ category +"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<NoticeDTO>();
			
			while(rs.next()) {
				NoticeDTO notice = new NoticeDTO();
				notice.setContent(rs.getString("content"));
				notice.setDate(rs.getDate("date"));
				notice.setIdx(rs.getInt("idx"));
				notice.setNickname(rs.getString("nickname"));
				notice.setOriginal_File(rs.getString("original_File"));
				notice.setReal_File(rs.getString("real_File"));
				notice.setSubject(rs.getString("subject"));
				notice.setCategory(rs.getString("category"));
				
				list.add(notice);
			}
//			System.out.println("list : " + list);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}
	
	public ArrayList<NoticeDTO> selectSVNoticeList() {
		ArrayList<NoticeDTO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT * FROM noticeboard ORDER BY idx LIMIT 5";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<NoticeDTO>();
			
			while(rs.next()) {
				NoticeDTO notice = new NoticeDTO();
				notice.setContent(rs.getString("content"));
				notice.setDate(rs.getDate("date"));
				notice.setIdx(rs.getInt("idx"));
				notice.setNickname(rs.getString("nickname"));
				notice.setOriginal_File(rs.getString("original_File"));
				notice.setReal_File(rs.getString("real_File"));
				notice.setSubject(rs.getString("subject"));
				notice.setCategory(rs.getString("category"));
				
				list.add(notice);
				
			}
			
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
