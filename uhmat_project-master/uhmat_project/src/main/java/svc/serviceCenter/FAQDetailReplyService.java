package svc.serviceCenter;

import java.sql.Connection;

import dao.FAQReplyDAO;
import db.JdbcUtil;

public class FAQDetailReplyService {

	public boolean registFAQDetailReply(int idx, String nickname, String answer) {
		System.out.println("FAQDetailReplyService - registFAQDetailReply");
		boolean isWriteReplySuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQReplyDAO dao = FAQReplyDAO.getInstance();
		
		dao.setConnection(con);
		
		isWriteReplySuccess = dao.insertFAQDetailReply(idx, nickname, answer);
		
		if(!isWriteReplySuccess) {
			JdbcUtil.rollback(con);
		} else {
			JdbcUtil.commit(con);
		}
		
		JdbcUtil.close(con);
		
		return isWriteReplySuccess;
	}

}
