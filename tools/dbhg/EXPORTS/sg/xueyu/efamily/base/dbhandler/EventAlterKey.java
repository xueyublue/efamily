package sg.xueyu.efamily.base.dbhandler;

import java.util.Date;

import sg.xueyu.dbhandler.conf.CompareCode;
import sg.xueyu.dbhandler.handler.AbstractAlterKey;

import sg.xueyu.efamily.base.dbhandler.Event;

public class EventAlterKey extends AbstractAlterKey {

	public EventAlterKey() {
		super(Event.STORE_NAME);
	}

	public void setEventId(String value) {
		setStringKey(Event.EVENT_ID, value);
	}

	public void setEventId(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Event.EVENT_ID, compCode, value);
	}

	public void setTitle(String value) {
		setStringKey(Event.TITLE, value);
	}

	public void setTitle(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Event.TITLE, compCode, value);
	}

	public void setLocation(String value) {
		setStringKey(Event.LOCATION, value);
	}

	public void setLocation(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Event.LOCATION, compCode, value);
	}

	public void setStartDate(Date value) {
		setDateKey(Event.START_DATE, value);
	}

	public void setStartDate(CompareCode compCode, Date value) {
		setDateKeyWithCompCode(Event.START_DATE, compCode, value);
	}

	public void setEndDate(Date value) {
		setDateKey(Event.END_DATE, value);
	}

	public void setEndDate(CompareCode compCode, Date value) {
		setDateKeyWithCompCode(Event.END_DATE, compCode, value);
	}

	public void setIsAllDay(String value) {
		setStringKey(Event.IS_ALL_DAY, value);
	}

	public void setIsAllDay(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Event.IS_ALL_DAY, compCode, value);
	}

	public void setCategory(String value) {
		setStringKey(Event.CATEGORY, value);
	}

	public void setCategory(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Event.CATEGORY, compCode, value);
	}

	public void setRegistBy(String value) {
		setStringKey(Event.REGIST_BY, value);
	}

	public void setRegistBy(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Event.REGIST_BY, compCode, value);
	}

	public void setUpdateBy(String value) {
		setStringKey(Event.UPDATE_BY, value);
	}

	public void setUpdateBy(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Event.UPDATE_BY, compCode, value);
	}

	public void setRegistDate(Date value) {
		setDateKey(Event.REGIST_DATE, value);
	}

	public void setRegistDate(CompareCode compCode, Date value) {
		setDateKeyWithCompCode(Event.REGIST_DATE, compCode, value);
	}

	public void setRegistPname(String value) {
		setStringKey(Event.REGIST_PNAME, value);
	}

	public void setRegistPname(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Event.REGIST_PNAME, compCode, value);
	}

	public void setLastUpdateDate(Date value) {
		setDateKey(Event.LAST_UPDATE_DATE, value);
	}

	public void setLastUpdateDate(CompareCode compCode, Date value) {
		setDateKeyWithCompCode(Event.LAST_UPDATE_DATE, compCode, value);
	}

	public void setLastUpdatePname(String value) {
		setStringKey(Event.LAST_UPDATE_PNAME, value);
	}

	public void setLastUpdatePname(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Event.LAST_UPDATE_PNAME, compCode, value);
	}

	public void updateEventId(String value) {
		setStringUpdateKey(Event.EVENT_ID, value);
	}

	public void updateTitle(String value) {
		setStringUpdateKey(Event.TITLE, value);
	}

	public void updateLocation(String value) {
		setStringUpdateKey(Event.LOCATION, value);
	}

	public void updateStartDate(Date value) {
		setDateUpdateKey(Event.START_DATE, value);
	}

	public void updateEndDate(Date value) {
		setDateUpdateKey(Event.END_DATE, value);
	}

	public void updateIsAllDay(String value) {
		setStringUpdateKey(Event.IS_ALL_DAY, value);
	}

	public void updateCategory(String value) {
		setStringUpdateKey(Event.CATEGORY, value);
	}

	public void updateRegistBy(String value) {
		setStringUpdateKey(Event.REGIST_BY, value);
	}

	public void updateUpdateBy(String value) {
		setStringUpdateKey(Event.UPDATE_BY, value);
	}

	public void updateRegistDate(Date value) {
		setDateUpdateKey(Event.REGIST_DATE, value);
	}

	public void updateRegistPname(String value) {
		setStringUpdateKey(Event.REGIST_PNAME, value);
	}

	public void updateLastUpdateDate(Date value) {
		setDateUpdateKey(Event.LAST_UPDATE_DATE, value);
	}

	public void updateLastUpdatePname(String value) {
		setStringUpdateKey(Event.LAST_UPDATE_PNAME, value);
	}

}
