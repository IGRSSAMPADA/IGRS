package com.wipro.igrs.conReport.dto;


/**
 * ===========================================================================
 * File           :   ConReportDTO.java
 * Description    :   Represents the DTO Class ECR
 * @author        :   Imran Shaik
 * Created Date   :   September 08, 2008
 * ===========================================================================
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Imran Shaik
 *
 */


public class ConReportDTO implements Serializable{

	private String empId;
	private String conId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dateOfJoin;
	private String dateOfBirth;
	private String gender;
	private String fiYearId;
	private String comments;
	private String repStatus;
	ArrayList fiYear;

	
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
	 * @return the conId
	 */
	public String getConId() {
		return conId;
	}
	/**
	 * @param conId the conId to set
	 */
	public void setConId(String conId) {
		this.conId = conId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the dateOfJoin
	 */
	public String getDateOfJoin() {
		return dateOfJoin;
	}
	/**
	 * @param dateOfJoin the dateOfJoin to set
	 */
	public void setDateOfJoin(String dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the fiYear
	 */
	public ArrayList getFiYear() {
		return fiYear;
	}
	/**
	 * @param fiYear the fiYear to set
	 */
	public void setFiYear(ArrayList fiYear) {
		this.fiYear = fiYear;
	}
	/**
	 * @return the fiYearId
	 */
	public String getFiYearId() {
		return fiYearId;
	}
	/**
	 * @param fiYearId the fiYearId to set
	 */
	public void setFiYearId(String fiYearId) {
		this.fiYearId = fiYearId;
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
	 * @return the repStatus
	 */
	public String getRepStatus() {
		return repStatus;
	}
	/**
	 * @param repStatus the repStatus to set
	 */
	public void setRepStatus(String repStatus) {
		this.repStatus = repStatus;
	}

	
}
