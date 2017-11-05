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
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.controller.ActionController;

public class DeleteRoleAction extends BaseAction implements Action {

	public DeleteRoleAction() throws Exception {
		super();
	}

	private String roleId;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			ActionResult authResult = credentialAuthentication(req);
			if (authResult != null) {
				return authResult;
			}

			// Role is not exist
			RoleEJB role = getRoleDao().getRole(roleId);
			if (role == null) {
				resp.setStatus(500);
				return ActionController.buildActionResult(null, "Role is not exist!", ResultType.Ajax);
			}

			// Do not allow to DELETE role if users exists
			LoginUserEJB[] users = getUserDao().getUsersByRoleId(roleId);
			if (users != null) {
				resp.setStatus(500);
				return ActionController.buildActionResult(null, "There are existing uses!", ResultType.Ajax);
			}

			// Do not allow to DELETE any Role if administrator flag is false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())) {
				resp.setStatus(500);
				return ActionController.buildActionResult(null, "Insufficient Previlege!", ResultType.Ajax);
			}

			// Perform to DELETE user
			getRoleDao().deleteRole(roleId);
			
			return ActionController.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			resp.setStatus(500);
			return ActionController.buildActionResult(null, "UnHandled Exception Occurred!!!", ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
}
