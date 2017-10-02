package sg.xueyu.efamily.dao;

import java.util.ArrayList;
import java.util.List;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.util.HandlerUtil;
import sg.xueyu.efamily.base.DataSource;
import sg.xueyu.efamily.base.dbhandler.LoginUserHandler;
import sg.xueyu.efamily.base.dbhandler.LoginUserSearchKey;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;

public class UserDao extends DataSource {

	private UserDao() {
		super();
	}

	public static List<LoginUserEJB> getAllUsers() throws Exception {
		LoginUserHandler handler = new LoginUserHandler(new UserDao().getConnection());
		LoginUserSearchKey searchKey = new LoginUserSearchKey();
		
		AbstractEntity[] results = handler.query(searchKey);
		List<LoginUserEJB> actualResults = new ArrayList<>();
		
		for (int i = 0; i < results.length; i++) {
			actualResults.add((LoginUserEJB) HandlerUtil.entity2bean(results[i], LoginUserEJB.class));
		}
		
		return actualResults;
	}

	public static List<LoginUserEJB> verify(String userId, String password) throws Exception {
		LoginUserHandler handler = new LoginUserHandler(new UserDao().getConnection());
		LoginUserSearchKey searchKey = new LoginUserSearchKey();
		searchKey.setUserId(userId);
		searchKey.setPassword(password);
		
		AbstractEntity[] results = handler.query(searchKey);
		List<LoginUserEJB> actualResults = new ArrayList<>();
		
		for (int i = 0; i < results.length; i++) {
			actualResults.add((LoginUserEJB) HandlerUtil.entity2bean(results[i], LoginUserEJB.class));
		}
		
		return actualResults;
	}
}
