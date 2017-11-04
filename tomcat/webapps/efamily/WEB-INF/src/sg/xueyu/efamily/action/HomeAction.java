package sg.xueyu.efamily.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.controller.ActionController;

public class HomeAction extends BaseAction implements Action {

	public HomeAction() throws Exception {
		super();
	}

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		try {
			ActionResult authResult = credentialAuthentication(req);
			if (authResult != null) {
				return authResult;
			}

			// Perform to forward to Home.jsp
			return ActionController.buildActionResultWithURL(SystemConstants.URL_HOME);
		} catch (Exception e) {
			SystemLogger.error(e);
			resp.setStatus(500);

			return ActionController.buildActionResult(null, "UnHandled Exception Occurred!!!", ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}

}
