package svc.community;

import java.sql.Connection;

import dao.CommunityDAO;

import static db.JdbcUtil.*; 

import vo.RecipeDTO;

public class RecipeWriteProService {
	
	public boolean registRecipe(RecipeDTO recipe) {
		System.out.println("RecipeWriteProService");
		
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		// CommunityDAO 객체의 insertRecipe() 메서드를 호출하여 글쓰기 작업 수행 후 결과 리턴받기
		// => 파라미터 : recipeDTO 객체(recipe)   리턴타입 : int(insertCount)
		int insertCount = dao.insertRecipe(recipe);
		
		if(insertCount > 0) {
			commit(con);
			isWriteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isWriteSuccess;
	}

}
