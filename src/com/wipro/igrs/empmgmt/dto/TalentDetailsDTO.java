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
* TalentDetailsDTO.java <br>
* TalentDetailsDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class TalentDetailsDTO implements Serializable
	{
		/**
	 * 
	 */
	private static final long serialVersionUID = -1862654495149691024L;
		//acadamic qualification
		//private String chkQuali[];
	    private String employee_id;
		private String degreesID[]; //new field added after db changed.
		private String degree[];
		private String stream[];
		private String passingYear[];
		private String grade[];
		private String createdBy[];
		private String createdDate[];
		
		//examd details
		//private String chkPreEmp[];
		private String organanization[];
		private String designation[];
		private String durationYears[];
		private String durationMonths[];
		private String compensation[];
		private String pfAccLocation[]; 
		private String pfAccNo[];
		private String reasonForSeparation[];
		private String taxDeductions[];
		/**
		 * 
		 */
		public TalentDetailsDTO() {
			// TODO Auto-generated constructor stub
			
		}
		
		public String[] getCompensation() {
			return compensation;
		}
		public void setCompensation(String[] compensation) {
			this.compensation = compensation;
		}
		public String[] getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String[] createdBy) {
			this.createdBy = createdBy;
		}
		public String[] getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(String[] createdDate) {
			this.createdDate = createdDate;
		}
		public String[] getDegree() {
			return degree;
		}
		public void setDegree(String[] degree) {
			this.degree = degree;
		}
		public String[] getDegreesID() {
			return degreesID;
		}
		public void setDegreesID(String[] degreesID) {
			this.degreesID = degreesID;
		}
		public String[] getDesignation() {
			return designation;
		}
		public void setDesignation(String[] designation) {
			this.designation = designation;
		}
		public String[] getDurationMonths() {
			return durationMonths;
		}
		public void setDurationMonths(String[] durationMonths) {
			this.durationMonths = durationMonths;
		}
		public String[] getDurationYears() {
			return durationYears;
		}
		public void setDurationYears(String[] durationYears) {
			this.durationYears = durationYears;
		}
		public String getEmployee_id() {
			return employee_id;
		}
		public void setEmployee_id(String employee_id) {
			this.employee_id = employee_id;
		}
		public String[] getGrade() {
			return grade;
		}
		public void setGrade(String[] grade) {
			this.grade = grade;
		}
		public String[] getOrgananization() {
			return organanization;
		}
		public void setOrgananization(String[] organanization) {
			this.organanization = organanization;
		}
		public String[] getPassingYear() {
			return passingYear;
		}
		public void setPassingYear(String[] passingYear) {
			this.passingYear = passingYear;
		}
		public String[] getPfAccLocation() {
			return pfAccLocation;
		}
		public void setPfAccLocation(String[] pfAccLocation) {
			this.pfAccLocation = pfAccLocation;
		}
		public String[] getPfAccNo() {
			return pfAccNo;
		}
		public void setPfAccNo(String[] pfAccNo) {
			this.pfAccNo = pfAccNo;
		}
		public String[] getReasonForSeparation() {
			return reasonForSeparation;
		}
		public void setReasonForSeparation(String[] reasonForSeparation) {
			this.reasonForSeparation = reasonForSeparation;
		}
		public String[] getStream() {
			return stream;
		}
		public void setStream(String[] stream) {
			this.stream = stream;
		}
		public String[] getTaxDeductions() {
			return taxDeductions;
		}
		public void setTaxDeductions(String[] taxDeductions) {
			this.taxDeductions = taxDeductions;
		}
		
		
}
