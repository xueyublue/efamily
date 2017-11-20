package sg.xueyu.efamily.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.efamily.base.Credentials;
import sg.xueyu.efamily.base.DataSource;
import sg.xueyu.efamily.base.SessionManager;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.base.ejb.RoleEJB;
import sg.xueyu.efamily.model.RoleModel;
import sg.xueyu.efamily.model.UserModel;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;

public class BaseController {

	private HttpServletRequest mHttpServletRequest = null;

	private HttpServletResponse mHttpServletResponse = null;

	private Connection mConnection = null;

	private UserModel mUserModel = null;

	private RoleModel mRoleModel = null;

	private SessionManager mSessionManager = null;

	private String mSessionUserId = null;

	private LoginUserEJB mSessionUser = null;

	private String mSessionRoleId = null;

	private RoleEJB mSessionRole = null;

	// Default constructor
	public BaseController() throws Exception {
		this.mConnection = new DataSource().getConnection();
		this.mUserModel = new UserModel(mConnection);
		this.mRoleModel = new RoleModel(mConnection);
		this.mSessionManager = SessionManager.getInstance();
	}
	
	public BaseController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.mHttpServletRequest = request;
		this.mHttpServletResponse = response;
		
		this.mConnection = new DataSource().getConnection();
		this.mUserModel = new UserModel(mConnection);
		this.mRoleModel = new RoleModel(mConnection);
		this.mSessionManager = SessionManager.getInstance();
	}

	protected HttpServletRequest getHttpServletRequest() {
		return mHttpServletRequest;
	}

	protected HttpServletResponse getHttpServletResponse() {
		return mHttpServletResponse;
	}

	protected Connection getConnection() {
		return mConnection;
	}

	protected UserModel getUserModel() {
		return mUserModel;
	}

	protected RoleModel getRoleModel() {
		return mRoleModel;
	}

	protected SessionManager getSessionManager() {
		return mSessionManager;
	}

	protected String getSessionUserId() {
		return mSessionUserId;
	}

	protected LoginUserEJB getSessionUser() {
		return mSessionUser;
	}

	protected String getSessionRoleId() {
		return mSessionRoleId;
	}

	protected RoleEJB getSessionRole() {
		return mSessionRole;
	}

	// Session credentials authentication
	protected ActionResult credentialAuthentication(HttpServletRequest req) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;
		UserModel userModel = new UserModel(mConnection);
		RoleModel roleModel = new RoleModel(mConnection);

		// Session UserId is null
		Credentials credentials = mSessionManager.getCredentials(req.getSession());
		if (credentials == null) {
			resultContent = new ResultContent(SystemConstants.URL_LOGIN, null);
			actionResult = new ActionResult(resultContent);

			return actionResult;
		}
		mSessionUserId = credentials.getUserId();
		// Session User is not exist in DB
		mSessionUser = userModel.getUser(mSessionUserId);
		if (mSessionUser == null) {
			resultContent = new ResultContent(SystemConstants.URL_LOGIN, null);
			actionResult = new ActionResult(resultContent);

			return actionResult;
		}
		// Role of session User is not exist in DB
		mSessionRole = roleModel.getRole(mSessionUser.getRoleId());
		if (mSessionRole == null) {
			resultContent = new ResultContent(SystemConstants.URL_LOGIN, null);
			actionResult = new ActionResult(resultContent);

			return actionResult;
		}

		return null;
	}

}
