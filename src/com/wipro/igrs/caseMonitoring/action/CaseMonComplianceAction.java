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
package com.wipro.igrs.caseMonitoring.action;

import in.cdac.ilcg.jasperreports.pdfexporter.JRPdfExporter;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.auditinspection.bd.SROReportDetailsBD;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caseMonitoring.bd.CaseMonBD;
import com.wipro.igrs.caseMonitoring.constant.CommonConstant;
import com.wipro.igrs.caseMonitoring.dto.CaseMonDTO;
import com.wipro.igrs.caseMonitoring.form.CaseMonForm;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.util.PropertiesFileReader;
//Added new common class for validation and mime check
import com.wipro.igrs.common.mime.MIMECheck;

public class CaseMonComplianceAction extends BaseAction {

	String hiLastAction="";
	String lastAction = "";
	String caseTxnId = "";
	String pymtsrid = "";
	ArrayList errorList = new ArrayList();
	ArrayList searchList = new ArrayList();
	String nxtAction = "";
	HashMap mapFile = new HashMap();
	HashMap map = null;
	ArrayList nxtAxnList=null;
	
	private Logger logger = (Logger) Logger.getLogger(CaseMonComplianceAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException, ServletException,
			Exception {

		int count = 0;
		String FORWAD_SUCCESS = "";
		CaseMonDTO cmDTO = null;
		session = request.getSession();
		String roleId = (String) session.getAttribute("role");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String officeId=(String)session.getAttribute("loggedToOffice");
		ActionMessages messages = new ActionMessages();
		CommonConstant caseConstant=new CommonConstant();
		String language=(String)session.getAttribute("languageLocale");
		if(language.equalsIgnoreCase("en")){
			session.setAttribute("modName","Case Monitoring");
			session.setAttribute("fnName","Update");
		}else{
			session.setAttribute("modName","प्रकरण मॉनिटरिंग");
			session.setAttribute("fnName","अद्यतन");
		}
		if (form != null) {
			try {
				CaseMonForm fm = (CaseMonForm) form;
				fm.setLanguage(language);
				CaseMonBD cmBD = new CaseMonBD();
				String fwdName = request.getParameter("fwdName");
				if (fwdName == null){fwdName = "";}
				cmDTO = fm.getCaseMonDTO();
				cmDTO.setCurrentUserId(userId);
				cmDTO.setCurrentOffId(officeId);
				cmDTO.setAxnDescrDate(fm.getAxnDescrDate());
				cmDTO.setReceivingDate(fm.getReceivingDate());
				cmDTO.setRefundDate(fm.getRefundDate());
				cmDTO.setNxtHearDate(fm.getNxtHearDate());
				if (!((String) request.getParameter("frm") == null || (String) request.getParameter("axn") == null))
				{
					cmDTO.setActionName(request.getParameter("axn"));
					cmDTO.setFormName(request.getParameter("frm"));
				}
				String formName = cmDTO.getFormName();
				if (formName == null){formName = "";}
				String actionName = cmDTO.getActionName();
				if (actionName == null){actionName = "";}
				if (!fwdName.equals("")) {formName = "";
					actionName = "";}
				String actionVal = cmDTO.getActionVal();
				if (actionVal == null){actionVal = "";}
				if (!fwdName.equals("")){formName = "";
					actionVal = "";}

				String crntUser = (String) request.getSession().getAttribute("UserId");
				if (crntUser == null || "".equalsIgnoreCase(crntUser)) 
				{
				crntUser = "igrs";
				cmDTO.setCurrentUserId(crntUser);
				}
				ArrayList dtlList=(ArrayList)cmBD.getInternalUserDtls(officeId,language);
				cmDTO.setParentOfficeId(officeId);
				for(int i=0;i<dtlList.size();i++){
				ArrayList list=(ArrayList)dtlList.get(0);
				cmDTO.setParentOfficeName((String)list.get(0));
				cmDTO.setParentDistrictId((String)list.get(1));
				cmDTO.setParentDistrictName((String)list.get(2));
				}
				if(caseConstant.DIG_OFFICE_TYPE_ID.equalsIgnoreCase(cmBD.getOfficeTypeId(officeId)))//role check
				{	session.setAttribute("role", "DIG");
				}
				else if(caseConstant.IG_OFFICE_TYPE_ID.equalsIgnoreCase(cmBD.getOfficeTypeId(officeId)))
				{	session.setAttribute("role", "IG");
				}
				else if(caseConstant.SR_OFFICE_TYPE_ID.equalsIgnoreCase(cmBD.getOfficeTypeId(officeId)))
				{	session.setAttribute("role", "SR");
				}
				else
				{	session.setAttribute("role", "DR");
				}//end
				if(request.getParameter("reset")!=null)
				{	String resetParam=request.getParameter("reset");
					if("resetUpdate".equalsIgnoreCase(resetParam))
					{
						cmDTO.setCaseHeadName("");
						cmDTO.setCaseTypeName("");
						cmDTO.setCaseTxnId("");
						cmDTO.setCaseCriteria3("");
						cmDTO.setCaseRefundId("");
						fm.setFromDate("");
						fm.setToDate("");
						cmDTO.setCaseCriteria1("");
						cmDTO.setCaseOthersId("");
						fm.setFromDate1("");
						fm.setToDate1("");
						cmDTO.setCaseCriteria2("");
						cmDTO.setCaseRevId("");
						cmDTO.setRrcId("");
						fm.setFromDate2("");
						fm.setToDate2("");
						FORWAD_SUCCESS = "updateMain";
					}
					if("resetCreate".equalsIgnoreCase(resetParam))
					{
						cmDTO.setCaseHeadName("");
						cmDTO.setCaseTypeName("");
						cmDTO.setCaseTxnId("");
						cmDTO.setCaseCriteria3("");
						cmDTO.setCaseRefundId("");
						fm.setFromDate("");
						fm.setToDate("");
						cmDTO.setCaseCriteria1("");
						cmDTO.setCaseOthersId("");
						fm.setFromDate1("");
						fm.setToDate1("");
						cmDTO.setCaseCriteria2("");
						cmDTO.setCaseRevId("");
						cmDTO.setRrcId("");
						fm.setFromDate2("");
						fm.setToDate2("");
						cmDTO.setComplaintId("");
						FORWAD_SUCCESS = "search";
					}
				}
				
				if (fwdName != null && fwdName.equalsIgnoreCase("create")) {
					logger.info("INSIDE CREATE>>>>>>>>>>>>>>>>>>>>>>>");
					cmDTO.setRevenueHeadId("");
					cmDTO.setSectionHeadId("");
					cmDTO.setDrComments("");
					session.removeAttribute("caseType");// added by shruti for clearing the form
					session.removeAttribute("caseHead");
					//cmDTO.setCaseHeadId("");
					//cmDTO.setCaseHeadName("");
					//cmDTO.setCaseHeadList(null);
					//cmDTO.setCaseTypeList(null);
					cmDTO.setCaseHeadList(cmBD.caseHeadStackBD(language,(String)session.getAttribute("role")));
					fm.getCaseMonDTO().setCaseHeadList(cmDTO.getCaseHeadList());
					fm.getCaseMonDTO().setCaseTypeList(cmDTO.getCaseTypeList());// end
					cmDTO.setCaseTypeName("");//added by roopam-23feb2015
					cmDTO.setCaseHeadName("");//added by roopam-23feb2015
					session.setAttribute("caseType", cmDTO.getCaseTypeName());
					session.setAttribute("caseHead", cmDTO.getCaseHeadName());
					fm.setCaseMonDTO(cmDTO);// added for making the form null
					FORWAD_SUCCESS = "search";
				}
				else if (formName.equalsIgnoreCase("cmPenaltyCrtSrch")&& actionName.equalsIgnoreCase("caseHead"))// on case head selection
				{
					logger.info("INSIDE CASE HEAD>>>>>>>>>>>>>>>>>>>>");
					session.removeAttribute("caseType");// added by shruti
					session.removeAttribute("caseHead");
					cmDTO.setCaseTypeList(cmBD.caseTypeStackBD(cmDTO.getCaseHeadId(),language,(String)session.getAttribute("role")));
					String cid = fm.getCaseMonDTO().getCaseTypeName();// end
					session.setAttribute("caseType", cmDTO.getCaseTypeName());
					session.setAttribute("caseHead", cmDTO.getCaseHeadName());
					cmDTO.setEstampType(fm.getCaseMonDTO().getEstampType());
					FORWAD_SUCCESS = "search";
				}
				else if (formName.equalsIgnoreCase("licenseSearchForm")&& actionName.equalsIgnoreCase("licenseSearch")) 
				{
					cmDTO = cmBD.licensIdBD(cmDTO.getLicenseIdSearch());
					fm.setCaseMonDTO(cmDTO);
					FORWAD_SUCCESS = "licenseSrch";
				} 
				else if (formName.equalsIgnoreCase("licenseCrt")&& actionName.equalsIgnoreCase("caseSubmit"))
				{
					cmDTO.setCaseTypeId((String) request.getSession().getAttribute("caseTypeId"));
					cmDTO.setCaseHeadId((String) request.getSession().getAttribute("caseHeadId"));
					cmDTO.setCaseActionTypeId((String) request.getSession().getAttribute("caseAxnTypeId"));
					if (cmBD.licenseCaseInsertBD(cmDTO)){
						FORWAD_SUCCESS = "caseCreated";} 
					else{
						FORWAD_SUCCESS = "failure";}
				}

				// Action Methods for Update ...........................
				if(request.getParameter("dms")!=null)//added  by shruti---for downlaod of uploads
				{
					String dms=request.getParameter("dms");
					if("retrieveProof".equalsIgnoreCase(dms))
					{	if(request.getParameter("retrievalPath")!=null)
						{	String fileLoc=request.getParameter("retrievalPath");
							DMSUtility dmsUtility=new DMSUtility();
							byte[] contents=dmsUtility.getDocumentBytes(fileLoc);
							dmsUtility.downloadDocument(response, fileLoc, contents);
						}
					}
				}
				
				if (fwdName.equalsIgnoreCase("update")) 
				{
					cmDTO=new CaseMonDTO();
					logger.info("INSIDE CASE UPDATE>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					session.removeAttribute("caseType");
					session.removeAttribute("caseHead");
					cmDTO.setCaseTxnId(null);
					cmDTO.setFromDateSearch(null);
					cmDTO.setToDateSearch(null);
					cmDTO.setRrcId(null);
					cmDTO.setCaseHeadId("");
					cmDTO.setCaseHeadName("");
					cmDTO.setCaseHeadList(null);
					cmDTO.setCaseTypeList(null);
					cmDTO.setCaseHeadList(cmBD.caseHeadStackBD(language,(String)session.getAttribute("role")));
					fm.getCaseMonDTO().setCaseHeadList(cmDTO.getCaseHeadList());
					fm.getCaseMonDTO().setCaseTypeList(cmDTO.getCaseTypeList());
					fm.getCaseMonDTO().setNextAxnRadio("");
					fm.getCaseMonDTO().setPartypaydec("");
					fm.getCaseMonDTO().setPartyresponse("");
					fm.getCaseMonDTO().setAuthDec("");
					fm.getCaseMonDTO().setFileDetails(null);
					fm.setPartypaydec("");
					fm.setPartyresponse("");
					fm.setPymtresponse("");
					fm.setPaymentway("");
					fm.setPymtdtls(null);
					fm.setCaseMonDTO(cmDTO);
					FORWAD_SUCCESS = "updateMain";
				}
				
				if (formName.equalsIgnoreCase("CmUpdateMainFrm")&& actionName.equalsIgnoreCase("caseType"))
				{
					logger.info("INSIDE CmUpdateMainFrm FORMNAME AND INSIDE caseType ACTIONNAME");
					cmDTO.setCaseTypeList(cmBD.caseTypeStackBD(cmDTO.getCaseHeadId(),language,(String)session.getAttribute("role")));
					cmDTO.setCaseActionTypeList(null);
					FORWAD_SUCCESS = "updateMain";
				} 
				else if (formName.equalsIgnoreCase("CmUpdateMainFrm")&& actionName.equalsIgnoreCase("showAction"))
				{
					logger.info("INSIDE CmUpdateMainFrm FORMNAME AND INSIDE  showAction ACTIONNAME");
					FORWAD_SUCCESS = "updateMain";
				}
				
				//********************search criteria for updating case***********************************************
				else if ((formName.equalsIgnoreCase("CmUpdateMainFrm")&& actionName.equalsIgnoreCase("searchResult")) ||request.getParameter("updatinpaging")!=null &&("true").equalsIgnoreCase(request.getParameter("updatinpaging")))
				{
					logger.info("INSIDE CmUpdateMainFrm FORMNAME AND INSIDE  searchResult ACTIONNAME");//search criteria for dashboard
					ArrayList searchList = new ArrayList();
					logger.info("CASE CRITERIA>>>"+fm.getCaseMonDTO().getCaseCriteria());
					if(request.getParameter("criteria")!=null)
					{	fm.getCaseMonDTO().setCaseCriteria(request.getParameter("criteria"));
					}
					if(fm.getCaseMonDTO().getCaseCriteria()!=null)
					{
						if(fm.getCaseMonDTO().getCaseCriteria().equalsIgnoreCase("Duration"))//DURATION BASED SEARCH
						{	if(cmDTO.getCaseHeadId().equalsIgnoreCase("CH_002"))///case head check
							{	cmDTO.setFromDateSearch(fm.getFromDate());
								cmDTO.setToDateSearch(fm.getToDate());
							}
							else if(cmDTO.getCaseHeadId().equalsIgnoreCase("CH_001"))
							{	cmDTO.setFromDateSearch(fm.getFromDate2());
								cmDTO.setToDateSearch(fm.getToDate2());
							}
							else if(cmDTO.getCaseHeadId().equalsIgnoreCase("CH_003")||("CH_003".equalsIgnoreCase(cmDTO.getCaseHeadName())))
							{	cmDTO.setCaseHeadId("CH_003");
								cmDTO.setFromDateSearch(fm.getFromDate1());
								cmDTO.setToDateSearch(fm.getToDate1());
							}//end
						if ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_007"))|| (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_008"))) // case type check
							{	logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 007 and 008");
							logger.debug("estamp type:-"+request.getParameter("radioJudi"));
							String estampType=request.getParameter("radioJudi");
								searchList = cmBD.searchListUpdateBD1(cmDTO);
								if (searchList.isEmpty()) 
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setErrorMsg("Records are not found");
									}
									else
									{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
									}	
								} 
								else 
								{	cmDTO.setErrorMsg(null);
									request.getSession().setAttribute("caseSearchList",searchList);
								}
								FORWAD_SUCCESS = "durSearchResult1";
							}
							else if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_009")) 
							{	logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 009");
								searchList = cmBD.searchListUpdateBD(cmDTO);
								if (searchList.isEmpty()) 
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setErrorMsg("Records are not found");
									}
									else
									{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
									}
								} 
								else 
								{	cmDTO.setErrorMsg(null);
									request.getSession().setAttribute("caseSearchList",searchList);
								}
								FORWAD_SUCCESS = "durSearchResult1";
							} 
							else if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_010")) 
							{	logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 010");
								searchList = cmBD.searchListUpdateBD2(cmDTO);
								if (searchList.isEmpty())
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setErrorMsg("Records are not found");
									}
									else
									{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
									}
								} 
								else 
								{	cmDTO.setErrorMsg(null);
									request.getSession().setAttribute("caseSearchList",searchList);
								}
								FORWAD_SUCCESS = "durSearchResult1";
							} 
							else if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_011")) 
							{	logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 011");
								searchList = cmBD.searchListUpdatePenalty45BD(cmDTO);
								if (searchList.isEmpty())
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setErrorMsg("Records are not found");
									}
									else
									{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
									}
								}
								else 
								{	cmDTO.setErrorMsg(null);
									request.getSession().setAttribute("caseSearchList",searchList);
								}
								FORWAD_SUCCESS = "durSearchResult1";
							}
							else if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_012")) 
							{	logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 012");
								searchList = cmBD.searchListUpdatePenalty70BD(cmDTO);
								if (searchList.isEmpty())
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setErrorMsg("Records are not found");
									}
									else
									{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
									}
								} 
								else 
								{	cmDTO.setErrorMsg(null);
									request.getSession().setAttribute("caseSearchList",searchList);
								}
								FORWAD_SUCCESS = "durSearchResult1";
							} 
							else// CASET_000,CASET_001,CASET_002,CASET_003,CASET_004,CASET_005
							{
										logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 001,2,3,4,5");
										searchList = cmBD.searchListUpdateBD(cmDTO);
										if (searchList.isEmpty())
										{	if("en".equalsIgnoreCase(language))
											{	cmDTO.setErrorMsg("Records are not found");
											}
											else
											{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
											}
										} 
										else 
										{	cmDTO.setErrorMsg(null);
											request.getSession().setAttribute("caseSearchList",searchList);
										}
										FORWAD_SUCCESS = "durSearchResult";
							}
							
	///***************dasboard display with last action in update case dashboard--- *************
								if (searchList != null)
								{	logger.info("INSIDE SEARCHLIST NOT EMPTY");
									for (int i = 0; i < searchList.size(); i++) 
									{	CaseMonDTO dto = (CaseMonDTO) searchList.get(i);
										cmDTO.setLastAction(dto.getLastAction());
										cmDTO.setHiLastAction(dto.getHiLastAction());
									}
									lastAction = cmDTO.getLastAction();
									hiLastAction=cmDTO.getHiLastAction();
								}//dasboard display with last action in update case dashboard-	
								
						}//searchresult action end
						
				
					else//case id BASED SEARCH OR PENALTY ID BASED--search criteria for others category
					{
						if(cmDTO.getCaseHeadId().equalsIgnoreCase("CH_001"))// case head check
						{	fm.getCaseMonDTO().setCaseTxnId(fm.getCaseMonDTO().getCaseRevId());
						}
						else if(cmDTO.getCaseHeadId().equalsIgnoreCase("CH_002"))
						{	fm.getCaseMonDTO().setCaseTxnId(fm.getCaseMonDTO().getCaseRefundId());
						}
						else if(cmDTO.getCaseHeadId().equalsIgnoreCase("CH_003"))
						{	fm.getCaseMonDTO().setCaseTxnId(fm.getCaseMonDTO().getCaseOthersId());
						}
						caseTxnId = fm.getCaseMonDTO().getCaseTxnId();// getting case id on which a record is being searched for updating case 
						if(fm.getCaseMonDTO().getRevisionNo()!=null)
						{	cmDTO.setRevisionNo(fm.getCaseMonDTO().getRevisionNo());
						}
						else if ((cmDTO.getCaseTxnId() == null)|| (cmDTO.getCaseTxnId().equals(""))) 
						{	cmDTO.setCaseTxnId(cmBD.getCaseId(cmDTO));// get case txn id for corresponding rrc no.
						} 
						else
						{	cmDTO.setRrcId(cmBD.getRRCId(cmDTO));
						}
						ArrayList list = (ArrayList) cmBD.caseLastAction(cmDTO.getCaseTxnId());
						if (list != null)
						{	for (int i = 0; i < list.size(); i++) 
							{	CaseMonDTO dto = (CaseMonDTO) list.get(i);
								cmDTO.setCaseActionTypeName(dto.getCaseActionTypeId());
								cmDTO.setCaseActionTypeNameRef(dto.getCaseActionTypeNameRef());
							}
						}	// end of rrc case modification
						fm.setCaseMonDTO(cmDTO);
						request.getSession().setAttribute("caseTypeId",cmDTO.getCaseTypeName());
						request.getSession().setAttribute("caseAxnTypeId",cmDTO.getCaseActionTypeName());
						
						if ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_007"))|| (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_008"))) 
						{	logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 007 and 008");
							searchList = cmBD.searchListUpdateBD1(cmDTO);
							if (searchList.isEmpty()) 
							{	if("en".equalsIgnoreCase(language))
								{	cmDTO.setErrorMsg("Records are not found");
								}
								else
								{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
								}
							} 
							else 
							{	cmDTO.setErrorMsg(null);
								request.getSession().setAttribute("caseSearchList",searchList);
							}
							FORWAD_SUCCESS = "searchResult";
						}
						else if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_009")) 
						{	logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 009");
							searchList = cmBD.searchListUpdateBD(cmDTO);
							if (searchList.isEmpty()) 
							{	if("en".equalsIgnoreCase(language))
								{	cmDTO.setErrorMsg("Records are not found");
								}
								else
								{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
								}
							} 
							else 
							{	cmDTO.setErrorMsg(null);
								request.getSession().setAttribute("caseSearchList",searchList);
							}
							FORWAD_SUCCESS = "searchResult";
						} 
						else if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_010")) 
						{	logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 010");
							searchList = cmBD.searchListUpdateBD2(cmDTO);
							if (searchList.isEmpty())
							{	if("en".equalsIgnoreCase(language))
								{	cmDTO.setErrorMsg("Records are not found");
								}
								else
								{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
								}
							} 
							else 
							{	cmDTO.setErrorMsg(null);
								request.getSession().setAttribute("caseSearchList",searchList);
							}
							FORWAD_SUCCESS = "searchResult";
						} 
						else if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_011")) 
						{
							logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 011");
							searchList = cmBD.searchListUpdatePenalty45BD(cmDTO);
							if (searchList.isEmpty())
							{	if("en".equalsIgnoreCase(language))
								{	cmDTO.setErrorMsg("Records are not found");
								}
								else
								{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
								}
							}
							else 
							{	cmDTO.setErrorMsg(null);
								request.getSession().setAttribute("caseSearchList",searchList);
							}
							FORWAD_SUCCESS = "searchResult";
						}
						else if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_012")) 
						{	logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 012");
							searchList = cmBD.searchListUpdatePenalty70BD(cmDTO);
							if(caseTxnId==null || caseTxnId.equalsIgnoreCase(""))
							{	caseTxnId=cmDTO.getCaseTxnId();
							}
							if (searchList.isEmpty())
							{	if("en".equalsIgnoreCase(language))
								{	cmDTO.setErrorMsg("Records are not found");
								}
								else
								{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
								}
							} 
							else 
							{	cmDTO.setErrorMsg(null);
								request.getSession().setAttribute("caseSearchList",searchList);
							}
							FORWAD_SUCCESS = "searchResult";
						} 
						
						else// CASET_000,CASET_001,CASET_002,CASET_003,CASET_004,CASET_005
						{
									logger.info("INSIDE SEARCH RESULT FOR CASE TYPE 001,2,3,4,5");
									searchList = cmBD.searchListUpdateBD(cmDTO);
									if (searchList.isEmpty())
									{	if("en".equalsIgnoreCase(language))
										{	cmDTO.setErrorMsg("Records are not found");
										}
										else
										{	cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
										}
									} 
									else 
									{	cmDTO.setErrorMsg(null);
										request.getSession().setAttribute("caseSearchList",searchList);
									}
									FORWAD_SUCCESS = "searchResult1";
						}
						
						
						//common code for all case types--dashboard on the basis of searched case id	
							if (searchList != null)
							{	logger.info("INSIDE SEARCHLIST NOT EMPTY");
								for (int i = 0; i < searchList.size(); i++) 
								{	CaseMonDTO dto = (CaseMonDTO) searchList.get(i);
									cmDTO.setLastAction(dto.getLastAction());
									cmDTO.setHiLastAction(dto.getHiLastAction());
								}
								lastAction = cmDTO.getLastAction();
								hiLastAction=cmDTO.getHiLastAction();
							}
					}
						fm.getCaseMonDTO().setCaseCriteria("");
				}//end of condition if case criteria is not null
				//end of dashboard code for update screen
				}
				
				
				
				
				
					
				// ********************************************on click of hyperlink of case id
				else if (formName.equalsIgnoreCase("CmUpdateResultFrm")&& actionName.equalsIgnoreCase("caseUpdate")
						&& request.getParameter("uploadAction")==null) //this && condition added by roopam-4feb2015
				{
						logger.info("ON CLICK OF HYPERLINK");
						logger.info("INSIDE FORMNAME CmUpdateResultFrm AND ACTIONNAME caseUpdate");
						ArrayList commentsList = new ArrayList();
						ArrayList attachmentsList = new ArrayList();
						String caseAxnId = (String) request.getSession().getAttribute("caseAxnTypeId");
						if(request.getParameter("hdnCaseParam")!=null)// for handling duration based search
						{	String hdncasedtls=request.getParameter("hdnCaseParam");
							String[] casedtls=hdncasedtls.split("~");
							caseTxnId=casedtls[0];
							cmDTO.setCaseTxnId(caseTxnId);
							lastAction=casedtls[1];
						}//end
						if(cmDTO.getCaseTxnId()!=null && !("").equalsIgnoreCase(cmDTO.getCaseTxnId())){
							
						}else{
						cmDTO.setCaseTxnId(caseTxnId);	//to be chkd-16feb2015
						}
						if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_003"))&& (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_010"))) // for time barred category of others case head
						{
								logger.info("INSIDE HYPERLINK CASE HEAD 003 AND CASE TYPE 010");
								fm.setCaseMonDTO(cmDTO);
								CaseMonDTO dto1 = new CaseMonDTO();
								dto1 = cmBD.caseSelectDetailsBD2(cmDTO.getCaseTxnId());
								cmDTO.setCaseTxnId(dto1.getCaseTxnId());
								cmDTO.setCaseStatus(dto1.getCaseStatus());
								cmDTO.setRevenueHeadName(dto1.getRevenueHeadName());
								cmDTO.setSectionHeadName(dto1.getSectionHeadName());
								cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
								cmDTO.setPenaltyAmt(dto1.getPenaltyAmt());
								commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);								
								cmDTO.setRecordsBuffer(commentsList);
								//below 2 statements added by roopam-4feb2015
								attachmentsList = cmBD.caseRetrieveAttachmentsBD(cmDTO.getCaseTxnId());
								if(attachmentsList!=null && attachmentsList.size()>0)
								cmDTO.setAttachmentsRecordsBuffer(attachmentsList);
								else
									cmDTO.setAttachmentsRecordsBuffer(null);
								ArrayList pendingPymtDtls = new ArrayList();
								pendingPymtDtls = cmBD.getPendingPymtOthers(caseTxnId);
								if (pendingPymtDtls != null) 
								{
									for (int i = 0; i < pendingPymtDtls.size(); i++)
									{	CaseMonDTO dto = (CaseMonDTO) pendingPymtDtls.get(i);
										cmDTO.setBalAmt(dto.getBalAmt());
										logger.info("balance amount after exceess payment>>>"+cmDTO.getBalAmt());//for handling more payment scenario
										if(Double.parseDouble(cmDTO.getBalAmt())<0.0)
										{cmDTO.setBalAmt("0");}
										logger.info("balance amount after exceess payment after making it zero>>>"+cmDTO.getBalAmt());//end		
										cmDTO.setPaidAmt(dto.getPaidAmt());
										cmDTO.setTotalRecAmt(dto.getTotalRecAmt());
									}
								}
								ArrayList pymtdtls = new ArrayList();
								pymtdtls = cmBD.getCasePymtDetails(caseTxnId);
								if (pymtdtls != null) 
								{
									for (int i = 0; i < pymtdtls.size(); i++)
									{	CaseMonDTO dto = (CaseMonDTO) pymtdtls.get(i);
											cmDTO.setTotalPaidAmt(dto.getTotalPaidAmt());
									}
								}
								if (cmDTO.getTotalPaidAmt() == null) 
								{	fm.setPymtdtls(null);
								} 
								else 
								{	fm.setPymtdtls(pymtdtls);
								}
								fm.setLastAction(lastAction);
								fm.setHiLastAction(hiLastAction);
								cmDTO.setNextAxnRadio("");
								request.setAttribute("commentsFm", fm);
								fm.setCaseMonDTO(cmDTO);
								FORWAD_SUCCESS = "updateOthersCaseDetails";
							}//end of time barred case condition	
						else if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_002"))&& ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_007")) || (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_008")))) //code for refund cases-reg fee and estamp refund
						{
								logger.info("INSIDE HYPERLINK CASE HEAD 002 AND CASE TYPE 007 and CASE TYPE 008");
								ArrayList nxtactndtls = cmBD.getNxtActnDtls(lastAction,caseTxnId);
								if (nxtactndtls != null)
								{	for (int i = 0; i < nxtactndtls.size(); i++)
									{	CaseMonDTO dto = (CaseMonDTO) nxtactndtls.get(i);
										cmDTO.setLastActionId(dto.getLastActionId());
										cmDTO.setPartyresponsedb(dto.getPartyresponsedb());
										caseAxnId=cmDTO.getLastActionId();
									}
								}
								if (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))
								{	nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(),caseAxnId);
									if (nxtAxnList.size() == 0)
									{	nxtAxnList = cmBD.nxtActionBD3(cmDTO.getCaseTypeId(),caseAxnId);
									}
									ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
									cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
									cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
									cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
									fm.setNxtActionId(cmDTO.getNxtActionId());
									fm.setNxtActionName(cmDTO.getNxtActionName());
								} 
								else if (cmDTO.getPartyresponsedb().equalsIgnoreCase("N"))
								{	nxtAxnList = cmBD.nxtActionBD1(cmDTO.getCaseTypeId(),caseAxnId);	
									if (nxtAxnList.size() == 0)
									{	nxtAxnList = cmBD.nxtActionBD2(cmDTO.getCaseTypeId(),caseAxnId);
									}
									ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
									cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
									cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
									cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
									fm.setNxtActionId(cmDTO.getNxtActionId());
									fm.setNxtActionName(cmDTO.getNxtActionName());
								}
								request.setAttribute("lastActionName", lastAction);
								request.setAttribute("nextActionName", cmDTO.getNxtActionName());
								fm.setCaseMonDTO(cmDTO);
								CaseMonDTO dto1 = new CaseMonDTO();
								dto1 = cmBD.caseSelectDetailsBD1(cmDTO.getCaseTxnId());
								cmDTO.setCaseTxnId(dto1.getCaseTxnId());
								cmDTO.setCaseStatus(dto1.getCaseStatus());
								cmDTO.setRevenueHeadName(dto1.getRevenueHeadName());
								cmDTO.setSectionHeadName(dto1.getSectionHeadName());
								cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
								commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);
								cmDTO.setRecordsBuffer(commentsList);
								//below 2 statements added by roopam-4feb2015
								attachmentsList = cmBD.caseRetrieveAttachmentsBD(cmDTO.getCaseTxnId());
								if(attachmentsList!=null && attachmentsList.size()>0)
									cmDTO.setAttachmentsRecordsBuffer(attachmentsList);
									else
										cmDTO.setAttachmentsRecordsBuffer(null);
								//added by shruti---21 oct 2014
								/*ArrayList pendingPymtDtls = new ArrayList();
								pendingPymtDtls = cmBD.getPendingPymtOthers(caseTxnId);
								if (pendingPymtDtls != null) {
									for (int i = 0; i < pendingPymtDtls.size(); i++){
										CaseMonDTO dto = (CaseMonDTO) pendingPymtDtls.get(i);
										cmDTO.setBalAmt(dto.getBalAmt());
										cmDTO.setPaidAmt(dto.getPaidAmt());
										cmDTO.setTotalRecAmt(dto.getTotalRecAmt());}}*/
								ArrayList pymtdtls = new ArrayList();
								pymtdtls = cmBD.getCasePymtDetailsEstamp(caseTxnId);
								if (pymtdtls != null)
								{
									for (int i = 0; i < pymtdtls.size(); i++)
									{
									CaseMonDTO dto = (CaseMonDTO) pymtdtls.get(i);
									cmDTO.setTotalRecAmt(dto.getTotalRecAmt());
									//cmDTO.setTotalPaidAmt(dto.getTotalPaidAmt());
									}
								}
								if (cmDTO.getTotalPaidAmt() == null) {
									fm.setPymtdtls(null);} 
								else {
									fm.setPymtdtls(pymtdtls);}
								//end
								fm.setLastAction(lastAction);
								fm.setHiLastAction(hiLastAction);
								cmDTO.setNextAxnRadio("");
								request.setAttribute("commentsFm", fm);
								fm.setCaseMonDTO(cmDTO);
								FORWAD_SUCCESS = "updateRefundCaseDetails";
						} //end of estamp refund case		
						else if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_003"))&& (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_009")))// others category -License cancellation condition
						{
								logger.info("INSIDE HYPERLINK CASE HEAD 003 AND CASE TYPE 009");
								ArrayList nxtactndtls = cmBD.getNxtActnDtls(lastAction,caseTxnId);
								if (nxtactndtls != null)
								{	for (int i = 0; i < nxtactndtls.size(); i++) 
									{	CaseMonDTO dto = (CaseMonDTO) nxtactndtls.get(i);
										cmDTO.setLastActionId(dto.getLastActionId());
										cmDTO.setPartyresponsedb(dto.getPartyresponsedb());
										caseAxnId=cmDTO.getLastActionId();
									}
								}
								if (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))
								{	ArrayList nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
										if (nxtAxnList.size() == 0)
										{	nxtAxnList = cmBD.nxtActionBD3(cmDTO.getCaseTypeId(),caseAxnId);
										}
										ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
										cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
										cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
										cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
										fm.setNxtActionId(cmDTO.getNxtActionId());
										fm.setNxtActionName(cmDTO.getNxtActionName());
										fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
								}
								request.setAttribute("lastActionName", lastAction);
								request.setAttribute("nextActionName", cmDTO.getNxtActionName());
								fm.setCaseMonDTO(cmDTO);
								CaseMonDTO dto1 = new CaseMonDTO();
								dto1 = cmBD.caseSelectDetailsBD(cmDTO.getCaseTxnId());
								cmDTO.setCaseTxnId(dto1.getCaseTxnId());
								cmDTO.setCaseStatus(dto1.getCaseStatus());
								cmDTO.setRevenueHeadName(dto1.getRevenueHeadName());
								cmDTO.setSectionHeadName(dto1.getSectionHeadName());
								cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
								commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);
								cmDTO.setRecordsBuffer(commentsList);
								//below 2 statements added by roopam-4feb2015
								attachmentsList = cmBD.caseRetrieveAttachmentsBD(cmDTO.getCaseTxnId());
								if(attachmentsList!=null && attachmentsList.size()>0)
									cmDTO.setAttachmentsRecordsBuffer(attachmentsList);
									else
										cmDTO.setAttachmentsRecordsBuffer(null);
								fm.setLastAction(lastAction);
								fm.setHiLastAction(hiLastAction);
								cmDTO.setNextAxnRadio("");
								request.setAttribute("commentsFm", fm);
								fm.setCaseMonDTO(cmDTO);
								FORWAD_SUCCESS = "updateLicCnclCaseDetails";
						} // end of others category -License cancellation condition
					else if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_003"))&& ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_011")) || (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_012")))) //start of others-penalty cases
					{
								logger.info("INSIDE HYPERLINK CASE HEAD 003 AND CASE TYPE 011 and 012");
								CaseMonDTO dto1 = new CaseMonDTO();
								if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_011"))
								{
									logger.info("INSIDE PENALTY 45 CASE TYPE");
									dto1 = cmBD.caseSelectPenalty45DetailsBD(cmDTO.getCaseTxnId());
									cmDTO.setCaseTxnId(dto1.getCaseTxnId());
									cmDTO.setCaseStatus(dto1.getCaseStatus());
									cmDTO.setRevisionNo(dto1.getRevisionNo());
									cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
								} 
								else if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_012"))
								{
									logger.info("INSIDE PENALTY 70 CASE TYPE");
									dto1 = cmBD.caseSelectPenalty70DetailsBD(cmDTO.getCaseTxnId());
									cmDTO.setCaseTxnId(dto1.getCaseTxnId());
									cmDTO.setCaseStatus(dto1.getCaseStatus());
									cmDTO.setRevisionNo(dto1.getRevisionNo());
									cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
								}
								ArrayList nxtactndtls = cmBD.getNxtActnDtls(lastAction,cmDTO.getRevisionNo());
								if (nxtactndtls != null) 
								{	for (int i = 0; i < nxtactndtls.size(); i++)
									{	CaseMonDTO dto = (CaseMonDTO) nxtactndtls.get(i);
										cmDTO.setLastActionId(dto.getLastActionId());
										cmDTO.setPartyresponsedb(dto.getPartyresponsedb());
										caseAxnId=cmDTO.getLastActionId();
									}
								}
								if (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))
								{	ArrayList nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
									if (nxtAxnList.size() == 0)
									{	nxtAxnList = cmBD.nxtActionBD3(cmDTO.getCaseTypeId(),caseAxnId);
									}
									ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
									cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
									cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
									cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
									fm.setNxtActionId(cmDTO.getNxtActionId());
									fm.setNxtActionName(cmDTO.getNxtActionName());
									fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
								}
									request.setAttribute("lastActionName", lastAction);
									request.setAttribute("nextActionName", cmDTO.getNxtActionName());
									fm.setCaseMonDTO(cmDTO);
									commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);
									cmDTO.setRecordsBuffer(commentsList);
									//below 2 statements added by roopam-4feb2015
									attachmentsList = cmBD.caseRetrieveAttachmentsBD(cmDTO.getCaseTxnId());
									if(attachmentsList!=null && attachmentsList.size()>0)
										cmDTO.setAttachmentsRecordsBuffer(attachmentsList);
										else
											cmDTO.setAttachmentsRecordsBuffer(null);
									fm.setLastAction(lastAction);
									fm.setHiLastAction(hiLastAction);
									cmDTO.setNextAxnRadio("");
									request.setAttribute("commentsFm", fm);
									fm.setCaseMonDTO(cmDTO);
									FORWAD_SUCCESS = "updatePenaltyCaseDetails";
					} //end of others-penalty cases
					else if(((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_001"))&& ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_001"))
									||(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_002"))||(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_003"))
									||(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_004"))||(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_005"))))) //start of agmp cases-under revenue head
					{	
						caseTxnId=cmDTO.getCaseTxnId();
							ArrayList nxtactndtls = cmBD.getNxtActnDtls(lastAction,caseTxnId);
							if(!(nxtactndtls.isEmpty()))
							{	for (int i = 0; i < nxtactndtls.size(); i++)
								{	CaseMonDTO dto = (CaseMonDTO) nxtactndtls.get(i);
									cmDTO.setLastActionId(dto.getLastActionId());
									cmDTO.setPartyresponsedb(dto.getPartyresponsedb());
									cmDTO.setAuthDec(dto.getAuthDec());
									request.setAttribute("authDec",cmDTO.getAuthDec());
									caseAxnId=cmDTO.getLastActionId();
								}
							}
							
							//modified by shruti---8 oct 2014
							CaseMonDTO dto1 = new CaseMonDTO();
							CaseMonDTO dto2 = new CaseMonDTO();
							CaseMonDTO dto3 = new CaseMonDTO();
							dto1 = cmBD.caseSelectDetailsBD(cmDTO.getCaseTxnId());
							cmDTO.setCaseTxnId(dto1.getCaseTxnId());//chk
							cmDTO.setCaseStatus(dto1.getCaseStatus());
							cmDTO.setRevenueHeadName(dto1.getRevenueHeadName());
							cmDTO.setSectionHeadName(dto1.getSectionHeadName());
							cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
							cmDTO.setCaseHearDate(dto1.getCaseHearDate());
							cmDTO.setCaseDueDate(dto1.getCaseDueDate());
							dto2 = cmBD.recoverableAmtDetailsBD(caseTxnId);
							cmDTO.setTotalRecAmt(dto2.getTotalRecAmt());
							//added by shruti for adeshika shulk----13 oct 2014
							dto3=cmBD.adeshikaShulkAmtDetailsBD(caseTxnId);
							cmDTO.setAdeshikaShulk(dto3.getAdeshikaShulk());
							commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);
							cmDTO.setRecordsBuffer(commentsList);
							//below 2 statements added by roopam-4feb2015
							attachmentsList = cmBD.caseRetrieveAttachmentsBD(cmDTO.getCaseTxnId());
							if(attachmentsList!=null && attachmentsList.size()>0)
								cmDTO.setAttachmentsRecordsBuffer(attachmentsList);
								else
									cmDTO.setAttachmentsRecordsBuffer(null);
							//end
							
							//following code commented by shruti for handling RRC cases
							/*else
							{*/ 		
								if ((cmDTO.getPartyresponsedb() == null)|| (cmDTO.getPartyresponsedb().equals("")))
									{	logger.info("INSIDE DB FLAG NULL>>>>>>>>>>>>>>>>>>>>");
										cmDTO.setPartyresponse("Y");
										nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
										ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
										cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
										cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
										cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
										fm.setNxtActionId(cmDTO.getNxtActionId());
										fm.setNxtActionName(cmDTO.getNxtActionName());
										fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
									}
									else// of if party response db is not empty
									{		if (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))
											{		nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(),caseAxnId);
													if (nxtAxnList.size() == 0)
													{	nxtAxnList = cmBD.nxtActionBD3(cmDTO.getCaseTypeId(),caseAxnId);
													}
													ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
													cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
													cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
													cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
													fm.setNxtActionId(cmDTO.getNxtActionId());
													fm.setNxtActionName(cmDTO.getNxtActionName());
													fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
											} 
											else if (cmDTO.getPartyresponsedb().equalsIgnoreCase("N"))
											{		nxtAxnList = cmBD.nxtActionBD1(cmDTO.getCaseTypeId(),caseAxnId);
													if (nxtAxnList.size() == 0)
													{	nxtAxnList = cmBD.nxtActionBD2(cmDTO.getCaseTypeId(),caseAxnId);
													}
													ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
													cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
													cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
													cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
													fm.setNxtActionId(cmDTO.getNxtActionId());
													fm.setNxtActionName(cmDTO.getNxtActionName());
													fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
											}
											if ((cmDTO.getPartyresponsedb().equalsIgnoreCase("N")||cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))
												&& (cmDTO.getLastActionId().equalsIgnoreCase("CASE_003") ||cmDTO.getLastActionId().equalsIgnoreCase("CASE_002"))) 	// added by shruti for rrc case proceedings--1 month post order and ex partee order
											{		logger.info("INSIDE RRC CHECK");
														String mnthchk = "";
														String mnthparam = "";
														mnthchk = cmBD.getRRCMonthCriteria(cmDTO);
														mnthparam = cmBD.getRRCConfigParam();
														if (Integer.parseInt(mnthchk) >= Integer.parseInt(mnthparam)&& cmBD.getRRCId(cmDTO).equalsIgnoreCase("")) 
														{		cmDTO.setInfomsg("Convert the case into RRC before proceeding further");
																ArrayList rrcdetails = new ArrayList();
																rrcdetails = cmBD.getRRCDetails(cmDTO);
																if (rrcdetails != null)
																{	for (int i = 0; i < rrcdetails.size(); i++) 
																	{	CaseMonDTO dto = (CaseMonDTO) rrcdetails.get(i);
																		cmDTO.setRrcno(dto.getRrcno());
																		cmDTO.setRrcDate(dto.getRrcDate());
																	}
																}
																if (cmDTO.getNxtActionId().equalsIgnoreCase("CASE_014")) // convert proceedings into demand notice in case it is certified copy
																{	cmDTO.setNxtActionId("CASE_004");
																	cmDTO.setNxtActionName("DEMAND NOTICE");
																	cmDTO.setHiNxtActionName("माँग नोटिस");
																}// end
																
																
																fm.setLastAction(lastAction);
																fm.setHiLastAction(hiLastAction);
																cmDTO.setNextAxnRadio("");
																cmDTO.setPartyresponse("");
																cmDTO.setPymtresponse("");
																fm.setPartypaydec("");
																fm.setPartyresponse("");
																fm.setPymtresponse("");
																fm.setPaymentway("");
																fm.setPymtdtls(null);
																request.setAttribute("commentsFm", fm);
																fm.setCaseMonDTO(cmDTO);	
																
																//modified by shruti--9 oct 2014
																request.setAttribute("lastActionName", lastAction);
																request.setAttribute("nextActionName", cmDTO.getNxtActionName());
																//end
																FORWAD_SUCCESS = "rrcDetails";
																//added by shruti--8 oct 2014
																return mapping.findForward(FORWAD_SUCCESS);
																//end
														}// to send demand notice after case is in rrc
														else if (Integer.parseInt(mnthchk) >= Integer.parseInt(mnthparam)&& !(cmBD.getRRCId(cmDTO).equalsIgnoreCase("")))//when rrc case id is there
														{
																logger.info("IF THE CASE IS IN RRC,FURTHER PROCEEDINGS after RRC CASE NUMBER IS THERE");
																
																//added by shruti---31 oct 2014 for handling rrc case proceeding
																if (cmDTO.getNxtActionId().equalsIgnoreCase("CASE_014")) // convert proceedings into demand notice in case it is certified copy
																{	cmDTO.setNxtActionId("CASE_004");
																	cmDTO.setNxtActionName("DEMAND NOTICE");
																	cmDTO.setHiNxtActionName("माँग नोटिस");
																	fm.setNxtActionId(cmDTO.getNxtActionId());
																	fm.setNxtActionName(cmDTO.getNxtActionName());
																	fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
																}// end
																
																
																//added by shruti--9 oct 2014
																request.setAttribute("lastActionName", lastAction);
																request.setAttribute("nextActionName", cmDTO.getNxtActionName());
																logger.info("IF THE CASE IS IN RRC,FURTHER PROCEEDINGS");
																//end
																ArrayList revisedList = new ArrayList();
																revisedList = cmBD.getRevisedRecoverableAmtDetails(cmDTO);
																if (revisedList != null)
																{	for (int i = 0; i < revisedList.size(); i++) 
																	{	CaseMonDTO dto = (CaseMonDTO) revisedList.get(i);
																		cmDTO.setPropTotalRecAmt(dto.getPropTotalRecAmt());
																		cmDTO.setPropRecStampAmt(dto.getPropRecStampAmt());
																		cmDTO.setPropRecRegAmt(dto.getPropRecRegAmt());
																	}
																}
																ArrayList pendingPymtDtls = new ArrayList();
																pendingPymtDtls = cmBD.getPendingPymtDetails(caseTxnId);
																if (pendingPymtDtls != null)
																{	for (int i = 0; i < pendingPymtDtls.size(); i++) 
																	{	CaseMonDTO dto = (CaseMonDTO) pendingPymtDtls.get(i);
																		cmDTO.setBalAmt(dto.getBalAmt());
																		cmDTO.setPaidAmt(dto.getPaidAmt());
																	}
																}
																ArrayList pymtdtls = new ArrayList();
																pymtdtls = cmBD.getCasePymtDetails(caseTxnId);
																if (pymtdtls != null)
																{	for (int i = 0; i < pymtdtls.size(); i++) 
																	{	CaseMonDTO dto = (CaseMonDTO) pymtdtls.get(i);
																		cmDTO.setTotalPaidAmt(dto.getTotalPaidAmt());
																	}
																}
																if (cmDTO.getTotalPaidAmt() == null)
																{	fm.setPymtdtls(null);
																} 
																else 
																{	fm.setPymtdtls(pymtdtls);
																}
																ArrayList regDtls=new ArrayList();//added by shruti--for registered doc details-6 oct 2014
																regDtls=cmBD.getRegDocDetails(caseTxnId,cmDTO);
																if (regDtls != null)
																{	for (int i = 0; i < regDtls.size(); i++) 
																	{	CaseMonDTO dto = (CaseMonDTO) regDtls.get(i);
																		cmDTO.setStampDuty(dto.getStampDuty());
																		cmDTO.setMrktVal(dto.getMrktVal());
																	}
																}//end			
																fm.setLastAction(lastAction);
																fm.setHiLastAction(hiLastAction);
																cmDTO.setNextAxnRadio("");
																cmDTO.setPartyresponse("");
																request.setAttribute("commentsFm", fm);
																fm.setCaseMonDTO(cmDTO);
																FORWAD_SUCCESS = "afterRRCDetails";
																//modified by shruti---8 oct 2014
																return mapping.findForward(FORWAD_SUCCESS);
																//end
														} 
													else // when case not in rrc and case proceedings are order and ex partee order
													{
														logger.info("FURTHER IF THE CASE IS NOT IN RRC");
														//added by shruti--9 oct 2014
														request.setAttribute("lastActionName", lastAction);
														request.setAttribute("nextActionName", cmDTO.getNxtActionName());
														logger.info("IF THE CASE IS IN RRC,FURTHER PROCEEDINGS");
														//end
														
														ArrayList revisedList = new ArrayList();
														revisedList = cmBD.getRevisedRecoverableAmtDetails(cmDTO);
														if (revisedList != null) 
														{	for (int i = 0; i < revisedList.size(); i++) 
															{	CaseMonDTO dto = (CaseMonDTO) revisedList.get(i);
																cmDTO.setPropTotalRecAmt(dto.getPropTotalRecAmt());
																cmDTO.setPropRecStampAmt(dto.getPropRecStampAmt());
																cmDTO.setPropRecRegAmt(dto.getPropRecRegAmt());
															}
														}
														ArrayList pendingPymtDtls = new ArrayList();
														pendingPymtDtls = cmBD.getPendingPymtDetails(caseTxnId);
														if (pendingPymtDtls != null) 
														{	for (int i = 0; i < pendingPymtDtls.size(); i++)
															{	CaseMonDTO dto = (CaseMonDTO) pendingPymtDtls.get(i);
																cmDTO.setBalAmt(dto.getBalAmt());
																cmDTO.setPaidAmt(dto.getPaidAmt());
															}
														}
														ArrayList pymtdtls = new ArrayList();
														pymtdtls = cmBD.getCasePymtDetails(caseTxnId);
														if (pymtdtls != null)
														{	for (int i = 0; i < pymtdtls.size(); i++) 
															{	CaseMonDTO dto = (CaseMonDTO) pymtdtls.get(i);
																cmDTO.setTotalPaidAmt(dto.getTotalPaidAmt());
															}
														}
														if (cmDTO.getTotalPaidAmt() == null) 
														{	fm.setPymtdtls(null);
														} 
														else 
														{	fm.setPymtdtls(pymtdtls);
														}
														ArrayList regDtls=new ArrayList();//added by shruti--for registered doc details-6 oct 2014
														regDtls=cmBD.getRegDocDetails(caseTxnId,cmDTO);
														if (regDtls != null)
														{	for (int i = 0; i < regDtls.size(); i++) 
															{	CaseMonDTO dto = (CaseMonDTO) regDtls.get(i);
																cmDTO.setStampDuty(dto.getStampDuty());
																cmDTO.setMrktVal(dto.getMrktVal());
															}
														}//end
														fm.setLastAction(lastAction);
														fm.setHiLastAction(hiLastAction);
														cmDTO.setNextAxnRadio("");
														cmDTO.setPartyresponse("");
														request.setAttribute("commentsFm", fm);
														fm.setCaseMonDTO(cmDTO);
														FORWAD_SUCCESS = "liceDetails";
														return mapping.findForward(FORWAD_SUCCESS);
													}
										} 
										else // else of 1 mnth chk--case is not in rrc
										{
												logger.info("IF 1 month criteria for RRC is yet TO COMPLETE then the case proceedings");
												ArrayList revisedList = new ArrayList();
												//added by shruti--9 oct 2014
												request.setAttribute("lastActionName", lastAction);
												request.setAttribute("nextActionName", cmDTO.getNxtActionName());
												logger.info("IF THE CASE IS IN RRC,FURTHER PROCEEDINGS");
												//end
												revisedList = cmBD.getRevisedRecoverableAmtDetails(cmDTO);
												if (revisedList != null) 
												{	for (int i = 0; i < revisedList.size(); i++)
													{
														CaseMonDTO dto = (CaseMonDTO) revisedList.get(i);
														cmDTO.setPropTotalRecAmt(dto.getPropTotalRecAmt());
														cmDTO.setPropRecStampAmt(dto.getPropRecStampAmt());
														cmDTO.setPropRecRegAmt(dto.getPropRecRegAmt());
													}
												}
												ArrayList pendingPymtDtls = new ArrayList();
												pendingPymtDtls = cmBD.getPendingPymtDetails(caseTxnId);
												if (pendingPymtDtls != null) 
												{	for (int i = 0; i < pendingPymtDtls.size(); i++) 
													{	CaseMonDTO dto = (CaseMonDTO) pendingPymtDtls.get(i);
														cmDTO.setBalAmt(dto.getBalAmt());
														cmDTO.setPaidAmt(dto.getPaidAmt());
													}
												}
												ArrayList pymtdtls = new ArrayList();
												pymtdtls = cmBD.getCasePymtDetails(caseTxnId);
												if (pymtdtls != null) 
												{	for (int i = 0; i < pymtdtls.size(); i++)
													{	CaseMonDTO dto = (CaseMonDTO) pymtdtls.get(i);
														cmDTO.setTotalPaidAmt(dto.getTotalPaidAmt());
													}
												}
												if (cmDTO.getTotalPaidAmt() == null) 
												{	fm.setPymtdtls(null);
												} 
												else 
												{	fm.setPymtdtls(pymtdtls);
												}
												ArrayList regDtls=new ArrayList();//added by shruti--for registered doc details-6 oct 2014
												regDtls=cmBD.getRegDocDetails(caseTxnId,cmDTO);//error
												if (regDtls != null)
												{	for (int i = 0; i < regDtls.size(); i++) 
													{
														CaseMonDTO dto = (CaseMonDTO) regDtls.get(i);
														cmDTO.setStampDuty(dto.getStampDuty());
														cmDTO.setMrktVal(dto.getMrktVal());
													}
												}//end
												commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);
												cmDTO.setRecordsBuffer(commentsList);
												//below 2 statements added by roopam-4feb2015
												attachmentsList = cmBD.caseRetrieveAttachmentsBD(cmDTO.getCaseTxnId());
												if(attachmentsList!=null && attachmentsList.size()>0)
													cmDTO.setAttachmentsRecordsBuffer(attachmentsList);
													else
														cmDTO.setAttachmentsRecordsBuffer(null);
												fm.setLastAction(lastAction);
												fm.setHiLastAction(hiLastAction);
												cmDTO.setNextAxnRadio("");
												cmDTO.setPartyresponse("");
												request.setAttribute("commentsFm", fm);
												fm.setCaseMonDTO(cmDTO);
												FORWAD_SUCCESS = "liceDetails";
												return mapping.findForward(FORWAD_SUCCESS);
											}// end of if else for rrc
										
									}
							/*}//else of nxtactionList not empty
*/							
						/*if (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))
						{	
							nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(),caseAxnId);
							if (nxtAxnList.isEmpty())
							{	nxtAxnList = cmBD.nxtActionBD3(cmDTO.getCaseTypeId(),caseAxnId);
							}
							ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
							cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
							cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
							cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
							fm.setNxtActionId(cmDTO.getNxtActionId());
							fm.setNxtActionName(cmDTO.getNxtActionName());
							fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
						} 
						else if (cmDTO.getPartyresponsedb().equalsIgnoreCase("N"))
						{	nxtAxnList = cmBD.nxtActionBD1(cmDTO.getCaseTypeId(),caseAxnId);
							if (nxtAxnList.isEmpty())
							{	nxtAxnList = cmBD.nxtActionBD2(cmDTO.getCaseTypeId(),caseAxnId);
							}
							ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
							cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
							cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
							cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
							fm.setNxtActionId(cmDTO.getNxtActionId());
							fm.setNxtActionName(cmDTO.getNxtActionName());
							fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
						}*/
						//end
						request.setAttribute("lastActionName", lastAction);
						request.setAttribute("nextActionName", cmDTO.getNxtActionName());
						logger.info("IF THE CASE IS IN RRC,FURTHER PROCEEDINGS");
						ArrayList revisedList = new ArrayList();
						revisedList = cmBD.getRevisedRecoverableAmtDetails(cmDTO);
						if (revisedList != null)
						{	for (int i = 0; i < revisedList.size(); i++) 
							{	CaseMonDTO dto = (CaseMonDTO) revisedList.get(i);
								cmDTO.setPropTotalRecAmt(dto.getPropTotalRecAmt());
								cmDTO.setPropRecStampAmt(dto.getPropRecStampAmt());
								cmDTO.setPropRecRegAmt(dto.getPropRecRegAmt());
							}
						}
						ArrayList pendingPymtDtls = new ArrayList();
						pendingPymtDtls = cmBD.getPendingPymtDetails(caseTxnId);
						if (pendingPymtDtls != null)
						{	for (int i = 0; i < pendingPymtDtls.size(); i++) 
							{	CaseMonDTO dto = (CaseMonDTO) pendingPymtDtls.get(i);
								cmDTO.setBalAmt(dto.getBalAmt());
								cmDTO.setPaidAmt(dto.getPaidAmt());
							}
						}
						ArrayList pymtdtls = new ArrayList();
						pymtdtls = cmBD.getCasePymtDetails(caseTxnId);
						if (pymtdtls != null)
						{	for (int i = 0; i < pymtdtls.size(); i++) 
							{	CaseMonDTO dto = (CaseMonDTO) pymtdtls.get(i);
								cmDTO.setTotalPaidAmt(dto.getTotalPaidAmt());
							}
						}
						if (cmDTO.getTotalPaidAmt() == null)
						{	fm.setPymtdtls(null);
						} 
						else 
						{	fm.setPymtdtls(pymtdtls);
						}
						//end**************
						
						ArrayList regDtls=new ArrayList();//added by shruti--for registered doc details-6 oct 2014
						regDtls=cmBD.getRegDocDetails(caseTxnId,cmDTO);
						if (regDtls != null)
						{	for (int i = 0; i < regDtls.size(); i++) 
							{	CaseMonDTO dto = (CaseMonDTO) regDtls.get(i);
								cmDTO.setStampDuty(dto.getStampDuty());
								cmDTO.setMrktVal(dto.getMrktVal());
							}
						}//end
						
						//commented by shruti---8 oct 2014
						/*CaseMonDTO dto1 = new CaseMonDTO();
						CaseMonDTO dto2 = new CaseMonDTO();
						dto1 = cmBD.caseSelectDetailsBD(cmDTO.getCaseTxnId());
						cmDTO.setCaseTxnId(dto1.getCaseTxnId());
						cmDTO.setCaseStatus(dto1.getCaseStatus());
						cmDTO.setRevenueHeadName(dto1.getRevenueHeadName());
						cmDTO.setSectionHeadName(dto1.getSectionHeadName());
						cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
						cmDTO.setCaseHearDate(dto1.getCaseHearDate());
						cmDTO.setCaseDueDate(dto1.getCaseDueDate());
						dto2 = cmBD.recoverableAmtDetailsBD(caseTxnId);
						cmDTO.setTotalRecAmt(dto2.getTotalRecAmt());
						commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);
						cmDTO.setRecordsBuffer(commentsList);
						*/
						fm.setLastAction(lastAction);
						fm.setHiLastAction(hiLastAction);
						cmDTO.setNextAxnRadio("");
						cmDTO.setPartyresponse("");
						cmDTO.setPymtresponse("");
						fm.setPartypaydec("");
						fm.setPartyresponse("");
						fm.setPymtresponse("");
						fm.setPaymentway("");
						fm.setPymtdtls(null);
						request.setAttribute("commentsFm", fm);
						fm.setCaseMonDTO(cmDTO);
						FORWAD_SUCCESS = "liceDetails";
					}
					// end of else after case type id caset_007
							fm.getCaseMonDTO().setFormName("");
							fm.getCaseMonDTO().setActionName("");
						logger.info("END OF HYPERLINK BLOCK");
				}
				//end of hyperlink click code
				
//***************************************** hyperlink code end*******************************************				
				
				// added for updating rrc case proceedings
				if ((formName.equalsIgnoreCase("CmUpdateRRCDetails"))&& (actionName.equalsIgnoreCase("updateRRC")))
				{
					String rrcNumber = cmBD.updateRRCDtls(cmDTO);
					if("en".equalsIgnoreCase(language))
					{
					cmDTO.setConfirmMsg("Case is in RRC now and new Case Number is "+ rrcNumber);
					}
					else
					{
						cmDTO.setConfirmMsg("मामला अब आर आर सी में है और नए मामले नंबर है: "+rrcNumber);
					}
					FORWAD_SUCCESS = "rrcConfirm";
				}// end
				
				if(request.getParameter("uploadAction")!=null)
				{
						String uploadAction=(request.getParameter("uploadAction"));
						logger.info("Attachment action>>>"+uploadAction);
			
						logger.info("CASE TXN ID>>>"+caseTxnId);
						logger.info("LAST ACTION>>>"+lastAction);
						//code for nextaction details on each case type
						
						ArrayList commentsList = new ArrayList();
						ArrayList attachmentsList = new ArrayList();
						String caseAxnId = (String) request.getSession().getAttribute("caseAxnTypeId");
						cmDTO.setCaseTxnId(caseTxnId);
							// for time barred category of others case head
							if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_003"))&& (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_010"))) 
							{
									fm.setCaseMonDTO(cmDTO);
									CaseMonDTO dto1 = new CaseMonDTO();
									dto1 = cmBD.caseSelectDetailsBD2(cmDTO.getCaseTxnId());
									cmDTO.setCaseTxnId(dto1.getCaseTxnId());
									cmDTO.setCaseStatus(dto1.getCaseStatus());
									cmDTO.setRevenueHeadName(dto1.getRevenueHeadName());
									cmDTO.setSectionHeadName(dto1.getSectionHeadName());
									cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
									cmDTO.setPenaltyAmt(dto1.getPenaltyAmt());
									commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);
									cmDTO.setRecordsBuffer(commentsList);
									//below 2 statements added by roopam-4feb2015
									attachmentsList = cmBD.caseRetrieveAttachmentsBD(cmDTO.getCaseTxnId());
									if(attachmentsList!=null && attachmentsList.size()>0)
										cmDTO.setAttachmentsRecordsBuffer(attachmentsList);
										else
											cmDTO.setAttachmentsRecordsBuffer(null);
									ArrayList pendingPymtDtls = new ArrayList();
									pendingPymtDtls = cmBD.getPendingPymtOthers(caseTxnId);
									if (pendingPymtDtls != null) {
										for (int i = 0; i < pendingPymtDtls.size(); i++){
											CaseMonDTO dto = (CaseMonDTO) pendingPymtDtls.get(i);
											cmDTO.setBalAmt(dto.getBalAmt());
											cmDTO.setPaidAmt(dto.getPaidAmt());
											cmDTO.setTotalRecAmt(dto.getTotalRecAmt());}}
									ArrayList pymtdtls = new ArrayList();
									pymtdtls = cmBD.getCasePymtDetails(caseTxnId);
									if (pymtdtls != null) {
										for (int i = 0; i < pymtdtls.size(); i++){
										CaseMonDTO dto = (CaseMonDTO) pymtdtls.get(i);
										cmDTO.setTotalPaidAmt(dto.getTotalPaidAmt());}}
									if (cmDTO.getTotalPaidAmt() == null) {
										fm.setPymtdtls(null);} 
									else {
										fm.setPymtdtls(pymtdtls);}
									//added by shruti--for registered doc details-6 oct 2014
									ArrayList regDtls=new ArrayList();
									regDtls=cmBD.getRegDocDetails(caseTxnId,cmDTO);
									if (regDtls != null)
									{
										for (int i = 0; i < regDtls.size(); i++) 
										{
											CaseMonDTO dto = (CaseMonDTO) regDtls.get(i);
											cmDTO.setStampDuty(dto.getStampDuty());
											cmDTO.setMrktVal(dto.getMrktVal());
										}
									}
									//end
								fm.setLastAction(lastAction);
								fm.setHiLastAction(hiLastAction);
								cmDTO.setNextAxnRadio("");
								request.setAttribute("commentsFm", fm);
								fm.setCaseMonDTO(cmDTO);
								FORWAD_SUCCESS = "updateOthersCaseDetails";
							}
							//end of time barred case condition
					
					//code for refund cases-reg fee and estamp refund
					else if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_002"))&& ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_007")) || (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_008")))) 
					{
							ArrayList nxtactndtls = cmBD.getNxtActnDtls(lastAction,caseTxnId);
							if (nxtactndtls != null)
							{
								for (int i = 0; i < nxtactndtls.size(); i++)
								{
									CaseMonDTO dto = (CaseMonDTO) nxtactndtls.get(i);
									cmDTO.setLastActionId(dto.getLastActionId());
									cmDTO.setPartyresponsedb(dto.getPartyresponsedb());
									caseAxnId=cmDTO.getLastActionId();
								}
							}
							if (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))
							{
								nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(),caseAxnId);
								if (nxtAxnList.size() == 0)
								{
									nxtAxnList = cmBD.nxtActionBD3(cmDTO.getCaseTypeId(),caseAxnId);
								}
								ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
								cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
								cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
								cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
								fm.setNxtActionId(cmDTO.getNxtActionId());
								fm.setNxtActionName(cmDTO.getNxtActionName());
								//fm.setHiNxtActionName(cmDTO.getHNxtActionName());
							} 
							else if (cmDTO.getPartyresponsedb().equalsIgnoreCase("N"))
							{
								nxtAxnList = cmBD.nxtActionBD1(cmDTO.getCaseTypeId(),caseAxnId);	
								if (nxtAxnList.size() == 0){
								nxtAxnList = cmBD.nxtActionBD2(cmDTO.getCaseTypeId(),caseAxnId);}
								ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
								cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
								cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
								cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
								fm.setNxtActionId(cmDTO.getNxtActionId());
								fm.setNxtActionName(cmDTO.getNxtActionName());
								fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
							}
							request.setAttribute("lastActionName", lastAction);
							request.setAttribute("nextActionName", cmDTO.getNxtActionName());
							fm.setCaseMonDTO(cmDTO);
							CaseMonDTO dto1 = new CaseMonDTO();
							dto1 = cmBD.caseSelectDetailsBD1(cmDTO.getCaseTxnId());
							cmDTO.setCaseTxnId(dto1.getCaseTxnId());
							cmDTO.setCaseStatus(dto1.getCaseStatus());
							cmDTO.setRevenueHeadName(dto1.getRevenueHeadName());
							cmDTO.setSectionHeadName(dto1.getSectionHeadName());
							cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
							commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);
							cmDTO.setRecordsBuffer(commentsList);
							//below 2 statements added by roopam-4feb2015
							attachmentsList = cmBD.caseRetrieveAttachmentsBD(cmDTO.getCaseTxnId());
							if(attachmentsList!=null && attachmentsList.size()>0)
								cmDTO.setAttachmentsRecordsBuffer(attachmentsList);
								else
									cmDTO.setAttachmentsRecordsBuffer(null);
							fm.setLastAction(lastAction);
							fm.setHiLastAction(hiLastAction);
							cmDTO.setNextAxnRadio("");
							request.setAttribute("commentsFm", fm);
							fm.setCaseMonDTO(cmDTO);
							FORWAD_SUCCESS = "updateRefundCaseDetails";
					} //end of estamp refund case
							
					//start of others-penalty cases
					else if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_003"))&& ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_011")) || (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_012")))) 
					{
						logger.info("INSIDE HYPERLINK CASE HEAD 003 AND CASE TYPE 011 and 012");
						ArrayList nxtactndtls = cmBD.getNxtActnDtls(lastAction,caseTxnId);
							if (nxtactndtls != null) 
							{
								for (int i = 0; i < nxtactndtls.size(); i++)
								{
									CaseMonDTO dto = (CaseMonDTO) nxtactndtls.get(i);
									cmDTO.setLastActionId(dto.getLastActionId());
									cmDTO.setPartyresponsedb(dto.getPartyresponsedb());
									caseAxnId=cmDTO.getLastActionId();
								}
							}
							if (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))
							{
								ArrayList nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
								if (nxtAxnList.size() == 0)
								{
									nxtAxnList = cmBD.nxtActionBD3(cmDTO.getCaseTypeId(),caseAxnId);
								}
								ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
								cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
								cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
								cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
								fm.setNxtActionId(cmDTO.getNxtActionId());
								fm.setNxtActionName(cmDTO.getNxtActionName());
								fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
							}
							
							request.setAttribute("lastActionName", lastAction);
							request.setAttribute("nextActionName", cmDTO.getNxtActionName());
							fm.setCaseMonDTO(cmDTO);
							CaseMonDTO dto1 = new CaseMonDTO();
							
							if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_011"))
							{
								logger.info("INSIDE PENALTY 45 CASE TYPE");
								dto1 = cmBD.caseSelectPenalty45DetailsBD(cmDTO.getCaseTxnId());
								cmDTO.setCaseTxnId(dto1.getCaseTxnId());
								cmDTO.setCaseStatus(dto1.getCaseStatus());
								cmDTO.setRevisionNo(dto1.getRevisionNo());
								cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
							} 
							
							else if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_012"))
							{
								logger.info("INSIDE PENALTY 70 CASE TYPE");
								dto1 = cmBD.caseSelectPenalty70DetailsBD(cmDTO.getCaseTxnId());
								cmDTO.setCaseTxnId(dto1.getCaseTxnId());
								cmDTO.setCaseStatus(dto1.getCaseStatus());
								cmDTO.setRevisionNo(dto1.getRevisionNo());
								cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
							}
							
							commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);
							cmDTO.setRecordsBuffer(commentsList);
							//below 2 statements added by roopam-4feb2015
							attachmentsList = cmBD.caseRetrieveAttachmentsBD(cmDTO.getCaseTxnId());
							if(attachmentsList!=null && attachmentsList.size()>0)
								cmDTO.setAttachmentsRecordsBuffer(attachmentsList);
								else
									cmDTO.setAttachmentsRecordsBuffer(null);
							fm.setLastAction(lastAction);
							fm.setHiLastAction(hiLastAction);
							cmDTO.setNextAxnRadio("");
							request.setAttribute("commentsFm", fm);
							fm.setCaseMonDTO(cmDTO);
							FORWAD_SUCCESS = "updatePenaltyCaseDetails";
					} //end of others-penalty cases
					
					//start of agmp cases-under revenue head 
					else if(((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_001"))&& ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_001"))
									||(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_002"))||(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_003"))
									||(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_004"))||(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_005"))))) 
					{	
							ArrayList nxtactndtls = cmBD.getNxtActnDtls(lastAction,caseTxnId);
						
							if(!(nxtactndtls.isEmpty()))
							{
								for (int i = 0; i < nxtactndtls.size(); i++)
								{
									CaseMonDTO dto = (CaseMonDTO) nxtactndtls.get(i);
									cmDTO.setLastActionId(dto.getLastActionId());
									cmDTO.setPartyresponsedb(dto.getPartyresponsedb());
									cmDTO.setAuthDec(dto.getAuthDec());
									request.setAttribute("authDec",cmDTO.getAuthDec());
									//added---31st oct 2013
										caseAxnId=cmDTO.getLastActionId();
									//end
								}
							}
							
							else
							{
									if ((cmDTO.getPartyresponsedb() == null)|| (cmDTO.getPartyresponsedb() == ""))
									{
										logger.info("INSIDE DB FLAG NULL>>>>>>>>>>>>>>>>>>>>");
										cmDTO.setPartyresponse("Y");
										nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
										ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
										cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
										cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
										cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
										fm.setNxtActionId(cmDTO.getNxtActionId());
										fm.setNxtActionName(cmDTO.getNxtActionName());
										fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
									}
									else
									{
											if (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))
											{
													nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(),caseAxnId);
													if (nxtAxnList.size() == 0)
													{
														nxtAxnList = cmBD.nxtActionBD3(cmDTO.getCaseTypeId(),caseAxnId);
													}
													ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
													cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
													cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
													cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
													fm.setNxtActionId(cmDTO.getNxtActionId());
													fm.setNxtActionName(cmDTO.getNxtActionName());
													fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
											} 
											
											else if (cmDTO.getPartyresponsedb().equalsIgnoreCase("N"))
											{
													nxtAxnList = cmBD.nxtActionBD1(cmDTO.getCaseTypeId(),caseAxnId);
													
													if (nxtAxnList.size() == 0)
													{
														nxtAxnList = cmBD.nxtActionBD2(cmDTO.getCaseTypeId(),caseAxnId);
													}
													ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
													cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
													cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
													cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
													fm.setNxtActionId(cmDTO.getNxtActionId());
													fm.setNxtActionName(cmDTO.getNxtActionName());
													fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
											}
											
										// added by shruti for rrc case proceedings
										if ((cmDTO.getPartyresponsedb().equalsIgnoreCase("N"))|| (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))
												&& ((cmDTO.getLastActionId().equalsIgnoreCase("CASE_003")) ||(cmDTO.getLastActionId().equalsIgnoreCase("CASE_002")))) 
										{
												logger.info("INSIDE RRC CHECK");
												String mnthchk = "";
												String mnthparam = "";
												mnthchk = cmBD.getRRCMonthCriteria(cmDTO);
												mnthparam = cmBD.getRRCConfigParam();
												
													if (Integer.parseInt(mnthchk) >= Integer.parseInt(mnthparam)&& cmBD.getRRCId(cmDTO).equalsIgnoreCase("")) 
													{
														cmDTO.setInfomsg("Convert the case into RRC before proceeding further");
														ArrayList rrcdetails = new ArrayList();
														rrcdetails = cmBD.getRRCDetails(cmDTO);
														if (rrcdetails != null)
														{
															for (int i = 0; i < rrcdetails.size(); i++) 
															{
																CaseMonDTO dto = (CaseMonDTO) rrcdetails.get(i);
																cmDTO.setRrcno(dto.getRrcno());
																cmDTO.setRrcDate(dto.getRrcDate());
															}
														}
														// convert proceedings into demand notice in case it is certified copy
														if (cmDTO.getNxtActionId().equalsIgnoreCase("CASE_014")) 
														{
															cmDTO.setNxtActionId("CASE_004");
															cmDTO.setNxtActionName("DEMAND NOTICE");
															cmDTO.setHiNxtActionName("माँग नोटिस");
														}// end
														
														fm.setLastAction(lastAction);
														fm.setHiLastAction(hiLastAction);
														cmDTO.setNextAxnRadio("");
														cmDTO.setPartyresponse("");
														request.setAttribute("commentsFm", fm);
														fm.setCaseMonDTO(cmDTO);
														FORWAD_SUCCESS = "rrcDetails";
													}// to send demand notice after case is in rrc
											
														
													else if (Integer.parseInt(mnthchk) >= Integer.parseInt(mnthparam)
															&& !(cmBD.getRRCId(cmDTO).equalsIgnoreCase("")))
													{
															logger.info("IF THE CASE IS IN RRC,FURTHER PROCEEDINGS");
															ArrayList revisedList = new ArrayList();
															revisedList = cmBD.getRevisedRecoverableAmtDetails(cmDTO);
															
															if (revisedList != null)
															{
																for (int i = 0; i < revisedList.size(); i++) 
																{
																	CaseMonDTO dto = (CaseMonDTO) revisedList.get(i);
																	cmDTO.setPropTotalRecAmt(dto.getPropTotalRecAmt());
																	cmDTO.setPropRecStampAmt(dto.getPropRecStampAmt());
																	cmDTO.setPropRecRegAmt(dto.getPropRecRegAmt());
																}
															}
															
															ArrayList pendingPymtDtls = new ArrayList();
															pendingPymtDtls = cmBD.getPendingPymtDetails(caseTxnId);
															
															if (pendingPymtDtls != null)
															{
																for (int i = 0; i < pendingPymtDtls.size(); i++) 
																{
																	CaseMonDTO dto = (CaseMonDTO) pendingPymtDtls.get(i);
																	cmDTO.setBalAmt(dto.getBalAmt());
																	cmDTO.setPaidAmt(dto.getPaidAmt());
																}
															}
															
															ArrayList pymtdtls = new ArrayList();
															pymtdtls = cmBD.getCasePymtDetails(caseTxnId);
															if (pymtdtls != null)
															{
																for (int i = 0; i < pymtdtls.size(); i++) 
																{
																	CaseMonDTO dto = (CaseMonDTO) pymtdtls.get(i);
																	cmDTO.setTotalPaidAmt(dto.getTotalPaidAmt());
																}
															}
															if (cmDTO.getTotalPaidAmt() == null)
															{
																fm.setPymtdtls(null);
															} 
															else 
															{
																fm.setPymtdtls(pymtdtls);
															}
															//added by shruti--for registered doc details-6 oct 2014
															ArrayList regDtls=new ArrayList();
															regDtls=cmBD.getRegDocDetails(caseTxnId,cmDTO);
															if (regDtls != null)
															{
																for (int i = 0; i < regDtls.size(); i++) 
																{
																	CaseMonDTO dto = (CaseMonDTO) regDtls.get(i);
																	cmDTO.setStampDuty(dto.getStampDuty());
																	cmDTO.setMrktVal(dto.getMrktVal());
																}
															}
															//end
															fm.setLastAction(lastAction);
															fm.setHiLastAction(hiLastAction);
															cmDTO.setNextAxnRadio("");
															cmDTO.setPartyresponse("");
															request.setAttribute("commentsFm", fm);
															fm.setCaseMonDTO(cmDTO);
															FORWAD_SUCCESS = "afterRRCDetails";
													} 
											else 
											{
												logger.info("FURTHER IF THE CASE IS NOT IN RRC");
												ArrayList revisedList = new ArrayList();
												revisedList = cmBD.getRevisedRecoverableAmtDetails(cmDTO);
												if (revisedList != null) 
												{
													for (int i = 0; i < revisedList.size(); i++) {
														CaseMonDTO dto = (CaseMonDTO) revisedList.get(i);
														cmDTO.setPropTotalRecAmt(dto.getPropTotalRecAmt());
														cmDTO.setPropRecStampAmt(dto.getPropRecStampAmt());
														cmDTO.setPropRecRegAmt(dto.getPropRecRegAmt());
													}
												}
												ArrayList pendingPymtDtls = new ArrayList();
												pendingPymtDtls = cmBD.getPendingPymtDetails(caseTxnId);
												if (pendingPymtDtls != null) 
												{
													for (int i = 0; i < pendingPymtDtls.size(); i++)
													{
														CaseMonDTO dto = (CaseMonDTO) pendingPymtDtls.get(i);
														cmDTO.setBalAmt(dto.getBalAmt());
														cmDTO.setPaidAmt(dto.getPaidAmt());
													}
												}
												ArrayList pymtdtls = new ArrayList();
												pymtdtls = cmBD.getCasePymtDetails(caseTxnId);
												if (pymtdtls != null)
												{
													for (int i = 0; i < pymtdtls.size(); i++) 
													{
														CaseMonDTO dto = (CaseMonDTO) pymtdtls.get(i);
														cmDTO.setTotalPaidAmt(dto.getTotalPaidAmt());
													}
												}
												if (cmDTO.getTotalPaidAmt() == null) 
												{
													fm.setPymtdtls(null);
												} 
												else 
												{
													fm.setPymtdtls(pymtdtls);
												}
												//added by shruti--for registered doc details-6 oct 2014
												ArrayList regDtls=new ArrayList();
												regDtls=cmBD.getRegDocDetails(caseTxnId,cmDTO);
												if (regDtls != null)
												{
													for (int i = 0; i < regDtls.size(); i++) 
													{
														CaseMonDTO dto = (CaseMonDTO) regDtls.get(i);
														cmDTO.setStampDuty(dto.getStampDuty());
														cmDTO.setMrktVal(dto.getMrktVal());
													}
												}
												//end
												fm.setLastAction(lastAction);
												fm.setHiLastAction(hiLastAction);
												cmDTO.setNextAxnRadio("");
												cmDTO.setPartyresponse("");
												request.setAttribute("commentsFm", fm);
												fm.setCaseMonDTO(cmDTO);
												FORWAD_SUCCESS = "liceDetails";
											}
										} 
										
										else // else of mnth chk
										{
											logger.info("IF 9 MONTHS ARE YET TO COMPLETE then the case proceedings");
											ArrayList revisedList = new ArrayList();
											revisedList = cmBD.getRevisedRecoverableAmtDetails(cmDTO);
											if (revisedList != null) 
											{
												for (int i = 0; i < revisedList.size(); i++)
												{
													CaseMonDTO dto = (CaseMonDTO) revisedList.get(i);
													cmDTO.setPropTotalRecAmt(dto.getPropTotalRecAmt());
													cmDTO.setPropRecStampAmt(dto.getPropRecStampAmt());
													cmDTO.setPropRecRegAmt(dto.getPropRecRegAmt());
												}
											}
											ArrayList pendingPymtDtls = new ArrayList();
											pendingPymtDtls = cmBD.getPendingPymtDetails(caseTxnId);
											if (pendingPymtDtls != null) 
											{
												for (int i = 0; i < pendingPymtDtls.size(); i++) 
												{
													CaseMonDTO dto = (CaseMonDTO) pendingPymtDtls.get(i);
													cmDTO.setBalAmt(dto.getBalAmt());
													cmDTO.setPaidAmt(dto.getPaidAmt());
												}
											}
											ArrayList pymtdtls = new ArrayList();
											pymtdtls = cmBD.getCasePymtDetails(caseTxnId);
											if (pymtdtls != null) 
											{
												for (int i = 0; i < pymtdtls.size(); i++)
												{
													CaseMonDTO dto = (CaseMonDTO) pymtdtls.get(i);
													cmDTO.setTotalPaidAmt(dto.getTotalPaidAmt());
												}
											}
											if (cmDTO.getTotalPaidAmt() == null) 
											{
												fm.setPymtdtls(null);
											} 
											else 
											{
												fm.setPymtdtls(pymtdtls);
											}
											//added by shruti--for registered doc details-6 oct 2014
											ArrayList regDtls=new ArrayList();
											regDtls=cmBD.getRegDocDetails(caseTxnId,cmDTO);
											if (regDtls != null)
											{
												for (int i = 0; i < regDtls.size(); i++) 
												{
													CaseMonDTO dto = (CaseMonDTO) regDtls.get(i);
													cmDTO.setStampDuty(dto.getStampDuty());
													cmDTO.setMrktVal(dto.getMrktVal());
												}
											}
											//end
											commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);
											cmDTO.setRecordsBuffer(commentsList);
											//below 2 statements added by roopam-4feb2015
											attachmentsList = cmBD.caseRetrieveAttachmentsBD(cmDTO.getCaseTxnId());
											if(attachmentsList!=null && attachmentsList.size()>0)
												cmDTO.setAttachmentsRecordsBuffer(attachmentsList);
												else
													cmDTO.setAttachmentsRecordsBuffer(null);
											fm.setLastAction(lastAction);
											fm.setHiLastAction(hiLastAction);
											cmDTO.setNextAxnRadio("");
											cmDTO.setPartyresponse("");
											request.setAttribute("commentsFm", fm);
											fm.setCaseMonDTO(cmDTO);
											FORWAD_SUCCESS = "liceDetails";
										}
										// end of if else for rrc
									}
							}//else of nxtactionList not empty
							
						if (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))
						{	
							nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(),caseAxnId);
							if (nxtAxnList.isEmpty())
							{
								nxtAxnList = cmBD.nxtActionBD3(cmDTO.getCaseTypeId(),caseAxnId);
							}
							ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
							cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
							cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
							cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
							fm.setNxtActionId(cmDTO.getNxtActionId());
							fm.setNxtActionName(cmDTO.getNxtActionName());
							fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
						} 
						else if (cmDTO.getPartyresponsedb().equalsIgnoreCase("N"))
						{
							nxtAxnList = cmBD.nxtActionBD1(cmDTO.getCaseTypeId(),caseAxnId);
							if (nxtAxnList.isEmpty())
							{
								nxtAxnList = cmBD.nxtActionBD2(cmDTO.getCaseTypeId(),caseAxnId);
							}
							ArrayList nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
							cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
							cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
							cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
							fm.setNxtActionId(cmDTO.getNxtActionId());
							fm.setNxtActionName(cmDTO.getNxtActionName());
							fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
						}
						//end
						request.setAttribute("lastActionName", lastAction);
						request.setAttribute("nextActionName", cmDTO.getNxtActionName());
						logger.info("IF THE CASE IS IN RRC,FURTHER PROCEEDINGS");
						ArrayList revisedList = new ArrayList();
						revisedList = cmBD.getRevisedRecoverableAmtDetails(cmDTO);
						if (revisedList != null)
						{
							for (int i = 0; i < revisedList.size(); i++) 
							{
								CaseMonDTO dto = (CaseMonDTO) revisedList.get(i);
								cmDTO.setPropTotalRecAmt(dto.getPropTotalRecAmt());
								cmDTO.setPropRecStampAmt(dto.getPropRecStampAmt());
								cmDTO.setPropRecRegAmt(dto.getPropRecRegAmt());
							}
						}
						ArrayList pendingPymtDtls = new ArrayList();
						pendingPymtDtls = cmBD.getPendingPymtDetails(caseTxnId);
						if (pendingPymtDtls != null)
						{
							for (int i = 0; i < pendingPymtDtls.size(); i++) 
							{
								CaseMonDTO dto = (CaseMonDTO) pendingPymtDtls.get(i);
								cmDTO.setBalAmt(dto.getBalAmt());
								cmDTO.setPaidAmt(dto.getPaidAmt());
							}
						}
						ArrayList pymtdtls = new ArrayList();
						pymtdtls = cmBD.getCasePymtDetails(caseTxnId);
						if (pymtdtls != null)
						{
							for (int i = 0; i < pymtdtls.size(); i++) 
							{
								CaseMonDTO dto = (CaseMonDTO) pymtdtls.get(i);
								cmDTO.setTotalPaidAmt(dto.getTotalPaidAmt());
							}
						}
						if (cmDTO.getTotalPaidAmt() == null)
						{
							fm.setPymtdtls(null);
						} 
						else 
						{
							fm.setPymtdtls(pymtdtls);
						}
						//added by shruti--for registered doc details-6 oct 2014
						ArrayList regDtls=new ArrayList();
						regDtls=cmBD.getRegDocDetails(caseTxnId,cmDTO);
						if (regDtls != null)
						{
							for (int i = 0; i < regDtls.size(); i++) 
							{
								CaseMonDTO dto = (CaseMonDTO) regDtls.get(i);
								cmDTO.setStampDuty(dto.getStampDuty());
								cmDTO.setMrktVal(dto.getMrktVal());
							}
						}
						//end
						
						//end**************
						CaseMonDTO dto1 = new CaseMonDTO();
						CaseMonDTO dto2 = new CaseMonDTO();
						CaseMonDTO dto3 = new CaseMonDTO();
						dto1 = cmBD.caseSelectDetailsBD(cmDTO.getCaseTxnId());
						cmDTO.setCaseTxnId(dto1.getCaseTxnId());
						cmDTO.setCaseStatus(dto1.getCaseStatus());
						cmDTO.setRevenueHeadName(dto1.getRevenueHeadName());
						cmDTO.setSectionHeadName(dto1.getSectionHeadName());
						cmDTO.setCaseCreatedDate(dto1.getCaseCreatedDate());
						cmDTO.setCaseHearDate(dto1.getCaseHearDate());
						cmDTO.setCaseDueDate(dto1.getCaseDueDate());
						dto2 = cmBD.recoverableAmtDetailsBD(caseTxnId);
						cmDTO.setTotalRecAmt(dto2.getTotalRecAmt());
						//added by shruti---13 oct 2014
						dto3=cmBD.adeshikaShulkAmtDetailsBD(caseTxnId);
						cmDTO.setAdeshikaShulk(dto3.getAdeshikaShulk());
						commentsList = cmBD.caseRetrieveCommentsBD(cmDTO.getCaseTxnId(), caseAxnId);
						cmDTO.setRecordsBuffer(commentsList);
						//below 2 statements added by roopam-4feb2015
						attachmentsList = cmBD.caseRetrieveAttachmentsBD(cmDTO.getCaseTxnId());
						if(attachmentsList!=null && attachmentsList.size()>0)
							cmDTO.setAttachmentsRecordsBuffer(attachmentsList);
							else
								cmDTO.setAttachmentsRecordsBuffer(null);
						fm.setLastAction(lastAction);
						fm.setHiLastAction(hiLastAction);
						request.setAttribute("commentsFm", fm);
						fm.setCaseMonDTO(cmDTO);
						FORWAD_SUCCESS = "liceDetails";
					}
					// end of else after case type id caset_007
						
						if ("attach".equalsIgnoreCase(uploadAction)) 
						{
							map = new HashMap();
							logger.info("Method to generate ReportId");
							cmDTO.setFileName("");
							cmDTO.setFilePath("");
							cmDTO.setFileDetails(null);
							fm.setCaseMonDTO(cmDTO);
							FORWAD_SUCCESS = CommonConstant.ATTACHMENT;
							return mapping.findForward(FORWAD_SUCCESS);
						}
						if("attach1".equalsIgnoreCase(uploadAction))
						{
							logger.info("attach");
							FormFile fileForm = cmDTO.getFileUpload();
							String fileName = fileForm.getFileName();
							String filePath = cmDTO.getFilePath();
							cmDTO.setFileByte(fileForm.getFileData());
							cmDTO.setCaseTxnId(caseTxnId);
							FormFile uploadedFile = cmDTO.getFileUpload();
							/*String fileExt = getFileExtension(uploadedFile.getFileName());
							AuditInspectionRule rule = new AuditInspectionRule();
							rule.validateFileType(fileExt);*/
							// Added new code for MIME type common - Rahul
							MIMECheck mimeCheck = new MIMECheck();
							String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
					        Boolean mime = mimeCheck.validateMIMEAndJPGFileType(uploadedFile);  // common code to check mime type and validation.
					        //new code finish
							int size = uploadedFile.getFileSize();
							double fileSizeInBytes = size;
							double fileSizeInKB = fileSizeInBytes / 1024.0;
							double fileSizeInMB = fileSizeInKB / 1024.0;
							DecimalFormat decim = new DecimalFormat("#.##");
							Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
							String photoSize = "(" + fileSize + "MB)";
							if (!mime)
							{
								errorList.add("Invalid file type. Please select another file \n\n अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
								request.setAttribute("errorsList", errorList);
								return mapping.findForward(CommonConstant.ATTACHMENT);
								
							}
							else
							{
								if (size > AuditInspectionConstant.MAX_FILE_SIZE) 
								{
									errorList.add("File size is Greater than 10 MB. Please select another file \n\n फ़ाइल का आकार 10 MB से अधिक है| अन्य फाइल चुनें|");
									request.setAttribute("errorsList", errorList);
								}
								else
								{
									CaseMonDTO cDTO = new CaseMonDTO();
									cDTO.setFileName(uploadedFile.getFileName());
									cDTO.setFileByte(uploadedFile.getFileData());
									cDTO.setFileSize(photoSize);
									if (!"".equals(fileName)&& !"".equals(filePath)) 
									{
										if (!(map.containsKey(fileName))) 
										{
											map.put(fileName, cDTO);
										} 
										else 
										{
											messages.add("MSG", new ActionMessage("cas.uploaderror"));
											saveMessages(request, messages);
											fm.setErrorchk("Y");
										}
									}
								}
								cmDTO.setFileDetails(map);
								fm.setCaseMonDTO(cmDTO);
								FORWAD_SUCCESS = CommonConstant.ATTACHMENT;
								return mapping.findForward(FORWAD_SUCCESS);
							}
						}
						else if("remove".equalsIgnoreCase(uploadAction))
						{
							logger.info("INSIDE REMOVE ACTION OF ATTACHMENT");
							cmDTO = fm.getCaseMonDTO();
							String[] floorID = cmDTO.getHdnFileName().split(",");
							for (int i = 0; i < floorID.length; i++) 
							{
								//logger.debug(floorID[i] + " is removed...");
								map.remove(floorID[i]);
							}
							cmDTO.setFileDetails(map);
							fm.setCaseMonDTO(cmDTO);
							FORWAD_SUCCESS = CommonConstant.ATTACHMENT;
							return mapping.findForward(FORWAD_SUCCESS);
						}
						else if("close".equalsIgnoreCase(uploadAction))
						{
							logger.info("INSIDE CLOSE ACTION OF ATTACHMENT");
							HashMap mapFile = cmDTO.getFileDetails();
							cmDTO.setCaseTxnId(caseTxnId);
							logger.info("BEFORE MAP SIZE");
							if (mapFile != null) 
							{
								logger.info("inside null con");
								logger.info("IN MAP FILE SIZE ZERO");
								String fileLoc = "";
								Set mapSet = (Set) mapFile.entrySet();
								Iterator mapIterator = mapSet.iterator();
								while (mapIterator.hasNext()) 
								{
									Map.Entry mapEntry = (Map.Entry) mapIterator.next();
									String key = (String) mapEntry.getKey();
									CaseMonDTO cDTO = (CaseMonDTO) mapEntry.getValue();
									count++;
									String fileName = "proof" + count+".jpg";
									if (cDTO.getFileByte() != null)
									{
										fileLoc = uploadFile(cmDTO.getCaseTxnId(),cDTO.getFileByte(),CommonConstant.IGRS_CASE_DOC_UPLOAD_PATH,fileName);
										if (fileLoc != null&& cDTO.getFileByte() != null)
										{
											cDTO.setFilePath(fileLoc);
										}
										if (fileLoc == null) 
										{
											messages.add("MSG1", new ActionMessage("upload_error"));
											saveMessages(request, messages);
											fm.setErrorchk("U");
											FORWAD_SUCCESS = CommonConstant.ATTACHMENT;
										}
									}
									mapFile.put(key, cDTO);
								}
							}
						}
				}
	
				
				// radio button click
				else if (formName.equalsIgnoreCase("CmUpdateDetailsFrm")&& actionName.equalsIgnoreCase("caseUpdate"))
				{
					
						if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_002"))&& ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_007")) ||(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_008")))) 
						{
							ArrayList commentsList = new ArrayList();
							//modified by shruti---4 july 2014
							String caseAxnId = cmDTO.getLastActionId();
							//end
							//String caseAxnId = (String) request.getSession().getAttribute("caseAxnTypeId");
							ArrayList nxtactndtls = cmBD.getNxtActnDtls(lastAction,caseTxnId);
							if (nxtactndtls != null) 
							{
								for (int i = 0; i < nxtactndtls.size(); i++) 
								{
									CaseMonDTO dto = (CaseMonDTO) nxtactndtls.get(i);
									cmDTO.setLastActionId(dto.getLastActionId());
									cmDTO.setPartyresponsedb(dto.getPartyresponsedb());
									cmDTO.setAuthDec(dto.getAuthDec());
								}
							}
							if ((fm.getCaseMonDTO().getActionChk() != null)|| (fm.getCaseMonDTO().getActionChk() != "")) 
							{
								fm.getCaseMonDTO().setActionChk("");
							}
							ArrayList nxtAxnList = null;
							ArrayList nxtAxnList2 = null;
	
							if (fm.getPartyresponse().equalsIgnoreCase(cmDTO.getPartyresponsedb())) 
							{
								logger.info("EQUAL>>>>>>>>");
								cmDTO.setPartyresponse(fm.getPartyresponse());
								if (fm.getPartyresponse().equalsIgnoreCase("Y")) 
								{
									cmDTO.setPartyresponse(fm.getPartyresponse());
									nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
								} 
								else if (fm.getPartyresponse().equalsIgnoreCase("N"))
								{
									cmDTO.setPartyresponse(fm.getPartyresponse());
									nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
								}
								nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
								cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
								cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
								cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
								fm.setNxtActionId(cmDTO.getNxtActionId());
								fm.setNxtActionName(cmDTO.getNxtActionName());
								fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
								request.setAttribute("lastActionName", lastAction);
								request.setAttribute("nextActionName", cmDTO.getNxtActionName());
								cmDTO.setNextAxnRadio("");
								request.setAttribute("commentsFm", fm);
								fm.setCaseMonDTO(cmDTO);
								FORWAD_SUCCESS = "updateRefundCaseDetails";
							} 
							else if (((fm.getPartyresponse().equalsIgnoreCase("Y")) && (cmDTO.getPartyresponsedb().equalsIgnoreCase("N")))
									|| ((fm.getPartyresponse().equalsIgnoreCase("N")) && (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y")))) 
							{
								logger.info("NOT EQUAL>>>>>>>");
								if (fm.getPartyresponse().equalsIgnoreCase("N"))
								{
									cmDTO.setPartyresponse(fm.getPartyresponse());
									nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
								} 
								else if (fm.getPartyresponse().equalsIgnoreCase("Y")) {
									cmDTO.setPartyresponse(fm.getPartyresponse());
									nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
								}
								nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
								cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
								cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
								cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
								fm.setNxtActionId(cmDTO.getNxtActionId());
								fm.setNxtActionName(cmDTO.getNxtActionName());
								fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
								request.setAttribute("lastActionName", lastAction);
								request.setAttribute("nextActionName", cmDTO.getNxtActionName());
								cmDTO.setNextAxnRadio("");
								request.setAttribute("commentsFm", fm);
								fm.setCaseMonDTO(cmDTO);
								FORWAD_SUCCESS = "updateRefundCaseDetails";
							}
					}


					else if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_003"))&& ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_011")) ||(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_012")))) 
					{
								ArrayList commentsList = new ArrayList();
								String caseAxnId = cmDTO.getLastActionId();
								CaseMonDTO tempDto=new CaseMonDTO();
								//modified by shruti---29 oct 2014 KKKKK
								if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_011"))
								{
									logger.info("GET REVISION NO FOR PENALTY 45 CASE TYPE");
									tempDto = cmBD.caseSelectPenalty45DetailsBD(cmDTO.getCaseTxnId());
									cmDTO.setRevisionNo(tempDto.getRevisionNo());
								} 
								else if (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_012"))
								{
									logger.info("GET REVISION NO FOR PENALTY 70 CASE TYPE");
									tempDto = cmBD.caseSelectPenalty70DetailsBD(cmDTO.getCaseTxnId());
									cmDTO.setRevisionNo(tempDto.getRevisionNo());
								}
								//end
								/*ArrayList nxtactndtls = cmBD.getNxtActnDtls(lastAction,caseTxnId);*/
								ArrayList nxtactndtls = cmBD.getNxtActnDtls(lastAction,cmDTO.getRevisionNo());
								if (nxtactndtls != null) {
									for (int i = 0; i < nxtactndtls.size(); i++) 
									{
										CaseMonDTO dto = (CaseMonDTO) nxtactndtls.get(i);
										cmDTO.setLastActionId(dto.getLastActionId());
										cmDTO.setPartyresponsedb(dto.getPartyresponsedb());
										cmDTO.setAuthDec(dto.getAuthDec());
									}
								}
								if ((fm.getCaseMonDTO().getActionChk() != null)|| (fm.getCaseMonDTO().getActionChk() != ""))
								{
									fm.getCaseMonDTO().setActionChk("");
								}
								ArrayList nxtAxnList = null;
								ArrayList nxtAxnList2 = null;
		
								if (fm.getPartyresponse().equalsIgnoreCase(cmDTO.getPartyresponsedb()))
								{
									logger.info("EQUAL>>>>>>>>");
									cmDTO.setPartyresponse(fm.getPartyresponse());
									if (fm.getPartyresponse().equalsIgnoreCase("Y"))
									{
										cmDTO.setPartyresponse(fm.getPartyresponse());
										nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
									} 
									else if (fm.getPartyresponse().equalsIgnoreCase("N")) 
									{
										cmDTO.setPartyresponse(fm.getPartyresponse());
										nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
									}
									nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
									cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
									cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
									cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
									fm.setNxtActionId(cmDTO.getNxtActionId());
									fm.setNxtActionName(cmDTO.getNxtActionName());
									fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
									request.setAttribute("lastActionName", lastAction);
									request.setAttribute("nextActionName", cmDTO.getNxtActionName());
									cmDTO.setNextAxnRadio("");
									request.setAttribute("commentsFm", fm);
									fm.setCaseMonDTO(cmDTO);
									FORWAD_SUCCESS = "updatePenaltyCaseDetails";
								} 
								else if (((fm.getPartyresponse().equalsIgnoreCase("Y")) && (cmDTO.getPartyresponsedb().equalsIgnoreCase("N")))|| 
										((fm.getPartyresponse().equalsIgnoreCase("N")) && (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y"))))
								{
									logger.info("NOT EQUAL>>>>>>>");
									if (fm.getPartyresponse().equalsIgnoreCase("N"))
									{
										cmDTO.setPartyresponse(fm.getPartyresponse());
										nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
									} 
									else if (fm.getPartyresponse().equalsIgnoreCase("Y"))
									{
										cmDTO.setPartyresponse(fm.getPartyresponse());
										nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(), caseAxnId);
									}
									nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
									cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
									cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
									cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
									fm.setNxtActionId(cmDTO.getNxtActionId());
									fm.setNxtActionName(cmDTO.getNxtActionName());
									fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
									request.setAttribute("lastActionName", lastAction);
									request.setAttribute("nextActionName", cmDTO.getNxtActionName());
									cmDTO.setNextAxnRadio("");
									request.setAttribute("commentsFm", fm);
									fm.setCaseMonDTO(cmDTO);
									FORWAD_SUCCESS = "updatePenaltyCaseDetails";
								}
					}// end
					
						else if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_001"))&& ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_001"))
										|| (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_002"))|| (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_003"))
										|| (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_004"))|| (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_005")))) 
						{
								ArrayList commentsList = new ArrayList();
								String caseAxnId=cmDTO.getLastActionId();
								cmDTO.setCaseTxnId(caseTxnId);
								String crntProcessAction = cmDTO.getCaseActionTypeNameRef();
								if((fm.getPartyresponse()==null)||(fm.getPartyresponse().equalsIgnoreCase("")))
								{
									ArrayList nxtAxnList2 = null;
									cmDTO.setNxtActionId("");
									cmDTO.setNxtActionName("");
									fm.setNxtActionId(cmDTO.getNxtActionId());
									fm.setNxtActionName(cmDTO.getNxtActionName());
									fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
									request.setAttribute("lastActionName", lastAction);
									request.setAttribute("nextActionName", cmDTO.getNxtActionName());
									cmDTO.setNextAxnRadio("");
									request.setAttribute("commentsFm", fm);
									fm.setCaseMonDTO(cmDTO);
									FORWAD_SUCCESS = "liceDetails";
								}
							else 
							{
									ArrayList nxtactndtls = cmBD.getNxtActnDtls(lastAction, caseTxnId);
									if (nxtactndtls != null)
									{
										for (int i = 0; i < nxtactndtls.size(); i++) 
										{
											CaseMonDTO dto = (CaseMonDTO) nxtactndtls.get(i);
											cmDTO.setLastActionId(dto.getLastActionId());
											cmDTO.setPartyresponsedb(dto.getPartyresponsedb());
											cmDTO.setAuthDec(dto.getAuthDec());
										}
									}
									if ((fm.getCaseMonDTO().getActionChk() != null)||(fm.getCaseMonDTO().getActionChk() != ""))
									{
										fm.getCaseMonDTO().setActionChk("");
									}
									ArrayList nxtAxnList = null;
									ArrayList nxtAxnList2 = null;
									if (fm.getPartyresponse().equalsIgnoreCase(cmDTO.getPartyresponsedb()))
									{
										//BELOW METHOD CALLINGS CORRECTED BY ROOPAM-4FEB2015
										logger.info("EQUAL>>>>>>>>");
										cmDTO.setPartyresponse(fm.getPartyresponse());
										if (fm.getPartyresponse().equalsIgnoreCase("Y")) 
										{	nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(),caseAxnId);//SIMPLE MAP TABLE
											logger.info("BEFORE CONDITION OF SIZE ZERO OR EMPTY NXT actionlist FOR FLAG y");
											if ((nxtAxnList.size() == 0)||(nxtAxnList.isEmpty()))
											{	nxtAxnList = cmBD.nxtActionBD3(cmDTO.getCaseTypeId(),caseAxnId);//MAP2 TABLE
											}
										} 
										else if (fm.getPartyresponse().equalsIgnoreCase("N"))
										{	nxtAxnList = cmBD.nxtActionBD1(cmDTO.getCaseTypeId(),caseAxnId);//SIMPLE MAP TABLE
											logger.info("BEFORE CONDITION OF SIZE ZERO OR EMPTY NXT actionlist FOR FLAG n");
											if ((nxtAxnList.size() == 0)||(nxtAxnList.isEmpty()))
											{	nxtAxnList = cmBD.nxtActionBD2(cmDTO.getCaseTypeId(),caseAxnId);//MAP1 TABLE
											}
										} 
										else
										{	nxtAxnList = cmBD.nxtActionBD1(cmDTO.getCaseTypeId(),caseAxnId);//SIMPLE MAP TABLE
										}
										nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
										cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
										cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
										cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
										fm.setNxtActionId(cmDTO.getNxtActionId());
										fm.setNxtActionName(cmDTO.getNxtActionName());
										fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
										request.setAttribute("lastActionName",lastAction);
										request.setAttribute("nextActionName", cmDTO.getNxtActionName());
										cmDTO.setNextAxnRadio("");
										request.setAttribute("commentsFm", fm);
										fm.setCaseMonDTO(cmDTO);
										FORWAD_SUCCESS = "liceDetails";
								}
								else if (((fm.getPartyresponse().equalsIgnoreCase("Y"))&& (cmDTO.getPartyresponsedb().equalsIgnoreCase("N")))
										|| ((fm.getPartyresponse().equalsIgnoreCase("N"))&& (cmDTO.getPartyresponsedb().equalsIgnoreCase("Y")))) 
								{
									logger.info("NOT EQUAL>>>>>>>");
									if (fm.getPartyresponse().equalsIgnoreCase("N")) {
										cmDTO.setPartyresponse(fm.getPartyresponse());
										fm.setPymtresponse("");//if party refusess to pay then empty pay detls
										fm.setPartypaydec("");//end
										nxtAxnList = cmBD.nxtActionBD1(cmDTO.getCaseTypeId(),caseAxnId);
										if ((nxtAxnList.size() == 0)||(nxtAxnList.isEmpty()))
										{	nxtAxnList = cmBD.nxtActionBD2(cmDTO.getCaseTypeId(),caseAxnId);
										}
									} 
									else if (fm.getPartyresponse().equalsIgnoreCase("Y")) {
										cmDTO.setPartyresponse(fm.getPartyresponse());
										nxtAxnList = cmBD.nxtActionBD(cmDTO.getCaseTypeId(),caseAxnId);
										if ((nxtAxnList.size() == 0)||(nxtAxnList.isEmpty()))
										{	nxtAxnList = cmBD.nxtActionBD3(cmDTO.getCaseTypeId(),caseAxnId);
										}
									}
									nxtAxnList2 = (ArrayList) nxtAxnList.get(0);
									cmDTO.setNxtActionId((String) nxtAxnList2.get(0));
									cmDTO.setNxtActionName((String) nxtAxnList2.get(1));
									cmDTO.setHiNxtActionName((String) nxtAxnList2.get(2));
									fm.setNxtActionId(cmDTO.getNxtActionId());
									fm.setNxtActionName(cmDTO.getNxtActionName());
									fm.setHiNxtActionName(cmDTO.getHiNxtActionName());
									request.setAttribute("lastActionName",lastAction);
									request.setAttribute("nextActionName", cmDTO.getNxtActionName());
									cmDTO.setNextAxnRadio("");
									request.setAttribute("commentsFm", fm);
									fm.setCaseMonDTO(cmDTO);
									FORWAD_SUCCESS = "liceDetails";
								}
							}
						}
						
				}
				
				
				// adedd by shruti-18th july 2013****************************************************
				// **********************************************TIME BARRED------CASET_010
				else if (formName.equalsIgnoreCase("CmUpdateOthersFrm") && (actionName.equalsIgnoreCase("drCommentsUpdate")
						|| actionName.equalsIgnoreCase("issueNotice") || actionName.equalsIgnoreCase("closeCase")))
				{
					logger.info("INSIDE FORMNAME CmUpdateOthersFrm AND INSIDE ACTION NAME issueNotice");
					String caseAxnId = fm.getNxtActionId();
					cmDTO.setCaseActionTypeId(caseAxnId);
					//logger.info("CASE TXN ID>>>>>>" + caseTxnId);
					cmDTO.setCaseTxnId(caseTxnId);
					if (actionVal.equalsIgnoreCase("payment")) 
					{
						logger.info("INSIDE PAYMENT CHUNK FOR OTHERS");
						caseAxnId = (String) request.getSession().getAttribute("caseAxnTypeId");
						cmDTO.setCaseTxnId(caseTxnId);
						cmDTO.setCaseActionTypeId(caseAxnId);
						String forwardPath = "./CaseMonComplianceAction.do?actionName=paymentsuccess&TRFS=NGI";
						String parentAmount = fm.getCaseMonDTO().getInstone();
						pymtsrid = cmBD.insertCasePymtTxnDtls(cmDTO, userId);
						request.setAttribute("parentModName","Case Monitoring");
						request.setAttribute("parentFunName", "Update");
						request.setAttribute("parentFunId", "FUN_042");
						request.setAttribute("parentAmount", parentAmount);
						request.setAttribute("parentKey", pymtsrid);
						request.setAttribute("parentTable","IGRS_CASE_PYMT_TXN_DTLS");
						request.setAttribute("parentColumnName", "SR_NO");
						request.setAttribute("forwardPath", forwardPath);
						//added
						request.setAttribute("parentDistrictId", cmDTO.getParentDistrictId());
						request.setAttribute("parentDistrictName", cmDTO.getParentDistrictName());
						request.setAttribute("parentOfficeId", cmDTO.getParentOfficeId());
						request.setAttribute("parentOfficeName", cmDTO.getParentOfficeName());
						request.setAttribute("parentReferenceId", cmDTO.getCaseTxnId());
						request.setAttribute("formName", "casemonfrm");
						
						//end
						cmDTO.setActionName("issueNotice");
						cmDTO.setActionVal("paymentsuccess");
						FORWAD_SUCCESS = "payment";
					} 
					else if (actionVal.equalsIgnoreCase("paymentsuccess")) 
					{
						logger.info("INSIDE PAYMENT SUCCESS CHUNK FOR OTHERS");
						cmDTO.setInstone("");// empty amount after payment
						logger.info("ACTION NAME>>>>>>>inside payment success");
						fm.setPartyresponse("");
						fm.setPartypaydec("");
						String paidAmt = "";
						paidAmt = cmBD.getPaidAmt(cmDTO, pymtsrid);
						fm.setPaidAmt(paidAmt);
						if("en".equalsIgnoreCase(language))
						{
						cmDTO.setConfirmMsg("Case has been updated. "+ "Payment for Rs " + fm.getPaidAmt()+ " has been done successfully");
						}
						else
						{
							cmDTO.setConfirmMsg("प्रकरण अद्यतन किया गया है | "+ fm.getPaidAmt()+ " रुपये के लिए भुगतान किया गया है |");
						}
						FORWAD_SUCCESS = "updationConfirm";
					} 
					else 
					{// adedd for uploaded doc to be saved
						HashMap mapAttachment = cmDTO.getFileDetails();
						if (mapAttachment != null)
						{
							logger.info("I am inside if of mapAttachment");
							Iterator I = map.entrySet().iterator();
							while (I.hasNext()) 
							{
								Map.Entry me = (Map.Entry) I.next();
							}
						}// end
						logger.info("BEFORE COMMENTS UPDATE BD 2 METHOD");
						boolean result = cmBD.commentsUpdateBD2(cmDTO,mapAttachment);
						fm.setPartyresponse("");
						fm.setPartypaydec("");
						fm.setAxnDescrDate(null);
						fm.setReceivingDate(null);
						fm.getCaseMonDTO().setAxnDescr("");
						fm.getCaseMonDTO().setAuthDec("");
						fm.getCaseMonDTO().setFileDetails(null);
						if (result) 
						{
							if (actionName.equalsIgnoreCase("drCommentsUpdate")) {
								if("en".equalsIgnoreCase(language))
								{
								cmDTO.setConfirmMsg("Case has been updated successfully");
								}
								else
								{
									cmDTO.setConfirmMsg("प्रकरण को सफलतापूर्वक अद्यतन किया गया है |");
								}
							} 
							else if (actionName.equalsIgnoreCase("issueNotice")) {
								if("en".equalsIgnoreCase(language))
								{
								cmDTO.setConfirmMsg(fm.getNxtActionName()+ " proceedings have been updated successfully");
								}
								else
								{
									cmDTO.setConfirmMsg(fm.getHiNxtActionName()+ " कार्यवाही को सफलतापूर्वक अद्यतन किया गया है |");
								}
							} 
							else if (actionName.equalsIgnoreCase("closeCase")) {
								if("en".equalsIgnoreCase(language))
								{
								cmDTO.setConfirmMsg("Case has been closed successfully");
								}
								else
								{
									cmDTO.setConfirmMsg("प्रकरण सफलतापूर्वक बंद कर दिया गया है |");
								}
							}
							FORWAD_SUCCESS = "updationConfirm";
						}
						else {
							FORWAD_SUCCESS = "failure";
						}

					}
				}
				// end****************************************************************************************

				// on submit of update screen in case
				else if (formName.equalsIgnoreCase("CmUpdateDetailsFrm") && (actionName.equalsIgnoreCase("drCommentsUpdate")
								|| actionName.equalsIgnoreCase("issueNotice") || actionName.equalsIgnoreCase("closeCase")||actionName.equalsIgnoreCase("paymentAction"))) 
				{
					String caseAxnId = fm.getNxtActionId();
					cmDTO.setCaseActionTypeId(caseAxnId);
					cmDTO.setCaseTxnId(caseTxnId);	
					if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_002"))&& ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_007")) || (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_008")))) //refund cases
					{	logger.info("INSIDE SUBMIT OF CASE HEAD 002 and CASE TYPE007 and 008");
						HashMap mapAttachment = cmDTO.getFileDetails();
						if (mapAttachment != null) {
							logger.info("I am inside if of mapAttachment");
							Iterator I = map.entrySet().iterator();
							while (I.hasNext()) {
								Map.Entry me = (Map.Entry) I.next();
							}
						}// end
						boolean result = cmBD.commentsUpdateBD1(cmDTO,mapAttachment); // in method comments update,action mapping beingdone
						fm.setPartyresponse("");
						fm.setPartypaydec("");
						fm.setAxnDescrDate(null);
						fm.setReceivingDate(null);
						fm.getCaseMonDTO().setAxnDescr("");
						fm.getCaseMonDTO().setAuthDec("");
						fm.getCaseMonDTO().setFileDetails(null);
						if (result)
						{		if (actionName.equalsIgnoreCase("drCommentsUpdate")) 
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setConfirmMsg("Case has been updated successfully");
									}
									else
									{	cmDTO.setConfirmMsg("प्रकरण को सफलतापूर्वक अद्यतन किया गया है |");
									}
								}
								else if (actionName.equalsIgnoreCase("issueNotice")) 
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setConfirmMsg(fm.getNxtActionName()+ " proceedings have been updated successfully");
									}
									else
									{	cmDTO.setConfirmMsg(fm.getHiNxtActionName()+ " कार्यवाही को सफलतापूर्वक अद्यतन किया गया है |");
									}
								} 
								else if (actionName.equalsIgnoreCase("closeCase")) 
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setConfirmMsg("Case has been closed successfully");
									}
									else
									{	cmDTO.setConfirmMsg("प्रकरण सफलतापूर्वक बंद कर दिया गया है |");
									}
								}
								FORWAD_SUCCESS = "updationConfirm";
						}
						else
						{
							FORWAD_SUCCESS = "failure";
						}

					}
					else if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_003")) && ((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_011"))))// adedd-22nd july 2013
					{	logger.info("INSIDE SUBMIT OF CASE HEAD 003 and CASE TYPE 011");
						HashMap mapAttachment = cmDTO.getFileDetails();
						if (mapAttachment != null) {
							logger.info("I am inside if of mapAttachment");
							Iterator I = map.entrySet().iterator();
							while (I.hasNext()) 
							{	Map.Entry me = (Map.Entry) I.next();
							}
						}// end
						boolean result = cmBD.commentsUpdatePenalty45BD(cmDTO,mapAttachment); 
						fm.setPartyresponse("");// in method comments update,action mapping being done
						fm.setPartypaydec("");
						fm.setAxnDescrDate(null);
						fm.setReceivingDate(null);
						fm.getCaseMonDTO().setAxnDescr("");
						fm.getCaseMonDTO().setAuthDec("");
						fm.getCaseMonDTO().setFileDetails(null);
						fm.getCaseMonDTO().setDrComments("");
						fm.setNxtHearDate("");
						if (result) 
							{	if (actionName.equalsIgnoreCase("drCommentsUpdate")) 
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setConfirmMsg("Case has been updated successfully");
									}
									else
									{	cmDTO.setConfirmMsg("प्रकरण को सफलतापूर्वक अद्यतन किया गया है |");
									}
								} 
								else if (actionName.equalsIgnoreCase("issueNotice")) 
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setConfirmMsg(fm.getNxtActionName()+ " proceedings have been updated successfully");
									}
									else
									{	cmDTO.setConfirmMsg(fm.getHiNxtActionName()+ " कार्यवाही को सफलतापूर्वक अद्यतन किया गया है |");
									}
								} 
								else if (actionName.equalsIgnoreCase("closeCase")) 
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setConfirmMsg("Case has been closed successfully");
									}
									else
									{	cmDTO.setConfirmMsg("प्रकरण सफलतापूर्वक बंद कर दिया गया है |");
									}
								}
								FORWAD_SUCCESS = "updationConfirm";
						}
						else {
							FORWAD_SUCCESS = "failure";
						}
					}// end
					else if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_003")) && (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_012")))
					{	logger.info("INSIDE SUBMIT OF CASE HEAD 003 and CASE TYPE 012");	
						HashMap mapAttachment = cmDTO.getFileDetails();
						if (mapAttachment != null) {
							logger.info("I am inside if of mapAttachment");
							Iterator I = map.entrySet().iterator();
							while (I.hasNext()) {
								Map.Entry me = (Map.Entry) I.next();
							}
						}// end
						boolean result = cmBD.commentsUpdatePenalty70BD(cmDTO,mapAttachment); // in method comments update,action mapping being done
						fm.setPartyresponse("");
						fm.setPartypaydec("");
						fm.setAxnDescrDate(null);
						fm.setReceivingDate(null);
						fm.getCaseMonDTO().setAxnDescr("");
						fm.getCaseMonDTO().setAuthDec("");
						fm.getCaseMonDTO().setFileDetails(null);
						fm.setNxtHearDate(null);
						fm.getCaseMonDTO().setDrComments("");
						if (result) 
						{		if (actionName.equalsIgnoreCase("drCommentsUpdate")) {
									if("en".equalsIgnoreCase(language))
									{	cmDTO.setConfirmMsg("Case has been updated successfully");
									}
									else
									{	cmDTO.setConfirmMsg("प्रकरण को सफलतापूर्वक अद्यतन किया गया है |");
									}
								} 
								else if (actionName.equalsIgnoreCase("issueNotice")) 
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setConfirmMsg(fm.getNxtActionName()+ " proceedings have been updated successfully");
									}
									else
									{	cmDTO.setConfirmMsg(fm.getHiNxtActionName()+ " कार्यवाही को सफलतापूर्वक अद्यतन किया गया है |");
									}
								} 
								else if (actionName.equalsIgnoreCase("closeCase")) 
								{	if("en".equalsIgnoreCase(language))
									{	cmDTO.setConfirmMsg("Case has been closed successfully");
									}
									else
									{	cmDTO.setConfirmMsg("प्रकरण सफलतापूर्वक बंद कर दिया गया है |");
									}
								}
								FORWAD_SUCCESS = "updationConfirm";
						} 
						else 
						{
							FORWAD_SUCCESS = "failure";
						}
					}
					else if ((cmDTO.getCaseHeadId().equalsIgnoreCase("CH_003"))&& (cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_009")))
					{	logger.info("INSIDE SUBMIT OF CASE HEAD 003 and CASE TYPE 009");
						HashMap mapAttachment = cmDTO.getFileDetails();
						if (mapAttachment != null) {
							logger.info("I am inside if of mapAttachment");
							Iterator I = map.entrySet().iterator();
							while (I.hasNext()) 
							{
								Map.Entry me = (Map.Entry) I.next();
							}
						}
						boolean result = cmBD.commentsUpdateBD(cmDTO,mapAttachment);
						fm.setPartyresponse("");
						fm.setPartypaydec("");
						fm.setAxnDescrDate(null);
						fm.setReceivingDate(null);
						fm.getCaseMonDTO().setAxnDescr("");
						fm.getCaseMonDTO().setAuthDec("");
						fm.getCaseMonDTO().setFileDetails(null);
						fm.getCaseMonDTO().setActionChk("");
						fm.setNxtHearDate(null);
						fm.getCaseMonDTO().setDrComments("");
						if (result) 
						{		if (actionName.equalsIgnoreCase("drCommentsUpdate"))
								{	if("en".equalsIgnoreCase(language))
										{	cmDTO.setConfirmMsg("Case has been updated successfully");
										}
										else
										{	cmDTO.setConfirmMsg("प्रकरण को सफलतापूर्वक अद्यतन किया गया है |");
										}
								}
								else if (actionName.equalsIgnoreCase("issueNotice")) 
								{	if("en".equalsIgnoreCase(language))
										{	cmDTO.setConfirmMsg(fm.getNxtActionName()+ " proceedings have been updated successfully");
										}
										else
										{	cmDTO.setConfirmMsg(fm.getHiNxtActionName()+ " कार्यवाही को सफलतापूर्वक अद्यतन किया गया है |");
										}
								} 
								else if (actionName.equalsIgnoreCase("closeCase")) 
								{	if("en".equalsIgnoreCase(language))
										{	cmDTO.setConfirmMsg("Case has been closed successfully");
										}
										else
										{	cmDTO.setConfirmMsg("प्रकरण सफलतापूर्वक बंद कर दिया गया है |");
										}
								}
								FORWAD_SUCCESS = "updationConfirm";
						} 
						else 
						{
							FORWAD_SUCCESS = "failure";
						}
					}

					else 
					{	if((fm.getPartyresponse().equalsIgnoreCase("Y"))||(fm.getPartyresponse().equalsIgnoreCase("N")))	
						{	cmDTO.setPartyresponse(fm.getPartyresponse());// added by shruti for update of response flag from form
						}
						if (actionVal.equalsIgnoreCase("payment")) 
						{	caseAxnId = (String) request.getSession().getAttribute("caseAxnTypeId");
							cmDTO.setCaseTxnId(caseTxnId);
							cmDTO.setCaseActionTypeId(caseAxnId);
							String forwardPath = "./CaseMonComplianceAction.do?actionName=paymentsuccess&TRFS=NGI";
							String parentAmount = fm.getCaseMonDTO().getInstone();
							pymtsrid = cmBD.insertCasePymtTxnDtls(cmDTO, userId);
							request.setAttribute("parentModName","Case Monitoring");
							request.setAttribute("parentFunName", "Update");
							request.setAttribute("parentFunId", "FUN_305");	//modified by shruti----28th oct 2013
							request.setAttribute("parentAmount", parentAmount);
							request.setAttribute("parentKey", pymtsrid);
							request.setAttribute("parentTable","IGRS_CASE_PYMT_TXN_DTLS");
							request.setAttribute("parentColumnName", "SR_NO");
							request.setAttribute("parentDistrictId", cmDTO.getParentDistrictId());
							request.setAttribute("parentDistrictName", cmDTO.getParentDistrictName());
							request.setAttribute("parentOfficeId", cmDTO.getParentOfficeId());
							request.setAttribute("parentOfficeName", cmDTO.getParentOfficeName());
							request.setAttribute("parentReferenceId", cmDTO.getCaseTxnId());
							request.setAttribute("forwardPath", forwardPath);
							request.setAttribute("formName", "casemonfrm");
							cmDTO.setActionName("issueNotice");
							cmDTO.setActionVal("paymentsuccess");
							FORWAD_SUCCESS = "payment";
						} 
						else if (actionVal.equalsIgnoreCase("paymentsuccess"))
						{	logger.info("ACTION NAME>>>>>>>inside payment success");
							fm.setPartyresponse("");
							fm.setPartypaydec("");
							String paidAmt = "";
							paidAmt = cmBD.getPaidAmt(cmDTO, pymtsrid);
							fm.setPaidAmt(paidAmt);
							boolean result = cmBD.getPaidAmtUpdateBD(cmDTO);
							if("en".equalsIgnoreCase(language))
							{	cmDTO.setConfirmMsg("Case has been updated. "+ "Payment for Rs " + fm.getPaidAmt()+ " has been done successfully");
							}
							else
							{	cmDTO.setConfirmMsg("प्रकरण अद्यतन किया गया है | "+ fm.getPaidAmt()+ " रुपये के लिए भुगतान किया गया है |");
							}
							FORWAD_SUCCESS = "updationConfirm";
						}
						else 
						{	HashMap mapAttachment = cmDTO.getFileDetails();
							if (mapAttachment != null) 
							{
								logger.debug("I am inside if of mapAttachment");
								Iterator I = map.entrySet().iterator();
								while (I.hasNext())
								{	Map.Entry me = (Map.Entry) I.next();
								}
							}// end
							boolean result = cmBD.commentsUpdateBD(cmDTO,mapAttachment);
							fm.setPartyresponse("");
							fm.setPartypaydec("");
							fm.setAxnDescrDate(null);
							fm.setReceivingDate(null);
							fm.getCaseMonDTO().setAxnDescr("");
							fm.getCaseMonDTO().setAuthDec("");
							fm.getCaseMonDTO().setFileDetails(null);
							fm.getCaseMonDTO().setDrComments("");
							fm.setNxtHearDate(null);
							if (result) 
								{	if (actionName.equalsIgnoreCase("drCommentsUpdate")) 
									{	if("en".equalsIgnoreCase(language))
											{	cmDTO.setConfirmMsg("Case has been updated successfully");
											}
											else
											{	cmDTO.setConfirmMsg("प्रकरण को सफलतापूर्वक अद्यतन किया गया है |");
											}
									} 
									else if (actionName.equalsIgnoreCase("issueNotice")) 
									{	if("en".equalsIgnoreCase(language))
											{	cmDTO.setConfirmMsg(fm.getNxtActionName()+ " proceedings have been updated successfully");
											}
											else
											{	cmDTO.setConfirmMsg(fm.getHiNxtActionName()+ " कार्यवाही को सफलतापूर्वक अद्यतन किया गया है |");
											}
									} 
									else if (actionName.equalsIgnoreCase("closeCase")) 
									{	if("en".equalsIgnoreCase(language))
											{	cmDTO.setConfirmMsg("Case has been closed successfully");
											}
											else
											{	cmDTO.setConfirmMsg("प्रकरण सफलतापूर्वक बंद कर दिया गया है |");
											}
									}
									FORWAD_SUCCESS = "updationConfirm";
							}
							else 
							{	FORWAD_SUCCESS = "failure";
							}
						}// end of else for caset_007
					}
				}

				// on submit of update in case of rrc
				else if (formName.equalsIgnoreCase("CmUpdateRRCDetailsFrm")&& (actionName.equalsIgnoreCase("drCommentsUpdate")
								|| actionName.equalsIgnoreCase("issueDemandNotice")|| actionName.equalsIgnoreCase("issueNotice") 
								|| actionName.equalsIgnoreCase("closeCase")))
				{
					logger.info("INSIDE FORMNAME CmUpdateRRCDetailsFrm AND ACTIONNAME issueDemandNotice");
					String caseAxnId = fm.getNxtActionId();
					cmDTO.setCaseActionTypeId(caseAxnId);
					logger.info("CASE TXN ID>>>>>>" + caseTxnId);
					cmDTO.setCaseTxnId(caseTxnId);
					if ((fm.getPartyresponse() != null)|| (fm.getPartyresponse() != "")) 
					{	cmDTO.setPartyresponse(fm.getPartyresponse());// added by shruti for update of response flag from form
					}
					if (actionVal.equalsIgnoreCase("payment"))
					{	caseAxnId = (String) request.getSession().getAttribute("caseAxnTypeId");
						cmDTO.setCaseTxnId(caseTxnId);
						cmDTO.setCaseActionTypeId(caseAxnId);
						String forwardPath = "./CaseMonComplianceAction.do?actionName=paymentsuccess&TRFS=NGI";
						String parentAmount = fm.getCaseMonDTO().getInstone();
						pymtsrid = cmBD.insertCasePymtTxnDtls(cmDTO, userId);
						request.setAttribute("parentModName","Case Monitoring");
						request.setAttribute("parentFunName", "Update");
						request.setAttribute("parentFunId", "FUN_042");
						request.setAttribute("parentAmount", parentAmount);
						request.setAttribute("parentKey", pymtsrid);
						request.setAttribute("parentTable","IGRS_CASE_PYMT_TXN_DTLS");
						request.setAttribute("parentColumnName", "SR_NO");
						request.setAttribute("forwardPath", forwardPath);
						request.setAttribute("parentDistrictId", cmDTO.getParentDistrictId());//added
						request.setAttribute("parentDistrictName", cmDTO.getParentDistrictName());
						request.setAttribute("parentOfficeId", cmDTO.getParentOfficeId());
						request.setAttribute("parentOfficeName", cmDTO.getParentOfficeName());
						request.setAttribute("parentReferenceId", cmDTO.getCaseTxnId());//end
						request.setAttribute("formName", "casemonfrm");
						cmDTO.setActionName("issueNotice");
						cmDTO.setActionVal("paymentsuccess");
						FORWAD_SUCCESS = "payment";
					} 
					else if (actionVal.equalsIgnoreCase("paymentsuccess"))
					{	logger.info("ACTION NAME>>>>>>>inside payment success");
						fm.setPartyresponse("");
						fm.setPartypaydec("");
						String paidAmt = "";
						paidAmt = cmBD.getPaidAmt(cmDTO, pymtsrid);
						fm.setPaidAmt(paidAmt);
						boolean result = cmBD.getPaidAmtUpdateBD(cmDTO);
						if("en".equalsIgnoreCase(language))
						{	cmDTO.setConfirmMsg("Case has been updated. "+ "Payment for Rs " + fm.getPaidAmt()+ " has been done successfully");
						}
						else
						{  cmDTO.setConfirmMsg("प्रकरण अद्यतन किया गया है | "+ fm.getPaidAmt()+ " रुपये के लिए भुगतान किया गया है |");
						}
						FORWAD_SUCCESS = "updationConfirm";
					}
					else 
					{	HashMap mapAttachment = cmDTO.getFileDetails();
						if (mapAttachment != null) {
							logger.debug("I am inside if of mapAttachment");
							Iterator I = map.entrySet().iterator();
							//logger.debug("I.hasNext():-" + I.hasNext());
							while (I.hasNext()) {
								Map.Entry me = (Map.Entry) I.next();
								/*logger.debug("getKey :-" + me.getKey() + ":"
										+ me.getValue());*/
							}
						}
						boolean result = cmBD.commentsUpdateBD(cmDTO,mapAttachment); 
						fm.setPartyresponse("");// in method commentsupdate,action mapping being done
						fm.setPartypaydec("");
						fm.setAxnDescrDate(null);
						fm.setReceivingDate(null);
						fm.getCaseMonDTO().setAxnDescr("");
						fm.getCaseMonDTO().setAuthDec("");
						fm.getCaseMonDTO().setFileDetails(null);
						fm.setNxtHearDate(null);
						if (result) 
						{	if (actionName.equalsIgnoreCase("drCommentsUpdate"))
							{	if("en".equalsIgnoreCase(language))
								{	cmDTO.setConfirmMsg("Case has been updated successfully");
								}
								else
								{	cmDTO.setConfirmMsg("प्रकरण को सफलतापूर्वक अद्यतन किया गया है |");
								}
							} 
							else if (actionName.equalsIgnoreCase("issueDemandNotice"))
							{	if("en".equalsIgnoreCase(language))
								{	cmDTO.setConfirmMsg("मांग नोटिस कार्यवाही को सफलतापूर्वक अद्यतन किया गया है |");
								}
								else
								{	cmDTO.setConfirmMsg(fm.getHiNxtActionName()+ " कार्यवाही को सफलतापूर्वक अद्यतन किया गया है |");
								}
								
							} 
							else if (actionName.equalsIgnoreCase("issueNotice")) 
							{	if("en".equalsIgnoreCase(language))
								{	cmDTO.setConfirmMsg(fm.getNxtActionName()+ " proceedings have been updated successfully");
								}
								else
								{	cmDTO.setConfirmMsg(fm.getHiNxtActionName()+ " कार्यवाही को सफलतापूर्वक अद्यतन किया गया है |");
								}
							} 
							else if (actionName.equalsIgnoreCase("closeCase")) 
							{	if("en".equalsIgnoreCase(language))
								{	cmDTO.setConfirmMsg("Case has been closed successfully");
								}
								else
								{	cmDTO.setConfirmMsg("प्रकरण सफलतापूर्वक बंद कर दिया गया है |");
								}
							}
							FORWAD_SUCCESS = "updationConfirm";
						} else {
							FORWAD_SUCCESS = "failure";
						}
					}

				}

				// end
				
				
				

				// ************************************************regarding print on update screen
				else if (fwdName != null && fwdName.equalsIgnoreCase("printUpdate")) 
				{
					JasperReport jasperReport = null;
				 	JasperPrint jasperPrint = null;
				 	PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
				 	 
				 	String path=prop.getValue("pdf.location");
				 	HashMap jasperMap=new HashMap();
					jasperMap.put("path", path);
					logger.debug("PATH IS----------------->"+path);
					
					DBUtility dbUtil = new DBUtility();
					if("CASET_001".equalsIgnoreCase(cmDTO.getCaseTypeId())||
							("CASET_002".equalsIgnoreCase(cmDTO.getCaseTypeId()))||
							("CASET_003".equalsIgnoreCase(cmDTO.getCaseTypeId())))
					{
								if(cmDTO.getCaseTxnId()!=null && !" ".equalsIgnoreCase(cmDTO.getCaseTxnId()))
								{
									String revenueHead=cmDTO.getCaseTxnId().substring(2, 6);
									logger.debug("revenue head for notice generation>>>>"+revenueHead);
									if("2103".equalsIgnoreCase(revenueHead))
									{
										
										
										if("en".equalsIgnoreCase(language))
										{
											if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRC.jrxml"));	
												
											}
											else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55AB.jrxml"));	
											}
											else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147.jrxml"));	
											}
											else
											{
											jasperMap.put("revenueHead","48-B");
											jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
											jasperMap.put("officeID", cmDTO.getCurrentOffId());
											jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A3_48B.jrxml"));	
											}
										}
										else
										{
											if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCHindii.jrxml"));	
											}
											else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABHindi.jrxml"));	
											}
											else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147Hindi.jrxml"));	
											}
											else
											{
											jasperMap.put("revenueHead","१८९९ (१८९९ का संख्‍यांक २ )  की धारा ४८-ख )");
											jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
											jasperMap.put("officeID", cmDTO.getCurrentOffId());
											jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A3_48BHindi.jrxml"));	
											}
										}
											
									}
									else if("2105".equalsIgnoreCase(revenueHead))
									{
										if("en".equalsIgnoreCase(language))
										{
											if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRC.jrxml"));	
												
											}
											else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55AB.jrxml"));	
											}
											else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147.jrxml"));	
											}
											else
											{
											jasperMap.put("revenueHead","47-A(3)");
											jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
											jasperMap.put("officeID", cmDTO.getCurrentOffId());
											jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A3_48B.jrxml"));	
											}
										}
										else
										{
											if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCHindii.jrxml"));	
												
											}
											else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABHindi.jrxml"));	
											}
											else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
											{
												jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
												jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147Hindi.jrxml"));	
											}
											else
											{
											jasperMap.put("revenueHead","१८९९ (१८९९ का संख्‍यांक २ )  की धारा ४७ - क (३)");
											jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
											jasperMap.put("officeID", cmDTO.getCurrentOffId());
											jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A3_48BHindi.jrxml"));	
											}
										}
									}
								}
					}
					else if("CASET_004".equalsIgnoreCase(cmDTO.getCaseType()) || "CASET_004".equalsIgnoreCase(cmDTO.getCaseTypeId()))
					{
						if(cmDTO.getCaseTxnId()!=null && !" ".equalsIgnoreCase(cmDTO.getCaseTxnId()))
						{
							
							String sectionHead=cmBD.getSectionHead(cmDTO.getCaseTxnId());
							logger.debug("revenue head for notice generation>>>>"+sectionHead);
							
							
							String estamp=cmBD.getEStampCodeForCaseId(cmDTO.getCaseTxnId());
							
							if(estamp!=null && !("").equalsIgnoreCase(estamp))//get data based on estamp
							{		
							if("33".equalsIgnoreCase(sectionHead) || "SH10".equalsIgnoreCase(sectionHead))
							{
								
								
								if("en".equalsIgnoreCase(language))
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCeStamp.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABeStamp.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147eStamp.jrxml"));	
									}
									else
									{
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case33_38_40eStamp.jrxml"));
									}
								}
								else
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCHindiieStamp.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABHindieStamp.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147HindieStamp.jrxml"));	
									}
									else
									{
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case33_38_40HindieStamp.jrxml"));	
									}
								}
									
							}
							else if("48B".equalsIgnoreCase(sectionHead) || "SH12".equalsIgnoreCase(sectionHead))
							{
								
								
								if("en".equalsIgnoreCase(language))
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCeStamp.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABeStamp.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147eStamp.jrxml"));	
									}
									else
									{
									jasperMap.put("revenueHead","48-B");
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A3_48BeStamp.jrxml"));	
									}
								}
								else
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCHindiieStamp.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABHindieStamp.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147HindieStamp.jrxml"));	
									}
									else
									{
									jasperMap.put("revenueHead","१८९९ (१८९९ का संख्‍यांक २ )  की धारा ४८-ख )");
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A3_48BHindieStamp.jrxml"));	
									}
								}
									
							}
							
							
						}else{//get data based on pstamp
							
							
							if("33".equalsIgnoreCase(sectionHead) || "SH10".equalsIgnoreCase(sectionHead))
							{
								
								
								if("en".equalsIgnoreCase(language))
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCpStamp.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABpStamp.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147pStamp.jrxml"));	
									}
									else
									{
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case33_38_40pStamp.jrxml"));
									}
								}
								else
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCHindiipStamp.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABHindipStamp.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147HindipStamp.jrxml"));	
									}
									else
									{
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case33_38_40HindipStamp.jrxml"));	
									}
								}
									
							}
							else if("48B".equalsIgnoreCase(sectionHead) || "SH12".equalsIgnoreCase(sectionHead))
							{
								
								
								if("en".equalsIgnoreCase(language))
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCpStamp.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABpStamp.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147pStamp.jrxml"));	
									}
									else
									{
									jasperMap.put("revenueHead","48-B");
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A3_48BpStamp.jrxml"));	
									}
								}
								else
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCHindiipStamp.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABHindipStamp.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147HindipStamp.jrxml"));	
									}
									else
									{
									jasperMap.put("revenueHead","१८९९ (१८९९ का संख्‍यांक २ )  की धारा ४८-ख )");
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A3_48BHindipStamp.jrxml"));	
									}
								}
									
							}
							
							
						
							
							
							
						}
						}
					}
					else if("CASET_005".equalsIgnoreCase(cmDTO.getCaseType()) || "CASET_005".equalsIgnoreCase(cmDTO.getCaseTypeId()))
					{
						if(cmDTO.getCaseTxnId()!=null && !" ".equalsIgnoreCase(cmDTO.getCaseTxnId()))
						{
							
							String sectionHead=cmBD.getSectionHead(cmDTO.getCaseTxnId());
							logger.debug("revenue head for notice generation>>>>"+sectionHead);
							if("33".equalsIgnoreCase(sectionHead) || "SH10".equalsIgnoreCase(sectionHead))
							{
								if("en".equalsIgnoreCase(language))
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRC.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55AB.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147.jrxml"));	
									}
									else
									{
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case33_38_40.jrxml"));	
									}
								}
								else
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCHindii.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABHindi.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147Hindi.jrxml"));	
									}
									else
									{
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case33_38_40Hindi.jrxml"));
									}
								}
									
							}
							else if("47A1".equalsIgnoreCase(sectionHead) || "SH2".equalsIgnoreCase(sectionHead))
							{
								if("en".equalsIgnoreCase(language))
								{
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperMap.put("revenueHead","47-A(1)");
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A1_1-A.jrxml"));	
								}
								else
								{
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperMap.put("revenueHead","१८९९ (१८९९ का संख्‍यांक २) की धारा ४७- क (१) ");
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A1_1-AHindi.jrxml"));	
								}
									
							}
							else if("47A1A".equalsIgnoreCase(sectionHead) || "SH14".equalsIgnoreCase(sectionHead))
							{
								if("en".equalsIgnoreCase(language))
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRC.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55AB.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147.jrxml"));	
									}
									else
									{
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperMap.put("revenueHead","47-A(1-A)");
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A1_1-A.jrxml"));
									}
								}
								else
								{
									if("DEMAND NOTICE".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case146MPLRCHindii.jrxml"));	
										
									}
									else if("ATTACHMENT ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormC_55ABHindi.jrxml"));	
									}
									else if("AUCTION ORDER".equalsIgnoreCase(fm.getNxtActionName()))
									{
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("caseFormE_147Hindi.jrxml"));	
									}
									else
									{
									jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
									jasperMap.put("officeID", cmDTO.getCurrentOffId());
									jasperMap.put("revenueHead","१८९९ (१८९९ का संख्‍यांक २) की धारा ४७  - क  (१-क) ");
									jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case47A1_1-AHindi.jrxml"));	
									}
								}
									
							}
						}
					}
					else if("CASET_007".equalsIgnoreCase(cmDTO.getCaseTypeId()))//for refund estamp
					{//to be checked-Roopam-3Feb2014
						if(cmDTO.getCaseTxnId()!=null && !" ".equalsIgnoreCase(cmDTO.getCaseTxnId()))
						{
							
										jasperMap.put("caseTxnID", cmDTO.getCaseTxnId());
										jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("case_49_50_mudrank.jrxml"));	
							
						}
					}
					dbUtil.closeConnection();
					jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
					ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
			 	 	JRPdfExporter exporter = new JRPdfExporter();
			 	 	logger.debug("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
					exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
					exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
					exporter.exportReport();
					logger.debug("Done--->");
					String pdfName=cmDTO.getCaseTxnId();
					ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
			  		response.setContentType("application/pdf");
			  		response.setHeader("Content-Disposition", "attachment; filename=\\"+pdfName+".pdf");
			  		response.setContentLength(pdfReport.size());
			  		ServletOutputStream out = response.getOutputStream();
			  		os.writeTo(out);
			  		out.flush();
			  		out.close();
					
					/*PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					String retPath=pr.getValue("igrs_upload_path")+"/CaseMonitoring.zip";//modified by shruti--30 sep 2014
					logger.debug("Inside print method");
					OutputStream os = new FileOutputStream(new File(retPath));//end
					ZipOutputStream zos = new ZipOutputStream(os);
					logger.info("CASE TYPE>>>>>>"+cmDTO.getCaseTypeId());*/
					
					/*if(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_005"))
					{
						if(((String)session.getAttribute("loggedToOffice"))==""||(String)session.getAttribute("loggedToOffice")==null)
						{
							session.setAttribute("loggedToOffice", "OFC85");
						}
						cmDTO.setOfficeId((String)session.getAttribute("loggedToOffice"));
						CaseMonDTO pdfdto=new CaseMonDTO();
						ArrayList pdfList=cmBD.getSalutationDetails(cmDTO);
						
						for(int i=0;i<pdfList.size();i++)
						{
							CaseMonDTO ndto=(CaseMonDTO)pdfList.get(i);
							pdfdto.setPartyName(ndto.getPartyName());
							pdfdto.setPartyAddress(ndto.getPartyAddress());
							pdfdto.setSrName(ndto.getSrName());
							pdfdto.setDeedInsName(ndto.getDeedInsName());
							pdfdto.setRegDate(ndto.getRegDate());
							pdfdto.setCurrentDate(ndto.getCurrentDate());
							pdfdto.setOfficeName(ndto.getOfficeName());
							//String fileFolderPath="D:/download/";
							String fileFolderPath=pr.getValue("igrs_upload_path")+cmDTO.getCaseTxnId();
							File folder = new File(fileFolderPath);
							logger.debug("Inside print for caset_005 folder path >>>"+folder);
							if (!folder.exists()) 
							{
							folder.mkdirs();
							}
							String fileName="NoticeParty"+i+".pdf";
							File fileToDownload = new File(fileFolderPath+"/"+fileName);
							
							logger.debug("Inside print method fileToDownload path for caset_005>>>"+fileToDownload);
						//ByteArrayOutputStream baos = new ByteArrayOutputStream();
						FileOutputStream baos=new FileOutputStream(fileToDownload);
						Document document = new Document(PageSize.A4, 20, 20, 20, 20);
				
						PdfWriter writer = PdfWriter.getInstance(document, baos);
						document.open(); 
						Cell row=new Cell(new Phrase("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						row.setHeader(true);
						row.setColspan(22);
						Table datatable = new Table(22);
						datatable.setWidth(100);
						datatable.setPadding(3);
						Cell title = new Cell(new Phrase("FORM-II", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
						title.setHorizontalAlignment(Element.ALIGN_CENTER);
						title.setLeading(20);
						title.setColspan(22);
						title.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(title);
						datatable.setBorderWidth(2);
						datatable.setAlignment(1);
						datatable.addCell(row); 
						Cell sectionheader=new Cell(new Phrase("[See Rule 4(2)]", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
						sectionheader.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader.setColspan(22);
						sectionheader.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(sectionheader);
						datatable.setAlignment(2);
						System.out.println("in pdf");
						Cell sectionheader1=new Cell(new Phrase("Form of notice prescribed under sub-rule (2) of rule 4 of", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
						sectionheader1.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader1.setColspan(22);
						sectionheader1.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(sectionheader1);
						datatable.setAlignment(3);
						Cell sectionheader2=new Cell(new Phrase("the Indian Stamp( Madhya Pradesh Prevention of", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
						sectionheader2.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader2.setColspan(22);
						sectionheader2.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(sectionheader2);
						datatable.setAlignment(4);
						Cell sectionheader3=new Cell(new Phrase("Undervaluation of Instruments) Rules, 1975", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
						sectionheader3.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader3.setColspan(22);
						sectionheader3.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(sectionheader3);
						datatable.setAlignment(5);
						Cell to=new Cell(new Phrase("To,", FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						to.setHorizontalAlignment(Element.ALIGN_LEFT);
						to.setColspan(22);
						to.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(to);
						datatable.setAlignment(5);	
						Cell line1=new Cell(new Phrase(pdfdto.getPartyName(), FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL)));
						line1.setColspan(22);
						line1.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line1);
						datatable.setAlignment(7);
						Cell line2=new Cell(new Phrase(pdfdto.getPartyAddress(), FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL)));
						line2.setColspan(22);
						line2.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line2);
						Cell line3=new Cell(new Phrase(" Please take notice that under sub-section (1) of section 33 of the Indian Stamp Act, 1899 (2 of 1899) a reference has been received from the Registering Officer "+pdfdto.getSrName()+" for determination of the market value of the properties covered by an instrument "+pdfdto.getDeedInsName()+" dated the "+pdfdto.getRegDate()+" and the duty payable on the above instrument. A copy of the reference is annexed.", FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						line3.setColspan(22);
						line3.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line3);
						Cell line5=new Cell(new Phrase(" (2) The matter relating to the dteremination of the market value of the properties and the duty payable on the instrument will be taken up for hearing on the _______(date)_________ (camp)_______ at _________ am/pm.", FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL)));
						line5.setColspan(22);
						line5.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line5);
						Cell line6=new Cell(new Phrase(" (3) You are hereby required to submit before the undersigned, on the date of hearing your objections and representations, if any, in writing to show that market value of the property has been truly set forrth in the instrument along with relevant documents if any, also indicating whether you want to adduce any oral evidence and be presenting at the hearing.", FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						line6.setColspan(22);
						line6.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line6);
						Cell line7=new Cell(new Phrase(" (4) If you fail to avail yourself of this opportunity of appearing before the undersigned or indicating whether you want to adduce any oral or documentary evidence as is necessary or producing the relevant documents, no further opportunity will be given and the matter will be disposed of on the basis of the facts available.", FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						line7.setColspan(22);
						line7.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line7);
						Cell line8=new Cell(new Phrase("\nOffice :  "+pdfdto.getOfficeName(), FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						line8.setHorizontalAlignment(Element.ALIGN_LEFT);
						line8.setColspan(11);
						line8.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line8);
						Cell line8a=new Cell(new Phrase("\nCollector", FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						line8a.setHorizontalAlignment(Element.ALIGN_RIGHT);
						line8a.setColspan(11);
						line8a.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line8a);
						Cell line9=new Cell(new Phrase("Station __________", FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						line9.setHorizontalAlignment(Element.ALIGN_JUSTIFIED); 
						line9.setColspan(11);
						line9.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line9);
						Cell line9a=new Cell(new Phrase("__________", FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						line9a.setHorizontalAlignment(Element.ALIGN_RIGHT); 
						line9a.setColspan(11);
						line9a.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line9a);
						Cell line10=new Cell(new Phrase("Date : "+pdfdto.getCurrentDate(), FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						line10.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
						line10.setColspan(11);
						line10.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line10);
						Cell line10a=new Cell(new Phrase("(SEAL)", FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						line10a.setHorizontalAlignment(Element.ALIGN_RIGHT);
						line10a.setColspan(11);
						line10a.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(line10a);
						datatable.setCellsFitPage(true);
						document.add(datatable); 
						document.close();
						baos.close();
	
						//added by shruti
						ZipEntry entry = new ZipEntry("NoticeParty"+(i+1)+".pdf");	
						if(fileToDownload.getParentFile().exists()==false)
						{
							fileToDownload.getParentFile().mkdirs();
						}
						FileInputStream fileInputStream = new FileInputStream(fileToDownload);
					    byte[] buf = new byte[(int) fileToDownload.length()];
					    fileInputStream.read(buf);
					    fileInputStream.close();
					    zos.putNextEntry(entry);
					    zos.write(buf);
					    zos.closeEntry(); 
					    }
					    zos.close();
					    
					    //added by shruti---14 oct 2014
					    response.setContentType("application/zip");   
					       response.setHeader("Content-Disposition","attachment;filename=\"" + retPath + "\"");   
					       try  
					       {   
					    	  System.out.println(retPath);
					          ByteArrayOutputStream baos = new ByteArrayOutputStream();   
					          ZipOutputStream zos1 = new ZipOutputStream(baos);   
					          byte bytes[] = new byte[2048];   
					  
					          FileInputStream fis = new FileInputStream(retPath);   
					          BufferedInputStream bis = new BufferedInputStream(fis);   
					          zos1.putNextEntry(new ZipEntry("FinalZip.zip"));   
					          int bytesRead;   
					          while ((bytesRead = bis.read(bytes)) != -1)   
					          {   
					            zos1.write(bytes, 0, bytesRead);   
					          }   
					  
					           zos1.closeEntry();   
					           bis.close();   
					           fis.close();    
					  
					           zos1.flush();   
					           baos.flush();   
					           zos1.close();   
					           baos.close();   
					  
					           ServletOutputStream op = response.getOutputStream();   
					           op.write(baos.toByteArray());   
					           op.flush();   
					            
					       }catch(IOException ioe)   
					       {   
					           ioe.printStackTrace();   
					       }   

					       //end
				
					}*/
					
					
					/*else if((cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_001"))||
							(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_002"))||
							(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_003"))||
							(cmDTO.getCaseTypeId().equalsIgnoreCase("CASET_004")))
					{
						//added by shruti---29 oct 2014
						if(((String)session.getAttribute("loggedToOffice"))==""||(String)session.getAttribute("loggedToOffice")==null)
						{
							session.setAttribute("loggedToOffice", "OFC85");
						}
						cmDTO.setOfficeId((String)session.getAttribute("loggedToOffice"));
						CaseMonDTO pdfdto=new CaseMonDTO();
						ArrayList pdfList=cmBD.getRevSalutationDetails(cmDTO);
						//end
						for(int i=0;i<pdfList.size();i++)
						{
							CaseMonDTO ndto=(CaseMonDTO)pdfList.get(i);
							pdfdto.setPartyName(ndto.getPartyName());
							pdfdto.setPartyAddress(ndto.getPartyAddress());
							pdfdto.setSrName(ndto.getSrName());
							pdfdto.setDeedInsName(ndto.getDeedInsName());
							pdfdto.setRegDate(ndto.getRegDate());
							pdfdto.setCurrentDate(ndto.getCurrentDate());
							pdfdto.setOfficeName(ndto.getOfficeName());
							pdfdto.setRegNo(ndto.getRegNo());
							pdfdto.setCaseHearDate(ndto.getCaseHearDate());
						//int i=0;
							String fileFolderPath="D:/download/";
							String fileName="NoticeParty"+i+".pdf";
							File fileToDownload = new File(fileFolderPath+fileName);
						//String fileFolderPath="D:/download/";
						String fileFolderPath=pr.getValue("igrs_upload_path")+cmDTO.getCaseTxnId();
						File folder = new File(fileFolderPath);
						logger.debug("Inside print for caset_001 folder path >>>"+folder);
						if (!folder.exists()) 
						{
						folder.mkdirs();
						}
						String fileName="NoticeParty"+i+".pdf";
						File fileToDownload = new File(fileFolderPath+"/"+fileName);
						logger.debug("Inside print method fileToDownload path for caset_001>>>"+fileToDownload);
									
						FileOutputStream baos=new FileOutputStream(fileToDownload);
						Document document = new Document(PageSize.A4, 20, 20, 20, 20);
				
						PdfWriter writer = PdfWriter.getInstance(document, baos);
						document.open();
						Cell row=new Cell(new Phrase("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						row.setHeader(true);
						row.setColspan(22);
						Table estampCerti = new Table(22);
						estampCerti.setWidth(100);
						estampCerti.setPadding(3);
						PdfPTable table = new PdfPTable(18);
						table.setWidthPercentage(100);
						Cell title = new Cell(new Phrase("FORM III", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
						title.setHorizontalAlignment(Element.ALIGN_CENTER);
						title.setLeading(20);
						title.setColspan(22);
						title.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(title);
						estampCerti.setBorderWidth(2);
						estampCerti.setAlignment(1);
						estampCerti.addCell(row); 
						Cell sectionheader=new Cell(new Phrase("[See Rule 4(2)]", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.BOLD)));
						sectionheader.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader.setColspan(22);
						sectionheader.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(sectionheader);
						estampCerti.setAlignment(2);
						System.out.println("in pdf");
						Cell sectionheader1=new Cell(new Phrase("Form of notice prescribed under sub-rule (2) of rule 4 of", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
						sectionheader1.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader1.setColspan(22);
						sectionheader1.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(sectionheader1);
						estampCerti.setAlignment(3);
						Cell sectionheader2=new Cell(new Phrase("the Indian Stamp( Madhya Pradesh Prevention of", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
						sectionheader2.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader2.setColspan(22);
						sectionheader2.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(sectionheader2);
						estampCerti.setAlignment(4);
						Cell sectionheader3=new Cell(new Phrase("Undervaluation of Instruments) Rules, 1975", FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD)));
						sectionheader3.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader3.setColspan(22);
						sectionheader3.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(sectionheader3);
						estampCerti.setAlignment(5);
						Cell to=new Cell(new Phrase("To,", FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						to.setHorizontalAlignment(Element.ALIGN_LEFT);
						to.setColspan(22);
						to.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(to);
						estampCerti.setAlignment(5);
						Cell to1=new Cell(new Phrase(pdfdto.getPartyName(), FontFactory.getFont(FontFactory.TIMES, 13, Font.NORMAL)));
						to1.setHorizontalAlignment(Element.ALIGN_LEFT);
						to1.setColspan(22);
						to1.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(to1);
						estampCerti.setAlignment(5);
						Cell line1=new Cell(new Phrase(" Please take notice that under sub-section (3) of section 47-A of the Indian Stamp Act, 1899 (No. II of 1899),it is proposed to take action for the determination of the market value of the properties covered by an instrument registered as Document No "+pdfdto.getRegNo()+" Dated "+pdfdto.getRegDate()+" and the duty payable on the above instruments.", FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL)));
						line1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
						line1.setColspan(22);
						line1.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(line1);
						estampCerti.setAlignment(7);
						Cell line2=new Cell(new Phrase(" (2) The matter relating to the determination of the market value of the property and the duty payable on the instrument will be taken up for hearing on the (Date)"+pdfdto.getCaseHearDate()+" (Camp)_______ at _________ am/pm.", FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL)));
						line2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
						line2.setColspan(22);
						line2.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(line2);
						estampCerti.setAlignment(8);
						Cell line3=new Cell(new Phrase(" (3) You are hereby required to submit before the undersigned on the date of hearing your objections and representations, if any, in writing to show that the market value of the property has been truly set forth in the instrument along with relevant documents, if any, also indicating whether you want to adduce any oral evidence and be presenting at the hearing.", FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL)));
						line3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
						line3.setColspan(22);
						line3.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(line3);
						estampCerti.setAlignment(9);
						Cell line4=new Cell(new Phrase(" (4) If you fail to avail yourself of the opportunity of appearing before the undersigned or indicating whether you want to adduce any oral or documentary evidence as is necessary or producing the relevant documents, no further opportunity will be given and the matter will be disposed of on the basis of the facts available.", FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL)));
						line4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
						line4.setColspan(22);
						line4.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(line4);
						estampCerti.setAlignment(9);
						Cell line5=new Cell(new Phrase("\nOffice __________ Collector", FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL)));
						line5.setHorizontalAlignment(Element.ALIGN_LEFT);
						line5.setColspan(22);
						line5.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(line5);
						Cell line6=new Cell(new Phrase("Station __________ __________", FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL)));
						line6.setColspan(22);
						line6.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(line6);
						Cell line7=new Cell(new Phrase("Date __________ (SEAL)", FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL)));
						line7.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
						line7.setColspan(22);
						line7.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(line7);
						estampCerti.setCellsFitPage(true);
						document.add(table);
						document.add(estampCerti); 
						document.close();
						baos.close();
	
						ZipEntry entry = new ZipEntry("NoticeParty"+i+".pdf");	
						if(fileToDownload.getParentFile().exists()==false)
						{
							fileToDownload.getParentFile().mkdirs();
						}
						FileInputStream fileInputStream = new FileInputStream(fileToDownload);
					    byte[] buf = new byte[(int) fileToDownload.length()];
					    fileInputStream.read(buf);
					    fileInputStream.close();
					    zos.putNextEntry(entry);
					    zos.write(buf);
					    zos.closeEntry(); 
					    }
					    zos.close();
					    
					    //modified by shruti---30 oct 2014
					    pdfdto.setCaseHearDate("");
					    
					    //end
					    
					  //added by shruti---14 oct 2014
					    response.setContentType("application/zip");   
					       response.setHeader("Content-Disposition","attachment;filename=\"" + retPath + "\"");   
					       try  
					       {   
					    	  System.out.println(retPath);
					          ByteArrayOutputStream baos = new ByteArrayOutputStream();   
					          ZipOutputStream zos1 = new ZipOutputStream(baos);   
					          byte bytes[] = new byte[2048];   
					  
					          FileInputStream fis = new FileInputStream(retPath);   
					          BufferedInputStream bis = new BufferedInputStream(fis);   
					          zos1.putNextEntry(new ZipEntry("FinalZip.zip"));   
					          int bytesRead;   
					          while ((bytesRead = bis.read(bytes)) != -1)   
					          {   
					            zos1.write(bytes, 0, bytesRead);   
					          }   
					  
					           zos1.closeEntry();   
					           bis.close();   
					           fis.close();    
					  
					           zos1.flush();   
					           baos.flush();   
					           zos1.close();   
					           baos.close();   
					  
					           ServletOutputStream op = response.getOutputStream();   
					           op.write(baos.toByteArray());   
					           op.flush();   
					            
					       }catch(IOException ioe)   
					       {   
					           ioe.printStackTrace();   
					       }   
					}
					       //end
*/					FORWAD_SUCCESS = "printUpdatedScreen";
					
				}

				// View Part Start
				// ----------------------------------------------------------------------
				else if (fwdName.equalsIgnoreCase("viewMain")) 
				{
					cmDTO.setCaseHeadList(cmBD.caseHeadStackBD(language,(String)session.getAttribute("role")));
					//CLEAR VALUES HERE
					cmDTO.setCaseTypeName("");//added by roopam-23feb2015
					cmDTO.setCaseHeadName("");//added by roopam-23feb2015
					FORWAD_SUCCESS = "viewMainScreen";
				} 
				else if (formName.equalsIgnoreCase("viewSearchForm") && actionName.equalsIgnoreCase("getCaseType")) 
				{
					cmDTO.setCaseTypeList(cmBD.caseTypeStackBD(cmDTO.getCaseHeadId(),language,(String)session.getAttribute("role")));
					cmDTO.setCaseActionTypeList(null);
					FORWAD_SUCCESS = "viewMainScreen";
				} 
				else if (formName.equalsIgnoreCase("viewSearchForm") && actionName.equalsIgnoreCase("searchResult")) 
				{
					ArrayList searchList = new ArrayList();
					searchList = cmBD.searchListViewBD(cmDTO);

					if (searchList.isEmpty()) {
						if("en".equalsIgnoreCase(language))
						{
							cmDTO.setErrorMsg("Records are not found");
						}
						else
						{
							cmDTO.setErrorMsg("कोई रिकॉर्ड नहीं मिला है |");
						}
					} 
					else 
					{
						cmDTO.setErrorMsg(null);
						request.getSession().setAttribute("caseViewSearchList",searchList);
					}
					FORWAD_SUCCESS = "viewResultScreen";
				}
				else if (formName.equalsIgnoreCase("viewResultForm") && actionName.equalsIgnoreCase("completeView"))
				{
					ArrayList searchList = new ArrayList();
					String caseTxnId = (String) request.getParameter("caseTxnId");
					searchList = cmBD.viewDataRetrieveBD(caseTxnId);
					cmDTO.setRecordsBuffer(searchList);
					request.setAttribute("viewMajorList", fm);
					FORWAD_SUCCESS = "viewDisplayCase";
				}
			} 
			catch (Exception e)
			{
				logger.debug(e.getMessage(),e);
				//logger.debug("in catch");
				return mapping.findForward("failure");
			}
		}
		logger.debug("FORWARD SUCCESS -->" + FORWAD_SUCCESS);
		return mapping.findForward(FORWAD_SUCCESS);
	}

	private String uploadFile(String caseTxnId, byte[] content,
			String fileUploadPath, String fileName) 
	{
		PropertiesFileReader pr=null;
		String filePath=null;
		try {
			pr = PropertiesFileReader.getInstance("resources.igrs");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			filePath = pr.getValue("igrs_upload_path")+CommonConstant.IGRS_CASE_DOC_UPLOAD_PATH + "/"+ caseTxnId + "/";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//logger.info("FILE PATH>>>>>>>>>>>>>>>>>>" + filePath);
		File folder = new File(filePath);
		if (!folder.exists())
		{
			folder.mkdirs();
		}
		try
		{

			File newFile = new File(filePath, fileName);
			if (!newFile.exists()) 
			{
				//logger.info("NEW FILE NAME:-" + newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
			} 
			else 
			{
				newFile = new File(filePath, fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return null;
		}
		return filePath + fileName;
	}

	/*private String getFileExtension(String fileName) 
	{
		try 
		{
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "";
	}*/
}
