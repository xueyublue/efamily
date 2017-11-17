package sg.xueyu.efamily.system;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CommonMethod {

	public static boolean isVoid(Object obj) {
		if (obj == null || obj.toString() == null) {
			return true;
		}
		if (obj.toString().trim().length() == 0 || obj.toString().trim().equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}

	public static String getStackTrace(Exception exception) {
		if (exception != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			exception.printStackTrace(pw);
			return sw.toString();
		}
		return "";
	}

}
