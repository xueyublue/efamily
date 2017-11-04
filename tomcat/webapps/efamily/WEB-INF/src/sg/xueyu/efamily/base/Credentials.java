package sg.xueyu.efamily.base;

public class Credentials {

	public static final String USER_ID = "USER_ID";
	public static final String USER_NAME = "USER_NAME";

	private String mUserId;

	private String mUserName;

	public Credentials() {
	}

	public Credentials(String userId, String userName) {
		this.mUserId = userId;
		this.mUserName = userName;
	}

	public String getUserId() {
		return mUserId;
	}

	public void setUserId(String userId) {
		this.mUserId = userId;
	}

	public String getUserName() {
		return mUserName;
	}

	public void setUserName(String userName) {
		this.mUserName = userName;
	}

}
