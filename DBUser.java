package dao;

import java.sql.SQLException;

public class DBUser extends DBConnection {

	private String strFacebookID;
	private String strMailAddress;

	public DBUser(String FacebookID, String MailAddress) {
		this.strFacebookID = FacebookID;
		this.strMailAddress = MailAddress;
	}

	public int checkLogin() {
		getConnection();
		int intLogin = 2;
		try {
			String sql = "select count(facebook_ID) from t_user where T_user.facebook_ID = " + this.strFacebookID + ";";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			intLogin = rs.getInt("count(facebook_ID)");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return intLogin;
	}

	public int insertUser(int intLogin) {
		getConnection();
		int intUserId = 0;
		try {
			if(intLogin == 0) {
				String sql = "insert into T_user (facebook_id, user_name, mail_address, birthday, Avatar_Path) values ('"+ strFacebookID +"', 'Unknow','" + strMailAddress + "','1955/1/1','ava02.png');";
				pstmt = conn.prepareStatement(sql);
				int i = pstmt.executeUpdate();
				conn.commit();
			}
			else if(intLogin == 2) {
				System.out.println("ERROR");
			}
			String sql = "select t_user.user_ID from t_user where t_user.facebook_id ="+ strFacebookID +";";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			intUserId = rs.getInt("user_id");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		closeConnection();
		return intUserId;
	}
}
