package vo;
/*
 	CREATE TABLE FAQ_reply(
		board_idx INT REFERENCES FAQBoard(idx),
		nickname VARCHAR(10) REFERENCES member(nickname),
		answer VARCHAR(3000) NOT NULL
	);
 */
public class FAQReplyDTO {
	private int board_idx;
	private String nickname;
	private String answer;
	
	public final int getBoard_idx() {
		return board_idx;
	}
	public final void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public final String getNickname() {
		return nickname;
	}
	public final void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public final String getAnswer() {
		return answer;
	}
	public final void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
