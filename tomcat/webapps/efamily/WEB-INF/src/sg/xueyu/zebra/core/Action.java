package sg.xueyu.zebra.core;

import java.lang.reflect.Method;

import sg.xueyu.zebra.annotation.Method.RequestMethod;

public class Action {

	private Class<?> mActionClass = null;

	private String mPath = null;

	private RequestMethod mRequestMethod = null;

	private Method mMethod = null;

	public Action() {
	}

	public Class<?> getActionClass() {
		return mActionClass;
	}

	public void setActionClass(Class<?> actionClass) {
		this.mActionClass = actionClass;
	}

	public String getPath() {
		return mPath;
	}

	public void setPath(String path) {
		this.mPath = path;
	}

	public RequestMethod getRequestMethod() {
		return mRequestMethod;
	}

	public void setRequestMethod(RequestMethod requestMethod) {
		this.mRequestMethod = requestMethod;
	}

	public Method getMethod() {
		return mMethod;
	}

	public void setMethod(Method method) {
		this.mMethod = method;
	}

}
