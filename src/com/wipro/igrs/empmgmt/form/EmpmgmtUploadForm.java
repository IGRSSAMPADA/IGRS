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
package com.wipro.igrs.empmgmt.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.baseaction.form.BaseForm;

/**
* 
* EmpmgmtUploadForm.java <br>
* EmpmgmtUploadForm <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class EmpmgmtUploadForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6219184675730454783L;
	private transient FormFile theFile = null;
	private ArrayList formFiles = null;
	private int index;
	private String attachAction;
	private String removeAction;
	private ArrayList listUploadDTO;
	private int sizeOfFile;
	private String documentType;

	private String documentname;

	/**
	 * @return the documentname
	 */
	/**
	 * @return
	 */
	public String getDocumentname() {
		return documentname;
	}

	/**
	 * @param documentname
	 *            the documentname to set
	 */
	/**
	 * @param documentname
	 */
	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}

	/**
	 * @return
	 */
	public ArrayList getFormFiles() {
		return formFiles;
	}

	/**
	 * @param formFiles
	 */
	public void setFormFiles(ArrayList formFiles) {
		this.formFiles = formFiles;
	}

	/**
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		theFile = null;

	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		return errors;

	}

	/**
	 * @return
	 */
	public String getAttachAction() {
		return attachAction;
	}

	/**
	 * @param attachAction
	 */
	public void setAttachAction(String attachAction) {
		this.attachAction = attachAction;
	}

	/**
	 * @return
	 */
	public String getRemoveAction() {
		return removeAction;
	}

	/**
	 * @param removeAction
	 */
	public void setRemoveAction(String removeAction) {
		this.removeAction = removeAction;
	}

	/**
	 * @return
	 */
	public ArrayList getListUploadDTO() {
		return listUploadDTO;
	}

	/**
	 * @param listUploadDTO
	 */
	public void setListUploadDTO(ArrayList listUploadDTO) {
		this.listUploadDTO = listUploadDTO;
	}

	/**
	 * @return
	 */
	public FormFile getTheFile() {
		return theFile;
	}

	/**
	 * @param theFile
	 */
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}

	/**
	 * @return
	 */
	public int getSizeOfFile() {
		return sizeOfFile;
	}

	/**
	 * @param sizeOfFile
	 */
	public void setSizeOfFile(int sizeOfFile) {
		this.sizeOfFile = sizeOfFile;
	}
}
