package bean;

public class ProductCommentBean {

		private int intUserId;
		private String strUserName;
		private String strAvPath;
		private String strComment;
		private String strTime;

	public ProductCommentBean() {
		this.intUserId = 0;
		this.strUserName = "";
		this.strAvPath = "";
		this.strComment = "";
		this.strTime = "";
	}

	public void setUserId(int userId) {
		this.intUserId = userId;
	}
	public int getUserId() {
		return this.intUserId;
	}

	public void setUserName(String userName) {
		this.strUserName = userName;
	}
	public String getUserName() {
		return this.strUserName;
	}

	public void setAvPath(String avPath) {
		this.strAvPath = avPath;
	}
	public String getAvPath() {
		return this.strAvPath;
	}

	public void setComment(String comment) {
		this.strComment = comment;
	}
	public String getComment() {
		return this.strComment;
	}

	public void setTime(String time) {
		this.strTime = time;
	}
	public String getTime() {
		return this.strTime;
	}
}
