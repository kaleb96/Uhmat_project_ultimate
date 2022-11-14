package svc.member;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.MemberDTO;

public class MemberGoogleJoinProService {
	
	public boolean joinMember(MemberDTO member) { 
		System.out.println("MemberGoogleJoinProService - joinMember");
		boolean isJoinSuccess = false;

		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);

		// (MemberDAO - insertMember() 메서드 호출 - 파라미터 동일, 리턴타입 : int(insertCount)
		// => Service 클래스에서 수행 결과(int)를 사용하여 commit, rollback 판별
		int insertCount = dao.insertMember(member);

		if (insertCount > 0) {
			commit(con);
			isJoinSuccess = true;
		} else {
			rollback(con);
		}

		close(con);

		return isJoinSuccess;
	}

	public boolean loginMember(MemberDTO member) {
		System.out.println("MemberGoogleJoinProService - loginMember ");
		boolean isLoginSuccess = false;

		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);

		isLoginSuccess = dao.selectApiMember(member); 

		close(con);

		return isLoginSuccess;
	}

	public boolean checkApiId(MemberDTO member) {
		System.out.println("MemberGoogleJoinProService -  checkApiId");
		boolean isApiUserSuccess = false;

		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);

		isApiUserSuccess = dao.checkApiId(member);

		close(con);

		return isApiUserSuccess;
	}

}
