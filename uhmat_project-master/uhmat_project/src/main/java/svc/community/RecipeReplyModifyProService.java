package svc.community;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CommunityDAO;

public class RecipeReplyModifyProService {

	public boolean isModifyReplyRecipe(int reply_idx, String nickname, String content) {
		System.out.println("RecipeReplyModifyProService - isModifyReplyRecipe");
		
		boolean isModifySuccess = false;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		int modifyCount = dao.modifyReplyRecipe(reply_idx, nickname, content);
		System.out.println("service : " + modifyCount);
		
		// modifyCount 가 0 보다 크면 commit, 아니면 rollback 작업 수행
		if(modifyCount > 0) {
			commit(con);
		// isDeleteSuccess 값을 true 로 변경하여 성공 표시
			isModifySuccess = true;
		} else {
			rollback(con);
			}
				
		close(con);
			
		return isModifySuccess;
	}

}
