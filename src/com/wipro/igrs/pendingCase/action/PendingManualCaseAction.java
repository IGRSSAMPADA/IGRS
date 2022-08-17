package com.wipro.igrs.pendingCase.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.eclipse.jdt.internal.compiler.ast.CaseStatement;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caseMonitoring.bo.CaseMonHistoryBO;
import com.wipro.igrs.caseMonitoring.constant.CommonConstant;
import com.wipro.igrs.caseMonitoring.dto.CaseMonHistoryDTO;
import com.wipro.igrs.caseMonitoring.form.CaseMonHistoryForm;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.RegCommonDTO;
import com.wipro.igrs.newreginit.rule.RegInitRule;
import com.wipro.igrs.pendingCase.bo.PendingManualCaseBO;
import com.wipro.igrs.pendingCase.dto.PendingManualCaseDTO;
import com.wipro.igrs.pendingCase.form.PendingManualCaseForm;
import com.wipro.igrs.report.action.reportPdf.Report;
import com.wipro.igrs.report.bo.MISReportBO;
import com.wipro.igrs.report.common.MarketTrendReportConstants;
import com.wipro.igrs.report.dto.MISReportDTO;
import com.wipro.igrs.util.JrxmlExportUtility;


public class PendingManualCaseAction extends BaseAction{
	
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */

	
	/**
	 * @see forwardJsp is used for redirecting
	 */
	private String FORWARD_PAGE = "";
	/**
	 * @author Akansha
	 */
	private Logger logger = (Logger) Logger
	.getLogger(PendingManualCaseAction.class);

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @exception Exception
	 * @return ActionForward
	 */

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException, ServletException,
			Exception {
		
		
		PendingManualCaseBO caseBO= new PendingManualCaseBO();
		String userId = (String) session.getAttribute("UserId");
		ArrayList errorList1 = new ArrayList();
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		String userOffice = (String)session.getAttribute("loggedToOffice").toString();
		String language=(String)session.getAttribute("languageLocale");
		PendingManualCaseForm caseForm  =  (PendingManualCaseForm)form;
		//PendingManualCaseDTO caseDTO = new PendingManualCaseDTO();
		/*if(language.equalsIgnoreCase("en")){
			session.setAttribute("modName","Manual Cases");
			session.setAttribute("fnName","Enter Stamp/RRC Cases");
		}else{
			session.setAttribute("modName","मैनुअल मामले");
			session.setAttribute("fnName","टिकट / आरआरसी मामले दर्ज करें");
		}*/
		
		

		PendingManualCaseDTO caseDTO;
		FORWARD_PAGE ="";
		caseForm.setErrorFound("");
		if (caseForm.getCaseDTO() != null) {
			caseDTO = caseForm.getCaseDTO();

		} else {
			caseDTO = new PendingManualCaseDTO();
			caseDTO.setCaseStatusList(new ArrayList());
			caseDTO.setSearchList(new ArrayList());
			caseDTO.setRevList(new ArrayList());
			caseDTO.setSearchList(new ArrayList());
		}

		if(request.getParameter("fnName")!=null && "EnterStampRRCCases".equalsIgnoreCase(request.getParameter("fnName")))
		{       //caseForm = new PendingManualCaseForm();
			
		//	caseDTO = new PendingManualCaseDTO();
			caseDTO.setCaseType("");
			caseDTO.setBalanceAmt("");
			caseDTO.setCaseNumber("");
			caseDTO.setCaseStatus("");
			caseDTO.setCaseStatusId("");
			caseDTO.setCaseTxId("");
			caseDTO.setDeficitStampAudit("");
			caseDTO.setDeficitStampCollector("");
			caseDTO.setEstimatedDeffStampAudit("");
			caseDTO.setOrderNumber("");
			caseDTO.setOrderNumber("");
			caseDTO.setPaidAmt("");
			caseDTO.setPayableAmt("");
			caseDTO.setPaymentAmount("");
			caseDTO.setPaymentType("");
			caseDTO.setPraposedMktValueAudit("");
			caseDTO.setPraposedRegAudit("");
			caseDTO.setPraposedStampAudit("");
			caseDTO.setPraposedStampSubReg("");
			caseDTO.setRegFee("");
			caseDTO.setRemarks("");
			caseDTO.setRevHeadId("");
			caseDTO.setRrcCaseNumber("");
			caseDTO.setSectionCommon("");
			caseDTO.setSectionId("");
			caseDTO.setSectionType("");
			caseDTO.setStampAmt("");
			caseDTO.setStampCaseNumber("");
			caseDTO.setStampDuty("");
			caseDTO.setStampImpound("");
			caseDTO.setTotalAudit("");
			caseDTO.setTermsCond("");
			caseDTO.setPraposedMrktValSr("");
			caseForm.setErrorFound("");
			FORWARD_PAGE="createCase";
		}
		if(request.getParameter("fnName")!=null && "EditStampRRCCases".equalsIgnoreCase(request.getParameter("fnName")))
		{	
			caseForm.setRevHeadId("");
			caseForm.setCaseStatusId("");
			caseForm.setCaseDate("");
			caseForm.setSectionId("");
			caseForm.setPaymentType("");
			caseForm.setCaseType("");
			caseForm.setRevHeadType("");
			//caseForm.setCaseType("");
		
			caseForm.setRevHeadId("");
			caseForm.setRevHeadName("");
			caseForm.setSectionName("");
			caseForm.setErrorFound("");
			caseDTO.setRevList(caseBO.getRevenueHeadList(language));
			FORWARD_PAGE="searchCase";
			return mapping.findForward(FORWARD_PAGE);
		}
		if(request.getParameter("fnName")!=null && "ManualStampRRCcaseReport".equalsIgnoreCase(request.getParameter("fnName")))
		{
			caseForm.setErrorFound("");
			String distId = caseBO.getDistrictId(userOffice);
			String officeType = caseBO.getOfficeType(userOffice); // officeType = 2
			String officer = caseBO.getType(officeType);
			String zoneId = caseBO.getDIGZone(userOffice);
			if(officeType.equalsIgnoreCase("4")){
				caseForm.setDistrictList(caseBO.getDistDIGList(zoneId,language));
				
			}
			else{
			caseForm.setDistrictList(caseBO.getDistrictDetails(language,userOffice,officeType));
			
			}

			caseForm.setRevList(caseBO.getRevenueHeadList(language ));
			caseForm.setDistrictId("");
			caseForm.setCaseType("");
			caseForm.setRevHeadId("");
			caseForm.setSectionId("");
			caseForm.setCaseDate("");
			caseForm.setCaseStatusId("");
			caseForm.setPaymentType("");
			caseForm.setErrorFound("");
			caseForm.setDrOfficeIdSelected("");
			caseForm.setDurationFrom("");
			caseForm.setDurationTo("");
			caseForm.setDistSelected("0");
			FORWARD_PAGE="caseReportSearch";
		}
		
		
		if (request.getParameter("modName") != null) {
			if (request.getParameter("modName").equalsIgnoreCase(
					"dashboard")){
				caseForm.setErrorFound("");
				ArrayList pendingApplicationList = caseBO.getPendingCases(
						userId, caseForm, language,userOffice);
				if (pendingApplicationList.size() == 0)
					caseForm.setPendingApplicationList(null);
				else
					caseForm
							.setPendingApplicationList(pendingApplicationList);
				request.setAttribute("pendingApplicationList", caseForm
						.getPendingApplicationList());
				FORWARD_PAGE="dashboard";
				//caseForm.setCaseType("");
			
				
			}
		
	}
		if(caseForm!=null)
		{

		   
		
			caseBO=new PendingManualCaseBO();
			
			caseDTO.setLoggedInUser(userId);
			
			caseForm.setErrorFound("");
			caseDTO.setLoggedInUserOffice(userOffice);
			if(caseForm.getActionName()==null || caseForm.getActionName().isEmpty()){
			//	caseForm.setCaseDTO(new PendingManualCaseDTO());
			/*caseDTO.setCaseType("");
			caseDTO.setRevHeadId("");
			caseDTO.setSectionId("");
			caseDTO.setSectionType("");
			caseDTO.setRevHeadType("");
			caseDTO.setSectionCommon("");*/
				
				caseForm.setCaseDTO(new PendingManualCaseDTO());
				//caseForm = new PendingManualCaseForm();
			}
			
			if(caseForm.getActionName()!=null &&"setCaseType".equalsIgnoreCase(caseForm.getActionName()))
			{
				
				caseDTO.setCaseType("s");
				caseDTO.setRevHeadType("");
				caseDTO.setSectionCommon("");
				caseDTO.setRevHeadId("");
				caseDTO.setSectionId("");
				caseDTO.setSectionType("");
				caseForm.setCaseDate("");
				caseForm.setOrderDate("");
				caseForm.setPaymentDate("");
				caseForm.setErrorFound("");
				caseDTO.setRevList(caseBO.getRevenueHeadList(language ));
				
				FORWARD_PAGE="createCase";
				caseForm.setActionName("");
			}
			 if(caseForm.getActionName()!=null &&"setCaseType1".equalsIgnoreCase(caseForm.getActionName()))
			{
				
				 
				caseDTO.setCaseType("r");
				caseDTO.setRevHeadType("");
				caseDTO.setSectionCommon("");
				caseDTO.setRevHeadId("");
				caseDTO.setSectionId("");
				caseDTO.setSectionType("");
				caseForm.setCaseDate("");
				caseForm.setOrderDate("");
				caseForm.setPaymentDate("");
				caseForm.setErrorFound("");
				//caseDTO.setDivisionList(caseBO.getCaseDivisionList(language));
				//caseDTO.setCaseStatusList(caseBO.getCaseStatusList(language));
				caseDTO.setRevList(caseBO.getRevenueHeadList(language ));
				
				FORWARD_PAGE="createCase";
				caseForm.setActionName("");
			}
			if(caseForm.getActionName()!=null &&"GET_CASE_SECTIONS".equalsIgnoreCase(caseForm.getActionName()))
			{
				//caseDTO.setCaseType("s");
				caseDTO.setRevHeadType("rev");
				//caseDTO.setRevList(caseBO.getRevenueHeadList(language ));
				caseDTO.setRevHeadName(caseBO.getRevenueHeadName(caseDTO.getRevHeadId()));
				caseDTO.setSectionList(caseBO.getCaseSectionList(language,caseDTO.getRevHeadId()));
				caseDTO.setSectionCommon("");
				caseForm.setCaseDate("");
				caseForm.setOrderDate("");
				caseForm.setPaymentDate("");
				//caseDTO.setCaseStatusList(caseBO.getCaseStatusList(language));
				//caseForm.setUploadList(new ArrayList());
				//caseDTO.setUploadId(1);
				FORWARD_PAGE="createCase";
				caseForm.setErrorFound("");
				caseForm.setActionName("");
			}
			
			if(caseForm.getActionName()!=null &&"GET_CASE_SECTIONS_REPORTS".equalsIgnoreCase(caseForm.getActionName()))
			{
				//caseDTO.setCaseType("s");
				caseDTO.setRevHeadType("rev");
				//caseDTO.setRevList(caseBO.getRevenueHeadList(language ));
				caseDTO.setRevHeadName(caseBO.getRevenueHeadName(caseForm.getRevHeadId()));
				caseDTO.setSectionList(caseBO.getCaseSectionList(language,caseForm.getRevHeadId()));
				FORWARD_PAGE="caseReportSearch";
				caseForm.setErrorFound("");
				caseForm.setActionName("");
			}
			if(caseForm.getActionName()!=null &&"GET_CASE_SECTIONS_EDIT".equalsIgnoreCase(caseForm.getActionName()))
			{
				//caseDTO.setCaseType("s");
				caseForm.setRevHeadType("rev");
				caseForm.setCaseDate("");
				caseForm.setOrderDate("");
				caseForm.setPaymentDate("");
				//caseDTO.setRevList(caseBO.getRevenueHeadList(language ));
				caseForm.setRevHeadName(caseBO.getRevenueHeadName(caseForm.getRevHeadId()));
				caseDTO.setSectionList(caseBO.getCaseSectionList(language,caseForm.getRevHeadId()));
				caseForm.setErrorFound("");
				caseForm.setSectionCommon("");
			
				FORWARD_PAGE="searchCase";
				caseForm.setActionName("");
			}
			
			if(caseForm.getActionName()!=null &&"GET_CASE_SECTIONS_EDIT_REMOVE".equalsIgnoreCase(caseForm.getActionName()))
			{
				//caseDTO.setCaseType("s");
				caseForm.setRevHeadType("");
				caseForm.setCaseDate("");
				caseForm.setOrderDate("");
				caseForm.setPaymentDate("");
				//caseDTO.setRevList(caseBO.getRevenueHeadList(language ));
				caseForm.setRevHeadName(caseBO.getRevenueHeadName(caseForm.getRevHeadId()));
				caseDTO.setSectionList(caseBO.getCaseSectionList(language,caseForm.getRevHeadId()));
				caseForm.setSectionCommon("");
				caseForm.setErrorFound("");
				FORWARD_PAGE="searchCase";
				caseForm.setActionName("");
			}
		
			if(caseForm.getActionName()!=null &&"GET_FORM_FIELDS".equalsIgnoreCase(caseForm.getActionName()))
			{
				caseDTO.setCaseType("s");
				caseDTO.setRevHeadType("rev");
				caseDTO.setSectionName(caseBO.getSectionHeadName(caseDTO.getSectionId()));
				
				caseDTO.setSectionType("");
				
		
				caseDTO.setBalanceAmt("");
				caseDTO.setCaseNumber("");
				caseDTO.setCaseStatus("");
				caseDTO.setCaseStatusId("");
				caseDTO.setCaseTxId("");
				caseDTO.setDeficitStampAudit("");
				caseDTO.setDeficitStampCollector("");
				caseDTO.setEstimatedDeffStampAudit("");
				caseDTO.setOrderNumber("");
				caseDTO.setOrderNumber("");
				caseDTO.setPaidAmt("");
				caseDTO.setPayableAmt("");
				caseDTO.setPaymentAmount("");
				caseDTO.setPaymentType("");
				caseDTO.setPraposedMktValueAudit("");
				caseDTO.setPraposedRegAudit("");
				caseDTO.setPraposedStampAudit("");
				caseDTO.setPraposedStampSubReg("");
				caseDTO.setRegFee("");
				caseDTO.setRemarks("");
				caseDTO.setRrcCaseNumber("");
				caseDTO.setStampAmt("");
				caseDTO.setStampCaseNumber("");
				caseDTO.setStampDuty("");
				caseDTO.setStampImpound("");
				caseDTO.setTotalAudit("");
				caseDTO.setTermsCond("");
				caseDTO.setPraposedMrktValSr("");
				caseForm.setErrorFound("");
				if(caseDTO.getRevHeadId().equalsIgnoreCase("1") && caseDTO.getSectionId().equalsIgnoreCase("1")){
				caseDTO.setSectionType("1");
				}
				if(caseDTO.getRevHeadId().equalsIgnoreCase("2") && (caseDTO.getSectionId().equalsIgnoreCase("2") )){
				caseDTO.setSectionType("2");//47a1
				}
				if(caseDTO.getRevHeadId().equalsIgnoreCase("1") && caseDTO.getSectionId().equalsIgnoreCase("4")){
				caseDTO.setSectionType("3");
			    }
				if(caseDTO.getRevHeadId().equalsIgnoreCase("1") && caseDTO.getSectionId().equalsIgnoreCase("5")){
				caseDTO.setSectionType("4");
				 }
				if(caseDTO.getRevHeadId().equalsIgnoreCase("1") && caseDTO.getSectionId().equalsIgnoreCase("6")){
				caseDTO.setSectionType("5");
				  }
				
				if(caseDTO.getRevHeadId().equalsIgnoreCase("2") &&  caseDTO.getSectionId().equalsIgnoreCase("3")){
				caseDTO.setSectionType("6");//47a3
				}
				//caseDTO.setSectionList(caseBO.getCaseSectionList(language,caseDTO.getRevHeadId()));
				FORWARD_PAGE="createCase";
				caseForm.setActionName("");
			}
			if(caseForm.getActionName()!=null &&"GET_FORM_FIELDS_RRC_CASES".equalsIgnoreCase(caseForm.getActionName()))
			{
				caseDTO.setCaseType("r");
				caseDTO.setRevHeadType("rev");
				caseDTO.setSectionName(caseBO.getSectionHeadName(caseDTO.getSectionId()));
				caseDTO.setSectionCommon("0");//for rrc case
				caseDTO.setSectionType("0");
				
				caseDTO.setBalanceAmt("");
				caseDTO.setCaseNumber("");
				caseDTO.setCaseStatus("");
				caseDTO.setCaseStatusId("");
				caseDTO.setCaseTxId("");
				caseDTO.setDeficitStampAudit("");
				caseDTO.setDeficitStampCollector("");
				caseDTO.setEstimatedDeffStampAudit("");
				caseDTO.setOrderNumber("");
				caseDTO.setOrderNumber("");
				caseDTO.setPaidAmt("");
				caseDTO.setPayableAmt("");
				caseDTO.setPaymentAmount("");
				caseDTO.setPaymentType("");
				caseDTO.setPraposedMktValueAudit("");
				caseDTO.setPraposedRegAudit("");
				caseDTO.setPraposedStampAudit("");
				caseDTO.setPraposedStampSubReg("");
				caseDTO.setRegFee("");
				caseDTO.setRemarks("");
				caseDTO.setRrcCaseNumber("");
				caseDTO.setStampAmt("");
				caseDTO.setStampCaseNumber("");
				caseDTO.setStampDuty("");
				caseDTO.setStampImpound("");
				caseDTO.setTotalAudit("");
				caseDTO.setTermsCond("");
				caseDTO.setPraposedMrktValSr("");
				caseForm.setErrorFound("");
				//caseDTO.setSectionList(caseBO.getCaseSectionList(language,caseDTO.getRevHeadId()));
				FORWARD_PAGE="createCase";
				caseForm.setActionName("");
			}
		/*	if(caseForm.getActionName()!=null &&"Open".equalsIgnoreCase(caseForm.getActionName()))
			{
				caseDTO.setCaseType("s");
				caseDTO.setRevHeadType("rev");
				//caseDTO.setSectionType(caseDTO.getSectionId());
				caseDTO.setCaseStatusId(caseDTO.getCaseStatusId());
				caseForm.setOrderDate("");
				caseDTO.setOrderNumber("");
				caseDTO.setMrkValue("");
				caseDTO.setStampDuty("");
				caseDTO.setRegFee("");
				caseDTO.setPaymentAmount("");
				caseDTO.setPaymentType("");
				caseForm.setPaymentDate("");
				caseDTO.setRemarks("");
				//caseDTO.setSectionList(caseBO.getCaseSectionList(language,caseDTO.getRevHeadId()));
				FORWARD_PAGE="createCase";
				caseForm.setActionName("");
			}*/
			if(caseForm.getActionName()!=null &&("Close".equalsIgnoreCase(caseForm.getActionName()) || "Open".equalsIgnoreCase(caseForm.getActionName())))
			{
				caseDTO.setCaseType("s");
				caseDTO.setRevHeadType("rev");
				//caseDTO.setSectionType(caseDTO.getSectionId());
				caseDTO.setCaseStatusId(caseDTO.getCaseStatusId());
				caseForm.setOrderDate("");
				caseDTO.setOrderNumber("");
				caseDTO.setMrkValue("");
				caseDTO.setStampDuty("");
				caseDTO.setRegFee("");
				caseDTO.setPaymentAmount("");
				caseDTO.setPaymentType("");
				caseForm.setPaymentDate("");
				caseDTO.setRemarks("");
				//caseDTO.setSectionList(caseBO.getCaseSectionList(language,caseDTO.getRevHeadId()));
				FORWARD_PAGE="createCase";
				caseForm.setActionName("");
			}
			
			if(caseForm.getActionName()!=null &&("OpenEdit".equalsIgnoreCase(caseForm.getActionName())||"CloseEdit".equalsIgnoreCase(caseForm.getActionName())))
			{caseForm.setErrorFound("");
				caseDTO.setCaseType("s");
			//	caseDTO.setRevHeadType("rev");
				//caseDTO.setSectionType(caseDTO.getSectionId());
				//caseDTO.setCaseStatusId(caseDTO.getCaseStatusId());
				
				caseForm.setCaseStatus(caseForm.getCaseStatus());
			
			
				FORWARD_PAGE="editPage";
				caseForm.setActionName("");
			}
			/*if(caseForm.getActionName()!=null &&"CloseEdit".equalsIgnoreCase(caseForm.getActionName()))
			{caseForm.setErrorFound("");
				caseDTO.setCaseType("s");
			//	caseDTO.setRevHeadType("rev");
				//caseDTO.setSectionType(caseDTO.getSectionId());
				caseForm.setCaseStatus(caseForm.getCaseStatus());
				//caseForm.setOrderDate("");
				//caseForm.setOrderNumber("");
				//caseForm.setMrkValue("");
				//caseForm.setStampDuty("");
				//caseForm.setRegFee("");
				//caseForm.setPaymentAmount("");
				//caseForm.setPaymentType("");
				//caseForm.setPaymentDate("");
				//caseForm.setRemarks("");
				//caseDTO.setSectionList(caseBO.getCaseSectionList(language,caseDTO.getRevHeadId()));
				FORWARD_PAGE="editPage";
				caseForm.setActionName("");
			}*/
			if(caseForm.getActionName()!=null &&"save_details".equalsIgnoreCase(caseForm.getActionName()))
			{caseForm.setErrorFound("");
				boolean saveFlag = caseBO.saveCaseData(caseForm);
				
				if(saveFlag){
				FORWARD_PAGE="confirmationPage";
				}
				
				else{
					caseForm.setErrorFound("notSaved");
					FORWARD_PAGE="confirmationEdit";
					
				}
				caseForm.setActionName("");
			}
			
			if(caseForm.getActionName()!=null &&"finalPage".equalsIgnoreCase(caseForm.getActionName()))
			{
			//	boolean saveFlag = caseBO.saveCaseData(caseForm);
				caseForm.setErrorFound("");
				FORWARD_PAGE="createConfirmationPage";
				caseForm.setActionName("");
				
			}
				
				
				
			
			if(caseForm.getActionName()!=null &&"validate_confirm".equalsIgnoreCase(caseForm.getActionName()))
			{
			//	caseBO.saveCaseData(caseForm);
				caseForm.setErrorFound("");
				FORWARD_PAGE="confirmationEdit";
				caseForm.setActionName("");
			}
			
			if(caseForm.getActionName()!=null &&"validate_details".equalsIgnoreCase(caseForm.getActionName()))
			{
			//	caseBO.saveCaseData(caseForm);
				caseForm.setErrorFound("");
				FORWARD_PAGE="confirmationEdit";
				caseForm.setActionName("");
			}
			
			if(caseForm.getActionName()!=null &&"edit_details".equalsIgnoreCase(caseForm.getActionName()))
			{
			//	caseBO.saveCaseData(caseForm);
				caseForm.setErrorFound("");
				caseForm.setCaseDetails("2");
				caseForm.setValidateEdit("0");
              caseBO.getEditDetailsDisplay(caseForm.getCaseTxId(),caseForm,language );
 				
              
             
 				if(caseForm.getCaseTypeEdit().equalsIgnoreCase("s")){
 					
 					caseForm.setSectionCommon("1");
 					 if(caseForm.getCaseStatus().equalsIgnoreCase("open")){
 		            	  caseForm.setPaymentRemarks("");
 							
 						}
 		              if(caseForm.getCaseStatus().equalsIgnoreCase("close")){
 							
 		            	  caseForm.setRemarks("");
 						}
 				}
 				else if(caseForm.getCaseTypeEdit().equalsIgnoreCase("r")){
 					   
 					caseForm.setSectionCommon("0");
 					caseForm.setSectionType("");
 				}
				FORWARD_PAGE="editPage";
				caseForm.setActionName("");
			}
			
			if(caseForm.getActionName()!=null &&"save_edit_details".equalsIgnoreCase(caseForm.getActionName()))
			{caseForm.setErrorFound("");
				caseForm.setCaseDetails("2");

                 caseForm.setValidateEdit("1");
                 
                // caseForm.setCaseDetails("1");
 				
 				
			FORWARD_PAGE="editPage";
			caseForm.setActionName("");
			}
			
			if(caseForm.getActionName()!=null &&"save_edit_details_confirm".equalsIgnoreCase(caseForm.getActionName()))
			{caseForm.setErrorFound("");
		    	boolean flag =	caseBO.saveEditCaseData(caseForm);
		    	
				if(flag){
			     caseForm.setCaseDetails("1");
				
				caseBO.getEditDetails(caseForm.getCaseTxId(),caseForm,language );
				
				if(caseForm.getCaseTypeEdit().equalsIgnoreCase("s")){
					
					caseForm.setSectionCommon("1");
				}
				else if(caseForm.getCaseTypeEdit().equalsIgnoreCase("r")){
					
					caseForm.setSectionCommon("0");
					caseForm.setSectionType("");
				}
				caseForm.setButtonDispay("1");
				
				}
				
				else{
					//String errorlist = "Details Could not be edited. Please Try again";
					caseForm.setErrorFound("error");
					caseForm.setCaseDetails("2");
				}
				FORWARD_PAGE="editPage";
				caseForm.setActionName("");
			}
			if(caseForm.getActionName()!=null &&"redirect_dashboard".equalsIgnoreCase(caseForm.getActionName()))
			{caseForm.setErrorFound("");
				ArrayList pendingApplicationList = caseBO.getPendingCases(
						userId, caseForm, language, userOffice);   
				if (pendingApplicationList.size() == 0)
					caseForm.setPendingApplicationList(null);
				else
					caseForm
							.setPendingApplicationList(pendingApplicationList);
				request.setAttribute("pendingApplicationList", caseForm
						.getPendingApplicationList());
				caseForm.setActionName("");
				caseForm.setLanguage(language);
				FORWARD_PAGE="dashboard";
			
			}
			if(caseForm.getActionName()!=null &&"resetForm".equalsIgnoreCase(caseForm.getActionName()))
			{
			//	caseBO.saveCaseData(caseForm);
				//FORWARD_PAGE="confirmationEdit";
				System.out.println(FORWARD_PAGE+"     FORWARD_PAGE"  );
				caseForm.setCaseDate("");
				caseForm.setPaymentType("");
				caseForm.setCaseStatusId("");
				caseForm.setRevHeadId("");
				caseForm.setCaseType("");
				caseForm.setSectionId("");
				caseForm.setRevHeadType("");
				caseForm.setDistrictId("");
				caseForm.setErrorFound("");
				caseForm.setDurationFrom("");
				caseForm.setDurationTo("");
				caseForm.setDrOfficeIdSelected("");
				caseForm.setDistSelected("0");
				caseForm.setActionName("");
				caseForm.setCaseDTO(new PendingManualCaseDTO());
				caseForm = new PendingManualCaseForm();
				
				FORWARD_PAGE="createCase";
				
			}
			if(caseForm.getActionName()!=null &&"resetFormSearch".equalsIgnoreCase(caseForm.getActionName()))
			{
			//	caseBO.saveCaseData(caseForm);
				//FORWARD_PAGE="confirmationEdit";
				System.out.println(FORWARD_PAGE+"     FORWARD_PAGE"  );
				caseForm.setCaseDate("");
				caseForm.setPaymentType("");
				caseForm.setCaseStatusId("");
				caseForm.setRevHeadId("");
				caseForm.setCaseType("");
				caseForm.setSectionId("");
				caseForm.setRevHeadType("");
				caseForm.setDistrictId("");
				caseForm.setErrorFound("");
				caseForm.setDurationFrom("");
				caseForm.setDurationTo("");
				caseForm.setDrOfficeIdSelected("");
				caseForm.setDistSelected("0");
				caseForm.setActionName("");
				caseForm.setCaseDTO(new PendingManualCaseDTO());
				caseForm = new PendingManualCaseForm();
				
				FORWARD_PAGE="caseReportSearch";
				
			}
			if(caseForm.getActionName()!=null &&"resetFormEdit".equalsIgnoreCase(caseForm.getActionName()))
			{
			//	caseBO.saveCaseData(caseForm);
				//FORWARD_PAGE="confirmationEdit";
				
				caseForm.setCaseDate("");
				caseForm.setPaymentType("");
				caseForm.setCaseStatusId("");
				caseForm.setRevHeadId("");
				caseForm.setCaseType("");
				caseForm.setSectionId("");
				caseForm.setRevHeadType("");
				caseForm.setErrorFound("");
				caseForm.setDurationFrom("");
				caseForm.setDurationTo("");
				caseForm.setActionName("");
				caseDTO.setRevList(caseBO.getRevenueHeadList(language));
				
				FORWARD_PAGE="searchCase";
			}
			if(caseForm.getActionName()!=null &&"GET_DR_OFFICE_NAMES".equalsIgnoreCase(caseForm.getActionName()))
			{   caseForm.setDistSelected("1");
				String selectedDistrict = caseForm.getDistrictId();
				caseForm.setErrorFound("");
				//caseForm.setDRofficeList(caseBO.getDRofficeName(language,selectedDistrict));
				caseForm.setDrOfficeNameList(caseBO.getDRofficeName(language,selectedDistrict));
				FORWARD_PAGE="caseReportSearch";
				caseForm.setActionName("");
			}
			if(caseForm.getActionName()!=null &&"cleanError".equalsIgnoreCase(caseForm.getActionName()))
			{   caseForm.setErrorFound("");
				//String selectedDistrict = caseForm.getDistrictId();
				//caseForm.setDRofficeList(caseBO.getDRofficeName(language,selectedDistrict));
				//caseForm.setDrOfficeNameList(caseBO.getDRofficeName(language,selectedDistrict));
				FORWARD_PAGE="caseReportSearch";
				caseForm.setActionName("");
			}
			
			if ("EXPORT_COMMON_REPORT_TO_XLS".equalsIgnoreCase(caseForm
					.getActionName())) {
				
				caseForm.setErrorFound("");
				logger.debug("EXPORT_COMMON_REPORT_TO_XLS entered");
				JrxmlExportUtility export = new JrxmlExportUtility();
				String jrxmlName = "";
				PropertiesFileReader prop = PropertiesFileReader
						.getInstance("resources.igrs");
				String selectedDistrict = caseForm.getDistrictId();
				String selectedRevId= caseForm.getRevHeadId();
				String selectedSectionId = caseForm.getSectionId();
				String selectedCaseType = caseForm.getCaseType();
			//	String selectedCaseDate = caseForm.getCaseDate();
				String selectedCaseStatus = caseForm.getCaseStatusId();
				String selectedPaymentType = caseForm.getPaymentType();
				String durationFrom = caseForm.getDurationFrom();
				String durationTo = caseForm.getDurationTo();
				String selectedDRofficeId = caseForm.getDrOfficeIdSelected();
				
				caseForm.setCaseStatusId(selectedCaseStatus);
				caseForm.setPaymentType(selectedPaymentType);
				if(selectedCaseStatus.equalsIgnoreCase("select")){
					
					selectedCaseStatus ="";
				}
                  if(selectedPaymentType.equalsIgnoreCase("select")){
					
                	  selectedPaymentType ="";
				}
				
	boolean searchFlag = caseBO.getSearchReportResult(selectedDistrict,selectedRevId,selectedSectionId,selectedCaseType,durationFrom,durationTo,selectedCaseStatus,selectedPaymentType,selectedDRofficeId);
				
	if(searchFlag){
	
		caseForm.setErrorFound("");
				if(selectedCaseType.equalsIgnoreCase("s")){
					
					if(selectedSectionId.equalsIgnoreCase("1")){
						jrxmlName = "Report_33.jrxml";
    					caseForm.setReportName("Report_33");		
					}
					if(selectedSectionId.equalsIgnoreCase("2")){
						
						jrxmlName = "Report_47A1.jrxml";
						caseForm.setReportName("Report_47A1");		
					}
					if(selectedSectionId.equalsIgnoreCase("3")){
						
						jrxmlName = "Report_47A3.jrxml";
						caseForm.setReportName("Report_47A3");	
					}
                    if(selectedSectionId.equalsIgnoreCase("4")){
						
                    	//jrxmlName = "Report_40.jrxml";
    					//caseForm.setReportName("Report_40");	
					}
					if(selectedSectionId.equalsIgnoreCase("5")){
						
						jrxmlName = "Report_48B.jrxml";
						caseForm.setReportName("Report_48B");	
					}
					if(selectedSectionId.equalsIgnoreCase("6")){
						
						jrxmlName = "section_41B.jrxml";
						caseForm.setReportName("section_41B");	
					}
					
					
					
				}
				else if(selectedCaseType.equalsIgnoreCase("r")){
					
					jrxmlName = "RRC.jrxml";
					caseForm.setReportName("RRC");
					
					
					
				}
				
			
				String path = prop.getValue("pdf.location");
				String reportPath = path.concat(jrxmlName);
				String reportName = caseForm.getReportName();
				HashMap jasperMap = new HashMap();
			
				jasperMap.put("lang", language);
				jasperMap.put("district_Id", selectedDistrict);
				jasperMap.put("revenue_head", selectedRevId);
				jasperMap.put("section", selectedSectionId);
				jasperMap.put("fromDate", durationFrom);
				jasperMap.put("toDate", durationTo);
				jasperMap.put("status", selectedCaseStatus);
				jasperMap.put("Payment", selectedPaymentType);
				jasperMap.put("officeID", selectedDRofficeId);
				
				//jasperMap.put("lang", language);
				
			////	jasperMap.put("distName", districtName);
				
					export.generateXLS(jasperMap, reportPath, reportName,
							response);
					caseForm.setErrorFound("");	
					FORWARD_PAGE="caseReportSearch";
					caseForm.setActionName("");
			}
	
           	else{
	     	caseForm.setErrorFound("reportNotAvailable");
		
	        }
	
				FORWARD_PAGE="caseReportSearch";
				caseForm.setActionName("");
				return mapping.findForward(FORWARD_PAGE);
//				logger.debug("EXPORT_COMMON_REPORT_TO_XLS exited" + reportName);
//				forwardJsp = MarketTrendReportConstants.COMMON_REPORT_SEARCH;
			}
			
			if (request.getParameter("hdnApplicationId") != null && ("displayCaseStamp".equalsIgnoreCase(request.getParameter("label")) ||"displayCaseRrc".equalsIgnoreCase(request.getParameter("label")))) {
				
				caseForm.setErrorFound("");
				caseForm.setCaseDetails("1");
				try{
				caseForm.setHiddenCaseNumber(request
							.getParameter("hdnApplicationId"));

					String hiddenCaseNumber = request
					.getParameter("hdnApplicationId").toString();
					
				//	caseForm.setMapCaseDisp(caseBO.getEditDetails(hiddenCaseNumber));
					//caseForm.setCaseDTO(null);
					caseBO.getEditDetails(hiddenCaseNumber,caseForm,language );
					//caseForm.setCaseType("stamp case");
					
					if("displayCaseStamp".equalsIgnoreCase(request.getParameter("label"))){
						
						caseForm.setSectionCommon("1");
						
					}
					else if("displayCaseRrc".equalsIgnoreCase(request.getParameter("label"))){
						caseForm.setSectionCommon("0");
						caseForm.setSectionType("");
						
					}
					caseForm.setButtonDispay("0");
				
				}
				
				catch (Exception e) {

					logger.debug(e);
					e.printStackTrace();
					logger.debug(e.getMessage(), e);
					FORWARD_PAGE="editPage";
					return mapping.findForward(FORWARD_PAGE);

				}
				
				
				FORWARD_PAGE="editPage";
				
				}
			
		}
		

		
		return mapping.findForward(FORWARD_PAGE);
	}


}
