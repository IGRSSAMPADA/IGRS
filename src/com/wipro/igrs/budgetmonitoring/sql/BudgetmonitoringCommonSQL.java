/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   BudgetmonitoringCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for Budget Monitoring.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  14th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.budgetmonitoring.sql;

public class BudgetmonitoringCommonSQL {
	public static final String SELECT_DISRICT_LIST = "SELECT DISTRICT_ID,DISTRICT_NAME FROM IGRS_DISTRICT_MASTER";
	public static final String SELECT_DRO_LIST = "SELECT OFFICE_TYPE_ID FROM IGRS_OFFICE_MASTER";
	public static final String SELECT_FINANCIALYEAR = "SELECT FY.FISCAL_YEAR_ID,FY.FISCAL_YEAR FROM IGRS_FISCAL_YEAR FY INNER JOIN IGRS_EXP_MASTER_DETAILS EXP ON FY.FISCAL_YEAR_ID = EXP.FISCAL_YEAR_ID WHERE EXP.OFFICE_TYPE_ID=";
	public static final String SELECT_FDATE_AND_TODATE = "SELECT AMOUNT_ALLOCATED,AMOUNT_BALANCE,AMOUNT_REQUESTED FROM IGRS_EXP_MASTER_DETAILS WHERE FISCAL_YEAR_ID=? AND to_date(TO_CHAR(CREATED_DATE,'dd/mm/yyyy'),'dd-mm-yy')BETWEEN to_date(?, 'dd-mm-yy') AND to_date(?, 'dd-mm-yy')";
}
