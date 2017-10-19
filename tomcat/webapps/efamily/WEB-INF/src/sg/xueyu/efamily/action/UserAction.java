package sg.xueyu.efamily.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.base.DataSource;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.base.ejb.RoleEJB;
import sg.xueyu.efamily.dao.RoleDao;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.CommonMethods;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class UserAction implements Action {

	private static final String RESULT_URL = "user.jsp";

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;

		Connection conn = null;
		UserDao userDao = null;
		RoleDao roleDao = null;

		try {
			conn = new DataSource().getConnection();
			userDao = new UserDao(conn);
			roleDao = new RoleDao(conn);

			// Session UserId is null
			String sessionUserId = CommonMethods.getSessionCredentials(req.getSession());
			if (sessionUserId == null) {
				resultContent = new ResultContent("login.jsp", null);
				actionResult = new ActionResult(resultContent);
				
				return actionResult;
			}
			// Session User is not exist in DB
			LoginUserEJB sessionUser = userDao.getUser(sessionUserId);
			if (sessionUser == null) {
				resultContent = new ResultContent("login.jsp", null);
				actionResult = new ActionResult(resultContent);
				
				return actionResult;
			}
			// Role of session User is not exist in DB
			RoleEJB sessionRole = roleDao.getRole(sessionUser.getRoleId());
			if (sessionRole == null) {
				resultContent = new ResultContent("login.jsp", null);
				actionResult = new ActionResult(resultContent);
				
				return actionResult;
			}

			// Perform to get all users from DB
			req.setAttribute("users", userDao.getAllUsers());

			resultContent = new ResultContent(RESULT_URL, null);
			actionResult = new ActionResult(resultContent);

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
