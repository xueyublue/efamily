package sg.xueyu.efamily.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.dao.EventDao;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class EventAction extends BaseAction implements Action {

	public EventAction() throws Exception {
		super();
	}

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;

		EventDao eventDao = null;

		try {
			ActionResult authResult = credentialAuthentication(req);
			if (authResult != null) {
				return authResult;
			}

			eventDao = new EventDao(getConnection());

			// Perform to forward to Event.jsp
			req.setAttribute("events", eventDao.getAllEvents());

			resultContent = new ResultContent(SystemConstants.URL_EVENT, null);
			actionResult = new ActionResult(resultContent);

			return actionResult;
		} catch (Exception e) {
			SystemLogger.error(e);

			resp.setStatus(500);
			resultContent = new ResultContent(null, "UnHandled Exception Occurred!!!");
			actionResult = new ActionResult(resultContent, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}

		return actionResult;
	}

}
