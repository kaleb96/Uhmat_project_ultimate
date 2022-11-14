package svc.member;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CommunityDAO;
import svc.community.RecipeListProService;
import vo.RecipeDTO;

public class MemberRecipeListService extends RecipeListProService {
	public int recipeCount(String keyword,String nickName) {
		System.out.println("RecipeListProService - recipeCount");
		
		int listCount = 0;
		
		Connection con = getConnection();
				
		CommunityDAO dao = CommunityDAO.getInstance();
				
		dao.setConnection(con);
				
		listCount = dao.selectRecipeCount();
				
		close(con);
				
		return listCount;
	}

	public ArrayList<RecipeDTO> getRecipeList(int pageNum, int listLimit, String keyword,String nickName) {
		System.out.println("RecipeListProService - getRecipeList");
		
		ArrayList<RecipeDTO> recipeList = null;
		
		Connection con = getConnection(); 
						
		CommunityDAO dao = CommunityDAO.getInstance();
						
		dao.setConnection(con);
						
		// 5. CommunityDAO 객체의 selectMateList() 메서드를 호출하여 게시물 목록 조회
		// => 파라미터 : 현재 페이지 번호(pageNum), 페이지 당 게시물 수(listLimit)
		//    리턴타입 : ArrayList<MateDTO> mateList
		recipeList = dao.selectRecipeList(pageNum, listLimit);	
						
		close(con);
		
		return recipeList;
	}
}
