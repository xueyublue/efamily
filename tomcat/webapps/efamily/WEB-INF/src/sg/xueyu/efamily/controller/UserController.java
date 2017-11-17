package sg.xueyu.efamily.controller;

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
import sg.xueyu.zebra.annotation.Path;
import sg.xueyu.zebra.controller.ActionController;

@Path("/user")
public class UserController extends BaseController {

	private String userId;

	private String userName;

	private String password;

	private String roleId;
	
	public UserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			getHttpServletRequest().setAttribute("users", getUserDao().getAllUsers());

			return ActionController.buildActionResultWithURL(SystemConstants.URL_USER);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);

			return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
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
			LoginUserEJB userEJB = getUserDao().getUser(userId);

			return ActionController.buildActionResult(null, userEJB, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);
			return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
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

			// Role is not exist
			RoleEJB role = getRoleDao().getRole(roleId);
			if (role == null) {
				getHttpServletResponse().setStatus(500);
				return ActionController.buildActionResult(null, "Role Id is not exist!", ResultType.Ajax);
			}

			// Do not allow to ADD user if administrator flag is false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())
					&& SystemConstants.ROLE_ADMIN_FLAG_TRUE.equals(role.getAdminFlag())) {
				getHttpServletResponse().setStatus(500);
				return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_INSUFFICIENT_PREVILEGE, ResultType.Ajax);
			}

			// User is exist
			if (getUserDao().getUser(userId) != null) {
				getHttpServletResponse().setStatus(500);
				return ActionController.buildActionResult(null, "User is exist!", ResultType.Ajax);
			}

			// Perform to DELETE user
			getUserDao().createUser(userId, userName, password, roleId);

			return ActionController.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);
			return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
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
				return ActionController.buildActionResult(null, "Role Id is not exist!", ResultType.Ajax);
			}

			// Do not allow to Update Administrator if administrator flag is
			// false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())
					&& SystemConstants.ROLE_ADMIN_FLAG_TRUE.equals(role.getAdminFlag())) {
				getHttpServletResponse().setStatus(500);
				return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_INSUFFICIENT_PREVILEGE, ResultType.Ajax);
			}

			// User is not exist
			if (getUserDao().getUser(userId) == null) {
				getHttpServletResponse().setStatus(500);
				return ActionController.buildActionResult(null, "User is not exist!", ResultType.Ajax);
			}

			// Perform to UPDATE user
			getUserDao().updateUser(userId, userName, password, roleId);

			return ActionController.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);
			return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
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

			// User is not exist
			LoginUserEJB user = getUserDao().getUser(userId);
			if (user == null) {
				getHttpServletResponse().setStatus(500);
				return ActionController.buildActionResult(null, "User is not exist!", ResultType.Ajax);
			}

			// Role Id is not exist
			RoleEJB role = getRoleDao().getRole(user.getRoleId());
			if (role == null) {
				getHttpServletResponse().setStatus(500);
				return ActionController.buildActionResult(null, "Role Id is not exist!", ResultType.Ajax);
			}

			// Do not allow to DELETE Administrator if administrator flag is
			// false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())
					&& SystemConstants.ROLE_ADMIN_FLAG_TRUE.equals(role.getAdminFlag())) {
				getHttpServletResponse().setStatus(500);
				return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_INSUFFICIENT_PREVILEGE, ResultType.Ajax);
			}

			// Perform to DELETE user
			getUserDao().deleteUser(userId);

			return ActionController.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);
			return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
}
