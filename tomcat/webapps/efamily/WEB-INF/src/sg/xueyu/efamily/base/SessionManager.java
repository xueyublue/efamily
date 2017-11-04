package sg.xueyu.efamily.base;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import sg.xueyu.efamily.system.EFamilyParam;
import sg.xueyu.efamily.system.SystemLogger;

public class SessionManager implements HttpSessionListener {

	private static SessionManager mSessionManager = null;

	private List<HttpSession> mSessionList = null;

	public SessionManager() {
		super();
		mSessionManager = this;
		prepare();
	}

	private void prepare() {
		mSessionList = new ArrayList<>();
	}

	public static SessionManager getInstance() {
		return mSessionManager;
	}

	public List<HttpSession> getSessionList() {
		return mSessionList;
	}

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		SystemLogger.info("Session Created: " + sessionEvent.getSession().getId());
		
		// Add session to session list
		mSessionList.add(sessionEvent.getSession());
		
		// Set session timeout
		sessionEvent.getSession().setMaxInactiveInterval(EFamilyParam.SESSION_TIME_OUT);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		SystemLogger.info("Session Destroyed: " + sessionEvent.getSession().getId());
		
		// Add session to session list
		mSessionList.remove(sessionEvent.getSession());
		
		// Remove Credentials from current session
		removeCredentials(sessionEvent.getSession());
	}

	public String getCredentials(HttpSession session) {
		if (!mSessionList.contains(session)) {
			return null;
		}
		
		Object userId = session.getAttribute("userId");
		
		if (userId == null) {
			return null;
		}
		
		return userId.toString();
	}

	public boolean checkCredentials(HttpSession session) {
		if (!mSessionList.contains(session)) {
			return false;
		}
		
		Object userId = session.getAttribute("userId");
		Object userName = session.getAttribute("userName");
		
		if (userId != null && userName != null) {
			return true;
		}
		
		return false;
	}

	public boolean setCredentials(HttpSession session, String userId, String userName) {
		if (!mSessionList.contains(session)) {
			return false;
		}
		
		session.setAttribute("userId", userId);
		session.setAttribute("userName", userName);
		
		return true;
	}

	public boolean removeCredentials(HttpSession session) {
		if (!mSessionList.contains(session)) {
			return false;
		}
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		return true;
	}
}
