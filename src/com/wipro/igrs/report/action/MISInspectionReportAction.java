/* 
 * <Copyright information>
 *
 * <Customer specific copyright notice (if any) >
 *
 * ==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 * ==============================================================================
 * 
 * File Name	   		: MISInspectionReportAction.java
 *
 * Description	   		: Struts Action to view Revenue Management Details
 *
 * Version Number  		: 1.0 
 *
 * Created Date	   		: 01-JUN-2016  
 *
 * Modification History	: Created by Siddhartha
 * 
 */

package com.wipro.igrs.report.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.estamping.constant.CommonConstant;
import com.wipro.igrs.report.action.reportPdf.Report;
import com.wipro.igrs.report.bd.ReportBD;
import com.wipro.igrs.report.bo.MISReportBO;
import com.wipro.igrs.report.bo.PendingCourtCaseReportBO;
import com.wipro.igrs.report.bo.ReportBO;
import com.wipro.igrs.report.common.MarketTrendReportConstants;
import com.wipro.igrs.report.dao.ReportDAO;
import com.wipro.igrs.report.dto.MISReportDTO;
import com.wipro.igrs.report.form.MISReportForm;
import com.wipro.igrs.spotInsp.bo.SpotInspBO;
import com.wipro.igrs.spotInsp.dto.SpotInspDTO;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.util.JrxmlExportUtility;
import com.wipro.igrs.util.PropertiesFileReader;

public class MISInspectionReportAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(MISReportAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		// HttpSession session = (HttpSession)request.getSession();

		logger.debug("my two");
		logger.debug("in Action Class");
		MISReportForm eForm = (MISReportForm) form;
		String userId = (String) session.getAttribute("UserId");
		String officeId = (String) session.getAttribute("loggedToOffice"); // OFC215
		String language = (String) session.getAttribute("languageLocale");
		MISReportBO reportBD = new MISReportBO();
		Report reportPDF = new Report();
		ReportDAO reportDao = new ReportDAO();
		/*
		 * ArrayList districtList = reportBD.getDistrictDetailsMIS(language, officeId);
		 */
		/*
		 * String districtName = reportBD.getDistrictName(eForm.getReportDTO() .getDistrictID());
		 */
		String deedName = reportBD.getDeedName(eForm.getReportDTO().getDeedID(), language);
		/*
		 * districtName = districtName.equalsIgnoreCase("") ? " ALL " : districtName;
		 */
		deedName = deedName.equalsIgnoreCase("") ? " ALL " : deedName;

		ReportBD reportBd = new ReportBD();
		String distId = reportBd.getDistrictId(officeId);
		String officeType = reportBd.getOfficeType(officeId); // officeType = 2
		String officer = reportBd.getType(officeType);
		// String language=(String)session.getAttribute("languageLocale");
		// MISReportDTO.setLanguage(language);
		eForm.setLanguage(language);

		if (officer.equalsIgnoreCase("IGO")) {
			eForm.setUser("I");
		} else if (officer.equalsIgnoreCase("DRO")) {
			eForm.setUser("DR");
			ArrayList distList = reportBd.getDistList(officeId); // OFC215
			MISReportDTO dto = (MISReportDTO) distList.get(0);
			eForm.setDistrictID(dto.getDistrictID());
			eForm.setZoneId(dto.getZoneId());
		} else if (officer.equalsIgnoreCase("DIGO")) {
			String zoneId = reportBd.getDIGZone(officeId);
			eForm.setZoneId(zoneId);
			logger.debug("The Zone of DIG : " + zoneId);

			eForm.setUser("DI");
		}

		int offcId = Integer.parseInt(officeType);
		// ArrayList tehsilList = new ArrayList();
		// tehsilList = reportBd.allTehsilList(distId);

		/*
		 * for (int i = 0; i < tehsilList.size(); i++) { ArrayList allTehsil = (ArrayList) tehsilList.get(i);
		 * 
		 * // eForm.setTehsilId(allTehsil.get(0).toString()); // eForm.setTehsilName(allTehsil.get(1).toString()); }
		 */
		// eForm.setTehsilList(tehsilList);
		/*
		 * for (int i = 0; tehsilList.size() > 1; i++) { ArrayList allTehsil = (ArrayList) tehsilList.get(i);
		 * 
		 * }
		 */

		eForm.setOffcId(offcId);
		eForm.getReportDTO().setDeedName(deedName);
		// eForm.getReportDTO().setDistrictName(districtName);
		// eForm.setDistrictList(districtList);
		eForm.setAreaTypeList(reportBD.getAreaTypeDetails(language));
		eForm.setFiscalYearList(reportBD.getYearForJudicalStamp(reportBD.getYearCheck()));
		eForm.getReportDTO().setOfficeName(reportBD.getOfficeName(eForm.getReportDTO().getOfficeId()));
		boolean validReport = true;

		boolean validReportInsp = reportBD.validateReportInsp(eForm.getDurFrom(), eForm.getDurTo()); // added by Siddhartha

		boolean validateIgrsUserMapping = reportBD.validateReport(eForm.getFromDate(), eForm.getToDate());

		// request.setAttribute("districtlst",eForm);

		ArrayList userTypeList = new ArrayList();
		String forwardJsp = "";
		String actionID = eForm.getActionName();
		if (actionID == null) {
			actionID = "---";
		}

		if (eForm != null) {

			MISReportDTO dto = eForm.getReportDTO();
			logger.debug("actionID value========" + actionID);

			Report rpt = new Report();

			String param = request.getParameter("param");
			String label = request.getParameter("label");

			if (MarketTrendReportConstants.USAGE_REQUEST_LABEL.equals(param)) {
				forwardJsp = MarketTrendReportConstants.USAGE_REPORT;
				actionID = null;
			}

			// Added By Rachita

			if (MarketTrendReportConstants.SEARCH_REPORT_PENDING_BACK.equals(eForm.getActionName())) {

				eForm.setFromDate("");
				eForm.setToDate("");

				forwardJsp = MarketTrendReportConstants.SEARCH_REPORT_PENDING;
				actionID = null;
				// }
			}
			if ("PENDING_COURT_CASES_LABEL".equalsIgnoreCase(param)) {
				eForm.setToDate("");
				eForm.setFromDate("");
				forwardJsp = "searchReportPending";
				eForm.setActionName("");
				return mapping.findForward(forwardJsp);
			}
			if ("PENDING_COURT_CASES_SUBMIT_ACTION".equalsIgnoreCase(eForm.getActionName())) {
				PendingCourtCaseReportBO pbo = new PendingCourtCaseReportBO();
				ArrayList dataList = pbo.getSearchReqReport(eForm.getFromDate(), eForm.getToDate());
				eForm.getPenDTO().setReportList(dataList);
				forwardJsp = "displayPendingCourtSearchReport";
				eForm.setActionName("");
				return mapping.findForward(forwardJsp);
			}

			// ADDED BY SHREERAJ
			if (MarketTrendReportConstants.REGISTRATION_REPORT_LABEL.equals(param)) {
				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
				eForm.getReportDTO().setDistrictID("");
				eForm.getReportDTO().setDeedID("");
				eForm.setReportName("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT_SEARCH;
				actionID = null;
			}
			if ("SUBMIT_REGISTRATION_REPORT_ACTION".equalsIgnoreCase(eForm.getActionName())) {
				reportBD = new MISReportBO();
				ArrayList reportList = new ArrayList<MISReportDTO>();
				if (validReport == true) {
					reportList = reportBD.getRegistrationReport(eForm, language);
					eForm.getReportDTO().setReportList(reportList);
					if (reportList.size() == 0) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "No record found");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कोई रिकॉर्ड नहीं मिला");
						forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT_SEARCH;
					} else {
						forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT;
					}
				}
				if (validReport == false) {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
					forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT_SEARCH;
				}
				actionID = null;
			}
			// ADDED BY THILAK
			if (MarketTrendReportConstants.IGRS_USER_MAPPING_REPORT_LABEL.equals(param)) {
				String jrxmlName = request.getParameter("jrxml");
				if (jrxmlName != null) {
					eForm.setJrxmlName(jrxmlName + ".jrxml");
				}
				String reportName = request.getParameter("fnName");
				if (reportName != null) {
					eForm.setReportName(reportName);
				}
				request.setAttribute("fnName", reportName);
				eForm.getReportDTO().setZoneId("");
				eForm.getReportDTO().setDistrictID("");
				eForm.setActionName("");
				eForm.setDistrictList(new ArrayList());
				// eForm.setTehsilList(new ArrayList());
				eForm.setZoneId("");
				eForm.setDistrictZoneId("");
				eForm.setViewType("");
				eForm.setCheck("");
				forwardJsp = MarketTrendReportConstants.IGRS_USER_MAPPING_REPORT;
				actionID = null;
			}
			if ("BACK_REGISTRATION_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
				eForm.getReportDTO().setDistrictID("");
				eForm.getReportDTO().setDeedID("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT_SEARCH;
				actionID = null;
			}
			if ("PDF_REGISTRATION_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				logger.debug("PDF_REGISTRATION_REPORT entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(MarketTrendReportConstants.REGISTRATION_REPORT_JRXML);
				String reportName = eForm.getReportName();
				HashMap jasperMap = new HashMap();
				jasperMap.put("fromDate", eForm.getFromDate());
				jasperMap.put("toDate", eForm.getToDate());
				if (eForm.getReportDTO().getDistrictID().equalsIgnoreCase("") || eForm.getReportDTO().getDistrictID() == null)
					jasperMap.put("districtID", "0");
				else
					jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
				jasperMap.put("distInt", Integer.parseInt(jasperMap.get("districtID").toString()));
				jasperMap.put("deedID", eForm.getReportDTO().getDeedID());
				jasperMap.put("lang", language);
				// jasperMap.put("distName", districtName);
				jasperMap.put("deedName", deedName);
				jasperMap.put("deedID", eForm.getReportDTO().getDeedID());
				jasperMap.put("path", path);

				/*
				 * if("en".equalsIgnoreCase(language)) export.generatePDF(jasperMap, reportPath, "RegistrationReport", response); else
				 */
				export.getJasperPdfviewerFromImg(jasperMap, reportPath, "RegistrationReport", response, path, "L");

				logger.debug("PDF_REGISTRATION_REPORT exit" + reportName);
				forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT;
				actionID = null;
			}
			if ("EXCEL_REGISTRATION_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				JrxmlExportUtility export = new JrxmlExportUtility();
				logger.debug("EXCEL_REGISTRATION_REPORT entered");
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(MarketTrendReportConstants.REGISTRATION_REPORT_EXCEL_JRXML);
				String reportName = eForm.getReportName();
				HashMap jasperMap = new HashMap();
				jasperMap.put("fromDate", eForm.getFromDate());
				jasperMap.put("toDate", eForm.getToDate());
				if (eForm.getReportDTO().getDistrictID().equalsIgnoreCase("") || eForm.getReportDTO().getDistrictID() == null)
					jasperMap.put("districtID", "0");
				else
					jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
				jasperMap.put("distInt", Integer.parseInt(jasperMap.get("districtID").toString()));
				jasperMap.put("deedID", eForm.getReportDTO().getDeedID());
				// jasperMap.put("distName", districtName);
				jasperMap.put("deedName", deedName);
				jasperMap.put("lang", language);
				jasperMap.put("path", path);
				export.generateXLS(jasperMap, reportPath, "RegistrationReport", response);
				eForm.setActionName("");
				logger.debug("EXCEL_REGISTRATION_REPORT exit" + reportName);
				forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT;
				actionID = null;
			}
			String fwdPage = (String) request.getParameter("fwdName");
			if (fwdPage != null && "drillDownCount".equalsIgnoreCase(fwdPage)) {
				String distID = (String) request.getParameter("distName");
				String docNo = (String) request.getParameter("downloadDoc");
				String deedID = (String) request.getParameter("deedID");
				String tehsilID = (String) request.getParameter("tehsilName");

				/*
				 * if(deedID==null) eForm.getReportDTO().setDrillDeedID(eForm.getReportDTO ().getDeedID());
				 */
				if (distID != null) {
					eForm.getReportDTO().setDrillDistID(distID);
					eForm.getReportDTO().setDistrictName(reportBD.getDistrictName(distID, language));
					if (deedID != null) {
						eForm.getReportDTO().setDrillDeedID(deedID);
						eForm.getReportDTO().setDeedName(reportBD.getDeedName(deedID, language));
					}
					if (tehsilID != null)
						eForm.getReportDTO().setDrillTehsilID(tehsilID);

					// ArrayList reportList =
					// reportBD.getRegistrationReportDrill(
					// eForm, language, distID, deedID, tehsilID);
					// eForm.getReportDTO().setSubReportList(reportList);
				}
				if (docNo != null) {

					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					String uniqueNumber = reportBD.getRegTxnID(docNo);
					if (null == uniqueNumber) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज़ डाउनलोड नहीं हुआ");
					} else if (uniqueNumber.equals("")) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज़ डाउनलोड नहीं हुआ");
					} else {
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
						metaDataInfo.setUniqueNo(uniqueNumber);
						metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
						metaDataInfo.setType("F");
						String guid = GUIDGenerator.getGUID();
						String downloadPath = pr.getValue("igrs_upload_path");
						// String EstampPath =
						// CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
						// String EstampPath =
						// CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
						String EstampPath = downloadPath + File.separator + guid;

						File folder = new File(EstampPath.toString());
						if (!folder.exists()) {
							System.out.println(folder.toString());

							folder.mkdirs();

						}
						String tempPath = "";
						if (null==metaDataInfo.getUniqueNo()) {
							throw new Exception();
						}
						if (metaDataInfo.getUniqueNo().equals("")) {
							throw new Exception();
						}
						// int result =
						// docOperations.downloadDocument(connDetails,metaDataInfo,CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode(),CommonConstant.MODULE_NAME);
						int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath.toString(), CommonConstant.MODULE_NAME);
						File[] listOfFiles = folder.listFiles();
						for (int z = 0; z < listOfFiles.length; z++) {
							tempPath = listOfFiles[z].getPath();

						}
						logger.debug("download result---------->" + result);
						byte bytes[] = DMSUtility.getDocumentBytes(tempPath);
						if (bytes != null) {
							DMSUtility.downloadDocument(response, tempPath, bytes);
						} else {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज़ डाउनलोड नहीं हुआ");
						}
					}
				}
				forwardJsp = MarketTrendReportConstants.REGISTRATION_SUB_REPORT;
				actionID = null;
			}
			if (fwdPage != null && "timeBasedCount".equalsIgnoreCase(fwdPage)) {

				String docNo = (String) request.getParameter("downloadDoc");

				if (docNo != null) {

					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					String uniqueNumber = reportBD.getRegTxnID(docNo);
					if (null == uniqueNumber) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज़ डाउनलोड नहीं हुआ");
					} else if (uniqueNumber.equals("")) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज़ डाउनलोड नहीं हुआ");
					} else {
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
						metaDataInfo.setUniqueNo(uniqueNumber);
						metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
						metaDataInfo.setType("F");
						String guid = GUIDGenerator.getGUID();
						String downloadPath = pr.getValue("igrs_upload_path");
						// String EstampPath =
						// CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
						// String EstampPath =
						// CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
						String EstampPath = downloadPath + File.separator + guid;

						File folder = new File(EstampPath.toString());
						if (!folder.exists()) {
							System.out.println(folder.toString());

							folder.mkdirs();

						}
						String tempPath = "";
						if (null==metaDataInfo.getUniqueNo()) {
							throw new Exception();
						}
						if (metaDataInfo.getUniqueNo().equals("")) {
							throw new Exception();
						}
						// int result =
						// docOperations.downloadDocument(connDetails,metaDataInfo,CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode(),CommonConstant.MODULE_NAME);
						int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath.toString(), CommonConstant.MODULE_NAME);
						File[] listOfFiles = folder.listFiles();
						for (int z = 0; z < listOfFiles.length; z++) {
							tempPath = listOfFiles[z].getPath();

						}
						logger.debug("download result---------->" + result);
						byte bytes[] = DMSUtility.getDocumentBytes(tempPath);
						if (bytes != null) {
							DMSUtility.downloadDocument(response, tempPath, bytes);
						} else {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज़ डाउनलोड नहीं हुआ");
						}
					}
				}
				forwardJsp = MarketTrendReportConstants.TIME_REGISTRATION_REPORT;
				actionID = null;

			}
			if ("PDF_DRILLDOWN_REGISTRATION_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				logger.debug("PDF_DRILLDOWN_REGISTRATION_REPORT entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(MarketTrendReportConstants.REGISTRATION_DRILLDOWN_REPORT_JRXML);
				String reportName = eForm.getReportName();
				String districtdrillName = reportBD.getDistrictName(eForm.getReportDTO().getDrillDistID(), language);
				String deeddrillName = reportBD.getDeedName(eForm.getReportDTO().getDrillDeedID(), language);
				districtdrillName = districtdrillName.equalsIgnoreCase("") ? " ALL " : districtdrillName;
				deeddrillName = deeddrillName.equalsIgnoreCase("") ? " ALL " : deeddrillName;
				HashMap jasperMap = new HashMap();
				if (eForm.getReportDTO().getDrillDeedID() == null)
					eForm.getReportDTO().setDrillDeedID("");
				jasperMap.put("fromDate", eForm.getFromDate());
				jasperMap.put("toDate", eForm.getToDate());
				if (eForm.getReportDTO().getDrillDistID() == null || eForm.getReportDTO().getDrillDistID().equalsIgnoreCase(""))
					jasperMap.put("districtID", "0");
				else
					jasperMap.put("districtID", eForm.getReportDTO().getDrillDistID());
				jasperMap.put("deedID", eForm.getReportDTO().getDrillDeedID());
				jasperMap.put("tehsilID", eForm.getReportDTO().getDrillTehsilID());
				jasperMap.put("lang", language);
				jasperMap.put("distName", districtdrillName);
				jasperMap.put("deedName", deeddrillName);
				jasperMap.put("lang", language);
				jasperMap.put("path", path);
				// export.generatePDF(jasperMap, reportPath,
				// "RegistrationSubReport", response);
				export.getJasperPdfviewerFromImg(jasperMap, reportPath, "RegistrationSubReport", response, path, "L");
				logger.debug("PDF_DRILLDOWN_REGISTRATION_REPORT exit" + reportName);
				forwardJsp = MarketTrendReportConstants.REGISTRATION_SUB_REPORT;
				actionID = null;
			}
			if ("EXCEL_DRILLDOWN_REGISTRATION_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				JrxmlExportUtility export = new JrxmlExportUtility();
				logger.debug("EXCEL_DRILLDOWN_REGISTRATION_REPORT entered");
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
				if (eForm.getReportDTO().getDeedID() == null)
					eForm.getReportDTO().setDeedID("");
				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(MarketTrendReportConstants.REGISTRATION_DRILLDOWN_EXCEL_REPORT_JRXML);
				String reportName = eForm.getReportName();
				String districtdrillName = reportBD.getDistrictName(eForm.getReportDTO().getDrillDistID(), language);
				String deeddrillName = reportBD.getDeedName(eForm.getReportDTO().getDrillDeedID(), language);
				districtdrillName = districtdrillName.equalsIgnoreCase("") ? " ALL " : districtdrillName;
				deeddrillName = deeddrillName.equalsIgnoreCase("") ? " ALL " : deeddrillName;
				HashMap jasperMap = new HashMap();
				jasperMap.put("fromDate", eForm.getFromDate());
				jasperMap.put("toDate", eForm.getToDate());
				if (eForm.getReportDTO().getDrillDistID() == null || eForm.getReportDTO().getDrillDistID().equalsIgnoreCase(""))
					jasperMap.put("districtID", "0");
				else
					jasperMap.put("districtID", eForm.getReportDTO().getDrillDistID());
				jasperMap.put("tehsilID", eForm.getReportDTO().getDrillTehsilID());
				jasperMap.put("deedID", eForm.getReportDTO().getDrillDeedID());
				jasperMap.put("distName", districtdrillName);
				jasperMap.put("deedName", deeddrillName);
				jasperMap.put("lang", language);
				jasperMap.put("path", path);
				export.generateXLS(jasperMap, reportPath, "RegistrationSubReport", response);
				eForm.setActionName("");
				logger.debug("EXCEL_DRILLDOWN_REGISTRATION_REPORT exit" + reportName);
				forwardJsp = MarketTrendReportConstants.REGISTRATION_SUB_REPORT;
				actionID = null;
			}
			if (fwdPage != null && "drillDownSlot".equalsIgnoreCase(fwdPage)) {

				String distID = (String) request.getParameter("distName");
				String docNo = (String) request.getParameter("downloadDoc");
				String report = (String) request.getParameter("report");
				if (distID != null) {
					ArrayList reportList = reportBD.getSlotStatReportDrill(eForm, language, distID, report);
					eForm.getReportDTO().setSubReportList(reportList);
					eForm.setReportName("slot");
				}

				forwardJsp = MarketTrendReportConstants.REGISTRATION_SUB_REPORT;
				actionID = null;

			}
			if ("BACK_SLOT_SUB_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				eForm.getReportDTO().setSubReportList(new ArrayList());
				forwardJsp = MarketTrendReportConstants.SLOT_STATISTICS_REPORT;
				actionID = null;
			}
			if ("BACK_REGISTRATION_SUB_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				eForm.getReportDTO().setSubReportList(new ArrayList());
				forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT;
				actionID = null;
			}
			if (MarketTrendReportConstants.SLOT_STATISTICS_REPORT_LABEL.equals(param)) {
				eForm.setFromDate("");
				eForm.setToDate("");

				eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
				eForm.getReportDTO().setDistrictID("");
				eForm.getReportDTO().setOfficeId("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.SLOT_STATISTICS_REPORT_SEARCH;
				actionID = null;
			}
			if (MarketTrendReportConstants.GET_OFFICE_SLOT_REPORT.equals(eForm.getActionName())) {

				eForm.getReportDTO().setUserType("3");
				ArrayList officeList = reportBD.getOfficeNameDetailsMISDist(eForm.getReportDTO(), language);
				eForm.setOfficeNameList(officeList);
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.SLOT_STATISTICS_REPORT_SEARCH;
				actionID = null;
			}
			if ("SUBMIT_SLOT_STATISTICS_REPORT_ACTION".equalsIgnoreCase(eForm.getActionName())) {
				reportBD = new MISReportBO();
				ArrayList reportList = new ArrayList<MISReportDTO>();
				if (validReport == true) {
					reportList = reportBD.getSlotRegistrationReport(eForm, language);
					ArrayList reportTotal = reportBD.getSlotRegistrationReportTotal(eForm);
					eForm.getReportDTO().setReportList(reportList);
					if (reportTotal != null && reportTotal.size() > 0) {
						ArrayList ret = (ArrayList) reportTotal.get(0);
						eForm.getReportDTO().setDistrictCount(ret.get(0).toString());
						eForm.getReportDTO().setTotSlotAvailable(ret.get(1).toString());
						eForm.getReportDTO().setTotSlotBooked(ret.get(2).toString());
						eForm.getReportDTO().setTotRegComp(ret.get(3).toString());
						eForm.getReportDTO().setTotDocDel(ret.get(4).toString());
						eForm.getReportDTO().setTotSlotPending(ret.get(5).toString());

					}
					if (reportList.size() == 0) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "No record found");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कोई रिकॉर्ड नहीं मिला");
						forwardJsp = MarketTrendReportConstants.SLOT_STATISTICS_REPORT_SEARCH;
					} else {
						forwardJsp = MarketTrendReportConstants.SLOT_STATISTICS_REPORT;
					}
				}
				if (validReport == false) {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
					forwardJsp = MarketTrendReportConstants.SLOT_STATISTICS_REPORT_SEARCH;
				}
				actionID = null;
			}
			if ("BACK_SLOT_STATISTICS_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
				eForm.getReportDTO().setDistrictID("");
				eForm.getReportDTO().setOfficeId("");
				eForm.getReportDTO().setDeedID("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.SLOT_STATISTICS_REPORT_SEARCH;
				actionID = null;
			}
			if (MarketTrendReportConstants.TIME_REGISTRATION_REPORT_LABEL.equals(param)) {
				String sameReport = request.getParameter("sameReport");
				if (sameReport != null) {
					eForm.setRadioSearch(sameReport);
				} else {
					eForm.setRadioSearch("N");
				}
				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDistrictID("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.TIME_REGISTRATION_REPORT_SEARCH;
				actionID = null;
			}
			if ("SUBMIT_TIME_REGISTRATION_REPORT_ACTION".equalsIgnoreCase(eForm.getActionName())) {
				reportBD = new MISReportBO();
				ArrayList reportList = new ArrayList<MISReportDTO>();
				if (validReport == true) {
					reportList = reportBD.getTimeRegistrationReport(eForm, language);
					eForm.getReportDTO().setReportList(reportList);
					if (reportList.size() == 0) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "No record found");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कोई रिकॉर्ड नहीं मिला");
						forwardJsp = MarketTrendReportConstants.TIME_REGISTRATION_REPORT_SEARCH;
					} else {
						forwardJsp = MarketTrendReportConstants.TIME_REGISTRATION_REPORT;
					}
				}
				if (validReport == false) {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
					forwardJsp = MarketTrendReportConstants.TIME_REGISTRATION_REPORT_SEARCH;
				}
				actionID = null;
			}
			if ("BACK_TIME_REGISTRATION_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDistrictID("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.TIME_REGISTRATION_REPORT_SEARCH;
				actionID = null;
			}
			/*
			 * if ("PDF_DRILLDOWN_TIMEBASED_REPORT".equalsIgnoreCase(eForm .getActionName())) { logger.debug("PDF_DRILLDOWN_REGISTRATION_REPORT entered"); JrxmlExportUtility export = new JrxmlExportUtility(); PropertiesFileReader prop = PropertiesFileReader .getInstance("resources.igrs");
			 * 
			 * String path = prop.getValue("pdf.location"); String reportPath = path .concat(MarketTrendReportConstants.TIMEBASED_REPORT_JRXML); String reportName = eForm.getReportName(); HashMap jasperMap = new HashMap(); if (eForm.getReportDTO().getDeedID() == null) eForm.getReportDTO().setDeedID(""); jasperMap.put("fromDate", eForm.getFromDate()); jasperMap.put("toDate", eForm.getToDate()); if (eForm.getReportDTO().getDistrictID() == null || eForm.getReportDTO().getDistrictID()
			 * .equalsIgnoreCase("")) jasperMap.put("distID", 0); else jasperMap.put("distID", eForm.getReportDTO() .getDistrictID()); jasperMap.put("distName", districtName);
			 * 
			 * jasperMap.put("lang", language); jasperMap.put("path", path); // export.generatePDFDiffSize(jasperMap, reportPath, // "TimeBasedRegReport", response,612,1018); export.getJasperPdfviewerFromImgDiffSize(jasperMap, reportPath, "TimeBasedRegReport", response, path, "L", 612, 1018); logger.debug("PDF_DRILLDOWN_REGISTRATION_REPORT exit" + reportName); forwardJsp = MarketTrendReportConstants.TIME_REGISTRATION_REPORT; actionID = null; }
			 */
			/*
			 * if ("EXCEL_DRILLDOWN_TIMEBASED_REPORT".equalsIgnoreCase(eForm .getActionName())) { JrxmlExportUtility export = new JrxmlExportUtility(); logger.debug("EXCEL_DRILLDOWN_REGISTRATION_REPORT entered"); PropertiesFileReader prop = PropertiesFileReader .getInstance("resources.igrs"); if (eForm.getReportDTO().getDeedID() == null) eForm.getReportDTO().setDeedID(""); String path = prop.getValue("pdf.location"); String reportPath = path
			 * .concat(MarketTrendReportConstants.TIMEBASED_REPORT_EXCEL_JRXML); String reportName = eForm.getReportName(); HashMap jasperMap = new HashMap(); jasperMap.put("fromDate", eForm.getFromDate()); jasperMap.put("toDate", eForm.getToDate()); if (eForm.getReportDTO().getDistrictID() == null || eForm.getReportDTO().getDistrictID() .equalsIgnoreCase("")) jasperMap.put("distID", 0); else jasperMap.put("distID", eForm.getReportDTO() .getDistrictID()); jasperMap.put("distName", districtName);
			 * jasperMap.put("lang", language); jasperMap.put("path", path); export.generateXLS(jasperMap, reportPath, "TimeBasedRegReport", response); eForm.setActionName(""); logger.debug("EXCEL_DRILLDOWN_REGISTRATION_REPORT exit" + reportName); forwardJsp = MarketTrendReportConstants.TIMEBASED_REPORT_EXCEL_JRXML; actionID = null; }
			 */
			if (MarketTrendReportConstants.SERVICE_PROVIDER_REPORT_LABEL.equals(param)) {
				String sameReport = request.getParameter("sameReport");
				if (sameReport != null) {
					eForm.setRadioSearch(sameReport);
				} else {
					eForm.setRadioSearch("N");
				}
				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDistrictID("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.SERVICE_PROVIDER_REPORT_SEARCH;
				actionID = null;
			}
			if ("SUBMIT_SERVICE_PROVIDER_REPORT_ACTION".equalsIgnoreCase(eForm.getActionName())) {
				reportBD = new MISReportBO();
				ArrayList reportList = new ArrayList<MISReportDTO>();
				if (validReport == true) {
					reportList = reportBD.getSPEstampReport(eForm, language);
					eForm.getReportDTO().setReportList(reportList);
					if (reportList.size() == 0) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "No record found");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कोई रिकॉर्ड नहीं मिला");
						forwardJsp = MarketTrendReportConstants.SERVICE_PROVIDER_REPORT_SEARCH;
					} else {
						forwardJsp = MarketTrendReportConstants.SERVICE_PROVIDER_REPORT;
					}
				}
				if (validReport == false) {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
					forwardJsp = MarketTrendReportConstants.SERVICE_PROVIDER_REPORT_SEARCH;
				}
				actionID = null;
			}
			if ("BACK_SERVICE_PROVIDER_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDistrictID("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.SERVICE_PROVIDER_REPORT_SEARCH;
				actionID = null;
			}
			if (MarketTrendReportConstants.SP_CREDIT_LMT_REPORT_LABEL.equals(param)) {
				String jrxmlName = request.getParameter("jrxml");
				if (jrxmlName != null) {
					eForm.setJrxmlName(jrxmlName + ".jrxml");
				}
				String reportName = request.getParameter("fnName");
				if (reportName != null) {
					eForm.setReportName(reportName);
				}
				request.setAttribute("fnName", reportName);
				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDistrictID("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.SP_CREDIT_LMT_REPORT_SEARCH;
				actionID = null;
			}
			// Added by Manu
			if (MarketTrendReportConstants.UST_PENALTY_REPORT_JRXML.equals(param)) {
				String jrxmlName = request.getParameter("jrxml");
				if (jrxmlName != null) {
					eForm.setJrxmlName(jrxmlName + ".jrxml");
				}
				String reportName = request.getParameter("fnName");
				if (reportName != null) {
					eForm.setReportName(reportName);
				}
				request.setAttribute("fnName", reportName);
				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDistrictID("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.UST_PENALTY_REPORT_SEARCH;
				actionID = null;
				// return mapping.findForward("USTPenaltyReport.jsp");
			}
			if ("EXPORT_REPORT_TO_PDF".equalsIgnoreCase(eForm.getActionName())) {
				logger.debug("EXPORT_REPORT_TO_PDF entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(eForm.getJrxmlName());
				String reportName = eForm.getReportName();
				HashMap jasperMap = new HashMap();
				jasperMap.put("fromDate", eForm.getFromDate());
				jasperMap.put("toDate", eForm.getToDate());
				jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
				jasperMap.put("lang", language);
				jasperMap.put("path", path);
				// jasperMap.put("distName", districtName);
				if (validReport == true) {
					// export.generatePDF(jasperMap, reportPath, reportName,
					// response);
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, reportName, response, path, "L");
				} else {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
				}

				request.setAttribute("fnName", reportName);
				logger.debug("EXPORT_REPORT_TO_PDF exited" + reportName);
				forwardJsp = MarketTrendReportConstants.SP_CREDIT_LMT_REPORT_SEARCH;
				actionID = null;
			}
			if ("EXPORT_REPORT_TO_XLS".equalsIgnoreCase(eForm.getActionName())) {
				logger.debug("EXPORT_REPORT_TO_XLS entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(eForm.getJrxmlName());
				String reportName = eForm.getReportName();
				HashMap jasperMap = new HashMap();
				jasperMap.put("fromDate", eForm.getFromDate());
				jasperMap.put("toDate", eForm.getToDate());
				jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
				jasperMap.put("lang", language);
				jasperMap.put("path", path);
				// jasperMap.put("distName", districtName);
				if (validReport == true) {
					export.generateXLS(jasperMap, reportPath, reportName, response);
				} else {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
				}
				request.setAttribute("fnName", reportName);
				eForm.setActionName("");
				logger.debug("EXPORT_REPORT_TO_EXCEL exited" + reportName);
				forwardJsp = MarketTrendReportConstants.SP_CREDIT_LMT_REPORT_SEARCH;
				actionID = null;
			}
			if ("EXPORT_REPORT_TO_HTML".equalsIgnoreCase(eForm.getActionName())) {
				logger.debug("EXPORT_REPORT_TO_HTML entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(eForm.getJrxmlName());
				String reportName = eForm.getReportName();
				HashMap jasperMap = new HashMap();
				jasperMap.put("fromDate", eForm.getFromDate());
				jasperMap.put("toDate", eForm.getToDate());
				jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
				jasperMap.put("lang", language);
				jasperMap.put("path", path);
				// jasperMap.put("distName", districtName);
				if (validReport == true) {
					export.generateHTML(jasperMap, reportPath, reportName, response);
				} else {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
				}
				request.setAttribute("fnName", reportName);
				logger.debug("EXPORT_REPORT_TO_HTML exited" + reportName);
				forwardJsp = MarketTrendReportConstants.SP_CREDIT_LMT_REPORT_SEARCH;
				actionID = null;
			}
			if (MarketTrendReportConstants.COMMON_REPORT_LABEL.equals(param)) {
				String jrxmlName = request.getParameter("jrxml");
				if (jrxmlName != null) {
					eForm.setJrxmlName(jrxmlName + ".jrxml");
				}
				String OffcMand = request.getParameter("OffcMand");
				if (OffcMand != null) {
					eForm.setOffcMand(OffcMand);
				} else {
					eForm.setOffcMand("");
				}
				eForm.setFromDate("");
				eForm.setToDate("");
				String reportName = request.getParameter("fnName");
				if (reportName != null) {
					eForm.setReportName(reportName);
				}
				request.setAttribute("fnName", reportName);
				eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
				eForm.getReportDTO().setDistrictID("");
				eForm.getReportDTO().setOfficeId("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.COMMON_REPORT_SEARCH;
				actionID = null;
			}
			if ("EXPORT_COMMON_REPORT_TO_PDF".equalsIgnoreCase(eForm.getActionName())) {
				logger.debug("EXPORT_COMMON_REPORT_TO_PDF entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(eForm.getJrxmlName());
				String reportName = eForm.getReportName();
				HashMap jasperMap = new HashMap();
				jasperMap.put("fromDate", eForm.getFromDate());
				jasperMap.put("toDate", eForm.getToDate());
				jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
				jasperMap.put("officeID", eForm.getReportDTO().getOfficeId());
				jasperMap.put("lang", language);
				jasperMap.put("path", path);
				// jasperMap.put("distName", districtName);
				jasperMap.put("officeName", eForm.getReportDTO().getOfficeName());
				if (validReport == true) {
					// export.generatePDF(jasperMap, reportPath, reportName,
					// response);
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, reportName, response, path, "L");
					logger.debug("EXPORT_COMMON_REPORT_TO_PDF exited" + reportName);
				} else {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
				}
				if ("SP License Summary Report".equalsIgnoreCase(reportName)) {
					forwardJsp = MarketTrendReportConstants.SP_LIC_SUMM_SEARCH;
				} else {
					forwardJsp = MarketTrendReportConstants.COMMON_REPORT_SEARCH;
				}
				actionID = null;
			}

			// Added by Thilak
			if ("EXCEL_CR88_REPORT1".equalsIgnoreCase(eForm.getActionName())) {
				// eForm.setCheck("");
				JrxmlExportUtility export = new JrxmlExportUtility();
				logger.debug("EXCEL_IGRS_USER_MAPPING_REPORT entered");
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(MarketTrendReportConstants.IGRS_USER_MAPPING_REPORT_EXCEL_JRXML);
				String reportName = eForm.getReportName();
				HashMap jasperMap = new HashMap();

				eForm.setCheck("");
				logger.debug("Zone Id " + eForm.getZoneId());
				if (eForm.getZoneId().equalsIgnoreCase("-1"))

					jasperMap.put("zoneID", "");
				else
					jasperMap.put("zoneID", eForm.getZoneId());
				logger.debug("District Id " + eForm.getDistrictID());
				if (eForm.getDistrictID().equalsIgnoreCase("-1"))

					jasperMap.put("districtID", "");
				else
					jasperMap.put("districtID", eForm.getDistrictID());
				jasperMap.put("districtName", eForm.getDistrictZoneName());
				jasperMap.put("lang", language);
				jasperMap.put("fromDate", eForm.getFromDate());
				jasperMap.put("toDate", eForm.getToDate());
				// jasperMap.put("path", path);
				// boolean flag=
				if (validateIgrsUserMapping == true) {
					export.generateXLS(jasperMap, reportPath, reportName, response);
				} else {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
				}
				/*
				 * boolean flag=export.generateNonEmptyXLS(jasperMap, reportPath, "CR88Report1Excel", response); if(flag==false) { eForm.setCheck("A"); }else{ eForm.setCheck("B"); }
				 */
				eForm.setActionName("");
				logger.debug("EXCEL_IGRS_USER_MAPPING_REPORT exit" + reportName);
				forwardJsp = MarketTrendReportConstants.IGRS_USER_MAPPING_REPORT;
				actionID = null;
			}

			if ("PDF_CR88_REPORT1".equalsIgnoreCase(eForm.getActionName())) {
				JrxmlExportUtility export = new JrxmlExportUtility();
				logger.debug("PDF_IGRS_USER_MAPPING_REPORT entered");
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(MarketTrendReportConstants.IGRS_USER_MAPPING_REPORT_PDF_JRXML);
				String reportName = eForm.getReportName();
				HashMap jasperMap = new HashMap();

				logger.debug("Zone Id " + eForm.getZoneId());
				jasperMap.put("zoneID", eForm.getZoneId());
				logger.debug("District Id " + eForm.getDistrictID());
				jasperMap.put("districtID", eForm.getDistrictID());
				jasperMap.put("districtName", eForm.getDistrictZoneName());
				jasperMap.put("lang", language);
				jasperMap.put("path", path);
				jasperMap.put("fromDate", eForm.getFromDate());
				jasperMap.put("toDate", eForm.getToDate());
				if (validateIgrsUserMapping == true) {
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "IgrsUserMappingReportPdf", response, path, "L");
				} else {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
				}
				eForm.setActionName("");
				logger.debug("PDF_IGRS_USER_MAPPING_REPORT exit" + reportName);
				forwardJsp = MarketTrendReportConstants.IGRS_USER_MAPPING_REPORT;
				actionID = null;
			}

			if ("EXPORT_COMMON_REPORT_TO_XLS".equalsIgnoreCase(eForm.getActionName())) {
				logger.debug("EXPORT_COMMON_REPORT_TO_XLS entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				String jrxmlName = eForm.getJrxmlName();
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
				if (eForm.getReportName().equals("Registration Details and Delivery Report"))
					jrxmlName = MarketTrendReportConstants.REGISTRATION_REPORT_DEL_DET_EXCEL_JRXML;

				else if (eForm.getReportName().equals("UST Global Report"))
					jrxmlName = MarketTrendReportConstants.UST_GLOBAL_REPORT_EXCEL_JRXML;

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(jrxmlName);
				String reportName = eForm.getReportName();
				HashMap jasperMap = new HashMap();
				jasperMap.put("fromDate", eForm.getFromDate());
				jasperMap.put("toDate", eForm.getToDate());
				jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
				jasperMap.put("officeID", eForm.getReportDTO().getOfficeId());
				jasperMap.put("lang", language);
				jasperMap.put("path", path);
				// jasperMap.put("distName", districtName);
				if (validReport == true) {
					export.generateXLS(jasperMap, reportPath, reportName, response);
				} else {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
				}
				eForm.setActionName("");
				logger.debug("EXPORT_COMMON_REPORT_TO_XLS exited" + reportName);
				forwardJsp = MarketTrendReportConstants.COMMON_REPORT_SEARCH;
				actionID = null;
			}
			if (MarketTrendReportConstants.SP_LIC_SUMM.equals(param)) {
				String jrxmlName = request.getParameter("jrxml");
				if (jrxmlName != null) {
					eForm.setJrxmlName(jrxmlName + ".jrxml");
				}
				eForm.setFromDate("");
				eForm.setToDate("");
				String reportName = request.getParameter("fnName");
				if (reportName != null) {
					eForm.setReportName(reportName);
				}
				request.setAttribute("fnName", reportName);
				// eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
				// eForm.getReportDTO().setDistrictID("");
				// eForm.getReportDTO().setOfficeId("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.SP_LIC_SUMM_SEARCH;
				actionID = null;
			}

			if ("districtAction".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setSrList(new ArrayList());
				eForm.setTehsilList(new ArrayList());
				eForm.setDeedID("");
				eForm.setInstID("");

				if ("DIGO".equalsIgnoreCase(officer)) {

					eForm.setDistrictID("-1");
				}
				eForm.setOfficeId("-1");
				eForm.setQuestion("");

				eForm.setDurFrom("");
				eForm.setDurTo("");
				String select[] = new String[1];
				select[0] = "-1";
				eForm.setSelectedClauses(select);
				eForm.setDeedId("");
				eForm.setRangeId("");
				eForm.setPropertyId("");
				eForm.setPropertyL2Id("");
				eForm.setPropertyL2List(new ArrayList());
				// eForm.setQuestion("N");
				eForm.setTehsilId("-1");

				if (eForm.getUser().equalsIgnoreCase("DI")) {
					// eForm.setDistrictID("-1");
					// eForm.setDistrictZoneId("-1");
					ArrayList list = reportBd.getDistDIGList(eForm.getZoneId(), language);
					eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));
					if (list.size() > 0)
						eForm.setDistrictList(list);
					else
						eForm.setDistrictList(new ArrayList());

				} else if (eForm.getUser().equalsIgnoreCase("I")) {
					eForm.setAreaTypeList(reportBd.getAreTypea(eForm.getLanguage()));
					eForm.setZoneId("");
					eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));
					eForm.setDistrictList(new ArrayList());
				} else if (eForm.getUser().equalsIgnoreCase("DR")) {
					// logger.debug("Entered DR");

					ArrayList distList = reportBd.getDistList(officeId);

					if (distList.size() > 0) {
						MISReportDTO a = (MISReportDTO) distList.get(0);
						eForm.setDistrictZoneId(a.getDistrictID());
						eForm.setDistrictZoneName(a.getDistrictName());
						eForm.setZoneId(a.getZoneId());

					}

					/*
					 * ArrayList tehsilList = reportBd.getTehsil(eForm .getDistrictID(), eForm.getLanguage());
					 */
					/*
					 * if (tehsilList.size() > 0) { MISReportDTO a = (MISReportDTO) tehsilList.get(0); eForm.setTehsilId(a.getTehsilId()); eForm.setTehsilName(a.getTehsilName()); // eForm.setZoneId(a.getZoneId()); }
					 */

					eForm.setDistrictList(reportBd.getDistList(officeId));
					eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));
					eForm.setAreaTypeList(reportBd.getAreTypea(eForm.getLanguage()));
					eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));
					// eForm.setDistrictList(distId)
					ArrayList list = reportBd.getDistDIGList(eForm.getZoneId(), language);
					if (list.size() > 0)
						eForm.setDistrictList(list);
					else
						eForm.setDistrictList(new ArrayList());
				}
				eForm.setActionName("");

				forwardJsp = "inspectionReportSearch";
				actionID = null;
			}

			if ("zoneAction".equalsIgnoreCase(eForm.getActionName()))

			{

				eForm.setSrList(new ArrayList());
				eForm.setTehsilList(new ArrayList());

				eForm.setDeedID("");
				eForm.setInstID("");

				eForm.setOfficeId("-1");

				eForm.setQuestion("");

				eForm.setDurFrom("");
				eForm.setDurTo("");

				String select[] = new String[1];
				select[0] = "-1";
				eForm.setSelectedClauses(select);
				eForm.setDeedId("");
				eForm.setRangeId("");
				eForm.setPropertyId("");
				eForm.setPropertyL2Id("");
				eForm.setPropertyL2List(new ArrayList());
				if (eForm.getUser().equalsIgnoreCase("I")) {
					eForm.setZoneId("-1");
					eForm.setDistrictID("-1");

				} else if (eForm.getUser().equalsIgnoreCase("DI")) {
					System.out.println("inside DI");
					eForm.setDistrictID("-1");
					eForm.setDistrictName("-1");

				}

				ArrayList districtList = reportBd.getDistDIGListForZone(eForm.getZoneId(), language);
				/*
				 * eForm.setDistrictZoneId("-1"); eForm.setDistrictZoneName("Select");
				 */
				// eForm.setQuestion("N");
				eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));
				eForm.setDistrictList(reportBd.getDistDIGListForZone(eForm.getZoneId(), language));
				/*
				 * if( districtList.size()>0) {
				 * 
				 * MISReportDTO a = (MISReportDTO) districtList.get(0); eForm.setDistrictName(a.getDistrictName()); eForm.setDistrictID(a.getDistrictID());
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * //eForm.setDistrictList(reportBd.getDistDIGList(eForm.getZoneId ())); // forwardJsp="inspectionReportSearch";
				 * 
				 * }
				 */forwardJsp = "inspectionReportSearch";
			}

			if ("stateAction".equalsIgnoreCase(eForm.getActionName()))

			{
				eForm.setSrList(new ArrayList());
				eForm.setTehsilList(new ArrayList());

				eForm.setDeedId("");

				// eForm.setDeedList(new ArrayList());
				// eForm.setInstList(new ArrayList());
				eForm.setDeedID("");
				eForm.setInstID("");
				eForm.setQuestion("");

				eForm.setOfficeId("-1");

				eForm.setDurFrom("");
				eForm.setDurTo("");
				String select[] = new String[1];
				select[0] = "-1";
				eForm.setSelectedClauses(select);
				eForm.setDeedId("");
				eForm.setRangeId("");
				eForm.setPropertyId("");
				eForm.setPropertyL2Id("");
				eForm.setPropertyL2List(new ArrayList());
				if (eForm.getUser().equalsIgnoreCase("I")) {
					eForm.setDistrictList(new ArrayList());

				}

				eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));

				// eForm.setQuestion("N");
				eForm.setZoneId("");
				forwardJsp = "inspectionReportSearch";

			}

			if ("districtOption".equalsIgnoreCase(eForm.getActionName())) {

				if ("DIGO".equalsIgnoreCase(officer)) {

					eForm.setDistrictID("-1");
				}
				String select[] = new String[1];
				select[0] = "-1";
				eForm.setSelectedClauses(select);

				if (eForm.getUser().equalsIgnoreCase("DI")) {
					ArrayList list = reportBd.getDistDIGList(eForm.getZoneId(), language);
					eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));
					if (list.size() > 0)
						eForm.setDistrictList(list);
					else
						eForm.setDistrictList(new ArrayList());

				} else if (eForm.getUser().equalsIgnoreCase("I")) {
					eForm.setZoneId("");
					eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));
					eForm.setDistrictList(new ArrayList());
				} else if (eForm.getUser().equalsIgnoreCase("DR")) {

					ArrayList distList = reportBd.getDistList(officeId);

					if (distList.size() > 0) {
						MISReportDTO a = (MISReportDTO) distList.get(0);
						eForm.setDistrictZoneId(a.getDistrictID());
						eForm.setDistrictZoneName(a.getDistrictName());
						eForm.setZoneId(a.getZoneId());
						eForm.setFromDate("");
						eForm.setToDate("");

					}

					eForm.setDistrictList(reportBd.getDistList(officeId));
					eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));
					// eForm.setDistrictList(distId)
					ArrayList list = reportBd.getDistDIGList(eForm.getZoneId(), language);
					if (list.size() > 0)
						eForm.setDistrictList(list);
					else
						eForm.setDistrictList(new ArrayList());
				}
				eForm.setActionName("");

				forwardJsp = "igrsUserMappingReport";
				actionID = null;
			}

			if ("zoneOption".equalsIgnoreCase(eForm.getActionName()))

			{
				String select[] = new String[1];
				select[0] = "-1";
				eForm.setSelectedClauses(select);
				if (eForm.getUser().equalsIgnoreCase("I")) {
					eForm.setZoneId("-1");
					eForm.setDistrictID("-1");

				} else if (eForm.getUser().equalsIgnoreCase("DI")) {
					System.out.println("inside DI");
					eForm.setDistrictID("-1");
					eForm.setDistrictName("-1");

				}

				ArrayList districtList = reportBd.getDistDIGListForZone(eForm.getZoneId(), language);

				eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));
				eForm.setDistrictList(reportBd.getDistDIGListForZone(eForm.getZoneId(), language));
				eForm.setFromDate("");
				eForm.setToDate("");
				/*
				 * if( districtList.size()>0) {
				 * 
				 * MISReportDTO a = (MISReportDTO) districtList.get(0); eForm.setDistrictName(a.getDistrictName()); eForm.setDistrictID(a.getDistrictID());
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * //eForm.setDistrictList(reportBd.getDistDIGList(eForm.getZoneId ())); // forwardJsp="inspectionReportSearch";
				 * 
				 * }
				 */forwardJsp = "igrsUserMappingReport";
			}

			if ("stateOption".equalsIgnoreCase(eForm.getActionName()))

			{

				String select[] = new String[1];
				select[0] = "-1";
				eForm.setSelectedClauses(select);
				if (eForm.getUser().equalsIgnoreCase("I")) {
					eForm.setDistrictList(new ArrayList());

				}

				eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));

				eForm.setZoneId("");
				eForm.setFromDate("");
				eForm.setToDate("");
				forwardJsp = "igrsUserMappingReport";

			}

			if ("getDistrictZoneOption".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setDistrictList(reportBd.getDistrictZone(eForm.getZoneId(), eForm.getLanguage()));

				forwardJsp = "igrsUserMappingReport";

			}

			if ("setIGstateZoneOption".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));
				eForm.setDistrictList(reportBd.getDistDIGListForZone(eForm.getZoneId(), eForm.getLanguage()));
				forwardJsp = "igrsUserMappingReport";

			}

			if ("setTehsilDR".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));
				// eForm.setTehsilId(eForm.getTehsilId());
				eForm.setQuestion("");
				eForm.setPropertyID("");
				eForm.setPropertySubID("");
				eForm.setPropertySubList(new ArrayList());
				eForm.setSubAreaList(new ArrayList());
				eForm.setWardPatwariList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setRangeId("");

				eForm.setSrList(new ArrayList());

				forwardJsp = "inspectionReportSearch";

			}

			if ("setDIGdistTehsil".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));

				eForm.setQuestion("");
				eForm.setPropertyID("");
				eForm.setPropertySubID("");
				eForm.setPropertySubList(new ArrayList());
				eForm.setSubAreaList(new ArrayList());
				eForm.setWardPatwariList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setRangeId("");
				eForm.setSrList(new ArrayList());

				forwardJsp = "inspectionReportSearch";

			}

			if ("setDIGZoneTehsil".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));

				eForm.setQuestion("");
				eForm.setPropertyID("");
				eForm.setPropertySubList(new ArrayList());
				eForm.setSubAreaList(new ArrayList());
				eForm.setWardPatwariList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setRangeId("");

				forwardJsp = "inspectionReportSearch";

			}

			if ("setIGdistTehsil".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));

				eForm.setQuestion("");
				eForm.setPropertyID("");
				eForm.setPropertySubID("");
				eForm.setPropertySubList(new ArrayList());
				eForm.setSubAreaList(new ArrayList());
				eForm.setWardPatwariList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setRangeId("");
				eForm.setSrList(new ArrayList());

				forwardJsp = "inspectionReportSearch";

			}

			if ("setIGZoneTehsil".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));
				// eForm.setTehsilList(new ArrayList());
				eForm.setQuestion("");
				eForm.setPropertyID("");
				eForm.setPropertySubList(new ArrayList());
				eForm.setSubAreaList(new ArrayList());
				eForm.setWardPatwariList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setRangeId("");
				eForm.setSrList(new ArrayList());

				forwardJsp = "inspectionReportSearch";

			}

			if ("setIGstateZone".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setZoneList(reportBd.getZone("1", eForm.getLanguage()));
				eForm.setDistrictList(reportBd.getDistDIGListForZone(eForm.getZoneId(), eForm.getLanguage()));

				eForm.setTehsilList(new ArrayList());
				eForm.setQuestion("");
				eForm.setPropertyID("");
				eForm.setPropertySubList(new ArrayList());
				eForm.setSubAreaList(new ArrayList());
				eForm.setWardPatwariList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setRangeId("");
				eForm.setSrList(new ArrayList());

				forwardJsp = "inspectionReportSearch";

			}

			if ("setIGstatedist".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));

				// eForm.setTehsilList(new ArrayList());
				eForm.setQuestion("");
				eForm.setPropertyID("");
				eForm.setPropertySubList(new ArrayList());
				eForm.setSubAreaList(new ArrayList());
				eForm.setWardPatwariList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setRangeId("");
				eForm.setSrList(new ArrayList());

				forwardJsp = "inspectionReportSearch";

			}

			if ("setIGstateTehsil".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));

				// eForm.setTehsilList(new ArrayList());
				eForm.setQuestion("");
				eForm.setPropertyID("");
				eForm.setPropertySubList(new ArrayList());
				eForm.setSubAreaList(new ArrayList());
				eForm.setWardPatwariList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setRangeId("");
				eForm.setSrList(new ArrayList());

				forwardJsp = "inspectionReportSearch";

			}

			if ("setInstrument".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setInstList(reportBd.getInstrument(eForm.getDeedID(), eForm.getLanguage()));

				forwardJsp = "inspectionReportSearch";

			}

			if ("answerRadio".equalsIgnoreCase(eForm.getActionName())) {
				eForm.setSroOfficeList(new ArrayList());
				eForm.setSrList(new ArrayList());
				if (eForm.getQuestion().equalsIgnoreCase("Y")) {
					if (!eForm.getViewType().equalsIgnoreCase("District")) {
						// eForm.setDistrictZoneId("-1");
						eForm.setTehsilList(new ArrayList());
					}
					eForm.setMohallaList(new ArrayList());
					eForm.setWardList(new ArrayList());
					// eForm.setTehsilId("-1");
					eForm.setAreaTypeId("-1");
					eForm.setMohallaId("-1");
					eForm.setWardId("-1");
					eForm.setWardPatwariId("-1");
					eForm.setSubAreaId("-1");
					eForm.setColonyId("-1");
					eForm.setWardIds("-1");

					if ("DRO".equalsIgnoreCase(officer)) {

						eForm.setTehsilId(eForm.getTehsilId());
						eForm.setAreaTypeList(reportBd.getAreTypea(eForm.getLanguage()));
						eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));

					}

					if ("IGO".equalsIgnoreCase(officer)) {
						eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));

					}

				}

				if (eForm.getQuestion().equalsIgnoreCase("N")) {
					eForm.setSroOfficeList(new ArrayList());
					eForm.setSrList(new ArrayList());
					if ("DRO".equalsIgnoreCase(officer)) {

						eForm.setSroOfficeList(new ArrayList());
						eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));

						ArrayList abc = reportBd.getSroListInsp(eForm.getTehsilId(), eForm.getLanguage());

						eForm.setSroOfficeList(abc);

					}

					if ("DIGO".equalsIgnoreCase(officer)) {
						eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));
						eForm.setSroOfficeList(reportBd.getSroListInsp(eForm.getTehsilId(), eForm.getLanguage()));

					}

					if ("IGO".equalsIgnoreCase(officer)) {

						eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));
						eForm.setSroOfficeList(reportBd.getSroListInsp(eForm.getTehsilId(), eForm.getLanguage()));

					}

				}

				/*
				 * ArrayList tehsilList=new ArrayList(); tehsilList=reportBd.getTehsil (eForm.getDistrictId(),eForm.getLanguage()); eForm.setTehsilList(tehsilList);
				 */
				//	
				// eForm.setTehsilList(reportBd.getTehsil(eForm.getZoneId(),eForm.getLanguage()));
				// eForm.setDistrictID("-1");
				// System.out.println("DistrictID" +eForm.getDistrictID());
				/*
				 * if("DRO".equalsIgnoreCase(officer)) { eForm.setDistrictZoneId(eForm.getDistrictID()); eForm.setDistrictZoneName(eForm.getDistrictName());
				 * 
				 * }
				 * 
				 * if("IGO".equalsIgnoreCase(officer)) { eForm.setDistrictListZone (reportBd.getDistrictZone(eForm.getZoneId (),eForm.getLanguage())); }
				 */

				if ("DIGO".equalsIgnoreCase(officer)) {
					eForm.setDistrictList(reportBd.getDistrictZone(eForm.getZoneId(), eForm.getLanguage()));
					eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));

				}

				// eForm.setDistrictId("-1");
				// eForm.setDistrictID("-1");

				// System.out.println(eForm.getTehsilId());
				// eForm.setTehsilId(eForm.getTehsilId());
				// System.out.println(eForm.getAreaTypeId());
				forwardJsp = "inspectionReportSearch";

			}

			if ("getPropertyL2".equalsIgnoreCase(eForm.getActionName())) {
				// eForm.getClauseId();
				String propertyID = eForm.getPropertyID();
				ArrayList propertyL2 = new ArrayList();
				/*
				 * try { String prop[] = propertyID.split("~");
				 * 
				 * propertyL2 = reportBd.getPropertyL2(prop[0], eForm .getLanguage()); } catch (Exception k) { logger.debug(k.getMessage()); }
				 */

				propertyL2 = reportBd.getPropertyL2(propertyID, eForm.getLanguage());

				// logger.info("getSpotCriteriaList --  "+spForm.getSpotCriteriaList().size());
				// request.setAttribute("spotCriteriaList",spForm.getSpotCriteriaList());
				eForm.setDistrictListZone(reportBd.getDistDIGList(eForm.getZoneId(), language));
				eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));
				eForm.setPropertySubList((propertyL2));
				forwardJsp = "inspectionReportSearch";

			}

			if ("setTehsil".equalsIgnoreCase(eForm.getActionName())) {
				System.out.println(eForm.getTehsilId());

				eForm.setTehsilId(eForm.getTehsilId());
				eForm.setDistrictListZone(reportBd.getDistDIGList(eForm.getZoneId(), language));

				ArrayList list = reportBd.getSroListInsp(eForm.getTehsilId(), eForm.getLanguage());
				if (list.size() > 0) {
					eForm.setSroOfficeList(list);
				} else
					eForm.setSroOfficeList(new ArrayList());

				forwardJsp = "inspectionReportSearch";

			}

			if ("setSro".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setSrList(reportBd.getSrList(eForm.getOfficeId()));

				forwardJsp = "inspectionReportSearch";
			}

			if ("setSr".equalsIgnoreCase(eForm.getActionName())) {

				forwardJsp = "inspectionReportSearch";
			}

			if ("setArea".equalsIgnoreCase(eForm.getActionName())) {
				logger.info("Inside GetSubArea Action");

				String areaId = eForm.getAreaTypeId();
				ReportBO reportBO = new ReportBO();
				// eForm.setDistrictList(reportBd.getDistDIGList(eForm.getZoneId()));

				if ("2".equalsIgnoreCase(areaId)) {
					eForm.setSubAreaList(reportBO.getSubArea(language, areaId, eForm.getTehsilId(), "Y"));
				} else {
					eForm.setSubAreaList(reportBO.getSubArea(language, areaId, eForm.getTehsilId(), "N"));

				}

				eForm.setWardPatwariList(new ArrayList());
				eForm.setMohallaList(new ArrayList());

				forwardJsp = "inspectionReportSearch";

			}

			if ("getWard".equalsIgnoreCase(eForm.getActionName())) {

				String statusSubId = eForm.getSubAreaId();
				ReportBO reportBO = new ReportBO();
				String municipalFlag = reportBO.getMuncipalFlag(statusSubId);
				if ("RF".equalsIgnoreCase(municipalFlag)) {
					eForm.setMunicipalCheckDisplay("Y");
					eForm.setMunicipalFlag("");
				} else if ("NF".equalsIgnoreCase(municipalFlag)) {
					eForm.setMunicipalCheckDisplay("N");
					eForm.setMunicipalFlag("Y");
				} else {
					eForm.setMunicipalCheckDisplay("N");
					eForm.setMunicipalFlag("N");
				}
				// eForm.setDistrictList(reportBd.getDistDIGList(eForm.getZoneId()));
				eForm.setWardPatwariList(reportBO.getWardPatwari(language, statusSubId, eForm.getTehsilId()));
				eForm.setMohallaList(new ArrayList());
				forwardJsp = "inspectionReportSearch";

			}

			if ("getDistrictZone".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setDistrictList(reportBd.getDistrictZone(eForm.getZoneId(), eForm.getLanguage()));
				eForm.setTehsilList(new ArrayList());
				eForm.setQuestion("");
				eForm.setPropertyID("");
				eForm.setPropertySubList(new ArrayList());
				eForm.setSubAreaList(new ArrayList());
				eForm.setWardPatwariList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setRangeId("");
				eForm.setSrList(new ArrayList());

				forwardJsp = "inspectionReportSearch";

			}

			if ("getMohallaAction".equalsIgnoreCase(eForm.getActionName())) {
				ReportBO reportBO = new ReportBO();
				logger.info("Inside Get Colony Action");
				// eForm.setDistrictList(reportBd.getDistDIGList(eForm.getZoneId()));
				eForm.setMohallaList(reportBO.getColonyName(language, eForm.getWardIds()));
				forwardJsp = "inspectionReportSearch";

			}

			if ("getPropertyDistrict".equalsIgnoreCase(eForm.getActionName())) {
				/*
				 * eForm.setZoneList(reportBd.getZone("1",eForm.getLanguage()));
				 * 
				 * ArrayList list=new ArrayList();
				 */

				if (officer.equalsIgnoreCase("IGO")) {
					ArrayList list = new ArrayList();
					list = reportBd.getDistrictZone(eForm.getZoneId(), eForm.getLanguage());

					eForm.setDistrictList(list);
					/*
					 * if(list.size()>0){
					 * 
					 * MISReportDTO a = (MISReportDTO) list.get(0); eForm.setDistrictName(a.getDistrictName()); eForm.setDistrictID(a.getDistrictID()); eForm.setDistrictList(list); }
					 */
				}
				/*
				 * list=reportBd.getDistDIGList(eForm.getZoneId()); if(list.size()>0) {
				 * 
				 * MISReportDTO a = (MISReportDTO) list.get(0); eForm.setDistrictName(a.getDistrictName()); eForm.setDistrictID(a.getDistrictID());
				 * 
				 * eForm.setDistrictList(list); }
				 */

				eForm.setTehsilList(reportBd.getTehsil(eForm.getDistrictID(), eForm.getLanguage()));
				eForm.setQuestion("");
				eForm.setPropertyID("");
				eForm.setPropertySubList(new ArrayList());
				eForm.setSubAreaList(new ArrayList());
				eForm.setWardPatwariList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setRangeId("");
				eForm.setSrList(new ArrayList());

				forwardJsp = "inspectionReportSearch";

			}

			if (MarketTrendReportConstants.INSPECTION_REPORT_LABEL // inspection
			// Report
			// Forward // edited by Siddhartha
			.equals(param)) {

				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDistrictID("");
				eForm.setActionName("");
				eForm.getReportDTO().setOfficeId("");
				eForm.setDistrictList(new ArrayList());
				eForm.setTehsilList(new ArrayList());
				eForm.setZoneId("");
				eForm.setDistrictZoneId("");
				eForm.setDeedId("");
				eForm.setQuestion("");
				eForm.setViewType("");
				eForm.setDeedList(new ArrayList());
				eForm.setInstList(new ArrayList());
				eForm.setDeedID("");
				eForm.setDurFrom("");
				eForm.setDurTo("");
				eForm.setSroOfficeList(new ArrayList());
				eForm.setOfficeId("-1");

				// eForm.setAreaTypeList("");
				// eForm.setZoneList("");

				ArrayList rangeList = new ArrayList();
				ArrayList clauseList = new ArrayList();
				ArrayList deedList = new ArrayList();
				ArrayList propertyList = new ArrayList();
				ArrayList tehsilList = new ArrayList();

				try {
					rangeList = reportBd.getCurrentRangeList();
					clauseList = reportBd.getCurrentSubClauseList1(eForm.getLanguage());
					deedList = reportBd.getAllDeedList(eForm.getLanguage());
					propertyList = reportBd.getAllpropertyList(eForm.getLanguage());
					tehsilList = reportBd.getTehsil(distId, eForm.getLanguage());
				} catch (Exception e) {

					e.printStackTrace();
				}

				eForm.setRangeList(rangeList);
				eForm.setClauseList(clauseList);
				eForm.setDeedList(deedList);
				eForm.setPropertyList(propertyList);

				forwardJsp = MarketTrendReportConstants.INSPECTION_REPORT_SEARCH;
				actionID = null;

			}
			if ("SUBMIT_INSPECTION_REPORT_ACTION".equalsIgnoreCase(eForm.getActionName())) {
				reportBD = new MISReportBO();
				ArrayList reportList = new ArrayList<MISReportDTO>();

				String days = reportDao.validateReportInspDay(eForm.getDurFrom(), eForm.getDurTo());

				if (validReportInsp == true) {
					reportList = reportBD.getInspectionReport(eForm, language);
					eForm.getReportDTO().setReportList(reportList);
					if (reportList.size() == 0) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "No record found");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कोई रिकॉर्ड नहीं मिला");
						forwardJsp = MarketTrendReportConstants.INSPECTION_REPORT;
					} else {
						forwardJsp = MarketTrendReportConstants.INSPECTION_REPORT;
					}
				}
				if (validReportInsp == false) {
					eForm.getReportDTO().setReportList(new ArrayList());
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + days + " days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कृपया  " + days + " दिनों से कम अवधि का चयन करें");
					// forwardJsp = MarketTrendReportConstants.INSPECTION_REPORT;
					forwardJsp = MarketTrendReportConstants.INSPECTION_REPORT_SEARCH;
				}
				actionID = null;
			}
			/*
			 * if("GET_DISTRICT".equalsIgnoreCase(eForm.getActionName())){ reportBD=new MISReportBO(); ArrayList reportList=new ArrayList<MISReportDTO>(); reportList= reportBD.getDistrictDetails(userId); eForm.getReportDTO().setReportList(reportList);
			 * 
			 * }
			 */
			if (fwdPage != null && "inspectionCount".equalsIgnoreCase(fwdPage)) {

				String docNo = (String) request.getParameter("downloadDoc");
				String key = (String) request.getParameter("key");
				if (key != null) {
					DMSUtility.downloadDocument(response, "Upload", DMSUtility.getDocumentBytes(key));

				}
				if (docNo != null) {

					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					if (docNo.equals("")) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज़ डाउनलोड नहीं हुआ");
					} else {
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
						metaDataInfo.setUniqueNo(docNo);
						metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
						metaDataInfo.setType("F");
						String guid = GUIDGenerator.getGUID();
						String downloadPath = pr.getValue("igrs_upload_path");
						// String EstampPath =
						// CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
						// String EstampPath =
						// CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
						String EstampPath = downloadPath + File.separator + guid;

						File folder = new File(EstampPath.toString());
						if (!folder.exists()) {
							System.out.println(folder.toString());

							folder.mkdirs();

						}
						String tempPath = "";
						if (null==metaDataInfo.getUniqueNo()) {
							throw new Exception();
						}
						if (metaDataInfo.getUniqueNo().equals("")) {
							throw new Exception();
						}
						// int result =
						// docOperations.downloadDocument(connDetails,metaDataInfo,CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode(),CommonConstant.MODULE_NAME);
						int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath.toString(), CommonConstant.MODULE_NAME);
						File[] listOfFiles = folder.listFiles();
						for (int z = 0; z < listOfFiles.length; z++) {
							tempPath = listOfFiles[z].getPath();

						}
						logger.debug("download result---------->" + result);
						byte bytes[] = DMSUtility.getDocumentBytes(tempPath);
						if (bytes != null) {
							DMSUtility.downloadDocument(response, tempPath, bytes);
						} else {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज़ डाउनलोड नहीं हुआ");
						}
					}
				}
				forwardJsp = MarketTrendReportConstants.INSPECTION_REPORT;
				actionID = null;

			}
			if ("BACK_INSPECTION_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDistrictID("");
				eForm.setActionName("");
				eForm.setReportList(new ArrayList());
				forwardJsp = MarketTrendReportConstants.INSPECTION_REPORT_SEARCH;
				actionID = null;
			}
			if (MarketTrendReportConstants.DELAY_REPORT_LABEL.equals(param)) {
				String jrxmlName = request.getParameter("jrxml");
				if (jrxmlName != null) {
					eForm.setJrxmlName(jrxmlName + ".jrxml");
				}
				/*
				 * String OffcMand = request.getParameter("OffcMand"); if(OffcMand!=null){ eForm.setOffcMand(OffcMand); }else{ eForm.setOffcMand(""); }
				 */
				eForm.setFromDate("");
				eForm.setToDate("");
				String reportName = request.getParameter("fnName");
				if (reportName != null) {
					eForm.setReportName(reportName);
				}
				request.setAttribute("fnName", reportName);
				eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
				eForm.getReportDTO().setDistrictID("");
				eForm.getReportDTO().setOfficeId("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.DELAY_REPORT_SEARCH;
				actionID = null;
			}
			if (MarketTrendReportConstants.ESTAMP_REPORT_LABEL.equals(param)) {
				String jrxmlName = request.getParameter("jrxml");
				if (jrxmlName != null) {
					eForm.setJrxmlName(jrxmlName + ".jrxml");
				}
				/*
				 * String OffcMand = request.getParameter("OffcMand"); if(OffcMand!=null){ eForm.setOffcMand(OffcMand); }else{ eForm.setOffcMand(""); }
				 */
				eForm.setFromDate("");
				eForm.setToDate("");
				String reportName = request.getParameter("fnName");
				if (reportName != null) {
					eForm.setReportName(reportName);
				}
				request.setAttribute("fnName", reportName);
				eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
				eForm.getReportDTO().setDistrictID("");
				eForm.getReportDTO().setOfficeId("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SEARCH;
				actionID = null;
			}
			if ("SUBMIT_ESTAMP_REPORT_ACTION".equalsIgnoreCase(eForm.getActionName())) {
				reportBD = new MISReportBO();
				ArrayList reportList = new ArrayList<MISReportDTO>();
				if (validReport == true) {
					reportList = reportBD.getEstampReport(eForm, language);
					eForm.getReportDTO().setReportList(reportList);
					if (reportList.size() == 0) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "No record found");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कोई रिकॉर्ड नहीं मिला");
						forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SEARCH;
					} else {
						forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SCREEN1;
					}
				}
				if (validReport == false) {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
					forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SEARCH;
				}
				actionID = null;
			}
			if ("BACK_ESTAMP_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				eForm.setFromDate("");
				eForm.setToDate("");
				eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
				eForm.getReportDTO().setDistrictID("");
				eForm.getReportDTO().setDeedID("");
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SEARCH;
				actionID = null;
			}
			if (fwdPage != null && "drillDownCountEstamp".equalsIgnoreCase(fwdPage)) {
				String distID = (String) request.getParameter("distName");
				// String docNo = (String)request.getParameter("downloadDoc");
				String deedID = (String) request.getParameter("deedName");
				// String tehsilID = (String)request.getParameter("tehsilName");

				/*
				 * if(deedID==null) eForm.getReportDTO().setDrillDeedID(eForm.getReportDTO ().getDeedID());
				 */

				// eForm.getReportDTO().setDrillDistID(distID);
				eForm.getReportDTO().setDistrictName(reportBD.getDistrictName(distID, language));
				if (deedID != null) {
					eForm.getReportDTO().setDeedID(deedID);
					eForm.getReportDTO().setDeedName(reportBD.getDeedName(deedID, language));
				}

				ArrayList reportList = reportBD.getEstampReportDrill(eForm, language, distID, deedID, userId);
				eForm.getReportDTO().setSubReportList(reportList);

				forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SCREEN2;
				actionID = null;
			}
			if (fwdPage != null && "drillDownCountEstamp1".equalsIgnoreCase(fwdPage)) {
				String distID = (String) request.getParameter("distName");
				String docNo = (String) request.getParameter("downloadDoc");
				String deedID = eForm.getReportDTO().getDeedID();
				String spID = (String) request.getParameter("spID");
				eForm.getReportDTO().setUserID(spID);
				if (docNo == null) {
					ArrayList reportList = reportBD.getEstampReportDrill1(eForm, language, eForm.getReportDTO().getDistrictID(), deedID, spID);
					eForm.getReportDTO().setSubReportList1(reportList);
				}

				eForm.getReportDTO().setDeedName(reportBD.getDeedName(deedID, language));
				if (docNo != null) {

					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					if (docNo.equals("")) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज़ डाउनलोड नहीं हुआ");
					} else {
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
						metaDataInfo.setUniqueNo(docNo);
						metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
						metaDataInfo.setType("E");
						String guid = GUIDGenerator.getGUID();
						String downloadPath = pr.getValue("igrs_upload_path");
						// String EstampPath =
						// CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
						// String EstampPath =
						// CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
						String EstampPath = downloadPath + File.separator + guid;

						File folder = new File(EstampPath.toString());
						if (!folder.exists()) {
							System.out.println(folder.toString());

							folder.mkdirs();

						}
						String tempPath = "";
						if (null==metaDataInfo.getUniqueNo()) {
							throw new Exception();
						}
						if (metaDataInfo.getUniqueNo().equals("")) {
							throw new Exception();
						}
						// int result =
						// docOperations.downloadDocument(connDetails,metaDataInfo,CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode(),CommonConstant.MODULE_NAME);
						int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath.toString(), CommonConstant.MODULE_NAME);
						File[] listOfFiles = folder.listFiles();
						for (int z = 0; z < listOfFiles.length; z++) {
							tempPath = listOfFiles[z].getPath();

						}
						logger.debug("download result---------->" + result);
						byte bytes[] = DMSUtility.getDocumentBytes(tempPath);
						if (bytes != null) {
							DMSUtility.downloadDocument(response, tempPath, bytes);
						} else {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Document was not downloaded");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "दस्तावेज़ डाउनलोड नहीं हुआ");
						}
					}
				}
				forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SCREEN3;
				actionID = null;
			}
			if ("BACK_ESTAMP_REPORT_1".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SCREEN1;
				actionID = null;
			}
			if ("BACK_ESTAMP_REPORT_2".equalsIgnoreCase(eForm.getActionName())) {

				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SCREEN2;
				actionID = null;
			}
			if ("EXCEL_ESTAMP_REPORT".equalsIgnoreCase(eForm.getActionName())) {
				logger.debug("EXCEL_ESTAMP_REPORT entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(eForm.getJrxmlName());
				// String reportName=eForm.getReportName();
				HashMap jasperMap = new HashMap();
				jasperMap.put("FROM", eForm.getFromDate());
				jasperMap.put("TO", eForm.getToDate());
				jasperMap.put("DISTRICT_ID", eForm.getReportDTO().getDistrictID());
				if (eForm.getReportDTO().getDeedID().equalsIgnoreCase(""))
					jasperMap.put("DEED_ID", null);
				else
					jasperMap.put("DEED_ID", eForm.getReportDTO().getDeedID());
				jasperMap.put("lang", language);
				jasperMap.put("path", path);
				// jasperMap.put("distName", districtName);
				jasperMap.put("CREATED_BY", eForm.getReportDTO().getUserID());
				if (validReport == true) {
					export.generateXLS(jasperMap, reportPath, "Estamp_Report", response);
				} else {
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 31 days or less than that");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 31 दिन या उस से कम की रखें।");
				}
				eForm.setActionName("");
				logger.debug("EXPORT_COMMON_REPORT_TO_XLS exited");
				if ("Estamp-Report0.jrxml".equalsIgnoreCase(eForm.getJrxmlName()))
					forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SCREEN1;

				actionID = null;
			}
			if (MarketTrendReportConstants.GET_OFFICE_COMMON_EPORT.equals(eForm.getActionName())) {

				String reportName = eForm.getReportName();

				request.setAttribute("fnName", reportName);
				eForm.getReportDTO().setUserType("3");
				ArrayList officeList = reportBD.getOfficeNameDetailsMISDist(eForm.getReportDTO(), language);
				eForm.setOfficeNameList(officeList);
				eForm.setActionName("");
				forwardJsp = MarketTrendReportConstants.COMMON_REPORT_SEARCH;
				actionID = null;
			}
			if (MarketTrendReportConstants.SEARCH_REQUEST_LABEL.equals(param)) {
				eForm.setFromDate("");
				eForm.setToDate("");
				forwardJsp = MarketTrendReportConstants.SEARCH_REPORT;
				actionID = null;
			}
			if (MarketTrendReportConstants.REG_DOC_COPY_LABEL.equals(param)) {
				eForm.setFromDate("");
				eForm.setToDate("");
				forwardJsp = MarketTrendReportConstants.REG_DOC_COPY_REPORT;
				actionID = null;
			}

			if (MarketTrendReportConstants.REVENUE_SRO_WISE_URBAN_RURAL_LABEL.equals(param)) {
				/*
				 * ArrayList sroList=reportBD.getSroList(); eForm.getReportDTO().setSroList(sroList);
				 */
				eForm.getReportDTO().setMonth("");
				eForm.getReportDTO().setYear("");
				forwardJsp = MarketTrendReportConstants.SRO_URBANRURAL_REPORT;
				actionID = null;
			}
			if (MarketTrendReportConstants.REVENUE_SRO_WISE_STAMP_REG_FEE_LABEL.equals(param)) {
				eForm.getReportDTO().setMonth("");
				eForm.getReportDTO().setYear("");
				forwardJsp = MarketTrendReportConstants.SRO_STAMREGFEE_REPORT;
				actionID = null;
			}

			if (MarketTrendReportConstants.MONTHLY_REVENUE_LABEL.equals(param)) {
				dto.setYear("");
				forwardJsp = MarketTrendReportConstants.MONTHLY_REVENUE_REPORT;
				actionID = null;
			}

			if (MarketTrendReportConstants.CESS_REVENUE_LABEL.equals(param)) {
				forwardJsp = MarketTrendReportConstants.CESS_REVENUE_REPORT;
				actionID = null;
			}

			String formName = eForm.getFormName();
			if (param == null) {
				// param = (String)session.getAttribute("param");
				label = (String) session.getAttribute("label");
			} else {
				// session.setAttribute("param", param);
				session.setAttribute("label", label);
			}
			// param = null ;
			logger.debug("Parameter:-" + param);
			// /Usage Form

			if (MarketTrendReportConstants.USAGE_FORM.equals(formName)) {
				if (MarketTrendReportConstants.PERIOD_MONTH_ACTION.equals(actionID)) {
					dto.setSearchType(eForm.getRadioSearch());
					forwardJsp = MarketTrendReportConstants.USAGE_REPORT;
				}
				if (MarketTrendReportConstants.CANCEL_ACTION.equals(actionID)) {
					forwardJsp = "welcome";
				}

				if (MarketTrendReportConstants.SUBMIT_ACTION_USAGE.equals(actionID)) {

					dto.setFromDate(eForm.getFromDate());
					dto.setToDate(eForm.getToDate());
					dto.setSearchType(eForm.getRadioSearch());
					logger.debug("inside submitaction" + eForm.getFromDate() + ":" + eForm.getToDate() + ":" + dto.getSearchType() + ":" + eForm.getRadioSearch());

					// if(MarketTrendReportConstants.
					// USAGE_REQUEST_LABEL.equals(param)) {
					ArrayList list = reportBD.getUsageReport(dto);
					session.setAttribute(MarketTrendReportConstants.USAGE_REPORT_SESSION, list);
					eForm.setUsageReportList(list);
					forwardJsp = MarketTrendReportConstants.USAGE_REPORT_POPUP;
					// }
				}
				if (MarketTrendReportConstants.RESET_ACTION.equals(actionID)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					dto.setFromDate("");
					dto.setToDate("");
					eForm.setRadioSearch("");
					dto.setSearchType("");
					dto.setMonth("");
					eForm.setReportDTO(dto);
					forwardJsp = MarketTrendReportConstants.USAGE_REPORT;
				}
			}
			if (MarketTrendReportConstants.USAGE_POPUP_FORM.equals(formName)) {

				if (MarketTrendReportConstants.EXPORT_TO_PDF_ACTION.equals(actionID)) {

					ArrayList misList = (ArrayList) session.getAttribute(MarketTrendReportConstants.USAGE_REPORT_SESSION);

					File file = reportPDF.createUsageReport(misList);
					request.setAttribute(MarketTrendReportConstants.PDF_REQUEST, file);
					forwardJsp = MarketTrendReportConstants.USAGE_REPORT_POPUP;
				}

			}

			// /

			// Document Search Request
			if (MarketTrendReportConstants.SEARCH_FORM.equals(formName)) {
				if (MarketTrendReportConstants.PERIOD_MONTH_ACTION.equals(actionID)) {
					dto.setSearchType(eForm.getRadioSearch());
					forwardJsp = MarketTrendReportConstants.SEARCH_REPORT;
				}
				if (MarketTrendReportConstants.CANCEL_ACTION.equals(actionID)) {
					forwardJsp = "welcome";
				}
				logger.debug("Action Name:-" + actionID);
				if (MarketTrendReportConstants.USER_TYPE_ACTION.equals(actionID)) {
					dto.setUserType(eForm.getUserType());
					dto.setMonth(dto.getMonth());
					if ("SRO".equals(dto.getUserType()) || "DRO".equals(dto.getUserType())) {
						userTypeList = reportBD.getOfficeNameDetailsMIS(dto, language);
						eForm.setOfficeNameList(userTypeList);
					}

					forwardJsp = MarketTrendReportConstants.SEARCH_REPORT;
				}
				if (MarketTrendReportConstants.RESET_ACTION.equals(actionID)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					dto.setFromDate("");
					dto.setToDate("");
					eForm.setRadioSearch("");
					dto.setSearchType("");
					dto.setMonth("");
					dto.setOfficeId("");
					eForm.setUserType("");
					dto.setUserType("");
					eForm.setReportDTO(dto);
					forwardJsp = MarketTrendReportConstants.SEARCH_REPORT;
				}

			}
			// added by SHreeraJ
			if (MarketTrendReportConstants.SUBMIT_ACTION.equals(actionID)) {

				reportBD = new MISReportBO();

				ArrayList searchList = null;
				try {
					searchList = reportBD.getSearchReqReport(eForm.getFromDate(), eForm.getToDate(), language);
				} catch (Exception e) {
					e.printStackTrace();
				}

				eForm.getReportDTO().setReportList((ArrayList) searchList.get(1));
				eForm.getReportDTO().setTotSum((String) searchList.get(0));

				forwardJsp = MarketTrendReportConstants.SEARCH_REPORT_POPUP;
				// }
			}
			if (MarketTrendReportConstants.SEARCH_REQ_REPORT_BACK.equals(actionID)) {

				eForm.setFromDate("");
				eForm.setToDate("");

				forwardJsp = MarketTrendReportConstants.SEARCH_REPORT;
				// }
			}
			if (MarketTrendReportConstants.REG_DOC_COPY_REP_SUBMIT_ACTION.equals(actionID)) {

				ArrayList regCopyRepList = reportBD.getRegCopyReport(eForm.getFromDate(), eForm.getToDate());

				eForm.getReportDTO().setReportList((ArrayList) regCopyRepList.get(1));
				eForm.getReportDTO().setTotSum((String) regCopyRepList.get(0));

				forwardJsp = MarketTrendReportConstants.REG_DOC_COPY_REPORT_VIEW;
				// }
			}
			if (MarketTrendReportConstants.REG_DOC_COPY_REPORT_BACK.equals(actionID)) {

				eForm.setFromDate("");
				eForm.setToDate("");

				forwardJsp = MarketTrendReportConstants.REG_DOC_COPY_REPORT;
				// }
			}
			if (MarketTrendReportConstants.REV_RCPT_SRO_SUBMIT_ACTION.equals(actionID)) {
				ArrayList searchList = reportBD.getRevRcptSROReport(eForm.getReportDTO().getMonth(), eForm.getReportDTO().getYear());
				/*
				 * ArrayList subMonthList=(ArrayList)searchList.get(0); ArrayList subPrvList=(ArrayList)searchList.get(1); ArrayList subAprList=(ArrayList)searchList.get(2);
				 */

				/*
				 * for(int i=0;i<searchList.size();i++){ MISReportDTO misDTO1 = (MISReportDTO)searchList.get(i);
				 * 
				 * } MISReportDTO misDTO2 = (MISReportDTO)searchList.get(1); MISReportDTO misDTO3 = (MISReportDTO)searchList.get(2);
				 */

				if (eForm.getReportDTO().getMonth().equals("01"))
					eForm.getReportDTO().setMonthName("January");
				if (eForm.getReportDTO().getMonth().equals("02"))
					eForm.getReportDTO().setMonthName("February");
				if (eForm.getReportDTO().getMonth().equals("03"))
					eForm.getReportDTO().setMonthName("March");
				if (eForm.getReportDTO().getMonth().equals("04"))
					eForm.getReportDTO().setMonthName("April");
				if (eForm.getReportDTO().getMonth().equals("05"))
					eForm.getReportDTO().setMonthName("May");
				if (eForm.getReportDTO().getMonth().equals("06"))
					eForm.getReportDTO().setMonthName("June");
				if (eForm.getReportDTO().getMonth().equals("07"))
					eForm.getReportDTO().setMonthName("July");
				if (eForm.getReportDTO().getMonth().equals("08"))
					eForm.getReportDTO().setMonthName("August");
				if (eForm.getReportDTO().getMonth().equals("09"))
					eForm.getReportDTO().setMonthName("September");
				if (eForm.getReportDTO().getMonth().equals("10"))
					eForm.getReportDTO().setMonthName("October");
				if (eForm.getReportDTO().getMonth().equals("11"))
					eForm.getReportDTO().setMonthName("November");
				if (eForm.getReportDTO().getMonth().equals("12"))
					eForm.getReportDTO().setMonthName("December");

				eForm.getReportDTO().setReportList(searchList);

				forwardJsp = MarketTrendReportConstants.REV_RCPT_SRO_SUBMIT_VIEW_JSP;
				// }
			}
			if (MarketTrendReportConstants.REV_RCPT_SRO_BACK_JSP.equals(actionID)) {

				eForm.getReportDTO().setMonth("");
				eForm.getReportDTO().setYear("");

				forwardJsp = MarketTrendReportConstants.SRO_URBANRURAL_REPORT;

			}

			if (MarketTrendReportConstants.REV_RCPT_SRO_STAMPREGFEE_SUBMIT_ACTION.equals(actionID)) {
				ArrayList searchList = reportBD.getSROStampRegReport(eForm.getReportDTO().getMonth(), eForm.getReportDTO().getYear());
				/*
				 * ArrayList subMonthList=(ArrayList)searchList.get(0); ArrayList subPrvList=(ArrayList)searchList.get(1); ArrayList subAprList=(ArrayList)searchList.get(2);
				 */

				/*
				 * for(int i=0;i<searchList.size();i++){ MISReportDTO misDTO1 = (MISReportDTO)searchList.get(i);
				 * 
				 * } MISReportDTO misDTO2 = (MISReportDTO)searchList.get(1); MISReportDTO misDTO3 = (MISReportDTO)searchList.get(2);
				 */

				if (eForm.getReportDTO().getMonth().equals("01"))
					eForm.getReportDTO().setMonthName("January");
				if (eForm.getReportDTO().getMonth().equals("02"))
					eForm.getReportDTO().setMonthName("February");
				if (eForm.getReportDTO().getMonth().equals("03"))
					eForm.getReportDTO().setMonthName("March");
				if (eForm.getReportDTO().getMonth().equals("04"))
					eForm.getReportDTO().setMonthName("April");
				if (eForm.getReportDTO().getMonth().equals("05"))
					eForm.getReportDTO().setMonthName("May");
				if (eForm.getReportDTO().getMonth().equals("06"))
					eForm.getReportDTO().setMonthName("June");
				if (eForm.getReportDTO().getMonth().equals("07"))
					eForm.getReportDTO().setMonthName("July");
				if (eForm.getReportDTO().getMonth().equals("08"))
					eForm.getReportDTO().setMonthName("August");
				if (eForm.getReportDTO().getMonth().equals("09"))
					eForm.getReportDTO().setMonthName("September");
				if (eForm.getReportDTO().getMonth().equals("10"))
					eForm.getReportDTO().setMonthName("October");
				if (eForm.getReportDTO().getMonth().equals("11"))
					eForm.getReportDTO().setMonthName("November");
				if (eForm.getReportDTO().getMonth().equals("12"))
					eForm.getReportDTO().setMonthName("December");

				eForm.getReportDTO().setReportList(searchList);

				forwardJsp = MarketTrendReportConstants.REV_RCPT_SRO_STAMPREGFEE_VIEW_JSP;
				// }
			}
			if (MarketTrendReportConstants.REV_RCPT_SRO_STAMPREGFEE_BACK_JSP.equals(actionID)) {

				eForm.getReportDTO().setMonth("");
				eForm.getReportDTO().setYear("");

				forwardJsp = MarketTrendReportConstants.SRO_STAMREGFEE_REPORT;

			}
			if (MarketTrendReportConstants.SEARCH_POPUP_FORM.equals(formName)) {

				if (MarketTrendReportConstants.EXPORT_TO_PDF_ACTION.equals(actionID)) {

					ArrayList misList = (ArrayList) session.getAttribute(MarketTrendReportConstants.SEARCH_REPORT_SESSION);

					File file = reportPDF.createSearchRequest(misList);
					request.setAttribute(MarketTrendReportConstants.PDF_REQUEST, file);
					forwardJsp = MarketTrendReportConstants.SEARCH_REPORT_POPUP;
				}

			}

			// Revenue Receipt For SRO Urban & Rural wise

			if (MarketTrendReportConstants.REVENUE_SRO_WISE_URBAN_RURAL_FORM.equals(formName)) {
				if (MarketTrendReportConstants.PERIOD_MONTH_ACTION.equals(actionID)) {
					dto.setSearchType(eForm.getRadioSearch());
					forwardJsp = MarketTrendReportConstants.SRO_URBANRURAL_REPORT;
				}
				if (MarketTrendReportConstants.CANCEL_ACTION.equals(actionID)) {
					forwardJsp = "welcome";
				}
				if (MarketTrendReportConstants.RESET_ACTION.equals(actionID)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					dto.setFromDate("");
					dto.setToDate("");
					eForm.setRadioSearch("");
					dto.setSearchType("");
					dto.setMonth("");
					dto.setOfficeId("");
					eForm.setUserType("");
					dto.setUserType("");
					eForm.setReportDTO(dto);
					forwardJsp = MarketTrendReportConstants.SRO_URBANRURAL_REPORT;
				}
				if (MarketTrendReportConstants.SUBMIT_ACTION.equals(actionID)) {

					dto.setSearchType(eForm.getRadioSearch());
					dto.setFromDate(eForm.getFromDate());
					dto.setToDate(eForm.getToDate());
					logger.debug("inside submitaction" + eForm.getFromDate() + ":" + eForm.getToDate());

					// if(MarketTrendReportConstants.
					// USAGE_REQUEST_LABEL.equals(param)) {
					ArrayList list = reportBD.getRevenueSROURReport(dto);
					eForm.setSroURReportList(list);
					session.setAttribute(MarketTrendReportConstants.SRO_URBANRURAL_SESSION, list);
					forwardJsp = MarketTrendReportConstants.SRO_URBANRURAL_POPUP;
					// }
				}
			}

			if (MarketTrendReportConstants.SRO_URBANRURAL_POPUPFORM.equals(formName)) {
				logger.debug("Inside SRO_URBANRURAL_POPUPFORM");
				if (MarketTrendReportConstants.EXPORT_TO_PDF_ACTION.equals(actionID)) {
					logger.debug("Inside EXPORT_TO_PDF_ACTION");
					ArrayList misList = (ArrayList) session.getAttribute(MarketTrendReportConstants.SRO_URBANRURAL_SESSION);

					File file = reportPDF.createRevenueReceiptSROWiseURReport(misList);

					request.setAttribute(MarketTrendReportConstants.PDF_REQUEST, file);
					forwardJsp = MarketTrendReportConstants.SRO_URBANRURAL_POPUP;
				}

			}

			// /Monthly Revenue Receipt Judical Stamp

			if (MarketTrendReportConstants.MONTHLY_REVENUE_FORM.equals(formName)) {

				if (MarketTrendReportConstants.SUBMIT_ACTION_VI.equals(actionID)) {

					// if(MarketTrendReportConstants.
					// USAGE_REQUEST_LABEL.equals(param)) {
					ArrayList list = reportBD.getMonthlyRevenueList(dto.getYear(), language);
					eForm.setMonthlyRevenueList(list);
					String fy = dto.getYear() + "-" + (Integer.parseInt(dto.getYear().substring(2)) + 1);
					eForm.setFinancialYear(fy);
					session.setAttribute(MarketTrendReportConstants.MONTHLY_REVENUE_SESSION, list);

					forwardJsp = MarketTrendReportConstants.MONTHLY_REVENUE_POPUP;
					// }
				}
				if (MarketTrendReportConstants.CANCEL_ACTION.equals(actionID)) {
					forwardJsp = "welcome";
				}
				if (MarketTrendReportConstants.RESET_ACTION.equals(actionID)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					dto.setFromDate("");
					dto.setToDate("");
					eForm.setRadioSearch("");
					dto.setSearchType("");
					dto.setMonth("");
					dto.setOfficeId("");
					eForm.setUserType("");
					dto.setUserType("");
					dto.setYear("");
					eForm.setReportDTO(dto);
					forwardJsp = MarketTrendReportConstants.MONTHLY_REVENUE_REPORT;
				}
			}
			if (MarketTrendReportConstants.MONTHLY_REVENUE_POPUPFORM.equals(formName)) {
				logger.debug("Inside MONTHLY_REVENUE_POPUPFORM");
				if (MarketTrendReportConstants.EXPORT_TO_PDF_ACTION.equals(actionID)) {
					logger.debug("Inside EXPORT_TO_PDF_ACTION");
					ArrayList misList = (ArrayList) session.getAttribute(MarketTrendReportConstants.MONTHLY_REVENUE_SESSION);

					File file = reportPDF.createMonthlyRevenueJudicalReport(misList);

					request.setAttribute(MarketTrendReportConstants.PDF_REQUEST, file);
					forwardJsp = MarketTrendReportConstants.MONTHLY_REVENUE_POPUP;
				}

			}

			// Cess Municipal Block
			if (MarketTrendReportConstants.CESS_REVENUE_FORM.equals(formName)) {
				if (MarketTrendReportConstants.PERIOD_MONTH_ACTION.equals(actionID)) {
					dto.setSearchType(eForm.getRadioSearch());
					forwardJsp = MarketTrendReportConstants.CESS_REVENUE_REPORT;
				}
				if (MarketTrendReportConstants.CANCEL_ACTION.equals(actionID)) {
					forwardJsp = "welcome";
				}
				if (MarketTrendReportConstants.USER_TYPE_ACTION.equals(actionID)) {
					dto.setUserType(eForm.getUserType());
					dto.setMonth(dto.getMonth());
					if ("SRO".equals(dto.getUserType()) || "DRO".equals(dto.getUserType())) {
						userTypeList = reportBD.getOfficeNameDetailsMIS(dto, language);
						eForm.setOfficeNameList(userTypeList);
					}
					forwardJsp = MarketTrendReportConstants.CESS_REVENUE_REPORT;
				}
				if (MarketTrendReportConstants.RESET_ACTION.equals(actionID)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					dto.setFromDate("");
					dto.setToDate("");
					eForm.setRadioSearch("");
					dto.setSearchType("");
					dto.setMonth("");
					dto.setOfficeId("");
					eForm.setUserType("");
					dto.setUserType("");
					dto.setYear("");
					eForm.setReportDTO(dto);
					forwardJsp = MarketTrendReportConstants.CESS_REVENUE_REPORT;
				}
				if (MarketTrendReportConstants.SUBMIT_ACTION.equals(actionID)) {

					// if(MarketTrendReportConstants.
					// USAGE_REQUEST_LABEL.equals(param)) {
					dto.setFromDate(eForm.getFromDate());
					dto.setToDate(eForm.getToDate());
					dto.setSearchType(eForm.getRadioSearch());
					dto.setUserType(eForm.getUserType());
					logger.debug("DistrictId:-" + dto.getDistrictID());
					logger.debug("From Date:-" + dto.getFromDate());
					logger.debug("To Date:-" + dto.getToDate());
					HashMap map = reportBD.getCessMunicipalBlock(dto);
					ArrayList cessList = new ArrayList();

					String totalOrg = "";
					if (map != null) {
						cessList = (ArrayList) map.get("dtoList");
						totalOrg = (String) map.get("dtoTotal");
					}
					eForm.setCessMunicipalList(cessList);
					dto.setRevenueTotalOrg(totalOrg);
					session.setAttribute(MarketTrendReportConstants.CESS_REVENUE_SESSION, map);
					forwardJsp = MarketTrendReportConstants.CESS_REVENUE_POPUP;
					// }
				}
			}

			if (MarketTrendReportConstants.CESS_REVENUE_POPUPFORM.equals(formName)) {
				logger.debug("Inside MONTHLY_REVENUE_POPUPFORM");
				if (MarketTrendReportConstants.EXPORT_TO_PDF_ACTION.equals(actionID)) {
					logger.debug("Inside EXPORT_TO_PDF_ACTION");
					HashMap map = (HashMap) session.getAttribute(MarketTrendReportConstants.CESS_REVENUE_SESSION);
					ArrayList cessList = new ArrayList();
					String totalOrg = "";
					if (map != null) {
						cessList = (ArrayList) map.get("dtoList");
						totalOrg = (String) map.get("dtoTotal");
					}
					File file = reportPDF.createCessMunicipalDutyReport(cessList, totalOrg);

					request.setAttribute(MarketTrendReportConstants.PDF_REQUEST, file);
					forwardJsp = MarketTrendReportConstants.CESS_REVENUE_POPUP;
				}
				if (MarketTrendReportConstants.CANCEL_ACTION.equals(actionID)) {
					forwardJsp = "welcome";
				}

			}

			eForm.setReportDTO(dto);

		}
		eForm.setActionName("");
		eForm.setActionID("");
		eForm.setFormName("");
		return mapping.findForward(forwardJsp);
	}
}
