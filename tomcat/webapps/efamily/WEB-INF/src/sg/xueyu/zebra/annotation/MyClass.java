package sg.xueyu.zebra.annotation;

import sg.xueyu.zebra.annotation.Method.RequestMethod;

@Path("user")
public class MyClass {

	@Path("/page")
	@Method(RequestMethod.GET)
	public String page() {

		return "I am a method to return page path.";
	}

}
