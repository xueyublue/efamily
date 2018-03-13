package sg.xueyu.efamily.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import sg.xueyu.dbhandler.util.DBUtils;
import sg.xueyu.efamily.model.CloudModel;
import sg.xueyu.efamily.model.dto.CloudFilesDTO;
import sg.xueyu.efamily.system.SystemConstants;
import sg.xueyu.efamily.system.SystemLogger;
import sg.xueyu.zebra.action.ActionResult;
import sg.xueyu.zebra.action.ResultType;
import sg.xueyu.zebra.annotation.Method;
import sg.xueyu.zebra.annotation.Method.RequestMethod;
import sg.xueyu.zebra.annotation.Param;
import sg.xueyu.zebra.annotation.Path;
import sg.xueyu.zebra.core.ActionResultBuilder;

@Path("/cloud")
public class CloudController extends BaseController {
	
	public CloudController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super(request, response);
	}
	
	@Method(RequestMethod.GET)
	public ActionResult getPage() throws Exception {
		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}
			
			// Check Cloud Path
			CloudModel cloudModel = new CloudModel(getConnection());
			cloudModel.checkCloudPath(getHttpServletRequest().getServletContext());

			// Perform to forward to Cloud.jsp
			return ActionResultBuilder.buildActionResultWithURL(SystemConstants.URL_CLOUD);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);

			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
	@Path("/getSubFiles")
	@Method(RequestMethod.GET)
	public ActionResult getSubFiles(@Param("path") String path) throws Exception {
		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}
			
			// Get Sub Files
			CloudModel cloudModel = new CloudModel(getConnection());
			List<CloudFilesDTO> cloudFilesDTOs = cloudModel.getSubFiles(getHttpServletRequest(), path);
			
			return ActionResultBuilder.buildActionResult(ActionResultBuilder.buildResultContent(null, cloudFilesDTOs), ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);

			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
	@Path("/upload")
	@Method(RequestMethod.POST)
	public ActionResult upload() throws Exception {
		try {
			ActionResult authResult = credentialAuthentication(getHttpServletRequest());
			if (authResult != null) {
				return authResult;
			}
			
//			if (getHttpServletRequest().getContentLength() > 0) {
//				InputStream inputStream = getHttpServletRequest().getInputStream();
//				File file = new File(getHttpServletRequest().getServletContext().getRealPath(EFamilyParam.CLOUD_ROOT_PATH) + "/" + String.valueOf(System.currentTimeMillis()) + ".txt");
//				file.createNewFile();
//				
//				FileOutputStream outputStream = new FileOutputStream(file);
//				byte temp[] = new byte[1024];
//				int size = -1;
//				while((size = inputStream.read(temp)) != -1) {
//					outputStream.write(temp, 0, size);
//				}
//				
//				System.out.println("Finished");
//			}
			
			List<Part> fileParts = new ArrayList<>();
			List<String> fileNames = new ArrayList<>();
			
			for (Part part : getHttpServletRequest().getParts()) {
				String cd = part.getHeader("Content-Disposition");
				if (cd.indexOf("filename") >= 0) {
					fileParts.add(part);
					fileNames.add(cd.substring(cd.lastIndexOf("=") + 1).replaceAll("\\\"", ""));
				}
			}
			
//			DiskFileItemFactory factory = new DiskFileItemFactory();
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			upload.setHeaderEncoding("UTF-8");
////			if (!ServletFileUpload.isMultipartContent(getHttpServletRequest())) {
////				return null;
////			}
//			
//			List<FileItem> list = upload.parseRequest(getHttpServletRequest());
//			for (FileItem item : list) {
//				if (item.isFormField()) {
//					String name = item.getFieldName();
//					String value = item.getString("UTF-8");
//					System.out.println(name + "=" + value);
//				} else {
//					String fileName = item.getName();
//					System.out.println(fileName);
//					if (fileName == null || fileName.trim().equals("")) {
//						continue;
//					}
//					
//					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
//					InputStream in = item.getInputStream();
//					
//					FileOutputStream out = new FileOutputStream(getHttpServletRequest().getServletContext().getRealPath(EFamilyParam.CLOUD_ROOT_PATH) + "/" + String.valueOf(System.currentTimeMillis()) + ".txt");
//					
//					byte buffer[] = new byte[1024];
//					
//					int len = 0;
//					while((len = in.read(buffer)) > 0) {
//						out.write(buffer, 0, len);
//					}
//					
//					in.close();
//					out.close();
//					item.delete();
//					
//					System.out.println("Finished");
//				}
//			}
			
			return ActionResultBuilder.buildActionResult(ActionResultBuilder.buildResultContent(null, null), ResultType.Ajax);
		} catch (Exception e) {
			SystemLogger.error(e);
			getHttpServletResponse().setStatus(500);

			return ActionResultBuilder.buildActionResult(null, SystemConstants.ERROR_MSG_UNHANDLED_EXCEPTION, ResultType.Ajax);
		} finally {
			DBUtils.closeConnection(getConnection());
		}
	}
	
	@Path("/download")
	@Method(RequestMethod.GET)
	public ActionResult download() throws Exception {
		return null;
	}
	
	@Path("/delete")
	@Method(RequestMethod.POST)
	public ActionResult delete() throws Exception {
		return null;
	}
	
}
