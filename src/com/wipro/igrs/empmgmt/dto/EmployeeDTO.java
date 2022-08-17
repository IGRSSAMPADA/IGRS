/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.empmgmt.dto;

import java.io.Serializable;


/**
* 
* EmployeeDTO.java <br>
* EmployeeDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class EmployeeDTO implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1773658696134934326L;
private String employeeNumber;
private String employeeName;
private String dateOfJoining;
private String payScale;
private String employeeDesignation;
private String placeOfPosting;
private String officalAddress;
private String residencalAddress;
private String contactNumber;
private String email;
private String result;
private String empSalary;
private String empClass;

public String getEmpSalary() {
	return empSalary;
}
public void setEmpSalary(String empSalary) {
	this.empSalary = empSalary;
}
public String getEmpClass() {
	return empClass;
}
public void setEmpClass(String empClass) {
	this.empClass = empClass;
}
/**
 * @return the employeeNumber
 */
public String getEmployeeNumber() {
	return employeeNumber;
}
/**
 * @param employeeNumber the employeeNumber to set
 */
public void setEmployeeNumber(String employeeNumber) {
	this.employeeNumber = employeeNumber;
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
 * @return the payScale
 */
public String getPayScale() {
	return payScale;
}
/**
 * @param payScale the payScale to set
 */
public void setPayScale(String payScale) {
	this.payScale = payScale;
}
/**
 * @return the employeeDesignation
 */
public String getEmployeeDesignation() {
	return employeeDesignation;
}
/**
 * @param employeeDesignation the employeeDesignation to set
 */
public void setEmployeeDesignation(String employeeDesignation) {
	this.employeeDesignation = employeeDesignation;
}
/**
 * @return the placeOfPosting
 */
public String getPlaceOfPosting() {
	return placeOfPosting;
}
/**
 * @param placeOfPosting the placeOfPosting to set
 */
public void setPlaceOfPosting(String placeOfPosting) {
	this.placeOfPosting = placeOfPosting;
}
/**
 * @return the officalAddress
 */
public String getOfficalAddress() {
	return officalAddress;
}
/**
 * @param officalAddress the officalAddress to set
 */
public void setOfficalAddress(String officalAddress) {
	this.officalAddress = officalAddress;
}
/**
 * @return the residencalAddress
 */
public String getResidencalAddress() {
	return residencalAddress;
}
/**
 * @param residencalAddress the residencalAddress to set
 */
public void setResidencalAddress(String residencalAddress) {
	this.residencalAddress = residencalAddress;
}
/**
 * @return the contactNumber
 */
public String getContactNumber() {
	return contactNumber;
}
/**
 * @param contactNumber the contactNumber to set
 */
public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}
/**
 * @return the email
 */
public String getEmail() {
	return email;
}
/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * @return the result
 */
public String getResult() {
	return result;
}
/**
 * @param result the result to set
 */
public void setResult(String result) {
	this.result = result;
}
}

