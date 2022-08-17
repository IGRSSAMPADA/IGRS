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

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.auditinspection.dao.SROReportDetailsDAO;
import com.wipro.igrs.caseMonitoring.bo.CaseMonBO;
import com.wipro.igrs.caseMonitoring.dao.CaseMonitoringDAO;
import com.wipro.igrs.caseMonitoring.dto.CaseMonDTO;
import com.wipro.igrs.caseMonitoring.sql.CaseMonSQL;
import com.wipro.igrs.common.IGRSCommon;

/*******************************************************************************
 * 
 * File : CaseMonBD.java Description : Represent the CaseMonitoring BD Class
 * Author : Karteek P Created Date : June 23, 2008
 ******************************************************************************/
public class CaseMonBD {

	private Logger logger = (Logger) Logger.getLogger(CaseMonBD.class);

	public CaseMonBD() {
	}

	/***************************************************************************
	 * Method : GetCaseIds Arguments : From Date and ToDate Return : ArrayList
	 * Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseIds(String _fromDate, String _toDate,
			String _caseType, String _status, String _caseActionType)
			throws NullPointerException, Exception {
		ArrayList caseList = null;
		CaseMonBO caseBo = new CaseMonBO();
		try {
			caseList = new ArrayList();
			caseList = caseBo.getCaseIds(_fromDate, _toDate, _caseType,
					_status, _caseActionType);
		} catch (NullPointerException ne) {
			logger.error("NullPointer Exception at getCaseIDs in CaseMonBD : "
					+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseids in CaseMonBD  :  " + e);
		}
		return caseList;
	}

	/***************************************************************************
	 * Method : GetCaseViewIds Arguments : From Date and ToDate,CaseType Return :
	 * ArrayList Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseViewIds(String _fromDate, String _toDate,
			String _caseType) throws NullPointerException, Exception {
		ArrayList caseList = null;
		CaseMonBO caseBo = new CaseMonBO();
		try {
			caseList = new ArrayList();
			caseList = caseBo.getCaseViewIds(_fromDate, _toDate, _caseType);
		} catch (NullPointerException ne) {
			logger
					.error("NullPointer Exception at getCaseViewIds in CaseMonBD : "
							+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseViewIds in CaseMonBD  :  " + e);
		}
		return caseList;
	}

	/***************************************************************************
	 * Method : GetCaseId Details Arguments : Case ID Return : ArrayList
	 * Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseIdDetails(String _caseId, String _caseActionType)
			throws Exception {
		ArrayList caseList = null;
		CaseMonBO caseBo = new CaseMonBO();
		try {
			caseList = new ArrayList();
			caseList = caseBo.getCaseIdDetails(_caseId, _caseActionType);
		} catch (NullPointerException ne) {
			logger
					.error("NullPointer Exception at getCaseIdDetails in CaseMonBD : "
							+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIdDetails in CaseMonBD  :  " + e);
		}
		return caseList;
	}

	/***************************************************************************
	 * Method : GetCase Notice Details Arguments : Case ID Return : ArrayList
	 * Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseNoticeDetails(String _caseId, String _caseActionType)
			throws Exception {
		ArrayList caseList = null;
		CaseMonBO caseBo = new CaseMonBO();
		try {
			caseList = new ArrayList();
			caseList = caseBo.getCaseNoticeDetails(_caseId, _caseActionType);
		} catch (NullPointerException ne) {
			logger
					.error("NullPointer Exception at getCaseIdDetails in CaseMonBD : "
							+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIdDetails in CaseMonBD  :  " + e);
		}
		return caseList;
	}

	/***************************************************************************
	 * Method : Set Case Details Arguments : CaseList Return : String Exception :
	 * NullPointerException,Exception
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 **************************************************************************/
	public synchronized String setCaseDetails(CaseMonDTO _caseDto, String roleId, String funId, String userId)
			throws NullPointerException, SQLException, Exception {
		String caseID = null;
		CaseMonBO caseBo = new CaseMonBO();
		try {
			caseID = caseBo.setCaseDetails(_caseDto,roleId,funId,userId);
		} catch (NullPointerException ne) {
			logger
					.error("NullPointer Exception at getCaseIdDetails in CaseMonBD : "
							+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIdDetails in CaseMonBD  :  " + e);
		}
		return caseID;
	}

	/***************************************************************************
	 * Method : Update Case Details Arguments : DTO object and Case Action
	 * Return : String Exception : NullPointerException,Exception
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 **************************************************************************/
	public String updateCaseDetails(CaseMonDTO _caseDto, String _caseAction, String roleId, String funId, String userId)
			throws Exception {
		String caseId = null;
		CaseMonBO caseBo = new CaseMonBO();
		try {
			caseId = caseBo.updateCaseDetails(_caseDto, _caseAction,roleId,funId,userId);
			return caseId;
		} catch (NullPointerException ne) {
			logger
					.error("NullPointer Exception at getCaseIdDetails in CaseMonBD : "
							+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIdDetails in CaseMonBD  :  " + e);
		}
		return caseId;

	}

	// code Start Here
	/***************************************************************************
	 * Method : Set Case Details Arguments : CaseList Return : String Exception :
	 * NullPointerException,Exception
	 **************************************************************************/
	public ArrayList caseHeadStackBD(String language,String role) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (caseHeadStackBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			list = cmBO.caseHeadStackBO(language,role);
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/***************************************************************************
	 * Method : CaseTypeStackBD Arguments : caseHeadId Return : String Exception :
	 * NullPointerException,Exception
	 **************************************************************************/
	public ArrayList caseTypeStackBD(String _caseHeadId,String language,String role) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (caseTypeStackBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			list = cmBO.caseTypeStackBO(_caseHeadId,language,role);
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/***************************************************************************
	 * Method : CaseActionTypeStackBD Arguments : caseTypeId Return : String
	 * Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList caseActionTypeStackBD(String _caseTypeId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (caseActionTypeStackBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			list = cmBO.caseActionTypeStackBO(_caseTypeId);
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
//added by shruti
	public ArrayList caseLastAction(String _caseTxnId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (caseActionTypeStackBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			list = cmBO.caseLastAction(_caseTxnId);
			return (list);
			
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
	
	
	public ArrayList viewDataRetrieveBD(String _caseTxnID)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (viewDataRetrieveBD) method");
			CaseMonBO cmBO = new CaseMonBO();

			list = cmBO.viewDataRetrieveBO(_caseTxnID);
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList searchListAddCompBD(CaseMonDTO _cmDTO)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (searchListViewBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[5];
			param[0] = "" + _cmDTO.getCaseHeadId();
			param[1] = "" + _cmDTO.getCaseTypeId();
			param[2] = "" + _cmDTO.getFromDateSearch();
			param[3] = "" + _cmDTO.getToDateSearch();
			param[4] = "" + _cmDTO.getCaseTxnId();
			list = cmBO.searchListAddCompBO(param);
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean addComplianceBD(CaseMonDTO _cmDTO, String roleId, String funId, String userId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		try {
			logger.info("Inside (addComplianceBD) method");
			CaseMonBO cmBO = new CaseMonBO();

			String param[] = new String[3];
			param[0] = "" + _cmDTO.getCompliance();
			param[1] = "" + _cmDTO.getCurrentUserId();
			param[2] = "" + _cmDTO.getCaseTxnId();
			return (cmBO.addComplianceBO(param
					,roleId,funId,userId));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	// Code Ends Here

	public CaseMonDTO licensIdBD(String _licenseId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		try {
			logger.info("Inside (licensIdBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			CaseMonDTO cmDTO = new CaseMonDTO();
			String param[] = new String[1];
			param[0] = _licenseId;
			ArrayList list = cmBO.licenseDetailsBO(param);

			if (!list.isEmpty()) {
				ArrayList licenseLst = new ArrayList();
				licenseLst = (ArrayList) list.get(0);
				cmDTO.setLicenseIdSearch(_licenseId);
				cmDTO.setSpName((String) licenseLst.get(1));
				cmDTO.setSpAddress((String) licenseLst.get(2));
				cmDTO.setLicenseValidTo((String) licenseLst.get(3));
				cmDTO.setSpUserId((String) licenseLst.get(4));
				cmDTO.setErrorMsg("FLAG");
			} else {
				cmDTO.setSpName("");
				cmDTO.setSpAddress("");
				cmDTO.setLicenseValidTo("");
				cmDTO.setLicenseIdSearch(_licenseId);
				cmDTO.setErrorMsg("This License doesn't exist");
			}
			return (cmDTO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean licenseCaseInsertBD(CaseMonDTO _cmDTO) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		try {
			logger.info("Inside (licensIdBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			IGRSCommon sqnce = new IGRSCommon();
			String casId = sqnce.getSequenceNumber("IGRS_CASE_TXN_ID", "CSO");
			String casAxnTrId = sqnce.getSequenceNumber("IGRS_CASE_AXN_TXN",
					"CSA");
			boolean flag = false;
			String param[] = new String[12];
			_cmDTO.setCaseTxnId(casId);
			param[0] = "" + casId;
			param[1] = "" + _cmDTO.getCaseHeadId();
			param[2] = "" + _cmDTO.getCaseTypeId();
			param[3] = "" + _cmDTO.getRevenueHeadId();
			param[4] = "" + _cmDTO.getSectionHeadId();
			param[5] = "" + "OPEN"; // Case Status
			param[6] = "" + _cmDTO.getSpUserId();
			param[7] = "" + _cmDTO.getLicenseId();
			param[8] = "" + _cmDTO.getCurrentUserId();
			param[9] = "" + _cmDTO.getCaseActionTypeId();
			param[10] = "" + casAxnTrId;
			param[11] = "" + _cmDTO.getDrComments();
			flag = cmBO.licenseCaseInsertBO(param);
			return (flag);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public ArrayList searchListUpdateBD(CaseMonDTO _cmDTO)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.debug("Inside (searchListUpdateBD) method");
			logger.debug("checking RRC case id value>>>>>>>>>"+_cmDTO.getRrcId());
			CaseMonBO cmBO = new CaseMonBO();
			if(_cmDTO.getCaseTxnId()!=null && !_cmDTO.getCaseTxnId().equals(""))
			{
			String param[] = new String[4];
			param[0] = "" + _cmDTO.getCaseActionTypeName();
			param[1] = "" + _cmDTO.getCaseHeadId();
			param[2] = "" + _cmDTO.getCaseTypeId();
			param[3] = "" + _cmDTO.getCaseTxnId();
			String str=CaseMonSQL.IGRS_CASE_SEARCH_LIST_UPDATE_REVENUEID;
			list = cmBO.searchListUpdateBO(param,str);
			}
			else if(!_cmDTO.getRrcId().equals("")&&_cmDTO.getRrcId()!=null)
			{
				String param[] = new String[5];
				param[0] = "" + _cmDTO.getCaseActionTypeName();
				param[1] = "" + _cmDTO.getCaseHeadId();
				param[2] = "" + _cmDTO.getCaseTypeId();
				param[3] = "" + _cmDTO.getFromDateSearch();
				param[4] = "" + _cmDTO.getToDateSearch();
				String str=CaseMonSQL.IGRS_CASE_SEARCH_LIST_UPDATE_REVENUERRCID;
				list = cmBO.searchListUpdateBO(param,str);
			}
			else
			{
				String param[] = new String[4];
				param[0] = "" + _cmDTO.getCaseHeadId();
				param[1] = "" + _cmDTO.getCaseTypeId();
				param[2] = "" + _cmDTO.getFromDateSearch();
				param[3] = "" + _cmDTO.getToDateSearch();
				String str=CaseMonSQL.IGRS_CASE_SEARCH_LIST_UPDATE_REVENUEDUR;
				list = cmBO.searchListUpdateBO(param,str);
			}
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	//added by shruti for refund cases
	public ArrayList searchListUpdateBD1(CaseMonDTO _cmDTO) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (searchListUpdateBD) method");
			CaseMonBO cmBO = new CaseMonBO();

			if(_cmDTO.getCaseTypeId()!=null && !_cmDTO.getCaseTypeId().equals(""))
			{
				if(_cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_007"))
				{
					if (_cmDTO.getCaseTxnId()!=null) {
						String param[] = new String[4];
						param[0] = "" + _cmDTO.getCaseActionTypeName();
						param[1] = "" + _cmDTO.getCaseHeadId();
						param[2] = "" + _cmDTO.getCaseTypeId();
						param[3] = "" + _cmDTO.getCaseTxnId();
						String str = CaseMonSQL.IGRS_SEARCH_LIST_FOR_ESTAMPID_UPDATE;
						list = cmBO.searchListRefundBO(param, str);
					} 
					else {
						String param[] = new String[4];
						//param[0] = "" + _cmDTO.getCaseActionTypeName();
						param[0] = "" + _cmDTO.getCaseHeadId();
						param[1] = "" + _cmDTO.getCaseTypeId();
						param[2] = "" + _cmDTO.getFromDateSearch();
						param[3] = "" + _cmDTO.getToDateSearch();
						String str = CaseMonSQL.IGRS_SEARCH_LIST_FOR_ESTAMPDUR_UPDATE;
						list = cmBO.searchListRefundBO(param, str);
					}
				}
				else if(_cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_008"))
				{
					if (_cmDTO.getCaseTxnId()!=null && !_cmDTO.getCaseTxnId().equals("")) {
						String param[] = new String[4];
						param[0] = "" + _cmDTO.getCaseActionTypeName();
						param[1] = "" + _cmDTO.getCaseHeadId();
						param[2] = "" + _cmDTO.getCaseTypeId();
						param[3] = "" + _cmDTO.getCaseTxnId();
						String str = CaseMonSQL.IGRS_SEARCH_LIST_FOR_REGFEEID_UPDATE;
						list = cmBO.searchListRefundBO(param, str);
					} 
					else {
						String param[] = new String[4];
						param[0] = "" + _cmDTO.getCaseHeadId();
						param[1] = "" + _cmDTO.getCaseTypeId();
						param[2] = "" + _cmDTO.getFromDateSearch();
						param[3] = "" + _cmDTO.getToDateSearch();
						String str = CaseMonSQL.IGRS_SEARCH_LIST_FOR_REGFEEDUR_UPDATE;
						list = cmBO.searchListRefundBO(param, str);
					}
				}
			}

			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	// added by shruti-18th july 2013
	public ArrayList searchListUpdateBD2(CaseMonDTO _cmDTO) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (searchListUpdateBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			if (_cmDTO.getCaseTxnId()!=null && !_cmDTO.getCaseTxnId().equals("")) 
			{
			String param[] = new String[4];
			param[0] = "" + _cmDTO.getCaseActionTypeName();
			param[1] = "" + _cmDTO.getCaseHeadId();
			param[2] = "" + _cmDTO.getCaseTypeId();
			param[3] = "" + _cmDTO.getCaseTxnId();
			String str = CaseMonSQL.IGRS_CASE_SEARCH_LIST_TIMEBARREDID_UPDATE;
			list = cmBO.searchListTimeBarredBO(param, str);
			}
			else
			{
				String param[] = new String[4];
				param[0] = "" + _cmDTO.getCaseHeadId();
				param[1] = "" + _cmDTO.getCaseTypeId();
				param[2] = "" + _cmDTO.getFromDateSearch();
				param[3] = "" + _cmDTO.getToDateSearch();
				String str = CaseMonSQL.IGRS_CASE_SEARCH_LIST_TIMEBARREDDUR_UPDATE;
				list = cmBO.searchListTimeBarredBO(param, str);
			}
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
	
	// added by shruti-18th july 2013
	public ArrayList searchListUpdatePenalty45BD(CaseMonDTO _cmDTO) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (searchListUpdateBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			

			if(_cmDTO.getCaseTxnId()!=null && !_cmDTO.getCaseTxnId().equals(""))
			{
				String param[] = new String[1];
				param[0] = ""+_cmDTO.getCaseTxnId();
				String str = CaseMonSQL.IGRS_CASE_SEARCH_LIST_PENALTY45ID;
				logger.info("MY QUERY--------" + str);
				list=cmBO.searchListTimeBarredBO(param, str);
			}
			else if(_cmDTO.getRevisionNo()!=null && !_cmDTO.getRevisionNo().equals(""))
			{
				String param[] = new String[1];
				param[0] = ""+_cmDTO.getRevisionNo();
				String str = CaseMonSQL.IGRS_CASE_SEARCH_LIST_PENALTY45REVISIONID;
				logger.info("MY QUERY--------" + str);
				list=cmBO.searchListTimeBarredBO(param, str);
			}
			else
			{
				String param[] = new String[2];
				param[0] = "" + _cmDTO.getFromDateSearch();
				param[1] = "" + _cmDTO.getToDateSearch();
				String str = CaseMonSQL.IGRS_CASE_SEARCH_LIST_PENALTY45DUR;
				logger.info("MY QUERY--------" + str);
				list=cmBO.searchListTimeBarredBO(param, str);
			}
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
	// added by shruti-18th july 2013
	public ArrayList searchListUpdatePenalty70BD(CaseMonDTO _cmDTO) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (searchListUpdateBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			//modified by shruti--28 june 2014--penalty case -!_cmDTO.getCaseTxnId().equalsIgnoreCase("") added
			if(_cmDTO.getCaseTxnId() != null && !_cmDTO.getCaseTxnId().equals(""))
			{
				String param[] = new String[1];
				param[0] = ""+_cmDTO.getCaseTxnId();
				String str = CaseMonSQL.IGRS_CASE_SEARCH_LIST_PENALTY70ID;
				logger.info("MY QUERY--------" + str);
				list=cmBO.searchListTimeBarredBO(param, str);
			}
			else if(_cmDTO.getRevisionNo()!=null && !_cmDTO.getRevisionNo().equals(""))
			{
				String param[] = new String[1];
				param[0] = ""+_cmDTO.getRevisionNo();
				String str = CaseMonSQL.IGRS_CASE_SEARCH_LIST_PENALTY70REVISIONID;
				logger.info("MY QUERY--------" + str);
				list=cmBO.searchListTimeBarredBO(param, str);
				//added by shruti--28 june 2014
				if(list.size()>0)
				{
				CaseMonDTO mydto=(CaseMonDTO)list.get(0);
				_cmDTO.setCaseTxnId(mydto.getCaseTxnId());
				}
				//end
			}
			else
			{
				String param[] = new String[2];
				param[0] = "" + _cmDTO.getFromDateSearch();
				param[1] = "" + _cmDTO.getToDateSearch();
				String str = CaseMonSQL.IGRS_CASE_SEARCH_LIST_PENALTY70DUR;
				logger.info("MY QUERY--------" + str);
				list=cmBO.searchListTimeBarredBO(param, str);
			}
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end

	//end
	public ArrayList sectionTypeStackBD() throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (sectionTypeStackBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			list = cmBO.sectionTypeStackBO();
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList revenueTypeStackBD() throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (revenueTypeStackBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			list = cmBO.revenueTypeStackBO();
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public String keepAxnIdBD() throws Exception {
		logger.debug("WE ARE IN BD Debug");
		String str = null;
		try {
			logger.info("Inside (keepAxnIdBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			str = cmBO.keepAxnIdBO();
			return str;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public CaseMonDTO caseSelectDetailsBD(String _caseTxnId) {
		
		//modifed by shruti-restruc
		logger.debug("WE ARE IN BD Debug");
		CaseMonDTO cmDTO = null;
		try {
			logger.info("Inside (keepAxnIdBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			cmDTO = new CaseMonDTO();
			ArrayList listBD = cmBO.caseSelectDetailsBO(_caseTxnId);

			ArrayList valueList = (ArrayList) listBD.get(0);
			if (!listBD.isEmpty()) {
				cmDTO.setCaseTxnId(_caseTxnId);
				cmDTO.setCaseStatus((String) valueList.get(0));
				cmDTO.setRevenueHeadName((String) valueList.get(1));
				cmDTO.setSectionHeadName((String) valueList.get(2));
				//cmDTO.setLicenseId((String) valueList.get(3));
				cmDTO.setCaseCreatedDate((String) valueList.get(3));
				cmDTO.setCaseHearDate((String) valueList.get(4));
				cmDTO.setCaseDueDate((String) valueList.get(5));
				//cmDTO.setTotalRecAmt((String) valueList.get(4));
				//cmDTO.setRecStampAmt((String) valueList.get(5));
				//cmDTO.setRecRegAmt((String) valueList.get(6));
			}
			return cmDTO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//added by shruti-16th july 2013

	public CaseMonDTO recoverableAmtDetailsBD(String _caseTxnId) {
		
		//modifed by shruti-restruc
		logger.debug("WE ARE IN BD Debug");
		CaseMonDTO cmDTO = null;
		try {
			logger.info("Inside (keepAxnIdBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			cmDTO = new CaseMonDTO();
			ArrayList listBD = cmBO.recoverableAmtDetailsBO(_caseTxnId);

			ArrayList valueList = (ArrayList) listBD.get(0);
			if (!listBD.isEmpty()) {
				cmDTO.setTotalRecAmt((String) valueList.get(0));
				
				//cmDTO.setCaseTxnId(_caseTxnId);
				//cmDTO.setCaseStatus((String) valueList.get(0));
				//cmDTO.setRevenueHeadName((String) valueList.get(1));
				//cmDTO.setSectionHeadName((String) valueList.get(2));
				//cmDTO.setLicenseId((String) valueList.get(3));
				//cmDTO.setCaseCreatedDate((String) valueList.get(3));
				//cmDTO.setTotalRecAmt((String) valueList.get(4));
				//cmDTO.setRecStampAmt((String) valueList.get(5));
				//cmDTO.setRecRegAmt((String) valueList.get(6));
			}
			return cmDTO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
	
	//added by shruti---13 oct 2014
public CaseMonDTO adeshikaShulkAmtDetailsBD(String _caseTxnId) {
		
		logger.debug("WE ARE IN adeshikaShulkAmtDetailsBD Debug");
		CaseMonDTO cmDTO = null;
		try {
			logger.info("Inside (adeshikaShulkAmtDetailsBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			cmDTO = new CaseMonDTO();
			ArrayList listBD = cmBO.adeshikaShulkAmtDetailsBO(_caseTxnId);
			ArrayList valueList = (ArrayList) listBD.get(0);
			if (!listBD.isEmpty()) {
				cmDTO.setAdeshikaShulk((String) valueList.get(0));
			}
			return cmDTO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
//end
	//adedd by shruti for refund cases
public CaseMonDTO caseSelectDetailsBD1(String _caseTxnId) {
		
		//modifed by shruti-restruc
		logger.debug("WE ARE IN BD Debug");
		CaseMonDTO cmDTO = null;
		try {
			logger.info("Inside (keepAxnIdBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			cmDTO = new CaseMonDTO();
			ArrayList listBD = cmBO.caseSelectDetailsBO1(_caseTxnId);

			ArrayList valueList = (ArrayList) listBD.get(0);
			if (!listBD.isEmpty()) {
				cmDTO.setCaseTxnId(_caseTxnId);
				cmDTO.setCaseStatus((String) valueList.get(0));
				cmDTO.setRevenueHeadName((String) valueList.get(1));
				cmDTO.setSectionHeadName((String) valueList.get(2));
				//cmDTO.setLicenseId((String) valueList.get(3));
				cmDTO.setCaseCreatedDate((String) valueList.get(3));
				cmDTO.setTotalRecAmt((String) valueList.get(4));
				cmDTO.setRecStampAmt((String) valueList.get(5));
				cmDTO.setRecRegAmt((String) valueList.get(6));
			}
			return cmDTO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end

public CaseMonDTO caseSelectDetailsBD2(String _caseTxnId) {
	
	//modifed by shruti-restruc
	logger.debug("WE ARE IN BD Debug");
	CaseMonDTO cmDTO = null;
	try {
		logger.info("Inside (keepAxnIdBD) method");
		CaseMonBO cmBO = new CaseMonBO();
		cmDTO = new CaseMonDTO();
		ArrayList listBD = cmBO.caseSelectDetailsBO2(_caseTxnId);

		ArrayList valueList = (ArrayList) listBD.get(0);
		if (!listBD.isEmpty()) {
			cmDTO.setCaseTxnId(_caseTxnId);
			cmDTO.setCaseStatus((String) valueList.get(0));
			cmDTO.setRevenueHeadName((String) valueList.get(1));
			cmDTO.setSectionHeadName((String) valueList.get(2));
			cmDTO.setCaseCreatedDate((String) valueList.get(3));
			cmDTO.setPenaltyAmt((String) valueList.get(4));
		}
		return cmDTO;
	} catch (Exception e) {
		logger.error(e);
		return null;
	}
}
//end
//adedd by shruti for penalty cases
public CaseMonDTO caseSelectPenalty45DetailsBD(String _caseTxnId) {
		
		//modifed by shruti-restruc
		logger.debug("WE ARE IN BD Debug");
		CaseMonDTO cmDTO = null;
		try {
			logger.info("Inside (keepAxnIdBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			cmDTO = new CaseMonDTO();
			ArrayList listBD = cmBO.caseSelectPenalty45DetailsBO(_caseTxnId);

			ArrayList valueList = (ArrayList) listBD.get(0);
			if (!listBD.isEmpty()) {
				cmDTO.setCaseTxnId(_caseTxnId);
				cmDTO.setCaseStatus((String) valueList.get(0));
				cmDTO.setRevisionNo((String) valueList.get(1));
				//cmDTO.setRevenueHeadName((String) valueList.get(1));
				//cmDTO.setSectionHeadName((String) valueList.get(2));
				//cmDTO.setLicenseId((String) valueList.get(3));
				cmDTO.setCaseCreatedDate((String) valueList.get(2));
				//cmDTO.setTotalRecAmt((String) valueList.get(4));
				//cmDTO.setRecStampAmt((String) valueList.get(5));
				//cmDTO.setRecRegAmt((String) valueList.get(6));
			}
			return cmDTO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
//adedd by shruti for penalty cases
public CaseMonDTO caseSelectPenalty70DetailsBD(String _caseTxnId) {
		
		//modifed by shruti-restruc
		logger.debug("WE ARE IN BD Debug");
		CaseMonDTO cmDTO = null;
		try {
			logger.info("Inside (keepAxnIdBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			cmDTO = new CaseMonDTO();
			ArrayList listBD = cmBO.caseSelectPenalty70DetailsBO(_caseTxnId);

			ArrayList valueList = (ArrayList) listBD.get(0);
			if (!listBD.isEmpty()) {
				cmDTO.setCaseTxnId(_caseTxnId);
				cmDTO.setCaseStatus((String) valueList.get(0));
				cmDTO.setRevisionNo((String) valueList.get(1));
				cmDTO.setCaseCreatedDate((String) valueList.get(2));
			}
			return cmDTO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
	public ArrayList caseRetrieveCommentsBD(String caseTxnId,
			String caseAxnId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside (caseRetrieveCommentsBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[2];
			param[0] = caseTxnId;
			param[1] = caseAxnId;
			listBd = cmBO.caseRetrieveCommentsBO(param);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList caseRetrieveAttachmentsBD(String caseTxnId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside (caseRetrieveCommentsBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[1];
			param[0] = caseTxnId;
			//param[1] = caseAxnId;
			listBd = cmBO.caseRetrieveAttachmentsBO(param);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public boolean commentsUpdateBD(CaseMonDTO _cmDTO,HashMap map) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		try {
			//MODIFIED BY SHRUTI-26th august 2013
			
			logger.info("Inside (commentsUpdateBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			IGRSCommon sqnce = new IGRSCommon();
			String casAxnTrId = sqnce.getSequenceNumber("IGRS_CASE_AXN_TXN","CSA");
			//String param[] = new String[9];
			String param[] = new String[18];
			param[0] = "" + casAxnTrId;
			param[1] = "" + _cmDTO.getCaseTxnId();
			param[2] = "" + _cmDTO.getCaseActionTypeId();
			param[3] = "" + _cmDTO.getCurrentUserId();
			param[4] = "" + _cmDTO.getDrComments();
			param[5] = "" + _cmDTO.getAxnDescr();
			param[6] = "" + _cmDTO.getFinalCaseComments();
			param[7] = "" + _cmDTO.getNxtActionId();
			if (_cmDTO.getReceivingDate() == null) // If Receive Date is not available then
			{param[8] = "";}
			else
			{
			param[8] = "" + _cmDTO.getReceivingDate();
			}
			if((_cmDTO.getPartyresponse()==null)||(_cmDTO.getPartyresponse().equalsIgnoreCase(""))){
			param[9]="Y";
			}
			else
			{
			param[9]=""+_cmDTO.getPartyresponse();
			}
			if(_cmDTO.getAxnDescrDate()==null)
			{
				param[10]="";
			}
			else
			{
			param[10]= ""+_cmDTO.getAxnDescrDate();
			}
			if(_cmDTO.getPartypaydec()==null)
			{
				param[11]="";
			}
			else
			{
				param[11]=""+_cmDTO.getPartypaydec();	
			}
			
			if(_cmDTO.getAuthDec()==null)
			{
				param[12]="";
			}
			else
			{
			param[12]=""+_cmDTO.getAuthDec();
			}
			/*added by shruti-11th july 2013 in order to 
			store extra fields left out for rev comm and rev board proceedings*/
			if(_cmDTO.getPropTotalRecAmt()==null)
			{
				param[13]="";
			}
			else
			{
			param[13]=""+_cmDTO.getPropTotalRecAmt();
			}
			if(_cmDTO.getPropRecStampAmt()==null)
			{
				param[14]="";
			}
			else
			{
			param[14]=""+_cmDTO.getPropRecStampAmt();
			}
			if(_cmDTO.getPropRecRegAmt()==null)
			{
				param[15]="";
			}
			else
			{
			param[15]=""+_cmDTO.getPropRecRegAmt();
			}
			if((_cmDTO.getRrcId()==null)||(_cmDTO.getRrcId().equalsIgnoreCase("")))
			{
				param[16]="-";
			}
			else
			{
				param[16]=""+_cmDTO.getRrcId();
			}
			if((_cmDTO.getNxtHearDate()==null)||(_cmDTO.getNxtHearDate().equalsIgnoreCase("")))
			{
				param[17]="";
			}
			else
			param[17]=getDatefromString(_cmDTO.getNxtHearDate());
			
			return (cmBO.commentsUpdateBO(param, _cmDTO.getActionName(),map));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	
	
	//added by shruti-15th july 2013
	public boolean getPaidAmtUpdateBD(CaseMonDTO _cmDTO) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		try {	
			logger.info("Inside (paidAmtUpdateBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[2];
			
			
			if(_cmDTO.getInstone()==null)
			{
				param[0]="0";
			}
			else
			{
				param[0]=_cmDTO.getInstone();
			}
			param[1] = "" + _cmDTO.getCaseTxnId();
			
			return cmBO.getPaidAmtUpdateBO(param);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	
	//added by shruti-refund cases
	public boolean commentsUpdateBD1(CaseMonDTO _cmDTO,HashMap map) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		try {
			//MODIFIED BY SHRUTI
			
			logger.info("Inside (commentsUpdateBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			IGRSCommon sqnce = new IGRSCommon();
			String casAxnTrId = sqnce.getSequenceNumber("IGRS_CASE_AXN_TXN","CSA");
			//String param[] = new String[9];
			String param[] = new String[19];
			param[0] = "" + casAxnTrId;
			param[1] = "" + _cmDTO.getCaseTxnId();
			param[2] = "" + _cmDTO.getCaseActionTypeId();
			param[3] = "" + _cmDTO.getCurrentUserId();
			param[4] = "" + _cmDTO.getDrComments();
			param[5] = "" + _cmDTO.getAxnDescr();
			param[6] = "" + _cmDTO.getFinalCaseComments();
			param[7] = "" + _cmDTO.getNxtActionId();
			if (_cmDTO.getReceivingDate() == null) // If Receive Date is not available then
			{param[8] = "";}
			else
			{
			param[8] = "" + _cmDTO.getReceivingDate();
			}
			if((_cmDTO.getPartyresponse()==null)||(_cmDTO.getPartyresponse().equalsIgnoreCase(""))){
				param[9]="Y";	
			}
			else
			{
			param[9]=""+_cmDTO.getPartyresponse();
			}
			if(_cmDTO.getAxnDescrDate()==null)
			{
				param[10]="";
			}
			else
			{
			param[10]= ""+_cmDTO.getAxnDescrDate();
			}
			if(_cmDTO.getPartypaydec()==null)
			{
				param[11]="";
			}
			else
			{
				param[11]=""+_cmDTO.getPartypaydec();	
			}
			
			if(_cmDTO.getAuthDec()==null)
			{
				param[12]="";
			}
			else
			{
			param[12]=""+_cmDTO.getAuthDec();
			}
			if(_cmDTO.getPropTotalRecAmt()==null)
			{
				param[13]="";
			}
			else
			{
			param[13]=""+_cmDTO.getPropTotalRecAmt();
			}
			if(_cmDTO.getPropRecStampAmt()==null)
			{
				param[14]="";
			}
			else
			{
			param[14]=""+_cmDTO.getPropRecStampAmt();
			}
			if(_cmDTO.getPropRecRegAmt()==null)
			{
				param[15]="";
			}
			else
			{
			param[15]=""+_cmDTO.getPropRecRegAmt();
			}
			param[16]=""+_cmDTO.getRefundAmount();
			param[17]=""+_cmDTO.getRefundBillNo();
			if(_cmDTO.getRefundDate()==null)
			{
				param[18]="";
			}
			else
			{
			param[18]=""+_cmDTO.getRefundDate();
			}
			return (cmBO.commentsUpdateBO1(param, _cmDTO.getActionName(),map));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	//end
	
	
	//added by shruti-penalty cases-22nd july 2013
	public boolean commentsUpdatePenalty45BD(CaseMonDTO _cmDTO,HashMap map) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		try {
			logger.info("Inside (commentsUpdateBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			IGRSCommon sqnce = new IGRSCommon();
			String casAxnTrId = sqnce.getSequenceNumber("IGRS_CASE_AXN_TXN","CSA");
			//String param[] = new String[9];
			String param[] = new String[19];
			param[0] = "" + casAxnTrId;
			//param[1] = "" + _cmDTO.getCaseTxnId();
			//modified by shruti---18 sep 2014
			param[1] = "" + _cmDTO.getRevisionNo();
			//end
			param[2] = "" + _cmDTO.getCaseActionTypeId();
			param[3] = "" + _cmDTO.getCurrentUserId();
			param[4] = "" + _cmDTO.getDrComments();
			param[5] = "" + _cmDTO.getAxnDescr();
			param[6] = "" + _cmDTO.getFinalCaseComments();
			param[7] = "" + _cmDTO.getNxtActionId();
			if (_cmDTO.getReceivingDate() == null) // If Receive Date is not available then
			{param[8] = "";}
			else
			{
			param[8] = "" + _cmDTO.getReceivingDate();
			}
			if((_cmDTO.getPartyresponse()==null)||(_cmDTO.getPartyresponse().equalsIgnoreCase(""))){
				param[9]="Y";
			}
			else
			{
			param[9]=""+_cmDTO.getPartyresponse();
			}
			if(_cmDTO.getAxnDescrDate()==null)
			{
				param[10]="";
			}
			else
			{
			param[10]= ""+_cmDTO.getAxnDescrDate();
			}
			if(_cmDTO.getPartypaydec()==null)
			{
				param[11]="";
			}
			else
			{
				param[11]=""+_cmDTO.getPartypaydec();	
			}
			
			if(_cmDTO.getAuthDec()==null)
			{
				param[12]="";
			}
			else
			{
			param[12]=""+_cmDTO.getAuthDec();
			}
			if(_cmDTO.getPropTotalRecAmt()==null)
			{
				param[13]="";
			}
			else
			{
			param[13]=""+_cmDTO.getPropTotalRecAmt();
			}
			if(_cmDTO.getPropRecStampAmt()==null)
			{
				param[14]="";
			}
			else
			{
			param[14]=""+_cmDTO.getPropRecStampAmt();
			}
			if(_cmDTO.getPropRecRegAmt()==null)
			{
				param[15]="";
			}
			else
			{
			param[15]=""+_cmDTO.getPropRecRegAmt();
			}
			param[16]=""+_cmDTO.getRefundAmount();
			param[17]=""+_cmDTO.getRefundBillNo();
			if(_cmDTO.getRefundDate()==null)
			{
				param[18]="";
			}
			else
			{
			param[18]=""+_cmDTO.getRefundDate();
			}
			return (cmBO.commentsUpdatePenalty45BO(param, _cmDTO.getActionName(),map));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	//end
	
	//added by shruti-penalty cases-22nd july 2013
	public boolean commentsUpdatePenalty70BD(CaseMonDTO _cmDTO,HashMap map) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		try {
			logger.info("Inside (commentsUpdateBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			IGRSCommon sqnce = new IGRSCommon();
			String casAxnTrId = sqnce.getSequenceNumber("IGRS_CASE_AXN_TXN","CSA");
			//String param[] = new String[9];
			String param[] = new String[19];
			param[0] = "" + casAxnTrId;
			//modified by shruti---29 sep 2014
			//param[1] = "" + _cmDTO.getCaseTxnId();
			param[1] = "" + _cmDTO.getRevisionNo();
			param[2] = "" + _cmDTO.getCaseActionTypeId();
			param[3] = "" + _cmDTO.getCurrentUserId();
			param[4] = "" + _cmDTO.getDrComments();
			param[5] = "" + _cmDTO.getAxnDescr();
			param[6] = "" + _cmDTO.getFinalCaseComments();
			param[7] = "" + _cmDTO.getNxtActionId();
			if (_cmDTO.getReceivingDate() == null) // If Receive Date is not available then
			{param[8] = "";}
			else
			{
			param[8] = "" + _cmDTO.getReceivingDate();
			}
			if((_cmDTO.getPartyresponse()==null)||(_cmDTO.getPartyresponse().equalsIgnoreCase(""))){
				param[9]="Y";
			}
			else
			{
			param[9]=""+_cmDTO.getPartyresponse();
			}
			if(_cmDTO.getAxnDescrDate()==null)
			{
				param[10]="";
			}
			else
			{
			param[10]= ""+_cmDTO.getAxnDescrDate();
			}
			if(_cmDTO.getPartypaydec()==null)
			{
				param[11]="";
			}
			else
			{
				param[11]=""+_cmDTO.getPartypaydec();	
			}
			
			if(_cmDTO.getAuthDec()==null)
			{
				param[12]="";
			}
			else
			{
			param[12]=""+_cmDTO.getAuthDec();
			}
			if(_cmDTO.getPropTotalRecAmt()==null)
			{
				param[13]="";
			}
			else
			{
			param[13]=""+_cmDTO.getPropTotalRecAmt();
			}
			if(_cmDTO.getPropRecStampAmt()==null)
			{
				param[14]="";
			}
			else
			{
			param[14]=""+_cmDTO.getPropRecStampAmt();
			}
			if(_cmDTO.getPropRecRegAmt()==null)
			{
				param[15]="";
			}
			else
			{
			param[15]=""+_cmDTO.getPropRecRegAmt();
			}
			param[16]=""+_cmDTO.getRefundAmount();
			param[17]=""+_cmDTO.getRefundBillNo();
			if(_cmDTO.getRefundDate()==null)
			{
				param[18]="";
			}
			else
			{
			param[18]=""+_cmDTO.getRefundDate();
			}
			return (cmBO.commentsUpdatePenalty70BO(param, _cmDTO.getActionName(),map));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	//end
	

	//added by shruti-refund cases
	public boolean commentsUpdateBD2(CaseMonDTO _cmDTO,HashMap map) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		try {
			
			logger.info("Inside (commentsUpdateBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			IGRSCommon sqnce = new IGRSCommon();
			String casAxnTrId = sqnce.getSequenceNumber("IGRS_CASE_AXN_TXN","CSA");
			String param[] = new String[19];
			param[0] = "" + casAxnTrId;
			param[1] = "" + _cmDTO.getCaseTxnId();
			param[2] = "" + _cmDTO.getCaseActionTypeId();
			param[3] = "" + _cmDTO.getCurrentUserId();
			param[4] = "" + _cmDTO.getDrComments();
			param[5] = "" + _cmDTO.getAxnDescr();
			param[6] = "" + _cmDTO.getFinalCaseComments();
			param[7] = "" + _cmDTO.getNxtActionId();
			if (_cmDTO.getReceivingDate() == null) // If Receive Date is not available then
			{param[8] = "";}
			else
			{
			param[8] = "" + _cmDTO.getReceivingDate();
			}
			if((_cmDTO.getPartyresponse()==null)||(_cmDTO.getPartyresponse().equalsIgnoreCase(""))){
			param[9]="Y";
			}
			else
			{
			param[9]=""+_cmDTO.getPartyresponse();
			}
			if(_cmDTO.getAxnDescrDate()==null)
			{
				param[10]="";
			}
			else
			{
			param[10]= ""+_cmDTO.getAxnDescrDate();
			}
			if(_cmDTO.getPartypaydec()==null)
			{
				param[11]="";
			}
			else
			{
				param[11]=""+_cmDTO.getPartypaydec();	
			}
			
			if(_cmDTO.getAuthDec()==null)
			{
				param[12]="";
			}
			else
			{
			param[12]=""+_cmDTO.getAuthDec();
			}
			if(_cmDTO.getPropTotalRecAmt()==null)
			{
				param[13]="";
			}
			else
			{
			param[13]=""+_cmDTO.getPropTotalRecAmt();
			}
			if(_cmDTO.getPropRecStampAmt()==null)
			{
				param[14]="";
			}
			else
			{
			param[14]=""+_cmDTO.getPropRecStampAmt();
			}
			if(_cmDTO.getPropRecRegAmt()==null)
			{
				param[15]="";
			}
			else
			{
			param[15]=""+_cmDTO.getPropRecRegAmt();
			}
			param[16]=""+_cmDTO.getRefundAmount();
			param[17]=""+_cmDTO.getRefundBillNo();
			if(_cmDTO.getRefundDate()==null)
			{
				param[18]="";
			}
			else
			{
			param[18]=""+_cmDTO.getRefundDate();
			}
			return (cmBO.commentsUpdateBO2(param, _cmDTO.getActionName(),map));
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	//end

	//end

	public ArrayList nxtActionBD(String _caseTypeId, String _caseAxnId)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside (nxtActionBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[2];
			param[0] = _caseTypeId;
			param[1] = _caseAxnId;
			listBd = cmBO.nxtActionBO(param);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//added by shruti
				public ArrayList nxtActionBD1(String _caseTypeId, String _caseAxnId)
				throws Exception {
			logger.debug("WE ARE IN BD Debug");
			ArrayList listBd = new ArrayList();
			try {
				logger.info("Inside (nxtActionBD) method");
				CaseMonBO cmBO = new CaseMonBO();
				String param[] = new String[2];
				param[0] = _caseTypeId;
				param[1] = _caseAxnId;
				listBd = cmBO.nxtActionBO1(param);
				return (listBd);
			} catch (Exception e) {
				logger.error(e);
				return null;
			}
			}
				//end
				
				//added by shruti--rrc case
				public ArrayList nxtActionBD2(String _caseTypeId, String _caseAxnId)
				throws Exception {
			logger.debug("WE ARE IN BD Debug");
			ArrayList listBd = new ArrayList();
			try {
				logger.info("Inside (nxtActionBD) method");
				CaseMonBO cmBO = new CaseMonBO();
				String param[] = new String[2];
				param[0] = _caseTypeId;
				param[1] = _caseAxnId;
				listBd = cmBO.nxtActionBO2(param);
				return (listBd);
			} catch (Exception e) {
				logger.error(e);
				return null;
			}
			}
				//end
				
				//added by shruti--rrc case
				public ArrayList nxtActionBD3(String _caseTypeId, String _caseAxnId)
				throws Exception {
			logger.debug("WE ARE IN BD Debug");
			ArrayList listBd = new ArrayList();
			try {
				logger.info("Inside (nxtActionBD) method");
				CaseMonBO cmBO = new CaseMonBO();
				String param[] = new String[2];
				param[0] = _caseTypeId;
				param[1] = _caseAxnId;
				listBd = cmBO.nxtActionBO3(param);
				return (listBd);
			} catch (Exception e) {
				logger.error(e);
				return null;
			}
			}
				//end
				
				

	public ArrayList searchListViewBD(CaseMonDTO _cmDTO)
			throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList list = new ArrayList();
		try {
			logger.info("Inside (searchListViewBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[5];
			param[0] = "" + _cmDTO.getCaseHeadId();
			param[1] = "" + _cmDTO.getCaseTypeId();
			param[2] = "" + _cmDTO.getFromDateSearch();
			param[3] = "" + _cmDTO.getToDateSearch();
			param[4] = "" + _cmDTO.getCaseTxnId();
			list = cmBO.searchListViewBO(param);
			return (list);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/***************************************************************************
	 * Method : GetCaseId Details Arguments : Case ID Return : ArrayList
	 * Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseIdDetails(String _caseId) throws Exception {
		ArrayList caseList = null;
		CaseMonBO caseBo = new CaseMonBO();
		try {
			caseList = new ArrayList();
			caseList = caseBo.getCaseIdDetails(_caseId);
		} catch (NullPointerException ne) {
			logger
					.error("NullPointer Exception at getCaseIdDetails in CaseMonBD : "
							+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIdDetails in CaseMonBD  :  " + e);
		}
		return caseList;
	}

	/***************************************************************************
	 * Method : GetCaseIds Arguments : From Date and ToDate Return : ArrayList
	 * Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseIds(String _fromDate, String _toDate,
			String _caseType, String _status) throws NullPointerException,
			Exception {
		ArrayList caseList = null;
		CaseMonBO caseBo = new CaseMonBO();
		try {
			caseList = new ArrayList();
			caseList = caseBo
					.getCaseIds(_fromDate, _toDate, _caseType, _status);
		} catch (NullPointerException ne) {
			logger.error("NullPointer Exception at getCaseIDs in CaseMonBD : "
					+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseids in CaseMonBD  :  " + e);
		}
		return caseList;
	}

	
	// added by shruti
	public boolean updateActionMap(CaseMonDTO cmDTO,String userId)
	{
		CaseMonBO caseBo = new CaseMonBO();
		boolean flag=false;
		try
		{
			flag=caseBo.updateActionMap(cmDTO, userId);
			
			
		}
		catch (NullPointerException ne) {
			logger.error("NullPointer Exception at update case action map in CaseMonBD : "
					+ ne);
		} catch (Exception e) {
			logger.error("Exception at update case action map in CaseMonBD  :  " + e);
		}
		
		return flag;
		
	}
	//end
	
	public ArrayList getNxtActnDtls(String lastAction,String caseTxnId)
	{
		CaseMonBO caseBo=new CaseMonBO();
		ArrayList dtls=new ArrayList();
		try
		{
			dtls= caseBo.getNxtActnDtls(lastAction, caseTxnId);
		}
		catch (NullPointerException ne) {
			logger.error("NullPointer Exception at update case action map in CaseMonBD : "
					+ ne);
		} catch (Exception e) {
			logger.error("Exception at update case action map in CaseMonBD  :  " + e);
		}
		return dtls;
	}
	
	//inserton in payment table
	public String insertCasePymtTxnDtls(CaseMonDTO cmDTO,String userId)
	{
		CaseMonBO caseBo=new CaseMonBO();
		String pymttxnsrid="";
		try
		{	
			pymttxnsrid= caseBo.insertCasePymtTxnDtls(cmDTO, userId);
		}
		catch (NullPointerException ne) {
			logger.error("NullPointer Exception at update case action map in CaseMonBD : "
					+ ne);
		} catch (Exception e) {
			logger.error("Exception at update case action map in CaseMonBD  :  " + e);
		}
		
		return pymttxnsrid;
	}
	
	
	public String getPaidAmt(CaseMonDTO cmDTO,String pymtsrid)
	{
		CaseMonBO caseBo=new CaseMonBO();
		String paidAmt="";
		try
		{
			paidAmt=caseBo.getPaidAmt(cmDTO, pymtsrid);
		}
		catch(NullPointerException ne)
		{
			logger.error("NullPointer Exception at update case action map in CaseMonBD : "
					+ ne);
		}
		catch (Exception e) {
			logger.error("Exception at update case action map in CaseMonBD  :  " + e);
		}
		return paidAmt;
	}
	
	public ArrayList getCasePymtDetails(String _caseId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside (caseRetrieveCommentsBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[1];
			param[0] = _caseId;
			listBd = cmBO.getCasePymtDetails(_caseId);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	//added by shruti--21 oct 2014
	public ArrayList getCasePymtDetailsEstamp(String _caseId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside (getCasePymtDetailsEstampBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[1];
			param[0] = _caseId;
			listBd = cmBO.getCasePymtDetailsEstamp(_caseId);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
	
	//added by shruti for regsitered doc details
	public ArrayList getRegDocDetails(String _caseId,CaseMonDTO cmDTO ) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside (getRegDocDetailsBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[1];
			param[0] = _caseId;
			listBd = cmBO.getRegDocDetails(_caseId,cmDTO);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
	
	public ArrayList getPendingPymtDetails(String _caseId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside (caseRetrieveCommentsBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[1];
			param[0] = _caseId;
			listBd = cmBO.getPendingPymtDetails(_caseId);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//added by shruti-18th july 2013
	public ArrayList getPendingPymtOthers(String _caseId) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside (caseRetrieveCommentsBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[1];
			param[0] = _caseId;
			listBd = cmBO.getPendingPymtOthers(_caseId);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
	//added by shruti-11th july 2013
	public ArrayList getRevisedRecoverableAmtDetails(CaseMonDTO dto) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside (caseRetrieveCommentsBD) method");
			CaseMonBO cmBO = new CaseMonBO();
			String param[] = new String[2];
			param[0] = dto.getCaseActionTypeName();
			param[1]=dto.getCaseTxnId();
			listBd = cmBO.getRevisedRecoverableAmtDetails(param);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public ArrayList getRRCDetails(CaseMonDTO dto) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside (caseRetrieveCommentsBD) method");
			CaseMonBO cmBO = new CaseMonBO();
		/*	String param[] = new String[2];
			param[0] = dto.getCaseActionTypeName();
			param[1]=dto.getCaseTxnId();*/
			listBd = cmBO.getRRCDetails(dto);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public String getRRCConfigParam()
	{
		String mnthparam="";
		try
		{
			CaseMonBO cmBO = new CaseMonBO();
			mnthparam=cmBO.getRRCConfigParam();
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}
		return mnthparam;
		
	}

	public String getRRCMonthCriteria(CaseMonDTO dto)
	{
		String mnthchk="";
		try
		{
			CaseMonBO cmBO = new CaseMonBO();
			mnthchk=cmBO.getRRCMonthCriteria(dto);
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}
		return mnthchk;
		
	}
	public String updateRRCDtls(CaseMonDTO dto)
	{
		boolean flag=false;
		String rno="";
				
		try
		{
			CaseMonBO cmBO = new CaseMonBO();
			rno=cmBO.updateRRCDtls(dto);
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}
	return rno;
	}

	
	public String getCaseId(CaseMonDTO dto)
	{
		String caseId="";
		try
		{
			CaseMonBO cmBO = new CaseMonBO();
			caseId=cmBO.getCaseId(dto);
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}
		return caseId;
		
	}
	public String getRRCId(CaseMonDTO dto)
	{
		String caseId="";
		try
		{
			CaseMonBO cmBO = new CaseMonBO();
			caseId=cmBO.getrrcId(dto);
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}
		return caseId;
		
	}
	//added by shruti
	private String getDatefromString(String dateString) {
		logger.debug("dateString" + dateString);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try {
			date = simpleDateFormat.parse(dateString);
			logger.debug("date=" + date);
		} catch (ParseException pe) {
			logger.debug("ParseException: " + pe);
		}
		return getStringFromDate(date);
	}
	private String getStringFromDate(Date date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String dateString = null;
		try {
			if (date != null)
				dateString = dateFormat.format(date);
		} catch (Exception e) {
			logger.debug("Error getting date1 field in 'dd-MMM-yy' format: " + e);
		}
		return dateString;
	}
	
//adedd by shruti-28th august 2013
	public ArrayList getSalutationDetails(CaseMonDTO dto) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside getSalutationDetails method");
			CaseMonBO cmBO = new CaseMonBO();
		/*	String[] caseid={dto.getCaseTxnId(),dto.getOfficeId()};*/
			listBd = cmBO.getSalutationDetails(dto);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	//added by shruti---29 oct 2014
	public ArrayList getRevSalutationDetails(CaseMonDTO dto) throws Exception {
		logger.debug("WE ARE IN BD Debug");
		ArrayList listBd = new ArrayList();
		try {
			logger.info("Inside getSalutationDetails method");
			CaseMonBO cmBO = new CaseMonBO();
		/*	String[] caseid={dto.getCaseTxnId(),dto.getOfficeId()};*/
			listBd = cmBO.getRevSalutationDetails(dto);
			return (listBd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end

	//added-28th oct 2013
	public ArrayList getInternalUserDtls(String officeId,String language) throws Exception{
		ArrayList resultList =new ArrayList();
		
		try {
			CaseMonBO cmBO = new CaseMonBO();
		resultList=cmBO.getInternalUserDtls(officeId,language);
		}catch (Exception e) {
			logger.error(e);	
		}
		 return resultList;
	}
	//end

	
	//added by shruti---6 sep 2014
	public String getOfficeTypeId(String officeId)
	{

		String officeTypeId = null;

		String arr[]={officeId};
		try{
			CaseMonBO cmBO = new CaseMonBO();
			officeTypeId=cmBO.getOfficeTypeId(officeId);
	}
		catch(Exception ex)
		{
		ex.printStackTrace();
		}
		return officeTypeId;
	}
	//end
	
	//ADDED BY SHRUTI---9 JAN 2015
	public String getSectionHead(String caseId)
	{

		String officeTypeId = null;

		String arr[]={caseId};
		try{
			CaseMonBO cmBO = new CaseMonBO();
			officeTypeId=cmBO.getSectionHead(caseId);
	}
		catch(Exception ex)
		{
		ex.printStackTrace();
		}
		return officeTypeId;
	}
	
	public String getEStampCodeForCaseId(String caseId) throws Exception {//added by roopam 9feb2015 pot estamp
		
		//log.info("Inside getEStampCodeForCaseId()");
		CaseMonitoringDAO dao = new CaseMonitoringDAO();
		String list = dao.getEStampCodeForCaseId(caseId);
		

		return list;
	}
	
}// close BD Class
