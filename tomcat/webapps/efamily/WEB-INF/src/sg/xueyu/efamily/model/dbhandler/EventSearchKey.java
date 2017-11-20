package sg.xueyu.efamily.model.dbhandler;

import java.util.Date;

import sg.xueyu.dbhandler.conf.CompareCode;
import sg.xueyu.dbhandler.handler.AbstractSearchKey;

import sg.xueyu.efamily.model.dbhandler.Event;

public class EventSearchKey extends AbstractSearchKey {

	public EventSearchKey() {
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


	public void setEventIdOrder(boolean value) {
		setOrder(Event.EVENT_ID, value);
	}

	public void setTitleOrder(boolean value) {
		setOrder(Event.TITLE, value);
	}

	public void setLocationOrder(boolean value) {
		setOrder(Event.LOCATION, value);
	}

	public void setStartDateOrder(boolean value) {
		setOrder(Event.START_DATE, value);
	}

	public void setEndDateOrder(boolean value) {
		setOrder(Event.END_DATE, value);
	}

	public void setIsAllDayOrder(boolean value) {
		setOrder(Event.IS_ALL_DAY, value);
	}

	public void setCategoryOrder(boolean value) {
		setOrder(Event.CATEGORY, value);
	}

	public void setRegistByOrder(boolean value) {
		setOrder(Event.REGIST_BY, value);
	}

	public void setUpdateByOrder(boolean value) {
		setOrder(Event.UPDATE_BY, value);
	}

	public void setRegistDateOrder(boolean value) {
		setOrder(Event.REGIST_DATE, value);
	}

	public void setRegistPnameOrder(boolean value) {
		setOrder(Event.REGIST_PNAME, value);
	}

	public void setLastUpdateDateOrder(boolean value) {
		setOrder(Event.LAST_UPDATE_DATE, value);
	}

	public void setLastUpdatePnameOrder(boolean value) {
		setOrder(Event.LAST_UPDATE_PNAME, value);
	}

}
