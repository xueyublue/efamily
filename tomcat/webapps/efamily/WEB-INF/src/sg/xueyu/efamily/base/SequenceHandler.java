package sg.xueyu.efamily.base;

import java.sql.Connection;

import sg.xueyu.dbhandler.handler.AbstractSequenceHandler;

public class SequenceHandler extends AbstractSequenceHandler {

	public static String nextEventId(Connection conn) throws Exception {
		return next(conn, "SELECT EVENT_SEQ.NEXTVAL FROM DUAL");
	}
}
