package sg.xueyu.efamily.base.dbhandler;

import java.sql.Connection;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.handler.AbstractHandler;

import sg.xueyu.efamily.base.dbhandler.LoginUser;

public class LoginUserHandler extends AbstractHandler {

	public LoginUserHandler(Connection connection) {
		super(connection);
	}

	@Override
	protected AbstractEntity createEntity() {
		return new LoginUser();
	}
}
