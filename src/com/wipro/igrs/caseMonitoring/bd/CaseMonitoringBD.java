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
package com.wipro.igrs.caseMonitoring.bd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.caseMonitoring.dao.CaseMonDAO;
import com.wipro.igrs.caseMonitoring.dao.CaseMonitoringDAO;
import com.wipro.igrs.caseMonitoring.dto.CaseMonitoringDTO;
import com.wipro.igrs.caseMonitoring.dto.PartyDetailsDTO;
import com.wipro.igrs.caseMonitoring.util.CommonUtil;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.dto.CommonDTO;
import com.wipro.igrs.util.PropertiesFileReader;

public class CaseMonitoringBD {
	private static Logger log = org.apache.log4j.Logger
			.getLogger(CaseMonitoringBD.class);

	public CaseMonitoringBD() {
	}

	/*
	 * public ArrayList getCountry() throws Exception { CaseMonitoringDAO dao =
	 * new CaseMonitoringDAO(); ArrayList ret = dao.getCountry(); ArrayList list
	 * = new ArrayList();
	 * 
	 * if (ret != null) { for (int i = 0; i < ret.size(); i++) { ArrayList lst =
	 * (ArrayList)ret.get(i); CaseMonitoringDTO dto = new CaseMonitoringDTO();
	 * dto.setCountry((String)lst.get(1)); list.add(dto); } } return list; }
	 * 
	 * 
	 * public ArrayList getState(String stateId) throws Exception {
	 * CaseMonitoringDAO dao = new CaseMonitoringDAO(); ArrayList ret =
	 * dao.getState(stateId); ArrayList list = new ArrayList();
	 * 
	 * if (ret != null) { for (int i = 0; i < ret.size(); i++) { ArrayList lst =
	 * (ArrayList)ret.get(i); CaseMonitoringDTO dto = new CaseMonitoringDTO();
	 * dto.setState((String)lst.get(1)); list.add(dto); } } return list; }
	 */

	/**
	 * @since : 15-01-2008 Method : getCopyIssuance Description : This method is
	 *        to set the copy issuance details for a particular id.
	 * @param certifiedid
	 *            : String
	 * @param flag
	 *            : String
	 * @return dto : CertifiedCopyDetailsDTO
	 * @throws : Exception
	 */
	/*public ArrayList getCaseNotice(String caseHead, String caseType1,
			String caseType2, String caseType3, String estampType,
			String estampSubType, String caseId, String fromDate,
			String toDate, String lastAction, String from, String to)
			throws Exception {

		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList list = dao.getCaseNotice(caseHead, caseType1, caseType2,
				caseType3, estampType, estampSubType, caseId, fromDate, toDate,
				lastAction, from, to);
		ArrayList returnlist = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setEstampId((String) alist.get(1));
				dto.setLastAction((String) alist.get(2));
				dto.setDate((String) alist.get(3));
				returnlist.add(dto);
				log.info("Estamp Id1 " + (String) alist.get(1));
				log.info("Estamp Id2 " + (String) alist.get(2));
				log.info("Estamp Id3 " + (String) alist.get(3));

			}
		}
		return returnlist;
	}*/

	/**
	 * @since : 06-05-2008 Method : getCasedrProcess Description : This method
	 *        is used to list the no of caseid(s)
	 * @param caseHead
	 *            : String
	 * @param caseType
	 *            : String
	 * @param estampType
	 *            : String
	 * @param estampSubType
	 *            : String
	 * @param caseId
	 *            : String
	 * @param fromDate
	 *            : String
	 * @param toDate
	 *            : String
	 * @param lastAction
	 *            : String
	 * @param from
	 *            : String
	 * @param to
	 *            : String
	 * @return returnlist : ArrayList
	 * @throws : Exception
	 */
	/*
	 * public ArrayList getCasedrProcess(String caseHead, String caseType,
	 * String estampType, String estampSubType, String caseId, String fromDate,
	 * String toDate, String lastAction, String from, String to) throws
	 * Exception { CaseMonitoringDAO dao = new CaseMonitoringDAO(); ArrayList
	 * list=dao.getCasedrProcess(caseHead,caseType,estampType,
	 * estampSubType,caseId,fromDate,toDate,lastAction,from,to); ArrayList
	 * returnlist= new ArrayList(); if (list !=null){ for (int i = 0; i <
	 * list.size(); i++){ CaseMonitoringDTO dto = new CaseMonitoringDTO();
	 * ArrayList alist = (ArrayList)list.get(i);
	 * dto.setCaseId((String)alist.get(0));
	 * dto.setEstampId((String)alist.get(1));
	 * dto.setLastAction((String)alist.get(2));
	 * dto.setDate((String)alist.get(3)); dto.setReportId((String)alist.get(4));
	 * returnlist.add(dto); } } return returnlist; }
	 */

	/**
	 * @since : 06-05-2008 Method : getTotalRows Description : This method gets
	 *        the total no of rows from DB
	 * @param caseId
	 *            : String
	 * @param fromDate
	 *            : String
	 * @param toDate
	 *            : String
	 * @param lastAction
	 *            : String
	 * @return list : ArrayList
	 * @throws : Exception
	 */
	/*public ArrayList getTotalRows(String caseHead, String caseType1,
			String caseType2, String caseType3, String estampType,
			String estampSubType, String caseId, String fromDate,
			String toDate, String lastAction) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList ret = dao.getTotalrowsDao(caseHead, caseType1, caseType2,
				caseType3, estampType, estampSubType, caseId, fromDate, toDate,
				lastAction);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				dto.setReportId((String) lst.get(0));
				list.add(dto);
			}
		}
		return list;
	}*/

	// Revenue Module - Get total no of rows
	/*public ArrayList getRevTotal(String caseHead, String caseType1,
			String caseId, String fromDate, String toDate, String lastAction)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		// ArrayList ret = dao.getTotalrowsDao(caseHead,caseType1,caseId,
		ArrayList ret = dao.getRevTotal(caseHead, caseType1, caseId, fromDate,
				toDate, lastAction);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				dto.setReportId((String) lst.get(0));
				list.add(dto);
			}
		}
		return list;
	}*/

	/**
	 * @since : 06-05-2008 Method : getTotalRow Description : This method gets
	 *        the total no of rows from DB
	 * @param caseId
	 *            : String
	 * @param fromDate
	 *            : String
	 * @param toDate
	 *            : String
	 * @param lastAction
	 *            : String
	 * @return list : ArrayList
	 * @throws : Exception
	 */
	/*public ArrayList getTotalCmtRow(String caseHead, String caseType1,
			String caseType2, String caseType3, String estampType,
			String estampSubType, String caseId, String fromDate,
			String toDate, String lastAction) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList ret = dao.getTotalcmtrowDao(caseHead, caseType1, caseType2,
				caseType3, estampType, estampSubType, caseId, fromDate, toDate,
				lastAction);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				dto.setReportId((String) lst.get(0));
				list.add(dto);
			}
		}
		return list;
	}*/

	/*public ArrayList getTotalRow(String caseHead, String caseType1,
			String caseType2, String caseType3, String estampType,
			String estampSubType, String caseId, String fromDate,
			String toDate, String status) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList ret = dao.getTotalrowDao(caseHead, caseType1, caseType2,
				caseType3, estampType, estampSubType, caseId, fromDate, toDate,
				status);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				dto.setReportId((String) lst.get(0));
				list.add(dto);
			}
		}
		return list;
	}*/

	public CaseMonitoringDTO getInitiate(String caseId) throws Exception {
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList list = dao.getInitiate(caseId);
		String comments = new String();
		ArrayList alist;
		if (list != null) {

			for (int i = 0; i < list.size(); i++) {
				alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setStatus((String) alist.get(1));
				dto.setRevenueHead((String) alist.get(2));
				dto.setSection((String) alist.get(3));
				dto.setEstampId((String) alist.get(4));
				dto.setEamount((String) alist.get(5));
				dto.setPurchaseMode((String) alist.get(6));
				dto.setEstampDate((String) alist.get(7));
				dto.setExpiryDate((String) alist.get(8));
				dto.setDeedType((String) alist.get(9));
				dto.setFirstName((String) alist.get(10));
				dto.setMidName((String) alist.get(11));
				dto.setLastName((String) alist.get(12));
				dto.setAddress((String) alist.get(13));
				String a = (String) alist.get(14);
				if (a == null)
					a = "";
				comments += a + "\n\n";
				dto.setDrLastCmt(comments);
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getNoticeDetail(String cid) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getNoticeDetailDao(cid);
		// ArrayList returnlist = new ArrayList();
		String comments = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				// CaseMonitoringDTO dto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setStatus((String) alist.get(1));
				dto.setRevenueHead((String) alist.get(2));
				dto.setSection((String) alist.get(3));
				dto.setEstampId((String) alist.get(4));
				dto.setEamount((String) alist.get(5));
				dto.setPurchaseMode((String) alist.get(6));
				dto.setEstampDate((String) alist.get(7));
				dto.setExpiryDate((String) alist.get(8));
				dto.setDeedType((String) alist.get(9));
				dto.setFirstName((String) alist.get(10));
				dto.setMidName((String) alist.get(11));
				dto.setLastName((String) alist.get(12));
				dto.setAddress((String) alist.get(13));
				String a = (String) alist.get(14);
				if (a == null)
					a = "";
				comments += a + "\n\n";
				dto.setDrLastCmt(comments);
				dto.setAppId((String) alist.get(15));
				dto.setNoticeDate((String) alist.get(16));
				dto.setAmount((String) alist.get(17));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getOrder(String caseId) throws Exception {
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList list = dao.getOrder(caseId);
		String comments = new String();
		ArrayList alist = null;

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setStatus((String) alist.get(1));
				dto.setRevenueHead((String) alist.get(2));
				dto.setSection((String) alist.get(3));
				dto.setEstampId((String) alist.get(4));
				dto.setEamount((String) alist.get(5));
				dto.setPurchaseMode((String) alist.get(6));
				dto.setEstampDate((String) alist.get(7));
				dto.setExpiryDate((String) alist.get(8));
				dto.setDeedType((String) alist.get(9));
				dto.setFirstName((String) alist.get(10));
				dto.setMidName((String) alist.get(11));
				dto.setLastName((String) alist.get(12));
				dto.setAddress((String) alist.get(13));
				String a = (String) alist.get(14);
				if (a == null)
					a = "";
				comments += a + "\n\n";
				dto.setDrLastCmt(comments);
				dto.setAppId((String) alist.get(15));
				dto.setNoticeDate((String) alist.get(16));
				dto.setRecDate((String) alist.get(17));
				dto.setOrderId((String) alist.get(18));
				dto.setOrderDate((String) alist.get(19));
				dto.setAmount((String) alist.get(20));
			}
		}
		return dto;
	}

	public boolean getDrComment(CaseMonitoringDTO dto1, String roleId,
			String funId, String userId) throws Exception {
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		String caseId = dto1.getCaseId();
		ArrayList list = dao.getDrComment(caseId);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setUserId((String) alist.get(1));
				dto.setDrValue((String) alist.get(2));
				dto.setNextDate((String) alist.get(3));
				dto.setRegFee((String) alist.get(4));
				dto.setStampDuty((String) alist.get(5));
				dto.setSpotCmt((String) alist.get(6));
			}
		}
		boolean result = insertDrCmt(dto, dto1, roleId, funId, userId);
		return result;
	}

	public boolean insertDrCmt(CaseMonitoringDTO dtonew,
			CaseMonitoringDTO dtoold, String roleId, String funId, String userId)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		boolean result = false;
		String[] drCmt = new String[8];
		drCmt[0] = dtonew.getCaseId();
		drCmt[1] = dtonew.getUserId();
		drCmt[2] = dtoold.getDrComment();
		// date
		drCmt[3] = dtonew.getDrValue();
		drCmt[4] = "";
		if (dtonew.getNextDate() != null)
			drCmt[4] = getDatefromString(dtonew.getNextDate());
		drCmt[5] = dtonew.getRegFee();
		drCmt[6] = dtonew.getStampDuty();
		drCmt[7] = dtonew.getSpotCmt();
		result = dao.insertDrCmt(drCmt, roleId, funId, userId);
		return result;

	}

	/**
	 * @since : 25-02-2008 Method : getStringFromDate Description : To typecast
	 *        the Date into String
	 * @param date
	 *            : Date
	 * @return dateString : String
	 */
	private String getStringFromDate(Date date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String dateString = null;
		try {
			if (date != null)
				dateString = dateFormat.format(date);
		} catch (Exception e) {
			log.debug("Error getting date1 field in 'dd-MMM-yy' format: " + e);
		}
		return dateString;
	}

	/**
	 * @since : 25-02-2008 Method : getDatefromString Description : To Typeecast
	 *        the String into custom Date Format
	 * @param dateString
	 *            : String
	 * @return getStringFromDate(date) : Method
	 */
	private String getDatefromString(String dateString) {
		log.debug("dateString" + dateString);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try {
			date = simpleDateFormat.parse(dateString);
			log.debug("date=" + date);
		} catch (ParseException pe) {
			log.debug("ParseException: " + pe);
		}
		return getStringFromDate(date);
	}

	public String getNoticeId(CaseMonitoringDTO dto, String roleId,
			String funId, String userId) throws Exception {
		log.info("--------------------Inside notice issued");
		CaseMonitoringDAO dao1 = new CaseMonitoringDAO();
		boolean casemonitoring = false;
		String[] notice = new String[12];
		String[] status = new String[2];
		notice[0] = dto.getCaseId();
		notice[1] = dto.getNoticeDetail();
		notice[2] = "";
		notice[3] = "";
		notice[4] = "";
		notice[5] = "";
		notice[6] = "";
		notice[7] = getAutoNoticeId();
		String nid = notice[7];
		notice[8] = "";
		notice[9] = "";
		if (dto.getFromDate() != null)
			;
		notice[10] = getDatefromString(dto.getFromDate());
		notice[11] = "";

		status[0] = "NOTICE";
		status[1] = dto.getCaseId();
		casemonitoring = dao1.noticeInsert(notice, status, roleId, funId,
				userId);
		return nid;
	}

	public String getAutoNoticeId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_REFUND_NOTICE_TXN",
				"NOTICE_TXN_ID", "NO");
		log.debug("Id               " + id);
		return id;
	}

	public boolean finalcmt(CaseMonitoringDTO dto, String roleId, String funId,
			String userId) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		boolean finalcmt = false;
		String[] param = new String[3];
		param[0] = dto.getFinalComment();
		param[1] = "CLOSE";
		param[2] = dto.getCaseId();
		finalcmt = dao.finalcmt(param, roleId, funId, userId);
		return finalcmt;
	}

	public boolean getRecDt(CaseMonitoringDTO dto, String roleId, String funId,
			String userId) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		boolean getRecDt = false;
		String[] param = new String[2];
		param[0] = getDatefromString(dto.getHearDate());
		param[1] = dto.getCaseId();
		getRecDt = dao.getRecDt(param, roleId, funId, userId);
		return getRecDt;
	}

	public boolean delStatus(CaseMonitoringDTO dto, String roleId,
			String funId, String userId) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		boolean delStatus = false;
		String[] stat = new String[2];
		stat[0] = dto.getRadioOptMan();
		stat[1] = dto.getCaseId();
		delStatus = dao.delStatus(stat, roleId, funId, userId);
		return delStatus;

	}

	public String getOrderId(CaseMonitoringDTO ordto) throws Exception {
		// to generate the order id - igrs_case_refund_order_txn
		log.info("Inside getOrderId-------");
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		boolean casemonitor = false;
		String[] orderid = new String[12];
		String[] lstAction = new String[2];
		orderid[0] = ordto.getCaseId();
		orderid[1] = getOrderautoId();
		String ordid = orderid[1];
		orderid[2] = ordto.getOrderId();
		orderid[3] = "";
		orderid[4] = "";
		orderid[5] = "";
		orderid[6] = "";
		orderid[7] = "";
		orderid[8] = "";
		orderid[9] = "";
		orderid[10] = getDatefromString(ordto.getHearDate());
		orderid[11] = "";

		lstAction[0] = "ORDER";
		lstAction[1] = ordto.getCaseId();
		casemonitor = dao.generateOrderid(orderid, lstAction);
		return ordid;
	}

	public String getRefundOrderId(CaseMonitoringDTO ordto) throws Exception {
		// to generate the order id - igrs_case_refund_order_txn
		log.info("Inside getRefundOrderId-------");
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		boolean casemonitor = false;
		String[] orderid = new String[12];
		String[] lstAction = new String[2];
		orderid[0] = ordto.getCaseId();
		orderid[1] = getOrderautoId();
		String ordid = orderid[1];
		orderid[2] = ordto.getOrderDetail();
		orderid[3] = "";
		orderid[4] = "";
		orderid[5] = "";
		orderid[6] = "";
		orderid[7] = "";
		orderid[8] = "";
		orderid[9] = "";
		orderid[10] = getDatefromString(ordto.getHrDate());
		orderid[11] = "";

		lstAction[0] = "ORDER";
		lstAction[1] = ordto.getCaseId();
		casemonitor = dao.generateOrderid(orderid, lstAction);
		return ordid;
	}

	private String getOrderautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_REFUND_ORDER_TXN",
				"ORDER_TXN_ID", "OR");
		return id;
	}

	/*public String getVoucherId(CaseMonitoringDTO ordto, String roleId,
			String funId, String userId) throws Exception {
		// to generate the voucher id - igrs_case_voucher_txn_id
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		String[] vouid = new String[12];
		String[] auth = new String[3];
		vouid[0] = getVoucherId();
		String voucherid = vouid[0];
		vouid[1] = ordto.getCaseId();
		vouid[2] = ordto.getRefundAmt();
		vouid[3] = "";
		vouid[4] = "";
		vouid[5] = "";
		vouid[6] = "";
		vouid[7] = "";
		vouid[8] = "";
		vouid[9] = "";
		vouid[10] = "";
		vouid[11] = "";
		// to save the refund authority comments - igrs_case_refund_txn
		auth[0] = ordto.getCaseDetail();
		auth[1] = "REFUND ORDER";
		auth[2] = ordto.getCaseId();
		boolean casemonitor = dao.generateVoucherid(vouid, auth, roleId, funId,
				userId);
		log.info("VoucherId = " + voucherid);
		return voucherid;
	}*/

	/*public String getVouId(CaseMonitoringDTO ordto, String roleId,
			String funId, String userId) throws Exception {
		// to generate the voucher id - igrs_case_voucher_txn_id
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		String[] vouid = new String[12];
		String[] auth = new String[3];
		vouid[0] = getVouId();
		String voucherid = vouid[0];
		vouid[1] = ordto.getCaseId();
		vouid[2] = ordto.getRefundAmt();
		vouid[3] = "";
		vouid[4] = "";
		vouid[5] = "";
		vouid[6] = "";
		vouid[7] = "";
		vouid[8] = "";
		vouid[9] = "";
		vouid[10] = "";
		vouid[11] = "";
		// to save the refund authority comments - igrs_case_refund_txn
		auth[0] = ordto.getCaseDetail();
		auth[1] = "ORDER";
		auth[2] = ordto.getCaseId();
		boolean casemonitor = dao.generateVoucherid(vouid, auth, roleId, funId,
				userId);
		log.info("VoucherId = " + voucherid);
		return voucherid;
	}*/

	private String getVouId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_REFUND_VOUCHER_TXN",
				"VOUCHER_TXN_ID", "RV");
		return id;
	}

	private String getVoucherId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_REFUND_VOUCHER_TXN",
				"VOUCHER_TXN_ID", "RV");
		return id;
	}

	/*public CaseMonitoringDTO getRefundOrder(String caseId) throws Exception {
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList list = dao.getRefundOrder(caseId);
		String comments = new String();
		ArrayList alist = null;

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setStatus((String) alist.get(1));
				dto.setRevenueHead((String) alist.get(2));
				dto.setSection((String) alist.get(3));
				dto.setEstampId((String) alist.get(4));
				dto.setEamount((String) alist.get(5));
				dto.setPurchaseMode((String) alist.get(6));
				dto.setEstampDate((String) alist.get(7));
				dto.setExpiryDate((String) alist.get(8));
				dto.setDeedType((String) alist.get(9));
				dto.setFirstName((String) alist.get(10));
				dto.setMidName((String) alist.get(11));
				dto.setLastName((String) alist.get(12));
				dto.setAddress((String) alist.get(13));
				String a = (String) alist.get(14);
				if (a == null)
					a = "";
				comments += a + "\n\n";
				dto.setDrLastCmt(comments);
				dto.setAppId((String) alist.get(15));
				dto.setNoticeDate((String) alist.get(16));
				dto.setRecDate((String) alist.get(17));
				dto.setOrderId((String) alist.get(18));
				dto.setOrderDate((String) alist.get(19));
				dto.setHearDate((String) alist.get(20));
				dto.setOrderId((String) alist.get(21));
				dto.setOrderDate((String) alist.get(22));
				dto.setHearDate((String) alist.get(23));
				dto.setVoucherId((String) alist.get(24));
				dto.setVoucherDate((String) alist.get(25));
				dto.setVoucherAmount((String) alist.get(26));
			}
		}
		return dto;
	}*/

	public boolean getNoticeRecDt(CaseMonitoringDTO dto) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		boolean getNoticeRecDt = false;
		String[] param = new String[2];
		param[0] = getDatefromString(dto.getRecDate());
		param[1] = dto.getCaseId();
		getNoticeRecDt = dao.getNoticeRecDt(param);
		return getNoticeRecDt;
	}

	public CaseMonitoringDTO printOrderOnly(String cid, String ordid,
			String vouid) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		String[] id = new String[3];
		id[0] = cid;
		id[1] = ordid;
		id[2] = vouid;
		ArrayList ret = dao.printOrderOnly(id);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				dto.setCaseId((String) lst.get(0));
				dto.setStatus((String) lst.get(1));
				dto.setAppId((String) lst.get(2));
				dto.setNoticeDate((String) lst.get(3));
				dto.setRecDate((String) lst.get(4));
				dto.setDate((String) lst.get(5));
				dto.setVoucherId((String) lst.get(6));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getPrintOrder(String cid, String not,
			String ordid, String vouid) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		String[] id = new String[4];
		id[0] = cid;
		id[1] = not;
		id[2] = ordid;
		id[3] = vouid;
		ArrayList ret = dao.getPrintOrder(id);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				dto.setCaseId((String) lst.get(0));
				dto.setStatus((String) lst.get(1));
				dto.setAppId((String) lst.get(2));
				dto.setNoticeDate((String) lst.get(3));
				dto.setRecDate((String) lst.get(4));
				dto.setAmount((String) lst.get(5));
				dto.setRefundAmt((String) lst.get(6));
				dto.setDate((String) lst.get(7));
				dto.setVoucherId((String) lst.get(8));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getPrintVoucher(String cid, String not,
			String ordid) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		String[] id = new String[3];
		id[0] = cid;
		id[1] = not;
		id[2] = ordid;
		ArrayList ret = dao.getPrintVoucher(id);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				dto.setCaseId((String) lst.get(0));
				dto.setStatus((String) lst.get(1));
				dto.setAppId((String) lst.get(2));
				dto.setNoticeDate((String) lst.get(3));
				dto.setRecDate((String) lst.get(4));
				dto.setAmount((String) lst.get(5));
				dto.setRefundAmt((String) lst.get(6));
				dto.setDate((String) lst.get(7));
				dto.setVoucherId((String) lst.get(8));
			}
		}
		return dto;
	}

	public ArrayList getRevheadList() throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList ret = dao.getRevheadlistDao();
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				dto.setRevenueId((String) lst.get(0));
				dto.setRevenueHead((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	public ArrayList getSecheadList() throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList ret = dao.getSecheadlistDao();
		ArrayList list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				dto.setSectionId((String) lst.get(0));
				dto.setSectionHead((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	/*
	 * public ArrayList getStampDetail(String id) throws Exception{
	 * CaseMonitoringDAO dao = new CaseMonitoringDAO(); ArrayList ret =
	 * dao.getStampDetailDao(id); ArrayList list = new ArrayList(); if
	 * (ret!=null) { for (int i=0;i<ret.size();i++) { ArrayList lst =
	 * (ArrayList)ret.get(i); CaseMonitoringDTO dto = new CaseMonitoringDTO();
	 * dto.setEstampId((String)lst.get(0)); log.info("EStampId..."+lst.get(0));
	 * dto.setEstampDate((String)lst.get(1)); log.info("Date..."+lst.get(1));
	 * list.add(dto); } } return list; }
	 */
	/*public ArrayList getStampDetail(String id, String fstval, String lstval)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList ret = dao.getStampDetailDao(id, fstval, lstval);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				dto.setEstampId((String) lst.get(0));
				// log.info("EStampId..."+lst.get(0));
				dto.setEstampDate((String) lst.get(1));
				// log.info("Date..."+lst.get(1));
				dto.setReportId((String) lst.get(2));
				// log.info("setTotalRow..."+lst.get(2));
				list.add(dto);
			}
		}
		return list;
	}*/

	public ArrayList getTotalSize(String id) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList ret = dao.getTotalsizeDao(id);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				dto.setReportId((String) lst.get(0));
				// log.info("setReportId..."+lst.get(0));
				list.add(dto);
			}
		}
		return list;

	}

	/*
	 * public ArrayList getEstampDetail(String id) throws Exception {
	 * CaseMonitoringDAO dao = new CaseMonitoringDAO(); CaseMonitoringDTO dto =
	 * new CaseMonitoringDTO(); ArrayList ret = dao.getEstampdetailDao(id);
	 * ArrayList list = new ArrayList(); if (ret != null) { for (int i = 0; i <
	 * ret.size(); i++) { ArrayList lst = (ArrayList) ret.get(i);
	 * dto.setEstampId((String) lst.get(0)); log.info("setEstampNo..." +
	 * lst.get(0)); dto.setStampDuty((String) lst.get(1));
	 * log.info("setStampDuty..." + lst.get(1)); dto.setEstampType((String)
	 * lst.get(2)); log.info("setStampOthers..." + lst.get(2));
	 * dto.setStampAmount((String) lst.get(3)); log.info("setStampTotal..." +
	 * lst.get(3)); dto.setFirstName((String) lst.get(4));
	 * log.info("setFirstName..." + lst.get(4)); dto.setMidName((String)
	 * lst.get(5)); log.info("setMiddleName..." + lst.get(5));
	 * dto.setLastName((String) lst.get(6)); log.info("setLastName..." +
	 * lst.get(6)); dto.setGender((String) lst.get(7)); log.info("setGender..."
	 * + lst.get(7)); dto.setAge((String) lst.get(8)); log.info("setAge..." +
	 * lst.get(8)); dto.setFatherName((String) lst.get(9));
	 * log.info("setFatherName..." + lst.get(9)); dto.setMotherName((String)
	 * lst.get(10)); log.info("setMotherName..." + lst.get(10));
	 * dto.setAddress((String) lst.get(11)); log.info("setPartyAddress..." +
	 * lst.get(11)); dto.setCity((String) lst.get(12)); log.info("setCity..." +
	 * lst.get(12)); dto.setCountry((String) lst.get(13));
	 * log.info("setCountry..." + lst.get(13)); dto.setState((String)
	 * lst.get(14)); log.info("setState..." + lst.get(14)); dto.setPin((String)
	 * lst.get(15)); log.info("setPostCode..." + lst.get(15));
	 * dto.setPhone((String) lst.get(16)); log.info("setPheNo..." +
	 * lst.get(16)); dto.setMobNo((String) lst.get(17)); log.info("setMobNo..."
	 * + lst.get(17)); dto.setEmailId((String) lst.get(18));
	 * log.info("setEmailId..." + lst.get(18)); dto.setProofId((String)
	 * lst.get(19)); log.info("setProofId..." + lst.get(19));
	 * dto.setPhotoId((String) lst.get(20)); log.info("setPhotoId..." +
	 * lst.get(20)); dto.setDeedType((String) lst.get(21));
	 * log.info("setDeed..." + lst.get(21)); dto.setEstampDate((String)
	 * lst.get(22)); log.info("setEstampDate..." + lst.get(22));
	 * dto.setExpiryDate((String) lst.get(23)); log.info("setExpDate..." +
	 * lst.get(23)); dto.setAmount((String) lst.get(24));
	 * log.info("setEstampDate..." + lst.get(24)); dto.setRefundAmt((String)
	 * lst.get(25)); log.info("setRefundAmt...." + lst.get(25));
	 * dto.setPurchaseMode((String) lst.get(26)); log.info("setPurMode...." +
	 * lst.get(26)); dto.setPmodeDeed((String) lst.get(27));
	 * log.info("setPmodeDeed...." + lst.get(27)); list.add(dto); } } return
	 * list; }
	 */

	/*public ArrayList getEstampDetail(String id) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList ret = dao.getEstampdetailDao(id);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				dto.setEstampId((String) lst.get(0));
				System.out.println("setEstampId..." + lst.get(0));
				dto.setStampDuty((String) lst.get(1));
				System.out.println("setStampDuty..." + lst.get(1));
				dto.setEstampDate((String) lst.get(2));
				System.out.println("setEstampDate..." + lst.get(2));
				dto.setExpiryDate((String) lst.get(3));
				System.out.println("setExpiryDate..." + lst.get(3));
				dto.setDeedType((String) lst.get(4));
				System.out.println("setDeedType..." + lst.get(4));
				dto.setFirstName((String) lst.get(5));
				System.out.println("setFirstName..." + lst.get(5));
				dto.setMidName((String) lst.get(6));
				System.out.println("setMidName..." + lst.get(6));
				dto.setLastName((String) lst.get(7));
				System.out.println("setLastName..." + lst.get(7));
				dto.setAddress((String) lst.get(8));
				System.out.println("setAddress..." + lst.get(8));

				list.add(dto);
			}
		}
		return list;
	}
*/
	public String insertcaseoptional(CaseMonitoringDTO dto, String roleId,
			String funId, String userId) throws Exception {
		CaseMonitoringDAO daos = new CaseMonitoringDAO();
		boolean casemonitoring = false;
		// String[] caserefund = new String[13];
		String[] caserefund = new String[17];
		// String[] drCmt = new String[8];

		String[] drCmt = new String[4];
		// added by shruti
		String[] actionMap = new String[6];
		IGRSCommon sqnce = new IGRSCommon();
		String caseAxnTrId = sqnce
				.getSequenceNumber("IGRS_CASE_AXN_TXN", "CSA");
		// end

		// method modified by shruti
		// caserefund[0] = caseidGenerator();
		// end

		/*
		 * // caserefund[0] = caseidGeneratorNew(userId); // String caseid =
		 * caserefund[0]; // caserefund[1] = ""; // caserefund[2] = ""; //
		 * caserefund[3] = ""; // caserefund[4] = ""; // caserefund[5] = ""; //
		 * caserefund[6] = "OPEN"; // caserefund[7] = "INITIATE"; //
		 * caserefund[8] = ""; // caserefund[9] = ""; // caserefund[10] =
		 * dto.getRevenueId(); // caserefund[11] = dto.getSectionId(); //
		 * caserefund[12] = dto.getEstampId();
		 */

		caserefund[0] = caseidGeneratorNew(dto.getCurrentOffId());
		String caseid = caserefund[0];
		caserefund[1] = userId;
		caserefund[2] = userId;
		caserefund[3] = "";
		caserefund[4] = dto.getEstampId();
		caserefund[5] = dto.getRevenueId();
		caserefund[6] = dto.getSectionId();
		caserefund[7] = "OPEN";
		caserefund[8] = "INITIATE";
		caserefund[9] = dto.getCaseHead();
		caserefund[10] = dto.getCaseType();
		// caserefund[9] = "CH_002";
		// caserefund[10]="CASET_007";
		caserefund[11] = "CASE_000";
		caserefund[12] = dto.getFirstName();
		caserefund[13] = dto.getMidName();
		caserefund[14] = dto.getLastName();
		caserefund[15] = dto.getAddress();
		caserefund[16] = dto.getRule();

		/*
		 * drCmt[0] = caserefund[0]; drCmt[1] = ""; drCmt[2] =
		 * dto.getDrLastCmt(); drCmt[3] = ""; drCmt[4] = ""; drCmt[5] = "";
		 * drCmt[6] = ""; drCmt[7] = "";
		 */
		drCmt[0] = caserefund[0];
		drCmt[1] = caseAxnTrId;
		drCmt[2] = userId;
		drCmt[3] = dto.getDrLastCmt();

		actionMap[0] = caserefund[0];
		actionMap[1] = "CASE_000";
		actionMap[2] = userId;
		actionMap[3] = "";
		actionMap[4] = caseAxnTrId;
		actionMap[5] = userId;

		casemonitoring = daos.insertcaseoptionaldao(caserefund, drCmt, roleId,
				funId, userId, actionMap);
		return caseid;
	}

	private String caseidGenerator() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_REFUND_TXN",
				"CASE_TXN_ID", "ESRF");
		return id;
	}

	// added by shruti
	private String caseidGeneratorNew(String officeId) throws Exception {

		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		String districtCode = dao.getLoggedInDRDistrict(officeId);
		if (districtCode.length() == 1) {
			districtCode = "0" + districtCode;
		}

		// modified by shruti

		String id = new CommonUtil().getAutoIdNew("IGRS_CASE_TXN_DETAILS","CASE_TXN_ID", districtCode);

		/*
		 * String id = new CommonUtil().getAutoIdNew("IGRS_CASE_REFUND_TXN",
		 * "CASE_TXN_ID", districtCode);
		 */
		return id;
	}
	
	public String getRegNumber(String PENALTYId) throws Exception {

		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		
		return dao.getRegNumber(PENALTYId);
	}

	// end
	
	
	//added by shruti-10th july 2013
	private String getCaseNo(CaseMonitoringDTO dto) throws Exception {

		 String prefix="";
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		
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
		
		 String revheadid=dto.getRevenueId();
		 
		 String revheadName=dao.getRvenueheadName(revheadid);
		 
		 prefix=districtCode+revheadName;
		 log.info("PREFIX::::::::::::::::::::::"+prefix);
		/*String id = new CommonUtil().getAutoIdNew("IGRS_CASE_TXN_DETAILS",
				"CASE_TXN_ID", districtCode);*/
		String id=dao.getCaseNo(prefix);
		return id;
	}
	//end
	
	//added by shruti-20th july 2013
	private String getRevisionSec45No() throws Exception {

		String prefix="";
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		prefix="SEC45";
		log.info("PREFIX::::::::::::::::::::::"+prefix);
		String id=dao.getRevisonNo(prefix);
		
		return id;
			
			
	}
	//end
	//added by shruti-20th july 2013
	private String getRevisionSec70No() throws Exception {

		String prefix="";
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		prefix="SEC70";
		log.info("PREFIX::::::::::::::::::::::"+prefix);
		String id=dao.getRevisonNoSec70(prefix);
		
		return id;
			
			
	}
	//end


	public ArrayList getCaseView(String caseHead, String caseType1,
			String caseType2, String caseType3, String estampType,
			String estampSubType, String caseId, String fromDate,
			String toDate, String status, String from, String to)
			throws Exception {

		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList list = dao.getCaseView(caseHead, caseType1, caseType2,
				caseType3, estampType, estampSubType, caseId, fromDate, toDate,
				status, from, to);
		ArrayList returnlist = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setEstampId((String) alist.get(1));
				dto.setLastAction((String) alist.get(2));
				dto.setDate((String) alist.get(3));
				dto.setLastActionID((String) alist.get(4));
				dto.setCaseDate((String) alist.get(5));
				returnlist.add(dto);
				log.info("Estamp Id1 " + (String) alist.get(1));
				log.info("Estamp Id2 " + (String) alist.get(2));
				log.info("Estamp Id3 " + (String) alist.get(3));

			}
		}
		return returnlist;
	}

	public CaseMonitoringDTO getCaseDetail(String caseId, String lastActionID)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getCaseDetail(caseId, lastActionID);
		String comment = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setStatus((String) alist.get(1));
				dto.setRevenueHead((String) alist.get(2));
				dto.setSectionHead((String) alist.get(3));
				dto.setSpotCmt((String) alist.get(4));
				dto.setDrValue((String) alist.get(5));
				dto.setHearDate((String) alist.get(6));
				dto.setRegFee((String) alist.get(7));
				dto.setStampDuty((String) alist.get(8));
				String a = (String) alist.get(9);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getViewNotice(String caseId, String lastActionID)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getViewNotice(caseId, lastActionID);
		String comment = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				String a = (String) alist.get(0);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
				dto.setNoticeDetail((String) alist.get(1));
				dto.setHearDate((String) alist.get(2));
				// dto.setDrValue((String)alist.get(3));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getViewOrder(String caseId, String lastActionID)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getViewOrder(caseId, lastActionID);
		String comment = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				String a = (String) alist.get(0);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
				dto.setNoticeDetail((String) alist.get(1));
				dto.setRecDate((String) alist.get(2));
				dto.setHearDate((String) alist.get(3));
				// dto.setDrValue((String)alist.get(4));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getExPartyOrder(String caseId, String lastActionID)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getExPartyOrder(caseId, lastActionID);
		String comment = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				String a = (String) alist.get(0);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
				dto.setNoticeDetail((String) alist.get(1));
				dto.setRecDate((String) alist.get(2));
				dto.setHearDate((String) alist.get(3));
				// dto.setDrValue((String)alist.get(4));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getViewDemandNotice(String caseId,
			String lastActionID) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getViewDemandNotice(caseId, lastActionID);
		String comment = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				String a = (String) alist.get(0);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
				dto.setNoticeDetail((String) alist.get(1));
				dto.setRecDate((String) alist.get(2));
				dto.setHearDate((String) alist.get(3));
				// dto.setDrValue((String)alist.get(4));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getAttachOrder(String caseId, String lastActionID)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		String comment = new String();
		ArrayList list = dao.getAttachOrder(caseId, lastActionID);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				String a = (String) alist.get(0);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
				dto.setNoticeDetail((String) alist.get(1));
				dto.setRecDate((String) alist.get(2));
				dto.setHearDate((String) alist.get(3));
				// dto.setDrValue((String)alist.get(4));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getAuctionOrder(String caseId, String lastActionID)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		String comment = new String();
		ArrayList list = dao.getAuctionOrder(caseId, lastActionID);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				String a = (String) alist.get(0);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
				dto.setNoticeDetail((String) alist.get(1));
				dto.setRecDate((String) alist.get(2));
				dto.setHearDate((String) alist.get(3));
				// dto.setDrValue((String)alist.get(4));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getRevenueCommission(String caseId,
			String lastActionID) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getRevenueCommission(caseId, lastActionID);
		String comment = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				String a = (String) alist.get(0);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
				dto.setNoticeDetail((String) alist.get(1));
				dto.setRecDate((String) alist.get(2));
				dto.setHearDate((String) alist.get(3));
				// dto.setDrValue((String)alist.get(4));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getRevenueBoard(String caseId, String lastActionID)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getRevenueBoard(caseId, lastActionID);
		String comment = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				String a = (String) alist.get(0);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
				dto.setNoticeDetail((String) alist.get(1));
				dto.setRecDate((String) alist.get(2));
				dto.setHearDate((String) alist.get(3));
				// dto.setDrValue((String)alist.get(4));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getHighCourt(String caseId, String lastActionID)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getHighCourt(caseId, lastActionID);
		String comment = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				String a = (String) alist.get(0);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
				dto.setNoticeDetail((String) alist.get(1));
				dto.setRecDate((String) alist.get(2));
				dto.setHearDate((String) alist.get(3));
				// dto.setDrValue((String)alist.get(4));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getSupremeCourt(String caseId, String lastActionID)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getSupremeCourt(caseId, lastActionID);
		String comment = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				String a = (String) alist.get(0);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
				dto.setNoticeDetail((String) alist.get(1));
				dto.setRecDate((String) alist.get(2));
				dto.setHearDate((String) alist.get(3));
				// dto.setDrValue((String)alist.get(4));
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getCloseState(String caseId, String lastActionID)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getCloseState(caseId, lastActionID);
		String comment = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				String a = (String) alist.get(0);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrComment(comment);
				dto.setNoticeDetail((String) alist.get(1));
				dto.setRecDate((String) alist.get(2));
				dto.setHearDate((String) alist.get(3));
				dto.setFinalComment((String) alist.get(4));
				// dto.setDrValue((String)alist.get(5));
			}
		}
		return dto;
	}

	/*public ArrayList getAddCmt(String caseHead, String caseType1,
			String caseType2, String caseType3, String estampType,
			String estampSubType, String caseId, String fromDate,
			String toDate, String lastAction, String from, String to)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList list = dao.getAddCmt(caseHead, caseType1, caseType2,
				caseType3, estampType, estampSubType, caseId, fromDate, toDate,
				lastAction, from, to);
		ArrayList returnlist = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setEstampId((String) alist.get(1));
				dto.setLastAction((String) alist.get(2));
				dto.setDate((String) alist.get(3));
				returnlist.add(dto);
			}
		}
		return returnlist;
	}*/

	public CaseMonitoringDTO getDrCmt(String caseId) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getDrCmt(caseId);
		String comment = new String();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setCaseStatus((String) alist.get(1));
				dto.setRevenueHead((String) alist.get(2));
				dto.setSectionHead((String) alist.get(3));
				dto.setSpotCmt((String) alist.get(4));
				dto.setDrValue((String) alist.get(5));
				dto.setHearDate((String) alist.get(6));
				dto.setRegFee((String) alist.get(7));
				dto.setStampDuty((String) alist.get(8));
				String a = (String) alist.get(9);
				if (a == null)
					a = "";
				comment += a + "\n\n";
				dto.setDrCmt(comment);
			}
		}
		return dto;
	}

	public CaseMonitoringDTO getCmtDetail(String caseId) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		ArrayList list = dao.getCmtDetail(caseId);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				dto.setLastAction((String) alist.get(0));
				dto.setCaseDetail((String) alist.get(1));
				dto.setHearDate((String) alist.get(2));
			}
		}
		return dto;
	}

	public boolean getCmtUpdate(CaseMonitoringDTO cmdto, String caseId)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		boolean getCmtUpdate = false;
		String[] param = new String[2];
		param[0] = cmdto.getNoticeDetail();
		param[1] = caseId;
		getCmtUpdate = dao.getCmtUpdate(param);
		return getCmtUpdate;
	}

	// Revenue Module - Get total no of rows from IGRS_RCPT_AUDIT_TXN
/*	public ArrayList getRevtotalRow(String caseType1, String caseId,
			String fromDate, String toDate) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList ret = dao.getRevtotalRow(caseType1, caseId, fromDate, toDate);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				dto.setRevenueId((String) lst.get(0));
				log.info("TotalRows=" + lst.get(0));
				list.add(dto);
			}
		}
		return list;
	}*/

	// Revenue Module - get the revenue head from igrs_case_revenue_head_master
	public ArrayList getRevenueList(String caseHead) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList ret = dao.getRevenueList(caseHead);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				dto.setRevenueId((String) lst.get(0));
				dto.setRevenueHead((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	// Revenue Module - get the section head from igrs_case_section_head_master
	public ArrayList getSectionList(String caseType) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList ret = dao.getSectionList(caseType);
		ArrayList list = new ArrayList();
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				CaseMonitoringDTO dto = new CaseMonitoringDTO();
				dto.setSectionId((String) lst.get(0));
				dto.setSectionHead((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

	// Revenue Module - update DR comments only
	public boolean getRevdrComment(CaseMonitoringDTO dto1) throws Exception {
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		String caseId = dto1.getCaseId();
		ArrayList list = dao.getRevdrComment(caseId);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setUserId((String) alist.get(1));
				dto.setAppId((String) alist.get(2));
				dto.setDrComment((String) alist.get(3));
				dto.setDate((String) alist.get(4));
			}
		}
		boolean result = insertRevdrCmt(dto, dto1);
		return result;
	}

	// Revenue Module - update DR comments only
	public boolean insertRevdrCmt(CaseMonitoringDTO dtonew,
			CaseMonitoringDTO dtoold) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside insertRevdrCmt()");
		boolean result = false;
		String[] drCmt = new String[4];
		drCmt[0] = dtonew.getCaseId();
		drCmt[1] = dtonew.getUserId();
		drCmt[2] = dtonew.getAppId();
		drCmt[3] = dtoold.getDrLastCmt();
		result = dao.insertRevdrCmt(drCmt);
		return result;
	}

	// Revenue Module - Generate Order Id
	public String getRevorderId(CaseMonitoringDTO nodto, String lastaction)
			throws Exception {
		// to generate the order id - igrs_case_action_map
		log.info("Inside getRevorderId");
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		boolean casemonitor = false;
		String[] orderid = new String[7];
		String[] lstAction = new String[4];
		String[] cmt = new String[3];
		orderid[0] = nodto.getCaseId();
		orderid[1] = lastaction;
		orderid[2] = "DR";
		if (lastaction.equals("Revenue Commissioner")
				|| lastaction.equals("Revenue Board")
				|| lastaction.equals("High Court")
				|| lastaction.equals("Supreme Court")) {
			log.info("Date (orderid[3]) =" + nodto.getDate());
			orderid[3] = getDatefromString(nodto.getDate());
		} else {
			orderid[3] = "";
		}
		orderid[4] = nodto.getNoticeDetail();
		if (lastaction.equals("Initiate")
				|| lastaction.equals("Revenue Commissioner")
				|| lastaction.equals("Revenue Board")
				|| lastaction.equals("High Court")
				|| lastaction.equals("Supreme Court")) {
			log.info("Date (orderid[5])=" + nodto.getDate());
			orderid[5] = "";
		} else {
			log.info("Date (orderid[5])=" + nodto.getDate());
			orderid[5] = getDatefromString(nodto.getDate());
		}
		if (lastaction.equals("Initiate")) {
			orderid[6] = getRevnoticeautoId();
		}
		if (lastaction.equals("Notice")) {
			orderid[6] = getRevorderautoId();
		}
		if (lastaction.equals("Order")) {
			orderid[6] = getRevexorderautoId();
		}
		if (lastaction.equals("Ex-Partee Order")) {
			orderid[6] = getRevdemandautoId();
		}
		if (lastaction.equals("Demand Notice")) {
			orderid[6] = getRevattachautoId();
		}
		if (lastaction.equals("Attachment Order")) {
			orderid[6] = getRevauctionautoId();
		}
		if (lastaction.equals("Auction Order")) {
			orderid[6] = getRevcommissionerautoId();
		}
		if (lastaction.equals("Revenue Commissioner")) {
			orderid[6] = getRevboardautoId();
		}
		if (lastaction.equals("Revenue Board")) {
			orderid[6] = getRevhighautoId();
		}
		if (lastaction.equals("High Court")) {
			orderid[6] = getRevsupremeautoId();
		}
		String ordid = orderid[6];
		lstAction[0] = getDatefromString(nodto.getHrDate());
		if (lastaction.equals("Initiate")) {
			lstAction[1] = "Notice";
		}
		if (lastaction.equals("Notice")) {
			lstAction[1] = "Order";
		}
		if (lastaction.equals("Order")) {
			lstAction[1] = "Ex-Partee Order";
		}
		if (lastaction.equals("Ex-Partee Order")) {
			lstAction[1] = "Demand Notice";
		}
		if (lastaction.equals("Demand Notice")) {
			lstAction[1] = "Attachment Order";
		}
		if (lastaction.equals("Attachment Order")) {
			lstAction[1] = "Auction Order";
		}
		if (lastaction.equals("Auction Order")) {
			lstAction[1] = "Revenue Commissioner";
		}
		if (lastaction.equals("Revenue Commissioner")) {
			lstAction[1] = "Revenue Board";
		}
		if (lastaction.equals("Revenue Board")) {
			lstAction[1] = "High Court";
		}
		if (lastaction.equals("High Court")) {
			lstAction[1] = "Supreme Court";
		}
		lstAction[2] = nodto.getCaseId();
		lstAction[3] = lastaction;
		cmt[0] = orderid[6];
		cmt[1] = "1";
		cmt[2] = nodto.getCaseId();
		casemonitor = dao.getRevorderId(orderid, lstAction, cmt);
		return ordid;
	}

	public String generateRevenueActionMapID(String id) throws Exception {
		String idMap = "";
		PropertiesFileReader pr = PropertiesFileReader
				.getInstance("resources.igrs");
		/*
		 * if (lastaction.equals("Initiate")) { orderid[6] =
		 * getRevnoticeautoId(); } if (lastaction.equals("Notice")) { orderid[6]
		 * = getRevorderautoId(); } if (lastaction.equals("Order")) { orderid[6]
		 * = getRevexorderautoId(); } if (lastaction.equals("Ex-Partee Order"))
		 * { orderid[6] = getRevdemandautoId(); } if
		 * (lastaction.equals("Demand Notice")) { orderid[6] =
		 * getRevattachautoId(); } if (lastaction.equals("Attachment Order")) {
		 * orderid[6] = getRevauctionautoId(); } if
		 * (lastaction.equals("Auction Order")) { orderid[6] =
		 * getRevcommissionerautoId(); } if
		 * (lastaction.equals("Revenue Commissioner")) { orderid[6] =
		 * getRevboardautoId(); } if (lastaction.equals("Revenue Board")) {
		 * orderid[6] = getRevhighautoId(); } if
		 * (lastaction.equals("High Court")) { orderid[6] =
		 * getRevsupremeautoId(); }
		 */
		return idMap;
	}

	// Revenue Module - To generate order id automatically
	private String getRevorderautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_ACTION_MAP",
				"CASE_ACTION_TXN_ID", "OR");
		return id;
	}

	private String getRevnoticeautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_ACTION_MAP",
				"CASE_ACTION_TXN_ID", "NO");
		return id;
	}

	private String getRevexorderautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_ACTION_MAP",
				"CASE_ACTION_TXN_ID", "EX");
		return id;
	}

	private String getRevdemandautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_ACTION_MAP",
				"CASE_ACTION_TXN_ID", "DE");
		return id;
	}

	private String getRevattachautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_ACTION_MAP",
				"CASE_ACTION_TXN_ID", "AT");
		return id;
	}

	private String getRevauctionautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_ACTION_MAP",
				"CASE_ACTION_TXN_ID", "AU");
		return id;
	}

	private String getRevcommissionerautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_ACTION_MAP",
				"CASE_ACTION_TXN_ID", "CO");
		return id;
	}

	private String getRevboardautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_ACTION_MAP",
				"CASE_ACTION_TXN_ID", "BO");
		return id;
	}

	private String getRevhighautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_ACTION_MAP",
				"CASE_ACTION_TXN_ID", "HI");
		return id;
	}

	private String getRevsupremeautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_ACTION_MAP",
				"CASE_ACTION_TXN_ID", "SU");
		return id;
	}

	// Revenue Module - Close Case
	public boolean getRevcloseCase(CaseMonitoringDTO dtonew, String lastaction)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getRevcloseCase()");
		boolean result = false;
		String[] drCmt = new String[4];
		drCmt[0] = "Close";
		drCmt[1] = dtonew.getFinalComment();
		drCmt[2] = dtonew.getCaseId();
		drCmt[3] = lastaction;
		result = dao.getRevcloseCase(drCmt);
		return result;
	}

	// Revenue Module - Case Create
	public ArrayList getReportList(String caseType, String caseId,
			String fdate, String tdate, String from, String to)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list = dao.getReportList(caseType, caseId, fdate, tdate,from, to);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				//modified---22nd oct 2013
				cmdto.setReportId((String) alist.get(0));
				cmdto.setParaId((String) alist.get(1));
				cmdto.setObjId((String) alist.get(2));
				cmdto.setRegId((String) alist.get(3));
				cmdto.setCaseDetail((String) alist.get(4));
				cmdto.setReportDate((String) alist.get(5));
				cmdto.setSectionId(Integer.toString(i+1));
				//cmdto.setHdnParam((String) alist.get(0)+"~"+(String) alist.get(1)+"~"+(String) alist.get(2)+"~"+(String) alist.get(3));
				if(caseType.equalsIgnoreCase("CASET_004")){//added by roopam-5feb2015-for public officer report
				cmdto.setHdnParam((String) alist.get(0)+"~"+(String) alist.get(1)+"~"+(String) alist.get(2)+"~"+(String) alist.get(6));
				cmdto.setEstampId(alist.get(6)!=null?(String)alist.get(6):"-");//added by roopam-5feb2015-coz of change in pot
				
				if(cmdto.getEstampId().equalsIgnoreCase("-")){
					
					cmdto.setEstampId(getPOTPhysicalStampDetails(cmdto.getReportId()));//added by roopam-9feb2015-coz of change in pot
					
				}
				
				}else{
				cmdto.setHdnParam((String) alist.get(0)+"~"+(String) alist.get(1)+"~"+(String) alist.get(2)+"~"+(String) alist.get(3));
				}
				/*cmdto.setCaseDetail((String) alist.get(0));
				cmdto.setReportId((String) alist.get(1));
				cmdto.setAppId((String) alist.get(2));
				cmdto.setSectionId((String) alist.get(3));*/
				listone.add(cmdto);
			}
		}
		return listone;
	}
	
	
	public ArrayList getReportListNew(String caseType, String caseId,
			String fdate, String tdate, String from, String to)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list = dao.getReportList(caseType, caseId, fdate, tdate,from, to);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				//modified---22nd oct 2013
				/*cmdto.setReportId((String) alist.get(0));
				cmdto.setParaId((String) alist.get(1));
				cmdto.setObjId((String) alist.get(2));
				cmdto.setRegId((String) alist.get(3));
				cmdto.setCaseDetail((String) alist.get(4));
				cmdto.setReportDate((String) alist.get(5));
				cmdto.setSectionId(Integer.toString(i+1));
				cmdto.setHdnParam((String) alist.get(0)+"~"+(String) alist.get(1)+"~"+(String) alist.get(2)+"~"+(String) alist.get(3))*/;
				cmdto.setCaseDetail((String) alist.get(0));
				cmdto.setReportId((String) alist.get(1));
				cmdto.setAppId((String) alist.get(2));
				cmdto.setSectionId((String) alist.get(3));
				listone.add(cmdto);
			}
		}
		return listone;
	}
	//********************************************************
	public ArrayList getRefundList(String caseType, String caseId,
			String fdate, String tdate, String from, String to,String estampType,String language)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list = dao.getRefundList(caseType, caseId, fdate, tdate, from, to,estampType,language);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setCaseDetail((String) alist.get(0));
				cmdto.setReportId((String) alist.get(1));
				cmdto.setAppId((String) alist.get(2));
				cmdto.setSectionId((String) alist.get(3));
				listone.add(cmdto);
			}
		}
		return listone;
	}
	//*******************************end 
	///***************************19th july 2013
	public ArrayList getPenaltyList(String caseType,String penaltyId,  String caseId,
			String fdate, String tdate, String from, String to)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list=dao.getPenaltyList(caseType, penaltyId,caseId, fdate, tdate, from, to);
		
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setCaseDetail((String) alist.get(0));
				cmdto.setReportId((String) alist.get(1));
				cmdto.setPenaltyId((String) alist.get(2));
				//cmdto.setAppId((String) alist.get(2));
				cmdto.setSectionId((String) alist.get(3));
				cmdto.setHdnParam((String) alist.get(1)+"~"+(String) alist.get(2));
				listone.add(cmdto);
			}
		}
		return listone;
	}
	//License cancellation
	public ArrayList getlicensedUserList(String caseType,String licenseId,
			String fdate, String tdate, String from, String to,String language)
			throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list=dao.getlicensedUserList(caseType, licenseId, fdate, tdate, from, to,language);
		
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setCaseDetail((String) alist.get(0));
				cmdto.setLicenseId((String) alist.get(1));
				cmdto.setValidTo((String) alist.get(2));
				cmdto.setSectionId((String) alist.get(3));
				listone.add(cmdto);
			}
		}
		return listone;
	}

	//end

	// Revenue Module - generate caseid using inserted datas in JSP
	public String getRevreportId(CaseMonitoringDTO dto, String docid,
			String repid, HashMap map,String offId) throws Exception {

		// modified by shruti
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonDAO cmDAO = new CaseMonDAO();

		String casetxnsrno = cmDAO.getCaseTxnSrNo();
		String caseactionmapsrno = cmDAO.getActionMapSrNo();
		String casecmntsrno = cmDAO.getCaseCmntsSrNo();

		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		log.info("Inside getRevreportId()");

		boolean casemonitor = false;

		String[] txn = new String[23];
		String[] cmt = new String[5];

		txn[0] = casetxnsrno;
		//txn[1] = getRevenueautoId();
		txn[1]=getCaseNo(dto);//modified acc to new case format
		String rid = txn[1];
		txn[2] = dto.getCaseHead();
		txn[3] = dto.getCaseType();
		txn[4] = dto.getRevenueId();
		txn[5] = dto.getSectionId();
		// txn[6] = repid;
		txn[6] = dto.getReportId();
		//modified by shruti--6 oct 2014
		txn[7] = dto.getRegId();// REG ID
		txn[8] = dto.getEamount();
		txn[9] = dto.getStampAmount();
		if("CASET_003".equalsIgnoreCase(dto.getCaseType())){//added by roopam 27feb2015 for poi report case
			txn[10] = dto.getRegFee();
		}else{
		txn[10] = dto.getRefundAmount();
		}
		 
		txn[11] = "OPEN";
		txn[12] = dto.getSpotCmt();
		if((dto.getHearDate()=="")||(dto.getHearDate()==null)){
		txn[13]=null;
		}
		else{
		txn[13] = getDatefromString(dto.getHearDate());
		}
		txn[14] = null;// final case comments
		txn[15] = null; // rrc case
		txn[16] = null;
		txn[17] = dto.getUserId();
		if("CASET_004".equalsIgnoreCase(dto.getCaseType())){
		txn[18] = (dto.getStampNo()!=null && !("").equalsIgnoreCase(dto.getStampNo()))?dto.getStampNo():dto.getEstampNo();//added by roopam for pot-estamp,pstamp case
		}else{
			txn[18]=null;
		}
		String initiate = "CASE_000";
		txn[19] = initiate;
		//addedby shruti 26th august 2013
		if((dto.getDueDate()=="")||(dto.getDueDate()==null))
		{
			txn[20]="";
		}
		else
		{
		txn[20]=getDatefromString(dto.getDueDate());
		}
		txn[21]=offId;
		
		if("CASET_003".equalsIgnoreCase(dto.getCaseType())){//added by roopam 27feb2015 for poi report case
			txn[22] = dto.getStampAmount(); //might get changed. sum of reg fee and stamp duty
		}else{
			txn[22]=dto.getStampAmount();
		}

		String actionmap[] = new String[7];

		actionmap[0] = caseactionmapsrno;
		actionmap[1] = getRevnoticeautoId();
		actionmap[2] = rid;
		actionmap[3] = initiate;
		actionmap[4] = dto.getDrComment();
		actionmap[5] = "Y";
		actionmap[6] = dto.getUserId();

		// igrs_case_txn_comments
		cmt[0] = casecmntsrno;
		cmt[1] = txn[1];
		cmt[2] = getRevnoticeautoId();
		cmt[3] = dto.getDrComment();
		cmt[4] = dto.getUserId();

		
		HashMap fileMap = new HashMap();

		casemonitor = dao.getRevreportId(txn, cmt, map, actionmap);
		if (!casemonitor) {
			rid = null;
		}

		return rid;
	}
	public String getRevreportIdImpound(CaseMonitoringDTO dto, String docid,
			String repid, HashMap map) throws Exception {

		// modified by shruti
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonDAO cmDAO = new CaseMonDAO();

		String casetxnsrno = cmDAO.getCaseTxnSrNo();
		String caseactionmapsrno = cmDAO.getActionMapSrNo();
		String casecmntsrno = cmDAO.getCaseCmntsSrNo();

		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		log.info("Inside getRevreportId()");

		boolean casemonitor = false;

		String[] txn = new String[22];
		String[] cmt = new String[5];

		txn[0] = casetxnsrno;
		//txn[1] = getRevenueautoId();
		txn[1]=getCaseNo(dto);//modified acc to new case format
		String rid = txn[1];
		txn[2] = dto.getCaseHead();
		txn[3] = dto.getCaseType();
		txn[4] = dto.getRevenueId();
		txn[5] = dto.getSectionId();
		// txn[6] = repid;
		txn[6] = dto.getReportId();
		txn[7] = null;// REG ID
		txn[8] = dto.getEamount();
		txn[9] = dto.getRecStampDuty();
		txn[10] = dto.getRecRegFee();
		txn[11] = "OPEN";
		txn[12] = dto.getSpotCmt();
		if((dto.getHearDate()=="")||(dto.getHearDate()==null)){
		txn[13]=null;
		}
		else{
		txn[13] = getDatefromString(dto.getHearDate());
		}
		txn[14] = null;// final case comments
		txn[15] = null; // rrc case
		txn[16] = null;
		txn[17] = dto.getUserId();
		txn[18] = null;
		String initiate = "CASE_000";
		txn[19] = initiate;
		//addedby shruti 26th august 2013
		if((dto.getHearDate()=="")||(dto.getHearDate()==null))
		{
			txn[20]="";
		}
		else
		txn[20]=getDatefromString(dto.getHearDate());
		txn[21]=dto.getCurrentOffId();//added by roopam-5feb2015 bug-3242

		String actionmap[] = new String[7];

		actionmap[0] = caseactionmapsrno;
		actionmap[1] = getRevnoticeautoId();
		actionmap[2] = rid;
		actionmap[3] = initiate;
		actionmap[4] = dto.getDrComment();
		actionmap[5] = "Y";
		actionmap[6] = dto.getUserId();

		// igrs_case_txn_comments
		cmt[0] = casecmntsrno;
		cmt[1] = txn[1];
		cmt[2] = getRevnoticeautoId();
		cmt[3] = dto.getDrComment();
		cmt[4] = dto.getUserId();

		
		HashMap fileMap = new HashMap();

		casemonitor = dao.getRevreportId(txn, cmt, map, actionmap);
		if (!casemonitor) {
			rid = null;
		}

		return rid;
	}
	//added by shruti -8th july 2013
	public String getRefundCaseId(CaseMonitoringDTO dto,String repid) throws Exception {

		//modified by shruti-10th july 2013-regarding case no format
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonDAO cmDAO = new CaseMonDAO();
		String casetxnsrno = cmDAO.getCaseTxnSrNo();
		String caseactionmapsrno = cmDAO.getActionMapSrNo();
		String casecmntsrno = cmDAO.getCaseCmntsSrNo();
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		log.info("Inside getEstampRefundCaseId()");
		boolean casemonitor = false;
		String[] txn = new String[22];
		//String[] cmt = new String[5];
		
		txn[0] = casetxnsrno;
		//txn[1] = getRevenueautoId();
		txn[1]=getCaseNo(dto);
		String rid = txn[1];
		txn[2] = dto.getCaseHead();
		txn[3] = dto.getCaseType();
		txn[4] = dto.getRevenueId();
		txn[5] = dto.getSectionId();
		txn[6] = "OPEN";
		txn[7] = null;// final case comments
		txn[8] = null; // rrc case
		txn[9] = dto.getRule(); // RULE 120
		txn[10] = dto.getStampAmount(); // refund amount
		txn[11] = ""; // refund bill no
		txn[12] = null; // refund bill date
		txn[13] = dto.getPartyName(); // party name
		txn[14] = dto.getPartyAddress(); //party address
		txn[15] = dto.getSrname();//sr name
		txn[16] = dto.getSroname();//sro name
		txn[17]=dto.getUserId();
		String initiate = "CASE_000";
		txn[18]=initiate;
		txn[19]=dto.getReportId();
		txn[20]=dto.getCurrentOffId();//added by roopam-3feb2015
		txn[21]=dto.getEstampType();//added by roopam-3feb2015
		//SET OFFICE ID
		//JUDICIAL/NONJUDICIAL
		
		
		String actionmap[] = new String[7];
		actionmap[0] = caseactionmapsrno;
		actionmap[1] = getRevnoticeautoId();
		actionmap[2] = rid;
		actionmap[3] = initiate;
		actionmap[4] = dto.getDrComment();
		actionmap[5] = "Y";
		actionmap[6] = dto.getUserId();
		
		casemonitor = dao.getRefundCaseId(txn, actionmap);
		if (!casemonitor) {
			rid = null;
		}
		return rid;
	}
	//end
	
	//added by shruti-20th july 2013
	public String getRevisionId(CaseMonitoringDTO dto,String repid) throws Exception {

		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonDAO cmDAO = new CaseMonDAO();
		String pnltytxnsrno = cmDAO.getPenaltySrNo();
		String caseactionmapsrno = cmDAO.getActionMapSrNo();
		String casecmntsrno = cmDAO.getCaseCmntsSrNo();
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		log.info("Inside getEstampRevisonId()");
		boolean casemonitor = false;
		String[] txn = new String[15];
		String[] cmt = new String[5];
		
		//txn[0] = casetxnsrno;
		txn[0]=pnltytxnsrno;
		txn[1]=dto.getReportId();
		txn[2]="OPEN";
		txn[3]=null;
		txn[4]=dto.getStampAmt();
		txn[5]=dto.getRegFee();
		txn[6]=dto.getSrname();
		txn[7]=dto.getSroname();
		txn[8]=dto.getFirstName();
		txn[9]=dto.getLastName();
		txn[10]=dto.getPartyAddress();
		txn[11]=dto.getUserId();
		String initiate = "CASE_000";
		txn[12]=initiate;
		txn[13]=dto.getPenaltyId();
		txn[14] = getRevisionSec45No();
		
		String rid=txn[14];
		
		String actionmap[] = new String[7];
		actionmap[0] = caseactionmapsrno;
		actionmap[1] = getRevnoticeautoId();
		actionmap[2] = rid;
		actionmap[3] = initiate;
		actionmap[4] = dto.getDrComment();
		actionmap[5] = "Y";
		actionmap[6] = dto.getUserId();
		
		casemonitor = dao.getRevisionId(txn, actionmap);
		if (!casemonitor) {
			rid = null;
		}
		return rid;
	}
	//end
	public String getRevisionIdSec70(CaseMonitoringDTO dto,String repid) throws Exception {

		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonDAO cmDAO = new CaseMonDAO();
		String pnltytxnsrno = cmDAO.getPenaltySec70SrNo();
		String caseactionmapsrno = cmDAO.getActionMapSrNo();
		String casecmntsrno = cmDAO.getCaseCmntsSrNo();
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		log.info("Inside getEstampRevisonId()");
		boolean casemonitor = false;
		String[] txn = new String[14];
		String[] cmt = new String[5];
		
		//txn[0] = casetxnsrno;
		txn[0]=pnltytxnsrno;
		txn[1]=dto.getReportId();
		txn[2]="OPEN";
		txn[3]=null;
		//txn[4]=dto.getStampAmt();
		txn[4]=dto.getRegFee();
		txn[5]=dto.getSrname();
		txn[6]=dto.getSroname();
		txn[7]=dto.getFirstName();
		txn[8]=dto.getLastName();
		txn[9]=dto.getPartyAddress();
		txn[10]=dto.getUserId();
		String initiate = "CASE_000";
		txn[11]=initiate;
		txn[12]=dto.getPenaltyId();
		txn[13] = getRevisionSec70No();
		
		String rid=txn[13];
		
		String actionmap[] = new String[7];
		actionmap[0] = caseactionmapsrno;
		actionmap[1] = getRevnoticeautoId();
		actionmap[2] = rid;
		actionmap[3] = initiate;
		actionmap[4] = dto.getDrComment();
		actionmap[5] = "Y";
		actionmap[6] = dto.getUserId();
		
		casemonitor = dao.getRevisionIdSec70(txn, actionmap);
		if (!casemonitor) {
			rid = null;
		}
		return rid;
	}
	//end
	
	
	//added by shruti-18th july 2013-others case
	public String getOthersCaseId(CaseMonitoringDTO dto,String repid) throws Exception {

		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		CaseMonDAO cmDAO = new CaseMonDAO();
		String casetxnsrno = cmDAO.getCaseTxnSrNo();
		String caseactionmapsrno = cmDAO.getActionMapSrNo();
		String casecmntsrno = cmDAO.getCaseCmntsSrNo();
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		log.info("Inside getEstampRefundCaseId()");
		boolean casemonitor = false;
		String[] txn = new String[15];
		String[] cmt = new String[5];
		
		txn[0] = casetxnsrno;
		txn[1]=getCaseNo(dto);
		String rid = txn[1];
		txn[2] = dto.getCaseHead();
		txn[3] = dto.getCaseType();
		txn[4] = dto.getRevenueId();
		txn[5] = dto.getSectionId();
		txn[6] = "OPEN";
		txn[7] = null;// final case comments
		txn[8] = null; // rrc case
		txn[9] = dto.getPenaltyAmt(); // PENALTY amount
		txn[10] = dto.getSrname();//sr name
		txn[11] = dto.getSroname();//sro name
		txn[12]=dto.getUserId();
		String initiate = "CASE_000";
		txn[13]=initiate;
		txn[14]=dto.getReportId();
		
		String actionmap[] = new String[7];
		actionmap[0] = caseactionmapsrno;
		actionmap[1] = getRevnoticeautoId();
		actionmap[2] = rid;
		actionmap[3] = initiate;
		actionmap[4] = dto.getDrComment();
		actionmap[5] = "Y";
		actionmap[6] = dto.getUserId();
		
		casemonitor = dao.getOthersCaseId(txn, actionmap);
		if (!casemonitor) {
			rid = null;
		}
		return rid;
	}
	//end

	
	//modified by shruti-9th july 2013 in order to allign as per the new case no. format
	
	private String getRevenueautoId() {
		String id = new CommonUtil().getAutoId("IGRS_CASE_TXN_DETAILS",
				"CASE_TXN_ID", "RGRF");
		return id;
	}

	// Revenue Module - getRevenueOrder
	public CaseMonitoringDTO getRevenueOrder(String caseId, String revnaction)
			throws Exception {
		CaseMonitoringDTO dto = new CaseMonitoringDTO();
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList list = dao.getRevenueOrder(caseId, revnaction);
		String comments = new String();
		ArrayList alist = null;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				alist = (ArrayList) list.get(i);
				dto.setCaseId((String) alist.get(0));
				dto.setStatus((String) alist.get(1));
				dto.setRevenueHead((String) alist.get(2));
				dto.setSection((String) alist.get(3));
				dto.setSpotCmt((String) alist.get(4));
				dto.setEamount((String) alist.get(5));
				dto.setNextDate((String) alist.get(6));
				dto.setAmount((String) alist.get(7));
				dto.setRefundAmount((String) alist.get(8));
				String a = (String) alist.get(9);
				if (a == null)
					a = "";
				comments += a + "\n\n";
				dto.setDrComment(comments);
			}
		}
		return dto;
	}

	// added by shruti

	public ArrayList getCaseRegDet(String UserId,String offId,String language) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getCaseRegDEt()");
		ArrayList list = dao.getCaseRegDet(UserId,offId,language);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				// CaseMonitoringForm cmf=new CaseMonitoringForm();
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setDistrictName((String) alist.get(0));
				cmdto.setCaseregdate((String) alist.get(1));
				listone.add(cmdto);
				// listone.add(cmf);
			}
		}
		return listone;
	}

	// for audit and inspection details
	public ArrayList getAuditList(CaseMonitoringDTO dto) throws Exception {
		String reportId = dto.getReportId();
		//modified by shruti---22nd oct 2013
		String regId=dto.getRegId(); 
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list = dao.getAuditDetails(dto);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setAuditDate((String) alist.get(0));
				cmdto.setReportId((String) alist.get(1));
				cmdto.setRegId((String) alist.get(2));
				cmdto.setValAtRegTime((String) alist.get(3));
				cmdto.setDefStampDuty((String) alist.get(4));
				cmdto.setDefRegFee((String) alist.get(5));
				cmdto.setValFromAgmp((String) alist.get(6));
				
			/*	cmdto.setSrname((String) alist.get(0));
				cmdto.setSroname((String) alist.get(1));
				cmdto.setAuditDate((String) alist.get(2));
				cmdto.setReportId((String) alist.get(3));
				cmdto.setValAtRegTime((String) alist.get(4));
				cmdto.setValFromAgmp((String) alist.get(5));
				cmdto.setDefStampDuty((String) alist.get(6));
				cmdto.setDefRegFee((String) alist.get(7));*/
				listone.add(cmdto);
			}
		}
		return listone;
	}
	
	public ArrayList getRegPartyDetails(CaseMonitoringDTO dto) throws Exception {
		String reportId = dto.getReportId();
		
		String regId=dto.getRegId(); 
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list = dao.getRegPartyDetails(dto);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < 1; i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setFirstName((String) alist.get(0));
				cmdto.setLastName((String) alist.get(1));
				
				listone.add(cmdto);
			}
		}
		return listone;
	}
	//added by shruti--1 st july 2014
	public ArrayList getRegDataList(CaseMonitoringDTO dto) throws Exception {
		String reportId = dto.getReportId();
		
		String regId=dto.getRegId(); 
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list = dao.getRegDetails(dto);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setValAtRegTime((String) alist.get(0));
				cmdto.setDocStampDuty((String) alist.get(1));
				cmdto.setDocRegFee((String) alist.get(2));
				cmdto.setSrname((String) alist.get(3));
				cmdto.setSroname((String) alist.get(4));
				cmdto.setRegDate((String) alist.get(5));
				listone.add(cmdto);
			}
		}
		return listone;
	}
	//end
	public ArrayList getRegDetailsTimeBarred(CaseMonitoringDTO dto) throws Exception {
		String reportId = dto.getReportId();
		
		String regId=dto.getRegId(); 
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list = dao.getRegDetailsTimeBarred(dto);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setValAtRegTime((String) alist.get(0));
				cmdto.setDocStampDuty((String) alist.get(1));
				cmdto.setDocRegFee((String) alist.get(2));
				cmdto.setSrname((String) alist.get(3));
				cmdto.setSroname((String) alist.get(4));
				cmdto.setRegDate((String) alist.get(5));
				listone.add(cmdto);
			}
		}
		return listone;
	}
	
	
	//added by roopam 5feb2015 pot estamp
	public ArrayList getPOTDetails(CaseMonitoringDTO dto,String lang) throws Exception {
		String reportId = dto.getReportId();
		
		String regId=dto.getRegId(); 
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list = dao.getPOTDetails(dto);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setPoComments((String) alist.get(0));
				cmdto.setAuditDate((String) alist.get(1));
				cmdto.setSrname(dao.getUserName((String) alist.get(3)));//get name from 3
				cmdto.setReqStampDuty((String) alist.get(2));
				if(lang.equalsIgnoreCase("en")){
				cmdto.setSroname(dao.getOfficeNameEng((String) alist.get(4)));//get name from 4
				cmdto.setDistrictName((String) alist.get(5));
				}else{
					cmdto.setSroname(dao.getOfficeNameHindi((String) alist.get(4)));
					cmdto.setDistrictName((String) alist.get(6));
				}
				cmdto.setRegDate((String) alist.get(1));
				listone.add(cmdto);
			}
		}
		return listone;
	}
	//end
	
	
	public String EstampDataList(CaseMonitoringDTO dto) throws Exception {//added by roopam 5feb2015 pot estamp
		
		log.info("Inside EstampDataList()");
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		
		return dao.EstampDataList(dto);
	}
	


//added by roopam 26feb2015 
public ArrayList getAuditDate(CaseMonitoringDTO dto) throws Exception {
	String reportId = dto.getReportId();
	
	String regId=dto.getRegId(); 
	CaseMonitoringDAO dao = new CaseMonitoringDAO();
	log.info("Inside getReportList()");
	ArrayList list = dao.getAuditDate(dto);
	ArrayList listone = new ArrayList();
	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
			ArrayList alist = (ArrayList) list.get(i);
			
			cmdto.setAuditDate((String) alist.get(0));
			cmdto.setPoName((String) alist.get(1));
			
			listone.add(cmdto);
		}
	}
	return listone;
}
//end

//added by roopam 27feb2015 
public ArrayList getReqDefStampDuty(CaseMonitoringDTO dto) throws Exception {
	String reportId = dto.getReportId();
	
	String regId=dto.getRegId(); 
	CaseMonitoringDAO dao = new CaseMonitoringDAO();
	log.info("Inside getReqDefStampDuty()");
	ArrayList list = dao.getReqDefStampDuty(dto);
	ArrayList listone = new ArrayList();
	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
			ArrayList alist = (ArrayList) list.get(i);
			
			cmdto.setReqStampDuty((String) alist.get(0));
			
			
			listone.add(cmdto);
		}
	}
	return listone;
}
//end
	
	public String getPOTPhysicalStampDetails(String reportId) throws Exception {//added by roopam 9feb2015 pot estamp
		
		log.info("Inside getPOTPhysicalStampDetails()");
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		ArrayList list = dao.getPOTPhysicalStampDetails(reportId);
		ArrayList rowList=new ArrayList();
		String finalList = "";
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				
				rowList=(ArrayList)(list.get(i));
				
				if(i==(list.size()-1)){
					finalList=finalList+rowList.get(0);
				}
				else{
				finalList=finalList+rowList.get(0)+", ";
				}
			}

		}

		return finalList;
	}
	
	
	//added by shruti---14 oct 2014
	//added by shruti--1 st july 2014
	public ArrayList getAmountDataList(CaseMonitoringDTO dto) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getAmountDataList()");
		ArrayList list = dao.getAmountDetails(dto);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setRecPenaltyAmt((String) alist.get(0));
				cmdto.setPenaltyAmt((String) alist.get(0));
				listone.add(cmdto);
			}
		}
		return listone;
	}
	//end
	
	public ArrayList getDurationchkAmt(CaseMonitoringDTO dto) throws Exception {
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getDurationchkAmt()");
		ArrayList list = dao.getDurationChkAmt(dto);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setDurationChk((String) alist.get(0));
				listone.add(cmdto);
			}
		}
		return listone;
	}
	
	
	public ArrayList getRegDataListImpoundCase(CaseMonitoringDTO dto) throws Exception {
		String reportId = dto.getReportId();
		
		String regId=dto.getRegId(); 
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list = dao.getRegDetailsImpoundCase(dto);
		ArrayList listone = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
				ArrayList alist = (ArrayList) list.get(i);
				cmdto.setValAtRegTime((String) alist.get(0));
				cmdto.setDocStampDuty((String) alist.get(1));
				cmdto.setDocRegFee((String) alist.get(2));
				cmdto.setSrname((String) alist.get(3));
				cmdto.setSroname((String) alist.get(4));
				cmdto.setRegDate((String) alist.get(5));
				listone.add(cmdto);
			}
		}
		return listone;
	}

	public ArrayList getEstampTxnDetails(CaseMonitoringDTO dto) throws Exception {
	
		String reportId = dto.getReportId();
	CaseMonitoringDAO dao = new CaseMonitoringDAO();
	log.info("Inside getReportList()");
	ArrayList list = dao.getEstampTxndDetails(reportId);
	ArrayList listone = new ArrayList();
	if (list != null) {
		for (int i = 0; i < list.size(); i++) {
			CaseMonitoringDTO cmdto = new CaseMonitoringDTO();
			ArrayList alist = (ArrayList) list.get(i);
			cmdto.setEstampIssuePerson((String) alist.get(0));
			cmdto.setEstampIssuedOffice((String) alist.get(1));
			cmdto.setEstampRefundPerson((String) alist.get(2));
			cmdto.setEstampRefundPrsnAddress((String) alist.get(3));
			//modified by shruti---30 sep 2014
			cmdto.setEstampAmt((String) alist.get(4));
			cmdto.setEstampFactor((String) alist.get(5));
			listone.add(cmdto);
		}
	}
	return listone;
}
	//added by shruti-27 august 2013
	public PartyDetailsDTO getImpoundedDataList(CaseMonitoringDTO dto) throws Exception {
		
		String reportId = dto.getReportId();
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list = dao.getImpondedDocDetails(reportId);
		ArrayList listone = new ArrayList();
		ArrayList partyList=new ArrayList();
		PartyDetailsDTO cmdto= new PartyDetailsDTO();
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++)
			{
				PartyDetailsDTO pdto= new PartyDetailsDTO();
				ArrayList alist = (ArrayList) list.get(i);
				pdto.setFirstName((String) alist.get(0));
				pdto.setPartyName((String) alist.get(0)+" "+(String) alist.get(2));
				pdto.setPartyTypeName((String) alist.get(3));
				if(pdto.getFirstName()=="" || pdto.getFirstName()==null)
				{
					pdto.setPartyName((String) alist.get(4));
				}
				/*cmdto.setSrname((String) alist.get(0));
				cmdto.setRegId((String) alist.get(1));
				cmdto.setFirstName((String) alist.get(2));
				cmdto.setMidName((String) alist.get(3));
				cmdto.setLastName((String) alist.get(4));
				cmdto.setRegDate((String) alist.get(5));*/
				
				/*cmdto.setSrname((String) alist.get(0));
				cmdto.setSroname((String) alist.get(1));
				cmdto.setAuditDate((String) alist.get(2));
				cmdto.setReportId((String) alist.get(3));
				cmdto.setValAtRegTime((String) alist.get(4));
				cmdto.setValFromAgmp((String) alist.get(5));
				cmdto.setDefStampDuty((String) alist.get(6));
				cmdto.setDefRegFee((String) alist.get(7));*/
				//listone.add(cmdto);
				partyList.add(pdto);
				cmdto.setPartyList(partyList);
			}
		}
		return cmdto;
	}
public PartyDetailsDTO getPartyList(CaseMonitoringDTO dto,String language) throws Exception {
		
		String regId = dto.getRegId();
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		log.info("Inside getReportList()");
		ArrayList list = dao.getPartyDetails(regId,language);
		ArrayList listone = new ArrayList();
		ArrayList partyList=new ArrayList();
		PartyDetailsDTO cmdto= new PartyDetailsDTO();
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++)
			{
				PartyDetailsDTO pdto= new PartyDetailsDTO();
				ArrayList alist = (ArrayList) list.get(i);
				pdto.setFirstName((String) alist.get(0));
				pdto.setPartyName((String) alist.get(0)+" "+(String) alist.get(2));
				pdto.setPartyTypeName((String) alist.get(3));
				if(pdto.getFirstName()=="" || pdto.getFirstName()==null)
				{
					pdto.setPartyName((String) alist.get(4));
				}
				partyList.add(pdto);
				cmdto.setPartyList(partyList);
			}
		}
		return cmdto;
	}
public PartyDetailsDTO getPartyDetailsNew(CaseMonitoringDTO dto,String language) throws Exception {
	
	String regId = dto.getRegId();
	CaseMonitoringDAO dao = new CaseMonitoringDAO();
	log.info("Inside getPartyDetailsNew()");
	ArrayList list = dao.getPartyDetailsNew(regId,language);
	ArrayList listone = new ArrayList();
	ArrayList partyList=new ArrayList();
	PartyDetailsDTO cmdto= new PartyDetailsDTO();
	
	if (list != null) {
		for (int i = 0; i < list.size(); i++)
		{
			PartyDetailsDTO pdto= new PartyDetailsDTO();
			ArrayList alist = (ArrayList) list.get(i);
			//pdto.setFirstName((String) alist.get(0));
			pdto.setPartyName(((String) alist.get(0)).trim());
			pdto.setPartyTypeName(((String) alist.get(1)).trim());
			/*if(pdto.getFirstName()=="" || pdto.getFirstName()==null)
			{
				pdto.setPartyName((String) alist.get(4));
			}*/
			partyList.add(pdto);
			cmdto.setPartyList(partyList);
		}
	}
	return cmdto;
}
public PartyDetailsDTO getPOTPartyList(CaseMonitoringDTO dto,String language) throws Exception {
	
	String estampId = dto.getEstampId();
	CaseMonitoringDAO dao = new CaseMonitoringDAO();
	log.info("Inside getPOTPartyList()");
	ArrayList list = dao.getPOTPartyDetails(estampId,language);
	ArrayList listone = new ArrayList();
	ArrayList partyList=new ArrayList();
	PartyDetailsDTO cmdto= new PartyDetailsDTO();
	
	if (list != null) {
		for (int i = 0; i < list.size(); i++)
		{
			PartyDetailsDTO pdto= new PartyDetailsDTO();
			ArrayList alist = (ArrayList) list.get(i);
			pdto.setFirstName((String) alist.get(0));
			pdto.setPartyName((String) alist.get(0)+" "+(String) alist.get(2));
			//pdto.setPartyTypeName((String) alist.get(3));
			if(pdto.getFirstName()=="" || pdto.getFirstName()==null)
			{
				pdto.setPartyName((String) alist.get(3));
			}
			partyList.add(pdto);
			cmdto.setPartyList(partyList);
		}
	}
	return cmdto;
}

public PartyDetailsDTO getPOTPstampPartyList(CaseMonitoringDTO dto,String language) throws Exception {
	
	String reportId = dto.getReportId();
	CaseMonitoringDAO dao = new CaseMonitoringDAO();
	log.info("Inside getPOTPstampPartyList()");
	ArrayList list = dao.getPOTPstampPartyList(reportId,language);
	ArrayList listone = new ArrayList();
	ArrayList partyList=new ArrayList();
	PartyDetailsDTO cmdto= new PartyDetailsDTO();
	
	if (list != null) {
		for (int i = 0; i < list.size(); i++)
		{
			PartyDetailsDTO pdto= new PartyDetailsDTO();
			ArrayList alist = (ArrayList) list.get(i);
			pdto.setFirstName((String) alist.get(0));
			pdto.setPartyName((String) alist.get(0)+" "+(String) alist.get(2));
			//pdto.setPartyTypeName((String) alist.get(3));
			/*if(pdto.getFirstName()=="" || pdto.getFirstName()==null)
			{
				pdto.setPartyName((String) alist.get(3));
			}*/
			partyList.add(pdto);
			cmdto.setPartyList(partyList);
		}
	}
	return cmdto;
}

// added by satbir kumar for case views

public ArrayList<CaseMonitoringDTO> getotherDetailsList(String language,
		String caseId, CaseMonitoringDTO caseDTO) {
	ArrayList OtherCaseDetailList=new ArrayList();
	ArrayList retList=new ArrayList();
	CaseMonitoringDAO dao = new CaseMonitoringDAO();
	OtherCaseDetailList=dao.getotherDetailsList(language,caseId,caseDTO);
	
	try{
		
		for(int i=0;i<OtherCaseDetailList.size();i++){
			ArrayList subList=(ArrayList)OtherCaseDetailList.get(i);
			CaseMonitoringDTO DTO= new CaseMonitoringDTO();
			
			DTO.setViewCaseActName((String) subList.get(0));
			DTO.setViewCaseActComments((String) subList.get(1));
			DTO.setViewCaseNoticeDate((String) subList.get(2));
			DTO.setViewCasePartyRes((String) subList.get(3));
			DTO.setViewCasePartwill((String) subList.get(4));
			DTO.setViewCaseAttachment((String) subList.get(5));
			
			
			
			retList.add(DTO);
		}
	}catch (Exception e) {
		e.printStackTrace();
}
	return retList;
}

public ArrayList<CaseMonitoringDTO> getcaseDetailsList(String language,
		String caseId, CaseMonitoringDTO caseDTO) {
	ArrayList CaseDetailList=new ArrayList();
	ArrayList retList=new ArrayList();
	CaseMonitoringDAO dao = new CaseMonitoringDAO();
	
	try{
		CaseDetailList=dao.getcaseDetailsList(language,caseId,caseDTO);
		for(int i=0;i<CaseDetailList.size();i++){
			ArrayList subList=(ArrayList)CaseDetailList.get(i);
			CaseMonitoringDTO DTO= new CaseMonitoringDTO();
			DTO.setViewCaseId((String) subList.get(0));
			DTO.setViewCaseStatus((String) subList.get(1));
			DTO.setViewCaseRevHead((String) subList.get(2));
			DTO.setViewCaseSec((String) subList.get(3));
			DTO.setViewCaseSpot((String) subList.get(4));
			DTO.setViewCaseValDR((String) subList.get(5));
			DTO.setViewCaseHearDate((String) subList.get(6));
			DTO.setViewCaseRegFee((String) subList.get(7));
			DTO.setViewCaseStampDut((String) subList.get(8));
			DTO.setViewCaseDRComments((String) subList.get(9));
			retList.add(DTO);
		}
	}catch (Exception e) {
		e.printStackTrace();
}
	return retList;
}


//end of addition
}