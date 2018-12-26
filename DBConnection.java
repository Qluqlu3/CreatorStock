package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	//データベース接続処理
	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	public  DBConnection() {
		this.conn = null;
		this.pstmt = null;
		this.rs = null;
	}

	public boolean getConnection() {

		//final String url = "jdbc:mariadb://localhost/creatorstock";
		//接続ユーザ
		//final String strDBUser = "root";
		//接続パスワード
		//final String strPass = "";

		 boolean flg = true;
		try {
			//Class.forName("org.mariadb.jdbc.Driver");
            //conn = DriverManager.getConnection(url, strDBUser, strPass);
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/CreatorStock?user=root&password=root&useUnicode=true&characterEncoding=utf8");
		}
		catch(ClassNotFoundException e) {
			//eclipseのコンソール
			e.printStackTrace();
			flg = false;
		}
		catch(SQLException e) {
			//eclipseのコンソール
			e.printStackTrace();
			flg = false;
		}
		catch(Exception e) {
			//eclipseのコンソール
			e.printStackTrace();
			flg = false;
		}
		return flg;
	}
	public boolean closeConnection() {

		boolean flg = true;
		try {
			//⑤クローズ処理
			//リザルトセットクローズ
			if(this.rs != null) {
				rs.close();
			}
			if(this.pstmt != null) {
				pstmt.close();
			}
			if(this.conn != null) {
				conn.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			flg = false;
		}
		return flg;
	}
	public Connection getConnectionchai(){
		
		//final String url = "jdbc:mariadb://localhost/creatorstock";
		//接続ユーザ
		//final String strDBUser = "root";
		//接続パスワード
		//final String strPass = "";
		
		try {
            //Class.forName("org.mariadb.jdbc.Driver");
            //conn = DriverManager.getConnection(url, strDBUser, strPass);
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/CreatorStock?user=root&password=root&useUnicode=true&characterEncoding=utf8");
		}
		catch(ClassNotFoundException e) {
			//eclipseのコンソール
			e.printStackTrace();
		}
		catch(SQLException e) {
			//eclipseのコンソール
			e.printStackTrace();
		}
		catch(Exception e) {
			//eclipseのコンソール
			e.printStackTrace();
		}
		return conn;
	}
	
}
