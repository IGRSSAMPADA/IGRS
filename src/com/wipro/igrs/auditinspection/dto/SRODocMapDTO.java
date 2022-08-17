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

package com.wipro.igrs.auditinspection.dto;

import java.io.Serializable;

public class SRODocMapDTO implements Serializable{

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	private String	inspTransactionId;
	
	private String	document_Name;
	private String filePath;
	/**
	 * @return the inspTransactionId
	 */
	public String getInspTransactionId() {
		return inspTransactionId;
	}
	
	/**
	 * @param inspTransactionId the inspTransactionId to set
	 */
	public void setInspTransactionId(String inspTransactionId) {
		this.inspTransactionId = inspTransactionId;
	}
	
	/**
	 * @return the document_Name
	 */
	public String getDocument_Name() {
		return document_Name;
	}
	
	/**
	 * @param document_Name the document_Name to set
	 */
	public void setDocument_Name(String document_Name) {
		this.document_Name = document_Name;
	}
	
	
	
}
