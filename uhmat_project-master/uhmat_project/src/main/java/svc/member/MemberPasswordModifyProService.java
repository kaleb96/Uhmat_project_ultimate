package svc.member;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;

public class MemberPasswordModifyProService {

	public boolean modifyPassword(String email, String passwd,String nickname) {
		boolean isModifyPasswordSuccess = false;

		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);

		int updateCount = dao.newPassword(email, passwd,nickname);

		if (updateCount > 0) {
			commit(con);
			isModifyPasswordSuccess = true;
		} else {
			rollback(con);
		}

		close(con);

		return isModifyPasswordSuccess;
	}

	public boolean alterPasswdCheck(String email, String alterPasswd, String nickname) {
		boolean isalterCheckSuccess = false;

		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);

		isalterCheckSuccess = dao.alterPasswdCheck(email, alterPasswd,nickname);

	

		close(con);

		return isalterCheckSuccess;
	}

}
