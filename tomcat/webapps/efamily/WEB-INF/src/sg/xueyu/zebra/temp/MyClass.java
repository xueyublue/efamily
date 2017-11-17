package sg.xueyu.zebra.temp;

import sg.xueyu.zebra.annotation.Method;
import sg.xueyu.zebra.annotation.Path;
import sg.xueyu.zebra.annotation.Method.RequestMethod;

//@Path("/user")
public class MyClass {

	@Path("/page")
	@Method(RequestMethod.GET)
	public String page() {

		return "I am a method to return page path.";
	}

	@Method(RequestMethod.GET)
	public String page2() {

		return "I am a method to return page2 path.";
	}
}
