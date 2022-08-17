/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   BudgetmonitoringDTO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DTO Class for Budget Monitoring.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  14th April, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.budgetmonitoring.dto;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.budgetmonitoring.bd.BudgetmonitoringBD;

public class BudgetmonitoringDTO {
	private Logger logger = (Logger) Logger.getLogger(BudgetmonitoringBD.class);

	public BudgetmonitoringDTO() {
		logger.info("we are in BudgetmonitoringDTO");

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
	private String name;
	private String value;
	ArrayList districtList;
	ArrayList financialList;
	ArrayList droList;
	ArrayList budgetmonitoringList;

	public ArrayList getBudgetmonitoringList() {
		return budgetmonitoringList;
	}

	public void setBudgetmonitoringList(
			ArrayList budgetmonitoringList) {
		this.budgetmonitoringList = budgetmonitoringList;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ArrayList getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public ArrayList getFinancialList() {
		return financialList;
	}

	public void setFinancialList(ArrayList financialList) {
		this.financialList = financialList;
	}

	public String getDroId() {
		return droId;
	}

	public void setDroId(String droId) {
		this.droId = droId;
	}

	public ArrayList getDroList() {
		return droList;
	}

	public void setDroList(ArrayList droList) {
		this.droList = droList;
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