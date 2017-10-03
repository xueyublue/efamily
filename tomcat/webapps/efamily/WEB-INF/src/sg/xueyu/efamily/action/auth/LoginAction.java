package sg.xueyu.efamily.action.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class LoginAction implements Action {

	private String userId;

	private String password;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<LoginUserEJB> results = UserDao.verify(userId, password);
		ResultContent resultContent = null;
		ActionResult result = null;

		if (results.size() == 1) {
			// set user info to result content
			resultContent = new ResultContent(null, results.get(0));
			// set user info to session
			HttpSession session = req.getSession();
			session.setAttribute("userId", results.get(0).getUserId());
			session.setAttribute("userName", results.get(0).getUserName());
		} else {
			resultContent = new ResultContent(null, null);
			// set user info in session to null
			HttpSession session = req.getSession();
			session.removeAttribute("userId");
			session.removeAttribute("userName");
		}
		result = new ActionResult(resultContent, ResultType.Ajax);

		return result;
	}

}
