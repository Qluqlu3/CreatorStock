package bean;

public class MatchTagBean {

	private int ProId;
	private String ImgPath;

	public MatchTagBean() {
		this.ProId = 0;
		this.ImgPath = "";
	}

	public void setProId(int proId) {
		this.ProId = proId;
	}
	public int getProId() {
		return this.ProId;
	}

	public void setImgPath(String imgPath) {
		this.ImgPath = imgPath;
	}
	public String getImgPath() {
		return this.ImgPath;
	}
}
