package sg.xueyu.efamily.base;

import java.sql.Connection;
import java.sql.SQLException;

import sg.xueyu.dbhandler.db.AbsDataSource;

public class DataSource extends AbsDataSource {

	private static final String LOOKUP_NAMING = "java:comp/env/orcl/orcl";
	
	public DataSource() throws Exception {
		super(LOOKUP_NAMING);
	}
	
	public Connection getConnection() throws SQLException {
		return super.getConnection();
	}
	
}
