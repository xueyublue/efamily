package sg.xueyu.efamily.base;

import java.sql.Connection;
import java.sql.SQLException;

import sg.xueyu.dbhandler.db.DBUtils;

public class NoPoolConnection {

	// TODO: Get parameters from properties or XML
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER = "xueyu";
	private static final String PASSWORD = "xueyu";

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return DBUtils.getNoPoolConnection(URL, USER, PASSWORD, DRIVER);
	}

}
