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

import org.apache.struts.action.ActionForm;

public class PoiTxnForm extends ActionForm{

	
	private String txnID;
	private String poName;
	private String poAddress;
	private String inspFromDate;
	private String inspToDate;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	private String dispatchDate;
	private String inspDate;
	private String dispatchNo;
	
	private String searched=null;
	
	//added by shruti
	private String language;
	
	

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTxnID() {
		return txnID;
	}

	public void setTxnID(String txnID) {
		this.txnID = txnID;
	}

	public String getPoName() {
		return poName;
	}

	public void setPoName(String poName) {
		this.poName = poName;
	}

	public String getPoAddress() {
		return poAddress;
	}

	public void setPoAddress(String poAddress) {
		this.poAddress = poAddress;
	}

	public String getInspFromDate() {
		return inspFromDate;
	}

	public void setInspFromDate(String inspFromDate) {
		this.inspFromDate = inspFromDate;
	}

	public String getInspToDate() {
		return inspToDate;
	}

	public void setInspToDate(String inspToDate) {
		this.inspToDate = inspToDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getInspDate() {
		return inspDate;
	}

	public void setInspDate(String inspDate) {
		this.inspDate = inspDate;
	}

	public String getDispatchNo() {
		return dispatchNo;
	}

	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}

	public String getSearched() {
		return searched;
	}

	public void setSearched(String searched) {
		this.searched = searched;
	}
	
}
