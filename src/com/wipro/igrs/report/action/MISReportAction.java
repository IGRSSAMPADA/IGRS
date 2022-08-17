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
 * File Name                                       : MarketTrendReportAction.java
 *
 * Description                                    : Struts Action to view Revenue Management Details
 *
 * Version Number                          : 1.0
 *
 * Created Date                                 : 31/05/2008 
 *
 * Modification History  : Created
 * Added - Slot Stastics Future Max Date Validation  - RAhul  25-OCT-16 
 */

package com.wipro.igrs.report.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.dev.RecordLister;
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
import com.wipro.igrs.report.bd.PDFGeneration;
import com.wipro.igrs.report.bd.ReportBD;
import com.wipro.igrs.report.bo.MISReportBO;
import com.wipro.igrs.report.bo.PendingCourtCaseReportBO;
import com.wipro.igrs.report.common.MarketTrendReportConstants;
import com.wipro.igrs.report.dao.ReportDAO;
import com.wipro.igrs.report.dto.MISReportDTO;
import com.wipro.igrs.report.form.MISReportForm;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.util.JrxmlExportUtility;
import com.wipro.igrs.util.PropertiesFileReader;

public class MISReportAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(MISReportAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		String forwardJsp = "";
		try {

			// HttpSession session = (HttpSession)request.getSession();

			logger.debug("my two");
			logger.debug("in Action Class");
			MISReportForm eForm = (MISReportForm) form;
			String userId = (String) session.getAttribute("UserId");
			String officeId = (String) session.getAttribute("loggedToOffice");
			String language = (String) session.getAttribute("languageLocale");
			MISReportBO reportBD = new MISReportBO();
			Report reportPDF = new Report();

			ReportDAO reportDao = new ReportDAO();

			ReportBD reportBd = new ReportBD();
			String officeType = reportBd.getOfficeType(officeId);
			ArrayList districtList = new ArrayList();

			if (officeType.equalsIgnoreCase("4")) {
				String zoneId = reportBd.getDIGZone(officeId);
				logger.debug("The Zone of DIG : " + zoneId);
				districtList = reportBd.getDistDIGList(zoneId, language);
			} else {
				districtList = reportBD.getDistrictDetailsMIS(language, officeId);
			}
			// ArrayList districtList = reportBD.getDistrictDetailsMIS(language,
			// officeId);
			String districtName = reportBD.getDistrictName(eForm.getReportDTO().getDistrictID(), language);
			String deedName = reportBD.getDeedName(eForm.getReportDTO().getDeedID(), language);
			districtName = districtName.equalsIgnoreCase("") ? language.equalsIgnoreCase("en") ? " ALL " : " सब " : districtName;
			deedName = deedName.equalsIgnoreCase("") ? language.equalsIgnoreCase("en") ? " ALL " : " सब " : deedName;
			String tehsilName = getTehsilName(eForm.getReportDTO().getTehsilIdreg(), eForm.getReportDTO().getTehsilListreg(), language);
			String srOfcName = getSrOfcName(eForm.getReportDTO().getOfficeRegId(), eForm.getReportDTO().getOffcListreg(), language);
			eForm.getReportDTO().setDeedName(deedName);
			eForm.getReportDTO().setDistrictName(districtName);
			eForm.setDistrictList(districtList);
			eForm.setAreaTypeList(reportBD.getAreaTypeDetails(language));
			eForm.setFiscalYearList(reportBD.getYearForJudicalStamp(reportBD.getYearCheck()));
			eForm.getReportDTO().setOfficeName(reportBD.getOfficeName(eForm.getReportDTO().getOfficeId()));
			boolean validReport = reportBD.validateReport(eForm.getFromDate(), eForm.getToDate());

			// request.setAttribute("districtlst",eForm);

			ArrayList userTypeList = new ArrayList();

			if (eForm != null) {

				String actionID = eForm.getActionName();
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
					eForm.getReportDTO().setOfficeReg("");
					eForm.getReportDTO().setSrNamereg("");
					eForm.getReportDTO().setTehsilreg("");
					eForm.getReportDTO().setTehsilIdreg("");
					eForm.getReportDTO().setSrUserIdreg("");
					eForm.getReportDTO().setOfficeRegId("");
					eForm.getReportDTO().setTehsilListreg(new ArrayList());
					eForm.getReportDTO().setOffcListreg(new ArrayList());
					eForm.getReportDTO().setSrListreg(new ArrayList());
					eForm.getReportDTO().setOfficeType(reportBD.getOfficeTypeID(officeId));
					forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT_SEARCH;
					actionID = null;
				}
				// ADDED BY SHUBHAM
				if (MarketTrendReportConstants.REGISTRATION_REPORT_SUMMARY.equals(param)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
					eForm.getReportDTO().setDistrictID("");
					eForm.getReportDTO().setDeedID("");
					eForm.setReportName("");
					eForm.setActionName("");
					eForm.getReportDTO().setOfficeReg("");
					eForm.getReportDTO().setSrNamereg("");
					eForm.getReportDTO().setTehsilreg("");
					eForm.getReportDTO().setTehsilIdreg("");
					eForm.getReportDTO().setSrUserIdreg("");
					eForm.getReportDTO().setOfficeRegId("");
					eForm.getReportDTO().setTehsilListreg(new ArrayList());
					eForm.getReportDTO().setOffcListreg(new ArrayList());
					eForm.getReportDTO().setSrListreg(new ArrayList());
					eForm.getReportDTO().setOfficeType(reportBD.getOfficeTypeID(officeId));
					forwardJsp = MarketTrendReportConstants.REGISTRATION_SUMMARY_REPORT_SEARCH;
					actionID = null;
				}
				if ("SUBMIT_REGISTRATION_REPORT_ACTION".equalsIgnoreCase(eForm.getActionName())) {
					reportBD = new MISReportBO();
					boolean validReportRegistration = reportBD.validateReportRegistration(eForm.getFromDate(), eForm.getToDate());// for registration report: parameter is ATT_190: made by akansha

					String reportDays = reportDao.validateReportRegistrationDay(eForm.getFromDate(), eForm.getToDate());
					ArrayList reportList = new ArrayList<MISReportDTO>();
					if (validReportRegistration == true) {
						reportList = reportBD.getRegistrationReport(eForm, language);
						eForm.getReportDTO().setReportList(reportList);
						// /ArrayList<MISReportDTO> pdfData = reportList;
						HashMap<Integer, ArrayList<String>> finalPdfData = new HashMap<Integer, ArrayList<String>>();
						for (int i = 0; i < reportList.size(); i++) {
							MISReportDTO dto1 = (MISReportDTO) reportList.get(i);
							HashMap<Integer, String> list = dto1.getRecordSet();
							for (Map.Entry<Integer, String> entry : list.entrySet()) {
								if (finalPdfData.containsKey(entry.getKey())) {
									ArrayList<String> entryValue = finalPdfData.get(entry.getKey());
									entryValue.add(entry.getValue());
									finalPdfData.put(entry.getKey(), entryValue);
								} else {
									ArrayList<String> entryValue = new ArrayList<String>(Arrays.asList(entry.getValue()));
									finalPdfData.put(entry.getKey(), entryValue);
								}
							}
						}

						PDFGeneration.createPDF(finalPdfData);
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
					if (validReportRegistration == false) {

						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + reportDays + " days or less than that");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी  " + reportDays + " दिन या उस से कम की रखें।");
						forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT_SEARCH;
					}
					actionID = null;
				}

				if ("SUBMIT_REGISTRATION_SUMMARY_REPORT_ACTION".equalsIgnoreCase(eForm.getActionName())) {
					reportBD = new MISReportBO();
					ArrayList reportList = new ArrayList<MISReportDTO>();

					String days = reportDao.validateReportDay(eForm.getFromDate(), eForm.getToDate());
					/* if (validReport == false) { */// change by rustam
					reportList = reportBD.getRegistrationSummaryReport(eForm, language);
					eForm.getReportDTO().setReportList(reportList);
					if (reportList.size() == 0) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "No record found");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कोई रिकॉर्ड नहीं मिला");
						forwardJsp = MarketTrendReportConstants.REGISTRATION_SUMMARY_REPORT_SEARCH;
					} else {
						forwardJsp = MarketTrendReportConstants.REGISTRATION_SUMMARY_REPORT;
					}
					// }
					/*
					 * if (validReport == false) { // change by rustam if (language.equalsIgnoreCase("en")) request .setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " +days+ " days or less than that"); else request .setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी  " +days+ " दिन या उस से कम की रखें।"); forwardJsp = MarketTrendReportConstants.REGISTRATION_SUMMARY_REPORT_SEARCH; }
					 */
					actionID = null;
				}
				if ("BACK_REGISTRATION_REPORT".equalsIgnoreCase(eForm.getActionName())) {
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
					eForm.getReportDTO().setDistrictID("");
					eForm.getReportDTO().setDeedID("");
					eForm.setActionName("");
					eForm.getReportDTO().setOfficeReg("");
					eForm.getReportDTO().setSrNamereg("");
					eForm.getReportDTO().setTehsilreg("");
					eForm.getReportDTO().setTehsilIdreg("");
					eForm.getReportDTO().setSrUserIdreg("");
					eForm.getReportDTO().setOfficeRegId("");
					eForm.getReportDTO().setTehsilListreg(new ArrayList());
					eForm.getReportDTO().setOffcListreg(new ArrayList());
					eForm.getReportDTO().setSrListreg(new ArrayList());

					forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT_SEARCH;
					actionID = null;
				}
				if ("BACK_REGISTRATION_SUMMARY_REPORT".equalsIgnoreCase(eForm.getActionName())) {
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.getReportDTO().setDeedTypeList(reportBD.getDeedTypeList(language));
					eForm.getReportDTO().setDistrictID("");
					eForm.getReportDTO().setDeedID("");
					eForm.setActionName("");
					eForm.getReportDTO().setOfficeReg("");
					eForm.getReportDTO().setSrNamereg("");
					eForm.getReportDTO().setTehsilreg("");
					eForm.getReportDTO().setTehsilIdreg("");
					eForm.getReportDTO().setSrUserIdreg("");
					eForm.getReportDTO().setOfficeRegId("");
					eForm.getReportDTO().setTehsilListreg(new ArrayList());
					eForm.getReportDTO().setOffcListreg(new ArrayList());
					eForm.getReportDTO().setSrListreg(new ArrayList());

					forwardJsp = MarketTrendReportConstants.REGISTRATION_SUMMARY_REPORT_SEARCH;
					actionID = null;
				}
				if ("PDF_REGISTRATION_REPORT".equalsIgnoreCase(eForm.getActionName())) {
					logger.debug("PDF_REGISTRATION_REPORT entered");
					JrxmlExportUtility export = new JrxmlExportUtility();
					PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
					// String frmDt = eForm.getFromDate(); //Added By Ankit : " 00:00:00"
					// String toDt = eForm.getToDate() ; //Added By Ankit : " 23:59:59"
					String frmDt = eForm.getFromDate() + " 00:00:00"; // Added By Ankit : " 00:00:00"
					String toDt = eForm.getToDate() + " 23:59:59"; // Added By Ankit : " 23:59:59"

					String path = prop.getValue("pdf.location");
					String reportPath = path.concat(MarketTrendReportConstants.REGISTRATION_REPORT_JRXML);
					String reportName = eForm.getReportName();
					ArrayList deedList = new ArrayList();
					ArrayList reportList = new ArrayList();
					deedList = eForm.getReportDTO().getDeedList();
					reportList = eForm.getReportDTO().getReportList();
					HashMap jasperMap = new HashMap();
					jasperMap.put("fromDate", frmDt);
					jasperMap.put("toDate", toDt);
					if (eForm.getReportDTO().getDistrictID().equalsIgnoreCase("") || eForm.getReportDTO().getDistrictID() == null)
						jasperMap.put("districtID", "0");

					else
						jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
					jasperMap.put("distInt", Integer.parseInt(jasperMap.get("districtID").toString()));
					jasperMap.put("deedID", eForm.getReportDTO().getDeedID());
					jasperMap.put("lang", language);
					jasperMap.put("distName", districtName);
					jasperMap.put("deedName", deedName);
					jasperMap.put("deedID", eForm.getReportDTO().getDeedID());
					jasperMap.put("tehsilId", eForm.getReportDTO().getTehsilIdreg());
					jasperMap.put("tehsilName", tehsilName);
					jasperMap.put("srOffice", eForm.getReportDTO().getOfficeRegId());
					jasperMap.put("srOfficeName", srOfcName);
					jasperMap.put("srId", eForm.getReportDTO().getSrUserIdreg());
					jasperMap.put("path", path);
					/*
					 * if("en".equalsIgnoreCase(language)) export.generatePDF(jasperMap, reportPath, "RegistrationReport", response); else
					 */

					logger.info(" JasperMap : " + jasperMap.toString());

					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "RegistrationReport", response, path, "L");

					logger.debug("PDF_REGISTRATION_REPORT exit" + reportName);
					forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT;
					actionID = null;
				}
				// ADD THIS METHOD BY RUSTAM REGISTRATION_SUMMARY_REPORT WITHOUT M-VIEW

				if ("PDF_REGISTRATION_SUMMARY_REPORT".equalsIgnoreCase(eForm.getActionName())) {
					logger.debug("PDF_REGISTRATION_SUMMARY_REPORT entered");
					String totalCount = eForm.getReportDTO().getTotRegDate();
					if (totalCount.equals("0")) {
						return new ActionForward("/jsp/reports/FailureReport.jsp");
					}
					JrxmlExportUtility export = new JrxmlExportUtility();
					PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

					String pattern = "dd/MM/yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String date = simpleDateFormat.format(new Date());
					String frmDt = date + " 00:00:00";
					String toDt = date + " 23:59:59";

					String path = prop.getValue("pdf.location");
					String reportPath = path.concat(MarketTrendReportConstants.REGISTRATION_SUMM_REPORT_JRXML);
					String reportName = eForm.getReportName();
					ArrayList deedList = new ArrayList();
					ArrayList reportList = new ArrayList();
					deedList = eForm.getReportDTO().getDeedList();
					reportList = eForm.getReportDTO().getReportList();
					HashMap jasperMap = new HashMap();
					jasperMap.put("fromDate", frmDt);
					jasperMap.put("toDate", toDt);
					if (eForm.getReportDTO().getDistrictID().equalsIgnoreCase("") || eForm.getReportDTO().getDistrictID() == null)
						jasperMap.put("districtID", "0");

					else
						jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
					jasperMap.put("distInt", Integer.parseInt(jasperMap.get("districtID").toString()));
					jasperMap.put("deedID", eForm.getReportDTO().getDeedID());
					jasperMap.put("lang", language);
					jasperMap.put("distName", districtName);
					jasperMap.put("deedName", deedName);
					jasperMap.put("deedID", eForm.getReportDTO().getDeedID());
					jasperMap.put("tehsilId", eForm.getReportDTO().getTehsilIdreg());
					jasperMap.put("tehsilName", tehsilName);
					jasperMap.put("srOffice", eForm.getReportDTO().getOfficeRegId());
					jasperMap.put("srOfficeName", srOfcName);
					jasperMap.put("srId", eForm.getReportDTO().getSrUserIdreg());
					jasperMap.put("path", path);

					logger.info(" JasperMap : " + jasperMap.toString());

					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "RegistrationReport", response, path, "L");

					logger.debug("PDF_REGISTRATION_SUMMARY_REPORT exit" + reportName);
					forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT;
					actionID = null;
				}

				// ADD THIS METHOD BY RUSTAM EXCEL_REGISTRATION_SUMMARY_REPORT WITHOUT M-VIEW

				if ("EXCEL_REGISTRATION_SUMMARY_REPORT".equalsIgnoreCase(eForm.getActionName())) {
					JrxmlExportUtility export = new JrxmlExportUtility();
					logger.debug("EXCEL_REGISTRATION_SUMMARY_REPORT entered");
					String totalCount = eForm.getReportDTO().getTotRegDate();
					if (totalCount.equals("0")) {
						return new ActionForward("/jsp/reports/FailureReport.jsp");
					}
					PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
					String pattern = "dd/MM/yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String date = simpleDateFormat.format(new Date());
					String frmDt = date + " 00:00:00";
					String toDt = date + " 23:59:59";

					String path = prop.getValue("pdf.location");
					String reportPath = path.concat(MarketTrendReportConstants.REGISTRATION_SUMM_REPORT_EXCEL_JRXML);
					String reportName = eForm.getReportName();
					HashMap jasperMap = new HashMap();
					jasperMap.put("fromDate", frmDt);
					jasperMap.put("toDate", toDt);
					if (eForm.getReportDTO().getDistrictID().equalsIgnoreCase("") || eForm.getReportDTO().getDistrictID() == null)
						jasperMap.put("districtID", "0");
					else
						jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
					jasperMap.put("distInt", Integer.parseInt(jasperMap.get("districtID").toString()));
					jasperMap.put("deedID", eForm.getReportDTO().getDeedID());
					jasperMap.put("distName", districtName);
					jasperMap.put("deedName", deedName);
					jasperMap.put("tehsilId", eForm.getReportDTO().getTehsilIdreg());
					jasperMap.put("tehsilName", tehsilName);
					jasperMap.put("srOffice", eForm.getReportDTO().getOfficeRegId());
					jasperMap.put("srOfficeName", srOfcName);
					jasperMap.put("srId", eForm.getReportDTO().getSrUserIdreg());
					jasperMap.put("lang", language);
					jasperMap.put("path", path);
					export.generateXLS(jasperMap, reportPath, "RegistrationReport", response);
					// eForm.setActionName("");
					logger.debug("EXCEL_REGISTRATION_SUMMARY_REPORT exit" + reportName);
					forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT;
					actionID = null;
				}

				if ("EXCEL_REGISTRATION_REPORT".equalsIgnoreCase(eForm.getActionName())) {
					JrxmlExportUtility export = new JrxmlExportUtility();
					logger.debug("EXCEL_REGISTRATION_REPORT entered");
					PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

					String frmDt = eForm.getFromDate() + " 00:00:00"; // Added By Ankit : " 00:00:00"
					String toDt = eForm.getToDate() + " 23:59:59"; // Added By Ankit : " 23:59:59"

					String path = prop.getValue("pdf.location");
					String reportPath = path.concat(MarketTrendReportConstants.REGISTRATION_REPORT_EXCEL_JRXML);
					String reportName = eForm.getReportName();
					HashMap jasperMap = new HashMap();
					jasperMap.put("fromDate", frmDt);
					jasperMap.put("toDate", toDt);
					if (eForm.getReportDTO().getDistrictID().equalsIgnoreCase("") || eForm.getReportDTO().getDistrictID() == null)
						jasperMap.put("districtID", "0");
					else
						jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
					jasperMap.put("distInt", Integer.parseInt(jasperMap.get("districtID").toString()));
					jasperMap.put("deedID", eForm.getReportDTO().getDeedID());
					jasperMap.put("distName", districtName);
					jasperMap.put("deedName", deedName);
					jasperMap.put("tehsilId", eForm.getReportDTO().getTehsilIdreg());
					jasperMap.put("tehsilName", tehsilName);
					jasperMap.put("srOffice", eForm.getReportDTO().getOfficeRegId());
					jasperMap.put("srOfficeName", srOfcName);
					jasperMap.put("srId", eForm.getReportDTO().getSrUserIdreg());
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
					String DID = (String) request.getParameter("deedID");

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

						ArrayList reportList = reportBD.getRegistrationReportDrill(eForm, language, distID, deedID, tehsilID, DID);
						eForm.getReportDTO().setSubReportList(reportList);
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

					String frmDt = eForm.getFromDate() + " 00:00:00"; // Added By Ankit : " 00:00:00"
					String toDt = eForm.getToDate() + " 23:59:59"; // Added By Ankit : " 23:59:59"

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
					jasperMap.put("fromDate", frmDt);
					jasperMap.put("toDate", toDt);
					if (eForm.getReportDTO().getDrillDistID() == null || eForm.getReportDTO().getDrillDistID().equalsIgnoreCase(""))
						jasperMap.put("districtID", "0");
					else
						jasperMap.put("districtID", eForm.getReportDTO().getDrillDistID());
					jasperMap.put("deedID", eForm.getReportDTO().getDrillDeedID());
					jasperMap.put("tehsilID", eForm.getReportDTO().getDrillTehsilID());
					jasperMap.put("lang", language);
					jasperMap.put("distName", districtdrillName);
					jasperMap.put("deedName", deeddrillName);
					// Added by Ashutosh for Export SR, Office
					jasperMap.put("srOffice", eForm.getReportDTO().getOfficeRegId());
					jasperMap.put("srId", eForm.getReportDTO().getSrUserIdreg());

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

					String frmDt = eForm.getFromDate() + " 00:00:00"; // Added By Ankit : " 00:00:00"
					String toDt = eForm.getToDate() + " 23:59:59"; // Added By Ankit : " 23:59:59"

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
					// Added by Ashutosh for Export SR, Office
					jasperMap.put("srOffice", eForm.getReportDTO().getOfficeRegId());
					jasperMap.put("srId", eForm.getReportDTO().getSrUserIdreg());
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
					// Added by Ashutosh - Export Report
					eForm.getReportDTO().setDrillDeedID("");
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
					// Added - Slot Stastics Future Max Date Validation - RAhul 25-OCT-16 start
					boolean validSlotDate = reportBD.validSlotDate(eForm.getToDate());
					boolean validReportForSlot = reportBD.validateReportForSlot(eForm.getFromDate(), eForm.getToDate());
					String durationSQL = reportBD.slotDuration();
					// Added - Slot Stastics Future Max Date Validation - RAhul 25-OCT-16 End
					ArrayList reportList = new ArrayList<MISReportDTO>();
					/* if ((validReport == true) && (validSlotDate ==true)) { */
					if ((validReportForSlot == true) && (validSlotDate == true)) {
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
					if (validReportForSlot == false) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + durationSQL + " days or less than that");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी  " + durationSQL + " दिन या उस से कम की रखें।");
						forwardJsp = MarketTrendReportConstants.SLOT_STATISTICS_REPORT_SEARCH;
					}
					// Added - Slot Stastics Future Max Date Validation - RAhul 25-OCT-16 start
					if (validSlotDate == false) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + durationSQL + " days or less than that");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी " + durationSQL + " दिन या उस से कम की रखें।");
						forwardJsp = MarketTrendReportConstants.SLOT_STATISTICS_REPORT_SEARCH;
					}
					// Added - Slot Stastics Future Max Date Validation - RAhul 25-OCT-16 end
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
				// Added by Ashutosh
				if ("SUBMIT_TIME_REGISTRATION_REPORT_ACTION".equalsIgnoreCase(eForm.getActionName())) {
					reportBD = new MISReportBO();
					ArrayList reportList = new ArrayList<MISReportDTO>();
					if (validReport == true) {
						eForm.setUser("");
						if (officeType.equalsIgnoreCase("3")) {
							eForm.setUser(userId);
						} else {
							eForm.setUser("");
						}
						logger.debug("office id is : " + officeId);
						System.out.println("df " + officeType);

						System.out.println("user " + eForm.getUser());
						logger.debug("user id  is : " + userId);

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
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 30 days or less than that");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 30 दिन या उस से कम की रखें।");
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
				// Added by Ashutosh
				if ("PDF_DRILLDOWN_TIMEBASED_REPORT".equalsIgnoreCase(eForm.getActionName())) {
					logger.debug("PDF_DRILLDOWN_REGISTRATION_REPORT entered");
					JrxmlExportUtility export = new JrxmlExportUtility();
					eForm.setUser("");
					if (officeType.equalsIgnoreCase("3")) {
						eForm.setUser(userId);
					} else {
						eForm.setUser("");
					}
					logger.debug("office id is : " + officeId);
					System.out.println("df " + officeType);

					System.out.println("user " + eForm.getUser());
					logger.debug("user id  is : " + userId);

					PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

					String path = prop.getValue("pdf.location");
					String reportPath = path.concat(MarketTrendReportConstants.TIMEBASED_REPORT_JRXML);
					String reportName = eForm.getReportName();
					HashMap jasperMap = new HashMap();
					if (eForm.getReportDTO().getDeedID() == null)
						eForm.getReportDTO().setDeedID("");
					jasperMap.put("fromDate", eForm.getFromDate());
					jasperMap.put("toDate", eForm.getToDate());
					if (eForm.getReportDTO().getDistrictID() == null || eForm.getReportDTO().getDistrictID().equalsIgnoreCase(""))
						jasperMap.put("distID", 0);
					else
						jasperMap.put("distID", eForm.getReportDTO().getDistrictID());
					jasperMap.put("distName", districtName);
					jasperMap.put("srId", eForm.getUser());

					jasperMap.put("lang", language);
					jasperMap.put("path", path);
					// export.generatePDFDiffSize(jasperMap, reportPath,
					// "TimeBasedRegReport", response,612,1018);
					export.getJasperPdfviewerFromImgDiffSize(jasperMap, reportPath, "TimeBasedRegReport", response, path, "L", 612, 1018);
					logger.debug("PDF_DRILLDOWN_REGISTRATION_REPORT exit" + reportName);
					forwardJsp = MarketTrendReportConstants.TIME_REGISTRATION_REPORT;
					actionID = null;
				}

				// Added by Ashutosh
				if ("EXCEL_DRILLDOWN_TIMEBASED_REPORT".equalsIgnoreCase(eForm.getActionName())) {
					JrxmlExportUtility export = new JrxmlExportUtility();
					logger.debug("EXCEL_DRILLDOWN_REGISTRATION_REPORT entered");
					eForm.setUser("");
					if (officeType.equalsIgnoreCase("3")) {
						eForm.setUser(userId);
					} else {
						eForm.setUser("");
					}
					logger.debug("office id is : " + officeId);
					System.out.println("df " + officeType);

					System.out.println("user " + eForm.getUser());
					logger.debug("user id  is : " + userId);

					PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
					if (eForm.getReportDTO().getDeedID() == null)
						eForm.getReportDTO().setDeedID("");
					String path = prop.getValue("pdf.location");
					String reportPath = path.concat(MarketTrendReportConstants.TIMEBASED_REPORT_EXCEL_JRXML);
					String reportName = eForm.getReportName();
					HashMap jasperMap = new HashMap();
					jasperMap.put("fromDate", eForm.getFromDate());
					jasperMap.put("toDate", eForm.getToDate());
					if (eForm.getReportDTO().getDistrictID() == null || eForm.getReportDTO().getDistrictID().equalsIgnoreCase(""))
						jasperMap.put("distID", 0);
					else
						jasperMap.put("distID", eForm.getReportDTO().getDistrictID());
					jasperMap.put("distName", districtName);
					jasperMap.put("srId", eForm.getUser());

					jasperMap.put("lang", language);
					jasperMap.put("path", path);
					export.generateXLS(jasperMap, reportPath, "TimeBasedRegReport", response);
					eForm.setActionName("");
					logger.debug("EXCEL_DRILLDOWN_REGISTRATION_REPORT exit" + reportName);
					forwardJsp = MarketTrendReportConstants.TIMEBASED_REPORT_EXCEL_JRXML;
					actionID = null;
				}
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
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 30 days or less than that");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 30 दिन या उस से कम की रखें।");
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
				if (MarketTrendReportConstants.UST_PENALTY_REPORT_PHASE2_JRXML // added by Thilak
				.equals(param)) {
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
					forwardJsp = MarketTrendReportConstants.UST_PENALTY_REPORT_PHASE2_SEARCH;
					actionID = null;

				} // added by Thilak

				if (MarketTrendReportConstants.COMPARISON_REPORT_JRXML // added by Thilak
				.equals(param)) {
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
					eForm.setViewType("");
					eForm.getReportDTO().setDistrictID("");
					eForm.setActionName("");
					eForm.getReportDTO().setOfficeType(reportBD.getOfficeTypeID(officeId));
					forwardJsp = MarketTrendReportConstants.COMPARISON_REPORT_SEARCH;
					actionID = null;

				} // added by Thilak

				if ("dateOption".equalsIgnoreCase(eForm.getActionName())) {

					String select[] = new String[1];
					select[0] = "-1";
					eForm.setSelectedClauses(select);
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setActionName("");

					forwardJsp = "comparisonReport";
					actionID = null;
				}

				if ("monthOption".equalsIgnoreCase(eForm.getActionName())) {

					reportBD = new MISReportBO();
					ArrayList yearList = reportBD.getYears();
					eForm.setYearList(yearList);
					String select[] = new String[1];
					select[0] = "-1";
					eForm.setSelectedClauses(select);

					eForm.setMonth1("");
					eForm.setMonth2("");
					eForm.getReportDTO().setYear1("");
					eForm.getReportDTO().setYear2("");
					eForm.setActionName("");

					forwardJsp = "comparisonReport";
					actionID = null;
				}

				if ("financialYearOption".equalsIgnoreCase(eForm.getActionName())) {

					reportBD = new MISReportBO();
					ArrayList finYearList = reportBD.getFinYears();
					eForm.setFinYearList(finYearList);
					String select[] = new String[1];
					select[0] = "-1";
					eForm.setSelectedClauses(select);
					eForm.getReportDTO().setFinYear1("");
					eForm.getReportDTO().setFinYear2("");
					eForm.setActionName("");

					forwardJsp = "comparisonReport";
					actionID = null;
				}

				if ("EXCEL_COMPARISON_REPORT".equalsIgnoreCase(eForm.getActionName())) {
					JrxmlExportUtility export = new JrxmlExportUtility();
					logger.debug("EXCEL_COMPARISON_REPORT entered");
					PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

					String path = prop.getValue("pdf.location");
					String reportPath = path.concat(MarketTrendReportConstants.COMPARISON_REPORT_EXCEL_JRXML);
					String reportName = eForm.getReportName();
					HashMap jasperMap = new HashMap();
					logger.debug("district Name " + eForm.getReportDTO().getDistrictName());
					if (eForm.getReportDTO().getDistrictName().equalsIgnoreCase("-1"))

						jasperMap.put("distName", "");
					else
						jasperMap.put("distName", eForm.getReportDTO().getDistrictName());
					logger.debug("District Id " + eForm.getReportDTO().getDistrictID());
					if (eForm.getReportDTO().getDistrictID().equalsIgnoreCase("-1"))

						jasperMap.put("distID", "");
					else
						jasperMap.put("distID", eForm.getReportDTO().getDistrictID());
					jasperMap.put("date1", eForm.getFromDate());
					jasperMap.put("date2", eForm.getToDate());
					jasperMap.put("lang", language);
					// jasperMap.put("path", path);
					export.generateXLS(jasperMap, reportPath, "Comparison Report", response);
					eForm.setActionName("");
					logger.debug("EXCEL_COMPARISON_REPORT exit" + reportName);
					forwardJsp = MarketTrendReportConstants.COMPARISON_REPORT_SEARCH;
					actionID = null;
				}

				if ("MONTH_COMPARISON_REPORT".equalsIgnoreCase(eForm.getActionName())) {
					JrxmlExportUtility export = new JrxmlExportUtility();
					logger.debug("MONTH_COMPARISON_REPORT entered");
					PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

					String path = prop.getValue("pdf.location");
					String reportPath = path.concat(MarketTrendReportConstants.MONTH_COMPARISON_REPORT_JRXML);
					String reportName = eForm.getReportName();
					HashMap jasperMap = new HashMap();

					logger.debug("district Name " + eForm.getReportDTO().getDistrictName());
					if (eForm.getReportDTO().getDistrictName().equalsIgnoreCase("-1"))

						jasperMap.put("distName", "");
					else
						jasperMap.put("distName", eForm.getReportDTO().getDistrictName());
					logger.debug("District Id " + eForm.getReportDTO().getDistrictID());
					if (eForm.getReportDTO().getDistrictID().equalsIgnoreCase("-1"))

						jasperMap.put("distID", "");
					else
						jasperMap.put("distID", eForm.getReportDTO().getDistrictID());
					jasperMap.put("month1", eForm.getMonth1());
					jasperMap.put("month2", eForm.getMonth2());
					jasperMap.put("year1", eForm.getReportDTO().getYear1());
					jasperMap.put("year2", eForm.getReportDTO().getYear2());
					jasperMap.put("lang", language);
					// jasperMap.put("path", path);
					export.generateXLS(jasperMap, reportPath, "Comparison Report For Month", response);
					eForm.setActionName("");
					logger.debug("MONTH_COMPARISON_REPORT exit" + reportName);
					forwardJsp = MarketTrendReportConstants.COMPARISON_REPORT_SEARCH;
					actionID = null;
				}

				if ("YEAR_COMPARISON_REPORT".equalsIgnoreCase(eForm.getActionName())) {
					JrxmlExportUtility export = new JrxmlExportUtility();
					logger.debug("YEAR_COMPARISON_REPORT entered");
					PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");

					String path = prop.getValue("pdf.location");
					String reportPath = path.concat(MarketTrendReportConstants.YEAR_COMPARISON_REPORT_JRXML);
					String reportName = eForm.getReportName();
					HashMap jasperMap = new HashMap();

					logger.debug("district Name " + eForm.getReportDTO().getDistrictName());
					if (eForm.getReportDTO().getDistrictName().equalsIgnoreCase("-1"))

						jasperMap.put("distName", "");
					else
						jasperMap.put("distName", eForm.getReportDTO().getDistrictName());
					logger.debug("District Id " + eForm.getReportDTO().getDistrictID());
					if (eForm.getReportDTO().getDistrictID().equalsIgnoreCase("-1"))

						jasperMap.put("distID", "");
					else
						jasperMap.put("distID", eForm.getReportDTO().getDistrictID());

					String finYear1 = eForm.getReportDTO().getFinYear1();
					String finYear2 = eForm.getReportDTO().getFinYear2();
					String[] finyears1 = finYear1.split("-");
					String[] finyears2 = finYear2.split("-");
					jasperMap.put("year1", finyears1[0]);
					jasperMap.put("year2", finyears1[1]);
					jasperMap.put("year3", finyears2[0]);
					jasperMap.put("year4", finyears2[1]);
					jasperMap.put("date1", "01-04-" + finyears1[0]);
					jasperMap.put("date2", "31-03-" + finyears1[1]);
					jasperMap.put("date3", "01-04-" + finyears2[0]);
					jasperMap.put("date4", "31-03-" + finyears2[1]);
					jasperMap.put("lang", language);
					// jasperMap.put("path", path);
					export.generateXLS(jasperMap, reportPath, "Comparison Report For Year", response);
					eForm.setActionName("");
					logger.debug("YEAR_COMPARISON_REPORT exit" + reportName);
					forwardJsp = MarketTrendReportConstants.COMPARISON_REPORT_SEARCH;
					actionID = null;
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
					if (eForm.getJrxmlName().equalsIgnoreCase("SP_details_Credit_Limit_Estamp.jrxml") || eForm.getJrxmlName().equalsIgnoreCase("SP_details_Credit_Limit_OthServices.jrxml")) {
						jasperMap.put("fromDate1", eForm.getFromDate() + " 00:00:00");
						jasperMap.put("toDate1", eForm.getToDate() + " 23:59:59");

					}
					if (eForm.getJrxmlName().equalsIgnoreCase("spLicenseDetls.jrxml")) {
						jasperMap.put("fromDate", eForm.getFromDate() + " 00:00:00");
						jasperMap.put("toDate", eForm.getToDate() + " 23:59:59");

					}

					jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
					jasperMap.put("lang", language);
					jasperMap.put("path", path);
					jasperMap.put("distName", districtName);

					if (eForm.getJrxmlName().equalsIgnoreCase("spLicenseDetls.jrxml")) {
						boolean validReportSpLicen = reportBD.validateReportSplicence(eForm.getFromDate(), eForm.getToDate());

						String reportDays = reportDao.validateReportSpLicenseDay(eForm.getFromDate(), eForm.getToDate());
						if (validReportSpLicen == true) {
							export.getJasperPdfviewerFromImg(jasperMap, reportPath, reportName, response, path, "L");
						} else {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + reportDays + " days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी  " + reportDays + " दिन या उस से कम की रखें।");
						}
					} else {
						if (validReport == true) {
							// export.generatePDF(jasperMap, reportPath, reportName,
							// response);
							export.getJasperPdfviewerFromImg(jasperMap, reportPath, reportName, response, path, "L");
						} else {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 30 days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 30 दिन या उस से कम की रखें।");
						}
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
					jasperMap.put("distName", districtName);

					if (eForm.getJrxmlName().equalsIgnoreCase("spLicenseDetls.jrxml")) {// akansha

						reportPath = "";
						reportPath = path.concat(MarketTrendReportConstants.spLicenseDetlsExcel);
						jasperMap.put("fromDate", eForm.getFromDate() + " 00:00:00");
						jasperMap.put("toDate", eForm.getToDate() + " 23:59:59");

					}

					if (eForm.getJrxmlName().equalsIgnoreCase("SP_details_Credit_Limit_Estamp.jrxml")) {
						reportPath = "";
						reportPath = path.concat(MarketTrendReportConstants.SP_details_Credit_Limit_Estamp_Excel_JRXML);
						jasperMap.put("fromDate1", eForm.getFromDate() + " 00:00:00");
						jasperMap.put("toDate1", eForm.getToDate() + " 23:59:59");

					}

					if (eForm.getJrxmlName().equalsIgnoreCase("SP_details_Credit_Limit_OthServices.jrxml")) {
						reportPath = "";
						reportPath = path.concat(MarketTrendReportConstants.SP_details_Credit_Limit_OtherServices_Excel_JRXML);
						jasperMap.put("fromDate1", eForm.getFromDate() + " 00:00:00");
						jasperMap.put("toDate1", eForm.getToDate() + " 23:59:59");
					}

					if (eForm.getJrxmlName().equalsIgnoreCase("spLicenseDetls.jrxml")) {
						boolean validReportSpLicen = reportBD.validateReportSplicence(eForm.getFromDate(), eForm.getToDate());

						String reportDays = reportDao.validateReportSpLicenseDay(eForm.getFromDate(), eForm.getToDate());
						if (validReportSpLicen == true) {
							export.generateXLS(jasperMap, reportPath, reportName, response);
						} else {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + reportDays + " days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी  " + reportDays + " दिन या उस से कम की रखें।");
						}
					}

					else {
						if (validReport == true) {
							export.generateXLS(jasperMap, reportPath, reportName, response);
						} else {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 30 days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 30 दिन या उस से कम की रखें।");
						}
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
					jasperMap.put("distName", districtName);
					if (validReport == true) {
						export.generateHTML(jasperMap, reportPath, reportName, response);
					} else {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 30 days or less than that");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 30 दिन या उस से कम की रखें।");
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
					eForm.getReportDTO().setOfficeType(reportBD.getOfficeTypeID(officeId));
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
					jasperMap.put("distName", districtName);
					jasperMap.put("officeName", eForm.getReportDTO().getOfficeName());
					String attributeDays = "";
					if (reportName.equalsIgnoreCase("Document Delivery Report")) {
						// if(eForm.getReportDTO().getUserType().equals("2"))
						if ("2".equalsIgnoreCase(eForm.getReportDTO().getUserType()))
							jasperMap.put("userID", userId);
						else
							jasperMap.put("userID", "");
						validReport = reportBD.validateDocDeliveryReport(eForm.getFromDate(), eForm.getToDate());
					}

					if (eForm.getReportName().equals("SP License Summary Report") || eForm.getReportName().equals("Registration Details and Delivery Report") || eForm.getReportName().equals("Cases referred to DRs by SRs Report")) {
						// jasperMap.put("fromDate", eForm.getFromDate()+" 00:00:00");
						// jasperMap.put("toDate", eForm.getToDate()+" 23:59:59");

						if (eForm.getReportName().equals("SP License Summary Report")) {
							attributeDays = "ATT_196";// for sp summary report
						}

						else if (eForm.getReportName().equals("Registration Details and Delivery Report")) {

							attributeDays = "ATT_197";// Registration Details and Delivery Report

						}

						else if (eForm.getReportName().equals("Cases referred to DRs by SRs Report")) {
							attributeDays = "ATT_199";
						}

					}
					if (eForm.getReportName().equalsIgnoreCase("Summary of cases referred to DR by SRs Report")) {
						attributeDays = "ATT_202";
					}

					if (eForm.getReportName().equalsIgnoreCase("Hold or Incomplete Report")) {
						attributeDays = "ATT_200";
					}

					boolean validReportGeneric = reportBD.validateGenericReport(eForm.getFromDate(), eForm.getToDate(), attributeDays);// 					
					String reportDays = reportDao.validateGenericReportDays(eForm.getFromDate(), eForm.getToDate(), attributeDays);

					if (eForm.getReportName().equals("SP License Summary Report") || eForm.getReportName().equals("Registration Details and Delivery Report") || eForm.getReportName().equals("Cases referred to DRs by SRs Report") || eForm.getReportName().equalsIgnoreCase("Hold or Incomplete Report") || eForm.getReportName().equals("Summary of cases referred to DR by SRs Report")) {

						/*
						 * if (eForm.getReportName().equalsIgnoreCase( "SP License Summary Report")){
						 */

						/*
						 * boolean validReportSpSumaryRepory = reportBD.validateSpSumaryReport(eForm.getFromDate(), eForm.getToDate());// for sp summary report: parameter is ATT_196: made by rahul
						 * 
						 * String reportDays = reportDao.validateSpSumaryReportDay(eForm.getFromDate(), eForm.getToDate());
						 */

						if (validReportGeneric == true) {
							export.getJasperPdfviewerFromImg(jasperMap, reportPath, reportName, response, path, "L");
							logger.debug("EXPORT_COMMON_REPORT_TO_PDF exited" + reportName);
						}

						else {

							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + reportDays + " days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी  " + reportDays + " दिन या उस से कम की रखें।");

						}

					} else {
						if (validReport == true) {
							// export.generatePDF(jasperMap, reportPath, reportName,
							// response);
							export.getJasperPdfviewerFromImg(jasperMap, reportPath, reportName, response, path, "L");
							// export.genProtestRelPDF(jasperMap,reportPath,reportName,response);
							logger.debug("EXPORT_COMMON_REPORT_TO_PDF exited" + reportName);
						} else {
							if (reportName.equalsIgnoreCase("Document Delivery Report")) {
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 7 days or less than that");
								else
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 7 दिन या उस से कम की रखें।");
							} else {
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 30 days or less than that");
								else
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 30 दिन या उस से कम की रखें।");
							}
						}
					}
					if ("SP License Summary Report".equalsIgnoreCase(reportName)) {
						forwardJsp = MarketTrendReportConstants.SP_LIC_SUMM_SEARCH;
					} else {
						forwardJsp = MarketTrendReportConstants.COMMON_REPORT_SEARCH;
					}
					actionID = null;
				}
				if ("EXPORT_COMMON_REPORT_TO_XLS".equalsIgnoreCase(eForm.getActionName())) {
					logger.debug("EXPORT_COMMON_REPORT_TO_XLS entered");

					String attributeDays = "";
					JrxmlExportUtility export = new JrxmlExportUtility();
					String jrxmlName = eForm.getJrxmlName();
					HashMap jasperMap = new HashMap();
					PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
					jasperMap.put("fromDate", eForm.getFromDate());
					jasperMap.put("toDate", eForm.getToDate());

					if (eForm.getReportName().equals("Registration Details and Delivery Report")) {
						jrxmlName = MarketTrendReportConstants.REGISTRATION_REPORT_DEL_DET_EXCEL_JRXML;
						attributeDays = "ATT_197";// Registration Details and Delivery Report
						jasperMap.put("fromDate", eForm.getFromDate() + " 00:00:00");
						jasperMap.put("toDate", eForm.getToDate() + " 23:59:59");
					}

					else if (eForm.getReportName().equals("UST Global Report"))
						jrxmlName = MarketTrendReportConstants.UST_GLOBAL_REPORT_EXCEL_JRXML;
					else if (eForm.getReportName().equals("Additional e-stamps generated by SR Report"))
						jrxmlName = MarketTrendReportConstants.addEStampReportExcel;

					else if (eForm.getReportName().equals("SP License Summary Report")) {
						jrxmlName = MarketTrendReportConstants.SP_License_Summary_Report_EXCEL;
						attributeDays = "ATT_196";// for sp summary report
						jasperMap.put("fromDate", eForm.getFromDate() + " 00:00:00");
						jasperMap.put("toDate", eForm.getToDate() + " 23:59:59");
					}

					else if (eForm.getReportName().equals("Document Delivery Report")) {
						if (eForm.getReportDTO().getUserType().equals("2"))
							jasperMap.put("empID", userId);
						else
							jasperMap.put("empID", "");
						validReport = reportBD.validateDocDeliveryReport(eForm.getFromDate(), eForm.getToDate());
						jrxmlName = MarketTrendReportConstants.Document_Delivery_Report_EXCEL;
					} else if (eForm.getReportName().equals("Hold or Incomplete Report")) {
						jrxmlName = MarketTrendReportConstants.Hold_Or_Incomplete_Report_EXCEL;
						attributeDays = "ATT_200";
						// jasperMap.put("fromDate", eForm.getFromDate()+" 00:00:00");
						// jasperMap.put("toDate", eForm.getToDate()+" 23:59:59");
					}

					else if (eForm.getReportName().equals("Cases referred to DRs by SRs Report")) {

						attributeDays = "ATT_199";// Cases referred to DRs by SRs Report
						jasperMap.put("fromDate", eForm.getFromDate() + " 00:00:00");
						jasperMap.put("toDate", eForm.getToDate() + " 23:59:59");

					}

					else if (eForm.getReportName().equals("Summary of cases referred to DR by SRs Report")) {

						attributeDays = "ATT_202";// Cases referred to DRs by SRs Report
						jasperMap.put("fromDate", eForm.getFromDate() + " 00:00:00");
						jasperMap.put("toDate", eForm.getToDate() + " 23:59:59");

					}

					else if (eForm.getReportName().equalsIgnoreCase("Delay Report")) {

						attributeDays = "ATT_201";
						jasperMap.put("fromDate", eForm.getFromDate() + " 00:00:00");
						jasperMap.put("toDate", eForm.getToDate() + " 23:59:59");

					}

					String path = prop.getValue("pdf.location");
					String reportPath = path.concat(jrxmlName);
					String reportName = eForm.getReportName();
					// HashMap jasperMap = new HashMap();

					jasperMap.put("districtID", eForm.getReportDTO().getDistrictID());
					jasperMap.put("officeID", eForm.getReportDTO().getOfficeId());
					jasperMap.put("lang", language);
					jasperMap.put("path", path);
					jasperMap.put("distName", districtName);

					boolean validReportGeneric = reportBD.validateGenericReport(eForm.getFromDate(), eForm.getToDate(), attributeDays);// 					
					String reportDays = reportDao.validateGenericReportDays(eForm.getFromDate(), eForm.getToDate(), attributeDays);

					if (eForm.getReportName().equals("SP License Summary Report") || eForm.getReportName().equals("Registration Details and Delivery Report") || eForm.getReportName().equals("Cases referred to DRs by SRs Report") || eForm.getReportName().equals("Hold or Incomplete Report") || eForm.getReportName().equals("Delay Report") || eForm.getReportName().equals("Summary of cases referred to DR by SRs Report")) {

						if (validReportGeneric == true) {
							export.generateXLS(jasperMap, reportPath, reportName, response);
						} else {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + reportDays + " days or less than that");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी  " + reportDays + " दिन या उस से कम की रखें।");

						}

					}

					else {

						if (validReport == true) {
							export.generateXLS(jasperMap, reportPath, reportName, response);
						} else {
							if (reportName.equalsIgnoreCase("Document Delivery Report")) {
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 7 days or less than that");
								else
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 7 दिन या उस से कम की रखें।");
							} else {
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 30 days or less than that");
								else
									request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 30 दिन या उस से कम की रखें।");
							}
						}
					}
					eForm.setActionName("");
					// logger.debug("EXPORT_COMMON_REPORT_TO_XLS exited" + reportName);
					// forwardJsp = MarketTrendReportConstants.COMMON_REPORT_SEARCH;

					if ("SP License Summary Report".equalsIgnoreCase(reportName)) {
						forwardJsp = MarketTrendReportConstants.SP_LIC_SUMM_SEARCH;
					} else if ("UST Penalty Report".equalsIgnoreCase(reportName)) {
						forwardJsp = MarketTrendReportConstants.UST_PENALTY_REPORT_SEARCH;
					} else if ("UST Penalty Report-Phase2".equalsIgnoreCase(reportName)) {
						forwardJsp = MarketTrendReportConstants.UST_PENALTY_REPORT_PHASE2_SEARCH;
					} else if ("Delay Report".equalsIgnoreCase(reportName)) {
						forwardJsp = MarketTrendReportConstants.DELAY_REPORT_SEARCH;
					} else {
						forwardJsp = MarketTrendReportConstants.COMMON_REPORT_SEARCH;
					}

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
				if (MarketTrendReportConstants.INSPECTION_REPORT_LABEL.equals(param)) {

					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.getReportDTO().setDistrictID("");
					eForm.setActionName("");
					forwardJsp = MarketTrendReportConstants.INSPECTION_REPORT_SEARCH;
					actionID = null;
				}
				if ("SUBMIT_INSPECTION_REPORT_ACTION".equalsIgnoreCase(eForm.getActionName())) {
					reportBD = new MISReportBO();
					ArrayList reportList = new ArrayList<MISReportDTO>();
					if (validReport == true) {
						reportList = reportBD.getInspectionReport(eForm, language);
						eForm.getReportDTO().setReportList(reportList);
						if (reportList.size() == 0) {
							if (language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "No record found");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG, "कोई रिकॉर्ड नहीं मिला");
							forwardJsp = MarketTrendReportConstants.INSPECTION_REPORT_SEARCH;
						} else {
							forwardJsp = MarketTrendReportConstants.INSPECTION_REPORT;
						}
					}
					if (validReport == false) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 30 days or less than that");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 30 दिन या उस से कम की रखें।");
						forwardJsp = MarketTrendReportConstants.INSPECTION_REPORT_SEARCH;
					}
					actionID = null;
				}
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
					eForm.getReportDTO().setDeedID("");
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

					boolean validReportEstamp = reportBD.validateReportEstamp(eForm.getFromDate(), eForm.getToDate());// for estamp report: parameter is ATT_194: made by akansha
					String reportDays = reportDao.validateReportEstampDays(eForm.getFromDate(), eForm.getToDate());
					if (eForm.getReportDTO().getDeedID() == null) {
						eForm.setDeedID("");
					} else {
						eForm.setDeedID(eForm.getReportDTO().getDeedID());
					}
					if (validReportEstamp == true) {
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
					if (validReportEstamp == false) {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of " + reportDays + " days or less than that");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी  " + reportDays + " दिन या उस से कम की रखें।");
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
						// eForm.getReportDTO().setDeedID(deedID); // blocked by akansha
						eForm.setDeedID(deedID);
						eForm.getReportDTO().setDeedName(reportBD.getDeedName(deedID, language));

					}

					ArrayList reportList = reportBD.getEstampReportDrill(eForm, language, distID, deedID, userId);
					eForm.getReportDTO().setSubReportList(reportList);
					// /eForm.getReportDTO().setDeedID("");
					forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SCREEN2;
					actionID = null;
				}
				if (fwdPage != null && "drillDownCountEstamp1".equalsIgnoreCase(fwdPage)) {
					String distID = (String) request.getParameter("distName");
					String docNo = (String) request.getParameter("downloadDoc");
					// String deedID = eForm.getReportDTO().getDeedID();

					String deedID = (String) request.getParameter("deedName");
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
							if (null==metaDataInfo.getUniqueNo()) {
								throw new Exception();
							}
							if (metaDataInfo.getUniqueNo().equals("")) {
								throw new Exception();
							}
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
								session.setAttribute("checkStatus", "DMSError");
								return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
							}
							if (metaDataInfo.getUniqueNo().equals("")) {
								session.setAttribute("checkStatus", "DMSError");
								return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
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
					// eForm.getReportDTO().setDeedID("");
					// eForm.getReportDTO().setDeedName("ALL")

					/*
					 * if(language.equalsIgnoreCase("en") ){ if( deedName.equalsIgnoreCase("ALL"))
					 * 
					 * eForm.getReportDTO().setDeedName("ALL");
					 * 
					 * 
					 * } else{ if( deedName.equalsIgnoreCase("सब")) eForm.getReportDTO().setDeedName("सब"); }
					 */

					if (eForm.getReportDTO().getDeedID().isEmpty()) {
						eForm.setDeedID("");
						if (language.equalsIgnoreCase("en")) {
							eForm.getReportDTO().setDeedName("ALL");
						} else {
							eForm.getReportDTO().setDeedName("सब");
						}
					}
					forwardJsp = MarketTrendReportConstants.ESTAMP_REPORT_SCREEN1;
					actionID = null;
				}
				if ("BACK_ESTAMP_REPORT_2".equalsIgnoreCase(eForm.getActionName())) {

					if (eForm.getDeedName().isEmpty()) {

						if (language.equalsIgnoreCase("en")) {
							eForm.getReportDTO().setDeedName("ALL");
						} else {
							eForm.getReportDTO().setDeedName("सब");
						}
					}

					else {
						eForm.getReportDTO().setDeedName(reportBD.getDeedName(eForm.getDeedName(), language));
					}
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
					jasperMap.put("FROM", eForm.getFromDate() + " 00:00:00");// add 00:00:00 by rustam
					jasperMap.put("TO", eForm.getToDate() + " 23:59:59"); // add 23:59:59 by rustam
					jasperMap.put("DISTRICT_ID", eForm.getReportDTO().getDistrictID());
					jasperMap.put("LICENCE", eForm.getReportDTO().getUserID());
					if (eForm.getDeedID().equalsIgnoreCase(""))
						jasperMap.put("DEED_ID", null);
					else
						jasperMap.put("DEED_ID", eForm.getDeedID());
					jasperMap.put("lang", language);
					jasperMap.put("path", path);
					jasperMap.put("distName", districtName);
					jasperMap.put("CREATED_BY", eForm.getReportDTO().getUserID());
					jasperMap.put("LICENCE", eForm.getReportDTO().getUserID());
					if (validReport == true) {
						export.generateXLS(jasperMap, reportPath, "Estamp_Report", response);
					} else {
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "Please select duration of 30 days or less than that");
						else
							request.setAttribute(DeedDraftConstant.FAILURE_MSG, "सर्च की अवधी 30 दिन या उस से कम की रखें।");
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

				if (MarketTrendReportConstants.GET_OFFICE_COMMON_SR_REPORT.equals(eForm.getActionName())) {

					String reportName = eForm.getReportName();

					request.setAttribute("fnName", reportName);
					eForm.getReportDTO().setUserType("2");
					ArrayList officeList = reportBD.getOfficeNameDetailsMISDistSr(officeId, language);
					eForm.setOfficeNameList(officeList);
					eForm.setActionName("");
					forwardJsp = MarketTrendReportConstants.COMMON_REPORT_SEARCH;
					actionID = null;
				}

				if ("GET_TEHSIL".equalsIgnoreCase(eForm.getActionName())) {
					eForm.getReportDTO().setTehsilListreg(new ArrayList());
					eForm.getReportDTO().setOffcListreg(new ArrayList());
					eForm.getReportDTO().setSrListreg(new ArrayList());
					String districtID = eForm.getReportDTO().getDistrictID();
					ArrayList tehsilList1 = new ArrayList();
					tehsilList1 = reportBD.gettehsil(districtID, language);
					if (tehsilList1.size() > 0) {
						dto.setTehsilListreg(tehsilList1);
					}
					forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT_SEARCH;

				}
				if ("GET_OFFICES".equalsIgnoreCase(eForm.getActionName())) {
					eForm.getReportDTO().setOffcListreg(new ArrayList());
					eForm.getReportDTO().setSrListreg(new ArrayList());
					String tehsilId = eForm.getReportDTO().getTehsilIdreg();
					eForm.getReportDTO().setOfficeRegId("");
					ArrayList officeList1 = new ArrayList();
					// officeList1 = reportBD.getoffices(tehsilId,language);
					officeList1 = reportBD.getOffice(tehsilId, language, officeId);

					if (officeList1.size() > 0) {
						dto.setOffcListreg(officeList1);
					}
					forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT_SEARCH;

				}
				if ("GET_SR_NAMES".equalsIgnoreCase(eForm.getActionName())) {
					eForm.getReportDTO().setSrListreg(new ArrayList());
					String officesId = eForm.getReportDTO().getOfficeRegId();
					eForm.getReportDTO().setSrUserIdreg("");
					ArrayList SRList1 = new ArrayList();
					SRList1 = reportBD.getSRS(officesId, language, officeId, userId);
					if (SRList1.size() > 0) {
						dto.setSrListreg(SRList1);
					}
					forwardJsp = MarketTrendReportConstants.REGISTRATION_REPORT_SEARCH;

				}

				if ("GET_TEHSIL_LIST".equalsIgnoreCase(eForm.getActionName())) {
					eForm.getReportDTO().setTehsilListreg(new ArrayList());
					eForm.getReportDTO().setOffcListreg(new ArrayList());
					eForm.getReportDTO().setSrListreg(new ArrayList());
					String districtID = eForm.getReportDTO().getDistrictID();
					ArrayList tehsilList1 = new ArrayList();
					tehsilList1 = reportBD.gettehsil(districtID, language);
					if (tehsilList1.size() > 0) {
						dto.setTehsilListreg(tehsilList1);

					}
					forwardJsp = MarketTrendReportConstants.REGISTRATION_SUMMARY_REPORT_SEARCH;

				}
				if ("GET_OFFICES_LIST".equalsIgnoreCase(eForm.getActionName())) {
					eForm.getReportDTO().setOffcListreg(new ArrayList());
					eForm.getReportDTO().setSrListreg(new ArrayList());
					String tehsilId = eForm.getReportDTO().getTehsilIdreg();
					eForm.getReportDTO().setOfficeRegId("");
					ArrayList officeList1 = new ArrayList();
					officeList1 = reportBD.getOffice(tehsilId, language, officeId);
					if (officeList1.size() > 0) {
						dto.setOffcListreg(officeList1);
					}
					forwardJsp = MarketTrendReportConstants.REGISTRATION_SUMMARY_REPORT_SEARCH;

				}
				if ("GET_SR_NAMES_LIST".equalsIgnoreCase(eForm.getActionName())) {
					eForm.getReportDTO().setSrListreg(new ArrayList());
					String officesId = eForm.getReportDTO().getOfficeRegId();
					ArrayList SRList1 = new ArrayList();
					eForm.getReportDTO().setSrUserIdreg("");
					SRList1 = reportBD.getSRS(officesId, language, officeId, userId);
					if (SRList1.size() > 0) {
						dto.setSrListreg(SRList1);
					}
					forwardJsp = MarketTrendReportConstants.REGISTRATION_SUMMARY_REPORT_SEARCH;

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
		} catch (Exception e) {
			if ("PDFVIEWEXCEPTION".equalsIgnoreCase(e.getMessage())) {
				return new ActionForward("/jsp/reports/FailureReport.jsp");
			} else {
				throw e;

			}

		}
		return mapping.findForward(forwardJsp);
	}

	private String getTehsilName(String tehsilId, ArrayList tehsilList, String language) {
		if (tehsilList != null && !tehsilList.isEmpty() && tehsilId != null) {
			for (Object dto : tehsilList) {
				MISReportDTO misReport = (MISReportDTO) dto;
				if (StringUtils.equals(misReport.getTehsilIdreg(), tehsilId)) {
					return misReport.getTehsilreg();
				}
			}
		}

		return language.equalsIgnoreCase("en") ? " ALL " : " सब ";
	}

	private String getSrOfcName(String srOfcId, ArrayList srOfcList, String language) {
		if (srOfcList != null && !srOfcList.isEmpty() && srOfcId != null) {
			for (Object dto : srOfcList) {
				MISReportDTO misReport = (MISReportDTO) dto;
				if (StringUtils.equals(misReport.getOfficeRegId(), srOfcId)) {
					return misReport.getOfficeReg();
				}
			}
		}
		return language.equalsIgnoreCase("en") ? " ALL " : " सब ";
	}
}
