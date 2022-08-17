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
* ChildDetailsDTO.java <br>
* ChildDetailsDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
public class ChildDetailsDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4616662316536205775L;
	private String childName;
	private String childGender;
	private String childDay;
	private String childMonth;
	private String childYear;
	private String childDateOfBirth;
	
	/**
	 * @return the childDateOfBirth
	 */
	public String getChildDateOfBirth() {
		return childDateOfBirth;
	}
	/**
	 * @param childDateOfBirth the childDateOfBirth to set
	 */
	public void setChildDateOfBirth(String childDateOfBirth) {
		this.childDateOfBirth = childDateOfBirth;
	}
	//--setters & getters
	public String getChildDay() {
		return childDay;
	}
	public void setChildDay(String childDay) {
		this.childDay = childDay;
	}
	
	public String getChildMonth() {
		return childMonth;
	}
	public void setChildMonth(String childMonth) {
		this.childMonth = childMonth;
	}
	
	public String getChildGender() {
		return childGender;
	}
	public void setChildGender(String childGender) {
		this.childGender = childGender;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getChildYear() {
		return childYear;
	}
	public void setChildYear(String childYear) {
		this.childYear = childYear;
	}
	
	
}