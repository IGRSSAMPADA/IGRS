/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   CommonAction.java
 * Author      :   Imran Shaik
 * Description :   Represents the Common Action Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.newreginit.action;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.PropertiesFileReader;

import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.dto.RegCompletDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.newreginit.form.RegCompletionForm;
import com.wipro.igrs.newreginit.rule.RegInitRule;
// Added new common class for validation and mime check
import com.wipro.igrs.common.mime.MIMECheck;

public class UploadAction extends  BaseAction {
	private  Logger logger = 
		(Logger) Logger.getLogger(CommonAction.class);
	//static private int count = 0;
   //  private HashMap map =null;
   //  private HashMap map2 =null;
    
  
    boolean bol = true;
   // static private String key = "key";
    //static private int keyCount=0;
   
	@SuppressWarnings("unchecked")
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response, HttpSession session ) throws Exception {
			
		
			// HttpSession session = request.getSession();
		
			 //if(session.getAttribute("forwardPath")==null)
			 //session.setAttribute("forwardPath", "./regInit.do");
			// session.setAttribute("modName","Registration Process");
			// session.setAttribute("fnName","Initiation");
			String forward="";
			RegCommonBO commonBo = new RegCommonBO();
			
			RegCommonForm regForm;
			RegCompletionForm eForm;
						
			/*String languageLocale="hi";
			if(session.getAttribute("languageLocale")!=null){
				languageLocale=(String)session.getAttribute("languageLocale");
			}*/
				
			String fromParam;
			String formName;
			String actionName;
			   ArrayList errorList = new ArrayList();
			   ArrayList errorListAddUpload = new ArrayList();
			if(request.getParameter("fromParam")!=null){
				fromParam=request.getParameter("fromParam");
				System.out.println(fromParam);
			}else{
				fromParam="";
			}
			
			
			if(fromParam.equalsIgnoreCase("01") || fromParam.equalsIgnoreCase("02")){
				
				if(fromParam.equalsIgnoreCase("01")){
			    regForm = (RegCommonForm)form;
			    eForm=new RegCompletionForm();
			    //System.out.println("flag is"+regForm.getFromAdjudication());
			    if(regForm.getFromAdjudication()==1){
			    	session.setAttribute("fnName","Adjudication");
			    }
			    formName = regForm.getFormName();
				actionName = regForm.getActionName();
				}
				else{
					eForm=    (RegCompletionForm) form;
					/*if(eForm.getIGRS_DATA_AVLBL()!=null && eForm.getIGRS_DATA_AVLBL().equalsIgnoreCase("N")){
						eForm.getRegcompletDto().setMpcstSuccessFlag(null);
					}*/
					regForm=new RegCommonForm();
					  //System.out.println("flag iss "+eForm.getRegInitForm().getFromAdjudication());
					if(eForm.getRegInitForm().getFromAdjudication()==1){
						//eForm.setFromAdjudication(1);
						//eForm.setFromPropertyForm(1);
				    	session.setAttribute("fnName","Adjudication");
				    }
					formName = eForm.getFormName();
					actionName = eForm.getActionName();
				}
			}
			else{
				regForm=new RegCommonForm();
				eForm=new RegCompletionForm();
				
				formName ="";
				actionName ="";
				
			}
			
				

			logger.debug("formName:-"+formName);
			logger.debug("actionName:-"+actionName);
			
			 
			 
                if (RegInitConstant.UPLOAD_FILE.equals(actionName)) {
                	
    				try {
    					FormFile uploadedFile = regForm.getCertificate();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}
    					RegInitRule rule = new RegInitRule();
    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 			   
    			//	if (rule.isError() || size <= 0)
    				if (!mime)
    				    {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setDocumentName(uploadedFile.getFileName());
    							regForm.setDocContents(uploadedFile.getFileData());
    							regForm.setDocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setFilePath(RegInitConstant.FILE_NAME_CERTIFICATE+fileExt);
    							regForm.setPartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setUploadType(RegInitConstant.FILE_NAME_CERTIFICATE+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                
                
                
                if (RegInitConstant.UPLOAD_FILE_TRNS.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getCertificateTrns();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    				//	String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    				   					//if (rule.isError() || size <= 0)
				        if (!mime)	{
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setDocumentNameTrns(uploadedFile.getFileName());
    							regForm.setDocContentsTrns(uploadedFile.getFileData());
    							regForm.setDocumentSizeTrns(photoSize);
    							regForm.setFilePathTrns(RegInitConstant.FILE_NAME_CERTIFICATE+fileExt);
    							regForm.setPartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setUploadTypeTrns(RegInitConstant.FILE_NAME_CERTIFICATE+fileExt);
    							
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
    				request.setAttribute("deedId", regForm.getDeedID());
    				}else{
    					request.setAttribute("deedId", 0);
    				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
    				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
    				request.setAttribute("roleId", regForm.getPartyType());
    				}else{
    					request.setAttribute("roleId", 0);
    				}
    				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
        			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
    				}else{
    					request.setAttribute("roleIdTrns", 0);
    				}
    			} 
                
                if (RegInitConstant.UPLOAD_PHOTO_PROOF.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU2();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    				//	rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    				
    					   
    					if (!mime) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU2DocumentName(uploadedFile.getFileName());
    							regForm.setU2DocContents(uploadedFile.getFileData());
    							regForm.setU2DocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU2FilePath(RegInitConstant.FILE_NAME_PHOTO_PROOF+fileExt);
    							regForm.setU2PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU2UploadType(RegInitConstant.FILE_NAME_PHOTO_PROOF+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                
                if (RegInitConstant.UPLOAD_PHOTO_PROOF_TRNS.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU2Trns();
    					//logger.debug("upload file path****************-->"+uploadedFile.toString());
    					//logger.debug("upload file path****************-->"+uploadedFile.);
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    					//if (rule.isError() || size <= 0)
				        if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU2DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU2DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU2DocumentSizeTrns(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU2FilePathTrns(RegInitConstant.FILE_NAME_PHOTO_PROOF+fileExt);
    							regForm.setU2PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU2UploadTypeTrns(RegInitConstant.FILE_NAME_PHOTO_PROOF+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_NOTARIZED_AFFIDAVIT_EXECUTANT.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU3();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    				//	rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    					
    					//if (rule.isError() || size <= 0)
    						if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU3DocumentName(uploadedFile.getFileName());
    							regForm.setU3DocContents(uploadedFile.getFileData());
    							regForm.setU3DocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU3FilePath(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_EXECUTANT+fileExt);
    							regForm.setU3PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU3UploadType(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_EXECUTANT+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_NOTARIZED_AFFIDAVIT_EXECUTANT_TRNS.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU3Trns();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    				    					//if (rule.isError() || size <= 0)
				        if (!mime)	{
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU3DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU3DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU3DocumentSizeTrns(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU3FilePathTrns(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_EXECUTANT+fileExt);
    							regForm.setU3PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU3UploadTypeTrns(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_EXECUTANT+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_EXECUTANT_PHOTO.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU4();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    				//.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    				
    					   
    					//if (rule.isError() || size <= 0)
    						if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU4DocumentName(uploadedFile.getFileName());
    							regForm.setU4DocContents(uploadedFile.getFileData());
    							regForm.setU4DocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU4FilePath(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO+fileExt);
    							regForm.setU4PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU4UploadType(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                
                if (RegInitConstant.UPLOAD_EXECUTANT_PHOTO_TRNS.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU4Trns();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    					
    					//if (rule.isError() || size <= 0)
				        if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU4DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU4DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU4DocumentSizeTrns(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU4FilePathTrns(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO+fileExt);
    							regForm.setU4PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU4UploadTypeTrns(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_EXECUTANT_PHOTO_2.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU7();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    				
    					   
    					//if (rule.isError() || size <= 0) 
    						if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU7DocumentName(uploadedFile.getFileName());
    							regForm.setU7DocContents(uploadedFile.getFileData());
    							regForm.setU7DocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU7FilePath(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO+fileExt);
    							regForm.setU7PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU7UploadType(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_EXECUTANT_PHOTO_2_TRNS.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU7Trns();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    					
    					//if (rule.isError() || size <= 0) 
				        if (!mime)	{
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU7DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU7DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU7DocumentSizeTrns(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU7FilePathTrns(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO+fileExt);
    							regForm.setU7PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU7UploadTypeTrns(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_NOTARIZED_AFFIDAVIT_ATTORNEY.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU5();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    				
    					   
    					//if (rule.isError() || size <= 0)
    						if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU5DocumentName(uploadedFile.getFileName());
    							regForm.setU5DocContents(uploadedFile.getFileData());
    							regForm.setU5DocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU5FilePath(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_ATTORNEY+fileExt);
    							regForm.setU5PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU5UploadType(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_ATTORNEY+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_NOTARIZED_AFFIDAVIT_ATTORNEY_TRNS.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU5Trns();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    					
    					
    				
    					   
    					//if (rule.isError() || size <= 0)
				        if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU5DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU5DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU5DocumentSizeTrns(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU5FilePathTrns(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_ATTORNEY+fileExt);
    							regForm.setU5PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU5UploadTypeTrns(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_ATTORNEY+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_ATTORNEY_PHOTO.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU6();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    				
    					   
    					//if (rule.isError() || size <= 0)
    						if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU6DocumentName(uploadedFile.getFileName());
    							regForm.setU6DocContents(uploadedFile.getFileData());
    							regForm.setU6DocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU6FilePath(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO+fileExt);
    							regForm.setU6PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU6UploadType(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_ATTORNEY_PHOTO_TRNS.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU6Trns();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    					
    					//if (rule.isError() || size <= 0) 
				        if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU6DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU6DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU6DocumentSizeTrns(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU6FilePathTrns(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO+fileExt);
    							regForm.setU6PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU6UploadTypeTrns(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_CLAIMANT_PHOTO.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU8();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    				
    					   
    					//if (rule.isError() || size <= 0) 
    						if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU8DocumentName(uploadedFile.getFileName());
    							regForm.setU8DocContents(uploadedFile.getFileData());
    							regForm.setU8DocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU8FilePath(RegInitConstant.FILE_NAME_CLAIMANT_PHOTO+fileExt);
    							regForm.setU8PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU8UploadType(RegInitConstant.FILE_NAME_CLAIMANT_PHOTO+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_CLAIMANT_PHOTO_TRNS.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU8Trns();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    					
    					//if (rule.isError() || size <= 0) 
				        if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU8DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU8DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU8DocumentSizeTrns(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU8FilePathTrns(RegInitConstant.FILE_NAME_CLAIMANT_PHOTO+fileExt);
    							regForm.setU8PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU8UploadTypeTrns(RegInitConstant.FILE_NAME_CLAIMANT_PHOTO+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_ATTORNEY_PHOTO_2.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU9();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    				
    					   
    					//if (rule.isError() || size <= 0) 
    						if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU9DocumentName(uploadedFile.getFileName());
    							regForm.setU9DocContents(uploadedFile.getFileData());
    							regForm.setU9DocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU9FilePath(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO+fileExt);
    							regForm.setU9PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU9UploadType(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_ATTORNEY_PHOTO_2_TRNS.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU9Trns();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    					
    					//if (rule.isError() || size <= 0)
				        if (!mime)
				        			{ errorList.add(RegInitConstant.
    								lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU9DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU9DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU9DocumentSizeTrns(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU9FilePathTrns(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO+fileExt);
    							regForm.setU9PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU9UploadTypeTrns(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO+fileExt);
    							//regForm.setPhotoCheck("phloded");
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			}
                if (RegInitConstant.UPLOAD_PAN_EXECUTANT.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU10();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    					
    					//if (rule.isError() || size <= 0) 
    						if (!mime)
    							{
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU10DocumentName(uploadedFile.getFileName());
    							regForm.setU10DocContents(uploadedFile.getFileData());
    							regForm.setU10DocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU10FilePath(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							regForm.setU10PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU10UploadType(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							//regForm.setPhotoCheck("phloded");
    							
    							//BELOW STATEMENTS ADDED FOR RESOLVING BUG NO. 1615 
    							regForm.setU11DocumentName(uploadedFile.getFileName());
    							regForm.setU11DocContents(uploadedFile.getFileData());
    							regForm.setU11DocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU11FilePath(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							regForm.setU11PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU11UploadType(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							
    							
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_PAN_EXECUTANT_TRNS.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU10Trns();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    					//if (rule.isError() || size <= 0) 
				        if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU10DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU10DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU10DocumentSizeTrns(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU10FilePathTrns(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							regForm.setU10PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU10UploadTypeTrns(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							//regForm.setPhotoCheck("phloded");
    							
    							
    							//BELOW STATEMENTS ADDED FOR RESOLVING BUG NO. 1615 
    							regForm.setU11DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU11DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU11DocumentSizeTrns(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU11FilePathTrns(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							regForm.setU11PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU11UploadTypeTrns(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_PAN_CLAIMANT.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU11();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    
    					//if (rule.isError() || size <= 0) 
    						if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU11DocumentName(uploadedFile.getFileName());
    							regForm.setU11DocContents(uploadedFile.getFileData());
    							regForm.setU11DocumentSize(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU11FilePath(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							regForm.setU11PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU11UploadType(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							//regForm.setPhotoCheck("phloded");
    							
    							
    							//BELOW STATEMENTS ADDED FOR RESOLVING BUG NO. 1615
    							regForm.setU10DocumentName(uploadedFile.getFileName());
    							regForm.setU10DocContents(uploadedFile.getFileData());
    							regForm.setU10DocumentSize(photoSize);
    							regForm.setU10FilePath(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							regForm.setU10PartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU10UploadType(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							
    							
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 
                if (RegInitConstant.UPLOAD_PAN_CLAIMANT_TRNS.equals(actionName)) {
    				try {
    					FormFile uploadedFile = regForm.getU11Trns();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePartyRelated(fileExt);
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
						 // new code finish. 
    				
    					//if (rule.isError() || size <= 0) 
				        if (!mime)	{
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							regForm.setU11DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU11DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU11DocumentSizeTrns(photoSize);
    							//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
    							regForm.setU11FilePathTrns(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							regForm.setU11PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU11UploadTypeTrns(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							//regForm.setPhotoCheck("phloded");
    							
    							//BELOW STATEMENTS ADDED FOR RESOLVING BUG NO. 1615
    							regForm.setU10DocumentNameTrns(uploadedFile.getFileName());
    							regForm.setU10DocContentsTrns(uploadedFile.getFileData());
    							regForm.setU10DocumentSizeTrns(photoSize);
    							regForm.setU10FilePathTrns(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    							regForm.setU10PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
    							regForm.setU10UploadTypeTrns(RegInitConstant.FILE_NAME_PAN_FORM_60+fileExt);
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
    				if(regForm.getInstID()!=0){
        				request.setAttribute("instId", regForm.getInstID());
        				}else{
        					request.setAttribute("instId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
        			//mapping.setForward("reginit.do");
    			} 

            	String clrFlag = "N";

				clrFlag = commonBo.getClrFlag(eForm.getPropertyId());
				String valTxnId = commonBo.getValTxnID(eForm.getPropertyId());
				String clrF = commonBo.getClrByClrTable(valTxnId);

				if (clrF != null && !clrF.isEmpty()) {

					if (clrF.equalsIgnoreCase("Y")) {

						eForm.setClrFlag(clrF);
					} else {
						eForm.setClrFlag("");
					}
				} else {
					eForm.setClrFlag("");
				}
                //below for property related uploads
                if (RegInitConstant.UPLOAD_FILE_ANGLE_1.equals(actionName)) {
                	
                	
                	
    				try {
    					
    					if (eForm.getClrFlag() != null) {
    						if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
    							
    							commonBo.refreshKhasraDataClr(eForm, request);
    						} else {
    							commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    						}

    					} else {
    						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    					}	
    					
    					
    					//commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    					//logger.debug("flag in upload action:"+eForm.getRegcompletDto().getMpcstSuccessFlag()+":");
    					FormFile uploadedFile = eForm.getRegcompletDto().getPropImage1();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePropUploadCompltn(fileExt);
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
    						
    					//if (rule.isError() || size <= 0) 
    						if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							eForm.getRegcompletDto().setPropImage1DocumentName(uploadedFile.getFileName());
    							eForm.getRegcompletDto().setPropImage1DocContents(uploadedFile.getFileData());
    							eForm.getRegcompletDto().setPropImage1DocumentSize(photoSize);
    							eForm.getRegcompletDto().setPropImage1FilePath(RegInitConstant.FILE_NAME_PROP_ANGLE_1+fileExt);
    							eForm.getRegcompletDto().setPropImage1PartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
    							eForm.getRegcompletDto().setPropImage1UploadType(RegInitConstant.FILE_NAME_PROP_ANGLE_1+fileExt);
    							
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=eForm.getForwardJsp();    				
    				

    					if (forward.equalsIgnoreCase("reginitProperty")) {
    						if (eForm.getClrFlag() != null) {
    							if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

    								forward = "reginitPropertyClr";
    							} else {
    								forward = "reginitProperty";
    							}

    						} else {
    							forward = "reginitProperty";
    						}

    					}

    					else if (forward.equalsIgnoreCase("reginitPropertyNonPropDeed")) {
    						if (eForm.getClrFlag() != null) {
    							if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

    								forward = "reginitPropertyNonPropDeedCLR";
    							} else {
    								forward = "reginitPropertyNonPropDeed";
    							}
    						} else {
    							forward = "reginitPropertyNonPropDeed";
    						}
    					}
    					
    				request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());
    				//request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
    			} 
                
                
                if (RegInitConstant.UPLOAD_FILE_ANGLE_2.equals(actionName)) {
                	
                	
                	
                	
    				try {
    					
    					if (eForm.getClrFlag() != null) {
    						if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
    							
    							commonBo.refreshKhasraDataClr(eForm, request);
    						} else {
    							commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    						}

    					} else {
    						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    					}	
    					
    					//commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    					FormFile uploadedFile = eForm.getRegcompletDto().getPropImage2();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePropUploadCompltn(fileExt);
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
    											 // new code finish. 
    					
    				
    					   
    					//if (rule.isError() || size <= 0) 
    									        if (!mime){
    									        		errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							eForm.getRegcompletDto().setPropImage2DocumentName(uploadedFile.getFileName());
    							eForm.getRegcompletDto().setPropImage2DocContents(uploadedFile.getFileData());
    							eForm.getRegcompletDto().setPropImage2DocumentSize(photoSize);
    							eForm.getRegcompletDto().setPropImage2FilePath(RegInitConstant.FILE_NAME_PROP_ANGLE_2+fileExt);
    							eForm.getRegcompletDto().setPropImage2PartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
    							eForm.getRegcompletDto().setPropImage2UploadType(RegInitConstant.FILE_NAME_PROP_ANGLE_2+fileExt);
    							
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=eForm.getForwardJsp();
    				
    				if (forward.equalsIgnoreCase("reginitProperty")) {
    					if (eForm.getClrFlag() != null) {
    						if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

    							forward = "reginitPropertyClr";
    						} else {
    							forward = "reginitProperty";
    						}

    					} else {
    						forward = "reginitProperty";
    					}

    				}

    				else if (forward.equalsIgnoreCase("reginitPropertyNonPropDeed")) {
    					if (eForm.getClrFlag() != null) {
    						if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

    							forward = "reginitPropertyNonPropDeedCLR";
    						} else {
    							forward = "reginitPropertyNonPropDeed";
    						}
    					} else {
    						forward = "reginitPropertyNonPropDeed";
    					}

    				}
    				request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());
    				//request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
    			} 
                
                if (RegInitConstant.UPLOAD_FILE_ANGLE_3.equals(actionName)) {
                	
                	
                	
                	
    				try {
    					if (eForm.getClrFlag() != null) {
    						if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
    							
    							commonBo.refreshKhasraDataClr(eForm, request);
    						} else {
    							commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    						}

    					} else {
    						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    					}	
    					
    					
    					//commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    					FormFile uploadedFile = eForm.getRegcompletDto().getPropImage3();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePropUploadCompltn(fileExt);
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
						 // new code finish. 
    					    					//if (rule.isError() || size <= 0)
    						if (!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							eForm.getRegcompletDto().setPropImage3DocumentName(uploadedFile.getFileName());
    							eForm.getRegcompletDto().setPropImage3DocContents(uploadedFile.getFileData());
    							eForm.getRegcompletDto().setPropImage3DocumentSize(photoSize);
    							eForm.getRegcompletDto().setPropImage3FilePath(RegInitConstant.FILE_NAME_PROP_ANGLE_3+fileExt);
    							eForm.getRegcompletDto().setPropImage3PartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
    							eForm.getRegcompletDto().setPropImage3UploadType(RegInitConstant.FILE_NAME_PROP_ANGLE_3+fileExt);
    							
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=eForm.getForwardJsp();
    				request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());
    				//request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
    			} 
                
 if (RegInitConstant.UPLOAD_PROPERTY_MAP.equals(actionName)) {
                	
                	
                	
                	
    				try {
    					
    					if (eForm.getClrFlag() != null) {
    						if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
    							
    							commonBo.refreshKhasraDataClr(eForm, request);
    						} else {
    							commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    						}

    					} else {
    						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    					}	
    					
    					
    					
    					//commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);    					
    					
    					FormFile uploadedFile = eForm.getRegcompletDto().getPropertyMap();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
    					}

    					//String fileExt = getFileExtension(uploadedFile.getFileName());
    					RegInitRule rule = new RegInitRule();
    					int height=rule.getHeight(uploadedFile.getFileData());
    					int width=rule.getWidth(uploadedFile.getFileData());
    					logger.debug("height got:"+height);
    					logger.debug("width got:"+width);
    					//rule.validateFileTypePropMapZip(fileExt);
    					// Added new code for MIME type common - Rahul
    					MIMECheck mimeCheck = new MIMECheck();
    					String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
				        Boolean mime = mimeCheck.validateMIMEAndFileType(uploadedFile);  // common code to check mime type and validation.
						 // new code finish. 
    					int size = uploadedFile.getFileSize();
    					double fileSizeInBytes = size;
    					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
    					double fileSizeInKB = fileSizeInBytes / 1024.0;
    					// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
    					double fileSizeInMB = fileSizeInKB / 1024.0;
    					DecimalFormat decim = new DecimalFormat("#.##");
    					Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
    					String photoSize="("+fileSize+"MB)";
    					//if (rule.isError() || size <= 0) 
    					  if(!mime){
    						errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
    							request.setAttribute("errorsList", errorList);
    						}else
    						if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE_MPCST_MAP) {
    							errorList.add(RegInitConstant.lbl_reg_init_upload_msg6);
    							request.setAttribute("errorsList", errorList);
    						} else {
    							eForm.getRegcompletDto().setPropMapDocumentName(uploadedFile.getFileName());
    							eForm.getRegcompletDto().setPropMapDocContents(uploadedFile.getFileData());
    							eForm.getRegcompletDto().setPropMapDocumentSize(photoSize);
    							eForm.getRegcompletDto().setPropMapFilePath(RegInitConstant.FILE_NAME_PROP_MAP+fileExt);
    							eForm.getRegcompletDto().setPropMapPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
    							eForm.getRegcompletDto().setPropMapUploadType(RegInitConstant.FILE_NAME_PROP_MAP+fileExt);
    							
    						}
    					}
    				} catch (Exception e) {
    					logger.debug(e.getMessage(),e);
    					errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=eForm.getForwardJsp();
    				
    				if (forward.equalsIgnoreCase("reginitProperty")) {
    					if (eForm.getClrFlag() != null) {
    						if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
    							
    							forward = "reginitPropertyClr";
    						} else {
    							forward = "reginitProperty";
    						}

    					} else {
    						forward = "reginitProperty";
    					}

    				}

    				else if (forward.equalsIgnoreCase("reginitPropertyNonPropDeed")) {
    					if (eForm.getClrFlag() != null) {
    						if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
    							
    							forward = "reginitPropertyNonPropDeedCLR";
    						} else {
    							forward = "reginitPropertyNonPropDeed";
    						}
    					} else {
    						forward = "reginitPropertyNonPropDeed";
    					}

    				}
    				request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());
    				//request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
    			} 
 
 
 //Added by ankit for plant and machinery -- start
 
 if (RegInitConstant.UPLOAD_AUDIT_REPORT.equals(actionName)) {
		
		try {
			
			if (eForm.getClrFlag() != null) {
				if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
					
					commonBo.refreshKhasraDataClr(eForm, request);
				} else {
					commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
				}

			} else {
				commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
			}	
			
			
			
			//commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);    					
			
			FormFile uploadedFile = eForm.getRegcompletDto().getAuditReport();
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
			}

			//String fileExt = getFileExtension(uploadedFile.getFileName());
			RegInitRule rule = new RegInitRule();
			int height=rule.getHeight(uploadedFile.getFileData());
			int width=rule.getWidth(uploadedFile.getFileData());
			logger.debug("height got:"+height);
			logger.debug("width got:"+width);
			//rule.validateFileTypePropMapZip(fileExt);
			// Added new code for MIME type common - Rahul
			MIMECheck mimeCheck = new MIMECheck();
			String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
	        Boolean mime = mimeCheck.validateMIMEAndJPGAndPDFFileType(uploadedFile);  // common code to check mime type and validation.
			 // new code finish. 
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize="("+fileSize+"MB)";
			//if (rule.isError() || size <= 0) 
			  if(!mime){
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
					request.setAttribute("errorsList", errorList);
				}else
				if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE_AUDIT_REPORT) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg6);
					request.setAttribute("errorsList", errorList);
				} else {
					eForm.getRegcompletDto().setAuditReportDocumentName(uploadedFile.getFileName());
					eForm.getRegcompletDto().setAuditReportDocContents(uploadedFile.getFileData());
					eForm.getRegcompletDto().setAuditReportDocumentSize(photoSize);
					eForm.getRegcompletDto().setAuditReportFilePath(RegInitConstant.FILE_NAME_AUDIT_REPORT+fileExt);
					eForm.getRegcompletDto().setAuditReportPartyOrProp(RegInitConstant.FILE_UPLOAD_AUDIT_REPORT);
					eForm.getRegcompletDto().setAuditReportUploadType(RegInitConstant.FILE_NAME_AUDIT_REPORT+fileExt);
					
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorsList", errorList);
		}
		
		forward=eForm.getForwardJsp();
		
		if (forward.equalsIgnoreCase("reginitProperty")) {
			if (eForm.getClrFlag() != null) {
				if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
					
					forward = "reginitPropertyClr";
				} else {
					forward = "reginitProperty";
				}

			} else {
				forward = "reginitProperty";
			}

		}

		else if (forward.equalsIgnoreCase("reginitPropertyNonPropDeed")) {
			if (eForm.getClrFlag() != null) {
				if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
					
					forward = "reginitPropertyNonPropDeedCLR";
				} else {
					forward = "reginitPropertyNonPropDeed";
				}
			} else {
				forward = "reginitPropertyNonPropDeed";
			}

		}
		request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());
		//request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
	} 
 
 //Added by ankit for plant and machinery - end
 
 
 
 
 if (RegInitConstant.ZIP_MPCST_ACTION.equals(actionName)) {
 	
 	
	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		ZipEntry entry = new ZipEntry(eForm.getRegcompletDto().getMpcst1DocumentName());
		entry.setSize(eForm.getRegcompletDto().getMpcst1DocContents().length);
		zos.putNextEntry(entry);
		zos.write(eForm.getRegcompletDto().getMpcst1DocContents());
		
		ZipEntry entry2 = new ZipEntry(eForm.getRegcompletDto().getMpcst2DocumentName());
		entry2.setSize(eForm.getRegcompletDto().getMpcst2DocContents().length);
		zos.putNextEntry(entry2);
		zos.write(eForm.getRegcompletDto().getMpcst2DocContents());
		
		
		zos.closeEntry();
		zos.close();
		byte[] zipFile= baos.toByteArray();
		
		logger.debug("zip byte[]:"+zipFile);
 	
		try {
			if (eForm.getClrFlag() != null) {
				if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
					
					commonBo.refreshKhasraDataClr(eForm, request);
				} else {
					commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
				}

			} else {
				commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
			}		
			//commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
			

			String fileExt = "zip";
			RegInitRule rule = new RegInitRule();
				
			
		
			   
			if (rule.isError()) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				
					eForm.getRegcompletDto().setPropMapDocumentName("MPCST.zip");
					eForm.getRegcompletDto().setPropMapDocContents(zipFile);
					//eForm.getRegcompletDto().setPropMapDocumentSize(photoSize);
					eForm.getRegcompletDto().setPropMapFilePath(RegInitConstant.FILE_NAME_PROP_MAP+fileExt);
					eForm.getRegcompletDto().setPropMapPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
					eForm.getRegcompletDto().setPropMapUploadType(RegInitConstant.FILE_NAME_PROP_MAP+fileExt);
					
				
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorsList", errorList);
		}
		
		forward=eForm.getForwardJsp();
		request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());
		//request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
	} 
 
 if (RegInitConstant.UPLOAD_PROPERTY_RIN.equals(actionName)) {
 	
 	
 	
 	
		try {
			if (eForm.getClrFlag() != null) {
				if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
					
					commonBo.refreshKhasraDataClr(eForm, request);
				} else {
					commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
				}

			} else {
				commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
			}		
			//commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);		
			
			//commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
			FormFile uploadedFile = eForm.getRegcompletDto().getPropertyRin();
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
			}

			//String fileExt = getFileExtension(uploadedFile.getFileName());
			RegInitRule rule = new RegInitRule();
			int height=rule.getHeight(uploadedFile.getFileData());
			int width=rule.getWidth(uploadedFile.getFileData());
			logger.debug("height got:"+height);
			logger.debug("width got:"+width);
			//rule.validateFileTypePropUploadCompltn(fileExt);
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
			 // new code finish. 
			
			//if (rule.isError() || size <= 0) 

	        if (!mime){
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
					request.setAttribute("errorsList", errorList);
				}else
				if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
					request.setAttribute("errorsList", errorList);
				} else {
					eForm.getRegcompletDto().setPropRinDocumentName(uploadedFile.getFileName());
					eForm.getRegcompletDto().setPropRinDocContents(uploadedFile.getFileData());
					eForm.getRegcompletDto().setPropRinDocumentSize(photoSize);
					eForm.getRegcompletDto().setPropRinFilePath(RegInitConstant.FILE_NAME_PROP_RIN+fileExt);
					eForm.getRegcompletDto().setPropRinPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
					eForm.getRegcompletDto().setPropRinUploadType(RegInitConstant.FILE_NAME_PROP_RIN+fileExt);
					
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorsList", errorList);
		}
		
		forward=eForm.getForwardJsp();
		
		if (forward.equalsIgnoreCase("reginitProperty")) {
				if (eForm.getClrFlag() != null) {
					if (eForm.getClrFlag().equalsIgnoreCase("Y")) {						
						forward = "reginitPropertyClr";
					} else {
						forward = "reginitProperty";
					}

				} else {
					forward = "reginitProperty";
				}

			}

			else if (forward.equalsIgnoreCase("reginitPropertyNonPropDeed")) {
				if (eForm.getClrFlag() != null) {
					if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
						
						forward = "reginitPropertyNonPropDeedCLR";
					} else {
						forward = "reginitPropertyNonPropDeed";
					}
				} else {
					forward = "reginitPropertyNonPropDeed";
				}

			}
		request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());
		//request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
	} 
 
 if (RegInitConstant.UPLOAD_PROPERTY_KHASRA.equals(actionName)) {
 	
 	
 	
 	
		try {
			if (eForm.getClrFlag() != null) {
				if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
					
					commonBo.refreshKhasraDataClr(eForm, request);
				} else {
					commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
				}

			} else {
				commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
			}			
			
			
			FormFile uploadedFile = eForm.getRegcompletDto().getPropertyKhasra();
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
			}

			//String fileExt = getFileExtension(uploadedFile.getFileName());
			RegInitRule rule = new RegInitRule();
			int height=rule.getHeight(uploadedFile.getFileData());
			int width=rule.getWidth(uploadedFile.getFileData());
			logger.debug("height got:"+height);
			logger.debug("width got:"+width);
			//rule.validateFileTypePropUploadCompltn(fileExt);
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
			 // new code finish. 
						//if (rule.isError() || size <= 0)

				if (!mime){
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
					request.setAttribute("errorsList", errorList);
				}else
				if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
					request.setAttribute("errorsList", errorList);
				} else {
					eForm.getRegcompletDto().setPropKhasraDocumentName(uploadedFile.getFileName());
					eForm.getRegcompletDto().setPropKhasraDocContents(uploadedFile.getFileData());
					eForm.getRegcompletDto().setPropKhasraDocumentSize(photoSize);
					eForm.getRegcompletDto().setPropKhasraFilePath(RegInitConstant.FILE_NAME_PROP_KHASRA+fileExt);
					eForm.getRegcompletDto().setPropKhasraPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
					eForm.getRegcompletDto().setPropKhasraUploadType(RegInitConstant.FILE_NAME_PROP_KHASRA+fileExt);
					
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorsList", errorList);
		}
		
		forward=eForm.getForwardJsp();
		
		if (forward.equalsIgnoreCase("reginitProperty")) {
			if (eForm.getClrFlag() != null) {
				if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

					forward = "reginitPropertyClr";
				} else {
					forward = "reginitProperty";
				}

			} else {
				forward = "reginitProperty";
			}

		}

		else if (forward.equalsIgnoreCase("reginitPropertyNonPropDeed")) {
			if (eForm.getClrFlag() != null) {
				if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

					forward = "reginitPropertyNonPropDeedCLR";
				} else {
					forward = "reginitPropertyNonPropDeed";
				}
			} else {
				forward = "reginitPropertyNonPropDeed";
			}

		}
		request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());
		//request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
	} 
 
 if (RegInitConstant.UPLOAD_PROPERTY_KHASRA_CLR.equals(actionName)) {
	 	
	 	
	 	
	 	
		try {
			
			
			if (eForm.getClrFlag() != null) {
				if (eForm.getClrFlag().equalsIgnoreCase("Y")) {
					
					commonBo.refreshKhasraDataClr(eForm, request);
				} else {
					commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
				}

			} else {
				commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
			}		
			FormFile uploadedFile = eForm.getRegcompletDto().getPropertyKhasra();
			//FormFile uploadedFile = eForm.getPropertyKhasraClr();
			
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
			}

			//String fileExt = getFileExtension(uploadedFile.getFileName());
			RegInitRule rule = new RegInitRule();
			int height=rule.getHeight(uploadedFile.getFileData());
			int width=rule.getWidth(uploadedFile.getFileData());
			logger.debug("height got:"+height);
			logger.debug("width got:"+width);
			//rule.validateFileTypePropUploadCompltn(fileExt);
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
			 // new code finish. 
						//if (rule.isError() || size <= 0)
	       // ArrayList<RegCompletDTO> rcList=new ArrayList<RegCompletDTO>();
	        RegCompletDTO rcDTO=new RegCompletDTO();
				if (!mime){
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
					request.setAttribute("errorsList", errorList);
				}else
				if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
					request.setAttribute("errorsList", errorList);
				} else {
					//eForm.getRegcompletDto().setPropKhasraDocumentName(uploadedFile.getFileName());
					eForm.getRegcompletDto().setPropKhasraDocumentName(uploadedFile.getFileName());
					eForm.getRegcompletDto().setPropKhasraDocContents(uploadedFile.getFileData());
					eForm.getRegcompletDto().setPropKhasraDocumentSize(photoSize);
					eForm.getRegcompletDto().setPropKhasraFilePath(RegInitConstant.FILE_NAME_PROP_KHASRA+fileExt);
					eForm.getRegcompletDto().setPropKhasraPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
					eForm.getRegcompletDto().setPropKhasraUploadType(RegInitConstant.FILE_NAME_PROP_KHASRA+fileExt);
					
				
					
					
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorsList", errorList);
		}
		
		forward=eForm.getForwardJsp();
		
		if (forward.equalsIgnoreCase("reginitProperty")) {
			if (eForm.getClrFlag() != null) {
				if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

					forward = "reginitPropertyClr";
				} else {
					forward = "reginitProperty";
				}

			} else {
				forward = "reginitProperty";
			}

		}

		else if (forward.equalsIgnoreCase("reginitPropertyNonPropDeed")) {
			if (eForm.getClrFlag() != null) {
				if (eForm.getClrFlag().equalsIgnoreCase("Y")) {

					forward = "reginitPropertyNonPropDeedCLR";
				} else {
					forward = "reginitPropertyNonPropDeed";
				}
			} else {
				forward = "reginitPropertyNonPropDeed";
			}

		}
		request.setAttribute("deedId", eForm.getRegInitForm().getDeedID());
		//request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
	} 
 if (RegInitConstant.MODIFY_FILE_ANGLE_1.equals(actionName)) {/*
 	
 	
 	
 	
		try {
			
			if(eForm.getKhasraNoArray()!=null && eForm.getKhasraAreaArray()!=null 
					&& eForm.getLagaanArray()!=null && eForm.getRinPustikaArray()!=null
					&& !eForm.getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
					&& !eForm.getLagaanArray().equalsIgnoreCase("null") && !eForm.getRinPustikaArray().equalsIgnoreCase("null")
					&& !eForm.getKhasraNoArray().equalsIgnoreCase("") && !eForm.getKhasraAreaArray().equalsIgnoreCase("")
					&& !eForm.getLagaanArray().equalsIgnoreCase("") && !eForm.getRinPustikaArray().equalsIgnoreCase("")){
				
				
				if(eForm.getKhasraNoArray().length()>0 && eForm.getKhasraAreaArray().length()>0 && eForm.getLagaanArray().length()>0 && eForm.getRinPustikaArray().length()>0){
				logger.debug("khasra nos. :-"+eForm.getKhasraNoArray());
                logger.debug("khasra areas. :-"+eForm.getKhasraAreaArray());
                logger.debug("lagaans :-"+eForm.getLagaanArray());
                logger.debug("rin pustikas :-"+eForm.getRinPustikaArray());
                
             
                
                commonBo.createNewKhasraDetls(eForm);
				}
				
				
			}
			FormFile uploadedFile = eForm.getRegcompletDto().getPropImage1();
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
			}

			String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileType(fileExt);
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize="("+fileSize+"MB)";
			
			
		
			   
			if (rule.isError() || size <= 0) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
					request.setAttribute("errorsList", errorList);
				} else {
					eForm.getRegcompletDto().setPropImage1DocumentName(uploadedFile.getFileName());
					eForm.getRegcompletDto().setPropImage1DocContents(uploadedFile.getFileData());
					eForm.getRegcompletDto().setPropImage1DocumentSize(photoSize);
					eForm.getRegcompletDto().setPropImage1FilePath("");
					eForm.getRegcompletDto().setPropImage1PartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
					eForm.getRegcompletDto().setPropImage1UploadType(RegInitConstant.FILE_NAME_PROP_ANGLE_1+fileExt);
					
				}
			}
		} catch (Exception e) {
			errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorsList", errorList);
		}
		
		forward="editPropDetails";
		request.setAttribute("deedId", regForm.getDeedID());
		request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
	*/} 
 
 
 if (RegInitConstant.MODIFY_FILE_ANGLE_2.equals(actionName)) {/*
 	
 	
 	
 	
		try {
if(eForm.getKhasraNoArray()!=null && eForm.getKhasraAreaArray()!=null 
		&& eForm.getLagaanArray()!=null && eForm.getRinPustikaArray()!=null
		&& !eForm.getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
		&& !eForm.getLagaanArray().equalsIgnoreCase("null") && !eForm.getRinPustikaArray().equalsIgnoreCase("null")
		&& !eForm.getKhasraNoArray().equalsIgnoreCase("") && !eForm.getKhasraAreaArray().equalsIgnoreCase("")
		&& !eForm.getLagaanArray().equalsIgnoreCase("") && !eForm.getRinPustikaArray().equalsIgnoreCase("")){
				
	if(eForm.getKhasraNoArray().length()>0 && eForm.getKhasraAreaArray().length()>0 && eForm.getLagaanArray().length()>0 && eForm.getRinPustikaArray().length()>0){
		logger.debug("khasra nos. :-"+eForm.getKhasraNoArray());
        logger.debug("khasra areas. :-"+eForm.getKhasraAreaArray());
        logger.debug("lagaans :-"+eForm.getLagaanArray());
        logger.debug("rin pustikas :-"+eForm.getRinPustikaArray());
        
     
        
        commonBo.createNewKhasraDetls(eForm);
		}
				
				
			}
			FormFile uploadedFile = eForm.getRegcompletDto().getPropImage2();
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
			}

			String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileType(fileExt);
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize="("+fileSize+"MB)";
			
			
		
			   
			if (rule.isError() || size <= 0) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
					request.setAttribute("errorsList", errorList);
				} else {
					eForm.getRegcompletDto().setPropImage2DocumentName(uploadedFile.getFileName());
					eForm.getRegcompletDto().setPropImage2DocContents(uploadedFile.getFileData());
					eForm.getRegcompletDto().setPropImage2DocumentSize(photoSize);
					eForm.getRegcompletDto().setPropImage2FilePath("");
					eForm.getRegcompletDto().setPropImage2PartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
					eForm.getRegcompletDto().setPropImage2UploadType(RegInitConstant.FILE_NAME_PROP_ANGLE_2+fileExt);
					
				}
			}
		} catch (Exception e) {
			errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorsList", errorList);
		}
		
		forward="editPropDetails";
		request.setAttribute("deedId", regForm.getDeedID());
		request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
	*/} 
 
 if (RegInitConstant.MODIFY_FILE_ANGLE_3.equals(actionName)) {/*
 	
 	
 	
 	
		try {
if(eForm.getKhasraNoArray()!=null && eForm.getKhasraAreaArray()!=null 
		&& eForm.getLagaanArray()!=null && eForm.getRinPustikaArray()!=null
		&& !eForm.getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
		&& !eForm.getLagaanArray().equalsIgnoreCase("null") && !eForm.getRinPustikaArray().equalsIgnoreCase("null")
		&& !eForm.getKhasraNoArray().equalsIgnoreCase("") && !eForm.getKhasraAreaArray().equalsIgnoreCase("")
		&& !eForm.getLagaanArray().equalsIgnoreCase("") && !eForm.getRinPustikaArray().equalsIgnoreCase("")){
				
	if(eForm.getKhasraNoArray().length()>0 && eForm.getKhasraAreaArray().length()>0 && eForm.getLagaanArray().length()>0 && eForm.getRinPustikaArray().length()>0){
		logger.debug("khasra nos. :-"+eForm.getKhasraNoArray());
        logger.debug("khasra areas. :-"+eForm.getKhasraAreaArray());
        logger.debug("lagaans :-"+eForm.getLagaanArray());
        logger.debug("rin pustikas :-"+eForm.getRinPustikaArray());
        
     
        
        commonBo.createNewKhasraDetls(eForm);
		}
				
				
			}
			FormFile uploadedFile = eForm.getRegcompletDto().getPropImage3();
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
			}

			String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileType(fileExt);
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize="("+fileSize+"MB)";
			
			
		
			   
			if (rule.isError() || size <= 0) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
					request.setAttribute("errorsList", errorList);
				} else {
					eForm.getRegcompletDto().setPropImage3DocumentName(uploadedFile.getFileName());
					eForm.getRegcompletDto().setPropImage3DocContents(uploadedFile.getFileData());
					eForm.getRegcompletDto().setPropImage3DocumentSize(photoSize);
					eForm.getRegcompletDto().setPropImage3FilePath("");
					eForm.getRegcompletDto().setPropImage3PartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
					eForm.getRegcompletDto().setPropImage3UploadType(RegInitConstant.FILE_NAME_PROP_ANGLE_3+fileExt);
					
				}
			}
		} catch (Exception e) {
			errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorsList", errorList);
		}
		
		forward="editPropDetails";
		request.setAttribute("deedId", regForm.getDeedID());
		request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
	*/} 
 
if (RegInitConstant.MODIFY_PROPERTY_MAP.equals(actionName)) {/*
 	
 	
 	
 	
		try {
if(eForm.getKhasraNoArray()!=null && eForm.getKhasraAreaArray()!=null 
		&& eForm.getLagaanArray()!=null && eForm.getRinPustikaArray()!=null
		&& !eForm.getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
		&& !eForm.getLagaanArray().equalsIgnoreCase("null") && !eForm.getRinPustikaArray().equalsIgnoreCase("null")
		&& !eForm.getKhasraNoArray().equalsIgnoreCase("") && !eForm.getKhasraAreaArray().equalsIgnoreCase("")
		&& !eForm.getLagaanArray().equalsIgnoreCase("") && !eForm.getRinPustikaArray().equalsIgnoreCase("")){
				
	if(eForm.getKhasraNoArray().length()>0 && eForm.getKhasraAreaArray().length()>0 && eForm.getLagaanArray().length()>0 && eForm.getRinPustikaArray().length()>0){
		logger.debug("khasra nos. :-"+eForm.getKhasraNoArray());
        logger.debug("khasra areas. :-"+eForm.getKhasraAreaArray());
        logger.debug("lagaans :-"+eForm.getLagaanArray());
        logger.debug("rin pustikas :-"+eForm.getRinPustikaArray());
        
     
        
        commonBo.createNewKhasraDetls(eForm);
		}
				
				
			}
			FormFile uploadedFile = eForm.getRegcompletDto().getPropertyMap();
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
			}

			String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileType(fileExt);
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String photoSize="("+fileSize+"MB)";
			
			
		
			   
			if (rule.isError() || size <= 0) {
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
					request.setAttribute("errorsList", errorList);
				} else {
					eForm.getRegcompletDto().setPropMapDocumentName(uploadedFile.getFileName());
					eForm.getRegcompletDto().setPropMapDocContents(uploadedFile.getFileData());
					eForm.getRegcompletDto().setPropMapDocumentSize(photoSize);
					eForm.getRegcompletDto().setPropMapFilePath("");
					eForm.getRegcompletDto().setPropMapPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
					eForm.getRegcompletDto().setPropMapUploadType(RegInitConstant.FILE_NAME_PROP_MAP+fileExt);
					
				}
			}
		} catch (Exception e) {
			errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorsList", errorList);
		}
		
		forward="editPropDetails";
		request.setAttribute("deedId", regForm.getDeedID());
		request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
	*/} 

if (RegInitConstant.MODIFY_PROPERTY_RIN.equals(actionName)) {/*




try {
	if(eForm.getKhasraNoArray()!=null && eForm.getKhasraAreaArray()!=null 
			&& eForm.getLagaanArray()!=null && eForm.getRinPustikaArray()!=null
			&& !eForm.getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
			&& !eForm.getLagaanArray().equalsIgnoreCase("null") && !eForm.getRinPustikaArray().equalsIgnoreCase("null")
			&& !eForm.getKhasraNoArray().equalsIgnoreCase("") && !eForm.getKhasraAreaArray().equalsIgnoreCase("")
			&& !eForm.getLagaanArray().equalsIgnoreCase("") && !eForm.getRinPustikaArray().equalsIgnoreCase("")){
		
		if(eForm.getKhasraNoArray().length()>0 && eForm.getKhasraAreaArray().length()>0 && eForm.getLagaanArray().length()>0 && eForm.getRinPustikaArray().length()>0){
			logger.debug("khasra nos. :-"+eForm.getKhasraNoArray());
            logger.debug("khasra areas. :-"+eForm.getKhasraAreaArray());
            logger.debug("lagaans :-"+eForm.getLagaanArray());
            logger.debug("rin pustikas :-"+eForm.getRinPustikaArray());
            
         
            
            commonBo.createNewKhasraDetls(eForm);
			}
		
		
	}
FormFile uploadedFile = eForm.getRegcompletDto().getPropertyRin();
if ("".equals(uploadedFile.getFileName())) {
	errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
}

String fileExt = getFileExtension(uploadedFile.getFileName());
AuditInspectionRule rule = new AuditInspectionRule();
rule.validateFileType(fileExt);
int size = uploadedFile.getFileSize();
double fileSizeInBytes = size;
// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
double fileSizeInKB = fileSizeInBytes / 1024.0;
// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
double fileSizeInMB = fileSizeInKB / 1024.0;
DecimalFormat decim = new DecimalFormat("#.##");
Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
String photoSize="("+fileSize+"MB)";




if (rule.isError() || size <= 0) {
	errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
	  	
	request.setAttribute("errorsList", errorList);
} else {
	if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
		errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
		request.setAttribute("errorsList", errorList);
	} else {
		eForm.getRegcompletDto().setPropRinDocumentName(uploadedFile.getFileName());
		eForm.getRegcompletDto().setPropRinDocContents(uploadedFile.getFileData());
		eForm.getRegcompletDto().setPropRinDocumentSize(photoSize);
		eForm.getRegcompletDto().setPropRinFilePath("");
		eForm.getRegcompletDto().setPropRinPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
		eForm.getRegcompletDto().setPropRinUploadType(RegInitConstant.FILE_NAME_PROP_RIN+fileExt);
		
	}
}
} catch (Exception e) {
errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
request.setAttribute("errorsList", errorList);
}

forward="editPropDetails";
request.setAttribute("deedId", regForm.getDeedID());
request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
*/} 

if (RegInitConstant.MODIFY_PROPERTY_KHASRA.equals(actionName)) {/*




try {
	
	if(eForm.getKhasraNoArray()!=null && eForm.getKhasraAreaArray()!=null 
			&& eForm.getLagaanArray()!=null && eForm.getRinPustikaArray()!=null
			&& !eForm.getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
			&& !eForm.getLagaanArray().equalsIgnoreCase("null") && !eForm.getRinPustikaArray().equalsIgnoreCase("null")
			&& !eForm.getKhasraNoArray().equalsIgnoreCase("") && !eForm.getKhasraAreaArray().equalsIgnoreCase("")
			&& !eForm.getLagaanArray().equalsIgnoreCase("") && !eForm.getRinPustikaArray().equalsIgnoreCase("")){
		
		if(eForm.getKhasraNoArray().length()>0 && eForm.getKhasraAreaArray().length()>0 && eForm.getLagaanArray().length()>0 && eForm.getRinPustikaArray().length()>0){
			logger.debug("khasra nos. :-"+eForm.getKhasraNoArray());
            logger.debug("khasra areas. :-"+eForm.getKhasraAreaArray());
            logger.debug("lagaans :-"+eForm.getLagaanArray());
            logger.debug("rin pustikas :-"+eForm.getRinPustikaArray());
            
         
            
            commonBo.createNewKhasraDetls(eForm);
			}
		
		
	}
	
	FormFile uploadedFile = eForm.getRegcompletDto().getPropertyKhasra();
	if ("".equals(uploadedFile.getFileName())) {
	errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
	}

	String fileExt = getFileExtension(uploadedFile.getFileName());
	AuditInspectionRule rule = new AuditInspectionRule();
	rule.validateFileType(fileExt);
	int size = uploadedFile.getFileSize();
	double fileSizeInBytes = size;
	// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
	double fileSizeInKB = fileSizeInBytes / 1024.0;
	// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
	double fileSizeInMB = fileSizeInKB / 1024.0;
	DecimalFormat decim = new DecimalFormat("#.##");
	Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
	String photoSize="("+fileSize+"MB)";




if (rule.isError() || size <= 0) {
	errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
	  	
	request.setAttribute("errorsList", errorList);
} else {
	if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
		errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
		request.setAttribute("errorsList", errorList);
	} else {
		eForm.getRegcompletDto().setPropKhasraDocumentName(uploadedFile.getFileName());
		eForm.getRegcompletDto().setPropKhasraDocContents(uploadedFile.getFileData());
		eForm.getRegcompletDto().setPropKhasraDocumentSize(photoSize);
		eForm.getRegcompletDto().setPropKhasraFilePath("");
		eForm.getRegcompletDto().setPropKhasraPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
		eForm.getRegcompletDto().setPropKhasraUploadType(RegInitConstant.FILE_NAME_PROP_KHASRA+fileExt);
		
	}
}
} catch (Exception e) {
errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
request.setAttribute("errorsList", errorList);
}

forward="editPropDetails";
request.setAttribute("deedId", regForm.getDeedID());
request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
*/} 

//RegCommonForm formM =(RegCommonForm)form; 
if(fromParam.equalsIgnoreCase("uploadAdditionalAdju") )
{
	 regForm = (RegCommonForm)form;
	 if(regForm.getActionName().equalsIgnoreCase("MULTI_UPLOAD_ADJU_ACTION_REMOVE"))
	 {
		 String id = regForm.getUniqueUploadIdAdju();
		 
		 ArrayList<CommonDTO> listDto = regForm.getListDtoAdju();
		 
		 
		 for(int i=0;i<listDto.size();i++)
		 {
			 CommonDTO dto = listDto.get(i);
			 
			 if(dto.getUniqueIdUpload().equalsIgnoreCase(id))
			 {
				 listDto.remove(i);
			 }
			 
			 
		 }
		 
		 forward = "popupPageAdju";
	 }
	 
	 
	 if(regForm.getActionName().equalsIgnoreCase("MULTI_UPLOAD_ADJU_ACTION_VIEW"))
	 {
		 String id = regForm.getUniqueUploadIdAdju();
		 
		 ArrayList<CommonDTO> listDto = regForm.getListDtoAdju();
		 CommonDTO download =null;
		 
		 for(int i=0;i<listDto.size();i++)
		 {
			 CommonDTO dto = listDto.get(i);
			 
			 if(dto.getUniqueIdUpload().equalsIgnoreCase(id))
			 {
				 download = dto;
			 }
			 
			 
		 }
		 DMSUtility.downloadDocument(response, download.getDocumentName(),download.getDocContents());
		 forward = "popupPageAdju";
	 }
	 
	 if(regForm.getActionName().equalsIgnoreCase("MULTI_UPLOAD_ADJU_ACTION"))
	 { 
	 
	 System.out.println(regForm.getActionName());
	 ArrayList<CommonDTO> dto = regForm.getListDtoAdju();
	 if(dto!=null){
		 
	 }else{
		 
		 dto=new ArrayList();
		 
	 }
	 CommonDTO newDto=null;
	 try {
			FormFile uploadedFile = regForm.getFileAUPathAdju();
			if ("".equals(uploadedFile.getFileName())) {
				errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg1);
			}

			//String fileExt = getFileExtension(uploadedFile.getFileName());
			RegInitRule rule = new RegInitRule();
			int height=rule.getHeight(uploadedFile.getFileData());
			int width=rule.getWidth(uploadedFile.getFileData());
			logger.debug("height got:"+height);
			logger.debug("width got:"+width);
			//rule.validateFileTypePartyRelated(fileExt);
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
			 // new code finish. 
		
			//if (rule.isError() || size <= 0) 

	        if (!mime)  {
				errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorListAddUpload", errorListAddUpload);
			} else {
				if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
					errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg5);
					request.setAttribute("errorListAddUpload", errorListAddUpload);
				}else
				if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
					errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg3);
					request.setAttribute("errorListAddUpload", errorListAddUpload);
				} else {
					 Date date = new Date();
					 newDto = new CommonDTO();
					 newDto.setDocumentName(regForm.getFileAUNameAdju()+"."+fileExt);
					newDto.setDocContents(uploadedFile.getFileData());
					newDto.setDocumentSize(photoSize);
					newDto.setDocumentPath(regForm.getFileAUNameAdju()+"."+fileExt);
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
					newDto.setUniqueIdUpload(fldrnm);
					dto.add(newDto);
					regForm.setListDtoAdju(dto);
					regForm.setFileAUNameAdju("");
					//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
					//regForm.setFilePath(RegInitConstant.FILE_NAME_CERTIFICATE+fileExt);
					//regForm.setPartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
					//regForm.setUploadType(RegInitConstant.FILE_NAME_CERTIFICATE+fileExt);
					//regForm.setPhotoCheck("phloded");
				}
				
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorListAddUpload", errorListAddUpload);
		}
	forward = "popupPageAdju";
	System.out.println(regForm.getFileAUNameAdju());
	
	
	
	//request.setAttribute("reginit", regForm);
	
}
	
	 
	 
}
if(fromParam.equalsIgnoreCase("uploadAdditional") )
{
	 regForm = (RegCommonForm)form;
	 if(regForm.getActionName().equalsIgnoreCase("MULTI_UPLOAD_APP_ACTION_REMOVE"))
	 {
		 String id = regForm.getUniqueUploadId();
		 
		 ArrayList<CommonDTO> listDto = regForm.getListDto();
		 
		 
		 for(int i=0;i<listDto.size();i++)
		 {
			 CommonDTO dto = listDto.get(i);
			 
			 if(dto.getUniqueIdUpload().equalsIgnoreCase(id))
			 {
				 listDto.remove(i);
			 }
			 
			 
		 }
		 
		 forward = "popupPage";
	 }
	 
	 
	 if(regForm.getActionName().equalsIgnoreCase("MULTI_UPLOAD_APP_ACTION_VIEW"))
	 {
		 String id = regForm.getUniqueUploadId();
		 
		 ArrayList<CommonDTO> listDto = regForm.getListDto();
		 CommonDTO download =null;
		 
		 for(int i=0;i<listDto.size();i++)
		 {
			 CommonDTO dto = listDto.get(i);
			 
			 if(dto.getUniqueIdUpload().equalsIgnoreCase(id))
			 {
				 download = dto;
			 }
			 
			 
		 }
		 DMSUtility.downloadDocument(response, download.getDocumentName(),download.getDocContents());
		 forward = "popupPage";
	 }
	 
	 if(regForm.getActionName().equalsIgnoreCase("MULTI_UPLOAD_APP_ACTION"))
	 { 
	 
	 System.out.println(regForm.getActionName());
	 ArrayList<CommonDTO> dto = regForm.getListDto();
	 if(dto!=null){
		 
	 }else{
		 
		 dto=new ArrayList();
		 
	 }
	 CommonDTO newDto=null;
	 try {
			FormFile uploadedFile = regForm.getFileAUPath();
			if ("".equals(uploadedFile.getFileName())) {
				errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg1);
			}

			//String fileExt = getFileExtension(uploadedFile.getFileName());
			RegInitRule rule = new RegInitRule();
			int height=rule.getHeight(uploadedFile.getFileData());
			int width=rule.getWidth(uploadedFile.getFileData());
			logger.debug("height got:"+height);
			logger.debug("width got:"+width);
			//rule.validateFileTypePartyRelated(fileExt);
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
			 // new code finish. 
			
			//if (rule.isError() || size <= 0) 
				if (!mime){
				errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorListAddUpload", errorListAddUpload);
			} else {
				if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
					errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg5);
					request.setAttribute("errorListAddUpload", errorListAddUpload);
				}else
				if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
					errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg3);
					request.setAttribute("errorListAddUpload", errorListAddUpload);
				} else {
					 Date date = new Date();
					 newDto = new CommonDTO();
					 newDto.setDocumentName(regForm.getFileAUName()+"."+fileExt);
					newDto.setDocContents(uploadedFile.getFileData());
					newDto.setDocumentSize(photoSize);
					newDto.setDocumentPath(regForm.getFileAUName()+"."+fileExt);
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
					newDto.setUniqueIdUpload(fldrnm);
					dto.add(newDto);
					regForm.setListDto(dto);
					regForm.setFileAUName("");
					//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
					//regForm.setFilePath(RegInitConstant.FILE_NAME_CERTIFICATE+fileExt);
					//regForm.setPartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
					//regForm.setUploadType(RegInitConstant.FILE_NAME_CERTIFICATE+fileExt);
					//regForm.setPhotoCheck("phloded");
				}
				
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
			errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorListAddUpload", errorListAddUpload);
		}
	forward = "popupPage";
	System.out.println(regForm.getFileAUName());
	
	
	
	//request.setAttribute("reginit", regForm);
	
}
	
	 
	 
}
if(fromParam.equalsIgnoreCase("uploadAdditionalP") )
{
	eForm = (RegCompletionForm)form;
	 if(eForm.getActionName().equalsIgnoreCase("MULTI_UPLOAD_APP_ACTION_REMOVE"))
	 {
		 String id = eForm.getUniqueUploadIdP();
		 
		 ArrayList<CommonDTO> listDto = eForm.getListDtoP();
		 
		 
		 for(int i=0;i<listDto.size();i++)
		 {
			 CommonDTO dto = listDto.get(i);
			 
			 if(dto.getUniqueIdUpload().equalsIgnoreCase(id))
			 {
				 listDto.remove(i);
			 }
			 
			 
		 }
		 request.setAttribute("reginitproperty", eForm);
		 forward = "popupPageP";
	 }
	 
	 
	 if(eForm.getActionName().equalsIgnoreCase("MULTI_UPLOAD_APP_ACTION_VIEW"))
	 {
		 String id = eForm.getUniqueUploadIdP();
		 
		 ArrayList<CommonDTO> listDto = eForm.getListDtoP();
		 CommonDTO download =null;
		 
		 for(int i=0;i<listDto.size();i++)
		 {
			 CommonDTO dto = listDto.get(i);
			 
			 if(dto.getUniqueIdUpload().equalsIgnoreCase(id))
			 {
				 download = dto;
			 }
			 
			 
		 }
		 DMSUtility.downloadDocument(response, download.getDocumentName(),download.getDocContents());
		 request.setAttribute("reginitproperty", eForm);
		 forward = "popupPageP";
	 }
	 
	 if(eForm.getActionName().equalsIgnoreCase("MULTI_UPLOAD_APP_ACTION"))
	 { 
	 
	 System.out.println(eForm.getActionName());
	 ArrayList<CommonDTO> dto = eForm.getListDtoP();
	 if(dto!=null){
		 
	 }else{
		 dto=new ArrayList();
	 }
	 CommonDTO newDto=null;
	 try {
			FormFile uploadedFile = eForm.getFileAUPathP();
			if ("".equals(uploadedFile.getFileName())) {
				errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg1);
			}

			//String fileExt = getFileExtension(uploadedFile.getFileName());
			RegInitRule rule = new RegInitRule();
			int height=rule.getHeight(uploadedFile.getFileData());
			int width=rule.getWidth(uploadedFile.getFileData());
			logger.debug("height got:"+height);
			logger.debug("width got:"+width);
			//rule.validateFileTypePartyRelated(fileExt);
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
			 // new code finish. 
			
			//if (rule.isError() || size <= 0)
	        if (!mime){
				errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg2);
				  	
				request.setAttribute("errorListAddUpload", errorListAddUpload);
			} else {
				if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
					errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg5);
					request.setAttribute("errorListAddUpload", errorListAddUpload);
				}else
				if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE) {
					errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg3);
					request.setAttribute("errorListAddUpload", errorListAddUpload);
				} else {
					 Date date = new Date();
					 newDto = new CommonDTO();
					 newDto.setDocumentName(eForm.getFileAUNameP()+"."+fileExt);
					newDto.setDocContents(uploadedFile.getFileData());
					newDto.setDocumentSize(photoSize);
					newDto.setDocumentPath(eForm.getFileAUNameP()+"."+fileExt);
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
					newDto.setUniqueIdUpload(fldrnm);
					dto.add(newDto);
					eForm.setListDtoP(dto);
					eForm.setFileAUNameP("");
					//String filePath=  uploadFile(uploadedFile,uploadedFile.getFileName(),regForm.getHidnRegTxnId());
					//regForm.setFilePath(RegInitConstant.FILE_NAME_CERTIFICATE+fileExt);
					//regForm.setPartyOrProp(RegInitConstant.FILE_UPLOAD_PARTY);
					//regForm.setUploadType(RegInitConstant.FILE_NAME_CERTIFICATE+fileExt);
					//regForm.setPhotoCheck("phloded");
				}
				
			}
		} catch (Exception e) {
			errorListAddUpload.add(RegInitConstant.lbl_reg_init_upload_msg4);
			request.setAttribute("errorListAddUpload", errorListAddUpload);
			logger.debug(e.getMessage(),e);
		}
	forward = "popupPageP";
	System.out.println(eForm.getFileAUNameP());
	
	
	
	request.setAttribute("reginitproperty", eForm);
	
}
	
	 
	 
}

if(fromParam.equalsIgnoreCase("mpcstUpload") )
{
	eForm = (RegCompletionForm)form;
	
	 
	 
	 
	 if(eForm.getActionName().equalsIgnoreCase("UPLOAD_MAP1_ACTION"))
	 {
		 
		try {
				FormFile uploadedFile = eForm.getRegcompletDto().getMpcst1();
				if ("".equals(uploadedFile.getFileName())) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
				}

				//String fileExt = getFileExtension(uploadedFile.getFileName());
				RegInitRule rule = new RegInitRule();
				int height=rule.getHeight(uploadedFile.getFileData());
				int width=rule.getWidth(uploadedFile.getFileData());
				logger.debug("height got:"+height);
				logger.debug("width got:"+width);
				//rule.validateFileTypePropUploadCompltn(fileExt);
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
				 // new code finish. 
				
				//if (rule.isError() || size <= 0)
					if (!mime){
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
					  	
					request.setAttribute("errorsList", errorList);
				} else {
					if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
						errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
						request.setAttribute("errorsList", errorList);
					}else
					if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE_MPCST_MAP) {
						errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
						request.setAttribute("errorsList", errorList);
					} else {
						eForm.getRegcompletDto().setMpcst1DocumentName(uploadedFile.getFileName());
						eForm.getRegcompletDto().setMpcst1DocContents(uploadedFile.getFileData());
						eForm.getRegcompletDto().setMpcst1DocumentSize(photoSize);
						eForm.getRegcompletDto().setMpcst1FilePath(RegInitConstant.FILE_NAME_MPCST_MAP1+fileExt);
						
						
					}
				}
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
				request.setAttribute("errorsList", errorList);
			}
			
	
			forward = eForm.getForwardJsp();
			
			request.setAttribute("reginitproperty", eForm);
		
			
	 
	 }
	 
	 if(eForm.getActionName().equalsIgnoreCase("UPLOAD_MAP2_ACTION"))
	 {
		 
		try {
				FormFile uploadedFile = eForm.getRegcompletDto().getMpcst2();
				if ("".equals(uploadedFile.getFileName())) {
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg1);
				}

				//String fileExt = getFileExtension(uploadedFile.getFileName());
				RegInitRule rule = new RegInitRule();
				int height=rule.getHeight(uploadedFile.getFileData());
				int width=rule.getWidth(uploadedFile.getFileData());
				logger.debug("height got:"+height);
				logger.debug("width got:"+width);
				//rule.validateFileTypePropUploadCompltn(fileExt);
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
				 // new code finish. 
			 
			//	if (rule.isError() || size <= 0)
		        if (!mime){
					errorList.add(RegInitConstant.lbl_reg_init_upload_msg2);
					  	
					request.setAttribute("errorsList", errorList);
				} else {
					if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
						errorList.add(RegInitConstant.lbl_reg_init_upload_msg5);
						request.setAttribute("errorsList", errorList);
					}else
					if (fileSizeInKB > RegInitConstant.MAX_FILE_SIZE_MPCST_MAP) {
						errorList.add(RegInitConstant.lbl_reg_init_upload_msg3);
						request.setAttribute("errorsList", errorList);
					} else {
						eForm.getRegcompletDto().setMpcst2DocumentName(uploadedFile.getFileName());
						eForm.getRegcompletDto().setMpcst2DocContents(uploadedFile.getFileData());
						eForm.getRegcompletDto().setMpcst2DocumentSize(photoSize);
						eForm.getRegcompletDto().setMpcst2FilePath(RegInitConstant.FILE_NAME_MPCST_MAP2+fileExt);
						
						
					}
				}
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
				errorList.add(RegInitConstant.lbl_reg_init_upload_msg4);
				request.setAttribute("errorsList", errorList);
			}
			
	
			forward = eForm.getForwardJsp();
			request.setAttribute("reginitproperty", eForm);
		
			
	 
	 }
	
	 
	 
}

if(request.getParameter("dms")!=null){
	eForm = (RegCompletionForm)form;
	if(request.getParameter("dms").equalsIgnoreCase("retrievalLive")){
	if(request.getParameter("retrievalType")!=null)
	{
		
		//RegCompletDTO dtoObj=(RegCompletDTO)regForm.getMapPropertyTransPartyDisp().get(key);
		
		if(request.getParameter("retrievalId")!=null && request.getParameter("retrievalId").toString().equalsIgnoreCase("mpcstMap1")){
			DMSUtility.downloadDocument(response, eForm.getRegcompletDto().getMpcst1FilePath(),eForm.getRegcompletDto().getMpcst1DocContents());

		}
		
		if(request.getParameter("retrievalId")!=null && request.getParameter("retrievalId").toString().equalsIgnoreCase("mpcstMap2")){
			DMSUtility.downloadDocument(response, eForm.getRegcompletDto().getMpcst2FilePath() ,eForm.getRegcompletDto().getMpcst2DocContents());

		}
		
		
		
	}
	}
}
			//regForm.setCommonDto(commonDto);
			
			
			request.setAttribute("reginit", regForm);
			
			
			
 			logger.debug("the forward path from the upload action in reg init is ==> " + forward);
 			return mapping.findForward(forward);		
	}
	

	
	
	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
		return "";
	}
	
	//method for creating folder on server
/*	public String uploadFile(String regTxnId, byte[] content, String txnPartyId, String partyOrProp, String uploadType) {
		
		


		String filePath;
		String path="";
		
		try{
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		
		path=pr.getValue("upload.location");
		}catch(Exception e){
			logger.debug("exception in uploadFile : "+e);
		}
		
		if(partyOrProp!=null){
		if(partyOrProp.equalsIgnoreCase("01")){
        filePath = path+RegInitConstant.FILE_UPLOAD_PATH+regTxnId+RegInitConstant.FILE_UPLOAD_PATH_PARTY+txnPartyId+"/";
		}
		else if(partyOrProp.equalsIgnoreCase("02")){
			filePath = path+RegInitConstant.FILE_UPLOAD_PATH+regTxnId+RegInitConstant.FILE_UPLOAD_PATH_PROP+txnPartyId+"/";
		}
		else{
			return null;
		}
		}else{
			
			filePath = path+RegInitConstant.FILE_UPLOAD_PATH+regTxnId+"/";
			
		}
		
		logger.debug("Upload path created : "+filePath);
		
		String fileName=uploadType;
        
        File folder = new File(filePath);
        if (!folder.exists()) {
              folder.mkdirs();
        }
        try {

              File newFile = new File(filePath, fileName);
              if (!newFile.exists()) {
                    logger.info("NEW FILE NAME:-" + newFile);
                    FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(content);
                    fos.close();
              } else {

                    //String str = fileName.substring(0, fileName.indexOf("."));
                    //fileName = str + "_01" + ".jpg";
                    //newFile = new File(filePath, fileName);
                    FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(content);
                    fos.close();
              }
              

        } catch (Exception ex) {
        	
              ex.printStackTrace();
              return null;
        }
        return filePath+fileName;
  
		
		

		String filePath;
		
		if(partyOrProp.equalsIgnoreCase("01")){
        filePath = RegInitConstant.FILE_UPLOAD_PATH+regTxnId+RegInitConstant.FILE_UPLOAD_PATH_PARTY+txnPartyId+"/";
		}
		else if(partyOrProp.equalsIgnoreCase("02")){
			filePath = RegInitConstant.FILE_UPLOAD_PATH+regTxnId+RegInitConstant.FILE_UPLOAD_PATH_PROP+txnPartyId+"/";
		}
		else{
			return null;
		}
		
		String fileName=uploadType;
        
        File folder = new File(filePath);
        if (!folder.exists()) {
              folder.mkdirs();
        }
        try {

              File newFile = new File(filePath, fileName);
              if (!newFile.exists()) {
                    logger.info("NEW FILE NAME:-" + newFile);
                    FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(content);
                    fos.close();
              } else {

                    //String str = fileName.substring(0, fileName.indexOf("."));
                    //fileName = str + "_01" + ".jpg";
                    //newFile = new File(filePath, fileName);
                    FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(content);
                    fos.close();
              }
              

        } catch (Exception ex) {
        	
              ex.printStackTrace();
              return null;
        }
        return filePath+fileName;
  }*/
	

}