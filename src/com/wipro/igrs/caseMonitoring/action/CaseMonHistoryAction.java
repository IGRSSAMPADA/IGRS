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
package com.wipro.igrs.caseMonitoring.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caseMonitoring.bo.CaseMonHistoryBO;
import com.wipro.igrs.caseMonitoring.constant.CommonConstant;
import com.wipro.igrs.caseMonitoring.dto.CaseMonHistoryDTO;
import com.wipro.igrs.caseMonitoring.form.CaseMonHistoryForm;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.newreginit.action.CommonAction;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.rule.RegInitRule;
import com.wipro.igrs.util.GUIDGenerator;

public class CaseMonHistoryAction extends BaseAction {

	//--addded by satbir kumar---
	private  Logger logger = 
		(Logger) Logger.getLogger(CaseMonHistoryAction.class);
	
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
	 
	 private boolean uploadFile(FormFile filetobeuploaded,String fileName,String filePath) {
		 
	        
	        
	        try {
	              File folder = new File(filePath);
	              if (!folder.exists()) {
	              folder.mkdirs();
	               }
	              
	                    File newFile = new File(filePath, fileName);
	                   // logger.info("NEW FILE NAME:-" + newFile);
	                    FileOutputStream fos = new FileOutputStream(newFile);
	                    fos.write(filetobeuploaded.getFileData());
	                    fos.close();
	              

	        } catch (Exception ex) {
	              ex.printStackTrace();
	        }
	        return true;
	  }
	 
	 
		public static void downloadDocument(HttpServletResponse res, byte[] docContents, String fileName) {
			try {
				OutputStream os = res.getOutputStream();
				res.setContentType("application/download");
				res.setHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode(fileName,"UTF-8"));
				os.write(docContents);
				os.flush();
				os.close();
			} catch (Exception e) {
			}
		}
	 
	 
	 //--end of addition-------
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException, ServletException,
			Exception {
		
		String FORWARD_PAGE = "";
		CaseMonHistoryBO caseBO=null;
		
		CaseMonHistoryForm caseForm 	= (CaseMonHistoryForm)form;
		CaseMonHistoryDTO caseDTO=caseForm.getCaseDTO();
		caseBO=new CaseMonHistoryBO();
		String userId = (String) session.getAttribute("UserId");
		ArrayList errorList1 = new ArrayList();
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		caseDTO.setLoggedInUser(userId);
		String language=(String)session.getAttribute("languageLocale");
		if(language.equalsIgnoreCase("en")){
			session.setAttribute("modName","Case Monitoring");
			session.setAttribute("fnName","Create");
		}else{
			session.setAttribute("modName","प्रकरण मॉनिटरिंग");
			session.setAttribute("fnName","बनायें");
		}
		if(request.getParameter("fnName")!=null && "Create Pending Cases".equalsIgnoreCase(request.getParameter("fnName")))
		{
			
			caseDTO= new CaseMonHistoryDTO();
			caseForm.setUploadList(new ArrayList());
			caseDTO.setUploadId(1);
			FORWARD_PAGE="createCase";
		}
		if(request.getParameter("fnName")!=null && "Update Pending Cases".equalsIgnoreCase(request.getParameter("fnName")))
		{
			caseForm.setUploadList(new ArrayList());
			caseDTO.setUploadId(1);
			caseDTO.setIdCheck("");
			caseDTO= new CaseMonHistoryDTO();
			FORWARD_PAGE="searchCase";
		}
		if(form!=null)
		{
			if(caseForm.getActionName()!=null &&"setCaseType".equalsIgnoreCase(caseForm.getActionName()))
			{
				caseDTO.setCaseType("s");
				caseDTO.setSectionList(caseBO.getCaseSectionList(language ));
				caseDTO.setCaseStatusList(caseBO.getCaseStatusList(language));
				caseForm.setUploadList(new ArrayList());
				caseDTO.setUploadId(1);
				FORWARD_PAGE="createCase";
				caseForm.setActionName("");
			}
			else if(caseForm.getActionName()!=null &&"setCaseType1".equalsIgnoreCase(caseForm.getActionName()))
			{
				caseDTO.setCaseType("r");
				caseDTO.setDivisionList(caseBO.getCaseDivisionList(language));
				caseDTO.setCaseStatusList(caseBO.getCaseStatusList(language));
				caseForm.setUploadList(new ArrayList());
				caseDTO.setUploadId(1);
				FORWARD_PAGE="createCase";
				caseForm.setActionName("");
			}
			else if(caseForm.getActionName()!=null &&"save".equalsIgnoreCase(caseForm.getActionName()))
			{
				caseBO.saveCaseData(caseDTO,caseForm);
				FORWARD_PAGE="confirmation";
				caseForm.setActionName("");
			}
			else if((caseForm.getActionName()!=null &&"search".equalsIgnoreCase(caseForm.getActionName()))
					||(request.getParameter("fnName")!=null && "search".equalsIgnoreCase(request.getParameter("fnName"))))
			{
				ArrayList searchList=new ArrayList();
				searchList=caseBO.searchCaseData(caseDTO);
				ArrayList recordList=new ArrayList();
				if(searchList!=null && searchList.size()>0)
				{
					for(int i=0;i<searchList.size();i++)
					{
						ArrayList tempList=new ArrayList();
						tempList=(ArrayList)searchList.get(i);
						CaseMonHistoryDTO dto1=new CaseMonHistoryDTO();
						dto1.setReferenceNo((String)tempList.get(0));
						dto1.setCaseType((String)tempList.get(1));
						
						dto1.setSectionId((String)tempList.get(2));
						dto1.setCaseNumber((String)tempList.get(3));
						dto1.setPartyDtls((String)tempList.get(4));
						dto1.setPropDtls((String)tempList.get(5));
						dto1.setStampAmt((String)tempList.get(6));
						dto1.setStampDuty((String)tempList.get(7));
						dto1.setRegFee((String)tempList.get(8));
						dto1.setRecStampDuty((String)tempList.get(9));
						dto1.setRecRegFee((String)tempList.get(10));
						dto1.setCaseStatusId((String)tempList.get(11));
						if("1".equalsIgnoreCase(dto1.getCaseStatusId()))
						{
							if("en".equalsIgnoreCase(language))
							{
							dto1.setCaseStatus("Pending");
							}
							else
							{
								dto1.setCaseStatus("अपूर्ण");
							}
						}
						else if("2".equalsIgnoreCase(dto1.getCaseStatusId()))
						{
							if("en".equalsIgnoreCase(language))
							{
							dto1.setCaseStatus("Close");
							}
							else
							{
								dto1.setCaseStatus("बंद");
							}
						}
						
						dto1.setDivisionId((String)tempList.get(12));
						dto1.setRrcAmt((String)tempList.get(13));
						dto1.setRemarks((String)tempList.get(14));
						dto1.setCreatedDate((String)tempList.get(15));
						recordList.add(dto1);
					}
					
				}
				if(searchList.size()==0)
				{
				caseDTO.setIdCheck("Y");
				}
				else
				{
					caseDTO.setIdCheck("");
				}
				caseDTO.setSearchList(recordList);
				request.setAttribute("searchList",caseDTO.getSearchList());
				caseForm.setUploadList(new ArrayList());
				caseForm.setUrlList(new ArrayList());
				caseDTO.setUploadId(1);
				FORWARD_PAGE="searchCase";
				caseForm.setActionName("");
			}
			else if(request.getParameter("param")!=null && "view".equalsIgnoreCase(request.getParameter("param")))
			{
				caseDTO.setReferenceNo(request.getParameter("referenceNo"));
				caseDTO=caseBO.viewCaseData(caseDTO,language);
				if("s".equalsIgnoreCase(caseDTO.getCaseType()))
				{
					caseDTO.setSectionList(caseBO.getCaseSectionList(language));
					caseDTO.setCaseStatusList(caseBO.getCaseStatusList(language));
				}
				else if("r".equalsIgnoreCase(caseDTO.getCaseType()))
				{
					caseDTO.setDivisionList(caseBO.getCaseDivisionList(language));
					caseDTO.setCaseStatusList(caseBO.getCaseStatusList(language));
				}
				
				ArrayList list =caseBO.getuploadUrlList(caseDTO);
				caseForm.setUploadList(list);
				FORWARD_PAGE="viewCase";
				caseForm.setActionName("");
			}
			else if(caseForm.getActionName()!=null &&"edit".equalsIgnoreCase(caseForm.getActionName()))
			{
				//caseDTO=caseBO.searchCaseData(caseDTO);
				if("s".equalsIgnoreCase(caseDTO.getCaseType()))
				{
					caseDTO.setSectionList(caseBO.getCaseSectionList(language));
					caseDTO.setCaseStatusList(caseBO.getCaseStatusList(language));
				}
				else if("r".equalsIgnoreCase(caseDTO.getCaseType()))
				{
					caseDTO.setDivisionList(caseBO.getCaseDivisionList(language));
					caseDTO.setCaseStatusList(caseBO.getCaseStatusList(language));
				}
				FORWARD_PAGE="updateCase";
				caseForm.setActionName("");
			}
			else if(caseForm.getActionName()!=null &&"update".equalsIgnoreCase(caseForm.getActionName()))
			{
				boolean flag=caseBO.updateCaseData(caseDTO,caseForm);
				if(flag==true)
				{
					FORWARD_PAGE="updationConfirmation";
				}
				else
				{
					
				}
				
				caseForm.setActionName("");
			}
			
			caseForm.setCaseDTO(caseDTO);
		}
		
		//---added by satbir kumar----
		
		if(caseForm.getActionName()!=null &&caseForm.getActionName().equalsIgnoreCase("uploadfile"))
		{
			 ArrayList<CaseMonHistoryDTO> list = caseForm.getUploadList();
			 
			 if(list!=null && list.size()!=0)
			 {
				 
			 }else
			 
			 {
				 list=new ArrayList();
			 }
			
			 CaseMonHistoryDTO dto1=null;
			 
			try {
				FormFile uploadedFile = caseDTO.getFile(); 
				
				if ("".equals(uploadedFile.getFileName())) {
					errorList1.add(RegInitConstant.lbl_reg_init_upload_msg1);
				}
				String fileExt = getFileExtension(uploadedFile.getFileName());
				RegInitRule rule = new RegInitRule();
				int height=rule.getHeight(uploadedFile.getFileData());
				int width=rule.getWidth(uploadedFile.getFileData());
				logger.debug("height got:"+height);
				logger.debug("width got:"+width);
				rule.validateFileTypePartyRelated(fileExt);
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
					errorList1.add(RegInitConstant.lbl_reg_init_upload_msg2);
					  	
					request.setAttribute("errorListAddUpload", errorList1);
				} else {
					if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
						errorList1.add(RegInitConstant.lbl_reg_init_upload_msg5);
						request.setAttribute("errorListAddUpload", errorList1);
					}else
					if (fileSizeInKB > CommonConstant.MAX_FILE_SIZE) {
						errorList1.add(RegInitConstant.lbl_reg_init_upload_msg3);
						request.setAttribute("errorListAddUpload", errorList1);
					}
					
				else
				{
				int i=1;
				
				
				if(caseDTO.getUploadId()==1)
				{
					
				}
				else
				{
					i=caseDTO.getUploadId();
					
					i++;
				}
				
						dto1 = new CaseMonHistoryDTO();
						dto1.setFile(uploadedFile);
						dto1.setUploadId((i));
						dto1.setDocContents(uploadedFile.getFileData());
						dto1.setDocumentName(uploadedFile.getFileName());
							list.add(dto1);
							caseForm.setUploadList(list);
						 request.setAttribute("copyForm",caseDTO);
						 caseForm.setActionName(null);
						 FORWARD_PAGE="createCase";
					
				}
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(caseForm.getActionName()!=null &&caseForm.getActionName().equalsIgnoreCase("updatecaseuploadfile"))
		{
			 ArrayList<CaseMonHistoryDTO> list = caseForm.getUploadList();
			 
			 if(list!=null && list.size()!=0)
			 {
				 
			
					 
					 caseDTO.setUploadId(list.size());
					 
				 
			 }else
			 
			 {
				 list=new ArrayList();
			 }
			
			 CaseMonHistoryDTO dto1=null;
			 
			try {
				FormFile uploadedFile = caseDTO.getFile(); 
				
				if ("".equals(uploadedFile.getFileName())) {
					errorList1.add(RegInitConstant.lbl_reg_init_upload_msg1);
				}
				String fileExt = getFileExtension(uploadedFile.getFileName());
				RegInitRule rule = new RegInitRule();
				int height=rule.getHeight(uploadedFile.getFileData());
				int width=rule.getWidth(uploadedFile.getFileData());
				logger.debug("height got:"+height);
				logger.debug("width got:"+width);
				rule.validateFileTypePartyRelated(fileExt);
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
					errorList1.add(RegInitConstant.lbl_reg_init_upload_msg2);
					  	
					request.setAttribute("errorListAddUpload", errorList1);
				} else {
					if (height > RegInitConstant.MAX_FILE_HEIGHT || width > RegInitConstant.MAX_FILE_WIDTH) {
						errorList1.add(RegInitConstant.lbl_reg_init_upload_msg5);
						request.setAttribute("errorListAddUpload", errorList1);
					}else
					if (fileSizeInKB > CommonConstant.MAX_FILE_SIZE) {
						errorList1.add(RegInitConstant.lbl_reg_init_upload_msg3);
						request.setAttribute("errorListAddUpload", errorList1);
					}
					
				else
				{
				int i=1;
				
				
				if(caseDTO.getUploadId()==1)
				{
					
				}
				else
				{
					i=caseDTO.getUploadId();
					
					i++;
				}
				
						dto1 = new CaseMonHistoryDTO();
						dto1.setFile(uploadedFile);
						dto1.setUploadId((i));
						dto1.setDocContents(uploadedFile.getFileData());
						dto1.setDocumentName(uploadedFile.getFileName());
							list.add(dto1);
							caseForm.setUploadList(list);
						 request.setAttribute("copyForm",caseDTO);
						 caseForm.setActionName(null);
					FORWARD_PAGE="updateCase";
					
				}
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(caseForm.getActionName()!=null &&caseForm.getActionName().equalsIgnoreCase("updatecasedownloadfile"))
		{
		
			 String id = String.valueOf(caseDTO.getUploadId());
		 
		 ArrayList<CaseMonHistoryDTO> listDto = caseForm.getUploadList();
		 CaseMonHistoryDTO download =null;
		 
		 for(int i=0;i<listDto.size();i++)
		 {
			 CaseMonHistoryDTO dto = listDto.get(i);
			 
			 if((String.valueOf(dto.getUploadId())).equalsIgnoreCase(id))
			 {
				 download = dto;
			 }
			 
			 
		 }
		 
		 if(download.getDbaseUpload()!=null && download.getDbaseUpload().equalsIgnoreCase("D"))
		 {
			 
			 String fileName= download.getUploadDocPath();
			 
			 byte[] b=DMSUtility.getDocumentBytes(fileName);
			 
			 download.setDocContents(b);
		 }
		 
		
		 
		 DMSUtility.downloadDocument(response, download.getDocumentName(),download.getDocContents());
		 request.setAttribute("reginitproperty", caseForm);
		 FORWARD_PAGE="updateCase";
		 caseForm.setActionName(null);
		 
		}
		
		
		
		if(caseForm.getActionName()!=null &&caseForm.getActionName().equalsIgnoreCase("downloadfile"))
		{
		
			 String id = String.valueOf(caseDTO.getUploadId());
		 
		 ArrayList<CaseMonHistoryDTO> listDto = caseForm.getUploadList();
		 CaseMonHistoryDTO download =null;
		 
		 for(int i=0;i<listDto.size();i++)
		 {
			 CaseMonHistoryDTO dto = listDto.get(i);
			 
			 if((String.valueOf(dto.getUploadId())).equalsIgnoreCase(id))
			 {
				 download = dto;
			 }
			 
			 
		 }
		 
		 if(download.getDbaseUpload()!=null && download.getDbaseUpload().equalsIgnoreCase("D"))
		 {
			 
			 String fileName= download.getUploadDocPath();
			 
			 byte[] b=DMSUtility.getDocumentBytes(fileName);
			 
			 download.setDocContents(b);
		 }
		 
		
		 
		 DMSUtility.downloadDocument(response, download.getDocumentName(),download.getDocContents());
		 request.setAttribute("reginitproperty", caseForm);
		 FORWARD_PAGE="createCase";
		 caseForm.setActionName(null);
		 
		}
		
		 
		if(caseForm.getActionName()!=null &&caseForm.getActionName().equalsIgnoreCase("updatecaseremovefile"))
		 {
			 String id =String.valueOf(caseDTO.getUploadId());
			 
			 ArrayList<CaseMonHistoryDTO> listDto = caseForm.getUploadList();
			 
			 
			 for(int i=0;i<listDto.size();i++)
			 {
				 CaseMonHistoryDTO dto = listDto.get(i);
				 
				 if((String.valueOf(dto.getUploadId())).equalsIgnoreCase(id))
				 {
					 
					 if(dto.getDbaseUpload()!=null && dto.getDbaseUpload().equalsIgnoreCase("D"))
					 {
						 caseDTO.setUploadSrNo(dto.getUploadSrNo());
	
						 caseBO.getremoveUpload(caseDTO);
							
					 }
					 
					 listDto.remove(i);
				 }
				 
				 
			 }
			 request.setAttribute("reginitproperty", caseForm);
			 FORWARD_PAGE="updateCase";
			 caseForm.setActionName(null);
		 }
		
		 
		 
		if(caseForm.getActionName()!=null &&caseForm.getActionName().equalsIgnoreCase("removefile"))
		 {
			 String id =String.valueOf(caseDTO.getUploadId());
			 
			 ArrayList<CaseMonHistoryDTO> listDto = caseForm.getUploadList();
			 
			 
			 for(int i=0;i<listDto.size();i++)
			 {
				 CaseMonHistoryDTO dto = listDto.get(i);
				 
				 if((String.valueOf(dto.getUploadId())).equalsIgnoreCase(id))
				 {
					 
					 if(dto.getDbaseUpload()!=null && dto.getDbaseUpload().equalsIgnoreCase("D"))
					 {
						 caseDTO.setUploadSrNo(dto.getUploadSrNo());
	
						 caseBO.getremoveUpload(caseDTO);
							
					 }
					 
					 listDto.remove(i);
				 }
				 
				 
			 }
			 request.setAttribute("reginitproperty", caseForm);
			 FORWARD_PAGE="createCase";
			 caseForm.setActionName(null);
		 }
		//---end of addition----
		
		return mapping.findForward(FORWARD_PAGE);
	}


}
