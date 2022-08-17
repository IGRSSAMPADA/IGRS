package com.wipro.igrs.pot.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.action.CaveatsViewSearchAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.estamping.constant.CommonConstant;
import com.wipro.igrs.pot.bd.PotBD;
import com.wipro.igrs.pot.dao.PotDAO;
import com.wipro.igrs.pot.dto.potDTO;
import com.wipro.igrs.pot.form.PotFORM;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.suppleDoc.dto.SuppleDocDTO;
import com.wipro.igrs.util.GUIDGenerator;
//Added new common code for MIME Type check of Uploaded file
import com.wipro.igrs.common.mime.MIMECheck;

public class PotReadAction extends BaseAction
{
	public String counterPStamp = "";
	public HashMap<String, potDTO> mapPStamp=null;;
	private  Logger logger = 
		(Logger) Logger.getLogger(PotReadAction.class);
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
	 * @throws Exception 
     * @throws Exception 
     */
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception
	{
		PotFORM formData = (PotFORM) form;
		 String language=(String)session.getAttribute("languageLocale");	
		PotDAO aObj= null;
		potDTO dto = formData.getPotDTO();
		String actionName = dto.getActionName();
		String forward="";
		PotBD bd = new PotBD();
		ArrayList errorList = new ArrayList();
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
		String userId = (String) session.getAttribute("UserId");
		String loggedOffice = (String) session.getAttribute("loggedToOffice");
        //String EstampPath = CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
      //  String EstampPath = CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
        String downloadPath=pr.getValue("igrs_upload_path");
        String EstampPath = downloadPath+File.separator+guid;
        formData.setCreatedBy(userId);
        formData.setLoggedOffice(loggedOffice);
        metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
        String initial = request.getParameter("modName");
        formData.setErrorMsg("");
		try {
			aObj = new PotDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String districtName = aObj.getDistrict(formData.getDistrict(),language);
		
		if(actionName!=null && actionName.equalsIgnoreCase("VIEW_ESTAMP"))
		{
			
			String estampcode = formData.getEstampCodech();
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
		  		String fileName = null;
		  		File f = new File(EstampPath);
		  		File [] files = f.listFiles();
		  	if(files!=null && files.length>0 ){
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
		  		formData.setErrorMsg("Document cannot be downloaded / दस्तावेज़  डाउनलोड  नहीं  किया  जा  सकता  है");
			}
			}catch (Exception e) {
				forward ="screen1";
				e.printStackTrace();
			}finally{
				if(fileInputStream!=null){
					fileInputStream.close();
				}
				if(out!=null){
					out.close();
				}
			}
			dto.setActionName(null);
			forward ="screen1";
		}
		else if(actionName!=null && actionName.equalsIgnoreCase("radio1"))
		{
			
			if("Y".equalsIgnoreCase(formData.getCheckRadioEP()))
			{
				formData.setDataShow1("Y");
				formData.setPstampDetails(new HashMap());
				// START | PO Tool Changes to reset the fields to be filled by PO | santosh
				logger.debug("PO Tool Changes | Inside potReadAction | Inside potReadAction class to clear the form on selection of readio button for having Estamp");
				formData.setEstampCode("");
				formData.setPoComments("");
				formData.setDateOfObjection("");
				formData.setImpundCheck("");
				formData.setErrorMsg("");
				formData.setPstampNoOfPersons("");
				formData.setPstampNoOfPersons1("");
				dto.setCodeStamps("");
				dto.setDenominationStamps(0.0);
				dto.setReqStampDuties(0);
				dto.setAttachedDoc(null);
				dto.setDocumentName("");
				dto.setDocumentPath("");
				// END | PO Tool Changes to reset the fields to be filled by PO | santosh
				forward ="screen1";
				
			}
			else if("N".equalsIgnoreCase(formData.getCheckRadioEP()))
			{
				logger.debug("PO Tool Changes | Inside potReadAction | class to clear the form on selection of readio button for having Pstamps");
				formData.setDataShow1("N");
				formData.setCaseStat("F");
				// START | PO Tool Changes to reset the fields to be filled by PO | santosh
				formData.setEstampCode("");
				formData.setPoComments("");
				formData.setDateOfObjection("");
				formData.setImpundCheck("");
				formData.setErrorMsg("");
				formData.setPstampNoOfPersons("");
				formData.setPstampNoOfPersons1("");
				dto.setCodeStamps("");
				dto.setDenominationStamps(0.0);
				dto.setReqStampDuties(0);
				dto.setDocumentName("");
				dto.setDocumentPath("");
				
				// END | PO Tool Changes to reset the fields to be filled by PO | santosh
				forward ="screen1";
			
			}
			dto.setActionName(null);
		}
		/*else if(actionName!=null && actionName.equalsIgnoreCase("radio"))
		{
		
		if("Y".equalsIgnoreCase(formData.getCheckRadio()))
				{
			
			formData.setDataShow("Y");
			forward ="screen1";
				}
		else
		{
			
			
			formData.setDataShow("N");
			forward ="screen1";
		}
		dto.setActionName(null);
		}*/
		else if(actionName!=null && actionName.equalsIgnoreCase("SAVE_DETAILS_POT"))
		{
			
			if(formData.getDataShow().equalsIgnoreCase("Y"))
			{
				bd.insertRegNumberDetails(formData);
				
				
			}
			else
			{
				if(formData.getDataShow1().equalsIgnoreCase("Y"))
				{
					//START | POT Tool Changes | Consume document on click of submit button | santosh
					//String estampcode = formData.getEstampCode();
					
					
					bd.updateEstamp(formData);
					//bd.getEstampDetails(formData);
					
					bd.insertEstampDetials(formData);
					//END | POT Tool Changes | Consume document on click of submit button | santosh
					
				}
				else
				{
					//boolean pstampInsertionCheck = false;
					counterPStamp = String.valueOf(formData.getPotDTO().getCounterPStamp()+1);
					mapPStamp = formData.getPstampDetails();

					potDTO ndto = new potDTO();
					ndto.setDenominationStamps(formData.getPotDTO().getDenominationStamps());
					ndto.setCodeStamps(formData.getPotDTO().getCodeStamps());

					formData.getPotDTO().setStampDutyNow(formData.getPotDTO().getStampDutyNow()+formData.getPotDTO().getDenominationStamps());


					mapPStamp.put(counterPStamp, ndto);
					formData.getPotDTO().setCounterPStamp(Integer.parseInt(counterPStamp));
					formData.setPstampDetails(mapPStamp);
					//START | POT Tool Changes | Consume document on click of submit button | santosh
					
					 bd.insertPstampDetails(formData);
					
					//END | POT Tool Changes | Consume document on click of submit button | santosh
					
				}
			}
			dto.setActionName(null);
			
			forward = "success";
		}
		else if(actionName!=null && actionName.equalsIgnoreCase("GET_ESTAMP_DETAILS"))
		{
			
			String estampcode = formData.getEstampCode();
			//formData.setEstampCodech(estampcode);
			String pageName = null;
			bd.fetchEstampDetails(formData,pageName);
			
			dto.setActionName(null);
			
			forward ="screen1";
		}
		else if(actionName!=null && actionName.equalsIgnoreCase("SavePStampDetails"))
		{
			
counterPStamp = String.valueOf(formData.getPotDTO().getCounterPStamp()+1);
mapPStamp = formData.getPstampDetails();

potDTO ndto = new potDTO();
ndto.setDenominationStamps(formData.getPotDTO().getDenominationStamps());
ndto.setCodeStamps(formData.getPotDTO().getCodeStamps());

formData.getPotDTO().setStampDutyNow(formData.getPotDTO().getStampDutyNow()+formData.getPotDTO().getDenominationStamps());


mapPStamp.put(counterPStamp, ndto);
formData.getPotDTO().setCounterPStamp(Integer.parseInt(counterPStamp));
formData.setPstampDetails(mapPStamp);

formData.getPotDTO().setDenominationStamps(0.0);
formData.getPotDTO().setCodeStamps("");





			
			dto.setActionName(null);
			
			forward ="screen1";
		}
		else if(actionName!=null && actionName.equalsIgnoreCase("deletePStampDetail"))
		{
			double value=0.0;
    		double valueStamps = formData.getPotDTO().getStampDutyNow();
    		String deleteKeys[] = formData.getKeysStringPStamp().split(",");
    		
    		mapPStamp = formData.getPstampDetails();
    		for(int i = 0; i<deleteKeys.length;i++)
			{
				if(mapPStamp.containsKey(deleteKeys[i]))
				{
					value = value + mapPStamp.get(deleteKeys[i]).getDenominationStamps();
					
					mapPStamp.remove(deleteKeys[i]);
				}
			}
		
			
			valueStamps = valueStamps-value;
			formData.getPotDTO().setStampDutyNow(valueStamps);
			formData.setPstampDetails(mapPStamp);

			dto.setActionName(null);
			
			forward ="screen1";
		}
		else if(actionName!=null && actionName.equalsIgnoreCase("downloadMainDoc"))
    	{
    		String filePath=formData.getPotDTO().getDocumentPath()+"/"+formData.getPotDTO().getDocumentName();
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

    			   int readNum;
    			   while ((readNum=fileInputStream.read(buf))!=-1)
    			   {
    			      out.write(buf,0,readNum);
    			   }
    			   fileInputStream.close();
    			   out.close();
			
    				dto.setActionName(null);
			
			actionName="";
			
			forward ="screen1";
    		
    	}
		else if("CONSUME_ESTAMP_DETAILS".equalsIgnoreCase(actionName))
		{
			String estampcode = formData.getEstampCode();
			bd.updateEstamp(formData);
			bd.getEstampDetails(formData);
			
			forward = "screen1";
		}
		else if (actionName!=null && "setUploadFile".equalsIgnoreCase(actionName)) {
			try {
				FormFile uploadedFile = formData.getPotDTO().getAttachedDoc();
				byte contents[] = uploadedFile.getFileData();
				formData.getPotDTO().setPhoto(contents);
				if ("".equals(uploadedFile.getFileName())) {
//					clearDoc(cDto);
					errorList
					.add("Invalid file selection. Please try again./अवैध फ़ाइल चयन. पुन: प्रयास करें.");
				}
				
				/*String fileExt = getFileExtension(uploadedFile.getFileName());
				AuditInspectionRule rule = new AuditInspectionRule();
				rule.validateFileTypePOT(fileExt);
				
				*/
				int size = uploadedFile.getFileSize();
				// new code 
				MIMECheck mimeCheck = new MIMECheck();
				String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
		       Boolean mime = mimeCheck.validateMIMEAndJPGFileType(uploadedFile);  // common code to check mime type and validation of Jpg file.
				 if (!mime) {
//					        clearDoc(cDto);
					         errorList
							.add("Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.");
					request.setAttribute("errorsList", errorList);
				} else {
					if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
//						clearDoc(cDto);
						errorList
								.add("File size is Greater than 10 MB. Please select another file./फ़ाइल का आकार 10 एमबी से अधिक है. एक और फाइल चुनें");
						request.setAttribute("errorsList", errorList);
					} else {
						 String docName = "Caveat_Document."+fileExt;
						// fm.getCaveatsDTO().setUploaded_doc_path(docName);
						 formData.getPotDTO().setDocumentName(uploadedFile.getFileName());
						
						 formData.getPotDTO().setDocContents(uploadedFile.getFileData());
						 formData.getPotDTO().setDocFileSize(getFileSize(uploadedFile.getFileData().length));
						 Date date = new Date();
							  Format yearformat  = new SimpleDateFormat("yy");
							  Format monthformat = new SimpleDateFormat("MM");
							  Format dateformat  = new SimpleDateFormat("dd");
							  Format hourformat  = new SimpleDateFormat("hh");
							  Format minformat  = new SimpleDateFormat("mm");
							  Format secformat  = new SimpleDateFormat("ss");
							  Format ampmformat  = new SimpleDateFormat("a");
							  String dfmt = dateformat.format(date);
							  String yfmt = yearformat.format(date);
							  String mfmt = monthformat.format(date);
							  String hfmt = hourformat.format(date);
							  String mifmt = minformat.format(date);
							  String sfmt = secformat.format(date);
							  String ampmfmt = ampmformat.format(date);
							  String fldrnm = dfmt+mfmt+yfmt+hfmt+mifmt+sfmt+ampmfmt;
							  PropertiesFileReader read = PropertiesFileReader.getInstance("igrs.properties");
							 String pa =  read.getValue("upload.location");
						//	logger.debug("the new date in upload thumb is----------------------- >"+fldrnm);
							String thumbName = uploadedFile.getFileName();
							String thumbPath = pa+fldrnm;
							 formData.getPotDTO().setDocumentPath(thumbPath);
							boolean up=uploadFile(uploadedFile,thumbName,thumbPath);
							System.out.println(up);
					}
				}
			} catch (Exception e) {
				errorList.add("Unable to upload file. Please try again./फ़ाइल अपलोड करने में असमर्थ. पुन: प्रयास करें.");
				request.setAttribute("errorsList", errorList);
			}
	dto.setActionName(null);
	
	if(errorList.size()>0)
	{
		dto.setErrorMsg((String)errorList.get(0));
		 formData.getPotDTO().setDocumentName("");
	}
	else
	{
		dto.setErrorMsg("");
	}
	forward ="screen1";
		} 
		else if (actionName!=null && "downloadMainDoc1".equalsIgnoreCase(actionName)) {
			try {
				byte[] content = formData.getPotDTO().getDocContents();
				String filename = formData.getPotDTO().getDocumentName();
				if(content != null) {
					CaveatsViewSearchAction.downloadDocument(response, content, filename);
				}
			} catch (Exception e) {
			}			
		}/*else if("RECOMMENDED_FOR_IMPOUND_BY_PO".equalsIgnoreCase(actionName)){
			
			String estampcode = formData.getEstampCode();
			
			bd.fetchEstampDetails(formData);
			
			dto.setActionName(null);
			
			forward ="screen1";
			
		}else if("NOT_RECOMMENDED_FOR_IMPOUND_BY_PO".equalsIgnoreCase(actionName)){
			
		}*/
		formData.setDistrictName(districtName);
		//String language=(String)session.getAttribute("languageLocale");
		//PotFORM formData = (PotFORM) form;
		//PotBD bd = new PotBD();
		//String languageLocale="hi";
		//if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			//bd.getDistrictId(request,language);
		//}else{
		//	bd.getDistrictId(request,language);
		//}
		
		//formData.setLanguage(language);
		
		
		return mapping.findForward(forward);
	
}
	
	
    private boolean uploadFile(FormFile filetobeuploaded,String fileName,String filePath) {

    	
    	
    	try {
    		File folder = new File(filePath);
    	      if (!folder.exists()) {
    		folder.mkdirs();
    	       }
    	      
    			File newFile = new File(filePath, fileName);
    		//	logger.info("NEW FILE NAME:-" + newFile);
    			FileOutputStream fos = new FileOutputStream(newFile);
    			fos.write(filetobeuploaded.getFileData());
    			fos.close();
    		

    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return true;
    }
	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}
	
	private String getFileSize(int length) {
		double fileSizeInBytes = length;
		// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
		double fileSizeInKB = fileSizeInBytes / 1024.0;
		// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
		double fileSizeInMB = fileSizeInKB / 1024.0;

		//System.out.println("fileSizeInBytes = "+fileSizeInBytes+" fileSizeInKB = "+fileSizeInKB+" fileSizeInMB = "+fileSizeInMB);
		DecimalFormat decim = new DecimalFormat("#.##");
		Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
		String photoSize="("+fileSize+" MB)";
		return photoSize;
	}
	
}