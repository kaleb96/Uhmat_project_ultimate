package svc.community;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CommunityDAO;
import vo.TmiReplyDTO;
import static db.JdbcUtil.*;

public class TmiRereplyWriteProService {

	public boolean writeTmiRereply(TmiReplyDTO tmiRereply) {
		System.out.println("TmiRereplyWriteService - writeTmiRereply");
		boolean isTmiRereplyWriteSuccess = false;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
		
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. CommunityDAO 객체에 Connection 객체 전달
		dao.setConnection(con);
		
		// CommunityDAO 객체(인스턴스)의 insertTmiRereply() 메서드를 호출하여 댓글쓰기 작업을 수행 한 후 결과를 리턴
		int tmiRereplyInsertCount = dao.insertTmiRereply(tmiRereply);
		
		if(tmiRereplyInsertCount > 0) {
			commit(con);
			
			isTmiRereplyWriteSuccess = true;
			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isTmiRereplyWriteSuccess;
	}

}
