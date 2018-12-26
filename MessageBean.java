package bean;

public class MessageBean {
	private int touserid;
	private String username;
	private String ava_path;
	private String msg;

	public MessageBean() {
		this.touserid = 0;
		this.username = "";
		this.ava_path = "";
		this.msg = "";
	}

	public void setUserId(int userId) {
		this.touserid = userId;
	}
	public int getId() {
		return this.touserid;
	}

	public void setName(String name) {
		this.username = name;
	}
	public String getName() {
		return this.username;
	}

	public void setAvpath(String avpath) {
		this.ava_path = avpath;
	}
	public String getAvpath() {
		return this.ava_path;
	}

	public void setMessage(String message) {
		this.msg = message;
	}
	public String getMessage() {
		return this.msg;
	}
}
