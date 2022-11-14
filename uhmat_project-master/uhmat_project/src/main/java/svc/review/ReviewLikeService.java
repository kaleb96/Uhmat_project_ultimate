package svc.review;

import vo.*;
import static db.JdbcUtil.*;

import java.sql.*;

import dao.*;
public class ReviewLikeService {

	public boolean modifyLike(int idx, String nickname) {
		System.out.println("ReviewLikeService");
		
		boolean isModifySuccess = false;
		
		
		//1. Connection pool
		Connection con = getConnection();
		
		//2.DAO 객체 가져오기
		ReviewCategoryDAO dao = ReviewCategoryDAO.getInstance();
		
		//3 dao객체에 커넥션 넘겨주기
		dao.setConnection(con);
		
		/*
		 * 좋아요 2단계의 로직 
		 *
		 * 1.
		 * 좋아요 버튼 클릭 시 : 
		 * ->idx : 해당 리뷰의 idx
		 * ->likes : 
		 * 		0 일경우 0-> 1
		 * 		1 일 경우 - > 0처리 하고 해당 data를 delete해줍니다.
		 * ->nickname: 로그인한 사용자의 nickname 
		 * 을 review_like table에 작성해줍니다.
		 * -------------------------------------------
		 * 2. 
		 * reivew_like 의 likes가 0 일경우 :
		 * reviewboard table에서 해당 idx의 data에서 likes 를 +1 해줍니다.
		 * 
		 * reivew_like 의 likes가 1 일경우 :
		 * reviewboard table에서 해당 idx data에서 likes -1을 해줍니다.
		 */
		
		//review_likes를 업데이트
		int totalLike = dao.selectLikeCount(idx);
		
		int updateLike = dao.updateLike(idx, nickname);
		
		if(updateLike != 0) {
			//review_like는 성공, like가 0 또는 1일경우에 따른 결과 처리
			int updateCount = dao.updateReview(idx, updateLike, totalLike);
			
			if(updateCount > 0) { // 결과 처리가 성공적으로 되었을 경우
				commit(con);
				isModifySuccess = true;
			} 		
		}
		else {
			rollback(con);
		}
		close(con);
		
		
		return isModifySuccess;
	}
	
}
