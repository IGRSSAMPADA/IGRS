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
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/


package com.wipro.igrs.auditinspection.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.auditinspection.bd.AGMPReportDetailsBD;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.form.AGMPReportDetailsForm;
import com.wipro.igrs.baseaction.action.BaseAction;





public class AGMPAuditViewAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(AGMPAuditViewAction.class);
	private String forwardPage = "";
	private String reqParam = "";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		try{
			if(form != null){
				AGMPReportDetailsForm eForm = (AGMPReportDetailsForm) form;
				AGMPReportDetailsBD bd = new AGMPReportDetailsBD();				
				AGMPReportDetailsDTO dto = eForm.getAgmpDTO();
				String userId = (String) session.getAttribute("UserId");
				String officeId = (String) session.getAttribute("loggedToOffice");
				eForm.setuId(userId);
				String nextPage = "";
				String formName = eForm.getFormName();
				String actionName=eForm.getActionName();
				// eForm.getRmDto().setViewResultList(null);
				// String actionName = eForm.getActionName();
				logger.debug("inside action class, Action name is......."+ eForm.getActionName());
				
				//added by shruti
		         String language=(String)session.getAttribute("languageLocale");
		         eForm.setLanguage(language);
		         //end
		         
				String label = null;
				String pageName = null;
				label = (String) request.getParameter("label");
				pageName = (String) request.getParameter("pageName");
				if (pageName == null)
				{
					pageName = "";
				}
				
				if(label !=null && label.equalsIgnoreCase("agmpAV")){
				eForm.getAgmpDTO().setOfficeTypeId("");
				eForm.getAgmpDTO().setOffcId("");
				eForm.getAgmpDTO().setAuditFromMonth("");
				eForm.getAgmpDTO().setAuditFromYear("");
				eForm.getAgmpDTO().setAuditToMonth("");
				eForm.getAgmpDTO().setAuditToYear("");
				eForm.getAgmpDTO().setReportType("");
				eForm.getAgmpDTO().setReportStatus("");
				eForm.getAgmpDTO().setParaId("");
				eForm.getAgmpDTO().setParaStatusId("");
				ArrayList offcList=bd.getOfficeType(language);
				ArrayList paraTypeList=bd.getParaType();
				ArrayList paraStatusList=bd.getParaStatus();
				eForm.getAgmpDTO().setOffcList(offcList);
				eForm.getAgmpDTO().setParaList(paraTypeList);
				eForm.getAgmpDTO().setParaStatusList(paraStatusList);
				eForm.getAgmpDTO().setAgmpSearchList(null);
				eForm.getAgmpDTO().setRecFlag("");
				eForm.getAgmpDTO().setAction("");
				session.removeAttribute("paraSearchList");
				eForm.setActionName("");
					forwardPage="splicence";
				}
				
				
				if(pageName != null && pageName.equalsIgnoreCase("offcName"))
				{	
					//logger.info(eForm.getAgmpDTO().getOfficeTypeId());
					if(eForm.getAgmpDTO().getOffcId()!=null){
						ArrayList offcList = bd.getOfficeTypeList(eForm.getAgmpDTO().getOfficeTypeId(),officeId,language);
					if(eForm.getAgmpDTO().getOfficeTypeId().equalsIgnoreCase("1")){
						eForm.getAgmpDTO().setHOList(offcList);
					}
					if(eForm.getAgmpDTO().getOfficeTypeId().equalsIgnoreCase("2")){
						eForm.getAgmpDTO().setDROList(offcList);

							}
					if(eForm.getAgmpDTO().getOfficeTypeId().equalsIgnoreCase("3")){
						eForm.getAgmpDTO().setSroList(offcList);

					}
			
					eForm.getAgmpDTO().setOfficeTypeId(eForm.getAgmpDTO().getOfficeTypeId());
			
					}

					
					/*ArrayList dist = bd.getDistrict();
					ArrayList areaList = bd.getAreaType();
					ArrayList sroList=new ArrayList();
					if (dform.getDsearchdto().getDistId() != null) 
					{
					sroList=bd.getSroList(dform.getDsearchdto().getDistId());
					}
					dsdto.setDistList(dist);
					dsdto.setSroList(sroList);
					dsdto.setAreaList(areaList);
					dform.setDsearchdto(dsdto);*/
					forwardPage = "splicence";
				}
				if(request.getParameter("paraTitle")!=null)
				{
					String paraID = (String)request.getParameter("paraTxnId");
					
					eForm.getAgmpDTO().setParaListView(bd.getObjectionDetails(paraID));
					
					forwardPage="objView";
					
				}
				 if(request.getParameter("txnid")!=null){
					 String reportID = (String)request.getParameter("txnid");
					eForm.getAgmpDTO().setReport(reportID);
					ArrayList paraSearchList = new ArrayList();
					ArrayList al=bd.fetchSearchResult(reportID);
					ArrayList al1=bd.fetchParaResult(reportID);
					 AGMPReportDetailsDTO dto1=new AGMPReportDetailsDTO();
					 dto1=(AGMPReportDetailsDTO)al.get(0);
					 for(int i=0;i<al1.size();i++)
					 {	  AGMPReportDetailsDTO dto2=new AGMPReportDetailsDTO();
						 
					 	dto2=(AGMPReportDetailsDTO)al1.get(i);						
						
					 	eForm.getAgmpDTO().setParaSearchList(dto2.getParaSearchList());
					 	paraSearchList.add(dto2);
					 }
						eForm.getAgmpDTO().setParaSearchList(paraSearchList);
					 
					 eForm.getAgmpDTO().setAuditDate(dto1.getAuditDate().substring(0, 10));
					 eForm.getAgmpDTO().setAudEntryDate(dto1.getAudEntryDate().substring(0, 10));
					 if(dto1.getReportStatus().equalsIgnoreCase("O")||dto1.getReportStatus().equalsIgnoreCase("Open")){
						 eForm.getAgmpDTO().setReportStatus("Open");
					 }
					 else{
						 eForm.getAgmpDTO().setReportStatus(dto1.getReportStatus());
					 }
					 
					 eForm.getAgmpDTO().setTxnId(reportID);
					 eForm.getAgmpDTO().setAuditType(dto1.getAuditType());
					 eForm.getAgmpDTO().setComplianceFlag(dto1.getComplianceFlag());
					 eForm.getAgmpDTO().setSrCompilance(dto1.getSrCompilance());
					 eForm.getAgmpDTO().setDrComplinace(dto1.getDrComplinace());
					 eForm.getAgmpDTO().setDigCompliance(dto1.getDigCompliance());
					 eForm.getAgmpDTO().setIgCompliance(dto1.getIgCompliance());
					 session.setAttribute("paraSearchList", paraSearchList);
					 eForm.setActionName("");
				 forwardPage = "agmpSearchView";
				 }
				 if(actionName!=null && actionName.equalsIgnoreCase("backTrace")){

					 
					 eForm.setActionName("");
					 forwardPage = "agmpSearchView";
					 
				 }
				 if(actionName!=null && actionName.equalsIgnoreCase("AGMPSearchAction")){
					
					 
						 
					 ArrayList agmpList=bd.getSearchResult(eForm.getAgmpDTO());
					 if(agmpList.size()==0||agmpList==null){
						 eForm.getAgmpDTO().setRecFlag("norecord"); 
					 }
					 else{
						 eForm.getAgmpDTO().setRecFlag("");
					 }
					 logger.info("Size of the array is:::::::"+agmpList.size());
					 eForm.getAgmpDTO().setAgmpSearchList(agmpList);
					 request.setAttribute("agmpList", agmpList);
					 eForm.setActionName("");
					 forwardPage = "splicence";
					 
					
				 }
				 
				 if(actionName!=null && actionName.equalsIgnoreCase("AGMPResetAction")){
					 eForm.getAgmpDTO().setAgmpSearchList(null);
					 eForm.getAgmpDTO().setAudEntryDate("");
					 eForm.getAgmpDTO().setOffcId("");
					 eForm.getAgmpDTO().setReportType("");
					 eForm.getAgmpDTO().setRecFlag("");
					 eForm.getAgmpDTO().setOfficeTypeId("");
					 eForm.getAgmpDTO().setAuditType("");
					 eForm.getAgmpDTO().setAuditFromYear("");
					 eForm.getAgmpDTO().setAuditFromMonth("");
					 eForm.getAgmpDTO().setAuditToMonth("");
					 eForm.getAgmpDTO().setAuditToYear("");
					 eForm.getAgmpDTO().setParaId("");
					 eForm.getAgmpDTO().setReportStatus("");
					 eForm.getAgmpDTO().setParaStatusId("");
					 forwardPage = "splicence";
					 
				 }
				 if(actionName!=null && actionName.equalsIgnoreCase("InternalResetAction")){
					 eForm.getAgmpDTO().setAgmpSearchList(null);
					 eForm.getAgmpDTO().setAudEntryDate("");
					 eForm.getAgmpDTO().setOffcId("");
					 eForm.getAgmpDTO().setReportType("");
					 eForm.getAgmpDTO().setRecFlag("");
					 eForm.getAgmpDTO().setOfficeTypeId("");
					 eForm.getAgmpDTO().setAuditType("");
					 eForm.getAgmpDTO().setAuditFromYear("");
					 eForm.getAgmpDTO().setAuditFromMonth("");
					 eForm.getAgmpDTO().setAuditToMonth("");
					 eForm.getAgmpDTO().setAuditToYear("");
					 eForm.getAgmpDTO().setParaId("");
					 eForm.getAgmpDTO().setReportStatus("");
					 eForm.getAgmpDTO().setParaStatusId("");
					 forwardPage = "internalSearchAV";
					 
				 }
				  
				 if(label !=null && label.equalsIgnoreCase("paging")){
						 
					 String reportID=eForm.getAgmpDTO().getReport();
						ArrayList paraSearchList = new ArrayList();
					 ArrayList al1=bd.fetchParaResult(reportID);
					 for(int i=0;i<al1.size();i++)
					 {
					    AGMPReportDetailsDTO dto2=new AGMPReportDetailsDTO();
					 	dto2=(AGMPReportDetailsDTO)al1.get(i);						
						
					 	eForm.getAgmpDTO().setParaSearchList(dto2.getParaSearchList());
					 	paraSearchList.add(dto2);
					 }
						eForm.getAgmpDTO().setParaSearchList(paraSearchList);
						 request.setAttribute("paraSearchList", paraSearchList);
						 forwardPage = "agmpSearchView";
				 }
				 
				 if(label !=null && label.equalsIgnoreCase("internalAV")){
						eForm.getAgmpDTO().setOfficeTypeId("");
						eForm.getAgmpDTO().setOffcId("");
						eForm.getAgmpDTO().setAuditFromMonth("");
						eForm.getAgmpDTO().setAuditFromYear("");
						eForm.getAgmpDTO().setAuditToMonth("");
						eForm.getAgmpDTO().setAuditToYear("");
						eForm.getAgmpDTO().setReportType("");
						eForm.getAgmpDTO().setReportStatus("");
						eForm.getAgmpDTO().setParaId("");
						eForm.getAgmpDTO().setParaStatusId("");
						 eForm.getAgmpDTO().setRecFlag("");
						 eForm.getAgmpDTO().setAgmpSearchList(null);
						ArrayList offcList=bd.getOfficeType(language);
						ArrayList paraTypeList=bd.getParaType();
						ArrayList paraStatusList=bd.getParaStatus();
						eForm.getAgmpDTO().setOffcList(offcList);
						eForm.getAgmpDTO().setParaList(paraTypeList);
						eForm.getAgmpDTO().setParaStatusList(paraStatusList);
						eForm.setActionName("");
							forwardPage="internalSearchAV";
						}
				 if(pageName != null && pageName.equalsIgnoreCase("offcNameInternal"))
					{	
						//logger.info(eForm.getAgmpDTO().getOfficeTypeId());
						if(eForm.getAgmpDTO().getOffcId()!=null){
							ArrayList offcList = bd.getOfficeTypeList(eForm.getAgmpDTO().getOfficeTypeId(),officeId,language);
						if(eForm.getAgmpDTO().getOfficeTypeId().equalsIgnoreCase("1")){
							eForm.getAgmpDTO().setHOList(offcList);
						}
						if(eForm.getAgmpDTO().getOfficeTypeId().equalsIgnoreCase("2")){
							eForm.getAgmpDTO().setDROList(offcList);

								}
						if(eForm.getAgmpDTO().getOfficeTypeId().equalsIgnoreCase("3")){
							eForm.getAgmpDTO().setSroList(offcList);

						}
				
						eForm.getAgmpDTO().setOfficeTypeId(eForm.getAgmpDTO().getOfficeTypeId());
				
						}

						forwardPage = "internalSearchAV";
					}
				 if(request.getParameter("txninternalid")!=null){
					 String reportID = (String)request.getParameter("txninternalid");
						eForm.getAgmpDTO().setReport(reportID);
					ArrayList paraSearchList = new ArrayList();
					ArrayList al=bd.fetchSearchResult(reportID);
					ArrayList al1=bd.fetchParaResult(reportID);
					 AGMPReportDetailsDTO dto1=new AGMPReportDetailsDTO();
					 dto1=(AGMPReportDetailsDTO)al.get(0);
					 for(int i=0;i<al1.size();i++)
					 {	  AGMPReportDetailsDTO dto2=new AGMPReportDetailsDTO();
						 
					 	dto2=(AGMPReportDetailsDTO)al1.get(i);						
						
					 	eForm.getAgmpDTO().setParaSearchList(dto2.getParaSearchList());
					 	paraSearchList.add(dto2);
					 }
						eForm.getAgmpDTO().setParaSearchList(paraSearchList);
					 
					 eForm.getAgmpDTO().setAuditDate(dto1.getAuditDate().substring(0, 10));
					 eForm.getAgmpDTO().setAudEntryDate(dto1.getAudEntryDate().substring(0, 10));
					 if(dto1.getReportStatus().equalsIgnoreCase("O")||dto1.getReportStatus().equalsIgnoreCase("Open")){
						 eForm.getAgmpDTO().setReportStatus("Open");
					 }
					 else{
						 eForm.getAgmpDTO().setReportStatus(dto1.getReportStatus());
					 }
					 
					 eForm.getAgmpDTO().setTxnId(reportID);
					 eForm.getAgmpDTO().setAuditType(dto1.getAuditType());
					 request.setAttribute("paraSearchList", paraSearchList);
					 eForm.setActionName("");
				 forwardPage = "internalSearchView";
				 }
				 
				 if(actionName!=null && actionName.equalsIgnoreCase("InternalSearchAction")){
					
				
					 ArrayList agmpList=bd.getSearchInternalResult(eForm.getAgmpDTO());
					 if(agmpList.size()==0||agmpList==null){
						 eForm.getAgmpDTO().setRecFlag("norecord"); 
					 }
					 else{
						 eForm.getAgmpDTO().setRecFlag("");
					 }
					 logger.info("Size of the array is:::::::"+agmpList.size());
					 eForm.getAgmpDTO().setAgmpSearchList(agmpList);
					 request.setAttribute("agmpList", agmpList);
					 eForm.setActionName("");
					 forwardPage = "internalSearchAV";
		
					
				 }
				 
				 if(label !=null && label.equalsIgnoreCase("pagingInternal")){
					 
					 String reportID=eForm.getAgmpDTO().getReport();
						ArrayList paraSearchList = new ArrayList();
					 ArrayList al1=bd.fetchParaResult(reportID);
					 for(int i=0;i<al1.size();i++)
					 {
					    AGMPReportDetailsDTO dto2=new AGMPReportDetailsDTO();
					 	dto2=(AGMPReportDetailsDTO)al1.get(i);						
						
					 	eForm.getAgmpDTO().setParaSearchList(dto2.getParaSearchList());
					 	paraSearchList.add(dto2);
					 }
						eForm.getAgmpDTO().setParaSearchList(paraSearchList);
						 request.setAttribute("paraSearchList", paraSearchList);
						 forwardPage = "internalSearchView";
				 }
				
				

			}
			
			
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("Returnign forwardPage : " + forwardPage);
		return mapping.findForward(forwardPage);
	}

}
