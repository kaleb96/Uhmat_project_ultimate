package svc.serviceCenter;

import java.sql.*;

import dao.*;
import db.*;
import vo.*;

public class FAQWriteProService {

	public boolean registFAQ(FAQDTO faq) {
//		System.out.println("FAQWriteProService - registFAQ");
		boolean isWriteSuccess = false;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQDAO dao = FAQDAO.getInstance();
		
		dao.setConnection(con);
		
		int insertCount  = dao.insertFAQ(faq);
//		System.out.println("insertCount : " + insertCount);
		
		
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
