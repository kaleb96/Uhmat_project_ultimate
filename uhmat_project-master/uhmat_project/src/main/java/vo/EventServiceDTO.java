package vo;

import java.security.Timestamp;

/*
CREATE table event_service (
	idx INT PRIMARY KEY,
	nickname VARCHAR(10) REFERENCES member(nickname), // 이벤트 참여시 멤버의 닉네임 필요
	subject VARCHAR(50) NOT NULL,
	content VARCHAR(10000) NOT NULL,
	service_photo1 VARCHAR(50),  // 사용자가 이벤트 참여시 올릴 파일
	service_original_photo1 VARCHAR(50),
	service_photo2 VARCHAR(50),  
	service_original_photo2 VARCHAR(50),
	service_photo3 VARCHAR(50),  
	service_original_photo3 VARCHAR(50),
	service_photo4 VARCHAR(50),  
	service_original_photo4 VARCHAR(50),
	service_photo5 VARCHAR(50),  
	service_original_photo5 VARCHAR(50),
	datetime TIMESTAMP DEFAULT now()
);
 */
public class EventServiceDTO {

	private int idx;
	private String nickname;
	private String subject;
	private String content;
	private String service_photo1;
	private String service_original_photo1;
	private String service_photo2;
	private String service_original_photo2;
	private String service_photo3;
	private String service_original_photo3;
	private String service_photo4;
	private String service_original_photo4;
	private String service_photo5;
	private String service_original_photo5;

	private Timestamp datetime;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getService_photo1() {
		return service_photo1;
	}
	public void setService_photo1(String service_photo1) {
		this.service_photo1 = service_photo1;
	}
	public String getService_original_photo1() {
		return service_original_photo1;
	}
	public void setService_original_photo1(String service_orginal_photo1) {
		this.service_original_photo1 = service_orginal_photo1;
	}
	public String getService_photo2() {
		return service_photo2;
	}
	public void setService_photo2(String service_photo2) {
		this.service_photo2 = service_photo2;
	}
	public String getService_original_photo2() {
		return service_original_photo2;
	}
	public void setService_original_photo2(String service_orginal_photo2) {
		this.service_original_photo2 = service_orginal_photo2;
	}
	public String getService_photo3() {
		return service_photo3;
	}
	public void setService_photo3(String service_photo3) {
		this.service_photo3 = service_photo3;
	}
	public String getService_original_photo3() {
		return service_original_photo3;
	}
	public void setService_original_photo3(String service_orginal_photo3) {
		this.service_original_photo3 = service_orginal_photo3;
	}
	public String getService_photo4() {
		return service_photo4;
	}
	public void setService_photo4(String service_photo4) {
		this.service_photo4 = service_photo4;
	}
	public String getService_original_photo4() {
		return service_original_photo4;
	}
	public void setService_original_photo4(String service_orginal_photo4) {
		this.service_original_photo4 = service_orginal_photo4;
	}
	public String getService_photo5() {
		return service_photo5;
	}
	public void setService_photo5(String service_photo5) {
		this.service_photo5 = service_photo5;
	}
	public String getService_original_photo5() {
		return service_original_photo5;
	}
	public void setService_original_photo5(String service_orginal_photo5) {
		this.service_original_photo5 = service_orginal_photo5;
	}
	public Timestamp getDatetime() {
		return datetime;
	}
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "EventServiceDTO [idx=" + idx + ", nickname=" + nickname + ", subject=" + subject + ", content="
				+ content + ", service_photo1=" + service_photo1 + ", service_original_photo1="
				+ service_original_photo1 + ", service_photo2=" + service_photo2 + ", service_original_photo2="
				+ service_original_photo2 + ", service_photo3=" + service_photo3 + ", service_original_photo3="
				+ service_original_photo3 + ", service_photo4=" + service_photo4 + ", service_original_photo4="
				+ service_original_photo4 + ", service_photo5=" + service_photo5 + ", service_original_photo5="
				+ service_original_photo5 + ", datetime=" + datetime + "]";
	}

	
	
	
	
}
