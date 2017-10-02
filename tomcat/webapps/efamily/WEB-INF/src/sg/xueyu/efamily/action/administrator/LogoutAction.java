package sg.xueyu.efamily.action.administrator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;

public class LogoutAction implements Action {

	private static final String RESULT_URL = "login.jsp";
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultContent resultContent = new ResultContent(RESULT_URL);
		ActionResult result = new ActionResult(resultContent);
		return result;
	}

}
