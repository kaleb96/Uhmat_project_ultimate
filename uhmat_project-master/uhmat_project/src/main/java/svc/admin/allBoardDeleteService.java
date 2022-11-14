package svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CommunityDAO;
import dao.MemberDAO;

public class allBoardDeleteService {

	public boolean deleteBoard(int idx, String title) {
		System.out.println("deleteBoard");
		boolean isDeleteSuccess = false;
		
		Connection con = getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		// CommunityDAO 객체의 deleteMate() 메서드를 호출하여 삭제 작업 수행
		// => 파라미터 : 글번호    리턴타입 : int(deleteCount)
		int deleteCount = dao.deleteBoard(idx,title);
		
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
