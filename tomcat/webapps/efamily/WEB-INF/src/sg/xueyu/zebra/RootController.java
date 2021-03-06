package sg.xueyu.zebra;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultContent;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.core.ActionContainer;
import sg.xueyu.zebra.core.ActionExecutor;
import sg.xueyu.zebra.core.ActionScanner;
import sg.xueyu.zebra.core.ConfigurationContainer;
import sg.xueyu.zebra.core.ConfigurationScanner;
import sg.xueyu.zebra.core.PackageScanner;

@MultipartConfig
public class RootController extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(RootController.class.getSimpleName());
	
	private static final long serialVersionUID = 1L;

/** Static Variables **/
	private static final String DEFAULT_PACKAGE_SCAN = "/";
	private static final String DEFAULT_VIEW_PREFIX = "/WEB-INF/view/";
	private static final String DEFAULT_VIEW_SUFFIX = ".jsp";
	private static final String DEFAULT_URL_PATTERN_SUFFIX = ".do";

/** Variables **/
	private List<String> mPackageScanList = new ArrayList<>();
	private String mViewPrefix = null;
	private String mViewSuffix = null;
	private String mURLPatternSuffix = null;
	
	private ActionContainer mActionContainer = null;

/** Override Methods **/
	@Override
	public void init(ServletConfig config) throws ServletException {

		// Scan Configurations
		ConfigurationScanner configScanner = new ConfigurationScanner();
		ConfigurationContainer configContainer = null;
		try {
			configContainer = configScanner.scan();
		} catch (Exception e1) {
			e1.printStackTrace();
			configContainer = null;
		}
		
		// Decide package scan list
		String packageScan = config.getInitParameter("packageScan");
		if (packageScan != null) {
			mPackageScanList.add(packageScan);
		}
		if (configContainer != null
				&& !configContainer.getPackageScanList().isEmpty()) {
			mPackageScanList.addAll(configContainer.getPackageScanList());
		}
		if (mPackageScanList.isEmpty()) {
			mPackageScanList.add(DEFAULT_PACKAGE_SCAN);
		}
		fixPackageScanList(mPackageScanList);

		// Decide view prefix
		String viewPrefix = config.getInitParameter("viewPrefix");
		if (viewPrefix != null) {
			mViewPrefix = viewPrefix;
		}
		else if (configContainer != null
				&& configContainer.getViewPrefix() != null) {
			mViewPrefix = configContainer.getViewPrefix();
		}
		else {
			mViewPrefix = DEFAULT_VIEW_PREFIX;
		}
		LOGGER.info("View prefix: " + mViewPrefix);
		
		// Decide view suffix
		String viewSuffix = config.getInitParameter("viewSuffix");
		if (viewSuffix != null) {
			mViewSuffix = viewSuffix;
		}
		else if (configContainer != null
				&& configContainer.getViewSuffix() != null) {
			mViewSuffix = configContainer.getViewSuffix();
		}
		else {
			mViewSuffix = DEFAULT_VIEW_SUFFIX;
		}
		LOGGER.info("View suffix: " + mViewSuffix);
		
		// Decide URL pattern suffix
		if (configContainer != null
				&& configContainer.getViewSuffix() != null) {
			mURLPatternSuffix = configContainer.getURLPatternSuffix();
		}
		else {
			mURLPatternSuffix = DEFAULT_URL_PATTERN_SUFFIX;
		}
		LOGGER.info("URL pattern suffix: " + mURLPatternSuffix);
		
		// Scan Package
		List<String> nameList = new ArrayList<>();
		for (String pack : mPackageScanList) {
			LOGGER.info("Scanning package: " + pack + "...");
			PackageScanner packageScanner = new PackageScanner(pack);
			try {
				nameList.addAll(packageScanner.getFullyQualifiedClassNameList());
			} catch (Exception e) {
				e.printStackTrace();
				nameList = null;
				return;
			}
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
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contextPath = req.getContextPath() + "/";
		String servletPath = req.getServletPath();

		// Create action executor
		ActionExecutor actionExecutor = new ActionExecutor(req, resp, mActionContainer, mURLPatternSuffix);
		
		// Get request path
		// Remove suffix of request URL
		String requestPath = actionExecutor.getRequestPath(servletPath, mURLPatternSuffix);
		
		try {
			// Execute action
			ActionResult actionResult = actionExecutor.execute();

			// Handle action result
			if (actionResult != null) {
				ResultContent resultContent = actionResult.getResultContent();
				ResultType resultType = actionResult.getResultType();

				switch (resultType) {
				case Redirect:
					resp.sendRedirect(contextPath + resultContent.getUrl());
					break;

				case Forward:
					req.getRequestDispatcher(getFullViewPath(requestPath) + resultContent.getUrl()).forward(req, resp);
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

	// Add "." to the package name
	// Remove duplicate packages
	// Remove the packages that contains, e.g. do not add "sg.daifuku.wms" if "sg.daifuku" already in list
	// TODO: Add the rooter package to scan list
	private void fixPackageScanList(List<String> list) {
		List<String> newScanList = new ArrayList<>();
		
		for (String pack : mPackageScanList) {
			if (!pack.endsWith(".")) {
				pack += ".";
				if (newScanList.size() == 0) {
					newScanList.add(pack);
				}
				else if (newScanList.contains(pack)) {
					continue;
				}
				else if (!newScanList.contains(pack)) {
					for (String string : newScanList) {
						if (string.contains(pack)
								|| pack.contains(string)) {
							// TODO: Compare which is rooter
							continue;			
						}
						newScanList.add(pack);
					}
				}
			}
		}
		mPackageScanList = newScanList;
	}
}
