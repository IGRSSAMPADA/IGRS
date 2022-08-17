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
 * File Name	   		: MarketTrendReportAction.java
 *
 * Description	   		: Struts Action to view Revenue Management Details
 *
 * Version Number  		: 1.0 
 *
 * Created Date	   		: 31/05/2008  
 *
 * Modification History	: Created
 */

package com.wipro.igrs.report.action;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.report.action.reportPdf.Report;
import com.wipro.igrs.report.bo.MarketTrendReportBO;
import com.wipro.igrs.report.common.MarketTrendReportConstants;
import com.wipro.igrs.report.dto.MarketTrendReportDTO;
import com.wipro.igrs.report.form.MarketTrendReportForm;

public class MarketTrendReportAction extends BaseAction{
	
	private static final Logger logger = Logger
	.getLogger(MarketTrendReportAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,HttpSession session ) throws Exception {
		
		
		
		
		logger.debug("my two");
		logger.debug("in Action Class");
		//HttpSession session = request.getSession(true);
		MarketTrendReportForm eForm = (MarketTrendReportForm)form;
		String language = (String)session.getAttribute("languageLocale");
		MarketTrendReportBO reportBD = new MarketTrendReportBO();
		Report reportPDF = new Report();
		ArrayList dist = reportBD.getDistrictDetails();
		ArrayList areaList = reportBD.getAreaType();
		 ArrayList deedTypeList=new ArrayList();
		 deedTypeList=reportBD.getDeedTypeList();
		String confParam=reportBD.getConfigyear();
		int year=Integer.parseInt(confParam);
		
		 eForm.setFiscalYearList(reportBD.getYearForJudicalStamp(year));
		//ArrayList districtList = reportBD.getDistrictDetails();
	//	ArrayList bookTypeList = reportBD.getBookTypeList();
		String actionName=eForm.getActionName();
		eForm.getReportDTO().setDistList(dist);
		eForm.getReportDTO().setAreaList(areaList);
		eForm.getReportDTO().setDeedTypeList(deedTypeList);
		//eForm.setDistrictList(dist);
		//eForm.setAreaTypeList(areaList);
		//eForm.setAreaTypeList(reportBD.getAreaTypeDetails());
		//eForm.setBookTypeList(bookTypeList);
		/*GregorianCalendar calendar=new GregorianCalendar();
		String year=String.valueOf(calendar.get(calendar.YEAR));
		ArrayList yearList=new ArrayList();
		 int j=Integer.parseInt(year); 
			int i=0;
			for(i=j;i>1950;i--){
				MarketTrendReportDTO dto1 = new MarketTrendReportDTO();
				dto1.setFromYear(String.valueOf(i));
											
				
																
				yearList.add(dto1);
				
			}
			eForm.getReportDTO().setFromYearList(yearList);*/
	     //request.setAttribute("districtlst",eForm);
	     
	    ArrayList userTypeList = new ArrayList();
		String forwardJsp = "";
		if(eForm !=null) {
			
			String actionID = eForm.getActionID();
			MarketTrendReportDTO dto = eForm.getReportDTO();
			logger.debug("actionID value========"+actionID);
			 
			
			Report rpt = new Report();
			
			String param = request.getParameter("param");
			String label = request.getParameter("label");
			
			if(MarketTrendReportConstants.
					MARKET_TREND_REQUEST_LABEL.equals(param)) {
				eForm.getReportDTO().setRadio("");
				eForm.getReportDTO().setFromYear("");
				eForm.getReportDTO().setToYear("");
				eForm.getReportDTO().setDistId("");
				eForm.getReportDTO().setTehisilId("");
				eForm.getReportDTO().setAreaTypeId("");
				eForm.getReportDTO().setSubAreaId("");
				eForm.getReportDTO().setWardId("");
				eForm.getReportDTO().setMohallaId("");
				 dto.setTehsiList(new ArrayList());
				 dto.setSubAreaTypeList(new ArrayList());
				 dto.setWardList(new ArrayList());
				 dto.setMohallaList(new ArrayList());
				 eForm.getReportDTO().setDeedId("");
				/*eForm.setToDate("");
				eForm.setRadioSearch("");
				dto.setSearchType("");
				dto.setUserType("");
				eForm.setUserType("");
				dto.setMonth("");
				dto.setOfficeId("");
				dto.setDistrictId("");
				dto.setTehsilId("");
				dto.setAreaTypeId("");
				eForm.setAreaType("");
				dto.setWardPatwariId("");
				dto.setMohallaId("");
				dto.setBookTypeId("");
				eForm.setMarketTrendList(new ArrayList());
				eForm.setReportDTO(dto);*/
				session.setAttribute("Year", null);
				
				 actionName="";
				
				forwardJsp = 
					MarketTrendReportConstants.MARKET_TREND_REPORT;
				actionID = "";
			}
			
			String formName = eForm.getFormName();
			if(param == null) {
				param = (String)session.getAttribute("param"); 
				label = (String) session.getAttribute("label");
			}else {
				session.setAttribute("param", param);
				session.setAttribute("label", label);
			}
			
			logger.debug("Parameter:-"+param);
			
			if(MarketTrendReportConstants.
					MARKET_TREND_REPORT_FORM.equals(formName)) {
				if(MarketTrendReportConstants.
						PERIOD_MONTH_ACTION.equals(actionID)) {
					dto.setSearchType(eForm.getRadioSearch());
					forwardJsp = 
						MarketTrendReportConstants.MARKET_TREND_REPORT ;
				}
				if(MarketTrendReportConstants.
						USER_TYPE_ACTION.equals(actionID)) {
					dto.setUserType(eForm.getUserType());
					dto.setMonth(dto.getMonth());
					userTypeList = reportBD.getOfficeNameDetails(dto);
					eForm.setOfficeNameList(userTypeList);
					forwardJsp = 
						MarketTrendReportConstants.MARKET_TREND_REPORT ;
				}
					
				if(MarketTrendReportConstants.
						DISTRICT_TYPE_ACTION.equals(actionID)) {
					eForm.setTehsilList(reportBD.getTehsilTypeDetails(dto));
					forwardJsp = 
						MarketTrendReportConstants.MARKET_TREND_REPORT ;
				}
				if(MarketTrendReportConstants.
						AREA_TYPE_ACTION.equals(actionID)) {
					dto.setAreaType(eForm.getAreaType());
					eForm.setWardList(reportBD.getWardDetails(dto));
					forwardJsp = 
						MarketTrendReportConstants.MARKET_TREND_REPORT ;
				}
				if(MarketTrendReportConstants.
						WARD_PATWARI_ACTION.equals(actionID)) {
					 
					eForm.setMohallaList(reportBD.getMohallaDetails(dto));
					forwardJsp = 
						MarketTrendReportConstants.MARKET_TREND_REPORT ;
				}
				if(MarketTrendReportConstants.SUBMIT_ACTION.equals(actionID)) {
					
					 
					dto.setFromDate(eForm.getFromDate());
					dto.setToDate(eForm.getToDate());
					logger.debug("inside submitaction"
							+eForm.getFromDate()+":"
							+eForm.getToDate());
					String str[] = reportBD.returnYear(dto);
					dto.setFromYear(str[0]);
					dto.setToYear(str[1]);
					session.setAttribute("Year", dto);
					/*if(MarketTrendReportConstants.
							MARKET_TREND_REQUEST_LABEL.equals(param)) {*/
						ArrayList list = reportBD.getMarketTrendReport(dto);
						session.setAttribute(
								MarketTrendReportConstants.
								MARKET_TREND_LIST_SESSION, 
								list);
						//File file = reportPDF.createMarketTrendReport(list);
						 
						eForm.setMarketTrendList(list);
						forwardJsp = 
								MarketTrendReportConstants.
								DISPLAY_MARKET_TREND_REPORT;
					//}
				}
				if(MarketTrendReportConstants.RESET_ACTION.equals(actionID)) {
					
					 
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setRadioSearch("");
					dto.setSearchType("");
					dto.setUserType("");
					eForm.setUserType("");
					dto.setMonth("");
					dto.setOfficeId("");
					dto.setDistrictId("");
					dto.setTehsilId("");
					dto.setAreaTypeId("");
					eForm.setAreaType("");
					dto.setWardPatwariId("");
					dto.setMohallaId("");
					dto.setBookTypeId("");
					eForm.setMarketTrendList(new ArrayList());
					eForm.setReportDTO(dto);
					session.setAttribute("Year", null);
					forwardJsp = 
						MarketTrendReportConstants.MARKET_TREND_REPORT ;
				}
				if(MarketTrendReportConstants.CANCEL_ACTION.equals(actionID)) {
					forwardJsp = "welcome";
				}
			}
			//added by Shreeraj
			if(eForm.getActionName()==""){
				eForm.getReportDTO().setRadio("");
				
				forwardJsp = "marketTrendReport";
			}
			if (request.getParameter("pageName") != null
					&& request.getParameter("pageName").equalsIgnoreCase("markettrendtehsil"))
			{
				
				ArrayList tehsilList = new ArrayList();
			//	ArrayList wardlist =new ArrayList();
				//ArrayList mohallalist=new ArrayList();
				
				tehsilList = reportBD.getTehisil(eForm.getReportDTO().getDistId());
				
				
				//eForm.getReportDTO().setMohallaId(request.getParameter("mohallaOrVillage"));
			//	if (eForm.getReportDTO().getWardpatwarId()!=null) 
			//	{
				//	wardlist = reportBD.getWardOrPatwari((MarketTrendReportDTO)eForm.getReportDTO());// "AR-002"
				//	logger.debug(" in action ward id--<><>"+eForm.getReportDTO().getWardId());
				//	 mohallalist = reportBD.getMohallaOrVillage((MarketTrendReportDTO)eForm.getReportDTO());
					
			//	}
			//	dto.setMohallaList(mohallalist);
			//	dto.setWardList(wardlist);
				dto.setDistList(dist);
				dto.setTehsiList(tehsilList);
			//	dto.setAreaList(areaList);
				dto.setRadio("area");
				eForm.setReportDTO(dto);
				actionName="";
				 forwardJsp = "marketTrendReport";
				
			}
			
			if (request.getParameter("pageName") != null
					&& request.getParameter("pageName").equalsIgnoreCase("markettrendSubArea"))
			{
				if("2".equalsIgnoreCase(dto.getAreaTypeId())){
					 dto.setSubAreaTypeList(reportBD.getSubArea(language,dto.getAreaTypeId(),dto.getTehisilId(),"Y"));
			
				}else{
					 dto.setSubAreaTypeList(reportBD.getSubArea(language,dto.getAreaTypeId(),dto.getTehisilId(),"N"));
	
				}
				dto.setRadio("area");
				eForm.setReportDTO(dto);
				 forwardJsp = "marketTrendReport";
			}
			
			if (request.getParameter("pageName") != null
					&& request.getParameter("pageName").equalsIgnoreCase("markettrendWard"))
			{
				dto.setWardList(reportBD.getWardPatwari(language,dto.getSubAreaId(),dto.getTehisilId()));
				dto.setRadio("area");
				eForm.setReportDTO(dto); 
				forwardJsp = "marketTrendReport";
			}
			
			if (request.getParameter("pageName") != null
					&& request.getParameter("pageName").equalsIgnoreCase("markettrendMohalla"))
			{
				dto.setMohallaList(reportBD.getColonyName(language,dto.getWardId()));
				dto.setRadio("area");
				eForm.setReportDTO(dto); 
				forwardJsp = "marketTrendReport";
			}
			 if(actionName!=null && actionName.equalsIgnoreCase("MarketTrendSearch")){
				 logger.debug("Action Name-----"+eForm.getActionName()); 
				 ArrayList noofdoclist=new ArrayList();
				 ArrayList reportList=new ArrayList();
				 ArrayList totDuty=new ArrayList();
					ArrayList dutiesList=new ArrayList();
				 if(eForm.getReportDTO().getRadio().equalsIgnoreCase("dur")){	
					System.out.println(eForm.getReportDTO().getFromYear()+"    "+eForm.getReportDTO().getToYear());	
					//noofdoclist=reportBD.getdurReport(eForm.getReportDTO().getFromYearId(),eForm.getReportDTO().getToYearId());
					//dutiesList=reportBD.getDurDuty(eForm.getReportDTO().getFromYearId(),eForm.getReportDTO().getToYearId());
					reportList=reportBD.getdurReport(eForm.getReportDTO().getFromYear(),eForm.getReportDTO().getToYear(),language);
					 totDuty=reportBD.getTotalReportDur(eForm.getReportDTO().getFromYear(),eForm.getReportDTO().getToYear());
					//dutiesList=reportBD.getDurDuty("2005","2013");
					//reportList.addAll(dutiesList);
					//reportList.addAll(noofdoclist);
					//System.out.println(noofdoclist.get(0));
					
				/*	for(int l=0;l<noofdoclist.size();l++){
						ArrayList l1=(ArrayList)noofdoclist.get(i);
						if(l1!=null){
							MarketTrendReportDTO dto1 = new MarketTrendReportDTO();
							
						}
					}*/
					
				//	reportList.add(dutiesList);
					 eForm.getReportDTO().setMarket23colList(reportList);
					 
					 ArrayList totduty1=(ArrayList)totDuty.get(0);
					 eForm.getReportDTO().setDeedCount((String)totduty1.get(0));
					 eForm.getReportDTO().setTotnoofDoc((String)totduty1.get(1));
					 eForm.getReportDTO().setTotRev((String)totduty1.get(2));
					 eForm.getReportDTO().setTotVal((String)totduty1.get(3));
					
					 eForm.getReportDTO().setTotnoofDocComp((String)totduty1.get(4));
					 eForm.getReportDTO().setTotRevComp((String)totduty1.get(5));
					 eForm.getReportDTO().setTotValComp((String)totduty1.get(6));
				 }
				 if(eForm.getReportDTO().getRadio().equalsIgnoreCase("area")){	
					 reportList=reportBD.getAreaReport(eForm.getReportDTO());
					 eForm.getReportDTO().setMarket23colList(reportList);
					 totDuty=reportBD.getTotalReport(eForm.getReportDTO());
					 ArrayList totduty1=(ArrayList)totDuty.get(0);
					 ArrayList totsubduty2=(ArrayList)totDuty.get(1);
					 
					 ArrayList totduty2=(ArrayList)totsubduty2.get(0);
					 eForm.getReportDTO().setDeedCount((String)totduty1.get(0));
					 eForm.getReportDTO().setTotnoofDoc((String)totduty1.get(1));
					 eForm.getReportDTO().setTotRev((String)totduty1.get(2));
					 eForm.getReportDTO().setTotVal((String)totduty1.get(3));
					 String doccurr=(String)totduty1.get(1);
					 String revcurr=(String)totduty1.get(2);
					 String valcurr=(String)totduty1.get(3);
					 /*int docprv=Integer.parseInt((String)totduty2.get(0));
					 int revprv=Integer.parseInt((String)totduty2.get(1));
					 int valprv=Integer.parseInt((String)totduty2.get(2));*/
					double dc=0,rc=0,vc=0,dp=0,rp=0,vp=0,doccomp=0,revcomp=0,valcomp=0;
					String docprv=(String)totduty2.get(0);
					 String revprv=(String)totduty2.get(1);
					 String valprv=(String)totduty2.get(2);
					 if(doccurr==null){
						 dc=0;
					 }else{
						 dc=Double.parseDouble(doccurr);
					 }
					 if(revcurr==null){
						 rc=0;
					 } else{
						 
						 rc=Double.parseDouble(revcurr);
					 }
						 if(valcurr==null){
					 
						  vc=0;
					 }else{
						 vc=Double.parseDouble(valcurr);
					 }
						 
					 if(docprv==null){
						  dp=0;
					 }else{
						 dp=Double.parseDouble(docprv);
					 }
					 if(revprv==null){
						 rp=0;
					 } else{
						 rp=Double.parseDouble(revprv);
					 }
						 if(valprv==null){
					 
						 vp=0;
					 }else{
						 vp=Double.parseDouble(valprv);
					 }
					 if(dp!=0){
					 doccomp=Math.abs(((dp-dc)/dp)*100);
					 eForm.getReportDTO().setTotnoofDocComp(String.valueOf(doccomp).concat("%"));
					 }else{
						 eForm.getReportDTO().setTotnoofDocComp("-");
						 
					 }
					 if(rp!=0){
						 
						 revcomp=Math.abs(((rp-rc)/rp)*100);
						 eForm.getReportDTO().setTotRevComp(String.valueOf(revcomp).concat("%"));
						 }else{
							 eForm.getReportDTO().setTotRevComp("-");
						 }
					 if(vp!=0){
						 valcomp=Math.abs(((vp-vc)/vp)*100);
						 eForm.getReportDTO().setTotValComp(String.valueOf(valcomp).concat("%"));
						 }else{
							 eForm.getReportDTO().setTotValComp("-");
						 }
					
					
					 
				 }
				 
				 if(eForm.getReportDTO().getRadio().equalsIgnoreCase("deeds")){	
						System.out.println(eForm.getReportDTO().getDeedId());
						
						noofdoclist=reportBD.getdeedReport1(eForm.getReportDTO().getDeedId());
						MarketTrendReportDTO dto1=(MarketTrendReportDTO)noofdoclist.get(0);
					ArrayList al=new ArrayList();
					al.add(dto1);
						eForm.getReportDTO().setMarket23colList(al);
						// ArrayList totduty1=(ArrayList)noofdoclist.get(0);
						 eForm.getReportDTO().setDeedCount("1");
						 eForm.getReportDTO().setTotnoofDoc(dto1.getNoOfDoc());
						 eForm.getReportDTO().setTotRev(dto1.getRevenue());
						 eForm.getReportDTO().setTotVal(dto1.getValuation());
						
						 eForm.getReportDTO().setTotnoofDocComp(dto1.getNoOfDocComp());
						 eForm.getReportDTO().setTotRevComp(dto1.getRevenueComp());
						 eForm.getReportDTO().setTotValComp(dto1.getValuationComp());
					//	marketDeedList=reportBD.getdeedReport2(eForm.getReportDTO().getDeedId());
						//eForm.getReportDTO().setMarket45colList(marketDeedList);
				 }
				
				 forwardJsp = "markettrendreportview";
			 }
			 if(actionName!=null && actionName.equalsIgnoreCase("MarketTrendBack")){
					eForm.getReportDTO().setRadio("");
					eForm.getReportDTO().setFromYear("");
					eForm.getReportDTO().setToYear("");
					eForm.getReportDTO().setDistId("");
					eForm.getReportDTO().setTehisilId("");
					eForm.getReportDTO().setAreaTypeId("");
					eForm.getReportDTO().setSubAreaId("");
					eForm.getReportDTO().setWardId("");
					eForm.getReportDTO().setMohallaId("");
					 dto.setTehsiList(new ArrayList());
					 dto.setSubAreaTypeList(new ArrayList());
					 dto.setWardList(new ArrayList());
					 dto.setMohallaList(new ArrayList());
					 eForm.getReportDTO().setDeedId("");
				 actionName="";
				
				 forwardJsp = "marketTrendReport";
			 }
			 
			if(MarketTrendReportConstants.
					MARKET_TREND_POPUP_FORM.equals(formName)) {
				
				if(MarketTrendReportConstants.
						EXPORT_TO_PDF_ACTION.equals(actionID)) {
					
					ArrayList marketList = 
								(ArrayList) session.getAttribute(
										MarketTrendReportConstants.
										MARKET_TREND_LIST_SESSION);
					MarketTrendReportDTO dtoPDF = (MarketTrendReportDTO)
											session.getAttribute("Year");
					File file = reportPDF.createMarketTrendReport(marketList,
											dtoPDF.getFromYear(),
											dtoPDF.getToYear());
					logger.debug("File Size:-"+file.length());
					request.setAttribute(
						MarketTrendReportConstants.PDF_REQUEST,
						file);
					forwardJsp = 
						MarketTrendReportConstants.
						exportToPDF;
				}
				
			}
			eForm.setReportDTO(dto);
			session.setAttribute("dform", eForm);
		}
		 eForm.setActionName("");
		 return mapping.findForward(forwardJsp);
	}
}

