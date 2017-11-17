package sg.xueyu.efamily.controller;

import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.annotation.Method;
import sg.xueyu.zebra.annotation.Method.RequestMethod;
import sg.xueyu.zebra.annotation.Path;
import sg.xueyu.zebra.controller.ActionController;

@Path("/index")
public class IndexController {

	@Method(RequestMethod.GET)
	public ActionResult get() {
		
		return ActionController.buildActionResultWithURL(SystemConstants.URL_LOGIN);
	}
	
}
