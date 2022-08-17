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
* FamilyMemberDTO.java <br>
* FamilyMemberDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class FamilyMemberDTO implements Serializable {

	private static final long serialVersionUID = 5564687084825066935L;
	private String relativeRowID;
	private String relativeTypeID;
	private String relativeTypeLabel;
	private String relativeName;
	private String relativeDay;
	private String relativeMonth;
	private String relativeYear;
	private String relativeDOB;
	private String relativeRadio;
	private String employeeID;

	/**
	 * @return the relativeTypeID
	 */
	public String getRelativeTypeID() {
		return relativeTypeID;
	}

	/**
	 * @param relativeTypeID
	 *            the relativeTypeID to set
	 */
	public void setRelativeTypeID(String relativeTypeID) {
		this.relativeTypeID = relativeTypeID;
	}

	/**
	 * @return the relativeTypeLabel
	 */
	public String getRelativeTypeLabel() {
		return relativeTypeLabel;
	}

	/**
	 * @param relativeTypeLabel
	 *            the relativeTypeLabel to set
	 */
	public void setRelativeTypeLabel(String relativeTypeLabel) {
		this.relativeTypeLabel = relativeTypeLabel;
	}

	/**
	 * @return the relativeName
	 */
	public String getRelativeName() {
		return relativeName;
	}

	/**
	 * @param relativeName
	 *            the relativeName to set
	 */
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}

	/**
	 * @return the relativeDay
	 */
	public String getRelativeDay() {
		return relativeDay;
	}

	/**
	 * @param relativeDay
	 *            the relativeDay to set
	 */
	public void setRelativeDay(String relativeDay) {
		this.relativeDay = relativeDay;
	}

	/**
	 * @return the relativeMonth
	 */
	public String getRelativeMonth() {
		return relativeMonth;
	}

	/**
	 * @param relativeMonth
	 *            the relativeMonth to set
	 */
	public void setRelativeMonth(String relativeMonth) {
		this.relativeMonth = relativeMonth;
	}

	/**
	 * @return the relativeYear
	 */
	public String getRelativeYear() {
		return relativeYear;
	}

	/**
	 * @param relativeYear
	 *            the relativeYear to set
	 */
	public void setRelativeYear(String relativeYear) {
		this.relativeYear = relativeYear;
	}

	/**
	 * @return the relativeDOB
	 */
	public String getRelativeDOB() {
		return relativeDOB;
	}

	/**
	 * @param relativeDOB
	 *            the relativeDOB to set
	 */
	public void setRelativeDOB(String relativeDOB) {
		this.relativeDOB = relativeDOB;
	}

	/**
	 * @return the relativeRadio
	 */
	public String getRelativeRadio() {
		return relativeRadio;
	}

	/**
	 * @param relativeRadio
	 *            the relativeRadio to set
	 */
	public void setRelativeRadio(String relativeRadio) {
		this.relativeRadio = relativeRadio;
	}

	/**
	 * @return the employeeID
	 */
	public String getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID
	 *            the employeeID to set
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return the relativeRowID
	 */
	public String getRelativeRowID() {
		return relativeRowID;
	}

	/**
	 * @param relativeRowID the relativeRowID to set
	 */
	public void setRelativeRowID(String relativeRowID) {
		this.relativeRowID = relativeRowID;
	}
}
