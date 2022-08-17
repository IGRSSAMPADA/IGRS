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
package com.wipro.igrs.documentsearch.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentDetails;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.CommonConstant;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.documentsearch.bd.DocumentSearchBD;
import com.wipro.igrs.documentsearch.bd.OldDocumentSearchBD;
import com.wipro.igrs.documentsearch.bo.DocumentSearchBO;
import com.wipro.igrs.documentsearch.dto.DocumentSearchDTO;
import com.wipro.igrs.documentsearch.dto.OldDocSearchDashboardDTO;
import com.wipro.igrs.documentsearch.dto.PartyDetailsDTO;
import com.wipro.igrs.documentsearch.form.DocumentSearchForm;
import com.wipro.igrs.newPropvaluation.bo.PropertyValuationBO;
import com.wipro.igrs.report.common.MarketTrendReportConstants;
import com.wipro.igrs.util.GUIDGenerator;

public class DocumentSearchBAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(DocumentSearchAction.class);

	String forwardPage = "";
	String noofyears = "";
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException, Exception {

		logger.info("we are in document search b action");
		DocumentSearchForm dform = (DocumentSearchForm) form;
		DocumentSearchDTO dsdto = dform.getDsearchdto();
		DocumentSearchBD bd = new DocumentSearchBD();
		DocumentSearchBO bo = new DocumentSearchBO();
		PartyDetailsDTO partydto = new PartyDetailsDTO();
		DocumentSearchDTO feeDsdto = new DocumentSearchDTO();
		PropertyValuationBO newPropBO = new PropertyValuationBO();

		String language = (String) session.getAttribute("languageLocale");
		String pageName = (String) request.getParameter("pageName");
		String userId = (String) (session.getAttribute("UserId").toString());
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		String officeId = (String) session.getAttribute("loggedToOffice");
		String dms = pr.getValue("ImageServerIP");
		String port = pr.getValue("port");
		String frwdName = request.getParameter("fwdName");

		dform.getDsearchdto().setDms(dms);
		dform.getDsearchdto().setPort(port);
		dform.setLanguage(language);
		dsdto.setUserTypeId(bo.chkUser(userId));
		if (dsdto.getUserTypeId().equalsIgnoreCase("I")) {
			ArrayList dtlList = (ArrayList) bo.getInternalUserDtls(officeId);
			dsdto.setParentOfficeId(officeId);
			for (int i = 0; i < dtlList.size(); i++) {
				ArrayList list = (ArrayList) dtlList.get(0);
				dsdto.setParentOfficeName((String) list.get(0));
				dsdto.setParentDistrictId((String) list.get(1));
				dsdto.setParentDistrictName((String) list.get(2));
			}
		} else if (dsdto.getUserTypeId().equalsIgnoreCase("2")) {
			dsdto.setParentDistrictName("NA");
			dsdto.setParentDistrictId("NA");
			dsdto.setParentOfficeId("NA");
			dsdto.setParentOfficeName("NA");
		} else {
			ArrayList dtlList = bo.getExternalUserDtls(userId);
			for (int i = 0; i < dtlList.size(); i++) {
				ArrayList list = (ArrayList) dtlList.get(0);
				dsdto.setParentDistrictName((String) list.get(0));
				dsdto.setParentDistrictId((String) list.get(1));
			}
			dsdto.setParentOfficeId("NA");
			dsdto.setParentOfficeName("NA");
		}

		if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("officeTypeB")) {
			dsdto = new DocumentSearchDTO();
			dsdto.setUserType("");
			forwardPage = "usertypeb";
			dsdto.setServiceType("FUN_063");
			ArrayList srchBList = bd.getSrchBResultList(userId);
			feeDsdto = bd.getOthersFeeDuty("FUN_063", "18", dsdto.getUserTypeId());
			dsdto.setTotalFee(feeDsdto.getTotalFee());
			if (srchBList.isEmpty()) {
				dform.setFromDate("");
				dform.setToDate("");
				logger.debug("m here in officeTypeB ---------------------------- ");
				ArrayList dist = bd.getDistrict(language);
				dsdto.setDistList(dist);
				ArrayList regDistList = bd.getRegsitrationDistrict(language);
				dsdto.setRegDistList(regDistList);
				ArrayList areaList = bd.getAreaType(language);
				dsdto.setAreaList(areaList);
				ArrayList fiscalYear = bd.getFinancialYear(language);
				dsdto.setFinancialYearList(fiscalYear);
				dsdto.setServiceType("FUN_063");
				dsdto.setSearchType("FUN_063");
				forwardPage = "usertypeb";
			} else if (srchBList != null || srchBList.size() > 0) {
				request.setAttribute("srchBList", srchBList);
				forwardPage = "searchBDashboard";
			} else {
				dform.setFromDate("");
				dform.setToDate("");

				dsdto = new DocumentSearchDTO();
				dsdto.setIsCompletePay(1);
				logger.debug("m here in officeTypeB ---------------------------- ");
				ArrayList dist = bd.getDistrict(language);
				ArrayList regDistList = bd.getRegsitrationDistrict(language);
				ArrayList areaList = bd.getAreaType(language);
				ArrayList fiscalYear = bd.getFinancialYear(language);
				dsdto.setAreaList(areaList);
				dsdto.setFinancialYearList(fiscalYear);
				dsdto.setDistList(dist);
				dsdto.setRegDistList(regDistList);
				dsdto.setServiceType("FUN_063");
				dsdto.setSearchType("FUN_063");
				forwardPage = "usertypeb";
			}
		}
		String newsrchB = request.getParameter("newUserSearchB");
		if (newsrchB != null && newsrchB.equals("true")) {

			dform.setFromDate("");
			dform.setToDate("");
			dsdto = new DocumentSearchDTO();
			dsdto.setIsCompletePay(1);
			logger.debug("m here in officeTypeB ---------------------------- ");
			ArrayList regDistList = bd.getRegsitrationDistrict(language);
			ArrayList areaList = bd.getAreaType(language);
			dsdto.setUserType("");
			dsdto.setAreaList(areaList);
			dsdto.setRegDistList(regDistList);
			ArrayList dist = bd.getDistrict(language);
			dsdto.setDistList(dist);
			ArrayList fiscalYear = bd.getFinancialYear(language);
			dsdto.setFinancialYearList(fiscalYear);
			dsdto.setServiceType("FUN_063");
			dsdto.setSearchType("FUN_063");
			feeDsdto = bd.getOthersFeeDuty("FUN_063", "18", dsdto.getUserTypeId());
			dsdto.setTotalFee(feeDsdto.getTotalFee());
			forwardPage = "usertypeb";
		}
		// PAYMENT INTEGRATION>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		if (dform.getDsearchdto().getActionName() != null && dform.getDsearchdto().getActionName().equals("paymentModule")) {
			logger.debug("in action payment call--- dform.getDsearchdto().getFormName()<>" + dform.getDsearchdto().getFormName());
			dform.getDsearchdto().setActionName(null);
			// on click of payment process for the first time
			if (dform.getDsearchdto().getFormName() != null && dform.getDsearchdto().getFormName().equals("docSearchTypeB")) {
				logger.debug("in action payment docSearchTypeB call---<>");
				dform.getDsearchdto().setToDate(dform.getToDate());
				dform.getDsearchdto().setFromDate(dform.getFromDate());
				session.setAttribute("user", "user");

				String functionId = dsdto.getServiceType();
				logger.info("UserId is ===============" + userId);
				logger.info("functionId is ===========" + functionId);

				feeDsdto = bd.getOthersFeeDuty(functionId, "19", dsdto.getUserTypeId());
				dsdto.setTotalFee(feeDsdto.getTotalFee());
				String txnid = bd.storeBTypeSearchNew(CommonConstant.PAYMENT_FLAG, dform.getDsearchdto(), dsdto, userId, functionId);
				String[] temp = new String[2];
				temp = txnid.split("~");
				String pkey = temp[0];
				String refid = temp[1];
				dsdto.setParentReferenceId(refid);
				dform.getDsearchdto().setRefId(refid);
				String amountTobePaid = "";
				ArrayList payeeDetl = bd.getPaymentList(refid, functionId);
				if (payeeDetl != null && payeeDetl.size() > 0) {
					float paidAmount = 0;
					float totalAmount = 0;
					float amtToBePaid = 0;
					for (int i = 0; i < payeeDetl.size(); i++) {
						DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
						float paidAmt = ldto.getPaidAmount();
						paidAmount = paidAmount + paidAmt;
						totalAmount = Float.parseFloat(ldto.getTotalFee());

					}
					amtToBePaid = totalAmount - paidAmount;
					if (amtToBePaid < 0)
						amtToBePaid = 0;
					dform.getDsearchdto().setPaidAmount(paidAmount);
					dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
					amountTobePaid = String.valueOf(amtToBePaid);
				} else
					amountTobePaid = String.valueOf(feeDsdto.getTotalFee());
				request.setAttribute("forwardPath", "./docsearchtypeb.do?pageName=usertypebdisp&TRFS=NGI");
				request.setAttribute("parentTable", "IGRS_SEARCH_PAYMENT_DETAILS");
				request.setAttribute("parentAmount", amountTobePaid);
				logger.info("PARENT AMOUNT FOR DOC SRCH B>>>>>" + feeDsdto.getTotalFee());
				logger.info("PENDING AMOUNT FOR DOC SRCH B>>>>>" + amountTobePaid);
				request.setAttribute("parentFunId", functionId);
				request.setAttribute("parentKey", pkey);
				request.setAttribute("parentModName", session.getAttribute("modName"));
				request.setAttribute("parentFunName", session.getAttribute("fnName"));
				request.setAttribute("parentColumnName", "TRANSACTION_ID");
				request.setAttribute("formName", "documentsearchtypeAone");

				if ("en".equalsIgnoreCase(language)) {
					session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
				} else {
					session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
				}
				if ("en".equalsIgnoreCase(language)) {
					session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_ENGLISH);
				} else {
					session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_HINDI);
				}
				request.setAttribute("parentDistrictId", dsdto.getParentDistrictId());
				request.setAttribute("parentDistrictName", dsdto.getParentDistrictName());
				request.setAttribute("parentOfficeId", dsdto.getParentOfficeId());
				request.setAttribute("parentOfficeName", dsdto.getParentOfficeName());
				request.setAttribute("parentReferenceId", dsdto.getParentReferenceId());
				logger.info("VVVVVVV>>>>>>" + dsdto.getParentDistrictId());
				logger.info("VVVVVVVVVVVVVVVV" + dsdto.getParentDistrictName());
				logger.info("VVVVVVVVVVVVVV" + dsdto.getParentOfficeId());
				logger.info("VVVVVVVV" + dsdto.getParentOfficeName());
				logger.info("getting parentAmount is ===============" + request.getAttribute("parentAmount"));
				logger.info("getting functionId is ===========" + request.getAttribute("parentFunId"));
				forwardPage = "forwardPath";
			}
			// redirect to payment after 2nd time in case of search b
			if (dform.getDsearchdto().getFormName() != null && dform.getDsearchdto().getFormName().equals("docSearchTypeBSearch")) {
				if (request.getParameter("radioButton1") != null && !request.getParameter("radioButton1").equals("")) {
					String regNo = request.getParameter("radioButton1");
					dsdto.setRegistNumber(regNo);
				}

				String functionId = dsdto.getServiceType();
				if (functionId.equalsIgnoreCase("FUN_063")) {
					dform.getDsearchdto().setTotalFee(dform.getDsearchdto().getServiceFee());
					functionId = "FUN_231";
					feeDsdto = bd.getOthersFeeDuty(functionId, "30", dsdto.getUserTypeId());
					dsdto.setTotalFee(feeDsdto.getTotalFee());
					if ("en".equalsIgnoreCase(language)) {
						session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
					} else {
						session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
					}
					if ("en".equalsIgnoreCase(language)) {
						session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_ENGLISH);
					} else {
						session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_HINDI);
					}
				}
				String doctxnid = dform.getDsearchdto().getRefId();
				dsdto.setParentReferenceId(doctxnid);
				String txnid = bd.updatePaymetTxnDetails(CommonConstant.PAYMENT_FLAG, doctxnid, dform.getDsearchdto().getRegistNumber(), userId, functionId);
				logger.info("functionId is ===========" + functionId);
				String amountTobePaid = "";
				ArrayList payeeDetl = bd.getPaymentList(doctxnid, functionId);
				if (payeeDetl != null && payeeDetl.size() > 0) {
					float paidAmount = 0;
					float totalAmount = 0;
					float amtToBePaid = 0;
					for (int i = 0; i < payeeDetl.size(); i++) {
						DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
						float paidAmt = ldto.getPaidAmount();
						paidAmount = paidAmount + paidAmt;
						totalAmount = Float.parseFloat(ldto.getTotalFee());

					}
					amtToBePaid = totalAmount - paidAmount;
					if (amtToBePaid < 0)
						amtToBePaid = 0;
					dform.getDsearchdto().setPaidAmount(paidAmount);
					dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
					amountTobePaid = String.valueOf(amtToBePaid);
				} else
					amountTobePaid = String.valueOf(feeDsdto.getTotalFee());
				request.setAttribute("forwardPath", "./docsearchtypeb.do?pageName=usertypeadisp&TRFS=NGI");
				request.setAttribute("parentTable", "IGRS_SEARCH_PAYMENT_DETAILS");
				// request.setAttribute("parentAmount", feeDsdto.getTotalFee());
				request.setAttribute("parentAmount", amountTobePaid);
				logger.info("PENDING AMOUNT FOR DOC SRCH B>>>>>" + amountTobePaid);
				logger.info("PARENT AMOUNT in case of search b @@@@@@ >>>>>>>>>>>>>>>" + feeDsdto.getTotalFee());
				request.setAttribute("parentFunId", functionId);
				request.setAttribute("parentKey", txnid);
				request.setAttribute("parentModName", session.getAttribute("modName"));
				request.setAttribute("parentFunName", session.getAttribute("fnName"));
				request.setAttribute("parentColumnName", "TRANSACTION_ID");
				request.setAttribute("parentDistrictId", dsdto.getParentDistrictId());
				request.setAttribute("parentDistrictName", dsdto.getParentDistrictName());
				request.setAttribute("parentOfficeId", dsdto.getParentOfficeId());
				request.setAttribute("parentOfficeName", dsdto.getParentOfficeName());
				request.setAttribute("parentReferenceId", dsdto.getParentReferenceId());
				request.setAttribute("formName", "documentsearchtypeAone");

				logger.info("VVVVVVV>>>>>>" + dsdto.getParentDistrictId());
				logger.info("VVVVVVVVVVVVVVVV" + dsdto.getParentDistrictName());
				logger.info("VVVVVVVVVVVVVV" + dsdto.getParentOfficeId());
				logger.info("VVVVVVVV" + dsdto.getParentOfficeName());
				logger.info("getting parentAmount is ===============" + request.getAttribute("parentAmount"));
				logger.info("getting functionId is ===========" + request.getAttribute("parentFunId"));
				forwardPage = "forwardPath";
			}
			// payment on download click in search B
			if (dform.getDsearchdto().getFormName() != null && dform.getDsearchdto().getFormName().equals("downloadDocumentFees")) {
				boolean signatureCheck = bd.getSignatureCheck(dform.getDsearchdto().getRegistNumber());
				if (signatureCheck || ("o".equalsIgnoreCase(dform.getDsearchdto().getRadiobutton()))) {
					String functionId = dsdto.getServiceType();
					if (functionId.equalsIgnoreCase("FUN_062")) {
						functionId = "FUN_230";
						feeDsdto = bd.getOthersFeeDuty("FUN_230", "19", dsdto.getUserTypeId());
						dsdto.setTotalFee(feeDsdto.getTotalFee());
						if ("en".equalsIgnoreCase(language)) {
							session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
						} else {
							session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
						}
						if ("en".equalsIgnoreCase(language)) {
							session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_ENGLISH);
						} else {
							session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_HINDI);
						}
					} else if (functionId.equalsIgnoreCase("FUN_063")) {
						functionId = "FUN_232";
						feeDsdto = bd.getOthersFeeDuty("FUN_232", "19", dsdto.getUserTypeId());
						dsdto.setTotalFee(feeDsdto.getTotalFee());
						if ("en".equalsIgnoreCase(language)) {
							session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
						} else {
							session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
						}
						if ("en".equalsIgnoreCase(language)) {
							session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_ENGLISH);
						} else {
							session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_HINDI);
						}
					}
					// for download of search a condition applied is if func id
					// FUN_062 change it to fun_230
					logger.info("UserId is ===============" + userId);
					logger.info("functionId is ===========" + functionId);
					String doctxnid = dform.getDsearchdto().getRefId();
					String txnid = bd.downloadPaymetTxnDetails(CommonConstant.PAYMENT_FLAG, doctxnid, userId, functionId, dsdto.getTotalFee());
					dsdto.setParentReferenceId(txnid);
					String amountTobePaid = "";
					// String downloadStatus=bd.getDownloadStatus(doctxnid);
					ArrayList payeeDetl = bd.getPaymentList(doctxnid, functionId);
					/*
					 * if("Y".equalsIgnoreCase(downloadStatus)){ amountTobePaid=feeDsdto.getTotalFee(); }else
					 */if (payeeDetl != null && payeeDetl.size() > 0) {
						float paidAmount = 0;
						float totalAmount = 0;
						float amtToBePaid = 0;
						for (int i = 0; i < payeeDetl.size(); i++) {
							DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
							float paidAmt = ldto.getPaidAmount();
							paidAmount = paidAmount + paidAmt;
							totalAmount = Float.parseFloat(ldto.getTotalFee());

						}
						amtToBePaid = totalAmount - paidAmount;
						if (amtToBePaid < 0)
							amtToBePaid = 0;
						dform.getDsearchdto().setPaidAmount(paidAmount);
						dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
						amountTobePaid = String.valueOf(amtToBePaid);
					} else
						amountTobePaid = String.valueOf(feeDsdto.getTotalFee());
					request.setAttribute("forwardPath", "./docsearchtypeb.do?pageName=downloadDocumentRedirect&TRFS=NGI");
					request.setAttribute("parentTable", "IGRS_SEARCH_PAYMENT_DETAILS");
					// request.setAttribute("parentAmount",feeDsdto.getTotalFee()
					// );
					request.setAttribute("parentAmount", amountTobePaid);
					logger.info("PENDING AMOUNT FOR DOC SRCH B>>>>>" + amountTobePaid);
					request.setAttribute("parentFunId", functionId);
					request.setAttribute("parentKey", txnid);
					request.setAttribute("parentModName", session.getAttribute("modName"));
					request.setAttribute("parentFunName", session.getAttribute("fnName"));
					request.setAttribute("parentColumnName", "TRANSACTION_ID");
					request.setAttribute("parentDistrictId", dsdto.getParentDistrictId());
					request.setAttribute("parentDistrictName", dsdto.getParentDistrictName());
					request.setAttribute("parentOfficeId", dsdto.getParentOfficeId());
					request.setAttribute("parentOfficeName", dsdto.getParentOfficeName());
					request.setAttribute("parentReferenceId", dsdto.getParentReferenceId());
					request.setAttribute("formName", "documentsearchtypeAone");

					logger.info("VVVVVVV>>>>>>" + dsdto.getParentDistrictId());
					logger.info("VVVVVVVVVVVVVVVV" + dsdto.getParentDistrictName());
					logger.info("VVVVVVVVVVVVVV" + dsdto.getParentOfficeId());
					logger.info("VVVVVVVV" + dsdto.getParentOfficeName());
					logger.info("getting parentAmount is ===============" + request.getAttribute("parentAmount"));
					logger.info("getting functionId is ===========" + request.getAttribute("parentFunId"));
					forwardPage = "forwardPath";
				} 

				else {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Cannot download the document as it is not signed by the Officer");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "यह दस्तावेज़ अधिकारी द्वारा हस्ताक्षरित नहीं है इसीलिए आप इसे डाउनलोड नहीं कर सक्ते।");
					dsdto.setTypeBresult(null);
					forwardPage = "usertypebdisplay";
				}
			}
		}

		else if (frwdName != null && (frwdName.equalsIgnoreCase("downloadCopyFinal"))) {
			DocumentSearchDTO sesstionDTO = dform.getDsearchdto();
			String regNo = sesstionDTO.getRegistNumber();
			userId = (String) session.getAttribute("UserId");
			String functionId = dsdto.getServiceType();
			String docId = "";
			boolean boo = false;
			boolean chkCountDownloaded = bd.chkPreviousDownloadedExist(dsdto, userId, regNo);
			if (chkCountDownloaded)
				if (regNo != null && !regNo.equals("")) {
					// String functionId=dsdto.getServiceType();
					docId = bd.storeSearchATxnDetails(dsdto, userId, functionId);
					DocumentSearchDTO resultdto = bd.checkRegistrationId(regNo, language);
					// dsdto.setRefId(docId);
					if (resultdto == null) {
						dform.getDsearchdto().setErrorName("No such record found");
						forwardPage = "usertypeadisp";
					}
					if (resultdto != null) {
						request.setAttribute("abc", resultdto.getKhasraList());
						logger.debug(" in action --<><>" + resultdto);
						session.setAttribute("resultdto", resultdto);

					}
				}
			DocumentOperations docOperations = new DocumentOperations();
			ODServerDetails connDetails = new ODServerDetails();
			logger.debug("value start:here");
			logger.debug(pr.getValue("AppServerIp"));
			logger.debug(pr.getValue("CabinetName"));
			logger.debug(pr.getValue("AppServerUserId"));
			logger.debug(pr.getValue("AppServerUserPass"));
			logger.debug(pr.getValue("ImageServerIP"));
			logger.debug(pr.getValue("ImageServerPort"));
			Dataclass metaDataInfo = new Dataclass();
			connDetails.setAppServerIp(pr.getValue("AppServerIp"));
			connDetails.setCabinetName(pr.getValue("CabinetName"));
			connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
			connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
			connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
			connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
			connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
			metaDataInfo.setUniqueNo(dform.getDsearchdto().getApplicationId());
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			metaDataInfo.setType("F");
			metaDataInfo.setLatestFlag("L");
			String guid = GUIDGenerator.getGUID();
			String downloadPath = pr.getValue("igrs_upload_path");
			// String EstampPath =
			// CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean
			// .getObjEstampDet().getEcode().toString();
			// String EstampPath =
			// CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
			String EstampPath = downloadPath + File.separator + guid;

			File folder = new File(EstampPath.toString());
			if (!folder.exists()) {
				System.out.println(folder.toString());

				folder.mkdirs();

			}
			String tempPath = "";
			String filePath = "";
			// int result =
			// docOperations.downloadDocument(connDetails,metaDataInfo
			// ,CommonConstant
			// .ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet
			// ().getEcode(),CommonConstant.MODULE_NAME);
			int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath.toString(), CommonConstant.MODULE_NAME);

			File[] listOfFiles = folder.listFiles();
			for (int z = 0; z < listOfFiles.length; z++) {
				tempPath = listOfFiles[z].getPath();

			}
			logger.debug("download result---------->" + result);
			// String filePath=bd.burnRequestIdForPDF(
			// dform.getDsearchdto().getRefId(), tempPath,EstampPath);
			String nullVoidFlag = bo.getNullvoidFlag(regNo);
			if ("T".equalsIgnoreCase(nullVoidFlag)) {

				ArrayList nullVoidList = bd.getNullVoidDetls(regNo);

				DocumentSearchDTO ds = (DocumentSearchDTO) nullVoidList.get(0);
				String orderDate = ds.getCourtOrderDate();
				String courtOrderNo = ds.getCourtOrderNo();
				if (orderDate != null && !orderDate.isEmpty()) {

					String reqData = "Document " + regNo + "is being nullified by Court Order number : " + courtOrderNo + "  Date : " + orderDate;
					filePath = bd.burnReqIdAndReqDataForPDF(dform.getDsearchdto().getRefId(), tempPath, EstampPath, reqData);
				}
			} else {
				filePath = bd.burnRequestIdForPDF(dform.getDsearchdto().getRefId(), tempPath, EstampPath);
			}

			byte bytes[] = DMSUtility.getDocumentBytes(filePath);
			if (bytes != null) {

				DMSUtility.downloadDocument(response, filePath, bytes);
				// boolean boo=
				// bd.updateDownloadStatus(dform.getDsearchdto().getRefId
				// (),docId);
				// if(docId!=null && !docId.equalsIgnoreCase(""))
				// boo=
				// bd.updateDownloadStatus(dform.getDsearchdto().getRefId(),
				// docId);
				// else
				boo = bd.updateDownloadStatus(dform.getDsearchdto().getRefId());
				if (boo)
					logger.debug("CERTIFIED COPY SUCCESSFULLY DOWNLOADED" + dform.getDsearchdto().getRefId());
			} else {
				if (language.equalsIgnoreCase("en"))
					request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
				else
					request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज डाउनलोड नहीं हो पाया है");
			}
			forwardPage = "success";
		}

		else if (frwdName != null && (frwdName.equalsIgnoreCase("downloadCopyFirstPage"))) {
			DocumentSearchDTO sesstionDTO = dform.getDsearchdto();
			String regNo = sesstionDTO.getRegistNumber();
			DocumentOperations docOperations = new DocumentOperations();
			ODServerDetails connDetails = new ODServerDetails();
			logger.debug("value start:here");
			logger.debug(pr.getValue("AppServerIp"));
			logger.debug(pr.getValue("CabinetName"));
			logger.debug(pr.getValue("AppServerUserId"));
			logger.debug(pr.getValue("AppServerUserPass"));
			logger.debug(pr.getValue("ImageServerIP"));
			logger.debug(pr.getValue("ImageServerPort"));
			Dataclass metaDataInfo = new Dataclass();
			connDetails.setAppServerIp(pr.getValue("AppServerIp"));
			connDetails.setCabinetName(pr.getValue("CabinetName"));
			connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
			connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
			connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
			connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
			connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
			metaDataInfo.setUniqueNo(dform.getDsearchdto().getApplicationId());
			metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			metaDataInfo.setType("R");
			metaDataInfo.setLatestFlag("L");
			String guid = GUIDGenerator.getGUID();
			String downloadPath = pr.getValue("igrs_upload_path");
			// String EstampPath =
			// CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean
			// .getObjEstampDet().getEcode().toString();
			// String EstampPath =
			// CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
			String EstampPath = downloadPath + File.separator + guid;

			File folder = new File(EstampPath.toString());
			if (!folder.exists()) {
				System.out.println(folder.toString());

				folder.mkdirs();

			}
			String tempPath = "";
			String filePath = "";
			// int result =
			// docOperations.downloadDocument(connDetails,metaDataInfo
			// ,CommonConstant
			// .ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet
			// ().getEcode(),CommonConstant.MODULE_NAME);
			int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath.toString(), CommonConstant.MODULE_NAME);

			File[] listOfFiles = folder.listFiles();
			for (int z = 0; z < listOfFiles.length; z++) {
				tempPath = listOfFiles[z].getPath();

			}
			logger.debug("download result---------->" + result);
			// String filePath=bd.burnRequestIdForPDF(
			// dform.getDsearchdto().getRefId(), tempPath,EstampPath);
			String nullVoidFlag = bo.getNullvoidFlag(regNo);
			if ("T".equalsIgnoreCase(nullVoidFlag)) {

				ArrayList nullVoidList = bd.getNullVoidDetls(regNo);

				DocumentSearchDTO ds = (DocumentSearchDTO) nullVoidList.get(0);

				if (ds != null) {
					String orderDate = ds.getCourtOrderDate();
					String orderNo = ds.getCourtOrderNo();
					if (orderDate != null && !orderDate.isEmpty() && orderNo != null && !orderNo.isEmpty()) {

						String reqData = "Document Number " + regNo + " \nis being nullified by Court Order number " + orderNo + "\n Date : " + orderDate;
						System.out.println("reqData :: " + reqData);
						filePath = bd.burnReqIdAndReqDataForPDF(dform.getDsearchdto().getRefId(), tempPath, EstampPath, reqData);
					}
				}
			} else {
				filePath = bd.burnRequestIdForPDF(dform.getDsearchdto().getRefId(), tempPath, EstampPath);
			}

			byte bytes[] = DMSUtility.getDocumentBytes(filePath);
			if (bytes != null) {
				DMSUtility.downloadDocument(response, filePath, bytes);
			} else {
				dsdto.setTypeBresult(null);
				if (language.equalsIgnoreCase("en"))
					request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
				else
					request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज डाउनलोड नहीं हो पाया है");
			}
			forwardPage = "usertypebdisplay";
		}
		// end
		if (request.getParameter("getAction") != null && "regTehsil".equalsIgnoreCase(request.getParameter("getAction"))) {
			ArrayList regTehsilList = bd.getRegistrationTehsil(dform.getDsearchdto().getHdnRegDistId().split("~")[0], language);
			if (regTehsilList != null) {
				dsdto.setRegTehsilList(regTehsilList);
			} else {
				dsdto.setRegTehsilList(new ArrayList());
			}
			dsdto.setHdnDistName(dform.getDsearchdto().getHdnRegDistId().split("~")[1]);
			dsdto.setGoLiveDate(bd.searchGoLiveDate(dform.getDsearchdto().getHdnRegDistId().split("~")[0]));
			forwardPage = "usertypeb";
		}
		if (request.getParameter("getAction") != null && "regTehsilChange".equalsIgnoreCase(request.getParameter("getAction"))) {
			dsdto.setHdnTehsilName(dform.getDsearchdto().getHdnRegTehsilId().split("~")[1]);
			forwardPage = "usertypeb";
		}
		if (request.getParameter("getAction") != null && "setRadioVal".equalsIgnoreCase(request.getParameter("getAction"))) {
			if ("n".equalsIgnoreCase(dsdto.getRadiobutton())) {
				forwardPage = "usertypeb";
			} else if ("o".equalsIgnoreCase(dsdto.getRadiobutton())) {
				forwardPage = "usertypeb";
			}
		}

		// property details
		if (request.getParameter("getAction") != null && "tehsil".equalsIgnoreCase(request.getParameter("getAction"))) {
			ArrayList tehsilList = bd.getTehisil(dform.getDsearchdto().getDistId(), language);
			if (tehsilList != null) {
				dsdto.setTehsiList(tehsilList);
			} else {
				dsdto.setTehsiList(new ArrayList());
			}
			dsdto.setTehisilId("");
			dsdto.setTehsiList(tehsilList);
			dsdto.setAreaTypeId("");
			dsdto.setSubAreaTypeId("");
			dsdto.setHdnSubAreaWardMappingId("");
			dsdto.setMohallaId("");
			dsdto.setAreaList(bd.getAreaType(language));
			dsdto.setSubAreaTypeList(new ArrayList());
			dsdto.setWardList(new ArrayList());
			dsdto.setMohallaList(new ArrayList());
			forwardPage = "usertypeb";
		}
		if (request.getParameter("getAction") != null && "tehsilChange".equalsIgnoreCase(request.getParameter("getAction"))) {
			dsdto.setSubAreaTypeId("");
			dsdto.setHdnSubAreaWardMappingId("");
			dsdto.setMohallaId("");
			String urbanFlag = "N";
			if ("2".equalsIgnoreCase(dform.getDsearchdto().getAreaTypeId())) {
				urbanFlag = "Y";
			}
			if (dform.getDsearchdto().getTehisilId() != null && !dform.getDsearchdto().getTehisilId().equalsIgnoreCase("")) {
				ArrayList subAreaList = newPropBO.getSubArea(language, dform.getDsearchdto().getAreaTypeId(), dform.getDsearchdto().getTehisilId(), urbanFlag);
				if (subAreaList != null) {
					dsdto.setSubAreaTypeList(subAreaList);
				} else {
					dsdto.setSubAreaTypeList(new ArrayList());
				}
			} else {
				dsdto.setAreaTypeId("");
				dsdto.setSubAreaTypeList(new ArrayList());
			}
			dsdto.setWardList(new ArrayList());
			dsdto.setMohallaList(new ArrayList());
			forwardPage = "usertypeb";
		}
		if (request.getParameter("getAction") != null && "subArea".equalsIgnoreCase(request.getParameter("getAction"))) {
			String urbanFlag = "N";
			if ("2".equalsIgnoreCase(dform.getDsearchdto().getAreaTypeId())) {
				urbanFlag = "Y";
			}
			ArrayList subAreaList = newPropBO.getSubArea(language, dform.getDsearchdto().getAreaTypeId(), dform.getDsearchdto().getTehisilId(), urbanFlag);
			if (subAreaList != null) {
				dsdto.setSubAreaTypeList(subAreaList);
			} else {
				dsdto.setSubAreaTypeList(new ArrayList());
			}
			dsdto.setSubAreaTypeId("");
			dsdto.setHdnSubAreaWardMappingId("");
			dsdto.setMohallaId("");
			dsdto.setWardList(new ArrayList());
			dsdto.setMohallaList(new ArrayList());
			forwardPage = "usertypeb";
		}
		if (request.getParameter("getAction") != null && "wardPatwari".equalsIgnoreCase(request.getParameter("getAction"))) {
			dsdto.setHdnSubAreaWardMappingId("");
			dsdto.setMohallaId("");
			ArrayList wardlist = newPropBO.getWardPatwari(language, dform.getDsearchdto().getSubAreaTypeId(), dform.getDsearchdto().getTehisilId());
			if (wardlist != null) {
				dsdto.setWardList(wardlist);
			} else {
				dsdto.setWardList(new ArrayList());
			}
			dsdto.setMohallaList(new ArrayList());
			forwardPage = "usertypeb";
		}
		if (request.getParameter("getAction") != null && "colony".equalsIgnoreCase(request.getParameter("getAction"))) {
			dsdto.setMohallaId("");
			// ArrayList mohallaList=newPropBO.getColonyName(language,dform.getDsearchdto().getHdnSubAreaWardMappingId());
			ArrayList mohallaList = newPropBO.getColonyName(language, dform.getDsearchdto().getSubAreaWardMappingId());
			if (mohallaList != null) {
				dsdto.setMohallaList(mohallaList);
			} else {
				dsdto.setMohallaList(new ArrayList());
				forwardPage = "usertypeb";
			}
		}

		if (request.getParameter("getAction") != null && "submitAction".equalsIgnoreCase(request.getParameter("getAction"))) {
			if ((dform.getFromDate() != null && !dform.getFromDate().equals("")) && (dform.getToDate() != null && !dform.getToDate().equals(""))) {
				dsdto.setFromDate(dform.getFromDate());
				dsdto.setToDate(dform.getToDate());
			}
			if ("n".equalsIgnoreCase(dsdto.getRadiobutton())) {
				String functionId = "";
				if (dsdto.getUserType() != null && dsdto.getUserType().equalsIgnoreCase("I"))
					functionId = "FUN_064";
				else
					functionId = "FUN_063";
				String txnid = bd.storeBTypeSearchNew(CommonConstant.PAYMENT_FLAG, dform.getDsearchdto(), dsdto, userId, functionId);
				String[] temp = new String[2];
				temp = txnid.split("~");
				String pkey = temp[0];
				String refid = temp[1];
				dform.getDsearchdto().setRefId(refid);
				dsdto.setHdnRegDistId(dform.getDsearchdto().getHdnRegDistId().split("~")[0]);
				ArrayList typeBSearchResult = bd.searchRegisteredDoc(dsdto, language);
				logger.info("size of resultlist>>>>>>>" + typeBSearchResult.size());
				session.setAttribute("typeBSearchResult", typeBSearchResult);
				dsdto.setTypeBresultList(typeBSearchResult);
				feeDsdto = bd.getOthersFeeDuty("FUN_231", "19", dsdto.getUserTypeId());
				dform.getDsearchdto().setServiceFee(feeDsdto.getServiceFee());
				dform.getDsearchdto().setOtherFee(feeDsdto.getOtherFee());
				dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
				dsdto.setTotalFee(feeDsdto.getTotalFee());
				forwardPage = "usertypebdisp";
			} else if ("o".equalsIgnoreCase(dsdto.getRadiobutton())) {
				String functionId = "";
				if (dsdto.getUserType() != null && dsdto.getUserType().equalsIgnoreCase("I"))
					functionId = "FUN_064";
				else
					functionId = "FUN_063";
				String txnid = bd.storeBTypeSearchNew(CommonConstant.PAYMENT_FLAG, dform.getDsearchdto(), dsdto, userId, functionId);
				String[] temp = new String[2];
				temp = txnid.split("~");
				String pkey = temp[0];
				String refid = temp[1];
				dform.getDsearchdto().setRefId(refid);
				ArrayList typeBSearchResult = bd.searchUnRegisteredDoc(dsdto);
				logger.info("size of resultlist>>>>>>>" + typeBSearchResult.size());
				session.setAttribute("typeBSearchResult", typeBSearchResult);
				dsdto.setTypeBresultList(typeBSearchResult);
				feeDsdto = bd.getOthersFeeDuty("FUN_231", "19", dsdto.getUserTypeId());
				dform.getDsearchdto().setServiceFee(feeDsdto.getServiceFee());
				dform.getDsearchdto().setOtherFee(feeDsdto.getOtherFee());
				dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
				dsdto.setTotalFee(feeDsdto.getTotalFee());
				forwardPage = "usertypebdisp";
			}
		}
		// on return from payment in search b first page
		if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("usertypebdisp")) {
			ArrayList payeeDetl = bd.getPaymentList(dform.getDsearchdto().getParentReferenceId(), "FUN_063");
			if (payeeDetl != null && payeeDetl.size() > 0) {
				float paidAmount = 0;
				float totalAmount = 0;
				float amtToBePaid = 0;
				for (int i = 0; i < payeeDetl.size(); i++) {
					DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
					float paidAmt = ldto.getPaidAmount();
					paidAmount = paidAmount + paidAmt;
					totalAmount = Float.parseFloat(ldto.getTotalFee());

				}
				amtToBePaid = totalAmount - paidAmount;
				if (amtToBePaid < 0)
					amtToBePaid = 0;
				dform.getDsearchdto().setPaidAmount(paidAmount);
				dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
				if (paidAmount < totalAmount) {
					dform.getDsearchdto().setIsCompletePay(1);
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Kindly pay the remaining amount INR: " + amtToBePaid);
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कृपया शेष राशि का भुगतान  करें (रुपए) :" + amtToBePaid);

					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "Your Certified Copy Reference ID is: " + dform.getDsearchdto().getParentReferenceId());
					else
						request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "आपका प्रमाणित प्रतिलिपि संदर्भ आईडी है :" + dform.getDsearchdto().getParentReferenceId());

					forwardPage = "usertypeb";
				} else {
					ArrayList typeBSearchResult = new ArrayList();
					if ("n".equalsIgnoreCase(dsdto.getRadiobutton())) {
						typeBSearchResult = bd.searchRegisteredDoc(dsdto, language);
					}
					if ("o".equalsIgnoreCase(dsdto.getRadiobutton())) {
						typeBSearchResult = bd.searchUnRegisteredDoc(dsdto);
					}
					session.setAttribute("typeBSearchResult", typeBSearchResult);
					logger.debug("=in action typeBSearchResult==" + session.getAttribute("typeBSearchResult"));
					dsdto.setTypeBresultList(typeBSearchResult);
					feeDsdto = bd.getOthersFeeDuty("FUN_231", "19", dsdto.getUserTypeId());
					dform.getDsearchdto().setServiceFee(feeDsdto.getServiceFee());
					dform.getDsearchdto().setOtherFee(feeDsdto.getOtherFee());
					dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
					dsdto.setTotalFee(feeDsdto.getTotalFee());
					dsdto.setSearchType("FUN_063");
					forwardPage = "usertypebdisp";
				}
			}
		}
		// on return from payment in search b SECOND page
		if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("usertypeadisp")) {
			if (request.getParameter("radioButton1") != null && !request.getParameter("radioButton1").equals("")) {
				String regNo = request.getParameter("radioButton1");
				dsdto.setRegistNumber(regNo);
			}
			// SHREE
			if (dsdto.getRegistNumber() != null && !dsdto.getRegistNumber().equals("")) {
				DocumentSearchDTO resultdto = null;
				bd.updateSearchStatus(dsdto.getRefId(), "FUN_232", dsdto.getRegistNumber());
				String checkProtestDetls = bd.getProtestFlag(dsdto.getRegistNumber());
				if (checkProtestDetls != null && !checkProtestDetls.equals("")) {

					resultdto = bd.checkRegistrationIdWithProtest(dsdto.getRegistNumber(), language);
				} else {
					resultdto = bd.checkRegistrationId(dsdto.getRegistNumber(), language);
				}
				if("o".equalsIgnoreCase(dsdto.getRadiobutton())){
					resultdto = bd.checkOldDocData(dsdto.getRegistNumber(), language);
				}
				if (resultdto == null) {
					
					dform.getDsearchdto().setErrorName("No such record found");
					
					forwardPage = "usertypebdisplay";
				} else if (resultdto != null) {
					ArrayList nullVoidDetls = new ArrayList();
					ArrayList bankChargeId = new ArrayList();
					String checkNullVoid = bd.getNullvoidFlag(dsdto.getRegistNumber());
					bankChargeId = bd.getBankChargeId(dsdto.getRegistNumber());
					ArrayList payeeDetl = bd.getPaymentList(dsdto.getRegistNumber(), "FUN_232");
					if (checkNullVoid.equalsIgnoreCase("T")) {
						nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
						dsdto.setNullVoidFlag("T");
					} else if (checkNullVoid.equalsIgnoreCase("F")) {
						nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
						dsdto.setNullVoidFlag("F");
					}
					if (bankChargeId != null && bankChargeId.size() > 0) {
						for (int i = 0; i < bankChargeId.size(); i++) {
							DocumentSearchDTO dto = (DocumentSearchDTO) bankChargeId.get(i);
							String bankId = dto.getBankChargeId();
							String caveatStatus = dto.getCaveatStatus();
							String caveatUploadedDoc = dto.getCaveatUploadedDoc();
							dsdto.setBankChargeId(bankId);
							dsdto.setCaveatStatus(caveatStatus);
							if (caveatUploadedDoc != null)
								dsdto.setCaveatUploadedDoc(caveatUploadedDoc);
							else
								dsdto.setCaveatUploadedDoc("NA");
						}
					}
					if (nullVoidDetls != null && nullVoidDetls.size() > 0) {
						for (int i = 0; i < nullVoidDetls.size(); i++) {
							DocumentSearchDTO dto = (DocumentSearchDTO) nullVoidDetls.get(i);
							String courtName = dto.getCourtName();
							String courtOrderNo = dto.getCourtOrderNo();
							String courtOrderDate = dto.getCourtOrderDate();
							dsdto.setCourtName(courtName);
							dsdto.setCourtOrderNo(courtOrderNo);
							dsdto.setCourtOrderDate(courtOrderDate);

						}
					}
					if (payeeDetl != null && payeeDetl.size() > 0) {
						float paidAmount = 0;
						float totalAmount = 0;
						float amtToBePaid = 0;
						for (int i = 0; i < payeeDetl.size(); i++) {
							DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
							float paidAmt = ldto.getPaidAmount();
							paidAmount = paidAmount + paidAmt;
							totalAmount = Float.parseFloat(ldto.getTotalFee());

						}
						amtToBePaid = totalAmount - paidAmount;
						if (amtToBePaid < 0)
							amtToBePaid = 0;
						dform.getDsearchdto().setPaidAmount(paidAmount);
						dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
						dform.getDsearchdto().setTotalFee(String.valueOf(totalAmount));

						if (paidAmount < totalAmount) {
							dform.getDsearchdto().setIsCompletePay(1);
						} else {
							dform.getDsearchdto().setIsCompletePay(0);
						}
					} else {
						dform.getDsearchdto().setIsCompletePay(1);
						dform.getDsearchdto().setPaidAmount(0);
						dform.getDsearchdto().setAmounttobePaid(0);
					}

					request.setAttribute("abc", resultdto.getKhasraList());
					session.setAttribute("resultdto", resultdto);
					ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
					logger.debug("party list value size is " + refprtList.size());
					partydto.setPartyList(refprtList);
					dform.setPartyDto(partydto);
					dsdto.setTypeBresult(resultdto.getTypeBresult());
					dsdto.setPropertyList(resultdto.getPropertyList());
					dsdto.setKhasraList(resultdto.getKhasraList());
					dsdto.setCaveatslist(resultdto.getCaveatslist());
					dsdto.setProtestList(resultdto.getProtestList());
					dsdto.setComplianceList(resultdto.getComplianceList());
					dsdto.setApplicationId(resultdto.getApplicationId());
					dsdto.setOldPropList(resultdto.getOldPropList());
					dsdto.setOldDocFY(resultdto.getOldDocFY());
					dsdto.setOldDocRegDate(resultdto.getOldDocRegDate());
					logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
					try {
						if (dsdto.getSearchType() != null) {
							if (dsdto.getSearchType().equalsIgnoreCase("FUN_062")) {
								feeDsdto = bd.getOthersFeeDuty("FUN_230", "19", dsdto.getUserTypeId());
								if ("en".equalsIgnoreCase(language)) {
									session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
								} else {
									session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
								}
								if ("en".equalsIgnoreCase(language)) {
									session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_ENGLISH);
								} else {
									session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_HINDI);
								}
							} else if (dsdto.getSearchType().equalsIgnoreCase("FUN_063")) {
								feeDsdto = bd.getOthersFeeDuty("FUN_232", "19", dsdto.getUserTypeId());
								if ("en".equalsIgnoreCase(language)) {
									session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
								} else {
									session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
								}
								if ("en".equalsIgnoreCase(language)) {
									session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_ENGLISH);
								} else {
									session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_HINDI);
								}
							} else if (dsdto.getSearchType().equalsIgnoreCase("FUN_064")) {
								feeDsdto.setTotalFee("0");
							}
						}
						logger.debug("feeDsdto.getOtherFee() " + feeDsdto.getOtherFee());
						logger.debug("feeDsdto.getServiceFee() " + feeDsdto.getServiceFee());
						logger.info("fees is....." + feeDsdto.getTotalFee());
						dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
					} catch (Exception e) {
						logger.info(e);
					}
					if (request.getParameter("radioButton2") != null && !request.getParameter("radioButton2").equals("")) {
						if (request.getParameter("radioButton2").equals("new")) {
							forwardPage = "usertypebdispHyper";
						}
					} else {
						forwardPage = "usertypebdisplay";
					}
				} else {
					forwardPage = "usertypebdisplay";
				}
			} else {
				forwardPage = "usertypebdisplay";
			}
		}
		// handling partial payment case -view for search a/b on dashboard
		if (request.getParameter("pageName") != null && (request.getParameter("pageName").equalsIgnoreCase("userSrchBView"))) {
			// for checking the state at which point the transaction will be
			// resumed
			String hdnRefId = (String) request.getParameter("hdnDocId");
			String[] txnIds = hdnRefId.split("~");
			String regNo = txnIds[1].toString();
			String flag = txnIds[2].toString();
			String docNo = txnIds[0].toString();
			String currentStatus = bd.checkResumeState(docNo);
			dsdto.setParentReferenceId(docNo);
			dsdto.setRefId(docNo);
			dsdto.setRegistNumber(regNo);
			String docId = "";
			boolean chkCountDownloaded = bd.chkPreviousDownloadedExist(dsdto, userId, docNo);
			if (chkCountDownloaded)
				if (regNo != null && !regNo.equals("")) {
					String functionId = dsdto.getServiceType();
					docId = bd.storeSearchATxnDetails(dsdto, userId, functionId);
					dsdto.setRefId(docId);
				}
			if (currentStatus.equalsIgnoreCase("FUN_063")) {
				ArrayList payeeDetl = bd.getPaymentList(docNo, "FUN_063");
				if (payeeDetl != null && payeeDetl.size() > 0) {
					float paidAmount = 0;
					float totalAmount = 0;
					float amtToBePaid = 0;
					for (int i = 0; i < payeeDetl.size(); i++) {
						DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
						float paidAmt = ldto.getPaidAmount();
						paidAmount = paidAmount + paidAmt;
						totalAmount = Float.parseFloat(ldto.getTotalFee());

					}
					amtToBePaid = totalAmount - paidAmount;
					if (amtToBePaid < 0)
						amtToBePaid = 0;
					dform.getDsearchdto().setPaidAmount(paidAmount);
					dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
					dform.getDsearchdto().setTotalFee(String.valueOf(totalAmount));
					if (paidAmount < totalAmount) {
						dform.getDsearchdto().setIsCompletePay(1);
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Kindly pay the remaining amount INR: " + amtToBePaid);
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कृपया शेष राशि का भुगतान  करें (रुपए) :" + amtToBePaid);

						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "Your Certified Copy Reference ID is: " + docNo);
						else
							request.setAttribute(DeedDraftConstant.SUCCESS_MSG, "आपका प्रमाणित प्रतिलिपि संदर्भ आईडी है :" + docNo);

						dform.setFromDate("");
						dform.setToDate("");
						logger.debug("m here in officeTypeB ---------------------------- ");
						ArrayList dist = bd.getDistrict(language);
						ArrayList regDistList = bd.getRegsitrationDistrict(language);
						ArrayList areaList = bd.getAreaType(language);
						ArrayList fiscalYear = bd.getFinancialYear(language);
						dsdto.setFinancialYearList(fiscalYear);
						dsdto.setAreaList(areaList);
						dsdto.setDistList(dist);
						dsdto.setRegDistList(regDistList);
						dsdto.setServiceType("FUN_063");
						dsdto.setSearchType("FUN_063");
						forwardPage = "usertypeb";
					} else {
						dform.getDsearchdto().setIsCompletePay(0);
						if (dsdto.getRegistNumber().equalsIgnoreCase("-")) {
							dform.setFromDate("");
							dform.setToDate("");
							logger.debug("m here in officeTypeB ---------------------------- ");
							ArrayList dist = bd.getDistrict(language);
							ArrayList regDistList = bd.getRegsitrationDistrict(language);
							ArrayList areaList = bd.getAreaType(language);
							ArrayList fiscalYear = bd.getFinancialYear(language);
							dsdto.setAreaList(areaList);
							dsdto.setDistList(dist);
							dsdto.setRegDistList(regDistList);
							dsdto.setFinancialYearList(fiscalYear);
							dsdto.setServiceType("FUN_063");
							dsdto.setSearchType("FUN_063");
							forwardPage = "usertypeb";
						} else {
							DocumentSearchDTO resultdto = bd.checkRegistrationId(dsdto.getRegistNumber(), language);
							//need to add
							String checkSearchType = bd.checkSearchType(dsdto.getRegistNumber());
							if("OLD".equalsIgnoreCase(checkSearchType)){
								resultdto=bd.checkOldDocData(dsdto.getRegistNumber(), language);
							}
							if (resultdto == null) {
								dform.getDsearchdto().setErrorName("No such record found");
								forwardPage = "usertypebdisplay";
							} else if (resultdto != null) {
								ArrayList payeeDetlList = bd.getPaymentList(dsdto.getRegistNumber(), "FUN_232");
								if (payeeDetlList != null && payeeDetlList.size() > 0) {

									for (int i = 0; i < payeeDetlList.size(); i++) {
										DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetlList.get(i);
										float paidAmt = ldto.getPaidAmount();
										paidAmount = paidAmount + paidAmt;
										totalAmount = Float.parseFloat(ldto.getTotalFee());

									}
									amtToBePaid = totalAmount - paidAmount;
									if (amtToBePaid < 0)
										amtToBePaid = 0;
									dform.getDsearchdto().setPaidAmount(paidAmount);
									dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
									dform.getDsearchdto().setTotalFee(String.valueOf(totalAmount));

									if (paidAmount < totalAmount) {
										dform.getDsearchdto().setIsCompletePay(1);
									} else {
										dform.getDsearchdto().setIsCompletePay(0);
									}
								} else {
									dsdto.setSearchType("FUN_063");
									dform.getDsearchdto().setIsCompletePay(1);
									dform.getDsearchdto().setPaidAmount(0);
									dform.getDsearchdto().setAmounttobePaid(0);
								}

								request.setAttribute("abc", resultdto.getKhasraList());
								session.setAttribute("resultdto", resultdto);
								ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
								logger.debug("party list value size is " + refprtList.size());
								partydto.setPartyList(refprtList);
								dform.setPartyDto(partydto);
								dsdto.setTypeBresult(resultdto.getTypeBresult());
								dsdto.setPropertyList(resultdto.getPropertyList());
								dsdto.setKhasraList(resultdto.getKhasraList());
								dsdto.setCaveatslist(resultdto.getCaveatslist());
								dsdto.setComplianceList(resultdto.getComplianceList());
								dsdto.setApplicationId(resultdto.getApplicationId());
								dsdto.setOldPropList(resultdto.getOldPropList());
								dsdto.setOldDocFY(resultdto.getOldDocFY());
								dsdto.setOldDocRegDate(resultdto.getOldDocRegDate());
								dsdto.setRadiobutton("OLD".equalsIgnoreCase(checkSearchType) ? "o":"n");
								logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
								try {
									if (dsdto.getSearchType() != null) {
										if (dsdto.getSearchType().equalsIgnoreCase("FUN_062")) {
											feeDsdto = bd.getOthersFeeDuty("FUN_230", "19", dsdto.getUserTypeId());
											if ("en".equalsIgnoreCase(language)) {
												session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
											} else {
												session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
											}
											if ("en".equalsIgnoreCase(language)) {
												session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_ENGLISH);
											} else {
												session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_HINDI);
											}
										} else if (dsdto.getSearchType().equalsIgnoreCase("FUN_063")) {
											feeDsdto = bd.getOthersFeeDuty("FUN_232", "19", dsdto.getUserTypeId());
											if ("en".equalsIgnoreCase(language)) {
												session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
											} else {
												session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
											}
											if ("en".equalsIgnoreCase(language)) {
												session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_ENGLISH);
											} else {
												session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_HINDI);
											}
										} else if (dsdto.getSearchType().equalsIgnoreCase("FUN_064")) {
											feeDsdto.setTotalFee("0");
										}
									}
									logger.debug("feeDsdto.getOtherFee() " + feeDsdto.getOtherFee());
									logger.debug("feeDsdto.getServiceFee() " + feeDsdto.getServiceFee());
									logger.info("fees is....." + feeDsdto.getTotalFee());
									dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
								} catch (Exception e) {
									logger.info(e);
								}
								forwardPage = "usertypebdisplay";
							} else {
								forwardPage = "usertypebdisplay";
							}

						}
					}
				}
			} else if (currentStatus.equalsIgnoreCase("FUN_231")) {
				ArrayList payeeDownloadDetl = bd.getPaymentList(dsdto.getRefId(), "FUN_231");
				if (payeeDownloadDetl != null && payeeDownloadDetl.size() > 0) {
					float paidAmount = 0;
					float totalAmount = 0;
					float amtToBePaid = 0;
					for (int i = 0; i < payeeDownloadDetl.size(); i++) {
						DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDownloadDetl.get(i);
						float paidAmt = ldto.getPaidAmount();
						paidAmount = paidAmount + paidAmt;
						totalAmount = Float.parseFloat(ldto.getTotalFee());

					}
					amtToBePaid = totalAmount - paidAmount;
					dform.getDsearchdto().setPaidAmount(paidAmount);
					dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
					dform.getDsearchdto().setTotalFee(String.valueOf(totalAmount));

					if (paidAmount < totalAmount) {
						dform.getDsearchdto().setIsCompletePay(1);
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Kindly pay the remaining amount INR: " + amtToBePaid);
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कृपया शेष राशि का भुगतान  करें (रुपए) :" + amtToBePaid);
						forwardPage = "usertypeadisp";
					} else {
						dform.getDsearchdto().setIsCompletePay(0);
					}

				}
				if (regNo != null && !regNo.equals("")) {
					DocumentSearchDTO resultdto = bd.checkRegistrationIdNew(regNo, flag, language);
					// dsdto.setRefId(txnIds[0].toString());
					logger.debug(" in action --<><>" + resultdto);
					session.setAttribute("resultdto", resultdto);
					if (resultdto == null) {
						forwardPage = "usertypeadisp";
					}
					if (resultdto != null) {
						logger.debug("--in action result --<>" + resultdto.getTypeBresult());
						ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
						logger.debug("party list value size is " + refprtList.size());
						partydto.setPartyList(refprtList);
						dform.setPartyDto(partydto);
						dsdto.setTypeBresult(resultdto.getTypeBresult());
						dsdto.setPropertyList(resultdto.getPropertyList());
						dsdto.setCaveatslist(resultdto.getCaveatslist());
						dsdto.setComplianceList(resultdto.getComplianceList());
						dsdto.setApplicationId(resultdto.getApplicationId());
						logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
						logger.info("fees is....." + feeDsdto.getTotalFee());

						try {
							feeDsdto = bd.getOthersFeeDuty("FUN_232", "19", dsdto.getUserTypeId());
							logger.debug("feeDsdto.getOtherFee() " + feeDsdto.getOtherFee());
							logger.debug("feeDsdto.getServiceFee() " + feeDsdto.getServiceFee());
							logger.info("fees is....." + feeDsdto.getTotalFee());
							dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
						} catch (Exception e) {
							logger.info(e);
						}
						forwardPage = "usertypeadisp";
					} else {
						forwardPage = "usertypeadisp";
					}
				}
			} else if (currentStatus.equalsIgnoreCase("FUN_232")) {
				DocumentSearchDTO resultdto = null;
				String checkProtestDetls = bd.getProtestFlag(dsdto.getRegistNumber());
				if (checkProtestDetls != null && !checkProtestDetls.equals("")) {

					resultdto = bd.checkRegistrationIdWithProtest(dsdto.getRegistNumber(), language);
				} else {
					resultdto = bd.checkRegistrationId(dsdto.getRegistNumber(), language);
				}
				if (resultdto == null) {
					dform.getDsearchdto().setErrorName("No such record found");
					forwardPage = "usertypebdisplay";
				} else if (resultdto != null) {
					ArrayList nullVoidDetls = new ArrayList();
					String checkNullVoid = bd.getNullvoidFlag(dsdto.getRegistNumber());
					if (checkNullVoid.equalsIgnoreCase("T")) {
						nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
						dsdto.setNullVoidFlag("T");
					} else if (checkNullVoid.equalsIgnoreCase("F")) {
						nullVoidDetls = bd.getNullVoidDetls(dsdto.getRegistNumber());
						dsdto.setNullVoidFlag("F");
					}

					if (nullVoidDetls != null && nullVoidDetls.size() > 0) {
						for (int i = 0; i < nullVoidDetls.size(); i++) {
							DocumentSearchDTO dto = (DocumentSearchDTO) nullVoidDetls.get(i);
							String courtName = dto.getCourtName();
							String courtOrderNo = dto.getCourtOrderNo();
							String courtOrderDate = dto.getCourtOrderDate();
							dsdto.setCourtName(courtName);
							dsdto.setCourtOrderNo(courtOrderNo);
							dsdto.setCourtOrderDate(courtOrderDate);

						}
					}
					ArrayList payeeDetl = bd.getPaymentList(dsdto.getParentReferenceId(), "FUN_232");
					if ((payeeDetl != null && payeeDetl.size() > 0)) {
						float paidAmount = 0;
						float totalAmount = 0;
						float amtToBePaid = 0;
						for (int i = 0; i < payeeDetl.size(); i++) {
							DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDetl.get(i);
							float paidAmt = ldto.getPaidAmount();
							paidAmount = paidAmount + paidAmt;
							totalAmount = Float.parseFloat(ldto.getTotalFee());

						}
						amtToBePaid = totalAmount - paidAmount;
						if (amtToBePaid < 0)
							amtToBePaid = 0;
						dform.getDsearchdto().setPaidAmount(paidAmount);
						dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
						dform.getDsearchdto().setTotalFee(String.valueOf(totalAmount));

						if (paidAmount < totalAmount) {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Kindly pay the remaining amount INR: " + amtToBePaid);
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कृपया शेष राशि का भुगतान  करें (रुपए) :" + amtToBePaid);
							dform.getDsearchdto().setIsCompletePay(1);
						} else {
							dform.getDsearchdto().setIsCompletePay(0);
						}
					} else {

						dsdto.setSearchType("FUN_063");
						dform.getDsearchdto().setIsCompletePay(1);
						dform.getDsearchdto().setPaidAmount(0);
						dform.getDsearchdto().setAmounttobePaid(0);
					}

					request.setAttribute("abc", resultdto.getKhasraList());
					session.setAttribute("resultdto", resultdto);
					ArrayList refprtList = (ArrayList) resultdto.getPartyDTO().getPartyList();
					logger.debug("party list value size is " + refprtList.size());
					partydto.setPartyList(refprtList);
					dform.setPartyDto(partydto);
					dsdto.setTypeBresult(resultdto.getTypeBresult());
					dsdto.setPropertyList(resultdto.getPropertyList());
					dsdto.setKhasraList(resultdto.getKhasraList());
					if (checkProtestDetls != null && !checkProtestDetls.equals("")) {
						dsdto.setProtestList(resultdto.getProtestList());
					}
					dsdto.setCaveatslist(resultdto.getCaveatslist());
					dsdto.setComplianceList(resultdto.getComplianceList());
					dsdto.setApplicationId(resultdto.getApplicationId());
					logger.debug("Party list size in form " + dform.getPartyDto().getPartyList().size());
					try {
						if (dsdto.getSearchType() != null) {
							if (dsdto.getSearchType().equalsIgnoreCase("FUN_062")) {
								feeDsdto = bd.getOthersFeeDuty("FUN_230", "19", dsdto.getUserTypeId());
								if ("en".equalsIgnoreCase(language)) {
									session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
								} else {
									session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
								}
								if ("en".equalsIgnoreCase(language)) {
									session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_ENGLISH);
								} else {
									session.setAttribute("fnName", CommonConstant.DOC_SRCH_A_FUNCTION_PAY_HINDI);
								}
							} else if (dsdto.getSearchType().equalsIgnoreCase("FUN_063")) {
								feeDsdto = bd.getOthersFeeDuty("FUN_232", "19", dsdto.getUserTypeId());
								if ("en".equalsIgnoreCase(language)) {
									session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_ENGLISH);
								} else {
									session.setAttribute("modName", CommonConstant.DOC_SRCH_MODNAME_HINDI);
								}
								if ("en".equalsIgnoreCase(language)) {
									session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_ENGLISH);
								} else {
									session.setAttribute("fnName", CommonConstant.DOC_SRCH_B_FUNCTION_PAY_HINDI);
								}
							} else if (dsdto.getSearchType().equalsIgnoreCase("FUN_064")) {
								feeDsdto.setTotalFee("0");
							}
							logger.debug("feeDsdto.getOtherFee() " + feeDsdto.getOtherFee());
							logger.debug("feeDsdto.getServiceFee() " + feeDsdto.getServiceFee());
							logger.info("fees is....." + feeDsdto.getTotalFee());
							dform.getDsearchdto().setTotalFee(feeDsdto.getTotalFee());
						}

					} catch (Exception e) {
						logger.info(e);
					}
					forwardPage = "usertypebdisplay";
				} else {
					forwardPage = "usertypebdisplay";
				}
			}

		}
		// end
		// after payment success for download service in search B
		if (request.getParameter("pageName") != null && request.getParameter("pageName").equalsIgnoreCase("downloadDocumentRedirect")) {
			ArrayList payeeDownloadDetl = bd.getPaymentList(dsdto.getRefId(), "FUN_232");
			if (payeeDownloadDetl != null && payeeDownloadDetl.size() > 0) {
				float paidAmount = 0;
				float totalAmount = 0;
				float amtToBePaid = 0;
				for (int i = 0; i < payeeDownloadDetl.size(); i++) {
					DocumentSearchDTO ldto = (DocumentSearchDTO) payeeDownloadDetl.get(i);
					float paidAmt = ldto.getPaidAmount();
					paidAmount = paidAmount + paidAmt;
					totalAmount = Float.parseFloat(ldto.getTotalFee());

				}
				amtToBePaid = totalAmount - paidAmount;
				dform.getDsearchdto().setPaidAmount(paidAmount);
				dform.getDsearchdto().setAmounttobePaid(amtToBePaid);
				dform.getDsearchdto().setTotalFee(String.valueOf(totalAmount));
				if (paidAmount < totalAmount) {
					dform.getDsearchdto().setIsCompletePay(1);
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Kindly pay the remaining amount INR: " + amtToBePaid);
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कृपया शेष राशि का भुगतान  करें (रुपए) :" + amtToBePaid);
					forwardPage = "usertypebdisplay";
				} else {
					dform.getDsearchdto().setIsCompletePay(0);
					forwardPage = "success";
				}

			} else {

				forwardPage = "usertypebdisplay";
			}

		}
		dform.setDsearchdto(dsdto);
		session.setAttribute("dform", dform);
		return mapping.findForward(forwardPage);
	}

}
