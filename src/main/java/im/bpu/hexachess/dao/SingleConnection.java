package im.bpu.hexachess.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SingleConnection {
	private static Connection connect;

	private SingleConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/hexachess?serverTimezone=UTC";
		String login = "root";
		String password = getPassword();

		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);

		connect = mysqlDS.getConnection();
	}

	private String getPassword() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win")) {
			return "";
		}
		return "password123";
	}

	public static Connection getInstance() throws ClassNotFoundException, SQLException {
		if (connect == null || connect.isClosed()) {
			new SingleConnection();
		}
		return connect;
	}

	public static void close() {
		try {
			if (connect != null && !connect.isClosed()) {
				connect.close();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
