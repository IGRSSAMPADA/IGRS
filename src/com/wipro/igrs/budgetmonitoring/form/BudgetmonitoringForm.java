/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   BudgetmonitoringForm.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the formBean Class for Budget Monitoring.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  14th April, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.budgetmonitoring.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.budgetmonitoring.dto.BudgetmonitoringDTO;

public class BudgetmonitoringForm extends ActionForm {
	public BudgetmonitoringForm() {
	}

	private String districtId;
	private String financialId;
	private String droId;
	private String timeofperiod;
	private String fromDate;
	private String toDate;
	private String allocatedbudget;
	private String amountbalance;
	private String amountrequested;
	BudgetmonitoringDTO dto = new BudgetmonitoringDTO();

	public String getFinancialId() {
		return financialId;
	}

	public void setFinancialId(String financialId) {
		this.financialId = financialId;
	}

	public String getTimeofperiod() {
		return timeofperiod;
	}

	public void setTimeofperiod(String timeofperiod) {
		this.timeofperiod = timeofperiod;
	}

	public BudgetmonitoringDTO getDto() {
		return dto;
	}

	public void setDto(BudgetmonitoringDTO dto) {
		this.dto = dto;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDroId() {
		return droId;
	}

	public void setDroId(String droId) {
		this.droId = droId;
	}

	public String getAllocatedbudget() {
		return allocatedbudget;
	}

	public void setAllocatedbudget(String allocatedbudget) {
		this.allocatedbudget = allocatedbudget;
	}

	public String getAmountbalance() {
		return amountbalance;
	}

	public void setAmountbalance(String amountbalance) {
		this.amountbalance = amountbalance;
	}

	public String getAmountrequested() {
		return amountrequested;
	}

	public void setAmountrequested(String amountrequested) {
		this.amountrequested = amountrequested;
	}

}