package sg.xueyu.efamily.system;

import javax.servlet.http.HttpServletResponse;

import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class ActionResultController {

	// 901
	// Session time out
	// session user is not exist
	// session role is not exist
	public static final ActionResult sessionError(HttpServletResponse resp) {
		resp.setStatus(901);
		ResultContent resultContent = new ResultContent(null, null);

		return new ActionResult(resultContent, ResultType.Ajax);
	}
}
