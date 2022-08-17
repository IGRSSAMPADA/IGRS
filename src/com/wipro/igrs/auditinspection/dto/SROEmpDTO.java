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

public class SROEmpDTO implements Serializable{
	
	private String	userId;
	
	private String	inspectionId;
	
	private String	designation;
	
	private String	joinDate;
	
	private String	seperarionDate;
	
	private String	employeename;
	
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
	
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	/**
	 * @return the joinDate
	 */
	public String getJoinDate() {
		return joinDate;
	}
	
	/**
	 * @param joinDate the joinDate to set
	 */
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	/**
	 * @return the seperarionDate
	 */
	public String getSeperarionDate() {
		return seperarionDate;
	}
	
	/**
	 * @param seperarionDate the seperarionDate to set
	 */
	public void setSeperarionDate(String seperarionDate) {
		this.seperarionDate = seperarionDate;
	}
	
	

	/**
	 * @return the employeename
	 */
	public String getEmployeename() {
		return employeename;
	}

	/**
	 * @param employeename the employeename to set
	 */
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	
	
	
	
}
