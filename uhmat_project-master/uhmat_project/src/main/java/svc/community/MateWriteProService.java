package svc.community;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CommunityDAO;
import vo.MateDTO;

public class MateWriteProService {


	public boolean registMate(MateDTO mate) {
		System.out.println("MateWriteProService");
//		System.out.println("깃은 왜 고친것만 커밋 될까?");
		
		// 1. 글쓰기 작업 요청 처리 결과를 판별하여 리턴하기 위한 boolean 타입 변수 선언
		boolean isWriteSuccess = false;
		
		// 2. JdbcUtil 클래스로부터 Connection Pool 에 저장된 Connection 객체 가져오기 - 공통
		Connection con = getConnection();
		
		// 3. CommunityDAO 클래스로부터 BoardDAO 인스턴스 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 4. BoardDAO 인스턴스에 Connection 객체 전달하기 - 공통
		dao.setConnection(con);
		
		// 5. CummunityDAO 객체의 mateInsertCount() 메서드를 호출하여 글쓰기 작업 수행 후 결과 리턴받기
		// => 파라미터 : MateDTO 객체(mate)   리턴타입 : int(insertCount)
		int insertCount = dao.mateInsertCount(mate);
		
		// 6. 
		if(insertCount > 0) { // 작업 성공 시
			// 트랜잭션 적용을 위해 JdbcUtil 클래스의 commit() 메서드를 호출하여 commit 작업 수행
			commit(con);
			// 작업 처리 결과를 성공으로 표시하기 위해 isWriteSuccess 를 true 로 변경
			isWriteSuccess = true;
		} else { // 작업 실패 시
			// 트랜잭션 취소를 위해 JdbcUtil 클래스의 rollback() 메서드를 호출하여 rollback 작업 수행
			rollback(con);
		}
		
		// 7. Connection 객체 반환 - 공통
		close(con);
		
		// 8. 작업 수행 결과 리턴
		return isWriteSuccess;
	}

}
