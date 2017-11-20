package sg.xueyu.efamily.model.dbhandler;

import java.sql.Connection;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.handler.AbstractHandler;
import sg.xueyu.efamily.model.dbhandler.Event;

public class EventHandler extends AbstractHandler {

	public EventHandler(Connection connection) {
		super(connection);
	}

	@Override
	protected AbstractEntity createEntity() {
		return new Event();
	}
}
