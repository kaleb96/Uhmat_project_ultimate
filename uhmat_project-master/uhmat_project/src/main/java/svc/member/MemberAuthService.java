package svc.member;

import java.sql.Connection;

import dao.AuthInfoDAO;
import vo.AuthInfoDTO;

import static db.JdbcUtil.*;
public class MemberAuthService {

	public boolean isAuthentication(AuthInfoDTO authInfo) {
		boolean isAuthenticationSuccess =false;
		System.out.println("isAuthentication");
		Connection con = getConnection();
		AuthInfoDAO dao = AuthInfoDAO.getInstance();
		dao.setConnection(con);
		
		isAuthenticationSuccess=dao.isAuthentication(authInfo);
		
		if (isAuthenticationSuccess) {
			commit(con);
		}else {
			rollback(con);
		}
		
		
		close(con);
	
		return isAuthenticationSuccess;
	}

}
