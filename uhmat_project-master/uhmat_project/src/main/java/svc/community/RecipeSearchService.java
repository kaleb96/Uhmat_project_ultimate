package svc.community;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CommunityDAO;
import vo.RecipeDTO;

import static db.JdbcUtil.*;

public class RecipeSearchService {

	public int recipeSearchListCount(String keyword) {
		System.out.println("RecipeSearchService - recipeSearchListCount");
		int listCount = 0;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectRecipeSearchListcount(keyword);
		System.out.println("listCount(service)" + listCount);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<RecipeDTO> recipeSearchList(int pageNum, int listLimit, String keyword) {

		ArrayList<RecipeDTO> recipeSearchList = null;
		
		Connection con = getConnection();
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.setConnection(con);
		
		recipeSearchList = dao.recipeSearchList(pageNum, listLimit, keyword);
		
		close(con);
		
		return recipeSearchList;
	}

}
