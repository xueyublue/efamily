package sg.xueyu.efamily.model.dbhandler;

import java.util.Date;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.handler.FieldName;

public class LoginUser extends AbstractEntity {

	public static final String STORE_NAME = "LOGIN_USER";

	public static final FieldName USER_ID = new FieldName(STORE_NAME, "USER_ID");

	public static final FieldName USER_NAME = new FieldName(STORE_NAME, "USER_NAME");

	public static final FieldName ROLE_ID = new FieldName(STORE_NAME, "ROLE_ID");

	public static final FieldName PASSWORD = new FieldName(STORE_NAME, "PASSWORD");

	public static final FieldName LAST_LOGIN_DATE = new FieldName(STORE_NAME, "LAST_LOGIN_DATE");

	public static final FieldName REGIST_DATE = new FieldName(STORE_NAME, "REGIST_DATE");

	public static final FieldName REGIST_PNAME = new FieldName(STORE_NAME, "REGIST_PNAME");

	public static final FieldName LAST_UPDATE_DATE = new FieldName(STORE_NAME, "LAST_UPDATE_DATE");

	public static final FieldName LAST_UPDATE_PNAME = new FieldName(STORE_NAME, "LAST_UPDATE_PNAME");

	public LoginUser() {
		super(STORE_NAME);
	}

	public String getUserId() {
		return String.valueOf(getValue(USER_ID, ""));
	}

	public void setUserId(String value) {
		setValue(USER_ID, value);
	}

	public String getUserName() {
		return String.valueOf(getValue(USER_NAME, ""));
	}

	public void setUserName(String value) {
		setValue(USER_NAME, value);
	}

	public String getRoleId() {
		return String.valueOf(getValue(ROLE_ID, ""));
	}

	public void setRoleId(String value) {
		setValue(ROLE_ID, value);
	}

	public String getPassword() {
		return String.valueOf(getValue(PASSWORD, ""));
	}

	public void setPassword(String value) {
		setValue(PASSWORD, value);
	}

	public Date getLastLoginDate() {
		return (Date) getValue(LAST_LOGIN_DATE, "");
	}

	public void setLastLoginDate(Date value) {
		setValue(LAST_LOGIN_DATE, value);
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
