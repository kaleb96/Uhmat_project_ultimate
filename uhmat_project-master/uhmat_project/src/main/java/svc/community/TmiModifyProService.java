package svc.community;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

import dao.CommunityDAO;
import vo.CommunityTmiDTO;

import static db.JdbcUtil.*;

public class TmiModifyProService {

	// tmi 게시글 수정 권한 판별을 위한 isTmiWriter() 메서드 정의
	public boolean isTmiWriter(int idx, String nickname) {
		System.out.println("TmiModifyProService - isTmiWriter()");
		boolean isTmiWriter = false;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
				
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. BoardDAO 객체에 Connetion 객체 전달하기 - 공통
		dao.setConnection(con);
		
		// CommunityTmiDTO 객체의 isTmiWriter() 메서드를 호출하여 수정 권한 판별 수행
		isTmiWriter = dao.isTmiWriter(idx, nickname);
		
		close(con);
		

		return isTmiWriter;

	}

	// 글 수정 작업 요청을 위한 modifyBoard() 메서드 정의
	public boolean ModifyTmiBoard(CommunityTmiDTO tmiBoard) {
		System.out.println("TmiModifyProService - ModifyTmiBoard()");
		boolean isTmiModifySuccess = false;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
				
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. BoardDAO 객체에 Connetion 객체 전달하기 - 공통
		dao.setConnection(con);
		
		// CommunityTmiDTO의 updateTmiBoard() 메서드를 호출하여 글 수정 작업 진행
		// => 파라미터 : CommunityTmiDTO 객체	리턴타입 : int(updateTmiCount)
		int updateTmiCount = dao.updateTmiBoard(tmiBoard);
		
		// 글 수정 작업 실행 결과를 판별하여 성공 시 commit, 실패 시 rollback
		if(updateTmiCount > 0) {
			commit(con);
			isTmiModifySuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isTmiModifySuccess;
	}
	
}
