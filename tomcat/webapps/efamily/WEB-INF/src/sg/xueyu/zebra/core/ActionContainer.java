package sg.xueyu.zebra.core;

import java.util.ArrayList;
import java.util.List;

import sg.xueyu.zebra.annotation.Method.RequestMethod;

public class ActionContainer {

	private List<Action> mActionList = null;

	public ActionContainer(List<Action> actionList) {
		this.mActionList = actionList;
	}

	// Get all actions
	public List<Action> getAllActions() {
		return mActionList;
	}

	// Find action class for path and request method
	public Action find(String path, RequestMethod requestMethod) {
		for (Action action : mActionList) {

			if (action.getPath().equals(path) && action.getRequestMethod().equals(requestMethod)) {
				return action;
			}
		}

		return null;
	}
	
	// Find action class for path and request method
	public List<Action> findAll(String path, RequestMethod requestMethod) {
		List<Action> actionList = new ArrayList<>();
		
		for (Action action : mActionList) {

			if (action.getPath().equals(path) && action.getRequestMethod().equals(requestMethod)) {
				actionList.add(action);
			}
		}

		return actionList;
	}
	
	// Check if can handle request path and request method
	public boolean check(String path, RequestMethod requestMethod) {
		for (Action action : mActionList) {

			if (action.getPath().equals(path) && action.getRequestMethod().equals(requestMethod)) {
				return true;
			}
		}

		return false;
	}
}
