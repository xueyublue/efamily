package sg.xueyu.efamily.base.dbhandler;

import java.util.Date;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.handler.FieldName;

public class Event extends AbstractEntity {

	public static final String STORE_NAME = "EVENT";

	public static final FieldName EVENT_ID = new FieldName(STORE_NAME, "EVENT_ID");

	public static final FieldName TITLE = new FieldName(STORE_NAME, "TITLE");

	public static final FieldName LOCATION = new FieldName(STORE_NAME, "LOCATION");

	public static final FieldName START_DATE = new FieldName(STORE_NAME, "START_DATE");

	public static final FieldName END_DATE = new FieldName(STORE_NAME, "END_DATE");

	public static final FieldName IS_ALL_DAY = new FieldName(STORE_NAME, "IS_ALL_DAY");

	public static final FieldName REGIST_BY = new FieldName(STORE_NAME, "REGIST_BY");

	public static final FieldName UPDATE_BY = new FieldName(STORE_NAME, "UPDATE_BY");

	public static final FieldName REGIST_DATE = new FieldName(STORE_NAME, "REGIST_DATE");

	public static final FieldName REGIST_PNAME = new FieldName(STORE_NAME, "REGIST_PNAME");

	public static final FieldName LAST_UPDATE_DATE = new FieldName(STORE_NAME, "LAST_UPDATE_DATE");

	public static final FieldName LAST_UPDATE_PNAME = new FieldName(STORE_NAME, "LAST_UPDATE_PNAME");

	public Event() {
		super(STORE_NAME);
	}

	public String getEventId() {
		return String.valueOf(getValue(EVENT_ID, ""));
	}

	public void setEventId(String value) {
		setValue(EVENT_ID, value);
	}

	public String getTitle() {
		return String.valueOf(getValue(TITLE, ""));
	}

	public void setTitle(String value) {
		setValue(TITLE, value);
	}

	public String getLocation() {
		return String.valueOf(getValue(LOCATION, ""));
	}

	public void setLocation(String value) {
		setValue(LOCATION, value);
	}

	public Date getStartDate() {
		return (Date) getValue(START_DATE, "");
	}

	public void setStartDate(Date value) {
		setValue(START_DATE, value);
	}

	public Date getEndDate() {
		return (Date) getValue(END_DATE, "");
	}

	public void setEndDate(Date value) {
		setValue(END_DATE, value);
	}

	public String getIsAllDay() {
		return String.valueOf(getValue(IS_ALL_DAY, ""));
	}

	public void setIsAllDay(String value) {
		setValue(IS_ALL_DAY, value);
	}

	public String getRegistBy() {
		return String.valueOf(getValue(REGIST_BY, ""));
	}

	public void setRegistBy(String value) {
		setValue(REGIST_BY, value);
	}

	public String getUpdateBy() {
		return String.valueOf(getValue(UPDATE_BY, ""));
	}

	public void setUpdateBy(String value) {
		setValue(UPDATE_BY, value);
	}

	public Date getRegistDate() {
		return (Date) getValue(REGIST_DATE, "");
	}

	public void setRegistDate(Date value) {
		setValue(REGIST_DATE, value);
	}

	public String getRegistPname() {
		return String.valueOf(getValue(REGIST_PNAME, ""));
	}

	public void setRegistPname(String value) {
		setValue(REGIST_PNAME, value);
	}

	public Date getLastUpdateDate() {
		return (Date) getValue(LAST_UPDATE_DATE, "");
	}

	public void setLastUpdateDate(Date value) {
		setValue(LAST_UPDATE_DATE, value);
	}

	public String getLastUpdatePname() {
		return String.valueOf(getValue(LAST_UPDATE_PNAME, ""));
	}

	public void setLastUpdatePname(String value) {
		setValue(LAST_UPDATE_PNAME, value);
	}

}
