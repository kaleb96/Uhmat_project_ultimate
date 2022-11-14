package vo;

public class ReviewLikeDTO {
	private int idx;
	private int likes;
	private String nickname;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "ReviewLikeDTO [idx=" + idx + ", likes=" + likes + ", nickname=" + nickname + "]";
	}
	
}
