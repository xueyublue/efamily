package sg.xueyu.efamily.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.CommonMethods;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class DeleteUserAction implements Action {
	
	private String userId;
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult result = null;
		
		if (CommonMethods.checkSessionCredentials(req.getSession())) {
			UserDao.deleteUser(userId);
			resultContent = new ResultContent(null, null);
			result = new ActionResult(resultContent, ResultType.Ajax);
		} else {
			resultContent = new ResultContent("login.jsp", null);
			result = new ActionResult(resultContent);
		}
		return result;
	}
}
