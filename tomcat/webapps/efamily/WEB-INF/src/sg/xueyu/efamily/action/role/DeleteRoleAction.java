package sg.xueyu.efamily.action.role;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.base.DataSource;
import sg.xueyu.efamily.base.SessionManager;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.base.ejb.RoleEJB;
import sg.xueyu.efamily.dao.RoleDao;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.ActionResultController;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class DeleteRoleAction implements Action {

	private String roleId;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;

		SessionManager sessionManager = SessionManager.getInstance();
		
		Connection conn = null;
		UserDao userDao = null;
		RoleDao roleDao = null;

		try {
			conn = new DataSource().getConnection();
			userDao = new UserDao(conn);
			roleDao = new RoleDao(conn);

			// Session UserId is null
			String sessionUserId = sessionManager.getCredentials(req.getSession());
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
				resultContent = new ResultContent(null, "Role is not exist!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Do not allow to DELETE role if users exists
			LoginUserEJB[] users = userDao.getUsersByRoleId(roleId);
			if (users != null) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "There are existing uses!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Do not allow to DELETE any Role if administrator flag is false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(sessionRole.getAdminFlag())) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "Insufficient Previlege!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Perform to DELETE user
			roleDao.deleteRole(roleId);

			resultContent = new ResultContent(null, null);

			return new ActionResult(resultContent, ResultType.Ajax);
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
