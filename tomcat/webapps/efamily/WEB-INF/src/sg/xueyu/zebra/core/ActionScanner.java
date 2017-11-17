package sg.xueyu.zebra.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import sg.xueyu.zebra.annotation.Path;

public class ActionScanner {

	private List<String> mQualifiedClassNameList = null;

/** Constructor **/
	public ActionScanner(List<String> nameList) {
		this.mQualifiedClassNameList = nameList;
	}

/** Get all action classes **/
	public List<String> getActionClassList() throws ClassNotFoundException {
		return doScan();
	}

/** Private Methods **/
	private List<String> doScan() throws ClassNotFoundException {
		if (mQualifiedClassNameList == null || mQualifiedClassNameList.isEmpty()) {
			return null;
		}

		List<String> actionList = new ArrayList<>();

		for (String name : mQualifiedClassNameList) {
			Class<?> cla = Class.forName(name);
			// If class is annotated by Path.class
			if (cla.isAnnotationPresent(Path.class)) {
				actionList.add(name);
			} else {
				Method[] methods = cla.getMethods();
				// If any method of the class is annotated by Path.class
				for (Method method : methods) {
					if (method.isAnnotationPresent(Path.class)) {
						actionList.add(name);
					}
				}
			}
		}

		return actionList;
	}

}
