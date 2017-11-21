package sg.xueyu.zebra.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import sg.xueyu.zebra.annotation.Method.RequestMethod;
import sg.xueyu.zebra.annotation.Path;

public class ActionScanner {

	private List<String> mClassNameList = null;

/** Constructor **/
	public ActionScanner(List<String> nameList) {
		this.mClassNameList = nameList;
	}

/** Get all action classes **/
	public ActionContainer scan() throws ClassNotFoundException {
		List<Class<?>> actionClassList = getActionClassList();
		
		List<Action> actionList = new ArrayList<>();
		
		for (Class<?> actionClass : actionClassList) {
			// Class is annotated by Path.class
			if (actionClass.isAnnotationPresent(Path.class)) {
				actionList.addAll(processAnnotatedClass(actionClass));
			} 
			// Class is not annotated by Path.class
			// Then check all declared methods
			else {
				actionList.addAll(processNonAnnotatedClass(actionClass));
			}
		}
		
		return new ActionContainer(actionList);
	}
	
	// Get all action class names
	public List<String> getActionClassNameList() throws ClassNotFoundException {
		return scanActionClassName();
	}
	
	// Get all action classes
	public List<Class<?>> getActionClassList() throws ClassNotFoundException {
		return scanActionClass();
	}

/** Private Methods **/
	// Scan action class names
	private List<String> scanActionClassName() throws ClassNotFoundException {
		List<String> actionClassNameList = new ArrayList<>();

		if (mClassNameList == null || mClassNameList.isEmpty()) {
			actionClassNameList.clear();
			return actionClassNameList;
		}

		for (String name : mClassNameList) {
			Class<?> cla = Class.forName(name);
			// If class is annotated by Path.class
			if (cla.isAnnotationPresent(Path.class)) {
				actionClassNameList.add(name);
			} else {
				Method[] methods = cla.getDeclaredMethods();
				// If any method of the class is annotated by Path.class
				for (Method method : methods) {
					if (method.isAnnotationPresent(Path.class)) {
						actionClassNameList.add(name);
					}
				}
			}
		}

		return actionClassNameList;
	}

	// Scan action classes
	private List<Class<?>> scanActionClass() throws ClassNotFoundException {
		List<Class<?>> actionClassList = new ArrayList<>();

		if (mClassNameList == null || mClassNameList.isEmpty()) {
			actionClassList.clear();
			return actionClassList;
		}

		for (String name : mClassNameList) {
			Class<?> cla = Class.forName(name);
			// If class is annotated by Path.class
			if (cla.isAnnotationPresent(Path.class)) {
				actionClassList.add(cla);
			} else {
				Method[] methods = cla.getDeclaredMethods();
				// If any method of the class is annotated by Path.class
				for (Method method : methods) {
					if (method.isAnnotationPresent(Path.class)) {
						actionClassList.add(cla);
					}
				}
			}
		}

		return actionClassList;
	}
	
	// Process annotated class
	private List<Action> processAnnotatedClass(Class<?> actionClass) {
		List<Action> actionList = new ArrayList<>();
		
		String rootPath = actionClass.getAnnotation(Path.class).value();
		
		// Check method
		Method[] methods = actionClass.getDeclaredMethods();
		
		// No method is exist in action class
		if (methods.length == 0) {
			return actionList;
		} 
		// Only 1 method is found in action class
		// Then use it to process rootPath.GET request if there is no annotations exist
		else if (methods.length == 1){
			Method method = methods[0];
			
			if (method.getAnnotations().length == 0) {
				Action action = new Action();
				action.setPath(rootPath);
				action.setRequestMethod(RequestMethod.GET);
				action.setActionClass(actionClass);
				action.setMethod(methods[0]);
				actionList.add(action);
			} else {
				String path = null;
				if (method.isAnnotationPresent(Path.class)) {
					path = method.getAnnotation(Path.class).value();
					path = rootPath + path;
				} else {
					path = rootPath;
				}
				RequestMethod requestMethod = null;
				if (method.isAnnotationPresent(sg.xueyu.zebra.annotation.Method.class)) {
					requestMethod = method.getAnnotation(sg.xueyu.zebra.annotation.Method.class).value();
				} else {
					requestMethod = RequestMethod.GET;
				}
				Action action = new Action();
				action.setPath(path);
				action.setRequestMethod(requestMethod);
				action.setActionClass(actionClass);
				action.setMethod(methods[0]);
				actionList.add(action);
			}
		}
		// More than 1 methods are found in action class
		else {
			for (Method method : methods) {
				if (method.getAnnotations().length == 0) {
					continue;
				}
				String path = null;
				if(method.isAnnotationPresent(Path.class)) {
					path = method.getAnnotation(Path.class).value();
					path = rootPath + path;
				} else {
					path = rootPath;
				}
				RequestMethod requestMethod = null;
				if (method.isAnnotationPresent(sg.xueyu.zebra.annotation.Method.class)) {
					requestMethod = method.getAnnotation(sg.xueyu.zebra.annotation.Method.class).value();
				} else {
					requestMethod = RequestMethod.GET;
				}
				Action action = new Action();
				action.setPath(path);
				action.setRequestMethod(requestMethod);
				action.setActionClass(actionClass);
				action.setMethod(method);
				actionList.add(action);
			}
		}
		
		return actionList;
	}
	
	// process non-annotated class
	private List<Action> processNonAnnotatedClass(Class<?> actionClass) {
		List<Action> actionList = new ArrayList<>();
		
		Method[] methods = actionClass.getDeclaredMethods();
		
		// No method is exist in action class
		if (methods.length == 0) {
			return actionList;
		} 
		// Only 1 method is found in action class
		// Then use it to process rootPath.GET request if there is no annotations exist
		else if (methods.length == 1){
			Method method = methods[0];
			
			if (method.getAnnotations().length == 0) {
				return actionList;
			} else {
				String path = null;
				if (method.isAnnotationPresent(Path.class)) {
					path = method.getAnnotation(Path.class).value();
				} else {
					return actionList;
				}
				RequestMethod requestMethod = null;
				if (method.isAnnotationPresent(sg.xueyu.zebra.annotation.Method.class)) {
					requestMethod = method.getAnnotation(sg.xueyu.zebra.annotation.Method.class).value();
				} else {
					requestMethod = RequestMethod.GET;
				}
				Action action = new Action();
				action.setPath(path);
				action.setRequestMethod(requestMethod);
				action.setActionClass(actionClass);
				action.setMethod(methods[0]);
				actionList.add(action);
			}
		}
		// More than 1 methods are found in action class
		else {
			for (Method method : methods) {
				if (method.getAnnotations().length == 0) {
					continue;
				}
				String path = null;
				if(method.isAnnotationPresent(Path.class)) {
					path = method.getAnnotation(Path.class).value();
				} else {
					continue;
				}
				RequestMethod requestMethod = null;
				if (method.isAnnotationPresent(sg.xueyu.zebra.annotation.Method.class)) {
					requestMethod = method.getAnnotation(sg.xueyu.zebra.annotation.Method.class).value();
				} else {
					requestMethod = RequestMethod.GET;
				}
				Action action = new Action();
				action.setPath(path);
				action.setRequestMethod(requestMethod);
				action.setActionClass(actionClass);
				action.setMethod(method);
				actionList.add(action);
			}
		}
		
		return actionList;
	}
}
