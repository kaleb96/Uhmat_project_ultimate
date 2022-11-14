package svc.serviceCenter;

import java.sql.*;

import dao.*;
import db.*;

public class NoticeDeleteService {

	public boolean removeNotice(int idx) {
//		System.out.println("NoticeDeleteService - removeNotice");
		boolean deleteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		deleteSuccess = dao.deleteNotice(idx);
//		System.out.println("deleteSuccess : " + deleteSuccess);
		
		if(!deleteSuccess) {
			JdbcUtil.rollback(con);
		} else {
			JdbcUtil.commit(con);
		}
		
		return deleteSuccess;
	}

}
