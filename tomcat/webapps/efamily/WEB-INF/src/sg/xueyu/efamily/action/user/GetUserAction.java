package sg.xueyu.efamily.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.action.BaseAction;
import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class GetUserAction extends BaseAction implements Action {

	public GetUserAction() throws Exception {
		super();
	}

	private String userId;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult actionResult = null;

		try {
			ActionResult authResult = credentialAuthentication(req);
			if (authResult != null) {
				return authResult;
			}

			// Perform to GET user
			LoginUserEJB userEJB = getUserDao().getUser(userId);

			resultContent = new ResultContent(null, userEJB);

			return new ActionResult(resultContent, ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);

			resp.setStatus(500);
			resultContent = new ResultContent(null, "UnHandled Exception Occurred!!!");
			actionResult = new ActionResult(resultContent, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}

		return actionResult;

	}
}
