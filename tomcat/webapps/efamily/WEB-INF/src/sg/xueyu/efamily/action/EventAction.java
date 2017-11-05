package sg.xueyu.efamily.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.dao.EventDao;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.controller.ActionController;

public class EventAction extends BaseAction implements Action {

	public EventAction() throws Exception {
		super();
	}

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		EventDao eventDao = null;

		try {
			ActionResult authResult = credentialAuthentication(req);
			if (authResult != null) {
				return authResult;
			}

			eventDao = new EventDao(getConnection());

			// Perform to forward to Event.jsp
			req.setAttribute("events", eventDao.getAllEvents());
			
			return ActionController.buildActionResultWithURL(SystemConstants.URL_EVENT);
		} catch (Exception e) {
			SystemLogger.error(e);
			resp.setStatus(500);

			return ActionController.buildActionResult(null, "UnHandled Exception Occurred!!!", ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}

}
