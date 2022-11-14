package svc.community;

import java.sql.Connection;

import dao.CommunityDAO;

import static db.JdbcUtil.*;

public class MateReplyModifyProService {

	public boolean isModifyReplyMate(int reply_idx, String nickname, String content) {
		System.out.println("MateReplyModifyProService - isModifyReplyMate");
		
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		int modifyCount = dao.modifyReplyMate(reply_idx, nickname, content);
		System.out.println("service" + modifyCount);
		
		// deleteCount 가 0 보다 크면 commit, 아니면 rollback 작업 수행
		if(modifyCount > 0) {
			commit(con);
		// isDeleteSuccess 값을 true 로 변경하여 성공 표시
			isModifySuccess = true;
		} else {
			rollback(con);
			}
				
		close(con);
			
		return isModifySuccess;
	}

}
