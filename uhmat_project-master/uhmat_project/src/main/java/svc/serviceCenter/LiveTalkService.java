package svc.serviceCenter;

import java.sql.*;
import java.util.*;

import dao.*;
import db.*;
import vo.*;

public class LiveTalkService {

	public boolean insertTalkLive(String talk, String nickname) {
		boolean insertOk = false;
		
		Connection con = JdbcUtil.getConnection();
		
		LiveTalkDAO dao = LiveTalkDAO.getInstance();
		
		dao.setConnection(con);
		
		insertOk = dao.insertTalkLive(talk, nickname);
		
		if(!insertOk) {
			JdbcUtil.rollback(con);
		} else {
			JdbcUtil.commit(con);
		}
		
		JdbcUtil.close(con);
				
		return insertOk;
	}

	public ArrayList<LiveTalkDTO> selectTalkLive() {
		ArrayList<LiveTalkDTO> list = null; 
		
		Connection con = JdbcUtil.getConnection();
		
		LiveTalkDAO dao = LiveTalkDAO.getInstance();
		
		dao.setConnection(con);
		
		list = dao.selectTalkLive();
		
		JdbcUtil.close(con);
		
		return list;
	}


}
