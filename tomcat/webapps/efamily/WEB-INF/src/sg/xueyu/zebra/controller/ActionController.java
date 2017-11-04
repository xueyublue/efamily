package sg.xueyu.zebra.controller;

import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class ActionController {

	// Build resultContent with URL/Data
	public static ResultContent buildResultContent(String url, Object data) {
		return new ResultContent(url, data);
	}

	// Build resultContent with URL/Data
	public static ResultContent buildResultContent(String url, Object data, String dateFormat) {
		ResultContent resultContent = new ResultContent(url, data);
		resultContent.setDateFormat(dateFormat);

		return resultContent;
	}

	// Build ActionResult with URL
	public static ActionResult buildActionResultWithURL(String url) {
		return new ActionResult(buildResultContent(url, null), ResultType.Forward);
	}

	// Build ActionResult with Data
	public static ActionResult buildActionResultWithData(Object data) {
		return new ActionResult(buildResultContent(null, data), ResultType.Forward);
	}

	// Build ActionResult with URL/Data
	public static ActionResult buildActionResult(String url, Object data) {
		return new ActionResult(buildResultContent(url, data), ResultType.Forward);
	}

	// Build ActionResult with URL/Data/Date Format
	public static ActionResult buildActionResult(String url, Object data, String dateFormat) {
		return new ActionResult(buildResultContent(url, data, dateFormat), ResultType.Forward);
	}

	// Build ActionResult with URL/Data/Date Format/ResultType
	public static ActionResult buildActionResult(String url, Object data, ResultType resultType) {
		return new ActionResult(buildResultContent(url, data), resultType);
	}

	// Build ActionResult with URL/Data/Date Format/ResultType
	public static ActionResult buildActionResult(String url, Object data, String dateFormat, ResultType resultType) {
		return new ActionResult(buildResultContent(url, data, dateFormat), resultType);
	}

	// Build ActionResult with ResultContent
	public static ActionResult buildActionResult(ResultContent resultContent) {
		return new ActionResult(resultContent, ResultType.Forward);
	}

	// Build ActionResult with ResultContent/ResultType
	public static ActionResult buildActionResult(ResultContent resultContent, ResultType resultType) {
		return new ActionResult(resultContent, resultType);
	}

}
