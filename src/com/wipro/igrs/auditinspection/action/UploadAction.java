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
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.auditinspection.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.auditinspection.form.UploadForm;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.util.PropertiesFileReader;

/**
 * MyEclipse Struts Creation date: 05-12-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class UploadAction extends BaseAction {
	
	Logger logger = (Logger) Logger.getLogger(UploadAction.class);
	/*
	 * Generated Methods
	 */

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); 
		UploadForm uploadForm = (UploadForm) form;
		ArrayList errorList = new ArrayList();
		//added by Shreeraj
		String filePath = null; 
		logger.debug("upload action ------------->"+uploadForm.getUploadAction());
		logger.debug("attach actiom---------------->"+uploadForm.getAttachAction());
		//String folName = "D:\\Upload\\";
		PropertiesFileReader pr=null;
		String folName=null;
		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			folName = pr.getValue("igrs_audit_upload_path");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				File folder = new File(folName+"10");
				 if (!folder.exists()) 
				 {
		              folder.mkdirs();
		              File folder1 = new File(folName+"10\\Audit Report");
		              try {
						filePath=pr.getValue("igrs_audit_upload_path")+"10\\Audit Report\\";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		              if (!folder1.exists())
			              {
				              folder1.mkdirs();
			              }
			     }
					 	else
					 	{
					 		File folder1 = new File(folder+"\\Audit Report");
				              try {
								filePath=pr.getValue("igrs_audit_upload_path")+"10\\Audit Report\\";
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				              if (!folder1.exists())
					              {
						              folder1.mkdirs();
					              }
			              }
				 logger.info("file path"+filePath);
				session.setAttribute("FilePath", filePath);
				AuditInspectionRule rule = new AuditInspectionRule();
				int currFileSize = 0;
				
				if(request.getParameter("dms")!=null){
                	if(request.getParameter("dms").equalsIgnoreCase("retrieval")){
                		uploadForm.setUploadAction("");
                		if(request.getParameter("path")!=null){
                			
                			String partyType="";
                			//filePath=request.getParameter("path").toString();
          //      			logger.debug("retrieval path-->"+filePath);
                		//	DMSUtility dmsUtil=new DMSUtility();
                		//	dmsUtil.readImage(filePath);
                			String filename = request.getParameter("path").toString();
                			
             			   // set the http content type to "APPLICATION/OCTET-STREAM
             			   

             			   // initialize the http content-disposition header to
             			   // indicate a file attachment with the default filename
             			   // "myFile.txt"
             			  // String fileName = (String)formDTO.getCompThumbPath();
             			   //Filename=\"myFile.txt\"";
             			   
             			   //code commented by shruti----2 june 2014
             			  /*response.setContentType("application/download");
             			   response.setHeader("Content-Disposition", "attachment; filename="
             						+ URLEncoder.encode(filename,"UTF-8"));
             			   
             			   File fileToDownload = new File(filename);
             			   FileInputStream fileInputStream = new FileInputStream(fileToDownload);
             			   OutputStream out = response.getOutputStream();
             			   byte[] buf = new byte[2048];

             			   int readNum;
             			   while ((readNum=fileInputStream.read(buf))!=-1)
             			   {
             			      out.write(buf,0,readNum);
             			   }
             			   fileInputStream.close();
             			   out.close();*/
                			
                			 byte[] contents =null;
              	            contents=DMSUtility.getDocumentBytes(filename);
              	           downloadDocument(response, contents, filename);
              	           //end of code by shruti
              	           
                			
                			/*if("biometricDetails".equalsIgnoreCase(formDTO.getCancelledPage()))
                			{
                				forwardJsp =  RegCompCheckerConstant.PARTIES_BIOMETRIC_DETAILS;
                			}
                			else if("viewWitness".equalsIgnoreCase(formDTO.getCancelledPage()))
                			{
                				forwardJsp="witnessView";
                			}
                				
                			else
                			{
                				forwardJsp = RegCompCheckerConstant.VIEW_COMPLIANCE_LIST_NEW;
                			}*/
                			}
                		
                	}
                }
				

		//added by ShreeRaj Khare
		if (uploadForm.getAttachAction().equalsIgnoreCase("attach")) {
			logger.debug("attach----------------------->");
			if (session.getAttribute("attachment1") == null) 
			{
				logger.debug("attachment null----------------------->");
				try{
					// Get the list of files
					FormFile uploadedFile = uploadForm.getTheFile();
					int size = uploadedFile.getFileSize();
					// save file in the app server
					String fileExt = getFileExtension(uploadedFile.getFileName());
					
					rule = new AuditInspectionRule();
					errorList = rule.validateFileType(fileExt);
					if (rule.isError()) 
					{
						request.setAttribute("errorsList", errorList);
						return mapping.findForward("upload");
					} 
					else if (size > AuditInspectionConstant.MAX_FILE_SIZE) 
					{
						errorList.add("<li><font color="+ "red"	+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
						request.setAttribute("errorsList", errorList);
						return mapping.findForward("upload");
					} 
					else
					{
						String newFile=filePath+ uploadedFile.getFileName();
						uploadForm.getuDTO().setFileName(newFile);
						uploadForm.getuDTO().setFileData(uploadedFile.getFileData());
						ArrayList lsUPloadDTO = new ArrayList();
						UploadFileDTO uploadFileDTO = new UploadFileDTO();
						uploadFileDTO.setFileName(uploadedFile.getFileName());
						uploadFileDTO.setFilePath(newFile);
						uploadFileDTO.setFileSize(size);
						uploadFileDTO.setFileData(uploadedFile.getFileData());
						lsUPloadDTO.add(uploadFileDTO);
						logger.info("File Content is: "+uploadFileDTO.getFilePath()+uploadFileDTO.getFileData());
						try {
							File newFile1 = new File(uploadFileDTO.getFilePath());
							FileOutputStream fos = new FileOutputStream(newFile1);
							
							fos.write(uploadFileDTO.getFileData());
							fos.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						session.setAttribute("attachment1", lsUPloadDTO);
					}
				}
				catch (Exception e) {
					errorList.add("Unable to upload file. Please try again.");
					request.setAttribute("errorsList", errorList);
				}
				
			}
			else{
				logger.debug("attachment not null----------------------->");
			try{
				ArrayList arrList = (ArrayList) session.getAttribute("attachment1");
				ArrayList arrForSession = new ArrayList();
				UploadFileDTO uploadFileDTO=null;
				for (int i = 0; i < arrList.size(); i++) 
				{
					uploadFileDTO = new UploadFileDTO();
					uploadFileDTO = (UploadFileDTO) arrList.get(i);
					
					currFileSize = currFileSize + uploadFileDTO.getFileSize();// set doctype to uploaddto
					arrForSession.add(uploadFileDTO);				
					}
			
			FormFile uploadedFile = uploadForm.getTheFile();
			uploadForm.setTheFile(uploadedFile);
			if ("".equals(uploadedFile.getFileName())) {
				errorList.add("Invalid file selection. Please try again.");
			}
			String fileExt = getFileExtension(uploadedFile.getFileName());
			errorList = rule.validateFileType(fileExt);
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String thumbSize="("+fileSize+"MB)";
			if (rule.isError())
			{
				errorList.add("Invalid file type. Please select another file.");   		
				request.setAttribute("errorsList", errorList);
			}
			else 
			{
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add("File size is Greater than 10 MB. Please select another file.");
					request.setAttribute("errorsList", errorList);
				} else {
					
					if (!uploadedFile.getFileName().equals("")) {
					uploadFileDTO = new UploadFileDTO();
					uploadFileDTO.setFileName(uploadedFile.getFileName());
					uploadFileDTO.setFileData(uploadedFile.getFileData());
					uploadFileDTO.setFilePath(filePath+ uploadedFile.getFileName());
					logger.info("File Content is: "+uploadFileDTO.getFilePath()+uploadFileDTO.getFileData());
					try {
						File newFile1 = new File(uploadFileDTO.getFilePath());
						FileOutputStream fos = new FileOutputStream(newFile1);
						fos.write(uploadFileDTO.getFileData());
						fos.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					arrForSession.add(uploadFileDTO);
				
					//uploadForm.getuDTO().setFileName( uploadedFile.getFileName());
					//uploadForm.getuDTO().setFileData(uploadedFile.getFileData());
				}// End if
					
					uploadForm.setDocumentName(uploadedFile.getFileName());
					uploadForm.setDocContents(uploadedFile.getFileData());
					uploadForm.setDocSize(thumbSize);
					uploadForm.setDocCheck("thloded");
					ArrayList lsUPloadDTO = new ArrayList();
					
					//UploadFileDTO uploadFileDTO = new UploadFileDTO();
					uploadFileDTO.setFileName(uploadedFile.getFileName());
					uploadFileDTO.setFilePath(filePath+ uploadedFile.getFileName());
					uploadFileDTO.setFileSize(size);

					lsUPloadDTO.add(uploadFileDTO);
					logger.info(arrForSession.size()+" "+arrForSession);
					uploadForm.getuDTO().setFilelist(arrForSession);
					session.setAttribute("attachment1", arrForSession);
				}
			}
			} catch (Exception e) {
				errorList.add("Unable to upload file. Please try again.");
				request.setAttribute("errorsList", errorList);
			}
			uploadForm.setAttachAction("");
			return mapping.findForward("upload");
		}
			uploadForm.setAttachAction("");
			return (mapping.findForward("upload"));
		}	
			
		
		//when user clicks on close
		if (uploadForm.getUploadAction().equalsIgnoreCase("saveDoc")) {
			ArrayList al=new ArrayList();
			al=uploadForm.getuDTO().getFilelist();
			if(al.size()==0){
			//if (session.getAttribute("attachment1") == null) {
				logger.info("File Content is: "+uploadForm.getuDTO().getFileName()+uploadForm.getuDTO().getFileData());
				String fileName=uploadForm.getuDTO().getFileName();
				byte[] fileData=uploadForm.getuDTO().getFileData();
				
				try {
					File newFile = new File(fileName);
					FileOutputStream fos = new FileOutputStream(newFile);
					fos.write(fileData);
					fos.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				//uploadForm.setUploadAction("yy");
				return mapping.findForward("uploadDoc");
			}
			else{
				try {
				
				
				al=uploadForm.getuDTO().getFilelist();
	for(int i=0;i<al.size();i++){
		UploadFileDTO dto=(UploadFileDTO)al.get(i);
		String fileName= dto.getFileName();
		byte[] fileData=dto.getFileData();
	
				
					File newFile = new File(filePath+fileName);
					FileOutputStream fos = new FileOutputStream(newFile);
					fos.write(fileData);
					fos.close();
					logger.info("File Uploaded is::::::::::::::::"+fileName);
	}} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			//uploadForm.setUploadAction("yy");
			return mapping.findForward("uploadDoc");
		}
		/*if (uploadForm.getAttachAction().equalsIgnoreCase("attach")) {
			if (session.getAttribute("attachment1") == null) {
				// Get the list of files
				FormFile strFileName = uploadForm.getTheFile();
				int size = strFileName.getFileSize();
			
				// save file in the app server
				String fileExt = getFileExtension(strFileName.getFileName());
				
				rule = new AuditInspectionRule();
				errorList = rule.validateFileType(fileExt);
				if (rule.isError()) {
					request.setAttribute("errorsList", errorList);
					return mapping.findForward("upload");
				} else if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList
							.add("<li><font color="
									+ "red"
									+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
					request.setAttribute("errorsList", errorList);
					return mapping.findForward("upload");

				} else {

					try {
						File newFile = new File(filePath+ strFileName.getFileName());
						FileOutputStream fos = new FileOutputStream(newFile);
						fos.write(strFileName.getFileData());
						fos.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					ArrayList lsUPloadDTO = new ArrayList();
					UploadFileDTO uploadFileDTO = new UploadFileDTO();
					uploadFileDTO.setFileName(strFileName.getFileName());// set
																			// doctype
																			// to
																			// uploaddto
					uploadFileDTO.setFileSize(size);

					lsUPloadDTO.add(uploadFileDTO);
					session.setAttribute("attachment1", lsUPloadDTO);
				}
			} else {
				ArrayList arrList = (ArrayList) session
						.getAttribute("attachment1");
				

				ArrayList arrForSession = new ArrayList();

				UploadFileDTO uploadFileDTO = null;
				for (int i = 0; i < arrList.size(); i++) {
					uploadFileDTO = (UploadFileDTO) arrList.get(i);
					currFileSize = currFileSize + uploadFileDTO.getFileSize();// set
																				// doctype
																				// to
																				// uploaddto
					arrForSession.add(uploadFileDTO);
				}
				FormFile forFile = uploadForm.getTheFile();

				String fileExt = getFileExtension(forFile.getFileName());
				
				rule = new AuditInspectionRule();
				errorList = rule.validateFileType(fileExt);
				if (rule.isError()) {
					request.setAttribute("errorsList", errorList);
					return mapping.findForward("upload");
				} else {
					int size = forFile.getFileSize();
					currFileSize = currFileSize + size;
					if (currFileSize > AuditInspectionConstant.MAX_FILE_SIZE) {
						errorList
								.add("<li><font color="
										+ "red"
										+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
						request.setAttribute("errorsList", errorList);
						return mapping.findForward("upload");
					} else {
						// uploadForm.setSizeOfFile(currFileSize);
						if (!forFile.getFileName().equals("")) {
							uploadFileDTO = new UploadFileDTO();
							uploadFileDTO.setFileName(forFile.getFileName());
							arrForSession.add(uploadFileDTO);
							try {
								File newFile = new File(filePath+ forFile.getFileName());
								FileOutputStream fos = new FileOutputStream(newFile);
								fos.write(forFile.getFileData());
								fos.close();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}// End if
						session.removeAttribute("attchment1");
						session.setAttribute("attachment1", arrForSession);

					}// else END IF
				}// End Else Block
			}// else END IF
			return (mapping.findForward("upload"));
		}// end if*/

		if (uploadForm.getRemoveAction().equalsIgnoreCase("remove")) {
			
			ArrayList arrList = (ArrayList) session.getAttribute("attachment1");

			try {
				String param[] = request.getParameterValues("checkbox");
				for (int i = 0; i < param.length; i++) 
				{
					String checkedFileName = param[i];	
					for (int j = 0; j < arrList.size(); j++) {
						UploadFileDTO uploadFileDTO = (UploadFileDTO) arrList.get(j);
						if (uploadFileDTO.getFileName().equalsIgnoreCase(checkedFileName))
						{
							File newFile = new File(filePath + checkedFileName);
							newFile.delete();
							arrList.remove(j);
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			session.removeAttribute("attachemnt1");
			session.setAttribute("attachment1", arrList);
			return mapping.findForward("upload");
		}
		

		return mapping.findForward("failure");
	}

	private String getFileExtension(String fileName) {
		int pos = fileName.lastIndexOf(".");
		String strExt = fileName.substring(pos + 1, fileName.length());
		return strExt;
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
}