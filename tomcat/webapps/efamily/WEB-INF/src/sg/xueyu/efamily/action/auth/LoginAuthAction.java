package sg.xueyu.efamily.action.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sg.xueyu.efamily.base.ejb.LoginUserEJB;
import sg.xueyu.efamily.dao.UserDao;
import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;

public class LoginAuthAction implements Action {

	private String userId;

	private String password;

	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ResultContent resultContent = null;
		ActionResult result = null;
		
		String authResult = UserDao.auth(userId, password);
		
		if (authResult == null) {
			// set user info to result content
			resultContent = new ResultContent(null, null);
			// set user info to session
			HttpSession session = req.getSession();
			LoginUserEJB loginUserEJB = UserDao.getUser(userId);
			session.setAttribute("userId", loginUserEJB.getUserId());
			session.setAttribute("userName", loginUserEJB.getUserName());
		} else {
			resp.setStatus(401);
			resultContent = new ResultContent(null, authResult);
			// set user info in session to null
			HttpSession session = req.getSession();
			session.removeAttribute("userId");
			session.removeAttribute("userName");
		}
		result = new ActionResult(resultContent, ResultType.Ajax);

		return result;
	}

}
