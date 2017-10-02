package sg.xueyu.efamily.base.dbhandler;

import java.sql.Connection;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.handler.AbstractHandler;

import sg.xueyu.efamily.base.dbhandler.Role;

public class RoleHandler extends AbstractHandler {

	public RoleHandler(Connection connection) {
		super(connection);
	}

	@Override
	protected AbstractEntity createEntity() {
		return new Role();
	}
}
