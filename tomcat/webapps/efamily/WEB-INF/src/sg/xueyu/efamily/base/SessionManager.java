package sg.xueyu.efamily.base;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import sg.xueyu.efamily.system.CommonMethods;
import sg.xueyu.efamily.system.EFamilyParam;
import sg.xueyu.efamily.system.SystemLogger;

public class SessionManager implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		SystemLogger.info("Session Created: " + sessionEvent.getSession().getId() 
				+ "_" + sessionEvent.getSession().getCreationTime());
		sessionEvent.getSession().setMaxInactiveInterval(EFamilyParam.SESSION_TIME_OUT);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		SystemLogger.info("Session Destroyed: " + sessionEvent.getSession().getId() 
				+ "_" + sessionEvent.getSession().getCreationTime());
		CommonMethods.removeSessionCredentials(sessionEvent.getSession());
	}

}
