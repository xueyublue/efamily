package sg.xueyu.zebra.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
