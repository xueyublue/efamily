package sg.xueyu.efamily.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.base.Credentials;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.annotation.Method;
import sg.xueyu.zebra.annotation.Method.RequestMethod;
import sg.xueyu.zebra.annotation.Param;
import sg.xueyu.zebra.core.ActionResultBuilder;
import sg.xueyu.zebra.annotation.Path;

@Path("/login")
public class LoginController extends BaseController {
	
	public LoginController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super(request, response);
	}
	
	@Method(RequestMethod.POST)
	public ActionResult post(@Param("userId") String userId, @Param("password") String password) throws Exception {
		try {
			String authResult = getUserDao().auth(userId, password);

			// Authentication is failed
			if (authResult != null) {
				getSessionManager().removeCredentials(getHttpServletRequest().getSession());
				getHttpServletResponse().setStatus(401);

				return ActionResultBuilder.buildActionResult(null, authResult, ResultType.Ajax);
			}

			// Perform to Authentication is successfully
			LoginUserEJB user = getUserDao().getUser(userId);
			getSessionManager().setCredentials(getHttpServletRequest().getSession(), new Credentials(user.getUserId(), user.getUserName()));
			getUserDao().udpateLastLoginDate(userId);

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
