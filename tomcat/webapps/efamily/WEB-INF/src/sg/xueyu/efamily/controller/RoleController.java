package sg.xueyu.efamily.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.base.ejb.RoleEJB;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.annotation.Method;
import sg.xueyu.zebra.annotation.Method.RequestMethod;
import sg.xueyu.zebra.core.ActionResultBuilder;
import sg.xueyu.zebra.annotation.Path;

@Path("/role")
public class RoleController extends BaseController {

	private String roleId;

	private String roleName;

	private String adminFlag;

	private String guestFlag;

	private Date expiryDate;
	
	public RoleController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super(request, response);
	}
	
	@Method(RequestMethod.GET)
	public ActionResult getPage() throws Exception {
		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}

			// Perform to get all users from DB
			getHttpServletRequest().setAttribute("roles", getRoleDao().getAllRoles());

			return ActionResultBuilder.buildActionResultWithURL(SystemConstants.URL_ROLE);

		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);

			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
	@Path("/get")
	@Method(RequestMethod.GET)
	public ActionResult get() throws Exception {
		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}

			// Perform to GET user
			RoleEJB role = getRoleDao().getRole(roleId);

			return ActionResultBuilder.buildActionResult(null, role, "yyyy-MM-dd", ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);
			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
	@Path("/add")
	@Method(RequestMethod.POST)
	public ActionResult add() throws Exception {
		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}

			// Role is exist
			RoleEJB role = getRoleDao().getRole(roleId);
			if (role != null) {
				getHttpServletResponse().setStatus(500);
				return ActionResultBuilder.buildActionResult(null, "Role is exist!", ResultType.Ajax);
			}

			// Do not allow to ADD role if administrator flag is false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())) {
				getHttpServletResponse().setStatus(500);
				return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_INSUFFICIENT_PREVILEGE, ResultType.Ajax);
			}

			// Perform to ADD user
			getRoleDao().createRole(roleId, roleName, adminFlag, guestFlag, expiryDate);

			return ActionResultBuilder.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);

			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
	@Path("/update")
	@Method(RequestMethod.POST)
	public ActionResult update() throws Exception {
		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}

			// Role Id is not exist
			RoleEJB role = getRoleDao().getRole(roleId);
			if (role == null) {
				getHttpServletResponse().setStatus(500);
				return ActionResultBuilder.buildActionResult(null, "Role Id is not exist!", ResultType.Ajax);
			}

			// Do not allow to Update any Role if administrator flag is false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())) {
				getHttpServletResponse().setStatus(500);
				return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_INSUFFICIENT_PREVILEGE, ResultType.Ajax);
			}

			// Perform to UPDATE role
			getRoleDao().updateRole(roleId, roleName, adminFlag, guestFlag, expiryDate);

			return ActionResultBuilder.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);
			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
	@Path("/delete")
	@Method(RequestMethod.POST)
	public ActionResult delete() throws Exception {
		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}

			// Role is not exist
			RoleEJB role = getRoleDao().getRole(roleId);
			if (role == null) {
				getHttpServletResponse().setStatus(500);
				return ActionResultBuilder.buildActionResult(null, "Role is not exist!", ResultType.Ajax);
			}

			// Do not allow to DELETE role if users exists
			LoginUserEJB[] users = getUserDao().getUsersByRoleId(roleId);
			if (users != null) {
				getHttpServletResponse().setStatus(500);
				return ActionResultBuilder.buildActionResult(null, "There are existing uses!", ResultType.Ajax);
			}

			// Do not allow to DELETE any Role if administrator flag is false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())) {
				getHttpServletResponse().setStatus(500);
				return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_INSUFFICIENT_PREVILEGE, ResultType.Ajax);
			}

			// Perform to DELETE user
			getRoleDao().deleteRole(roleId);
			
			return ActionResultBuilder.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);
			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
}