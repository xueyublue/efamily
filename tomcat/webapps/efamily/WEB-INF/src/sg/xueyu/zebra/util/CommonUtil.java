package sg.xueyu.zebra.util;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class CommonUtil {

	private static final List<String> patterns = new ArrayList<>();
	private static final List<TypeConverter> converters = new ArrayList<>();

	static {
		patterns.add("yyyy-MM-dd HH:mm:ss");
		patterns.add("yyyy-MM-dd");
	}

	private CommonUtil() {
		throw new AssertionError();
	}

	// UpCase first character
	public static String capitalize(String str) {
		StringBuilder sb = new StringBuilder();
		if (str != null && str.length() > 0) {
			sb.append(str.substring(0, 1).toUpperCase());
			if (str.length() > 1) {
				sb.append(str.substring(1));
			}
			return sb.toString();
		}
		return str;
	}

	// Get random color
	public static Color getRandomColor() {
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		return new Color(r, g, b);
	}

	public static void registerDateTimePattern(String pattern) {
		patterns.add(pattern);
	}

	public static void unRegisterDateTimePattern(String pattern) {
		patterns.remove(pattern);
	}

	public static void registerTypeConverter(TypeConverter converter) {
		converters.add(converter);
	}

	public static void unRegisterTypeConverter(TypeConverter converter) {
		converters.remove(converter);
	}

	public static Date convertStringToDateTime(String str) {
		if (str != null) {
			for (String pattern : patterns) {
				Date date = tryConvertStringToDate(str, pattern);
				if (date != null) {
					return date;
				}
			}
		}

		return null;
	}

	public static String convertDateTimeToString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	private static Date tryConvertStringToDate(String str, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);

		try {
			return dateFormat.parse(str);
		} catch (ParseException ex) {
		}

		return null;
	}

	public static Object changeStringToObject(Class<?> elemType, String value) {
		Object tempObj = null;

		if (elemType == byte.class || elemType == Byte.class) {
			tempObj = Byte.parseByte(value);
		} else if (elemType == short.class || elemType == Short.class) {
			tempObj = Short.parseShort(value);
		} else if (elemType == int.class || elemType == Integer.class) {
			tempObj = Integer.parseInt(value);
		} else if (elemType == long.class || elemType == Long.class) {
			tempObj = Long.parseLong(value);
		} else if (elemType == double.class || elemType == Double.class) {
			tempObj = Double.parseDouble(value);
		} else if (elemType == float.class || elemType == Float.class) {
			tempObj = Float.parseFloat(value);
		} else if (elemType == boolean.class || elemType == Boolean.class) {
			tempObj = Boolean.parseBoolean(value);
		} else if (elemType == java.util.Date.class) {
			tempObj = convertStringToDateTime(value);
		} else if (elemType == java.lang.String.class) {
			tempObj = value;
		} else {
			for (TypeConverter converter : converters) {
				try {
					tempObj = converter.convert(elemType, value);
					if (tempObj != null) {
						return tempObj;
					}
				} catch (Exception e) {
				}
			}
		}

		return tempObj;
	}

	public static String getFileSuffix(String filename) {
		int index = filename.lastIndexOf(".");
		return index > 0 ? filename.substring(index) : "";
	}

}
