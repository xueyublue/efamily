package sg.xueyu.zebra.core;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sg.xueyu.zebra.util.ReflectionUtil;
import sg.xueyu.zebra.util.ZebraUtil;

public class DataBinding {

	private HttpServletRequest mRequest = null;

	private Enumeration<String> mRequestParaNames = null;

	/** Default Constructor **/
	public DataBinding(HttpServletRequest httpServletRequest) {
		this.mRequest = httpServletRequest;
		this.mRequestParaNames = mRequest.getParameterNames();
	}

	/** Protect Methods **/
	protected HttpServletRequest getServletRequest() {
		return mRequest;
	}

	/** Public Methods **/
	// Bind data to a method
	public Object[] bindToMethod(Method method) throws Exception {
		List<Object> valueList = new ArrayList<>();
		
		// LOOP request parameter names
		while (mRequestParaNames.hasMoreElements()) {
			Object valueInstance = new Object();
			
			// Get next parameter name
			String reqParaName = mRequestParaNames.nextElement();
			
			// Check is exist parameter name in method
			if (!isExistParameterForName(method, reqParaName)) {
				throw new Exception("Parameter Name: " + reqParaName + " is not exist in " + method.getName());
			}
			
			// Get Parameter from method
			Parameter parameter = getParameterForName(method, reqParaName);
			
			if (parameter != null) {
				Object paramValue = null;
				Class<?> paramType = parameter.getType();
				
				// Field type is array
				if (paramType.isArray()) {
					Class<?> elemType = paramType.getComponentType();
					String[] values = mRequest.getParameterValues(reqParaName);
					paramValue = Array.newInstance(elemType, values.length);
					for (int i = 0; i < values.length; i++) {
						Object tempObj = ZebraUtil.changeStringToObject(elemType, values[i]);
						Array.set(paramValue, i, tempObj);
					}
				}
				// Field type is not array
				else {
					paramValue = ZebraUtil.changeStringToObject(paramType, mRequest.getParameter(reqParaName));
				}
				
				// Set value to action instance object
				ReflectionUtil.setValue(valueInstance, reqParaName.replaceAll("\\[|\\]", ""), paramValue);
				
				valueList.add(valueInstance);
			}
		}

		return valueList.toArray();
	}

	// Bind data to a object
	public boolean bindToObject(Object object) {
		// LOOP parameter names
		while (mRequestParaNames.hasMoreElements()) {
			// Get next parameter name
			String paramName = mRequestParaNames.nextElement();
			// Get field data type for parameter name in action instance object
			Class<?> fieldType = ReflectionUtil.getFieldType(object, paramName.replaceAll("\\[|\\]", ""));
			if (fieldType != null) {
				Object paramValue = null;
				// Field type is array
				if (fieldType.isArray()) {
					Class<?> elemType = fieldType.getComponentType();
					String[] values = mRequest.getParameterValues(paramName);
					paramValue = Array.newInstance(elemType, values.length);
					for (int i = 0; i < values.length; i++) {
						Object tempObj = ZebraUtil.changeStringToObject(elemType, values[i]);
						Array.set(paramValue, i, tempObj);
					}
				}
				// Field type is not array
				else {
					paramValue = ZebraUtil.changeStringToObject(fieldType, mRequest.getParameter(paramName));
				}
				// Set value to action instance object
				ReflectionUtil.setValue(object, paramName.replaceAll("\\[|\\]", ""), paramValue);
			}
		}

		return true;
	}

	/** Private Methods **/
	// Check if exist parameter name for name
	private boolean isExistParameterForName(Method method, String paraName) {
		Parameter[] parameters = method.getParameters();

		if (parameters == null || parameters.length == 0) {
			return false;
		}

		for (Parameter parameter : parameters) {
			if (parameter.getName().equalsIgnoreCase(paraName)) {
				return true;
			}
		}

		return false;
	}
	
	// Check if exist parameter name for name
	private Parameter getParameterForName(Method method, String paraName) {
		Parameter[] parameters = method.getParameters();

		for (Parameter parameter : parameters) {
			if (parameter.getName().equalsIgnoreCase(paraName)) {
				return parameter;
			}
		}

		return null;
	}
}
