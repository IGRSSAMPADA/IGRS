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


package com.wipro.igrs.reginit.action;


import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.wipro.igrs.UserRegistration.bd.UserRegistrationBD;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.IGRSCommon;

import com.wipro.igrs.reginit.bo.RegCommonBO;
import com.wipro.igrs.reginit.constant.RegInitConstant;

import com.wipro.igrs.reginit.dto.CommonDTO;
import com.wipro.igrs.reginit.dto.PropertyValuationDTO;
import com.wipro.igrs.reginit.dto.RegCommonDTO;
import com.wipro.igrs.reginit.dto.RegCompletDTO;
import com.wipro.igrs.reginit.form.RegCommonForm;
import com.wipro.igrs.reginit.form.RegCompletionForm;

import com.wipro.igrs.propertyvaluation.form.PropertyValuationForm;


public class UploadAction extends  Action {
	private  Logger logger = 
		(Logger) Logger.getLogger(CommonAction.class);
	//static private int count = 0;
     private HashMap map =null;
     private HashMap map2 =null;
    
  
    boolean bol = true;
    static private String key = "key";
    //static private int keyCount=0;
   
	@SuppressWarnings("unchecked")
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response ) throws Exception {
			
		
			 HttpSession session = request.getSession();
		
			 //if(session.getAttribute("forwardPath")==null)
			 //session.setAttribute("forwardPath", "./regInit.do");
			 session.setAttribute("modName","Registration Process");
			 session.setAttribute("fnName","Initiation");
			String forward="";
			RegCommonBO commonBo = new RegCommonBO();
			
			RegCommonForm regForm;
			RegCompletionForm eForm;
						
			

			/*if(request.getAttribute("regFormDashboard")!=null){
				regForm=(RegCommonForm)request.getAttribute("regFormDashboard");
				
				request.setAttribute("deedId", regForm.getDeedID());
    			request.setAttribute("roleId", regForm.getPartyType());
    			//request.setAttribute("hidnRegTxnId", regForm.getHidnRegTxnId());
    			
			}
			else*/
			String fromParam;
			String formName;
			String actionName;
			if(request.getParameter("fromParam")!=null){
				fromParam=request.getParameter("fromParam");
			
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
			
			 
			    ArrayList errorList = new ArrayList();
                if (RegInitConstant.UPLOAD_FILE.equals(actionName)) {
                	
    				try {
    					FormFile uploadedFile = regForm.getCertificate();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
    				request.setAttribute("deedId", regForm.getDeedID());
    				}else{
    					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=regForm.getJspName();
    				if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
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
                if (RegInitConstant.UPLOAD_FILE_ANGLE_1.equals(actionName)) {
                	
                	
                	
                	
    				try {
    					
    					commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    					
    					FormFile uploadedFile = eForm.getRegcompletDto().getPropImage1();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=eForm.getForwardJsp();
    				request.setAttribute("deedId", regForm.getDeedID());
    				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
    			} 
                
                
                if (RegInitConstant.UPLOAD_FILE_ANGLE_2.equals(actionName)) {
                	
                	
                	
                	
    				try {
    					commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    					FormFile uploadedFile = eForm.getRegcompletDto().getPropImage2();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=eForm.getForwardJsp();
    				request.setAttribute("deedId", regForm.getDeedID());
    				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
    			} 
                
                if (RegInitConstant.UPLOAD_FILE_ANGLE_3.equals(actionName)) {
                	
                	
                	
                	
    				try {
    					commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    					FormFile uploadedFile = eForm.getRegcompletDto().getPropImage3();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=eForm.getForwardJsp();
    				request.setAttribute("deedId", regForm.getDeedID());
    				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
    			} 
                
 if (RegInitConstant.UPLOAD_PROPERTY_MAP.equals(actionName)) {
                	
                	
                	
                	
    				try {
    					commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
    					FormFile uploadedFile = eForm.getRegcompletDto().getPropertyMap();
    					if ("".equals(uploadedFile.getFileName())) {
    						errorList.add("Invalid file selection. Please try again.");
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
    					
    					
    				
    					   
    					if (rule.isError()) {
    						errorList.add("Invalid file type. Please select another file.");
    						  	
    						request.setAttribute("errorsList", errorList);
    					} else {
    						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
    							errorList.add("File size is Greater than 10 MB. Please select another file.");
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
    					errorList.add("Unable to upload file. Please try again.");
    					request.setAttribute("errorsList", errorList);
    				}
    				
    				forward=eForm.getForwardJsp();
    				request.setAttribute("deedId", regForm.getDeedID());
    				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
    			} 
 
 if (RegInitConstant.UPLOAD_PROPERTY_RIN.equals(actionName)) {
 	
 	
 	
 	
		try {
			commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
			FormFile uploadedFile = eForm.getRegcompletDto().getPropertyRin();
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.");
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
			
			
		
			   
			if (rule.isError()) {
				errorList.add("Invalid file type. Please select another file.");
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add("File size is Greater than 10 MB. Please select another file.");
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
			errorList.add("Unable to upload file. Please try again.");
			request.setAttribute("errorsList", errorList);
		}
		
		forward=eForm.getForwardJsp();
		request.setAttribute("deedId", regForm.getDeedID());
		request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
	} 
 
 if (RegInitConstant.UPLOAD_PROPERTY_KHASRA.equals(actionName)) {
 	
 	
 	
 	
		try {
			commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
			FormFile uploadedFile = eForm.getRegcompletDto().getPropertyKhasra();
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.");
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
			
			
		
			   
			if (rule.isError()) {
				errorList.add("Invalid file type. Please select another file.");
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add("File size is Greater than 10 MB. Please select another file.");
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
			errorList.add("Unable to upload file. Please try again.");
			request.setAttribute("errorsList", errorList);
		}
		
		forward=eForm.getForwardJsp();
		request.setAttribute("deedId", regForm.getDeedID());
		request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
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
				errorList.add("Invalid file selection. Please try again.");
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
			
			
		
			   
			if (rule.isError()) {
				errorList.add("Invalid file type. Please select another file.");
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add("File size is Greater than 10 MB. Please select another file.");
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
			errorList.add("Unable to upload file. Please try again.");
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
				errorList.add("Invalid file selection. Please try again.");
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
			
			
		
			   
			if (rule.isError()) {
				errorList.add("Invalid file type. Please select another file.");
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add("File size is Greater than 10 MB. Please select another file.");
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
			errorList.add("Unable to upload file. Please try again.");
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
				errorList.add("Invalid file selection. Please try again.");
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
			
			
		
			   
			if (rule.isError()) {
				errorList.add("Invalid file type. Please select another file.");
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add("File size is Greater than 10 MB. Please select another file.");
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
			errorList.add("Unable to upload file. Please try again.");
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
				errorList.add("Invalid file selection. Please try again.");
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
			
			
		
			   
			if (rule.isError()) {
				errorList.add("Invalid file type. Please select another file.");
				  	
				request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add("File size is Greater than 10 MB. Please select another file.");
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
			errorList.add("Unable to upload file. Please try again.");
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
	errorList.add("Invalid file selection. Please try again.");
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




if (rule.isError()) {
	errorList.add("Invalid file type. Please select another file.");
	  	
	request.setAttribute("errorsList", errorList);
} else {
	if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
		errorList.add("File size is Greater than 10 MB. Please select another file.");
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
errorList.add("Unable to upload file. Please try again.");
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
	errorList.add("Invalid file selection. Please try again.");
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




if (rule.isError()) {
	errorList.add("Invalid file type. Please select another file.");
	  	
	request.setAttribute("errorsList", errorList);
} else {
	if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
		errorList.add("File size is Greater than 10 MB. Please select another file.");
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
errorList.add("Unable to upload file. Please try again.");
request.setAttribute("errorsList", errorList);
}

forward="editPropDetails";
request.setAttribute("deedId", regForm.getDeedID());
request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
*/} 
			
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
		}
		return "";
	}
	
	
	

}