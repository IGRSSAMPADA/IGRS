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
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.auditinspection.form.DROUploadForm;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.util.PropertiesFileReader;
//Added new common code for MIME Type check of Uploaded file
import com.wipro.igrs.common.mime.MIMECheck;



public class DROUploadAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
		
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward(); // return value
		DROUploadForm uploadForm = (DROUploadForm) form;
		ArrayList errorList = new ArrayList();
		PropertiesFileReader pr=null;
		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null; 
		String folName = null;
		//folName="D:\\" +"Upload\\";
		try {
			folName=pr.getValue("igrs_audit_upload_path");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File folder = new File(folName+"10");
		 if (!folder.exists()) 
		 {
              folder.mkdirs();
              String path=folName+"10\\"+"DRO Inspection Report\\";
              File folder1 = new File(path);
              if (!folder1.exists())
	           {
		              folder1.mkdirs();
	           }
              File folder2 = new File(path+"1");
              if (!folder2.exists())
	           {
		              folder2.mkdirs();
	           }
              try {
				filePath=pr.getValue("igrs_audit_upload_path")+"10\\"+"DRO Inspection Report\\"+"1\\";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
		else
		{
			 		 String path=folName+"10\\"+"DRO Inspection Report\\";
			 		 File folder1 = new File(path);
			 		 if (!folder1.exists())
		              {
			              folder1.mkdirs();
		              }
			 		 File folder2 = new File(path+"1");
		              if (!folder2.exists())
			          {
				              folder2.mkdirs();
			           }
		              try {
						filePath=pr.getValue("igrs_audit_upload_path")+"10\\"+"DRO Inspection Report\\"+"1\\";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	      }
		session.setAttribute("FilePath", filePath);
		AuditInspectionRule rule = null;
		int currFileSize = 0;
		HashMap lekfilesmap=null;
		
		if(request.getParameter("upload")!=null && request.getParameter("upload").equals("lek"))
		{
			if (request.getParameter("attachmenttype")!=null) 
			{
				if(session.getAttribute("lekfiles") == null)
				{
					lekfilesmap=new HashMap();
				}
				else
				{
					lekfilesmap=(HashMap)session.getAttribute("lekfiles");
				}
				FormFile strFileName = uploadForm.getTheFile();
				int size = strFileName.getFileSize();
				/*String fileExt = getFileExtension(strFileName.getFileName());
				rule = new AuditInspectionRule();
				errorList = rule.validateFileType(fileExt);*/
				MIMECheck mimeCheck = new MIMECheck();
				String fileExt=mimeCheck.getFileExtension(strFileName.getFileName());
		        Boolean mime = mimeCheck.validateMIMEAndFileType(strFileName);  // common code to check mime type and validation.
				 // new code finish.
				if (!mime) {
					errorList
					.add("<li><font color="
							+ "red"
							+ ">Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.</font></li>");
					request.setAttribute("errorsList", errorList);
					return mapping.findForward("uploadDRO");
				} 
				else if (size > AuditInspectionConstant.MAX_FILE_SIZE) 
				{
					errorList.add("<li><font color="+ "red"+ ">File size is Greater than 10MB,Please select Other File.</font></li>");
					request.setAttribute("errorsList", errorList);
					return mapping.findForward("uploadDRO");
				} 
				else 
				{
					try 
					{
						File newFile = new File(filePath+ strFileName.getFileName());
						FileOutputStream fos = new FileOutputStream(newFile);
						fos.write(strFileName.getFileData());
						fos.close();
					} 
					catch (Exception ex) 
					{
						ex.printStackTrace();
					}
					ArrayList lsUPloadDTO = new ArrayList();
					UploadFileDTO uploadFileDTO = new UploadFileDTO();
					uploadFileDTO.setFileName(strFileName.getFileName());
					uploadFileDTO.setTheFile(strFileName);						
					uploadFileDTO.setFileSize(size);
					uploadFileDTO.setDocId(uploadForm.getActionType());
					lsUPloadDTO.add(uploadFileDTO);
					lekfilesmap.put(request.getParameter("attachmenttype"),uploadFileDTO);
					session.setAttribute("lekfiles", lekfilesmap);
				}
			} 
			return (mapping.findForward("uploadDROclose"));
		
		}
		if (uploadForm.getAttachAction().equalsIgnoreCase("attach")&& request.getParameter("upload")!=null && request.getParameter("upload").equals("pending")) 
		{
				if (session.getAttribute("pendingfiles") == null ) 
				{
						FormFile strFileName = uploadForm.getTheFile();
						int size = strFileName.getFileSize();
						/*String fileExt = getFileExtension(strFileName.getFileName());
						rule = new AuditInspectionRule();
						errorList = rule.validateFileType(fileExt);*/
						MIMECheck mimeCheck = new MIMECheck();
						String fileExt=mimeCheck.getFileExtension(strFileName.getFileName());
				        Boolean mime = mimeCheck.validateMIMEAndFileType(strFileName);  // common code to check mime type and validation.
						 // new code finish.
						if (!mime) 
						{
							errorList
							.add("<li><font color="
									+ "red"
									+ ">Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.</font></li>");
							request.setAttribute("errorsList", errorList);
							return mapping.findForward("uploadDRO");
						} 
						else if (size > AuditInspectionConstant.MAX_FILE_SIZE) 
						{
							errorList.add("<li><font color="+ "red"+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
							request.setAttribute("errorsList", errorList);
							return mapping.findForward("uploadDRO");
						} 
						else 
						{
							try 
							{
								File newFile = new File(filePath+ strFileName.getFileName());
								FileOutputStream fos = new FileOutputStream(newFile);
								fos.write(strFileName.getFileData());
								fos.close();
							} 
							catch (Exception ex) 
							{
								ex.printStackTrace();
							}
							ArrayList lsUPloadDTO = new ArrayList();
							UploadFileDTO uploadFileDTO = new UploadFileDTO();
							uploadFileDTO.setFileName(strFileName.getFileName());
							uploadFileDTO.setFilePath(filePath+strFileName.getFileName());											
							uploadFileDTO.setTheFile(strFileName);							
							uploadFileDTO.setFileSize(size);
							uploadFileDTO.setDocId(uploadForm.getActionType());
							lsUPloadDTO.add(uploadFileDTO);
							session.setAttribute("pendingfiles", lsUPloadDTO);
						}
					} 
					else 
					{
							ArrayList arrList = (ArrayList) session.getAttribute("pendingfiles");
							ArrayList arrForSession = new ArrayList();
							UploadFileDTO uploadFileDTO = null;
							for (int i = 0; i < arrList.size(); i++) 
							{
								uploadFileDTO = (UploadFileDTO) arrList.get(i);
								currFileSize = currFileSize + uploadFileDTO.getFileSize();
								arrForSession.add(uploadFileDTO);
							}
							FormFile forFile = uploadForm.getTheFile();
							/*String fileExt = getFileExtension(forFile.getFileName());
							rule = new AuditInspectionRule();
							errorList = rule.validateFileType(fileExt);*/
							MIMECheck mimeCheck = new MIMECheck();
							String fileExt=mimeCheck.getFileExtension(forFile.getFileName());
					        Boolean mime = mimeCheck.validateMIMEAndFileType(forFile);  // common code to check mime type and validation.
							 // new code finish.
							if (!mime) 
							{errorList
								.add("<li><font color="
										+ "red"
										+ ">Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.</font></li>");
								request.setAttribute("errorsList", errorList);
								return mapping.findForward("uploadDRO");
							} 
							else 
							{
								int size = forFile.getFileSize();
								currFileSize = currFileSize + size;
								if (currFileSize > AuditInspectionConstant.MAX_FILE_SIZE) {
									errorList.add("<li><font color="+ "red"+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
									request.setAttribute("errorsList", errorList);
									return mapping.findForward("uploadDRO");
								} else {
									FormFile strFileName = uploadForm.getTheFile();
									// uploadForm.setSizeOfFile(currFileSize);
									if (!forFile.getFileName().equals("")) {
										uploadFileDTO = new UploadFileDTO();
										uploadFileDTO.setFileName(forFile.getFileName());
										uploadFileDTO.setFilePath(filePath+strFileName.getFileName());		
										uploadFileDTO.setTheFile(forFile);
										uploadFileDTO.setDocId(uploadForm.getActionType());
										arrForSession.add(uploadFileDTO);
										try {
											File newFile = new File(filePath+ forFile.getFileName());
											FileOutputStream fos = new FileOutputStream(newFile);
											fos.write(forFile.getFileData());
											fos.close();
										} catch (Exception ex) {
											ex.printStackTrace();
										}
									}
									session.removeAttribute("pendingfiles");
									session.setAttribute("pendingfiles", arrForSession);
			
								}
							}
					}
			return (mapping.findForward("uploadDRO"));
		}
		
		else if (uploadForm.getAttachAction().equalsIgnoreCase("attach")&& request.getParameter("upload")!=null && request.getParameter("upload").equals("audit")) 
		{
					if (session.getAttribute("auditfiles") == null ) 
					{
						FormFile strFileName = uploadForm.getTheFile();
						int size = strFileName.getFileSize();
						/*String fileExt = getFileExtension(strFileName.getFileName());
						rule = new AuditInspectionRule();
						errorList = rule.validateFileType(fileExt);*/
						MIMECheck mimeCheck = new MIMECheck();
						String fileExt=mimeCheck.getFileExtension(strFileName.getFileName());
				        Boolean mime = mimeCheck.validateMIMEAndFileType(strFileName);  // common code to check mime type and validation.
						 // new code finish.
						if (!mime) {
							errorList
							.add("<li><font color="
									+ "red"
									+ ">Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.</font></li>");
							request.setAttribute("errorsList", errorList);
							return mapping.findForward("uploadDRO");
						} 
						else if (size > AuditInspectionConstant.MAX_FILE_SIZE) 
						{
							errorList.add("<li><font color="+ "red"+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
							request.setAttribute("errorsList", errorList);
							return mapping.findForward("uploadDRO");
						} 
						else 
						{
							try 
							{
								File newFile = new File(filePath+ strFileName.getFileName());
								FileOutputStream fos = new FileOutputStream(newFile);
								fos.write(strFileName.getFileData());
								fos.close();
							} 
							catch (Exception ex) 
							{
								ex.printStackTrace();
							}
							ArrayList lsUPloadDTO = new ArrayList();
							UploadFileDTO uploadFileDTO = new UploadFileDTO();
							uploadFileDTO.setFileName(strFileName.getFileName());
							uploadFileDTO.setFilePath(filePath+strFileName.getFileName());																				
							uploadFileDTO.setFileSize(size);
							uploadFileDTO.setDocId(uploadForm.getActionType());
							lsUPloadDTO.add(uploadFileDTO);
							session.setAttribute("auditfiles", lsUPloadDTO);
						}
					} 
					else 
					{
							ArrayList arrList = (ArrayList) session.getAttribute("auditfiles");
							ArrayList arrForSession = new ArrayList();
							UploadFileDTO uploadFileDTO = null;
							for (int i = 0; i < arrList.size(); i++) 
							{
								uploadFileDTO = (UploadFileDTO) arrList.get(i);
								currFileSize = currFileSize + uploadFileDTO.getFileSize();
								arrForSession.add(uploadFileDTO);
							}
							FormFile forFile = uploadForm.getTheFile();
							/*String fileExt = getFileExtension(forFile.getFileName());
							rule = new AuditInspectionRule();
							errorList = rule.validateFileType(fileExt);*/
							MIMECheck mimeCheck = new MIMECheck();
							String fileExt=mimeCheck.getFileExtension(forFile.getFileName());
					        Boolean mime = mimeCheck.validateMIMEAndFileType(forFile);  // common code to check mime type and validation.
							 // new code finish.
							if (!mime) 
							{errorList
								.add("<li><font color="
										+ "red"
										+ ">Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.</font></li>");
								request.setAttribute("errorsList", errorList);
								return mapping.findForward("uploadDRO");
							} 
							else 
							{
								int size = forFile.getFileSize();
								currFileSize = currFileSize + size;
								if (currFileSize > AuditInspectionConstant.MAX_FILE_SIZE) 
								{
									errorList.add("<li><font color="+ "red"+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
									request.setAttribute("errorsList", errorList);
									return mapping.findForward("uploadDRO");
								} 
								else 
								{
									// uploadForm.setSizeOfFile(currFileSize);
									if (!forFile.getFileName().equals("")) 
									{
										uploadFileDTO = new UploadFileDTO();
										uploadFileDTO.setFileName(forFile.getFileName());
										uploadFileDTO.setDocId(uploadForm.getActionType());
										arrForSession.add(uploadFileDTO);
										try 
										{
											File newFile = new File(filePath+ forFile.getFileName());
											FileOutputStream fos = new FileOutputStream(newFile);
											fos.write(forFile.getFileData());
											fos.close();
										} 
										catch (Exception ex) 
										{
											ex.printStackTrace();
										}
									}
									session.removeAttribute("auditfiles");
									session.setAttribute("auditfiles", arrForSession);
								}
							}
				}
			return (mapping.findForward("uploadDRO"));
		}
		
		else if (uploadForm.getAttachAction().equalsIgnoreCase("attach")&& request.getParameter("upload")!=null && request.getParameter("upload").equals("internal")) 
		{
				if (session.getAttribute("internalauditfiles") == null ) 
				{
						FormFile strFileName = uploadForm.getTheFile();
						int size = strFileName.getFileSize();
						/*String fileExt = getFileExtension(strFileName.getFileName());
						rule = new AuditInspectionRule();
						errorList = rule.validateFileType(fileExt);*/
						MIMECheck mimeCheck = new MIMECheck();
						String fileExt=mimeCheck.getFileExtension(strFileName.getFileName());
				        Boolean mime = mimeCheck.validateMIMEAndFileType(strFileName);  // common code to check mime type and validation.
						 // new code finish.
						if (!mime) 
						{errorList
							.add("<li><font color="
									+ "red"
									+ ">Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.</font></li>");
							request.setAttribute("errorsList", errorList);
							return mapping.findForward("uploadDRO");
						} 
						else if (size > AuditInspectionConstant.MAX_FILE_SIZE) 
						{
							errorList.add("<li><font color="+ "red"+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
							request.setAttribute("errorsList", errorList);
							return mapping.findForward("uploadDRO");
						} 
						else 
						{
								try 
								{
									File newFile = new File(filePath+ strFileName.getFileName());
									FileOutputStream fos = new FileOutputStream(newFile);
									fos.write(strFileName.getFileData());
									fos.close();
								} 
								catch (Exception ex) 
								{
									ex.printStackTrace();
								}
								ArrayList lsUPloadDTO = new ArrayList();
								UploadFileDTO uploadFileDTO = new UploadFileDTO();
								uploadFileDTO.setFileName(strFileName.getFileName());
								uploadFileDTO.setFilePath(filePath+strFileName.getFileName());																								
								uploadFileDTO.setFileSize(size);
								uploadFileDTO.setDocId(uploadForm.getActionType());
								lsUPloadDTO.add(uploadFileDTO);
								session.setAttribute("internalauditfiles", lsUPloadDTO);
						}
				} 
				else 
				{
						ArrayList arrList = (ArrayList) session.getAttribute("internalauditfiles");
						ArrayList arrForSession = new ArrayList();
						UploadFileDTO uploadFileDTO = null;
						for (int i = 0; i < arrList.size(); i++) 
						{
							uploadFileDTO = (UploadFileDTO) arrList.get(i);
							currFileSize = currFileSize + uploadFileDTO.getFileSize();
							arrForSession.add(uploadFileDTO);
						}
						FormFile forFile = uploadForm.getTheFile();
						/*String fileExt = getFileExtension(forFile.getFileName());
						rule = new AuditInspectionRule();
						errorList = rule.validateFileType(fileExt);*/
						MIMECheck mimeCheck = new MIMECheck();
						String fileExt=mimeCheck.getFileExtension(forFile.getFileName());
				        Boolean mime = mimeCheck.validateMIMEAndFileType(forFile);  // common code to check mime type and validation.
						 // new code finish.
						if (!mime) 
						{
							errorList
							.add("<li><font color="
									+ "red"
									+ ">Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.</font></li>");
							request.setAttribute("errorsList", errorList);
							return mapping.findForward("uploadDRO");
						} 
						else 
						{
								int size = forFile.getFileSize();
								currFileSize = currFileSize + size;
								if (currFileSize > AuditInspectionConstant.MAX_FILE_SIZE) 
								{
									errorList.add("<li><font color="+ "red"+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
									request.setAttribute("errorsList", errorList);
									return mapping.findForward("uploadDRO");
								} 
								else 
								{
									// uploadForm.setSizeOfFile(currFileSize);
									if (!forFile.getFileName().equals("")) 
									{
										uploadFileDTO = new UploadFileDTO();
										uploadFileDTO.setFileName(forFile.getFileName());
										uploadFileDTO.setDocId(uploadForm.getActionType());
										arrForSession.add(uploadFileDTO);
										try {
											File newFile = new File(filePath
													+ forFile.getFileName());
											FileOutputStream fos = new FileOutputStream(
													newFile);
											fos.write(forFile.getFileData());
											fos.close();
										} catch (Exception ex) {
											ex.printStackTrace();
										}
									}
								session.removeAttribute("internalauditfiles");
								session.setAttribute("internalauditfiles", arrForSession);
								}
						}
				}
		return (mapping.findForward("uploadDRO"));
	}
		else if (uploadForm.getAttachAction().equalsIgnoreCase("attach")&& request.getParameter("upload")!=null && request.getParameter("upload").equals("stamp")) 
		{
				if (session.getAttribute("stampfiles") == null ) 
				{
					FormFile strFileName = uploadForm.getTheFile();
					int size = strFileName.getFileSize();
					/*String fileExt = getFileExtension(strFileName.getFileName());
					rule = new AuditInspectionRule();
					errorList = rule.validateFileType(fileExt);*/
					MIMECheck mimeCheck = new MIMECheck();
					String fileExt=mimeCheck.getFileExtension(strFileName.getFileName());
			        Boolean mime = mimeCheck.validateMIMEAndFileType(strFileName);  // common code to check mime type and validation.
					 // new code finish.
					if (!mime) {
						errorList
						.add("<li><font color="
								+ "red"
								+ ">Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.</font></li>");
						request.setAttribute("errorsList", errorList);
						return mapping.findForward("uploadDRO");
					} 
					else if (size > AuditInspectionConstant.MAX_FILE_SIZE) 
					{
						errorList.add("<li><font color="+ "red"+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
						request.setAttribute("errorsList", errorList);
						return mapping.findForward("uploadDRO");
					} 
					else 
					{
						try 
						{
							File newFile = new File(filePath+ strFileName.getFileName());
							FileOutputStream fos = new FileOutputStream(newFile);
							fos.write(strFileName.getFileData());
							fos.close();
						} 
						catch (Exception ex) 
						{
							ex.printStackTrace();
						}
						ArrayList lsUPloadDTO = new ArrayList();
						UploadFileDTO uploadFileDTO = new UploadFileDTO();
						uploadFileDTO.setFileName(strFileName.getFileName());
						uploadFileDTO.setFileSize(size);
						uploadFileDTO.setDocId(uploadForm.getActionType());
						lsUPloadDTO.add(uploadFileDTO);
						session.setAttribute("stampfiles", lsUPloadDTO);
					}
				} 
				else 
				{
					ArrayList arrList = (ArrayList) session.getAttribute("stampfiles");
					ArrayList arrForSession = new ArrayList();
					UploadFileDTO uploadFileDTO = null;
					for (int i = 0; i < arrList.size(); i++) 
					{
						uploadFileDTO = (UploadFileDTO) arrList.get(i);
						currFileSize = currFileSize + uploadFileDTO.getFileSize();
						arrForSession.add(uploadFileDTO);
					}
					FormFile forFile = uploadForm.getTheFile();
					/*String fileExt = getFileExtension(forFile.getFileName());
					rule = new AuditInspectionRule();
					errorList = rule.validateFileType(fileExt);*/
					MIMECheck mimeCheck = new MIMECheck();
					String fileExt=mimeCheck.getFileExtension(forFile.getFileName());
			        Boolean mime = mimeCheck.validateMIMEAndFileType(forFile);  // common code to check mime type and validation.
					 // new code finish.
					if (!mime) 
					{
						errorList
						.add("<li><font color="
								+ "red"
								+ ">Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.</font></li>");
						request.setAttribute("errorsList", errorList);
						return mapping.findForward("uploadDRO");
					} 
					else 
					{
						int size = forFile.getFileSize();
						currFileSize = currFileSize + size;
						if (currFileSize > AuditInspectionConstant.MAX_FILE_SIZE) 
						{
							errorList.add("<li><font color="+ "red"+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
							request.setAttribute("errorsList", errorList);
							return mapping.findForward("uploadDRO");
						} 
						else {
							// uploadForm.setSizeOfFile(currFileSize);
							if (!forFile.getFileName().equals("")) 
							{
								uploadFileDTO = new UploadFileDTO();
								uploadFileDTO.setFileName(forFile.getFileName());
								uploadFileDTO.setDocId(uploadForm.getActionType());
								arrForSession.add(uploadFileDTO);
								try {
									File newFile = new File(filePath+ forFile.getFileName());
									FileOutputStream fos = new FileOutputStream(newFile);
									fos.write(forFile.getFileData());
									fos.close();
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
							session.removeAttribute("stampfiles");
							session.setAttribute("stampfiles", arrForSession);
						}
				}
		}
		return (mapping.findForward("uploadDRO"));
	}
		
		if (uploadForm.getRemoveAction().equalsIgnoreCase("removepending")) 
		{
			ArrayList arrList=null;
			if(session.getAttribute("pendingfiles")!=null)
			{
				arrList = (ArrayList) session.getAttribute("pendingfiles");
			}
			try 
			{
				String param[] = request.getParameterValues("checkbox");
				for (int i = 0; i < param.length; i++) 
				{
					String checkedFileName = param[i];
					for (int j = 0; j < arrList.size(); j++) 
					{
						UploadFileDTO uploadFileDTO = (UploadFileDTO) arrList.get(j);
						if (uploadFileDTO.getFileName().equalsIgnoreCase(checkedFileName)) 
						{
							File newFile = new File(filePath + checkedFileName);
							newFile.delete();
							arrList.remove(j);
						}
					}
				}
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			session.removeAttribute("pendingfiles");
			session.setAttribute("pendingfiles", arrList);
			return mapping.findForward("uploadDRO");
		}
		
		
		if (uploadForm.getRemoveAction().equalsIgnoreCase("removeaudit")) 
		{
			ArrayList arrList=null;
			if(session.getAttribute("auditfiles")!=null)
			{
				arrList = (ArrayList) session.getAttribute("auditfiles");
			}
			try 
			{
				String param[] = request.getParameterValues("checkbox");
				for (int i = 0; i < param.length; i++) 
				{
					String checkedFileName = param[i];
					for (int j = 0; j < arrList.size(); j++) 
					{
						UploadFileDTO uploadFileDTO = (UploadFileDTO) arrList.get(j);
						if (uploadFileDTO.getFileName().equalsIgnoreCase(checkedFileName)) 
						{
							File newFile = new File(filePath + checkedFileName);
							newFile.delete();
							arrList.remove(j);
						}
					}
				}
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			session.removeAttribute("auditfiles");
			session.setAttribute("auditfiles", arrList);
			return mapping.findForward("uploadDRO");
		}
		/*
		 * 
		 * 
		 * 
		 */
		if (uploadForm.getRemoveAction().equalsIgnoreCase("removeinternalaudit"))
		{
			ArrayList arrList=null;
			if(session.getAttribute("internalauditfiles")!=null)
			{
				arrList = (ArrayList) session.getAttribute("internalauditfiles");
			}
			try 
			{
				String param[] = request.getParameterValues("checkbox");
				for (int i = 0; i < param.length; i++) 
				{
					String checkedFileName = param[i];
					for (int j = 0; j < arrList.size(); j++) 
					{
						UploadFileDTO uploadFileDTO = (UploadFileDTO) arrList.get(j);
						if (uploadFileDTO.getFileName().equalsIgnoreCase(checkedFileName)) 
						{
							File newFile = new File(filePath + checkedFileName);
							newFile.delete();
							arrList.remove(j);
						}
					}
				}
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			session.removeAttribute("internalauditfiles");
			session.setAttribute("internalauditfiles", arrList);
			return mapping.findForward("uploadDRO");
		}
		
		/*
		 * 
		 * 
		 */
		if (uploadForm.getRemoveAction().equalsIgnoreCase("removestamp")) 
		{
			ArrayList arrList=null;
			if(session.getAttribute("stampfiles")!=null)
			{
				arrList = (ArrayList) session.getAttribute("stampfiles");
			}
			try 
			{
				String param[] = request.getParameterValues("checkbox");
				for (int i = 0; i < param.length; i++) 
				{
					String checkedFileName = param[i];
					for (int j = 0; j < arrList.size(); j++) 
					{
						UploadFileDTO uploadFileDTO = (UploadFileDTO) arrList.get(j);
						if (uploadFileDTO.getFileName().equalsIgnoreCase(checkedFileName)) 
						{
							File newFile = new File(filePath + checkedFileName);
							newFile.delete();
							arrList.remove(j);
						}
					}
				}
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			session.removeAttribute("stampfiles");
			session.setAttribute("stampfiles", arrList);
			return mapping.findForward("uploadDRO");
		}
		
		
		
		return mapping.findForward("failure");
	}

	private String getFileExtension(String fileName) 
	{
		int pos = fileName.lastIndexOf(".");
		String strExt = fileName.substring(pos + 1, fileName.length());
		return strExt;
	}
}
