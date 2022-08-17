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
package com.wipro.igrs.empmgmt.dto;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

/**
* 
* EmpmgmtUploadDTO.java <br>
* EmpmgmtUploadDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class EmpmgmtUploadDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5632641020964067644L;
	private String fileName;
	private int fileId;
	private int fileSize;
	private String docID;
	private String docTypeID;
    private String documenttype;
    private String documentname;
    private transient FormFile formFileObject;
    private transient byte[] fileContents;
    private String docTypeLabel;
	/**
	 * @return the documentname
	 */
	public String getDocumentname() {
		return documentname;
	}
	/**
	 * @param documentname the documentname to set
	 */
	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getDocumenttype() {
		return documenttype;
	}
	public void setDocumenttype(String documenttype) {
		this.documenttype = documenttype;
	}
	/**
	 * @return the formFileObject
	 */
	public FormFile getFormFileObject() {
		return formFileObject;
	}
	/**
	 * @param formFileObject the formFileObject to set
	 */
	public void setFormFileObject(FormFile formFileObject) {
		this.formFileObject = formFileObject;
	}
	/**
	 * @return the docID
	 */
	public String getDocID() {
		return docID;
	}
	/**
	 * @param docID the docID to set
	 */
	public void setDocID(String docID) {
		this.docID = docID;
	}
	/**
	 * @return the fileContents
	 */
	public byte[] getFileContents() {
		return fileContents;
	}
	/**
	 * @param fileContents the fileContents to set
	 */
	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}
	/**
	 * @return the docTypeID
	 */
	public String getDocTypeID() {
		return docTypeID;
	}
	/**
	 * @param docTypeID the docTypeID to set
	 */
	public void setDocTypeID(String docTypeID) {
		this.docTypeID = docTypeID;
	}
	/**
	 * @return the docTypeLabel
	 */
	public String getDocTypeLabel() {
		return docTypeLabel;
	}
	/**
	 * @param docTypeLabel the docTypeLabel to set
	 */
	public void setDocTypeLabel(String docTypeLabel) {
		this.docTypeLabel = docTypeLabel;
	}

}
