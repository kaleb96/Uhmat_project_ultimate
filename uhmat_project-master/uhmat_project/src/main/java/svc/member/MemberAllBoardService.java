package svc.member;

import java.sql.Connection;
import java.util.ArrayList;


import dao.MemberDAO;
import db.JdbcUtil;


public class MemberAllBoardService {
	public int getMemberListSelectCount(String title,String keyword,String nickName) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectAnythingListcount(keyword,title,nickName);
		System.out.println("getList listCount " + listCount);
		
		JdbcUtil.close(con);
		
		return listCount;
	}
	
	public ArrayList getMemberBoardList(int pageNum, int listLimit,String title, String keyword,String nickName) {
		ArrayList list = null;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance(); 
		
		dao.setConnection(con);
		
		list = dao.selectAnythingList(pageNum, listLimit,title, keyword,nickName); 
		
		JdbcUtil.close(con);
		
		return list;
	}
}
