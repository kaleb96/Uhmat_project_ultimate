package svc.community;

import vo.ActionForward;
import vo.CommunityTmiDTO;
import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CommunityDAO;

public class TmiWriteProService {

	public boolean registTmiBoard(CommunityTmiDTO tmiBoard) {
		System.out.println("TmiWriteProService");
		boolean isTmiWirteSuccess = false;
		
		// 1. JdbcUtill 클래스로부터 Connection Pool에 저장된 Connection 객체를 가져옵니다. - 공통작업
		// => Service 클래스에서 트랜잭션 관리를 위해 Connection 객체에 접근해야하기 때문입니다.
		Connection con = getConnection(); // svc.class를 만들면 메서드안에는 무조건 Connection 객체가 사용!
		
		// 2. CommunityDAO 클래스로부터 CommunityDAO 인스턴스를 가져와서 저장합니다. - 공통작업
		// => 싱글톤 디자인 패턴으로 생성된 CommunityDAO 인스턴스 가져오기
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. CommunityDAO 인스턴스에 Connection 객체를 전달합니다. - 공통작업
		dao.setConnection(con);
		
		// 4. CommunityDAO 인스턴스의 XXX 메서드 호출하여 요청받은 XXX 작업을 수행하고 결과를 리턴받습니다.
		// CommunityDAO 객체(인스턴스)의 insertTmiBoard() 메서드를 호출하여 글쓰기 작업을 수행 한 후 결과를 리턴받습니다.
		int tmiInsertCount = dao.insertTmiBoard(tmiBoard);
		
		// 5. 리턴받은 작업 수행 결과를 통해 판별 후 트랜잭션 처리 작업을 수행합니다.
		if(tmiInsertCount > 0) { // 작업 성공 시
			// 트랜잭션 적용을 위해 JdbcUtil 클래스의 commit() 메서드를 호출하여 commit 작업을 수행합니다.
			commit(con);
			// 작업 처리 결과를 성공으로 표시하기 위해서 isWriteSuccess를 true로 변경합니다.
			isTmiWirteSuccess = true;
		} else { // 작업 실패 시
			// 트랜잭션 취소를 위해 JdbcUtil 클래스의 rollback() 메서드를 호출하여 rollback 작업을 수행합니다.
			rollback(con);
		}
		
		// 6. Connection 객체 반환 - 공통작업
		close(con);
		
		return isTmiWirteSuccess;
	}

}
