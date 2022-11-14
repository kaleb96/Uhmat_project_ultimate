package svc.community;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CommunityDAO;
import vo.MateDTO;

public class MateModifyProService {
	// 글 수정
	public boolean modifyMate(MateDTO mate) {
		
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		// MateDAO 의 updateMate() 메서드를 호출하여 글 수정 작업 수행
		// => 파라미터 : MateDTO 객체   리턴타입 : int(updateCount)
		int updateCount = dao.updateMate(mate);
		
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isModifySuccess;
	}

}
