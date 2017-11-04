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

	private String mSessionUserId = null;

	private LoginUserEJB mSessionUser = null;

	private String mSessionRoleId = null;

	private RoleEJB mSessionRole = null;

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

	public String getSessionUserId() {
		return mSessionUserId;
	}

	public LoginUserEJB getSessionUser() {
		return mSessionUser;
	}

	public String getSessionRoleId() {
		return mSessionRoleId;
	}

	public RoleEJB getSessionRole() {
		return mSessionRole;
	}

	protected ActionResult credentialAuthentication(HttpServletRequest req) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;
		UserDao userDao = new UserDao(mConnection);
		RoleDao roleDao = new RoleDao(mConnection);

		// Session UserId is null
		mSessionUserId = mSessionManager.getCredentials(req.getSession());
		if (mSessionUserId == null) {
			resultContent = new ResultContent(SystemConstants.URL_LOGIN, null);
			actionResult = new ActionResult(resultContent);

			return actionResult;
		}
		// Session User is not exist in DB
		mSessionUser = userDao.getUser(mSessionUserId);
		if (mSessionUser == null) {
			resultContent = new ResultContent(SystemConstants.URL_LOGIN, null);
			actionResult = new ActionResult(resultContent);

			return actionResult;
		}
		// Role of session User is not exist in DB
		mSessionRole = roleDao.getRole(mSessionUser.getRoleId());
		if (mSessionRole == null) {
			resultContent = new ResultContent(SystemConstants.URL_LOGIN, null);
			actionResult = new ActionResult(resultContent);

			return actionResult;
		}

		return null;
	}

}
