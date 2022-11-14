package svc.member;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FAQDAO;
import db.JdbcUtil;
import svc.serviceCenter.FAQListService;
import vo.FAQDTO;

public class MemberFAQListService extends FAQListService {
	public int getListSelectCount(String keyword,String nickName) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQDAO dao = FAQDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectAnythingListcount(keyword);
		System.out.println("getList listCount " + listCount);
		
		JdbcUtil.close(con);
		
		return listCount;
	}
	
	public ArrayList<FAQDTO> getFAQList(int pageNum, int listLimit, String keyword,String nickName) {
		ArrayList<FAQDTO> list = null;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQDAO dao = FAQDAO.getInstance();
		
		dao.setConnection(con);
		
		list = dao.selectAnythingList(pageNum, listLimit, keyword);
		
		JdbcUtil.close(con);
		
		return list;
	}
}
