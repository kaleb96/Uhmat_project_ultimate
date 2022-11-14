package dao;

import java.sql.*;
import java.util.*;

import db.*;
import vo.*;

public class LiveTalkDAO {
		// 1. 멤버변수 선언 및 인스턴스 생성
		private static LiveTalkDAO instance = new LiveTalkDAO();

		// 2. 생성자 정의
		private LiveTalkDAO() {
		}

		public static LiveTalkDAO getInstance() {
			return instance;
		}
		
		private Connection con;
		public void setConnection(Connection con) {
			this.con = con;
		}

		public boolean insertTalkLive(String talk, String nickname) {
			boolean insertTalk = false;
			int insertCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int num = 1;
			
			try {
				String sql = "SELECT MAX(idx) FROM livetalk";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					num = rs.getInt(1) + 1; // 조회된 가장 큰 번호 + 1 값을 새 글 번호로 저장
				}
				sql = "INSERT INTO livetalk VALUES(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, nickname);
				pstmt.setString(2, talk);
				pstmt.setInt(3, num);
				
				insertCount = pstmt.executeUpdate();
				
				if(insertCount > 0 ) {
					insertTalk = true;
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
				e.printStackTrace();
			} finally {
				JdbcUtil.close(pstmt);
			}
			
			
			return insertTalk;
		}

		public ArrayList<LiveTalkDTO> selectTalkLive() {
			ArrayList<LiveTalkDTO> list = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM livetalk ORDER BY idx DESC";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				list = new ArrayList<LiveTalkDTO>();
				
				while(rs.next()) {
					LiveTalkDTO dto = new LiveTalkDTO();
					dto.setNickname(rs.getString("nickname"));
					dto.setTalk(rs.getString("talk"));
					dto.setIdx(rs.getInt("idx"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
				e.printStackTrace();
			}
			
			return list;
		}
}
