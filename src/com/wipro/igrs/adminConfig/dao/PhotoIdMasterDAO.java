/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PhotoIdMasterDAO.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
 */
package com.wipro.igrs.adminConfig.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.adminConfig.dto.PhotoIdMasterDTO;
import com.wipro.igrs.adminConfig.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;

public class PhotoIdMasterDAO {
	DBUtility dbUtil = null;
	String query = null;
	ArrayList list = null;
	IGRSCommon igrsCommon = null;
	private Logger logger = (Logger) Logger.getLogger(PhotoIdMasterDAO.class);

	public PhotoIdMasterDAO() {

		try {
			igrsCommon = new IGRSCommon();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/**
	 * Method : addPhotoIdMaster() Description : returns boolean gives success
	 * of insert or not
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @param query
	 *            :array of string return Type :boolean
	 */
	public boolean addPhotoIdMaster(String[] param, String roleId, String funId, String userId) throws Exception {

		boolean insertflag = false;

		try {
			dbUtil = new DBUtility();
			logger.debug("before inserting master data ");
			query = CommonSQL.PHOTOID_MASTER_SAVE_DATA;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			insertflag = dbUtil.executeUpdate(param);

			logger.debug("after inserting ");
			if (insertflag)
			{
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST","INSERT","T",funId,userId,roleId);
			}
			else
			{
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST","INSERT","F",funId,userId,roleId);
			}
		} catch (Exception x) {
			logger.error("Exception in addingData(): " + x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST","INSERT","F",funId,userId,roleId);
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
	 * Method : updatePhotoIdmaster Description : returns boolean gives success
	 * of UPDATE or not
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @param query
	 *            :array of string return Type :boolean
	 */
	public boolean updatePhotoIdmaster(String p[], String roleId, String funId, String userId) throws Exception {

		boolean updateflag = false;

		try {
			dbUtil = new DBUtility();
			logger.debug("before updating master data ");
			query = CommonSQL.PHOTOID_MASTER_UPDATE_DATA;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			updateflag = dbUtil.executeUpdate(p);
			
			logger.debug("after updating ");
			if (updateflag)
			{
			dbUtil.commit();
			igrsCommon.saveLogDet("IGRS_PHOTOID_PROOF_TYPE_MASTER","UPDATE","T",funId,userId,roleId);
			}
			else
			{
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_PHOTOID_PROOF_TYPE_MASTER","UPDATE","F",funId,userId,roleId);
			}
		} catch (Exception x) {
			logger.error("Exception in updatingData(): " + x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_PHOTOID_PROOF_TYPE_MASTER","UPDATE","F",funId,userId,roleId);
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
	 * Method : getPhotoIdList Description : returns total data of
	 * partytypeMaster
	 * 
	 * @param query :
	 *            return Type :ArrayList
	 */
	public ArrayList  getPhotoIdList() throws Exception {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_PHOTOID_DATA;
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
	 * Method : getPhotoIdIdList() Description : returns data corresponding to
	 * id
	 * 
	 * @param query
	 *            :PhotoId return Type :dto object
	 */
	public ArrayList  getPhotoIdIdList(String id)
			throws Exception {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_PHOTOID_ID_DATA;
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
}