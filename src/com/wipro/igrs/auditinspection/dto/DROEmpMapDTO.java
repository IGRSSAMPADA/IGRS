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

public class DROEmpMapDTO implements Serializable{
	
	private String inspId;
	
	private String empId;
	
	private String empName;
	
	private String designation;
	
	private String joiningDate;
	
	private String seperationDate;
	
	private String flagEmp;
	
	
	
	public String getFlagEmp() {
		return flagEmp;
	}
	public void setFlagEmp(String flagEmp) {
		this.flagEmp = flagEmp;
	}
	/**
     * @return the inspId
     */
    public String getInspId() {
    	return inspId;
    }
	/**
     * @param inspId the inspId to set
     */
    public void setInspId(String inspId) {
    	this.inspId = inspId;
    }
	/**
     * @return the empId
     */
    public String getEmpId() {
    	return empId;
    }
	/**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
    	this.empId = empId;
    }
	/**
     * @return the empName
     */
    public String getEmpName() {
    	return empName;
    }
	/**
     * @param empName the empName to set
     */
    public void setEmpName(String empName) {
    	this.empName = empName;
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
     * @return the joiningDate
     */
    public String getJoiningDate() {
    	return joiningDate;
    }
	/**
     * @param joiningDate the joiningDate to set
     */
    public void setJoiningDate(String joiningDate) {
    	this.joiningDate = joiningDate;
    }
	/**
     * @return the seperationDate
     */
    public String getSeperationDate() {
    	return seperationDate;
    }
	/**
     * @param seperationDate the seperationDate to set
     */
    public void setSeperationDate(String seperationDate) {
    	this.seperationDate = seperationDate;
    }

}

