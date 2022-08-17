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


public class CaseDetails implements Serializable {

	
	private String txtCaseID;
	private String caseStatus;
	private String txtAgmpComm;
	private String objDetails; 
	
	public String getObjDetails() {
		return objDetails;
	}
	public void setObjDetails(String objDetails) {
		this.objDetails = objDetails;
	}
	public String getTxtAgmpComm() {
		return txtAgmpComm;
	}
	public void setTxtAgmpComm(String txtAgmpComm) {
		this.txtAgmpComm = txtAgmpComm;
	}
	public String getTxtCaseID() {
		return txtCaseID;
	}
	public void setTxtCaseID(String txtCaseID) {
		this.txtCaseID = txtCaseID;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	
	
}
