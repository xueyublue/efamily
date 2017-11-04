package sg.xueyu.efamily.action.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.efamily.action.BaseAction;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.controller.ActionController;

public class LogoutAction extends BaseAction implements Action {

	public LogoutAction() throws Exception {
		super();
	}

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Destroy Session
		req.getSession().invalidate();

		return ActionController.buildActionResult("login.do", null, ResultType.Redirect);
	}

}
