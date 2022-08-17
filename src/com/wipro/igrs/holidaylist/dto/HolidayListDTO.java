
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
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
 */ 

/* 
 * FILE NAME: HolidayListDTO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 8th AUGUST 2008
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DTO FOR HOLIDAY LIST.
 */

package com.wipro.igrs.holidaylist.dto;


/**
 * @author NIHAR M.
 *
 */
public class HolidayListDTO {

	private String holidayDescription;
	private String financialYear;
	private String date;
	private String holidayCreateForm;
	private String holidayViewForm;
	private String holidayUpdateForm;
	
	private String formName;
	private String actionName;
	
	private String holidayValue;
	private String hdnholiName;

	private String indHolidayDetails;
	private String holidayId;
	private String holidayStatus;
	private String holidayYear;
	
	//EXTRA - REMOVE
	private String name;
	private String pass;
	
	
	private String holCheckValue;
	
	public String getHolCheckValue() {
		return holCheckValue;
	}

	public void setHolCheckValue(String holCheckValue) {
		this.holCheckValue = holCheckValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getHolidayCreateForm() {
		return holidayCreateForm;
	}

	public void setHolidayCreateForm(String holidayCreateForm) {
		this.holidayCreateForm = holidayCreateForm;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getHolidayDescription() {
		return holidayDescription;
	}

	public void setHolidayDescription(String holidayDescription) {
		this.holidayDescription = holidayDescription;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHolidayValue() {
		return holidayValue;
	}

	public void setHolidayValue(String holidayValue) {
		this.holidayValue = holidayValue;
	}

	public String getHolidayViewForm() {
		return holidayViewForm;
	}

	public void setHolidayViewForm(String holidayViewForm) {
		this.holidayViewForm = holidayViewForm;
	}

	public String getHdnholiName() {
		return hdnholiName;
	}

	public void setHdnholiName(String hdnholiName) {
		this.hdnholiName = hdnholiName;
	}

	public String getIndHolidayDetails() {
		return indHolidayDetails;
	}

	public void setIndHolidayDetails(String indHolidayDetails) {
		this.indHolidayDetails = indHolidayDetails;
	}

	public String getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(String holidayId) {
		this.holidayId = holidayId;
	}

	public String getHolidayStatus() {
		return holidayStatus;
	}

	public void setHolidayStatus(String holidayStatus) {
		this.holidayStatus = holidayStatus;
	}

	public String getHolidayYear() {
		return holidayYear;
	}

	public void setHolidayYear(String holidayYear) {
		this.holidayYear = holidayYear;
	}

	public String getHolidayUpdateForm() {
		return holidayUpdateForm;
	}

	public void setHolidayUpdateForm(String holidayUpdateForm) {
		this.holidayUpdateForm = holidayUpdateForm;
	}

}
