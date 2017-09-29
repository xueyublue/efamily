package sg.xueyu.efamily.base;

import sg.xueyu.dbhandler.db.AbstractDataSource;

public class DataSource extends AbstractDataSource {

	private static final String LOOKUP_NAMING = "java:comp/env/orcl/orcl";
	
	public DataSource() {
		super(LOOKUP_NAMING);
		
	}
	
}
