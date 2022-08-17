/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
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
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.adminConfig.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.adminConfig.form.AdminAttrForm;
import com.wipro.igrs.adminConfig.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;

/**
* 
* AttributeDAO.java <br>
* AttributeDAO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 14-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class AttributeDAO {
	
	ArrayList list = null;
	String query;
	IGRSCommon igrsCommon = null;
	DBUtility dbUtil; // obj for dbutility
	private Logger logger = (Logger) Logger.getLogger(AttributeDAO.class);

	/**
	 * 
	 * @throws Exception
	 */
	public AttributeDAO() throws Exception {
		 // creating connection to db

	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	public ArrayList gettingData(String[] p) {

		try {
			dbUtil = new DBUtility();
			// getting challangen details from igrs_challan_gen_details
			query = CommonSQL.GET_ALL_ATTRIBUTE_LIST;
			logger.debug("the query is  in DAO   " + query);
//			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(query);
//			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error(x.getMessage(), x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug("Exception in closing connection :-" + ex);
			}
		}
		return list;
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	public ArrayList displayData(String[] p) {

		try {
			dbUtil = new DBUtility();
			// getting challangen details from igrs_challan_gen_details
			query = CommonSQL.DISPLAY_ALL_ATTRIBUTE_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			//logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error(x.getMessage(), x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.debug(ex.getMessage(), ex);
			}
		}
		return list;
	}

	/**
	 * 
	 * @param param
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean addingData(String[] param, String roleId, String funId, String userId) throws Exception {

		boolean insertflag = false;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_ONE_ROLE_ID_FOR_USER_ID);
			roleId = dbUtil.executeQry(new String[]{userId});
			igrsCommon = new IGRSCommon();
			logger.debug("before inserting TID data");
			query = CommonSQL.ADDING_ATTRIBUTE_LIST;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			insertflag = dbUtil.executeUpdate(param);

			logger.debug("after inserting ");
			try {
				if (insertflag) {
					//				dbUtil.commit();
					igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST", "INSERT",
							"T", userId, funId, roleId);
				} else {
					//				dbUtil.rollback();
					igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST", "INSERT",
							"F", userId, funId, roleId);
				}
			} catch (Exception e) {
			}
		} catch (Exception x) {
			logger.error(x.getMessage(), x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		return insertflag;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList getDataById(String id) {

		try {
			dbUtil = new DBUtility();
			igrsCommon = new IGRSCommon();
			query = CommonSQL.ATTRIBUTE_LIST_BY_ID;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			list = new ArrayList();
			list = dbUtil.executeQuery(new String[]{id});
//			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error(x.getMessage(), x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);

			}
		}
		return list;
	}

	/**
	 * 
	 * @param param
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean updateData(String[] param, String roleId, String funId, String userId) throws Exception {

		boolean updateflag = false;

		try {
			dbUtil = new DBUtility();
			
			logger.debug("before inserting TID data ");
			query = CommonSQL.UPDATE_ATTRIBUTE_LIST;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			updateflag = dbUtil.executeUpdate(param);

			logger.debug("after inserting ");
//			if(updateflag) {
//				dbUtil.commit();
//			}else {
//				dbUtil.rollback();
//			}
			try {
				igrsCommon = new IGRSCommon();
				if (updateflag) {
					igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST", "UPDATE",
							"T", userId, funId, roleId);
				} else {
					igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST", "UPDATE",
							"F", userId, funId, roleId);
				}
			} catch (Exception e) {
			}
		} catch (Exception x) {
			logger.error(x.getMessage(), x);
//			dbUtil.rollback();
//			igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST","UPDATE","F",userId,funId,roleId);
		} finally {
			try {
				//dbUtil.commit();
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		return updateflag;
	}

	/**
	 * 
	 * @param param
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteDataById(String[] param, String roleId, String funId, String userId) throws Exception {

		boolean updateflag = false;

		try {
			dbUtil = new DBUtility();
			igrsCommon = new IGRSCommon();
			logger.debug("before inserting TID data ");
			query = CommonSQL.UPDATE_ATTRIBUTE_STATUS;
			dbUtil.createPreparedStatement(query);
			logger.debug("the query is  in DAO   " + query);
			updateflag = dbUtil.executeUpdate(param);
			
			
			logger.debug("after updating status ");

			if(updateflag) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST","UPDATE","T",userId,funId,roleId);
			}else {
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST","UPDATE","F",userId,funId,roleId);
			}
		} catch (Exception x) {
			logger.error(x.getMessage(), x);
			dbUtil.rollback();
			igrsCommon.saveLogDet("IGRS_CONFIG_PARAM_LIST","UPDATE","F",userId,funId,roleId);
			
		} finally {
			try {
				//dbUtil.commit();
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
		return updateflag;
	}

	public ArrayList<DropDownDTO> getModuleList() {
		ArrayList<DropDownDTO> retVal = new ArrayList<DropDownDTO>();
		try {
			ArrayList data, row;
			DBUtility util = new DBUtility();
			DropDownDTO entry;
			try {
				String query = CommonSQL.GET_MODULE_LISTING;
				util.createStatement();
				data = util.executeQuery(query);
				for (Object item : data) {
					row = (ArrayList) item;
					entry = new DropDownDTO();
					entry.setOptionValue((String) row.get(0));
					entry.setOptionLabel((String) row.get(1));
					retVal.add(entry);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				util.closeConnection();
			}
		} catch (Exception x) {
			logger.error(x.getMessage(), x);
		} 
		return retVal;
	}

	public ArrayList<DropDownDTO> getFunctionList(String moduleID) {
		ArrayList<DropDownDTO> retVal = new ArrayList<DropDownDTO>();
		try {
			ArrayList data, row;
			DBUtility util = new DBUtility();
			DropDownDTO entry;
			try {
				String query = CommonSQL.GET_FUNCTION_LISTING_BY_MODULE;
				util.createPreparedStatement(query);
				data = util.executeQuery(new String[]{moduleID});
				for (Object item : data) {
					row = (ArrayList) item;
					entry = new DropDownDTO();
					entry.setOptionValue((String) row.get(0));
					entry.setOptionLabel((String) row.get(1));
					retVal.add(entry);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				util.closeConnection();
			}
		} catch (Exception x) {
			logger.error(x.getMessage(), x);
		} 
		return retVal;
	}

	public String getSubModuleID(String functionID) {
		String retVal = "";
		try {
			ArrayList data, row;
			DBUtility util = new DBUtility();
			try {
				String query = CommonSQL.GET_SUBMODULE_ID_BYFUNCTION;
				util.createPreparedStatement(query);
				data = util.executeQuery(new String[]{functionID});
				for (Object item : data) {
					row = (ArrayList) item;
					retVal = (String) row.get(0);
					break;
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				util.closeConnection();
			}
		} catch (Exception x) {
			logger.error(x.getMessage(), x);
		} 
		return retVal;
	}

	public boolean checkAttributeUniqueName(AdminAttrForm adminForm) {
		boolean retVal = false;
		try {
			ArrayList data, row;
			DBUtility util = new DBUtility();
			try {
				String query = CommonSQL.GET_UNIQUE_ATTRIB_NAME_CHECK;
				util.createPreparedStatement(query);
				data = util.executeQuery(new String[]{adminForm.getModuleID(), adminForm.getAttributeName()});
				for (Object item : data) {
					row = (ArrayList) item;
					retVal = ((String) row.get(0)).equals("0");
					break;
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				util.closeConnection();
			}
		} catch (Exception x) {
			logger.error(x.getMessage(), x);
		} 
		return retVal;
	}

}
