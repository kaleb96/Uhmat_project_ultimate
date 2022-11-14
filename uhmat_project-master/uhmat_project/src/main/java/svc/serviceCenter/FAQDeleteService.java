package svc.serviceCenter;

import java.sql.Connection;

import dao.FAQDAO;
import dao.FAQReplyDAO;
import db.JdbcUtil;

public class FAQDeleteService {

	public boolean removeFAQ(int idx) {
		System.out.println("FAQDeleteService - removeFAQ");
		boolean deleteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQDAO dao = FAQDAO.getInstance();
		
		dao.setConnection(con);
		
		deleteSuccess = dao.deleteFAQ(idx);
		System.out.println("deleteSuccess : "+ deleteSuccess);
		
		if(!deleteSuccess) {
			JdbcUtil.rollback(con);

		} else {
			JdbcUtil.commit(con);
		}
		
		return deleteSuccess;
	}

	public boolean removeReplyFAQ(int idx) {
		boolean deleteReplySuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQReplyDAO dao = FAQReplyDAO.getInstance();
		
		dao.setConnection(con);
		
		deleteReplySuccess = dao.deleteReplyFAQ(idx);
		
		if(!deleteReplySuccess) {
			JdbcUtil.rollback(con);
		} else {
			JdbcUtil.commit(con);
		}
		
		JdbcUtil.close(con);
		
		return deleteReplySuccess;
	}


	public boolean checkReply(int idx) {
		boolean checkReply = false;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQReplyDAO dao = FAQReplyDAO.getInstance();
		
		dao.setConnection(con);
		
		checkReply = dao.selectFAQReply(idx);
		
		JdbcUtil.close(con);
		
		return checkReply;
	}


}
