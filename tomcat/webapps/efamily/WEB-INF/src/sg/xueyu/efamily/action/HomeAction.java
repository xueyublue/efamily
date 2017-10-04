package sg.xueyu.efamily.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.efamily.system.CommonMethods;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;

public class HomeAction implements Action {

	private static final String RESULT_URL = "home.jsp";
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultContent resultContent = null;
		ActionResult result = null;
		if (CommonMethods.checkCredentials(req)) {
			resultContent = new ResultContent(RESULT_URL, null);
			result = new ActionResult(resultContent);
		} else {
			resultContent = new ResultContent("login.jsp", null);
			result = new ActionResult(resultContent);
		}
		return result;
	}

}