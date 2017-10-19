package sg.xueyu.zebra;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import sg.xueyu.zebra.action.Action;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.action.Uploadable;
import sg.xueyu.zebra.util.CommonUtil;
import sg.xueyu.zebra.util.ReflectionUtil;

@MultipartConfig
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_PACKAGE_NAME = "";
	private static final String DEFAULT_ACTION_NAME = "Action";
	private static final String DEFAULT_JSP_PATH = "/WEB-INF/jsp/";
	
	private String packagePrefix = null;
	private String actionSuffix = null;
	private String jspPrefix = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		String initParam = config.getInitParameter("packagePrefix");
		packagePrefix = initParam != null ? initParam : DEFAULT_PACKAGE_NAME;
		initParam = config.getInitParameter("actionSuffix");
		actionSuffix = initParam != null ? initParam : DEFAULT_ACTION_NAME;
		initParam = config.getInitParameter("jspPrefix");
		jspPrefix = initParam != null ? initParam : DEFAULT_JSP_PATH;
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath() + "/";
		String servletPath = req.getServletPath();

		try {
			Action action = (Action) Class.forName(getFullActionName(servletPath)).newInstance();
			try {
				injectProperties(action, req);
			} catch (Exception e) {
			}
			if (action instanceof Uploadable) {
				List<Part> fileparts = new ArrayList<>();
				List<String> filenames = new ArrayList<>();
				for (Part part : req.getParts()) {
					String cd = part.getHeader("Content-Disposition");
					if (cd.indexOf("filename") >= 0) {
						fileparts.add(part);
						filenames.add(cd.substring(cd.lastIndexOf("=") + 1).replaceAll("\\\"", ""));
					}
				}
				((Uploadable) action).setParts(fileparts.toArray(new Part[fileparts.size()]));
				((Uploadable) action).setFilenames(filenames.toArray(new String[filenames.size()]));
			}
			ActionResult actionResult = action.execute(req, resp);
			if (actionResult != null) {
				ResultContent resultContent = actionResult.getResultContent();
				ResultType resultType = actionResult.getResultType();
				
				switch (resultType) {
				case Redirect:
					resp.sendRedirect(contextPath + resultContent.getUrl());
					break;
					
				case Forward:
					req.getRequestDispatcher(getFullJspPath(servletPath) + resultContent.getUrl()).forward(req, resp);
					break;
					
				case Ajax:
					PrintWriter pw = resp.getWriter();
					pw.println(resultContent.getJsonData());
					pw.close();
					break;
					
				case Chain:
					req.getRequestDispatcher(contextPath + resultContent.getUrl()).forward(req, resp);
					break;
					
				case RedirectChain:
					resp.sendRedirect(contextPath + resultContent.getUrl());
					break;
					
				default:
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("error.html");
		}
	}
	
	private String getFullActionName(String servletPath) {
		int start = servletPath.lastIndexOf("/") + 1;
		int end = servletPath.lastIndexOf(".do");
		
		return packagePrefix + getSubPackage(servletPath) + CommonUtil.capitalize(servletPath.substring(start, end)) + actionSuffix;
	}

	private String getFullJspPath(String servletPath) {
		
		return jspPrefix + getSubJspPath(servletPath);
	}

	private String getSubPackage(String servletPath) {
		
		return getSubJspPath(servletPath).replaceAll("\\/", ".");
	}

	private String getSubJspPath(String servletPath) {
		int start = 1;
		int end = servletPath.lastIndexOf("/");
		
		return end > start ? servletPath.substring(start, end > 0 ? end + 1 : 0) : "";
	} 
		 
	private void injectProperties(Action action, HttpServletRequest req) throws Exception {
		Enumeration<String> paramNamesEnum = req.getParameterNames();
		while (paramNamesEnum.hasMoreElements()) {
			String paramName = paramNamesEnum.nextElement();
			Class<?> fieldType = ReflectionUtil.getFieldType(action, paramName.replaceAll("\\[|\\]", ""));
			if (fieldType != null) {
				Object paramValue = null;
				if (fieldType.isArray()) {
					Class<?> elemType = fieldType.getComponentType();
					String[] values = req.getParameterValues(paramName);
					paramValue = Array.newInstance(elemType, values.length);
					for (int i = 0; i < values.length; i++) {
						Object tempObj = CommonUtil.changeStringToObject(elemType, values[i]);
						Array.set(paramValue, i, tempObj);
					}
				} else {
					paramValue = CommonUtil.changeStringToObject(fieldType, req.getParameter(paramName));
				}
				ReflectionUtil.setValue(action, paramName.replaceAll("\\[|\\]", ""), paramValue);
			}
		}
	}

}
