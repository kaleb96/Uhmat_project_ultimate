package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.MapDTO;
import vo.RestaurantInfoDTO;
import vo.ReviewBoardDTO;


public class RestaurantDAO {
	// 싱글톤 DAO 생성
	private RestaurantDAO() {}
	private static RestaurantDAO instance = new RestaurantDAO();
	public static  RestaurantDAO getInstance() {
		return instance;
	}
	
	//커넥션 객체를 전달받기 위한 지역변수 선언 + 넘겨받는 메서드
	private Connection con;
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	//전체 목록의 개수 조회
	public int selectListCount() {
		System.out.println("RestaurantDAO-selectListCount");
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM restaurant_info";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectListCount - SQL 구문 오류!");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	//전체 카테고리 목록의 게시물의 개수를 가져옴 (오버라이딩2)
	public int selectListCount(String category) {
		System.out.println("RestaurantDAO-selectListCount(category)");
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM restaurant_info WHERE category=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectListCount - SQL 구문 오류!");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	//카테고리에 해당하는 검색어만 입력
	public int selectListCount(String category, String keyword) {
		System.out.println("RestaurantDAO-selectListCount(category,keyword)");
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM restaurant_info WHERE category=? AND res_name LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, '%'+keyword+'%');
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectListCount - SQL 구문 오류!");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}

	//검색어만 입력
	public int selectListCount(int i, String keyword) {
		System.out.println("RestaurantDAO-selectListCount(keyword)");
		int listCount=0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT COUNT(*) FROM restaurant_info WHERE res_name LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, '%'+keyword+'%');
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectListCount - SQL 구문 오류!");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	//전체 목록을 페이징 처리하여 조회
	public List<RestaurantInfoDTO> selectRestaurantList(int pageNum, int listLimit) {
		System.out.println("RestaurantDAO-selectRestaurantList");
		List<RestaurantInfoDTO> list = null;
		int startPage = (pageNum-1)*listLimit;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM restaurant_info ORDER BY res_name LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startPage);
			pstmt.setInt(2, listLimit);
			rs = pstmt.executeQuery();
			
			
			list = new ArrayList<RestaurantInfoDTO>();

			while(rs.next()) {
				RestaurantInfoDTO dto = new RestaurantInfoDTO();
				dto.setResName(rs.getString("res_name"));
				dto.setrPostcode(rs.getString("r_postcode"));

				dto.setAddress(rs.getString("address"));
				dto.setPhoneNumber(rs.getString("phone_number"));
				dto.setOpentime(rs.getString("opentime"));
				dto.setResLink(rs.getString("res_link"));
				dto.setPhoto(rs.getString("photo"));
				dto.setReviewCount(rs.getInt("reviewCount"));
				dto.setRating(rs.getFloat("rating"));
				dto.setCategory(rs.getString("category"));
				System.out.println(dto);
				
				list.add(dto);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectRestaurantList - SQL 구문 오류!");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	//키워드로 검색된 레스토랑 오버라이딩 2 값들 받아오기
	public List<RestaurantInfoDTO> selectRestaurantList(int pageNum, String keyword, int listLimit) {
		System.out.println("RestaurantDAO-selectRestaurantList(int pageNum, String keyword, int listLimit)");
		List<RestaurantInfoDTO> list = null;
		int startPage = (pageNum-1)*listLimit;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM restaurant_info WHERE res_name LIKE ? ORDER BY res_name LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, '%'+keyword+'%');
			pstmt.setInt(2, startPage);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			

			list = new ArrayList<RestaurantInfoDTO>();

			while(rs.next()) {
				RestaurantInfoDTO dto = new RestaurantInfoDTO();
				dto.setResName(rs.getString("res_name"));
				dto.setrPostcode(rs.getString("r_postcode"));

				dto.setAddress(rs.getString("address"));
				dto.setPhoneNumber(rs.getString("phone_number"));
				dto.setOpentime(rs.getString("opentime"));
				dto.setResLink(rs.getString("res_link"));
				dto.setPhoto(rs.getString("photo"));
				dto.setReviewCount(rs.getInt("reviewCount"));
				dto.setRating(rs.getFloat("rating"));
				dto.setCategory(rs.getString("category"));
				System.out.println(dto);

				list.add(dto);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectRestaurantList - SQL 구문 오류!");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	//선택된 카테고리 목록을 페이징처리하여 조회 (오버라이딩3)
	public List<RestaurantInfoDTO> selectRestaurantList(int pageNum, int listLimit, String category) {
		System.out.println("RestaurantDAO-selectRestaurantList(int pageNum, int listLimit, String category)");
		List<RestaurantInfoDTO> list = null;
		int startPage = (pageNum-1)*listLimit;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM restaurant_info WHERE category=? ORDER BY res_name LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, startPage);
			pstmt.setInt(3, listLimit);
			rs = pstmt.executeQuery();
			

			list = new ArrayList<RestaurantInfoDTO>();

			while(rs.next()) {
				RestaurantInfoDTO dto = new RestaurantInfoDTO();
				dto.setResName(rs.getString("res_name"));
				dto.setrPostcode(rs.getString("r_postcode"));

				dto.setAddress(rs.getString("address"));
				dto.setPhoneNumber(rs.getString("phone_number"));
				dto.setOpentime(rs.getString("opentime"));
				dto.setResLink(rs.getString("res_link"));
				dto.setPhoto(rs.getString("photo"));
				dto.setReviewCount(rs.getInt("reviewCount"));
				dto.setRating(rs.getFloat("rating"));
				dto.setCategory(rs.getString("category"));
				
				System.out.println(dto);

				list.add(dto);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectRestaurantList - SQL 구문 오류!");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	//오버라이딩4. 키워드랑 카테고리가 다 검색된 레스토랑
	public List<RestaurantInfoDTO> selectRestaurantList(int pageNum, int listLimit, String category, String keyword) {
		System.out.println("RestaurantDAO-selectRestaurantList(int pageNum, int listLimit, String category, String keyword)");
		List<RestaurantInfoDTO> list = null;
		int startPage = (pageNum-1)*listLimit;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM restaurant_info WHERE category=? AND res_name LIKE ? ORDER BY res_name LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, keyword);
			pstmt.setInt(3, startPage);
			pstmt.setInt(4, listLimit);
			rs = pstmt.executeQuery();
			

			list = new ArrayList<RestaurantInfoDTO>();

			while(rs.next()) {
				RestaurantInfoDTO dto = new RestaurantInfoDTO();
				dto.setResName(rs.getString("res_name"));
				dto.setrPostcode(rs.getString("r_postcode"));

				dto.setAddress(rs.getString("address"));
				dto.setPhoneNumber(rs.getString("phone_number"));
				dto.setOpentime(rs.getString("opentime"));
				dto.setResLink(rs.getString("res_link"));
				dto.setPhoto(rs.getString("photo"));
				dto.setReviewCount(rs.getInt("reviewCount"));
				dto.setRating(rs.getFloat("rating"));
				dto.setCategory(rs.getString("category"));
				
				System.out.println(dto);

				list.add(dto);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectRestaurantList - SQL 구문 오류!");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}	

	//식당 정보 입력

	public int insertResInfo(RestaurantInfoDTO dto) {
		int insertCount = 0 ;
		System.out.println("RestaurantDAO - insertResInfo()");
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO restaurant_info VALUES(?,?,?,0,?,?,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getResName());
			pstmt.setString(2, dto.getrPostcode());
			pstmt.setString(3, dto.getAddress());
			pstmt.setString(4, dto.getPhoneNumber());
			pstmt.setString(5, dto.getOpentime());
			pstmt.setString(6, dto.getResLink());
			pstmt.setString(7, dto.getPhoto());
			pstmt.setString(8, dto.getResInfo());
			pstmt.setString(9, dto.getCategory());
			

			System.out.println(dto);

			insertCount=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RestaurantDAO - insertResInfo() - SQL 구문 오류!");
		}finally {
			close(pstmt);
		}
		
		return insertCount;
	}


	//식당 상세정보 가져오기
	public RestaurantInfoDTO getRestaurantInfo(String resName) {
		System.out.println("RestaurantInfoDTO-getRestaurantInfo");
		RestaurantInfoDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT*FROM restaurant_info WHERE res_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, resName);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new RestaurantInfoDTO();
				dto.setResName(rs.getString("res_name"));
				dto.setrPostcode(rs.getString("r_postcode"));
				dto.setAddress(rs.getString("address"));
				dto.setPhoneNumber(rs.getString("phone_number"));
				dto.setOpentime(rs.getString("opentime"));
				dto.setResLink(rs.getString("res_link"));
				dto.setPhoto(rs.getString("photo"));
				dto.setReviewCount(rs.getInt("reviewCount"));
				dto.setRating(rs.getFloat("rating"));
				dto.setResInfo(rs.getString("res_info"));
				dto.setCategory(rs.getString("category"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getRestaurantInfo() - SQL 구문 오류!");
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return dto;
	}

	//식당 위치 정보 입력
	public int insertMapInfo(MapDTO map) {
		System.out.println("RestaurantDAO - insertMapInfo()");
		PreparedStatement pstmt = null;
		int insertCount = 0;
		try {
			String sql = "INSERT INTO map VALUES(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, map.getLongitude());
			pstmt.setDouble(2, map.getLatitude());
			pstmt.setString(3, map.getResName());
			insertCount=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RestaurantDAO - insertMapInfo() - SQL 구문 오류!");
		}finally {
			close(pstmt);
		}
		return insertCount;
	}

	//식당 정보 삭제
	public int deleteRestaurantInfo(String resName) {		
		System.out.println("restaurantInfoDAO - deleteRestaurantInfo()");
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM restaurant_info WHERE res_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, resName);
			deleteCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("deleteRestaurantInfo() - SQL 구문 오류!");
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	
	//식당 정보 삭제 전 사진의 이름을 가져오는 메서드
	public String selectPhoto(String resName) {
		System.out.println("restaurantDAO - selectPhoto()");
		String photo="";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT photo FROM restaurant_info WHERE res_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, resName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				photo = rs.getString("photo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectPhoto() - SQL 구문 오류!");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return photo;
	}

	//식당 위치의 지도 정보 1개 들고 오기
	public MapDTO getMapInfo(String resName) {
		MapDTO dto = null;
		System.out.println("restaurantDAO - getMapInfo()");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM map WHERE res_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, resName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MapDTO();
				dto.setLongitude(rs.getDouble("longitude"));
				dto.setLatitude(rs.getDouble("latitude"));
				dto.setResName(rs.getString("res_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getMapInfo() - SQL 구문 오류!");
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return dto;
	}

	//식당이 삭제 될 시 위치 정보도 같이 삭제
	public void deleteMapInfo(String resName) {
		System.out.println("restaurantDAO - deleteMapInfo()");
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM map WHERE res_name =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, resName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("deleteMapInfo() - SQL 구문 오류!");
		}finally {
			close(pstmt);
		}
		
	}

	//식당 정보 수정
	public int modifyResInfo(RestaurantInfoDTO dto) {
		int modifyCount = 0;
		System.out.println("RestaurantDAO - modifyResInfo()");
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE restaurant_info SET "
					+ "r_postcode=?, address=?,phone_number=?,opentime=?,res_link=?,photo=?,res_info=?,category=? WHERE res_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getrPostcode());
			pstmt.setString(2, dto.getAddress());
			pstmt.setString(3, dto.getPhoneNumber());
			pstmt.setString(4, dto.getOpentime());
			pstmt.setString(5, dto.getResLink());
			pstmt.setString(6, dto.getPhoto());
			pstmt.setString(7, dto.getResInfo());
			pstmt.setString(8, dto.getCategory());
			pstmt.setString(9, dto.getResName());
			modifyCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("mdoifyResInfo() - SQL 구문오류!");
		}finally {
			close(pstmt);
		}
		
		return modifyCount;
	}

	//음식점 위치 정보 수정 
	public int updateMapInfo(MapDTO map) {
		System.out.println("RestaurantDAO - updateMapInfo()");
		int updateCount = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE map SET longitude=?, latitude=? WHERE res_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, map.getLongitude());
			pstmt.setDouble(2, map.getLatitude());
			pstmt.setString(3, map.getResName());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("updateMapInfo - SQL 구문 오류!");
		}finally {
			close(pstmt);
		}
				
		return updateCount;
	}

	//전체 식당 위치정보 조회
	public ArrayList<RestaurantInfoDTO> selectMapList() {
		System.out.println("RestaurantDAO - selectMapList");
		ArrayList<RestaurantInfoDTO> list =null;
		
		PreparedStatement pstmt = null, pstmt2=null;
		ResultSet rs = null,rs2=null;
		
		try {
			String sql = "SELECT * FROM restaurant_info";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<RestaurantInfoDTO>();
			while(rs.next()) {
				RestaurantInfoDTO dto = new RestaurantInfoDTO();
				dto.setResName(rs.getString("res_name"));
				dto.setrPostcode(rs.getString("r_postcode"));
				dto.setAddress(rs.getString("address"));
				dto.setRating(rs.getFloat("rating"));
				dto.setPhoneNumber(rs.getString("phone_number"));
				dto.setOpentime(rs.getString("opentime"));
				dto.setResLink(rs.getString("res_link"));
				dto.setPhoto(rs.getString("photo"));
				dto.setResInfo(rs.getString("res_info"));
				dto.setReviewCount(rs.getInt("reviewCount"));
				dto.setCategory(rs.getString("category"));
				
				//식당 이름에 해당하는 식당 위치정보 입력!
				String sql2 = "SELECT * FROM map WHERE res_name=?";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, (String)rs.getString("res_name"));
				rs2 = pstmt2.executeQuery();
				if(rs2.next()) {
					dto.setLongitude(rs2.getDouble("longitude"));
					dto.setLatitude(rs2.getDouble("latitude"));
				}
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("map.re SQL 구문 오류!");
		}
		
		return list;
	}
	//지도에서 검색된 식당위치정보만 조회
	public ArrayList<RestaurantInfoDTO> selectMapList(String keyword) {
		System.out.println("RestaurantDAO - selectMapList(String keyword)");
		ArrayList<RestaurantInfoDTO> list =null;
		
		PreparedStatement pstmt = null, pstmt2=null;
		ResultSet rs = null,rs2=null;
		
		try {
			String sql = "SELECT * FROM restaurant_info WHERE res_name LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<RestaurantInfoDTO>();
			
			while(rs.next()) {
				RestaurantInfoDTO dto = new RestaurantInfoDTO();
				dto.setResName(rs.getString("res_name"));
				dto.setrPostcode(rs.getString("r_postcode"));
				dto.setAddress(rs.getString("address"));
				dto.setRating(rs.getFloat("rating"));
				dto.setPhoneNumber(rs.getString("phone_number"));
				dto.setOpentime(rs.getString("opentime"));
				dto.setResLink(rs.getString("res_link"));
				dto.setPhoto(rs.getString("photo"));
				dto.setResInfo(rs.getString("res_info"));
				dto.setReviewCount(rs.getInt("reviewCount"));
				dto.setCategory(rs.getString("category"));
				
				//식당 이름에 해당하는 식당 위치정보 입력!
				sql = "SELECT*FROM map WHERE res_name=?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setString(1, rs.getString("res_name"));
				rs2 = pstmt2.executeQuery();
				while(rs2.next()) {
					dto.setLatitude(rs2.getDouble("latitude"));
					dto.setLongitude(rs2.getDouble("longitude"));
				}
				list.add(dto);
			}
			System.out.println("selectMapList(String keyword) - list : " + list);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("selectMapList(String keyword) - SQL 구문 오류!");
		}finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}
	
	//전체 목록을 페이징 처리하여 조회
		public ArrayList<RestaurantInfoDTO> selectMainRestaurantList(String search) {
			ArrayList<RestaurantInfoDTO> list = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM restaurant_info WHERE res_name LIKE ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, '%'+search+'%');
				rs = pstmt.executeQuery();
				

				list = new ArrayList<RestaurantInfoDTO>();

				while(rs.next()) {
					RestaurantInfoDTO dto = new RestaurantInfoDTO();
					dto.setResName(rs.getString("res_name"));
					dto.setrPostcode(rs.getString("r_postcode"));
					dto.setAddress(rs.getString("address"));
					dto.setPhoneNumber(rs.getString("phone_number"));
					dto.setOpentime(rs.getString("opentime"));
					dto.setResLink(rs.getString("res_link"));
					dto.setPhoto(rs.getString("photo"));
					dto.setReviewCount(rs.getInt("reviewCount"));
					dto.setRating(rs.getFloat("rating"));
					
					System.out.println(dto);

					list.add(dto);				
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("selectRestaurantList - SQL 구문 오류!");
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return list;
		}

		//리뷰에 입력된 식당이 존재하는지 확인하는 메서드
		public boolean selectRestaurantList(String resName) {
			boolean isRes = false;
			System.out.println("restaurantDAO - selectRestaurantList(resName)");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT * FROM restaurant_info WHERE res_name=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, resName);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					isRes=true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return isRes;
		}

		//메인에 출력할 레스토랑 구문 (별점과 댓글이 많은 순서대로 출력)
		public ArrayList<RestaurantInfoDTO> selectResBoardList(int pageNum, int listLimit) {
			System.out.println("selectResBoardList()");
			ArrayList<RestaurantInfoDTO> resInfo = null;
			
			String sql = "";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			// 시작행 번호 계산
			int startRow = (pageNum  - 1) * listLimit;

				try {
					sql = "SELECT * FROM restaurant_info ORDER BY rating DESC,reviewcount DESC"
							+ " LIMIT ?,?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, listLimit);
					
					rs = pstmt.executeQuery();
					
					resInfo = new ArrayList<RestaurantInfoDTO>();
						
					while(rs.next()) {	
						RestaurantInfoDTO dto = new RestaurantInfoDTO();
						// 게시물 정보 저장
						dto.setResName(rs.getString("res_name"));
						dto.setrPostcode(rs.getString("r_postcode"));
						dto.setAddress(rs.getString("address"));
						dto.setPhoneNumber(rs.getString("phone_number"));
						dto.setOpentime(rs.getString("opentime"));
						dto.setResLink(rs.getString("res_link"));
						dto.setPhoto(rs.getString("photo"));
						dto.setReviewCount(rs.getInt("reviewCount"));
						dto.setRating(rs.getFloat("rating"));
						dto.setCategory(rs.getString("category"));
						resInfo.add(dto);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("SQL 구문작성오류 - selectResBoardList()");
				} finally {
					close(rs);
					close(pstmt);
				}
			return resInfo;
		}
	
}
