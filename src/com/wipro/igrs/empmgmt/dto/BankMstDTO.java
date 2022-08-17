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
* BankMstDTO.java <br>
* BankMstDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class BankMstDTO implements Serializable {

	private static final long serialVersionUID = -7851117212610222286L;
	private String bankID;
	private String bankName;
	private String bankPhone;
	private String bankAddress;

	/**
	 * @return the bankID
	 */
	public String getBankID() {
		return bankID;
	}

	/**
	 * @param bankID
	 *            the bankID to set
	 */
	public void setBankID(String bankID) {
		this.bankID = bankID;
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
	 * @return the bankPhone
	 */
	public String getBankPhone() {
		return bankPhone;
	}

	/**
	 * @param bankPhone
	 *            the bankPhone to set
	 */
	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
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

}
