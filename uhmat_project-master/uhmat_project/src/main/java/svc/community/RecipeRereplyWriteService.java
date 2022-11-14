package svc.community;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CommunityDAO;
import vo.RecipeReplyDTO;

public class RecipeRereplyWriteService {

	public boolean writeRecipeRereply(RecipeReplyDTO recipeRereply) {
		System.out.println("RecipeRereplyWriteService - writeRecipeRereply");
		
		boolean isRecipeRereplySuccess = false;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		// CommunityDAO 객체(인스턴스)의 insertMateRereply() 메서드를 호출하여 댓글쓰기 작업을 수행 한 후 결과를 리턴
		int RecipeRereplyInsertCount = dao.insertRecipeRereply(recipeRereply);
		
		if(RecipeRereplyInsertCount > 0) {
			commit(con);
			isRecipeRereplySuccess = true;
			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isRecipeRereplySuccess;
	}
	
}
