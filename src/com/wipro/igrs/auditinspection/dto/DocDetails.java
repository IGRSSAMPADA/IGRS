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


public class DocDetails implements Serializable {

	private String	valAgmp;

	private String	stampDuty;

	private String	regFee;

	private String	objDetails;

	private String	txtAgmpComm;

	private String	docId;

	private String	txtStampDuty1;

	private String	txtRegFee1;

	private String	txtSRname;

	private String	txtDocID;

	private String	docStatusId;
	//added by shruti
	private String miscStatusId;
	private String caseDocID;
	private String marketVal;
	

	public String getMarketVal() {
		return marketVal;
	}

	public void setMarketVal(String marketVal) {
		this.marketVal = marketVal;
	}

	public String getCaseDocID() {
		return caseDocID;
	}

	public void setCaseDocID(String caseDocID) {
		this.caseDocID = caseDocID;
	}

	public String getMiscStatusId() {
		return miscStatusId;
	}

	public void setMiscStatusId(String miscStatusId) {
		this.miscStatusId = miscStatusId;
	}

	/**
	 * @return the valAgmp
	 */
	public String getValAgmp() {
		return valAgmp;
	}

	/**
	 * @param valAgmp the valAgmp to set
	 */
	public void setValAgmp(String valAgmp) {
		this.valAgmp = valAgmp;
	}

	/**
	 * @return the stampDuty
	 */
	public String getStampDuty() {
		return stampDuty;
	}

	/**
	 * @param stampDuty the stampDuty to set
	 */
	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}

	/**
	 * @return the regFee
	 */
	public String getRegFee() {
		return regFee;
	}

	/**
	 * @param regFee the regFee to set
	 */
	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}

	/**
	 * @return the objDetails
	 */
	public String getObjDetails() {
		return objDetails;
	}

	/**
	 * @param objDetails the objDetails to set
	 */
	public void setObjDetails(String objDetails) {
		this.objDetails = objDetails;
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

	/**
	 * @return the docId
	 */
	public String getDocId() {
		return docId;
	}

	/**
	 * @param docId the docId to set
	 */
	public void setDocId(String docId) {
		this.docId = docId;
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
	 * @return the docStatusId
	 */
	public String getDocStatusId() {
		return docStatusId;
	}

	/**
	 * @param docStatusId the docStatusId to set
	 */
	public void setDocStatusId(String docStatusId) {
		this.docStatusId = docStatusId;
	}

}
