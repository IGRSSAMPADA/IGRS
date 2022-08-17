/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.caveats.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
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
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.common.AuditInspectionConstant;
/**
* 
* CaveatReleaseDetailAction.java <br>
* CaveatReleaseDetailAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class CaveatReleaseDetailAction extends BaseAction {
	
	private Logger logger = (Logger) Logger
			.getLogger(CaveatReleaseDetailAction.class);

	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException, ServletException,
			Exception {
		logger.debug("WE ARE IN CaveatReleaseDetailAction Debug");
		logger.info("WE ARE IN  CaveatReleaseDetailAction INFO");
		String FORWAD_SUCCESS="";
		try {
			CaveatsDelegate cavBD = new CaveatsDelegate();
			CaveatsForm fb = (CaveatsForm) form;
			CaveatsDTO dtoRelease = fb.getCaveatsDTO();
			String upload = request.getParameter("upload");
			String clearReleaseFile = request.getParameter("clearReleaseFile");
			String clearRelease = request.getParameter("clearRelease");
			String downloadRelease = request.getParameter("downloadRelease");
			String downloadMainDoc = request.getParameter("downloadMainDoc");
			
			///
			
			
			
			///
			
			
			
			
			if ("yes".equals(upload)) {
				ArrayList errorList = new ArrayList();
				try {
					FormFile uploadedFile = dtoRelease.getReleaseDoc();
					if ("".equals(uploadedFile.getFileName())) {
//						clearDoc(dtoRelease);
						errorList 
						.add("Invalid file selection. Please try again./अवैध फ़ाइल चयन. पुन: प्रयास करें");
					}
					String fileExt = getFileExtension(uploadedFile
							.getFileName());
					AuditInspectionRule rule = new AuditInspectionRule();
					rule.validateFileType(fileExt);
					int size = uploadedFile.getFileSize();
					if (rule.isError()) {
//						clearDoc(dtoRelease);
						errorList
								.add("Invalid file type. Please select another file./अमान्य फ़ाइल प्रकार. एक और फ़ाइल का चयन करें.");
						request.setAttribute("errorsList", errorList);
					} else {
						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
//							clearDoc(dtoRelease);
							errorList
									.add("File size is Greater than 10 MB. Please select another file./फ़ाइल का आकार 10 एमबी से अधिक है. एक और फ़ाइल का चयन करें.");
							request.setAttribute("errorsList", errorList);
						} else {
							dtoRelease.setReleaseDocName(uploadedFile.getFileName());
							dtoRelease.setReleaseContents(uploadedFile.getFileData());
							dtoRelease.setReleaseFileSize(getFileSize(uploadedFile.getFileData().length));
						}
					}
				} catch (Exception e) {
					errorList.add("Unable to upload file. Please try again./फ़ाइल अपलोड करने में असमर्थ. पुन: प्रयास करें....");
					request.setAttribute("errorsList", errorList);
				}
				FORWAD_SUCCESS = "upload";
			}
		else if ("yes".equals(downloadRelease)) {
				byte[] content = dtoRelease.getReleaseContents();
				String filename = dtoRelease.getReleaseDocName();
				try {
					if (content != null) {
						CaveatsViewSearchAction.downloadDocument(response,
								content, filename);
					}
				} catch (Exception e) {
					e.hashCode();
				}
				FORWAD_SUCCESS = "upload";
			}
			
			
			///
/*		 if("yes".equals(downloadMainDoc)) {

	        	try
	        	{
	        	response.setContentType("application/download");
	            
				String filename=dtoRelease.getUploaded_doc_path();
				File fileToDownload = new File(filename);

				response.setHeader("Content-Disposition", "attachment; filename="

	                                                + URLEncoder.encode(dtoRelease.getDocName().toString(),"UTF-8"));
				
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
	            dtoRelease.setActionName("");
	            FORWAD_SUCCESS = "upload";
	            return mapping.findForward(FORWAD_SUCCESS); 
	    		
	        
	        }
	        	//return mapping.findForward(FORWAD_SUCCESS); 
		
            catch (Exception e) {
            	e.printStackTrace();
			}	
	        	
	        
		}
			
		
		
			*/
			///
			
			
			
		else if("yes".equals(clearRelease))
		{
			dtoRelease.setReasonForRelease("");
			dtoRelease.setRemarksForRelease("");
			dtoRelease.setReleaseContents(null);
			dtoRelease.setReleaseDocName("");
			FORWAD_SUCCESS = "upload";
		}
			else if ("yes".equals(clearReleaseFile)) {
				dtoRelease.setReleaseContents(null);
				dtoRelease.setReleaseDocName("");
				FORWAD_SUCCESS = "upload";
			} else {
				String loggedIn = (String)session.getAttribute("UserId");
				if (loggedIn == null || "".equalsIgnoreCase(loggedIn))
					loggedIn = "igrs";
				dtoRelease.setLoggedIn(loggedIn);
				String refID = request.getParameter("referenceID");
				if (refID != null)
					dtoRelease.setReferenceID(refID);
				boolean log = cavBD.updateFlag(dtoRelease);
				if (log) {
					FORWAD_SUCCESS = "success";
				} else {
					FORWAD_SUCCESS = "faliure";
				}
			}
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			//logger.error(e);
			return mapping.findForward("Failure");
		}
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

	private void clearDoc(CaveatsDTO dtoRelease) {
		dtoRelease.setReleaseDocName(null);
		dtoRelease.setReleaseContents(null);
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
