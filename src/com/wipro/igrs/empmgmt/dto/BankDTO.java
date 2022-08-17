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
* BankDTO.java <br>
* BankDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class BankDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5694675423577065240L;

	private String employee_id;

	private String nameAsInBank;

	private String panNo;

	private String bankAccountNo;

	private String bankName;

	private String bankBranch;

	private String bankAddress;

	private String fundTypeId;

	private String fundTypeName;

	private ArrayList fundList;

	private ArrayList childFundList;
	
	private String bankNameLabel;
	
	private String bankIFSC;

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
	 * @return the nameAsInBank
	 */
	public String getNameAsInBank() {
		return nameAsInBank;
	}

	/**
	 * @param nameAsInBank
	 *            the nameAsInBank to set
	 */
	public void setNameAsInBank(String nameAsInBank) {
		this.nameAsInBank = nameAsInBank;
	}

	/**
	 * @return the panNo
	 */
	public String getPanNo() {
		return panNo;
	}

	/**
	 * @param panNo
	 *            the panNo to set
	 */
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	/**
	 * @return the bankAccountNo
	 */
	public String getBankAccountNo() {
		return bankAccountNo;
	}

	/**
	 * @param bankAccountNo
	 *            the bankAccountNo to set
	 */
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankBranch
	 */
	public String getBankBranch() {
		return bankBranch;
	}

	/**
	 * @param bankBranch
	 *            the bankBranch to set
	 */
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	/**
	 * @return the bankAddress
	 */
	public String getBankAddress() {
		return bankAddress;
	}

	/**
	 * @param bankAddress
	 *            the bankAddress to set
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	/**
	 * @return the fundTypeId
	 */
	public String getFundTypeId() {
		return fundTypeId;
	}

	/**
	 * @param fundTypeId
	 *            the fundTypeId to set
	 */
	public void setFundTypeId(String fundTypeId) {
		this.fundTypeId = fundTypeId;
	}

	/**
	 * @return the fundTypeName
	 */
	public String getFundTypeName() {
		return fundTypeName;
	}

	/**
	 * @param fundTypeName
	 *            the fundTypeName to set
	 */
	public void setFundTypeName(String fundTypeName) {
		this.fundTypeName = fundTypeName;
	}

	/**
	 * @return the fundList
	 */
	public ArrayList getFundList() {
		return fundList;
	}

	/**
	 * @param fundList
	 *            the fundList to set
	 */
	public void setFundList(ArrayList fundList) {
		this.fundList = fundList;
	}

	/**
	 * @return the childFundList
	 */
	public ArrayList getChildFundList() {
		return childFundList;
	}

	/**
	 * @param childFundList
	 *            the childFundList to set
	 */
	public void setChildFundList(ArrayList childFundList) {
		this.childFundList = childFundList;
	}

	/**
	 * @return the bankNameLabel
	 */
	public String getBankNameLabel() {
		return bankNameLabel;
	}

	/**
	 * @param bankNameLabel the bankNameLabel to set
	 */
	public void setBankNameLabel(String bankNameLabel) {
		this.bankNameLabel = bankNameLabel;
	}

	/**
	 * @return the bankIFSC
	 */
	public String getBankIFSC() {
		return bankIFSC;
	}

	/**
	 * @param bankIFSC the bankIFSC to set
	 */
	public void setBankIFSC(String bankIFSC) {
		this.bankIFSC = bankIFSC;
	}

}