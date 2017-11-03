package sg.xueyu.efamily.action.auth;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.base.DataSource;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.CommonMethod;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class LoginAuthAction implements Action {

	private String userId;
	private String password;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;

		Connection conn = null;
		UserDao userDao = null;

		try {
			conn = new DataSource().getConnection();
			userDao = new UserDao(conn);

			String authResult = userDao.auth(userId, password);

			// Authentication is failed
			if (authResult != null) {
				CommonMethod.removeSessionCredentials(req.getSession());

				resp.setStatus(401);
				resultContent = new ResultContent(null, authResult);
				actionResult = new ActionResult(resultContent, ResultType.Ajax);

				return actionResult;
			}

			// Perform to Authentication is successfully
			LoginUserEJB user = userDao.getUser(userId);
			CommonMethod.setSessionCredentials(req.getSession(), user.getUserId(), user.getUserName());
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
			DBUtils.closeConnection(conn);
		}

		return actionResult;
	}

}
