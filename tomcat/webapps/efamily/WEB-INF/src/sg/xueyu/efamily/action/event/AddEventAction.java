package sg.xueyu.efamily.action.event;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.action.BaseAction;
import sg.xueyu.efamily.base.SequenceHandler;
import sg.xueyu.efamily.dao.EventDao;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.controller.ActionController;

public class AddEventAction extends BaseAction implements Action {

	public AddEventAction() throws Exception {
		super();
	}

	private String title;

	private String location;

	private Date startDate;

	private Date endDate;

	private String isAllDay;

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

			// Perform to ADD event
			eventDao.createEvent(SequenceHandler.nextEventId(getConnection()), title, location, startDate, endDate,
					isAllDay, category, getSessionUserId());
			
			return ActionController.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			resp.setStatus(500);

			return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
}
