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

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.report.action.reportPdf.Report;
import com.wipro.igrs.report.bo.PendingCourtCaseReportBO;
import com.wipro.igrs.report.bo.ReportBO;
import com.wipro.igrs.report.common.MarketTrendReportConstants;
import com.wipro.igrs.report.common.ReportConstants;
import com.wipro.igrs.report.dto.ReportDTO;
import com.wipro.igrs.report.form.ReportForm;
import com.wipro.igrs.report.util.PropertiesFileReader;

public class ReportAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(ReportAction.class);

	/**
	 * Execute method is used as controller
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {

		logger.debug("my two");
		logger.debug("in Action Class");
	//	HttpSession session = request.getSession(true);
		ReportForm eForm = (ReportForm) form;
		String language=(String)session.getAttribute("languageLocale");
		ReportBO reportBD = new ReportBO();
		//Report reportPDF = new Report();
		PendingCourtCaseReportBO pendingCourtBO = new PendingCourtCaseReportBO();
		ArrayList districtList = pendingCourtBO.getDistrictList(language);

		eForm.setDistrictList(districtList);
		//rachita for comparative
		//dto.setDistrictNameList(bo.getDistrictList());
		// request.setAttribute("districtlst",eForm);

		ArrayList userTypeList = new ArrayList();
		String forwardJsp = "";
		if (eForm != null) {

			String actionName = eForm.getActionName();
			String formName = eForm.getFormName();
			ReportDTO dto = eForm.getReportDTO();
			logger.debug("actionID value========" + actionName);

			//Report rpt = new Report();

			String param = request.getParameter("param");
			String label = request.getParameter("label");
			//temp val
			if(param == null)
			    param = label;
			if(eForm.getActionName() == null)
			    eForm.setActionName("");
			
			eForm.setDistrictList(districtList);
			request.setAttribute("districtlst", eForm);

			ArrayList sroList = reportBD.getSroNameDetails();
			eForm.setSroList(sroList);
			request.setAttribute("srolst", eForm);

			ArrayList droList = reportBD.getDroNameDetails();
			eForm.setDroList(droList);
			request.setAttribute("drolst", eForm);
			
			//COMMENTED BY RACHITA---ORDER TO AVOID ERROR
			//ArrayList factorList = reportBD.getEffectFactorDetails();

			ArrayList fiscalYearList = reportBD.getFiscalYearDetails();
			eForm.setFiscalYearList(fiscalYearList);
			request.setAttribute("fiscalYearlst", eForm);

			//eForm.setFactorList(factorList);
			//END OF CODE COMMENTED BY RACHITA
			request.setAttribute("FactorLst", eForm);
			logger.debug("label value========" + label);
			
			if ("1".equalsIgnoreCase(param)) {
				forwardJsp = ReportConstants.REV_PROJ_SUCCESS;
				actionName = null;
				logger.debug("returnpage^^^^^^^^^^" + forwardJsp);
			}
			if ("2".equalsIgnoreCase(param)) {
				forwardJsp = ReportConstants.SRO_STAMP_REG_FEE_SUCCESS;
				actionName = null;
				logger.debug("returnpage^^^^^^^^^^" + forwardJsp);
			}
			if ("3".equalsIgnoreCase(param)) {
				forwardJsp = ReportConstants.SUB_STAMP_REG_FEE_SUCCESS;
				actionName = null;
				logger.debug("returnpage^^^^^^^^^^" + forwardJsp);
			}
			// START added by rachita for comparative figures of revenue receipts
			if ("4".equalsIgnoreCase(param)) {
				
				//eForm.setFiscalYearList(reportBD.getYearForJudicalStamp(reportBD.getYearCheck()));
				eForm.setFiscalYearList(reportBD.getYearForCreateTarget(reportBD.getYearCheck()));
				dto.setFromYear("");
				dto.setDistrictId("");
				eForm.setActionName("");
				forwardJsp = "compRevSuccess";
				
				logger.debug("returnpage^^^^^^^^^^" + forwardJsp);
			}
			 if ("compRevUpdate".equalsIgnoreCase(param)) {
	   				eForm.setFiscalYearList(reportBD.getYearForCreateTarget(reportBD.getYearCheck()));
	   				dto.setFromYear("");
	   				dto.setDistrictId("");
	   				forwardJsp = "compRevSuccessUpdate";
	   				eForm.setActionName("");
					//eForm.setUpdateFlag(0);
	   				eForm.setUpdateFlagForView(1);
					logger.debug("returnpage^^^^^^^^^^" + forwardJsp);
	   				//actionName = null;
	   				logger.debug("returnpage^^^^^^^^^^" + forwardJsp);
	   			}
	            if (eForm.getActionName()
						.equalsIgnoreCase("UPDATEFLAGFORVIEW")) {
            	   ArrayList arr = new ArrayList();
   				ArrayList sub =new ArrayList();
   				ArrayList main =new ArrayList();
   				String fromYear=eForm.getReportDTO().getFromYear();
   				String districtID=eForm.getReportDTO().getDistrictId();
   				
   				int fYear=(Integer.parseInt(fromYear));
   				int j=fYear+1;
   				String finanYear=fYear + "-" + j;
   				session.setAttribute("finanYear", finanYear);
   				session.setAttribute("district", districtID);
   				PendingCourtCaseReportBO objPending = new PendingCourtCaseReportBO();
				arr = objPending.viewCreateTarget(districtID,finanYear);
   				if(arr.size()== 0){
   					//dto.setUpdateFlag("0");
   					eForm.setUpdateFlagForView(0);
   					eForm.setFiscalYearList(reportBD.getYearForCreateTarget(reportBD.getYearCheck()));
   					logger.debug("update flag^^^^^^^^^^0"  );
   					//session.setAttribute("UpdateFlag", "0");
   					eForm.setActionName("");
   					eForm.getReportDTO().setFromYear("");
   					eForm.getReportDTO().setDistrictId("");
   	   				forwardJsp = "compRevSuccessUpdate";
   	   				return mapping.findForward(forwardJsp);				
   				}
   				else{
   					
   					eForm.setUpdateFlagForView(1);
   					eForm.setFiscalYearList(reportBD.getYearForCreateTarget(reportBD.getYearCheck()));
   					logger.debug("update flag^^^^^^^^^^1"  );
   				
   				//	session.setAttribute("UpdateFlag", "1");
   					eForm.setActionName("");
   	   				forwardJsp = "compRevSuccessUpdate";
   	   				return mapping.findForward(forwardJsp);
   	   				
   				}
				
				}	
   				
			
	            //Added by rachita for create target update flag
	               if (eForm.getActionName()
	 						.equalsIgnoreCase("UPDATEFLAG")) {
	             	   ArrayList arr = new ArrayList();
	    				ArrayList sub =new ArrayList();
	    				ArrayList main =new ArrayList();
	    				String fromYear=eForm.getReportDTO().getFromYear();
	    				String districtID=eForm.getReportDTO().getDistrictId();
	    				
	    				int fYear=(Integer.parseInt(fromYear));
	    				int j=fYear+1;
	    				String finanYear=fYear + "-" + j;
	    				session.setAttribute("finanYear", finanYear);
	    				session.setAttribute("district", districtID);
	    				PendingCourtCaseReportBO objPending = new PendingCourtCaseReportBO();
	 				arr = objPending.viewCreateTarget(districtID,finanYear);
	    				if(arr.size()==0){
	    					//dto.setUpdateFlag("0");
	    					eForm.setUpdateFlag(0);
	    					eForm.setFiscalYearList(reportBD.getYearForCreateTarget(reportBD.getYearCheck()));
	    					logger.debug("update flag^^^^^^^^^^0"  );
	    					//session.setAttribute("UpdateFlag", "0");
	    					eForm.setActionName("");
	    	   				forwardJsp = "compRevSuccess";
	    	   				return mapping.findForward(forwardJsp);				
	    				}
	    				else{
	    					
	    					eForm.setUpdateFlag(1);
	    					eForm.setFiscalYearList(reportBD.getYearForCreateTarget(reportBD.getYearCheck()));
	    					logger.debug("update flag^^^^^^^^^^1"  );
	    					eForm.getReportDTO().setFromYear("");
	    					eForm.getReportDTO().setDistrictId("");
	    				//	session.setAttribute("UpdateFlag", "1");
	    					eForm.setActionName("");
	    	   				forwardJsp = "compRevSuccess";
	    	   				return mapping.findForward(forwardJsp);
	    	   				
	    				}
	 				
	 				}	
	    					
	    					
	    				//end 
			
			 if("COMPARATIVE_SUBMIT_ACTION_VIEW".equalsIgnoreCase(eForm.getActionName()))
				{
					
					
					ArrayList arr = new ArrayList();
					ArrayList sub =new ArrayList();
					ArrayList main =new ArrayList();
					String fromYear=eForm.getReportDTO().getFromYear();
					String districtID=eForm.getReportDTO().getDistrictId();
					
					int fYear=(Integer.parseInt(fromYear));
					int j=fYear+1;
					String finanYear=fYear + "-" + j;
					session.setAttribute("finanYear", finanYear);
					session.setAttribute("district", districtID);
					PendingCourtCaseReportBO objPending = new PendingCourtCaseReportBO();
					arr = objPending.viewComparitive(districtID,finanYear);
					if(arr.size()==0){
						dto.setFlag("0");
										forwardJsp = "compRevSuccessUpdate";
										eForm.setFiscalYearList(reportBD.getYearForJudicalStamp(reportBD.getYearCheck()));
										eForm.setActionName("");
										return mapping.findForward(forwardJsp);
					}
					else{
						dto.setFlag("1");
						sub = (ArrayList)arr.get(0);
						
							ReportDTO a = new ReportDTO();
							eForm.getReportDTO().setJanTarget((String)sub.get(0));
							eForm.getReportDTO().setFebTarget((String)sub.get(1));
							eForm.getReportDTO().setMarTarget((String)sub.get(2));
							eForm.getReportDTO().setAprTarget((String)sub.get(3));
							eForm.getReportDTO().setMayTarget((String)sub.get(4));
							eForm.getReportDTO().setJunTarget((String)sub.get(5));
							eForm.getReportDTO().setJulTarget((String)sub.get(6));
							eForm.getReportDTO().setAugTarget((String)sub.get(7));
							eForm.getReportDTO().setSepTarget((String)sub.get(8));
							eForm.getReportDTO().setOctTarget((String)sub.get(9));
							eForm.getReportDTO().setNovTarget((String)sub.get(10));
							eForm.getReportDTO().setDecTarget((String)sub.get(11));
							eForm.getReportDTO().setJanTargetC((String)sub.get(12));
							eForm.getReportDTO().setFebTargetC((String)sub.get(13));
							eForm.getReportDTO().setMarTargetC((String)sub.get(14));
							eForm.getReportDTO().setAprTargetC((String)sub.get(15));
							eForm.getReportDTO().setMayTargetC((String)sub.get(16));
							eForm.getReportDTO().setJunTargetC((String)sub.get(17));
							eForm.getReportDTO().setJulTargetC((String)sub.get(18));
							eForm.getReportDTO().setAugTargetC((String)sub.get(19));
							eForm.getReportDTO().setSepTargetC((String)sub.get(20));
							eForm.getReportDTO().setOctTargetC((String)sub.get(21));
							eForm.getReportDTO().setNovTargetC((String)sub.get(22));
							eForm.getReportDTO().setDecTargetC((String)sub.get(23));
							main.add(a);
							a.setViewList(main);
							
						
						
					eForm.setActionName("");
					forwardJsp = "compRevenueReceiptsUpdateView";
					return mapping.findForward(forwardJsp);
					}
				}
			
			
			
			
			
			if("COMPARATIVE_SUBMIT_ACTION".equalsIgnoreCase(eForm.getActionName()))
			{
				
				dto.setMarTarget("");
				dto.setAprTarget("");
				dto.setMayTarget("");
				dto.setJunTarget("");
				dto.setJulTarget("");
				dto.setAugTarget("");
				dto.setSepTarget("");
				dto.setOctTarget("");
				dto.setNovTarget("");
				dto.setDecTarget("");
				dto.setJanTarget("");
				dto.setFebTarget("");
				dto.setMarTarget("");
				
				dto.setMarTargetC("");
				dto.setAprTargetC("");
				dto.setMayTargetC("");
				dto.setJunTargetC("");
				dto.setJulTargetC("");
				dto.setAugTargetC("");
				dto.setSepTargetC("");
				dto.setOctTargetC("");
				dto.setNovTargetC("");
				dto.setDecTargetC("");
				dto.setJanTargetC("");
				dto.setFebTargetC("");
				dto.setMarTargetC("");
				eForm.setReportDTO(dto);
				
				ArrayList arr = new ArrayList();
				String fromYear=eForm.getReportDTO().getFromYear();
				String districtId=eForm.getReportDTO().getDistrictId();
				ArrayList loop =new ArrayList();
				int fYear=(Integer.parseInt(fromYear));
				int j=fYear+1;
				String finanYear=fYear + "-" + j;
				session.setAttribute("finanYear", finanYear);
				session.setAttribute("districtId", districtId);
				forwardJsp = "compRevenueReceiptsView";
				eForm.setActionName("");
				return mapping.findForward(forwardJsp);
			}
			// END added by rachita for comparative figures of revenue receipts 
			
			
			
			
			if ("5".equalsIgnoreCase(param)) {
				forwardJsp = ReportConstants.REG_DOC_COPY_REQUEST_SUCCESS;
				eForm.setActionName("");
				logger.debug("returnpage^^^^^^^^^^" + forwardJsp);
			}
			// added by rachita for create target
if ("compFigures".equalsIgnoreCase(param)) {
	eForm.getReportDTO().setFromYear("");
	eForm.getReportDTO().setToYear("");
	eForm.getReportDTO().setDistrictId("");	
	eForm.setFiscalYearList(reportBD.getYearForJudicalStamp(reportBD.getYearCheck()));
	
	eForm.setDistrictList(districtList);
	forwardJsp = "compFiguresRevenueReceipts";
	eForm.setActionName("");
	logger.debug("returnpage^^^^^^^^^^" + forwardJsp);
			}

//rachita for comparative figures of revenue receipts search screen
//added by rachitaaa
if("COMPARATIVE_FIGURES_SUBMIT_ACTION_VIEW".equalsIgnoreCase(eForm.getActionName()))
{
	PendingCourtCaseReportBO objPending1 = new PendingCourtCaseReportBO();
	String fromYear = eForm.getReportDTO().getFromYear();
	String toYear = eForm.getReportDTO().getToYear();
	int fYear=(Integer.parseInt(fromYear));
	int tYear=(Integer.parseInt(toYear));
	int tYears = tYear + 1;
	String a1 = null;
	String b1 = null;
	ArrayList arrOfYear = new ArrayList();
	ArrayList arr = new ArrayList();
	ArrayList progAchivement = new ArrayList();
	ArrayList subProgAchivement = new ArrayList();
	ArrayList mainProgAchivement = new ArrayList();
	
	ArrayList sub =new ArrayList();
	ArrayList main =new ArrayList();
	String DISTRICT1 = (String)eForm.getReportDTO().getDistrictId();

		
		
		for(int i=fYear ; i<tYears ; i++ )
		{
			
			
			ReportDTO dto123 = new ReportDTO();
			
			
			int c= i;
			c++;
			a1=i + "-" +c;
			System.out.println(a1);
			dto123.setYears(a1);
			arrOfYear.add(dto123);
			
			
			
		}
		//Added by Shreeraj
		ArrayList list=objPending1.getRevRcptMP(arrOfYear,DISTRICT1,language);
		ArrayList revTargetList=objPending1.getRevRcptMPTargets(arrOfYear,DISTRICT1);
		eForm.getReportDTO().setViewList(list);
		eForm.getReportDTO().setViewTargetList(revTargetList);
		
		eForm.setActionName("");
		//end
		
		
		forwardJsp = "compFiguresRevenueRecieptsView";
		return mapping.findForward(forwardJsp);
		
		


}

if ("RevRctMPBack".equals(actionName)) {
	eForm.getReportDTO().setFromYear("");
	eForm.getReportDTO().setToYear("");
	eForm.getReportDTO().setDistrictId("");
	eForm.setFiscalYearList(reportBD.getYearForJudicalStamp(reportBD.getYearCheck()));	
	eForm.setDistrictList(districtList);
	forwardJsp = "compFiguresRevenueReceipts";
	eForm.setActionName("");
}


			//Added by rachita



if ("ResetViewTargetUpdate".equals(actionName)) {
	
	
	dto.setMarTarget("");
	dto.setAprTarget("");
	dto.setMayTarget("");
	dto.setJunTarget("");
	dto.setJulTarget("");
	dto.setAugTarget("");
	dto.setSepTarget("");
	dto.setOctTarget("");
	dto.setNovTarget("");
	dto.setDecTarget("");
	dto.setJanTarget("");
	dto.setFebTarget("");
	dto.setMarTarget("");
	
	dto.setMarTargetC("");
	dto.setAprTargetC("");
	dto.setMayTargetC("");
	dto.setJunTargetC("");
	dto.setJulTargetC("");
	dto.setAugTargetC("");
	dto.setSepTargetC("");
	dto.setOctTargetC("");
	dto.setNovTargetC("");
	dto.setDecTargetC("");
	dto.setJanTargetC("");
	dto.setFebTargetC("");
	dto.setMarTargetC("");
	eForm.setReportDTO(dto);
	eForm.setActionName("");
	forwardJsp = "compRevenueReceiptsUpdateView";
	
}

if ("ResetUpdateTarget".equals(actionName)) {
	
	eForm.setFiscalYearList(reportBD.getYearForCreateTarget(reportBD.getYearCheck()));
	dto.setFromYear("");
	dto.setDistrictId("");
	eForm.setReportDTO(dto);
	 eForm.setActionName("");
	forwardJsp = "compRevSuccessUpdate";
}

if ("ResetCreateTarget".equals(actionName)) {
	
	eForm.setFiscalYearList(reportBD.getYearForCreateTarget(reportBD.getYearCheck()));
	dto.setFromYear("");
	dto.setDistrictId("");
	eForm.setReportDTO(dto);
	 eForm.setActionName("");
	forwardJsp = "compRevSuccess";
}

if ("ResetViewTarget".equals(actionName)) {
	
	
	dto.setMarTarget("");
	dto.setAprTarget("");
	dto.setMayTarget("");
	dto.setJunTarget("");
	dto.setJulTarget("");
	dto.setAugTarget("");
	dto.setSepTarget("");
	dto.setOctTarget("");
	dto.setNovTarget("");
	dto.setDecTarget("");
	dto.setJanTarget("");
	dto.setFebTarget("");
	dto.setMarTarget("");
	
	dto.setAprTargetC("");
	dto.setMayTargetC("");
	dto.setJunTargetC("");
	dto.setJulTargetC("");
	dto.setAugTargetC("");
	dto.setSepTargetC("");
	dto.setOctTargetC("");
	dto.setNovTargetC("");
	dto.setDecTargetC("");
	dto.setJanTargetC("");
	dto.setFebTargetC("");
	dto.setMarTargetC("");
	eForm.setReportDTO(dto);
     eForm.setActionName("");
	forwardJsp = "compRevenueReceiptsView";
}

if("COMPARITIVE_INSERT".equalsIgnoreCase(eForm.getActionName()))
{
	PendingCourtCaseReportBO objPending = new PendingCourtCaseReportBO();
	String CREATEDBY=(String)session.getAttribute("UserId");
	
	
	//String f=(String)eForm.getReportDTO().getFromYear();
	//String fr=f+1;
	//String FINANCIAL_YEAR = f + "-" + fr;
	String FINANCIAL_YEAR =(String) session.getAttribute("finanYear");
	String DISTRICT =(String) session.getAttribute("districtId");
	String JANUARY = eForm.getReportDTO().getJanTarget();
	String FEBRUARY = eForm.getReportDTO().getFebTarget();
	String MARCH = eForm.getReportDTO().getMarTarget();
	String APRIL = eForm.getReportDTO().getAprTarget();
	String MAY = eForm.getReportDTO().getMayTarget();
	String JUNE = eForm.getReportDTO().getJunTarget();
	String JULY = eForm.getReportDTO().getJulTarget();
	String AUGUST = eForm.getReportDTO().getAugTarget();
	String SEPTEMBER= eForm.getReportDTO().getSepTarget();
	String OCTOBER = eForm.getReportDTO().getOctTarget();
	String NOVEMBER = eForm.getReportDTO().getNovTarget();
	String DECEMBER = eForm.getReportDTO().getDecTarget();
	
	String JANUARYC = eForm.getReportDTO().getJanTargetC();
	String FEBRUARYC = eForm.getReportDTO().getFebTargetC();
	String MARCHC = eForm.getReportDTO().getMarTargetC();
	String APRILC = eForm.getReportDTO().getAprTargetC();
	String MAYC = eForm.getReportDTO().getMayTargetC();
	String JUNEC = eForm.getReportDTO().getJunTargetC();
	String JULYC = eForm.getReportDTO().getJulTargetC();
	String AUGUSTC = eForm.getReportDTO().getAugTargetC();
	String SEPTEMBERC= eForm.getReportDTO().getSepTargetC();
	String OCTOBERC = eForm.getReportDTO().getOctTargetC();
	String NOVEMBERC = eForm.getReportDTO().getNovTargetC();
	String DECEMBERC = eForm.getReportDTO().getDecTargetC();
	
	String CREATEDDATE =" ";
	String UPDATEDBY = " ";
	String UPDATEDDATE = " ";
	//String DISTRICT=" ";
	objPending.insertComparitive(FINANCIAL_YEAR,JANUARY,FEBRUARY,MARCH,
		                       APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,
		                       NOVEMBER,DECEMBER,CREATEDBY,CREATEDDATE,UPDATEDBY
		                       ,UPDATEDDATE,DISTRICT,JANUARYC,FEBRUARYC,MARCHC,
		                       APRILC,MAYC,JUNEC,JULYC,AUGUSTC,SEPTEMBERC,OCTOBERC,
		                       NOVEMBERC,DECEMBERC);
	
	forwardJsp = "successTarget";
	eForm.setActionName("");
}
			
			//Added by rachita

			if("COMPARITIVE_UPDATE".equalsIgnoreCase(eForm.getActionName()))
			{
				String UPDATEDBY=(String)session.getAttribute("UserId");
				PendingCourtCaseReportBO objPending = new PendingCourtCaseReportBO();
				String FINANCIAL_YEAR =(String) session.getAttribute("finanYear");
				String JANUARY = eForm.getReportDTO().getJanTarget();
				String FEBRUARY = eForm.getReportDTO().getFebTarget();
				String MARCH = eForm.getReportDTO().getMarTarget();
				String APRIL = eForm.getReportDTO().getAprTarget();
				String MAY = eForm.getReportDTO().getMayTarget();
				String JUNE = eForm.getReportDTO().getJunTarget();
				String JULY = eForm.getReportDTO().getJulTarget();
				String AUGUST = eForm.getReportDTO().getAugTarget();
				String SEPTEMBER= eForm.getReportDTO().getSepTarget();
				String OCTOBER = eForm.getReportDTO().getOctTarget();
				String NOVEMBER = eForm.getReportDTO().getNovTarget();
				String DECEMBER = eForm.getReportDTO().getDecTarget();
				
				String JANUARYC = eForm.getReportDTO().getJanTargetC();
				String FEBRUARYC = eForm.getReportDTO().getFebTargetC();
				String MARCHC = eForm.getReportDTO().getMarTargetC();
				String APRILC = eForm.getReportDTO().getAprTargetC();
				String MAYC = eForm.getReportDTO().getMayTargetC();
				String JUNEC = eForm.getReportDTO().getJunTargetC();
				String JULYC = eForm.getReportDTO().getJulTargetC();
				String AUGUSTC = eForm.getReportDTO().getAugTargetC();
				String SEPTEMBERC= eForm.getReportDTO().getSepTargetC();
				String OCTOBERC = eForm.getReportDTO().getOctTargetC();
				String NOVEMBERC = eForm.getReportDTO().getNovTargetC();
				String DECEMBERC = eForm.getReportDTO().getDecTargetC();
				
				String UPDATEDDATE = " ";
				String DISTRICT= eForm.getReportDTO().getDistrictId();
				String DISTRICTNAME= eForm.getReportDTO().getDistrictName();
				objPending.updateComparitive(FINANCIAL_YEAR,JANUARY,FEBRUARY,MARCH,
					                       APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,
					                       NOVEMBER,DECEMBER,UPDATEDBY
					                       ,UPDATEDDATE,DISTRICT,JANUARYC,FEBRUARYC,MARCHC,
					                       APRILC,MAYC,JUNEC,JULYC,AUGUSTC,SEPTEMBERC,OCTOBERC,
					                       NOVEMBERC,DECEMBERC);
				
				forwardJsp = "successTargetUpdate";
				eForm.setActionName("");
			}
			if ("CESS_REVENUE_LABEL".equalsIgnoreCase(param)) {
				
				eForm.setFiscalYearList(reportBD.getYearForJudicalStamp(reportBD.getYearCheck()));
				
				forwardJsp = "cessRevenue";
				eForm.setActionName("");
				logger.debug("returnpage^^^^^^^^^^" + forwardJsp);
		         eForm.getPenDTO().setToYear("");
		         eForm.getPenDTO().setFromYear("");
		         eForm.getPenDTO().setToMonth("");
		         eForm.getPenDTO().setFromMonth("");
		         
			}
			
			if("CESS_BLOCK_MUNICIPAL_SUBMIT_ACTION".equalsIgnoreCase(eForm.getActionName()))
			{
				
				PendingCourtCaseReportBO pbo= new PendingCourtCaseReportBO();
				ArrayList dataList=pbo.getSearchCessMunicipalReport(eForm.getPenDTO().getFromMonth(),eForm.getPenDTO().getFromYear(),eForm.getPenDTO().getToMonth(),eForm.getPenDTO().getToYear());
				eForm.getPenDTO().setCessReportList(dataList);
				forwardJsp = "cessRevenueView";
				eForm.setActionName("");
				return mapping.findForward(forwardJsp);
				
			}

			if (param == null) {
				param = (String) session.getAttribute("param");
				label = (String) session.getAttribute("label");
			} else {
				session.setAttribute("param", param);
				session.setAttribute("label", label);
			}

			logger.debug("Parameter:-" + param);

			if (ReportConstants.REVENUE_PROJECTION_FORM.equals(formName)) {
				if (ReportConstants.PERIOD_MONTH_ACTION.equals(actionName)) {

					dto.setRadioSearch(eForm.getRadioSearch());

					forwardJsp = ReportConstants.REV_PROJ_SUCCESS;
				}
				if (ReportConstants.USER_TYPE_ACTION.equals(actionName)) {
					dto.setUserType(eForm.getUserType());
					forwardJsp = ReportConstants.REV_PROJ_SUCCESS;
				}
				if (ReportConstants.SUBMIT_ACTION.equals(actionName)) {
					dto.setFromDate(eForm.getFromDate());
					logger.debug("dto.getFromDate()==========="
							+ dto.getFromDate());
					dto.setToDate(eForm.getToDate());
					logger.debug("eForm.getToDate()========="
							+ eForm.getToDate());
					dto.setMonth(eForm.getMonth());
					logger.debug("eForm.getMonth()========" + eForm.getMonth());
					dto.setRadioSearch(eForm.getRadioSearch());
					logger.debug("eForm.getRadioSearch()========="
							+ eForm.getRadioSearch());
					dto.setUserType(eForm.getUserType());
					logger.debug("eForm.getUserType()=========="
							+ eForm.getUserType());

					logger.debug("dto.getFacValIncDec()================"
							+ dto.getFacValIncDec());
					ArrayList reportList = reportBD
							.getRevenueProjectDetails(dto);
					eForm.setReportList(reportList);
					session.setAttribute("ReportLst", eForm);
					logger.debug("after success");
					forwardJsp = ReportConstants.REV_PROJ_SUCCESS_POPUP;
				}
				if (ReportConstants.RESET_ACTION.equals(actionName)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setRadioSearch("");
					dto.setRadioSearch("");
					dto.setUserType("");
					eForm.setUserType("");
					dto.setMonth("");
					dto.setOfficeId("");

					eForm.setReportDTO(dto);
					forwardJsp = ReportConstants.REV_PROJ_SUCCESS;
				}
				if (ReportConstants.CANCEL_ACTION.equals(actionName)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setRadioSearch("");
					dto.setRadioSearch("");
					dto.setUserType("");
					eForm.setUserType("");
					dto.setMonth("");
					dto.setOfficeId("");

					eForm.setReportDTO(dto);
					forwardJsp = ReportConstants.MAIN_PAGE;
				}
			}
			if (ReportConstants.REVENUE_PROJECTION_POPUP_FORM.equals(formName)) {
				if (ReportConstants.EXPORT_TO_PDF.equals(actionName)) {
					File pdfFile = prepareReveProjFile(eForm);
					if (pdfFile == null) {
						logger.debug("Export Failure");
					} else {
						logger.debug("Export success");
						request.setAttribute("PDF_REQUEST", pdfFile);
					}
					forwardJsp = ReportConstants.exportToPDF;
				}
			}

			if (ReportConstants.SRO_STAMP_REG_FEE_FORM.equals(formName)) {
				if (ReportConstants.PERIOD_MONTH_ACTION.equals(actionName)) {

					dto.setRadioSearch(eForm.getRadioSearch());

					forwardJsp = ReportConstants.SRO_STAMP_REG_FEE_SUCCESS;
				}

				if (ReportConstants.SUBMIT_ACTION.equals(actionName)) {
					dto.setFromDate(eForm.getFromDate());
					dto.setToDate(eForm.getToDate());
					dto.setMonth(eForm.getMonth());
					//added by ashima
					/*ArrayList fiscalYearList1 = reportBD.getFiscalYearDetails();
					eForm.setFiscalYearList(fiscalYearList1);
					request.setAttribute("fiscalYearlst", eForm);*/
                     //
					dto.setRadioSearch(eForm.getRadioSearch());
					
					ArrayList reportList = reportBD
							.getSROwiseStampRegFeeDetails(dto);
					
					eForm.setReportList(reportList);
					session.setAttribute("ReportLst", eForm);
					forwardJsp = ReportConstants.SRO_RPT_SUCCESS;
				}
				if (ReportConstants.RESET_ACTION.equals(actionName)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setRadioSearch("");
					dto.setRadioSearch("");
					dto.setMonth("");
					eForm.setReportDTO(dto);
					forwardJsp = ReportConstants.SRO_RPT_SUCCESS;
				}
				if (ReportConstants.CANCEL_ACTION.equals(actionName)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setRadioSearch("");
					dto.setRadioSearch("");
					dto.setMonth("");

					eForm.setReportDTO(dto);
					forwardJsp = ReportConstants.MAIN_PAGE;
				}
			}
			if (ReportConstants.SRO_STAMP_REG_FEE_FORM_POPUP.equals(formName)) {
				if (ReportConstants.EXPORT_TO_PDF.equals(actionName)) {
					File pdfFile = prepareSROwiseStampRegFeePdfFile(eForm);
					if (pdfFile == null) {
						logger.debug("Export Failure");
					} else {
						logger.debug("Export success");
						request.setAttribute("PDF_REQUEST", pdfFile);
					}
					forwardJsp = ReportConstants.exportToPDF;
				}
			}

			if (ReportConstants.SUB_STAMP_REG_FEE_FORM.equals(formName)) {
				if (ReportConstants.PERIOD_MONTH_ACTION.equals(actionName)) {

					dto.setRadioSearch(eForm.getRadioSearch());

					forwardJsp = ReportConstants.SUB_STAMP_REG_FEE_SUCCESS;
				}

				if (ReportConstants.SUBMIT_ACTION.equals(actionName)) {
					dto.setFromDate(eForm.getFromDate());
					dto.setToDate(eForm.getToDate());
					dto.setMonth(eForm.getMonth());
					
					///for fin year added by ashima
					/*String fy=dto.getYear()+"-"+(Integer.parseInt(dto.getYear().substring(2))+1);
					//dto.setYear(eForm.getYear());
					eForm.setFinancialYear(fy);*/
					//
					dto.setRadioSearch(eForm.getRadioSearch());
					dto.setUserType(eForm.getUserType());
					ArrayList reportList = reportBD
							.getSubwiseStampRegFeeDetails(dto);
					logger.debug("reportList.size()====" + reportList.size());
					logger.debug("reportList====" + reportList);
					if (reportList != null && reportList.size() > 0) {
						reportList.remove(reportList.size() - 2);
						logger.debug("reportList.size()-2===="
								+ (reportList.size() - 2));
						eForm.setReportList(reportList);
						ReportDTO reportDTO = (ReportDTO) reportList
								.get(reportList.size() - 1);
						eForm.setReportDTO(reportDTO);
					}
					session.setAttribute("ReportLst", eForm);
					logger.debug("after success");
					forwardJsp = 
						ReportConstants.SUB_STAMP_REG_FEE_SUCCESS_POPUP;
				}
				if (ReportConstants.RESET_ACTION.equals(actionName)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setRadioSearch("");
					dto.setRadioSearch("");
					dto.setMonth("");
					eForm.setReportDTO(dto);
					forwardJsp = ReportConstants.SUB_STAMP_REG_FEE_SUCCESS;
				}
				if (ReportConstants.CANCEL_ACTION.equals(actionName)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setRadioSearch("");
					dto.setRadioSearch("");
					dto.setMonth("");

					eForm.setReportDTO(dto);
					forwardJsp = ReportConstants.MAIN_PAGE;
				}
				
				
			}
			if (ReportConstants.SUB_STAMP_REG_FEE_POPUP_FORM.
					 equals(formName)) {
				if (ReportConstants.EXPORT_TO_PDF.equals(actionName)) {
					File pdfFile = prepareSUBwiseStampRegFeePdfFile(eForm);
					if (pdfFile == null) {
						logger.debug("Export Failure");
					} else {
						logger.debug("Export success");
						request.setAttribute("PDF_REQUEST", pdfFile);
					}
					forwardJsp = ReportConstants.exportToPDF;
				}
			}
			if (ReportConstants.COMPARATIVE_REVENUE_RECIPT_FORM
					.equals(formName)) {

				if (ReportConstants.SUBMIT_ACTION.equals(actionName)) {

					String fiscalId = eForm.getReportDTO().getFiscalId();
					logger.debug("fiscalId in  label 4=========" + fiscalId);
					logger.debug("bb4  fiscal Year===="
							+ eForm.getReportDTO().getFiscalYear());
					if(fiscalYearList!=null) {
						for (int i = 0; i < fiscalYearList.size(); i++) {
							ReportDTO reportDTO = 
								(ReportDTO) fiscalYearList.get(i);
							logger.debug("reportDTO.getFiscalYear()====="
									+ reportDTO.getFiscalYear());
							logger.debug("reportDTO.getFiscalId()====="
									+ reportDTO.getFiscalId());
	
							if (reportDTO.getFiscalId() != null
									&& fiscalId.equalsIgnoreCase(reportDTO
											.getFiscalId())) {
								eForm.getReportDTO().setFiscalYear(
										reportDTO.getFiscalYear());
							}
							logger.debug("afterfiscal Year===="
									+ eForm.getReportDTO().getFiscalYear());
						}
					}
					session.setAttribute("fiscalYR", eForm.getReportDTO()
							.getFiscalYear());
					String seFiscalYR = (String) session
							.getAttribute("fiscalYR");
					logger.debug("session fiscalYR " + seFiscalYR);
					ArrayList reportList = reportBD
							.getCompRevReceiptsDetails(dto);
					ReportDTO reportDTO = new ReportDTO();
					if (reportList != null) {
						if(reportList.size() >= 2) {
							reportList.remove(reportList.size() - 2);
						}	
						logger.debug("reportList size in comp==="
								+ reportList.size());
						eForm.setReportList(reportList);
						if(reportList.size() >=1) {
							reportDTO = (ReportDTO) reportList.get(reportList
								.size() - 1);
						}	

					}
					eForm.setReportDTO(reportDTO);
					session.setAttribute("ReportLst", eForm);
					forwardJsp = ReportConstants.COMP_REV_RECEIPT_SUCCESS_POPUP;
				}
				if (ReportConstants.RESET_ACTION.equals(actionName)) {
					dto.setDroId("");
					dto.setDistrictId("");
					dto.setFiscalId("");
					eForm.setReportDTO(dto);
					forwardJsp = ReportConstants.COMP_REV_RECEIPT_SUCCESS;
				}
				if (ReportConstants.CANCEL_ACTION.equals(actionName)) {

					dto.setDroId("");
					dto.setDistrictId("");
					dto.setFiscalId("");
					eForm.setReportDTO(dto);
					forwardJsp = ReportConstants.MAIN_PAGE;
				}
			}
			if (ReportConstants.COMPARATIVE_REVENUE_RECIPT_FORM_POPUP.
										equals(formName)) {
				if (ReportConstants.EXPORT_TO_PDF.equals(actionName)) {
					String str1 = (String) session.getAttribute("fiscalYR");
					File pdfFile = prepareCompRevReceiptsPdfFile(eForm,str1);
					if (pdfFile == null) {
						logger.debug("Export Failure");
					} else {
						logger.debug("Export success");
						request.setAttribute("PDF_REQUEST", pdfFile);
					}
					forwardJsp = ReportConstants.exportToPDF;
				}
				if (ReportConstants.EXPORT_TO_PDF_INC.equals(actionName)) {
					String str1 = (String) session.getAttribute("fiscalYR");
					File pdfFile = IncDecInRevTargetFile(eForm,str1);
					if (pdfFile == null) {
						logger.debug("Export Failure");
					} else {
						logger.debug("Export success");
						request.setAttribute("PDF_REQUEST", pdfFile);
					}
					forwardJsp = ReportConstants.exportToPDF;
				}
				if (ReportConstants.COMPARATIVE_INC_DEC_ACTION.
						equals(actionName)) {
					eForm.setReportDTO(dto);
					forwardJsp = 
						ReportConstants.COMPARATIVE_REVENUE_INC_DEC_POPUP;
				}
			}
			if (ReportConstants.REGISTERED_DOC_COPY_FORM.equals(formName)) {
				if (ReportConstants.PERIOD_MONTH_ACTION.equals(actionName)) {

					dto.setRadioSearch(eForm.getRadioSearch());

					forwardJsp = ReportConstants.REG_DOC_COPY_REQUEST_SUCCESS;
				}
				
				if (ReportConstants.SUBMIT_ACTION.equals(actionName)) {
					dto.setFromDate(eForm.getFromDate());
					dto.setToDate(eForm.getToDate());
					dto.setMonth(eForm.getMonth());
					dto.setRadioSearch(eForm.getRadioSearch());
					dto.setUserType(eForm.getUserType());
					ArrayList reportList = 
								reportBD.getRegDocDetails(dto);
					eForm.setReportList(reportList);
					session.setAttribute("ReportLst", reportList);
					forwardJsp = ReportConstants.REG_DOC_COPY_SUCCESS;
				}
				if (ReportConstants.RESET_ACTION.equals(actionName)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setRadioSearch("");
					dto.setRadioSearch("");
					dto.setMonth("");
					eForm.setReportDTO(dto);
					forwardJsp = ReportConstants.REG_DOC_COPY_REQUEST_SUCCESS;
				}
				if (ReportConstants.CANCEL_ACTION.equals(actionName)) {
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setRadioSearch("");
					dto.setRadioSearch("");
					dto.setMonth("");
					eForm.setReportDTO(dto);
					forwardJsp = ReportConstants.MAIN_PAGE;
				}
			}
			if (ReportConstants.REGISTERED_DOC_COPY_FORM_POPUP.
												equals(formName)) {
				if (ReportConstants.EXPORT_TO_PDF.equals(actionName)) {
					File pdfFile = prepareRegDocCopyPdfFile(eForm);
					if (pdfFile == null) {
						logger.debug("Export Failure");
					} else {
						logger.debug("Export success");
						request.setAttribute("PDF_REQUEST", pdfFile);
					}
					forwardJsp = ReportConstants.exportToPDF;
				}
			}
			eForm.setReportDTO(dto);

		}
		eForm.setActionName("");
		return mapping.findForward(forwardJsp);
	}

	private synchronized File prepareSROwiseStampRegFeePdfFile(ReportForm eForm) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 20, 20);
		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");

			fileName = pr.getValue("REPORT_LOCATION") + "SROwiseStampRegFee"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			// PdfWriter.getInstance(document, new
			// FileOutputStream("SROwiseStampRegFee.pdf"));
			document.open();

			Table datatable = new Table(20);
			int headerwidths[] = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
					10, 10, 10, 10, 10, 10, 10, 10, 10 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);
			datatable.setPadding(3);

			Cell title = new Cell(new Phrase(
					"Revenue Receipt SRO Wise - Stamp Duty/Registration Fee",
					FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(20);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);

			// datatable.setDefaultCellBorderWidth(2);
			// datatable.setDefaultHorizontalAlignment(1);
			Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			slno.setHeader(true);
			slno.setRowspan(2);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			// datatable.addCell("Header 1");
			Cell sroType = new Cell(new Phrase("Name of S.R.O.", FontFactory
					.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			sroType.setHeader(true);
			sroType.setRowspan(2);
			sroType.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(sroType);

			// datatable.addCell("Header 2");
			Cell income = new Cell(new Phrase(
					"Income During the month..............", FontFactory
							.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			income.setHeader(true);
			income.setColspan(3);
			income.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(income);

			Cell incMon = new Cell(new Phrase(
					"Income During the Same month of Previous Year",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			incMon.setHeader(true);
			incMon.setColspan(3);
			incMon.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(incMon);

			Cell compPercent = new Cell(new Phrase(
					"Comparative Increase/Decrease in Percent", FontFactory
							.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			compPercent.setHeader(true);
			compPercent.setColspan(3);
			compPercent.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(compPercent);

			Cell docReg = new Cell(new Phrase(
					"No. of Documents Registered During the month", FontFactory
							.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			docReg.setHeader(true);
			docReg.setColspan(3);
			docReg.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(docReg);

			Cell docRegMonth = new Cell(
					new Phrase(
							"No. of Documents Registered During the Same month of Previous Year",
							FontFactory.getFont(FontFactory.COURIER, 12,
									Font.BOLD)));
			docRegMonth.setHeader(true);
			docRegMonth.setColspan(3);
			docRegMonth.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(docRegMonth);

			Cell compDoc = new Cell(new Phrase(
					"Comperative Increase /Decrease in Percentage ",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			compDoc.setHeader(true);
			compDoc.setColspan(3);
			compDoc.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(compDoc);

			for (int i = 0; i < 6; i++) {
				Cell stampDty = new Cell(new Phrase("Stamp Duty", FontFactory
						.getFont(FontFactory.COURIER, 12, Font.BOLD)));
				stampDty.setHeader(true);
				stampDty.setColspan(1);
				stampDty.setBackgroundColor(new Color(196, 255, 255));
				datatable.addCell(stampDty);

				// datatable.addCell("Header 6");

				Cell regFee = new Cell(
						new Phrase("Registration Fee", FontFactory.getFont(
								FontFactory.COURIER, 12, Font.BOLD)));
				regFee.setHeader(true);
				regFee.setColspan(1);
				regFee.setBackgroundColor(new Color(196, 255, 255));
				datatable.addCell(regFee);
				// datatable.addCell("Header 7");

				Cell total = new Cell(new Phrase("Total", FontFactory.getFont(
						FontFactory.COURIER, 12, Font.BOLD)));
				total.setHeader(true);
				total.setColspan(1);
				total.setBackgroundColor(new Color(196, 255, 255));
				datatable.addCell(total);
			}
			datatable.endHeaders();
			// datatable.setDefaultCellBorderWidth(1);

			for (int i = 0; i < eForm.getReportList().size(); i++) {
				// datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
				ReportDTO reportDTO = new ReportDTO();
				reportDTO = (ReportDTO) eForm.getReportList().get(i);
				// datatable.setAlignment(Element.ALIGN_RIGHT);
				logger.debug("list values=====" + eForm.getReportList());
				logger
						.debug("list size========"
								+ eForm.getReportList().size());
				datatable.setRight(Element.ALIGN_RIGHT);
				datatable.addCell(String.valueOf(reportDTO.getSno()));
				logger.debug("sno===" + reportDTO.getSno());
				logger.debug("string sno===="
						+ String.valueOf(reportDTO.getSno()));
				datatable.addCell(reportDTO.getSroName());
				datatable
						.addCell(String.valueOf(reportDTO.getCurMonStampDty()));
				datatable.addCell(String.valueOf(reportDTO.getCurMonRegFee()));
				datatable.addCell(String.valueOf(reportDTO.getCurMonTot()));
				datatable
						.addCell(String.valueOf(reportDTO.getPreMonStampDty()));
				datatable.addCell(String.valueOf(reportDTO.getPreMonRegFee()));
				datatable.addCell(String.valueOf(reportDTO.getPreMonTot()));
				datatable.addCell(String.valueOf(reportDTO.getCompStampDty()));
				datatable.addCell(String.valueOf(reportDTO.getCompRegFee()));
				datatable.addCell(String.valueOf(reportDTO.getCompTot()));
				datatable.addCell(String.valueOf(reportDTO
						.getCurNoDocStampDty()));
				datatable
						.addCell(String.valueOf(reportDTO.getCurNoDocRegFee()));
				datatable.addCell(String.valueOf(reportDTO.getCurNoDocTot()));
				datatable.addCell(String.valueOf(reportDTO
						.getPreNoDocStampDty()));
				datatable
						.addCell(String.valueOf(reportDTO.getPreNoDocRegFee()));
				datatable.addCell(String.valueOf(reportDTO.getPreNoDocTot()));
				datatable.addCell(String.valueOf(reportDTO
						.getCompNoDocStampDty()));
				datatable.addCell(String
						.valueOf(reportDTO.getCompNoDocRegFee()));
				datatable.addCell(String.valueOf(reportDTO.getCompNoDocTot()));
			}
			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}

	private synchronized File prepareReveProjFile(ReportForm eForm) {

		Document document = new Document(PageSize.A4.rotate(), 50, 50, 40, 40);
		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");

			fileName = pr.getValue("REPORT_LOCATION") + "RevenueProjection"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			// PdfWriter.getInstance(document, new
			// FileOutputStream("RevenueProjection.pdf"));
			document.open();

			Table datatable = new Table(12);
			int headerwidths[] = { 10, 15, 10, 10, 10, 10, 10, 10, 10, 10, 10,
					10 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(105);
			datatable.setPadding(3);

			Cell title = new Cell(new Phrase("Revenue Projection Report",
					FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			// title.setLeading(100);
			title.setColspan(12);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);

			// datatable.setDefaultCellBorderWidth(2);
			// datatable.setDefaultHorizontalAlignment(1);
			Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			slno.setHeader(true);
			slno.setRowspan(3);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			// datatable.addCell("Header 1");
			Cell docType = new Cell(new Phrase("Document Type", FontFactory
					.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			docType.setHeader(true);
			docType.setRowspan(3);
			docType.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(docType);

			// datatable.addCell("Header 2");
			Cell yearType = new Cell(new Phrase(
					"Year (Last * Year - depending on Search Criteria)",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			yearType.setHeader(true);
			yearType.setColspan(6);
			yearType.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(yearType);

			Cell projRevenue = new Cell(new Phrase("Projected Revenue",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			projRevenue.setHeader(true);
			projRevenue.setColspan(4);
			projRevenue.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(projRevenue);

			// datatable.addCell("Header 3");
			Cell yearPrevType = new Cell(new Phrase(
					"Year (Last * Year) Comparision Data", FontFactory.getFont(
							FontFactory.COURIER, 12, Font.BOLD)));
			yearPrevType.setHorizontalAlignment(Element.ALIGN_CENTER);
			yearPrevType.setHeader(true);
			yearPrevType.setColspan(6);
			yearPrevType.setBackgroundColor(new Color(176, 255, 255));
			datatable.addCell(yearPrevType);

			Cell prjCurrType = new Cell(new Phrase("Projected Data",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			prjCurrType.setHorizontalAlignment(Element.ALIGN_CENTER);
			prjCurrType.setHeader(true);
			prjCurrType.setColspan(4);
			prjCurrType.setBackgroundColor(new Color(176, 255, 255));
			datatable.addCell(prjCurrType);

			// datatable.addCell("Header 5");
			Cell noDocPrev = new Cell(new Phrase("No of Docs", FontFactory
					.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			noDocPrev.setHeader(true);
			noDocPrev.setColspan(1);
			noDocPrev.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noDocPrev);

			// datatable.addCell("Header 6");

			Cell reveRecePrev = new Cell(new Phrase("Revenue Reciepts",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			reveRecePrev.setHeader(true);
			reveRecePrev.setColspan(1);
			reveRecePrev.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(reveRecePrev);
			// datatable.addCell("Header 7");

			Cell valPrev = new Cell(new Phrase("Valuation", FontFactory
					.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			valPrev.setHeader(true);
			valPrev.setColspan(1);
			valPrev.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(valPrev);

			// datatable.addCell("Header 8");
			Cell docPrevGro = new Cell(new Phrase(
					"Comparative Growth in Documents from previous Year",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			docPrevGro.setHeader(true);
			docPrevGro.setColspan(1);
			docPrevGro.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(docPrevGro);

			// datatable.addCell("Header 9");
			Cell docRevGro = new Cell(new Phrase(
					"Comparative Growth in Revenue from previous Year",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			docRevGro.setHeader(true);
			docRevGro.setColspan(1);
			docRevGro.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(docRevGro);

			Cell docValGro = new Cell(new Phrase(
					"Comparative Growth in Valuation from previous Year",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			docValGro.setHeader(true);
			docValGro.setColspan(1);
			docValGro.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(docValGro);

			Cell noDocRev = new Cell(new Phrase("No of Docs", FontFactory
					.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			noDocRev.setHeader(true);
			noDocRev.setColspan(1);
			noDocRev.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noDocRev);

			// datatable.addCell("Header 6");

			Cell reveReceRev = new Cell(new Phrase("Revenue Reciepts",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			reveReceRev.setHeader(true);
			reveReceRev.setColspan(1);
			reveReceRev.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(reveReceRev);
			// datatable.addCell("Header 7");

			Cell valRev = new Cell(new Phrase("Valuation", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			valRev.setHeader(true);
			valRev.setColspan(1);
			valRev.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(valRev);

			Cell effFactor = new Cell(new Phrase("Effecting Factor",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			effFactor.setHeader(true);
			effFactor.setColspan(1);
			effFactor.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(effFactor);

			datatable.endHeaders();
			// datatable.setDefaultCellBorderWidth(1);

			for (int i = 0; i < eForm.getReportList().size(); i++) {
				ReportDTO reportDTO = new ReportDTO();
				reportDTO = (ReportDTO) eForm.getReportList().get(i);
				// datatable.setAlignment(Element.ALIGN_RIGHT);
				logger.debug("list values=====" + eForm.getReportList());
				logger
						.debug("list size========"
								+ eForm.getReportList().size());
				datatable.setRight(Element.ALIGN_RIGHT);
				Cell sno = new Cell(String.valueOf(reportDTO.getSno()));
				sno.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(sno);
				Cell docuType = new Cell(reportDTO.getDocType());
				docuType.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(docuType);
				Cell cel = new Cell(String.valueOf(reportDTO.getNoOfDoc()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getRevReceipt()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getValuation()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getCompNoOfDoc()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getComprevReceipt()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getCompValuation()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getEffFacDoc()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getEffFacReceipt()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getEffFacValuation()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getEffFactorVal()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
			}
			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}

	private synchronized File prepareSUBwiseStampRegFeePdfFile(ReportForm eForm) {
		Document document = new Document(PageSize.A4.rotate(), 40, 40, 10, 10);
		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");

			fileName = pr.getValue("REPORT_LOCATION") + "SubWiseStampReg"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			// PdfWriter.getInstance(document, new
			// FileOutputStream("SubWiseStampReg.pdf"));
			document.open();

			Table datatable = new Table(6);
			int headerwidths[] = { 10, 24, 10, 10, 10, 10 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);
			datatable.setPadding(3);

			Cell title = new Cell(new Phrase(
					"Subject Wise Stamp Duty/Registration Fee", FontFactory
							.getFont(FontFactory.COURIER, 18, Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(6);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);
			// datatable.setDefaultCellBorderWidth(2);
			// datatable.setDefaultHorizontalAlignment(1);
			Cell slno = new Cell(new Phrase("S.No", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			slno.setHorizontalAlignment(Element.ALIGN_CENTER);
			slno.setHeader(true);
			slno.setRowspan(2);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			Cell sub = new Cell(new Phrase("Subject", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			sub.setHorizontalAlignment(Element.ALIGN_CENTER);
			sub.setHeader(true);
			sub.setRowspan(2);
			sub.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(sub);

			Cell exmMnth = new Cell(new Phrase("Exemption given in the Month",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			exmMnth.setHorizontalAlignment(Element.ALIGN_CENTER);
			exmMnth.setHeader(true);
			exmMnth.setColspan(4);
			exmMnth.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(exmMnth);

			/*
			 * Cell exmMnthApr = new Cell(new Phrase("Exemption given in the
			 * Duration April to ..........",
			 * FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			 * exmMnthApr.setHeader(true); exmMnthApr.setColspan(4);
			 * exmMnthApr.setBackgroundColor(new Color(255,255,36));
			 * datatable.addCell(exmMnthApr);
			 */

			// for(int i=0;i<2;i++){
			Cell noDocPrev = new Cell(new Phrase("No of Documents ",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			noDocPrev.setHorizontalAlignment(Element.ALIGN_CENTER);
			noDocPrev.setHeader(true);
			noDocPrev.setColspan(1);
			noDocPrev.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(noDocPrev);

			Cell stampDty = new Cell(new Phrase("Exemption in Stamp Duty ",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			stampDty.setHorizontalAlignment(Element.ALIGN_CENTER);
			stampDty.setHeader(true);
			stampDty.setColspan(1);
			stampDty.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(stampDty);

			Cell regFee = new Cell(new Phrase("Exemption in Registration Fee",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			regFee.setHorizontalAlignment(Element.ALIGN_CENTER);
			regFee.setHeader(true);
			regFee.setColspan(1);
			regFee.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(regFee);

			Cell total = new Cell(new Phrase("Total", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			total.setHorizontalAlignment(Element.ALIGN_CENTER);
			total.setHeader(true);
			total.setColspan(1);
			total.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(total);
			// }
			datatable.endHeaders();
			for (int i = 0; i < eForm.getReportList().size(); i++) {
				ReportDTO reportDTO = new ReportDTO();
				reportDTO = (ReportDTO) eForm.getReportList().get(i);

				datatable.setRight(Element.ALIGN_RIGHT);
				Cell cel = new Cell(String.valueOf(reportDTO.getSno()));
				cel.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(cel);

				cel = new Cell(reportDTO.getExemName());
				cel.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(cel);

				cel = new Cell(String.valueOf(reportDTO.getExemNoDoc()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);

				cel = new Cell(String.valueOf(reportDTO.getExemStampDty()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);

				cel = new Cell(String.valueOf(reportDTO.getExemRegFee()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);

				cel = new Cell(String.valueOf(reportDTO.getExemTot()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);

			}
			Cell cell = new Cell(new Phrase(" ", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			cell.setHeader(true);
			cell.setColspan(1);
			cell.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(cell);

			total = new Cell(new Phrase("Total", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			total.setHeader(true);
			total.setColspan(1);
			total.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(total);

			Cell cel = new Cell(String.valueOf(eForm.getReportDTO()
					.getExemNoDocTot()));
			cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
			datatable.addCell(cel);

			cel = new Cell(String.valueOf(eForm.getReportDTO()
					.getExemStampDtyTot()));
			cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
			datatable.addCell(cel);

			cel = new Cell(String.valueOf(eForm.getReportDTO()
					.getExemRegFeeTot()));
			cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
			datatable.addCell(cel);

			cel = new Cell(String.valueOf(eForm.getReportDTO().getExemTotTot()));
			cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
			datatable.addCell(cel);

			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}

	private synchronized File prepareRegDocCopyPdfFile(ReportForm eForm) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");

			fileName = pr.getValue("REPORT_LOCATION") + "RegDoc"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			// PdfWriter.getInstance(document, new
			// FileOutputStream("RegDoc.pdf"));
			document.open();

			Table datatable = new Table(6);
			int headerwidths[] = { 12, 12, 24, 24, 12, 24 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);
			datatable.setPadding(3);

			Cell title = new Cell(new Phrase(
					"Registered Document Copy Request", FontFactory.getFont(
							FontFactory.COURIER, 18, Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(6);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);

			// datatable.setDefaultCellBorderWidth(2);
			// datatable.setDefaultHorizontalAlignment(1);

			Cell slno = new Cell(new Phrase("S.No.", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			slno.setHeader(true);
			slno.setColspan(1);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			// datatable.addCell("Header 6");

			Cell userId = new Cell(new Phrase("User ID", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			userId.setHeader(true);
			userId.setColspan(1);
			userId.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(userId);

			Cell docDetails = new Cell(new Phrase("Document Details ",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			docDetails.setHeader(true);
			docDetails.setColspan(1);
			docDetails.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(docDetails);

			Cell prop = new Cell(new Phrase("Property Owner Name ", FontFactory
					.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			prop.setHeader(true);
			prop.setColspan(1);
			prop.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(prop);

			Cell count = new Cell(new Phrase("Count", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			count.setHeader(true);
			count.setColspan(1);
			count.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(count);

			Cell total = new Cell(new Phrase("Total", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			total.setHeader(true);
			total.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(total);

			datatable.endHeaders();
			// datatable.setDefaultCellBorderWidth(1);

			for (int i = 0; i < eForm.getReportList().size(); i++) {
				// datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
				ReportDTO reportDTO = new ReportDTO();
				reportDTO = (ReportDTO) eForm.getReportList().get(i);
				datatable.setRight(Element.ALIGN_RIGHT);
				Cell cel = new Cell(String.valueOf(reportDTO.getSno()));
				cel.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(cel);
				cel = new Cell(reportDTO.getRegUserId());
				cel.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(cel);
				cel = new Cell(reportDTO.getRegDocDetails());
				cel.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(cel);
				cel = new Cell(reportDTO.getRegPropOwnerName());
				cel.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getRegCount()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getRegTotal()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
			}
			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}

	private synchronized File prepareCompRevReceiptsPdfFile(ReportForm eForm,
			String str1) {
		// this.IncDecInRevTargetFile(eForm);
		Document document = new Document(PageSize.A4.rotate(), 40, 40, 5, 5);
		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");

			fileName = pr.getValue("REPORT_LOCATION") + "CompRevReceipts"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			// PdfWriter.getInstance(document, new
			// FileOutputStream("CompRevReceipts.pdf"));
			document.open();

			Font font = new Font();
			font.setColor(Color.BLUE);
			font.setStyle(Font.UNDERLINE);

			Table datatable = new Table(5);
			int headerwidths[] = { 4, 6, 10, 10, 15 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);
			datatable.setPadding(3);

			Cell title = new Cell(
					new Phrase(
							"Comparative Figures of Revenue Receipts of Districts relating to state of madya pradesh report ",
							FontFactory.getFont(FontFactory.COURIER, 18,
									Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(5);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);
			Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			slno.setHorizontalAlignment(Element.ALIGN_CENTER);
			slno.setHeader(true);
			slno.setRowspan(2);
			slno.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(slno);

			// datatable.addCell("Header 1");
			Cell mon = new Cell(new Phrase("Month", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			mon.setHorizontalAlignment(Element.ALIGN_CENTER);
			mon.setHeader(true);
			mon.setRowspan(2);
			mon.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(mon);
			logger.debug("seFiscalYR%%%%%%%%%%%%%%%%" + str1);
			Cell incMon = new Cell(new Phrase("Year" + "  " + str1, FontFactory
					.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			incMon.setHorizontalAlignment(Element.ALIGN_CENTER);
			incMon.setHeader(true);
			incMon.setColspan(3);
			incMon.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(incMon);

			// for(int i=0;i<5;i++){
			Cell progTarg = new Cell(new Phrase("Prog. Target", FontFactory
					.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			progTarg.setHorizontalAlignment(Element.ALIGN_CENTER);
			progTarg.setHeader(true);
			progTarg.setColspan(1);
			progTarg.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(progTarg);

			Cell progAchiev = new Cell(new Phrase("Prog. Achiev", FontFactory
					.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			progAchiev.setHorizontalAlignment(Element.ALIGN_CENTER);
			progAchiev.setHeader(true);
			progAchiev.setColspan(1);
			progAchiev.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(progAchiev);

			Cell incDec = new Cell(new Phrase("% in/decrease from pre. year",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			incDec.setHorizontalAlignment(Element.ALIGN_CENTER);
			incDec.setHeader(true);
			incDec.setColspan(1);
			incDec.setBackgroundColor(new Color(196, 255, 255));
			datatable.addCell(incDec);
			// }

			datatable.endHeaders();
			for (int i = 0; i < eForm.getReportList().size(); i++) {
				// datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
				ReportDTO reportDTO = new ReportDTO();
				reportDTO = (ReportDTO) eForm.getReportList().get(i);
				// datatable.setAlignment(Element.ALIGN_RIGHT);
				logger.debug("list values=====" + eForm.getReportList());
				logger
						.debug("list size========"
								+ eForm.getReportList().size());
				datatable.setAlignment(Element.ALIGN_RIGHT);
				Cell cel = new Cell(String.valueOf(reportDTO.getSno()));
				cel.setHorizontalAlignment(Element.ALIGN_CENTER);
				datatable.addCell(cel);
				cel = new Cell(reportDTO.getProgMonth());
				cel.setHorizontalAlignment(Element.ALIGN_LEFT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getProgTarg()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO.getProgAchiev()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
				cel = new Cell(String.valueOf(reportDTO
						.getPercentIncDecInAchiev()));
				cel.setHorizontalAlignment(Element.ALIGN_RIGHT);
				datatable.addCell(cel);
			}
			datatable.setCellsFitPage(true);
			document.add(datatable);
			// File fi
			/*
			 * document.add( new Paragraph( new Chunk("% increase/Decrease in
			 * Revenue Targets", font) .setRemoteGoto("RevTarg.pdf", "3")));
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}

	public synchronized File IncDecInRevTargetFile(ReportForm eForm, String str1) {

		Document document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
		String fileName = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			Date date = new Date();

			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("com.wipro.igrs.igrs");

			fileName = pr.getValue("REPORT_LOCATION") + "IncDecInRevTarget"
					+ dateFormat.format(date) + ".pdf";

			logger.debug("File is created :-" + fileName);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();

			Table datatable = new Table(2);
			int headerwidths[] = { 12, 12 };
			datatable.setWidths(headerwidths);
			datatable.setWidth(100);
			datatable.setPadding(3);

			Cell title = new Cell(
					new Phrase(
							"% Increase/Decrease in Revenue Targets (Indicative Figures)",
							FontFactory.getFont(FontFactory.COURIER, 18,
									Font.BOLD)));
			title.setHorizontalAlignment(Element.ALIGN_CENTER);
			title.setLeading(20);
			title.setColspan(2);
			title.setBorder(Rectangle.NO_BORDER);
			title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			datatable.addCell(title);

			logger.debug("fiscal year in incdecrpt==" + str1);
			Cell yr = new Cell(new Phrase("Year" + "  " + str1, FontFactory
					.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			yr.setHorizontalAlignment(Element.ALIGN_CENTER);
			yr.setHeader(true);
			yr.setColspan(2);
			yr.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(yr);

			// for(int i=0;i<5;i++){
			Cell rel = new Cell(new Phrase("%(+) OR (-) IN RELATION TO",
					FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			rel.setHorizontalAlignment(Element.ALIGN_CENTER);
			rel.setHeader(true);
			rel.setColspan(2);
			rel.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(rel);
			// }

			// for(int j=0;j<5;j++){
			Cell targ = new Cell(new Phrase("Target", FontFactory.getFont(
					FontFactory.COURIER, 12, Font.BOLD)));
			targ.setHorizontalAlignment(Element.ALIGN_CENTER);
			targ.setHeader(true);
			targ.setColspan(1);
			targ.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(targ);

			Cell preYear = new Cell(new Phrase("prev.year", FontFactory
					.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			preYear.setHorizontalAlignment(Element.ALIGN_CENTER);
			preYear.setHeader(true);
			preYear.setColspan(1);
			preYear.setBackgroundColor(new Color(255, 255, 36));
			datatable.addCell(preYear);
			// }
			datatable.endHeaders();
			datatable.setAlignment(Element.ALIGN_RIGHT);
			Cell cel = new Cell(String.valueOf(eForm.getReportDTO()
					.getProgTargTot()));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(cel);
			cel = new Cell(String.valueOf(eForm.getReportDTO()
					.getPercentIncDecInTarget()));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable.addCell(cel);
			datatable.setCellsFitPage(true);
			document.add(datatable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
		return (new File(fileName));
	}
}
