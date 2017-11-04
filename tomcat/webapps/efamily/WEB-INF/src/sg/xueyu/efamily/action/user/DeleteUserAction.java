package sg.xueyu.efamily.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.action.BaseAction;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.base.ejb.RoleEJB;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class DeleteUserAction extends BaseAction implements Action {

	public DeleteUserAction() throws Exception {
		super();
	}

	private String userId;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;

		try {
			ActionResult authResult = credentialAuthentication(req);
			if (authResult != null) {
				return authResult;
			}

			// User is not exist
			LoginUserEJB user = getUserDao().getUser(userId);
			if (user == null) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "User is not exist!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Role Id is not exist
			RoleEJB role = getRoleDao().getRole(user.getRoleId());
			if (role == null) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "Role Id is not exist!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Do not allow to DELETE Administrator if administrator flag is
			// false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())
					&& SystemConstants.ROLE_ADMIN_FLAG_TRUE.equals(role.getAdminFlag())) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "Insufficient Previlege!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Perform to DELETE user
			getUserDao().deleteUser(userId);

			resultContent = new ResultContent(null, null);

			return new ActionResult(resultContent, ResultType.Ajax);
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
