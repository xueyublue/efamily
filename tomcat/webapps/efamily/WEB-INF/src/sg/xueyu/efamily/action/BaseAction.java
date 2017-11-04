package sg.xueyu.efamily.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import sg.xueyu.efamily.base.DataSource;
import sg.xueyu.efamily.base.SessionManager;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.base.ejb.RoleEJB;
import sg.xueyu.efamily.dao.RoleDao;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;

public class BaseAction {

	private Connection mConnection = null;
	private SessionManager mSessionManager = null;

	public BaseAction() throws Exception {
		mConnection = new DataSource().getConnection();
		mSessionManager = SessionManager.getInstance();
	}

	protected Connection getConnection() {
		return mConnection;
	}

	protected SessionManager getSessionManager() {
		return mSessionManager;
	}

	protected ActionResult credentialAuthentication(HttpServletRequest req) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;
		UserDao userDao = new UserDao(mConnection);
		RoleDao roleDao = new RoleDao(mConnection);

		// Session UserId is null
		String sessionUserId = mSessionManager.getCredentials(req.getSession());
		if (sessionUserId == null) {
			resultContent = new ResultContent(SystemConstants.URL_LOGIN, null);
			actionResult = new ActionResult(resultContent);

			return actionResult;
		}
		// Session User is not exist in DB
		LoginUserEJB sessionUser = userDao.getUser(sessionUserId);
		if (sessionUser == null) {
			resultContent = new ResultContent(SystemConstants.URL_LOGIN, null);
			actionResult = new ActionResult(resultContent);

			return actionResult;
		}
		// Role of session User is not exist in DB
		RoleEJB sessionRole = roleDao.getRole(sessionUser.getRoleId());
		if (sessionRole == null) {
			resultContent = new ResultContent(SystemConstants.URL_LOGIN, null);
			actionResult = new ActionResult(resultContent);

			return actionResult;
		}

		return null;
	}

}
