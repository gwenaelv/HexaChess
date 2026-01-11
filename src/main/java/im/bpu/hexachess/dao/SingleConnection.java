package im.bpu.hexachess.dao;

import im.bpu.hexachess.Config;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SingleConnection {
	private static final String DEFAULT_DB_URL =
		"jdbc:mysql://localhost:3306/hexachess?serverTimezone=UTC";
	private static final String DEFAULT_DB_USER = "root";
	private static final String DEFAULT_WINDOWS_PASS = "";
	private static final String DEFAULT_LINUX_PASS = "password123";
	private static Connection connect;
	private SingleConnection() throws SQLException {
		String url = Config.get("DB_URL", DEFAULT_DB_URL);
		String login = Config.get("DB_USER", DEFAULT_DB_USER);
		String password = Config.get("DB_PASS", getPassword());
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);
		connect = mysqlDS.getConnection();
	}
	private String getPassword() {
		String osName = System.getProperty("os.name").toLowerCase();
		boolean isWindows = osName.contains("win");
		return isWindows ? DEFAULT_WINDOWS_PASS : DEFAULT_LINUX_PASS;
	}
	public static synchronized Connection getInstance() throws SQLException {
		if (connect == null || connect.isClosed())
			new SingleConnection();
		return connect;
	}
	public static void close() {
		try {
			if (connect != null && !connect.isClosed())
				connect.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}