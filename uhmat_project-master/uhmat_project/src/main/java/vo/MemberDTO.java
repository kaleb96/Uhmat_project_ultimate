package vo;

import java.sql.Date;
/*
CREATE TABLE member (
nickname VARCHAR(50) UNIQUE NOT NULL,
name VARCHAR(5),
email VARCHAR(25) PRIMARY KEY,
passwd VARCHAR(16),
birthdate DATE,
postcode VARCHAR(5),
address1 varchar(50),
address2 varchar(50),
icon VARCHAR(20) DEFAULT 'default.png',
auth_status VARCHAR(1) DEFAULT 'N',
api_id VARCHAR(60),
boardCount int 
);
*/
//멤버 관리 하는 클래스
public class MemberDTO {
	private String email;
	private String name;
	private String passwd;
	private String nickname;
	private Date birthdate;
	private Date hiredate;
	private String postCode;
	private String address1;
	private String address2;
	private String icon;
	private String auth_status;
	private String api_id;
	private int boardCount;
	
	public final int getBoardCount() {
		return boardCount;
	}
	public final void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public final String getEmail() {
		return email;
	}
	public final void setEmail(String email) {
		this.email = email;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getPasswd() {
		return passwd;
	}
	public final void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public final String getNickname() {
		return nickname;
	}
	public final void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public final Date getBirthdate() {
		return birthdate;
	}
	public final void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public final Date getHiredate() {
		return hiredate;
	}
	public final void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public final String getPostCode() {
		return postCode;
	}
	public final void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public final String getAddress1() {
		return address1;
	}
	public final void setAddress1(String address1) {
		this.address1 = address1;
	}
	public final String getAddress2() {
		return address2;
	}
	public final void setAddress2(String address2) {
		this.address2 = address2;
	}
	public final String getIcon() {
		return icon;
	}
	public final void setIcon(String icon) {
		this.icon = icon;
	}
	public final String getAuth_status() {
		return auth_status;
	}
	public final void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}
	public final String getApi_id() {
		return api_id;
	}
	public final void setApi_id(String api_id) {
		this.api_id = api_id;
	}
	@Override
	public String toString() {
		return "MemberDTO [email=" + email + ", name=" + name + ", passwd=" + passwd + ", nickname=" + nickname
				+ ", birthdate=" + birthdate + ", hiredate=" + hiredate + ", postCode=" + postCode + ", address1="
				+ address1 + ", address2=" + address2 + ", icon=" + icon + ", auth_status=" + auth_status + ", api_id="
				+ api_id + ", boardCount=" + boardCount + "]";
	}
	
	



	 
	

}
