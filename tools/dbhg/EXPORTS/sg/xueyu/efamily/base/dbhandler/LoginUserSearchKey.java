package sg.xueyu.efamily.base.dbhandler;

import java.util.Date;

import sg.xueyu.dbhandler.conf.CompareCode;
import sg.xueyu.dbhandler.handler.AbstractSearchKey;

import sg.xueyu.efamily.base.dbhandler.LoginUser;

public class LoginUserSearchKey extends AbstractSearchKey {

	public LoginUserSearchKey() {
		super(LoginUser.STORE_NAME);
	}

	public void setUserId(String value) {
		setStringKey(LoginUser.USER_ID, value);
	}

	public void setUserId(CompareCode compCode, String value) {
		setStringKeyWithCompCode(LoginUser.USER_ID, compCode, value);
	}

	public void setUserName(String value) {
		setStringKey(LoginUser.USER_NAME, value);
	}

	public void setUserName(CompareCode compCode, String value) {
		setStringKeyWithCompCode(LoginUser.USER_NAME, compCode, value);
	}

	public void setRoleId(String value) {
		setStringKey(LoginUser.ROLE_ID, value);
	}

	public void setRoleId(CompareCode compCode, String value) {
		setStringKeyWithCompCode(LoginUser.ROLE_ID, compCode, value);
	}

	public void setPassword(String value) {
		setStringKey(LoginUser.PASSWORD, value);
	}

	public void setPassword(CompareCode compCode, String value) {
		setStringKeyWithCompCode(LoginUser.PASSWORD, compCode, value);
	}

	public void setLastLoginDate(Date value) {
		setDateKey(LoginUser.LAST_LOGIN_DATE, value);
	}

	public void setLastLoginDate(CompareCode compCode, Date value) {
		setDateKeyWithCompCode(LoginUser.LAST_LOGIN_DATE, compCode, value);
	}

	public void setRegistDate(Date value) {
		setDateKey(LoginUser.REGIST_DATE, value);
	}

	public void setRegistDate(CompareCode compCode, Date value) {
		setDateKeyWithCompCode(LoginUser.REGIST_DATE, compCode, value);
	}

	public void setRegistPname(String value) {
		setStringKey(LoginUser.REGIST_PNAME, value);
	}

	public void setRegistPname(CompareCode compCode, String value) {
		setStringKeyWithCompCode(LoginUser.REGIST_PNAME, compCode, value);
	}

	public void setLastUpdateDate(Date value) {
		setDateKey(LoginUser.LAST_UPDATE_DATE, value);
	}

	public void setLastUpdateDate(CompareCode compCode, Date value) {
		setDateKeyWithCompCode(LoginUser.LAST_UPDATE_DATE, compCode, value);
	}

	public void setLastUpdatePname(String value) {
		setStringKey(LoginUser.LAST_UPDATE_PNAME, value);
	}

	public void setLastUpdatePname(CompareCode compCode, String value) {
		setStringKeyWithCompCode(LoginUser.LAST_UPDATE_PNAME, compCode, value);
	}

}
