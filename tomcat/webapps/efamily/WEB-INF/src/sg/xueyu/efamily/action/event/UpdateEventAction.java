package sg.xueyu.efamily.action.event;

import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.base.DataSource;
import sg.xueyu.efamily.base.SessionManager;
import sg.xueyu.efamily.base.ejb.EventEJB;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.base.ejb.RoleEJB;
import sg.xueyu.efamily.dao.EventDao;
import sg.xueyu.efamily.dao.RoleDao;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.efamily.system.ActionResultController;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class UpdateEventAction implements Action {

	private String eventId;
	
	private String title;
	
	private String location;
	
	private String isAllDay;
	
	private Date startDate;
	
	private Date endDate;
	
	private String category;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;

		SessionManager sessionManager = SessionManager.getInstance();
		
		Connection conn = null;
		UserDao userDao = null;
		RoleDao roleDao = null;
		EventDao eventDao = null;

		try {
			conn = new DataSource().getConnection();
			userDao = new UserDao(conn);
			roleDao = new RoleDao(conn);
			eventDao = new EventDao(conn);

			// Session UserId is null
			String sessionUserId = sessionManager.getCredentials(req.getSession());
			if (sessionUserId == null) {
				return ActionResultController.sessionError(resp);
			}
			// Session User is not exist in DB
			LoginUserEJB sessionUser = userDao.getUser(sessionUserId);
			if (sessionUser == null) {
				return ActionResultController.sessionError(resp);
			}
			// Role of session User is not exist in DB
			RoleEJB sessionRole = roleDao.getRole(sessionUser.getRoleId());
			if (sessionRole == null) {
				return ActionResultController.sessionError(resp);
			}

			// Role Id is not exist
			EventEJB event = eventDao.getEvent(eventId);
			if (event == null) {
				resp.setStatus(500);
				resultContent = new ResultContent(null, "Event is not exist!");
				return new ActionResult(resultContent, ResultType.Ajax);
			}

			// Perform to UPDATE event
			eventDao.updateEvent(eventId, title, location, startDate, endDate, isAllDay, category, sessionUserId);
			
			resultContent = new ResultContent(null, null);

			return new ActionResult(resultContent, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);

			resp.setStatus(500);
			resultContent = new ResultContent(null, "UnHandled Exception Occurred!!!");
			actionResult = new ActionResult(resultContent, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(conn);
		}

		return actionResult;
	}
}
