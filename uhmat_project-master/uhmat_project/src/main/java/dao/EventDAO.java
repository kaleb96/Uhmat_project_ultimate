package dao;

import java.sql.Connection;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.EventServiceDTO;

public class EventDAO {
	// 1. 멤버변수 선언 및 인스턴스 생성
	private static EventDAO instance = new EventDAO();

	// 2. 생성자 정의
	private EventDAO() {
	}

	public static EventDAO getInstance() {
		return instance;
	}

	// --------------------------------------------------------------------
	// 외부(Service Clas)로 부터 Connection 객체를 전달받아 관리하기 위해
	// Conneciton 타입 멤버변수와 Setter 메서드를 정의합니다.
	private Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	
	// =====================================================================
	
	// 이벤트페이지의 서비스(이벤트참여) 글 쓰기
	public int insertService(EventServiceDTO eventService) {
		System.out.println("이벤트페이지 이벤트참여 - insertService");
		
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = 1;
		
		try {
			// 새 글 번호로 사용될 번호를 생성하기 위해 기존 게시물의 가장 큰 번호 조회
			// => 조회 결과가 있을 경우 해당 번호 + 1 값을 새 글 번호로 저장
			String sql = "SELECT MAX(idx) FROM event_service";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
			close(pstmt);
			
			// 전달받은 데이터를 community_recipe 테이블에 INSERT
			sql = "INSERT INTO event_service VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, eventService.getNickname());
			pstmt.setString(3, eventService.getSubject());
			pstmt.setString(4, eventService.getContent());
			pstmt.setString(5, eventService.getService_photo1());
			pstmt.setString(6, eventService.getService_original_photo1());
			pstmt.setString(7, eventService.getService_photo2());
			pstmt.setString(8, eventService.getService_original_photo2());
			pstmt.setString(9, eventService.getService_photo3());
			pstmt.setString(10, eventService.getService_original_photo3());
			pstmt.setString(11, eventService.getService_photo4());
			pstmt.setString(12, eventService.getService_original_photo4());
			pstmt.setString(13, eventService.getService_photo5());
			pstmt.setString(14, eventService.getService_original_photo5());
			
			insertCount = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 - insertService() " + e.getMessage());
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}
	
}
