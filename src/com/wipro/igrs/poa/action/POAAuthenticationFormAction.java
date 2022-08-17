/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.poa.action;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.poa.bo.POAAuthenticationFormBO;
import com.wipro.igrs.poa.dao.POAAuthenticationFormDAO;
import com.wipro.igrs.poa.dto.POAAuthenticationFormDTO;
import com.wipro.igrs.poa.form.POAAuthenticationForm;
//Added new common class for validation and mime check
import com.wipro.igrs.common.mime.MIMECheck;


/* class added by SHRUTI*/

public class POAAuthenticationFormAction extends BaseAction {
    
    private static Logger logger = (Logger) Logger.getLogger(POAAuthenticationFormAction.class);
	
    String forwardpage;
	POAAuthenticationFormBO poaBO=null;
	POAAuthenticationFormDTO poaDTO=new POAAuthenticationFormDTO();
	
	public ActionForward execute(ActionMapping mapping,
			                     ActionForm form,
			                     HttpServletRequest request,
			                     HttpServletResponse response,HttpSession session)
	                         throws IOException,Exception {
		String UserId = null;
		if(session.getAttribute("UserId").toString() == null | session.getAttribute("UserId").toString().equalsIgnoreCase(""))
			UserId = "ADMIN";
		else
			UserId = session.getAttribute("UserId").toString();
		
		POAAuthenticationForm poaForm 	= (POAAuthenticationForm)form;
		 poaBO=new POAAuthenticationFormBO();
		String page=request.getParameter("page");
		if("POAForm".equals(page)){
			//poaForm=new POAAuthenticationForm();
		poaDTO= new POAAuthenticationFormDTO();
		
		forwardpage="createPOAForm";
		}	
		if(form!=null)
		{
			//poaForm.getPoaDTO().setCurrentYear(poaBO.getCurrentYear());
			poaDTO.setCurrentYear(poaBO.getCurrentYear());
			poaForm.setCurrentDate(poaBO.getSystemDate());
			logger.info("CURRENT YEAR--------------------********************"+poaForm.getPoaDTO().getCurrentYear());
			poaForm.setPoaTypeList(poaBO.getpoaTypeList());
			poaForm.setEcodePoaTypeList(poaBO.getecodePoaTypeList());
			poaForm.setPoaDTO(poaForm.getPoaDTO());
			poaDTO.setSr_UserId(UserId);
			poaDTO.setSrname(poaBO.getSRName(UserId));
			ArrayList sroDetails= (ArrayList)poaBO.getSROName(poaDTO);
			for(int i=0;i<sroDetails.size();i++)
			{
				poaDTO.setSroId(sroDetails.get(0).toString());
				logger.info("********-------------------"+poaDTO.getSroId());
				poaDTO.setSroName(sroDetails.get(1).toString());
				logger.info("********--------------------"+poaDTO.getSroName());
			}
			
			poaDTO.setDoa(poaBO.getSystemDate());
			/*String _countryId=poaForm.getPoaDTO().getAwrdrcountryId();
			String _stateId=poaForm.getPoaDTO().getAwrdrstateId();
			String _countryId1=poaForm.getPoaDTO().getAcptrcountryId();
			String _stateId1=poaForm.getPoaDTO().getAcptrstateId();
			poaForm.setAwrdrcountryList(poaBO.getawrdrcountryList());
			poaForm.setAcptrcountryList(poaBO.getacptrcountryList());*/
			poaForm.setPoaDTO(poaDTO);
			
			
			if("PoASearch".equalsIgnoreCase(request.getParameter("login"))){
				poaForm.setPoaAcceptorList(new ArrayList());
				poaForm.setPoaApplicntDetlsList(new ArrayList());
				poaForm.setPoaAwardeeDetlsList(new ArrayList());
				poaForm.setPoaCommonDetlsList(new ArrayList());
				poaForm.setActionType("");
				 forwardpage="searchDuration1";
				 
			}
			
			//********************************ADDED BY SIMRAN ---FOR POA VIEW**********************************//
			if(request.getParameter("poaId")!= null)
			{
				String poaId = (String)request.getParameter("poaId");
				//logger.debug("POAID******************"+poaId);
				ArrayList poaApplicantDetls = poaBO.getPOAApplicantDetlsForView(poaId);
				poaForm.setPoaApplicntDetlsList(poaApplicantDetls);
				ArrayList poaAwardeeDetls = poaBO.getPOAAwardeeDetlsForView(poaId);
				poaForm.setPoaAwardeeDetlsList(poaAwardeeDetls);
				ArrayList poaAcceptorDetls = poaBO.getPOAAcceptorDetlsForView(poaId);
				poaForm.setPoaAcceptorList(poaAcceptorDetls);
				ArrayList poaCommonDetls = poaBO.getPOACommonDetlsForView(poaId);
				poaForm.setPoaCommonDetlsList(poaCommonDetls);
				poaForm.setActionType("");
				forwardpage = "poaListView2";
			}
			
			
			
			
			
			
			
			//**************************************************************************************************//
			
			if(poaForm.getActionType()!=null){
			/*if(poaForm.getActionType().equalsIgnoreCase("stateAction")){
			poaForm.setAwrdrstateList(poaBO.getawrdrstateList(_countryId));
			}
			if(poaForm.getActionType().equalsIgnoreCase("districtAction")){
			poaForm.setAwrdrdistrictList(poaBO.getawrdrdistrictList(_stateId));
			}
			if(poaForm.getActionType().equalsIgnoreCase("stateAction1")){
			poaForm.setAcptrstateList(poaBO.getacptrstateList(_countryId1));
			}
			if(poaForm.getActionType().equalsIgnoreCase("districtAction1")){
			poaForm.setAcptrdistrictList(poaBO.getacptrdistrictList(_stateId1));
			}*/
				if(poaForm.getActionType().equalsIgnoreCase("poaViewDuration"))
			{
					 logger.debug("Your selected search duration ");
					 ArrayList poaList = new ArrayList();
					 poaDTO=poaForm.getPoaDTO();
					 poaList = poaBO.getPoaViewDetails(poaDTO);
					 poaDTO.setPoaList(poaList);
					 poaForm.setPoaDTO(poaDTO);
					 request.setAttribute("poaList",poaList);
					 //poaForm.getPoaDto().setAction("poaViewList");
					// request.setAttribute("poaForm", poaForm);
					 forwardpage="poaListView1";
					 //return mapping.findForward("poaListView1");
				 }
			if("poaViewList1".equalsIgnoreCase(poaForm.getActionType())){
				   logger.debug("POA ACTION SEACH STAT--------->  ");
				   String poaId = (String)request.getParameter("poaId");
				   //logger.debug("The Selected Id is -->  " + poaId);
				   ArrayList list = poaBO.getPoaDetails(poaId);				
				   poaDTO.setPoaList(list);
				  // logger.debug(" search list in action--<>"+poaDTO.getPoaList());
				   poaForm.setPoaDTO(poaDTO);
				   session.setAttribute("poaForm", poaForm);
				   logger.debug("ArrayList ------------>   "  + poaDTO.getPoaList().size());
				   return mapping.findForward("search");
				}
			if(poaForm.getActionType().equalsIgnoreCase("Submit"))
			{
				logger.info("INSIDE SUBMIT>>>>");
				poaDTO=poaForm.getPoaDTO();
				String poano=poaBO.getInsertionOfPOADetails(poaDTO);
				poaDTO.setPoaAuthNo(poano);
				logger.info("INSERTION success>>>>>>>");
					//poaForm.setPoaDTO(poaDTO);
					//logger.info("POA AUTH NUMBER------"+poaDTO.getPoaAuthNo());
					poaForm.setPoaAuthNo(poano);
					
					forwardpage="poaFormSuccess";
			}
			if(poaForm.getActionType().equalsIgnoreCase("UploadAwrdrSignature"))
			{
				ArrayList errorList=new ArrayList(); 
				try {
					FormFile uploadedFile = poaDTO.getAwrdrSignature();
					if ("".equals(uploadedFile.getFileName())) 
					{
						errorList.add("Invalid file selection. Please try again.");
					}
					/*String fileExt = getFileExtension(uploadedFile.getFileName());
					AuditInspectionRule rule = new AuditInspectionRule();
					rule.validateFileType(fileExt);*/
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
					double fileSizeInMB = fileSizeInKB / 1024.0;
					DecimalFormat decim = new DecimalFormat("#.##");
					Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
					String signatureSize="("+fileSize+"MB)";
					// Added new code for MIME type common - Rahul
					MIMECheck mimeCheck = new MIMECheck();
					String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
			        Boolean mime = mimeCheck.validateMIMEAndJPGFileType(uploadedFile);  // common code to check mime type and validation.
			        
					if (!mime) {
						   errorList.add("Invalid file type. Please select another file.");
						   request.setAttribute("errorsList", errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.");
							request.setAttribute("errorsList", errorList);
						} else {
						poaDTO.setAwrdrSignatureName(uploadedFile.getFileName());
							/*formDTO.setSignatureName(uploadedFile.getFileName());
							formDTO.setSignatureContents(uploadedFile.getFileData());
							formDTO.setSignatureSize(signatureSize);
							formDTO.setSignCheck("signloded");*/
						}
				} 
				}catch (Exception e) {
					errorList.add("Unable to upload file. Please try again.");
					request.setAttribute("errorsList", errorList);
				}
			forwardpage = "createPOAForm";   	
			}
			if(poaForm.getActionType().equalsIgnoreCase("removeAwrdrSign"))
			{
				try {
					poaDTO.setAwrdrSignatureName("");
					poaForm.setPoaDTO(poaDTO);
					forwardpage = "createPOAForm";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(poaForm.getActionType().equalsIgnoreCase("removeAwrdrPhoto"))
			{
				try {
					poaDTO.setAwrdrPhotoName("");
					poaForm.setPoaDTO(poaDTO);
					forwardpage = "createPOAForm";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(poaForm.getActionType().equalsIgnoreCase("removeAwrdrThumb"))
			{
				try {
					poaDTO.setAwrdrThumbName("");
					poaForm.setPoaDTO(poaDTO);
					forwardpage = "createPOAForm";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(poaForm.getActionType().equalsIgnoreCase("removeAcptrSign"))
			{
				try {
					poaDTO.setAcptrSignatureName("");
					poaForm.setPoaDTO(poaDTO);
					forwardpage = "createPOAForm";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(poaForm.getActionType().equalsIgnoreCase("removeAcptrPhoto"))
			{
				try {
					poaDTO.setAcptrPhotoName("");
					poaForm.setPoaDTO(poaDTO);
					forwardpage = "createPOAForm";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(poaForm.getActionType().equalsIgnoreCase("removeAcptrThumb"))
			{
				try {
					poaDTO.setAcptrThumbName("");
					poaForm.setPoaDTO(poaDTO);
					forwardpage = "createPOAForm";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(poaForm.getActionType().equalsIgnoreCase("UploadAwrdrPhoto"))
			{
				ArrayList errorList=new ArrayList();
				try {
					FormFile uploadedFile = poaDTO.getAwrdrPhoto();
					if ("".equals(uploadedFile.getFileName())) {
						errorList.add("Invalid file selection. Please try again.");
					}
					/*String fileExt = getFileExtension(uploadedFile.getFileName());
					AuditInspectionRule rule = new AuditInspectionRule();
					rule.validateFileType(fileExt);*/
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
					double fileSizeInMB = fileSizeInKB / 1024.0;
					DecimalFormat decim = new DecimalFormat("#.##");
					Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
					String photoSize="("+fileSize+"MB)";
					// Added new code for MIME type common - Rahul
					MIMECheck mimeCheck = new MIMECheck();
					String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
			        Boolean mime = mimeCheck.validateMIMEAndJPGFileType(uploadedFile);  // common code to check mime type and validation.

					if (!mime) {
						errorList.add("Invalid file type. Please select another file.");
						  	
						request.setAttribute("errorsList", errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.");
							request.setAttribute("errorsList", errorList);
						} else {
							poaDTO.setAwrdrPhotoName(uploadedFile.getFileName());
							/*formDTO.setDocumentName(uploadedFile.getFileName());
							formDTO.setDocContents(uploadedFile.getFileData());
							formDTO.setPhotoSize(photoSize);
							formDTO.setPhotoCheck("phloded");*/
						}
					}
				} catch (Exception e) {
					errorList.add("Unable to upload file. Please try again.");
					request.setAttribute("errorsList", errorList);
				}

			forwardpage = "createPOAForm";   
		
		
			}
			if(poaForm.getActionType().equalsIgnoreCase("UploadAwrdrThumb"))
			{
				ArrayList errorList=new ArrayList();
				try {
					FormFile uploadedFile = poaDTO.getAwrdrThumb();
					if ("".equals(uploadedFile.getFileName())) {
						errorList.add("Invalid file selection. Please try again.");
					}
//					formDTO.setThunmbName(null);
//					formDTO.setThumbContents(null);
					/*String fileExt = getFileExtension(uploadedFile.getFileName());
					AuditInspectionRule rule = new AuditInspectionRule();
					rule.validateFileType(fileExt);*/
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
					double fileSizeInMB = fileSizeInKB / 1024.0;
					DecimalFormat decim = new DecimalFormat("#.##");
					Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
					String thumbSize="("+fileSize+"MB)";
					// Added new code for MIME type common - Rahul
					MIMECheck mimeCheck = new MIMECheck();
					String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
			        Boolean mime = mimeCheck.validateMIMEAndJPGFileType(uploadedFile);  // common code to check mime type and validation.					
					if (!mime) {
						   errorList.add("Invalid file type. Please select another file.");
						   		
						request.setAttribute("errorsList", errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.");
							request.setAttribute("errorsList", errorList);
						} else {
							poaDTO.setAwrdrThumbName(uploadedFile.getFileName());
							/*formDTO.setThunmbName(uploadedFile.getFileName());
							formDTO.setThumbContents(uploadedFile.getFileData());
							formDTO.setThumbSize(thumbSize);
							formDTO.setThumbCheck("thloded");*/
						}
					}
				} catch (Exception e) {
					errorList.add("Unable to upload file. Please try again.");
					request.setAttribute("errorsList", errorList);
				}
				forwardpage = "createPOAForm"; 
			} 
		
			if(poaForm.getActionType().equalsIgnoreCase("UploadAcptrSignature"))
			{
				ArrayList errorList=new ArrayList(); 
				try {
					FormFile uploadedFile = poaDTO.getAcptrSignature();
					if ("".equals(uploadedFile.getFileName())) 
					{
						errorList.add("Invalid file selection. Please try again.");
					}
					/*String fileExt = getFileExtension(uploadedFile.getFileName());
					AuditInspectionRule rule = new AuditInspectionRule();
					rule.validateFileType(fileExt);*/
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
					double fileSizeInMB = fileSizeInKB / 1024.0;
					DecimalFormat decim = new DecimalFormat("#.##");
					Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
					String signatureSize="("+fileSize+"MB)";
					// Added new code for MIME type common - Rahul
					MIMECheck mimeCheck = new MIMECheck();
					String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
			        Boolean mime = mimeCheck.validateMIMEAndJPGFileType(uploadedFile);  // common code to check mime type and validation.

					if (!mime) {
						   errorList.add("Invalid file type. Please select another file.");
						   request.setAttribute("errorsList", errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.");
							request.setAttribute("errorsList", errorList);
						} else {
						poaDTO.setAcptrSignatureName(uploadedFile.getFileName());
							/*formDTO.setSignatureName(uploadedFile.getFileName());
							formDTO.setSignatureContents(uploadedFile.getFileData());
							formDTO.setSignatureSize(signatureSize);
							formDTO.setSignCheck("signloded");*/
						}
				} 
				}catch (Exception e) {
					errorList.add("Unable to upload file. Please try again.");
					request.setAttribute("errorsList", errorList);
				}
			forwardpage = "createPOAForm";   	
			}
			if(poaForm.getActionType().equalsIgnoreCase("UploadAcptrPhoto"))
			{
				ArrayList errorList=new ArrayList();
				try {
					FormFile uploadedFile = poaDTO.getAcptrPhoto();
					if ("".equals(uploadedFile.getFileName())) {
						errorList.add("Invalid file selection. Please try again.");
					}
					/*String fileExt = getFileExtension(uploadedFile.getFileName());
					AuditInspectionRule rule = new AuditInspectionRule();
					rule.validateFileType(fileExt);*/
					int size = uploadedFile.getFileSize();
					double fileSizeInBytes = size;
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
					double fileSizeInMB = fileSizeInKB / 1024.0;
					DecimalFormat decim = new DecimalFormat("#.##");
					Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
					String photoSize="("+fileSize+"MB)";
					// Added new code for MIME type common - Rahul
					MIMECheck mimeCheck = new MIMECheck();
					String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
			        Boolean mime = mimeCheck.validateMIMEAndJPGFileType(uploadedFile);  // common code to check mime type and validation.

					if (!mime) {
						errorList.add("Invalid file type. Please select another file.");
						  	
						request.setAttribute("errorsList", errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
							errorList.add("File size is Greater than 10 MB. Please select another file.");
							request.setAttribute("errorsList", errorList);
						} else {
							poaDTO.setAcptrPhotoName(uploadedFile.getFileName());
							/*formDTO.setDocumentName(uploadedFile.getFileName());
							formDTO.setDocContents(uploadedFile.getFileData());
							formDTO.setPhotoSize(photoSize);
							formDTO.setPhotoCheck("phloded");*/
						}
					}
				} catch (Exception e) {
					errorList.add("Unable to upload file. Please try again.");
					request.setAttribute("errorsList", errorList);
				}

			forwardpage = "createPOAForm";   
		
			}
			if(poaForm.getActionType().equalsIgnoreCase("UploadAcptrThumb"))
			{ 
				ArrayList errorList=new ArrayList();
						try {
							FormFile uploadedFile = poaDTO.getAcptrThumb();
							if ("".equals(uploadedFile.getFileName())) {
								errorList.add("Invalid file selection. Please try again.");
							}
//							formDTO.setThunmbName(null);
//							formDTO.setThumbContents(null);
							/*String fileExt = getFileExtension(uploadedFile.getFileName());
							AuditInspectionRule rule = new AuditInspectionRule();
							rule.validateFileType(fileExt);*/
							int size = uploadedFile.getFileSize();
							double fileSizeInBytes = size;
							// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
							double fileSizeInKB = fileSizeInBytes / 1024.0;
							// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
							double fileSizeInMB = fileSizeInKB / 1024.0;
							DecimalFormat decim = new DecimalFormat("#.##");
							Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
							String thumbSize="("+fileSize+"MB)";
							// Added new code for MIME type common - Rahul
	    					MIMECheck mimeCheck = new MIMECheck();
	    					String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
					        Boolean mime = mimeCheck.validateMIMEAndJPGFileType(uploadedFile);  // common code to check mime type and validation.							
							if (!mime) {
								   errorList.add("Invalid file type. Please select another file.");
								   		
								request.setAttribute("errorsList", errorList);
							} else {
								if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
									errorList.add("File size is Greater than 10 MB. Please select another file.");
									request.setAttribute("errorsList", errorList);
								} else {
									poaDTO.setAcptrThumbName(uploadedFile.getFileName());
									/*formDTO.setThunmbName(uploadedFile.getFileName());
									formDTO.setThumbContents(uploadedFile.getFileData());
									formDTO.setThumbSize(thumbSize);
									formDTO.setThumbCheck("thloded");*/
								}
							}
						} catch (Exception e) {
							errorList.add("Unable to upload file. Please try again.");
							request.setAttribute("errorsList", errorList);
						}
					forwardpage = "createPOAForm";   
			} 
			}
			
			
					}
		request.setAttribute("poaForm", poaForm);
		return mapping.findForward(forwardpage);
	}
		/*private String getFileExtension(String fileName) {
			try {
				int pos = fileName.lastIndexOf(".");
				String strExt = fileName.substring(pos + 1, fileName.length());
				return strExt;
			} catch (Exception e) {
			}
			return "";
		}*/

		private String removeFile(String fileName) {
			String filePath = "D:/upload/";
			File newFile = new File(filePath + fileName);
			newFile.delete();

			return "";
		}
}//close Action class
