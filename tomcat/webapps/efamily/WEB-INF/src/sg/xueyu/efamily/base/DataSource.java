package sg.xueyu.efamily.base;

import sg.xueyu.dbhandler.db.AbsDataSource;

public class DataSource extends AbsDataSource {

	private static final String LOOKUP_NAMING = "java:comp/env/orcl/orcl";
	
	public DataSource() throws Exception {
		super(LOOKUP_NAMING);
	}
	
}
