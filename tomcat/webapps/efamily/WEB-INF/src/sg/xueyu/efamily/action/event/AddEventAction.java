package sg.xueyu.efamily.action.event;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.action.BaseAction;
import sg.xueyu.efamily.base.SequenceHandler;
import sg.xueyu.efamily.dao.EventDao;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

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
		ResultContent resultContent = null;
		ActionResult actionResult = null;

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

			resultContent = new ResultContent(null, null);
			actionResult = new ActionResult(resultContent, ResultType.Ajax);

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
