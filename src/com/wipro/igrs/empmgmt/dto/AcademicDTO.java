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
import java.util.ArrayList;
/**
* 
* AcademicDTO.java <br>
* AcademicDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class AcademicDTO implements Serializable
	{
		/**
	 * 
	 */
	private static final long serialVersionUID = -2083530324535574188L;
		//acadamic qualification
		//private String chkQuali[];
	    private String employeeid;
		private String degreesID; //new field added after db changed.
		private String degree;
		private String stream;
		private String passingYear;
		private String grade;
		private String createdBy;
		private String createdDate;
		private String talentForm;
		
		private String qualificainId;
		private String qualificainName;
		 ArrayList qualificationList;
		public String getTalentForm() {
			return talentForm;
		}
		public void setTalentForm(String talentForm) {
			this.talentForm = talentForm;
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
		public String getDegree() {
			return degree;
		}
		public void setDegree(String degree) {
			this.degree = degree;
		}
		public String getDegreesID() {
			return degreesID;
		}
		public void setDegreesID(String degreesID) {
			this.degreesID = degreesID;
		}
	
		public String getGrade() {
			return grade;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
		public String getPassingYear() {
			return passingYear;
		}
		public void setPassingYear(String passingYear) {
			this.passingYear = passingYear;
		}
		public String getStream() {
			return stream;
		}
		public void setStream(String stream) {
			this.stream = stream;
		}
		public String getQualificainId() {
			return qualificainId;
		}
		public void setQualificainId(String qualificainId) {
			this.qualificainId = qualificainId;
		}
		public String getQualificainName() {
			return qualificainName;
		}
		public void setQualificainName(String qualificainName) {
			this.qualificainName = qualificainName;
		}
		public ArrayList getQualificationList() {
			return qualificationList;
		}
		public void setQualificationList(ArrayList qualificationList) {
			this.qualificationList = qualificationList;
		}
		public String getEmployeeid() {
			return employeeid;
		}
		public void setEmployeeid(String employeeid) {
			this.employeeid = employeeid;
		}
		
		
		
	}