package sg.xueyu.efamily.system;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpSession;

public class CommonMethods {

	public static final String getSessionCredentials(HttpSession session) {
		Object userId = session.getAttribute("userId");
		if (userId == null) {
			return null;
		}
		return userId.toString();
	}
	
	public static final boolean checkSessionCredentials(HttpSession session) {
		Object userId = session.getAttribute("userId");
		Object userName = session.getAttribute("userName");
		if (userId != null && userName != null) {
			return true;
		}
		return false;
	}
	
	public static final void setSessionCredentials(HttpSession session, String userId, String userName) {
		session.setAttribute("userId", userId);
		session.setAttribute("userName", userName);
	}
	
	public static final void removeSessionCredentials(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userName");
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
