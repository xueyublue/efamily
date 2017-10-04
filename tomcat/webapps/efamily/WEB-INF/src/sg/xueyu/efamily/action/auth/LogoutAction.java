package sg.xueyu.efamily.action.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class LogoutAction implements Action {

	private static final String RESULT_URL = "login.do";
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Destroy Session
		req.getSession().invalidate();
		
		// Redirect request to login.do
		ResultContent resultContent = new ResultContent(RESULT_URL, null);
		ActionResult result = new ActionResult(resultContent, ResultType.Redirect);
		return result;
	}

}
