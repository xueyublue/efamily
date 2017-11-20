package sg.xueyu.efamily.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.base.SequenceHandler;
import sg.xueyu.efamily.base.ejb.EventEJB;
import sg.xueyu.efamily.model.EventModel;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.annotation.Method;
import sg.xueyu.zebra.annotation.Method.RequestMethod;
import sg.xueyu.zebra.annotation.Param;
import sg.xueyu.zebra.core.ActionResultBuilder;
import sg.xueyu.zebra.annotation.Path;

@Path("/event")
public class EventController extends BaseController {
	
	public EventController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super(request, response);
	}
	
	@Method(RequestMethod.GET)
	public ActionResult getPage() throws Exception {
		EventModel eventModel = null;

		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}

			eventModel = new EventModel(getConnection());

			// Perform to forward to Event.jsp
			getHttpServletRequest().setAttribute("events", eventModel.getAllEvents());
			
			return ActionResultBuilder.buildActionResultWithURL(SystemConstants.URL_EVENT);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);

			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
	@Path("/get")
	@Method(RequestMethod.GET)
	public ActionResult get(@Param("eventId") String eventId) throws Exception {
		EventModel eventModel = null;

		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}

			eventModel = new EventModel(getConnection());

			// Perform to GET event
			EventEJB event = eventModel.getEvent(eventId);

			ResultContent resultContent = null;
			if (SystemConstants.EVENT_ISALLDAY_TRUE.equals(event.getIsAllDay())) {
				resultContent = ActionResultBuilder.buildResultContent(null, event, "yyyy-MM-dd");
			} else {
				resultContent = ActionResultBuilder.buildResultContent(null, event, "yyyy-MM-dd HH:mm");
			}

			return ActionResultBuilder.buildActionResult(resultContent, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);
			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
	@Path("/update")
	@Method(RequestMethod.POST)
	public ActionResult update(@Param("eventId") String eventId, @Param("title") String title, @Param("location") String location, 
			@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("isAllDay") String isAllDay, @Param("category") String category) throws Exception {
		EventModel eventModel = null;

		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}

			eventModel = new EventModel(getConnection());

			// Role Id is not exist
			EventEJB event = eventModel.getEvent(eventId);
			if (event == null) {
				getHttpServletResponse().setStatus(500);
				return ActionResultBuilder.buildActionResult(null, "Event is not exist!", ResultType.Ajax);
			}

			// Perform to UPDATE event
			eventModel.updateEvent(eventId, title, location, startDate, endDate, isAllDay, category,
					getSessionManager().getCredentials(getHttpServletRequest().getSession()).getUserId());
			return ActionResultBuilder.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);
			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
	@Path("/add")
	@Method(RequestMethod.POST)
	public ActionResult add(@Param("title") String title, @Param("location") String location, @Param("startDate") Date startDate, 
			@Param("endDate") Date endDate, @Param("isAllDay") String isAllDay, @Param("category") String category) throws Exception {
		EventModel eventModel = null;

		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}

			eventModel = new EventModel(getConnection());

			// Perform to ADD event
			eventModel.createEvent(SequenceHandler.nextEventId(getConnection()), title, location, startDate, endDate,
					isAllDay, category, getSessionUserId());
			
			return ActionResultBuilder.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);

			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
	@Path("/delete")
	@Method(RequestMethod.POST)
	public ActionResult delete(@Param("eventId") String eventId) throws Exception {
		EventModel eventModel = null;

		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}

			eventModel = new EventModel(getConnection());

			// Event is not exist
			EventEJB event = eventModel.getEvent(eventId);
			if (event == null) {
				getHttpServletResponse().setStatus(500);
				return ActionResultBuilder.buildActionResult(null, "Event is not exist!", ResultType.Ajax);
			}

			// Do not allow to DELETE any Event if administrator flag is false
			if (SystemConstants.ROLE_ADMIN_FLAG_FALSE.equals(getSessionRole().getAdminFlag())) {
				getHttpServletResponse().setStatus(500);
				return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_INSUFFICIENT_PREVILEGE, ResultType.Ajax);
			}

			// Perform to DELETE user
			eventModel.deleteEvent(eventId);
			return ActionResultBuilder.buildActionResult(null, null, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);
			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}	
	
}
