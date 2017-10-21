package sg.xueyu.efamily.base;

import java.sql.Connection;

import sg.xueyu.dbhandler.handler.AbstractSequenceHandler;
import sg.xueyu.efamily.system.CommonMethods;

public class SequenceHandler extends AbstractSequenceHandler {

	public static String nextEventId(Connection conn) throws Exception {
		String value = next(conn, "SELECT EVENT_SEQ.NEXTVAL FROM DUAL");
		return addLeadingZeros(value, 10);
	}

/** Private Methods **/	
	private static String addLeadingZeros(String value, int length) {
		if (CommonMethods.isVoid(value) || length <= 0) {
			return null;
		} else if (value.length() >= length) {
			return value;
		} else {
			int zeros = length - value.length();
			String strZeros = "";
			for (int i = 0; i < zeros; i++) {
				strZeros += "0";
			}
			return strZeros + value;
		}
	}
}
