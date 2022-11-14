package svc.community;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CommunityDAO;
import static db.JdbcUtil.*;

public class TmiReplyDeleteProService {

	public boolean isDeleteTmiReply(int idx, String nickname) {
		System.out.println("TmiReplyDeleteProService - isDeleteTmiReply");
		boolean isTmiReplyDeleteSuccess = false;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
		
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. CommunityDAO 객체에 Connection 객체 전달
		dao.setConnection(con);
		
		// CommunityDAO 객체의 deleteTmiReply() 메서드를 호출하여 삭제 작업 수행
		// => 파라미터 : 글번호, 닉네임    리턴타입 : int(deleteTmiReplyCount)
		int deleteTmiReplyCount = dao.deleteTmiReply(idx, nickname);
		System.out.println("TmiReplyDeleteProService - deleteTmiReply");
		
		if(deleteTmiReplyCount > 0) {
			commit(con);
			isTmiReplyDeleteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isTmiReplyDeleteSuccess;
	}

}
