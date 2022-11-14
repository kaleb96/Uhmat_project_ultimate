package svc.review;

import static db.JdbcUtil.*;
import java.sql.*;

import dao.*;

public class CheckHashService {

	public boolean checkHash(String hash) {
			System.out.println("checkHashService");
			
			boolean checkHashSuccess = false;
			
			
			//1. Connection Pool
			Connection con = getConnection();
			
			//2. DAO 객채 가져오기
			ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
			
			//3. DAO 객체에 커넥션 넘겨주기
			dao.setConnection(con);
			
			/*
			 * checkHash 로직
			 * 1.hash 값을 dao 파라미터로 전달
			 * 
			 * 2.hash를 split
			 * 
			 * 3.tag_relation에 해당
			 */
		return checkHashSuccess;
	}

	
}
