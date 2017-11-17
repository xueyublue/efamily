package sg.xueyu.efamily.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.annotation.Method;
import sg.xueyu.zebra.annotation.Method.RequestMethod;
import sg.xueyu.zebra.annotation.Path;
import sg.xueyu.zebra.controller.ActionController;

@Path("/logout")
public class LogoutController extends BaseController {
	
	public LogoutController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super(request, response);
	}
	
	@Method(RequestMethod.GET)
	public ActionResult post() throws Exception {
		// Destroy Session
		getHttpServletRequest().getSession().invalidate();

		return ActionController.buildActionResult("index.do", null, ResultType.Redirect);
	}
	
}
