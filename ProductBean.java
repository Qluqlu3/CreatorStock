package bean;


public class ProductBean {

	private String strProductName;
	private String strProfileContent;
	private String strDataPath;
	private String strImagePath;
	private int intProductID;
	private int intUserID;
	private int intPrice;
	private int intCategoryID;



	public ProductBean() {
		this.strProductName = "";
		this.strProfileContent = "";
		this.strDataPath = "";
		this.strImagePath = "";
		this.intProductID = 0;
		this.intUserID = 0;
		this.intPrice = 0;
		this.intCategoryID = 0;
	}



	//作品名
	public String getProductName() {
		return this.strProductName;
	}
	public void setProductName(String ProductName) {
		this.strProductName = ProductName;
	}
	//プロフィール文
	public String getProfileContent() {
		return this.strProfileContent;
	}
	public void setProfileContent(String Profilecontent) {
		this.strProfileContent = Profilecontent;
	}
	//データのパス
	public String getDataPath() {
		return this.strDataPath;
	}
	public void setDataPath(String DataPath) {
		this.strDataPath = DataPath;
	}
	//サムネイルのパス
	public String getImagePath() {
		return this.strImagePath;
	}
	public void setImagePath(String ImagePath) {
		this.strImagePath = ImagePath;
	}
	//商品ID
	public int getProductID() {
		return this.intProductID;
	}
	public void setProductID(int ProductID) {
		this.intProductID = ProductID;
	}
	//作者ID
	public int getUserID() {
		return this.intUserID;
	}
	public void setUserID(int UserID) {
		this.intUserID = UserID;
	}
	//作品価格
	public int getPrice() {
		return this.intPrice;
	}
	public void setPrice(int Price) {
		this.intPrice = Price;
	}
	//カテゴリID
	public int getCategoryID() {
		return this.intCategoryID;
	}
	public void setCategoryID(int CategoryID) {
		this.intCategoryID = CategoryID;
	}
}
