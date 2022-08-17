package com.wipro.igrs.pot.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.estamping.constant.CommonConstant;
import com.wipro.igrs.pot.bd.PotBD;
import com.wipro.igrs.pot.dto.potDTO;
import com.wipro.igrs.pot.form.PotFORM;
import com.wipro.igrs.util.GUIDGenerator;

public class PotCreateEstamp  extends BaseAction{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		PotFORM formData = (PotFORM) form;
		String actionName = formData.getActionName();
		String forward = "success";
		PotBD bd = new PotBD();
		String userId = (String) session.getAttribute("UserId");
		String officeId = (String)session.getAttribute("loggedToOffice");

		String language=(String)session.getAttribute("languageLocale");
		DocumentOperations docOperations = new DocumentOperations();
        ODServerDetails connDetails = new ODServerDetails();
        Dataclass metaDataInfo = new Dataclass();
        PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
        connDetails.setAppServerIp(pr.getValue("AppServerIp"));
        connDetails.setCabinetName(pr.getValue("CabinetName"));
        connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
        connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
        connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
        connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
        connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
        String guid=GUIDGenerator.getGUID();
        //String EstampPath = CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
      //  String EstampPath = CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
        String downloadPath=pr.getValue("igrs_upload_path");
        String EstampPath = downloadPath+File.separator+guid;
        
        metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
        String initial = request.getParameter("modName");
        
        if(initial!=null && "POT".equalsIgnoreCase(initial))
        {
        	
        	formData = new PotFORM();
        	
        	session.setAttribute("potRequest", formData);
        	formData.setActionName("");
        	actionName="";
        	forward = "success";
        	
        }
        
        
        ArrayList list=(ArrayList) bd.getPotNameBD(userId);
		for(int i=0;i<list.size();i++)
		{
			potDTO potdto=new potDTO();
			potdto=(potDTO)list.get(i);
			//potdto.getFirstName();
			potdto.getPoFirstName();
			//System.out.println(potdto.getFirstName());
			//System.out.println(potdto.getMiddleName());
			//System.out.println(potdto.getLastName());
			
		/*potfrm.setFirstName(potdto.getFirstName());
		potfrm.setMiddleName(potdto.getMiddleName());
		potfrm.setLastName(potdto.getLastName());
		*/

			
			formData.setPoFirstName(potdto.getPoFirstName());
			formData.setPoMiddleName(potdto.getPoMiddleName());
			formData.setPoLastName(potdto.getPoLastName());
			if(initial!=null && "POT".equalsIgnoreCase(initial))
	        {
			formData.setDistrict(potdto.getDistrict());
	        }



		}
		bd.getDistrictNames(request,language);
		if("GET_ESTAMP_DETAILS".equalsIgnoreCase(actionName))
		{
			String estampcode = formData.getEstampCode();
			
			bd.getEstampDetails(formData);
			
			forward = "success";
		}
		
		if("CONSUME_ESTAMP_DETAILS".equalsIgnoreCase(actionName))
		{
			String estampcode = formData.getEstampCode();
			bd.updateEstamp(formData);
			bd.getEstampDetails(formData);
			
			forward = "success";
		}
		if("IMPOUND_ESTAMP_DETAILS".equalsIgnoreCase(actionName))
		{
			String estampcode = formData.getEstampCode();
			//bd.updateEstamp1(formData);
			bd.getEstampDetails(formData);
			formData.setImpoundShow("Y");
			forward = "success";
		}
		if("IMPOUND_ESTAMP_DETAILS_1".equalsIgnoreCase(actionName))
		{
			String estampcode = formData.getEstampCode();
			
			bd.updatePOT(formData);
			bd.updateEstamp1(formData);
			formData.setImpoundShow("Y");
			forward = "success1";
		}
		if("VIEW_ESTAMP".equalsIgnoreCase(actionName))
		{
			String estampcode = formData.getEstampCode();
			 File output1;
				output1 = new File(EstampPath.toString());
				if (output1.exists()) {
					//logger.info("file already exists deleting....");
					output1.delete();
				}
				if (output1.getParentFile().exists() == false) {
					//logger.info("Parent not found creating directory....");
					output1.getParentFile().mkdirs();
				}
		        
		metaDataInfo.setUniqueNo(formData.getEstampCode());
		if (null==metaDataInfo.getUniqueNo()) {
			session.setAttribute("checkStatus", "DMSError");
			return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
		}
		if (metaDataInfo.getUniqueNo().equals("")) {
			session.setAttribute("checkStatus", "DMSError");
			return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
		}
		  int result = docOperations.downloadDocument(connDetails,metaDataInfo,EstampPath,CommonConstant.MODULE_NAME);
		  response.setContentType("application/download");

			  String path = EstampPath+File.separator+"EStamp.PDF";
			   response.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(path,"UTF-8"));
			   
			   File fileToDownload = new File(path);
			   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
			   OutputStream out = response.getOutputStream();
			   byte[] buf = new byte[2048];

			   int readNum;
			   while ((readNum=fileInputStream.read(buf))!=-1)
			   {
			      out.write(buf,0,readNum);
			   }
			   fileInputStream.close();
			   out.close();
		
			
			forward = "success";
		}
		
		
		
		return mapping.findForward(forward);
	}

}
