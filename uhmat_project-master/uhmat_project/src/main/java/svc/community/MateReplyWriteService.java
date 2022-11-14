package svc.community;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CommunityDAO;
import vo.MateDTO;
import vo.MateReplyDTO;

public class MateReplyWriteService {

	public boolean replyMate(MateReplyDTO mateReply) {

		boolean isReplySuccess = false;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		// CommentDAO 의 insertReplyMate() 메서드 호출하여 답글 등록 작업 수행
		// => 파라미터 : CommentDTO 객체   리턴타입 : int(insertCount)
		int insertCount = dao.insertReplyMate(mateReply);
		
		if(insertCount > 0) {
			commit(con);
			isReplySuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		return isReplySuccess;
	}

}
