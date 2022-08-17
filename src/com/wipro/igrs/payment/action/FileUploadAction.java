package com.wipro.igrs.payment.action;

import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.payment.bd.FileUploadBD;
import com.wipro.igrs.payment.form.FileUploadForm;

/**
 * ===========================================================================
 * File : FileUploadAction.java 
 * Description : Represents the file upload Action Class 
 * Author : vengamamba P
 * Created Date : Mar 28, 2008
 * 
 * ===========================================================================
 */
public class FileUploadAction extends BaseAction {
	 private static final Logger logger = Logger
	.getLogger(FileUploadAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {

		FileUploadForm fform = (FileUploadForm) form;
		String FORWARD_JSP = "fail";
		String insertstring = "fail";
		ArrayList displayList = null;
		String actionValue = fform.getActionValue();
	//	HttpSession session = request.getSession();
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		// action belong to cancel button
		if ("Cancel".equalsIgnoreCase(actionValue)) {
			FORWARD_JSP = "Cancel";

		} else {
			FileUploadBD filebd = new FileUploadBD();

			// Process the FormFile
			FormFile myFile = fform.getTheFile();

			String contentType = myFile.getContentType();
			String fileName = myFile.getFileName();
			int fileSize = myFile.getFileSize();
			byte[] fileData = myFile.getFileData();
			InputStream is = myFile.getInputStream();
			/*
			 * logger.debug("contentType: " + contentType);
			 * logger.debug("File Name: " + fileName); logger.debug("File
			 * Size: " + fileSize); logger.debug("InputStream: " + is);
			 */
			try {

				insertstring = filebd.excelReader(is,roleId,funId,userId);

				ArrayList mylist = new ArrayList();
				if (insertstring.equals("success")) {
					displayList = filebd.displayData();

					if (displayList.size() > 0) {
						for (int i = 0; i < displayList.size(); i++) {
							ArrayList temp = new ArrayList();
							temp = (ArrayList) displayList.get(i);
							logger.debug("temparray=" + temp);

							fform.setTransactionId((temp.get(0).toString()));
							fform.setCin((temp.get(3).toString()));
							fform.setAmount((temp.get(2).toString()));
							fform.setScrollno((temp.get(1).toString()));
							fform.setDepositedDate(temp.get(5).toString());
							fform.setDepositedBy((temp.get(4).toString()));
							fform.setTreasuryName((temp.get(6).toString()));
							mylist.add(fform);
						}
					}
				//	session = request.getSession();
					session.setAttribute("displayList", mylist);
					FORWARD_JSP = "success";

				}
			} catch (Exception e) {
				e.getStackTrace();
				logger.error("exception in file upload =" + e);
			}

		}
		return mapping.findForward(FORWARD_JSP);
	}
}