package com.wipro.igrs.caveats.action;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bd.DocumentFlagEvaluationBD;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.form.DocumentFlagEvaluationForm;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.common.mime.MIMECheck;
import com.wipro.igrs.util.GUIDGenerator;

public class DocumentFlagEvaluation extends BaseAction{
	
	private Logger logger = (Logger) Logger.getLogger(DocumentFlagEvaluation.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		String userId = (String) session.getAttribute("UserId");
		String language = (String)session.getAttribute("languageLocale");
		String forwardPage = "failure";
		DocumentFlagEvaluationForm docForm = (DocumentFlagEvaluationForm) form;
		DocumentFlagEvaluationBD bd = new DocumentFlagEvaluationBD();
		PropertiesFileReader proper; 
		String FILE_UPLOAD_PATH="";
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		CaveatsBO objCaveatBO = new CaveatsBO();
		boolean revertFlag = false;
		try{
			
			String headerimg=getServlet().getServletContext().getRealPath("")+ "/images/header img.jpg";
			docForm.setHeaderImg(headerimg);
			
			if ("Declare-Null or Void".equalsIgnoreCase(request.getParameter("fnName"))) {
				docForm.setRegId("");
				docForm.setRegNumber("");
				forwardPage = "searchDeclareNullVoidPage";
				
			}else if("Revert-Null or Void".equalsIgnoreCase(request.getParameter("fnName"))){
				docForm.setRegId1("");
				docForm.setRegNumber1("");
				forwardPage = "searchRevertNullVoidPage";
				
			}else if("SEARCH_REVERT_NULL_VOID_ACTION".equalsIgnoreCase(request.getParameter("actionName"))){
				
				docForm.setRevertCourtName("");
				docForm.setRevertCourtOrderDate("");
				docForm.setRevertCourtOrderNo("");
				docForm.setRemarks1("");
				docForm.setUploadedDoc1(null);
				docForm.setDocName1("");
				
				proper=	PropertiesFileReader.getInstance("resources.igrs");
				FILE_UPLOAD_PATH=proper.getValue("igrs_upload_path")+File.separator+"03"+File.separator;
				
				/*if(docForm.getRegId1().startsWith("0"))
				{
					docForm.setRegId(docForm.getRegId1().substring(1));
				}*/
				//boolean check = bd.checkStatus(docForm.getRegId1());
				
				if(!bd.checkRegNumber(docForm.getRegNumber1()))
				{	
					docForm.setError1("Y");
					if("en".equalsIgnoreCase(language))
					{	
					request.setAttribute("MSG", "No records found for this application number.");
					}
					else
					{
						request.setAttribute("MSG", "इस पंजीकरण नंबर के लिए कोई रिकॉर्ड नहीं मिला!");	
					}
				
				forwardPage = "searchRevertNullVoidPage";
				return mapping.findForward(forwardPage);

				}else{
					List list = bd.getRegDetails(docForm.getRegNumber1());
					String status = null, regId = null;
					if(list!=null&&list.size()>0)
					{
						for(int i=0;i<list.size();i++)
						{
							ArrayList subList=(ArrayList) list.get(i);
							status=(String) subList.get(0);
							regId = (String) subList.get(1);
							docForm.setRegId1(regId);
						}
					}
					
					if(status!=null && !(status.trim().equals("17"))){
						docForm.setError1("Y");
						if("en".equalsIgnoreCase(language))
						{	
						request.setAttribute("MSG", "This document is not under Print Preview Stage");
						}
						else
						{
							request.setAttribute("MSG", "यह दस्तावेज़ प्रिंट पूर्वावलोकन स्टेज के अंतर्गत नहीं है|");	
						}
						forwardPage = "searchRevertNullVoidPage";
						return mapping.findForward(forwardPage);
						
					}else if((bd.checkNullAndVoid(docForm.getRegNumber1())).equals("false"))
					{
						docForm.setError1("Y");
						if("en".equalsIgnoreCase(language))
						{	
						request.setAttribute("MSG", "This document is not marked as null/void.");
						}
						else
						{
							request.setAttribute("MSG", "यह दस्तावेज़ रिक्त / शून्य के रूप में चिह्नित नहीं है!");	
						}
						
						forwardPage = "searchRevertNullVoidPage";
						return mapping.findForward(forwardPage);
						
					}else if(status.equals("17"))
					{	
						//String regNumber = bd.getCompletionNumber(docForm.getRegId());
						//docForm.setRegNumber(regNumber);
						List lastRemarksAndUploads = bd.getLastRemarksAndUploads(docForm.getRegNumber1());
						if(lastRemarksAndUploads!=null&&lastRemarksAndUploads.size()>0)
						{
							for(int i=0;i<lastRemarksAndUploads.size();i++)
							{
								ArrayList subList=(ArrayList) lastRemarksAndUploads.get(i);
								String lastRemarks=(String) subList.get(0);
								String lastUploadPath = (String) subList.get(1);
								String courtName = (String) subList.get(2);
								String courtOrderNo = (String) subList.get(3);
								String courtOrderDate = (String) subList.get(4);
								docForm.setLastRemarks(lastRemarks);
								docForm.setLastUploadPath(lastUploadPath);
								
								if(null!=courtName && !"".equalsIgnoreCase(courtName)){
									docForm.setCourtName(courtName);
								}
								if(null!=courtOrderNo && !"".equalsIgnoreCase(courtOrderNo)){
									docForm.setCourtOrderNo(courtOrderNo);
								}
								if(null!=courtOrderDate && !"".equalsIgnoreCase(courtOrderDate)){
									docForm.setCourtOrderDate(courtOrderDate);
								}
							}
						}
						forwardPage = "docDetailsRevert";
						return mapping.findForward(forwardPage);

					}
				}
				
				
				
			
			}else if("SEARCH_ACTION".equalsIgnoreCase(request.getParameter("actionName"))){
				
				
				docForm.setCourtName("");
				docForm.setCourtOrderDate("");
				docForm.setCourtOrderNo("");
				docForm.setRemarks("");
				docForm.setUploadedDoc(null);
				docForm.setDocName("");
				
				proper=	PropertiesFileReader.getInstance("resources.igrs");
				FILE_UPLOAD_PATH=proper.getValue("igrs_upload_path")+File.separator+"03"+File.separator;
				
				/*if(docForm.getRegId().startsWith("0"))
				{
					docForm.setRegId(docForm.getRegId().substring(1));
				}*/
				
				if(!bd.checkRegNumber(docForm.getRegNumber()))
				{
					docForm.setError("Y");
					if("en".equalsIgnoreCase(language))
					{	
					request.setAttribute("MSG", "No records found for this registration number.");
					}
					else
					{
						request.setAttribute("MSG", "इस पंजीकरण नंबर के लिए कोई रिकॉर्ड नहीं मिला|");	
					}
					forwardPage = "searchDeclareNullVoidPage";
					return mapping.findForward(forwardPage);
					
				}else{
					List list = bd.getRegDetails(docForm.getRegNumber());
					String status = null, regId = null;
					if(list!=null&&list.size()>0)
					{
						for(int i=0;i<list.size();i++)
						{
							ArrayList subList=(ArrayList) list.get(i);
							status=(String) subList.get(0);
							regId = (String) subList.get(1);
							docForm.setRegId(regId);
						}
					}
					
					if(status!=null && !(status.trim().equals("17"))){
						docForm.setError("Y");
						if("en".equalsIgnoreCase(language))
						{	
						request.setAttribute("MSG", "This document is not under Print Preview Stage");
						}
						else
						{
							request.setAttribute("MSG", "यह दस्तावेज़ प्रिंट पूर्वावलोकन स्टेज के अंतर्गत नहीं है|");	
						}
						forwardPage = "searchDeclareNullVoidPage";
						return mapping.findForward(forwardPage);
						
					}else if((bd.checkNullAndVoid(docForm.getRegNumber())).equals("true"))
					{
						docForm.setError("Y");
						if("en".equalsIgnoreCase(language))
						{	
							request.setAttribute("MSG", "This document is already marked as null/void.");
						}
						else
						{
							request.setAttribute("MSG", "यह दस्तावेज़ पहले से ही रिक्त / शून्य के रूप में चिह्नित है|");	
						}
						
						forwardPage = "searchDeclareNullVoidPage";
						return mapping.findForward(forwardPage);
						
					}else if(status.equals("17"))
					{	
						//String regNumber = bd.getCompletionNumber(docForm.getRegId());
						//docForm.setRegNumber(regNumber);
						forwardPage = "docDetailsDeclare";

					}
				}
				
				
			}else if("downloadDocument".equalsIgnoreCase(request.getParameter("actionName"))){
				
				  if (null !=docForm.getRegNumber() && !"".equalsIgnoreCase(docForm.getRegNumber())) {	
					
					DocumentOperations docOperations = new DocumentOperations();
					ODServerDetails connDetails = new ODServerDetails();
					logger.debug("value start:here");
					logger.debug(pr.getValue("AppServerIp"));
					logger.debug(pr.getValue("CabinetName"));
					logger.debug(pr.getValue("AppServerUserId"));
					logger.debug(pr.getValue("AppServerUserPass"));
					logger.debug(pr.getValue("ImageServerIP"));
					logger.debug(pr.getValue("ImageServerPort"));
					Dataclass metaDataInfo = new Dataclass();
					connDetails.setAppServerIp(pr.getValue("AppServerIp"));
					connDetails.setCabinetName(pr.getValue("CabinetName"));
					connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
					connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
					connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
					connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
					connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
					metaDataInfo.setUniqueNo(bd.fetchRegTxnId(docForm.getRegNumber()));
					metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
					metaDataInfo.setType("F");
			        metaDataInfo.setLatestFlag("L");
					//metaDataInfo.setType("R");
					String guid = GUIDGenerator.getGUID();
					String downloadPath = pr.getValue("igrs_upload_path");
			
					String EstampPath = downloadPath + File.separator + guid;
		
					File folder = new File(EstampPath.toString());
					if (!folder.exists()) {
						System.out.println(folder.toString());
		
						folder.mkdirs();
		
					}
					String tempPath = "";
					if (null==metaDataInfo.getUniqueNo()) {
						session.setAttribute("checkStatus", "DMSError");
						return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
					}
					if (metaDataInfo.getUniqueNo().equals("")) {
						session.setAttribute("checkStatus", "DMSError");
						return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
					}
					int result = docOperations.downloadDocument(connDetails,metaDataInfo, EstampPath.toString(),"Estamp");
					File[] listOfFiles = folder.listFiles();
					for (int z = 0; z < listOfFiles.length; z++) {
						tempPath = listOfFiles[z].getPath();
		
					}
					logger.debug("download result---------->" + result);
					 //String filePath=bd.burnRequestIdForPDF( dform.getDsearchdto().getRefId(), tempPath,EstampPath);
					byte bytes[] = DMSUtility.getDocumentBytes(tempPath);
					if (bytes != null) {
						DMSUtility.downloadDocument(response, tempPath, bytes);
					} else {
						
							ActionMessages message = new ActionMessages();
							message.add("message", new ActionMessage("reg.doc.download.failedMsg"));
							saveMessages(request,message);
							
					}
				}
				forwardPage = "docDetailsDeclare";
				//eForm.getRegDTO().setRegCompleteId(eRegNo);
			}else if("downloadDocument1".equalsIgnoreCase(request.getParameter("actionName"))){
				
				if (null !=docForm.getRegNumber1() && !"".equalsIgnoreCase(docForm.getRegNumber1())) {	
					
					DocumentOperations docOperations = new DocumentOperations();
					ODServerDetails connDetails = new ODServerDetails();
					logger.debug("value start:here");
					logger.debug(pr.getValue("AppServerIp"));
					logger.debug(pr.getValue("CabinetName"));
					logger.debug(pr.getValue("AppServerUserId"));
					logger.debug(pr.getValue("AppServerUserPass"));
					logger.debug(pr.getValue("ImageServerIP"));
					logger.debug(pr.getValue("ImageServerPort"));
					Dataclass metaDataInfo = new Dataclass();
					connDetails.setAppServerIp(pr.getValue("AppServerIp"));
					connDetails.setCabinetName(pr.getValue("CabinetName"));
					connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
					connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
					connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
					connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
					connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
					metaDataInfo.setUniqueNo(bd.fetchRegTxnId(docForm.getRegNumber1()));
					metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
					metaDataInfo.setType("F");
			        metaDataInfo.setLatestFlag("L");
					//metaDataInfo.setType("R");
					String guid = GUIDGenerator.getGUID();
					String downloadPath = pr.getValue("igrs_upload_path");
			
					String EstampPath = downloadPath + File.separator + guid;
		
					File folder = new File(EstampPath.toString());
					if (!folder.exists()) {
						System.out.println(folder.toString());
		
						folder.mkdirs();
		
					}
					String tempPath = "";
					if (null==metaDataInfo.getUniqueNo()) {
						throw new Exception();
					}
					if (metaDataInfo.getUniqueNo().equals("")) {
						throw new Exception();
					}
					int result = docOperations.downloadDocument(connDetails,metaDataInfo, EstampPath.toString(),"Estamp");
					File[] listOfFiles = folder.listFiles();
					for (int z = 0; z < listOfFiles.length; z++) {
						tempPath = listOfFiles[z].getPath();
		
					}
					logger.debug("download result---------->" + result);
					 //String filePath=bd.burnRequestIdForPDF( dform.getDsearchdto().getRefId(), tempPath,EstampPath);
					byte bytes[] = DMSUtility.getDocumentBytes(tempPath);
					if (bytes != null) {
						DMSUtility.downloadDocument(response, tempPath, bytes);
					} else {
						
							ActionMessages message = new ActionMessages();
							message.add("message", new ActionMessage("reg.doc.download.failedMsg"));
							saveMessages(request,message);
							
					}
				}
				forwardPage = "docDetailsRevert";
			}else if("downloadLastUpload".equalsIgnoreCase(request.getParameter("actionName"))){
				String pathfile = request.getParameter("pathfile");
				String EfilingUploadPath = docForm.getLastUploadPath();
				//docForm.setFinalDocPath1(EfilingUploadPath);
				
		
		
		
					byte bytes[] = DMSUtility.getDocumentBytes(EfilingUploadPath);
					if (bytes != null) {
							DMSUtility.downloadDocument(response, pathfile, bytes);
							forwardPage="docDetailsRevert";
			
							return mapping.findForward(forwardPage);
					}
			}else if("UPLOAD_FILE".equalsIgnoreCase(request.getParameter("actionName"))){
				
				// Uploads the document -- Start
				docForm.setFileError("");
				docForm.setFileSizeError("");
				String filePath;
				String path="";
				
			  FormFile uploadedFile = docForm.getUploadedDoc();
			  //to check file size
			  int size = uploadedFile.getFileSize();
			  double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			  int MAX_FILE_SIZE = 500;
				double fileSizeInKB = fileSizeInBytes / 1024.0;
			  if(fileSizeInKB > MAX_FILE_SIZE){
				  request.setAttribute("msg","File size is Greater than 500 KB. Please select another file. / फ़ाइल का माप 500 KB से अधिक है। अन्य फाइल चुनें।"); 
				  forwardPage="docDetailsDeclare";
				  docForm.setFileSizeError("Y");
				  return mapping.findForward(forwardPage); 
			  }
			  
		//check for file extension
			  MIMECheck mimeCheck = new MIMECheck();
			  String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
			  //Boolean mime = mimeCheck.validateFileTypeEfile(fileExt);
			  Boolean mime = bd.validateFileTypeEfile(fileExt);
			  
			  //end of file extension
			  
			  
			  if(mime){
			  docForm.setDocName(uploadedFile.getFileName());
			  //PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			  path=pr.getValue("upload.null.void.location");
			  String dateTime = new SimpleDateFormat("_ddMMMyyyy_HH-mm-ss").format(new Date());
			  filePath=path+docForm.getRegNumber()+dateTime;
			  docForm.setFinalDocPath(filePath);
			  
			  File folder = new File(filePath);
			  String fileName="/AdditionalUpload.Pdf";
			  byte[] content =  uploadedFile.getFileData();
			  if (!folder.exists()) {
	              folder.mkdirs();
	           }
			  
			  try{
				  
				  File newFile = new File(filePath, fileName);
				  FileOutputStream fos = new FileOutputStream(newFile);
				   fos.write(content);
                    fos.close();
                    
                    boolean uploadStatus=true;
                    
                    if(uploadStatus==true){
                    	if(language.equalsIgnoreCase("en"))
                    		request.setAttribute("successMsg","Successfully Uploaded.");
                    	else
							request.setAttribute("successMsg","सफलतापूर्वक अपलोड की गई");
                    	forwardPage="docDetailsDeclare";
                    }
                    
                    else{
					 if(language.equalsIgnoreCase("en"))
							request.setAttribute("failureMsg","Not Uploaded Successfully");
						else
							request.setAttribute("failureMsg","सफलतापूर्वक अपलोड नहीं किया गया");
					 forwardPage="docDetailsDeclare";
					}
                    
                    return mapping.findForward(forwardPage);   
                    
			  }
			 catch (Exception ex) {
		        	
	              ex.printStackTrace();
		  }
			  }
			  else{
				  docForm.setFileError("Y");
					  if(language.equalsIgnoreCase("en"))
                    		request.setAttribute("msg","Please Upload a Valid File.File should be in PDF Format.");
                    	else
							request.setAttribute("msg","कृपया एक मान्य फाइल अपलोड करें। फाइल पीडीएफ प्रारूप में होनी चाहिए।");
					  forwardPage="docDetailsDeclare";
                    	 return mapping.findForward(forwardPage); 
				  
			  }
			  
			  // End - uploading
				
			}else if("UPLOAD_FILE1".equalsIgnoreCase(request.getParameter("actionName"))){
				
				// Uploads the document -- Start
				docForm.setFileError1("");
				docForm.setFileSizeError1("");
				String filePath;
				String path="";
				
			  FormFile uploadedFile = docForm.getUploadedDoc1();
			  //to check file size
			  int size = uploadedFile.getFileSize();
			  double fileSizeInBytes = size;
			  int MAX_FILE_SIZE = 500;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
				double fileSizeInKB = fileSizeInBytes / 1024.0;
			  if(fileSizeInKB > MAX_FILE_SIZE){
				  request.setAttribute("msg","File size is Greater than 500 KB. Please select another file. / फ़ाइल का माप 500 KB से अधिक है। अन्य फाइल चुनें।"); 
				  forwardPage="docDetailsRevert";
				  docForm.setFileSizeError1("Y");
				  return mapping.findForward(forwardPage); 
			  }
			  
		//check for file extension
			  MIMECheck mimeCheck = new MIMECheck();
			  String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
			  Boolean mime = bd.validateFileTypeEfile(fileExt);
			  
			  //end of file extension
			  
			  
			  if(mime){
			  docForm.setDocName1(uploadedFile.getFileName());
			  //PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			  path=pr.getValue("upload.null.void.location");
			  String dateTime = new SimpleDateFormat("_ddMMMyyyy_HH-mm-ss").format(new Date());
			  filePath=path+docForm.getRegNumber1()+dateTime;
			  docForm.setFinalDocPath1(filePath);
			  
			  File folder = new File(filePath);
			  String fileName="/AdditionalUpload.Pdf";
			  byte[] content =  uploadedFile.getFileData();
			  if (!folder.exists()) {
	              folder.mkdirs();
	        }
			  
			  try{
				  File newFile = new File(filePath, fileName);
				  FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(content);
                    fos.close();
                    
                    boolean uploadStatus=true;
                    
                    if(uploadStatus==true){
                    	if(language.equalsIgnoreCase("en"))
                    		request.setAttribute("successMsg","Successfully Uploaded.");
                    	else
							request.setAttribute("successMsg","सफलतापूर्वक अपलोड की गई");
                    	forwardPage="docDetailsRevert";
                    }
                    
                    else{
					 if(language.equalsIgnoreCase("en"))
							request.setAttribute("failureMsg","Not Uploaded Successfully");
						else
							request.setAttribute("failureMsg","सफलतापूर्वक अपलोड नहीं किया गया");
					 forwardPage="docDetailsRevert";
					}
                    
                    return mapping.findForward(forwardPage);   
                    
			  }
			 catch (Exception ex) {
		        	
	              ex.printStackTrace();
		  }
			  }
			  else{
				  docForm.setFileError1("Y");
					  if(language.equalsIgnoreCase("en"))
                    		request.setAttribute("msg","Please Upload a Valid File.File should be in PDF Format.");
                    	else
							request.setAttribute("msg","कृपया एक मान्य फाइल अपलोड करें। फाइल पीडीएफ प्रारूप में होनी चाहिए।");
					  forwardPage="docDetailsRevert";
                    	 return mapping.findForward(forwardPage); 
				  
			  }
			  
			  // End - uploading
				
			}else if("downloadUploadedForm".equalsIgnoreCase(request.getParameter("actionName"))){
				
				String pathfile = request.getParameter("pathfile");
				String EfilingUploadPath = docForm.getFinalDocPath()+pathfile;
				//docForm.setFinalDocPath(EfilingUploadPath);
				
		
		
		
					byte bytes[] = DMSUtility.getDocumentBytes(EfilingUploadPath);
					if (bytes != null) {
							DMSUtility.downloadDocument(response, pathfile, bytes);
							forwardPage="docDetailsRevert";
			
							return mapping.findForward(forwardPage);
					}
			}else if("downloadUploadedForm1".equalsIgnoreCase(request.getParameter("actionName"))){
				
				String pathfile = request.getParameter("pathfile");
				String EfilingUploadPath = docForm.getFinalDocPath1()+pathfile;
				//docForm.setFinalDocPath1(EfilingUploadPath);
				
		
		
		
					byte bytes[] = DMSUtility.getDocumentBytes(EfilingUploadPath);
					if (bytes != null) {
							DMSUtility.downloadDocument(response, pathfile, bytes);
							forwardPage="docDetailsRevert";
			
							return mapping.findForward(forwardPage);
					}
			}else if("declareNullAndVoid".equalsIgnoreCase(request.getParameter("actionName"))){
				boolean flag = bd.declareNullAndVoid(docForm, userId);
				
				if(flag){
					forwardPage = "successNullAndVoid";
				}
				
			}else if("revertNullAndVoid".equalsIgnoreCase(request.getParameter("actionName"))){
				 revertFlag = true;
				boolean flag = bd.revertNullAndVoid(docForm, userId,revertFlag);
				if(flag){
					forwardPage = "revertNullAndVoid";
				}
				
			}else if("RESET_ACTION".equalsIgnoreCase(request.getParameter("actionName"))){
				 
				 docForm.setError("");
				 docForm.setRegNumber("");
				 docForm.setRegId("");
				 forwardPage = "searchDeclareNullVoidPage";
				 return mapping.findForward(forwardPage);
					
			}else if("RESET_ACTION_REVERT".equalsIgnoreCase(request.getParameter("actionName"))){
					 
					 docForm.setError1("");
					 docForm.setRegNumber1("");
					 docForm.setRegId1("");
					 forwardPage = "searchRevertNullVoidPage";
					 return mapping.findForward(forwardPage);
						
			}
			
		}catch(Exception ex){
			
		}
		return mapping.findForward(forwardPage);
	}
	
	

}
