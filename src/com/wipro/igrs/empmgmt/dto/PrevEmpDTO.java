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
* PrevEmpDTO.java <br>
* PrevEmpDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class PrevEmpDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7339176317696507960L;
	private String employee_id;
	private String organization;
	private String designation;
	private String durationYears;
	private String durationMonths;
	private String compensation;
	private String pfAccLocation;
	private String pfAccNo;
	private String reasonForSeparation;
	private String taxDeductions;

	private String fromDate;
	private String toDate;

	public String getCompensation() {
		return compensation;
	}

	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDurationMonths() {
		return durationMonths;
	}

	public void setDurationMonths(String durationMonths) {
		this.durationMonths = durationMonths;
	}

	public String getDurationYears() {
		return durationYears;
	}

	public void setDurationYears(String durationYears) {
		this.durationYears = durationYears;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getPfAccLocation() {
		return pfAccLocation;
	}

	public void setPfAccLocation(String pfAccLocation) {
		this.pfAccLocation = pfAccLocation;
	}

	public String getPfAccNo() {
		return pfAccNo;
	}

	public void setPfAccNo(String pfAccNo) {
		this.pfAccNo = pfAccNo;
	}

	public String getReasonForSeparation() {
		return reasonForSeparation;
	}

	public void setReasonForSeparation(String reasonForSeparation) {
		this.reasonForSeparation = reasonForSeparation;
	}

	public String getTaxDeductions() {
		return taxDeductions;
	}

	public void setTaxDeductions(String taxDeductions) {
		this.taxDeductions = taxDeductions;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
}