package sg.xueyu.efamily.base;

import java.sql.Connection;
import java.sql.SQLException;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.system.EFamilyParam;

public class NoPoolConnection {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return DBUtils.getNoPoolConnection(EFamilyParam.DB_DRIVER, EFamilyParam.DB_URL, EFamilyParam.DB_USERNAME, EFamilyParam.DB_PASSWORD);
	}

}
