package sg.xueyu.zebra.util;

import java.net.URL;

public class StringUtils {

	// Check is object is null or blank string
	public static boolean isBlank(Object obj) {
		if (obj == null || "".equals(obj.toString().trim())) {
			return true;
		}

		return false;
	}

	// Replace dot to slash
	public static String dotToSlash(String str) {
		if (isBlank(str)) {
			return str;
		}

		return str.replaceAll("\\.", "/");
	}

	// Get root path of Resource
	// file:/C:/app/tomcat/webapps/app/WEB-INF/lib/log4j-1.2.15.jar!/org/apache/log4j
	// -> C:/app/tomcat/webapps/app/WEB-INF/lib/log4j-1.2.15.jar
	public static String getRootPath(URL url) {
		String fileURL = url.getFile();
		int pos = fileURL.indexOf("!");

		if (-1 == pos) {
			return fileURL;
		}

		return fileURL.substring(5, pos);
	}

	// Trim extension
	public static String trimExtension(String name) {
		int pos = name.indexOf('.');

		if (-1 != pos) {
			return name.substring(0, pos);
		}

		return name;
	}
}
