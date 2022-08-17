/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :  DutyCalculationAction.java 
 * Author      :  Madan Mohan 
 * Description :   14 jan 2008
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0            Vinay Sharma       05 jun 2013       changes as per new SRS requirements       
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.bankingestamp.action;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
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
import com.lowagie.text.pdf.PdfWriter;
import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.EStamp;
import com.newgen.burning.EStampPartyDetails;
import com.newgen.burning.ODServerDetails;
import com.newgen.burning.ReadPropertiesFile;
import com.octo.captcha.service.CaptchaServiceException;
import com.sun.org.apache.xpath.internal.operations.Equals;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.Seals.bd.SealsBD;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.baseaction.constant.Constants;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.LanguageSelectionAction;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.OTPUtility;
import com.wipro.igrs.deedDraft.bd.DeedDraftBD;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.digitalSign.DigtalSignThread;
import com.wipro.igrs.digitalSign.DocumentService;
import com.wipro.igrs.estamping.bd.EstampingBD;
import com.wipro.igrs.estamping.bo.DutyCalculationBO;
import com.wipro.igrs.estamping.bo.EstampBO;
import com.wipro.igrs.estamping.constant.CommonConstant;
import com.wipro.igrs.estamping.dto.DashBoardDTO;
import com.wipro.igrs.estamping.dto.EstampDTO;
import com.wipro.igrs.estamping.form.DutyCalculationForm;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;
import com.wipro.igrs.util.GUIDGenerator;

/**
 * @since 14 jan 2008
 * @author Madan Mohan
 * @see this class is used for Duty Calculation Action
 * 
 */
public class DutyCalculationAction extends BaseAction {
	
	/**
	 * @see forwardJsp is used for redirecting
	 */
	Locale currentLocale;
	String country;
	ResourceBundle messages;
	private String forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
	/**
	 * @author Madan Mohan
	 */
	private Logger logger = (Logger) Logger.getLogger(DutyCalculationAction.class);
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @exception Exception
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		String language = (String) session.getAttribute("languageLocale");
		String formNameNU = mapping.getName();//added by saurav
		if (form != null) {
			
			DutyCalculationForm eForm = (DutyCalculationForm) form;
			
			DutyCalculationBO bo = new DutyCalculationBO();
			String userId = (String) session.getAttribute("UserId");
			eForm.setUid(userId);
			String page = (String) request.getParameter("page");
			PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
			
			String downloadPath = proper.getValue("igrs_upload_path");
			downloadPath = downloadPath + File.separator + "IGRS";
			eForm.setOwmFlag("");
			eForm.setOwmFile("");
			eForm.setMuncipalityDIspaly("");
			eForm.setStampDisplay("");
			eForm.setJanpadDisplay("");
			eForm.setUpkarDisplay("");
			eForm.setExemptedAmountDisplay("");
			eForm.setEstampTypeCheck("");
			eForm.setAllowPrint("N");
			eForm.setPrintFlag("N");
			eForm.setPrintCheck("");
			eForm.getObjDashBoardDTO().setZeroAmountFlag("N");
			
			System.out.println("Page  " + page);
			DutyCalculationDTO dto = eForm.getDutyCalculationDTO();
			DashBoardDTO objDashBoardDTO1 = eForm.getObjDashBoardDTO();
			// objDashBoardDTO1.setLanguage(language);
			IGRSCommon common = new IGRSCommon();
			System.out.println("Before action");
			String formName = dto.getFormName();
			String actionName = "";
			boolean checkEstamp = false;
			if (request.getParameter("actionName") != null) {
				
				actionName = request.getParameter("actionName");
				
			}

			else

			{
				
				actionName = dto.getActionName();
			}
			// String actionName = dto.getActionName();
			
			String modName = (String) request.getParameter("modName");
			System.out.println("modName:-" + modName);
			String fnName = (String) request.getParameter("fnName");
			System.out.println("fnName:-" + fnName);
			
			if (modName != null && fnName != null) {
				session.setAttribute("modName", modName);
				session.setAttribute("fnName", fnName);
			}
			
			// Added by Lavi
			
			// for creating dashboard
			
			EstampBO objEstampBO = new EstampBO();
			if (request.getParameter("link") == null) {
				if (request.getParameter("modName") != null) {
					if (request.getParameter("modName").equalsIgnoreCase("E-Stamps")) {
						
						ArrayList pendingApplicationList = objEstampBO.getPendingEstampApps(session.getAttribute("UserId").toString(), language);
						eForm.setDeedConsumeFlag("");
						eForm.setDeedDraftId("");
						eForm.setDeedDraftErrorFlag("");
						eForm.setDeedDraftStatus("");
						eForm.setEcode("");
						eForm.setDocPath("");
						eForm.setDocPathComplete("");
						eForm.setHdnAmount("");
						
						logger.info("--------------->" + pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							eForm.getObjDashBoardDTO().setPendingEstampApplicationList(null);
						else
							eForm.getObjDashBoardDTO().setPendingEstampApplicationList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", eForm.getObjDashBoardDTO().getPendingEstampApplicationList());
						logger.info("--------------->succesfully received values");
						forwardJsp = CommonConstant.FORWARD_PAGE_DASHBOARD;
						
						logger.info("--------------->showing dashboard");
						
					}
					
				}
				
			}
			
			// after click on any pending application id from dashboard
			
			if (request.getParameter("hdnApplicationId") != null && request.getAttribute("sourceModFlag") == null) {
				
				String[] txnBalBoth = request.getParameter("hdnApplicationId").toString().split("~");
				
				eForm.getObjDashBoardDTO().setHidnEstampTxnId(txnBalBoth[0]);
				eForm.getObjDashBoardDTO().setBalanceAmount(txnBalBoth[1]);
				eForm.getObjDashBoardDTO().setHidnUserId(userId);
				// objDashBoardDTO1.setFlag("Y");
				// String appStatus[] = new String[5];
				
				try {
					String TxnId = eForm.getObjDashBoardDTO().getHidnEstampTxnId();
					
					objDashBoardDTO1.setHidnEstampTxnId(TxnId);
					
					eForm.setMainTxnId(objDashBoardDTO1.getHidnEstampTxnId());
					
					String balAmnt = (String) eForm.getObjDashBoardDTO().getBalanceAmount();
					
					logger.debug("======= > " + balAmnt);
					
					if (Double.parseDouble(balAmnt) > 0) {
						
						ArrayList dutyDetails = objEstampBO.getDetailsOfDuties(objDashBoardDTO1, language);
						for (int i = 0; i < dutyDetails.size(); i++) {
							DashBoardDTO dto1 = (DashBoardDTO) dutyDetails.get(i);
							logger.debug("<-----" + dto1.getMainTxnId1());
							objDashBoardDTO1.setMainTxnId1((dto1.getMainTxnId1()));
						}
						logger.info("-------dutyDetails-------->" + dutyDetails.size());
						logger.info("-------dutyDetails-------->" + objDashBoardDTO1.getMainTxnId1());
						if (dutyDetails.size() == 0)
							eForm.getObjDashBoardDTO().setDetailsTxnID(null);
						else
							eForm.getObjDashBoardDTO().setDetailsTxnID(dutyDetails);
						
						request.setAttribute("dutyDetails", eForm.getObjDashBoardDTO().getDetailsTxnID());
						
						ArrayList applicantDetails = objEstampBO.getDetailsOfApplicant(TxnId, language);
						logger.info("--------------->" + applicantDetails.size());
						
						if (applicantDetails.size() == 0)
							eForm.getObjDashBoardDTO().setPartyDetails(null);
						else
							eForm.getObjDashBoardDTO().setPartyDetails(applicantDetails);
						
						request.setAttribute("applicantDetails", eForm.getObjDashBoardDTO().getPartyDetails());
						
						for (int i = 0; i < applicantDetails.size(); i++) {
							DashBoardDTO ddto = (DashBoardDTO) applicantDetails.get(i);
							
							logger.info("--------------->" + ddto.getAppStatus());
							logger.info("--------------->" + ddto.getAppType());
							logger.info("--------------->" + ddto.getPartyType());
							logger.info("--------------->" + ddto.getAppAuthFirstName());
							logger.info("--------------->" + ddto.getPartyAuthFirstName());
						}
						
						ArrayList documentDetails = objEstampBO.getDetailsOfDocument(TxnId);
						logger.info("--------------->" + documentDetails.size());
						/*
						 * ArrayList paras = objEstampBO.getParaDetls(TxnId,
						 * language);
						 * 
						 * 
						 * if (paras.size() == 0)
						 * eForm.getObjDashBoardDTO().setParas(new ArrayList());
						 * else eForm.getObjDashBoardDTO().setParas(paras);
						 */

						if (documentDetails.size() == 0)
							eForm.getObjDashBoardDTO().setDocDetails(new ArrayList());
						else
							eForm.getObjDashBoardDTO().setDocDetails(documentDetails);
						
						request.setAttribute("documentDetails", eForm.getObjDashBoardDTO().getDocDetails());
						forwardJsp = CommonConstant.FORWARD_PAGE_SUCCESS;
					} else {
						if (eForm.getMainTxnId() != null) {
							logger.info("------>*****" + eForm.getMainTxnId());
						} else {
							logger.info("------>*****" + objDashBoardDTO1.getHidnEstampTxnId());
							String TxnRequestId = objDashBoardDTO1.getHidnEstampTxnId();
							eForm.setMainTxnId(TxnRequestId);
						}
						
						DecimalFormat myformatter = new DecimalFormat("############");
						// duty//
						String duty = bo.getDuty(eForm.getMainTxnId());
						bo.getDutyDetails(eForm.getMainTxnId(), eForm);
						eForm.getDutyCalculationDTO().setTotal(Double.parseDouble(duty));
						eForm.getDutyCalculationDTO().setTotalDisplay(myformatter.format(Double.parseDouble(duty)));
						// date and time//
						String currDate = bo.getCurrentDate();
						eForm.setCurrentDate(currDate);
						// deeds and instruments and purpose//
						ArrayList second = new ArrayList();
						ArrayList comp1 = new ArrayList();
						second = bo.getDeedDtls(eForm.getMainTxnId(), language);
						if (second.size() > 0) {
							for (int i = 0; i < second.size(); i++) {
								comp1.add((ArrayList) second.get(i));
								if (comp1 != null && comp1.size() > 0) {
									for (int k = 0; k < comp1.size(); k++) {
										ArrayList compSub = (ArrayList) comp1.get(k);
										String deed = (String) compSub.get(0);
										String inst = (String) compSub.get(1);
										String purpose = (String) compSub.get(2);
										String instDiscp = (String) compSub.get(3);
										logger.debug("the deed is.........................." + deed);
										logger.debug("the inst is.........................." + inst);
										logger.debug("the purpose is.........................." + purpose);
										eForm.getDutyCalculationDTO().setDeedType(deed);
										eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
										eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
										
										eForm.getInstDTO().setInstType(inst);
										eForm.setEstampPurpose(purpose);
									}
								}
							}
						}
						// names and office details//
						
						String type = objEstampBO.gettype(userId); // --added by
						// satbir
						// kumar--
						
						logger.debug(type);
						
						/*
						 * String roleId = bo.getRole(userId); String srrole =
						 * "GR1356609170484"; String drrole = "GR1356671177515";
						 * String sprole = "GR1358488496205"; String rurole =
						 * "GR1357368369828"; String spbankrole =
						 * "GR1359453019113";
						 */

						// eForm.getObjDashBoardDTO().setRole(roleId);
						if ("2".equalsIgnoreCase(type)) {
							ArrayList userdet = new ArrayList();
							ArrayList comp2 = new ArrayList();
							userdet = bo.getruName(userId, language);
							if (userdet.size() > 0) {
								for (int i = 0; i < userdet.size(); i++) {
									comp2.add((ArrayList) userdet.get(i));
									if (comp2 != null && comp2.size() > 0) {
										for (int k = 0; k < comp2.size(); k++) {
											ArrayList compSub = (ArrayList) comp2.get(k);
											
											String name = (String) compSub.get(0);
											String cid = (String) compSub.get(1);
											String sid = (String) compSub.get(2);
											String did = (String) compSub.get(3);
											String dname = (String) compSub.get(4);
											
											eForm.setUserName(name);
											if (cid.equalsIgnoreCase("1")) {
												if (sid.equalsIgnoreCase("1")) {
													eForm.setIssuedPlace(dname);
													
												} else {
													if ("en".equalsIgnoreCase(language)) {
														eForm.setIssuedPlace("Others");
													} else {
														eForm.setIssuedPlace("अन्य");
													}
												}
												
											} else {
												if ("en".equalsIgnoreCase(language)) {
													eForm.setIssuedPlace("Others");
												} else {
													eForm.setIssuedPlace("अन्य");
												}
											}
											
										}
									}
								}
							}
							
							eForm.setOfficeName("-");
						}

						else if ("3".equalsIgnoreCase(type)) {
							String spname = bo.getspName(userId);
							String licNo = bo.getspLicenseNo(userId);
							ArrayList spdtls = new ArrayList();
							ArrayList comp3 = new ArrayList();
							spdtls = bo.getspDtls(userId, language);
							if (spdtls.size() > 0) {
								for (int i = 0; i < spdtls.size(); i++) {
									comp3.add((ArrayList) spdtls.get(i));
									if (comp3 != null && comp3.size() > 0) {
										for (int k = 0; k < comp3.size(); k++) {
											ArrayList compSub = (ArrayList) comp3.get(k);
											
											String ofc = (String) compSub.get(0);
											String plc = (String) compSub.get(1);
											String tehsil = (String) compSub.get(2);
											eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
											eForm.setIssuedPlace(plc);
											eForm.setUserName(spname + "/" + licNo);
										}
									}
								}
								
							}
						}

						else if ("4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
							
							ArrayList spbankdtls = new ArrayList();
							ArrayList comp4 = new ArrayList();
							spbankdtls = bo.getspbnkDtls(userId, language);
							if (spbankdtls.size() > 0) {
								for (int i = 0; i < spbankdtls.size(); i++) {
									comp4.add((ArrayList) spbankdtls.get(i));
									if (comp4 != null && comp4.size() > 0) {
										for (int k = 0; k < comp4.size(); k++) {
											ArrayList compSub = (ArrayList) comp4.get(k);
											
											String uname = (String) compSub.get(0);
											String ofc = (String) compSub.get(1);
											String plc = (String) compSub.get(2);
											
											String tehsil = (String) compSub.get(3);
											eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
											eForm.setIssuedPlace(plc);
											eForm.setUserName(uname);
										}
									}
								}
								
							}
						}

						else if ("I".equalsIgnoreCase(type)) {
							ArrayList iudtls = new ArrayList();
							ArrayList comp4 = new ArrayList();
							String officeId = (String) session.getAttribute("loggedToOffice");
							iudtls = bo.getiuDtls(userId, officeId, language);
							if (iudtls.size() > 0) {
								for (int i = 0; i < iudtls.size(); i++) {
									comp4.add((ArrayList) iudtls.get(i));
									if (comp4 != null && comp4.size() > 0) {
										for (int k = 0; k < comp4.size(); k++) {
											ArrayList compSub = (ArrayList) comp4.get(k);
											
											String ofc = (String) compSub.get(0);
											String plc = (String) compSub.get(1);
											String uname = (String) compSub.get(2);
											eForm.setOfficeName(ofc);
											eForm.setIssuedPlace(plc);
											eForm.setUserName(uname);
										}
									}
								}
								
							}
						}
						
						// party details--applicant//
						ArrayList appdtls = new ArrayList();
						ArrayList comp5 = new ArrayList();
						appdtls = bo.getAppDtls(eForm.getMainTxnId(), language);
						if (appdtls.size() > 0) {
							for (int i = 0; i < appdtls.size(); i++) {
								comp5.add((ArrayList) appdtls.get(i));
								if (comp5 != null && comp5.size() > 0) {
									for (int k = 0; k < comp5.size(); k++) {
										ArrayList compSub = (ArrayList) comp5.get(k);
										
										String name = (String) compSub.get(0);
										String cntry = (String) compSub.get(1);
										String state = (String) compSub.get(2);
										String district = (String) compSub.get(3);
										String addrs = (String) compSub.get(4);
										String fathrNme = (String) compSub.get(5);
										String noOfPrsns = (String) compSub.get(6);
										String orgName = (String) compSub.get(7);
										String disId = (String) compSub.get(8);
										String deedDuratn = (String) compSub.get(9);
										
										if (orgName != null) {
											
											eForm.setAppNamedsply(orgName);
											eForm.setAppCountryName(cntry);
											eForm.setAppStateName(state);
											eForm.setAppDistrictName(district);
											eForm.setAppAddress(addrs);
											eForm.setAppAuthPersonName(name);
											eForm.setAppPersons(noOfPrsns);
											eForm.setAppDistrict(disId);
											eForm.setAppFatherName(null);
											eForm.setIsAuthapp(1);
											if (deedDuratn != null)
												eForm.setDeedDuration(deedDuratn);
											else
												eForm.setDeedDuration("-");
										} else {
											eForm.setAppNamedsply(name);
											eForm.setAppCountryName(cntry);
											eForm.setAppStateName(state);
											eForm.setAppDistrictName(district);
											eForm.setAppAddress(addrs);
											eForm.setAppFatherName(fathrNme);
											eForm.setAppPersons(noOfPrsns);
											eForm.setAppDistrict(disId);
											if (deedDuratn != null)
												eForm.setDeedDuration(deedDuratn);
											else
												eForm.setDeedDuration("-");
											
										}
										
									}
								}
							}
							
						}
						
						// party details--party//
						ArrayList partydtls = new ArrayList();
						ArrayList comp6 = new ArrayList();
						partydtls = bo.getPartyDtls(eForm.getMainTxnId(), language);
						if (partydtls.size() > 0) {
							for (int i = 0; i < partydtls.size(); i++) {
								comp6.add((ArrayList) partydtls.get(i));
								if (comp6 != null && comp6.size() > 0) {
									for (int k = 0; k < comp6.size(); k++) {
										ArrayList compSub = (ArrayList) comp6.get(k);
										
										String name = (String) compSub.get(0);
										String cntry = (String) compSub.get(1);
										String state = (String) compSub.get(2);
										String district = (String) compSub.get(3);
										String addrs = (String) compSub.get(4);
										String fathrNme = (String) compSub.get(5);
										String noOfPrsns = (String) compSub.get(6);
										String orgName = (String) compSub.get(7);
										
										if (orgName != null) {
											eForm.setPartyNamedsply(orgName);
											eForm.setPartyCountryName(cntry);
											eForm.setPartyStateName(state);
											eForm.setPartyDistrictName(district);
											eForm.setPartyAddress(addrs);
											eForm.setPartyAuthPersonName(name);
											eForm.setPartyFatherName(null);
											eForm.setPartyPersons(noOfPrsns);
											eForm.setIsAuthparty(1);
										} else {
											eForm.setPartyNamedsply(name);
											eForm.setPartyCountryName(cntry);
											eForm.setPartyStateName(state);
											eForm.setPartyDistrictName(district);
											eForm.setPartyAddress(addrs);
											eForm.setPartyFatherName(fathrNme);
											eForm.setPartyPersons(noOfPrsns);
										}
										
										/*
										 * if (orgName.equalsIgnoreCase(null)){
										 * eForm.setPartyNamedsply(name);
										 * eForm.setPartyCountryName(cntry);
										 * eForm.setPartyStateName(state);
										 * eForm.setPartyDistrictName(district);
										 * eForm.setPartyAddress(addrs);
										 * eForm.setPartyFatherName(fathrNme);
										 * eForm.setPartyPersons(noOfPrsns);
										 * }else{
										 * eForm.setPartyNamedsply(orgName);
										 * eForm.setPartyCountryName(cntry);
										 * eForm.setPartyStateName(state);
										 * eForm.setPartyDistrictName(district);
										 * eForm.setPartyAddress(addrs);
										 * eForm.setPartyAuthPersonName(name);
										 * eForm.setPartyPersons(noOfPrsns);
										 * eForm.setIsAuthparty(1);
										 * 
										 * }
										 */

									}
								}
							}
							
						} else {
							eForm.setPartyNamedsply("-");
							eForm.setPartyCountryName("-");
							eForm.setPartyStateName("-");
							eForm.setPartyDistrictName("-");
							eForm.setPartyAddress("-");
							eForm.setPartyFatherName("-");
							eForm.setPartyPersons("-");
							
						}
						// //////////////////////vinay///////////////
						
						ArrayList adoptdtls = new ArrayList();
						ArrayList comp95 = new ArrayList();
						adoptdtls = bo.getPartyAdoptDtls(eForm.getMainTxnId(), language);
						if (adoptdtls.size() > 0) {
							for (int i = 0; i < partydtls.size(); i++) {
								comp95.add((ArrayList) adoptdtls.get(i));
								if (comp95 != null && comp95.size() > 0) {
									for (int k = 0; k < comp6.size(); k++) {
										ArrayList compSub = (ArrayList) comp95.get(k);
										
										String name = (String) compSub.get(0);
										String cntry = (String) compSub.get(1);
										String state = (String) compSub.get(2);
										String district = (String) compSub.get(3);
										String addrs = (String) compSub.get(4);
										String fathrNme = (String) compSub.get(5);
										String noOfPrsns = (String) compSub.get(6);
										String orgName = (String) compSub.get(7);
										
										if (orgName != null) {
											eForm.setAdoptNameDisplay(orgName);
											eForm.setAdoptCountryName(cntry);
											eForm.setAdoptStateName(state);
											eForm.setAdoptDistrictName(district);
											eForm.setAdoptAddress(addrs);
											eForm.setPartyAuthPersonName(name);
											eForm.setAdoptNoPerson(noOfPrsns);
										} else {
											eForm.setAdoptNameDisplay(name);
											eForm.setAdoptCountryName(cntry);
											eForm.setAdoptStateName(state);
											eForm.setAdoptDistrictName(district);
											eForm.setAdoptAddress(addrs);
											eForm.setAdoptFatherName(fathrNme);
											eForm.setAdoptNoPerson(noOfPrsns);
											eForm.setIsAdoption("Y");
										}
										
										/*
										 * if (orgName.equalsIgnoreCase(null)){
										 * eForm.setPartyNamedsply(name);
										 * eForm.setPartyCountryName(cntry);
										 * eForm.setPartyStateName(state);
										 * eForm.setPartyDistrictName(district);
										 * eForm.setPartyAddress(addrs);
										 * eForm.setPartyFatherName(fathrNme);
										 * eForm.setPartyPersons(noOfPrsns);
										 * }else{
										 * eForm.setPartyNamedsply(orgName);
										 * eForm.setPartyCountryName(cntry);
										 * eForm.setPartyStateName(state);
										 * eForm.setPartyDistrictName(district);
										 * eForm.setPartyAddress(addrs);
										 * eForm.setPartyAuthPersonName(name);
										 * eForm.setPartyPersons(noOfPrsns);
										 * eForm.setIsAuthparty(1);
										 * 
										 * }
										 */

									}
								}
							}
							
						} else {
							eForm.setAdoptNameDisplay("-");
							eForm.setAdoptCountryName("-");
							eForm.setAdoptStateName("-");
							eForm.setAdoptDistrictName("-");
							eForm.setAdoptAddress("-");
							eForm.setAdoptFatherName("-");
							eForm.setAdoptNoPerson("-");
							eForm.setIsAdoption("N");
						}
						
						// ///////////viany/////////////
						// if ecode already exists then only e-stamp certificate
						// generation
						
						ArrayList ecodeExists = new ArrayList();
						ArrayList comp8 = new ArrayList();
						ecodeExists = bo.getEcodeDtls(eForm.getMainTxnId(), language);
						
						if (ecodeExists.size() > 0) {
							
							for (int i = 0; i < ecodeExists.size(); i++) {
								comp8.add((ArrayList) ecodeExists.get(i));
								if (comp8 != null && comp8.size() > 0) {
									for (int k = 0; k < comp8.size(); k++) {
										ArrayList compSub1 = (ArrayList) comp8.get(k);
										
										String ecode = (String) compSub1.get(0);
										String amt = (String) compSub1.get(1);
										String estampType = (String) compSub1.get(2);
										String estampDate = (String) compSub1.get(3);
										String issuedBy = (String) compSub1.get(4);
										String offc = (String) compSub1.get(5);
										String place = (String) compSub1.get(6);
										String validityDt = (String) compSub1.get(7);
										String printCheck = (String) compSub1.get(8);
										if (printCheck != null)
											eForm.setPrintCheck("N");
										
										eForm.setEcode(ecode);
										eForm.getDutyCalculationDTO().setTotalDisplay(amt);
										eForm.setEstampType(estampType);
										/*
										 * SimpleDateFormat sdf1 = new
										 * SimpleDateFormat
										 * ("yyyy-MM-dd- KK:mm:ss");
										 * SimpleDateFormat sdf2 = new
										 * SimpleDateFormat
										 * ("dd/MM/yyyy hh:mm:ss a"); logger
										 * .debug("the date is-----"
										 * +estampDate); Date d1 =
										 * sdf1.parse(estampDate); String
										 * transDate = sdf2.format(d1);
										 */
										eForm.setCurrentDate(estampDate);
										eForm.setUserName(issuedBy);
										eForm.setOfficeName(offc);
										eForm.setIssuedPlace(place);
										eForm.setEstampValidity(validityDt);
										if ("".equalsIgnoreCase(eForm.getStampDisplay().trim()))
											bo.getDutyDetails(eForm.getMainTxnId(), eForm);
										logger.debug("Just before Final page redirection if ecode is already present");
										forwardJsp = "ecodePageForZeroBal";
										
									}
								}
							}
						}

						else {
							// generation of e-code and insertion into the
							// tables.
							try {
								boolean boo = false;
								boo = bo.inserteCode(eForm, objDashBoardDTO1);
								
								if (boo) {
									
									ArrayList ecodedtls = new ArrayList();
									ArrayList comp7 = new ArrayList();
									ecodedtls = bo.getEcodeDtls(eForm.getMainTxnId(), language);
									if (ecodedtls.size() > 0) {
										for (int i = 0; i < ecodedtls.size(); i++) {
											comp7.add((ArrayList) ecodedtls.get(i));
											if (comp7 != null && comp7.size() > 0) {
												for (int k = 0; k < comp7.size(); k++) {
													ArrayList compSub = (ArrayList) comp7.get(k);
													
													String ecode = (String) compSub.get(0);
													String amt = (String) compSub.get(1);
													String estampType = (String) compSub.get(2);
													String estampDate = (String) compSub.get(3);
													String issuedBy = (String) compSub.get(4);
													String offc = (String) compSub.get(5);
													String place = (String) compSub.get(6);
													String validityDt = (String) compSub.get(7);
													String printCheck = (String) compSub.get(8);
													if (printCheck != null)
														eForm.setPrintCheck("N");
													eForm.setEcode(ecode);
													eForm.getDutyCalculationDTO().setTotalDisplay(amt);
													eForm.setEstampType(estampType);
													/*
													 * SimpleDateFormat sdf1 =
													 * new SimpleDateFormat
													 * ("yyyy-MM-dd- KK:mm:ss");
													 * SimpleDateFormat sdf2 =
													 * new SimpleDateFormat
													 * ("dd/MM/yyyy hh:mm:ss a"
													 * ); logger
													 * .debug("the date is-----"
													 * +estampDate); Date d1 =
													 * sdf1.parse(estampDate);
													 * String transDate =
													 * sdf2.format(d1);
													 */
													if ("".equalsIgnoreCase(eForm.getStampDisplay().trim()))
														bo.getDutyDetails(eForm.getMainTxnId(), eForm);
													eForm.setCurrentDate(estampDate);
													eForm.setUserName(issuedBy);
													eForm.setOfficeName(offc);
													eForm.setIssuedPlace(place);
													eForm.setEstampValidity(validityDt);
													logger.debug("Just before Final page redirection");
													forwardJsp = "ecodePageForZeroBal";
													
												}
											}
										}
										
									}
								} else {
									forwardJsp = "failure";
								}
								
							} catch (Exception e) {
								
							}
						}
						
					}
					
				} catch (Exception e) {
					
					logger.debug(e);
					forwardJsp = CommonConstant.FORWARD_PAGE_FAILURE;
					return mapping.findForward(forwardJsp);
					
				}
				
				// forwardJsp = CommonConstant.FORWARD_PAGE_SUCCESS;
				// return mapping.findForward(forwardJsp);
			}
			if (request.getParameter("fromModule") != null) {
				if (request.getParameter("fromModule").equalsIgnoreCase("dutyCalculate")) {
					eForm.setDutyCalculationDTO((DutyCalculationDTO) request.getAttribute("dutyDTO"));
					eForm.setInstDTO((InstrumentsDTO) request.getAttribute("instDTO"));
					eForm.setAppellate(bo.getAppellate(language));
					eForm.setCountryList(bo.getCountry(language));
					eForm.setPhotoIdList(bo.getPhotoId(language));
					eForm.setDeedConsumeFlag("");
					eForm.setDeedDraftId("");
					eForm.setDeedDraftErrorFlag("");
					eForm.setDoc("");
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
				}
			}
			// --addded by satbir kumar for download of image---
			if (request.getParameter("actionName") != null) {
				if (request.getParameter("actionName").equalsIgnoreCase("downLoad1")) {
					try {
						byte contents[] = eForm.getImage1();
						String fileName = eForm.getDoc();
						downloadDocument(response, contents, fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			// ---end of addition by satbir ----
			
			// Added by Lavi on 14th October 2013 for downloading the uploaded
			// scanned document.
			
			if (request.getParameter("dms") != null) {
				if (request.getParameter("dms").equalsIgnoreCase("downloadFromPath")) {
					String filename = request.getParameter("path").toString();
					
					// set the http content type to "APPLICATION/OCTET-STREAM
					response.setContentType("application/download");
					
					// initialize the http content-disposition header to
					// indicate a file attachment with the default filename
					// "myFile.txt"
					String fileName = eForm.getDoc();
					// Filename=\"myFile.txt\"";
					response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
					
					File fileToDownload = new File(filename);
					FileInputStream fileInputStream = new FileInputStream(fileToDownload);
					OutputStream out = response.getOutputStream();
					byte[] buf = new byte[2048];
					
					int readNum;
					while ((readNum = fileInputStream.read(buf)) != -1) {
						out.write(buf, 0, readNum);
					}
					fileInputStream.close();
					out.close();
				}
			}
			
			// end of code Added by Lavi on 14th October 2013 for downloading
			// the uploaded scanned document.
			
			// ---------------payment action starts---------------
			// added by saurav
			if (("estampNU").equalsIgnoreCase(formNameNU)) {
				if (null != userId ) {
					if(!userId.isEmpty()){
						forwardJsp="ecodeDRSViewLogedIn";
						/*ArrayList<String> funcList = (ArrayList<String>) session.getAttribute("fncList");
						if(funcList.contains("FUN_222")){
							forwardJsp="ecodeDRSViewLogedIn";
						}else if(funcList.contains("FUN_221")){
							forwardJsp="ecodeDRSViewLogedIn";
						}*/
					}
				} else {
					if (null == request.getParameter("modNameNU")) {
						logger.debug("------------------->inside search action");
						eForm.getObjDashBoardDTO().setCheck("");
						eForm.getObjDashBoardDTO().setEcode("");
						eForm.getObjDashBoardDTO().setLangSelect("");
						// request.setAttribute("lang","en");
						
						String lang = new String("hi");
						if (null != session.getAttribute("languageLocale")) {
							lang = (String) session.getAttribute("languageLocale");
						}
						if(null != request.getParameter("lang")){
							lang = (String) request.getParameter("lang");
						}
						country = new String("IN");
						currentLocale = new Locale(lang, country);
						session.setAttribute("languageLocale", lang);
						session.setAttribute("org.apache.struts.action.LOCALE", currentLocale);
						messages = ResourceBundle.getBundle("resources.MessagesBundle", currentLocale);
						forwardJsp = CommonConstant.FORWARD_PAGE_SEARCH_NU;
						logger.info("--------------->showing search screen");
						return mapping.findForward(forwardJsp);

					}
				}
				if ("SEARCH_ACTION_NU".equalsIgnoreCase(actionName)) {
					Boolean isResponseCorrect = Boolean.FALSE;
					eForm.getObjDashBoardDTO().setCheck("");
					String captchaId = (String) request.getSession().getAttribute("QARFAD");
					String responses = request.getParameter("j_captcha_response");
					ArrayList ecodeId;
					try {
						isResponseCorrect = IGRSCommon.validateCaptcha(captchaId, responses);
					} catch (CaptchaServiceException e) {
						logger.debug("Failed to get Captcha", e);
					}
					if (!isResponseCorrect) {
						eForm.getObjDashBoardDTO().setCheck("CF");
						forwardJsp = CommonConstant.FORWARD_PAGE_SEARCH_NU;
						logger.debug("----------------> Captcha failed..");
					} else {
						String lang = eForm.getObjDashBoardDTO().getLangSelect();
						if (null == lang) {
							lang = "hi";
						} else if ("".equalsIgnoreCase(lang)) {
							lang = "hi";
						}
						country = new String("IN");
						currentLocale = new Locale(lang, country);
						session.setAttribute("languageLocale", lang);
						session.setAttribute("org.apache.struts.action.LOCALE", currentLocale);
						messages = ResourceBundle.getBundle("resources.MessagesBundle", currentLocale);
						eForm.getObjDashBoardDTO().setLangSelect(lang);
						eForm.getObjDashBoardDTO().setCheck("");
						language=lang;
						ArrayList estampCode;
						String estampCodeId = eForm.getObjDashBoardDTO().getEcode();
						String moduleFlag = bo.moduleFlag(estampCodeId);
						if (null != moduleFlag) {
							if ("E".equalsIgnoreCase(moduleFlag)) {
								logger.debug("--------------->" + estampCodeId);
								estampCode = objEstampBO.checkEcode(estampCodeId);

								logger.debug("----------------> inside search action");

								if (estampCode.size() == 0) {
									eForm.getObjDashBoardDTO().setCheck("Y");
									logger.debug("----------------> no ecode obtained from database");
									forwardJsp = CommonConstant.FORWARD_PAGE_SEARCH_DRS;
									logger.debug("-------------->" + forwardJsp);
								} else {/* ARY */
									String estampid = estampCodeId.substring(3, 4);
									ArrayList ecodeDetailsDRS = new ArrayList();
									if (estampid.equalsIgnoreCase("1")) {
										ecodeDetailsDRS = objEstampBO.viewEcodeDetailsNJ_DRS(estampCodeId, language);
										logger.debug("----------------> ecode obtained");
									} else if (estampid.equalsIgnoreCase("2")) {
										ecodeDetailsDRS = objEstampBO.viewEcodeDetailsDRS(estampCodeId, language);
										logger.debug("----------------> ecode obtained");
									}
									logger.debug("----------------> ecode obtained");
									if (ecodeDetailsDRS.size() == 0)
										eForm.getObjDashBoardDTO().setViewDRSEcodeDetails(null);
									else
										eForm.getObjDashBoardDTO().setViewDRSEcodeDetails(ecodeDetailsDRS);

									request.setAttribute("ecodeDetails", eForm.getObjDashBoardDTO().getViewDRSEcodeDetails());
									logger.debug("----------------> ecodeDetails obtained");

									String ecodeTypeID = estampCodeId.substring(3, 4);

									ArrayList ecodeType = objEstampBO.viewEcodeType(ecodeTypeID, language);

									if (ecodeType.size() == 0)
										eForm.getObjDashBoardDTO().setViewRUEcodeType(null);
									else
										eForm.getObjDashBoardDTO().setViewRUEcodeType(ecodeType);

									request.setAttribute("ecodeType", eForm.getObjDashBoardDTO().getViewRUEcodeType());

									ArrayList viewApplicantDetailsDRS = objEstampBO.getDetailsOfApplicantDRS(estampCodeId, language);
									logger.info("--------------->" + viewApplicantDetailsDRS.size());

									if (viewApplicantDetailsDRS.size() == 0)
										eForm.getObjDashBoardDTO().setPartyDetailsDRS(null);
									else
										eForm.getObjDashBoardDTO().setPartyDetailsDRS(viewApplicantDetailsDRS);

									request.setAttribute("applicantDetails", eForm.getObjDashBoardDTO().getPartyDetailsDRS());

									for (int i = 0; i < viewApplicantDetailsDRS.size(); i++) {
										DashBoardDTO ddto = (DashBoardDTO) viewApplicantDetailsDRS.get(i);

										logger.info("--------------->" + ddto.getAppStatus());
										logger.info("--------------->" + ddto.getAppType());
										logger.info("--------------->" + ddto.getPartyType());
										logger.info("--------------->" + ddto.getAppAuthFirstName());
										logger.info("--------------->" + ddto.getPartyAuthFirstName());
									}

									if ("R".equalsIgnoreCase(moduleFlag)) {
										String txnId = bo.getRegId(estampCodeId);
										bo.getDutyDetailsInitiation(txnId, eForm, "I");
										eForm.setBreifDocument("NA");
									} else if ("C".equalsIgnoreCase(moduleFlag)) {
										String txnId = bo.getRegId(estampCodeId);
										bo.getDutyDetailsInitiation(txnId, eForm, "C");
										eForm.setBreifDocument("NA");
									} else {
										eForm.setBreifDocument(bo.getBreifDocument(estampCodeId));
										bo.getDutyDetails(estampCodeId, eForm);
									}
									eForm.setEstampTypeCheck(bo.getEstampCodeType(estampCodeId));
									forwardJsp = CommonConstant.FORWARD_PAGE_ECODEVIEW_NU;
									logger.debug("----------------> ecodeType obtained");
								}
							} else {
								if ("".equals(moduleFlag)) {
									eForm.getObjDashBoardDTO().setCheck("WE");
								} else {
									eForm.getObjDashBoardDTO().setCheck("RE");
								}
								forwardJsp = CommonConstant.FORWARD_PAGE_SEARCH_NU;
							}
						} else {
							eForm.getObjDashBoardDTO().setCheck("WE");
							forwardJsp = CommonConstant.FORWARD_PAGE_SEARCH_NU;
						}
					}
					// return mapping.findForward(forwardJsp);
					eForm.getObjDashBoardDTO().setEcode("");

				}
				if ("RESET_ACTION_NU".equals(actionName)) {
					logger.debug("----------------> inside reset action");

					// eForm.getObjDashBoardDTO().setEcode("");
					eForm.getObjDashBoardDTO().setCheck("");
					eForm.getObjDashBoardDTO().setEcode("");
					eForm.getObjDashBoardDTO().setLangSelect("");
					forwardJsp = CommonConstant.FORWARD_PAGE_SEARCH_NU;

					return mapping.findForward(forwardJsp);
				}
			}
			// end saurav
			if ("NEXT_TO_PAYMENT_ACTION_DASHBOARD".equals(actionName)) {
				
				boolean checkFlag = bo.checkEstampValidity(eForm.getMainTxnId(), eForm.getDeedDraftId());
				if (!checkFlag) {
					logger.debug("Record to be inserted have an error for estamp txn id:::::::::" + eForm.getMainTxnId());
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(CommonConstant.FAILURE_MSG, "There was a problem generating the estamp. Please try again!!!");
					else
						request.setAttribute(CommonConstant.FAILURE_MSG, "ई-स्टाम्प सुरक्षित नहीं हो पाया है। कृपया पुनः प्रयास करें!!!");
					forwardJsp = CommonConstant.FORWARD_PAGE_DASHBOARD;
					return mapping.findForward(forwardJsp);
				}
				ArrayList first = new ArrayList();
				logger.info("----->" + objDashBoardDTO1.getMainTxnId1());
				String TxnRequestId = objDashBoardDTO1.getMainTxnId1().toString();
				// objDashBoardDTO1.getMainTxnId1();
				logger.info("---------------->" + actionName);
				// TxnRequestId =
				// eForm.getObjDashBoardDTO().getMainTxnId1().toString();
				logger.info("---------------->" + TxnRequestId);
				// first = bo.getPayDtls(eForm.getMainTxnId());
				
				eForm.setMainTxnId(TxnRequestId);
				
				logger.info("---->***" + eForm.getMainTxnId());
				
				first = bo.getPayDtls(TxnRequestId);
				
				if (first.size() == 0) {
					logger.debug("inside if");
					// String duty = bo.getDuty(eForm.getMainTxnId());
					String duty = bo.getDuty((String) TxnRequestId);
					
					eForm.getDutyCalculationDTO().setTotal(Double.parseDouble(duty));
					boolean insrt = false;
					try {
						insrt = bo.insertPay(eForm, objDashBoardDTO1);
						
						if (insrt) {
							
							String type = objEstampBO.gettype(userId); // --added
							// by
							// satbir
							// kumar--
							
							logger.debug(type);
							
							if ("3".equalsIgnoreCase(type) || "4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
								ArrayList spdetails = bo.getDetails(userId, objDashBoardDTO1);
								
								if (spdetails != null) {
									logger.debug(objDashBoardDTO1.getDistrictid());
									logger.debug(objDashBoardDTO1.getDistrictname());
									
									request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
									
									request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
									request.setAttribute("parentOfficeId", "NA");
									request.setAttribute("parentOfficeName", "NA");
									request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
								}
							}
							
							if ("2".equalsIgnoreCase(type)) {
								String txnId = objDashBoardDTO1.getMainTxnId1().toString();
								ArrayList rudetails = bo.getruDetails(userId, objDashBoardDTO1, txnId, language);
								
								if (rudetails != null) {
									logger.debug(objDashBoardDTO1.getDistrictid());
									logger.debug(objDashBoardDTO1.getDistrictname());
									
									request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
									
									request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
									request.setAttribute("parentOfficeId", "NA");
									request.setAttribute("parentOfficeName", "NA");
									request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
								}
							}
							
							if ("I".equalsIgnoreCase(type)) {
								String offcId = (String) session.getAttribute("loggedToOffice");
								ArrayList iudetails = bo.getiuDetails(objDashBoardDTO1, offcId, language);
								
								if (iudetails != null) {
									logger.debug(objDashBoardDTO1.getDistrictid());
									logger.debug(objDashBoardDTO1.getDistrictname());
									
									request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
									
									request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
									request.setAttribute("parentOfficeId", objDashBoardDTO1.getIuofficeid());
									request.setAttribute("parentOfficeName", objDashBoardDTO1.getIuofficename());
									request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
								}
								
							}
							logger.debug("inside if insrt");
							DecimalFormat myformatter = new DecimalFormat("############");
							request.setAttribute("parentModName", "E-Stamping");
							request.setAttribute("parentFunName", "E-Stamp Non Judicial");
							request.setAttribute("parentFunId", "FUN_023");
							request.setAttribute("parentAmount", myformatter.format(eForm.getDutyCalculationDTO().getTotal()));
							request.setAttribute("parentTable", "IGRS_ESTAMP_PAYMENT_DETLS");
							request.setAttribute("parentKey", eForm.getUniqKey());
							request.setAttribute("forwardPath", "./EstampDutyCalculation.do?TRFS=NGI");
							request.setAttribute("parentColumnName", "ESTAMP_PAYMENT_ID");
							request.setAttribute("formName", "estamp");
							
							logger.debug("just before redirection");
							forwardJsp = "nextToPay";
							
						} else {
							forwardJsp = "failure";
						}
					} catch (Exception e) {
						forwardJsp = "failure";
					}
					
				} else {
					
					logger.debug("inside else---means record is found");
					ArrayList comp = new ArrayList();
					String uniqId = "";
					String amtToBePaid = "";
					String paidAmt = "";
					String pymtFlg = "";
					
					for (int i = 0; i < first.size(); i++) {
						comp.add((ArrayList) first.get(i));
						if (comp != null && comp.size() > 0) {
							for (int k = 0; k < comp.size(); k++) {
								ArrayList compSub = (ArrayList) comp.get(k);
								uniqId = (String) compSub.get(0);
								amtToBePaid = (String) compSub.get(1);
								paidAmt = (String) compSub.get(2);
								pymtFlg = (String) compSub.get(3);
								logger.debug("unique id is ............................" + uniqId);
								logger.debug("unpayable amt is ............................" + amtToBePaid);
								logger.debug("paid amt is..................................." + paidAmt);
								logger.debug("pymtFlg is....................................." + pymtFlg);
							}
						}
					}
					double damtToBePaid = Double.parseDouble(amtToBePaid);
					
					if (pymtFlg.equalsIgnoreCase("I")) {
						
						String type = objEstampBO.gettype(userId); // --added by
						// satbir
						// kumar--
						
						logger.debug(type);
						
						if ("3".equalsIgnoreCase(type) || "4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
							ArrayList spdetails = bo.getDetails(userId, objDashBoardDTO1);
							
							if (spdetails != null) {
								logger.debug(objDashBoardDTO1.getDistrictid());
								logger.debug(objDashBoardDTO1.getDistrictname());
								
								request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
								
								request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
								request.setAttribute("parentOfficeId", "NA");
								request.setAttribute("parentOfficeName", "NA");
								request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
							}
						}
						
						if ("2".equalsIgnoreCase(type)) {
							String txnId = objDashBoardDTO1.getMainTxnId1().toString();
							ArrayList rudetails = bo.getruDetails(userId, objDashBoardDTO1, txnId, language);
							
							if (rudetails != null) {
								logger.debug(objDashBoardDTO1.getDistrictid());
								logger.debug(objDashBoardDTO1.getDistrictname());
								
								request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
								
								request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
								request.setAttribute("parentOfficeId", "NA");
								request.setAttribute("parentOfficeName", "NA");
								request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
							}
						}
						
						if ("I".equalsIgnoreCase(type)) {
							String offcId = (String) session.getAttribute("loggedToOffice");
							ArrayList iudetails = bo.getiuDetails(objDashBoardDTO1, offcId, language);
							if (iudetails != null) {
								logger.debug(objDashBoardDTO1.getDistrictid());
								logger.debug(objDashBoardDTO1.getDistrictname());
								
								request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
								
								request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
								request.setAttribute("parentOfficeId", objDashBoardDTO1.getIuofficeid());
								request.setAttribute("parentOfficeName", objDashBoardDTO1.getIuofficename());
								request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
							}
							
						}
						
						DecimalFormat myformatter = new DecimalFormat("############");
						request.setAttribute("parentModName", "E-Stamping");
						request.setAttribute("parentFunName", "E-Stamp Non Judicial");
						request.setAttribute("parentFunId", "FUN_023");
						request.setAttribute("parentAmount", myformatter.format(damtToBePaid));
						request.setAttribute("parentTable", "IGRS_ESTAMP_PAYMENT_DETLS");
						request.setAttribute("parentKey", uniqId);
						request.setAttribute("forwardPath", "./EstampDutyCalculation.do?TRFS=NGI");
						request.setAttribute("parentColumnName", "ESTAMP_PAYMENT_ID");
						request.setAttribute("formName", "estamp");
						
						forwardJsp = "nextToPay";
					}
					
					if (pymtFlg.equalsIgnoreCase("P")) {
						
						String type = objEstampBO.gettype(userId); // --added by
						// satbir
						// kumar--
						
						logger.debug(type);
						
						if ("3".equalsIgnoreCase(type) || "4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
							ArrayList spdetails = bo.getDetails(userId, objDashBoardDTO1);
							
							if (spdetails != null) {
								logger.debug(objDashBoardDTO1.getDistrictid());
								logger.debug(objDashBoardDTO1.getDistrictname());
								
								request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
								
								request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
								request.setAttribute("parentOfficeId", "NA");
								request.setAttribute("parentOfficeName", "NA");
								request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
							}
						}
						
						if ("2".equalsIgnoreCase(type)) {
							String txnId = objDashBoardDTO1.getMainTxnId1().toString();
							ArrayList rudetails = bo.getruDetails(userId, objDashBoardDTO1, txnId, language);
							
							if (rudetails != null) {
								logger.debug(objDashBoardDTO1.getDistrictid());
								logger.debug(objDashBoardDTO1.getDistrictname());
								
								request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
								
								request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
								request.setAttribute("parentOfficeId", "NA");
								request.setAttribute("parentOfficeName", "NA");
								request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
							}
						}
						
						if ("I".equalsIgnoreCase(type)) {
							String offcId = (String) session.getAttribute("loggedToOffice");
							ArrayList iudetails = bo.getiuDetails(objDashBoardDTO1, offcId, language);
							if (iudetails != null) {
								logger.debug(objDashBoardDTO1.getDistrictid());
								logger.debug(objDashBoardDTO1.getDistrictname());
								
								request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
								
								request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
								request.setAttribute("parentOfficeId", objDashBoardDTO1.getIuofficeid());
								request.setAttribute("parentOfficeName", objDashBoardDTO1.getIuofficename());
								request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
							}
							
						}
						double dpaidAmt = Double.parseDouble(paidAmt);
						// condition 1-------------------pending
						// amount-------------
						if (damtToBePaid > dpaidAmt) {
							double bal = damtToBePaid - dpaidAmt;
							boolean insrt = false;
							eForm.getDutyCalculationDTO().setTotal(bal);
							try {
								insrt = bo.insertPay(eForm, objDashBoardDTO1);
								
								if (insrt) {
									
									String type1 = objEstampBO.gettype(userId); // --added
									// by
									// satbir
									// kumar--
									
									logger.debug(type1);
									
									if ("3".equalsIgnoreCase(type1) || "4".equalsIgnoreCase(type1) || "7".equalsIgnoreCase(type1) || "5".equalsIgnoreCase(type1)) {
										ArrayList spdetails = bo.getDetails(userId, objDashBoardDTO1);
										
										if (spdetails != null) {
											logger.debug(objDashBoardDTO1.getDistrictid());
											logger.debug(objDashBoardDTO1.getDistrictname());
											
											request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
											
											request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
											request.setAttribute("parentOfficeId", "NA");
											request.setAttribute("parentOfficeName", "NA");
											request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
										}
									}
									
									if ("2".equalsIgnoreCase(type1)) {
										String txnId = objDashBoardDTO1.getMainTxnId1().toString();
										ArrayList rudetails = bo.getruDetails(userId, objDashBoardDTO1, txnId, language);
										
										if (rudetails != null) {
											logger.debug(objDashBoardDTO1.getDistrictid());
											logger.debug(objDashBoardDTO1.getDistrictname());
											
											request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
											
											request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
											request.setAttribute("parentOfficeId", "NA");
											request.setAttribute("parentOfficeName", "NA");
											request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
										}
									}
									
									if ("I".equalsIgnoreCase(type1)) {
										String offcId = (String) session.getAttribute("loggedToOffice");
										ArrayList iudetails = bo.getiuDetails(objDashBoardDTO1, offcId, language);
										if (iudetails != null) {
											logger.debug(objDashBoardDTO1.getDistrictid());
											logger.debug(objDashBoardDTO1.getDistrictname());
											
											request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
											
											request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
											request.setAttribute("parentOfficeId", objDashBoardDTO1.getIuofficeid());
											request.setAttribute("parentOfficeName", objDashBoardDTO1.getIuofficename());
											request.setAttribute("parentReferenceId", objDashBoardDTO1.getMainTxnId1().toString());
										}
										
									}
									logger.debug("inside if insrt-----of else condition---means record found");
									DecimalFormat myformatter = new DecimalFormat("############");
									request.setAttribute("parentModName", "E-Stamping");
									request.setAttribute("parentFunName", "E-Stamp Non Judicial");
									request.setAttribute("parentFunId", "FUN_023");
									request.setAttribute("parentAmount", myformatter.format(eForm.getDutyCalculationDTO().getTotal()));
									request.setAttribute("parentTable", "IGRS_ESTAMP_PAYMENT_DETLS");
									request.setAttribute("parentKey", eForm.getUniqKey());
									request.setAttribute("forwardPath", "./EstampDutyCalculation.do?TRFS=NGI");
									request.setAttribute("parentColumnName", "ESTAMP_PAYMENT_ID");
									request.setAttribute("formName", "estamp");
									
									logger.debug("just before redirection");
									forwardJsp = "nextToPay";
									
								} else {
									forwardJsp = "failure";
								}
							} catch (Exception e) {
								forwardJsp = "failure";
							}
							
						}
						
						// condition 2-------------------full payment, eCode
						// generation------
						if (damtToBePaid == dpaidAmt) {
							DecimalFormat myformatter = new DecimalFormat("############");
							double bal = damtToBePaid - dpaidAmt;
							// duty//
							String duty = bo.getDuty(TxnRequestId);
							eForm.getDutyCalculationDTO().setTotal(Double.parseDouble(duty));
							eForm.getDutyCalculationDTO().setTotalDisplay(myformatter.format(Double.parseDouble(duty)));
							// date and time//
							String currDate = bo.getCurrentDate();
							eForm.setCurrentDate(currDate);
							// deeds and instruments and purpose//
							ArrayList second = new ArrayList();
							ArrayList comp1 = new ArrayList();
							second = bo.getDeedDtls(TxnRequestId, language);
							if (second.size() > 0) {
								for (int i = 0; i < second.size(); i++) {
									comp1.add((ArrayList) second.get(i));
									if (comp1 != null && comp1.size() > 0) {
										for (int k = 0; k < comp1.size(); k++) {
											ArrayList compSub = (ArrayList) comp1.get(k);
											String deed = (String) compSub.get(0);
											String inst = (String) compSub.get(1);
											String purpose = (String) compSub.get(2);
											String instDiscp = (String) compSub.get(3);
											logger.debug("the deed is.........................." + deed);
											logger.debug("the inst is.........................." + inst);
											logger.debug("the purpose is.........................." + purpose);
											eForm.getDutyCalculationDTO().setDeedType(deed);
											eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
											eForm.getInstDTO().setInstType(inst);
											eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
											eForm.setEstampPurpose(purpose);
										}
									}
								}
							}
							// names and office details//
							
							String type1 = objEstampBO.gettype(userId); // --added
							// by
							// satbir
							// kumar--
							
							logger.debug(type1);
							/*
							 * String roleId = bo.getRole(userId); String srrole
							 * = "GR1356609170484"; String drrole =
							 * "GR1356671177515"; String sprole =
							 * "GR1358488496205"; String rurole =
							 * "GR1357368369828"; String spbankrole =
							 * "GR1359453019113";
							 * 
							 * eForm.getObjDashBoardDTO().setRole(roleId);
							 */

							if ("2".equalsIgnoreCase(type1)) {
								ArrayList userdet = new ArrayList();
								ArrayList comp2 = new ArrayList();
								userdet = bo.getruName(userId, language);
								if (userdet.size() > 0) {
									for (int i = 0; i < userdet.size(); i++) {
										comp2.add((ArrayList) userdet.get(i));
										if (comp2 != null && comp2.size() > 0) {
											for (int k = 0; k < comp2.size(); k++) {
												ArrayList compSub = (ArrayList) comp2.get(k);
												
												String name = (String) compSub.get(0);
												String cid = (String) compSub.get(1);
												String sid = (String) compSub.get(2);
												String did = (String) compSub.get(3);
												String dname = (String) compSub.get(4);
												
												eForm.setUserName(name);
												if (cid.equalsIgnoreCase("1")) {
													if (sid.equalsIgnoreCase("1")) {
														eForm.setIssuedPlace(dname);
														
													} else {
														if ("en".equalsIgnoreCase(language)) {
															eForm.setIssuedPlace("Others");
														} else {
															eForm.setIssuedPlace("अन्य");
														}
													}
													
												} else {
													if ("en".equalsIgnoreCase(language)) {
														eForm.setIssuedPlace("Others");
													} else {
														eForm.setIssuedPlace("अन्य");
													}
												}
												
											}
										}
									}
								}
								
								eForm.setOfficeName("-");
							}

							else if ("3".equalsIgnoreCase(type)) {
								String spname = bo.getspName(userId);
								
								String licNo = bo.getspLicenseNo(userId);
								ArrayList spdtls = new ArrayList();
								ArrayList comp3 = new ArrayList();
								spdtls = bo.getspDtls(userId, language);
								if (spdtls.size() > 0) {
									for (int i = 0; i < spdtls.size(); i++) {
										comp3.add((ArrayList) spdtls.get(i));
										if (comp3 != null && comp3.size() > 0) {
											for (int k = 0; k < comp3.size(); k++) {
												ArrayList compSub = (ArrayList) comp3.get(k);
												
												String ofc = (String) compSub.get(0);
												String plc = (String) compSub.get(1);
												String tehsil = (String) compSub.get(2);
												eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
												eForm.setIssuedPlace(plc);
												eForm.setUserName(spname + "/" + licNo);
											}
										}
									}
									
								}
							}

							else if ("4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
								
								ArrayList spbankdtls = new ArrayList();
								ArrayList comp4 = new ArrayList();
								spbankdtls = bo.getspbnkDtls(userId, language);
								if (spbankdtls.size() > 0) {
									for (int i = 0; i < spbankdtls.size(); i++) {
										comp4.add((ArrayList) spbankdtls.get(i));
										if (comp4 != null && comp4.size() > 0) {
											for (int k = 0; k < comp4.size(); k++) {
												ArrayList compSub = (ArrayList) comp4.get(k);
												
												String uname = (String) compSub.get(0);
												String ofc = (String) compSub.get(1);
												String plc = (String) compSub.get(2);
												String tehsil = (String) compSub.get(3);
												eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
												eForm.setIssuedPlace(plc);
												eForm.setUserName(uname);
											}
										}
									}
									
								}
							}

							else if ("I".equalsIgnoreCase(type)) {
								ArrayList iudtls = new ArrayList();
								ArrayList comp4 = new ArrayList();
								String officeId = (String) session.getAttribute("loggedToOffice");
								iudtls = bo.getiuDtls(userId, officeId, language);
								if (iudtls.size() > 0) {
									for (int i = 0; i < iudtls.size(); i++) {
										comp4.add((ArrayList) iudtls.get(i));
										if (comp4 != null && comp4.size() > 0) {
											for (int k = 0; k < comp4.size(); k++) {
												ArrayList compSub = (ArrayList) comp4.get(k);
												
												String ofc = (String) compSub.get(0);
												String plc = (String) compSub.get(1);
												String uname = (String) compSub.get(2);
												eForm.setOfficeName(ofc);
												eForm.setIssuedPlace(plc);
												eForm.setUserName(uname);
											}
										}
									}
									
								}
							}
							
							// party details--applicant//
							ArrayList appdtls = new ArrayList();
							ArrayList comp5 = new ArrayList();
							appdtls = bo.getAppDtls(TxnRequestId, language);
							if (appdtls.size() > 0) {
								for (int i = 0; i < appdtls.size(); i++) {
									comp5.add((ArrayList) appdtls.get(i));
									if (comp5 != null && comp5.size() > 0) {
										for (int k = 0; k < comp5.size(); k++) {
											ArrayList compSub = (ArrayList) comp5.get(k);
											
											String name = (String) compSub.get(0);
											String cntry = (String) compSub.get(1);
											String state = (String) compSub.get(2);
											String district = (String) compSub.get(3);
											String addrs = (String) compSub.get(4);
											String fathrNme = (String) compSub.get(5);
											String noOfPrsns = (String) compSub.get(6);
											String orgName = (String) compSub.get(7);
											String disId = (String) compSub.get(8);
											
											if (orgName == (null) || orgName.equalsIgnoreCase("")) {
												eForm.setAppNamedsply(name);
												eForm.setAppCountryName(cntry);
												eForm.setAppStateName(state);
												eForm.setAppDistrictName(district);
												eForm.setAppAddress(addrs);
												eForm.setAppFatherName(fathrNme);
												eForm.setAppPersons(noOfPrsns);
												eForm.setAppDistrict(disId);
											} else {
												eForm.setAppNamedsply(orgName);
												eForm.setAppCountryName(cntry);
												eForm.setAppStateName(state);
												eForm.setAppDistrictName(district);
												eForm.setAppAddress(addrs);
												eForm.setAppAuthPersonName(name);
												eForm.setAppPersons(noOfPrsns);
												eForm.setAppDistrict(disId);
												eForm.setIsAuthapp(1);
												
											}
											
										}
									}
								}
								
							}
							
							// party details--party//
							ArrayList partydtls = new ArrayList();
							ArrayList comp6 = new ArrayList();
							partydtls = bo.getPartyDtls(TxnRequestId, language);
							if (partydtls.size() > 0) {
								for (int i = 0; i < partydtls.size(); i++) {
									comp6.add((ArrayList) partydtls.get(i));
									if (comp6 != null && comp6.size() > 0) {
										for (int k = 0; k < comp6.size(); k++) {
											ArrayList compSub = (ArrayList) comp6.get(k);
											
											String name = (String) compSub.get(0);
											String cntry = (String) compSub.get(1);
											String state = (String) compSub.get(2);
											String district = (String) compSub.get(3);
											String addrs = (String) compSub.get(4);
											String fathrNme = (String) compSub.get(5);
											String noOfPrsns = (String) compSub.get(6);
											String orgName = (String) compSub.get(7);
											
											if (orgName == (null) || orgName.equalsIgnoreCase("")) {
												eForm.setPartyNamedsply(name);
												eForm.setPartyCountryName(cntry);
												eForm.setPartyStateName(state);
												eForm.setPartyDistrictName(district);
												eForm.setPartyAddress(addrs);
												eForm.setPartyFatherName(fathrNme);
												eForm.setPartyPersons(noOfPrsns);
											} else {
												eForm.setPartyNamedsply(orgName);
												eForm.setPartyCountryName(cntry);
												eForm.setPartyStateName(state);
												eForm.setPartyDistrictName(district);
												eForm.setPartyAddress(addrs);
												eForm.setPartyAuthPersonName(name);
												eForm.setPartyPersons(noOfPrsns);
												eForm.setIsAuthparty(1);
												
											}
											
										}
									}
								}
								
							} else {
								eForm.setPartyNamedsply("-");
								eForm.setPartyCountryName("-");
								eForm.setPartyStateName("-");
								eForm.setPartyDistrictName("-");
								eForm.setPartyAddress("-");
								eForm.setPartyFatherName("-");
								eForm.setPartyPersons("-");
								
							}
							// generation of e-code and insertion into the
							// tables.
							try {
								boolean boo = false;
								boo = bo.inserteCode(eForm, objDashBoardDTO1);
								
								if (boo) {
									
									ArrayList ecodedtls = new ArrayList();
									ArrayList comp7 = new ArrayList();
									ecodedtls = bo.getEcodeDtls(TxnRequestId, language);
									if (ecodedtls.size() > 0) {
										for (int i = 0; i < ecodedtls.size(); i++) {
											comp7.add((ArrayList) ecodedtls.get(i));
											if (comp7 != null && comp7.size() > 0) {
												for (int k = 0; k < comp7.size(); k++) {
													ArrayList compSub = (ArrayList) comp7.get(k);
													
													String ecode = (String) compSub.get(0);
													String amt = (String) compSub.get(1);
													String estampType = (String) compSub.get(2);
													String estampDate = (String) compSub.get(3);
													String issuedBy = (String) compSub.get(4);
													String offc = (String) compSub.get(5);
													String place = (String) compSub.get(6);
													String validityDt = (String) compSub.get(7);
													eForm.setEcode(ecode);
													eForm.getDutyCalculationDTO().setTotalDisplay(amt);
													eForm.setEstampType(estampType);
													eForm.setCurrentDate(estampDate);
													eForm.setUserName(issuedBy);
													eForm.setOfficeName(offc);
													eForm.setIssuedPlace(place);
													eForm.setEstampValidity(validityDt);
													
													forwardJsp = "ecodePage";
													
												}
											}
										}
										
									}
								} else {
									forwardJsp = "failure";
								}
								
							} catch (Exception e) {
								
							}
							
						}// --------end second condition
						
					}// -------end paymnent flag=P
					
					if (pymtFlg.equalsIgnoreCase("C")) {
						
						DecimalFormat myformatter = new DecimalFormat("############");
						// double bal= damtToBePaid-dpaidAmt;
						// duty//
						String duty = bo.getDuty(TxnRequestId);
						eForm.getDutyCalculationDTO().setTotal(Double.parseDouble(duty));
						eForm.getDutyCalculationDTO().setTotalDisplay(myformatter.format(Double.parseDouble(duty)));
						// date and time//
						String currDate = bo.getCurrentDate();
						eForm.setCurrentDate(currDate);
						// deeds and instruments and purpose//
						ArrayList second = new ArrayList();
						ArrayList comp1 = new ArrayList();
						second = bo.getDeedDtls(TxnRequestId, language);
						if (second.size() > 0) {
							for (int i = 0; i < second.size(); i++) {
								comp1.add((ArrayList) second.get(i));
								if (comp1 != null && comp1.size() > 0) {
									for (int k = 0; k < comp1.size(); k++) {
										ArrayList compSub = (ArrayList) comp1.get(k);
										String deed = (String) compSub.get(0);
										String inst = (String) compSub.get(1);
										String purpose = (String) compSub.get(2);
										String instDiscp = (String) compSub.get(3);
										logger.debug("the deed is.........................." + deed);
										logger.debug("the inst is.........................." + inst);
										logger.debug("the purpose is.........................." + purpose);
										eForm.getDutyCalculationDTO().setDeedType(deed);
										eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
										eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
										eForm.getInstDTO().setInstType(inst);
										eForm.setEstampPurpose(purpose);
									}
								}
							}
						}
						// names and office details//
						String type = objEstampBO.gettype(userId); // --added by
						// satbir
						// kumar--
						
						logger.debug(type);
						
						/*
						 * String roleId = bo.getRole(userId); String srrole =
						 * "GR1356609170484"; String drrole = "GR1356671177515";
						 * String sprole = "GR1358488496205"; String rurole =
						 * "GR1357368369828"; String spbankrole =
						 * "GR1359453019113";
						 * 
						 * eForm.getObjDashBoardDTO().setRole(roleId);
						 */

						if ("2".equalsIgnoreCase(type)) {
							ArrayList userdet = new ArrayList();
							ArrayList comp2 = new ArrayList();
							userdet = bo.getruName(userId, language);
							if (userdet.size() > 0) {
								for (int i = 0; i < userdet.size(); i++) {
									comp2.add((ArrayList) userdet.get(i));
									if (comp2 != null && comp2.size() > 0) {
										for (int k = 0; k < comp2.size(); k++) {
											ArrayList compSub = (ArrayList) comp2.get(k);
											
											String name = (String) compSub.get(0);
											String cid = (String) compSub.get(1);
											String sid = (String) compSub.get(2);
											String did = (String) compSub.get(3);
											String dname = (String) compSub.get(4);
											
											eForm.setUserName(name);
											if (cid.equalsIgnoreCase("1")) {
												if (sid.equalsIgnoreCase("1")) {
													eForm.setIssuedPlace(dname);
													
												} else {
													if ("en".equalsIgnoreCase(language)) {
														eForm.setIssuedPlace("Others");
													} else {
														eForm.setIssuedPlace("अन्य");
													}
												}
												
											} else {
												if ("en".equalsIgnoreCase(language)) {
													eForm.setIssuedPlace("Others");
												} else {
													eForm.setIssuedPlace("अन्य");
												}
											}
											
										}
									}
								}
							}
							
							eForm.setOfficeName("-");
						}

						else if ("3".equalsIgnoreCase(type)) {
							String spname = bo.getspName(userId);
							String licNo = bo.getspLicenseNo(userId);
							ArrayList spdtls = new ArrayList();
							ArrayList comp3 = new ArrayList();
							spdtls = bo.getspDtls(userId, language);
							if (spdtls.size() > 0) {
								for (int i = 0; i < spdtls.size(); i++) {
									comp3.add((ArrayList) spdtls.get(i));
									if (comp3 != null && comp3.size() > 0) {
										for (int k = 0; k < comp3.size(); k++) {
											ArrayList compSub = (ArrayList) comp3.get(k);
											
											String ofc = (String) compSub.get(0);
											String plc = (String) compSub.get(1);
											String tehsil = (String) compSub.get(2);
											eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
											eForm.setIssuedPlace(plc);
											eForm.setUserName(spname + "/" + licNo);
										}
									}
								}
								
							}
						}

						else if ("4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
							
							ArrayList spbankdtls = new ArrayList();
							ArrayList comp4 = new ArrayList();
							spbankdtls = bo.getspbnkDtls(userId, language);
							if (spbankdtls.size() > 0) {
								for (int i = 0; i < spbankdtls.size(); i++) {
									comp4.add((ArrayList) spbankdtls.get(i));
									if (comp4 != null && comp4.size() > 0) {
										for (int k = 0; k < comp4.size(); k++) {
											ArrayList compSub = (ArrayList) comp4.get(k);
											
											String uname = (String) compSub.get(0);
											String ofc = (String) compSub.get(1);
											String plc = (String) compSub.get(2);
											String tehsil = (String) compSub.get(3);
											eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
											eForm.setIssuedPlace(plc);
											eForm.setUserName(uname);
										}
									}
								}
								
							}
						}

						else if ("I".equalsIgnoreCase(type)) {
							ArrayList iudtls = new ArrayList();
							ArrayList comp4 = new ArrayList();
							String officeId = (String) session.getAttribute("loggedToOffice");
							iudtls = bo.getiuDtls(userId, officeId, language);
							if (iudtls.size() > 0) {
								for (int i = 0; i < iudtls.size(); i++) {
									comp4.add((ArrayList) iudtls.get(i));
									if (comp4 != null && comp4.size() > 0) {
										for (int k = 0; k < comp4.size(); k++) {
											ArrayList compSub = (ArrayList) comp4.get(k);
											
											String ofc = (String) compSub.get(0);
											String plc = (String) compSub.get(1);
											String uname = (String) compSub.get(2);
											eForm.setOfficeName(ofc);
											eForm.setIssuedPlace(plc);
											eForm.setUserName(uname);
										}
									}
								}
								
							}
						}
						
						// party details--applicant//
						ArrayList appdtls = new ArrayList();
						ArrayList comp5 = new ArrayList();
						appdtls = bo.getAppDtls(TxnRequestId, language);
						if (appdtls.size() > 0) {
							for (int i = 0; i < appdtls.size(); i++) {
								comp5.add((ArrayList) appdtls.get(i));
								if (comp5 != null && comp5.size() > 0) {
									for (int k = 0; k < comp5.size(); k++) {
										ArrayList compSub = (ArrayList) comp5.get(k);
										
										String name = (String) compSub.get(0);
										String cntry = (String) compSub.get(1);
										String state = (String) compSub.get(2);
										String district = (String) compSub.get(3);
										String addrs = (String) compSub.get(4);
										String fathrNme = (String) compSub.get(5);
										String noOfPrsns = (String) compSub.get(6);
										String orgName = (String) compSub.get(7);
										String disId = (String) compSub.get(8);
										
										if (orgName == (null) || orgName.equalsIgnoreCase("")) {
											eForm.setAppNamedsply(name);
											eForm.setAppCountryName(cntry);
											eForm.setAppStateName(state);
											eForm.setAppDistrictName(district);
											eForm.setAppAddress(addrs);
											eForm.setAppFatherName(fathrNme);
											eForm.setAppPersons(noOfPrsns);
											eForm.setAppDistrict(disId);
										} else {
											eForm.setAppNamedsply(orgName);
											eForm.setAppCountryName(cntry);
											eForm.setAppStateName(state);
											eForm.setAppDistrictName(district);
											eForm.setAppAddress(addrs);
											eForm.setAppAuthPersonName(name);
											eForm.setAppPersons(noOfPrsns);
											eForm.setAppDistrict(disId);
											eForm.setIsAuthapp(1);
											
										}
										
									}
								}
							}
							
						}
						
						// party details--party//
						ArrayList partydtls = new ArrayList();
						ArrayList comp6 = new ArrayList();
						partydtls = bo.getPartyDtls(TxnRequestId, language);
						if (partydtls.size() > 0) {
							for (int i = 0; i < partydtls.size(); i++) {
								comp6.add((ArrayList) partydtls.get(i));
								if (comp6 != null && comp6.size() > 0) {
									for (int k = 0; k < comp6.size(); k++) {
										ArrayList compSub = (ArrayList) comp6.get(k);
										
										String name = (String) compSub.get(0);
										String cntry = (String) compSub.get(1);
										String state = (String) compSub.get(2);
										String district = (String) compSub.get(3);
										String addrs = (String) compSub.get(4);
										String fathrNme = (String) compSub.get(5);
										String noOfPrsns = (String) compSub.get(6);
										String orgName = (String) compSub.get(7);
										
										if (orgName == (null) || orgName.equalsIgnoreCase("")) {
											eForm.setPartyNamedsply(name);
											eForm.setPartyCountryName(cntry);
											eForm.setPartyStateName(state);
											eForm.setPartyDistrictName(district);
											eForm.setPartyAddress(addrs);
											eForm.setPartyFatherName(fathrNme);
											eForm.setPartyPersons(noOfPrsns);
										} else {
											eForm.setPartyNamedsply(orgName);
											eForm.setPartyCountryName(cntry);
											eForm.setPartyStateName(state);
											eForm.setPartyDistrictName(district);
											eForm.setPartyAddress(addrs);
											eForm.setPartyAuthPersonName(name);
											eForm.setPartyPersons(noOfPrsns);
											eForm.setIsAuthparty(1);
											
										}
										
									}
								}
							}
							
						} else {
							eForm.setPartyNamedsply("-");
							eForm.setPartyCountryName("-");
							eForm.setPartyStateName("-");
							eForm.setPartyDistrictName("-");
							eForm.setPartyAddress("-");
							eForm.setPartyFatherName("-");
							eForm.setPartyPersons("-");
							
						}
						// e-code details.
						
						ArrayList ecodedtls = new ArrayList();
						ArrayList comp7 = new ArrayList();
						ecodedtls = bo.getEcodeDtls(TxnRequestId, language);
						if (ecodedtls.size() > 0) {
							for (int i = 0; i < ecodedtls.size(); i++) {
								comp7.add((ArrayList) ecodedtls.get(i));
								if (comp7 != null && comp7.size() > 0) {
									for (int k = 0; k < comp7.size(); k++) {
										ArrayList compSub = (ArrayList) comp7.get(k);
										
										String ecode = (String) compSub.get(0);
										String amt = (String) compSub.get(1);
										String estampType = (String) compSub.get(2);
										String estampDate = (String) compSub.get(3);
										String issuedBy = (String) compSub.get(4);
										String offc = (String) compSub.get(5);
										String place = (String) compSub.get(6);
										String validityDt = (String) compSub.get(7);
										eForm.setEcode(ecode);
										eForm.getDutyCalculationDTO().setTotalDisplay(amt);
										eForm.setEstampType(estampType);
										eForm.setCurrentDate(estampDate);
										eForm.setUserName(issuedBy);
										eForm.setOfficeName(offc);
										eForm.setIssuedPlace(place);
										eForm.setEstampValidity(validityDt);
										
										forwardJsp = "ecodePage";
										
									}
								}
							}
							
						}
						
					}// end payment flag=C
					
				}// -----------------end else condition--means record found.
				
			}
			
			if ("ecodeFinalPage".equalsIgnoreCase(formName)) {
				
				logger.debug("ESTAMP ID BEFORE PDF IN ECODE FINAL PAGE------>" + eForm.getMainTxnId());
				
				if ("COMPLETE_TXN".equalsIgnoreCase(actionName)) {
					
					logger.debug("ESTAMP ID BEFORE PDF------>" + eForm.getMainTxnId());
					// For the pdf creation of E-stamp certificate.
					
					// For the pdf creation of E-stamp certificate.
					String estampGenCert = objEstampBO.getCertChkDetails(eForm.getMainTxnId().toString());
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					DocumentOperations docOperations = new DocumentOperations();
					ODServerDetails connDetails = new ODServerDetails();
					Dataclass metaDataInfo = new Dataclass();
					connDetails.setAppServerIp(pr.getValue("AppServerIp"));
					connDetails.setCabinetName(pr.getValue("CabinetName"));
					connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
					connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
					connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
					connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
					connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
					metaDataInfo.setUniqueNo(eForm.getEcode().toString());
					metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
					if (estampGenCert.equalsIgnoreCase("Y")) // Certificate
					// already
					// generated
					// .....code to
					// download that
					{
						
						// ArrayList<DocumentDetails> docList =
						// docOperations.searchDocs(connDetails, metaDataInfo,
						// null);
						// DocumentDetails docDetails = new DocumentDetails();
						/*
						 * byte[] bytes =null; for(int i = 0; i <
						 * docList.size(); i ++){
						 * connDetails.setAppServerIp(pr.getValue
						 * ("AppServerIp"));
						 * connDetails.setCabinetName(pr.getValue
						 * ("CabinetName"));
						 * connDetails.setAppServerUserId(pr.getValue
						 * ("AppServerUserId"));
						 * connDetails.setAppServerUserPass
						 * (pr.getValue("AppServerUserPass"));
						 * connDetails.setImageServerIp
						 * (pr.getValue("ImageServerIP"));
						 * connDetails.setImageServerPort
						 * (pr.getValue("ImageServerPort"));
						 * connDetails.setJbossPath(pr.getValue("JbossPath"));
						 * 
						 * docDetails = docList.get(i); bytes =
						 * docOperations.downloadDocumentWithReqId(connDetails,
						 * docDetails, "RQ234");
						 * 
						 * }
						 */
						String EstampPath = downloadPath + File.separator + eForm.getEcode().toString();
						logger.debug("******************" + EstampPath);
						File output;
						output = new File(EstampPath.toString());
						if (output.exists()) {
							logger.info("file already exists deleting....");
							output.delete();
						}
						if (output.getParentFile().exists() == false) {
							logger.info("Parent not found creating directory....");
							output.getParentFile().mkdirs();
						}
						if (null==metaDataInfo.getUniqueNo()) {
							session.setAttribute("checkStatus", "DMSError");
							return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
						}
						if (metaDataInfo.getUniqueNo().equals("")) {
							session.setAttribute("checkStatus", "DMSError");
							return new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
						}
						int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath, CommonConstant.MODULE_NAME);
						logger.debug("download result---------->" + result);
						String filePath = EstampPath + File.separator + "EStamp.PDF";
						// below code to view the doc
						// set the http content type to
						// "APPLICATION/OCTET-STREAM
						response.setContentType("application/download");
						
						response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filePath, "UTF-8"));
						
						File fileToDownload = new File(filePath);
						FileInputStream fileInputStream = new FileInputStream(fileToDownload);
						OutputStream out = response.getOutputStream();
						byte[] buf = new byte[2048];
						
						int readNum;
						while ((readNum = fileInputStream.read(buf)) != -1) {
							out.write(buf, 0, readNum);
						}
						fileInputStream.close();
						out.close();
					} else {
						// ByteArrayOutputStream baos = new
						// ByteArrayOutputStream();
						
						String EstampPath = downloadPath + File.separator + eForm.getEcode().toString() + File.separator + "EStamp.PDF";
						File output;
						output = new File(EstampPath.toString());
						if (output.exists()) {
							logger.info("file already exists deleting....");
							output.delete();
						}
						if (output.getParentFile().exists() == false) {
							logger.info("Parent not found creating directory....");
							output.getParentFile().mkdirs();
						}
						OutputStream fos = new FileOutputStream(EstampPath, false);
						Document document = new Document(PageSize.A4, 20, 20, 20, 20);
						PdfWriter writer = PdfWriter.getInstance(document, fos);
						document.open();
						
						Cell row = new Cell(new Phrase("", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						row.setHeader(true);
						row.setColspan(22);
						
						Table estampCerti = new Table(22);
						estampCerti.setWidth(100);
						estampCerti.setPadding(3);
						
						/*
						 * PdfPTable table = new PdfPTable(18);
						 * table.setWidthPercentage(100);
						 * 
						 * String path =
						 * request.getRealPath("/")+"images\\HeaderComp.jpg";
						 * logger.info("======>"+path); Image image1 =
						 * Image.getInstance(path);
						 * //image1.setBackgroundColor(new Color(0xC0, 0xC0,
						 * 0xC0));
						 * 
						 * 
						 * PdfPCell imageHeader = new PdfPCell(image1,false);
						 * imageHeader.setColspan(18);
						 * imageHeader.setHorizontalAlignment
						 * (Element.ALIGN_LEFT);
						 * 
						 * //imageHeader.setHorizontalAlignment(0);
						 * 
						 * imageHeader.setBorder(Rectangle.NO_BORDER);
						 * //imageHeader.setBackgroundColor(new Color(0xC0,
						 * 0xC0, 0xC0)); table.addCell(imageHeader);
						 */

						estampCerti.addCell(row);
						
						Cell title = new Cell(new Phrase("Certificate of Stamp Duty", FontFactory.getFont(FontFactory.TIMES_ITALIC, 14, Font.BOLD)));
						title.setHorizontalAlignment(Element.ALIGN_CENTER);
						title.setLeading(20);
						title.setColspan(22);
						title.setBorder(Rectangle.NO_BORDER);
						estampCerti.addCell(title);
						estampCerti.setBorderWidth(2);
						estampCerti.setAlignment(1);
						
						estampCerti.addCell(row);
						
						Cell sectionheader = new Cell(new Phrase("E-Stamp Details", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
						sectionheader.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader.setColspan(22);
						sectionheader.setBorder(Rectangle.NO_BORDER);
						sectionheader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
						estampCerti.addCell(sectionheader);
						estampCerti.setAlignment(2);
						
						Cell estamp_Code = new Cell(new Phrase("E-Stamp Code", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						estamp_Code.setHeader(true);
						estamp_Code.setColspan(11);
						estampCerti.addCell(estamp_Code);
						
						Cell ecodeValue = new Cell(new Phrase(eForm.getEcode(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						ecodeValue.setHeader(true);
						ecodeValue.setColspan(11);
						estampCerti.addCell(ecodeValue);
						
						Cell estampAmnt = new Cell(new Phrase("E-Stamp Amount", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						estampAmnt.setHeader(true);
						estampAmnt.setColspan(11);
						estampCerti.addCell(estampAmnt);
						
						Cell estampAmntValue = new Cell(new Phrase(eForm.getDutyCalculationDTO().getTotalDisplay(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						estampAmntValue.setHeader(true);
						estampAmntValue.setColspan(11);
						estampCerti.addCell(estampAmntValue);
						
						Cell estampType = new Cell(new Phrase("E-Stamp Type", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						estampType.setHeader(true);
						estampType.setColspan(11);
						estampCerti.addCell(estampType);
						
						Cell estampTypeValue = new Cell(new Phrase(eForm.getEstampType(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						estampTypeValue.setHeader(true);
						estampTypeValue.setColspan(11);
						estampCerti.addCell(estampTypeValue);
						
						Cell issueDate = new Cell(new Phrase("Issue Date & Time", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						issueDate.setHeader(true);
						issueDate.setColspan(11);
						estampCerti.addCell(issueDate);
						
						Cell issueDateValue = new Cell(new Phrase(eForm.getCurrentDate(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						issueDateValue.setHeader(true);
						issueDateValue.setColspan(11);
						estampCerti.addCell(issueDateValue);
						
						Cell issuer = new Cell(new Phrase("User ID/Issuer", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						issuer.setHeader(true);
						issuer.setColspan(11);
						estampCerti.addCell(issuer);
						
						Cell issuerValue = new Cell(new Phrase(eForm.getUserName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						issuerValue.setHeader(true);
						issuerValue.setColspan(11);
						estampCerti.addCell(issuerValue);
						
						Cell issueOffice = new Cell(new Phrase("SP/SRO/DRO/HO", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						issueOffice.setHeader(true);
						issueOffice.setColspan(11);
						estampCerti.addCell(issueOffice);
						
						Cell issueOfficeValue = new Cell(new Phrase(eForm.getOfficeName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						issueOfficeValue.setHeader(true);
						issueOfficeValue.setColspan(11);
						estampCerti.addCell(issueOfficeValue);
						
						Cell place = new Cell(new Phrase("Place", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						place.setHeader(true);
						place.setColspan(11);
						estampCerti.addCell(place);
						
						Cell placeValue = new Cell(new Phrase(eForm.getIssuedPlace(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						placeValue.setHeader(true);
						placeValue.setColspan(11);
						estampCerti.addCell(placeValue);
						
						Cell validity = new Cell(new Phrase("Validity", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						validity.setHeader(true);
						validity.setColspan(11);
						estampCerti.addCell(validity);
						
						Cell validityValue = new Cell(new Phrase(eForm.getEstampValidity(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						validityValue.setHeader(true);
						validityValue.setColspan(11);
						estampCerti.addCell(validityValue);
						
						estampCerti.addCell(row);
						
						Cell sectionheader1 = new Cell(new Phrase("Deed Details", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
						sectionheader1.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader1.setColspan(22);
						sectionheader1.setBorder(Rectangle.NO_BORDER);
						sectionheader1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
						estampCerti.addCell(sectionheader1);
						estampCerti.setAlignment(2);
						
						Cell deedType = new Cell(new Phrase("Deed Type", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						deedType.setHeader(true);
						deedType.setColspan(11);
						estampCerti.addCell(deedType);
						
						Cell deedTypeValue = new Cell(new Phrase(eForm.getDutyCalculationDTO().getDeedType(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						deedTypeValue.setHeader(true);
						deedTypeValue.setColspan(11);
						estampCerti.addCell(deedTypeValue);
						
						Cell deedInst = new Cell(new Phrase("Deed Instrument", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						deedInst.setHeader(true);
						deedInst.setColspan(11);
						estampCerti.addCell(deedInst);
						
						Cell deedInstValue = new Cell(new Phrase(eForm.getInstDTO().getInstType(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						deedInstValue.setHeader(true);
						deedInstValue.setColspan(11);
						estampCerti.addCell(deedInstValue);
						
						/*
						 * Cell exemption=new Cell(new Phrase("Exemption",
						 * FontFactory.getFont(FontFactory.COURIER, 8,
						 * Font.BOLD))); exemption.setHeader(true);
						 * exemption.setColspan(11);
						 * estampCerti.addCell(exemption);
						 * 
						 * Cell exemptionValue=new Cell(new
						 * Phrase(eForm.getEcode(),
						 * FontFactory.getFont(FontFactory.COURIER, 8,
						 * Font.NORMAL))); exemptionValue.setHeader(true);
						 * exemptionValue.setColspan(11);
						 * estampCerti.addCell(exemptionValue);
						 */

						Cell purpose = new Cell(new Phrase("Purpose", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						purpose.setHeader(true);
						purpose.setColspan(11);
						estampCerti.addCell(purpose);
						
						Cell purposeValue = new Cell(new Phrase(eForm.getEstampPurpose(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						purposeValue.setHeader(true);
						purposeValue.setColspan(11);
						estampCerti.addCell(purposeValue);
						
						// Ecode duration field has to be given in the creation
						// of E-stamp ------ yet to be done
						Cell deedDuration = new Cell(new Phrase("Deed Duration", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						deedDuration.setHeader(true);
						deedDuration.setColspan(11);
						estampCerti.addCell(deedDuration);
						
						Cell deedDurationValue = new Cell(new Phrase(eForm.getDeedDuration(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						deedDurationValue.setHeader(true);
						deedDurationValue.setColspan(11);
						estampCerti.addCell(deedDurationValue);
						//
						estampCerti.addCell(row);
						
						Cell sectionheader2 = new Cell(new Phrase("First Party Details", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
						sectionheader2.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader2.setColspan(22);
						sectionheader2.setBorder(Rectangle.NO_BORDER);
						sectionheader2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
						estampCerti.addCell(sectionheader2);
						estampCerti.setAlignment(2);
						
						Cell aplicantName = new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						aplicantName.setHeader(true);
						aplicantName.setColspan(11);
						estampCerti.addCell(aplicantName);
						
						Cell aplicantNameValue = new Cell(new Phrase(eForm.getAppNamedsply(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						aplicantNameValue.setHeader(true);
						aplicantNameValue.setColspan(11);
						estampCerti.addCell(aplicantNameValue);
						
						Cell applicantCountry = new Cell(new Phrase("Country", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						applicantCountry.setHeader(true);
						applicantCountry.setColspan(11);
						estampCerti.addCell(applicantCountry);
						
						Cell applicantCountryValue = new Cell(new Phrase(eForm.getAppCountryName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						applicantCountryValue.setHeader(true);
						applicantCountryValue.setColspan(11);
						estampCerti.addCell(applicantCountryValue);
						
						Cell applicantState = new Cell(new Phrase("State", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						applicantState.setHeader(true);
						applicantState.setColspan(11);
						estampCerti.addCell(applicantState);
						
						Cell applicantStateValue = new Cell(new Phrase(eForm.getAppStateName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						applicantStateValue.setHeader(true);
						applicantStateValue.setColspan(11);
						estampCerti.addCell(applicantStateValue);
						
						Cell applicantDistt = new Cell(new Phrase("District", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						applicantDistt.setHeader(true);
						applicantDistt.setColspan(11);
						estampCerti.addCell(applicantDistt);
						
						Cell applicantDisttValue = new Cell(new Phrase(eForm.getAppDistrictName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						applicantDisttValue.setHeader(true);
						applicantDisttValue.setColspan(11);
						estampCerti.addCell(applicantDisttValue);
						
						Cell applicantAddress = new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						applicantAddress.setHeader(true);
						applicantAddress.setColspan(11);
						estampCerti.addCell(applicantAddress);
						
						Cell applicantAddressValue = new Cell(new Phrase(eForm.getAppAddress(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						applicantAddressValue.setHeader(true);
						applicantAddressValue.setColspan(11);
						estampCerti.addCell(applicantAddressValue);
						if (eForm.getAppFatherName() == null || eForm.getAppFatherName().toString().equalsIgnoreCase("")) {
							Cell appFatherName = new Cell(new Phrase("Authorized Person's Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
							appFatherName.setHeader(true);
							appFatherName.setColspan(11);
							estampCerti.addCell(appFatherName);
							
							Cell appFatherNameValue = new Cell(new Phrase(eForm.getAppAuthPersonName().toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
							appFatherNameValue.setHeader(true);
							appFatherNameValue.setColspan(11);
							estampCerti.addCell(appFatherNameValue);
						} else {
							Cell appFatherName = new Cell(new Phrase("Father's Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
							appFatherName.setHeader(true);
							appFatherName.setColspan(11);
							estampCerti.addCell(appFatherName);
							
							Cell appFatherNameValue = new Cell(new Phrase(eForm.getAppFatherName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
							appFatherNameValue.setHeader(true);
							appFatherNameValue.setColspan(11);
							estampCerti.addCell(appFatherNameValue);
						}
						
						Cell numberOfPersons = new Cell(new Phrase("Number of Persons", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						numberOfPersons.setHeader(true);
						numberOfPersons.setColspan(11);
						estampCerti.addCell(numberOfPersons);
						
						Cell numberOfPersonsValue = new Cell(new Phrase(eForm.getAppPersons(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						numberOfPersonsValue.setHeader(true);
						numberOfPersonsValue.setColspan(11);
						estampCerti.addCell(numberOfPersonsValue);
						
						estampCerti.addCell(row);
						
						Cell sectionheader3 = new Cell(new Phrase("Second Party Details", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
						sectionheader3.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader3.setColspan(22);
						sectionheader3.setBorder(Rectangle.NO_BORDER);
						sectionheader3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
						estampCerti.addCell(sectionheader3);
						estampCerti.setAlignment(2);
						
						Cell name = new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						name.setHeader(true);
						name.setColspan(11);
						estampCerti.addCell(name);
						
						Cell nameValue = new Cell(new Phrase(eForm.getPartyNamedsply(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						nameValue.setHeader(true);
						nameValue.setColspan(11);
						estampCerti.addCell(nameValue);
						
						Cell country = new Cell(new Phrase("Country", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						country.setHeader(true);
						country.setColspan(11);
						estampCerti.addCell(country);
						
						Cell countryValue = new Cell(new Phrase(eForm.getPartyCountryName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						countryValue.setHeader(true);
						countryValue.setColspan(11);
						estampCerti.addCell(countryValue);
						
						Cell state = new Cell(new Phrase("State", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						state.setHeader(true);
						state.setColspan(11);
						estampCerti.addCell(state);
						
						Cell stateValue = new Cell(new Phrase(eForm.getPartyStateName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						stateValue.setHeader(true);
						stateValue.setColspan(11);
						estampCerti.addCell(stateValue);
						
						Cell distt = new Cell(new Phrase("District", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						distt.setHeader(true);
						distt.setColspan(11);
						estampCerti.addCell(distt);
						
						Cell disttValue = new Cell(new Phrase(eForm.getPartyDistrictName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						disttValue.setHeader(true);
						disttValue.setColspan(11);
						estampCerti.addCell(disttValue);
						
						Cell address = new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						address.setHeader(true);
						address.setColspan(11);
						estampCerti.addCell(address);
						
						Cell addressValue = new Cell(new Phrase(eForm.getPartyAddress(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						addressValue.setHeader(true);
						addressValue.setColspan(11);
						estampCerti.addCell(addressValue);
						
						if (eForm.getPartyFatherName() == null || eForm.getPartyFatherName().toString().equalsIgnoreCase("")) {
							Cell fatherName = new Cell(new Phrase("Authorized Person's Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
							fatherName.setHeader(true);
							fatherName.setColspan(11);
							estampCerti.addCell(fatherName);
							
							Cell fatherNameValue = new Cell(new Phrase(eForm.getPartyAuthPersonName().toString(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
							fatherNameValue.setHeader(true);
							fatherNameValue.setColspan(11);
							estampCerti.addCell(fatherNameValue);
							
						} else {
							Cell fatherName = new Cell(new Phrase("Father's Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
							fatherName.setHeader(true);
							fatherName.setColspan(11);
							estampCerti.addCell(fatherName);
							
							Cell fatherNameValue = new Cell(new Phrase(eForm.getPartyFatherName(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
							fatherNameValue.setHeader(true);
							fatherNameValue.setColspan(11);
							estampCerti.addCell(fatherNameValue);
						}
						
						Cell numberOfPerson = new Cell(new Phrase("Number of person", FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
						numberOfPerson.setHeader(true);
						numberOfPerson.setColspan(11);
						estampCerti.addCell(numberOfPerson);
						
						Cell numberOfPersonValue = new Cell(new Phrase(eForm.getPartyPersons(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL)));
						numberOfPersonValue.setHeader(true);
						numberOfPersonValue.setColspan(11);
						estampCerti.addCell(numberOfPersonValue);
						
						estampCerti.addCell(row);
						
						Cell sectionheader4 = new Cell(new Phrase("2D Bar Code", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
						sectionheader4.setHorizontalAlignment(Element.ALIGN_CENTER);
						sectionheader4.setColspan(22);
						sectionheader4.setBorder(Rectangle.NO_BORDER);
						sectionheader4.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
						estampCerti.addCell(sectionheader4);
						estampCerti.setAlignment(2);
						
						estampCerti.setCellsFitPage(true);
						// document.add(table);
						document.add(estampCerti);
						document.close();
						// commented by simran
						/*
						 * response.setContentType("application/pdf");
						 * response.setHeader("Content-Disposition",
						 * "attachment; filename=\"E-Stamp Certificate.pdf");
						 * response.setContentLength(baos.size());
						 * ServletOutputStream out = response.getOutputStream();
						 * baos.writeTo(out); out.flush();
						 */

						boolean boo = false;
						logger.debug("---->  inside pdf action" + eForm.getMainTxnId());
						
						// boo = bo.updateStatus(eForm);
						boo = true;
						// eForm=null;
						String docPath = objEstampBO.getEstampDocDetails(eForm.getMainTxnId().toString());
						String outputPath = "";
						String fileName = "";
						if (!docPath.equalsIgnoreCase("")) {
							
							int result = -1;
							PropertiesFileReader pr1 = PropertiesFileReader.getInstance("resources.igrs");
							String hindiFont = pr1.getValue("dms_hindi_font_path");
							String englishFont = pr1.getValue("dms_english_font_path");
							SealsBD sealBD = new SealsBD();
							boolean val = sealBD.validateFont(hindiFont, englishFont);
							if (!val) {
								throw new Exception();
							}
							BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
							outputPath = downloadPath + File.separator + eForm.getEcode().toString();
							fileName = "EStamp_temp.pdf";
							result = burnObj.generateEStampWithBarcode(outputPath, "D:\\images\\header.jpg", EstampPath, fileName);
							logger.debug("result  ^^^^^^^^^^^^^^^^^ " + result);
							
							// below code to check if there is any scanned
							// document , if any merging both docs
							
							String dmsFolderName = "IGRS";
							// result =
							// burnObj.mergeCertificateWithEStamp(connDetails,metaDataInfo,"D:\\download\\",
							// "R", "RegCert.pdf", "E" , "EStampPdf.pdf",
							// outputPath, "RE", "FinalCert.pdf",
							// dmsFolderName); //merging two docs
							burnObj.mergeDocs(downloadPath + File.separator + eForm.getEcode().toString(), outputPath + File.separator + "EStamp_temp.pdf", docPath, "EStamp.PDF");
							
						} else {
							int result = -1;
							PropertiesFileReader pr1 = PropertiesFileReader.getInstance("resources.igrs");
							
							String hindiFont = pr1.getValue("dms_hindi_font_path");
							String englishFont = pr1.getValue("dms_english_font_path");
							SealsBD sealBD = new SealsBD();
							boolean val = sealBD.validateFont(hindiFont, englishFont);
							if (!val) {
								throw new Exception();
							}
							BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
							outputPath = downloadPath + File.separator + eForm.getEcode().toString();
							fileName = "EStamp.pdf";
							result = burnObj.generateEStampWithBarcode(outputPath, CommonConstant.DRIVE_PATH + File.separator + "images" + File.separator + "header.jpg", EstampPath, fileName);
							logger.debug("result  ^^^^^^^^^^^^^^^^^ " + result);
						}
						// below code for uploading in DMS
						boolean flag = true;
						try {
							ReadPropertiesFile prop = new ReadPropertiesFile();
							// DMS start
							// PropertiesFileReader pr =
							// PropertiesFileReader.getInstance("resources.igrs");
							
							DocumentOperations dos = new DocumentOperations();
							ODServerDetails ods = new ODServerDetails();
							Dataclass dc = new Dataclass();
							
							ods.setAppServerIp(pr.getValue("AppServerIp"));
							ods.setCabinetName(pr.getValue("CabinetName"));
							ods.setAppServerUserId(pr.getValue("AppServerUserId"));
							ods.setAppServerUserPass(pr.getValue("AppServerUserPass"));
							ods.setImageServerIp(pr.getValue("ImageServerIP"));
							ods.setImageServerPort(pr.getValue("ImageServerPort"));
							ods.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
							dc.setUniqueNo(eForm.getEcode().toString());
							fileName = "EStamp.pdf";
							String path = outputPath + "/" + fileName;
							logger.debug("path^^^^^^^^^^^^^^^^^^^^^^^^^^^" + path);
							File file = new File(path);
							// dc.setDataclassName(pr.getValue("IGRSDataclass"));
							String docId = dos.uploadDocument(ods, file, "IGRS", true, dc);
							logger.debug("doc Id " + docId);
							
							if (docId != "-1") {
								// update generate cert status in table
								flag = objEstampBO.updateCertificateGenerationChk(eForm.getMainTxnId().toString());
								
							}
							// below code to view the doc
							// set the http content type to
							// "APPLICATION/OCTET-STREAM
							response.setContentType("application/download");
							
							response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(path, "UTF-8"));
							
							File fileToDownload = new File(path);
							FileInputStream fileInputStream = new FileInputStream(fileToDownload);
							OutputStream out = response.getOutputStream();
							byte[] buf = new byte[2048];
							
							int readNum;
							while ((readNum = fileInputStream.read(buf)) != -1) {
								out.write(buf, 0, readNum);
							}
							fileInputStream.close();
							out.close();
							
						} catch (Exception e) {
							
						}
						forwardJsp = "estampSuccess";
					}
				}
				
			}// end of print page
			
			if (request.getParameter("paymentStatus") != null && request.getAttribute("eStampRegDistId") == null) {
				logger.info("------>*****BACK TO ACTION");
				if (eForm.getMainTxnId() != null) {
					logger.info("------>*****" + eForm.getMainTxnId());
				} else {
					logger.info("------>*****" + objDashBoardDTO1.getMainTxnId1());
					String TxnRequestId = objDashBoardDTO1.getMainTxnId1().toString();
					eForm.setMainTxnId(TxnRequestId);
				}
				// objDashBoardDTO1.getMainTxnId1().toString();
				
				// logger.info("------>*****"+eForm.getMainTxnId());
				
				if (request.getParameter("paymentStatus").equalsIgnoreCase("P")) {
					String uniId = (String) request.getParameter("parentKey");
					if (uniId != null || uniId != "") {
						eForm.setUniqKey(uniId);
					}
					/*
					 * String mainId = bo.getMainId(uniId);
					 * eForm.setMainTxnId(mainId);
					 */
					ArrayList first = new ArrayList();
					ArrayList comp = new ArrayList();
					String uniqId = "";
					String amtToBePaid = "";
					String paidAmt = "";
					String pymtFlg = "";
					first = bo.getPayDtls(eForm.getMainTxnId());
					
					if (first.size() > 0) {
						for (int i = 0; i < first.size(); i++) {
							comp.add((ArrayList) first.get(i));
							if (comp != null && comp.size() > 0) {
								for (int k = 0; k < comp.size(); k++) {
									ArrayList compSub = (ArrayList) comp.get(k);
									uniqId = (String) compSub.get(0);
									amtToBePaid = (String) compSub.get(1);
									paidAmt = (String) compSub.get(2);
									pymtFlg = (String) compSub.get(3);
								}
							}
						}
					}
					// Commented By Lavi for partial payment
					// double damtToBePaid = Double.parseDouble(paidAmt);
					
					double damtToBePaid = Double.parseDouble(amtToBePaid); // Added
					// By
					// Lavi
					// for
					// Partial
					// Payment.
					double dpaidAmt = Double.parseDouble(paidAmt);
					// condition 1-------------------pending amount-------------
					if (damtToBePaid > dpaidAmt) {
						double bal = damtToBePaid - dpaidAmt;
						eForm.setIsPartial(1);
						
						// Added By Lavi for forwarding from payment screen to
						// Dashboard.
						
						ArrayList pendingApplicationList = objEstampBO.getPendingEstampApps(session.getAttribute("UserId").toString(), language);
						
						logger.info("--------------->" + pendingApplicationList.size());
						if (pendingApplicationList.size() == 0)
							eForm.getObjDashBoardDTO().setPendingEstampApplicationList(null);
						else
							eForm.getObjDashBoardDTO().setPendingEstampApplicationList(pendingApplicationList);
						request.setAttribute("pendingApplicationList", eForm.getObjDashBoardDTO().getPendingEstampApplicationList());
						logger.info("--------------->succesfully received values");
						// forwardJsp = CommonConstant.FORWARD_PAGE_DASHBOARD;
						if (language.equalsIgnoreCase("en")) {
							request.setAttribute("success_msg", "The Estamp has still amount left to process the transaction. Estamp Transaction Id is: " + eForm.getMainTxnId());
						} else {
							request.setAttribute("success_msg", "ई स्टाम्प अभी भी राशि लेन - देन की प्रक्रिया के लिए छोड़ दिया गया है। ई स्टाम्प आइडी : " + eForm.getMainTxnId());
						}
						
						forwardJsp = "confirmation";
						logger.info("--------------->showing dashboard");
					}
					
					// condition 2-------------------full payment, eCode
					// generation------
					// MODIFIED BY SIMRAN
					if (damtToBePaid <= dpaidAmt) {
						String eCodeNumber = "";
						DecimalFormat myformatter = new DecimalFormat("############");
						double bal = damtToBePaid - dpaidAmt;
						// duty//
						String duty = bo.getDuty(eForm.getMainTxnId());
						eForm.getDutyCalculationDTO().setTotal(Double.parseDouble(duty));
						eForm.getDutyCalculationDTO().setTotalDisplay(myformatter.format(Double.parseDouble(duty)));
						// date and time//
						String currDate = bo.getCurrentDate();
						eForm.setCurrentDate(currDate);
						// deeds and instruments and purpose//
						ArrayList second = new ArrayList();
						ArrayList comp1 = new ArrayList();
						second = bo.getDeedDtls(eForm.getMainTxnId(), language);
						if (second.size() > 0) {
							for (int i = 0; i < second.size(); i++) {
								comp1.add((ArrayList) second.get(i));
								if (comp1 != null && comp1.size() > 0) {
									for (int k = 0; k < comp1.size(); k++) {
										ArrayList compSub = (ArrayList) comp1.get(k);
										String deed = (String) compSub.get(0);
										String inst = (String) compSub.get(1);
										String purpose = (String) compSub.get(2);
										String instDiscp = (String) compSub.get(3);
										logger.debug("the deed is.........................." + deed);
										logger.debug("the inst is.........................." + inst);
										logger.debug("the purpose is.........................." + purpose);
										eForm.getDutyCalculationDTO().setDeedType(deed);
										eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
										eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
										eForm.getInstDTO().setInstType(inst);
										eForm.setEstampPurpose(purpose);
									}
								}
							}
						}
						// names and office details//
						
						String type = objEstampBO.gettype(userId); // --added by
						// satbir
						// kumar--
						
						logger.debug(type);
						/*
						 * String roleId = bo.getRole(userId); String srrole =
						 * "GR1356609170484"; String drrole = "GR1356671177515";
						 * String sprole = "GR1358488496205"; String rurole =
						 * "GR1357368369828"; String spbankrole =
						 * "GR1359453019113";
						 * 
						 * eForm.getObjDashBoardDTO().setRole(roleId);
						 */

						if ("2".equalsIgnoreCase(type)) {
							ArrayList userdet = new ArrayList();
							ArrayList comp2 = new ArrayList();
							userdet = bo.getruName(userId, language);
							if (userdet.size() > 0) {
								for (int i = 0; i < userdet.size(); i++) {
									comp2.add((ArrayList) userdet.get(i));
									if (comp2 != null && comp2.size() > 0) {
										for (int k = 0; k < comp2.size(); k++) {
											ArrayList compSub = (ArrayList) comp2.get(k);
											
											String name = (String) compSub.get(0);
											String cid = (String) compSub.get(1);
											String sid = (String) compSub.get(2);
											String did = (String) compSub.get(3);
											String dname = (String) compSub.get(4);
											
											eForm.setUserName(name);
											if (cid.equalsIgnoreCase("1")) {
												if (sid.equalsIgnoreCase("1")) {
													eForm.setIssuedPlace(dname);
													
												} else {
													if ("en".equalsIgnoreCase(language)) {
														eForm.setIssuedPlace("Others");
													} else {
														eForm.setIssuedPlace("अन्य");
													}
												}
												
											} else {
												if ("en".equalsIgnoreCase(language)) {
													eForm.setIssuedPlace("Others");
												} else {
													eForm.setIssuedPlace("अन्य");
												}
											}
											
										}
									}
								}
							}
							
							eForm.setOfficeName("-");
						}

						else if ("3".equalsIgnoreCase(type)) {
							String spname = bo.getspName(userId);
							String licNo = bo.getspLicenseNo(userId);
							ArrayList spdtls = new ArrayList();
							ArrayList comp3 = new ArrayList();
							spdtls = bo.getspDtls(userId, language);
							if (spdtls.size() > 0) {
								for (int i = 0; i < spdtls.size(); i++) {
									comp3.add((ArrayList) spdtls.get(i));
									if (comp3 != null && comp3.size() > 0) {
										for (int k = 0; k < comp3.size(); k++) {
											ArrayList compSub = (ArrayList) comp3.get(k);
											
											String ofc = (String) compSub.get(0);
											String plc = (String) compSub.get(1);
											String tehsil = (String) compSub.get(2);
											eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
											eForm.setIssuedPlace(plc);
											eForm.setUserName(spname + "/" + licNo);
										}
									}
								}
								
							}
						}

						else if ("4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
							
							ArrayList spbankdtls = new ArrayList();
							ArrayList comp4 = new ArrayList();
							spbankdtls = bo.getspbnkDtls(userId, language);
							if (spbankdtls.size() > 0) {
								for (int i = 0; i < spbankdtls.size(); i++) {
									comp4.add((ArrayList) spbankdtls.get(i));
									if (comp4 != null && comp4.size() > 0) {
										for (int k = 0; k < comp4.size(); k++) {
											ArrayList compSub = (ArrayList) comp4.get(k);
											
											String uname = (String) compSub.get(0);
											String ofc = (String) compSub.get(1);
											String plc = (String) compSub.get(2);
											String tehsil = (String) compSub.get(3);
											eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
											eForm.setIssuedPlace(plc);
											eForm.setUserName(uname);
										}
									}
								}
								
							}
						}

						else if ("I".equalsIgnoreCase(type)) {
							ArrayList iudtls = new ArrayList();
							ArrayList comp4 = new ArrayList();
							String officeId = (String) session.getAttribute("loggedToOffice");
							iudtls = bo.getiuDtls(userId, officeId, language);
							if (iudtls.size() > 0) {
								for (int i = 0; i < iudtls.size(); i++) {
									comp4.add((ArrayList) iudtls.get(i));
									if (comp4 != null && comp4.size() > 0) {
										for (int k = 0; k < comp4.size(); k++) {
											ArrayList compSub = (ArrayList) comp4.get(k);
											
											String ofc = (String) compSub.get(0);
											String plc = (String) compSub.get(1);
											String uname = (String) compSub.get(2);
											eForm.setOfficeName(ofc);
											eForm.setIssuedPlace(plc);
											eForm.setUserName(uname);
										}
									}
								}
								
							}
						}
						
						// party details--applicant//
						ArrayList appdtls = new ArrayList();
						ArrayList comp5 = new ArrayList();
						appdtls = bo.getAppDtls(eForm.getMainTxnId(), language);
						if (appdtls.size() > 0) {
							for (int i = 0; i < appdtls.size(); i++) {
								comp5.add((ArrayList) appdtls.get(i));
								if (comp5 != null && comp5.size() > 0) {
									for (int k = 0; k < comp5.size(); k++) {
										ArrayList compSub = (ArrayList) comp5.get(k);
										
										String name = (String) compSub.get(0);
										String cntry = (String) compSub.get(1);
										String state = (String) compSub.get(2);
										String district = (String) compSub.get(3);
										String addrs = (String) compSub.get(4);
										String fathrNme = (String) compSub.get(5);
										String noOfPrsns = (String) compSub.get(6);
										String orgName = (String) compSub.get(7);
										String disId = (String) compSub.get(8);
										String deedDuratn = (String) compSub.get(9);
										
										if (orgName != null) {
											
											eForm.setAppNamedsply(orgName);
											eForm.setAppCountryName(cntry);
											eForm.setAppStateName(state);
											eForm.setAppDistrictName(district);
											eForm.setAppAddress(addrs);
											eForm.setAppAuthPersonName(name);
											eForm.setAppPersons(noOfPrsns);
											eForm.setAppDistrict(disId);
											eForm.setIsAuthapp(1);
											eForm.setDeedDuration(deedDuratn);
											eForm.setAppFatherName(null);
										} else {
											eForm.setAppNamedsply(name);
											eForm.setAppCountryName(cntry);
											eForm.setAppStateName(state);
											eForm.setAppDistrictName(district);
											eForm.setAppAddress(addrs);
											eForm.setAppFatherName(fathrNme);
											eForm.setAppPersons(noOfPrsns);
											eForm.setAppDistrict(disId);
											if (deedDuratn != null)
												eForm.setDeedDuration(deedDuratn);
											else
												eForm.setDeedDuration("-");
											
										}
										
									}
								}
							}
							
						}
						
						// party details--party//
						ArrayList partydtls = new ArrayList();
						ArrayList comp6 = new ArrayList();
						partydtls = bo.getPartyDtls(eForm.getMainTxnId(), language);
						if (partydtls.size() > 0) {
							for (int i = 0; i < partydtls.size(); i++) {
								comp6.add((ArrayList) partydtls.get(i));
								if (comp6 != null && comp6.size() > 0) {
									for (int k = 0; k < comp6.size(); k++) {
										ArrayList compSub = (ArrayList) comp6.get(k);
										
										String name = (String) compSub.get(0);
										String cntry = (String) compSub.get(1);
										String state = (String) compSub.get(2);
										String district = (String) compSub.get(3);
										String addrs = (String) compSub.get(4);
										String fathrNme = (String) compSub.get(5);
										String noOfPrsns = (String) compSub.get(6);
										String orgName = (String) compSub.get(7);
										
										if (orgName != null) {
											eForm.setPartyNamedsply(orgName);
											eForm.setPartyCountryName(cntry);
											eForm.setPartyStateName(state);
											eForm.setPartyDistrictName(district);
											eForm.setPartyAddress(addrs);
											eForm.setPartyAuthPersonName(name);
											eForm.setPartyPersons(noOfPrsns);
											eForm.setIsAuthparty(1);
											eForm.setPartyFatherName(null);
										} else {
											eForm.setPartyNamedsply(name);
											eForm.setPartyCountryName(cntry);
											eForm.setPartyStateName(state);
											eForm.setPartyDistrictName(district);
											eForm.setPartyAddress(addrs);
											eForm.setPartyFatherName(fathrNme);
											eForm.setPartyPersons(noOfPrsns);
										}
										
										/*
										 * if (orgName.equalsIgnoreCase(null)){
										 * eForm.setPartyNamedsply(name);
										 * eForm.setPartyCountryName(cntry);
										 * eForm.setPartyStateName(state);
										 * eForm.setPartyDistrictName(district);
										 * eForm.setPartyAddress(addrs);
										 * eForm.setPartyFatherName(fathrNme);
										 * eForm.setPartyPersons(noOfPrsns);
										 * }else{
										 * eForm.setPartyNamedsply(orgName);
										 * eForm.setPartyCountryName(cntry);
										 * eForm.setPartyStateName(state);
										 * eForm.setPartyDistrictName(district);
										 * eForm.setPartyAddress(addrs);
										 * eForm.setPartyAuthPersonName(name);
										 * eForm.setPartyPersons(noOfPrsns);
										 * eForm.setIsAuthparty(1);
										 * 
										 * }
										 */

									}
								}
							}
							
						} else {
							eForm.setPartyNamedsply("-");
							eForm.setPartyCountryName("-");
							eForm.setPartyStateName("-");
							eForm.setPartyDistrictName("-");
							eForm.setPartyAddress("-");
							eForm.setPartyFatherName("-");
							eForm.setPartyPersons("-");
							
						}
						// generation of e-code and insertion into the tables.
						try {
							boolean boo = false;
							
							checkEstamp = objEstampBO.checkEstamp(eForm.getMainTxnId(), "E");
							if (checkEstamp) {
								boo = bo.inserteCode(eForm, objDashBoardDTO1);
							} else {
								eCodeNumber = objEstampBO.getEcode(eForm.getMainTxnId(), "E");
							}
							if (boo) {
								
								ArrayList ecodedtls = new ArrayList();
								ArrayList comp7 = new ArrayList();
								ecodedtls = bo.getEcodeDtls(eForm.getMainTxnId(), language);
								if (ecodedtls.size() > 0) {
									for (int i = 0; i < ecodedtls.size(); i++) {
										comp7.add((ArrayList) ecodedtls.get(i));
										if (comp7 != null && comp7.size() > 0) {
											for (int k = 0; k < comp7.size(); k++) {
												ArrayList compSub = (ArrayList) comp7.get(k);
												eCodeNumber = (String) compSub.get(0);
												String ecode = (String) compSub.get(0);
												String amt = (String) compSub.get(1);
												String estampType = (String) compSub.get(2);
												String estampDate = (String) compSub.get(3);
												String issuedBy = (String) compSub.get(4);
												String offc = (String) compSub.get(5);
												String place = (String) compSub.get(6);
												String validityDt = (String) compSub.get(7);
												eForm.setEcode(ecode);
												eForm.getDutyCalculationDTO().setTotalDisplay(amt);
												eForm.setEstampType(estampType);
												/*
												 * SimpleDateFormat sdf1 = new
												 * SimpleDateFormat
												 * ("yyyy-MM-dd- KK:mm:ss");
												 * SimpleDateFormat sdf2 = new
												 * SimpleDateFormat
												 * ("dd/MM/yyyy hh:mm:ss a");
												 * logger
												 * .debug("the date is-----"
												 * +estampDate); Date d1 =
												 * sdf1.parse(estampDate);
												 * String transDate =
												 * sdf2.format(d1);
												 */
												eForm.setCurrentDate(estampDate);
												eForm.setUserName(issuedBy);
												eForm.setOfficeName(offc);
												eForm.setIssuedPlace(place);
												eForm.setEstampValidity(validityDt);
												logger.debug("Just before Final page redirection");
												forwardJsp = "ecodePage";
												
											}
										}
									}
									
								}
							} else {
								forwardJsp = "failure";
							}
							
						} catch (Exception e) {
							
						}
						if (language.equalsIgnoreCase("en")) {
							request.setAttribute("success_msg", "The Estamp has been created successfully. Estamp code is: " + eCodeNumber);
						} else {
							request.setAttribute("success_msg", "ई स्टाम्प सफलतापूर्वक बना दिया गया है। ई स्टाम्प कोड : " + eCodeNumber);
						}
						
						forwardJsp = "confirmation";
					}// end of second condition
				}
			}
			
			// ----------------modify action ends-----------------
			
			// ---------------payment action ends---------------
			eForm.getDutyCalculationDTO().setFormName("");
			eForm.getDutyCalculationDTO().setActionName("");
			
			if (CommonConstant.DUTY_PAGE.equals(page)) {
				
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				eForm.setObjDashBoardDTO(new DashBoardDTO());
				eForm.setDeedConsumeFlag("");
				eForm.setDeedDraftId("");
				
				eForm.setDeedDraftErrorFlag("");
				eForm.setDeedDraftStatus("");
				eForm.setPartyAuthFirstName("");
				eForm.setPartyAuthMiddleName("");
				eForm.setPartyAuthLastName("");
				dto.setFirstName("");
				dto.setMidName("");
				dto.setLastName("");
				dto.setAge(0);
				dto.setSex("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setBaseValue("0.0"); // modified by SIMRAN
				eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				dto.setActionName("");
				
				eForm.setAppType("Select");
				eForm.setAppFirsName("");
				eForm.setAppMiddleName("");
				eForm.setAppLastName("");
				eForm.setAppAge("");
				eForm.setAppDay("");
				eForm.setAppMonth("");
				eForm.setAppYear("");
				eForm.setAppFatherName("");
				eForm.setAppMotherName("");
				eForm.setAppCountry("");
				eForm.setAppState("");
				eForm.setAppDistrict("");
				eForm.setAppAddress("");
				eForm.setAppPostalCode("");
				eForm.setAppPhno("");
				eForm.setAppMobno("");
				eForm.setAppEmailID("");
				eForm.setAppPhotoId("");
				eForm.setAppPhotoIdno("");
				eForm.setAppBankAddress("");
				eForm.setAppBankName("");
				eForm.setAppPersons("");
				eForm.setAppOrgName("");
				// ADDED BY SIMRAN
				eForm.setAppAuthFirstName("");
				eForm.setAppAuthMiddleName("");
				eForm.setAppAuthLastName("");
				
				eForm.setPartyType("Select");
				eForm.setPartyFirsName("");
				eForm.setPartyMiddleName("");
				eForm.setPartyLastName("");
				eForm.setPartyAge("");
				eForm.setPartyDay("");
				eForm.setPartyMonth("");
				eForm.setPartyYear("");
				eForm.setPartyFatherName("");
				eForm.setPartyMotherName("");
				eForm.setPartyCountry("");
				eForm.setPartyState("");
				eForm.setPartyDistrict("");
				eForm.setPartyAddress("");
				eForm.setPartyPostalCode("");
				eForm.setPartyPhno("");
				eForm.setPartyMobno("");
				eForm.setPartyEmailID("");
				eForm.setPartyPhotoId("");
				eForm.setPartyPhotoIdno("");
				eForm.setPartyBankAddress("");
				eForm.setPartyBankName("");
				eForm.setPartyPersons("");
				eForm.setPartyOrgName("");
				eForm.setDeedDuration("");
				
				eForm.setEstampPurpose("");
				eForm.setFilePhoto2(null);
				eForm.setDoc("");
				eForm.setDocname("");
				eForm.setIsModify(0);
				eForm.setErrMsg(0);
				
				if (eForm.getModuleName() != null && eForm.getModuleName().equalsIgnoreCase(CommonConstant.REGISTRATION_DUTY_PAGE)) {
					eForm.setDutycalculationDTOList(bo.getDeed(CommonConstant.NON_DUTY_REGISTRATION_DEED));
				} else {
					
					eForm.setDutycalculationDTOList(bo.getDeed(CommonConstant.NON_DUTY_DEED));
				}
				
				dto.setSex("M".equals(dto.getSex()) ? "Male" : "F".equals(dto.getSex()) ? "Female" : "");
				
				dto.setActionName(null);
				actionName = "";
				eForm.setDutyCalculationDTO(dto);
				String currYear = bo.getCurrentYear();
				eForm.setCurrentYear(currYear);
				
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				// forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
				// forwardJsp = "dutyNext";
				
			}
			if (CommonConstant.REGISTRATION_DUTY_PAGE.equals(page)) {
				eForm.setModuleName(CommonConstant.REGISTRATION_DUTY_PAGE);
				formName = "dutyHomePage";
				actionName = "nextPage";
			}
			
			System.out.println("actionName:-" + actionName + ":" + formName);
			System.out.println("forwardJsp:-" + forwardJsp);
			
			if (CommonConstant.DUTY_NEXT_PAGE.equals(formName)) {
				
				System.out.println("actionName:-" + actionName);
				if (CommonConstant.DUTY_PREV_ACTION.equals(actionName)) {/*
																		 * DutyCalculationDTO
																		 * dto1
																		 * =
																		 * eForm
																		 * .
																		 * getDutyCalculationDTO
																		 * ();
																		 * dto.
																		 * setFirstName
																		 * (
																		 * dto1.
																		 * getFirstName
																		 * ());
																		 * dto.
																		 * setMidName
																		 * (
																		 * dto1.
																		 * getMidName
																		 * ());
																		 * dto.
																		 * setLastName
																		 * (
																		 * dto1.
																		 * getLastName
																		 * ());
																		 * dto
																		 * .setAge
																		 * (
																		 * dto1.
																		 * getAge
																		 * ());
																		 * dto
																		 * .setSex
																		 * (
																		 * "Male"
																		 * .
																		 * equals
																		 * (
																		 * dto1.
																		 * getSex
																		 * ()) ?
																		 * "M" :
																		 * "Female"
																		 * .
																		 * equals
																		 * (
																		 * dto1.
																		 * getSex
																		 * ()) ?
																		 * "F" :
																		 * "");
																		 * 
																		 * 
																		 * 
																		 * 
																		 * System.
																		 * out
																		 * .println
																		 * (
																		 * "Name:-"
																		 * +
																		 * dto1.
																		 * getFirstName
																		 * ());
																		 * eForm
																		 * .
																		 * setDutyCalculationDTO
																		 * (
																		 * dto);
																		 * dto.
																		 * setActionName
																		 * (
																		 * null)
																		 * ;
																		 * actionName
																		 * = "";
																		 * forwardJsp
																		 * =
																		 * CommonConstant
																		 * .
																		 * FORWARD_PAGE_HOME
																		 * ;
																		 */
				}
				System.out.println(" before change action actionName:-" + actionName);
				if (CommonConstant.DEED_CHANGE_ACTION.equals(actionName)) {/*
																			 * DutyCalculationDTO
																			 * dto1
																			 * =
																			 * eForm
																			 * .
																			 * getDutyCalculationDTO
																			 * (
																			 * )
																			 * ;
																			 * int
																			 * deedId
																			 * =
																			 * dto1
																			 * .
																			 * getDeedId
																			 * (
																			 * )
																			 * ;
																			 * eForm
																			 * .
																			 * setHdnAmount
																			 * (
																			 * ""
																			 * )
																			 * ;
																			 * eForm
																			 * .
																			 * getDutyCalculationDTO
																			 * (
																			 * )
																			 * .
																			 * setBaseValue
																			 * (
																			 * "0.0"
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * /
																			 * /
																			 * modified
																			 * by
																			 * SIMRAN
																			 * eForm
																			 * .
																			 * getDutyCalculationDTO
																			 * (
																			 * )
																			 * .
																			 * setAnnualRent
																			 * (
																			 * 0.0
																			 * )
																			 * ;
																			 * eForm
																			 * .
																			 * getDutyCalculationDTO
																			 * (
																			 * )
																			 * .
																			 * setDutyPaid
																			 * (
																			 * 0.0
																			 * )
																			 * ;
																			 * eForm
																			 * .
																			 * setInstDTO
																			 * (
																			 * new
																			 * InstrumentsDTO
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * eForm
																			 * .
																			 * setExempDTO
																			 * (
																			 * new
																			 * ExemptionDTO
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * eForm
																			 * .
																			 * setExemptionDTOList
																			 * (
																			 * new
																			 * ArrayList
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * ArrayList
																			 * instList
																			 * =
																			 * bo
																			 * .
																			 * getInstrument
																			 * (
																			 * deedId
																			 * ,
																			 * language
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * /
																			 * /
																			 * modified
																			 * by
																			 * SIMRAN
																			 * 
																			 * 
																			 * ArrayList
																			 * <
																			 * ExemptionDTO
																			 * >
																			 * exemDTOList
																			 * =
																			 * bo
																			 * .
																			 * getExemptions
																			 * (
																			 * dto
																			 * .
																			 * getDeedId
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * eForm
																			 * .
																			 * setInstrumentDTOList
																			 * (
																			 * instList
																			 * )
																			 * ;
																			 * 
																			 * forwardJsp
																			 * =
																			 * CommonConstant
																			 * .
																			 * FORWARD_PAGE_NEXT
																			 * ;
																			 * dto
																			 * .
																			 * setActionName
																			 * (
																			 * null
																			 * )
																			 * ;
																			 * actionName
																			 * =
																			 * ""
																			 * ;
																			 */
				}
				
				if (CommonConstant.DUTY_CALCULATE_ACTION.equals(actionName)) {/*
																			 * 
																			 * InstrumentsDTO
																			 * instdto
																			 * =
																			 * eForm
																			 * .
																			 * getInstDTO
																			 * (
																			 * )
																			 * ;
																			 * ExemptionDTO
																			 * exedto
																			 * =
																			 * eForm
																			 * .
																			 * getExempDTO
																			 * (
																			 * )
																			 * ;
																			 * DutyCalculationDTO
																			 * dtoForm
																			 * =
																			 * eForm
																			 * .
																			 * getDutyCalculationDTO
																			 * (
																			 * )
																			 * ;
																			 * 
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "The exemption value is :"
																			 * +
																			 * exedto
																			 * .
																			 * getHdnExAmount
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "The exemption value is for :"
																			 * +
																			 * exedto
																			 * .
																			 * getHdnExempAmount
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "The exemption NAME is :"
																			 * +
																			 * exedto
																			 * .
																			 * getHdnExemptions
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "The exemption value is :"
																			 * +
																			 * exedto
																			 * .
																			 * getExemType
																			 * (
																			 * )
																			 * )
																			 * ;
																			 * 
																			 * String
																			 * [
																			 * ]
																			 * exemptions
																			 * =
																			 * exedto
																			 * .
																			 * getHdnExemptions
																			 * (
																			 * )
																			 * ==
																			 * null
																			 * ?
																			 * null
																			 * :
																			 * exedto
																			 * .
																			 * getHdnExemptions
																			 * (
																			 * )
																			 * .
																			 * split
																			 * (
																			 * "~"
																			 * )
																			 * ;
																			 * 
																			 * double
																			 * stampDuty
																			 * =
																			 * bo
																			 * .
																			 * getDutyCalculation
																			 * (
																			 * dtoForm
																			 * ,
																			 * instdto
																			 * ,
																			 * exedto
																			 * )
																			 * ;
																			 * 
																			 * dtoForm
																			 * .
																			 * setStampDuty
																			 * (
																			 * stampDuty
																			 * )
																			 * ;
																			 * 
																			 * if
																			 * (
																			 * stampDuty
																			 * <
																			 * 0
																			 * )
																			 * {
																			 * stampDuty
																			 * =
																			 * 0.0
																			 * ;
																			 * dtoForm
																			 * .
																			 * setStampDuty
																			 * (
																			 * stampDuty
																			 * )
																			 * ;
																			 * }
																			 * double
																			 * dutyArray
																			 * [
																			 * ]
																			 * =
																			 * new
																			 * double
																			 * [
																			 * 3
																			 * ]
																			 * ;
																			 * double
																			 * nagarPalikaDuty
																			 * =
																			 * 0.0
																			 * ;
																			 * double
																			 * panchayatDuty
																			 * =
																			 * 0.0
																			 * ;
																			 * double
																			 * upkarDuty
																			 * =
																			 * 0.0
																			 * ;
																			 * 
																			 * dutyArray
																			 * =
																			 * bo
																			 * .
																			 * getMunicipalDutyCalculation
																			 * (
																			 * dtoForm
																			 * ,
																			 * instdto
																			 * ,
																			 * exedto
																			 * )
																			 * ;
																			 * 
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "NAGAR PALIKA DUTY"
																			 * +
																			 * dutyArray
																			 * [
																			 * 0
																			 * ]
																			 * +
																			 * "PANCHAYAT DUTY"
																			 * +
																			 * dutyArray
																			 * [
																			 * 1
																			 * ]
																			 * +
																			 * "UPKAR DUTY"
																			 * +
																			 * dutyArray
																			 * [
																			 * 2
																			 * ]
																			 * )
																			 * ;
																			 * 
																			 * if
																			 * (
																			 * dutyArray
																			 * .
																			 * length
																			 * >=
																			 * 1
																			 * )
																			 * {
																			 * nagarPalikaDuty
																			 * =
																			 * (
																			 * dutyArray
																			 * [
																			 * 0
																			 * ]
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setNagarPalikaDuty
																			 * (
																			 * (
																			 * nagarPalikaDuty
																			 * )
																			 * )
																			 * ;
																			 * panchayatDuty
																			 * =
																			 * (
																			 * dutyArray
																			 * [
																			 * 1
																			 * ]
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setPanchayatDuty
																			 * (
																			 * dutyArray
																			 * [
																			 * 1
																			 * ]
																			 * )
																			 * ;
																			 * upkarDuty
																			 * =
																			 * (
																			 * dutyArray
																			 * [
																			 * 2
																			 * ]
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setUpkarDuty
																			 * (
																			 * upkarDuty
																			 * )
																			 * ;
																			 * }
																			 * 
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "NAGAR PALIKA DUTY"
																			 * +
																			 * nagarPalikaDuty
																			 * +
																			 * "PANCHAYAT DUTY"
																			 * +
																			 * panchayatDuty
																			 * +
																			 * "UPKAR DUTY"
																			 * +
																			 * upkarDuty
																			 * )
																			 * ;
																			 * 
																			 * double
																			 * total
																			 * =
																			 * nagarPalikaDuty
																			 * +
																			 * panchayatDuty
																			 * +
																			 * upkarDuty
																			 * +
																			 * stampDuty
																			 * ;
																			 * dtoForm
																			 * .
																			 * setTotal
																			 * (
																			 * total
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * 
																			 * //
																			 * dtoForm
																			 * .
																			 * setTotal
																			 * (
																			 * common
																			 * .
																			 * getCurrencyFormat
																			 * (
																			 * total
																			 * )
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * 
																			 * //
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "nagarPalikaDuty"
																			 * ,
																			 * nagarPalikaDuty
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "panchayatDuty"
																			 * ,
																			 * panchayatDuty
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "upkarDuty"
																			 * ,
																			 * upkarDuty
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "total"
																			 * ,
																			 * total
																			 * )
																			 * ;
																			 * 
																			 * 
																			 * 
																			 * 
																			 * //
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "DutyDTO"
																			 * ,
																			 * dtoForm
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "InstrumentDTO"
																			 * ,
																			 * instdto
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "ExemptionDTO"
																			 * ,
																			 * exedto
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "duty"
																			 * ,
																			 * duty
																			 * )
																			 * ;
																			 * 
																			 * double
																			 * regFee
																			 * =
																			 * bo
																			 * .
																			 * getRegistrationFee
																			 * (
																			 * dtoForm
																			 * ,
																			 * instdto
																			 * ,
																			 * exedto
																			 * )
																			 * ;
																			 * dtoForm
																			 * .
																			 * setRegFee
																			 * (
																			 * regFee
																			 * )
																			 * ;
																			 * /
																			 * /
																			 * session
																			 * .
																			 * setAttribute
																			 * (
																			 * "regFee"
																			 * ,
																			 * regFee
																			 * )
																			 * ;
																			 * if
																			 * (
																			 * regFee
																			 * <
																			 * 0
																			 * )
																			 * {
																			 * regFee
																			 * =
																			 * 0.0
																			 * ;
																			 * dtoForm
																			 * .
																			 * setRegFee
																			 * (
																			 * regFee
																			 * )
																			 * ;
																			 * }
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "Reg Fee:-"
																			 * +
																			 * regFee
																			 * )
																			 * ;
																			 */
				}
				
				if (CommonConstant.DUTY_RADIO_ACTION.equals(actionName)) {/*
																		 * InstrumentsDTO
																		 * instdto
																		 * =
																		 * eForm
																		 * .
																		 * getInstDTO
																		 * ();
																		 * DutyCalculationDTO
																		 * dtoId
																		 * =
																		 * eForm
																		 * .
																		 * getDutyCalculationDTO
																		 * ();
																		 * System
																		 * .out.
																		 * println
																		 * (
																		 * "Inside radio butto"
																		 * );
																		 * 
																		 * int
																		 * deedId
																		 * =
																		 * dtoId
																		 * .
																		 * getDeedId
																		 * ();
																		 * int
																		 * instId
																		 * =
																		 * instdto
																		 * .
																		 * getInstId
																		 * ();
																		 * 
																		 * ArrayList
																		 * exemDTOList
																		 * =bo.
																		 * getExemptions
																		 * (
																		 * deedId
																		 * ,
																		 * instId
																		 * );
																		 * 
																		 * 
																		 * 
																		 * 
																		 * eForm.
																		 * setExemptionDTOList
																		 * (
																		 * exemDTOList
																		 * );
																		 * 
																		 * 
																		 * 
																		 * 
																		 * System.
																		 * out
																		 * .println
																		 * (
																		 * "After exemption list"
																		 * );
																		 * 
																		 * //
																		 * dtoId
																		 * .
																		 * setBaseValue
																		 * (dto.
																		 * getBaseValue
																		 * ());
																		 * 
																		 * 
																		 * 
																		 * 
																		 * eForm.
																		 * setDutyCalculationDTO
																		 * (
																		 * dtoId
																		 * );
																		 * 
																		 * forwardJsp
																		 * =
																		 * CommonConstant
																		 * .
																		 * FORWARD_PAGE_NEXT
																		 * ;
																		 */
				}
				if (CommonConstant.DUTY_CALCULATE_ACTION.equals(actionName)) {
					
					if (eForm.getModuleName() != null && eForm.getModuleName().equalsIgnoreCase(CommonConstant.REGISTRATION_DUTY_PAGE)) {
						forwardJsp = CommonConstant.FORWARD_NONREGINIT_DISPLAY;
						dto.setActionName(null);
						actionName = "";
					} else {
						// added by Lavi on 11th October 2013 for scanner
						// integration
						
						String type = objEstampBO.gettype(userId);
						
						if ("I".equalsIgnoreCase(type)) {
							logger.info("Inside if action of Judicial page for internal users: " + type);
							eForm.setIsInternalUser(1);
							// downloadPath=downloadPath+File.separator+"IGRS";
							eForm.setParentPathScan(downloadPath + File.separator + "05" + File.separator);
							eForm.setFileNameScan("Estamp_Document.pdf");
							eForm.setGuidUpload(GUIDGenerator.getGUID());
							
						}
					}
					
					/*
					 * DutyCalculationBO boexemption = new DutyCalculationBO();
					 * DutyCalculationDTO dutyDTO =
					 * eForm.getDutyCalculationDTO(); InstrumentsDTO
					 * instrumentDTO = eForm.getInstDTO(); ExemptionDTO
					 * exemptionDTO = eForm.getExempDTO();
					 * System.out.println(exemptionDTO); int inst =
					 * instrumentDTO.getInstId(); int deed =
					 * dutyDTO.getDeedId(); String[] exemptions =
					 * exemptionDTO.getHdnExemptions() == null ? null :
					 * exemptionDTO.getHdnExemptions().split(", ");
					 * 
					 * // String[] exem =
					 * exemptionDTO.getHdnExempAmount().split(" // ~");
					 * 
					 * eForm.setSelectedExemptionList(boexemption
					 * .getExemptionList(exemptions)); DecimalFormat myformatter
					 * = new DecimalFormat( "############");
					 * 
					 * bo.captureNonPropStampDetails(
					 * eForm.getDutyCalculationDTO(), eForm.getInstDTO(),
					 * eForm.getExempDTO());
					 * 
					 * dutyDTO.setAnnualRentDisplay(myformatter.format(dutyDTO
					 * .getAnnualRent()));
					 * dutyDTO.setBaseValueDisplay(myformatter
					 * .format(Double.parseDouble(dutyDTO .getBaseValue())));
					 * dutyDTO.setDutyAlreadyPaidDisplay(myformatter
					 * .format(dutyDTO.getDutyAlreadyPaid()));
					 * dutyDTO.setDutyPaidDisplay(myformatter.format(dutyDTO
					 * .getDutyPaid()));
					 * dutyDTO.setDateCalculation(common.getCurrentDate());
					 * dutyDTO.setTotalDisplay(myformatter.format(dutyDTO
					 * .getTotal()));
					 * dutyDTO.setNagarPalikaDutyDisplay(myformatter
					 * .format(dutyDTO.getNagarPalikaDuty()));
					 * dutyDTO.setPanchayatDutyDisplay
					 * (myformatter.format(dutyDTO .getPanchayatDuty()));
					 * dutyDTO.setUpkarDutyDisplay(myformatter.format(dutyDTO
					 * .getUpkarDuty()));
					 * dutyDTO.setStampDutyDisplay(myformatter.format(dutyDTO
					 * .getStampDuty()));
					 * dutyDTO.setRegFeeDisplay(myformatter.format(dutyDTO
					 * .getRegFee()));
					 * 
					 * session.setAttribute("baseValue",
					 * common.getCurrencyFormat(dto.getBaseValue()));
					 * session.setAttribute("duty",
					 * common.getCurrencyFormat(Double.parseDouble(duty)));
					 * session.setAttribute("dateCalculation",
					 * common.getCurrentDate());
					 * 
					 * session.setAttribute("annualRent",
					 * common.getCurrencyFormat(dto.getAnnualRent()));
					 * 
					 * session.setAttribute("dutyPaid",
					 * common.getCurrencyFormat(dto.getDutyPaid()));
					 * 
					 * 
					 * System.out.println("Stamp Id" + dutyDTO.getStampId());
					 * request.setAttribute("stampId", dutyDTO.getStampId());
					 * System.out.println("Stamp Id" +
					 * request.getAttribute("stampId"));
					 * eForm.setDutyCalculationDTO(dutyDTO);
					 * eForm.setExempDTO(exemptionDTO);
					 * eForm.setInstDTO(instrumentDTO);
					 * 
					 * System.out.println("base Value:-" +
					 * dutyDTO.getBaseValue()); System.out.println("Deed Name:-"
					 * + dutyDTO.getDeedType());
					 * System.out.println("Instrument Name:-" +
					 * instrumentDTO.getInstType());
					 * System.out.println("Exemption Name:-" +
					 * exemptionDTO.getHdnExemptions());
					 * 
					 * if (eForm.getModuleName() != null &&
					 * eForm.getModuleName().equalsIgnoreCase(
					 * CommonConstant.REGISTRATION_DUTY_PAGE)) { forwardJsp =
					 * CommonConstant.FORWARD_NONREGINIT_DISPLAY;
					 * dto.setActionName(null); actionName = ""; } else { //
					 * added by Lavi on 11th October 2013 for scanner
					 * integration
					 * 
					 * String type = objEstampBO.gettype(userId);
					 * 
					 * if("I".equalsIgnoreCase(type)) {logger.info(
					 * "Inside if action of Judicial page for internal users: "
					 * +type); eForm.setIsInternalUser(1);
					 * eForm.setParentPathScan("D:/Upload/05/");
					 * eForm.setFileNameScan("Estamp_Document.pdf");
					 * eForm.setGuidUpload(GUIDGenerator.getGUID());
					 * 
					 * }
					 * 
					 * // end of code added by Lavi on 11th October 2013 for
					 * scanner integration
					 * 
					 * eForm.setCountryList(bo.getCountry(language));
					 * eForm.setPhotoIdList(bo.getPhotoId(language));
					 * eForm.setAppellate(bo.getAppellate(language)); forwardJsp
					 * = CommonConstant.FORWARD_PAGE_DISPLAY;
					 * dto.setActionName(null); actionName = ""; }
					 */
					actionName = "";
					
				}
				if (CommonConstant.CANCEL_ACTION.equals(actionName)) {
					forwardJsp = "welcome";
				}
				if (CommonConstant.RESET_PAGE_ACTION.equals(actionName)) {
					eForm.setExemptionDTOList(new ArrayList());
					eForm.setInstrumentDTOList(new ArrayList());
					DutyCalculationDTO dto1 = new DutyCalculationDTO();
					
					/*
					 * dto.setFirstName(dto1.getFirstName());
					 * dto.setMidName(dto1.getMidName());
					 * dto.setLastName(dto1.getLastName());
					 * dto.setAge(dto1.getAge());
					 * dto.setSex("Male".equals(dto1.getSex()) ? "M" : "Female"
					 * .equals(dto1.getSex()) ? "F" : "");
					 */
					dto.setDeedId(dto1.getDeedId());
					dto.setBaseValue("0.0"); // modified by SIMRAN
					
					eForm.setInstDTO(new InstrumentsDTO());
					eForm.setDutyCalculationDTO(dto);
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					dto.setActionName(null);
					actionName = "";
				}
			}
			
			if (CommonConstant.DUTY_VIEW_PAGE.equals(formName)) {
				logger.debug(" hello inside next page action");
				System.out.println("the actionanme is" + actionName);
				
				if ("appType1".equalsIgnoreCase(actionName)) {
					
					eForm.setAppFirsName("");
					eForm.setAppMiddleName("");
					eForm.setAppLastName("");
					eForm.setAppAge("");
					eForm.setAppDay("");
					eForm.setAppMonth("");
					eForm.setAppYear("");
					eForm.setAppFatherName("");
					eForm.setAppMotherName("");
					eForm.setAppCountry("1");
					eForm.setAppState("1");
					eForm.setAppDistrict("");
					eForm.setAppAddress("");
					eForm.setAppPostalCode("");
					eForm.setAppPhno("");
					eForm.setAppMobno("");
					eForm.setAppEmailID("");
					eForm.setAppPhotoId("");
					eForm.setAppPhotoIdno("");
					eForm.setAppBankAddress("");
					eForm.setAppBankName("");
					eForm.setAppPersons("");
					eForm.setAppOrgName("");
					eForm.setAppCountryName("INDIA");
					eForm.setAppStateName("M.P.");
					eForm.setDistrictList(bo.getDistrict("1", language));
					String appname = eForm.getAppTypeName();
					eForm.setAppGender("M");
					
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
				}
				if ("partyType1".equalsIgnoreCase(actionName)) {
					
					eForm.setPartyFirsName("");
					eForm.setPartyMiddleName("");
					eForm.setPartyLastName("");
					eForm.setPartyAge("");
					eForm.setPartyDay("");
					eForm.setPartyMonth("");
					eForm.setPartyYear("");
					eForm.setPartyFatherName("");
					eForm.setPartyMotherName("");
					// eForm.setPartyCountry("");
					// eForm.setPartyState("");
					eForm.setPartyDistrict("");
					eForm.setPartyAddress("");
					eForm.setPartyPostalCode("");
					eForm.setPartyPhno("");
					eForm.setPartyMobno("");
					eForm.setPartyEmailID("");
					eForm.setPartyPhotoId("");
					eForm.setPartyPhotoIdno("");
					eForm.setPartyBankAddress("");
					eForm.setPartyBankName("");
					eForm.setPartyPersons("");
					eForm.setPartyOrgName("");
					eForm.setPartyCountry("1");
					eForm.setPartyState("1");
					eForm.setDistrictList(bo.getDistrict("1", language));
					eForm.setPartyCountryName("INDIA");
					eForm.setPartyStateName("M.P.");
					String partyname = eForm.getPartyTypeName();
					eForm.setPartyGender("M");
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
				}
				
				if (CommonConstant.STATE_LOAD.equals(actionName)) {
					String countryId = eForm.getAppCountry();
					if (!countryId.equals(null) || countryId != null || countryId != "") {
						eForm.setStateList(bo.getState(countryId, language));
					} else {
						String countryId2 = eForm.getPartyCountry();
						eForm.setStateList(bo.getState(countryId2, language));
					}
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
				}
				if ("stateloadAdopt".equals(actionName)) {
					String countryId = eForm.getAdoptCountry();
					
					eForm.setStateList(bo.getState(countryId, language));
					
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
				}
				if ("districtLoad".equalsIgnoreCase(actionName)) {
					String stateId = eForm.getAppState();
					if (!stateId.equals(null) || stateId != null || stateId != "") {
						eForm.setDistrictList(bo.getDistrict(stateId, language));
					} else {
						String stateId2 = eForm.getPartyState();
						eForm.setDistrictList(bo.getDistrict(stateId2, language));
					}
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
				}
				if ("districtLoadAdopt".equalsIgnoreCase(actionName)) {
					String stateId = eForm.getAdoptState();
					
					eForm.setDistrictList(bo.getDistrict(stateId, language));
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
				}
				if ("insertAction".equalsIgnoreCase(actionName)) {
					
					if (eForm.getDoc() == null || eForm.getDoc().toString().equalsIgnoreCase(""))

					{
						ArrayList errorList = new ArrayList();
						if ("en".equalsIgnoreCase(language)) {
							errorList.add("Please consume deed draft.");
						} else {
							errorList.add("डीड ड्राफ्ट उपभोग करें.");
							
						}
						request.setAttribute("errorsList", errorList);
					} else {
						logger.debug("inside insert action");
						try {
							eForm.setErrMsg(0);
							logger.debug("inside insert action try");
							boolean txn = false;
							
							String userId1 = (String) session.getAttribute("UserId");
							String deedDraftId = (String) session.getAttribute("deedDraftId");
							String result = objEstampBO.validateDeedIdBeforeSavingRecord(eForm, language, userId1, deedDraftId);
							if (result.equals("failed")) {
								eForm.getDutyCalculationDTO().setActionName("");
								eForm.setDeedDraftErrorFlag("N");
								eForm.setDeedDraftStatus("A");
								eForm.setDeedDraftError("Deed Draft Id Already Consumed");
								actionName = "";
								ArrayList errorList1 = new ArrayList();
								if ("en".equalsIgnoreCase(language)) {
									errorList1.add("Deed Draft Id Already Consumed.");
								} else {
									errorList1.add("डीड ड्राफ्ट आईडी पहले से  कन्जूम कर लिया गया है।");
									
								}
								
								request.setAttribute("errorsList", errorList1);
								forwardJsp = CommonConstant.FORWARD_PAGE_VIEW;
								return mapping.findForward(forwardJsp);
								
							} else {
								txn = bo.insertTxn(eForm, language);
								logger.debug("inside insert action-after insert");
								if (txn) {
									String maintxnid = eForm.getMainTxnId();
									boolean boo = bo.insertPay(eForm, objDashBoardDTO1);
									logger.debug("the main transaction id after insertion is........" + maintxnid);
									
									// added by Lavi for scanner integration of
									// internal user
									if (eForm.getDoc().equalsIgnoreCase("") == false) {
										if (Integer.toString(eForm.getIsInternalUser()).equalsIgnoreCase("1")) {
											if ((eForm.getDoc() != null) || (!(eForm.getDoc()).equalsIgnoreCase(""))) {
												boolean inserDocDetls = bo.insertDocDetls(eForm);
											}
										}
										// end of code added by Lavi for scanner
										// integration of internal user
										else {
											// --added by satbir kumar----
											if ((eForm.getDoc() != null) || (!(eForm.getDoc()).equalsIgnoreCase(""))) {
												if ((eForm.getDoc() != null) || (!(eForm.getDoc()).equalsIgnoreCase(""))) {
													boolean inserDocDetls = bo.insertDocDetls(eForm);
												}
												/*
												 * FormFile photo =
												 * eForm.getImage(); String
												 * docname = eForm.getDoc();
												 * String docPath =
												 * CommonConstant
												 * .DRIVE_PATH+File
												 * .separator+"Upload"
												 * +File.separator
												 * +"05"+File.separator
												 * +eForm.getMainTxnId();
												 * eForm.setDocPath(docPath);
												 * boolean
												 * up=uploadFile(photo,docname
												 * ,docPath); boolean
												 * inserDocDetls =
												 * bo.insertDocDetls(eForm);
												 */}
											// ---end of addition by satbir
											// kumar----
										}
									}
									
									// Added By Lavi for validation
									if (Double.parseDouble(eForm.getDutyCalculationDTO().getPayableDutyDisplay()) <= 0) {
										eForm.setPay(1);
										logger.debug("----------->" + eForm.getPay());
									} else {
										eForm.setPay(1);
										logger.debug("----------->" + eForm.getPay());
									}
									// End of validation
									
									forwardJsp = CommonConstant.FORWARD_PAGE_VIEW;
								} else {
									
									eForm.setErrMsg(1);
									forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
								}
							}
						} catch (Exception e) {
							String msg = e.getMessage();
							if (msg.equalsIgnoreCase("10001")) {
								logger.debug("Exception while inserting record, deed or inst ID are coming as 0");
								if (language.equalsIgnoreCase("en"))
									request.setAttribute(CommonConstant.FAILURE_MSG, "There was a problem generating the estamp. Please try again!!!");
								else
									request.setAttribute(CommonConstant.FAILURE_MSG, "ई-स्टाम्प सुरक्षित नहीं हो पाया है। कृपया पुनः प्रयास करें!!!");
								forwardJsp = CommonConstant.FORWARD_PAGE_DASHBOARD;
							} else {
								eForm.setErrMsg(1);
								forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
							}
						}// end catch
						
					}
				}// end insertAction
				
				if ("updateAction".equalsIgnoreCase(actionName)) {
					
					try {
						eForm.setErrMsg(0);
						logger.debug("inside insert action try");
						boolean updatetxn = false;
						
						// added by Lavi for scanner integration of internal
						// user
						if (eForm.getDoc().equalsIgnoreCase("") == false) {
							if (Integer.toString(eForm.getIsInternalUser()).equalsIgnoreCase("1")) {
								// eForm.setDoc(eForm.getFileNameScan());
								// eForm.setDocPath(eForm.getParentPathScan());
							}
							// end of code added by Lavi for scanner integration
							// of internal user
							
							else {
								
								if ((eForm.getDoc() != null) || (!(eForm.getDoc()).equalsIgnoreCase(""))) {
									FormFile photo = eForm.getImage();
									String docname = eForm.getDoc();
									// downloadPath=downloadPath+File.separator+"IGRS";
									// String docPath =
									// downloadPath+File.separator+"05"+File.separator+eForm.getMainTxnId();
									// String docPath =
									// CommonConstant.DRIVE_PATH+File.separator+"Upload"+File.separator+"05"+File.separator+eForm.getMainTxnId();
									eForm.setDocPath(eForm.getDocPathComplete());
									String docPath = eForm.getDocPathComplete();
									// boolean
									// up=uploadFile(photo,docname,docPath);
									// boolean inserDocDetls =
									// bo.insertDocDetls(eForm);
									
								}
							}
						}
						updatetxn = bo.updateTxn(eForm, language);
						logger.debug("inside insert action-after insert");
						if (updatetxn) {
							String maintxnid = eForm.getMainTxnId();
							logger.debug("the main transaction id after insertion is........" + maintxnid);
							forwardJsp = CommonConstant.FORWARD_PAGE_VIEW;
						} else {
							
							eForm.setErrMsg(1);
							forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
						}
						
					} catch (Exception e) {
						eForm.setErrMsg(1);
						forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
						
					}
				}
				// added by Lavi on 11th October 2013 for scanner integration
				
				if ("afterScannerUpload".equalsIgnoreCase(actionName)) {
					eForm.setDoc(eForm.getFileNameScan());
					eForm.setDocPath(eForm.getParentPathScan() + "/" + eForm.getGuidUpload());
					eForm.setDocPathComplete(eForm.getDocPath() + "/" + eForm.getDoc());
					forwardJsp = "dutyDisplay";
					actionName = "";
				}
				
				// end of code added by Lavi on 11th October 2013 for scanner
				// integration
				if ("deedIdValidate".equalsIgnoreCase(actionName)) {
					
					eForm.getDutyCalculationDTO().setActionName("");
					eForm.setDeedDraftErrorFlag("N");
					eForm.setDeedDraftStatus("A");
					eForm.setDeedDraftError("");
					String userId1 = (String) session.getAttribute("UserId");
					session.setAttribute("deedDraftId", eForm.getDeedDraftId());
					objEstampBO.validateDeedId1(eForm, language, userId1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
					return mapping.findForward(forwardJsp);
				}
				if ("deedConsume".equalsIgnoreCase(actionName)) {
					
					eForm.getDutyCalculationDTO().setActionName("");
					try {
						boolean flag = objEstampBO.updateDeedStatus(eForm, language, response);
						if (flag == true) {
							eForm.setDeedConsumeFlag("Y");
							
						} else {
							eForm.setDeedConsumeFlag("N");
							
						}
					} catch (Exception e) {
						e.printStackTrace();
						
						if (eForm.getDeedDraftError().equalsIgnoreCase("Already Consumed")) {
							if ("en".equalsIgnoreCase(language)) {
								eForm.setDeedDraftError("Already Consumed");
								
							} else {
								eForm.setDeedDraftError("आईडी को पहले से ही इस्तेमाल किया जा चुका है।");
								
							}
							
						} else {
							if ("en".equalsIgnoreCase(language)) {
								eForm.setDeedDraftError("Invalid Id");
								
							} else {
								eForm.setDeedDraftError("अवैध आईडी|");
								
							}
						}
					}
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
					return mapping.findForward(forwardJsp);
				}
				
				if ("deedDraft".equalsIgnoreCase(actionName)) {
					
					eForm.getDutyCalculationDTO().setActionName("");
					
					DeedDraftBD bd = new DeedDraftBD();
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					// boolean validID=bd.checkID(regForm.getDeedDraftId());
					// if(validID)
					// {
					String path = pr.getValue("pdf.location");
					byte[] propDetlsBytes = null; // added by ankit for prop val
													// pdf
					bd.generateDeedPDF(path, eForm.getDeedDraftId(), response, 0, propDetlsBytes); // change
																									// by
																									// ankit
																									// for
																									// prop
																									// val
																									// pdf
					
					// }else{
					// formDTO.setValidDeed("Invalid");
					// }
					
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
				}
				
				if ("setUploadFile".equalsIgnoreCase(actionName)) {
					
					try {
						
						ArrayList errorList = new ArrayList();
						FormFile uploadedFile = eForm.getFilePhoto2();
						eForm.setImage1(uploadedFile.getFileData());
						
						eForm.setImage(uploadedFile);
						
						/*
						 * if ("".equals(uploadedFile.getFileName())) {
						 * errorList
						 * .add("Invalid file selection. Please try again."); }
						 */
						String fileExt = getFileExtension(uploadedFile.getFileName());
						AuditInspectionRule rule = new AuditInspectionRule();
						rule.validateFileTypeEstamp(fileExt);
						int size = uploadedFile.getFileSize();
						double fileSizeInBytes = size;
						double fileSizeInKB = fileSizeInBytes / 1024.0;
						double fileSizeInMB = fileSizeInKB / 1024.0;
						DecimalFormat decim = new DecimalFormat("#.##");
						Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
						String photoSize = "(" + fileSize + "MB)";
						if (rule.isError()) {
							if ("en".equalsIgnoreCase(language)) {
								errorList.add("Invalid file type. Please select another file.");
							} else {
								errorList.add("अमान्य फ़ाइल प्रकार |अन्य फाइल चुनें |");
							}
							request.setAttribute("errorsList", errorList);
						} else {
							if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
								if ("en".equalsIgnoreCase(language)) {
									errorList.add("File size is Greater than 10 MB. Please select another file.");
								} else {
									errorList.add("फ़ाइल का आकार 10 MB से अधिक है| अन्य फाइल चुनें|");
									
								}
								request.setAttribute("errorsList", errorList);
							} else {
								
								String docName = "Estamp_Document." + fileExt;
								
								eForm.setDoc(docName);
								// eForm.setDocPath(docPath);
								/*
								 * dto.setFilePhoto2(uploadedFile);
								 * eForm.getDoddto
								 * ().setFilePhoto2(uploadedFile);
								 */

								forwardJsp = "dutyDisplay";
								eForm.getObjDashBoardDTO().setActionName(null);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				// ----------------------remove file coding----------
				if ("removeUploadFile".equalsIgnoreCase(actionName)) {
					try {
						
						// Added By Lavi For Scanner Integration
						if (Integer.toString(eForm.getIsInternalUser()).equalsIgnoreCase("1")) {
							String fname = (String) eForm.getDoc();
							String docPath = eForm.getDocPath();
							removeFile(fname, docPath);
							eForm.setDocname("");
							eForm.setDoc("");
							
						}
						// end of code Added By Lavi For Scanner Integration
						else {
							FormFile fname = (FormFile) eForm.getFilePhoto2();
							// downloadPath=downloadPath+File.separator+"IGRS";
							String docPath = downloadPath + File.separator + "05" + File.separator + eForm.getMainTxnId();
							
							// String docPath =
							// CommonConstant.DRIVE_PATH+File.separator+"Upload"+File.separator+"05"+File.separator+eForm.getMainTxnId()+"_"+"nonJud"+"_"+fname.getFileName();
							
							removeFile(fname.getFileName(), docPath);
							eForm.setDocname("");
							eForm.setDoc("");
						}
						
						forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
						eForm.getObjDashBoardDTO().setActionName(null);
						actionName = "";
						eForm.getObjDashBoardDTO().setActionName(null);
						eForm.getObjDashBoardDTO().setActionName("");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// end :upload coding
				
			}// end duty view page
			
			// --------------start of details view
			// page--------------------------
			
			if ("detailsViewPage".equalsIgnoreCase(formName)) {
				
				// -------------------modify action starts-----------
				// if("modify".equalsIgnoreCase(actionName)) {
				if ("modify".equals(actionName)) {
					eForm.setIsModify(1);
					forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
					
				}
				
				if ("PAY_LATER".equalsIgnoreCase(actionName)) {
					
					boolean boo = false;
					// boo = bo.insertPay(eForm, objDashBoardDTO1);
					forwardJsp = CommonConstant.FORWARD_PAGE_AFTR_PAY_LATER;
				}
				
				if ("ESTAMP_CERTIFICATE".equalsIgnoreCase(actionName)) {
					boolean boo = false;
					boo = bo.insertPay(eForm, objDashBoardDTO1);
					
					DecimalFormat myformatter = new DecimalFormat("############");
					// duty//
					String duty = bo.getDuty(eForm.getMainTxnId());
					eForm.getDutyCalculationDTO().setTotal(Double.parseDouble(duty));
					eForm.getDutyCalculationDTO().setTotalDisplay(myformatter.format(Double.parseDouble(duty)));
					// date and time//
					String currDate = bo.getCurrentDate();
					eForm.setCurrentDate(currDate);
					// deeds and instruments and purpose//
					ArrayList second = new ArrayList();
					ArrayList comp1 = new ArrayList();
					second = bo.getDeedDtls(eForm.getMainTxnId(), language);
					if (second.size() > 0) {
						for (int i = 0; i < second.size(); i++) {
							comp1.add((ArrayList) second.get(i));
							if (comp1 != null && comp1.size() > 0) {
								for (int k = 0; k < comp1.size(); k++) {
									ArrayList compSub = (ArrayList) comp1.get(k);
									String deed = (String) compSub.get(0);
									String inst = (String) compSub.get(1);
									String purpose = (String) compSub.get(2);
									String instDiscp = (String) compSub.get(3);
									logger.debug("the deed is.........................." + deed);
									logger.debug("the inst is.........................." + inst);
									logger.debug("the purpose is.........................." + purpose);
									eForm.getDutyCalculationDTO().setDeedType(deed);
									eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
									eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
									eForm.getInstDTO().setInstType(inst);
									eForm.setEstampPurpose(purpose);
								}
							}
						}
					}
					// names and office details//
					String type = objEstampBO.gettype(userId); // --added by
					// satbir
					// kumar--
					
					logger.debug(type);
					/*
					 * String roleId = bo.getRole(userId); String srrole =
					 * "GR1356609170484"; String drrole = "GR1356671177515";
					 * String sprole = "GR1358488496205"; String rurole =
					 * "GR1357368369828"; String spbankrole = "GR1359453019113";
					 * 
					 * eForm.getObjDashBoardDTO().setRole(roleId);
					 */
					if ("2".equalsIgnoreCase(type)) {
						ArrayList userdet = new ArrayList();
						ArrayList comp2 = new ArrayList();
						userdet = bo.getruName(userId, language);
						if (userdet.size() > 0) {
							for (int i = 0; i < userdet.size(); i++) {
								comp2.add((ArrayList) userdet.get(i));
								if (comp2 != null && comp2.size() > 0) {
									for (int k = 0; k < comp2.size(); k++) {
										ArrayList compSub = (ArrayList) comp2.get(k);
										
										String name = (String) compSub.get(0);
										String cid = (String) compSub.get(1);
										String sid = (String) compSub.get(2);
										String did = (String) compSub.get(3);
										String dname = (String) compSub.get(4);
										
										eForm.setUserName(name);
										if (cid.equalsIgnoreCase("1")) {
											if (sid.equalsIgnoreCase("1")) {
												eForm.setIssuedPlace(dname);
												
											} else {
												if ("en".equalsIgnoreCase(language)) {
													eForm.setIssuedPlace("Others");
												} else {
													eForm.setIssuedPlace("अन्य");
												}
											}
											
										} else {
											if ("en".equalsIgnoreCase(language)) {
												eForm.setIssuedPlace("Others");
											} else {
												eForm.setIssuedPlace("अन्य");
											}
										}
										
									}
								}
							}
						}
						
						eForm.setOfficeName("-");
					}

					else if ("3".equalsIgnoreCase(type)) {
						String spname = bo.getspName(userId);
						String licNo = bo.getspLicenseNo(userId);
						ArrayList spdtls = new ArrayList();
						ArrayList comp3 = new ArrayList();
						spdtls = bo.getspDtls(userId, language);
						if (spdtls.size() > 0) {
							for (int i = 0; i < spdtls.size(); i++) {
								comp3.add((ArrayList) spdtls.get(i));
								if (comp3 != null && comp3.size() > 0) {
									for (int k = 0; k < comp3.size(); k++) {
										ArrayList compSub = (ArrayList) comp3.get(k);
										
										String ofc = (String) compSub.get(0);
										String plc = (String) compSub.get(1);
										String tehsil = (String) compSub.get(2);
										eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
										eForm.setIssuedPlace(plc);
										eForm.setUserName(spname + "/" + licNo);
									}
								}
							}
							
						}
					}

					else if ("4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
						
						ArrayList spbankdtls = new ArrayList();
						ArrayList comp4 = new ArrayList();
						spbankdtls = bo.getspbnkDtls(userId, language);
						if (spbankdtls.size() > 0) {
							for (int i = 0; i < spbankdtls.size(); i++) {
								comp4.add((ArrayList) spbankdtls.get(i));
								if (comp4 != null && comp4.size() > 0) {
									for (int k = 0; k < comp4.size(); k++) {
										ArrayList compSub = (ArrayList) comp4.get(k);
										
										String uname = (String) compSub.get(0);
										String ofc = (String) compSub.get(1);
										String plc = (String) compSub.get(2);
										String tehsil = (String) compSub.get(3);
										eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
										eForm.setIssuedPlace(plc);
										eForm.setUserName(uname);
									}
								}
							}
							
						}
					}

					else if ("I".equalsIgnoreCase(type)) {
						ArrayList iudtls = new ArrayList();
						ArrayList comp4 = new ArrayList();
						String officeId = (String) session.getAttribute("loggedToOffice");
						iudtls = bo.getiuDtls(userId, officeId, language);
						if (iudtls.size() > 0) {
							for (int i = 0; i < iudtls.size(); i++) {
								comp4.add((ArrayList) iudtls.get(i));
								if (comp4 != null && comp4.size() > 0) {
									for (int k = 0; k < comp4.size(); k++) {
										ArrayList compSub = (ArrayList) comp4.get(k);
										
										String ofc = (String) compSub.get(0);
										String plc = (String) compSub.get(1);
										String uname = (String) compSub.get(2);
										eForm.setOfficeName(ofc);
										eForm.setIssuedPlace(plc);
										eForm.setUserName(uname);
									}
								}
							}
							
						}
					}
					
					// party details--applicant//
					ArrayList appdtls = new ArrayList();
					ArrayList comp5 = new ArrayList();
					appdtls = bo.getAppDtls(eForm.getMainTxnId(), language);
					if (appdtls.size() > 0) {
						for (int i = 0; i < appdtls.size(); i++) {
							comp5.add((ArrayList) appdtls.get(i));
							if (comp5 != null && comp5.size() > 0) {
								for (int k = 0; k < comp5.size(); k++) {
									ArrayList compSub = (ArrayList) comp5.get(k);
									
									String name = (String) compSub.get(0);
									String cntry = (String) compSub.get(1);
									String state = (String) compSub.get(2);
									String district = (String) compSub.get(3);
									String addrs = (String) compSub.get(4);
									String fathrNme = (String) compSub.get(5);
									String noOfPrsns = (String) compSub.get(6);
									String orgName = (String) compSub.get(7);
									String disId = (String) compSub.get(8);
									String deedDuratn = (String) compSub.get(9);
									
									if (orgName != null) {
										
										eForm.setAppNamedsply(orgName);
										eForm.setAppCountryName(cntry);
										eForm.setAppStateName(state);
										eForm.setAppDistrictName(district);
										eForm.setAppAddress(addrs);
										eForm.setAppAuthPersonName(name);
										eForm.setAppPersons(noOfPrsns);
										eForm.setAppDistrict(disId);
										eForm.setIsAuthapp(1);
										eForm.setDeedDuration(deedDuratn);
										eForm.setAppFatherName(null);
									} else {
										eForm.setAppNamedsply(name);
										eForm.setAppCountryName(cntry);
										eForm.setAppStateName(state);
										eForm.setAppDistrictName(district);
										eForm.setAppAddress(addrs);
										eForm.setAppFatherName(fathrNme);
										eForm.setAppPersons(noOfPrsns);
										eForm.setAppDistrict(disId);
										if (deedDuratn != null)
											eForm.setDeedDuration(deedDuratn);
										else
											eForm.setDeedDuration("-");
										
									}
									
								}
							}
						}
						
					}
					
					// party details--party//
					ArrayList partydtls = new ArrayList();
					ArrayList comp6 = new ArrayList();
					partydtls = bo.getPartyDtls(eForm.getMainTxnId(), language);
					if (partydtls.size() > 0) {
						for (int i = 0; i < partydtls.size(); i++) {
							comp6.add((ArrayList) partydtls.get(i));
							if (comp6 != null && comp6.size() > 0) {
								for (int k = 0; k < comp6.size(); k++) {
									ArrayList compSub = (ArrayList) comp6.get(k);
									
									String name = (String) compSub.get(0);
									String cntry = (String) compSub.get(1);
									String state = (String) compSub.get(2);
									String district = (String) compSub.get(3);
									String addrs = (String) compSub.get(4);
									String fathrNme = (String) compSub.get(5);
									String noOfPrsns = (String) compSub.get(6);
									String orgName = (String) compSub.get(7);
									
									if (orgName != null) {
										eForm.setPartyNamedsply(orgName);
										eForm.setPartyCountryName(cntry);
										eForm.setPartyStateName(state);
										eForm.setPartyDistrictName(district);
										eForm.setPartyAddress(addrs);
										eForm.setPartyAuthPersonName(name);
										eForm.setPartyPersons(noOfPrsns);
										eForm.setIsAuthparty(1);
										eForm.setPartyFatherName(null);
									} else {
										eForm.setPartyNamedsply(name);
										eForm.setPartyCountryName(cntry);
										eForm.setPartyStateName(state);
										eForm.setPartyDistrictName(district);
										eForm.setPartyAddress(addrs);
										eForm.setPartyFatherName(fathrNme);
										eForm.setPartyPersons(noOfPrsns);
									}
									
									/*
									 * if (orgName.equalsIgnoreCase(null)){
									 * eForm.setPartyNamedsply(name);
									 * eForm.setPartyCountryName(cntry);
									 * eForm.setPartyStateName(state);
									 * eForm.setPartyDistrictName(district);
									 * eForm.setPartyAddress(addrs);
									 * eForm.setPartyFatherName(fathrNme);
									 * eForm.setPartyPersons(noOfPrsns); }else{
									 * eForm.setPartyNamedsply(orgName);
									 * eForm.setPartyCountryName(cntry);
									 * eForm.setPartyStateName(state);
									 * eForm.setPartyDistrictName(district);
									 * eForm.setPartyAddress(addrs);
									 * eForm.setPartyAuthPersonName(name);
									 * eForm.setPartyPersons(noOfPrsns);
									 * eForm.setIsAuthparty(1);
									 * 
									 * }
									 */

								}
							}
						}
						
					} else {
						eForm.setPartyNamedsply("-");
						eForm.setPartyCountryName("-");
						eForm.setPartyStateName("-");
						eForm.setPartyDistrictName("-");
						eForm.setPartyAddress("-");
						eForm.setPartyFatherName("-");
						eForm.setPartyPersons("-");
						
					}
					// generation of e-code and insertion into the tables.
					try {
						boolean boo1 = false;
						boo1 = bo.inserteCode(eForm, objDashBoardDTO1);
						
						if (boo) {
							
							ArrayList ecodedtls = new ArrayList();
							ArrayList comp7 = new ArrayList();
							ecodedtls = bo.getEcodeDtls(eForm.getMainTxnId(), language);
							if (ecodedtls.size() > 0) {
								for (int i = 0; i < ecodedtls.size(); i++) {
									comp7.add((ArrayList) ecodedtls.get(i));
									if (comp7 != null && comp7.size() > 0) {
										for (int k = 0; k < comp7.size(); k++) {
											ArrayList compSub = (ArrayList) comp7.get(k);
											
											String ecode = (String) compSub.get(0);
											String amt = (String) compSub.get(1);
											String estampType = (String) compSub.get(2);
											String estampDate = (String) compSub.get(3);
											String issuedBy = (String) compSub.get(4);
											String offc = (String) compSub.get(5);
											String place = (String) compSub.get(6);
											String validityDt = (String) compSub.get(7);
											eForm.setEcode(ecode);
											eForm.getDutyCalculationDTO().setTotalDisplay(amt);
											eForm.setEstampType(estampType);
											/*
											 * SimpleDateFormat sdf1 = new
											 * SimpleDateFormat
											 * ("yyyy-MM-dd- KK:mm:ss");
											 * SimpleDateFormat sdf2 = new
											 * SimpleDateFormat
											 * ("dd/MM/yyyy hh:mm:ss a"); logger
											 * .debug("the date is-----"
											 * +estampDate); Date d1 =
											 * sdf1.parse(estampDate); String
											 * transDate = sdf2.format(d1);
											 */
											eForm.setCurrentDate(estampDate);
											eForm.setUserName(issuedBy);
											eForm.setOfficeName(offc);
											eForm.setIssuedPlace(place);
											eForm.setEstampValidity(validityDt);
											logger.debug("Just before Final page redirection");
											forwardJsp = "ecodePage";
											
										}
									}
								}
								
							}
						} else {
							forwardJsp = "failure";
						}
						
					} catch (Exception e) {
						
					}
					
				}
				// ----------------modify action ends-----------------
				
				// ---------------payment action starts---------------
				
				// if ("NEXT_TO_PAYMENT_ACTION".equalsIgnoreCase(actionName)){
				
				if ("NEXT_TO_PAYMENT_ACTION".equals(actionName)) {
					
					// to check the Estamp is genuine
					
					boolean checkFlag = bo.checkEstampValidity(eForm.getMainTxnId(), eForm.getDeedDraftId());
					if (!checkFlag) {
						logger.debug("Exception while inserting record, deed or inst ID are coming as 0");
						if (language.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG, "There was a problem generating the estamp. Please try again!!!");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG, "ई-स्टाम्प सुरक्षित नहीं हो पाया है। कृपया पुनः प्रयास करें!!!");
						forwardJsp = CommonConstant.FORWARD_PAGE_DASHBOARD;
						return mapping.findForward(forwardJsp);
					}
					
					ArrayList first = new ArrayList();
					first = bo.getPayDtls(eForm.getMainTxnId());
					if (first.size() == 0) {
						logger.debug("inside if");
						String duty = bo.getDuty(eForm.getMainTxnId());
						eForm.getDutyCalculationDTO().setTotal(Double.parseDouble(duty));
						boolean insrt = false;
						try {
							
							insrt = bo.insertPay(eForm, objDashBoardDTO1);
							
							if (insrt) {
								String type = objEstampBO.gettype(userId); // --added
								// by
								// satbir
								// kumar--
								
								logger.debug(type);
								
								if ("3".equalsIgnoreCase(type) || "4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
									ArrayList spdetails = bo.getDetails(userId, objDashBoardDTO1);
									
									if (spdetails != null) {
										logger.debug(objDashBoardDTO1.getDistrictid());
										logger.debug(objDashBoardDTO1.getDistrictname());
										
										request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
										
										request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
										request.setAttribute("parentOfficeId", "NA");
										request.setAttribute("parentOfficeName", "NA");
										request.setAttribute("parentReferenceId", eForm.getMainTxnId());
									}
								}
								
								if ("2".equalsIgnoreCase(type)) {
									String txnId = eForm.getMainTxnId();
									ArrayList rudetails = bo.getruDetails(userId, objDashBoardDTO1, txnId, language);
									
									if (rudetails != null) {
										logger.debug(objDashBoardDTO1.getDistrictid());
										logger.debug(objDashBoardDTO1.getDistrictname());
										
										request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
										
										request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
										request.setAttribute("parentOfficeId", "NA");
										request.setAttribute("parentOfficeName", "NA");
										request.setAttribute("parentReferenceId", eForm.getMainTxnId());
									}
								}
								
								if ("I".equalsIgnoreCase(type)) {
									String offcId = (String) session.getAttribute("loggedToOffice");
									ArrayList iudetails = bo.getiuDetails(objDashBoardDTO1, offcId, language);
									if (iudetails != null) {
										logger.debug(objDashBoardDTO1.getDistrictid());
										logger.debug(objDashBoardDTO1.getDistrictname());
										
										request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
										
										request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
										request.setAttribute("parentOfficeId", objDashBoardDTO1.getIuofficeid());
										request.setAttribute("parentOfficeName", objDashBoardDTO1.getIuofficename());
										request.setAttribute("parentReferenceId", eForm.getMainTxnId());
									}
									
								}
								logger.debug("inside if insrt");
								DecimalFormat myformatter = new DecimalFormat("############");
								request.setAttribute("parentModName", "E-Stamping");
								request.setAttribute("parentFunName", "E-Stamp Non Judicial");
								request.setAttribute("parentFunId", "FUN_023");
								request.setAttribute("parentAmount", myformatter.format(eForm.getDutyCalculationDTO().getTotal()));
								request.setAttribute("parentTable", "IGRS_ESTAMP_PAYMENT_DETLS");
								request.setAttribute("parentKey", eForm.getUniqKey());
								request.setAttribute("forwardPath", "./EstampDutyCalculation.do?TRFS=NGI");
								request.setAttribute("parentColumnName", "ESTAMP_PAYMENT_ID");
								request.setAttribute("formName", "estamp");
								
								logger.debug("just before redirection");
								if (Double.parseDouble(duty) > 0) {
									forwardJsp = "nextToPay";
								} else {
									String eCodeNumber = "";
									
									// duty//
									duty = bo.getDuty(eForm.getMainTxnId());
									eForm.getDutyCalculationDTO().setTotal(Double.parseDouble(duty));
									eForm.getDutyCalculationDTO().setTotalDisplay(myformatter.format(Double.parseDouble(duty)));
									// date and time//
									String currDate = bo.getCurrentDate();
									eForm.setCurrentDate(currDate);
									// deeds and instruments and purpose//
									ArrayList second = new ArrayList();
									ArrayList comp1 = new ArrayList();
									second = bo.getDeedDtls(eForm.getMainTxnId(), language);
									if (second.size() > 0) {
										for (int i = 0; i < second.size(); i++) {
											comp1.add((ArrayList) second.get(i));
											if (comp1 != null && comp1.size() > 0) {
												for (int k = 0; k < comp1.size(); k++) {
													ArrayList compSub = (ArrayList) comp1.get(k);
													String deed = (String) compSub.get(0);
													String inst = (String) compSub.get(1);
													String purpose = (String) compSub.get(2);
													String instDiscp = (String) compSub.get(3);
													logger.debug("the deed is.........................." + deed);
													logger.debug("the inst is.........................." + inst);
													logger.debug("the purpose is.........................." + purpose);
													eForm.getDutyCalculationDTO().setDeedType(deed);
													eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
													eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
													eForm.getInstDTO().setInstType(inst);
													eForm.setEstampPurpose(purpose);
												}
											}
										}
									}
									// names and office details//
									
									type = objEstampBO.gettype(userId); // --added
									// by
									// satbir
									// kumar--
									
									logger.debug(type);
									/*
									 * String roleId = bo.getRole(userId);
									 * String srrole = "GR1356609170484"; String
									 * drrole = "GR1356671177515"; String sprole
									 * = "GR1358488496205"; String rurole =
									 * "GR1357368369828"; String spbankrole =
									 * "GR1359453019113";
									 * 
									 * 
									 * 
									 * 
									 * eForm.getObjDashBoardDTO().setRole(roleId)
									 * ;
									 */

									if ("2".equalsIgnoreCase(type)) {
										ArrayList userdet = new ArrayList();
										ArrayList comp2 = new ArrayList();
										userdet = bo.getruName(userId, language);
										if (userdet.size() > 0) {
											for (int i = 0; i < userdet.size(); i++) {
												comp2.add((ArrayList) userdet.get(i));
												if (comp2 != null && comp2.size() > 0) {
													for (int k = 0; k < comp2.size(); k++) {
														ArrayList compSub = (ArrayList) comp2.get(k);
														
														String name = (String) compSub.get(0);
														String cid = (String) compSub.get(1);
														String sid = (String) compSub.get(2);
														String did = (String) compSub.get(3);
														String dname = (String) compSub.get(4);
														
														eForm.setUserName(name);
														if (cid.equalsIgnoreCase("1")) {
															if (sid.equalsIgnoreCase("1")) {
																eForm.setIssuedPlace(dname);
																
															} else {
																if ("en".equalsIgnoreCase(language)) {
																	eForm.setIssuedPlace("Others");
																} else {
																	eForm.setIssuedPlace("अन्य");
																}
															}
															
														} else {
															if ("en".equalsIgnoreCase(language)) {
																eForm.setIssuedPlace("Others");
															} else {
																eForm.setIssuedPlace("अन्य");
															}
														}
														
													}
												}
											}
										}
										
										eForm.setOfficeName("-");
									}

									else if ("3".equalsIgnoreCase(type)) {
										String spname = bo.getspName(userId);
										String licNo = bo.getspLicenseNo(userId);
										ArrayList spdtls = new ArrayList();
										ArrayList comp3 = new ArrayList();
										spdtls = bo.getspDtls(userId, language);
										if (spdtls.size() > 0) {
											for (int i = 0; i < spdtls.size(); i++) {
												comp3.add((ArrayList) spdtls.get(i));
												if (comp3 != null && comp3.size() > 0) {
													for (int k = 0; k < comp3.size(); k++) {
														ArrayList compSub = (ArrayList) comp3.get(k);
														
														String ofc = (String) compSub.get(0);
														String plc = (String) compSub.get(1);
														String tehsil = (String) compSub.get(2);
														eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
														eForm.setIssuedPlace(plc);
														eForm.setUserName(spname + "/" + licNo);
													}
												}
											}
											
										}
									}

									else if ("4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
										
										ArrayList spbankdtls = new ArrayList();
										ArrayList comp4 = new ArrayList();
										spbankdtls = bo.getspbnkDtls(userId, language);
										if (spbankdtls.size() > 0) {
											for (int i = 0; i < spbankdtls.size(); i++) {
												comp4.add((ArrayList) spbankdtls.get(i));
												if (comp4 != null && comp4.size() > 0) {
													for (int k = 0; k < comp4.size(); k++) {
														ArrayList compSub = (ArrayList) comp4.get(k);
														
														String uname = (String) compSub.get(0);
														String ofc = (String) compSub.get(1);
														String plc = (String) compSub.get(2);
														String tehsil = (String) compSub.get(3);
														eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
														eForm.setIssuedPlace(plc);
														eForm.setUserName(uname);
													}
												}
											}
											
										}
									}

									else if ("I".equalsIgnoreCase(type)) {
										ArrayList iudtls = new ArrayList();
										ArrayList comp4 = new ArrayList();
										String officeId = (String) session.getAttribute("loggedToOffice");
										iudtls = bo.getiuDtls(userId, officeId, language);
										if (iudtls.size() > 0) {
											for (int i = 0; i < iudtls.size(); i++) {
												comp4.add((ArrayList) iudtls.get(i));
												if (comp4 != null && comp4.size() > 0) {
													for (int k = 0; k < comp4.size(); k++) {
														ArrayList compSub = (ArrayList) comp4.get(k);
														
														String ofc = (String) compSub.get(0);
														String plc = (String) compSub.get(1);
														String uname = (String) compSub.get(2);
														eForm.setOfficeName(ofc);
														eForm.setIssuedPlace(plc);
														eForm.setUserName(uname);
													}
												}
											}
											
										}
									}
									
									// party details--applicant//
									ArrayList appdtls = new ArrayList();
									ArrayList comp5 = new ArrayList();
									appdtls = bo.getAppDtls(eForm.getMainTxnId(), language);
									if (appdtls.size() > 0) {
										for (int i = 0; i < appdtls.size(); i++) {
											comp5.add((ArrayList) appdtls.get(i));
											if (comp5 != null && comp5.size() > 0) {
												for (int k = 0; k < comp5.size(); k++) {
													ArrayList compSub = (ArrayList) comp5.get(k);
													
													String name = (String) compSub.get(0);
													String cntry = (String) compSub.get(1);
													String state = (String) compSub.get(2);
													String district = (String) compSub.get(3);
													String addrs = (String) compSub.get(4);
													String fathrNme = (String) compSub.get(5);
													String noOfPrsns = (String) compSub.get(6);
													String orgName = (String) compSub.get(7);
													String disId = (String) compSub.get(8);
													String deedDuratn = (String) compSub.get(9);
													
													if (orgName != null) {
														
														eForm.setAppNamedsply(orgName);
														eForm.setAppCountryName(cntry);
														eForm.setAppStateName(state);
														eForm.setAppDistrictName(district);
														eForm.setAppAddress(addrs);
														eForm.setAppAuthPersonName(name);
														eForm.setAppPersons(noOfPrsns);
														eForm.setAppDistrict(disId);
														eForm.setIsAuthapp(1);
														eForm.setDeedDuration(deedDuratn);
													} else {
														eForm.setAppNamedsply(name);
														eForm.setAppCountryName(cntry);
														eForm.setAppStateName(state);
														eForm.setAppDistrictName(district);
														eForm.setAppAddress(addrs);
														eForm.setAppFatherName(fathrNme);
														eForm.setAppPersons(noOfPrsns);
														eForm.setAppDistrict(disId);
														if (deedDuratn != null)
															eForm.setDeedDuration(deedDuratn);
														else
															eForm.setDeedDuration("-");
														
													}
													
												}
											}
										}
										
									}
									
									// party details--party//
									ArrayList partydtls = new ArrayList();
									ArrayList comp6 = new ArrayList();
									partydtls = bo.getPartyDtls(eForm.getMainTxnId(), language);
									if (partydtls.size() > 0) {
										for (int i = 0; i < partydtls.size(); i++) {
											comp6.add((ArrayList) partydtls.get(i));
											if (comp6 != null && comp6.size() > 0) {
												for (int k = 0; k < comp6.size(); k++) {
													ArrayList compSub = (ArrayList) comp6.get(k);
													
													String name = (String) compSub.get(0);
													String cntry = (String) compSub.get(1);
													String state = (String) compSub.get(2);
													String district = (String) compSub.get(3);
													String addrs = (String) compSub.get(4);
													String fathrNme = (String) compSub.get(5);
													String noOfPrsns = (String) compSub.get(6);
													String orgName = (String) compSub.get(7);
													
													if (orgName != null) {
														eForm.setPartyNamedsply(orgName);
														eForm.setPartyCountryName(cntry);
														eForm.setPartyStateName(state);
														eForm.setPartyDistrictName(district);
														eForm.setPartyAddress(addrs);
														eForm.setPartyAuthPersonName(name);
														eForm.setPartyPersons(noOfPrsns);
														eForm.setIsAuthparty(1);
													} else {
														eForm.setPartyNamedsply(name);
														eForm.setPartyCountryName(cntry);
														eForm.setPartyStateName(state);
														eForm.setPartyDistrictName(district);
														eForm.setPartyAddress(addrs);
														eForm.setPartyFatherName(fathrNme);
														eForm.setPartyPersons(noOfPrsns);
													}
													
													/*
													 * if
													 * (orgName.equalsIgnoreCase
													 * (null)){
													 * eForm.setPartyNamedsply
													 * (name);
													 * eForm.setPartyCountryName
													 * (cntry);
													 * eForm.setPartyStateName
													 * (state);
													 * eForm.setPartyDistrictName
													 * (district);
													 * eForm.setPartyAddress
													 * (addrs);
													 * eForm.setPartyFatherName
													 * (fathrNme);
													 * eForm.setPartyPersons
													 * (noOfPrsns); }else{
													 * eForm.
													 * setPartyNamedsply(orgName
													 * );
													 * eForm.setPartyCountryName
													 * (cntry);
													 * eForm.setPartyStateName
													 * (state);
													 * eForm.setPartyDistrictName
													 * (district);
													 * eForm.setPartyAddress
													 * (addrs);
													 * eForm.setPartyAuthPersonName
													 * (name);
													 * eForm.setPartyPersons
													 * (noOfPrsns);
													 * eForm.setIsAuthparty(1);
													 * 
													 * }
													 */

												}
											}
										}
										
									} else {
										eForm.setPartyNamedsply("-");
										eForm.setPartyCountryName("-");
										eForm.setPartyStateName("-");
										eForm.setPartyDistrictName("-");
										eForm.setPartyAddress("-");
										eForm.setPartyFatherName("-");
										eForm.setPartyPersons("-");
										
									}
									// generation of e-code and insertion into
									// the tables.
									try {
										boolean boo = false;
										boo = bo.inserteCode(eForm, objDashBoardDTO1);
										
										if (boo) {
											
											ArrayList ecodedtls = new ArrayList();
											ArrayList comp7 = new ArrayList();
											ecodedtls = bo.getEcodeDtls(eForm.getMainTxnId(), language);
											if (ecodedtls.size() > 0) {
												for (int i = 0; i < ecodedtls.size(); i++) {
													comp7.add((ArrayList) ecodedtls.get(i));
													if (comp7 != null && comp7.size() > 0) {
														for (int k = 0; k < comp7.size(); k++) {
															ArrayList compSub = (ArrayList) comp7.get(k);
															eCodeNumber = (String) compSub.get(0);
															String ecode = (String) compSub.get(0);
															String amt = (String) compSub.get(1);
															String estampType = (String) compSub.get(2);
															String estampDate = (String) compSub.get(3);
															String issuedBy = (String) compSub.get(4);
															String offc = (String) compSub.get(5);
															String place = (String) compSub.get(6);
															String validityDt = (String) compSub.get(7);
															eForm.setEcode(ecode);
															eForm.getDutyCalculationDTO().setTotalDisplay(amt);
															eForm.setEstampType(estampType);
															/*
															 * SimpleDateFormat
															 * sdf1 = new
															 * SimpleDateFormat
															 * (
															 * "yyyy-MM-dd- KK:mm:ss"
															 * );
															 * SimpleDateFormat
															 * sdf2 = new
															 * SimpleDateFormat
															 * (
															 * "dd/MM/yyyy hh:mm:ss a"
															 * ); logger.debug(
															 * "the date is-----"
															 * +estampDate);
															 * Date d1 =
															 * sdf1.parse
															 * (estampDate);
															 * String transDate
															 * =
															 * sdf2.format(d1);
															 */
															eForm.setCurrentDate(estampDate);
															eForm.setUserName(issuedBy);
															eForm.setOfficeName(offc);
															eForm.setIssuedPlace(place);
															eForm.setEstampValidity(validityDt);
															logger.debug("Just before Final page redirection");
															forwardJsp = "ecodePage";
															
														}
													}
												}
												
											}
										} else {
											forwardJsp = "failure";
										}
										
									} catch (Exception e) {
										
									}
									if (language.equalsIgnoreCase("en")) {
										request.setAttribute("success_msg", "The Estamp has been created successfully. Estamp code is: " + eCodeNumber);
									} else {
										request.setAttribute("success_msg", "ई स्टाम्प सफलतापूर्वक बना दिया गया है। ई स्टाम्प कोड : " + eCodeNumber);
									}
									
									forwardJsp = "confirmation";
								}
								
							} else {
								forwardJsp = "failure";
							}
						} catch (Exception e) {
							forwardJsp = "failure";
						}
						
					} else {
						
						logger.debug("inside else---means record is found");
						ArrayList comp = new ArrayList();
						String uniqId = "";
						String amtToBePaid = "";
						String paidAmt = "";
						String pymtFlg = "";
						
						for (int i = 0; i < first.size(); i++) {
							comp.add((ArrayList) first.get(i));
							if (comp != null && comp.size() > 0) {
								for (int k = 0; k < comp.size(); k++) {
									ArrayList compSub = (ArrayList) comp.get(k);
									uniqId = (String) compSub.get(0);
									amtToBePaid = (String) compSub.get(1);
									paidAmt = (String) compSub.get(2);
									pymtFlg = (String) compSub.get(3);
									logger.debug("unique id is ............................" + uniqId);
									logger.debug("unpayable amt is ............................" + amtToBePaid);
									logger.debug("paid amt is..................................." + paidAmt);
									logger.debug("pymtFlg is....................................." + pymtFlg);
								}
							}
						}
						double damtToBePaid = Double.parseDouble(amtToBePaid);
						
						if (pymtFlg.equalsIgnoreCase("I")) {
							
							String type = objEstampBO.gettype(userId); // --added
							// by
							// satbir
							// kumar--
							
							logger.debug(type);
							
							if ("3".equalsIgnoreCase(type) || "4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
								ArrayList spdetails = bo.getDetails(userId, objDashBoardDTO1);
								
								if (spdetails != null) {
									logger.debug(objDashBoardDTO1.getDistrictid());
									logger.debug(objDashBoardDTO1.getDistrictname());
									
									request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
									
									request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
									request.setAttribute("parentOfficeId", "NA");
									request.setAttribute("parentOfficeName", "NA");
									request.setAttribute("parentReferenceId", eForm.getMainTxnId());
								}
							}
							
							if ("2".equalsIgnoreCase(type)) {
								String txnId = eForm.getMainTxnId();
								ArrayList rudetails = bo.getruDetails(userId, objDashBoardDTO1, txnId, language);
								
								if (rudetails != null) {
									logger.debug(objDashBoardDTO1.getDistrictid());
									logger.debug(objDashBoardDTO1.getDistrictname());
									
									request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
									
									request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
									request.setAttribute("parentOfficeId", "NA");
									request.setAttribute("parentOfficeName", "NA");
									request.setAttribute("parentReferenceId", eForm.getMainTxnId());
								}
							}
							
							if ("I".equalsIgnoreCase(type)) {
								String offcId = (String) session.getAttribute("loggedToOffice");
								ArrayList iudetails = bo.getiuDetails(objDashBoardDTO1, offcId, language);
								if (iudetails != null) {
									logger.debug(objDashBoardDTO1.getDistrictid());
									logger.debug(objDashBoardDTO1.getDistrictname());
									
									request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
									
									request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
									request.setAttribute("parentOfficeId", objDashBoardDTO1.getIuofficeid());
									request.setAttribute("parentOfficeName", objDashBoardDTO1.getIuofficename());
									request.setAttribute("parentReferenceId", eForm.getMainTxnId());
								}
								
							}
							
							DecimalFormat myformatter = new DecimalFormat("############");
							request.setAttribute("parentModName", "E-Stamping");
							request.setAttribute("parentFunName", "E-Stamp Non Judicial");
							request.setAttribute("parentFunId", "FUN_023");
							request.setAttribute("parentAmount", myformatter.format(damtToBePaid));
							request.setAttribute("parentTable", "IGRS_ESTAMP_PAYMENT_DETLS");
							request.setAttribute("parentKey", uniqId);
							request.setAttribute("forwardPath", "./EstampDutyCalculation.do?TRFS=NGI");
							request.setAttribute("parentColumnName", "ESTAMP_PAYMENT_ID");
							request.setAttribute("formName", "estamp");
							
							forwardJsp = "nextToPay";
						}
						
						if (pymtFlg.equalsIgnoreCase("P")) {
							
							double dpaidAmt = Double.parseDouble(paidAmt);
							// condition 1-------------------pending
							// amount-------------
							if (damtToBePaid > dpaidAmt) {
								double bal = damtToBePaid - dpaidAmt;
								boolean insrt = false;
								eForm.getDutyCalculationDTO().setTotal(bal);
								try {
									insrt = bo.insertPay(eForm, objDashBoardDTO1);
									
									if (insrt) {
										
										String type = objEstampBO.gettype(userId); // --added
																					// by
										// satbir
										// kumar--
										
										logger.debug(type);
										
										if ("3".equalsIgnoreCase(type) || "4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
											ArrayList spdetails = bo.getDetails(userId, objDashBoardDTO1);
											
											if (spdetails != null) {
												logger.debug(objDashBoardDTO1.getDistrictid());
												logger.debug(objDashBoardDTO1.getDistrictname());
												
												request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
												
												request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
												request.setAttribute("parentOfficeId", "NA");
												request.setAttribute("parentOfficeName", "NA");
												request.setAttribute("parentReferenceId", eForm.getMainTxnId());
											}
										}
										
										if ("2".equalsIgnoreCase(type)) {
											String txnId = eForm.getMainTxnId();
											
											ArrayList rudetails = bo.getruDetails(userId, objDashBoardDTO1, txnId, language);
											
											if (rudetails != null) {
												logger.debug(objDashBoardDTO1.getDistrictid());
												logger.debug(objDashBoardDTO1.getDistrictname());
												
												request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
												
												request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
												request.setAttribute("parentOfficeId", "NA");
												request.setAttribute("parentOfficeName", "NA");
												request.setAttribute("parentReferenceId", eForm.getMainTxnId());
											}
										}
										
										if ("I".equalsIgnoreCase(type)) {
											String offcId = (String) session.getAttribute("loggedToOffice");
											ArrayList iudetails = bo.getiuDetails(objDashBoardDTO1, offcId, language);
											if (iudetails != null) {
												logger.debug(objDashBoardDTO1.getDistrictid());
												logger.debug(objDashBoardDTO1.getDistrictname());
												
												request.setAttribute("parentDistrictId", objDashBoardDTO1.getDistrictid());
												
												request.setAttribute("parentDistrictName", objDashBoardDTO1.getDistrictname());
												request.setAttribute("parentOfficeId", objDashBoardDTO1.getIuofficeid());
												request.setAttribute("parentOfficeName", objDashBoardDTO1.getIuofficename());
												request.setAttribute("parentReferenceId", eForm.getMainTxnId());
											}
											
										}
										logger.debug("inside if insrt-----of else condition---means record found");
										DecimalFormat myformatter = new DecimalFormat("############");
										request.setAttribute("parentModName", "E-Stamping");
										request.setAttribute("parentFunName", "E-Stamp Non Judicial");
										request.setAttribute("parentFunId", "FUN_023");
										request.setAttribute("parentAmount", myformatter.format(eForm.getDutyCalculationDTO().getTotal()));
										request.setAttribute("parentTable", "IGRS_ESTAMP_PAYMENT_DETLS");
										request.setAttribute("parentKey", eForm.getUniqKey());
										request.setAttribute("forwardPath", "./EstampDutyCalculation.do?TRFS=NGI");
										request.setAttribute("parentColumnName", "ESTAMP_PAYMENT_ID");
										request.setAttribute("formName", "estamp");
										
										logger.debug("just before redirection");
										forwardJsp = "nextToPay";
										
									} else {
										forwardJsp = "failure";
									}
								} catch (Exception e) {
									forwardJsp = "failure";
								}
								
							}
							
							// condition 2-------------------full payment, eCode
							// generation------
							if (damtToBePaid == dpaidAmt) {
								DecimalFormat myformatter = new DecimalFormat("############");
								double bal = damtToBePaid - dpaidAmt;
								// duty//
								String duty = bo.getDuty(eForm.getMainTxnId());
								eForm.getDutyCalculationDTO().setTotal(Double.parseDouble(duty));
								eForm.getDutyCalculationDTO().setTotalDisplay(myformatter.format(Double.parseDouble(duty)));
								// date and time//
								String currDate = bo.getCurrentDate();
								eForm.setCurrentDate(currDate);
								// deeds and instruments and purpose//
								ArrayList second = new ArrayList();
								ArrayList comp1 = new ArrayList();
								second = bo.getDeedDtls(eForm.getMainTxnId(), language);
								if (second.size() > 0) {
									for (int i = 0; i < second.size(); i++) {
										comp1.add((ArrayList) second.get(i));
										if (comp1 != null && comp1.size() > 0) {
											for (int k = 0; k < comp1.size(); k++) {
												ArrayList compSub = (ArrayList) comp1.get(k);
												String deed = (String) compSub.get(0);
												String inst = (String) compSub.get(1);
												String purpose = (String) compSub.get(2);
												String instDiscp = (String) compSub.get(3);
												logger.debug("the deed is.........................." + deed);
												logger.debug("the inst is.........................." + inst);
												logger.debug("the purpose is.........................." + purpose);
												eForm.getDutyCalculationDTO().setDeedType(deed);
												eForm.getInstDTO().setInstType(inst);
												eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
												eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
												eForm.setEstampPurpose(purpose);
											}
										}
									}
								}
								// names and office details//
								String type = objEstampBO.gettype(userId); // --added
								// by
								// satbir
								// kumar--
								
								logger.debug(type);
								/*
								 * String roleId = bo.getRole(userId); String
								 * srrole = "GR1356609170484"; String drrole =
								 * "GR1356671177515"; String sprole =
								 * "GR1358488496205"; String rurole =
								 * "GR1357368369828"; String spbankrole =
								 * "GR1359453019113";
								 * 
								 * eForm.getObjDashBoardDTO().setRole(roleId);
								 */
								if ("2".equalsIgnoreCase(type)) {
									ArrayList userdet = new ArrayList();
									ArrayList comp2 = new ArrayList();
									userdet = bo.getruName(userId, language);
									if (userdet.size() > 0) {
										for (int i = 0; i < userdet.size(); i++) {
											comp2.add((ArrayList) userdet.get(i));
											if (comp2 != null && comp2.size() > 0) {
												for (int k = 0; k < comp2.size(); k++) {
													ArrayList compSub = (ArrayList) comp2.get(k);
													
													String name = (String) compSub.get(0);
													String cid = (String) compSub.get(1);
													String sid = (String) compSub.get(2);
													String did = (String) compSub.get(3);
													String dname = (String) compSub.get(4);
													
													eForm.setUserName(name);
													if (cid.equalsIgnoreCase("1")) {
														if (sid.equalsIgnoreCase("1")) {
															eForm.setIssuedPlace(dname);
															
														} else {
															if ("en".equalsIgnoreCase(language)) {
																eForm.setIssuedPlace("Others");
															} else {
																eForm.setIssuedPlace("अन्य");
															}
														}
														
													} else {
														if ("en".equalsIgnoreCase(language)) {
															eForm.setIssuedPlace("Others");
														} else {
															eForm.setIssuedPlace("अन्य");
														}
													}
													
												}
											}
										}
									}
									
									eForm.setOfficeName("-");
								}

								else if ("3".equalsIgnoreCase(type)) {
									String spname = bo.getspName(userId);
									String licNo = bo.getspLicenseNo(userId);
									ArrayList spdtls = new ArrayList();
									ArrayList comp3 = new ArrayList();
									spdtls = bo.getspDtls(userId, language);
									if (spdtls.size() > 0) {
										for (int i = 0; i < spdtls.size(); i++) {
											comp3.add((ArrayList) spdtls.get(i));
											if (comp3 != null && comp3.size() > 0) {
												for (int k = 0; k < comp3.size(); k++) {
													ArrayList compSub = (ArrayList) comp3.get(k);
													
													String ofc = (String) compSub.get(0);
													String plc = (String) compSub.get(1);
													String tehsil = (String) compSub.get(2);
													eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
													eForm.setIssuedPlace(plc);
													eForm.setUserName(spname + "/" + licNo);
												}
											}
										}
										
									}
								}

								else if ("4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
									
									ArrayList spbankdtls = new ArrayList();
									ArrayList comp4 = new ArrayList();
									spbankdtls = bo.getspbnkDtls(userId, language);
									if (spbankdtls.size() > 0) {
										for (int i = 0; i < spbankdtls.size(); i++) {
											comp4.add((ArrayList) spbankdtls.get(i));
											if (comp4 != null && comp4.size() > 0) {
												for (int k = 0; k < comp4.size(); k++) {
													ArrayList compSub = (ArrayList) comp4.get(k);
													
													String uname = (String) compSub.get(0);
													String ofc = (String) compSub.get(1);
													String plc = (String) compSub.get(2);
													String tehsil = (String) compSub.get(3);
													eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
													eForm.setIssuedPlace(plc);
													eForm.setUserName(uname);
												}
											}
										}
										
									}
								}

								else if ("I".equalsIgnoreCase(type)) {
									ArrayList iudtls = new ArrayList();
									ArrayList comp4 = new ArrayList();
									String officeId = (String) session.getAttribute("loggedToOffice");
									iudtls = bo.getiuDtls(userId, officeId, language);
									if (iudtls.size() > 0) {
										for (int i = 0; i < iudtls.size(); i++) {
											comp4.add((ArrayList) iudtls.get(i));
											if (comp4 != null && comp4.size() > 0) {
												for (int k = 0; k < comp4.size(); k++) {
													ArrayList compSub = (ArrayList) comp4.get(k);
													
													String ofc = (String) compSub.get(0);
													String plc = (String) compSub.get(1);
													String uname = (String) compSub.get(2);
													eForm.setOfficeName(ofc);
													eForm.setIssuedPlace(plc);
													eForm.setUserName(uname);
												}
											}
										}
										
									}
								}
								
								// party details--applicant//
								ArrayList appdtls = new ArrayList();
								ArrayList comp5 = new ArrayList();
								appdtls = bo.getAppDtls(eForm.getMainTxnId(), language);
								if (appdtls.size() > 0) {
									for (int i = 0; i < appdtls.size(); i++) {
										comp5.add((ArrayList) appdtls.get(i));
										if (comp5 != null && comp5.size() > 0) {
											for (int k = 0; k < comp5.size(); k++) {
												ArrayList compSub = (ArrayList) comp5.get(k);
												
												String name = (String) compSub.get(0);
												String cntry = (String) compSub.get(1);
												String state = (String) compSub.get(2);
												String district = (String) compSub.get(3);
												String addrs = (String) compSub.get(4);
												String fathrNme = (String) compSub.get(5);
												String noOfPrsns = (String) compSub.get(6);
												String orgName = (String) compSub.get(7);
												String disId = (String) compSub.get(8);
												
												if (orgName == (null) || orgName.equalsIgnoreCase("")) {
													eForm.setAppNamedsply(name);
													eForm.setAppCountryName(cntry);
													eForm.setAppStateName(state);
													eForm.setAppDistrictName(district);
													eForm.setAppAddress(addrs);
													eForm.setAppFatherName(fathrNme);
													eForm.setAppPersons(noOfPrsns);
													eForm.setAppDistrict(disId);
												} else {
													eForm.setAppNamedsply(orgName);
													eForm.setAppCountryName(cntry);
													eForm.setAppStateName(state);
													eForm.setAppDistrictName(district);
													eForm.setAppAddress(addrs);
													eForm.setAppAuthPersonName(name);
													eForm.setAppPersons(noOfPrsns);
													eForm.setAppDistrict(disId);
													eForm.setIsAuthapp(1);
													
												}
												
											}
										}
									}
									
								}
								
								// party details--party//
								ArrayList partydtls = new ArrayList();
								ArrayList comp6 = new ArrayList();
								partydtls = bo.getPartyDtls(eForm.getMainTxnId(), language);
								if (partydtls.size() > 0) {
									for (int i = 0; i < partydtls.size(); i++) {
										comp6.add((ArrayList) partydtls.get(i));
										if (comp6 != null && comp6.size() > 0) {
											for (int k = 0; k < comp6.size(); k++) {
												ArrayList compSub = (ArrayList) comp6.get(k);
												
												String name = (String) compSub.get(0);
												String cntry = (String) compSub.get(1);
												String state = (String) compSub.get(2);
												String district = (String) compSub.get(3);
												String addrs = (String) compSub.get(4);
												String fathrNme = (String) compSub.get(5);
												String noOfPrsns = (String) compSub.get(6);
												String orgName = (String) compSub.get(7);
												
												if (orgName == (null) || orgName.equalsIgnoreCase("")) {
													eForm.setPartyNamedsply(name);
													eForm.setPartyCountryName(cntry);
													eForm.setPartyStateName(state);
													eForm.setPartyDistrictName(district);
													eForm.setPartyAddress(addrs);
													eForm.setPartyFatherName(fathrNme);
													eForm.setPartyPersons(noOfPrsns);
												} else {
													eForm.setPartyNamedsply(orgName);
													eForm.setPartyCountryName(cntry);
													eForm.setPartyStateName(state);
													eForm.setPartyDistrictName(district);
													eForm.setPartyAddress(addrs);
													eForm.setPartyAuthPersonName(name);
													eForm.setPartyPersons(noOfPrsns);
													eForm.setIsAuthparty(1);
													
												}
												
											}
										}
									}
									
								} else {
									eForm.setPartyNamedsply("-");
									eForm.setPartyCountryName("-");
									eForm.setPartyStateName("-");
									eForm.setPartyDistrictName("-");
									eForm.setPartyAddress("-");
									eForm.setPartyFatherName("-");
									eForm.setPartyPersons("-");
									
								}
								// generation of e-code and insertion into the
								// tables.
								try {
									boolean boo = false;
									// boolean checkEstamp = false;
									checkEstamp = objEstampBO.checkEstamp(eForm.getMainTxnId(), "E");
									if (checkEstamp) {
										boo = bo.inserteCode(eForm, objDashBoardDTO1);
									}
									
									if (boo) {
										
										ArrayList ecodedtls = new ArrayList();
										ArrayList comp7 = new ArrayList();
										ecodedtls = bo.getEcodeDtls(eForm.getMainTxnId(), language);
										if (ecodedtls.size() > 0) {
											for (int i = 0; i < ecodedtls.size(); i++) {
												comp7.add((ArrayList) ecodedtls.get(i));
												if (comp7 != null && comp7.size() > 0) {
													for (int k = 0; k < comp7.size(); k++) {
														ArrayList compSub = (ArrayList) comp7.get(k);
														
														String ecode = (String) compSub.get(0);
														String amt = (String) compSub.get(1);
														String estampType = (String) compSub.get(2);
														String estampDate = (String) compSub.get(3);
														String issuedBy = (String) compSub.get(4);
														String offc = (String) compSub.get(5);
														String place = (String) compSub.get(6);
														String validityDt = (String) compSub.get(7);
														eForm.setEcode(ecode);
														eForm.getDutyCalculationDTO().setTotalDisplay(amt);
														eForm.setEstampType(estampType);
														eForm.setCurrentDate(estampDate);
														eForm.setUserName(issuedBy);
														eForm.setOfficeName(offc);
														eForm.setIssuedPlace(place);
														eForm.setEstampValidity(validityDt);
														duty = bo.getDuty(eForm.getMainTxnId());
														bo.getDutyDetails(eForm.getMainTxnId(), eForm);
														forwardJsp = "ecodePageForZeroBal";
														
													}
												}
											}
											
										}
									} else {
										forwardJsp = "failure";
									}
									
								} catch (Exception e) {
									
								}
								
							}// --------end second condition
							
						}// -------end paymnent flag=P
						
						if (pymtFlg.equalsIgnoreCase("C")) {
							
							DecimalFormat myformatter = new DecimalFormat("############");
							// double bal= damtToBePaid-dpaidAmt;
							// duty//
							String duty = bo.getDuty(eForm.getMainTxnId());
							eForm.getDutyCalculationDTO().setTotal(Double.parseDouble(duty));
							eForm.getDutyCalculationDTO().setTotalDisplay(myformatter.format(Double.parseDouble(duty)));
							// date and time//
							String currDate = bo.getCurrentDate();
							eForm.setCurrentDate(currDate);
							// deeds and instruments and purpose//
							ArrayList second = new ArrayList();
							ArrayList comp1 = new ArrayList();
							second = bo.getDeedDtls(eForm.getMainTxnId(), language);
							if (second.size() > 0) {
								for (int i = 0; i < second.size(); i++) {
									comp1.add((ArrayList) second.get(i));
									if (comp1 != null && comp1.size() > 0) {
										for (int k = 0; k < comp1.size(); k++) {
											ArrayList compSub = (ArrayList) comp1.get(k);
											String deed = (String) compSub.get(0);
											String inst = (String) compSub.get(1);
											String purpose = (String) compSub.get(2);
											String instDiscp = (String) compSub.get(3);
											logger.debug("the deed is.........................." + deed);
											logger.debug("the inst is.........................." + inst);
											logger.debug("the purpose is.........................." + purpose);
											eForm.getDutyCalculationDTO().setDeedType(deed);
											eForm.getInstDTO().setInstType(inst);
											eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
											eForm.getDutyCalculationDTO().setInstDescription(instDiscp);
											eForm.setEstampPurpose(purpose);
										}
									}
								}
							}
							// names and office details//
							String type = objEstampBO.gettype(userId); // --added
							// by
							// satbir
							// kumar--
							
							logger.debug(type);
							/*
							 * String roleId = bo.getRole(userId); String srrole
							 * = "GR1356609170484"; String drrole =
							 * "GR1356671177515"; String sprole =
							 * "GR1358488496205"; String rurole =
							 * "GR1357368369828"; String spbankrole =
							 * "GR1359453019113";
							 * 
							 * eForm.getObjDashBoardDTO().setRole(roleId);
							 */

							if ("2".equalsIgnoreCase(type)) {
								ArrayList userdet = new ArrayList();
								ArrayList comp2 = new ArrayList();
								userdet = bo.getruName(userId, language);
								if (userdet.size() > 0) {
									for (int i = 0; i < userdet.size(); i++) {
										comp2.add((ArrayList) userdet.get(i));
										if (comp2 != null && comp2.size() > 0) {
											for (int k = 0; k < comp2.size(); k++) {
												ArrayList compSub = (ArrayList) comp2.get(k);
												
												String name = (String) compSub.get(0);
												String cid = (String) compSub.get(1);
												String sid = (String) compSub.get(2);
												String did = (String) compSub.get(3);
												String dname = (String) compSub.get(4);
												
												eForm.setUserName(name);
												if (cid.equalsIgnoreCase("1")) {
													if (sid.equalsIgnoreCase("1")) {
														eForm.setIssuedPlace(dname);
														
													} else {
														if ("en".equalsIgnoreCase(language)) {
															eForm.setIssuedPlace("Others");
														} else {
															eForm.setIssuedPlace("अन्य");
														}
													}
													
												} else {
													if ("en".equalsIgnoreCase(language)) {
														eForm.setIssuedPlace("Others");
													} else {
														eForm.setIssuedPlace("अन्य");
													}
												}
												
											}
										}
									}
								}
								
								eForm.setOfficeName("-");
							}

							else if ("3".equalsIgnoreCase(type)) {
								String spname = bo.getspName(userId);
								String licNo = bo.getspLicenseNo(userId);
								ArrayList spdtls = new ArrayList();
								ArrayList comp3 = new ArrayList();
								spdtls = bo.getspDtls(userId, language);
								if (spdtls.size() > 0) {
									for (int i = 0; i < spdtls.size(); i++) {
										comp3.add((ArrayList) spdtls.get(i));
										if (comp3 != null && comp3.size() > 0) {
											for (int k = 0; k < comp3.size(); k++) {
												ArrayList compSub = (ArrayList) comp3.get(k);
												
												String ofc = (String) compSub.get(0);
												String plc = (String) compSub.get(1);
												String tehsil = (String) compSub.get(2);
												eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
												eForm.setIssuedPlace(plc);
												eForm.setUserName(spname + "/" + licNo);
											}
										}
									}
									
								}
							}

							else if ("4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
								
								ArrayList spbankdtls = new ArrayList();
								ArrayList comp4 = new ArrayList();
								spbankdtls = bo.getspbnkDtls(userId, language);
								if (spbankdtls.size() > 0) {
									for (int i = 0; i < spbankdtls.size(); i++) {
										comp4.add((ArrayList) spbankdtls.get(i));
										if (comp4 != null && comp4.size() > 0) {
											for (int k = 0; k < comp4.size(); k++) {
												ArrayList compSub = (ArrayList) comp4.get(k);
												
												String uname = (String) compSub.get(0);
												String ofc = (String) compSub.get(1);
												String plc = (String) compSub.get(2);
												String tehsil = (String) compSub.get(3);
												eForm.setOfficeName(ofc + " " + tehsil + " " + plc);
												eForm.setIssuedPlace(plc);
												eForm.setUserName(uname);
											}
										}
									}
									
								}
							}

							else if ("I".equalsIgnoreCase(type)) {
								ArrayList iudtls = new ArrayList();
								ArrayList comp4 = new ArrayList();
								String officeId = (String) session.getAttribute("loggedToOffice");
								iudtls = bo.getiuDtls(userId, officeId, language);
								if (iudtls.size() > 0) {
									for (int i = 0; i < iudtls.size(); i++) {
										comp4.add((ArrayList) iudtls.get(i));
										if (comp4 != null && comp4.size() > 0) {
											for (int k = 0; k < comp4.size(); k++) {
												ArrayList compSub = (ArrayList) comp4.get(k);
												
												String ofc = (String) compSub.get(0);
												String plc = (String) compSub.get(1);
												String uname = (String) compSub.get(2);
												eForm.setOfficeName(ofc);
												eForm.setIssuedPlace(plc);
												eForm.setUserName(uname);
											}
										}
									}
									
								}
							}
							
							// party details--applicant//
							ArrayList appdtls = new ArrayList();
							ArrayList comp5 = new ArrayList();
							appdtls = bo.getAppDtls(eForm.getMainTxnId(), language);
							if (appdtls.size() > 0) {
								for (int i = 0; i < appdtls.size(); i++) {
									comp5.add((ArrayList) appdtls.get(i));
									if (comp5 != null && comp5.size() > 0) {
										for (int k = 0; k < comp5.size(); k++) {
											ArrayList compSub = (ArrayList) comp5.get(k);
											
											String name = (String) compSub.get(0);
											String cntry = (String) compSub.get(1);
											String state = (String) compSub.get(2);
											String district = (String) compSub.get(3);
											String addrs = (String) compSub.get(4);
											String fathrNme = (String) compSub.get(5);
											String noOfPrsns = (String) compSub.get(6);
											String orgName = (String) compSub.get(7);
											String disId = (String) compSub.get(8);
											
											if (orgName == (null) || orgName.equalsIgnoreCase("")) {
												eForm.setAppNamedsply(name);
												eForm.setAppCountryName(cntry);
												eForm.setAppStateName(state);
												eForm.setAppDistrictName(district);
												eForm.setAppAddress(addrs);
												eForm.setAppFatherName(fathrNme);
												eForm.setAppPersons(noOfPrsns);
												eForm.setAppDistrict(disId);
											} else {
												eForm.setAppNamedsply(orgName);
												eForm.setAppCountryName(cntry);
												eForm.setAppStateName(state);
												eForm.setAppDistrictName(district);
												eForm.setAppAddress(addrs);
												eForm.setAppAuthPersonName(name);
												eForm.setAppPersons(noOfPrsns);
												eForm.setAppDistrict(disId);
												eForm.setIsAuthapp(1);
												
											}
											
										}
									}
								}
								
							}
							
							// party details--party//
							ArrayList partydtls = new ArrayList();
							ArrayList comp6 = new ArrayList();
							partydtls = bo.getPartyDtls(eForm.getMainTxnId(), language);
							if (partydtls.size() > 0) {
								for (int i = 0; i < partydtls.size(); i++) {
									comp6.add((ArrayList) partydtls.get(i));
									if (comp6 != null && comp6.size() > 0) {
										for (int k = 0; k < comp6.size(); k++) {
											ArrayList compSub = (ArrayList) comp6.get(k);
											
											String name = (String) compSub.get(0);
											String cntry = (String) compSub.get(1);
											String state = (String) compSub.get(2);
											String district = (String) compSub.get(3);
											String addrs = (String) compSub.get(4);
											String fathrNme = (String) compSub.get(5);
											String noOfPrsns = (String) compSub.get(6);
											String orgName = (String) compSub.get(7);
											
											if (orgName == (null) || orgName.equalsIgnoreCase("")) {
												eForm.setPartyNamedsply(name);
												eForm.setPartyCountryName(cntry);
												eForm.setPartyStateName(state);
												eForm.setPartyDistrictName(district);
												eForm.setPartyAddress(addrs);
												eForm.setPartyFatherName(fathrNme);
												eForm.setPartyPersons(noOfPrsns);
											} else {
												eForm.setPartyNamedsply(orgName);
												eForm.setPartyCountryName(cntry);
												eForm.setPartyStateName(state);
												eForm.setPartyDistrictName(district);
												eForm.setPartyAddress(addrs);
												eForm.setPartyAuthPersonName(name);
												eForm.setPartyPersons(noOfPrsns);
												eForm.setIsAuthparty(1);
												
											}
											
										}
									}
								}
								
							} else {
								eForm.setPartyNamedsply("-");
								eForm.setPartyCountryName("-");
								eForm.setPartyStateName("-");
								eForm.setPartyDistrictName("-");
								eForm.setPartyAddress("-");
								eForm.setPartyFatherName("-");
								eForm.setPartyPersons("-");
								
							}
							// e-code details.
							
							ArrayList ecodedtls = new ArrayList();
							ArrayList comp7 = new ArrayList();
							ecodedtls = bo.getEcodeDtls(eForm.getMainTxnId(), language);
							if (ecodedtls.size() > 0) {
								for (int i = 0; i < ecodedtls.size(); i++) {
									comp7.add((ArrayList) ecodedtls.get(i));
									if (comp7 != null && comp7.size() > 0) {
										for (int k = 0; k < comp7.size(); k++) {
											ArrayList compSub = (ArrayList) comp7.get(k);
											
											String ecode = (String) compSub.get(0);
											String amt = (String) compSub.get(1);
											String estampType = (String) compSub.get(2);
											String estampDate = (String) compSub.get(3);
											String issuedBy = (String) compSub.get(4);
											String offc = (String) compSub.get(5);
											String place = (String) compSub.get(6);
											String validityDt = (String) compSub.get(7);
											eForm.setEcode(ecode);
											eForm.getDutyCalculationDTO().setTotalDisplay(amt);
											eForm.setEstampType(estampType);
											eForm.setCurrentDate(estampDate);
											eForm.setUserName(issuedBy);
											eForm.setOfficeName(offc);
											eForm.setIssuedPlace(place);
											eForm.setEstampValidity(validityDt);
											
											forwardJsp = "ecodePage";
											
										}
									}
								}
								
							}
							
						}// end payment flag=C
						
					}// -----------------end else condition--means record found.
					
				}// ---------------payment action ends---------------
				eForm.getDutyCalculationDTO().setFormName("");
				eForm.getDutyCalculationDTO().setActionName("");
			}
			
			// ------------------------------------------end of details view
			// page--------------------------
			
			// start of print page
			
			/*
			 * if ("ecodeFinalPage".equalsIgnoreCase(formName)) {
			 * 
			 * if ("COMPLETE_TXN".equalsIgnoreCase(actionName)) {
			 * 
			 * // --------------just update the ESTAMP_TXN_STATUS from S to // C
			 * in IGRS_ESTAMP_TXN_DETLS table to remove from // dashboard. }
			 * 
			 * }// end of print page
			 * 
			 * if (request.getParameter("paymentStatus") != null) {
			 * 
			 * if (request.getParameter("paymentStatus").equalsIgnoreCase("P"))
			 * { String uniId = (String) request.getParameter("parentKey"); if
			 * (uniId != null || uniId != "") { eForm.setUniqKey(uniId); }
			 * 
			 * String mainId = bo.getMainId(uniId); eForm.setMainTxnId(mainId);
			 * ArrayList first = new ArrayList(); ArrayList comp = new
			 * ArrayList(); String uniqId = ""; String amtToBePaid = ""; String
			 * paidAmt = ""; String pymtFlg = ""; first =
			 * bo.getPayDtls(eForm.getMainTxnId());
			 * 
			 * if (first.size() > 0) { for (int i = 0; i < first.size(); i++) {
			 * comp.add((ArrayList) first.get(i)); if (comp != null &&
			 * comp.size() > 0) { for (int k = 0; k < comp.size(); k++) {
			 * ArrayList compSub = (ArrayList) comp.get(k); uniqId = (String)
			 * compSub.get(0); amtToBePaid = (String) compSub.get(1); paidAmt =
			 * (String) compSub.get(2); pymtFlg = (String) compSub.get(3); } } }
			 * }
			 * 
			 * double damtToBePaid = Double.parseDouble(paidAmt); double
			 * dpaidAmt = Double.parseDouble(paidAmt); // condition
			 * 1-------------------pending amount------------- if (damtToBePaid
			 * > dpaidAmt) { double bal = damtToBePaid - dpaidAmt;
			 * eForm.setIsPartial(1); forwardJsp =
			 * CommonConstant.FORWARD_PAGE_VIEW; }
			 * 
			 * // condition 2-------------------full payment, eCode //
			 * generation------ if (damtToBePaid == dpaidAmt) { DecimalFormat
			 * myformatter = new DecimalFormat( "############"); double bal =
			 * damtToBePaid - dpaidAmt; // duty// String duty =
			 * bo.getDuty(eForm.getMainTxnId());
			 * eForm.getDutyCalculationDTO().setTotal(
			 * Double.parseDouble(duty));
			 * eForm.getDutyCalculationDTO().setTotalDisplay(
			 * myformatter.format(Double.parseDouble(duty))); // date and time//
			 * String currDate = bo.getCurrentDate();
			 * eForm.setCurrentDate(currDate); // deeds and instruments and
			 * purpose// ArrayList second = new ArrayList(); ArrayList comp1 =
			 * new ArrayList(); second =
			 * bo.getDeedDtls(eForm.getMainTxnId(),language); if (second.size()
			 * > 0) { for (int i = 0; i < second.size(); i++) {
			 * comp1.add((ArrayList) second.get(i)); if (comp1 != null &&
			 * comp1.size() > 0) { for (int k = 0; k < comp1.size(); k++) {
			 * ArrayList compSub = (ArrayList) comp1 .get(k); String deed =
			 * (String) compSub.get(0); String inst = (String) compSub.get(1);
			 * String purpose = (String) compSub .get(2); logger
			 * .debug("the deed is.........................." + deed); logger
			 * .debug("the inst is.........................." + inst); logger
			 * .debug("the purpose is.........................." + purpose);
			 * eForm.getDutyCalculationDTO() .setDeedType(deed);
			 * eForm.getInstDTO().setInstType(inst);
			 * eForm.setEstampPurpose(purpose); } } } } // names and office
			 * details// String roleId = bo.getRole(userId); String srrole =
			 * "GR1356609170484"; String drrole = "GR1356671177515"; String
			 * sprole = "GR1358488496205"; String rurole = "GR1357368369828";
			 * String spbankrole = "GR1359453019113";
			 * 
			 * if (roleId.equalsIgnoreCase(rurole)) { ArrayList userdet = new
			 * ArrayList(); ArrayList comp2 = new ArrayList(); userdet =
			 * bo.getruName(userId); if (userdet.size() > 0) { for (int i = 0; i
			 * < userdet.size(); i++) { comp2.add((ArrayList) userdet.get(i));
			 * if (comp2 != null && comp2.size() > 0) { for (int k = 0; k <
			 * comp2.size(); k++) { ArrayList compSub = (ArrayList) comp2
			 * .get(k);
			 * 
			 * String name = (String) compSub .get(0); String cid = (String)
			 * compSub .get(1); String sid = (String) compSub .get(2); String
			 * did = (String) compSub .get(3); String dname = (String) compSub
			 * .get(4);
			 * 
			 * eForm.setUserName(name); if (cid.equalsIgnoreCase("1")) { if
			 * (sid.equalsIgnoreCase("1")) { eForm.setIssuedPlace(dname);
			 * 
			 * } else { eForm .setIssuedPlace("Others"); }
			 * 
			 * } else { eForm.setIssuedPlace("Others"); }
			 * 
			 * } } } }
			 * 
			 * eForm.setOfficeName("-"); }
			 * 
			 * else if (roleId.equalsIgnoreCase(sprole)) { String spname =
			 * bo.getspName(userId); ArrayList spdtls = new ArrayList();
			 * ArrayList comp3 = new ArrayList(); spdtls = bo.getspDtls(userId);
			 * if (spdtls.size() > 0) { for (int i = 0; i < spdtls.size(); i++)
			 * { comp3.add((ArrayList) spdtls.get(i)); if (comp3 != null &&
			 * comp3.size() > 0) { for (int k = 0; k < comp3.size(); k++) {
			 * ArrayList compSub = (ArrayList) comp3 .get(k);
			 * 
			 * String ofc = (String) compSub .get(0); String plc = (String)
			 * compSub .get(1); eForm.setOfficeName(ofc);
			 * eForm.setIssuedPlace(plc); eForm.setUserName(spname); } } }
			 * 
			 * } }
			 * 
			 * else if (roleId.equalsIgnoreCase(spbankrole)) {
			 * 
			 * ArrayList spbankdtls = new ArrayList(); ArrayList comp4 = new
			 * ArrayList(); spbankdtls = bo.getspbnkDtls(userId); if
			 * (spbankdtls.size() > 0) { for (int i = 0; i < spbankdtls.size();
			 * i++) { comp4.add((ArrayList) spbankdtls.get(i)); if (comp4 !=
			 * null && comp4.size() > 0) { for (int k = 0; k < comp4.size();
			 * k++) { ArrayList compSub = (ArrayList) comp4 .get(k);
			 * 
			 * String uname = (String) compSub .get(0); String ofc = (String)
			 * compSub .get(1); String plc = (String) compSub .get(2);
			 * eForm.setOfficeName(ofc); eForm.setIssuedPlace(plc);
			 * eForm.setUserName(uname); } } }
			 * 
			 * } }
			 * 
			 * else { // for sr and dr //
			 * role...........................................
			 * 
			 * }
			 * 
			 * // party details--applicant// ArrayList appdtls = new
			 * ArrayList(); ArrayList comp5 = new ArrayList(); appdtls =
			 * bo.getAppDtls(eForm.getMainTxnId()); if (appdtls.size() > 0) {
			 * for (int i = 0; i < appdtls.size(); i++) { comp5.add((ArrayList)
			 * appdtls.get(i)); if (comp5 != null && comp5.size() > 0) { for
			 * (int k = 0; k < comp5.size(); k++) { ArrayList compSub =
			 * (ArrayList) comp5 .get(k);
			 * 
			 * String name = (String) compSub.get(0); String cntry = (String)
			 * compSub.get(1); String state = (String) compSub.get(2); String
			 * district = (String) compSub .get(3); String addrs = (String)
			 * compSub.get(4); String fathrNme = (String) compSub .get(5);
			 * String noOfPrsns = (String) compSub .get(6); String orgName =
			 * (String) compSub .get(7); String disId = (String) compSub.get(8);
			 * 
			 * if (orgName != null) {
			 * 
			 * eForm.setAppNamedsply(orgName); eForm.setAppCountryName(cntry);
			 * eForm.setAppStateName(state); eForm.setAppDistrictName(district);
			 * eForm.setAppAddress(addrs); eForm.setAppAuthPersonName(name);
			 * eForm.setAppPersons(noOfPrsns); eForm.setAppDistrict(disId);
			 * eForm.setIsAuthapp(1); } else { eForm.setAppNamedsply(name);
			 * eForm.setAppCountryName(cntry); eForm.setAppStateName(state);
			 * eForm.setAppDistrictName(district); eForm.setAppAddress(addrs);
			 * eForm.setAppFatherName(fathrNme); eForm.setAppPersons(noOfPrsns);
			 * eForm.setAppDistrict(disId);
			 * 
			 * }
			 * 
			 * } } }
			 * 
			 * }
			 * 
			 * // party details--party// ArrayList partydtls = new ArrayList();
			 * ArrayList comp6 = new ArrayList(); partydtls =
			 * bo.getPartyDtls(eForm.getMainTxnId()); if (partydtls.size() > 0)
			 * { for (int i = 0; i < partydtls.size(); i++) {
			 * comp6.add((ArrayList) partydtls.get(i)); if (comp6 != null &&
			 * comp6.size() > 0) { for (int k = 0; k < comp6.size(); k++) {
			 * ArrayList compSub = (ArrayList) comp6 .get(k);
			 * 
			 * String name = (String) compSub.get(0); String cntry = (String)
			 * compSub.get(1); String state = (String) compSub.get(2); String
			 * district = (String) compSub .get(3); String addrs = (String)
			 * compSub.get(4); String fathrNme = (String) compSub .get(5);
			 * String noOfPrsns = (String) compSub .get(6); String orgName =
			 * (String) compSub .get(7);
			 * 
			 * if (orgName != null) { eForm.setPartyNamedsply(orgName);
			 * eForm.setPartyCountryName(cntry); eForm.setPartyStateName(state);
			 * eForm .setPartyDistrictName(district);
			 * eForm.setPartyAddress(addrs); eForm.setPartyAuthPersonName(name);
			 * eForm.setPartyPersons(noOfPrsns); eForm.setIsAuthparty(1); } else
			 * { eForm.setPartyNamedsply(name);
			 * eForm.setPartyCountryName(cntry); eForm.setPartyStateName(state);
			 * eForm .setPartyDistrictName(district);
			 * eForm.setPartyAddress(addrs); eForm.setPartyFatherName(fathrNme);
			 * eForm.setPartyPersons(noOfPrsns); }
			 * 
			 * /* if (orgName.equalsIgnoreCase(null)){
			 * eForm.setPartyNamedsply(name); eForm.setPartyCountryName(cntry);
			 * eForm.setPartyStateName(state);
			 * eForm.setPartyDistrictName(district);
			 * eForm.setPartyAddress(addrs); eForm.setPartyFatherName(fathrNme);
			 * eForm.setPartyPersons(noOfPrsns); }else{
			 * eForm.setPartyNamedsply(orgName);
			 * eForm.setPartyCountryName(cntry); eForm.setPartyStateName(state);
			 * eForm.setPartyDistrictName(district);
			 * eForm.setPartyAddress(addrs); eForm.setPartyAuthPersonName(name);
			 * eForm.setPartyPersons(noOfPrsns); eForm.setIsAuthparty(1);
			 * 
			 * }
			 */

			/*
			 * } }
			 * 
			 * 
			 * } else { eForm.setPartyNamedsply("-");
			 * eForm.setPartyCountryName("-"); eForm.setPartyStateName("-");
			 * eForm.setPartyDistrictName("-"); eForm.setPartyAddress("-");
			 * eForm.setPartyFatherName("-"); eForm.setPartyPersons("-");
			 * 
			 * } // generation of e-code and insertion into the tables. try {
			 * boolean boo = false; boo = bo.inserteCode(eForm,
			 * objDashBoardDTO1);
			 * 
			 * if (boo) {
			 * 
			 * ArrayList ecodedtls = new ArrayList(); ArrayList comp7 = new
			 * ArrayList(); ecodedtls = bo.getEcodeDtls(eForm .getMainTxnId());
			 * if (ecodedtls.size() > 0) { for (int i = 0; i < ecodedtls.size();
			 * i++) { comp7.add((ArrayList) ecodedtls.get(i)); if (comp7 != null
			 * && comp7.size() > 0) { for (int k = 0; k < comp7.size(); k++) {
			 * ArrayList compSub = (ArrayList) comp7 .get(k);
			 * 
			 * String ecode = (String) compSub .get(0); String amt = (String)
			 * compSub .get(1); String estampType = (String) compSub .get(2);
			 * String estampDate = (String) compSub .get(3); String issuedBy =
			 * (String) compSub .get(4); String offc = (String) compSub .get(5);
			 * String place = (String) compSub .get(6); String validityDt =
			 * (String) compSub .get(7); eForm.setEcode(ecode);
			 * eForm.getDutyCalculationDTO() .setTotalDisplay(amt);
			 * eForm.setEstampType(estampType); /* SimpleDateFormat sdf1 = new
			 * SimpleDateFormat ("yyyy-MM-dd- KK:mm:ss"); SimpleDateFormat sdf2
			 * = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a"); logger
			 * .debug("the date is-----" +estampDate); Date d1 =
			 * sdf1.parse(estampDate); String transDate = sdf2.format(d1);
			 */
			/*
			 * eForm .setCurrentDate(estampDate); eForm.setUserName(issuedBy);
			 * eForm.setOfficeName(offc); eForm.setIssuedPlace(place); eForm
			 * .setEstampValidity(validityDt); logger
			 * .debug("Just before Final page redirection"); forwardJsp =
			 * "ecodePage";
			 * 
			 * } } }
			 * 
			 * } } else { forwardJsp = "failure"; }
			 * 
			 * } catch (Exception e) {
			 * 
			 * }
			 * 
			 * }// end of second condition } }
			 */
			// end of payment status after payment
			// For E-Stamp Search Screen for view for Registered User
			if (request.getParameter("modName") != null) {
				if (request.getParameter("modName").equalsIgnoreCase("E-StampsView")) {
					logger.debug("------------------->inside search action");
					
					eForm.getObjDashBoardDTO().setCheck("");
					eForm.getObjDashBoardDTO().setEcode("");
					
					forwardJsp = CommonConstant.FORWARD_PAGE_SEARCH;
					logger.info("--------------->showing search screen");
					
					return mapping.findForward(forwardJsp);
				}
			}
			
			// ---- for reset on search screen of view page for Registered User
			// ---
			
			if ("RESET_ACTION".equalsIgnoreCase(actionName)) {
				
				logger.debug("----------------> inside reset action");
				
				// eForm.getObjDashBoardDTO().setEcode("");
				eForm.getObjDashBoardDTO().setCheck("");
				
				forwardJsp = CommonConstant.FORWARD_PAGE_SEARCH;
				
				return mapping.findForward(forwardJsp);
			}
			
			if ("RESET_ACTION_DEACTIVATE".equalsIgnoreCase(actionName)) {
				
				logger.debug("----------------> inside reset action");
				
				// eForm.getObjDashBoardDTO().setEcode("");
				eForm.getObjDashBoardDTO().setCheck("");
				
				forwardJsp = CommonConstant.FORWARD_PAGE_DEACTIVATE_SEARCH;
				
				return mapping.findForward(forwardJsp);
			}
			// ---- for search action on search screen of view page for
			// Registered User ---
			
			if ("SEARCH_ACTION".equalsIgnoreCase(actionName)) {
				
				eForm.getObjDashBoardDTO().setCheck("");
				
				ArrayList ecodeId;
				String ecode = eForm.getObjDashBoardDTO().getEcode();
				String moduleFlag = bo.moduleFlag(ecode);
				logger.debug("--------------->" + ecode);
				ecodeId = objEstampBO.checkEcode(ecode);
				
				logger.debug("----------------> inside search action");
				
				if (ecodeId.size() == 0) {
					eForm.getObjDashBoardDTO().setCheck("Y");
					logger.debug("----------------> no ecode obtained from database");
					forwardJsp = CommonConstant.FORWARD_PAGE_SEARCH;
					logger.debug("-------------->" + forwardJsp);
				} else {
					ArrayList ecodeDetails = objEstampBO.viewEcodeDetails(ecode, language);
					logger.debug("----------------> ecode obtained");
					if (ecodeDetails.size() == 0)
						eForm.getObjDashBoardDTO().setViewRUEcodeDetails(null);
					else
						eForm.getObjDashBoardDTO().setViewRUEcodeDetails(ecodeDetails);
					
					request.setAttribute("ecodeDetails", eForm.getObjDashBoardDTO().getViewRUEcodeDetails());
					logger.debug("----------------> ecodeDetails obtained");
					
					String ecodeTypeID = ecode.substring(3, 4);
					
					ArrayList ecodeType = objEstampBO.viewEcodeType(ecodeTypeID, language);
					
					if (ecodeType.size() == 0)
						eForm.getObjDashBoardDTO().setViewRUEcodeType(null);
					else
						eForm.getObjDashBoardDTO().setViewRUEcodeType(ecodeType);
					
					request.setAttribute("ecodeType", eForm.getObjDashBoardDTO().getViewRUEcodeType());
					eForm.setEstampTypeCheck(bo.getEstampCodeType(ecode));
					
					forwardJsp = CommonConstant.FORWARD_PAGE_ECODEVIEW;
					logger.debug("----------------> ecodeType obtained");
				}
				eForm.getObjDashBoardDTO().setEcode("");
				// return mapping.findForward(forwardJsp);
				
			}
			
			// For E-Stamp Search Screen for view for DRS Official
			
			if (request.getParameter("modName") != null) {
				if (request.getParameter("modName").equalsIgnoreCase("E-StampsViewDRS")) {
					
					logger.debug("------------------->inside search action");
					
					eForm.getObjDashBoardDTO().setCheck("");
					eForm.getObjDashBoardDTO().setEcode("");
					
					forwardJsp = CommonConstant.FORWARD_PAGE_SEARCH_DRS;
					logger.info("--------------->showing search screen");
					
					return mapping.findForward(forwardJsp);
				}
			}
			
			// ---- for search action on search screen of view page for DRS
			// Official ---
			
			if ("SEARCH_ACTION_DRS".equalsIgnoreCase(actionName)) {
				
				eForm.getObjDashBoardDTO().setCheck("");
				
				ArrayList estampCode;
				String estampCodeId = eForm.getObjDashBoardDTO().getEcode();
				String moduleFlag = bo.moduleFlag(estampCodeId);
				logger.debug("--------------->" + estampCodeId);
				estampCode = objEstampBO.checkEcode(estampCodeId);
				
				logger.debug("----------------> inside search action");
				
				if (estampCode.size() == 0) {
					eForm.getObjDashBoardDTO().setCheck("Y");
					logger.debug("----------------> no ecode obtained from database");
					forwardJsp = CommonConstant.FORWARD_PAGE_SEARCH_DRS;
					logger.debug("-------------->" + forwardJsp);
				} else {/* ARY */
					String estampid = estampCodeId.substring(3, 4);
					ArrayList ecodeDetailsDRS = new ArrayList();
					if (estampid.equalsIgnoreCase("1")) {
						ecodeDetailsDRS = objEstampBO.viewEcodeDetailsNJ_DRS(estampCodeId, language);
						logger.debug("----------------> ecode obtained");
					} else if (estampid.equalsIgnoreCase("2")) {
						ecodeDetailsDRS = objEstampBO.viewEcodeDetailsDRS(estampCodeId, language);
						logger.debug("----------------> ecode obtained");
					}
					
					/*
					 * ArrayList ecodeDetailsDRS = objEstampBO
					 * .viewEcodeDetailsDRS(estampCodeId, language);
					 */
					logger.debug("----------------> ecode obtained");
					if (ecodeDetailsDRS.size() == 0)
						eForm.getObjDashBoardDTO().setViewDRSEcodeDetails(null);
					else
						eForm.getObjDashBoardDTO().setViewDRSEcodeDetails(ecodeDetailsDRS);
					
					request.setAttribute("ecodeDetails", eForm.getObjDashBoardDTO().getViewDRSEcodeDetails());
					logger.debug("----------------> ecodeDetails obtained");
					
					String ecodeTypeID = estampCodeId.substring(3, 4);
					
					ArrayList ecodeType = objEstampBO.viewEcodeType(ecodeTypeID, language);
					
					if (ecodeType.size() == 0)
						eForm.getObjDashBoardDTO().setViewRUEcodeType(null);
					else
						eForm.getObjDashBoardDTO().setViewRUEcodeType(ecodeType);
					
					request.setAttribute("ecodeType", eForm.getObjDashBoardDTO().getViewRUEcodeType());
					
					ArrayList viewApplicantDetailsDRS = objEstampBO.getDetailsOfApplicantDRS(estampCodeId, language);
					logger.info("--------------->" + viewApplicantDetailsDRS.size());
					
					if (viewApplicantDetailsDRS.size() == 0)
						eForm.getObjDashBoardDTO().setPartyDetailsDRS(null);
					else
						eForm.getObjDashBoardDTO().setPartyDetailsDRS(viewApplicantDetailsDRS);
					
					request.setAttribute("applicantDetails", eForm.getObjDashBoardDTO().getPartyDetailsDRS());
					
					for (int i = 0; i < viewApplicantDetailsDRS.size(); i++) {
						DashBoardDTO ddto = (DashBoardDTO) viewApplicantDetailsDRS.get(i);
						
						logger.info("--------------->" + ddto.getAppStatus());
						logger.info("--------------->" + ddto.getAppType());
						logger.info("--------------->" + ddto.getPartyType());
						logger.info("--------------->" + ddto.getAppAuthFirstName());
						logger.info("--------------->" + ddto.getPartyAuthFirstName());
					}
					
					if ("R".equalsIgnoreCase(moduleFlag)) {
						String txnId = bo.getRegId(estampCodeId);
						bo.getDutyDetailsInitiation(txnId, eForm, "I");
						eForm.setBreifDocument("NA");
					} else if ("C".equalsIgnoreCase(moduleFlag)) {
						String txnId = bo.getRegId(estampCodeId);
						bo.getDutyDetailsInitiation(txnId, eForm, "C");
						eForm.setBreifDocument("NA");
					} else {
						eForm.setBreifDocument(bo.getBreifDocument(estampCodeId));
						bo.getDutyDetails(estampCodeId, eForm);
					}
					eForm.setEstampTypeCheck(bo.getEstampCodeType(estampCodeId));
					forwardJsp = CommonConstant.FORWARD_PAGE_ECODEVIEW_DRS;
					logger.debug("----------------> ecodeType obtained");
				}
				// return mapping.findForward(forwardJsp);
				eForm.getObjDashBoardDTO().setEcode("");
			}
			
			// for the search screen of Deactivation request by DR
			
			if (request.getParameter("modName") != null) {
				if (request.getParameter("modName").equalsIgnoreCase("E-StampDeactivate")) {
					
					eForm.getObjDashBoardDTO().setCheck("");
					eForm.getObjDashBoardDTO().setCheckDeact("");
					eForm.getObjDashBoardDTO().setCheckExpiry("");
					eForm.getObjDashBoardDTO().setCheckDeactv("");
					eForm.getObjDashBoardDTO().setCheckConsumd("");
					eForm.getObjDashBoardDTO().setEcode("");
					
					logger.debug("------------------->inside search action");
					
					forwardJsp = CommonConstant.FORWARD_PAGE_DEACTIVATE_SEARCH;
					logger.info("--------------->showing search screen");
					
					return mapping.findForward(forwardJsp);
				}
			}
			
			// ---- for search action on search screen of deactivation of
			// E-stamps by DR ---
			
			if ("SEARCH_ACTION_DEACTIVATE".equalsIgnoreCase(actionName)) {
				
				eForm.getObjDashBoardDTO().setPersFatherName("");
				eForm.getObjDashBoardDTO().setPersFatherName("");
				eForm.getObjDashBoardDTO().setPersMotherName("");
				eForm.getObjDashBoardDTO().setPersAddress("");
				eForm.getObjDashBoardDTO().setPersPhnNo("");
				eForm.getObjDashBoardDTO().setPersMobNo("");
				
				ArrayList estampCode;
				String estampCodeId = eForm.getObjDashBoardDTO().getEcode();
				
				String checkUser = objEstampBO.checkUser(estampCodeId, userId);
				
				if (checkUser.equalsIgnoreCase("1")) {
					objDashBoardDTO1.setEcode(estampCodeId);
					String amount = objEstampBO.getAmount(estampCodeId);
					double am = 0;
					if (amount != null && !amount.equalsIgnoreCase("")) {
						am = Double.parseDouble(amount);
					}
					logger.debug("--------------->" + estampCodeId);
					estampCode = objEstampBO.checkEcode(estampCodeId);
					
					ArrayList estampCodeDeact = objEstampBO.checkEcodeDeact(estampCodeId);
					ArrayList estampCodeExp = objEstampBO.checkEcodeExp(estampCodeId);
					ArrayList estampCodeDeactv = objEstampBO.checkEcodeDeactv(estampCodeId);
					ArrayList estampCodeConsumd = objEstampBO.checkEcodeConsumed(estampCodeId);
					
					logger.debug("----------------> inside search action");
					/*
					 * Added by Gulrej ************** adds Refund Request no in
					 * View Screen *** Start
					 */

					ArrayList refundRequestDtls = (new EstampingBD()).getRefundRequestDtls(estampCodeId);
					
					if (refundRequestDtls != null && refundRequestDtls.size() > 0) {
						ArrayList refundRequestDtlsList = new ArrayList();
						for (int i = 0; i < refundRequestDtls.size(); i++) {
							refundRequestDtlsList = (ArrayList) refundRequestDtls.get(i);
							eForm.getObjDashBoardDTO().setRefundRequestNo(refundRequestDtlsList.get(0).toString());
						}
					} else {
						eForm.getObjDashBoardDTO().setRefundRequestNo("NA");
					}
					
					/*
					 * Added by Gulrej ************** adds Refund Request no in
					 * View Screen **** End
					 */

					if (estampCode.size() == 0) {
						eForm.getObjDashBoardDTO().setCheck("Y");
						logger.debug("----------------> no ecode obtained from database");
						forwardJsp = CommonConstant.FORWARD_PAGE_DEACTIVATE_SEARCH;
						logger.debug("-------------->" + forwardJsp);
					}

					else if (estampCodeDeact.size() != 0) {
						eForm.getObjDashBoardDTO().setCheckDeact("Y");
						logger.debug("----------------> deactivation request already generated");
						forwardJsp = CommonConstant.FORWARD_PAGE_DEACTIVATE_SEARCH;
						logger.debug("-------------->" + forwardJsp);
					}

					else if (estampCodeExp.size() != 0) {
						eForm.getObjDashBoardDTO().setCheckExpiry("Y");
						logger.debug("----------------> already expired");
						forwardJsp = CommonConstant.FORWARD_PAGE_DEACTIVATE_SEARCH;
						logger.debug("-------------->" + forwardJsp);
					}

					else if (estampCodeDeactv.size() != 0) {
						eForm.getObjDashBoardDTO().setCheckDeactv("Y");
						logger.debug("----------------> already deactivated");
						forwardJsp = CommonConstant.FORWARD_PAGE_DEACTIVATE_SEARCH;
						logger.debug("-------------->" + forwardJsp);
					} else if (estampCodeConsumd.size() != 0) {
						eForm.getObjDashBoardDTO().setCheckConsumd("Y");
						logger.debug("----------------> already consumed");
						forwardJsp = CommonConstant.FORWARD_PAGE_DEACTIVATE_SEARCH;
						logger.debug("-------------->" + forwardJsp);
					} else if (am <= 0) {
						eForm.getObjDashBoardDTO().setZeroAmountFlag("Y");
						logger.debug("----------------> zero amount");
						forwardJsp = CommonConstant.FORWARD_PAGE_DEACTIVATE_SEARCH;
						logger.debug("-------------->" + forwardJsp);
					} else {
						String estampid = estampCodeId.substring(3, 4);
						if (estampid.equalsIgnoreCase("1")) {
							ArrayList ecodeDeactDetailsDR = objEstampBO.deactEcodeDetails(estampCodeId, objDashBoardDTO1, language);
							logger.debug("----------------> ecode obtained");
							if (ecodeDeactDetailsDR.size() == 0)
								eForm.getObjDashBoardDTO().setEcodeDeactDetails(null);
							else
								eForm.getObjDashBoardDTO().setEcodeDeactDetails(ecodeDeactDetailsDR);
							
							request.setAttribute("ecodeDeactDetails", eForm.getObjDashBoardDTO().getEcodeDeactDetails());
							logger.debug("----------------> ecodeDeactDetails obtained");
						} else if (estampid.equalsIgnoreCase("2")) {
							ArrayList ecodeDeactDetailsDR = objEstampBO.deactDocTypeJud(estampCodeId, objDashBoardDTO1, language);
							logger.debug("----------------> ecode obtained");
							if (ecodeDeactDetailsDR.size() == 0)
								eForm.getObjDashBoardDTO().setEcodeDeactDetails(null);
							else
								eForm.getObjDashBoardDTO().setEcodeDeactDetails(ecodeDeactDetailsDR);
							
							request.setAttribute("ecodeDeactDetails", eForm.getObjDashBoardDTO().getEcodeDeactDetails());
							logger.debug("----------------> ecodeDeactDetails obtained");
						}
						// String ecodeTypeID = estampCodeId.substring(3, 4);
						
						ArrayList deactDrName = objEstampBO.deactDRName(session.getAttribute("UserId").toString(), objDashBoardDTO1);
						
						if (deactDrName.size() == 0)
							eForm.getObjDashBoardDTO().setEcodeDeactDRName(null);
						else
							eForm.getObjDashBoardDTO().setEcodeDeactDRName(deactDrName);
						
						request.setAttribute("ecodeDeactDRName", eForm.getObjDashBoardDTO().getEcodeDeactDRName());
						logger.debug("----------------> ecodeDeactDRName obtained");
						
						ArrayList ecodeDate = objEstampBO.deactEcodeDate();
						
						if (ecodeDate.size() == 0)
							eForm.getObjDashBoardDTO().setEcodeDeactDate(null);
						else
							eForm.getObjDashBoardDTO().setEcodeDeactDate(ecodeDate);
						
						request.setAttribute("ecodeDeactDate", eForm.getObjDashBoardDTO().getEcodeDeactDate());
						logger.debug("----------------> ecodeDeactDate obtained");
						
						ArrayList ecodeDeactAppName = objEstampBO.deactEcodeAppDetails(estampCodeId);
						logger.debug(ecodeDeactAppName);
						if (ecodeDeactAppName.size() == 0)
							eForm.getObjDashBoardDTO().setEcodeDeactAppDetails(null);
						else
							eForm.getObjDashBoardDTO().setEcodeDeactAppDetails(ecodeDeactAppName);
						
						request.setAttribute("ecodeDeactAppDetails", eForm.getObjDashBoardDTO().getEcodeDeactAppDetails());
						
						logger.debug("----------------> ecodeDeactAppDetails obtained");
						
						ArrayList ecodeDeactPartyName = objEstampBO.deactEcodePartyDetails(estampCodeId);
						logger.debug(ecodeDeactPartyName);
						if (ecodeDeactPartyName.size() == 0)
							eForm.getObjDashBoardDTO().setEcodeDeactPartyDetails(null);
						else
							eForm.getObjDashBoardDTO().setEcodeDeactPartyDetails(ecodeDeactPartyName);
						
						request.setAttribute("ecodeDeactAppDetails", eForm.getObjDashBoardDTO().getEcodeDeactPartyDetails());
						logger.debug("----------------> ecodeDeactPartyDetails obtained");
						String moduleFlag = bo.moduleFlag(estampCodeId);
						if ("R".equalsIgnoreCase(moduleFlag)) {
							eForm.setBreifDocument("NA");
							String txnId = bo.getRegId(estampCodeId);
							bo.getDutyDetailsInitiation(txnId, eForm, "I");
						} else if ("C".equalsIgnoreCase(moduleFlag)) {
							eForm.setBreifDocument("NA");
							String txnId = bo.getRegId(estampCodeId);
							bo.getDutyDetailsInitiation(txnId, eForm, "C");
						} else {
							eForm.setBreifDocument(bo.getBreifDocument(estampCodeId));
							bo.getDutyDetails(estampCodeId, eForm);
						}
						eForm.setEstampTypeCheck(bo.getEstampCodeType(estampCodeId));
						forwardJsp = CommonConstant.FORWARD_PAGE_DEACTIVATE;
						logger.debug("----------------> ecodeDeactView obtained");
					}
				} else {
					eForm.getObjDashBoardDTO().setEcode("");
					if (language.equalsIgnoreCase("en"))
						request.setAttribute("failureMsg", "User is not authorized to view this E-stamp.");
					else
						request.setAttribute("failureMsg", "उपयोगकर्ता इस ई-स्टांप देखने के लिए अधिकृत नहीं है !");
					forwardJsp = "searchDeactivate";
					
				}
				// return mapping.findForward(forwardJsp);
				// eForm.getObjDashBoardDTO().setEcode("");
			}
			
			if ("DEACTIVATE".equals(actionName)) {
				boolean deactDetails;
				deactDetails = objEstampBO.insertDeactDetails(eForm, objDashBoardDTO1, userId);
				
				boolean txn = false;
				logger.debug("----> ecode " + objDashBoardDTO1.getEcode());
				txn = bo.insertConsumeExp(eForm, objDashBoardDTO1);
				logger.debug("inside deactivate consume insert action-after insert");
				
				ArrayList deactRequestId = objEstampBO.deactRequestId(objDashBoardDTO1);
				logger.debug("----------------> deactRequestId obtained");
				if (deactRequestId.size() == 0)
					eForm.getObjDashBoardDTO().setDeactRequestNo(null);
				else
					eForm.getObjDashBoardDTO().setDeactRequestNo(deactRequestId);
				
				request.setAttribute("ecodeDeactDetails", eForm.getObjDashBoardDTO().getDeactRequestNo());
				
				logger.debug("----------------> deactRequestNo obtained");
				
				forwardJsp = CommonConstant.FORWARD_PAGE_DEACT_SUCCESS;
				
				logger.debug("----------------> deactRequestNoSuccess obtained");
				
			}// end form!=null
			
			if ("RESET_ACTION_DEACTIVATE".equalsIgnoreCase(actionName)) {
				
				logger.debug("----------------> inside reset action");
				
				eForm.getObjDashBoardDTO().setPersName("");
				eForm.getObjDashBoardDTO().setPersFatherName("");
				eForm.getObjDashBoardDTO().setPersMotherName("");
				eForm.getObjDashBoardDTO().setPersAddress("");
				eForm.getObjDashBoardDTO().setPersMobNo("");
				eForm.getObjDashBoardDTO().setPersPhnNo("");
				
				forwardJsp = CommonConstant.FORWARD_PAGE_DEACTIVATE;
				
				return mapping.findForward(forwardJsp);
			}
			
			if ("GENERATE_OTP".equalsIgnoreCase(actionName)) {
				DecimalFormat myformatter = new DecimalFormat("############");
				eForm.setStampDisplay(myformatter.format(Double.parseDouble(eForm.getStamp())));
				eForm.setMuncipalityDIspaly(myformatter.format(Double.parseDouble(eForm.getMuncipality())));
				eForm.setJanpadDisplay(myformatter.format(Double.parseDouble(eForm.getJanpad())));
				eForm.setUpkarDisplay(myformatter.format(Double.parseDouble(eForm.getUpkar())));
				eForm.setExemptedAmountDisplay(myformatter.format(Double.parseDouble(eForm.getExemptedAmount())));
				OTPUtility otp = new OTPUtility();
				boolean flag = otp.generateOTP(userId, eForm.getEcode().toString(), "ACT_204");
				// if(flag)
				// {
				forwardJsp = "ecodePageForZeroBal";
				// }
			}
			if ("VALIDATE_OTP".equalsIgnoreCase(actionName)) {
				DecimalFormat myformatter = new DecimalFormat("############");
				eForm.setStampDisplay(myformatter.format(Double.parseDouble(eForm.getStamp())));
				eForm.setMuncipalityDIspaly(myformatter.format(Double.parseDouble(eForm.getMuncipality())));
				eForm.setJanpadDisplay(myformatter.format(Double.parseDouble(eForm.getJanpad())));
				eForm.setUpkarDisplay(myformatter.format(Double.parseDouble(eForm.getUpkar())));
				eForm.setExemptedAmountDisplay(myformatter.format(Double.parseDouble(eForm.getExemptedAmount())));
				OTPUtility otp = new OTPUtility();
				boolean flag = otp.validateOTP(eForm.getEcode().toString(), "ACT_204", userId, eForm.getOtp());
				if (flag) {
					eForm.setAllowPrint("Y");
					eForm.setPrintFlag("N");
					forwardJsp = "ecodePageForZeroBal";
				} else {
					eForm.setPrintFlag("Y");
				}
				forwardJsp = "ecodePageForZeroBal";
			}
			if ("COMPLETE_TXN_FOR_ZERO".equalsIgnoreCase(actionName)) {
				
				logger.debug("ESTAMP ID BEFORE PDF------>" + eForm.getMainTxnId());
				// For the pdf creation of E-stamp certificate.
				
				String estampGenCert = objEstampBO.getCertChkDetails(eForm.getMainTxnId().toString());
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				DocumentOperations docOperations = new DocumentOperations();
				ODServerDetails connDetails = new ODServerDetails();
				Dataclass metaDataInfo = new Dataclass();
				connDetails.setAppServerIp(pr.getValue("AppServerIp"));
				connDetails.setCabinetName(pr.getValue("CabinetName"));
				connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
				connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
				connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
				connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
				connDetails.setIniPath("D:\\simran\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
				metaDataInfo.setUniqueNo(eForm.getEcode().toString());
				metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
				if (estampGenCert.equalsIgnoreCase("Y")) // Certificate already
				// generated
				// .....code to
				// download that
				{
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(CommonConstant.FAILURE_MSG, "The Estamp has already been printed");
					else
						request.setAttribute(CommonConstant.FAILURE_MSG, "ई-स्टाम्प पहले से ही उत्पन्न किया जा चुका है।");
					eForm.setOwmFlag(null);
					eForm.setOwmFile(null);
					forwardJsp = "ecodePageForZeroBal";
					
					/*
					 * DecimalFormat myformatter = new DecimalFormat(
					 * "############");
					 * eForm.setStampDisplay(myformatter.format(
					 * Double.parseDouble(eForm.getStamp())));
					 * eForm.setMuncipalityDIspaly
					 * (myformatter.format(Double.parseDouble
					 * (eForm.getMuncipality())));
					 * eForm.setJanpadDisplay(myformatter
					 * .format(Double.parseDouble(eForm.getJanpad())));
					 * eForm.setUpkarDisplay
					 * (myformatter.format(Double.parseDouble
					 * (eForm.getUpkar())));
					 * eForm.setExemptedAmountDisplay(myformatter
					 * .format(Double.parseDouble(eForm.getExemptedAmount())));
					 * String guid=GUIDGenerator.getGUID(); //String EstampPath
					 * = CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid; String
					 * EstampPath = downloadPath+File.separator+guid; File
					 * output; output = new File(EstampPath.toString()); if
					 * (output.exists()) {
					 * logger.info("file already exists deleting....");
					 * output.delete(); } if (output.getParentFile().exists() ==
					 * false) {
					 * logger.info("Parent not found creating directory....");
					 * output.getParentFile().mkdirs(); } int result =
					 * docOperations
					 * .downloadDocument(connDetails,metaDataInfo,EstampPath
					 * ,CommonConstant.MODULE_NAME);
					 * logger.debug("download result---------->"+result);
					 * eForm.setOwmFlag("Y");
					 * eForm.setOwmFile(EstampPath+File.separator+"EStamp.PDF");
					 * forwardJsp="ecodePageForZeroBal"; String filePath =
					 * EstampPath+File.separator+"EStamp.PDF"; // below code to
					 * view the doc // set the http content type to
					 * "APPLICATION/OCTET-STREAM
					 * response.setContentType("application/download");
					 * 
					 * 
					 * response.setHeader("Content-Disposition",
					 * "attachment; filename=" +
					 * URLEncoder.encode(filePath,"UTF-8"));
					 * 
					 * logger.debug("path beforre opening :"+filePath); try{
					 * File fileToDownload = new File(filePath); FileInputStream
					 * fileInputStream = new FileInputStream(fileToDownload);
					 * OutputStream out = response.getOutputStream(); byte[] buf
					 * = new byte[2048];
					 * 
					 * int readNum; while
					 * ((readNum=fileInputStream.read(buf))!=-1) {
					 * out.write(buf,0,readNum); } fileInputStream.close();
					 * out.close(); } catch(Exception e) { logger.debug(e); }
					 */} else {
					// ByteArrayOutputStream baos = new ByteArrayOutputStream();
					// added by vinay newgen
					String EstampPath = downloadPath + File.separator + eForm.getEcode().toString() + File.separator + "EStamp.PDF";
					File output;
					output = new File(EstampPath.toString());
					if (output.exists()) {
						logger.info("file already exists deleting....");
						output.delete();
					}
					if (output.getParentFile().exists() == false) {
						logger.info("Parent not found creating directory....");
						output.getParentFile().mkdirs();
					}
					EStamp estampObj = new EStamp();
					ArrayList<EStampPartyDetails> eStampPartyList = new ArrayList();
					estampObj.seteStampCode(eForm.getEcode());
					DecimalFormat myformatter = new DecimalFormat("############");
					eForm.setStampDisplay(myformatter.format(Double.parseDouble(eForm.getStamp())));
					eForm.setMuncipalityDIspaly(myformatter.format(Double.parseDouble(eForm.getMuncipality())));
					eForm.setJanpadDisplay(myformatter.format(Double.parseDouble(eForm.getJanpad())));
					eForm.setUpkarDisplay(myformatter.format(Double.parseDouble(eForm.getUpkar())));
					eForm.setExemptedAmountDisplay(myformatter.format(Double.parseDouble(eForm.getExemptedAmount())));
					// estampObj.seteStampAmount("216250");
					String govtStamp = objEstampBO.getGovtStamp(eForm.getEcode());
					// estampObj.setGovtStampDuty(eForm.getStampDisplay());
					estampObj.setGovtStampDuty(govtStamp);
					estampObj.setMunicipalityDuty(eForm.getMuncipalityDIspaly());
					estampObj.setJanpadDuty(eForm.getJanpadDisplay());
					estampObj.setExemptedAmount(eForm.getExemptedAmountDisplay());
					estampObj.setUpkarAmount(eForm.getUpkarDisplay());
					estampObj.seteStampType(eForm.getEstampType());
					estampObj.setIssuedDate(eForm.getCurrentDate());
					estampObj.setUserId(eForm.getUserName());
					estampObj.setOffice(eForm.getOfficeName());
					estampObj.setPlace(eForm.getIssuedPlace());
					estampObj.setMainDeedDocPath("E:" + File.separator + "upload" + File.separator + "IGRS" + File.separator + "MP022409062014000003" + File.separator + "EStamp.PDF");
					// estampObj.setValidity(eForm.getEstampValidity());
					estampObj.setDeedType((eForm.getDutyCalculationDTO().getDeedType()));
					estampObj.setDeedInstrument(eForm.getDutyCalculationDTO().getInstDescription());
					estampObj.setDeedPurpose(eForm.getEstampPurpose());
					// estampObj.setDeedDuration(eForm.getDeedDuration());
					estampObj.seteStampAmount(eForm.getDutyCalculationDTO().getTotalDisplay());
					EStampPartyDetails party1 = new EStampPartyDetails();
					String[] fName = objEstampBO.getFatherName(eForm.getMainTxnId().toString());
					if (fName[0].equalsIgnoreCase("-"))
						eForm.setAppFatherName(null);
					if (fName[1].equalsIgnoreCase("-"))
						eForm.setPartyFatherName(null);
					party1.setName(eForm.getAppNamedsply());
					party1.setCountry(eForm.getAppCountryName());
					party1.setState(eForm.getAppStateName());
					party1.setDistrict(eForm.getAppDistrictName());
					party1.setAddress(eForm.getAppAddress() + " " + eForm.getAppDistrictName() + " " + eForm.getAppStateName() + " " + eForm.getAppCountryName());
					// eForm.setAppFatherName("prashant");
					if (eForm.getAppFatherName() == null || eForm.getAppFatherName().toString().equalsIgnoreCase("")) {
						party1.setAuthorizerName(eForm.getAppAuthPersonName().toString());
						party1.setPartyType("1");
						
					} else {
						party1.setAuthorizerName(eForm.getAppFatherName().toString());
						if ("en".equalsIgnoreCase(language)) {
							party1.setName(eForm.getAppNamedsply() + " S/O D/O W/O C/O " + eForm.getAppFatherName().toString());
						} else {
							party1.setName(eForm.getAppNamedsply() + " पुत्र/पुत्री/पत्नी/द्वारा  " + eForm.getAppFatherName().toString());
						}
						party1.setPartyType("2");
						
					}
					party1.setNoOfPerson(eForm.getAppPersons());
					eStampPartyList.add(party1);
					
					EStampPartyDetails party2 = new EStampPartyDetails();
					party2.setName(eForm.getPartyNamedsply());
					party2.setCountry(eForm.getPartyCountryName());
					party2.setState(eForm.getPartyStateName());
					party2.setDistrict(eForm.getPartyDistrictName());
					party2.setAddress(eForm.getPartyAddress() + " " + eForm.getPartyDistrictName() + " " + eForm.getPartyStateName() + " " + eForm.getPartyCountryName());
					// eForm.setPartyFatherName("deepak");
					if (eForm.getPartyFatherName() == null || eForm.getPartyFatherName().toString().equalsIgnoreCase("")) {
						party2.setAuthorizerName(eForm.getPartyAuthPersonName());
						party2.setPartyType("1");
						
					} else {
						party2.setAuthorizerName(eForm.getPartyFatherName());
						if ("en".equalsIgnoreCase(language)) {
							party2.setName(eForm.getPartyNamedsply() + " S/O D/O W/O C/O " + eForm.getPartyFatherName().toString());
						} else {
							party2.setName(eForm.getPartyNamedsply() + " पुत्र/पुत्री/पत्नी/द्वारा  " + eForm.getPartyFatherName().toString());
						}
						party2.setPartyType("2");
						
					}
					party2.setNoOfPerson(eForm.getPartyPersons());
					eStampPartyList.add(party2);
					if ("Y".equalsIgnoreCase(eForm.getIsAdoption())) {
						EStampPartyDetails party3 = new EStampPartyDetails();
						party3.setName(eForm.getAdoptNameDisplay());
						party3.setCountry(eForm.getAdoptCountryName());
						party3.setState(eForm.getAdoptStateName());
						party3.setDistrict(eForm.getAdoptDistrictName());
						party3.setAddress(eForm.getAdoptAddress() + " " + eForm.getAdoptDistrictName() + " " + eForm.getAdoptStateName() + " " + eForm.getAdoptCountryName());
						party3.setAuthorizerName(eForm.getAdoptFatherName());
						party3.setPartyType("2");
						if ("en".equalsIgnoreCase(language)) {
							party3.setName(eForm.getAdoptNameDisplay() + " S/O D/O W/O C/O " + eForm.getAdoptFatherName().toString());
						} else {
							party3.setName(eForm.getAdoptNameDisplay() + " पुत्र/पुत्री/पत्नी/द्वारा  " + eForm.getAdoptFatherName().toString());
						}
						
						party3.setNoOfPerson(eForm.getAdoptNoPerson());
						eStampPartyList.add(party3);
					}
					estampObj.setPartyList(eStampPartyList);
					int resultStamp = -1;
					PropertiesFileReader pr1 = PropertiesFileReader.getInstance("resources.igrs");
					
					String hindiFont = pr1.getValue("dms_hindi_font_path");
					String englishFont = pr1.getValue("dms_english_font_path");
					SealsBD sealBD = new SealsBD();
					boolean val = sealBD.validateFont(hindiFont, englishFont);
					if (!val) {
						throw new Exception();
					}
					BurningImageAndText burnObj = new BurningImageAndText(hindiFont, englishFont);
					String outputPath = downloadPath + File.separator + eForm.getEcode().toString();
					String var = getServlet().getServletContext().getRealPath("") + "/images/header img.jpg";
					String dmsFolderName = "IGRS";
					
					String docPath = objEstampBO.getEstampDocDetails(eForm.getMainTxnId().toString());
					String deedContent = bo.getDeedContent(eForm.getMainTxnId().toString(), "E");
					estampObj.setDeedContent(deedContent);
					estampObj.setMainDeedDocPath(docPath);
					/*
					 * if(!docPath.equalsIgnoreCase("")) {
					 * if("en".equalsIgnoreCase(language)) { resultStamp =
					 * burnObj.createEStamps( outputPath, var, "English",
					 * estampObj, "EStampPdf.pdf"); } else {
					 * estampObj.seteStampType("गैर न्यायिक");
					 * 
					 * resultStamp = burnObj.createEStamps( outputPath,var,
					 * "Hindi", estampObj, "EStampPdf.pdf"); } int result = -1;
					 * outputPath =
					 * CommonConstant.DRIVE_PATH+File.separator+"upload"
					 * +File.separator
					 * +"IGRS"+File.separator+eForm.getEcode().toString();
					 * String fileName = "EStamp.pdf";
					 * burnObj.mergeDocs(CommonConstant
					 * .DRIVE_PATH+File.separator
					 * +"upload"+File.separator+"IGRS"+
					 * File.separator+eForm.getEcode().toString(),
					 * outputPath+File.separator+"EStampPdf.pdf", docPath,
					 * "EStamp.PDF");
					 * 
					 * } else
					 */

					{
						if ("en".equalsIgnoreCase(language)) {
							resultStamp = burnObj.createEStamps(outputPath, var, "English", estampObj, "EStamp1.PDF", 1, 1);
						} else {
							estampObj.seteStampType("गैर न्यायिक");
							
							resultStamp = burnObj.createEStamps(outputPath, var, "Hindi", estampObj, "EStamp1.PDF", 1, 1);
							
						}
					}
					String signFlag = pr.getValue("digital_sign_flag");
					if ("I".equalsIgnoreCase(new EstampingBD().getUserType(userId))) {
						if (signFlag.equalsIgnoreCase("Y")) {
							new DocumentService().sign(outputPath + File.separator + "EStamp1.PDF", outputPath + File.separator + "EStamp.PDF", objEstampBO.getSubjectName(userId).toString(), 100, 100, 200, 200);
						} else {
							
							ExecutorService executor = Executors.newFixedThreadPool(1);
							List<Callable<Object>> todo = new ArrayList<Callable<Object>>();
							logger.debug("signed pathelse:" + outputPath + File.separator + "EStamp.PDF");
							logger.debug("input pathelse:" + outputPath + File.separator + "EStamp1.PDF");
							
							todo.add(Executors.callable(new DigtalSignThread(outputPath + File.separator + "EStamp1.PDF", outputPath + File.separator + "EStamp.PDF", "100", "100", "200", "200", objEstampBO.getSubjectName(userId).toString())));
							// logger.debug("input path:"+documentPath);
							
							executor.invokeAll(todo, 1, TimeUnit.MINUTES); // Timeout
							
							executor.shutdown();
							
							// executor.invokeAll(Arrays.asList(new
							// DigtalSignThread(outputPath+File.separator+
							// "EStamp1.PDF",outputPath+File.separator+
							// "EStamp.PDF",objEstampBO.getSubjectName(userId).toString(),"100","100","200","200")),
							// 1, TimeUnit.MINUTES); // Timeout of 10 minutes.
							
						}
						try {
							ReadPropertiesFile prop = new ReadPropertiesFile();
							metaDataInfo.setUniqueNo(eForm.getEcode().toString());
							metaDataInfo.setLatestFlag("L");
							metaDataInfo.setType("E");
							String fileName = "EStamp.PDF";
							String path = outputPath + File.separator + fileName;
							logger.debug("path^^^^^^^^^^^^^^^^^^^^^^^^^^^" + path);
							File file = new File(path);
							if (!file.exists()) {
								throw new Exception();
							}
							metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
							String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
							logger.debug("doc Id " + docId);
							if (docId != "-1") {
								// update generate cert status in table
								boolean flag = objEstampBO.updateCertificateGenerationChk(eForm.getMainTxnId().toString());
								
							}
							String guid = GUIDGenerator.getGUID();
							// String EstampPath =
							// CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
							// String EstampPath1 =
							// CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
							String EstampPath1 = downloadPath + File.separator + guid;
							File output1;
							output1 = new File(EstampPath1.toString());
							if (output.exists()) {
								logger.info("file already exists deleting....");
								output1.delete();
							}
							if (output1.getParentFile().exists() == false) {
								logger.info("Parent not found creating directory....");
								output1.getParentFile().mkdirs();
							}
							metaDataInfo.setType("E");
							int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath1, CommonConstant.MODULE_NAME);
							
							eForm.setOwmFlag("Y");
							eForm.setOwmFile(EstampPath1 + File.separator + CommonConstant.SIGNED_ESTAMP_NAME);
							logger.debug("download result---------->" + result);
							forwardJsp = "ecodePageForZeroBal";
							
							// below code to view the doc
							// set the http content type to
							// "APPLICATION/OCTET-STREAM
							/*
							 * response.setContentType("application/download");
							 * 
							 * 
							 * response.setHeader("Content-Disposition",
							 * "attachment; filename=" +
							 * URLEncoder.encode(path,"UTF-8"));
							 * 
							 * File fileToDownload = new File(path);
							 * FileInputStream fileInputStream = new
							 * FileInputStream(fileToDownload); OutputStream out
							 * = response.getOutputStream(); byte[] buf = new
							 * byte[2048];
							 * 
							 * int readNum; while
							 * ((readNum=fileInputStream.read(buf))!=-1) {
							 * out.write(buf,0,readNum); }
							 * fileInputStream.close(); out.close();
							 */

						} catch (Exception e) {
							logger.debug(e.getStackTrace());
						}
						forwardJsp = "ecodePageForZeroBal";
					} else {
						byte[] eStampByte = DMSUtility.getDocumentBytes(outputPath + File.separator + "EStamp1.PDF");
						request.setAttribute("eStampByte", eStampByte);
						request.setAttribute("eStampfilePath", outputPath.replaceAll("\\\\", "/") + "//" + CommonConstant.SIGNED_ESTAMP_NAME);
						forwardJsp = "eStampSignPDF";
						logger.debug("BYTE ARRAY IS:::::::" + eStampByte + "FINAL PATH IS::::::::" + outputPath.replaceAll("\\\\", "/") + "//" + CommonConstant.SIGNED_ESTAMP_NAME);
						logger.debug("CODE TRANSFERRED TO E-MUDHRA");
						
					}
					
				}
				
				/*
				 * // end by vinay newgen String EstampPath =
				 * "D:/upload/IGRS/"+eForm.getEcode().toString()+"/Estamp.PDF";
				 * File output; output = new File(EstampPath.toString()); if
				 * (output.exists()) {
				 * logger.info("file already exists deleting....");
				 * output.delete(); } if (output.getParentFile().exists() ==
				 * false) {
				 * logger.info("Parent not found creating directory....");
				 * output.getParentFile().mkdirs(); } OutputStream fos = new
				 * FileOutputStream(EstampPath, false); Document document = new
				 * Document(PageSize.A4, 20, 20, 20, 20); PdfWriter writer =
				 * PdfWriter.getInstance(document, fos); document.open();
				 * 
				 * Cell row=new Cell(new Phrase("",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * row.setHeader(true); row.setColspan(22);
				 * 
				 * Table estampCerti = new Table(22); estampCerti.setWidth(100);
				 * estampCerti.setPadding(3);
				 * logger.info("======>"+estampCerti.getWidth());
				 * 
				 * // commented on 10th Oct for SIT work
				 * 
				 * PdfPTable table = new PdfPTable(18);
				 * table.setWidthPercentage(100);
				 * 
				 * String path =
				 * request.getRealPath("/")+"images\\HeaderComp.jpg";
				 * logger.info("======>"+path); Image image1 =
				 * Image.getInstance(path); //image1.setBackgroundColor(new
				 * Color(0xC0, 0xC0, 0xC0));
				 * 
				 * 
				 * PdfPCell imageHeader = new PdfPCell(image1,false);
				 * imageHeader.setColspan(18);
				 * imageHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
				 * 
				 * //imageHeader.setHorizontalAlignment(0);
				 * 
				 * imageHeader.setBorder(Rectangle.NO_BORDER);
				 * //imageHeader.setBackgroundColor(new Color(0xC0, 0xC0,
				 * 0xC0)); table.addCell(imageHeader);
				 * 
				 * // commented on 10th Oct for SIT work
				 * 
				 * //PdfPCell heading = new PdfPCell(newPhrase(
				 * "Government of Madhya Pradesh Department Of Registration And Stamps"
				 * , FontFactory.getFont(FontFactory.TIMES_ROMAN, 16,
				 * Font.BOLD))); //PdfPCell heading1 = new PdfPCell(new
				 * Phrase("Department Of Registration And Stamps",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 16,
				 * Font.BOLD)));
				 * //heading.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * //heading1.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * 
				 * //heading.setColspan(14); //heading1.setColspan(12);
				 * //heading.setBorder(Rectangle.NO_BORDER);
				 * //heading1.setBorder(Rectangle.NO_BORDER);
				 * //heading.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				 * //heading1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				 * //table.addCell(heading); //table.addCell(heading1);
				 * 
				 * 
				 * 
				 * //PdfPCell imageHeader2 = new PdfPCell(image2,false);
				 * //imageHeader2.setColspan(6);
				 * //imageHeader2.setBorder(Rectangle.NO_BORDER);
				 * //imageHeader2.setBackgroundColor(new Color(0xC0, 0xC0,
				 * 0xC0)); //table.addCell(imageHeader2);
				 * 
				 * estampCerti.addCell(row);
				 * 
				 * Cell title = new Cell(new Phrase("Certificate of Stamp Duty",
				 * FontFactory.getFont(FontFactory.TIMES_ITALIC, 14,
				 * Font.BOLD)));
				 * title.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * title.setLeading(20); title.setColspan(22);
				 * title.setBorder(Rectangle.NO_BORDER);
				 * estampCerti.addCell(title); estampCerti.setBorderWidth(2);
				 * estampCerti.setAlignment(1);
				 * 
				 * estampCerti.addCell(row);
				 * 
				 * Cell sectionheader=new Cell(new Phrase("E-Stamp Details",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
				 * Font.BOLD)));
				 * sectionheader.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * sectionheader.setColspan(22);
				 * sectionheader.setBorder(Rectangle.NO_BORDER);
				 * sectionheader.setBackgroundColor(new Color(0xC0, 0xC0,
				 * 0xC0)); estampCerti.addCell(sectionheader);
				 * estampCerti.setAlignment(2);
				 * 
				 * Cell estamp_Code=new Cell(new Phrase("E-Stamp Code",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * estamp_Code.setHeader(true); estamp_Code.setColspan(11);
				 * estampCerti.addCell(estamp_Code);
				 * 
				 * Cell ecodeValue=new Cell(new Phrase(eForm.getEcode(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); ecodeValue.setHeader(true);
				 * ecodeValue.setColspan(11); estampCerti.addCell(ecodeValue);
				 * 
				 * Cell estampAmnt=new Cell(new Phrase("E-Stamp Amount",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * estampAmnt.setHeader(true); estampAmnt.setColspan(11);
				 * estampCerti.addCell(estampAmnt);
				 * 
				 * Cell estampAmntValue=new Cell(new
				 * Phrase(eForm.getDutyCalculationDTO().getTotalDisplay(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); estampAmntValue.setHeader(true);
				 * estampAmntValue.setColspan(11);
				 * estampCerti.addCell(estampAmntValue);
				 * 
				 * Cell estampType=new Cell(new Phrase("E-Stamp Type",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * estampType.setHeader(true); estampType.setColspan(11);
				 * estampCerti.addCell(estampType);
				 * 
				 * Cell estampTypeValue=new Cell(new
				 * Phrase(eForm.getEstampType(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); estampTypeValue.setHeader(true);
				 * estampTypeValue.setColspan(11);
				 * estampCerti.addCell(estampTypeValue);
				 * 
				 * Cell issueDate=new Cell(new Phrase("Issue Date & Time",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * issueDate.setHeader(true); issueDate.setColspan(11);
				 * estampCerti.addCell(issueDate);
				 * 
				 * Cell issueDateValue=new Cell(new
				 * Phrase(eForm.getCurrentDate(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); issueDateValue.setHeader(true);
				 * issueDateValue.setColspan(11);
				 * estampCerti.addCell(issueDateValue);
				 * 
				 * Cell issuer=new Cell(new Phrase("User ID/Issuer",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * issuer.setHeader(true); issuer.setColspan(11);
				 * estampCerti.addCell(issuer);
				 * 
				 * Cell issuerValue=new Cell(new Phrase(eForm.getUserName(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); issuerValue.setHeader(true);
				 * issuerValue.setColspan(11); estampCerti.addCell(issuerValue);
				 * 
				 * Cell issueOffice=new Cell(new Phrase("SP/SRO/DRO/HO",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * issueOffice.setHeader(true); issueOffice.setColspan(11);
				 * estampCerti.addCell(issueOffice);
				 * 
				 * Cell issueOfficeValue=new Cell(new
				 * Phrase(eForm.getOfficeName(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); issueOfficeValue.setHeader(true);
				 * issueOfficeValue.setColspan(11);
				 * estampCerti.addCell(issueOfficeValue);
				 * 
				 * Cell place=new Cell(new Phrase("Place",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * place.setHeader(true); place.setColspan(11);
				 * estampCerti.addCell(place);
				 * 
				 * Cell placeValue=new Cell(new Phrase(eForm.getIssuedPlace(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); placeValue.setHeader(true);
				 * placeValue.setColspan(11); estampCerti.addCell(placeValue);
				 * 
				 * Cell validity=new Cell(new Phrase("Validity",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * validity.setHeader(true); validity.setColspan(11);
				 * estampCerti.addCell(validity);
				 * 
				 * Cell validityValue=new Cell(new
				 * Phrase(eForm.getEstampValidity(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); validityValue.setHeader(true);
				 * validityValue.setColspan(11);
				 * estampCerti.addCell(validityValue);
				 * 
				 * estampCerti.addCell(row);
				 * 
				 * Cell sectionheader1=new Cell(new Phrase("Deed Details",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
				 * Font.BOLD)));
				 * sectionheader1.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * sectionheader1.setColspan(22);
				 * sectionheader1.setBorder(Rectangle.NO_BORDER);
				 * sectionheader1.setBackgroundColor(new Color(0xC0, 0xC0,
				 * 0xC0)); estampCerti.addCell(sectionheader1);
				 * estampCerti.setAlignment(2);
				 * 
				 * Cell deedType=new Cell(new Phrase("Deed Type",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * deedType.setHeader(true); deedType.setColspan(11);
				 * estampCerti.addCell(deedType);
				 * 
				 * Cell deedTypeValue=new Cell(new
				 * Phrase(eForm.getDutyCalculationDTO().getDeedType(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); deedTypeValue.setHeader(true);
				 * deedTypeValue.setColspan(11);
				 * estampCerti.addCell(deedTypeValue);
				 * 
				 * Cell deedInst=new Cell(new Phrase("Deed Instrument",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * deedInst.setHeader(true); deedInst.setColspan(11);
				 * estampCerti.addCell(deedInst);
				 * 
				 * Cell deedInstValue=new Cell(new
				 * Phrase(eForm.getInstDTO().getInstType(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); deedInstValue.setHeader(true);
				 * deedInstValue.setColspan(11);
				 * estampCerti.addCell(deedInstValue);
				 * 
				 * Cell exemption=new Cell(new Phrase("Exemption",
				 * FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				 * exemption.setHeader(true); exemption.setColspan(11);
				 * estampCerti.addCell(exemption);
				 * 
				 * Cell exemptionValue=new Cell(new Phrase(eForm.getEcode(),
				 * FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));
				 * exemptionValue.setHeader(true);
				 * exemptionValue.setColspan(11);
				 * estampCerti.addCell(exemptionValue);
				 * 
				 * Cell purpose=new Cell(new Phrase("Purpose",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * purpose.setHeader(true); purpose.setColspan(11);
				 * estampCerti.addCell(purpose);
				 * 
				 * Cell purposeValue=new Cell(new
				 * Phrase(eForm.getEstampPurpose(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); purposeValue.setHeader(true);
				 * purposeValue.setColspan(11);
				 * estampCerti.addCell(purposeValue);
				 * 
				 * // Ecode duration field has to be given in the creation of
				 * E-stamp ------ yet to be done Cell deedDuration=new Cell(new
				 * Phrase("Deed Duration",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * deedDuration.setHeader(true); deedDuration.setColspan(11);
				 * estampCerti.addCell(deedDuration);
				 * 
				 * Cell deedDurationValue=new Cell(new
				 * Phrase(eForm.getDeedDuration(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); deedDurationValue.setHeader(true);
				 * deedDurationValue.setColspan(11);
				 * estampCerti.addCell(deedDurationValue); //
				 * estampCerti.addCell(row);
				 * 
				 * Cell sectionheader2=new Cell(new
				 * Phrase("First Party Details",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
				 * Font.BOLD)));
				 * sectionheader2.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * sectionheader2.setColspan(22);
				 * sectionheader2.setBorder(Rectangle.NO_BORDER);
				 * sectionheader2.setBackgroundColor(new Color(0xC0, 0xC0,
				 * 0xC0)); estampCerti.addCell(sectionheader2);
				 * estampCerti.setAlignment(2);
				 * 
				 * Cell aplicantName=new Cell(new Phrase("Name",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * aplicantName.setHeader(true); aplicantName.setColspan(11);
				 * estampCerti.addCell(aplicantName);
				 * 
				 * Cell aplicantNameValue=new Cell(new
				 * Phrase(eForm.getAppNamedsply(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); aplicantNameValue.setHeader(true);
				 * aplicantNameValue.setColspan(11);
				 * estampCerti.addCell(aplicantNameValue);
				 * 
				 * Cell applicantCountry=new Cell(new Phrase("Country",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * applicantCountry.setHeader(true);
				 * applicantCountry.setColspan(11);
				 * estampCerti.addCell(applicantCountry);
				 * 
				 * Cell applicantCountryValue=new Cell(new
				 * Phrase(eForm.getAppCountryName(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); applicantCountryValue.setHeader(true);
				 * applicantCountryValue.setColspan(11);
				 * estampCerti.addCell(applicantCountryValue);
				 * 
				 * Cell applicantState=new Cell(new Phrase("State",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * applicantState.setHeader(true);
				 * applicantState.setColspan(11);
				 * estampCerti.addCell(applicantState);
				 * 
				 * Cell applicantStateValue=new Cell(new
				 * Phrase(eForm.getAppStateName(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); applicantStateValue.setHeader(true);
				 * applicantStateValue.setColspan(11);
				 * estampCerti.addCell(applicantStateValue);
				 * 
				 * Cell applicantDistt=new Cell(new Phrase("District",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * applicantDistt.setHeader(true);
				 * applicantDistt.setColspan(11);
				 * estampCerti.addCell(applicantDistt);
				 * 
				 * Cell applicantDisttValue=new Cell(new
				 * Phrase(eForm.getAppDistrictName(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); applicantDisttValue.setHeader(true);
				 * applicantDisttValue.setColspan(11);
				 * estampCerti.addCell(applicantDisttValue);
				 * 
				 * Cell applicantAddress=new Cell(new Phrase("Address",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * applicantAddress.setHeader(true);
				 * applicantAddress.setColspan(11);
				 * estampCerti.addCell(applicantAddress);
				 * 
				 * Cell applicantAddressValue=new Cell(new
				 * Phrase(eForm.getAppAddress(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); applicantAddressValue.setHeader(true);
				 * applicantAddressValue.setColspan(11);
				 * estampCerti.addCell(applicantAddressValue);
				 * 
				 * if(eForm.getAppFatherName()==null ||
				 * eForm.getAppFatherName().toString().equalsIgnoreCase("")) {
				 * Cell appFatherName=new Cell(new
				 * Phrase("Authorized Person's Name",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * appFatherName.setHeader(true); appFatherName.setColspan(11);
				 * estampCerti.addCell(appFatherName);
				 * 
				 * Cell appFatherNameValue=new Cell(new
				 * Phrase(eForm.getAppAuthPersonName().toString(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); appFatherNameValue.setHeader(true);
				 * appFatherNameValue.setColspan(11);
				 * estampCerti.addCell(appFatherNameValue); } else { Cell
				 * appFatherName=new Cell(new Phrase("Father's Name",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * appFatherName.setHeader(true); appFatherName.setColspan(11);
				 * estampCerti.addCell(appFatherName);
				 * 
				 * Cell appFatherNameValue=new Cell(new
				 * Phrase(eForm.getAppFatherName(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); appFatherNameValue.setHeader(true);
				 * appFatherNameValue.setColspan(11);
				 * estampCerti.addCell(appFatherNameValue); }
				 * 
				 * 
				 * 
				 * Cell numberOfPersons=new Cell(new Phrase("Number of Persons",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * numberOfPersons.setHeader(true);
				 * numberOfPersons.setColspan(11);
				 * estampCerti.addCell(numberOfPersons);
				 * 
				 * Cell numberOfPersonsValue=new Cell(new
				 * Phrase(eForm.getAppPersons(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); numberOfPersonsValue.setHeader(true);
				 * numberOfPersonsValue.setColspan(11);
				 * estampCerti.addCell(numberOfPersonsValue);
				 * 
				 * estampCerti.addCell(row);
				 * 
				 * Cell sectionheader3=new Cell(new
				 * Phrase("Second Party Details",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
				 * Font.BOLD)));
				 * sectionheader3.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * sectionheader3.setColspan(22);
				 * sectionheader3.setBorder(Rectangle.NO_BORDER);
				 * sectionheader3.setBackgroundColor(new Color(0xC0, 0xC0,
				 * 0xC0)); estampCerti.addCell(sectionheader3);
				 * estampCerti.setAlignment(2);
				 * 
				 * Cell name=new Cell(new Phrase("Name",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * name.setHeader(true); name.setColspan(11);
				 * estampCerti.addCell(name);
				 * 
				 * Cell nameValue=new Cell(new Phrase(eForm.getPartyNamedsply(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); nameValue.setHeader(true);
				 * nameValue.setColspan(11); estampCerti.addCell(nameValue);
				 * 
				 * Cell country=new Cell(new Phrase("Country",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * country.setHeader(true); country.setColspan(11);
				 * estampCerti.addCell(country);
				 * 
				 * Cell countryValue=new Cell(new
				 * Phrase(eForm.getPartyCountryName(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); countryValue.setHeader(true);
				 * countryValue.setColspan(11);
				 * estampCerti.addCell(countryValue);
				 * 
				 * Cell state=new Cell(new Phrase("State",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * state.setHeader(true); state.setColspan(11);
				 * estampCerti.addCell(state);
				 * 
				 * Cell stateValue=new Cell(new
				 * Phrase(eForm.getPartyStateName(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); stateValue.setHeader(true);
				 * stateValue.setColspan(11); estampCerti.addCell(stateValue);
				 * 
				 * Cell distt=new Cell(new Phrase("District",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * distt.setHeader(true); distt.setColspan(11);
				 * estampCerti.addCell(distt);
				 * 
				 * Cell disttValue=new Cell(new
				 * Phrase(eForm.getPartyDistrictName(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); disttValue.setHeader(true);
				 * disttValue.setColspan(11); estampCerti.addCell(disttValue);
				 * 
				 * Cell address=new Cell(new Phrase("Address",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * address.setHeader(true); address.setColspan(11);
				 * estampCerti.addCell(address);
				 * 
				 * Cell addressValue=new Cell(new
				 * Phrase(eForm.getPartyAddress(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); addressValue.setHeader(true);
				 * addressValue.setColspan(11);
				 * estampCerti.addCell(addressValue);
				 * 
				 * 
				 * if(eForm.getPartyFatherName()==null ||
				 * eForm.getPartyFatherName().toString().equalsIgnoreCase("")) {
				 * Cell fatherName=new Cell(new
				 * Phrase("Authorized Person's Name",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * fatherName.setHeader(true); fatherName.setColspan(11);
				 * estampCerti.addCell(fatherName);
				 * 
				 * Cell fatherNameValue=new Cell(new
				 * Phrase(eForm.getPartyAuthPersonName(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); fatherNameValue.setHeader(true);
				 * fatherNameValue.setColspan(11);
				 * estampCerti.addCell(fatherNameValue);
				 * 
				 * 
				 * } else { Cell fatherName=new Cell(new Phrase("Father's Name",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * fatherName.setHeader(true); fatherName.setColspan(11);
				 * estampCerti.addCell(fatherName);
				 * 
				 * Cell fatherNameValue=new Cell(new
				 * Phrase(eForm.getPartyFatherName(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); fatherNameValue.setHeader(true);
				 * fatherNameValue.setColspan(11);
				 * estampCerti.addCell(fatherNameValue); }
				 * 
				 * 
				 * Cell numberOfPerson=new Cell(new Phrase("Number of Persons",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.BOLD)));
				 * numberOfPerson.setHeader(true);
				 * numberOfPerson.setColspan(11);
				 * estampCerti.addCell(numberOfPerson);
				 * 
				 * Cell numberOfPersonValue=new Cell(new
				 * Phrase(eForm.getPartyPersons(),
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 8,
				 * Font.NORMAL))); numberOfPersonValue.setHeader(true);
				 * numberOfPersonValue.setColspan(11);
				 * estampCerti.addCell(numberOfPersonValue);
				 * 
				 * estampCerti.addCell(row);
				 * 
				 * Cell sectionheader4=new Cell(new Phrase("2D Bar Code",
				 * FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
				 * Font.BOLD)));
				 * sectionheader4.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * sectionheader4.setColspan(22);
				 * sectionheader4.setBorder(Rectangle.NO_BORDER);
				 * sectionheader4.setBackgroundColor(new Color(0xC0, 0xC0,
				 * 0xC0)); estampCerti.addCell(sectionheader4);
				 * estampCerti.setAlignment(2);
				 * 
				 * estampCerti.setCellsFitPage(true); //document.add(table);
				 * document.add(estampCerti); document.close();
				 * response.setContentType("estamp/pdf");
				 * response.setHeader("Content-Disposition",
				 * "attachment; filename=\"E-Stamp Certificate.pdf");
				 * response.setContentLength(baos.size()); ServletOutputStream
				 * out = response.getOutputStream(); baos.writeTo(out);
				 * out.flush();
				 * 
				 * boolean boo = false;
				 * logger.debug("---->  inside pdf action"+eForm
				 * .getMainTxnId());
				 * 
				 * //boo = bo.updateStatus(eForm); boo = true; //eForm=null;
				 * String docPath =
				 * objEstampBO.getEstampDocDetails(eForm.getMainTxnId
				 * ().toString()); String outputPath = ""; String fileName = "";
				 * if(!docPath.equalsIgnoreCase("")) {
				 * 
				 * int result = -1; BurningImageAndText burnObj = new
				 * BurningImageAndText(); outputPath =
				 * "D:/upload/IGRS/"+eForm.getEcode().toString(); fileName =
				 * "EStamp_temp.pdf"; String
				 * var=getServlet().getServletContext().getRealPath("")+
				 * "/images/header img.jpg"; result =
				 * burnObj.generateEStampWithBarcode(outputPath,var, EstampPath,
				 * fileName); logger.debug("result  ^^^^^^^^^^^^^^^^^ "+result);
				 * 
				 * //below code to check if there is any scanned document , if
				 * any merging both docs
				 * 
				 * String dmsFolderName = "IGRS"; //result =
				 * burnObj.mergeCertificateWithEStamp
				 * (connDetails,metaDataInfo,"D:\\download\\", "R",
				 * "RegCert.pdf", "E" , "EStampPdf.pdf", outputPath, "RE",
				 * "FinalCert.pdf", dmsFolderName); //merging two docs
				 * burnObj.mergeDocs
				 * ("D:/upload/IGRS/"+eForm.getEcode().toString(),
				 * outputPath+"\\EStamp_temp.pdf", docPath, "Estamp.PDF");
				 * 
				 * } else { int result = -1; BurningImageAndText burnObj = new
				 * BurningImageAndText(); outputPath =
				 * "D:/upload/IGRS/"+eForm.getEcode().toString(); fileName =
				 * "EStamp.pdf"; String
				 * var=getServlet().getServletContext().getRealPath("")+
				 * "/images/header img.jpg";
				 * 
				 * result = burnObj.generateEStampWithBarcode(outputPath, var,
				 * EstampPath, fileName);
				 * logger.debug("result  ^^^^^^^^^^^^^^^^^ "+result); } //below
				 * code for uploading in DMS boolean flag = true; try {
				 * ReadPropertiesFile prop = new ReadPropertiesFile(); //DMS
				 * start //PropertiesFileReader pr =
				 * PropertiesFileReader.getInstance("resources.igrs");
				 * 
				 * // DocumentOperations dos = new DocumentOperations();
				 * //ODServerDetails ods = new ODServerDetails(); //Dataclass dc
				 * = new Dataclass();
				 * 
				 * 
				 * // ods.setAppServerIp(pr.getValue("AppServerIp")); //
				 * ods.setCabinetName(pr.getValue("CabinetName")); //
				 * ods.setAppServerUserId(pr.getValue("AppServerUserId")); //
				 * ods.setAppServerUserPass(pr.getValue("AppServerUserPass"));
				 * // ods.setImageServerIp(pr.getValue("ImageServerIP")); //
				 * ods.setImageServerPort(pr.getValue("ImageServerPort")); //
				 * ods.setIniPath(
				 * "D:\\simran\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
				 * metaDataInfo.setUniqueNo(eForm.getEcode().toString());
				 * fileName = "EStamp.pdf"; String path =
				 * outputPath+"/"+fileName;
				 * logger.debug("path^^^^^^^^^^^^^^^^^^^^^^^^^^^"+path); File
				 * file = new File(path);
				 * metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
				 * String docId = docOperations.uploadDocument(connDetails,
				 * file, "IGRS", true, metaDataInfo);
				 * logger.debug("doc Id "+docId); if(docId != "-1") { // update
				 * generate cert status in table flag =
				 * objEstampBO.updateCertificateGenerationChk
				 * (eForm.getMainTxnId().toString());
				 * 
				 * 
				 * } // below code to view the doc // set the http content type
				 * to "APPLICATION/OCTET-STREAM
				 * response.setContentType("application/download");
				 * 
				 * 
				 * response.setHeader("Content-Disposition",
				 * "attachment; filename=" + URLEncoder.encode(path,"UTF-8"));
				 * 
				 * File fileToDownload = new File(path); FileInputStream
				 * fileInputStream = new FileInputStream(fileToDownload);
				 * OutputStream out = response.getOutputStream(); byte[] buf =
				 * new byte[2048];
				 * 
				 * int readNum; while ((readNum=fileInputStream.read(buf))!=-1)
				 * { out.write(buf,0,readNum); } fileInputStream.close();
				 * out.close();
				 * 
				 * } catch(Exception e) {
				 * 
				 * } forwardJsp="estampSuccess"; }
				 */

			}
			
			if ("DOC_SIGNED_DSC".equalsIgnoreCase(actionName)) {
				logger.debug("ESTAMP ID AFTER PDF------>" + eForm.getMainTxnId().toString());
				// For the pdf creation of E-stamp certificate.
				System.out.println("DOC_SIGNED_DSC" + eForm.getSignedPath());
				String outputPath = eForm.getSignedPath();
				if (outputPath != null && !outputPath.isEmpty()) {
					
					String estampGenCert = objEstampBO.getCertChkDetails(eForm.getMainTxnId().toString());
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					DocumentOperations docOperations = new DocumentOperations();
					ODServerDetails connDetails = new ODServerDetails();
					Dataclass metaDataInfo = new Dataclass();
					connDetails.setAppServerIp(pr.getValue("AppServerIp"));
					connDetails.setCabinetName(pr.getValue("CabinetName"));
					connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
					connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
					connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
					connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
					connDetails.setIniPath("D:\\simran\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
					metaDataInfo.setUniqueNo(eForm.getEcode().toString());
					metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
					
					if ("".equalsIgnoreCase(eForm.getStampDisplay().trim()))
						bo.getDutyDetails(eForm.getMainTxnId(), eForm);
					
					if (estampGenCert != null && estampGenCert.equalsIgnoreCase("Y")) {
						session.setAttribute("estampID", null);
						eForm.setPrintCheck("N");
						forwardJsp = "ecodePageForZeroBal";
						return mapping.findForward(forwardJsp);
					}
					try {
						ReadPropertiesFile prop = new ReadPropertiesFile();
						metaDataInfo.setUniqueNo(eForm.getEcode().toString());
						metaDataInfo.setLatestFlag("L");
						metaDataInfo.setType("E");
						String fileName = "EStamp.PDF";
						String path = outputPath;// +File.separator+fileName;
						logger.debug("path^^^^^^^^^^^^^^^^^^^^^^^^^^^" + path);
						File file = new File(path);
						if (!file.exists()) {
							throw new Exception();
						}
						metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
						String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
						logger.debug("doc Id " + docId);
						boolean flag = false;
						if (docId != "-1") {
							// update generate cert status in table
							flag = objEstampBO.updateCertificateGenerationChk(eForm.getMainTxnId().toString());
							session.setAttribute("estampID", "estampreprint");
							
						}
						if (flag) {
							eForm.setPrintCheck("N");
						}
						String guid = GUIDGenerator.getGUID();
						// String EstampPath =
						// CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
						// String EstampPath1 =
						// CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
						String EstampPath1 = downloadPath + File.separator + guid;
						File output1;
						output1 = new File(EstampPath1.toString());
						if (output1.exists()) {
							logger.info("file already exists deleting....");
							output1.delete();
						}
						if (output1.getParentFile().exists() == false) {
							logger.info("Parent not found creating directory....");
							output1.getParentFile().mkdirs();
						}
						metaDataInfo.setType("E");
						int result = docOperations.downloadDocument(connDetails, metaDataInfo, EstampPath1, CommonConstant.MODULE_NAME);
						
						eForm.setOwmFlag("Y");
						eForm.setOwmFile(EstampPath1 + File.separator + CommonConstant.SIGNED_ESTAMP_NAME);
						logger.debug("OWN FILE PATH::::::" + eForm.getOwmFile());
						logger.debug("download result---------->" + result);
						forwardJsp = "ecodePageForZeroBal";
						
					} catch (Exception e) {
						logger.debug(e.getStackTrace());
						forwardJsp = "failure";
					}
					forwardJsp = "ecodePageForZeroBal";
				} else {
					if ("".equalsIgnoreCase(eForm.getStampDisplay().trim()))
						bo.getDutyDetails(eForm.getMainTxnId(), eForm);
					if (language.equalsIgnoreCase("en"))
						request.setAttribute(CommonConstant.FAILURE_MSG, "Could not Apply the Digital Signature");
					else
						request.setAttribute(CommonConstant.FAILURE_MSG, "डिजिटल हस्ताक्षर दस्तावेज़ में नहीं लग पाया है।");
					
					forwardJsp = "ecodePageForZeroBal";
				}
				
			}
			// For E-Stamp Search Screen for consumption of E-stamp
			
			if (request.getParameter("modName") != null) {
				if (request.getParameter("modName").equalsIgnoreCase("E-StampConsumption")) {
					
					logger.debug("------------------->inside search action for consumption");
					
					eForm.getObjDashBoardDTO().setCheck("");
					eForm.getObjDashBoardDTO().setEcode("");
					
					forwardJsp = "searchConsumption";
					logger.info("--------------->showing search screen for consumption");
					
					return mapping.findForward(forwardJsp);
				}
			} // end of E-Stamp Search Screen for consumption of E-stamp
			
			// ---- after search of consumption, the verification/updation
			// screen
			
			if ("SEARCH_ACTION_CONSUMPTION".equalsIgnoreCase(actionName)) {
				
				eForm.getObjDashBoardDTO().setCheck("");
				eForm.getObjDashBoardDTO().setIsConsumedChecked(0);
				
				ArrayList estampCode;
				String estampCodeId = eForm.getObjDashBoardDTO().getEcode();
				objDashBoardDTO1.setEcode(estampCodeId);
				logger.debug("--------------->" + objDashBoardDTO1.getEcode());
				logger.debug("--------------->" + estampCodeId);
				estampCode = objEstampBO.checkEcode(estampCodeId);
				
				logger.debug("----------------> inside search action");
				
				if (estampCode.size() == 0) {
					eForm.getObjDashBoardDTO().setCheck("Y");
					logger.debug("----------------> no ecode obtained from database");
					forwardJsp = "searchConsumption";
					logger.debug("-------------->" + forwardJsp);
				} else {
					/* ARY */
					ArrayList ecodeDetailsDRS = new ArrayList();
					String estampid = estampCodeId.substring(3, 4);
					if (estampid.equalsIgnoreCase("1")) {
						ecodeDetailsDRS = objEstampBO.viewEcodeDetailsNJ_DRS(estampCodeId, language);
						logger.debug("----------------> ecode obtained");
					} else if (estampid.equalsIgnoreCase("2")) {
						ecodeDetailsDRS = objEstampBO.viewEcodeDetailsDRS(estampCodeId, language);
						logger.debug("----------------> ecode obtained");
					}
					
					// ///////////////////////////////
					/*
					 * ArrayList ecodeDetailsDRS = objEstampBO
					 * .viewEcodeDetailsDRS(estampCodeId, language);
					 */
					logger.debug("----------------> ecode obtained");
					if (ecodeDetailsDRS.size() == 0)
						eForm.getObjDashBoardDTO().setViewDRSEcodeDetails(null);
					else
						eForm.getObjDashBoardDTO().setViewDRSEcodeDetails(ecodeDetailsDRS);
					
					request.setAttribute("ecodeDetails", eForm.getObjDashBoardDTO().getViewDRSEcodeDetails());
					logger.debug("----------------> ecodeDetails obtained");
					
					String ecodeTypeID = estampCodeId.substring(3, 4);
					
					ArrayList ecodeType = objEstampBO.viewEcodeType(ecodeTypeID, language);
					
					if (ecodeType.size() == 0)
						eForm.getObjDashBoardDTO().setViewRUEcodeType(null);
					else
						eForm.getObjDashBoardDTO().setViewRUEcodeType(ecodeType);
					
					request.setAttribute("ecodeType", eForm.getObjDashBoardDTO().getViewRUEcodeType());
					
					ArrayList viewApplicantDetailsDRS = objEstampBO.getDetailsOfApplicantDRS(estampCodeId, language);
					logger.info("--------------->" + viewApplicantDetailsDRS.size());
					String moduleFlag = bo.moduleFlag(estampCodeId);
					if ("R".equalsIgnoreCase(moduleFlag)) {
						String txnId = bo.getRegId(estampCodeId);
						bo.getDutyDetailsInitiation(txnId, eForm, "I");
						eForm.setBreifDocument("NA");
					} else if ("C".equalsIgnoreCase(moduleFlag)) {
						String txnId = bo.getRegId(estampCodeId);
						bo.getDutyDetailsInitiation(txnId, eForm, "C");
						eForm.setBreifDocument("NA");
					} else {
						eForm.setBreifDocument(bo.getBreifDocument(estampCodeId));
						bo.getDutyDetails(estampCodeId, eForm);
					}
					eForm.setEstampTypeCheck(bo.getEstampCodeType(estampCodeId));
					if (viewApplicantDetailsDRS.size() == 0)
						eForm.getObjDashBoardDTO().setPartyDetailsDRS(null);
					else
						eForm.getObjDashBoardDTO().setPartyDetailsDRS(viewApplicantDetailsDRS);
					
					request.setAttribute("applicantDetails", eForm.getObjDashBoardDTO().getPartyDetailsDRS());
					
					for (int i = 0; i < viewApplicantDetailsDRS.size(); i++) {
						DashBoardDTO ddto = (DashBoardDTO) viewApplicantDetailsDRS.get(i);
						
						logger.info("--------------->" + ddto.getAppStatus());
						logger.info("--------------->" + ddto.getAppType());
						logger.info("--------------->" + ddto.getPartyType());
						logger.info("--------------->" + ddto.getAppAuthFirstName());
						logger.info("--------------->" + ddto.getPartyAuthFirstName());
					}
					
					ArrayList ecodeConsumption = objEstampBO.getecodeConsumption(estampCodeId, language);
					logger.debug("----------------> ecode obtained");
					if (ecodeDetailsDRS.size() == 0)
						eForm.getObjDashBoardDTO().setEcodeConStatus(null);
					else
						eForm.getObjDashBoardDTO().setEcodeConStatus(ecodeConsumption);
					
					request.setAttribute("ecodeDetails", eForm.getObjDashBoardDTO().getEcodeConStatus());
					logger.debug("----------------> ecodeDetails obtained");
					
					forwardJsp = "ecodeConView";
					logger.debug("----------------> ecodeConsumption Status obtained");
				}
				// return mapping.findForward(forwardJsp);
				// eForm.getObjDashBoardDTO().setEcode("");
			}// ---- end of after search of consumption, the
			// verification/updation screen
			
			// --- For consumption textarea if status is not consumed
			
			if ("CONSUME".equalsIgnoreCase(actionName)) {
				String estampCodeId = eForm.getObjDashBoardDTO().getEcode();
				String moduleFlag = bo.moduleFlag(estampCodeId);
				if ("R".equalsIgnoreCase(moduleFlag)) {
					String txnId = bo.getRegId(estampCodeId);
					bo.getDutyDetailsInitiation(txnId, eForm, "I");
					eForm.setBreifDocument("NA");
				} else if ("C".equalsIgnoreCase(moduleFlag)) {
					String txnId = bo.getRegId(estampCodeId);
					bo.getDutyDetailsInitiation(txnId, eForm, "C");
					eForm.setBreifDocument("NA");
				} else {
					eForm.setBreifDocument(bo.getBreifDocument(estampCodeId));
					bo.getDutyDetails(estampCodeId, eForm);
				}
				eForm.getObjDashBoardDTO().setIsConsumedChecked(1);
				
				eForm.getObjDashBoardDTO().setRemarks("");
				eForm.setEstampTypeCheck(bo.getEstampCodeType(estampCodeId));
				
				forwardJsp = "ecodeConView";
				// eForm.getObjDashBoardDTO().setIsConsumedChecked(0);
			}
			// --- end of consumption textarea if status is not consumed
			
			// --- updation of ecode detls table for status consumed
			
			if ("SUBMIT_CONSUME".equalsIgnoreCase(actionName)) {
				logger.debug("inside consume insert action");
				try {
					logger.debug("inside consume insert action try");
					boolean txn = false;
					logger.debug("---- ecode ---" + objDashBoardDTO1.getEcode());
					txn = bo.insertConsume(eForm, objDashBoardDTO1);
					logger.debug("inside consume insert action-after insert");
					forwardJsp = "consumptionSuccess";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}// --- end of updation of ecode detls table for status consumed
			
			if (request.getParameter("flag") != null) {
				if (request.getParameter("flag").equalsIgnoreCase("fromInitiation")) {
					
					try {
						
						logger.debug("FROM INITIATION TO ESTAMP CODE GENERATION");
						EstampDTO estampDTO = new EstampDTO();
						estampDTO.setUid(userId);
						String disttId = (String) request.getAttribute("eStampRegDistId");
						String amnt = (String) request.getAttribute("eStampRegAmnt");
						String func_id = (String) request.getAttribute("eStampRegFuncId");
						String purpose = (String) request.getAttribute("eStampRegPurpose");
						String regInitId = (String) request.getAttribute("eStampRegId");
						
						if (disttId != null || disttId != "") {
							String eCode = null;
							
							// names and office details//
							
							String type = objEstampBO.gettype(userId); // --added
							// by
							// satbir
							// kumar--
							
							logger.debug(type);
							
							/*
							 * String roleId = bo.getRole(userId); String srrole
							 * = "GR1356609170484"; String drrole =
							 * "GR1356671177515"; String sprole =
							 * "GR1358488496205"; String rurole =
							 * "GR1357368369828"; String spbankrole =
							 * "GR1359453019113";
							 * 
							 * eForm.getObjDashBoardDTO().setRole(roleId);
							 */

							if ("2".equalsIgnoreCase(type)) {
								ArrayList userdet = new ArrayList();
								ArrayList comp2 = new ArrayList();
								userdet = bo.getruName(userId, language);
								if (userdet.size() > 0) {
									for (int i = 0; i < userdet.size(); i++) {
										comp2.add((ArrayList) userdet.get(i));
										if (comp2 != null && comp2.size() > 0) {
											for (int k = 0; k < comp2.size(); k++) {
												ArrayList compSub = (ArrayList) comp2.get(k);
												
												String name = (String) compSub.get(0);
												String cid = (String) compSub.get(1);
												String sid = (String) compSub.get(2);
												String did = (String) compSub.get(3);
												String dname = (String) compSub.get(4);
												
												estampDTO.setUserName(name);
												if (cid.equalsIgnoreCase("1")) {
													if (sid.equalsIgnoreCase("1")) {
														estampDTO.setIssuedPlace(dname);
														
													} else {
														if ("en".equalsIgnoreCase(language)) {
															estampDTO.setIssuedPlace("Others");
														} else {
															estampDTO.setIssuedPlace("अन्य");
														}
													}
													
												} else {
													if ("en".equalsIgnoreCase(language)) {
														estampDTO.setIssuedPlace("Others");
													} else {
														estampDTO.setIssuedPlace("अन्य");
													}
												}
												
											}
										}
									}
								}
								
								estampDTO.setOfficeName("-");
							}

							else if ("3".equalsIgnoreCase(type)) {
								String spname = bo.getspName(userId);
								String licNo = bo.getspLicenseNo(userId);
								ArrayList spdtls = new ArrayList();
								ArrayList comp3 = new ArrayList();
								spdtls = bo.getspDtls(userId, language);
								if (spdtls.size() > 0) {
									for (int i = 0; i < spdtls.size(); i++) {
										comp3.add((ArrayList) spdtls.get(i));
										if (comp3 != null && comp3.size() > 0) {
											for (int k = 0; k < comp3.size(); k++) {
												ArrayList compSub = (ArrayList) comp3.get(k);
												
												String ofc = (String) compSub.get(0);
												String plc = (String) compSub.get(1);
												String tehsil = (String) compSub.get(2);
												estampDTO.setOfficeName(ofc + " " + tehsil + " " + plc);
												estampDTO.setIssuedPlace(plc);
												estampDTO.setUserName(spname + "/" + licNo);
											}
										}
									}
									
								}
							}

							else if ("4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
								
								ArrayList spbankdtls = new ArrayList();
								ArrayList comp4 = new ArrayList();
								spbankdtls = bo.getspbnkDtls(userId, language);
								if (spbankdtls.size() > 0) {
									for (int i = 0; i < spbankdtls.size(); i++) {
										comp4.add((ArrayList) spbankdtls.get(i));
										if (comp4 != null && comp4.size() > 0) {
											for (int k = 0; k < comp4.size(); k++) {
												ArrayList compSub = (ArrayList) comp4.get(k);
												
												String uname = (String) compSub.get(0);
												String ofc = (String) compSub.get(1);
												String plc = (String) compSub.get(2);
												String tehsil = (String) compSub.get(3);
												estampDTO.setOfficeName(ofc + " " + tehsil + " " + plc);
												estampDTO.setIssuedPlace(plc);
												estampDTO.setUserName(uname);
											}
										}
									}
									
								}
							}

							else if ("I".equalsIgnoreCase(type)) {
								ArrayList iudtls = new ArrayList();
								ArrayList comp4 = new ArrayList();
								String officeId = (String) session.getAttribute("loggedToOffice");
								iudtls = bo.getiuDtls(userId, officeId, language);
								if (iudtls.size() > 0) {
									for (int i = 0; i < iudtls.size(); i++) {
										comp4.add((ArrayList) iudtls.get(i));
										if (comp4 != null && comp4.size() > 0) {
											for (int k = 0; k < comp4.size(); k++) {
												ArrayList compSub = (ArrayList) comp4.get(k);
												
												String ofc = (String) compSub.get(0);
												String plc = (String) compSub.get(1);
												String uname = (String) compSub.get(2);
												estampDTO.setOfficeName(ofc);
												estampDTO.setIssuedPlace(plc);
												estampDTO.setUserName(uname);
											}
										}
									}
									
								}
							}
							checkEstamp = bo.checkEstamp(regInitId, "R");
							if (checkEstamp) {
								String ecodeTxnId = bo.getEcode(disttId, amnt, func_id, purpose, estampDTO, regInitId, "R", language);
								logger.debug("ecode is:" + ecodeTxnId);
								request.setAttribute("eCode", ecodeTxnId);
								logger.debug("ecode is:" + ecodeTxnId.split("~")[0]);
								String TxnRequestId = bo.getEstampTxnId(ecodeTxnId.split("~")[0]);
								estampDTO.setMainTxnId(TxnRequestId);
								ArrayList second = new ArrayList();
								ArrayList comp1 = new ArrayList();
								second = bo.getDeedDtls(TxnRequestId, language);
								if (second.size() > 0) {
									for (int i = 0; i < second.size(); i++) {
										comp1.add((ArrayList) second.get(i));
										if (comp1 != null && comp1.size() > 0) {
											for (int k = 0; k < comp1.size(); k++) {
												ArrayList compSub = (ArrayList) comp1.get(k);
												String deed = (String) compSub.get(0);
												String inst = (String) compSub.get(1);
												String purpose1 = (String) compSub.get(2);
												String instDiscp = (String) compSub.get(3);
												logger.debug("the deed is.........................." + deed);
												logger.debug("the inst is.........................." + inst);
												logger.debug("the purpose is.........................." + purpose);
												estampDTO.getDutyCalculationDTO().setDeedType(deed);
												estampDTO.getDutyCalculationDTO().setInstDescription(instDiscp);
												estampDTO.getDutyCalculationDTO().setInstDescription(instDiscp);
												estampDTO.getInstDTO().setInstType(inst);
												estampDTO.setEstampPurpose(purpose1);
											}
										}
									}
								}
								// names and office details//
								// String type = objEstampBO.gettype(userId);
								// //--added by satbir kumar--
								
								// logger.debug(type);
								
								/*
								 * String roleId = bo.getRole(userId); String
								 * srrole = "GR1356609170484"; String drrole =
								 * "GR1356671177515"; String sprole =
								 * "GR1358488496205"; String rurole =
								 * "GR1357368369828"; String spbankrole =
								 * "GR1359453019113";
								 * 
								 * eForm.getObjDashBoardDTO().setRole(roleId);
								 */

								/*
								 * if("2".equalsIgnoreCase(type)) { ArrayList
								 * userdet = new ArrayList(); ArrayList comp2 =
								 * new ArrayList(); userdet =
								 * bo.getruName(userId,language); if
								 * (userdet.size() > 0) { for (int i = 0; i <
								 * userdet.size(); i++) { comp2.add((ArrayList)
								 * userdet.get(i)); if (comp2 != null &&
								 * comp2.size() > 0) { for (int k = 0; k <
								 * comp2.size(); k++) { ArrayList compSub =
								 * (ArrayList) comp2 .get(k);
								 * 
								 * String name = (String) compSub .get(0);
								 * String cid = (String) compSub .get(1); String
								 * sid = (String) compSub .get(2); String did =
								 * (String) compSub .get(3); String dname =
								 * (String) compSub .get(4);
								 * 
								 * eForm.setUserName(name); if
								 * (cid.equalsIgnoreCase("1")) { if
								 * (sid.equalsIgnoreCase("1")) {
								 * eForm.setIssuedPlace(dname);
								 * 
								 * } else { eForm .setIssuedPlace("Others"); }
								 * 
								 * } else { eForm.setIssuedPlace("Others"); }
								 * 
								 * } } } }
								 * 
								 * eForm.setOfficeName("-"); }
								 */

								/*
								 * else if ("3".equalsIgnoreCase(type)) { String
								 * spname = bo.getspName(userId); ArrayList
								 * spdtls = new ArrayList(); ArrayList comp3 =
								 * new ArrayList(); spdtls =
								 * bo.getspDtls(userId,language); if
								 * (spdtls.size() > 0) { for (int i = 0; i <
								 * spdtls.size(); i++) { comp3.add((ArrayList)
								 * spdtls.get(i)); if (comp3 != null &&
								 * comp3.size() > 0) { for (int k = 0; k <
								 * comp3.size(); k++) { ArrayList compSub =
								 * (ArrayList) comp3 .get(k);
								 * 
								 * String ofc = (String) compSub .get(0); String
								 * plc = (String) compSub .get(1);
								 * eForm.setOfficeName(ofc);
								 * eForm.setIssuedPlace(plc);
								 * eForm.setUserName(spname); } } }
								 * 
								 * } }
								 */

								/*
								 * elseif("4".equalsIgnoreCase(type)||"7".
								 * equalsIgnoreCase
								 * (type)||"5".equalsIgnoreCase(type)) {
								 * 
								 * ArrayList spbankdtls = new ArrayList();
								 * ArrayList comp4 = new ArrayList(); spbankdtls
								 * = bo.getspbnkDtls(userId,language); if
								 * (spbankdtls.size() > 0) { for (int i = 0; i <
								 * spbankdtls.size(); i++) {
								 * comp4.add((ArrayList) spbankdtls.get(i)); if
								 * (comp4 != null && comp4.size() > 0) { for
								 * (int k = 0; k < comp4.size(); k++) {
								 * ArrayList compSub = (ArrayList) comp4
								 * .get(k);
								 * 
								 * String uname = (String) compSub .get(0);
								 * String ofc = (String) compSub .get(1); String
								 * plc = (String) compSub .get(2);
								 * eForm.setOfficeName(ofc);
								 * eForm.setIssuedPlace(plc);
								 * eForm.setUserName(uname); } } }
								 * 
								 * } }
								 */

								// else if("I".equalsIgnoreCase(type))
								// {
								ArrayList iudtls = new ArrayList();
								ArrayList comp4 = new ArrayList();
								String officeId = (String) session.getAttribute("loggedToOffice");
								iudtls = bo.getiuDtls(userId, officeId, language);
								if (iudtls.size() > 0) {
									for (int i = 0; i < iudtls.size(); i++) {
										comp4.add((ArrayList) iudtls.get(i));
										if (comp4 != null && comp4.size() > 0) {
											for (int k = 0; k < comp4.size(); k++) {
												ArrayList compSub = (ArrayList) comp4.get(k);
												
												String ofc = (String) compSub.get(0);
												String plc = (String) compSub.get(1);
												String uname = (String) compSub.get(2);
												estampDTO.setOfficeName(ofc);
												estampDTO.setIssuedPlace(plc);
												estampDTO.setUserName(uname);
											}
										}
									}
									
								}
								// }
								
								// party details--applicant//
								/*
								 * ArrayList appdtls = new ArrayList();
								 * ArrayList comp5 = new ArrayList(); appdtls =
								 * bo.getAppDtls(TxnRequestId,language); if
								 * (appdtls.size() > 0) { for (int i = 0; i <
								 * appdtls.size(); i++) { comp5.add((ArrayList)
								 * appdtls.get(i)); if (comp5 != null &&
								 * comp5.size() > 0) { for (int k = 0; k <
								 * comp5.size(); k++) { ArrayList compSub =
								 * (ArrayList) comp5 .get(k);
								 * 
								 * String name = (String) compSub.get(0); String
								 * cntry = (String) compSub.get(1); String state
								 * = (String) compSub.get(2); String district =
								 * (String) compSub .get(3); String addrs =
								 * (String) compSub.get(4); if(addrs!=null) {
								 * addrs =
								 * addrs.replace(RegInitConstant.SPLIT_CONSTANT
								 * ,","); } String fathrNme = (String) compSub
								 * .get(5); String noOfPrsns = (String) compSub
								 * .get(6); String orgName = (String) compSub
								 * .get(7); String appid=(String) compSub
								 * .get(11); String officialName=(String)
								 * compSub .get(12); String
								 * officialAddr=(String) compSub .get(13);
								 * String disId = (String) compSub.get(8);
								 * String authPersonName = (String)
								 * compSub.get(10);
								 * 
								 * orgName = orgName== null?"":orgName; if
								 * (orgName.equalsIgnoreCase("")) {
								 * if("3".equalsIgnoreCase(appid)) {
								 * if(officialName
								 * !=null&&!officialName.equalsIgnoreCase("")) {
								 * estampDTO.setAppNamedsply(officialName);
								 * 
								 * } else { estampDTO.setAppNamedsply("--"); }
								 * estampDTO.setAppAddress(officialAddr); } else
								 * { estampDTO.setAppNamedsply(name);
								 * estampDTO.setAppAddress(addrs); }
								 * estampDTO.setAppCountryName(cntry);
								 * estampDTO.setAppStateName(state);
								 * estampDTO.setAppDistrictName(district);
								 * 
								 * estampDTO.setAppFatherName(fathrNme);
								 * estampDTO.setAppPersons(noOfPrsns);
								 * estampDTO.setAppDistrict(disId); } else {
								 * estampDTO.setAppNamedsply(orgName);
								 * estampDTO.setAppCountryName(cntry);
								 * estampDTO.setAppStateName(state);
								 * estampDTO.setAppDistrictName(district);
								 * estampDTO.setAppAddress(addrs);
								 * estampDTO.setAppAuthPersonName
								 * (authPersonName);
								 * estampDTO.setAppPersons(noOfPrsns);
								 * estampDTO.setAppDistrict(disId);
								 * estampDTO.setIsAuthapp(1);
								 * 
								 * }
								 * 
								 * } } }
								 * 
								 * }
								 */

								// party details--party//
								/*
								 * ArrayList partydtls = new ArrayList();
								 * ArrayList comp6 = new ArrayList(); partydtls
								 * = bo.getPartyDtls(TxnRequestId,language); if
								 * (partydtls.size() > 0) { for (int i = 0; i <
								 * partydtls.size(); i++) {
								 * comp6.add((ArrayList) partydtls.get(i)); if
								 * (comp6 != null && comp6.size() > 0) { for
								 * (int k = 0; k < comp6.size(); k++) {
								 * ArrayList compSub = (ArrayList) comp6
								 * .get(k);
								 * 
								 * String name = (String) compSub.get(0); String
								 * cntry = (String) compSub.get(1); String state
								 * = (String) compSub.get(2); String district =
								 * (String) compSub .get(3); String addrs =
								 * (String) compSub.get(4); if(addrs!=null) {
								 * addrs =
								 * addrs.replace(RegInitConstant.SPLIT_CONSTANT
								 * ,","); } String fathrNme = (String) compSub
								 * .get(5); String noOfPrsns = (String) compSub
								 * .get(6); String orgName = (String) compSub
								 * .get(7); String authPersonName = (String)
								 * compSub .get(8); String appid=(String)
								 * compSub .get(9); String officialName=(String)
								 * compSub .get(10); String
								 * officialAddr=(String) compSub .get(11);
								 * orgName = orgName== null?"":orgName; if
								 * (orgName.equalsIgnoreCase("")) {
								 * if("3".equalsIgnoreCase(appid)) {
								 * if(officialName
								 * !=null&&!officialName.equalsIgnoreCase("")) {
								 * estampDTO.setPartyNamedsply(officialName);
								 * 
								 * } else { estampDTO.setPartyNamedsply("--"); }
								 * estampDTO.setPartyAddress(officialAddr); }
								 * else { estampDTO.setPartyNamedsply(name);
								 * estampDTO.setPartyAddress(addrs); }
								 * estampDTO.setPartyCountryName(cntry);
								 * estampDTO.setPartyStateName(state); estampDTO
								 * .setPartyDistrictName(district);
								 * 
								 * estampDTO.setPartyFatherName(fathrNme);
								 * estampDTO.setPartyPersons(noOfPrsns); } else
								 * { estampDTO.setPartyNamedsply(orgName);
								 * estampDTO.setPartyCountryName(cntry);
								 * estampDTO.setPartyStateName(state); estampDTO
								 * .setPartyDistrictName(district);
								 * estampDTO.setPartyAddress(addrs);
								 * estampDTO.setPartyAuthPersonName
								 * (authPersonName);
								 * estampDTO.setPartyPersons(noOfPrsns);
								 * estampDTO.setIsAuthparty(1);
								 * 
								 * }
								 * 
								 * } } }
								 * 
								 * } else { estampDTO.setPartyNamedsply("-");
								 * estampDTO.setPartyCountryName("-");
								 * estampDTO.setPartyStateName("-");
								 * estampDTO.setPartyDistrictName("-");
								 * estampDTO.setPartyAddress("-");
								 * estampDTO.setPartyFatherName("-");
								 * estampDTO.setPartyPersons("-");
								 * 
								 * }
								 */
								// vinay added for initition adoption
								/*
								 * ArrayList adoptdtls = new ArrayList();
								 * ArrayList comp95 = new ArrayList(); adoptdtls
								 * =bo.getPartyAdoptDtls(eForm.getMainTxnId(),
								 * language); if (adoptdtls.size() > 0) { for
								 * (int i = 0; i < partydtls.size(); i++) {
								 * comp95.add((ArrayList) adoptdtls.get(i)); if
								 * (comp95 != null && comp95.size() > 0) { for
								 * (int k = 0; k < comp6.size(); k++) {
								 * ArrayList compSub = (ArrayList) comp95
								 * .get(k);
								 * 
								 * String name = (String) compSub.get(0); String
								 * cntry = (String) compSub.get(1); String state
								 * = (String) compSub.get(2); String district =
								 * (String) compSub .get(3); String addrs =
								 * (String) compSub.get(4); String fathrNme =
								 * (String) compSub .get(5); String noOfPrsns =
								 * (String) compSub .get(6); String orgName =
								 * (String) compSub .get(7);
								 * 
								 * if (orgName != null) {
								 * estampDTO.setAdoptNameDisplay(orgName);
								 * estampDTO.setAdoptCountryName(cntry);
								 * estampDTO.setAdoptStateName(state); estampDTO
								 * .setAdoptDistrictName(district);
								 * estampDTO.setAdoptAddress(addrs);
								 * estampDTO.setPartyAuthPersonName(name);
								 * estampDTO.setAdoptNoPerson(noOfPrsns); } else
								 * { estampDTO.setAdoptNameDisplay(name);
								 * estampDTO.setAdoptCountryName(cntry);
								 * estampDTO.setAdoptStateName(state); estampDTO
								 * .setAdoptDistrictName(district);
								 * estampDTO.setAdoptAddress(addrs);
								 * estampDTO.setAdoptFatherName(fathrNme);
								 * estampDTO.setAdoptNoPerson(noOfPrsns);
								 * estampDTO.setIsAdoption("Y"); }
								 * 
								 * 
								 * if (orgName.equalsIgnoreCase(null)){
								 * eForm.setPartyNamedsply(name);
								 * eForm.setPartyCountryName(cntry);
								 * eForm.setPartyStateName(state);
								 * eForm.setPartyDistrictName(district);
								 * eForm.setPartyAddress(addrs);
								 * eForm.setPartyFatherName(fathrNme);
								 * eForm.setPartyPersons(noOfPrsns); }else{
								 * eForm.setPartyNamedsply(orgName);
								 * eForm.setPartyCountryName(cntry);
								 * eForm.setPartyStateName(state);
								 * eForm.setPartyDistrictName(district);
								 * eForm.setPartyAddress(addrs);
								 * eForm.setPartyAuthPersonName(name);
								 * eForm.setPartyPersons(noOfPrsns);
								 * eForm.setIsAuthparty(1);
								 * 
								 * }
								 * 
								 * 
								 * } } }
								 * 
								 * } else { estampDTO.setAdoptNameDisplay("-");
								 * estampDTO.setAdoptCountryName("-");
								 * estampDTO.setAdoptStateName("-");
								 * estampDTO.setAdoptDistrictName("-");
								 * estampDTO.setAdoptAddress("-");
								 * estampDTO.setAdoptFatherName("-");
								 * estampDTO.setAdoptNoPerson("-");
								 * estampDTO.setIsAdoption("N"); }
								 */

								// end
								// e-code details.
								ArrayList ecodedtls = new ArrayList();
								ArrayList comp7 = new ArrayList();
								ecodedtls = bo.getEcodeDtls(TxnRequestId, language);
								if (ecodedtls.size() > 0) {
									
									for (int i = 0; i < ecodedtls.size(); i++) {
										comp7.add((ArrayList) ecodedtls.get(i));
										if (comp7 != null && comp7.size() > 0) {
											for (int k = 0; k < comp7.size(); k++) {
												ArrayList compSub = (ArrayList) comp7.get(k);
												
												String ecode = (String) compSub.get(0);
												String amt = (String) compSub.get(1);
												String estampType = (String) compSub.get(2);
												String estampDate = (String) compSub.get(3);
												String issuedBy = (String) compSub.get(4);
												String offc = (String) compSub.get(5);
												String place = (String) compSub.get(6);
												String validityDt = (String) compSub.get(7);
												estampDTO.setEcode(ecode);
												estampDTO.getDutyCalculationDTO().setTotalDisplay(amt);
												estampDTO.setEstampType(estampType);
												estampDTO.setCurrentDate(estampDate);
												estampDTO.setUserName(issuedBy);
												estampDTO.setOfficeName(offc);
												estampDTO.setIssuedPlace(place);
												estampDTO.setEstampValidity(validityDt);
												
											}
										}
									}
									
								}
								if (request.getAttribute("sourceModFlag").equals("PV")) {
									forwardJsp = "estampToRegInit";
								} else if ((request.getAttribute("sourceModFlag").equals("PVN"))) {
									forwardJsp = "estampToRegNonProp";
								}
								
							}
						}
						if (checkEstamp) {
							String var = getServlet().getServletContext().getRealPath("") + "/images/header img.jpg";
							bo.getDutyDetailsInitiation(regInitId, estampDTO, "I");
							bo.updateInitationLanguage(regInitId, language);
							String EstampPdfPath = bo.generateEstampPDF(estampDTO, language, var, regInitId);
							request.setAttribute("eStampPdfPath", EstampPdfPath);
						} else {
							request.setAttribute("noECode", "NA");
							if (request.getAttribute("sourceModFlag").equals("PV")) {
								forwardJsp = "estampToRegInit";
							} else if ((request.getAttribute("sourceModFlag").equals("PVN"))) {
								forwardJsp = "estampToRegNonProp";
							}
						}
					} catch (Exception e) {
						session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
						forwardJsp = "failureEstamp";
						logger.debug("ERROR IN CREATING ESTAMP FROM INITATION " + e);
					}
				}
				
			}
			
			if (request.getParameter("flag") != null) {
				if (request.getParameter("flag").equalsIgnoreCase("fromCompletion")) {
					try {
						
						logger.debug("FROM COMPLETION TO ESTAMP CODE GENERATION");
						EstampDTO estampDTO = new EstampDTO();
						estampDTO.setUid(userId);
						String disttId = (String) request.getAttribute("eStampRegDistId");
						String amnt = (String) request.getAttribute("eStampRegAmnt");
						String func_id = (String) request.getAttribute("eStampRegFuncId");
						String purpose = (String) request.getAttribute("eStampRegPurpose");
						String regInitId = (String) request.getAttribute("eStampRegId");
						
						if (disttId != null || disttId != "") {
							String eCode = null;
							
							// names and office details//
							String type = objEstampBO.gettype(userId); // --added
							// by
							// satbir
							// kumar--
							
							logger.debug(type);
							
							/*
							 * String roleId = bo.getRole(userId); String srrole
							 * = "GR1356609170484"; String drrole =
							 * "GR1356671177515"; String sprole =
							 * "GR1358488496205"; String rurole =
							 * "GR1357368369828"; String spbankrole =
							 * "GR1359453019113";
							 * 
							 * eForm.getObjDashBoardDTO().setRole(roleId);
							 */

							if ("2".equalsIgnoreCase(type)) {
								ArrayList userdet = new ArrayList();
								ArrayList comp2 = new ArrayList();
								userdet = bo.getruName(userId, language);
								if (userdet.size() > 0) {
									for (int i = 0; i < userdet.size(); i++) {
										comp2.add((ArrayList) userdet.get(i));
										if (comp2 != null && comp2.size() > 0) {
											for (int k = 0; k < comp2.size(); k++) {
												ArrayList compSub = (ArrayList) comp2.get(k);
												
												String name = (String) compSub.get(0);
												String cid = (String) compSub.get(1);
												String sid = (String) compSub.get(2);
												String did = (String) compSub.get(3);
												String dname = (String) compSub.get(4);
												
												estampDTO.setUserName(name);
												if (cid.equalsIgnoreCase("1")) {
													if (sid.equalsIgnoreCase("1")) {
														estampDTO.setIssuedPlace(dname);
														
													} else {
														if ("en".equalsIgnoreCase(language)) {
															estampDTO.setIssuedPlace("Others");
														} else {
															estampDTO.setIssuedPlace("अन्य");
														}
													}
													
												} else {
													if ("en".equalsIgnoreCase(language)) {
														estampDTO.setIssuedPlace("Others");
													} else {
														estampDTO.setIssuedPlace("अन्य");
													}
												}
												
											}
										}
									}
								}
								
								estampDTO.setOfficeName("-");
							}

							else if ("3".equalsIgnoreCase(type)) {
								String spname = bo.getspName(userId);
								String licNo = bo.getspLicenseNo(userId);
								ArrayList spdtls = new ArrayList();
								ArrayList comp3 = new ArrayList();
								spdtls = bo.getspDtls(userId, language);
								if (spdtls.size() > 0) {
									for (int i = 0; i < spdtls.size(); i++) {
										comp3.add((ArrayList) spdtls.get(i));
										if (comp3 != null && comp3.size() > 0) {
											for (int k = 0; k < comp3.size(); k++) {
												ArrayList compSub = (ArrayList) comp3.get(k);
												
												String ofc = (String) compSub.get(0);
												String plc = (String) compSub.get(1);
												String tehsil = (String) compSub.get(2);
												estampDTO.setOfficeName(ofc + " " + tehsil + " " + plc);
												estampDTO.setIssuedPlace(plc);
												estampDTO.setUserName(spname + "/" + licNo);
											}
										}
									}
									
								}
							}

							else if ("4".equalsIgnoreCase(type) || "7".equalsIgnoreCase(type) || "5".equalsIgnoreCase(type)) {
								
								ArrayList spbankdtls = new ArrayList();
								ArrayList comp4 = new ArrayList();
								spbankdtls = bo.getspbnkDtls(userId, language);
								if (spbankdtls.size() > 0) {
									for (int i = 0; i < spbankdtls.size(); i++) {
										comp4.add((ArrayList) spbankdtls.get(i));
										if (comp4 != null && comp4.size() > 0) {
											for (int k = 0; k < comp4.size(); k++) {
												ArrayList compSub = (ArrayList) comp4.get(k);
												
												String uname = (String) compSub.get(0);
												String ofc = (String) compSub.get(1);
												String plc = (String) compSub.get(2);
												String tehsil = (String) compSub.get(3);
												estampDTO.setOfficeName(ofc + " " + tehsil + " " + plc);
												estampDTO.setIssuedPlace(plc);
												estampDTO.setUserName(uname);
											}
										}
									}
									
								}
							}

							else if ("I".equalsIgnoreCase(type)) {
								ArrayList iudtls = new ArrayList();
								ArrayList comp4 = new ArrayList();
								String officeId = (String) session.getAttribute("loggedToOffice");
								iudtls = bo.getiuDtls(userId, officeId, language);
								if (iudtls.size() > 0) {
									for (int i = 0; i < iudtls.size(); i++) {
										comp4.add((ArrayList) iudtls.get(i));
										if (comp4 != null && comp4.size() > 0) {
											for (int k = 0; k < comp4.size(); k++) {
												ArrayList compSub = (ArrayList) comp4.get(k);
												
												String ofc = (String) compSub.get(0);
												String plc = (String) compSub.get(1);
												String uname = (String) compSub.get(2);
												estampDTO.setOfficeName(ofc);
												estampDTO.setIssuedPlace(plc);
												estampDTO.setUserName(uname);
											}
										}
									}
									
								}
							}
							
							checkEstamp = bo.checkEstamp(regInitId, "C");
							if (checkEstamp) {
								String ecodeTxnId = bo.getEcode(disttId, amnt, func_id, purpose, estampDTO, regInitId, "C", language);
								request.setAttribute("eCode", ecodeTxnId);
								String TxnRequestId = bo.getEstampTxnId(ecodeTxnId.split("~")[0]);
								estampDTO.setMainTxnId(TxnRequestId);
								ArrayList second = new ArrayList();
								ArrayList comp1 = new ArrayList();
								second = bo.getDeedDtls(TxnRequestId, language);
								if (second.size() > 0) {
									for (int i = 0; i < second.size(); i++) {
										comp1.add((ArrayList) second.get(i));
										if (comp1 != null && comp1.size() > 0) {
											for (int k = 0; k < comp1.size(); k++) {
												ArrayList compSub = (ArrayList) comp1.get(k);
												String deed = (String) compSub.get(0);
												String inst = (String) compSub.get(1);
												String purpose1 = (String) compSub.get(2);
												String instDiscp = (String) compSub.get(3);
												logger.debug("the deed is.........................." + deed);
												logger.debug("the inst is.........................." + inst);
												logger.debug("the purpose is.........................." + purpose);
												estampDTO.getDutyCalculationDTO().setDeedType(deed);
												estampDTO.getDutyCalculationDTO().setInstDescription(instDiscp);
												estampDTO.getDutyCalculationDTO().setInstDescription(instDiscp);
												estampDTO.getInstDTO().setInstType(inst);
												estampDTO.setEstampPurpose(purpose1);
											}
										}
									}
								}
								// names and office details//
								// String type = objEstampBO.gettype(userId);
								// //--added by satbir kumar--
								
								// logger.debug(type);
								
								/*
								 * String roleId = bo.getRole(userId); String
								 * srrole = "GR1356609170484"; String drrole =
								 * "GR1356671177515"; String sprole =
								 * "GR1358488496205"; String rurole =
								 * "GR1357368369828"; String spbankrole =
								 * "GR1359453019113";
								 * 
								 * eForm.getObjDashBoardDTO().setRole(roleId);
								 */

								/*
								 * if("2".equalsIgnoreCase(type)) { ArrayList
								 * userdet = new ArrayList(); ArrayList comp2 =
								 * new ArrayList(); userdet =
								 * bo.getruName(userId,language); if
								 * (userdet.size() > 0) { for (int i = 0; i <
								 * userdet.size(); i++) { comp2.add((ArrayList)
								 * userdet.get(i)); if (comp2 != null &&
								 * comp2.size() > 0) { for (int k = 0; k <
								 * comp2.size(); k++) { ArrayList compSub =
								 * (ArrayList) comp2 .get(k);
								 * 
								 * String name = (String) compSub .get(0);
								 * String cid = (String) compSub .get(1); String
								 * sid = (String) compSub .get(2); String did =
								 * (String) compSub .get(3); String dname =
								 * (String) compSub .get(4);
								 * 
								 * eForm.setUserName(name); if
								 * (cid.equalsIgnoreCase("1")) { if
								 * (sid.equalsIgnoreCase("1")) {
								 * eForm.setIssuedPlace(dname);
								 * 
								 * } else { eForm .setIssuedPlace("Others"); }
								 * 
								 * } else { eForm.setIssuedPlace("Others"); }
								 * 
								 * } } } }
								 * 
								 * eForm.setOfficeName("-"); }
								 */

								/*
								 * else if ("3".equalsIgnoreCase(type)) { String
								 * spname = bo.getspName(userId); ArrayList
								 * spdtls = new ArrayList(); ArrayList comp3 =
								 * new ArrayList(); spdtls =
								 * bo.getspDtls(userId,language); if
								 * (spdtls.size() > 0) { for (int i = 0; i <
								 * spdtls.size(); i++) { comp3.add((ArrayList)
								 * spdtls.get(i)); if (comp3 != null &&
								 * comp3.size() > 0) { for (int k = 0; k <
								 * comp3.size(); k++) { ArrayList compSub =
								 * (ArrayList) comp3 .get(k);
								 * 
								 * String ofc = (String) compSub .get(0); String
								 * plc = (String) compSub .get(1);
								 * eForm.setOfficeName(ofc);
								 * eForm.setIssuedPlace(plc);
								 * eForm.setUserName(spname); } } }
								 * 
								 * } }
								 */

								/*
								 * elseif("4".equalsIgnoreCase(type)||"7".
								 * equalsIgnoreCase
								 * (type)||"5".equalsIgnoreCase(type)) {
								 * 
								 * ArrayList spbankdtls = new ArrayList();
								 * ArrayList comp4 = new ArrayList(); spbankdtls
								 * = bo.getspbnkDtls(userId,language); if
								 * (spbankdtls.size() > 0) { for (int i = 0; i <
								 * spbankdtls.size(); i++) {
								 * comp4.add((ArrayList) spbankdtls.get(i)); if
								 * (comp4 != null && comp4.size() > 0) { for
								 * (int k = 0; k < comp4.size(); k++) {
								 * ArrayList compSub = (ArrayList) comp4
								 * .get(k);
								 * 
								 * String uname = (String) compSub .get(0);
								 * String ofc = (String) compSub .get(1); String
								 * plc = (String) compSub .get(2);
								 * eForm.setOfficeName(ofc);
								 * eForm.setIssuedPlace(plc);
								 * eForm.setUserName(uname); } } }
								 * 
								 * } }
								 */

								// else if("I".equalsIgnoreCase(type))
								// {
								ArrayList iudtls = new ArrayList();
								ArrayList comp4 = new ArrayList();
								String officeId = (String) session.getAttribute("loggedToOffice");
								iudtls = bo.getiuDtls(userId, officeId, language);
								if (iudtls.size() > 0) {
									for (int i = 0; i < iudtls.size(); i++) {
										comp4.add((ArrayList) iudtls.get(i));
										if (comp4 != null && comp4.size() > 0) {
											for (int k = 0; k < comp4.size(); k++) {
												ArrayList compSub = (ArrayList) comp4.get(k);
												
												String ofc = (String) compSub.get(0);
												String plc = (String) compSub.get(1);
												String uname = (String) compSub.get(2);
												estampDTO.setOfficeName(ofc);
												estampDTO.setIssuedPlace(plc);
												estampDTO.setUserName(uname);
											}
										}
									}
									
								}
								// }
								
								// party details--applicant//
								/*
								 * ArrayList appdtls = new ArrayList();
								 * ArrayList comp5 = new ArrayList(); appdtls =
								 * bo.getAppDtls(TxnRequestId,language); if
								 * (appdtls.size() > 0) { for (int i = 0; i <
								 * appdtls.size(); i++) { comp5.add((ArrayList)
								 * appdtls.get(i)); if (comp5 != null &&
								 * comp5.size() > 0) { for (int k = 0; k <
								 * comp5.size(); k++) { ArrayList compSub =
								 * (ArrayList) comp5 .get(k);
								 * 
								 * String name = (String) compSub.get(0); String
								 * cntry = (String) compSub.get(1); String state
								 * = (String) compSub.get(2); String district =
								 * (String) compSub .get(3); String addrs =
								 * (String) compSub.get(4); String fathrNme =
								 * (String) compSub .get(5); String noOfPrsns =
								 * (String) compSub .get(6); String orgName =
								 * (String) compSub .get(7); String disId =
								 * (String) compSub.get(8); String
								 * authPersonName = (String) compSub.get(10);
								 * String appid=(String) compSub .get(11);
								 * String officialName=(String) compSub
								 * .get(12); String officialAddr=(String)
								 * compSub .get(13); orgName = orgName==
								 * null?"":orgName; if
								 * (orgName.equalsIgnoreCase("")) {
								 * if("3".equalsIgnoreCase(appid)) {
								 * if(officialName
								 * !=null&&!officialName.equalsIgnoreCase("")) {
								 * estampDTO.setAppNamedsply(officialName); }
								 * else { estampDTO.setAppNamedsply("--"); }
								 * estampDTO.setAppAddress(officialAddr); } else
								 * { estampDTO.setAppNamedsply(name);
								 * estampDTO.setAppAddress(addrs); }
								 * estampDTO.setAppCountryName(cntry);
								 * estampDTO.setAppStateName(state);
								 * estampDTO.setAppDistrictName(district);
								 * 
								 * estampDTO.setAppFatherName(fathrNme);
								 * estampDTO.setAppPersons(noOfPrsns);
								 * estampDTO.setAppDistrict(disId); } else {
								 * estampDTO.setAppNamedsply(orgName);
								 * estampDTO.setAppCountryName(cntry);
								 * estampDTO.setAppStateName(state);
								 * estampDTO.setAppDistrictName(district);
								 * estampDTO.setAppAddress(addrs);
								 * estampDTO.setAppAuthPersonName
								 * (authPersonName);
								 * estampDTO.setAppPersons(noOfPrsns);
								 * estampDTO.setAppDistrict(disId);
								 * estampDTO.setIsAuthapp(1);
								 * 
								 * }
								 * 
								 * } } }
								 * 
								 * }
								 * 
								 * // party details--party// ArrayList partydtls
								 * = new ArrayList(); ArrayList comp6 = new
								 * ArrayList(); partydtls =
								 * bo.getPartyDtls(TxnRequestId,language); if
								 * (partydtls.size() > 0) { for (int i = 0; i <
								 * partydtls.size(); i++) {
								 * comp6.add((ArrayList) partydtls.get(i)); if
								 * (comp6 != null && comp6.size() > 0) { for
								 * (int k = 0; k < comp6.size(); k++) {
								 * ArrayList compSub = (ArrayList) comp6
								 * .get(k);
								 * 
								 * String name = (String) compSub.get(0); String
								 * cntry = (String) compSub.get(1); String state
								 * = (String) compSub.get(2); String district =
								 * (String) compSub .get(3); String addrs =
								 * (String) compSub.get(4); String fathrNme =
								 * (String) compSub .get(5); String noOfPrsns =
								 * (String) compSub .get(6); String orgName =
								 * (String) compSub .get(7);
								 * 
								 * String authPersonName = (String) compSub
								 * .get(8); String appid=(String) compSub
								 * .get(9); String officialName=(String) compSub
								 * .get(10); String officialAddr=(String)
								 * compSub .get(11); orgName = orgName==
								 * null?"":orgName; if
								 * (orgName.equalsIgnoreCase("")) {
								 * if("3".equalsIgnoreCase(appid)) {
								 * if(officialName
								 * !=null&&!officialName.equalsIgnoreCase("")) {
								 * estampDTO.setPartyNamedsply(officialName); }
								 * else { estampDTO.setPartyNamedsply("--"); }
								 * estampDTO.setPartyAddress(officialAddr); }
								 * else { estampDTO.setPartyNamedsply(name);
								 * estampDTO.setPartyAddress(addrs); }
								 * estampDTO.setPartyCountryName(cntry);
								 * estampDTO.setPartyStateName(state); estampDTO
								 * .setPartyDistrictName(district);
								 * 
								 * estampDTO.setPartyFatherName(fathrNme);
								 * estampDTO.setPartyPersons(noOfPrsns); } else
								 * { estampDTO.setPartyNamedsply(orgName);
								 * estampDTO.setPartyCountryName(cntry);
								 * estampDTO.setPartyStateName(state); estampDTO
								 * .setPartyDistrictName(district);
								 * estampDTO.setPartyAddress(addrs);
								 * estampDTO.setPartyAuthPersonName
								 * (authPersonName);
								 * estampDTO.setPartyPersons(noOfPrsns);
								 * estampDTO.setIsAuthparty(1);
								 * 
								 * }
								 * 
								 * } } }
								 * 
								 * } else { estampDTO.setPartyNamedsply("-");
								 * estampDTO.setPartyCountryName("-");
								 * estampDTO.setPartyStateName("-");
								 * estampDTO.setPartyDistrictName("-");
								 * estampDTO.setPartyAddress("-");
								 * estampDTO.setPartyFatherName("-");
								 * estampDTO.setPartyPersons("-");
								 * 
								 * }
								 */
								// e-code details.
								// in compltion for adoption deed
								/*
								 * ArrayList adoptdtls = new ArrayList();
								 * ArrayList comp95 = new ArrayList(); adoptdtls
								 * =bo.getPartyAdoptDtls(eForm.getMainTxnId(),
								 * language); if (adoptdtls.size() > 0) { for
								 * (int i = 0; i < partydtls.size(); i++) {
								 * comp95.add((ArrayList) adoptdtls.get(i)); if
								 * (comp95 != null && comp95.size() > 0) { for
								 * (int k = 0; k < comp6.size(); k++) {
								 * ArrayList compSub = (ArrayList) comp95
								 * .get(k);
								 * 
								 * String name = (String) compSub.get(0); String
								 * cntry = (String) compSub.get(1); String state
								 * = (String) compSub.get(2); String district =
								 * (String) compSub .get(3); String addrs =
								 * (String) compSub.get(4); String fathrNme =
								 * (String) compSub .get(5); String noOfPrsns =
								 * (String) compSub .get(6); String orgName =
								 * (String) compSub .get(7);
								 * 
								 * if (orgName != null) {
								 * estampDTO.setAdoptNameDisplay(orgName);
								 * estampDTO.setAdoptCountryName(cntry);
								 * estampDTO.setAdoptStateName(state); estampDTO
								 * .setAdoptDistrictName(district);
								 * estampDTO.setAdoptAddress(addrs);
								 * estampDTO.setPartyAuthPersonName(name);
								 * estampDTO.setAdoptNoPerson(noOfPrsns); } else
								 * { estampDTO.setAdoptNameDisplay(name);
								 * estampDTO.setAdoptCountryName(cntry);
								 * estampDTO.setAdoptStateName(state); estampDTO
								 * .setAdoptDistrictName(district);
								 * estampDTO.setAdoptAddress(addrs);
								 * estampDTO.setAdoptFatherName(fathrNme);
								 * estampDTO.setAdoptNoPerson(noOfPrsns);
								 * estampDTO.setIsAdoption("Y"); }
								 * 
								 * 
								 * if (orgName.equalsIgnoreCase(null)){
								 * eForm.setPartyNamedsply(name);
								 * eForm.setPartyCountryName(cntry);
								 * eForm.setPartyStateName(state);
								 * eForm.setPartyDistrictName(district);
								 * eForm.setPartyAddress(addrs);
								 * eForm.setPartyFatherName(fathrNme);
								 * eForm.setPartyPersons(noOfPrsns); }else{
								 * eForm.setPartyNamedsply(orgName);
								 * eForm.setPartyCountryName(cntry);
								 * eForm.setPartyStateName(state);
								 * eForm.setPartyDistrictName(district);
								 * eForm.setPartyAddress(addrs);
								 * eForm.setPartyAuthPersonName(name);
								 * eForm.setPartyPersons(noOfPrsns);
								 * eForm.setIsAuthparty(1);
								 * 
								 * }
								 * 
								 * 
								 * } } }
								 * 
								 * } else { estampDTO.setAdoptNameDisplay("-");
								 * estampDTO.setAdoptCountryName("-");
								 * estampDTO.setAdoptStateName("-");
								 * estampDTO.setAdoptDistrictName("-");
								 * estampDTO.setAdoptAddress("-");
								 * estampDTO.setAdoptFatherName("-");
								 * estampDTO.setAdoptNoPerson("-");
								 * estampDTO.setIsAdoption("N"); }
								 */
								// end
								ArrayList ecodedtls = new ArrayList();
								ArrayList comp7 = new ArrayList();
								ecodedtls = bo.getEcodeDtls(TxnRequestId, language);
								logger.debug("ecode set " + ecodedtls);
								if (ecodedtls.size() > 0) {
									for (int i = 0; i < ecodedtls.size(); i++) {
										comp7.add((ArrayList) ecodedtls.get(i));
										if (comp7 != null && comp7.size() > 0) {
											for (int k = 0; k < comp7.size(); k++) {
												ArrayList compSub = (ArrayList) comp7.get(k);
												
												String ecode = (String) compSub.get(0);
												logger.debug("ecode set " + ecode);
												String amt = (String) compSub.get(1);
												String estampType = (String) compSub.get(2);
												String estampDate = (String) compSub.get(3);
												String issuedBy = (String) compSub.get(4);
												String offc = (String) compSub.get(5);
												String place = (String) compSub.get(6);
												String validityDt = (String) compSub.get(7);
												estampDTO.setEcode(ecode);
												estampDTO.getDutyCalculationDTO().setTotalDisplay(amt);
												estampDTO.setEstampType(estampType);
												estampDTO.setCurrentDate(estampDate);
												estampDTO.setUserName(issuedBy);
												estampDTO.setOfficeName(offc);
												estampDTO.setIssuedPlace(place);
												estampDTO.setEstampValidity(validityDt);
												
												// PDF CODE
												
											}
										}
									}
									
								}
							} else {
								request.setAttribute("noECode", objEstampBO.getEcode(regInitId, "C"));
								
							}
							forwardJsp = "estampToRegComp";
							
						}
						if (checkEstamp) {
							String var = getServlet().getServletContext().getRealPath("") + "/images/header img.jpg";
							bo.getDutyDetailsInitiation(regInitId, estampDTO, "C");
							String lang = bo.getLangauge(regInitId);
							if ("English".equalsIgnoreCase(lang)) {
								lang = "en";
							} else {
								lang = "hi";
							}
							String EstampPdfPath = bo.generateEstampPDF(estampDTO, lang, var, regInitId);
							
							request.setAttribute("eStampPdfPath", EstampPdfPath);
							bo.updateEstampFlag(regInitId);
						}

						else {
							request.setAttribute("noECode", "NA");
						}
					} catch (Exception e) {
						e.printStackTrace();
						session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
						forwardJsp = "failureEstamp";
						logger.debug("ERROR IN CREATING ESTAMP FROM COMPLITION " + e);
					}
				}
				
			}
		}
		logger.debug("------>******" + forwardJsp);
		
		return mapping.findForward(forwardJsp);
		
	}
	
	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}
	
	private String removeFile(String fileName, String filePath) {
		// String filePath = "D:/upload/";
		// String filePath = getServlet().getServletContext().getRealPath("")
		// + "/temp/";
		File newFile = new File(filePath + fileName);
		newFile.delete();
		
		return "";
	}
	
	private boolean uploadFile(FormFile filetobeuploaded, String fileName, String filePath) {
		
		try {
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			
			File newFile = new File(filePath, fileName);
			logger.info("NEW FILE NAME:-" + newFile);
			FileOutputStream fos = new FileOutputStream(newFile);
			fos.write(filetobeuploaded.getFileData());
			fos.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
	public static void downloadDocument(HttpServletResponse res, byte[] docContents, String fileName) {
		try {
			OutputStream os = res.getOutputStream();
			res.setContentType("application/download");
			res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			os.write(docContents);
			os.flush();
			os.close();
		} catch (Exception e) {
		}
	}
	
}
