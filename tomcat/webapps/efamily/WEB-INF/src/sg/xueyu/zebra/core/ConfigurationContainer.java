package sg.xueyu.zebra.core;

import java.util.List;

public class ConfigurationContainer {

	private List<String> mPackageScanList = null;

	private String mViewPrefix = null;

	private String mViewSuffix = null;

	public ConfigurationContainer() {
	}

	public ConfigurationContainer(List<String> packageScanList, String viewPrefix, String viewSuffix) {
		super();
		this.mPackageScanList = packageScanList;
		this.mViewPrefix = viewPrefix;
		this.mViewSuffix = viewSuffix;
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

}
