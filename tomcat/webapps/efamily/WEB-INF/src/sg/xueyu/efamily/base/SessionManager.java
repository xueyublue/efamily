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

	// Default constructor
	public SessionManager() {
		super();
		mSessionManager = this;
		prepare();
	}

	private void prepare() {
		mSessionList = new ArrayList<>();
	}

	// Return instance of SessionManager
	public static SessionManager getInstance() {
		return mSessionManager;
	}

	// Return activated session list
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
	
	// Add credentials to session
	public boolean setCredentials(HttpSession session, Credentials credentials) {
		if (!mSessionList.contains(session)) {
			return false;
		}

		// Loop existing sessions
		// Invalidate previous session if user id is same
		for (HttpSession prvSession : mSessionList) {
			Object userId = prvSession.getAttribute(Credentials.USER_ID);
			if (prvSession != session 
					&& userId != null
					&& credentials.getUserId().equals(userId.toString())) {
				mSessionList.remove(prvSession);
				prvSession.invalidate();
				break;
			}
		}

		session.setAttribute(Credentials.USER_ID, credentials.getUserId());
		session.setAttribute(Credentials.USER_NAME, credentials.getUserName());

		return true;
	}
	
	// Get credentials from session
	public Credentials getCredentials(HttpSession session) {
		if (!mSessionList.contains(session)) {
			return null;
		}

		Object userId = session.getAttribute(Credentials.USER_ID);
		Object userName = session.getAttribute(Credentials.USER_NAME);

		if (userId == null) {
			return null;
		}

		Credentials credentials = new Credentials(userId.toString(), userName.toString());

		return credentials;
	}

	// Check credentials of session
	public boolean checkCredentials(HttpSession session) {
		if (!mSessionList.contains(session)) {
			return false;
		}

		Object userId = session.getAttribute(Credentials.USER_ID);
		Object userName = session.getAttribute(Credentials.USER_NAME);

		if (userId != null && userName != null) {
			return true;
		}

		return false;
	}

	// Remove credentials from session
	public boolean removeCredentials(HttpSession session) {
		if (!mSessionList.contains(session)) {
			return false;
		}

		session.removeAttribute(Credentials.USER_ID);
		session.removeAttribute(Credentials.USER_NAME);

		return true;
	}
}
