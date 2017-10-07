package sg.xueyu.efamily.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.xueyu.dbhandler.db.DBUtils;
import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.util.HandlerUtil;
import sg.xueyu.efamily.base.DataSource;
import sg.xueyu.efamily.base.dbhandler.LoginUserAlterKey;
import sg.xueyu.efamily.base.dbhandler.LoginUserHandler;
import sg.xueyu.efamily.base.dbhandler.LoginUserSearchKey;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;

public class UserDao extends DataSource {

	private UserDao() {
		super();
	}

	public static List<LoginUserEJB> getAllUsers() throws Exception {
		Connection connection = new DataSource().getConnection();
		LoginUserHandler handler = new LoginUserHandler(connection);
		LoginUserSearchKey searchKey = new LoginUserSearchKey();

		AbstractEntity[] results = handler.query(searchKey);
		List<LoginUserEJB> actualResults = new ArrayList<>();

		for (int i = 0; i < results.length; i++) {
			actualResults.add((LoginUserEJB) HandlerUtil.entity2bean(results[i], LoginUserEJB.class));
		}
		DBUtils.closeConnection(connection);
		return actualResults;
	}

	public static String auth(String userId, String password) throws Exception {
		Connection connection = new DataSource().getConnection();
		LoginUserHandler handler = new LoginUserHandler(connection);
		LoginUserSearchKey searchKey = new LoginUserSearchKey();
		searchKey.setUserId(userId);
		if (handler.query(searchKey).length == 0) {
			DBUtils.closeConnection(connection);
			return "UserId is invalid";
		} else {
			searchKey.clear();
			searchKey.setUserId(userId);
			searchKey.setPassword(password);
			if (handler.query(searchKey).length == 0) {
				DBUtils.closeConnection(connection);
				return "Password is wrong";
			}
		}
		DBUtils.closeConnection(connection);
		return null;
	}

	public static LoginUserEJB getUser(String userId) throws Exception {
		Connection connection = new DataSource().getConnection();
		LoginUserHandler handler = new LoginUserHandler(connection);
		LoginUserSearchKey searchKey = new LoginUserSearchKey();
		searchKey.setUserId(userId);

		AbstractEntity[] results = handler.query(searchKey);
		if (results.length == 1) {
			DBUtils.closeConnection(connection);
			return (LoginUserEJB) HandlerUtil.entity2bean(results[0], LoginUserEJB.class);
		}

		DBUtils.closeConnection(connection);
		return null;
	}

	public static void udpateLastLoginDate(String userId) throws Exception {
		Connection connection = new DataSource().getConnection();
		LoginUserHandler handler = new LoginUserHandler(connection);
		LoginUserAlterKey alterKey = new LoginUserAlterKey();
		alterKey.setUserId(userId);
		alterKey.updateLastLoginDate(new Date());
		handler.update(alterKey);
		DBUtils.commit(connection);
		DBUtils.closeConnection(connection);
	}
}
