/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MohallaMasterDAO.java
 * Author		:	vengamamba
 * Date			: 	17/06/2008
 */
package com.wipro.igrs.adminConfig.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.adminConfig.dto.MohallaMasterDTO;
import com.wipro.igrs.adminConfig.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;

public class MohallaMasterDAO {

	DBUtility dbUtil = null;
	String query = null;
	ArrayList list = null;
	private Logger logger = (Logger) Logger.getLogger(MohallaMasterDAO.class);
	IGRSCommon igrsCommon = null;
	public MohallaMasterDAO() {

		try {
			igrsCommon = new IGRSCommon();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/**
	 * ===========================================================================
	 * Method : getWardpatwariList Description : Returns list of wardpatwariids
	 * and its values. Arguments : return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList  getWardpatwariList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_WARD_PATWARI_IDLIST;
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
	 * Method : addMohallaMaster Description : returns boolean gives success of
	 * insert or not
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @param query :
	 *            array of string , return Type :boolean
	 */
	public boolean addMohallaMaster(String[] param, String roleId, String funId, String userId) throws Exception {

		boolean insertflag = false;

		try {
			dbUtil = new DBUtility();
			logger.debug("before inserting master data ");
			query = CommonSQL.MOHALLA_MASTER_SAVE_DATA;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			insertflag = dbUtil.executeUpdate(param);
			logger.debug("after inserting ");
			if (insertflag)
			{
			dbUtil.commit();
			igrsCommon.saveLogDet("IGRS_MOHALLA_VILLAGE_MASTER","INSERT","T",funId,userId,roleId);
			}
			else
			{
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_MOHALLA_VILLAGE_MASTER","INSERT","F",funId,userId,roleId);
			}
		} catch (Exception x) {
			logger.error("Exception in addingData(): " + x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_MOHALLA_VILLAGE_MASTER","INSERT","F",funId,userId,roleId);

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
	 * Method : updateMohallamaster Description : returns boolean gives success
	 * of UPDATE or not
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @param query :
	 *            array of string , return Type :boolean
	 */
	public boolean updateMohallamaster(String p[], String roleId, String funId, String userId) throws Exception {

		boolean updateflag = false;

		try {
			dbUtil = new DBUtility();
			logger.debug("before updating master data ");
			query = CommonSQL.MOHALLA_MASTER_UPDATE_DATA;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			updateflag = dbUtil.executeUpdate(p);

			logger.debug("after updating ");
			if (updateflag)
			{
			dbUtil.commit();
			igrsCommon.saveLogDet("IGRS_MOHALLA_VILLAGE_MASTER","UPDATE","T",funId,userId,roleId);
			}
			else
			{
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_MOHALLA_VILLAGE_MASTER","UPDATE","T",funId,userId,roleId);
			}
		} catch (Exception x) {
			logger.error("Exception in updatingData(): " + x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_MOHALLA_VILLAGE_MASTER","UPDATE","T",funId,userId,roleId);
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
	 * Method : getMohallaList Description : returns total data of
	 * mohallavillageMaster
	 * 
	 * @param query :
	 *            return Type :ArrayList
	 */
	public ArrayList  getMohallaList() throws Exception {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_MOHALLA_VILLAGE_DATA;
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
	 * Method : getMohallaIdList() Description : returns data corresponding to
	 * id
	 * 
	 * @param query
	 *            :villageid return Type :dto object
	 */
	public ArrayList  getMohallaIdList(String id)
			throws Exception {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_MOHALLA_VILLAGE_ID_DATA;
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