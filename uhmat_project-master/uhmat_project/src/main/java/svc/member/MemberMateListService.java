package svc.member;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CommunityDAO;
import svc.community.MateListProService;
import vo.MateDTO;

public class MemberMateListService extends MateListProService {
public int mateCount(String keyword,String nickName) {
		
		// 1. 리턴할 데이터를 저장할 변수 선언
		int listCount = 0;
		
		// 2. Connection 객체 가져오기
		Connection con = getConnection();
		
		// 3. CommunityDAO 객체 가져오기
		CommunityDAO dao = CommunityDAO.getInstance();
		
		// 4. CommunityDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
		
		// 5. CommunityDAO 객체의 selectMateCount() 메서드 호출하여 전체 게시물 수 조회
		listCount = dao.selectMateCount(keyword);
		
		// 6. Connection 객체 반환
		close(con);
		
		// 7. 조회 결과 리턴
		return listCount;
	}
	
	// ------------------------------------------------------------------------------
	// 전체 게시물 목록 조회 작업을 요청할 getMateList() 메서드 정의
	public ArrayList<MateDTO> getMateList(int pageNum, int listLimit, String keyword,String nickName) {
		
		// 1. 리턴할 데이터를 저장할 변수 선언
		ArrayList<MateDTO> mateList = null;
		 
		// 2. Connection 객체 가져오기
		Connection con = getConnection(); 
				
		// 3. CommunityDAO 객체 가져오기
		CommunityDAO dao = CommunityDAO.getInstance();
				
		// 4. CommunityDAO 객체에 Connection 객체 전달하기
		dao.setConnection(con);
				
		// 5. CommunityDAO 객체의 selectMateList() 메서드를 호출하여 게시물 목록 조회
		// => 파라미터 : 현재 페이지 번호(pageNum), 페이지 당 게시물 수(listLimit)
		//    리턴타입 : ArrayList<MateDTO> mateList
		mateList = dao.selectMateList(keyword, pageNum, listLimit);	
				
		// 6. Connection 객체 반환
		close(con);
		
		
		return mateList;
	}

}
