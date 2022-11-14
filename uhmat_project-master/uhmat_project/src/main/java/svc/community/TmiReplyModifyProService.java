package svc.community;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CommunityDAO;
import vo.TmiReplyDTO;
import static db.JdbcUtil.*;

public class TmiReplyModifyProService {

	// 수정 권한 판별을 위한 isTmiReplyWriter() 메서드 정의
	public boolean isTmiReplyWriter(int idx, String nickname) {
		System.out.println("TmiReplyModifyProService - isTmiReplyWriter");
		boolean isTmiReplyWriter = false;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
		
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. CommunityDAO 객체에 Connection 객체 전달
		dao.setConnection(con);
		
		// CommunityDAO 객체의 isTmiReplyWriter() 메서드를 호출 수정 권한 판별 수행!
		isTmiReplyWriter = dao.isTmiReplyWriter(idx, nickname);
		
		return isTmiReplyWriter;
	}

	// 댓글 수정 작업 요청을 위한 modifyTmiReply() 메서드 정의
	public boolean modifyTmiReply(TmiReplyDTO tmiReply) {
		System.out.println("TmiReplyModifyProService - modifyTmiReply");
		boolean isTmiReplyModifySuccess = false;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
		
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. CommunityDAO 객체에 Connection 객체 전달
		dao.setConnection(con);
		
		// CommunityDAO 객체의 isTmiReplyWriter() 메서드를 호출하여 수정 권한 판별 수행
		int tmiReplyUpdateCount = dao.updateTmiReply(tmiReply);
		
		// 댓글 수정 작업 실행 결과 판별하여 성공 시 commit, 실패 시 rollback
		if(tmiReplyUpdateCount > 0) {
			commit(con);
			isTmiReplyModifySuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isTmiReplyModifySuccess;
	}

}
