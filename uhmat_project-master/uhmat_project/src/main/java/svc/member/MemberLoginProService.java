package svc.member;

import java.sql.Connection;
import static db.JdbcUtil.*;  
import dao.MemberDAO;
import vo.MemberDTO;

public class MemberLoginProService {

	public boolean loginMember(MemberDTO member) {
		boolean isLoginSuccess = false;
		
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		isLoginSuccess = dao.selectMember(member);
		
		close(con);
		
		return isLoginSuccess;
	}

	public boolean isAuthenticatedUser(MemberDTO member) {
		System.out.println("isAuthenticatedUser");
		boolean isAuthenticatedUserSuccess = false;
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		isAuthenticatedUserSuccess=dao.isAuthenticatedUser(member);
		System.out.println(isAuthenticatedUserSuccess);
		close(con);
		return isAuthenticatedUserSuccess;
	}

	public MemberDTO getMember(String email) {
		MemberDTO member = null;
		
		Connection con = getConnection();
		MemberDAO dao = MemberDAO.getInstance();
		dao.setConnection(con);
		
		member = dao.getNickname(email);
		
		close(con);
		
		return member;
	}

}
