package com.wipro.igrs.pot.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
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
import com.wipro.igrs.pot.form.PotFORM;
import com.wipro.igrs.util.GUIDGenerator;


public class PotUpdate extends BaseAction
{
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws Exception 
     */
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception
	{
		boolean flag = false;
		PotFORM data = (PotFORM) form;
		PotBD potBD = null;
		String actionName = data.getPotDTO().getActionName();
		String guid=GUIDGenerator.getGUID();
		ODServerDetails connDetails = new ODServerDetails();
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		connDetails.setAppServerIp(pr.getValue("AppServerIp"));
        connDetails.setCabinetName(pr.getValue("CabinetName"));
        connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
        connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
        connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
        connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
        connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
		String downloadPath=pr.getValue("igrs_upload_path");
		String EstampPath = downloadPath+File.separator+guid;
		Dataclass metaDataInfo = new Dataclass();
		metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
		DocumentOperations docOperations = new DocumentOperations();
		String forward="success1";
		boolean documentViewFlag = false;
		data.setErrorMsg("");
		
		try
		{
			potBD = new PotBD();
			if(actionName!=null && actionName.equalsIgnoreCase("VIEW_ESTAMP"))
			{
				
				String estampcode = data.getEstampCodech();
				documentViewFlag = true;
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
					//metaDataInfo.setUniqueNo(formData.getEstampCodech());
					FileInputStream fileInputStream = null;
					OutputStream out = null;
			try{		
				metaDataInfo.setUniqueNo(data.getEstampCode());
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
			  		String fileName = null;
			  		File f = new File(EstampPath);
			  		File [] files = f.listFiles();
			  	if(files!=null && files.length>0){
			  		for(File file : files){
				  		fileName = file.getName();
				 	  }
			  		
			  		String path = EstampPath+File.separator+fileName;
				  //String path = EstampPath+File.separator+"EStamp.PDF";
				   response.setHeader("Content-Disposition", "attachment; filename="
							+ URLEncoder.encode(path,"UTF-8"));
				   
				   File fileToDownload = new File(path);
				    fileInputStream = new FileInputStream(fileToDownload);
				    out = response.getOutputStream();
				   byte[] buf = new byte[2048];

				   int readNum;
				   while ((readNum=fileInputStream.read(buf))!=-1)
				   {
				      out.write(buf,0,readNum);
				   }
			  	}else{
			  		data.setErrorMsg("Document cannot be downloaded / दस्तावेज़  डाउनलोड  नहीं  किया  जा  सकता  है");
				}
				}catch (Exception e) {
					forward ="success1";
					e.printStackTrace();
					
				}finally{
					if(fileInputStream!=null){
						fileInputStream.close();
					}
					if(out!=null){
						out.close();
					}
				}
				//dto.setActionName(null);
				actionName="";
				forward ="success1";
				
				
			}
			else if(data.getPotDTO().getActionName().equalsIgnoreCase("downloadMainDoc"))
			{
				
				String filePath=data.getPotDTO().getDocumentPath()+"/"+data.getPotDTO().getDocumentName();
				//	logger.debug("retrieval path of sign-->"+filePath);
					/*DMSUtility dmsUtil=new DMSUtility();
					
					DMSUtility.downloadDocument(response, filePath, dmsUtil.getImageBytes(filePath));*/
		    		  response.setContentType("application/download");
			    		//	logger.debug("retrieval path of sign-->"+filePath);
			    			/*DMSUtility dmsUtil=new DMSUtility();
			    			
			    			DMSUtility.downloadDocument(response, filePath, dmsUtil.getImageBytes(filePath));*/
		          		  response.setHeader("Content-Disposition", "attachment; filename="
		    						+ URLEncoder.encode(filePath,"UTF-8"));
		    			   
		    			   File fileToDownload = new File(filePath);
		    			   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
		    			   OutputStream out = response.getOutputStream();
		    			   byte[] buf = new byte[2048];
		    			   data.getPotDTO().setActionName(null);
		    			   flag=true;
		    			   int readNum;
		    			   while ((readNum=fileInputStream.read(buf))!=-1)
		    			   {
		    			      out.write(buf,0,readNum);
		    			   }
		    			   fileInputStream.close();
		    			   out.close();
					
				
			}
			else if (data.getPotDTO().getActionName().equalsIgnoreCase("update"))
			{
			flag = potBD.potUpdate1(request,data);
			   data.getPotDTO().setActionName(null);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionError error = new ActionError("error.msg");
			errors.add("key1",error);
			saveErrors(request,errors);
			return mapping.findForward("fail");
		}
		if(documentViewFlag){
			String status = data.getStatus();
			if(status!=null){
			if(status.contains("The E-stamp is already impounded by DR"))
			{
				status = "The E-stamp is already impounded by DR. / ई-स्टांप पहले से ही जिला रजिस्ट्रार द्वारा जब्त कर लिया है";
			}else if(status.contains("Already Consumed"))
			{
				status = "Already Consumed / इ-स्टाम्प का उपयोग हो चुका है";
			}
			else if(status.contains("Refund has been applied for this e-stamp with Refund id"))
			{
				status = "Refund has been applied for this e-stamp. /  इस  ई-टिकट  के  लिए  रिफंड  लागू  किया  गया  है ";
			}
			}
			data.setStatus(status);
			request.setAttribute("pot",data);
		}

			if(flag)
			{
				data.setTransactionId("");
				return mapping.findForward("success");
			}else if(documentViewFlag){
				return mapping.findForward("success1");
			}
			else
			{
			return mapping.findForward("fail");
			}
		
		
	}

}
