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

public class SROPendingItemsDTO implements Serializable{
   
	
	private String	pendingTaskNo;
	
	private String	pendingItem;
	
	private String	pendingFrom;
	//added by vinay
	private String pendingComment;
	
	
	public String getPendingComment() {
		return pendingComment;
	}

	public void setPendingComment(String pendingComment) {
		this.pendingComment = pendingComment;
	}

	/**
     * @return the pendingTaskNo
     */
    public String getPendingTaskNo() {
    	return pendingTaskNo;
    }

	/**
     * @param pendingTaskNo the pendingTaskNo to set
     */
    public void setPendingTaskNo(String pendingTaskNo) {
    	this.pendingTaskNo = pendingTaskNo;
    }

	/**
     * @return the pendingItem
     */
    public String getPendingItem() {
    	return pendingItem;
    }

	/**
     * @param pendingItem the pendingItem to set
     */
    public void setPendingItem(String pendingItem) {
    	this.pendingItem = pendingItem;
    }

	/**
     * @return the pendingFrom
     */
    public String getPendingFrom() {
    	return pendingFrom;
    }

	/**
     * @param pendingFrom the pendingFrom to set
     */
    public void setPendingFrom(String pendingFrom) {
    	this.pendingFrom = pendingFrom;
    }
	
	
}
