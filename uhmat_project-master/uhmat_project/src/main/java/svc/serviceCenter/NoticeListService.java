package svc.serviceCenter;

import java.sql.*;
import java.util.*;

import dao.*;
import db.*;
import vo.*;

public class NoticeListService {

	public int getListSelectCount(String keyword) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectAnythingListcount(keyword);
//		System.out.println(listCount);
		JdbcUtil.close(con);
		
		return listCount;
	}
	
	public ArrayList<NoticeDTO> getNoticeList(int pageNum, int listLimit, String keyword) {
		ArrayList<NoticeDTO> list = null;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		list = dao.selectAnythingList(pageNum, listLimit, keyword);
		
		JdbcUtil.close(con);
		
		return list;
	}

}
