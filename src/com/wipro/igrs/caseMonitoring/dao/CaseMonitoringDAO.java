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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.caseMonitoring.dto.CaseMonitoringDTO;
import com.wipro.igrs.caseMonitoring.sql.CaseMonSQL;
import com.wipro.igrs.caseMonitoring.sql.CommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.util.PropertiesFileReader;

public class CaseMonitoringDAO {

	private static Logger log = org.apache.log4j.Logger.getLogger(CaseMonitoringDAO.class);
	//DBUtility dbUtil;

	public CaseMonitoringDAO() {
	}
	public ArrayList getInitiate(String caseId) {
		String sql = CommonSQL.IGRS_NOTICE_ONE;
		log.info("caseId  ********************** " + caseId);
		String[] param = new String[1];
		param[0] = caseId;
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to NoticeOne page");
			dbUtil.createPreparedStatement(sql);
			//log.info("SQL:" + CommonSQL.IGRS_NOTICE_ONE);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getNoticeOne() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getNoticeDetailDao(String caseId) {
		String sql = CommonSQL.IGRS_CASE_NOTICE_DRPROCESS;
		//log.info("caseId********************** " + caseId);
		String[] param = new String[1];
		param[0] = caseId;
		ArrayList list = null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to getNoticeDetailDao page");
			dbUtil.createPreparedStatement(sql);
			//log.info("SQL:" + CommonSQL.IGRS_CASE_NOTICE_DRPROCESS);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getNoticeDetailDao() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getOrder(String caseId) {
		String sql = CommonSQL.IGRS_ORDER_ONE;
		String[] param = new String[1];
		param[0] = caseId;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to OrderOne page");
			dbUtil.createPreparedStatement(sql);
			//log.info("SQL:" + CommonSQL.IGRS_ORDER_ONE);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getOrderOne() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getDrComment(String caseId) {
		String sql = CommonSQL.NOTICE_DR_CMT;
		//log.info("caseId " + caseId);
		String[] param = new String[1];
		param[0] = caseId;
		ArrayList list = null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Entered into DR_comment()");
			dbUtil.createPreparedStatement(sql);
			//log.info("sql:" + CommonSQL.NOTICE_DR_CMT);
			list = dbUtil.executeQuery(param);
		} catch (Exception e) {
			log.info("Exception in drCommetn: " + e);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public boolean insertDrCmt(String[] params, String roleId, String funId,
			String userId) {

		boolean submitIntimationInfo = false;DBUtility dbUtil=null;
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.NOTICE_DR_CMT_INSERT);
			submitIntimationInfo = dbUtil.executeUpdate(params);
			if (submitIntimationInfo) 
			{
				igrsCommon.saveLogDet("IGRS_CASE_COMMENTS", "INSERT", "T",funId, userId, roleId);
				dbUtil.commit();
			}
			else 
			{
				igrsCommon.saveLogDet("IGRS_CASE_COMMENTS", "INSERT", "F",funId, userId, roleId);
				dbUtil.rollback();
			}
		} catch (Exception x) {
			log.error("Exception in submitIntimationInfo() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.error("Exception in submitIntimationInfo() :-" + ex);
			}
		}

		return submitIntimationInfo;
	}

	public boolean noticeInsert(String[] notice, String[] status,
			String roleId, String funId, String userId) {
		boolean noticeSend = false;DBUtility dbUtil=null;
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.NOTICE_INSERT);

			noticeSend = dbUtil.executeUpdate(notice);
			if (noticeSend) 
			{
				igrsCommon.saveLogDet("IGRS_CASE_REFUND_NOTICE_TXN", "INSERT","T", funId, userId, roleId);
			} 
			else
			{
				igrsCommon.saveLogDet("IGRS_CASE_REFUND_NOTICE_TXN", "INSERT","F", funId, userId, roleId);
			}
			if (noticeSend) 
			{
				dbUtil.createPreparedStatement(CommonSQL.CASE_STATUS);
				noticeSend = dbUtil.executeUpdate(status);
			}
			if (noticeSend) 
			{
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_CASE_REFUND_TXN", "INSERT", "T",funId, userId, roleId);
			} 
			else 
			{
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_CASE_REFUND_TXN", "INSERT", "F",funId, userId, roleId);
			}
		} catch (Exception e) {
			log.error("Exception in noticeSend :" + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				log.error("Exception in noticeSend :" + e);
			}
		}

		return noticeSend;
	}

	public boolean getRecDt(String[] caseId, String roleId, String funId,
			String userId) {
		boolean getRecDt = false;DBUtility dbUtil=null;
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.REC_DATE);
			getRecDt = dbUtil.executeUpdate(caseId);
			if (getRecDt) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_CASE_REFUND_ORDER_TXN", "UPDATE","T", funId, userId, roleId);
			}
			else
			{
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_CASE_REFUND_ORDER_TXN", "UPDATE","F", funId, userId, roleId);
			}
		} catch (Exception e) {
			log.error("Exception in getRecDt : " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				log.error("Exception in RecDt :" + e);
			}
		}
		return getRecDt;
	}

	public boolean insertdrlastCmt(String[] sid, String[] nid) {
		boolean casemonitoring = false;DBUtility dbUtil=null;
		try {
			log.info("before inserting DR-Comments ");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.IGRS_CASE_DR_RECDATE_UPDATE);
			casemonitoring = dbUtil.executeUpdate(nid);
			if (casemonitoring) {
				if (sid != null) {
					if (sid.length > 0) {
						dbUtil.createPreparedStatement(CommonSQL.IGRS_CASE_DRCMT_UPDATE_INSERT);
						casemonitoring = dbUtil.executeUpdate(sid);
					}
				}
			}
			log.info("before inserting");
			if (casemonitoring) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
			log.info("after inserting");
		} catch (Exception x) {
			log.info("Exception in insertdrlastCmt() :- " + x);

		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.info("Exception in insertdrlastCmt() :-" + ex);
			}
		}
		return casemonitoring;
	}

	public boolean generateOrderid(String[] orderid, String[] lstAction) {
		boolean casemonitoring = false;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("before getting orderid ");
			dbUtil.createPreparedStatement(CommonSQL.IGRS_CASE_ORDER_ID);
			//log.info("SQL : " + CommonSQL.IGRS_CASE_ORDER_ID);
			casemonitoring = dbUtil.executeUpdate(orderid);
			if (casemonitoring) {
				dbUtil.createPreparedStatement(CommonSQL.CASE_STATUS);
				casemonitoring = dbUtil.executeUpdate(lstAction);
			}
			if (casemonitoring) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
		} catch (Exception x) {
			log.info("Exception in generateOrderid() :- " + x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				log.error("Exception in noticeSend :" + e);
			}
		}
		return casemonitoring;
	}

	public boolean getNoticeRecDt(String[] caseId) {
		boolean getNoticeRecDt = false;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.REC_NOTICE_DATE);
			getNoticeRecDt = dbUtil.executeUpdate(caseId);
			if (getNoticeRecDt) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			log.error("Exception in getRecDt : " + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				log.error("Exception in getNoticeRecDt :" + e);
			}
		}
		return getNoticeRecDt;
	}

	public ArrayList printOrderOnly(String[] caseid) {
		ArrayList listprint = null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("before printing the printOrderOnly detail ");
			dbUtil
					.createPreparedStatement(CommonSQL.IGRS_CASE_PRINT_ORDER_ONLY);
			//log.info("SQL : " + CommonSQL.IGRS_CASE_PRINT_ORDER_ONLY);
			listprint = dbUtil.executeQuery(caseid);
			log.info("After printing the printOrderOnly detail ");
		} catch (Exception x) {
			log.info("Exception in printOrderOnly() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return listprint;
	}

	public ArrayList getPrintOrder(String[] caseid) {
		ArrayList listprint = null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("before printing the getPrintOrder detail ");
			dbUtil.createPreparedStatement(CommonSQL.IGRS_CASE_PRINT_ORDER);
			//log.info("SQL : " + CommonSQL.IGRS_CASE_PRINT_ORDER);
			listprint = dbUtil.executeQuery(caseid);
			log.info("After printing the getPrintOrder detail ");
		} catch (Exception x) {
			log.info("Exception in getPrintDetail() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return listprint;
	}

	public ArrayList getPrintVoucher(String[] caseid) {
		ArrayList listprint = null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("before printing the getPrintVoucher detail ");
			dbUtil.createPreparedStatement(CommonSQL.IGRS_CASE_PRINT_VOUCHER);
			//log.info("SQL : " + CommonSQL.IGRS_CASE_PRINT_VOUCHER);
			listprint = dbUtil.executeQuery(caseid);
			log.info("After printing the getPrintVoucher detail ");
		} catch (Exception x) {
			log.info("Exception in getPrintDetail() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return listprint;
	}

	public ArrayList getRevheadlistDao() {
		ArrayList list = null;
		IGRSCommon igrsCommon;DBUtility dbUtil=null;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getRevheadlistIgrs();
		} catch (Exception e) {
			log.error("Exception in getRevheadlistDao():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getSecheadlistDao() {
		ArrayList list = null;
		IGRSCommon igrsCommon;DBUtility dbUtil=null;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getSecheadlistIgrs();
		} catch (Exception e) {
			log.error("Exception in getSecheadlistDao():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	/*public ArrayList getStampDetailDao(String sid, String N, String M) {
		ArrayList list = null;
		log.info("EStampId(First Letter) = " + sid);
		String sql = CommonSQL.IGRS_ESTAMP_SEARCH_QUERY;
		sql += " WHERE TRANSACTION_ID LIKE '" + sid + "%') WHERE RN BETWEEN '"+ N + "' AND '" + M + "'";
		String[] id = new String[1];
		id[0] = sid;
		try {
			dbUtil = new DBUtility();
			log.info("inside getStampDetailDao() function...");
			dbUtil.createStatement();
			log.info("SQL = " + sql);
			list = dbUtil.executeQuery(sql);
		} catch (Exception e) {
			log.error("Exception in getStampDetailDao():" + e);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}*/

	/*public ArrayList getEstampdetailDao(String sid) {

		ArrayList list = null;
		log.info("EStampId = " + sid);
		String sql = "select IGRS_ESTAMP_TXN_DETAILS.ESTAMP_CODE,IGRS_ESTAMP_TXN_DETAILS.ESTAMP_DUTY,"
				+ " trunc(IGRS_ESTAMP_TXN_DETAILS.ESTAMP_DATE)ESTAMP_DATE,"
				+ " trunc(IGRS_ESTAMP_TXN_DETAILS.ESTAMP_DATE+180)ESTAMP_EXPIRY_DATE,"
				+ " (SELECT DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER"
				+ " WHERE DEED_TYPE_ID=(SELECT IGRS_ESTAMP_DEED_DETAILS.DEED_TYPE_ID FROM IGRS_ESTAMP_DEED_DETAILS"
				+ " WHERE ESTAMP_TXN_ID='"
				+ sid
				+ "'))DEED_TYPE_NAME,"
				+ " IGRS_ESTAMP_TXN_PARTY_DETAILS.PARTY_FIRST_NAME,"
				+ " IGRS_ESTAMP_TXN_PARTY_DETAILS.PARTY_MIDDLE_NAME,IGRS_ESTAMP_TXN_PARTY_DETAILS.PARTY_LAST_NAME,"
				+ " IGRS_ESTAMP_TXN_PARTY_DETAILS.PARTY_ADDRESS"
				+ " from IGRS_ESTAMP_TXN_DETAILS,IGRS_ESTAMP_DEED_DETAILS,IGRS_ESTAMP_TXN_PARTY_DETAILS"
				+ " where IGRS_ESTAMP_TXN_DETAILS.TRANSACTION_ID=IGRS_ESTAMP_TXN_PARTY_DETAILS.ESTAMP_TXN_ID"
				+ " and  IGRS_ESTAMP_TXN_DETAILS.TRANSACTION_ID=IGRS_ESTAMP_DEED_DETAILS.ESTAMP_TXN_ID"
				+ " and IGRS_ESTAMP_TXN_DETAILS.TRANSACTION_ID ='" + sid + "'";
		String[] id = new String[1];
		id[0] = sid;
		log.info("hello");
		try {
			dbUtil = new DBUtility();
			log.info("before get the estamp no detail");
			dbUtil.createStatement();
			log.info("SQL = " + sql);
			list = dbUtil.executeQuery(sql);
		} catch (Exception e) {
			log.error("Exception in getEstampdetailDao():" + e);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}*/

	public boolean insertcaseoptionaldao(String[] columns, String[] drCmt,
			String roleId, String funId, String userId, String[] actionMap) {
		boolean casemonitoring = false;DBUtility dbUtil=null;
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.IGRS_CASE_OPTIONAL_INSERT1);
			casemonitoring = dbUtil.executeUpdate(columns);

			if (casemonitoring) {

				igrsCommon.saveLogDet("IGRS_CASE_REFUND_TXN", "INSERT", "T",
						funId, userId, roleId);
			} else {
				igrsCommon.saveLogDet("IGRS_CASE_REFUND_TXN", "INSERT", "F",
						funId, userId, roleId);
			}

			// added by shruti for CASE ACTION MAP DATA
			if (casemonitoring) {
				dbUtil.createPreparedStatement(CommonSQL.INSERT_ACTION_MAP);
				casemonitoring = dbUtil.executeUpdate(actionMap);
			}
			// end
			// MODIFIED BY SHRUTI-IGRS_CASE_COMMENTS TABLE BEING REFFERE IS NOT
			// THERE IN DB
			if (casemonitoring) {
				/*
				 * dbUtil.createPreparedStatement(CommonSQL.NOTICE_DR_CMT_INSERT)
				 * ;
				 */
				dbUtil
						.createPreparedStatement(CommonSQL.INSERT_CASE_TXN_COMMENTS);
				casemonitoring = dbUtil.executeUpdate(drCmt);
			}
			// END OF MODIFIED CODE

			if (casemonitoring) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_CASE_REFUND_TXN", "INSERT", "T",funId, userId, roleId);
			} else {
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_CASE_REFUND_TXN", "INSERT", "F",funId, userId, roleId);
			}

		} catch (Exception e) {
			log.error("Insert Query Exception......" + e);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return casemonitoring;
	}

	public boolean delStatus(String[] caseId, String roleId, String funId,
			String userId) {
		boolean delStatus = false;DBUtility dbUtil=null;
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.DELIVERY_STATUS);
			delStatus = dbUtil.executeUpdate(caseId);
			if (delStatus) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_CASE_REFUND_VOUCHER_TXN", "UPDATE","T", funId, userId, roleId);
			} else {
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_CASE_REFUND_VOUCHER_TXN", "UPDATE","F", funId, userId, roleId);
			}
		} catch (Exception e) {
			log.error("Exception in delivery Status" + e);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				log.error("Exception in delivery Status" + e);
			}
		}
		return delStatus;
	}

	public ArrayList getTotalsizeDao(String sid) {
		ArrayList list = null;
		String str = "";
		log.info("getTotalsizeDao(First Letter) = " + sid);
		String sql = CommonSQL.IGRS_ESTAMP_SEARCH_TOTAL_SIZE;
		sql += " WHERE TRANSACTION_ID LIKE '" + sid + "%'";
		String[] id = new String[1];
		id[0] = sid;DBUtility dbUtil=null;
		try {
			log.info("inside getTotalsizeDao() function...");
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			log.info("SQL = " + sql);
			list = dbUtil.executeQuery(sql);
		} catch (Exception e) {
			log.error("Exception in getStampDetailDao():" + e);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getCaseView(String caseHead, String caseType1,
			String caseType2, String caseType3, String estampType,
			String estampSubType, String caseId, String fromDate,
			String toDate, String status, String from, String to) {
		// modified by shruti as currently doc details are not being stored
		/* String sql = CommonSQL.IGRS_VIEW; */
		// end
		
		String sql="";
		if(caseHead!=null && "CH_001".equalsIgnoreCase(caseHead) )
		{
			sql = CommonSQL.IGRS_VIEW1;
				if (caseId != null && !"".equals(caseId.trim())) {
					sql += " AND UPPER(IGRS_CASE_TXN_DETAILS.CASE_TXN_ID) "
							+ "like UPPER('%" + caseId + "%')";
				}
		
				if ((fromDate != null && toDate != null)
						&& (!"".equals(fromDate.trim()) && !"".equals(toDate.trim()))) {
				/*	sql += " AND IGRS_CASE_TXN_DETAILS.CREATED_DATE "
							+ "BETWEEN TO_CHAR(TO_DATE('" + fromDate + "',"
							+ "'dd/mm/yyyy')) AND TO_CHAR(TO_DATE('" + toDate
							+ "','dd/mm/yyyy'))";*/
					
					sql += " AND IGRS_CASE_TXN_DETAILS.CREATED_DATE "
						+ "BETWEEN TO_DATE('" + fromDate + "',"
						+ "'dd-mm-yy') AND TO_DATE('" + toDate
						+ "','dd-mm-yy')+1";
					
					
					
					
					//sql += "IGRS_CASE_TXN_DETAILS.CREATED_DATE BETWEEN TO_DATE( fromDate,'dd-mm-yy') AND TO_DATE('10/11/2014','dd-mm-yy')";
					
				}
		
				if (status != null && !"".equals(status.trim())) {
					sql += " AND IGRS_CASE_TXN_DETAILS.CASE_STATUS='" + status + "'";
				}
				sql+="AND CASE_HEAD_ID='"+caseHead+"' AND CASE_TYPE_ID='"+caseType1+"'";
		}
		else if(caseHead!=null && "CH_002".equalsIgnoreCase(caseHead) )
		{
			sql = CommonSQL.IGRS_VIEW2;
				if (caseId != null && !"".equals(caseId.trim())) {
					sql += " AND UPPER(IGRS_CASE_REFUND_TXN_DTLS.CASE_TXN_ID) "
							+ "like UPPER('%" + caseId + "%')";
				}
		
				if ((fromDate != null && toDate != null)
						&& (!"".equals(fromDate.trim()) && !"".equals(toDate.trim()))) {
					/*sql += " AND IGRS_CASE_REFUND_TXN_DTLS.CREATED_DATE "
							+ "BETWEEN TO_CHAR(TO_DATE('" + fromDate + "',"
							+ "'dd/mm/yyyy')) AND TO_CHAR(TO_DATE('" + toDate
							+ "','dd/mm/yyyy'))";*/
					
					
					sql += " AND IGRS_CASE_REFUND_TXN_DTLS.CREATED_DATE "
						+ "BETWEEN TO_DATE('" + fromDate + "',"
						+ "'dd-mm-yy') AND TO_DATE('" + toDate
						+ "','dd-mm-yy')+1";
					
					
				}
		
				if (status != null && !"".equals(status.trim())) {
					sql += " AND IGRS_CASE_REFUND_TXN_DTLS.CASE_STATUS='" + status + "'";
				}
				sql+="AND CASE_HEAD_ID='"+caseHead+"' AND CASE_TYPE_ID='"+caseType1+"'";
				if(estampType!=null)
				{
					if("Judicial".equalsIgnoreCase(estampType))
					{
						sql+=" AND ESTAMP_REQ_ID like 'RR02%'";
					}
					else
					{
						sql+=" AND ESTAMP_REQ_ID like 'RR01%'";
					}
					
				}
				
		}
		else if(caseHead!=null && "CH_003".equalsIgnoreCase(caseHead) && caseType1!=null && "CASET_009".equalsIgnoreCase(caseType1) )
		{
			sql = CommonSQL.IGRS_VIEW1;
				if (caseId != null && !"".equals(caseId.trim())) {
					sql += " AND UPPER(IGRS_CASE_TXN_DETAILS.CASE_TXN_ID) "
							+ "like UPPER('%" + caseId + "%')";
				}
		
				if ((fromDate != null && toDate != null)
						&& (!"".equals(fromDate.trim()) && !"".equals(toDate.trim()))) {
					/*sql += " AND IGRS_CASE_TXN_DETAILS.CREATED_DATE "
							+ "BETWEEN TO_CHAR(TO_DATE('" + fromDate + "',"
							+ "'dd/mm/yyyy')) AND TO_CHAR(TO_DATE('" + toDate
							+ "','dd/mm/yyyy'))";
					*/
					
					
					sql += " AND IGRS_CASE_TXN_DETAILS.CREATED_DATE "
						+ "BETWEEN TO_DATE('" + fromDate + "',"
						+ "'dd-mm-yy') AND TO_DATE('" + toDate
						+ "','dd-mm-yy')+1";
					
					
				}
		
				if (status != null && !"".equals(status.trim())) {
					sql += " AND IGRS_CASE_TXN_DETAILS.CASE_STATUS='" + status + "'";
				}
				sql+="AND CASE_HEAD_ID='"+caseHead+"' AND CASE_TYPE_ID='"+caseType1+"'";
				
		}
		else if(caseHead!=null && "CH_003".equalsIgnoreCase(caseHead) && caseType1!=null && "CASET_010".equalsIgnoreCase(caseType1) )
		{
			sql = CommonSQL.IGRS_VIEW3;
				if (caseId != null && !"".equals(caseId.trim())) {
					sql += " AND UPPER(IGRS_CASE_OTHERS_TXN_DTLS.CASE_TXN_ID) "
							+ "like UPPER('%" + caseId + "%')";
				}
		
				if ((fromDate != null && toDate != null)
						&& (!"".equals(fromDate.trim()) && !"".equals(toDate.trim()))) {
					/*sql += " AND IGRS_CASE_OTHERS_TXN_DTLS.CREATED_DATE "
							+ "BETWEEN TO_CHAR(TO_DATE('" + fromDate + "',"
							+ "'dd/mm/yyyy')) AND TO_CHAR(TO_DATE('" + toDate
							+ "','dd/mm/yyyy'))";
					*/
					
					sql += " AND IGRS_CASE_OTHERS_TXN_DTLS.CREATED_DATE "
						+ "BETWEEN TO_DATE('" + fromDate + "',"
						+ "'dd-mm-yy') AND TO_DATE('" + toDate
						+ "','dd-mm-yy')+1";
					
				}
		
				if (status != null && !"".equals(status.trim())) {
					sql += " AND IGRS_CASE_OTHERS_TXN_DTLS.CASE_STATUS='" + status + "'";
				}
				
		}
		else if(caseHead!=null && "CH_003".equalsIgnoreCase(caseHead) && caseType1!=null && "CASET_011".equalsIgnoreCase(caseType1) )
		{
			sql = CommonSQL.IGRS_VIEW4;
				if (caseId != null && !"".equals(caseId.trim())) {
					sql += " AND UPPER(IGRS_CASE_REV_PNLTY_TXN_DTLS.CASE_TXN_ID) "
							+ "like UPPER('%" + caseId + "%')";
				}
		
				if ((fromDate != null && toDate != null)
						&& (!"".equals(fromDate.trim()) && !"".equals(toDate.trim()))) {
					/*sql += " AND IGRS_CASE_REV_PNLTY_TXN_DTLS.CREATED_DATE "
							+ "BETWEEN TO_CHAR(TO_DATE('" + fromDate + "',"
							+ "'dd/mm/yyyy')) AND TO_CHAR(TO_DATE('" + toDate
							+ "','dd/mm/yyyy'))";
					*/
					
					
					sql += " AND IGRS_CASE_REV_PNLTY_TXN_DTLS.CREATED_DATE "
						+ "BETWEEN TO_DATE('" + fromDate + "',"
						+ "'dd-mm-yy') AND TO_DATE('" + toDate
						+ "','dd-mm-yy')+1";
					
					
				}
		
				if (status != null && !"".equals(status.trim())) {
					sql += " AND IGRS_CASE_REV_PNLTY_TXN_DTLS.CASE_STATUS='" + status + "'";
				}
				
		}
		else if(caseHead!=null && "CH_003".equalsIgnoreCase(caseHead) && caseType1!=null && "CASET_012".equalsIgnoreCase(caseType1) )
		{
			sql = CommonSQL.IGRS_VIEW5;
				if (caseId != null && !"".equals(caseId.trim())) {
					sql += " AND UPPER(IGRS_CASE_PNLTY70_TXN_DTLS.CASE_TXN_ID) "
							+ "like UPPER('%" + caseId + "%')";
				}
		
				if ((fromDate != null && toDate != null)
						&& (!"".equals(fromDate.trim()) && !"".equals(toDate.trim()))) {
					/*sql += " AND IGRS_CASE_PNLTY70_TXN_DTLS.CREATED_DATE "
							+ "BETWEEN TO_CHAR(TO_DATE('" + fromDate + "',"
							+ "'dd/mm/yyyy')) AND TO_CHAR(TO_DATE('" + toDate
							+ "','dd/mm/yyyy'))";*/
					
					
					sql += " AND IGRS_CASE_PNLTY70_TXN_DTLS.CREATED_DATE "
						+ "BETWEEN TO_DATE('" + fromDate + "',"
						+ "'dd-mm-yy') AND TO_DATE('" + toDate
						+ "','dd-mm-yy')+1";
					
					
				}
		
				if (status != null && !"".equals(status.trim())) {
					sql += " AND IGRS_CASE_PNLTY70_TXN_DTLS.CASE_STATUS='" + status + "'";
				}
				
		}
	
		

		ArrayList list = null;DBUtility dbUtil=null;
		try {
			log.info("Enter in to View page");
			dbUtil = new DBUtility();
			dbUtil.createStatement();

			//log.info("SQL:" + sql);
			list = dbUtil.executeQuery(sql);
		} catch (Exception x) {
			log.info("Exception in getCaseView() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getCaseDetail(String caseId, String lastActionID) {
		// commnented by shruti
		// String sql = CommonSQL.CASE_DETAIL;
		// end
		// added by shruti
		String sql = CommonSQL.CASE_DETAIL1;
		// end
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			// commented by shruti
			// log.info("SQL:" + CommonSQL.CASE_DETAIL);
			// end
			// added by shruti
			//log.info("SQL:" + CommonSQL.CASE_DETAIL1);
			// end of code
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getCaseDetail() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getViewNotice(String caseId, String lastActionID) {
		String sql = CommonSQL.VIEW_NOTICE;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			//log.info("SQL:" + CommonSQL.VIEW_NOTICE);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getViewNotice() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getViewOrder(String caseId, String lastActionID) {
		String sql = CommonSQL.VIEW_ORDER;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			//log.info("SQL:" + CommonSQL.VIEW_ORDER);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getViewOrder() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getExPartyOrder(String caseId, String lastActionID) {
		String sql = CommonSQL.EX_PARTY_ORDER;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			//log.info("SQL:" + CommonSQL.EX_PARTY_ORDER);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getExPartyOrder() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getViewDemandNotice(String caseId, String lastActionID) {
		String sql = CommonSQL.DEMAND_NOTICE;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			//log.info("SQL:" + CommonSQL.DEMAND_NOTICE);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getViewDemandNotice() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getAttachOrder(String caseId, String lastActionID) {
		String sql = CommonSQL.ATTACHMENT_ORDER;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			log.info("SQL:" + CommonSQL.ATTACHMENT_ORDER);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getAttachOrder() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getAuctionOrder(String caseId, String lastActionID) {
		String sql = CommonSQL.AUCTION_ORDER;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			log.info("SQL:" + CommonSQL.AUCTION_ORDER);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getAuctionOrder() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getRevenueCommission(String caseId, String lastActionID) {
		String sql = CommonSQL.REVENUE_COMMISION;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			log.info("SQL:" + CommonSQL.REVENUE_COMMISION);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getRevenueCommission() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getRevenueBoard(String caseId, String lastActionID) {
		String sql = CommonSQL.REVENUE_BOARD;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			log.info("SQL:" + CommonSQL.REVENUE_BOARD);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getRevenueBoard() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getHighCourt(String caseId, String lastActionID) {
		String sql = CommonSQL.HIGH_COURT;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			log.info("SQL:" + CommonSQL.HIGH_COURT);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getHighCourt() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getSupremeCourt(String caseId, String lastActionID) {
		String sql = CommonSQL.SUPREME_COURT;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			log.info("SQL:" + CommonSQL.SUPREME_COURT);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getSupremeCourt() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getCloseState(String caseId, String lastActionID) {
		// modified by shruti
		// String sql = CommonSQL.CLOSE_STATE;
		String sql = CommonSQL.CLOSE_STATE1;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = lastActionID;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to CaseView page");
			dbUtil.createPreparedStatement(sql);
			// log.info("SQL:" + CommonSQL.CLOSE_STATE);
			//log.info("SQL:" + CommonSQL.CLOSE_STATE1);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getCloseState() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	/*public ArrayList getAddCmt(String caseHead, String caseType1,
			String caseType2, String caseType3, String estampType,
			String estampSubType, String caseId, String fromDate,
			String toDate, String lastAction, String from, String to) {

		String sql = CommonSQL.IGRS_ADD_CMT;
		if (caseHead != null || caseType1 != null || caseType2 != null
				|| caseType3 != null || estampType != null
				|| estampSubType != null || caseId != null || fromDate != null
				|| toDate != null || lastAction != null || from != null
				|| to != null) {
			
			 * if (estampType!=null && !"".equals(estampType.trim())) { sql +="
			 * B.ESTAMP_TYPE_ID='"+estampType+"'"+" AND
			 * B.TRANSACTION_ID=A.ESTAMP_ID AND A.CASE_STATUS='OPEN' AND"; }
			 

			if (caseId != null && !"".equals(caseId.trim())) {
				sql += "  A.CASE_TXN_ID ='" + caseId + "'";
			}

			if ((fromDate != null && toDate != null)
					&& (!"".equals(fromDate.trim()) && !""
							.equals(toDate.trim()))) {
				sql += "  TRUNC(A.CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('"
						+ fromDate + "','dd/mm/yy')) AND TO_CHAR(TO_DATE('"
						+ toDate + "','dd/mm/yy'))";
			}

			if (lastAction != null && !"".equals(lastAction.trim())) {
				sql += " AND C.CASE_ACTION_NAME='" + lastAction + "'";
			}

			sql += " AND A.CASE_STATUS='OPEN')";

			if ((from != null && to != null)
					&& (!"".equals(from.trim()) && !"".equals(to.trim())))
				sql += " WHERE RN BETWEEN '" + from + "' AND '" + to + "'";
		}
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to AddComment page");
			dbUtil.createStatement();

			log.info("SQL:" + sql);
			list = dbUtil.executeQuery(sql);
		} catch (Exception x) {
			log.info("Exception in getAddCmt() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}*/

	public ArrayList getDrCmt(String caseId) {
		String sql = CommonSQL.COMMENT_UPDATE;
		String[] param = new String[1];
		param[0] = caseId;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter into CaseView page");
			dbUtil.createPreparedStatement(sql);
			log.info("SQL:" + CommonSQL.COMMENT_UPDATE);
			list = dbUtil.executeQuery(param);

		} catch (Exception x) {
			log.info("Exception in getDrCmt() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	public ArrayList getCmtDetail(String caseId) {
		String sql = CommonSQL.CMT_DETAIL;
		String[] param = new String[1];
		param[0] = caseId;
		ArrayList list = new ArrayList();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter into getCmtDetail");
			dbUtil.createPreparedStatement(sql);
			log.info("SQL: " + CommonSQL.CMT_DETAIL);
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			log.info("Exception in getCmtDetail() :-" + x);
		}
		finally {
			try {
				dbUtil.closeConnection();
			} catch (SQLException se) {
				log
						.error("SQL Exception at close Db Connection  at getCaseIds() : "
								+ se);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean finalcmt(String[] caseId, String roleId, String funId,
			String userId) {
		boolean finalcmt = false;DBUtility dbUtil=null;
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.FINAL_COMMENT);
			finalcmt = dbUtil.executeUpdate(caseId);
			if (finalcmt) {
				dbUtil.commit();
				igrsCommon.saveLogDet("IGRS_MOHALLA_VILLAGE_MASTER", "UPDATE","T", funId, userId, roleId);
			} 
			else
			{
				dbUtil.rollback();
				igrsCommon.saveLogDet("IGRS_MOHALLA_VILLAGE_MASTER", "UPDATE","T", funId, userId, roleId);
			}
		} catch (Exception e) {
			log.error("Exception in finalcmt: " + e);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return finalcmt;
	}

	public boolean getCmtUpdate(String[] caseId) {
		boolean cmtUpdate = false;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.ADD_COMPLIANCE);
			cmtUpdate = dbUtil.executeUpdate(caseId);
			if (cmtUpdate) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			log.error("Exception in getCmtUpdate:" + e);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return cmtUpdate;
	}

	// Revenue module - order
	public ArrayList getRevenueOrder(String caseId, String revnaction) {
		String sql = CommonSQL.IGRS_REVENUE_ORDER;
		String[] param = new String[2];
		param[0] = caseId;
		param[1] = revnaction;
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Enter in to getRevenueOrder page");
			dbUtil.createPreparedStatement(sql);
			log.info("SQL:" + CommonSQL.IGRS_REVENUE_ORDER);
			list = dbUtil.executeQuery(param);
		} catch (Exception x) {
			log.info("Exception in getRevenueOrder() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	// Revenue Module - get last dr comment from igrs_case_txn_comment
	public ArrayList getRevdrComment(String caseId) {
		String sql = CommonSQL.IGRS_REVENUE_DR_LASTCMT;
		log.info("caseId " + caseId);
		String[] param = new String[1];
		param[0] = caseId;
		ArrayList list = null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			log.info("Entered into DR_comment()");
			dbUtil.createPreparedStatement(sql);
			log.info("sql:" + CommonSQL.IGRS_REVENUE_DR_LASTCMT);
			list = dbUtil.executeQuery(param);
		} catch (Exception e) {
			log.info("Exception in drCommetn: " + e);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	// Revenue Module - Update DR comments only
	public boolean insertRevdrCmt(String[] params) {
		boolean revenuedrcmt = false;DBUtility dbUtil=null;
		try {
			log.info("DAO - Inside insertRevdrCmt()");
			dbUtil = new DBUtility();
			dbUtil
					.createPreparedStatement(CommonSQL.IGRS_REVENUE_INSERT_DRCMTONLY);
			revenuedrcmt = dbUtil.executeUpdate(params);
			dbUtil.commit();
		} catch (Exception x) {
			log.error("Exception in insertRevdrCmt() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return revenuedrcmt;
	}

	// Revenue Module - generate orderid only
	public boolean getRevorderId(String[] notice, String[] status, String[] cmt) {
		boolean noticeSend = false;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.IGRS_REV_ORDER_ID);
			log.info("SQL=" + CommonSQL.IGRS_REV_ORDER_ID);
			noticeSend = dbUtil.executeUpdate(notice);
			if (noticeSend) {
				dbUtil.createPreparedStatement(CommonSQL.IGRS_REV_HRDATE);
				noticeSend = dbUtil.executeUpdate(status);
				if (noticeSend) {
					dbUtil.createPreparedStatement(CommonSQL.IGRS_REV_TXN_CMT);
					noticeSend = dbUtil.executeUpdate(cmt);
				}
			}

			if (noticeSend) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			log.error("Exception in getRevorderId :" + e);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return noticeSend;
	}

	// Revenue Module - get the revenue head from igrs_case_revenue_head_master
	public ArrayList getRevenueList(String caseHead) {
		ArrayList list = null;
		IGRSCommon igrsCommon;DBUtility dbUtil=null;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getRevenueList(caseHead);
		} catch (Exception e) {
			log.error("Exception in getRevheadlistDao():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	// Revenue Module - get the section head from igrs_case_section_head_master
	public ArrayList getSectionList(String caseType) {
		ArrayList list = null;
		IGRSCommon igrsCommon;DBUtility dbUtil=null;
		try {
			igrsCommon = new IGRSCommon();
			list = igrsCommon.getSectionList(caseType);
		} catch (Exception e) {
			log.error("Exception in getSectionList():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	// Revenue Module - Close Case
	public boolean getRevcloseCase(String[] params) {
		boolean revenuedrcmt = false;DBUtility dbUtil=null;
		try {
			log.info("DAO - Inside IGRS_REVENUE_CLOSE_CASE()");
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.IGRS_REVENUE_CLOSE_CASE);
			revenuedrcmt = dbUtil.executeUpdate(params);
			if (revenuedrcmt) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
		} catch (Exception x) {
			log.error("IGRS_REVENUE_CLOSE_CASE() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return revenuedrcmt;
	}

	// Revenue Module - get the revenue report id-- in use 2013
	public ArrayList getReportList(String caseType, String caseId,
			String fdate, String tdate, String from, String to) {
		DBUtility dbUtil=null;
		ArrayList list = null;
		log.info("caseType in dao:-" + caseType);
		try {
			dbUtil = new DBUtility();
			if ((caseType != null) && !"".equals(caseType.trim())) 
			{
				if (caseType.equals("CASET_001"))
				{
					if ((caseId != null && caseType != null)&& (!"".equals(caseId.trim()) && !"".equals(caseType.trim()))) 
					{
						String[] param={caseId};
						String sql=CommonSQL.GETAGMPREPORTIDBASEDDTLS;
						dbUtil.createPreparedStatement(sql);
						list=dbUtil.executeQuery(param);
					}
					if ((fdate != null && tdate != null && caseType != null) && (!"".equals(fdate.trim())
							&& !"".equals(tdate.trim()) && !"".equals(caseType.trim()))) 
					{
							String[] param={fdate,tdate};
						String sql=CommonSQL.GETAGMPREPORTDURATIONBASEDDTLS;
						dbUtil.createPreparedStatement(sql);
						list=dbUtil.executeQuery(param);
					}
				}
				if (caseType.equals("CASET_002"))
				{
					if ((caseId != null && caseType != null)&& (!"".equals(caseId.trim()) && !"".equals(caseType.trim()))) 
					{
						String[] param={caseId};
						String sql=CommonSQL.GETINTERNALREPORTIDBASEDDTLS;
						dbUtil.createPreparedStatement(sql);
						list=dbUtil.executeQuery(param);
					}
					if ((fdate != null && tdate != null && caseType != null) && (!"".equals(fdate.trim())
							&& !"".equals(tdate.trim()) && !"".equals(caseType.trim()))) 
					{
							String[] param={fdate,tdate};
						String sql=CommonSQL.GETINTERNALREPORTDURATIONBASEDDTLS;
						dbUtil.createPreparedStatement(sql);
						list=dbUtil.executeQuery(param);
					}
				}
				if (caseType.equals("CASET_003"))
				{
					if ((caseId != null && caseType != null)&& (!"".equals(caseId.trim()) && !"".equals(caseType.trim()))) 
					{
						String[] param={caseId};
						String sql=CommonSQL.GETPOIREPORTIDBASEDDTLS;
						dbUtil.createPreparedStatement(sql);
						list=dbUtil.executeQuery(param);
					}
					if ((fdate != null && tdate != null && caseType != null) && (!"".equals(fdate.trim())
							&& !"".equals(tdate.trim()) && !"".equals(caseType.trim()))) 
					{
							String[] param={fdate,tdate};
						String sql=CommonSQL.GETPOIREPORTDURATIONBASEDDTLS;
						dbUtil.createPreparedStatement(sql);
						list=dbUtil.executeQuery(param);
					}
				}
				if (caseType.equals("CASET_004"))
				{
					if ((caseId != null && caseType != null)&& (!"".equals(caseId.trim()) && !"".equals(caseType.trim()))) 
					{
						String[] param={caseId};
						String sql=CommonSQL.GETPOREPORTIDBASEDDTLS;//modified by roopam-5feb2015-coz of change in pot
						dbUtil.createPreparedStatement(sql);
						list=dbUtil.executeQuery(param);
					}
					if ((fdate != null && tdate != null && caseType != null) && (!"".equals(fdate.trim())
							&& !"".equals(tdate.trim()) && !"".equals(caseType.trim()))) 
					{
							String[] param={fdate,tdate};
						String sql=CommonSQL.GETPOREPORTDURATIONBASEDDTLS;//modified by roopam-5feb2015-coz of change in pot
						dbUtil.createPreparedStatement(sql);
						list=dbUtil.executeQuery(param);
					}
				}
				if (caseType.equals("CASET_005"))
				{
					if ((caseId != null && caseType != null)&& (!"".equals(caseId.trim()) && !"".equals(caseType.trim()))) 
					{
						String[] param={caseId};
						String sql=CommonSQL.GETSRIMPOUNDREPORTIDDTLS;
						dbUtil.createPreparedStatement(sql);
						list=dbUtil.executeQuery(param);
					}
					if ((fdate != null && tdate != null && caseType != null) && (!"".equals(fdate.trim())
							&& !"".equals(tdate.trim()) && !"".equals(caseType.trim()))) 
					{
							String[] param={fdate,tdate};
						String sql=CommonSQL.GETSRIMPOUNDREPORTDURATIONDTLS;
						dbUtil.createPreparedStatement(sql);
						list=dbUtil.executeQuery(param);
					}
				}
				if (caseType.equals("CASET_007"))
				{
				if ((caseId != null && caseType != null)&& (!"".equals(caseId.trim()) && !"".equals(caseType.trim()))) 
				{
					String[] param={caseId};
					String sql=CommonSQL.GETESTAMPREFUNDREPORTIDDTLS;
					dbUtil.createPreparedStatement(sql);
					list=dbUtil.executeQuery(param);
						}
				if ((fdate != null && tdate != null && caseType != null) && (!"".equals(fdate.trim()) 
						&& !"".equals(tdate.trim()) && !"".equals(caseType.trim()))) 
				{
					String[] param={fdate,tdate};
					String sql=CommonSQL.GETESTAMPREFUNDREPORTDURATIONDTLS;
					dbUtil.createPreparedStatement(sql);
					list=dbUtil.executeQuery(param);
				}
					
				}
				if (caseType.equals("CASET_010")) 
				{
					if ((caseId != null && caseType != null)&& (!"".equals(caseId.trim()) && !"".equals(caseType.trim()))) 
						{	
						String[] param={caseId};
						String sql=CommonSQL.GETTIMEBARREDIDDTLS;
						dbUtil.createPreparedStatement(sql);
						list=dbUtil.executeQuery(param);
						}
				if ((fdate != null && tdate != null && caseType != null) && (!"".equals(fdate.trim()) 
						&& !"".equals(tdate.trim()) && !"".equals(caseType.trim()))) 
				{
					String[] param={fdate,tdate};
					String sql=CommonSQL.GETTIMEBARREDDURATIONDTLS;
					dbUtil.createPreparedStatement(sql);
					list=dbUtil.executeQuery(param);
				}
					}
			}
			
		} catch (Exception x) {
			log.info("Exception in IGRS_CASE_REVENUE_REPORT() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	
	
	//****************************************************
	// Revenue Module - get the revenue report id--- in use 2013
	public ArrayList getRefundList(String caseType, String caseId,
			String fdate, String tdate, String from, String to,String estampType,String language) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			if ((caseType != null) && !"".equals(caseType.trim())) 
			{
			if (caseType.equals("CASET_007")) {
				
				if ((caseId != null && caseType != null)&& (!"".equals(caseId.trim()) 
						&& !"".equals(caseType.trim()))) 
				{
					String[] param = { caseId };
					String sql="";
					if("judicial".equalsIgnoreCase(estampType))
					{
						if("en".equalsIgnoreCase(language))
						{
							sql = CommonSQL.GETESTAMPREFUNDREPORTIDDTLS;
						}
						else
						{
							sql = CommonSQL.GETESTAMPREFUNDREPORTIDDTLS_H;
						}
					}
					else
					{
						if("en".equalsIgnoreCase(language))
						{
							sql = CommonSQL.ESTAMP_RR_DEACTIVATE;
						}
						else
						{
							sql = CommonSQL.ESTAMP_RR_DEACTIVATE_H;
						}
						
					}
					dbUtil.createPreparedStatement(sql);
					list = dbUtil.executeQuery(param);
				}
				if ((fdate != null && tdate != null && caseType != null) && (!"".equals(fdate.trim())
								&& !"".equals(tdate.trim()) && !"".equals(caseType.trim()))) 
				{
					String[] param = { fdate, tdate };
					String sql="";
					if("judicial".equalsIgnoreCase(estampType))
					{
						if("en".equalsIgnoreCase(language))
						{
						sql = CommonSQL.GETESTAMPREFUNDREPORTDURATIONDTLS;
						}
						else
						{
							sql = CommonSQL.GETESTAMPREFUNDREPORTDURATIONDTLS_H;
						}
					}
					else
					{
						if("en".equalsIgnoreCase(language))
						{
						sql = CommonSQL.ESTAMP_RR_DEACTIVATE_DURATIONDTLS;
						}
						else
						{
						sql = CommonSQL.ESTAMP_RR_DEACTIVATE_DURATIONDTLS_H;	
						}
					}
					dbUtil.createPreparedStatement(sql);
					list = dbUtil.executeQuery(param);
				}
				
			}
		}
		} catch (Exception x) {
			log.info("Exception in IGRS_CASE_REFUND_REPORT() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	
	///****************************************************end 

	//**********************************8 others-penalty sec 45
	
	// Revenue Module - get the revenue report id--- in use 2013
	public ArrayList getPenaltyList(String caseType, String penaltyId,
			String caseId, String fdate, String tdate, String from, String to) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			if ((caseType != null) && !"".equals(caseType.trim())) 
			{
				if (caseType.equals("CASET_011"))
				{
					if ((caseId != null && caseType != null)&& (!"".equals(caseId.trim()) 
							&& !"".equals(caseType.trim()))) 
					{
						String[] param = { caseId };
						String sql = CommonSQL.GETOTHERSCASEIDDTLS;
						dbUtil.createPreparedStatement(sql);
						list = dbUtil.executeQuery(param);
					}
					if ((penaltyId != null && caseType != null)&& (!"".equals(penaltyId.trim())
							&& !"".equals(caseType.trim()))) 
					{
						String[] param = { penaltyId };
						String sql = CommonSQL.GETOTHERSPENALTYIDDTLS;
						dbUtil.createPreparedStatement(sql);
						list = dbUtil.executeQuery(param);
					}
					if ((fdate != null && tdate != null && caseType != null)&& (!"".equals(fdate.trim())
									&& !"".equals(tdate.trim()) && !"".equals(caseType.trim()))) 
					{
						String[] param = { fdate,tdate };
						String sql = CommonSQL.GETOTHERSPENALTYDURATIONDTLS;
						dbUtil.createPreparedStatement(sql);
						list = dbUtil.executeQuery(param);
					}
				}
			}
			if (caseType.equals("CASET_012")) {

				if ((caseType != null) && !"".equals(caseType.trim())) {
					if ((caseId != null && caseType != null)
							&& (!"".equals(caseId.trim()) && !"".equals(caseType
									.trim()))) {

						String[] param = { caseId };
						String sql = CommonSQL.GETOTHERSCASE70IDDTLS;
						dbUtil.createPreparedStatement(sql);
						list = dbUtil.executeQuery(param); 
					}
					if ((penaltyId != null && caseType != null)
							&& (!"".equals(penaltyId.trim()) && !"".equals(caseType
									.trim()))) {

						String[] param = { penaltyId };
						String sql = CommonSQL.GETOTHERSPENALTY70IDDTLS;
						dbUtil.createPreparedStatement(sql);
						list = dbUtil.executeQuery(param);
					}
					if ((fdate != null && tdate != null && caseType != null)
							&& (!"".equals(fdate.trim())
									&& !"".equals(tdate.trim()) && !""
									.equals(caseType.trim()))) {
						String[] param = { fdate,tdate };
						String sql = CommonSQL.GETOTHERSPENALTY70DURATIONDTLS;
						dbUtil.createPreparedStatement(sql);
						list = dbUtil.executeQuery(param);

					}
				}
			}
		} catch (Exception x) {
			log.info("Exception in IGRS_CASE_REVENUE_REPORT() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	//License Cancellation--in use 2013
	public ArrayList getlicensedUserList(String caseType, String licenseId,
			String fdate, String tdate, String from, String to,String language) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			if((caseType != null) && !"".equals(caseType.trim())) 
			{
				if (caseType.equals("CASET_009"))
				{
						if ((licenseId != null && caseType != null)
								&& (!"".equals(licenseId.trim()) && !"".equals(caseType.trim())))
						{
							String[] param={licenseId};
							String sql="";
							if("en".equalsIgnoreCase(language))
							{
							sql=CommonSQL.GETLICENSECANCELLATIONIDDTLS;
							}
							else
							{
							sql=CommonSQL.GETLICENSECANCELLATIONIDDTLS_H;	
							}
							dbUtil.createPreparedStatement(sql);
							list=dbUtil.executeQuery(param);
						}
						if ((fdate != null && tdate != null && caseType != null)&& (!"".equals(fdate.trim())
										&& !"".equals(tdate.trim()) && !"".equals(caseType.trim()))) 
						{	
							String[] param={fdate,tdate};
							String sql="";
							if("en".equalsIgnoreCase(language))
							{
							sql=CommonSQL.GETLICCANCLDURATIONDTLS;
							}
							else
							{
							sql=CommonSQL.GETLICCANCLDURATIONDTLS_H;
							}
							dbUtil.createPreparedStatement(sql);
							list=dbUtil.executeQuery(param);
						}
				}
			}
		} catch (Exception x) {
			log.info("Exception in IGRS_CASE_REVENUE_REPORT() :- " + x);
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	
	
	//*********************************************end
	// Revenue Module - generate revenue case id
	public boolean getRevreportId(String[] txn, String[] cmt, HashMap map,
			String[] actionmap) {
		boolean casemonitoring = false;DBUtility dbUtil=null;
		try {

			log.info("I am in dao getRevreportId========");
			log.info("before inserting DR-Comments ");
			dbUtil = new DBUtility();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			dbUtil.createPreparedStatement(CommonSQL.IGRS_REVENUE_TXN_DETAILS);
			casemonitoring = dbUtil.executeUpdate(txn);
			if (casemonitoring) {
				dbUtil.createPreparedStatement(CommonSQL.IGRS_REVENUE_ACTION_MAP);
				casemonitoring = dbUtil.executeUpdate(actionmap);
				if (casemonitoring)
				{
					dbUtil.createPreparedStatement(CommonSQL.IGRS_REVENUE_TXN_COMMENTS);
					casemonitoring = dbUtil.executeUpdate(cmt);
					
					//modified by shruti---10 july 2014)
					if(casemonitoring && txn[3]!=null && "CASET_009".equalsIgnoreCase(txn[3]))
					{
						String[] param={txn[6]};
						dbUtil.createPreparedStatement(CommonSQL.UPDATE_IGRS_SP_DETLS);
						casemonitoring = dbUtil.executeUpdate(param);
					}
					//end
					
					//modified by roopam---9 feb 2015)
					if(casemonitoring && txn[3]!=null && "CASET_004".equalsIgnoreCase(txn[3]))
					{
						String[] param={txn[1],txn[6]};
						dbUtil.createPreparedStatement(CommonSQL.UPDATE_POT_DETLS);
						casemonitoring = dbUtil.executeUpdate(param);
					}
					//end
					
					
//commented-25th july 2013
				/*	if (casemonitoring) 
					{
						log.debug("map:");
						log.debug("I am in dao =====");
						if (map != null) 
						{
							log.info("I am in dao map noteql to null ======");
							Iterator I = map.entrySet().iterator();
							log.debug("I.hasNext():-" + I.hasNext());
							while (I.hasNext())
							{
								Map.Entry me = (Map.Entry) I.next();
								log.debug("getKey :-" + me.getKey() + ":"+ me.getValue());
								CaseMonitoringDTO dto = (CaseMonitoringDTO) me.getValue();
								ArrayList columnNames = new ArrayList();
								ArrayList columnValue = new ArrayList();
								columnNames.add("DOC_TYPE_ID");
								columnNames.add("DOC_STATUS");
								columnNames.add("CASE_ACTION_TXN_ID");
								columnNames.add("DOC_FILE_NAME");
								columnNames.add("CREATED_BY");
								// below line commented by shruti-to avoid
								// exception23rd may 2013
								// columnValue.add(pr.getValue("DOCUMENT_TYPE_MAH"));
								// added by shruti
								columnValue.add("23");
								// end
								String fileName = (String) me.getKey();
								FormFile file = dto.getFileUpload();
								String filePath = dto.getFilePath();
								log.debug("filePath:" + filePath + ":"+ file.getFileSize());
								filePath = filePath.substring(0, filePath.indexOf(fileName));
								log.debug("fileName:" + fileName + ":"+ filePath);
								columnValue.add("A");
								columnValue.add(actionmap[5]);
								columnValue.add(me.getKey());
								columnValue.add(actionmap[2]);
								casemonitoring = dbUtil.saveDRODocuments(
										columnNames, columnValue,
										"IGRS_CASE_TXN_DOC_DETAILS",
										"DOC_OBJECT", "CASE_TXN_ID",
										actionmap[0], file, filePath);

								if (!casemonitoring) 
								{
									break;
								}
								
							}
						}
					}
					*/
					
					
				}
			}
			log.info("before inserting getRevreportId :-" + casemonitoring);
			if (casemonitoring) {
				log.debug("inside commit");
				dbUtil.commit();
			} else {
				log.debug("inside rollback");
				dbUtil.rollback();
			}
			log.info("after inserting getRevreportId");
		} catch (Exception x) {
			log.info("Exception in getRevreportId() :- " + x);
			x.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return casemonitoring;
	}
	
	
	
	//added by shruti-8th july 2013
	public boolean getRefundCaseId(String[] txn,
			String[] actionmap) {
		boolean casemonitoring = false;DBUtility dbUtil=null;
		try {
			log.info("I am in dao getRefundCaseId========");
			dbUtil = new DBUtility();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			//------------ADDED BY SATBIR KUMAR--------
			
			String[] cmt = new String[1];
			
			cmt [0]=txn[19];
			
			dbUtil.createPreparedStatement(CommonSQL.IGRS_UPDATE_ELIGIBLITY_STATUS);
			casemonitoring = dbUtil.executeUpdate(cmt);
			//------------END OF ADDITION--------
			dbUtil.createPreparedStatement(CommonSQL.IGRS_REFUND_TXN_DETAILS);
			casemonitoring = dbUtil.executeUpdate(txn);
			if (casemonitoring) {
				dbUtil.createPreparedStatement(CommonSQL.IGRS_REVENUE_ACTION_MAP);
				casemonitoring = dbUtil.executeUpdate(actionmap);
					}
			log.info("before inserting getRefundCaseId :-" + casemonitoring);
			if (casemonitoring) {
				log.debug("inside commit");
				dbUtil.commit();
			} 
			else {
				log.debug("inside rollback");
				dbUtil.rollback();
			}
			log.info("after inserting getRefundCaseId");
		} catch (Exception x) {
			log.info("Exception in getRefundCaseId() :- " + x);
			x.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return casemonitoring;
	}
	//end
	
	//adedd-20th july 2013
	
	//added by shruti-8th july 2013
	public boolean getRevisionId(String[] txn,
			String[] actionmap) {
		boolean casemonitoring = false;DBUtility dbUtil=null;
		try {
			log.info("I am in dao getRefundCaseId========");
			dbUtil = new DBUtility();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			dbUtil.createPreparedStatement(CommonSQL.IGRS_PENALTY_TXN_DTLS);
			casemonitoring = dbUtil.executeUpdate(txn);
			if (casemonitoring) {
				dbUtil.createPreparedStatement(CommonSQL.IGRS_REVENUE_ACTION_MAP);
				casemonitoring = dbUtil.executeUpdate(actionmap);
					}
			log.info("before inserting getRevisionId :-" + casemonitoring);
			if (casemonitoring) {
				log.debug("inside commit");
				dbUtil.commit();
				
			} 
			else {
				log.debug("inside rollback");
				dbUtil.rollback();
			}
			log.info("after inserting getRevisionId");
		} catch (Exception x) {
			log.info("Exception in getRevisionId() :- " + x);
			x.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return casemonitoring;
	}
	//end

	//added by shruti-22th july 2013
	public boolean getRevisionIdSec70(String[] txn,
			String[] actionmap) {
		boolean casemonitoring = false;DBUtility dbUtil=null;
		try {
			log.info("I am in dao getRefundCaseId========");
			dbUtil = new DBUtility();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			dbUtil.createPreparedStatement(CommonSQL.IGRS_PENALTY_SEC70_TXN_DTLS);
			casemonitoring = dbUtil.executeUpdate(txn);
			if (casemonitoring) {
				dbUtil.createPreparedStatement(CommonSQL.IGRS_REVENUE_ACTION_MAP);
				casemonitoring = dbUtil.executeUpdate(actionmap);
					}
			log.info("before inserting getRevisionId :-" + casemonitoring);
			if (casemonitoring) {
				log.debug("inside commit");
				dbUtil.commit();
				
			} 
			else {
				log.debug("inside rollback");
				dbUtil.rollback();
			}
			log.info("after inserting getRevisionId");
		} catch (Exception x) {
			log.info("Exception in getRevisionId() :- " + x);
			x.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return casemonitoring;
	}
	//end


	//adedd by shruti-18thjuly 2013
	public boolean getOthersCaseId(String[] txn,
			String[] actionmap) {
		boolean casemonitoring = false;
		String [] param=new String[1];
		param[0]=txn[14];
		DBUtility dbUtil=null;
		try {
			log.info("I am in dao getRefundCaseId========");
			dbUtil = new DBUtility();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			dbUtil.createPreparedStatement(CommonSQL.IGRS_CASE_OTHERS_INSERT);
			casemonitoring = dbUtil.executeUpdate(txn);
			if (casemonitoring) {
				dbUtil.createPreparedStatement(CommonSQL.IGRS_REVENUE_ACTION_MAP);
				casemonitoring = dbUtil.executeUpdate(actionmap);
				//added by shruti-3rd july 2013
				if(casemonitoring)
				{
					dbUtil.createPreparedStatement(CommonSQL.IGRS_UPDATE_OPEN_CASE_TIME_BARRED);
					casemonitoring=dbUtil.executeUpdate(param);
				}
				//end
					}
			log.info("before inserting getRefundCaseId :-" + casemonitoring);
			if (casemonitoring) {
				log.debug("inside commit");
				dbUtil.commit();
			} 
			else {
				log.debug("inside rollback");
				dbUtil.rollback();
			}
			log.info("after inserting getRefundCaseId");
		} catch (Exception x) {
			log.info("Exception in getRefundCaseId() :- " + x);
			x.printStackTrace();
		} finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return casemonitoring;
	}
	//end

	//end
	/**
	 * @since : 07-05-2008 Method : getVoucherId Description : This method
	 *        inserts the Voucher Details to DB
	 * @param ordto
	 *            : CaseMonitoringDTO
	 * @return list : String
	 * @throws : Exception
	 */
	// public ArrayList getTotalrowsDao(String caseHead, String caseType1,
	/*public ArrayList getRevTotal(String caseHead, String caseType1,
			String caseId, String fromDate, String toDate, String lastAction) {
		ArrayList list = null;
		String str1 = null;
		String str2 = null;
		log.info("getTotalrowsDao() = " + caseId);
		log.info("LastAction -------= " + lastAction);
		// case head - revenue
		if (caseHead != null || caseId != null || fromDate != null
				|| toDate != null || lastAction != null) {
			if (caseHead.equals("1")) {
				String sql1 = CommonSQL.IGRS_CASE_REVENUE_TOTAL_ROWS;
				if (caseId != null && !"".equals(caseId.trim())) {
					sql1 += " CASE_TXN_ID LIKE '" + caseId + "%'";
				}
				if ((fromDate != null && toDate != null)
						&& (!"".equals(fromDate.trim()) && !"".equals(toDate
								.trim()))) {
					sql1 += " TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE(";
					sql1 += "'" + fromDate
							+ "','dd/mm/yy')) AND TO_CHAR(TO_DATE(";
					sql1 += "'" + toDate + "','dd/mm/yy'))";
				}
				if (lastAction != null && !"".equals(lastAction.trim())) {
					sql1 += " AND CASE_ACTION_ID='" + lastAction + "'";
				}
				if (caseType1.equals("1")) {
					if ((caseType1 != null) && !"".equals(caseType1.trim())) {
						sql1 += " AND CASE_TXN_ID LIKE 'RGR%'";
					}
				}
				if (caseType1.equals("2")) {
					if ((caseType1 != null) && !"".equals(caseType1.trim())) {
						sql1 += " AND CASE_TXN_ID LIKE 'INT%'";
					}
				}
				if (caseType1.equals("3")) {
					if ((caseType1 != null) && !"".equals(caseType1.trim())) {
						sql1 += " AND CASE_TXN_ID LIKE 'POIR%'";
					}
				}
				if (caseType1.equals("4")) {
					if ((caseType1 != null) && !"".equals(caseType1.trim())) {
						sql1 += " AND CASE_TXN_ID LIKE 'POR%'";
					}
				}
				if (caseType1.equals("5")) {
					if ((caseType1 != null) && !"".equals(caseType1.trim())) {
						sql1 += " AND CASE_TXN_ID LIKE 'DOC%'";
					}
				}
				if (caseType1.equals("6")) {
					if ((caseType1 != null) && !"".equals(caseType1.trim())) {
						sql1 += " AND CASE_TXN_ID LIKE 'CASE%'";
					}
				}
				sql1 += " AND CASE_STATUS='OPEN'";
				try {
					dbUtil = new DBUtility();
					log.info("inside getTotalrowsDao() function...");
					dbUtil.createStatement();
					log.info("SQL = " + sql1);
					list = dbUtil.executeQuery(sql1);
				} catch (Exception e) {
					log.info("Exception in getTotalrowsDao():" + e);
				} finally {
					try {
						if (dbUtil != null) {
							dbUtil.closeConnection();
						}
					} catch (Exception cx) {
						log.debug(cx);
					}
				}
			}
		}
		return list;
	}*/

	/*// Revenue Module - getRevtotalRow
	public ArrayList getRevtotalRow(String caseType1, String caseId,
			String fromDate, String toDate) {
		ArrayList list = null;
		String str1 = null;
		String str2 = null;
		log.info("getTotalrowsDao() = " + caseType1);
		if (caseType1 != null || caseId != null || fromDate != null
				|| toDate != null) {
			String sql1 = CommonSQL.IGRS_REVENUE_TOTAL_ROWS;
			if (caseId != null && !"".equals(caseId.trim())) {
				sql1 += " PHYSICAL_REPORT_ID LIKE '" + caseId + "%'";
			}
			if ((fromDate != null && toDate != null)
					&& (!"".equals(fromDate.trim()) && !""
							.equals(toDate.trim()))) {
				sql1 += " TRUNC(AUDIT_FROM_DATE) BETWEEN TO_CHAR(";
				sql1 += " TO_DATE('" + fromDate + "','dd/mm/yy')) AND ";
				sql1 += " TO_CHAR(TO_DATE('" + toDate + "','dd/mm/yy'))";
				sql1 += " AND TRUNC(AUDIT_TO_DATE) BETWEEN TO_CHAR(";
				sql1 += " TO_DATE('" + fromDate + "','dd/mm/yy')) AND ";
				sql1 += " TO_CHAR(TO_DATE('" + toDate + "','dd/mm/yy'))";
			}
			if (caseType1.equals("1")) {
				if ((caseType1 != null) && !"".equals(caseType1.trim())) {
					sql1 += " AND TRANSACTION_ID LIKE 'AG%'";
				}
			}
			if (caseType1.equals("2")) {
				if ((caseType1 != null) && !"".equals(caseType1.trim())) {
					sql1 += " AND TRANSACTION_ID LIKE 'IN%'";
				}
			}
			if (caseType1.equals("3")) {
				if ((caseType1 != null) && !"".equals(caseType1.trim())) {
					sql1 += " AND TRANSACTION_ID LIKE 'POR%'";
				}
			}
			if (caseType1.equals("4")) {
				if ((caseType1 != null) && !"".equals(caseType1.trim())) {
					sql1 += " AND TRANSACTION_ID LIKE 'POI%'";
				}
			}
			if (caseType1.equals("5")) {
				if ((caseType1 != null) && !"".equals(caseType1.trim())) {
					sql1 += " AND TRANSACTION_ID LIKE 'D%'";
				}
			}
			if (caseType1.equals("6")) {
				if ((caseType1 != null) && !"".equals(caseType1.trim())) {
					sql1 += " AND TRANSACTION_ID LIKE 'C%'";
				}
			}
			try {
				log.info("inside getTotalrowsDao() function...");
				dbUtil = new DBUtility();
				dbUtil.createStatement();
				log.info("SQL = " + sql1);
				list = dbUtil.executeQuery(sql1);
			} catch (Exception e) {
				log.info("Exception in getTotalrowsDao():" + e);
			} finally {
				try {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				} catch (Exception cx) {
					log.debug(cx);
				}
			}
		}
		return list;
	}*/

	// added by shruti
	public String getLoggedInDRDistrict(String userid) throws Exception {
		String did = "";DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String requserid[] = { userid };
			dbUtil.createPreparedStatement(CommonSQL.SELECTDRDISTRICT);
			did = dbUtil.executeQry(requserid);
		} catch (Exception exception) {
			log.info("Exception in SPDAO-user district " + exception);
		} finally {
			dbUtil.closeConnection();
		}
		return did;
	}
	
	public String getRegNumber(String penalty) throws Exception {
		String did = "";DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String requserid[] = { penalty };
			dbUtil.createPreparedStatement(CommonSQL.GET_REG_ID);
			did = dbUtil.executeQry(requserid);
		} catch (Exception exception) {
			log.info("Exception in SPDAO-user district " + exception);
		} finally {
			dbUtil.closeConnection();
		}
		return did;
	}

	public ArrayList getCaseRegDet(String UserId,String offId,String language) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String[] userid = { offId };
			if("en".equalsIgnoreCase(language))
			{
			dbUtil.createPreparedStatement(CommonSQL.SELECTCASEREGDET);
			}
			else
			{
			dbUtil.createPreparedStatement(CommonSQL.SELECTCASEREGDET_H);
			}
			list = dbUtil.executeQuery(userid);
		} catch (Exception e) {
			log.error("Exception in getSectionList():" + e);
		}finally {
			try{
			dbUtil.closeConnection();
			}catch(Exception e){
				
			}
		}
		return list;
	}

	// audit and inspection details
//modified--22nd oct 2013
	public ArrayList getAuditDetails(CaseMonitoringDTO dto) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] reportid = { dto.getReportId(),dto.getObjId() };
			
			dbUtil.createPreparedStatement(CommonSQL.GETAUDITINPECTIONDETAILSNEW);
			list = dbUtil.executeQuery(reportid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	public ArrayList getAuditDate(CaseMonitoringDTO dto) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] reportid = { dto.getReportId() };
			
			dbUtil.createPreparedStatement(CommonSQL.GET_AUDIT_DATE);
			list = dbUtil.executeQuery(reportid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	public ArrayList getReqDefStampDuty(CaseMonitoringDTO dto) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] reportid = { dto.getReportId(),dto.getParaId() };
			
			dbUtil.createPreparedStatement(CommonSQL.GET_REG_STAMP_DUTY);
			list = dbUtil.executeQuery(reportid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	//added by shruti---1 july 2014
	public ArrayList getRegDetails(CaseMonitoringDTO dto) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] reportid = {dto.getRegId()};		
			dbUtil.createPreparedStatement(CommonSQL.GETREGDETAILS);
			list = dbUtil.executeQuery(reportid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	public ArrayList getRegDetailsTimeBarred(CaseMonitoringDTO dto) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] reportid = {dto.getRegId()};		
			dbUtil.createPreparedStatement(CommonSQL.GETREGDETAILSTIME_BARRED);
			list = dbUtil.executeQuery(reportid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getRegDetailsTimeBarred():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	public ArrayList getRegPartyDetails(CaseMonitoringDTO dto) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] reportid = {dto.getRegId()};		
			dbUtil.createPreparedStatement(CommonSQL.GETREGPARTYDETAILS);
			list = dbUtil.executeQuery(reportid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	//end
	//added by roopam---5 feb 2015
	public ArrayList getPOTDetails(CaseMonitoringDTO dto) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] reportid = {dto.getReportId()};		
			dbUtil.createPreparedStatement(CommonSQL.GETPOTDETAILS);
			list = dbUtil.executeQuery(reportid);
			log.debug(list);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getPOTDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	public String getUserName(String userId) {
		String val = new String();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] param = {userId};		
			dbUtil.createPreparedStatement(CommonSQL.GET_USERNAME);
			val = dbUtil.executeQry(param);
			log.debug(val);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getPOTDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return val;
	}
	public String getOfficeNameEng(String ofcId) {
		String val = new String();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] param = {ofcId};		
			dbUtil.createPreparedStatement(CommonSQL.GET_OFCNAME);
			val = dbUtil.executeQry(param);
			log.debug(val);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getPOTDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return val;
	}
	public String getOfficeNameHindi(String ofcId) {
		String val = new String();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] param = {ofcId};		
			dbUtil.createPreparedStatement(CommonSQL.GET_OFCNAME_hi);
			val = dbUtil.executeQry(param);
			log.debug(val);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getPOTDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return val;
	}
	//added by roopam---9 feb 2015
	public ArrayList getPOTPhysicalStampDetails(String reportId) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] reportid = {reportId};		
			dbUtil.createPreparedStatement(CommonSQL.GET_POT_PHYSICAL_STAMP_DETAILS);
			list = dbUtil.executeQuery(reportid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getPOTPhysicalStampDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	
	public String getEStampCodeForCaseId(String caseId) {
		String list = "";
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] param = {caseId};		
			dbUtil.createPreparedStatement(CommonSQL.GET_ESTAMP_CODE_FOR_CASE_ID);
			list = dbUtil.executeQry(param);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getEStampCodeForCaseId():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	
	public String EstampDataList(CaseMonitoringDTO dto) {
		String list = new String();
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] reportid = {dto.getEstampId()};		
			dbUtil.createPreparedStatement(CommonSQL.GETESTAMPAMOUNTDETAILS);
			list = dbUtil.executeQry(reportid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	//end

	//added by shruti----14 oct 2014
	public ArrayList getAmountDetails(CaseMonitoringDTO dto) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] reportid = {dto.getReportId()};		
			dbUtil.createPreparedStatement(CommonSQL.GETPENALTYAMOUNT);
			list = dbUtil.executeQuery(reportid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAmountDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	//end
	
	//added by shruti---12 Nov 2014
	public ArrayList getDurationChkAmt(CaseMonitoringDTO dto) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String[] reportid = {dto.getReportId()};		
			dbUtil.createPreparedStatement(CommonSQL.GETPENALTYDURATIONCHK);
			list = dbUtil.executeQuery(reportid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAmountDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	//end
	public ArrayList getRegDetailsImpoundCase(CaseMonitoringDTO dto) {
		ArrayList list = null;
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();

			String[] reportid = {dto.getReportId()};		
			dbUtil.createPreparedStatement(CommonSQL.GETREGDETAILSIMPOUNDCASE);
			list = dbUtil.executeQuery(reportid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	
	public String getEstampTxnId(String ReportId)
	{DBUtility dbUtil=null;
		String estampTxnId="";
		String[] repid={ReportId};
		try
		{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GETESTAMPTXNID);
			log.info("STATEMENT>>>>>" + CommonSQL.GETESTAMPTXNID);
			estampTxnId=dbUtil.executeQry(repid);
		}
		catch(Exception e)
		{
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return estampTxnId;
	}
	public ArrayList getEstampTxndDetails(String ReportId) {
		ArrayList list = null;
		String estamptxnid="";DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			CaseMonitoringDAO dao=new CaseMonitoringDAO();
			estamptxnid=dao.getEstampTxnId(ReportId);
			String[] estampid = { estamptxnid, estamptxnid};
			dbUtil.createPreparedStatement(CommonSQL.GETESTAMPTXNDETAILS);
			log.info("STATEMENT>>>>>" + CommonSQL.GETESTAMPTXNDETAILS);
			list = dbUtil.executeQuery(estampid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	//******************19th july 2013
	public ArrayList getPenaltySec45TxndDetails(String ReportId) {
		ArrayList list = null;
		String estamptxnid="";DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			CaseMonitoringDAO dao=new CaseMonitoringDAO();
			estamptxnid=dao.getEstampTxnId(ReportId);
			String[] estampid = { estamptxnid, estamptxnid};
			dbUtil.createPreparedStatement(CommonSQL.GETESTAMPTXNDETAILS);
			log.info("STATEMENT>>>>>" + CommonSQL.GETESTAMPTXNDETAILS);
			list = dbUtil.executeQuery(estampid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	//end
	
	//adedd by shruti-10th july 2013
	public synchronized String getCaseNo(String prefix){
		String refId=null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			CaseMonitoringDAO dao=new CaseMonitoringDAO();
			String sqlCurrentdate="SELECT TO_CHAR(SYSDATE,'MMYYYY') FROM DUAL";
			dbUtil.createStatement();
			String currentDate=dbUtil.executeQry(sqlCurrentdate);
			//log.debug("current date:-"+currentDate);
			String newPrefix=prefix+currentDate;
			//log.info("NEW PREFIX>>>>>>>>>>>>>>>>>>>>>>>>>>"+newPrefix);
			String serialnumber=dao.getCaseIdSqNo();
			if(serialnumber.length()==0)
			{
				serialnumber="0000";
			}
			else if(serialnumber.length()==1)
			{
				serialnumber="000"+serialnumber;
			}
			else if(serialnumber.length()==2)
			{
			serialnumber="00"+serialnumber;	
			}
			else if(serialnumber.length()==3)
			{
				serialnumber="0"+serialnumber;
			}
			else
			{
			serialnumber=serialnumber;	
			}
			refId=newPrefix+serialnumber;
		} catch (Exception x) {
			log.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				log.debug(x);
			}
		}

		return refId;
	}
	
	public synchronized String getCaseIdSqNo()
	{
		String casenoseq="";DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			casenoseq=dbUtil.executeQry(CommonSQL.GETCASENOSEQNO);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				log.debug(x);
			}
		}
		return casenoseq;
	}
	
	public synchronized String getRevionIdSqNo()
	{
		String casenoseq="";DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			casenoseq=dbUtil.executeQry(CommonSQL.GETREVISIONSEQNO);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return casenoseq;
	}
	//aded-22july 2013
	public synchronized String getRevionIdSec70SqNo()
	{
		String casenoseq="";DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			casenoseq=dbUtil.executeQry(CommonSQL.GETREVISIONSEC70SEQNO);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return casenoseq;
	}
	//end
	public String getRvenueheadName(String revenueheadid)
	{
		String[] rhid={revenueheadid};
		String revheadname="";DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GETREVENUEHEADNAME);
			revheadname=dbUtil.executeQry(rhid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return revheadname;
	}
	
	//adedd by shruti-10th july 2013
	public synchronized String getRevisonNo(String prefix){
		String refId=null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			CaseMonitoringDAO dao=new CaseMonitoringDAO();
			String sqlCurrentdate="SELECT TO_CHAR(SYSDATE,'MMYYYY') FROM DUAL";
			dbUtil.createStatement();
			String currentDate=dbUtil.executeQry(sqlCurrentdate);
			//log.debug("current date:-"+currentDate);
			
			String newPrefix=prefix+currentDate;
			//log.info("NEW PREFIX>>>>>>>>>>>>>>>>>>>>>>>>>>"+newPrefix);
			
			String serialnumber=dao.getRevionIdSqNo();
			
			if(serialnumber.length()==0)
			{
				serialnumber="0000";
			}
			else if(serialnumber.length()==1)
			{
				serialnumber="000"+serialnumber;
			}
			else if(serialnumber.length()==2)
			{
			serialnumber="00"+serialnumber;	
			}
			else if(serialnumber.length()==3)
			{
				serialnumber="0"+serialnumber;
			}
			else
			{
			serialnumber=serialnumber;	
			}
			
			refId=newPrefix+serialnumber;
			
			
			
		

		} catch (Exception x) {
			log.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				log.debug(x);
			}
		}

		return refId;
	}
	//adedd 22-july 2013
	//adedd by shruti-10th july 2013
	public synchronized String getRevisonNoSec70(String prefix){
		String refId=null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			CaseMonitoringDAO dao=new CaseMonitoringDAO();
			String sqlCurrentdate="SELECT TO_CHAR(SYSDATE,'MMYYYY') FROM DUAL";
			dbUtil.createStatement();
			String currentDate=dbUtil.executeQry(sqlCurrentdate);
			//log.debug("current date:-"+currentDate);
			
			String newPrefix=prefix+currentDate;
			//log.info("NEW PREFIX>>>>>>>>>>>>>>>>>>>>>>>>>>"+newPrefix);
			
			String serialnumber=dao.getRevionIdSec70SqNo();
			
			if(serialnumber.length()==0)
			{
				serialnumber="0000";
			}
			else if(serialnumber.length()==1)
			{
				serialnumber="000"+serialnumber;
			}
			else if(serialnumber.length()==2)
			{
			serialnumber="00"+serialnumber;	
			}
			else if(serialnumber.length()==3)
			{
				serialnumber="0"+serialnumber;
			}
			else
			{
			serialnumber=serialnumber;	
			}
			
			refId=newPrefix+serialnumber;
			
			
			
		

		} catch (Exception x) {
			log.debug(x);
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception x) {
				log.debug(x);
			}
		}

		return refId;
	}
	//end
	
	//added by shruti-27th august 2013
	public ArrayList getImpondedDocDetails(String ReportId) {
		ArrayList list = null;
		ArrayList list1=null;
		ArrayList list2=null;
		ArrayList list3=null;
		ArrayList list4=null;
		String regTxnId="";DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String[] reportid = { ReportId };
			dbUtil.createPreparedStatement(CommonSQL.GETREGTXNIDFORIMPONDEDDOC);
			//log.info("STATEMENT>>>>>" + CommonSQL.GETREGTXNIDFORIMPONDEDDOC);
			regTxnId=dbUtil.executeQry(reportid);
			String[] txnid={regTxnId};
			dbUtil.createPreparedStatement(CommonSQL.GETTXNPARTYDETAILS);
			list=dbUtil.executeQuery(txnid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	//end
	public ArrayList getPartyDetails(String RegId,String language) {
		ArrayList list = null;
		ArrayList list1=null;
		ArrayList list2=null;
		ArrayList list3=null;
		ArrayList list4=null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String[] txnid={RegId};
			if("en".equalsIgnoreCase(language))
			{
			dbUtil.createPreparedStatement(CommonSQL.GETREGNOBASEDPARTYDETAILS);
			}
			else
			{
			dbUtil.createPreparedStatement(CommonSQL.GETREGNOBASEDPARTYDETAILS_H);
			}
			list=dbUtil.executeQuery(txnid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	
	public ArrayList getPartyDetailsNew(String RegId,String language) {
		ArrayList list = null;
		ArrayList list1=null;
		ArrayList list2=null;
		ArrayList list3=null;
		ArrayList list4=null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String[] txnid={RegId};
			if("en".equalsIgnoreCase(language))
			{
			dbUtil.createPreparedStatement(CommonSQL.GET_PARTY_DETAILS);
			}
			else
			{
			dbUtil.createPreparedStatement(CommonSQL.GET_PARTY_DETAILS_HI);
			}
			list=dbUtil.executeQuery(txnid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	
	public ArrayList getPOTPartyDetails(String estampId,String language) {
		ArrayList list = null;
		ArrayList list1=null;
		ArrayList list2=null;
		ArrayList list3=null;
		ArrayList list4=null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String[] txnid={estampId};
			/*if("en".equalsIgnoreCase(language))
			{*/
			dbUtil.createPreparedStatement(CommonSQL.GET_ESTAMP_BASEDPARTYDETAILS);
			/*}
			else
			{
			dbUtil.createPreparedStatement(CommonSQL.GETREGNOBASEDPARTYDETAILS_H);
			}*/
			list=dbUtil.executeQuery(txnid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	public ArrayList getPOTPstampPartyList(String reportId,String language) {
		ArrayList list = null;
		ArrayList list1=null;
		ArrayList list2=null;
		ArrayList list3=null;
		ArrayList list4=null;DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String[] txnid={reportId};
			/*if("en".equalsIgnoreCase(language))
			{*/
			dbUtil.createPreparedStatement(CommonSQL.GET_PSTAMP_BASEDPARTYDETAILS);
			/*}
			else
			{
			dbUtil.createPreparedStatement(CommonSQL.GETREGNOBASEDPARTYDETAILS_H);
			}*/
			list=dbUtil.executeQuery(txnid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getPOTPstampPartyList():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	// added by satbir kumar for case views
	public ArrayList getotherDetailsList(String language, String caseId,
			CaseMonitoringDTO caseDTO) {
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
	try {
			dbUtil = new DBUtility();
			String[] txnid={caseId};
			
			dbUtil.createPreparedStatement(CommonSQL.GETCASEVIEWOTHERDETAILS);
		
			list=dbUtil.executeQuery(txnid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}
	public ArrayList getcaseDetailsList(String language, String caseId,
			CaseMonitoringDTO caseDTO) {
		ArrayList list = new ArrayList();DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String[] txnid={caseId};
		
			if("CH_001".equalsIgnoreCase(caseDTO.getCaseHead()))
			{
			dbUtil.createPreparedStatement(CommonSQL.GETCASEVIEWDETAILS);
			}
			else if("CH_002".equalsIgnoreCase(caseDTO.getCaseHead()))
			{
				dbUtil.createPreparedStatement(CommonSQL.GETCASEREFUNDVIEWDETAILS);
			}
			else if("CH_003".equalsIgnoreCase(caseDTO.getCaseHead()))
			{
				if(caseDTO.getCaseType1()!=null)
				{
					if("CASET_009".equalsIgnoreCase(caseDTO.getCaseType1()))
					{
						dbUtil.createPreparedStatement(CommonSQL.GETCASEVIEWDETAILS);
					}
					if("CASET_010".equalsIgnoreCase(caseDTO.getCaseType1()))
					{
						dbUtil.createPreparedStatement(CommonSQL.GETCASEPENALTYVIEWDETAILS);
					}
					else if("CASET_011".equalsIgnoreCase(caseDTO.getCaseType1()))
					{
						dbUtil.createPreparedStatement(CommonSQL.GETCASEPENALTY45VIEWDETAILS);
					}
					else if("CASET_012".equalsIgnoreCase(caseDTO.getCaseType1()))
					{
						dbUtil.createPreparedStatement(CommonSQL.GETCASEPENALTY70VIEWDETAILS);	
					}
				}
				
			}
			
			list=dbUtil.executeQuery(txnid);
		} catch (Exception e) {
			e.getStackTrace();
			log.error("Exception in getAuditDetails():" + e);
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception cx) {
				log.debug(cx);
			}
		}
		return list;
	}

	//end of addition
}