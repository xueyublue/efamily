package sg.xueyu.efamily.model;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import sg.xueyu.efamily.model.dto.CloudFilesDTO;
import sg.xueyu.efamily.system.EFamilyParam;

public class CloudModel {

	@SuppressWarnings("unused")
	private Connection mConnection = null;

	public CloudModel(Connection conn) {
		this.mConnection = conn;
	}

	// Check Cloud Basic Path
	public void checkCloudPath(ServletContext servletContext) {

		String path = servletContext.getRealPath(EFamilyParam.CLOUD_ROOT_PATH);
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}

		path = servletContext.getRealPath(EFamilyParam.CLOUD_DOCUMENT_PATH);
		dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}

		path = servletContext.getRealPath(EFamilyParam.CLOUD_MUSIC_PATH);
		dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}

		path = servletContext.getRealPath(EFamilyParam.CLOUD_PICTURE_PATH);
		dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}

		path = servletContext.getRealPath(EFamilyParam.CLOUD_VIDEO_PATH);
		dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
	}

	// Get Sub Files
	public List<CloudFilesDTO> getSubFiles(HttpServletRequest httpServletRequest, String path) {
		// Validate Path
		if (path.equals("/")) {
			path = httpServletRequest.getServletContext().getRealPath(EFamilyParam.CLOUD_ROOT_PATH);
		} else {
			path = httpServletRequest.getServletContext().getRealPath(EFamilyParam.CLOUD_ROOT_PATH) + path;
		}
		
		if (!new File(path).exists()) {
			return null;
		}

		// Get sub files
		List<CloudFilesDTO> cloudFilesDTOs = new ArrayList<>();
		List<File> files = reArrangeFiles(new File(path).listFiles());
		for (File file : files) {
			CloudFilesDTO dto = new CloudFilesDTO();

			// Set name
			dto.setName(file.getName());
			// Set file size
			dto.setSize(changeFileSizeToDisp(file.length()));
			// Set directory
			if (file.isDirectory()) {
				dto.setDir(true);
				dto.setSize("-");
			} else {
				dto.setDir(false);
			}
			// Set last modified date
			dto.setLastModifiedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(file.lastModified())));
			
			cloudFilesDTOs.add(dto);
		}

		return cloudFilesDTOs;
	}

	/** Private Methods **/
	// Change File Size to Display Text
	private String changeFileSizeToDisp(long size) {

		StringBuilder dispSize = new StringBuilder();

		String uom_BYTE = "BYTE";
		String uom_KB = "KB";
		String uom_MB = "MB";
		String uom_GB = "GB";
		String uom_TB = "TB";

		if (size <= 1024) {
			return dispSize.append(size).append(" ").append(uom_BYTE).toString();
		} 
		else if (size <= 1014 * 1024) {
			return dispSize.append(size / 1024).append(" ").append(uom_KB).toString();
		}
		else if (size <= 1014 * 1024 * 1024) {
			return dispSize.append(size / 1024 /1024).append(" ").append(uom_MB).toString();
		}
		else if (size <= 1014 * 1024 * 1024 * 1024) {
			return dispSize.append(size / 1024 / 1024 /1024).append(" ").append(uom_GB).toString();
		}
		else if (size <= 1014 * 1024 * 1024 * 1024 * 1024) {
			return dispSize.append(size / 1024 / 1024 /1024 /1024).append(" ").append(uom_TB).toString();
		}

		return "";
	}
	
	// Re-Arrange Files
	private List<File> reArrangeFiles(File[] files) {
		
		List<File> dirList = new ArrayList<>();
		List<File> fileList = new ArrayList<>();
		for (File file : files) {
			if (file.isDirectory()) {
				dirList.add(file);
			} else {
				fileList.add(file);
			}
		}
		
		dirList.addAll(fileList);
		
		return dirList;
	}
}
