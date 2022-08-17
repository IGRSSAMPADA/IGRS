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
 * File Name	   		: ReportAction.java
 *
 * Description	   		: Struts Action to view Revenue Management Details
 *
 * Version Number  		: 0.0 
 *
 * Created Date	   		: 29 04 2008   
 *
 * Modification History	: Created
 */

package com.wipro.igrs.report.action;


import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.report.bd.ReportingBD;
import com.wipro.igrs.report.common.ReportConstants;
import com.wipro.igrs.report.dto.ReportingDTO;
import com.wipro.igrs.report.form.ReportingForm;

public class ReportingAction extends BaseAction{
	
	private Logger logger = 
		(Logger) Logger.getLogger(ReportingAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,HttpSession session) throws Exception {
		
		
		ReportingForm reportForm = (ReportingForm)form;
		ReportingBD reportBD = new ReportingBD();
	//	HttpSession session = request.getSession();
		
		ArrayList reportList = new ArrayList();
		String returnPage = "";
		String language=(String)session.getAttribute("languageLocale");
		String label = request.getParameter("label");
		String labl="";
		logger.debug("request label value==="+request.getParameter("label"));
		if(request.getParameter("label")!=null){
			session.setAttribute("LABEL", request.getParameter("label"));
			logger.debug("session label value====="+session.getAttribute("LABEL"));
			 labl = (String)session.getAttribute("LABEL");
		}
		
		
		if (reportForm.getActionForm()==null){
		 if(labl.equalsIgnoreCase("6")){ 
			// ReportingDTO dto=new ReportingDTO();
			 /*ArrayList sroList = reportBD.getSroNameDetails(reportForm);
			 reportForm.setSroList(sroList);
			 ArrayList droList=reportBD.getDroNameDetails(reportForm);
			 reportForm.setDroList(droList);
			 dto.setSroIDList(sroList);
			 dto.setDroIDList(droList);
			 reportForm.setReportDTO(dto);
			 session.setAttribute("reportForm",reportForm);*/
			 reportForm.getReportDTO().setDistrictID("");
			 reportForm.getReportDTO().setYear("");
			 reportForm.getReportDTO().setMonthTarget("");
			 reportForm.setActionID("");
			 reportForm.setDistrictList(reportBD.getDistrictDetails(language));
			 reportForm.setFiscalYearList(reportBD.getYearForJudicalStamp(reportBD.getYearCheck()));
		//	 reportForm.setReportDTO(dto);
			 session.setAttribute("reportForm",reportForm);
			 returnPage = "revenueScreen";        
	         logger.debug("returnpage^^^^^^^^^^"+returnPage);
		 }
		 else  if(labl.equalsIgnoreCase("7")){ 
			// ReportingDTO dto=new ReportingDTO();
			 //ArrayList sroList = reportBD.getSroNameDetails(reportForm);
			 //reportForm.setSroList(sroList);
			 //ArrayList droList=reportBD.getDroNameDetails(reportForm);
			// reportForm.setDroList(droList);
			 
			// dto.setSroIDList(sroList);
			// dto.setDroIDList(droList);
			 //igrsC.getDistrict("1", language);
			 reportForm.getReportDTO().setDistrictID("");

			 reportForm.getReportDTO().setYear("");
			 reportForm.getReportDTO().setMonthTarget("");
			 reportForm.setDistrictList(reportBD.getDistrictDetails(language));
			 reportForm.setFiscalYearList(reportBD.getYearForJudicalStamp(reportBD.getYearCheck()));
		//	 reportForm.setReportDTO(dto);
			 session.setAttribute("reportForm",reportForm);
			 returnPage = "ComprevenueScreen";        
	         logger.debug("returnpage^^^^^^^^^^"+returnPage);
		 }
		 else if(labl.equalsIgnoreCase("8")){
			 ReportingDTO dto=new ReportingDTO();
			 ArrayList sroList = reportBD.getSroNameDetails(reportForm);
			 reportForm.setSroList(sroList);
			 ArrayList droList=reportBD.getDroNameDetails(reportForm);
			 reportForm.setDroList(droList);
			 ArrayList distList=reportBD.getdistictDetails(reportForm);
			 dto.setDistrictIDList(distList);
			 dto.setSroIDList(sroList);
			 dto.setDroIDList(droList);
			 reportForm.setReportDTO(dto);
			 session.setAttribute("reportForm",reportForm);
			 returnPage = "rrcScreen";        
	         logger.debug("returnpage^^^^^^^^^^"+returnPage);
		 }
		 else  if(labl.equalsIgnoreCase("9")){ 
			 reportForm.setRadioSearch("");
			 reportForm.setFromDate("");
			 reportForm.setToDate("");
			 ReportingDTO dto=new ReportingDTO();
			 session.setAttribute("reportForm",reportForm);
			 returnPage = "CesswiseScreen";        
	         logger.debug("returnpage^^^^^^^^^^"+returnPage);
		 }
		 else  if(labl.equalsIgnoreCase("10")){ 
			 reportForm.getReportDTO().setYear("");
			 reportForm.getReportDTO().setMonthTarget("");
			 reportForm.setActionID("");
			 reportForm.setFiscalYearList(reportBD.getYearForJudicalStamp(reportBD.getYearCheck()));

			 //ReportingDTO dto=new ReportingDTO();
			 session.setAttribute("reportForm",reportForm);
			 returnPage = "subjectWiseStmpRegFee";        
	         logger.debug("returnpage^^^^^^^^^^"+returnPage);
		 }
			 
		} 
		
		 if ("RevenueReceipt".equalsIgnoreCase(reportForm.getActionForm()))
			{
				String actionValue =reportForm.getActionID();
				if(actionValue!=null){
					//if(labl.equalsIgnoreCase("6")){
						
						/*if(actionValue.equalsIgnoreCase("submit")){
							reportList = reportBD.getRevenueReceiptDetails(reportForm);
							reportForm.setReportList(reportList);
							session.setAttribute("ReportLst", reportForm);
							logger.debug("after success");				
							logger.debug("REPORTLIST="+reportList);
							returnPage ="revenuesuccess";
							//returnPage = ReportConstants.noDataFound;
							logger.debug("return page@@@@@@@@@@2"+returnPage);
						}
*/
						//Added by Shreeraj
						if(actionValue.equalsIgnoreCase("submitReport")){
							ArrayList regDocReport=reportBD.getRevenueReceiptReport(reportForm.getReportDTO());
							ArrayList totalregDocReport=reportBD.getTotalRevenueReceiptReport(reportForm.getReportDTO());
							ArrayList list = (ArrayList) totalregDocReport.get(0);
							reportForm.getReportDTO().setTotNoDocMonth((String) list.get(0));
							reportForm.getReportDTO().setTotRevRcptMonth((String) list.get(1));
							reportForm.getReportDTO().setTotNoDocPeriod((String) list.get(2));
							reportForm.getReportDTO().setTotRevRcptPeriod((String) list.get(3));
							reportForm.getReportDTO().setReportList(regDocReport);
							 if(reportForm.getReportDTO().getMonthTarget().equals("01"))
								 reportForm.setMonth("January");
							 if(reportForm.getReportDTO().getMonthTarget().equals("02"))
								 reportForm.setMonth("February");
							 if(reportForm.getReportDTO().getMonthTarget().equals("03"))
								 reportForm.setMonth("March");
							 if(reportForm.getReportDTO().getMonthTarget().equals("04"))
								 reportForm.setMonth("April");
							 if(reportForm.getReportDTO().getMonthTarget().equals("05"))
								 reportForm.setMonth("May");
							 if(reportForm.getReportDTO().getMonthTarget().equals("06"))
								 reportForm.setMonth("June");
							 if(reportForm.getReportDTO().getMonthTarget().equals("07"))
								 reportForm.setMonth("July");
							 if(reportForm.getReportDTO().getMonthTarget().equals("08"))
								 reportForm.setMonth("August");
							 if(reportForm.getReportDTO().getMonthTarget().equals("09"))
								 reportForm.setMonth("September");
							 if(reportForm.getReportDTO().getMonthTarget().equals("10"))
								 reportForm.setMonth("October");
							 if(reportForm.getReportDTO().getMonthTarget().equals("11"))
								 reportForm.setMonth("November");
							 if(reportForm.getReportDTO().getMonthTarget().equals("12"))
								 reportForm.setMonth("December");
							returnPage ="revenuesuccess";
						}
						else if(actionValue.equalsIgnoreCase("backReport")){
							       
							 reportForm.getReportDTO().setDistrictID("");
							 reportForm.getReportDTO().setYear("");
							 reportForm.getReportDTO().setMonthTarget("");
							 returnPage = "revenueScreen"; 
						}
						else if(actionValue.equalsIgnoreCase("resetReport")){
						       
							 reportForm.getReportDTO().setDistrictID("");
							 reportForm.getReportDTO().setYear("");
							 reportForm.getReportDTO().setMonthTarget("");
							 returnPage = "revenueScreen"; 
						}
					    else if(actionValue.equalsIgnoreCase("cancel")){
							//returnPage = "mainpage";
					    	returnPage = ReportConstants.noDataFound;
							logger.debug("after cancel");
						}
					    else if(actionValue.equalsIgnoreCase("submitSubjectWiseReport")){
							//returnPage = "mainpage";
					    	ArrayList regDocReport=reportBD.getSubjectWiseReport(reportForm.getReportDTO());
							ArrayList totalregDocReport=reportBD.getTotalSubjectWiseReport(reportForm.getReportDTO());
							ArrayList list = (ArrayList) totalregDocReport.get(0);
							reportForm.getReportDTO().setTotNoDocMonth((String) list.get(0));
							reportForm.getReportDTO().setTotExeStampMonth((String) list.get(1));
							reportForm.getReportDTO().setTotExeRegFeeMonth((String) list.get(2));
							reportForm.getReportDTO().setTotTotalMonth((String) list.get(3));
							reportForm.getReportDTO().setTotNoDocPeriod((String) list.get(4));
							reportForm.getReportDTO().setTotExeStampPeriod((String) list.get(5));
							reportForm.getReportDTO().setTotExeRegFeePeriod((String) list.get(6));
							reportForm.getReportDTO().setTotTotalPeriod((String) list.get(7));
							reportForm.getReportDTO().setReportList(regDocReport);
							 if(reportForm.getReportDTO().getMonthTarget().equals("01"))
								 reportForm.setMonth("January");
							 if(reportForm.getReportDTO().getMonthTarget().equals("02"))
								 reportForm.setMonth("February");
							 if(reportForm.getReportDTO().getMonthTarget().equals("03"))
								 reportForm.setMonth("March");
							 if(reportForm.getReportDTO().getMonthTarget().equals("04"))
								 reportForm.setMonth("April");
							 if(reportForm.getReportDTO().getMonthTarget().equals("05"))
								 reportForm.setMonth("May");
							 if(reportForm.getReportDTO().getMonthTarget().equals("06"))
								 reportForm.setMonth("June");
							 if(reportForm.getReportDTO().getMonthTarget().equals("07"))
								 reportForm.setMonth("July");
							 if(reportForm.getReportDTO().getMonthTarget().equals("08"))
								 reportForm.setMonth("August");
							 if(reportForm.getReportDTO().getMonthTarget().equals("09"))
								 reportForm.setMonth("September");
							 if(reportForm.getReportDTO().getMonthTarget().equals("10"))
								 reportForm.setMonth("October");
							 if(reportForm.getReportDTO().getMonthTarget().equals("11"))
								 reportForm.setMonth("November");
							 if(reportForm.getReportDTO().getMonthTarget().equals("12"))
								 reportForm.setMonth("December");
							returnPage = "subjectWiseStmpRegFeesView"; 
							
						}
					    else if(actionValue.equalsIgnoreCase("backSubWiseReport")){
						       
							
							 reportForm.getReportDTO().setYear("");
							 reportForm.getReportDTO().setMonthTarget("");
							 returnPage = "subjectWiseStmpRegFee"; 
						} 
					    else if(actionValue.equalsIgnoreCase("submitCompInfoReport")){
							//returnPage = "mainpage";
					    	ArrayList regDocReport=reportBD.getCompInfoReport(reportForm.getReportDTO());
							ArrayList nodocList=reportBD.getNoDocCompInfoReport(reportForm.getReportDTO());
						//	ArrayList list = (ArrayList) totalregDocReport.get(0);
						
							reportForm.getReportDTO().setReportList(regDocReport);
							reportForm.getReportDTO().setNoDocReportList(nodocList);
							 if(reportForm.getReportDTO().getMonthTarget().equals("01"))
								 reportForm.setMonth("January");
							 if(reportForm.getReportDTO().getMonthTarget().equals("02"))
								 reportForm.setMonth("February");
							 if(reportForm.getReportDTO().getMonthTarget().equals("03"))
								 reportForm.setMonth("March");
							 if(reportForm.getReportDTO().getMonthTarget().equals("04"))
								 reportForm.setMonth("April");
							 if(reportForm.getReportDTO().getMonthTarget().equals("05"))
								 reportForm.setMonth("May");
							 if(reportForm.getReportDTO().getMonthTarget().equals("06"))
								 reportForm.setMonth("June");
							 if(reportForm.getReportDTO().getMonthTarget().equals("07"))
								 reportForm.setMonth("July");
							 if(reportForm.getReportDTO().getMonthTarget().equals("08"))
								 reportForm.setMonth("August");
							 if(reportForm.getReportDTO().getMonthTarget().equals("09"))
								 reportForm.setMonth("September");
							 if(reportForm.getReportDTO().getMonthTarget().equals("10"))
								 reportForm.setMonth("October");
							 if(reportForm.getReportDTO().getMonthTarget().equals("11"))
								 reportForm.setMonth("November");
							 if(reportForm.getReportDTO().getMonthTarget().equals("12"))
								 reportForm.setMonth("December");
							returnPage = "ComprevenueView"; 
							
						}   
					    else if(actionValue.equalsIgnoreCase("backCompInfoReport")){
						       
							 reportForm.getReportDTO().setDistrictID("");

							 reportForm.getReportDTO().setYear("");
							 reportForm.getReportDTO().setMonthTarget("");
							 returnPage = "ComprevenueScreen";
						} 
				//	}
					else if(labl.equalsIgnoreCase("7")){
						if(actionValue.equalsIgnoreCase("submit")){
							reportList = reportBD.getCompRevenueReceiptDetails(reportForm);
							reportForm.setReportList(reportList);
							session.setAttribute("ReportLst", reportForm);
							logger.debug("after success");				
							logger.debug("REPORTLIST="+reportList);
							String type=reportForm.getRadioSearch();
							if (type.equalsIgnoreCase("Month")){
							
								returnPage ="revenuecompsuccessmonth";
								//returnPage = ReportConstants.noDataFound;
							}
							else
								returnPage="revenuecompsuccessperiod";
								//returnPage = ReportConstants.noDataFound;
							
							logger.debug("return page@@@@@@@@@@2"+returnPage);
						}
						
					    else if(actionValue.equalsIgnoreCase("cancel")){
							//returnPage = "mainpage";
					    	returnPage = ReportConstants.noDataFound;
							logger.debug("after cancel");
						}			
					}
				
				}
			}
		 
		 //incase of cesswiseinf Report
		 if ("CesswiseInf".equalsIgnoreCase(reportForm.getActionForm()))
			{
				String actionValue =reportForm.getActionID();
				if(actionValue!=null){
					
						if(actionValue.equalsIgnoreCase("submit")){	
							reportList = reportBD.getCessDetails(reportForm);
							reportForm.setReportList(reportList);
							session.setAttribute("ReportLst", reportForm);
							logger.debug("after success");				
							logger.debug("REPORTLIST="+reportList);
							if(reportList.size()==0)
							{	
							//returnPage = ReportConstants.noDataFound;
							request.setAttribute("error", "No records found");
							returnPage = "CesswiseScreen";
							}
							else
							{
								returnPage ="cesssuccess";
							}
							//returnPage = ReportConstants.noDataFound;
							
						}
						
					else if(actionValue.equalsIgnoreCase("cancel")){
							//returnPage = "mainpage";
							returnPage = ReportConstants.noDataFound;
							logger.debug("after cancel");
					}			
				}	
			
			}
		 //incase of RRCCases Report
		 //ASHIMA
		 if ("RRCCases".equalsIgnoreCase(reportForm.getActionForm()))
			{
				String actionValue =reportForm.getActionID();
				if(actionValue!=null){
					
						if(actionValue.equalsIgnoreCase("submit")){
							reportList = reportBD.getRRCDetails(reportForm);
							reportForm.setReportList(reportList);
							session.setAttribute("ReportLst", reportForm);
							logger.debug("after success");				
							logger.debug("REPORTLIST="+reportList);
							String type=reportForm.getRadioSearch();
							if (type.equalsIgnoreCase("Month")){
							
								returnPage ="rrcsuccessmonth";
								//returnPage = ReportConstants.noDataFound;
							}
							else if (type.equalsIgnoreCase("Period")){
								returnPage="rrcsuccessperiod";
								//returnPage = ReportConstants.noDataFound;
							
							logger.debug("return page@@@@@@@@@@2"+returnPage);
							}
							else  if (type.equalsIgnoreCase("Year")){
								returnPage="rrcsuccessyear";
								//returnPage = ReportConstants.noDataFound;
								
								logger.debug("return page@@@@@@@@@@2"+returnPage);
							}
					}
						
					else if(actionValue.equalsIgnoreCase("cancel")){
							//returnPage = "mainpage";
							returnPage = ReportConstants.noDataFound;
							logger.debug("after cancel");
					}			
				}	
			
			}
		//incase of pdf of Cess wise information report
		 if ("CessPopup".equalsIgnoreCase(reportForm.getActionForm()))
			{
				String actionValue =reportForm.getActionID();	
				if(actionValue.equalsIgnoreCase("EXPORT_TO_PDF")){
					File pdfFile = reportBD.prepareCessPdfFile(reportForm);
					if(pdfFile == null){
						logger.debug("Export Failure");				
					}else{
						logger.debug("Export success");
						request.setAttribute("EXCEL_REPORT_FILE", pdfFile);
					}
					returnPage = "pdfsuccess";
				}
			}
		 
		//incase of pdf of RRC cases report for month
		 if ("RRcPopupmth".equalsIgnoreCase(reportForm.getActionForm()))
			{
				String actionValue =reportForm.getActionID();	
				if(actionValue.equalsIgnoreCase("EXPORT_TO_PDF")){
					File pdfFile = reportBD.prepareRRCmthPdfFile(reportForm);
					if(pdfFile == null){
						logger.debug("Export Failure");				
					}else{
						logger.debug("Export success");
						request.setAttribute("EXCEL_REPORT_FILE", pdfFile);
					}
					returnPage = "pdfsuccess";
				}
			}
		//incase of pdf of RRC cases report for Period
		 if ("RRcPopupPeriod".equalsIgnoreCase(reportForm.getActionForm()))
			{
				String actionValue =reportForm.getActionID();	
				if(actionValue.equalsIgnoreCase("EXPORT_TO_PDF")){
					File pdfFile = reportBD.prepareRRCperiodPdfFile(reportForm);
					if(pdfFile == null){
						logger.debug("Export Failure");				
					}else{
						logger.debug("Export success");
						request.setAttribute("EXCEL_REPORT_FILE", pdfFile);
					}
					returnPage = "pdfsuccess";
				}
			}	
		//incase of pdf of RRC cases report for year
		 if ("RRcPopupYear".equalsIgnoreCase(reportForm.getActionForm()))
			{
				String actionValue =reportForm.getActionID();	
				if(actionValue.equalsIgnoreCase("EXPORT_TO_PDF")){
					File pdfFile = reportBD.prepareRRCYearPdfFile(reportForm);
					if(pdfFile == null){
						logger.debug("Export Failure");				
					}else{
						logger.debug("Export success");
						request.setAttribute("EXCEL_REPORT_FILE", pdfFile);
					}
					returnPage = "pdfsuccess";
				}
			}	
		 //incase of pdf of RevenueRecipt report
		 if ("RevenueReceiptPopup".equalsIgnoreCase(reportForm.getActionForm()))
			{
				String actionValue =reportForm.getActionID();	
				if(actionValue.equalsIgnoreCase("EXPORT_TO_PDF")){
					File pdfFile = reportBD.prepareRevenrecepPdfFile(reportForm);
					if(pdfFile == null){
						logger.debug("Export Failure");				
					}else{
						logger.debug("Export success");
						request.setAttribute("EXCEL_REPORT_FILE", pdfFile);
					}
					returnPage = "pdfsuccess";
				}
			}	
		//incase of pdf of Comparative RevenueRecipt month report
		 if ("CompRevenueReceiptPopupmth".equalsIgnoreCase(reportForm.getActionForm()))
			{
				String actionValue =reportForm.getActionID();	
				if(actionValue.equalsIgnoreCase("EXPORT_TO_PDF")){
					File pdfFile = reportBD.preparecompRevrepPdfmth(reportForm);
					if(pdfFile == null){
						logger.debug("Export Failure");				
					}else{
						logger.debug("Export success");
						request.setAttribute("EXCEL_REPORT_FILE", pdfFile);
					}
					returnPage = "pdfsuccessmthofcomp";
				}
			}	
		 
		 
		 
		 
		//incase of pdf of Comparative RevenueRecipt Period report
		 if ("CompRevenueReceiptPopupperiod".equalsIgnoreCase(reportForm.getActionForm()))
			{
				String actionValue =reportForm.getActionID();	
				if(actionValue.equalsIgnoreCase("EXPORT_TO_PDF")){
					File pdfFile = reportBD.preparecompRevrepPdfperiod(reportForm);
					if(pdfFile == null){
						logger.debug("Export Failure");				
					}else{
						logger.debug("Export success");
						request.setAttribute("EXCEL_REPORT_FILE", pdfFile);
					}
					returnPage = "pdfsuccess";
				}
			}	
		 
		 
		 
		 reportForm.setActionForm(null);
		 reportForm.setActionID(null);
		 return mapping.findForward(returnPage);
	}
}
		
		
		
		
		
	