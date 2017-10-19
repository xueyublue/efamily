package sg.xueyu.efamily.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.base.ejb.RoleEJB;
import sg.xueyu.efamily.dao.RoleDao;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.CommonMethods;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class DeleteUserAction implements Action {

	private String userId;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;

		// Session UserId is null
		String sessionUserId = CommonMethods.getSessionCredentials(req.getSession());
		if (sessionUserId == null) {
			resultContent = new ResultContent("login.jsp", null);
			return new ActionResult(resultContent);
		}
		// Session User is not exist in DB
		LoginUserEJB sessionUser = UserDao.getUser(sessionUserId);
		if (sessionUser == null) {
			resultContent = new ResultContent("login.jsp", null);
			return new ActionResult(resultContent);
		}
		// Role of session User is not exist in DB
		RoleEJB sessionRole = RoleDao.getRole(sessionUser.getRoleId());
		if (sessionRole == null) {
			resultContent = new ResultContent("login.jsp", null);
			return new ActionResult(resultContent);
		}

		// User is not exist
		LoginUserEJB user = UserDao.getUser(userId);
		if (user == null) {
			resp.setStatus(500);
			resultContent = new ResultContent(null, "User is not exist!");
			return new ActionResult(resultContent, ResultType.Ajax);
		}
				
		// Role Id is not exist
		RoleEJB role = RoleDao.getRole(user.getRoleId());
		if (role == null) {
			resp.setStatus(500);
			resultContent = new ResultContent(null, "Role Id is not exist!");
			return new ActionResult(resultContent, ResultType.Ajax);
		}
		
		// Do not allow to DELETE Administrator if administrator flag is false
		if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(sessionRole.getAdminFlag())
				&& SystemConstants.ROLE_ADMIN_FLAG_TRUE.equals(role.getAdminFlag())) {
			resp.setStatus(500);
			resultContent = new ResultContent(null, "Insufficient Previlege!");
			return new ActionResult(resultContent, ResultType.Ajax);
		}

		// Perform to DELETE user
		UserDao.deleteUser(userId);

		resultContent = new ResultContent(null, null);

		return new ActionResult(resultContent, ResultType.Ajax);
	}
}
