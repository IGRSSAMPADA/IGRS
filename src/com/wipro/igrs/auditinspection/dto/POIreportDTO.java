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
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

public class POIreportDTO implements Serializable{

	
	private String address;
	private String district;
	private String insDate;
	private String insEntryDate;
	private String toDate;
	private String fromDate;
	private String comments;
	//private String auditDate;
	//private String dispatchDate;
	//private String entryDate;
	//private String physicalreportId;
	private FormFile auditReport;
	private FormFile others;
	
	
	
	
	private String districtName;
	private String districtId;
	private ArrayList districtList;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getInsDate() {
		return insDate;
	}
	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}
	public String getInsEntryDate() {
		return insEntryDate;
	}
	public void setInsEntryDate(String insEntryDate) {
		this.insEntryDate = insEntryDate;
	}
	/*
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	*/
	public FormFile getAuditReport() {
		return auditReport;
	}
	public void setAuditReport(FormFile auditReport) {
		this.auditReport = auditReport;
	}
	public FormFile getOthers() {
		return others;
	}
	public void setOthers(FormFile others) {
		this.others = others;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public ArrayList getDistrictList() {
		return districtList;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	
}
