package sg.xueyu.efamily.dao;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.efamily.base.DataSource;
import sg.xueyu.efamily.base.dbhandler.LoginUser;
import sg.xueyu.efamily.base.dbhandler.LoginUserHandler;
import sg.xueyu.efamily.base.dbhandler.LoginUserSearchKey;

public class UserDao extends DataSource {

	private UserDao() {
		super();
	}
	
	public static LoginUser[]  getAllUsers() 
			throws Exception {
		LoginUserHandler handler = new LoginUserHandler(new UserDao().getConnection());
		LoginUserSearchKey searchKey = new LoginUserSearchKey();
		AbstractEntity[] results = handler.query(searchKey);
		
		if (results.length > 0) {
			LoginUser[] actualResults = new LoginUser[results.length];
			for (int i = 0; i < results.length; i++) {
				actualResults[i] = (LoginUser) results[i];
			}
			
			return actualResults;
		}
		
		return null;
	}
}
