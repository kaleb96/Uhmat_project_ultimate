package svc.member;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.*;

import dao.*;  
  
public class MemberSendAuthMailService {

	public boolean registAuthCode(String emial, String authCode) {
		boolean isRegistSuccess = false;
		
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		// MemberDAO 객체의 registAuthCode() 메서드를 호출하여 인증코드 등록 작업 수행
		// => 파라미터 : 아이디, 인증코드   리턴타입 : int(registCount)
		int registCount = dao.registAuthCode(emial, authCode);
		
		if(registCount > 0) {
			commit(con);
			isRegistSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isRegistSuccess;
	}

}