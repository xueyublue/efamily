package sg.xueyu.efamily.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@Path("/add")
	@Method(RequestMethod.POST)
	public ActionResult add() throws Exception {
		return null;
	}
	
	@Path("/update")
	@Method(RequestMethod.POST)
	public ActionResult update() throws Exception {
		return null;
	}
	
	@Path("/delete")
	@Method(RequestMethod.POST)
	public ActionResult delete() throws Exception {
		return null;
	}
	
}
