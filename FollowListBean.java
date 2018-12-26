package bean;

public class FollowListBean {
	private int intUserId;
	private String strUserName;
	private String strAvaPath;

	public FollowListBean() {
		this.intUserId = 0;
		this.strUserName = "";
		this.strAvaPath = "";
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

	public void setAvaPath(String avaPath) {
		this.strAvaPath = avaPath;
	}
	public String getAvaPath() {
		return this.strAvaPath;
	}
}
