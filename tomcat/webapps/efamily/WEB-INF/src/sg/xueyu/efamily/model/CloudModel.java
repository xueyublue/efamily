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
		}

		if (!new File(path).exists()) {
			return null;
		}

		// Get sub files
		List<CloudFilesDTO> cloudFilesDTOs = new ArrayList<>();
		File[] files = new File(path).listFiles();
		for (File file : files) {
			CloudFilesDTO dto = new CloudFilesDTO();
			
			// Set name
			dto.setName(file.getName());
			// Set directory
			if (file.isDirectory()) {
				dto.setDir(true);
			} else {
				dto.setDir(false);
			}
			// Set last modified date
			dto.setLastModifiedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(file.lastModified())));
			// Set file size
			dto.setSize(String.valueOf(file.getTotalSpace()));
			
			cloudFilesDTOs.add(dto);
		}

		return cloudFilesDTOs;
	}

}
