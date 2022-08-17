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
package com.wipro.igrs.documentsearch.bo;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wipro.igrs.DeliveryOfDocuments.dao.DeliveryOfDocumentsDAO;
import com.wipro.igrs.documentsearch.dao.DocumentSearchDAO;
import com.wipro.igrs.documentsearch.dto.DocumentSearchDTO;

public class DocumentSearchBO {
	DocumentSearchDAO dsdao;
	Logger logger = (Logger) Logger.getLogger(DocumentSearchBO.class);
	/**
	 * @throws Exception
	 */
	public DocumentSearchBO() throws Exception {
		logger.debug("WE ARE IN DS BD");
		dsdao = new DocumentSearchDAO();
	}
	/**
	 * @param _refRegId
	 * @return
	 * @throws Exception
	 */
	public ArrayList checkRegistrationId(String _refRegId, String language) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.checkRegistrationId(_refRegId, language);
			// logger.info("in bo checkRegistrationId-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}

	public ArrayList checkRegistrationIdwithProtest(String _refRegId, String language) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.checkRegistrationIdwithProtest(_refRegId, language);
			// logger.info("in bo checkRegistrationId-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}
	// added by shruti--14 feb 2014
	public ArrayList checkOldRegistrationId(DocumentSearchDTO dsdto, String language) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.checkOldRegistrationId(dsdto, language);
			// logger.info("in bo checkRegistrationId-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}
	// end
	public ArrayList getKhasraDetail(String _propId) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.getKhasraDetail(_propId);
			// logger.info("in bo checkRegistrationId-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}

	public ArrayList protestDetls(String propId, String language) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.protestDetls(propId, language);
			// logger.info("in bo checkRegistrationId-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}

	/**
	 * @param _refRegId
	 * @return
	 * @throws Exception
	 */
	public ArrayList verifyRegistrationId(String _refRegId) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.verifyRegistrationId(_refRegId);
			// logger.info("in bo checkRegistrationId-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}
	/**
	 * @param _refTranId
	 * @return
	 * @throws Exception
	 */
	public String checkEstampTxnId(String _refTranId) throws Exception {
		String estampId = null;

		try {
			estampId = dsdao.checkEstampTxnId(_refTranId);
			// logger.info("checkEstampTxnId-->"+estampId);
		} catch (Exception e) {
			logger.error(e);

		}
		return estampId;
	}

	/**
	 * @param _refTranId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPartyDetails(String _refTranId, String language) throws Exception {
		ArrayList partyList = null;

		try {
			partyList = dsdao.getPartyDetails(_refTranId, language);
			// logger.info("getPartyDetails-->"+partyList);
		} catch (Exception e) {
			logger.error(e);

		}

		return partyList;
	}

	public String storeBTypeSearch(String paymentflag, DocumentSearchDTO dsto, DocumentSearchDTO dsdto, String userId, String functionId) throws Exception {
		String doctxnid = null;

		try {

			doctxnid = dsdao.storeBTypeSearch(paymentflag, dsto, dsdto, userId, functionId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}
	/*
	 * //by Shreeraj public String storeBDocSearch(String tehsil,String areaType,String wardPatwariName,String wardName,String mohlaName)throws Exception{ String doctxnid=null;
	 * 
	 * try{
	 * 
	 * doctxnid=dsdao.storeBDocSearch(tehsil,areaType,wardPatwariName,wardName,mohlaName);
	 * 
	 * }catch(Exception e){ e.printStackTrace(); } return doctxnid; }
	 */
	public String storeCTypeSearch(String paymentflag, DocumentSearchDTO dsto, String userId, String functionId) throws Exception {
		String doctxnid = null;

		try {

			doctxnid = dsdao.storeCTypeSearch(paymentflag, dsto, userId, functionId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}

	public String storePaymetTxnDetails(String paymentflag, DocumentSearchDTO dsdto, String userId, String functionId, String refID) throws Exception {
		String txnid = null;

		try {

			txnid = dsdao.storePaymetTxnDetails(paymentflag, dsdto, userId, functionId, refID);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return txnid;
	}
	public String updatePaymetTxnDetails(String paymentflag, String txnid, String regnum, String userId, String functionId) throws Exception {
		String doctxnid = null;

		try {

			doctxnid = dsdao.updatePaymetTxnDetails(paymentflag, txnid, regnum, userId, functionId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}
	public String updateOfficialTxnDetails(String txnid, String regnum) throws Exception {
		String doctxnid = null;

		try {

			doctxnid = dsdao.updateOfficialTxnDetails(txnid, regnum);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}

	public String storeOfficeTxnDetails(String searchPurpose, String regnum, String userId, String functionId) throws Exception {
		String doctxnid = null;

		try {

			doctxnid = dsdao.storeOfficeTxnDetails(searchPurpose, regnum, userId, functionId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}

	// added by shruti---25 aug 2014

	public boolean updateOfficeTxnDetails(String docName, String docPath, String docTxnId) throws Exception {

		boolean flag = false;
		try {
			flag = dsdao.updateOfficeTxnDetails(docName, docPath, docTxnId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	// end

	public String downloadPaymetTxnDetails(String paymentflag, String doctxnid, String userId, String functionId, String fee) throws Exception {
		String txnid = "";

		try {

			txnid = dsdao.downloadPaymetTxnDetails(paymentflag, doctxnid, userId, functionId, fee);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return txnid;
	}

	/**
	 * @param _refTranId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyDetails(String _refTranId, String language) throws Exception {
		ArrayList propertyList = null;

		try {
			propertyList = dsdao.getPropertyDetails(_refTranId, language);
			// logger.info("getPartyDetails-->"+propertyList);
		} catch (Exception e) {
			logger.error(e);

		}
		return propertyList;
	}
	// adedd bys hruti-13th aug
	public ArrayList getSrchAResultList(String userId) throws Exception {
		ArrayList partyList = null;

		try {
			partyList = dsdao.getSrchAresultList(userId);
			// logger.info("getSrchAResultList()-->"+partyList);
		} catch (Exception e) {
			logger.error(e);

		}

		return partyList;
	}
	public ArrayList getSrchBResultList(String userId) throws Exception {
		ArrayList partyList = null;

		try {
			partyList = dsdao.getSrchBresultList(userId);
			// logger.info("getSrchAResultList()-->"+partyList);
		} catch (Exception e) {
			logger.error(e);

		}

		return partyList;
	}
	// added by shruti 16th aug 2013
	public ArrayList getSrchLogList(String toDate) throws Exception {
		ArrayList logList = null;

		try {
			logList = dsdao.getSrchLogList(toDate);
			// logger.info("getSrchAResultList()-->"+logList);
		} catch (Exception e) {
			logger.error(e);

		}

		return logList;
	}
	public ArrayList getDownloadLogList(String toDate) throws Exception {
		ArrayList logList = null;

		try {
			logList = dsdao.getDownloadLogList(toDate);
			// logger.info("getSrchAResultList()-->"+logList);
		} catch (Exception e) {
			logger.error(e);

		}

		return logList;
	}
	public ArrayList getuserDetailsList(String userid) throws Exception {
		ArrayList detailsList = null;

		try {
			detailsList = dsdao.getUserDetailsList(userid);
			// logger.info("getSrchAResultList()-->"+detailsList);
		} catch (Exception e) {
			logger.error(e);

		}

		return detailsList;
	}

	public ArrayList checkRegistrationIdNew(String _refRegId, String flag, String language) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.checkRegistrationIdNew(_refRegId, flag, language);
			// logger.info("in bo checkRegistrationId-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}

	public ArrayList checkRegistrationIdNewWithProtest(String _refRegId, String flag, String language) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.checkRegistrationIdNewWithProtest(_refRegId, flag, language);
			// logger.info("in bo checkRegistrationId-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}
	// adedd by shruti
	public ArrayList checkResumeState(String docTxnId) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.checkResumeState(docTxnId);
			// logger.info("in bo checkResumeState-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}

	public ArrayList getTypeBSearchRecordDetails(String docTxnId) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.getTypeBSearchRecordDetails(docTxnId);
			// logger.info("in bo checkResumeState-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}
		return resultList;
	}
	// added by shruti
	public String chkUser(String userId) throws Exception {
		ArrayList resultList = new ArrayList();

		String typeid = "";
		try {

			typeid = dsdao.chkUser(userId);
			// resultList=dsdao.chkUser(userId);
			// resultList.trimToSize();
			// logger.info("in bo getPaymentParameters-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return typeid;
	}
	public ArrayList getExternalUserDtls(String userId) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.getExternalUserDtls(userId);
			// logger.info("in bo getExternalUserDtls-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}
	public ArrayList getRUUserDtls(String userId) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.getRUUserDtls(userId);
			// logger.info("in bo getExternalUserDtls-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}

	public ArrayList getInternalUserDtls(String officeId) throws Exception {
		ArrayList resultList = new ArrayList();

		try {

			resultList = dsdao.getInternalUserDtls(officeId);
			// logger.info("in bo getInternalUserDtls-->"+resultList);

		} catch (Exception e) {
			logger.error(e);

		}

		return resultList;
	}
	// added by shruti 23rd sep 2013
	public String storeSearchATxnDetails(DocumentSearchDTO dsdto, String userId, String functionId) throws Exception {
		String txnid = null;

		try {

			txnid = dsdao.storeSearchATxnDetails(dsdto, userId, functionId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return txnid;
	}
	public boolean chkPreviousDownloadedExist(DocumentSearchDTO dsdto, String userId, String regNo) throws Exception {

		boolean flag = false;
		try {

			flag = dsdao.chkPreviousDownloadedExist(dsdto, userId, regNo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// added by shruti---26 nov 2014
	public String searchGoLiveDate(String distId) throws Exception {
		String date = null;

		try {
			date = dsdao.searchGoLiveDate(distId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public ArrayList searchRegisteredDoc(DocumentSearchDTO dsdto, String language) {
		return dsdao.searchRegisteredDoc(dsdto, language);

	}
	public ArrayList searchUnRegisteredDoc(DocumentSearchDTO dsdto) {
		return dsdao.searchUnRegisteredDoc(dsdto);

	}
	public String storeBTypeSearchNew(String paymentflag, DocumentSearchDTO dsto, DocumentSearchDTO dsdto, String userId, String functionId) throws Exception {
		String doctxnid = null;

		try {

			doctxnid = dsdao.storeBTypeSearchNew(paymentflag, dsto, dsdto, userId, functionId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctxnid;
	}
	public ArrayList getPaymentList(String refID, String funID) throws Exception {
		return dsdao.getPaymentList(refID, funID);

	}
	public boolean updateSearchStatus(String refID, String funID, String status) throws Exception {
		boolean flg = false;
		flg = dsdao.updateSearchStatus(refID, funID, status);
		return flg;
	}
	public boolean updateDownloadStatus(String refID) throws Exception {
		boolean flg = false;
		flg = dsdao.updateDownloadStatus(refID);
		return flg;
	}
	public boolean updateDownloadStatus(String refID, String docId) throws Exception {
		boolean flg = false;
		flg = dsdao.updateDownloadStatus(refID, docId);
		return flg;
	}
	public String getDownloadStatus(String refID) throws Exception {
		String date = null;

		try {
			date = dsdao.getDownloadStatus(refID);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	public boolean getSignatureCheck(String refID) throws Exception {
		boolean flg = false;
		String result = dsdao.getSignatureCheck(refID);
		if (result == null || "".equalsIgnoreCase(result))
			flg = false;
		else
			flg = true;
		return flg;
	}
	public String getNullvoidFlag(String refID) throws Exception {
		String nullVoidFlag = null;

		try {
			nullVoidFlag = dsdao.getNullvoidFlag(refID);
			// logger.info("checkEstampTxnId-->"+estampId);
		} catch (Exception e) {
			logger.error(e);

		}
		return nullVoidFlag;
	}
	public String getProtestFlag(String refID) throws Exception {
		String protestFlag = null;

		try {
			protestFlag = dsdao.getProtestFlag(refID);
			// logger.info("checkEstampTxnId-->"+estampId);
		} catch (Exception e) {
			logger.error(e);

		}
		return protestFlag;
	}

	public ArrayList getNullVoidDetls(String refID) throws Exception {
		return dsdao.getNullVoidDetls(refID);

	}

	public ArrayList getProtestId(String refID) throws Exception {
		return dsdao.getProtestId(refID);

	}

	public ArrayList getBankChargeId(String refID) throws Exception {
		return dsdao.getBankChargeId(refID);

	}
	public boolean getValidregNo(String regNumber) throws Exception {
		boolean response = false;
		try {
			response = dsdao.getValidregNo(regNumber);
			// logger.info("checkEstampTxnId-->"+estampId);
		} catch (Exception e) {
			logger.error(e);

		}
		return response;
	}
	
	public ArrayList<String> checkOldDocPropData(String _refRegId, String language) throws Exception {
		ArrayList retList = new ArrayList();
		try {
			ArrayList list = new ArrayList();
			list = dsdao.checkOldDocPropData(_refRegId,language);
			retList.add(list);
		} catch (Exception e) {
			logger.error("checkOldDocData error ---->" + e);
		}
		return retList;
	}
	public ArrayList<String> checkOldDocPartyData(String _refRegId, String language) throws Exception {
		ArrayList retList = new ArrayList();
		try {
			ArrayList list = new ArrayList();
			retList = dsdao.checkOldDocPartyData(_refRegId,language);
			
		} catch (Exception e) {
			logger.error("checkOldDocPartyData error ---->" + e);
		}
		return retList;
	}
	public String checkSearchType(String refId){
		String result="";
		try {
			result= dsdao.checkSearchType(refId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/*public Map getPartyAllData(String regNumber) throws Exception {
		return dsdao.getPartyAllData(regNumber, new ArrayList());
	}*/
	
	
}
