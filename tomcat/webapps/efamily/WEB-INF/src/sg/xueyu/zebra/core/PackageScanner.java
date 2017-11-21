package sg.xueyu.zebra.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import sg.xueyu.zebra.util.StringUtils;

public class PackageScanner {

	private String mBasePackage;

	private ClassLoader mClassLoader;

/** Constructors **/	
	// Used for scan directory
	public PackageScanner(String basePackage) {
		this.mBasePackage = basePackage;
		this.mClassLoader = getClass().getClassLoader();
	}

	// Used for scan Jar files
	public PackageScanner(String basePackage, ClassLoader classLoader) {
		this.mBasePackage = basePackage;
		this.mClassLoader = classLoader;
	}

/** Get all qualified class name list **/	
	public List<String> getFullyQualifiedClassNameList() throws FileNotFoundException, IOException {
		if (mBasePackage.endsWith(".")) {
			mBasePackage = mBasePackage.substring(0, mBasePackage.lastIndexOf('.'));
		}
		if (mBasePackage.startsWith(".")) {
			mBasePackage = mBasePackage.substring(1, mBasePackage.length());
		}
		return doScan(mBasePackage, new ArrayList<String>());
	}

/** Private Methods **/	
	private List<String> doScan(String basePackage, List<String> nameList) throws FileNotFoundException, IOException {
		String slashPath = StringUtils.dotToSlash(basePackage);

		URL url = null;
		if ("/".equals(slashPath) 
				|| "".equals(slashPath.trim())
				|| "*".equals(slashPath.trim())) {
			basePackage = "";
			url = mClassLoader.getResource("");
		} else {
			url = mClassLoader.getResource(slashPath);	
		}
		String filePath = StringUtils.getRootPath(url);

		List<String> names = new ArrayList<>();
		names.clear();
		
		// Read classes from JAR file
		if (isJarFile(filePath)) {
			names = readFromJar(filePath, slashPath);
		}
		// Read classes from directory
		else if (new File(filePath).isDirectory()){
			names = readFromDirectory(filePath);
		} 
		// Do nothing when file path is not a JAR and directory
		else {
			// DO NOTHING
		}

		// Add names to name list
		for (String name : names) {
			if (isClassFile(name)) {
				nameList.add(toFullyQualifiedName(name, basePackage));
			} else if (isDir(name)){
				if ("".equals(basePackage)) {
					doScan(name, nameList);	
				} else {
					doScan(basePackage + "." + name, nameList);
				}
			} else {
				// DO NOTHING
			}
		}

		return nameList;
	}

	// Get all classes from Jar file
	private List<String> readFromJar(String jarPath, String slashPackageName) throws FileNotFoundException, IOException {
		JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarPath));
		JarEntry entry = jarInputStream.getNextJarEntry();

		List<String> nameList = new ArrayList<String>();

		while (null != entry) {
			String name = entry.getName();
			if (name.startsWith(slashPackageName) && isClassFile(name)) {
				nameList.add(name);
			}

			entry = jarInputStream.getNextJarEntry();
		}

		return nameList;
	}

	// Get all classes from directory
	private List<String> readFromDirectory(String dirPath) {
		File file = new File(dirPath);
		String[] names = file.list();

		if (null == names) {
			return null;
		}

		return Arrays.asList(names);
	}

	// Convert to fully qualified name
	private String toFullyQualifiedName(String shortName, String basePackage) {
		StringBuilder sb = new StringBuilder(basePackage);
		sb.append(".");
		sb.append(StringUtils.trimExtension(shortName));

		return sb.toString();
	}

	// Check if file path is a class file
	private boolean isClassFile(String filePath) {
		return filePath.endsWith(".class");
	}

	// Check if file path is a JAR file
	private boolean isJarFile(String filePath) {
		return filePath.endsWith(".jar");
	}

	// Check if file path is a class file
	private boolean isDir(String filePath) {
		return filePath.contains(".") 
				&& filePath.indexOf('.') != 0 ? false : true;
	}
}
