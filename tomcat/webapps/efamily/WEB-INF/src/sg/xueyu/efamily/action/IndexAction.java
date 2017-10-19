package sg.xueyu.efamily.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;

public class IndexAction implements Action {

	private static final String RESULT_URL = "login.jsp";
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = new ResultContent(RESULT_URL, null);
		ActionResult actionResult = new ActionResult(resultContent);
		return actionResult;
	}

}
