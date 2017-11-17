package sg.xueyu.efamily.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.annotation.Method;
import sg.xueyu.zebra.annotation.Method.RequestMethod;
import sg.xueyu.zebra.annotation.Path;

@Path("/path")
public class TemplateController extends BaseController {
	
	public TemplateController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super(request, response);
	}
	
	@Method(RequestMethod.GET)
	public ActionResult getPage() throws Exception {
		return null;
	}
	
	@Path("/get")
	@Method(RequestMethod.GET)
	public ActionResult get() throws Exception {
		return null;
	}
	
	@Path("/add")
	@Method(RequestMethod.POST)
	public ActionResult add() throws Exception {
		return null;
	}
	
	@Path("/update")
	@Method(RequestMethod.POST)
	public ActionResult update() throws Exception {
		return null;
	}
	
	@Path("/delete")
	@Method(RequestMethod.POST)
	public ActionResult delete() throws Exception {
		return null;
	}
}
