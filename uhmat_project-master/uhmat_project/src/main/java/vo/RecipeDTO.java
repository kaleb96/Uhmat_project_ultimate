package vo;

import java.sql.Timestamp;

public class RecipeDTO {
	private int idx;
	private String nickname;
	private String subject;
	private String content;
	private int readcount;
	private String original_File1;
	private String real_File1;
	private String original_File2;
	private String real_File2;
	private String original_File3;
	private String real_File3;
	private String original_File4;
	private String real_File4;
	private String original_File5;
	private String real_File5;
	private Timestamp date;
	private String report;
	private String category;
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
	public String getOriginal_File1() {
		return original_File1;
	}
	public void setOriginal_File1(String original_File1) {
		this.original_File1 = original_File1;
	}
	public String getReal_File1() {
		return real_File1;
	}
	public void setReal_File1(String real_File1) {
		this.real_File1 = real_File1;
	}
	public String getOriginal_File2() {
		return original_File2;
	}
	public void setOriginal_File2(String original_File2) {
		this.original_File2 = original_File2;
	}
	public String getReal_File2() {
		return real_File2;
	}
	public void setReal_File2(String real_File2) {
		this.real_File2 = real_File2;
	}
	public String getOriginal_File3() {
		return original_File3;
	}
	public void setOriginal_File3(String original_File3) {
		this.original_File3 = original_File3;
	}
	public String getReal_File3() {
		return real_File3;
	}
	public void setReal_File3(String real_File3) {
		this.real_File3 = real_File3;
	}
	public String getOriginal_File4() {
		return original_File4;
	}
	public void setOriginal_File4(String original_File4) {
		this.original_File4 = original_File4;
	}
	public String getReal_File4() {
		return real_File4;
	}
	public void setReal_File4(String real_File4) {
		this.real_File4 = real_File4;
	}
	public String getOriginal_File5() {
		return original_File5;
	}
	public void setOriginal_File5(String original_File5) {
		this.original_File5 = original_File5;
	}
	public String getReal_File5() {
		return real_File5;
	}
	public void setReal_File5(String real_File5) {
		this.real_File5 = real_File5;
	}
	
	
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
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
	@Override
	public String toString() {
		return "RecipeDTO [idx=" + idx + ", nickname=" + nickname + ", subject=" + subject + ", content=" + content
				+ ", readcount=" + readcount + ", original_File1=" + original_File1 + ", real_File1=" + real_File1
				+ ", original_File2=" + original_File2 + ", real_File2=" + real_File2 + ", original_File3="
				+ original_File3 + ", real_File3=" + real_File3 + ", original_File4=" + original_File4 + ", real_File4="
				+ real_File4 + ", original_File5=" + original_File5 + ", real_File5=" + real_File5 + ", datetime="
				+ date + ", report=" + report + "]";
	}
	
	
	
	
}
