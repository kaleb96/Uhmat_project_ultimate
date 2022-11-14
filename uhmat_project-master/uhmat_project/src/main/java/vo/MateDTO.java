package vo;

import java.sql.Timestamp;

public class MateDTO {

	private int idx;
	private String nickname;
	private String subject;
	private String content;
	private int readcount;
	private Timestamp date;
	private String category;
	private String report;
	private String icon;
	
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
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
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	@Override
	public String toString() {
		return "MateDTO [idx=" + idx + ", nickname=" + nickname + ", subject=" + subject + ", content=" + content
				+ ", readcount=" + readcount + ", date=" + date + ", category=" + category + ", report=" + report
				+ ", icon=" + icon + "]";
	}
	
	
	
}
