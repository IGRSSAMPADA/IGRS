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

package com.wipro.igrs.auditinspection.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.baseaction.form.BaseForm;

/**
 * Form bean for a Struts application. Users may access 1 field on this form:
 * <ul>
 * <li>testFile - [your comment here]
 * </ul>
 * 
 * @version 1.0
 * @author
 */
public class UploadForm extends BaseForm {

	private FormFile theFile = null;
	UploadFileDTO uDTO = new UploadFileDTO();
	private String attachAction;
	private String uploadAction;
	private String removeAction;

	private ArrayList listUploadDTO;

	private int sizeOfFile;
	private String documentName;
	private byte[] docContents;
	private String docSize;
	private String docCheck;
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		theFile = null;

	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		return errors;

	}

	
	/**
	 * @return the uDTO
	 */
	public UploadFileDTO getuDTO() {
		return uDTO;
	}

	/**
	 * @param uDTO the uDTO to set
	 */
	public void setuDTO(UploadFileDTO uDTO) {
		this.uDTO = uDTO;
	}

	/**
	 * @return the uploadAction
	 */
	public String getUploadAction() {
		return uploadAction;
	}

	/**
	 * @param uploadAction the uploadAction to set
	 */
	public void setUploadAction(String uploadAction) {
		this.uploadAction = uploadAction;
	}

	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	/**
	 * @return the docContents
	 */
	public byte[] getDocContents() {
		return docContents;
	}

	/**
	 * @param docContents the docContents to set
	 */
	public void setDocContents(byte[] docContents) {
		this.docContents = docContents;
	}

	/**
	 * @return the docSize
	 */
	public String getDocSize() {
		return docSize;
	}

	/**
	 * @param docSize the docSize to set
	 */
	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}

	/**
	 * @return the docCheck
	 */
	public String getDocCheck() {
		return docCheck;
	}

	/**
	 * @param docCheck the docCheck to set
	 */
	public void setDocCheck(String docCheck) {
		this.docCheck = docCheck;
	}

	/**
	 * @return the theFile
	 */
	public FormFile getTheFile() {
		return theFile;
	}

	/**
	 * @param theFile
	 *            the theFile to set
	 */
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}

	/**
	 * @return the attachAction
	 */
	public String getAttachAction() {
		return attachAction;
	}

	/**
	 * @param attachAction
	 *            the attachAction to set
	 */
	public void setAttachAction(String attachAction) {
		this.attachAction = attachAction;
	}

	/**
	 * @return the removeAction
	 */
	public String getRemoveAction() {
		return removeAction;
	}

	/**
	 * @param removeAction
	 *            the removeAction to set
	 */
	public void setRemoveAction(String removeAction) {
		this.removeAction = removeAction;
	}

	/**
	 * @return the listUploadDTO
	 */
	public ArrayList getListUploadDTO() {
		return listUploadDTO;
	}

	/**
	 * @param listUploadDTO
	 *            the listUploadDTO to set
	 */
	public void setListUploadDTO(ArrayList listUploadDTO) {
		this.listUploadDTO = listUploadDTO;
	}

	/**
	 * @return the sizeOfFile
	 */
	public int getSizeOfFile() {
		return sizeOfFile;
	}

	/**
	 * @param sizeOfFile
	 *            the sizeOfFile to set
	 */
	public void setSizeOfFile(int sizeOfFile) {
		this.sizeOfFile = sizeOfFile;
	}

}
