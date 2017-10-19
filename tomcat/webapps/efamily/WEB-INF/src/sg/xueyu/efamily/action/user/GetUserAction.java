package sg.xueyu.efamily.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.base.ejb.RoleEJB;
import sg.xueyu.efamily.dao.RoleDao;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.CommonMethods;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class GetUserAction implements Action {

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

		// Perform to GET user
		LoginUserEJB userEJB = UserDao.getUser(userId);
		
		resultContent = new ResultContent(null, userEJB);
		
		return new ActionResult(resultContent, ResultType.Ajax);
	}
}
