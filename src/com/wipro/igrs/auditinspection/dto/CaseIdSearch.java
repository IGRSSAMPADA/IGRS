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


public class CaseIdSearch implements Serializable {
	private String	caseId;
	private String	caseDate;
	
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCaseDate() {
		return caseDate;
	}
	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}


}
