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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.auditinspection.form.UploadForm;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.util.PropertiesFileReader;
//Added new common code for MIME Type check of Uploaded file
import com.wipro.igrs.common.mime.MIMECheck;


/**
 * MyEclipse Struts Creation date: 05-12-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class FileUploadAction extends BaseAction {
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
		
		PropertiesFileReader pr =null;
		String filePath=null;
		try {
			pr=PropertiesFileReader.getInstance("resources.igrs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			filePath=pr.getValue("igrs_upload_path")+"/10/POI/";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File folder=new File(filePath);
		if(!folder.exists())
		{
			folder.mkdir();
		}
		session.setAttribute("FilePath", filePath);
		AuditInspectionRule rule = null;
		int currFileSize = 0;

		

		if (uploadForm.getAttachAction().equalsIgnoreCase("attach")) {
			if (session.getAttribute("attachment1") == null) {
				// Get the list of files
				FormFile strFileName = uploadForm.getTheFile();
				
				int size = strFileName.getFileSize();
				
				// save file in the app server
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
					return mapping.findForward("upload1");
				} else if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList
							.add("<li><font color="
									+ "red"
									+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
					request.setAttribute("errorsList", errorList);
					return mapping.findForward("upload1");

				} else {

					try {
						File newFile = new File(filePath
								+ strFileName.getFileName());
						FileOutputStream fos = new FileOutputStream(newFile);
						fos.write(strFileName.getFileData());
						fos.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					ArrayList lsUPloadDTO = new ArrayList();
					UploadFileDTO uploadFileDTO = new UploadFileDTO();
					uploadFileDTO.setTheFile(strFileName);
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

				UploadFileDTO uploadFileDTO = new UploadFileDTO();
				for (int i = 0; i < arrList.size(); i++) {
					uploadFileDTO = (UploadFileDTO) arrList.get(i);
					currFileSize = currFileSize + uploadFileDTO.getFileSize();// set
					uploadFileDTO.setTheFile(uploadFileDTO.getTheFile());
					// doctype
					// to
					// uploaddto
					arrForSession.add(uploadFileDTO);
				}
				FormFile forFile = uploadForm.getTheFile();
			//	uploadFileDTO.setTheFile(forFile);
				/*String fileExt = getFileExtension(forFile.getFileName());
				
				rule = new AuditInspectionRule();
				errorList = rule.validateFileType(fileExt);*/
				MIMECheck mimeCheck = new MIMECheck();
				String fileExt=mimeCheck.getFileExtension(forFile.getFileName());
		        Boolean mime = mimeCheck.validateMIMEAndFileType(forFile);  // common code to check mime type and validation.
				 // new code finish.
				if (!mime) {
					errorList
					.add("<li><font color="
							+ "red"
							+ ">Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.</font></li>");
					request.setAttribute("errorsList", errorList);
					return mapping.findForward("upload1");
				} else {
					int size = forFile.getFileSize();
					currFileSize = currFileSize + size;
					if (currFileSize > AuditInspectionConstant.MAX_FILE_SIZE) {
						errorList.add("<li><font color="
									+ "red"
										+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
						request.setAttribute("errorsList", errorList);
						return mapping.findForward("upload1");
					} else {
						// uploadForm.setSizeOfFile(currFileSize);
						if (!forFile.getFileName().equals("")) {
							uploadFileDTO = new UploadFileDTO();
							uploadFileDTO.setFileName(forFile.getFileName());
							uploadFileDTO.setTheFile(forFile);
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
			return (mapping.findForward("upload1"));
		}// end if

		if (uploadForm.getRemoveAction().equalsIgnoreCase("remove")) {
			
			ArrayList arrList = (ArrayList) session.getAttribute("attachment1");

			try {
				String param[] = request.getParameterValues("checkbox");
				for (int i = 0; i < param.length; i++) {
					String checkedFileName = param[i];
					
					for (int j = 0; j < arrList.size(); j++) {
						UploadFileDTO uploadFileDTO = (UploadFileDTO) arrList.get(j);
						if (uploadFileDTO.getFileName().equalsIgnoreCase(checkedFileName)) {
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
			return mapping.findForward("upload1");
		}
		

		return mapping.findForward("failure");
	}

	private String getFileExtension(String fileName) {
		int pos = fileName.lastIndexOf(".");
		String strExt = fileName.substring(pos + 1, fileName.length());
		return strExt;
	}
}