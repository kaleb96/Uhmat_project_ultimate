package svc.community;

import java.sql.Connection;

import dao.CommunityDAO;
import vo.TmiReplyDTO;

import static db.JdbcUtil.*;

public class TmiReplyWriteService {

	public boolean writeTmiReply(TmiReplyDTO tmiReply) {
		System.out.println("TmiReplyWriteService - writeTmiReply");
		boolean isTmiReplyWriteSuccess = false;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
		
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. CommunityDAO 객체에 Connection 객체 전달
		dao.setConnection(con);
		
		// CommunityDAO 객체(인스턴스)의 insertTmiReply() 메서드를 호출하여 댓글쓰기 작업을 수행 한 후 결과를 리턴
		// // => 파라미터 : CommunityDAO 객체(tmiReply) 리턴타입 : int(tmiInsertCount)
		int tmiReplyInsertCount = dao.insertTmiReply(tmiReply);
		
		
		// 리턴받은 작업 수행 결과를 통해 판별 후 트랜잭션 처리 작업 수행
		if(tmiReplyInsertCount > 0) {
			commit(con);
			
			isTmiReplyWriteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isTmiReplyWriteSuccess;
	}

}
