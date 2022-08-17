package com.wipro.igrs.adminConfig.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.adminConfig.dto.SubClauseMapDTO;
import com.wipro.igrs.adminConfig.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;

/**
 * ===========================================================================
 * File : subClauseMapDAO .java Description : Represents the sub clause map DAO
 * Class Author : vengamamba P Created Date : Apr 28, 2008
 * 
 * ===========================================================================
 */
public class SubClauseMapDAO {
	ArrayList list = null;
	String query;
	DBUtility dbUtil; // obj for dbutility
	IGRSCommon igrsCommon = null;
	private Logger logger = (Logger) Logger.getLogger(SubClauseMapDAO.class);

	public SubClauseMapDAO() throws Exception {
		 // creating connection to db
		igrsCommon = new IGRSCommon();
		

	}

	/**
	 * ===========================================================================
	 * Method : getDistrictList() Description : Returns list of Districtids and
	 * its values. Arguments : return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getDistrictList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_DISTRICT_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in DistrictList(): " + x);

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
	public ArrayList getOperatorList() {

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
	 * Method : getPropertyIdList() Description : Returns list of propertyids
	 * and its values. Arguments : return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getPropertyIdList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_PROPERTY_TYPE_LIST;
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
	 * Method : getWardPatwariList() Description : Returns list of
	 * wardpatwariListof ids and its values. Arguments : tehsilid return type :
	 * Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getWardPatwariList(String tehsil) {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_WARD_PATWARI_LIST + "'" + tehsil + "'";
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
	 * Method : getTehsilList() Description : Returns list of TehsilListof ids
	 * and its values. Arguments : districtid return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getTehsilList(String district) {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_TEHSIL_LIST + "'" + district + "'";
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
	 * Method : getVillageIDList Description : Returns list of VillageIDListof
	 * ids and its values. Arguments : wardPatwariid return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getVillageIDList(String wardpatid) {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_MOHALLA_VILLAGE_LIST + "'" + wardpatid
					+ "'";
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
	 * Method : getPropertyL1IdList Description : Returns list of
	 * propertyL1Listof ids and its values. Arguments : propertyid return type :
	 * Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getPropertyL1IdList(String propertyid) {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_PROPERTY_TYPEL1_LIST + "'" + propertyid
					+ "'";
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
	 * Method : getPropertyL2IdList Description : Returns list of
	 * propertyL2Listof ids and its values. Arguments : propertyL1id return type :
	 * Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getPropertyL2IdList(String propertyL1id) {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_PROPERTY_TYPEL2_LIST + "'" + propertyL1id
					+ "'";
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
	 * Method : getSubClauseList() Description : Returns list of SubClauseList
	 * of ids and its values. Arguments : return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getSubClauseList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.MASTER_SUBCLAUSE_LIST;
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
			query = CommonSQL.SUBCLAUSE_AREA_MAP_SAVE;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			insertflag = dbUtil.executeUpdate(param);

			logger.debug("after inserting ");
			if(insertflag) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_SUB_CLAUSE_AREA_MAPPING","INSERT","T",funId,userId,roleId);
			}else {
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_SUB_CLAUSE_AREA_MAPPING","INSERT","F",funId,userId,roleId);
			}
 		} catch (Exception x) {
			logger.error("Exception in updateStatus(): " + x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_SUB_CLAUSE_AREA_MAPPING","INSERT","F",funId,userId,roleId);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in updateStatus()close connection: "
						+ ex);

			}
		}
		return insertflag;
	}

	/**
	 * Method : commitingData Description : commiting whole trans success
	 * 
	 * @param query :
	 * @throws :
	 *             Exception return Type :void
	 */

	public void commitingData() throws Exception {
		try {
			dbUtil = new DBUtility();
			logger.debug("before commiting whole transaction ");
			dbUtil.commit();
		} catch (Exception x) {
			logger.error("Exception in commit(): " + x);

		}
	}

	/**
	 * Method : cancellingData Description : rollbacking whole trans if anyone
	 * fail
	 * 
	 * @param query :
	 * @throws :
	 *             Exception return Type :void
	 */

	public void cancellingData() throws Exception {
		try {
			dbUtil = new DBUtility();
			logger.debug("before commiting whole transaction ");
			dbUtil.rollback();
		} catch (Exception x) {
			logger.error("Exception in rollback(): " + x);

		}
	}

}
