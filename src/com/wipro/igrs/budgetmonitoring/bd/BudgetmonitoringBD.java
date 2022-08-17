/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   BudgetmonitoringBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for Budget Monitoring.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  14th April, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.budgetmonitoring.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.budgetmonitoring.dao.BudgetmonitoringDAO;
import com.wipro.igrs.budgetmonitoring.dto.BudgetmonitoringDTO;

public class BudgetmonitoringBD {
	private Logger logger = (Logger) Logger
	.getLogger(BudgetmonitoringBD.class);
	public BudgetmonitoringBD() {
		logger.info("we are in BudgetmonitoringBD+++++++++++++==========");
	}

	BudgetmonitoringDAO budgetdao = new BudgetmonitoringDAO();

	public ArrayList getDistrictList() throws Exception {
		return budgetdao.getDistrictList();
	}

	public ArrayList getFinancialList(String droid)
			throws Exception {
		return budgetdao.getFinancialList(droid);
	}

	public ArrayList getDroList() throws Exception {
		return budgetdao.getDroList();
	}

	public ArrayList getBudgetmonitoringList(String fyear,
			String fdate, String tdate) throws Exception {
		return budgetdao.getBudgetmonitoringList(fyear, fdate, tdate);
	}
}
