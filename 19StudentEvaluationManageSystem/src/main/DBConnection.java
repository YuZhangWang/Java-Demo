package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	//获得数据库连接
	public static Connection getConnection(){
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://127.0.0.1:3306/student?useUnicode=true&characterEncoding=UTF-8";
			String user = "root";
			String password = "666419";

			conn = DriverManager.getConnection(url, user, password);

			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//throw new RuntimeException("�Ҳ�����");
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new RuntimeException("url��user��password���?");
		}
		return conn;
	}

	//关闭数据库资源
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn)
			throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	// 关闭数据库资源(delete update insert)
	public static void close(PreparedStatement pstmt, Connection conn) throws Exception {
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getConnection());
	}
}

