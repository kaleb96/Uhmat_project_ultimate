package vo;

import java.sql.Timestamp;

public class MateReplyDTO {
	private int idx;
	private String nickname;
	private String content;
	private int re_ref;
	private int re_lev;
	private int re_seq;
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
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public int getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
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
		return "CommentDTO [idx=" + idx + ", nickname=" + nickname + ", content=" + content + ", re_ref=" + re_ref
				+ ", re_lev=" + re_lev + ", re_seq=" + re_seq + ", date=" + date + ", board_idx=" + board_idx + "]";
	}
	
	
	
	
	
	
}
