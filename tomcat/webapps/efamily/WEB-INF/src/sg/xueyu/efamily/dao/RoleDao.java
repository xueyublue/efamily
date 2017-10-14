package sg.xueyu.efamily.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.dbhandler.util.HandlerUtil;
import sg.xueyu.efamily.base.DataSource;
import sg.xueyu.efamily.base.dbhandler.RoleHandler;
import sg.xueyu.efamily.base.dbhandler.RoleSearchKey;
import sg.xueyu.efamily.base.ejb.RoleEJB;

public class RoleDao extends DataSource {

	public RoleDao() throws Exception {
		super();
	}

	private static RoleDao getInstance() throws Exception {
		return new RoleDao();
	}
	
	public static List<RoleEJB> getAllRoles() throws Exception {
		Connection connection = RoleDao.getInstance().getConnection();
		RoleHandler handler = new RoleHandler(connection);
		RoleSearchKey searchKey = new RoleSearchKey();

		AbstractEntity[] results = handler.query(searchKey);
		List<RoleEJB> actualResults = new ArrayList<>();

		for (int i = 0; i < results.length; i++) {
			actualResults.add((RoleEJB) HandlerUtil.entity2bean(results[i], RoleEJB.class));
		}
		DBUtils.closeConnection(connection);
		
		return actualResults;
	}
	
	public static RoleEJB getRole(String roleId) throws Exception {
		Connection connection = RoleDao.getInstance().getConnection();
		RoleHandler handler = new RoleHandler(connection);
		
		RoleSearchKey searchKey = new RoleSearchKey();
		searchKey.setRoleId(roleId);

		AbstractEntity[] results = handler.query(searchKey);
		if (results.length == 1) {
			DBUtils.closeConnection(connection);
			return (RoleEJB) HandlerUtil.entity2bean(results[0], RoleEJB.class);
		}

		DBUtils.closeConnection(connection);
		
		return null;
	}
}
