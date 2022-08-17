/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/
package com.wipro.igrs.caseMonitoring.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.caseMonitoring.dto.CaseMonDTO;
import com.wipro.igrs.caseMonitoring.dto.CaseMonitoringDTO;
import com.wipro.igrs.caseMonitoring.sql.CaseMonSQL;
import com.wipro.igrs.caseMonitoring.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;

/*******************************************************************************
 * 
 * File : CaseMonDAO.java Description : Represent the CaseMonitoring DAO Class
 * Author : Karteek P Created Date : June 23, 2008
 ******************************************************************************/
public class CaseMonDAO {
	//DBUtility dbUtility = null;

	private Logger logger = (Logger) Logger.getLogger(CaseMonDAO.class);

	public CaseMonDAO() {
		

	}

	/***************************************************************************
	 * Method : GetCaseIds Arguments : From Date and ToDate Return : ArrayList
	 * Exception : NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized ArrayList getCaseIds(String _fromDate, String _toDate,
			String _caseType, String _status, String _caseActionType)
			throws NullPointerException, SQLException, Exception {
		ArrayList caseList = null;
		DBUtility dbUtility = new DBUtility();
		try {
			
			caseList = new ArrayList();
			String caseTypeId = getCaseTypeId(_caseType);
			if (caseTypeId != null) {
				dbUtility.createPreparedStatement(CaseMonSQL.IGRS_CASE_TXN_IDS);
				//logger.debug("3333 QRY IN DAO -->  "+ CaseMonSQL.IGRS_CASE_TXN_IDS);
				String[] caseDates = new String[5];
				caseDates[0] = _status;
				caseDates[1] = _fromDate;
				caseDates[2] = _toDate;
				caseDates[3] = caseTypeId;
				caseDates[4] = getCaseAction(_caseActionType);
				//logger.debug("case Type in DAO is -->  " + caseDates[3]);
				caseList = dbUtility.executeQuery(caseDates);
			}
		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in getCaseIds() in BO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in getCaseIds() in BO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in getCaseIds() in BO  : " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseIds() : "
								+ se);
			}
		}
		//logger.debug("222 Final DAO retrun -->  " + caseList);
		return caseList;
	}

	/***************************************************************************
	 * Method : getCaseViewIds Arguments : From Date and ToDate Return :
	 * ArrayList Exception : NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized ArrayList getCaseViewIds(String _fromDate,
			String _toDate, String _caseType) throws NullPointerException,
			SQLException, Exception {
		ArrayList caseList = null;
		DBUtility dbUtility = new DBUtility();
		try {
			caseList = new ArrayList();
			String caseTypeId = getCaseTypeId(_caseType);
			if (caseTypeId != null) {
				dbUtility
						.createPreparedStatement(CaseMonSQL.IGRS_CASE_VIEW_LIST);
				/*logger.debug("3333 QRY IN DAO -->  "
						+ CaseMonSQL.IGRS_CASE_VIEW_LIST);*/
				String[] caseDates = new String[3];

				caseDates[0] = _fromDate;
				caseDates[1] = _toDate;
				caseDates[2] = caseTypeId;
				//logger.debug("case Type in DAO is -->  " + caseDates[2]);
				caseList = dbUtility.executeQuery(caseDates);
			}
		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in getCaseIds() in BO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in getCaseIds() in BO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in getCaseIds() in BO  : " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseIds() : "
								+ se);
			}
		}
		//logger.debug("222 Final DAO retrun -->  " + caseList);
		return caseList;
	}

	/***************************************************************************
	 * Method : GetCaseId Action Details Arguments : CaseType Return : String
	 * Exception : NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized String getCaseAction(String _caseActionType)
			throws NullPointerException, SQLException, Exception {
		String caseActionID = null;
		DBUtility caseActionDb = null;
		try {
			caseActionDb = new DBUtility();
			caseActionDb.createStatement();

			String caseQry = CaseMonSQL.IGRS_CASE_ID_ACTION + " '"
					+ _caseActionType + "' ";

			//logger.debug("qry is  : " + caseQry);
			caseActionID = caseActionDb.executeQry(caseQry);

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in getCaseAction() in BO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in getCaseAction() in BO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in getCaseAction() in BO  : " + e);
		} finally {
			try {
				caseActionDb.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseAction() : "
								+ se);
			}
		}
		//logger.debug("222 Final DAO retrun -->  " + caseActionID);
		return caseActionID;
	}

	/***************************************************************************
	 * Method : GetCaseId Details Arguments : CaseID Return : ArrayList
	 * Exception : NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized ArrayList getCaseIdDetails(String _caseId,
			String _caseActionType) throws NullPointerException, SQLException,
			Exception {
		ArrayList caseList = null;
		DBUtility dbUtility = new DBUtility();
		try {
			caseList = new ArrayList();
			String caseTypeAction = getCaseAction(_caseActionType);
			/*String caseQry = CaseMonSQL.IGRS_CASE_TXN_LIST + " '" + _caseId
					+ "' and ICTD.case_action_id = '" + caseTypeAction + "'";*/
			String caseQry = CaseMonSQL.IGRS_CASE_TXN_LIST1 + " '" + _caseId
			+ "' and ICTD.case_action_id = '" + caseTypeAction + "'";
			dbUtility.createStatement();
			caseList = dbUtility.executeQuery(caseQry);

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseIdDetails() : "
								+ se);
			}
		}
		//logger.debug("222 Final DAO retrun -->  " + caseList);
		return caseList;
	}

	/***************************************************************************
	 * Method : GetCaseNotice Details Arguments : CaseID Return : ArrayList
	 * Exception : NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized ArrayList getCaseNotice(String _caseId,
			String _caseActionType) throws NullPointerException, SQLException,
			Exception {
		ArrayList caseList = null;
		DBUtility dbUtility = new DBUtility();
		try {
			caseList = new ArrayList();
			String caseTypeAction = getCaseAction(_caseActionType);
			String caseQry = CaseMonSQL.IGRS_CASE_NOTICE_DETAILS + " '"
					+ _caseId + "' and ICTD.case_action_id = '"
					+ caseTypeAction + "'";
			dbUtility.createStatement();
			//logger.debug("qry is  : " + caseQry);
			caseList = dbUtility.executeQuery(caseQry);

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseIdDetails() : "
								+ se);
			}
		}
		//logger.debug("222 Final DAO retrun -->  " + caseList);
		return caseList;
	}

	/***************************************************************************
	 * Method : Get EStamp Amount Arguments : CaseID Return : ArrayList
	 * Exception : NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized String getCaseEstampAmt(String _eStampId)
			throws NullPointerException, SQLException, Exception {
		String stampAmt = null;
		DBUtility stampAmtdb = null;
		try {
			stampAmtdb = new DBUtility();
			String caseStampAmtQry = CaseMonSQL.IGRS_CASE_STAMP_AMT + " '"
					+ _eStampId + "' ";
			//logger.debug("before qry is  : " + caseStampAmtQry);
			stampAmtdb.createStatement();
			//logger.debug("qry is  : " + caseStampAmtQry);
			stampAmt = stampAmtdb.executeQry(caseStampAmtQry);
			if (stampAmt != null) {
				return stampAmt;
			} else {
				return null;
			}
		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in getCaseEstampAmt() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in getCaseEstampAmt() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in getCaseEstampAmt() in DAO  : " + e);
		} finally {
			try {
				stampAmtdb.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
								+ se);
			}
		}
		//logger.debug("33333 Final DAO retrun -->  " + stampAmt);
		return stampAmt;
	}

	/***************************************************************************
	 * Method : Set Case Details Arguments : CaseList Return : String Exception
	 * : NullPointerException,SQLException,Exception
	 * 
	 * @param userId
	 * @param funId
	 * @param roleId
	 **************************************************************************/
	public synchronized String setCaseDetails(String[] _caseList,
			String _comments, String roleId, String funId, String userId)
			throws NullPointerException, SQLException, Exception {
		String caseId = null;
		DBUtility dbUtility = new DBUtility();
		try {
			logger.debug("insert Values is started DAO ");
			IGRSCommon igrsCommon = new IGRSCommon();
			_caseList[0] = igrsCommon.getSequenceNumber("IGRS_CASE_TXN_ID",
					"CRP");
			_caseList[7] = getCaseActionId("INITIATE");

			dbUtility.createPreparedStatement(CaseMonSQL.IGRS_CASE_LIST_INSERT);
			boolean id = false;
			//logger.debug("The Array is  " + _caseList);
			id = dbUtility.executeUpdate(_caseList);
			//logger.debug("iddddddddddd --> " + id);
			if (id != false) {
				caseId = _caseList[0];
				//logger.debug("Case ID Is --------->   " + caseId);
				if (caseId != null) {
					String actionId = _caseList[7];
					if (actionId != null) {
						String caseactionTxnID = igrsCommon.getSequenceNumber(
								"IGRS_CASE_AXN_TXN", "CSA");
						boolean actionMap = false;
						actionMap = setactionMap(caseId, actionId,
								caseactionTxnID, null);
						if (actionMap != false) {
							boolean comment = false;
							comment = setCaseComments(caseId, _comments,
									caseactionTxnID, roleId, funId, userId);
							if (comment != false) {
								return caseId;
							}
						} else {
						}
					} else {
					}
				} else {
					return null;
				}
			} else {
				return null;
			}

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in setCaseDetails() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in setCaseDetails() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in setCaseDetails() in DAO  : " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
								+ se);
			}
		}
		//logger.debug("33333 Final DAO retrun -->  " + caseId);
		return caseId;
	}

	/***************************************************************************
	 * Method : Update Case Details Arguments : CaseList Return : String
	 * Exception : NullPointerException,SQLException,Exception
	 * 
	 * @param userId
	 * @param funId
	 * @param roleId
	 **************************************************************************/
	public synchronized String updateCaseDetails(String[] _caseList,
			String _comments, String roleId, String funId, String userId)
			throws NullPointerException, SQLException, Exception {
		String caseId = null;
		DBUtility dbUtility = new DBUtility();
		try {
			logger.debug("insert Values is started DAO ");
			IGRSCommon igrsCommon = new IGRSCommon();
			_caseList[1] = getCaseActionId(_caseList[1]);

			dbUtility.createPreparedStatement(CaseMonSQL.IGRS_CASE_UPDATE);
			//logger.debug("qryyyyyyyyyyy---->  " + CaseMonSQL.IGRS_CASE_UPDATE);

			boolean caseUpdate = false;
			//logger.debug("The Array is  " + _caseList);
			caseUpdate = dbUtility.executeUpdate(_caseList);
			//logger.debug("iddddddddddd --> " + caseUpdate);
			if (caseUpdate) {
				caseId = _caseList[2];
				//logger.debug("Case ID Is --------->   " + caseId);
				if (caseId != null) {
					String actionId = _caseList[1];
					if (actionId != null) {
						String caseactionTxnID = igrsCommon.getSequenceNumber(
								"IGRS_CASE_AXN_TXN", "CSA");
						boolean actionMap = false;
						actionMap = setactionMap(caseId, actionId,
								caseactionTxnID, "");
						if (actionMap) {
							boolean comment = false;
							comment = setCaseComments(caseId, _comments,
									caseactionTxnID, funId, userId, roleId);
							if (comment) {
								igrsCommon.saveLogDet("igrs_case_txn_details",
										"UPDATE", "T", funId, userId, roleId);
								return caseId;

							}
						} else {
						}
					} else {
					}
				} else {
					return null;
				}
			} else {
				igrsCommon.saveLogDet("igrs_case_txn_details", "UPDATE", "F",
						funId, userId, roleId);
				return null;
			}

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in updateCaseDetails() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in updateCaseDetails() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in updateCaseDetails() in DAO  : " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at updateCaseDetails() : "
								+ se);
			}
		}
		//logger.debug("33333 Final DAO retrun -->  " + caseId);
		return caseId;
	}

	/***************************************************************************
	 * Method : Case Notice Details Arguments : CaseList,comments,notice Return
	 * : String Exception : NullPointerException,SQLException,Exception
	 * 
	 * @param userId
	 * @param funId
	 * @param roleId
	 **************************************************************************/
	public synchronized String CaseNoticeDetails(String[] _caseList,
			String _comments, String _notice, String roleId, String funId,
			String userId) throws NullPointerException, SQLException, Exception {
		String caseId = null;
		DBUtility dbUtility = new DBUtility();
		try {
			logger.debug("insert Values is started DAO ");
			IGRSCommon igrsCommon = new IGRSCommon();
			_caseList[1] = getCaseActionId(_caseList[1]);

			dbUtility.createPreparedStatement(CaseMonSQL.IGRS_CASE_NOTICE);

			boolean caseUpdate = false;
			//logger.debug("The Array is  " + _caseList);
			caseUpdate = dbUtility.executeUpdate(_caseList);
			//logger.debug("iddddddddddd --> " + caseUpdate);
			if (caseUpdate) {
				caseId = _caseList[3];
				//logger.debug("Case ID Is --------->   " + caseId);
				if (caseId != null) {
					String actionId = _caseList[1];
					if (actionId != null) {
						String caseactionTxnID = igrsCommon.getSequenceNumber(
								"IGRS_CASE_AXN_TXN", "CSA");
						boolean actionMap = false;
						actionMap = setactionMap(caseId, actionId,
								caseactionTxnID, _notice);
						if (actionMap) {
							boolean comment = false;
							comment = setCaseComments(caseId, _comments,
									caseactionTxnID, funId, userId, roleId);
							if (comment) {
								igrsCommon.saveLogDet("igrs_case_txn_details",
										"UPDATE", "T", funId, userId, roleId);
								return caseId;
							}
						} else {
						}
					} else {
					}
				} else {
					return null;
				}
			} else {
				igrsCommon.saveLogDet("igrs_case_txn_details", "UPDATE", "F",
						funId, userId, roleId);
				return null;
			}

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in CaseNoticeDetails() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in CaseNoticeDetails() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in CaseNoticeDetails() in DAO  : " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at CaseNoticeDetails() : "
								+ se);
			}
		}
		//logger.debug("33333 Final DAO retrun -->  " + caseId);
		return caseId;
	}

	/***************************************************************************
	 * Method : Case Close Details Arguments : CaseList,comments,notice Return :
	 * String Exception : NullPointerException,SQLException,Exception
	 * 
	 * @param userId
	 * @param funId
	 * @param roleId
	 **************************************************************************/
	public synchronized String CaseClsoeDetails(String[] _caseList,
			String _comments, String _notice, String roleId, String funId,
			String userId) throws NullPointerException, SQLException, Exception {
		String caseId = null;
		DBUtility dbUtility = new DBUtility();
		try {
			logger.debug("insert Values is started DAO ");
			IGRSCommon igrsCommon = new IGRSCommon();
			_caseList[1] = getCaseActionId(_caseList[1]);

			dbUtility.createPreparedStatement(CaseMonSQL.IGRS_CASE_CLOSE);

			boolean caseUpdate = false;
			//logger.debug("The Array is  " + _caseList);
			caseUpdate = dbUtility.executeUpdate(_caseList);
			//logger.debug("iddddddddddd --> " + caseUpdate);
			if (caseUpdate) {
				caseId = _caseList[3];
				//logger.debug("Case ID Is --------->   " + caseId);
				if (caseId != null) {
					String actionId = _caseList[1];
					if (actionId != null) {
						String caseactionTxnID = igrsCommon.getSequenceNumber(
								"IGRS_CASE_AXN_TXN", "CSA");
						boolean actionMap = false;
						actionMap = setactionMap(caseId, actionId,
								caseactionTxnID, _notice);
						if (actionMap) {
							boolean comment = false;
							comment = setCaseComments(caseId, _comments,
									caseactionTxnID, funId, userId, roleId);
							if (comment) {
								igrsCommon.saveLogDet("igrs_case_txn_details",
										"UPDATE", "T", funId, userId, roleId);
								return caseId;

							}
						} else {
						}
					} else {
					}
				} else {
					return null;
				}
			} else {
				igrsCommon.saveLogDet("igrs_case_txn_details", "UPDATE", "F",
						funId, userId, roleId);
				return null;
			}

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in CaseNoticeDetails() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in CaseNoticeDetails() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in CaseNoticeDetails() in DAO  : " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at CaseNoticeDetails() : "
								+ se);
			}
		}
		//logger.debug("33333 Final DAO retrun -->  " + caseId);
		return caseId;
	}

	/***************************************************************************
	 * Method : Get Case ID Arguments : Return : String Exception :
	 * NullPointerException,SQLException,Exception
	 **************************************************************************/
	/*
	 * public synchronized String getCaseID() throws
	 * NullPointerException,SQLException,Exception{ String caseID = null;
	 * DBUtility caseidDB = null; try{ caseidDB = new DBUtility();
	 * caseidDB.createStatement(); caseID =
	 * caseidDB.executeQry(CaseMonSQL.IGRS_CASE_ID_GET); if(caseID != null){
	 * return caseID; } else{ return null; } }catch (NullPointerException ne) {
	 * logger.error(" NullPointer Exception at Creating " + " Statement in
	 * getCaseID() in DAO : " + ne); }catch (SQLException se) { logger.error("
	 * SQL Exception at Creating " + " Statement in getCaseID() in DAO : " +
	 * se); }catch (Exception e) { logger.error(" Exception at Creating " + "
	 * Statement in getCaseID() in DAO : " + e); }finally{ try{
	 * caseidDB.closeConnection(); }catch (SQLException se) { logger.error("SQL
	 * Exception at close Db Connection at getCaseEstampAmt() : " + se); } }
	 * logger.debug("33333 Final DAO retrun --> " + caseID); return caseID; }
	 */
	/***************************************************************************
	 * Method : Get Case Type ID Arguments : Return : String Exception :
	 * NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized String getCaseTypeId(String _caseType)
			throws NullPointerException, SQLException, Exception {
		String caseTpeID = null;
		DBUtility caseTypeidDB = null;
		try {
			caseTypeidDB = new DBUtility();
			caseTypeidDB.createStatement();
			String caseTypeQry = CaseMonSQL.IGRS_CASE_TYPE_ID + "'" + _caseType
					+ "'";
			caseTpeID = caseTypeidDB.executeQry(caseTypeQry);
			if (caseTpeID != null) {
				return caseTpeID;
			} else {
				return null;
			}
		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in getCaseTypeId() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in getCaseTypeId() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in getCaseTypeId() in DAO  : " + e);
		} finally {
			try {
				caseTypeidDB.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseTypeId() : "
								+ se);
			}
		}
		//logger.debug("33333 Final DAO retrun -->  " + caseTpeID);
		return caseTpeID;
	}

	/***************************************************************************
	 * Method : Get Case Action ID Arguments : Return : String Exception :
	 * NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized String getCaseActionId(String _caseAction)
			throws NullPointerException, SQLException, Exception {
		String caseID = null;
		DBUtility caseidDB = null;
		try {
			caseidDB = new DBUtility();
			String caseActionQry = CaseMonSQL.IGRS_CASE_ACTION_ID_GET + "'"
					+ _caseAction + "'";
			caseidDB.createStatement();
			caseID = caseidDB.executeQry(caseActionQry);
			if (caseID != null) {
				return caseID;
			} else {
				return null;
			}
		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in getCaseActionId() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in getCaseActionId() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in getCaseActionId() in DAO  : " + e);
		} finally {
			try {
				caseidDB.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
								+ se);
			}
		}
		//logger.debug("33333 Final DAO retrun -->  " + caseID);
		return caseID;
	}

	/***************************************************************************
	 * Method : setCaseComments Arguments : CaseID Return : boolean Exception :
	 * NullPointerException,SQLException,Exception
	 * 
	 * @param userId
	 * @param funId
	 * @param roleId
	 **************************************************************************/
	public synchronized boolean setCaseComments(String _caseID,
			String _comments, String _caseactionTxnID, String roleId,
			String funId, String userId) throws NullPointerException,
			SQLException, Exception {
		boolean comment = false;
		DBUtility commentsDb = null;
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			commentsDb = new DBUtility();
			commentsDb
					.createPreparedStatement(CaseMonSQL.IGRS_CASE_LIST_COMMENTS);
			String[] caseComments = new String[3];
			caseComments[0] = _caseID;
			caseComments[1] = _comments;
			caseComments[2] = _caseactionTxnID;
			comment = commentsDb.executeUpdate(caseComments);
			if (comment != false) {
				igrsCommon.saveLogDet("igrs_case_txn_comments", "INSERT", "T",
						funId, userId, roleId);
				return comment;
			} else {
				igrsCommon.saveLogDet("igrs_case_txn_comments", "INSERT", "F",
						funId, userId, roleId);
				return false;
			}
		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in setCaseComments() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in setCaseComments() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in setCaseComments() in DAO  : " + e);
		} finally {
			try {
				commentsDb.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at setCaseComments() : "
								+ se);
			}
		}
		//logger.debug("33333 Final DAO retrun -->  " + comment);
		return comment;
	}

	/***************************************************************************
	 * Method : setactionMap Arguments : CaseID , actionId and caseActionTxnId
	 * Return : boolean Exception : NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized boolean setactionMap(String _caseId, String _actionId,
			String _caseActionTxnID, String _notice)
			throws NullPointerException, SQLException, Exception {
		boolean caseAxnTxnMap = false;
		DBUtility caseActionMpDB = null;
		try {
			caseActionMpDB = new DBUtility();
			caseActionMpDB
					.createPreparedStatement(CaseMonSQL.IGRS_CASE_ACTION_MAP);
			String[] caseActionMap = new String[4];
			caseActionMap[0] = _caseId;
			caseActionMap[1] = _actionId;
			caseActionMap[2] = _caseActionTxnID;
			caseActionMap[3] = _notice;
			caseAxnTxnMap = caseActionMpDB.executeUpdate(caseActionMap);
			if (caseAxnTxnMap != false) {
				caseActionMpDB.commit();
				return caseAxnTxnMap;
			} else {
				return false;
			}
		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in setactionMap() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in setactionMap() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in setactionMap() in DAO  : " + e);
		} finally {
			try {
				caseActionMpDB.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at setactionMap() : "
								+ se);
			}
		}
		//logger.debug("33333 Final DAO retrun -->  " + caseAxnTxnMap);
		return caseAxnTxnMap;
	}

	// Strat

	/***************************************************************************
	 * Method : caseHeadStack Arguments : From Date and ToDate Return :
	 * ArrayList Exception : NullPointerException,SQLException,Exception Author
	 * : Piyush
	 **************************************************************************/
	// $01 Method for Retrieving CaseHead from IGRS_CASE_HEAD_MASTER
	public ArrayList caseHeadStack(String language,String role) throws NullPointerException, Exception,
			Exception {

		ArrayList ar1 = new ArrayList();

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseHeadStack");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str="";
			if("en".equalsIgnoreCase(language))
			{
				if(role!=null && !role.equalsIgnoreCase(""))
				{
					if("IG".equalsIgnoreCase(role))
					{
						str = CaseMonSQL.IGRS_CASE_HEAD_MASTER_IG;
					}
					else if("DR".equalsIgnoreCase(role))
					{
						str = CaseMonSQL.IGRS_CASE_HEAD_MASTER; // Query for Case
					}
					
				}
			
			}
			else
			{
				if("IG".equalsIgnoreCase(role))
				{
					str = CaseMonSQL.IGRS_CASE_HEAD_MASTER_H_IG;
				}
				else if("DR".equalsIgnoreCase(role))
				{
					str = CaseMonSQL.IGRS_CASE_HEAD_MASTER_H;
				}
			
			}
			// Head list from
			// IGRS_CASE_MASTER_MASTER
			dbUtil.createStatement();
			typeList = dbUtil.executeQuery(str);
			//logger.info("CASE_HEAD_LIST:=" + typeList);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setCaseHeadId((String) typeTemp.get(0));
						type.setCaseHeadName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $01 End
	/***************************************************************************
	 * Method : CaseTypeStack Arguments : CaseHeadId Return : ArrayList
	 * Exception : NullPointerException,SQLException,Exception Author : Piyush
	 **************************************************************************/
	// $02 Method for Retrieving CaseHead from IGRS_CASE_HEAD_MASTER
	public ArrayList caseTypeStack(String _caseHeadId,String language,String role)
			throws NullPointerException, Exception, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseHeadStack");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String param[] = new String[1];
			param[0] = _caseHeadId;
			String str="";
			if("en".equalsIgnoreCase(language))
			{
				if(role!=null && !role.equals(""))
				{
					if("IG".equalsIgnoreCase(role))
					{
						str = CaseMonSQL.IGRS_CASE_TYPE_MASTER_IG; // Query for Case
					}
					else if("DR".equalsIgnoreCase(role))
					{
						str = CaseMonSQL.IGRS_CASE_TYPE_MASTER; // Query for Case
					}
				}
			}
			else
			{
				if(role!=null && !role.equals(""))
				{
					if("IG".equalsIgnoreCase(role))
					{
						str = CaseMonSQL.IGRS_CASE_TYPE_MASTER_H_IG; // Query for Case
					}
					else if("DR".equalsIgnoreCase(role))
					{
						str = CaseMonSQL.IGRS_CASE_TYPE_MASTER_H;
					}
				}
			
			}
			// Head list from
			// IGRS_CASE_MASTER_MASTER
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			//logger.info("CASE_TYPE_LIST:=" + typeList);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setCaseTypeId((String) typeTemp.get(0));
						type.setCaseTypeName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger
						.error("Exception at close Connection at CaseTypeStack in DAO  : "
								+ e);

			}
		}
		return ar1;
	}

	// $07 Method for Retrieving CaseAction from IGRS_CASE_ACTION_MASTER using
	// IGRS_CASE_TYPE_ACTION_MAP
	public ArrayList caseActionTypeStackDAO(String _caseTypeId)
			throws Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseActionTypeStackDAO");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_ACTION_MASTER;
			String param[] = new String[1];
			param[0] = _caseTypeId;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			//logger.info("CASE_ACTION_LIST:=" + typeList);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setCaseActionTypeId((String) typeTemp.get(0));
						type.setCaseActionTypeName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// added by shruti
	public ArrayList caseLastAction(String _caseTxnId) throws Exception {

		String actionid = "";
		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseActionTypeStackDAO");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_LAST_ACTION;
			String param[] = new String[2];
			param[0] = _caseTxnId;
			param[1] = _caseTxnId;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			//logger.info("CASE_ACTION_LIST:=" + typeList);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setCaseActionTypeId((String) typeTemp.get(0));
						type.setCaseActionTypeName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}

		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// end
	public ArrayList viewDataRetrieveDAO(String _caseTxnId)
			throws NullPointerException, SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseRetrieveCommentsDAO");
		ArrayList ar1 = new ArrayList();

		try {
			// modified by shruti-restruc
			ArrayList generalList = new ArrayList();
			dbUtil = new DBUtility();
			String qry1 = CaseMonSQL.IGRS_CASE_TXN_DETAILSFOR_VIEW;
			String param1[] = new String[1];
			param1[0] = "" + _caseTxnId;
			dbUtil.createPreparedStatement(qry1);
			generalList = dbUtil.executeQuery(param1);
			
			
			if(generalList.size()==0)
			{
				String qry2 = CaseMonSQL.IGRS_CASE_TXN_DETAILS_REFUND_VIEW;
				String param2[] = new String[1];
				param2[0] = "" + _caseTxnId;
				dbUtil.createPreparedStatement(qry2);
				generalList = dbUtil.executeQuery(param2);	
			}
			
			//logger.debug("Generallist_________*******************  __________________"+ generalList);
			ArrayList generalList2 = (ArrayList) generalList.get(0);
			CaseMonDTO type1 = new CaseMonDTO();
			type1.setCaseActionTypeId((String) generalList2.get(0));
			type1.setCaseHeadName((String) generalList2.get(1));
			type1.setCaseTypeId((String) generalList2.get(2));
			type1.setRevenueHeadName((String) generalList2.get(3));
			type1.setSectionHeadName((String) generalList2.get(4));
			type1.setCaseStatus((String) generalList2.get(5));
			// type1.setCaseCloseDate((String) generalList2.get(6));
			type1.setFinalCaseComments((String) generalList2.get(6));
			// type1.setCasePenalty((String) generalList2.get(8));
			type1.setCaseCreatedDate((String) generalList2.get(7));
			type1.setCaseTypeName((String) generalList2.get(8));
			type1.setCaseTxnId(_caseTxnId);
			String param2[] = new String[3];
			param2[0] = "" + (String) generalList2.get(2);
			param2[1] = "" + (String) generalList2.get(2);
			param2[2] = "" + _caseTxnId;
			type1.setMajorList(fnDetailStack(param2));
			ar1.add(type1);
			//logger.debug("Generallist_________ADDED ++++++++++++++++++++++  ________");

		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		logger.debug("FINALLLL  ARAY.......................+++=============");
		logger.debug("LIST " + ar1);
		logger.debug("FINALLLL  ARAY.......................+++=============");

		return ar1;
	}

	public ArrayList fnDetailStack(String param[]) throws NullPointerException,
			SQLException, Exception {
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseRetrieveCommentsDAO");
		ArrayList ar1 = new ArrayList();

		try {
			dbUtil = new DBUtility();
			ArrayList arrAxnList = new ArrayList();
			String qry2 = CaseMonSQL.IGRS_CASE_AXNID_RETRIEVEFOR_VIEW;
			String caseTxnId = param[2];
			dbUtil.createPreparedStatement(qry2);
			arrAxnList = dbUtil.executeQuery(param);
			/*logger.debug("arrAxnList _________ *******************  ________"
					+ arrAxnList);*/
			if (arrAxnList != null) {
				for (int i = 0; i < arrAxnList.size(); i++) {
					ArrayList arrAxnList2 = (ArrayList) arrAxnList.get(i);
					CaseMonDTO type2 = new CaseMonDTO();
					if (arrAxnList2 != null && arrAxnList2.size() > 0) {
						type2.setCaseActionTypeId((String) arrAxnList2.get(0));
						type2
								.setCaseActionTypeName((String) arrAxnList2
										.get(1));
						type2.setListRemarks(fnRemarksStack(
								(String) arrAxnList2.get(0), caseTxnId));// /
						// Calling
						// Function1
						// for
						// REMARKS.....
						ar1.add(type2);
					}
				}
			}
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		logger.debug("FINALLLL  ARAY.......................+++=============");
		//logger.debug("LIST " + ar1);
		logger.debug("FINALLLL  ARAY.......................+++=============");

		return ar1;
	}

	public ArrayList fnRemarksStack(String _axnId, String _caseTxnId)
			throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-fnRemarksStack");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str3 = CaseMonSQL.IGRS_CASE_AXN_REMARKSFOR_VIEW;
			dbUtil.createPreparedStatement(str3);
			String param3[] = new String[2];
			param3[0] = _axnId;
			param3[1] = _caseTxnId;
			typeList = dbUtil.executeQuery(param3);
			/*logger.debug("typeList _________ *******************  ________"
					+ typeList);*/
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type3 = new CaseMonDTO();
						type3.setAxnDescr((String) typeTemp.get(1));
						type3.setAxnDescrDate((String) typeTemp.get(2));
						type3.setReceivingDate((String) typeTemp.get(2));
						type3.setListComments(fnCommentsStack((String) typeTemp
								.get(0)));
						// / Calling Function2 for Multiple
						// Comments.....
						ar1.add(type3);
					}
				}
			}
			/*logger.debug("typeList ______ADDED___ +++++++++++++++++  ________"
					+ typeList);*/
			return ar1;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		finally {
			try {
				dbUtil.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseIds() : "
								+ se);
			}
		}
	}

	public ArrayList fnCommentsStack(String _caseAxnTxnId)
			throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-fnCommentsStack");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str3 = CaseMonSQL.IGRS_CASE_TXN_COMMENTSFOR_VIEW;
			dbUtil.createPreparedStatement(str3);
			String param4[] = new String[1];
			param4[0] = _caseAxnTxnId;
			typeList = dbUtil.executeQuery(param4);
			/*logger
					.debug("typeList fo Comments_________ *******************  ________"
							+ typeList);*/
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type4 = new CaseMonDTO();
						type4.setDrComments((String) typeTemp.get(1));
						type4.setDrCommentsDate((String) typeTemp.get(2));
						ar1.add(type4);
					}
				}
			}
		/*	logger
					.debug("typeList for Comments ______ADDED___ +++++++++++++++++  ________"
							+ typeList);*/
			return ar1;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		finally {
			try {
				dbUtil.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseIds() : "
								+ se);
			}
		}
	}

	public boolean addComplianceDAO(String param[], String roleId,
			String funId, String userId) throws NullPointerException,
			SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseRetrieveCommentsDAO");
		try {

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_UPDATE_COMPLIANCE;
			dbUtil.createPreparedStatement(str);
			return dbUtil.executeUpdate(param);
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
			return false;
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}

	public ArrayList searchListAddCompDAO(String param[])
			throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-searchListViewDAO");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_SEARCH_LIST_FOR_ADDCOMP;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			/*logger.info("CASE_SEARCH_LIST:=" + typeList);*/
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setCaseTxnId((String) typeTemp.get(0));
						type.setLicenseId((String) typeTemp.get(1));
						type.setCaseCreatedDate((String) typeTemp.get(2));
						type.setCaseStatus((String) typeTemp.get(3));
						type.setCaseActionTypeName((String) typeTemp.get(4));
						type.setSerialNo(i + 1);
						ar1.add(type);
					}
				}
			}
			return ar1;
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return null;
	}

	// End Start

	// $01 Method for Retrieving CaseHead from IGRS_CASE_HEAD_MASTER
	public ArrayList caseHeadStackDAO() throws ServletException, IOException,
			Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseHeadStack");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_HEAD_MASTER; // Query for Case
			// Head list from
			// IGRS_CASE_MASTER_MASTER
			dbUtil.createStatement();
			typeList = dbUtil.executeQuery(str);
		/*	logger.info("CASE_HEAD_LIST:=" + typeList);*/
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setCaseHeadId((String) typeTemp.get(0));
						type.setCaseHeadName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $01 End
	// $02 Method for Retrieving CaseHead from IGRS_CASE_HEAD_MASTER
	public ArrayList caseTypeStackDAO(String _caseHeadId)
			throws ServletException, IOException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseHeadStack");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String param[] = new String[1];
			param[0] = _caseHeadId;
			String str = CaseMonSQL.IGRS_CASE_TYPE_MASTER; // Query for Case
			// Head list from
			// IGRS_CASE_MASTER_MASTER
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			/*logger.info("CASE_TYPE_LIST:=" + typeList);*/
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setCaseTypeId((String) typeTemp.get(0));
						type.setCaseTypeName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $02 End
	// $03 Method for Retrieving SectionHead from IGRS_CASE_REVENUE_HEAD_MASTER
	public ArrayList revenueTypeStackDAO() throws ServletException,
			IOException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-revenueHeadStack");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_REVENUE_HEAD_MASTER;
			dbUtil.createStatement();
			typeList = dbUtil.executeQuery(str);
			/*logger.info("REVENUE_HEAD_LIST:=" + typeList);*/
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setRevenueHeadId((String) typeTemp.get(0));
						type.setRevenueHeadName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $03 End
	// $04 Method for Retrieving SectionHead from IGRS_CASE_SECTION_HEAD_MASTER
	public ArrayList sectionTypeStackDAO() throws ServletException,
			IOException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-sectionTypeStackDAO");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_SECTION_HEAD_MASTER;
			dbUtil.createStatement();
			typeList = dbUtil.executeQuery(str);
		/*	logger.info("SECTION_HEAD_LIST:=" + typeList);*/
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setSectionHeadId((String) typeTemp.get(0));
						type.setSectionHeadName((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			logger.error("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		}
		return ar1;
	}

	// $04 End
	// $05
	public ArrayList licenseDetailsDAO(String param[])
			throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-licenseDetailsDAO");
		try {

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_LICENSEID_SEARCH;
			dbUtil.createPreparedStatement(str);
			ar1 = dbUtil.executeQuery(param);
		} catch (NullPointerException ne) {
			// TODO: handle exception
		} catch (SQLException se) {
			// TODO: handle exception
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return ar1;
	}

	// $05 End
	// $06
	public boolean licenseCaseInsertDAO(String param1[], String param2[],
			String param3[]) throws NullPointerException, SQLException,
			Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-licenseCaseInsertDAO");
		try {

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_LICENSE_INSERT;
			dbUtil.createPreparedStatement(str);
			if (dbUtil.executeUpdate(param1)) {
				String str2 = CaseMonSQL.IGRS_CASE_AXN_DETAILS_INSERT;
				dbUtil.createPreparedStatement(str2);
				if (dbUtil.executeUpdate(param2)) {
					String str3 = CaseMonSQL.IGRS_CASE_COMMENTS_DETAILS_INSERT;
					dbUtil.createPreparedStatement(str3);
					if (dbUtil.executeUpdate(param3)) {
						return true;
					} else
						return false;
				} else
					return false;
			}

		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}

	// $08 End
	public ArrayList searchListUpdateDAO(String param[], String sql)
			throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-searchListUpdateDAO");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			typeList = dbUtil.executeQuery(param);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setCaseTxnId((String) typeTemp.get(0));
						type.setCaseCreatedDate((String) typeTemp.get(1));
						type.setLastAction((String) typeTemp.get(2));
						type.setSerialNo(i + 1);
						type.setRrcId((String) typeTemp.get(3));
						type.setHiLastAction((String) typeTemp.get(4));
						type.setHdnCaseParam((String) typeTemp.get(0)+"~"+(String) typeTemp.get(2));
						ar1.add(type);
					}
				}
			}
			return ar1;
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return null;
	}

	// $08 End
	// $09

	public ArrayList searchListTimeBarredDAO(String param[], String sql)
	throws NullPointerException, SQLException, Exception {

ArrayList ar1 = new ArrayList();
DBUtility dbUtil = null;
logger.debug("WE ARE IN DAO-searchListUpdateDAO");
try {
	ArrayList typeList = new ArrayList();
	ArrayList typeTemp = new ArrayList();

	dbUtil = new DBUtility();
	dbUtil.createPreparedStatement(sql);
	typeList = dbUtil.executeQuery(param);
	if (typeList != null) {
		for (int i = 0; i < typeList.size(); i++) {
			typeTemp = new ArrayList();
			typeTemp = (ArrayList) typeList.get(i);
			if (typeTemp.size() > 0) {
				CaseMonDTO type = new CaseMonDTO();
				type.setCaseTxnId((String) typeTemp.get(0));
				type.setCaseCreatedDate((String) typeTemp.get(1));
				type.setLastAction((String) typeTemp.get(2));
				type.setHiLastAction((String) typeTemp.get(3));
				type.setSerialNo(i + 1);
				type.setHdnCaseParam((String) typeTemp.get(0)+"~"+(String) typeTemp.get(2));
				ar1.add(type);
			}
		}
	}
	return ar1;
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
	return null;
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
return null;
}

	public ArrayList searchListRefundDAO(String param[], String sql)
	throws NullPointerException, SQLException, Exception {

ArrayList ar1 = new ArrayList();
DBUtility dbUtil = null;
logger.debug("WE ARE IN DAO-searchListUpdateDAO");
try {
	ArrayList typeList = new ArrayList();
	ArrayList typeTemp = new ArrayList();

	dbUtil = new DBUtility();
	dbUtil.createPreparedStatement(sql);
	typeList = dbUtil.executeQuery(param);
	if (typeList != null) {
		for (int i = 0; i < typeList.size(); i++) {
			typeTemp = new ArrayList();
			typeTemp = (ArrayList) typeList.get(i);
			if (typeTemp.size() > 0) {
				CaseMonDTO type = new CaseMonDTO();
				type.setCaseTxnId((String) typeTemp.get(0));
				type.setCaseCreatedDate((String) typeTemp.get(1));
				type.setLastAction((String) typeTemp.get(2));
				type.setSerialNo(i + 1);
				type.setHdnCaseParam((String) typeTemp.get(0)+"~"+(String) typeTemp.get(2));
				ar1.add(type);
			}
		}
	}
	return ar1;
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
	return null;
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
return null;
}

	public String keepAxnIdDAO() throws NullPointerException, SQLException,
			Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-keepAxnIdDAO");
		try {

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_AXNID_CATCH;
			dbUtil.createStatement();
			return dbUtil.executeQry(str);
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return null;
	}

	// $09
	// $10
	public ArrayList caseSelectDetailsDAO(String _caseTxnId)
			throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseSelectDetailsDAO");
		try {

			dbUtil = new DBUtility();
			String param[] = new String[1];
			param[0] = _caseTxnId;
			String str = CaseMonSQL.IGRS_CASE_LICENSE_DETAILS;
			dbUtil.createPreparedStatement(str);
			return dbUtil.executeQuery(param);
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return null;
	}
	//added -16th july 2013
	public ArrayList recoverableAmtDetailsDAO(String _caseTxnId)
	throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseSelectDetailsDAO");
		try {
		
			dbUtil = new DBUtility();
			String param[] = new String[1];
			param[0] = _caseTxnId;
			String param1[]=new String[2];
			param1[0]=_caseTxnId;
			param1[1]=_caseTxnId;
			String str = CaseMonSQL.IGRS_RECOVERABLE_AMT_CASE_TXN_COMMENTS;
			dbUtil.createPreparedStatement(str);
			ar1=dbUtil.executeQuery(param1);
			if(ar1.size()==0)
			{
				String str1 = CaseMonSQL.IGRS_RECOVERABLE_AMT_CASE_TXN_DETAILS;
				dbUtil.createPreparedStatement(str1);
				ar1=dbUtil.executeQuery(param);
			}
			return ar1;
			//return dbUtil.executeQuery(param);
	
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
//return null;
return ar1;
}
//end-16th july 2013
	
	
	//added by shruti---13 oct 2014
	public ArrayList adeshikaShulkAmtDetailsDAO(String _caseTxnId)
	throws NullPointerException, SQLException, Exception {
		ArrayList temp=new ArrayList();
		
		DBUtility dbUtil = null;
		ArrayList ar1=null;
		logger.debug("WE ARE IN DAO-adeshikaShulkAmtDetailsDAO");
		try {
		
			dbUtil = new DBUtility();
			String param[] = new String[1];
			param[0] = _caseTxnId;
			String str = CaseMonSQL.RRC_NO_CHK;
			dbUtil.createPreparedStatement(str);
			temp=dbUtil.executeQuery(param);
			
			if(temp!=null )
			{
				for(int i=0;i<temp.size();i++)
				{
					ar1 = new ArrayList();
					ar1 = (ArrayList) temp.get(i);
					if(!ar1.contains(null))
					{
						String str1 = CaseMonSQL.ADESHIKA_AMT_CHK;
						dbUtil.createStatement();
						ar1=dbUtil.executeQuery(str1);
					}
					else// zero amount for adeshika shulk
					{
					ar1.remove(0);	//remove null from arraylist
					ar1.add("0");//add element 0 in the arraylist;
					ArrayList a=new ArrayList();
					a.add(ar1);//add arraylist to new arraylist;
					ar1=a;
					}
				}
			}	
			return ar1;
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
//return null;
return ar1;
}
	//end
	//adedd by shruti for refund case
	public ArrayList caseSelectDetailsDAO1(String _caseTxnId)
	throws NullPointerException, SQLException, Exception {

ArrayList ar1 = new ArrayList();
DBUtility dbUtil = null;
logger.debug("WE ARE IN DAO-caseSelectDetailsDAO");
try {

	dbUtil = new DBUtility();
	String param[] = new String[1];
	param[0] = _caseTxnId;
	String str = CaseMonSQL.IGRS_CASE_LICENSE_DETAILS1;
	dbUtil.createPreparedStatement(str);
	return dbUtil.executeQuery(param);
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
return null;
}
	//end
	
	//adedd by shruti for penalty case under sec 45
	public ArrayList caseSelectDetailsPenalty45DAO(String _caseTxnId)
	throws NullPointerException, SQLException, Exception {

ArrayList ar1 = new ArrayList();
DBUtility dbUtil = null;
logger.debug("WE ARE IN DAO-caseSelectDetailsDAO");
try {

	dbUtil = new DBUtility();
	String param[] = new String[1];
	param[0] = _caseTxnId;
	String str = CaseMonSQL.IGRS_CASE_PENALTY45_DETAILS;
	dbUtil.createPreparedStatement(str);
	return dbUtil.executeQuery(param);
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
return null;
}
	//end
	
	//adedd by shruti for penalty case under sec 45
	public ArrayList caseSelectDetailsPenalty70DAO(String _caseTxnId)
	throws NullPointerException, SQLException, Exception {

ArrayList ar1 = new ArrayList();
DBUtility dbUtil = null;
logger.debug("WE ARE IN DAO-caseSelectDetailsDAO");
try {

	dbUtil = new DBUtility();
	String param[] = new String[1];
	param[0] = _caseTxnId;
	String str = CaseMonSQL.IGRS_CASE_PENALTY70_DETAILS;
	dbUtil.createPreparedStatement(str);
	return dbUtil.executeQuery(param);
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
return null;
}
	//end
	//adedd by shruti for others case
	public ArrayList caseSelectDetailsDAO2(String _caseTxnId)
	throws NullPointerException, SQLException, Exception {

ArrayList ar1 = new ArrayList();
DBUtility dbUtil = null;
logger.debug("WE ARE IN DAO-caseSelectDetailsDAO");
try {

	dbUtil = new DBUtility();
	String param[] = new String[1];
	param[0] = _caseTxnId;
	String str = CaseMonSQL.IGRS_CASE_LICENSE_DETAILS2;
	dbUtil.createPreparedStatement(str);
	return dbUtil.executeQuery(param);
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
return null;
}
	//end
	
	
	
	
	

	// $10End
	// $11 Start
	public ArrayList caseRetrieveCommentsDAO(String param[])
			throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseRetrieveCommentsDAO");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_COMMENTS_RETRIEVE;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			/*logger.info("CASE_COMMENTS_LIST:=" + typeList);*/
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setDrComments((String) typeTemp.get(0));
						type.setDrCommentsDate((String) typeTemp.get(1));
						ar1.add(type);
					}
				}
			}
			return ar1;
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return null;
	}
	
	public ArrayList caseRetrieveAttachmentsDAO(String param[])
	throws NullPointerException, SQLException, Exception {

ArrayList ar1 = new ArrayList();
DBUtility dbUtil = null;
logger.debug("WE ARE IN DAO-caseRetrieveAttachmentsDAO");
try {
	ArrayList typeList = new ArrayList();
	ArrayList typeTemp = new ArrayList();

	dbUtil = new DBUtility();
	String str = CaseMonSQL.IGRS_CASE_ATTACHMENTS_RETRIEVE;
	dbUtil.createPreparedStatement(str);
	typeList = dbUtil.executeQuery(param);
	/*logger.info("CASE_COMMENTS_LIST:=" + typeList);*/
	if (typeList != null) {
		for (int i = 0; i < typeList.size(); i++) {
			typeTemp = new ArrayList();
			typeTemp = (ArrayList) typeList.get(i);
			if (typeTemp.size() > 0) {
				CaseMonDTO type = new CaseMonDTO();
				type.setDrComments((String) typeTemp.get(0));       //attachment path goes here
				type.setDrCommentsDate((String) typeTemp.get(1));   //attachment date goes here
				ar1.add(type);
			}
		}
	}
	return ar1;
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
	return null;
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
return null;
}

	// $11 End
	// $12
	public boolean commentsUpdateDAO(String param1[], String param2[],
			String param7[], HashMap map) throws NullPointerException,
			SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-commentsUpdateDAO");
		try {
			dbUtil = new DBUtility();
			//String str = CaseMonSQL.IGRS_CASE_AXN_DETAILS_INSERT;
			//dbUtil.createPreparedStatement(str);
			//if (dbUtil.executeUpdate(param1)) 
			//{
				String str2 = CaseMonSQL.IGRS_CASE_COMMENTS_DETAILS_INSERT;
				dbUtil.createPreparedStatement(str2);
				// return dbUtil.executeUpdate(param2);
				if (dbUtil.executeUpdate(param2)) 
				{
					CaseMonDAO dao = new CaseMonDAO();
					return dao.insertDoc(map, param7);
				}
			//}
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}

	// $12End
	// $13
	public boolean issueNoticeDAO(String param3[], String param4[],
			String param5[], String param7[], HashMap map)
			throws NullPointerException, SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-issueNoticeDAO");
		try {
			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_AXN_DESCRIPTION_INSERT;
			dbUtil.createPreparedStatement(str);
			if (dbUtil.executeUpdate(param3)) {
				String str2 = CaseMonSQL.IGRS_CASE_ACTIONID_UPDATE;
				dbUtil.createPreparedStatement(str2);
				if (dbUtil.executeUpdate(param4) && param5 != null)
					if (param5 != null) {
						String str3 = CaseMonSQL.IGRS_CASE_COMMENTS_DETAILS_INSERT;
						dbUtil.createPreparedStatement(str3);
						// return dbUtil.executeUpdate(param5);
						if (dbUtil.executeUpdate(param5)) {
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
						}
					}
			}
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}
	
	//adedd by shruti-15th july 2013
	public boolean getPaidAmtUpdateDAO(String param[])
			throws NullPointerException, SQLException, Exception {

		boolean flag=false;
		
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-issueNoticeDAO");
		try {
			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_FINALAMOUNT_UPDATE;
			dbUtil.createPreparedStatement(str);
			flag=dbUtil.executeUpdate(param);
			
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return flag;
	}//end
	
	
	
	
	//adedd by shruti for refund cases
	
	public boolean issueNoticeDAO1(String param3[], String param4[],
			String param5[], String param7[], HashMap map)
			throws NullPointerException, SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-issueNoticeDAO");
		try {
			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_AXN_DESCRIPTION_INSERT;
			dbUtil.createPreparedStatement(str);
			if (dbUtil.executeUpdate(param3)) {
				String str2 = CaseMonSQL.IGRS_CASE_ACTIONID_UPDATE1;
				dbUtil.createPreparedStatement(str2);
				if (dbUtil.executeUpdate(param4) && param5 != null)
					if (param5 != null) {
						String str3 = CaseMonSQL.IGRS_CASE_COMMENTS_DETAILS_INSERT;
						dbUtil.createPreparedStatement(str3);
						// return dbUtil.executeUpdate(param5);
						if (dbUtil.executeUpdate(param5)) {
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
						}
					}
			}
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}
//end
	//ADEDD-SHRUTI-22ND JULY 2013
	
	public boolean issueNoticePenalty45DAO(String param3[], String param4[],
			String param5[], String param7[], HashMap map)
			throws NullPointerException, SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-issueNoticeDAO");
		try {
			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_AXN_DESCRIPTION_INSERT;
			dbUtil.createPreparedStatement(str);
			if (dbUtil.executeUpdate(param3)) {
				String str2 = CaseMonSQL.IGRS_CASE_ACTIONID_UPDATE_PENALTY45;
				dbUtil.createPreparedStatement(str2);
				if (dbUtil.executeUpdate(param4) && param5 != null)
					if (param5 != null) {
						String str3 = CaseMonSQL.IGRS_CASE_COMMENTS_DETAILS_INSERT;
						dbUtil.createPreparedStatement(str3);
						// return dbUtil.executeUpdate(param5);
						if (dbUtil.executeUpdate(param5)) {
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
						}
					}
			}
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}
//end
//ADEDD-SHRUTI-22ND JULY 2013
	
	public boolean issueNoticePenalty70DAO(String param3[], String param4[],
			String param5[], String param7[], HashMap map)
			throws NullPointerException, SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-issueNoticeDAO");
		try {
			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_AXN_DESCRIPTION_INSERT;
			dbUtil.createPreparedStatement(str);
			if (dbUtil.executeUpdate(param3)) {
				String str2 = CaseMonSQL.IGRS_CASE_ACTIONID_UPDATE_PENALTY70;
				dbUtil.createPreparedStatement(str2);
				if (dbUtil.executeUpdate(param4) && param5 != null)
					if (param5 != null) {
						String str3 = CaseMonSQL.IGRS_CASE_COMMENTS_DETAILS_INSERT;
						dbUtil.createPreparedStatement(str3);
						// return dbUtil.executeUpdate(param5);
						if (dbUtil.executeUpdate(param5)) {
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
						}
					}
			}
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}
//end


	// $13End
	// $14 Start
	public boolean closeCaseDAO(String param6[], String param1[],
			String param2[], String param7[], HashMap map)
			throws NullPointerException, SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-closeCaseDAO");
		try {
			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_CLOSE_UPDATE;
			dbUtil.createPreparedStatement(str);
			if (dbUtil.executeUpdate(param6)) {
				String str2 = CaseMonSQL.IGRS_CASE_AXN_DETAILS_INSERT;
				dbUtil.createPreparedStatement(str2);
				if (dbUtil.executeUpdate(param1) && param2 != null) {
					String str3 = CaseMonSQL.IGRS_CASE_COMMENTS_DETAILS_INSERT;
					dbUtil.createPreparedStatement(str3);
					// return dbUtil.executeUpdate(param2);
					if (dbUtil.executeUpdate(param2)) {
						CaseMonDAO dao = new CaseMonDAO();
						return dao.insertDoc(map, param7);
					}
				}
			}
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}
	
	//adedd by shruti-refund cases
	public boolean closeCaseDAO1(String param6[], String param1[],
			String param2[], String param7[], HashMap map,String param8[])
			throws NullPointerException, SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-closeCaseDAO");
		try {
			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_CLOSE_UPDATE1;
			dbUtil.createPreparedStatement(str);
			if (dbUtil.executeUpdate(param6)) {
				String str2 = CaseMonSQL.IGRS_CASE_AXN_DETAILS_INSERT;
				dbUtil.createPreparedStatement(str2);
				if (dbUtil.executeUpdate(param1) && param2 != null) {
					String str3 = CaseMonSQL.IGRS_CASE_COMMENTS_DETAILS_INSERT;
					dbUtil.createPreparedStatement(str3);
					// return dbUtil.executeUpdate(param2);
					if (dbUtil.executeUpdate(param2) && param8 !=null) {
						String str4=CaseMonSQL.SELECTESTAMPTXNID;
						dbUtil.createPreparedStatement(str4);
						String estampid=dbUtil.executeQry(param8);
						if(estampid.equalsIgnoreCase(""))
						{
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
						}
						else
						{
							String str5=CaseMonSQL.UPDATEESTAMPSTATUS;
							dbUtil.createPreparedStatement(str5);
							String[] eid={param1[6],estampid};
							if(dbUtil.executeUpdate(eid))
							{
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
							}
						}
							
						/*if((estampid!=null) ||(estampid!=""))
						{
						String str5=CaseMonSQL.UPDATEESTAMPSTATUS;
						dbUtil.createPreparedStatement(str5);
						String[] eid={estampid,param1[6]};
						if(dbUtil.executeUpdate(eid)){
						CaseMonDAO dao = new CaseMonDAO();
						return dao.insertDoc(map, param7);
						}
						}
						else
						{
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
						}*/
					}
				}
			}
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}

	//end
	//adedd by shruti-penalty cases
	public boolean closeCasePenalty45DAO(String param6[], String param1[],
			String param2[], String param7[], HashMap map,String param8[])
			throws NullPointerException, SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-closeCaseDAO");
		try {
			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_CLOSE_UPDATE_PENALTY45;
			dbUtil.createPreparedStatement(str);
			if (dbUtil.executeUpdate(param6)) {
				String str2 = CaseMonSQL.IGRS_CASE_AXN_DETAILS_INSERT;
				dbUtil.createPreparedStatement(str2);
				if (dbUtil.executeUpdate(param1) && param2 != null)
				{
					String str3 = CaseMonSQL.IGRS_CASE_COMMENTS_DETAILS_INSERT;
					dbUtil.createPreparedStatement(str3);
					if (dbUtil.executeUpdate(param2)) 
						{
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
						}
				}
			}
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}

	//end
	//adedd by shruti-penalty cases
	public boolean closeCasePenalty70DAO(String param6[], String param1[],
			String param2[], String param7[], HashMap map,String param8[])
			throws NullPointerException, SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-closeCaseDAO");
		try {
			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_CLOSE_UPDATE_PENALTY70;
			dbUtil.createPreparedStatement(str);
			if (dbUtil.executeUpdate(param6)) {
				String str2 = CaseMonSQL.IGRS_CASE_AXN_DETAILS_INSERT;
				dbUtil.createPreparedStatement(str2);
				if (dbUtil.executeUpdate(param1) && param2 != null)
				{
					String str3 = CaseMonSQL.IGRS_CASE_COMMENTS_DETAILS_INSERT;
					dbUtil.createPreparedStatement(str3);
					if (dbUtil.executeUpdate(param2)) 
						{
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
						}
				}
			}
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}

	//end
	
	//adedd by shruti-refund cases
	public boolean closeCaseDAO2(String param6[], String param1[],
			String param2[], String param7[], HashMap map,String param8[])
			throws NullPointerException, SQLException, Exception {

		DBUtility dbUtil = null;
		String[] pid={param1[2]}; 
		logger.debug("WE ARE IN DAO-closeCaseDAO");
		try {
			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_CLOSE_UPDATE2;
			dbUtil.createPreparedStatement(str);
			if (dbUtil.executeUpdate(param6)) {
				//String str2 = CaseMonSQL.IGRS_CASE_AXN_DETAILS_INSERT;
				//dbUtil.createPreparedStatement(str2);
				//if (dbUtil.executeUpdate(param1) && param2 != null) {
				if(param2!=null)
				{
					String str3 = CaseMonSQL.IGRS_CASE_COMMENTS_DETAILS_INSERT;
					dbUtil.createPreparedStatement(str3);
					if(dbUtil.executeUpdate(param2))
					{
						String str4 = CaseMonSQL.CLOSETIMEBARREDCASE;
						dbUtil.createPreparedStatement(str4);
						return dbUtil.executeUpdate(pid);
					}
					 //return dbUtil.executeUpdate(param2);
					 
					 
					/*if (dbUtil.executeUpdate(param2) && param8 !=null) {
						String str4=CaseMonSQL.SELECTESTAMPTXNID;
						dbUtil.createPreparedStatement(str4);
						String estampid=dbUtil.executeQry(param8);
						if(estampid.equalsIgnoreCase(""))
						{
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
						}
						else
						{
							String str5=CaseMonSQL.UPDATEESTAMPSTATUS;
							dbUtil.createPreparedStatement(str5);
							String[] eid={param1[6],estampid};
							if(dbUtil.executeUpdate(eid))
							{
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
							}
						}*/
							
						/*if((estampid!=null) ||(estampid!=""))
						{
						String str5=CaseMonSQL.UPDATEESTAMPSTATUS;
						dbUtil.createPreparedStatement(str5);
						String[] eid={estampid,param1[6]};
						if(dbUtil.executeUpdate(eid)){
						CaseMonDAO dao = new CaseMonDAO();
						return dao.insertDoc(map, param7);
						}
						}
						else
						{
							CaseMonDAO dao = new CaseMonDAO();
							return dao.insertDoc(map, param7);
						}*/
					//}
				}
			}
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return false;
	}

	//end
	

	// $14End
	// $15 Start
	public ArrayList nxtActionDAO(String param[]) throws NullPointerException,
			SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseRetrieveCommentsDAO");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_NEXT_PROCESS_ACTION;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			return typeList;
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return null;
	}

	// adedd by shruti
	public ArrayList nxtActionDAO1(String param[]) throws NullPointerException,
			SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseRetrieveCommentsDAO");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_NEXT_PROCESS_ACTION1;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			return typeList;
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return null;
	}

	// end
	// $15 End
	// $16 End
	public ArrayList searchListViewDAO(String param[])
			throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-searchListViewDAO");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_SEARCH_LIST_FOR_VIEW;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			logger.info("CASE_SEARCH_LIST:=" + typeList);
			if (typeList != null) {
				for (int i = 0; i < typeList.size(); i++) {
					typeTemp = new ArrayList();
					typeTemp = (ArrayList) typeList.get(i);
					if (typeTemp.size() > 0) {
						CaseMonDTO type = new CaseMonDTO();
						type.setCaseTxnId((String) typeTemp.get(0));
						type.setLicenseId((String) typeTemp.get(1));
						type.setCaseCreatedDate((String) typeTemp.get(2));
						type.setCaseStatus((String) typeTemp.get(3));
						type.setCaseActionTypeName((String) typeTemp.get(4));
						type.setSerialNo(i + 1);
						ar1.add(type);
					}
				}
			}
			return ar1;
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return null;
	}

	// $16 End

	/***************************************************************************
	 * Method : GetCaseIds Arguments : From Date and ToDate Return : ArrayList
	 * Exception : NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized ArrayList getCaseIds(String _fromDate, String _toDate,
			String _caseType, String _status) throws NullPointerException,
			SQLException, Exception {
		ArrayList caseList = null;
		DBUtility dbUtility = new DBUtility();
		try {
			caseList = new ArrayList();
			String caseTypeId = getCaseTypeId(_caseType);
			if (caseTypeId != null) {
				dbUtility.createPreparedStatement(CaseMonSQL.IGRS_CASE_TXN_IDS);
				String[] caseDates = new String[4];
				caseDates[0] = _status;
				caseDates[1] = _fromDate;
				caseDates[2] = _toDate;
				caseDates[3] = caseTypeId;
				caseList = dbUtility.executeQuery(caseDates);
			}
		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in getCaseIds() in BO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in getCaseIds() in BO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in getCaseIds() in BO  : " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseIds() : "
								+ se);
			}
		}
		return caseList;
	}

	/***************************************************************************
	 * Method : GetCaseId Details Arguments : CaseID Return : ArrayList
	 * Exception : NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized ArrayList getCaseIdDetails(String _caseId)
			throws NullPointerException, SQLException, Exception {
		ArrayList caseList = null;DBUtility dbUtility = new DBUtility();
		try {
			caseList = new ArrayList();
			String caseQry = CaseMonSQL.IGRS_CASE_TXN_LIST + " '" + _caseId
					+ "' ";
			dbUtility.createStatement();
			caseList = dbUtility.executeQuery(caseQry);

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseIdDetails() : "
								+ se);
			}
		}
		return caseList;
	}

	/***************************************************************************
	 * Method : Get Case ID Arguments : Return : String Exception :
	 * NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized String getCaseID() throws NullPointerException,
			SQLException, Exception {
		String caseID = null;
		DBUtility caseidDB = null;
		try {
			caseidDB = new DBUtility();
			caseidDB.createStatement();
			caseID = caseidDB.executeQry(CaseMonSQL.IGRS_CASE_ID_GET);
			if (caseID != null) {
				return caseID;
			} else {
				return null;
			}
		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + e);
		} finally {
			try {
				caseidDB.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
								+ se);
			}
		}
		return caseID;
	}

	/***************************************************************************
	 * Method : setactionMap Arguments : CaseID , actionId and caseActionTxnId
	 * Return : boolean Exception : NullPointerException,SQLException,Exception
	 **************************************************************************/
	public synchronized boolean setactionMap(String _caseId, String _actionId,
			String _caseActionTxnID) throws NullPointerException, SQLException,
			Exception {
		boolean caseAxnTxnMap = false;
		DBUtility caseActionMpDB = null;
		try {
			caseActionMpDB = new DBUtility();
			caseActionMpDB
					.createPreparedStatement(CaseMonSQL.IGRS_CASE_ACTION_MAP);
			String[] caseActionMap = new String[3];
			caseActionMap[0] = _caseId;
			caseActionMap[1] = _actionId;
			caseActionMap[2] = _caseActionTxnID;
			caseAxnTxnMap = caseActionMpDB.executeUpdate(caseActionMap);
			if (caseAxnTxnMap != false) {
				return caseAxnTxnMap;
			} else {
				return false;
			}
		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in setactionMap() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in setactionMap() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in setactionMap() in DAO  : " + e);
		} finally {
			try {
				caseActionMpDB.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at setactionMap() : "
								+ se);
			}
		}
		return caseAxnTxnMap;
	}

	// added by shruti

	public boolean updateActionMap(CaseMonDTO cmDTO, String userId) {
		boolean flag = false;
		String[] mapAction = new String[7];
		DBUtility dbUtil =null;
		try {
			dbUtil = new DBUtility();

			IGRSCommon sqnce = new IGRSCommon();
			String caseAxnTrId = sqnce.getSequenceNumber("IGRS_CASE_AXN_TXN",
					"CSA");
			String sql = CaseMonSQL.INSERT_ACTION_MAP_UPDATE_RECORD;
			if (cmDTO.getPartyresponse() == null) {
				cmDTO.setPartyresponse("Y");
			}
			mapAction[0] = cmDTO.getCaseTxnId();
			// mapAction[1]=cmDTO.getNxtActionId();
			mapAction[1] = cmDTO.getCaseActionTypeId();
			mapAction[2] = userId;
			mapAction[3] = "";
			mapAction[4] = caseAxnTrId;
			mapAction[5] = userId;
			mapAction[6] = cmDTO.getPartyresponse();

			dbUtil.createPreparedStatement(sql);
			flag = dbUtil.executeUpdate(mapAction);

		} catch (NullPointerException ne) {
			logger
					.error("NullPointer Exception at update case action map in CaseMonBD : "
							+ ne);
		} catch (Exception e) {
			logger
					.error("Exception at update case action map in CaseMonBD  :  "
							+ e);
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception se) {
				logger
						.error("Exception at close Db Connection  at updateActionMap() : "
								+ se);
			}
		}
		return flag;

	}

	// end

	public synchronized ArrayList getNxtActnDtls(String lastaction,
			String caseTxnId) throws NullPointerException, SQLException,
			Exception {
		DBUtility dbUtility =null;
		ArrayList caseList = null;
		try {
			dbUtility = new DBUtility();

			String[] params = new String[2];
			// params[0]=lastaction;
			params[0] = caseTxnId;
			params[1] = caseTxnId;
			caseList = new ArrayList();
			// String caseQry = CaseMonSQL.GETNEXTACTNDTLS;
			dbUtility.createPreparedStatement(CaseMonSQL.GETNEXTACTNDTLS);
			caseList = dbUtility.executeQuery(params);

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating prepared "
					+ " Statement in getCaseIdDetails() in BO  : " + e);
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseIdDetails() : "
								+ se);
			}
		}
		return caseList;
	}

	// end

	public synchronized String getActionMapSrNo() throws NullPointerException,
			SQLException, Exception {
		String caseID = null;
		DBUtility caseidDAO = null;
		try {
			caseidDAO = new DBUtility();
			caseidDAO.createStatement();
			caseID = caseidDAO.executeQry(CaseMonSQL.GETACTIONMAPSRNO);
			if (caseID != null) {
				return caseID;
			} else {
				return null;
			}
		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + e);
		} finally {
			try {
				caseidDAO.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
								+ se);
			}
		}
		return caseID;
	}

	public synchronized String getCaseTxnSrNo() throws NullPointerException,
			SQLException, Exception {

		String caseID = null;
		DBUtility caseidDAO = null;
		try {
			caseidDAO = new DBUtility();
			caseidDAO.createStatement();
			caseID = caseidDAO.executeQry(CaseMonSQL.GETCASETXNSRNO);
			if (caseID != null) {
				return caseID;
			} else {
				return null;
			}

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + e);
		} finally {
			try {
				caseidDAO.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
								+ se);
			}
		}
		return caseID;
	}

	public synchronized String getCaseCmntsSrNo() throws NullPointerException,
			SQLException, Exception {

		String caseID = null;
		DBUtility caseidDAO = null;
		try {
			caseidDAO = new DBUtility();
			caseidDAO.createStatement();
			caseID = caseidDAO.executeQry(CaseMonSQL.GETCASECMNTSSRNO);
			if (caseID != null) {
				return caseID;
			} else {
				return null;
			}

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + e);
		} finally {
			try {
				caseidDAO.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
								+ se);
			}
		}
		return caseID;
	}

	//adedd by shruti-20th july 2013
	public synchronized String getPenaltySrNo() throws NullPointerException,
	SQLException, Exception {

String caseID = null;
DBUtility caseidDAO = null;
try {
	caseidDAO = new DBUtility();
	caseidDAO.createStatement();
	caseID = caseidDAO.executeQry(CaseMonSQL.GETCASEPENALTYSRNO);
	if (caseID != null) {
		return caseID;
	} else {
		return null;
	}

} catch (NullPointerException ne) {
	logger.error(" NullPointer Exception at Creating  "
			+ " Statement in getCaseID() in DAO  : " + ne);
} catch (SQLException se) {
	logger.error(" SQL Exception at Creating  "
			+ " Statement in getCaseID() in DAO  : " + se);
} catch (Exception e) {
	logger.error(" Exception at Creating  "
			+ " Statement in getCaseID() in DAO  : " + e);
} finally {
	try {
		caseidDAO.closeConnection();
	} catch (SQLException se) {
		logger
				.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
						+ se);
	}
}
return caseID;
}
	
	//adedd by shruti-20th july 2013
	public synchronized String getPenaltySec70SrNo() throws NullPointerException,
	SQLException, Exception {

String caseID = null;
DBUtility caseidDAO = null;
try {
	caseidDAO = new DBUtility();
	caseidDAO.createStatement();
	caseID = caseidDAO.executeQry(CaseMonSQL.GETCASEPENALTYSEC70SRNO);
	if (caseID != null) {
		return caseID;
	} else {
		return null;
	}

} catch (NullPointerException ne) {
	logger.error(" NullPointer Exception at Creating  "
			+ " Statement in getCaseID() in DAO  : " + ne);
} catch (SQLException se) {
	logger.error(" SQL Exception at Creating  "
			+ " Statement in getCaseID() in DAO  : " + se);
} catch (Exception e) {
	logger.error(" Exception at Creating  "
			+ " Statement in getCaseID() in DAO  : " + e);
} finally {
	try {
		caseidDAO.closeConnection();
	} catch (SQLException se) {
		logger
				.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
						+ se);
	}
}
return caseID;
}
	

	//end
	
	public ArrayList nxtActionDAO2(String param[]) throws NullPointerException,
			SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseRetrieveCommentsDAO");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_NEXT_PROCESS_ACTION2;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			return typeList;
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return null;
	}

	public ArrayList nxtActionDAO3(String param[]) throws NullPointerException,
			SQLException, Exception {

		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseRetrieveCommentsDAO");
		try {
			ArrayList typeList = new ArrayList();
			ArrayList typeTemp = new ArrayList();

			dbUtil = new DBUtility();
			String str = CaseMonSQL.IGRS_CASE_NEXT_PROCESS_ACTION3;
			dbUtil.createPreparedStatement(str);
			typeList = dbUtil.executeQuery(param);
			return typeList;
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return null;
	}

	public synchronized String getCasePymtTxnSrNo()
			throws NullPointerException, SQLException, Exception {

		String pymtsrid = null;
		DBUtility caseidDAO = null;
		try {
			caseidDAO = new DBUtility();
			caseidDAO.createStatement();
			pymtsrid = caseidDAO.executeQry(CaseMonSQL.GETCASEPYMTTXNID);
			if (pymtsrid != null) {
				return pymtsrid;
			} else {
				return null;
			}

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + e);
		} finally {
			try {
				caseidDAO.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
								+ se);
			}
		}
		return pymtsrid;
	}

	public synchronized String insertCasePymtTxnDtls(CaseMonDTO cmDTO,
			String userId) throws NullPointerException, SQLException, Exception {

		boolean flag = false;

		String pymtsrid = null;
		DBUtility caseidDAO = null;
		try {
			CaseMonDAO dao = new CaseMonDAO();
			caseidDAO = new DBUtility();
			pymtsrid = dao.getCasePymtTxnSrNo();
			String[] params = { pymtsrid, cmDTO.getCaseTxnId(),
					cmDTO.getCaseActionTypeId(), "I", userId };
			caseidDAO.createPreparedStatement(CaseMonSQL.INSERTCASEPYMTDTLS);
			flag = caseidDAO.executeUpdate(params);
			if (flag) {
				if (pymtsrid != null) {
					return pymtsrid;
				} else {
					return null;
				}
			}

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + e);
		} finally {
			try {
				caseidDAO.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
								+ se);
			}
		}
		return pymtsrid;
	}

	public String getPaidAmt(CaseMonDTO cmDTO, String pymtsrid)
			throws NullPointerException, SQLException, Exception {

		String paidAmt = null;
		DBUtility caseidDAO = null;
		try {
			CaseMonDAO dao = new CaseMonDAO();
			caseidDAO = new DBUtility();
			String[] params = { cmDTO.getCaseTxnId(), pymtsrid };
			caseidDAO.createPreparedStatement(CaseMonSQL.GETPAIDAMOUNT);
			paidAmt = caseidDAO.executeQry(params);

			if (paidAmt != null) {
				return paidAmt;
			} else {
				return null;
			}

		} catch (NullPointerException ne) {
			logger.error(" NullPointer Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + ne);
		} catch (SQLException se) {
			logger.error(" SQL Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + se);
		} catch (Exception e) {
			logger.error(" Exception at Creating  "
					+ " Statement in getCaseID() in DAO  : " + e);
		} finally {
			try {
				caseidDAO.closeConnection();
			} catch (SQLException se) {
				logger
						.error("SQL Exception at close Db Connection  at getCaseEstampAmt() : "
								+ se);
			}
		}
		return paidAmt;
	}

	public ArrayList getCasePaymtDetailsDAO(String _caseTxnId)
			throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		String totalPaidAmt="";
		String finalRecAmt="";
		String balAmt="";
		int balanceAmt=0;
		
		logger.debug("WE ARE IN DAO-caseSelectDetailsDAO");
		try {

			dbUtil = new DBUtility();
			String param[] = new String[1];
			param[0] = _caseTxnId;
			CaseMonDTO dto=new CaseMonDTO();
			dbUtil.createPreparedStatement(CaseMonSQL.GETCASEPYMTDTLS);
			/*totalPaidAmt=dbUtil.executeQry(param);
			
			dbUtil.createPreparedStatement(CaseMonSQL.GETFINALRECOVERABLEAMOUNT);
			finalRecAmt=dbUtil.executeQry(param);
			
			if(finalRecAmt!=""||totalPaidAmt!="")
			{
			balanceAmt=Integer.parseInt(finalRecAmt)-Integer.parseInt(totalPaidAmt);
			}
			else
			{	
			balanceAmt=Integer.parseInt(finalRecAmt);
			}
			balAmt=Integer.toString(balanceAmt);
	
			
			dto.setTotalPaidAmt(totalPaidAmt);
			dto.setFinalRecAmt(finalRecAmt);
			dto.setBalAmt(balAmt);
			
			ar1.add(dto);*/
			
			ar1 = dbUtil.executeQuery(param);
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return ar1;
	}
	
	//ADDED BY SHRUTI---21 OCT 2014
	public ArrayList getCasePaymtDetailsEstampDAO(String _caseTxnId)
	throws NullPointerException, SQLException, Exception {

ArrayList ar1 = new ArrayList();
DBUtility dbUtil = null;
String totalPaidAmt="";
String finalRecAmt="";
String balAmt="";
int balanceAmt=0;

logger.debug("WE ARE IN DAO-getCasePaymtDetailsEstampDAO");
try {

	dbUtil = new DBUtility();
	String param[] = new String[1];
	param[0] = _caseTxnId;
	CaseMonDTO dto=new CaseMonDTO();
	dbUtil.createPreparedStatement(CaseMonSQL.GETRECAMTESTAMP);
	ar1 = dbUtil.executeQuery(param);
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
return ar1;
}

	//END
	
	//added by shruti---6 oct 2014
	public ArrayList getRegDocDetailsDAO(String _caseTxnId,CaseMonDTO cmDTO)
	throws NullPointerException, SQLException, Exception {

			ArrayList ar1 = new ArrayList();
			DBUtility dbUtil = null;
			
			logger.debug("WE ARE IN DAO-getRegDocDetailsDAO");
			try {
			
				dbUtil = new DBUtility();
				String param[] = new String[1];
				param[0] = _caseTxnId;
				//CaseMonDTO dto=new CaseMonDTO();
				if(cmDTO.getCaseTypeId()!=null && "CASET_005".equalsIgnoreCase(cmDTO.getCaseTypeId()))
				{
					dbUtil.createPreparedStatement(CaseMonSQL.GETREGDOCDTLSIMPOUNDCASE);
					ar1 = dbUtil.executeQuery(param);
				}
				else
				{
					dbUtil.createPreparedStatement(CaseMonSQL.GETREGDOCDTLS);
					ar1 = dbUtil.executeQuery(param);
				}
				
			} catch (NullPointerException ne) {
				logger.error(ne);
			} catch (SQLException se) {
				logger.error(se);
			} catch (Exception e) {
				logger.error(e);
			} finally {
				try {
					logger.error("using FINALLY Connection is closed");
					dbUtil.closeConnection();
				} catch (Exception e) {
					logger.error(e);
				}
			}
			return ar1;
}
	//end

	//adedd by shruti-15th july 2013
	public ArrayList getPendingPaymtDetailsDAO(String _caseTxnId)
	throws NullPointerException, SQLException, Exception {

	ArrayList ar1 = new ArrayList();
	DBUtility dbUtil = null;
	String totalPaidAmt="";
	String finalRecAmt="";
	String balanceAmt="";
	double paidAmt=0;
	double totalAmt=0;
	double balAmt=0;
	
	logger.debug("WE ARE IN DAO-getPendingPaymtDetailsDAO");
	try {
	
		dbUtil = new DBUtility();
		String param[] = new String[2];
		param[0] = _caseTxnId;
		param[1] = _caseTxnId;
		
		String param1[] = new String[1];
		param1[0] = _caseTxnId;
		
		CaseMonDTO dto=new CaseMonDTO();
		dbUtil.createPreparedStatement(CaseMonSQL.GETCASEPYMTDTLS);
		totalPaidAmt=dbUtil.executeQry(param1);
		if(totalPaidAmt.equalsIgnoreCase("")){
			paidAmt=paidAmt;
		}
		else{
			paidAmt=Integer.parseInt(totalPaidAmt);
		}
		
		dbUtil.createPreparedStatement(CaseMonSQL.IGRS_RECOVERABLE_AMT_CASE_TXN_COMMENTS);
		finalRecAmt=dbUtil.executeQry(param);
		if(finalRecAmt.equalsIgnoreCase(""))
		{
			dbUtil.createPreparedStatement(CaseMonSQL.IGRS_RECOVERABLE_AMT_CASE_TXN_DETAILS);
			finalRecAmt=dbUtil.executeQry(param1);	
		}
	
		if(finalRecAmt.equalsIgnoreCase("")){
			totalAmt=totalAmt;
		}
		else
		{
			totalAmt=Double.parseDouble(finalRecAmt);
			balAmt=totalAmt;
		}
		if(totalAmt!=0)
		{
			balAmt=totalAmt-paidAmt;
		}
			
		//dto.setFinalRecAmt(Integer.toString(totalAmt));
		dto.setBalAmt(Double.toString(balAmt));
		dto.setPaidAmt(Double.toString(paidAmt));
		ar1.add(dto);
		
		//ar1 = dbUtil.executeQuery(param);
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
return ar1;
}

	//end
	
	//adedd by shruti-18th july 2013
	public ArrayList getPendingPaymtOthersDAO(String _caseTxnId)
	throws NullPointerException, SQLException, Exception {

	ArrayList ar1 = new ArrayList();
	DBUtility dbUtil = null;
	String totalPaidAmt="";
	String finalRecAmt="";
	String balanceAmt="";
	int paidAmt=0;
	int totalAmt=0;
	int balAmt=0;
	
	logger.debug("WE ARE IN DAO-getPendingPaymtOthersDAO");
	try {
	
		dbUtil = new DBUtility();
		String param[] = new String[1];
		param[0] = _caseTxnId;
		
		
		CaseMonDTO dto=new CaseMonDTO();
		dbUtil.createPreparedStatement(CaseMonSQL.GETCASEPYMTDTLS);
		totalPaidAmt=dbUtil.executeQry(param);
		if(totalPaidAmt.equalsIgnoreCase("")){
			paidAmt=paidAmt;
		}
		else{
			paidAmt=Integer.parseInt(totalPaidAmt);
		}
		
		dbUtil.createPreparedStatement(CaseMonSQL.IGRS_RECOVERABLE_AMT_CASE_OTHERS_TXN);
		finalRecAmt=dbUtil.executeQry(param);
	
		if(finalRecAmt.equalsIgnoreCase("")){
			totalAmt=totalAmt;
		}
		else
		{
			totalAmt=Integer.parseInt(finalRecAmt);
			balAmt=totalAmt;
		}
		if(totalAmt!=0)
		{
			balAmt=totalAmt-paidAmt;
		}
			
		dto.setFinalRecAmt(Integer.toString(totalAmt));
		dto.setBalAmt(Integer.toString(balAmt));
		dto.setPaidAmt(Integer.toString(paidAmt));
		ar1.add(dto);
		
		//ar1 = dbUtil.executeQuery(param);
} catch (NullPointerException ne) {
	logger.error(ne);
} catch (SQLException se) {
	logger.error(se);
} catch (Exception e) {
	logger.error(e);
} finally {
	try {
		logger.error("using FINALLY Connection is closed");
		dbUtil.closeConnection();
	} catch (Exception e) {
		logger.error(e);
	}
}
return ar1;
}
	//end
	public synchronized String getCaseDocSrNo() {

		String pymtsrid = null;
		DBUtility caseidDAO = null;

		try {
			caseidDAO = new DBUtility();
			caseidDAO.createStatement();
			pymtsrid = caseidDAO.executeQry(CaseMonSQL.GETCASEDOCSRNO);
			if (pymtsrid != null) {
				return pymtsrid;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				caseidDAO.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}

		return pymtsrid;
	}

	public boolean insertDoc(HashMap map, String[] param) throws Exception {
		boolean flag = false;
		DBUtility dbUtil = new DBUtility();
		try {
			logger.debug("map:");
			logger.debug("I am in dao =====");
			if (map != null) {
				System.out.println("I am in dao map noteql to null ======");
				Iterator I = map.entrySet().iterator();
				//logger.debug("I.hasNext():-" + I.hasNext());
				while (I.hasNext()) {
					Map.Entry me = (Map.Entry) I.next();
					/*logger.debug("getKey :-" + me.getKey() + ":"
							+ me.getValue());*/
					CaseMonDTO dto1 = (CaseMonDTO) me.getValue();
					dto1.setDocsrno(getCaseDocSrNo());
					ArrayList columnNames = new ArrayList();
					ArrayList columnValue = new ArrayList();
					columnNames.add("SR_NO");
					columnNames.add("DOC_FILE_LOC");
					columnNames.add("CASE_ACTION_TXN_ID");
					columnNames.add("CASE_TXN_ID");
					columnNames.add("CREATED_BY");

					columnValue.add(dto1.getDocsrno());
					String fileName = (String) me.getKey();
					String fileLoc = dto1.getFilePath();
					columnValue.add(fileLoc);
					columnValue.add(param[0]);
					columnValue.add(param[1]);
					columnValue.add(param[2]);
					flag = dbUtil.saveCaseDocuments(columnNames, columnValue,
							"IGRS_CASE_TXN_DOC_DETAILS");
				}
			} else {
				flag = true;
			}
			//logger.info("before inserting getRevreportId :-" + flag);
			if (flag) {
				logger.debug("inside commit");
				dbUtil.commit();
			} else {
				logger.debug("inside rollback");
				dbUtil.rollback();
			}
			logger.info("after inserting getRevreportId");
		} catch (Exception x) {
			logger.info("Exception in getRevreportId() :- " + x);
			x.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				logger.debug(cx);
			}
		}
		return flag;
	}
	
	
	
	
	//ADDED-11TH JULY 2013
	public ArrayList getRevisedRecoverableAmtDetails(String[] param)
			throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseSelectDetailsDAO");
		try {

			dbUtil = new DBUtility();
			String param1[] = new String[2];
			param1[0] = param[0];
			param1[1]=param[1];
			String str = CaseMonSQL.GETREVISEDRECOPVERABLEAMOUNTS;
			dbUtil.createPreparedStatement(str);
			ar1 = dbUtil.executeQuery(param1);
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return ar1;
	}

	//RRC
	//ADDED-11TH JULY 2013
	
	private String getRRCCaseNumber(CaseMonDTO dto) throws Exception {

		 String prefix="";
		CaseMonDAO dao = new CaseMonDAO();
		String districtCode = dao.getLoggedInDRDistrict(dto.getCurrentOffId());
		if (districtCode.length() == 0) {
			districtCode = "00" + districtCode;
		}
		else if (districtCode.length() == 1) {
			districtCode = "0" + districtCode;
		}
		else{
			districtCode = districtCode;
		}
		 String revheadName=dto.getRevenueHeadName();
		 prefix="RRC"+districtCode+revheadName;
		 //logger.info("PREFIX::::::::::::::::::::::"+prefix);
		String id=dao.getRRCCaseFormat(prefix);
		return id;
	}
	
	/*public String getLoggedInDRDistrict(String userid) throws Exception {
		String did = "";
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String requserid[] = { userid };
			dbUtil.createPreparedStatement(CommonSQL.SELECTDRDISTRICT);
			did = dbUtil.executeQry(requserid);
		} catch (Exception exception) {
			logger.info("Exception in SPDAO-user district " + exception);
		} finally {
			dbUtil.closeConnection();
		}
		return did;
	}*/
	//modified by shruti on 8th oct 2014
	
	public String getLoggedInDRDistrict(String officeid) throws Exception {
		String did = "";
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String requserid[] = { officeid };
			//dbUtil.createPreparedStatement(CommonSQL.SELECTDRDISTRICT);
			dbUtil.createPreparedStatement(CommonSQL.SELECTDRDISTRICTNEW);
			did = dbUtil.executeQry(requserid);
		} catch (Exception exception) {
			logger.info("Exception in SPDAO-user district " + exception);
		} finally {
			dbUtil.closeConnection();
		}
		return did;
	}
	
	
	public ArrayList getRRCDetails(CaseMonDTO dto)
			throws NullPointerException, SQLException, Exception {

		ArrayList ar1 = new ArrayList();
		DBUtility dbUtil = null;
		logger.debug("WE ARE IN DAO-caseSelectDetailsDAO");
		try {
			dbUtil = new DBUtility();
			String sqlCurrentdate="SELECT TO_CHAR(SYSDATE,'DD/MM/YYY') FROM DUAL";
			dbUtil.createStatement();
			String currentDate=dbUtil.executeQry(sqlCurrentdate);
			//logger.debug("current date:-"+currentDate);
			CaseMonDAO dao=new CaseMonDAO();
			String rrcno=dao.getRRCCaseNumber(dto);
			dto.setRrcno(rrcno);
			dto.setRrcDate(currentDate);
			ar1.add(dto);
		
		} catch (NullPointerException ne) {
			logger.error(ne);
		} catch (SQLException se) {
			logger.error(se);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				logger.error("using FINALLY Connection is closed");
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return ar1;
	}
	
	public synchronized String getRRCCaseFormat(String prefix){
		String refId=null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			CaseMonDAO dao=new CaseMonDAO();
			String sqlCurrentdate="SELECT TO_CHAR(SYSDATE,'MMYYYY') FROM DUAL";
			dbUtil.createStatement();
			String currentDate=dbUtil.executeQry(sqlCurrentdate);
			//logger.debug("current date:-"+currentDate);
			String newPrefix=prefix+currentDate;
			//logger.info("NEW PREFIX>>>>>>>>>>>>>>>>>>>>>>>>>>"+newPrefix);
			String serialnumber=dao.getRRCCaseIdSqNo();
			if(serialnumber.length()==0){
				serialnumber="0000";
			}
			else if(serialnumber.length()==1){
				serialnumber="000"+serialnumber;
			}
			else if(serialnumber.length()==2){
			serialnumber="00"+serialnumber;	
			}
			else if(serialnumber.length()==3){
				serialnumber="0"+serialnumber;
			}
			else{
			serialnumber=serialnumber;	
			}
			refId=newPrefix+serialnumber;
		} catch (Exception x) {
			logger.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				logger.debug(x);
			}
		}

		return refId;
	}
	
	//RRC CASE
	public synchronized String getRRCCaseIdSqNo()
	{
		DBUtility dbUtil=null;
		String casenoseq="";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			casenoseq=dbUtil.executeQry(CommonSQL.GETRRCCASENOSEQNO);
		} catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				logger.debug(x);
			}
		}
		return casenoseq;
	}
	
	public String getRRCConfigParam()
	{
		String mnthparam="";
		DBUtility dbUtil=null;
		
		try
		{
			dbUtil=new DBUtility();
			dbUtil.createStatement();
			mnthparam=dbUtil.executeQry(CommonSQL.GETRRCCRITERIA);
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				logger.debug(x);
			}
		}
		return mnthparam;
		
	}
	public String getRRCMonthCriteria(CaseMonDTO dto)
	{
		String mnthchk="";
		String actionDate="";
		DBUtility dbUtil=null;
		String[] param={dto.getCaseTxnId()};
		
		
		try
		{
			dbUtil=new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GETRRCDUEDETAILS);
			actionDate=dbUtil.executeQry(param);
			
			
			boolean flag = false;
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

				Date execDate = sdf1.parse(actionDate);
				Date sysDate = new Date();
				//logger.debug("sysdate"+sysDate);
				String d1 = sdf1.format(sysDate);
				Date sDate = sdf1.parse(d1);
				/*logger.debug("Final Sysdate"+sDate);
				logger.debug("final execDate"+execDate);
				logger.debug("<------getTime"+sDate.getTime());
				logger.debug("<------getTime"+execDate.getTime());*/
				
				long diff1 = (long)(sDate.getTime() - execDate.getTime());
				long diff = diff1/(1000*60*60*24);
				/*logger.debug("diff"+diff1);
				logger.debug("diff"+diff);*/
				
				mnthchk=Integer.toString((int)diff);
				
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				logger.debug(x);
			}
		}
		return mnthchk;
		
	}
	
	public String updateRRCDtls(CaseMonDTO dto)
	{
		boolean flag=false;
		String rcaseno="";
		DBUtility dbUtil=null;
		
				
		try
		{
			dbUtil=new DBUtility();
			
			String[] param={dto.getRrcno(),dto.getCaseTxnId()};
			CaseMonDAO cmDAO = new CaseMonDAO();
			IGRSCommon sqnce = new IGRSCommon();
			String cmntssrno = cmDAO.getCaseCmntsSrNo();
			String casAxnTrId = sqnce.getSequenceNumber("IGRS_CASE_AXN_TXN","CSA");
			String[] param1=new String[5];
			param1[0]=cmntssrno;
			param1[1]=dto.getCaseTxnId();
			param1[2]=casAxnTrId;
			param1[3]=dto.getCurrentUserId();
			param1[4]=dto.getAxnDescr();
			
			dbUtil.createPreparedStatement(CommonSQL.UPDATERRCCASENUMBER);
			flag=dbUtil.executeUpdate(param);
			if(flag)
			{
				dbUtil.createPreparedStatement(CommonSQL.UPDATERRCDTLS);
				flag=dbUtil.executeUpdate(param1);
			}
			rcaseno=dto.getRrcno();
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				logger.debug(x);
			}
		}
		
		return rcaseno;
	}

	
	public String getCaseId(CaseMonDTO dto)
	{
		String caseId="";
		DBUtility dbUtil=null;
		String [] param={dto.getRrcId()};
		try
		{
			dbUtil=new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GETCASENUMBER);
			caseId=dbUtil.executeQry(param);
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				logger.debug(x);
			}
		}
		return caseId;
	}
	
	public String getRRCId(CaseMonDTO dto)
	{
		String caseId="";
		DBUtility dbUtil=null;
		String [] param={dto.getCaseTxnId()};
		try
		{
			dbUtil=new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GETRRCID);
			caseId=dbUtil.executeQry(param);
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				logger.debug(x);
			}
		}
		return caseId;
	}
	
	//added by shruti-28 august 2013
	public ArrayList getSalutationDetails(CaseMonDTO dto)
	throws NullPointerException, SQLException, Exception {

	ArrayList ar1 = new ArrayList();
	DBUtility dbUtil = null;
	String regId="";
	String[] param={dto.getCaseTxnId()};
	logger.debug("WE ARE IN DAO-getSalutationDetails");
	try {
	
		dbUtil = new DBUtility();
		
		dbUtil.createPreparedStatement(CaseMonSQL.GETREGID);
		regId=dbUtil.executeQry(param);
		
		String[] idDtls={regId,dto.getOfficeId()};
		dbUtil.createPreparedStatement(CaseMonSQL.GETSALUTATIONDTLS);
		ar1=dbUtil.executeQuery(idDtls);
		
	} catch (NullPointerException ne) {
		logger.error(ne);
	} catch (SQLException se) {
		logger.error(se);
		System.out.println(se);
	} catch (Exception e) {
		logger.error(e);
	} finally {
		try {
			logger.error("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.error(e);
		}
	}
	return ar1;
	}
	
	//added by shruti---29 oct 2014
	public ArrayList getRevSalutationDetails(CaseMonDTO dto)
	throws NullPointerException, SQLException, Exception {

	ArrayList ar1 = new ArrayList();
	DBUtility dbUtil = null;
	String regId="";
	String[] regIdArr=new String[2];
	String[] param={dto.getCaseTxnId()};
	logger.debug("WE ARE IN DAO-getSalutationDetails");
	try {
	
		dbUtil = new DBUtility();
		
		dbUtil.createPreparedStatement(CaseMonSQL.GETREGNUMBER);
		regId=dbUtil.executeQry(param);
		
		String[] idDtls={dto.getCaseTxnId(),regId,dto.getOfficeId()};
		dbUtil.createPreparedStatement(CaseMonSQL.GETREVSALUTATIONDTLS);
		ar1=dbUtil.executeQuery(idDtls);
		
	} catch (NullPointerException ne) {
		logger.error(ne);
	} catch (SQLException se) {
		logger.error(se);
		System.out.println(se);
	} catch (Exception e) {
		logger.error(e);
	} finally {
		try {
			logger.error("using FINALLY Connection is closed");
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.error(e);
		}
	}
	return ar1;
	}
	//end
	
	//added---28th oct 2013
	public ArrayList getInternalUserDtls(String officeId,String language) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		String officeName="";
		ArrayList resultList=new ArrayList();
		String arry[] = new String[1];
		if (officeId != null) {
			arry[0] = officeId;
		}
		try {
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(language))
			{
			dbUtil.createPreparedStatement(CaseMonSQL.GETOFFICENAME);
			}
			else
			{
				dbUtil.createPreparedStatement(CaseMonSQL.GETOFFICENAME_H);
			}
			resultList=dbUtil.executeQuery(arry);
			} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return resultList;
	}
	
	//added by shruti----6 sep 2014
	public String getOfficeTypeId(String officeId)
	{
		DBUtility dbUtil =null;
		String officeTypeId = null;
		String arr[]={officeId};
		try{
	    dbUtil = new DBUtility();
	    dbUtil.createPreparedStatement(CaseMonSQL.GETOFFICETYPEID);
	    officeTypeId = dbUtil.executeQry(arr);
	}
		catch(Exception ex)
		{
		ex.printStackTrace();
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return officeTypeId;
	}
	//end
	//added by shruti----9 jan 2015
	public String getSectionHead(String caseId)
	{
		DBUtility dbUtil =null;
		String officeTypeId = null;
		String arr[]={caseId};
		try{
	    dbUtil = new DBUtility();
	    dbUtil.createPreparedStatement(CaseMonSQL.GETSECTIONHEADID);
	    officeTypeId = dbUtil.executeQry(arr);
	}
		catch(Exception ex)
		{
		ex.printStackTrace();
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return officeTypeId;
	}
}// Dao End
