package sg.xueyu.efamily.action.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.action.BaseAction;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class LoginAuthAction extends BaseAction implements Action {

	public LoginAuthAction() throws Exception {
		super();
	}

	private String userId;
	private String password;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;

		UserDao userDao = null;

		try {
			userDao = new UserDao(getConnection());

			String authResult = userDao.auth(userId, password);

			// Authentication is failed
			if (authResult != null) {
				getSessionManager().removeCredentials(req.getSession());

				resp.setStatus(401);
				resultContent = new ResultContent(null, authResult);
				actionResult = new ActionResult(resultContent, ResultType.Ajax);

				return actionResult;
			}

			// Perform to Authentication is successfully
			LoginUserEJB user = userDao.getUser(userId);
			getSessionManager().setCredentials(req.getSession(), user.getUserId(), user.getUserName());
			userDao.udpateLastLoginDate(userId);

			resultContent = new ResultContent(null, null);
			actionResult = new ActionResult(resultContent, ResultType.Ajax);

			return actionResult;

		} catch (Exception e) {
			SystemLogger.error(e);

			resp.setStatus(500);
			resultContent = new ResultContent(null, "UnHandled Exception Occurred!!!");
			actionResult = new ActionResult(resultContent, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}

		return actionResult;
	}

}
