package svc.community;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CommunityDAO;

public class TmiDeleteProService {

	public boolean deleteTmi(int idx) {
		System.out.println("MateDeleteProService - deleteTmi");
		boolean isDeleteSuccess = false;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
				
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. BoardDAO 객체에 Connetion 객체 전달하기 - 공통
		dao.setConnection(con);
		
		// CommunityDAO 객체의 deleteMate() 메서드를 호출하여 삭제 작업 수행
		// => 파라미터 : 글번호    리턴타입 : int(deleteCount)
		int deleteCount = dao.deleteTmi(idx);
		
		// deleteCount 가 0 보다 크면 commit, 아니면 rollback 작업 수행
			if(deleteCount > 0) {
				commit(con);
				// isDeleteSuccess 값을 true 로 변경하여 성공 표시
				isDeleteSuccess = true;
			} else {
				rollback(con);
			}
				
		close(con);
				
		
		return isDeleteSuccess;
	}
	
//	public boolean isTmiWriter(int idx, String nickname) {
//		System.out.println("TmiDeleteProService - isTmiWriter");
//		boolean isTmiWriter = false;
//		
//		// 1. Connection 객체 가져오기 - 공통
//		Connection con = getConnection();
//				
//		// 2. CommunityDAO 객체 가져오기 - 공통
//		CommunityDAO dao = CommunityDAO.getInstance();
//		
//		// 3. BoardDAO 객체에 Connetion 객체 전달하기 - 공통
//		dao.setConnection(con);
//		
//		// CommunityTmiDTO 객체의 isTmiWriter() 메서드를 호출하여 수정 권한 판별 수행
//		isTmiWriter = dao.isTmiWriter(idx, nickname);
//		
//		close(con);
//		
//		return isTmiWriter;
//	}

//	public boolean removeTmiBoard(int idx) {
//		System.out.println("TmiDeleteProService - removeTmiBoard");
//		boolean isTmiDeleteSuccess = false;
//		
//		// 1. Connection 객체 가져오기 - 공통
//		Connection con = getConnection();
//				
//		// 2. CommunityDAO 객체 가져오기 - 공통
//		CommunityDAO dao = CommunityDAO.getInstance();
//		
//		// 3. BoardDAO 객체에 Connetion 객체 전달하기 - 공통
//		dao.setConnection(con);
//		
//		// CommunityTmiDTO deleteTmiBoard() 메서드를 호출하여 삭제 작업 수행
//		// => 파라미터 : 글번호		리턴타입 : int(deleteTmiCount)
//		int deleteTmiCount = dao.deleteTmiBoard(idx);
//		
//		// deleteCount가 0 보다 크면 commit, 아니면 rollback 작업 수행
//		if(deleteTmiCount > 0) {
//			commit(con);
//			isTmiDeleteSuccess = true;
//		} else {
//			rollback(con);
//		}
//		
//		return isTmiDeleteSuccess;
//	}

	public void deleteTmiBoard(int idx) {
		System.out.println("TmiDeleteProService - deleteTmiBoard");
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
				
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. BoardDAO 객체에 Connetion 객체 전달하기 - 공통
		dao.setConnection(con);
		
		// CommunityDAO 객체의 deleteMate() 메서드를 호출하여 삭제 작업 수행
		// => 파라미터 : 글번호    리턴타입 : int(deleteCount)
		int deleteCount = dao.deleteTmiBoard(idx);
		
		// deleteCount 가 0 보다 크면 commit, 아니면 rollback 작업 수행
		if(deleteCount > 0) {
			commit(con);
			// isDeleteSuccess 값을 true 로 변경하여 성공 표시
			
		} else {
			rollback(con);
		}
			
	close(con);
						
		
		
	}


}
