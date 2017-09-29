package sg.xueyu.efamily.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(getInitParameter("app_name") + " is started.");
		
		PrintWriter out = resp.getWriter();
		out.write("<html>\r\n");
		out.write("<head>\r\n");
		out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
		out.write("</head>\r\n");
		out.write("\r\n");
		out.write("<body>\r\n");
		out.write("<H2>\r\n");
		out.write("Welcome to " + getInitParameter("app_name"));
		out.write("\r\n");
		out.write("</H2>\r\n");
		out.write("</body>\r\n");
		out.write("</html>");
	}

}
