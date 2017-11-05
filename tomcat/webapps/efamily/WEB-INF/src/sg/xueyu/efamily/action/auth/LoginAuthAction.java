package sg.xueyu.efamily.action.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.action.BaseAction;
import sg.xueyu.efamily.base.Credentials;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.controller.ActionController;

public class LoginAuthAction extends BaseAction implements Action {

	public LoginAuthAction() throws Exception {
		super();
	}

	private String userId;
	private String password;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			String authResult = getUserDao().auth(userId, password);

			// Authentication is failed
			if (authResult != null) {
				getSessionManager().removeCredentials(req.getSession());
				resp.setStatus(401);

				return ActionController.buildActionResult(null, authResult, ResultType.Ajax);
			}

			// Perform to Authentication is successfully
			LoginUserEJB user = getUserDao().getUser(userId);
			getSessionManager().setCredentials(req.getSession(), new Credentials(user.getUserId(), user.getUserName()));
			getUserDao().udpateLastLoginDate(userId);

			return ActionController.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			resp.setStatus(500);

			return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}

}
