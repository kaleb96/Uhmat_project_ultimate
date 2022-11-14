package svc.review;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;

import dao.ReviewCategoryDAO;
import vo.ReviewBoardDTO;

public class ReviewModifyProService {


	public boolean modifyReview(ReviewBoardDTO dto, String originPath, String realPath) {
		
		System.out.println("ReviewModifyProService - modifyReview()");
		System.out.println("절대경로 : " + realPath + " 파일이름 : " + originPath);
		

		boolean isModifySuccess = false;
		
		// 1. Connection pool
		Connection con = getConnection();
		
		// 2. DAO 객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		// 3. DAO 객체에 Connection 전달
		dao.setConnection(con);
		
		/*

		 * 4. dao의 updateReview를 통해 update 진행
		 * 5. updateCount에 따라 update
		 * -> 성공 시 파일삭제, 커밋, isModifySuccess = true 로 변환
		 * -> 실패 시 rollback 진행
		 */
		
		
		int updateCount = dao.updateReview(dto);

		if(updateCount > 0) {
			Path path = Paths.get(realPath + "/"+ originPath);
			try {
				Files.deleteIfExists(path);
				System.out.println("Delete is success");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Delete is failed");
				e.printStackTrace();
			}
			
			commit(con);
			isModifySuccess = true;
		
		} else {
			rollback(con);
		}		
		close(con);

		return isModifySuccess;
	}

	public boolean registTag(int idx, String tag) {
		
		System.out.println("ReviewModifyProService - registTag()");
		boolean isTagWrite = false;
		// 1. 커넥션 풀 가져오기
		Connection con = getConnection();
		
		// 2. DAO 객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		// 3. DAO 객체에 connection pool 전달하기
		dao.setConnection(con);
		
		// 4. DAO  메서드 호출하기
		int updateCount = dao.updateTag(idx, tag);
		// 5. 결과에 따른 트랜잭션 처리하기
		if(updateCount > 0) {
			//성공시 커밋 & isWriteSuccess 를 true로 변경
			commit(con);
			isTagWrite = true;
			
		} else {
			// 실패시 rollback;
			rollback(con);
		}
		// 6. 커넥션 풀 반환
			close(con);
		
		return isTagWrite;
	}


}
