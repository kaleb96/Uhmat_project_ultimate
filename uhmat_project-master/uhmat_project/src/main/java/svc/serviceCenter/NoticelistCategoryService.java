package svc.serviceCenter;

import java.sql.*;
import java.util.*;

import dao.*;
import db.*;
import vo.*;

public class NoticelistCategoryService {

	public int getListCount(String category) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectNoticeCategoryListcount(category);
//		System.out.println(listCount);
		JdbcUtil.close(con);
		
		return listCount;
	}

	public ArrayList<NoticeDTO> selectNoticeCategorylist(int pageNum, int listLimit, String category) {
		ArrayList<NoticeDTO> list = null;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		list = dao.selectNoticeCategory(pageNum, listLimit, category);
//		System.out.println(listCount);
		JdbcUtil.close(con);
		
		return list;
	}

}
