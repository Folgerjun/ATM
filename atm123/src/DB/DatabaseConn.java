package DB;

import java.sql.*;

public class DatabaseConn {
	private Statement stmt = null;
	ResultSet rs = null;
	private Connection conn = null;
	String sql;

	public DatabaseConn() {}

	public void OpenConn() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm123?useUnicode=true&characterEncoding=UTF-8","root",
					"mysql123");
		} catch (Exception e) {
			System.out.println("OpenConn:" + e.getMessage());
		}
	}

	public ResultSet executeQuery(String sql) {
		stmt = null;
		rs = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("查询数据:" + e.getMessage());
		}
		return rs;
	}

	public void executeUpdate(String sql) {
		stmt = null;
		rs = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("更新数据:" + e.getMessage());
		}
	}

	public void closeStmt() {
		try {
			stmt.close();
		} catch (SQLException e) {
			System.err.println("释放对象:" + e.getMessage());
		}
	}

	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException ex) {
			System.err.println("释放对象:" + ex.getMessage());
		}
	}

	public static String toUTF(String str) {
		try {
			if (str == null)
				str = "";
			else
				str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			System.out.println(e);
		}
		return str;
	}
}
