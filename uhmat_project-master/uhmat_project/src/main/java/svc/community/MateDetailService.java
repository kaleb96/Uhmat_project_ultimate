package svc.community;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CommunityDAO;
import vo.MateDTO;
import vo.MateReplyDTO;

public class MateDetailService {
	
	// 조회수 증가 작업을 요청하는 increaseReadcount() 메서드
	public void increaseReadcount(int idx) {
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		// CommunityDAO 객체의 increaseReadcount() 메서드를 호출하여 조회수 증가 작업 수행
		// => 파라미터 : idx
		dao.increaseReadcount(idx);
		
		// 조회수 증가 작업 commit
		commit(con);
		
		close(con);
		
		
	}
	
	// 1개 게시물 상세 정보 조회 작업을 요청하는 getMate() 메서드
	public MateDTO getMate(int idx) {
		
		MateDTO mate = null;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		// CommunityDAO 객체의 getMate() 메서드를 호출하여 1개 게시물의 상세 정보 조회 작업 수행
		// => 파라미터 : idx   리턴타입 : MateDTO(mate)
		mate = dao.getMate(idx);
		
		close(con);
		
		return mate;
	}

	public ArrayList<MateReplyDTO> getMateReply(int idx) {
		System.out.println("getMateReply");
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		ArrayList<MateReplyDTO> mateReplyList = dao.selectMateReply(idx);
		
		close(con);
		
		
		return mateReplyList;
	}

}
