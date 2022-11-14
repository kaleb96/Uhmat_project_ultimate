package dao;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;

import vo.*;

import static db.JdbcUtil.*;

public class ReviewCategoryDAO {
	private static ReviewCategoryDAO instance = new ReviewCategoryDAO();
	private static Connection con;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	public void setConnection(Connection con) {
		this.con = con; 
	} 
	private ReviewCategoryDAO() {}
	public static ReviewCategoryDAO getInstance() {
		return instance;
	}
	
	public int selectReviewListCount() {
		
		int listCount = 0;
		
		//구문 작성 전 Setting
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 구문 작성 및 실행
		try {
			String sql = "SELECT COUNT(?) FROM reviewboard";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

			System.out.println("SQL 구문 작성 및 실행 오류 - selectReviewListCount()" + e.getMessage());


		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	//전체 리뷰 검색(페이지 한정)
	public ArrayList<ReviewBoardDTO> selectReviewBoardList(int pageNum, int listLimit) {
		
		ArrayList<ReviewBoardDTO> reviewList = null;
		
		String sql = "";
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		// 시작행 번호 계산
		int startRow = (pageNum  - 1) * listLimit;
		
		/*************************************
		 * 댓글 부분 구현될 경우 새로 sql 문 작성 해야 함
		 ****************************************/

			try {
				sql = "SELECT * FROM reviewboard JOIN member on reviewboard.nickname = member.nickname "
							+ "ORDER BY idx DESC "
							+ "LIMIT ?, ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, listLimit);
				
				rs = pstmt.executeQuery();
				
				reviewList = new ArrayList<ReviewBoardDTO>();
					
				while(rs.next()) {
					
					ReviewBoardDTO dto = new ReviewBoardDTO();
					String tagResult = "";
					// 게시물 정보 저장
					dto.setIdx(rs.getInt("idx"));
					dto.setRes_name(rs.getString("res_name"));
					dto.setNickname(rs.getString("nickname"));
					dto.setSubject(rs.getString("subject"));
					dto.setPhoto(rs.getString("photo"));
					dto.setContent(rs.getString("content"));
					dto.setLikes(rs.getInt("likes"));
					dto.setRating(rs.getFloat("rating"));
					dto.setDate(rs.getDate("date"));
					dto.setIcon(rs.getString("icon"));
					
						String sql2 = "SELECT tag_name FROM tag_relation WHERE review_idx=?";
						pstmt2  = con.prepareStatement(sql2);
						pstmt2.setInt(1, dto.getIdx());
						rs2 = pstmt2.executeQuery();
						
						tagResult = "#";
						StringJoiner joiner = new StringJoiner("#");
							while(rs2.next()) {
								
								joiner.add(rs2.getString("tag_name"));;
							}
						
						tagResult = tagResult + joiner;
						System.out.println(tagResult);
						dto.setTag_name(tagResult);
						reviewList.add(dto);
						close(rs2);
						close(pstmt2);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("SQL 구문작성오류 - selectReviewList()");
			} finally {
				close(rs);
				close(pstmt);
			}
		return reviewList;
	}
	
	//태그로 검색됨 오버라이딩2
		public ArrayList<ReviewBoardDTO> selectReviewBoardList(int pageNum, int listLimit, String targetTag) {
			
			ArrayList<ReviewBoardDTO> reviewList = null;
			
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			ResultSet rs = null;
			ResultSet rs2 = null;

			String sql = "";


			 
				// 시작행 번호 계산
//				int startRow = (pageNum  - 1) * listLimit;
				
				/*************************************
				 * 댓글 부분 구현될 경우 새로 sql 문 작성 해야 함
				 ****************************************/

					try {
						sql = "SELECT * FROM reviewboard r inner join " 
								 + " (SELECT * FROM tag_relation WHERE tag_name LIKE ?) s on r.idx=s.review_idx "
								 + " INNER JOIN member AS m ON r.nickname = m.nickname"
								+ " ORDER BY idx DESC ";
//								+ " limit ?, ?";
						
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, targetTag);
//						pstmt.setInt(2, startRow);
//						pstmt.setInt(3, listLimit);
						rs = pstmt.executeQuery();
						
						reviewList = new ArrayList<ReviewBoardDTO>();
							
						while(rs.next()) {
							
							ReviewBoardDTO dto = new ReviewBoardDTO();
							String tagResult = "";
							// 게시물 정보 저장
							dto.setIdx(rs.getInt("idx"));
							dto.setRes_name(rs.getString("res_name"));
							dto.setNickname(rs.getString("nickname"));
							dto.setSubject(rs.getString("subject"));
							dto.setPhoto(rs.getString("photo"));
							dto.setContent(rs.getString("content"));
							dto.setLikes(rs.getInt("likes"));
							dto.setRating(rs.getFloat("rating"));
							dto.setDate(rs.getDate("date"));
							dto.setIcon(rs.getString("icon"));
							
								String sql2 = "SELECT tag_name FROM tag_relation WHERE review_idx=?";
								pstmt2  = con.prepareStatement(sql2);
								pstmt2.setInt(1, dto.getIdx());
								rs2 = pstmt2.executeQuery();
								
								tagResult = "#";
								StringJoiner joiner = new StringJoiner("#");
									while(rs2.next()) {
										
										joiner.add(rs2.getString("tag_name"));;
									}
								
								tagResult = tagResult + joiner;
								System.out.println(tagResult);
								dto.setTag_name(tagResult);
								reviewList.add(dto);
						}
						close(rs2);
						close(pstmt2);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("SQL 구문작성오류 - selectReviewList()");
					} finally {

						close(rs);
						close(pstmt);
					}
				return reviewList;
		}
		
	//오버로딩된 메서드, 식당정보에 해당하는 리뷰 검색
	public ArrayList<ReviewBoardDTO> selectReviewBoardList(String resName, int pageNum, int listLimit) {
		ArrayList<ReviewBoardDTO> reviewList = null;
		
		String sql = "";
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		// 시작행 번호 계산
		int startRow = (pageNum  - 1) * listLimit;

			try {
				sql = "SELECT * FROM reviewboard JOIN member on reviewboard.nickname = member.nickname WHERE res_name=?"
							+ "ORDER BY idx DESC "
							+ "LIMIT ?, ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, resName);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, listLimit);
				
				rs = pstmt.executeQuery();
				
				reviewList = new ArrayList<ReviewBoardDTO>();
					
				while(rs.next()) {	
					ReviewBoardDTO dto = new ReviewBoardDTO();
					// 게시물 정보 저장
					dto.setIdx(rs.getInt("idx"));
					dto.setRes_name(rs.getString("res_name"));
					dto.setNickname(rs.getString("nickname"));
					dto.setSubject(rs.getString("subject"));
					dto.setPhoto(rs.getString("photo"));
					dto.setContent(rs.getString("content"));
					dto.setLikes(rs.getInt("likes"));
					dto.setRating(rs.getFloat("rating"));
					dto.setDate(rs.getDate("date"));
					dto.setIcon(rs.getString("icon"));
				
					String sql2 = "SELECT tag_name FROM tag_relation WHERE review_idx=?";
					pstmt2  = con.prepareStatement(sql2);
					pstmt2.setInt(1, dto.getIdx());
					rs2 = pstmt2.executeQuery();
					
					reviewList.add(dto);
					close(rs2);
					close(pstmt2);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("SQL 구문작성오류 - selectReviewList()");
			} finally {
				close(rs);
				close(pstmt);
			}
		return reviewList;
	}

	public ReviewBoardDTO selectReviewBoardList(int idx) {
		ReviewBoardDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		String tagResult = "";
		try {
			String sql = "SELECT * FROM reviewboard JOIN member on reviewboard.nickname = member.nickname WHERE idx =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ReviewBoardDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setContent(rs.getString("content"));
				dto.setLikes(rs.getInt("likes"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPhoto(rs.getString("photo"));
				dto.setRating(rs.getFloat("rating"));
				dto.setRes_name(rs.getString("res_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setDate(rs.getDate("date"));
				dto.setIcon(rs.getString("icon"));
				
				String sql2 = "SELECT tag_name FROM tag_relation WHERE review_idx=?";
				pstmt2  = con.prepareStatement(sql2);
				pstmt2.setInt(1, dto.getIdx());
				rs2 = pstmt2.executeQuery();
				
				tagResult = "#";
				StringJoiner joiner = new StringJoiner("#");
				while(rs2.next()) {
					
					joiner.add(rs2.getString("tag_name"));;
				}
				
				tagResult = tagResult + joiner;
					System.out.println(tagResult);
					dto.setTag_name(tagResult);
					close(rs2);
					close(pstmt2);
			}
//				System.out.println(dto); DTO값을 잘 전달 받았는지 체크!

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문작성 및 실행오류 - selectReviewBoardList(idx) - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return dto;
	}
	
	//리뷰 정보를 입력 및 식당 정보 업데이트
	public int insertReview(ReviewBoardDTO dto) {
		int insertCount = 0;
		
		PreparedStatement pstmt= null,pstmt2=null;
		ResultSet rs= null;
		
		
		int num = 1;
		int updateCount = 0;
		// 1. idx 값을 새 번호로 잡아주기
		try {
			String sql = "SELECT MAX(idx) FROM reviewboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
			//pstmt,rs 사용완료후 반환
			close(rs);
			close(pstmt);
			
			
			
			//Insert작업 수행
			sql = "INSERT INTO reviewboard VALUES(?,?,?,?,?,?,0,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getRes_name());
			pstmt.setString(3, dto.getNickname());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getPhoto());
			pstmt.setString(6, dto.getContent());
			pstmt.setFloat(7, dto.getRating());
			
			insertCount = pstmt.executeUpdate();
			// 리뷰 개수에 따른 별점 평균 조회
			sql = "SELECT AVG(rating), COUNT(idx) FROM reviewboard "
					+ " WHERE res_name =? "
					+ " GROUP BY res_name";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRes_name());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				double avgRating =Double.parseDouble(df.format((rs.getDouble("AVG(rating)"))));
				int reviewCount = rs.getInt("Count(idx)");
				sql = "UPDATE restaurant_info SET rating=?, reviewcount=? WHERE res_name=?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setDouble(1, avgRating);		//식당에 해당하는 리뷰들의 평점 평균 업데이트
				pstmt2.setInt(2, reviewCount);	// 원래 리뷰개수
				pstmt2.setString(3, dto.getRes_name());
				System.out.println("-------------------------------------");
				System.out.println("avgRating:"+avgRating);
				System.out.println("reviewcount: "+reviewCount);
				System.out.println(dto.getRes_name());
				System.out.println("--------------------------------------");
				pstmt2.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL구문 작성및 실행 오류 insertReview() - " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	
	public int insertTag(String tag) {
		int insertCount = 0;
				
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				
		
			int num = 1;
			// 1. idx 값을 새 번호로 잡아주기
			try {
				String sql = "SELECT MAX(review_idx) FROM tag_relation";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					num = rs.getInt(1) + 1;
				}
					
				//pstmt,rs 사용완료후 반환
				close(rs);
				close(pstmt);
					
				// 전달 받은 tag를 #와 공백을 기준으로 나눔
				
				tag = tag.replaceAll("#", "");
				String str[] = tag.split(" ");
				
				for(String s : str) {
					sql="INSERT INTO tag_relation VALUES(?, ?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.setString(2, s);
					System.out.println(s);
					insertCount = pstmt.executeUpdate();
				}
					
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("SQL구문 작성및 실행 오류 insertReview() - " + e.getMessage());
				} finally {
					close(pstmt);
				}
				
				return insertCount;
	}
	
	
	public int updateReview(ReviewBoardDTO dto) {

		
		int updateCount = 0;
		
		PreparedStatement pstmt = null, pstmt2=null;
		ResultSet rs = null;
		// 리뷰 개수에 따른 별점 평균 조회
		String sql;
				
		 sql = "UPDATE reviewboard SET res_name=?, subject=?, rating=?, content=?, photo=? "
				+ "WHERE idx=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRes_name());
			pstmt.setString(2, dto.getSubject());
			pstmt.setFloat(3, dto.getRating());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getPhoto());
			pstmt.setInt(6, dto.getIdx());
			System.out.println("========================");
			System.out.println(sql);
			System.out.println(dto.getRes_name()+", subject"+ dto.getSubject()+dto.getRating()+dto.getContent()+dto.getIdx());
			System.out.println("========================");
			updateCount = pstmt.executeUpdate();
			try {
				sql = "SELECT AVG(rating), COUNT(idx) FROM reviewboard "
						+ " WHERE res_name =? "
						+ " GROUP BY res_name";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getRes_name());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					double avgRating =Double.parseDouble(df.format((rs.getDouble("AVG(rating)"))));
					int reviewCount = rs.getInt("Count(idx)");
					sql = "UPDATE restaurant_info SET rating=?, reviewcount=? WHERE res_name=?";
					pstmt2 = con.prepareStatement(sql);
					pstmt2.setDouble(1, avgRating);		//식당에 해당하는 리뷰들의 평점 평균 업데이트
					pstmt2.setInt(2, reviewCount);	// 원래 리뷰개수 그대로
					pstmt2.setString(3, dto.getRes_name());
					System.out.println("-------------------------------------");
					System.out.println("avgRating:"+avgRating);
					System.out.println("reviewcount: "+reviewCount);
					System.out.println(dto.getRes_name());
					System.out.println("--------------------------------------");
					pstmt2.executeUpdate();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			
			System.out.println("SQL 구문 작성 및 실행오류 - " + e.getMessage());
			e.printStackTrace();
		} finally {
//			close(rs);
			close(pstmt);
//			close(pstmt2);
		}
		
		return updateCount;
	}
	// override-----------------------------------------------------------------------------
	public int updateReview(int idx, int updateLike, int totalLike) {
		int updateCount = 0;
		String sql = "";
		PreparedStatement pstmt = null;
	
		if (updateLike == 1) {
			sql = "UPDATE reviewboard SET likes=likes + 1 WHERE idx=?";
			System.out.println("리뷰 좋아요 증가");
		} else {
			if(totalLike > 0) {
			sql = "UPDATE reviewboard SET likes=likes - 1 WHERE idx=?";
			} else {
				sql = "UPDATE reviewboard SET likes = 0 WHERE idx=?";
			}
			System.out.println("리뷰 좋아요 취소");
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sql 구문 작성 및 실행 오류 - updateReview - (reviewBoard좋아요");
		}

		return updateCount;
	}
	
	public boolean checkPassword(String pass) {
		// 비밀번호를 확인해야함 member에서
		boolean isWriter = false;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT FROM member WHERE pass=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isWriter = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 실행 및 구문 작성오류 - " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return isWriter;
	}
	public int deleteReview(int idx) {
		int deleteCount = 0;
		
		PreparedStatement pstmt = null, pstmt2=null;
		ResultSet rs = null, rs2=null;
		
		String sql;
		
		try {
			sql = "SELECT res_name FROM reviewboard WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs2 = pstmt.executeQuery();
			String resName = "";
			if(rs2.next()) {
				resName = rs2.getString("res_name");
			} 			
			close(pstmt);
				
			sql = "DELETE FROM reviewBoard WHERE idx=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			deleteCount = pstmt.executeUpdate();
			
			close(pstmt);
			sql = "SELECT AVG(rating), COUNT(idx) FROM reviewboard "
					+ " WHERE res_name =? "
					+ " GROUP BY res_name";
			System.out.println("--------------------------------");
			System.out.println("resName: "+resName);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, resName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("================================");
				System.out.println("삭제 후 식당 정보 업데이트!");
				System.out.println("================================");
				double avgRating =Double.parseDouble(df.format((rs.getDouble("AVG(rating)"))));
				int reviewCount = rs.getInt("Count(idx)");
				sql = "UPDATE restaurant_info SET rating=?, reviewcount=? WHERE res_name=?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setDouble(1, avgRating);		//식당에 해당하는 리뷰들의 평점 평균 업데이트
				pstmt2.setInt(2, reviewCount);	// 원래 리뷰개수 그대로
				pstmt2.setString(3, resName);
				System.out.println("-------------------------------------");
				System.out.println("avgRating:"+avgRating);
				System.out.println("reviewcount: "+reviewCount);
				System.out.println(resName);
				System.out.println("--------------------------------------");
				pstmt2.executeUpdate();
			}else {
				//삭제하고 연동된 리뷰가 아예 없을 때는 0으로 변경
				sql = "UPDATE restaurant_info SET rating=0, reviewcount=0 WHERE res_name=?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, resName);
				System.out.println("-------------------------------------");
				System.out.println("--------------------------------------");
				pstmt2.executeUpdate();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs2);
			close(rs);
			close(pstmt);
		}
		
		return deleteCount;
	}
	
	
	public int updateLike(int idx, String nickname) {
		int updateLike = 0;
		boolean isSelect = false;
		/*
		 * idx와 nickname에 해당하는 data를 조회시 return 값이 0일경우
		 * likes 에 + 1을 update
		 * 
		 * return 값이 있을 경우
		 * 해당데이터 delete
		 * 
		 * 둘중의 하나의 처리가 완료 되었을 경우 updateLike = 1;
		 */
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 1. 좋아요가 있는지 없는지 조회
		try {
			String sql = "SELECT * FROM review_likes WHERE idx=? AND nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, nickname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isSelect = true; 
			}
			
			close(pstmt);
			close(rs);
			
			if(!isSelect) { // likes 가 없을 경우, insert
				sql = "INSERT INTO review_likes VALUES(?, 1, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);
				pstmt.setString(2, nickname);
				updateLike = pstmt.executeUpdate();
				System.out.println("review_likes - 좋아요 추가");
			
			} else { // likes가 있는 경우 기존의 data를 delete
				
				sql="DELETE FROM review_likes WHERE idx=? AND nickname=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, idx);				
				pstmt.setString(2, nickname);
				updateLike = pstmt.executeUpdate();
				updateLike = -1;
				System.out.println("review_likes - 좋아요 취소");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sql 구문 실행 및 작성오류 - review_likes update");
		} finally {
			
			close(pstmt);
			
		}
		return updateLike;
	}
	public int selectLikeCount(int idx) {
		
		int totalLike = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT likes FROM reviewboard WHERE idx =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalLike = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQL 구문작성 및 오류 " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return totalLike;
	}
	
	public ArrayList<ReviewReplyDTO> selectReivewReplyList(int board_idx) {
		
		ArrayList<ReviewReplyDTO> replyList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM review_reply JOIN member ON review_reply.nickname=member.nickname WHERE board_idx=? " + 
		" ORDER BY idx DESC";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			
			rs = pstmt.executeQuery();
			
			replyList = new ArrayList<ReviewReplyDTO>();
			while(rs.next()) {
				
				ReviewReplyDTO dto = new ReviewReplyDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setNickname(rs.getString("nickname"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setBoard_idx(board_idx);
				dto.setIcon(rs.getString("icon"));
				
				replyList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 작성 및 실행 오류" + e.getMessage());
		
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return replyList;
	}
	public int insertReply(int board_idx, String nickname, String content) {
		
		System.out.println("insertReply");
		int insertCount = 0;
		
		int num = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT MAX(idx) FROM review_reply";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				num = rs.getInt(1) + 1;
			}
			
			//pstmt,rs 사용완료후 반환
			close(rs);
			close(pstmt);
			
			sql = "INSERT INTO review_reply VALUES(?, ? ,?, now(), ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, nickname);
			pstmt.setString(3, content);
			pstmt.setInt(4, board_idx);
			
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQL 구문작성 및 실행 오류 - " + e.getMessage() );
		} finally {
			close(pstmt);
		}
		
		return insertCount;
	}
	public ArrayList<ReviewReplyDTO> selectDetailReplyList(int board_idx) {
		
		ArrayList<ReviewReplyDTO> replyList = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM review_reply JOIN member ON review_reply.nickname=member.nickname WHERE board_idx=? "
					+ " order by idx DESC limit 0, 2";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			
			rs = pstmt.executeQuery();
			
			replyList = new ArrayList<ReviewReplyDTO>();
			while(rs.next()) {
				
				ReviewReplyDTO dto = new ReviewReplyDTO();
				dto.setIdx(rs.getInt("idx"));
				dto.setNickname(rs.getString("nickname"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getTimestamp("date"));
				dto.setBoard_idx(board_idx);
				dto.setIcon(rs.getString("icon"));
				
				replyList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 구문 작성 및 실행 오류" + e.getMessage());
		
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return replyList;
	}
	
	//태그 업데이트
	public int updateTag(int idx, String tag) {
			
			PreparedStatement pstmt = null;
			String sql= "";
			int updateCount = 0;
			tag = tag.replaceAll("#", "");
			String str[] = tag.split(" ");

			try {
					sql="DELETE FROM tag_relation WHERE review_idx=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, idx);
					pstmt.executeUpdate();
					close(pstmt);
					commit(con);
					
					for(String s : str) {
						sql="INSERT INTO tag_relation VALUES(?, ?)";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, idx);
						pstmt.setString(2, s);
						updateCount = pstmt.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("SQL 구문 작성 및 실행 오류 - updateTag()");
			} finally {
				close(pstmt);
			}
			return updateCount;
		}
		
		// 목민수파트
		public ArrayList<ReviewBoardDTO> selectReviewBestLikeBoardList(int pageNum, int listLimit, String targetTag) {
			System.out.println("selectReviewBestLikeBoardList()");
			ArrayList<ReviewBoardDTO> reviewList = null;
			
			String sql = "";
			PreparedStatement pstmt = null,pstmt2=null;
			ResultSet rs = null,rs2=null;
			
			// 시작행 번호 계산
			int startRow = (pageNum  - 1) * listLimit;

				try {
					sql = "SELECT * FROM reviewboard JOIN member ON reviewboard.nickname=member.nickname "
								+ "ORDER BY likes DESC "
								+ "LIMIT ?, ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, listLimit);
					
					rs = pstmt.executeQuery();
					
					reviewList = new ArrayList<ReviewBoardDTO>();
					String tagResult = "";
					while(rs.next()) {	
						ReviewBoardDTO dto = new ReviewBoardDTO();
						// 게시물 정보 저장
						dto.setIdx(rs.getInt("idx"));
						dto.setRes_name(rs.getString("res_name"));
						dto.setNickname(rs.getString("nickname"));
						dto.setSubject(rs.getString("subject"));
						dto.setPhoto(rs.getString("photo"));
						dto.setContent(rs.getString("content"));
						dto.setLikes(rs.getInt("likes"));
						dto.setRating(rs.getFloat("rating"));
						dto.setDate(rs.getDate("date"));
						dto.setIcon(rs.getString("icon"));
							String sql2 = "SELECT tag_name FROM tag_relation WHERE review_idx=?";
							pstmt2  = con.prepareStatement(sql2);
							pstmt2.setInt(1, dto.getIdx());
							rs2 = pstmt2.executeQuery();
							
							tagResult = "#";
							StringJoiner joiner = new StringJoiner("#");
								while(rs2.next()) {
									
									joiner.add(rs2.getString("tag_name"));;
								}
							
							tagResult = tagResult + joiner;
							System.out.println(tagResult);
							dto.setTag_name(tagResult);
						reviewList.add(dto);
						System.out.println("=====================");
						System.out.println(dto);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("SQL 구문작성오류 - selectReviewList()");
				} finally {
					close(rs);
					close(pstmt);
				}
			return reviewList;
		}

		//박영재
		public ArrayList<ReviewBoardDTO> selectMainReviewBoardList(String search) {

			ArrayList<ReviewBoardDTO> reviewList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			
			/*************************************
			 * 댓글 부분 구현될 경우 새로 sql 문 작성 해야 함
			 ****************************************/
				
				try {
					String sql = "SELECT * FROM reviewboard JOIN member ON reviewboard.nickname = member.nickname WHERE subject LIKE ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%" + search + "%");
					
					rs = pstmt.executeQuery();
					
					reviewList = new ArrayList<ReviewBoardDTO>();
						
					while(rs.next()) {
						
						ReviewBoardDTO dto = new ReviewBoardDTO();
						String tagResult = "";
						// 게시물 정보 저장
						dto.setIdx(rs.getInt("idx"));
						dto.setRes_name(rs.getString("res_name"));
						dto.setNickname(rs.getString("nickname"));
						dto.setSubject(rs.getString("subject"));
						dto.setPhoto(rs.getString("photo"));
						dto.setContent(rs.getString("content"));
						dto.setLikes(rs.getInt("likes"));
						dto.setRating(rs.getFloat("rating"));
						dto.setDate(rs.getDate("date"));
						dto.setIcon(rs.getString("icon"));
						
						String sql2 = "SELECT tag_name FROM tag_relation WHERE review_idx=?";
						pstmt2  = con.prepareStatement(sql2);
						pstmt2.setInt(1, dto.getIdx());
						rs2 = pstmt2.executeQuery();
						
						tagResult = "#";
						StringJoiner joiner = new StringJoiner("#");
						while(rs2.next()) {
							
							joiner.add(rs2.getString("tag_name"));;
						}
						
						tagResult = tagResult + joiner;
						System.out.println(tagResult);
//					System.out.println(dto);
						dto.setTag_name(tagResult);
						reviewList.add(dto);
						close(rs2);
						close(pstmt2);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("SQL 구문작성오류 - selectReviewList()");
				} finally {
					close(rs);
					close(pstmt);
				}
			return reviewList;
		}
		
		//검색어 없을 때 메인에 가져올 목록
		public ArrayList<ReviewBoardDTO> selectMainReviewBoardList() {
ArrayList<ReviewBoardDTO> reviewList = null;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			
			/*************************************
			 * 댓글 부분 구현될 경우 새로 sql 문 작성 해야 함
			 ****************************************/
				
				try {
					String sql = "SELECT * FROM reviewboard JOIN member ON reviewboard.nickname = member.nickname ORDER BY likes DESC";
					pstmt = con.prepareStatement(sql);
					
					rs = pstmt.executeQuery();
					
					reviewList = new ArrayList<ReviewBoardDTO>();
						
					while(rs.next()) {
						
						ReviewBoardDTO dto = new ReviewBoardDTO();
						String tagResult = "";
						// 게시물 정보 저장
						dto.setIdx(rs.getInt("idx"));
						dto.setRes_name(rs.getString("res_name"));
						dto.setNickname(rs.getString("nickname"));
						dto.setSubject(rs.getString("subject"));
						dto.setPhoto(rs.getString("photo"));
						dto.setContent(rs.getString("content"));
						dto.setLikes(rs.getInt("likes"));
						dto.setRating(rs.getFloat("rating"));
						dto.setDate(rs.getDate("date"));
						dto.setIcon(rs.getString("icon"));
						
						String sql2 = "SELECT tag_name FROM tag_relation WHERE review_idx=?";
						pstmt2  = con.prepareStatement(sql2);
						pstmt2.setInt(1, dto.getIdx());
						rs2 = pstmt2.executeQuery();
						
						tagResult = "#";
						StringJoiner joiner = new StringJoiner("#");
						while(rs2.next()) {
							
							joiner.add(rs2.getString("tag_name"));;
						}
						
						tagResult = tagResult + joiner;
						System.out.println(tagResult);
//					System.out.println(dto);
						dto.setTag_name(tagResult);
						reviewList.add(dto);
						close(rs2);
						close(pstmt2);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("SQL 구문작성오류 - selectReviewList()");
				} finally {
					close(rs);
					close(pstmt);
				}
			return reviewList;
		}
		
		public int selectResearchReviewListCount(String search) {
			int listCount = 0;
			
			//구문 작성 전 Setting
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			// 구문 작성 및 실행
			try {
				String sql = "SELECT COUNT(*) FROM reviewboard WHERE subject LIKE ?	";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + search + "%");
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL 구문 작성 및 실행 오류 - selectReviewListCount()" + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return listCount;
		}
		public ArrayList<ReviewBoardDTO> selectRecentReview(int pageNum, int listLimit, String targetTag) {
			System.out.println("selectRecentReview()");
			ArrayList<ReviewBoardDTO> reviewList = null;
			
			String sql = "";
			PreparedStatement pstmt = null,pstmt2=null;
			ResultSet rs = null,rs2=null;
			
			// 시작행 번호 계산
			int startRow = (pageNum  - 1) * listLimit;

				try {
					sql = "SELECT * FROM reviewboard JOIN member ON reviewboard.nickname = member.nickname "
								+ "ORDER BY date DESC "
								+ "LIMIT ?, ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, listLimit);
					
					rs = pstmt.executeQuery();
					
					reviewList = new ArrayList<ReviewBoardDTO>();
					String tagResult = "";
					while(rs.next()) {	
						ReviewBoardDTO dto = new ReviewBoardDTO();
						// 게시물 정보 저장
						dto.setIdx(rs.getInt("idx"));
						dto.setRes_name(rs.getString("res_name"));
						dto.setNickname(rs.getString("nickname"));
						dto.setSubject(rs.getString("subject"));
						dto.setPhoto(rs.getString("photo"));
						dto.setContent(rs.getString("content"));
						dto.setLikes(rs.getInt("likes"));
						dto.setRating(rs.getFloat("rating"));
						dto.setDate(rs.getDate("date"));
						dto.setIcon(rs.getString("icon"));
							String sql2 = "SELECT tag_name FROM tag_relation WHERE review_idx=?";
							pstmt2  = con.prepareStatement(sql2);
							pstmt2.setInt(1, dto.getIdx());
							rs2 = pstmt2.executeQuery();
							
							tagResult = "#";
							StringJoiner joiner = new StringJoiner("#");
								while(rs2.next()) {
									
									joiner.add(rs2.getString("tag_name"));;
								}
							
							tagResult = tagResult + joiner;
							System.out.println(tagResult);
							dto.setTag_name(tagResult);
						reviewList.add(dto);
						System.out.println("=====================");
						System.out.println(dto);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("SQL 구문작성오류 - selectReviewList()");
				} finally {
					close(rs);
					close(rs2);
					close(pstmt);
				}
			return reviewList;
		}
		
		
}
