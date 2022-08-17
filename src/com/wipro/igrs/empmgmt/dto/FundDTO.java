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
* FundDTO.java <br>
* FundDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FundDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7857944254733178061L;

	private String employee_id;

	private String type;
	
	private String fundName;

	private String accountNo;

	private String accountLocation;
	
	private ArrayList nomineeList;//nomineeList
	
	private boolean fromDB;

	/**
	 * @return the employee_id
	 */
	public String getEmployee_id() {
		return employee_id;
	}

	/**
	 * @param employee_id
	 *            the employee_id to set
	 */
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo
	 *            the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the accountLocation
	 */
	public String getAccountLocation() {
		return accountLocation;
	}

	/**
	 * @param accountLocation
	 *            the accountLocation to set
	 */
	public void setAccountLocation(String accountLocation) {
		this.accountLocation = accountLocation;
	}

	/**
	 * @return the nomineeList
	 */
	public ArrayList getNomineeList() {
		return nomineeList;
	}

	/**
	 * @param nomineeList
	 *            the nomineeList to set
	 */
	public void setNomineeList(ArrayList nomineeList) {
		this.nomineeList = nomineeList;
	}

	/**
	 * @param index
	 * @param value
	 */
	public void setNomineeArrDTO(int index, NomineeDTO value) {

		for (; index >= nomineeList.size(); nomineeList.add(new NomineeDTO()))
			;
		nomineeList.add(index, value);
	}

	/**
	 * @param index
	 * @return
	 */
	public NomineeDTO getNomineeArrDTO(int index) {
		for (; index >= nomineeList.size(); nomineeList.add(new NomineeDTO()))
			;
		return (NomineeDTO) nomineeList.get(index);
	}

	/**
	 * @return the fromDB
	 */
	public boolean isFromDB() {
		return fromDB;
	}

	/**
	 * @param fromDB the fromDB to set
	 */
	public void setFromDB(boolean fromDB) {
		this.fromDB = fromDB;
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

	
}