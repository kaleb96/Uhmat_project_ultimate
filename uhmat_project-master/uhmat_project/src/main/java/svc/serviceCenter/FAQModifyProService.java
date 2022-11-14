package svc.serviceCenter;

import java.sql.*;

import dao.*;
import db.*;
import vo.*;

public class FAQModifyProService {

	public boolean modifyFAQ(FAQDTO faq) {
//		System.out.println("FAQModifyProService - modifyFAQ");
		boolean isModifySuccess = false;
		int updateCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQDAO dao = FAQDAO.getInstance();
		
		dao.setConnection(con);
		
		updateCount = dao.updateFAQ(faq);
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
