

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
 * FILE NAME: CreateHolidayDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 8th AUGUST 2008
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DAO FOR HOLIDAY CREATE.
 */


package com.wipro.igrs.holidaylist.dao;

import org.apache.log4j.Logger;

import com.wipro.igrs.holidaylist.sql.HolidayListSQL;
import com.wipro.igrs.db.DBUtility;

/**
 * @author NIHAR M.
 *
 */
public class CreateHolidayDAO {

	private Logger logger = (Logger) Logger.getLogger(CreateHolidayDAO.class);
	DBUtility dbUtil;


	/**
	 * @throws Exception
	 */
	public CreateHolidayDAO() throws Exception{
		dbUtil = new DBUtility();
	}


	/**
	 * @param formDTO
	 * @return boolean
	 */
	public boolean createNewHoliday(String[] param) throws Exception {
		logger.debug("Called: DAO:: createNewHoliday()");
		boolean docsInserted = false;
		try {
			logger.debug("BEFORE ");
			dbUtil.createPreparedStatement(HolidayListSQL.CREATE_NEW_HOLIDAY);
			logger.debug("Query:- "+HolidayListSQL.CREATE_NEW_HOLIDAY);
			docsInserted = dbUtil.executeUpdate(param);
			logger.debug("AFTER ");
		} catch (Exception x) {
			logger.debug("Exception in createNewHoliday() :-   " + x);
			x.printStackTrace();
		}

		if(docsInserted){
			logger.debug("DAO:: createNewHoliday:: - docsInserted:-  "+docsInserted);
			try {
				dbUtil.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return docsInserted;

	}

}
