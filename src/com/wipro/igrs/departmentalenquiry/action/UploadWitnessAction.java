/*
 * UploadWitnessAction.java
 */

package com.wipro.igrs.departmentalenquiry.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.departmentalenquiry.dto.WitnessDTO;
import com.wipro.igrs.departmentalenquiry.form.UploadWitnessForm;
import com.wipro.igrs.departmentalenquiry.rule.DepartmentalEnquiryRule;

/**
 * MyEclipse Struts Creation date: 06-09-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class UploadWitnessAction extends BaseAction {
	/*
	 * Generated Methods
	 */


	private String getFileExtension(String fileName) {
		int pos = fileName.lastIndexOf(".");
		String strExt = fileName.substring(pos + 1, fileName.length());
		return strExt;
	}


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		UploadWitnessForm uploadForm = (UploadWitnessForm) form;
		
			// should be removed
		//session = request.getSession();
		
		
		String filePath = getServlet().getServletContext().getRealPath("")
				+ "/temp/";
		session.setAttribute("FilePath", filePath);
		DepartmentalEnquiryRule rule = null;
		ArrayList errorList = null;
		int currFileSize = 0;

		// System.out.println("In action");

		if (uploadForm.getAttachAction().equalsIgnoreCase("attach")) {
			if (session.getAttribute("attachment1") == null) {
				// Get the list of files
				FormFile strFileName = uploadForm.getTheFile();
				rule = new DepartmentalEnquiryRule();
				errorList = rule.validateWitnessDetails(uploadForm);
				if (rule.isError()) {
					request.setAttribute("errorsList", errorList);
					return mapping.findForward("upload");
				} else {
					try {
						// System.out.println("I am Writing the File In temp");
						File newFile = new File(filePath
								+ strFileName.getFileName());
						FileOutputStream fos = new FileOutputStream(newFile);
						fos.write(strFileName.getFileData());
						fos.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					ArrayList lsUPloadDTO = new ArrayList();
					WitnessDTO uploadFileDTO = new WitnessDTO();
					uploadFileDTO.setStrEnqOfficerName(uploadForm
							.getStrEnqOfficerName());// set doctype to
					// uploaddto
					uploadFileDTO.setStrSupportingDocName(strFileName
							.getFileName());
					uploadFileDTO.setFileSize(strFileName.getFileSize());

					lsUPloadDTO.add(uploadFileDTO);
					session.setAttribute("attachment1", lsUPloadDTO);
				}
			} else {
				ArrayList arrList = (ArrayList) session
						.getAttribute("attachment1");
				// System.out.println("From the UI list "+arrList.size());

				ArrayList arrForSession = new ArrayList();

				WitnessDTO uploadFileDTO = null;
				for (int i = 0; i < arrList.size(); i++) {
					uploadFileDTO = (WitnessDTO) arrList.get(i);
					currFileSize = currFileSize + uploadFileDTO.getFileSize();// set
					// doctype
					// to
					// uploaddto
					arrForSession.add(uploadFileDTO);
				}
				FormFile forFile = uploadForm.getTheFile();

				String fileExt = getFileExtension(forFile.getFileName());
				// System.out.println("File Extenssion :"+fileExt);
				rule = new DepartmentalEnquiryRule();
				errorList = rule.validateWitnessDetails(uploadForm);
				if (rule.isError()) {
					request.setAttribute("errorsList", errorList);
					return mapping.findForward("upload");
				} else {
					int size = forFile.getFileSize();
					currFileSize = currFileSize + size;
					// System.out.println("SIZE OF File In Else Block :"+size);
					// System.out.println("Combined FIle Size in Else Block
					// :"+currFileSize);
					if (currFileSize > AuditInspectionConstant.MAX_FILE_SIZE) {
						ArrayList errorList1 = new ArrayList();
						errorList1
								.add("<li><font color="
										+ "red"
										+ ">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
						request.setAttribute("errorsList", errorList1);
						return mapping.findForward("upload");
					} else {
						// uploadForm.setSizeOfFile(currFileSize);
						if (!forFile.getFileName().equals("")
								|| forFile.getFileName().length() < 1) {
							uploadFileDTO = new WitnessDTO();
							uploadFileDTO.setStrEnqOfficerName(uploadForm
									.getStrEnqOfficerName());
							uploadFileDTO.setStrSupportingDocName(forFile
									.getFileName());
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
						}// End if
						session.removeAttribute("attchment1");
						session.setAttribute("attachment1", arrForSession);

					}// else END IF
				}// End Else Block
			}// else END IF
			return (mapping.findForward("upload"));
		}// end if

		if (uploadForm.getRemoveAction().equalsIgnoreCase("remove")) {
			// System.out.println("ACTION :"+uploadForm.getRemoveAction());
			ArrayList arrList = (ArrayList) session.getAttribute("attachment1");

			try {
				String param[] = request.getParameterValues("checkbox");
				for (int i = 0; i < param.length; i++) {
					String checkedFileName = param[i];
					// System.out.println("Checked File Name
					// :"+checkedFileName);
					for (int j = 0; j < arrList.size(); j++) {
						WitnessDTO uploadFileDTO = (WitnessDTO) arrList.get(j);
						if (uploadFileDTO.getStrSupportingDocName()
								.equalsIgnoreCase(checkedFileName)) {
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
		// System.out.println("End of action");

		return mapping.findForward("failure");
	}

}