package sg.xueyu.efamily.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.dbhandler.util.HandlerUtil;
import sg.xueyu.efamily.base.dbhandler.Event;
import sg.xueyu.efamily.base.dbhandler.EventAlterKey;
import sg.xueyu.efamily.base.dbhandler.EventHandler;
import sg.xueyu.efamily.base.dbhandler.EventSearchKey;
import sg.xueyu.efamily.base.ejb.EventEJB;

public class EventDao {

	private Connection mConnection = null;

	public EventDao(Connection conn) {
		this.mConnection = conn;
	}

	public List<EventEJB> getAllEvents() throws Exception {
		EventHandler handler = new EventHandler(mConnection);
		EventSearchKey searchKey = new EventSearchKey();

		searchKey.setStartDateOrder(true);

		AbstractEntity[] results = handler.query(searchKey);
		List<EventEJB> actualResults = new ArrayList<>();

		for (int i = 0; i < results.length; i++) {
			actualResults.add((EventEJB) HandlerUtil.entity2bean(results[i], EventEJB.class));
		}

		return actualResults;
	}

	public EventEJB getEvent(String eventId) throws Exception {
		EventHandler handler = new EventHandler(mConnection);

		EventSearchKey searchKey = new EventSearchKey();
		searchKey.setEventId(eventId);

		AbstractEntity[] results = handler.query(searchKey);
		if (results.length == 1) {
			return (EventEJB) HandlerUtil.entity2bean(results[0], EventEJB.class);
		}

		return null;
	}

	public void createEvent(String eventId, String title, String location, Date startDate, Date endDate,
			String isAllDay, String category, String registBy) throws Exception {
		EventHandler handler = new EventHandler(mConnection);

		Event event = new Event();
		event.setEventId(eventId);
		event.setTitle(title);
		event.setLocation(location);
		event.setStartDate(startDate);
		event.setEndDate(endDate);
		event.setIsAllDay(isAllDay);
		event.setCategory(category);
		event.setRegistBy(registBy);

		event.setRegistPname(EventDao.class.getSimpleName());

		handler.create(event);

		DBUtils.commit(mConnection);
	}

	public void updateEvent(String eventId, String title, String location, Date startDate, Date endDate,
			String isAllDay, String category, String updateBy) throws Exception {
		EventHandler handler = new EventHandler(mConnection);
		EventAlterKey alterKey = new EventAlterKey();

		alterKey.setEventId(eventId);

		alterKey.updateTitle(title);
		alterKey.updateLocation(location);
		alterKey.updateStartDate(startDate);
		alterKey.updateEndDate(endDate);
		alterKey.updateIsAllDay(isAllDay);
		alterKey.updateCategory(category);
		alterKey.updateUpdateBy(updateBy);

		alterKey.updateLastUpdatePname(EventDao.class.getSimpleName());

		handler.update(alterKey);

		DBUtils.commit(mConnection);
	}

	public void deleteEvent(String eventId) throws Exception {
		EventHandler handler = new EventHandler(mConnection);

		EventAlterKey alterKey = new EventAlterKey();
		alterKey.setEventId(eventId);

		handler.delete(alterKey);

		DBUtils.commit(mConnection);
	}

}
