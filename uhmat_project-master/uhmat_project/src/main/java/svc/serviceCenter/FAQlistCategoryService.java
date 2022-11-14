package svc.serviceCenter;

import java.sql.*;
import java.util.*;

import dao.*;
import db.*;
import vo.*;

public class FAQlistCategoryService {

	public int getListCount(String category) {
//		System.out.println("FAQListService - getListCount");
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQDAO dao = FAQDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectFAQCategoryListcount(category);
//		System.out.println(listCount);
		JdbcUtil.close(con);
		
		return listCount;
	}
	
	public ArrayList<FAQDTO> selectFAQCategorylist(int pageNum, int listLimit, String category) {
		ArrayList<FAQDTO> list = null;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQDAO dao = FAQDAO.getInstance();
		
		dao.setConnection(con);
		
		list = dao.selectFAQCategory(pageNum, listLimit, category);
		
		JdbcUtil.close(con);
		
		return list;
	}


}
