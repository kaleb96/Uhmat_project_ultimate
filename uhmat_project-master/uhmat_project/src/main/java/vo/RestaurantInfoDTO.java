package vo;

//create table restaurantInfo(
//res_name VARCHAR(50) PRIMARY KEY,
//r_postcode VARCHAR(5) NOT NULL,
//address VARCHAR(50) NOT NULL,
//rating FLOAT DEFAULT 0,
//phone_number  VARCHAR(15) NOT NULL,
//opentime VARCHAR(200) NOT NULL,
//res_link VARCHAR(100),
//photo VARCHAR(100),
//res_info VARCHAR(5000) NOT NULL,
//reviewCount INT default 0
//);

public class RestaurantInfoDTO { 
	private String resName;	//식당 이름
	private String rPostcode;	//우편번호
	private String address;	//상세 주소 
	private String resInfo; //식당 상세내용
	private String phoneNumber; //식당 번호
	private String opentime; //영업 시간
	private String resLink; //레스토랑의 웹사이트 링크
	private String photo; //식당 사진
	private float rating;	//별점
	private int reviewCount; //식당 리뷰 개수
	private double longitude;	//지도 위도
	private double latitude;	//지도 경도
	private String category; // 식당 카테고리 (DB 수정 필요)
	
	@Override
	public String toString() {
		return "RestaurantInfoDTO [resName=" + resName + ", rPostcode=" + rPostcode + ", address=" + address
				+ ", resInfo=" + resInfo + ", phoneNumber=" + phoneNumber + ", opentime=" + opentime + ", resLink="
				+ resLink + ", photo=" + photo + ", rating=" + rating + ", reviewCount=" + reviewCount + ", category="
				+ category + "]";
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getResInfo() {
		return resInfo;
	}
	public void setResInfo(String content) {
		this.resInfo = content;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getrPostcode() {
		return rPostcode;
	}
	public void setrPostcode(String rPostcode) {
		this.rPostcode = rPostcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getOpentime() {
		return opentime;
	}
	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	public String getResLink() {
		return resLink;
	}
	public void setResLink(String resLink) {
		this.resLink = resLink;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
