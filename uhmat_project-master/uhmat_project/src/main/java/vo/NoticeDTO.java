package vo;

import java.sql.*;

/*
 	CREATE TABLE NoticeBoard(
		idx INT PRIMARY KEY,
		nickname VARCHAR(10) REFERENCES member(nickname),
		subject VARCHAR(20)  NOT NULL,
		content VARCHAR(2000) NOT NULL,
		date DATE NOT NULL,
		real_File VARCHAR(100),
		original_File VARCHAR(100),
		category VARCHAR(16) REFERENCES Service_Category(notice_category)
	);
 		
 */

public class NoticeDTO {
	private int idx;
	private String nickname;
	private String subject;
	private String content;
	private Date date;
	private String real_File;
	private String original_File;
	private String category;
	
	public final int getIdx() {
		return idx;
	}
	public final void setIdx(int idx) {
		this.idx = idx;
	}
	public final String getNickname() {
		return nickname;
	}
	public final void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public final String getSubject() {
		return subject;
	}
	public final void setSubject(String subject) {
		this.subject = subject;
	}
	public final String getContent() {
		return content;
	}
	public final void setContent(String content) {
		this.content = content;
	}
	public final Date getDate() {
		return date;
	}
	public final void setDate(Date date) {
		this.date = date;
	}
	public final String getReal_File() {
		return real_File;
	}
	public final void setReal_File(String real_File) {
		this.real_File = real_File;
	}
	public final String getOriginal_File() {
		return original_File;
	}
	public final void setOriginal_File(String original_File) {
		this.original_File = original_File;
	}
	public final String getCategory() {
		return category;
	}
	public final void setCategory(String category) {
		this.category = category;
	}
	
	
}
