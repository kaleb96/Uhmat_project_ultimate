package vo;
/*
 	CREATE table livetalk (
 		nickname VARCHAR(10)  REFERENCES member(nickname),
		talk VARCHAR(100)
	);
 */
public class LiveTalkDTO {
	private String nickname;
	private String talk;
	private int idx;
	
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
	public final String getTalk() {
		return talk;
	}
	public final void setTalk(String talk) {
		this.talk = talk;
	}
	
	
}
