/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   MunicipalBodyDTO.java
 * Author      :   Mostafa Mahmoud 
 * Description :   Represents the DTO Class for Municipal Body Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mostafa Mahmoud 10th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.municipalbodymaster.dto;

public class MunicipalBodyDTO {
	
	private String municipalBodyId;
	private String municipalBodyName;
	private String municipalBodyDesc;
	private String municipalBodyStatus;
	private String municipalBodyCreatedById;
	private String municipalBodyCreatedDate;
	private String municipalBodyModifiedById;
	private String municipalBodyModificationDate;
	private String municipalBodyCreatedByName;
	private String municipalBodyModifiedByName;
	
	
	public MunicipalBodyDTO() {
		
	}
	
	public String getMunicipalBodyId() {
		return municipalBodyId;
	}
	public void setMunicipalBodyId(String municipalBodyId) {
		this.municipalBodyId = municipalBodyId;
	}
	public String getMunicipalBodyName() {
		return municipalBodyName;
	}
	public void setMunicipalBodyName(String municipalBodyName) {
		this.municipalBodyName = municipalBodyName;
	}
	public String getMunicipalBodyDesc() {
		return municipalBodyDesc;
	}
	public void setMunicipalBodyDesc(String municipalBodyDesc) {
		this.municipalBodyDesc = municipalBodyDesc;
	}
	public String getMunicipalBodyStatus() {
		return municipalBodyStatus;
	}
	public void setMunicipalBodyStatus(String municipalBodyStatus) {
		this.municipalBodyStatus = municipalBodyStatus;
	}
	public String getMunicipalBodyCreatedById() {
		return municipalBodyCreatedById;
	}
	public void setMunicipalBodyCreatedById(String municipalBodyCreatedById) {
		this.municipalBodyCreatedById = municipalBodyCreatedById;
	}
	public String getMunicipalBodyModifiedById() {
		return municipalBodyModifiedById;
	}
	public void setMunicipalBodyModifiedById(String municipalBodyModifiedById) {
		this.municipalBodyModifiedById = municipalBodyModifiedById;
	}
	public String getMunicipalBodyCreatedByName() {
		return municipalBodyCreatedByName;
	}
	public void setMunicipalBodyCreatedByName(String municipalBodyCreatedByName) {
		this.municipalBodyCreatedByName = municipalBodyCreatedByName;
	}
	public String getMunicipalBodyModifiedByName() {
		return municipalBodyModifiedByName;
	}
	public void setMunicipalBodyModifiedByName(String municipalBodyModifiedByName) {
		this.municipalBodyModifiedByName = municipalBodyModifiedByName;
	}
	public String getMunicipalBodyCreatedDate() {
		return municipalBodyCreatedDate;
	}
	public void setMunicipalBodyCreatedDate(String municipalBodyCreatedDate) {
		this.municipalBodyCreatedDate = municipalBodyCreatedDate;
	}
	public String getMunicipalBodyModificationDate() {
		return municipalBodyModificationDate;
	}
	public void setMunicipalBodyModificationDate(
			String municipalBodyModificationDate) {
		this.municipalBodyModificationDate = municipalBodyModificationDate;
	}
	
	

}
