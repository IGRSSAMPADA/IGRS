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

public class POIAddObjectionDTO implements Serializable{
	private String txtDocID;
	private String txtSRname;
	private String txtStampDuty1;
	private String txtRegFee1;
	private String tempValAgmp;
	private String txtStampDuty;
	private String txtRegFee;
	private String txtObjDetails;
	private String txtAgmpComm;
	private String objectionStatus;
	//following variables Added by shruti for removing jsp error(DocDisplayDetails.jsp).
	private String caseNo;     
	private String paraname;
	private String paratype;
	private String ragistrationDate;
	private String executionDate;
	private String tranPartyName;
	private String propertyDescription;
	private String propertyAddress;
	private String propertyArea;
	private String registrationFee;
	private String stamDutyInDoc;
	private String registrationTime;
	private String proposedValuationByDR;
	private String deficientStampDuty;
	private String deficientRegFee;
	private String drComments;
	private String igComments;
	private String objectsDetails;
	private String objectStatus;
	private String caseStatus;
	private String finalComments;
	
	
	//added by shruti
	private String marketVal;
	private String regDate;
	private String regFlag;
	
	RegIdOtherSearch	 regIdOther	= new RegIdOtherSearch();
	
	public String getMarketVal() {
		return marketVal;
	}
	public void setMarketVal(String marketVal) {
		this.marketVal = marketVal;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getRegFlag() {
		return regFlag;
	}
	public void setRegFlag(String regFlag) {
		this.regFlag = regFlag;
	}
	public String getParaname() {
		return paraname;
	}
	public void setParaname(String paraname) {
		this.paraname = paraname;
	}
	public String getParatype() {
		return paratype;
	}
	public void setParatype(String paratype) {
		this.paratype = paratype;
	}
	public String getRagistrationDate() {
		return ragistrationDate;
	}
	public void setRagistrationDate(String ragistrationDate) {
		this.ragistrationDate = ragistrationDate;
	}
	public String getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}
	public String getTranPartyName() {
		return tranPartyName;
	}
	public void setTranPartyName(String tranPartyName) {
		this.tranPartyName = tranPartyName;
	}
	public String getPropertyDescription() {
		return propertyDescription;
	}
	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}
	public String getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	public String getPropertyArea() {
		return propertyArea;
	}
	public void setPropertyArea(String propertyArea) {
		this.propertyArea = propertyArea;
	}
	public String getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}
	public String getStamDutyInDoc() {
		return stamDutyInDoc;
	}
	public void setStamDutyInDoc(String stamDutyInDoc) {
		this.stamDutyInDoc = stamDutyInDoc;
	}
	public String getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}
	public String getProposedValuationByDR() {
		return proposedValuationByDR;
	}
	public void setProposedValuationByDR(String proposedValuationByDR) {
		this.proposedValuationByDR = proposedValuationByDR;
	}
	public String getDeficientStampDuty() {
		return deficientStampDuty;
	}
	public void setDeficientStampDuty(String deficientStampDuty) {
		this.deficientStampDuty = deficientStampDuty;
	}
	public String getDeficientRegFee() {
		return deficientRegFee;
	}
	public void setDeficientRegFee(String deficientRegFee) {
		this.deficientRegFee = deficientRegFee;
	}
	public String getDrComments() {
		return drComments;
	}
	public void setDrComments(String drComments) {
		this.drComments = drComments;
	}
	public String getIgComments() {
		return igComments;
	}
	public void setIgComments(String igComments) {
		this.igComments = igComments;
	}
	public String getObjectsDetails() {
		return objectsDetails;
	}
	public void setObjectsDetails(String objectsDetails) {
		this.objectsDetails = objectsDetails;
	}
	public String getObjectStatus() {
		return objectStatus;
	}
	public void setObjectStatus(String objectStatus) {
		this.objectStatus = objectStatus;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getFinalComments() {
		return finalComments;
	}
	public void setFinalComments(String finalComments) {
		this.finalComments = finalComments;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	/**
     * @return the objectionStatus
     */
    public String getObjectionStatus() {
    	return objectionStatus;
    }
	/**
     * @param objectionStatus the objectionStatus to set
     */
    public void setObjectionStatus(String objectionStatus) {
    	this.objectionStatus = objectionStatus;
    }
	/**
     * @return the txtDocID
     */
    public String getTxtDocID() {
    	return txtDocID;
    }
	/**
     * @param txtDocID the txtDocID to set
     */
    public void setTxtDocID(String txtDocID) {
    	this.txtDocID = txtDocID;
    }
	/**
     * @return the txtSRname
     */
    public String getTxtSRname() {
    	return txtSRname;
    }
	/**
     * @param txtSRname the txtSRname to set
     */
    public void setTxtSRname(String txtSRname) {
    	this.txtSRname = txtSRname;
    }
	/**
     * @return the txtStampDuty1
     */
    public String getTxtStampDuty1() {
    	return txtStampDuty1;
    }
	/**
     * @param txtStampDuty1 the txtStampDuty1 to set
     */
    public void setTxtStampDuty1(String txtStampDuty1) {
    	this.txtStampDuty1 = txtStampDuty1;
    }
	/**
     * @return the txtRegFee1
     */
    public String getTxtRegFee1() {
    	return txtRegFee1;
    }
	/**
     * @param txtRegFee1 the txtRegFee1 to set
     */
    public void setTxtRegFee1(String txtRegFee1) {
    	this.txtRegFee1 = txtRegFee1;
    }
	/**
     * @return the tempValAgmp
     */
    public String getTempValAgmp() {
    	return tempValAgmp;
    }
	/**
     * @param tempValAgmp the tempValAgmp to set
     */
    public void setTempValAgmp(String tempValAgmp) {
    	this.tempValAgmp = tempValAgmp;
    }
	/**
     * @return the txtStampDuty
     */
    public String getTxtStampDuty() {
    	return txtStampDuty;
    }
	/**
     * @param txtStampDuty the txtStampDuty to set
     */
    public void setTxtStampDuty(String txtStampDuty) {
    	this.txtStampDuty = txtStampDuty;
    }
	/**
     * @return the txtRegFee
     */
    public String getTxtRegFee() {
    	return txtRegFee;
    }
	/**
     * @param txtRegFee the txtRegFee to set
     */
    public void setTxtRegFee(String txtRegFee) {
    	this.txtRegFee = txtRegFee;
    }
	/**
     * @return the txtObjDetails
     */
    public String getTxtObjDetails() {
    	return txtObjDetails;
    }
	/**
     * @param txtObjDetails the txtObjDetails to set
     */
    public void setTxtObjDetails(String txtObjDetails) {
    	this.txtObjDetails = txtObjDetails;
    }
	/**
     * @return the txtAgmpComm
     */
    public String getTxtAgmpComm() {
    	return txtAgmpComm;
    }
	/**
     * @param txtAgmpComm the txtAgmpComm to set
     */
    public void setTxtAgmpComm(String txtAgmpComm) {
    	this.txtAgmpComm = txtAgmpComm;
    }
	
}
