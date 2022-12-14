/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2013-14
 *==============================================================================
 *
 * File Name   :   CommonAction.java
 * Author      :   Imran Shaik
 * Description :   Represents the Common Action Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 		 
 * --------------------------------------------------------------------------------
 * 	0.1             Roopam Mehta		 26th Jul, 2013	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.newreginit.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.itextpdf.text.log.SysoLogger;
import com.itextpdf.text.pdf.PdfDictionary;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource; /*import com.wipro.igrs.AadharEsign.Helper.EsignXMLCreator;
 import com.wipro.igrs.AadharEsign.Helper.JaxbOperations;
 import com.wipro.igrs.AadharEsign.Helper.ReadWritePDF;
 import com.wipro.igrs.AadharEsign.pdf.PKCS7EmbeddingInPDF;
 import com.wipro.igrs.AadharEsign.responseData.AspEsignResp;
 import com.wipro.igrs.AadharEsign.responseData.EsignResp;
 import com.wipro.igrs.AadharEsign.validate.AspEsignXMLResponse;
 import com.wipro.igrs.AadharEsign.validate.EsignException;
 import com.wipro.igrs.AadharEsign.validate.ValidateEsignXmlResponse;
 import com.wipro.igrs.AadharEsign.validate.ValidateXmlSignature;*/
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteMakerDTO;
import com.wipro.igrs.aadhar.domain.authentication.subservice.Demo;
import com.wipro.igrs.aadhar.domain.authentication.subservice.EncPidResult;
import com.wipro.igrs.aadhar.domain.authentication.subservice.Pi;
import com.wipro.igrs.aadhar.domain.authentication.subservice.Pid;
import com.wipro.igrs.aadhar.util.subservice.AuthUtil;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.clr.services.DownloadService; //import com.wipro.igrs.clr.services.FormVIBPdfGeneration;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.common.form.CommonForm;
import com.wipro.igrs.deedDraft.bd.DeedDraftBD;
import com.wipro.igrs.empmgmt.bd.CommonBD;
import com.wipro.igrs.login.loadSingleton.LoadMenuExternal;
import com.wipro.igrs.newPropvaluation.bo.PropertyValuationBO;
import com.wipro.igrs.newPropvaluation.dto.PropertyValuationDTO;
import com.wipro.igrs.newdutycalculation.bo.DutyCalculationBO;
import com.wipro.igrs.newdutycalculation.form.DutyCalculationForm;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.AadharDTO;
import com.wipro.igrs.newreginit.dto.AadharRespDTO;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.dto.DeclarationDTO;
import com.wipro.igrs.newreginit.dto.FormOneDTO;
import com.wipro.igrs.newreginit.dto.FormUserEnterableDTO;
import com.wipro.igrs.newreginit.dto.MetaPojo;
import com.wipro.igrs.newreginit.dto.PartyDetailsDTO;
import com.wipro.igrs.newreginit.dto.RegCommonDTO;
import com.wipro.igrs.newreginit.dto.RegCompletDTO;
import com.wipro.igrs.newreginit.dto.UsesPojo;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.newreginit.form.RegCompletionForm;
import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;

public class CommonAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(CommonAction.class);
	private static Proxy proxy;

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		HashMap map = null;
		HashMap map2 = null;
		String forward = "";
		RegCommonBO commonBo = new RegCommonBO();
		RegCommonForm regForm;
		RegCompletionForm regCompForm;
		PropertyValuationBO propBO = new PropertyValuationBO();

		RegCompCheckerBD chkbd = new RegCompCheckerBD();
		RegCommonBD commonBd = new RegCommonBD();
		String userId = (String) session.getAttribute("UserId");
		String hdnAppTxnId = request.getParameter("hdnAppTxnId");

		

		

		if (request.getAttribute("regFormDashboard") != null) {
			regForm = (RegCommonForm) request.getAttribute("regFormDashboard");
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());
			// request.setAttribute("hidnRegTxnId", regForm.getHidnRegTxnId());
		} else
			regForm = (RegCommonForm) form;
		if (request.getParameter("confirmation") != null) {
			if (request.getParameter("confirmation").equalsIgnoreCase("Y")) {
				regForm.setActionName(RegInitConstant.NO_ACTION);
			}
		}
		if (request.getParameter("multipleProps") != null) {
			if (request.getParameter("multipleProps").equalsIgnoreCase("Y")) {
				regForm.setActionName(RegInitConstant.NO_ACTION);
			}
		}
		// Added by ankit for aadhar authentication bypass for CLR --- start
		/*
		 * String sts =
		 * commonBo.getClrFlagForAadhar(RegInitConstant.ADHAR_BYPASS_MODULE_NAME_1
		 * , RegInitConstant.ADHAR_STATUS_DESC_1); if (sts != null &&
		 * !sts.isEmpty()) { logger.info(" status flag for aadhar bypass : " +
		 * regForm.getClrFlagForAadhar()); if ("CLR".equalsIgnoreCase(sts)) {
		 * regForm.setClrFlagForAadhar("Y"); } } else {
		 */
		regForm.setClrFlagForAadhar("N");
		// }
		logger.info(" CLR flag for aadhar bypass : " + regForm.getClrFlagForAadhar());
		// Added by ankit for aadhar authentication bypass for CLR --- end
		// DutyCalculationForm pform = null;
		if (request.getAttribute("propDTO") != null) {
			PropertyValuationDTO propDTO = (PropertyValuationDTO) request.getAttribute("propDTO");
			regForm.setIsMultiplePropsFlag(propDTO.getIsMultipleProperty());
			// regForm.setClrFlg(propDTO.getClrFlag());

			/*
			 * String instClrFlag = "N";
			 * 
			 * instClrFlag =
			 * commonBo.getInstrumentClrFlag(Integer.toString(regForm
			 * .getInstID()));
			 * 
			 * if(instClrFlag != null && !instClrFlag.isEmpty()){
			 * 
			 * if (instClrFlag.equalsIgnoreCase("Y")){
			 * regForm.setClrFlg(propDTO.getClrFlag());
			 * 
			 * }
			 * 
			 * else{
			 * 
			 * regForm.setClrFlg("N"); } }
			 */
			if (regForm.getClrFlg() != null && !regForm.getClrFlg().isEmpty()) {
				if (regForm.getClrFlg().equalsIgnoreCase("Y")) {
					// regForm.setListID("7");//commented by ankit for aadhar
					// bypass
					// regForm.setListIDTrns("7");//commented by ankit for
					// aadhar bypass
				}
			}
			// Added for aadhar integration by Ankit
		} else {
			regForm.setIsMultiplePropsFlag(0);
		}
		RegCommonDTO commonDto;
		if (regForm.getCommonDto() != null) {
			commonDto = regForm.getCommonDto();
		} else {
			commonDto = new RegCommonDTO();
			commonDto.setState(new ArrayList());
			commonDto.setDistrict(new ArrayList());
			commonDto.setIndstate(new ArrayList());
			commonDto.setInddistrict(new ArrayList());
		}
		String languageLocale = "hi";
		if (session.getAttribute("languageLocale") != null) {
			languageLocale = (String) session.getAttribute("languageLocale");
		}
		if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			session.setAttribute("modName", RegInitConstant.MODNAME_ENGLISH);
		} else {
			session.setAttribute("modName", RegInitConstant.MODNAME_HINDI);
		}
		commonDto.setLanguage(languageLocale);
		// following code for clearing form beans when the module is hit first
		// time.
		if (request.getParameter("modName") != null) {
			if (request.getParameter("modName").equalsIgnoreCase("RegistrationProperty") || request.getParameter("modName").equalsIgnoreCase("Registration Process")) {
				if (regForm != null) {
					resetForm(regForm);
					if (regForm.getIsMultiplePropsFlag() == 0) {
						commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
						commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(), languageLocale));
						commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendar(), languageLocale));
						regForm.setFromAdjudication(0);
						regForm.setHidnRegTxnId("");
						regForm.setParty1OwnerCount(0);
						regForm.setParty1PoaHolderCount(0);
						regForm.setParty2OwnerCount(0);
						regForm.setParty2PoaHolderCount(0);
						regForm.setDoneeCount(0);
						regForm.setDonorCount(0);
						regForm.setBuyerCount(0);
						regForm.setSellerPoaCount(0);
						regForm.setSellerSelfCount(0);
						regForm.setOwnerCount(0);
						regForm.setPoaAccepterCount(0);
						regForm.setPoaHolderCount(0);
						regForm.setParentFunIdCashLess("");
					}
					if (regForm.getIsMultiplePropsFlag() == 1) {
						commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
						commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
						commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendarTrns(), languageLocale));
						regForm.setCountryTrns("1");
						regForm.setCountryNameTrns("INDIA");
						regForm.setStatenameTrns("1");
						regForm.setStatenameNameTrns("MADHYA PRADESH");
						regForm.setIndcountryTrns("1");
						regForm.setIndcountryNameTrns("INDIA");
						regForm.setIndstatenameTrns("1");
						regForm.setIndstatenameNameTrns("MADHYA PRADESH");
						regForm.setAuthPerCountryTrns("1");
						regForm.setAuthPerCountryNameTrns("INDIA");
						regForm.setAuthPerStatenameTrns("1");
						regForm.setAuthPerStatenameNameTrns("MADHYA PRADESH");
						regForm.setOwnerIndcountryTrns("1");
						regForm.setOwnerIndstatenameTrns("1");
						if (regForm.getHidnRegTxnId() != null) {
							String shareApp = commonBo.getApplicantShare(regForm.getHidnRegTxnId());
							if (shareApp != null) {
								if (shareApp.equalsIgnoreCase("") || shareApp.equalsIgnoreCase("null")) {
									regForm.setHdnDeclareShareCheck("N");
								} else {
									regForm.setHdnDeclareShareCheck("Y");
								}
							}
						}
					}
				}
				session.removeAttribute("commonDto");
				session.removeAttribute("roleId");
				session.removeAttribute("functionId");
				session.removeAttribute("status");
				session.removeAttribute("view");
				session.removeAttribute("regFormProp");
				// following attributes for payment
				/*
				 * session.removeAttribute("parentModName");
				 * session.removeAttribute("parentFunName");
				 * session.removeAttribute("parentFunId");
				 * session.removeAttribute("parentAmount");
				 */
			}
		}
		// end of code for clearing form beans
		// added by Shreeraj
		if (request.getParameter("modName") != null) {
			if (request.getParameter("modName").equalsIgnoreCase("Adjudicated Apllication")) {
				regForm.setAdjudicationNumber("");
				regForm.setErrorMsg("");
				forward = "searchAdju";
				return mapping.findForward(forward);
			}
		}

		regForm.setHidnUserId(userId);
		// Start:======== Added by Ankita
		if (request.getAttribute("eForm") != null) {
			DutyCalculationForm eForm = (DutyCalculationForm) request.getAttribute("eForm");
			// for adhar integration
			if (eForm.getDutyCalculationDTO().getValuationId() != null) {
				String valId = eForm.getDutyCalculationDTO().getValuationId();
				String distId = commonBo.getDistrictID(valId);
				String tehsilID = commonBo.getTehsilID(valId);
				String propTypeId = "N";
				propTypeId = commonBo.getPropTypeId(valId);
				if (!propTypeId.equalsIgnoreCase("3")) {
					regForm.setClrFlg("");
				} else {

					/*
					 * String instClrFlag = "N";
					 * 
					 * instClrFlag =
					 * commonBo.getInstrumentClrFlag(Integer.toString
					 * (regForm.getInstID()));
					 * 
					 * if(instClrFlag != null && !instClrFlag.isEmpty()){
					 * 
					 * if (instClrFlag.equalsIgnoreCase("Y")){
					 * regForm.setClrFlg(
					 * commonBo.getDistrictClrFlag(languageLocale, distId));
					 * regForm.setClrFlg(propBO.getTehsilClrFlag(languageLocale,
					 * tehsilID));
					 * 
					 * }
					 * 
					 * else{
					 * 
					 * regForm.setClrFlg("N"); } }
					 */

					regForm.setClrFlg(commonBo.getDistrictClrFlag(languageLocale, distId));
					regForm.setClrFlg(propBO.getTehsilClrFlag(languageLocale, tehsilID));
				}
			}
			if (eForm.getFromAdjudication() == 1) {
				regForm.setFromAdjudication(1);
			} else {
				regForm.setFromAdjudication(0);
			}
			if (eForm.getDutyCalculationDTO().getMainDutyTxnId() != null)
				regForm.setDuty_txn_id(Integer.parseInt(eForm.getDutyCalculationDTO().getMainDutyTxnId()));
			else
				regForm.setDuty_txn_id(0);
			logger.debug("duty txn id----------->" + regForm.getDuty_txn_id());
			if (eForm.getDutyCalculationDTO().getCancellationFlag() != null) {
				if (eForm.getDutyCalculationDTO().getCancellationFlag().equalsIgnoreCase("Y")) {
					commonBo.getCancellationLabel(Integer.toString(regForm.getDuty_txn_id()), regForm, languageLocale);
				} else {
					regForm.setCancellationLabel("");
				}
			} else {
				regForm.setCancellationLabel("");
			}
			// if(regForm.getIsMultiplePropsFlag()==0){
			// regForm.setDeedtype1(eForm.getDutyCalculationDTO().getDeedType());
			regForm.setDeedID(eForm.getDutyCalculationDTO().getDeedId());
			regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()), languageLocale));
			regForm.setInstID(eForm.getInstDTO().getInstId());
			regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()), languageLocale));
			// regForm.setInstType(eForm.getInstDTO().getInstType());
			logger.debug("inst id----------->" + regForm.getInstID());
			// regForm.setExmpID(eForm.getExempDTO().getHdnExAmount()); //comma
			// separated exemption ids.
			regForm.setExmpID(commonBo.getExempId(regForm.getDuty_txn_id()));
			regForm.setSelectedExemptionList(commonBo.getExemptionList(Integer.toString(regForm.getDuty_txn_id()), languageLocale));
			regForm.setFamilyFlag(commonBo.getFamilyFlag(Integer.toString(regForm.getDuty_txn_id())));
			String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
			if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {
				if (flags[2].trim().equalsIgnoreCase("Y")) {
					regForm.setCommonDeed(1);
				} else {
					regForm.setCommonDeed(0);
				}
				regForm.setPvReqFlag(flags[1].trim());
				regForm.setPropReqFlag(flags[0].trim());
			} else {
				regForm.setCommonDeed(0);
				regForm.setPvReqFlag("");
				regForm.setPropReqFlag("");
			}
			// }
			// regForm.setValuationId(eForm.getDutyCalculationDTO().getPropDTO().getValuationId());
			/*
			 * String distId=""; String tehslId=""; String propertyId="";
			 */
			// following code in case of Exchange deed
			// if(regForm.getExchangePropertyList().size()==0)
			// {
			// if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE ||
			// regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
			// commonBo.getExchangePropertyList(regForm,0,null);
			// commonBo.getAgriPropertyList(regForm,0,null);
			commonBo.getAllPropertyList(regForm, 0, null, languageLocale);
			// }
			/*
			 * else{
			 * 
			 * if(eForm.getDutyCalculationDTO().getPropDTO().getPropertyId().
			 * equalsIgnoreCase("3")){
			 * 
			 * String
			 * mainValId=eForm.getDutyCalculationDTO().getPropDTO().getValuationId
			 * ();
			 * 
			 * commonBo.getAgriPropertyList(regForm,0,null);
			 * 
			 * 
			 * 
			 * }else{
			 * distId=eForm.getDutyCalculationDTO().getPropDTO().getDistrictId
			 * (); if(distId.length()==1) distId="0"+distId;
			 * 
			 * tehslId=eForm.getDutyCalculationDTO().getPropDTO().getTehsilId();
			 * if(tehslId.length()==1) tehslId="0"+tehslId;
			 * 
			 * if((regForm.getPropertyId()=="" || regForm.getPropertyId()==null)
			 * ) { propertyId=distId+tehslId+commonBo.getPropertyIdSequence();
			 * logger.debug("property id------------>"+propertyId);
			 * if(propertyId.length()==15)
			 * regForm.setPropertyId("0"+propertyId); else
			 * regForm.setPropertyId(propertyId); } } }
			 */
			// end of code for property id generation by roopam
			// }
			// following code for property id generation in case of other than
			// exchange deed
			regForm.setFromAdjudication(eForm.getFromAdjudication());
			if (regForm.getFromAdjudication() == 1) {
				// session.setAttribute("fnName","Adjudication");
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
				}
			} else {
				// session.setAttribute("fnName","Initiation");
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
				}
			}
			int deed = 0;
			deed = regForm.getDeedID();
			request.setAttribute("deedId", deed);
			commonDto.setDeedId(deed);
			if (regForm.getIsDashboardFlag() == 0)
				commonDto.setPartyType(commonBo.getPartyType(deed, regForm.getInstID(), languageLocale));
			// commonDto.setAppType(commonBo.getAppType(languageLocale));
			// logger.debug("drop down:--->"+commonDto.getAppType());
			commonDto.setCountry(commonBo.getCountry(languageLocale));
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIdProf(commonBo.getIdProf(languageLocale));
			commonDto.setDeedType(commonBo.getDeedType(languageLocale));// hindi
			// missing
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
			// commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar()));
			regForm.setCountry("1");
			regForm.setCountryName("INDIA");
			regForm.setStatename("1");
			regForm.setStatenameName("MADHYA PRADESH");
			regForm.setAuthPerCountry("1");
			regForm.setAuthPerCountryName("INDIA");
			regForm.setAuthPerStatename("1");
			regForm.setAuthPerStatenameName("MADHYA PRADESH");
			regForm.setIndcountry("1");
			regForm.setIndcountryName("INDIA");
			regForm.setIndstatename("1");
			regForm.setOwnerIndcountry("1");
			regForm.setOwnerIndstatename("1");
			regForm.setOwnerIndcountryTrns("1");
			regForm.setOwnerIndstatenameTrns("1");
			regForm.setIndstatenameName("MADHYA PRADESH");
			regForm.setIndcountryArb("1");
			regForm.setIndstatenameArb("1");
			regForm.setMineralListSelected(eForm.getDutyCalculationDTO().getMineralListChecked());
			regForm.setMineralSelected(eForm.getDutyCalculationDTO().getMineralSelected());
			regForm.setMiningDuration(eForm.getDutyCalculationDTO().getMiningTotalDuration());
		}
		if (regForm.getFromAdjudication() == 0) {
			if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
			} else {
				session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
			}
		} else {
			// session.setAttribute("fnName","Adjudication");
			if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
				session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
			} else {
				session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
			}
		}
		forward = regForm.getPage();
		if (request.getAttribute("regFormDashboard") == null) { // this line was
			// added for
			// dashboard
			/*
			 * int deed=0; deed=regForm.getDeedID();
			 * request.setAttribute("deedId", deed); commonDto.setDeedId(deed);
			 * if(regForm.getIsDashboardFlag()==0)
			 * commonDto.setPartyType(commonBo
			 * .getPartyType(deed,regForm.getInstID(),languageLocale));
			 * commonDto.setAppType(commonBo.getAppType(languageLocale));
			 * //logger.debug("drop down:--->"+commonDto.getAppType());
			 * commonDto.setCountry(commonBo.getCountry(languageLocale));
			 * commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			 * commonDto.setIdProf(commonBo.getIdProf(languageLocale));
			 * commonDto
			 * .setDeedType(commonBo.getDeedType(languageLocale));//hindi
			 * missing
			 * commonDto.setCategoryList(commonBo.getCategoryList(languageLocale
			 * ));
			 * commonDto.setOccupationList(commonBo.getOccupationList(languageLocale
			 * ));
			 * //commonDto.setRelationshipList(commonBo.getRelationshipList(regForm
			 * .getGendar())); regForm.setCountry("1");
			 * regForm.setCountryName("INDIA"); regForm.setStatename("1");
			 * regForm.setStatenameName("MADHYA PRADESH");
			 * regForm.setAuthPerCountry("1");
			 * regForm.setAuthPerCountryName("INDIA");
			 * regForm.setAuthPerStatename("1");
			 * regForm.setAuthPerStatenameName("MADHYA PRADESH");
			 * regForm.setIndcountry("1"); regForm.setIndcountryName("INDIA");
			 * regForm.setIndstatename("1");
			 * regForm.setIndstatenameName("MADHYA PRADESH");
			 * regForm.setIndcountryArb("1"); regForm.setIndstatenameArb("1");
			 */
			// following code for getting state and district of applicant
			// for getting organization state list
			if (regForm.getCountry() != null && !regForm.getCountry().equalsIgnoreCase("") && !regForm.getCountry().equalsIgnoreCase("null")) {
				commonDto.setState(commonBo.getState(regForm.getCountry(), languageLocale));
				commonDto.setGovernmentOfficeName(commonBo.getGovtOfficeName(languageLocale));
				forward = "success";
			} else {
				commonDto.setState(new ArrayList());
				commonDto.setGovernmentOfficeName(commonBo.getGovtOfficeName(languageLocale));
			}
			// for getting organization district list
			if (regForm.getStatename() != null && !regForm.getStatename().equalsIgnoreCase("") && !regForm.getStatename().equalsIgnoreCase("null")) {
				commonDto.setDistrict(commonBo.getDistrict(regForm.getStatename(), languageLocale));
				forward = "success";
			} else {
				commonDto.setDistrict(new ArrayList());
			}
			// for getting individual state list
			if (regForm.getIndcountry() != null && !regForm.getIndcountry().equalsIgnoreCase("") && !regForm.getIndcountry().equalsIgnoreCase("null")) {
				commonDto.setIndstate(commonBo.getState(regForm.getIndcountry(), languageLocale));
				forward = "success";
			} else {
				commonDto.setIndstate(new ArrayList());
			}
			// for getting individual district list
			if (regForm.getIndstatename() != null && !regForm.getIndstatename().equalsIgnoreCase("") && !regForm.getIndstatename().equalsIgnoreCase("null")) {
				commonDto.setInddistrict(commonBo.getDistrict(regForm.getIndstatename(), languageLocale));
				forward = "success";
			} else {
				commonDto.setInddistrict(new ArrayList());
			}
			if (regForm.getDeedID() == RegInitConstant.DEED_AWARD_PV) {
				// for getting Arbitrator state list
				if (regForm.getIndcountryArb() != null && !regForm.getIndcountryArb().equalsIgnoreCase("") && !regForm.getIndcountryArb().equalsIgnoreCase("null")) {
					commonDto.setIndstate(commonBo.getState(regForm.getIndcountryArb(), languageLocale));
					forward = "success";
				} else {
					commonDto.setIndstate(new ArrayList());
				}
				// for getting Arbitrator district list
				if (regForm.getIndstatenameArb() != null && !regForm.getIndstatenameArb().equalsIgnoreCase("") && !regForm.getIndstatenameArb().equalsIgnoreCase("null")) {
					commonDto.setInddistrict(commonBo.getDistrict(regForm.getIndstatenameArb(), languageLocale));
					forward = "success";
				} else {
					commonDto.setInddistrict(new ArrayList());
				}
			}
		}
		if (regForm.getIsMultiplePropsFlag() == 1) {
			String[] deedInstArray = commonBo.getDeedInstId(regForm.getHidnRegTxnId());
			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
			if (deedInstArray[2].trim().equals("-")) {
				regForm.setExmpID("");
				regForm.setHdnExAmount("");
			} else {
				regForm.setExmpID(deedInstArray[2].trim());
				regForm.setHdnExAmount(deedInstArray[2].trim());
			}
		}
		forward = "success";
		String formName = regForm.getFormName();
		String actionName = regForm.getActionName();
		logger.debug("formName:-" + formName);
		logger.debug("actionName:-" + actionName);
		// added for aadhar integration by ankit ------ start
		if ("Aadhar Validation".equalsIgnoreCase(actionName) || "Aadhar Validation Transaction".equalsIgnoreCase(actionName) || "Aadhar Validation displayRegDetls".equalsIgnoreCase(actionName) || "Aadhar Validation Consenter".equalsIgnoreCase(actionName) || "Aadhar Validation displayConsenterDetls".equalsIgnoreCase(actionName) || "Aadhar Validation Owner".equalsIgnoreCase(actionName) || "Aadhar Validation displayOwner".equalsIgnoreCase(actionName) || "Aadhar Validation OwnerTrns".equalsIgnoreCase(actionName)) {

		}
		// added for aadhar integration by ankit ------ end
		if ("VALIDATE".equalsIgnoreCase(actionName)) {
			commonBo.validateDeedDraftId(regForm, languageLocale);
			regForm.setRegInitEstampCode(null);
			regForm.setActionName("");
			forward = "reginitConfirm";
		}
		if ("CONSUME".equalsIgnoreCase(actionName)) {
			String deedId = null;
			String filePath = null;
			ArrayList list = commonBo.getDeedDraftId(regForm.getHidnRegTxnId());
			if (list != null && list.size() == 1) {
				ArrayList rowList = (ArrayList) list.get(0);
				if (rowList != null && rowList.size() == 2) {
					deedId = (String) rowList.get(0);
					filePath = (String) rowList.get(1);
				}
			}
			if (deedId != null && filePath != null) {
				logger.debug("deed id already mapped. retrived already mapped deed id.");
				regForm.setDeedStat("5");
				regForm.setDeedDraftId(deedId);
				// set deed draft path and contents in form
				regForm.setDeedPath(filePath);
				regForm.setDeedContents(DMSUtility.getDocumentBytes(filePath));
				regForm.setDeedPageNo(commonBo.getMaxDeedPage(deedId));
			} else {
				logger.debug("consuming deed id.");
				try {
					commonBo.consumeDeedDraftId(regForm, languageLocale, response);
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
					regForm.setDeedStat("3");
					if ("en".equalsIgnoreCase(languageLocale)) {
						regForm.setErMsg("Could not consume Deed Id.");
					} else {
						regForm.setErMsg("????????? ???????????? ?????? ??????????????? ???????????? ???????????? ?????? ?????????");
					}
				}
			}
			regForm.setRegInitEstampCode(null);
			regForm.setActionName("");
			forward = "reginitConfirm";
		}
		if (RegInitConstant.OWNER_PAGE_FORM.equals(formName)) {
			if (RegInitConstant.MODIFY_OWNER_ACTION.equals(actionName)) {/*
																		 * 
																		 * 
																		 * if(regForm
																		 * .
																		 * getOwnerOgrName
																		 * (
																		 * ).equals
																		 * (
																		 * "-"))
																		 * {
																		 * regForm
																		 * .
																		 * setOwnerOgrName
																		 * ("");
																		 * }if(
																		 * regForm
																		 * .
																		 * getOwnerEmailID
																		 * (
																		 * ).equals
																		 * (
																		 * "-"))
																		 * {
																		 * regForm
																		 * .
																		 * setOwnerEmailID
																		 * ("");
																		 * }if(
																		 * regForm
																		 * .
																		 * getOwnerIdno
																		 * (
																		 * ).equals
																		 * (
																		 * "-"))
																		 * {
																		 * regForm
																		 * .
																		 * setOwnerIdno
																		 * ("");
																		 * }if(
																		 * regForm
																		 * .
																		 * getAddressOwnerOutMp
																		 * (
																		 * ).equals
																		 * (
																		 * "-"))
																		 * {
																		 * regForm
																		 * .
																		 * setAddressOwnerOutMp
																		 * ("");
																		 * }
																		 * 
																		 * 
																		 * 
																		 * regForm.
																		 * setActionName
																		 * ("");
																		 * regForm
																		 * .
																		 * setPartyModifyFlag
																		 * (1);
																		 * commonDto
																		 * .
																		 * setIdProf
																		 * (
																		 * commonBo
																		 * .
																		 * getIdProf
																		 * (
																		 * languageLocale
																		 * ));
																		 * forward
																		 * =
																		 * "displayOwner"
																		 * ;
																		 * return
																		 * mapping
																		 * .
																		 * findForward
																		 * (
																		 * forward
																		 * );
																		 */
			}
			if (RegInitConstant.OWNER_NO_ACTION.equals(actionName)) {
				commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));
				commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendar(), languageLocale));
				regForm.setOwnerModifyFlag(1);
				regForm.setActionName("");
				forward = "displayOwner";
			}
			if (RegInitConstant.OWNER_NO_ACTION_FOR_DISABLITY.equals(actionName)) {
				commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));
				commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendar(), languageLocale));
				if (regForm.getOwnerIndScheduleArea().equalsIgnoreCase("y")) {
					regForm.setOwnerPermissionNo("");
				}
				if (regForm.getOwnerIndDisability().equalsIgnoreCase("n")) {
					regForm.setOwnerIndDisabilityDesc("");
				}
				regForm.setOwnerModifyFlag(1);
				regForm.setActionName("");
				forward = "displayOwner";
			}
			if (RegInitConstant.SAVE_OWNER_ACTION.equals(actionName)) {
				boolean boo = commonBo.updateOwnerDetails(regForm);
				if (boo) {
					// regForm.setPartyModifyFlag(0);
					if (!("7".equalsIgnoreCase(regForm.getOwnerListID()))) {
						regForm.setOwnerDisplayAadhar("0");
					} else {
						regForm.setOwnerDisplayAadhar("0");
					}
					regForm.setOwnerModifyFlag(0);
					commonBo.getOwnerDetails(regForm.getOwnerId(), regForm, languageLocale);
					regForm.setActionName("");
					forward = "displayOwner";
				} else {
					forward = "failure";
				}
				// SAVE OWNER DETAILS
			}
			if (RegInitConstant.OWNER_GENDER_ACTION_MODIFY.equals(actionName)) {
				commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendar(), languageLocale));
				commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "displayOwner";
				// return mapping.findForward(forward);
			}
			if (RegInitConstant.SUBMIT_FORM_ACTION_OWNER.equalsIgnoreCase(actionName)) {
				logger.debug("category value----------->" + regForm.getIndCategoryTrns());
				regForm.setActionName(RegInitConstant.NO_ACTION);
				commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));// state
				// id
				// passed
				// is
				// for
				// MP
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
				regForm.setPartyModifyFlag(1);
				regForm.setOwnerModifyFlag(1);
				regForm.setOwnerPermissionNo("");
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				request.setAttribute("roleId", regForm.getPartyType());
				forward = regForm.getJspName();
			}
			/*
			 * if(RegInitConstant.BACK_TO_OWNER_DISPLAY_ACTION.equals(actionName)
			 * ){
			 * 
			 * if(regForm.getPartyModifyFlag()==1){
			 * regForm.setOwnerModifyFlag(1); }else{
			 * regForm.setOwnerModifyFlag(0); }
			 * 
			 * forward = "displayOwner"; return mapping.findForward(forward);
			 * 
			 * 
			 * }
			 */
		}
		if (RegInitConstant.CONSENTER_PAGE_FORM.equals(formName)) {
			if (RegInitConstant.BACK_TO_CONSENTER_DISPLAY_ACTION.equals(actionName)) {
				regForm.setActionName("");
				regForm.setPartyModifyFlag(0);
				commonBo.openConsenterView(request, regForm, languageLocale);
				forward = "displayConsenterDetls";
				return mapping.findForward(forward);
			}
			if (RegInitConstant.MODIFY_CONSENTER_ACTION.equals(actionName)) {
				regForm.setActionName("");
				regForm.setPartyModifyFlag(1);
				commonDto.setDistrict(commonBo.getDistrict(regForm.getRegDTO().getConsenterState(), languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setState(commonBo.getState("1", languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
				if (regForm.getRegDTO().getConsenterPhotoIdNumber().equalsIgnoreCase("-")) {
					regForm.getRegDTO().setConsenterPhotoIdNumber("");
				}
				regForm.setListDto(regForm.getRegDTO().getListDto());
				// aadhar changes
				if (regForm.getRegDTO().getConsenterPhotoId().equalsIgnoreCase("7")) {
					regForm.setConsenterCheckDisplay("1");
				} else {
					regForm.setConsenterCheckDisplay("0");
				}
				forward = "displayConsenterDetls";
				return mapping.findForward(forward);
			}
			if (RegInitConstant.GET_CONSENTER_DISTRICT_MODIFY.equals(actionName) || RegInitConstant.NO_ACTION_CONSENTER_DISPLAY.equals(actionName)) {
				regForm.setActionName("");
				commonDto.setDistrict(commonBo.getDistrict(regForm.getRegDTO().getConsenterState(), languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setState(commonBo.getState("1", languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
				forward = "displayConsenterDetls";
				return mapping.findForward(forward);
			}
			if (RegInitConstant.GET_CONSENTER_DISTRICT.equals(actionName) || RegInitConstant.AADHAR_CHECK.equals(actionName) || RegInitConstant.NO_ACTION_CONSENTER.equals(actionName) || RegInitConstant.Aadhar_Validation_Consenter.equalsIgnoreCase(actionName)) {
				regForm.setActionName("");
				commonDto.setDistrict(commonBo.getDistrict(regForm.getRegDTO().getConsenterState(), languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setState(commonBo.getState("1", languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
				forward = "reginitConsenter";
				return mapping.findForward(forward);
			}
			if (RegInitConstant.DEL_CONSENTER_ACTION.equals(actionName)) {
				// delete code
				regForm.setActionName("");
				String[] consenterID = regForm.getHdnDeleteConsenter().split(",");
				boolean consentersDeltd = commonBo.deleteConsenterDetails(consenterID, regForm.getHidnRegTxnId());
				if (consentersDeltd) {
					HashMap consenterMap = regForm.getDtlsMapConsntr();
					for (int i = 0; i < consenterID.length; i++) {
						consenterMap.remove(consenterID[i]);
					}
					regForm.setDtlsMapConsntr(consenterMap);
					commonDto.setDistrict(commonBo.getDistrict(regForm.getRegDTO().getConsenterState(), languageLocale));
					commonDto.setIdProf(commonBo.getIdProf(languageLocale));
					commonDto.setState(commonBo.getState("1", languageLocale));
					commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
					regForm.setConsenterAddedCount(regForm.getDtlsMapConsntr().size());
					forward = "reginitConsenter";
					return mapping.findForward(forward);
				} else {
					logger.debug("unable to delete consenters.");
					forward = "failure";
				}
			}
			if (RegInitConstant.ADD_CONSENTER_ACTION.equals(actionName)) {
				RegCompleteMakerDTO mapDto = new RegCompleteMakerDTO();
				regForm.setActionName("");
				// added for aadhar integration
				regForm.setAadharErrMsg(""); // added for aadhar integration
				regForm.setAadharRespCode(""); // added for aadhar integration
				regForm.setAadharCheck("false");// added for aadhar integration
				// individual
				/*
				 * if (regForm.getClrFlg().equalsIgnoreCase("Y")) {
				 * //mapDto.setConsenterPhotoId("7");
				 * regForm.getRegDTO().setConsenterPhotoId
				 * (mapDto.getConsenterPhotoId()); }
				 */
				mapDto.setConsenterFirstName(regForm.getRegDTO().getConsenterFirstName());
				mapDto.setConsenterAge(regForm.getRegDTO().getConsenterAge());
				mapDto.setConsenterFatherName(regForm.getRegDTO().getConsenterFatherName());
				regForm.setConsenterId(commonBo.getConsenterIdSeq());
				mapDto.setConsenterSno(regForm.getConsenterId());
				map = regForm.getDtlsMapConsntr();
				map.put(regForm.getConsenterId(), mapDto);
				regForm.setDtlsMapConsntr(map);
				// CONSENTER INSERTION
				boolean check = false;
				CommonAction obj = new CommonAction();
				ArrayList<CommonDTO> dto = regForm.getListDto();
				if (dto != null && dto.size() > 0) {
					for (int i = 0; i < dto.size(); i++) {
						CommonDTO dtos = dto.get(i);
						// String fileName=dtos.getDocumentName();
						// String ext=commonBo.getFileExtension(fileName);
						// ileName=fileName.substring(fileName.lastIndexOf("."));
						// fileName=fileName+"_"+i+"."+ext;
						dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(i)));
						String checkUpload = obj.uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), regForm.getConsenterId(), "04", dtos.getDocumentName());
						if (checkUpload == null) {
							check = false;
							break;
						} else {
							dtos.setDocumentPath(checkUpload);
							check = true;
						}
					}
					if (check == true) {
						check = commonBo.insertAdditionalUploadsConsenter(regForm);
					}
				} else {
					check = true;
				}
				if (check) {
					check = commonBo.insertConsenterDetails(regForm, null);
					if (check) {
						regForm.setAadharName(""); // added by Ankit for Aadhar
						// integration
						forward = "reginitConsenter";
						regForm.setListDto(new ArrayList());
						regForm.setRegDTO(new RegCompleteMakerDTO());
						regForm.setConsenterAddedCount(regForm.getDtlsMapConsntr().size());
						commonDto.setIdProf(commonBo.getIdProf(languageLocale));
						commonDto.setState(commonBo.getState("1", languageLocale));
						commonDto.setDistrict(new ArrayList());
						commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
						return mapping.findForward(forward);
					} else {
						forward = "failure";
					}
				} else {
					forward = "failure";
				}
			}
			if (RegInitConstant.SAVE_LAST_CONSENTER.equals(actionName)) {
				regForm.setActionName("");
				regForm.setConsenterId(commonBo.getConsenterIdSeq());
				/*
				 * if (regForm.getClrFlg().equalsIgnoreCase("Y")) {
				 * regForm.getRegDTO().setConsenterPhotoId("7"); }
				 */
				// CONSENTER INSERTION
				boolean check = false;
				CommonAction obj = new CommonAction();
				ArrayList<CommonDTO> dto = regForm.getListDto();
				if (dto != null && dto.size() > 0) {
					for (int i = 0; i < dto.size(); i++) {
						CommonDTO dtos = dto.get(i);
						dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(i)));
						String checkUpload = obj.uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), regForm.getConsenterId(), "04", dtos.getDocumentName());
						if (checkUpload == null) {
							check = false;
							break;
						} else {
							dtos.setDocumentPath(checkUpload);
							check = true;
						}
					}
					if (check == true) {
						check = commonBo.insertAdditionalUploadsConsenter(regForm);
					}
				} else {
					check = true;
				}
				if (check) {
					check = commonBo.insertConsenterDetails(regForm, "save");
					if (check) {
						boolean calConsenterDuty = true;
						if (regForm.getConsenterWithConsidFlag().equalsIgnoreCase("N")) {
							DutyCalculationBO dutyBo = new DutyCalculationBO();
							calConsenterDuty = dutyBo.consenterInsertions(regForm.getHidnRegTxnId());
						} else {
							calConsenterDuty = true;
						}
						// cal dc method here
						if (calConsenterDuty) {
							logger.debug("dc successful.");
							// copy new final duties in reg init duty table
							calConsenterDuty = commonBo.copyFinalConsenterDuties(regForm);
							if (calConsenterDuty) {
								logger.debug("save new duty details from dc successful.");
								forward = "reginitConfirm";
								request.setAttribute("deedId", regForm.getDeedID());
								request.setAttribute("instId", regForm.getInstID());
								commonBo.landConfirmationScreen(regForm, languageLocale, request);
								return mapping.findForward(forward);
							} else {
								logger.debug("unable to save new duty details from dc.");
								forward = "failure";
							}
						} else {
							logger.debug("failure in dc method while calculating consenter duties.");
							forward = "failure";
						}
					} else {
						forward = "failure";
					}
				} else {
					forward = "failure";
				}
			}
			if (RegInitConstant.RESET_CONSENTER_ACTION.equals(actionName)) {
				regForm.setActionName("");
				regForm.setAadharErrMsg("");
				regForm.setAadharName(""); // added by Ankit for Aadhar
				// integration
				regForm.setAadharRespCode("");// added by Ankit for Aadhar
				// integration
				regForm.setAcknowledgementId("");// added by Ankit for Aadhar
				// integration
				regForm.setTransactionId("");// added by Ankit for Aadhar
				// integration
				forward = "reginitConsenter";
				regForm.setListDto(new ArrayList());
				regForm.setRegDTO(new RegCompleteMakerDTO());
				if (regForm.getDtlsMapConsntr() != null) {
					regForm.setConsenterAddedCount(regForm.getDtlsMapConsntr().size());
				} else {
					regForm.setConsenterAddedCount(0);
				}
				commonDto.setState(commonBo.getState("1", languageLocale));
				commonDto.setDistrict(new ArrayList());
				return mapping.findForward(forward);
			}
			if (RegInitConstant.SAVE_CONSENTER_ACTION.equals(actionName)) {
				regForm.setActionName("");
				regForm.setConsenterId(regForm.getRegDTO().getConsenterSno());
				String path = "";
				try {
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					path = pr.getValue("upload.location");
				} catch (Exception e) {
					logger.debug("exception in uploadFile : " + e);
					logger.debug(e.getMessage(), e);
				}
				boolean checkAdditionalUploads = true;
				path = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_CONSENTER + regForm.getConsenterId() + "/";
				try {
					// By Mohit
					File f = new File(path);
					FileUtils.cleanDirectory(f);
				} catch (Exception e) {
					request.setAttribute("error", "Unable to clean directory.");
					logger.debug(e.getMessage(), e);
				} finally {
					try {
						checkAdditionalUploads = true;
						commonBo.deleteAllRemovedUploadsConsenter(regForm.getHidnRegTxnId(), regForm.getConsenterId());
					} catch (Exception e) {
						request.setAttribute("error", "Unable to delete records.");
						checkAdditionalUploads = false;
						logger.debug(e.getMessage(), e);
					}
				}
				// CONSENTER INSERTION
				boolean check = false;
				CommonAction obj = new CommonAction();
				if (checkAdditionalUploads) {
					ArrayList<CommonDTO> dto = regForm.getListDto();
					if (dto != null && dto.size() > 0) {
						for (int i = 0; i < dto.size(); i++) {
							CommonDTO dtos = dto.get(i);
							dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(i)));
							String checkUpload = obj.uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), regForm.getConsenterId(), "04", dtos.getDocumentName());
							if (checkUpload == null) {
								check = false;
								break;
							} else {
								dtos.setDocumentPath(checkUpload);
								check = true;
							}
						}
						if (check == true) {
							check = commonBo.insertAdditionalUploadsConsenter(regForm);
						}
					} else {
						check = true;
					}
				}
				if (check) {
					check = commonBo.updateConsenterDetails(regForm);
					if (check) {
						regForm.setPartyModifyFlag(0);
						commonBo.openConsenterView(request, regForm, languageLocale);
						forward = "displayConsenterDetls";
						return mapping.findForward(forward);
					} else {
						forward = "failure";
					}
				} else {
					forward = "failure";
				}
			}
		}
		if (RegInitConstant.MAPPING_FORM.equals(formName)) {
			if (RegInitConstant.PARTY_POPUP_CLOSE_MAPPING_ACTION.equals(actionName)) {
				forward = commonBo.landMappingScreen(regForm, languageLocale);
				regForm.setActionName("");
				return mapping.findForward(forward);
			}
			if (RegInitConstant.RESET_SHARES_ACTION.equals(actionName)) {
				regForm.setActionName(RegInitConstant.NO_ACTION);
				regForm.setTermsCondShare("");
				forward = "alreadyMapped";
				return mapping.findForward(forward);
			}
			/*
			 * if (RegInitConstant.VALIDATE_SHARES_ACTION.equals(actionName)) {
			 * 
			 * regForm.setActionName(""); regForm.setAadharName(""); // added
			 * for aadhar integration regForm.setAadharErrMsg(""); // added for
			 * aadhar integration regForm.setAadharRespCode(""); // added for
			 * aadhar integration regForm.setAadharCheck("false");// added for
			 * aadhar integration
			 * 
			 * HashMap activeMap = new HashMap(); int saveMappingFlag = 0;
			 * 
			 * if (regForm.getPropWithMapping() != null &&
			 * regForm.getPropWithMapping().size() > 0) { activeMap =
			 * regForm.getPropWithMapping(); } else { activeMap =
			 * regForm.getPropWithoutMapping(); saveMappingFlag = 1; }
			 * 
			 * commonBo.refreshHashMap(activeMap, request, regForm,
			 * saveMappingFlag);
			 * 
			 * HashMap shareMap = activeMap; double shareParty1 = 0; double
			 * shareParty2 = 0; int party1Added = 0; int party2Added = 0;
			 * 
			 * if (shareMap != null && shareMap.size() > 0) {
			 * 
			 * Set set = shareMap.keySet(); Iterator itr = set.iterator();
			 * ArrayList partyList; String key; CommonDTO dto; int roleId;
			 * double share; double mutationShare =0.0; int partyType1Or2; while
			 * (itr.hasNext()) { String partyId =""; String propId = "";
			 * party1Added = 0; party2Added = 0; //
			 * if(regForm.getDeedID()!=RegInitConstant.DEED_EXCHANGE){
			 * shareParty1 = 0; shareParty2 = 0; // } key = (String)
			 * itr.next();// key is property id propId = key;
			 * 
			 * partyList = (ArrayList) shareMap.get(key); if (partyList != null
			 * && partyList.size() > 0) {
			 * 
			 * for (int i = 0; i < partyList.size(); i++) {
			 * 
			 * dto = (CommonDTO) partyList.get(i);
			 * 
			 * partyId =""; partyId = dto.getId();
			 * 
			 * roleId = Integer.parseInt(dto.getRoleId());
			 * 
			 * partyType1Or2 = commonBo.getPartyType1Or2( regForm.getDeedID(),
			 * regForm .getInstID(), roleId);
			 * 
			 * if (saveMappingFlag == 1) {
			 * 
			 * if (partyType1Or2 == 1) { if (dto.getPartyCheck()
			 * .equalsIgnoreCase("Y")) party1Added = 1; } else { if
			 * (dto.getPartyCheck() .equalsIgnoreCase("Y")) party2Added = 1; }
			 * 
			 * }
			 * 
			 * if (dto.getShareOfProp() != null && !dto.getShareOfProp()
			 * .equalsIgnoreCase("")) { //share = Double.parseDouble(dto
			 * //.getShareOfProp()); share = 0; dto.setPartyShare(""); String[]
			 * fractionArray = dto .getShareOfProp().split("/"); try { if
			 * (fractionArray.length != 2){ if (fractionArray.length == 1){ //
			 * return Double.parseDouble(fractionArray[0]);
			 * 
			 * System.out .println(Double.parseDouble(fractionArray[0])); } else
			 * { System.out .println(Double.parseDouble(fractionArray[0])); } }
			 * double b = Double.parseDouble(fractionArray[1]); if (b==0d){
			 * System.out .println(Double.parseDouble(fractionArray[1])); }
			 * Double a = Double.parseDouble(fractionArray[0]); share = a/b;
			 * dto.setPartyShare(String.valueOf(share));
			 * 
			 * } catch (NumberFormatException e){ e.printStackTrace(); }
			 * 
			 * 
			 * } else { share = 0; } if (partyType1Or2 == 1) { shareParty1 +=
			 * share; } else { shareParty2 += share; }
			 * 
			 * }
			 * 
			 * if (saveMappingFlag == 1) {
			 * 
			 * if (regForm.getDeedID() == RegInitConstant.DEED_TRUST ||
			 * regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV
			 * || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV
			 * && !(regForm .getInstID() == RegInitConstant.INST_DISSOLUTION_NPV
			 * || regForm .getInstID() == RegInitConstant.INST_DISSOLUTION_2)))
			 * {
			 * 
			 * if (party1Added == 0) { if (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) { request
			 * .setAttribute( "shareErrorMsg", RegInitConstant.MESSAGE_1_EN); }
			 * else { request .setAttribute( "shareErrorMsg",
			 * RegInitConstant.MESSAGE_1_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); }
			 * 
			 * } else if (party1Added == 0 || party2Added == 0) { if
			 * (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_1_EN); } else {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_1_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); }
			 * 
			 * }
			 * 
			 * if (regForm.getDeedID() == RegInitConstant.DEED_EXCHANGE) { if
			 * ((shareParty2 != 0 && shareParty2 != 100) || (shareParty1 != 0 &&
			 * shareParty1 != 100)) { if ((shareParty2 != 0 && shareParty2 != 1)
			 * || (shareParty1 != 0 && shareParty1 != 1)) { if (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_EN); } else {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); } } else if
			 * (regForm.getDeedID() == RegInitConstant.DEED_PARTITION_PV ||
			 * regForm.getDeedID() == RegInitConstant.DEED_TRUST ||
			 * regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV
			 * || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV
			 * && !(regForm .getInstID() == RegInitConstant.INST_DISSOLUTION_NPV
			 * || regForm .getInstID() == RegInitConstant.INST_DISSOLUTION_2)))
			 * { if (shareParty1 != 0 && shareParty1 != 100) { if (shareParty1
			 * != 0 && shareParty1 != 1) { if (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_EN); } else {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); } } else if
			 * (regForm.getInstID() == RegInitConstant.INST_BUILDER_2) {
			 * 
			 * // get total shares from prop_duty table ArrayList shares =
			 * commonBo .getBuilderOwnerShares(key, regForm .getDuty_txn_id());
			 * // ArrayList shares=new ArrayList();
			 * 
			 * if (shares != null) { double builderShare = shares.get(0) != null
			 * ? Double .parseDouble(shares.get(0) .toString()) : 100; double
			 * ownerShare = shares.get(1) != null ? Double
			 * .parseDouble(shares.get(1) .toString()) : 100; // int
			 * builderShare=40; // int ownerShare=60; if ((shareParty1 != 0 &&
			 * shareParty2 != 0) && (shareParty1 != ownerShare || shareParty2 !=
			 * builderShare)) { if (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) { request
			 * .setAttribute( "shareErrorMsg", RegInitConstant.MESSAGE_2_EN); }
			 * else { request .setAttribute( "shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); } }
			 * 
			 * } else { if ((shareParty1 != 0 && shareParty2 != 0) &&
			 * (shareParty1 != 100 || shareParty2 != 100)) { if ((shareParty1 !=
			 * 0 && shareParty2 != 0) && (shareParty1 != 1 || shareParty2 != 1))
			 * { if (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_EN); } else {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); } }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE){ if(
			 * (shareParty2!=0 || shareParty1!=0) && (shareParty2!=100 ||
			 * shareParty1!=100)){ if(languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_EN); }else{
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_HI); } forward="alreadyMapped"; return
			 * mapping.findForward(forward); } }
			 * 
			 * 
			 * boolean shareUpdate = commonBo.updatePartyPropShares( shareMap,
			 * regForm, saveMappingFlag);
			 * 
			 * if (shareUpdate) {
			 * 
			 * commonBo.landConfirmationScreen(regForm, languageLocale,request);
			 * request.setAttribute("deedId", regForm.getDeedID());
			 * request.setAttribute("instId", regForm.getInstID());
			 * forward="reginitConfirm";
			 * 
			 * regForm.setActionName("");
			 * 
			 * String[] param=commonBo.getConsenterFlag(regForm.getHidnRegTxnId
			 * ()); regForm.setConsenterFlag(param[0]);
			 * regForm.setConsenterCount (Integer.parseInt(param[1]));
			 * 
			 * 
			 * if (regForm.getPropReqFlag().equalsIgnoreCase("Y") &&
			 * regForm.getConsenterFlag().equalsIgnoreCase( "Y")) { boolean
			 * updateStatus = commonBo .updateConsenterFlag(
			 * RegInitConstant.CONSENTER_IN_PROGRESS, regForm.getHidnRegTxnId(),
			 * "Y", regForm .getConsenterWithConsidFlag()); if (updateStatus) {
			 * forward = "reginitConsenter"; regForm.setListDto(new
			 * ArrayList()); regForm.setRegDTO(new RegCompleteMakerDTO());
			 * regForm.setConsenterAddedCount(0); commonDto.setIdProf(commonBo
			 * .getIdProf(languageLocale));
			 * commonDto.setState(commonBo.getState("1", languageLocale));
			 * commonDto.setDistrict(new ArrayList());
			 * commonDto.setRelationshipList(commonBo .getRelationshipList(null,
			 * languageLocale)); // return mapping.findForward(forward); } else
			 * { logger .debug("unable to update reg status for consenter.");
			 * forward = "failure";
			 * 
			 * } } else { forward = "reginitConfirm";
			 * request.setAttribute("deedId", regForm.getDeedID());
			 * request.setAttribute("instId", regForm.getInstID());
			 * commonBo.landConfirmationScreen(regForm, languageLocale,
			 * request); return mapping.findForward(forward); } } else { forward
			 * = "failure"; } return mapping.findForward(forward);
			 * 
			 * // insert shares here
			 * 
			 * }
			 * 
			 * }
			 */
			/*
			 * if (RegInitConstant.VALIDATE_SHARES_ACTION.equals(actionName)) {
			 * 
			 * regForm.setActionName(""); regForm.setAadharName(""); // added
			 * for aadhar integration regForm.setAadharErrMsg(""); // added for
			 * aadhar integration regForm.setAadharRespCode(""); // added for
			 * aadhar integration regForm.setAadharCheck("false");// added for
			 * aadhar integration RegCommonBD commonBd = new RegCommonBD();
			 * 
			 * HashMap activeMap = new HashMap(); int saveMappingFlag = 0;
			 * 
			 * if (regForm.getPropWithMapping() != null &&
			 * regForm.getPropWithMapping().size() > 0) { activeMap =
			 * regForm.getPropWithMapping(); } else { activeMap =
			 * regForm.getPropWithoutMapping(); saveMappingFlag = 1; }
			 * 
			 * commonBo.refreshHashMap(activeMap, request, regForm,
			 * saveMappingFlag);
			 * 
			 * HashMap shareMap = activeMap;//party map double shareParty1 = 0;
			 * double shareParty2 = 0; int party1Added = 0; int party2Added = 0;
			 * 
			 * if (shareMap != null && shareMap.size() > 0) {
			 * 
			 * Set set = shareMap.keySet(); Iterator itr = set.iterator();
			 * ArrayList partyList; String key; CommonDTO dto; int roleId;
			 * double share; double mutationShare =0.0; int partyType1Or2;
			 * 
			 * while (itr.hasNext()) { String partyId =""; String propId = "";
			 * party1Added = 0; party2Added = 0; //
			 * if(regForm.getDeedID()!=RegInitConstant.DEED_EXCHANGE){
			 * shareParty1 = 0; shareParty2 = 0; String sellableArea="0"; String
			 * getPropType ="0"; // } key = (String) itr.next();// key is
			 * property id propId = key;
			 * 
			 * getPropType = commonBo.getPropertyTypeID(propId); String
			 * valuationId
			 * =commonBd.getPropValuationId(regForm.getHidnRegTxnId(),propId);
			 * 
			 * 
			 * 
			 * sellableArea =
			 * commonBd.getSellableArea(propId,valuationId,getPropType);
			 * 
			 * 
			 * partyList = (ArrayList) shareMap.get(key); if (partyList != null
			 * && partyList.size() > 0) {
			 * 
			 * for (int i = 0; i < partyList.size(); i++) {
			 * 
			 * dto = (CommonDTO) partyList.get(i);
			 * 
			 * partyId =""; partyId = dto.getId();
			 * 
			 * 
			 * 
			 * roleId = Integer.parseInt(dto.getRoleId());
			 * 
			 * partyType1Or2 = commonBo.getPartyType1Or2( regForm.getDeedID(),
			 * regForm .getInstID(), roleId);
			 * 
			 * if (saveMappingFlag == 1) {
			 * 
			 * if (partyType1Or2 == 1) { if (dto.getPartyCheck()
			 * .equalsIgnoreCase("Y")) party1Added = 1; } else { if
			 * (dto.getPartyCheck() .equalsIgnoreCase("Y")) party2Added = 1; }
			 * 
			 * }
			 * 
			 * if (dto.getShareOfProp() != null && !dto.getShareOfProp()
			 * .equalsIgnoreCase("")) { //share = Double.parseDouble(dto
			 * //.getShareOfProp()); share =
			 * Double.parseDouble(dto.getShareOfProp()); dto.setPartyShare("");
			 * 
			 * 
			 * 
			 * } else { share = 0; } if (partyType1Or2 == 1) { shareParty1 +=
			 * share; } else { shareParty2 += share; }
			 * 
			 * }
			 * 
			 * if (saveMappingFlag == 1) {
			 * 
			 * if (regForm.getDeedID() == RegInitConstant.DEED_TRUST ||
			 * regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV
			 * || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV
			 * && !(regForm .getInstID() == RegInitConstant.INST_DISSOLUTION_NPV
			 * || regForm .getInstID() == RegInitConstant.INST_DISSOLUTION_2)))
			 * {
			 * 
			 * if (party1Added == 0) { if (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) { request
			 * .setAttribute( "shareErrorMsg", RegInitConstant.MESSAGE_1_EN); }
			 * else { request .setAttribute( "shareErrorMsg",
			 * RegInitConstant.MESSAGE_1_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); }
			 * 
			 * } else if (party1Added == 0 || party2Added == 0) { if
			 * (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_1_EN); } else {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_1_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); }
			 * 
			 * }
			 * 
			 * if (regForm.getDeedID() == RegInitConstant.DEED_EXCHANGE) { if
			 * ((shareParty2 != 0 && shareParty2 != 100) || (shareParty1 != 0 &&
			 * shareParty1 != 100)) { if ((shareParty2 != 0 && shareParty2 != 1)
			 * || (shareParty1 != 0 && shareParty1 != 1)) { if (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_EN); } else {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); } } else if
			 * (regForm.getDeedID() == RegInitConstant.DEED_PARTITION_PV ||
			 * regForm.getDeedID() == RegInitConstant.DEED_TRUST ||
			 * regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV
			 * || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV
			 * && !(regForm .getInstID() == RegInitConstant.INST_DISSOLUTION_NPV
			 * || regForm .getInstID() == RegInitConstant.INST_DISSOLUTION_2)))
			 * { if (shareParty1 != 0 && shareParty1 != 100) { if (shareParty1
			 * != 0 && shareParty1 != 1) { if (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_EN); } else {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); } } else if
			 * (regForm.getInstID() == RegInitConstant.INST_BUILDER_2) {
			 * 
			 * // get total shares from prop_duty table ArrayList shares =
			 * commonBo .getBuilderOwnerShares(key, regForm .getDuty_txn_id());
			 * // ArrayList shares=new ArrayList();
			 * 
			 * if (shares != null) { double builderShare = shares.get(0) != null
			 * ? Double .parseDouble(shares.get(0) .toString()) : 100; double
			 * ownerShare = shares.get(1) != null ? Double
			 * .parseDouble(shares.get(1) .toString()) : 100; // int
			 * builderShare=40; // int ownerShare=60; if ((shareParty1 != 0 &&
			 * shareParty2 != 0) && (shareParty1 != ownerShare || shareParty2 !=
			 * builderShare)) { if (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) { request
			 * .setAttribute( "shareErrorMsg", RegInitConstant.MESSAGE_2_EN); }
			 * else { request .setAttribute( "shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); } }
			 * 
			 * } else { if ((shareParty1 != 0 && shareParty2 != 0) &&
			 * (shareParty1 != 100 || shareParty2 != 100)) { if ((shareParty1 !=
			 * 0 && shareParty2 != 0) && (shareParty1 != 1 || shareParty2 != 1))
			 * { if (languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_EN); } else {
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_HI); } forward = "alreadyMapped";
			 * return mapping.findForward(forward); } }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE){ if(
			 * (shareParty2!=0 || shareParty1!=0) && (shareParty2!=100 ||
			 * shareParty1!=100)){ if(languageLocale
			 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_EN); }else{
			 * request.setAttribute("shareErrorMsg",
			 * RegInitConstant.MESSAGE_2_HI); } forward="alreadyMapped"; return
			 * mapping.findForward(forward); } }
			 * 
			 * 
			 * boolean shareUpdate = commonBo.updatePartyPropShares( shareMap,
			 * regForm, saveMappingFlag);
			 * 
			 * if (shareUpdate) {
			 * 
			 * commonBo.landConfirmationScreen(regForm, languageLocale,request);
			 * request.setAttribute("deedId", regForm.getDeedID());
			 * request.setAttribute("instId", regForm.getInstID());
			 * forward="reginitConfirm";
			 * 
			 * regForm.setActionName("");
			 * 
			 * String[] param=commonBo.getConsenterFlag(regForm.getHidnRegTxnId
			 * ()); regForm.setConsenterFlag(param[0]);
			 * regForm.setConsenterCount (Integer.parseInt(param[1]));
			 * 
			 * 
			 * if (regForm.getPropReqFlag().equalsIgnoreCase("Y") &&
			 * regForm.getConsenterFlag().equalsIgnoreCase( "Y")) { boolean
			 * updateStatus = commonBo .updateConsenterFlag(
			 * RegInitConstant.CONSENTER_IN_PROGRESS, regForm.getHidnRegTxnId(),
			 * "Y", regForm .getConsenterWithConsidFlag()); if (updateStatus) {
			 * forward = "reginitConsenter"; regForm.setListDto(new
			 * ArrayList()); regForm.setRegDTO(new RegCompleteMakerDTO());
			 * regForm.setConsenterAddedCount(0); commonDto.setIdProf(commonBo
			 * .getIdProf(languageLocale));
			 * commonDto.setState(commonBo.getState("1", languageLocale));
			 * commonDto.setDistrict(new ArrayList());
			 * commonDto.setRelationshipList(commonBo .getRelationshipList(null,
			 * languageLocale)); // return mapping.findForward(forward); } else
			 * { logger .debug("unable to update reg status for consenter.");
			 * forward = "failure";
			 * 
			 * } } else { forward = "reginitConfirm";
			 * request.setAttribute("deedId", regForm.getDeedID());
			 * request.setAttribute("instId", regForm.getInstID());
			 * commonBo.landConfirmationScreen(regForm, languageLocale,
			 * request); return mapping.findForward(forward); } } else { forward
			 * = "failure"; } return mapping.findForward(forward);
			 * 
			 * // insert shares here
			 * 
			 * }
			 * 
			 * }
			 */
			if (RegInitConstant.VALIDATE_SHARES_ACTION.equals(actionName)) {
				regForm.setActionName("");
				regForm.setAadharName(""); // added for aadhar integration
				regForm.setAadharErrMsg(""); // added for aadhar integration
				regForm.setAadharRespCode(""); // added for aadhar integration
				regForm.setAadharCheck("false");// added for aadhar integration
				regForm.setIsPropertyRemainingMap(0);
				HashMap activeMap = new HashMap();
				int saveMappingFlag = 0;
				if (regForm.getPropWithMapping() != null && regForm.getPropWithMapping().size() > 0) {
					activeMap = regForm.getPropWithMapping();
					// saveMappingFlag =1;
				} else {
					activeMap = regForm.getPropWithoutMapping();
					saveMappingFlag = 1;
				}
				commonBo.refreshHashMap(activeMap, request, regForm, saveMappingFlag);
				HashMap shareMap = activeMap;
				double shareParty1 = 0;
				double shareParty2 = 0;
				int party1Added = 0;
				int party2Added = 0;
				if (shareMap != null && shareMap.size() > 0) {
					Set set = shareMap.keySet();
					Iterator itr = set.iterator();
					ArrayList partyList;
					String key;
					CommonDTO dto;
					int roleId;
					double share;
					String calculatedShare;
					int partyType1Or2;
					while (itr.hasNext()) {
						party1Added = 0;
						party2Added = 0;
						// if(regForm.getDeedID()!=RegInitConstant.DEED_EXCHANGE){
						shareParty1 = 0;
						shareParty2 = 0;
						// }
						key = (String) itr.next();// key is property id
						partyList = (ArrayList) shareMap.get(key);
						if (partyList != null && partyList.size() > 0) {
							for (int i = 0; i < partyList.size(); i++) {
								dto = (CommonDTO) partyList.get(i);
								roleId = Integer.parseInt(dto.getRoleId());
								// dto.getId();
								// Updated by Rakesh for check the owner's poa
								// roleId for partyType1Or2
								// int clrFlowId=regForm.getPropertyDiv();
								if (!dto.getIsApplicantFlag().isEmpty()) {
									if (roleId == 50004 && dto.getIsApplicantFlag().equalsIgnoreCase("o")) {
										String partyTxnId = dto.getId();
										String roleIdOfParent = commonBo.getRoleIdOfParent(partyTxnId);
										roleId = Integer.parseInt(roleIdOfParent);
									}
								}
								// Updated by Rakesh for check the owner's poa
								// roleId for partyType1Or2
								partyType1Or2 = commonBo.getPartyType1Or2(regForm.getDeedID(), regForm.getInstID(), roleId);
								if (saveMappingFlag == 1) {
									if (partyType1Or2 == 1) {
										if (dto.getPartyCheck().equalsIgnoreCase("Y"))
											party1Added = 1;
									} else {
										if (dto.getPartyCheck().equalsIgnoreCase("Y"))
											party2Added = 1;
									}
								}
								if (dto.getShareOfProp() != null && !dto.getShareOfProp().equalsIgnoreCase("")) {
									share = Double.parseDouble(dto.getShareOfProp());
									dto.setPartyShare("");
									String[] fractionArray = dto.getCalculatedShare().split("/");
									try {
										if (fractionArray.length != 2) {
											if (fractionArray.length == 1) {
												// return
												// Double.parseDouble(fractionArray[0]);
												System.out.println(Double.parseDouble(fractionArray[0]));
											} else {
												System.out.println(Double.parseDouble(fractionArray[0]));
											}
										}
										double b = Double.parseDouble(fractionArray[1]);
										if (b == 0d) {
											System.out.println(Double.parseDouble(fractionArray[1]));
										}
										Double a = Double.parseDouble(fractionArray[0]);
										dto.setPartyShare(String.valueOf(a / b));
										if (b == 0d) {
											dto.setPartyShare("0");
										}
									} catch (NumberFormatException e) {
										e.printStackTrace();
									}
								} else {
									share = 0;
								}
								if (partyType1Or2 == 1) {
									shareParty1 += share;
								} else {
									shareParty2 += share;
								}
							}
							// commonBo.refreshHashMapForFormBackUp(activeMap,request,regForm,saveMappingFlag);
							// Added by rakesh for form backup
							regForm.getMapShare();
							regForm.getMapFractionalShare();
							// Added by rakesh for form backup
							if (saveMappingFlag == 1) {
								if (regForm.getDeedID() == RegInitConstant.DEED_TRUST || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && !(regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_2))) {
									if (party1Added == 0) {
										if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_EN);
										} else {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_HI);
										}
										regForm.setTermsCondShare("");
										forward = "alreadyMapped";
										return mapping.findForward(forward);
									}
								} else if (party1Added == 0 || party2Added == 0) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_EN);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_HI);
									}
									regForm.setTermsCondShare("");
									forward = "alreadyMapped";
									return mapping.findForward(forward);
								}
							}
							if (regForm.getDeedID() == RegInitConstant.DEED_EXCHANGE) {
								if ((shareParty2 != 0 && shareParty2 != 100) || (shareParty1 != 0 && shareParty1 != 100)) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN + key);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI + key);
									}
									regForm.setTermsCondShare("");
									if (regForm.getMapPropIdList().size() == 1) {
										regForm.setIsPropertyRemainingMap(1);
									}
									forward = "alreadyMapped";
									return mapping.findForward(forward);
									// need to check
								} else if ((shareParty2 == 0 && shareParty1 == 0)) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN + key);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI + key);
									}
									regForm.setTermsCondShare("");
									if (regForm.getMapPropIdList().size() == 1) {
										regForm.setIsPropertyRemainingMap(1);
									}
									forward = "alreadyMapped";
									return mapping.findForward(forward);
									// need to check
								}
							} else if (regForm.getDeedID() == RegInitConstant.DEED_PARTITION_PV || regForm.getDeedID() == RegInitConstant.DEED_TRUST || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && !(regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_2))) {
								if (shareParty1 != 0 && shareParty1 != 100) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN + key);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI + key);
									}
									if (regForm.getMapPropIdList().size() == 1) {
										regForm.setIsPropertyRemainingMap(1);
									}
									regForm.setTermsCondShare("");
									forward = "alreadyMapped";
									return mapping.findForward(forward);
									// need to check
								} else if ((shareParty1 == 0)) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN + key);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI + key);
									}
									if (regForm.getMapPropIdList().size() == 1) {
										regForm.setIsPropertyRemainingMap(1);
									}
									regForm.setTermsCondShare("");
									forward = "alreadyMapped";
									return mapping.findForward(forward);
									// need to check
								}
							} else if (regForm.getInstID() == RegInitConstant.INST_BUILDER_2) {
								// get total shares from prop_duty table
								ArrayList shares = commonBo.getBuilderOwnerShares(key, regForm.getDuty_txn_id());
								// ArrayList shares=new ArrayList();
								if (shares != null) {
									double builderShare = shares.get(0) != null ? Double.parseDouble(shares.get(0).toString()) : 100;
									double ownerShare = shares.get(1) != null ? Double.parseDouble(shares.get(1).toString()) : 100;
									// int builderShare=40;
									// int ownerShare=60;
									if ((shareParty1 != 0 && shareParty2 != 0) && (shareParty1 != ownerShare || shareParty2 != builderShare)) {
										if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN + key);
										} else {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI + key);
										}
										regForm.setTermsCondShare("");
										if (regForm.getMapPropIdList().size() == 1) {
											regForm.setIsPropertyRemainingMap(1);
										}
										forward = "alreadyMapped";
										return mapping.findForward(forward);
									} else if ((shareParty1 == 0 || shareParty2 == 0)) {
										if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN + key);
										} else {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI + key);
										}
										regForm.setTermsCondShare("");
										if (regForm.getMapPropIdList().size() == 1) {
											regForm.setIsPropertyRemainingMap(1);
										}
										forward = "alreadyMapped";
										return mapping.findForward(forward);
									}
								}
							} else {
								if ((shareParty1 != 0 && shareParty2 != 0) && (shareParty1 != 100 || shareParty2 != 100)) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN + key);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI + key);
									}
									regForm.setTermsCondShare("");
									if (regForm.getMapPropIdList().size() == 1) {
										regForm.setIsPropertyRemainingMap(1);
									}
									forward = "alreadyMapped";
									return mapping.findForward(forward);
								} else if ((shareParty1 == 0 || shareParty2 == 0)) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN + key);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI + key);
									}
									regForm.setTermsCondShare("");
									if (regForm.getMapPropIdList().size() == 1) {
										regForm.setIsPropertyRemainingMap(1);
									}
									forward = "alreadyMapped";
									return mapping.findForward(forward);
								}
							}
						}
					}
					/*
					 * if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE){
					 * if( (shareParty2!=0 || shareParty1!=0) &&
					 * (shareParty2!=100 || shareParty1!=100)){
					 * if(languageLocale
					 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_2_EN); }else{
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_2_HI); } forward="alreadyMapped";
					 * return mapping.findForward(forward); } }
					 */
					boolean shareUpdate = commonBo.updatePartyPropShares(shareMap, regForm, saveMappingFlag);
					if (shareUpdate) {
						/*
						 * commonBo.landConfirmationScreen(regForm,
						 * languageLocale,request);
						 * request.setAttribute("deedId", regForm.getDeedID());
						 * request.setAttribute("instId", regForm.getInstID());
						 * forward="reginitConfirm";
						 */
						regForm.setActionName("");
						regForm.setAadharConsent("");
						/*
						 * String[]
						 * param=commonBo.getConsenterFlag(regForm.getHidnRegTxnId
						 * ()); regForm.setConsenterFlag(param[0]);
						 * regForm.setConsenterCount
						 * (Integer.parseInt(param[1]));
						 */
						int index = 0;
						for (int i = 0; i < regForm.getMapPropIdList().size(); i++) {
							CommonDTO checkList = (CommonDTO) regForm.getMapPropIdList().get(i);
							if (regForm.getSelectedPropIdMap() != null) {
								if (checkList.getPropertyId().equalsIgnoreCase(regForm.getSelectedPropIdMap())) {
									index = i;
									regForm.getMapPropIdList().remove(index);
									regForm.setPropertyDiv(0);
								}
							}
						}
						if (regForm.getMapPropIdList().size() == 1) {
							regForm.setIsPropertyRemainingMap(1);
						}
						if (regForm.getPropReqFlag().equalsIgnoreCase("Y") && regForm.getConsenterFlag().equalsIgnoreCase("Y")) {
							boolean updateStatus = commonBo.updateConsenterFlag(RegInitConstant.CONSENTER_IN_PROGRESS, regForm.getHidnRegTxnId(), "Y", regForm.getConsenterWithConsidFlag());
							if (updateStatus) {
								forward = "reginitConsenter";
								regForm.setListDto(new ArrayList());
								regForm.setRegDTO(new RegCompleteMakerDTO());
								regForm.setConsenterAddedCount(0);
								commonDto.setIdProf(commonBo.getIdProf(languageLocale));
								commonDto.setState(commonBo.getState("1", languageLocale));
								commonDto.setDistrict(new ArrayList());
								commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
								// return mapping.findForward(forward);
							} else {
								logger.debug("unable to update reg status for consenter.");
								forward = "failure";
							}
						} else {
							forward = "reginitConfirm";
							request.setAttribute("deedId", regForm.getDeedID());
							request.setAttribute("instId", regForm.getInstID());
							commonBo.landConfirmationScreen(regForm, languageLocale, request);
							// return mapping.findForward(forward);
						}
					} else {
						forward = "failure";
					}
					if (regForm.getMapPropIdList().size() > 0) {
						forward = "alreadyMapped";
					}
					if (regForm.getMapPropIdList().size() == 1) {
						regForm.setIsPropertyRemainingMap(1);
						forward = "alreadyMapped";
					}
					return mapping.findForward(forward);
					// insert shares here
				}
			}
			// for changed flow of mapping and share- CLR
			if ("CHANGE_PROPERTY_FOR_MAPPING_SHARE".equalsIgnoreCase(actionName)) {
				regForm.setActionName("");
				regForm.setPropertyDiv(0);
				// Added by rakesh for form backup
				regForm.setMapShare(null);
				regForm.setMapFractionalShare(null);
				// Added by rakesh for form backup
				// eForm.setMapBuildingDetails1(new HashMap());
				// eForm.setSelectedSubClauseList(new ArrayList());
				// regForm.setSelectedPropId(regForm.getSelectedValIdMap());
				if (regForm.getMapPropIdList().size() == 1) {
					regForm.setIsPropertyRemainingMap(1);
				}
				if (regForm.getSelectedPropIdMap().equalsIgnoreCase("select")) {
					regForm.setPropertyDiv(0);
				} else {
					regForm.setSelectedPropId(regForm.getSelectedPropIdMap());
					regForm.setPropertyDiv(0);
					// Property Party Combo display: Start
					String clrFlag = "N";
					clrFlag = commonBd.getClrFlagByPropId(regForm.getSelectedPropId());
					String mapCheck;
					if (request.getAttribute("mapCheck") != null) {
						mapCheck = (String) request.getAttribute("mapCheck");
					} else {
						mapCheck = "";
					}
					int count = commonBo.getPropertyCount(regForm.getHidnRegTxnId());
					int partyCount = commonBo.getPartyCount(regForm.getHidnRegTxnId());
					// for Clr : Start
					/*
					 * int clrCount =
					 * commonBo.getPropertyCountCLR(regForm.getHidnRegTxnId());
					 * 
					 * if (clrCount>0){
					 * 
					 * boolean insertedFlag =
					 * commonBo.getClrPropertyAndInsert(regForm
					 * .getHidnRegTxnId(),userId); }
					 */
					// for Clr : End
					boolean mapInsert = false;
					/*
					 * if ((mapCheck != null && mapCheck.equalsIgnoreCase("ON"))
					 * || (partyCount == 1 && count > 1)) {
					 * 
					 * mapInsert = commonBo.getAllPartiesProperties(regForm);
					 * 
					 * logger.debug("mapping insertion status : " + mapInsert);
					 * 
					 * }
					 */
					if (clrFlag.equalsIgnoreCase("Y")) {
						if (count == 1 || partyCount == 1 || regForm.getDeedID() == RegInitConstant.DEED_EXCHANGE || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV || (count > 1 && mapCheck.equalsIgnoreCase("ON") && mapInsert)) {
							// hashmap already containing mappings
							// regForm.setPropWithMapping(commonBo.getAllPartiesPropertiesAlreadyMap(regForm,languageLocale));
							// regForm.setPropWithoutMapping(null);
							// forward="alreadyMapped";
							//
							regForm.setPropWithMapping(commonBo.getAllPartiesPropertiesMapCLR(regForm, languageLocale));
							regForm.setPropWithoutMapping(null);
							/*
							 * regForm.setPropWithMapping(null);
							 * regForm.setPropWithoutMapping(commonBo
							 * .getAllPartiesPropertiesMapNonCLR(regForm,
							 * languageLocale));
							 */
							regForm.setAppStatus(RegInitConstant.STATUS_BACKED_MAPPING_SAVED);
							/*
							 * appStatus.equalsIgnoreCase(RegInitConstant.STATUS_PROP_SAVED
							 * ) ||appStatus.equalsIgnoreCase(RegInitConstant.
							 * STATUS_BACKED_MAPPING_SAVED)
							 */
							regForm.setDeclareShareSize(regForm.getPropWithMapping().size());
							// Set set=regForm.getPropWithMapping().keySet();
							// Iterator itr=set.iterator();
							// ArrayList
							// lis=(ArrayList)regForm.getPropWithMapping().get(itr.next());
							// if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE){
							// regForm.setShareOfPropSize(3);
							// }else{
							// regForm.setShareOfPropSize((regForm.getPropWithMapping().size())*(lis.size()));
							// }
							forward = "alreadyMapped";
							regForm.setCheckParty("true");
							if (regForm.getMapPropIdList().size() == 1) {
								regForm.setIsPropertyRemainingMap(1);
							} else {
								regForm.setIsPropertyRemainingMap(0);
							}
							regForm.setPropertyDiv(2);
							return mapping.findForward(forward);
						} else {
							regForm.setPropWithMapping(null);
							regForm.setPropWithoutMapping(commonBo.getAllPartiesPropertiesMapCLR(regForm, languageLocale));
							// Rakesh Mapping and share
							regForm.setAppStatus(RegInitConstant.STATUS_PROP_SAVED);
							regForm.setDeclareShareSize(regForm.getPropWithoutMapping().size());
							// Set set=regForm.getPropWithoutMapping().keySet();
							// Iterator itr=set.iterator();
							// ArrayList
							// lis=(ArrayList)regForm.getPropWithoutMapping().get(itr.next());
							// regForm.setShareOfPropSize((regForm.getPropWithoutMapping().size())*(lis.size()));
							if (regForm.getMapPropIdList().size() == 1) {
								regForm.setIsPropertyRemainingMap(1);
							} else {
								regForm.setIsPropertyRemainingMap(0);
							}
							regForm.setPropertyDiv(2);
							forward = "getMapping";
							return mapping.findForward(forward);
						}
						// regForm.setPropertyDiv(2);
					} else {
						if (count == 1 || partyCount == 1 || regForm.getDeedID() == RegInitConstant.DEED_EXCHANGE || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV || (count > 1 && mapCheck.equalsIgnoreCase("ON") && mapInsert)) {
							// hashmap already containing mappings
							// regForm.setPropWithMapping(commonBo.getAllPartiesPropertiesAlreadyMap(regForm,languageLocale));
							// regForm.setPropWithoutMapping(null);
							// forward="alreadyMapped";
							//
							regForm.setPropWithMapping(commonBo.getAllPartiesPropertiesMapNonCLR(regForm, languageLocale));
							regForm.setPropWithoutMapping(null);
							regForm.setAppStatus(RegInitConstant.STATUS_BACKED_MAPPING_SAVED);
							/*
							 * appStatus.equalsIgnoreCase(RegInitConstant.STATUS_PROP_SAVED
							 * ) ||appStatus.equalsIgnoreCase(RegInitConstant.
							 * STATUS_BACKED_MAPPING_SAVED)
							 */
							regForm.setDeclareShareSize(regForm.getPropWithMapping().size());
							// Set set=regForm.getPropWithMapping().keySet();
							// Iterator itr=set.iterator();
							// ArrayList
							// lis=(ArrayList)regForm.getPropWithMapping().get(itr.next());
							// if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE){
							// regForm.setShareOfPropSize(3);
							// }else{
							// regForm.setShareOfPropSize((regForm.getPropWithMapping().size())*(lis.size()));
							// }
							forward = "alreadyMapped";
							regForm.setCheckParty("true");
							regForm.setPropertyDiv(1);
							if (regForm.getMapPropIdList().size() == 1) {
								regForm.setIsPropertyRemainingMap(1);
							} else {
								regForm.setIsPropertyRemainingMap(0);
							}
							// regForm.setIsPropertyRemainingMap(1);
							return mapping.findForward(forward);
						} else {
							regForm.setPropWithMapping(null);
							regForm.setPropWithoutMapping(commonBo.getAllPartiesPropertiesMapNonCLR(regForm, languageLocale));
							// Rakesh Mapping and share
							regForm.setAppStatus(RegInitConstant.STATUS_PROP_SAVED);
							regForm.setDeclareShareSize(regForm.getPropWithoutMapping().size());
							// Set set=regForm.getPropWithoutMapping().keySet();
							// Iterator itr=set.iterator();
							// ArrayList
							// lis=(ArrayList)regForm.getPropWithoutMapping().get(itr.next());
							// regForm.setShareOfPropSize((regForm.getPropWithoutMapping().size())*(lis.size()));
							regForm.setPropertyDiv(1);
							if (regForm.getMapPropIdList().size() == 1) {
								regForm.setIsPropertyRemainingMap(1);
							} else {
								regForm.setIsPropertyRemainingMap(0);
							}
							// regForm.setIsPropertyRemainingMap(0);
							forward = "getMapping";
							return mapping.findForward(forward);
						}
					}
					// CHANGE HERE FOR DISSOLUTION OF TRUST
					// Property Party Combo display: End
				}
				// forward="alreadyMapped";
				if (regForm.getMapPropIdList().size() == 1) {
					regForm.setIsPropertyRemainingMap(1);
				}
				return mapping.findForward(forward);
			}
		}
		if (RegInitConstant.TRANS_PARTY_PAGE_FORM.equals(formName)) {
			if (RegInitConstant.ADD_MORE_TRNS_OWNER_ACTION.equals(actionName)) {
				RegCommonDTO mapDto = new RegCommonDTO();
				mapDto.setOwnerNameTrns(regForm.getOwnerNameTrns());
				if (regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null"))
					mapDto.setOwnerOgrNameTrns("-");
				else
					mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());
				mapDto.setOwnerGendarValTrns(regForm.getOwnerGendarTrns());
				if (regForm.getOwnerGendarTrns().equalsIgnoreCase("f")) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);
					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);
					}
				} else if (regForm.getOwnerGendarTrns().equalsIgnoreCase("m")) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);
					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);
					}
				} else {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.other_gender_ENGLISH);
					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.other_gender_hindi);
					}
				}
				mapDto.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
				mapDto.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
				mapDto.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
				if (regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
					mapDto.setOwnerEmailIDTrns("-");
				else
					mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());
				mapDto.setOwnerAgeTrns(regForm.getOwnerAgeTrns());
				mapDto.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdnoTrns());
				mapDto.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
				/*
				 * if (regForm.getClrFlg() != null) { if
				 * (regForm.getClrFlg().equalsIgnoreCase("y") &&
				 * regForm.getListAdoptedPartyTrns
				 * ().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
				 * mapDto.setOwnerListIDTrns("7"); } } else {
				 * mapDto.setOwnerListIDTrns(regForm.getOwnerListIDTrns()); }
				 */
				mapDto.setOwnerProofNameTrns(regForm.getOwnerListIDTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofNameTrns());
				/*
				 * if (regForm.getClrFlg() != null) { if
				 * (regForm.getClrFlg().equalsIgnoreCase("y") &&
				 * regForm.getListAdoptedPartyTrns
				 * ().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
				 * mapDto.setOwnerProofNameTrns("AADHAR CARD"); } } else {
				 * mapDto.setOwnerProofNameTrns(regForm.getOwnerListIDTrns().
				 * equalsIgnoreCase("") ? "-" :
				 * regForm.getOwnerProofNameTrns()); }
				 */
				if (regForm.getAddressOwnerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMpTrns())) {
					mapDto.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMpTrns());
				} else {
					mapDto.setAddressOwnerOutMpTrns("-");
				}
				// added for aadhar by ankit - start
				if (regForm.getAadharNameTrnsOwner() != null && !("").equalsIgnoreCase(regForm.getAadharNameTrnsOwner())) {
					mapDto.setAadharNameTrnsOwner(regForm.getAadharNameTrnsOwner());
					regForm.setAadharNameTrnsOwner("");
					regForm.setAadharRespCodeOwner("");
					regForm.setAadharCheckOwner("");
					regForm.setAadharErrMsgOwner("");
				} else {
					mapDto.setAadharNameTrnsOwner("");
				}
				if (regForm.getAadharRespDto() != null) {
					if (!"".equalsIgnoreCase(regForm.getAadharRespDto().getTransactionCode()) && !"".equalsIgnoreCase(regForm.getAadharRespDto().getTransactionId())) {
						mapDto.setAadharRespDto(regForm.getAadharRespDto());
					}
				}
				// added for aadhar by ankit - start
				mapDto.setOwnerIndCategoryTrns(regForm.getOwnerIndCategoryTrns());
				mapDto.setOwnerIndcountryTrns(regForm.getOwnerIndcountryTrns());
				mapDto.setOwnerIndstatenameTrns(regForm.getOwnerIndstatenameTrns());
				mapDto.setOwnerInddistrictTrns(regForm.getOwnerInddistrictTrns());
				mapDto.setOwnerIndphnoTrns(regForm.getOwnerIndphnoTrns().equalsIgnoreCase("") ? "" : regForm.getOwnerIndphnoTrns());
				mapDto.setOwnerPostalCodeTrns(regForm.getOwnerPostalCodeTrns().equalsIgnoreCase("") ? "" : regForm.getOwnerPostalCodeTrns());
				mapDto.setOwnerIndDisabilityTrns(regForm.getOwnerIndDisabilityTrns());
				mapDto.setOwnerIndDisabilityDescTrns(regForm.getOwnerIndDisabilityDescTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIndDisabilityDescTrns());
				mapDto.setOwnerIndMinorityTrns(regForm.getOwnerIndMinorityTrns());
				mapDto.setOwnerIndPovertyLineTrns(regForm.getOwnerIndPovertyLineTrns());
				mapDto.setOwnerOccupationIdTrns(regForm.getOwnerOccupationIdTrns());
				mapDto.setOwnerRelationshipTrns(regForm.getOwnerRelationshipTrns());
				mapDto.setOwnerFatherNameTrns(regForm.getOwnerFatherNameTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerFatherNameTrns());
				mapDto.setOwnerMotherNameTrns(regForm.getOwnerMotherNameTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerMotherNameTrns());
				mapDto.setOwnerSpouseNameTrns(regForm.getOwnerSpouseNameTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerSpouseNameTrns());
				mapDto.setOwnerPanNumberTrns(regForm.getOwnerPanNumberTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerPanNumberTrns());
				mapDto.setOwnerPermissionNoTrns(regForm.getOwnerPermissionNoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerPermissionNoTrns());
				mapDto.setOwnerIndScheduleAreaTrns(regForm.getOwnerIndScheduleAreaTrns().equalsIgnoreCase("") ? "" : regForm.getOwnerIndScheduleAreaTrns());
				mapDto.setOwnerInddistrictTrnsName(commonBo.getDistrictName(regForm.getOwnerInddistrictTrns(), languageLocale));
				mapDto.setOwnerIndCategoryTrnsName(commonBo.getCategoryName(regForm.getOwnerIndCategoryTrns(), languageLocale));
				mapDto.setOwnerOccupationIdTrnsName(commonBo.getOccupationName(regForm.getOwnerOccupationIdTrns(), languageLocale));
				mapDto.setOwnerRelationshipTrnsName(commonBo.getRelationshipName(regForm.getOwnerRelationshipTrns(), languageLocale));
				mapDto.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());
				regForm.getTrnsOwnerList().put(mapDto.getOwnerTxnId(), mapDto);
				regForm.setOwnerNameTrns("");
				regForm.setOwnerOgrNameTrns("");
				regForm.setOwnerGendarTrns("M");
				regForm.setOwnerNationalityTrns("");
				regForm.setOwnerAddressTrns("");
				regForm.setOwnerPhnoTrns("");
				regForm.setOwnerEmailIDTrns("");
				regForm.setOwnerAgeTrns("");
				regForm.setOwnerIdnoTrns("");
				regForm.setOwnerListIDTrns("");
				regForm.setOwnerProofNameTrns("");
				regForm.setAddressOwnerOutMpTrns("");
				regForm.setAadharNameTrnsOwner("");// Added for aadhar by ankit
				regForm.setAadharRespCodeOwner("");// Added for aadhar by ankit
				regForm.setAadharErrMsgOwner(""); // Added for aadhar by ankit
				regForm.setAadharCheckOwner("");// Added for aadhar by ankit
				regForm.setOwnerPanNumberTrns("");
				regForm.setOwnerIndphnoTrns("");
				regForm.setOwnerPostalCodeTrns("");
				regForm.setOwnerIndDisabilityTrns("N");
				regForm.setOwnerInddistrictTrns("");
				regForm.setOwnerIndCategoryTrns("");
				regForm.setOwnerIndDisabilityDescTrns("");
				regForm.setOwnerIndMinorityTrns("N");
				regForm.setOwnerIndPovertyLineTrns("N");
				regForm.setOwnerOccupationIdTrns("");
				regForm.setOwnerRelationshipTrns("");
				regForm.setOwnerFatherNameTrns("");
				regForm.setOwnerMotherNameTrns("");
				regForm.setOwnerSpouseNameTrns("");
				regForm.setOwnerPermissionNoTrns("");
				regForm.setOwnerIndScheduleAreaTrns("N");
				commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "transactingParty";
			}
			if (RegInitConstant.DELETE_MORE_TRNS_OWNER_ACTION.equals(actionName)) {
				String[] trnsPrtyID = regForm.getHdnDeleteOwnerID().split(",");
				HashMap ownerMap = regForm.getTrnsOwnerList();
				for (int i = 0; i < trnsPrtyID.length; i++) {
					ownerMap.remove(trnsPrtyID[i]);
				}
				regForm.setTrnsOwnerList(ownerMap);
				forward = "transactingParty";
				regForm.setActionName(RegInitConstant.NO_ACTION);
				actionName = RegInitConstant.NO_ACTION;
			}
			if (RegInitConstant.GENDER_TRNS_ACTION.equals(actionName)) {
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "transactingParty";
				// return mapping.findForward(forward);
			}
			if (RegInitConstant.OWNER_GENDER_ACTION_TRNS.equals(actionName)) {
				commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "transactingParty";
			}
			if (RegInitConstant.AUTH_PER_GENDER_TRNS_ACTION.equals(actionName)) {
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "transactingParty";
			}
			if (RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)) {
				String partyTypeTrns = regForm.getListAdoptedPartyTrns();
				regForm.setOwnerListIDTrns("");
				regForm.setOwnerIdnoTrns("");
				regForm.setAadharNameTrnsOwner("");
				regForm.setAadharCheckOwner("");
				regForm.setAadharRespCodeOwner("");
				regForm.setAadharErrMsgOwner("");
				regForm.setTrnsOwnerList(new HashMap());
				regForm.setGovernmentOfficeNameTrans("");
				logger.debug("partyTypeTrns:-" + partyTypeTrns);
				forward = "transactingParty";
				// saveToken(request);
			}
			if (!RegInitConstant.GENDER_MODIFY_ACTION.equals(actionName) && !RegInitConstant.AUTH_PER_GENDER_MODIFY_ACTION.equals(actionName)) {
				// below code for getting state and district for transacting
				// party
				// for getting organization state list
				if (regForm.getCountryTrns() != null && !regForm.getCountryTrns().equalsIgnoreCase("") && !regForm.getCountryTrns().equalsIgnoreCase("null")) {
					commonDto.setStateTrns(commonBo.getState(regForm.getCountryTrns(), languageLocale));
					forward = "transactingParty";
				} else {
					commonDto.setStateTrns(new ArrayList());
				}
				// for getting organization district list
				if (regForm.getStatenameTrns() != null && !regForm.getStatenameTrns().equalsIgnoreCase("") && !regForm.getStatenameTrns().equalsIgnoreCase("null")) {
					commonDto.setDistrictTrns(commonBo.getDistrict(regForm.getStatenameTrns(), languageLocale));
					forward = "transactingParty";
				} else {
					commonDto.setDistrictTrns(new ArrayList());
				}
				// for getting individual state list
				if (regForm.getIndcountryTrns() != null && !regForm.getIndcountryTrns().equalsIgnoreCase("") && !regForm.getIndcountryTrns().equalsIgnoreCase("null")) {
					commonDto.setIndstateTrns(commonBo.getState(regForm.getIndcountryTrns(), languageLocale));
					forward = "transactingParty";
				} else {
					commonDto.setIndstateTrns(new ArrayList());
				}
				// for getting individual district list
				if (regForm.getIndstatenameTrns() != null && !regForm.getIndstatenameTrns().equalsIgnoreCase("") && !regForm.getIndstatenameTrns().equalsIgnoreCase("null")) {
					commonDto.setInddistrictTrns(commonBo.getDistrict(regForm.getIndstatenameTrns(), languageLocale));
					forward = "transactingParty";
				} else {
					commonDto.setInddistrictTrns(new ArrayList());
				}
				// end of code for populating drop downs
			}
			// NEXT TO PROPERTY DETAILS PAGE THROUGH TRANSACTING PARTIES PAGE
			if (RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName)) {
				// below code is for the insertion of last transacting party in
				// map
				RegCommonDTO mapDto = new RegCommonDTO();

				String latestOwnerAdded = "";// added by akansha to fix owner
				// issue: 4th march 2020
				// following code for insertion of owner details into map
				int roleId = Integer.parseInt(regForm.getPartyTypeTrns());
				String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
				int claimantFlag = Integer.parseInt(claimantArr[0].trim());
				mapDto.setClaimantFlag(claimantFlag);
				if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
					mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					RegCommonDTO mapDto1 = new RegCommonDTO();
					mapDto1.setOwnerNameTrns(regForm.getOwnerNameTrns());
					if (regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null"))
						mapDto1.setOwnerOgrNameTrns("-");
					else
						mapDto1.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());
					mapDto1.setOwnerGendarValTrns(regForm.getOwnerGendarTrns());
					if (regForm.getOwnerGendarTrns().equalsIgnoreCase("f")) {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);
						} else {
							mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);
						}
					} else if (regForm.getOwnerGendarTrns().equalsIgnoreCase("m")) {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);
						} else {
							mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);
						}
					} else {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto1.setOwnerGendarTrns(RegInitConstant.other_gender_ENGLISH);
						} else {
							mapDto1.setOwnerGendarTrns(RegInitConstant.other_gender_hindi);
						}
					}
					mapDto1.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
					mapDto1.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
					mapDto1.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
					if (regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
						mapDto1.setOwnerEmailIDTrns("-");
					else
						mapDto1.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());
					mapDto1.setOwnerAgeTrns(regForm.getOwnerAgeTrns());
					mapDto1.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdnoTrns());
					mapDto1.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
					// added for aadhar integration - start
					/*
					 * if (regForm.getClrFlg() != null) { if
					 * (regForm.getClrFlg().equalsIgnoreCase("y") &&
					 * regForm.getListAdoptedPartyTrns
					 * ().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
					 * mapDto1.setOwnerListIDTrns("7"); } } else {
					 * mapDto1.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
					 * }
					 */
					// added for aadhar integration - end
					mapDto1.setOwnerProofNameTrns(regForm.getOwnerListIDTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofNameTrns());
					if (regForm.getAddressOwnerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMpTrns())) {
						mapDto1.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMpTrns());
					} else {
						mapDto1.setAddressOwnerOutMpTrns("-");
					}
					// added for aadhar - start
					if (regForm.getAadharNameTrnsOwner() != null && !("").equalsIgnoreCase(regForm.getAadharNameTrnsOwner())) {
						mapDto1.setAadharNameTrnsOwner(regForm.getAadharNameTrnsOwner());
						regForm.setAadharNameTrnsOwner("");
						regForm.setAadharErrMsgOwner("");
						regForm.setAadharRespCodeOwner("");
						regForm.setAadharCheckOwner("");
					} else {
						mapDto1.setAadharNameTrnsOwner("");
					}
					if (regForm.getAadharRespDto() != null) {
						if (!"".equalsIgnoreCase(regForm.getAadharRespDto().getTransactionCode()) && !"".equalsIgnoreCase(regForm.getAadharRespDto().getTransactionId())) {
							mapDto1.setAadharRespDto(regForm.getAadharRespDto());
						}
					}
					// added for aadhar - end
					mapDto1.setOwnerIndCategoryTrns(regForm.getOwnerIndCategoryTrns());
					mapDto1.setOwnerIndcountryTrns(regForm.getOwnerIndcountryTrns());
					mapDto1.setOwnerIndstatenameTrns(regForm.getOwnerIndstatenameTrns());
					mapDto1.setOwnerInddistrictTrns(regForm.getOwnerInddistrictTrns());
					mapDto1.setOwnerIndphnoTrns(regForm.getOwnerIndphnoTrns());
					mapDto1.setOwnerPostalCodeTrns(regForm.getOwnerPostalCodeTrns());
					mapDto1.setOwnerIndDisabilityTrns(regForm.getOwnerIndDisabilityTrns());
					mapDto1.setOwnerIndDisabilityDescTrns(regForm.getOwnerIndDisabilityDescTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIndDisabilityDescTrns());
					mapDto1.setOwnerIndMinorityTrns(regForm.getOwnerIndMinorityTrns());
					mapDto1.setOwnerIndPovertyLineTrns(regForm.getOwnerIndPovertyLineTrns());
					mapDto1.setOwnerOccupationIdTrns(regForm.getOwnerOccupationIdTrns());
					mapDto1.setOwnerRelationshipTrns(regForm.getOwnerRelationshipTrns());
					mapDto1.setOwnerFatherNameTrns(regForm.getOwnerFatherNameTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerFatherNameTrns());
					mapDto1.setOwnerMotherNameTrns(regForm.getOwnerMotherNameTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerMotherNameTrns());
					mapDto1.setOwnerSpouseNameTrns(regForm.getOwnerSpouseNameTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerSpouseNameTrns());
					// mapDto1.setOwnerPanNumberTrns(regForm.getOwnerPanNumberTrns());
					mapDto1.setOwnerPanNumberTrns(regForm.getOwnerPanNumberTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerPanNumberTrns());
					mapDto1.setOwnerPermissionNoTrns(regForm.getOwnerPermissionNoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerPermissionNoTrns());
					mapDto1.setOwnerIndScheduleAreaTrns(regForm.getOwnerIndScheduleAreaTrns().equalsIgnoreCase("") ? "" : regForm.getOwnerIndScheduleAreaTrns());
					mapDto1.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());
					latestOwnerAdded = mapDto1.getOwnerTxnId();// added by
					// akansha
					regForm.getTrnsOwnerList().put(mapDto1.getOwnerTxnId(), mapDto1);// added
					// by
					// akansha
					mapDto.setTrnsOwnerList(regForm.getTrnsOwnerList());
				}
				mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedPartyTrns());
				mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedPartyTrns().trim(), languageLocale));
				mapDto.setPurposeTrns("");
				mapDto.setBname(""); // might be changed
				mapDto.setBaddress(""); // might be changed
				mapDto.setApplicantOrTransParty("Transacting");
				mapDto.setPartyTypeTrns(regForm.getPartyTypeTrns());
				regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
				mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
				mapDto.setUserID(regForm.getHidnUserId());
				if (regForm.getRelationWithOwnerTrns().equalsIgnoreCase("") || regForm.getRelationWithOwnerTrns().equalsIgnoreCase("null")) {
					mapDto.setRelationWithOwnerTrns("-");
				} else {
					mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwnerTrns());
				}
				// FOLLOWING CODE FOR SETTING PARTY TYPE FLAG
				// int
				// selectedRoleId=Integer.parseInt(regForm.getPartyTypeTrns());
				mapDto.setPartyTypeFlag("N");
				mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyTypeTrns(), languageLocale, regForm.getDeedID(), regForm.getInstID()));
				if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					if (regForm.getGovernmentOfficeNameTrans() != null && !regForm.getGovernmentOfficeNameTrans().isEmpty()) {
						mapDto.setGovtOffcNameTrns(regForm.getGovernmentOfficeNameTrans());
						// regForm.setGovernmentOfficeNameTrans("");
					}
					// mapDto.setGovtOfcCheck(regForm.getGovtOfcCheck());
					if (regForm.getNameOfOfficialTrns() != null && !("").equalsIgnoreCase(regForm.getNameOfOfficialTrns())) {
						mapDto.setNameOfOfficial(regForm.getNameOfOfficialTrns());
					} else {
						mapDto.setNameOfOfficial("-");
					}
					mapDto.setDesgOfOfficial(regForm.getDesgOfOfficialTrns());
					mapDto.setAddressOfOfficial(regForm.getAddressOfOfficialTrns());
					mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));
					mapDto.setOgrNameTrns("-");
					// mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim());
					mapDto.setIndAuthPersn(mapDto.getDesgOfOfficial());
					mapDto.setIndividualOrOrganization(RegInitConstant.GOVT_OFFCL_ID);
					if (regForm.getAddressGovtOffclOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressGovtOffclOutMpTrns())) {
						mapDto.setAddressGovtOffclOutMpTrns(regForm.getAddressGovtOffclOutMpTrns());
					} else {
						mapDto.setAddressGovtOffclOutMpTrns("-");
					}
					if (regForm.getGovernmentOfficeNameTrans() != null && !("").equalsIgnoreCase(regForm.getGovernmentOfficeNameTrans())) {
						String offcName = commonBd.getGovtOffcName(regForm.getGovernmentOfficeNameTrans(), languageLocale);
						mapDto.setGovtOfficeName(offcName);
						regForm.setGovernmentOfficeNameTrans("");
					} else {
						mapDto.setGovtOfficeName("-");
						regForm.setGovernmentOfficeNameTrans("");
					}
				} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
					// organization
					mapDto.setOgrNameTrns(regForm.getOgrNameTrns());
					mapDto.setAuthPerNameTrns(regForm.getAuthPerNameTrns());
					mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
					mapDto.setOrgaddressTrns(regForm.getOrgaddressTrns());
					mapDto.setSelectedCountry(regForm.getCountryTrns());
					mapDto.setSelectedCountryName(regForm.getCountryNameTrns());
					mapDto.setSelectedState(regForm.getStatenameTrns());
					mapDto.setSelectedStateName(regForm.getStatenameNameTrns());
					mapDto.setSelectedDistrict(regForm.getDistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrictTrns(), languageLocale));
					if (regForm.getMobnoTrns().equalsIgnoreCase("") || regForm.getMobnoTrns().equalsIgnoreCase("null"))
						mapDto.setMobnoTrns("-");
					else
						mapDto.setMobnoTrns(regForm.getMobnoTrns());
					if (regForm.getPhnoTrns().equalsIgnoreCase("") || regForm.getPhnoTrns().equalsIgnoreCase("null"))
						mapDto.setPhnoTrns("-");
					else
						mapDto.setPhnoTrns(regForm.getPhnoTrns());
					mapDto.setConsiAmountTrns(regForm.getConsiAmountTrns());
					mapDto.setMarketValueTrns(regForm.getMarketValueTrns());
					mapDto.setFnameTrns("");
					mapDto.setMnameTrns("");
					mapDto.setLnameTrns("");
					// mapDto.setGendarTrns("-");
					// mapDto.setSelectedGender("");
					mapDto.setAgeTrns("");
					// mapDto.setFatherNameTrns("-");
					mapDto.setMotherNameTrns("");
					mapDto.setSpouseNameTrns("");
					mapDto.setNationalityTrns("");
					mapDto.setPostalCodeTrns("");
					mapDto.setEmailIDTrns("");
					mapDto.setSelectedPhotoId("");
					mapDto.setSelectedPhotoIdName("");
					mapDto.setIdnoTrns("-");
					mapDto.setIndReligionTrns("");
					mapDto.setIndCasteTrns("");
					mapDto.setIndDisabilityTrns("");
					mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
					mapDto.setAuthPerGendarTrns(regForm.getAuthPerGendarTrns());
					if (regForm.getAuthPerGendarTrns().equalsIgnoreCase("m"))
						mapDto.setSelectedGender("Male");
					else
						mapDto.setSelectedGender("Female");
					mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherNameTrns());
					mapDto.setRelationshipTrns(regForm.getAuthPerRelationshipTrns());
					mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationshipTrns()), languageLocale));
					mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddressTrns().trim());
					mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountryTrns());
					mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountryTrns(), languageLocale));
					mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatenameTrns());
					mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatenameTrns(), languageLocale));
					mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrictTrns());
					mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrictTrns().trim(), languageLocale));
					if (regForm.getAddressOrgOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOrgOutMpTrns())) {
						mapDto.setAddressOrgOutMpTrns(regForm.getAddressOrgOutMpTrns());
					} else {
						mapDto.setAddressOrgOutMpTrns("-");
					}
					if (regForm.getAddressAuthPerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressAuthPerOutMpTrns())) {
						mapDto.setAddressAuthPerOutMpTrns(regForm.getAddressAuthPerOutMpTrns());
					} else {
						mapDto.setAddressAuthPerOutMpTrns("-");
					}
				} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
					// individual
					mapDto.setFnameTrns(regForm.getFnameTrns());
					if (regForm.getMnameTrns().equalsIgnoreCase("") || regForm.getMnameTrns().equalsIgnoreCase("null"))
						mapDto.setMnameTrns("-");
					else
						mapDto.setMnameTrns(regForm.getMnameTrns());
					mapDto.setLnameTrns(regForm.getLnameTrns());
					mapDto.setGendarTrns(regForm.getGendarTrns());
					if (regForm.getGendarTrns().equalsIgnoreCase("m"))
						mapDto.setSelectedGender("Male");
					else
						mapDto.setSelectedGender("Female");
					if (regForm.getAgeTrns().equalsIgnoreCase("") || regForm.getAgeTrns().equalsIgnoreCase("null"))
						mapDto.setAgeTrns("-");
					else
						mapDto.setAgeTrns(regForm.getAgeTrns());
					mapDto.setFatherNameTrns(regForm.getFatherNameTrns());
					if (regForm.getMotherNameTrns().equalsIgnoreCase("") || regForm.getMotherNameTrns().equalsIgnoreCase("null"))
						mapDto.setMotherNameTrns("-");
					else
						mapDto.setMotherNameTrns(regForm.getMotherNameTrns());
					if (regForm.getSpouseNameTrns().equalsIgnoreCase("") || regForm.getSpouseNameTrns().equalsIgnoreCase("null"))
						mapDto.setSpouseNameTrns("-");
					else
						mapDto.setSpouseNameTrns(regForm.getSpouseNameTrns());
					mapDto.setNationalityTrns(regForm.getNationalityTrns());
					// For Guardian - Rahul
					if (regForm.getGuardianTrans().equalsIgnoreCase("") || regForm.getGuardianTrans().equalsIgnoreCase("null"))
						mapDto.setGuardianTrans("-");
					else
						mapDto.setGuardianTrans(regForm.getGuardianTrans());
					if (regForm.getPostalCodeTrns().equalsIgnoreCase("") || regForm.getPostalCodeTrns().equalsIgnoreCase("null"))
						mapDto.setPostalCodeTrns("-");
					else
						mapDto.setPostalCodeTrns(regForm.getPostalCodeTrns());
					if (regForm.getIndphnoTrns().equalsIgnoreCase("") || regForm.getIndphnoTrns().equalsIgnoreCase("null"))
						mapDto.setPhnoTrns("-");
					else
						mapDto.setPhnoTrns(regForm.getIndphnoTrns());
					if (regForm.getIndmobnoTrns().equalsIgnoreCase("") || regForm.getIndmobnoTrns().equalsIgnoreCase("null"))
						mapDto.setMobnoTrns("-");
					else
						mapDto.setMobnoTrns(regForm.getIndmobnoTrns());
					if (regForm.getEmailIDTrns().equalsIgnoreCase("") || regForm.getEmailIDTrns().equalsIgnoreCase("null"))
						mapDto.setEmailIDTrns("-");
					else
						mapDto.setEmailIDTrns(regForm.getEmailIDTrns());
					mapDto.setSelectedPhotoId(regForm.getListIDTrns());
					// added for aadhar by ankit - start
					if (regForm.getAadharNameTrns() != null && !regForm.getAadharNameTrns().isEmpty()) {
						mapDto.setAadharNameTrns(regForm.getAadharNameTrns());
					}
					if (regForm.getTransactionId() != null && regForm.getAcknowledgementId() != null) {
						mapDto.setAcknowledgementId(regForm.getAcknowledgementId());
						mapDto.setTransactionId(regForm.getTransactionId());
					}
					// added for aadhar by ankit - end
					mapDto.setSelectedPhotoIdName(regForm.getListIDNameTrns());
					mapDto.setIdnoTrns(regForm.getIdnoTrns());
					mapDto.setOgrNameTrns("-");
					mapDto.setAuthPerNameTrns("-");
					mapDto.setIndAuthPersn(regForm.getFnameTrns() + " " + regForm.getLnameTrns());
					mapDto.setOrgaddressTrns(regForm.getIndaddressTrns());
					mapDto.setSelectedCountry(regForm.getIndcountryTrns());
					mapDto.setSelectedCountryName(regForm.getIndcountryNameTrns());
					mapDto.setSelectedState(regForm.getIndstatenameTrns());
					mapDto.setSelectedStateName(regForm.getIndstatenameNameTrns());
					mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));
					mapDto.setIndCategoryTrns(regForm.getIndCategoryTrns());
					mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationIdTrns(), languageLocale));
					mapDto.setOccupationIdTrns(regForm.getOccupationIdTrns());
					mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleAreaTrns());
					if (regForm.getIndScheduleAreaTrns() != null) {
						if (regForm.getIndScheduleAreaTrns().equalsIgnoreCase("Y")) {
							mapDto.setIndScheduleAreaTrnsDisplay("Yes");
						} else {
							mapDto.setIndScheduleAreaTrnsDisplay("No");
							mapDto.setPermissionNoTrns(regForm.getPermissionNoTrns());
							mapDto.setDocumentNameTrns(regForm.getDocumentNameTrns());
							mapDto.setDocumentSizeTrns(regForm.getDocumentSizeTrns());
							mapDto.setDocContentsTrns(regForm.getDocContentsTrns());
							mapDto.setFilePathTrns(regForm.getFilePathTrns());
							mapDto.setPartyOrPropTrns(regForm.getPartyOrPropTrns());
							mapDto.setUploadTypeTrns(regForm.getUploadTypeTrns());
						}
					}
					if (regForm.getIndDisabilityTrns().equalsIgnoreCase("") || regForm.getIndDisabilityTrns().equalsIgnoreCase("null")) {
						mapDto.setIndDisabilityTrns("-");
					} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("Y")) {
						mapDto.setIndDisabilityTrns("Yes");
						if (regForm.getIndDisabilityDescTrns().equalsIgnoreCase("") || regForm.getIndDisabilityDescTrns().equalsIgnoreCase("null")) {
							mapDto.setIndDisabilityDescTrns("-");
						} else {
							mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDescTrns());
						}
					} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("N")) {
						mapDto.setIndDisabilityTrns("No");
					}
					mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
					// mapDto.setUserDOBTrns(UserRegistrationBD.getOracleDate(regForm.getUserDayTrns(),
					// regForm.getUserMonthTrns(), regForm.getUserYearTrns()));
					if (regForm.getIndMinorityTrns().equalsIgnoreCase("") || regForm.getIndMinorityTrns().equalsIgnoreCase("null")) {
						mapDto.setIndMinorityTrns("-");
					} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("Y")) {
						mapDto.setIndMinorityTrns("Yes");
					} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("N")) {
						mapDto.setIndMinorityTrns("No");
					}
					// mapDto.setIndMinorityTrns("");
					if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("") || regForm.getIndPovertyLineTrns().equalsIgnoreCase("null")) {
						mapDto.setIndPovertyLineTrns("-");
					} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("Y")) {
						mapDto.setIndPovertyLineTrns("Yes");
					} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("N")) {
						mapDto.setIndPovertyLineTrns("No");
					}
					mapDto.setRelationshipTrns(regForm.getRelationshipTrns());
					mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationshipTrns()), languageLocale));
					if (regForm.getAddressIndOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressIndOutMpTrns())) {
						mapDto.setAddressIndOutMpTrns(regForm.getAddressIndOutMpTrns());
					} else {
						mapDto.setAddressIndOutMpTrns("-");
					}
				}
				// anuj set in dto from form
				// below code for uploads other than collector's permission no.
				if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					mapDto.setU2DocumentNameTrns(regForm.getU2DocumentNameTrns());
					mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSizeTrns());
					mapDto.setU2FilePathTrns(regForm.getU2FilePathTrns());
					mapDto.setU2DocContentsTrns(regForm.getU2DocContentsTrns());
					mapDto.setU2PartyOrPropTrns(regForm.getU2PartyOrPropTrns());
					mapDto.setU2UploadTypeTrns(regForm.getU2UploadTypeTrns());
					// BELOW CODE FOR EXECUTANT
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
						mapDto.setU3DocumentNameTrns(regForm.getU3DocumentNameTrns());
						mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSizeTrns());
						mapDto.setU3FilePathTrns(regForm.getU3FilePathTrns());
						mapDto.setU3DocContentsTrns(regForm.getU3DocContentsTrns());
						mapDto.setU3PartyOrPropTrns(regForm.getU3PartyOrPropTrns());
						mapDto.setU3UploadTypeTrns(regForm.getU3UploadTypeTrns());
						/*
						 * mapDto.setU4DocumentNameTrns(regForm.getU4DocumentNameTrns
						 * ());mapDto.setU4DocumentSizeTrns(regForm.
						 * getU4DocumentSizeTrns());
						 * mapDto.setU4FilePathTrns(regForm
						 * .getU4FilePathTrns());
						 * mapDto.setU4DocContentsTrns(regForm
						 * .getU4DocContentsTrns());
						 * mapDto.setU4PartyOrPropTrns(
						 * regForm.getU4PartyOrPropTrns());
						 * mapDto.setU4UploadTypeTrns
						 * (regForm.getU4UploadTypeTrns());
						 */
						mapDto.setU10DocumentNameTrns(regForm.getU10DocumentNameTrns());
						mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSizeTrns());
						mapDto.setU10FilePathTrns(regForm.getU10FilePathTrns());
						mapDto.setU10DocContentsTrns(regForm.getU10DocContentsTrns());
						mapDto.setU10PartyOrPropTrns(regForm.getU10PartyOrPropTrns());
						mapDto.setU10UploadTypeTrns(regForm.getU10UploadTypeTrns());
					}
					// BELOW CODE FOR EXECUTANT POA HOLDER
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_5) {
						/*
						 * mapDto.setU5DocumentNameTrns(regForm.getU5DocumentNameTrns
						 * ());mapDto.setU5DocumentSizeTrns(regForm.
						 * getU5DocumentSizeTrns());
						 * mapDto.setU5FilePathTrns(regForm
						 * .getU5FilePathTrns());
						 * mapDto.setU5DocContentsTrns(regForm
						 * .getU5DocContentsTrns());
						 * mapDto.setU5PartyOrPropTrns(
						 * regForm.getU5PartyOrPropTrns());
						 * mapDto.setU5UploadTypeTrns
						 * (regForm.getU5UploadTypeTrns());
						 */
						mapDto.setU6DocumentNameTrns(regForm.getU6DocumentNameTrns());
						mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSizeTrns());
						mapDto.setU6FilePathTrns(regForm.getU6FilePathTrns());
						mapDto.setU6DocContentsTrns(regForm.getU6DocContentsTrns());
						mapDto.setU6PartyOrPropTrns(regForm.getU6PartyOrPropTrns());
						mapDto.setU6UploadTypeTrns(regForm.getU6UploadTypeTrns());
						/*
						 * mapDto.setU7DocumentNameTrns(regForm.getU7DocumentNameTrns
						 * ());mapDto.setU7DocumentSizeTrns(regForm.
						 * getU7DocumentSizeTrns());
						 * mapDto.setU7FilePathTrns(regForm
						 * .getU7FilePathTrns());
						 * mapDto.setU7DocContentsTrns(regForm
						 * .getU7DocContentsTrns());
						 * mapDto.setU7PartyOrPropTrns(
						 * regForm.getU7PartyOrPropTrns());
						 * mapDto.setU7UploadTypeTrns
						 * (regForm.getU7UploadTypeTrns());
						 */
						mapDto.setSrOfficeNameTrns(regForm.getSrOfficeNameTrns());
						if (regForm.getPoaRegNoTrns() != null && !regForm.getPoaRegNoTrns().equalsIgnoreCase("")) {
							mapDto.setPoaRegNoTrns(regForm.getPoaRegNoTrns());
						} else {
							mapDto.setPoaRegNoTrns("-");
						}
						if (regForm.getDatePoaRegTrns() != null && !regForm.getDatePoaRegTrns().equalsIgnoreCase("")) {
							mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaRegTrns()));
						} else {
							mapDto.setDatePoaRegTrns("-");
						}
					}
					// BELOW CODE FOR CLAIMANT
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
						/*
						 * mapDto.setU8DocumentNameTrns(regForm.getU8DocumentNameTrns
						 * ());mapDto.setU8DocumentSizeTrns(regForm.
						 * getU8DocumentSizeTrns());
						 * mapDto.setU8FilePathTrns(regForm
						 * .getU8FilePathTrns());
						 * mapDto.setU8DocContentsTrns(regForm
						 * .getU8DocContentsTrns());
						 * mapDto.setU8PartyOrPropTrns(
						 * regForm.getU8PartyOrPropTrns());
						 * mapDto.setU8UploadTypeTrns
						 * (regForm.getU8UploadTypeTrns());
						 */
						mapDto.setU11DocumentNameTrns(regForm.getU11DocumentNameTrns());
						mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSizeTrns());
						mapDto.setU11FilePathTrns(regForm.getU11FilePathTrns());
						mapDto.setU11DocContentsTrns(regForm.getU11DocContentsTrns());
						mapDto.setU11PartyOrPropTrns(regForm.getU11PartyOrPropTrns());
						mapDto.setU11UploadTypeTrns(regForm.getU11UploadTypeTrns());
					}
					// BELOW CODE FOR CLAIMANT POA HOLDER
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_6) {
						mapDto.setU9DocumentNameTrns(regForm.getU9DocumentNameTrns());
						mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSizeTrns());
						mapDto.setU9FilePathTrns(regForm.getU9FilePathTrns());
						mapDto.setU9DocContentsTrns(regForm.getU9DocContentsTrns());
						mapDto.setU9PartyOrPropTrns(regForm.getU9PartyOrPropTrns());
						mapDto.setU9UploadTypeTrns(regForm.getU9UploadTypeTrns());
					}
				}
				mapDto.setListDto(regForm.getListDto());
				map = regForm.getMapTransactingParties();
				int count = regForm.getMapKeyCount();
				if (count == 0)
					count = 1;
				if (map.containsKey(Integer.toString(count))) {
					count++;
					map.put(Integer.toString(count), mapDto);
				} else
					map.put(Integer.toString(count), mapDto);
				regForm.setMapKeyCount(count);
				regForm.setAddMoreCounter(count);
				// key="key";
				regForm.setMapTransactingParties(map);
				Collection mapCol = regForm.getMapTransactingParties().values();
				Object[] l2 = mapCol.toArray();
				RegCommonDTO mapDto2 = new RegCommonDTO();
				int roleId1 = 0;
				int partyType1Or2 = 0;
				int party1Added = 0;
				int party2Added = 0;
				int party3Added = 0;
				for (int i = 0; i < l2.length; i++) {
					mapDto2 = (RegCommonDTO) l2[i];
					if (mapDto2.getPartyTypeTrns() != null && !mapDto2.getPartyTypeTrns().equalsIgnoreCase("")) {
						roleId1 = Integer.parseInt(mapDto2.getPartyTypeTrns());
					}
					partyType1Or2 = commonBo.getPartyType1Or2(regForm.getDeedID(), regForm.getInstID(), roleId1);
					if (partyType1Or2 == 1) {
						party1Added = 1;
					} else if (partyType1Or2 == 2) {
						party2Added = 1;
					} else {
						party3Added = 1;
					}
				}
				if (regForm.getDeedID() == RegInitConstant.DEED_TRUST || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && !(regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_2)) || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV) {
					if (party1Added == 0) {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_EN);
							// regForm.setTrnsOwnerList(new HashMap());
							regForm.getTrnsOwnerList().remove(latestOwnerAdded);// added
							// by
							// akansha
						} else {
							request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_HI);
							// regForm.setTrnsOwnerList(new HashMap());
							regForm.getTrnsOwnerList().remove(latestOwnerAdded);// added
							// by
							// akansha
						}
						map.remove(Integer.toString(count));
						regForm.setMapKeyCount(--count);
						regForm.setAddMoreCounter(count);
						regForm.setMapTransactingParties(map);
						actionName = RegInitConstant.NO_ACTION;
						regForm.setActionName(RegInitConstant.NO_ACTION);
						request.setAttribute("deedId", regForm.getDeedID());
						request.setAttribute("instId", regForm.getInstID());
						request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
						forward = "transactingParty";
						return mapping.findForward(forward);
					}
				} else if (party1Added == 0 || party2Added == 0) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_EN);
						// regForm.setTrnsOwnerList(new HashMap());
						regForm.getTrnsOwnerList().remove(latestOwnerAdded);// added
						// by
						// akansha
					} else {
						request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_HI);
						// regForm.setTrnsOwnerList(new HashMap());
						regForm.getTrnsOwnerList().remove(latestOwnerAdded);// added
						// by
						// akansha
					}
					mapDto.setTrnsOwnerList(regForm.getTrnsOwnerList());// added
					// by
					// akansha
					map.remove(Integer.toString(count));
					regForm.setMapKeyCount(--count);
					regForm.setAddMoreCounter(count);
					regForm.setMapTransactingParties(map);
					actionName = RegInitConstant.NO_ACTION;
					regForm.setActionName(RegInitConstant.NO_ACTION);
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", regForm.getInstID());
					request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
					forward = "transactingParty";
					return mapping.findForward(forward);
				}
				/*
				 * else if(party1Added==0){
				 * if(languageLocale.equalsIgnoreCase(RegInitConstant
				 * .LANGUAGE_ENGLISH)){ request.setAttribute("shareErrorMsg",
				 * RegInitConstant.MESSAGE_3_New_EN); }else{
				 * request.setAttribute("shareErrorMsg",
				 * RegInitConstant.MESSAGE_3_HI); }
				 * 
				 * map.remove(Integer.toString(count));
				 * regForm.setMapKeyCount(--count);
				 * regForm.setAddMoreCounter(count);
				 * regForm.setMapTransactingParties(map);
				 * actionName=RegInitConstant.NO_ACTION;
				 * regForm.setActionName(RegInitConstant.NO_ACTION);
				 * request.setAttribute("deedId", regForm.getDeedID());
				 * request.setAttribute("instId", regForm.getInstID());
				 * request.setAttribute("roleIdTrns",
				 * regForm.getPartyTypeTrns()); forward="transactingParty";
				 * return mapping.findForward(forward); }
				 */
				// end of insertion of last transacting party in map
				regForm.getMapTransPartyDbInsertion().put(Integer.toString(count), mapDto);
				// below code is for insertion of transacting parties in
				// database.
				Collection mapCollection = regForm.getMapTransPartyDbInsertion().values();
				Object[] l1 = mapCollection.toArray();
				RegCommonDTO mapDto1 = new RegCommonDTO();
				boolean allUploadSuccessful = false;
				boolean transPartyDetailsInserted = false;
				// below loop for creating folder structure of uploads
				String filePath;
				String filePathPhotoProof;
				String filePathNotAffExec;
				String filePathExecPhoto;
				String filePathNotAffAttrn;
				String filePathAttrnPhoto;
				String filePathClaimPhoto;
				String filePathPanForm60;
				// above declarations added because of audit report
				for (int i = 0; i < l1.length; i++) {
					allUploadSuccessful = false;
					mapDto1 = (RegCommonDTO) l1[i];
					mapDto1.setU2FilePathTrns("");
					mapDto1.setU3FilePathTrns("");
					mapDto1.setU10FilePathTrns("");
					mapDto1.setU6FilePathTrns("");
					mapDto1.setU11FilePathTrns("");
					mapDto1.setU9FilePathTrns("");
					mapDto1.setFilePathTrns("");
					filePath = "";
					filePathPhotoProof = "";
					filePathNotAffExec = "";
					filePathExecPhoto = "";
					filePathNotAffAttrn = "";
					filePathAttrnPhoto = "";
					filePathClaimPhoto = "";
					filePathPanForm60 = "";
					// above commented for audit report
					int dtoRoleId = Integer.parseInt(mapDto1.getPartyTypeTrns());
					String[] claimantArr1 = commonBo.getClaimantFlag(Integer.toString(dtoRoleId));
					int claimantFlag1 = Integer.parseInt(claimantArr1[0].trim());
					boolean additionalUpload = false;
					ArrayList<CommonDTO> dto = mapDto1.getListDto();
					if (dto != null && dto.size() > 0) {
						for (int j = 0; j < dto.size(); j++) {
							CommonDTO dtos = dto.get(j);
							dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(j)));
							String checkUpload = uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), mapDto1.getPartyRoleTypeId(), "01", dtos.getDocumentName());
							if (checkUpload == null) {
								additionalUpload = false;
								break;
							} else {
								dtos.setDocumentPath(checkUpload);
								additionalUpload = true;
							}
						}
					} else {
						additionalUpload = true;
					}
					// mohit additional upload
					if (!mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
						if (additionalUpload) {
							if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID) || mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
								if ((mapDto1.getU2DocumentNameTrns() != null && !mapDto1.getU2DocumentNameTrns().equalsIgnoreCase(""))) {
									filePathPhotoProof = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU2DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU2PartyOrPropTrns(), mapDto1.getU2UploadTypeTrns());
								} else {
									filePathPhotoProof = "";
								}
								if (filePathPhotoProof != null) {
									mapDto1.setU2FilePathTrns(filePathPhotoProof);
									// BELOW CODE FOR EXECUTANT
									if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_1) {
										if (mapDto1.getU3DocumentNameTrns() != null && !mapDto1.getU3DocumentNameTrns().equalsIgnoreCase("")) {
											filePathNotAffExec = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU3DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU3PartyOrPropTrns(), mapDto1.getU3UploadTypeTrns());
										} else {
											filePathNotAffExec = "";
										}
										if (filePathNotAffExec != null) {
											mapDto1.setU3FilePathTrns(filePathNotAffExec);
											// filePathExecPhoto=uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU4DocContentsTrns(),
											// mapDto1.getPartyRoleTypeId(),mapDto1.getU4PartyOrPropTrns(),mapDto1.getU4UploadTypeTrns());
											// if(filePathExecPhoto!=null &&
											// !filePathExecPhoto.equalsIgnoreCase("")){
											// mapDto1.setU4FilePathTrns(filePathExecPhoto);
											if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
												if (!mapDto1.getSelectedPhotoId().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU10DocumentNameTrns() != null && !mapDto1.getU10DocumentNameTrns().equalsIgnoreCase("")))) {
													filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU10DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU10PartyOrPropTrns(), mapDto1.getU10UploadTypeTrns());
													if (filePathPanForm60 != null && !filePathPanForm60.equalsIgnoreCase("")) {
														mapDto1.setU10FilePathTrns(filePathPanForm60);
														allUploadSuccessful = true;
													} else {
														allUploadSuccessful = false;
														break;
													}
												} else {
													allUploadSuccessful = true;
												}
											} else {
												if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU10DocumentNameTrns() != null && !mapDto1.getU10DocumentNameTrns().equalsIgnoreCase("")))) {
													filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU10DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU10PartyOrPropTrns(), mapDto1.getU10UploadTypeTrns());
													if (filePathPanForm60 != null && !filePathPanForm60.equalsIgnoreCase("")) {
														mapDto1.setU10FilePathTrns(filePathPanForm60);
														allUploadSuccessful = true;
													} else {
														allUploadSuccessful = false;
														break;
													}
												} else {
													allUploadSuccessful = true;
												}
											}
											// }else{
											// allUploadSuccessful=false;
											// break;
											// }
										} else {
											allUploadSuccessful = false;
											break;
										}
									}
									// BELOW CODE FOR EXECUTANT POA HOLDER
									if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_2 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_5) {
										// filePathExecPhoto=uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU7DocContentsTrns(),
										// mapDto1.getPartyRoleTypeId(),mapDto1.getU7PartyOrPropTrns(),mapDto1.getU7UploadTypeTrns());
										// if(filePathExecPhoto!=null &&
										// !filePathExecPhoto.equalsIgnoreCase(""))
										// {
										// mapDto1.setU7FilePathTrns(filePathExecPhoto);
										/*
										 * filePathNotAffAttrn=uploadFile(regForm
										 * .getHidnRegTxnId(),mapDto1.
										 * getU5DocContentsTrns(),
										 * mapDto1.getPartyRoleTypeId
										 * (),mapDto1.getU5PartyOrPropTrns
										 * (),mapDto1.getU5UploadTypeTrns());
										 * 
										 * if(filePathNotAffAttrn!=null &&
										 * !filePathNotAffAttrn
										 * .equalsIgnoreCase("")) {
										 * mapDto1.setU5FilePathTrns
										 * (filePathNotAffAttrn);
										 */
										filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU6DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU6PartyOrPropTrns(), mapDto1.getU6UploadTypeTrns());
										if (filePathAttrnPhoto != null && !filePathAttrnPhoto.equalsIgnoreCase("")) {
											mapDto1.setU6FilePathTrns(filePathAttrnPhoto);
											allUploadSuccessful = true;
										} else {
											allUploadSuccessful = false;
											break;
										}
										// }
										// }
									}
									// BELOW CODE FOR CLAIMANT
									if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_3) {
										// filePathClaimPhoto=uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU8DocContentsTrns(),
										// mapDto1.getPartyRoleTypeId(),mapDto1.getU8PartyOrPropTrns(),mapDto1.getU8UploadTypeTrns());
										// if(filePathClaimPhoto!=null &&
										// !filePathClaimPhoto.equalsIgnoreCase("")){
										// mapDto1.setU8FilePathTrns(filePathClaimPhoto);
										if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
											if (!mapDto1.getSelectedPhotoId().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU11DocumentNameTrns() != null && !mapDto1.getU11DocumentNameTrns().equalsIgnoreCase("")))) {
												filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU11DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU11PartyOrPropTrns(), mapDto1.getU11UploadTypeTrns());
												if (filePathPanForm60 != null && !filePathPanForm60.equalsIgnoreCase("")) {
													mapDto1.setU11FilePathTrns(filePathPanForm60);
													allUploadSuccessful = true;
												} else {
													allUploadSuccessful = false;
													break;
												}
											} else {
												allUploadSuccessful = true;
											}
										} else {
											if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU11DocumentNameTrns() != null && !mapDto1.getU11DocumentNameTrns().equalsIgnoreCase("")))) {
												filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU11DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU11PartyOrPropTrns(), mapDto1.getU11UploadTypeTrns());
												if (filePathPanForm60 != null && !filePathPanForm60.equalsIgnoreCase("")) {
													mapDto1.setU11FilePathTrns(filePathPanForm60);
													allUploadSuccessful = true;
												} else {
													allUploadSuccessful = false;
													break;
												}
											} else {
												allUploadSuccessful = true;
											}
										}
										// }else{
										// allUploadSuccessful=false;
										// break;
										// }
									}
									// BELOW CODE FOR CLAIMANT POA HOLDER
									if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_4 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_6) {
										filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU9DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU9PartyOrPropTrns(), mapDto1.getU9UploadTypeTrns());
										if (filePathAttrnPhoto != null && !filePathAttrnPhoto.equalsIgnoreCase("")) {
											mapDto1.setU9FilePathTrns(filePathAttrnPhoto);
											allUploadSuccessful = true;
										} else {
											allUploadSuccessful = false;
											break;
										}
									}
									if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
										if (mapDto1.getIndCategoryTrns().equalsIgnoreCase("1") && mapDto1.getIndScheduleAreaTrns().equalsIgnoreCase("N")) {
											filePath = uploadFile(regForm.getHidnRegTxnId(), mapDto1.getDocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getPartyOrPropTrns(), mapDto1.getUploadTypeTrns());
											if (filePath != null && !filePath.equalsIgnoreCase("")) {
												mapDto1.setFilePathTrns(filePath);
												allUploadSuccessful = true;
											} else {
												allUploadSuccessful = false;
												break;
											}
										}
									}
								}
							} else {
								allUploadSuccessful = true;
							}
						}
					} else {
						allUploadSuccessful = true;
					}
				}
				// below loop for inserting data into database
				if (allUploadSuccessful) {
					for (int i = 0; i < l1.length; i++) {
						mapDto1 = (RegCommonDTO) l1[i];
						boolean insertAdditionalUploads = false;
						// mohit database insertion
						if (mapDto1.getListDto() != null && mapDto1.getListDto().size() > 0) {
							insertAdditionalUploads = commonBo.insertAdditionalUploads(mapDto1, regForm);
						} else {
							insertAdditionalUploads = true;
						}
						if (insertAdditionalUploads) {
							mapDto1.setPropertyId(regForm.getPropertyId());
							transPartyDetailsInserted = commonBo.insertTransPartyDetails(mapDto1, regForm, regForm.getExchangePropertyList(), regForm.getIsMultiplePropsFlag(), regForm.getAgriPropertyList());
							// insert in db-anuj
							// clrOwnerShare
							if (!transPartyDetailsInserted) {
								forward = "transactingParty";
								regForm.setHidnRegTxnId(null);
								actionName = RegInitConstant.NO_ACTION;
								regForm.setActionName(RegInitConstant.NO_ACTION);
								break;
							}
						}
					}
					if (transPartyDetailsInserted) {
						forward = "reginitProperty";
					} else {
						forward = "transactingParty";
						regForm.setHidnRegTxnId(null);
						actionName = RegInitConstant.NO_ACTION;
						regForm.setActionName(RegInitConstant.NO_ACTION);
					}
				} else {
					forward = "transactingParty";
					regForm.setHidnRegTxnId(null);
					actionName = RegInitConstant.NO_ACTION;
					regForm.setActionName(RegInitConstant.NO_ACTION);
				}
				regForm.setMapTransPartyDbInsertion(new HashMap());
				// regForm.setActionName(RegInitConstant.NO_ACTION);
				request.setAttribute("propAction", "propAction");
				request.setAttribute("regInitForm", regForm);
				// actionName=RegInitConstant.NO_ACTION;
				regForm.setActionName(RegInitConstant.NO_ACTION);
			}
			if (regForm.getMapKeyCount() == 0)
				regForm.setDeleteTrnsPrtyID("");
			if (RegInitConstant.ADD_MORE_ACTION.equals(actionName)) {
				regForm.setNewParty("1");
				regForm.setActionName("");
				// regForm.setAadharNameTrns(""); // Aadhar Integration
				RegCommonDTO mapDto = new RegCommonDTO();
				// following code for insertion of owner details into map
				int roleId = Integer.parseInt(regForm.getPartyTypeTrns());
				String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
				int claimantFlag = Integer.parseInt(claimantArr[0].trim());
				if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
					mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
					RegCommonDTO mapDto1 = new RegCommonDTO();
					mapDto1.setOwnerNameTrns(regForm.getOwnerNameTrns());
					if (regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null"))
						mapDto1.setOwnerOgrNameTrns("-");
					else
						mapDto1.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());
					mapDto1.setOwnerGendarValTrns(regForm.getOwnerGendarTrns());
					if (regForm.getOwnerGendarTrns().equalsIgnoreCase("f")) {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);
						} else {
							mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);
						}
					} else if (regForm.getOwnerGendarTrns().equalsIgnoreCase("m")) {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);
						} else {
							mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);
						}
					} else {
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto1.setOwnerGendarTrns(RegInitConstant.other_gender_ENGLISH);
						} else {
							mapDto1.setOwnerGendarTrns(RegInitConstant.other_gender_hindi);
						}
					}
					mapDto1.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
					mapDto1.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
					mapDto1.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
					if (regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
						mapDto1.setOwnerEmailIDTrns("-");
					else
						mapDto1.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());
					mapDto1.setOwnerAgeTrns(regForm.getOwnerAgeTrns());
					mapDto1.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdnoTrns());
					mapDto1.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
					/*
					 * if (regForm.getClrFlg() != null) { if
					 * (regForm.getClrFlg().equalsIgnoreCase("y") &&
					 * regForm.getListAdoptedPartyTrns
					 * ().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
					 * mapDto1.setOwnerListIDTrns("7"); } } else {
					 * mapDto1.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
					 * }
					 */
					mapDto1.setOwnerProofNameTrns(regForm.getOwnerListIDTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofNameTrns());
					// changed by akansha
					/*
					 * if (regForm.getClrFlg() != null) { if
					 * (regForm.getClrFlg().equalsIgnoreCase("y") &&
					 * regForm.getListAdoptedPartyTrns
					 * ().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
					 * mapDto1.setOwnerProofNameTrns("AADHAR CARD"); } } else {
					 * mapDto1
					 * .setOwnerProofNameTrns(regForm.getOwnerListIDTrns()
					 * .equalsIgnoreCase("") ? "-" :
					 * regForm.getOwnerProofNameTrns()); }
					 */
					if (regForm.getAddressOwnerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMpTrns())) {
						mapDto1.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMpTrns());
					} else {
						mapDto1.setAddressOwnerOutMpTrns("-");
					}
					mapDto1.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());
					// added for aadhar - start
					if (regForm.getAadharNameTrnsOwner() != null && !("").equalsIgnoreCase(regForm.getAadharNameTrnsOwner())) {
						mapDto1.setAadharNameTrnsOwner(regForm.getAadharNameTrnsOwner());
						regForm.setAadharNameTrnsOwner("");
						regForm.setAadharCheckOwner("");
						regForm.setAadharRespCodeOwner("");
						regForm.setAadharErrMsgOwner("");
					} else {
						mapDto1.setAadharNameTrnsOwner("");
					}
					if (regForm.getAadharRespDto() != null) {
						if (!"".equalsIgnoreCase(regForm.getAadharRespDto().getTransactionCode()) && !"".equalsIgnoreCase(regForm.getAadharRespDto().getTransactionId())) {
							mapDto1.setAadharRespDto(regForm.getAadharRespDto());
						}
					}
					// added for aadhar - end
					mapDto1.setOwnerIndCategoryTrns(regForm.getOwnerIndCategoryTrns());
					mapDto1.setOwnerIndcountryTrns(regForm.getOwnerIndcountryTrns());
					mapDto1.setOwnerIndstatenameTrns(regForm.getOwnerIndstatenameTrns());
					mapDto1.setOwnerInddistrictTrns(regForm.getOwnerInddistrictTrns());
					mapDto1.setOwnerIndphnoTrns(regForm.getOwnerIndphnoTrns().equalsIgnoreCase("") ? "" : regForm.getOwnerIndphnoTrns());
					mapDto1.setOwnerPostalCodeTrns(regForm.getOwnerPostalCodeTrns().equalsIgnoreCase("") ? "" : regForm.getOwnerPostalCodeTrns());
					mapDto1.setOwnerIndDisabilityTrns(regForm.getOwnerIndDisabilityTrns());
					mapDto1.setOwnerIndDisabilityDescTrns(regForm.getOwnerIndDisabilityDescTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIndDisabilityDescTrns());
					mapDto1.setOwnerIndMinorityTrns(regForm.getOwnerIndMinorityTrns());
					mapDto1.setOwnerIndPovertyLineTrns(regForm.getOwnerIndPovertyLineTrns());
					mapDto1.setOwnerOccupationIdTrns(regForm.getOwnerOccupationIdTrns());
					mapDto1.setOwnerRelationshipTrns(regForm.getOwnerRelationshipTrns());
					mapDto1.setOwnerFatherNameTrns(regForm.getOwnerFatherNameTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerFatherNameTrns());
					mapDto1.setOwnerMotherNameTrns(regForm.getOwnerMotherNameTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerMotherNameTrns());
					mapDto1.setOwnerSpouseNameTrns(regForm.getOwnerSpouseNameTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerSpouseNameTrns());
					// mapDto1.setOwnerPanNumberTrns(regForm.getOwnerPanNumberTrns());
					mapDto1.setOwnerPanNumberTrns(regForm.getOwnerPanNumberTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerPanNumberTrns());
					mapDto1.setOwnerPermissionNoTrns(regForm.getOwnerPermissionNoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerPermissionNoTrns());
					mapDto1.setOwnerIndScheduleAreaTrns(regForm.getOwnerIndScheduleAreaTrns().equalsIgnoreCase("") ? "" : regForm.getOwnerIndScheduleAreaTrns());
					mapDto1.setOwnerInddistrictTrnsName(commonBo.getDistrictName(regForm.getOwnerInddistrictTrns(), languageLocale));
					mapDto1.setOwnerIndCategoryTrnsName(commonBo.getCategoryName(regForm.getOwnerIndCategoryTrns(), languageLocale));
					mapDto1.setOwnerOccupationIdTrnsName(commonBo.getOccupationName(regForm.getOwnerOccupationIdTrns(), languageLocale));
					mapDto1.setOwnerRelationshipTrnsName(commonBo.getRelationshipName(regForm.getOwnerRelationshipTrns(), languageLocale));
					regForm.getTrnsOwnerList().put(mapDto1.getOwnerTxnId(), mapDto1);
					mapDto.setTrnsOwnerList(regForm.getTrnsOwnerList());
				}
				mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedPartyTrns());
				mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedPartyTrns().trim(), languageLocale));
				mapDto.setPurposeTrns("");
				mapDto.setBname(""); // might be changed
				mapDto.setBaddress(""); // might be changed
				mapDto.setApplicantOrTransParty("Transacting");
				mapDto.setPartyTypeTrns(regForm.getPartyTypeTrns());
				regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
				mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
				mapDto.setUserID(regForm.getHidnUserId());
				/*
				 * if(regForm.getOwnershipShareTrns().equalsIgnoreCase(""))
				 * mapDto.setOwnershipShareTrns("-"); else
				 * mapDto.setOwnershipShareTrns
				 * (regForm.getOwnershipShareTrns());
				 */
				if (regForm.getRelationWithOwnerTrns() != null && (regForm.getRelationWithOwnerTrns().equalsIgnoreCase("") || regForm.getRelationWithOwnerTrns().equalsIgnoreCase("null"))) {
					mapDto.setRelationWithOwnerTrns("-");
				} else {
					mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwnerTrns());
				}
				if (regForm.getShareOfPropTrns().equalsIgnoreCase("") || regForm.getShareOfPropTrns().equalsIgnoreCase("null")) {
					mapDto.setShareOfPropTrns("-");
					mapDto.setHdnDeclareShareCheck("N");
				} else {
					mapDto.setShareOfPropTrns(regForm.getShareOfPropTrns());
					mapDto.setHdnDeclareShareCheck("Y");
				}
				// following code for share of property validation.
				// int appRoleId=regForm.getApplicantRoleId2();
				// if(regForm.getDeedID()==RegInitConstant.DEED_CONVEYANCE_P){
				// if(appRoleId==RegInitConstant.ROLE_SELLER_SELF ||
				// appRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
				String applicantRoleId1 = commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
				// following if condition for disabling applicant role once
				// total sum of share is 100%.
				if (regForm.getTotalShareOfProp() == 100) {
					regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId1));
				}
				/*
				 * }else
				 * if(Integer.parseInt(regForm.getPartyTypeTrns())==regForm
				 * .getApplicantRoleId2()){ String
				 * applicantRoleId=commonBo.getApplicantRoleId
				 * (regForm.getHidnRegTxnId());
				 * 
				 * 
				 * //following if condition for disabling applicant role once
				 * total sum of share is 100%.
				 * if(regForm.getTotalShareOfProp()==100){
				 * regForm.setApplicantRoleId
				 * (Integer.parseInt(applicantRoleId)); } }
				 */
				// }
				/*
				 * if(regForm.getDeedID()==RegInitConstant.DEED_GIFT){
				 * if(appRoleId==RegInitConstant.ROLE_SELLER_SELF ||
				 * appRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
				 * 
				 * StringapplicantRoleId=commonBo.getApplicantRoleId(regForm.
				 * getHidnRegTxnId());
				 * 
				 * 
				 * //following if condition for disabling applicant role once
				 * total sum of share is 100%.
				 * if(regForm.getTotalShareOfProp()==100){
				 * regForm.setApplicantRoleId
				 * (Integer.parseInt(applicantRoleId)); }
				 * 
				 * }else
				 * if(Integer.parseInt(regForm.getPartyTypeTrns())==regForm
				 * .getApplicantRoleId2()){ String
				 * applicantRoleId=commonBo.getApplicantRoleId
				 * (regForm.getHidnRegTxnId());
				 * 
				 * 
				 * //following if condition for disabling applicant role once
				 * total sum of share is 100%.
				 * if(regForm.getTotalShareOfProp()==100){
				 * regForm.setApplicantRoleId
				 * (Integer.parseInt(applicantRoleId)); } } }
				 */
				// end of code for share of property validation.
				// FOLLOWING CODE FOR SETTING PARTY TYPE FLAG
				// int
				// selectedRoleId=Integer.parseInt(regForm.getPartyTypeTrns());
				// int
				// applicantRoleId=Integer.parseInt(commonBo.getApplicantRoleId(regForm.getHidnRegTxnId()));
				/*
				 * if(regForm.getDeedID()==RegInitConstant.DEED_CONVEYANCE_P){
				 * //CONVEYANCE DEED
				 * if(applicantRoleId==RegInitConstant.ROLE_SELLER_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_SELLER_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE_BUYER ||
				 * applicantRoleId==RegInitConstant.ROLE_BUYER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_BUYER ||
				 * selectedRoleId==RegInitConstant.ROLE_BUYER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } }else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_POA_P){
				 * //GIFT/POA/LEASE DEEDS if(selectedRoleId==applicantRoleId ){
				 * mapDto.setPartyTypeFlag("C"); }
				 * if(selectedRoleId==RegInitConstant.ROLE_POA_ACCEPTER){
				 * regForm.setIsPoaAddedFlag(1); logger.debug("poa flag added");
				 * }
				 * 
				 * } else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_CERTIFICATE_SALE
				 * ){ //SALE DEED
				 * if(applicantRoleId==RegInitConstant.ROLE_OWNER_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE_BUYER ||
				 * applicantRoleId==RegInitConstant.ROLE_BUYER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_BUYER ||
				 * selectedRoleId==RegInitConstant.ROLE_BUYER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } } else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE){
				 * //EXCHANGE DEED
				 * if(applicantRoleId==RegInitConstant.ROLE1_OWNER_POA_HOLDER ||
				 * applicantRoleId==RegInitConstant.ROLE1_OWNER_SELF){
				 * if(selectedRoleId==RegInitConstant.ROLE1_OWNER_POA_HOLDER ||
				 * selectedRoleId==RegInitConstant.ROLE1_OWNER_SELF){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE2_OWNER_POA_HOLDER ||
				 * applicantRoleId==RegInitConstant.ROLE2_OWNER_SELF){
				 * if(selectedRoleId==RegInitConstant.ROLE2_OWNER_POA_HOLDER ||
				 * selectedRoleId==RegInitConstant.ROLE2_OWNER_SELF){
				 * mapDto.setPartyTypeFlag("C"); } } } else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_TRUST_PV){
				 * //TRUST PV DEED regForm.setIsFirstPartyAddedFlag(1);
				 * if(applicantRoleId==RegInitConstant.ROLE_TRUSTEE ||
				 * applicantRoleId==RegInitConstant.ROLE_TRUSTEE_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_TRUSTEE ||
				 * selectedRoleId==RegInitConstant.ROLE_TRUSTEE_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } }else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_PV){
				 * //SETTLEMENT DEED
				 * if(applicantRoleId==RegInitConstant.ROLE_SETTLER_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_SETTLER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_SETTLER_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_SETTLER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } }else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_PV ||
				 * regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_PV){
				 * //MORTGAGE DEED
				 * if(applicantRoleId==RegInitConstant.ROLE_MORTGAGER_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_MORTGAGER_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE_MORTGAGEE_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_MORTGAGEE_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } }else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_RELEASE_PV){
				 * //RELEASE DEED
				 * if(applicantRoleId==RegInitConstant.ROLE_RELEASER_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_RELEASER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_RELEASER_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_RELEASER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE_RELEASEE_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_RELEASEE_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_RELEASEE_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_RELEASEE_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } } else
				 * if(regForm.getDeedID(
				 * )==RegInitConstant.DEED_TRANSFER_LEASE_PV){ //TRANSFER OF
				 * LEASE DEED
				 * if(applicantRoleId==RegInitConstant.ROLE_TRANSFERER_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_TRANSFERER_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE_TRANSFEREE_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_TRANSFEREE_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } } else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_AWARD_PV){
				 * //AWARD DEED
				 * if(applicantRoleId==RegInitConstant.ROLE_AWARDER_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_AWARDER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_AWARDER_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_AWARDER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE_AWARDEE_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_AWARDEE_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_AWARDEE_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_AWARDEE_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } } else
				 * if(regForm.getDeedID(
				 * )==RegInitConstant.DEED_AGREEMENT_MEMO_PV){ //AGREEMENT OF
				 * MEMORANDUM DEED
				 * if(applicantRoleId==RegInitConstant.ROLE_OWNER_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } } else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
				 * //PARTITION DEED
				 * if(applicantRoleId==RegInitConstant.ROLE_OWNER_SELF ||
				 * applicantRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } } else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_GIFT){ //GIFT
				 * DEED if(applicantRoleId==RegInitConstant.ROLE_DONOR ||
				 * applicantRoleId==RegInitConstant.ROLE_DONOR_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_DONOR ||
				 * selectedRoleId==RegInitConstant.ROLE_DONOR_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE_DONEE ||
				 * applicantRoleId==RegInitConstant.ROLE_DONEE_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_DONEE ||
				 * selectedRoleId==RegInitConstant.ROLE_DONEE_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } } else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_LEASE_PV){
				 * //LEASE PV DEED
				 * if(applicantRoleId==RegInitConstant.ROLE_LESSER ||
				 * applicantRoleId==RegInitConstant.ROLE_LESSER_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_LESSER ||
				 * selectedRoleId==RegInitConstant.ROLE_LESSER_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE_LESSEE ||
				 * applicantRoleId==RegInitConstant.ROLE_LESSEE_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_LESSEE ||
				 * selectedRoleId==RegInitConstant.ROLE_LESSEE_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } } }
				 * 
				 * else
				 * if(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_PV){
				 * //DISSOLUTION OF PARTNERSHIP DEED
				 * if(applicantRoleId==RegInitConstant.ROLE_TRANS_PARTY_SELF ||
				 * applicantRoleId
				 * ==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER){
				 * if(selectedRoleId==RegInitConstant.ROLE_TRANS_PARTY_SELF ||
				 * selectedRoleId==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER){
				 * mapDto.setPartyTypeFlag("C"); } }else
				 * if(applicantRoleId==RegInitConstant.ROLE_RETIRING_PARTY_SELF
				 * ||
				 * applicantRoleId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER
				 * ){
				 * if(selectedRoleId==RegInitConstant.ROLE_RETIRING_PARTY_SELF
				 * ||
				 * selectedRoleId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER
				 * ){ mapDto.setPartyTypeFlag("C"); } } }
				 */
				mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyTypeTrns(), languageLocale, regForm.getDeedID(), regForm.getInstID()));
				if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					// mapDto.setGovtOfcCheck(regForm.getGovtOfcCheck());
					if (regForm.getNameOfOfficialTrns() != null && !("").equalsIgnoreCase(regForm.getNameOfOfficialTrns())) {
						mapDto.setNameOfOfficial(regForm.getNameOfOfficialTrns());
					} else {
						mapDto.setNameOfOfficial("-");
					}
					if (regForm.getGovernmentOfficeNameTrans() != null && !regForm.getGovernmentOfficeNameTrans().isEmpty()) {
						mapDto.setGovtOffcNameTrns(regForm.getGovernmentOfficeNameTrans());
						// regForm.setGovernmentOfficeNameTrans("");
					}
					mapDto.setDesgOfOfficial(regForm.getDesgOfOfficialTrns());
					mapDto.setAddressOfOfficial(regForm.getAddressOfOfficialTrns());
					mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));
					mapDto.setOgrNameTrns("-");
					// mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim());
					// mapDto.setIndAuthPersn(mapDto.getDesgOfOfficial());
					mapDto.setIndividualOrOrganization(RegInitConstant.GOVT_OFFCL_ID);
					// changes for minor - Rahul
					if (mapDto.getIndividualOrOrganization() == RegInitConstant.GOVT_OFFCL_ID) {
						mapDto.setIndAuthPersn(mapDto.getDesgOfOfficial());
						regForm.setAbc("2");
					}
					if (regForm.getAddressGovtOffclOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressGovtOffclOutMpTrns())) {
						mapDto.setAddressGovtOffclOutMpTrns(regForm.getAddressGovtOffclOutMpTrns());
					} else {
						mapDto.setAddressGovtOffclOutMpTrns("-");
					}
					if (regForm.getGovernmentOfficeNameTrans() != null && !("").equalsIgnoreCase(regForm.getGovernmentOfficeNameTrans())) {
						String offcName = commonBd.getGovtOffcName(regForm.getGovernmentOfficeNameTrans(), languageLocale);
						mapDto.setGovtOfficeName(offcName);
						regForm.setGovernmentOfficeNameTrans("");
					} else {
						mapDto.setGovtOfficeName("-");
						regForm.setGovernmentOfficeNameTrans("");
					}
				} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
					// organization
					mapDto.setOgrNameTrns(regForm.getOgrNameTrns());
					mapDto.setAuthPerNameTrns(regForm.getAuthPerNameTrns());
					// mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
					mapDto.setOrgaddressTrns(regForm.getOrgaddressTrns());
					mapDto.setSelectedCountry(regForm.getCountryTrns());
					mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
					mapDto.setSelectedState(regForm.getStatenameTrns());
					mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
					mapDto.setSelectedDistrict(regForm.getDistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrictTrns(), languageLocale));
					if (regForm.getMobnoTrns().equalsIgnoreCase("") || regForm.getMobnoTrns().equalsIgnoreCase("null"))
						mapDto.setMobnoTrns("-");
					else
						mapDto.setMobnoTrns(regForm.getMobnoTrns());
					if (regForm.getPhnoTrns().equalsIgnoreCase("") || regForm.getPhnoTrns().equalsIgnoreCase("null"))
						mapDto.setPhnoTrns("-");
					else
						mapDto.setPhnoTrns(regForm.getPhnoTrns());
					mapDto.setConsiAmountTrns(regForm.getConsiAmountTrns());
					mapDto.setMarketValueTrns(regForm.getMarketValueTrns());
					mapDto.setFnameTrns("");
					mapDto.setMnameTrns("");
					mapDto.setLnameTrns("");
					// mapDto.setGendarTrns("-");
					// mapDto.setSelectedGender("");
					mapDto.setAgeTrns("");
					// mapDto.setFatherNameTrns("-");
					mapDto.setMotherNameTrns("");
					mapDto.setSpouseNameTrns("");
					mapDto.setNationalityTrns("");
					mapDto.setPostalCodeTrns("");
					mapDto.setEmailIDTrns("");
					mapDto.setSelectedPhotoId("");
					mapDto.setSelectedPhotoIdName("");
					mapDto.setIdnoTrns("-");
					// mapDto.setIndReligionTrns("");
					mapDto.setIndCasteTrns("");
					mapDto.setIndDisabilityTrns("");
					mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
					// modified by rahul
					if (mapDto.getIndividualOrOrganization() == RegInitConstant.ORGANISATION_ID) {
						if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							// if(mapDto.getPoaHolderFlag()==RegInitConstant.POA_HOLDER_FLAG){
							// to fetch hindi english both name
							mapDto.setRoleNameForPOA(commonBo.getRoleNameForPOA(regForm.getPartyTypeTrns().trim()));
							String RoleName = mapDto.getRoleNameForPOA();
							System.out.println("Role Name " + RoleName);
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							String PoaName = null;
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " ??????????????????(??????????????????????????????  ?????????????????????????????????) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " ?????????????????? (????????????????????????????????? ??????)";
								}
							}
							System.out.println("POA NAMe is " + PoaName);
							System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
							String OwnerDetail = null;
							// mapDto.setOwnerPartyDesc(commonBo.getOwnerPartyDesc(regForm.getHidnRegTxnId(),languageLocale));
							// OwnerDetail = mapDto.getOwnerPartyDesc();
							// System.out.println("Owner result from DB is "+OwnerDetail);
							// String parts[] = OwnerDetail.split(",");
							// String Owner = parts[0];
							// String PoADesc =parts[1];
							// String OwnerRolePoA=parts[0]+PoaName+parts[1];
							int size = mapDto.getTrnsOwnerList().size();
							int least = 0;
							// RegCommonDTO party1 = new RegCommonDTO();
							Iterator iterator = mapDto.getTrnsOwnerList().keySet().iterator();
							while (iterator.hasNext()) {
								int temp = Integer.parseInt((String) iterator.next());
								if (least == 0) {
									least = temp;
								}
								if (temp < least) {
									least = temp;
								}
								// party1 =
								// (RegCommonDTO)mapDto.getTrnsOwnerList().get(least+"");
							}
							RegCommonDTO party1 = (RegCommonDTO) mapDto.getTrnsOwnerList().get(least + "");
							String firstOwnerName = party1.getOwnerNameTrns();
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								if (size > 1) {
									PoaName = " and others " + "(" + PoaName + ") ";
								}
							} else {
								if (size > 1) {
									PoaName = " ????????? ??????????????? ?????????   " + "(" + PoaName + ") ";
								}
							}
							// System.out.println("owner Role to display "+
							// OwnerRolePoA);
							String OwnerRolePoA = firstOwnerName + PoaName + mapDto.getAuthPerNameTrns();
							mapDto.setIndAuthPersn(OwnerRolePoA);
							regForm.setAbc("2");
						} else {
							mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
							regForm.setAbc("2");
						}
					}
					mapDto.setAuthPerGendarTrns(regForm.getAuthPerGendarTrns());
					if (regForm.getAuthPerGendarTrns().equalsIgnoreCase("m")) {
						// mapDto.setSelectedGender("Male");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
						} else {
							mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
						}
					} else if (regForm.getAuthPerGendarTrns().equalsIgnoreCase("f")) {
						// mapDto.setSelectedGender("Female");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
						} else {
							mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
						}
					} else {
						// mapDto.setSelectedGender("other");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setSelectedGender(RegInitConstant.other_gender_ENGLISH);
						} else {
							mapDto.setSelectedGender(RegInitConstant.other_gender_hindi);
						}
					}
					mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherNameTrns());
					mapDto.setRelationshipTrns(regForm.getAuthPerRelationshipTrns());
					mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationshipTrns()), languageLocale));
					mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddressTrns().trim());
					mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountryTrns());
					mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountryTrns(), languageLocale));
					mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatenameTrns());
					mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatenameTrns(), languageLocale));
					mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrictTrns());
					mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrictTrns().trim(), languageLocale));
					if (regForm.getAddressOrgOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOrgOutMpTrns())) {
						mapDto.setAddressOrgOutMpTrns(regForm.getAddressOrgOutMpTrns());
					} else {
						mapDto.setAddressOrgOutMpTrns("-");
					}
					if (regForm.getAddressAuthPerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressAuthPerOutMpTrns())) {
						mapDto.setAddressAuthPerOutMpTrns(regForm.getAddressAuthPerOutMpTrns());
					} else {
						mapDto.setAddressAuthPerOutMpTrns("-");
					}
				} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
					// individual
					mapDto.setFnameTrns(regForm.getFnameTrns());
					if (regForm.getMnameTrns().equalsIgnoreCase("") || regForm.getMnameTrns().equalsIgnoreCase("null"))
						mapDto.setMnameTrns("-");
					else
						mapDto.setMnameTrns(regForm.getMnameTrns());
					// Added for aadhar integration - start
					if (regForm.getAadharNameTrns() != null) {
						mapDto.setAadharNameTrns(regForm.getAadharNameTrns());
						mapDto.setAadharName(regForm.getAadharNameTrns()); // to
						// display
						// aadhar
						// name
						// on
						// DisplayRegDetails.jsp
					}
					if (regForm.getListIDTrns().equalsIgnoreCase("7")) {
						mapDto.setPartyDisplayAadhar("1");
					} else {
						mapDto.setPartyDisplayAadhar("0");
					}
					// added for aadhar integration by ankit
					if (regForm.getTransactionId() != null && regForm.getAcknowledgementId() != null) {
						mapDto.setAcknowledgementId(regForm.getAcknowledgementId());
						mapDto.setTransactionId(regForm.getTransactionId());
					}
					// Added for aadhar integration - end
					mapDto.setLnameTrns(regForm.getLnameTrns());
					mapDto.setGendarTrns(regForm.getGendarTrns());
					if (regForm.getGendarTrns().equalsIgnoreCase("m")) {
						// mapDto.setSelectedGender("Male");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
						} else {
							mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
						}
					} else if (regForm.getGendarTrns().equalsIgnoreCase("f")) {
						// mapDto.setSelectedGender("Female");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
						} else {
							mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
						}
					} else {
						// mapDto.setSelectedGender("other");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setSelectedGender(RegInitConstant.other_gender_ENGLISH);
						} else {
							mapDto.setSelectedGender(RegInitConstant.other_gender_hindi);
						}
					}
					if (regForm.getAgeTrns().equalsIgnoreCase(""))
						mapDto.setAgeTrns("-");
					else
						mapDto.setAgeTrns(regForm.getAgeTrns());
					mapDto.setFatherNameTrns(regForm.getFatherNameTrns());
					if (regForm.getMotherNameTrns().equalsIgnoreCase(""))
						mapDto.setMotherNameTrns("-");
					else
						mapDto.setMotherNameTrns(regForm.getMotherNameTrns());
					if (regForm.getSpouseNameTrns().equalsIgnoreCase(""))
						mapDto.setSpouseNameTrns("-");
					else
						mapDto.setSpouseNameTrns(regForm.getSpouseNameTrns());
					// For Guardian - Rahul
					if (regForm.getGuardianTrans().equalsIgnoreCase("") || regForm.getGuardianTrans().equalsIgnoreCase("null"))
						mapDto.setGuardianTrans("-");
					else {
						mapDto.setGuardianTrans(regForm.getGuardianTrans());
					}
					mapDto.setNationalityTrns(regForm.getNationalityTrns());
					if (regForm.getPostalCodeTrns().equalsIgnoreCase(""))
						mapDto.setPostalCodeTrns("-");
					else
						mapDto.setPostalCodeTrns(regForm.getPostalCodeTrns());
					if (regForm.getIndphnoTrns().equalsIgnoreCase(""))
						mapDto.setPhnoTrns("-");
					else
						mapDto.setPhnoTrns(regForm.getIndphnoTrns());
					if (regForm.getIndmobnoTrns().equalsIgnoreCase(""))
						mapDto.setMobnoTrns("-");
					else
						mapDto.setMobnoTrns(regForm.getIndmobnoTrns());
					if (regForm.getEmailIDTrns().equalsIgnoreCase(""))
						mapDto.setEmailIDTrns("-");
					else
						mapDto.setEmailIDTrns(regForm.getEmailIDTrns());
					mapDto.setSelectedPhotoId(regForm.getListIDTrns());
					mapDto.setSelectedPhotoIdName(regForm.getListIDNameTrns());
					mapDto.setIdnoTrns(regForm.getIdnoTrns());
					mapDto.setOgrNameTrns("-");
					mapDto.setAuthPerNameTrns("-");
					// mapDto.setIndAuthPersn(regForm.getFnameTrns()+" "+regForm.getLnameTrns());
					// changed for minor , POA // for adding more than 1 party -
					// Rahul - to display on initation
					if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						// if(mapDto.getPoaHolderFlag()==RegInitConstant.POA_HOLDER_FLAG){
						// to fetch hindi english both name
						mapDto.setRoleNameForPOA(commonBo.getRoleNameForPOA(regForm.getPartyTypeTrns().trim()));
						String RoleName = mapDto.getRoleNameForPOA();
						System.out.println("Role Name " + RoleName);
						String HindiEnglishRole[] = RoleName.split(",");
						String hindesc = HindiEnglishRole[0];
						String EngDesc = HindiEnglishRole[1];
						String PoaName = null;
						if (EngDesc.contains("Authenticated")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(Authenticated PoA Holder) ";
							} else {
								PoaName = " ??????????????????(??????????????????????????????  ?????????????????????????????????) ";
							}
						} else {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								PoaName = " By(PoA Holder) ";
							} else {
								PoaName = " ?????????????????? (????????????????????????????????? ??????)";
							}
						}
						System.out.println("POA NAMe is " + PoaName);
						System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
						String OwnerDetail = null;
						/*
						 * mapDto.setOwnerPartyDesc(commonBo.getOwnerPartyDesc(regForm
						 * .getHidnRegTxnId(),languageLocale)); OwnerDetail =
						 * mapDto.getOwnerPartyDesc();
						 * System.out.println("Owner result from DB is "
						 * +OwnerDetail); String parts[] =
						 * OwnerDetail.split(","); String Owner = parts[0];
						 * String PoADesc =parts[1];
						 */
						// String OwnerRolePoA=parts[0]+PoaName+parts[1];
						int size = mapDto.getTrnsOwnerList().size();
						int least = 0;
						/*
						 * Iterator iterator =
						 * mapDto.getTrnsOwnerList().keySet().iterator(); while
						 * (iterator.hasNext()) { int temp =
						 * Integer.parseInt((String)iterator.next()); if(least
						 * == 0){ least = temp; } if(temp < least){ least =
						 * temp; } }
						 * 
						 * RegCommonDTO party1 =
						 * (RegCommonDTO)mapDto.getTrnsOwnerList
						 * ().get(least+"");
						 * 
						 * String firstOwnerName = party1.getOwnerNameTrns(); if
						 * (size>1) { PoaName =
						 * " and others by"+"("+PoaName+") "; } else{ PoaName =
						 * " by"+"("+PoaName+") "; }
						 */
						// RegCommonDTO party1 = new RegCommonDTO();
						Iterator iterator = mapDto.getTrnsOwnerList().keySet().iterator();
						while (iterator.hasNext()) {
							int temp = Integer.parseInt((String) iterator.next());
							if (least == 0) {
								least = temp;
							}
							if (temp < least) {
								least = temp;
							}
							// party1 =
							// (RegCommonDTO)mapDto.getTrnsOwnerList().get(temp+"");
						}
						RegCommonDTO party1 = (RegCommonDTO) mapDto.getTrnsOwnerList().get(least + "");
						String firstOwnerName = party1.getOwnerNameTrns();
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							if (size > 1) {
								PoaName = " and others " + "(" + PoaName + ") ";
							}
						} else {
							if (size > 1) {
								PoaName = " ????????? ??????????????? ?????????   " + "(" + PoaName + ") ";
							}
						}
						// System.out.println("owner Role to display "+
						// OwnerRolePoA);
						// String
						// OwnerRolePoA=firstOwnerName+PoaName+mapDto.getAuthPerNameTrns();
						// System.out.println("owner Role to display "+
						// OwnerRolePoA);
						String OwnerRolePoA = firstOwnerName + PoaName + mapDto.getFnameTrns() + " " + mapDto.getMnameTrns() + " " + mapDto.getLnameTrns();
						mapDto.setIndAuthPersn(OwnerRolePoA);
						regForm.setAbc("2");
					} else {
						// added by rahul for minor changes -
						int partyAge = 0;
						if (regForm.getAgeTrns() != null && !"".equalsIgnoreCase(regForm.getAgeTrns())) {
							partyAge = Integer.parseInt(regForm.getAgeTrns());
						}
						if (regForm.getMnameTrns().equalsIgnoreCase("") || regForm.getMnameTrns().equalsIgnoreCase("null")) {
							mapDto.setIndAuthPersn(regForm.getFnameTrns() + " " + regForm.getLnameTrns());
						} else {
							mapDto.setIndAuthPersn(regForm.getFnameTrns() + " " + regForm.getMnameTrns() + " " + regForm.getLnameTrns());
						}
						String name = mapDto.getIndAuthPersn();
						if (partyAge < 18) {
							RegCommonBO bo = new RegCommonBO();
							String relations = bo.getRelationshipName(String.valueOf(regForm.getRelationshipTrns()), languageLocale);
							System.out.println("Relationship ---------" + String.valueOf(regForm.getRelationshipTrns()));
							// String name = mapDto.getIndAuthPersn();
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								name = "(Minor) " + name + " " + relations + " " + regForm.getFatherNameTrns() + " by " + regForm.getGuardianTrans();
							} else {
								name = "(?????????????????????) " + name + " " + relations + " " + regForm.getFatherNameTrns() + " ?????????????????????  " + regForm.getGuardianTrans();
							}
							/*
							 * mapDto.setAgeAboveName(name);
							 * regForm.setAbc("1");
							 */
						}
						mapDto.setIndAuthPersn(name);
						regForm.setAbc("2");
						/*
						 * else { regForm.setAbc("2"); }
						 */
					}
					System.out.println("indAutyhpesrson value" + regForm.getAbc());
					mapDto.setOrgaddressTrns(regForm.getIndaddressTrns());
					mapDto.setSelectedCountry(regForm.getIndcountryTrns());
					mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
					mapDto.setSelectedState(regForm.getIndstatenameTrns());
					mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
					mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
					mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));
					mapDto.setSelectedCategoryName(regForm.getSelectedCategoryNameTrns());
					mapDto.setIndCategoryTrns(regForm.getIndCategoryTrns());
					mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationIdTrns(), languageLocale));
					mapDto.setOccupationIdTrns(regForm.getOccupationIdTrns());
					mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleAreaTrns());
					if (regForm.getIndCategoryTrns().equalsIgnoreCase(RegInitConstant.ST_CONSTANT)) {
						if (regForm.getIndScheduleAreaTrns() != null) {
							if (regForm.getIndScheduleAreaTrns().equalsIgnoreCase("Y")) {
								// mapDto.setIndScheduleAreaTrnsDisplay("Yes");
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
								} else {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
								}
							} else {
								// mapDto.setIndScheduleAreaTrnsDisplay("No");
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
								} else {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
								}
								mapDto.setPermissionNoTrns(regForm.getPermissionNoTrns());
								mapDto.setDocumentNameTrns(regForm.getDocumentNameTrns());
								mapDto.setDocumentSizeTrns(regForm.getDocumentSizeTrns());
								mapDto.setFilePathTrns(regForm.getFilePathTrns());
								mapDto.setDocContentsTrns(regForm.getDocContentsTrns());
								mapDto.setPartyOrPropTrns(regForm.getPartyOrPropTrns());
								mapDto.setUploadTypeTrns(regForm.getUploadTypeTrns());
							}
						}
					}
					if (regForm.getIndDisabilityTrns().equalsIgnoreCase("")) {
						mapDto.setIndDisabilityTrns("-");
					} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("Y")) {
						mapDto.setIndDisabilityIdTrns(regForm.getIndDisabilityTrns());
						// mapDto.setIndDisabilityTrns("Yes");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
						} else {
							mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
						}
						if (regForm.getIndDisabilityDescTrns().equalsIgnoreCase("")) {
							mapDto.setIndDisabilityDescTrns("-");
						} else {
							mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDescTrns());
						}
					} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("N")) {
						mapDto.setIndDisabilityIdTrns(regForm.getIndDisabilityTrns());
						// mapDto.setIndDisabilityTrns("No");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
						} else {
							mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
						}
					}
					mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
					// mapDto.setUserDOBTrns(UserRegistrationBD.getOracleDate(regForm.getUserDayTrns(),
					// regForm.getUserMonthTrns(), regForm.getUserYearTrns()));
					if (regForm.getIndMinorityTrns().equalsIgnoreCase("")) {
						mapDto.setIndMinorityTrns("-");
					} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("Y")) {
						// mapDto.setIndMinorityTrns("Yes");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
						} else {
							mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
						}
					} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("N")) {
						// mapDto.setIndMinorityTrns("No");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
						} else {
							mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
						}
					}
					// mapDto.setIndMinorityTrns("");
					if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("")) {
						mapDto.setIndPovertyLineTrns("-");
					} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("Y")) {
						// mapDto.setIndPovertyLineTrns("Yes");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
						} else {
							mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
						}
					} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("N")) {
						// mapDto.setIndPovertyLineTrns("No");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
						} else {
							mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
						}
					}
					// mapDto.setIndPovertyLineTrns("");
					mapDto.setRelationshipTrns(regForm.getRelationshipTrns());
					mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationshipTrns()), languageLocale));
					if (regForm.getAddressIndOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressIndOutMpTrns())) {
						mapDto.setAddressIndOutMpTrns(regForm.getAddressIndOutMpTrns());
					} else {
						mapDto.setAddressIndOutMpTrns("-");
					}
				}
				// set in mapdto from form- anuj
				// mapDto.setGovtOfcCheck(regForm.getGovtOfcCheckTrns());
				// mapDto.setNameOfOfficial(regForm.getNameOfOfficialTrns());
				// mapDto.setDesgOfOfficial(regForm.getDesgOfOfficialTrns());
				// mapDto.setAddressOfOfficial(regForm.getAddressOfOfficialTrns());
				// below code for uploads other than collector's permission no.
				if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					mapDto.setU2DocumentNameTrns(regForm.getU2DocumentNameTrns());
					mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSizeTrns());
					mapDto.setU2FilePathTrns(regForm.getU2FilePathTrns());
					mapDto.setU2DocContentsTrns(regForm.getU2DocContentsTrns());
					mapDto.setU2PartyOrPropTrns(regForm.getU2PartyOrPropTrns());
					mapDto.setU2UploadTypeTrns(regForm.getU2UploadTypeTrns());
					mapDto.setClaimantFlag(claimantFlag);
					// BELOW CODE FOR EXECUTANT
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
						mapDto.setU3DocumentNameTrns(regForm.getU3DocumentNameTrns());
						mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSizeTrns());
						mapDto.setU3FilePathTrns(regForm.getU3FilePathTrns());
						mapDto.setU3DocContentsTrns(regForm.getU3DocContentsTrns());
						mapDto.setU3PartyOrPropTrns(regForm.getU3PartyOrPropTrns());
						mapDto.setU3UploadTypeTrns(regForm.getU3UploadTypeTrns());
						/*
						 * mapDto.setU4DocumentNameTrns(regForm.getU4DocumentNameTrns
						 * ());mapDto.setU4DocumentSizeTrns(regForm.
						 * getU4DocumentSizeTrns());
						 * mapDto.setU4FilePathTrns(regForm
						 * .getU4FilePathTrns());
						 * mapDto.setU4DocContentsTrns(regForm
						 * .getU4DocContentsTrns());
						 * mapDto.setU4PartyOrPropTrns(
						 * regForm.getU4PartyOrPropTrns());
						 * mapDto.setU4UploadTypeTrns
						 * (regForm.getU4UploadTypeTrns());
						 */
						mapDto.setU10DocumentNameTrns(regForm.getU10DocumentNameTrns());
						mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSizeTrns());
						mapDto.setU10FilePathTrns(regForm.getU10FilePathTrns());
						mapDto.setU10DocContentsTrns(regForm.getU10DocContentsTrns());
						mapDto.setU10PartyOrPropTrns(regForm.getU10PartyOrPropTrns());
						mapDto.setU10UploadTypeTrns(regForm.getU10UploadTypeTrns());
					}
					// BELOW CODE FOR EXECUTANT POA HOLDER
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_5) {
						/*
						 * mapDto.setU5DocumentNameTrns(regForm.getU5DocumentNameTrns
						 * ());mapDto.setU5DocumentSizeTrns(regForm.
						 * getU5DocumentSizeTrns());
						 * mapDto.setU5FilePathTrns(regForm
						 * .getU5FilePathTrns());
						 * mapDto.setU5DocContentsTrns(regForm
						 * .getU5DocContentsTrns());
						 * mapDto.setU5PartyOrPropTrns(
						 * regForm.getU5PartyOrPropTrns());
						 * mapDto.setU5UploadTypeTrns
						 * (regForm.getU5UploadTypeTrns());
						 */
						mapDto.setU6DocumentNameTrns(regForm.getU6DocumentNameTrns());
						mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSizeTrns());
						mapDto.setU6FilePathTrns(regForm.getU6FilePathTrns());
						mapDto.setU6DocContentsTrns(regForm.getU6DocContentsTrns());
						mapDto.setU6PartyOrPropTrns(regForm.getU6PartyOrPropTrns());
						mapDto.setU6UploadTypeTrns(regForm.getU6UploadTypeTrns());
						/*
						 * mapDto.setU7DocumentNameTrns(regForm.getU7DocumentNameTrns
						 * ());mapDto.setU7DocumentSizeTrns(regForm.
						 * getU7DocumentSizeTrns());
						 * mapDto.setU7FilePathTrns(regForm
						 * .getU7FilePathTrns());
						 * mapDto.setU7DocContentsTrns(regForm
						 * .getU7DocContentsTrns());
						 * mapDto.setU7PartyOrPropTrns(
						 * regForm.getU7PartyOrPropTrns());
						 * mapDto.setU7UploadTypeTrns
						 * (regForm.getU7UploadTypeTrns());
						 */
						mapDto.setSrOfficeNameTrns(regForm.getSrOfficeNameTrns());
						if (regForm.getPoaRegNoTrns() != null && !regForm.getPoaRegNoTrns().equalsIgnoreCase("")) {
							mapDto.setPoaRegNoTrns(regForm.getPoaRegNoTrns());
						} else {
							mapDto.setPoaRegNoTrns("-");
						}
						if (regForm.getDatePoaRegTrns() != null && !regForm.getDatePoaRegTrns().equalsIgnoreCase("")) {
							mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaRegTrns()));
						} else {
							mapDto.setDatePoaRegTrns("-");
						}
					}
					// BELOW CODE FOR CLAIMANT
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
						/*
						 * mapDto.setU8DocumentNameTrns(regForm.getU8DocumentNameTrns
						 * ());mapDto.setU8DocumentSizeTrns(regForm.
						 * getU8DocumentSizeTrns());
						 * mapDto.setU8FilePathTrns(regForm
						 * .getU8FilePathTrns());
						 * mapDto.setU8DocContentsTrns(regForm
						 * .getU8DocContentsTrns());
						 * mapDto.setU8PartyOrPropTrns(
						 * regForm.getU8PartyOrPropTrns());
						 * mapDto.setU8UploadTypeTrns
						 * (regForm.getU8UploadTypeTrns());
						 */
						mapDto.setU11DocumentNameTrns(regForm.getU11DocumentNameTrns());
						mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSizeTrns());
						mapDto.setU11FilePathTrns(regForm.getU11FilePathTrns());
						mapDto.setU11DocContentsTrns(regForm.getU11DocContentsTrns());
						mapDto.setU11PartyOrPropTrns(regForm.getU11PartyOrPropTrns());
						mapDto.setU11UploadTypeTrns(regForm.getU11UploadTypeTrns());
					}
					// BELOW CODE FOR CLAIMANT POA HOLDER
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_6) {
						mapDto.setU9DocumentNameTrns(regForm.getU9DocumentNameTrns());
						mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSizeTrns());
						mapDto.setU9FilePathTrns(regForm.getU9FilePathTrns());
						mapDto.setU9DocContentsTrns(regForm.getU9DocContentsTrns());
						mapDto.setU9PartyOrPropTrns(regForm.getU9PartyOrPropTrns());
						mapDto.setU9UploadTypeTrns(regForm.getU9UploadTypeTrns());
					}
				}
				mapDto.setListDto(regForm.getListDto());
				regForm.setListDto(new ArrayList<CommonDTO>());
				map = regForm.getMapTransactingParties();
				int count = regForm.getMapKeyCount();
				if (count == 0)
					count = 1;
				if (map.containsKey(Integer.toString(count))) {
					count++;
					map.put(Integer.toString(count), mapDto);
				} else
					map.put(Integer.toString(count), mapDto);
				regForm.getMapTransPartyDbInsertion().put(Integer.toString(count), mapDto);
				regForm.setMapKeyCount(count);
				regForm.setAddMoreCounter(count);
				// key="key";
				regForm.setMapTransactingParties(map);
				if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					regForm.setGovtOfcCheckTrns("");
					regForm.setNameOfOfficialTrns("");
					regForm.setDesgOfOfficialTrns("");
					regForm.setAddressOfOfficialTrns("");
					regForm.setAddressGovtOffclOutMpTrns("");
					regForm.setInddistrictTrns("");
				}
				if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
					regForm.setPartyTypeTrns(null);
					regForm.setOgrNameTrns("");
					regForm.setAuthPerNameTrns("");
					regForm.setOrgaddressTrns("");
					regForm.setPhnoTrns("");
					regForm.setMobnoTrns("");
					regForm.setConsiAmountTrns("");
					regForm.setMarketValueTrns("");
					regForm.setCountryTrns("");
					regForm.setDistrictTrns("");
					regForm.setStatenameTrns("");
					// commonDto.setCountryTrns(commonBo.getCountry());
					regForm.setActionName("");
					regForm.setOwnershipShareTrns("");
					regForm.setRelationWithOwnerTrns("");
					regForm.setShareOfPropTrns("");
					regForm.setCountryTrns("1");
					regForm.setCountryNameTrns(commonBo.getCountryName("1", languageLocale));
					regForm.setStatenameTrns("1");
					regForm.setStatenameNameTrns(commonBo.getStateName("1", languageLocale));
					/*
					 * regForm.setIndcountryTrns("INDIA");
					 * regForm.setIndcountryNameTrns("INDIA");
					 * regForm.setIndstatenameTrns("MP");
					 * regForm.setIndstatenameNameTrns("MADHYA PRADESH");
					 */
					regForm.setAuthPerGendarTrns("M");
					regForm.setAuthPerFatherNameTrns("");
					regForm.setAuthPerRelationshipTrns(0);
					regForm.setAuthPerAddressTrns("");
					regForm.setAuthPerDistrictTrns("");
					regForm.setAddressOrgOutMpTrns("");
					regForm.setAddressAuthPerOutMpTrns("");
					regForm.setOwnerIndcountryTrns("1");
					regForm.setOwnerIndstatenameTrns("1");
					commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				}
				if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
					regForm.setOgrNameTrns("");
					mapDto.setOgrNameTrns("-");
					regForm.setPartyTypeTrns(null);
					regForm.setFnameTrns("");
					regForm.setMnameTrns("");
					regForm.setLnameTrns("");
					regForm.setGendarTrns("M");
					regForm.setAgeTrns("");
					regForm.setFatherNameTrns("");
					// Added Guardian tran blank - rahul
					regForm.setGuardianTrans("");
					regForm.setMotherNameTrns("");
					regForm.setSpouseNameTrns("");
					regForm.setNationalityTrns("");
					regForm.setPostalCodeTrns("");
					regForm.setIndphnoTrns("");
					regForm.setIndmobnoTrns("");
					regForm.setEmailIDTrns("");
					regForm.setListIDTrns("");
					regForm.setIdnoTrns("");
					regForm.setIndaddressTrns("");
					regForm.setIndcountryTrns("");
					regForm.setInddistrictTrns("");
					regForm.setIndstatenameTrns("");
					// commonDto.setIndcountryTrns(commonBo.getCountry());
					// commonDto.setIdProfTrns(commonBo.getIdProf());
					regForm.setActionName("");
					regForm.setIndReligionTrns("");
					regForm.setIndCasteTrns("");
					regForm.setIndDisabilityTrns("N");
					regForm.setIndDisabilityDescTrns("");
					regForm.setIndMinorityTrns("N");
					regForm.setOwnershipShareTrns("");
					regForm.setRelationWithOwnerTrns("");
					regForm.setShareOfPropTrns("");
					regForm.setIndPovertyLineTrns("N");
					// regForm.setIndMinorityTrns("N");
					regForm.setIndCategoryTrns("");
					regForm.setOccupationIdTrns("");
					/*
					 * regForm.setCountryTrns("INDIA");
					 * regForm.setCountryNameTrns("INDIA");
					 */
					regForm.setIndstatenameTrns("1");
					regForm.setIndstatenameNameTrns(commonBo.getStateName("1", languageLocale));
					regForm.setIndcountryTrns("1");
					regForm.setIndcountryNameTrns(commonBo.getCountryName("1", languageLocale));
					regForm.setUserDayTrns("");
					regForm.setUserMonthTrns("");
					regForm.setUserYearTrns("");
					regForm.setUserDOBTrns("");
					regForm.setIndScheduleAreaTrns("");
					regForm.setPermissionNoTrns("");
					regForm.setCertificateTrns(null);
					regForm.setDocumentNameTrns("");
					regForm.setDocumentSizeTrns("");
					regForm.setFilePathTrns("");
					regForm.setDocContentsTrns(new byte[0]);
					regForm.setPartyOrPropTrns("");
					regForm.setUploadTypeTrns("");
					regForm.setAddressIndOutMpTrns("");
					// regForm.setDocContentsTrns(new byte());
					// regForm.setIndstatenameTrns("MP");
					// regForm.setIndstatenameNameTrns("MADHYA PRADESH");
					regForm.setAadharNameTrns(""); // added for aadhar
					// integration
					regForm.setAadharErrMsg(""); // added for aadhar integration
					regForm.setAadharRespCode(""); // added for aadhar
					// integration
					regForm.setAadharCheck("false");// added for aadhar
					// integration
					regForm.setAcknowledgementId("");// added for aadhar
					// integration
					regForm.setTransactionId("");// added for aadhar integration
					regForm.setRelationshipTrns(0);
					commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				}
				if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					regForm.setU2Trns(null);
					regForm.setU2DocumentNameTrns("");
					regForm.setU2DocumentSizeTrns("");
					regForm.setU2FilePathTrns("");
					regForm.setU2DocContentsTrns(new byte[0]);
					regForm.setU2PartyOrPropTrns("");
					regForm.setU2UploadTypeTrns("");
					regForm.setU3Trns(null);
					regForm.setU3DocumentNameTrns("");
					regForm.setU3DocumentSizeTrns("");
					regForm.setU3FilePathTrns("");
					regForm.setU3DocContentsTrns(new byte[0]);
					regForm.setU3PartyOrPropTrns("");
					regForm.setU3UploadTypeTrns("");
					regForm.setU4Trns(null);
					regForm.setU4DocumentNameTrns("");
					regForm.setU4DocumentSizeTrns("");
					regForm.setU4FilePathTrns("");
					regForm.setU4DocContentsTrns(new byte[0]);
					regForm.setU4PartyOrPropTrns("");
					regForm.setU4UploadTypeTrns("");
					regForm.setU5Trns(null);
					regForm.setU5DocumentNameTrns("");
					regForm.setU5DocumentSizeTrns("");
					regForm.setU5FilePathTrns("");
					regForm.setU5DocContentsTrns(new byte[0]);
					regForm.setU5PartyOrPropTrns("");
					regForm.setU5UploadTypeTrns("");
					regForm.setU6Trns(null);
					regForm.setU6DocumentNameTrns("");
					regForm.setU6DocumentSizeTrns("");
					regForm.setU6FilePathTrns("");
					regForm.setU6DocContentsTrns(new byte[0]);
					regForm.setU6PartyOrPropTrns("");
					regForm.setU6UploadTypeTrns("");
					regForm.setU7Trns(null);
					regForm.setU7DocumentNameTrns("");
					regForm.setU7DocumentSizeTrns("");
					regForm.setU7FilePathTrns("");
					regForm.setU7DocContentsTrns(new byte[0]);
					regForm.setU7PartyOrPropTrns("");
					regForm.setU7UploadTypeTrns("");
					regForm.setU8Trns(null);
					regForm.setU8DocumentNameTrns("");
					regForm.setU8DocumentSizeTrns("");
					regForm.setU8FilePathTrns("");
					regForm.setU8DocContentsTrns(new byte[0]);
					regForm.setU8PartyOrPropTrns("");
					regForm.setU8UploadTypeTrns("");
					regForm.setU9Trns(null);
					regForm.setU9DocumentNameTrns("");
					regForm.setU9DocumentSizeTrns("");
					regForm.setU9FilePathTrns("");
					regForm.setU9DocContentsTrns(new byte[0]);
					regForm.setU9PartyOrPropTrns("");
					regForm.setU9UploadTypeTrns("");
					regForm.setU10Trns(null);
					regForm.setU10DocumentNameTrns("");
					regForm.setU10DocumentSizeTrns("");
					regForm.setU10FilePathTrns("");
					regForm.setU10DocContentsTrns(new byte[0]);
					regForm.setU10PartyOrPropTrns("");
					regForm.setU10UploadTypeTrns("");
					regForm.setU11Trns(null);
					regForm.setU11DocumentNameTrns("");
					regForm.setU11DocumentSizeTrns("");
					regForm.setU11FilePathTrns("");
					regForm.setU11DocContentsTrns(new byte[0]);
					regForm.setU11PartyOrPropTrns("");
					regForm.setU11UploadTypeTrns("");
				}
				regForm.setSrOfficeNameTrns("");
				regForm.setPoaRegNoTrns("");
				regForm.setDatePoaRegTrns("");
				if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
					regForm.setTrnsOwnerList(new HashMap());
					regForm.setOwnerNameTrns("");
					regForm.setOwnerOgrNameTrns("");
					regForm.setOwnerGendarTrns("M");
					regForm.setOwnerNationalityTrns("");
					regForm.setOwnerAddressTrns("");
					regForm.setOwnerPhnoTrns("");
					regForm.setOwnerEmailIDTrns("");
					regForm.setOwnerIdnoTrns("");
					regForm.setOwnerAgeTrns("");
					regForm.setOwnerListIDTrns("");
					regForm.setOwnerProofNameTrns("");
					commonDto.setIdProf(commonBo.getIdProf(languageLocale));
					regForm.setOwnerDayTrns("");
					regForm.setOwnerMonthTrns("");
					regForm.setOwnerYearTrns("");
					regForm.setAddressOwnerOutMpTrns("");
					regForm.setAadharCheckOwner(""); // added by ankit for
					// Aadhar Integration
					regForm.setAadharNameTrnsOwner(""); // added by ankit for
					// Aadhar Integration
					regForm.setAadharRespCodeOwner(""); // added by ankit for
					// Aadhar Integration
					regForm.setAadharErrMsgOwner("");
					regForm.setOwnerIndphnoTrns("");
					regForm.setOwnerPostalCodeTrns("");
					regForm.setOwnerIndDisabilityTrns("N");
					regForm.setOwnerIndDisabilityDescTrns("");
					regForm.setOwnerIndMinorityTrns("N");
					regForm.setOwnerIndPovertyLineTrns("N");
					regForm.setOwnerOccupationIdTrns("");
					regForm.setOwnerFatherNameTrns("");
					regForm.setOwnerMotherNameTrns("");
					regForm.setOwnerSpouseNameTrns("");
					regForm.setOwnerRelationshipTrns("");
					regForm.setOwnerIndScheduleAreaTrns("N");
					regForm.setOwnerPermissionNoTrns("");
					regForm.setOwnerCertificateTrns("");
					regForm.setOwnerPanNumberTrns("");
					regForm.setOwnerInddistrictTrns("");
					regForm.setOwnerIndCategoryTrns("");
				}
				regForm.setTermsCondPartyTrns("");
				regForm.setListAdoptedPartyTrns(null);
				forward = "transactingParty";
			}
			if (RegInitConstant.DELETE_MORE_ACTION.equals(actionName)) {
				String[] trnsPrtyID = regForm.getHdnDeleteTrnsPrtyId().split(",");
				int count = regForm.getMapKeyCount() - 1;
				map = regForm.getMapTransactingParties();
				for (int i = 0; i < trnsPrtyID.length; i++) {
					map.remove(trnsPrtyID[i]);
				}
				regForm.setMapTransactingParties(map);
				// above for display map
				// below for insertion map
				map = new HashMap();
				map = regForm.getMapTransPartyDbInsertion();
				for (int j = 0; j < trnsPrtyID.length; j++) {
					logger.debug(trnsPrtyID[j] + " is removed...");
					map.remove(trnsPrtyID[j]);
				}
				regForm.setAddMoreCounter(count);
				regForm.setMapKeyCount(count);
				if (map.size() == 0) {
					regForm.setAddMoreCounter(0);
				}
				regForm.setMapTransPartyDbInsertion(map);
				forward = "transactingParty";
				// request.setAttribute("roleIdTrns",regForm.getPartyTypeTrns());
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				regForm.setActionName(RegInitConstant.NO_ACTION);
				actionName = RegInitConstant.NO_ACTION;
				// return mapping.findForward(forward);
			}
			if (RegInitConstant.RESET_TRANSACTING_ACTION.equals(actionName)) {
				if (regForm.getPartyTypeTrns() != null && !regForm.getPartyTypeTrns().equalsIgnoreCase("") && !regForm.getPartyTypeTrns().equalsIgnoreCase("null")) {
					request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
				} else {
					request.setAttribute("roleIdTrns", 0);
				}
				// String partyTypeTrns = regForm.getListAdoptedPartyTrns();
				// session.setAttribute("partyType", partyTypeTrns);
				// resetToken(request);
				regForm.setDeleteOwnerID("");
				regForm.setHdnDeleteOwnerID("");
				// regForm.setOwnerGendarVal("");
				// regForm.setOwnerModifyFlag(0);
				// regForm.setAppOwnerList(new HashMap());
				regForm.setTrnsOwnerList(new HashMap());
				regForm.setNameOfOfficialTrns("");
				regForm.setDesgOfOfficialTrns("");
				regForm.setAddressOfOfficialTrns("");
				regForm.setAddressGovtOffclOutMpTrns("");
				regForm.setAddressAuthPerOutMpTrns("");
				regForm.setAddressIndOutMpTrns("");
				regForm.setAddressOrgOutMpTrns("");
				regForm.setAddressOwnerOutMpTrns("");
				regForm.setFnameTrns("");
				regForm.setMnameTrns("");
				regForm.setLnameTrns("");
				regForm.setGendarTrns("M");
				regForm.setAgeTrns("");
				// Added Guaridan Blank - Rahul
				regForm.setGuardianTrans("");
				regForm.setFatherNameTrns("");
				regForm.setMotherNameTrns("");
				regForm.setSpouseNameTrns("");
				regForm.setNationalityTrns("");
				regForm.setIndaddressTrns("");
				regForm.setIndcountryTrns("");
				regForm.setIndstatenameTrns("");
				regForm.setInddistrictTrns("");
				regForm.setPostalCodeTrns("");
				regForm.setIndphnoTrns("");
				regForm.setIndmobnoTrns("");
				regForm.setEmailIDTrns("");
				regForm.setListIDTrns("");
				regForm.setIdnoTrns("");
				regForm.setIndCasteTrns("");
				regForm.setIndReligionTrns("");
				regForm.setIndDisabilityTrns("N");
				regForm.setShareOfPropTrns("");
				regForm.setOwnershipShareTrns("");
				regForm.setRelationWithOwnerTrns("");
				regForm.setIndDisabilityDescTrns("");
				commonDto.setStateTrns(commonBo.getState("1", languageLocale));
				commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));
				commonDto.setIndstateTrns(commonBo.getState("1", languageLocale));
				commonDto.setInddistrictTrns(commonBo.getDistrict("1", languageLocale));
				// commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale));
				commonDto.setCountryTrns(commonBo.getCountry(languageLocale));
				commonDto.setIndcountryTrns(commonBo.getCountry(languageLocale));
				commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
				commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				regForm.setRelationshipTrns(0);
				// commonDto.setDistrictTrns(commonBo.getDistrict("1"));
				// regForm.setIndPovertyLine("");
				regForm.setIndPovertyLineTrns("N");
				regForm.setIndMinorityTrns("N");
				regForm.setIndCategoryTrns("");
				regForm.setIndScheduleAreaTrns("");
				regForm.setPermissionNoTrns("");
				regForm.setOccupationIdTrns("");
				regForm.setCertificateTrns(null);
				regForm.setDocumentNameTrns("");
				regForm.setDocumentSizeTrns("");
				regForm.setFilePathTrns("");
				regForm.setOgrNameTrns("");
				regForm.setAuthPerNameTrns("");
				regForm.setOrgaddressTrns("");
				regForm.setCountryTrns("");
				regForm.setStatenameTrns("");
				regForm.setDistrictTrns("");
				regForm.setPhnoTrns("");
				regForm.setMobnoTrns("");
				regForm.setActionName(RegInitConstant.NO_ACTION);
				regForm.setOwnerNameTrns("");
				regForm.setOwnerOgrNameTrns("");
				regForm.setOwnerAddressTrns("");
				regForm.setOwnerAgeTrns("");
				regForm.setOwnerEmailIDTrns("");
				regForm.setOwnerGendarTrns("M");
				regForm.setOwnerIdnoTrns("");
				regForm.setOwnerListIDTrns("");
				regForm.setOwnerNationalityTrns("");
				regForm.setOwnerPhnoTrns("");
				regForm.setOwnerProofNameTrns("");
				regForm.setCountryTrns("1");
				regForm.setCountryNameTrns("INDIA");
				regForm.setStatenameTrns("1");
				regForm.setStatenameNameTrns("MADHYA PRADESH");
				regForm.setIndcountryTrns("1");
				regForm.setIndcountryNameTrns("INDIA");
				regForm.setIndstatenameTrns("1");
				regForm.setIndstatenameNameTrns("MADHYA PRADESH");
				regForm.setUserDayTrns("");
				regForm.setUserMonthTrns("");
				regForm.setUserYearTrns("");
				regForm.setUserDOBTrns("");
				regForm.setOwnerIndcountryTrns("1");
				regForm.setOwnerIndstatenameTrns("1");
				regForm.setCertificateTrns(null);
				regForm.setDocumentNameTrns("");
				regForm.setDocumentSizeTrns("");
				regForm.setFilePathTrns("");
				regForm.setDocContentsTrns(new byte[0]);
				regForm.setPartyOrPropTrns("");
				regForm.setUploadTypeTrns("");
				regForm.setU2Trns(null);
				regForm.setU2DocumentNameTrns("");
				regForm.setU2DocumentSizeTrns("");
				regForm.setU2FilePathTrns("");
				regForm.setU2DocContentsTrns(new byte[0]);
				regForm.setU2PartyOrPropTrns("");
				regForm.setU2UploadTypeTrns("");
				regForm.setU3Trns(null);
				regForm.setU3DocumentNameTrns("");
				regForm.setU3DocumentSizeTrns("");
				regForm.setU3FilePathTrns("");
				regForm.setU3DocContentsTrns(new byte[0]);
				regForm.setU3PartyOrPropTrns("");
				regForm.setU3UploadTypeTrns("");
				regForm.setU4Trns(null);
				regForm.setU4DocumentNameTrns("");
				regForm.setU4DocumentSizeTrns("");
				regForm.setU4FilePathTrns("");
				regForm.setU4DocContentsTrns(new byte[0]);
				regForm.setU4PartyOrPropTrns("");
				regForm.setU4UploadTypeTrns("");
				regForm.setU5Trns(null);
				regForm.setU5DocumentNameTrns("");
				regForm.setU5DocumentSizeTrns("");
				regForm.setU5FilePathTrns("");
				regForm.setU5DocContentsTrns(new byte[0]);
				regForm.setU5PartyOrPropTrns("");
				regForm.setU5UploadTypeTrns("");
				regForm.setU6Trns(null);
				regForm.setU6DocumentNameTrns("");
				regForm.setU6DocumentSizeTrns("");
				regForm.setU6FilePathTrns("");
				regForm.setU6DocContentsTrns(new byte[0]);
				regForm.setU6PartyOrPropTrns("");
				regForm.setU6UploadTypeTrns("");
				regForm.setU7Trns(null);
				regForm.setU7DocumentNameTrns("");
				regForm.setU7DocumentSizeTrns("");
				regForm.setU7FilePathTrns("");
				regForm.setU7DocContentsTrns(new byte[0]);
				regForm.setU7PartyOrPropTrns("");
				regForm.setU7UploadTypeTrns("");
				regForm.setU8Trns(null);
				regForm.setU8DocumentNameTrns("");
				regForm.setU8DocumentSizeTrns("");
				regForm.setU8FilePathTrns("");
				regForm.setU8DocContentsTrns(new byte[0]);
				regForm.setU8PartyOrPropTrns("");
				regForm.setU8UploadTypeTrns("");
				regForm.setU9Trns(null);
				regForm.setU9DocumentNameTrns("");
				regForm.setU9DocumentSizeTrns("");
				regForm.setU9FilePathTrns("");
				regForm.setU9DocContentsTrns(new byte[0]);
				regForm.setU9PartyOrPropTrns("");
				regForm.setU9UploadTypeTrns("");
				regForm.setU10Trns(null);
				regForm.setU10DocumentNameTrns("");
				regForm.setU10DocumentSizeTrns("");
				regForm.setU10FilePathTrns("");
				regForm.setU10DocContentsTrns(new byte[0]);
				regForm.setU10PartyOrPropTrns("");
				regForm.setU10UploadTypeTrns("");
				regForm.setU11Trns(null);
				regForm.setU11DocumentNameTrns("");
				regForm.setU11DocumentSizeTrns("");
				regForm.setU11FilePathTrns("");
				regForm.setU11DocContentsTrns(new byte[0]);
				regForm.setU11PartyOrPropTrns("");
				regForm.setU11UploadTypeTrns("");
				regForm.setAuthPerAddressTrns("");
				regForm.setAuthPerCountryTrns("1");
				regForm.setAuthPerDistrictTrns("");
				regForm.setAuthPerStatenameTrns("1");
				regForm.setAuthPerFatherNameTrns("");
				regForm.setAuthPerRelationshipTrns(0);
				regForm.setAuthPerGendarTrns("M");
				// regForm.setIndstatenameTrns("MP");
				// regForm.setIndstatenameNameTrns("MADHYA PRADESH");
				// session.setAttribute("commonDto", commonDto);
				regForm.setCommonDto(commonDto);
				// Aadhar check - start
				regForm.setAadharCheck("");
				regForm.setAadharErrMsg("");
				regForm.setAadharName("");
				regForm.setAadharNameTrns("");
				regForm.setAadharRespCode("");
				regForm.setAadharCheckOwner("");
				regForm.setAadharErrMsgOwner("");
				regForm.setAadharNameOwner("");
				regForm.setAadharNameTrnsOwner("");
				regForm.setTransactionId("");
				regForm.setAcknowledgementId("");
				regForm.setAadharDto(null);
				regForm.setAadharRespDto(null);
				regForm.setGovernmentOfficeName("");
				regForm.setGovernmentOfficeNameTrans("");
				// Aadhar check - end
				// session.setAttribute("regForm", regForm);
				// for transacting
				regForm.setOwnerIndphnoTrns("");
				regForm.setOwnerPostalCodeTrns("");
				regForm.setOwnerIndDisabilityTrns("N");
				regForm.setOwnerIndDisabilityDescTrns("");
				regForm.setOwnerIndMinorityTrns("N");
				regForm.setOwnerIndPovertyLineTrns("N");
				regForm.setOwnerOccupationIdTrns("");
				regForm.setOwnerFatherNameTrns("");
				regForm.setOwnerMotherNameTrns("");
				regForm.setOwnerSpouseNameTrns("");
				regForm.setOwnerRelationshipTrns("");
				regForm.setOwnerIndScheduleAreaTrns("N");
				regForm.setOwnerPermissionNoTrns("");
				regForm.setOwnerCertificateTrns("");
				regForm.setOwnerPanNumberTrns("");
				regForm.setOwnerInddistrictTrns("");
				regForm.setOwnerIndCategoryTrns("");
				regForm.setOwnerIndcountryTrns("1");
				regForm.setOwnerIndstatenameTrns("1");
				forward = "transactingParty";
			}
		}
		// back from upload action code here
		if (RegInitConstant.PARTY_PAGE_FORM.equals(formName)) {
			if (RegInitConstant.OWNER_POPUP_MODIFY_CLOSE_ACTION.equals(actionName)) {
				// regForm.setPartyModifyFlag(1);
				String partyId = request.getParameter("key");
				String roleId = request.getParameter("role");
				if (partyId != null && roleId != null) {
					regForm.setPartyTxnId(partyId);
					regForm.setPartyType(roleId);
				}
				HashMap partyDispMap = regForm.getMapTransactingPartiesDisp();
				RegCommonDTO mapDto = (RegCommonDTO) partyDispMap.get(regForm.getPartyTxnId());
				mapDto.setTrnsOwnerList(commonBo.getOwnersHashMap(regForm.getPartyTxnId(), regForm.getHidnRegTxnId()));
				// commonBo.openPartyView(request, regForm, languageLocale);
				if (regForm.getPartyModifyFlag() == 1) {
					regForm.setOwnerModifyFlag(1);
					// commonBo.getPartyDetsForViewConfirmModify(regForm);
					regForm.setTrnsOwnerList(mapDto.getTrnsOwnerList());
					/*
					 * commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale)
					 * );
					 * commonDto.setOccupationList(commonBo.getOccupationList(
					 * languageLocale));
					 * commonDto.setRelationshipList(commonBo.getRelationshipList
					 * (regForm.getGendarTrns(),languageLocale));
					 * commonDto.setAuthPerRelationshipList
					 * (commonBo.getRelationshipList
					 * (regForm.getAuthPerGendarTrns(),languageLocale));
					 * regForm.setActionName(RegInitConstant.NO_ACTION);
					 * commonDto
					 * .setDistrictTrns(commonBo.getDistrict("1",languageLocale
					 * ));//state id passed is for MP
					 * commonDto.setCategoryList(commonBo
					 * .getCategoryList(languageLocale));
					 * regForm.setPartyModifyFlag(1);
					 * regForm.setOwnerModifyFlag(1);
					 * request.setAttribute("deedId", regForm.getDeedID());
					 * request.setAttribute("instId", regForm.getInstID());
					 * request.setAttribute("roleId", regForm.getPartyType());
					 * String[]
					 * claimantArr=commonBo.getClaimantFlag(regForm.getPartyType
					 * ());
					 * regForm.setClaimantFlag(Integer.parseInt(claimantArr[
					 * 0].trim()));
					 * regForm.setPoaHolderFlag(Integer.parseInt(claimantArr
					 * [1].trim()));
					 */
				} else {
					regForm.setOwnerModifyFlag(0);
				}
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				request.setAttribute("roleId", regForm.getPartyType());
				forward = "displayRegDetls";
				actionName = "";
				regForm.setActionName("");
				return mapping.findForward(forward);
			}
			if (RegInitConstant.ADD_MORE_APP_OWNER_ACTION.equals(actionName)) {
				RegCommonDTO mapDto = new RegCommonDTO();
				mapDto.setOwnerNameTrns(regForm.getOwnerName());
				if (regForm.getOwnerOgrName().equalsIgnoreCase("") || regForm.getOwnerOgrName().equalsIgnoreCase("null"))
					mapDto.setOwnerOgrNameTrns("-");
				else
					mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrName());
				mapDto.setOwnerGendarValTrns(regForm.getOwnerGendar());
				if (regForm.getOwnerGendar().equalsIgnoreCase("f")) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);
					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);
					}
				} else if (regForm.getOwnerGendar().equalsIgnoreCase("m")) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);
					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);
					}
				} else {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.other_gender_ENGLISH);
					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.other_gender_hindi);
					}
				}
				mapDto.setOwnerNationalityTrns(regForm.getOwnerNationality());
				mapDto.setOwnerAddressTrns(regForm.getOwnerAddress());
				mapDto.setOwnerPhnoTrns(regForm.getOwnerPhno());
				if (regForm.getOwnerEmailID().equalsIgnoreCase("") || regForm.getOwnerEmailID().equalsIgnoreCase("null"))
					mapDto.setOwnerEmailIDTrns("-");
				else
					mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailID());
				mapDto.setOwnerAgeTrns(regForm.getOwnerAge());
				mapDto.setOwnerIdnoTrns(regForm.getOwnerIdno().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdno());
				mapDto.setOwnerListIDTrns(regForm.getOwnerListID());
				/*
				 * if (regForm.getClrFlg() != null) { if
				 * (regForm.getClrFlg().equalsIgnoreCase("y") &&
				 * regForm.getListAdoptedParty
				 * ().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
				 * mapDto.setOwnerListIDTrns("7"); } } else {
				 * mapDto.setOwnerListIDTrns(regForm.getOwnerListID()); }
				 */
				mapDto.setOwnerProofNameTrns(regForm.getOwnerListID().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofName());
				/*
				 * if (regForm.getClrFlg() != null) { if
				 * (regForm.getClrFlg().equalsIgnoreCase("y") &&
				 * regForm.getListAdoptedParty
				 * ().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
				 * mapDto.setOwnerProofNameTrns("AADHAR CARD"); } } else {
				 * mapDto
				 * .setOwnerProofNameTrns(regForm.getOwnerListID().equalsIgnoreCase
				 * ("") ? "-" : regForm.getOwnerProofName()); }
				 */
				if (regForm.getAddressOwnerOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMp())) {
					mapDto.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMp());
				} else {
					mapDto.setAddressOwnerOutMpTrns("-");
				}
				// added for aadhar - start
				if (regForm.getAadharNameOwner() != null && !("").equalsIgnoreCase(regForm.getAadharNameOwner())) {
					mapDto.setAadharNameOwner(regForm.getAadharNameOwner());
					regForm.setAadharNameOwner("");
					regForm.setAadharCheckOwner("");
					regForm.setAadharRespCodeOwner("");
					regForm.setAadharErrMsgOwner("");
				} else {
					mapDto.setAadharNameOwner("");
				}
				if (regForm.getAadharRespDto() != null) {
					if (!"".equalsIgnoreCase(regForm.getAadharRespDto().getTransactionCode()) && !"".equalsIgnoreCase(regForm.getAadharRespDto().getTransactionId())) {
						mapDto.setAadharRespDto(regForm.getAadharRespDto());
					}
				}
				// added for aadhar - end
				mapDto.setOwnerIndCategoryTrns(regForm.getOwnerIndCategory());
				mapDto.setOwnerIndcountryTrns(regForm.getOwnerIndcountry());
				mapDto.setOwnerIndstatenameTrns(regForm.getOwnerIndstatename());
				mapDto.setOwnerInddistrictTrns(regForm.getOwnerInddistrict());
				mapDto.setOwnerIndphnoTrns(regForm.getOwnerIndphno().equalsIgnoreCase("") ? "" : regForm.getOwnerIndphno());
				mapDto.setOwnerPostalCodeTrns(regForm.getOwnerPostalCode().equalsIgnoreCase("") ? "" : regForm.getOwnerPostalCode());
				mapDto.setOwnerIndDisabilityTrns(regForm.getOwnerIndDisability());
				mapDto.setOwnerIndDisabilityDescTrns(regForm.getOwnerIndDisabilityDesc().equalsIgnoreCase("") ? "-" : regForm.getOwnerIndDisabilityDesc());
				mapDto.setOwnerIndMinorityTrns(regForm.getOwnerIndMinority());
				mapDto.setOwnerIndPovertyLineTrns(regForm.getOwnerIndPovertyLine());
				mapDto.setOwnerOccupationIdTrns(regForm.getOwnerOccupationId());
				mapDto.setOwnerRelationshipTrns(regForm.getOwnerRelationship());
				mapDto.setOwnerFatherNameTrns(regForm.getOwnerFatherName().equalsIgnoreCase("") ? "-" : regForm.getOwnerFatherName());
				mapDto.setOwnerMotherNameTrns(regForm.getOwnerMotherName().equalsIgnoreCase("") ? "-" : regForm.getOwnerMotherName());
				mapDto.setOwnerSpouseNameTrns(regForm.getOwnerSpouseName().equalsIgnoreCase("") ? "-" : regForm.getOwnerSpouseName());
				// mapDto.setOwnerPanNumberTrns(regForm.getOwnerPanNumber());
				mapDto.setOwnerPanNumberTrns(regForm.getOwnerPanNumber().equalsIgnoreCase("") ? "-" : regForm.getOwnerPanNumber());
				mapDto.setOwnerPermissionNoTrns(regForm.getOwnerPermissionNo().equalsIgnoreCase("") ? "-" : regForm.getOwnerPermissionNo());
				mapDto.setOwnerIndScheduleAreaTrns(regForm.getOwnerIndScheduleArea().equalsIgnoreCase("") ? "" : regForm.getOwnerIndScheduleArea());
				mapDto.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());
				mapDto.setOwnerInddistrictTrnsName(commonBo.getDistrictName(regForm.getOwnerInddistrict(), languageLocale));
				mapDto.setOwnerIndCategoryTrnsName(commonBo.getCategoryName(regForm.getOwnerIndCategory(), languageLocale));
				mapDto.setOwnerOccupationIdTrnsName(commonBo.getOccupationName(regForm.getOwnerOccupationId(), languageLocale));
				mapDto.setOwnerRelationshipTrnsName(commonBo.getRelationshipName(regForm.getOwnerRelationship(), languageLocale));
				regForm.getAppOwnerList().put(mapDto.getOwnerTxnId(), mapDto);
				regForm.setOwnerName("");
				regForm.setOwnerOgrName("");
				regForm.setOwnerGendar("M");
				regForm.setOwnerNationality("");
				regForm.setOwnerAddress("");
				regForm.setOwnerPhno("");
				regForm.setOwnerEmailID("");
				regForm.setOwnerAge("");
				regForm.setOwnerIdno("");
				regForm.setOwnerListID("");
				regForm.setOwnerProofName("");
				regForm.setAddressOwnerOutMp("");
				regForm.setAadharNameOwner("");// added for aadhar
				regForm.setAadharCheckOwner("");// added for aadhar
				regForm.setAadharErrMsgOwner("");// added for aadhar
				regForm.setOwnerPanNumber("");
				regForm.setOwnerIndphno("");
				regForm.setOwnerPostalCode("");
				regForm.setOwnerIndDisability("N");
				regForm.setOwnerInddistrict("");
				regForm.setOwnerIndCategory("");
				regForm.setOwnerIndCategoryName("");
				regForm.setOwnerIndDisabilityDesc("");
				regForm.setOwnerIndMinority("N");
				regForm.setOwnerIndPovertyLine("N");
				regForm.setOwnerOccupationId("");
				regForm.setOwnerOccupationIdName("");
				regForm.setOwnerRelationship("");
				regForm.setOwnerRelationshipName("");
				regForm.setOwnerInddistrictName("");
				regForm.setOwnerFatherName("");
				regForm.setOwnerMotherName("");
				regForm.setOwnerSpouseName("");
				regForm.setOwnerPermissionNo("");
				regForm.setOwnerIndScheduleArea("N");
				commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendar(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "success";
			}
			if (RegInitConstant.DELETE_MORE_APP_OWNER_ACTION.equals(actionName)) {
				String[] trnsPrtyID = regForm.getHdnDeleteOwnerID().split(",");
				HashMap ownerMap = regForm.getAppOwnerList();
				for (int i = 0; i < trnsPrtyID.length; i++) {
					ownerMap.remove(trnsPrtyID[i]);
				}
				regForm.setAppOwnerList(ownerMap);
				forward = "success";
				regForm.setActionName(RegInitConstant.NO_ACTION);
				actionName = RegInitConstant.NO_ACTION;
			}
			if (RegInitConstant.GENDER_ACTION.equals(actionName)) {
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "success";
			}
			if (RegInitConstant.AUTH_PER_GENDER_ACTION.equals(actionName)) {
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "success";
			}
			if (RegInitConstant.OWNER_GENDER_ACTION.equals(actionName)) {
				commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendar(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "success";
			}
			if (RegInitConstant.OWNER_GENDER_ACTION_TRNS.equals(actionName)) {
				commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "success";
			}
			if (RegInitConstant.GENDER_MODIFY_ACTION.equals(actionName)) {
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "displayRegDetls";
				// return mapping.findForward(forward);
			}
			if (RegInitConstant.AUTH_PER_GENDER_MODIFY_ACTION.equals(actionName)) {
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "displayRegDetls";
				// return mapping.findForward(forward);
			}
			if (RegInitConstant.CHANGE_PARTY_ACTION.equals(actionName)) {
				String partyType = regForm.getListAdoptedParty();
				/*
				 * if("7".equalsIgnoreCase(regForm.getListID()) ||
				 * "7".equalsIgnoreCase(regForm.getListIDTrns()) ||
				 * "7".equalsIgnoreCase(regForm.getOwnerListID()) ||
				 * "7".equalsIgnoreCase(regForm.getOwnerListIDTrns())){
				 * 
				 * 
				 * vdvfvbfbgbgbg }
				 */
				regForm.setOwnerListID("");
				regForm.setOwnerIdno("");
				regForm.setAadharNameOwner("");
				regForm.setAadharCheckOwner("");
				regForm.setAadharRespCodeOwner("");
				regForm.setAadharErrMsgOwner("");
				regForm.setAppOwnerList(new HashMap());
				logger.debug("partyType:-" + partyType);
				// saveToken(request);
			}
			if (RegInitConstant.NEXT_ACTION.equals(actionName)) {
				// if(isTokenValid(request)) {
				// following code for populating drop downs
				// commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale));
				commonDto.setCountryTrns(commonBo.getCountry(languageLocale));
				commonDto.setIndcountryTrns(commonBo.getCountry(languageLocale));
				commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
				commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				// BELOW CODE FOR INSERTING APPLICANT DETAILS IN DATABASE.
				regForm.setHidnUserId(userId);
				if (regForm.getIsMultiplePropsFlag() == 0) {
					Calendar currentDate = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
					String dateNow = formatter.format(currentDate.getTime());
					String regTxnIdSeq = commonBo.getRegInitTxnIdSeq();
					String regTxnId = null;
					regTxnId = dateNow + regTxnIdSeq;
					regForm.setHidnRegTxnId(regTxnId);
				}
				int roleId = Integer.parseInt(regForm.getPartyType());
				String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
				int claimantFlag = Integer.parseInt(claimantArr[0].trim());
				regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
				boolean applicantDetailsInserted = false;
				boolean allUploadSuccessful = false;
				String filePath;
				String filePathPhotoProof;
				String filePathNotAffExec;
				// String filePathExecPhoto;
				// String filePathNotAffAttrn;
				String filePathAttrnPhoto;
				// String filePathClaimPhoto;
				String filePathPanForm60;
				regForm.setU2FilePath("");
				regForm.setU3FilePath("");
				regForm.setU10FilePath("");
				regForm.setU6FilePath("");
				regForm.setU11FilePath("");
				regForm.setU9FilePath("");
				regForm.setFilePath("");
				if (regForm.getGuardian().equalsIgnoreCase("") || regForm.getGuardian().equalsIgnoreCase("null"))
					regForm.setGuardianTrans("-");
				else
					regForm.setGuardianTrans(regForm.getGuardian());
				/*
				 * if(regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant
				 * .INDIVIDUAL_ID) ||
				 * regForm.getListAdoptedParty().equalsIgnoreCase
				 * (RegInitConstant.ORGANISATION_ID))
				 */
				if (!regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					if ((regForm.getU2DocumentName() != null && !regForm.getU2DocumentName().equalsIgnoreCase(""))) {
						filePathPhotoProof = uploadFile(regForm.getHidnRegTxnId(), regForm.getU2DocContents(), regForm.getPartyRoleTypeId(), regForm.getU2PartyOrProp(), regForm.getU2UploadType()); // for
						// pan
					} else {
						filePathPhotoProof = "";
					}
					if (filePathPhotoProof != null) {
						regForm.setU2FilePath(filePathPhotoProof);
						// BELOW CODE FOR EXECUTANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
							if (regForm.getU3DocumentName() != null && !regForm.getU3DocumentName().equalsIgnoreCase("")) {
								filePathNotAffExec = uploadFile(regForm.getHidnRegTxnId(), regForm.getU3DocContents(), regForm.getPartyRoleTypeId(), regForm.getU3PartyOrProp(), regForm.getU3UploadType());
							} else {
								filePathNotAffExec = "";
							}
							if (filePathNotAffExec != null) {
								regForm.setU3FilePath(filePathNotAffExec);
								// filePathExecPhoto=uploadFile(regForm.getHidnRegTxnId(),regForm.getU4DocContents(),
								// regForm.getPartyRoleTypeId(),regForm.getU4PartyOrProp(),regForm.getU4UploadType());
								// if(filePathExecPhoto!=null){
								// regForm.setU4FilePath(filePathExecPhoto);
								if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
									if (!regForm.getListID().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentName() != null && !regForm.getU10DocumentName().equalsIgnoreCase("")))) {
										filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContents(), regForm.getPartyRoleTypeId(), regForm.getU10PartyOrProp(), regForm.getU10UploadType());
										if (filePathPanForm60 != null) {
											regForm.setU10FilePath(filePathPanForm60);
											allUploadSuccessful = true;
										} else {
											allUploadSuccessful = false;
										}
									} else {
										allUploadSuccessful = true;
									}
								} else {
									if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentName() != null && !regForm.getU10DocumentName().equalsIgnoreCase("")))) {
										filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContents(), regForm.getPartyRoleTypeId(), regForm.getU10PartyOrProp(), regForm.getU10UploadType());
										if (filePathPanForm60 != null) {
											regForm.setU10FilePath(filePathPanForm60);
											allUploadSuccessful = true;
										} else {
											allUploadSuccessful = false;
										}
									} else {
										allUploadSuccessful = true;
									}
								}
								// }else{
								// allUploadSuccessful=false;
								// }
							} else {
								allUploadSuccessful = false;
							}
						}
						// BELOW CODE FOR EXECUTANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_5) {
							// filePathExecPhoto=uploadFile(regForm.getHidnRegTxnId(),regForm.getU7DocContents(),
							// regForm.getPartyRoleTypeId(),regForm.getU7PartyOrProp(),regForm.getU7UploadType());
							// if(filePathExecPhoto!=null)
							// {
							// regForm.setU7FilePath(filePathExecPhoto);
							// filePathNotAffAttrn=uploadFile(regForm.getHidnRegTxnId(),regForm.getU5DocContents(),
							// regForm.getPartyRoleTypeId(),regForm.getU5PartyOrProp(),regForm.getU5UploadType());
							// if(filePathNotAffAttrn!=null)
							// {
							// regForm.setU5FilePath(filePathNotAffAttrn);
							filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), regForm.getU6DocContents(), regForm.getPartyRoleTypeId(), regForm.getU6PartyOrProp(), regForm.getU6UploadType());
							if (filePathAttrnPhoto != null) {
								regForm.setU6FilePath(filePathAttrnPhoto);
								allUploadSuccessful = true;
							} else {
								allUploadSuccessful = false;
							}
							// }
							// }
						}
						// BELOW CODE FOR CLAIMANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
							// filePathClaimPhoto=uploadFile(regForm.getHidnRegTxnId(),regForm.getU8DocContents(),
							// regForm.getPartyRoleTypeId(),regForm.getU8PartyOrProp(),regForm.getU8UploadType());
							// if(filePathClaimPhoto!=null){
							// regForm.setU8FilePath(filePathClaimPhoto);
							if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
								if (!regForm.getListID().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentName() != null && !regForm.getU11DocumentName().equalsIgnoreCase("")))) {
									filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContents(), regForm.getPartyRoleTypeId(), regForm.getU11PartyOrProp(), regForm.getU11UploadType());
									if (filePathPanForm60 != null) {
										regForm.setU11FilePath(filePathPanForm60);
										allUploadSuccessful = true;
									} else {
										allUploadSuccessful = false;
									}
								} else {
									allUploadSuccessful = true;
								}
							} else {
								if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentName() != null && !regForm.getU11DocumentName().equalsIgnoreCase("")))) {
									filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContents(), regForm.getPartyRoleTypeId(), regForm.getU11PartyOrProp(), regForm.getU11UploadType());
									if (filePathPanForm60 != null) {
										regForm.setU11FilePath(filePathPanForm60);
										allUploadSuccessful = true;
									} else {
										allUploadSuccessful = false;
									}
								} else {
									allUploadSuccessful = true;
								}
							}
							// }else{
							// allUploadSuccessful=false;
							// }
						}
						// BELOW CODE FOR CLAIMANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_6) {
							filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), regForm.getU9DocContents(), regForm.getPartyRoleTypeId(), regForm.getU9PartyOrProp(), regForm.getU9UploadType());
							if (filePathAttrnPhoto != null) {
								regForm.setU9FilePath(filePathAttrnPhoto);
								allUploadSuccessful = true;
							} else {
								allUploadSuccessful = false;
							}
						}
						if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID) && regForm.getIndCategory().equalsIgnoreCase("1") && regForm.getIndScheduleArea().equalsIgnoreCase("N")) {
							filePath = uploadFile(regForm.getHidnRegTxnId(), regForm.getDocContents(), regForm.getPartyRoleTypeId(), regForm.getPartyOrProp(), regForm.getUploadType());
							if (filePath != null) {
								regForm.setFilePath(filePath);
								allUploadSuccessful = true;
							} else {
								allUploadSuccessful = false;
							}
						}
					}
				} else {
					allUploadSuccessful = true;
				}
				if (allUploadSuccessful) {
					boolean check = true;
					ArrayList<CommonDTO> dto = regForm.getListDto();
					if (dto != null && dto.size() > 0) {
						for (int i = 0; i < dto.size(); i++) {
							CommonDTO dtos = dto.get(i);
							dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(i)));
							String checkUpload = uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), regForm.getPartyRoleTypeId(), "01", dtos.getDocumentName());
							if (checkUpload == null) {
								check = false;
								break;
							} else {
								dtos.setDocumentPath(checkUpload);
								check = true;
							}
						}
						/*
						 * if(check==true) { check =
						 * commonBo.insertAdditionalUploads(regForm); }
						 */
					} else {
						check = true;
					}
					if (check) {
						if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
							RegCommonDTO mapDto = new RegCommonDTO();
							mapDto.setOwnerNameTrns(regForm.getOwnerName());
							if (regForm.getOwnerOgrName().equalsIgnoreCase("") || regForm.getOwnerOgrName().equalsIgnoreCase("null"))
								mapDto.setOwnerOgrNameTrns("-");
							else
								mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrName());
							mapDto.setOwnerGendarValTrns(regForm.getOwnerGendar());
							if (regForm.getOwnerGendar().equalsIgnoreCase("f")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);
								} else {
									mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);
								}
							} else if (regForm.getOwnerGendar().equalsIgnoreCase("m")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);
								} else {
									mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setOwnerGendarTrns(RegInitConstant.other_gender_ENGLISH);
								} else {
									mapDto.setOwnerGendarTrns(RegInitConstant.other_gender_hindi);
								}
							}
							mapDto.setOwnerNationalityTrns(regForm.getOwnerNationality());
							mapDto.setOwnerAddressTrns(regForm.getOwnerAddress());
							mapDto.setOwnerPhnoTrns(regForm.getOwnerPhno());
							if (regForm.getOwnerEmailID().equalsIgnoreCase("") || regForm.getOwnerEmailID().equalsIgnoreCase("null"))
								mapDto.setOwnerEmailIDTrns("-");
							else
								mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailID());
							mapDto.setOwnerAgeTrns(regForm.getOwnerAge());
							mapDto.setOwnerIdnoTrns(regForm.getOwnerIdno().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdno());
							mapDto.setOwnerListIDTrns(regForm.getOwnerListID());
							mapDto.setOwnerIndCategoryTrns(regForm.getOwnerIndCategory());
							mapDto.setOwnerIndCategoryTrnsName(commonBo.getCategoryName(regForm.getOwnerIndCategory(), languageLocale));
							mapDto.setOwnerIndcountryTrns(regForm.getOwnerIndcountry());
							mapDto.setOwnerIndstatenameTrns(regForm.getOwnerIndstatename());
							mapDto.setOwnerInddistrictTrns(regForm.getOwnerInddistrict());
							mapDto.setOwnerInddistrictTrnsName(commonBo.getDistrictName(regForm.getOwnerInddistrict(), languageLocale));
							mapDto.setOwnerIndphnoTrns(regForm.getOwnerIndphno().equalsIgnoreCase("") ? "" : regForm.getOwnerIndphno());
							mapDto.setOwnerPostalCodeTrns(regForm.getOwnerPostalCode().equalsIgnoreCase("") ? "" : regForm.getOwnerPostalCode());
							mapDto.setOwnerIndDisabilityTrns(regForm.getOwnerIndDisability());
							mapDto.setOwnerIndDisabilityDescTrns(regForm.getOwnerIndDisabilityDesc().equalsIgnoreCase("") ? "-" : regForm.getOwnerIndDisabilityDesc());
							mapDto.setOwnerIndMinorityTrns(regForm.getOwnerIndMinority());
							mapDto.setOwnerIndPovertyLineTrns(regForm.getOwnerIndPovertyLine());
							mapDto.setOwnerOccupationIdTrns(regForm.getOwnerOccupationId());
							mapDto.setOwnerOccupationIdTrnsName(commonBo.getOccupationName(regForm.getOwnerOccupationId(), languageLocale));
							mapDto.setOwnerRelationshipTrns(regForm.getOwnerRelationship());
							mapDto.setOwnerRelationshipTrnsName(commonBo.getRelationshipName(regForm.getOwnerRelationship(), languageLocale));
							mapDto.setOwnerFatherNameTrns(regForm.getOwnerFatherName().equalsIgnoreCase("") ? "-" : regForm.getOwnerFatherName());
							mapDto.setOwnerMotherNameTrns(regForm.getOwnerMotherName().equalsIgnoreCase("") ? "-" : regForm.getOwnerMotherName());
							mapDto.setOwnerSpouseNameTrns(regForm.getOwnerSpouseName().equalsIgnoreCase("") ? "-" : regForm.getOwnerSpouseName());
							mapDto.setOwnerPanNumberTrns(regForm.getOwnerPanNumber().equalsIgnoreCase("") ? "-" : regForm.getOwnerPanNumber());
							mapDto.setOwnerPermissionNoTrns(regForm.getOwnerPermissionNo().equalsIgnoreCase("") ? "-" : regForm.getOwnerPermissionNo());
							mapDto.setOwnerIndScheduleAreaTrns(regForm.getOwnerIndScheduleArea().equalsIgnoreCase("-") ? "" : regForm.getOwnerIndScheduleArea());
							// mapDto.setOwnerCertificateTrns(regForm.
							// getOwnerCertificate
							// ().equalsIgnoreCase("")?"":regForm
							// .getOwnerCertificate());
							/*
							 * if (regForm.getClrFlg() != null) { if
							 * (regForm.getClrFlg().equalsIgnoreCase("y") &&
							 * regForm.getListAdoptedParty().equalsIgnoreCase(
							 * RegInitConstant.INDIVIDUAL_ID)) {
							 * mapDto.setOwnerListIDTrns("7"); } } else {
							 * mapDto.
							 * setOwnerListIDTrns(regForm.getOwnerListID()); }
							 */
							mapDto.setOwnerProofNameTrns(regForm.getOwnerListID().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofName());
							if (regForm.getAddressOwnerOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMp())) {
								mapDto.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMp());
							} else {
								mapDto.setAddressOwnerOutMpTrns("-");
							}
							// aadhar for owner
							if (regForm.getAadharNameOwner() != null && !("").equalsIgnoreCase(regForm.getAadharNameOwner())) {
								mapDto.setAadharNameOwner(regForm.getAadharNameOwner());
								regForm.setAadharNameOwner("");
								regForm.setAadharCheckOwner("");
								regForm.setAadharRespCodeOwner("");
								regForm.setAadharErrMsgOwner("");
							} else {
								mapDto.setAadharNameOwner("");
							}
							if (regForm.getAadharRespDto() != null) {
								if (!"".equalsIgnoreCase(regForm.getAadharRespDto().getTransactionCode()) && !"".equalsIgnoreCase(regForm.getAadharRespDto().getTransactionId())) {
									mapDto.setAadharRespDto(regForm.getAadharRespDto());
								}
							}
							// end
							mapDto.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());
							regForm.getAppOwnerList().put(mapDto.getOwnerTxnId(), mapDto);
						}
						// insertion changes for Aadhar integration
						applicantDetailsInserted = commonBo.insertApplicantAndPropertyDetails(regForm); // anuj
						// db
						// insertion
						if (applicantDetailsInserted) {// this if condition
							// added by roopam on
							// 6feb2015 for db
							// constraints
							// correction
							if (check) {
								check = commonBo.insertAdditionalUploads(regForm);
							} else {
								applicantDetailsInserted = false;
							}
						}
					}
				}
				if (applicantDetailsInserted) {
					RegCommonDTO mapDto = new RegCommonDTO();
					mapDto.setClaimantFlag(claimantFlag);
					mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedParty().trim());
					// mapDto.setListAdoptedPartyNameTrns(regForm.getListAdoptedPartyName().trim());
					mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedParty().trim(), languageLocale));
					mapDto.setPurposeTrns(regForm.getPurpose().trim());
					mapDto.setBname("");
					mapDto.setBaddress("");
					mapDto.setApplicantOrTransParty("Applicant");
					mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId().trim());
					mapDto.setUserID(regForm.getHidnUserId().trim());
					/*
					 * if(regForm.getOwnershipShare().equalsIgnoreCase(""))
					 * mapDto.setOwnershipShareTrns("-"); else
					 * mapDto.setOwnershipShareTrns
					 * (regForm.getOwnershipShare().trim());
					 */
					if (regForm.getRelationWithOwner() != null) {
						if (regForm.getRelationWithOwner().equalsIgnoreCase("") || regForm.getRelationWithOwner().equalsIgnoreCase("null")) {
							mapDto.setRelationWithOwnerTrns("-");
						} else {
							mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwner().trim());
						}
					}
					/*
					 * if(regForm.getShareOfProp().equalsIgnoreCase("") ||
					 * regForm.getShareOfProp().equalsIgnoreCase("null")){
					 * mapDto.setShareOfPropTrns("-");
					 * mapDto.setHdnDeclareShareCheck("N"); } else{
					 * mapDto.setShareOfPropTrns
					 * (regForm.getShareOfProp().trim());
					 * mapDto.setHdnDeclareShareCheck("Y"); }
					 */
					mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyType().trim(), languageLocale, regForm.getDeedID(), regForm.getInstID()));
					mapDto.setPartyTypeFlag("A");
					// anuj copy from form into dto, 4 fields
					/*
					 * mapDto.setGovtOfcCheck(regForm.getGovtOfcCheck());
					 * mapDto.setNameOfOfficial(regForm.getNameOfOfficial());
					 * mapDto.setDesgOfOfficial(regForm.getDesgOfOfficial());
					 * mapDto
					 * .setAddressOfOfficial(regForm.getAddressOfOfficial());
					 */
					// below code for uploads other than collector's permission
					// no.
					if (!regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
						mapDto.setU2DocumentNameTrns(regForm.getU2DocumentName());
						mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSize());
						mapDto.setU2FilePathTrns(regForm.getU2FilePath());
						mapDto.setU2DocContentsTrns(regForm.getU2DocContents());
						// BELOW CODE FOR EXECUTANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
							mapDto.setU3DocumentNameTrns(regForm.getU3DocumentName());
							mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSize());
							mapDto.setU3FilePathTrns(regForm.getU3FilePath());
							mapDto.setU3DocContentsTrns(regForm.getU3DocContents());
							/*
							 * mapDto.setU4DocumentNameTrns(regForm.getU4DocumentName
							 * ());mapDto.setU4DocumentSizeTrns(regForm.
							 * getU4DocumentSize());
							 * mapDto.setU4FilePathTrns(regForm
							 * .getU4FilePath());
							 * mapDto.setU4DocContentsTrns(regForm
							 * .getU4DocContents());
							 */
							mapDto.setU10DocumentNameTrns(regForm.getU10DocumentName());
							mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSize());
							mapDto.setU10FilePathTrns(regForm.getU10FilePath());
							mapDto.setU10DocContentsTrns(regForm.getU10DocContents());
						}
						// BELOW CODE FOR EXECUTANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_5) {
							/*
							 * mapDto.setU5DocumentNameTrns(regForm.getU5DocumentName
							 * ());mapDto.setU5DocumentSizeTrns(regForm.
							 * getU5DocumentSize());
							 * mapDto.setU5FilePathTrns(regForm
							 * .getU5FilePath());
							 * mapDto.setU5DocContentsTrns(regForm
							 * .getU5DocContents());
							 */
							mapDto.setU6DocumentNameTrns(regForm.getU6DocumentName());
							mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSize());
							mapDto.setU6FilePathTrns(regForm.getU6FilePath());
							mapDto.setU6DocContentsTrns(regForm.getU6DocContents());
							/*
							 * mapDto.setU7DocumentNameTrns(regForm.getU7DocumentName
							 * ());mapDto.setU7DocumentSizeTrns(regForm.
							 * getU7DocumentSize());
							 * mapDto.setU7FilePathTrns(regForm
							 * .getU7FilePath());
							 * mapDto.setU7DocContentsTrns(regForm
							 * .getU7DocContents());
							 */
							mapDto.setSrOfficeNameTrns(regForm.getSrOfficeName());
							if (regForm.getPoaRegNo() != null && !regForm.getPoaRegNo().equalsIgnoreCase("")) {
								mapDto.setPoaRegNoTrns(regForm.getPoaRegNo());
							} else {
								mapDto.setPoaRegNoTrns("-");
							}
							if (regForm.getDatePoaReg() != null && !regForm.getDatePoaReg().equalsIgnoreCase("")) {
								mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaReg()));
							} else {
								mapDto.setDatePoaRegTrns("-");
							}
						}
						// BELOW CODE FOR CLAIMANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
							/*
							 * mapDto.setU8DocumentNameTrns(regForm.getU8DocumentName
							 * ());mapDto.setU8DocumentSizeTrns(regForm.
							 * getU8DocumentSize());
							 * mapDto.setU8FilePathTrns(regForm
							 * .getU8FilePath());
							 * mapDto.setU8DocContentsTrns(regForm
							 * .getU8DocContents());
							 */
							mapDto.setU11DocumentNameTrns(regForm.getU11DocumentName());
							mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSize());
							mapDto.setU11FilePathTrns(regForm.getU11FilePath());
							mapDto.setU11DocContentsTrns(regForm.getU11DocContents());
						}
						// BELOW CODE FOR CLAIMANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_6) {
							mapDto.setU9DocumentNameTrns(regForm.getU9DocumentName());
							mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSize());
							mapDto.setU9FilePathTrns(regForm.getU9FilePath());
							mapDto.setU9DocContentsTrns(regForm.getU9DocContents());
						}
					}
					// ArrayList<CommonDTO> liDto =
					// commonBo.getAdditonalUploads(regForm.getHidnRegTxnId(),regForm.getPartyRoleTypeId());
					mapDto.setListDto(regForm.getListDto());
					regForm.setListDto(new ArrayList<CommonDTO>());
					if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
						// mapDto.setGovtOfcCheck(regForm.getGovtOfcCheck());
						if (regForm.getNameOfOfficial() != null && !("").equalsIgnoreCase(regForm.getNameOfOfficial())) {
							mapDto.setNameOfOfficial(regForm.getNameOfOfficial());
						} else {
							mapDto.setNameOfOfficial("-");
						}
						mapDto.setDesgOfOfficial(regForm.getDesgOfOfficial());
						mapDto.setAddressOfOfficial(regForm.getAddressOfOfficial());
						mapDto.setSelectedDistrict(regForm.getInddistrict());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrict(), languageLocale));
						mapDto.setOgrNameTrns("-");
						// mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim());
						// mapDto.setIndAuthPersn(mapDto.getDesgOfOfficial());
						mapDto.setIndividualOrOrganization(RegInitConstant.GOVT_OFFCL_ID);
						if (mapDto.getIndividualOrOrganization() == RegInitConstant.GOVT_OFFCL_ID) {
							mapDto.setIndAuthPersn(mapDto.getDesgOfOfficial());
							regForm.setAbc("2");
						}
						if (regForm.getPartyType() != null) {
							mapDto.setPartyTypeTrns(regForm.getPartyType().trim());
						} else {
							mapDto.setPartyTypeTrns("");
						}
						if (regForm.getAddressGovtOffclOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressGovtOffclOutMp())) {
							mapDto.setAddressGovtOffclOutMpTrns(regForm.getAddressGovtOffclOutMp());
						} else {
							mapDto.setAddressGovtOffclOutMpTrns("-");
						}
						if (regForm.getGovernmentOfficeName() != null && !("").equalsIgnoreCase(regForm.getGovernmentOfficeName())) {
							String offcName = commonBd.getGovtOffcName(regForm.getGovernmentOfficeName(), languageLocale);
							mapDto.setGovtOfficeName(offcName);
							regForm.setGovernmentOfficeName("");
						} else {
							mapDto.setGovtOfficeName("-");
						}
						// mapDto.setAddressGovtOffclOutMpTrns(regForm.getAddressGovtOffclOutMp());
					} else if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
						// organization
						mapDto.setOgrNameTrns(regForm.getOgrName().trim());
						mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim());
						// mapDto.setIndAuthPersn(regForm.getAuthPerName().trim());
						mapDto.setOrgaddressTrns(regForm.getOrgaddress().trim());
						mapDto.setSelectedCountry(regForm.getCountry().trim());
						mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
						mapDto.setSelectedState(regForm.getStatename().trim());
						mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
						mapDto.setSelectedDistrict(regForm.getDistrict().trim());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrict().trim(), languageLocale));
						if (regForm.getMobno().trim().equalsIgnoreCase("") || regForm.getMobno().trim().equalsIgnoreCase("null"))
							mapDto.setMobnoTrns("-");
						else
							mapDto.setMobnoTrns(regForm.getMobno().trim());
						if (regForm.getPhno().trim().equalsIgnoreCase("") || regForm.getPhno().trim().equalsIgnoreCase("null"))
							mapDto.setPhnoTrns("-");
						else
							mapDto.setPhnoTrns(regForm.getPhno().trim());
						mapDto.setFnameTrns("");
						mapDto.setMnameTrns("");
						mapDto.setLnameTrns("");
						mapDto.setGendarTrns("-");
						mapDto.setSelectedGender("");
						mapDto.setAgeTrns("-");
						mapDto.setFatherNameTrns("-");
						mapDto.setMotherNameTrns("");
						mapDto.setSpouseNameTrns("");
						mapDto.setNationalityTrns("");
						mapDto.setPostalCodeTrns("");
						mapDto.setEmailIDTrns("");
						mapDto.setSelectedPhotoId("");
						mapDto.setSelectedPhotoIdName("-");
						mapDto.setIdnoTrns("-");
						mapDto.setPartyTypeTrns(regForm.getPartyType().trim());
						// mapDto.setIndReligionTrns("");
						mapDto.setIndCasteTrns("");
						mapDto.setIndDisabilityTrns("");
						mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
						// for organisation POA
						if (mapDto.getIndividualOrOrganization() == RegInitConstant.ORGANISATION_ID) {
							if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
								mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
								// if(mapDto.getPoaHolderFlag()==RegInitConstant.POA_HOLDER_FLAG){
								// to fetch hindi english both name
								mapDto.setRoleNameForPOA(commonBo.getRoleNameForPOA(regForm.getPartyType().trim()));
								String RoleName = mapDto.getRoleNameForPOA();
								System.out.println("Role Name " + RoleName);
								String HindiEnglishRole[] = RoleName.split(",");
								String hindesc = HindiEnglishRole[0];
								String EngDesc = HindiEnglishRole[1];
								String PoaName = null;
								if (EngDesc.contains("Authenticated")) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										PoaName = " By(Authenticated PoA Holder) ";
									} else {
										PoaName = " ??????????????????(??????????????????????????????  ?????????????????????????????????) ";
									}
								} else {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										PoaName = " By(PoA Holder) ";
									} else {
										PoaName = " ?????????????????? (????????????????????????????????? ??????)";
									}
								}
								System.out.println("POA NAMe is " + PoaName);
								System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
								String OwnerDetail = null;
								// mapDto.setOwnerPartyDescForOrg(commonBo.getOwnerPartyDescForOrg(regForm.getHidnRegTxnId(),languageLocale));
								mapDto.setOwnerPartyDescForOrg(commonBo.getOwnerPartyDesc(regForm.getHidnRegTxnId(), languageLocale));
								mapDto.setOwnerPartyPoaDescForOrg(commonBo.getOwnerPartyPoaDescForOrg(regForm.getHidnRegTxnId(), languageLocale));
								OwnerDetail = mapDto.getOwnerPartyDescForOrg();
								System.out.println("Owner result from DB is " + OwnerDetail);
								// String parts[] = OwnerDetail.split(",");
								// String Owner = parts[0];
								String PoADesc = mapDto.getOwnerPartyPoaDescForOrg();
								String OwnerRolePoA = OwnerDetail + PoaName + PoADesc;
								System.out.println("owner Role to display " + OwnerRolePoA);
								mapDto.setIndAuthPersn(OwnerRolePoA);
								regForm.setAbc("2");
							} else {
								mapDto.setIndAuthPersn(regForm.getAuthPerName().trim());
								regForm.setAbc("2");
							}
						}
						mapDto.setAuthPerGendarTrns(regForm.getAuthPerGendar());
						String gendr = "";
						if (regForm.getAuthPerGendar().equalsIgnoreCase("m")) {
							// mapDto.setSelectedGender("Male");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.MALE_ENGLISH;
							} else {
								gendr = RegInitConstant.MALE_HINDI;
							}
						} else if (regForm.getAuthPerGendar().equalsIgnoreCase("f")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.FEMALE_ENGLISH;
							} else {
								gendr = RegInitConstant.FEMALE_HINDI;
							}
							// mapDto.setSelectedGender("Female");
						} else {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.other_gender_ENGLISH;
							} else {
								gendr = RegInitConstant.other_gender_hindi;
							}
							// mapDto.setSelectedGender("Female");
						}
						mapDto.setSelectedGender(gendr);
						mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherName());
						mapDto.setRelationshipTrns(regForm.getAuthPerRelationship());
						mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationship()), languageLocale));
						mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddress().trim());
						mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountry());
						mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountry(), languageLocale));
						mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatename());
						mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatename(), languageLocale));
						mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrict());
						mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrict().trim(), languageLocale));
						if (regForm.getAddressOrgOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressOrgOutMp())) {
							mapDto.setAddressOrgOutMpTrns(regForm.getAddressOrgOutMp());
						} else {
							mapDto.setAddressOrgOutMpTrns("-");
						}
						if (regForm.getAddressAuthPerOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressAuthPerOutMp())) {
							mapDto.setAddressAuthPerOutMpTrns(regForm.getAddressAuthPerOutMp());
						} else {
							mapDto.setAddressAuthPerOutMpTrns("-");
						}
						// mapDto.setUserDOBTrns("-");
					} else if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
						// individual
						mapDto.setOgrNameTrns("-");
						mapDto.setFnameTrns(regForm.getFname());
						if (regForm.getMname().equalsIgnoreCase("") || regForm.getMname().equalsIgnoreCase("null"))
							mapDto.setMnameTrns("-");
						else
							mapDto.setMnameTrns(regForm.getMname());
						mapDto.setLnameTrns(regForm.getLname());
						mapDto.setGendarTrns(regForm.getGendar());
						// Guardain Mino - Rahul
						if (regForm.getGuardian().equalsIgnoreCase("") || regForm.getGuardian().equalsIgnoreCase("null"))
							mapDto.setGuardianTrans("-");
						else {
							mapDto.setGuardianTrans(regForm.getGuardian());
						}
						/*
						 * if(regForm.getGendar().equalsIgnoreCase("m"))
						 * mapDto.setSelectedGender("Male"); else
						 * mapDto.setSelectedGender("Female");
						 */
						String gendr = "";
						if (regForm.getGendar().equalsIgnoreCase("m")) {
							// mapDto.setSelectedGender("Male");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.MALE_ENGLISH;
							} else {
								gendr = RegInitConstant.MALE_HINDI;
							}
						} else if (regForm.getGendar().equalsIgnoreCase("f")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.FEMALE_ENGLISH;
							} else {
								gendr = RegInitConstant.FEMALE_HINDI;
							}
							// mapDto.setSelectedGender("Female");
						} else {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.other_gender_ENGLISH;
							} else {
								gendr = RegInitConstant.other_gender_hindi;
							}
							// mapDto.setSelectedGender("Female");
						}
						mapDto.setSelectedGender(gendr);
						if (regForm.getAge().equalsIgnoreCase("") || regForm.getAge().equalsIgnoreCase("null"))
							mapDto.setAgeTrns("-");
						else
							mapDto.setAgeTrns(regForm.getAge());
						mapDto.setFatherNameTrns(regForm.getFatherName());
						if (regForm.getMotherName().equalsIgnoreCase("") || regForm.getMotherName().equalsIgnoreCase("null"))
							mapDto.setMotherNameTrns("-");
						else
							mapDto.setMotherNameTrns(regForm.getMotherName());
						if (regForm.getSpouseName().equalsIgnoreCase("") || regForm.getSpouseName().equalsIgnoreCase("null"))
							mapDto.setSpouseNameTrns("-");
						else
							mapDto.setSpouseNameTrns(regForm.getSpouseName());
						mapDto.setNationalityTrns(regForm.getNationality());
						if (regForm.getPostalCode().equalsIgnoreCase("") || regForm.getPostalCode().equalsIgnoreCase("null"))
							mapDto.setPostalCodeTrns("-");
						else
							mapDto.setPostalCodeTrns(regForm.getPostalCode());
						if (regForm.getIndphno().equalsIgnoreCase("") || regForm.getIndphno().equalsIgnoreCase("null"))
							mapDto.setPhnoTrns("-");
						else
							mapDto.setPhnoTrns(regForm.getIndphno());
						if (regForm.getIndmobno().equalsIgnoreCase("") || regForm.getIndmobno().equalsIgnoreCase("null"))
							mapDto.setMobnoTrns("-");
						else
							mapDto.setMobnoTrns(regForm.getIndmobno());
						if (regForm.getEmailID().equalsIgnoreCase("") || regForm.getEmailID().equalsIgnoreCase("null"))
							mapDto.setEmailIDTrns("-");
						else
							mapDto.setEmailIDTrns(regForm.getEmailID());
						mapDto.setSelectedPhotoId(regForm.getListID());
						mapDto.setSelectedPhotoIdName(regForm.getListIDName());
						if (regForm.getIdno().equalsIgnoreCase("") || regForm.getIdno().equalsIgnoreCase("null"))
							mapDto.setIdnoTrns("-");
						else
							mapDto.setIdnoTrns(regForm.getIdno());
						// Added for aadhar integration -- start
						if (regForm.getAadharName() != null) {
							mapDto.setAadharName(regForm.getAadharName());
							mapDto.setAadharNameTrns(regForm.getAadharName()); // added
							// to
							// display
							// party
							// details
							// at
							// displayRegDetls
						}
						if (regForm.getListID().equalsIgnoreCase("7")) {
							mapDto.setPartyDisplayAadhar("1");
						} else {
							mapDto.setPartyDisplayAadhar("0");
						}
						// Added for aadhar integration -- end
						mapDto.setOgrNameTrns("-");
						mapDto.setAuthPerNameTrns("-");
						// set value for PoA or Authenticated PoA by Db from
						// initaiton- Rahul
						int partyAge = 0;
						if (regForm.getAge() != null && !"".equalsIgnoreCase(regForm.getAge())) {
							partyAge = Integer.parseInt(regForm.getAge());
						}
						if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
							mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
							// if(mapDto.getPoaHolderFlag()==RegInitConstant.POA_HOLDER_FLAG){
							// to fetch hindi english both name
							mapDto.setRoleNameForPOA(commonBo.getRoleNameForPOA(regForm.getPartyType().trim()));
							String RoleName = mapDto.getRoleNameForPOA();
							System.out.println("Role Name " + RoleName);
							String HindiEnglishRole[] = RoleName.split(",");
							String hindesc = HindiEnglishRole[0];
							String EngDesc = HindiEnglishRole[1];
							String PoaName = null;
							if (EngDesc.contains("Authenticated")) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(Authenticated PoA Holder) ";
								} else {
									PoaName = " ??????????????????(??????????????????????????????  ?????????????????????????????????) ";
								}
							} else {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									PoaName = " By(PoA Holder) ";
								} else {
									PoaName = " ?????????????????? (????????????????????????????????? ??????)";
								}
							}
							System.out.println("POA NAMe is " + PoaName);
							System.out.println("POA holder flag is " + mapDto.getPoaHolderFlag());
							String OwnerDetail = null;
							mapDto.setOwnerPartyDesc(commonBo.getOwnerPartyDesc(regForm.getHidnRegTxnId(), languageLocale));
							mapDto.setOwnerPoADesc(commonBo.getOwnerPartyPoaName(regForm.getHidnRegTxnId(), languageLocale));
							OwnerDetail = mapDto.getOwnerPartyDesc();
							System.out.println("Owner result from DB is " + OwnerDetail);
							String parts[] = OwnerDetail.split(",");
							// String Owner = parts[0];
							// String PoADesc =parts[1];
							// String OwnerRolePoA=parts[0]+PoaName+parts[1];
							String OwnerRolePoA = OwnerDetail + PoaName + mapDto.getOwnerPoADesc();
							System.out.println("owner Role to display " + OwnerRolePoA);
							mapDto.setIndAuthPersn(OwnerRolePoA);
							regForm.setAbc("2");
						}
						// added for Minor -Guardian and Middle name from
						// Initiation - Rahul
						else {
							if (regForm.getMname().equalsIgnoreCase("") || regForm.getMname().equalsIgnoreCase("null")) {
								mapDto.setIndAuthPersn(regForm.getFname() + " " + regForm.getLname());
							} else {
								mapDto.setIndAuthPersn(regForm.getFname() + " " + regForm.getMname() + " " + regForm.getLname());
							}
							String name = mapDto.getIndAuthPersn();
							if (partyAge < 18) {
								RegCommonBO bo = new RegCommonBO();
								String relations = bo.getRelationshipName(String.valueOf(regForm.getRelationship()), languageLocale);
								System.out.println("Relationship ---------" + String.valueOf(regForm.getRelationship()));
								// String name = mapDto.getIndAuthPersn();
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									name = "(Minor) " + name + " " + relations + " " + regForm.getFatherName() + " by " + regForm.getGuardian();
								} else {
									name = "(?????????????????????  ) " + name + " " + relations + " " + regForm.getFatherName() + " ??????????????????   " + regForm.getGuardian();
								}
								/*
								 * mapDto.setAgeAboveName(name);
								 * regForm.setAbc("1");
								 */
							}
							mapDto.setIndAuthPersn(name);
							regForm.setAbc("2");
							/*
							 * else { regForm.setAbc("2"); }
							 */
						}
						System.out.println("indAutyhpesrson value" + regForm.getAbc());
						// changes finish
						mapDto.setOrgaddressTrns(regForm.getIndaddress());
						mapDto.setSelectedCountry(regForm.getIndcountry());
						mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
						mapDto.setSelectedState(regForm.getIndstatename());
						mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
						mapDto.setSelectedDistrict(regForm.getInddistrict());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrict(), languageLocale));
						mapDto.setSelectedCategoryName(regForm.getSelectedCategoryName());
						mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationId(), languageLocale));
						mapDto.setIndCategoryTrns(regForm.getIndCategory());
						mapDto.setPartyTypeTrns(regForm.getPartyType());
						mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleArea());
						if (regForm.getIndScheduleArea() != null) {
							if (regForm.getIndScheduleArea().equalsIgnoreCase("Y")) {
								// mapDto.setIndScheduleAreaTrnsDisplay("Yes");
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
								} else {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
								}
							} else {
								// mapDto.setIndScheduleAreaTrnsDisplay("No");
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
								} else {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
								}
								mapDto.setPermissionNoTrns(regForm.getPermissionNo());
								mapDto.setDocumentNameTrns(regForm.getDocumentName());
								mapDto.setDocumentSizeTrns(regForm.getDocumentSize());
								mapDto.setFilePathTrns(regForm.getFilePath());
								mapDto.setDocContentsTrns(regForm.getDocContents());
							}
						}
						if (regForm.getIndDisability().equalsIgnoreCase("") || regForm.getIndDisability().equalsIgnoreCase("null")) {
							mapDto.setIndDisabilityTrns("-");
						} else if (regForm.getIndDisability().equalsIgnoreCase("Y")) {
							mapDto.setIndDisabilityIdTrns(regForm.getIndDisability());
							// mapDto.setIndDisabilityTrns("Yes");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
							} else {
								mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
							}
							if (regForm.getIndDisabilityDesc().equalsIgnoreCase("") || regForm.getIndDisabilityDesc().equalsIgnoreCase("null")) {
								mapDto.setIndDisabilityDescTrns("-");
							} else {
								mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDesc());
							}
						} else if (regForm.getIndDisability().equalsIgnoreCase("N")) {
							mapDto.setIndDisabilityIdTrns(regForm.getIndDisability());
							// mapDto.setIndDisabilityTrns("No");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
							} else {
								mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
							}
						}
						mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
						// mapDto.setUserDOBTrns(UserRegistrationBD.getOracleDate(regForm.getUserDay(),
						// regForm.getUserMonth(), regForm.getUserYear()));
						if (regForm.getIndMinority().equalsIgnoreCase("") || regForm.getIndMinority().equalsIgnoreCase("null")) {
							mapDto.setIndMinorityTrns("-");
						} else if (regForm.getIndMinority().equalsIgnoreCase("Y")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
							} else {
								mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
							}
						} else if (regForm.getIndMinority().equalsIgnoreCase("N")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
							} else {
								mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
							}
							// mapDto.setIndMinorityTrns("No");
						}
						if (regForm.getIndPovertyLine().equalsIgnoreCase("") || regForm.getIndPovertyLine().equalsIgnoreCase("null")) {
							mapDto.setIndPovertyLineTrns("-");
						} else if (regForm.getIndPovertyLine().equalsIgnoreCase("Y")) {
							// mapDto.setIndPovertyLineTrns("Yes");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
							} else {
								mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
							}
						} else if (regForm.getIndPovertyLine().equalsIgnoreCase("N")) {
							// mapDto.setIndPovertyLineTrns("No");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
							} else {
								mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
							}
						}
						mapDto.setRelationshipTrns(regForm.getRelationship());
						mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationship()), languageLocale));
						if (regForm.getAddressIndOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressIndOutMp())) {
							mapDto.setAddressIndOutMpTrns(regForm.getAddressIndOutMp());
						} else {
							mapDto.setAddressIndOutMpTrns("-");
						}
					}
					if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						mapDto.setTrnsOwnerList(regForm.getAppOwnerList());
						regForm.setDeleteOwnerID("");
						regForm.setHdnDeleteOwnerID("");
						/*
						 * mapDto.setOwnerNameTrns(regForm.getOwnerName());
						 * if(regForm.getOwnerOgrName().equalsIgnoreCase("") ||
						 * regForm.getOwnerOgrName().equalsIgnoreCase("null"))
						 * mapDto.setOwnerOgrNameTrns("-"); else
						 * mapDto.setOwnerOgrNameTrns
						 * (regForm.getOwnerOgrName());
						 * 
						 * if(regForm.getOwnerGendar().equalsIgnoreCase("M"))
						 * mapDto.setOwnerGendarTrns("Male"); else
						 * mapDto.setOwnerGendarTrns("Female"); String gendr="";
						 * 
						 * if(regForm.getOwnerGendar().equalsIgnoreCase("m")){
						 * if(languageLocale.equalsIgnoreCase(RegInitConstant.
						 * LANGUAGE_ENGLISH)){
						 * gendr=RegInitConstant.MALE_ENGLISH; }else{
						 * gendr=RegInitConstant.MALE_HINDI; } } else{
						 * 
						 * if(languageLocale.equalsIgnoreCase(RegInitConstant.
						 * LANGUAGE_ENGLISH)){
						 * gendr=RegInitConstant.FEMALE_ENGLISH; }else{
						 * gendr=RegInitConstant.FEMALE_HINDI; }
						 * 
						 * } mapDto.setOwnerGendarTrns(gendr);
						 * mapDto.setOwnerNationalityTrns
						 * (regForm.getOwnerNationality());
						 * mapDto.setOwnerAddressTrns
						 * (regForm.getOwnerAddress());
						 * mapDto.setOwnerPhnoTrns(regForm.getOwnerPhno());
						 * if(regForm.getOwnerEmailID().equalsIgnoreCase("") ||
						 * regForm.getOwnerEmailID().equalsIgnoreCase("null"))
						 * mapDto.setOwnerEmailIDTrns("-"); else
						 * mapDto.setOwnerEmailIDTrns
						 * (regForm.getOwnerEmailID());
						 * 
						 * 
						 * if(regForm.getOwnerAge().equalsIgnoreCase("") ||
						 * regForm.getOwnerAge().equalsIgnoreCase("null"))
						 * mapDto.setOwnerAgeTrns("-"); else
						 * mapDto.setOwnerAgeTrns(regForm.getOwnerAge());
						 * //mapDto.setOwnerAgeTrns("-");
						 * //mapDto.setOwnerDOBTrns
						 * (UserRegistrationBD.getOracleDate
						 * (regForm.getOwnerDay(), regForm.getOwnerMonth(),
						 * regForm.getOwnerYear()));
						 * mapDto.setOwnerIdnoTrns(regForm
						 * .getOwnerIdno().equalsIgnoreCase
						 * ("")?"-":regForm.getOwnerIdno());
						 * mapDto.setOwnerListIDTrns(regForm.getOwnerListID());
						 * mapDto
						 * .setOwnerProofNameTrns(regForm.getOwnerListID().
						 * equalsIgnoreCase
						 * ("")?"-":regForm.getOwnerProofName());
						 * 
						 * if(regForm.getAddressOwnerOutMp()!=null &&
						 * !("").equalsIgnoreCase
						 * (regForm.getAddressOwnerOutMp())){
						 * mapDto.setAddressOwnerOutMpTrns
						 * (regForm.getAddressOwnerOutMp()); }else{
						 * mapDto.setAddressOwnerOutMpTrns("-"); }
						 */
					}
					map = regForm.getMapTransactingParties();
					int count = regForm.getMapKeyCount();
					if (count == 0)
						count = 1;
					// else
					// count++;
					if (map.containsKey(Integer.toString(count))) {
						// keyCount=keyCount+1;
						// key=key+keyCount;
						count++;
						map.put(Integer.toString(count), mapDto);
					} else
						map.put(Integer.toString(count), mapDto);
					regForm.setMapKeyCount(count);
					regForm.setAddMoreCounter(count);
					// key="key";
					regForm.setMapBuildingDetails(map);
					// String
					// applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
					// regForm.setApplicantRoleId2(Integer.parseInt(applicantRoleId));
					/*
					 * if(regForm.getHdnDeclareShareCheck().equalsIgnoreCase("Y")
					 * ) {
					 * 
					 * inttotalShare=commonBo.getShareOfPropList(regForm.
					 * getHidnRegTxnId());
					 * //logger.debug("applicant role id---------->"
					 * +applicantRoleId);
					 * //logger.debug("total share---------->"+totalShare);
					 * regForm.setTotalShareOfProp(totalShare);
					 * 
					 * 
					 * 
					 * if(regForm.getDeedID()==RegInitConstant.DEED_TRUST_PV){
					 * 
					 * if(totalShare==100){ //redirect to property screen
					 * request.setAttribute("propAction", "propAction");
					 * request.setAttribute("regInitForm", regForm); forward
					 * ="reginitProperty"; return mapping.findForward(forward);
					 * }
					 * 
					 * } else { //following if condition for disabling applicant
					 * role once total sum of share is 100%.
					 * if(regForm.getDeedID()==RegInitConstant.DEED_POA_P){
					 * 
					 * if(Integer.parseInt(applicantRoleId)==RegInitConstant.
					 * ROLE_POA_ACCEPTER){ regForm.setIsPoaAddedFlag(1);
					 * regForm.
					 * setApplicantRoleId(Integer.parseInt(applicantRoleId));
					 * 
					 * }else if(totalShare==100){
					 * regForm.setApplicantRoleId(Integer
					 * .parseInt(applicantRoleId)); }
					 * 
					 * }else if(totalShare==100){
					 * regForm.setApplicantRoleId(Integer
					 * .parseInt(applicantRoleId)); }
					 * 
					 * }
					 * 
					 * }else{ //IF SHARES ARE NOT DECLARED BY USER
					 * 
					 * if(regForm.getDeedID()==RegInitConstant.DEED_POA_P){
					 * 
					 * if(Integer.parseInt(applicantRoleId)==RegInitConstant.
					 * ROLE_POA_ACCEPTER){ regForm.setIsPoaAddedFlag(1);
					 * 
					 * }
					 * 
					 * } if(regForm.getDeedID()==RegInitConstant.DEED_TRUST_PV){
					 * 
					 * //CHECK FOR REDIRECTION ON THE BASIS OF RADIO BUTTON
					 * SELECTED.
					 * if(regForm.getAddMoreTransParty().equalsIgnoreCase("N")){
					 * request.setAttribute("propAction", "propAction");
					 * request.setAttribute("regInitForm", regForm); forward
					 * ="reginitProperty"; return mapping.findForward(forward);
					 * }
					 * 
					 * }
					 * 
					 * }
					 */
					if (regForm.getInstID() == RegInitConstant.INST_TRUST_DISSOLUTION) {
						// CHECK FOR REDIRECTION ON THE BASIS OF RADIO BUTTON
						// SELECTED.
						if (regForm.getAddMoreTransParty().equalsIgnoreCase("N")) {
							actionName = RegInitConstant.NO_ACTION;
							regForm.setActionName(RegInitConstant.NO_ACTION);
							request.setAttribute("propAction", "propAction");
							request.setAttribute("regInitForm", regForm);
							forward = "reginitProperty";
							return mapping.findForward(forward);
						} else {
							forward = "transactingParty";
						}
					} else {
						forward = "transactingParty";
					}
				} else {
					forward = "failure";
					regForm.setHidnRegTxnId(null);
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", regForm.getInstID());
					request.setAttribute("roleId", regForm.getPartyType());
					return mapping.findForward(forward);
				}
				actionName = RegInitConstant.NO_ACTION;
				regForm.setActionName(RegInitConstant.NO_ACTION);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				request.setAttribute("roleId", regForm.getPartyType());
				request.setAttribute("roleIdTrns", 0);
				regForm.setDeedId(request.getAttribute("deedId").toString());
				// resetToken(request);
				// }
				regForm.setCountryTrns("1");
				regForm.setCountryNameTrns(commonBo.getCountryName("1", languageLocale));
				regForm.setStatenameTrns("1");
				regForm.setStatenameNameTrns(commonBo.getStateName("1", languageLocale));
				regForm.setIndcountryTrns("1");
				regForm.setIndcountryNameTrns(commonBo.getCountryName("1", languageLocale));
				regForm.setIndstatenameTrns("1");
				regForm.setIndstatenameNameTrns(commonBo.getStateName("1", languageLocale));
				regForm.setOwnerIndcountryTrns("1");
				regForm.setOwnerIndstatenameTrns("1");
				regForm.setClaimantFlag(0);
				regForm.setPoaHolderFlag(0);
				// Added for aadhar integration -- start
				regForm.setAadharCheck("");
				regForm.setAadharName("");
				regForm.setAadharRespCode("");
				regForm.setAadharNameTrns("");
				regForm.setAadharErrMsg("");
				// Added for aadhar integration -- end
				regForm.setOwnerPanNumberTrns("");
				regForm.setOwnerIndphnoTrns("");
				regForm.setOwnerPostalCodeTrns("");
				regForm.setOwnerIndDisabilityTrns("N");
				regForm.setOwnerInddistrictTrns("");
				regForm.setOwnerIndCategoryTrns("");
				regForm.setOwnerIndDisabilityDescTrns("");
				regForm.setOwnerIndMinorityTrns("N");
				regForm.setOwnerIndPovertyLineTrns("N");
				regForm.setOwnerOccupationIdTrns("");
				regForm.setOwnerRelationshipTrns("");
				regForm.setOwnerFatherNameTrns("");
				regForm.setOwnerMotherNameTrns("");
				regForm.setOwnerSpouseNameTrns("");
				regForm.setOwnerPermissionNoTrns("");
				regForm.setOwnerIndScheduleAreaTrns("N");
				// regForm.setOwnerGendarTrns("M");
				commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendarTrns(), languageLocale));
			}
			if (RegInitConstant.RESET_APPLICANT_ACTION.equals(actionName)) {
				String partyType = regForm.getListAdoptedParty();
				// session.setAttribute("partyType", partyType);
				// resetToken(request);
				commonDto.setInstrument(new ArrayList());
				commonDto.setExemption(new ArrayList());
				regForm.setDeleteOwnerID("");
				regForm.setHdnDeleteOwnerID("");
				// regForm.setOwnerGendarVal("");
				// regForm.setOwnerModifyFlag(0);
				regForm.setAppOwnerList(new HashMap());
				// regForm.setTrnsOwnerList(new HashMap());
				regForm.setRent(0);
				regForm.setAdvance(0);
				regForm.setDevlpmtCharge(0);
				regForm.setOtherRecCharge(0);
				regForm.setPremium(0);
				regForm.setTermLease(0);
				regForm.setTrustName("");
				regForm.setTrustDate("");
				regForm.setWithPos("");
				regForm.setSecLoanAmt(0);
				regForm.setFnameArb("");
				regForm.setMnameArb("");
				regForm.setLnameArb("");
				regForm.setGendarArb("M");
				regForm.setAgeArb("");
				regForm.setFatherNameArb("");
				regForm.setMotherNameArb("");
				regForm.setSpouseNameArb("");
				regForm.setNationalityArb("");
				regForm.setIndaddressArb("");
				regForm.setInddistrictArb("");
				regForm.setEmailIDArb("");
				regForm.setIndCategoryArb("");
				regForm.setIndDisabilityArb("N");
				regForm.setIndDisabilityDescArb("");
				regForm.setListIDArb("");
				regForm.setIdnoArb("");
				regForm.setAdvancePaymntDetails("");
				regForm.setPossGiven("N");
				regForm.setDissolutionDate("");
				regForm.setRetirmentDate("");
				regForm.setPoaPeriod(0);
				regForm.setNameOfOfficial("");
				regForm.setDesgOfOfficial("");
				regForm.setAddressOfOfficial("");
				regForm.setAddressGovtOffclOutMp("");
				regForm.setAddressAuthPerOutMp("");
				regForm.setAddressIndOutMp("");
				regForm.setAddressOrgOutMp("");
				regForm.setAddressOwnerOutMp("");
				regForm.setFname("");
				regForm.setMname("");
				regForm.setLname("");
				regForm.setGendar("M");
				// regForm.setAuthPerGendar("M");
				regForm.setAge("");
				regForm.setFatherName("");
				regForm.setMotherName("");
				regForm.setSpouseName("");
				regForm.setNationality("");
				regForm.setIndaddress("");
				regForm.setIndcountry("");
				regForm.setIndstatename("");
				regForm.setInddistrict("");
				regForm.setPostalCode("");
				regForm.setIndphno("");
				regForm.setIndmobno("");
				regForm.setEmailID("");
				regForm.setListID("");
				regForm.setIdno("");
				regForm.setDeedType("");
				regForm.setIndCaste("");
				regForm.setIndReligion("");
				regForm.setIndDisability("N");
				// regForm.setShareOfProp("");
				regForm.setOwnershipShare("");
				regForm.setRelationWithOwner("");
				regForm.setIndDisabilityDesc("");
				regForm.setIndMinority("N");
				regForm.setIndPovertyLine("N");
				regForm.setIndCategory("");
				regForm.setIndScheduleArea("");
				regForm.setPermissionNo("");
				regForm.setCertificate(null);
				regForm.setDocumentName("");
				regForm.setDocumentSize("");
				regForm.setFilePath("");
				regForm.setOwnerGendar("M");
				regForm.setOccupationId("");
				// changed by akansha for owner
				regForm.setOwnerIndphno("");
				regForm.setOwnerPostalCode("");
				regForm.setOwnerIndDisability("N");
				regForm.setOwnerIndDisabilityDesc("");
				regForm.setOwnerIndMinority("N");
				regForm.setOwnerIndPovertyLine("N");
				regForm.setOwnerOccupationId("");
				regForm.setOwnerOccupationIdName("");
				regForm.setOwnerFatherName("");
				regForm.setOwnerMotherName("");
				regForm.setOwnerSpouseName("");
				regForm.setOwnerRelationship("");
				regForm.setOwnerRelationshipName("");
				regForm.setOwnerInddistrictName("");
				regForm.setOwnerIndScheduleArea("N");
				regForm.setOwnerPermissionNo("");
				regForm.setOwnerCertificate("");
				regForm.setOwnerPanNumber("");
				regForm.setOwnerInddistrict("");
				regForm.setOwnerIndCategory("");
				regForm.setOwnerIndCategoryName("");
				// regForm.setIndPovertyLineTrns("");
				regForm.setInstrument("");
				commonDto.setState(commonBo.getState("1", languageLocale));
				commonDto.setDistrict(commonBo.getDistrict("1", languageLocale));
				commonDto.setIndstate(commonBo.getState("1", languageLocale));
				commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));
				// commonDto.setAppType(commonBo.getAppType(languageLocale));
				commonDto.setCountry(commonBo.getCountry(languageLocale));
				commonDto.setIndcountry(commonBo.getCountry(languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setDeedType(commonBo.getDeedType(languageLocale));
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
				commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(), languageLocale));
				commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendar(), languageLocale));
				regForm.setRelationship(0);
				// commonDto.setDistrict(commonBo.getDistrict("1"));
				regForm.setOgrName("");
				regForm.setAuthPerName("");
				regForm.setOrgaddress("");
				regForm.setCountry("");
				regForm.setStatename("");
				regForm.setDistrict("");
				regForm.setPhno("");
				regForm.setMobno("");
				regForm.setApplicantUserIdCheck(null);
				regForm.setActionName(RegInitConstant.NO_ACTION);
				// following reseting owner details
				regForm.setOwnerName("");
				regForm.setOwnerOgrName("");
				regForm.setOwnerAddress("");
				regForm.setOwnerAge("");
				regForm.setOwnerEmailID("");
				regForm.setOwnerGendar("M");
				regForm.setOwnerIdno("");
				regForm.setOwnerListID("");
				regForm.setOwnerNationality("");
				regForm.setOwnerPhno("");
				regForm.setOwnerProofName("");
				// session.setAttribute("commonDto", commonDto);
				regForm.setCommonDto(commonDto);
				// session.setAttribute("regForm", regForm);
				regForm.setCountry("1");
				regForm.setCountryName("INDIA");
				regForm.setStatename("1");
				regForm.setStatenameName("MADHYA PRADESH");
				regForm.setIndcountry("1");
				regForm.setIndcountryName("INDIA");
				regForm.setOwnerIndcountry("1");
				regForm.setOwnerIndstatename("1");
				regForm.setOwnerIndcountryTrns("1");
				regForm.setOwnerIndstatenameTrns("1");
				regForm.setIndstatename("1");
				regForm.setIndstatenameName("MADHYA PRADESH");
				regForm.setIndcountryArb("1");
				regForm.setIndstatenameArb("1");
				regForm.setUserDay("");
				regForm.setUserMonth("");
				regForm.setUserYear("");
				regForm.setUserDOB("");
				// regForm.setDeclareShare("true");
				regForm.setHdnDeclareShareCheck("Y");
				regForm.setRent(0);
				regForm.setAdvance(0);
				regForm.setDevlpmtCharge(0);
				regForm.setOtherRecCharge(0);
				regForm.setPremium(0);
				regForm.setTermLease(0);
				regForm.setTrustName("");
				regForm.setTrustDate("");
				regForm.setWithPos("");
				regForm.setSecLoanAmt(0);
				regForm.setIndcountryArb("1");
				regForm.setIndstatenameArb("1");
				regForm.setInddistrictArb("");
				regForm.setFnameArb("");
				regForm.setMnameArb("");
				regForm.setLnameArb("");
				regForm.setGendarArb("M");
				regForm.setAgeArb("");
				regForm.setFatherNameArb("");
				regForm.setMotherNameArb("");
				regForm.setSpouseNameArb("");
				regForm.setNationalityArb("");
				regForm.setIndaddressArb("");
				regForm.setIndcountryArb("");
				regForm.setIndstatenameArb("");
				regForm.setInddistrictArb("");
				regForm.setIndphnoArb("");
				regForm.setIndmobnoArb("");
				regForm.setEmailIDArb("");
				regForm.setIndCategoryArb("");
				regForm.setIndDisabilityArb("N");
				regForm.setIndDisabilityDescArb("");
				regForm.setListIDArb("");
				regForm.setIdnoArb("");
				regForm.setAdvance(0);
				regForm.setAdvancePaymntDate("");
				regForm.setPossGiven("N");
				regForm.setPossGivenName("");
				regForm.setPoaWithConsid("");
				regForm.setPoaPeriod(0);
				regForm.setDissolutionDate("");
				regForm.setRetirmentDate("");
				regForm.setCertificate(null);
				regForm.setDocumentName("");
				regForm.setDocumentSize("");
				regForm.setFilePath("");
				regForm.setDocContents(new byte[0]);
				regForm.setPartyOrProp("");
				regForm.setUploadType("");
				regForm.setU2(null);
				regForm.setU2DocumentName("");
				regForm.setU2DocumentSize("");
				regForm.setU2FilePath("");
				regForm.setU2DocContents(new byte[0]);
				regForm.setU2PartyOrProp("");
				regForm.setU2UploadType("");
				regForm.setU3(null);
				regForm.setU3DocumentName("");
				regForm.setU3DocumentSize("");
				regForm.setU3FilePath("");
				regForm.setU3DocContents(new byte[0]);
				regForm.setU3PartyOrProp("");
				regForm.setU3UploadType("");
				regForm.setU4(null);
				regForm.setU4DocumentName("");
				regForm.setU4DocumentSize("");
				regForm.setU4FilePath("");
				regForm.setU4DocContents(new byte[0]);
				regForm.setU4PartyOrProp("");
				regForm.setU4UploadType("");
				regForm.setU5(null);
				regForm.setU5DocumentName("");
				regForm.setU5DocumentSize("");
				regForm.setU5FilePath("");
				regForm.setU5DocContents(new byte[0]);
				regForm.setU5PartyOrProp("");
				regForm.setU5UploadType("");
				regForm.setU6(null);
				regForm.setU6DocumentName("");
				regForm.setU6DocumentSize("");
				regForm.setU6FilePath("");
				regForm.setU6DocContents(new byte[0]);
				regForm.setU6PartyOrProp("");
				regForm.setU6UploadType("");
				regForm.setU7(null);
				regForm.setU7DocumentName("");
				regForm.setU7DocumentSize("");
				regForm.setU7FilePath("");
				regForm.setU7DocContents(new byte[0]);
				regForm.setU7PartyOrProp("");
				regForm.setU7UploadType("");
				regForm.setU8(null);
				regForm.setU8DocumentName("");
				regForm.setU8DocumentSize("");
				regForm.setU8FilePath("");
				regForm.setU8DocContents(new byte[0]);
				regForm.setU8PartyOrProp("");
				regForm.setU8UploadType("");
				regForm.setU9(null);
				regForm.setU9DocumentName("");
				regForm.setU9DocumentSize("");
				regForm.setU9FilePath("");
				regForm.setU9DocContents(new byte[0]);
				regForm.setU9PartyOrProp("");
				regForm.setU9UploadType("");
				regForm.setU10(null);
				regForm.setU10DocumentName("");
				regForm.setU10DocumentSize("");
				regForm.setU10FilePath("");
				regForm.setU10DocContents(new byte[0]);
				regForm.setU10PartyOrProp("");
				regForm.setU10UploadType("");
				regForm.setU11(null);
				regForm.setU11DocumentName("");
				regForm.setU11DocumentSize("");
				regForm.setU11FilePath("");
				regForm.setU11DocContents(new byte[0]);
				regForm.setU11PartyOrProp("");
				regForm.setU11UploadType("");
				regForm.setSrOfficeName("");
				regForm.setPoaRegNo("");
				regForm.setDatePoaReg("");
				regForm.setAuthPerAddress("");
				regForm.setAuthPerCountry("1");
				regForm.setAuthPerDistrict("");
				regForm.setAuthPerStatename("1");
				regForm.setAuthPerFatherName("");
				regForm.setAuthPerRelationship(0);
				regForm.setAuthPerGendar("M");
				// Added for addhar integration by Ankit - Start
				regForm.setAadharCheck("");
				regForm.setAadharErrMsg("");
				regForm.setAadharName("");
				regForm.setAadharNameTrns("");
				regForm.setAadharRespCode("");
				regForm.setAadharCheckOwner("");
				regForm.setAadharErrMsgOwner("");
				regForm.setAadharNameOwner("");
				regForm.setAadharNameTrnsOwner("");
				regForm.setTransactionId("");
				regForm.setAcknowledgementId("");
				regForm.setAadharDto(null);
				regForm.setAadharRespDto(null);
				regForm.setGovernmentOfficeName("");
				regForm.setGovernmentOfficeNameTrans("");
				// Added for addhar integration by Ankit - Start
				forward = "success";
			}
			if (RegInitConstant.APPLICANT_USERID_CHECK_ACTION.equalsIgnoreCase(actionName)) {
				regForm.setActionName(RegInitConstant.NO_ACTION);
				if (regForm.getHdnapplicantUserIdCheck().equalsIgnoreCase("checked")) {
					commonBo.getApplicantRegistrationDetls(session.getAttribute("UserId").toString(), regForm, languageLocale, commonDto);
					/*
					 * regForm.setFname(appRegDetls[0].trim());
					 * if(appRegDetls[1].trim().equalsIgnoreCase("null")){
					 * regForm.setMname(""); }else{
					 * regForm.setMname(appRegDetls[1].trim()); }
					 * regForm.setLname(appRegDetls[2].trim());
					 * regForm.setGendar(appRegDetls[3].trim());
					 * commonDto.setRelationshipList
					 * (commonBo.getRelationshipList
					 * (regForm.getGendar(),languageLocale));
					 * if(appRegDetls[4].trim().equalsIgnoreCase("null")){
					 * regForm.setNationality(""); }else{
					 * regForm.setNationality(appRegDetls[4].trim()); }
					 */
					// regForm.setIndcountry("1");
					commonDto.setIndcountry(commonBo.getCountry(languageLocale));
					commonDto.setIndstate(commonBo.getState("1", languageLocale));
					commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));
					// added by ankit for aadhar integration - start
					if ("7".equalsIgnoreCase(regForm.getListID()) && !("Y".equalsIgnoreCase(regForm.getClrFlg()))) {
						regForm.setAadharCheck("true");
						regForm.setAadharName("");
					} else {
						regForm.setAadharCheck("");
						regForm.setAadharName("");
						regForm.setAadharErrMsg("");
					}
					// added by ankit for aadhar integration - end
					regForm.setCommonDto(commonDto);
					// regForm.setIndstatename(appRegDetls[6].trim());
					/*
					 * if(appRegDetls[6].trim().equalsIgnoreCase("1")){ //if
					 * state is MP
					 * regForm.setInddistrict(appRegDetls[7].trim());
					 * regForm.setInddistrictNameTrns
					 * (commonBo.getDistrictName(appRegDetls
					 * [7].trim(),languageLocale)); }else{
					 * regForm.setInddistrict("");
					 * regForm.setInddistrictNameTrns(""); }
					 * regForm.setIndaddress(appRegDetls[8].trim());
					 * if(appRegDetls[9].trim().equalsIgnoreCase("null")){
					 * regForm.setPostalCode(""); }else{
					 * regForm.setPostalCode(appRegDetls[9].trim()); }
					 * 
					 * if(appRegDetls[10].trim().equalsIgnoreCase("null")){
					 * regForm.setIndphno(""); }else{
					 * regForm.setIndphno(appRegDetls[10].trim()); }
					 * 
					 * //regForm.setIndphno(appRegDetls[10].trim());
					 * regForm.setIndmobno(appRegDetls[11].trim());
					 * if(appRegDetls[12]!=null &&
					 * !appRegDetls[12].trim().equalsIgnoreCase("") &&
					 * !appRegDetls[12].trim().equalsIgnoreCase("null")){
					 * regForm.setEmailID(appRegDetls[12].trim()); } else{
					 * regForm.setEmailID(""); }
					 * //regForm.setListID(appRegDetls[13].trim());
					 * if(appRegDetls[13].trim().equalsIgnoreCase("null")){
					 * regForm.setListID(""); regForm.setListIDName(""); }else{
					 * regForm.setListID(appRegDetls[13].trim());
					 * regForm.setListIDName
					 * (commonBo.getPhotoIdProofName(appRegDetls
					 * [13].trim(),languageLocale)); }
					 */
					/*
					 * if(appRegDetls[14].trim().equalsIgnoreCase("null")){
					 * regForm.setIdno(""); }else{
					 * regForm.setIdno(appRegDetls[14].trim()); }
					 * regForm.setFatherName(appRegDetls[15].trim());
					 * if(appRegDetls[16].trim().equalsIgnoreCase("null")){
					 * regForm.setMotherName(""); }else{
					 * regForm.setMotherName(appRegDetls[16].trim()); }
					 * if(appRegDetls[17].trim().equalsIgnoreCase("null")){
					 * regForm.setSpouseName(""); }else{
					 * regForm.setSpouseName(appRegDetls[17].trim()); }
					 * 
					 * if(appRegDetls[18].trim().equalsIgnoreCase("null")){
					 * regForm.setAge(""); }else{
					 * regForm.setAge(appRegDetls[18].trim()); }
					 */
					// String dob=appRegDetls[18].trim();
					/*
					 * String[]
					 * finalDate=commonBo.parseStringDatefromDB(appRegDetls
					 * [18].trim()); regForm.setUserDay(finalDate[0]);
					 * regForm.setUserMonth(finalDate[1]);
					 * regForm.setUserYear(finalDate[2]);
					 */
					/*
					 * regForm.setIndCategory(appRegDetls[19].trim());
					 * regForm.setSelectedCategoryName
					 * (commonBo.getCategoryName(appRegDetls
					 * [19].trim(),languageLocale));
					 * regForm.setOccupationId(appRegDetls[20].trim());
					 * regForm.setSelectedOccupationName
					 * (commonBo.getOccupationName
					 * (appRegDetls[20].trim(),languageLocale));
					 */
					/*
					 * if(appRegDetls[19].trim().equalsIgnoreCase("null")){
					 * regForm.setIndCategory("");
					 * regForm.setSelectedCategoryName(""); }else{
					 * regForm.setIndCategory(appRegDetls[19].trim());
					 * regForm.setSelectedCategoryName
					 * (commonBo.getCategoryName(appRegDetls
					 * [19].trim(),languageLocale)); }
					 * 
					 * 
					 * if(appRegDetls[20].trim().equalsIgnoreCase("null")){
					 * regForm.setOccupationId("");
					 * regForm.setSelectedOccupationName(""); }else{
					 * regForm.setOccupationId(appRegDetls[20].trim());
					 * regForm.setSelectedOccupationName
					 * (commonBo.getOccupationName
					 * (appRegDetls[20].trim(),languageLocale)); }
					 */
					// occupation
					// regForm.setIndcountryName(commonBo.getCountryName(appRegDetls[5].trim()));
					// regForm.setIndstatenameName(commonBo.getStateName(appRegDetls[6].trim()));
					// regForm.setInddistrictName(commonBo.getDistrictName(appRegDetls[7].trim()));
					// regForm.setListIDName(commonBo.getPhotoIdProofName(appRegDetls[13].trim(),languageLocale));
					// regForm.setCountry("INDIA");
					// regForm.setCountryName("INDIA");
					// regForm.setStatename("MP");
					// regForm.setStatenameName("MADHYA PRADESH");
					/*
					 * regForm.setIndcountry("1");
					 * regForm.setIndcountryName(commonBo.getCountryName("1",
					 * languageLocale)); regForm.setIndstatename("1");
					 * regForm.setIndstatenameName(commonBo.getStateName("1",
					 * languageLocale));
					 */
				} else {
					regForm.setApplicantUserIdCheck(null);
					regForm.setFname("");
					regForm.setMname("");
					regForm.setLname("");
					regForm.setGendar("M");
					commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
					regForm.setNationality("");
					// regForm.setIndcountry("");
					// regForm.setIndstatename("");
					regForm.setInddistrict("");
					regForm.setIndaddress("");
					regForm.setPostalCode("");
					regForm.setIndphno("");
					regForm.setIndmobno("");
					regForm.setEmailID("");
					regForm.setListID("");
					regForm.setIdno("");
					regForm.setFatherName("");
					regForm.setMotherName("");
					regForm.setSpouseName("");
					regForm.setAge("");
					// commonDto.setIndstate(new ArrayList());
					// commonDto.setInddistrict(new ArrayList());
					// commonDto.setIndcountry(commonBo.getCountry());
					commonDto.setIdProf(commonBo.getIdProf(languageLocale));
					regForm.setCommonDto(commonDto);
					// regForm.setIndcountry("INDIA");
					// regForm.setIndcountryName("");
					regForm.setIndstatename("1");
					regForm.setIndstatenameName(commonBo.getStateName("1", languageLocale));
					regForm.setIndcountry("1");
					regForm.setIndcountryName(commonBo.getCountryName("1", languageLocale));
					regForm.setUserDay("");
					regForm.setUserMonth("");
					regForm.setUserYear("");
					regForm.setIndCategory("");
					regForm.setOccupationId("");
					// regForm.setIndstatename("MP");
					// regForm.setIndstatenameName("MADHYA PRADESH");
					regForm.setAadharCheck(""); // added by ankit for Aadhar
												// Integration
					regForm.setAadharErrMsg(""); // added by ankit for Aadhar
													// Integration
					regForm.setAadharName(""); // added by ankit for Aadhar
												// Integration
					regForm.setAcknowledgementId("");// added by ankit for
														// Aadhar Integration
					regForm.setTransactionId("");// added by ankit for Aadhar
													// Integration
				}
				// session.setAttribute("regForm", regForm);
				forward = "success";
			}
		}
		/*
		 * if (request.getParameter("mapping") != null &&
		 * request.getParameter("mapping").equalsIgnoreCase("Y")) {
		 * 
		 * String mapCheck; if (request.getAttribute("mapCheck") != null) {
		 * mapCheck = (String) request.getAttribute("mapCheck"); } else {
		 * mapCheck = ""; }
		 * 
		 * int count = commonBo.getPropertyCount(regForm.getHidnRegTxnId()); int
		 * partyCount = commonBo.getPartyCount(regForm.getHidnRegTxnId());
		 * 
		 * 
		 * //for Clr : Start
		 * 
		 * 
		 * int clrCount =
		 * commonBo.getPropertyCountCLR(regForm.getHidnRegTxnId());
		 * 
		 * if (clrCount>0){
		 * 
		 * boolean insertedFlag =
		 * commonBo.getClrPropertyAndInsert(regForm.getHidnRegTxnId(),userId); }
		 * 
		 * 
		 * //for Clr : End
		 * 
		 * boolean mapInsert = false;
		 * 
		 * if ((mapCheck != null && mapCheck.equalsIgnoreCase("ON")) ||
		 * (partyCount == 1 && count > 1)) {
		 * 
		 * mapInsert = commonBo.getAllPartiesProperties(regForm);
		 * 
		 * logger.debug("mapping insertion status : " + mapInsert);
		 * 
		 * }
		 * 
		 * // CHANGE HERE FOR DISSOLUTION OF TRUST if (count == 1 || partyCount
		 * == 1 || regForm.getDeedID() == RegInitConstant.DEED_EXCHANGE ||
		 * regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV || (count >
		 * 1 && mapCheck.equalsIgnoreCase("ON") && mapInsert)) {
		 * 
		 * // hashmap already containing mappings
		 * 
		 * //
		 * regForm.setPropWithMapping(commonBo.getAllPartiesPropertiesAlreadyMap
		 * (regForm,languageLocale)); // regForm.setPropWithoutMapping(null); //
		 * forward="alreadyMapped"; //
		 * 
		 * if(count==1){ regForm.setIsPropertyRemainingMap(1);
		 * 
		 * } regForm.setPropWithMapping(commonBo
		 * .getAllPartiesPropertiesAlreadyMap(regForm, languageLocale));
		 * regForm.setPropWithoutMapping(null); regForm
		 * .setAppStatus(RegInitConstant.STATUS_BACKED_MAPPING_SAVED);
		 * 
		 * 
		 * appStatus.equalsIgnoreCase(RegInitConstant.STATUS_PROP_SAVED)
		 * ||appStatus.equalsIgnoreCase(RegInitConstant.
		 * STATUS_BACKED_MAPPING_SAVED)
		 * 
		 * regForm .setDeclareShareSize(regForm.getPropWithMapping() .size());
		 * // Set set=regForm.getPropWithMapping().keySet(); // Iterator
		 * itr=set.iterator(); // ArrayList //
		 * lis=(ArrayList)regForm.getPropWithMapping().get(itr.next()); //
		 * if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE){ //
		 * regForm.setShareOfPropSize(3); // }else{ //
		 * regForm.setShareOfPropSize
		 * ((regForm.getPropWithMapping().size())*(lis.size())); // }
		 * 
		 * forward = "alreadyMapped"; regForm.setCheckParty("true");
		 * 
		 * return mapping.findForward(forward);
		 * 
		 * } else {
		 * 
		 * regForm.setPropWithMapping(null);
		 * regForm.setPropWithoutMapping(commonBo
		 * .getAllPartiesPropertiesMap(regForm, languageLocale));
		 * 
		 * if(regForm.getPropWithoutMapping().size()==1){
		 * 
		 * regForm.setIsPropertyRemainingMap(1); }
		 * 
		 * //Rakesh Mapping and share
		 * regForm.setAppStatus(RegInitConstant.STATUS_PROP_SAVED);
		 * regForm.setDeclareShareSize(regForm.getPropWithoutMapping() .size());
		 * // Set set=regForm.getPropWithoutMapping().keySet(); // Iterator
		 * itr=set.iterator(); // ArrayList //
		 * lis=(ArrayList)regForm.getPropWithoutMapping().get(itr.next()); //
		 * regForm
		 * .setShareOfPropSize((regForm.getPropWithoutMapping().size())*(lis
		 * .size()));
		 * 
		 * forward = "getMapping"; return mapping.findForward(forward);
		 * 
		 * }
		 * 
		 * }
		 */
		if (request.getParameter("mapping") != null && request.getParameter("mapping").equalsIgnoreCase("Y")) {
			ArrayList propList = commonBd.getAllPropertiesForShareMapping(regForm.getHidnRegTxnId());
			ArrayList mainList = new ArrayList();
			// mainList = new ArrayList();
			ArrayList subList = null;
			CommonDTO dto;
			if (propList != null) {
				for (int i = 0; i < propList.size(); i++) {
					subList = (ArrayList) propList.get(i);
					dto = new CommonDTO();
					dto.setPropertyId(subList.get(0).toString());
					dto.setValuationId(subList.get(0).toString());
					mainList.add(dto);
				}
				regForm.setMapPropIdList(mainList);
				if (regForm.getMapPropIdList().size() == 1) {
					regForm.setIsPropertyRemainingMap(1);
				}
			}
			regForm.setDeclareShareSize(1);
			regForm.setShareOfPropSize(1);
			forward = "alreadyMapped";
			return mapping.findForward(forward);
		}
		if (RegInitConstant.PARTY_POPUP_CLOSE_CONFIRM_ACTION.equals(actionName)) {
			commonBo.landConfirmationScreen(regForm, languageLocale, request);
			regForm.setActionName("");
			forward = "reginitConfirm";
		}
		if ("checkForAge".equalsIgnoreCase(actionName) || "checkForAge".equalsIgnoreCase(regForm.getActionNameTransacting())) {
			regForm.setAge("");
			regForm.setAgeTrns("");
			regForm.setGuardian("");
			regForm.setGuardianTrans("");
			regForm.setActionName("");
			regForm.setListAdoptedParty("");
			regForm.setListAdoptedPartyName("");
			regForm.setListAdoptedPartyTrns("");
			regForm.setListAdoptedPartyNameTrns("");
			regForm.setActionNameTransacting("");
		}
		if (RegInitConstant.REG_INIT_CONFIRM_PAGE.equals(formName)) {
			/*
			 * if(RegInitConstant.PARTY_POPUP_CLOSE_CONFIRM_ACTION.equals(actionName
			 * )){ commonBo.landConfirmationScreen(regForm, languageLocale,
			 * request); regForm.setActionName(""); forward="reginitConfirm"; }
			 */
			if (RegInitConstant.MODIFY_ACTION.equals(actionName)) {
				forward = "success";
			}
			if (RegInitConstant.BACK_TO_EXTRA_DISPLAY_ACTION.equals(actionName)) {
				commonBo.getExtraDetls(regForm, languageLocale);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				logger.debug("request deed ---->" + request.getAttribute("deedId"));
				regForm.setCallingAction("regInit.do?TRFS=NGI");
				regForm.setExtraDetlsModifyFlag(0);
				forward = "displayExtraDetls";
			}
			// added by Shreeraj
			if (RegInitConstant.SUBMIT_ADJU_ACTION.equals(actionName)) {
				// RegCommonDTO commonDto =new RegCommonDTO();
				// commonDto.ge
				// regForm.getStampDuty()
				String currDate = commonBo.getCurrDateTime();
				System.out.println("curr date 2 : " + currDate);
				regForm.setCurrDateTime(currDate);
				// if(regForm.getStampManually().equalsIgnoreCase("Y")){
				// reg txn status set 10 here
				// START | changes to insert the stamp details manually entered
				// by DR on Adjudication Fee payment page - By Santosh
				boolean insertStatus = false;
				boolean insertDutyStatus = false;
				try {
					/*
					 * if((null!=regForm.getRegistrationFee() ||
					 * !"".equalsIgnoreCase(regForm.getRegistrationFee())) ||
					 * (null!=regForm.getTotalduty() ||
					 * !"".equalsIgnoreCase(regForm.getTotalduty()))){
					 * insertDutyStatus = commonBo.insertStampDuties(regForm,
					 * userId); }
					 */
					if (null != regForm.getTotaldutyAdju() && !"".equalsIgnoreCase(regForm.getTotaldutyAdju())) {
						insertStatus = commonBo.insertAdjuDuties(regForm);
					}
				} catch (Exception e) {
					logger.debug("Exception occurred while inserting data for adjudication application " + e.getMessage());
				}
				logger.debug("insert status for stamp details manually entered by DR: " + insertStatus);
				// END | changes to insert the stamp details manually entered by
				// DR on Adjudication Fee payment page - By Santosh
				boolean updateTxnStatus = commonBo.insertTxnDetailsFinalAction(RegInitConstant.STATUS_COMPLETE, regForm.getHidnRegTxnId());
				logger.debug("txn status updated as 10 after complete adju payment: " + updateTxnStatus);
				if (!updateTxnStatus) {
					forward = "failure";
					return mapping.findForward(forward);
				}
				/*
				 * } else { boolean
				 * insertStatus=commonBo.insertAdjuDutiesSys(regForm); }
				 */
				forward = "success1";
			}
			if (RegInitConstant.ADJU_COMPLETED.equals(actionName)) {
				forward = "welcome";
			}
			if (RegInitConstant.POSTAL_COUNTRY_CHANGE.equals(actionName)) {
				commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
				regForm.setPostalState("");
				regForm.setPostalDistrict("");
				forward = "success1";
			}
			if (RegInitConstant.POSTAL_STATE_CHANGE.equals(actionName)) {
				commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
				commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
				forward = "success1";
			}
			// END
			// added by anuj for reg fee integration
			/*
			 * if(RegInitConstant.NEXT_TO_FEE_PAYMENT_ACTION.equals(actionName)){
			 * 
			 * regForm.setHidnUserId(session.getAttribute("UserId").toString());
			 * session.setAttribute("parentModName", "Registration Process");
			 * session.setAttribute("parentFunName", "Registration Initiation");
			 * session.setAttribute("parentFunId", "FUN_023");
			 * session.setAttribute("parentAmount", regForm.getTotalduty());
			 * 
			 * //following added on 13feb for new payment modality.
			 * 
			 * if(Double.parseDouble(regForm.getTotalBalanceAmountFee())>0) {
			 * request.setAttribute("parentModName", "Registration Process");
			 * request.setAttribute("parentFunName", "Registration Initiation");
			 * request.setAttribute("parentFunId", "FUN_206");
			 * request.setAttribute("parentAmount",
			 * regForm.getTotalBalanceAmountFee());//CHANGE
			 * request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
			 * //regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
			 * //request.setAttribute("parentKey",
			 * regForm.getPaymentTxnSeqId());
			 * request.setAttribute("forwardPath", "./nonPropRegInit.do");
			 * request.setAttribute("parentColumnName", "TXN_ID"); //end of
			 * addition on 13feb for new payment modality.
			 * 
			 * //code for checking user type of logged in user: //below for new
			 * payment requirements. completed on 4 sept 2013
			 * 
			 * String userTypeId=commonBo.getUserTypeId(userId); String
			 * parentOfficeId="NA"; String parentOfficeName="NA"; String
			 * parentDistrictId="NA"; String parentDistrictName="NA"; String
			 * parentReferenceId=regForm.getHidnRegTxnId();
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * if(userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU))
			 * {
			 * 
			 * //String[] arr=commonBo.getDistIdNameRU(userId);
			 * 
			 * //if(arr!=null && arr.length==2){
			 * parentDistrictId=commonBo.getDistIdEstamp
			 * (regForm.getHidnRegTxnId());
			 * parentDistrictName=commonBo.getDistrictName
			 * (parentDistrictId,languageLocale); //} //else{
			 * //parentDistrictId="NA"; //parentDistrictName="NA"; //}
			 * 
			 * }else
			 * if(userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP)
			 * ||
			 * userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB)
			 * || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI)
			 * ||
			 * userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)){
			 * 
			 * String[] arr=commonBo.getDistIdNameSP(userId);
			 * 
			 * if(arr!=null && arr.length==2){ parentDistrictId=arr[0].trim();
			 * parentDistrictName
			 * =commonBo.getDistrictName(parentDistrictId,languageLocale); }
			 * else{ parentDistrictId="NA"; parentDistrictName="NA"; }
			 * 
			 * }else
			 * if(userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS
			 * )){
			 * 
			 * 
			 * String[]
			 * arr=commonBo.getDistIdNameOfficeNameDRS(session.getAttribute
			 * ("loggedToOffice").toString());
			 * parentOfficeId=session.getAttribute("loggedToOffice").toString();
			 * 
			 * if(arr!=null && arr.length==3){
			 * 
			 * parentDistrictId=arr[0].trim();
			 * parentDistrictName=commonBo.getDistrictName
			 * (parentDistrictId,languageLocale);
			 * parentOfficeName=commonBo.getOfficeName
			 * (parentOfficeId,languageLocale); } else{
			 * 
			 * parentDistrictId="NA"; parentDistrictName="NA";
			 * parentOfficeName="NA"; }
			 * 
			 * 
			 * }
			 * 
			 * 
			 * request.setAttribute("parentOfficeId", parentOfficeId);
			 * request.setAttribute("parentOfficeName", parentOfficeName);
			 * request.setAttribute("parentDistrictId", parentDistrictId);
			 * request.setAttribute("parentDistrictName", parentDistrictName);
			 * request.setAttribute("parentReferenceId", parentReferenceId);
			 * 
			 * //end of new payment requirements added on 4th sept 2013
			 * 
			 * String paymentType="2";
			 * 
			 * regForm.setPaymentType(paymentType);
			 * 
			 * //CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.
			 * 
			 * String[]
			 * paymentArr=commonBo.getPaymentFlag(regForm.getHidnRegTxnId
			 * (),paymentType);
			 * //logger.debug("----------------->payment flag:-"+paymentArr[0]);
			 * if(paymentArr!=null) { if(!paymentArr[0].equalsIgnoreCase("p") &&
			 * !paymentArr[0].equalsIgnoreCase("c")){
			 * 
			 * if(paymentArr[0].equalsIgnoreCase("I")){
			 * 
			 * //following code for updating purpose
			 * 
			 * 
			 * 
			 * request.setAttribute("parentKey", paymentArr[1]); //following
			 * code for updating purpose boolean
			 * updatePurpose=commonBo.updateEStampPurpose
			 * (regForm.getHidnRegTxnId(),regForm.getPurpose());
			 * if(updatePurpose) forward="reginitPayment"; else
			 * forward="failure";
			 * 
			 * 
			 * }
			 * 
			 * 
			 * } else if(paymentArr[0].equalsIgnoreCase("p")){
			 * 
			 * 
			 * 
			 * regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
			 * request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
			 * //insertion code //String
			 * param[]={regForm.getHidnRegTxnId(),regForm
			 * .getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
			 * boolean
			 * insertStatus=commonBo.insertPaymentDetails(regForm,paymentType);
			 * logger
			 * .debug("----------------->payment insertion status:-"+insertStatus
			 * ); if(insertStatus) forward="regFeePage"; else forward="failure";
			 * } else if(paymentArr[0].equalsIgnoreCase("c")){
			 * forward="success1"; }else{ forward="failure"; } }else{
			 * 
			 * 
			 * 
			 * regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
			 * request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
			 * //insertion code //String
			 * param[]={regForm.getHidnRegTxnId(),regForm
			 * .getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
			 * boolean
			 * insertStatus=commonBo.insertPaymentDetails(regForm,paymentType);
			 * logger
			 * .debug("----------------->payment insertion status:-"+insertStatus
			 * ); if(insertStatus) forward="reginitPayment"; else
			 * forward="failure"; } }else{
			 * 
			 * actionName=RegInitConstant.FINAL_ACTION;
			 * 
			 * } //forward="reginitPayment"; //return
			 * mapping.findForward(forward); }
			 */
			if (RegInitConstant.NEXT_TO_PAYMENT_ACTION.equals(actionName)) {
				// added by ankit for female rebate ----- start
				boolean rebateApplicableCheck = commonBo.checkFemaleRebate(regForm.getHidnRegTxnId());
				logger.info("rebateApplicableCheck - " + rebateApplicableCheck);
				if (!rebateApplicableCheck) {
					if (languageLocale.equalsIgnoreCase("en")) {
						request.setAttribute("ERROR", "All buyers must be female as exemption is taken/ Exemption taken for Organisation or Govt");
					} else {
						request.setAttribute("ERROR", "????????? ???????????????????????? ?????? ??????????????? ???????????? ??????????????? ????????????????????? ????????? ?????? ?????? ?????? / ??????????????? ?????? ??????????????? ?????? ????????? ?????????");
					}
					logger.info("rebateApplicableCheck when false - " + rebateApplicableCheck);
					// regForm.setActionName(RegInitConstant.NO_ACTION);
					regForm.setRegInitEstampCode(null);
					regForm.setActionName("");
					forward = "reginitConfirm";
				}
				// added by ankit for female rebate ----- end
				else { // start of else of rebateApplicableCheck
					regForm.setPurposeAddedFlag("y");
					/*
					 * session.setAttribute("parentModName",
					 * "Registration Process");
					 * session.setAttribute("parentFunName",
					 * "Registration Initiation");
					 * session.setAttribute("parentFunId", "FUN_023");
					 * session.setAttribute("parentAmount",
					 * regForm.getTotalduty());
					 */
					// following added on 13feb for new payment modality.
					if (Double.parseDouble(regForm.getTotalBalanceAmount()) > 0) {
						request.setAttribute("parentModName", "Registration Process");
						request.setAttribute("parentFunName", "Registration Initiation");
						request.setAttribute("formName", "reginit");
						request.setAttribute("parentFunId", "FUN_023");
						request.setAttribute("parentAmount", regForm.getTotalBalanceAmount());
						request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
						// regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						// request.setAttribute("parentKey",
						// regForm.getPaymentTxnSeqId());
						request.setAttribute("forwardPath", "./regInit.do?TRFS=NGI");
						request.setAttribute("parentColumnName", "TXN_ID");
						// end of addition on 13feb for new payment modality.
						// code for checking user type of logged in user:
						// below for new payment requirements. completed on 4
						// sept
						// 2013
						String userTypeId = commonBo.getUserTypeId(userId);
						String parentOfficeId = "NA";
						String parentOfficeName = "NA";
						String parentDistrictId = "NA";
						String parentDistrictName = "NA";
						String parentReferenceId = regForm.getHidnRegTxnId();
						if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU)) {
							// String[] arr=commonBo.getDistIdNameRU(userId);
							// if(arr!=null && arr.length==2){
							parentDistrictId = commonBo.getDistIdEstamp(regForm.getHidnRegTxnId());
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
							// }
							// else{
							// parentDistrictId="NA";
							// parentDistrictName="NA";
							// }
						} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)) {
							String[] arr = commonBo.getDistIdNameSP(userId);
							if (arr != null && arr.length == 2) {
								parentDistrictId = arr[0].trim();
								parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
							} else {
								parentDistrictId = "NA";
								parentDistrictName = "NA";
							}
						} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)) {
							String[] arr = commonBo.getDistIdNameOfficeNameDRS(session.getAttribute("loggedToOffice").toString());
							parentOfficeId = session.getAttribute("loggedToOffice").toString();
							if (arr != null && arr.length == 3) {
								parentDistrictId = arr[0].trim();
								parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
								parentOfficeName = commonBo.getOfficeName(parentOfficeId, languageLocale);
							} else {
								parentDistrictId = "NA";
								parentDistrictName = "NA";
								parentOfficeName = "NA";
							}
						}
						request.setAttribute("parentOfficeId", parentOfficeId);
						request.setAttribute("parentOfficeName", parentOfficeName);
						request.setAttribute("parentDistrictId", parentDistrictId);
						request.setAttribute("parentDistrictName", parentDistrictName);
						request.setAttribute("parentReferenceId", parentReferenceId);
						String paymentType = "1";
						regForm.setPaymentType(paymentType);
						// end of new payment requirements added on 4th sept
						// 2013
						// CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.
						String[] paymentArr = commonBo.getPaymentFlag(regForm.getHidnRegTxnId(), paymentType);
						// logger.debug("----------------->payment flag:-"+paymentArr
						// [0]);
						if (paymentArr != null) {
							if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {
								if (paymentArr[0].equalsIgnoreCase("I")) {
									// following code for updating purpose
									if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
										boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
										if (updatePurpose) {
											logger.debug("purpose updated");
											forward = "reginitPayment";
										} else {
											logger.debug("purpose not updated");
											forward = "failure";
										}
									}
									regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
									request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
									// insertion code
									// String
									// param[]={regForm.getHidnRegTxnId(),regForm.
									// getTotalduty
									// (),regForm.getHidnUserId(),regForm
									// .getPurpose()};
									boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
									logger.debug("----------------->payment insertion status new record:-" + insertStatus);
									if (insertStatus)
										forward = "reginitPayment";
									else
										forward = "failure";
								}
							} else if (paymentArr[0].equalsIgnoreCase("p")) {
								if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
									boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
									if (updatePurpose) {
										logger.debug("purpose updated");
										// forward="reginitPayment";
									} else {
										logger.debug("purpose not updated");
										// forward="failure";
									}
								}
								regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
								request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
								// insertion code
								// String
								// param[]={regForm.getHidnRegTxnId(),regForm.
								// getTotalduty
								// (),regForm.getHidnUserId(),regForm.getPurpose()};
								boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
								logger.debug("----------------->payment insertion status:-" + insertStatus);
								if (insertStatus)
									forward = "reginitPayment";
								else
									forward = "failure";
							} else if (paymentArr[0].equalsIgnoreCase("c")) {
								forward = "success1";
							} else {
								forward = "failure";
							}
						} else {
							if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
								boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
								if (updatePurpose) {
									logger.debug("purpose updated");
									// forward="reginitPayment";
								} else {
									logger.debug("purpose not updated");
									// forward="failure";
								}
							}
							regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
							// insertion code
							// String
							// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty
							// (),regForm.getHidnUserId(),regForm.getPurpose()};
							boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
							logger.debug("----------------->payment insertion status:-" + insertStatus);
							if (insertStatus)
								forward = "reginitPayment";
							else
								forward = "failure";
						}
					} else {
						actionName = RegInitConstant.FINAL_ACTION;
					}
				}// end of rebateApplicableCheck
				// forward="reginitPayment";
				// return mapping.findForward(forward);
			}
			if (RegInitConstant.NEXT_TO_FEE_PAYMENT_ACTION.equals(actionName)) {
				regForm.setActionName("");
				regForm.setHidnUserId(session.getAttribute("UserId").toString());
				/*
				 * session.setAttribute("parentModName",
				 * "Registration Process");
				 * session.setAttribute("parentFunName",
				 * "Registration Initiation");
				 * session.setAttribute("parentFunId", "FUN_023");
				 * session.setAttribute("parentAmount", regForm.getTotalduty());
				 */
				// following added on 13feb for new payment modality.
				if (Double.parseDouble(regForm.getTotalBalanceAmountFee()) > 0) {
					request.setAttribute("parentModName", "Registration Process");
					request.setAttribute("parentFunName", "Registration Initiation");
					request.setAttribute("formName", "reginit");
					request.setAttribute("parentFunId", "FUN_206");
					request.setAttribute("parentAmount", regForm.getTotalBalanceAmountFee());// CHANGE
					request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
					// regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
					// request.setAttribute("parentKey",
					// regForm.getPaymentTxnSeqId());
					request.setAttribute("forwardPath", "./regInit.do?TRFS=NGI");
					request.setAttribute("parentColumnName", "TXN_ID");
					// end of addition on 13feb for new payment modality.
					// code for checking user type of logged in user:
					// below for new payment requirements. completed on 4 sept
					// 2013
					String userTypeId = commonBo.getUserTypeId(userId);
					String parentOfficeId = "NA";
					String parentOfficeName = "NA";
					String parentDistrictId = "NA";
					String parentDistrictName = "NA";
					String parentReferenceId = regForm.getHidnRegTxnId();
					if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU)) {
						// String[] arr=commonBo.getDistIdNameRU(userId);
						// if(arr!=null && arr.length==2){
						parentDistrictId = commonBo.getDistIdEstamp(regForm.getHidnRegTxnId());
						parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						// }
						// else{
						// parentDistrictId="NA";
						// parentDistrictName="NA";
						// }
					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)) {
						String[] arr = commonBo.getDistIdNameSP(userId);
						if (arr != null && arr.length == 2) {
							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						} else {
							parentDistrictId = "NA";
							parentDistrictName = "NA";
						}
					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)) {
						String[] arr = commonBo.getDistIdNameOfficeNameDRS(session.getAttribute("loggedToOffice").toString());
						parentOfficeId = session.getAttribute("loggedToOffice").toString();
						if (arr != null && arr.length == 3) {
							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
							parentOfficeName = commonBo.getOfficeName(parentOfficeId, languageLocale);
						} else {
							parentDistrictId = "NA";
							parentDistrictName = "NA";
							parentOfficeName = "NA";
						}
					}
					request.setAttribute("parentOfficeId", parentOfficeId);
					request.setAttribute("parentOfficeName", parentOfficeName);
					request.setAttribute("parentDistrictId", parentDistrictId);
					request.setAttribute("parentDistrictName", parentDistrictName);
					request.setAttribute("parentReferenceId", parentReferenceId);
					// end of new payment requirements added on 4th sept 2013
					String paymentType = "2";
					regForm.setPaymentType(paymentType);
					// CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.
					String[] paymentArr = commonBo.getPaymentFlag(regForm.getHidnRegTxnId(), paymentType);
					// logger.debug("----------------->payment flag:-"+paymentArr[0]);
					if (paymentArr != null) {
						if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {
							if (paymentArr[0].equalsIgnoreCase("I")) {
								regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
								request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
								// insertion code
								// String
								// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
								boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
								logger.debug("----------------->payment insertion status:-" + insertStatus);
								if (insertStatus)
									forward = "reginitPayment";
								else
									forward = "failure";
							}
						} else if (paymentArr[0].equalsIgnoreCase("p")) {
							regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
							// insertion code
							// String
							// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
							boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
							logger.debug("----------------->payment insertion status:-" + insertStatus);
							if (insertStatus)
								forward = "reginitPayment";
							else
								forward = "failure";
						} else if (paymentArr[0].equalsIgnoreCase("c")) {
							forward = "success1";
						} else {
							forward = "failure";
						}
					} else {
						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
						// insertion code
						// String
						// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
						boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
						logger.debug("----------------->payment insertion status:-" + insertStatus);
						if (insertStatus)
							forward = "reginitPayment";
						else
							forward = "failure";
					}
				} else {
					actionName = RegInitConstant.FINAL_ACTION;
				}
				// forward="reginitPayment";
				// return mapping.findForward(forward);
			}
			// added by saurav for mutation fee payment
			if (RegInitConstant.NEXT_TO_MUTATION_FEE_PAYMENT_ACTION.equals(actionName)) {
				regForm.setActionName("");
				regForm.setHidnUserId(session.getAttribute("UserId").toString());
				/*
				 * session.setAttribute("parentModName",
				 * "Registration Process");
				 * session.setAttribute("parentFunName",
				 * "Registration Initiation");
				 * session.setAttribute("parentFunId", "FUN_023");
				 * session.setAttribute("parentAmount", regForm.getTotalduty());
				 */
				// following added on 13feb for new payment modality.
				if (Double.parseDouble(regForm.getBalanceLeftMutation()) > 0) {
					request.setAttribute("parentModName", "Registration Process");
					request.setAttribute("parentFunName", "Registration Initiation");
					request.setAttribute("formName", "reginit");
					request.setAttribute("parentFunId", "FUN_601");
					request.setAttribute("parentAmount", regForm.getBalanceLeftMutation());// CHANGE
					request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
					request.setAttribute("forwardPath", "./regInit.do?TRFS=NGI");
					request.setAttribute("parentColumnName", "TXN_ID");
					String userTypeId = commonBo.getUserTypeId(userId);
					String parentOfficeId = "NA";
					String parentOfficeName = "NA";
					String parentDistrictId = "NA";
					String parentDistrictName = "NA";
					String parentReferenceId = regForm.getHidnRegTxnId();
					if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU)) {
						// String[] arr=commonBo.getDistIdNameRU(userId);
						// if(arr!=null && arr.length==2){
						parentDistrictId = commonBo.getDistIdEstamp(regForm.getHidnRegTxnId());
						parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						// }
						// else{
						// parentDistrictId="NA";
						// parentDistrictName="NA";
						// }
					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)) {
						String[] arr = commonBo.getDistIdNameSP(userId);
						if (arr != null && arr.length == 2) {
							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						} else {
							parentDistrictId = "NA";
							parentDistrictName = "NA";
						}
					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)) {
						String[] arr = commonBo.getDistIdNameOfficeNameDRS(session.getAttribute("loggedToOffice").toString());
						parentOfficeId = session.getAttribute("loggedToOffice").toString();
						if (arr != null && arr.length == 3) {
							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
							parentOfficeName = commonBo.getOfficeName(parentOfficeId, languageLocale);
						} else {
							parentDistrictId = "NA";
							parentDistrictName = "NA";
							parentOfficeName = "NA";
						}
					}
					request.setAttribute("parentOfficeId", parentOfficeId);
					request.setAttribute("parentOfficeName", parentOfficeName);
					request.setAttribute("parentDistrictId", parentDistrictId);
					request.setAttribute("parentDistrictName", parentDistrictName);
					request.setAttribute("parentReferenceId", parentReferenceId);
					String paymentType = "4"; //
					regForm.setPaymentType(paymentType);
					String[] paymentArr = commonBo.getPaymentFlag(regForm.getHidnRegTxnId(), paymentType);
					// logger.debug("----------------->payment flag:-"+paymentArr[0]);
					if (paymentArr != null) {
						if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {
							if (paymentArr[0].equalsIgnoreCase("I")) {
								regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
								request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
								// insertion code
								// String
								// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
								boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
								logger.debug("----------------->payment insertion status:-" + insertStatus);
								if (insertStatus)
									forward = "reginitPayment";
								else
									forward = "failure";
							}
						} else if (paymentArr[0].equalsIgnoreCase("p")) {
							regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
							// insertion code
							// String
							// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
							boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
							logger.debug("----------------->payment insertion status:-" + insertStatus);
							if (insertStatus)
								forward = "reginitPayment";
							else
								forward = "failure";
						} else if (paymentArr[0].equalsIgnoreCase("c")) {
							forward = "success1";
						} else {
							forward = "failure";
						}
					} else {
						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
						// insertion code
						// String
						// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
						boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
						logger.debug("----------------->payment insertion status:-" + insertStatus);
						if (insertStatus)
							forward = "reginitPayment";
						else
							forward = "failure";
					}
				} else {
					actionName = RegInitConstant.FINAL_ACTION;
				}
				// forward="reginitPayment";
				// return mapping.findForward(forward);
			}
			// end of mutation fee payment
			if (RegInitConstant.NEXT_TO_ADJU_PAYMENT_ACTION.equals(actionName)) {
				boolean insertStatusDuty = false;
				if (regForm.getAdjuDutyAddedFlag() != null && !("").equalsIgnoreCase(regForm.getAdjuDutyAddedFlag()) && ("Y").equalsIgnoreCase(regForm.getAdjuDutyAddedFlag())) {
					insertStatusDuty = true;
				} else {
					if (regForm.getListDtoAdju() != null && regForm.getListDtoAdju().size() > 0) {
						logger.debug("adjudication additional uploads found---> ");
						boolean checkAdditionalUploads = false;
						for (int i = 0; i < regForm.getListDtoAdju().size(); i++) {
							CommonDTO dto = regForm.getListDtoAdju().get(i);
							dto.setDocumentName(commonBo.getNewFileName(dto.getDocumentName(), Integer.toString(i)));
							String filepath = uploadFile(regForm.getHidnRegTxnId(), dto.getDocContents(), null, null, dto.getDocumentName());
							if (filepath != null) {
								checkAdditionalUploads = true;
								dto.setDocumentPath(filepath);
							} else {
								checkAdditionalUploads = false;
								break;
							}
						}
						if (checkAdditionalUploads) {
							checkAdditionalUploads = commonBo.insertAdditionalUploadsAdju(regForm);
							if (checkAdditionalUploads) {
								insertStatusDuty = commonBo.insertAdjuDuties(regForm);
								if (!insertStatusDuty) {
									logger.debug("adjudication duty insertion failure---> ");
									forward = "failure";
								}
							} else {
								logger.debug("adjudication additional upload table insertion failure---> ");
								forward = "failure";
							}
						} else {
							logger.debug("adjudication additional upload file creation failure---> ");
							forward = "failure";
						}
					} else {
						logger.debug("adjudication additional uploads not found---> ");
						insertStatusDuty = commonBo.insertAdjuDuties(regForm);
					}
					// insertStatusDuty=commonBo.insertAdjuDuties(regForm);
				}
				logger.debug("adjudication duties insertion status---> " + insertStatusDuty);
				if (insertStatusDuty) {
					regForm.setActionName("");
					regForm.setHidnUserId(session.getAttribute("UserId").toString());
					/*
					 * session.setAttribute("parentModName",
					 * "Registration Process");
					 * session.setAttribute("parentFunName",
					 * "Registration Initiation");
					 * session.setAttribute("parentFunId", "FUN_023");
					 * session.setAttribute("parentAmount",
					 * regForm.getTotalduty());
					 */
					// following added on 13feb for new payment modality.
					// if(Double.parseDouble(regForm.getTotalBalanceAmountFee())>0)
					// {
					request.setAttribute("parentModName", "Registration Process");
					request.setAttribute("parentFunName", "Registration Initiation");
					request.setAttribute("formName", "reginit");
					request.setAttribute("parentFunId", "FUN_029");
					request.setAttribute("parentAmount", regForm.getTotalBalanceAmountAdjuFee());// CHANGE
					request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
					// regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
					// request.setAttribute("parentKey",
					// regForm.getPaymentTxnSeqId());
					request.setAttribute("forwardPath", "./regInit.do?TRFS=NGI");
					request.setAttribute("parentColumnName", "TXN_ID");
					// end of addition on 13feb for new payment modality.
					// code for checking user type of logged in user:
					// below for new payment requirements. completed on 4 sept
					// 2013
					String userTypeId = commonBo.getUserTypeId(userId);
					String parentOfficeId = "NA";
					String parentOfficeName = "NA";
					String parentDistrictId = "NA";
					String parentDistrictName = "NA";
					String parentReferenceId = regForm.getHidnRegTxnId();
					if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU)) {
						// String[] arr=commonBo.getDistIdNameRU(userId);
						// if(arr!=null && arr.length==2){
						parentDistrictId = commonBo.getDistIdEstamp(regForm.getHidnRegTxnId());
						parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						// }
						// else{
						// parentDistrictId="NA";
						// parentDistrictName="NA";
						// }
					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)) {
						String[] arr = commonBo.getDistIdNameSP(userId);
						if (arr != null && arr.length == 2) {
							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						} else {
							parentDistrictId = "NA";
							parentDistrictName = "NA";
						}
					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)) {
						String[] arr = commonBo.getDistIdNameOfficeNameDRS(session.getAttribute("loggedToOffice").toString());
						parentOfficeId = session.getAttribute("loggedToOffice").toString();
						if (arr != null && arr.length == 3) {
							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
							parentOfficeName = commonBo.getOfficeName(parentOfficeId, languageLocale);
						} else {
							parentDistrictId = "NA";
							parentDistrictName = "NA";
							parentOfficeName = "NA";
						}
					}
					request.setAttribute("parentOfficeId", parentOfficeId);
					request.setAttribute("parentOfficeName", parentOfficeName);
					request.setAttribute("parentDistrictId", parentDistrictId);
					request.setAttribute("parentDistrictName", parentDistrictName);
					request.setAttribute("parentReferenceId", parentReferenceId);
					// end of new payment requirements added on 4th sept 2013
					String paymentType = "3";
					regForm.setPaymentType(paymentType);
					// CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.
					String[] paymentArr = commonBo.getPaymentFlag(regForm.getHidnRegTxnId(), paymentType);
					// logger.debug("----------------->payment flag:-"+paymentArr[0]);
					if (paymentArr != null) {
						if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {
							if (paymentArr[0].equalsIgnoreCase("I")) {
								regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
								request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
								// insertion code
								// String
								// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
								boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
								logger.debug("----------------->payment insertion status:-" + insertStatus);
								if (insertStatus)
									forward = "reginitPayment";
								else
									forward = "failure";
							}
						} else if (paymentArr[0].equalsIgnoreCase("p")) {
							regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
							// insertion code
							// String
							// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
							boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
							logger.debug("----------------->payment insertion status:-" + insertStatus);
							if (insertStatus)
								forward = "reginitPayment";
							else
								forward = "failure";
						} else if (paymentArr[0].equalsIgnoreCase("c")) {
							forward = "success1";
						} else {
							forward = "failure";
						}
					} else {
						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
						// insertion code
						// String
						// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
						boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
						logger.debug("----------------->payment insertion status:-" + insertStatus);
						if (insertStatus)
							forward = "reginitPayment";
						else
							forward = "failure";
					}
					/*
					 * }else{
					 * 
					 * actionName=RegInitConstant.FINAL_ACTION;
					 * 
					 * }
					 */
					// forward="reginitPayment";
					// return mapping.findForward(forward);
				} else {
					logger.debug("adjudication duty insertion failure---> ");
					forward = "failure";
				}
			}
		}
		// FOLLOWING ADDED BY ROOPAM
		if (RegInitConstant.CANCEL_TRANSACTING_PART_ACTION.equals(actionName) || RegInitConstant.CANCEL_ACTION.equals(actionName) && (request.getParameter("modName") == null)) {
			cancelAction(session, regForm);
			if (map != null) {
				if (!map.isEmpty())
					map.clear();
			}
			// count=0;
			// keyCount=0;
			forward = "welcome";
			return mapping.findForward(forward);
		}
		// following code for party type
		// if(request.getAttribute("regFormDashboard")==null){ //this line was
		// added for dashboard
		if (RegInitConstant.PARTY_PAGE_FORM.equals(formName) || RegInitConstant.TRANS_PARTY_PAGE_FORM.equals(formName)) {
			String roleType = null;
			String roleTypeTrns = null;
			if (request.getParameter("partyType") == null || request.getParameter("partyType").toString().equalsIgnoreCase("")) {
				roleType = "0";
			} else {
				roleType = (String) request.getParameter("partyType");
				regForm.setPartyType(roleType); // setting role id.
				String[] claimantArr = commonBo.getClaimantFlag(roleType);
				// int claimantFlag=Integer.parseInt(claimantArr[0]);
				regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
				commonDto.setAppType(commonBo.getAppType(languageLocale, regForm.getPoaHolderFlag(), 0));
			}
			if (request.getParameter("partyTypeTrns") == null || request.getParameter("partyTypeTrns").toString().equalsIgnoreCase("")) {
				roleTypeTrns = "0";
			} else {
				roleTypeTrns = (String) request.getParameter("partyTypeTrns");
				regForm.setPartyTypeTrns(roleTypeTrns); // setting role id.
				String[] claimantArr = commonBo.getClaimantFlag(roleTypeTrns);
				// int claimantFlag=Integer.parseInt(claimantArr[0]);
				regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
				commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale, regForm.getPoaHolderFlag(), 0));
			}
			if ((roleType != null || RegInitConstant.NEXT_ACTION.equals(actionName)) || RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)) {
				if ((roleType != null || RegInitConstant.NEXT_ACTION.equals(actionName)) && !(RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName))) {
					request.setAttribute("roleId", Integer.parseInt(roleType));
					request.setAttribute("roleIdTrns", "0");
				}
				if (roleTypeTrns != null && (RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName) || RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName)))
					request.setAttribute("roleIdTrns", Integer.parseInt(roleTypeTrns));
			}
		}
		if (RegInitConstant.NO_ACTION.equals(actionName)) {
			if (regForm.getPartyRoleId() != null && !regForm.getPartyRoleId().equalsIgnoreCase("") && !regForm.getPartyRoleId().equalsIgnoreCase("null")) {
				request.setAttribute("roleIdTrns", Integer.parseInt(regForm.getPartyRoleId()));
			} else
				request.setAttribute("roleIdTrns", 0);
		}
		if (RegInitConstant.NO_ACTION.equals(actionName)) { // forward
			// ="reginitMapping";
		} else if (RegInitConstant.NEXT_ACTION.equals(actionName) || RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName) || RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName) || RegInitConstant.ADD_MORE_ACTION.equals(actionName) || RegInitConstant.DELETE_MORE_ACTION.equals(actionName))
			forward = "transactingParty";
		else if (RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName))
			forward = "reginitMapping";
		else if (RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName) || RegInitConstant.NEXT_TO_PROPERTY_ACTION_THROUGH_MAP.equals(actionName))
			forward = "reginitProperty";
		else if (RegInitConstant.NEXT_TO_PAYMENT_ACTION.equals(actionName)) {// forward
			// ="reginitPayment";
			regForm.setActionName(RegInitConstant.NO_ACTION);
		}
		// else
		// forward ="success";
		if (RegInitConstant.RESET_MAPPING_ACTION.equals(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setOwnerId("");
			// session.setAttribute("regForm", regForm);
			forward = "reginitMapping";
		}
		if (RegInitConstant.RESET_PROPERTY_ACTION.equals(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			// PropertyValuationDTO dto= new PropertyValuationDTO();
			RegCompletDTO comDto = new RegCompletDTO();
			regForm.setRegcompletDto(comDto);
			// regForm.setPropertyDTO(dto);
			// session.setAttribute("regForm", regForm);
			forward = "reginitProperty";
		}
		String label = null;
		label = (String) request.getParameter("label");
		if (label != null && label != "") {
			if (label.equalsIgnoreCase("display")) {
				RegCommonDTO mapDtoDisp = new RegCommonDTO();
				String key = request.getParameter("key");
				request.setAttribute("displayOnwerPartyKey", key);
				map2 = regForm.getMapTransactingPartiesDisp();
				if (!map2.isEmpty())
					map2.clear();
				mapDtoDisp = (RegCommonDTO) regForm.getMapTransactingParties().get(key);
				map2.put(key, mapDtoDisp);
				// aadhar integration
				if ("7".equals(mapDtoDisp.getSelectedPhotoId())) {
					mapDtoDisp.setPartyDisplayAadhar("1");
				} else {
					mapDtoDisp.setPartyDisplayAadhar("0");
				}
				regForm.setMapTransactingPartiesDisp(map2);
				String confirmationFlag = null;
				confirmationFlag = (String) request.getParameter("confirmationFlag");
				if (confirmationFlag != null && confirmationFlag != "") {
					if (confirmationFlag.equalsIgnoreCase("true")) {
						regForm.setConfirmationFlag("01");
					}
				} else {
					regForm.setConfirmationFlag("00");
				}
				request.setAttribute("deedId", regForm.getDeedId());
				request.setAttribute("instId", regForm.getInstID());
				// request.setAttribute("roleId", regForm.getPartyType());
				// request.setAttribute("roleId",
				// mapDtoDisp.getPartyTypeTrns());
				if (mapDtoDisp.getPartyTypeTrns() != null && !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("") && !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("null")) {
					request.setAttribute("roleId", mapDtoDisp.getPartyTypeTrns());
				} else {
					request.setAttribute("roleId", 0);
				}
				/*
				 * int roleId=0;
				 * 
				 * if(mapDtoDisp.getPartyTypeTrns()!=null){
				 * roleId=Integer.parseInt(mapDtoDisp.getPartyTypeTrns()); }
				 */
				// String[]
				// claimantArr=commonBo.getClaimantFlag(Integer.toString(roleId));
				// String[]
				// claimantArr=commonBo.getClaimantFlag(mapDtoDisp.getPartyTypeTrns());
				// int claimantFlag=Integer.parseInt(claimantArr[0]);
				// regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				// regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
				// request.setAttribute("roleIdTrns",
				// regForm.getPartyTypeTrns());
				forward = "displayRegDetls";
			}
			if (label.equalsIgnoreCase("displayApp")) {
				forward = "success";
			}
			if (label.equalsIgnoreCase("displayTrns")) {
				forward = "transactingParty";
			}
			if (label.equalsIgnoreCase("displayPartyEdit")) {
				forward = "displayRegDetls";
			}
			if (label.equalsIgnoreCase("deedDraftView")) {
				DeedDraftBD bd = new DeedDraftBD();
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				// boolean validID=bd.checkID(regForm.getDeedDraftId());
				// if(validID)
				// {
				String path = pr.getValue("pdf.location");
				String flags[] = commonBo.getInstrumentFlagDeedPDF(String.valueOf(regForm.getInstID()));
				String propReq = flags[0];
				String propValReq = flags[1];
				String commonDeed = flags[2];
				String propOptionalFlg = flags[3];
				ArrayList list = bd.getPropdetails(regForm.getHidnRegTxnId());
				if (propReq.equalsIgnoreCase("Y") && (propValReq.equalsIgnoreCase("Y") || propValReq.equalsIgnoreCase("N"))) {
					if (propOptionalFlg.equalsIgnoreCase("Y") && !(list.size() > 0)) {
						bd.generateDeedPDFOLD(path, regForm.getDeedDraftId(), response, 0);
					} else {
						bd.newDeedPDF(regForm, path, regForm.getDeedDraftId(), response, 0, languageLocale);
					}
				} else {
					bd.generateDeedPDFOLD(path, regForm.getDeedDraftId(), response, 0); // changed
					// by
					// ankit
					// for
					// prop
					// val
					// pdf
				}
				// byte[] propDetlsBytes = bd.newDeedPDF(regForm,path,
				// regForm.getDeedDraftId(), response, 0);
				// byte[] propDetlsBytes =
				// bd.propDetlsPdf(regForm.getHidnRegTxnId()); //added by ankit
				// for prop val pdf
				// bd.generateDeedPDF(path, regForm.getDeedDraftId(), response,
				// 0,propDetlsBytes); //changed by ankit for prop val pdf
				// }else{
				// formDTO.setValidDeed("Invalid");
				// }
				regForm.setActionName("");
				forward = "reginitConfirm";
			}
			if (label.equalsIgnoreCase("deedDraftViewFolder")) {
				DMSUtility.downloadDocument(response, regForm.getDeedPath(), regForm.getDeedContents());
				regForm.setActionName("");
				forward = "reginitConfirm";
			}
			if (label.equalsIgnoreCase("displayOwner")) {
				// open owner view here
				String key = request.getParameter("key");
				String reqAtt = request.getParameter("displayOnwerPartyKey");
				if (reqAtt != null) {
					commonBo.getOwnerDetailsFromHashmap(key, regForm, languageLocale, null, reqAtt);
				} else {
					commonBo.getOwnerDetails(key, regForm, languageLocale);
				}
				regForm.setActionName("");
				if (regForm.getPartyModifyFlag() == 1) {
					regForm.setOwnerModifyFlag(1);
				} else {
					regForm.setOwnerModifyFlag(0);
				}
				if (regForm.getOwnerModifyFlag() == 1) {
					if (regForm.getOwnerOgrName().equals("-")) {
						regForm.setOwnerOgrName("");
					}
					if (regForm.getOwnerEmailID().equals("-")) {
						regForm.setOwnerEmailID("");
					}
					if (regForm.getOwnerIdno().equals("-")) {
						regForm.setOwnerIdno("");
					}
					if (regForm.getAddressOwnerOutMp().equals("-")) {
						regForm.setAddressOwnerOutMp("");
					}
					if (regForm.getOwnerIndphno().equals("-")) {
						regForm.setOwnerIndphno("");
					}
					if (regForm.getOwnerPostalCode().equals("-")) {
						regForm.setOwnerPostalCode("");
					}
					if (regForm.getOwnerPanNumber().equals("-")) {
						regForm.setOwnerPanNumber("");
					}
					if (regForm.getOwnerMotherName().equals("-")) {
						regForm.setOwnerMotherName("");
					}
					if (regForm.getOwnerSpouseName().equals("-")) {
						regForm.setOwnerSpouseName("");
					}
					if (regForm.getOwnerPermissionNo().equals("-")) {
						regForm.setOwnerPermissionNo("");
					}
					if (regForm.getOwnerIndDisabilityDesc().equals("-")) {
						regForm.setOwnerIndDisabilityDesc("");
					}
					commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));
					commonDto.setIdProf(commonBo.getIdProf(languageLocale));
					commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendar(), languageLocale));
					// added by Ankit for Aadhar integration
					regForm.setAadharCheckOwner("");
					regForm.setAadharErrMsgOwner("");
					regForm.setAadharNameTrnsOwner("");
					regForm.setAadharRespCodeOwner("");
				}
				if (!("7".equalsIgnoreCase(regForm.getOwnerListID()))) {
					regForm.setOwnerDisplayAadhar("0");
					regForm.setAadharNameOwner("");
				} else {
					regForm.setOwnerDisplayAadhar("1");
				}
				forward = "displayOwner";
			}
			if (label.equalsIgnoreCase("displayOwnerAppLive")) {
				// open owner view here
				String key = request.getParameter("key");
				commonBo.getOwnerDetailsFromHashmap(key, regForm, languageLocale, "app", null);
				regForm.setActionName("");
				forward = "displayOwner";
			}
			if (label.equalsIgnoreCase("displayOwnerTrnsLive")) {
				// open owner view here
				String key = request.getParameter("key");
				commonBo.getOwnerDetailsFromHashmap(key, regForm, languageLocale, "Trns", null);
				regForm.setActionName("");
				forward = "displayOwner";
			}
		}
		// ok action
		if (RegInitConstant.OK_ACTION.equals(actionName)) {
			request.setAttribute("roleId", regForm.getPartyType());
			if (!regForm.getPartyTypeTrns().equals(null) && !regForm.getPartyTypeTrns().equalsIgnoreCase(""))
				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
			else
				request.setAttribute("roleIdTrns", "0");
			regForm.setActionName(RegInitConstant.NO_ACTION);
			forward = "transactingParty";
		}
		// confirmation ok action
		if (RegInitConstant.CONFIRMATION_OK_ACTION.equals(actionName)) {
			regForm.setPartyModifyFlag(0);
			regForm.setExtraDetlsModifyFlag(0);
			String[] deedInstArray = commonBo.getDeedInstId(regForm.getHidnRegTxnId());
			if (deedInstArray != null && deedInstArray.length > 0) {
				request.setAttribute("deedId", deedInstArray[0].trim());
				request.setAttribute("instId", deedInstArray[1].trim());
				regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
				regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
			} else {
				request.setAttribute("deedId", 0);
				request.setAttribute("instId", 0);
				regForm.setDeedID(0);
				regForm.setInstID(0);
			}
			regForm.setActionName(RegInitConstant.NO_ACTION);
			forward = "reginitConfirm";
		}
		if (RegInitConstant.BACK_TO_REG_DISPLAY_ACTION.equals(actionName)) {
			commonBo.openPartyView(request, regForm, languageLocale);
			regForm.setPartyModifyFlag(0);
			regForm.setOwnerModifyFlag(0);
			commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
			commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
			commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
			regForm.setActionName(RegInitConstant.NO_ACTION);
			commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));// state
			// id
			// passed
			// is
			// for
			// MP
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			forward = "displayRegDetls";
		}
		// view from confirmation screen.
		/*
		 * if(request.getParameter("view")!=null){ String
		 * view=request.getParameter("view").toString();
		 * if(view.equalsIgnoreCase(RegInitConstant.APPLICANT_VIEW)){
		 * forward="applicantView"; }
		 * if(view.equalsIgnoreCase(RegInitConstant.TRANSACTING_PARTY_VIEW)){
		 * forward="transactingPartyView"; }
		 * if(view.equalsIgnoreCase(RegInitConstant.MAPPING_VIEW)){
		 * forward="mappingView"; }
		 * if(view.equalsIgnoreCase(RegInitConstant.PROPERTY_VIEW)){
		 * forward="propertyView"; } }
		 */
		// return from payment
		// String paymentTxnId=null;
		// String strNonJudECode=null;
		// String modName=null;
		if (request.getAttribute("noECode") != null) {
			String eCode = commonBo.getEstampCode(regForm.getHidnRegTxnId());
			if (eCode != null && !("").equalsIgnoreCase(eCode)) {
				regForm.setRegInitEstampCode(eCode);
				// regForm.setRegInitEstampCode(null);
				forward = "reginitConfirm";
			} else {
				PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
				String[] eStampDetsNew = commonBo.getEstampTxnId(regForm.getHidnRegTxnId());
				String downloadPath = proper.getValue("igrs_upload_path");
				String eStampPdfPath = "";
				String eStampDetsCode = eStampDetsNew[0];
				System.out.println("eStampDetsNew  0 " + eStampDetsNew[0]);
				System.out.println("eStampDetsNew  1 " + eStampDetsNew[1]);
				eStampPdfPath = downloadPath + File.separator + "IGRS" + File.separator + eStampDetsCode + File.separator + "EStamp.PDF";
				// String eStampDets=request.getAttribute("eCode").toString();
				// if(request.getAttribute("eStampPdfPath")!=null)
				// eStampPdfPath=request.getAttribute("eStampPdfPath").toString();
				// if(eStampDets!=null && !eStampDets.equalsIgnoreCase("") &&
				// !("").equalsIgnoreCase(eStampPdfPath)){
				// String[] eStampDetsArr=eStampDets.split("~");
				// insertion code
				boolean insertEstampMapDetls = false;
				String etxid = eStampDetsNew[1]; // added by siddhartha
				boolean check2 = commonBo.check2(etxid); //
				if (check2) {
					insertEstampMapDetls = commonBo.insertEstampMappingDetls(eStampDetsNew[1], regForm, eStampPdfPath);
				}
				if (insertEstampMapDetls) {
					// regForm.setRegInitEstampCode(eStampDetsArr[0].trim());
					// forward = "reginitConfirm";
					eCode = commonBo.getEstampCode(regForm.getHidnRegTxnId());
					if (eCode != null && !("").equalsIgnoreCase(eCode)) {
						regForm.setRegInitEstampCode(eCode);
						// regForm.setRegInitEstampCode(null);
						regForm.setDeedPageNo(commonBo.getMaxDeedPage(regForm.getDeedDraftId()));
						forward = "reginitConfirm";
					} else {
						regForm.setRegInitEstampCode(null);
						forward = "failure";
					}
				} else {
					regForm.setRegInitEstampCode(null);
					forward = "failure";
				}
				// forward = "reginitConfirm";
			}
			/*
			 * regForm.setRegInitEstampCode(eCode);
			 * regForm.setDeedPageNo(commonBo
			 * .getMaxDeedPage(regForm.getDeedDraftId())); forward =
			 * "reginitConfirm";
			 */
			forward = "reginitConfirm";
		}
		if (request.getParameter("paymentStatus") != null && request.getAttribute("eCode") == null) {
			if (regForm.getFromAdjudication() == 1) {
				// session.setAttribute("fnName","Adjudication");
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
				}
			} else {
				// session.setAttribute("fnName","Initiation");
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
				}
			}
			NumberFormat obj = new DecimalFormat("#0.00");
			double paidAmount = 0;
			if (request.getParameter("paymentStatus").equalsIgnoreCase("P")) {
				String[] deedInstArray = commonBo.getDeedInstId(regForm.getHidnRegTxnId());
				if (deedInstArray != null && deedInstArray.length > 0) {
					request.setAttribute("deedId", deedInstArray[0].trim());
					request.setAttribute("instId", deedInstArray[1].trim());
					regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
					regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
				} else {
					request.setAttribute("deedId", 0);
					request.setAttribute("instId", 0);
					regForm.setDeedID(0);
					regForm.setInstID(0);
				}
				// following added on 13feb for new payment modality.
				// String paymentStatus=request.getParameter("paymentStatus");
				// logger.debug("payment status---------->"+paymentStatus);
				// end of addition on 13feb for new payment modality.
				if (regForm.getPaymentType().equalsIgnoreCase("1")) {
					String[] paymentArray = commonBo.getPaymentTxnId(regForm.getHidnRegTxnId(), regForm.getPaymentType());
					if (paymentArray != null && paymentArray.length > 0) {
						paidAmount = Double.parseDouble(paymentArray[1].trim());
						regForm.setRegInitPaymntTxnId(paymentArray[0].trim());
						double finalPaid = Double.parseDouble(regForm.getTotalPaidAmount()) + paidAmount;
						regForm.setTotalPaidAmount(obj.format(finalPaid));
						double finalBalance = Double.parseDouble(regForm.getTotalPayableAmount()) - Double.parseDouble(regForm.getTotalPaidAmount());
						regForm.setTotalBalanceAmount(obj.format(finalBalance));
						if (paidAmount >= Double.parseDouble(paymentArray[2].trim()) || finalBalance <= 0) {
							// set p to c
							String param1[] = {RegInitConstant.PAYMENT_FLAG_COMPLETED, regForm.getHidnRegTxnId()};
							boolean updatePaymentStatus = commonBo.updatePaymentStatus(param1);
							logger.debug("payment status updated as c: " + updatePaymentStatus);
							if (updatePaymentStatus) {
								regForm.setPaymentCompleteFlag(1);
								// e stamping redirection at this point.
								request.setAttribute("eStampRegId", regForm.getHidnRegTxnId());
								request.setAttribute("eStampRegAmnt", regForm.getTotalPayableAmount());
								request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(regForm.getHidnRegTxnId()));
								request.setAttribute("eStampRegFuncId", "023");
								request.setAttribute("eStampRegPurpose", regForm.getPurpose());
								request.setAttribute("sourceModFlag", "PV");
								forward = "reginitEstamp";
								return mapping.findForward(forward);
							} else {
								forward = "failure";
								regForm.setRegInitEstampCode(null);
								return mapping.findForward(forward);
							}
						} else {
							regForm.setRegInitEstampCode(null);
						}
					}
					forward = "reginitConfirm";
				} else if (regForm.getPaymentType().equalsIgnoreCase("2")) {
					String[] paymentArray = commonBo.getPaymentTxnId(regForm.getHidnRegTxnId(), regForm.getPaymentType());
					if (paymentArray != null && paymentArray.length > 0) {
						paidAmount = Double.parseDouble(paymentArray[1].trim());
						regForm.setRegInitPaymntTxnId(paymentArray[0].trim());
						double finalPaid = Double.parseDouble(regForm.getTotalPaidAmountFee()) + paidAmount;
						regForm.setTotalPaidAmountFee(obj.format(finalPaid));
						double finalBalance = Double.parseDouble(regForm.getTotalPayableAmountFee()) - Double.parseDouble(regForm.getTotalPaidAmountFee());
						regForm.setTotalBalanceAmountFee(obj.format(finalBalance));
						if (paidAmount >= Double.parseDouble(paymentArray[2].trim()) || finalBalance <= 0) {
							regForm.setPaymentCompleteFlagFee(1);
							// e stamping redirection at this point.
							/*
							 * request.setAttribute("eStampRegId",
							 * regForm.getHidnRegTxnId());
							 * request.setAttribute("eStampRegAmnt",
							 * regForm.getTotalPayableAmount());
							 * request.setAttribute("eStampRegDistId",
							 * commonBo.getDistIdEstamp
							 * (regForm.getHidnRegTxnId()));
							 * request.setAttribute("eStampRegFuncId", "023");
							 * request.setAttribute("eStampRegPurpose",
							 * regForm.getPurpose());
							 * request.setAttribute("sourceModFlag", "PVN");
							 * forward = "reginitEstamp"; return
							 * mapping.findForward(forward);
							 */
							// below code hard coded
							/*
							 * boolean insertEstampMapDetls=false;
							 * insertEstampMapDetls
							 * =commonBo.insertEstampMappingDetls("111",
							 * regForm);
							 * 
							 * if(insertEstampMapDetls){
							 * regForm.setRegInitEstampCode("222"); forward =
							 * "reginitConfirm"; }else{ forward = "failure"; }
							 */
						}
					}
					forward = "regFeePage";
					// return after reg fee payment -- anuj
				} else {
					regForm.setRegInitEstampCode(null);
					String[] paymentArray = commonBo.getPaymentTxnId(regForm.getHidnRegTxnId(), regForm.getPaymentType());
					if (paymentArray != null && paymentArray.length > 0) {
						paidAmount = Double.parseDouble(paymentArray[1].trim());
						regForm.setRegInitPaymntTxnId(paymentArray[0].trim());
						double finalPaid = Double.parseDouble(regForm.getTotalPaidAmountAdjuFee()) + paidAmount;
						regForm.setTotalPaidAmountAdjuFee(obj.format(finalPaid));
						double finalBalance = Double.parseDouble(regForm.getTotalPayableAmountAdjuFee()) - Double.parseDouble(regForm.getTotalPaidAmountAdjuFee());
						regForm.setTotalBalanceAmountAdjuFee(obj.format(finalBalance));
						if (paidAmount >= Double.parseDouble(paymentArray[2].trim()) || finalBalance <= 0) {
							regForm.setPaymentCompleteFlagAdjuFee(1);
						}
					}
					forward = "reginitConfirm";
				}
			}
		}
		// following code will execute when flow returns from estamping
		if (request.getAttribute("eCode") != null) {
			String eCode = commonBo.getEstampCode(regForm.getHidnRegTxnId());
			if (eCode != null && !("").equalsIgnoreCase(eCode)) {
				regForm.setRegInitEstampCode(eCode);
				// added by Shreeraj
				regForm.setDeedPageNo(commonBo.getMaxDeedPage(regForm.getDeedDraftId()));
				forward = "reginitConfirm";
			} else {
				String eStampPdfPath = "";
				String eStampDets = request.getAttribute("eCode").toString();
				if (request.getAttribute("eStampPdfPath") != null) {
					eStampPdfPath = request.getAttribute("eStampPdfPath").toString();
				}
				if (eStampDets != null && !eStampDets.equalsIgnoreCase("") && !("").equalsIgnoreCase(eStampPdfPath)) {
					String[] eStampDetsArr = eStampDets.split("~");
					// insertion code
					String etxnId = eStampDetsArr[1].trim(); // // added by
					// siddhartha
					boolean check2 = commonBo.check2(etxnId); //
					boolean insertEstampMapDetls = false;
					if (check2) {
						insertEstampMapDetls = commonBo.insertEstampMappingDetls(eStampDetsArr[1].trim(), regForm, eStampPdfPath);
					}
					if (insertEstampMapDetls) {
						eCode = commonBo.getEstampCode(regForm.getHidnRegTxnId());
						if (eCode != null && !("").equalsIgnoreCase(eCode)) {
							regForm.setRegInitEstampCode(eCode);
							// regForm.setRegInitEstampCode(null);
							regForm.setDeedPageNo(commonBo.getMaxDeedPage(regForm.getDeedDraftId()));
							forward = "reginitConfirm";
						} else {
							regForm.setRegInitEstampCode(null);
							forward = "failure";
						}
					} else {
						regForm.setRegInitEstampCode(null);
						forward = "failure";
					}
				} else {
					regForm.setRegInitEstampCode(null);
					forward = "failure";
				}
			}
		}
		// ADDED BY SHREERAJ
		// following code will run when the deed draft is uploaded
		if (RegInitConstant.UPLOAD_DEED_DRAFT.equalsIgnoreCase(actionName)) {
			FormFile uploadFile = regForm.getDeedUploadPath();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			boolean ret = false;
			String path = pr.getValue("upload.location");
			// String deedName=pr.getValue("reg.deedDraftFileName");
			// String filePath =
			// path+regForm.getDeedId()+"/"+RegInitConstant.UPLOAD_NAME_SIGN_DOC;
			String filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/" + RegInitConstant.UPLOAD_DEED_UPLOADED + "/";
			String ext = getFileExtension((String) uploadFile.getFileName());
			if (uploadFile != null) {
				if ("pdf".equalsIgnoreCase(ext)) {
					ret = commonBo.generatePDF2(filePath, uploadFile.getFileData());
					if (ret) {
						regForm.setUpOrNot("1");
					} else {
						regForm.setUpOrNot("0");
					}
				} else if ("jpg".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext) || "tiff".equalsIgnoreCase(ext) || "bmp".equalsIgnoreCase(ext)) {
					ArrayList imageList = new ArrayList();
					imageList.add(uploadFile.getFileData());
					ret = commonBo.generatePDF(filePath, imageList);
					if (ret) {
						regForm.setUpOrNot("1");
					} else {
						regForm.setUpOrNot("0");
					}
				} else {
					regForm.setUpOrNot("0");
				}
			}
			if (ret) {
				logger.debug("Uploaded successfully");
				String deedName = pr.getValue("reg.deedDraftFileName");
				String filepath = path + RegInitConstant.FILE_UPLOAD_PATH + "/" + regForm.getHidnRegTxnId() + "/" + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/";
				byte arr[] = commonBo.mergeDeedPDF(filepath, response, deedName);
				if (arr != null) {
					logger.debug("Merged successfully");
					int noofPages = regForm.getConsenterNoPages();
					boolean boo = commonBo.updateDeedDetails(deedName, filepath, regForm.getHidnRegTxnId(), noofPages);
					if (boo) {
						regForm.setUpOrNot("1");
						logger.debug("ALL THE UPLOAD PATH AND NAMES ARE STORED IN DB!!!!!!!!!!!!!!!UPDATION SUCCESSFULL");
					} else {
						regForm.setUpOrNot("0");
						logger.debug("ALL THE UPLOAD PATH AND NAMES ARE NOT STORED IN DB!!!!!!!!!!!!!!!UPDATION FAILURE");
					}
					regForm.setDeedContents(arr);
				} else {
					if (languageLocale.equalsIgnoreCase("en"))
						request.setAttribute(RegInitConstant.FAILURE_MSG, "The deed could not be merged");
					else
						request.setAttribute(RegInitConstant.FAILURE_MSG, "??????????????? ???????????? ???????????? ?????? ???????????? ?????????");
					regForm.setUpOrNot("0");
					logger.debug("Merging Failure");
				}
			} else {
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.FAILURE_MSG, "The deed could not be uploaded");
				else
					request.setAttribute(RegInitConstant.FAILURE_MSG, "??????????????? ??????????????? ???????????? ?????? ???????????? ?????????");
				regForm.setUpOrNot("0");
				logger.debug("Upload Failure");
			}
			logger.debug("Uploaded successfully");
			regForm.setActionName("");
			regForm.setUploadCheck("0");
			forward = "reginitConfirm";
		}
		// ADDED BY SHREERAJ
		// following code will run when the document with consenter details is
		// downloaded
		if (RegInitConstant.PRINT_CONSENTER_PDF.equalsIgnoreCase(actionName)) {
			boolean pdfFlag = false;
			/*
			 * PropertiesFileReader pr =
			 * PropertiesFileReader.getInstance("resources.igrs"); String
			 * filepath="";
			 * 
			 * if(languageLocale.equalsIgnoreCase("en"))
			 * filepath=pr.getValue("pdf.location"
			 * )+RegInitConstant.PRINT_CONSENTER_PDF_ENGLISH_JRXML; else
			 * filepath=pr.getValue("pdf.location")+RegInitConstant.
			 * PRINT_CONSENTER_PDF_HINDI_JRXML;
			 */
			// pdfFlag=commonBo.generateConsenterPDF(filepath,response,regForm);
			pdfFlag = commonBo.generateGraphicsConsenterPDF(response, regForm, languageLocale);
			int noOfPages = regForm.getConsenterNoPages();
			regForm.setConsUploadFlag("1");// uploads are present
			regForm.setConsenterUploadPage(1);
			if (pdfFlag) {
			} else {
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.FAILURE_MSG, "The document could not be downloaded");
				else
					request.setAttribute(RegInitConstant.FAILURE_MSG, "???????????????????????? ????????????????????? ???????????? ?????? ???????????? ?????????");
			}
			regForm.setActionName("");
			forward = "reginitConfirm";
		}
		if (RegInitConstant.UPLOAD_CONSENTER.equalsIgnoreCase(actionName)) {
			regForm.setIsDashboardFlag(3);
			regForm.setConsUploadFlag("1");// uploads are present
			regForm.setConsenterUploadPage(1);
			regForm.setConsBrowseFlag("Y");
			regForm.setActionName("");
			regForm.setConsenterUploadList(new ArrayList());
			forward = "reginitConfirm";
		}
		if (RegInitConstant.UPLOAD_CONSENTER_DOC.equalsIgnoreCase(actionName)) {
			FormFile uploadFile = regForm.getConsenterUploadPath();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			boolean ret = false;
			String path = pr.getValue("upload.location");
			System.out.println(regForm.getIndexCons());
			String filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/" + RegInitConstant.CONSENTER_PAGE + regForm.getConsenterUploadPage() + ".pdf";
			String ext = getFileExtension((String) uploadFile.getFileName());
			if (uploadFile != null) {
				if ("jpg".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext) || "tiff".equalsIgnoreCase(ext) || "bmp".equalsIgnoreCase(ext)) {
					ArrayList imageList = new ArrayList();
					imageList.add(uploadFile.getFileData());
					ret = commonBo.generatePDF(filePath, imageList);
					if (ret) {
						regForm.setUpOrNot("1");
					} else {
						regForm.setUpOrNot("0");
					}
				} else {
					regForm.setFinalPage("N");
					if (languageLocale.equalsIgnoreCase("en"))
						request.setAttribute(RegInitConstant.FAILURE_MSG, ext + " file cannot be uploaded. Kindly upload JPEG file");
					else
						request.setAttribute(RegInitConstant.FAILURE_MSG, "??????????????? ??????????????? ???????????? ?????? ???????????? ?????????");
				}
			}
			if (ret) {
				logger.debug("Uploaded successfully");
				regForm.setConsUploadSucc("Y");
				if (regForm.getConsDeleteFlag() != null && regForm.getConsDeleteFlag().equalsIgnoreCase("Y")) {
					ArrayList list = regForm.getConsenterUploadList();
					RegCommonForm regF = new RegCommonForm();
					regF.setConsenterPages(regForm.getConsenterUploadPage());
					list.add(regF);
					regForm.setConsenterUploadList(list);
					if (regForm.getConsenterNoPages() == list.size()) {
						regForm.setConsBrowseFlag("N");
						regForm.setMergeSuccess("Y");
					}
					if (list.size() == 0) {
						regForm.setConsenterUploadPage(1);
					} else {
						// regForm.setConsenterUploadPage(regForm.getConsenterUploadPage()+1);
						for (int i = 1; i <= regForm.getConsenterNoPages(); i++) {
							boolean flag = false;
							for (int j = 0; j < list.size(); j++) {
								RegCommonForm rForm = (RegCommonForm) list.get(j);
								int pageNo = rForm.getConsenterPages();
								if (i == pageNo)
									flag = true;
							}
							if (flag == false) {
								regForm.setConsenterUploadPage(i);
								break;
							}
						}
					}
				} else {
					regForm.setConsenterUploadPage(regForm.getConsenterUploadPage() + 1);
					ArrayList uploadList = new ArrayList();
					for (int i = 1; i < regForm.getConsenterUploadPage(); i++) {
						RegCommonForm regF = new RegCommonForm();
						regF.setConsenterPages(i);
						uploadList.add(regF);
						if (regForm.getConsenterNoPages() + 1 == regForm.getConsenterUploadPage()) {
							regForm.setConsBrowseFlag("N");
							regForm.setMergeSuccess("Y");
						}
					}
					regForm.setConsenterUploadList(uploadList);
				}
			} else {
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.FAILURE_MSG, "The document could not be uploaded.Kindly upload JPEG file");
				else
					request.setAttribute(RegInitConstant.FAILURE_MSG, "??????????????? ???????????? ???????????? ?????? ???????????? ?????????");
				regForm.setUpOrNot("0");
				logger.debug("Upload Failure PAGE NUMBER:::::" + regForm.getConsenterUploadPage());
			}
			regForm.setActionName("");
			forward = "reginitConfirm";
		}
		if (RegInitConstant.DELETE_CONSENTER_DOC.equalsIgnoreCase(actionName)) {
			String deletePageNo = regForm.getIndexCons();
			ArrayList arr = regForm.getConsenterUploadList();
			for (int i = 0; i < arr.size(); i++) {
				RegCommonForm rForm = (RegCommonForm) arr.get(i);
				if (rForm.getConsenterPages() == Integer.parseInt(deletePageNo))
					arr.remove(i);
			}
			regForm.setConsenterUploadList(arr);
			regForm.setConsBrowseFlag("Y");
			regForm.setConsDeleteFlag("Y");
			regForm.setMergeSuccess("");
			ArrayList list = regForm.getConsenterUploadList();
			for (int i = 1; i <= regForm.getConsenterNoPages(); i++) {
				boolean flag = false;
				for (int j = 0; j < list.size(); j++) {
					RegCommonForm rForm = (RegCommonForm) list.get(j);
					int pageNo = rForm.getConsenterPages();
					if (i == pageNo)
						flag = true;
				}
				if (flag == false) {
					regForm.setConsenterUploadPage(i);
					break;
				}
			}
			regForm.setActionName("");
			if (arr.size() == 0) {
				regForm.setConsenterUploadPage(1);
			}
			regForm.setActionName("");
			forward = "reginitConfirm";
		}
		if (RegInitConstant.MERGE_CONSENTER_DEEDDRAFT.equalsIgnoreCase(actionName)) {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			String path = pr.getValue("upload.location");
			String deedName = pr.getValue("reg.deedDraftFileName");
			String filepath = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/";
			byte arr[] = commonBo.mergeConsenterDeedPDF(filepath, response, regForm.getConsenterNoPages(), deedName);
			if (arr != null) {
				logger.debug("Merged successfully");
				int noofPages = regForm.getConsenterNoPages();
				boolean boo = commonBo.updateDeedDetails(deedName, filepath, regForm.getHidnRegTxnId(), noofPages);
				if (boo) {
					regForm.setUpOrNot("1");
					regForm.setFinalPage("Y");
					logger.debug("ALL THE UPLOAD PATH AND NAMES ARE STORED IN DB!!!!!!!!!!!!!!!UPDATION SUCCESSFULL");
				} else {
					regForm.setUpOrNot("0");
					regForm.setFinalPage("N");
					logger.debug("ALL THE UPLOAD PATH AND NAMES ARE NOT STORED IN DB!!!!!!!!!!!!!!!UPDATION FAILURE");
				}
				regForm.setDeedContents(arr);
				regForm.setMergeSuccess("merged");
				regForm.setConsUploadFlag("2");
				regForm.setConsUploadSucc("N");
				regForm.setConsBrowseFlag("N");
			} else {
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.FAILURE_MSG, "The deed could not be merged");
				else
					request.setAttribute(RegInitConstant.FAILURE_MSG, "??????????????? ???????????? ???????????? ?????? ???????????? ?????????");
				regForm.setUpOrNot("0");
				logger.debug("Merging Failure");
			}
			regForm.setActionName("");
			forward = "reginitConfirm";
		}
		if (RegInitConstant.ADD_UPLOADS_AGAIN.equalsIgnoreCase(actionName)) {
			regForm.setConsenterUploadList(new ArrayList());
			int noOfPages = regForm.getConsenterNoPages();
			regForm.setConsUploadFlag("1");// uploads are present
			regForm.setConsBrowseFlag("Y");
			regForm.setMergeSuccess("N");
			regForm.setFinalPage("N");
			regForm.setConsenterUploadPage(1);
			regForm.setActionName("");
			forward = "reginitConfirm";
		}
		String viewUpload = (String) request.getParameter("fwdName");
		if (viewUpload != null && "viewUpload".equalsIgnoreCase(viewUpload)) {
			try {
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				String path = pr.getValue("upload.location");
				ArrayList deedDocList = commonBo.getDeedDocDetails(regForm.getHidnRegTxnId());
				for (int i = 0; i < deedDocList.size(); i++) {
					ArrayList subList = (ArrayList) deedDocList.get(i);
					String deedDocPath = (String) subList.get(1);
					String deedDocName = (String) subList.get(0);
					if (deedDocPath == null || deedDocPath.equals("")) {
						regForm.setDeedDocPath("");
					} else {
						regForm.setDeedDocName(deedDocName);
						regForm.setDeedDocPath(deedDocPath);
					}
				}
				String filename = regForm.getDeedDocPath();
				// String deedName=pr.getValue("reg.deedDraftFileName");
				// String filename =
				// path+RegInitConstant.FILE_UPLOAD_PATH+"/"+regForm.getHidnRegTxnId()+"/"+RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT+"/"+deedName;
				byte[] arr = DMSUtility.getDocumentBytes(filename);
				DMSUtility.downloadDocument(response, filename, arr);
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
			forward = "reginitConfirm";
		}
		if (viewUpload != null && "viewUploadCons".equalsIgnoreCase(viewUpload)) {
			try {
				String pageConsNo = (String) request.getParameter("PageConsNo");
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				String path = pr.getValue("upload.location");
				String deedName = pr.getValue("reg.deedDraftFileName");
				String filepath = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/" + RegInitConstant.CONSENTER_PAGE + pageConsNo + ".pdf";
				byte[] arr = DMSUtility.getDocumentBytes(filepath);
				DMSUtility.downloadDocument(response, RegInitConstant.CONSENTER_PAGE + pageConsNo + ".pdf", arr);
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
			forward = "reginitConfirm";
		}
		// final action after payment.
		if (RegInitConstant.FINAL_ACTION.equals(actionName)) {
			try {
				commonDto.setIndcountry(commonBo.getCountry(languageLocale));
				commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
				commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
				regForm.setPostalCountry("1");
				regForm.setPostalState("");
				regForm.setPostalDistrict("");
				regForm.setHdnPostalAddress1(regForm.getPostalAddress1());
				// change payment flag to c here.
				if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
					boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
					if (updatePurpose) {
						logger.debug("purpose updated");
						// forward="reginitPayment";
					} else {
						logger.debug("purpose not updated");
						// forward="failure";
					}
				}
				String currDate = commonBo.getCurrDateTime();
				logger.debug("curr date 2 : " + currDate);
				regForm.setCurrDateTime(currDate);
				String msg = "";
				// added by Anuj for ref_gee_pay_check checkbox
				String regFeeCheck = (String) regForm.getCheckRegFeeBox();
				if ("on".equalsIgnoreCase(regFeeCheck)) {
					regFeeCheck = "Y";
				} else {
					// regFeeCheck = "N";
					regFeeCheck = "Y"; // changed by akansha for reg fee
					// mandatory
				}
				if (Double.parseDouble(regForm.getTotalduty()) > 0) {
					String param1[] = {RegInitConstant.PAYMENT_FLAG_COMPLETED, regForm.getHidnRegTxnId()};
					boolean updatePaymentStatus = commonBo.insertTxnDetails(param1, regFeeCheck);
					logger.debug("payment status updated as c: " + updatePaymentStatus);
					if (updatePaymentStatus)
						msg = regForm.getHidnRegTxnId().toString();
					else
						msg = RegInitConstant.ERROR_MSG;
				} else {
					boolean updatePaymentStatus = commonBo.insertTxnDetailsFinalActionWithRegFee(RegInitConstant.STATUS_FINAL_SCREEN, regForm.getHidnRegTxnId(), regFeeCheck);
					logger.debug("payment status updated as c: " + updatePaymentStatus);
					if (updatePaymentStatus)
						msg = regForm.getHidnRegTxnId().toString();
					else
						msg = RegInitConstant.ERROR_MSG;
					// msg=regForm.getHidnRegTxnId().toString();
				}
				// added by saurav for rcms fee payment
				String rcmsPayCheck = commonBo.rcmsCheckCyber(regForm.getHidnRegTxnId());
				if ("Y".equalsIgnoreCase(rcmsPayCheck)) {
					// checking whether form details are saved or not.

					boolean saved = commonBo.getFormOneInsertStatus(regForm.getHidnRegTxnId(), "FORM_VIA");
					if (!saved) {
						String userDetail = commonBo.getUserDetail(userId);
						regForm.setUserName(userDetail+", ");
						regForm.setFormA1Consent("");
						regForm.setFormA1SPConsent("");
						ArrayList fDto = commonBo.getFormData("FORM_VIA");
						ArrayList list1 = new ArrayList();
						// regForm.setCourtDetail(list1);
						regForm.setCaseName(new String[]{""});
						regForm.setCaseCase(new String[]{""});
						regForm.setCaseAdress(new String[]{""});
						regForm.setUserEnterableOne("");
						regForm.setUserEnterableThree("");
						regForm.setUserEnterableTwo("");
						regForm.setOrderdateone("");
						regForm.setOrderdatetwo("");
						regForm.setOrderdatethree("");
						regForm.setEncumberanceData("");
						regForm.setEncumberanceDate("");
						regForm.setFormOne(fDto);
						regForm.setSellerInfo(commonBo.getSellerDetail(regForm.getHidnRegTxnId(), languageLocale));
						regForm.setCourtNameList(new ArrayList<String>());
						regForm.setCaseNumberList(new ArrayList<String>());
						regForm.setNameAndAddressOfPartyList(new ArrayList<String>());
						regForm.setFormName("FORM_VIA");
					} else {
						saved = false;
						saved = commonBo.getFormOneInsertStatus(regForm.getHidnRegTxnId(), "FORM_VIB");
						if (!saved) {
							ArrayList fDto = commonBo.getFormData("FORM_VIB");
							String userDetail = commonBo.getUserDetail(userId);
							regForm.setUserName(userDetail+", ");
							regForm.setFormA2SPConsent("");
							regForm.setFormA2Consent("");
							regForm.setBuyerInfo(commonBo.getBuyerDetail(regForm.getHidnRegTxnId(), languageLocale));
							regForm.setSellerInfo(commonBo.getSellerDetail(regForm.getHidnRegTxnId(), languageLocale));
							regForm.setPropInfo(commonBo.getPropDetail(regForm.getHidnRegTxnId(), languageLocale));
							regForm.setFormOne(fDto);
							regForm.setCyberFormName("FORM_VIB");
						} else {
							regForm.setCyberFormName("FORM_CHECKED");
							boolean isFormViAPrsnt = false;
							if (!isFormViAPrsnt) {
								 commonBo.generateFormVIAPDF(regForm.getHidnRegTxnId(),languageLocale);
							}

							boolean isFormViBPrsnt = false;
							if (!isFormViAPrsnt) {
								 commonBo.generateFormVIBPDF(regForm.getHidnRegTxnId(),languageLocale);
							}

							//regForm.setPartyDetail(commonBo.getPartyDetails(regForm.getHidnRegTxnId(), languageLocale));
							if (null != regForm.getFormOne()) {
								regForm.getFormOne().clear();
							}
						}
					}
					// String[] selectedId = {"1001", "1005"};
					// regForm.setTestSelection(selectedId);
					regForm.setMutationFeeCheck(rcmsPayCheck);
					String distictProp = commonBo.distinctTehCount(regForm.getHidnRegTxnId());
					String perCaseFee = commonBo.getRCMSFee();
					int totalAmount =  Integer.parseInt(perCaseFee);
					regForm.setTotalMutationFees(String.valueOf(totalAmount));
					String checkPayment = commonBo.getRcmsFeeStatus(regForm.getHidnRegTxnId());
					int balanceAmount = totalAmount - (Integer.parseInt(checkPayment));
					regForm.setBalanceLeftMutation(String.valueOf(balanceAmount));
					regForm.setTotalPaidMutationFees(checkPayment);
				}else if(commonBd.rcmsCheck(regForm.getHidnRegTxnId()).equalsIgnoreCase("Y")){
					regForm.setCyberFormName("FORM_CHECKED");
					regForm.setMutationFeeCheck(rcmsPayCheck);
					
                		regForm.setFormA1("");
						regForm.setFormA2("");
                	
					String distictProp = commonBo.distinctTehCount(regForm.getHidnRegTxnId());
					String perCaseFee = commonBo.getRCMSFee();
					int totalAmount = 1 * Integer.parseInt(perCaseFee);
					regForm.setTotalMutationFees(String.valueOf(totalAmount));
					String checkPayment = commonBo.getRcmsFeeStatus(regForm.getHidnRegTxnId());
					int balanceAmount = totalAmount - (Integer.parseInt(checkPayment));
					regForm.setBalanceLeftMutation(String.valueOf(balanceAmount));
					regForm.setTotalPaidMutationFees(checkPayment);
					if (checkPayment.equalsIgnoreCase(String.valueOf(totalAmount))) {
						if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
							forward = "success1";
						} else {
							forward = "dashboardLandSuccess";
						}
					}
					forward = "mutationFeePage";
				} else {
					regForm.setMutationFeeCheck(rcmsPayCheck);
				}
				regForm.setActionName(RegInitConstant.NO_ACTION);
				request.setAttribute("regInitTxnId", regForm.getHidnRegTxnId());
				if ("Y".equalsIgnoreCase(regFeeCheck)) {
					RegCommonBO bo = new RegCommonBO();
					bo.getPaymentAmounts(regForm);
					forward = "regFeePage";
				} else {
					forward = "success1";
				}
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
				forward = "failure";
			}
		}

		if ("test_selected_id".equals(actionName)) {
			String[] selectedId = regForm.getTestSelection();
			if (null != selectedId) {
				for (int i = 0; i < selectedId.length; i++) {
					String temp = selectedId[i];
					System.out.println(temp);
				}
			}
			String[] sss = regForm.getSelectedDeclation();
			for (int k1 = 0; k1 < sss.length; k1++) {
				System.out.println(sss[k1]);
			}
			System.out.println(regForm.getUserEnterableOne());
			System.out.println(regForm.getUserEnterableTwo());
			System.out.println(regForm.getUserEnterableThree());
			System.out.println(regForm.getOrderdateone());
			System.out.println(regForm.getOrderdatetwo());
			System.out.println(regForm.getOrderdatethree());
			String[] cname = regForm.getCaseName();
			String[] cname1 = regForm.getCaseCase();
			String[] cname2 = regForm.getCaseAdress();
			//regForm.getFormA1Consent();
			String formTpeName = regForm.getCyberFormName();
			// String[] fieldValue =
			// request.getParameterValues("userEnterableFieldValue");
			ArrayList<FormOneDTO> fdtoList = regForm.getFormOne();
			for (int i = 0; i < fdtoList.size(); i++) {
				FormOneDTO fdto = fdtoList.get(i);
				ArrayList<DeclarationDTO> decdtolist = fdto.getDeclaration();
				for (int j = 0; j < decdtolist.size(); j++) {
					DeclarationDTO decDto = decdtolist.get(j);
					if ("Y".equals(decDto.getUserEntrableFlag())) {
						ArrayList<FormUserEnterableDTO> userdtolist = decDto.getUserEnterableData();
						for (int k = 0; k < userdtolist.size(); k++) {
							FormUserEnterableDTO userDto = userdtolist.get(k);
							System.out.println(userDto.getUserEnterableFieldNameEnglish());
							// System.out.println(userDto.getValueEntered());
							System.out.println(userDto.getUserEnterableId());
							if (userDto.getUserEnterableId().equals("2000")) {
								userDto.setValueEntered(regForm.getUserEnterableOne());
							} else if (userDto.getUserEnterableId().equals("2001")) {
								userDto.setValueEntered(regForm.getOrderdateone());
							} else if (userDto.getUserEnterableId().equals("2002")) {
								userDto.setValueEntered(regForm.getUserEnterableThree());
							} else if (userDto.getUserEnterableId().equals("2003")) {
								userDto.setValueEntered(regForm.getOrderdatethree());
							} else if (userDto.getUserEnterableId().equals("2004")) {
								// userDto.setValueEntered(regForm.getCourtName());
							} else if (userDto.getUserEnterableId().equals("2005")) {
								// userDto.setValueEntered(regForm.getCaseNumber());
							} else if (userDto.getUserEnterableId().equals("2006")) {
								// userDto.setValueEntered(regForm.getNameAndAddressOfParty());
							} else if (userDto.getUserEnterableId().equals("2007")) {
								userDto.setValueEntered(regForm.getEncumberanceData());
							} else if (userDto.getUserEnterableId().equals("2008")) {
								userDto.setValueEntered(regForm.getUserEnterableTwo());
							} else if (userDto.getUserEnterableId().equals("2009")) {
								userDto.setValueEntered(regForm.getOrderdatetwo());
							} else if (userDto.getUserEnterableId().equals("2010")) {
								userDto.setValueEntered(regForm.getEncumberanceDate());
							}
							System.out.println(userDto.getValueEntered());
						}
					}
				}
			}
			ArrayList courtData = new ArrayList();
			// courtData.
			courtData.add(0, cname);
			courtData.add(1, cname1);
			courtData.add(2, cname2);
			String star[] = request.getParameterValues("adharData");
			boolean resp = false;
			if ("FORM_VIA".equalsIgnoreCase(formTpeName)) {
				resp = commonBo.insertFormOneData(star, fdtoList, sss, regForm.getHidnRegTxnId(), userId, formTpeName, regForm.getSellerInfo(), courtData);
				if (resp) {
					resp = commonBo.updateconsent(regForm.getFormA1Consent(),regForm.getFormA1SPConsent(), regForm.getHidnRegTxnId(),"A1", userId);
					if (null != regForm.getFormOne()) {
						regForm.getFormOne().clear();
					}
					ArrayList fDto = commonBo.getFormData("FORM_VIB");
					regForm.setFormOne(fDto);
					regForm.setBuyerInfo(commonBo.getBuyerDetail(regForm.getHidnRegTxnId(), languageLocale));
					regForm.setSellerInfo(commonBo.getSellerDetail(regForm.getHidnRegTxnId(), languageLocale));
					regForm.setPropInfo(commonBo.getPropDetail(regForm.getHidnRegTxnId(), languageLocale));
					regForm.setUserEnterableOne("");
					regForm.setUserEnterableThree("");
					regForm.setUserEnterableTwo("");
					regForm.setOrderdateone("");
					regForm.setOrderdatetwo("");
					regForm.setOrderdatethree("");
					regForm.setEncumberanceData("");
					regForm.setEncumberanceDate("");
					regForm.setCaseName(new String[]{""});
					regForm.setCaseCase(new String[]{""});
					regForm.setCaseAdress(new String[]{""});
					// regForm.setTest
					regForm.setCourtNameList(new ArrayList<String>());
					regForm.setCaseNumberList(new ArrayList<String>());
					regForm.setNameAndAddressOfPartyList(new ArrayList<String>());
					regForm.setCyberFormName("FORM_VIB");
				} else {
					regForm.setSellerInfo(commonBo.getSellerDetail(regForm.getHidnRegTxnId(), languageLocale));
					regForm.setCyberFormName("FORM_VIA");
				}
			} else if ("FORM_VIB".equalsIgnoreCase(formTpeName)) {
				resp = commonBo.insertFormTwoData(star, fdtoList, sss, regForm.getHidnRegTxnId(), userId, formTpeName, regForm.getBuyerInfo());
				if (resp) {
					resp = commonBo.updateconsent(regForm.getFormA2Consent(),regForm.getFormA2SPConsent(), regForm.getHidnRegTxnId(),"A2", userId);
					if (null != regForm.getFormOne()) {
						regForm.getFormOne().clear();
					}
					regForm.setUserEnterableOne("");
					regForm.setUserEnterableThree("");
					regForm.setUserEnterableTwo("");
					regForm.setOrderdateone("");
					regForm.setOrderdatetwo("");
					regForm.setOrderdatethree("");
					regForm.setEncumberanceData("");
					regForm.setEncumberanceDate("");
					regForm.setCyberFormName("FORM_CHECKED");

				} else {
					regForm.setCyberFormName("FORM_VIB");
				}
			}

			/*
			 * for (int i = 0; i < fieldValue.length; i++) { String temp =
			 * fieldValue[i]; System.out.println(temp); }
			 */
			forward = "mutationFeePage";
		}
		// final action after payment.
		if (RegInitConstant.FINAL_ACTION_FEE.equals(actionName)) {
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
			regForm.setPostalCountry("1");
			regForm.setPostalState("");
			regForm.setPostalDistrict("");
			regForm.setHdnPostalAddress1(regForm.getPostalAddress1());
			// change payment flag to c here.
			String currDate = commonBo.getCurrDateTime();
			System.out.println("curr date 2 : " + currDate);
			regForm.setCurrDateTime(currDate);
			String msg = "";
			String param1[] = {RegInitConstant.PAYMENT_FLAG_COMPLETED, regForm.getHidnRegTxnId()};
			boolean updatePaymentStatus = commonBo.insertTxnDetailsFee(param1);
			logger.debug("fee payment status updated as c: " + updatePaymentStatus);
			if (updatePaymentStatus)
				msg = regForm.getHidnRegTxnId().toString();
			else
				msg = RegInitConstant.ERROR_MSG;
			// added by saurav for rcms fee payment
			String rcmsPayCheck = commonBo.rcmsCheckCyber(regForm.getHidnRegTxnId());
			if ("Y".equalsIgnoreCase(rcmsPayCheck)) {
				// checking whether form details are saved or not.
				boolean saved = commonBo.getFormOneInsertStatus(regForm.getHidnRegTxnId(), "FORM_VIA");
				if (!saved) {
					String userDetail = commonBo.getUserDetail(userId);
					regForm.setUserName(userDetail+", ");
					regForm.setFormA1Consent("");
					regForm.setFormA1SPConsent("");
					ArrayList fDto = commonBo.getFormData("FORM_VIA");
					ArrayList list1 = new ArrayList();
					// regForm.setCourtDetail(list1);
					regForm.setCaseName(new String[]{""});
					regForm.setCaseCase(new String[]{""});
					regForm.setCaseAdress(new String[]{""});
					regForm.setUserEnterableOne("");
					regForm.setUserEnterableThree("");
					regForm.setUserEnterableTwo("");
					regForm.setOrderdateone("");
					regForm.setOrderdatetwo("");
					regForm.setOrderdatethree("");
					regForm.setEncumberanceData("");
					regForm.setEncumberanceDate("");
					regForm.setCourtNameList(new ArrayList<String>());
					regForm.setCaseNumberList(new ArrayList<String>());
					regForm.setNameAndAddressOfPartyList(new ArrayList<String>());
					regForm.setSellerInfo(commonBo.getSellerDetail(regForm.getHidnRegTxnId(), languageLocale));
					regForm.setFormOne(fDto);
					regForm.setCyberFormName("FORM_VIA");
				} else {
					saved = false;
					saved = commonBo.getFormOneInsertStatus(regForm.getHidnRegTxnId(), "FORM_VIB");
					if (!saved) {
						String userDetail = commonBo.getUserDetail(userId);
						regForm.setUserName(userDetail+", ");
						regForm.setFormA2SPConsent("");
						regForm.setFormA2Consent("");
						ArrayList fDto = commonBo.getFormData("FORM_VIB");
						regForm.setBuyerInfo(commonBo.getBuyerDetail(regForm.getHidnRegTxnId(), languageLocale));
						regForm.setSellerInfo(commonBo.getSellerDetail(regForm.getHidnRegTxnId(), languageLocale));
						regForm.setPropInfo(commonBo.getPropDetail(regForm.getHidnRegTxnId(), languageLocale));
						regForm.setFormOne(fDto);
						regForm.setCyberFormName("FORM_VIB");
					} else {
						regForm.setCyberFormName("FORM_CHECKED");
						commonBo.generateFormVIAPDF(regForm.getHidnRegTxnId(),languageLocale);
						commonBo.generateFormVIBPDF(regForm.getHidnRegTxnId(),languageLocale);
						if (null != regForm.getFormOne()) {
							regForm.getFormOne().clear();
						}
						
					}
				}

				// String[] selectedId = {"1001", "1005"};
				// regForm.setTestSelection(selectedId);
				ArrayList cyberformlist = chkbd.getCyberTehsilFormDetails(regForm.getHidnRegTxnId());
				if(cyberformlist.size() != 0){
            		for(int i = 0 ; i < cyberformlist.size() ;i++)
					{
						ArrayList subList = (ArrayList)cyberformlist.get(i);
						regForm.setFormA1((String)subList.get(1));
						regForm.setFormA2((String)subList.get(2));
					}
            	}else{
            		regForm.setFormA1("");
					regForm.setFormA2("");
            	}
				regForm.setMutationFeeCheck(rcmsPayCheck);
				String distictProp = commonBo.distinctTehCount(regForm.getHidnRegTxnId());
				String perCaseFee = commonBo.getRCMSFee();
				int totalAmount = 1 * Integer.parseInt(perCaseFee);
				regForm.setTotalMutationFees(String.valueOf(totalAmount));
				String checkPayment = commonBo.getRcmsFeeStatus(regForm.getHidnRegTxnId());
				int balanceAmount = totalAmount - (Integer.parseInt(checkPayment));
				regForm.setTotalPaidAmountFee(String.valueOf(balanceAmount));
				regForm.setBalanceLeftMutation(String.valueOf(balanceAmount));
				System.out.println("balance left amount --> "+regForm.getBalanceLeftMutation());
				regForm.setTotalPaidMutationFees(checkPayment);
				forward = "mutationFeePage";
			} else if(commonBd.rcmsCheck(regForm.getHidnRegTxnId()).equalsIgnoreCase("Y")){
				regForm.setCyberFormName("FORM_CHECKED");
				regForm.setMutationFeeCheck(rcmsPayCheck);
				
            		regForm.setFormA1("");
					regForm.setFormA2("");
            	
				String distictProp = commonBo.distinctTehCount(regForm.getHidnRegTxnId());
				String perCaseFee = commonBo.getRCMSFee();
				int totalAmount = 1 * Integer.parseInt(perCaseFee);
				regForm.setTotalMutationFees(String.valueOf(totalAmount));
				String checkPayment = commonBo.getRcmsFeeStatus(regForm.getHidnRegTxnId());
				int balanceAmount = totalAmount - (Integer.parseInt(checkPayment));
				regForm.setBalanceLeftMutation(String.valueOf(balanceAmount));
				regForm.setTotalPaidMutationFees(checkPayment);
				if (checkPayment.equalsIgnoreCase(String.valueOf(totalAmount))) {
					if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
						forward = "success1";
					} else {
						forward = "dashboardLandSuccess";
					}
				}
				forward = "mutationFeePage";
			}else {
				regForm.setMutationFeeCheck(rcmsPayCheck);
				forward = "success1";
			}
			regForm.setActionName(RegInitConstant.NO_ACTION);
			request.setAttribute("regInitTxnId", regForm.getHidnRegTxnId());
			// forward = "success1";
		}
		// final action after mutaion fee payment.
		if (RegInitConstant.FINAL_ACTION_MUTATION_FEE.equals(actionName)) {
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
			regForm.setPostalCountry("1");
			regForm.setPostalState("");
			regForm.setPostalDistrict("");
			regForm.setHdnPostalAddress1(regForm.getPostalAddress1());
			// change payment flag to c here.
			String currDate = commonBo.getCurrDateTime();
			System.out.println("curr date 2 : " + currDate);
			regForm.setCurrDateTime(currDate);
			String msg = "";
			String param1[] = {RegInitConstant.PAYMENT_FLAG_COMPLETED, regForm.getHidnRegTxnId()};
			//boolean updatePaymentStatus = commonBo.insertTxnDetailsFee(param1);
			//logger.debug("fee payment status updated as c: " + updatePaymentStatus);

				boolean isFormViAPrsnt = commonBo.checkFormVIAPresnt(regForm.getHidnRegTxnId());
				isFormViAPrsnt=false;
				if (!isFormViAPrsnt) {
					 commonBo.generateFormVIAPDF(regForm.getHidnRegTxnId(),languageLocale);
				}

				boolean isFormViBPrsnt = commonBo.checkFormVIBPresnt(regForm.getHidnRegTxnId());
				isFormViBPrsnt=false;
				if (!isFormViAPrsnt) {
					 commonBo.generateFormVIBPDF(regForm.getHidnRegTxnId(),languageLocale);
				}

				//regForm.setPartyDetail(commonBo.getPartyDetails(regForm.getHidnRegTxnId(), languageLocale));
				forward = "success1";
			
			// added by saurav for rcms fee payment
			/*
			 * String rcmsPayCheck =
			 * commonBo.rcmsCheck(regForm.getHidnRegTxnId());
			 * if("Y".equalsIgnoreCase(rcmsPayCheck)){
			 * regForm.setMutationFeeCheck(rcmsPayCheck); String distictProp =
			 * commonBo.distinctTehCount(regForm.getHidnRegTxnId()); String
			 * perCaseFee = commonBo.getRCMSFee(); int totalAmount =
			 * Integer.parseInt(distictProp)*Integer.parseInt(perCaseFee);
			 * regForm.setTotalMutationFees(String.valueOf(totalAmount)); String
			 * checkPayment =
			 * commonBo.getRcmsFeeStatus(regForm.getHidnRegTxnId()); forward =
			 * "mutationFeePage"; }else{
			 * regForm.setMutationFeeCheck(rcmsPayCheck); forward = "success1";
			 * }
			 */
			regForm.setActionName(RegInitConstant.NO_ACTION);
			request.setAttribute("regInitTxnId", regForm.getHidnRegTxnId());
			request.setAttribute("errorMutationEsign", msg);
		}
		// COMPLETE TRANSACTION
		if (RegInitConstant.COMPLETE_APPLICATION_ACTION.equals(actionName)) {
			try {
				ArrayList list = commonBo.getAllUploadsCountAndSize(regForm.getHidnRegTxnId());
				boolean completeTransaction = commonBo.updateTransactionStatus(regForm, ((Integer) list.get(0)).toString(), ((Double) list.get(1)).toString());
				logger.debug("complete transactiooooon status: " + completeTransaction);
				if (completeTransaction) {
					/*
					 * request.setAttribute("label", "userSlotbook");
					 * request.setAttribute("modName", "Slot");
					 * request.setAttribute("from", "regInit");
					 * request.setAttribute("regInitId",
					 * regForm.getHidnRegTxnId());
					 */
					cancelAction(session, regForm);
					if (map != null) {
						if (!map.isEmpty())
							map.clear();
					}
					// count=0;
					// keyCount=0;
					// forward="welcome";
					// label=userSlotbook&modName=Slot&from=regInit&regInitId=<%=request.getAttribute("regInitTxnId")%>
					forward = "welcome";
					// return mapping.findForward(forward);
				} else
					forward = "failure";
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
				forward = "failure";
			}
			// forward = "success1";
		}
		// for creating dashboard
		if (request.getParameter("modName") != null) {
			if (request.getParameter("modName").equalsIgnoreCase("Registration Process")) {
				cancelAction(session, regForm);
				if (request.getParameter("fromAdju") != null) {
					if (request.getParameter("fromAdju").equalsIgnoreCase("Y")) {
						regForm.setFromAdjudication(1);
						// session.setAttribute("fnName","Adjudication");
						regForm.setParentFuncId("");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
						} else {
							session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
						}
					} else {
						regForm.setFromAdjudication(0);
						// session.setAttribute("fnName","Initiation");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
						} else {
							session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
						}
					}
				} else {
					regForm.setFromAdjudication(0);
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
					} else {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
					}
				}
				regForm.setAddmoreStatus(0);
				regForm.setPartyAddStatus(0);
				ArrayList pendingApplicationList = commonBo.getPendingRegApps(session.getAttribute("UserId").toString(), regForm.getFromAdjudication(), languageLocale);
				if (pendingApplicationList.size() == 0)
					regForm.setPendingRegApplicationList(null);
				else
					regForm.setPendingRegApplicationList(pendingApplicationList);
				request.setAttribute("pendingApplicationList", regForm.getPendingRegApplicationList());
				forward = "reginitDashboard";
			}
		}
		// after click on any pending application id from dashboard
		if (request.getParameter("hdnApplicationId") != null && request.getAttribute("eCode") == null) {
			try {
				cancelAction(session, regForm);
				regForm.setHidnRegTxnId(request.getParameter("hdnApplicationId"));
				regForm.setHidnUserId(userId);
				// String appStatus[] = new String[5];
				// FOR GETTING REGISTRATION STATUS FROM DB
				String appStatus = "";
				String appFlag = "";
				String[] appStatus1 = commonBo.getPendingAppStatus(regForm.getHidnRegTxnId());
				appStatus = appStatus1[0].trim();
				appFlag = appStatus1[1].trim();
				if (appFlag.equalsIgnoreCase("R")) {
					regForm.setInitiateAdjuApp(1);
					System.out.println("initiated adju app::" + regForm.getInitiateAdjuApp() + "flag is" + appStatus1[1].trim());
					logger.debug("Adjudicated Application record retrieval------------>" + appStatus1[1].trim());
				}
				logger.debug("pending app status from db------------>" + appStatus);
				regForm.setAppStatus(appStatus);
				/*
				 * if(appStatus!=null){
				 * 
				 * if(appStatus.equalsIgnoreCase("A")){
				 * 
				 * }else{ regForm.setFromAdjudication(0); }
				 * 
				 * 
				 * }else{ regForm.setFromAdjudication(0); }
				 */
				if (appStatus != null && !appStatus.equalsIgnoreCase("") && !appStatus.equalsIgnoreCase("null")) {
					// get Adjudication_flag from db.
					// set in form
					System.out.println(regForm.getHidnRegTxnId());
					String adjFlag = commonBo.getAdjFlag(regForm.getHidnRegTxnId());
					// String clrFlag =
					// commonBo.getClrFlagDasboard(regForm.getHidnRegTxnId());
					System.out.println("the adjudication flag is" + adjFlag);
					if (adjFlag != null && adjFlag.equalsIgnoreCase("A")) {
						regForm.setFromAdjudication(1);
						regForm.setParentFunIdCashLess("FUN_029");
						// session.setAttribute("fnName","Adjudication");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
						} else {
							session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
						}
					} else {
						regForm.setFromAdjudication(0);
						// session.setAttribute("fnName","Initiation");
						if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
							session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
						} else {
							session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
						}
					}
					/*
					 * if(adjFlag!=null){
					 * 
					 * if(adjFlag.equalsIgnoreCase("A")){
					 * regForm.setFromAdjudication(1); }else{
					 * regForm.setFromAdjudication(0); }
					 * 
					 * 
					 * }else{ regForm.setFromAdjudication(0); }
					 */
					String[] deedInstArray = commonBo.getDeedInstId(regForm.getHidnRegTxnId());// get
					// pan
					// flag
					// from
					// db
					regForm.setTransCheck(deedInstArray[7].trim());
					regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
					// regForm.setCommonDeed(commonBo.getCommonDeedFlag(deedInstArray[0].trim()));
					// // setting common deed flag
					regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
					if (deedInstArray[2].trim().equals("-") || deedInstArray[2].trim().equals("")) {
						regForm.setExmpID("");
						regForm.setHdnExAmount("");
					} else {
						regForm.setExmpID(deedInstArray[2].trim());
						regForm.setHdnExAmount(deedInstArray[2].trim());
					}
					if (deedInstArray[5] != null) {
						regForm.setDuty_txn_id(Integer.parseInt(deedInstArray[5].trim()));
					} else {
						regForm.setDuty_txn_id(0);
					}
					String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
					if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {
						if (flags[2].trim().equalsIgnoreCase("Y")) {
							regForm.setCommonDeed(1);
						} else {
							regForm.setCommonDeed(0);
						}
						regForm.setPvReqFlag(flags[1].trim());
						regForm.setPropReqFlag(flags[0].trim());
					} else {
						regForm.setCommonDeed(0);
						regForm.setPvReqFlag("");
						regForm.setPropReqFlag("");
					}
					// DutyCalculationBO bo = new DutyCalculationBO();
					// String
					// propValReqFlag=bo.getPropValRequiredFlag(regForm.getInstID());
					// regForm.setPropValReqFlag(propValReqFlag);
					regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()), languageLocale));
					regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()), languageLocale));
					// below code for exemptions
					// String exemptions = regForm.getExmpID();
					regForm.setSelectedExemptionList(commonBo.getExemptionList(Integer.toString(regForm.getDuty_txn_id()), languageLocale));
					regForm.setFamilyFlag(commonBo.getFamilyFlag(Integer.toString(regForm.getDuty_txn_id())));
					/*
					 * if(regForm.getDeedID()==RegInitConstant.DEED_ADOPTION ||
					 * regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
					 * regForm
					 * .getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
					 * regForm
					 * .getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA
					 * || regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV ||
					 * regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
					 * regForm.getDeedID()==RegInitConstant.DEED_POA ||
					 * regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV
					 * || regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
					 * regForm
					 * .getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
					 * regForm
					 * .getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
					 * regForm
					 * .getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
					 * regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV
					 * ||
					 * regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV
					 * ||
					 * regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV
					 * ||
					 * regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV
					 * ||regForm.getDeedID()==RegInitConstant.
					 * DEED_LETTER_OF_LICENCE_NPV ||
					 * regForm.getDeedID()==RegInitConstant
					 * .DEED_SECURITY_BOND_NPV ||
					 * regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV ||
					 * regForm
					 * .getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV ||
					 * regForm.getCommonDeed()==1){ //for deeds following code
					 * set2
					 * 
					 * 
					 * if(regForm.getDeedID()==RegInitConstant.
					 * DEED_AGREEMENT_MEMO_NPV ||
					 * regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV ||
					 * regForm
					 * .getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV){
					 * 
					 * if(regForm.getInstID()==RegInitConstant.
					 * INST_AGREEMENT_MEMO_NPV_1 ||
					 * regForm.getInstID()==RegInitConstant
					 * .INST_AGREEMENT_MEMO_NPV_2 ||
					 * regForm.getInstID()==RegInitConstant
					 * .INST_AGREEMENT_MEMO_NPV_3 ||
					 * regForm.getInstID()==RegInitConstant
					 * .INST_AGREEMENT_MEMO_NPV_4 ||
					 * regForm.getInstID()==RegInitConstant
					 * .INST_AGREEMENT_MEMO_NPV_6 ||
					 * regForm.getInstID()==RegInitConstant
					 * .INST_AGREEMENT_MEMO_NPV_7 ||
					 * regForm.getInstID()==RegInitConstant
					 * .INST_AGREEMENT_MEMO_NPV_9 ||
					 * regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_1
					 * ||
					 * regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_2
					 * ||regForm.getInstID()==RegInitConstant.
					 * INST_FURTHER_CHARGE_NPV_1){
					 * 
					 * regForm.setAgreeMemoInstFlag(RegInitConstant.
					 * NPV_PROP_NOT_REQ_CONSTANT_2);
					 * 
					 * }elseif(regForm.getInstID()==RegInitConstant.
					 * INST_AGREEMENT_MEMO_NPV_10 ||
					 * regForm.getInstID()==RegInitConstant
					 * .INST_AGREEMENT_MEMO_NPV_5 ||
					 * regForm.getInstID()==RegInitConstant
					 * .INST_AGREEMENT_MEMO_NPV_8 ||
					 * regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_3
					 * ||
					 * regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_4
					 * ||regForm.getInstID()==RegInitConstant.
					 * INST_FURTHER_CHARGE_NPV_2){
					 * 
					 * regForm.setAgreeMemoInstFlag(RegInitConstant.
					 * NPV_PROP_REQ_CONSTANT_1); }
					 * 
					 * 
					 * }
					 * 
					 * regForm.setDeedTypeFlag(1);
					 * //regForm.setHdnDeclareShareCheck("N");
					 * 
					 * 
					 * }
					 */
					if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_APP_SAVED)/*
																					 * ||
																					 * appStatus
																					 * .
																					 * equalsIgnoreCase
																					 * (
																					 * RegInitConstant
																					 * .
																					 * STATUS_MULTI_PROP_PV_RECVD
																					 * )
																					 */) { // for
						// landing
						// on
						// transacting
						// party
						// screen
						commonBo.landTransactingPartyScreen(regForm, appStatus, languageLocale);
						/*
						 * if(appStatus.equalsIgnoreCase(RegInitConstant.STATUS_MULTI_PROP_PV_RECVD
						 * )){ regForm.setIsMultiplePropsFlag(1); }
						 */
						if (regForm.getPvReqFlag().equalsIgnoreCase("y")) // 0
						// is
						// for
						// deeds
						// following
						// code
						// set
						// 1
						// (pv
						// related)
						{
							forward = "transactingParty";
						} else { // for deeds following code set 2 (pv not
							// related)
							forward = "dashboardLandTrns";
						}
						request.setAttribute("deedId", regForm.getDeedID());
						request.setAttribute("instId", regForm.getInstID());
						request.setAttribute("roleIdTrns", "0");
					} else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_TRNS_SAVED) || appStatus.equalsIgnoreCase(RegInitConstant.STATUS_MULTI_PROP_PV_RECVD)
					/*
					 * ||appStatus.equalsIgnoreCase(RegInitConstant.
					 * STATUS_MULTI_TRNS_SAVED)
					 */) { // for landing on property screen
						/*
						 * if(regForm.getCommonDeed()==1){ //for common deeds
						 * forward ="reginitParticular"; } else{
						 */
						if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_TRNS_SAVED)) {
							if (regForm.getPvReqFlag().equalsIgnoreCase("y")) {
								commonBo.landPropertyScreen(regForm, appStatus, languageLocale);
								forward = "reginitProperty";
							} else if (regForm.getPropReqFlag().equalsIgnoreCase("y")) {
								forward = "dashboardLandProp";
							} else if (regForm.getCommonDeed() == 1) {
								forward = "reginitParticular";
							}
						} else {
							// commonBo.landPropertyScreen(regForm,appStatus,languageLocale);
							forward = "reginitPropertyNPV";
						}
						request.setAttribute("propAction", "propAction");
						request.setAttribute("regInitForm", regForm);
						// }
					} else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS)) { // for
						// landing
						// on
						// property
						// screenm
						// multi
						// prop
						// in
						// progress
						// check for latest property type as agri land or not
						// String
						// latestPropertyType=commonBo.getLatestPropertyType(regForm.getHidnRegTxnId());
						/*
						 * if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE
						 * ||
						 * regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV
						 * || (latestPropertyType!=null &&
						 * latestPropertyType.equalsIgnoreCase("3"))){
						 */
						if (regForm.getPvReqFlag().equalsIgnoreCase("y")) {
							commonBo.landPropertyScreen(regForm, appStatus, languageLocale);
							forward = "reginitProperty";
						} else {
							forward = "dashboardLandMultiProp";
						}
						request.setAttribute("propAction", "propAction");
						request.setAttribute("regInitForm", regForm);
						/*
						 * }else
						 * if(regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV
						 * ||
						 * regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV
						 * || regForm.getDeedID()==RegInitConstant.DEED_POA ||
						 * regForm
						 * .getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV ||
						 * regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
						 * regForm
						 * .getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV
						 * ||regForm.getDeedID()==RegInitConstant.
						 * DEED_SURRENDER_LEASE_NPV ||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_COMPOSITION_NPV ||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_SECURITY_BOND_NPV ||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_LETTER_OF_LICENCE_NPV||
						 * (regForm.getDeedID()==RegInitConstant.DEED_TRUST &&
						 * regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P
						 * ) ||(regForm.getDeedID()==RegInitConstant.
						 * DEED_PARTNERSHIP_NPV &&
						 * (regForm.getInstID()==RegInitConstant
						 * .INST_PARTNERSHIP_NPV_1 ||
						 * regForm.getInstID()==RegInitConstant
						 * .INST_PARTNERSHIP_NPV_2 ))||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_PARTITION_NPV ||
						 * (regForm.getDeedID()==RegInitConstant
						 * .DEED_AGREEMENT_MEMO_NPV &&
						 * regForm.getAgreeMemoInstFlag
						 * ()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
						 * (regForm
						 * .getDeedID()==RegInitConstant.DEED_TRANSFER_NPV &&
						 * regForm.getAgreeMemoInstFlag()==RegInitConstant.
						 * NPV_PROP_REQ_CONSTANT_1) ||
						 * (regForm.getDeedID()==RegInitConstant
						 * .DEED_FURTHER_CHARGE_NPV &&
						 * regForm.getAgreeMemoInstFlag
						 * ()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1)){
						 * 
						 * 
						 * 
						 * 
						 * 
						 * 
						 * 
						 * 
						 * 
						 * 
						 * commonBo.landTransactingPartyScreen(regForm,appStatus,
						 * languageLocale); regForm.setIsMultiplePropsFlag(1);
						 * forward="dashboardLandTrns";
						 * 
						 * }else{
						 * commonBo.landTransactingPartyScreen(regForm,appStatus
						 * ,languageLocale); regForm.setIsMultiplePropsFlag(1);
						 * request.setAttribute("multipleProps",
						 * "multipleProps"); forward="propval"; }
						 */
					} else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_PROP_SAVED) || appStatus.equalsIgnoreCase(RegInitConstant.STATUS_BACKED_MAPPING_SAVED)) {/*
																																										 * 
																																										 * //
																																										 * land
																																										 * on
																																										 * mapping
																																										 * screen
																																										 * 
																																										 * int
																																										 * count
																																										 * =
																																										 * commonBo
																																										 * .
																																										 * getPropertyCount
																																										 * (
																																										 * regForm
																																										 * .
																																										 * getHidnRegTxnId
																																										 * (
																																										 * )
																																										 * )
																																										 * ;
																																										 * 
																																										 * if
																																										 * (
																																										 * regForm
																																										 * .
																																										 * getPvReqFlag
																																										 * (
																																										 * )
																																										 * .
																																										 * equalsIgnoreCase
																																										 * (
																																										 * "y"
																																										 * )
																																										 * ||
																																										 * regForm
																																										 * .
																																										 * getPropReqFlag
																																										 * (
																																										 * )
																																										 * .
																																										 * equalsIgnoreCase
																																										 * (
																																										 * "y"
																																										 * )
																																										 * )
																																										 * {
																																										 * if
																																										 * (
																																										 * count
																																										 * ==
																																										 * 1
																																										 * ||
																																										 * regForm
																																										 * .
																																										 * getDeedID
																																										 * (
																																										 * )
																																										 * ==
																																										 * RegInitConstant
																																										 * .
																																										 * DEED_EXCHANGE
																																										 * ||
																																										 * regForm
																																										 * .
																																										 * getDeedID
																																										 * (
																																										 * )
																																										 * ==
																																										 * RegInitConstant
																																										 * .
																																										 * DEED_PARTITION_NPV
																																										 * ||
																																										 * appStatus
																																										 * .
																																										 * equalsIgnoreCase
																																										 * (
																																										 * RegInitConstant
																																										 * .
																																										 * STATUS_BACKED_MAPPING_SAVED
																																										 * )
																																										 * )
																																										 * {
																																										 * 
																																										 * 
																																										 * regForm
																																										 * .
																																										 * setPropWithMapping
																																										 * (
																																										 * commonBo
																																										 * .
																																										 * getAllPartiesPropertiesAlreadyMap
																																										 * (
																																										 * regForm
																																										 * ,
																																										 * languageLocale
																																										 * )
																																										 * )
																																										 * ;
																																										 * regForm
																																										 * .
																																										 * setPropWithoutMapping
																																										 * (
																																										 * null
																																										 * )
																																										 * ;
																																										 * regForm
																																										 * .
																																										 * setDeclareShareSize
																																										 * (
																																										 * regForm
																																										 * .
																																										 * getPropWithMapping
																																										 * (
																																										 * )
																																										 * .
																																										 * size
																																										 * (
																																										 * )
																																										 * )
																																										 * ;
																																										 * 
																																										 * if
																																										 * (
																																										 * regForm
																																										 * .
																																										 * getPvReqFlag
																																										 * (
																																										 * )
																																										 * .
																																										 * equalsIgnoreCase
																																										 * (
																																										 * "y"
																																										 * )
																																										 * )
																																										 * {
																																										 * forward
																																										 * =
																																										 * "alreadyMapped"
																																										 * ;
																																										 * }
																																										 * else
																																										 * {
																																										 * forward
																																										 * =
																																										 * "alreadyMappedPropNonPV"
																																										 * ;
																																										 * }
																																										 * 
																																										 * regForm
																																										 * .
																																										 * setCheckParty
																																										 * (
																																										 * "true"
																																										 * )
																																										 * ;
																																										 * return
																																										 * mapping
																																										 * .
																																										 * findForward
																																										 * (
																																										 * forward
																																										 * )
																																										 * ;
																																										 * 
																																										 * }
																																										 * else
																																										 * {
																																										 * 
																																										 * regForm
																																										 * .
																																										 * setPropWithMapping
																																										 * (
																																										 * null
																																										 * )
																																										 * ;
																																										 * regForm
																																										 * .
																																										 * setPropWithoutMapping
																																										 * (
																																										 * commonBo
																																										 * .
																																										 * getAllPartiesPropertiesMap
																																										 * (
																																										 * regForm
																																										 * ,
																																										 * languageLocale
																																										 * )
																																										 * )
																																										 * ;
																																										 * regForm
																																										 * .
																																										 * setDeclareShareSize
																																										 * (
																																										 * regForm
																																										 * .
																																										 * getPropWithoutMapping
																																										 * (
																																										 * )
																																										 * .
																																										 * size
																																										 * (
																																										 * )
																																										 * )
																																										 * ;
																																										 * 
																																										 * 
																																										 * if
																																										 * (
																																										 * regForm
																																										 * .
																																										 * getPvReqFlag
																																										 * (
																																										 * )
																																										 * .
																																										 * equalsIgnoreCase
																																										 * (
																																										 * "y"
																																										 * )
																																										 * )
																																										 * {
																																										 * forward
																																										 * =
																																										 * "getMapping"
																																										 * ;
																																										 * }
																																										 * else
																																										 * {
																																										 * forward
																																										 * =
																																										 * "getMappingPropNonPV"
																																										 * ;
																																										 * 
																																										 * }
																																										 * 
																																										 * }
																																										 * 
																																										 * }
																																										 * else
																																										 * if
																																										 * (
																																										 * regForm
																																										 * .
																																										 * getCommonDeed
																																										 * (
																																										 * )
																																										 * ==
																																										 * 1
																																										 * )
																																										 * {
																																										 * 
																																										 * int
																																										 * particularCount
																																										 * =
																																										 * commonBo
																																										 * .
																																										 * getParticularCount
																																										 * (
																																										 * regForm
																																										 * .
																																										 * getHidnRegTxnId
																																										 * (
																																										 * )
																																										 * )
																																										 * ;
																																										 * if
																																										 * (
																																										 * particularCount
																																										 * >
																																										 * 0
																																										 * )
																																										 * {
																																										 * regForm
																																										 * .
																																										 * setParticularWithoutMapping
																																										 * (
																																										 * commonBo
																																										 * .
																																										 * getAllPartiesParticularsMap
																																										 * (
																																										 * regForm
																																										 * ,
																																										 * languageLocale
																																										 * )
																																										 * )
																																										 * ;
																																										 * forward
																																										 * =
																																										 * "getMappingParticularNonPV"
																																										 * ;
																																										 * }
																																										 * else
																																										 * {
																																										 * forward
																																										 * =
																																										 * "failure"
																																										 * ;
																																										 * }
																																										 * 
																																										 * 
																																										 * }
																																										 * 
																																										 * return
																																										 * mapping
																																										 * .
																																										 * findForward
																																										 * (
																																										 * forward
																																										 * )
																																										 * ;
																																										 */
						forward = commonBo.landMappingScreen(regForm, languageLocale);
						return mapping.findForward(forward);
					} else if (appStatus.equalsIgnoreCase(RegInitConstant.CONSENTER_IN_PROGRESS)) {
						String[] param = commonBo.getConsenterFlag(regForm.getHidnRegTxnId());
						regForm.setConsenterFlag("Y");
						regForm.setConsenterWithConsidFlag(param[0]);
						// regForm.setConsenterCount(Integer.parseInt(param[1]));
						regForm.setDtlsMapConsntr(commonBo.getConsenters(regForm.getHidnRegTxnId()));
						regForm.setConsenterAddedCount(regForm.getDtlsMapConsntr() != null ? regForm.getDtlsMapConsntr().size() : 0);
						// get already added consenters in map here
						String consenterClrFlag = "N";
						consenterClrFlag = commonBo.getClrFlagForConsenter(regForm.getHidnRegTxnId());
						if (consenterClrFlag.equalsIgnoreCase("Y")) {
							regForm.setClrFlg("Y");
						}
						if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
							forward = "reginitConsenter";
							logger.debug("consenter clr check : " + regForm.getClrFlg());
						} else {
							forward = "reginitConsenterNonPV";
						}
						commonDto.setIdProf(commonBo.getIdProf(languageLocale));
						commonDto.setState(commonBo.getState("1", languageLocale));
						commonDto.setDistrict(new ArrayList());
						commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
						return mapping.findForward(forward);
					} else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_SHARES_SAVED)) {
						commonBo.landConfirmationScreen(regForm, languageLocale, request);
						// if(regForm.getDeedID()!=0 && regForm.getInstID()!=0){
						request.setAttribute("deedId", regForm.getDeedID());
						request.setAttribute("instId", regForm.getInstID());
						// }
						/*
						 * if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE
						 * ||
						 * regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV
						 * ||
						 * regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV
						 * || regForm.getDeedID()==RegInitConstant.DEED_POA ||
						 * regForm
						 * .getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV ||
						 * regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
						 * regForm
						 * .getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV
						 * ||regForm.getDeedID()==RegInitConstant.
						 * DEED_RECONV_MORTGAGE_NPV ||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_SURRENDER_LEASE_NPV ||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_COMPOSITION_NPV ||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_SECURITY_BOND_NPV ||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_LETTER_OF_LICENCE_NPV ||
						 * (regForm.getDeedID()==RegInitConstant.DEED_TRUST &&
						 * regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P
						 * ) ||(regForm.getDeedID()==RegInitConstant.
						 * DEED_PARTNERSHIP_NPV &&
						 * (regForm.getInstID()==RegInitConstant
						 * .INST_PARTNERSHIP_NPV_1 ||
						 * regForm.getInstID()==RegInitConstant
						 * .INST_PARTNERSHIP_NPV_2 ) )||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_PARTITION_NPV ||
						 * (regForm.getDeedID()==RegInitConstant
						 * .DEED_AGREEMENT_MEMO_NPV &&
						 * regForm.getAgreeMemoInstFlag
						 * ()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
						 * (regForm
						 * .getDeedID()==RegInitConstant.DEED_TRANSFER_NPV &&
						 * regForm.getAgreeMemoInstFlag()==RegInitConstant.
						 * NPV_PROP_REQ_CONSTANT_1) ||
						 * (regForm.getDeedID()==RegInitConstant
						 * .DEED_FURTHER_CHARGE_NPV &&
						 * regForm.getAgreeMemoInstFlag
						 * ()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
						 * regForm.getDeedTypeFlag()==1)
						 */
						double totPay = Double.parseDouble(regForm.getTotalPayableAmount());
						double totBal = Double.parseDouble(regForm.getTotalBalanceAmount());
						if (regForm.getPvReqFlag().equalsIgnoreCase("N")) {
							if ((regForm.getFromAdjudication() == 0 && totPay > 0 && totBal <= 0 && regForm.getRegInitEstampCode() == null)) {
								request.setAttribute("eStampRegId", regForm.getHidnRegTxnId());
								request.setAttribute("eStampRegAmnt", regForm.getTotalPayableAmount());
								request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(regForm.getHidnRegTxnId()));
								request.setAttribute("eStampRegFuncId", "023");
								request.setAttribute("eStampRegPurpose", regForm.getPurpose());
								request.setAttribute("sourceModFlag", "PVN");
								forward = "reginitEstamp";
								return mapping.findForward(forward);
							} else {
								forward = "reginitConfirmNonPV";
							}
						} else {
							if ((regForm.getFromAdjudication() == 0 && totPay > 0 && totBal <= 0 && regForm.getRegInitEstampCode() == null)) {
								request.setAttribute("eStampRegId", regForm.getHidnRegTxnId());
								request.setAttribute("eStampRegAmnt", regForm.getTotalPayableAmount());
								request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(regForm.getHidnRegTxnId()));
								request.setAttribute("eStampRegFuncId", "023");
								request.setAttribute("eStampRegPurpose", regForm.getPurpose());
								request.setAttribute("sourceModFlag", "PV");
								forward = "reginitEstamp";
								return mapping.findForward(forward);
							} else {
								forward = "reginitConfirm";
							}
						}
					} else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_FINAL_SCREEN)) { // for
						// landing
						// on
						// final
						// success
						// screen
						RegCommonBO bo = new RegCommonBO();
						bo.getPaymentAmounts(regForm);
						double regFee = 0;
						double regFeePaid = 0;
						String payComplt = "Y";
						if ("Y".equalsIgnoreCase(regForm.getCheckRegFeeBox())) {
							regFee = Double.parseDouble((String) regForm.getTotalBalanceAmountFee());
							regFeePaid = Double.parseDouble((String) regForm.getTotalPaidAmountFee());
							payComplt = bo.checkFeeCompletFlag(regForm.getHidnRegTxnId());
						}
						if ("Y".equalsIgnoreCase(regForm.getCheckRegFeeBox()) && (regFeePaid == 0)) {
							commonBo.landConfirmationScreen(regForm, languageLocale, request);
							request.setAttribute("deedId", regForm.getDeedID());
							request.setAttribute("instId", regForm.getInstID());
							if (regForm.getPvReqFlag().equalsIgnoreCase("N")) {
								forward = "reginitConfirmNonPV";
							} else {
								forward = "reginitConfirm";
							}
						} else if ("Y".equalsIgnoreCase(regForm.getCheckRegFeeBox()) && (regFee > 0.0 || "N".equalsIgnoreCase(payComplt))) {
							if (regForm.getPvReqFlag().equalsIgnoreCase("N")) {
								forward = "regFeePageNPV";
							} else {
								forward = "regFeePage";
							}
						} else {
							commonDto.setIndcountry(commonBo.getCountry(languageLocale));
							commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
							commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
							regForm.setPostalCountry("1");
							regForm.setPostalState("");
							regForm.setPostalDistrict("");
							regForm.setHdnPostalAddress1(regForm.getPostalAddress1());
							String dutyListArr[] = new String[9];
							String purpose = commonBo.getEStampPurpose(regForm.getHidnRegTxnId());
							// String purpose="";
							if (purpose != null && !purpose.equalsIgnoreCase("")) {
								regForm.setPurpose(purpose.replace(RegInitConstant.SPLIT_CONSTANT, ","));
							} else {
								regForm.setPurpose("");
							}
							dutyListArr = commonBo.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
							/*
							 * NumberFormat obj=new DecimalFormat("#0.00");
							 * if(dutyListArr!=null){
							 * regForm.setStampduty1(obj.format
							 * (Double.parseDouble(dutyListArr[0].trim())));
							 * regForm
							 * .setNagarPalikaDuty(obj.format(Double.parseDouble
							 * (dutyListArr[2].trim())));
							 * regForm.setPanchayatDuty
							 * (obj.format(Double.parseDouble
							 * (dutyListArr[1].trim())));
							 * regForm.setUpkarDuty(obj
							 * .format(Double.parseDouble
							 * (dutyListArr[3].trim())));
							 * regForm.setTotalduty(obj
							 * .format(Double.parseDouble
							 * (dutyListArr[5].trim())));
							 * regForm.setRegistrationFee
							 * (obj.format(Double.parseDouble
							 * (dutyListArr[4].trim())));
							 * 
							 * if(dutyListArr[6].trim()!=null &&
							 * !dutyListArr[6].trim().equalsIgnoreCase(""))
							 * regForm
							 * .setMarketValueShares(obj.format(Double.parseDouble
							 * (dutyListArr[6].trim()))); else
							 * regForm.setMarketValueShares
							 * (Double.toString(0.0));
							 * 
							 * if(dutyListArr[7].trim()!=null &&
							 * !dutyListArr[7].trim().equalsIgnoreCase(""))
							 * regForm
							 * .setDutyPaid(obj.format(Double.parseDouble(
							 * dutyListArr[7].trim()))); else
							 * regForm.setDutyPaid(Double.toString(0.0));
							 * 
							 * if(dutyListArr[8].trim()!=null &&
							 * !dutyListArr[8].trim().equalsIgnoreCase(""))
							 * regForm
							 * .setRegPaid(obj.format(Double.parseDouble(
							 * dutyListArr [8].trim()))); else
							 * regForm.setRegPaid(Double.toString(0.0)); }
							 */
							commonBo.setDutyInForm(regForm, dutyListArr, languageLocale);
							// following code for getting current date time
							String currDate = commonBo.getCurrDateTime();
							// System.out.println("curr date 2 : " + currDate);
							regForm.setCurrDateTime(currDate);
							request.setAttribute("regInitTxnId", regForm.getHidnRegTxnId());
							String rcmsPayCheck = commonBo.rcmsCheckCyber(regForm.getHidnRegTxnId());
							if ("Y".equalsIgnoreCase(rcmsPayCheck)) {
								// checking whether form details are saved or
								// not.
								boolean saved = commonBo.getFormOneInsertStatus(regForm.getHidnRegTxnId(), "FORM_VIA");
								if (!saved) {
									String userDetail = commonBo.getUserDetail(userId);
									regForm.setUserName(userDetail+", ");
									regForm.setFormA1Consent("");
									regForm.setFormA1SPConsent("");
									ArrayList fDto = commonBo.getFormData("FORM_VIA");
									ArrayList list1 = new ArrayList();
									// regForm.setCourtDetail(list1);
									regForm.setCaseName(new String[]{""});
									regForm.setCaseCase(new String[]{""});
									regForm.setCaseAdress(new String[]{""});
									regForm.setUserEnterableOne("");
									regForm.setUserEnterableThree("");
									regForm.setUserEnterableTwo("");
									regForm.setOrderdateone("");
									regForm.setOrderdatetwo("");
									regForm.setOrderdatethree("");
									regForm.setEncumberanceData("");
									regForm.setEncumberanceDate("");
									regForm.setCourtNameList(new ArrayList<String>());
									regForm.setCaseNumberList(new ArrayList<String>());
									regForm.setNameAndAddressOfPartyList(new ArrayList<String>());
									regForm.setFormOne(fDto);
									regForm.setSellerInfo(commonBo.getSellerDetail(regForm.getHidnRegTxnId(), languageLocale));
									regForm.setCyberFormName("FORM_VIA");
								} else {
									saved = false;
									saved = commonBo.getFormOneInsertStatus(regForm.getHidnRegTxnId(), "FORM_VIB");
									if (!saved) {
										String userDetail = commonBo.getUserDetail(userId);
										regForm.setUserName(userDetail+", ");
										regForm.setFormA2SPConsent("");
										regForm.setFormA2Consent("");
										ArrayList fDto = commonBo.getFormData("FORM_VIB");
										regForm.setBuyerInfo(commonBo.getBuyerDetail(regForm.getHidnRegTxnId(), languageLocale));
										regForm.setSellerInfo(commonBo.getSellerDetail(regForm.getHidnRegTxnId(), languageLocale));
										regForm.setPropInfo(commonBo.getPropDetail(regForm.getHidnRegTxnId(), languageLocale));
										regForm.setFormOne(fDto);
										regForm.setCyberFormName("FORM_VIB");
									} else {
										regForm.setCyberFormName("FORM_CHECKED");
										// added by ankit for esign and pdf work
										// ---

										boolean isFormViAPrsnt = false;
										if (!isFormViAPrsnt) {
											 commonBo.generateFormVIAPDF(regForm.getHidnRegTxnId(),languageLocale);
										}

										boolean isFormViBPrsnt = false;
										if (!isFormViAPrsnt) {
											 commonBo.generateFormVIBPDF(regForm.getHidnRegTxnId(),languageLocale);
										}

										//regForm.setPartyDetail(commonBo.getPartyDetails(regForm.getHidnRegTxnId(), languageLocale));

										if (null != regForm.getFormOne()) {
											regForm.getFormOne().clear();
										}
									}
								}
								String[] selectedId = {};
								regForm.setTestSelection(selectedId);
								regForm.setMutationFeeCheck(rcmsPayCheck);
								
								ArrayList cyberformlist = chkbd.getCyberTehsilFormDetails(regForm.getHidnRegTxnId());
								if(cyberformlist.size() != 0){
	                        		for(int i = 0 ; i < cyberformlist.size() ;i++)
	        						{
	        							ArrayList subList = (ArrayList)cyberformlist.get(i);
	        							regForm.setFormA1((String)subList.get(1));
	        							regForm.setFormA2((String)subList.get(2));
	        						}
	                        	}else{
	                        		regForm.setFormA1("");
        							regForm.setFormA2("");
	                        	}
								String distictProp = commonBo.distinctTehCount(regForm.getHidnRegTxnId());
								String perCaseFee = commonBo.getRCMSFee();
								int totalAmount = 1 * Integer.parseInt(perCaseFee);
								regForm.setTotalMutationFees(String.valueOf(totalAmount));
								String checkPayment = commonBo.getRcmsFeeStatus(regForm.getHidnRegTxnId());
								int balanceAmount = totalAmount - (Integer.parseInt(checkPayment));
								regForm.setBalanceLeftMutation(String.valueOf(balanceAmount));
								regForm.setTotalPaidMutationFees(checkPayment);
								if (checkPayment.equalsIgnoreCase(String.valueOf(totalAmount))) {
									if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
										forward = "success1";
									} else {
										forward = "dashboardLandSuccess";
									}
								}
								forward = "mutationFeePage";
							}else if(commonBd.rcmsCheck(regForm.getHidnRegTxnId()).equalsIgnoreCase("Y")){
								regForm.setCyberFormName("FORM_CHECKED");
								regForm.setMutationFeeCheck(rcmsPayCheck);
					
	                        		regForm.setFormA1("");
        							regForm.setFormA2("");
	                        	
								String distictProp = commonBo.distinctTehCount(regForm.getHidnRegTxnId());
								String perCaseFee = commonBo.getRCMSFee();
								int totalAmount = 1 * Integer.parseInt(perCaseFee);
								regForm.setTotalMutationFees(String.valueOf(totalAmount));
								String checkPayment = commonBo.getRcmsFeeStatus(regForm.getHidnRegTxnId());
								int balanceAmount = totalAmount - (Integer.parseInt(checkPayment));
								regForm.setBalanceLeftMutation(String.valueOf(balanceAmount));
								regForm.setTotalPaidMutationFees(checkPayment);
								if (checkPayment.equalsIgnoreCase(String.valueOf(totalAmount))) {
									if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
										forward = "success1";
									} else {
										forward = "dashboardLandSuccess";
									}
								}
								forward = "mutationFeePage";
							}
							else {
								regForm.setMutationFeeCheck(rcmsPayCheck);
								if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
									forward = "success1";
								} else {
									forward = "dashboardLandSuccess";
								}
							}

						}
					} else if (appStatus.equalsIgnoreCase(RegInitConstant.STATUS_COMPLETE)) { // complete
						// applications
						// on
						// dashboard
						/*
						 * if(regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV
						 * ||
						 * regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV
						 * || regForm.getDeedID()==RegInitConstant.DEED_POA ||
						 * regForm
						 * .getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV ||
						 * regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
						 * regForm
						 * .getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV
						 * ||regForm.getDeedID()==RegInitConstant.
						 * DEED_SURRENDER_LEASE_NPV ||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_COMPOSITION_NPV ||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_SECURITY_BOND_NPV ||
						 * regForm.getDeedID()==RegInitConstant
						 * .DEED_LETTER_OF_LICENCE_NPV ||
						 * (regForm.getDeedID()==RegInitConstant
						 * .DEED_PARTNERSHIP_NPV &&
						 * (regForm.getInstID()==RegInitConstant
						 * .INST_PARTNERSHIP_NPV_1 ||
						 * regForm.getInstID()==RegInitConstant
						 * .INST_PARTNERSHIP_NPV_2 ))||
						 * (regForm.getDeedID()==RegInitConstant
						 * .DEED_AGREEMENT_MEMO_NPV &&
						 * regForm.getAgreeMemoInstFlag
						 * ()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
						 * (regForm
						 * .getDeedID()==RegInitConstant.DEED_TRANSFER_NPV &&
						 * regForm.getAgreeMemoInstFlag()==RegInitConstant.
						 * NPV_PROP_REQ_CONSTANT_1) ||
						 * (regForm.getDeedID()==RegInitConstant
						 * .DEED_FURTHER_CHARGE_NPV &&
						 * regForm.getAgreeMemoInstFlag
						 * ()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1)){
						 * regForm.setDeedTypeFlag(0); //// deedTypeFlag 0 for
						 * deeds where property details are required. }
						 */
						// for print correction
						String purpose = commonBo.getEStampPurpose(regForm.getHidnRegTxnId());
						// String purpose="";
						if (purpose != null && !purpose.equalsIgnoreCase("")) {
							regForm.setPurpose(purpose.replace(RegInitConstant.SPLIT_CONSTANT, ","));
						} else {
							regForm.setPurpose("");
						}
						String dutyListArr[] = new String[10];
						if (regForm.getFromAdjudication() == 0) {
							// dutyListArr=new String[10];
							dutyListArr = commonBo.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
						} else {
							// dutyListArr=new String[6];
							dutyListArr = commonBo.getDutyDetlsAdjuForConfirmation(regForm.getHidnRegTxnId());
						}
						/*
						 * NumberFormat obj=new DecimalFormat("#0.00");
						 * if(dutyListArr!=null){
						 * regForm.setStampduty1(obj.format
						 * (Double.parseDouble(dutyListArr[0].trim())));
						 * regForm.
						 * setNagarPalikaDuty(obj.format(Double.parseDouble
						 * (dutyListArr[2].trim())));
						 * regForm.setPanchayatDuty(obj
						 * .format(Double.parseDouble(dutyListArr[1].trim())));
						 * regForm
						 * .setUpkarDuty(obj.format(Double.parseDouble(dutyListArr
						 * [3].trim())));
						 * regForm.setTotalduty(obj.format(Double.
						 * parseDouble(dutyListArr[5].trim())));
						 * regForm.setRegistrationFee
						 * (obj.format(Double.parseDouble
						 * (dutyListArr[4].trim())));
						 * 
						 * if(dutyListArr[6].trim()!=null &&
						 * !dutyListArr[6].trim().equalsIgnoreCase(""))
						 * regForm.setMarketValueShares
						 * (obj.format(Double.parseDouble
						 * (dutyListArr[6].trim()))); else
						 * regForm.setMarketValueShares(Double.toString(0.0));
						 * 
						 * if(dutyListArr[7].trim()!=null &&
						 * !dutyListArr[7].trim().equalsIgnoreCase(""))
						 * regForm.setDutyPaid
						 * (obj.format(Double.parseDouble(dutyListArr
						 * [7].trim()))); else
						 * regForm.setDutyPaid(Double.toString(0.0));
						 * 
						 * if(dutyListArr[8].trim()!=null &&
						 * !dutyListArr[8].trim().equalsIgnoreCase(""))
						 * regForm.setRegPaid
						 * (obj.format(Double.parseDouble(dutyListArr
						 * [8].trim()))); else
						 * regForm.setRegPaid(Double.toString(0.0)); }
						 */
						commonBo.setDutyInForm(regForm, dutyListArr, languageLocale);
						// regForm=commonBo.printToPdf(regForm, request,
						// response, languageLocale);
						commonBo.printToPdfJasper(regForm, request, response, languageLocale, "Y");
						request.setAttribute("deedId", regForm.getDeedID());
						request.setAttribute("instId", regForm.getInstID());
						request.setAttribute("pendingApplicationList", regForm.getPendingRegApplicationList());
						forward = "reginitDashboard";
					}
				}
			} catch (Exception e) {
				logger.debug(e);
				e.printStackTrace();
				logger.debug(e.getMessage(), e);
				forward = "failure";
				return mapping.findForward(forward);
			}
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			// forward="reginitConfirm";
			return mapping.findForward(forward);
		}
		// delete application from dashboard
		if (request.getParameter("hdnDelApplicationId") != null) {
			String appId = request.getParameter("hdnDelApplicationId");
			boolean applicationDetlsDeltd = commonBo.deleteSelectedAppDetails(appId, regForm.getFromAdjudication());
			logger.debug("Registration Initiation Application deleted :- " + applicationDetlsDeltd);
			if (applicationDetlsDeltd) {
				ArrayList pendingApplicationList = commonBo.getPendingRegApps(session.getAttribute("UserId").toString(), regForm.getFromAdjudication(), languageLocale);
				if (pendingApplicationList.size() == 0)
					regForm.setPendingRegApplicationList(null);
				else
					regForm.setPendingRegApplicationList(pendingApplicationList);
			}
			request.setAttribute("pendingApplicationList", regForm.getPendingRegApplicationList());
			forward = "reginitDashboard";
		}
		// end of delete application from dashboard
		// ADD MULTIPLE PROPERTIES
		/*
		 * if(request.getParameter("multipleProps")!=null){
		 * if(request.getParameter("multipleProps").equalsIgnoreCase("Y")){
		 * 
		 * regForm.setIsMultiplePropsFlag(1);
		 * request.setAttribute("multipleProps", "multipleProps");
		 * 
		 * if(regForm.getFromAdjudication()==1){
		 * request.setAttribute("fromAdju", "Y"); }
		 * 
		 * 
		 * forward="propval";
		 * 
		 * }
		 * 
		 * 
		 * }
		 */
		// END
		// NEXT TO CONFIRMATION SCREEN
		if (request.getParameter("confirmation") != null) {
			if (request.getParameter("confirmation").equalsIgnoreCase("Y")) {
				regForm.setActionName("");
				String[] param = commonBo.getConsenterFlag(regForm.getHidnRegTxnId());
				regForm.setConsenterFlag("Y");
				regForm.setConsenterWithConsidFlag(param[0]);
				// regForm.setConsenterCount(Integer.parseInt(param[1]));
				if (regForm.getPropReqFlag().equalsIgnoreCase("Y") && regForm.getConsenterFlag().equalsIgnoreCase("Y")) {
					boolean updateStatus = commonBo.updateConsenterFlag(RegInitConstant.CONSENTER_IN_PROGRESS, regForm.getHidnRegTxnId(), "Y", regForm.getConsenterWithConsidFlag());
					if (updateStatus) {
						forward = "reginitConsenter";
						regForm.setListDto(new ArrayList());
						regForm.setRegDTO(new RegCompleteMakerDTO());
						regForm.setConsenterAddedCount(0);
						commonDto.setIdProf(commonBo.getIdProf(languageLocale));
						commonDto.setState(commonBo.getState("1", languageLocale));
						commonDto.setDistrict(new ArrayList());
						commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
						return mapping.findForward(forward);
					} else {
						logger.debug("unable to update reg status for consenter.");
						forward = "failure";
					}
				} else {
					forward = "reginitConfirm";
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", regForm.getInstID());
					commonBo.landConfirmationScreen(regForm, languageLocale, request);
					return mapping.findForward(forward);
				}
				/*
				 * request.setAttribute("deedId", regForm.getDeedID());
				 * request.setAttribute("instId", regForm.getInstID());
				 * 
				 * commonBo.landConfirmationScreen(regForm,
				 * languageLocale,request);
				 * 
				 * 
				 * 
				 * forward="reginitConfirm";
				 */
			}
		}
		// following code for generating e code for 0 payable amount.
		if (RegInitConstant.GENERATE_ECODE_ZERO_AMNT_ACTION.equals(actionName) && request.getAttribute("eCode") == null) {
			// save purpose here
			// following code for updating purpose
			if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
				boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
				if (updatePurpose) {
					regForm.setPurposeAddedFlag("Y");
					logger.debug("purpose updated before generating e code for zero amount.");
					regForm.setActionName("");
					// redirect to estamping for ecode generation here.
					request.setAttribute("eStampRegId", regForm.getHidnRegTxnId());
					request.setAttribute("eStampRegAmnt", regForm.getTotalPayableAmount());
					request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(regForm.getHidnRegTxnId()));
					request.setAttribute("eStampRegFuncId", "023");
					request.setAttribute("eStampRegPurpose", regForm.getPurpose());
					request.setAttribute("sourceModFlag", "PV");
					forward = "reginitEstamp";
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", regForm.getInstID());
					return mapping.findForward(forward);
				} else {
					forward = "failure";
					logger.debug("purpose not updated before generating e code for zero amount.");
				}
			}
		}
		if (RegInitConstant.CALCULATE_DUTIES_ACTION.equals(actionName)) {/*
																		 * 
																		 * String[
																		 * ]
																		 * deedInstArray
																		 * =
																		 * commonBo
																		 * .
																		 * getDeedInstId
																		 * (
																		 * regForm
																		 * .
																		 * getHidnRegTxnId
																		 * ());
																		 * if(
																		 * deedInstArray
																		 * !=
																		 * null
																		 * &&
																		 * deedInstArray
																		 * .
																		 * length
																		 * >0){
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * request.
																		 * setAttribute
																		 * (
																		 * "deedId"
																		 * ,
																		 * deedInstArray
																		 * [
																		 * 0].trim
																		 * ());
																		 * request
																		 * .
																		 * setAttribute
																		 * (
																		 * "instId"
																		 * ,
																		 * deedInstArray
																		 * [
																		 * 1].trim
																		 * ());
																		 * regForm
																		 * .
																		 * setDeedID
																		 * (
																		 * Integer
																		 * .
																		 * parseInt
																		 * (
																		 * deedInstArray
																		 * [
																		 * 0].trim
																		 * ()));
																		 * regForm
																		 * .
																		 * setInstID
																		 * (
																		 * Integer
																		 * .
																		 * parseInt
																		 * (
																		 * deedInstArray
																		 * [
																		 * 1].trim
																		 * ()));
																		 * if(
																		 * deedInstArray
																		 * [
																		 * 2].trim
																		 * (
																		 * ).equals
																		 * ("-")
																		 * ||
																		 * deedInstArray
																		 * [
																		 * 2].trim
																		 * ().
																		 * equalsIgnoreCase
																		 * (
																		 * "")){
																		 * regForm
																		 * .
																		 * setExmpID
																		 * ("");
																		 * regForm
																		 * .
																		 * setHdnExAmount
																		 * ("");
																		 * }
																		 * else{
																		 * regForm
																		 * .
																		 * setExmpID
																		 * (
																		 * deedInstArray
																		 * [
																		 * 2].trim
																		 * ());
																		 * 
																		 * String
																		 * exemp
																		 * =
																		 * deedInstArray
																		 * [
																		 * 2].trim
																		 * (
																		 * ).replace
																		 * ("-",
																		 * ",");
																		 * /
																		 * /exemp
																		 * =
																		 * exemp
																		 * .
																		 * substring
																		 * (
																		 * exemp
																		 * .
																		 * length
																		 * (
																		 * )-1);
																		 * exemp
																		 * =
																		 * exemp
																		 * .
																		 * substring
																		 * (
																		 * 0,exemp
																		 * .
																		 * length
																		 * (
																		 * )-1);
																		 * regForm
																		 * .
																		 * setHdnExAmount
																		 * (
																		 * exemp
																		 * ); }
																		 * 
																		 * }else
																		 * {
																		 * request
																		 * .
																		 * setAttribute
																		 * (
																		 * "deedId"
																		 * , 0);
																		 * request
																		 * .
																		 * setAttribute
																		 * (
																		 * "instId"
																		 * , 0);
																		 * regForm
																		 * .
																		 * setDeedID
																		 * (0);
																		 * regForm
																		 * .
																		 * setInstID
																		 * (0);
																		 * regForm
																		 * .
																		 * setExmpID
																		 * ("");
																		 * regForm
																		 * .
																		 * setHdnExAmount
																		 * ("");
																		 * }
																		 * regForm
																		 * .
																		 * setDeedtype1
																		 * (
																		 * commonBo
																		 * .
																		 * getDeedName
																		 * (
																		 * Integer
																		 * .
																		 * toString
																		 * (
																		 * regForm
																		 * .
																		 * getDeedID
																		 * ()),
																		 * languageLocale
																		 * ));
																		 * regForm
																		 * .
																		 * setInstType
																		 * (
																		 * commonBo
																		 * .
																		 * getInstrumentName
																		 * (
																		 * Integer
																		 * .
																		 * toString
																		 * (
																		 * regForm
																		 * .
																		 * getInstID
																		 * ()),
																		 * languageLocale
																		 * ));
																		 * //
																		 * below
																		 * code
																		 * for
																		 * exemptions
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * //String
																		 * exemptions
																		 * =
																		 * regForm
																		 * .
																		 * getExmpID
																		 * ();
																		 * regForm
																		 * .
																		 * setSelectedExemptionList
																		 * (
																		 * commonBo
																		 * .
																		 * getExemptionList
																		 * (
																		 * Integer
																		 * .
																		 * toString
																		 * (
																		 * regForm
																		 * .
																		 * getDuty_txn_id
																		 * ()),
																		 * languageLocale
																		 * ));
																		 * 
																		 * NumberFormat
																		 * obj
																		 * =new
																		 * DecimalFormat
																		 * (
																		 * "#0.00"
																		 * );//
																		 * FOLLOWING
																		 * CODE
																		 * FOR
																		 * DUTY.
																		 * 
																		 * //IN
																		 * CASE
																		 * OF
																		 * MULTIPLE
																		 * PROPERTIES
																		 * double
																		 * dutyArray
																		 * [] =
																		 * new
																		 * double
																		 * [3];
																		 * double
																		 * nagarPalikaDuty
																		 * =
																		 * 0.0;
																		 * double
																		 * panchayatDuty
																		 * =
																		 * 0.0;
																		 * double
																		 * upkarDuty
																		 * =
																		 * 0.0;
																		 * double
																		 * total
																		 * =
																		 * 0.0;
																		 * double
																		 * stampDuty
																		 * =0.0;
																		 * double
																		 * regFee
																		 * =0.0;
																		 * stampDuty
																		 * =
																		 * commonBo
																		 * .
																		 * getDutyCalculation
																		 * (
																		 * regForm
																		 * );
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * logger.
																		 * debug
																		 * (
																		 * "after first procedure of duty calculation"
																		 * );
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * regForm.
																		 * setStampduty1
																		 * (obj.
																		 * format
																		 * (
																		 * stampDuty
																		 * ));
																		 * 
																		 * dutyArray
																		 * =
																		 * commonBo
																		 * .
																		 * getMunicipalDutyCalculation
																		 * (
																		 * regForm
																		 * );
																		 * logger
																		 * .
																		 * debug
																		 * (
																		 * "after second procedure of duty calculation"
																		 * ); if
																		 * (
																		 * dutyArray
																		 * .
																		 * length
																		 * >= 1)
																		 * {
																		 * nagarPalikaDuty
																		 * =(
																		 * dutyArray
																		 * [0]);
																		 * panchayatDuty
																		 * =(
																		 * dutyArray
																		 * [1]);
																		 * upkarDuty
																		 * =(
																		 * dutyArray
																		 * [2]);
																		 * }
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
																		 * regForm
																		 * .
																		 * setNagarPalikaDuty
																		 * (obj.
																		 * format
																		 * (
																		 * nagarPalikaDuty
																		 * ));
																		 * regForm
																		 * .
																		 * setPanchayatDuty
																		 * (obj.
																		 * format
																		 * (
																		 * panchayatDuty
																		 * ));
																		 * regForm
																		 * .
																		 * setUpkarDuty
																		 * (obj.
																		 * format
																		 * (
																		 * upkarDuty
																		 * ));
																		 * regForm
																		 * .
																		 * setTotalduty
																		 * (obj.
																		 * format
																		 * (
																		 * total
																		 * ));
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * regForm.
																		 * setTotalPayableAmount
																		 * (obj.
																		 * format
																		 * (
																		 * Double
																		 * .
																		 * parseDouble
																		 * (
																		 * regForm
																		 * .
																		 * getTotalduty
																		 * (
																		 * ))));
																		 * regForm
																		 * .
																		 * setTotalPaidAmount
																		 * (obj.
																		 * format
																		 * (
																		 * Double
																		 * .
																		 * parseDouble
																		 * (
																		 * "0"))
																		 * );
																		 * regForm
																		 * .
																		 * setTotalBalanceAmount
																		 * (obj.
																		 * format
																		 * (
																		 * Double
																		 * .
																		 * parseDouble
																		 * (
																		 * regForm
																		 * .
																		 * getTotalduty
																		 * (
																		 * ))));
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * regForm.
																		 * setRegInitEstampCode
																		 * (
																		 * null)
																		 * ;
																		 * 
																		 * 
																		 * regFee
																		 * =
																		 * commonBo
																		 * .
																		 * getRegistrationFee
																		 * (
																		 * regForm
																		 * );
																		 * logger
																		 * .
																		 * debug
																		 * (
																		 * "after third procedure of duty calculation"
																		 * );
																		 * regForm
																		 * .
																		 * setRegistrationFee
																		 * (obj.
																		 * format
																		 * (
																		 * regFee
																		 * ));
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * //below
																		 * code
																		 * for
																		 * inserting
																		 * duties
																		 * into
																		 * reg
																		 * init
																		 * table
																		 * String
																		 * []
																		 * param
																		 * =
																		 * {regForm
																		 * .
																		 * getHidnRegTxnId
																		 * (
																		 * ),regForm
																		 * .
																		 * getStampduty1
																		 * (
																		 * ),regForm
																		 * .
																		 * getPanchayatDuty
																		 * (),
																		 * regForm
																		 * .
																		 * getNagarPalikaDuty
																		 * (
																		 * ),regForm
																		 * .
																		 * getUpkarDuty
																		 * (
																		 * ),regForm
																		 * .
																		 * getRegistrationFee
																		 * (),
																		 * regForm
																		 * .
																		 * getTotalduty
																		 * (
																		 * ),session
																		 * .
																		 * getAttribute
																		 * (
																		 * "UserId"
																		 * )
																		 * .toString
																		 * ()};
																		 * /
																		 * /String
																		 * []
																		 * param
																		 * =
																		 * {regForm
																		 * ,
																		 * session
																		 * .
																		 * getAttribute
																		 * (
																		 * "UserId"
																		 * )
																		 * .toString
																		 * ()};
																		 * 
																		 * 
																		 * boolean
																		 * stampDutiesInserted
																		 * =
																		 * false
																		 * ;
																		 * stampDutiesInserted
																		 * =
																		 * commonBo
																		 * .
																		 * insertStampDuties
																		 * (
																		 * regForm
																		 * ,
																		 * session
																		 * .
																		 * getAttribute
																		 * (
																		 * "UserId"
																		 * )
																		 * .toString
																		 * ());
																		 * //
																		 * CALCULATE_DUTIES_ACTION
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * logger.
																		 * debug
																		 * (
																		 * "multiple properties duties insertion status:--"
																		 * +
																		 * stampDutiesInserted
																		 * );
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * regForm.
																		 * setIsDutyCalculated
																		 * (1);
																		 * regForm
																		 * .
																		 * setActionName
																		 * (
																		 * RegInitConstant
																		 * .
																		 * NO_ACTION
																		 * );
																		 * forward
																		 * =
																		 * "reginitConfirm"
																		 * ;
																		 */
		}
		if (request.getParameter("label") != null) {
			if (request.getParameter("label").equalsIgnoreCase("displayConsenter")) {
				if (request.getParameter("fromPage") != null && request.getParameter("fromPage").toString().equalsIgnoreCase("consenterDetls")) {
					regForm.setConfirmationFlag("00");
				} else {
					regForm.setConfirmationFlag("01");
				}
				regForm.setPartyModifyFlag(0);
				commonBo.openConsenterView(request, regForm, languageLocale);
				// aadhar integration
				regForm.setAadharCheck("");
				regForm.setAadharErrMsg("");
				regForm.setAadharName("");
				regForm.setAadharRespCode("");
				forward = "displayConsenterDetls";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayDuties")) {
				commonBo.getDutiesView(regForm, languageLocale);
				forward = "dutyView";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayConsenterDuties")) {
				commonBo.getConsenterDutiesView(regForm, languageLocale);
				forward = "displayConsenterDuties";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayParty")) {
				regForm.setPartyModifyFlag(0);
				regForm.setOwnerModifyFlag(0);
				/*
				 * if (regForm.getListIDTrns().equalsIgnoreCase("7")) {
				 * 
				 * regForm.setPartyDisplayAadhar("1"); }
				 */
				commonBo.openPartyView(request, regForm, languageLocale);
				forward = "displayRegDetls";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayProperty")) {
				regForm.setClrRcmsFlag("");
				String confirmationFlag = null;
				confirmationFlag = (String) request.getParameter("confirmationFlag");
				if (confirmationFlag != null && confirmationFlag != "") {
					if (confirmationFlag.equalsIgnoreCase("false")) {
						regForm.setConfirmationFlag("00");
					} else {
						regForm.setConfirmationFlag("01");
					}
				} else {
					regForm.setConfirmationFlag("01");
				}
				String propertyId = "";
				if (request.getParameter("key") != null)
					propertyId = request.getParameter("key");
				else if (request.getAttribute("key") != null)
					propertyId = (String) request.getAttribute("key");
				regForm.setPropertyId(propertyId);
				regForm.setMapPropertyTransPartyDisp(commonBo.getPropertyDetsForViewConfirm(regForm.getHidnRegTxnId(), propertyId, languageLocale));
				// Added by Rakesh for CLR property view and bifurcation : Start
				regCompForm = new RegCompletionForm();
				CommonForm comForm = new CommonForm();
				String propTypeID = "";
				propTypeID = commonBo.getPropertyTypeID(propertyId);
				regForm.setPropertyTypeID(propTypeID);
				if (regForm.getPropertyTypeID().equalsIgnoreCase("3")) {
					String clrRcmsFlag = "N";
					clrRcmsFlag = commonBo.getClrRCMSFlag(regForm.getPropertyId());
					if (clrRcmsFlag.equalsIgnoreCase("y")) {
						regForm.setClrRcmsFlag(clrRcmsFlag);
					}
				}
				// Commented by Rakesh for clr 2
				// comForm.setPropertyId(regForm.getPropertyId());
				// ArrayList
				// clrPropView=regCompForm.setClrKhasraViewDetls(commonBo.getPropDetlsForDisplayClr(regForm.getPropertyId()));
				/*
				 * ArrayList clrPropView = commonBo
				 * .getPropDetlsForDisplayClr(regForm.getPropertyId());
				 * 
				 * PropertiesFileReader pr = PropertiesFileReader
				 * .getInstance("resources.igrs");
				 * 
				 * ArrayList subList; for (int i = 0; i < clrPropView.size();
				 * i++) { subList = (ArrayList) clrPropView.get(i);
				 * System.out.println("subListSize:" + subList);
				 * regCompForm.setClrFlag((String) subList.get(0));
				 * regForm.setClrFlg((String) subList.get(0));
				 * 
				 * if((String) subList.get(0)!=null ){
				 * if(subList.get(0).toString().equalsIgnoreCase("Y")){ if
				 * (subList.get(0) != null) { regCompForm.setClrFlag((String)
				 * subList.get(0)); comForm.setClrFlag((String) subList.get(0));
				 * } else { regCompForm.setClrFlag("N"); }
				 * 
				 * if (subList.get(1) != null) {
				 * regForm.getRegcompletDto().setRicircle( (String)
				 * subList.get(1)); } else {
				 * regForm.getRegcompletDto().setRicircle(
				 * pr.getValue("clr_no_data_found")); } if (subList.get(2) !=
				 * null) { regForm.getRegcompletDto().setClrkhasraNo( (String)
				 * subList.get(2)); } else {
				 * regForm.getRegcompletDto().setClrkhasraNo(
				 * pr.getValue("clr_no_data_found")); } if (subList.get(3) !=
				 * null) { regForm.getRegcompletDto().setClrKhasraArea( (String)
				 * subList.get(3)); } else {
				 * regForm.getRegcompletDto().setClrKhasraArea(
				 * pr.getValue("clr_no_data_found")); } if (subList.get(4) !=
				 * null) { regForm.getRegcompletDto().setNorthKhasraNo( (String)
				 * subList.get(4)); } else {
				 * regForm.getRegcompletDto().setNorthKhasraNo(
				 * pr.getValue("clr_no_data_found")); } if (subList.get(5) !=
				 * null) { regForm.getRegcompletDto().setSouthKhasraNo( (String)
				 * subList.get(5)); } else {
				 * regForm.getRegcompletDto().setSouthKhasraNo(
				 * pr.getValue("clr_no_data_found")); } if (subList.get(6) !=
				 * null) { regForm.getRegcompletDto().setEastKhasraNo( (String)
				 * subList.get(6)); } else {
				 * regForm.getRegcompletDto().setEastKhasraNo(
				 * pr.getValue("clr_no_data_found")); } if (subList.get(7) !=
				 * null) { regForm.getRegcompletDto().setWestKhasraNo( (String)
				 * subList.get(7)); } else {
				 * regForm.getRegcompletDto().setWestKhasraNo(
				 * pr.getValue("clr_no_data_found")); } if (subList.get(8) !=
				 * null) { regForm.getRegcompletDto().setMapPathClr( (String)
				 * subList.get(8)); } else {
				 * regForm.getRegcompletDto().setMapPathClr(
				 * pr.getValue("clr_no_data_found")); } if (subList.get(9) !=
				 * null) { regForm.getRegcompletDto().setKhasraPathClr( (String)
				 * subList.get(9)); } else {
				 * regForm.getRegcompletDto().setKhasraPathClr(
				 * pr.getValue("clr_no_data_found")); }
				 * 
				 * 
				 * 
				 * //clr party owner details display: Start ArrayList
				 * partyDetails=
				 * commonBo.getPartyDetlsForDisplayClr(regForm.getPropertyId());
				 * regForm.getRegcompletDto().setClrOwnerArray(partyDetails);
				 * ArrayList<RegCompletDTO> paramList1 = new
				 * ArrayList<RegCompletDTO>();
				 * 
				 * 
				 * 
				 * RegCompletDTO paramDto1 ; ArrayList subList2; for (int k = 0;
				 * k < partyDetails.size(); k++) { //row_list = (RegCompletDTO)
				 * ownerDetails.get(i); subList2 = (ArrayList)
				 * partyDetails.get(k); //Owner ow = (Owner)
				 * partyDetails.get(i); paramDto1 = new RegCompletDTO();
				 * paramDto1.setPartyDetails(subList2.get(0).toString());
				 * paramDto1.setCasteOfParty(subList2.get(1).toString());
				 * paramDto1.setShareOfParty(subList2.get(2).toString());
				 * //paramDto1.setShareOfParty(subList2.get(3).toString());
				 * 
				 * paramList1.add(paramDto1);
				 * 
				 * } regForm.setClrOwnerArray(paramList1); //clr party owner
				 * details display: End
				 * 
				 * 
				 * } } }
				 */
				// regForm.setConfirmationFlag("01");
				if (regForm.getPropertyId() != null) {
					/*
					 * String getPropType = commonBo.getPropertyTypeID(regForm
					 * .getPropertyId());
					 * regCompForm.setPropertyTypeId(getPropType);
					 * regForm.setPropertyTypeID(getPropType);
					 */
					ArrayList propdet = commonBo.getPropertyTypeIdAndClrFlag(regForm.getPropertyId());
					String propertyTypeId = "";
					String clrFlag = "";
					for (int i = 0; i < propdet.size(); i++) {
						ArrayList clrList = (ArrayList) propdet.get(i);
						propertyTypeId = (String) clrList.get(0);
						regCompForm.setPropertyTypeId(propertyTypeId);
						clrFlag = (String) clrList.get(1);
						regCompForm.setClrFlag(clrFlag);
					}
				}
				if (regCompForm.getPropertyTypeId().equalsIgnoreCase("3")) {
					if (regCompForm.getClrFlag() != null) {
						if (regCompForm.getClrFlag().equalsIgnoreCase("Y")) {
							forward = "propertyViewClr";
						} else {
							forward = "propertyView";
						}
					} else {
						forward = "propertyView";
					}
				} else {
					forward = "propertyView";
				}
				// Added by Rakesh for CLR property view and bifurcation : End
				// forward = "propertyView";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayCLRdata")) {
				regForm.setClrLink("");
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				// Desktop d = Desktop.getDesktop();
				String propID = "";
				if (request.getParameter("key") != null)
					propID = request.getParameter("key");
				else if (request.getAttribute("key") != null)
					propID = (String) request.getAttribute("key");
				ArrayList validateKhasraDetls = commonBo.getKhasraToValidate(propID);
				StringBuilder commaSepValueKhasra = new StringBuilder();
				for (int i = 0; i < validateKhasraDetls.size(); i++) {
					ArrayList clrList = (ArrayList) validateKhasraDetls.get(i);
					commaSepValueKhasra.append(clrList.get(0));
					if (i != validateKhasraDetls.size() - 1) {
						commaSepValueKhasra.append(",");
					}
				}
				System.out.println("khasra list   " + commaSepValueKhasra.toString());
				String colonyId = "";
				colonyId = commonBo.getCLRcensusCode(regForm.getHidnRegTxnId(), propID);
				// colonyId ="05020100002004";
				System.out.println("khasra list with colony   " + colonyId);
				// colonyId = "03010100060154";
				// commaSepValueKhasra = new StringBuilder();
				// commaSepValueKhasra.append("2,9,1/11");
				String khasraUrl6 = pr.getValue("khasraUrl6");
				String khasraUrl7 = pr.getValue("khasraUrl7");
				khasraUrl6 = khasraUrl6 + colonyId;
				khasraUrl7 = khasraUrl7 + commaSepValueKhasra;
				String finalKhasraURL = khasraUrl6 + khasraUrl7;
				long startTime = System.currentTimeMillis();
				logger.info("*********finalKhasraURL Time Start for khasra Id: " + finalKhasraURL + " is: " + startTime + " Milli Seconds************");
				URL url = new URL(finalKhasraURL);
				// forward="";
				// d.browse(new URI(finalKhasraURL));//to open url in new tab
				regForm.setClrLink(finalKhasraURL);
				logger.debug("clr link  " + regForm.getClrLink());
				/*
				 * HttpURLConnection conn; String
				 * proxyFlag=pr.getValue("proxyFlag");
				 * if(proxyFlag.equalsIgnoreCase("Y")){ try{ String proxyIp =
				 * pr.getValue("http.proxyHost"); String proxyPort =
				 * pr.getValue("http.proxyPort"); if(proxyIp != null &&
				 * !proxyIp.isEmpty() && proxyPort != null &&
				 * !proxyPort.isEmpty()) { Proxy proxy = new
				 * Proxy(java.net.Proxy.Type.HTTP, new
				 * InetSocketAddress(proxyIp, Integer.parseInt(proxyPort))); } }
				 * catch (Exception e) { logger.debug("Error message : "+
				 * e.getMessage(), e); }
				 * 
				 * conn = (HttpURLConnection) url .openConnection(proxy); }else{
				 * conn = (HttpURLConnection) url .openConnection(); }
				 */
				/*
				 * conn.setRequestMethod("GET");
				 * conn.setRequestProperty("Accept", "text/xml");
				 * conn.setRequestMethod("HEAD"); int ConnectTimeout =
				 * Integer.valueOf(pr.getValue("igrs_khasra_timeout")); int
				 * ReadTimeout =
				 * Integer.valueOf(pr.getValue("igrs_khasra_timeout_read"));
				 * conn.setConnectTimeout(ConnectTimeout*1000);
				 * conn.setReadTimeout(ReadTimeout*1000);
				 * 
				 * logger.debug("Response Code :- "+ conn.getResponseCode());
				 */
				forward = "displayCLRdata";
				/*
				 * if (request.getParameter("fromPage") != null) { if
				 * (request.getParameter("fromPage").equalsIgnoreCase(
				 * "propFlow")) { forward = "propertyView"; }
				 * 
				 * else if(request.getParameter("fromPage").equalsIgnoreCase(
				 * "nonPropFlow")){
				 * 
				 * forward = "propertyViewNPV"; } }
				 * 
				 * else{
				 * 
				 * forward = "propertyView"; }
				 */
			}
			if (request.getParameter("label").equalsIgnoreCase("displayExtra")) {
				commonBo.getExtraDetls(regForm, languageLocale);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				logger.debug("request deed ---->" + request.getAttribute("deedId"));
				regForm.setCallingAction("regInit.do?TRFS=NGI");
				regForm.setExtraDetlsModifyFlag(0);
				forward = "displayExtraDetls";
			}
			if (request.getParameter("label").equalsIgnoreCase("fromAdjudication")) {
				forward = "searchAdju";
			}
		}
		// END
		// following code for skipping applicant details page in case of
		// multiple properties.
		// below code chunk is nor used in new reg init flow
		if (request.getParameter("modName") != null) {/*
													 * if(request.getParameter("modName"
													 * ).equalsIgnoreCase(
													 * "RegistrationProperty")&&
													 * request
													 * .getAttribute("propDTO"
													 * )!=null){
													 * 
													 * 
													 * 
													 * 
													 * 
													 * if(regForm.
													 * getIsMultiplePropsFlag
													 * ()==1) {
													 * PropertyValuationDTO
													 * propDTO
													 * =(PropertyValuationDTO
													 * )request
													 * .getAttribute("propDTO");
													 * 
													 * regForm.setValuationId(
													 * propDTO
													 * .getValuationId());
													 * logger.debug(
													 * "valuation id----------->"
													 * +
													 * propDTO.getValuationId())
													 * ;
													 * //regForm.setMarketValue
													 * (eForm
													 * .getDutyCalculationDTO
													 * ().getPropDTO
													 * ().getFinalMarketValue
													 * ());
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * //if(pform.getInstrumentDTO
													 * (
													 * ).getLabelAmountFlag()!=
													 * null &&
													 * pform.getInstrumentDTO(
													 * ).getLabelAmountFlag
													 * ()!="") //{
													 * //session.setAttribute
													 * ("labelAmountFlag",
													 * pform.getInstrumentDTO().
													 * getLabelAmountFlag());
													 * //regForm
													 * .setLabelAmountFlag
													 * (session.getAttribute(
													 * "labelAmountFlag"
													 * ).toString()); //}
													 * //if(session
													 * .getAttribute(
													 * "labelAmountFlag"
													 * )!=null){ //
													 * regForm.setLabelAmountFlag
													 * (session.getAttribute(
													 * "labelAmountFlag"
													 * ).toString()); //} String
													 * distId=""; String
													 * tehslId=""; String
													 * propertyId="";
													 * //following code in case
													 * of Exchange deed
													 * if(regForm
													 * .getExchangePropertyList
													 * ().size()==0) {
													 * if(regForm
													 * .getDeedID()==RegInitConstant
													 * .DEED_EXCHANGE ||
													 * regForm.
													 * getDeedID()==RegInitConstant
													 * .DEED_PARTITION_PV){
													 * 
													 * commonBo.
													 * getExchangePropertyList
													 * (regForm,0,null);
													 * 
													 * 
													 * }else{
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * if(propDTO.getPropertyId()
													 * .equalsIgnoreCase("3")){
													 * 
													 * StringmainValId=propDTO.
													 * getValuationId();
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * commonBo.getAgriPropertyList
													 * (regForm,0,null);
													 * 
													 * 
													 * 
													 * }else{
													 * distId=propDTO.getDistrictId
													 * ();
													 * if(distId.length()==1)
													 * distId="0"+distId;
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * tehslId=propDTO.getTehsilId
													 * ();
													 * if(tehslId.length()==1)
													 * tehslId="0"+tehslId;
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * if((regForm.getPropertyId(
													 * )=="" ||
													 * regForm.getPropertyId
													 * ()==null) ) {
													 * propertyId=distId
													 * +tehslId+commonBo.
													 * getPropertyIdSequence();
													 * logger.debug(
													 * "property id------------>"
													 * +propertyId);
													 * if(propertyId
													 * .length()==15)
													 * regForm.setPropertyId
													 * ("0"+propertyId); else
													 * regForm
													 * .setPropertyId(propertyId
													 * ); } } //} //end of code
													 * for property id
													 * generation by roopam //}
													 * 
													 * if(regForm.getDeedID()!=
													 * RegInitConstant
													 * .DEED_TRUST_PV) {
													 * //FOLLOWING CODE FOR
													 * INSERTING APPLICANT
													 * DETAILS IN HASHMAP
													 * map=regForm
													 * .getMapTransactingParties
													 * ();regForm.
													 * setMapTransactingParties
													 * (commonBo
													 * .insertApplicantDetsInMap
													 * (map,"",
													 * regForm.getHidnRegTxnId
													 * (),
													 * regForm,languageLocale));
													 * }else{
													 * regForm.setAddMoreCounter
													 * (0);regForm.
													 * setHdnDeclareShareCheck
													 * ("Y"); }
													 * 
													 * String[]
													 * deedInstArray=commonBo
													 * .getDeedInstId
													 * (regForm.getHidnRegTxnId
													 * ());
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * regForm.setDeedID(Integer.
													 * parseInt
													 * (deedInstArray[0].
													 * trim()));
													 * regForm.setInstID
													 * (Integer.
													 * parseInt(deedInstArray
													 * [1].trim()));
													 * if(deedInstArray
													 * [2].trim().equals("-") ||
													 * deedInstArray
													 * [2].trim().equals("")){
													 * regForm.setExmpID("");
													 * regForm
													 * .setHdnExAmount("");
													 * }else{regForm.setExmpID(
													 * deedInstArray[2].trim());
													 * regForm
													 * .setHdnExAmount(deedInstArray
													 * [2].trim()); }
													 * 
													 * commonDto.setPartyType(
													 * commonBo
													 * .getPartyType(regForm
													 * .getDeedID
													 * (),regForm.getInstID
													 * (),languageLocale));
													 * commonDto
													 * .setAppTypeTrns(commonBo
													 * .getAppType
													 * (languageLocale));
													 * commonDto
													 * .setCountryTrns(commonBo
													 * .getCountry
													 * (languageLocale));
													 * commonDto
													 * .setIndcountryTrns
													 * (commonBo
													 * .getCountry(languageLocale
													 * ));
													 * commonDto.setIdProfTrns
													 * (commonBo
													 * .getIdProf(languageLocale
													 * ));
													 * commonDto.setCategoryList
													 * (
													 * commonBo.getCategoryList(
													 * languageLocale));
													 * commonDto
													 * .setOccupationList
													 * (commonBo
													 * .getOccupationList
													 * (languageLocale));
													 * regForm
													 * .setDeedId(Integer.
													 * toString
													 * (regForm.getDeedID()));
													 * regForm
													 * .setHidnUserId(userId);
													 * 
													 * //FOLLOWING CODE FOR
													 * INSERTING PROPERTY
													 * DETAILS THROUGH PV INTO
													 * DB boolean
													 * multiplePropDetlsInserted
													 * ;
													 * multiplePropDetlsInserted
													 * =commonBo.
													 * insrtMultiplePropDetls
													 * (regForm);logger.debug(
													 * "multiple property details insertion status---------->"
													 * +
													 * multiplePropDetlsInserted
													 * );
													 * 
													 * if(regForm.getDeedID()!=
													 * RegInitConstant
													 * .DEED_TRUST_PV) {
													 * //FOLLOWING CODE FOR
													 * FETCHING APPLICANT ROLE
													 * ID. //disabling applicant
													 * role in multiple
													 * properties. String
													 * applicantRoleId
													 * =commonBo.
													 * getApplicantRoleId (
													 * regForm.getHidnRegTxnId()
													 * );logger.debug(
													 * "applicant role id---------->"
													 * +applicantRoleId);
													 * regForm
													 * .setApplicantRoleId
													 * (Integer
													 * .parseInt(applicantRoleId
													 * )); // int
													 * totalShare=commonBo
													 * .getShareOfPropList
													 * (regForm
													 * .getHidnRegTxnId());
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * 
													 * regForm.setTotalShareOfProp
													 * (totalShare);
													 * 
													 * }
													 * if(multiplePropDetlsInserted
													 * ) forward
													 * ="transactingParty"; else
													 * forward ="failure"; } }
													 */
		}
		// commonDto.setPartyType(commonBo.getPartyType(regForm.getDeedID(),regForm.getInstID(),languageLocale));
		/*
		 * commonDto.setAppTypeTrns(commonBo.getAppType());
		 * commonDto.setCountryTrns(commonBo.getCountry());
		 * commonDto.setIndcountryTrns(commonBo.getCountry());
		 * commonDto.setIdProfTrns(commonBo.getIdProf());
		 */
		// after searching adjudication number
		if (RegInitConstant.ADJUDICATION_ACTION.equals(actionName)) {
			/*
			 * if(regForm.getDeedID()==RegInitConstant.DEED_ADOPTION ||
			 * regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
			 * regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
			 * regForm
			 * .getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA ||
			 * regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_POA ||
			 * regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV
			 * || regForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV ||
			 * regForm.getCommonDeed()==1){ //for deeds following code set2
			 * 
			 * 
			 * if(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV
			 * || regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV ||
			 * regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV){
			 * 
			 * if(
			 * regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_1 ||
			 * regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_2 ||
			 * regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_3 ||
			 * regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_4 ||
			 * regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_6 ||
			 * regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_7 ||
			 * regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_9 ||
			 * regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_1 ||
			 * regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_2 ||
			 * regForm.getInstID()==RegInitConstant.INST_FURTHER_CHARGE_NPV_1){
			 * 
			 * regForm.setAgreeMemoInstFlag(RegInitConstant.
			 * NPV_PROP_NOT_REQ_CONSTANT_2);
			 * 
			 * }else
			 * if(regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_10
			 * || regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_5
			 * || regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_8
			 * || regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_3 ||
			 * regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_4 ||
			 * regForm.getInstID()==RegInitConstant.INST_FURTHER_CHARGE_NPV_2){
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * regForm.setAgreeMemoInstFlag(RegInitConstant.NPV_PROP_REQ_CONSTANT_1
			 * ); }
			 * 
			 * 
			 * }
			 * 
			 * regForm.setDeedTypeFlag(1);
			 * //regForm.setHdnDeclareShareCheck("N");
			 * 
			 * 
			 * }
			 */
			regForm.setActionName(RegInitConstant.NO_ACTION);
			String adjudicationId = regForm.getAdjudicationNumber();
			// following code for searching adjudication number and its status
			// in database.
			String[] adjuArray = commonBo.getAdjudicationStatus(adjudicationId);
			if (adjuArray != null) {
				if (adjuArray[0].trim().equalsIgnoreCase("A")) {
					if (adjuArray[1].trim().equalsIgnoreCase("10")) {
						// int initiateAdjuApp=1;
						regForm.setHidnRegTxnId(adjudicationId);
						boolean adjuFlag;
						adjuFlag = commonBo.updateAdjudicationFlag(adjudicationId, session.getAttribute("UserId").toString());
						if (adjuFlag) {
							String[] deedInstArray = commonBo.getDeedInstId(regForm.getAdjudicationNumber());
							regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
							regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
							if (deedInstArray[2].trim().equals("-") || deedInstArray[2].trim().equals("")) {
								regForm.setExmpID("");
								regForm.setHdnExAmount("");
							} else {
								regForm.setExmpID(deedInstArray[2].trim());
								regForm.setHdnExAmount(deedInstArray[2].trim());
							}
							if (deedInstArray[5] != null) {
								regForm.setDuty_txn_id(Integer.parseInt(deedInstArray[5].trim()));
							} else {
								regForm.setDuty_txn_id(0);
							}
							String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
							if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {
								if (flags[2].trim().equalsIgnoreCase("Y")) {
									regForm.setCommonDeed(1);
								} else {
									regForm.setCommonDeed(0);
								}
								regForm.setPvReqFlag(flags[1].trim());
								regForm.setPropReqFlag(flags[0].trim());
							} else {
								regForm.setCommonDeed(0);
								regForm.setPvReqFlag("");
								regForm.setPropReqFlag("");
							}
							regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()), languageLocale));
							regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()), languageLocale));
							regForm.setSelectedExemptionList(commonBo.getExemptionList(Integer.toString(regForm.getDuty_txn_id()), languageLocale));
							regForm.setFamilyFlag(commonBo.getFamilyFlag(Integer.toString(regForm.getDuty_txn_id())));
							// regForm.setInitiateAdjuApp(1);
							commonBo.landConfirmationScreen(regForm, languageLocale, request);
							request.setAttribute("deedId", regForm.getDeedID());
							request.setAttribute("instId", regForm.getInstID());
							// session.setAttribute("fnName","Initiation");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
							} else {
								session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
							}
							if (regForm.getPvReqFlag().equalsIgnoreCase("N")) {
								forward = "reginitConfirmNonPV";
							} else {
								forward = "reginitConfirm";
							}
						} else {
							String msg = RegInitConstant.ADJU_MSG_1;
							regForm.setErrorMsg(msg);
							forward = "searchAdju";
						}
					} else if (adjuArray[1].trim().equalsIgnoreCase("100")) {
						String msg = RegInitConstant.ADJU_MSG_3;
						regForm.setErrorMsg(msg);
						forward = "searchAdju";
					} else {
						String msg = RegInitConstant.ADJU_MSG_1;
						regForm.setErrorMsg(msg);
						forward = "searchAdju";
					}
				} else if (adjuArray[0].trim().equalsIgnoreCase("R")) {
					String msg = RegInitConstant.ADJU_MSG_4;
					regForm.setErrorMsg(msg);
					forward = "searchAdju";
				} else {
					String msg = RegInitConstant.ADJU_MSG_2;
					regForm.setErrorMsg(msg);
					forward = "searchAdju";
				}
			} else {
				String msg = RegInitConstant.ADJU_MSG_2;
				regForm.setErrorMsg(msg);
				forward = "searchAdju";
			}
		}/*
		 * else{ String msg=RegInitConstant.ADJU_MSG_2;
		 * regForm.setErrorMsg(msg); forward="searchAdju"; }
		 */
		/*
		 * regForm.setHidnUserId(userId);
		 * 
		 * //for generating temporary Registration Initiation id. Calendar
		 * currentDate = Calendar.getInstance(); SimpleDateFormat formatter= new
		 * SimpleDateFormat("ddMMyy"); String dateNow =
		 * formatter.format(currentDate.getTime()); String regTxnIdSeq=
		 * commonBo.getRegInitTxnIdSeq(); String regTxnId=null;
		 * regTxnId=dateNow+regTxnIdSeq; regForm.setHidnRegTxnId(regTxnId);
		 * //code for insertion of registration id and status in initiation
		 * tables. boolean
		 * regIdUpdated=commonBo.updateAdjudicationRecords(regForm
		 * .getHidnRegTxnId(), adjudicationId, regForm.getHidnUserId());
		 */
		/*
		 * if(regIdUpdated) { try{ String[]
		 * deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
		 * if(deedInstArray!=null && deedInstArray.length>0){
		 * 
		 * request.setAttribute("deedId", deedInstArray[0].trim());
		 * request.setAttribute("instId", deedInstArray[1].trim());
		 * regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
		 * regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
		 * if(deedInstArray[2].trim().equals("-")){ regForm.setExmpID("");
		 * regForm.setHdnExAmount(""); }else{
		 * regForm.setExmpID(deedInstArray[2].trim());
		 * regForm.setHdnExAmount(deedInstArray[2].trim()); }
		 * 
		 * }else { request.setAttribute("deedId", 0);
		 * request.setAttribute("instId", 0); regForm.setDeedID(0);
		 * regForm.setInstID(0); regForm.setExmpID("0");
		 * regForm.setHdnExAmount("0"); }
		 * 
		 * regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.
		 * getDeedID())));
		 * regForm.setInstType(commonBo.getInstrumentName(Integer
		 * .toString(regForm.getInstID()))); //below code for exemptions
		 * 
		 * String exemptions = regForm.getExmpID();
		 * regForm.setSelectedExemptionList
		 * (commonBo.getExemptionList(exemptions));
		 * 
		 * HashMap propMap =new HashMap();
		 * propMap=regForm.getMapPropertyTransParty();
		 * logger.debug("in confirmation action----------------------->");
		 * 
		 * ArrayList
		 * propertyIdList=commonBo.getPropertyIdMarketValAdju(adjudicationId);
		 * //GETTING ADJUDICATED MARKET VALUES double totalMarketVal=0;
		 * 
		 * int numberOfProperties=propertyIdList.size(); ArrayList row_list=new
		 * ArrayList(); String propIds; String propId[]; //above declarations
		 * because of audit report for(int i=0;i<propertyIdList.size();i++){
		 * 
		 * //ArrayList row_list=new ArrayList();
		 * row_list=(ArrayList)propertyIdList.get(i);
		 * propIds=row_list.toString(); propIds=propIds.substring(1,
		 * propIds.length()-1);
		 * logger.debug("property id and market value list-->"+propIds);
		 * propId=propIds.split(",");
		 * 
		 * totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
		 * 
		 * 
		 * 
		 * ArrayList
		 * transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm
		 * .getHidnRegTxnId());
		 * 
		 * 
		 * for(int j=0;j<transPartyDets.size();j++){
		 * 
		 * CommonDTO dto=new CommonDTO(); dto=(CommonDTO)transPartyDets.get(j);
		 * logger.debug("transacting party list  role------>"+dto.getRole());
		 * logger.debug("transacting party list  name------>"+dto.getName());
		 * logger.debug("transacting party list  id------>"+dto.getId());
		 * 
		 * } logger.debug("property id------>"+propId[0].trim());
		 * logger.debug("market value------>"+propId[1].trim());
		 * propMap.put(propId[0].trim()+","+propId[1].trim(), transPartyDets);
		 * 
		 * }
		 * 
		 * NumberFormat obj=new DecimalFormat("#0.00");
		 * regForm.setTotalMarketValue(obj.format(totalMarketVal));
		 * regForm.setMapPropertyTransParty(propMap);
		 * 
		 * 
		 * 
		 * String
		 * dutyListArr[]=commonBo.getAdjudicatedDutyDetls(adjudicationId);
		 * //GETTING ADJUDICATED DUTIES
		 * 
		 * if(numberOfProperties>0) { if(dutyListArr!=null){
		 * regForm.setStampduty1
		 * (obj.format(Double.parseDouble(dutyListArr[0].trim())));
		 * regForm.setNagarPalikaDuty
		 * (obj.format(Double.parseDouble(dutyListArr[2].trim())));
		 * regForm.setPanchayatDuty
		 * (obj.format(Double.parseDouble(dutyListArr[1].trim())));
		 * regForm.setUpkarDuty
		 * (obj.format(Double.parseDouble(dutyListArr[3].trim())));
		 * regForm.setTotalduty
		 * (obj.format(Double.parseDouble(dutyListArr[5].trim())));
		 * regForm.setRegistrationFee
		 * (obj.format(Double.parseDouble(dutyListArr[4].trim())));
		 * 
		 * if(dutyListArr[6].trim()!=null &&
		 * !dutyListArr[6].trim().equalsIgnoreCase(""))
		 * regForm.setMarketValueShares
		 * (obj.format(Double.parseDouble(dutyListArr[6].trim()))); else
		 * regForm.setMarketValueShares(Double.toString(0.0));
		 * 
		 * if(dutyListArr[7].trim()!=null &&
		 * !dutyListArr[7].trim().equalsIgnoreCase(""))
		 * regForm.setDutyPaid(obj.format
		 * (Double.parseDouble(dutyListArr[7].trim()))); else
		 * regForm.setDutyPaid(Double.toString(0.0));
		 * 
		 * if(dutyListArr[8].trim()!=null &&
		 * !dutyListArr[8].trim().equalsIgnoreCase(""))
		 * regForm.setRegPaid(obj.format
		 * (Double.parseDouble(dutyListArr[8].trim()))); else
		 * regForm.setRegPaid(Double.toString(0.0));
		 * 
		 * if(numberOfProperties==1) {
		 * 
		 * 
		 * regForm.setIsMultiplePropsFlag(0); regForm.setIsDutyCalculated(1);
		 * 
		 * }else if(numberOfProperties>1){
		 * 
		 * regForm.setIsDutyCalculated(1); regForm.setIsMultiplePropsFlag(1);
		 * 
		 * 
		 * }
		 * 
		 * regForm.setRegInitPaymntTxnId(null);
		 * 
		 * 
		 * //forward="reginitConfirmAdju"; forward="reginitConfirm";
		 * 
		 * } else{ String msg="No duties found"; regForm.setErrorMsg(msg);
		 * forward="searchAdju"; } //return mapping.findForward(forward);
		 * 
		 * }else{ String msg="No property found"; regForm.setErrorMsg(msg);
		 * forward="searchAdju"; }
		 * 
		 * }catch(Exception e){
		 * 
		 * forward="failure"; return mapping.findForward(forward);
		 * 
		 * }commonBo.landConfirmationScreen(regForm);
		 * 
		 * }
		 *//*
			 * else{
			 * 
			 * 
			 * try{
			 * 
			 * throw new SQLException();
			 * 
			 * }catch(SQLException e){
			 * 
			 * forward="failure"; return mapping.findForward(forward);
			 * 
			 * }
			 * 
			 * 
			 * }
			 */
		/*
		 * if(RegInitConstant.DECLARE_SHARE_CHECK_ACTION.equalsIgnoreCase(actionName
		 * )){ regForm.setActionName(RegInitConstant.NO_ACTION);
		 * if(regForm.getHdnDeclareShareCheck().equalsIgnoreCase("Y")){
		 * 
		 * regForm.setDeclareShare("true");
		 * 
		 * } else{ regForm.setShareOfProp(""); regForm.setShareOfPropTrns("");
		 * regForm.setDeclareShare(null); }
		 * 
		 * 
		 * request.setAttribute("deedId", regForm.getDeedID());
		 * request.setAttribute("roleId", regForm.getPartyType()); forward
		 * =regForm.getJspName(); //forward ="success";
		 * 
		 * }
		 */
		if (request.getParameter("dms") != null) {
			if (request.getParameter("dms").equalsIgnoreCase("retrieval")) {
				if (request.getParameter("path") != null) {
					String partyType = "";
					String filePath = request.getParameter("path").toString();
					logger.debug("retrieval path-->" + filePath);
					int indexOfDot = filePath.lastIndexOf(".");
					// int totLen=filePath.length();
					// dmsUtil.readImage(filePath);
					String key = request.getParameter("key");
					String fileName = "";
					String fileNameClrKhasra = "";
					String fileNameClrMap = "";
					String fileNameAuditReport = ""; // added by ankit for plant
					// and machinery
					if (filePath != null && !filePath.equalsIgnoreCase("null")) {
						fileName = filePath.substring(indexOfDot - 2, indexOfDot) + ".";
						fileNameClrKhasra = filePath.substring(indexOfDot - 9, indexOfDot) + ".";
						fileNameClrMap = filePath.substring(indexOfDot - 6, indexOfDot) + ".";
						fileNameAuditReport = filePath.substring(indexOfDot - 11, indexOfDot) + ".";
					}
					if (request.getParameter("roleType") != null) {
						partyType = request.getParameter("roleType").toString();
						regForm.setPartyType(partyType);
						logger.debug("role type---------->" + partyType);
						RegCommonDTO dtoObj = (RegCommonDTO) regForm.getMapTransactingPartiesDisp().get(key);
						if (request.getParameter("addUpload") != null && request.getParameter("addUpload").toString().equalsIgnoreCase("1")) {
							String id = request.getParameter("uKey");
							ArrayList<CommonDTO> listDto = dtoObj.getListDto();
							if (id != null && !id.equalsIgnoreCase("")) {
								for (int i = 0; i < listDto.size(); i++) {
									CommonDTO dto = listDto.get(i);
									if (dto.getUniqueIdUpload().equalsIgnoreCase(id)) {
										DMSUtility.downloadDocument(response, dto.getDocumentPath(), dto.getDocContents());
										break;
									}
								}
							}
							// mohit
						} else {
							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_CERTIFICATE)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getDocContentsTrns());
							}
							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PHOTO_PROOF)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getU2DocContentsTrns());
							}
							int roleId = Integer.parseInt(partyType);
							String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
							int claimantFlag = Integer.parseInt(claimantArr[0].trim());
							// BELOW CODE FOR EXECUTANT
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_EXECUTANT)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getU3DocContentsTrns());
								}
								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getU4DocContentsTrns());
								}
								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PAN_FORM_60)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getU10DocContentsTrns());
								}
							}
							// BELOW CODE FOR EXECUTANT POA HOLDER
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_5) {
								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_ATTORNEY)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getU5DocContentsTrns());
								}
								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getU6DocContentsTrns());
								}
								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getU7DocContentsTrns());
								}
							}
							// BELOW CODE FOR CLAIMANT
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_CLAIMANT_PHOTO)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getU8DocContentsTrns());
								}
								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PAN_FORM_60)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getU11DocContentsTrns());
								}
							}
							// BELOW CODE FOR CLAIMANT POA HOLDER
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_6) {
								if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO)) {
									DMSUtility.downloadDocument(response, filePath, dtoObj.getU9DocContentsTrns());
								}
							}
						}
						if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
							forward = "displayRegDetls";
						} else {
							forward = "displayRegDetlsNPV";
						}
						request.setAttribute("deedId", regForm.getDeedID());
						request.setAttribute("instId", regForm.getInstID());
						request.setAttribute("roleId", partyType);
					} else {
						if (request.getParameter("addUpload") != null && request.getParameter("addUpload").toString().equalsIgnoreCase("3")) {
							String id = request.getParameter("uKey");
							ArrayList<CommonDTO> listDto = regForm.getListDtoAdju();
							if (id != null && !id.equalsIgnoreCase("")) {
								for (int i = 0; i < listDto.size(); i++) {
									CommonDTO dto = listDto.get(i);
									if (dto.getUniqueIdUpload().equalsIgnoreCase(id)) {
										DMSUtility.downloadDocument(response, dto.getDocumentPath(), dto.getDocContents());
										break;
									}
								}
							}
							// mohit
							if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
								forward = "reginitConfirm";
							} else {
								forward = "reginitConfirmNonPV";
							}
						} else if (request.getParameter("addUpload") != null && request.getParameter("addUpload").toString().equalsIgnoreCase("4")) {
							String id = request.getParameter("uKey");
							ArrayList<CommonDTO> listDto = regForm.getRegDTO().getListDto();
							if (id != null && !id.equalsIgnoreCase("")) {
								for (int i = 0; i < listDto.size(); i++) {
									CommonDTO dto = listDto.get(i);
									if (dto.getUniqueIdUpload().equalsIgnoreCase(id)) {
										DMSUtility.downloadDocument(response, dto.getDocumentPath(), dto.getDocContents());
										break;
									}
								}
							}
							// mohit
							if (("Y").equalsIgnoreCase(regForm.getPvReqFlag())) {
								forward = "displayConsenterDetls";
							} else {
								forward = "displayConsenterDetlsNPV";
							}
						} else {
							RegCompletDTO dtoObj = (RegCompletDTO) regForm.getMapPropertyTransPartyDisp().get(key);
							if (request.getParameter("addUpload") != null && request.getParameter("addUpload").toString().equalsIgnoreCase("2")) {
								String id = request.getParameter("uKey");
								ArrayList<CommonDTO> listDto = dtoObj.getListDtoP();
								if (id != null && !id.equalsIgnoreCase("")) {
									for (int i = 0; i < listDto.size(); i++) {
										CommonDTO dto = listDto.get(i);
										if (dto.getUniqueIdUpload().equalsIgnoreCase(id)) {
											DMSUtility.downloadDocument(response, dto.getDocumentPath(), dto.getDocContents());
											break;
										}
									}
								}
								// mohit
							}
							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_1)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getPropImage1DocContents());
							}
							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_2)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getPropImage2DocContents());
							}
							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_3)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getPropImage3DocContents());
							}
							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_MAP)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getPropMapDocContents());
							}
							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_RIN)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getPropRinDocContents());
							}
							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_KHASRA)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getPropKhasraDocContents());
							}
							if (fileNameClrMap.equalsIgnoreCase(RegInitConstant.FILE_NAME_CLR_MAP)) {
								byte[] templateContents = DMSUtility.getDocumentBytes(filePath);
								/*
								 * DMSUtility.downloadDocument(response,
								 * filePath, dtoObj.getPropKhasraDocContents());
								 */
								DMSUtility.downloadDocument(response, filePath, templateContents);
							}
							if (fileNameClrKhasra.equalsIgnoreCase(RegInitConstant.FILE_NAME_CLR_KHASRA)) {
								byte[] templateContents = DMSUtility.getDocumentBytes(filePath);
								DMSUtility.downloadDocument(response, filePath, templateContents);
							}
							// Added by ankit for plant and machinery
							// logger.info("fileNameAuditReport : "+fileNameAuditReport);
							if (fileNameAuditReport.equalsIgnoreCase(RegInitConstant.FILE_NAME_AUDIT_REPORT)) {
								// logger.info("downloading content for AuditReport : ");
								// byte[]
								// templateContents=DMSUtility.getDocumentBytes(filePath);
								DMSUtility.downloadDocument(response, filePath, dtoObj.getAuditReportDocContents());
							}
							if (regForm.getPropertyId() != null) {
								String getPropType = commonBo.getPropertyTypeID(regForm.getPropertyId());
								regForm.setPropertyTypeID(getPropType);
							}
							if (regForm.getPvReqFlag().equalsIgnoreCase("Y")) {
								if (regForm.getPropertyTypeID().equalsIgnoreCase("3")) {
									if (regForm.getClrFlg() != null) {
										if (regForm.getClrFlg().equalsIgnoreCase("Y")) {
											forward = "propertyViewClr";
										} else {
											forward = "propertyView";
										}
									} else {
										forward = "propertyView";
									}
								} else {
									forward = "propertyView";
								}
								// forward = "propertyView";
							} else {
								forward = "propertyViewNPV";
							}
						}
					}
				}
			}
			if (request.getParameter("dms").equalsIgnoreCase("retrievalLive")) {
				if (request.getParameter("retrievalType") != null && request.getParameter("retrievalType").toString().equalsIgnoreCase("1")) {
					// below for applicant screen
					if (request.getParameter("appFlag") != null && request.getParameter("appFlag").toString().equalsIgnoreCase("Y")) {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("1")) {
							DMSUtility.downloadDocument(response, regForm.getFilePath(), regForm.getDocContents());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("2")) {
							DMSUtility.downloadDocument(response, regForm.getU2FilePath(), regForm.getU2DocContents());
						}
						// int roleId=Integer.parseInt(partyType);
						// String[]
						// claimantArr=commonBo.getClaimantFlag(Integer.toString(roleId));
						// int
						// claimantFlag=Integer.parseInt(claimantArr[0].trim());
						// BELOW CODE FOR EXECUTANT
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
						// {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("3")) {
							DMSUtility.downloadDocument(response, regForm.getU3FilePath(), regForm.getU3DocContents());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("4")) {
							DMSUtility.downloadDocument(response, regForm.getU4FilePath(), regForm.getU4DocContents());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("10")) {
							DMSUtility.downloadDocument(response, regForm.getU10FilePath(), regForm.getU10DocContents());
						}
						// }
						// BELOW CODE FOR EXECUTANT POA HOLDER
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
						// {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("5")) {
							DMSUtility.downloadDocument(response, regForm.getU5FilePath(), regForm.getU5DocContents());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("6")) {
							DMSUtility.downloadDocument(response, regForm.getU6FilePath(), regForm.getU6DocContents());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("7")) {
							DMSUtility.downloadDocument(response, regForm.getU7FilePath(), regForm.getU7DocContents());
						}
						// }
						// BELOW CODE FOR CLAIMANT
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
						// {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("8")) {
							DMSUtility.downloadDocument(response, regForm.getU8FilePath(), regForm.getU8DocContents());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("11")) {
							DMSUtility.downloadDocument(response, regForm.getU11FilePath(), regForm.getU11DocContents());
						}
						// }
						// BELOW CODE FOR CLAIMANT POA HOLDER
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
						// {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("9")) {
							DMSUtility.downloadDocument(response, regForm.getU9FilePath(), regForm.getU9DocContents());
						}
						// }
						forward = "success";
					}
					// below for transacting party screen
					if (request.getParameter("appFlag") != null && (request.getParameter("appFlag").toString().equalsIgnoreCase("N") || request.getParameter("appFlag").toString().equalsIgnoreCase("E"))) {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("1")) {
							DMSUtility.downloadDocument(response, regForm.getFilePathTrns(), regForm.getDocContentsTrns());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("2")) {
							DMSUtility.downloadDocument(response, regForm.getU2FilePathTrns(), regForm.getU2DocContentsTrns());
						}
						// int roleId=Integer.parseInt(partyType);
						// String[]
						// claimantArr=commonBo.getClaimantFlag(Integer.toString(roleId));
						// int
						// claimantFlag=Integer.parseInt(claimantArr[0].trim());
						// BELOW CODE FOR EXECUTANT
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
						// {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("3")) {
							DMSUtility.downloadDocument(response, regForm.getU3FilePathTrns(), regForm.getU3DocContentsTrns());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("4")) {
							DMSUtility.downloadDocument(response, regForm.getU4FilePathTrns(), regForm.getU4DocContentsTrns());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("10")) {
							DMSUtility.downloadDocument(response, regForm.getU10FilePathTrns(), regForm.getU10DocContentsTrns());
						}
						// }
						// BELOW CODE FOR EXECUTANT POA HOLDER
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
						// {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("5")) {
							DMSUtility.downloadDocument(response, regForm.getU5FilePathTrns(), regForm.getU5DocContentsTrns());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("6")) {
							DMSUtility.downloadDocument(response, regForm.getU6FilePathTrns(), regForm.getU6DocContentsTrns());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("7")) {
							DMSUtility.downloadDocument(response, regForm.getU7FilePathTrns(), regForm.getU7DocContentsTrns());
						}
						// }
						// BELOW CODE FOR CLAIMANT
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
						// {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("8")) {
							DMSUtility.downloadDocument(response, regForm.getU8FilePathTrns(), regForm.getU8DocContentsTrns());
						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("11")) {
							DMSUtility.downloadDocument(response, regForm.getU11FilePathTrns(), regForm.getU11DocContentsTrns());
						}
						// }
						// BELOW CODE FOR CLAIMANT POA HOLDER
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
						// {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("9")) {
							DMSUtility.downloadDocument(response, regForm.getU9FilePathTrns(), regForm.getU9DocContentsTrns());
						}
						// }
						if (request.getParameter("appFlag").toString().equalsIgnoreCase("N")) {
							forward = "transactingParty";
						} else {
							forward = "displayRegDetls";
						}
					}
				}/*
				 * else if(request.getParameter("retrievalType")!=null &&
				 * request
				 * .getParameter("retrievalType").toString().equalsIgnoreCase
				 * ("2")) {
				 * 
				 * RegCompletDTO
				 * dtoObj=(RegCompletDTO)regForm.getMapPropertyTransPartyDisp
				 * ().get(key);
				 * 
				 * if(fileName.equalsIgnoreCase(RegInitConstant.
				 * FILE_NAME_PROP_ANGLE_1)){
				 * DMSUtility.downloadDocument(response,
				 * filePath,dtoObj.getPropImage1DocContents());
				 * 
				 * }
				 * 
				 * if(fileName.equalsIgnoreCase(RegInitConstant.
				 * FILE_NAME_PROP_ANGLE_2)){
				 * DMSUtility.downloadDocument(response,
				 * filePath,dtoObj.getPropImage2DocContents());
				 * 
				 * }
				 * 
				 * if(fileName.equalsIgnoreCase(RegInitConstant.
				 * FILE_NAME_PROP_ANGLE_3)){
				 * DMSUtility.downloadDocument(response,
				 * filePath,dtoObj.getPropImage3DocContents());
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_MAP
				 * )){ DMSUtility.downloadDocument(response,
				 * filePath,dtoObj.getPropMapDocContents());
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_RIN
				 * )){ DMSUtility.downloadDocument(response,
				 * filePath,dtoObj.getPropRinDocContents());
				 * 
				 * }
				 * 
				 * if(fileName.equalsIgnoreCase(RegInitConstant.
				 * FILE_NAME_PROP_KHASRA)){
				 * DMSUtility.downloadDocument(response,
				 * filePath,dtoObj.getPropKhasraDocContents());
				 * 
				 * }
				 * 
				 * forward="propertyView"; }
				 */
				// }
			}
			// added by ankit and saurav for esign and form pdf work
			if (request.getParameter("dms").equalsIgnoreCase("downloadFormAPDFDoc")) {
				if (request.getParameter("formType") != null && !request.getParameter("formType").toString().isEmpty()) {
					// commonBo.downloadFormAPDFs(response, hdnAppTxnId,
					// (String)request.getParameter("formType") );
					/*
					 * String finalPath = commonBo.getFormA_1_2Path(
					 * hdnAppTxnId, (String)request.getParameter("formType") );
					 * DownloadService ds = new DownloadService();
					 * ds.downloadFiles(finalPath, response);
					 */
				}
			}
		}
		// added by ankit and saurav for esign and form pdf work
		if (RegInitConstant.UPLOAD_FORM_A_1_FILE.equalsIgnoreCase(actionName)) {
			// String ret = commonBo.uploadFormAPDF(regForm.getA1Upload());
			String ret = "";
			ret = ret == null ? uploadFile(regForm.getHidnRegTxnId(), regForm.getA1Upload().getFileData(), null, "formviA", regForm.getA1Upload().getFileName()) : ret;
			logger.debug("UPLOAD_FORM_A_1_FILE message - " + ret);
			forward = regForm.getJspName();
		}

		if (RegInitConstant.PRINT_ACTION.equalsIgnoreCase(actionName) || RegInitConstant.PRINT_ACTION_FINAL.equalsIgnoreCase(actionName)) {
			// regForm=commonBo.printToPdf(regForm, request, response,
			// languageLocale);
			regForm.setActionName("");
			if (RegInitConstant.PRINT_ACTION.equalsIgnoreCase(actionName)) {
				commonBo.printToPdfJasper(regForm, request, response, languageLocale, "N");
				regForm.setRegInitEstampCode(null);
			} else {
				commonBo.printToPdfJasper(regForm, request, response, languageLocale, "Y");
			}
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			forward = regForm.getJspName();
		}
		if (RegInitConstant.PRINT_PAYMENT_ACTION.equalsIgnoreCase(actionName)) {
			// regForm=commonBo.printToPdf(regForm, request, response,
			// languageLocale);
			commonBo.printToPdfJasper(regForm, request, response, languageLocale, "Y");
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			forward = regForm.getJspName();
		}
		if (RegInitConstant.MODIFY_PARTY_ACTION.equalsIgnoreCase(actionName)) {
			actionName = "";
			regForm.setActionName(RegInitConstant.NO_ACTION);
			commonBo.getPartyDetsForViewConfirmModify(regForm);
			commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
			// commonDto.setCategoryList(commonBo.getCategoryList());
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
			commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
			commonDto.setOwnerRelationShipList(commonBo.getRelationshipList(regForm.getOwnerGendar(), languageLocale));
			commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
			// regForm.setFnameTrns("Roopam");
			// regForm.setActionName(RegInitConstant.NO_ACTION);
			commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));// state
			commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));// state
			// id
			// passed
			// is
			// for
			// MP
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			regForm.setPartyModifyFlag(1);
			regForm.setOwnerModifyFlag(1);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			// regForm.setClaimantFlag(Integer.parseInt(commonBo.getClaimantFlag(regForm.getPartyType())));
			String applicantFlag = "";
			String[] claimantArr = new String[2];
			claimantArr = commonBo.getClaimantFlag(regForm.getPartyType());
			String partyTypeId = commonBo.getPartyTypeFlag(regForm.getPartyTxnId());
			regForm.setPartyType(partyTypeId);
			request.setAttribute("roleId", partyTypeId);
			claimantArr = commonBo.getClaimantFlag(partyTypeId);
			regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
			regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
			if (regForm.getListIDTrns().equalsIgnoreCase("7")) {
				regForm.setPartyDisplayAadhar("1");
			} else {
				regForm.setPartyDisplayAadhar("0");
			}
			// added by Ankit for Aadhar integration
			regForm.setAadharCheck("");
			regForm.setAadharErrMsg("");
			regForm.setAadharRespCode("");
			forward = "displayRegDetls";
		}
		if (RegInitConstant.SAVE_PARTY_ACTION.equalsIgnoreCase(actionName)) {
			String path = "";
			try {
				PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
				path = prop.getValue("upload.location");
			} catch (Exception e) {
				logger.debug("exception in uploadFile : " + e);
				logger.debug(e.getMessage(), e);
			}
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setPartyModifyFlag(0);
			regForm.setOwnerModifyFlag(0);
			boolean checkAdditionalUploads = true;
			path = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_PARTY + regForm.getPartyTxnId() + "/";
			try {
				// By Mohit
				File f = new File(path);
				FileUtils.cleanDirectory(f);
				// commonBo.deleteAllRemovedUploads(regForm.getHidnRegTxnId(),regForm.getPartyTxnId());
			} catch (Exception e) {
				request.setAttribute("error", "Unable to clean directory.");
				logger.debug(e.getMessage(), e);
				// checkAdditionalUploads = true;
			} finally {
				try {
					checkAdditionalUploads = true;
					commonBo.deleteAllRemovedUploads(regForm.getHidnRegTxnId(), regForm.getPartyTxnId());
				} catch (Exception e) {
					request.setAttribute("error", "Unable to delete records.");
					logger.debug(e.getMessage(), e);
					checkAdditionalUploads = false;
				}
			}
			// below write code for saving modified party details
			if (checkAdditionalUploads) {
				ArrayList<CommonDTO> listDto = regForm.getListDto();
				if (listDto != null && listDto.size() > 0) {
					// checkAdditionalUploads =
					// commonBo.deleteAllRemovedUploads(regForm.getHidnRegTxnId(),regForm.getPartyTxnId());
					if (checkAdditionalUploads) {
						for (int i = 0; i < listDto.size(); i++) {
							CommonDTO dto = listDto.get(i);
							dto.setDocumentName(commonBo.getNewFileName(dto.getDocumentName(), Integer.toString(i)));
							String filepath = uploadFile(regForm.getHidnRegTxnId(), dto.getDocContents(), regForm.getPartyTxnId(), "01", dto.getDocumentName());
							if (filepath != null) {
								checkAdditionalUploads = true;
								dto.setDocumentPath(filepath);
							} else {
								checkAdditionalUploads = false;
								break;
							}
						}
					}
					if (checkAdditionalUploads) {
						regForm.setPartyRoleTypeId(regForm.getPartyTxnId());
						checkAdditionalUploads = commonBo.insertAdditionalUploads(regForm);
					}
				} else {
					checkAdditionalUploads = true;
				}
			}
			// mohit
			if (checkAdditionalUploads) {
				boolean allUploadsUpdated = false;
				boolean allDetailsUpdated = false;
				String filePath;
				String filePathPhotoProof;
				String filePathNotAffExec;
				String filePathExecPhoto;
				String filePathNotAffAttrn;
				String filePathAttrnPhoto;
				String filePathClaimPhoto;
				String filePathPanForm60;
				regForm.setU2FilePathTrns("");
				regForm.setU3FilePathTrns("");
				regForm.setU10FilePathTrns("");
				regForm.setU6FilePathTrns("");
				regForm.setU11FilePathTrns("");
				regForm.setU9FilePathTrns("");
				regForm.setFilePathTrns("");
				int roleId = Integer.parseInt(regForm.getPartyTypeTrns());
				// int
				// claimantFlag=Integer.parseInt(commonBo.getClaimantFlag(Integer.toString(roleId)));
				String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
				int claimantFlag = Integer.parseInt(claimantArr[0].trim());
				/*
				 * if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant
				 * .INDIVIDUAL_ID) ||
				 * regForm.getListAdoptedPartyTrns().equalsIgnoreCase
				 * (RegInitConstant.ORGANISATION_ID))
				 */
				if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					if (!regForm.getU2DocumentNameTrns().equalsIgnoreCase("")) {
						filePathPhotoProof = uploadFile(regForm.getHidnRegTxnId(), regForm.getU2DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU2PartyOrPropTrns(), regForm.getU2UploadTypeTrns());
					} else {
						filePathPhotoProof = "";
					}
					if (filePathPhotoProof != null) {
						regForm.setU2FilePathTrns(filePathPhotoProof);
						// BELOW CODE FOR EXECUTANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
							if (regForm.getU3DocumentNameTrns() != null && !regForm.getU3DocumentNameTrns().equalsIgnoreCase("")) {
								filePathNotAffExec = uploadFile(regForm.getHidnRegTxnId(), regForm.getU3DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU3PartyOrPropTrns(), regForm.getU3UploadTypeTrns());
							} else {
								filePathNotAffExec = "";
							}
							if (filePathNotAffExec != null) {
								regForm.setU3FilePathTrns(filePathNotAffExec);
								// filePathExecPhoto=uploadFile(regForm.getHidnRegTxnId(),regForm.getU4DocContentsTrns(),
								// regForm.getPartyTxnId(),regForm.getU4PartyOrPropTrns(),regForm.getU4UploadTypeTrns());
								// if(filePathExecPhoto!=null){
								// regForm.setU4FilePathTrns(filePathExecPhoto);
								if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) // for
								// individual
								{
									if (!regForm.getListIDTrns().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentNameTrns() != null && !regForm.getU10DocumentNameTrns().equalsIgnoreCase("")))) {
										filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU10PartyOrPropTrns(), regForm.getU10UploadTypeTrns());
										if (filePathPanForm60 != null) {
											regForm.setU10FilePathTrns(filePathPanForm60);
											allUploadsUpdated = true;
										} else {
											allUploadsUpdated = false;
										}
									} else {
										allUploadsUpdated = true;
									}
								} else { // for organization
									/*
									 * filePathPanForm60=uploadFile(regForm.getHidnRegTxnId
									 * (),regForm.getU10DocContentsTrns(),
									 * regForm.getPartyTxnId(),regForm.
									 * getU10PartyOrPropTrns
									 * (),regForm.getU10UploadTypeTrns());
									 * 
									 * if(filePathPanForm60!=null){
									 * regForm.setU10FilePathTrns
									 * (filePathPanForm60);
									 * allUploadsUpdated=true; }else{
									 * allUploadsUpdated=false; }
									 */
									if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentNameTrns() != null && !regForm.getU10DocumentNameTrns().equalsIgnoreCase("")))) {
										filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU10PartyOrPropTrns(), regForm.getU10UploadTypeTrns());
										if (filePathPanForm60 != null) {
											regForm.setU10FilePathTrns(filePathPanForm60);
											allUploadsUpdated = true;
										} else {
											allUploadsUpdated = false;
										}
									} else {
										allUploadsUpdated = true;
									}
								}
								// }else{
								// allUploadsUpdated=false;
								// }
							} else {
								allUploadsUpdated = false;
							}
						}
						// BELOW CODE FOR EXECUTANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_5) {
							// filePathExecPhoto=uploadFile(regForm.getHidnRegTxnId(),regForm.getU7DocContentsTrns(),
							// regForm.getPartyTxnId(),regForm.getU7PartyOrPropTrns(),regForm.getU7UploadTypeTrns());
							// if(filePathExecPhoto!=null)
							// {
							// regForm.setU7FilePathTrns(filePathExecPhoto);
							/*
							 * filePathNotAffAttrn=uploadFile(regForm.getHidnRegTxnId
							 * (),regForm.getU5DocContentsTrns(),
							 * regForm.getPartyTxnId
							 * (),regForm.getU5PartyOrPropTrns
							 * (),regForm.getU5UploadTypeTrns());
							 * 
							 * if(filePathNotAffAttrn!=null) {
							 * regForm.setU5FilePathTrns(filePathNotAffAttrn);
							 */
							filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), regForm.getU6DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU6PartyOrPropTrns(), regForm.getU6UploadTypeTrns());
							if (filePathAttrnPhoto != null) {
								regForm.setU6FilePathTrns(filePathAttrnPhoto);
								allUploadsUpdated = true;
							} else {
								allUploadsUpdated = false;
							}
							// }
							// }
						}
						// BELOW CODE FOR CLAIMANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
							// filePathClaimPhoto=uploadFile(regForm.getHidnRegTxnId(),regForm.getU8DocContentsTrns(),
							// regForm.getPartyTxnId(),regForm.getU8PartyOrPropTrns(),regForm.getU8UploadTypeTrns());
							// if(filePathClaimPhoto!=null){
							// regForm.setU8FilePathTrns(filePathClaimPhoto);
							if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) // for
							// individual
							{
								if (!regForm.getListID().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentNameTrns() != null && !regForm.getU11DocumentNameTrns().equalsIgnoreCase("")))) {
									filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU11PartyOrPropTrns(), regForm.getU11UploadTypeTrns());
									if (filePathPanForm60 != null) {
										regForm.setU11FilePathTrns(filePathPanForm60);
										allUploadsUpdated = true;
									} else {
										allUploadsUpdated = false;
									}
								} else {
									allUploadsUpdated = true;
								}
							} else {
								// for organization
								/*
								 * filePathPanForm60=uploadFile(regForm.getHidnRegTxnId
								 * (),regForm.getU11DocContentsTrns(),
								 * regForm.getPartyTxnId
								 * (),regForm.getU11PartyOrPropTrns
								 * (),regForm.getU11UploadTypeTrns());
								 * 
								 * if(filePathPanForm60!=null){
								 * regForm.setU11FilePathTrns
								 * (filePathPanForm60); allUploadsUpdated=true;
								 * }else{ allUploadsUpdated=false; }
								 */
								if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentNameTrns() != null && !regForm.getU11DocumentNameTrns().equalsIgnoreCase("")))) {
									filePathPanForm60 = uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU11PartyOrPropTrns(), regForm.getU11UploadTypeTrns());
									if (filePathPanForm60 != null) {
										regForm.setU11FilePathTrns(filePathPanForm60);
										allUploadsUpdated = true;
									} else {
										allUploadsUpdated = false;
									}
								} else {
									allUploadsUpdated = true;
								}
							}
							// }else{
							// allUploadsUpdated=false;
							// }
						}
						// BELOW CODE FOR CLAIMANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_6) {
							filePathAttrnPhoto = uploadFile(regForm.getHidnRegTxnId(), regForm.getU9DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU9PartyOrPropTrns(), regForm.getU9UploadTypeTrns());
							if (filePathAttrnPhoto != null) {
								regForm.setU9FilePathTrns(filePathAttrnPhoto);
								allUploadsUpdated = true;
							} else {
								allUploadsUpdated = false;
							}
						}
						if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
							if (regForm.getIndCategoryTrns().equalsIgnoreCase("1") && regForm.getIndScheduleAreaTrns().equalsIgnoreCase("N")) {
								filePath = uploadFile(regForm.getHidnRegTxnId(), regForm.getDocContentsTrns(), regForm.getPartyTxnId(), regForm.getPartyOrPropTrns(), regForm.getUploadTypeTrns());
								if (filePath != null) {
									regForm.setFilePathTrns(filePath);
									allUploadsUpdated = true;
								} else {
									allUploadsUpdated = false;
								}
							}
						}
					}
				} else {
					allUploadsUpdated = true;
				}
				if (allUploadsUpdated) {
					allDetailsUpdated = commonBo.updateTransPartyDetails(regForm); // update
					// in
					// db
					// anuj
					if (allDetailsUpdated) {
						// Aadhar changes
						if (regForm.getListIDTrns().equalsIgnoreCase("7")) {
							regForm.setPartyDisplayAadhar("1");
						} else {
							regForm.setPartyDisplayAadhar("0");
						}
					}
					logger.debug("transacting party details updation status---->" + allDetailsUpdated);
				}
				/*
				 * request.setAttribute("deedId", regForm.getDeedID());
				 * request.setAttribute("roleId", regForm.getPartyType());
				 * request.setAttribute("instId", regForm.getInstID());
				 * forward="reginitConfirm";
				 * 
				 * commonBo.landConfirmationScreen(regForm,languageLocale);
				 */
				commonBo.openPartyView(request, regForm, languageLocale);
				regForm.setPartyModifyFlag(0);
				regForm.setOwnerModifyFlag(0);
				commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
				commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));// state
				// id
				// passed
				// is
				// for
				// MP
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			} else {
				request.setAttribute("error", "Unable To Save the Document");
			}
			forward = "displayRegDetls";
		}
		if (RegInitConstant.SUBMIT_FORM_ACTION.equalsIgnoreCase(actionName)) {
			logger.debug("category value----------->" + regForm.getIndCategoryTrns());
			regForm.setActionName(RegInitConstant.NO_ACTION);
			commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));// state
			// id
			// passed
			// is
			// for
			// MP
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			regForm.setPartyModifyFlag(1);
			regForm.setOwnerModifyFlag(1);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());
			forward = regForm.getJspName();
		}
		if (RegInitConstant.MODIFY_PROPERTY_ACTION.equalsIgnoreCase(actionName)) {
			// commonBo.getPartyDetsForViewConfirmModify(regForm);
			// commonDto.setIdProfTrns(commonBo.getIdProf());
			// commonDto.setCategoryList(commonBo.getCategoryList());
			// commonDto.setOccupationList(commonBo.getOccupationList());
			// regForm.setFnameTrns("Roopam");
			// regForm.setActionName(RegInitConstant.NO_ACTION);
			// commonDto.setDistrictTrns(commonBo.getDistrict("1"));//state id
			// passed is for MP
			// commonDto.setCategoryList(commonBo.getCategoryList());
			DeedDraftBD bd = new DeedDraftBD();
			boolean isConsumed = bd.getDeedIsConsumed(regForm.getHidnRegTxnId(), "R");
			logger.info(" deed status : " + isConsumed);
			if (isConsumed) {
				regForm.setPropertyModifyFlag(0);
				throw new Exception("----------- DEED ALREADY CONSUMED ---------");
			} else {
				regForm.setPropertyModifyFlag(1);
			}
			// request.setAttribute("deedId", regForm.getDeedID());
			// request.setAttribute("roleId", regForm.getPartyType());
			// request.setAttribute("propMap",
			// regForm.getMapPropertyTransPartyDisp());
			// Added by Rakesh for PropDetails Edit bifurcation: Start
			// regCompForm = new RegCompletionForm();
			if (regForm.getPropertyId() != null) {
				String getPropType = commonBo.getPropertyTypeID(regForm.getPropertyId());
				// regCompForm.setPropertyTypeId(getPropType);
				regForm.setPropertyTypeID(getPropType);
				ArrayList propdet = commonBo.getPropertyTypeIdAndClrFlag(regForm.getPropertyId());
				String clrFlag = "";
				for (int i = 0; i < propdet.size(); i++) {
					ArrayList clrList = (ArrayList) propdet.get(i);
					clrFlag = (String) clrList.get(1);
					regForm.setClrFlg(clrFlag);
				}
			}
			if (regForm.getPropertyTypeID().equalsIgnoreCase("3")) {
				if (regForm.getClrFlg() != null) {
					if (regForm.getClrFlg().equalsIgnoreCase("Y")) {
						forward = "modifyPropDetlsClr";
					} else {
						forward = "modifyPropDetls";
					}
				} else {
					forward = "modifyPropDetls";
				}
			} else {
				forward = "modifyPropDetls";
			}
			// Added by Rakesh for PropDetails Edit bifurcation: Start
			request.setAttribute("regInitForm", regForm);
		}
		if (RegInitConstant.MODIFY_EXTRA_DETLS_ACTION.equalsIgnoreCase(actionName)) {
			commonBo.modifyextra(commonDto, regForm, languageLocale);

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			regForm.setCallingAction("regInit.do?TRFS=NGI");
			forward = "displayExtraDetls";
		}
		if (RegInitConstant.SAVE_EXTRA_DETLS_ACTION.equalsIgnoreCase(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setExtraDetlsModifyFlag(0);
			// below write code for saving modified extra details
			boolean allDetailsUpdated = false;
			allDetailsUpdated = commonBo.updateBankDetails(regForm);
			logger.debug("extra details updation status---->" + allDetailsUpdated);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			// regForm.setCallingAction("regInit.do");
			// forward="reginitConfirm";
			regForm.setCallingAction("regInit.do?TRFS=NGI");
			commonBo.getExtraDetls(regForm, languageLocale);
			forward = "displayExtraDetls";
		}
		if (RegInitConstant.EXTRA_DETLS_NO_ACTION.equalsIgnoreCase(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setCallingAction("regInit.do?TRFS=NGI");
			// regForm.setExtraDetlsModifyFlag(0);
			// below write code for saving modified extra details
			// boolean allDetailsUpdated=false;
			// allDetailsUpdated = commonBo.updateBankDetails(regForm);
			// logger.debug("extra details updation status---->"+allDetailsUpdated);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			// regForm.setCallingAction("regInit.do");
			forward = "displayExtraDetls";
		}
		if (RegInitConstant.POSTAL_CHECK_ACTION.equalsIgnoreCase(actionName)) {
			forward = "success1";
		}
		if (RegInitConstant.COPY_APPLICANT_ADDRESS_ACTION.equalsIgnoreCase(actionName)) {
			commonBo.copyPostalAddress(regForm);
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
			forward = "success1";
		}
		// if(request.getParameter("label")==null){
		if (regForm.getDeedID() != 0) {
			request.setAttribute("deedId", regForm.getDeedID());
		} else {
			request.setAttribute("deedId", 0);
		}
		if (regForm.getInstID() != 0) {
			request.setAttribute("instId", regForm.getInstID());
		} else {
			request.setAttribute("instId", 0);
		}
		if (regForm.getPartyType() != null && !regForm.getPartyType().equalsIgnoreCase("")) {
			request.setAttribute("roleId", regForm.getPartyType());
		} else {
			request.setAttribute("roleId", 0);
		}
		if (regForm.getPartyTypeTrns() != null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")) {
			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
		} else {
			request.setAttribute("roleIdTrns", 0);
		}
		// }
		regForm.setCommonDto(commonDto);
		request.setAttribute("reginit", regForm);
		logger.debug("deed id request attribute-->" + request.getAttribute("deedId"));
		// logger.debug("total poa count-------->"+regForm.getTotalPoaCount());
		logger.debug("-------------------->" + session.getAttribute("parentModName"));
		logger.debug("-------------------->" + session.getAttribute("parentFunName"));
		logger.debug("-------------------->" + session.getAttribute("parentFunId"));
		logger.debug("-------------------->" + session.getAttribute("parentAmount"));
		logger.debug("----------modName---------->" + session.getAttribute("modName"));
		logger.debug("----------fnName---------->" + session.getAttribute("fnName"));
		logger.debug("the forward path from the common action in reg init is ==> " + forward);
		return mapping.findForward(forward);
	}

	public static void cancelAction(HttpSession session, RegCommonForm regForm) {
		// RegCommonForm regForm=(RegCommonForm)session.getAttribute("regForm");
		if (regForm != null) {
			// RegCommonDTO commonDto = regForm.getCommonDto();
			// commonDto.setInstrument(new ArrayList());
			// commonDto.setExemption(new ArrayList());
			regForm.setFname("");
			regForm.setMname("");
			regForm.setLname("");
			regForm.setGendar("M");
			regForm.setAge("");
			regForm.setFatherName("");
			regForm.setMotherName("");
			regForm.setSpouseName("");
			// Set Blank Guardian - Rahul
			regForm.setGuardian("");
			regForm.setNationality("");
			regForm.setIndaddress("");
			regForm.setIndcountry("");
			regForm.setIndstatename("");
			regForm.setInddistrict("");
			regForm.setPostalCode("");
			regForm.setIndphno("");
			regForm.setIndmobno("");
			regForm.setEmailID("");
			regForm.setListID("");
			regForm.setIdno("");
			regForm.setDeedType("");
			regForm.setPurpose("");
			regForm.setInstrument("");
			regForm.setIndCaste("");
			regForm.setIndReligion("");
			regForm.setIndDisability("N");
			regForm.setIndMinority("");
			// regForm.setShareOfProp("");
			regForm.setOwnershipShare("");
			regForm.setRelationWithOwner("");
			regForm.setOgrName("");
			regForm.setAuthPerName("");
			regForm.setOrgaddress("");
			regForm.setCountry("");
			regForm.setStatename("");
			regForm.setDistrict("");
			regForm.setPhno("");
			regForm.setMobno("");
			regForm.setDeedType("");
			regForm.setPurpose("");
			// commonDto.setInstrument(new ArrayList());
			// commonDto.setExemption(new ArrayList());
			regForm.setFnameTrns("");
			regForm.setMnameTrns("");
			regForm.setLnameTrns("");
			regForm.setGendarTrns("M");
			regForm.setAgeTrns("");
			regForm.setFatherNameTrns("");
			regForm.setMotherNameTrns("");
			// Set Blank Guardian - Rahul
			regForm.setGuardianTrans("");
			regForm.setSpouseNameTrns("");
			regForm.setNationalityTrns("");
			regForm.setIndaddressTrns("");
			regForm.setIndcountryTrns("");
			regForm.setIndcountryNameTrns("");
			regForm.setIndstatenameTrns("");
			regForm.setIndstatenameNameTrns("");
			regForm.setInddistrictTrns("");
			regForm.setInddistrictNameTrns("");
			regForm.setIndMinorityTrns("");
			regForm.setPostalCodeTrns("");
			regForm.setIndphnoTrns("");
			regForm.setIndmobnoTrns("");
			regForm.setEmailIDTrns("");
			regForm.setListIDTrns("");
			regForm.setListIDNameTrns("");
			regForm.setIdnoTrns("");
			regForm.setPurposeTrns("");
			regForm.setDeleteTrnsPrtyID("");
			regForm.setIndCasteTrns("");
			regForm.setIndReligionTrns("");
			regForm.setIndDisabilityTrns("N");
			regForm.setShareOfPropTrns("");
			regForm.setOwnershipShareTrns("");
			regForm.setRelationWithOwnerTrns("");
			regForm.setIndDisabilityDesc("");
			regForm.setIndDisabilityDescTrns("");
			regForm.setOgrNameTrns("");
			regForm.setAuthPerNameTrns("");
			regForm.setOrgaddressTrns("");
			regForm.setCountryTrns("");
			regForm.setCountry("");
			regForm.setCountryNameTrns("");
			regForm.setStatenameTrns("");
			regForm.setStatenameNameTrns("");
			regForm.setDistrictTrns("");
			regForm.setDistrictNameTrns("");
			regForm.setPhnoTrns("");
			regForm.setMobnoTrns("");
			regForm.setDeleteTrnsPrtyID("");
			regForm.setPartyType(null);
			regForm.setPartyTypeTrns(null);
			regForm.setListAdoptedParty(null);
			regForm.setListAdoptedPartyTrns(null);
			regForm.setOwnerList(new ArrayList());
			regForm.setPoaList(new ArrayList());
			regForm.setSelectedPoa(null);
			regForm.setSelectedPoaName(null);
			regForm.setParty1OwnerCount(0);
			regForm.setParty1PoaHolderCount(0);
			regForm.setParty2OwnerCount(0);
			regForm.setParty2PoaHolderCount(0);
			regForm.setDoneeCount(0);
			regForm.setDonorCount(0);
			regForm.setBuyerCount(0);
			regForm.setSellerPoaCount(0);
			regForm.setSellerSelfCount(0);
			regForm.setOwnerCount(0);
			regForm.setPoaAccepterCount(0);
			regForm.setPoaHolderCount(0);
			regForm.setOwnerId("");
			regForm.setHdnDeleteTrnsPrtyId("");
			regForm.setHdnOwnerId("");
			regForm.setHidnRegTxnId("");
			regForm.setHidnUserId("");
			// regForm.setPropertyDTO(new PropertyValuationDTO());
			regForm.setMapTransactingParties(new HashMap());
			regForm.setMapTransactingPartiesDisp(new HashMap());
			regForm.setMapTransPartyDbInsertion(new HashMap());
			regForm.setRegInitEstampCode(null);
			regForm.setRegInitPaymntTxnId(null);
			regForm.setRegInitPermTxnId("");
			regForm.setCurrDateTime("");
			regForm.setIsMultiplePropsFlag(0);
			regForm.setIndPovertyLine("");
			regForm.setIndPovertyLineTrns("");
			regForm.setActionName(" ");
			regForm.setFormName(" ");
			regForm.setPage("success");
			regForm.setListAdoptedPartyNameTrns("");
			regForm.setListAdoptedPartyName("");
			regForm.setAddMoreCounter(0);
			//
			regForm.setRegInitPermTxnId("");
			// regForm.setRegInitPaymntTxnId("");
			// regForm.setRegInitEstampCode("");
			regForm.setMapRegTxnIdDisp(new HashMap());
			//
			regForm.setOwnerId("");
			regForm.setHdnOwnerId("");
			regForm.setHdnDeleteTrnsPrtyId("");
			regForm.setAbc("");
			//
			// private String selectedPoa;
			// private String selectedPoaName;
			regForm.setCurrDateTime("");
			regForm.setDeedID(0);
			// Start:==== Added by ankita
			regForm.setDeedtype1("");
			regForm.setInstType("");
			/*
			 * private double stampduty1; private double nagarPalikaDuty;
			 * private double panchayatDuty; private double upkarDuty; private
			 * double totalduty ; private double registrationFee;
			 */
			regForm.setStampduty1("");
			regForm.setNagarPalikaDuty("");
			regForm.setPanchayatDuty("");
			regForm.setUpkarDuty("");
			regForm.setTotalduty("");
			regForm.setRegistrationFee("");
			regForm.setSelectedExemptionList(new ArrayList());
			regForm.setTotalMarketValue("");
			regForm.setExemType("");
			regForm.setFromModule("");
			regForm.setCheckModule("");
			regForm.setCheckRegNo(""); // TO BE SET AS BLANK
			// End :====== Added by ankita
			// following added roopam after first demo.
			regForm.setApplicantUserIdCheck("");
			regForm.setHdnapplicantUserIdCheck("");
			regForm.setInstID(0);
			regForm.setExmpID("");
			regForm.setPendingRegApplicationList(new ArrayList());
			regForm.setPropertyId("");
			regForm.setValuationId("");
			regForm.setIsDashboardFlag(0);
			regForm.setIsMultiplePropsFlag(0);
			regForm.setIsTransactingPartyAddedFlag(0);
			regForm.setMapPropertyTransParty(new HashMap());
			regForm.setMapPropertyTransPartyDisp(new HashMap());
			// private float totalMarketValue=0;
			/*
			 * private String totalMarketValue; private int applicantRoleId;
			 * private int totalShareOfProp; private int applicantRoleId2;
			 * private int mapKeyCount=0; private HashMap
			 * mapTransPartyDbInsertion = new HashMap();
			 * 
			 * private int totalShareOfPropBuyer; private int
			 * totalShareOfPropSellerSelf; private int
			 * totalShareOfPropSellerPoa;
			 */
			// private float marketValue;
			regForm.setApplicantRoleId(0);
			regForm.setApplicantRoleId2(0);
			// regForm.setTotalShareOfProp(0);
			regForm.setTotalShareOfProp(0);
			regForm.setTotalShareOfPropBuyer(0);
			regForm.setTotalShareOfPropSellerSelf(0);
			regForm.setTotalShareOfPropDonor(0);
			regForm.setTotalShareOfPropDonee(0);
			regForm.setTotalShareOfPropOwnerSelf(0);
			regForm.setTotalShareOfPropOwnerSelfParty1(0);
			regForm.setTotalShareOfPropOwnerSelfParty2(0);
			regForm.setTotalShareOfPropLesser(0);
			regForm.setTotalShareOfPropLessee(0);
			// following fields for owner of poa
			regForm.setOwnerName("");
			regForm.setOwnerOgrName("");
			regForm.setOwnerGendar("M");
			regForm.setOwnerNationality("");
			regForm.setOwnerAddress("");
			regForm.setOwnerPhno("");
			regForm.setOwnerEmailID("");
			regForm.setOwnerListID("");
			regForm.setOwnerIdno("");
			regForm.setOwnerAge("");
			regForm.setOwnerProofName("");
			regForm.setOwnerNameTrns("");
			regForm.setOwnerOgrNameTrns("");
			regForm.setOwnerGendarTrns("M");
			regForm.setOwnerNationalityTrns("");
			regForm.setOwnerAddressTrns("");
			regForm.setOwnerPhnoTrns("");
			regForm.setOwnerEmailIDTrns("");
			regForm.setOwnerListIDTrns("");
			regForm.setOwnerIdnoTrns("");
			regForm.setOwnerAgeTrns("");
			regForm.setOwnerProofNameTrns("");
			/*
			 * private String ownerNameTrns; private String ownerOgrNameTrns;
			 * private String ownerGendarTrns="M"; private String
			 * ownerNationalityTrns; private String ownerAddressTrns; private
			 * String ownerPhnoTrns; private String ownerEmailIDTrns; private
			 * String ownerListIDTrns; private String ownerIdnoTrns; private
			 * String ownerAgeTrns; private String ownerProofNameTrns;
			 */
			// private String poaOwnerId;
			// private String hdnExAmount;
			regForm.setPaymentCompleteFlag(0);
			regForm.setIsDutyCalculated(0);
			regForm.setMarketValueShares("");
			regForm.setDutyPaid("");
			// private String dutyPaid;
			regForm.setLabelAmountFlag("");
			// private double regPaid=0.0;
			regForm.setMapPropertyTransParty(new HashMap());
			regForm.setAdjudicationNumber("");
			regForm.setErrorMsg("");
			regForm.setExchangePropertyList(new ArrayList());
			regForm.setAgriPropertyList(new ArrayList());
			regForm.setIndMinority("N");
			regForm.setIndMinorityTrns("N");
			regForm.setIndPovertyLine("N");
			regForm.setIndPovertyLineTrns("N");
			regForm.setSelectedCategoryName("");
			regForm.setSelectedCategoryNameTrns("");
			regForm.setIndDisabilityDesc("");
			regForm.setIndDisabilityDescTrns("");
			regForm.setUserDayTrns("");
			regForm.setUserMonthTrns("");
			regForm.setUserYearTrns("");
			regForm.setUserDOBTrns("");
			regForm.setUserDay("");
			regForm.setUserMonth("");
			regForm.setUserYear("");
			regForm.setUserDOB("");
			regForm.setOwnerDayTrns("");
			regForm.setOwnerMonthTrns("");
			regForm.setOwnerYearTrns("");
			regForm.setOwnerDOBTrns("");
			regForm.setOwnerDay("");
			regForm.setOwnerMonth("");
			regForm.setOwnerYear("");
			regForm.setOwnerDOB("");
			// regForm.setDeclareShare("true");
			regForm.setHdnDeclareShareCheck("Y");
			// Added for addhar integration by Ankit - start
			regForm.setAadharCheck("");
			regForm.setAadharName("");
			regForm.setAadharRespCode("");
			regForm.setAadharNameTrns("");
			regForm.setAadharErrMsg("");
			regForm.setAadharCheckOwner("");
			regForm.setAadharErrMsgOwner("");
			regForm.setAadharNameOwner("");
			regForm.setAadharNameTrnsOwner("");
			regForm.setAcknowledgementId("");
			regForm.setTransactionId("");
			regForm.setAadharDto(null);
			regForm.setAadharRespDto(null);
			// added by Ankit for Aadhar integration - end
		}
		// session.removeAttribute("OrganisationList");
		// session.removeAttribute("TpartiesIndividualList");
		session.removeAttribute("commonDto");
		session.removeAttribute("roleId");
		session.removeAttribute("functionId");
		session.removeAttribute("partyType");
		session.removeAttribute("regForm");
		session.removeAttribute("status");
		session.removeAttribute("view");
		session.removeAttribute("eform");
		session.removeAttribute("labelAmountFlag");
	}

	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		return "";
	}

	// method for creating folder on server
	public String uploadFile(String regTxnId, byte[] content, String txnPartyId, String partyOrProp, String uploadType) {
		String filePath;
		String path = "";
		try {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			path = pr.getValue("upload.location");
			if (partyOrProp != null) {
				if (partyOrProp.equalsIgnoreCase("01")) {
					filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_PARTY + txnPartyId + "/";
				} else if (partyOrProp.equalsIgnoreCase("02")) {
					filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_PROP + txnPartyId + "/";
				} else if (partyOrProp.equalsIgnoreCase("03")) {
					filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + txnPartyId + "/";
				} else if (partyOrProp.equalsIgnoreCase("04")) {
					filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_CONSENTER + txnPartyId + "/";
				}
				// Added by Rakesh
				else if (partyOrProp.equalsIgnoreCase("MapCLR")) {
					filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_PROP + txnPartyId + "/";
				} else if (partyOrProp.equalsIgnoreCase("KhasraCLR")) {
					filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_PROP + txnPartyId + "/";
				}
				// Added by ankit for plant and machinery
				else if (partyOrProp.equalsIgnoreCase("05")) {
					filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + RegInitConstant.FILE_UPLOAD_PATH_AUDIT_REPORT + txnPartyId + "/";
				} else if (partyOrProp.equalsIgnoreCase("formviA")) {
					filePath = path + RegInitConstant.FORMVIA_PATH + "/" + regTxnId + "/";
				} else if (partyOrProp.equalsIgnoreCase("formviB")) {
					filePath = path + RegInitConstant.FORMVIB_PATH + "/" + regTxnId + "/";
				} else {
					return null;
				}
			} else {
				filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regTxnId + "/";
			}
			logger.debug("Upload path created : " + filePath);
			String fileName = uploadType;
			File folder = new File(filePath);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			File newFile = new File(filePath, fileName);
			if (!newFile.exists()) {
				logger.info("NEW FILE NAME:-" + newFile);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
				logger.debug("file written:" + (filePath + fileName));
			} else {
				// String str = fileName.substring(0, fileName.indexOf("."));
				// fileName = str + "_01" + ".jpg";
				// newFile = new File(filePath, fileName);
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(content);
				fos.close();
				logger.debug("file written:" + (filePath + fileName));
			}
			return filePath + fileName;
		} catch (Exception ex) {
			logger.debug(ex.getMessage(), ex);
			return null;
		}
	}

	public static void resetForm(RegCommonForm regForm) {

		// RegCommonDTO commonDto =new RegCommonDTO();
		// commonDto.setInstrument(new ArrayList());
		// commonDto.setExemption(new ArrayList());
		regForm.setRegDTO(new RegCompleteMakerDTO());
		regForm.setPropertyDiv(0);
		regForm.setSelectedPropId("");
		regForm.setSelectedPropIdMap("");
		// private HashMap appOwnerList=new HashMap();
		// private HashMap trnsOwnerList=new HashMap();
		regForm.setAadharCheck("");// added by Ankit for Aadhar integration
		regForm.setAadharName("");// added by Ankit for Aadhar integration
		regForm.setAadharRespCode("");// added by Ankit for Aadhar integration
		regForm.setAadharCheckOwner("");// added by Ankit for Aadhar integration
		regForm.setAadharNameOwner("");// added by Ankit for Aadhar integration
		regForm.setAadharRespCodeOwner("");// added by Ankit for Aadhar
		// integration
		regForm.setDeleteOwnerID("");
		regForm.setHdnDeleteOwnerID("");
		regForm.setOwnerGendarVal("");
		regForm.setOwnerModifyFlag(0);
		regForm.setNewParty("");
		regForm.setAppOwnerList(new HashMap());
		regForm.setTrnsOwnerList(new HashMap());
		regForm.setPoaShareDebenture("");
		regForm.setTotalConsenterConsid("");
		regForm.setAddressIndOutMp("");
		regForm.setAddressOrgOutMp("");
		regForm.setAddressAuthPerOutMp("");
		regForm.setAddressOwnerOutMp("");
		regForm.setAddressGovtOffclOutMp("");
		regForm.setConsenterUploadList(new ArrayList());
		regForm.setAddressIndOutMpTrns("");
		regForm.setAddressOrgOutMpTrns("");
		regForm.setAddressAuthPerOutMpTrns("");
		regForm.setAddressOwnerOutMpTrns("");
		regForm.setAddressGovtOffclOutMpTrns("");
		regForm.setUpOrNot("");
		regForm.setConsenterFlag("N");
		regForm.setConsenterWithConsidFlag("N");
		regForm.setConsenterCount(0);
		regForm.setConsenterAddedCount(0);
		regForm.setDtlsMapConsntr(new HashMap());
		regForm.setConsenterId("");
		regForm.setDeleteConsenter("");
		regForm.setHdnDeleteConsenter("");
		regForm.setConsenterWiseDutyList(new ArrayList());
		regForm.setFamilyFlag("N");
		regForm.setIsDashboardFlag(3);
		regForm.setMergeSuccess("N");
		regForm.setTermsCondPartyApp("");
		regForm.setTermsCondShare("");
		regForm.setTermsCondPartyTrns("");
		regForm.setTermsCondProp("");
		regForm.setFinalDisclaimerCheck("off");
		regForm.setGovtOfcCheck("");
		regForm.setNameOfOfficial("");
		regForm.setDesgOfOfficial("");
		regForm.setAddressOfOfficial("");
		regForm.setFinalPage("");
		regForm.setTransCheck("");
		// regForm.setOfficialCheck()
		regForm.setNameOfOfficialTrns("");
		regForm.setDesgOfOfficialTrns("");
		regForm.setAddressOfOfficialTrns("");
		regForm.setListDto(new ArrayList());
		regForm.setListDtoAdju(new ArrayList());
		regForm.setListDtoP(new ArrayList());
		regForm.setDeedDraftId("");
		regForm.setConsUploadFlag("0");
		regForm.setAdjudicationNumber("");
		regForm.setApplicantUserIdCheck(null);
		// set Guardian Name - blank Rahul
		regForm.setGuardian("");
		regForm.setFname("");
		regForm.setMname("");
		regForm.setLname("");
		regForm.setGendar("M");
		regForm.setOwnerGendar("M");
		regForm.setAge("");
		regForm.setFatherName("");
		regForm.setMotherName("");
		regForm.setSpouseName("");
		regForm.setNationality("");
		regForm.setIndaddress("");
		regForm.setIndcountry("");
		regForm.setIndstatename("");
		regForm.setInddistrict("");
		regForm.setIndMinority("N");
		regForm.setPostalCode("");
		regForm.setIndphno("");
		regForm.setIndmobno("");
		regForm.setEmailID("");
		regForm.setListID("");
		regForm.setIdno("");
		regForm.setDeedType("");
		regForm.setInstrument("");
		regForm.setIndCaste("");
		regForm.setIndReligion("");
		regForm.setIndDisability("N");
		regForm.setIndCategory("");
		regForm.setIndCategoryTrns("");
		regForm.setOccupationId("");
		regForm.setOccupationIdTrns("");
		regForm.setOgrName("");
		regForm.setAuthPerName("");
		regForm.setOrgaddress("");
		regForm.setCountry("");
		regForm.setStatename("");
		regForm.setDistrict("");
		regForm.setPhno("");
		regForm.setMobno("");
		regForm.setDeedType("");
		regForm.setPurpose("");
		// regForm.setShareOfProp("");
		regForm.setRelationshipTrns(0);
		regForm.setRelationship(0);
		regForm.setFnameTrns("");
		regForm.setMnameTrns("");
		regForm.setLnameTrns("");
		// Guardian Trans blank - Rahul
		regForm.setGuardianTrans(" ");
		regForm.setGendarTrns("M");
		regForm.setAgeTrns("");
		regForm.setFatherNameTrns("");
		regForm.setMotherNameTrns("");
		regForm.setSpouseNameTrns("");
		regForm.setNationalityTrns("");
		regForm.setIndaddressTrns("");
		regForm.setIndcountryTrns("");
		regForm.setIndcountryNameTrns("");
		regForm.setIndstatenameTrns("");
		regForm.setIndstatenameNameTrns("");
		regForm.setInddistrictTrns("");
		regForm.setInddistrictNameTrns("");
		regForm.setIndMinorityTrns("N");
		regForm.setPostalCodeTrns("");
		regForm.setIndphnoTrns("");
		regForm.setIndmobnoTrns("");
		regForm.setEmailIDTrns("");
		regForm.setListIDTrns("");
		regForm.setListIDNameTrns("");
		regForm.setIdnoTrns("");
		regForm.setPurposeTrns("");
		regForm.setDeleteTrnsPrtyID("");
		regForm.setIndCasteTrns("");
		regForm.setIndReligionTrns("");
		regForm.setIndDisabilityTrns("N");
		regForm.setShareOfPropTrns("");
		regForm.setOwnershipShareTrns("");
		regForm.setRelationWithOwnerTrns("");
		regForm.setOgrNameTrns("");
		regForm.setAuthPerNameTrns("");
		regForm.setOrgaddressTrns("");
		regForm.setCountryTrns("");
		regForm.setCountry("");
		regForm.setCountryNameTrns("");
		regForm.setStatenameTrns("");
		regForm.setStatenameNameTrns("");
		regForm.setDistrictTrns("");
		regForm.setDistrictNameTrns("");
		regForm.setPhnoTrns("");
		regForm.setMobnoTrns("");
		regForm.setDeleteTrnsPrtyID("");
		regForm.setPartyType(null);
		regForm.setPartyTypeTrns(null);
		regForm.setListAdoptedParty(null);
		regForm.setListAdoptedPartyTrns(null);
		regForm.setOwnerList(new ArrayList());
		regForm.setPoaList(new ArrayList());
		regForm.setSelectedPoa(null);
		regForm.setSelectedPoaName(null);
		regForm.setOwnerId("");
		regForm.setHdnDeleteTrnsPrtyId("");
		regForm.setHdnOwnerId("");
		regForm.setHidnUserId("");
		// regForm.setPropertyDTO(new PropertyValuationDTO());
		regForm.setRegcompletDto(new RegCompletDTO());
		regForm.setMapTransactingParties(new HashMap());
		regForm.setMapTransactingPartiesDisp(new HashMap());
		regForm.setMapTransPartyDbInsertion(new HashMap());
		regForm.setRegInitEstampCode(null);
		regForm.setRegInitPaymntTxnId(null);
		regForm.setRegInitPermTxnId("");
		regForm.setCurrDateTime("");
		regForm.setPropertyId("");
		regForm.setTotalPoaCount(0);
		regForm.setTotalOwnerCount(0);
		regForm.setIndPovertyLine("N");
		regForm.setIndPovertyLineTrns("N");
		regForm.setActionName(" ");
		regForm.setFormName(" ");
		regForm.setPage("success");
		regForm.setListAdoptedPartyNameTrns("");
		regForm.setListAdoptedPartyName("");
		regForm.setAddMoreCounter(0);
		regForm.setTotalShareOfProp(0);
		regForm.setTotalShareOfPropBuyer(0);
		regForm.setTotalShareOfPropSellerSelf(0);
		regForm.setTotalShareOfPropDonor(0);
		regForm.setTotalShareOfPropDonee(0);
		regForm.setTotalShareOfPropOwnerSelf(0);
		regForm.setTotalShareOfPropOwnerSelfParty1(0);
		regForm.setTotalShareOfPropOwnerSelfParty2(0);
		regForm.setTotalShareOfPropLesser(0);
		regForm.setTotalShareOfPropLessee(0);
		regForm.setOwnerNameTrns("");
		regForm.setOwnerOgrNameTrns("");
		regForm.setOwnerGendarTrns("M");
		regForm.setOwnerNationalityTrns("");
		regForm.setOwnerAddressTrns("");
		regForm.setOwnerPhnoTrns("");
		regForm.setOwnerEmailIDTrns("");
		regForm.setOwnerIdnoTrns("");
		regForm.setOwnerAgeTrns("");
		regForm.setOwnerListIDTrns("");
		regForm.setOwnerProofNameTrns("");
		regForm.setIsTransactingPartyAddedFlag(0);
		regForm.setIsDashboardFlag(0);
		// commonDto.setIdProf(commonBo.getIdProf());
		regForm.setOwnerName("");
		regForm.setOwnerOgrName("");
		regForm.setOwnerGendar("M");
		regForm.setOwnerNationality("");
		regForm.setOwnerAddress("");
		regForm.setOwnerPhno("");
		regForm.setOwnerEmailID("");
		regForm.setOwnerListID("");
		regForm.setOwnerIdno("");
		regForm.setOwnerAge("");
		regForm.setOwnerProofName("");
		regForm.setPaymentCompleteFlag(0);
		regForm.setAdjuDutyAddedFlag("N");
		regForm.setIsDutyCalculated(0);
		regForm.setMarketValueShares("");
		regForm.setDutyPaid("");
		regForm.setRegPaid("");
		// private String dutyPaid;
		regForm.setLabelAmountFlag("");
		regForm.setAdjudicationNumber("");
		// private double regPaid=0.0;
		regForm.setErrorMsg("");
		regForm.setMapPropertyTransParty(new HashMap());
		regForm.setExchangePropertyList(new ArrayList());
		regForm.setAgriPropertyList(new ArrayList());
		regForm.setExchangeAgriPropertyListDisp(new ArrayList());
		regForm.setSelectedCategoryName("");
		regForm.setSelectedCategoryNameTrns("");
		regForm.setIndDisabilityDesc("");
		regForm.setIndDisabilityDescArb("");
		regForm.setIndDisabilityDescTrns("");
		regForm.setUserDayTrns("");
		regForm.setUserMonthTrns("");
		regForm.setUserYearTrns("");
		regForm.setUserDOBTrns("");
		regForm.setAgeTrns("");
		regForm.setUserDay("");
		regForm.setUserMonth("");
		regForm.setUserYear("");
		regForm.setUserDOB("");
		regForm.setAge("");
		regForm.setOwnerDayTrns("");
		regForm.setOwnerMonthTrns("");
		regForm.setOwnerYearTrns("");
		regForm.setOwnerDOBTrns("");
		regForm.setOwnerAgeTrns("");
		regForm.setOwnerDay("");
		regForm.setOwnerMonth("");
		regForm.setOwnerYear("");
		regForm.setOwnerDOB("");
		regForm.setOwnerAge("");
		// regForm.setDeclareShare();
		regForm.setHdnDeclareShareCheck("Y");
		regForm.setPostalAddress1("Y");
		regForm.setPostalAddress2("N");
		regForm.setHdnPostalAddress1("Y");
		regForm.setIndScheduleAreaTrns("Y");
		regForm.setPermissionNoTrns("");
		regForm.setCertificate(null);
		regForm.setDocumentName("");
		regForm.setDocumentSize("");
		regForm.setFilePath("");
		regForm.setDocContents(new byte[0]);
		regForm.setPartyOrProp("");
		regForm.setUploadType("");
		regForm.setU2(null);
		regForm.setU2DocumentName("");
		regForm.setU2DocumentSize("");
		regForm.setU2FilePath("");
		regForm.setU2DocContents(new byte[0]);
		regForm.setU2PartyOrProp("");
		regForm.setU2UploadType("");
		regForm.setU3(null);
		regForm.setU3DocumentName("");
		regForm.setU3DocumentSize("");
		regForm.setU3FilePath("");
		regForm.setU3DocContents(new byte[0]);
		regForm.setU3PartyOrProp("");
		regForm.setU3UploadType("");
		regForm.setU4(null);
		regForm.setU4DocumentName("");
		regForm.setU4DocumentSize("");
		regForm.setU4FilePath("");
		regForm.setU4DocContents(new byte[0]);
		regForm.setU4PartyOrProp("");
		regForm.setU4UploadType("");
		regForm.setU5(null);
		regForm.setU5DocumentName("");
		regForm.setU5DocumentSize("");
		regForm.setU5FilePath("");
		regForm.setU5DocContents(new byte[0]);
		regForm.setU5PartyOrProp("");
		regForm.setU5UploadType("");
		regForm.setU6(null);
		regForm.setU6DocumentName("");
		regForm.setU6DocumentSize("");
		regForm.setU6FilePath("");
		regForm.setU6DocContents(new byte[0]);
		regForm.setU6PartyOrProp("");
		regForm.setU6UploadType("");
		regForm.setU7(null);
		regForm.setU7DocumentName("");
		regForm.setU7DocumentSize("");
		regForm.setU7FilePath("");
		regForm.setU7DocContents(new byte[0]);
		regForm.setU7PartyOrProp("");
		regForm.setU7UploadType("");
		regForm.setU8(null);
		regForm.setU8DocumentName("");
		regForm.setU8DocumentSize("");
		regForm.setU8FilePath("");
		regForm.setU8DocContents(new byte[0]);
		regForm.setU8PartyOrProp("");
		regForm.setU8UploadType("");
		regForm.setU9(null);
		regForm.setU9DocumentName("");
		regForm.setU9DocumentSize("");
		regForm.setU9FilePath("");
		regForm.setU9DocContents(new byte[0]);
		regForm.setU9PartyOrProp("");
		regForm.setU9UploadType("");
		regForm.setU10(null);
		regForm.setU10DocumentName("");
		regForm.setU10DocumentSize("");
		regForm.setU10FilePath("");
		regForm.setU10DocContents(new byte[0]);
		regForm.setU10PartyOrProp("");
		regForm.setU10UploadType("");
		regForm.setU11(null);
		regForm.setU11DocumentName("");
		regForm.setU11DocumentSize("");
		regForm.setU11FilePath("");
		regForm.setU11DocContents(new byte[0]);
		regForm.setU11PartyOrProp("");
		regForm.setU11UploadType("");
		regForm.setIndScheduleArea("Y");
		regForm.setPermissionNo("");
		regForm.setCertificateTrns(null);
		regForm.setDocumentNameTrns("");
		regForm.setDocumentSizeTrns("");
		regForm.setFilePathTrns("");
		regForm.setDocContentsTrns(new byte[0]);
		regForm.setPartyOrPropTrns("");
		regForm.setUploadTypeTrns("");
		regForm.setU2Trns(null);
		regForm.setU2DocumentNameTrns("");
		regForm.setU2DocumentSizeTrns("");
		regForm.setU2FilePathTrns("");
		regForm.setU2DocContentsTrns(new byte[0]);
		regForm.setU2PartyOrPropTrns("");
		regForm.setU2UploadTypeTrns("");
		regForm.setU3Trns(null);
		regForm.setU3DocumentNameTrns("");
		regForm.setU3DocumentSizeTrns("");
		regForm.setU3FilePathTrns("");
		regForm.setU3DocContentsTrns(new byte[0]);
		regForm.setU3PartyOrPropTrns("");
		regForm.setU3UploadTypeTrns("");
		regForm.setU4Trns(null);
		regForm.setU4DocumentNameTrns("");
		regForm.setU4DocumentSizeTrns("");
		regForm.setU4FilePathTrns("");
		regForm.setU4DocContentsTrns(new byte[0]);
		regForm.setU4PartyOrPropTrns("");
		regForm.setU4UploadTypeTrns("");
		regForm.setU5Trns(null);
		regForm.setU5DocumentNameTrns("");
		regForm.setU5DocumentSizeTrns("");
		regForm.setU5FilePathTrns("");
		regForm.setU5DocContentsTrns(new byte[0]);
		regForm.setU5PartyOrPropTrns("");
		regForm.setU5UploadTypeTrns("");
		regForm.setU6Trns(null);
		regForm.setU6DocumentNameTrns("");
		regForm.setU6DocumentSizeTrns("");
		regForm.setU6FilePathTrns("");
		regForm.setU6DocContentsTrns(new byte[0]);
		regForm.setU6PartyOrPropTrns("");
		regForm.setU6UploadTypeTrns("");
		regForm.setU7Trns(null);
		regForm.setU7DocumentNameTrns("");
		regForm.setU7DocumentSizeTrns("");
		regForm.setU7FilePathTrns("");
		regForm.setU7DocContentsTrns(new byte[0]);
		regForm.setU7PartyOrPropTrns("");
		regForm.setU7UploadTypeTrns("");
		regForm.setU8Trns(null);
		regForm.setU8DocumentNameTrns("");
		regForm.setU8DocumentSizeTrns("");
		regForm.setU8FilePathTrns("");
		regForm.setU8DocContentsTrns(new byte[0]);
		regForm.setU8PartyOrPropTrns("");
		regForm.setU8UploadTypeTrns("");
		regForm.setU9Trns(null);
		regForm.setU9DocumentNameTrns("");
		regForm.setU9DocumentSizeTrns("");
		regForm.setU9FilePathTrns("");
		regForm.setU9DocContentsTrns(new byte[0]);
		regForm.setU9PartyOrPropTrns("");
		regForm.setU9UploadTypeTrns("");
		regForm.setU10Trns(null);
		regForm.setU10DocumentNameTrns("");
		regForm.setU10DocumentSizeTrns("");
		regForm.setU10FilePathTrns("");
		regForm.setU10DocContentsTrns(new byte[0]);
		regForm.setU10PartyOrPropTrns("");
		regForm.setU10UploadTypeTrns("");
		regForm.setU11Trns(null);
		regForm.setU11DocumentNameTrns("");
		regForm.setU11DocumentSizeTrns("");
		regForm.setU11FilePathTrns("");
		regForm.setU11DocContentsTrns(new byte[0]);
		regForm.setU11PartyOrPropTrns("");
		regForm.setU11UploadTypeTrns("");
		regForm.setSrOfficeNameTrns("");
		regForm.setPoaRegNoTrns("");
		regForm.setDatePoaRegTrns("");
		regForm.setSrOfficeName("");
		regForm.setPoaRegNo("");
		regForm.setDatePoaReg("");
		regForm.setPostalCountry("1");
		regForm.setPostalState("1");
		regForm.setPostalDistrict("");
		regForm.setPostalAddress("");
		regForm.setPartyModifyFlag(0);
		regForm.setOwnerModifyFlag(0);
		regForm.setExtraDetlsModifyFlag(0);
		regForm.setDeedTypeFlag(0);
		regForm.setAdoptionDeedParty1Added(0);
		regForm.setAdoptionDeedParty2Added(0);
		regForm.setAdoptionDeedParty3Added(0);
		regForm.setRent(0);
		regForm.setAdvance(0);
		regForm.setAdvancePaymntDetails("");
		regForm.setDevlpmtCharge(0);
		regForm.setOtherRecCharge(0);
		regForm.setPremium(0);
		regForm.setTermLease(0);
		regForm.setCallingAction("");
		regForm.setTrustName("");
		regForm.setTrustDate("");
		regForm.setWithPos("");
		regForm.setSecLoanAmt(0);
		regForm.setIndcountryArb("1");
		regForm.setIndstatenameArb("1");
		regForm.setInddistrictArb("");
		regForm.setFnameArb("");
		regForm.setMnameArb("");
		regForm.setLnameArb("");
		regForm.setGendarArb("M");
		regForm.setAgeArb("");
		regForm.setFatherNameArb("");
		regForm.setMotherNameArb("");
		regForm.setSpouseNameArb("");
		regForm.setNationalityArb("");
		regForm.setIndaddressArb("");
		regForm.setIndcountryArb("");
		regForm.setIndstatenameArb("");
		regForm.setInddistrictArb("");
		regForm.setIndphnoArb("");
		regForm.setIndmobnoArb("");
		regForm.setEmailIDArb("");
		regForm.setIndCategoryArb("");
		regForm.setIndDisabilityArb("N");
		regForm.setIndDisabilityDescArb("");
		regForm.setListIDArb("");
		regForm.setIdnoArb("");
		regForm.setAdvance(0);
		regForm.setAdvancePaymntDate("");
		regForm.setPossGiven("N");
		regForm.setPossGivenName("");
		regForm.setPoaWithConsid("");
		regForm.setPoaPeriod(0);
		regForm.setConsiAmountTrns("");
		regForm.setExtraFieldLabel(RegInitConstant.EXTRA_FIELD_NOT_APPLICABLE);
		regForm.setContriProp("");
		regForm.setDissolutionDate("");
		regForm.setRetirmentDate("");
		regForm.setAgreeMemoInstFlag(0);
		regForm.setClaimantFlag(0);
		regForm.setPoaHolderFlag(0);
		// for common deeds
		regForm.setCommonDeed(0);
		regForm.setAddPartyNewRole(0); // variable for radio. value
		// 0 for same role
		regForm.setRoleSameAsPrevious(0); // flag for above radio. 1
		// for previous (same)
		// role
		regForm.setCommonDeedRoleName("");
		regForm.setAddMoreTransParty("N");
		regForm.setAddMoreParticularCounter(0);
		regForm.setMapParticulars(new HashMap());
		regForm.setParticularName("");
		regForm.setParticularDesc("");
		regForm.setDeleteParticularID("");
		regForm.setHdnDeleteParticularID("");
		regForm.setIsPoaAddedFlag(0);
		regForm.setApplicantRoleId(0);
		regForm.setInitiateAdjuApp(0);
		regForm.setStampManually("N");
		regForm.setStampduty1("");
		regForm.setNagarPalikaDuty("");
		regForm.setPanchayatDuty("");
		regForm.setUpkarDuty("");
		regForm.setTotalduty("");
		regForm.setRegistrationFee("");
		regForm.setStampduty1Adju("");
		regForm.setNagarPalikaDutyAdju("");
		regForm.setPanchayatDutyAdju("");
		regForm.setUpkarDutyAdju("");
		regForm.setTotaldutyAdju("");
		regForm.setRegistrationFeeAdju("");
		regForm.setIsFirstPartyAddedFlag(0);
		regForm.setExecutantClaimant(0);
		regForm.setExecutantClaimantTrns(0);
		regForm.setAuthPerAddress("");
		regForm.setAuthPerCountry("1");
		regForm.setAuthPerDistrict("");
		regForm.setAuthPerStatename("1");
		regForm.setAuthPerFatherName("");
		regForm.setAuthPerRelationship(0);
		regForm.setAuthPerGendar("M");
		regForm.setAuthPerAddressTrns("");
		regForm.setAuthPerCountryTrns("1");
		regForm.setAuthPerDistrictTrns("");
		regForm.setAuthPerStatenameTrns("1");
		regForm.setAuthPerFatherNameTrns("");
		regForm.setAuthPerRelationshipTrns(0);
		regForm.setAuthPerGendarTrns("M");
		regForm.setAdjuRemarks("");
		regForm.setExecutionDate("");
		regForm.setRegistrationDate("");
		regForm.setRegistrationNo("");
		regForm.setReceiptAmount(0);
		regForm.setReceiptAmountDisp("");
		regForm.setBankName("");
		regForm.setCancellationLabel("");
		regForm.setAddPropertyOptional("");
		regForm.setHdnAddPropertyOptional("off");
		// changed by akansha for owner
		// for applicant
		regForm.setOwnerIndphno("");
		regForm.setOwnerPostalCode("");
		regForm.setOwnerIndDisability("N");
		regForm.setOwnerIndDisabilityDesc("");
		regForm.setOwnerIndMinority("N");
		regForm.setOwnerIndPovertyLine("N");
		regForm.setOwnerOccupationId("");
		regForm.setOwnerOccupationIdName("");
		regForm.setOwnerFatherName("");
		regForm.setOwnerMotherName("");
		regForm.setOwnerSpouseName("");
		regForm.setOwnerRelationship("");
		regForm.setOwnerRelationshipName("");
		regForm.setOwnerInddistrictName("");
		regForm.setOwnerIndScheduleArea("N");
		regForm.setOwnerPermissionNo("");
		regForm.setOwnerCertificate("");
		regForm.setOwnerPanNumber("");
		regForm.setOwnerInddistrict("");
		regForm.setOwnerIndCategory("");
		regForm.setOwnerIndCategoryName("");
		// for transacting
		regForm.setOwnerIndphnoTrns("");
		regForm.setOwnerPostalCodeTrns("");
		regForm.setOwnerIndDisabilityTrns("N");
		regForm.setOwnerIndDisabilityDescTrns("");
		regForm.setOwnerIndMinorityTrns("N");
		regForm.setOwnerIndPovertyLineTrns("N");
		regForm.setOwnerOccupationIdTrns("");
		regForm.setOwnerFatherNameTrns("");
		regForm.setOwnerMotherNameTrns("");
		regForm.setOwnerSpouseNameTrns("");
		regForm.setOwnerRelationshipTrns("");
		regForm.setOwnerIndScheduleAreaTrns("N");
		regForm.setOwnerPermissionNoTrns("");
		regForm.setOwnerCertificateTrns("");
		regForm.setOwnerPanNumberTrns("");
		regForm.setOwnerInddistrictTrns("");
		regForm.setOwnerIndCategoryTrns("");
		regForm.setOwnerIndcountryTrns("1");
		regForm.setOwnerIndstatenameTrns("1");
		regForm.setClrLink("");
		regForm.setClrRcmsFlag("");

	}
}