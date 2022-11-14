package vo;

/*
CREATE TABLE auth_info (
		id VARCHAR(16) PRIMARY KEY,
		auth_code VARCHAR(50) NOT NULL
);
*/

//인증코드를 관리하는 클래스 정의
public class AuthInfoDTO {
	private String email;
	private String authCode;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
}