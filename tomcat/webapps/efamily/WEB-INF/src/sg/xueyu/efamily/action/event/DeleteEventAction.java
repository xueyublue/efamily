package sg.xueyu.efamily.action.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.action.BaseAction;
import sg.xueyu.efamily.base.ejb.EventEJB;
import sg.xueyu.efamily.dao.EventDao;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.controller.ActionController;

public class DeleteEventAction extends BaseAction implements Action {

	public DeleteEventAction() throws Exception {
		super();
	}

	private String eventId;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		EventDao eventDao = null;

		try {
			ActionResult authResult = credentialAuthentication(req);
			if (authResult != null) {
				return authResult;
			}

			eventDao = new EventDao(getConnection());

			// Event is not exist
			EventEJB event = eventDao.getEvent(eventId);
			if (event == null) {
				resp.setStatus(500);
				return ActionController.buildActionResult(null, "Event is not exist!", ResultType.Ajax);
			}

			// Do not allow to DELETE any Event if administrator flag is false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())) {
				resp.setStatus(500);
				return ActionController.buildActionResult(null, "Insufficient Previlege!", ResultType.Ajax);
			}

			// Perform to DELETE user
			eventDao.deleteEvent(eventId);
			return ActionController.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			resp.setStatus(500);
			return ActionController.buildActionResult(null, "UnHandled Exception Occurred!!!", ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
}
