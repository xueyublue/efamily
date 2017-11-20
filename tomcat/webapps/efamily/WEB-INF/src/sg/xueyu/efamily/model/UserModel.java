package sg.xueyu.efamily.model;

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
import sg.xueyu.efamily.model.dto.LoginUserDTO;

public class UserModel {

	private Connection mConnection = null;

	public UserModel(Connection conn) {
		this.mConnection = conn;
	}

	public List<LoginUserDTO> getAllUsers() throws Exception {
		LoginUserHandler handler = new LoginUserHandler(mConnection);
		LoginUserSearchKey searchKey = new LoginUserSearchKey();

		searchKey.setUserIdOrder(true);
		
		AbstractEntity[] results = handler.query(searchKey);
		List<LoginUserDTO> actualResults = new ArrayList<>();

		for (int i = 0; i < results.length; i++) {
			actualResults.add((LoginUserDTO) HandlerUtil.entity2bean(results[i], LoginUserDTO.class));
		}

		return actualResults;
	}

	public LoginUserDTO getUser(String userId) throws Exception {
		LoginUserHandler handler = new LoginUserHandler(mConnection);

		LoginUserSearchKey searchKey = new LoginUserSearchKey();
		searchKey.setUserId(userId);

		AbstractEntity[] results = handler.query(searchKey);
		if (results.length == 1) {
			return (LoginUserDTO) HandlerUtil.entity2bean(results[0], LoginUserDTO.class);
		}

		return null;
	}
	
	public LoginUserDTO[] getUsersByRoleId(String roleId) throws Exception {
		LoginUserHandler handler = new LoginUserHandler(mConnection);

		LoginUserSearchKey searchKey = new LoginUserSearchKey();
		searchKey.setRoleId(roleId);

		AbstractEntity[] results = handler.query(searchKey);
		
		if (results.length > 0) {
			LoginUserDTO[] users = new LoginUserDTO[results.length];
			for (int i = 0; i < results.length; i++) {
				users[i] = (LoginUserDTO) HandlerUtil.entity2bean(results[i], LoginUserDTO.class);
			}
			
			return users;
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

		user.setRegistPname(UserModel.class.getSimpleName());

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
		alterKey.updateLastUpdatePname(UserModel.class.getSimpleName());

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

		alterKey.updateLastUpdatePname(UserModel.class.getSimpleName());
		alterKey.updateLastLoginDate(new Date());

		handler.update(alterKey);

		DBUtils.commit(mConnection);
	}

}
