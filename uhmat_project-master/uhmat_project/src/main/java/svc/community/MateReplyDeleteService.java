package svc.community;

import java.sql.Connection;

import dao.CommunityDAO;

import static db.JdbcUtil.*;

public class MateReplyDeleteService {

	public boolean isDeleteReplyMate(int reply_idx, String nickname) {
		
		boolean isDeleteSuccess = false;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		// CommunityDAO 객체의 deleteReplyMate() 메서드를 호출하여 삭제 작업 수행
		// => 파라미터 : 글번호    리턴타입 : int(deleteCount)
		int deleteCount = dao.deleteReplyMate(reply_idx, nickname);
		System.out.println("service" + deleteCount);
		
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

}
