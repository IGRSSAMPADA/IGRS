/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterDTO.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master DTO Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 
 *  1.0         Sayed Taha(Wipro-egypt)     27th Aug, 2008 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.deedparammaster.dto;

import java.util.ArrayList;
import java.io.Serializable;


public class DeedParamMasterDTO implements Serializable{
	/**
	 * 
	 */
	private String paramid;
	private String paramname;                    
	private String paramDesc;                
	private String paramstatus;                     
	private String createdby;
	private String Createddate;
	private String updatedby;
	private String updatedDate;
	
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
		return Createddate;
	}
	public void setCreateddate(String createddate) {
		Createddate = createddate;
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
	

	
	
}