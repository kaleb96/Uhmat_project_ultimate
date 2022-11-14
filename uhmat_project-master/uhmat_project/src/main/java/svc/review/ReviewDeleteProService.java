package svc.review;
import static db.JdbcUtil.*;

import java.io.*;
import java.nio.file.*;
import java.sql.*;

import dao.*;
public class ReviewDeleteProService {

	public boolean isWriter(String pass) {
		System.out.println("ReviewDeleteProService - isWriter()");
		boolean isWriter = false;
		
		//Connection Pool 가져오기
		Connection con = getConnection();
		
		//DAO 객제 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		//DAO 객체에 Connection 주기
		dao.setConnection(con);
		
//		isWriter = dao.checkPassword(pass); 멤버랑 연동시 사용 가능
//		isWriter = dao.checkPassword(idx, pass); // 회원비밀번호와 연동하여 비밀번호를 체크해줘야함
		isWriter= true;
		/*
		 * 확인 후 맞을 시
		 * -> isWriter = true, 커넥션풀 반환
		 * 
		 * 틀릴 시 isWriter는 false임으로 그래도 리턴
		 */
		if(isWriter) {
			close(con);
			
		}
		return isWriter;
	}

	public boolean isDeleteSuccess(int idx, String filePath, String fileName) {
		System.out.println("RevoewDeleteProService - isDeleteSuccess");
		boolean isDeleteSuccess = false;
		
		//Connection 풀
		Connection con = getConnection();
		
		//DAO객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		//dao에 커넥션 전달하기
		dao.setConnection(con);
		
		//dao deleteReview(idx) 수행하기
		int deleteCount = dao.deleteReview(idx);
		
		/*
		 * delete 성공시(deleteCount > 0)
		 * -> 
		 * isDeleteSuccess = true
		 * 파일 삭제
		 * commit
		 * 
		 * delete 실패시
		 * rollback
		 */
		if(deleteCount > 0) {
			
			Path path = Paths.get(filePath + "/" + fileName);
			try {
				Files.deleteIfExists(path);
				System.out.println("fileDelete is success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("fileDelete is failed");
			}
			
			commit(con);
			isDeleteSuccess = true;
			
		} else {
			rollback(con);
		}
		close(con);
		return isDeleteSuccess;
	}


}
