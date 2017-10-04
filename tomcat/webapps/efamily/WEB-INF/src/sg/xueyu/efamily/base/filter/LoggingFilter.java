package sg.xueyu.efamily.base.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import sg.xueyu.efamily.system.EFamilyParam;
import sg.xueyu.efamily.system.HttpRequestLogger;

@WebFilter(value = "/*")
public class LoggingFilter implements Filter {

	@Override
	public void destroy() {
		//
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("request-uri: " + req.getRequestURI());
		if (EFamilyParam.ENABLE_LOGGING) {
			HttpRequestLogger.info("");
			HttpRequestLogger.info(">>> NEW HTTP REQUEST <<<");
			HttpRequestLogger.info("request-uri: " + req.getRequestURI());
			HttpRequestLogger.info("query-string: " + req.getQueryString());
			HttpRequestLogger.info("remote-addr: " + req.getRemoteAddr());
			Enumeration<String> reqHeaders = req.getHeaderNames();
			while (reqHeaders.hasMoreElements()) {
				String header = (String) reqHeaders.nextElement();
				HttpRequestLogger.info(header + ": " + req.getHeader(header));
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

}
