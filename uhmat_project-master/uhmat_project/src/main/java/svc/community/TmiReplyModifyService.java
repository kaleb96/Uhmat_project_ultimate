package svc.community;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CommunityDAO;
import vo.TmiReplyDTO;

public class TmiReplyModifyService {

	public TmiReplyDTO getTmiReply(int idx) {
		System.out.println("TmiReplyModifyService - getTmiReply");
		
		TmiReplyDTO tmiReply = null;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
		
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. CommunityDAO 객체에 Connection 객체 전달
		dao.setConnection(con);
		
		// CommunityDAO 객체의 selectTmiReply() 메서드를 호출하여 1개 게시물의 상세 정보를 조회합니다.
		tmiReply = dao.selectTmiReply(idx);
		
		close(con);
		
		return tmiReply;
	}

	
}
