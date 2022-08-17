/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   BudgetmonitoringDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for Budget Monitoring.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  14th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.budgetmonitoring.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.budgetmonitoring.bd.BudgetmonitoringBD;
import com.wipro.igrs.budgetmonitoring.dto.BudgetmonitoringDTO;
import com.wipro.igrs.budgetmonitoring.sql.BudgetmonitoringCommonSQL;
import com.wipro.igrs.db.DBUtility;

public class BudgetmonitoringDAO {

	private Logger logger = (Logger) Logger.getLogger(BudgetmonitoringBD.class);
	private ArrayList districtList = new ArrayList();
	private ArrayList financialList = new ArrayList();
	private ArrayList droList = new ArrayList();
	private ArrayList budgetmonitoringList = new ArrayList();

	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	BudgetmonitoringDTO dto = null;

	public BudgetmonitoringDAO() {

		try {
			logger.info("we are in BudgetmonitoringDAO+++++++++++==========");
			
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public ArrayList getDistrictList() throws Exception {
		try {
			sql = BudgetmonitoringCommonSQL.SELECT_DISRICT_LIST;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			dto = new BudgetmonitoringDTO();
			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {

				subList = (ArrayList) mainList1.get(i);
				dto = new BudgetmonitoringDTO();
				dto.setName(subList.get(1).toString());
				dto.setValue(subList.get(0).toString());
				districtList.add(dto);
				// dbUtility.commit();
			}
		} catch (Exception e) {
			e.getStackTrace();
			dbUtility.rollback();
		}finally {
			dbUtility.closeConnection();
		}
		return districtList;

	}

	public ArrayList getFinancialList(String droid)
			throws Exception {
		try {
			sql = BudgetmonitoringCommonSQL.SELECT_FINANCIALYEAR + "'" + droid
					+ "'";
		    dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new BudgetmonitoringDTO();
				dto.setName(subList.get(1).toString());
				dto.setValue(subList.get(0).toString());
				financialList.add(dto);
				// dbUtility.commit();
			}
		} catch (Exception e) {
			e.getStackTrace();
			//dbUtility.rollback();
		}finally {
			dbUtility.closeConnection();
		}
		return financialList;

	}

	public ArrayList getDroList() throws Exception {
		try {
			sql = BudgetmonitoringCommonSQL.SELECT_DRO_LIST;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new BudgetmonitoringDTO();
				dto.setName(subList.get(0).toString());
				dto.setValue(subList.get(0).toString());
				droList.add(dto);

			}
		} catch (Exception e) {
			e.getStackTrace();
			//dbUtility.rollback();
		}finally {
			dbUtility.closeConnection();
		}
		return droList;

	}

	public ArrayList getBudgetmonitoringList(String fyear,
			String fdate, String tdate) throws Exception {
		try {
			dbUtility = new DBUtility();
			sql = BudgetmonitoringCommonSQL.SELECT_FDATE_AND_TODATE;
			dbUtility.createPreparedStatement(sql);
			String bm[] = new String[3];
			bm[0] = fyear;
			bm[1] = fdate;
			bm[2] = tdate;

			ArrayList mainList1 = dbUtility.executeQuery(bm);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new BudgetmonitoringDTO();
				dto.setAllocatedbudget(subList.get(0).toString());
				dto.setAmountbalance(subList.get(1).toString());

				dto.setAmountrequested(subList.get(2).toString());

				budgetmonitoringList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			dbUtility.closeConnection();
		}
		return budgetmonitoringList;
	}

}
