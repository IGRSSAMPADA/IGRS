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

public class DROPendingTaskDTO implements Serializable {
	
	private String pendingTask;
	private String pendingNo;
	private String pendingFrom;
	
	/**
     * @return the pendingNo
     */
    public String getPendingNo() {
    	return pendingNo;
    }
	/**
     * @param pendingNo the pendingNo to set
     */
    public void setPendingNo(String pendingNo) {
    	this.pendingNo = pendingNo;
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
	/**
	 * @return the pendingTask
	 */
	public String getPendingTask() {
		return pendingTask;
	}
	/**
	 * @param pendingTask the pendingTask to set
	 */
	public void setPendingTask(String pendingTask) {
		this.pendingTask = pendingTask;
	}

}

