package sg.xueyu.efamily.action.user;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.base.DataSource;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.base.ejb.RoleEJB;
import sg.xueyu.efamily.dao.RoleDao;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.ActionResultController;
import sg.xueyu.efamily.system.CommonMethod;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class AddUserAction implements Action {

	private String userId;

	private String userName;

	private String password;

	private String roleId;

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
			String sessionUserId = CommonMethod.getSessionCredentials(req.getSession());
			if (sessionUserId == null) {
				return ActionResultController.sessionError(resp);
			}
			// Session User is not exist in DB
			LoginUserEJB sessionUser = userDao.getUser(sessionUserId);
			if (sessionUser == null) {
				return ActionResultController.sessionError(resp);
			}
			// Role of session User is not exist in DB
			RoleEJB sessionRole = roleDao.getRole(sessionUser.getRoleId());
			if (sessionRole == null) {
				return ActionResultController.sessionError(resp);
			}

			// Role is not exist
			RoleEJB role = roleDao.getRole(roleId);
			if (role == null) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "Role Id is not exist!");
				actionResult = new ActionResult(resultContent, ResultType.Ajax);

				return actionResult;
			}

			// Do not allow to ADD user if administrator flag is false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(sessionRole.getAdminFlag())
					&& SystemConstants.ROLE_ADMIN_FLAG_TRUE.equals(role.getAdminFlag())) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "Insufficient Previlege!");
				actionResult = new ActionResult(resultContent, ResultType.Ajax);

				return actionResult;
			}

			// User is exist
			if (userDao.getUser(userId) != null) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "User is exist!");
				actionResult = new ActionResult(resultContent, ResultType.Ajax);

				return actionResult;
			}

			// Perform to DELETE user
			userDao.createUser(userId, userName, password, roleId);

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
