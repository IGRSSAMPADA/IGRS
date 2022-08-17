package com.wipro.igrs.adminConfig.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.adminConfig.dto.DeedParamMapDTO;
import com.wipro.igrs.adminConfig.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;

/**
 * ===========================================================================
 * File : DeedParamMapDAO .java Description : Represents the deed master DAO
 * Class Author : vengamamba P Created Date : Apr 21, 2008
 * 
 * ===========================================================================
 */
public class DeedParamMapDAO {
	ArrayList list = null;
	String query;
	DBUtility dbUtil; // obj for dbutility
	IGRSCommon igrsCommon = null;
	private Logger logger = (Logger) Logger.getLogger(DeedParamMapDAO.class);

	public DeedParamMapDAO() throws Exception {
		igrsCommon = new IGRSCommon();
		 // creating connection to db

	}

	/**
	 * ===========================================================================
	 * Method : getParamList() Description : Returns list of paramids and its
	 * values. Arguments : return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList  getParamList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_PARAM_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in photoidlist(): " + x);

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
	 * ===========================================================================
	 * Method : getOperatorList() Description : Returns list of operatorids and
	 * its values. Arguments : return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList  getOperatorList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_OPERATOR_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in photoidlist(): " + x);

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
	 * ===========================================================================
	 * Method : getDeedList() Description : Returns list of deedids and its
	 * values. Arguments : return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getDeedList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_DEED_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in photoidlist(): " + x);

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
	 * ===========================================================================
	 * Method : getInstrumentList() Description : Returns list of instrumentids
	 * and its values. Arguments : deedid return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList  getInstrumentList(String deed) {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_INSTRUMENT_LIST + "'" + deed + "'";
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getSubModuleList(): " + x);

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
	 * ===========================================================================
	 * Method : getExemptionList() Description : Returns list of Exemptionids
	 * and its values. Arguments : instrumentid return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList  getExemptionList(String instrument,
			String type) {

		try {
			dbUtil = new DBUtility();
			if (type.equalsIgnoreCase("deed"))
				query = CommonSQL.MASTER_EXEMPTION_LIST_DEED + "'" + instrument
						+ "'";
			else
				query = CommonSQL.MASTER_EXEMPTION_LIST + "'" + instrument
						+ "'";

			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in photoidlist(): " + x);

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
	 * Method : addingData Description : returns boolean gives success of insert
	 * or not
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @param query :
	 *            array of string , return Type :boolean
	 */
	public boolean addingData(String[] param, String roleId, String funId, String userId) throws Exception {

		boolean insertflag = false;

		try {
			dbUtil = new DBUtility();
			logger.debug("before inserting master data ");
			query = CommonSQL.MASTER_SAVE_DATA;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			insertflag = dbUtil.executeUpdate(param);

			logger.debug("after inserting ");
			if(insertflag) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST","INSERT","T",funId,userId,roleId);
			}else {
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST","INSERT","F",funId,userId,roleId);
			}
		} catch (Exception x) {
			logger.error("Exception in addingData(): " + x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST","INSERT","F",funId,userId,roleId);
		} finally {
			try {
				//dbUtil.commit();
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("addingData(): " + ex);
			}
		}
		return insertflag;
	}

}
