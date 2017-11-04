package sg.xueyu.efamily.action.event;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.action.BaseAction;
import sg.xueyu.efamily.base.ejb.EventEJB;
import sg.xueyu.efamily.dao.EventDao;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.controller.ActionController;

public class UpdateEventAction extends BaseAction implements Action {

	public UpdateEventAction() throws Exception {
		super();
	}

	private String eventId;

	private String title;

	private String location;

	private String isAllDay;

	private Date startDate;

	private Date endDate;

	private String category;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		EventDao eventDao = null;

		try {
			ActionResult authResult = credentialAuthentication(req);
			if (authResult != null) {
				return authResult;
			}

			eventDao = new EventDao(getConnection());

			// Role Id is not exist
			EventEJB event = eventDao.getEvent(eventId);
			if (event == null) {
				resp.setStatus(500);
				return ActionController.buildActionResult(null, "Event is not exist!", ResultType.Ajax);
			}

			// Perform to UPDATE event
			eventDao.updateEvent(eventId, title, location, startDate, endDate, isAllDay, category,
					getSessionManager().getCredentials(req.getSession()).getUserId());
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
