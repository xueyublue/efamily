package sg.xueyu.efamily.base.ejb;

import java.util.Date;

public class RoleEJB {

	private String roleId;

	private String roleName;

	private String adminFlag;

	private String guestFlag;

	private Date expiryDate;

	private Date registDate;

	private String registPname;

	private Date lastUpdateDate;

	private String lastUpdatePname;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}

	public String getGuestFlag() {
		return guestFlag;
	}

	public void setGuestFlag(String guestFlag) {
		this.guestFlag = guestFlag;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
