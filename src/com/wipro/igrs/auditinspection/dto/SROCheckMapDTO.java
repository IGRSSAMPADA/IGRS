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

public class SROCheckMapDTO implements Serializable{
	
	
	private String	itemId;
	
	private String	itemName;
	
	private String	itemRemarks;
	
	private String	chkVal;
	
	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}
	
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	 * @return the chkVal
	 */
	public String getChkVal() {
		return chkVal;
	}
	
	/**
	 * @param chkVal the chkVal to set
	 */
	public void setChkVal(String chkVal) {
		this.chkVal = chkVal;
	}
	
	
	
	

}
