package svc.community;

import java.sql.Connection;

import dao.CommunityDAO;
import vo.MateReplyDTO;

import static db.JdbcUtil.*;

public class MateRereplyFormService {

	public MateReplyDTO selectReply(int reply_idx) {
		System.out.println("MateRereplyFormService - selectReply");

		MateReplyDTO mateReply = null;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		mateReply = dao.selectRereply(reply_idx);
		
		close(con);
		
		return mateReply;
	}

}
