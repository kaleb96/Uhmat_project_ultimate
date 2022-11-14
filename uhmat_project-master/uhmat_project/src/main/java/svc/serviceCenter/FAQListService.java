package svc.serviceCenter;

import java.sql.*;
import java.util.*;

import dao.*;
import db.*;
import vo.*;

public class FAQListService {

	public int getListSelectCount(String keyword) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQDAO dao = FAQDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectAnythingListcount(keyword);
		System.out.println("getList listCount " + listCount);
		
		JdbcUtil.close(con);
		
		return listCount;
	}
	
	public ArrayList<FAQDTO> getFAQList(int pageNum, int listLimit, String keyword) {
		ArrayList<FAQDTO> list = null;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQDAO dao = FAQDAO.getInstance();
		
		dao.setConnection(con);
		
		list = dao.selectAnythingList(pageNum, listLimit, keyword);
		
		JdbcUtil.close(con);
		
		return list;
	}

	

}
