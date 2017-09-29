package sg.xueyu.efamily.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_PACKAGE_NAME = "sg.xueyu.efamily.action.";
	private static final String DEFAULT_ACTION_NAME = "Action";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get ServletPath
		String servletPath = request.getServletPath();
		// Get ActionClassName from request
		int start = 1;
		int end = servletPath.lastIndexOf(".do");
		String actionName = end > start ? servletPath.substring(start, end) + DEFAULT_ACTION_NAME : "";
		String actionClassName = DEFAULT_PACKAGE_NAME + actionName.substring(0, 1).toUpperCase() + actionName.substring(1);
		// Invoke ActionClassName by reflection
		System.out.println(actionClassName);
	}

}
