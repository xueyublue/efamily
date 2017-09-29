package sg.xueyu.efamily.base;

import java.util.Locale;
import java.util.ResourceBundle;

public final class ResourceUtil extends Object {

	public static String getParam(String resName, String key) {
		try {
			ResourceBundle rb = ResourceBundle.getBundle(resName, Locale.getDefault());
			String retStr = (rb != null) ? rb.getString(key) : "";
			return retStr;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static int getIntParam(String resName, String key) {
		try {
			return Integer.parseInt(getParam(resName, key));
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static long getLongParam(String resName, String key) {
		try {
			return Long.parseLong(getParam(resName, key));
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static boolean getBoolParam(String resName, String key) {
		try {
			return Boolean.valueOf(getParam(resName, key)).booleanValue();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
