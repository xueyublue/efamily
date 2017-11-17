package sg.xueyu.zebra.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
		
		for (Class<?> actionClass : actionClassList) {
			// Class is annotated by Path.class
			if (actionClass.isAnnotationPresent(Path.class)) {
				//
			} else {
				Method[] methods = actionClass.getMethods();
				for (Method method : methods) {
					//
				}
			}
		}
		
		return null;
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
		if (mClassNameList == null || mClassNameList.isEmpty()) {
			return null;
		}

		List<String> actionClassNameList = new ArrayList<>();

		for (String name : mClassNameList) {
			Class<?> cla = Class.forName(name);
			// If class is annotated by Path.class
			if (cla.isAnnotationPresent(Path.class)) {
				actionClassNameList.add(name);
			} else {
				Method[] methods = cla.getMethods();
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
		if (mClassNameList == null || mClassNameList.isEmpty()) {
			return null;
		}

		List<Class<?>> actionClassList = new ArrayList<>();

		for (String name : mClassNameList) {
			Class<?> cla = Class.forName(name);
			// If class is annotated by Path.class
			if (cla.isAnnotationPresent(Path.class)) {
				actionClassList.add(cla);
			} else {
				Method[] methods = cla.getMethods();
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
	
}
