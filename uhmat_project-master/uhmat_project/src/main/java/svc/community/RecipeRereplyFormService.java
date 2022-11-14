package svc.community;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CommunityDAO;
import vo.RecipeReplyDTO;

public class RecipeRereplyFormService {

	public RecipeReplyDTO selectReply(int reply_idx) {
		System.out.println("MateRereplyFormService - selectReply");

		RecipeReplyDTO recipeReply = null;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		recipeReply = dao.selectRecipeRereply(reply_idx);
		
		close(con);
		
		return recipeReply;
	}

}
