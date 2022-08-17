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

public class SROCommentsDTO implements Serializable{
	
	private String	sroComments;
	
	private String	userId;
	
	private String	inspectionId;
	
	/**
	 * @return the sroComments
	 */
	public String getSroComments() {
		return sroComments;
	}
	
	/**
	 * @param sroComments the sroComments to set
	 */
	public void setSroComments(String sroComments) {
		this.sroComments = sroComments;
	}
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the inspectionId
	 */
	public String getInspectionId() {
		return inspectionId;
	}
	
	/**
	 * @param inspectionId the inspectionId to set
	 */
	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}
	
	
	
	

}
