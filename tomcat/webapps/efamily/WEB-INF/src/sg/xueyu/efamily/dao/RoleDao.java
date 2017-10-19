package sg.xueyu.efamily.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.util.HandlerUtil;
import sg.xueyu.efamily.base.dbhandler.RoleHandler;
import sg.xueyu.efamily.base.dbhandler.RoleSearchKey;
import sg.xueyu.efamily.base.ejb.RoleEJB;

public class RoleDao {

	private Connection mConnection = null;

	public RoleDao(Connection conn) {
		this.mConnection = conn;
	}

	public List<RoleEJB> getAllRoles() throws Exception {
		RoleHandler handler = new RoleHandler(mConnection);
		RoleSearchKey searchKey = new RoleSearchKey();

		AbstractEntity[] results = handler.query(searchKey);
		List<RoleEJB> actualResults = new ArrayList<>();

		for (int i = 0; i < results.length; i++) {
			actualResults.add((RoleEJB) HandlerUtil.entity2bean(results[i], RoleEJB.class));
		}

		return actualResults;
	}

	public RoleEJB getRole(String roleId) throws Exception {
		RoleHandler handler = new RoleHandler(mConnection);

		RoleSearchKey searchKey = new RoleSearchKey();
		searchKey.setRoleId(roleId);

		AbstractEntity[] results = handler.query(searchKey);
		if (results.length == 1) {
			return (RoleEJB) HandlerUtil.entity2bean(results[0], RoleEJB.class);
		}

		return null;
	}
}
