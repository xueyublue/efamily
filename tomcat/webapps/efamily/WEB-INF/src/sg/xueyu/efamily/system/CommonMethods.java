package sg.xueyu.efamily.system;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonMethods {

	public static final boolean checkCredentials(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object userId = session.getAttribute("userId");
		Object userName = session.getAttribute("userName");
		if (userId != null && userName != null) {
			return true;
		}
		return false;
	}
	
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
