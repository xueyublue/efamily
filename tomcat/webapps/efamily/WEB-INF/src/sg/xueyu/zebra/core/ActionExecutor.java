package sg.xueyu.zebra.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.annotation.Method.RequestMethod;

public class ActionExecutor {
	private HttpServletRequest mServletRequest = null;

	private HttpServletResponse mServletResponse = null;

	private ActionContainer mActionContainer = null;

	/** Default Constructor **/
	public ActionExecutor(HttpServletRequest request, HttpServletResponse response, ActionContainer actionContainer) {
		this.mServletRequest = request;
		this.mServletResponse = response;
		this.mActionContainer = actionContainer;
	}

	/** Protected Methods **/

	/** Public Methods **/
	public ActionResult execute(String requestPath, RequestMethod requestMethod) throws Exception {
		// Find action that matched request URL and HTTP request method
		Action action = mActionContainer.find(requestPath, requestMethod);
		if (action == null) {
			throw new Exception("No matched action found!");
		}

		// Get action class from Action object
		Class<?> actionClass = action.getActionClass();
		if (actionClass == null) {
			throw new Exception("Matched action Class<?> is null!");
		}

		// Initialize action class instance
		Object actionInstance = null;
		Constructor<?>[] constructor = actionClass.getConstructors();

		for (Constructor<?> cons : constructor) {
			Class<?>[] types = cons.getParameterTypes();
			// If constructor do not have any parameters inside
			// Then will be using the default constructor to initialize action
			// class
			if (types.length == 0) {
				actionInstance = cons.newInstance();
			}
			// If constructor have 2 parameters inside and parameter type is
			// HttpServletRequest/HttpServletResponse
			// Then will be using it to initialize action class
			else if (types.length == 2 && types[0].equals(HttpServletRequest.class)
					&& types[1].equals(HttpServletResponse.class)) {
				actionInstance = cons.newInstance(mServletRequest, mServletResponse);
			}
			// If others then set action instance to null
			else {
				actionInstance = null;
			}
		}

		Method actionMethod = action.getMethod();

		// Inject request data to Action method
		DataBinding dataBinding = new DataBinding(mServletRequest);
		Object[] values = dataBinding.bindToMethod(actionMethod);

		// Invoke action method
		return (ActionResult) actionMethod.invoke(actionInstance, values);
	}

	/** Private Methods **/
}
