package bean;

public class MessageHistoryBean {

	private int intUserId;
	private String strTime;
	private String strMessage;

	public MessageHistoryBean() {
		this.intUserId = 0;
		this.strTime = "";
		this.strMessage = "";
	}

	public void setUserId(int userId) {
		this.intUserId = userId;
	}
	public int getUserId() {
		return this.intUserId;
	}

	public void setTime(String time) {
		this.strTime = time;
	}
	public String getTime() {
		return this.strTime;
	}

	public void setMessage(String message) {
		this.strMessage = message;
	}
	public String getMessage() {
		return this.strMessage;
	}

}
