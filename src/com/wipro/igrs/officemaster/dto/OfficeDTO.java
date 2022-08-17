/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   OfficeDTO.java
 * Author      :   Mahmoud Eid 
 * Description :   Represents the DTO Class for Office Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mahmoud Eid  10th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.officemaster.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class OfficeDTO implements Serializable{
	
	public OfficeDTO()
	{
		
	}
	
	private String officeId;
	private String officeName;
	private String officeDesc;
	private String officeStatus;
	private String pageName;
	private String parentId;
	private String districtId;
	private String tehsilId;
	private String wardId;
	private String officeAddress;
	private String officePhoneNumber;
	private String officeFaxNumber;
	private String mohallaVillageId;
	private String officeTypeId;
	private String createdby;
	private String updatedby;
	private String updatedDate;
	private String createdDate;
	private String parentName;
	private String districtName;
	private String tehsilName;
	private String wardName;
	private String mohallaVillageName;
	private String officeTypeName;
	
	
	
	
	
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getOfficeDesc() {
		return officeDesc;
	}
	public void setOfficeDesc(String officeDesc) {
		this.officeDesc = officeDesc;
	}
	public String getOfficeStatus() {
		return officeStatus;
	}
	public void setOfficeStatus(String officeStatus) {
		this.officeStatus = officeStatus;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getWardId() {
		return wardId;
	}
	public void setWardId(String wardId) {
		this.wardId = wardId;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public String getOfficePhoneNumber() {
		return officePhoneNumber;
	}
	public void setOfficePhoneNumber(String officePhoneNumber) {
		this.officePhoneNumber = officePhoneNumber;
	}
	public String getOfficeFaxNumber() {
		return officeFaxNumber;
	}
	public void setOfficeFaxNumber(String officeFaxNumber) {
		this.officeFaxNumber = officeFaxNumber;
	}
	public String getMohallaVillageId() {
		return mohallaVillageId;
	}
	public void setMohallaVillageId(String mohallaVillageId) {
		this.mohallaVillageId = mohallaVillageId;
	}
	public String getOfficeTypeId() {
		return officeTypeId;
	}
	public void setOfficeTypeId(String officeTypeId) {
		this.officeTypeId = officeTypeId;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getMohallaVillageName() {
		return mohallaVillageName;
	}
	public void setMohallaVillageName(String mohallaVillageName) {
		this.mohallaVillageName = mohallaVillageName;
	}
	public String getOfficeTypeName() {
		return officeTypeName;
	}
	public void setOfficeTypeName(String officeTypeName) {
		this.officeTypeName = officeTypeName;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	

}
