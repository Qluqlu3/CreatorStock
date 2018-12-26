package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.FollowListBean;

public class DBFollowList extends DBConnection {

	private int intUserId;

	public DBFollowList(int userId) {
		super();
		this.intUserId = userId;
	}

	public ArrayList<FollowListBean> selectFollowList() {

		ArrayList<FollowListBean> arryFollow = new ArrayList<>();
		boolean flg = false;

		try {
			flg = getConnection();
			String sql = "select user_id, user_name, avatar_path "
							+ "from t_follow_list inner join t_user on follow_to_id = user_id "
							+"where follow_id = " + this.intUserId +";";
			//System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FollowListBean flb = new FollowListBean();
				flb.setUserId(rs.getInt("user_id"));
				flb.setUserName(rs.getString("user_name"));
				flb.setAvaPath(rs.getString("avatar_path"));

				arryFollow.add(flb);
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
		return arryFollow;
	}

	public void deleteFollow(int intDeleteFollowId) {

		boolean flg = false;

		try {
			flg = getConnection();
			String sql = "delete from t_Follow_List where follow_id = "+ this.intUserId +" and follow_to_id = "+ intDeleteFollowId +";";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			conn.commit();
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
	}
}
