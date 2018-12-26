package dao;


import java.sql.SQLException;

import bean.ProductBean;


public class DBProduct extends DBConnection {

	public DBProduct() {
		super();
	}



	public void addProduct(ProductBean bean) {
		String sql = "";
		sql = "INSERT INTO T_Product(Product_Name,Profile_content,Data_Path,image_Path,Price,Category_ID,Creator_ID,Pass_Flag) VALUES(?,?,?,?,?,?,?,?);";
		if(getConnection()) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bean.getProductName());
				pstmt.setString(2, bean.getProfileContent());
				pstmt.setString(3, bean.getDataPath());
				pstmt.setString(4, bean.getImagePath());
				pstmt.setInt(5, bean.getPrice());
				pstmt.setInt(6, bean.getCategoryID());
				pstmt.setInt(7, bean.getUserID());//session
                pstmt.setInt(8, 1);
				pstmt.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnection();
			}
		}
	}


	public void addProductTag(String[] values) {
		String sql = "";
		sql = "INSERT INTO T_Tags(Product_ID, Tag_Name) values(?, ?);";

		int productid = selectProductID();

		if(getConnection()) {
			try{
				pstmt = conn.prepareStatement(sql);
				for(String tag : values){
					pstmt.setInt(1, productid);
					pstmt.setString(2, tag);
					//debug
					System.out.print("ProductID = " + productid + ": ");
					System.out.println("TagName = "+ tag + ",");
					//debug end
					pstmt.executeUpdate();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				closeConnection();
			}
		}
	}


	public int selectProductID() {
		int intProductID = 0;
		String sql = "";
		sql = "SELECT * FROM T_Product;";

		if(getConnection()) {
            try {
            	pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery(sql);
                //最後のカーソルに最新のProductIDが入っている
                rs.last();
                intProductID = rs.getInt("Product_ID");
            }
            catch(Exception e) {
            	e.printStackTrace();
            }
            finally {
                closeConnection();
            }
        }
		return intProductID;
	}


}
