package sg.xueyu.efamily.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.dbhandler.util.HandlerUtil;
import sg.xueyu.efamily.base.dbhandler.LoginUser;
import sg.xueyu.efamily.base.dbhandler.LoginUserAlterKey;
import sg.xueyu.efamily.base.dbhandler.LoginUserHandler;
import sg.xueyu.efamily.base.dbhandler.LoginUserSearchKey;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;

public class UserDao {

	private Connection mConnection = null;

	public UserDao(Connection conn) {
		this.mConnection = conn;
	}

	public List<LoginUserEJB> getAllUsers() throws Exception {
		LoginUserHandler handler = new LoginUserHandler(mConnection);
		LoginUserSearchKey searchKey = new LoginUserSearchKey();

		AbstractEntity[] results = handler.query(searchKey);
		List<LoginUserEJB> actualResults = new ArrayList<>();

		for (int i = 0; i < results.length; i++) {
			actualResults.add((LoginUserEJB) HandlerUtil.entity2bean(results[i], LoginUserEJB.class));
		}

		return actualResults;
	}

	public LoginUserEJB getUser(String userId) throws Exception {
		LoginUserHandler handler = new LoginUserHandler(mConnection);

		LoginUserSearchKey searchKey = new LoginUserSearchKey();
		searchKey.setUserId(userId);

		AbstractEntity[] results = handler.query(searchKey);
		if (results.length == 1) {
			return (LoginUserEJB) HandlerUtil.entity2bean(results[0], LoginUserEJB.class);
		}

		return null;
	}

	public void createUser(String userId, String userName, String password, String roleId) throws Exception {
		LoginUserHandler handler = new LoginUserHandler(mConnection);

		LoginUser user = new LoginUser();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.setRoleId(roleId);

		user.setRegistPname(UserDao.class.getSimpleName());

		handler.create(user);

		DBUtils.commit(mConnection);
	}

	public void updateUser(String userId, String userName, String password, String roleId) throws Exception {
		LoginUserHandler handler = new LoginUserHandler(mConnection);
		LoginUserAlterKey alterKey = new LoginUserAlterKey();

		alterKey.setUserId(userId);
		alterKey.updateUserName(userName);
		alterKey.updatePassword(password);
		alterKey.updateRoleId(roleId);

		alterKey.updateLastUpdateDate(new Date());
		alterKey.updateLastUpdatePname(UserDao.class.getSimpleName());

		handler.update(alterKey);

		DBUtils.commit(mConnection);
	}

	public void deleteUser(String userId) throws Exception {
		LoginUserHandler handler = new LoginUserHandler(mConnection);

		LoginUserAlterKey alterKey = new LoginUserAlterKey();
		alterKey.setUserId(userId);

		handler.delete(alterKey);

		DBUtils.commit(mConnection);
	}

	public String auth(String userId, String password) throws Exception {
		LoginUserHandler handler = new LoginUserHandler(mConnection);
		LoginUserSearchKey searchKey = new LoginUserSearchKey();
		searchKey.setUserId(userId);
		if (handler.query(searchKey).length == 0) {
			return "UserId is invalid";
		} else {
			searchKey.clear();
			searchKey.setUserId(userId);
			searchKey.setPassword(password);
			if (handler.query(searchKey).length == 0) {
				return "Password is wrong";
			}
		}

		return null;
	}

	public void udpateLastLoginDate(String userId) throws Exception {
		LoginUserHandler handler = new LoginUserHandler(mConnection);

		LoginUserAlterKey alterKey = new LoginUserAlterKey();
		alterKey.setUserId(userId);

		alterKey.updateLastUpdatePname(UserDao.class.getSimpleName());
		alterKey.updateLastLoginDate(new Date());

		handler.update(alterKey);

		DBUtils.commit(mConnection);
	}

}
