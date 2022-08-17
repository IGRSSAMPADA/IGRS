/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceMasterDAO.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
 */
package com.wipro.igrs.adminConfig.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.adminConfig.dto.ServiceMasterDTO;
import com.wipro.igrs.adminConfig.sql.CommonSQL;
import com.wipro.igrs.bankmaster.sql.BankCommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;

public class ServiceMasterDAO {
	DBUtility dbUtil = null;
	String query = null;
	ArrayList list = null;
	IGRSCommon igrsCommon = null;
	private Logger logger = (Logger) Logger.getLogger(ServiceMasterDAO.class);

	public ServiceMasterDAO() {

		try {
			igrsCommon = new IGRSCommon();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/**
	 * Method : addServiceMaster() Description : returns boolean gives success
	 * of insert or not
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @param query
	 *            :array of string return Type :boolean
	 */
	public boolean addServiceMaster(String[] param, String roleId, String funId, String userId) throws Exception {

		boolean insertflag = false;

		try {
			dbUtil = new DBUtility();
			logger.debug("before inserting master data ");
			query = CommonSQL.SERVICE_MASTER_SAVE_DATA;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			insertflag = dbUtil.executeUpdate(param);
			logger.debug("after inserting ");
			if (insertflag)
			{
			dbUtil.commit();
			igrsCommon.saveLogDet("IGRS_SERVICE_MASTER","INSERT","T",funId,userId,roleId);
			}
			else
			{
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_SERVICE_MASTER","INSERT","F",funId,userId,roleId);
			}
		} catch (Exception x) {
			logger.error("Exception in addingData(): " + x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_SERVICE_MASTER","INSERT","F",funId,userId,roleId);

		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("addingData(): " + ex);
			}
		}
		return insertflag;
	}

	/**
	 * Method : updateServicemaster Description : returns boolean gives success
	 * of UPDATE or not
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @param query
	 *            :array of string return Type :boolean
	 */
	public boolean updateServicemaster(String p[], String roleId, String funId, String userId) throws Exception {

		boolean updateflag = false;

		try {
			dbUtil = new DBUtility();
			logger.debug("before updating master data ");
			query = CommonSQL.SERVICE_MASTER_UPDATE_DATA;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			updateflag = dbUtil.executeUpdate(p);

			logger.debug("after updating ");
			if (updateflag)
			{
				dbUtil.commit();
			igrsCommon.saveLogDet("IGRS_SERVICE_MASTER","UPDATE","T",funId,userId,roleId);
			}
			else{
				dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_SERVICE_MASTER","UPDATE","F",funId,userId,roleId);
			}
		} catch (Exception x) {
			logger.error("Exception in updatingData(): " + x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_SERVICE_MASTER","UPDATE","F",funId,userId,roleId);
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("updatingData(): " + ex);
			}
		}
		return updateflag;
	}

	/**
	 * Method : getServiceList Description : returns total data of
	 * partytypeMaster
	 * 
	 * @param query :
	 *            return Type :ArrayList
	 */
	public ArrayList getServiceList() throws Exception {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_SERVICE_DATA;
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
	 * Method : getServiceIdList() Description : returns data corresponding to
	 * id
	 * 
	 * @param query
	 *            :Serviceid return Type :dto object
	 */
	public ArrayList getServiceIdList(String id)
			throws Exception {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_SERVICE_ID_DATA;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			String p[] = new String[1];
			;
			p[0] = id;
			list = dbUtil.executeQuery(p);
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
	
	public boolean deleteService(String serviceId, String roleId, String funId, String userId) throws Exception {
		boolean success = true;
    	String param[] = new String[1];
    	param[0] = serviceId;
    	
    	String sql=CommonSQL.DELETE_SERVICE_MASTER; 
    try {
    	igrsCommon = new IGRSCommon();
    	dbUtil = new DBUtility();
    	dbUtil.createPreparedStatement(sql);
        boolean boo = dbUtil.executeUpdate(param);
        if (boo){
        	dbUtil.commit();
        	igrsCommon.saveLogDet("IGRS_SERVICE_MASTER","DELETE","T",funId,userId,roleId);
         }    
        else
        {	
        	dbUtil.rollback();
        igrsCommon.saveLogDet("IGRS_SERVICE_MASTER","DELETE","F",funId,userId,roleId);
        }
    	} catch (Exception e) {
    		logger.error("Exception in deleteService" + e);
    	}
    	finally {
    		try {
    			dbUtil.closeConnection();
    		} catch (Exception e) {
    			logger.error("Exception in closing connection" + e);
        	}
        
   	 	}

		return success;
	}
}