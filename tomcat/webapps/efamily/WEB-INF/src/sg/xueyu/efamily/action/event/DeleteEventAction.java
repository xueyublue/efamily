package sg.xueyu.efamily.action.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.action.BaseAction;
import sg.xueyu.efamily.base.ejb.EventEJB;
import sg.xueyu.efamily.dao.EventDao;
import sg.xueyu.efamily.dao.RoleDao;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class DeleteEventAction extends BaseAction implements Action {

	public DeleteEventAction() throws Exception {
		super();
	}

	private String eventId;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;

		UserDao userDao = null;
		RoleDao roleDao = null;
		EventDao eventDao = null;

		try {
			ActionResult authResult = credentialAuthentication(req);
			if (authResult != null) {
				return authResult;
			}

			userDao = new UserDao(getConnection());
			roleDao = new RoleDao(getConnection());
			eventDao = new EventDao(getConnection());

			// Event is not exist
			EventEJB event = eventDao.getEvent(eventId);
			if (event == null) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "Event is not exist!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Do not allow to DELETE any Event if administrator flag is false
			String sessionUserId = getSessionManager().getCredentials(req.getSession());
			String sessionRoleId = userDao.getUser(sessionUserId).getRoleId();
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(roleDao.getRole(sessionRoleId).getAdminFlag())) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "Insufficient Previlege!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Perform to DELETE user
			eventDao.deleteEvent(eventId);

			resultContent = new ResultContent(null, null);

			return new ActionResult(resultContent, ResultType.Ajax);
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
