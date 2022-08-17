
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
 * FILE NAME: RegistrationCompletionDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DAO FOR REGISTRATION COMPLETION ACTION.
 */

package com.wipro.igrs.RegistrationCompletion.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.RegistrationCompletion.sql.RegistrationCompletionSQL;
//import com.wipro.igrs.caseMonitoring.dao.ViewCaseDAO;
import com.wipro.igrs.db.DBUtility;


/**
 * @author NIHAR M.
 *
 */
public class RegistrationCompletionDAO {

	DBUtility dbUtil;
	private Logger logger = (Logger) Logger	.getLogger(RegistrationCompletionDAO.class);

	public RegistrationCompletionDAO() throws Exception{
		dbUtil = new DBUtility();
	}

	/**
	 * @param param
	 * @return ArrayList
	 */
	public ArrayList getTxnIDDetails(String[] param) throws Exception{

		logger.debug("Called:  DAO:: getTxnIDDetails()");
		ArrayList list = null;
		try {
			logger.debug("RegistrationCompletionDAO:: getTxnIDDetails:: BEFORE SQL ");
			dbUtil.createPreparedStatement(RegistrationCompletionSQL.GET_TXN_ID_DETAILS);
			logger.debug("RegistrationCompletionDAO:: SQL:" + RegistrationCompletionSQL.GET_TXN_ID_DETAILS);
			list = dbUtil.executeQuery(param);
			logger.debug("RegistrationCompletionDAO:: getTxnIDDetails:: AFTER SQL ");
		} catch (Exception x) {
			logger.debug("Exception in getTxnIDDetails() :- " + x);
		}
		return list;
	}


	/**
	 * @param param
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getIndRegIDDetails(String[] param) throws Exception{

		logger.debug("DAO:: Called: getIndRegIDDetails");
		ArrayList list = null;
		try {
			logger.debug("RegistrationCompletionDAO:: getIndRegIDDetails:: BEFORE SQL ");
			dbUtil.createPreparedStatement(RegistrationCompletionSQL.INDIVIDUAL_REG_ID_DETAILS);
			logger.debug("RegistrationCompletionDAO:: SQL:" + RegistrationCompletionSQL.INDIVIDUAL_REG_ID_DETAILS);
			list = dbUtil.executeQuery(param);
			logger.debug("RegistrationCompletionDAO:: getIndRegIDDetails:: AFTER SQL ");
		} catch (Exception x) {
			logger.debug("Exception in getIndRegIDDetails() :- " + x);
		}
		return list;
	}


	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getStatusList() throws Exception {
		ArrayList list;
		dbUtil.createStatement();
		list = dbUtil.executeQuery("Select reg_status_id, reg_status_name from"
				+ " IGRS_REG_COMP_STATUS_MASTER");

	//	LoggerMsg.debug("listttttttttt:----------  "+list);
		return list;

	}

	/**
	 * @param p1
	 * @param p2
	 * @return boolean
	 */
	public boolean submitUpdatedDetails(String[] p1, String[] p2){

		logger.debug("Called: DAO:: submitUpdatedDetails()");
		boolean docsInserted = false;
		try {
			logger.debug("BEFORE ");
			dbUtil.createPreparedStatement(RegistrationCompletionSQL.UPDATE_REG_TXN_DETAILS);
			logger.debug("submitUpdatedDetails: Query:- "+RegistrationCompletionSQL.UPDATE_REG_TXN_DETAILS);
			docsInserted = dbUtil.executeUpdate(p1);
			logger.debug("AFTER ");
		} catch (Exception x) {
			logger.debug("Exception in submitUpdatedDetails() :-   " + x);
			x.printStackTrace();
		}

		if(docsInserted){
			logger.debug("DAO:: submitUpdatedDetails:: - docsInserted:-  "+docsInserted);
			try {
				dbUtil.commit();
				boolean docInserted = insertTxnDetails(p2);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return docsInserted;
	}



	/**
	 * @param p2
	 * @return boolean
	 */
	public boolean insertTxnDetails(String[] p2){

		logger.debug("Called: DAO:: insertTxnDetails()");
		boolean docsInserted = false;

		try {
			logger.debug("BEFORE ");
			dbUtil.createPreparedStatement(RegistrationCompletionSQL.INSERT_REG_DETAILS);
			logger.debug("insertTxnDetails: Query:- "+RegistrationCompletionSQL.INSERT_REG_DETAILS);
			docsInserted = dbUtil.executeUpdate(p2);
			logger.debug("AFTER ");
		} catch (Exception x) {
			logger.debug("Exception in insertTxnDetails() :-   " + x);
			x.printStackTrace();
		}
		if(docsInserted){
			logger.debug("DAO:: submitUpdatedDetails:: - docsInserted:-  "+docsInserted);
			try {
				dbUtil.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return docsInserted;
	}
}
