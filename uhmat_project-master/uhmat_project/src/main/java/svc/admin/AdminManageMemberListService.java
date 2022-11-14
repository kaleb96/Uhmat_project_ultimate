package svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.*;
import java.util.*;

import dao.*;
import db.*;
import vo.*;

public class AdminManageMemberListService {

	public int getMemberListSelectCount(String keyword) {
//		System.out.println("getMemberListSelectCount");
		int listCount = 0;
		
		Connection con = JdbcUtil.getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		listCount = dao.SelectMemberListCount(keyword);
				
		JdbcUtil.close(con);
		
		return listCount;
	}
	
	public ArrayList<MemberDTO> getMemberList(int pageNum, int listLimit, String keyword) {
//		System.out.println("getMemberList");
		ArrayList<MemberDTO> list= null;
		
		Connection con = getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		list = dao.AdminSelectMemberList(pageNum, listLimit, keyword);
//		System.out.println("AdminManageMemberListService의 list :" + list);
		close(con);
		
		return list;
	}

	public MemberDTO getMember(String nickname) {
		MemberDTO member = null;
		
		Connection con = getConnection();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		dao.setConnection(con);
		
		member = dao.selectMember(nickname);
		
		close(con);
		
		return member;
	}





}
