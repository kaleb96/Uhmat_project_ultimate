package svc.member;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberDTO;

public class MemberDetailModifyProService {
	public boolean motifyMember(MemberDTO member) {
		boolean isModifyMemberSuccess = false;

		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);

		int updateCount = dao.motifyMember(member);

		if (updateCount > 0) {
			commit(con);
			isModifyMemberSuccess = true;
		} else {
			rollback(con);
		}

		close(con);

		return isModifyMemberSuccess;
	}
}
