/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :  DeliveryOfDocument.java
 * Author      :   Aakriti Kaplish
 * Description :   Represents the Form Class for Delivery Of documents
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     	21,June, 2013	1.0      
 *     		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.DeliveryOfDocuments.form;


import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import com.wipro.igrs.DeliveryOfDocuments.dto.DeliveryOfDocumentsDTO;



public class DeliveryOfDocumentsForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DeliveryOfDocumentsDTO doddto = new DeliveryOfDocumentsDTO();
	
	private ArrayList statuslist = new ArrayList();
		
	private String uid;
	private String formName;
	private String actionName;
	
	
	private String regNo;
	private String statusSelected;
	private String statusSelectedName;
	private String fromRequestDate;
	private String toRequestDate;
	private String docName;
	private String docPath;
	
	// Added by Neeti
	private String rcmsPrintUrl;
	
	
	public void setRcmsPrintUrl(String rcmsPrintUrl) {
		this.rcmsPrintUrl = rcmsPrintUrl;
	}
	public String getRcmsPrintUrl() {
		return rcmsPrintUrl;
	}
	
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getStatusSelected() {
		return statusSelected;
	}
	public String getStatusSelectedName() {
		return statusSelectedName;
	}
	public void setStatusSelectedName(String statusSelectedName) {
		this.statusSelectedName = statusSelectedName;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getFromRequestDate() {
		return fromRequestDate;
	}
	public void setFromRequestDate(String fromRequestDate) {
		this.fromRequestDate = fromRequestDate;
	}
	public String getToRequestDate() {
		return toRequestDate;
	}
	public void setToRequestDate(String toRequestDate) {
		this.toRequestDate = toRequestDate;
	}
	public void setStatusSelected(String statusSelected) {
		this.statusSelected = statusSelected;
	}
	public ArrayList getStatuslist() {
		return statuslist;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public void setStatuslist(ArrayList statuslist) {
		this.statuslist = statuslist;
	}
	public DeliveryOfDocumentsDTO getDoddto() {
		return doddto;
	}
	public void setDoddto(DeliveryOfDocumentsDTO doddto) {
		this.doddto = doddto;
	}
	
	
	
}