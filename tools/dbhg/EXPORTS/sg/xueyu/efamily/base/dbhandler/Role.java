package sg.xueyu.efamily.base.dbhandler;

import java.util.Date;

import sg.xueyu.dbhandler.handler.AbstractEntity;
import sg.xueyu.dbhandler.handler.FieldName;

public class Role extends AbstractEntity {

	public static final String STORE_NAME = "ROLE";

	public static final FieldName ROLE_ID = new FieldName(STORE_NAME, "ROLE_ID");

	public static final FieldName ROLE_NAME = new FieldName(STORE_NAME, "ROLE_NAME");

	public static final FieldName ADMIN_FLAG = new FieldName(STORE_NAME, "ADMIN_FLAG");

	public static final FieldName GUEST_FLAG = new FieldName(STORE_NAME, "GUEST_FLAG");

	public static final FieldName EXPIRY_DATE = new FieldName(STORE_NAME, "EXPIRY_DATE");

	public static final FieldName REGIST_DATE = new FieldName(STORE_NAME, "REGIST_DATE");

	public static final FieldName REGIST_PNAME = new FieldName(STORE_NAME, "REGIST_PNAME");

	public static final FieldName LAST_UPDATE_DATE = new FieldName(STORE_NAME, "LAST_UPDATE_DATE");

	public static final FieldName LAST_UPDATE_PNAME = new FieldName(STORE_NAME, "LAST_UPDATE_PNAME");

	public Role() {
		super(STORE_NAME);
	}

	public String getRoleId() {
		return String.valueOf(getValue(ROLE_ID, ""));
	}

	public void setRoleId(String value) {
		setValue(ROLE_ID, value);
	}

	public String getRoleName() {
		return String.valueOf(getValue(ROLE_NAME, ""));
	}

	public void setRoleName(String value) {
		setValue(ROLE_NAME, value);
	}

	public String getAdminFlag() {
		return String.valueOf(getValue(ADMIN_FLAG, ""));
	}

	public void setAdminFlag(String value) {
		setValue(ADMIN_FLAG, value);
	}

	public String getGuestFlag() {
		return String.valueOf(getValue(GUEST_FLAG, ""));
	}

	public void setGuestFlag(String value) {
		setValue(GUEST_FLAG, value);
	}

	public Date getExpiryDate() {
		return (Date) getValue(EXPIRY_DATE, "");
	}

	public void setExpiryDate(Date value) {
		setValue(EXPIRY_DATE, value);
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
