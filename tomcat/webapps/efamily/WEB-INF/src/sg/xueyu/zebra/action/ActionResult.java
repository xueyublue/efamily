package sg.xueyu.zebra.action;

public class ActionResult {

	private ResultContent resultContent;
	private ResultType resultType;

	public ActionResult(ResultContent resultContent) {
		this(resultContent, ResultType.Forward);
	}

	public ActionResult(ResultContent resultContent, ResultType type) {
		this.resultContent = resultContent;
		this.resultType = type;
	}

	public ResultContent getResultContent() {
		return resultContent;
	}

	public ResultType getResultType() {
		return resultType;
	}

}
