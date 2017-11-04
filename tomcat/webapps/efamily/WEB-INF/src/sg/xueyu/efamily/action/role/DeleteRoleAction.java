package sg.xueyu.efamily.action.role;

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

public class DeleteRoleAction extends BaseAction implements Action {

	public DeleteRoleAction() throws Exception {
		super();
	}

	private String roleId;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;

		try {
			ActionResult authResult = credentialAuthentication(req);
			if (authResult != null) {
				return authResult;
			}

			// Role is not exist
			RoleEJB role = getRoleDao().getRole(roleId);
			if (role == null) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "Role is not exist!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Do not allow to DELETE role if users exists
			LoginUserEJB[] users = getUserDao().getUsersByRoleId(roleId);
			if (users != null) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "There are existing uses!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Do not allow to DELETE any Role if administrator flag is false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "Insufficient Previlege!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Perform to DELETE user
			getRoleDao().deleteRole(roleId);

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
