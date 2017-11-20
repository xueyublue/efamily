package sg.xueyu.efamily.model.dbhandler;

import java.util.Date;

import sg.xueyu.dbhandler.conf.CompareCode;
import sg.xueyu.dbhandler.handler.AbstractSearchKey;
import sg.xueyu.efamily.model.dbhandler.Role;

public class RoleSearchKey extends AbstractSearchKey {

	public RoleSearchKey() {
		super(Role.STORE_NAME);
	}

	public void setRoleId(String value) {
		setStringKey(Role.ROLE_ID, value);
	}

	public void setRoleId(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Role.ROLE_ID, compCode, value);
	}

	public void setRoleName(String value) {
		setStringKey(Role.ROLE_NAME, value);
	}

	public void setRoleName(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Role.ROLE_NAME, compCode, value);
	}

	public void setAdminFlag(String value) {
		setStringKey(Role.ADMIN_FLAG, value);
	}

	public void setAdminFlag(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Role.ADMIN_FLAG, compCode, value);
	}

	public void setGuestFlag(String value) {
		setStringKey(Role.GUEST_FLAG, value);
	}

	public void setGuestFlag(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Role.GUEST_FLAG, compCode, value);
	}

	public void setExpiryDate(Date value) {
		setDateKey(Role.EXPIRY_DATE, value);
	}

	public void setExpiryDate(CompareCode compCode, Date value) {
		setDateKeyWithCompCode(Role.EXPIRY_DATE, compCode, value);
	}

	public void setRegistDate(Date value) {
		setDateKey(Role.REGIST_DATE, value);
	}

	public void setRegistDate(CompareCode compCode, Date value) {
		setDateKeyWithCompCode(Role.REGIST_DATE, compCode, value);
	}

	public void setRegistPname(String value) {
		setStringKey(Role.REGIST_PNAME, value);
	}

	public void setRegistPname(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Role.REGIST_PNAME, compCode, value);
	}

	public void setLastUpdateDate(Date value) {
		setDateKey(Role.LAST_UPDATE_DATE, value);
	}

	public void setLastUpdateDate(CompareCode compCode, Date value) {
		setDateKeyWithCompCode(Role.LAST_UPDATE_DATE, compCode, value);
	}

	public void setLastUpdatePname(String value) {
		setStringKey(Role.LAST_UPDATE_PNAME, value);
	}

	public void setLastUpdatePname(CompareCode compCode, String value) {
		setStringKeyWithCompCode(Role.LAST_UPDATE_PNAME, compCode, value);
	}


	public void setRoleIdOrder(boolean value) {
		setOrder(Role.ROLE_ID, value);
	}

	public void setRoleNameOrder(boolean value) {
		setOrder(Role.ROLE_NAME, value);
	}

	public void setAdminFlagOrder(boolean value) {
		setOrder(Role.ADMIN_FLAG, value);
	}

	public void setGuestFlagOrder(boolean value) {
		setOrder(Role.GUEST_FLAG, value);
	}

	public void setExpiryDateOrder(boolean value) {
		setOrder(Role.EXPIRY_DATE, value);
	}

	public void setRegistDateOrder(boolean value) {
		setOrder(Role.REGIST_DATE, value);
	}

	public void setRegistPnameOrder(boolean value) {
		setOrder(Role.REGIST_PNAME, value);
	}

	public void setLastUpdateDateOrder(boolean value) {
		setOrder(Role.LAST_UPDATE_DATE, value);
	}

	public void setLastUpdatePnameOrder(boolean value) {
		setOrder(Role.LAST_UPDATE_PNAME, value);
	}

}
