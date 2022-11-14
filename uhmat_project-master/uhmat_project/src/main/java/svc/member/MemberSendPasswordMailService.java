package svc.member;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberSendPasswordMailService {
	public boolean newPassword(String email, String passwd,String nickname) {
		boolean isNewPasswordSuccess = false;
		
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
	
		int registCount = dao.newPassword(email, passwd,nickname);
		
		if(registCount > 0) {
			commit(con);
			isNewPasswordSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isNewPasswordSuccess;
	}
}
