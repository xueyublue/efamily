package sg.xueyu.efamily.system;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class SystemLogger {

	private static final String DATE_TIME_FORMATE = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat(DATE_TIME_FORMATE);
	
	private static final Logger logger = Logger.getLogger("HttpRequest");
	private static final String tagRequest	 = "E-Family <<< Client ";
	private static final String tagResponse	 = "E-Family >>> Client ";

	public static void infoRequest(Object reqMsg, String msgID) {
		System.out.println(DATE_TIME_FORMATTER.format(new Date()) + " INFO " + tagRequest + msgID + " " + reqMsg);
		logger.info(tagRequest + msgID + " " + reqMsg);
	}

	public static void infoResponse(Object resMsg, String msgID) {
		System.out.println(DATE_TIME_FORMATTER.format(new Date()) + " INFO " + tagResponse + msgID + " " + resMsg);
		logger.info(tagResponse + msgID + " " + resMsg);
	}
	
	public static void errorRequest(Object reqMsg, String msgID) {
		System.err.println(DATE_TIME_FORMATTER.format(new Date()) + " ERROR " + tagRequest + msgID + " " + reqMsg);
		logger.error(tagRequest + msgID + " " + reqMsg);
	}

	public static void errorResponse(Object resMsg, String msgID) {
		System.err.println(DATE_TIME_FORMATTER.format(new Date()) + " ERROR " + tagResponse + msgID + " " + resMsg);
		logger.error(tagResponse + msgID + " " + resMsg);
	}
	
	public static void info(String log) {
		System.out.println(DATE_TIME_FORMATTER.format(new Date()) + " INFO " + log);
		logger.info(log);
	}
	
	public static void error(String log) {
		System.err.println(DATE_TIME_FORMATTER.format(new Date()) + " ERROR " + log);
		logger.error(log);
	}
	
	public static void error(Exception exception) {
		System.err.println(DATE_TIME_FORMATTER.format(new Date()) + " ERROR " + CommonMethod.getStackTrace(exception));
		logger.error(CommonMethod.getStackTrace(exception));
	}
}
