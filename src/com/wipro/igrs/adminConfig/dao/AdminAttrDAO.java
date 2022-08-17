package com.wipro.igrs.adminConfig.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.adminConfig.dto.AdminAttrDTO;
import com.wipro.igrs.adminConfig.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;

/**
 * ===========================================================================
 * File : AdminAttrDAO.java Description : Represents the Admin configuration DAO
 * Class Author : vengamamba P Created Date : Apr 10, 2008
 * 
 * ===========================================================================
 */
public class AdminAttrDAO {
	ArrayList list = null;
	String query;
	DBUtility dbUtil; // obj for dbutility
	private Logger logger = (Logger) Logger.getLogger(AdminAttrDAO.class);

	public AdminAttrDAO() throws Exception {
		 // creating connection to db

	}

	/**
	 * ===========================================================================
	 * Method : getModuleList() Description : Returns list of Moduleids and its
	 * values. Arguments : return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList  getModuleList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.ADMIN_MODULE_LIST;
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
	 * Method : getSubModuleList() Description : Returns list of subModuleids
	 * and its values. Arguments : moduleid return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getSubModuleList(String module) {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.ADMIN_SUBMODULE_LIST + "'" + module + "'";
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
	 * Method : getFunctionList() Description : Returns list of functionids and
	 * its values. Arguments : submoduleid return type : Arraylist
	 * 
	 * ===========================================================================
	 */
	public ArrayList getFunctionList(String submodule) {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.ADMIN_FUNCTION_LIST + "'" + submodule + "'";
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
}
