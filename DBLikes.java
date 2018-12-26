package dao;

import bean.ProductBean;

public class DBLikes extends DBConnection {


	public DBLikes() {

        super();
    }



	public int getLikes(ProductBean bean) {
		int count = 0;
		String sql = "";
        int productid = bean.getProductID();

		if (getConnection()) {
			try {
				sql = "SELECT COUNT(*) AS amount FROM T_Likes WHERE Product_ID ="+ productid +";";

				pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery(sql);
                rs.next();
                	count = rs.getInt("amount");


			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection();
			}
		}
		return count;
	}

	//DB荳翫↓繝�繝ｼ繧ｿ縺後≠繧後�ｰtrue
	public boolean checkLikesStatus(ProductBean bean) {
		String sql = "";
		int likescount = 0;
		boolean likesFLG = false;
		int userid = bean.getUserID();
        int productid = bean.getProductID();
        //System.out.println("test2");
		if (getConnection()) {
			try {
				sql = "SELECT COUNT(*) AS amount FROM T_Likes WHERE User_ID ="+ userid +" AND Product_ID ="+ productid +";";
				//System.out.println(sql);
				pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery(sql);
                rs.next();
                likescount = rs.getInt("amount");
                //System.out.println("test1");
                //System.out.println(likescount);
                if (likescount==1) {
                	likesFLG = true;
                }
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection();
			}
		}
		return likesFLG;
	}


	public void removeLikes(ProductBean bean) {
		String sql = "";
		int userid = bean.getUserID();
        int productid = bean.getProductID();

		if (getConnection()) {
			try {
				//System.out.println("test1");
				sql = "DELETE FROM T_Likes WHERE User_ID ="+ userid +" AND Product_ID ="+ productid +";";
				pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate(sql);
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection();
			}
		}
	}


	public void addLikes(ProductBean bean) {
		String sql = "INSERT INTO T_Likes(User_ID, Product_ID) VALUES (?, ?);";
		int userid = bean.getUserID();
        int productid = bean.getProductID();

		if (getConnection()) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, userid);
				pstmt.setInt(2, productid);
				//System.out.println(sql);
                pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection();
			}
		}
	}


}
