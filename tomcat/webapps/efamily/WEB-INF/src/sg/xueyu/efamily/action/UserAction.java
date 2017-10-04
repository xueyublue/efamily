package sg.xueyu.efamily.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.CommonMethods;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;

public class UserAction implements Action {

	private static final String RESULT_URL = "user.jsp";
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult result = null;
		if (CommonMethods.checkCredentials(req)) {
			req.setAttribute("users", UserDao.getAllUsers());
			resultContent = new ResultContent(RESULT_URL, null);
			result = new ActionResult(resultContent);
		} else {
			resultContent = new ResultContent("login.jsp", null);
			result = new ActionResult(resultContent);
		}
		return result;
	}

}