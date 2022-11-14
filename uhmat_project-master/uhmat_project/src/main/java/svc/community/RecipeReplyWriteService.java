package svc.community;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.CommunityDAO;
import vo.RecipeReplyDTO;

public class RecipeReplyWriteService {

	public boolean replyRecipe(RecipeReplyDTO recipeReply) {
		System.out.println("RecipeReplyWriteService - replyRecipe");
		
		boolean isReplySuccess = false;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		// CommentDAO 의 insertReplyMate() 메서드 호출하여 답글 등록 작업 수행
		// => 파라미터 : CommentDTO 객체   리턴타입 : int(insertCount)
		int insertCount = dao.insertReplyRecipe(recipeReply);
		
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
