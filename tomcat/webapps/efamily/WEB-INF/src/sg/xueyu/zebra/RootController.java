package sg.xueyu.zebra;

import java.io.FileNotFoundException;
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
import sg.xueyu.zebra.util.ZebraUtil;
import sg.xueyu.zebra.util.PackageScanner;
import sg.xueyu.zebra.util.ReflectionUtil;

@MultipartConfig
public class RootController extends HttpServlet {

	private static final long serialVersionUID = 1L;

/** Static Variables **/	
	private static final String DEFAULT_PACKAGE_SCAN = "/";
	private static final String DEFAULT_PAGE_SUFFIX = "Page";
	private static final String DEFAULT_ACTION_SUFFIX = "Action";
	private static final String DEFAULT_VIEW_PREFIX = "/WEB-INF/view/";
	
/** Variables **/	
	private String mPackageScan = null;
	private String mPageSuffix = null;
	private String mActionSuffix = null;
	private String mViewPrefix = null;

	private List<String> mQualifiedClassNameList = null;
	
/** Override Methods **/
	@Override
	public void init(ServletConfig config) throws ServletException {
		String packageScan = config.getInitParameter("packageScan");
		mPackageScan = packageScan != null ? packageScan : DEFAULT_PACKAGE_SCAN;
		if (!mPackageScan.endsWith(".")) {
			mPackageScan += ".";
		}
		
		// Scan Package
		try {
			PackageScanner scanner = new PackageScanner(mPackageScan);
			mQualifiedClassNameList = scanner.getFullyQualifiedClassNameList();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			mQualifiedClassNameList = null;
		} catch (IOException e) {
			e.printStackTrace();
			mQualifiedClassNameList = null;
		}
		
		String pageSuffix = config.getInitParameter("pageSuffix");
		mPageSuffix = pageSuffix != null ? pageSuffix : DEFAULT_PAGE_SUFFIX;
		
		String actionSuffix = config.getInitParameter("actionSuffix");
		mActionSuffix = actionSuffix != null ? actionSuffix : DEFAULT_ACTION_SUFFIX;
		
		String viewPrefix = config.getInitParameter("viewPrefix");
		mViewPrefix = viewPrefix != null ? viewPrefix : DEFAULT_VIEW_PREFIX;
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath() + "/";
		String servletPath = req.getServletPath();

		try {
			// Get Action class
			Action action = (Action) Class.forName(getFullActionName(servletPath)).newInstance();
			
			// Inject request data to Action class
			injectProperties(action, req);
			
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
			
			// Execute Action
			ActionResult actionResult = action.execute(req, resp);
			
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
	// Get package path of Action
	private String getFullActionName(String servletPath) {
		int start = servletPath.lastIndexOf("/") + 1;
		int endDo = servletPath.lastIndexOf(".do");
		int endPage = servletPath.lastIndexOf(".page");
		
		if (endDo != -1) {
			return mPackageScan + getSubPackage(servletPath) + ZebraUtil.capitalize(servletPath.substring(start, endDo)) + mActionSuffix;	
		}
		else if (endPage != -1) {
			return mPackageScan + getSubPackage(servletPath) + ZebraUtil.capitalize(servletPath.substring(start, endPage)) + mPageSuffix;	
		} 
		else {
			return null;
		}
	}

	// Get JSP file path
	private String getFullViewPath(String servletPath) {
		
		return mViewPrefix + getSubViewPath(servletPath);
	}

	// Convert SERVLET path to sub package
	private String getSubPackage(String servletPath) {
		
		return getSubViewPath(servletPath).replaceAll("\\/", ".");
	}

	// Get sub JSP file path
	private String getSubViewPath(String servletPath) {
		int start = 1;
		int end = servletPath.lastIndexOf("/");
		
		return end > start ? servletPath.substring(start, end > 0 ? end + 1 : 0) : "";
	} 
	
	// Inject request data to Action class
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
						Object tempObj = ZebraUtil.changeStringToObject(elemType, values[i]);
						Array.set(paramValue, i, tempObj);
					}
				} else {
					paramValue = ZebraUtil.changeStringToObject(fieldType, req.getParameter(paramName));
				}
				ReflectionUtil.setValue(action, paramName.replaceAll("\\[|\\]", ""), paramValue);
			}
		}
	}

}
