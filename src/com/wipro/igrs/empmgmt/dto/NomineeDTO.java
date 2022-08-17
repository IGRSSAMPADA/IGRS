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
* NomineeDTO.java <br>
* NomineeDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ })
public class NomineeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3322712154083645982L;

	private String	employee_id;

	private String	fundTypeId;

	private String	nomineeName;

	private String	nomineeAddress;

	private String	relationWithNominee;

	private String	nomineeAge;

	private String	fundName;

	private String	fundIndex;
	
	private String strAccountNumber;
	
	private String selected;

	private String	nomineePercentage;
	/**
	 * @return the strAccountNumber
	 */
	public String getStrAccountNumber() {
		return strAccountNumber;
	}

	/**
	 * @param strAccountNumber the strAccountNumber to set
	 */
	public void setStrAccountNumber(String strAccountNumber) {
		this.strAccountNumber = strAccountNumber;
	}

	/**
	 * @return the employee_id
	 */
	public String getEmployee_id() {
		return employee_id;
	}

	/**
	 * @param employee_id the employee_id to set
	 */
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	/**
	 * @return the fundTypeId
	 */
	public String getFundTypeId() {
		return fundTypeId;
	}

	/**
	 * @param fundTypeId the fundTypeId to set
	 */
	public void setFundTypeId(String fundTypeId) {
		this.fundTypeId = fundTypeId;
	}

	/**
	 * @return the nomineeName
	 */
	public String getNomineeName() {
		return nomineeName;
	}

	/**
	 * @param nomineeName the nomineeName to set
	 */
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	/**
	 * @return the nomineeAddress
	 */
	public String getNomineeAddress() {
		return nomineeAddress;
	}

	/**
	 * @param nomineeAddress the nomineeAddress to set
	 */
	public void setNomineeAddress(String nomineeAddress) {
		this.nomineeAddress = nomineeAddress;
	}

	/**
	 * @return the relationWithNominee
	 */
	public String getRelationWithNominee() {
		return relationWithNominee;
	}

	/**
	 * @param relationWithNominee the relationWithNominee to set
	 */
	public void setRelationWithNominee(String relationWithNominee) {
		this.relationWithNominee = relationWithNominee;
	}

	/**
	 * @return the nomineeAge
	 */
	public String getNomineeAge() {
		return nomineeAge;
	}

	/**
	 * @param nomineeAge the nomineeAge to set
	 */
	public void setNomineeAge(String nomineeAge) {
		this.nomineeAge = nomineeAge;
	}

	/**
	 * @return the fundName
	 */
	public String getFundName() {
		return fundName;
	}

	/**
	 * @param fundName the fundName to set
	 */
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	/**
	 * @return the fundIndex
	 */
	public String getFundIndex() {
		return fundIndex;
	}

	/**
	 * @param fundIndex the fundIndex to set
	 */
	public void setFundIndex(String fundIndex) {
		this.fundIndex = fundIndex;
	}

	/**
	 * @return the selected
	 */
	public String getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}

	/**
	 * @return the nomineePercentage
	 */
	public String getNomineePercentage() {
		return nomineePercentage;
	}

	/**
	 * @param nomineePercentage the nomineePercentage to set
	 */
	public void setNomineePercentage(String nomineePercentage) {
		this.nomineePercentage = nomineePercentage;
	}

	
}