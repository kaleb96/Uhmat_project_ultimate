package vo;

import java.sql.*;

public class ReviewReplyDTO {
	
	private int idx;
	private String nickname;
	private String content;
	private Timestamp date;
	private int board_idx;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	
	@Override
	public String toString() {
		return "ReviewReplyDTO [idx=" + idx + ", nickname=" + nickname + ", content=" + content + ", date=" + date
				+ ", board_idx=" + board_idx + "]";
	}
	
	
}
