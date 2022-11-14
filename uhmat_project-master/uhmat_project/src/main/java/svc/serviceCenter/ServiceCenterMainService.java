package svc.serviceCenter;

import java.sql.*;
import java.util.*;

import dao.*;
import db.*;
import vo.*;

public class ServiceCenterMainService {

	public ArrayList<FAQDTO> getSVFAQList() {
		ArrayList<FAQDTO> faq5 = null;
		
		Connection con = JdbcUtil.getConnection();
		
		FAQDAO dao = FAQDAO.getInstance();

		dao.setConnection(con);
		
		faq5 = dao.selectSVFAQList();
		
		JdbcUtil.close(con);
		
		return faq5;
	}

	public ArrayList<NoticeDTO> getSVNoticeList() {
		ArrayList<NoticeDTO> notice5 = null;
		
		Connection con = JdbcUtil.getConnection();
		
		NoticeDAO dao = NoticeDAO.getInstance();
		
		dao.setConnection(con);
		
		notice5 = dao.selectSVNoticeList();
		
		JdbcUtil.close(con);
		
		return notice5;
	}

}
