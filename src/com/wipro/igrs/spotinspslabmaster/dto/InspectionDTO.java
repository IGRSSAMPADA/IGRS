/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2008-09
 *==============================================================================
 *
 * File Name   :   InspectionDTO.java
 * Author      :   Yousry Ibrahem 
 * Description :   Represents the DTO Class for Spot Inspection Slab.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Yousry Ibrahem  12th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.spotinspslabmaster.dto;

import java.util.Date;

public class InspectionDTO {
	
	
	private String slabID;
	private String slabMaxRange;
	private String slabMinRange;
	private String status;
	private String percentage;
	private String createdBy;
	private String modifiedBy;
	private String createdDate;
	private String updatedDate;
	private String minSpotInsp;
	private String timeFrom;
	private String timeTo;
	private String remarks;
	private String approvalStatus;
	private String approvalRemarks;
	private String statusDesc;
	
	
	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalRemarks() {
		return approvalRemarks;
	}

	public void setApprovalRemarks(String approvalRemarks) {
		this.approvalRemarks = approvalRemarks;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return Returns the SlabID.
	 */
	public String getSlabID() {
		return slabID;
	}
	
	/**
	 * @param SlabID
	 *            The SlabID to set.
	 */
	public void setSlabID(String slabID) {
		this.slabID = slabID;
	}
	
	/**
	 * @return Returns the SlabMaxRange.
	 */
	public String  getSlabMaxRange() {
		return slabMaxRange;
	}
	
	/**
	 * @param SlabMaxRange
	 *            The SlabMaxRange to set.
	 */
	public void setSlabMaxRange(String  slabMaxRange) {
		this.slabMaxRange = slabMaxRange;
	}
	
	/**
	 * @return Returns the SlabMinRange.
	 */
	public String  getSlabMinRange() {
		return slabMinRange;
	}
	
	/**
	 * @param SlabMinRange
	 *            The SlabMinRange to set.
	 */
	public void setSlabMinRange(String  slabMinRange) {
		this.slabMinRange = slabMinRange;
	}
	
	/**
	 * @return Returns the Status.
	 */
	public String  getStatus() {
		return status;
	}
	
	/**
	 * @param Status
	 *            The Status to set.
	 */
	public void setStatus(String  status) {
		this.status = status;
	}
	
	/**
	 * @return Returns the Percentage.
	 */
	public String  getPercentage() {
		return percentage;
	}
	
	/**
	 * @param Percentage
	 *            The Percentage to set.
	 */
	public void setPercentage(String  percentage) {
		this.percentage = percentage;
	}
	
	/**
	 * @return Returns the CreatedDate.
	 */
	public String  getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * @param CreatedDate
	 *            The CreatedDate to set.
	 */
	public void setCreatedDate(String  createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * @return Returns the UpdatedDate.
	 */
	public String  getUpdatedDate() {
		return updatedDate;
	}
	
	/**
	 * @param UpdatedDate
	 *            The UpdatedDate to set.
	 */
	public void setUpdatedDate(String  updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	/**
	 * @return Returns the MinSpotInsp.
	 */
	public String  getMinSpotInsp() {
		return minSpotInsp;
	}
	
	/**
	 * @param MinSpotInsp
	 *            The MinSpotInsp to set.
	 */
	public void setMinSpotInsp(String  minSpotInsp) {
		this.minSpotInsp = minSpotInsp;
	}

	public String  getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String  createdBy) {
		this.createdBy = createdBy;
	}

	public String  getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String  modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	

}
