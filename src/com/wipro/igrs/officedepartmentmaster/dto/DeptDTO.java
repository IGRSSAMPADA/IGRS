/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 
 * ===========================================================================
 * File           :   DeptDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Sayed Taha
 * Created Date   :   Aug 11, 2008

 * ===========================================================================
 */
package com.wipro.igrs.officedepartmentmaster.dto;

import java.util.Date;

public class DeptDTO {
	  private String deptID;
	  private String deptName;
	  private String deptDesc;
	  private String deptStatus;
	  private String createdBy;
	  private Date createdDate;
	  private String updatedBy;
	  private Date updateDate; 


/**
 * @return the deptID
 */

public String getDeptID() {
	return deptID;
}
/**
 * @param deptID
 *            the deptID to set
 */

public void setDeptID(String deptID) {
	this.deptID = deptID;
}
/**
 * @return the deptName
 */
public String getDeptName() {
	return deptName;
}
/**
 * @param deptName
 *            the deptName to set
 */
public void setDeptName(String deptName) {
	this.deptName = deptName;
}
/**
 * @return the deptDesc
 */
public String getDeptDesc() {
	return deptDesc;
}
/**
 * @param deptDesc
 *            the deptDesc to set
 */
public void setDeptDesc(String deptDesc) {
	this.deptDesc = deptDesc;
}
/**
 * @return the deptStatus
 */
public String getDeptStatus() {
	return deptStatus;
}
/**
 * @param deptStatus
 *            the deptStatus to set
 */
public void setDeptStatus(String deptStatus) {
	this.deptStatus = deptStatus;
}
/**
 * @return the createdBy
 */
public String getCreatedBy() {
	return createdBy;
}
/**
 * @param createdBy
 *            the createdBy to set
 */
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
/**
 * @return the createdDate
 */
public Date getCreatedDate() {
	return createdDate;
}
/**
 * @param createdDate
 *            the createdDate to set
 */
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
/**
 * @return the updatedBy
 */
public String getUpdatedBy() {
	return updatedBy;
}
/**
 * @param updatedBy
 *            the updatedBy to set
 */
public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
}
/**
 * @return the updateDate
 */
public Date getUpdateDate() {
	return updateDate;
}
/**
 * @param updateDate
 *            the updateDate to set
 */
public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
}
}