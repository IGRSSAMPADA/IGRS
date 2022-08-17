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
package com.wipro.igrs.caseMonitoring.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.caseMonitoring.dao.CaseMonDAO;
import com.wipro.igrs.caseMonitoring.dao.CaseMonitoringDAO;
import com.wipro.igrs.caseMonitoring.dto.CaseMonDTO;
import com.wipro.igrs.caseMonitoring.dto.CaseMonitoringDTO;
import com.wipro.igrs.caseMonitoring.sql.CommonSQL;
import com.wipro.igrs.caseMonitoring.util.CommonUtil;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;

/*******************************************************************************
 * 
 * File : CaseMonBO.java Description : Represent the CaseMonitoring BO Class
 * Author : Karteek P Created Date : June 23, 2008
 ******************************************************************************/
public class CaseMonBO {
	public CaseMonBO() {
	}

	CaseMonDAO caseDao = new CaseMonDAO();

	private Logger logger = (Logger) Logger.getLogger(CaseMonBO.class);

	/***************************************************************************
	 * Method : GetCaseIds Arguments : From Date and ToDate Return : ArrayList
	 * Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseIds(String _fromDate, String _toDate,
			String _caseType, String _status, String _caseActionType)
			throws NullPointerException, Exception {

		ArrayList caseList = null;
		try {
			ArrayList getCaseIDS = new ArrayList();
			ArrayList getCaseId = null;
			getCaseIDS = caseDao.getCaseIds(_fromDate, _toDate, _caseType,
					_status, _caseActionType);
			if (getCaseIDS != null) {
				caseList = new ArrayList();
				for (int i = 0; i < getCaseIDS.size(); i++) {
					getCaseId = new ArrayList();
					getCaseId = (ArrayList) getCaseIDS.get(i);
					if (getCaseId.size() > 0) {
						CaseMonDTO caseDto = new CaseMonDTO();
						caseDto.setCaseID((String) getCaseId.get(0));
						caseDto.setCaseCreatedDate((String) getCaseId.get(1));

						caseList.add(caseDto);
					} else {
						caseList.add(null);
					}
				}
			} else {
				caseList.add(null);
			}

		} catch (NullPointerException ne) {
			logger.error("Nullpointer Exception at getCaseIds In caseMonBO  : "
					+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIds in CaseMonBO  : " + e);
		}

		return caseList;
	}

	/***************************************************************************
	 * Method : GetCaseViewIds Arguments : From Date and ToDate and CaseType
	 * Return : ArrayList Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseViewIds(String _fromDate, String _toDate,
			String _caseType) throws NullPointerException, Exception {

		ArrayList caseList = null;
		try {
			ArrayList getCaseIDS = new ArrayList();
			ArrayList getCaseId = null;
			logger.debug("Case Type Bo --->  " + _caseType);
			getCaseIDS = caseDao.getCaseViewIds(_fromDate, _toDate, _caseType);
			if (getCaseIDS != null) {
				caseList = new ArrayList();
				for (int i = 0; i < getCaseIDS.size(); i++) {
					getCaseId = new ArrayList();
					getCaseId = (ArrayList) getCaseIDS.get(i);
					if (getCaseId.size() > 0) {
						CaseMonDTO caseDto = new CaseMonDTO();
						caseDto.setCaseID((String) getCaseId.get(0));
						caseDto.setCaseCreatedDate((String) getCaseId.get(1));
						caseDto.setCaseStatus((String) getCaseId.get(2));
						//logger.debug("444 Value --> " + getCaseId);
						caseList.add(caseDto);
					} else {
						caseList.add(null);
					}
				}
			} else {
				caseList.add(null);
			}

		} catch (NullPointerException ne) {
			logger.error("Nullpointer Exception at getCaseViewIds In caseMonBO  : "+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseViewIds in CaseMonBO  : " + e);
		}
		//logger.debug("Final BO return -->  " + caseList);
		return caseList;
	}

	/***************************************************************************
	 * Method : GetCaseId Details Arguments : Case ID Return : ArrayList
	 * Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseIdDetails(String _caseId, String _caseActionType)
			throws NullPointerException, Exception {
		ArrayList caseDetails = null;
		try {
			ArrayList getCaseDetails = new ArrayList();
			ArrayList getCaseIdDetails = null;
			getCaseDetails = caseDao.getCaseIdDetails(_caseId, _caseActionType);
			if (getCaseDetails != null) {
				caseDetails = new ArrayList();
				String comments = "";
				getCaseIdDetails = new ArrayList();
				CaseMonDTO caseDto = new CaseMonDTO();
				for (int i = 0; i < getCaseDetails.size(); i++) {
					getCaseIdDetails = (ArrayList) getCaseDetails.get(i);
					if (getCaseIdDetails.size() > 0) {
						/*logger.debug("Before Comments are -->  " + comments
								+ "   --  i value " + i + "added value is --> "
								+ getCaseIdDetails.get(4));*/
						if ((String) getCaseIdDetails.get(4) != null) {
							comments = comments + " "
									+ (String) getCaseIdDetails.get(4);
						}
						//logger.debug("After  Comments are -->  " + comments);
					}
				}
				//logger.debug("Comments are -->  " + comments);
				caseDto.setCaseComments(comments);
				caseDto.setCaseID((String) getCaseIdDetails.get(0));
				caseDto.setCaseCreatedDate((String) getCaseIdDetails.get(1));
				caseDto.setCaseStampId((String) getCaseIdDetails.get(2));
				//logger.debug("3333333333333--->  " + caseDto.getCaseStampId());
				caseDto.setCaseStampAmt(caseDao.getCaseEstampAmt(caseDto.getCaseStampId()));
				caseDto.setCasePenalty((String) getCaseIdDetails.get(3));
				//logger.debug("444 Value --> " + getCaseIdDetails);
				//logger.debug("55555555 -->  " + caseDto);
				caseDetails.add(caseDto);

			}

		} catch (NullPointerException ne) {
			logger.error("Nullpointer Exception at getCaseIdDetails In caseMonBO  : "+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIdDetails in CaseMonBO  : " + e);
		}
		logger.debug("Final BO return -->  " + caseDetails);
		return caseDetails;
	}

	/***************************************************************************
	 * Method : GetCase Notice Details Arguments : Case ID Return : ArrayList
	 * Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseNoticeDetails(String _caseId, String _caseActionType)
			throws NullPointerException, Exception {
		ArrayList caseDetails = null;
		try {
			ArrayList getCaseDetails = new ArrayList();
			ArrayList getCaseIdDetails = null;
			getCaseDetails = caseDao.getCaseIdDetails(_caseId, _caseActionType);
			if (getCaseDetails != null) {
				caseDetails = new ArrayList();
				String comments = "";
				String notice = "";
				getCaseIdDetails = new ArrayList();
				CaseMonDTO caseDto = new CaseMonDTO();
				for (int i = 0; i < getCaseDetails.size(); i++) {
					getCaseIdDetails = (ArrayList) getCaseDetails.get(i);
					if (getCaseIdDetails.size() > 0) {
						/*logger.debug("Before Comments are -->  " + comments
								+ "   --  i value " + i + "added value is --> "
								+ getCaseIdDetails.get(4));*/
						if ((String) getCaseIdDetails.get(4) != null)
						{
							comments = comments + " "+ (String) getCaseIdDetails.get(4);
						}
						if ((String) getCaseIdDetails.get(5) != null) {
							notice = notice + (String) getCaseIdDetails.get(5);
						}
						//logger.debug("After  Comments are -->  " + comments);
					}
				}
				//logger.debug("Comments are -->  " + comments);
				caseDto.setCaseComments(comments);
				caseDto.setCaseID((String) getCaseIdDetails.get(0));
				caseDto.setCaseNotice(notice);
				caseDto.setCasenoticeDate((String) getCaseIdDetails.get(6));
				caseDto.setCaseCreatedDate((String) getCaseIdDetails.get(1));
				caseDto.setCaseStampId((String) getCaseIdDetails.get(2));
				//logger.debug("3333333333333--->  " + caseDto.getCaseStampId());
				caseDto.setCaseStampAmt(caseDao.getCaseEstampAmt(caseDto.getCaseStampId()));
				caseDto.setCasePenalty((String) getCaseIdDetails.get(3));
				//logger.debug("444 Value --> " + getCaseIdDetails);
				//logger.debug("55555555 -->  " + caseDto);
				caseDetails.add(caseDto);

			}

		} catch (NullPointerException ne) {
			logger.error("Nullpointer Exception at getCaseIdDetails In caseMonBO  : "+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIdDetails in CaseMonBO  : " + e);
		}
		//logger.debug("Final BO return -->  " + caseDetails);
		return caseDetails;
	}

	/***************************************************************************
	 * Method : Set Case Details Arguments : CaseList Return : String Exception
	 * : NullPointerException,Exception
	 * 
	 * @param userId
	 * @param funId
	 * @param roleId
	 **************************************************************************/
	public synchronized String setCaseDetails(CaseMonDTO _caseDto,
			String roleId, String funId, String userId)
			throws NullPointerException, SQLException, Exception {
		String caseID = null;
		try {
			String[] caseList = new String[8];
			caseList[0] = _caseDto.getCaseID();
			caseList[1] = _caseDto.getCaseHeadName();
			caseList[2] = _caseDto.getCaseTypeName();
			caseList[3] = "Open";
			caseList[4] = "IGRS";
			caseList[5] = _caseDto.getCaseStampId();
			//logger.debug("BO Penalty AMt --> " + _caseDto.getCasePenalty());
			caseList[6] = _caseDto.getCasePenalty();
			caseList[7] = null;

			// caseList[6] = _caseDto.getCaseDrComments();
			caseID = caseDao.setCaseDetails(caseList, _caseDto
					.getCaseDrComments(), roleId, funId, userId);

		} catch (NullPointerException ne) {
			logger.error("Nullpointer Exception at setCaseDetails In caseMonBO  : "+ ne);
		} catch (Exception e) {
			logger.error("Exception at setCaseDetails in CaseMonBO  : " + e);
		}

		return caseID;
	}

	/***************************************************************************
	 * Method : Set Case Details Arguments : CaseList Return : String Exception
	 * : NullPointerException,Exception
	 * 
	 * @param userId
	 * @param funId
	 * @param roleId
	 **************************************************************************/
	public synchronized String updateCaseDetails(CaseMonDTO _caseDto,
			String _caseAction, String roleId, String funId, String userId)
			throws NullPointerException, SQLException, Exception {
		String caseID = null;
		try {
			if ("INITIATE".equalsIgnoreCase(_caseAction)) {
				String[] caseList = new String[3];
				caseList[0] = _caseDto.getRevisedPenalty();
				caseList[1] = _caseAction;
				caseList[2] = _caseDto.getCaseID();
				String caseid = caseDao.updateCaseDetails(caseList, _caseDto
						.getCaseDrComments(), roleId, funId, userId);
				return caseid;
			}
			if ("CLOSE".equalsIgnoreCase(_caseAction)) {
				String[] caseList = new String[4];
				caseList[0] = _caseDto.getRevisedPenalty();
				caseList[1] = _caseAction;
				caseList[2] = _caseDto.getCaseNotice();
				caseList[3] = _caseDto.getCaseID();
				String caseId = caseDao.CaseClsoeDetails(caseList, _caseDto
						.getCaseDrComments(), "", roleId, funId, userId);
				return caseId;
			}
			if ("NOTICE".equalsIgnoreCase(_caseAction)) {
				String[] caseList = new String[4];
				caseList[0] = _caseDto.getRevisedPenalty();
				caseList[1] = _caseAction;
				caseList[2] = _caseDto.getCaseHearDate();
				caseList[3] = _caseDto.getCaseID();
				String caseId = caseDao.CaseNoticeDetails(caseList, _caseDto
						.getCaseDrComments(), _caseDto.getCaseNotice(), roleId,
						funId, userId);
				return caseId;
			}
		} catch (NullPointerException ne) {
			logger.error("Nullpointer Exception at updateCaseDetails In caseMonBO  : "+ ne);
		} catch (Exception e) {
			logger.error("Exception at updateCaseDetails in CaseMonBO  : " + e);
		}

		return caseID;
	}

	// Code Starts Here
	/***************************************************************************
	 * Method : caseHeadStackBO Arguments : Return : ArrayList Throws :
	 * NullPointerException,Exception
	 **************************************************************************/
	public ArrayList caseHeadStackBO(String language,String role) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (caseHeadStackBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = caseDao.caseHeadStack(language,role);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/***************************************************************************
	 * Method : caseTypeStackBO Arguments : caseHeadId Return : ArrayList Throws
	 * : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList caseTypeStackBO(String _caseHeadId,String language,String role) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (caseTypeStackBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = caseDao.caseTypeStack(_caseHeadId,language,role);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/***************************************************************************
	 * Method : caseActionTypeStackBO Arguments : caseTypeId Return : ArrayList
	 * Throws : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList caseActionTypeStackBO(String _caseTypeId) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (caseActionTypeStackBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.caseActionTypeStackDAO(_caseTypeId);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// added by shruti
	public ArrayList caseLastAction(String _caseTxnId) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (caseActionTypeStackBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.caseLastAction(_caseTxnId);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// end

	/***************************************************************************
	 * Method : viewDataRetrieveBO Arguments : CaseTransactionId Return :
	 * ArrayList Throws : NullPointerException,Exception
	 **************************************************************************/

	public ArrayList viewDataRetrieveBO(String _caseTxnId)
			throws NullPointerException, Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (viewDataRetrieveBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();

			listBo = cmDAO.viewDataRetrieveDAO(_caseTxnId);

			return (listBo);
		} catch (NullPointerException ne) {
			logger.error(ne);

		} catch (Exception e) {
			logger.error(e);

		}
		return listBo;
	}

	public ArrayList searchListAddCompBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (searchListAddCompBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.searchListAddCompDAO(param);

			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean addComplianceBO(String param[], String roleId, String funId,
			String userId) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		try {
			logger.info("Inside (addComplianceBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();

			return cmDAO.addComplianceDAO(param, roleId, funId, userId);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	// Code Ends Here

	public boolean addParty(CaseMonDTO _cDTO) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		try {
			logger.info("Inside (addParty) method");
			CaseMonDAO refDAO = new CaseMonDAO();
			CommonUtil cUtil = new CommonUtil();
			return (true);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public ArrayList sectionTypeStackBO() throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (sectionTypeStackBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.sectionTypeStackDAO();
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList revenueTypeStackBO() throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (sectionTypeStackBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.revenueTypeStackDAO();
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// ADDED BY SHRUTI

	public boolean licenseCaseInsertBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		try {
			// modified acc to restruc
			logger.info("Inside (licenseCaseInsertBO) method");

			CaseMonDAO cmDAO = new CaseMonDAO();
			String casesrno = cmDAO.getCaseTxnSrNo();
			String actionmapsrno = cmDAO.getActionMapSrNo();
			String cmntssrno = cmDAO.getCaseCmntsSrNo();

			String param1[] = new String[8];
			param1[0] = casesrno;
			param1[1] = param[0];
			param1[2] = param[1];
			param1[3] = param[2];
			param1[4] = param[3];
			param1[5] = param[4];
			param1[6] = param[5];
			param1[7] = param[8];
			// param1[6] = param[6];
			// param1[7] = param[7];
			// param1[8] = param[8];
			// param1[9] = param[9];

			String param2[] = new String[7];

			param2[0] = actionmapsrno;
			param2[1] = param[10];
			param2[2] = param[0];
			param2[3] = param[9];
			param2[4] = param[11];
			param2[5] = "Y";
			param2[6] = param[8];

			String param3[] = new String[5];
			param3[0] = cmntssrno;
			param3[1] = param[0];
			param3[2] = param[10];
			param3[3] = param[11];
			param3[4] = param[8];

			boolean flag = cmDAO.licenseCaseInsertDAO(param1, param2, param3);

			return (flag);

		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	public String keepAxnIdBO() throws Exception {
		logger.debug("WE ARE IN BO Debug");
		String str = "";
		try {
			logger.info("Inside (keepAxnIdBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			str = cmDAO.keepAxnIdDAO();
			return (str);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList caseSelectDetailsBO(String _caseTxnId) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		String str = "";
		try {
			logger.info("Inside (caseSelectDetailsBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			ArrayList listBO = new ArrayList();
			listBO = cmDAO.caseSelectDetailsDAO(_caseTxnId);
			return (listBO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//added by shruti
	public ArrayList recoverableAmtDetailsBO(String _caseTxnId) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		String str = "";
		try {
			logger.info("Inside (recoverableAmtDetailsDAO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			ArrayList listBO = new ArrayList();
			listBO = cmDAO.recoverableAmtDetailsDAO(_caseTxnId);
			return (listBO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
	
	//added by shruti----13 oct 2014
	public ArrayList adeshikaShulkAmtDetailsBO(String _caseTxnId) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		String str = "";
		try {
			logger.info("Inside (recoverableAmtDetailsDAO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			ArrayList listBO = new ArrayList();
			listBO = cmDAO.adeshikaShulkAmtDetailsDAO(_caseTxnId);
			return (listBO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}//end
	//added by shruti for refund cases
	public ArrayList caseSelectDetailsBO1(String _caseTxnId) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		String str = "";
		try {
			logger.info("Inside (caseSelectDetailsBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			ArrayList listBO = new ArrayList();
			listBO = cmDAO.caseSelectDetailsDAO1(_caseTxnId);
			return (listBO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
	//added by shruti for refund cases
	public ArrayList caseSelectDetailsBO2(String _caseTxnId) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		String str = "";
		try {
			logger.info("Inside (caseSelectDetailsBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			ArrayList listBO = new ArrayList();
			listBO = cmDAO.caseSelectDetailsDAO2(_caseTxnId);
			return (listBO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	//end
	public ArrayList caseSelectPenalty45DetailsBO(String _caseTxnId) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		String str = "";
		try {
			logger.info("Inside (caseSelectDetailsBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			ArrayList listBO = new ArrayList();
			listBO = cmDAO.caseSelectDetailsPenalty45DAO(_caseTxnId);
			return (listBO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList caseSelectPenalty70DetailsBO(String _caseTxnId) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		String str = "";
		try {
			logger.info("Inside (caseSelectDetailsBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			ArrayList listBO = new ArrayList();
			listBO = cmDAO.caseSelectDetailsPenalty70DAO(_caseTxnId);
			return (listBO);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList caseRetrieveCommentsBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (caseRetrieveCommentsBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.caseRetrieveCommentsDAO(param);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList caseRetrieveAttachmentsBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (caseRetrieveAttachmentsBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.caseRetrieveAttachmentsDAO(param);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public boolean commentsUpdateBO(String param[], String _axnName, HashMap map)
			throws Exception {

		// modified by shruti
		boolean flag = false;

		logger.debug("WE ARE IN BO Debug");
		try {
			logger.info("Inside (commentsUpdateBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();

			String caseactionmapsrno = cmDAO.getActionMapSrNo();
			String cmntssrno = cmDAO.getCaseCmntsSrNo();

			// String docsrno=cmDAO.getCaseDocSrNo();

			String param1[] = new String[10];
			param1[0] = caseactionmapsrno;
			param1[1] = param[0]; // Case Axn Txn Id
			param1[2] = param[1]; // Case Txn Id
			param1[3] = param[2]; // Case Action Type Id
			param1[4] = param[5];// remarks
			param1[5] = param[9];// response flag
			param1[6] = param[3];// created by
			param1[7] = param[11];// pay dec flag
			param1[8] = param[12];// auth dec flag
			param1[9] = param[16];

			String param2[] = new String[12];
			param2[0] = cmntssrno;
			param2[1] = param[1]; // Case Txn Id
			param2[2] = param[0]; // Case Axn Txn Id
			param2[3] = param[4];// comments
			param2[4] = param[3]; // Current User Id
			param2[5] = param[10];// action date
			param2[6] = param[5];// axn desc
			param2[7] = param[8];// //receive date
			param2[8] = param[2];// case action_id
			param2[9] = param[13];//proposed total recoverable amt
			param2[10] = param[14];//proposed recoverable stamp duty
			param2[11] = param[15];//proposed recoverable reg fee

			String param3[] = new String[10];
			param3[0] = caseactionmapsrno;
			param3[1] = param[0]; // Case Axn Txn Id
			param3[2] = param[1]; // Case Txn Id
			// param3[2] = param[7]; // Case Action Type Id
			param3[3] = param[2];
			// param3[4] = param[3]; // Current User Id
			param3[4] = param[5]; // Action Description
			// param3[6] = param[8]; // Action Receive Date
			param3[5] = param[9];// response flag
			param3[6] = param[3];// created_by
			param3[7] = param[11];// pay dec flag
			param3[8] = param[12];// auth dec flag
			param3[9] =  param[16];//rrc no

			String param4[] = new String[3];//modified by shruti-15th july 2013;
			// param4[0] = param[7]; // Case Action Type Id
			param4[0] = param[2];
			param4[1] = param[17]; 
			param4[2]=param[1];// Case Txn Id
			//modified by shruti-26th august 2013
			
			
			String param5[] = new String[12];
			if (param[4] != "") {

				param5[0] = cmntssrno;
				param5[1] = param[1]; // Case Txn Id
				param5[2] = param[0]; // Case Axn Txn Id
				param5[3] = param[4]; // DR Comments
				param5[4] = param[3]; // Current User Id
				param5[5] = param[10];// action date
				param5[6] = param[5];// axn desc
				param5[7] = param[8];// //receive date
				param5[8] = param[2];// case action id
				param5[9] = param[13];//proposed recoverable total amt
				param5[10] = param[14];//proposed recoverable stamp duty
				param5[11] = param[15];//proposed recoverable reg fee

			} else
				param5 = null;

			String param6[] = new String[3];
			param6[0] = param[6]; // Final Case Comments
			param6[1] = param[2]; // Case Action Type Id
			param6[2] = param[1]; // Case Txn Id

			String param7[] = new String[3];
			// param7[0]=docsrno;
			param7[0] = param[0];
			param7[1] = param[1];
			param7[2] = param[3];

			if (_axnName.equalsIgnoreCase("drCommentsUpdate")) {
				return (cmDAO.commentsUpdateDAO(param1, param2, param7, map));

			} else if (_axnName.equalsIgnoreCase("issueNotice")) {
				return (cmDAO.issueNoticeDAO(param3, param4, param5, param7,
						map));
			} else if (_axnName.equalsIgnoreCase("closeCase")) {
				return (cmDAO.closeCaseDAO(param6, param1, param2, param7, map));
			}
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	
	
	//added by shruti-15th july 2013
	public boolean getPaidAmtUpdateBO(String param[])
	throws Exception {

		//boolean flag = false;
		logger.debug("WE ARE IN BO Debug");
		try {
			logger.info("Inside (commentsUpdateBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			return cmDAO.getPaidAmtUpdateDAO(param);
} catch (Exception e) {
	logger.error(e);
	return false;
}
}




	//end

	//added by shruti-refund cases
	public boolean commentsUpdateBO1(String param[], String _axnName, HashMap map)
	throws Exception {

// modified by shruti
boolean flag = false;

logger.debug("WE ARE IN BO Debug");
try {
	logger.info("Inside (commentsUpdateBO) method");
	CaseMonDAO cmDAO = new CaseMonDAO();

	String caseactionmapsrno = cmDAO.getActionMapSrNo();
	String cmntssrno = cmDAO.getCaseCmntsSrNo();

	// String docsrno=cmDAO.getCaseDocSrNo();

	String param1[] = new String[10];
	param1[0] = caseactionmapsrno;
	param1[1] = param[0]; // Case Axn Txn Id
	param1[2] = param[1]; // Case Txn Id
	param1[3] = param[2]; // Case Action Type Id
	param1[4] = param[5];// remarks
	param1[5] = param[9];// response flag
	param1[6] = param[3];// created by
	param1[7] = param[11];// pay dec flag
	param1[8] = param[12];// auth dec flag
	param1[9]="-";

	String param2[] = new String[12];
	param2[0] = cmntssrno;
	param2[1] = param[1]; // Case Txn Id
	param2[2] = param[0]; // Case Axn Txn Id
	param2[3] = param[4];// comments
	param2[4] = param[3]; // Current User Id
	param2[5] = param[10];// action date
	param2[6] = param[5];// axn desc
	param2[7] = param[8];// //receive date
	param2[8] = param[2];// case action_id
	param2[9] = param[13];//proposed total recoverable amt
	param2[10] = param[14];//proposed recoverable stamp duty
	param2[11] = param[15];//proposed recoverable reg fee
	
	String param3[] = new String[10];
	param3[0] = caseactionmapsrno;
	param3[1] = param[0]; // Case Axn Txn Id
	param3[2] = param[1]; // Case Txn Id
	// param3[2] = param[7]; // Case Action Type Id
	param3[3] = param[2];
	// param3[4] = param[3]; // Current User Id
	param3[4] = param[5]; // Action Description
	// param3[6] = param[8]; // Action Receive Date
	param3[5] = param[9];// response flag
	param3[6] = param[3];// created_by
	param3[7] = param[11];// pay dec flag
	param3[8] = param[12];// auth dec flag
	param3[9]="-";

	String param4[] = new String[2];
	// param4[0] = param[7]; // Case Action Type Id
	param4[0] = param[2];
	param4[1] = param[1]; // Case Txn Id
	
	
	
	
	
	String param5[] = new String[12];
	if (param[4] != "") {

		param5[0] = cmntssrno;
		param5[1] = param[1]; // Case Txn Id
		param5[2] = param[0]; // Case Axn Txn Id
		param5[3] = param[4]; // DR Comments
		param5[4] = param[3]; // Current User Id
		param5[5] = param[10];// action date
		param5[6] = param[5];// axn desc
		param5[7] = param[8];// //receive date
		param5[8] = param[2];// case action id
		param5[9] = param[13];//proposed recoverable total amt
		param5[10] = param[14];//proposed recoverable stamp duty
		param5[11] = param[15];//proposed recoverable reg fee

	} else
		param5 = null;

	String param6[] = new String[6];
	param6[0] = param[6]; // Final Case Comments
	param6[1] = param[2]; // Case Action Type Id
	param6[2]=param[16];//refund amount
	param6[3]=param[17];//refund bill no
	param6[4]=param[18];//refund bill date
	param6[5] = param[1]; // Case Txn Id

	String param7[] = new String[3];
	// param7[0]=docsrno;
	param7[0] = param[0];
	param7[1] = param[1];
	param7[2] = param[3];
	
	String param8[]=new String[1];
	param8[0]=param[1];

	if (_axnName.equalsIgnoreCase("drCommentsUpdate")) {
		return (cmDAO.commentsUpdateDAO(param1, param2, param7, map));

	} else if (_axnName.equalsIgnoreCase("issueNotice")) {
		return (cmDAO.issueNoticeDAO1(param3, param4, param5, param7,
				map));
	} else if (_axnName.equalsIgnoreCase("closeCase")) {
		return (cmDAO.closeCaseDAO1(param6, param1, param2, param7, map,param8));
	}
	return false;
} catch (Exception e) {
	logger.error(e);
	return false;
}
}
	//end
	//added by shruti-penalty cases-22nd july 2013
	public boolean commentsUpdatePenalty45BO(String param[], String _axnName, HashMap map)
	throws Exception {

// modified by shruti
boolean flag = false;

logger.debug("WE ARE IN BO Debug");
try {
	logger.info("Inside (commentsUpdateBO) method");
	CaseMonDAO cmDAO = new CaseMonDAO();

	String caseactionmapsrno = cmDAO.getActionMapSrNo();
	String cmntssrno = cmDAO.getCaseCmntsSrNo();

	// String docsrno=cmDAO.getCaseDocSrNo();

	String param1[] = new String[10];
	param1[0] = caseactionmapsrno;
	param1[1] = param[0]; // Case Axn Txn Id
	param1[2] = param[1]; // Case Txn Id
	param1[3] = param[2]; // Case Action Type Id
	param1[4] = param[5];// remarks
	param1[5] = param[9];// response flag
	param1[6] = param[3];// created by
	param1[7] = param[11];// pay dec flag
	param1[8] = param[12];// auth dec flag
	param1[9] = "-";//rrc

	String param2[] = new String[12];
	param2[0] = cmntssrno;
	param2[1] = param[1]; // Case Txn Id
	param2[2] = param[0]; // Case Axn Txn Id
	param2[3] = param[4];// comments
	param2[4] = param[3]; // Current User Id
	param2[5] = param[10];// action date
	param2[6] = param[5];// axn desc
	param2[7] = param[8];// //receive date
	param2[8] = param[2];// case action_id
	param2[9] = param[13];//proposed total recoverable amt
	param2[10] = param[14];//proposed recoverable stamp duty
	param2[11] = param[15];//proposed recoverable reg fee
	
	String param3[] = new String[10];
	param3[0] = caseactionmapsrno;
	param3[1] = param[0]; // Case Axn Txn Id
	param3[2] = param[1]; // Case Txn Id
	// param3[2] = param[7]; // Case Action Type Id
	param3[3] = param[2];
	// param3[4] = param[3]; // Current User Id
	param3[4] = param[5]; // Action Description
	// param3[6] = param[8]; // Action Receive Date
	param3[5] = param[9];// response flag
	param3[6] = param[3];// created_by
	param3[7] = param[11];// pay dec flag
	param3[8] = param[12];// auth dec flag
	param3[9]="-";//rrc

	String param4[] = new String[2];
	// param4[0] = param[7]; // Case Action Type Id
	param4[0] = param[2];
	param4[1] = param[1]; // Case Txn Id
	
	
	
	
	
	String param5[] = new String[12];
	if (param[4] != "") {

		param5[0] = cmntssrno;
		param5[1] = param[1]; // Case Txn Id
		param5[2] = param[0]; // Case Axn Txn Id
		param5[3] = param[4]; // DR Comments
		param5[4] = param[3]; // Current User Id
		param5[5] = param[10];// action date
		param5[6] = param[5];// axn desc
		param5[7] = param[8];// //receive date
		param5[8] = param[2];// case action id
		param5[9] = param[13];//proposed recoverable total amt
		param5[10] = param[14];//proposed recoverable stamp duty
		param5[11] = param[15];//proposed recoverable reg fee

	} else
		param5 = null;

	String param6[] = new String[6];
	param6[0] = param[6]; // Final Case Comments
	param6[1] = param[2]; // Case Action Type Id
	param6[2]=param[16];//refund amount
	param6[3]=param[17];//refund bill no
	param6[4]=param[18];//refund bill date
	param6[5] = param[1]; // Case Txn Id

	String param7[] = new String[3];
	// param7[0]=docsrno;
	param7[0] = param[0];
	param7[1] = param[1];
	param7[2] = param[3];
	
	String param8[]=new String[1];
	param8[0]=param[1];

	if (_axnName.equalsIgnoreCase("drCommentsUpdate")) {
		return (cmDAO.commentsUpdateDAO(param1, param2, param7, map));

	} else if (_axnName.equalsIgnoreCase("issueNotice")) {
		return (cmDAO.issueNoticePenalty45DAO(param3, param4, param5, param7,
				map));
	} else if (_axnName.equalsIgnoreCase("closeCase")) {
		return (cmDAO.closeCasePenalty45DAO(param6, param1, param2, param7, map,param8));
	}
	return false;
} catch (Exception e) {
	logger.error(e);
	return false;
}
}
	//end
	
	//added by shruti-penalty cases-22nd july 2013
	public boolean commentsUpdatePenalty70BO(String param[], String _axnName, HashMap map)
	throws Exception {

// modified by shruti
boolean flag = false;

logger.debug("WE ARE IN BO Debug");
try {
	logger.info("Inside (commentsUpdateBO) method");
	CaseMonDAO cmDAO = new CaseMonDAO();

	String caseactionmapsrno = cmDAO.getActionMapSrNo();
	String cmntssrno = cmDAO.getCaseCmntsSrNo();

	// String docsrno=cmDAO.getCaseDocSrNo();

	String param1[] = new String[10];
	param1[0] = caseactionmapsrno;
	param1[1] = param[0]; // Case Axn Txn Id
	param1[2] = param[1]; // Case Txn Id
	param1[3] = param[2]; // Case Action Type Id
	param1[4] = param[5];// remarks
	param1[5] = param[9];// response flag
	param1[6] = param[3];// created by
	param1[7] = param[11];// pay dec flag
	param1[8] = param[12];// auth dec flag
	param1[9]="-";

	String param2[] = new String[12];
	param2[0] = cmntssrno;
	param2[1] = param[1]; // Case Txn Id
	param2[2] = param[0]; // Case Axn Txn Id
	param2[3] = param[4];// comments
	param2[4] = param[3]; // Current User Id
	param2[5] = param[10];// action date
	param2[6] = param[5];// axn desc
	param2[7] = param[8];// //receive date
	param2[8] = param[2];// case action_id
	param2[9] = param[13];//proposed total recoverable amt
	param2[10] = param[14];//proposed recoverable stamp duty
	param2[11] = param[15];//proposed recoverable reg fee
	
	String param3[] = new String[10];
	param3[0] = caseactionmapsrno;
	param3[1] = param[0]; // Case Axn Txn Id
	param3[2] = param[1]; // Case Txn Id
	// param3[2] = param[7]; // Case Action Type Id
	param3[3] = param[2];
	// param3[4] = param[3]; // Current User Id
	param3[4] = param[5]; // Action Description
	// param3[6] = param[8]; // Action Receive Date
	param3[5] = param[9];// response flag
	param3[6] = param[3];// created_by
	param3[7] = param[11];// pay dec flag
	param3[8] = param[12];// auth dec flag
	param3[9] ="-";//rrc

	String param4[] = new String[2];
	// param4[0] = param[7]; // Case Action Type Id
	param4[0] = param[2];
	param4[1] = param[1]; // Case Txn Id
	
	
	String param5[] = new String[12];
	if (param[4] != "") {

		param5[0] = cmntssrno;
		param5[1] = param[1]; // Case Txn Id
		param5[2] = param[0]; // Case Axn Txn Id
		param5[3] = param[4]; // DR Comments
		param5[4] = param[3]; // Current User Id
		param5[5] = param[10];// action date
		param5[6] = param[5];// axn desc
		param5[7] = param[8];// //receive date
		param5[8] = param[2];// case action id
		param5[9] = param[13];//proposed recoverable total amt
		param5[10] = param[14];//proposed recoverable stamp duty
		param5[11] = param[15];//proposed recoverable reg fee

	} else
		param5 = null;

	String param6[] = new String[6];
	param6[0] = param[6]; // Final Case Comments
	param6[1] = param[2]; // Case Action Type Id
	param6[2]=param[16];//refund amount
	param6[3]=param[17];//refund bill no
	param6[4]=param[18];//refund bill date
	param6[5] = param[1]; // Case Txn Id

	String param7[] = new String[3];
	// param7[0]=docsrno;
	param7[0] = param[0];
	param7[1] = param[1];
	param7[2] = param[3];
	
	String param8[]=new String[1];
	param8[0]=param[1];

	if (_axnName.equalsIgnoreCase("drCommentsUpdate")) {
		return (cmDAO.commentsUpdateDAO(param1, param2, param7, map));

	} else if (_axnName.equalsIgnoreCase("issueNotice")) {
		return (cmDAO.issueNoticePenalty70DAO(param3, param4, param5, param7,
				map));
	} else if (_axnName.equalsIgnoreCase("closeCase")) {
		return (cmDAO.closeCasePenalty70DAO(param6, param1, param2, param7, map,param8));
	}
	return false;
} catch (Exception e) {
	logger.error(e);
	return false;
}
}
	//end
	
	//adedd by shruti-18th july 2013-others case
	public boolean commentsUpdateBO2(String param[], String _axnName, HashMap map)
	throws Exception {

// modified by shruti
boolean flag = false;

logger.debug("WE ARE IN BO Debug");
try {
	logger.info("Inside (commentsUpdateBO) method");
	CaseMonDAO cmDAO = new CaseMonDAO();

	String caseactionmapsrno = cmDAO.getActionMapSrNo();
	String cmntssrno = cmDAO.getCaseCmntsSrNo();

	// String docsrno=cmDAO.getCaseDocSrNo();

	String param1[] = new String[10];
	param1[0] = caseactionmapsrno;
	param1[1] = param[0]; // Case Axn Txn Id
	param1[2] = param[1]; // Case Txn Id
	param1[3] = param[2]; // Case Action Type Id
	param1[4] = param[5];// remarks
	param1[5] = param[9];// response flag
	param1[6] = param[3];// created by
	param1[7] = param[11];// pay dec flag
	param1[8] = param[12];// auth dec flag
	param1[9]="-";

	String param2[] = new String[12];
	param2[0] = cmntssrno;
	param2[1] = param[1]; // Case Txn Id
	param2[2] = param[0]; // Case Axn Txn Id
	param2[3] = param[4];// comments
	param2[4] = param[3]; // Current User Id
	param2[5] = param[10];// action date
	param2[6] = param[5];// axn desc
	param2[7] = param[8];// //receive date
	param2[8] = param[2];// case action_id
	param2[9] = param[13];//proposed total recoverable amt
	param2[10] = param[14];//proposed recoverable stamp duty
	param2[11] = param[15];//proposed recoverable reg fee
	
	String param3[] = new String[10];
	param3[0] = caseactionmapsrno;
	param3[1] = param[0]; // Case Axn Txn Id
	param3[2] = param[1]; // Case Txn Id
	// param3[2] = param[7]; // Case Action Type Id
	param3[3] = param[2];
	// param3[4] = param[3]; // Current User Id
	param3[4] = param[5]; // Action Description
	// param3[6] = param[8]; // Action Receive Date
	param3[5] = param[9];// response flag
	param3[6] = param[3];// created_by
	param3[7] = param[11];// pay dec flag
	param3[8] = param[12];// auth dec flag
	param3[9] = "-";

	String param4[] = new String[2];
	// param4[0] = param[7]; // Case Action Type Id
	param4[0] = param[2];
	param4[1] = param[1]; // Case Txn Id
	
	
	
	
	
	String param5[] = new String[12];
	if (param[4] != "") {

		param5[0] = cmntssrno;
		param5[1] = param[1]; // Case Txn Id
		param5[2] = param[0]; // Case Axn Txn Id
		param5[3] = param[4]; // DR Comments
		param5[4] = param[3]; // Current User Id
		param5[5] = param[10];// action date
		param5[6] = param[5];// axn desc
		param5[7] = param[8];// //receive date
		param5[8] = param[2];// case action id
		param5[9] = param[13];//proposed recoverable total amt
		param5[10] = param[14];//proposed recoverable stamp duty
		param5[11] = param[15];//proposed recoverable reg fee

	} else
		param5 = null;

	String param6[] = new String[3];
	param6[0] = param[6]; // Final Case Comments
	//param6[1] = param[2]; // Case Action Type Id
	
	//modified by shruti
	param6[1]="CASE_011";
	//end
	
	
	//param6[2]=param[16];//refund amount
	//param6[3]=param[17];//refund bill no
	//param6[4]=param[18];//refund bill date
	param6[2] = param[1]; // Case Txn Id

	String param7[] = new String[3];
	// param7[0]=docsrno;
	param7[0] = param[0];
	param7[1] = param[1];
	param7[2] = param[3];
	
	String param8[]=new String[1];
	param8[0]=param[1];

	if (_axnName.equalsIgnoreCase("drCommentsUpdate")) {
		return (cmDAO.commentsUpdateDAO(param1, param2, param7, map));

	} else if (_axnName.equalsIgnoreCase("issueNotice")) {
		return (cmDAO.issueNoticeDAO1(param3, param4, param5, param7,
				map));
	} else if (_axnName.equalsIgnoreCase("closeCase")) {
		return (cmDAO.closeCaseDAO2(param6, param1, param2, param7, map,param8));
	}
	return false;
} catch (Exception e) {
	logger.error(e);
	return false;
}
}
	//end

	public ArrayList nxtActionBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (nxtActionBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.nxtActionDAO(param);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// added by shruti
	public ArrayList nxtActionBO1(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (nxtActionBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.nxtActionDAO1(param);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// end

	
	public ArrayList nxtActionBO2(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (nxtActionBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.nxtActionDAO2(param);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// end
	public ArrayList nxtActionBO3(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (nxtActionBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.nxtActionDAO3(param);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList licenseDetailsBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		try {
			logger.info("Inside (addComplianceBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();

			return cmDAO.licenseDetailsDAO(param);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList searchListUpdateBO(String param[], String sql)
			throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (searchListUpdateBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.searchListUpdateDAO(param, sql);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList searchListTimeBarredBO(String param[], String sql)
			throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (searchListUpdateBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.searchListTimeBarredDAO(param, sql);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public ArrayList searchListRefundBO(String param[], String sql)
			throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (searchListUpdateBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.searchListTimeBarredDAO(param, sql);
			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList searchListViewBO(String param[]) throws Exception {
		logger.debug("WE ARE IN BO Debug");
		ArrayList listBo = new ArrayList();
		try {
			logger.info("Inside (searchListViewBO) method");
			CaseMonDAO cmDAO = new CaseMonDAO();
			listBo = cmDAO.searchListViewDAO(param);

			return (listBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/***************************************************************************
	 * Method : GetCaseIds Arguments : From Date and ToDate Return : ArrayList
	 * Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseIds(String _fromDate, String _toDate,
			String _caseType, String _status) throws NullPointerException,
			Exception {

		ArrayList caseList = null;
		try {
			ArrayList getCaseIDS = new ArrayList();
			ArrayList getCaseId = null;
			getCaseIDS = caseDao.getCaseIds(_fromDate, _toDate, _caseType,
					_status);
			if (getCaseIDS != null) {
				caseList = new ArrayList();
				for (int i = 0; i < getCaseIDS.size(); i++) {
					getCaseId = new ArrayList();
					getCaseId = (ArrayList) getCaseIDS.get(i);
					if (getCaseId.size() > 0) {
						CaseMonDTO caseDto = new CaseMonDTO();
						caseDto.setCaseID((String) getCaseId.get(0));
						caseDto.setCaseCreatedDate((String) getCaseId.get(1));
						caseList.add(caseDto);
					} else {
						caseList.add(null);
					}
				}
			} else {
				caseList.add(null);
			}

		} catch (NullPointerException ne) {
			logger.error("Nullpointer Exception at getCaseIds In caseMonBO  : "
					+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIds in CaseMonBO  : " + e);
		}
		return caseList;
	}

	/***************************************************************************
	 * Method : GetCaseId Details Arguments : Case ID Return : ArrayList
	 * Exception : NullPointerException,Exception
	 **************************************************************************/
	public ArrayList getCaseIdDetails(String _caseId)
			throws NullPointerException, Exception {
		ArrayList caseDetails = null;
		try {
			ArrayList getCaseDetails = new ArrayList();
			ArrayList getCaseIdDetails = null;
			getCaseDetails = caseDao.getCaseIdDetails(_caseId);
			if (getCaseDetails != null) {
				caseDetails = new ArrayList();
				getCaseIdDetails = new ArrayList();
				getCaseIdDetails = (ArrayList) getCaseDetails.get(0);
				if (getCaseIdDetails.size() > 0) {
					CaseMonDTO caseDto = new CaseMonDTO();
					caseDto.setCaseID((String) getCaseIdDetails.get(0));
					caseDto
							.setCaseCreatedDate((String) getCaseIdDetails
									.get(1));
					caseDto.setCaseStampId((String) getCaseIdDetails.get(2));
					caseDto.setCaseStampAmt(caseDao.getCaseEstampAmt(caseDto
							.getCaseStampId()));
					caseDto.setCasePenalty((String) getCaseIdDetails.get(3));
					caseDto.setCaseComments((String) getCaseIdDetails.get(4));
					caseDetails.add(caseDto);
				}
			}

		} catch (NullPointerException ne) {
			logger
					.error("Nullpointer Exception at getCaseIdDetails In caseMonBO  : "
							+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIdDetails in CaseMonBO  : " + e);
		}
		return caseDetails;
	}

	// added by shruti

	public boolean updateActionMap(CaseMonDTO cmDTO, String userId) {
		// CaseMonBO caseBo = new CaseMonBO();
		boolean flag = false;
		try {

			flag = caseDao.updateActionMap(cmDTO, userId);

		} catch (NullPointerException ne) {
			logger
					.error("NullPointer Exception at update case action map in CaseMonBD : "
							+ ne);
		} catch (Exception e) {
			logger
					.error("Exception at update case action map in CaseMonBD  :  "
							+ e);
		}

		return flag;
	}

	// end

	public ArrayList getNxtActnDtls(String lastaction, String caseTxnId) {
		ArrayList dtls = null;
		try {
			ArrayList dtls1 = new ArrayList();
			ArrayList dtls2 = null;

			dtls1 = caseDao.getNxtActnDtls(lastaction, caseTxnId);
			if (dtls1 != null) {
				dtls = new ArrayList();
				dtls2 = (ArrayList) dtls1.get(0);
				if (dtls2.size() > 0) {
					CaseMonDTO caseDto = new CaseMonDTO();
					caseDto.setLastActionId((String) dtls2.get(0));
					caseDto.setPartyresponsedb((String) dtls2.get(1));
					caseDto.setAuthDec((String) dtls2.get(2));
					dtls.add(caseDto);
				}
			}

		} catch (NullPointerException ne) {
			logger
					.error("NullPointer Exception at update case action map in CaseMonBD : "
							+ ne);
		} catch (Exception e) {
			logger
					.error("Exception at update case action map in CaseMonBD  :  "
							+ e);
		}
		return dtls;
	}

	public String insertCasePymtTxnDtls(CaseMonDTO cmDTO, String userId) {
		String pymttxnsrid = "";

		try {

			pymttxnsrid = caseDao.insertCasePymtTxnDtls(cmDTO, userId);

		} catch (NullPointerException ne) {
			logger
					.error("NullPointer Exception at update case action map in CaseMonBD : "
							+ ne);
		} catch (Exception e) {
			logger
					.error("Exception at update case action map in CaseMonBD  :  "
							+ e);
		}

		return pymttxnsrid;
	}

	// end

	public String getPaidAmt(CaseMonDTO cmDTO, String pymtsrid) {
		String paidAmt = "";
		try {
			paidAmt = caseDao.getPaidAmt(cmDTO, pymtsrid);
		} catch (NullPointerException ne) {
			logger
					.error("NullPointer Exception at update case action map in CaseMonBD : "
							+ ne);
		} catch (Exception e) {
			logger
					.error("Exception at update case action map in CaseMonBD  :  "
							+ e);
		}
		return paidAmt;
	}

	public ArrayList getCasePymtDetails(String _caseId)
			throws NullPointerException, Exception {

		ArrayList caseDetails = null;
		ArrayList getCaseDetails = new ArrayList();
		try {
			
			ArrayList getCaseIdDetails = null;
			getCaseDetails = caseDao.getCasePaymtDetailsDAO(_caseId);
			if (getCaseDetails != null) {
				caseDetails = new ArrayList();
				getCaseIdDetails = new ArrayList();
				getCaseIdDetails = (ArrayList) getCaseDetails.get(0);
				if (getCaseIdDetails.size() > 0) {
					CaseMonDTO caseDto = new CaseMonDTO();
					caseDto.setTotalPaidAmt((String) getCaseIdDetails.get(0));
					caseDetails.add(caseDto);
				}
			}

		} catch (NullPointerException ne) {
			logger
					.error("Nullpointer Exception at getCaseIdDetails In caseMonBO  : "
							+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIdDetails in CaseMonBO  : " + e);
		}
		return caseDetails;
	}
	
	//added by shruti---21 oct 2014
	public ArrayList getCasePymtDetailsEstamp(String _caseId)
	throws NullPointerException, Exception {

ArrayList caseDetails = null;
ArrayList getCaseDetails = new ArrayList();
try {
	
	ArrayList getCaseIdDetails = null;
	getCaseDetails = caseDao.getCasePaymtDetailsEstampDAO(_caseId);
	if (getCaseDetails != null) {
		caseDetails = new ArrayList();
		getCaseIdDetails = new ArrayList();
		getCaseIdDetails = (ArrayList) getCaseDetails.get(0);
		if (getCaseIdDetails.size() > 0) {
			CaseMonDTO caseDto = new CaseMonDTO();
			caseDto.setTotalRecAmt((String) getCaseIdDetails.get(0));
			caseDetails.add(caseDto);
		}
	}

} catch (NullPointerException ne) {
	logger
			.error("Nullpointer Exception at getCaseIdDetails In caseMonBO  : "
					+ ne);
} catch (Exception e) {
	logger.error("Exception at getCaseIdDetails in CaseMonBO  : " + e);
}
return caseDetails;
}
	//end
	
	//added by shruti---6 oct 2014
	public ArrayList getRegDocDetails(String _caseId,CaseMonDTO cmDTO)
	throws NullPointerException, Exception {

		ArrayList caseDetails = null;
		ArrayList getCaseDetails = new ArrayList();
		try {
			
			ArrayList getCaseIdDetails = null;
			getCaseDetails = caseDao.getRegDocDetailsDAO(_caseId,cmDTO);
			if (getCaseDetails != null) {
				caseDetails = new ArrayList();
				getCaseIdDetails = new ArrayList();
				getCaseIdDetails = (ArrayList) getCaseDetails.get(0);
				if (getCaseIdDetails.size() > 0) {
					CaseMonDTO caseDto = new CaseMonDTO();
					caseDto.setStampDuty((String) getCaseIdDetails.get(0));
					caseDto.setMrktVal((String) getCaseIdDetails.get(1));
					caseDetails.add(caseDto);
				}
			}
		
			
		} catch (NullPointerException ne) {
			logger
					.error("Nullpointer Exception at getRegDocDetails In caseMonBO  : "
							+ ne);
		} catch (Exception e) {
			logger.error("Exception at getRegDocDetails in CaseMonBO  : " + e);
		}
		return caseDetails;
}
	//end
	
	//added by shruti-15th july 2013
	public ArrayList getPendingPymtDetails(String _caseId)
	throws NullPointerException, Exception {

		ArrayList caseDetails = null;
		ArrayList getCaseDetails = new ArrayList();
		try {
			
			ArrayList getCaseIdDetails = null;
			getCaseDetails = caseDao.getPendingPaymtDetailsDAO(_caseId);

} catch (NullPointerException ne) {
	logger
			.error("Nullpointer Exception at getCaseIdDetails In caseMonBO  : "
					+ ne);
} catch (Exception e) {
	logger.error("Exception at getCaseIdDetails in CaseMonBO  : " + e);
}

return getCaseDetails;
}
	//end
	//adedd by shruti-18th july 2013
	public ArrayList getPendingPymtOthers(String _caseId)
	throws NullPointerException, Exception {

		ArrayList caseDetails = null;
		ArrayList getCaseDetails = new ArrayList();
		try {
			
			ArrayList getCaseIdDetails = null;
			getCaseDetails = caseDao.getPendingPaymtOthersDAO(_caseId);

} catch (NullPointerException ne) {
	logger
			.error("Nullpointer Exception at getCaseIdDetails In caseMonBO  : "
					+ ne);
} catch (Exception e) {
	logger.error("Exception at getCaseIdDetails in CaseMonBO  : " + e);
}

return getCaseDetails;
}
	//end


	public ArrayList getRevisedRecoverableAmtDetails(String[] param)
			throws NullPointerException, Exception {

		ArrayList caseDetails = null;
		try {
			ArrayList getCaseDetails = new ArrayList();
			ArrayList getCaseIdDetails = null;
			getCaseDetails = caseDao.getRevisedRecoverableAmtDetails(param);
			if (getCaseDetails != null) {
				caseDetails = new ArrayList();
				//getCaseIdDetails = new ArrayList();
				//getCaseIdDetails = (ArrayList) getCaseDetails.get(0);
				logger.info("SIZE OF ARRAYLIST>>>>>>>>>>>>"+getCaseDetails.size());
				if (getCaseDetails.size()>0) {
					getCaseIdDetails = (ArrayList) getCaseDetails.get(0);
					CaseMonDTO caseDto = new CaseMonDTO();
					caseDto.setPropTotalRecAmt((String) getCaseIdDetails.get(0));
					caseDto.setPropRecStampAmt((String) getCaseIdDetails.get(1));
					caseDto.setPropRecRegAmt((String) getCaseIdDetails.get(2));
					caseDetails.add(caseDto);
				}
			}

		} catch (NullPointerException ne) {
			logger
					.error("Nullpointer Exception at getCaseIdDetails In caseMonBO  : "
							+ ne);
		} catch (Exception e) {
			logger.error("Exception at getCaseIdDetails in CaseMonBO  : " + e);
		}
		return caseDetails;
	}

	//RRC
	public ArrayList getRRCDetails(CaseMonDTO dto)
	throws NullPointerException, Exception {

ArrayList caseDetails = null;
try {
	caseDetails = caseDao.getRRCDetails(dto);
	/*if (getCaseDetails != null) {
		caseDetails = new ArrayList();
		getCaseIdDetails = new ArrayList();
		getCaseIdDetails = (ArrayList) getCaseDetails.get(0);
		if (getCaseIdDetails.size() > 0) {
			CaseMonDTO caseDto = new CaseMonDTO();
			caseDto.setPropTotalRecAmt((String) getCaseIdDetails.get(0));
			caseDto.setPropRecStampAmt((String) getCaseIdDetails.get(1));
			caseDto.setPropRecRegAmt((String) getCaseIdDetails.get(2));
			caseDetails.add(caseDto);
		}
	}*/

} catch (NullPointerException ne) {
	logger
			.error("Nullpointer Exception at getCaseIdDetails In caseMonBO  : "
					+ ne);
} catch (Exception e) {
	logger.error("Exception at getCaseIdDetails in CaseMonBO  : " + e);
}
return caseDetails;
}

	
	public String getRRCConfigParam()
	{
		String mnthparam="";
		try
		{
			mnthparam=caseDao.getRRCConfigParam();
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
			mnthchk=caseDao.getRRCMonthCriteria(dto);
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
			rno=caseDao.updateRRCDtls(dto);
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
			caseId=caseDao.getCaseId(dto);
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}
		return caseId;
		
	}
	public String getrrcId(CaseMonDTO dto)
	{
		String caseId="";
		try
		{
			caseId=caseDao.getRRCId(dto);
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.error("Exception in getAuditDetails():" + e);
		}
		return caseId;
		
	}
	
	
	//added by shruti-28th august 2013
	public ArrayList getSalutationDetails(CaseMonDTO dto)
	throws NullPointerException, Exception {

	ArrayList caseDetails = null;
	try {
		ArrayList getCaseDetails = new ArrayList();
		ArrayList getCaseIdDetails = null;
	
		
		getCaseDetails = caseDao.getSalutationDetails(dto);
		if (getCaseDetails != null) 
		{
			caseDetails = new ArrayList();
			logger.info("SIZE OF ARRAYLIST>>>>>>>>>>>>"+getCaseDetails.size());
			for(int i=0;i<getCaseDetails.size();i++)
			{
				getCaseIdDetails = (ArrayList) getCaseDetails.get(i);
				CaseMonDTO caseDto = new CaseMonDTO();
				caseDto.setPartyName((String) getCaseIdDetails.get(0));
				if(caseDto.getPartyName()==""||caseDto.getPartyName()==null)
				{
					caseDto.setPartyName((String) getCaseIdDetails.get(1));
				}
				caseDto.setPartyAddress((String) getCaseIdDetails.get(2));
				caseDto.setSrName((String) getCaseIdDetails.get(3));
				caseDto.setDeedInsName((String) getCaseIdDetails.get(4));
				caseDto.setRegDate((String) getCaseIdDetails.get(5));
				caseDto.setCurrentDate((String) getCaseIdDetails.get(6));
				caseDto.setOfficeName((String) getCaseIdDetails.get(7));
				caseDetails.add(caseDto);
				
			}
			/*if (getCaseDetails.size()>0) 
			{
				getCaseIdDetails = (ArrayList) getCaseDetails.get(0);
				CaseMonDTO caseDto = new CaseMonDTO();
				caseDto.setPropTotalRecAmt((String) getCaseIdDetails.get(0));
				caseDto.setPropRecStampAmt((String) getCaseIdDetails.get(1));
				caseDto.setPropRecRegAmt((String) getCaseIdDetails.get(2));
				caseDetails.add(caseDto);
			}*/
		}

} catch (NullPointerException ne) {
	logger
			.error("Nullpointer Exception at getSalutationDetails In caseMonBO  : "
					+ ne);
} catch (Exception e) {
	logger.error("Exception at getSalutationDetails in CaseMonBO  : " + e);
}
return caseDetails;
}
//end
	
	
	//added by shruti---29 ot 2014
	public ArrayList getRevSalutationDetails(CaseMonDTO dto)
	throws NullPointerException, Exception {

	ArrayList caseDetails = null;
	try {
		ArrayList getCaseDetails = new ArrayList();
		ArrayList getCaseIdDetails = null;
	
		
		getCaseDetails = caseDao.getRevSalutationDetails(dto);
		if (getCaseDetails != null) 
		{
			caseDetails = new ArrayList();
			logger.info("SIZE OF ARRAYLIST>>>>>>>>>>>>"+getCaseDetails.size());
			for(int i=0;i<getCaseDetails.size();i++)
			{
				getCaseIdDetails = (ArrayList) getCaseDetails.get(i);
				CaseMonDTO caseDto = new CaseMonDTO();
				caseDto.setPartyName((String) getCaseIdDetails.get(0));
				if(caseDto.getPartyName()==""||caseDto.getPartyName()==null)
				{
					caseDto.setPartyName((String) getCaseIdDetails.get(1));
				}
				caseDto.setPartyAddress((String) getCaseIdDetails.get(2));
				caseDto.setSrName((String) getCaseIdDetails.get(3));
				caseDto.setDeedInsName((String) getCaseIdDetails.get(4));
				caseDto.setRegDate((String) getCaseIdDetails.get(5));
				caseDto.setCurrentDate((String) getCaseIdDetails.get(6));
				caseDto.setOfficeName((String) getCaseIdDetails.get(7));
				caseDto.setRegNo((String) getCaseIdDetails.get(8));
				caseDto.setCaseHearDate((String) getCaseIdDetails.get(9));
				caseDetails.add(caseDto);
				
			}
		}

} catch (NullPointerException ne) {
	logger
			.error("Nullpointer Exception at getSalutationDetails In caseMonBO  : "
					+ ne);
} catch (Exception e) {
	logger.error("Exception at getSalutationDetails in CaseMonBO  : " + e);
}
return caseDetails;
}
//end
	
	//added-28th oct 2013
	public ArrayList getInternalUserDtls(String officeId,String language) throws Exception{
		ArrayList resultList =new ArrayList();
		
		try {
		resultList=caseDao.getInternalUserDtls(officeId,language);
		}catch (Exception e) {
			logger.error(e);	
		}
		 return resultList;
	}
	//end
	//added by shruti--6 sep 2014
	public String getOfficeTypeId(String officeId)
	{

		String officeTypeId = null;

		String arr[]={officeId};
		try{
			officeTypeId=caseDao.getOfficeTypeId(officeId);
	}
		catch(Exception ex)
		{
		ex.printStackTrace();
		}
		return officeTypeId;
	}
	//end
	//added by shruti----9 jan 2015
	public String getSectionHead(String caseId)
	{

		String officeTypeId = null;

		String arr[]={caseId};
		try{
			officeTypeId=caseDao.getSectionHead(caseId);
	}
		catch(Exception ex)
		{
		ex.printStackTrace();
		}
		return officeTypeId;
	}
}// Bo Close
