package com.wipro.igrs.empmgmt.dto;

import java.io.Serializable;

public class TransferDTO implements Serializable{
	private String employeeId;
	private String oldLoc;
	private String newLoc;
	private String dateOfJoining;
	private String comments;
	private String transferType;
	private String designation;
	private String transferStatus;
	private String employeeName;
	private String department;
	private String officeId;
	
	/**
	 * @return the officeId
	 */
	public String getOfficeId() {
		return officeId;
	}
	/**
	 * @param officeId the officeId to set
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return the oldLoc
	 */
	public String getOldLoc() {
		return oldLoc;
	}
	/**
	 * @param oldLoc the oldLoc to set
	 */
	public void setOldLoc(String oldLoc) {
		this.oldLoc = oldLoc;
	}
	/**
	 * @return the newLoc
	 */
	public String getNewLoc() {
		return newLoc;
	}
	/**
	 * @param newLoc the newLoc to set
	 */
	public void setNewLoc(String newLoc) {
		this.newLoc = newLoc;
	}
	/**
	 * @return the dateOfJoining
	 */
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the transferType
	 */
	public String getTransferType() {
		return transferType;
	}
	/**
	 * @param transferType the transferType to set
	 */
	public void setTransferType(String transferType) {
		this.transferType = transferType;
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
	 * @return the transferStatus
	 */
	public String getTransferStatus() {
		return transferStatus;
	}
	/**
	 * @param transferStatus the transferStatus to set
	 */
	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

}

