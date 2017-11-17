package sg.xueyu.zebra.core;

import java.util.List;

public class ActionExecutor {

	private List<Action> mActionList = null;

	public ActionExecutor(List<Action> actions) {
		this.mActionList = actions;
	}
	
	public boolean execute() {

		return false;
	}

}
