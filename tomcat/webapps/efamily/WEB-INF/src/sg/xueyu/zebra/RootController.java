package sg.xueyu.zebra;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.action.Uploadable;
import sg.xueyu.zebra.annotation.Method.RequestMethod;
import sg.xueyu.zebra.core.Action;
import sg.xueyu.zebra.core.ActionContainer;
import sg.xueyu.zebra.core.ActionScanner;
import sg.xueyu.zebra.core.PackageScanner;
import sg.xueyu.zebra.util.ReflectionUtil;
import sg.xueyu.zebra.util.ZebraUtil;

@MultipartConfig
public class RootController extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(RootController.class.getSimpleName());
	
	private static final long serialVersionUID = 1L;

/** Static Variables **/
	private static final String DEFAULT_PACKAGE_SCAN = "/";
	private static final String DEFAULT_VIEW_PREFIX = "/WEB-INF/view/";

/** Variables **/
	private String mPackageScan = null;
	private String mViewPrefix = null;
	
	private ActionContainer mActionContainer = null;

/** Override Methods **/
	@Override
	public void init(ServletConfig config) throws ServletException {
		String packageScan = config.getInitParameter("packageScan");
		mPackageScan = packageScan != null ? packageScan : DEFAULT_PACKAGE_SCAN;
		if (!mPackageScan.endsWith(".")) {
			mPackageScan += ".";
		}

		// Scan Package
		LOGGER.info("Scanning package: " + mPackageScan + "...");
		PackageScanner packageScanner = new PackageScanner(mPackageScan);
		List<String> nameList = null;
		try {
			nameList = packageScanner.getFullyQualifiedClassNameList();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			nameList = null;
			return;
		} catch (IOException e) {
			e.printStackTrace();
			nameList = null;
			return;
		}
		LOGGER.info(nameList.size() + " classes are found.");
		
		// Scan Actions
		LOGGER.info("Scanning actions...");
		ActionScanner actionScanner = new ActionScanner(nameList);
		try {
			mActionContainer = actionScanner.scan();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			mActionContainer = null;
			return;
		}
		LOGGER.info(mActionContainer.getAllActions().size() + " actions are found.");

		String viewPrefix = config.getInitParameter("viewPrefix");
		mViewPrefix = viewPrefix != null ? viewPrefix : DEFAULT_VIEW_PREFIX;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath() + "/";
		String servletPath = req.getServletPath();
		
		if (servletPath.endsWith(".do")) {
			servletPath = servletPath.substring(0, servletPath.indexOf(".do"));
		}
		
		try {
			RequestMethod requestMethod = getRequestMethod(req);
			Action action = mActionContainer.find(servletPath, requestMethod);
			
			if (action == null) {
				throw new Exception("No matched action class found under package: " + mPackageScan);
			}
			
			Class<?> actionClass = action.getActionClass();
			
			// Initialize action instance
			Object actionInstance = null;
			Constructor<?>[] constructor = actionClass.getConstructors();
			
			for (Constructor<?> cons : constructor) {
				Class<?>[] types = cons.getParameterTypes();
				if(types.length == 0) {
					actionInstance = cons.newInstance();	
				}
				else if (types.length == 2
						&& types[0].equals(HttpServletRequest.class)
						&& types[1].equals(HttpServletResponse.class)) {
					actionInstance = cons.newInstance(req, resp);
				} else {
					actionInstance = null;
				}
			}
			
			// Inject request data to Action instance
			injectProperties(actionInstance, req);

			Method actionMethod = action.getMethod();
			
			// Invoke action
			ActionResult actionResult = (ActionResult) actionMethod.invoke(actionInstance);
			
			// Handle file upload Action
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

			// Handle action result
			if (actionResult != null) {
				ResultContent resultContent = actionResult.getResultContent();
				ResultType resultType = actionResult.getResultType();

				switch (resultType) {
				case Redirect:
					resp.sendRedirect(contextPath + resultContent.getUrl());
					break;

				case Forward:
					req.getRequestDispatcher(getFullViewPath(servletPath) + resultContent.getUrl()).forward(req, resp);
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
	
/** Private Methods **/
	// Get request method
	private RequestMethod getRequestMethod(HttpServletRequest request) {
		String method = request.getMethod();
		
		if (RequestMethod.GET.name().equalsIgnoreCase(method)) {
			return RequestMethod.GET;
		}
		else if (RequestMethod.POST.name().equalsIgnoreCase(method)) {
			return RequestMethod.POST;
		} 
		else {
			return null;
		}
	}
	// Get JSP file path
	private String getFullViewPath(String servletPath) {

		return mViewPrefix + getSubViewPath(servletPath);
	}

	// Get sub JSP file path
	private String getSubViewPath(String servletPath) {
		int start = 1;
		int end = servletPath.lastIndexOf("/");

		return end > start ? servletPath.substring(start, end > 0 ? end + 1 : 0) : "";
	}

	// Inject request data to Action class
	private void injectProperties(Object actionInstance, HttpServletRequest req) throws Exception {
		Enumeration<String> paramNamesEnum = req.getParameterNames();
		while (paramNamesEnum.hasMoreElements()) {
			String paramName = paramNamesEnum.nextElement();
			Class<?> fieldType = ReflectionUtil.getFieldType(actionInstance, paramName.replaceAll("\\[|\\]", ""));
			if (fieldType != null) {
				Object paramValue = null;
				if (fieldType.isArray()) {
					Class<?> elemType = fieldType.getComponentType();
					String[] values = req.getParameterValues(paramName);
					paramValue = Array.newInstance(elemType, values.length);
					for (int i = 0; i < values.length; i++) {
						Object tempObj = ZebraUtil.changeStringToObject(elemType, values[i]);
						Array.set(paramValue, i, tempObj);
					}
				} else {
					paramValue = ZebraUtil.changeStringToObject(fieldType, req.getParameter(paramName));
				}
				ReflectionUtil.setValue(actionInstance, paramName.replaceAll("\\[|\\]", ""), paramValue);
			}
		}
	}

}
