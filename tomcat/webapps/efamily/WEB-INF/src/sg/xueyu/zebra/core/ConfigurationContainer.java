package sg.xueyu.zebra.core;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationContainer {

	private List<String> mPackageScanList = null;

	private String mViewPrefix = null;

	private String mViewSuffix = null;

	private String mURLPatternSuffix = null;

	public ConfigurationContainer() {
		mPackageScanList = new ArrayList<String>();
	}

	public List<String> getPackageScanList() {
		return mPackageScanList;
	}

	public void setPackageScanList(List<String> packageScanList) {
		this.mPackageScanList = packageScanList;
	}

	public String getViewPrefix() {
		return mViewPrefix;
	}

	public void setViewPrefix(String viewPrefix) {
		this.mViewPrefix = viewPrefix;
	}

	public String getViewSuffix() {
		return mViewSuffix;
	}

	public void setViewSuffix(String viewSuffix) {
		this.mViewSuffix = viewSuffix;
	}

	public String getURLPatternSuffix() {
		return mURLPatternSuffix;
	}

	public void setURLPatternSuffix(String URLPatternSuffix) {
		mURLPatternSuffix = URLPatternSuffix;
	}

}
