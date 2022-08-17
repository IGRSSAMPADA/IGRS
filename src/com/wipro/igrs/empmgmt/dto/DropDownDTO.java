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
* DropDownDTO.java <br>
* DropDownDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class DropDownDTO implements Serializable {

	private static final long serialVersionUID = 1407629712768940448L;
	private String optionValue;
	private String optionLabel;
	private String locale;
	
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the optionValue
	 */
	public String getOptionValue() {
		return optionValue;
	}

	/**
	 * @param optionValue
	 *            the optionValue to set
	 */
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	/**
	 * @return the optionLabel
	 */
	public String getOptionLabel() {
		return optionLabel;
	}

	/**
	 * @param optionLabel
	 *            the optionLabel to set
	 */
	public void setOptionLabel(String optionLabel) {
		this.optionLabel = optionLabel;
	}

}
