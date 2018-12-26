package bean;

public class ProductDetailBean {

	private int intCreatorId;
	private String strUserName;
	private int intProId;
	private String strProName;
	private String strAvPath;
	private int intCategory;
	private int intPrice;
	private String strImgPath;
	private String strDataPath;
	private String strProContent;

	public ProductDetailBean() {
		this.intCreatorId = 0;
		this.strUserName = "";
		this.intProId = 0;
		this.strProName = "";
		this.strAvPath = "";
		this.intCategory = 0;
		this.intPrice = 0;
		this.strImgPath = "";
		this.strDataPath = "";
		this.strProContent = "";
	}

	public void setCreatorId(int creatorId) {
		this.intCreatorId = creatorId;
	}
	public int getCreatorId() {
		return this.intCreatorId;
	}

	public void setUserName(String userName) {
		this.strUserName = userName;
	}
	public String getUserName() {
		return this.strUserName;
	}

	public void setProId(int proId) {
		this.intProId = proId;
	}
	public int getProId() {
		return this.intProId;
	}

	public void setProName(String proName) {
		this.strProName = proName;
	}
	public String getProName() {
		return this.strProName;
	}

	public void setAvPath(String avPath) {
		this.strAvPath = avPath;
	}
	public String getAvPath() {
		return this.strAvPath;
	}

	public void setCategory(int category) {
		this.intCategory = category;
	}
	public int getCategory() {
		return this.intCategory;
	}

	public void setPrice(int price) {
		this.intPrice = price;
	}
	public int getPrice() {
		return this.intPrice;
	}

	public void setImgPath(String imgPath) {
		this.strImgPath = imgPath;
	}
	public String getImgPath() {
		return this.strImgPath;
	}

	public void setDataPath(String dataPath) {
		this.strDataPath = dataPath;
	}
	public String getDataPath() {
		return this.strDataPath;
	}

	public void setProContent(String proContent) {
		this.strProContent = proContent;
	}
	public String getProContent() {
		return this.strProContent;
	}
}
