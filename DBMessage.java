package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.MessageBean;
import bean.MessageHistoryBean;

public class DBMessage extends DBConnection {

	private int intUserId;
	private int intToUserId;

	public DBMessage(int userId) {
		super();
		this.intUserId = userId;
	}

	public void insertMessage(int toUserId, String message) {
		//メッセージを格納するメソッド
		try {
			boolean flg = getConnection();
			int intToUserId = toUserId;
			String strMessage = message;

			String sql = "insert into t_message (user_id, to_user_id, message_time, message_content) "
							+ "values (" + this.intUserId+ ","+ intToUserId +", NOW() ,'" + strMessage + "');";
			pstmt = conn.prepareStatement(sql);
			int i = pstmt.executeUpdate();
			flg = closeConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
			closeConnection();
		}
		catch(Exception e) {
			e.printStackTrace();
			closeConnection();
		}
	}

	public ArrayList<MessageBean> selectList(int userId) {
		//id,アバター,最新メッセージ

		//ArrayList
		//ArrayList<ShohinBean> arryShohin = new ArrayList<ShohinBean>();
		ArrayList<MessageBean> arryMessage = new ArrayList<MessageBean>();
		try {
			boolean flg = getConnection();
			String sql = " select T_User.User_ID, T_User.User_Name, T_User.Avatar_Path, T_Message.Message_Content"
								+" from T_Message"
								+" inner JOIN T_User ON (T_Message.User_ID = T_User.User_ID OR T_Message.To_User_ID = T_User.User_ID) AND (T_User.User_ID <> " + this.intUserId + ")"
								+" where (T_Message.User_ID = "+ this.intUserId +" OR T_Message.To_User_ID = "+ this.intUserId +") AND (least(T_Message.User_ID, T_Message.To_User_ID), greatest(T_Message.User_ID, T_Message.To_User_ID), T_Message.Message_Time) "
								+" in"
								+" ("
										+" select"
										+" least(T_Message.User_ID, T_Message.To_User_ID) as x, greatest(T_Message.User_ID, T_Message.To_User_ID) as y,"
										+" max(T_Message.Message_Time) as msg_time"
										+" from T_Message"
										+" group by x, y"
										+" ) "
								+" ORDER BY T_Message.Message_Time DESC;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MessageBean msb = new MessageBean();
				msb.setUserId(rs.getInt("t_user.user_id"));
				msb.setName(rs.getString("t_user.user_name"));
				msb.setAvpath(rs.getString("t_user.avatar_path"));
				msb.setMessage(rs.getString("t_message.message_content"));

				arryMessage.add(msb);
			}
			flg = closeConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return arryMessage;
	}

	public ArrayList<MessageHistoryBean> getHistory(int toUserId) {
		ArrayList<MessageHistoryBean> arryMhb = new ArrayList<>();
		try {
			boolean flg = getConnection();
			this.intToUserId = toUserId;
			String sql = "select user_id, message_time, message_content from t_message "
							+ "where ( user_id = "+ this.intUserId +" and to_user_id = " + this.intToUserId + ")"
							+" or ( user_id = "+ this.intToUserId +" and to_user_id = " + this.intUserId + ")"
							+ "order by message_time ASC;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MessageHistoryBean mhb = new MessageHistoryBean();
				mhb.setUserId(rs.getInt("user_id"));
				mhb.setTime(rs.getString("message_time"));
				mhb.setMessage(rs.getString("message_content"));

				arryMhb.add(mhb);
			}
			flg = closeConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return arryMhb;
	}
}

