package sg.xueyu.efamily.base.ejb;

import java.util.Date;

public class LoginUserEJB {

	private String userId;

	private String userName;

	private String roleId;

	private String password;

	private Date lastLoginDate;

	private Date registDate;

	private String registPname;

	private Date lastUpdateDate;

	private String lastUpdatePname;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
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
