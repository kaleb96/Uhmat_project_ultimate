package svc.serviceCenter;

import java.sql.*;

import dao.*;
import db.*;
import vo.*;

public class NoticeWriteProService {

	public boolean registNotice(NoticeDTO notice) {
//		System.out.println("NoticeWriteProService - registNotice");
		boolean isWriteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount  = dao.insertNotice(notice);
//		System.out.println(insertCount);
		
		if(insertCount > 0) {
			JdbcUtil.commit(con);
			isWriteSuccess = true;
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return isWriteSuccess;
	}

}
