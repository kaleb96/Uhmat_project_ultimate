package svc.community;

import java.sql.Connection;

import dao.CommunityDAO;

import static db.JdbcUtil.*;

import vo.MateReplyDTO;

public class MateRereplyWriteService {

	public boolean writeMateRereply(MateReplyDTO mateRereply) {
		System.out.println("MateRereplyWriteService - writeMateRereply");
		
		boolean isMateRereplySuccess = false;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		// CommunityDAO 객체(인스턴스)의 insertMateRereply() 메서드를 호출하여 댓글쓰기 작업을 수행 한 후 결과를 리턴
		int mateRereplyInsertCount = dao.insertMateRereply(mateRereply);
		
		if(mateRereplyInsertCount > 0) {
			commit(con);
			isMateRereplySuccess = true;
			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isMateRereplySuccess;
	}

}
