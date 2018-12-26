package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.MatchTagBean;
import bean.ProductCommentBean;
import bean.ProductDetailBean;
import bean.ProductListCommentBean;

public class DBProductDetail extends DBConnection {

	private int intUserId;
	private int intProductId;

	public DBProductDetail(int userId, int productId) {
		super();
		this.intUserId = userId;
		this.intProductId = productId;
	}

	public ArrayList<ProductDetailBean> selectDetail() {

		ArrayList<ProductDetailBean> arrypdb = new ArrayList<>();
		boolean flg = false;

		try {
			flg = getConnection();

				String sql = "select creator_id, user_name, product_name, avatar_path, category_id, price, image_path, data_path, profile_content"
								+ " from t_product inner join t_user on t_product.creator_id = t_user.user_id"
								+" where product_id = "+ this.intProductId +";";

				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				ProductDetailBean pdb = new ProductDetailBean();

				pdb.setCreatorId(rs.getInt("creator_id"));
				pdb.setUserName(rs.getString("user_name"));
				pdb.setProName(rs.getString("product_name"));
				pdb.setAvPath(rs.getString("avatar_path"));
				pdb.setCategory(rs.getInt("category_id"));
				pdb.setPrice(rs.getInt("price"));
				pdb.setImgPath(rs.getString("image_path"));
				pdb.setDataPath(rs.getString("data_path"));
				pdb.setProContent(rs.getString("profile_content"));

				arrypdb.add(pdb);
		}
		catch(SQLException e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		flg = closeConnection();
		return arrypdb;
	}

	public ArrayList<ProductCommentBean> selectComment() {

		ArrayList<ProductCommentBean> arrypcb = new ArrayList<>();
		boolean flg = false;

		try {
			flg = getConnection();

			String sql = "select user_name, avatar_path, comment_content, comment_time"
								+" from t_comment inner join t_user on t_comment.user_id = t_user.user_id"
								+" where t_comment.product_id = "+ this.intProductId
								+" order by comment_time DESC;";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductCommentBean pcb = new ProductCommentBean();
				pcb.setUserName(rs.getString("user_name"));
				pcb.setAvPath(rs.getString("avatar_path"));
				pcb.setComment(rs.getString("comment_content"));
				pcb.setTime(rs.getString("comment_time"));
				arrypcb.add(pcb);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		flg = closeConnection();
		return arrypcb;
	}

	public void insertComment(String comment) {
		boolean flg = false;
		try {
			flg = getConnection();

			String strComment = comment;
			String sql = "insert into t_comment (user_id, product_id, comment_time, comment_content)"
								+ " values ("+ this.intUserId +","+ this.intProductId +", NOW() ,"+ strComment +")";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			conn.commit();

			flg = closeConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			flg = closeConnection();
		}
	}

	//タグ表示
	public ArrayList<String> getTag() {

		ArrayList<String> arryTag = new ArrayList<>();
		boolean flg = false;

		try {
			flg = getConnection();
			String sql = "select tag_name from t_tags where product_id = "+ this.intProductId +";";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				arryTag.add(rs.getString("tag_name"));
			}
			flg = closeConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		return arryTag;
	}

	//タグ一致作品取得
	public ArrayList<MatchTagBean> matchTag() {

		ArrayList<MatchTagBean> arryMatchTag = new ArrayList<>();
		boolean flg = false;

		try {
			flg = getConnection();
			//String sql = "select distinct t_tags.product_id as product_id, t_product.image_path as image_path"
			//					+" from t_tags inner join t_product on t_tags.product_id = t_product.product_id"
			//					+" where t_tags.tag_name in (select tag_name from t_tags "
			//					+" where product_id = "+ this.intProductId + " and t_tags.product_id != "+ this.intProductId
			//					+" order by rand() limit 3;";
			String sql = "SELECT DISTINCT T_Tags.Product_ID AS product_id ,T_Product.image_Path AS image_path " +
					"FROM T_Tags " +
					"LEFT JOIN T_Product ON T_Tags.Product_ID = T_Product.Product_ID " +
					"WHERE T_Tags.Tag_Name IN (SELECT Tag_Name FROM T_Tags WHERE Product_ID = " + this.intProductId + ") AND T_Tags.Product_ID != "+ this.intProductId +" ORDER BY Rand() LIMIT 3";

			//System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MatchTagBean mtb = new MatchTagBean();
				mtb.setProId(rs.getInt("product_id"));
				mtb.setImgPath(rs.getString("Image_path"));

				arryMatchTag.add(mtb);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		flg = closeConnection();
		return arryMatchTag;
	}






	//商品一覧
	public ArrayList<ProductDetailBean> selectProductList() {

		ArrayList<ProductDetailBean> arry = new ArrayList<>();
		boolean flg = false;

		try {
			flg = getConnection();

			String sql = "select user_name, avatar_path, product_id, product_name, category_id, price, image_path, data_path, profile_content"
								+" from t_product inner join t_user on t_product.creator_id = t_user.user_id"
								+" where t_product.creator_id = "+ this.intUserId +";";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDetailBean pdb = new ProductDetailBean();
				//ユーザー名
				pdb.setUserName(rs.getString("user_name"));
				pdb.setProId(rs.getInt("product_id"));
				pdb.setProName(rs.getString("product_name"));
				pdb.setAvPath(rs.getString("avatar_path"));
				pdb.setCategory(rs.getInt("category_id"));
				pdb.setPrice(rs.getInt("price"));
				pdb.setImgPath(rs.getString("image_path"));
				pdb.setDataPath(rs.getString("data_path"));
				pdb.setProContent(rs.getString("profile_content"));
				arry.add(pdb);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		flg = closeConnection();
		return arry;
	}

	//最新コメント取得
	public ArrayList<ProductListCommentBean> selectLatestComment() {

		ArrayList<ProductListCommentBean> arry = new ArrayList<>();
		boolean flg = false;

		try {
			flg = getConnection();

			String sql = "select p.product_id, t_user.user_id, user_name, avatar_path, comment_content, comment_time"
								+" from (t_product p left outer join t_comment"
								+" on p.Product_ID = t_comment.product_id)"
								+" left outer join t_user"
								+" on t_comment.User_ID = T_User.User_ID"
								+" where t_comment.Product_ID IN ("
									+" select Product_ID"
									+" from t_product"
									+" where creator_id = "+ this.intUserId
									+" )"
									+" and 3 >= ("
										+" SELECT COUNT(*)"
										+" FROM t_comment c"
										+" WHERE (p.Product_ID = c.Product_ID"
										+" AND c.Comment_Time >= t_comment.Comment_Time)"
									+" )"
									+" or (t_comment.User_ID is null and creator_id = " + this.intUserId +" )"
									+" order by p.Product_ID, Comment_Time DESC;";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductListCommentBean plcb = new ProductListCommentBean();

				plcb.setProId(rs.getInt("p.product_id"));
				plcb.setUserId(rs.getInt("t_user.user_id"));
				plcb.setUserName(rs.getString("user_name"));
				plcb.setAvPath(rs.getString("avatar_path"));
				plcb.setComment(rs.getString("comment_content"));
				plcb.setTime(rs.getString("comment_time"));
				arry.add(plcb);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		flg = closeConnection();
		return arry;
	}

	//商品削除
	public void deleteProduct() {

		boolean flg = false;

		try {
			flg = getConnection();
			String sql = "delete from t_product where product_id = "+ this.intProductId +";";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			conn.commit();
			flg = closeConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
			flg = closeConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			flg = closeConnection();
		}
	}
}
