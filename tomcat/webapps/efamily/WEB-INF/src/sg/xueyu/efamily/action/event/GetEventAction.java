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
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.controller.ActionController;

public class GetEventAction extends BaseAction implements Action {

	public GetEventAction() throws Exception {
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

			// Perform to GET event
			EventEJB event = eventDao.getEvent(eventId);

			ResultContent resultContent = null;
			if (SystemConstants.EVENT_ISALLDAY_TRUE.equals(event.getIsAllDay())) {
				resultContent = ActionController.buildResultContent(null, event, "yyyy-MM-dd");
			} else {
				resultContent = ActionController.buildResultContent(null, event, "yyyy-MM-dd HH:mm");
			}

			return ActionController.buildActionResult(resultContent, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			resp.setStatus(500);
			return ActionController.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
}
