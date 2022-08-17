/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterForm.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master DTO Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	
 *  1.0         Sayed Taha(Wipro-egypt)     27th Aug, 2008  		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.deedparammaster.form;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;



public class DeedParamMasterForm extends ActionForm{

	private String paramid;
	private String paramname;                    
	private String paramDesc;                
	private String paramstatus;                     
	private String createdby;
	private String createddate;
	private String updatedby;
	private String updatedDate;
	
	private String [] selectedDeedParam;
	
	ArrayList deedparamList;
	
	public String getParamid() {
		return paramid;
	}
	public void setParamid(String paramid) {
		this.paramid = paramid;
	}
	public String getParamname() {
		return paramname;
	}
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	public String getParamDesc() {
		return paramDesc;
	}
	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}
	public String getParamstatus() {
		return paramstatus;
	}
	public void setParamstatus(String paramstatus) {
		this.paramstatus = paramstatus;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		createddate = createddate;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public ArrayList getDeedparamList() {
		return deedparamList;
	}
	public void setDeedparamList(ArrayList deedparamList) {
		this.deedparamList = deedparamList;
	}
	public String[] getSelectedDeedParam() {
		return selectedDeedParam;
	}
	public void setSelectedDeedParam(String[] selectedDeedParam) {
		this.selectedDeedParam = selectedDeedParam;
	}
	
	
	
	
}