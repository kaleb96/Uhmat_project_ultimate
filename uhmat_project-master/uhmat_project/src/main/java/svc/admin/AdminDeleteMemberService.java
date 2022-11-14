package svc.admin;

import java.sql.*;

import dao.*;
import db.*;

public class AdminDeleteMemberService {

	public int deleteMember(String email) {
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		int deleteCount = dao.deleteMember(email);
		
		if(deleteCount > 0) {
			JdbcUtil.commit(con);
		} else {
			JdbcUtil.rollback(con);
		}
		
		JdbcUtil.close(con);
		
		return deleteCount;
	}

}
