package sg.xueyu.efamily.base.dbhandler;

import java.sql.Connection;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.handler.AbstractHandler;

import sg.xueyu.efamily.base.dbhandler.Event;

public class EventHandler extends AbstractHandler {

	public EventHandler(Connection connection) {
		super(connection);
	}

	@Override
	protected AbstractEntity createEntity() {
		return new Event();
	}
}
