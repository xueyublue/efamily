package sg.xueyu.zebra.action;

import com.google.gson.Gson;

public class ResultContent {

	private String url;
	private Object data;

	public ResultContent(String url, Object data) {
		this.url = url;
		this.data = data;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getJsonData() {
		return new Gson().toJson(data);
	}
}
