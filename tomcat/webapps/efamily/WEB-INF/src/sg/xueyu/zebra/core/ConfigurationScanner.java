package sg.xueyu.zebra.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sg.xueyu.zebra.annotation.Configuration;
import sg.xueyu.zebra.annotation.config.PackageScan;
import sg.xueyu.zebra.annotation.config.URLPatternSuffix;
import sg.xueyu.zebra.annotation.config.ViewPrefix;
import sg.xueyu.zebra.annotation.config.ViewSuffix;

public class ConfigurationScanner {

/** Constructor **/
	public ConfigurationScanner() {
	}

/** Get configurations **/
	public ConfigurationContainer scan() throws ClassNotFoundException, FileNotFoundException, IOException {
		List<Class<?>> configClassList = scanConfigClass();

		ConfigurationContainer container = new ConfigurationContainer();

		for (Class<?> configClass : configClassList) {
			// Class is annotated by PackageScan.class
			if (configClass.isAnnotationPresent(PackageScan.class)) {
				String[] packageScanArr = configClass.getAnnotation(PackageScan.class).value();
				container.getPackageScanList().addAll(Arrays.asList(packageScanArr));
			}
			// Class is annotated by ViewPrefix.class
			if (configClass.isAnnotationPresent(ViewPrefix.class)) {
				container.setViewPrefix(configClass.getAnnotation(ViewPrefix.class).value());
			}
			// Class is annotated by ViewSuffix.class
			if (configClass.isAnnotationPresent(ViewSuffix.class)) {
				container.setViewSuffix(configClass.getAnnotation(ViewSuffix.class).value());
			}
			// Class is annotated by URLPatternSuffix.class
			if (configClass.isAnnotationPresent(URLPatternSuffix.class)) {
				container.setURLPatternSuffix(configClass.getAnnotation(URLPatternSuffix.class).value());
			}
		}

		return container;
	}

/** Private Methods **/
	// Scan configuration class
	private List<Class<?>> scanConfigClass() throws ClassNotFoundException, FileNotFoundException, IOException {
		PackageScanner packageScanner = new PackageScanner("*");

		List<String> classNameList = packageScanner.getFullyQualifiedClassNameList();

		List<Class<?>> configClassList = new ArrayList<>();

		for (String name : classNameList) {
			Class<?> cla = Class.forName(name);
			// If class is annotated by Configuration.class
			if (cla.isAnnotationPresent(Configuration.class)) {
				configClassList.add(cla);
			}
		}

		return configClassList;
	}
}
