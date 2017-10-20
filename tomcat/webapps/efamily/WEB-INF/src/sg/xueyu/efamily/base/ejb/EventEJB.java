package sg.xueyu.efamily.base.ejb;

import java.util.Date;

public class EventEJB {

	private String eventId;

	private String title;

	private String location;

	private Date startDate;

	private Date endDate;

	private String isAllDay;

	private String registBy;

	private String updateBy;

	private Date registDate;

	private String registPname;

	private Date lastUpdateDate;

	private String lastUpdatePname;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIsAllDay() {
		return isAllDay;
	}

	public void setIsAllDay(String isAllDay) {
		this.isAllDay = isAllDay;
	}

	public String getRegistBy() {
		return registBy;
	}

	public void setRegistBy(String registBy) {
		this.registBy = registBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public String getRegistPname() {
		return registPname;
	}

	public void setRegistPname(String registPname) {
		this.registPname = registPname;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdatePname() {
		return lastUpdatePname;
	}

	public void setLastUpdatePname(String lastUpdatePname) {
		this.lastUpdatePname = lastUpdatePname;
	}

}
