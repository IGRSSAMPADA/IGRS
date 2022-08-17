/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceParamMapDAO.java
 * Author		:	vengamamba
 * Date			: 	23/06/2008
 */

package com.wipro.igrs.adminConfig.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.adminConfig.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;

public class ServiceParamMapDAO {

	DBUtility dbUtil = null;
	String query = null;
	ArrayList list = null;
	IGRSCommon igrsCommon = null;
	private Logger logger = (Logger) Logger.getLogger(ServiceParamMapDAO.class);

	public ServiceParamMapDAO() {

		try {
			dbUtil = new DBUtility();
			igrsCommon = new IGRSCommon();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/**
	 * Method : addParamMapping() Description : returns boolean gives success of
	 * insert or not
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @param query
	 *            :array of string return Type :boolean
	 */
	public boolean addParamMapping(String[] param, String roleId, String funId, String userId) throws Exception {

		boolean insertflag = false;

		try {
		        igrsCommon  = new IGRSCommon();
		    	logger.debug("before inserting master data ");
			query = CommonSQL.SERVICE_PARAM_MAP_ADDING;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			insertflag = dbUtil.executeUpdate(param);

			logger.debug("after inserting ");

		} catch (Exception x) {
			logger.error("Exception in addingData(): " + x);

		} finally {
			try {
				if (insertflag)
				{
					dbUtil.commit();
					igrsCommon.saveLogDet("IGRS_SERVICE_FEE_PARAM_MAPPING","INSERT","T",funId,userId,roleId);
				}
				
				else
				{
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_SERVICE_FEE_PARAM_MAPPING","INSERT","F",funId,userId,roleId);
				}
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("addingData(): " + ex);
			}
		}
		return insertflag;
	}

	/**
	 * Method : getFuncIdList Description : returns id,name list of function
	 * master
	 * 
	 * @param query :
	 *            return Type :ArrayList
	 */
	public ArrayList getFuncIdList() throws Exception {

		try {
			query = CommonSQL.GET_FUNCTION_ID_MAP;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in wardpatwarilist(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}

	/**
	 * Method : getServiceIdList Description : returns id,name list of Service
	 * master
	 * 
	 * @param query :
	 *            return Type :ArrayList
	 */
	public ArrayList getServiceIdList() throws Exception {

		try {
			query = CommonSQL.GET_SERVICE_ID_MAP;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in wardpatwarilist(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}

	/**
	 * Method :getServiceParamIdList() Description : returns id,name list of
	 * service Param master
	 * 
	 * @param query :
	 *            return Type :ArrayList
	 */
	public ArrayList getServiceParamIdList() throws Exception {

		try {
			query = CommonSQL.GET_SERVICEPARAM_ID_MAP;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in wardpatwarilist(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}

	/**
	 * Method : getOperatorIdList() Description : returns id,name list of
	 * operator master
	 * 
	 * @param query :
	 *            return Type :ArrayList
	 */
	public ArrayList getOperatorIdList() throws Exception {

		try {
			query = CommonSQL.GET_OPERATOR_ID_MAP;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in wardpatwarilist(): " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);

			}
		}
		return list;
	}

}
