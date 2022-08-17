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

public class DROInspItemMap implements Serializable {
	
	private String inspReportId;
	private String itemId;
	private String itemName;
	private String itemRemarks;
	private int itemValue;
	private String itemStatus;
	private String fromDate;
	private String toDate;
	
	
	public String getInspReportId() {
    	return inspReportId;
    }
	public void setInspReportId(String inspReportId) {
    	this.inspReportId = inspReportId;
    }
	public String getItemId() {
    	return itemId;
    }
	public void setItemId(String itemId) {
    	this.itemId = itemId;
    }
	public String getItemName() {
    	return itemName;
    }
	public void setItemName(String itemName) {
    	this.itemName = itemName;
    }
	
	public String getFromDate() {
    	return fromDate;
    }
	public void setFromDate(String fromDate) {
    	this.fromDate = fromDate;
    }
	public String getToDate() {
    	return toDate;
    }
	public void setToDate(String toDate) {
    	this.toDate = toDate;
    }
	/**
     * @return the itemRemarks
     */
    public String getItemRemarks() {
    	return itemRemarks;
    }
	/**
     * @param itemRemarks the itemRemarks to set
     */
    public void setItemRemarks(String itemRemarks) {
    	this.itemRemarks = itemRemarks;
    }
	/**
     * @param itemValue the itemValue to set
     */
    public void setItemValue(int itemValue) {
    	this.itemValue = itemValue;
    }
	/**
     * @return the itemValue
     */
    public int getItemValue() {
    	return itemValue;
    }
	/**
     * @return the itemStatus
     */
    public String getItemStatus() {
    	return itemStatus;
    }
	/**
     * @param itemStatus the itemStatus to set
     */
    public void setItemStatus(String itemStatus) {
    	this.itemStatus = itemStatus;
    }

}

