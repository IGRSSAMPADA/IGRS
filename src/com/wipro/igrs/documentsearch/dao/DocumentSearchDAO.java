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
package com.wipro.igrs.documentsearch.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.documentsearch.constant.CommonConstant;
import com.wipro.igrs.documentsearch.dto.DocumentSearchDTO;
import com.wipro.igrs.documentsearch.sql.CommonSQL;
import com.wipro.igrs.newPropvaluation.sql.PropertyValuationSQL;

public class DocumentSearchDAO {

	CommonConstant COMMONCONSTANT = new CommonConstant();

	Logger logger = (Logger) Logger.getLogger(DocumentSearchDAO.class);
	public DocumentSearchDAO() throws Exception {
		logger.debug("WE ARE IN DS DAO");
	}
	public ArrayList checkRegistrationId(String _refRegId, String language) throws Exception {
		logger.debug(" DAO checkRegistrationId--");
		String refRegTransId = null;
		ArrayList resultList = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.DOC_SEARCH_SELECT_REGID + "'" + _refRegId + "'";
			dbUtil.createStatement();
			refRegTransId = dbUtil.executeQry(str);
			if (refRegTransId != null && !"".equalsIgnoreCase(refRegTransId)) {
				String refEstampId = checkEstampTxnId(refRegTransId);
				ArrayList partyList = getPartyDetails(refRegTransId, language);
				ArrayList propertyList = getPropertyDetails(refRegTransId, language);
				ArrayList bankChargesList = getBankChargeId(refRegTransId);
				ArrayList caveatlist = getCaveatDeatils(_refRegId, language);
				ArrayList complainceList = getPropertyComplainceDetails(refRegTransId);
				resultList.add(refRegTransId);
				resultList.add(refEstampId);
				resultList.add(partyList);
				resultList.add(propertyList);
				resultList.add(bankChargesList);
				resultList.add(caveatlist);
				resultList.add(complainceList);
				logger.debug(" --in dao-- reg id--" + ((String) resultList.get(0)));
				logger.debug(" --in dao-- estamp id--" + (String) resultList.get(1));
				logger.debug(" --in dao-- party list--" + resultList.get(2));
				logger.debug(" --in dao-- property list--" + resultList.get(3));
				logger.debug(" --in dao-- subclause list--" + resultList.get(4));
				logger.debug(" --in dao-- complaince list--" + resultList.get(5));
				ArrayList tmpList = (ArrayList) resultList.get(3);
				if (tmpList != null && tmpList.size() > 0) {
					logger.debug("--size--<>" + tmpList.size());
					for (int j = 0; j < tmpList.size(); j++) {
						logger.debug("tmp list  for--<>" + j);
						ArrayList arl = (ArrayList) tmpList.get(j);
						if (arl != null && arl.size() > 0) {
							String propId = (String) arl.get(0);
							String newpropId = (String) arl.get(1);
						}
					}
				}
			} else {
				resultList.add("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}

	// added by shruti---14 feb 2014
	public ArrayList checkOldRegistrationId(DocumentSearchDTO dsdto, String language) throws Exception {
		logger.debug(" DAO checkRegistrationId--");
		String refRegTransId = null;
		ArrayList resultList = new ArrayList();
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {dsdto.getSroName(), dsdto.getSroName(), dsdto.getSrName(), dsdto.getBookNo(), dsdto.getVolNo(), dsdto.getSerialNo(), dsdto.getDateOfReg()};
			String str = CommonSQL.DOC_SEARCH_SELECT_OLD_REGID;
			dbUtil.createPreparedStatement(str);
			refRegTransId = dbUtil.executeQry(param);
			if (refRegTransId != null && !"".equalsIgnoreCase(refRegTransId)) {
				String refEstampId = checkEstampTxnId(refRegTransId);
				ArrayList partyList = getPartyDetails(refRegTransId, language);
				ArrayList propertyList = getPropertyDetails(refRegTransId, language);
				ArrayList caveatlist = getCaveatDeatils(refRegTransId, language);
				ArrayList complainceList = getPropertyComplainceDetails(refRegTransId);
				resultList.add(refRegTransId);
				resultList.add(refEstampId);
				resultList.add(partyList);
				resultList.add(propertyList);
				resultList.add(caveatlist);
				resultList.add(complainceList);
				logger.debug(" --in dao-- reg id--" + ((String) resultList.get(0)));
				logger.debug(" --in dao-- estamp id--" + (String) resultList.get(1));
				logger.debug(" --in dao-- party list--" + resultList.get(2));
				logger.debug(" --in dao-- property list--" + resultList.get(3));
				logger.debug(" --in dao-- subclause list--" + resultList.get(4));
				logger.debug(" --in dao-- complaince list--" + resultList.get(5));
				logger.debug("Ruuuu ");
				logger.debug("Ru " + propertyList.get(0));
			} else {
				resultList.add("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}

	public ArrayList getKhasraDetail(String _propId) throws Exception {
		logger.debug(" DAO checkRegistrationId--");
		String refRegTransId = null;
		ArrayList resultList = new ArrayList();
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.DOC_SEARCH_KHASRA_DETAILS;
			logger.debug(" in dao try for getKhasraDetail --" + str);
			dbUtil.createPreparedStatement(str);
			resultList = dbUtil.executeQuery(new String[]{_propId});
			if (resultList != null) {

			} else {
				resultList.add("error");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}
	public ArrayList verifyRegistrationId(String _refRegId) throws Exception {
		logger.debug(" DAO checkRegistrationId--");
		String refRegTransId = null;
		ArrayList resultList = new ArrayList();
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {_refRegId};
			String str = CommonSQL.DOC_SEARCH_SELECT_REGID;
			dbUtil.createPreparedStatement(str);
			refRegTransId = dbUtil.executeQry(param);
			resultList.add(refRegTransId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}
	public String storeBTypeSearch(String paymentflag, DocumentSearchDTO dsto, DocumentSearchDTO dsdto, String userId, String functionId) throws Exception {

		String docTxnId = "";
		String TxnId = "";
		String payTxnId = "";
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			boolean flg1 = false;
			String dataInsert = CommonSQL.DOC_SEARCH_BTXN_INSERT;
			// added by ShreeraJ
			String dataInsertNew = CommonSQL.DOC_SEARCH_BTXN_NEW_INSERT;
			// String dataInsertNew=CommonSQL.DOC_SEARCH_BTXN_NEW_INSERT;
			String mainInsert = CommonSQL.DOC_SEARCH_DETAILS_INSERT;

			String payInsert = CommonSQL.DOC_SEARCH_PAYTXN_INSERT;
			String[] detArray = new String[21];
			String[] mainArray = new String[5];
			String[] payArray = new String[5];
			IGRSCommon common = new IGRSCommon();
			docTxnId = common.getSequenceNumber("DUMMY", "CERTIFIEDCOPY");
			TxnId = common.getTransactionNumber("DUMMY");
			payTxnId = common.getTransactionNumber("DUMMY");
			/*
			 * logger.debug("Document Transaction id generated for payment is "+docTxnId); logger.debug("Transaction id generated for data is "+TxnId); logger.debug("Transaction id generated for document is "+docTxnId); logger.debug("Transaction id generated for payment is "+payTxnId);
			 */
			// Main Table Insert
			try {
				mainArray[0] = docTxnId;
				mainArray[1] = userId;
				mainArray[2] = null;
				mainArray[3] = userId;
				mainArray[4] = functionId;

				boolean flg = false;
				dbUtil.createPreparedStatement(mainInsert);
				flg = dbUtil.executeUpdate(mainArray);
				logger.debug("status--<>flg1--" + flg + "-- flg1--" + flg1);
				if (!flg) {
					dbUtil.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Payment Table Insert

			try {
				payArray[0] = payTxnId;
				payArray[1] = docTxnId;
				payArray[2] = paymentflag;
				payArray[3] = functionId;
				payArray[4] = userId;
				boolean flg = false;

				dbUtil.createPreparedStatement(payInsert);
				flg = dbUtil.executeUpdate(payArray);
				logger.debug("status--<>flg1--" + flg + "-- flg1--" + flg1);
				if (!flg) {
					dbUtil.rollback();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (docTxnId != null) {
				try {
					logger.debug("<------------------------" + dsdto.getToDate1() + dsdto.getFromDate1());
					logger.info(dsto.getToDate() + " new " + dsdto.getFromDate() + "reg" + dsdto.getRegDate() + dsdto.getRegDate());
					logger.info(dsto.getToDate1() + " old " + dsto.getFromDate1());
					SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
					SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
					String regDate = "";
					if (dsdto.getRegDate() != "") {
						Date regDate1 = format1.parse(dsto.getRegDate());
						regDate = format2.format(regDate1);
					}

					logger.info("flag is" + dsto.getRegFlag() + dsdto.getRegFlag());

					if (dsto.getRegFlag().equalsIgnoreCase("new")) {
						logger.debug("in new flag<------------");
						String finaltodate = "";
						String finalfromdate = "";

						if (!(dsto.getToDate().equals("")) || (dsto.getToDate().equals(null))) {
							Date todate = format1.parse(dsto.getToDate());
							Date fromdate = format1.parse(dsto.getFromDate());
							logger.info(format2.format(todate));
							logger.info(format2.format(fromdate));
							if ((todate != null) || !(todate.equals("")) && (fromdate != null) || !(fromdate.equals(""))) {
								finaltodate = format2.format(todate);
								finalfromdate = format2.format(fromdate);

								logger.info("tehsil" + dsto.getTehisilName() + "pat" + dsto.getWardpatwarName() + "moh" + dsto.getMohallaName());
								detArray[0] = TxnId;
								detArray[1] = docTxnId;
								detArray[2] = dsto.getDistId();
								detArray[3] = dsdto.getTehisilId();
								detArray[4] = dsdto.getAreaTypeId();
								detArray[5] = dsdto.getWardId();
								detArray[6] = dsdto.getWardpatwarId();
								detArray[7] = dsdto.getMohallaId();
								detArray[8] = finalfromdate;
								detArray[9] = finaltodate;
								detArray[10] = userId;
								detArray[11] = dsto.getTransPartyFirstName();
								detArray[12] = dsto.getTransPartyMiddName();
								detArray[13] = dsto.getTransPartyLastName();
								detArray[14] = dsto.getTransPartyGender();
								detArray[15] = dsto.getTransPartyMotName();
								detArray[16] = dsto.getTransPartyFatName();
								detArray[17] = dsto.getTransPartyAdd();
								detArray[18] = dsto.getPropertyAddr();
								detArray[19] = regDate;
								detArray[20] = dsto.getOrgName();

								dbUtil.createPreparedStatement(dataInsertNew);
								flg1 = dbUtil.executeUpdate(detArray);
							}
						}
					} else if (dsto.getRegFlag().equalsIgnoreCase("old")) {
						logger.info("old flag");
						String finaltodate1 = "";
						String finalfromdate1 = "";
						if ((dsto.getToDate1().equals(null)) || !(dsto.getToDate1().equals(""))) {
							Date todate1 = format1.parse(dsto.getToDate1());
							Date fromdate1 = format1.parse(dsto.getFromDate1());

							logger.info(format2.format(todate1));
							logger.info(format2.format(fromdate1));
							finaltodate1 = format2.format(todate1);
							finalfromdate1 = format2.format(fromdate1);
							detArray[0] = TxnId;
							detArray[1] = docTxnId;
							detArray[2] = dsto.getDistId1();
							detArray[3] = dsto.getTehsilName();
							detArray[4] = dsto.getAreaName();
							detArray[5] = dsto.getWarddName();
							detArray[6] = dsto.getWardPatName();
							detArray[7] = dsto.getMohName();
							detArray[8] = finalfromdate1;
							detArray[9] = finaltodate1;
							detArray[10] = userId;
							detArray[11] = dsto.getTransPartyFirstName();
							detArray[12] = dsto.getTransPartyMiddName();
							detArray[13] = dsto.getTransPartyLastName();
							detArray[14] = dsto.getTransPartyGender();
							detArray[15] = dsto.getTransPartyMotName();
							detArray[16] = dsto.getTransPartyFatName();
							detArray[17] = dsto.getTransPartyAdd();
							detArray[18] = dsto.getPropertyAddr();
							detArray[19] = regDate;
							detArray[20] = dsto.getOrgName();

							dbUtil.createPreparedStatement(dataInsert);
							flg1 = dbUtil.executeUpdate(detArray);

							// added by shruti
							dsto.setParentReferenceId(docTxnId);
							dsdto.setParentReferenceId(docTxnId);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		logger.debug(docTxnId);
		// modified by shruti-9th sep 2013
		// return docTxnId;
		// modified by shruti 19th sep
		return payTxnId + "~" + docTxnId;
	}

	public String storeCTypeSearch(String paymentflag, DocumentSearchDTO dsto, String userId, String functionId) throws Exception {

		String docTxnId = "";
		String TxnId;
		// String payTxnId;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			boolean flg1 = false;
			String dataInsert = CommonSQL.DOC_SEARCH_BTXN_INSERT;
			String mainInsert = CommonSQL.DOC_SEARCH_DETAILS_INSERT;
			// added by ShreeraJ
			String dataInsertNew = CommonSQL.DOC_SEARCH_BTXN_NEW_INSERT;
			// String payInsert=CommonSQL.DOC_SEARCH_PAYTXN_INSERT;
			String[] detArray = new String[21];
			String[] mainArray = new String[5];
			// String[] payArray=new String[5];
			IGRSCommon common = new IGRSCommon();
			docTxnId = common.getSequenceNumber("DUMMY", "CERTIFIEDCOPY");
			TxnId = common.getTransactionNumber("DUMMY");
			// payTxnId=common.getTransactionNumber("DUMMY");
			/*
			 * logger.debug("Document Transaction id generated for payment is "+docTxnId); logger.debug("Transaction id generated for data is "+TxnId); logger.debug("Transaction id generated for document is "+docTxnId);
			 */

			// Main Table Insert
			try {
				mainArray[0] = docTxnId;
				mainArray[1] = userId;
				mainArray[2] = null;
				mainArray[3] = userId;
				mainArray[4] = functionId;

				boolean flg = false;
				dbUtil.createPreparedStatement(mainInsert);
				flg = dbUtil.executeUpdate(mainArray);
				logger.debug("status--<>flg1--" + flg + "-- flg1--" + flg1);
				if (!flg) {
					dbUtil.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (docTxnId != null) {
				try {
					logger.debug("<------------------------" + dsto.getToDate1() + dsto.getFromDate1());
					logger.info(dsto.getToDate() + " new " + dsto.getFromDate() + "reg" + dsto.getRegDate() + dsto.getRegDate());
					logger.info(dsto.getToDate1() + " old " + dsto.getFromDate1());
					SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
					SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
					String regDate = "";
					if (!(dsto.getRegDate().equals("")) || (dsto.getRegDate().equals(null))) {
						Date regDate1 = format1.parse(dsto.getRegDate());
						regDate = format2.format(regDate1);
					}
					if (dsto.getRegFlag() != "" || dsto.getRegFlag() != null) {
						logger.info("flag is" + dsto.getRegFlag() + dsto.getRegFlag());
						if (dsto.getRegFlag().equalsIgnoreCase("new")) {
							logger.debug("in new flag<------------");
							String finaltodate = "";
							String finalfromdate = "";
							if (!(dsto.getToDate().equals("")) || (dsto.getToDate().equals(null))) {
								Date todate = format1.parse(dsto.getToDate());
								Date fromdate = format1.parse(dsto.getFromDate());
								logger.info(format2.format(todate));
								logger.info(format2.format(fromdate));
								if ((todate != null) || !(todate.equals("")) && (fromdate != null) || !(fromdate.equals(""))) {
									finaltodate = format2.format(todate);
									finalfromdate = format2.format(fromdate);
								}
							}
							logger.info(dsto.getMohallaId());
							logger.info("tehsil" + dsto.getTehisilName() + "pat" + dsto.getWardpatwarName() + "moh" + dsto.getMohallaName());
							detArray[0] = TxnId;
							detArray[1] = docTxnId;
							detArray[2] = dsto.getDistId();
							detArray[3] = dsto.getTehisilId();
							detArray[4] = dsto.getAreaTypeId();
							detArray[5] = dsto.getSubAreaWardMappingId().split("~")[0];
							detArray[6] = dsto.getWardpatwarId();
							detArray[7] = dsto.getMohallaId().split("~")[0];
							detArray[8] = finalfromdate;
							detArray[9] = finaltodate;
							detArray[10] = userId;
							detArray[11] = dsto.getTransPartyFirstName();
							detArray[12] = dsto.getTransPartyMiddName();
							detArray[13] = dsto.getTransPartyLastName();
							detArray[14] = dsto.getTransPartyGender();
							detArray[15] = dsto.getTransPartyMotName();
							detArray[16] = dsto.getTransPartyFatName();
							detArray[17] = dsto.getTransPartyAdd();
							detArray[18] = dsto.getPropertyAddr();
							detArray[19] = regDate;
							detArray[20] = dsto.getOrgName();

							dbUtil.createPreparedStatement(dataInsertNew);
							flg1 = dbUtil.executeUpdate(detArray);
						} else if (dsto.getRegFlag().equalsIgnoreCase("old")) {
							logger.info("old flag");
							String finaltodate1 = "";
							String finalfromdate1 = "";
							if (!(dsto.getToDate1().equals("")) || (dsto.getToDate1().equals(null))) {
								Date todate1 = format1.parse(dsto.getToDate1());
								Date fromdate1 = format1.parse(dsto.getFromDate1());

								logger.info(format2.format(todate1));
								logger.info(format2.format(fromdate1));
								finaltodate1 = format2.format(todate1);
								finalfromdate1 = format2.format(fromdate1);
							}
							detArray[0] = TxnId;
							detArray[1] = docTxnId;
							detArray[2] = dsto.getDistId1();
							detArray[3] = dsto.getTehsilName();
							detArray[4] = dsto.getAreaName();
							detArray[5] = dsto.getWarddName();
							detArray[6] = dsto.getWardPatName();
							detArray[7] = dsto.getMohName();
							detArray[8] = finalfromdate1;
							detArray[9] = finaltodate1;
							detArray[10] = userId;
							detArray[11] = dsto.getTransPartyFirstName();
							detArray[12] = dsto.getTransPartyMiddName();
							detArray[13] = dsto.getTransPartyLastName();
							detArray[14] = dsto.getTransPartyGender();
							detArray[15] = dsto.getTransPartyMotName();
							detArray[16] = dsto.getTransPartyFatName();
							detArray[17] = dsto.getTransPartyAdd();
							detArray[18] = dsto.getPropertyAddr();
							detArray[19] = regDate;
							detArray[20] = dsto.getOrgName();

							dbUtil.createPreparedStatement(dataInsert);
							flg1 = dbUtil.executeUpdate(detArray);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		/* logger.debug(docTxnId); */
		return docTxnId;
	}

	public String storeOfficeTxnDetails(String searchPurpose, String regnum, String userId, String functionId) throws Exception {

		String docTxnId = "";
		String TxnId = "";
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			boolean flg1 = false;

			String detailsInsert = CommonSQL.DOC_OFFICE_SEARCH_DETAILS_INSERT;
			String[] detArray = new String[6];
			IGRSCommon common = new IGRSCommon();
			docTxnId = common.getSequenceNumber("DUMMY", "CERTIFIEDCOPY");
			// logger.debug("Document Transaction id generated for payment is "+docTxnId);
			TxnId = common.getTransactionNumber("DUMMY");
			// logger.debug("Transaction id generated for payment is "+TxnId);

			if (docTxnId != null) {
				detArray[0] = docTxnId;
				detArray[1] = userId;
				detArray[2] = regnum;
				detArray[3] = userId;
				detArray[4] = functionId;
				detArray[5] = searchPurpose;
				dbUtil.createPreparedStatement(detailsInsert);
				flg1 = dbUtil.executeUpdate(detArray);
			}
			boolean flg = false;
			if (!flg) {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		// logger.debug(regnum);
		return docTxnId;
	}

	// added by shruti---25 aug 2014
	public boolean updateOfficeTxnDetails(String docName, String docPath, String docTxnId) throws Exception {

		String TxnId = "";
		boolean flg1 = false;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();

			String detailsUpdate = CommonSQL.DOC_OFFICE_SEARCH_DETAILS_UPDATE;
			String[] detArray = new String[3];

			if (docTxnId != null) {
				detArray[0] = docName;
				detArray[1] = docPath;
				detArray[2] = docTxnId;
				dbUtil.createPreparedStatement(detailsUpdate);
				flg1 = dbUtil.executeUpdate(detArray);
			}
			boolean flg = false;
			if (!flg) {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		return flg1;
	}
	// end
	public String storePaymetTxnDetails(String paymentflag, DocumentSearchDTO dsdto, String userId, String functionId, String refID) throws Exception {

		String docTxnId = "";
		String TxnId = "";
		String regnum = "";
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			boolean flg1 = false;

			regnum = dsdto.getRegistNumber();
			if (dsdto.getRegistNumber() == null) {
				regnum = "";
			}
			if (dsdto.getDateOfReg() == null) {
				dsdto.setDateOfReg("");
			}
			if (dsdto.getAreaTypeId() == null) {
				dsdto.setAreaTypeId("");
			}

			String detailsInsert = CommonSQL.DOC_SEARCH_A_DETAILS_INSERT;
			String sqlInsert = CommonSQL.DOC_SEARCH_PAYTXN_INSERT;
			String[] insrArray = new String[6];
			String[] detArray = new String[13];
			IGRSCommon common = new IGRSCommon();

			if (refID != null && !("").equalsIgnoreCase(refID))// for partial payment
				docTxnId = refID;
			else
				docTxnId = common.getSequenceNumber("DUMMY", "CERTIFIEDCOPY");
			// logger.debug("Document Transaction id generated for payment is "+docTxnId);
			TxnId = common.getTransactionNumber("DUMMY");
			// logger.debug("Transaction id generated for payment is "+TxnId);
			insrArray[0] = TxnId;
			insrArray[1] = docTxnId;
			insrArray[2] = paymentflag;
			insrArray[3] = functionId;
			insrArray[4] = userId;
			insrArray[5] = dsdto.getTotalFee();
			if (docTxnId != null) {
				detArray[0] = docTxnId;
				detArray[1] = userId;
				detArray[2] = regnum;
				detArray[3] = userId;
				detArray[4] = functionId;
				// added by shruti
				detArray[5] = dsdto.getDistName();
				detArray[6] = dsdto.getSroName();
				detArray[7] = dsdto.getSrName();
				detArray[8] = dsdto.getBookNo();
				detArray[9] = dsdto.getVolNo();
				detArray[10] = dsdto.getSerialNo();
				detArray[11] = dsdto.getDateOfReg();
				detArray[12] = dsdto.getAreaTypeId();

				if (refID == null || ("").equalsIgnoreCase(refID))// for partial payment
				{
					dbUtil.createPreparedStatement(detailsInsert);
					flg1 = dbUtil.executeUpdate(detArray);
				}
			}
			boolean flg = false;
			dbUtil.createPreparedStatement(sqlInsert);
			flg = dbUtil.executeUpdate(insrArray);
			// added by shruti
			dsdto.setParentReferenceId(docTxnId);
			// end

			logger.debug("status--<>flg1--" + flg + "-- flg1--" + flg1);
			if (!flg) {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		// logger.debug(regnum);
		// modified by shruti
		return TxnId;
	}

	public String updateOfficialTxnDetails(String docTxnId, String regnum) throws Exception {

		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			boolean flg1 = false;

			String detailsInsert = CommonSQL.UPDATEDOCSRCHDTLS;

			// String sqlInsert=CommonSQL.DOC_SEARCH_PAYTXN_INSERT;
			// String[] insrArray=new String[5];
			String[] detArray = new String[2];
			// logger.debug("Document Transaction id generated for updateOfficialTxnDetails is "+docTxnId);

			if (docTxnId != null) {
				detArray[0] = regnum;
				detArray[1] = docTxnId;
				dbUtil.createPreparedStatement(detailsInsert);
				flg1 = dbUtil.executeUpdate(detArray);
			}
			boolean flg = false;

			logger.debug("status--<>flg1--" + flg + "-- flg1--" + flg1);
			if (!flg) {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		// logger.debug(regnum);
		return docTxnId;
	}

	public String updatePaymetTxnDetails(String paymentflag, String docTxnId, String regnum, String userId, String functionId) throws Exception {

		String TxnId = "";
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			boolean flg1 = false;

			String detailsInsert = CommonSQL.UPDATEPYMTTXNDTLS;

			String sqlInsert = CommonSQL.DOC_SEARCH_PAYTXN_INSERT;
			String[] insrArray = new String[5];
			String[] detArray = new String[2];

			IGRSCommon common = new IGRSCommon();
			// logger.debug("Document Transaction id generated for payment is "+docTxnId);
			TxnId = common.getTransactionNumber("DUMMY");
			// logger.debug("Transaction id generated for payment is "+TxnId);
			insrArray[0] = TxnId;
			insrArray[1] = docTxnId;
			insrArray[2] = paymentflag;
			insrArray[3] = functionId;
			insrArray[4] = userId;
			if (docTxnId != null) {
				detArray[0] = regnum;
				detArray[1] = docTxnId;
				dbUtil.createPreparedStatement(detailsInsert);
				flg1 = dbUtil.executeUpdate(detArray);
			}
			boolean flg = false;
			dbUtil.createPreparedStatement(sqlInsert);
			flg = dbUtil.executeUpdate(insrArray);
			logger.debug("status--<>flg1--" + flg + "-- flg1--" + flg1);
			if (!flg) {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		// logger.debug(regnum);
		return TxnId;
	}

	public String downloadPaymetTxnDetails(String paymentflag, String docTxnId, String userId, String functionId, String fee) throws Exception {

		String TxnId = "";
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String sqlInsert = CommonSQL.DOC_SEARCH_PAYTXN_INSERT;
			String[] insrArray = new String[6];
			IGRSCommon common = new IGRSCommon();
			TxnId = common.getTransactionNumber("DUMMY");
			// logger.debug("Document Transaction id generated for payment is "+docTxnId);
			// logger.debug("Transaction id generated for payment is "+TxnId);
			insrArray[0] = TxnId;
			insrArray[1] = docTxnId;
			insrArray[2] = paymentflag;
			insrArray[3] = functionId;
			insrArray[4] = userId;
			insrArray[5] = fee;
			boolean flg = false;
			dbUtil.createPreparedStatement(sqlInsert);
			flg = dbUtil.executeUpdate(insrArray);
			if (!flg) {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		return TxnId;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String checkEstampTxnId(String _refTranId) throws Exception {
		logger.debug(" DAO checkEstampTxnId--");
		String refEstampTransId = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {_refTranId};
			// modified by shruti as per latest db-2nd aug 2013
			/* String str = "SELECT ESTAMP_TXN_ID  FROM IGRS_REG_INIT_TXN_DETLS WHERE TRANSACTION_ID ='"+_refTranId+"'"; */
			// MODIFIED BY SHRUTI-5TH AUG 2013
			/* String str = "SELECT ESTAMP_TXN_ID  FROM IGRS_REG_ESTAMP_MAP WHERE TRANSACTION_ID ='"+_refTranId+"'"; */
			// moidified by shruti-25th sep 2013
			String str = CommonSQL.CHKESTAMPCODE;
			dbUtil.createPreparedStatement(str);
			refEstampTransId = dbUtil.executeQry(param);
			// logger.debug("in dao try for checkEstampTxnId--" + str);
			/*
			 * dbUtil.createStatement(); refEstampTransId = dbUtil.executeQry(str);
			 */
			// logger.debug("in dao try for checkEstampTxnId--" + refEstampTransId);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return refEstampTransId;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPartyDetails(String _refTranId, String language) throws Exception {
		logger.debug(" DAO getPartyDetails--");
		ArrayList partyList = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {_refTranId};
			String str = "";
			if ("en".equalsIgnoreCase(language)) {
				str = CommonSQL.GETPARTYDTLS;
			} else {
				str = CommonSQL.GETPARTYDTLS_H;
			}
			dbUtil.createPreparedStatement(str);
			partyList = dbUtil.executeQuery(param);
			/*
			 * String str = "SELECT PARTY_FIRST_NAME,PARTY_MIDDLE_NAME,PARTY_LAST_NAME,( SELECT PARTY_TYPE_NAME FROM " +" IGRS_PARTY_TYPE_MASTER WHERE PARTY_TYPE_STATUS='A' AND  PARTY_TYPE_ID=rtpd.PARTY_TYPE_ID) PARTY_TYPE_NAME, " +" PARTY_GENDER,PARTY_AGE,NATIONALITY,(SELECT COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE COUNTRY_STATUS='A' " +" AND COUNTRY_ID=rtpd.COUNTRY_ID) COUNTRY_NAME,(SELECT STATE_NAME FROM IGRS_STATE_MASTER WHERE " +" STATE_STATUS='A' AND STATE_ID=RTPD.STATE_ID) STATE_NAME, (SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER  WHERE DISTRICT_STATUS='A' AND  " +" DISTRICT_ID= RTPD.DISTRICT_ID) DISTRICT_NAME,PARTY_ADDRESS,POSTAL_CODE,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID, " +" APPELLATE_TYPE_ID,GUARDIAN_NAME, MOTHER_NAME,ORGANIZATION_NAME,AUTHRZD_PERSN_NAME from IGRS_REG_TXN_PARTY_DETLS rtpd where REG_TXN_ID='"+_refTranId+"' order by PARTY_TYPE_ID";
			 */
			logger.debug("in dao try for district" + str);
			/*
			 * dbUtil.createStatement(); partyList = dbUtil.executeQuery(str);
			 */
			// logger.debug("in dao try for getPartyDetails" + partyList);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return partyList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyDetails(String _refTranId, String language) throws Exception {
		logger.debug(" DAO getPartyDetails--");
		ArrayList propertyList = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {_refTranId};
			String str = "";
			if ("en".equalsIgnoreCase(language)) {
				str = CommonSQL.GETPROPERTYDTLS;
			} else {
				str = CommonSQL.GETPROPERTYDTLS_H;
			}
			dbUtil.createPreparedStatement(str);
			propertyList = dbUtil.executeQuery(param);
			logger.debug("in dao try for getPropertyDetails" + propertyList.get(0));
			for (int i = 0; i < propertyList.size(); i++) {
				System.out.println(propertyList.get(i));
			}
			/*
			 * dbUtil.createStatement(); propertyList = dbUtil.executeQuery(str);
			 */
			// logger.debug("in dao try for getPropertyDetails" + propertyList);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return propertyList;
	}

	public ArrayList getCaveatDeatils(String _refTranId, String language) throws Exception {
		logger.debug(" DAO getPropertySubcaluseDetails--");
		ArrayList propertyList = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {_refTranId};
			String str = CommonSQL.BankChargeId;

			// logger.debug("in dao try for getPropertySubcaluseDetails" + str);
			dbUtil.createPreparedStatement(str);
			propertyList = dbUtil.executeQuery(param);
			/*
			 * dbUtil.createStatement(); propertyList = dbUtil.executeQuery(str);
			 */
			// logger.debug("in dao try for getPropertySubcaluseDetails" + propertyList);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return propertyList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertySubcaluseDetails(String _refTranId) throws Exception {
		logger.debug(" DAO getPropertySubcaluseDetails--");
		ArrayList propertyList = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {_refTranId};
			String str = CommonSQL.GETSUBCLAUSEDTLS;
			logger.debug("in dao try for getPropertySubcaluseDetails" + str);

			dbUtil.createPreparedStatement(str);
			propertyList = dbUtil.executeQuery(param);
			/*
			 * dbUtil.createStatement(); propertyList = dbUtil.executeQuery(str);
			 */
			logger.debug("in dao try for getPropertySubcaluseDetails" + propertyList);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return propertyList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyComplainceDetails(String _refTranId) throws Exception {
		logger.debug(" DAO getPropertyComplainceDetails--");
		ArrayList complainceList = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.GETCOMPIANCEDTLS;
			logger.debug("in dao try for getPropertyComplainceDetails" + str);
			dbUtil.createStatement();
			complainceList = dbUtil.executeQuery(str);
			// logger.debug("in dao try for getPropertyComplainceDetails" + complainceList);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return complainceList;
	}

	/**
	 * @param _searchDTO
	 * @param _sqlQry
	 * @return
	 * @throws Exception
	 */
	public ArrayList getTypeBSearchDetails(String[] _docArray, String _sqlQry) throws Exception {
		ArrayList docsearchList = new ArrayList();
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			// String str = "SELECT COMPLAINCE_NAME FROM IGRS_REG_COMPLIANCE_DETAILS WHERE COMPLAINCE_STATUS='A' ";
			logger.debug("in dao try for getPropertyComplainceDetails" + _sqlQry);
			dbUtil.createPreparedStatement(_sqlQry);
			docsearchList = dbUtil.executeQuery(_docArray);

		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return docsearchList;
	}
	/**
	 * @param _searchDTO
	 * @param _sqlQry
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDocAdvanceSearchDetails(String _regId) throws Exception {
		ArrayList docsearchList = new ArrayList();
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();

			String[] param = {_regId};
			/*
			 * String sqlQry = "SELECT distinct C.REGISTRATION_NUMBER,to_date(c.CREATED_DATE,'dd/mm/yyyy') created_date,A.PROPERTY_ID, A.PROPERTY_ADDRESS "+ "FROM IGRS_REG_PROPRTY_DTLS A,IGRS_REG_TXN_DETLS C where A.REG_TXN_ID=C.REG_TXN_ID"+ " and C.REG_COMP_NUMBER='"+_regId+"'";
			 */

			String sqlQry = CommonSQL.GEWTADVNCDSRCHDTLS;
			logger.debug("in dao try for getPropertyComplainceDetails" + sqlQry);

			dbUtil.createPreparedStatement(sqlQry);
			docsearchList = dbUtil.executeQuery(param);
			/*
			 * dbUtil.createStatement(); docsearchList= dbUtil.executeQuery(sqlQry);
			 */

		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return docsearchList;
	}
	/**
	 * @param _searchDTO
	 * @param _sqlQry
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDocPartyDetails(String propertyId, DocumentSearchDTO _searchDTO) throws Exception {
		ArrayList docPartyList = new ArrayList();
		DBUtility dbUtil = null;

		try {

			dbUtil = new DBUtility();
			String sqlQry = "  SELECT distinct PARTYPE.PARTY_TYPE_ID,PARTY_TYPE_NAME,PARTY_FIRST_NAME||' '||PARTY_MIDDLE_NAME||' ' " + " ||PARTY_LAST_NAME || ' ' ||ORGANIZATION_NAME|| ' ' || AUTHRZD_PERSN_NAME|| ' ' ||DESG_OF_OFFICIAL  FROM IGRS_REG_TXN_PARTY_DETLS PARDET ,IGRS_PARTY_TYPE_MASTER PARTYPE  ,IGRS_REG_INIT_PROP_TRANS_MAP PROPMAP ,IGRS_REG_PROPRTY_DTLS PROPDTLS,IGRS_REG_PROP_KHASARA_DTLS KHASRADTLS " + " WHERE PARTY_TYPE_STATUS='A' AND  PARTYPE.PARTY_TYPE_ID=PARDET.PARTY_TYPE_ID AND PROPMAP.PROPERTY_ID=PROPDTLS.PROPERTY_ID AND PARDET.PARTY_TXN_ID=PROPMAP.PARTY_TXN_ID AND " + "  PROPMAP.PROPERTY_ID='" + propertyId + "' and PROPDTLS.PROPERTY_ID='" + propertyId + "'";

			// modified by shruti-2nd aug 2013

			String fName = _searchDTO.getTransPartyFirstName();
			String mName = _searchDTO.getTransPartyMiddName();
			String lName = _searchDTO.getTransPartyLastName();
			String momName = _searchDTO.getTransPartyMotName();
			String dadName = _searchDTO.getTransPartyFatName();
			String prtyAdd = _searchDTO.getTransPartyAdd();
			String propAdd = _searchDTO.getPropertyAddr();
			String orgName = _searchDTO.getOrgName();
			String khasraNo = _searchDTO.getKhasraNumber();
			logger.info("first" + fName + " midd" + mName + " last" + lName + " mom" + momName + " dad" + dadName + " partyadd" + prtyAdd + " propadd" + propAdd + " orgname" + orgName + " khasra number" + khasraNo);

			/*
			 * if(fName.equalsIgnoreCase(null)){ String sqlQry ="  SELECT PARTYPE.PARTY_TYPE_ID,PARTY_TYPE_NAME,PARTY_FIRST_NAME||' '||PARTY_MIDDLE_NAME||' ' "+ " ||PARTY_LAST_NAME || ' ' ||ORGANIZATION_NAME||  '' AUTHRZD_PERSN_NAME  FROM IGRS_REG_TXN_PARTY_DETLS PARDET ,IGRS_PARTY_TYPE_MASTER PARTYPE  ,IGRS_REG_INIT_PROP_TRANS_MAP PROPMAP  "+ " WHERE PARTY_TYPE_STATUS='A' AND  PARTYPE.PARTY_TYPE_ID=PARDET.PARTY_TYPE_ID AND PARDET.PARTY_TXN_ID=PROPMAP.PARTY_TXN_ID AND "+ "  PROPMAP.PROPERTY_ID='"+propertyId+"'"; }
			 */
			if (!((fName.equalsIgnoreCase(null)) || (fName.equalsIgnoreCase("")))) {

				sqlQry = sqlQry + "  AND PARTY_FIRST_NAME='" + fName + "'";
			}

			if (!((mName.equalsIgnoreCase(null)) || (mName.equalsIgnoreCase("")))) {

				sqlQry = sqlQry + "  AND PARTY_MIDDLE_NAME='" + mName + "'";

			}
			if (!((lName.equalsIgnoreCase(null)) || (lName.equalsIgnoreCase("")))) {

				sqlQry = sqlQry + "  AND PARTY_LAST_NAME='" + lName + "'";;

			}
			if (!((orgName.equalsIgnoreCase(null)) || (orgName.equalsIgnoreCase("")))) {

				sqlQry = sqlQry + "	 AND PARDET.ORGANIZATION_NAME='" + orgName + "'";;

			}
			if (!((momName.equalsIgnoreCase(null)) || (momName.equalsIgnoreCase("")))) {

				sqlQry = sqlQry + "	 AND PARDET.MOTHER_NAME='" + momName + "'";;

			}
			if (!((dadName.equalsIgnoreCase(null)) || (dadName.equalsIgnoreCase("")))) {

				sqlQry = sqlQry + "	 AND PARDET.GUARDIAN_NAME='" + dadName + "'";;

			}
			if (!((prtyAdd.equalsIgnoreCase(null)) || (prtyAdd.equalsIgnoreCase("")))) {

				sqlQry = sqlQry + "	 AND PARDET.PARTY_ADDRESS='" + prtyAdd + "'";;

			}
			if (!((propAdd.equalsIgnoreCase(null)) || (propAdd.equalsIgnoreCase("")))) {

				sqlQry = sqlQry + " AND PROPDTLS.PROPERTY_ADDRESS='" + propAdd + "'";;

			}
			if (!((khasraNo.equalsIgnoreCase(null)) || (khasraNo.equalsIgnoreCase("")))) {

				sqlQry = sqlQry + " PROPDTLS.PROPERTY_ID=KHASRADTLS.PROPERTY_ID AND KHASRADTLS.KHASRA_NUMBER='" + khasraNo + "'";;

			}

			// logger.info("--sqlQry--<>"+sqlQry);

			logger.debug("in dao try for getPropertyComplainceDetails" + sqlQry);
			dbUtil.createStatement();
			docPartyList = dbUtil.executeQuery(sqlQry);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return docPartyList;
	}

	// adedd by shruti-3th aug
	public ArrayList getSrchAresultList(String userId) throws Exception {
		logger.debug(" DAO getSrchAresultList()--");
		ArrayList partyList = null;
		String count = "";
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();

			// String[] param={CommonConstant.SEARCHAVIEWCOUNT};
			// String sql=CommonSQL.GETSEARCHALISTDTLS;
			/*
			 * dbUtil.createStatement(); count=dbUtil.executeQry(sql);
			 */
			// dbUtil.createPreparedStatement(sql);
			// count=dbUtil.executeQry(param);
			String[] param1 = {userId};
			String str = CommonSQL.GETSEARCHARECORDLIST;
			logger.debug(" @@@Print Query for A search" + str);
			dbUtil.createPreparedStatement(str);
			partyList = dbUtil.executeQuery(param1);

			/*
			 * dbUtil.createStatement(); partyList = dbUtil.executeQuery(str);
			 */
			// logger.debug("in dao try forgetSrchAresultList()" + partyList);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return partyList;
	}

	public ArrayList getSrchBresultList(String userId) throws Exception {
		logger.debug(" DAO getSrchAresultList()--");
		ArrayList partyList = null;
		String count = "";
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();

			// String[] param={CommonConstant.SEARCHBVIEWCOUNT};

			// String sql=CommonSQL.GETSEARCHBLISTDTLS;
			// dbUtil.createPreparedStatement(sql);
			// /count=dbUtil.executeQry(param);
			/*
			 * dbUtil.createStatement(); count=dbUtil.executeQry(sql);
			 */

			String[] param1 = {userId};
			String str = CommonSQL.GETSEARCHBRECORDLIST;
			logger.debug(" @@@Print Query for B Search" + str);
			dbUtil.createPreparedStatement(str);
			partyList = dbUtil.executeQuery(param1);

			/*
			 * dbUtil.createStatement(); partyList = dbUtil.executeQuery(str);
			 */
			// logger.debug("in dao try forgetSrchAresultList()" + partyList);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return partyList;
	}

	// adedd by shruti-16th aug 2013
	public ArrayList getSrchLogList(String toDate) throws Exception {
		logger.debug(" DAO getSrchAresultList()--");
		ArrayList logList = null;
		String count = "";
		String period = "";
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {CommonConstant.ADMINSEARCHLOGENTRY};
			String sql = CommonSQL.GETLOGLENGTH;
			/*
			 * dbUtil.createStatement(); count=dbUtil.executeQry(sql);
			 */
			dbUtil.createPreparedStatement(sql);
			count = dbUtil.executeQry(param);

			String[] param1 = {CommonConstant.LOGDURATIONPARAM};
			String sql1 = CommonSQL.GETLOGDURATION;
			/*
			 * dbUtil.createStatement(); period=dbUtil.executeQry(sql1);
			 */
			dbUtil.createPreparedStatement(sql1);
			period = dbUtil.executeQry(param1);

			String[] param2 = {toDate, period, count};
			String str = CommonSQL.GETRLOGRECORD;
			dbUtil.createPreparedStatement(str);
			logList = dbUtil.executeQuery(param2);

			/*
			 * dbUtil.createStatement(); logList = dbUtil.executeQuery(str);
			 */
			// logger.debug("in dao try forgetSrchAresultList()" + logList);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return logList;
	}

	public ArrayList getDownloadLogList(String toDate) throws Exception {
		logger.debug(" DAO getSrchAresultList()--");
		ArrayList logList = null;
		String count = "";
		String period = "";
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {CommonConstant.ADMINSEARCHLOGENTRY};
			String sql = CommonSQL.GETDOWNLOADCONFIGPARAM;
			/*
			 * dbUtil.createStatement(); count=dbUtil.executeQry(sql);
			 */
			dbUtil.createPreparedStatement(sql);
			count = dbUtil.executeQry(param);

			String[] param1 = {CommonConstant.LOGDURATIONPARAM};
			String sql1 = CommonSQL.GETDWNLOADDURATION;
			/*
			 * dbUtil.createStatement(); period=dbUtil.executeQry(sql1);
			 */
			dbUtil.createPreparedStatement(sql1);
			period = dbUtil.executeQry(param1);

			String[] param2 = {toDate, period, count};
			String str = CommonSQL.GETDWNLOADLOGLIST;

			dbUtil.createPreparedStatement(str);
			logList = dbUtil.executeQuery(param2);
			/*
			 * dbUtil.createStatement(); logList = dbUtil.executeQuery(str);
			 */
			// logger.debug("in dao try forgetSrchAresultList()" + logList);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return logList;
	}
	// adedd by shruti-19th aug 2013
	public ArrayList getUserDetailsList(String userid) throws Exception {
		logger.debug(" DAO getSrchAresultList()--");
		ArrayList detailsList = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {userid};
			String str = CommonSQL.GETUSERDTLSLIST;

			dbUtil.createPreparedStatement(str);
			detailsList = dbUtil.executeQuery(param);
			/*
			 * dbUtil.createStatement(); detailsList = dbUtil.executeQuery(str);
			 */
			// logger.debug("in dao try getUserDetailsList" + detailsList);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return detailsList;
	}

	// adedd by shruti
	public ArrayList checkRegistrationIdNew(String _refRegId, String flag, String language) throws Exception {
		logger.debug(" DAO checkRegistrationId--");
		String refRegTransId = null;
		ArrayList resultList = new ArrayList();
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = "";
			if (flag.equalsIgnoreCase("N")) {
				str = CommonSQL.DOC_SEARCH_SELECT_REGID + "'" + _refRegId + "'";
			} else if (flag.equalsIgnoreCase("O")) {
				str = CommonSQL.DOC_SEARCH_SELECT_OLD_REGID + "'" + _refRegId + "'";
			}
			logger.debug(" in dao try for checkRegistrationId --" + str);
			dbUtil.createStatement();
			refRegTransId = dbUtil.executeQry(str);
			if (refRegTransId != null && !" ".equalsIgnoreCase(refRegTransId)) {
				String refEstampId = checkEstampTxnId(refRegTransId);
				ArrayList partyList = getPartyDetails(refRegTransId, language);
				ArrayList propertyList = getPropertyDetails(refRegTransId, language);

				ArrayList caveatlist = getCaveatDeatils(_refRegId, language);
				ArrayList complainceList = getPropertyComplainceDetails(refRegTransId);
				resultList.add(refRegTransId);
				resultList.add(refEstampId);
				resultList.add(partyList);
				resultList.add(propertyList);
				resultList.add(caveatlist);
				resultList.add(complainceList);

			} else {
				resultList.add("error");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}
	// ADDED BY SHRUTI

	public ArrayList checkRegistrationIdNewWithProtest(String _refRegId, String flag, String language) throws Exception {
		logger.debug(" DAO checkRegistrationId--");
		String refRegTransId = null;
		ArrayList resultList = new ArrayList();
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = "";
			if (flag.equalsIgnoreCase("N")) {
				str = CommonSQL.DOC_SEARCH_SELECT_REGID + "'" + _refRegId + "'";
			} else if (flag.equalsIgnoreCase("O")) {
				str = CommonSQL.DOC_SEARCH_SELECT_OLD_REGID + "'" + _refRegId + "'";
			}
			logger.debug(" in dao try for checkRegistrationId --" + str);
			dbUtil.createStatement();
			refRegTransId = dbUtil.executeQry(str);
			if (refRegTransId != null && !" ".equalsIgnoreCase(refRegTransId)) {
				String refEstampId = checkEstampTxnId(refRegTransId);
				ArrayList partyList = getPartyDetails(refRegTransId, language);
				ArrayList propertyList = getPropertyDetails(refRegTransId, language);

				ArrayList caveatlist = getCaveatDeatils(_refRegId, language);
				ArrayList complainceList = getPropertyComplainceDetails(refRegTransId);
				resultList.add(refRegTransId);
				resultList.add(refEstampId);
				resultList.add(partyList);
				resultList.add(propertyList);
				resultList.add(caveatlist);
				resultList.add(complainceList);

			} else {
				resultList.add("error");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}
	public ArrayList checkResumeState(String docTxnId) throws Exception {
		logger.debug(" DAO checkRegistrationId--");
		String docTransId = null;
		ArrayList resultList = new ArrayList();
		String[] param = {docTxnId, docTxnId};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.SEARCH_RESUME_QRY;
			logger.debug(" in dao try for checkResumeState --" + str);
			dbUtil.createPreparedStatement(str);
			docTransId = dbUtil.executeQry(param);
			resultList.add(docTransId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}

	// added by shruti
	public ArrayList getTypeBSearchRecordDetails(String DocTxnId) throws Exception {
		ArrayList docsearchList = new ArrayList();
		DBUtility dbUtil = null;

		try {
			String[] txnid = {DocTxnId};
			dbUtil = new DBUtility();
			String str = CommonSQL.GETTYPEBRECORDDTLS;
			logger.debug("in dao try for getTypeBSearchRecordDetails" + str);
			dbUtil.createPreparedStatement(str);
			docsearchList = dbUtil.executeQuery(txnid);

		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return docsearchList;
	}

	// added by shruti

	public String chkUser(String userId) throws Exception {

		logger.debug(" getPaymentParameters--");
		ArrayList resultList = new ArrayList();
		String typeid = "";
		String[] param = {userId};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.CHECKUSERTYPE;
			logger.debug(" in dao try for getPaymentParameters --" + str);
			dbUtil.createPreparedStatement(str);
			typeid = dbUtil.executeQry(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return typeid;
	}
	public ArrayList getExternalUserDtls(String userId) throws Exception {

		logger.debug(" getExternalUserDtls--");
		ArrayList resultList = new ArrayList();
		String typeid = "";
		String[] param = {userId};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.CHECKUSERTYPE;
			logger.debug(" in dao try for getExternalUserDtls --" + str);
			dbUtil.createPreparedStatement(str);
			resultList = dbUtil.executeQuery(param);
			typeid = dbUtil.executeQry(param);

			String str2 = CommonSQL.GETSPDISTRICT;
			dbUtil.createPreparedStatement(str2);
			resultList = dbUtil.executeQuery(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}
	//
	public ArrayList getRUUserDtls(String userId) throws Exception {

		logger.debug(" getExternalUserDtls--");
		ArrayList resultList = new ArrayList();
		String typeid = "";
		String[] param = {userId};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str1 = CommonSQL.GETRUDISTRICT;
			dbUtil.createPreparedStatement(str1);
			resultList = dbUtil.executeQuery(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}
	// end
	public ArrayList getInternalUserDtls(String officeId) {
		ArrayList list = null;
		DBUtility dbUtil = null;
		String officeName = "";
		ArrayList resultList = new ArrayList();
		String arry[] = new String[1];
		if (officeId != null) {
			arry[0] = officeId;
		}
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GETOFFICENAME);
			// officeName=dbUtil.executeQry(arry);
			resultList = dbUtil.executeQuery(arry);
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

	public String storeSearchATxnDetails(DocumentSearchDTO dsdto, String userId, String functionId) throws Exception {

		String docTxnId = "";
		String TxnId = "";
		String regnum = "";
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			boolean flg1 = false;

			regnum = dsdto.getRegistNumber();
			if (dsdto.getRegistNumber() == null) {
				regnum = "";
			}
			if (dsdto.getDateOfReg() == null) {
				dsdto.setDateOfReg("");
			}
			if (dsdto.getAreaTypeId() == null) {
				dsdto.setAreaTypeId("");
			}

			String detailsInsert = CommonSQL.DOC_SEARCH_A_DETAILS_INSERT;
			String[] detArray = new String[13];
			IGRSCommon common = new IGRSCommon();
			docTxnId = common.getSequenceNumber("DUMMY", "CERTIFIEDCOPY");
			// logger.debug("Document Transaction id generated for payment is "+docTxnId);
			TxnId = common.getTransactionNumber("DUMMY");
			// logger.debug("Transaction id generated for payment is "+TxnId);
			if (docTxnId != null) {
				detArray[0] = docTxnId;
				detArray[1] = userId;
				detArray[2] = regnum;
				detArray[3] = userId;
				detArray[4] = functionId;
				detArray[5] = dsdto.getDistName();
				detArray[6] = dsdto.getSroName();
				detArray[7] = dsdto.getSrName();
				detArray[8] = dsdto.getBookNo();
				detArray[9] = dsdto.getVolNo();
				detArray[10] = dsdto.getSerialNo();
				detArray[11] = dsdto.getDateOfReg();
				detArray[12] = dsdto.getAreaTypeId();
				dbUtil.createPreparedStatement(detailsInsert);
				flg1 = dbUtil.executeUpdate(detArray);
			}
			logger.debug("status--<>flg1--" + flg1 + "-- flg1--" + flg1);
			if (!flg1) {
				dbUtil.rollback();
			}
			if (flg1) {
				dbUtil.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		// logger.debug(regnum);
		// modified by shruti
		return docTxnId;
	}

	// added by shruti----26 nov 2014
	public String searchGoLiveDate(String distId) throws Exception {

		String date = "";
		String[] param = {distId};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_GO_LIVE_DATE);
			date = dbUtil.executeQry(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		// logger.debug(regnum);
		// modified by shruti
		return date;
	}
	public static String getConvertedDate(String date) {

		String finalDate = "";
		if (date == null)
			date = "";
		if (date != null && !date.equalsIgnoreCase("")) {
			StringTokenizer st = new StringTokenizer(date, "/");
			String day = st.nextToken();
			String month = st.nextToken();
			String year = st.nextToken();
			String inputDate = day + "-" + month + "-" + year;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date newDate;
			try {
				newDate = formatter.parse(inputDate);
				SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
				finalDate = format.format(newDate);
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return finalDate;
	}

	public ArrayList getTableDetails(String fiscalYearId) throws Exception {
		ArrayList tableList = new ArrayList<String>();
		String[] param = {fiscalYearId};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_TABLE_NAME);
			ArrayList temp = dbUtil.executeQuery(param);
			if (temp.size() >= 1) {
				tableList = (ArrayList) temp.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		return tableList;
	}

	public ArrayList searchRegisteredDoc(DocumentSearchDTO dsdto, String language) throws ParseException {
		ArrayList list = new ArrayList();
		ArrayList queryResult;
		ArrayList retVal = new ArrayList();
		String rolegroup;
		String SQL = "";
		String fiscalYearId = "";
		String mainTable = "";
		String partyTable = "";
		ArrayList tableDetail = new ArrayList<String>();
		String[] param = null;
		IGRSCommon common = null;
		DBUtility dbUtil = null;

		try {
			// dbUtil = new DBUtility();
			common = new IGRSCommon();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (dsdto.getHdnDistName() != null && !dsdto.getHdnDistName().equals("")) {
			/*
			 * param=new String[18]; param[0]=dsdto.getHdnDistName(); param[1]=dsdto.getHdnTehsilName(); param[2]=getConvertedDate(dsdto.getFromDate()); param[3]=getConvertedDate(dsdto.getToDate()); param[4]=dsdto.getDistId(); param[5]=dsdto.getTehisilId(); param[6]=dsdto.getAreaTypeId(); param[7]=dsdto.getSubAreaTypeId(); param[8]=dsdto.getSubAreaWardMappingId(); param[9]=dsdto.getMohallaId(); param[10]=dsdto.getKhasraNumber(); param[11]=dsdto.getTransPartyFirstName(); param[12]=dsdto.getTransPartyMiddName(); param[13]=dsdto.getTransPartyLastName(); param[14]=dsdto.getTransPartyMotName(); param[15]=dsdto.getTransPartyFatName(); param[16]=dsdto.getOrgName(); param[17]=dsdto.getTransPartyAdd();
			 */

			param = new String[17];
			// param[0]=dsdto.getHdnDistName().toUpperCase(); //changes for mview
			param[0] = dsdto.getHdnRegDistId();
			param[1] = dsdto.getHdnTehsilName().toUpperCase();
			param[2] = getConvertedDate(dsdto.getFromDate());
			param[3] = getConvertedDate(dsdto.getToDate());
			param[4] = dsdto.getDistId();
			param[5] = dsdto.getTehisilId();
			param[6] = dsdto.getAreaTypeId();
			param[7] = dsdto.getSubAreaTypeId();
			param[8] = dsdto.getSubAreaWardMappingId();
			param[9] = dsdto.getMohallaId();
			param[10] = dsdto.getKhasraNumber().toUpperCase();
			param[11] = dsdto.getTransPartyFirstName().toUpperCase();
			param[12] = dsdto.getTransPartyMiddName().toUpperCase();
			param[13] = dsdto.getTransPartyLastName().toUpperCase();
			param[14] = dsdto.getTransPartyMotName().toUpperCase();
			param[15] = dsdto.getTransPartyFatName().toUpperCase();
			param[16] = dsdto.getOrgName().toUpperCase();
			// param[17] = dsdto.getTransPartyAdd().toUpperCase();

			fiscalYearId = dsdto.getFiscalYearId();
		}

		if ("en".equalsIgnoreCase(language) || "hi".equalsIgnoreCase(language)) {
			StringBuilder stbr = null;
			try {
				tableDetail = getTableDetails(fiscalYearId);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (!tableDetail.isEmpty()) {
				mainTable = (String) tableDetail.get(0) + " P,";
				partyTable = (String) tableDetail.get(1) + " PA,";
			}
			if ("hi".equalsIgnoreCase(language)) {
				// stbr = new StringBuilder(CommonSQL.SEARCHBASICQRY_UPDATED_MV); // same for both hindi and engligh "like" removed from district name
				stbr = new StringBuilder(CommonSQL.SEARCH_BASE_QUERY);

			} else {
				// stbr = new StringBuilder(CommonSQL.SEARCHBASICQRY_UPDATED_MV);
				stbr = new StringBuilder(CommonSQL.SEARCH_BASE_QUERY);
			}
			stbr.append(mainTable);
			stbr.append(partyTable);
			stbr.append(CommonSQL.COMPLETING_BASE_QUERY);
			TreeMap<Integer, String> paramsMap = new TreeMap<Integer, String>();
			// changed by rustam

			paramsMap.put(0, param[0]);
			/*
			 * if (param[4]!=null && "".equals(param[4]) == false) { paramsMap.put(1, param[4]); }else{ paramsMap.put(1, ""); }
			 */

			/*
			 * if (param[4]!=null && "".equals(param[4]) == false) { paramsMap.put(2, param[4]); }else{ paramsMap.put(2, ""); }
			 */// commented for mview by saurav
			/*
			 * System.out.println(param[2]+" ------------date from and date end---------------"+param[3]); Date dFrom = new SimpleDateFormat("DD-MM-YYYY").parse(param[2]); Date dTo = new SimpleDateFormat("DD-MM-YYYY").parse(param[3]);
			 * 
			 * int difference = dFrom.compareTo(dTo); int loopCount = 1; int leftDays=0; if (difference > 60) { loopCount = difference / 60; leftDays = difference%60; } for (int lc = 0; lc <= loopCount; lc++) { queryResult = new ArrayList(); if(!(lc==loopCount)){ param[2] = new SimpleDateFormat("DD/MM/YYYY").format(dFrom); param[3] = new SimpleDateFormat("DD/MM/YYYY").format(dFrom.getTime() + (60 * 24 * 60 * 60 * 1000)); dFrom = new SimpleDateFormat("DD/MM/YYYY").parse(param[3]); }else{ param[2] = new SimpleDateFormat("DD/MM/YYYY").format(dFrom); param[3] = new SimpleDateFormat("DD/MM/YYYY").format(dTo.getTime() + (leftDays * 24 * 60 * 60 * 1000)); dFrom = new SimpleDateFormat("DD/MM/YYYY").parse(param[3]); }
			 */
			if (param[1] != null && "".equalsIgnoreCase(param[1]) == false) {
				stbr.append(CommonSQL.GETQRYWITHTEHSIL);
				paramsMap.put(3, "%" + param[1] + "%");
			}
			if ((param[2] != null && "".equals(param[2]) == false) && (param[3] != null && "".equals(param[3]) == false)) {
				stbr.append(CommonSQL.GETQRYWITHDURATION);
				paramsMap.put(4, param[2] + " 00:00:00");
				paramsMap.put(5, param[3] + " 23:59:59");
			}
			if (param[4] != null && "".equals(param[4]) == false) {
				stbr.append(CommonSQL.GETQRYWITHPROPDISTRICT);
				paramsMap.put(6, param[4]);
			}
			if (param[5] != null && "".equals(param[5]) == false) {
				stbr.append(CommonSQL.GETQRYWITHPROPTEHSIL);
				paramsMap.put(7, param[5]);
			}
			if (param[6] != null && "".equals(param[6]) == false) {
				stbr.append(CommonSQL.GETQRYWITHPROPAREA);
				paramsMap.put(8, param[6]);
			}
			if (param[7] != null && "".equals(param[7]) == false) {
				stbr.append(CommonSQL.GETQRYWITHPROPGOVBODY);
				paramsMap.put(9, param[7]);
			}
			if (param[8] != null && "".equals(param[8]) == false) {
				stbr.append(CommonSQL.GETQRYWITHPROPWARD);
				paramsMap.put(10, param[8].split("~")[0]);
			}
			if (param[9] != null && "".equals(param[9]) == false) {
				stbr.append(CommonSQL.GETQRYWITHPROPMOHALLA);
				paramsMap.put(11, param[9].split("~")[0]);// split func required
			}
			if (param[10] != null && "".equals(param[10]) == false) {
				stbr.append(CommonSQL.GETQRYWITHPROPKHASRA);
				paramsMap.put(12, "%" + param[10] + "%");
			}
			if (param[11] != null && "".equals(param[11]) == false) {
				stbr.append(CommonSQL.GETFNAMEQUERY);
				paramsMap.put(13, "%" + param[11] + "%");
				// paramsMap.put(14, "%" + param[11] + "%");
			}
			if (param[12] != null && "".equals(param[12]) == false) {
				stbr.append(CommonSQL.GETMIDNAMEQUERY);
				paramsMap.put(14, "%" + param[12] + "%");
			}
			if (param[13] != null && "".equals(param[13]) == false) {
				stbr.append(CommonSQL.GETLASTNAMEQUERY);
				paramsMap.put(15, "%" + param[13] + "%");
			}
			if (param[14] != null && "".equals(param[14]) == false) {
				stbr.append(CommonSQL.GETMOTHERNAME);
				paramsMap.put(16, "%" + param[14] + "%");
			}
			if (param[15] != null && "".equals(param[15]) == false) {
				stbr.append(CommonSQL.GETGUARDIANNAME);
				paramsMap.put(17, "%" + param[15] + "%");
			}
			if (param[16] != null && "".equals(param[16]) == false) {
				stbr.append(CommonSQL.GETORGNAME);
				paramsMap.put(18, "%" + param[16] + "%");
			}
			/*
			 * if (param[17] != null && "".equals(param[17]) == false) { stbr.append(CommonSQL.GETFNAMEQUERY); paramsMap.put(20, "%" + param[17] + "%"); paramsMap.put(21, "%" + param[17] + "%"); }
			 */
			stbr.append(CommonSQL.ORDERREGNOASC);
			logger.info("Final build query : " + stbr.toString());
			logger.info("Params mapping : " + paramsMap);

			try {
				dbUtil = new DBUtility();
				Collection<String> values = paramsMap.values();
				ArrayList<String> tmp = new ArrayList(values);
				String[] params = tmp.toArray(new String[]{});
				logger.info("query params : " + params);
				DocumentSearchDAO dao = new DocumentSearchDAO();
				queryResult = dao.getRegDocDetails(stbr.toString(), params, dsdto);
				retVal.addAll(queryResult);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// retVal.addAll(queryResult);
		}
		// }
		/*
		 * else if("hi".equalsIgnoreCase(language)) { StringBuilder stbr = new StringBuilder(CommonSQL.SEARCHBASICQRYHI); TreeMap<String, String> paramsMap = new TreeMap<String, String>(); paramsMap.put("0", "%"+param[0]+"%");
		 * 
		 * if(param[1]!=null && "".equalsIgnoreCase(param[1])==false ) { stbr.append(CommonSQL.GETQRYWITHTEHSILHI); paramsMap.put("1", "%"+param[1]+"%"); } if ((param[2]!=null && "".equals(param[2]) == false) && (param[3]!=null && "".equals(param[3]) == false)) { stbr.append(CommonSQL.GETQRYWITHDURATION); paramsMap.put("2", param[2]); paramsMap.put("3", param[3]); } if (param[4]!=null && "".equals(param[4]) == false) { stbr.append(CommonSQL.GETQRYWITHPROPDISTRICT); paramsMap.put("4", param[4]+"%"); } if (param[5]!=null && "".equals(param[5]) == false) { stbr.append(CommonSQL.GETQRYWITHPROPTEHSIL); paramsMap.put("5", param[5]+"%"); } if (param[6]!=null && "".equals(param[6]) == false) { stbr.append(CommonSQL.GETQRYWITHPROPAREA); paramsMap.put("6", param[6]+"%"); } if (param[7]!=null && "".equals(param[7]) == false) { stbr.append(CommonSQL.GETQRYWITHPROPGOVBODY); paramsMap.put("7", param[7]+"%"); } if (param[8]!=null && "".equals(param[8]) == false) { stbr.append(CommonSQL.GETQRYWITHPROPWARD); paramsMap.put("8", param[8].split("~")[0]+"%"); } if (param[9]!=null && "".equals(param[9]) == false) { stbr.append(CommonSQL.GETQRYWITHPROPMOHALLA); paramsMap.put("9", param[9].split("~")[0]+"%");// split func required } if (param[10]!=null && "".equals(param[10]) == false) { stbr.append(CommonSQL.GETQRYWITHPROPKHASRA); paramsMap.put("10", "%"+param[10]+"%"); } if (param[11]!=null && "".equals(param[11]) == false) { stbr.append(CommonSQL.GETQRYWITHPARTYFIRSTNAME); paramsMap.put("11", "%"+param[11]+"%"); } if (param[12]!=null && "".equals(param[12]) == false) { stbr.append(CommonSQL.GETQRYWITHPARTYMIDDLENAME); paramsMap.put("12", "%"+param[12]+"%"); } if (param[13]!=null && "".equals(param[13]) == false) { stbr.append(CommonSQL.GETQRYWITHPARTYLASTNAME); paramsMap.put("13", "%"+param[13]+"%"); } if (param[14]!=null && "".equals(param[14]) == false) { stbr.append(CommonSQL.GETQRYWITHPARTYMOTHERNAME); paramsMap.put("14", "%"+param[14]+"%"); } if (param[15]!=null && "".equals(param[15]) == false) { stbr.append(CommonSQL.GETQRYWITHPARTYGUARDIANNAME); paramsMap.put("15", "%"+param[15]+"%"); } if (param[16]!=null && "".equals(param[16]) == false) { stbr.append(CommonSQL.GETQRYWITHPARTYORGNAME); paramsMap.put("16", "%"+param[16]+"%"); } if (param[17]!=null && "".equals(param[17]) == false) { stbr.append(CommonSQL.GETQRYWITHPARTYADDRESS); paramsMap.put("17", "%"+param[17]+"%"); } stbr.append(CommonSQL.ORDERREGNOASC); logger.info("Final build query : " + stbr.toString()); logger.info("Params mapping : " + paramsMap);
		 * 
		 * 
		 * 
		 * try { dbUtil = new DBUtility(); Collection<String> values = paramsMap.values(); ArrayList<String> tmp = new ArrayList(values); String[] params = tmp.toArray(new String[]{}); logger.info("query params : " + params); DocumentSearchDAO dao=new DocumentSearchDAO(); queryResult=dao.getRegDocDetails(stbr.toString(), params, dsdto); retVal.addAll(queryResult);
		 * 
		 * } catch (Exception e) { logger.error(e.getMessage(), e); }finally { try { dbUtil.closeConnection(); } catch (Exception e) { e.printStackTrace(); } } }
		 */
		return retVal;

	}
	// added by shruti---6 dec 2014
	public ArrayList searchUnRegisteredDoc(DocumentSearchDTO dsdto) {
		ArrayList list = new ArrayList();
		ArrayList queryResult;
		String language = "en";
		ArrayList retVal = new ArrayList();
		String rolegroup;
		String SQL = "";
		DBUtility dbUtil = null;

		String[] param = null;

		try {
			// dbUtil = new DBUtility();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (dsdto.getHdnDistName() != null && !dsdto.getHdnDistName().equals("")) {
			param = new String[18];
			// param[0]=dsdto.getHdnDistName();
			// param[0] = "JUNK".toUpperCase();
			param[0] = dsdto.getHdnRegDistId().split("~")[0];// registered district ID //use split("~")[0]
			param[1] = dsdto.getHdnTehsilName().toUpperCase();// registered tehsil name
			param[2] = dsdto.getFromDate();
			param[3] = dsdto.getToDate();
			param[4] = dsdto.getDistId();// prop district id
			param[5] = dsdto.getTehsilName();// prop tehsil name
			param[6] = dsdto.getAreaTypeId();// prop
			param[7] = dsdto.getSubAreaTypeId();
			param[8] = dsdto.getWarddName();
			param[9] = dsdto.getMohName();
			param[10] = dsdto.getKhasraNumber();
			param[11] = dsdto.getTransPartyFirstName().toUpperCase();
			param[12] = dsdto.getTransPartyMiddName().toUpperCase();
			param[13] = dsdto.getTransPartyLastName().toUpperCase();
			param[14] = "";// dsdto.getTransPartyMotName().toUpperCase();
			param[15] = "";// dsdto.getTransPartyFatName().toUpperCase();
			param[16] = dsdto.getOrgName().toUpperCase();
			param[17] = dsdto.getTransPartyAdd().toUpperCase();
		}

		StringBuilder stbr = new StringBuilder(CommonSQL.OLD_DOC_SEARCH_BASIC);
		TreeMap<Integer, String> paramsMap = new TreeMap<Integer, String>();
		paramsMap.put(0, param[0]);

		try {
			if ((param[2] != null && "".equals(param[2]) == false) && (param[3] != null && "".equals(param[3]) == false)) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_DATE);
				paramsMap.put(1, param[2]);
				paramsMap.put(2, param[3]);
			}
			if (param[4] != null && "".equals(param[4]) == false) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_PROP_DIST);
				paramsMap.put(3, param[4]);
			}
			if (param[5] != null && "".equals(param[5]) == false) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_PROP_TEH);
				paramsMap.put(4, "%" + param[5] + "%");
			}
			if (param[6] != null && "".equals(param[6]) == false) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_PROP_AREA);
				paramsMap.put(5, param[6]);
			}
			if (param[8] != null && "".equals(param[8]) == false) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_PROP_WARD);
				paramsMap.put(6, "%" + param[8].split("~")[0] + "%");
			}
			if (param[9] != null && "".equals(param[9]) == false) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_PROP_MOHALLA);
				paramsMap.put(7, "%" + param[9].split("~")[0] + "%");// split func required
			}
			if (param[10] != null && "".equals(param[10]) == false) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_PROP_KHASRA);
				paramsMap.put(8, "%" + param[10] + "%");
			}
			if (param[11] != null && "".equals(param[11]) == false) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_FIRST_NAME);
				paramsMap.put(9, "%" + param[11] + "%");
				paramsMap.put(10, "%" + param[11] + "%");
			}
			if (param[12] != null && "".equals(param[12]) == false) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_MIDDLE_NAME);
				paramsMap.put(11, "%" + param[12] + "%");
				paramsMap.put(12, "%" + param[12] + "%");
			}
			if (param[13] != null && "".equals(param[13]) == false) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_LAST_NAME);
				paramsMap.put(13, "%" + param[13] + "%");
				paramsMap.put(14, "%" + param[13] + "%");
			}
			if (param[14] != null && "".equals(param[14]) == false) {
				stbr.append(CommonSQL.GETQRYWITHPARTYMOTHERNAME);
				paramsMap.put(15, "%" + param[14] + "%");
			}
			if (param[15] != null && "".equals(param[15]) == false) {
				stbr.append(CommonSQL.GETQRYWITHPARTYGUARDIANNAME);
				paramsMap.put(16, "%" + param[15] + "%");
			}
			if (param[16] != null && "".equals(param[16]) == false) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_ORG_NAME);
				paramsMap.put(17, "%" + param[16] + "%");
				paramsMap.put(18, "%" + param[16] + "%");
			}
			if (param[17] != null && "".equals(param[17]) == false) {
				stbr.append(CommonSQL.OLD_DOC_SEARCH_PARTY_ADDRESS);
				paramsMap.put(19, "%" + param[17] + "%");
				paramsMap.put(20, "%" + param[17] + "%");
			}
			logger.info("Final build query : " + stbr.toString());
			logger.info("Params mapping : " + paramsMap);
			try {
				dbUtil = new DBUtility();
				Collection<String> values = paramsMap.values();
				ArrayList<String> tmp = new ArrayList(values);
				String[] params = tmp.toArray(new String[]{});
				logger.info("query params : " + params);
				DocumentSearchDAO dao = new DocumentSearchDAO();
				queryResult = dao.getUnRegDocDetails(stbr.toString(), params, dsdto);
				retVal.addAll(queryResult);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return retVal;
	}
	// end

	// added by shruti---29 nov 2014
	public ArrayList getRegDocDetails(String query, String[] params, DocumentSearchDTO dto) {
		ArrayList retVal = null;
		PreparedStatement prepStmt;
		ResultSet rset;
		Connection conn = null;
		DBUtility dbUtil = new DBUtility("");
		Blob doc;
		try {
			try {

				if (conn == null || conn.isClosed()) {
					conn = dbUtil.getDBConnection();
				}
				prepStmt = conn.prepareStatement(query);
				for (int iLoop = 0; iLoop < params.length; iLoop++) {
					prepStmt.setString((iLoop + 1), params[iLoop]);
				}
				rset = prepStmt.executeQuery();
				retVal = new ArrayList();
				while (rset.next()) {
					dto = new DocumentSearchDTO();
					dto.setRegistNumber(rset.getString(1));
					dto.setDateOfReg(rset.getString(2));
					retVal.add(dto);
				}
				rset.close();
				prepStmt.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				dbUtil.releaseConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	// end

	// added by shruti---6 dec 2014
	public ArrayList getUnRegDocDetails(String query, String[] params, DocumentSearchDTO dto) {
		ArrayList retVal = null;
		PreparedStatement prepStmt;
		ResultSet rset;
		Blob doc;
		DBUtility dbUtil = new DBUtility("");
		Connection conn = null;
		try {
			try {

				if (conn == null || conn.isClosed()) {
					conn = dbUtil.getDBConnection();
				}
				prepStmt = conn.prepareStatement(query);
				for (int iLoop = 0; iLoop < params.length; iLoop++) {
					prepStmt.setString((iLoop + 1), params[iLoop]);
				}
				rset = prepStmt.executeQuery();
				retVal = new ArrayList();
				while (rset.next()) {
					dto = new DocumentSearchDTO();
					dto.setRegistNumber(rset.getString(1) + "-" + rset.getString(2));
					dto.setDateOfReg(rset.getString(3));

					retVal.add(dto);
				}
				rset.close();
				prepStmt.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				dbUtil.releaseConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
	// end
	public String storeBTypeSearchNew(String paymentflag, DocumentSearchDTO dsto, DocumentSearchDTO dsdto, String userId, String functionId) throws Exception {

		String docTxnId = "";
		String TxnId = "";
		String payTxnId = "";
		String refID = dsdto.getParentReferenceId();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			boolean flg1 = false;
			String dataInsert = CommonSQL.DOC_SEARCH_BTXN_INSERT;
			String dataInsertNew = CommonSQL.DOC_SEARCH_BTXN_NEW_INSERT;
			String mainInsert = CommonSQL.DOC_SEARCH_DETAILS_INSERT;

			String payInsert = CommonSQL.DOC_SEARCH_PAYTXN_INSERT;
			String[] detArray = new String[24];
			String[] mainArray = new String[5];
			String[] payArray = new String[6];
			IGRSCommon common = new IGRSCommon();

			if (refID != null && !("").equalsIgnoreCase(refID))// for partial payment
				docTxnId = refID;
			else
				docTxnId = common.getSequenceNumber("DUMMY", "CERTIFIEDCOPY");
			TxnId = common.getTransactionNumber("DUMMY");
			payTxnId = common.getTransactionNumber("DUMMY");

			// Main Table Insert
			try {
				mainArray[0] = docTxnId;
				mainArray[1] = userId;
				mainArray[2] = null;
				mainArray[3] = userId;
				mainArray[4] = functionId;

				boolean flg = false;
				if (refID == null || ("").equalsIgnoreCase(refID))// for partial payment
				{
					dbUtil.createPreparedStatement(mainInsert);
					flg = dbUtil.executeUpdate(mainArray);
					logger.debug("status--<>flg1--" + flg + "-- flg1--" + flg1);
					if (!flg) {
						dbUtil.rollback();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Payment Table Insert
			try {
				payArray[0] = payTxnId;
				payArray[1] = docTxnId;
				payArray[2] = paymentflag;
				payArray[3] = functionId;
				payArray[4] = userId;
				payArray[5] = dsdto.getTotalFee();
				boolean flg = false;

				dbUtil.createPreparedStatement(payInsert);
				flg = dbUtil.executeUpdate(payArray);
				logger.debug("status--<>flg1--" + flg + "-- flg1--" + flg1);
				if (!flg) {
					dbUtil.rollback();
				}

			} catch (Exception e) {
				logger.debug(e);
			}

			if (docTxnId != null) {
				try {
					// logger.debug("<------------------------"+dsdto.getToDate1()+dsdto.getFromDate1());
					// logger.info(dsto.getToDate()+" new "+dsdto.getFromDate()+"reg"+dsdto.getRegDate()+dsdto.getRegDate());
					// logger.info(dsto.getToDate1()+" old "+dsto.getFromDate1());
					SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
					String regDate = "";
					if (dsdto.getRegDate() != null && !"".equals(dsdto.getRegDate())) {
						Date regDate1 = format1.parse(dsto.getRegDate());
						regDate = format2.format(regDate1);
					}

					// logger.info("flag is"+dsto.getRegFlag()+dsdto.getRegFlag());

					// if(dsto.getRegFlag().equalsIgnoreCase("new")){
					// logger.debug("in new flag<------------");
					String finaltodate = "";
					String finalfromdate = "";

					if ((dsto.getToDate() != null) && !("".equals(dsto.getToDate()))) {
						Date todate = format1.parse(dsto.getToDate());
						Date fromdate = format1.parse(dsto.getFromDate());
						logger.info(format2.format(todate));
						logger.info(format2.format(fromdate));
						if ((todate != null) && !(todate.equals("")) && (fromdate != null) && !(fromdate.equals(""))) {
							finaltodate = format2.format(todate);
							finalfromdate = format2.format(fromdate);

							logger.info("tehsil" + dsto.getTehisilName() + "pat" + dsto.getWardpatwarName() + "moh" + dsdto.getMohallaName());
							detArray[0] = TxnId;
							detArray[1] = docTxnId;
							if (dsdto.getDistId() != null && !"".equalsIgnoreCase(dsdto.getDistId())) {
								detArray[2] = dsdto.getDistId();
							} else {
								detArray[2] = dsto.getHdnRegDistId().split("~")[0];
							}
							if (dsdto.getTehisilId() != null && !"".equalsIgnoreCase(dsdto.getTehisilId())) {
								detArray[3] = dsdto.getTehisilId();
							} else {
								detArray[3] = "";
							}
							if (dsdto.getAreaTypeId() != null && !"".equalsIgnoreCase(dsdto.getAreaTypeId())) {
								detArray[4] = dsdto.getAreaTypeId();
							} else {
								detArray[4] = "";
							}
							if (dsdto.getSubAreaWardMappingId() != null && !"".equalsIgnoreCase(dsdto.getSubAreaWardMappingId())) {
								detArray[5] = dsdto.getSubAreaWardMappingId().split("~")[0];
							} else {
								detArray[5] = "";
							}
							if (dsdto.getWardpatwarId() != null && !"".equalsIgnoreCase(dsdto.getWardpatwarId())) {
								detArray[6] = dsdto.getWardpatwarId();
							} else {
								detArray[6] = "";
							}
							if (dsdto.getMohallaId() != null && !"".equalsIgnoreCase(dsdto.getMohallaId())) {
								detArray[7] = dsdto.getMohallaId().split("~")[0];
							} else {
								detArray[7] = "";
							}
							detArray[8] = finalfromdate;
							detArray[9] = finaltodate;
							detArray[10] = userId;
							detArray[11] = dsto.getTransPartyFirstName();
							detArray[12] = dsto.getTransPartyMiddName();
							detArray[13] = dsto.getTransPartyLastName();
							detArray[14] = dsto.getTransPartyGender();
							detArray[15] = dsto.getTransPartyMotName();
							detArray[16] = dsto.getTransPartyFatName();
							detArray[17] = dsto.getTransPartyAdd();
							detArray[18] = dsto.getPropertyAddr();
							detArray[19] = regDate;
							detArray[20] = dsto.getOrgName();
							// System.out.println("Hidden values are --"+ dsto.getHdnRegDistId()+"!!!!!!!"+dsdto.getHdnRegTehsilId());
							if (dsto.getHdnRegDistId() != null && !("").equalsIgnoreCase(dsto.getHdnRegDistId())) {
								detArray[21] = dsto.getHdnRegDistId().split("~")[0];
							} else {
								detArray[21] = "";
							}
							if (dsdto.getHdnRegTehsilId() != null && !("").equalsIgnoreCase(dsdto.getHdnRegTehsilId())) {
								detArray[22] = dsto.getHdnRegTehsilId().split("~")[0];
							} else {
								detArray[22] = "";
							}
							if ("o".equalsIgnoreCase(dsdto.getRadiobutton())) {
								detArray[23] = "OLD";
							} else {
								detArray[23] = "NEW";
							}
							dbUtil.createPreparedStatement(dataInsertNew);
							flg1 = dbUtil.executeUpdate(detArray);
						}
					}
					// }
					/*
					 * else if (dsto.getRegFlag().equalsIgnoreCase("old")) { logger.info("old flag"); String finaltodate1=""; String finalfromdate1=""; if(!(dsto.getToDate1().equals(""))||(dsto.getToDate1().equals(null))) { Date todate1 = format1.parse(dsto.getToDate1()); Date fromdate1 = format1.parse(dsto.getFromDate1());
					 * 
					 * logger.info(format2.format(todate1)); logger.info(format2.format(fromdate1)); finaltodate1=format2.format(todate1); finalfromdate1=format2.format(fromdate1); detArray[0]=TxnId; detArray[1]=docTxnId; detArray[2]=dsto.getDistId1(); detArray[3]=dsto.getTehsilName(); detArray[4]=dsto.getAreaName(); detArray[5]=dsto.getWarddName(); detArray[6]=dsto.getWardPatName(); detArray[7]=dsto.getMohName(); detArray[8]=finalfromdate1; detArray[9]=finaltodate1; detArray[10]=userId; detArray[11]=dsto.getTransPartyFirstName(); detArray[12]=dsto.getTransPartyMiddName(); detArray[13]=dsto.getTransPartyLastName(); detArray[14]=dsto.getTransPartyGender(); detArray[15]=dsto.getTransPartyMotName(); detArray[16]=dsto.getTransPartyFatName(); detArray[17]=dsto.getTransPartyAdd(); detArray[18]=dsto.getPropertyAddr(); detArray[19]=regDate; detArray[20]=dsto.getOrgName();
					 * 
					 * dbUtil.createPreparedStatement(dataInsert); flg1=dbUtil.executeUpdate(detArray);
					 * 
					 * //added by shruti dsto.setParentReferenceId(docTxnId); dsdto.setParentReferenceId(docTxnId); } }
					 */
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		logger.debug(docTxnId);
		// modified by shruti-9th sep 2013
		// return docTxnId;
		// modified by shruti 19th sep
		return payTxnId + "~" + docTxnId;
	}
	public ArrayList getPaymentList(String refID, String funID) throws Exception {
		logger.debug(" DAO getPaymentList--");
		String docTransId = null;
		ArrayList resultList = new ArrayList();
		String[] param = {refID, funID};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.CHECK_PAYMENT_DETAILS;
			logger.debug(" in dao try for getPaymentList --" + str);
			dbUtil.createPreparedStatement(str);
			resultList = dbUtil.executeQuery(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}

	public boolean updateSearchStatus(String refID, String funID, String regNO) {
		boolean transactionflag = false;
		boolean transactionflag1 = false;
		String detls[] = new String[2];
		DBUtility dbUtility = null;
		try {

			dbUtility = new DBUtility();
			detls[0] = regNO;
			detls[1] = refID;
			// detls[2]=funID;
			String sqlprop = CommonSQL.UPDATE_DOCSTATUS;
			dbUtility.createPreparedStatement(sqlprop);
			transactionflag = dbUtility.executeUpdate(detls);

		} catch (Exception e) {
			e.printStackTrace();

			logger.error(" Exception at insertPinToSms in DAO " + e);

		}

		finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception e) {
				logger.error("Exception at estamp in DAO  " + e);
			}
		}

		return transactionflag;
	}

	public boolean updateDownloadStatus(String refID) {
		boolean transactionflag = false;
		boolean transactionflag1 = false;
		String detls[] = new String[1];
		DBUtility dbUtility = null;
		try {

			dbUtility = new DBUtility();
			// detls[0]=regNO;
			detls[0] = refID;
			// detls[2]=funID;
			String sqlprop = CommonSQL.UPDATE_DOWNSTATUS;
			dbUtility.createPreparedStatement(sqlprop);
			transactionflag = dbUtility.executeUpdate(detls);
			if (transactionflag) {
				String downSQL = CommonSQL.DOWNLOAD_COMPLETED;
				dbUtility.createPreparedStatement(downSQL);
				transactionflag = dbUtility.executeUpdate(detls);
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(" Exception at updateDownloadStatus in DAO " + e);

		}

		finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception e) {
				logger.error("Exception at estamp in DAO  " + e);
			}
		}

		return transactionflag;
	}

	public String getDownloadStatus(String refID) throws Exception {

		String date = "";
		String[] param = {refID};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_DOWNSTATUS);
			date = dbUtil.executeQry(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		// logger.debug(regnum);
		// modified by shruti
		return date;
	}

	public String getSignatureCheck(String refID) throws Exception {

		String date = "";
		String[] param = {refID};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_SIGN_STATUS);
			date = dbUtil.executeQry(param);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		// logger.debug(regnum);
		// modified by shruti
		return date;
	}
	public boolean chkPreviousDownloadedExist(DocumentSearchDTO dsdto, String userId, String regNo) throws Exception {
		DBUtility dbUtil = null;
		boolean flag = false;

		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);

			String check = CommonSQL.CHECKDOWNLOADED;
			dbUtil.createPreparedStatement(check);
			String checkarr[] = new String[1];
			checkarr[0] = regNo;
			String count = dbUtil.executeQry(checkarr);
			if (Integer.parseInt(count) > 0) {
				flag = true;
				return flag;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeConnection();

		}
		return flag;
	}

	public boolean updateDownloadStatus(String refID, String docId) {
		System.out.println("refID : " + refID);
		System.out.println("docId : " + docId);
		boolean transactionflag = false;
		boolean transactionflag1 = false;
		String detls[] = new String[1];
		String detls1[] = new String[1];
		DBUtility dbUtility = null;
		try {

			dbUtility = new DBUtility();
			// detls[0]=regNO;
			detls[0] = docId;
			// detls[2]=funID;
			String sqlprop = CommonSQL.UPDATE_DOWNSTATUS;
			dbUtility.createPreparedStatement(sqlprop);
			transactionflag = dbUtility.executeUpdate(detls);
			if (transactionflag) {
				String downSQL = CommonSQL.DOWNLOAD_COMPLETED;
				dbUtility.createPreparedStatement(downSQL);
				transactionflag = dbUtility.executeUpdate(detls);
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(" Exception at updateDownloadStatus in DAO " + e);

		}

		finally {
			try {
				if (dbUtility != null) {
					dbUtility.closeConnection();
				}
			} catch (Exception e) {
				logger.error("Exception at estamp in DAO  " + e);
			}
		}

		return transactionflag;
	}
	public String getNullvoidFlag(String refID) throws Exception {
		logger.debug(" DAO checkEstampTxnId--");
		String nullVoidFlag = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {refID};
			String str = CommonSQL.CHECK_NULL_VOID_FLAG;
			dbUtil.createPreparedStatement(str);
			nullVoidFlag = dbUtil.executeQry(param);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return nullVoidFlag;
	}

	public String getProtestFlag(String refID) throws Exception {
		logger.debug(" DAO getrotestFlag--");
		String protestFlag = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {refID};
			String str = CommonSQL.CHECK_PROTEST_FLAG;
			dbUtil.createPreparedStatement(str);
			protestFlag = dbUtil.executeQry(param);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return protestFlag;
	}
	public ArrayList getNullVoidDetls(String refID) throws Exception {
		logger.debug(" DAO getNullVoidDetls--");
		String docTransId = null;
		ArrayList resultList = new ArrayList();
		String[] param = new String[2];
		param[0] = refID;
		param[1] = refID;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.NULLVOIDRECORD;
			logger.debug(" in dao try for getNullVoidDetls --" + str);
			dbUtil.createPreparedStatement(str);
			resultList = dbUtil.executeQuery(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}

	public ArrayList getProtestId(String refID) throws Exception {
		logger.debug(" DAO getProtestDetls--");
		String docTransId = null;
		ArrayList resultList = new ArrayList();
		String[] param = new String[2];
		param[0] = refID;
		param[1] = refID;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.GETPROTESTID;
			logger.debug(" in dao try for getNullVoidDetls --" + str);
			dbUtil.createPreparedStatement(str);
			resultList = dbUtil.executeQuery(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}

	public ArrayList getBankChargeId(String refID) throws Exception {
		logger.debug(" DAO getBankIdDetls--");
		String docTransId = null;
		ArrayList resultList = new ArrayList();
		String[] param = {refID};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.BankChargeId;
			logger.debug(" in dao try for bankChargeId --" + str);
			dbUtil.createPreparedStatement(str);
			resultList = dbUtil.executeQuery(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}

	// Code written for check RegistrationId with protest

	public ArrayList checkRegistrationIdwithProtest(String _refRegId, String language) throws Exception {
		logger.debug(" DAO checkRegistrationId--");
		String refRegTransId = null;
		ArrayList resultList = new ArrayList();
		DBUtility dbUtil = null;
		String propId = "";
		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.DOC_SEARCH_SELECT_REGID + "'" + _refRegId + "'";
			dbUtil.createStatement();
			refRegTransId = dbUtil.executeQry(str);
			if (refRegTransId != null && !"".equalsIgnoreCase(refRegTransId)) {
				String refEstampId = checkEstampTxnId(refRegTransId);
				ArrayList partyList = getPartyDetails(refRegTransId, language);
				ArrayList propertyList = getPropertyDetails(refRegTransId, language);
				ArrayList caveatlist = getCaveatDeatils(_refRegId, language);
				ArrayList complainceList = getPropertyComplainceDetails(refRegTransId);
				resultList.add(refRegTransId);
				resultList.add(refEstampId);
				resultList.add(partyList);
				resultList.add(propertyList);
				resultList.add(caveatlist);
				resultList.add(complainceList);
				logger.debug(" --in dao-- reg id--" + ((String) resultList.get(0)));
				logger.debug(" --in dao-- estamp id--" + (String) resultList.get(1));
				logger.debug(" --in dao-- party list--" + resultList.get(2));
				logger.debug(" --in dao-- property list--" + resultList.get(3));
				logger.debug(" --in dao-- subclause list--" + resultList.get(4));
				logger.debug(" --in dao-- complaince list--" + resultList.get(5));
				/*
				 * ArrayList tmpList = (ArrayList) resultList.get(3); if (tmpList != null && tmpList.size() > 0) { logger.debug("--size--<>" + tmpList.size()); for (int j = 0; j < tmpList.size(); j++) { logger.debug("tmp list  for--<>" + j); ArrayList arl = (ArrayList) tmpList.get(j); if (arl != null && arl.size() > 0) { propId=(String)arl.get(1); } } }
				 */
				// ArrayList protestDetls=protestDetls(propId,language);
				// resultList.add(protestDetls);
			} else {
				resultList.add("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList protestDetls(String propId, String language) throws Exception {
		logger.debug(" DAO getPartyDetails--");
		ArrayList protestList = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String[] param = {propId};
			String str = "";
			if ("en".equalsIgnoreCase(language)) {
				str = CommonSQL.GETPROTESTDTLS;
			} else {
				str = CommonSQL.GETPROTESTDTLS_H;
			}
			dbUtil.createPreparedStatement(str);
			protestList = dbUtil.executeQuery(param);

		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return protestList;
	}

	public synchronized ArrayList getFinancialYear(String language) throws Exception {
		DBUtility dbUtil;
		ArrayList list;
		dbUtil = null;
		list = new ArrayList();
		dbUtil = new DBUtility();
		try {
			logger.debug("Before initialize DBUtility");
			String sql = "SELECT fiscal_year_id,FISCAL_YEAR FROM IGRS_FISCAL_YEAR WHERE GUIDELINE_YEAR_STATUS='A' ORDER BY FISCAL_YEAR";
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return list;
	}

	public synchronized ArrayList getFiscalYearList(String language, String type) throws Exception {
		DBUtility dbUtil;
		ArrayList list;
		dbUtil = null;
		list = new ArrayList();
		dbUtil = new DBUtility();
		try {
			logger.debug("Before initialize DBUtility");
			String sql = "SELECT fiscal_year_id,FISCAL_YEAR FROM IGRS_FISCAL_YEAR WHERE GUIDELINE_YEAR_STATUS='A' and OLDORNEW='" + type + "' ORDER BY FISCAL_YEAR";
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return list;
	}
	// added by saurav for server side validation
	public boolean getValidregNo(String regNumber) throws Exception {
		boolean res = false;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String sql = CommonSQL.GET_DOC_COUNT;
			String param[] = {regNumber};
			dbUtil.createPreparedStatement(sql);
			String resp = dbUtil.executeQry(param);
			if (null != resp) {
				if (!("0").equals(resp)) {
					res = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return res;
	}

	public ArrayList checkOldDocPropData(String refID, String language) throws Exception {
		logger.debug(" DAO getBankIdDetls--");
		String docTransId = null;
		ArrayList resultList = new ArrayList();
		String[] param = {refID.split("-")[0], refID.split("-")[1]};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.OLD_DOC_SEARCH_PROP_DATA;
			logger.debug(" in dao try for bankChargeId --" + str);
			dbUtil.createPreparedStatement(str);
			resultList = dbUtil.executeQuery(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}
	public ArrayList checkOldDocPartyData(String refID, String language) throws Exception {
		logger.debug(" DAO getBankIdDetls--");
		String docTransId = null;
		ArrayList resultList = new ArrayList();
		String[] param = {refID.split("-")[0], refID.split("-")[1]};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.OLD_DOC_SEARCH_PARTY_DATA;
			logger.debug(" in dao try for bankChargeId --" + str);
			dbUtil.createPreparedStatement(str);
			resultList = dbUtil.executeQuery(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}

	public String checkSearchType(String refId) throws Exception {
		String result = "";
		logger.debug(" checkSearchType -- old doc search type--");
		ArrayList resultList = new ArrayList();
		String[] param = {refId};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.DOC_SEARCH_TYPE;
			logger.debug(" in dao try for getPaymentParameters --" + str);
			dbUtil.createPreparedStatement(str);
			result = dbUtil.executeQry(param);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return result;
	}
	public String getlinkStatus() throws Exception {
		String result = "";
		logger.debug(" checkSearchType -- old doc search type--");
		ArrayList resultList = new ArrayList();
		// String[] param = {refId};
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.DOC_SEARCH_HYPER_LINK;
			logger.debug(" in dao try for getPaymentParameters --" + str);
			dbUtil.createStatement();
			result = dbUtil.executeQry(str);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}

		return result;
	}
	// added by saurav for document search changes
	/*
	 * public ArrayList<String> getPartyAllData(String registrationNumber, String partyType) throws Exception{ ArrayList<String> partyDetail = new ArrayList<String>(); DBUtility dbUtil = null; try{ ArrayList list = new ArrayList(); dbUtil = new DBUtility(); String sql = CommonSQL.GET_PARTY_DETAIL; dbUtil.createPreparedStatement(sql); String[] param={registrationNumber}; list = dbUtil.executeQuery(param); list = (ArrayList) list.get(0); for(int i = 0; i<list.size(); i++){ partyDetail.add((String) list.get(i)); } }catch (Exception e) { e.printStackTrace(); } finally { if (dbUtil != null) { dbUtil.closeConnection(); } dbUtil = null; } return partyDetail; }
	 */
	public ArrayList getTehsil(String distId, String lang) throws Exception {
		ArrayList result = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			result = new ArrayList();
			String str = CommonSQL.DOC_SEARCH_TEHSIL;
			logger.debug(" in dao try for get tehsil method of Document Search --" + str);
			dbUtil.createPreparedStatement(str);
			String[] arr = {distId};
			result = dbUtil.executeQuery(arr);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return result;
	}
	public ArrayList getTehsilGDL(String distId, String lang, String fisYear) throws Exception {
		ArrayList result = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			result = new ArrayList();
			String str = CommonSQL.DOC_SEARCH_TEHSIL_COMMOM+" IGRS_GUIDElINE_COL_"+fisYear+CommonSQL.TEHSIL_REMAINING;
			logger.debug(" in dao try for get tehsil method of Document Search --" + str);
			dbUtil.createPreparedStatement(str);
			String[] arr = {distId,fisYear};
			result = dbUtil.executeQuery(arr);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return result;
	}
	public ArrayList getSubArea(String language, String areaId, String tehsilId, String urbanFlag, String fisYear) throws Exception {

		ArrayList subAreaList = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			
			if ("Y".equalsIgnoreCase(urbanFlag)) {
				String sql = CommonSQL.DOC_SEARCH_SUB_AREA+ "IGRS_GUIDElINE_COL_"+fisYear+CommonSQL.SUB_AREA_URBAN;
				dbUtil.createPreparedStatement(sql);
				subAreaList = dbUtil.executeQuery(new String[]{"2", tehsilId, fisYear});
			} else {
				String sql = CommonSQL.DOC_SEARCH_SUB_AREA+ "IGRS_GUIDElINE_COL_"+fisYear+CommonSQL.SUB_AREA_RURAL;
				dbUtil.createPreparedStatement(sql);
				subAreaList = dbUtil.executeQuery(new String[]{ fisYear});
			}

			return subAreaList;

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}

	}

	public ArrayList getWardPatwari(String language, String subAreaId, String tehsilId, String fisYear) {
		ArrayList wardPatwariList = null;
		DBUtility dbUtil = null;

		try {
			dbUtil = new DBUtility();
			String sql = CommonSQL.DOC_SEARCH_WARD_COMMON+" IGRS_GUIDElINE_COL_"+fisYear+CommonSQL.WARD_REMAINING;
			dbUtil.createPreparedStatement(sql);

			wardPatwariList = dbUtil.executeQuery(new String[]{fisYear, tehsilId,subAreaId});

		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
		return wardPatwariList;
	}
	public ArrayList getColonyName(String language, String wardPatwariId, String fisYear) {
		ArrayList l2NameList = null;
		DBUtility dbUtil = null;
		
		try {
			dbUtil = new DBUtility();
			String sql = CommonSQL.DOC_SEARCH_COLONY_COMMON+" IGRS_GUIDElINE_COL_"+fisYear+CommonSQL.COLONY_REMAINING;
			dbUtil.createPreparedStatement(sql);
			
			l2NameList = dbUtil.executeQuery(new String[]{fisYear, wardPatwariId});
			return l2NameList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				logger.error(e);

			}

		}
	}
	
	public ArrayList getRegistrationDistrict(String fiscalYear){
		ArrayList returnList=null;
		DBUtility dbUtil = null;
		try{
			String sql = CommonSQL.DISTRICT_NAME_FY_WISE;
			String arr[] = {fiscalYear};
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			returnList = new ArrayList();
			returnList = dbUtil.executeQuery(arr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnList;
	}
	
	public synchronized ArrayList getRegistrationTehsil(String distId,String language, String fiscalYear) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("getTehsil(String) - start");
		}
		String SQL = "";
		if ("en".equalsIgnoreCase(language)) {
			SQL = CommonSQL.REG_TEHSIL_QUERY;
		} else {
			SQL = CommonSQL.REG_TEHSIL_QUERY_H;
		}
		ArrayList list = new ArrayList();
		String arry[] = new String[2];
		if (distId != null) {
			arry[0] = distId;
			arry[1] = fiscalYear;
		}
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			System.out.println("Wipro in IGRSCommon - "
					+ "getRegistrationTehsil after PreparedStatement");
			list = dbUtil.executeQuery(arry);
			System.out.println("Wipro in IGRSCommon - "
					+ "getRegistrationTehsil after executeQuery");
		} catch (Exception x) {
			System.out.println(x);
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		}

		return list;
	}
	
	public ArrayList checkRegistrationIdOffice(String _refRegId, String language) throws Exception {
		logger.debug(" DAO checkRegistrationId--");
		String refRegTransId = null;
		ArrayList resultList = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			String str = CommonSQL.DOC_SEARCH_SELECT_REGID_OFFICE + "'" + _refRegId + "'";
			dbUtil.createStatement();
			refRegTransId = dbUtil.executeQry(str);
			if (refRegTransId != null && !"".equalsIgnoreCase(refRegTransId)) {
				String refEstampId = checkEstampTxnId(refRegTransId);
				ArrayList partyList = getPartyDetails(refRegTransId, language);
				ArrayList propertyList = getPropertyDetails(refRegTransId, language);
				ArrayList bankChargesList = getBankChargeId(refRegTransId);
				ArrayList caveatlist = getCaveatDeatils(_refRegId, language);
				ArrayList complainceList = getPropertyComplainceDetails(refRegTransId);
				resultList.add(refRegTransId);
				resultList.add(refEstampId);
				resultList.add(partyList);
				resultList.add(propertyList);
				resultList.add(bankChargesList);
				resultList.add(caveatlist);
				resultList.add(complainceList);
				logger.debug(" --in dao-- reg id--" + ((String) resultList.get(0)));
				logger.debug(" --in dao-- estamp id--" + (String) resultList.get(1));
				logger.debug(" --in dao-- party list--" + resultList.get(2));
				logger.debug(" --in dao-- property list--" + resultList.get(3));
				logger.debug(" --in dao-- subclause list--" + resultList.get(4));
				logger.debug(" --in dao-- complaince list--" + resultList.get(5));
				ArrayList tmpList = (ArrayList) resultList.get(3);
				if (tmpList != null && tmpList.size() > 0) {
					logger.debug("--size--<>" + tmpList.size());
					for (int j = 0; j < tmpList.size(); j++) {
						logger.debug("tmp list  for--<>" + j);
						ArrayList arl = (ArrayList) tmpList.get(j);
						if (arl != null && arl.size() > 0) {
							String propId = (String) arl.get(0);
							String newpropId = (String) arl.get(1);
						}
					}
				}
			} else {
				resultList.add("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
			dbUtil = null;
		}
		return resultList;
	}
	public String getFiscalYearName(String fiscalYearId) throws Exception{
		String retval="";

		DBUtility dbUtil = null;
		String sql = CommonSQL.GET_FISCAL_YEAR_NAME;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sql);
			String arr[] = {fiscalYearId};
			retval = dbUtil.executeQry(arr);
		}catch(Exception ex){
			if(fiscalYearId==null){
				logger.debug("Fiscal year not selected");
			}else{
				logger.debug("Some unexpected error occured while fetching fiscal year name");
			}
			ex.printStackTrace();
		}
		
		return retval;
	}
}
