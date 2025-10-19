package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 独自クラスとして作った、DBへの接続専用クラス。
 *
 */
public class DBConnector {
	private final static String URL = "jdbc:mysql://localhost:3306/test2";
	private final static String USER = "root";
	private final static String PASS = "password";

	/**
	 * DBへの接続をメソッドでまとめたもの。
	 * @return Connection型
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
