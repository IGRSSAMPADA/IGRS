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
* ServiceVerificationDTO.java <br>
* ServiceVerificationDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class ServiceVerificationDTO implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -6892075096601919805L;
	private String verifyingAuthority;
	 private String dateOfVerivication;
	 private String comments;
	 private String documentType;
	 private transient FormFile document;
	 private String documentid;
	/**
	 * @return the verifyingAuthority
	 */
	public String getVerifyingAuthority() {
		return verifyingAuthority;
	}
	/**
	 * @param verifyingAuthority the verifyingAuthority to set
	 */
	public void setVerifyingAuthority(String verifyingAuthority) {
		this.verifyingAuthority = verifyingAuthority;
	}
	/**
	 * @return the dateOfVerivication
	 */
	public String getDateOfVerivication() {
		return dateOfVerivication;
	}
	/**
	 * @param dateOfVerivication the dateOfVerivication to set
	 */
	public void setDateOfVerivication(String dateOfVerivication) {
		this.dateOfVerivication = dateOfVerivication;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}
	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	/**
	 * @return the document
	 */
	public FormFile getDocument() {
		return document;
	}
	/**
	 * @param document the document to set
	 */
	public void setDocument(FormFile document) {
		this.document = document;
	}
	/**
	 * @return the documentid
	 */
	public String getDocumentid() {
		return documentid;
	}
	/**
	 * @param documentid the documentid to set
	 */
	public void setDocumentid(String documentid) {
		this.documentid = documentid;
	}
	

}
