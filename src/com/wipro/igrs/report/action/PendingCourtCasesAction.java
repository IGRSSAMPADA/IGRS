/*package com.wipro.igrs.report.action;


import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.CitizenFeedback.bd.CitizenFeebackViewBD;
import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.report.action.reportPdf.Report;
import com.wipro.igrs.report.bo.MISReportBO;
import com.wipro.igrs.report.bo.PendingCourtCaseReportBO;
import com.wipro.igrs.report.dto.MISReportDTO;
import com.wipro.igrs.report.form.MISReportForm;
import com.wipro.igrs.revenueManagement.bd.RevMgtBD;
import com.wipro.igrs.revenueManagement.bo.RevMgtBO;
import com.wipro.igrs.revenueManagement.dto.RevMgtDTO;
import com.wipro.igrs.revenueManagement.dto.RevenueMgtDTO;
import com.wipro.igrs.revenueManagement.form.RevMgtForm;

public class PendingCourtCasesAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(PendingCourtCasesAction.class);
	private String forwardPage = "";
	private String reqParam = "";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		try {
			if (form != null) {

				MISReportForm eForm = (MISReportForm)form;
				
				PendingCourtCaseReportBO pendingBO = new PendingCourtCaseReportBO();
				Report reportPDF = new Report();
				String actionID = eForm.getActionName();
				MISReportDTO dto = eForm.getReportDTO();
				logger.debug("actionID value========"+actionID);
				 
				
				Report rpt = new Report();
				
				String param = request.getParameter("param");
				String label = request.getParameter("label");
				
			
			
			  

				


				
				// Transaction Mapping
				
				if (formName != null
						&& formName.equalsIgnoreCase("PENDING_COURT_CASES_SUBMIT_ACTION")) {

					// for Primary And Secondary Radio Buttons
					if (eForm.getActionName().equalsIgnoreCase("SetDataCheckbox")) {
						logger.debug("the value of radio button is......");
						String radioValue = dto.getRadioSelect();
						logger.debug("the value of radio button is......"+ radioValue);
						// dto.setRadioSelect2(radioValue);
						dto.setRadioSelectSecond(radioValue);
						dto.setPaymentType("");
						eForm.setRmDto(dto);
						//formName = "";
						//eForm.setActionName("");
						forwardPage = "FirstJsp";

					}

					// reset

					if (eForm.getActionName().equalsIgnoreCase("Reset")) {

						//request.removeAttribute("viewResultList");
						// session.removeAttribute("combSt");
						//dto.setDistrictName("");
						dto.setDurationFrom("");
						dto.setDurationTo("");
						dto.setHdnTxnId("");
						dto.setOfficeName("");
						dto.setPaymentType("");
						dto.setRadioSelect("");

						dto.setRadioSelectSecond("");
						dto.setReceiptId("");
						dto.setReceiptID("");
						dto.setTxnAmt(0.0);
						dto.setTxnDate("");
						dto.setTxnId("");
						dto.setTxnPurpose("");
						dto.setTxnType("");
						dto.setUserId("");
						dto.setIsViewEmpty(0);
						// dto.setViewComb("");
						//dto.setViewResultList(new ArrayList());

						forwardPage = "FirstJsp";
					}

					// start of action viewSearch
					if ("SEARCH".equalsIgnoreCase(eForm.getActionName())) {

						String paymentType = "";
						String fromDate = "";
						String toDate = "";
						String txnId = "";
						paymentType = dto.getPaymentType();
						txnId = dto.getTxnId();
						fromDate = eForm.getFromDate();
						toDate = eForm.getToDate();

						dto.setIsViewEmpty(0);
						dto.setViewResultList(new ArrayList());
						ArrayList viewResultList = new ArrayList();
					
						if (paymentType.equalsIgnoreCase("4")) {

							// viewResultList=
							// revBD.getViewDetls(paymentType,fromDate,toDate,txnId);
							viewResultList = revBD.getFilteredresults1(paymentType, fromDate, toDate,txnId);

						
							try {
								if (viewResultList.size() > 0) {

								
									dto.setViewResultList(viewResultList);
									request.setAttribute(
											"viewResultList",
											viewResultList);
									//formName = "";
									//eForm.setActionName("");
									forwardPage = "FirstJsp";

								} else {
									dto.setIsViewEmpty(1);
									//formName = "";
									//eForm.setActionName("");
									forwardPage = "FirstJsp";
								}

							} catch (Exception e) {
								logger.debug("inside popup page1, reg detl is empty");
								dto.setIsViewEmpty(1);
								//formName = "";
								//eForm.setActionName("");
								forwardPage = "FirstJsp";
							}
							//formName = "";
							//eForm.setActionName("");
						}

						else if (paymentType.equals("1")
								|| paymentType.equals("2")
								|| paymentType.equals("3")) {
							try {
								viewResultList = revBD
										.getFilteredresults(
												paymentType, fromDate,
												toDate, txnId);
								if (viewResultList.size() > 0) {

									dto.setViewResultList(viewResultList);
									request.setAttribute(
											"viewResultList",
											viewResultList);
									//formName = "";
									//eForm.setActionName("");
									forwardPage = "FirstJsp";

								} else {
									dto.setIsViewEmpty(1);
									//formName = "";
									//eForm.setActionName("");
									forwardPage = "FirstJsp";
								}

							} catch (Exception e) {
								logger.debug("inside popup page1, reg detl is empty");
								dto.setIsViewEmpty(1);
								//formName = "";
								//eForm.setActionName("");
								forwardPage = "FirstJsp";
							}

							//formName = "";
							//eForm.setActionName("");
						}

					}
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				// Form For Receipt/Challan Search
				if (formName != null
						&& formName.equalsIgnoreCase("ReceiptSearch")) {

					// For Reset

					if (eForm.getActionName().equalsIgnoreCase("Reset")) {

						dto.setRadioSelectReceipt("");
						dto.setRadioSelectReceipt2("");
						dto.setIsViewEmpty(0);
						request.removeAttribute("viewResultList");
						// dto.setViewResultList(new ArrayList());
						dto.setReceiptTextBox("");

					}

					// For Cash/Online/Challan Radio Button
					if (eForm.getActionName().equalsIgnoreCase("SetDataRadioButton")) {

						String radioValue = dto.getRadioSelectReceipt();
						
						// dto.setRadioSelect2(radioValue);

						dto.setRadioSelectReceipt2(radioValue);
						dto.setReceiptTextBox("");

						eForm.setRmDto(dto);
						formName = "";
						radioValue = "";

						eForm.setActionName("");
						forwardPage = "ThirdJsp";

					}

					// Start Of Form View Search

					if (eForm.getActionName().equalsIgnoreCase("RECEIPTSEARCHACTION")) {

						String radioSelectReceipt = "";
						String receiptTextBox = "";

						radioSelectReceipt = dto.getRadioSelectReceipt();
						receiptTextBox = dto.getReceiptTextBox();
						dto.setIsViewEmpty(0);
						dto.setViewResult(new ArrayList());
						ArrayList viewResult = new ArrayList();
						if (radioSelectReceipt.equalsIgnoreCase("1")) // In Case of cash receipt search
					     {

							viewResult = revBD.getFilteredresults2(receiptTextBox, dto);

							
							if (viewResult.size() != 0) {
								dto.setViewResult(viewResult);
								request.setAttribute("viewResult",viewResult);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FourthJsp";

							} else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "ThirdJsp";
							}

							// eForm.setActionName("");
						}

						else if (radioSelectReceipt.equalsIgnoreCase("2")) // In Case Of Challan Receipt Search
														
						{

							viewResult = revBD.getFilteredresults3(receiptTextBox, radioSelectReceipt,dto);
							
							if (viewResult.size() != 0) {
								dto.setViewResult(viewResult);
								request.setAttribute("viewResult",viewResult);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FourthJsp";

							} 
							else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "ThirdJsp";
							}

							// formName="";
							// eForm.setActionName("");
						}

						else if (radioSelectReceipt
								.equalsIgnoreCase("3")) // In Case Of Online Receipt Search
														
						{

							viewResult = revBD.getFilteredresults4(receiptTextBox, radioSelectReceipt,dto);
							
							if (viewResult.size() != 0) {
								dto.setViewResult(viewResult);
								request.setAttribute("viewResult",viewResult);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FourthJsp";

							}

							else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "ThirdJsp";
							}

							// formName="";
							// eForm.setActionName("");
						}

					}

				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				// start of Revenue Collection Search form
				if (formName != null
						&& formName.equalsIgnoreCase("revReconcSearchPage")) {

					// For Reset

					if (eForm.getActionName().equalsIgnoreCase(
							"ResetRevenueCollection")) {

						dto.setRadioMode("");
						dto.setName("");
						dto.setValue("");
						dto.setIsViewEmpty(0);
						request.removeAttribute("viewCashRevenue");
						dto.setViewCashRevenue(new ArrayList());

						// dto.setOfficeIdList(new ArrayList());
						// dto.setOfficeNameList(new ArrayList());
						dto.setRevenueOfficeName("");
						dto.setRevenueDistrictId("");
						dto.setRevenueDistrictName("");
						dto.setRevenueOfficeId("");
						dto.setRevenueOfficeTypeName("");
						dto.setRevenueToDate("");
						dto.setRevenueFromDate("");
						dto.setCashTransactionId("");
						dto.setCashCreatedDate("");
						dto.setCashFunctionId("");
						dto.setCashFunctionName("");
						dto.setCashGrossAmt("");
						dto.setCashUserCreatedBy("");
						dto.setCashPaymentType("");

					}

					if (eForm.getActionName()
							.equalsIgnoreCase("OfficeTypeLoad")) {
						String disId = dto.getRevenueDistrictId();
						dto.setOfficeIdList(bo.getOfficeTypeList(disId));
						formName = "";
						// actionName="";
						forwardPage = "FifthJsp";
					}

					if (eForm.getActionName()
							.equalsIgnoreCase("OfficeNameLoad")) {
						String disId = dto.getRevenueDistrictId();
						String offtyp = dto.getRevenueOfficeId();
						dto.setOfficeNameList(bo.getOfficeNameList(disId,
								offtyp));
						formName = "";
						// actionName="";
						forwardPage = "FifthJsp";
					}

					if (eForm.getActionName().equalsIgnoreCase(
							"REVENUECOLLECTIONSEARCH")) {
						String radioMode = "";
						String revenueDistrictId = "";
						String revenueOfficeTypeName = "";
						String revenueToDate = "";
						String revenueFromDate = "";

						radioMode = dto.getRadioMode();
						revenueDistrictId = dto.getRevenueDistrictId();
						revenueOfficeTypeName = dto.getRevenueOfficeTypeName();
						
						revenueToDate = eForm.getRevenueToDate();
						revenueFromDate = eForm.getRevenueFromDate();
						dto.setIsViewEmpty(0);
						dto.setViewResult(new ArrayList());
						ArrayList viewCashRevenue = new ArrayList();

						if (radioMode.equalsIgnoreCase("1")) // In Case Of Cash
																// Revenue
																// Collection
						{
							
							viewCashRevenue = revBD.getRevenueCollectionCash(
									revenueDistrictId, revenueOfficeTypeName,
									revenueFromDate, revenueToDate, dto);
							
							if (viewCashRevenue.size() != 0) {
								dto.setViewCashRevenue(viewCashRevenue);
								request.setAttribute("viewCashRevenue",
										viewCashRevenue);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "SixthJsp";

							} else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FifthJsp";
							}

						}

						if (radioMode.equalsIgnoreCase("2")) // In Case Of
																// Challan
																// Revenue
																// Collection
						{
							
							viewCashRevenue = revBD
									.getRevenueCollectionChallan(radioMode,
											revenueDistrictId,
											revenueOfficeTypeName,
											revenueFromDate, revenueToDate, dto);
						
							if (viewCashRevenue.size() != 0) {
								dto.setViewCashRevenue(viewCashRevenue);
								request.setAttribute("viewCashRevenue",
										viewCashRevenue);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "SixthJsp";

							} else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FifthJsp";
							}

						}

						if (radioMode.equalsIgnoreCase("3")) // In Case Of
																// Online
																// Revenue
																// Collection
						{
							
							viewCashRevenue = revBD.getRevenueCollectionOnline(
									radioMode, revenueDistrictId,
									revenueOfficeTypeName, revenueFromDate,
									revenueToDate, dto);
							
							if (viewCashRevenue.size() != 0) {
								dto.setViewCashRevenue(viewCashRevenue);
								request.setAttribute("viewCashRevenue",
										viewCashRevenue);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "SixthJsp";

							} else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FifthJsp";
							}

						}

				

				
						// end of action viewSearch

					}
				}
		}
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("Returnign forwardPage : " + forwardPage);
		return mapping.findForward(forwardPage);
	}
}*/