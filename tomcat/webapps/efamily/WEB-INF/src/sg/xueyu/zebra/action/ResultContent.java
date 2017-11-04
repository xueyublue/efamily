package sg.xueyu.zebra.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResultContent {

	private String url;
	private Object data;

	private String dateFormat;

	public ResultContent() {
	}

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

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getJsonData() {
		Gson gson = null;
		if (dateFormat == null) {
			gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		} else {
			gson = new GsonBuilder().setDateFormat(dateFormat).create();
		}
		return gson.toJson(data);
	}
}
