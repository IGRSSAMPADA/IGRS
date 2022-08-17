
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
 * FILE NAME: ViewHolidayDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 8th AUGUST 2008
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DAO FOR HOLIDAY VIEW.
 */


package com.wipro.igrs.holidaylist.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.holidaylist.dto.HolidayListDTO;
import com.wipro.igrs.holidaylist.sql.HolidayListSQL;

/**
 * @author NIHAR M.
 *
 */
public class ViewHolidayDAO {


	private Logger logger = (Logger) Logger.getLogger(ViewHolidayDAO.class);
	DBUtility dbUtil;


	/**
	 * @throws Exception
	 */
	public ViewHolidayDAO() throws Exception{
		dbUtil = new DBUtility();
	}



	/**
	 * @return ArrayList
	 */
	public HashMap getAllHolidayList() throws Exception{

		HolidayListDTO  holDTO ;
		ArrayList holidayList = new ArrayList();
		ArrayList list1 = new ArrayList();
		ArrayList list2 = new ArrayList();

		HashMap hMap = new HashMap();

		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list2 = dbUtil.executeQuery(HolidayListSQL.GET_HOLIDAY_LIST);
			logger.debug(HolidayListSQL.GET_HOLIDAY_LIST);
			for (int i = 0; i < list2.size(); i++) {
				list1 = (ArrayList)list2.get(i);
				holDTO = new HolidayListDTO();

				holDTO.setHolidayId((String)list1.get(0));
				holDTO.setDate((String)list1.get(1));
				holDTO.setHolidayDescription((String)list1.get(2));

				if("P".equalsIgnoreCase((String)list1.get(3))){
					holDTO.setHolidayStatus("Pending");
				}
				if("A".equalsIgnoreCase((String)list1.get(3))){
					holDTO.setHolidayStatus("Approved");
				}

				holDTO.setHolidayYear((String)list1.get(11));
				System.out.println("year:-   "+(String)list1.get(11));
				System.out.println("date:-   "+(String)list1.get(1));
				hMap.put((String)list1.get(0), holDTO);
			}
		} catch (Exception x) {
			x.printStackTrace();
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {

				logger.debug(ex);
			}
		}
		return hMap;
	}




	/**
	 * @param holidayId
	 * @return boolean
	 */
	public boolean removeHoliday(String[] holidayId){

		logger.debug("Called: DAO:: removeHoliday()");
		boolean removal = false;
		try {
			logger.debug("BEFORE ");
			dbUtil.createPreparedStatement(HolidayListSQL.REMOVE_HOLIDAY);
			logger.debug("Query:- "+HolidayListSQL.REMOVE_HOLIDAY);

			if(holidayId != null){
				for (int i= 0; i<holidayId.length; i++){
					String[] param = new String[1];
					param[0] = holidayId[i];
					removal = dbUtil.executeUpdate(param);
					if(!removal) {
						break;
					}
				}
			}
			if(removal) {
				dbUtil.commit();
			}else {
				dbUtil.rollback();
			}
			logger.debug("AFTER ");
		} catch (Exception x) {
			logger.debug("Exception in removeHoliday() :-   " + x);
			removal = false;
			try {
				dbUtil.rollback();
			}catch(Exception v) {
				logger.debug(v);
			}
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
		return removal;
	}



	/**
	 * @param param
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getIndHolidayDetails(String[] param) throws Exception{

		ArrayList list = null;
		try {
			dbUtil.createPreparedStatement(HolidayListSQL.GET_IND_HOLIDAY_DETAILS);
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			x.printStackTrace();
		}
		return list;
	}



	/**
	 * @param param
	 * @return boolean
	 */
	public boolean updateHolidayDetails(String[] param) throws Exception {
		logger.debug("Called: DAO:: updateHolidayDetails()");
		boolean updated = false;
		try {
			logger.debug("BEFORE ");
			dbUtil.createPreparedStatement(HolidayListSQL.UPDATE_HOLIDAY_DETAILS);
			logger.debug("Query:- "+HolidayListSQL.UPDATE_HOLIDAY_DETAILS);

			updated = dbUtil.executeUpdate(param);
			if(updated) {
				dbUtil.commit();
			}else {
				dbUtil.rollback();
			}
			logger.debug("AFTER ");
		} catch (Exception x) {
			logger.debug("Exception in updateHolidayDetails() :-   " + x);
			updated = false;
			try {
				dbUtil.rollback();
			}catch(Exception v) {
				logger.debug(v);
			}
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
		return updated;
	}
}
