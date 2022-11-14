package svc.serviceCenter;

import java.sql.*;
import java.util.*;

import dao.*;
import db.*;
import vo.*;

public class NoticeSelectAnthingService {

	public int getListCount(String ment) {
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.selectAnythingListcount(ment);
//		System.out.println(listCount);
		JdbcUtil.close(con);
		
		return listCount;
	}

	public ArrayList<NoticeDTO> selectFAQAnthinglist(int pageNum, int listLimit, String ment) {
		ArrayList<NoticeDTO> list = null;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		list = dao.selectAnythingList(pageNum, listLimit, ment);
		
		JdbcUtil.close(con);
		
		return list;
	}

}
