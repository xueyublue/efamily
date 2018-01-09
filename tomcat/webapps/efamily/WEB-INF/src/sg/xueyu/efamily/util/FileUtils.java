package sg.xueyu.efamily.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import sg.xueyu.efamily.system.CommonMethod;

public class FileUtils {

	// Check if directory is exist
	public static boolean isDirExist(String dirPath) {
		if (new File(dirPath).exists()) {
			return true;
		}
		
		return false;
	}

	// Check if file is exist
	public static boolean isFileExist(String file) {
		if (new File(file).exists()) {
			return true;
		}
		
		return false;
	}

	// Check if file is exist
	public static boolean isFileExist(String filePath, String fileName) {
		if (!new File(filePath).exists()) {
			return false;
		} else if (new File(filePath + File.separator + fileName).exists()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean createDir(String dirPath, boolean overWrite) {
		if (!isDirExist(dirPath)) {
			return new File(dirPath).mkdirs();
		} else if (overWrite) {
			if (deleteDir(dirPath)) {
				return createDir(dirPath, false);
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public static boolean createFile(String file, boolean overWrite) throws IOException {
		if (!isFileExist(file)) {
			return new File(file).createNewFile();
		} else if (overWrite) {
			if (deleteFile(file)) {
				return new File(file).createNewFile();
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public static void createFile(String filePath, String fileName, boolean overWrite) throws IOException {
		if (isDirExist(filePath) && !isFileExist(filePath, fileName)) {
			new File(filePath + File.separator + fileName).createNewFile();
		}
	}

	public static boolean copyFile(String srcFileName, String destFileName, boolean overlay) {
		// is source file exist?
		// is source file is a file?
		File srcFile = new File(srcFileName);
		if (!srcFile.exists() || !srcFile.isFile()) {
			return false;
		}
		// is destination file exist?
		File destFile = new File(destFileName);
		if (destFile.exists()) {
			// delete destination file if overlay is true
			if (overlay) {
				new File(destFileName).delete();
			}
		} // create directory if destination directory is not exist
		else if (!destFile.getParentFile().exists()) {
			if (!destFile.getParentFile().mkdirs()) {
				return false;
			}
		}
		// copy file
		int byteread = 0;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];

			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean copyDir(String srcDirName, String destDirName, boolean overlay) {
		// is source directory exist?
		File srcDir = new File(srcDirName);
		if (!srcDir.exists() || !srcDir.isDirectory()) {
			return false;
		}
		// add separator if destination directory name is not end with /
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		File destDir = new File(destDirName);
		// delete destination directory if overlay is true
		if (destDir.exists()) {
			if (overlay) {
				new File(destDirName).delete();
			} else {
				return false;
			}
		}
		// create dest directory if not exist
		else if (!destDir.mkdirs()) {
			return false;
		}
		// copy directory
		boolean flag = true;
		File[] files = srcDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			// copy file
			if (files[i].isFile()) {
				flag = copyFile(files[i].getAbsolutePath(), destDirName + files[i].getName(), overlay);
				if (!flag) {
					break;
				}
			} else if (files[i].isDirectory()) {
				flag = copyDir(files[i].getAbsolutePath(), destDirName + files[i].getName(), overlay);
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {
			// copy file failed!
			return false;
		} else {
			return true;
		}
	}

	public static boolean deleteEmptyDir(String dir) {
		File tempDir = new File(dir.trim());

		if (!tempDir.exists()) {
			return false;
		} else {
			return tempDir.delete();
		}
	}

	public static boolean deleteDir(String dirPath) {
		if (!isDirExist(dirPath)) {
			return false;
		}
		File dir = new File(dirPath);
		if (!dir.exists()) {
			return false;
		} else {
			if (dir.isDirectory()) {
				String[] children = dir.list();
				// delete all sub directories
				for (int i = 0; i < children.length; i++) {
					boolean success = deleteDir(new File(dir, children[i]).getPath());
					if (!success) {
						return false;
					}
				}
			}
			// now the directory is empty
			// delete directory
			return dir.delete();
		}
	}

	public static boolean deleteFile(String filePath) {
		if (isFileExist(filePath)) {
			return new File(filePath).delete();
		}
		return false;
	}

	public static ArrayList<String> getSubFolderNames(String path) {
		File file = new File(path);
		File[] array = file.listFiles();

		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			if (array[i].isDirectory()) {
				// only take file name
				arrayList.add(array[i].getName());
			} else if (array[i].isDirectory()) {
				getSubFileNames(array[i].getPath());
			}
		}

		return arrayList;
	}

	public static ArrayList<String> getSubFileNames(String path) {
		File file = new File(path);
		File[] array = file.listFiles();

		ArrayList<String> arrayList = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile()) {
				// only take file name
				arrayList.add(array[i].getName());
			} else if (array[i].isDirectory()) {
				getSubFileNames(array[i].getPath());
			}
		}

		return arrayList;
	}

	public static ArrayList<String> getFileNamesNoSuffix(String path) {
		File file = new File(path);
		File[] array = file.listFiles();

		ArrayList<String> arrayList = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile()) {
				arrayList.add(array[i].getName().split("\\.")[0]);
			} else if (array[i].isDirectory()) {
				getFileNamesNoSuffix(array[i].getPath());
			}
		}

		return arrayList;
	}

	public static String read(String filePath) {
		// file path is null
		if (CommonMethod.isVoid(filePath)) {
			return null;
		}
		// file is not exist
		if (!isFileExist(filePath)) {
			return null;
		}
		String fileContent = "";
		Reader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(new File(filePath)));
			int tempChar_int;
			while ((tempChar_int = reader.read()) != -1) {
				fileContent += Character.valueOf((char) tempChar_int);
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		} catch (IOException ex) {
			Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return fileContent;
	}

	public static boolean rename(String srcPath, String destPath) {
		if (CommonMethod.isVoid(srcPath) || CommonMethod.isVoid(destPath)) {
			return false;
		}
		if (!isDirExist(srcPath) && !isFileExist(srcPath)) {
			return false;
		}
		if (isDirExist(destPath) || isFileExist(destPath)) {
			return false;
		}

		new File(srcPath).renameTo(new File(destPath));
		return true;
	}

}
