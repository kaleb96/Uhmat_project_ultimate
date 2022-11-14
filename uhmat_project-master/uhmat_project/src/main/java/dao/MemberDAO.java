package dao;

import static db.JdbcUtil.close;

import java.sql.*;
import java.util.*;

import db.*;
import vo.*;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private static Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		return instance;
	}

	public boolean selectMember(MemberDTO member) {
		boolean isLoginSuccess = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM member WHERE email=? AND passwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPasswd());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				isLoginSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDAO - selectMember() 메서드 오류 : " + e.getMessage());
		} finally {
			close(pstmt);
			close(rs);
		}

		return isLoginSuccess;
	}

	public boolean selectApiMember(MemberDTO member) {
		boolean isLoginSuccess = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM member WHERE email=? AND api_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getApi_id());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				isLoginSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDAO - selectMember() 메서드 오류 : " + e.getMessage());
		} finally {
			close(pstmt);
			close(rs);
		}

		return isLoginSuccess;
	}

	public int insertMember(MemberDTO dto) {
		System.out.println("insertMember");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int insertCount = 0;
		String sql = "";
		
		try {
			sql = "INSERT INTO member VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getNickname());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setDate(5, dto.getBirthdate());
			pstmt.setString(6, dto.getPostCode());
			pstmt.setString(7, dto.getAddress1());
			pstmt.setString(8, dto.getAddress2());
			pstmt.setString(9, "1-1.jpg");
			pstmt.setString(10, dto.getAuth_status());
			pstmt.setString(11, dto.getApi_id());
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			close(pstmt);
		}

		return insertCount;

	}

	public boolean checkUser(MemberDTO dto) throws Exception {
		boolean isLoginSuccess = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 3단계. SQL 구문 작성 및 전달
			// => 전달받은 아이디, 패스워드를 사용하여 일치하는 레코드 조회
			String sql = "SELECT * FROM auth_member WHERE email=? AND passwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPasswd());

			// 4단계. SQL 구문 실행 및 결과 처리
			rs = pstmt.executeQuery();

			// ResultSet 객체의 다음 레코드가 존재할 경우 = 로그인 성공
			if (rs.next()) {
				// 조회 성공 시(= 로그인 성공) isLoginSuccess 값을 true 로 변경
				isLoginSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반환
			close(rs);
			close(pstmt);
		}

		return isLoginSuccess;
	}

	public int registAuthCode(String email, String authCode) {
		int registCount = 0;

		PreparedStatement pstmt = null, pstmt2 = null;
		ResultSet rs = null;

		try {
			// 전달받은 ID 에 대한 기존 인증코드가 존재하는지 확인 후
			// 만약, 기존 인증코드가 존재하면 새 인증코드로 덮어쓰고(UPDATE),
			// 아니면, 아이디와 인증코드를 새로 등록(INSERT)
			String sql = "SELECT auth_code FROM auth_info WHERE email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) { // 기존 인증코드가 이미 존재하는 경우
				// UPDATE 구문을 사용하여 인증코드 갱신(새로운 인증코드로 덮어씀)
				sql = "UPDATE auth_info SET auth_code=? WHERE email=?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, authCode);
				pstmt2.setString(2, email);
				registCount = pstmt2.executeUpdate();
				System.out.println("인증코드 갱신 성공!");
			} else { // 기존 인증코드가 존재하지 않는 경우
				// INSERT 구문을 사용하여 아이디와 인증코드 추가
				sql = "INSERT INTO auth_info VALUES (?,?)";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, email);
				pstmt2.setString(2, authCode);
				registCount = pstmt2.executeUpdate();
				System.out.println("인증코드 등록 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(pstmt2);
		}

		return registCount;
	}

	public boolean selectDuplicateNickName(String nickName) {
		System.out.println("selectDuplicateNickName");
		boolean isDuplicate = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			System.out.println(nickName);
			String sql = "SELECT nickName FROM member WHERE nickName=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();

			if (rs.next()) { // 아이디가 이미 존재할 경우
				isDuplicate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDAO - selectDuplicateNickName() 메서드 오류 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return isDuplicate;
	}

	public boolean selectDuplicateEmail(String email) {
		boolean isDuplicate = false;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT email FROM member WHERE email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) { // 아이디가 이미 존재할 경우
				isDuplicate = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDAO - selectDuplicateEmail() 메서드 오류 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return isDuplicate;
	}

	public boolean isAuthenticatedUser(MemberDTO member) {
		System.out.println("isAuthenticatedUser_DAO");
		boolean isAuthenticatedUserSuccess = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT auth_status FROM member WHERE email=? AND passwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPasswd());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals("Y")) {
					isAuthenticatedUserSuccess = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDAO - selectMember() 메서드 오류 : " + e.getMessage());
		} finally {
			close(pstmt);
			close(rs);
		}

		return isAuthenticatedUserSuccess;
	}

	public boolean checkApiId(MemberDTO member) {
		System.out.println("checkApiId");
		boolean isApiUserSuccess = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT api_id FROM member WHERE email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getEmail());

			rs = pstmt.executeQuery();
			if (rs.next()) {

				isApiUserSuccess = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("MemberDAO - selectMember() 메서드 오류 : " + e.getMessage());
		} finally {
			close(pstmt);
			close(rs);
		}

		return isApiUserSuccess;
	}

	public int newPassword(String email, String passwd,String nickname) {
		int updateCount = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE member SET passwd=? WHERE email=? or nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, passwd);
			pstmt.setString(2, email);
			pstmt.setString(3, nickname);
			updateCount = pstmt.executeUpdate();
			System.out.println("패스워드 갱신 성공!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			close(pstmt);

		}

		return updateCount;
	}

	public MemberDTO selectMember(String nickName) {
		System.out.println("selectMember");
		MemberDTO member = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM member WHERE nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberDTO();
				member.setName(rs.getString("name"));
				member.setNickname(rs.getString("nickname"));
				member.setEmail(rs.getString("email"));
				member.setBirthdate(rs.getDate("birthdate"));
				member.setPostCode(rs.getString("postcode"));
				member.setAddress1(rs.getString("address1"));
				member.setAddress2(rs.getString("address2"));
				member.setIcon(rs.getString("icon"));
				System.out.println("내정보:"+member);
//				System.out.println(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 오류 - selectMember() : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return member;
	}
	public MemberDTO getNickname(String email) {
//		System.out.println("selectMember");
		MemberDTO member = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM member WHERE email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberDTO();
				member.setNickname(rs.getString("nickname"));
				member.setIcon(rs.getString("icon"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 오류 - selectMember() : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return member;
	}

	public int motifyMember(MemberDTO member) {
		System.out.println("motifyMember");
		int updateCount = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "UPDATE member SET nickname=?,name=?,birthdate=?, postcode=?,address1=?,address2=?,icon=? WHERE email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getNickname());
			pstmt.setString(2, member.getName());
			pstmt.setDate(3, member.getBirthdate());
			pstmt.setString(4, member.getPostCode());
			pstmt.setString(5, member.getAddress1());
			pstmt.setString(6, member.getAddress2());
			pstmt.setString(7, member.getIcon());
			pstmt.setString(8, member.getEmail());

			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 오류 - updatemember() : " + e.getMessage());
		} finally {
			close(pstmt);
		}

		return updateCount;
	}

	public ArrayList<MemberDTO> AdminSelectMemberList(int pageNum, int listLimit, String keyword) {
//		System.out.println("AdminSelectMemberList");
		ArrayList<MemberDTO> list = null;
		int listCount =0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		int startRow = (pageNum- 1) * listLimit;
		
		try {
			String sql = "SELECT * FROM member WHERE name LIKE ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%"); 
				
			rs = pstmt.executeQuery();
			
			list = new ArrayList<MemberDTO>();
			
			while(rs.next()) {
				System.out.println("rs.next()");
				sql = "SELECT ((select Count(subject) from community_mate as m where m.nickname=ma.nickname)+(select Count(subject) from community_tmi as t where t.nickname=ma.nickname)+(select Count(subject) from community_recipe as r where r.nickname=ma.nickname)+(select Count(subject) from reviewboard as v where v.nickname=ma.nickname)+(select Count(subject) from faqboard as f where f.nickname=ma.nickname)+(select Count(subject) from noticeboard as n where n.nickname=ma.nickname)) as NUM FROM member as ma WHERE ma.nickname =?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rs.getString("nickname"));
				rs2 = pstmt.executeQuery();
				
				if(rs2.next()) {
					System.out.println("rs2.next()");
					listCount = rs2.getInt(1);
					sql = "SELECT * FROM member WHERE name LIKE ? LIMIT ?,? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%" + keyword + "%"); 
					pstmt.setInt(2, startRow);
					pstmt.setInt(3, listLimit);
					rs3 = pstmt.executeQuery();
					
					
					if(rs3.next()) {
						System.out.println("rs3.next()");
						MemberDTO member = new MemberDTO();
						member.setName(rs.getString("name"));
						member.setNickname(rs.getString("nickname"));
						member.setEmail(rs.getString("email"));
						member.setBirthdate(rs.getDate("birthdate"));
						member.setPostCode(rs.getString("postcode"));
						member.setAddress1(rs.getString("address1"));
						member.setAddress2(rs.getString("address2"));
						member.setBoardCount(listCount);
						
						System.out.println("member : " + member);
						
						list.add(member);
					}
				}	
			}
//			System.out.println("AdminSelectMemberList의 list :"  + list);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 오류 AdminSelectMemberList() :" + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public int SelectMemberListCount(String keyword) {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM member WHERE name LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%" );
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
//			System.out.println("listCount : " + listCount);
			
		} catch (SQLException e) {
			System.out.println("SQL 구문 오류 발생! -  " + e.getMessage());
			e.printStackTrace();
		} finally {
			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return listCount;
	}

	public int deleteMember(String email) {
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "DELETE FROM member WHERE email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			
			deleteCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
		return deleteCount;
	}

	public int selectAnythingListcount(String keyword,String title,String nickName) {
		
		int listCount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if (title.equals("FAQ")) {
			title="FAQBoard";
			System.out.println("FAQ - selectAnythingListcount() 호출!");
		}
		if (title.equals("Mate")) { 
			title="community_Mate";
			System.out.println("Mate - selectAnythingListcount() 호출!");
		}
		if (title.equals("Tmi")) { 
			title="community_Tmi";
			System.out.println("Tmi - selectAnythingListcount() 호출!");
		}
		if (title.equals("Recipe")) {
			title="community_Recipe";
			System.out.println("Recipe - selectAnythingListcount() 호출!");

		}
		// 리스트와 검색창을 동시에 수행하는 메서드
		// sql 구문 중요!
		try {
			String sql = "SELECT COUNT(*) FROM "+title+" WHERE nickname=? and subject LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);

			}
			System.out.println("selectTmiListCount() - " + listCount);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 발생! - selectAnythingListcount() " + e.getMessage());

		} finally {
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public ArrayList selectAnythingList(int pageNum, int listLimit, String title, String keyword,String nickName) {
		System.out.println("----------------------------------------------");
	
		ArrayList BoardList=null;
		if (title.equals("FAQ")) {
			title="FAQBoard";
			BoardList = new ArrayList<FAQDTO>();
			System.out.println("FAQ - selectAnythingList() 호출!");
		}
		if (title.equals("Mate")) { 
			title="community_Mate";
			BoardList = new ArrayList<MateDTO>();
			System.out.println("Mate - selectAnythingList() 호출!");
		}
		if (title.equals("Tmi")) { 
			title="community_Tmi";
			BoardList = new ArrayList<CommunityTmiDTO>();
			System.out.println("Tmi - selectAnythingList() 호출!");
		}
		if (title.equals("Recipe")) {
			title="community_Recipe";
			BoardList = new ArrayList<RecipeDTO>();
			System.out.println("Recipe - selectAnythingList() 호출!");

		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 현재 페이지 번호를 활용하여 조회 시 시작행 번호를 계산
		int startRow = (pageNum - 1) * listLimit;

		// tmi게시판 전체리스트와 검색 기능을 함께 사용하는 SQL 구문!
		try {
			String sql = "SELECT * FROM "+title+" WHERE nickname=? and subject LIKE ? ORDER BY idx DESC LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  nickName );
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, listLimit);

			rs = pstmt.executeQuery();


			// while문을 사용하여 조회 결과에 대한 반복 작업을 수행.
			while (rs.next()) {
				
				if (title.equals("FAQBoard")) {
					FAQDTO Board = new FAQDTO();
					Board.setIdx(rs.getInt("idx"));
						Board.setNickname(rs.getString("nickname"));
						Board.setSubject(rs.getString("subject"));
						Board.setContent(rs.getString("content"));
						Board.setDate(rs.getDate("date"));
						BoardList.add(Board);
						System.out.println("FAQ - selectAnythingList() 호출!");
				}
				if (title.equals("community_Mate")) { 
				
					MateDTO Board = new MateDTO();
					Board.setIdx(rs.getInt("idx"));
					Board.setNickname(rs.getString("nickname"));
					Board.setSubject(rs.getString("subject"));
					Board.setContent(rs.getString("content"));
					Board.setDate(rs.getTimestamp("datetime"));
					System.out.println("Mate - selectAnythingList() 호출!");
				

					BoardList.add(Board);
				}
				if (title.equals("community_Tmi")) { 
					CommunityTmiDTO Board = new CommunityTmiDTO();
					Board.setIdx(rs.getInt("idx"));
					Board.setNickname(rs.getString("nickname"));
					Board.setSubject(rs.getString("subject"));
					Board.setContent(rs.getString("content"));
					Board.setDate(rs.getTimestamp("datetime"));
					System.out.println("Tmi - selectAnythingList() 호출!");
					BoardList.add(Board);

				
				}
				if (title.equals("community_Recipe")) {
					
					RecipeDTO Board = new RecipeDTO();
					Board.setIdx(rs.getInt("idx"));
					Board.setNickname(rs.getString("nickname"));
					Board.setSubject(rs.getString("subject"));
					Board.setContent(rs.getString("content"));
					Board.setDate(rs.getTimestamp("datetime"));
					BoardList.add(Board);
					System.out.println("Recipe - selectAnythingList() 호출!");

				}
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("SQL구문 오류 발생! - selectAnythingList" + e.getMessage());

		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("selectAnythingList - " + BoardList);
		return BoardList;
	}

	public boolean alterPasswdCheck(String email, String alterPasswd, String nickname) {
		boolean isPasswdCheck = false;

		PreparedStatement pstmt = null;
		ResultSet rs=null;

		try {
			String sql = "SELECT * from member where passwd=? and (email=? or nickname=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, alterPasswd);
			pstmt.setString(2, email);
			pstmt.setString(3, nickname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isPasswdCheck=true;
				
			}
			System.out.println("체크 성공!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			close(pstmt);

		}

		return isPasswdCheck;
	}

	public int deleteBoard(int idx, String title) {
		if (title.equals("FAQ")) {
			title="FAQBoard";
			System.out.println("FAQ - selectAnythingList() 호출!");
		}
		if (title.equals("Notice")) {
			title="noticeboard";
			System.out.println("FAQ - selectAnythingList() 호출!");
		}
		if (title.equals("Mate")) { 
			title="community_Mate";
			System.out.println("Mate - selectAnythingList() 호출!");
		}
		if (title.equals("Tmi")) { 
			title="community_Tmi";
			System.out.println("Tmi - selectAnythingList() 호출!");
		}
		if (title.equals("Recipe")) {
			title="community_Recipe";
			System.out.println("Recipe - selectAnythingList() 호출!");

		}
		int deleteCount = 0;

		PreparedStatement pstmt = null;

		try {
			String sql = "DELETE FROM "+title+" WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);

			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 오류 - deleteMate() : " + e.getMessage());
		} finally {
			close(pstmt);
		}

		return deleteCount;
	
	}


}