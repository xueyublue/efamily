package sg.xueyu.efamily.action.role;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.action.BaseAction;
import sg.xueyu.efamily.base.ejb.RoleEJB;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.controller.ActionController;

public class UpdateRoleAction extends BaseAction implements Action {

	public UpdateRoleAction() throws Exception {
		super();
	}

	private String roleId;

	private String roleName;

	private String adminFlag;

	private String guestFlag;

	private Date expiryDate;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			ActionResult authResult = credentialAuthentication(req);
			if (authResult != null) {
				return authResult;
			}

			// Role Id is not exist
			RoleEJB role = getRoleDao().getRole(roleId);
			if (role == null) {
				resp.setStatus(500);
				return ActionController.buildActionResult(null, "Role Id is not exist!", ResultType.Ajax);
			}

			// Do not allow to Update any Role if administrator flag is false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())) {
				resp.setStatus(500);
				return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_INSUFFICIENT_PREVILEGE, ResultType.Ajax);
			}

			// Perform to UPDATE role
			getRoleDao().updateRole(roleId, roleName, adminFlag, guestFlag, expiryDate);

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
