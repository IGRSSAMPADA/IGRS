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


public class CaseIdOtherSearch implements Serializable {
	
	
	
	private String case_Id;
	private String case_Date;
	private String case_Status;
	public String getCase_Id() {
		return case_Id;
	}
	public void setCase_Id(String case_Id) {
		this.case_Id = case_Id;
	}
	public String getCase_Date() {
		return case_Date;
	}
	public void setCase_Date(String case_Date) {
		this.case_Date = case_Date;
	}
	public String getCase_Status() {
		return case_Status;
	}
	public void setCase_Status(String case_Status) {
		this.case_Status = case_Status;
	}
	
	
	
}
