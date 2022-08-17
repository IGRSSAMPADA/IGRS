/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   DepartmentMasterForm.java
 * Author      :   Sayed Taha 
 * Description :   Represents the Office Department Action Form.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 *Created Date :   11 aug 2008
 */
package com.wipro.igrs.officedepartmentmaster.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class DepartmentMasterForm extends ActionForm{
	
	private ArrayList allDepartments;
    private String pageName;
    private String deptID;
    private String deptName;
    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;
    private String status;   
    private String description;
   private String[] selectedDept;
	public ArrayList getAllDepartments() {
		return allDepartments;
	}
	public void setAllDepartments(ArrayList allDepartments) {
		this.allDepartments = allDepartments;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String[] getSelectedDept() {
		return selectedDept;
	}
	public void setSelectedDept(String[] selectedDept) {
		this.selectedDept = selectedDept;
	}
}
