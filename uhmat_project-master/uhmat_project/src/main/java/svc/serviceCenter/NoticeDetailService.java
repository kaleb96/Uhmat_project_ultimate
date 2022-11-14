package svc.serviceCenter;

import java.sql.*;

import dao.*;
import db.*;
import vo.*;

public class NoticeDetailService {

	public NoticeDTO getNotice(int idx) {
//		System.out.println("NoticeDetailService - getNoticeBoard");
		NoticeDTO notice = null;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		notice = dao.selectNotice(idx);
//		System.out.println("notice : " + notice);
		
		JdbcUtil.close(con);
		
		return notice;
	}

}
