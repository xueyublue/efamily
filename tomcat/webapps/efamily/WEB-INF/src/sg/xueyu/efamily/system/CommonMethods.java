package sg.xueyu.efamily.system;

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
}
