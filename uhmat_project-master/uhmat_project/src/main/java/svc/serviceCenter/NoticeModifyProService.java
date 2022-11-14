package svc.serviceCenter;

import java.sql.*;

import dao.*;
import db.*;
import vo.*;

public class NoticeModifyProService {

	public boolean modifyNotice(NoticeDTO notice) {
//		System.out.println("NoticeModifyProService - modifyNotice");
		boolean isModifySuccess = false;
		int updateCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		updateCount = dao.updateNotice(notice);
//		System.out.println("updateCount " + updateCount);
		
		if(updateCount > 0) {
			isModifySuccess = true;
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isModifySuccess;
	}

}
