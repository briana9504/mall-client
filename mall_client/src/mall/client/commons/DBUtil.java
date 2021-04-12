package mall.client.commons;
import java.sql.*;

public class DBUtil {
	//1. DB연결
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mall","root","java1004");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	//2. DB연결(conn, stmt, rs) 해제 -gabage collect가 정리하기 전에 정리해야할 것이 있음: 강제로 메모리 안에서 삭제해줄 필요가 있음
	//정리할때는 최근에 생긴 순서로 정리함
	public void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
