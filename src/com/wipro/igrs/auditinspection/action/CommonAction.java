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


import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.auditinspection.bd.AGMPReportDetailsBD;
import com.wipro.igrs.auditinspection.bd.SROReportDetailsBD;
import com.wipro.igrs.auditinspection.constant.CommonConstant;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.CaseDetails;
import com.wipro.igrs.auditinspection.dto.CaseIdOtherSearch;
import com.wipro.igrs.auditinspection.dto.CaseIdSearch;
import com.wipro.igrs.auditinspection.dto.DocDetails;
import com.wipro.igrs.auditinspection.dto.PartyDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PropertyDetailsDTO;
import com.wipro.igrs.auditinspection.dto.RegIdOtherSearch;
import com.wipro.igrs.auditinspection.dto.RegIdSearch;
import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.auditinspection.form.AGMPReportDetailsForm;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.revenueManagement.form.RevMgtForm;
import com.wipro.igrs.suppleDoc.bd.SuppleDocBD;
import com.wipro.igrs.suppleDoc.dto.SuppleDocDTO;



public class CommonAction extends BaseAction {

	 Logger logger = (Logger) Logger.getLogger(CommonAction.class);
	 AGMPReportDetailsForm agmpreportdetailform=null;
	 AGMPReportDetailsDTO agmpreportdetailsdto=null;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {


		
	
		
		
		CommonConstant auditConstant=new CommonConstant();
		
		ArrayList errorList = new ArrayList();
		try
		{
			AGMPReportDetailsBD agmpbd = new AGMPReportDetailsBD();
			AuditInspectionRule rule = null;
			
			if ("request".equals(mapping.getScope()))
			{
				session.setAttribute(mapping.getAttribute(), form);
			}
			
			agmpreportdetailform = (AGMPReportDetailsForm) form;
	         String language=(String)session.getAttribute("languageLocale");
	         agmpreportdetailform.setLanguage(language);
	         
	         //on click of document details
			 if(request.getParameter("edit")!=null)
             {
                     String edit=request.getParameter("edit");
                     if(edit.equalsIgnoreCase("true"))
                     {
                  	   ArrayList list=agmpbd.getDocDtls(agmpreportdetailform.getAgmpreportdetailsdto().getTxnId());
                         logger.debug("IN ACTION>>>>"+list.size());
                         
                  	   ArrayList selectedItems=new ArrayList();
                         for(int i=0;i<list.size();i++)
                         {
                        	 selectedItems.add(list.get(i));
                        	 logger.debug("SELCTED ITEM LIST SIZE>>>>"+selectedItems.size());
                         }  
                         agmpreportdetailsdto.setSelectedItems(selectedItems);
                         agmpreportdetailform.setAgmpreportdetailsdto(agmpreportdetailsdto);
                         return mapping.findForward("editDoc");                            
                     }
             }
			 
			 if(request.getParameter("paraTxnId")!=null)
			 {
				 String para_txn_id=request.getParameter("paraTxnId");
				 AGMPReportDetailsDTO agmpreportdetailsdto2=agmpbd.getParaDtls(para_txn_id);
				 logger.info(">>>>para_txn_id status for bringing para status list"+(String)request.getParameter("status"));
				 ArrayList paraStatusList=agmpbd.getParaStatusList((String)request.getParameter("status"));
				 agmpreportdetailsdto2.setParaStatusList(paraStatusList);
				 //added for persistence
				 agmpreportdetailsdto2.setParaId(para_txn_id);
				 agmpreportdetailsdto2.setParaStatus((String)request.getParameter("status"));
				 //agmpreportdetailsdto2.setFormName("EditDocDetails");
				 //agmpreportdetailsdto2.setAction("ExistingAuditAction");
				 //end
				 ArrayList list=agmpbd.getObjectionDtls(para_txn_id);
				 agmpreportdetailsdto2.setObjectionList(list);
				 //agmpreportdetailsdto2.setAction("");
				
				 if(session.getAttribute("role")!= null)
				 {
					 if(session.getAttribute("role").toString().equalsIgnoreCase("SR"))
					 {
						 agmpreportdetailsdto.setOffcChk("SR");
					 }
					 else
					 {
						 agmpreportdetailsdto2.setParaComments("");
						 agmpreportdetailsdto.setOffcChk("DR");
					 }
				 }
				 agmpreportdetailsdto.setParaName(agmpreportdetailsdto2.getParaName());
				 agmpreportdetailsdto.setParaNum(agmpreportdetailsdto2.getParaNum());
				 String paraType = agmpreportdetailsdto2.getParaTypeName();
				 paraType = paraType == null?"-":paraType;
				 if(paraType.equals(""))
					 paraType = "-";
				 agmpreportdetailsdto.setParaTypeName(paraType);
				 agmpreportdetailsdto.setParaStatusList(agmpreportdetailsdto2.getParaStatusList());
				 agmpreportdetailsdto.setParaId(agmpreportdetailsdto2.getParaId());
				 agmpreportdetailsdto.setParaStatus(agmpreportdetailsdto2.getParaStatus());
				 agmpreportdetailsdto.setObjectionList(agmpreportdetailsdto2.getObjectionList());
				 agmpreportdetailsdto.setParaComments(agmpreportdetailsdto2.getParaComments());
				 agmpreportdetailsdto.setDbParaStatus(agmpreportdetailsdto2.getDbParaStatus());
				 agmpreportdetailform.setAgmpreportdetailsdto(agmpreportdetailsdto);
				 return mapping.findForward("objDtls"); 
			 }
			 
			 if(request.getParameter("objTxnId")!=null)
			 {
				 String obj_txn_id=request.getParameter("objTxnId");
				 AGMPReportDetailsDTO agmpreportdetailsdto1=agmpbd.getObjectionParaDtls(obj_txn_id);
				 
				 agmpreportdetailsdto1.setObjId(obj_txn_id);
				 //added for persistence
				 agmpreportdetailsdto1.setParaId(agmpreportdetailsdto.getParaId());
				 agmpreportdetailsdto1.setParaStatus(agmpreportdetailsdto.getParaStatus());
				 //end
				 //agmpreportdetailsdto1.setFormName("EditObjDetails");
				 //agmpreportdetailsdto1.setAction("ExistingAuditAction");
				 
				 agmpreportdetailsdto.setObjId(agmpreportdetailsdto1.getObjId());
				 //added for persistence
				 agmpreportdetailsdto.setParaId(agmpreportdetailsdto1.getParaId());
				 agmpreportdetailsdto.setParaStatus(agmpreportdetailsdto1.getParaStatus());
				 agmpreportdetailsdto.setObjDtls(agmpreportdetailsdto1.getObjDtls());
				 agmpreportdetailsdto.setObjDate(agmpreportdetailsdto1.getObjDate());
				 agmpreportdetailsdto.setValReg(agmpreportdetailsdto1.getValReg());
				 agmpreportdetailsdto.setValAgmp(agmpreportdetailsdto1.getValAgmp());
				 agmpreportdetailsdto.setDefStampDuty(agmpreportdetailsdto1.getDefStampDuty());
				 agmpreportdetailsdto.setDefRegFee(agmpreportdetailsdto1.getDefRegFee());
				 agmpreportdetailsdto.setCaseId(agmpreportdetailsdto1.getCaseId());
				 agmpreportdetailsdto.setDocId(agmpreportdetailsdto1.getDocId());
				 agmpreportdetailsdto.setObjStatus(agmpreportdetailsdto1.getObjStatus());
				 agmpreportdetailform.setAgmpreportdetailsdto(agmpreportdetailsdto);
				 return mapping.findForward("editObj"); 
			 }
			 
			agmpreportdetailsdto = agmpreportdetailform.getAgmpreportdetailsdto();
			if(agmpreportdetailsdto.getFormName()!=null)
			{
				if(agmpreportdetailsdto.getFormName().equalsIgnoreCase("EditDocDetails")&&
						agmpreportdetailsdto.getAction().equals("ExistingAuditAction"))
				{
					boolean flg=false;
					flg=agmpbd.updateParaDetails(agmpreportdetailsdto);
					if(flg)
					{
						agmpreportdetailsdto.setAction("");
						agmpreportdetailsdto.setFormName("");
						
						
						return mapping.findForward("updatesuccess");
					}
				}
				else if(agmpreportdetailsdto.getFormName().equalsIgnoreCase("EditObjDetails")&&
						agmpreportdetailsdto.getAction().equals("ExistingAuditAction"))
				{
					boolean flg=false;
					flg=agmpbd.updateObjectionParaDetails(agmpreportdetailsdto);
					if(flg)
					{
						agmpreportdetailsdto.setAction("");
						agmpreportdetailsdto.setFormName("");
						AGMPReportDetailsDTO agmpreportdetailsdto1=agmpbd.getParaDtls(agmpreportdetailsdto.getParaId());
						 ArrayList paraStatusList=agmpbd.getParaStatusList(agmpreportdetailsdto.getParaStatus());
						 agmpreportdetailsdto1.setParaStatusList(paraStatusList);
						 ArrayList list=agmpbd.getObjectionDtls(agmpreportdetailsdto.getParaId());
						 agmpreportdetailsdto1.setObjectionList(list);
						 
						 
						 
						 //added by shruti--24 june 2014
						 agmpreportdetailsdto.setParaStatusList(agmpreportdetailsdto1.getParaStatusList());
						 agmpreportdetailsdto.setObjectionList(agmpreportdetailsdto1.getObjectionList());
						 if(session.getAttribute("role")!= null)
						 {
							 if(session.getAttribute("role").toString().equalsIgnoreCase("SR"))
							 {
								 agmpreportdetailsdto.setOffcChk("SR");
							 }
							 else
							 {
								 agmpreportdetailsdto.setParaComments("");
								 agmpreportdetailsdto.setOffcChk("DR");
							 }
						 }
						 //end
						 agmpreportdetailform.setAgmpreportdetailsdto(agmpreportdetailsdto);
						 return mapping.findForward("objDtls"); 	 
					}
				}
			}
			
			
			if(request.getParameter("start")!=null)
			{
				if (session.getAttribute("existingDetails") != null)
				{
					session.removeAttribute("existingDetails");
				}
				if(session.getAttribute("aGMPReportDetailsForm")!=null)
				{
					session.removeAttribute("aGMPReportDetailsForm");
				}
				if (session.getAttribute("docIDStatus") != null)
				{
					session.removeAttribute("docIDStatus");
				}
				if (session.getAttribute("caseIDStatus") != null)
				{
					session.removeAttribute("caseIDStatus");
				}
				if (session.getAttribute("miscIDStatus") != null)
				{
					session.removeAttribute("miscIDStatus");
				}
				if (session.getAttribute("dtoList") != null)
				{
					session.removeAttribute("dtoList");
				}
				if (session.getAttribute("FilePath") != null)
				{
					session.removeAttribute("FilePath");
				}
				if (session.getAttribute("attachment1") != null)
				{
					session.removeAttribute("attachment1");
				}
				
				agmpreportdetailform.reset(mapping, request);
				
				if ("session".equals(mapping.getScope()))
				{
					request.setAttribute(mapping.getAttribute(), form);
				}
				
				agmpreportdetailform = (AGMPReportDetailsForm) form;
				agmpreportdetailsdto = agmpreportdetailform.getAgmpreportdetailsdto();
				String officeId=(String)session.getAttribute("loggedToOffice");
				//modified by shruti----19 june 2014
				//agmpreportdetailsdto.setDistrict(agmpbd.agmpdao.getDistrictId(officeId));
				agmpreportdetailsdto.setDistrict(agmpbd.getDistrict(officeId, language));
				
				
				String strForwardPage = startAuditAction(request,agmpreportdetailsdto, agmpbd, session);
				return mapping.findForward(strForwardPage);
			}
			
			
			else if (request.getParameter("cancel") != null) 
			{
				if (session.getAttribute("existingDetails") != null)
				{
					session.removeAttribute("existingDetails");
				}
				if (session.getAttribute("docIDStatus") != null)
				{
					session.removeAttribute("docIDStatus");
				}
				if (session.getAttribute("dtoList") != null)
				{
					session.removeAttribute("dtoList");
				}
				if (session.getAttribute("FilePath") != null)
				{
					session.removeAttribute("FilePath");
				}
				if (session.getAttribute("attachment1") != null)
				{
					session.removeAttribute("attachment1");
				}
				if(session.getAttribute("aGMPReportDetailsForm")!=null)
				{
						session.removeAttribute("aGMPReportDetailsForm");
				}
				if ("session".equals(mapping.getScope()))
				{
					request.setAttribute(mapping.getAttribute(), form);
				}
				
				agmpreportdetailform = (AGMPReportDetailsForm) form;
				agmpreportdetailsdto = agmpreportdetailform.getAgmpreportdetailsdto();
				String officeId=(String)session.getAttribute("loggedToOffice");
				agmpreportdetailsdto.setDistrict(agmpbd.getOfficeName(officeId,language));
				
				agmpreportdetailform.reset(mapping, request);
				return mapping.findForward("cancel");
			} 
			
			else if (request.getParameter("regid") != null) 
			{
				String regid = request.getParameter("regid");
				String userid=(String)session.getAttribute("UserId");
				agmpreportdetailsdto.setRegId(regid);
				String str = agmpreportdetailsdto.getDocType();
				if (agmpreportdetailsdto.getDocType().equalsIgnoreCase("Regid"))
				{
					RegIdOtherSearch regIdOthers = agmpbd.getRegIdDetails(agmpreportdetailsdto,userid);
					request.setAttribute("regidOthers", regIdOthers);
					return mapping.findForward("regdetails");
				}
			} 
			
			else if (request.getParameter("filling_id") != null) 
			{
				String docid = request.getParameter("filling_id");
				agmpreportdetailsdto.setRegId(docid);
				RegIdOtherSearch regIdOthers = agmpbd.getRegIdOtherDetails(agmpreportdetailsdto);
				request.setAttribute("regidOthers", regIdOthers);
				return mapping.findForward("regothers");
			} 
			
			else if (request.getParameter("addDoc") != null) 
			{
				return mapping.findForward("addDocRcpt");
			} 
			
			else if (request.getParameter("existRegid") != null) 
			{
				//on click of report status open for existing report
				String reportId = request.getParameter("existRegid");
				AGMPReportDetailsDTO agmpdto = new AGMPReportDetailsDTO();
				agmpdto = agmpbd.getExistingDetails(reportId,language);
				agmpreportdetailsdto.setTxnId(reportId);
				agmpreportdetailsdto.setTxtAuditorName((agmpdto.getTxtAuditorName()));
				agmpreportdetailsdto.setTxtAuditLocation(agmpdto.getTxtAuditLocation());
				session.setAttribute("existingDetails", agmpdto);
	
				if (agmpreportdetailsdto.getAuditBody().equalsIgnoreCase("AGMP")
						&& agmpreportdetailsdto.getAuditType().equalsIgnoreCase("Receipt")) 
				{
					ArrayList auditParaDtlsList=agmpbd.getReceiptParaDtlsList(agmpreportdetailsdto.getTxnId());
					session.setAttribute("auditParaDtlsList", auditParaDtlsList);
					
					ArrayList Paralist = agmpbd.getReceiptParaList(language);
					session.setAttribute("Paralist", Paralist);
					return mapping.findForward("ExistingAgmp");
				}
				else if (agmpreportdetailsdto.getAuditBody().equalsIgnoreCase("Internal")
				&& agmpreportdetailsdto.getAuditType().equalsIgnoreCase("Receipt"))
				{
					ArrayList Paralist = agmpbd.getReceiptParaList(language);
					session.setAttribute("Paralist", Paralist);
					return mapping.findForward("existingIntAddPara");
				}
				else if (agmpreportdetailsdto.getAuditBody().equalsIgnoreCase("AGMP")
				&& agmpreportdetailsdto.getAuditType().equalsIgnoreCase("Expenditure"))
				{
					try {
						ArrayList Paralist = agmpbd.getExpenParaList();
						session.setAttribute("Paralist", Paralist);					
					}
					catch (Exception e) {
						// TODO: handle exception
					}
					return mapping.findForward("ExistingExpAgmpDtl");
				} 
				else if (agmpreportdetailsdto.getAuditBody().equalsIgnoreCase("Internal")
				&& agmpreportdetailsdto.getAuditType().equalsIgnoreCase("Expenditure"))
				{
					try {
						ArrayList Paralist = agmpbd.getExpenParaList();
						session.setAttribute("Paralist", Paralist);
						
					}
					catch (Exception e) {
						// TODO: handle exception
					}
					return mapping.findForward("ExistingExpInternalDtl");
				}
			}
			
			
	
			/**
			 * This Condition will be satisfied When we will come from Start Page.
			 * At this point of time We have to fetch the office Details and
			 * populate the ListBox. Here officeTypeId is hard coded to OT1. Later
			 * this can be changed according to the user who is logged in we can
			 * fetch Users office type ID and pass this to the BD method to get
			 * SRO(Office Details).
			 */
			if (agmpreportdetailsdto.getFormName() == null && agmpreportdetailsdto.getAction() == null)
			{
				String strForwardPage = startAuditAction(request,agmpreportdetailsdto, agmpbd, session);
				return mapping.findForward(strForwardPage);
			}
			/**
			 * This Condition will handle the request for New Audit Report. A Audit
			 * Report can be Receipt Or Expenditure. Depending on the condition it
			 * will forward the request to another Page.
			 */
	
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("NewRcptForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("newRcpt")) 
			{
				String strForwardPage = newAuditAction(request,agmpreportdetailsdto, agmpbd, rule, errorList);
				return mapping.findForward(strForwardPage);
			}
	
			/***********************************************************************
			 * New Agmp Receipt Form
			 **********************************************************************/
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("NewAgmpRcptForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("newagmp")) 
			{
				String strForwardPage = newAgmpReceiptAction(request,agmpreportdetailsdto, session, rule, errorList);
				return mapping.findForward(strForwardPage);
			}
	
			/*
			 * Existing Expenditure
			 * 
			 */
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("ExistingExpAuditPara")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("ExistingExpAuditAction")) 
			{
				String strForward = existingExpAuditPara(request,agmpreportdetailsdto, session, rule, errorList, agmpbd);
				return mapping.findForward(strForward);
			}
			
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("ExistExpAddAuditPara")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("ExistExpAddAuditAction")) 
			{
				String strForward = existingAddAuditPara(request,agmpreportdetailsdto, rule, errorList);
				return mapping.findForward(strForward);
			}
			
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("ExistingExpIntAuditPara")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("ExistingExpIntAuditAction"))
			{
				String strForward = existingExpIntAddPara(request,agmpreportdetailsdto, session, rule, errorList, agmpbd);
				return mapping.findForward(strForward);
			}
			
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("ExistExpIntAddAuditPara")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("ExistExpIntAddAuditAction")) 
			{
				String strForwardPage = existingExpIntAddPara(request,agmpreportdetailsdto, rule, errorList);
				return mapping.findForward(strForwardPage);
			}
	
			/***********************************************************************
			 * Existing AuditPara Form
			 **********************************************************************/
			//on adding audit para in existing agmp receipt report
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("ExistingAuditPara")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("ExistingAuditAction"))
			{
				String strForward = existingAddAuditPara(request,agmpreportdetailsdto, session, rule, errorList, agmpbd);
				return mapping.findForward(strForward);
			}
			
			//on click of adding documents
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("ExistAddAuditPara")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("ExistAddAuditAction")) 
			{
				String strForward = existAddParaDetails(request,agmpreportdetailsdto, rule, errorList);
				return mapping.findForward(strForward);
			}
	
			/**
			 * New Agmp Expenditure Form
			 */
			
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("NewAgmpExpForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("NewAgmpExpAction")) 
			{
				String forwardPage = newExpAgmpForm(request, agmpreportdetailsdto,rule, errorList, session);
				return mapping.findForward(forwardPage);
			}
	
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("NewExpAuditReportForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("NewExpAuditReportAction")) 
			{
				String forwardPage = newExpAuditReportForm(request,agmpreportdetailsdto, rule, errorList, session);
				return mapping.findForward(forwardPage);
			}
	
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("AddAuditParaForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("AddAuditParaAction")) 
			{
				String forwardPage = newExpAddDocForm(request,agmpreportdetailsdto, rule, errorList, session);
				return mapping.findForward(forwardPage);
			}
	
			/**
			 * This Condition will satisfied If a User Will be finished with the
			 * attachments and Selecting a paera to add
			 */
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("NewAuditRepotForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("newauditreport"))
			{
				String strReturnVal = newReceiptAuditAttachDocAction(request,agmpreportdetailsdto, session, rule, errorList);
				return mapping.findForward(strReturnVal);
	
			}
	
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("AddAuditParaForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("auditparaaction")) 
			{
				String strReturnVal = receiptAddAuditParaAction(request,agmpreportdetailsdto, rule, errorList);
				return mapping.findForward(strReturnVal);
			}
			/**
			 * New Internal Expenditure Audit
			 */
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("NewIntExpForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("NewIntExpAction")) 
			{
				String forwardPage = newIntExpForm(request, agmpreportdetailsdto,rule, errorList, session);
				return mapping.findForward(forwardPage);
			}
			
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("NewInternalExpForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("NewInternalExpAction"))
			{
				String forwardPage = newIntExpAuditReport(request,agmpreportdetailsdto, session, rule, errorList);
				return mapping.findForward(forwardPage);
			}
			
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("AddIntAuditParaForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("AddIntAuditParaAction")) 
			{
				String forwardPage = newExpIntAddDocForm(request,agmpreportdetailsdto, rule, errorList, session);
				return mapping.findForward(forwardPage);
			}
			
			
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("CaseIdForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("searchcaseid")) 
			{
				String strReturnVal = null;
				if (isCancelled(request)) 
				{
					return mapping.findForward("newagmp");
				}
				if (agmpreportdetailsdto.getCaseType().equalsIgnoreCase("CaseId")) 
				{
					strReturnVal = caseIdSearchAction(request, agmpreportdetailsdto,agmpbd,session);
					return mapping.findForward(strReturnVal);
				}
				if (agmpreportdetailsdto.getCaseType().equalsIgnoreCase("RRCCaseId")) 
				{
					strReturnVal = searchOtherDetails(request,agmpreportdetailsdto, agmpbd);
					return mapping.findForward(strReturnVal);
				}
	
			}
			
			
			//on searching reg id
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("RegidForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("searchregid")) 
			{
					String strReturnVal = null;
				
						String partydtls=(String)request.getParameter("partyDetails");
						if(partydtls!=null)
						{
						if(partydtls.equalsIgnoreCase("true"))
						{
							
							if(agmpreportdetailsdto.getDocTypeFlag().equalsIgnoreCase("R"))
							{
								agmpreportdetailsdto.setDocType("");
								PartyDetailsDTO partydto1 = new PartyDetailsDTO();
								PartyDetailsDTO partyDto = new PartyDetailsDTO();
								try {
									
									partydto1=agmpbd.getPartyDtls(agmpreportdetailsdto.getRegId(),language);
									partyDto.setPartyList(partydto1.getPartyList());
									agmpreportdetailform.setPartyDto(partyDto);
									strReturnVal= "partydtls";
									return mapping.findForward(strReturnVal);
									
								} 
								catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}else{
								
								
								SuppleDocDTO sdDTO=new SuppleDocDTO();
								SuppleDocBD sdBD=new SuppleDocBD();
								
								String suppTxnId=request.getParameter("supplementaryId");
			            		sdDTO=sdBD.getTotalDetailsBD(suppTxnId,language);
			            		if(sdDTO!=null)
			            		{
			            			session.setAttribute("propTxnNo", sdDTO.getPropertyTxnId());
			            			request.setAttribute("partyDetails", sdDTO.getPartyDetails());
			            			request.setAttribute("estampDetails", sdDTO.getEstampDetails());
			            			request.setAttribute("pstampDetails", sdDTO.getPstampDetails());
			            			
			            			request.setAttribute("SuppleDocDTO", sdDTO);
			            			//fm.setSuppleDocDTO(sdDTO);
			            			//FORWAD_SUCCESS="viewScreen";
			            		}
			            		//else
			            			//FORWAD_SUCCESS="failure";
								
								//fetch details from supple doc
								//strReturnVal= "partydtlsSuppleDoc";
			            		strReturnVal= "partydtls";
								return mapping.findForward(strReturnVal);
								
								
							}
						}
						}
						
						String propertydtls=(String)request.getParameter("propertyDetails");
						if(propertydtls!=null)
						{
						if(propertydtls.equalsIgnoreCase("true"))
						{	
							
							if(agmpreportdetailsdto.getDocTypeFlag().equalsIgnoreCase("R"))
							{
							agmpreportdetailsdto.setDocType("");
							PropertyDetailsDTO propertydto1 = new PropertyDetailsDTO();
							PropertyDetailsDTO propertyDto = new PropertyDetailsDTO();
							try {
								
								propertydto1=agmpbd.getPropertyDtls(agmpreportdetailsdto.getRegId(),language);
								if(propertydto1!=null){
								propertyDto.setPropertyList(propertydto1.getPropertyList());
								agmpreportdetailform.setPropertyDto(propertyDto);
								}else{
									propertyDto.setPropertyList(null);
									agmpreportdetailform.setPropertyDto(null);
									request.setAttribute("errorMsg", "No property found for the selected Registration Number./चयनित पंजीकरण संख्या के लिए कोई संपत्ति मौजूद नहीं है।");
								}
								strReturnVal= "propertydtls";
								return mapping.findForward(strReturnVal);
								
							} 
							catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							}else{
								
								//fetch details from supple doc
								
								//strReturnVal= "propertydtlsSuppleDoc";
								strReturnVal= "propertydtls";
								return mapping.findForward(strReturnVal);
							}
							
							
						}
						}
						
					
					if (isCancelled(request)) 
					{
						return mapping.findForward("newagmp");
					}
					if (agmpreportdetailsdto.getDocType().equalsIgnoreCase("Regid")) 
					{
						strReturnVal = regIdSearchAction(request, agmpreportdetailsdto,agmpbd,session);
						agmpreportdetailsdto.setDocTypeFlag("R");
						return mapping.findForward(strReturnVal);
					}
					
					if (agmpreportdetailsdto.getOthersDocType()!="" && "sendToAction".equalsIgnoreCase(agmpreportdetailsdto.getOthersDocType())) 
					{//OTHERS
						
						//strReturnVal = searchOtherDetails(request,agmpreportdetailsdto, agmpbd);
						//agmpreportdetailsdto.setDocTypeFlag("O");
						//return mapping.findForward(strReturnVal);
						/*agmpreportdetailsdto.setTxtDocID(agmpreportdetailsdto.getOthersRegId());
						agmpreportdetailsdto.setTxtStampDuty1(agmpreportdetailsdto.getOthersStampDuty());
						
						agmpreportdetailsdto.setTxtRegFee1(agmpreportdetailsdto.getOthersRegFee());
						agmpreportdetailsdto.setTxtSRname(agmpreportdetailsdto.getOthersSrName());
						agmpreportdetailsdto.setMarketVal(agmpreportdetailsdto.getOthersValAtRegTime());
						agmpreportdetailsdto.setRegDate(agmpreportdetailsdto.getOthersRegDate());*/
						
						strReturnVal= "addDocRcpt";
						agmpreportdetailsdto.setOthersDocType("");
						agmpreportdetailsdto.setOthersFlag("1");
						return mapping.findForward(strReturnVal);
					}
					if (agmpreportdetailsdto.getDocType().equalsIgnoreCase("others")) 
					{
						agmpreportdetailsdto.setOthersRegId("");
						agmpreportdetailsdto.setOthersRegDate("");
						agmpreportdetailsdto.setOthersRegFee("");
						agmpreportdetailsdto.setOthersConsiderationAmt("");
						agmpreportdetailsdto.setOthersSrName("");
						agmpreportdetailsdto.setOthersSroName("");
						agmpreportdetailsdto.setOthersStampDuty("");
						agmpreportdetailsdto.setOthersValAtRegTime("");
						
						//OTHERS
						//strReturnVal = searchOtherDetails(request,agmpreportdetailsdto, agmpbd);
						agmpreportdetailsdto.setDocTypeFlag("O");
						//return mapping.findForward(strReturnVal);
						strReturnVal= "idothers";
						return mapping.findForward(strReturnVal);
						
					}
					
	
			}
			

			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("addDocAgmp")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("addDocAgmp")) 
			{
				String forwardPage = addDocAgmpDetails(request,agmpreportdetailsdto, session, agmpbd, form);
				return mapping.findForward(forwardPage);
			}
			
			/**
			 * New Internal Rcpt Audit
			 */
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("NewInternalRcptForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("newinternal")) 
			{
				if (isCancelled(request)) 
				{
					return mapping.findForward("newagmp");
				}
	
				rule = new AuditInspectionRule();
				errorList = rule.validateAudit(agmpreportdetailsdto);
				if (rule.isError()) 
				{
					request.setAttribute("errorsList", errorList);
					return mapping.findForward("newinternal");
				}
	
				String sroId = agmpreportdetailsdto.getSroId();
				AGMPReportDetailsDTO tempList = (AGMPReportDetailsDTO) session.getAttribute("SRODetails1");
				ArrayList l1 = tempList.getSroList();
				ArrayList Paralist = agmpbd.getReceiptParaList(language);
				session.setAttribute("Paralist", Paralist);
				for (int i = 0; i < l1.size(); i++) 
				{
					AGMPReportDetailsDTO list = (AGMPReportDetailsDTO) l1.get(i);
					if (list.getSroId().equals(sroId))
					{
						String name = list.getSroName();
						agmpreportdetailsdto.setSroName(name);
						break;
					}
				}
				return mapping.findForward("newreportinternal");
			}
			
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("NewInternalForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("newInternalreport")) 
			{
				String strReturnVal = newReceiptAuditAddDocInternal(request,agmpreportdetailsdto, session, rule, errorList);
				return mapping.findForward(strReturnVal);
			}
			
			
			if (agmpreportdetailsdto.getFormName().equalsIgnoreCase("AddIntParaForm")&& agmpreportdetailsdto.getAction().equalsIgnoreCase("InternalAuditpara")) 
			{
				String strRetunVal = newReceiptAddAuditParaInternal(request,agmpreportdetailsdto, rule, errorList);
				return mapping.findForward(strRetunVal);
			}
			
			}catch (Exception e) 
			{
				errorList.add("The Transaction will not be proceed");
				errorList.add(e.toString());
				request.setAttribute("errorsList",errorList);
				return mapping.findForward("newrcpt");
			}
			
	
			return mapping.findForward("false");
		}
	
		
		/**
		 * @param request
		 * @param agmpreportdetailsdto
		 * @param agmpbd
		 * @param session
		 * @return
		 */
		private String startAuditAction(HttpServletRequest request,
				AGMPReportDetailsDTO agmpreportdetailsdto,
				AGMPReportDetailsBD agmpbd, HttpSession session) 
		{
			ArrayList sroList = null;
			String userId = (String) session.getAttribute("UserId");
			CommonConstant auditConstant=new CommonConstant();
			// (DR/SR)
			try 
			{
				//MODIFIED BY SHRUTI ---- 17TH OCT 2013
				//sroList = agmpbd.getSroName(userId);
				String officeId=(String)session.getAttribute("loggedToOffice");
				 String language=(String)session.getAttribute("languageLocale");
				//CHECK OF SR/DR LOGIN----28 march 2014
					//role check
					SROReportDetailsBD sroReportBd = new SROReportDetailsBD();
					if(auditConstant.DIG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
					{
						sroList = sroReportBd.getSROForDIG(officeId, language);
						session.setAttribute("role", "DIG");
					}
					else if(auditConstant.IG_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
					{
						sroList=sroReportBd.getAllSroName(language);
						session.setAttribute("role", "IG");
					}
					else if(auditConstant.SR_OFFICE_TYPE_ID.equalsIgnoreCase(sroReportBd.getOfficeTypeId(officeId)))
					{
						sroList=sroReportBd.getLoggedInSroName(officeId, language);
						agmpreportdetailsdto.setOffcChk("SR");
						session.setAttribute("role", "SR");
					}
					else
					{
						sroList = agmpbd.getJurisdictionSroName(officeId,language);
						agmpreportdetailsdto.setOffcChk("DR");
						session.setAttribute("role", "DR");
					}
					//end
					
				agmpreportdetailsdto.setSroList(sroList);
			}
			catch (Exception sroEx)
			{
				sroEx.printStackTrace();
			}
			session.setAttribute("SRODetails1", agmpreportdetailsdto);
			return "newrcpt";
		}// END OF METHOD
	
		
		/**
		 * @param request
		 * @param agmpreportdetailsdto
		 * @param agmpbd
		 * @param rule
		 * @param errorList
		 * @return
		 */

	private String newAuditAction(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AGMPReportDetailsBD agmpbd, AuditInspectionRule rule,
			ArrayList errorList) 
	{
	
		String strReturnValue = null;
		try {
			String value = agmpreportdetailsdto.getReport(); // Getting The Report Type(Receipt Or Expenditure)
			rule = new AuditInspectionRule();
			errorList = rule.validateAudit(agmpreportdetailsdto);

			// Cheking Validations for the Page.
			if (rule.isError()) 
			{
				request.setAttribute("errorsList", errorList);
				strReturnValue = "newrcpt";
			}
			/*
			 * Checking Whether a Audit Type is RECEIPT
			 */
			else if (agmpreportdetailsdto.getAuditType().equalsIgnoreCase("receipt")) 
			{
				/*
				 * If it is RECEIPT Then Is it a NEW or Existing Report.
				 */
				if (value.equalsIgnoreCase("new"))
				{
					// If NEW Then Is It done By AGMP or Internal Audit Body
					if (agmpreportdetailsdto.getAuditBody().equalsIgnoreCase("agmp")) 
					{

						strReturnValue = "auditnew";
					} 
					else
					{
						strReturnValue = "newinternal";
					}
				}
				/*
				 * Checking RECEIPT is EXISTING
				 */
				else 
				{
					// Checking EXISTING Reciept is done By AGMP OR INTERNAL
					// AUDIT BODY
					if (agmpreportdetailsdto.getAuditBody().equalsIgnoreCase("agmp")) 
					{
						ArrayList existing = new ArrayList();
						existing = agmpbd.getExistingAgmpRcptTxnIds(agmpreportdetailsdto);
						request.setAttribute("existingTxn", existing);
						strReturnValue = "existingAudit";
					}
					// Checking EXISTING Reciept is done By INTERNAL AUDIT BODY
					else 
					{
						ArrayList existing = new ArrayList();
						existing = agmpbd.getExistingInternalRcptTxnIds(agmpreportdetailsdto);
						request.setAttribute("existingTxn", existing);
						strReturnValue = "existingInternal";
					}
				}
			}
			/*
			 * If Audit Report is not RECEIPT it would be EXPENDITURE REPORT
			 */
			else 
			{
				// Checking if EXPENDITURE REPORT is NEW or EXISTING
				if (value.equalsIgnoreCase("new")) 
				{
					// If NEW Then whether it is done by AGMP or INTERNAL Audit Body
					if (agmpreportdetailsdto.getAuditBody().equalsIgnoreCase("agmp")) 
					{
						strReturnValue = "ExpAuditNew";
					} 
					else 
					{
						strReturnValue = "InternalExpAudit";
					}
				}
				// Checking for Existing EXPENDITURE Report
				else 
				{
					// If EXISTING Then Checking it is done by AGMP or INTERNAL Audit Body
					if (agmpreportdetailsdto.getAuditBody().equalsIgnoreCase("agmp")) 
					{
						ArrayList existing = new ArrayList();
						existing = agmpbd.getExistingAgmpExpTxnIds(agmpreportdetailsdto);
						request.setAttribute("existingTxn", existing);
						strReturnValue = "ExistingExpAGMP";
					} 
					else
					{
						ArrayList existing = new ArrayList();
						existing = agmpbd.getExistingInternalExpTxnIds(agmpreportdetailsdto);
						request.setAttribute("existingTxn", existing);
						strReturnValue = "ExistingExpInternal";
					}
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return strReturnValue;
	}// END OF METHOD

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param session
	 * @param rule
	 * @param errorList
	 * @return
	 */
	private String newAgmpReceiptAction(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto, HttpSession session,
			AuditInspectionRule rule, ArrayList errorList) 
	{
		String strReturnVal = null;
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError())
		{
			request.setAttribute("errorsList", errorList);
			strReturnVal = "auditnew";
			return strReturnVal;
		}
		String sroId = agmpreportdetailsdto.getSroId();
		AGMPReportDetailsDTO tempList = (AGMPReportDetailsDTO) session.getAttribute("SRODetails1");
		ArrayList l1 = tempList.getSroList();
		for (int i = 0; i < l1.size(); i++) 
		{
			AGMPReportDetailsDTO list = (AGMPReportDetailsDTO) l1.get(i);
			if (list.getSroId().equals(sroId)) 
			{
				String name = list.getSroName();
				agmpreportdetailsdto.setSroName(name);
				break;
			}
		}
		try
		{
			String language=(String)session.getAttribute("languageLocale");
			AGMPReportDetailsBD agmpbd = new AGMPReportDetailsBD();
			ArrayList Paralist = agmpbd.getReceiptParaList(language);
			session.setAttribute("Paralist", Paralist);
		} catch (Exception e) 
		{
			
		}
		strReturnVal = "success";
		return strReturnVal;
	}// END OF METHOD

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param session
	 * @param rule
	 * @param errorList
	 * @return
	 */
	private String newReceiptAuditAttachDocAction(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto, HttpSession session,
			AuditInspectionRule rule, ArrayList errorList)
	{
		String strReturnVal = null;

		rule = new AuditInspectionRule();
		session.setAttribute("aGMPReportDetailsForm",agmpreportdetailform);
	
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			if (session.getAttribute("attachment1") == null) 
			{
				errorList.add("<li><font color=" + "red"+ ">Please Attach Documents</font></li>");
			} 
			else if (((ArrayList) session.getAttribute("attachment1")).size() < 1)
			{
				errorList.add("<li><font color=" + "red"+ ">Please Attach Documents</font></li>");
			}
			request.setAttribute("errorsList", errorList);
			strReturnVal = "success";
		}
		if (session.getAttribute("attachment1") == null) 
		{
			errorList.add("<li><font color=" + "red"+ ">Please Attach Documents</font></li>");
			request.setAttribute("errorsList", errorList);
			strReturnVal = "success";
		} 
		else 
		{
			ArrayList list = (ArrayList) session.getAttribute("attachment1");
			if (list.size() < 1)
			{
				errorList.add("<li><font color=" + "red"+ ">Please Attach Documents</font></li>");
				request.setAttribute("errorsList", errorList);
				strReturnVal = "success";
			} 
			else
			{
				strReturnVal = "newauditpara";
			}
		}
		return strReturnVal;
	}// END OF METHOD

	/***************************************************************************
	 * Existing AuditParaDetails
	 **************************************************************************/
	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param session
	 * @param rule
	 * @param errorList
	 * @param agmpBD
	 * @return
	 */
	private String existingAddAuditPara(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto, HttpSession session,
			AuditInspectionRule rule, ArrayList errorList,
			AGMPReportDetailsBD agmpBD) 
	{
		String strForward = null;

		if (agmpreportdetailsdto.getAuditBody().equals("AGMP")) 
		{
			rule = new AuditInspectionRule();
			errorList = rule.validateAudit(agmpreportdetailsdto);
			if (rule.isError()) 
			{
				request.setAttribute("errorsList", errorList);
				strForward = "ExistingAgmp";
			}

			if (agmpreportdetailsdto.getIsFinalPage().equalsIgnoreCase("true"))
			{
				String language=(String)session.getAttribute("languageLocale");
				agmpreportdetailsdto.setReport("EXISTING");
				session.removeAttribute("docIDStatus");
				String txnId = agmpreportdetailsdto.getTxnId();
				agmpreportdetailsdto = agmpBD.getExistingDetails(txnId,language);
				session.setAttribute("existingDetails", agmpreportdetailsdto);
			}
			strForward = "existAddAudit";
		} 
		else 
		{
			rule = new AuditInspectionRule();
			errorList = rule.validateAudit(agmpreportdetailsdto);
			if (rule.isError()) 
			{
				request.setAttribute("errorsList", errorList);
				strForward = "existingIntAddPara";
			}
			if (agmpreportdetailsdto.getIsFinalPage().equalsIgnoreCase("true")) 
			{
				String language=(String)session.getAttribute("languageLocale");
				agmpreportdetailsdto.setReport("EXISTING");
				session.removeAttribute("docIDStatus");
				String txnId = agmpreportdetailsdto.getTxnId();
				agmpreportdetailsdto = agmpBD.getExistingDetails(txnId,language);
				session.setAttribute("existingDetails", agmpreportdetailsdto);
			}
			strForward = "existIntAddpara_01";
		}
		return strForward;
	} /* End Of The Method */

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param rule
	 * @param errorList
	 * @return
	 */
	private String existAddParaDetails(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AuditInspectionRule rule, ArrayList errorList) 
	{
		String strForward = null;
		if (agmpreportdetailsdto.getAuditBody().equals("AGMP")) 
		{
			logger.info("para type >>>>>>>>>>>>>"+agmpreportdetailsdto.getListParaType());
			rule = new AuditInspectionRule();
			errorList = rule.validateAudit(agmpreportdetailsdto);
			if (rule.isError()) 
			{
				request.setAttribute("errorsList", errorList);
				strForward = "existAddAudit";
			}
			//added by shruti--- modified by 11 sep 2013
			String paraType=agmpreportdetailsdto.getListParaType();
			if(paraType.equalsIgnoreCase("documents")||(paraType.equalsIgnoreCase("दस्तावेज़"))||paraType.equalsIgnoreCase("P01"))
			{
				agmpreportdetailsdto.setTxtStampDuty(""); // for 2483
				agmpreportdetailsdto.setTxtRegFee("");    // for 2483
				agmpreportdetailsdto.setTempValAgmp("");  // for 2483
				strForward = "existAddDoc";
			}
			else if(paraType.equalsIgnoreCase("Pending Cases")||(paraType.equalsIgnoreCase("अनिर्णीत मामले"))||paraType.equalsIgnoreCase("P02"))
			{
				strForward ="existAddPendingDoc";
			}
			else if(paraType.equalsIgnoreCase("miscellaneous")||(paraType.equalsIgnoreCase("विविध"))||paraType.equalsIgnoreCase("P03"))
			{
				strForward ="existAddMiscDoc";
			}
			else
			{
			strForward = "existAddDoc";
			}
			//end
		} 
		else {
			rule = new AuditInspectionRule();
			errorList = rule.validateAudit(agmpreportdetailsdto);
			if (rule.isError()) 
			{
				request.setAttribute("errorsList", errorList);
				strForward = "existIntAddpara_01";
			}
			//added by shruti--- modified by 11 sep 2013
			String paraType=agmpreportdetailsdto.getListParaType();
			if(paraType.equalsIgnoreCase("documents")||(paraType.equalsIgnoreCase("दस्तावेज़"))||paraType.equalsIgnoreCase("P01"))
			{
				agmpreportdetailsdto.setTxtStampDuty(""); // for 2886
				agmpreportdetailsdto.setTxtRegFee("");    // for 2886
				agmpreportdetailsdto.setTempValAgmp("");  // for 2886
				strForward = "existingIntAdd";
			}
			else if(paraType.equalsIgnoreCase("Pending Cases")||(paraType.equalsIgnoreCase("अनिर्णीत मामले"))||paraType.equalsIgnoreCase("P02"))
			{
				strForward ="existingIntPendingAdd";
			}
			else if(paraType.equalsIgnoreCase("miscellaneous")||(paraType.equalsIgnoreCase("विविध"))||paraType.equalsIgnoreCase("P03"))
			{
				strForward ="existingIntMiscAdd";
			}
			else
			{
			strForward = "existingIntAdd";
			}
			
			//strForward = "existingIntAdd";
		}

		return strForward;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param session
	 * @param agmpbd
	 * @param form
	 * @return
	 */
	private String addDocAgmpDetails(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto, HttpSession session,
			AGMPReportDetailsBD agmpbd, ActionForm form) 
	{		String strForward = null;
		AGMPReportDetailsForm agmpreportdetailform = (AGMPReportDetailsForm) form;
		if (isCancelled(request)) 
		{
			strForward = "newagmp";
		}
		String result = "false";
		boolean res = false;
		boolean res1 = false;
		String auditBody = agmpreportdetailsdto.getAuditBody(); // Agmp/Internal
		String auditType = agmpreportdetailsdto.getAuditType(); // Receipt/Expenditure
		String auditReport = agmpreportdetailsdto.getReport(); // New/Existing

		String str1 = agmpreportdetailform.getAddMore();
		ArrayList dtoList = new ArrayList();

		/* Showing added DocId Details */
		logger.debug("in addDocAgmpDetails");
		if (session.getAttribute("docIDStatus") != null) 
		{
			AGMPReportDetailsForm agmpreportdetailform1 = (AGMPReportDetailsForm) form;
			AGMPReportDetailsDTO agmpreportdetailsdto1 = agmpreportdetailform1.getAgmpreportdetailsdto();
			DocDetails docDetail = null;
			if (agmpreportdetailsdto.getDocType().equalsIgnoreCase("others")) 
			{
				docDetail = agmpbd.getDocStatus(agmpreportdetailsdto1);
				//System.out.println("reg id by dto 1========"+agmpreportdetailsdto1.getTxtDocID());
				//System.out.println("reg id by dto ========"+agmpReport.getTxtDocID());
				//docDetail.setTxtDocID(agmpreportdetailsdto.getTxtDocID());
			}
			else
			{	
				docDetail = agmpbd.getRegStatus(agmpreportdetailsdto);
			}
		
			dtoList = (ArrayList) session.getAttribute("docIDStatus");
			dtoList.add(docDetail);
			session.removeAttribute("docIDStatus");
			session.setAttribute("docIDStatus", dtoList);
		}
		//added by shruti----- 12th sep 2013 for adding objected doc in case of pending audit para
		else if (session.getAttribute("caseIDStatus") != null) 
		{
			AGMPReportDetailsForm agmpreportdetailform1 = (AGMPReportDetailsForm) form;
			AGMPReportDetailsDTO agmpreportdetailsdto1 = agmpreportdetailform1.getAgmpreportdetailsdto();
			CaseDetails caseDetail = null;
			
			if (agmpreportdetailsdto.getCaseType().equalsIgnoreCase("CaseId"))
			{
				caseDetail = agmpbd.getCaseStatus(agmpreportdetailsdto);
			}
			else 
			{
				caseDetail = agmpbd.getRRCCaseStatus(agmpreportdetailsdto);
			}
		
			dtoList = (ArrayList) session.getAttribute("caseIDStatus");
			dtoList.add(caseDetail);
			session.removeAttribute("caseIDStatus");
			session.setAttribute("caseIDStatus", dtoList);
		}
		else if (session.getAttribute("miscIDStatus") != null) 
		{
			AGMPReportDetailsForm agmpreportdetailform1 = (AGMPReportDetailsForm) form;
			AGMPReportDetailsDTO agmpreportdetailsdto1 = agmpreportdetailform1.getAgmpreportdetailsdto();
			DocDetails docDetail = null;
			docDetail=new DocDetails();
			docDetail.setDocStatusId("OPEN");
			docDetail.setObjDetails(agmpreportdetailsdto1.getTxtObjDetails());
			dtoList=(ArrayList)session.getAttribute("miscIDStatus");
			//dtoList = (ArrayList) session.getAttribute("caseIDStatus");
			dtoList.add(docDetail);
			session.removeAttribute("miscIDStatus");
			session.setAttribute("miscIDStatus", dtoList);
		}
		//end
		else
		{
			AGMPReportDetailsForm agmpreportdetailform1 = (AGMPReportDetailsForm) form;
			DocDetails docDetail = null;
			CaseDetails caseDetail = null;
			//modified by shruti--12th sep 2013
			logger.info("list para type in add more>>>>>>>>>>>>"+agmpreportdetailsdto.getListParaType());
			logger.info("list para type in add more>>>>>>>>>>>>"+agmpreportdetailsdto.getListParaType());
			String listParaType=agmpreportdetailsdto.getListParaType();
			
			if(listParaType!=null)
			{
				if(listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01"))
				{
					if (agmpreportdetailsdto.getDocType().equalsIgnoreCase("others"))
					{
						docDetail = agmpbd.getDocStatus(agmpreportdetailsdto);
						dtoList.add(docDetail);
						session.setAttribute("docIDStatus", dtoList);
					}
					else 
					{
						docDetail = agmpbd.getRegStatus(agmpreportdetailsdto);
						dtoList.add(docDetail);
						session.setAttribute("docIDStatus", dtoList);
					}
				}
				else if(listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02"))
				{
					
					if (agmpreportdetailsdto.getCaseType() != null && agmpreportdetailsdto.getCaseType().equalsIgnoreCase("CaseId"))
					{
						caseDetail = agmpbd.getCaseStatus(agmpreportdetailsdto);
						dtoList.add(caseDetail);
						session.setAttribute("caseIDStatus", dtoList);
						
					}
					else 
					{
						caseDetail = agmpbd.getRRCCaseStatus(agmpreportdetailsdto);
						dtoList.add(caseDetail);
						session.setAttribute("caseIDStatus", dtoList);
					}
				}
				else if(listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03"))
				{
					docDetail=new DocDetails();
					docDetail.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
					docDetail.setDocStatusId("OPEN");
					dtoList.add(docDetail);
					session.setAttribute("miscIDStatus", dtoList);
				}
				//MODIFIED BY SHRUTI FOR EXP PARA 1 AND EXP PARA 2
				else
				{
					docDetail = agmpbd.getRegStatus(agmpreportdetailsdto);
					dtoList.add(docDetail);
					session.setAttribute("docIDStatus", dtoList);
				}
			}
			else
			{
				if (agmpreportdetailsdto.getDocType().equalsIgnoreCase("others"))
				{
					docDetail = agmpbd.getDocStatus(agmpreportdetailsdto);
					dtoList.add(docDetail);
					session.setAttribute("docIDStatus", dtoList);
				}
				else 
				{
					docDetail = agmpbd.getRegStatus(agmpreportdetailsdto);
					dtoList.add(docDetail);
					session.setAttribute("docIDStatus", dtoList);
				}
			}
			
			
		}
		/*
		 * Handling Save and Add Button
		 */

		if (str1.equals("addMore")) 
		{
			AGMPReportDetailsDTO[] agmpReportDto = null;
			/* Saving Details in session */
			logger.debug("dd->"+session.getAttribute("dtoList"));
			if (session.getAttribute("dtoList") != null) 
			{
				
				//added by shruti
				ArrayList addDocVal = (ArrayList) session.getAttribute("dtoList");
				String listParaType=agmpreportdetailsdto.getListParaType();
				if(listParaType!=null)
				{
						if(listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01"))
						{
							DocDetails docDetails = new DocDetails();
							docDetails.setValAgmp(agmpreportdetailsdto.getTempValAgmp());
							docDetails.setStampDuty(agmpreportdetailsdto.getTxtStampDuty());
							docDetails.setRegFee(agmpreportdetailsdto.getTxtRegFee());
							docDetails.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
							docDetails.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
							docDetails.setTxtDocID(agmpreportdetailsdto.getTxtDocID());
							docDetails.setMarketVal(agmpreportdetailsdto.getMarketVal());
							addDocVal.add(docDetails);
						}
						else if(listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02"))
						{
							CaseDetails caseDetails = new CaseDetails();
							caseDetails.setTxtCaseID(agmpreportdetailsdto.getTxtCaseID());
							caseDetails.setCaseStatus(agmpreportdetailsdto.getCaseStatus());
							addDocVal.add(caseDetails);
						}
						else if(listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03"))
						{
							DocDetails docDetails = new DocDetails();
							docDetails.setDocStatusId(agmpreportdetailsdto.getDocStatusId());
							docDetails.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
							docDetails.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
							addDocVal.add(docDetails);
						}
				}
				else
				{
					DocDetails docDetails = new DocDetails();
					docDetails.setValAgmp(agmpreportdetailsdto.getTempValAgmp());
					docDetails.setStampDuty(agmpreportdetailsdto.getTxtStampDuty());
					docDetails.setRegFee(agmpreportdetailsdto.getTxtRegFee());
					docDetails.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
					docDetails.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
					docDetails.setTxtDocID(agmpreportdetailsdto.getTxtDocID());
					docDetails.setMarketVal(agmpreportdetailsdto.getMarketVal());
					addDocVal.add(docDetails);
				}
					
				//end
				session.removeAttribute("dtoList");
				session.setAttribute("dtoList", addDocVal);
				
				if (auditBody.equalsIgnoreCase("AGMP") && auditType.equalsIgnoreCase("Receipt")
						&& auditReport.equalsIgnoreCase("new"))
				{

							if (listParaType != null) {
								if (listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01")) 
								{
									strForward = "addDocAgmp1";
								}
								else if (listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02")) {
									strForward = "addPendingDocAgmp1";
								}
								else if (listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03")) {
									strForward = "addMiscDocAgmp1";
								}
							} 
							else {
								strForward = "addDocAgmp1";
							}
					
				} 
				
				else if (auditBody.equalsIgnoreCase("internal") && auditType.equalsIgnoreCase("Receipt")&& auditReport.equalsIgnoreCase("new")) 
				{
					//listParaType=agmpreportdetailsdto.getListParaType();
					//logger.info("BEFORE REDIRECT>>>>>>>>"+listParaType);
					if (listParaType != null) {
						if (listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01")) 
						{
							strForward = "addDocInternalReciept";
						}
						else if (listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02")) {
							strForward = "addPendingDocInternalReciept";
						}
						else if (listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03")) {
							strForward = "addMiscDocInternalReciept";
						}
					} 
					else {
						strForward = "addDocInternalReciept";
					}
					//strForward = "addDocInternalReciept";
				} 
				
				else if (auditBody.equalsIgnoreCase("AGMP") && auditType.equalsIgnoreCase("Expenditure")&& auditReport.equalsIgnoreCase("new")) 
				{
					strForward = "addDocAgmp";
				} 
				
				else if (auditBody.equalsIgnoreCase("Internal") && auditType.equalsIgnoreCase("Expenditure")&& auditReport.equalsIgnoreCase("new")) {
					strForward = "addDocInternalExp";
				}
				//on click of ADD MORE IN CASE OF EXISTING REPORT
				else if (auditBody.equalsIgnoreCase("AGMP") && auditType.equalsIgnoreCase("Receipt")&& auditReport.equalsIgnoreCase("existing"))
				{
					listParaType=agmpreportdetailsdto.getListParaType();
					logger.info("BEFORE REDIRECT>>>>>>>>"+listParaType);
					if(listParaType!=null)
					{
							if(listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01"))
							{
								strForward = "existAddDoc1";
							}
							else if(listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02"))
							{
								strForward = "existAddDoc2";
							}
							else if(listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03"))
							{
								strForward = "existAddDoc3";
							}
					}
					else
					{
						strForward = "existAddDoc1";
					}
					//end
				} 
				
				else if (auditBody.equalsIgnoreCase("internal") && auditType.equalsIgnoreCase("Receipt")&& auditReport.equalsIgnoreCase("existing"))
				{
					listParaType=agmpreportdetailsdto.getListParaType();
					logger.info("BEFORE REDIRECT>>>>>>>>"+listParaType);
					if(listParaType!=null)
					{
							if(listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01"))
							{
								strForward = "existInternalAddDoc";
							}
							else if(listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02"))
							{
								strForward = "existInternalAddPendingDoc";
							}
							else if(listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03"))
							{
								strForward = "existInternalAddMiscDoc";
							}
					}
					else
					{
						strForward = "existInternalAddDoc";
					}
					agmpreportdetailsdto.setTxtStampDuty(""); //
					agmpreportdetailsdto.setTxtRegFee("");    //
					agmpreportdetailsdto.setTempValAgmp("");  //
					//strForward = "existInternalAddDoc";
				} 
				else if (auditBody.equalsIgnoreCase("AGMP") && auditType.equalsIgnoreCase("Expenditure")&& auditReport.equalsIgnoreCase("existing")) 
				{
					strForward = "existExpAgmpAddDoc";
				} 
				else if (auditBody.equalsIgnoreCase("Internal")&& auditType.equalsIgnoreCase("Expenditure")&& auditReport.equalsIgnoreCase("existing"))
				{
					strForward = "ExistExpIntAddDoc1";
				}
			} 
			else
			{
				ArrayList addDocList = new ArrayList();
				//modified by shruti-12th sep 2013
				if(agmpreportdetailsdto.getListParaType()!=null)
				{
						if(agmpreportdetailsdto.getListParaType().equalsIgnoreCase("documents")||agmpreportdetailsdto.getListParaType().equalsIgnoreCase("दस्तावेज़")||agmpreportdetailsdto.getListParaType().equalsIgnoreCase("P01"))
						{
							agmpReportDto = agmpbd.getDocIdStatus(agmpreportdetailsdto);
							DocDetails docDetails2 = new DocDetails();
							docDetails2.setValAgmp(agmpreportdetailsdto.getTempValAgmp());
							docDetails2.setStampDuty(agmpreportdetailsdto.getTxtStampDuty());
							docDetails2.setRegFee(agmpreportdetailsdto.getTxtRegFee());
							docDetails2.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
							docDetails2.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
							docDetails2.setTxtDocID(agmpreportdetailsdto.getTxtDocID());
							docDetails2.setMarketVal(agmpreportdetailsdto.getMarketVal());
							addDocList.add(docDetails2);
						}
						else if(agmpreportdetailsdto.getListParaType().equalsIgnoreCase("Pending cases")||agmpreportdetailsdto.getListParaType().equalsIgnoreCase("अनिर्णीत मामले")||agmpreportdetailsdto.getListParaType().equalsIgnoreCase("P02"))
						{
							agmpReportDto = agmpbd.getCaseIdStatus(agmpreportdetailsdto);
							CaseDetails caseDetails2 = new CaseDetails();
							caseDetails2.setTxtCaseID(agmpreportdetailsdto.getTxtCaseID());
							caseDetails2.setCaseStatus(agmpreportdetailsdto.getCaseStatus());
							addDocList.add(caseDetails2);
						}
						else if(agmpreportdetailsdto.getListParaType().equalsIgnoreCase("Miscellaneous")||agmpreportdetailsdto.getListParaType().equalsIgnoreCase("विविध")||agmpreportdetailsdto.getListParaType().equalsIgnoreCase("P03"))
						{
							DocDetails docDetails3 = new DocDetails();
							docDetails3.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
							docDetails3.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
							docDetails3.setDocStatusId(agmpreportdetailsdto.getDocStatusId());
							addDocList.add(docDetails3);
						}	
						//exp PARA 1 AND EXP PARA 2--ADDED BY SHRUTI---4 FEB 2014
						else
						{
							agmpReportDto = agmpbd.getDocIdStatus(agmpreportdetailsdto);
							DocDetails docDetails2 = new DocDetails();
							docDetails2.setValAgmp(agmpreportdetailsdto.getTempValAgmp());
							docDetails2.setStampDuty(agmpreportdetailsdto.getTxtStampDuty());
							docDetails2.setRegFee(agmpreportdetailsdto.getTxtRegFee());
							docDetails2.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
							docDetails2.setTxtDocID(agmpreportdetailsdto.getTxtDocID());
							docDetails2.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
							docDetails2.setMarketVal(agmpreportdetailsdto.getMarketVal());
							addDocList.add(docDetails2);
						}
				}
				else
				{
					agmpReportDto = agmpbd.getDocIdStatus(agmpreportdetailsdto);
					DocDetails docDetails2 = new DocDetails();
					docDetails2.setValAgmp(agmpreportdetailsdto.getTempValAgmp());
					docDetails2.setStampDuty(agmpreportdetailsdto.getTxtStampDuty());
					docDetails2.setRegFee(agmpreportdetailsdto.getTxtRegFee());
					docDetails2.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
					docDetails2.setTxtDocID(agmpreportdetailsdto.getTxtDocID());
					docDetails2.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
					docDetails2.setMarketVal(agmpreportdetailsdto.getMarketVal());
					addDocList.add(docDetails2);
				}
				session.setAttribute("dtoList", addDocList);
				
				
				if (auditBody.equalsIgnoreCase("AGMP") && auditType.equalsIgnoreCase("Receipt")&& auditReport.equalsIgnoreCase("new")) 
				{
					// strForward = "addDocAgmp1";
					ArrayList auditParaDtlsList;
					ArrayList Paralist;
					try {
						String language=(String)session.getAttribute("languageLocale");
						auditParaDtlsList = agmpbd.getReceiptParaDtlsList(agmpreportdetailsdto.getTxnId());
						session.setAttribute("auditParaDtlsList",auditParaDtlsList);
						Paralist = agmpbd.getReceiptParaList(language);
						session.setAttribute("Paralist", Paralist);

						String listParaType = agmpreportdetailsdto.getListParaType();
						logger.info("LIST PARA TYPE>>>>>>>>> ON ADD MORE"+ listParaType);
						if (listParaType != null) {
							if (listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01")) 
							{
								strForward = "addDocAgmp1";
							}
							else if (listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02")) {
								strForward = "addPendingDocAgmp1";
							}
							else if (listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03")) {
								strForward = "addMiscDocAgmp1";
							}
						} else {
							strForward = "addDocAgmp1";
						}
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
				else if (auditBody.equalsIgnoreCase("internal") && auditType.equalsIgnoreCase("Receipt")&& auditReport.equalsIgnoreCase("new")) 
				{
					ArrayList auditParaDtlsList;
					ArrayList Paralist;
					try {
						String language=(String)session.getAttribute("languageLocale");
						auditParaDtlsList = agmpbd.getReceiptParaDtlsList(agmpreportdetailsdto.getTxnId());
						session.setAttribute("auditParaDtlsList",auditParaDtlsList);
						Paralist = agmpbd.getReceiptParaList(language);
						session.setAttribute("Paralist", Paralist);

						String listParaType = agmpreportdetailsdto.getListParaType();
						logger.info("LIST PARA TYPE>>>>>>>>> ON ADD MORE"+ listParaType);
						if (listParaType != null) {
							if (listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01")) 
							{
								strForward = "addDocInternalReciept";
							}
							else if (listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02")) {
								strForward = "addPendingDocInternalReciept";
							}
							else if (listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03")) {
								strForward = "addMiscDocInternalReciept";
							}
						} else {
							strForward = "addDocInternalReciept";
						}
					} catch (Exception e) {

						e.printStackTrace();
					}
					//strForward = "addDocInternalReciept";
				} 
				else if (auditBody.equalsIgnoreCase("AGMP") && auditType.equalsIgnoreCase("Expenditure")&& auditReport.equalsIgnoreCase("new")) 
				{
					strForward = "addDocAgmp";
				} 
				else if (auditBody.equalsIgnoreCase("Internal") && auditType.equalsIgnoreCase("Expenditure")&& auditReport.equalsIgnoreCase("new"))
				{
					strForward = "addDocInternalExp";
				} 
				else if (auditBody.equalsIgnoreCase("AGMP") && auditType.equalsIgnoreCase("Receipt")&& auditReport.equalsIgnoreCase("existing"))
				{
					ArrayList auditParaDtlsList;
					ArrayList Paralist;
					try {
						String language=(String)session.getAttribute("languageLocale");
						auditParaDtlsList = agmpbd.getReceiptParaDtlsList(agmpreportdetailsdto.getTxnId());
						session.setAttribute("auditParaDtlsList", auditParaDtlsList);
						Paralist = agmpbd.getReceiptParaList(language);
						session.setAttribute("Paralist", Paralist);
						
						//modified by shruti-12th sep 2013
						String listParaType=agmpreportdetailsdto.getListParaType();
						logger.info("LIST PARA TYPE>>>>>>>>> ON ADD MORE"+listParaType);
						if(listParaType!=null)
						{
								if(listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01"))
								{
									strForward = "existAddDoc1";
								}
								else if(listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02"))
								{
									strForward = "existAddDoc2";
								}
								else if(listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03"))
								{
									strForward = "existAddDoc3";
								}
						}
						else
						{
							strForward = "existAddDoc1";
						}
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					
				} 
				else if (auditBody.equalsIgnoreCase("internal") && auditType.equalsIgnoreCase("Receipt")&& auditReport.equalsIgnoreCase("existing")) 
				{
					//strForward = "existInternalAddDoc";
					
					String listParaType=agmpreportdetailsdto.getListParaType();
					logger.info("BEFORE REDIRECT>>>>>>>>"+listParaType);
					if(listParaType!=null)
					{
							if(listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01"))
							{
								strForward = "existInternalAddDoc";
							}
							else if(listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02"))
							{
								strForward = "existInternalAddPendingDoc";
							}
							else if(listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03"))
							{
								strForward = "existInternalAddMiscDoc";
							}
					}
					else
					{
						strForward = "existInternalAddDoc";
					}
					agmpreportdetailsdto.setTxtStampDuty(""); //
					agmpreportdetailsdto.setTxtRegFee("");    //
					agmpreportdetailsdto.setTempValAgmp("");  //
					
					
					
				} 
				else if (auditBody.equalsIgnoreCase("AGMP") && auditType.equalsIgnoreCase("Expenditure")&& auditReport.equalsIgnoreCase("existing")) 
				{
					strForward = "existExpAgmpAddDoc";
				}
				else if (auditBody.equalsIgnoreCase("Internal") && auditType.equalsIgnoreCase("Expenditure")&& auditReport.equalsIgnoreCase("existing"))
				{
					strForward = "ExistExpIntAddDoc1";
				}
			}

		}
		
		/* Handling Save Button */
		else if (str1.equals("Save")) 
		{
			try 
			{
				if (session.getAttribute("dtoList") == null)
				{
					ArrayList list2 = new ArrayList();
					String listParaType=agmpreportdetailsdto.getListParaType();
					if(listParaType!=null)
					{
					logger.info("IN SAVE >>>>>"+listParaType);
					logger.info("IN SAVE >>>>>"+listParaType);
						if(listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01"))
						{
							DocDetails docDetails1 = new DocDetails();
							docDetails1.setValAgmp(agmpreportdetailsdto.getTempValAgmp());
							docDetails1.setStampDuty(agmpreportdetailsdto.getTxtStampDuty());
							docDetails1.setRegFee(agmpreportdetailsdto.getTxtRegFee());
							docDetails1.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
							docDetails1.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
							docDetails1.setTxtDocID(agmpreportdetailsdto.getTxtDocID());
							docDetails1.setMarketVal(agmpreportdetailsdto.getMarketVal());
							list2.add(docDetails1);
						}
						else if(listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02"))
						{
							CaseDetails caseDetails2 = new CaseDetails();
							caseDetails2.setTxtCaseID(agmpreportdetailsdto.getTxtCaseID());
							caseDetails2.setCaseStatus(agmpreportdetailsdto.getCaseStatus());
							caseDetails2.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
							list2.add(caseDetails2);
						}
						else if(listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03"))
						{
							DocDetails docDetails3 = new DocDetails();
							docDetails3.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
							docDetails3.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
							docDetails3.setDocStatusId(agmpreportdetailsdto.getDocStatusId());
							list2.add(docDetails3);
						}
					}
					else
					{
					DocDetails docDetails1 = new DocDetails();
					docDetails1.setValAgmp(agmpreportdetailsdto.getTempValAgmp());
					docDetails1.setStampDuty(agmpreportdetailsdto.getTxtStampDuty());
					docDetails1.setRegFee(agmpreportdetailsdto.getTxtRegFee());
					docDetails1.setTxtDocID(agmpreportdetailsdto.getTxtDocID());
					docDetails1.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
					docDetails1.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
					docDetails1.setMarketVal(agmpreportdetailsdto.getMarketVal());
					list2.add(docDetails1);
					}
					
					session.setAttribute("dtoList", list2);
				} 
				else
				{
					ArrayList list1 = (ArrayList) session.getAttribute("dtoList");
					
					String listParaType=agmpreportdetailsdto.getListParaType();
					if(listParaType!=null)
					{
						//modified by shruti-14th sep 2013
						logger.info("IN SAVE >>>>>"+listParaType);
						if(listParaType.equalsIgnoreCase("documents")||listParaType.equalsIgnoreCase("दस्तावेज़")||listParaType.equalsIgnoreCase("P01"))
						{
							DocDetails docDetails1 = new DocDetails();
							docDetails1.setValAgmp(agmpreportdetailsdto.getTempValAgmp());
							docDetails1.setStampDuty(agmpreportdetailsdto.getTxtStampDuty());
							docDetails1.setRegFee(agmpreportdetailsdto.getTxtRegFee());
							docDetails1.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
							docDetails1.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
							//added by shruti
							docDetails1.setTxtDocID(agmpreportdetailsdto.getTxtDocID());
							docDetails1.setDocStatusId("OPEN");
							docDetails1.setCaseDocID("");
							docDetails1.setMarketVal(agmpreportdetailsdto.getMarketVal());
							//end
							list1.add(docDetails1);
						}
						else if(listParaType.equalsIgnoreCase("Pending Cases")||listParaType.equalsIgnoreCase("अनिर्णीत मामले")||listParaType.equalsIgnoreCase("P02"))
						{
							CaseDetails caseDetails2 = new CaseDetails();
							
							caseDetails2.setTxtCaseID(agmpreportdetailsdto.getTxtCaseID());
							caseDetails2.setCaseStatus(agmpreportdetailsdto.getCaseStatus());
							list1.add(caseDetails2);
						}
						else if(listParaType.equalsIgnoreCase("Miscellaneous")||listParaType.equalsIgnoreCase("विविध")||listParaType.equalsIgnoreCase("P03"))
						{
							DocDetails docDetails3 = new DocDetails();
							docDetails3.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
							docDetails3.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
							docDetails3.setDocStatusId(agmpreportdetailsdto.getDocStatusId());
							list1.add(docDetails3);
						}
						//end
					}
					else
					{
					DocDetails docDetails1 = new DocDetails();
					docDetails1.setValAgmp(agmpreportdetailsdto.getTempValAgmp());
					docDetails1.setStampDuty(agmpreportdetailsdto.getTxtStampDuty());
					docDetails1.setRegFee(agmpreportdetailsdto.getTxtRegFee());
					docDetails1.setObjDetails(agmpreportdetailsdto.getTxtObjDetails());
					docDetails1.setTxtAgmpComm(agmpreportdetailsdto.getTxtAgmpComm());
					docDetails1.setTxtDocID(agmpreportdetailsdto.getTxtDocID());
					docDetails1.setMarketVal(agmpreportdetailsdto.getMarketVal());
					list1.add(docDetails1);
					}

				
					session.removeAttribute("dtoList");
					session.setAttribute("dtoList", list1);
				}
				ArrayList docAgmpList = (ArrayList) session.getAttribute("dtoList");
				ArrayList listFileNames = (ArrayList) session.getAttribute("attachment1");
				String strFilePath = (String) session.getAttribute("FilePath");
				
				if("AGMP".equalsIgnoreCase(auditBody)&&"RECEIPT".equalsIgnoreCase(auditType)&&"NEW".equalsIgnoreCase(auditReport))
				{
					String strUserId = (String) session.getAttribute("UserId");
					String officeId=(String)session.getAttribute("loggedToOffice");
					
					result = agmpbd.submitAGMPRcptReport(agmpreportdetailsdto,
							listFileNames, strFilePath, docAgmpList,strUserId, officeId);
					
					for (int i = 0; i < listFileNames.size(); i++) 
					{
						
						UploadFileDTO uploadFormDTO = (UploadFileDTO) listFileNames.get(i);
						File newFile = new File(strFilePath+ uploadFormDTO.getFileName());
						newFile.delete();
					}
				}
				else if("AGMP".equalsIgnoreCase(auditBody)&&"EXPENDITURE".equalsIgnoreCase(auditType)&&"NEW".equalsIgnoreCase(auditReport))
				{
					String strUserId = (String) session.getAttribute("UserId");
					String officeId=(String)session.getAttribute("loggedToOffice");
					
					result = agmpbd.submitAGMPExpReport(agmpreportdetailsdto,
							listFileNames, strFilePath, docAgmpList,strUserId, officeId);
					
					for (int i = 0; i < listFileNames.size(); i++) 
					{
						
						UploadFileDTO uploadFormDTO = (UploadFileDTO) listFileNames.get(i);
						File newFile = new File(strFilePath+ uploadFormDTO.getFileName());
						newFile.delete();
					}
				}
				//ADDED BY SHRUTI----13 JUNE 2014--FOR NEW,EXPENDITURE AND INTERNAL
				else if("INTERNAL".equalsIgnoreCase(auditBody)&&"EXPENDITURE".equalsIgnoreCase(auditType)&&"NEW".equalsIgnoreCase(auditReport))
				{
					String strUserId = (String) session.getAttribute("UserId");
					String officeId=(String)session.getAttribute("loggedToOffice");
					
					result = agmpbd.submitInternalExpReport(agmpreportdetailsdto,
							listFileNames, strFilePath, docAgmpList,strUserId, officeId);
					
					for (int i = 0; i < listFileNames.size(); i++) 
					{
						
						UploadFileDTO uploadFormDTO = (UploadFileDTO) listFileNames.get(i);
						File newFile = new File(strFilePath+ uploadFormDTO.getFileName());
						newFile.delete();
					}
				}
				else if("INTERNAL".equalsIgnoreCase(auditBody)&&"RECEIPT".equalsIgnoreCase(auditType)&&"NEW".equalsIgnoreCase(auditReport))
				{
					String strUserId = (String) session.getAttribute("UserId");
					String officeId=(String)session.getAttribute("loggedToOffice");
					
					result = agmpbd.submitInternalRcptReport(agmpreportdetailsdto,
							listFileNames, strFilePath, docAgmpList,strUserId, officeId);
					
					for (int i = 0; i < listFileNames.size(); i++) 
					{
						
						UploadFileDTO uploadFormDTO = (UploadFileDTO) listFileNames.get(i);
						File newFile = new File(strFilePath+ uploadFormDTO.getFileName());
						newFile.delete();
					}
				}

				/*if (auditReport.equalsIgnoreCase("new"))
				{
					String strUserId = (String) session.getAttribute("UserId");
					String officeId=(String)session.getAttribute("loggedToOffice");
					result = agmpbd.submitAGMPReport(agmpreportdetailsdto,
							listFileNames, strFilePath, docAgmpList, strUserId);
					result = agmpbd.submitAGMPReport(agmpreportdetailsdto,
							listFileNames, strFilePath, docAgmpList,strUserId, officeId);
					for (int i = 0; i < listFileNames.size(); i++) 
					{
						
						UploadFileDTO uploadFormDTO = (UploadFileDTO) listFileNames.get(i);
						File newFile = new File(strFilePath+ uploadFormDTO.getFileName());
						newFile.delete();
					}
				} */
				else if("EXISTING".equalsIgnoreCase(auditReport))
				{
					res = agmpbd.submitExistingAGMPReport(agmpreportdetailsdto,docAgmpList);
				}
				session.removeAttribute("dtoList");
				session.removeAttribute("attachment1");
				session.removeAttribute("ListFilePath");
				} 
			catch (Exception e) 
			{
				e.printStackTrace();
				}
			if (!result.equalsIgnoreCase("false")) 
			{
				
				agmpreportdetailsdto.setTxnId(result);
				if (auditReport.equalsIgnoreCase("new") && auditBody.equalsIgnoreCase("AGMP")&& auditType.equalsIgnoreCase("receipt")) 
				{
					try 
					{
						String language=(String)session.getAttribute("languageLocale");
						ArrayList Paralist = agmpbd.getReceiptParaList(language);
						session.setAttribute("Paralist", Paralist);
						strForward = "ReceiptAgmpFinal";
					} 
					catch (Exception e) 
					{
						
					}
					
				} 
				else if (auditReport.equalsIgnoreCase("new") && auditBody.equalsIgnoreCase("internal")&& auditType.equalsIgnoreCase("receipt"))
				{					
					try 
					{
						String language=(String)session.getAttribute("languageLocale");
						ArrayList Paralist = agmpbd.getReceiptParaList(language);
						session.setAttribute("Paralist", Paralist);
						strForward = "addParaInternal1";
					} 
					catch (Exception e) 
					{
						// TODO: handle exception
					}
				}
				else if (auditReport.equalsIgnoreCase("new") && auditBody.equalsIgnoreCase("agmp")&& auditType.equalsIgnoreCase("expenditure")) 
				{
					try 
					{
						ArrayList Paralist = agmpbd.getExpenParaList();
						session.setAttribute("Paralist", Paralist);
						strForward = "ExpAgmpFinal";
					} 
					catch (Exception e) 
					{
						// TODO: handle exception
					}
				} 
				else if (auditReport.equalsIgnoreCase("new") && auditBody.equalsIgnoreCase("internal")&& auditType.equalsIgnoreCase("expenditure")) 
				{
					try 
					{
						ArrayList Paralist = agmpbd.getExpenParaList();
						session.setAttribute("Paralist", Paralist);
						strForward = "ExpInternalFinal";
					}
					catch (Exception e)
					{
						// TODO: handle exception
					}
				}
				
				
			} 
			else if (res)
			{
				if (auditReport.equalsIgnoreCase("existing") && auditBody.equalsIgnoreCase("agmp")&& auditType.equalsIgnoreCase("receipt")) 
				{
					try 
					{
						String language=(String)session.getAttribute("languageLocale");
						ArrayList Paralist = agmpbd.getReceiptParaList(language);
						session.setAttribute("Paralist", Paralist);
					}
					catch (Exception e) 
					{
						// TODO: handle exception
					}
					strForward = "addPara01_1";
				} 
				else if (auditReport.equalsIgnoreCase("existing")&& auditBody.equalsIgnoreCase("internal")&& auditType.equalsIgnoreCase("receipt"))
				{
					try 
					{
						String language=(String)session.getAttribute("languageLocale");
						ArrayList Paralist = agmpbd.getReceiptParaList(language);
						session.setAttribute("Paralist", Paralist);
					} 
					catch (Exception e)
					{
						// TODO: handle exception
					}
					strForward = "addParaIntFinal";
				} 
				else if (auditReport.equalsIgnoreCase("existing") && auditBody.equalsIgnoreCase("agmp")&& auditType.equalsIgnoreCase("expenditure")) 
				{
					try 
					{
						ArrayList Paralist = agmpbd.getExpenParaList();
						session.setAttribute("Paralist", Paralist);					
					} 
					catch (Exception e)
					{
						// TODO: handle exception
					}
					strForward = "ExpExistFinal";
				} 
				else if (auditReport.equalsIgnoreCase("existing") && auditBody.equalsIgnoreCase("internal")&& auditType.equalsIgnoreCase("expenditure"))
				{
					try 
					{
						ArrayList Paralist = agmpbd.getExpenParaList();
						session.setAttribute("Paralist", Paralist);					
					} 
					catch (Exception e) 
					{
						// TODO: handle exception
					}
					strForward = "ExistingExpInternalFinal";
				}

			}
		}

		return strForward;

	}

	/** End Of the Method* */

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param session
	 * @param rule
	 * @param errorList
	 * @param agmpbd
	 * @return
	 */
	private String existingExpAuditPara(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto, HttpSession session,
			AuditInspectionRule rule, ArrayList errorList,
			AGMPReportDetailsBD agmpbd) 
	{
		String strForward = null;
		
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			request.setAttribute("errorsList", errorList);
			strForward = "ExistingExpAgmpDtl";
		}
		if (agmpreportdetailsdto.getIsFinalPage().equalsIgnoreCase("true")) 
		{
			String language=(String)session.getAttribute("languageLocale");
			agmpreportdetailsdto.setReport("EXISTING");
			session.removeAttribute("docIDStatus");
			String txnId = agmpreportdetailsdto.getTxnId();
			agmpreportdetailsdto = agmpbd.getExistingDetails(txnId,language);
			session.setAttribute("existingDetails", agmpreportdetailsdto);
		}

		strForward = "ExistingExpAddAudit";

		return strForward;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param rule
	 * @param errorList
	 * @return
	 */
	private String existingAddAuditPara(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AuditInspectionRule rule, ArrayList errorList) 
	{
		String strForward = null;
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			request.setAttribute("errorsList", errorList);
			strForward = "ExistingExpAddAudit";
		}
		strForward = "ExistExpAddDoc";

		return strForward;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param session
	 * @param rule
	 * @param errorList
	 * @param agmpbd
	 * @return
	 */
	private String existingExpIntAddPara(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto, HttpSession session,
			AuditInspectionRule rule, ArrayList errorList,
			AGMPReportDetailsBD agmpbd) 
	{
		String strForward = null;

		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			request.setAttribute("errorsList", errorList);
			strForward = "ExistingExpInternalDtl";
		}
		if (agmpreportdetailsdto.getIsFinalPage().equalsIgnoreCase("true")) 
		{
			String language=(String)session.getAttribute("languageLocale");
			agmpreportdetailsdto.setReport("EXISTING");
			session.removeAttribute("docIDStatus");
			String txnId = agmpreportdetailsdto.getTxnId();
			agmpreportdetailsdto = agmpbd.getExistingDetails(txnId,language);
			session.setAttribute("existingDetails", agmpreportdetailsdto);
		}
		strForward = "ExistingExpIntAddAudit";

		return strForward;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param rule
	 * @param errorList
	 * @return
	 */
	private String existingExpIntAddPara(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AuditInspectionRule rule, ArrayList errorList) 
	{
		String strForward = null;
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			request.setAttribute("errorsList", errorList);
			strForward = "ExistingExpIntAddAudit";
		}
		strForward = "ExistExpIntAddDoc";
		return strForward;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param rule
	 * @param errorList
	 * @return
	 */
	private String newReceiptAddAuditParaInternal(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AuditInspectionRule rule, ArrayList errorList)
	{
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			request.setAttribute("errorsList", errorList);
			return "addIntAuditPara2";
		}
		if (isCancelled(request)) 
		{
			return "newagmp";
		}
		String paraType=agmpreportdetailsdto.getListParaType();
		if(paraType.equalsIgnoreCase("documents")||paraType.equalsIgnoreCase("दस्तावेज़")||paraType.equalsIgnoreCase("P01"))
		{
			return "addDocDetails";
		}
		else if(paraType.equalsIgnoreCase("Pending Cases")||paraType.equalsIgnoreCase("अनिर्णीत मामले")||paraType.equalsIgnoreCase("P02"))
		{
			return "addPendingDocDetails";
		}
		else if(paraType.equalsIgnoreCase("miscellaneous")||paraType.equalsIgnoreCase("विविध")||paraType.equalsIgnoreCase("P03"))
		{
			return "addMiscDocDetails";
		}
		else
		return "addDocDetails";
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param session
	 * @param rule
	 * @param errorList
	 * @return
	 */
	private String newReceiptAuditAddDocInternal(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto, HttpSession session,
			AuditInspectionRule rule, ArrayList errorList) {
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			if (session.getAttribute("attachment1") == null) 
			{
				errorList.add("<li><font color=" + "red"+ ">Please Attach Documents</font></li>");
			}
			request.setAttribute("errorsList", errorList);
			return "newreportinternal";
		}
		if (isCancelled(request))
		{
			return "newagmp";
		}

		if (session.getAttribute("attachment1") == null) 
		{
			errorList.add("<li><font color=" + "red"+ ">Please Attach Documents</font></li>");
			request.setAttribute("errorsList", errorList);
			return "newreportinternal";
		}

		return "addIntAuditPara2";
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param rule
	 * @param errorList
	 * @return
	 */
	private String receiptAddAuditParaAction(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AuditInspectionRule rule, ArrayList errorList) 
	{
		String strReturnVal = null;
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			request.setAttribute("errorsList", errorList);
			strReturnVal = "newauditpara";
		}
		else if (isCancelled(request)) 
		{
			strReturnVal = "newagmp";
		}
		
		String paraType=agmpreportdetailsdto.getListParaType();
		if(paraType.equalsIgnoreCase("documents")||paraType.equalsIgnoreCase("दस्तावेज़")||paraType.equalsIgnoreCase("P01"))
		{
			strReturnVal = "addDocRcpt";
		}
		else if(paraType.equalsIgnoreCase("Pending Cases")||paraType.equalsIgnoreCase("अनिर्णीत मामले")||paraType.equalsIgnoreCase("P02"))
		{
			strReturnVal ="addPendingDocRcpt";
		}
		else if(paraType.equalsIgnoreCase("miscellaneous")||paraType.equalsIgnoreCase("विविध")||paraType.equalsIgnoreCase("P03"))
		{
			strReturnVal ="addMiscDocRcpt";
		}
		else
			strReturnVal = "addDocRcpt";
		return strReturnVal;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param agmpbd
	 * @return
	 */
	private String regIdSearchAction(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AGMPReportDetailsBD agmpbd,HttpSession session) 
	{
		String strForward = null;
		ArrayList searchlist = new ArrayList();
	
		if (agmpreportdetailsdto.getDocType().equalsIgnoreCase("Regid")) 
		{
			if (agmpreportdetailsdto.getRegId().length() < 15) 
			{
				searchlist = agmpbd.getRegDetails(agmpreportdetailsdto);
				RegIdSearch regId[] = new RegIdSearch[searchlist.size()];
				for (int i = 0; i < regId.length; i++) 
				{
					regId[i] = new RegIdSearch();
				}
				for (int i = 0; i < searchlist.size(); i++) 
				{
					ArrayList row_list = (ArrayList) searchlist.get(i);
					String reg = (String) row_list.get(0);
					String reg1 = (String) row_list.get(1);

					regId[i].setRegId(reg);
					regId[i].setRegDate(reg1);

				}

				if (regId.length != 0) 
				{
					agmpreportdetailsdto.setRegIdsearch(regId);
				}
				request.setAttribute("searchlist", regId);
				if(searchlist==null || !(searchlist.size()>0)){
					request.setAttribute("searchlist", null);
					request.setAttribute("errMsg", "Invalid Registration Number./अवैध पंजीकरण संख्या।");
				}
				strForward = "regId";
			}
		
			if (agmpreportdetailsdto.getRegId().length() >= 15) 
			{
				String userid=(String)session.getAttribute("UserId");
				RegIdOtherSearch regIdOthers = agmpbd.getRegIdDetails(agmpreportdetailsdto,userid);
				request.setAttribute("regidOthers", regIdOthers);
				strForward = "regdetails";

			}
		}
		return strForward;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param agmpbd
	 * @return
	 */
	private String searchOtherDetails(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AGMPReportDetailsBD agmpbd) {
		
		if (agmpreportdetailsdto.getRegId().length() >=18)
		{
			RegIdOtherSearch regIdOthers = agmpbd.getRegIdOtherDetails(agmpreportdetailsdto);
			request.setAttribute("regidOthers", regIdOthers);
			return "regothers";
		} 
		else
		{
			ArrayList docidlist = new ArrayList();
			docidlist = agmpbd.getDocIdDetails(agmpreportdetailsdto);
			RegIdOtherSearch docid[] = new RegIdOtherSearch[docidlist.size()];
			for (int i = 0; i < docid.length; i++)
			{
				docid[i] = new RegIdOtherSearch();
			}
			for (int i = 0; i < docidlist.size(); i++) 
			{
				ArrayList row_list = (ArrayList) docidlist.get(i);
				String doc_id = (String) row_list.get(0);
				String doc_date = (String) row_list.get(1);
				docid[i].setFilling_id(doc_id);
				docid[i].setFilling_date(doc_date);
			}
			if (docid.length != 0) 
			{
				agmpreportdetailsdto.setRegIdOthers(docid);
				request.setAttribute("docidlist", docid);
			}
			
			if(docid==null || !(docid.length>0)){
				request.setAttribute("docidlist", null);
				request.setAttribute("errMsg", "Invalid Document ID./अवैध पंजीकरण संख्या।");
			}
			
			return "documentids";
		}
	}

	/**
	 * @param request
	 * @param agmpdto
	 * @param rule
	 * @param errorList
	 * @param session
	 * @return
	 */
	private String newExpAgmpForm(HttpServletRequest request,
			AGMPReportDetailsDTO agmpdto, AuditInspectionRule rule,
			ArrayList errorList, HttpSession session)
	{
		String forwardPage = null;
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpdto);
		if (rule.isError()) 
		{
			request.setAttribute("errorsList", errorList);
			forwardPage = "ExpAuditNew";
		}
		String sroId = agmpdto.getSroId();
		AGMPReportDetailsDTO tempList = (AGMPReportDetailsDTO) session.getAttribute("SRODetails1");
		ArrayList l1 = tempList.getSroList();
		
		for (int i = 0; i < l1.size(); i++) 
		{
			AGMPReportDetailsDTO list = (AGMPReportDetailsDTO) l1.get(i);
			if (list.getSroId().equals(sroId)) 
			{
				String name = list.getSroName();
				agmpdto.setSroName(name);
				break;
			}
		}
		forwardPage = "success1"; 
		//forwardPage="NewExpAuditPara";
		return forwardPage;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param rule
	 * @param errorList
	 * @param session
	 * @return
	 */
	private String newExpAuditReportForm(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AuditInspectionRule rule, ArrayList errorList, HttpSession session) 
	{
		String forwardPage = null;
		if (isCancelled(request)) 
		{
			forwardPage = "NewExpenditureAudit";
		}
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			if (session.getAttribute("attachment1") == null) 
			{
				errorList.add("<li><font color=" + "red"+ ">Please Attach Documents</font></li>");
			} 
			else if (((ArrayList) session.getAttribute("attachment1")).size() < 1)
			{
				errorList.add("<li><font color=" + "red"+ ">Please Attach Documents</font></li>");
			}
			request.setAttribute("errorsList", errorList);
			forwardPage = "success1";
		}

		if (session.getAttribute("attachment1") == null)
		{
			errorList.add("<li><font color=" + "red"+ ">Please Attach Documents</font></li>");
			request.setAttribute("errorsList", errorList);
			forwardPage = "success1";
		} 
		else
		{
			ArrayList list = (ArrayList) session.getAttribute("attachment1");
			if (list.size() < 1) 
			{
				errorList.add("<li><font color=" + "red"+ ">Please Attach Documents</font></li>");
				request.setAttribute("errorsList", errorList);
				forwardPage = "success";
			} 
			else
				forwardPage = "NewExpAuditPara";
		}

		return forwardPage;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param rule
	 * @param errorList
	 * @param session
	 * @return
	 */
	private String newExpAddDocForm(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AuditInspectionRule rule, ArrayList errorList, HttpSession session) 
	{
		String forwardPage = null;
		if (isCancelled(request)) 
		{
			forwardPage = "newagmp";
		}
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			request.setAttribute("errorsList", errorList);
			forwardPage = "NewExpAuditPara";
		}

		forwardPage = "AddDocExp";
		return forwardPage;
	}

	/**
	 * @param request
	 * @param agmpdto
	 * @param rule
	 * @param errorList
	 * @param session
	 * @return
	 */
	private String newIntExpForm(HttpServletRequest request,
			AGMPReportDetailsDTO agmpdto, AuditInspectionRule rule,
			ArrayList errorList, HttpSession session) 
	{
		String forwardPage = null;
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpdto);
		if (isCancelled(request)) 
		{
			forwardPage = "newagmp";
		}
		else if (rule.isError()) 
		{
			request.setAttribute("errorsList", errorList);
			forwardPage = "InternalExpAudit";
		} 
		else 
		{
			String sroId = agmpdto.getSroId();
			AGMPReportDetailsDTO tempList = (AGMPReportDetailsDTO) session.getAttribute("SRODetails1");
			ArrayList l1 = tempList.getSroList();
			try 
			{
				AGMPReportDetailsBD agmpbd = new AGMPReportDetailsBD();
				ArrayList Paralist = agmpbd.getExpenParaList();
				session.setAttribute("Paralist", Paralist);
			
			} catch (Exception e)
			{
				// TODO: handle exception
			}
			for (int i = 0; i < l1.size(); i++) 
			{
				AGMPReportDetailsDTO list = (AGMPReportDetailsDTO) l1.get(i);
				if (list.getSroId().equals(sroId)) 
				{
					String name = list.getSroName();
					agmpdto.setSroName(name);
					break;
				}
			}
			forwardPage = "successfull";
		}
		return forwardPage;
	}

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param rule
	 * @param errorList
	 * @param session
	 * @return
	 */
	private String newExpIntAddDocForm(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AuditInspectionRule rule, ArrayList errorList, HttpSession session) 
	{
		String forwardPage = null;
		if (isCancelled(request)) 
		{
			forwardPage = "newagmp";
		}
		rule = new AuditInspectionRule();
		errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			request.setAttribute("errorsList", errorList);
			forwardPage = "NewIntExpAuditPara";
		}

		forwardPage = "AddDocIntExp";
		return forwardPage;
	}// END OF METHOD

	/**
	 * @param request
	 * @param agmpreportdetailsdto
	 * @param session
	 * @param rule
	 * @param errorList
	 * @return
	 */
	private String newIntExpAuditReport(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto, HttpSession session,
			AuditInspectionRule rule, ArrayList errorList) 
	{
		String fwdPage = null;
		if (isCancelled(request))
		{
			fwdPage = "newagmp";
		}
		rule = new AuditInspectionRule();
		//modified by shruti---13 june 2014
		//errorList = rule.validateAudit(agmpreportdetailsdto);
		if (rule.isError()) 
		{
			if (session.getAttribute("attachment1") == null) 
			{
				errorList.add("<li><font color=red>Please Attach Documents</font></li>");
			}
			request.setAttribute("errorsList", errorList);
			fwdPage = "successfull";
		}
		if (session.getAttribute("attachment1") == null)
		{
			fwdPage = "successfull";
		} 
		else 
		{
			ArrayList list = (ArrayList) session.getAttribute("attachment1");
			if (list.size() < 1)
			{
				errorList.add("<li><font color=red>Please Attach Documents</font></li>");
				request.setAttribute("errorsList", errorList);
				fwdPage = "successfull";
			} 
			else
				fwdPage = "NewIntExpAuditPara";
		}
		return fwdPage;
	}// END OF METHOD
	
	
	// added by shruti-----11th sep 2013
	private String caseIdSearchAction(HttpServletRequest request,
			AGMPReportDetailsDTO agmpreportdetailsdto,
			AGMPReportDetailsBD agmpbd,HttpSession session) 
	{
		String strForward = null;
		ArrayList searchlist = new ArrayList();
		if (agmpreportdetailsdto.getCaseType().equalsIgnoreCase("CaseId")) 
		{
			if (agmpreportdetailsdto.getCaseId().length() < 10) 
			{
				searchlist = agmpbd.getCaseIdInfo(agmpreportdetailsdto);
				CaseIdSearch caseId[] = new CaseIdSearch[searchlist.size()];
				for (int i = 0; i < caseId.length; i++) 
				{
					caseId[i] = new CaseIdSearch();
				}
				for (int i = 0; i < searchlist.size(); i++) 
				{
					ArrayList row_list = (ArrayList) searchlist.get(i);
					String reg = (String) row_list.get(0);
					String reg1 = (String) row_list.get(1);

					caseId[i].setCaseId(reg);
					caseId[i].setCaseDate(reg1);
				}

				if (caseId.length != 0) 
				{
					agmpreportdetailsdto.setCaseIdsearch(caseId);
					request.setAttribute("searchlist", caseId);
				}
				strForward = "caseId";
			}
			if (agmpreportdetailsdto.getCaseId().length() >= 10) 
			{
				String userid=(String)session.getAttribute("UserId");
				CaseIdOtherSearch caseIdOthers = agmpbd.getCaseIdDetails(agmpreportdetailsdto, userid);
				request.setAttribute("caseidOthers", caseIdOthers);
				strForward = "casedetails";
			}
		}
		return strForward;
	}
	// END OF METHOD

	
	
}
