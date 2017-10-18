package sg.xueyu.efamily.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.efamily.dao.RoleDao;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.CommonMethods;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class AddUserAction implements Action {
	
	private String userId;
	
	private String userName;
	
	private String password;
	
	private String roleId;
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult result = null;
		
		if (CommonMethods.checkSessionCredentials(req.getSession())) {
			if (UserDao.getUser(userId) != null) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "User is exist!");
				result = new ActionResult(resultContent, ResultType.Ajax);
			} else if (RoleDao.getRole(roleId) == null) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "Role Id is not exist!");
				result = new ActionResult(resultContent, ResultType.Ajax);
			} else {
				UserDao.createUser(userId, userName, password, roleId);	
				resultContent = new ResultContent(null, null);
				result = new ActionResult(resultContent, ResultType.Ajax);
			}
		} else {
			resultContent = new ResultContent("login.jsp", null);
			result = new ActionResult(resultContent);
		}
		return result;
	}
}
