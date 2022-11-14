package svc.community;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CommunityDAO;

public class RecipeReplyDeleteProService {

	public boolean isDeleteRecipeReply(int reply_idx, String nickname) {
		System.out.println("RecipeReplyDeleteProService - isDeleteRecipeReply");
		boolean isRecipeReplyDeleteSuccess = false;
		
		// 1. Connection 객체 가져오기 - 공통
		Connection con = getConnection();
		
		// 2. CommunityDAO 객체 가져오기 - 공통
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 3. CommunityDAO 객체에 Connection 객체 전달
		dao.setConnection(con);
		
		// CommunityDAO 객체의 deleteRecipeReply() 메서드를 호출하여 삭제 작업 수행
		// => 파라미터 : 글번호, 닉네임    리턴타입 : int(deleteTmiReplyCount)
		int deleteRecipeReplyCount = dao.deleteRecipeReply(reply_idx, nickname);
		System.out.println("RecipeReplyDeleteProService - deleteRecipeReply");
		
		if(deleteRecipeReplyCount > 0) {
			commit(con);
			isRecipeReplyDeleteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isRecipeReplyDeleteSuccess;
	}

}
