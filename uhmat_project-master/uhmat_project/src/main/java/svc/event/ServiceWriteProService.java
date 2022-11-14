package svc.event;

import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CommunityDAO;
import dao.EventDAO;
import vo.EventServiceDTO;

public class ServiceWriteProService {

	public boolean registService(EventServiceDTO eventService) {
		System.out.println("ServiceWriteProService - registService");
		
		boolean isServiceWriteSuccess = false;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
		
		// 2. CommunityDAO 객체 가져오기 - 공통
		EventDAO dao = EventDAO.getInstance();
		
		// 3. CommunityDAO 객체에 Connection 객체 전달
		dao.setConnection(con);
		
		// EventDAO 객체의 insertRecipe() 메서드를 호출하여 글쓰기 작업 수행 후 결과 리턴받기
		// => 파라미터 : recipeDTO 객체(recipe)   리턴타입 : int(insertCount)
		int insertCount = dao.insertService(eventService);
		
		// 리턴받은 작업 수행 결과를 통해 판별 후 트랜잭션 처리 작업 수행
		if(insertCount > 0) { // 작업 성공 시
			// 트랜잭션 적용을 위해 JdbcUtil 클래스의 commit() 메서드를 호출하여 commit 작업 수행
			commit(con);
			
			// 작업 처리 결과를 성공으로 표시하기 위해 isServiceWriteSuccess 를 true 로 변경
			isServiceWriteSuccess = true;
		} else { // 작업 실패 시
			// 트랜잭션 취소를 위해 JdbcUtil 클래스의 rollback() 메서드를 호출하여 rollback 작업 수행
			rollback(con);
		}
		
		// Connection 객체 반환 - 공통
		close(con);
		
		return isServiceWriteSuccess;
	}

}
