package svc.community;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CommunityDAO;
import vo.RecipeDTO;
import vo.RecipeReplyDTO;

public class RecipeDetailService {

	public void increaseRecipeReadcount(int idx) {
		System.out.println("RecipeDetailService - increaseRecipeReadcount");
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		// CommunityDAO 객체의 increaseRecipeReadcount 메서드를 호출하여 조회수 증가 작업 수행
		// => 파라미터 : idx
		dao.increaseRecipeReadcount(idx);
		
		// 조회수 증가 작업 commit
		commit(con);
		
		close(con);
		
	}

	public RecipeDTO getRecipe(int idx) {
		System.out.println("RecipeDetailService - getRecipe");
		
		RecipeDTO recipe = null;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		// CommunityDAO 객체의 getRecipe() 메서드를 호출하여 1개 게시물의 상세 정보 조회 작업 수행
		// => 파라미터 : idx   리턴타입 : RecipeDTO(recipe)
		recipe = dao.getRecipe(idx);
		
		close(con);

		return recipe;
	}

	public ArrayList<RecipeReplyDTO> getRecipeReply(int idx) {
System.out.println("RecipeDetailService - getRecipeReply");
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		ArrayList<RecipeReplyDTO> recipeReplyList = dao.selectRecipeReply(idx);
		
		close(con);
		
		
		return recipeReplyList;
	}

	
}
