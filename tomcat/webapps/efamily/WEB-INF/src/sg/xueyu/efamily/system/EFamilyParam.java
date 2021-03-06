package sg.xueyu.efamily.system;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import sg.xueyu.efamily.base.ResourceUtil;

public final class EFamilyParam {

/** Property File Name **/
	private static final String WMS_DEFAULT_RESOURCE = "EFamilyParam";

/** Properties **/
	public static final String APP_NAME = getParam("APP_NAME");
	
	public static final String DB_DRIVER = getParam("DB_DRIVER");
	
	public static final String DB_URL = getParam("DB_URL");
	
	public static final String DB_USERNAME = getParam("DB_USERNAME");
	
	public static final String DB_PASSWORD = getParam("DB_PASSWORD");
	
	public static final boolean ENABLE_LOGGING = getBoolParam("ENABLE_LOGGING");
	
	public static final int SESSION_TIME_OUT = getIntParam("SESSION_TIME_OUT");
	
	public static final String URL_PATTERN = getParam("URL_PATTERN");

	public static final String URL_PATTERN_SUFFIX = getParam("URL_PATTERN_SUFFIX");

	public static final String CLOUD_ROOT_PATH = getParam("CLOUD_ROOT_PATH");
	public static final String CLOUD_DOCUMENT_PATH = getParam("CLOUD_DOCUMENT_PATH");
	public static final String CLOUD_MUSIC_PATH = getParam("CLOUD_MUSIC_PATH");
	public static final String CLOUD_PICTURE_PATH = getParam("CLOUD_PICTURE_PATH");
	public static final String CLOUD_VIDEO_PATH = getParam("CLOUD_VIDEO_PATH");
	
/** Class **/
	private EFamilyParam() {
	}

	private static String getParam(String key) {
		try {
			ResourceBundle rb = getBundle(WMS_DEFAULT_RESOURCE, Locale.getDefault());
			if (rb != null) {
				return (rb.getString(key));
			}
		} catch (Exception e) {
		}
		return "";

	}
	
	private static int getIntParam(String key) {
		try {
			return Integer.parseInt(getParam(key));
		} catch (Exception e) {
			// do nothing.
		}
		return -1;
	}

	@SuppressWarnings("unused")
	private static double getDoubleParam(String key) {
		try {
			return Double.parseDouble(getParam(key));
		} catch (Exception e) {
		}
		return -1;
	}

	@SuppressWarnings("unused")
	private static ArrayList<String> getListParam(String key) {
		ArrayList<String> retList = new ArrayList<String>();
		try {
			StringTokenizer st = new StringTokenizer(getParam(key), ",");
			while (st.hasMoreTokens()) {
				retList.add(st.nextToken());
			}
		} catch (Exception e) {
		}
		return retList;
	}
	
	private static boolean getBoolParam(String key) {
		return ResourceUtil.getBoolParam(WMS_DEFAULT_RESOURCE, key);
	}

	private static ResourceBundle getBundle(String res, Locale locale) {
		return (ResourceBundle.getBundle(res, locale));
	}
}
