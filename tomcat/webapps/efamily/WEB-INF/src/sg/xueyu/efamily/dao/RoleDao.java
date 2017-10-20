package sg.xueyu.efamily.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.dbhandler.util.HandlerUtil;
import sg.xueyu.efamily.base.dbhandler.Role;
import sg.xueyu.efamily.base.dbhandler.RoleAlterKey;
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

	public void createRole(String roleId, String roleName, String adminFlag, String guestFlag, Date expiryDate) throws Exception {
		RoleHandler handler = new RoleHandler(mConnection);

		Role role = new Role();
		role.setRoleId(roleId);
		role.setRoleName(roleName);
		role.setAdminFlag(adminFlag);
		role.setGuestFlag(guestFlag);
		role.setExpiryDate(expiryDate);
		role.setRegistPname(this.getClass().getSimpleName());

		handler.create(role);

		DBUtils.commit(mConnection);
	}

	public void updateRole(String roleId, String roleName, String adminFlag, String guestFlag, Date expiryDate) throws Exception {
		RoleHandler handler = new RoleHandler(mConnection);

		RoleAlterKey alterKey = new RoleAlterKey();
		alterKey.setRoleId(roleId);

		alterKey.updateRoleName(roleName);
		alterKey.updateAdminFlag(adminFlag);
		alterKey.updateGuestFlag(guestFlag);
		alterKey.updateExpiryDate(expiryDate);

		alterKey.updateLastUpdateDate(new Date());
		alterKey.updateLastUpdatePname(this.getClass().getSimpleName());

		handler.update(alterKey);

		DBUtils.commit(mConnection);
	}
	
	public void deleteRole(String roleId) throws Exception {
		RoleHandler handler = new RoleHandler(mConnection);
		
		RoleAlterKey alterKey = new RoleAlterKey();
		
		alterKey.setRoleId(roleId);
		
		handler.delete(alterKey);
		
		DBUtils.commit(mConnection);
	}
	
	
}