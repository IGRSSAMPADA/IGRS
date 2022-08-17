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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * MyEclipse Struts Creation date: 06-27-2008
 * 
 * XDoclet definition:
 * 
 * @struts.form name="droUploadForm"
 */
public class DROUploadForm extends ActionForm {

	private FormFile theFile = null;

	private String attachAction;

	private String removeAction;

	private String actionType;

	private ArrayList listUploadDTO;

	private int sizeOfFile;

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

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType
	 *            the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

}