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
 * Description :   
 */
/**
 * File Name: DutyCalculationAction.java
 * @author Madan Mohan
 * @version 1.0
 * Created on 14 jan 2008
 *
 */
package com.wipro.igrs.newdutycalculation.action;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.newPropvaluation.dao.PropertyValuationDAO;
import com.wipro.igrs.newPropvaluation.dto.PropertyValuationDTO;
import com.wipro.igrs.newdutycalculation.bo.CalculateDuty;
import com.wipro.igrs.newdutycalculation.bo.CalculateRegFee;
import com.wipro.igrs.newdutycalculation.bo.DutyCalculationBO;
import com.wipro.igrs.newdutycalculation.constant.CommonConstant;
import com.wipro.igrs.newdutycalculation.dto.UserEnterableDTO;
import com.wipro.igrs.newdutycalculation.form.DutyCalculationForm;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.propertyvaluation.bo.PropertyValuationBO;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;

/**
 * @since 23 April 2014
 * @author Vinay Sharma
 * @see this class is used for Duty Calculation Action
 */
public class DutyCalculationAction extends BaseAction {
	/**
	 * @see forwardJsp is used for redirecting
	 */
	private String forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
	/**
	 * @author Vinay Sharma
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
		if (form != null) {
			DutyCalculationForm eForm = (DutyCalculationForm) form;
			DutyCalculationBO bo = new DutyCalculationBO();
			PropertyValuationBO pbo = new PropertyValuationBO();
			eForm.getDutyCalculationDTO().setCurrentYear(bo.getCurrentYear());
			String page = (String) request.getParameter("page");
			String userId = (String) session.getAttribute("UserId");
			String language = (String) session.getAttribute("languageLocale");
			eForm.getDutyCalculationDTO().setUserId(userId);
			eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
			eForm.getDutyCalculationDTO().setOpenPopUpRent("");
			String locale = CommonConstant.LANGUAGE_HINDI;
			Locale currentLocale;
			if (session.getAttribute("org.apache.struts.action.LOCALE") != null) {
				currentLocale = (Locale) session.getAttribute("org.apache.struts.action.LOCALE");
				locale = currentLocale.toString();
			}
			logger.debug("Page  " + page);
			DutyCalculationDTO dto = eForm.getDutyCalculationDTO();
			logger.debug("Before action");
			String formName = dto.getFormName();
			String actionName = dto.getActionName();
			String modName = (String) request.getParameter("modName");
			logger.debug("modName:-" + modName);
			String moduleNameE = (String) session.getAttribute("modName");
			String fnName = (String) request.getParameter("fnName");
			String functionNameE = (String) session.getAttribute("fnName");
			logger.debug("modName:-" + modName);
			boolean regFlag = true;
			String fromModule = (String) request.getParameter("fromModule");
			String rentRequest = (String) request.getParameter("pageAction");
			// developer agreement
			request.setAttribute("instsId", String.valueOf(eForm.getInstDTO().getInstId()));
			request.setAttribute("builderShare", String.valueOf(eForm.getInstDTO().getInStampDutyAmount()));
			/*
			 * if(2179==eForm.getInstDTO().getInstId()){ request.setAttribute("instsId", String.valueOf(eForm.getInstDTO().getInstId())); request.setAttribute("builderShare", String.valueOf(eForm.getInstDTO().getInStampDutyAmount())); request.setAttribute("builderShareFlag", "Y"); }else{ request.setAttribute("builderShareFlag", "N"); }
			 */
			logger.debug("Builder share from request --->" + request.getAttribute("builderShare"));
			if (fromModule != null) {
				if (fromModule.equalsIgnoreCase("propVal")) {
					page = "";
					formName = "";
					actionName = CommonConstant.RETURN_FROM_PROP_VAL;
				}
			}
			if (modName != null && fnName != null) {
				session.setAttribute("modName", modName);
				session.setAttribute("fnName", fnName);
			}
			if (CommonConstant.DUTY_PAGE.equals(page)) {
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				dto.setFirstName("");
				dto.setMidName("");
				dto.setLastName("");
				dto.setTypeOfTransaction("N");
				dto.setDob("");
				dto.setDobDay("");
				dto.setDobMonth("");
				dto.setAvailExemption("");
				dto.setDobYear("");
				dto.setSex("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setBaseValue("0.0");// double to string
				eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				dto.setActionName("");
				dto.setPropertyValuationRequired("");
				dto.setPropertyName("");
				dto.setMarketValue("");
				dto.setValuationIdValidate("");
				dto.setValuationId("");
				dto.setHvValId("");
				dto.setMinRegDisclaimerFlag("");
				dto.setMinStampDisclaimerFlag("");
				dto.setHvValIdFlag("");
				dto.setAddPropertyFlag("");
				dto.setPropertyDetailsList(new ArrayList());
				dto.setUserValueList(new ArrayList());
				dto.setExemptionDescpList(new ArrayList());
				dto.setExemptionList(new ArrayList());
				dto.setRegFeeExemptionList(new ArrayList());
				dto.setRegFeeExemptionDiscriptionList(new ArrayList());
				dto.setInstDescription("");
				dto.setFromModule("");
				dto.setCancellationFlag("");
				dto.setDeedCategoryId("");
				dto.setDeedCategoryName("");
				dto.setFamilyCheck("");
				dto.setFamilyVisible("");
				dto.setFamilyCheckFlag("");
				dto.setDeedDescription("");
				dto.setMunicipalCheck("");
				dto.setMunicipalCheckFlag("");
				dto.setMunicipalVisible("N");
				dto.setActionName("");
				actionName = "";
				dto.setMineralListSelected(new ArrayList());
				dto.setMineralSelected(null);
				dto.setMineralList(new ArrayList());
				dto.setSelectedMineralId("");
				if (modName.equalsIgnoreCase("Property Valuation")) {
					dto.setFromEstamp(false);
				} else {
					dto.setFromEstamp(true);
				}
				eForm.setHdnAmount("");
				forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
			}
			if (CommonConstant.REGISTRATION_DUTY_PAGE.equals(page)) {
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				// dto.setFirstName("");
				// dto.setMidName("");
				// dto.setLastName("");
				// dto.setDob("");
				// dto.setDobDay("");
				// dto.setDobMonth("");
				// dto.setDobYear("");
				// dto.setSex("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setAvailExemption("");
				dto.setBaseValue("0.0");// double to string
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				dto.setFirstName("");
				dto.setMidName("");
				dto.setLastName("");
				dto.setDob("");
				dto.setMinRegDisclaimerFlag("");
				dto.setMinStampDisclaimerFlag("");
				dto.setDobDay("");
				dto.setDobMonth("");
				dto.setCancellationFlag("");
				dto.setDobYear("");
				dto.setSex("");
				dto.setTypeOfTransaction("N");
				dto.setFromModule("");
				dto.setDeedCategoryName("");
				dto.setDeedCategoryId("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setBaseValue("0.0");// double to string
				eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				dto.setActionName("");
				dto.setPropertyValuationRequired("");
				dto.setPropertyName("");
				dto.setMarketValue("");
				dto.setValuationIdValidate("");
				dto.setValuationId("");
				dto.setHvValId("");
				dto.setHvValIdFlag("");
				dto.setAddPropertyFlag("");
				dto.setFamilyCheck("");
				dto.setFamilyVisible("");
				dto.setFamilyCheckFlag("");
				dto.setPropertyDetailsList(new ArrayList());
				dto.setUserValueList(new ArrayList());
				dto.setExemptionDescpList(new ArrayList());
				dto.setExemptionList(new ArrayList());
				dto.setRegFeeExemptionList(new ArrayList());
				dto.setRegFeeExemptionDiscriptionList(new ArrayList());
				dto.setInstDescription("");
				dto.setDeedDescription("");
				dto.setMunicipalVisible("N");
				dto.setMineralSelected(null);
				dto.setMineralList(new ArrayList());
				// eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				eForm.setHdnAmount("");
				dto.setActionName("");
				dto.setFromEstamp(false);
				forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
				eForm.setModuleName(CommonConstant.REGISTRATION_DUTY_PAGE);
				formName = "dutyHomePage";
				actionName = "nextPage";
			}
			if (CommonConstant.ESTAMP_MODULE.equalsIgnoreCase(fromModule)) {
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				// dto.setFirstName("");
				// dto.setMidName("");
				// dto.setLastName("");
				// dto.setDob("");
				// dto.setDobDay("");
				// dto.setDobMonth("");
				// dto.setDobYear("");
				// dto.setSex("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setBaseValue("0.0");// double to string
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				dto.setFirstName("");
				dto.setMidName("");
				dto.setLastName("");
				dto.setFromModule(CommonConstant.ESTAMP_MODULE);
				dto.setDob("");
				dto.setDobDay("");
				dto.setDobMonth("");
				dto.setDobYear("");
				dto.setSex("");
				dto.setDeedCategoryName("");
				dto.setDeedCategoryId("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setBaseValue("0.0");// double to string
				eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				dto.setActionName("");
				dto.setPropertyValuationRequired("");
				dto.setPropertyName("");
				dto.setMarketValue("");
				dto.setValuationIdValidate("");
				dto.setValuationId("");
				dto.setHvValId("");
				dto.setHvValIdFlag("");
				dto.setAddPropertyFlag("");
				dto.setPropertyDetailsList(new ArrayList());
				dto.setUserValueList(new ArrayList());
				dto.setExemptionDescpList(new ArrayList());
				dto.setExemptionList(new ArrayList());
				dto.setRegFeeExemptionList(new ArrayList());
				dto.setRegFeeExemptionDiscriptionList(new ArrayList());
				dto.setInstDescription("");
				dto.setDeedDescription("");
				dto.setMunicipalVisible("N");
				// eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				dto.setMineralSelected(null);
				dto.setMineralList(new ArrayList());
				// eForm.setModuleName("");
				dto.setActionName("");
				forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
				eForm.setModuleName(CommonConstant.REGISTRATION_DUTY_PAGE);
				formName = "dutyHomePage";
				actionName = "nextPage";
			}
			logger.debug("actionName:-" + actionName + ":" + formName);
			logger.debug("forwardJsp:-" + forwardJsp);
			if (CommonConstant.DUTY_HOME_PAGE.equals(formName)) {
				if (CommonConstant.CANCEL_ACTION.equals(actionName)) {
					forwardJsp = "welcome";
				}
				if (CommonConstant.RESET_PAGE_ACTION.equals(actionName)) {
					eForm.setExemptionDTOList(new ArrayList());
					eForm.setInstrumentDTOList(new ArrayList());
					dto.setFirstName("");
					dto.setMidName("");
					dto.setLastName("");
					dto.setDob("");
					dto.setDobDay("");
					dto.setDobMonth("");
					dto.setDobYear("");
					dto.setSex("");
					eForm.setHdnAmount("");
					dto.setActionName(null);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
				}
				if (CommonConstant.DUTY_NEXT_ACTION.equals(actionName)) {
					// dto.setDeedId(dto.getDeedId());
					if (eForm.getModuleName() != null && eForm.getModuleName().equalsIgnoreCase(CommonConstant.REGISTRATION_DUTY_PAGE)) {
						eForm.setDeedCategoryDTOList(bo.getDeedCategory(language));
						// eForm.setDutycalculationDTOList(bo.getDeed(
						// CommonConstant.NON_DUTY_REGISTRATION_DEED ));
						// added by lavi
						dto.setFromReg(1);
					} else {
						/* Added by Vinay */
						eForm.setDeedCategoryDTOList(bo.getDeedCategory(language));
						// eForm.setDutycalculationDTOList(bo.getDeed(
						// CommonConstant.NON_DUTY_DEED)); commented by vinay
					}
					if (request.getParameter("fromAdju") != null) {
						// RegCommonForm rForm= new RegCommonForm();
						if (request.getParameter("fromAdju").equalsIgnoreCase("Y")) {
							eForm.setFromAdjudication(1);
							// session.setAttribute("fnName","Adjudication");
							// rForm.setFromAdjudication(eForm.getFromAdjudication
							// ());
						} else {
							eForm.setFromAdjudication(0);
							// rForm.setFromAdjudication(eForm.getFromAdjudication
							// ());
							// session.setAttribute("fnName","Initiation");
						}
					} else {
						eForm.setFromAdjudication(0);
					}
					if (locale.equalsIgnoreCase("hi_IN")) {
						dto.setSex("M".equals(dto.getSex()) ? "h.Male" : "F".equals(dto.getSex()) ? "h.Female" : "");
					} else {
						dto.setSex("M".equals(dto.getSex()) ? "Male" : "F".equals(dto.getSex()) ? "Female" : "");
					}
					dto.setActionName(null);
					actionName = "";
					eForm.setDutyCalculationDTO(dto);
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
			}
			if (CommonConstant.DUTY_NEXT_PAGE.equals(formName)) {
				logger.debug("actionName:-" + actionName);
				if (CommonConstant.DUTY_PREV_ACTION.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					dto.setFirstName(dto1.getFirstName());
					dto.setMidName(dto1.getMidName());
					dto.setLastName(dto1.getLastName());
					dto.setAge(dto1.getAge());
					if (locale.equalsIgnoreCase("hi_IN")) {
						dto.setSex("M".equals(dto.getSex()) ? "h.MAlE" : "F".equals(dto.getSex()) ? "h.Female" : "");
					} else {
						dto.setSex("Male".equals(dto1.getSex()) ? "M" : "Female".equals(dto1.getSex()) ? "F" : "");
					}
					logger.debug("Name:-" + dto1.getFirstName());
					eForm.setDutyCalculationDTO(dto);
					dto.setActionName(null);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
				}
				logger.debug(" before change action actionName:-" + actionName);
				if (CommonConstant.DEED_CATEGORY_CHANGE_ACTION.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					eForm.setDutycalculationDTOList(bo.getDeed(dto1.getDeedCategoryId(), dto1.getCancellationFlag(), language, dto1.getFromModule()));
					eForm.getDutyCalculationDTO().setBaseValue("0.0");// double
					// to
					// string
					eForm.getDutyCalculationDTO().setAnnualRent(0.0);
					eForm.getDutyCalculationDTO().setDutyPaid(0.0);
					eForm.getDutyCalculationDTO().setDeedType("");
					eForm.setInstDTO(new InstrumentsDTO());
					eForm.setExempDTO(new ExemptionDTO());
					eForm.setExemptionDTOList(new ArrayList());
					eForm.setInstrumentDTOList(new ArrayList());
					dto.setUserValueList(new ArrayList());
					dto.setDeedDescription("");
					dto.setInstDescription("");
					dto.setPropertyDetailsList(new ArrayList());
					dto.setMunicipalVisible("");
					if (!"Y".equalsIgnoreCase(dto.getCancellationFlag())) {
						dto.setExemptionList(new ArrayList());
						dto.setExemptionDescpList(new ArrayList());
						dto.setRegFeeExemptionList(new ArrayList());
						dto.setRegFeeExemptionDiscriptionList(new ArrayList());
					}
					dto.setPropertyValuationRequired("");
					dto.setPropertyName("");
					dto.setMarketValue("");
					dto.setValuationIdValidate("");
					dto.setValuationId("");
					dto.setHvValId("");
					dto.setHvValIdFlag("");
					dto.setAvailExemption("");
					eForm.setHdnAmount("");
					// added for radio change check to refresh mineral values
					// 2277 2254 2261 2262
					eForm.setMineralSelected("");
					dto1.setSelectedMineralId("");
					// dto.setMineralList(new ArrayList());
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					dto.setActionName(null);
					actionName = "";
				}
				if (CommonConstant.EXCHANGE_ADD_PROPERTY.equalsIgnoreCase(actionName)) {
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					dto.setRinPustikaCheck("");
					dto.setRinPustikaCheckFlag("");
					String exP = dto.getExchangeParty();
					boolean rinFlag = false;
					ArrayList propertyList = dto.getExchangeList1();
					for (int i = 0; i < propertyList.size(); i++) {
						DutyCalculationDTO dddto = (DutyCalculationDTO) propertyList.get(i);
						if ("3".equalsIgnoreCase(dddto.getPropertyId())) {
							rinFlag = true;
						}
					}
					if (rinFlag) {
						dto.setRinPustikaVisible1("Y");
					} else {
						dto.setRinPustikaVisible1("N");
					}
					boolean rinFlag1 = false;
					ArrayList propertyList1 = dto.getExchangeList2();
					for (int i = 0; i < propertyList1.size(); i++) {
						DutyCalculationDTO dddto = (DutyCalculationDTO) propertyList1.get(i);
						if ("3".equalsIgnoreCase(dddto.getPropertyId())) {
							rinFlag1 = true;
						}
					}
					if (rinFlag1) {
						dto.setRinPustikaVisible2("Y");
					} else {
						dto.setRinPustikaVisible2("N");
					}
					dto.setAddPropertyFlag("Y");
					dto.setActionName("");
				}
				if (CommonConstant.EXCHANGE_DELETE_PROPERTY1.equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList propertyDetails = dto1.getExchangeList1();
					ArrayList propertyDetailsList = new ArrayList();
					for (int i = 0; i < propertyDetails.size(); i++) {
						DutyCalculationDTO ddto = (DutyCalculationDTO) propertyDetails.get(i);
						if (dto1.getDeleteValuationId().equalsIgnoreCase(ddto.getValuationId())) {
							dto1.setExchangeMV1(dto1.getExchangeMV1() - Double.parseDouble(ddto.getMarketValue()));
						} else {
							propertyDetailsList.add(ddto);
						}
					}
					dto1.setExchangeList1(propertyDetailsList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.EXCHANGE_DELETE_PROPERTY2.equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList propertyDetails = dto1.getExchangeList2();
					ArrayList propertyDetailsList = new ArrayList();
					for (int i = 0; i < propertyDetails.size(); i++) {
						DutyCalculationDTO ddto = (DutyCalculationDTO) propertyDetails.get(i);
						if (dto1.getDeleteValuationId().equalsIgnoreCase(ddto.getValuationId())) {
							dto1.setExchangeMV2(dto1.getExchangeMV2() - Double.parseDouble(ddto.getMarketValue()));
						} else {
							propertyDetailsList.add(ddto);
						}
					}
					dto1.setExchangeList2(propertyDetailsList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if ("ShareClickAction".equalsIgnoreCase(actionName)) {
					/*
					 * String param[]=request.getParameterValues("userEnterableFieldValue" ); ArrayList list=eForm.getInstDTO().getUserEnterableList(); if(list!=null) { int j=0; for(int i=0;i<list.size();i++) { InstrumentsDTO indto= (InstrumentsDTO) list.get(i); String id=indto.getUserEnterableId(); String value=""; if( "Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag())&& "Y" .equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) { value=eForm.getInstDTO().getCommonUserField(); } else if
					 * ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag())&& "N" .equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) { value=eForm.getInstDTO().getInStampDutyAmount(); } else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag())&& "Y" .equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) { value=eForm.getInstDTO().getAsConsiderationAmount(); } else { value=param[j]; j++; } indto.setUserEnterableFieldValue(value); } }
					 */
					if ("N".equalsIgnoreCase(dto.getHvValIdFlag())) {
						dto.setValuationIdValidate("false");
						dto.setValuationId("");
						dto.setValuationIdList(new ArrayList());
						dto.setMarketValue("");
						dto.setPropertyName("");
					} else {
						dto.setValuationIdValidate("");
						dto.setValuationId("");
					}
					eForm.getInstDTO().setAsConsiderationAmount("");
					eForm.getInstDTO().setInStampDutyAmount("");
					if ("Y".equalsIgnoreCase(dto.getHvShareFlag())) {
						eForm.getInstDTO().setUserEnterableFieldReq("Y");
						ArrayList enetrableList = bo.getUserEnterableField(String.valueOf(dto.getDeedId()), String.valueOf(eForm.getInstDTO().getInstId()), language);
						eForm.getInstDTO().setUserEnterableList(enetrableList);
					} else {
						eForm.getInstDTO().setUserEnterableFieldReq("N");
						eForm.getInstDTO().setUserEnterableList(null);
					}
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.ID_VALIDATE.equalsIgnoreCase(actionName)) {
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							indto.setUserEnterableFieldValue(value);
						}
					}
					if ("N".equalsIgnoreCase(dto.getHvValIdFlag())) {
						dto.setValuationIdValidate("false");
						dto.setValuationId("");
						dto.setValuationIdList(new ArrayList());
						dto.setMarketValue("");
						dto.setPropertyName("");
					} else {
						dto.setValuationIdValidate("");
						dto.setValuationId("");
					}
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.VALUATION_ID_VALIDATE.equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					// HashMap<String,String> userValueMap=new
					// HashMap<String,String>();
					String instIdForClrCheck =String.valueOf(eForm.getInstDTO().getInstId());
					String valId = eForm.getDutyCalculationDTO().getValuationId();
					PropertyValuationDAO pDao = new PropertyValuationDAO();
					String instClrFlagCheck = pDao.getInstrumentClrFlag(instIdForClrCheck);
					instClrFlagCheck=(null==instClrFlagCheck)?"N":("".equalsIgnoreCase(instClrFlagCheck)?"N":instClrFlagCheck);
					RegCommonBO commanBo= new RegCommonBO();
					String valIdClrFlag=commanBo.getClrByClrTable(valId);
					valIdClrFlag=(null==valIdClrFlag)?"N":("".equalsIgnoreCase(valIdClrFlag)?"N":valIdClrFlag);
					
						if(("Y".equalsIgnoreCase(valIdClrFlag) & "N".equalsIgnoreCase(instClrFlagCheck))){
							request.setAttribute("ERROR", "Valuation id of CLR cannot be used in non CLR instruments");
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							return mapping.findForward(forwardJsp);
						}else if(("N".equalsIgnoreCase(valIdClrFlag) & "Y".equalsIgnoreCase(instClrFlagCheck))){
							request.setAttribute("ERROR", "Valuation id of Non CLR instruments cannot be used in CLR instruments");
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							return mapping.findForward(forwardJsp);
						}
					
					ArrayList userList = new ArrayList();
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue("");
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					String validate = bo.validateValuationId(dto1, eForm.getInstDTO(), language);// method
					// alterd
					// by
					// roopam
					// 14april15
					if ("A".equalsIgnoreCase(validate)) {
						dto1.setValuationIdValidate("true");
						// eForm.getInstDTO().setCommonUserField("");
						// eForm.getInstDTO().setAsConsiderationAmount("");
						// eForm.getInstDTO().setInStampDutyAmount("");
						dto1.setAddPropertyFlag("N");
						dto1.setHvValIdFlag("N");
						dto1.setHvValId("");
						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getAsConsiderationAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
									value = eForm.getInstDTO().getValueOfMovableProp();
								}// added by ankit for plant and machinery
								else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}
					} else if ("NA".equalsIgnoreCase(validate)) {
						dto1.setValuationIdValidate("false");
						dto1.setAddPropertyFlag("Y");
						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getAsConsiderationAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
									value = eForm.getInstDTO().getValueOfMovableProp();
								}// added by ankit for plant and machinery
								else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}
					} else if ("Agri".equalsIgnoreCase(validate)) {
						dto1.setValuationIdValidate("AGRI");
						dto1.setAddPropertyFlag("Y");
						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getAsConsiderationAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
									value = eForm.getInstDTO().getValueOfMovableProp();
								}// added by ankit for plant and machinery
								else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}
					} else if ("SUB".equalsIgnoreCase(validate)) {
						dto1.setValuationIdValidate("SUB");
						dto1.setAddPropertyFlag("Y");
						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getAsConsiderationAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
									value = eForm.getInstDTO().getValueOfMovableProp();
								}// added by ankit for plant and machinery
								else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}
					} else if ("buyer".equalsIgnoreCase(validate)) {
						dto1.setValuationIdValidate("BUYER");
						dto1.setAddPropertyFlag("Y");
						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getAsConsiderationAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
									value = eForm.getInstDTO().getValueOfMovableProp();
								}// added by ankit for plant and machinery
								else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}
					} else if ("leaseun".equalsIgnoreCase(validate)) {
						dto1.setValuationIdValidate("lease");
						dto1.setAddPropertyFlag("Y");
						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getAsConsiderationAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
									value = eForm.getInstDTO().getValueOfMovableProp();
								}// added by ankit for plant and machinery
								else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}
					} else {
						dto1.setValuationIdValidate("DUP");
						dto1.setAddPropertyFlag("Y");
						if (list != null) {
							int j = 0;
							for (int i = 0; i < list.size(); i++) {
								InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
								String id = indto.getUserEnterableId();
								String value = "";
								if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getCommonUserField();
								} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getInStampDutyAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
									value = eForm.getInstDTO().getAsConsiderationAmount();
								} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
									value = eForm.getInstDTO().getValueOfMovableProp();
								}// added by ankit for plant and machinery
								else {
									value = param[j];
									j++;
								}
								indto.setUserEnterableFieldValue(value);
							}
						}
					}
					dto1.setActionName(null);
					if (dto1.getPropertyDetailsList().size() > 0) {
						InstrumentsDTO idto = eForm.getInstDTO();
						idto.setLeaseFreeze("Y");
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.ADD_PROPERTY.equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					dto1.setRinPustikaCheck("");
					dto1.setRinPustikaCheckFlag("");
					dto1.setAddPropertyFlag("Y");
					boolean rinFlag = false;
					ArrayList propertyList = dto1.getPropertyDetailsList();
					for (int i = 0; i < propertyList.size(); i++) {
						DutyCalculationDTO dddto = (DutyCalculationDTO) propertyList.get(i);
						if ("3".equalsIgnoreCase(dddto.getPropertyId())) {
							rinFlag = true;
						}
					}
					if (rinFlag) {
						dto1.setRinPustikaVisible("Y");
					} else {
						dto1.setRinPustikaVisible("N");
					}
					eForm.getInstDTO().setCommonUserField("");
					eForm.getInstDTO().setAsConsiderationAmount("");
					eForm.getInstDTO().setInStampDutyAmount("");
					eForm.getDutyCalculationDTO().setGovValue("");
					eForm.getInstDTO().setValueOfMovableProp("");// added by
					// ankit for
					// plant and
					// machinery
					ArrayList userList = new ArrayList();
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue("");
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					//added for developer agreement for the by pass of valuation id check
					if (2179 == eForm.getInstDTO().getInstId()|| dto.getInstClrFlag().equalsIgnoreCase("Y")) {
						dto.setHvValIdFlag("N");
						dto.setValuationIdValidate("false");
						dto.setValuationId("");
						dto.setValuationIdList(new ArrayList());
						dto.setMarketValue("");
						dto.setPropertyName("");
						dto.setHvValId("N");
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.ADD_LAND_REVENUE.equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					if (dto1.getAvgLandRevenue() == null || dto1.getAvgLandRevenue().equalsIgnoreCase("")) {
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute("ERROR", "Enter average land revenue");
						} else {
							request.setAttribute("ERROR", "Enter average land revenue");
						}
						actionName = "";
						forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					} else {
						int id = dto1.getLandRevenueId() + 1;
						dto1.setLandRevenueId(id);
						DutyCalculationDTO dto2 = new DutyCalculationDTO();
						dto2.setAvgLandRevenue(dto1.getAvgLandRevenue());
						dto2.setLandRevenueIdString(String.valueOf(id));
						dto1.getLandRevenueList().add(dto2);
						dto1.setAvgLandRevenue("");
						dto1.setTotalLandRevenue(dto1.getTotalLandRevenue() + Double.parseDouble(dto2.getAvgLandRevenue()));
						if (Double.parseDouble(dto2.getAvgLandRevenue()) >= dto1.getMaxLandRevenue()) {
							dto1.setMaxLandRevenue(Double.parseDouble(dto2.getAvgLandRevenue()));
						}
						eForm.setDutyCalculationDTO(dto1);
						actionName = "";
						forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					}
				}
				if (CommonConstant.DELETE_LAND_REVENUE.equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList propertyDetails = dto1.getLandRevenueList();
					ArrayList propertyDetailsList = new ArrayList();
					dto1.setMaxLandRevenue(0);
					for (int i = 0; i < propertyDetails.size(); i++) {
						DutyCalculationDTO ddto = (DutyCalculationDTO) propertyDetails.get(i);
						if (dto1.getDeleteValuationId().equalsIgnoreCase(String.valueOf(ddto.getLandRevenueIdString()))) {
							dto1.setTotalLandRevenue(dto1.getTotalLandRevenue() - Double.parseDouble(ddto.getAvgLandRevenue()));
						} else {
							if (Double.parseDouble(ddto.getAvgLandRevenue()) >= dto1.getMaxLandRevenue()) {
								dto1.setMaxLandRevenue(Double.parseDouble(ddto.getAvgLandRevenue()));
							}
							propertyDetailsList.add(ddto);
						}
					}
					if (propertyDetailsList.size() == 0) {
						dto1.setAddPropertyFlag("Y");
					}
					dto1.setLandRevenueList(propertyDetailsList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.DELETE_PROPERTY.equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList propertyDetails = dto1.getPropertyDetailsList();
					ArrayList propertyDetailsList = new ArrayList();
					for (int i = 0; i < propertyDetails.size(); i++) {
						DutyCalculationDTO ddto = (DutyCalculationDTO) propertyDetails.get(i);
						if (dto1.getDeleteValuationId().equalsIgnoreCase(ddto.getValuationId())) {
							if (dto1.getDeedId() != 1058) {
								dto1.setTotalStampDuty(dto1.getTotalStampDuty() - ddto.getStampDuty());
								dto1.setTotalregFee(dto1.getTotalregFee() - ddto.getRegFee());
								dto1.setTotalUpkar(dto1.getTotalUpkar() - ddto.getUpkarDuty());
								dto1.setTotalNagarPalika(dto1.getTotalNagarPalika() - ddto.getNagarPalikaDuty());
								dto1.setTotalPanchyat(dto1.getTotalPanchyat() - ddto.getPanchayatDuty());
								dto1.setTotalPaidStamp(dto1.getTotalPaidStamp() - ddto.getPaidStamp());
								dto1.setTotalPaidReg(dto1.getTotalPaidReg() - ddto.getPaidReg());
								dto1.setEntireTotal(dto1.getEntireTotal() - ddto.getTotal());
								dto1.setTotalPayableReg(dto1.getTotalPayableReg() - ddto.getPayableRegFee());
								dto1.setTotalPayableStamp(dto1.getTotalPayableStamp() - ddto.getPayableStampDuty());
								double govtValue = 0;
								if (ddto.getGovValue() != null && !ddto.getGovValue().equalsIgnoreCase("")) {
									govtValue = Double.parseDouble(ddto.getGovValue());
								}
								dto1.setTotalGovValue(dto1.getTotalGovValue() - govtValue);
							}
						} else {
							propertyDetailsList.add(ddto);
						}
					}
					if (eForm.getInstDTO().getInstId() == 2179 || dto.getInstClrFlag().equalsIgnoreCase("Y")) //added for developer agreement for the by pass of valuation id check
					{
						// dto1.setHvShare("N");
						dto.setHvValIdFlag("N");
						dto.setValuationIdValidate("false");
						dto.setValuationId("");
						dto.setValuationIdList(new ArrayList());
						dto.setMarketValue("");
						dto.setPropertyName("");
						dto.setHvValId("N");
					}
					if (propertyDetailsList.size() == 0) {
						dto1.setAddPropertyFlag("Y");
						InstrumentsDTO idto = eForm.getInstDTO();
						idto.setLeaseFreeze("N");
					}
					dto1.setPropertyDetailsList(propertyDetailsList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.DIFF_RIN_PUSTIKA.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					if ("Y".equalsIgnoreCase(dto1.getRinPustikaCheckFlag())) {
						dto1.setRinPustikaCheck("Y");
					} else {
						dto1.setRinPustikaCheck("N");
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.MUNICIPAL_CHECK.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					if ("Y".equalsIgnoreCase(dto1.getMunicipalCheckFlag())) {
						dto1.setMunicipalCheck("Y");
					} else {
						dto1.setMunicipalCheck("N");
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if ("familyAction".equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					if ("Y".equalsIgnoreCase(dto1.getFamilyCheckFlag())) {
						dto1.setFamilyCheck("Y");
					} else {
						dto1.setFamilyCheck("N");
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if ("govAction".equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					if ("Y".equalsIgnoreCase(dto1.getGovCheckFlag())) {
						dto1.setGovCheck("Y");
					} else {
						dto1.setGovCheck("N");
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if ("AddExemption".equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					eForm.setDutyCalculationDTO(dto1);
					ArrayList exempList = dto1.getExemptionList();
					for (int i = 0; i < exempList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempList.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(dto2.getExempId())) {
							dto2.setExempCheckBox(dto2.getExempId());
							dto1.getExemptionDescpList().add(dto2);
							dto.setSelectedExempId(dto2.getExempId());
						}
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if ("addMineralSelected".equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList minerallistSelected = dto1.getMineralList();
					String[] listofMineralIdSelected = dto1.getSelectedMineralId().split("~");
					ArrayList mineralList = new ArrayList();
					for (int i = 0; i < minerallistSelected.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) minerallistSelected.get(i);
						for (int j = 1; j < listofMineralIdSelected.length; j++) {
							String id = listofMineralIdSelected[j];
							if (id.equalsIgnoreCase(dto2.getMineralId())) {
								dto2.setListOFmineral(dto2.getMineralId());
								mineralList.add(dto2.getListOFmineral());
							}
						}
					}
					dto1.setMineralListChecked(mineralList);
					System.out.println(mineralList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					// listofMineralIdSelected ="";
					dto1.setMineralList(new ArrayList());
				}
				if ("deleteExempetion".equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					ArrayList exempList = dto1.getExemptionList();
					ArrayList newList = new ArrayList();
					for (int i = 0; i < exempList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempList.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(dto2.getExempId())) {
							dto2.setExempCheckBox("");
						}
					}
					ArrayList exempDispList = dto1.getExemptionDescpList();
					for (int i = 0; i < exempDispList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempDispList.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(dto2.getExempId())) {
						} else {
							newList.add(dto2);
						}
					}
					dto1.setExemptionDescpList(newList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if ("AddRegExemption".equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					eForm.setDutyCalculationDTO(dto1);
					ArrayList exempList = dto1.getRegFeeExemptionList();
					for (int i = 0; i < exempList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempList.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(dto2.getExempId())) {
							dto2.setExempCheckBox(dto2.getExempId());
							dto1.getRegFeeExemptionDiscriptionList().add(dto2);
						}
					}
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if ("deleteRegExempetion".equalsIgnoreCase(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					ArrayList userList = new ArrayList();
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
								dto1.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
								dto1.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dto1.setUserValueList(userList);
					}
					ArrayList exempList = dto1.getRegFeeExemptionList();
					ArrayList newList = new ArrayList();
					for (int i = 0; i < exempList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempList.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(dto2.getExempId())) {
							dto2.setExempCheckBox("");
						}
					}
					ArrayList exempDispList = dto1.getRegFeeExemptionDiscriptionList();
					for (int i = 0; i < exempDispList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) exempDispList.get(i);
						if (dto1.getSelectedExempId().equalsIgnoreCase(dto2.getExempId())) {
						} else {
							newList.add(dto2);
						}
					}
					dto1.setRegFeeExemptionDiscriptionList(newList);
					eForm.setDutyCalculationDTO(dto1);
					actionName = "";
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if (CommonConstant.DEED_CHANGE_ACTION.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					int deedId = dto1.getDeedId();
					eForm.setHdnAmount("");
					eForm.getDutyCalculationDTO().setBaseValue("0.0");// double
					// to
					// string
					eForm.getDutyCalculationDTO().setAnnualRent(0.0);
					eForm.getDutyCalculationDTO().setGovCheck("");
					eForm.getDutyCalculationDTO().setGovValue("");
					eForm.getDutyCalculationDTO().setGovCheckFlag("");
					eForm.getDutyCalculationDTO().setDutyPaid(0.0);
					eForm.setInstDTO(new InstrumentsDTO());
					eForm.setExempDTO(new ExemptionDTO());
					if (!"Y".equalsIgnoreCase(dto1.getCancellationFlag())) {
						dto.setExemptionList(new ArrayList());
						dto.setExemptionDescpList(new ArrayList());
						dto.setRegFeeExemptionList(new ArrayList());
						dto.setRegFeeExemptionDiscriptionList(new ArrayList());
					}
					ArrayList instList = bo.getInstrument(deedId, locale, dto1.getCancellationFlag(), dto1.getFromModule());
					dto.setValuationIdValidate("");
					/*
					 * ArrayList<ExemptionDTO> exemDTOList =bo.getExemptions(dto.getDeedId());
					 */eForm.setExemptionDTOList(new ArrayList());
					dto.setDeedDescription(bo.getDeedDiscription(deedId, language));
					dto.setExemptionDescpList(new ArrayList());
					dto.setInstDescription("");
					if (deedId == 1054) {
						dto.setValuationIdValidate("true");
						dto.setExchangeMV1(0);
						dto.setExchangeMV2(0);
						dto.setExchangeList1(new ArrayList());
						dto.setExchangeList2(new ArrayList());
					}
					eForm.setInstrumentDTOList(instList);
					// added for radio change check to refresh mineral values
					// 2277 2254 2261 2262
					eForm.setMineralSelected("");
					dto1.setSelectedMineralId("");
					// dto.setMineralList(new ArrayList());
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					dto.setActionName(null);
					actionName = "";
				}
				if (CommonConstant.DUTY_CALCULATE_ACTION.equals(actionName)) {
					InstrumentsDTO instdto = eForm.getInstDTO();
					ExemptionDTO exedto = eForm.getExempDTO();
					DutyCalculationDTO dtoForm = eForm.getDutyCalculationDTO();
					// Added by Rakesh for Unique Rin Pustika No
					ArrayList tempD = new ArrayList();
					ArrayList rinPustikaArrayClr = new ArrayList();
					// ConcurrentHashMap
					/*
					 * ConcurrentHashMap khasraMap= new ConcurrentHashMap(); //HashMap khasraMap= new HashMap(); HashMap<String,ArrayList<String>> khasraForValMap=null; HashMap<String,HashMap<String,ArrayList<String>>> ValColKhasraMap=new HashMap();
					 */
					// HashMap<String,ArrayList<String,ArrayList<String>>>
					// ValColKhasraMap=new HashMap();
					for (int i = 0; i < dtoForm.getPropertyDetailsList().size(); i++) {
						DutyCalculationDTO dcTemp = new DutyCalculationDTO();
						dcTemp = (DutyCalculationDTO) dtoForm.getPropertyDetailsList().get(i);
						tempD.add(dcTemp.getValuationId());
						String rinPusClr = "";
						/*
						 * String colonyId =""; ArrayList khastListWRTValTxnID=null;
						 */
						// HashMap khasraMap= new HashMap();
						rinPusClr = (String) bo.getRinPustikaNo(dcTemp.getValuationId());
						/*
						 * colonyId=(String)bo.getColonyId(dcTemp.getValuationId( ));
						 * 
						 * 
						 * khastListWRTValTxnID=(ArrayList)bo.getKhasraListClr( dcTemp.getValuationId()); ArrayList khasraList=new ArrayList(); ArrayList khasraList2=new ArrayList(); for (int j = 0; j < khastListWRTValTxnID.size(); j++) {
						 * 
						 * 
						 * ArrayList temp=(ArrayList)khastListWRTValTxnID.get(j); khasraList.add(temp.get(0)); khasraList2.add(temp.get(0)); } khasraForValMap=new HashMap<String, ArrayList<String>>(); // for unique khasra wrt colonyId: Start khasraForValMap.put(colonyId, khasraList2);
						 * 
						 * if(!khasraList2.isEmpty()){ ValColKhasraMap.put(dcTemp.getValuationId(), khasraForValMap); } ValColKhasraMap.put(dcTemp.getValuationId(), khasraForValMap); // for unique khasra wrt colonyId: End
						 * 
						 * for (Object key :khasraMap.keySet()) { if(key.equals(colonyId)){ ArrayList temp=(ArrayList)khasraMap.get(colonyId); khasraList.addAll(temp); khasraMap.put(colonyId, khasraList); } } //khasraMap.put(colonyId, khasraList); if(!khasraList.isEmpty()){ khasraMap.put(colonyId, khasraList); }
						 */
						if (!rinPusClr.equalsIgnoreCase(null) && !rinPusClr.isEmpty()) {
							rinPustikaArrayClr.add(rinPusClr);
						}
					}
					/*
					 * if(dtoForm.getDeedId()==1054){
					 * 
					 * for (int i = 0; i < dtoForm.getExchangeList1().size(); i++) { DutyCalculationDTO dcTemp=new DutyCalculationDTO();
					 * 
					 * 
					 * 
					 * 
					 * 
					 * dcTemp=(DutyCalculationDTO)dtoForm.getExchangeList1().get( i); tempD.add(dcTemp.getValuationId());
					 * 
					 * String rinPusClr =""; String colonyId =""; ArrayList khastListWRTValTxnID=null; //HashMap khasraMap= new HashMap(); rinPusClr=(String)bo.getRinPustikaNo(dcTemp.getValuationId ()); colonyId=(String)bo.getColonyId(dcTemp.getValuationId());
					 * 
					 * 
					 * 
					 * 
					 * 
					 * khastListWRTValTxnID=(ArrayList)bo.getKhasraListClr(dcTemp .getValuationId()); ArrayList khasraList=new ArrayList(); ArrayList khasraList2=new ArrayList(); for (int j = 0; j < khastListWRTValTxnID.size(); j++) {
					 * 
					 * 
					 * ArrayList temp=(ArrayList)khastListWRTValTxnID.get(j); khasraList.add(temp.get(0)); khasraList2.add(temp.get(0)); } khasraForValMap=new HashMap<String, ArrayList<String>>(); // for unique khasra wrt colonyId: Start khasraForValMap.put(colonyId, khasraList2);
					 * 
					 * if(!khasraList2.isEmpty()){ ValColKhasraMap.put(dcTemp.getValuationId(), khasraForValMap); } ValColKhasraMap.put(dcTemp.getValuationId(), khasraForValMap); // for unique khasra wrt colonyId: End
					 * 
					 * for (Object key :khasraMap.keySet()) { if(key.equals(colonyId)){ ArrayList temp=(ArrayList)khasraMap.get(colonyId); khasraList.addAll(temp); khasraMap.put(colonyId, khasraList); } } //khasraMap.put(colonyId, khasraList); if(!khasraList.isEmpty()){ khasraMap.put(colonyId, khasraList); }
					 * 
					 * if(!rinPusClr.equalsIgnoreCase(null)&& !rinPusClr.isEmpty()){ rinPustikaArrayClr.add(rinPusClr); } } for (int i = 0; i < dtoForm.getExchangeList2().size(); i++) { DutyCalculationDTO dcTemp=new DutyCalculationDTO();
					 * 
					 * 
					 * 
					 * 
					 * 
					 * dcTemp=(DutyCalculationDTO)dtoForm.getExchangeList2().get( i); tempD.add(dcTemp.getValuationId());
					 * 
					 * String rinPusClr =""; String colonyId =""; ArrayList khastListWRTValTxnID=null; //HashMap khasraMap= new HashMap(); rinPusClr=(String)bo.getRinPustikaNo(dcTemp.getValuationId ()); colonyId=(String)bo.getColonyId(dcTemp.getValuationId());
					 * 
					 * 
					 * 
					 * 
					 * 
					 * khastListWRTValTxnID=(ArrayList)bo.getKhasraListClr(dcTemp .getValuationId()); ArrayList khasraList=new ArrayList(); ArrayList khasraList2=new ArrayList(); for (int j = 0; j < khastListWRTValTxnID.size(); j++) {
					 * 
					 * 
					 * ArrayList temp=(ArrayList)khastListWRTValTxnID.get(j); khasraList.add(temp.get(0)); khasraList2.add(temp.get(0)); } khasraForValMap=new HashMap<String, ArrayList<String>>(); // for unique khasra wrt colonyId: Start khasraForValMap.put(colonyId, khasraList2);
					 * 
					 * if(!khasraList2.isEmpty()){ ValColKhasraMap.put(dcTemp.getValuationId(), khasraForValMap); } ValColKhasraMap.put(dcTemp.getValuationId(), khasraForValMap); // for unique khasra wrt colonyId: End
					 * 
					 * for (Object key :khasraMap.keySet()) { if(key.equals(colonyId)){ ArrayList temp=(ArrayList)khasraMap.get(colonyId); khasraList.addAll(temp); khasraMap.put(colonyId, khasraList); } } //khasraMap.put(colonyId, khasraList); if(!khasraList.isEmpty()){ khasraMap.put(colonyId, khasraList); }
					 * 
					 * if(!rinPusClr.equalsIgnoreCase(null)&& !rinPusClr.isEmpty()){ rinPustikaArrayClr.add(rinPusClr); } }
					 * 
					 * } // Iterator it1=ValColKhasraMap.keySet().iterator(); Iterator it2; String key1="";; String key2=""; HashMap<String,ArrayList<String>> tempMap1=null; ArrayList tempAL1=null; while (it1.hasNext()) { key1 =(String) it1.next(); tempMap1=ValColKhasraMap.get(key1); it2=ValColKhasraMap.get(key1).keySet().iterator(); while (it2.hasNext()) { key2 =(String) it2.next(); tempAL1=tempMap1.get(key2);
					 * 
					 * }
					 * 
					 * Iterator it3=ValColKhasraMap.keySet().iterator(); Iterator it4; String key3=""; String key4=""; HashMap<String,ArrayList<String>> tempMap2=null; while (it3.hasNext()) { key3 =(String) it3.next(); tempMap2=ValColKhasraMap.get(key3); it4=ValColKhasraMap.get(key3).keySet().iterator(); while (it4.hasNext()) { key4 =(String) it4.next(); ArrayList tempAL2=tempMap2.get(key4); if(key1.equalsIgnoreCase(key3)){
					 * 
					 * }else{ if(key2.equalsIgnoreCase(key4)){ for (int i = 0; i < tempAL2.size(); i++) { if(tempAL1.contains(tempAL2.get(i))){ if("en".equalsIgnoreCase(language)) { request.setAttribute("ERROR", "Khasra number should be unique for a village, duplicate Khasra are not allowed in registration process." ); } else { request.setAttribute("ERROR", "प्रत्येक खसरा क्रमांक एक ग्राम के लिए एक ही होता है अतः खसरे की पुनरावृत्ति पंजीयन प्रिक्रिया में मान्य नहीं है|" );
					 * 
					 * } regFlag=false; forwardJsp = CommonConstant.FORWARD_PAGE_NEXT; dto.setActionName(null); actionName=""; } }
					 * 
					 * 
					 * } } } }
					 * 
					 * } //
					 */
					HashSet uniRinPustika = new HashSet();
					uniRinPustika.addAll(rinPustikaArrayClr);
					if (rinPustikaArrayClr.size() == uniRinPustika.size()) {
					} else {
						if ("en".equalsIgnoreCase(language)) {
							request.setAttribute("ERROR", "RIN pustika number is already part of another property in the ongoing transaction. Please enter new valuation id.");
						} else {
							request.setAttribute("ERROR", "ऋण  पुस्तिका संख्या पहले से चल रही लेनदेन में दूसरी संपत्ति का हिस्सा है। कृपया नया मूल्यांकन आईडी दर्ज करें |");
						}
						regFlag = false;
						forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
						dto.setActionName(null);
						actionName = "";
					}
					session.setAttribute("rinPustikaArrayClr", rinPustikaArrayClr);
					/* session.setAttribute("khasraMapClr", khasraMap); */
					// Added by Rakesh for Unique Rin Pustika No
					double userStampDuty = 0;
					// System.out.println("The exemption value is :"+exedto.
					// getHdnExAmount());
					// System.out.println("The exemption value is for :"+exedto.
					// getHdnExempAmount());
					logger.debug("The exemption NAME is :" + exedto.getHdnExemptions());
					// System.out.println("The exemption value is :"+exedto.
					// getExemType());
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList userList = new ArrayList();
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
								/*if (instdto.getInstId() == 2092 || instdto.getInstId() == 2093 || instdto.getInstId() == 2094 || instdto.getInstId() == 2231) {
									if (null != dtoForm.getAlreadyPaidRegFee()) {
										if (!("").equalsIgnoreCase(dtoForm.getAlreadyPaidRegFee())) {
											if (!value.equalsIgnoreCase(dtoForm.getAlreadyPaidRegFee())) {
												value = dtoForm.getAlreadyPaidRegFee();
											}
										}
									}
								}*/
								dtoForm.setAlreadyPaidRegFee(value);

							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
								dtoForm.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue("");
							userList.add(userDto);
						}
						// dto1.setUserValueMap(userValueMap);
						dtoForm.setUserValueList(userList);
					}
					String[] exemptions = exedto.getHdnExemptions() == null ? null : exedto.getHdnExemptions().split("~");
					// added by akansha
					if (instdto.getInstId() == 2253) {
						userStampDuty = Double.parseDouble(eForm.getInstDTO().getInStampDutyAmount());
						dtoForm.setStampDuty(userStampDuty);
						double stampDuty = userStampDuty;// bo.getDutyCalculation
						// (dtoForm,instdto,
						// exedto);
						dtoForm.setStampDuty(stampDuty);
						double dutyArray[] = new double[3];
						double nagarPalikaDuty = new CalculateDuty().calculateMuncipalDuty(instdto, dtoForm, dtoForm);
						double panchayatDuty = new CalculateDuty().calculateJanPadDuty(instdto, dtoForm, dtoForm);
						double upkarDuty = 0.0;
						dtoForm.setNagarPalikaDuty((nagarPalikaDuty));
						dtoForm.setPanchayatDuty(panchayatDuty);
						upkarDuty = new CalculateDuty().calculateUpkar(eForm.getInstDTO().getInstId(), stampDuty);
						dtoForm.setUpkarDuty(upkarDuty);
						double total = nagarPalikaDuty + panchayatDuty + upkarDuty + stampDuty;
						dtoForm.setTotal(total);
						double regFee = 0;// bo.getRegistrationFee(dtoForm,instdto
						// ,exedto);
						regFee = Double.parseDouble(new CalculateRegFee().calculateRegFee(instdto, dtoForm, dtoForm));
						dtoForm.setRegFee(regFee);
						// session.setAttribute("regFee",regFee);
						logger.debug("Reg Fee:-" + regFee);
						double paidRegFee = 0;
						double paidStampduty = 0;
						if (dtoForm.getAlreadyPaidRegFee() != null && !dtoForm.getAlreadyPaidRegFee().equalsIgnoreCase("")) {
							paidRegFee = Double.parseDouble(dtoForm.getAlreadyPaidRegFee());
						}
						if (dtoForm.getAlreadyPaidStampDuty() != null && !dtoForm.getAlreadyPaidStampDuty().equalsIgnoreCase("")) {
							paidStampduty = Double.parseDouble(dtoForm.getAlreadyPaidStampDuty());
						}
						if (((regFee) - paidRegFee) < 0) {
							dtoForm.setPayableRegFee(0);
						} else {
							dtoForm.setPayableRegFee((regFee) - paidRegFee);
						}
						if (((total) - paidStampduty) < 0) {
							dtoForm.setPayableStampDuty(0);
						} else {
							dtoForm.setPayableStampDuty((total) - paidStampduty);
						}
						double minRegFee = bo.minRegFee(String.valueOf(dtoForm.getDeedId()), String.valueOf(instdto.getInstId()));
						double minStamp = bo.minStampDuty(String.valueOf(dtoForm.getDeedId()), String.valueOf(instdto.getInstId()));
						if (minRegFee > 0) {
							dtoForm.setMinRegFee(String.valueOf(minRegFee));
							dtoForm.setMinRegDisclaimerFlag("Y");
						} else {
							dtoForm.setMinRegFee(String.valueOf(minRegFee));
							dtoForm.setMinRegDisclaimerFlag("N");
						}
						if (minStamp > 0) {
							dtoForm.setMinStamp(String.valueOf(minStamp));
							dtoForm.setMinStampDisclaimerFlag("Y");
						} else {
							dtoForm.setMinStamp(String.valueOf(minStamp));
							dtoForm.setMinStampDisclaimerFlag("N");
						}
						if (dtoForm.getPayableStampDuty() < minStamp) {
							dtoForm.setPayableStampDuty(minStamp);
						}
						if (dtoForm.getPayableRegFee() < minRegFee) {
							dtoForm.setPayableRegFee(minRegFee);
						}
						// property wise exemption code comes here-Roopam- 4
						// April 2015
						int flag = new CalculateDuty().calculatePropWiseExemptedDutiesEWS(instdto, dtoForm);
						if (flag == 1) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’");
							} else {
								request.setAttribute("ERROR", "If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’");
							}
							regFlag = false;
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";
						} else if (flag == 2) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "Please select exemption belonging to either of EWS or MIG or LIG category");
							} else {
								request.setAttribute("ERROR", "Please select exemption belonging to either of EWS or MIG or LIG category");
							}
							regFlag = false;
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";
						} else {
							ArrayList listexempStamp = dtoForm.getExemptionDescpList();
							double d = bo.getExemptionPecentage(dtoForm, instdto);
							double fd = dtoForm.getPayableStampDuty() - ((dtoForm.getPayableStampDuty() * d) / 100);
							dtoForm.setMinusExempStamp(((dtoForm.getPayableStampDuty() * d) / 100));
							double fd1 = dtoForm.getTotalPayableStamp() - ((dtoForm.getTotalPayableStamp() * d) / 100);
							// dtoForm.setMinusExempStampTotal(((dtoForm.
							// getTotalPayableStamp()*d)/100));
							dtoForm.setMinusExempStampTotal((dtoForm.getMinusExempStampTotal()) + ((dtoForm.getTotalPayableStamp() * d) / 100));
							if (dtoForm.getDeedId() == 1092) {// lease
								boolean leaseRegFee1000Exemp = false;
								ArrayList list1 = dtoForm.getRegFeeExemptionDiscriptionList();
								if (list1 != null && list1.size() > 0) {
									for (int i = 0; i < list1.size(); i++) {
										DutyCalculationDTO dto1 = (DutyCalculationDTO) list1.get(i);
										if (!dto1.getExempId().equalsIgnoreCase("130")) {
											leaseRegFee1000Exemp = true;
											break;
										}
									}
								} else {
									leaseRegFee1000Exemp = true;
								}
								if (!leaseRegFee1000Exemp) {
									if (dtoForm.getTotalPayableReg() > 1000) {
										dtoForm.setTotalPayableReg(1000);
										// regFee=1000;
									}
								}
							}
							String exceptionlist = exedto.getExemId();
							System.out.println("exceptionlist cons  " + exceptionlist);
							System.out.println("exemptions cons  " + exemptions);
							double r = bo.getExemptionPecentageRegFee(dtoForm, instdto);
							double rd = dtoForm.getPayableRegFee() - ((dtoForm.getPayableRegFee() * r) / 100);
							dtoForm.setMinusExempReg(((dtoForm.getPayableRegFee() * r) / 100));
							double rd1 = dtoForm.getTotalPayableReg() - ((dtoForm.getTotalPayableReg() * r) / 100);
							dtoForm.setMinusExempRegTotal(((dtoForm.getTotalPayableReg() * r) / 100));
							dtoForm.setDutyafterExemption(fd);
							dtoForm.setDutyafterExemptionTotal(fd1);
							dtoForm.setRegFeeafterExemp(rd);
							dtoForm.setRegFeeafterExempTotal(rd1);
							// System.out.println("hdghjdhgsadhasd");
							String propValReqFlag = bo.getPropValRequiredFlag(instdto.getInstId());
							if (propValReqFlag.equalsIgnoreCase("Y") && !"true".equalsIgnoreCase(dtoForm.getValuationIdValidate())) {
							} else {
								if (dtoForm.getTotalPayableStamp() < minStamp) {
									dtoForm.setTotalPayableStamp(minStamp);
								}
								if (dtoForm.getTotalPayableReg() < minRegFee) {
									dtoForm.setTotalPayableReg(minRegFee);
								}
							}
							if (dtoForm.getDutyafterExemption() < minStamp) {
								dtoForm.setDutyafterExemption(minStamp);
							}
							/*
							 * if(dtoForm.getRegFeeafterExemp()<minRegFee) { dtoForm.setRegFeeafterExemp(minRegFee); }
							 */
							if (dtoForm.getDutyafterExemptionTotal() < minStamp) {
								dtoForm.setDutyafterExemptionTotal(minStamp);
							}
							/*
							 * if(dtoForm.getRegFeeafterExempTotal()<minRegFee) { dtoForm.setRegFeeafterExempTotal(minRegFee); }
							 */
							if (dtoForm.getExemptionDescpList() != null) {
								ArrayList exemp = dtoForm.getExemptionDescpList();
								for (int i = 0; i < exemp.size(); i++) {
									DutyCalculationDTO dto1 = (DutyCalculationDTO) exemp.get(i);
									if (dto1.getExmepApplicable().equalsIgnoreCase("S")) {
										if (dto1.getExempId().equalsIgnoreCase("180")) {
											dtoForm.setDutyafterExemption(0);
											dtoForm.setDutyafterExemptionTotal(0);
										}
									}
								}
							}
						}
					} else {
						double stampDuty = 0;// bo.getDutyCalculation(dtoForm,
						// instdto,exedto);
						stampDuty = Double.parseDouble(new CalculateDuty().calculateStampDuty(instdto, dtoForm, dtoForm));
						double panchayatDuty = new CalculateDuty().calculateJanPadDuty(instdto, dtoForm, dtoForm);
						ArrayList listexempStamp = dtoForm.getExemptionDescpList();
						ArrayList list1 = dtoForm.getRegFeeExemptionDiscriptionList();
						if (list1 != null && list1.size() == 1 && listexempStamp != null && listexempStamp.size() == 1) {
							for (int i = 0; i < list1.size(); i++) {
								for (int j = 0; j < listexempStamp.size(); j++) {
									DutyCalculationDTO dto1 = (DutyCalculationDTO) list1.get(i);
									DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexempStamp.get(j);
									if (dtoForm1.getExempId().equalsIgnoreCase("184") && listexempStamp.size() == 1 && dto1.getExempId().equalsIgnoreCase("69") && list1.size() == 1) {
										stampDuty = 500;
										panchayatDuty = 500;
									}
								}
							}
						}
						for (int i = 0; i < listexempStamp.size(); i++) {
							DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexempStamp.get(i);
							if (dtoForm1.getExmepApplicable().equalsIgnoreCase("S")) {
								if (dtoForm1.getExempId().equalsIgnoreCase("184") && listexempStamp.size() == 1) {
									stampDuty = 500;
									panchayatDuty = 500;
								}
							}
							// added by saurav for transfer rights
							if ((instdto.getInstId() == 2273 || instdto.getInstId() == 2274) && dtoForm1.getExempId().equalsIgnoreCase("182")) {
								stampDuty = 0;
							}
							// end of transfer rights
						}
						dtoForm.setStampDuty(stampDuty);
						/*
						 * if(instdto.getInstId()==2012 && dto.getSelectedExempId().equals("184")){ if(stampDuty!=500){ stampDuty=500; dtoForm.setStampDuty(stampDuty); } }
						 */
						if (stampDuty < 0) {
							stampDuty = 0.0;
							dtoForm.setStampDuty(stampDuty);
						}
						double dutyArray[] = new double[3];
						double nagarPalikaDuty = new CalculateDuty().calculateMuncipalDuty(instdto, dtoForm, dtoForm);
						if (instdto.getInstId() == 2012 || instdto.getInstId() == 2016 || instdto.getInstId() == 2041) {
							if (panchayatDuty > stampDuty) {
								panchayatDuty = stampDuty;
							}
							if (nagarPalikaDuty > stampDuty) {
								nagarPalikaDuty = stampDuty;
							}
						}
						double upkarDuty = 0.0;
						dtoForm.setNagarPalikaDuty((nagarPalikaDuty));
						dtoForm.setPanchayatDuty(panchayatDuty);
						if (instdto.getInstId() == 2254 || instdto.getInstId() == 2261 || instdto.getInstId() == 2262) {
							double miningDuration = dtoForm.getMiningTotalDuration();
							if (miningDuration >= 30) {
								upkarDuty = stampDuty * 10 / 100;
								String totalUpkar = Double.toString(upkarDuty);
								dtoForm.setTotalUpkarDisplay(totalUpkar);
							}
						} else if (instdto.getInstId() == 2220 || instdto.getInstId() == 2219) {
							if (!dtoForm.getPeriodMonth().equalsIgnoreCase("") && !dtoForm.getPeriodMonth().isEmpty()) {
								if (Double.parseDouble(dtoForm.getPeriodMonth()) >= 12) {
									double year = Double.parseDouble(dtoForm.getPeriodYear()) + 1;
									dtoForm.setPeriodYear(Double.toString(year));
								}
							}
							double miningDuration = 0;
							if (!dtoForm.getPeriodYear().equalsIgnoreCase("") && !dtoForm.getPeriodYear().isEmpty()) {
								miningDuration = Double.parseDouble(dtoForm.getPeriodYear()) + dtoForm.getTotalYear();
							}
							if (miningDuration >= 30) {
								upkarDuty = stampDuty * 10 / 100;
								String totalUpkar = Double.toString(upkarDuty);
								dtoForm.setTotalUpkarDisplay(totalUpkar);
							} else {
								upkarDuty = new CalculateDuty().calculateUpkar(eForm.getInstDTO().getInstId(), stampDuty);
							}
						} else {
							upkarDuty = new CalculateDuty().calculateUpkar(eForm.getInstDTO().getInstId(), stampDuty);
						}
						dtoForm.setUpkarDuty(upkarDuty);
						// added by ankit : duty cal on plant and machinery
						// value
						double stampDutyOnMovProp = 0;
						double janpadDutyOnMovProp = 0;
						double municipalDutyOnMovProp = 0;
						double upkarDutyOnMovProp = 0;
						double regFeeOnMovProp = 0;
						double totalDutyOnMovProp = 0;
						double totalRegFeeOnMovProp = 0;
						if (instdto.getInstId() == 2271 || instdto.getInstId() == 2272) {
							logger.info("getValueOfPlantAndMachinery : " + eForm.getInstDTO().getValueOfMovableProp());
							/*
							 * ArrayList<UserEnterableDTO> userList1 = dtoForm.getUserValueList(); if(userList1.size()>0){ for(int i=0;i<userList1.size();i++){ logger.info("valuePlantAndMach 1: " +userList1.get(i).getId()); logger.info("valuePlantAndMach 2: " +userList1.get(i).getName()); logger.info("valuePlantAndMach 3: " +userList1.get(i).getValue()); } }
							 */
							stampDutyOnMovProp = Double.parseDouble(new CalculateDuty().calculateStampDutyOnMovableProp(instdto, dtoForm, CommonConstant.STAMP_MOV_PROP));
							janpadDutyOnMovProp = Double.parseDouble(new CalculateDuty().calculateJanpadDutyOnMovableProp(instdto, dtoForm, CommonConstant.JANPAD_MOV_PROP));
							upkarDutyOnMovProp = new CalculateDuty().calculateUpkarDutyOnMovableProp(instdto, dtoForm, CommonConstant.UPKAR_MOV_PROP, stampDutyOnMovProp);
							// municipalDutyOnMovProp = Double.parseDouble(new
							// CalculateDuty
							// ().calculateMunicipalDutyOnMovableProp(instdto,
							// dtoForm, CommonConstant.MUNICIPAL_MOV_PROP));
							municipalDutyOnMovProp = Double.parseDouble(new CalculateDuty().calculateMunicipalDutyOnMovableProp(instdto, dtoForm, dtoForm, CommonConstant.MUNICIPAL_MOV_PROP));
							regFeeOnMovProp = Double.parseDouble(new CalculateDuty().calculateRegFeeOnMovableProp(instdto, dtoForm, CommonConstant.REG_FEE_MOV_PROP));
							dtoForm.setStampDutyOnMovProp(stampDutyOnMovProp);
							dtoForm.setJanpadDutyOnMovProp(janpadDutyOnMovProp);
							dtoForm.setUpkarDutyOnMovProp(upkarDutyOnMovProp);
							dtoForm.setMunicipalDutyOnMovProp(municipalDutyOnMovProp);
							dtoForm.setRegFeeOnMovProp(regFeeOnMovProp);
							totalDutyOnMovProp = stampDutyOnMovProp + janpadDutyOnMovProp + upkarDutyOnMovProp + municipalDutyOnMovProp;
							totalRegFeeOnMovProp = regFeeOnMovProp;
							// dtoForm.setTotalDutyOnMovProp(totalDutyOnMovProp);
							// dtoForm.setTotalRegFeeOnMovProp(dtoForm.
							// getTotalRegFeeOnMovProp()+totalRegFeeOnMovProp);
							logger.info("stampDutyOnMovProp : " + stampDutyOnMovProp);
							logger.info("janpadDutyOnMovProp : " + janpadDutyOnMovProp);
							logger.info("upkarDutyOnMovProp : " + upkarDutyOnMovProp);
							logger.info("municipalDutyOnMovProp : " + municipalDutyOnMovProp);
							logger.info("regFeeOnMovProp : " + regFeeOnMovProp);
							logger.info("totalDutyOnMovProp : " + totalDutyOnMovProp);
							logger.info("totalRegFeeOnMovProp : " + totalRegFeeOnMovProp);
							// String valuePlantAndMach = new
							// CalculateDuty().calculateDutyOnMovableProp
							// (instdto, dtoForm,CommonConstant.STAMP_MOV_PROP);
							// logger.info("valuePlantAndMach : "+
							// valuePlantAndMach);
						}
						double total = nagarPalikaDuty + panchayatDuty + upkarDuty + stampDuty;
						dtoForm.setTotal(total);
						if (instdto.getInstId() == 2220 || instdto.getInstId() == 2219 || instdto.getInstId() == 2254 || instdto.getInstId() == 2261 || instdto.getInstId() == 2262 || instdto.getInstId() == 2277) {
							dtoForm.setTotalPayableStamp(Math.ceil(total));
						}
						double regFee = 0;// bo.getRegistrationFee(dtoForm,instdto
						// ,exedto);
						regFee = Double.parseDouble(new CalculateRegFee().calculateRegFee(instdto, dtoForm, dtoForm));
						dtoForm.setRegFee(regFee);
						// session.setAttribute("regFee",regFee);
						if (regFee < 0) {
							regFee = 0.0;
							dtoForm.setRegFee(regFee);
						}
						logger.debug("Reg Fee:-" + regFee);
						double paidRegFee = 0;
						double paidStampduty = 0;
						if (dtoForm.getAlreadyPaidRegFee() != null && !dtoForm.getAlreadyPaidRegFee().equalsIgnoreCase("")) {
							paidRegFee = Double.parseDouble(dtoForm.getAlreadyPaidRegFee());
						}
						if (dtoForm.getAlreadyPaidStampDuty() != null && !dtoForm.getAlreadyPaidStampDuty().equalsIgnoreCase("")) {
							paidStampduty = Double.parseDouble(dtoForm.getAlreadyPaidStampDuty());
						}
						if (((regFee) - paidRegFee) < 0) {
							dtoForm.setPayableRegFee(0);
						} else {
							dtoForm.setPayableRegFee((regFee) - paidRegFee);
						}
						if (((total) - paidStampduty) < 0) {
							dtoForm.setPayableStampDuty(0);
						} else {
							dtoForm.setPayableStampDuty(Math.ceil((total) - paidStampduty));
						}
						double minRegFee = bo.minRegFee(String.valueOf(dtoForm.getDeedId()), String.valueOf(instdto.getInstId()));
						double minStamp = bo.minStampDuty(String.valueOf(dtoForm.getDeedId()), String.valueOf(instdto.getInstId()));
						if (minRegFee > 0) {
							dtoForm.setMinRegFee(String.valueOf(minRegFee));
							dtoForm.setMinRegDisclaimerFlag("Y");
						} else {
							dtoForm.setMinRegFee(String.valueOf(minRegFee));
							dtoForm.setMinRegDisclaimerFlag("N");
						}
						if (minStamp > 0) {
							dtoForm.setMinStamp(String.valueOf(minStamp));
							dtoForm.setMinStampDisclaimerFlag("Y");
						} else {
							dtoForm.setMinStamp(String.valueOf(minStamp));
							dtoForm.setMinStampDisclaimerFlag("N");
						}
						if (dtoForm.getPayableStampDuty() < minStamp) {
							dtoForm.setPayableStampDuty(minStamp);
						}
						if (dtoForm.getPayableRegFee() < minRegFee) {
							dtoForm.setPayableRegFee(minRegFee);
						}
						// property wise exemption code comes here-Roopam- 4
						// April 2015
						int flag = new CalculateDuty().calculatePropWiseExemptedDutiesEWS(instdto, dtoForm);
						if (flag == 1) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’");
							} else {
								request.setAttribute("ERROR", "If you want to avail EWS / LIG / MIG exemption, please select checkbox on Property valuation page – ‘Is your property is lease hold property of Housing Board’");
							}
							regFlag = false;
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";
						} else if (flag == 2) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "Please select exemption belonging to either of EWS or MIG or LIG category");
							} else {
								request.setAttribute("ERROR", "Please select exemption belonging to either of EWS or MIG or LIG category");
							}
							regFlag = false;
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";
						} else {
							double fd1 = 0.0;
							/*
							 * String exceptionlist = exedto.getExemId(); System. out.println("exceptionlist  sing "+exceptionlist ); System.out.println("exemptions  sing "+exemptions );
							 */
							double d = bo.getExemptionPecentage(dtoForm, instdto);
							double fd = dtoForm.getPayableStampDuty() - ((dtoForm.getPayableStampDuty() * d) / 100);
							listexempStamp = dtoForm.getExemptionDescpList();
							list1 = dtoForm.getRegFeeExemptionDiscriptionList();
							if (list1 != null && list1.size() == 1 && listexempStamp != null && listexempStamp.size() == 1) {
								for (int i = 0; i < list1.size(); i++) {
									for (int j = 0; j < listexempStamp.size(); j++) {
										DutyCalculationDTO dto1 = (DutyCalculationDTO) list1.get(i);
										DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexempStamp.get(j);
										if (dtoForm1.getExempId().equalsIgnoreCase("184") && listexempStamp.size() == 1 && dto1.getExempId().equalsIgnoreCase("69") && list1.size() == 1) {
											fd = dtoForm.getPayableStampDuty();
										}
									}
								}
							}
							for (int i = 0; i < listexempStamp.size(); i++) {
								DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexempStamp.get(i);
								if (dtoForm1.getExmepApplicable().equalsIgnoreCase("S")) {
									if (dtoForm1.getExempId().equalsIgnoreCase("184") && listexempStamp.size() == 1) {
										fd = dtoForm.getPayableStampDuty();
									}
								}
							}
							dtoForm.setMinusExempStamp(((dtoForm.getPayableStampDuty() * d) / 100));
							if (instdto.getInstId() == 2220 || instdto.getInstId() == 2219) {
								fd1 = Math.ceil(dtoForm.getPayableStampDuty() - ((dtoForm.getPayableStampDuty() * d) / 100));
							} else {
								fd1 = Math.ceil(dtoForm.getTotalPayableStamp() - ((dtoForm.getTotalPayableStamp() * d) / 100));
								// listexempStamp=dtoForm.getExemptionDescpList()
								// ;
								// list1=dtoForm.getRegFeeExemptionDiscriptionList
								// ();
								/*
								 * if(list1!=null && list1.size()==1 && listexempStamp!=null && listexempStamp.size()==1){ for(int i=0;i<list1.size();i++) { for(int j=0;j<listexempStamp.size();j++){ DutyCalculationDTO dto1=(DutyCalculationDTO) list1.get(i); DutyCalculationDTO dtoForm1=(DutyCalculationDTO) listexempStamp.get(j);
								 * 
								 * 
								 * 
								 * 
								 * 
								 * if(dtoForm1.getExempId().equalsIgnoreCase("93" ) && listexempStamp.size()==1 && dto1.getExempId().equalsIgnoreCase("69") && list1.size()==1) { double newStampDuty=dtoForm.getTotalStampDuty()/100; fd1 =newStampDuty+dtoForm.getTotalNagarPalika() +dtoForm .getTotalPanchyat()+dtoForm.getTotalUpkar();
								 * 
								 * } } } }
								 */
								/*
								 * if(listexempStamp.size()==1){ for(int j=0;j<listexempStamp.size();j++){ //DutyCalculationDTO dto1=(DutyCalculationDTO) list1.get(i); DutyCalculationDTO dtoForm1=(DutyCalculationDTO) listexempStamp.get(j); if (dtoForm1.getExempId().equalsIgnoreCase("93") && listexempStamp.size()==1 ) { double newStampDuty=dtoForm.getTotalStampDuty()/100; fd1 =newStampDuty+dtoForm.getTotalNagarPalika() +dtoForm .getTotalPanchyat()+dtoForm.getTotalUpkar(); } } }
								 */
							}
							// dtoForm.setMinusExempStampTotal(((dtoForm.
							// getTotalPayableStamp()*d)/100));
							dtoForm.setMinusExempStampTotal((dtoForm.getMinusExempStampTotal()) + ((dtoForm.getTotalPayableStamp() * d) / 100));
							if (dtoForm.getDeedId() == 1092) {// lease
								boolean leaseRegFee1000Exemp = false;
								list1 = dtoForm.getRegFeeExemptionDiscriptionList();
								if (list1 != null && list1.size() > 0) {
									for (int i = 0; i < list1.size(); i++) {
										DutyCalculationDTO dto1 = (DutyCalculationDTO) list1.get(i);
										if (!dto1.getExempId().equalsIgnoreCase("130")) {
											leaseRegFee1000Exemp = true;
											break;
										}
									}
								} else {
									leaseRegFee1000Exemp = true;
								}
								if (!leaseRegFee1000Exemp) {
									if (dtoForm.getTotalPayableReg() > 1000) {
										dtoForm.setTotalPayableReg(1000);
										// regFee=1000;
									}
								}
							}
							double r = bo.getExemptionPecentageRegFee(dtoForm, instdto);
							double rd = dtoForm.getPayableRegFee() - ((dtoForm.getPayableRegFee() * r) / 100);
							dtoForm.setMinusExempReg(((dtoForm.getPayableRegFee() * r) / 100));
							double rd1 = 0;
							if (instdto.getInstId() == 2092 || instdto.getInstId() == 2093 || instdto.getInstId() == 2094 || instdto.getInstId() == 2231) {
								ArrayList listexemp = dtoForm.getRegFeeExemptionDiscriptionList();
								double alreadyPaidRegFee = dtoForm.getTotalPaidReg();
								//alreadyPaidRegFee=60100;
								rd1 = ((dtoForm.getTotalregFee()) - ((dtoForm.getTotalregFee() * r) / 100));
								rd1 = Math.ceil(rd1 - alreadyPaidRegFee);
								dtoForm.setTotalPayableReg(rd1);
							} else {
								rd1 = Math.ceil(dtoForm.getTotalPayableReg() - ((dtoForm.getTotalPayableReg() * r) / 100));
							}
							dtoForm.setMinusExempRegTotal(((dtoForm.getTotalPayableReg() * r) / 100));
							if (instdto.getInstId() == 2054) {
								ArrayList listexemp = dtoForm.getRegFeeExemptionDiscriptionList();
								for (int i = 0; i < listexemp.size(); i++) {
									DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexemp.get(i);
									if (dtoForm1.getExmepApplicable().equalsIgnoreCase("R")) {
										if (dtoForm1.getExempId().equalsIgnoreCase("81")) {
											if (rd > 100000) {
												rd = 100000;
											}
										}
									}
								}
								listexempStamp = dtoForm.getExemptionDescpList();
								for (int i = 0; i < listexempStamp.size(); i++) {
									DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexempStamp.get(i);
									if (dtoForm1.getExmepApplicable().equalsIgnoreCase("S")) {
										if (dtoForm1.getExempId().equalsIgnoreCase("112")) {
											if (fd > 1000000) {
												fd = 1000000;
											}
										}
									}
								}
							}
							// added by ankit - as per dept requirement
							if (instdto.getInstId() == 2091) {
								// ArrayList listexemp =
								// dtoForm.getRegFeeExemptionDiscriptionList();
								listexempStamp = dtoForm.getExemptionDescpList();
								for (int i = 0; i < listexempStamp.size(); i++) {
									DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexempStamp.get(i);
									if (dtoForm1.getExmepApplicable().equalsIgnoreCase("S")) {
										if (dtoForm1.getExempId().equalsIgnoreCase("102")) {
											if (fd1 > 1000000) {
												dtoForm.setMinusExempStampTotal(fd1 - 1000000);
												fd1 = 1000000;
												// dtoForm.setMinusExempStamp(((
												// dtoForm.getPayableStampDuty()
												// * d) / 100));
											}
										}
									}
								}
							}
							// added by saurav - as per dept requirement -- for the female rebate -- when reg fee already paid have some value
							/*
							 * if (instdto.getInstId() == 2092 || instdto.getInstId() == 2093 || instdto.getInstId() == 2094 || instdto.getInstId() == 2231) {
							 * 
							 * ArrayList listexemp = dtoForm.getRegFeeExemptionDiscriptionList(); for (int i = 0; i < listexemp.size(); i++) { DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexemp.get(i); if (dtoForm1.getExmepApplicable().equalsIgnoreCase("R")) { if (dtoForm1.getExempId().equalsIgnoreCase("188") || dtoForm1.getExempId().equalsIgnoreCase("189")) { dtoForm.setPayableRegFee(dtoForm.getPayableRegFee() + paidRegFee); rd = dtoForm.getPayableRegFee() -
							 * ((dtoForm.getPayableRegFee() r) / 100); rd1 = Math.ceil(dtoForm.getTotalPayableReg() - ((dtoForm.getTotalPayableReg() r) / 100)); if (((dtoForm.getPayableRegFee()) - paidRegFee) < 0) { dtoForm.setPayableRegFee(0); } else { dtoForm.setPayableRegFee((dtoForm.getPayableRegFee()) - paidRegFee); }
							 * 
							 * if (dtoForm.getPayableRegFee() < minRegFee) { dtoForm.setPayableRegFee(minRegFee); } } } } }
							 */
							// end
							dtoForm.setDutyafterExemption(fd);
							dtoForm.setDutyafterExemptionTotal(fd1);
							dtoForm.setRegFeeafterExemp(rd);
							dtoForm.setRegFeeafterExempTotal(rd1);
							String propValReqFlag = bo.getPropValRequiredFlag(instdto.getInstId());
							if (propValReqFlag.equalsIgnoreCase("Y") && !"true".equalsIgnoreCase(dtoForm.getValuationIdValidate())) {
							} else {
								if (dtoForm.getTotalPayableStamp() < minStamp) {
									dtoForm.setTotalPayableStamp(minStamp);
								}
								if (dtoForm.getTotalPayableReg() < minRegFee) {
									dtoForm.setTotalPayableReg(minRegFee);
								}
							}
							if (dtoForm.getDutyafterExemption() < minStamp) {
								dtoForm.setDutyafterExemption(minStamp);
							}
							if (2058 != instdto.getInstId()) {
								if (dtoForm.getRegFeeafterExemp() < minRegFee) {
									dtoForm.setRegFeeafterExemp(minRegFee);
								}
							}
							if (dtoForm.getDutyafterExemptionTotal() < minStamp) {
								dtoForm.setDutyafterExemptionTotal(minStamp);
							}
							if (2058 != instdto.getInstId()) {
								if (dtoForm.getRegFeeafterExempTotal() < minRegFee) {
									dtoForm.setRegFeeafterExempTotal(minRegFee);
								}
							}
							if (dtoForm.getRegFeeExemptionDiscriptionList() != null) {
								ArrayList exemp = dtoForm.getRegFeeExemptionDiscriptionList();
								for (int i = 0; i < exemp.size(); i++) {
									DutyCalculationDTO dto1 = (DutyCalculationDTO) exemp.get(i);
									if (dto1.getExmepApplicable().equalsIgnoreCase("R")) {
										if (dto1.getExempId().equalsIgnoreCase("69")) {
											dtoForm.setRegFeeafterExemp(0);
											dtoForm.setRegFeeafterExempTotal(0);
										}
										if (dto1.getExempId().equalsIgnoreCase("137")) {
											dtoForm.setRegFeeafterExemp(0);
											dtoForm.setRegFeeafterExempTotal(0);
										}
										// added for adding exemption to few instruments
										if (dto1.getExempId().equalsIgnoreCase("186")) {
											dtoForm.setRegFeeafterExemp(0);
											dtoForm.setRegFeeafterExempTotal(0);
										}
									}
								}
							}
							if (dtoForm.getExemptionDescpList() != null) {
								ArrayList exemp = dtoForm.getExemptionDescpList();
								for (int i = 0; i < exemp.size(); i++) {
									DutyCalculationDTO dto1 = (DutyCalculationDTO) exemp.get(i);
									if (dto1.getExmepApplicable().equalsIgnoreCase("S")) {
										if (dto1.getExempId().equalsIgnoreCase("136") || dto1.getExempId().equalsIgnoreCase("180")) {
											dtoForm.setDutyafterExemption(0);
											dtoForm.setDutyafterExemptionTotal(0);
										}
										if (dto1.getExempId().equalsIgnoreCase("187")) {
											dtoForm.setDutyafterExemption(0);
											dtoForm.setDutyafterExemptionTotal(0);
										}
										// added by saurav for transfer rights
										if ((instdto.getInstId() == 2273 || instdto.getInstId() == 2274) && dto1.getExempId().equalsIgnoreCase("182")) {
											dtoForm.setDutyafterExemption(0);
											dtoForm.setDutyafterExemptionTotal(0);
										}
									}
								}
							}
						}
					}
				}
			}
			if ("calcRent".equalsIgnoreCase(actionName) || "calcRoyalty".equalsIgnoreCase(actionName)) {
				ArrayList userList = new ArrayList();
				String param[] = request.getParameterValues("userEnterableFieldValue");
				DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
				ArrayList list = eForm.getInstDTO().getUserEnterableList();
				if (list != null) {
					int j = 0;
					for (int i = 0; i < list.size(); i++) {
						InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
						UserEnterableDTO userDto = new UserEnterableDTO();
						String id = indto.getUserEnterableId();
						String value = "";
						if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getCommonUserField();
						} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getInStampDutyAmount();
						} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getAsConsiderationAmount();
						} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
							value = eForm.getInstDTO().getValueOfMovableProp();
						}// added by ankit for plant and machinery
						else {
							value = param[j];
							j++;
						}
						if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
							dto1.setAlreadyPaidRegFee(value);
						}
						if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
							dto1.setAlreadyPaidStampDuty(value);
						}
						userDto.setId(id);
						userDto.setValue(value);
						userDto.setName(indto.getUserEnterableName());
						indto.setUserEnterableFieldValue(value);
						userList.add(userDto);
					}
					// dto1.setUserValueMap(userValueMap);
					dto1.setOpenPopUpRent("Y");
					dto1.setUserValueList(userList);
					dto1.setActionName("");
				}
				eForm.setDutyCalculationDTO(dto1);
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
			}
			if ("calcPremium".equalsIgnoreCase(actionName) || "calcPremiumMining".equalsIgnoreCase(actionName)) {
				ArrayList userList = new ArrayList();
				String param[] = request.getParameterValues("userEnterableFieldValue");
				DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
				ArrayList list = eForm.getInstDTO().getUserEnterableList();
				if (list != null) {
					int j = 0;
					for (int i = 0; i < list.size(); i++) {
						InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
						UserEnterableDTO userDto = new UserEnterableDTO();
						String id = indto.getUserEnterableId();
						String value = "";
						if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getCommonUserField();
						} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getInStampDutyAmount();
						} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getAsConsiderationAmount();
						} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
							value = eForm.getInstDTO().getValueOfMovableProp();
						}// added by ankit for plant and machinery
						else {
							value = param[j];
							j++;
						}
						if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
							dto1.setAlreadyPaidRegFee(value);
						}
						if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
							dto1.setAlreadyPaidStampDuty(value);
						}
						userDto.setId(id);
						userDto.setValue(value);
						userDto.setName(indto.getUserEnterableName());
						indto.setUserEnterableFieldValue(value);
						userList.add(userDto);
					}
					// dto1.setUserValueMap(userValueMap);
					dto1.setOpenPopUpPremium("Y");
					dto1.setUserValueList(userList);
					dto1.setActionName(null);
				}
				eForm.setDutyCalculationDTO(dto1);
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
			}
			if (CommonConstant.FORWARD_PREMIUM_PAGE_ACTION.equalsIgnoreCase(rentRequest)) {
				InstrumentsDTO instdto = eForm.getInstDTO();
				if (instdto.getInstId() == 2254 || instdto.getInstId() == 2261 || instdto.getInstId() == 2262 || instdto.getInstId() == 2277) {
					dto.setPremium("");
					// dto.setDevelopmentCharges("");
					dto.setAdditionalPremium("");
					dto.setOtherCharges("");
					forwardJsp = "calculatePremiumMining";
				} else {
					dto.setPremium("");
					dto.setDevelopmentCharges("");
					dto.setAdditionalPremium("");
					dto.setOtherCharges("");
					forwardJsp = CommonConstant.FORWARD_PREMIUM_PAGE;
				}
			}
			if (CommonConstant.FORWARD_RENT_PAGE_ACTION.equalsIgnoreCase(rentRequest)) {
				InstrumentsDTO instdto = eForm.getInstDTO();
				if (instdto.getInstId() == 2254 || instdto.getInstId() == 2261 || instdto.getInstId() == 2262 || instdto.getInstId() == 2277) {
					dto.setRentArrayList(new ArrayList());
					dto.setTotalYear(0);
					dto.setTotalMonth(0);
					dto.setAnnualRoyalty("");
					dto.setExcavatedQuantity("");
					dto.setMiningYears("");
					dto.setMiningMonths("");
					forwardJsp = "calculateRoyalty";
				} else {
					dto.setRentArrayList(new ArrayList());
					dto.setTotalYear(0);
					dto.setTotalMonth(0);
					dto.setMothlyRent("");
					dto.setPeriodMonth("");
					dto.setPeriodYear("");
					dto.setMaintence("");
					forwardJsp = CommonConstant.FORWARD_RENT_PAGE;
				}
			}
			if (CommonConstant.CALCULATE_PREMIUM.equalsIgnoreCase((actionName))) {
				InstrumentsDTO instdto = eForm.getInstDTO();
				if (instdto.getInstId() == 2254 || instdto.getInstId() == 2261 || instdto.getInstId() == 2262 || instdto.getInstId() == 2277) {
					if (dto.getPremium() == null || dto.getPremium().equalsIgnoreCase("")) {
						dto.setPremium("0");
					}
					if (dto.getAdditionalPremium() == null || dto.getAdditionalPremium().equalsIgnoreCase("")) {
						dto.setAdditionalPremium("0");
					}
					if (dto.getOtherCharges() == null || dto.getOtherCharges().equalsIgnoreCase("")) {
						dto.setOtherCharges("0");
					}
					double premium = Double.parseDouble(dto.getPremium());
					double addtionalPremium = Double.parseDouble(dto.getAdditionalPremium());
					double other = Double.parseDouble(dto.getOtherCharges());
					String abc = BigDecimal.valueOf(premium + addtionalPremium + other).toPlainString();
					eForm.getInstDTO().setAsConsiderationAmount(abc);
					eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
					eForm.getDutyCalculationDTO().setOpenPopUpRent("");
					dto.setActionName(null);
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				} else {
					if (dto.getPremium() == null || dto.getPremium().equalsIgnoreCase("")) {
						dto.setPremium("0");
					}
					if (dto.getAdditionalPremium() == null || dto.getAdditionalPremium().equalsIgnoreCase("")) {
						dto.setAdditionalPremium("0");
					}
					if (dto.getDevelopmentCharges() == null || dto.getDevelopmentCharges().equalsIgnoreCase("")) {
						dto.setDevelopmentCharges("0");
					}
					if (dto.getOtherCharges() == null || dto.getOtherCharges().equalsIgnoreCase("")) {
						dto.setOtherCharges("0");
					}
					double premium = Double.parseDouble(dto.getPremium());
					double addtionalPremium = Double.parseDouble(dto.getAdditionalPremium());
					double devcharges = Double.parseDouble(dto.getDevelopmentCharges());
					double other = Double.parseDouble(dto.getOtherCharges());
					eForm.getInstDTO().setAsConsiderationAmount(String.valueOf(premium + addtionalPremium + devcharges + other));
					eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
					eForm.getDutyCalculationDTO().setOpenPopUpRent("");
					dto.setActionName(null);
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
			}
			if (CommonConstant.ADD_SLAB.equalsIgnoreCase((actionName))) {
				DutyCalculationDTO ddto = new DutyCalculationDTO();
				InstrumentsDTO idto = eForm.getInstDTO();
				int instId = idto.getInstId();
				String leaseId = idto.getLeaseId();
				int lid = 0;
				if (leaseId != null && !leaseId.equalsIgnoreCase("")) {
					lid = Integer.parseInt(leaseId);
				}
				if (instId == 2254 || instId == 2261 || instId == 2262 || instId == 2277) {
					ddto.setAnnualRoyalty(dto.getAnnualRoyalty());
					ddto.setMiningYears(dto.getMiningYears());
					ddto.setMiningMonths(dto.getMiningMonths());
					ddto.setExcavatedQuantity(dto.getExcavatedQuantity());
					double year = dto.getTotalYear();
					double month = dto.getTotalMonth();
					year = year + Double.parseDouble(dto.getMiningYears());
					month = month + Double.parseDouble(dto.getMiningMonths());
					if (month >= 12) {
						year = year + 1;
						month = month - 12;
					}
					dto.getRentArrayList().add(ddto);
					dto.setAnnualRoyalty("");
					dto.setTotalYear(year);
					dto.setTotalMonth(month);
					dto.setMiningMonths("");
					dto.setMiningYears("");
					dto.setExcavatedQuantity("");
					dto.setActionName(null);
					forwardJsp = "calculateRoyalty";
				} else {
					ddto.setMothlyRent(dto.getMothlyRent());
					ddto.setPeriodMonth(dto.getPeriodMonth());
					ddto.setPeriodYear(dto.getPeriodYear());
					ddto.setMaintence(dto.getMaintence());
					double year = dto.getTotalYear();
					double month = dto.getTotalMonth();
					year = year + Double.parseDouble(dto.getPeriodYear());
					month = month + Double.parseDouble(dto.getPeriodMonth());
					if (month >= 12) {
						year = year + 1;
						month = month - 12;
					}
					if (instId == 2215 || lid == 2215) {
						if (year >= 5) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "Period cannot be greater than 5 years");
							} else {
								request.setAttribute("ERROR", "अवधि 5 साल से अधिक नहीं हो सकती|");
							}
						} else {
							dto.getRentArrayList().add(ddto);
							dto.setTotalYear(year);
							dto.setTotalMonth(month);
							dto.setMothlyRent("");
							dto.setPeriodMonth("");
							dto.setPeriodYear("");
							dto.setMaintence("");
						}
					} else if (instId == 2216 || lid == 2216) {
						if (year >= 10) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "Period cannot be greater than 10 years");
							} else {
								request.setAttribute("ERROR", "अवधि 10 साल से अधिक नहीं हो सकती|");
							}
						} else {
							dto.getRentArrayList().add(ddto);
							dto.setTotalYear(year);
							dto.setTotalMonth(month);
							dto.setMothlyRent("");
							dto.setPeriodMonth("");
							dto.setPeriodYear("");
							dto.setMaintence("");
						}
					} else if (instId == 2223 || lid == 2223) {
						if (year >= 20) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "Period cannot be greater than 20 years");
							} else {
								request.setAttribute("ERROR", "अवधि 20 साल से अधिक नहीं हो सकती|");
							}
						} else {
							dto.getRentArrayList().add(ddto);
							dto.setTotalYear(year);
							dto.setTotalMonth(month);
							dto.setMothlyRent("");
							dto.setPeriodMonth("");
							dto.setPeriodYear("");
							dto.setMaintence("");
						}
					} else if (instId == 2217 || instId == 2221 || instId == 2210 || lid == 2217) {
						if (year >= 30) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "Period can't be greater than  or equal to 30 years");
							} else {
								request.setAttribute("ERROR", "अवधि 30 साल के बराबर  या उससे अधिक नहीं हो सकती|");
							}
						} else {
							dto.getRentArrayList().add(ddto);
							dto.setMothlyRent("");
							dto.setTotalYear(year);
							dto.setTotalMonth(month);
							dto.setPeriodMonth("");
							dto.setPeriodYear("");
							dto.setMaintence("");
						}
					} else {
						dto.getRentArrayList().add(ddto);
						dto.setMothlyRent("");
						dto.setTotalYear(year);
						dto.setTotalMonth(month);
						dto.setPeriodMonth("");
						dto.setPeriodYear("");
						dto.setMaintence("");
					}
					dto.setActionName(null);
					forwardJsp = CommonConstant.FORWARD_RENT_PAGE;
				}
			}
			if (CommonConstant.CALCULATE_RENT.equalsIgnoreCase((actionName))) {
				InstrumentsDTO idto = eForm.getInstDTO();
				double rent = 0;
				if (dto.getMothlyRent() == null || dto.getMothlyRent().equalsIgnoreCase("")) {
					dto.setMothlyRent("0");
				}
				if (dto.getMaintence() == null || dto.getMaintence().equalsIgnoreCase("")) {
					dto.setMaintence("0");
				}
				if (dto.getPeriodYear() == null || dto.getPeriodYear().equalsIgnoreCase("")) {
					dto.setPeriodYear("0");
				}
				if (dto.getPeriodMonth() == null || dto.getPeriodMonth().equalsIgnoreCase("")) {
					dto.setPeriodMonth("0");
				}
				double monthlyRent = Double.parseDouble(dto.getMothlyRent());
				double maintenece = Double.parseDouble(dto.getMaintence());
				double year = Double.parseDouble(dto.getPeriodYear());
				double month = Double.parseDouble(dto.getPeriodMonth());
				double period = year + (month / 12);
				double totalPeriod = period;
				rent = (period * (monthlyRent + maintenece));
				ArrayList list = dto.getRentArrayList();
				for (int i = 0; i < list.size(); i++) {
					DutyCalculationDTO ddto = (DutyCalculationDTO) list.get(i);
					monthlyRent = Double.parseDouble(ddto.getMothlyRent());
					maintenece = Double.parseDouble(ddto.getMaintence());
					year = Double.parseDouble(ddto.getPeriodYear());
					month = Double.parseDouble(ddto.getPeriodMonth());
					period = year + (month / 12);
					totalPeriod = period + totalPeriod;
					rent = rent + (period * (monthlyRent + maintenece));
				}
				DutyCalculationDTO ddto = new DutyCalculationDTO();
				InstrumentsDTO idto1 = eForm.getInstDTO();
				int instId = idto1.getInstId();
				ddto.setMothlyRent(dto.getMothlyRent());
				ddto.setPeriodMonth(dto.getPeriodMonth());
				ddto.setPeriodYear(dto.getPeriodYear());
				ddto.setMaintence(dto.getMaintence());
				dto.getRentArrayList().add(ddto);
				rent = Math.ceil((rent / totalPeriod) * 12);
				eForm.getInstDTO().setInStampDutyAmount(String.valueOf(rent));
				eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
				eForm.getDutyCalculationDTO().setOpenPopUpRent("");
				dto.setActionName(null);
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
			}
			if (CommonConstant.CALCULATE_Royalty.equalsIgnoreCase((actionName))) {
				InstrumentsDTO idto = eForm.getInstDTO();
				double royalty = 0;
				if (dto.getAnnualRoyalty() == null || dto.getAnnualRoyalty().equalsIgnoreCase("")) {
					dto.setAnnualRoyalty("0");
				}
				if (dto.getExcavatedQuantity() == null || dto.getExcavatedQuantity().equalsIgnoreCase("")) {
					dto.setExcavatedQuantity("0");
				}
				if (dto.getMiningYears() == null || dto.getMiningYears().equalsIgnoreCase("")) {
					dto.setMiningYears("0");
				}
				if (dto.getMiningMonths() == null || dto.getMiningMonths().equalsIgnoreCase("")) {
					dto.setMiningMonths("0");
				}
				double royalty1 = Double.parseDouble(dto.getAnnualRoyalty());
				double quantity = Double.parseDouble(dto.getExcavatedQuantity());
				double year = Double.parseDouble(dto.getMiningYears());
				double month = Double.parseDouble(dto.getMiningMonths());
				double period = year + (month / 12);
				double totalPeriod = period;
				royalty = (period * royalty1 * quantity);
				ArrayList list = dto.getRentArrayList();
				for (int i = 0; i < list.size(); i++) {
					DutyCalculationDTO ddto = (DutyCalculationDTO) list.get(i);
					royalty1 = Double.parseDouble(ddto.getAnnualRoyalty());
					double quantity1 = Double.parseDouble(ddto.getExcavatedQuantity());
					double year1 = Double.parseDouble(ddto.getMiningYears());
					month = Double.parseDouble(ddto.getMiningMonths());
					double period1 = year1 + (month / 12);
					totalPeriod = period1 + totalPeriod;
					royalty = royalty + (period1 * royalty1 * quantity1);
				}
				dto.setMiningTotalDuration(totalPeriod);
				DutyCalculationDTO ddto = new DutyCalculationDTO();
				InstrumentsDTO idto1 = eForm.getInstDTO();
				int instId = idto1.getInstId();
				ddto.setAnnualRoyalty(dto.getAnnualRoyalty());
				ddto.setMiningMonths(dto.getMiningMonths());
				ddto.setMiningYears(dto.getMiningYears());
				ddto.setExcavatedQuantity(dto.getExcavatedQuantity());
				dto.getRentArrayList().add(ddto);
				royalty = Math.ceil(royalty);
				String royaltyCalc = BigDecimal.valueOf(royalty).toPlainString();
				eForm.getInstDTO().setInStampDutyAmount(royaltyCalc);
				eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
				eForm.getDutyCalculationDTO().setOpenPopUpRent("");
				dto.setActionName(null);
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
			}
			if ("cancellationCategoryChange".equalsIgnoreCase(actionName)) {
				DutyCalculationDTO dtoId = eForm.getDutyCalculationDTO();
				dtoId.setCancellationFlag("N");
				dtoId.setDeedCategoryName(dtoId.getCancellationCategoryName());
				dtoId.setDeedCategoryId(dtoId.getCancellationCategoryId());
				dtoId.setDeedId(0);
				dtoId.setDeedType("");
				InstrumentsDTO instdto = eForm.getInstDTO();
				instdto.setUserEnterableList(new ArrayList());
				dtoId.setExemptionList(new ArrayList());
				dtoId.setExemptionDescpList(new ArrayList());
				dtoId.setRegFeeExemptionList(new ArrayList());
				dtoId.setRegFeeExemptionDiscriptionList(new ArrayList());
				eForm.setDutycalculationDTOList(bo.getDeed(dtoId.getCancellationCategoryId(), dtoId.getCancellationFlag(), language, dtoId.getFromModule()));
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(dtoId);
			}
			if ("cancellationDeedchange".equalsIgnoreCase(actionName)) {
				DutyCalculationDTO dtoId = eForm.getDutyCalculationDTO();
				InstrumentsDTO instdto = eForm.getInstDTO();
				instdto.setUserEnterableList(new ArrayList());
				dtoId.setCancellationFlag("N");
				eForm.setDutycalculationDTOList(bo.getDeed(dtoId.getCancellationCategoryId(), dtoId.getCancellationFlag(), language, dtoId.getFromModule()));
				dtoId.setDeedType(dtoId.getCancellationDeedName());
				dtoId.setDeedCategoryName(dtoId.getCancellationCategoryName());
				dtoId.setDeedCategoryId(dtoId.getCancellationCategoryId());
				dtoId.setDeedId(Integer.parseInt(dtoId.getCancellationDeedId()));
				dtoId.setExemptionList(new ArrayList());
				dtoId.setExemptionDescpList(new ArrayList());
				dtoId.setRegFeeExemptionList(new ArrayList());
				dtoId.setRegFeeExemptionDiscriptionList(new ArrayList());
				ArrayList instList = bo.getInstrument(dtoId.getDeedId(), "N", locale, dtoId.getFromModule());
				eForm.setInstrumentDTOList(instList);
				eForm.setDutyCalculationDTO(dtoId);
			}
			if ("availexemptionAction".equalsIgnoreCase(actionName)) {
				DutyCalculationDTO dtoId = eForm.getDutyCalculationDTO();
				ArrayList userList = new ArrayList();
				String param[] = request.getParameterValues("userEnterableFieldValue");
				ArrayList list = eForm.getInstDTO().getUserEnterableList();
				if (list != null) {
					int j = 0;
					for (int i = 0; i < list.size(); i++) {
						InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
						UserEnterableDTO userDto = new UserEnterableDTO();
						String id = indto.getUserEnterableId();
						String value = "";
						if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getCommonUserField();
						} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getInStampDutyAmount();
						} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
							value = eForm.getInstDTO().getAsConsiderationAmount();
						} else {
							value = param != null ? param[j] : "";// null
							// handeling
							// done by
							// roopam
							j++;
						}
						if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
							dtoId.setAlreadyPaidRegFee(value);
						}
						if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
							dtoId.setAlreadyPaidStampDuty(value);
						}
						userDto.setId(id);
						userDto.setValue(value);
						userDto.setName(indto.getUserEnterableName());
						indto.setUserEnterableFieldValue(value);
						userList.add(userDto);
					}
					// dto1.setUserValueMap(userValueMap);
					dtoId.setUserValueList(userList);
				}
				if ("Y".equalsIgnoreCase(dtoId.getAvailExemptionFlag())) {
					dtoId.setAvailExemption("Y");
					dtoId.setExemptionVisible("Y");
					if (dtoId.getCancellationDeedId() != null && !dtoId.getCancellationDeedId().isEmpty() && dtoId.getCancellationInstrumentId() != null && !dtoId.getCancellationInstrumentId().isEmpty()) {
						int deedId = Integer.parseInt(dtoId.getCancellationDeedId());
						int instId = Integer.parseInt(dtoId.getCancellationInstrumentId());
						ArrayList exemDTOList = bo.getExemptions(deedId, instId, language, dto.isFromEstamp());
						dtoId.setExemptionList(exemDTOList);
						ArrayList regFeeExempList = bo.getRegFeeExemptions(deedId, instId, language, dto.isFromEstamp());
						dtoId.setRegFeeExemptionList(regFeeExempList);
						// if
						// (dtoId.getFromModule().equalsIgnoreCase("estamping")
						if (dtoId.getCancellationDeedId().equalsIgnoreCase("1014") && dtoId.getDeedId() != 1092 && dtoId.getDeedId() != 0) {
							ArrayList regList = dtoId.getRegFeeExemptionList();
							ArrayList stList = dtoId.getExemptionList();
							for (int i = 0; i < regList.size(); i++) {
								DutyCalculationDTO dto1 = (DutyCalculationDTO) regList.get(i);
								if (dto1.getExmepApplicable().equalsIgnoreCase("R")) {
									if (dto1.getExempId().equalsIgnoreCase("137")) {
										regList.remove(dto1);
									}
								}
							}
							for (int i = 0; i < stList.size(); i++) {
								DutyCalculationDTO dto1 = (DutyCalculationDTO) stList.get(i);
								if (dto1.getExmepApplicable().equalsIgnoreCase("S")) {
									if (dto1.getExempId().equalsIgnoreCase("136")) {
										stList.remove(dto1);
									}
								}
							}
						}
					}
				} else {
					dtoId.setAvailExemption("N");
					dtoId.setExemptionVisible("N");
					dtoId.setExemptionDescpList(new ArrayList());
					dtoId.setRegFeeExemptionDiscriptionList(new ArrayList());
					ArrayList regList = dtoId.getRegFeeExemptionList();
					ArrayList stList = dtoId.getExemptionList();
					for (int i = 0; i < regList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) regList.get(i);
						dto2.setExempCheckBox("");
					}
					for (int i = 0; i < stList.size(); i++) {
						DutyCalculationDTO dto2 = (DutyCalculationDTO) stList.get(i);
						dto2.setExempCheckBox("");
					}
				}
				eForm.setDutyCalculationDTO(dtoId);
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
			}
			if ("leaseIdChange".equalsIgnoreCase(actionName)) {
				InstrumentsDTO instdto = eForm.getInstDTO();
				eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
				eForm.getDutyCalculationDTO().setOpenPopUpRent("");
				DutyCalculationDTO dtoId = eForm.getDutyCalculationDTO();
				logger.debug("Inside radio butto");
				int deedId = dtoId.getDeedId();
				int instId = instdto.getInstId();
				ArrayList enetrableList1 = bo.getUserEnterableField(String.valueOf(deedId), String.valueOf(instId), language);
				ArrayList enetrableList = bo.getUserEnterableField(String.valueOf(deedId), String.valueOf(instdto.getLeaseId()), language);
				instdto.setUserEnterableList(new ArrayList());
				instdto.setUserEnterableList(enetrableList1);
				instdto.getUserEnterableList().addAll(enetrableList);
				dtoId.setActionName(null);
				eForm.setDutyCalculationDTO(dtoId);
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
			}
			if (CommonConstant.DUTY_RADIO_ACTION.equals(actionName)) {
				InstrumentsDTO instdto = eForm.getInstDTO();
				eForm.getDutyCalculationDTO().setOpenPopUpPremium("");
				eForm.getDutyCalculationDTO().setOpenPopUpRent("");
				DutyCalculationDTO dtoId = eForm.getDutyCalculationDTO();
				instdto.setInStampDutyAmount("");
				instdto.setAsConsiderationAmount("");
				logger.debug("Inside radio butto");
				dto.setMineralList(new ArrayList());
				int deedId = dtoId.getDeedId();
				int instId = instdto.getInstId();
				instdto.setLeaseFreeze("N");
				RegCommonBO commanBo = new RegCommonBO();
				String instClrFlag = "";
				
				instClrFlag = commanBo.getInstrumentClrFlag(String.valueOf(instId));
				
				if (instClrFlag != null && !instClrFlag.isEmpty() && !instClrFlag.equalsIgnoreCase("")){
					
					if(instClrFlag.equalsIgnoreCase("Y")){
						
						dto.setInstClrFlag(instClrFlag);
						
					}
					
					else{
						dto.setInstClrFlag("N");
						
					}
					
				}
				
				else{
					
					dto.setInstClrFlag("N");
				}
				
				
				if (instId == 2254 || instId == 2261 || instId == 2262 || instId == 2277) {
					String mineralSelected = eForm.getDutyCalculationDTO().getMineralSelected();
					ArrayList selectedMineralList = bo.getSelectedMineralList(language, mineralSelected);
					dtoId.setMineralList(selectedMineralList);
				} else {
					eForm.setMineralSelected("");
				}
				ArrayList enetrableList = new ArrayList();
				if (moduleNameE.equals("E-Stamps") && functionNameE.equals("Non-Judicial")) {
					enetrableList = bo.getUserEnterableFieldEstamp(String.valueOf(deedId), String.valueOf(instId), language);
				} else {
					enetrableList = bo.getUserEnterableField(String.valueOf(deedId), String.valueOf(instId), language);
				}
				if (enetrableList == null || enetrableList.size() == 0) {
					instdto.setUserEnterableFieldReq("N");
				} else {
					if (instId == 2179)// added for developer agreement for the
					// by pass of valuation id check
					{
						dtoId.setHvShare("N");
						instdto.setUserEnterableFieldReq("N");
						instdto.setUserEnterableList(null);
						dto.setHvValIdFlag("N");
						dto.setValuationIdValidate("false");
						dto.setValuationId("");
						dto.setValuationIdList(new ArrayList());
						dto.setMarketValue("");
						dto.setPropertyName("");
						dto.setHvValId("N");
					} 
					
					else if (dtoId.getInstClrFlag().equalsIgnoreCase("Y")){
						
						dto.setHvValIdFlag("N");
						dto.setValuationIdValidate("false");
						dto.setValuationId("");
						dto.setValuationIdList(new ArrayList());
						dto.setMarketValue("");
						dto.setPropertyName("");
						dto.setHvValId("N");
						instdto.setUserEnterableFieldReq("Y");
						instdto.setUserEnterableList(enetrableList);
					}
					
					else {
						instdto.setUserEnterableFieldReq("Y");
						instdto.setUserEnterableList(enetrableList);
					}
				}
				if (dtoId.getInstClrFlag().equalsIgnoreCase("Y")){
					
					dto.setHvValIdFlag("N");
					dto.setValuationIdValidate("false");
					dto.setValuationId("");
					dto.setValuationIdList(new ArrayList());
					dto.setMarketValue("");
					dto.setPropertyName("");
					dto.setHvValId("N");
					
					if("N".equalsIgnoreCase(instdto.getUserEnterableFieldReq())){
						instdto.setUserEnterableFieldReq("N");
					}else{
						instdto.setUserEnterableFieldReq("Y");
						instdto.setUserEnterableList(enetrableList);
					}
					
				}
				String propValRequired = bo.getPropValRequiredFlag(instId);
				String municipal = bo.getmuniciplaFlag(instId);
				if ("Y".equalsIgnoreCase(municipal)) {
					dtoId.setMunicipalVisible("Y");
				} else {
					dtoId.setMunicipalVisible("N");
				}
				dtoId.setRinPustikaVisible("N");
				dtoId.setRinPustikaVisible1("N");
				dtoId.setRinPustikaVisible2("N");
				dtoId.setEntireTotal(0);
				dtoId.setTotalStampDuty(0);
				dtoId.setTotalregFee(0);
				dtoId.setGovCheck("");
				dtoId.setGovValue("");
				dtoId.setGovCheckFlag("");
				dtoId.setTotalUpkar(0);
				dtoId.setTotalNagarPalika(0);
				dtoId.setTotalPanchyat(0);
				dtoId.setTotalPaidStamp(0);
				dtoId.setTotalPaidReg(0);
				dtoId.setEntireTotal(0);
				dtoId.setTotalGovValue(0);
				dtoId.setTotalPayableReg(0);
				dtoId.setTotalStampDutyOnMovProp(0); // added by ankit
				dtoId.setTotalMunicipalDutyOnMovProp(0); // added by ankit
				dtoId.setTotalJanpadDutyOnMovProp(0); // added by ankit
				dtoId.setTotalUpkarDutyOnMovProp(0); // added by ankit
				dtoId.setTotalRegFeeOnMovProp(0); // added by ankit
				instdto.setLeaseId("");
				dtoId.setTotalPayableStamp(0);
				dtoId.setAlreadyPaidRegFee("0");
				dtoId.setAlreadyPaidStampDuty("0");
				dtoId.setPropertyValuationRequired(propValRequired);
				if (!"Y".equalsIgnoreCase(dtoId.getCancellationFlag())) {
					ArrayList exemDTOList = bo.getExemptions(deedId, instId, language, dtoId.isFromEstamp());
					dtoId.setExemptionList(exemDTOList);
					ArrayList regFeeExempList = bo.getRegFeeExemptions(deedId, instId, language, dtoId.isFromEstamp());
					dtoId.setRegFeeExemptionList(regFeeExempList);
					dtoId.setExemptionVisible("N");
					dtoId.setAvailExemption("N");
					String family = bo.getFamilyFlag(instId);
					if ("Y".equalsIgnoreCase(family)) {
						dtoId.setFamilyVisible("Y");
					} else {
						dtoId.setFamilyVisible("N");
					}
				}
				dtoId.setInstDescription(bo.getInstDiscription(instId, language));
				dtoId.setExemptionDescpList(new ArrayList());
				dtoId.setRegFeeExemptionDiscriptionList(new ArrayList());
				logger.debug("After exemption list");
				dtoId.setPropertyDetailsList(new ArrayList());
				// dtoId.setBaseValue(dto.getBaseValue());
				if (instId == 2105 || instId == 2106 || instId == 2108 || instId == 2184) {
					dtoId.setMaxPartitionMv(0);
					dtoId.setPartitionMV(0);
					dtoId.setNoOfSperatedPart("");
					dtoId.setAlreadyPaidStampDuty("");
				}
				if (instId == 2220 || instId == 2219) {
					if ("en".equalsIgnoreCase(language)) {
						InstrumentsDTO idto = new InstrumentsDTO();
						idto.setLeaseId("2214");
						// idto.setLeaseName("0 to less than 1 Yr");
						idto.setLeaseName("0 to 1 Yr");
						InstrumentsDTO idto1 = new InstrumentsDTO();
						idto1.setLeaseId("2215");
						// idto1.setLeaseName("1 to less than 5 Yr");
						idto1.setLeaseName("1 to 5 Yr");
						InstrumentsDTO idto2 = new InstrumentsDTO();
						idto2.setLeaseId("2216");
						// idto2.setLeaseName("5 to less than 10 Yr");
						idto2.setLeaseName("5 to 10 Yr");
						InstrumentsDTO idto3 = new InstrumentsDTO();
						idto3.setLeaseId("2223");
						// idto3.setLeaseName("10 to less than 20 Yr");
						idto3.setLeaseName("10 to 20 Yr");
						InstrumentsDTO idto4 = new InstrumentsDTO();
						idto4.setLeaseId("2217");
						idto4.setLeaseName("20 to less than 30 Yr");
						InstrumentsDTO idto5 = new InstrumentsDTO();
						idto5.setLeaseId("2218");
						idto5.setLeaseName("greater than or equal to 30 yr");
						instdto.setLeasePeriod(new ArrayList());
						instdto.getLeasePeriod().add(idto);
						instdto.getLeasePeriod().add(idto1);
						instdto.getLeasePeriod().add(idto2);
						instdto.getLeasePeriod().add(idto3);
						instdto.getLeasePeriod().add(idto4);
						instdto.getLeasePeriod().add(idto5);
					} else {
						InstrumentsDTO idto = new InstrumentsDTO();
						idto.setLeaseId("2214");
						// idto.setLeaseName("0 से 1 वर्ष से कम के लिए");
						idto.setLeaseName("0 से 1 वर्ष के लिए");
						InstrumentsDTO idto1 = new InstrumentsDTO();
						idto1.setLeaseId("2215");
						// idto1.setLeaseName("1 से 5 वर्ष से कम के लिए");
						idto1.setLeaseName("1 से 5 वर्ष के लिए");
						InstrumentsDTO idto2 = new InstrumentsDTO();
						idto2.setLeaseId("2216");
						// idto2.setLeaseName("5 से 10 वर्ष से कम के लिए");
						idto2.setLeaseName("5 से 10 वर्ष  के लिए");
						InstrumentsDTO idto3 = new InstrumentsDTO();
						idto3.setLeaseId("2223");
						// idto3.setLeaseName("10 से 20 वर्ष से कम के लिए");
						idto3.setLeaseName("10 से 20 वर्ष के लिए");
						InstrumentsDTO idto4 = new InstrumentsDTO();
						idto4.setLeaseId("2217");
						idto4.setLeaseName("20 से 30 वर्ष से कम के लिए");
						InstrumentsDTO idto5 = new InstrumentsDTO();
						idto5.setLeaseId("2218");
						idto5.setLeaseName("30 वर्ष  से अधिक या बराबर");
						instdto.setLeasePeriod(new ArrayList());
						instdto.getLeasePeriod().add(idto);
						instdto.getLeasePeriod().add(idto1);
						instdto.getLeasePeriod().add(idto2);
						instdto.getLeasePeriod().add(idto3);
						instdto.getLeasePeriod().add(idto4);
						instdto.getLeasePeriod().add(idto5);
					}
				}
				if (instId == 2025 && !"Y".equalsIgnoreCase(dtoId.getCancellationFlag())) {
					dtoId.setCancellationCategoryList(bo.cancellationCategoryList(language));
					dtoId.setCancellationCategoryId("5");
					dtoId.setCancellationCategoryName("Miscellaneous");
					dtoId.setCancellationDeedList(bo.cancellationDeedList(language));
					dtoId.setCancellationDeedId("1014");
					dtoId.setCancellationDeedName("Cancellation deed");
					dtoId.setCancellationFlag("Y");
					dtoId.setDeedId(0);
					dtoId.setDeedType("");
					dtoId.setCancellationInstrumentId(String.valueOf(instdto.getInstId()));
					instdto.setInstId(0);
					dtoId.setCancellationInstrument(instdto.getInstType());
					instdto.setInstType("");
					dtoId.setDeedCategoryId("");
					dtoId.setDeedCategoryName("");
					dtoId.setCancellationDeedDiscprtion(dtoId.getDeedDescription());
					dtoId.setCancellationInstDiscrition(dtoId.getInstDescription());
					dtoId.setCancellationRadio("1");
					dtoId.setDeedDescription("");
					dtoId.setInstDescription("");
					eForm.setDutycalculationDTOList(bo.getDeed(dtoId.getCancellationCategoryId(), "Y", language, dtoId.getFromModule()));
					eForm.setInstrumentDTOList(new ArrayList());
				}
				dtoId.setLandRevenueList(new ArrayList());
				dtoId.setTotalLandRevenue(0);
				dtoId.setMaxLandRevenue(0);
				dtoId.setLandRevenueId(0);
				dtoId.setActionName(null);
				eForm.setDutyCalculationDTO(dtoId);
				dto.setMineralListSelected(new ArrayList());
				// dto.setMineralSelected(null);
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
			}
			if (CommonConstant.DUTY_CALCULATE_ACTION.equals(actionName)) {
				DutyCalculationBO boexemption = new DutyCalculationBO();
				DutyCalculationDTO dutyDTO = eForm.getDutyCalculationDTO();
				InstrumentsDTO instrumentDTO = eForm.getInstDTO();
				DecimalFormat myformatter = new DecimalFormat("###.##");
				ExemptionDTO exemptionDTO = eForm.getExempDTO();
				// RegCommonForm rForm= new RegCommonForm();
				// rForm.setFromAdjudication(eForm.getFromAdjudication());
				// request.setAttribute("fromAdju",eForm.getFromAdjudication());
				logger.debug("from ADJU type" + request.getAttribute("fromAdju"));
				/*
				 * if(rForm.getFromAdjudication()==1){ session.setAttribute("fnName","Adjudication"); }
				 */
				logger.debug(exemptionDTO);
				int inst = instrumentDTO.getInstId();
				int deed = dutyDTO.getDeedId();
				System.out.println(exemptionDTO.getHdnExemptions());
				/*
				 * String[] exemptions = exemptionDTO.getHdnExemptions() == null ? null : exemptionDTO.getHdnExemptions().split(", ");
				 */
				String[] exemptions;
				if (exemptionDTO.getHdnExemptions() != null && !exemptionDTO.getHdnExemptions().equalsIgnoreCase("")) {
					exemptions = exemptionDTO.getHdnExemptions().split(", ");
				} else {
					exemptions = null;
				}
				// String[] exem = exemptionDTO.getHdnExempAmount().split("
				// ~");
				eForm.setSelectedExemptionList(boexemption.getExemptionList(exemptions));
				if (eForm.getSelectedExemptionList().size() <= 0) {
					eForm.setSelectedExemptionList(null);
				}
				// DecimalFormat myformatter=new DecimalFormat("###.##");
				// bo.captureNonPropStampDetails(eForm.getDutyCalculationDTO(),
				// eForm.getInstDTO(), eForm.getExempDTO());
				// dutyDTO.setBaseValueDisplay(String.valueOf(dutyDTO.getBaseValue
				// ()));
				// dutyDTO.setBaseValueDisplay(String.valueOf(dutyDTO.getBaseValue
				// ()));
				// dutyDTO.setBaseValueDisplay(common.getCurrencyFormatBaseValue(
				// dutyDTO.getBaseValue()));//double to string
				dutyDTO.setBaseValueDisplay(dutyDTO.getBaseValue());// double to
				// string
				// BigDecimal bigD=new
				// BigDecimal(dutyDTO.getBaseValue()).setScale
				// (20,RoundingMode.HALF_EVEN);
				// new BigDecimal(dutyDTO.getBaseValue()).setScale(7, 7);
				// dutyDTO.setBaseValueDisplay(String.valueOf(bigD.doubleValue())
				// );
				// bigD.setScale(7, 7);
				// ADD BELOW STATEMENTS IN DEED-INSTRUMENT CONDITION
				// GET PROP_VAL_REQ_FLAG BASED IN INSTRUMENT ID HERE
				String propValReqFlag = bo.getPropValRequiredFlag(inst);
				// added by akansha by convence exemption
				// adding instrument for plant and machinery by ankit -- 2271 &
				// 2272
				ArrayList exlist = dto.getExemptionDescpList();
				if ((deed == 1053) && (dto.getSelectedExempId().equals("96")) && (inst == 2090 || inst == 2271) && (exlist.size() > 0)) {
					double d = bo.getExemptionPecentage(dutyDTO, instrumentDTO);
					dutyDTO.setAnnualRentDisplay(myformatter.format(dutyDTO.getAnnualRent()));
					// dutyDTO.setBaseValueDisplay(myformatter.format(dutyDTO.
					// getBaseValue()));
					dutyDTO.setDutyAlreadyPaidDisplay(myformatter.format(dutyDTO.getDutyAlreadyPaid()));
					dutyDTO.setDutyPaidDisplay(myformatter.format(dutyDTO.getDutyPaid()));
					dutyDTO.setDateCalculation(bo.getCurrentDate());
					dutyDTO.setTotalDisplay(myformatter.format(dutyDTO.getTotal()));
					dutyDTO.setNagarPalikaDutyDisplay(myformatter.format(dutyDTO.getNagarPalikaDuty()));
					dutyDTO.setPanchayatDutyDisplay(myformatter.format(dutyDTO.getPanchayatDuty()));
					dutyDTO.setUpkarDutyDisplay(myformatter.format(dutyDTO.getUpkarDuty()));
					dutyDTO.setStampDutyDisplay(myformatter.format(dutyDTO.getStampDuty()));
					dutyDTO.setRegFeeDisplay(myformatter.format(dutyDTO.getRegFee()));
					dutyDTO.setTotalregFeeDisplay(myformatter.format(dutyDTO.getTotalregFee()));
					dutyDTO.setTotalStampDutyDisplay(myformatter.format(dutyDTO.getTotalStampDuty() * d / 100));
					// dutyDTO.setTotalStampDutyDisplay(myformatter.format(9999))
					// ;
					dutyDTO.setTotalNagarPalikaDisplay(myformatter.format(dutyDTO.getTotalNagarPalika()));
					// double Nagarpalika = dutyDTO.getTotalNagarPalika();
					// dutyDTO.setTotalNagarPalikaDisplay(myformatter.format(111)
					// );
					dutyDTO.setTotalPanchyatDisplay(myformatter.format(dutyDTO.getTotalPanchyat()));
					// dutyDTO.setTotalPanchyatDisplay(myformatter.format(3434));
					// dutyDTO.setTotalUpkarDisplay(myformatter.format(dutyDTO.
					// getTotalUpkar()));
					double stampNew = Math.ceil(Double.parseDouble((dutyDTO.getTotalStampDutyDisplay())));
					dutyDTO.setTotalUpkarDisplay(myformatter.format(stampNew * 10 / 100));
					double upkar = Math.ceil(Double.parseDouble(dutyDTO.getTotalUpkarDisplay()));
					// dutyDTO.setTotalUpkarDisplay(myformatter.format(5645));
					dutyDTO.setEntireTotalDisplay(myformatter.format(dutyDTO.getEntireTotal()));
					// dutyDTO.setEntireTotalDisplay(myformatter.format(7547));
					dutyDTO.setTotalPayableStampDisplay(myformatter.format(dutyDTO.getTotalPayableStamp()));
					// dutyDTO.setTotalPayableStampDisplay(myformatter.format(654
					// ));
					dutyDTO.setTotalPayableRegDisplay(myformatter.format(dutyDTO.getTotalPayableReg()));
					dutyDTO.setDutyafterExemptionDisplay(myformatter.format(dutyDTO.getDutyafterExemption()));
					// dutyDTO.setDutyafterExemptionDisplay(myformatter.format(947
					// ));
					// dutyDTO.setDutyafterExemptionDisplayTotal(myformatter.
					// format(dutyDTO.getDutyafterExemptionTotal()));
					dutyDTO.setDutyafterExemptionDisplayTotal(myformatter.format(stampNew + dutyDTO.getTotalNagarPalika() + dutyDTO.getTotalPanchyat() + upkar));
					dutyDTO.setDutyafterExemptionTotal(Double.parseDouble((dutyDTO.getDutyafterExemptionDisplayTotal())));
					// dutyDTO.setDutyafterExemptionDisplayTotal(myformatter.
					// format(543));
					dutyDTO.setRegFeeafterExempDisplay(myformatter.format(dutyDTO.getRegFeeafterExemp()));
					dutyDTO.setRegFeeafterExempTotalDisplay(myformatter.format(dutyDTO.getRegFeeafterExempTotal()));
					dutyDTO.setPayableRegFeeDisplay(myformatter.format(dutyDTO.getPayableRegFee()));
					dutyDTO.setPayableDutyDisplay(myformatter.format(dutyDTO.getPayableStampDuty()));
				} else {
					dutyDTO.setAnnualRentDisplay(myformatter.format(dutyDTO.getAnnualRent()));
					// dutyDTO.setBaseValueDisplay(myformatter.format(dutyDTO.
					// getBaseValue()));
					dutyDTO.setDutyAlreadyPaidDisplay(myformatter.format(dutyDTO.getDutyAlreadyPaid()));
					dutyDTO.setDutyPaidDisplay(myformatter.format(dutyDTO.getDutyPaid()));
					dutyDTO.setDateCalculation(bo.getCurrentDate());
					dutyDTO.setTotalDisplay(myformatter.format(Math.ceil(dutyDTO.getTotal())));
					dutyDTO.setNagarPalikaDutyDisplay(myformatter.format(dutyDTO.getNagarPalikaDuty()));
					dutyDTO.setPanchayatDutyDisplay(myformatter.format(dutyDTO.getPanchayatDuty()));
					dutyDTO.setUpkarDutyDisplay(myformatter.format(Math.ceil(dutyDTO.getUpkarDuty())));
					dutyDTO.setStampDutyDisplay(myformatter.format(Math.ceil(dutyDTO.getStampDuty())));
					dutyDTO.setRegFeeDisplay(myformatter.format(dutyDTO.getRegFee()));
					dutyDTO.setTotalregFeeDisplay(myformatter.format(dutyDTO.getTotalregFee()));
					dutyDTO.setTotalStampDutyDisplay(myformatter.format(Math.ceil(dutyDTO.getTotalStampDuty())));
					dutyDTO.setTotalNagarPalikaDisplay(myformatter.format(dutyDTO.getTotalNagarPalika()));
					dutyDTO.setTotalPanchyatDisplay(myformatter.format(dutyDTO.getTotalPanchyat()));
					if ((inst == 2254 || inst == 2220 || inst == 2219 || inst == 2277)) {
						dutyDTO.setTotalUpkarDisplay(myformatter.format(Math.ceil(dutyDTO.getUpkarDuty())));
						dutyDTO.setEntireTotalDisplay(myformatter.format(Math.ceil(dutyDTO.getEntireTotal() + dutyDTO.getUpkarDuty())));
					} else {
						dutyDTO.setTotalUpkarDisplay(myformatter.format(Math.ceil(dutyDTO.getTotalUpkar())));
						dutyDTO.setEntireTotalDisplay(myformatter.format(Math.ceil(dutyDTO.getEntireTotal())));
					}
					if (inst == 2220 || inst == 2219) {
						dutyDTO.setTotalPayableStampDisplay(myformatter.format(Math.ceil(dutyDTO.getDutyafterExemption())));
					}
					/*
					 * else if(inst==2012 && dto.getSelectedExempId().equals("184")){ if(dutyDTO.getPanchayatDuty()!=500){ dutyDTO.setPanchayatDuty(500); dutyDTO.setPanchayatDutyDisplay (myformatter.format(dutyDTO.getPanchayatDuty())); } if(dutyDTO.getStampDuty()!=500){ dutyDTO.setStampDuty(500); dutyDTO.setStampDutyDisplay(myformatter .format(dutyDTO.getStampDuty())); }
					 * 
					 * }
					 */
					else {
						dutyDTO.setTotalPayableStampDisplay(myformatter.format(Math.ceil(dutyDTO.getTotalPayableStamp())));
					}
					// dutyDTO.setTotalPayableStampDisplay(myformatter.format(Math
					// .ceil(dutyDTO.getTotalPayableStamp())));
					dutyDTO.setTotalPayableRegDisplay(myformatter.format(Math.ceil(dutyDTO.getTotalPayableReg())));
					dutyDTO.setDutyafterExemptionDisplay(myformatter.format(Math.ceil(dutyDTO.getDutyafterExemption())));
					dutyDTO.setDutyafterExemptionDisplayTotal(myformatter.format(Math.ceil(dutyDTO.getDutyafterExemptionTotal())));
					dutyDTO.setRegFeeafterExempDisplay(myformatter.format(Math.ceil(dutyDTO.getRegFeeafterExemp())));
					dutyDTO.setRegFeeafterExempTotalDisplay(myformatter.format(Math.ceil(dutyDTO.getRegFeeafterExempTotal())));
					dutyDTO.setPayableRegFeeDisplay(myformatter.format(Math.ceil(dutyDTO.getPayableRegFee())));
					dutyDTO.setPayableDutyDisplay(myformatter.format(Math.ceil(dutyDTO.getPayableStampDuty())));
				}
				if (instrumentDTO.getValueOfMovableProp() != null && !instrumentDTO.getValueOfMovableProp().isEmpty()) {
					if (Long.parseLong(instrumentDTO.getValueOfMovableProp()) > 0 && propValReqFlag.equalsIgnoreCase("N")) {
						/*
						 * if(dutyDTO.getRegFeeExemptionDiscriptionList()!=null){
						 * 
						 * logger.info("getRegFeeafterExemp :"+dutyDTO. getRegFeeafterExemp() + " : "+dutyDTO.getRegFeeOnMovProp()); logger.info("getRegFeeafterExempTotal : "+ dutyDTO.getRegFeeafterExempTotal()+ " : "+dutyDTO.getRegFeeOnMovProp()); logger.info("getPayableRegFee :" +dutyDTO.getPayableRegFee()+ " : "+dutyDTO.getRegFeeOnMovProp()); logger.info("getTotalPayableReg : "+ dutyDTO.getTotalPayableReg()+ " : "+dutyDTO.getRegFeeOnMovProp());
						 * 
						 * }else{ logger.info("getPayableRegFee :"+dutyDTO.getPayableRegFee ()+ " : "+dutyDTO.getRegFeeOnMovProp()); logger.info("getTotalPayableReg : "+ dutyDTO.getTotalPayableReg()+ " : "+dutyDTO.getRegFeeOnMovProp());
						 * 
						 * }
						 * 
						 * if(dutyDTO.getExemptionDescpList()!=null){
						 * 
						 * logger.info("getDutyafterExemption :"+dutyDTO. getDutyafterExemption()+ " : "+dutyDTO.getStampDutyOnMovProp()); logger.info("getDutyafterExemptionTotal : "+ dutyDTO.getDutyafterExemptionTotal()+ " : "+dutyDTO.getTotalDutyOnMovProp()); logger.info("getPayableStampDuty :" +dutyDTO.getPayableStampDuty()+ " : "+dutyDTO.getStampDutyOnMovProp()); logger.info("getTotalPayableStamp : "+ dutyDTO.getTotalPayableStamp()+ " : "+dutyDTO.getTotalDutyOnMovProp());
						 * 
						 * } else{logger.info("getPayableStampDuty :"+dutyDTO. getPayableStampDuty()+ " : "+dutyDTO.getStampDutyOnMovProp()); logger.info("getTotalPayableStamp : "+ dutyDTO.getTotalPayableStamp()+ " : "+dutyDTO.getTotalDutyOnMovProp()); }
						 */
						double totalDutyOnMovProp = dutyDTO.getStampDutyOnMovProp() + dutyDTO.getJanpadDutyOnMovProp() + dutyDTO.getMunicipalDutyOnMovProp() + dutyDTO.getUpkarDutyOnMovProp();
						dutyDTO.setTotalDutyOnMovProp(totalDutyOnMovProp);
						dutyDTO.setTotalRegFeeOnMovProp(dutyDTO.getRegFeeOnMovProp());
						dutyDTO.setAnnualRent(dutyDTO.getAnnualRent());
						dutyDTO.setDutyAlreadyPaid(dutyDTO.getDutyAlreadyPaid());
						dutyDTO.setDutyPaid(dutyDTO.getDutyPaid());
						dutyDTO.setDateCalculation(bo.getCurrentDate());
						dutyDTO.setTotal(Math.ceil(dutyDTO.getTotalDutyOnMovProp()));
						dutyDTO.setNagarPalikaDuty(dutyDTO.getMunicipalDutyOnMovProp());
						dutyDTO.setPanchayatDuty(dutyDTO.getJanpadDutyOnMovProp());
						dutyDTO.setUpkarDuty(Math.ceil(dutyDTO.getUpkarDutyOnMovProp()));
						dutyDTO.setStampDuty(Math.ceil(dutyDTO.getStampDutyOnMovProp()));
						dutyDTO.setRegFee(dutyDTO.getRegFeeOnMovProp());
						dutyDTO.setTotalregFee(dutyDTO.getTotalRegFeeOnMovProp());
						dutyDTO.setTotalStampDuty(Math.ceil(dutyDTO.getStampDutyOnMovProp()));
						dutyDTO.setTotalNagarPalika(dutyDTO.getMunicipalDutyOnMovProp());
						dutyDTO.setTotalPanchyat(dutyDTO.getJanpadDutyOnMovProp());
						dutyDTO.setTotalUpkar(Math.ceil(dutyDTO.getUpkarDutyOnMovProp()));
						dutyDTO.setEntireTotal(dutyDTO.getTotalDutyOnMovProp());
						dutyDTO.setTotalPayableStamp(dutyDTO.getTotalDutyOnMovProp());
						dutyDTO.setTotalPayableReg(dutyDTO.getTotalRegFeeOnMovProp());
						dutyDTO.setDutyafterExemption(dutyDTO.getTotalDutyOnMovProp());
						dutyDTO.setDutyafterExemptionTotal(dutyDTO.getTotalDutyOnMovProp());
						dutyDTO.setRegFeeafterExemp(Math.ceil(dutyDTO.getTotalRegFeeOnMovProp()));
						dutyDTO.setRegFeeafterExempTotal(Math.ceil(dutyDTO.getTotalRegFeeOnMovProp()));
						dutyDTO.setPayableRegFee(Math.ceil(dutyDTO.getTotalRegFeeOnMovProp()));
						dutyDTO.setPayableStampDuty(dutyDTO.getTotalDutyOnMovProp());
						// exemption on plant and machinery
						ArrayList listexempStamp = dto.getExemptionDescpList();
						ArrayList listexempReg = dto.getRegFeeExemptionDiscriptionList();
						double r = bo.getExemptionPecentageRegFee(dutyDTO, instrumentDTO);
						double rd = dutyDTO.getPayableRegFee() - ((dutyDTO.getPayableRegFee() * r) / 100);
						double rd1 = Math.ceil(dutyDTO.getTotalPayableReg() - ((dutyDTO.getTotalPayableReg() * r) / 100));
						double d = bo.getExemptionPecentage(dutyDTO, instrumentDTO);
						double fd = dutyDTO.getPayableStampDuty() - ((dutyDTO.getPayableStampDuty() * d) / 100);
						double fd1 = Math.ceil(dutyDTO.getTotalPayableStamp() - ((dutyDTO.getTotalPayableStamp() * d) / 100));
						for (int i = 0; i < listexempStamp.size(); i++) {
							DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexempStamp.get(i);
							if (dtoForm1.getExmepApplicable().equalsIgnoreCase("S")) {
								if (dtoForm1.getExempId().equals("129") || dtoForm1.getExempId().equals("180") || dtoForm1.getExempId().equals("182")) {
									dutyDTO.setMinusExempStamp(((dutyDTO.getPayableStampDuty() * d) / 100));
									dutyDTO.setMinusExempStampTotal((dutyDTO.getMinusExempStampTotal()) + ((dutyDTO.getTotalPayableStamp() * d) / 100));
									dutyDTO.setDutyafterExemption(fd);
									dutyDTO.setDutyafterExemptionTotal(fd1);
								}
							}
						}
						for (int i = 0; i < listexempReg.size(); i++) {
							DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexempReg.get(i);
							if (dtoForm1.getExmepApplicable().equalsIgnoreCase("R")) {
								if (dtoForm1.getExempId().equalsIgnoreCase("69")) {
									dutyDTO.setMinusExempReg(((dutyDTO.getPayableRegFee() * r) / 100));
									dutyDTO.setMinusExempRegTotal(((dutyDTO.getTotalPayableReg() * r) / 100));
									dutyDTO.setRegFeeafterExemp(rd);
									dutyDTO.setRegFeeafterExempTotal(rd1);
								}
							}
						}
					}
					if (Long.parseLong(instrumentDTO.getValueOfMovableProp()) > 0 && propValReqFlag.equalsIgnoreCase("Y") && "true".equalsIgnoreCase(dutyDTO.getValuationIdValidate())) {

						double totalDutyOnMovProp = dutyDTO.getTotalStampDutyOnMovProp() + dutyDTO.getTotalJanpadDutyOnMovProp() + dutyDTO.getTotalMunicipalDutyOnMovProp() + dutyDTO.getTotalUpkarDutyOnMovProp();
						dutyDTO.setTotalDutyOnMovProp(totalDutyOnMovProp);
						dutyDTO.setAnnualRent(dutyDTO.getAnnualRent());
						dutyDTO.setDutyAlreadyPaid(dutyDTO.getDutyAlreadyPaid());
						dutyDTO.setDutyPaid(dutyDTO.getDutyPaid());
						dutyDTO.setDateCalculation(bo.getCurrentDate());
						dutyDTO.setTotal(Math.ceil(dutyDTO.getTotal().doubleValue() + dutyDTO.getTotalDutyOnMovProp()));
						// dutyDTO.setNagarPalikaDuty(dutyDTO.getNagarPalikaDuty(
						// ) + dutyDTO.getMunicipalDutyOnMovProp());
						// dutyDTO.setPanchayatDuty(dutyDTO.getPanchayatDuty()+
						// dutyDTO.getJanpadDutyOnMovProp());
						// dutyDTO.setUpkarDuty(Math.ceil(dutyDTO.getUpkarDuty()+
						// dutyDTO.getUpkarDutyOnMovProp()));
						// dutyDTO.setStampDuty(Math.ceil(dutyDTO.getStampDuty()+
						// dutyDTO.getStampDutyOnMovProp()));
						dutyDTO.setRegFee(dutyDTO.getRegFee() + dutyDTO.getTotalRegFeeOnMovProp());
						dutyDTO.setTotalregFee(dutyDTO.getTotalregFee() + dutyDTO.getTotalRegFeeOnMovProp());
						dutyDTO.setTotalStampDuty(Math.ceil(dutyDTO.getTotalStampDuty() + dutyDTO.getTotalStampDutyOnMovProp()));
						dutyDTO.setTotalNagarPalika(dutyDTO.getTotalNagarPalika() + dutyDTO.getTotalMunicipalDutyOnMovProp());
						dutyDTO.setTotalPanchyat(dutyDTO.getTotalPanchyat() + dutyDTO.getTotalJanpadDutyOnMovProp());
						dutyDTO.setTotalUpkar(Math.ceil(dutyDTO.getTotalUpkar() + dutyDTO.getTotalUpkarDutyOnMovProp()));
						dutyDTO.setEntireTotal(Math.ceil(dutyDTO.getEntireTotal() + dutyDTO.getTotalDutyOnMovProp()));
						dutyDTO.setTotalPayableStamp(Math.ceil(dutyDTO.getTotalPayableStamp() + dutyDTO.getTotalDutyOnMovProp()));
						dutyDTO.setTotalPayableReg(Math.ceil(dutyDTO.getTotalPayableReg() + dutyDTO.getTotalRegFeeOnMovProp()));
						dutyDTO.setDutyafterExemption(Math.ceil(dutyDTO.getDutyafterExemption() + dutyDTO.getTotalDutyOnMovProp()));
						dutyDTO.setDutyafterExemptionTotal(Math.ceil(dutyDTO.getDutyafterExemptionTotal() + dutyDTO.getTotalDutyOnMovProp()));
						dutyDTO.setRegFeeafterExemp(Math.ceil(dutyDTO.getRegFeeafterExemp() + dutyDTO.getTotalRegFeeOnMovProp()));
						dutyDTO.setRegFeeafterExempTotal(Math.ceil(dutyDTO.getRegFeeafterExempTotal() + dutyDTO.getTotalRegFeeOnMovProp()));
						dutyDTO.setPayableRegFee(Math.ceil(dutyDTO.getPayableRegFee() + dutyDTO.getTotalRegFeeOnMovProp()));
						dutyDTO.setPayableStampDuty(Math.ceil(dutyDTO.getPayableStampDuty() + dutyDTO.getTotalDutyOnMovProp()));
						ArrayList propertyList = dutyDTO.getPropertyDetailsList();
						if (propertyList != null && propertyList.size() > 0) {
							for (int i = 0; i < propertyList.size(); i++) {
								DutyCalculationDTO ddto = (DutyCalculationDTO) propertyList.get(i);
								ddto.setStampDuty(ddto.getStampDuty() + ddto.getStampDutyOnMovProp());
								ddto.setNagarPalikaDuty(ddto.getNagarPalikaDuty() + ddto.getMunicipalDutyOnMovProp());
								ddto.setPanchayatDuty(ddto.getPanchayatDuty() + ddto.getJanpadDutyOnMovProp());
								ddto.setUpkarDuty(ddto.getUpkarDuty() + ddto.getUpkarDutyOnMovProp());
								ddto.setRegFee(ddto.getRegFee() + ddto.getRegFeeOnMovProp());
								ddto.setDashSatmp(myformatter.format(ddto.getStampDuty()));
								ddto.setDashMunicipal(myformatter.format(ddto.getNagarPalikaDuty()));
								ddto.setDashJanpad(myformatter.format(ddto.getPanchayatDuty()));
								ddto.setDashUpkar(myformatter.format(ddto.getUpkarDuty()));
								ddto.setDashReg(myformatter.format(ddto.getRegFee()));
								double totalDuty = ddto.getStampDutyOnMovProp() + ddto.getMunicipalDutyOnMovProp() + ddto.getJanpadDutyOnMovProp() + ddto.getUpkarDutyOnMovProp();
								ddto.setTotalDutyOnMovProp(totalDuty);
								ddto.setDashTotal(myformatter.format(ddto.getTotal() + totalDuty));
								ddto.setDashPayTotal(myformatter.format(ddto.getPayableStampDuty() + totalDuty));
								ddto.setDashPayReg(myformatter.format(ddto.getPayableRegFee() + ddto.getRegFeeOnMovProp()));
								logger.info("getStampDutyOnMovProp :" + ddto.getStampDutyOnMovProp());
								logger.info("getJanpadDutyOnMovProp :" + ddto.getJanpadDutyOnMovProp());
								logger.info("getMunicipalDutyOnMovProp :" + ddto.getMunicipalDutyOnMovProp());
								logger.info("getUpkarDutyOnMovProp :" + ddto.getUpkarDutyOnMovProp());
								logger.info("getRegFeeOnMovProp :" + ddto.getRegFeeOnMovProp());
								// propertyList.set(i, ddto);
							}

							// dutyDTO.setPropertyDetailsList(propertyList);
						}
						// exemption on plant and machinery
						ArrayList listexempStamp = dto.getExemptionDescpList();
						ArrayList listexempReg = dto.getRegFeeExemptionDiscriptionList();
						double r = bo.getExemptionPecentageRegFee(dutyDTO, instrumentDTO);
						double rd = dutyDTO.getPayableRegFee() - ((dutyDTO.getPayableRegFee() * r) / 100);
						double rd1 = Math.ceil(dutyDTO.getTotalPayableReg() - ((dutyDTO.getTotalPayableReg() * r) / 100));
						double d = bo.getExemptionPecentage(dutyDTO, instrumentDTO);
						double fd = dutyDTO.getPayableStampDuty() - ((dutyDTO.getPayableStampDuty() * d) / 100);
						double fd1 = Math.ceil(dutyDTO.getTotalPayableStamp() - ((dutyDTO.getTotalPayableStamp() * d) / 100));
						for (int i = 0; i < listexempStamp.size(); i++) {
							DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexempStamp.get(i);
							if (dtoForm1.getExmepApplicable().equalsIgnoreCase("S")) {
								if (dtoForm1.getExempId().equals("129") || dtoForm1.getExempId().equals("180") || dtoForm1.getExempId().equals("182")) {
									dutyDTO.setMinusExempStamp(((dutyDTO.getPayableStampDuty() * d) / 100));
									dutyDTO.setMinusExempStampTotal((dutyDTO.getMinusExempStampTotal()) + ((dutyDTO.getTotalPayableStamp() * d) / 100));
									dutyDTO.setDutyafterExemption(fd);
									dutyDTO.setDutyafterExemptionTotal(fd1);
								}
							}
						}
						for (int i = 0; i < listexempReg.size(); i++) {
							DutyCalculationDTO dtoForm1 = (DutyCalculationDTO) listexempReg.get(i);
							if (dtoForm1.getExmepApplicable().equalsIgnoreCase("R")) {
								if (dtoForm1.getExempId().equalsIgnoreCase("69")) {
									dutyDTO.setMinusExempReg(((dutyDTO.getPayableRegFee() * r) / 100));
									dutyDTO.setMinusExempRegTotal(((dutyDTO.getTotalPayableReg() * r) / 100));
									dutyDTO.setRegFeeafterExemp(rd);
									dutyDTO.setRegFeeafterExempTotal(rd1);
								}
							}

						}
					}
					// set values to display on jsp page
					dutyDTO.setAnnualRentDisplay(myformatter.format(dutyDTO.getAnnualRent()));
					dutyDTO.setDutyAlreadyPaidDisplay(myformatter.format(dutyDTO.getDutyAlreadyPaid()));
					dutyDTO.setDutyPaidDisplay(myformatter.format(dutyDTO.getDutyPaid()));
					dutyDTO.setDateCalculation(bo.getCurrentDate());
					dutyDTO.setTotalDisplay(myformatter.format(Math.ceil(dutyDTO.getTotal())));
					dutyDTO.setNagarPalikaDutyDisplay(myformatter.format(dutyDTO.getNagarPalikaDuty()));
					dutyDTO.setPanchayatDutyDisplay(myformatter.format(dutyDTO.getPanchayatDuty()));
					dutyDTO.setUpkarDutyDisplay(myformatter.format(Math.ceil(dutyDTO.getUpkarDuty())));
					dutyDTO.setStampDutyDisplay(myformatter.format(Math.ceil(dutyDTO.getStampDuty())));
					dutyDTO.setRegFeeDisplay(myformatter.format(dutyDTO.getRegFee()));
					dutyDTO.setTotalregFeeDisplay(myformatter.format(dutyDTO.getTotalregFee()));
					dutyDTO.setTotalStampDutyDisplay(myformatter.format(Math.ceil(dutyDTO.getTotalStampDuty())));
					dutyDTO.setTotalNagarPalikaDisplay(myformatter.format(dutyDTO.getTotalNagarPalika()));
					dutyDTO.setTotalPanchyatDisplay(myformatter.format(dutyDTO.getTotalPanchyat()));
					dutyDTO.setTotalUpkarDisplay(myformatter.format(Math.ceil(dutyDTO.getTotalUpkar())));
					dutyDTO.setEntireTotalDisplay(myformatter.format(Math.ceil(dutyDTO.getEntireTotal())));
					dutyDTO.setTotalPayableStampDisplay(myformatter.format(Math.ceil(dutyDTO.getTotalPayableStamp())));
					dutyDTO.setTotalPayableRegDisplay(myformatter.format(Math.ceil(dutyDTO.getTotalPayableReg())));
					dutyDTO.setDutyafterExemptionDisplay(myformatter.format(Math.ceil(dutyDTO.getDutyafterExemption())));
					dutyDTO.setDutyafterExemptionDisplayTotal(myformatter.format(Math.ceil(dutyDTO.getDutyafterExemptionTotal())));
					dutyDTO.setRegFeeafterExempDisplay(myformatter.format(Math.ceil(dutyDTO.getRegFeeafterExemp())));
					dutyDTO.setRegFeeafterExempTotalDisplay(myformatter.format(Math.ceil(dutyDTO.getRegFeeafterExempTotal())));
					dutyDTO.setPayableRegFeeDisplay(myformatter.format(Math.ceil(dutyDTO.getPayableRegFee())));
					dutyDTO.setPayableDutyDisplay(myformatter.format(Math.ceil(dutyDTO.getPayableStampDuty())));
				}
				logger.info("getAnnualRentDisplay :" + dutyDTO.getAnnualRent());
				logger.info("getDutyAlreadyPaidDisplay :" + dutyDTO.getDutyAlreadyPaid());
				logger.info("getDutyPaidDisplay :" + dutyDTO.getDutyPaid());
				logger.info("getDateCalculation :" + dutyDTO.getDateCalculation());
				logger.info("getTotalDisplay :" + dutyDTO.getTotal());
				logger.info("getNagarPalikaDutyDisplay :" + dutyDTO.getNagarPalikaDuty());
				logger.info("getPanchayatDutyDisplay :" + dutyDTO.getPanchayatDuty());
				logger.info("getUpkarDutyDisplay :" + dutyDTO.getUpkarDuty());
				logger.info("getStampDutyDisplay :" + dutyDTO.getStampDuty());
				logger.info("getRegFeeDisplay :" + dutyDTO.getRegFee());
				logger.info("getTotalregFeeDisplay :" + dutyDTO.getTotalregFee());
				logger.info("getTotalStampDutyDisplay :" + dutyDTO.getTotalStampDuty());
				logger.info("getTotalNagarPalikaDisplay :" + dutyDTO.getTotalNagarPalika());
				logger.info("getTotalPanchyatDisplay :" + dutyDTO.getTotalPanchyat());
				logger.info("getTotalUpkar :" + dutyDTO.getTotalUpkar());
				logger.info("getEntireTotal :" + dutyDTO.getEntireTotal());
				logger.info("getTotalPayableStampDisplay :" + dutyDTO.getTotalPayableStamp());
				logger.info("getTotalPayableRegDisplay :" + dutyDTO.getTotalPayableReg());
				logger.info("getDutyafterExemptionDisplay :" + dutyDTO.getDutyafterExemption());
				logger.info("getDutyafterExemptionDisplayTotal :" + dutyDTO.getDutyafterExemptionTotal());
				logger.info("getRegFeeafterExempDisplay :" + dutyDTO.getRegFeeafterExemp());
				logger.info("getRegFeeafterExempTotalDisplay :" + dutyDTO.getRegFeeafterExempTotal());
				logger.info("getPayableRegFeeDisplay :" + dutyDTO.getPayableRegFee());
				logger.info("getPayableDutyDisplay :" + dutyDTO.getPayableStampDuty());
				logger.info("getTotalStampDutyOnMovProp :" + dutyDTO.getTotalStampDutyOnMovProp());
				logger.info("getTotalJanpadDutyOnMovProp :" + dutyDTO.getTotalJanpadDutyOnMovProp());
				logger.info("getTotalMunicipalDutyOnMovProp :" + dutyDTO.getTotalMunicipalDutyOnMovProp());
				logger.info("getTotalUpkarDutyOnMovProp :" + dutyDTO.getTotalUpkarDutyOnMovProp());
				logger.info("getTotalRegFeeOnMovProp :" + dutyDTO.getTotalRegFeeOnMovProp());
				logger.debug("Stamp Id" + dutyDTO.getStampId());
				request.setAttribute("stampId", dutyDTO.getStampId());
				logger.debug("Stamp Id" + request.getAttribute("stampId"));
				eForm.setDutyCalculationDTO(dutyDTO);
				eForm.setExempDTO(exemptionDTO);
				eForm.setInstDTO(instrumentDTO);
				logger.debug("base Value:-" + dutyDTO.getBaseValue());
				logger.debug("Deed Name:-" + dutyDTO.getDeedType());
				logger.debug("Instrument Name:-" + instrumentDTO.getInstType());
				logger.debug("Exemption Name:-" + exemptionDTO.getHdnExemptions());
				if (propValReqFlag.equalsIgnoreCase("Y") && !"true".equalsIgnoreCase(dutyDTO.getValuationIdValidate())) {
					request.setAttribute("deedId1", String.valueOf(eForm.getDutyCalculationDTO().getDeedId()));
					forwardJsp = CommonConstant.FORWARD_PROP_VAL;
					if ("Y".equalsIgnoreCase(eForm.getDutyCalculationDTO().getRinPustikaCheckFlag())) {
						request.setAttribute("rinPustika", "Y");
					} else {
						request.setAttribute("rinPustika", "N");
					}
					if (eForm.getInstDTO().getInstId() == 2105 || eForm.getInstDTO().getInstId() == 2106 || eForm.getInstDTO().getInstId() == 2108 || eForm.getInstDTO().getInstId() == 2184) {
						request.setAttribute("singleBuyer", "Y");
					} else {
						request.setAttribute("singleBuyer", "N");
					}
					// HashMap<String,String> userValueMap=new
					// HashMap<String,String>();
					ArrayList userList = new ArrayList();
					String param[] = request.getParameterValues("userEnterableFieldValue");
					ArrayList list = eForm.getInstDTO().getUserEnterableList();
					if (list != null) {
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							InstrumentsDTO indto = (InstrumentsDTO) list.get(i);
							UserEnterableDTO userDto = new UserEnterableDTO();
							String id = indto.getUserEnterableId();
							String value = "";
							if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getCommonUserField();
							} else if ("Y".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getInStampDutyAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "Y".equalsIgnoreCase(indto.getAsConsiderationAmountFlag())) {
								value = eForm.getInstDTO().getAsConsiderationAmount();
							} else if ("N".equalsIgnoreCase(indto.getInStampDutyAmountFlag()) && "N".equalsIgnoreCase(indto.getAsConsiderationAmountFlag()) && "Y".equalsIgnoreCase(indto.getMovablePropFlag())) {
								value = eForm.getInstDTO().getValueOfMovableProp();
							}// added by ankit for plant and machinery
							else {
								value = param[j];
								j++;
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidRegFeeFlag())) {
								dto.setAlreadyPaidRegFee(value);
							}
							if ("Y".equalsIgnoreCase(indto.getAlreadyPaidStampDutyFlag())) {
								dto.setAlreadyPaidStampDuty(value);
							}
							userDto.setId(id);
							userDto.setValue(value);
							userDto.setName(indto.getUserEnterableName());
							indto.setUserEnterableFieldValue(value);
							// userValueMap.put(id,value);
							userList.add(userDto);
						}
						// dto.setUserValueMap(userValueMap);
						dto.setUserValueList(userList);
					}
					dto.setActionName(null);
					actionName = "";
				} else {
					/*
					 * if(eForm.getModuleName()!=null&&eForm.getModuleName(). equalsIgnoreCase(CommonConstant.REGISTRATION_DUTY_PAGE)) {
					 * 
					 * if(propValReqFlag.equalsIgnoreCase("Y")){
					 * 
					 * forwardJsp = CommonConstant.FORWARD_REGINIT_DISPLAY;
					 * 
					 * }else{ forwardJsp = CommonConstant.FORWARD_NONREGINIT_DISPLAY; } request.setAttribute("stampId", dutyDTO.getStampId()); request .setAttribute("fromAdju",eForm.getFromAdjudication()); dto.setActionName(null); actionName=""; } else
					 */
					// {
					if (dto.getDeedId() == 1054) {
						if (dto.getExchangeList1().size() == 0 && dto.getExchangeList2().size() == 0) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "Please add property for both transactiong parties");
							} else {
								request.setAttribute("ERROR", "कृपया दोनों पार्टी के लिए संपत्ति जोड़ें|");
							}
							regFlag = false;
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";
						} else if (dto.getExchangeList1().size() == 0) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "Please add property for first transacting party");
							} else {
								request.setAttribute("ERROR", "कृपया प्रथम पार्टी के लिए संपत्ति जोड़ें|");
							}
							regFlag = false;
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";
						} else if (dto.getExchangeList2().size() == 0) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "Please add property for second transactiong party");
							} else {
								request.setAttribute("ERROR", "कृपया दूसरी  पार्टी के लिए संपत्ति जोड़ें| ");
							}
							regFlag = false;
							forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
							dto.setActionName(null);
							actionName = "";
						} else {
							bo.submitDutyDetails(dutyDTO, eForm.getInstDTO());
							forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
							dto.setActionName(null);
							actionName = "";
						}
					} else if (eForm.getInstDTO().getInstId() == 2105 || eForm.getInstDTO().getInstId() == 2106 || eForm.getInstDTO().getInstId() == 2108 || eForm.getInstDTO().getInstId() == 2184) {
						double separtedPart = Double.parseDouble(dto.getNoOfSperatedPart());
						if (separtedPart < 2) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "No of Separated Parts must be greater than equal to 2");
							} else {
								request.setAttribute("ERROR", "हिस्सों की संख्या 2 के बराबर या उससे अधिक होनी चाहिए");
							}
							regFlag = false;
						} else if (separtedPart != dto.getPropertyDetailsList().size()) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "No of Separated Parts must be equal to no of Properties");
							} else {
								request.setAttribute("ERROR", "हिस्सों की संख्या संपत्तियों की संख्या के बराबर होनी चाहिए।");
							}
							regFlag = false;
						} else if (eForm.getInstDTO().getInstId() == 2105 || eForm.getInstDTO().getInstId() == 2106 || eForm.getInstDTO().getInstId() == 2108 || eForm.getInstDTO().getInstId() == 2184) {
							if (bo.partitionvalidation(dto.getPropertyDetailsList()))// change
							// partition
							// validation
							// here...Roopam
							{
								bo.submitDutyDetails(dutyDTO, eForm.getInstDTO());
								forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
								dto.setActionName(null);
								actionName = "";
							} else {
								if ("en".equalsIgnoreCase(language)) {
									request.setAttribute("ERROR", "If you intend to do the partition of only undiverted agricultural land please refer instrument 'Partition of Agricultural land' ");
								} else {
									request.setAttribute("ERROR", "केवल अव्यपवर्तित कृषि भूमि के विभाजन की अनुमति नहीं है.।एक अलग  प्रकार की संपत्ति जोडे|");
								}
								regFlag = false;
							}
						} else {
							bo.submitDutyDetails(dutyDTO, eForm.getInstDTO());
							forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
							dto.setActionName(null);
							actionName = "";
						}
					} else if (eForm.getInstDTO().getInstId() == 2107) {
						if (dto.getLandRevenueList().size() < 2) {
							if ("en".equalsIgnoreCase(language)) {
								request.setAttribute("ERROR", "No of Separated Parts must be greater than equal to 2");
							} else {
								request.setAttribute("ERROR", "हिस्सों की संख्या 2 के बराबर या उससे अधिक होनी चाहिए");
							}
							regFlag = false;
						} else {
							bo.submitDutyDetails(dutyDTO, eForm.getInstDTO());
							forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
							dto.setActionName(null);
							actionName = "";
						}
					} else {
						bo.submitDutyDetails(dutyDTO, eForm.getInstDTO());
						forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
						dto.setActionName(null);
						actionName = "";
					}
					// }
					if (eForm.getModuleName() != null && eForm.getModuleName().equalsIgnoreCase(CommonConstant.REGISTRATION_DUTY_PAGE) && regFlag == true) {
						if (propValReqFlag.equalsIgnoreCase("Y")) {
							forwardJsp = CommonConstant.FORWARD_REGINIT_DISPLAY;
							request.setAttribute("fromAdju", eForm.getFromAdjudication());
						} else {
							forwardJsp = CommonConstant.FORWARD_NONREGINIT_DISPLAY;
							// request.setAttribute("stampId",
							// dutyDTO.getStampId());
							request.setAttribute("fromAdju", eForm.getFromAdjudication());
						}
						request.setAttribute("eForm", eForm);
						dto.setActionName(null);
						actionName = "";
					}
					if (eForm.getDutyCalculationDTO().getFromModule().equalsIgnoreCase("eStamping") && regFlag == true) {
						request.setAttribute("dutyDTO", eForm.getDutyCalculationDTO());
						request.setAttribute("instDTO", eForm.getInstDTO());
						forwardJsp = CommonConstant.TO_ESTAMP;
						dto.setActionName(null);
						actionName = "";
					}
				}
				dto.setMineralSelected(null);
			}
			if (CommonConstant.CANCEL_ACTION.equals(actionName)) {
				dto.setActionName("");
				forwardJsp = "welcome";
			}
			if (CommonConstant.RESET_PAGE_ACTION.equals(actionName)) {
				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				DutyCalculationDTO dto1 = new DutyCalculationDTO();
				/*
				 * dto.setFirstName(dto1.getFirstName()); dto.setMidName(dto1.getMidName()); dto.setLastName(dto1.getLastName()); dto.setAge(dto1.getAge()); dto.setSex("Male".equals(dto1.getSex()) ? "M" : "Female" .equals(dto1.getSex()) ? "F" : "");
				 */
				dto.setDeedId(dto1.getDeedId());
				dto.setBaseValue("0.0");// double to string
				eForm.setInstDTO(new InstrumentsDTO());
				eForm.setDutyCalculationDTO(dto);
				forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				dto.setActionName(null);
				actionName = "";
			}
			if (CommonConstant.RETURN_FROM_PROP_VAL.equals(actionName)) {
				logger.info("----------- Inside RETURN_FROM_PROP_VAL ----------");
				PropertyValuationDTO propDTO = new PropertyValuationDTO();
				if (request.getAttribute("propDTO") != null) {
					propDTO = (PropertyValuationDTO) request.getAttribute("propDTO");
					// use propDTO for final screen designing of duty
					// calculation involving property details or forwarding to
					// reg init
				}
				DutyCalculationDTO dutyDTO = eForm.getDutyCalculationDTO();
				// dutyDTO.setValTxnId((String)request.getAttribute("valuationId"
				// ));
				// above line commented because valuation id is available in
				// propDTO.
				dutyDTO.setPropDTO(propDTO);
				if (propDTO.getFinalMarketValue() == null || propDTO.getFinalMarketValue().equalsIgnoreCase("") || "3".equalsIgnoreCase(propDTO.getPropertyName()) || "1".equalsIgnoreCase(propDTO.getPropertyName())) {
					propDTO.setFinalMarketValue(propDTO.getPlotMarketValue());
				}
				/*
				 * if(eForm.getModuleName()!=null&&eForm.getModuleName(). equalsIgnoreCase(CommonConstant.REGISTRATION_DUTY_PAGE)) { forwardJsp = CommonConstant.FORWARD_REGINIT_DISPLAY; // this section code will only be executed if selected instrument involved property valuation.
				 * 
				 * 
				 * request.setAttribute("eForm", eForm); //request.setAttribute("stampId", dutyDTO.getStampId()); //request .setAttribute("fromAdju",eForm.getFromAdjudication());
				 * 
				 * dto.setActionName(null); actionName=""; }
				 */
				// else
				{
					if (dutyDTO.getDeedId() == 1054) {
						DutyCalculationDTO ddto = new DutyCalculationDTO();
						ddto.setPropertyName(bo.getPropertyName(propDTO.getPropertyName(), language));
						ddto.setPropertyId(propDTO.getPropertyName());
						ddto.setMarketValue(propDTO.getFinalMarketValue());
						DecimalFormat myformatter1 = new DecimalFormat("###.##");
						ddto.setMarketValueDisplay(myformatter1.format(Double.parseDouble(ddto.getMarketValue())));
						ddto.setLandId(propDTO.getAgriLandTypeId());
						if (propDTO.getAgriTotalUnDivertedArea() != null && !propDTO.getAgriTotalUnDivertedArea().equalsIgnoreCase("")) {
							ddto.setPropArea(Double.parseDouble(propDTO.getAgriTotalUnDivertedArea()));
						}
						if ("3".equals(propDTO.getPropertyName())) {
							if ("firstParty".equalsIgnoreCase(dutyDTO.getExchangeParty())) {
								dutyDTO.setRinPustikaVisible1("Y");
							} else {
								dutyDTO.setRinPustikaVisible2("Y");
							}
							ddto.setValuationId(bo.getValuationIdAGri(propDTO.getValuationId()));
						} else {
							// dutyDTO.setRinPustikaVisible("N");
							ddto.setValuationId(propDTO.getValuationId());
						}
						if ("firstParty".equalsIgnoreCase(dutyDTO.getExchangeParty())) {
							dutyDTO.getExchangeList1().add(ddto);
							dutyDTO.setPartyAdded("firstParty");
							dutyDTO.setExchangeMV1(dutyDTO.getExchangeMV1() + Double.parseDouble(ddto.getMarketValue()));
						} else {
							dutyDTO.getExchangeList2().add(ddto);
							dutyDTO.setPartyAdded("secondParty");
							dutyDTO.setExchangeMV2(dutyDTO.getExchangeMV2() + Double.parseDouble(ddto.getMarketValue()));
						}
						dutyDTO.setValuationIdValidate("true");
						dutyDTO.setAddPropertyFlag("N");
						dutyDTO.setHvValIdFlag("N");
						dutyDTO.setHvValId("");
						dutyDTO.setExchangeParty("");
						eForm.setDutyCalculationDTO(dutyDTO);
						forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
						dto.setActionName(null);
						actionName = "";
					} else if (eForm.getInstDTO().getInstId() == 2105 || eForm.getInstDTO().getInstId() == 2106 || eForm.getInstDTO().getInstId() == 2108 || eForm.getInstDTO().getInstId() == 2184) {
						DutyCalculationDTO ddto = new DutyCalculationDTO();
						ddto.setPropertyName(bo.getPropertyName(propDTO.getPropertyName(), language));
						ddto.setPropertyId(propDTO.getPropertyName());
						ddto.setLandId(propDTO.getAgriLandTypeId());
						if ("3".equals(propDTO.getPropertyName())) {
							dutyDTO.setRinPustikaVisible("Y");
							ddto.setValuationId(bo.getValuationIdAGri(propDTO.getValuationId()));
						} else {
							// dutyDTO.setRinPustikaVisible("N");
							ddto.setValuationId(propDTO.getValuationId());
						}
						ddto.setMarketValue(propDTO.getFinalMarketValue());
						DecimalFormat myformatter1 = new DecimalFormat("###.##");
						ddto.setMarketValueDisplay(myformatter1.format(Double.parseDouble(ddto.getMarketValue())));
						dutyDTO.setPartitionMV(dutyDTO.getPartitionMV() + Double.parseDouble(ddto.getMarketValue()));
						if (dutyDTO.getMaxPartitionMv() <= Double.parseDouble(ddto.getMarketValue())) {
							dutyDTO.setMaxPartitionMv(Double.parseDouble(ddto.getMarketValue()));
						}
						ddto.setAgriApplicableSubclauseId(propDTO.getAgriApplicableSubclauseId());// adde
						// by
						// roopam
						// -14april15
						dutyDTO.getPropertyDetailsList().add(ddto);
						eForm.getInstDTO().setLeaseFreeze("Y");
						dutyDTO.setValuationIdValidate("true");
						dutyDTO.setAddPropertyFlag("N");
						dutyDTO.setHvValIdFlag("N");
						dutyDTO.setHvValId("");
						dutyDTO.setExchangeParty("");
						eForm.setDutyCalculationDTO(dutyDTO);
						forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
						dto.setActionName(null);
						actionName = "";
					} else {
						// use dutyDTO.getPropDTO() for displaying property
						// details on dc final screen.
						DutyCalculationDTO ddto = new DutyCalculationDTO();
						ddto.setPropertyName(bo.getPropertyName(propDTO.getPropertyName(), language));
						ddto.setPropertyId(propDTO.getPropertyName());
						if ("3".equals(propDTO.getPropertyName())) {
							dutyDTO.setRinPustikaVisible("Y");
							ddto.setValuationId(bo.getValuationIdAGri(propDTO.getValuationId()));
						} else {
							// dutyDTO.setRinPustikaVisible("N");
							ddto.setValuationId(propDTO.getValuationId());
						}
						ddto.setMarketValue(propDTO.getFinalMarketValue());
						DecimalFormat myformatter1 = new DecimalFormat("###.##");
						ddto.setMarketValueDisplay(myformatter1.format(Double.parseDouble(ddto.getMarketValue())));
						// ddto.setUserValueMap(dto.getUserValueMap());
						ddto.setUserValueList(dto.getUserValueList());
						// Added by ankit for plant and machinery - start
						double stampDutyOnMovProp = 0;
						double janpadDutyOnMovProp = 0;
						double municipalDutyOnMovProp = 0;
						double upkarDutyOnMovProp = 0;
						double regFeeOnMovProp = 0;
						if (eForm.getInstDTO().getValueOfMovableProp() != null && !eForm.getInstDTO().getValueOfMovableProp().isEmpty()) {
							if (Long.parseLong(eForm.getInstDTO().getValueOfMovableProp()) > 0) {
								stampDutyOnMovProp = Double.parseDouble(new CalculateDuty().calculateStampDutyOnMovableProp(eForm.getInstDTO(), dutyDTO, CommonConstant.STAMP_MOV_PROP));
								janpadDutyOnMovProp = Double.parseDouble(new CalculateDuty().calculateJanpadDutyOnMovableProp(eForm.getInstDTO(), dutyDTO, CommonConstant.JANPAD_MOV_PROP));
								upkarDutyOnMovProp = new CalculateDuty().calculateUpkarDutyOnMovableProp(eForm.getInstDTO(), dutyDTO, CommonConstant.UPKAR_MOV_PROP, stampDutyOnMovProp);
								// municipalDutyOnMovProp =
								// Double.parseDouble(new CalculateDuty().
								// calculateMunicipalDutyOnMovableProp
								// (eForm.getInstDTO(), dutyDTO,
								// CommonConstant.MUNICIPAL_MOV_PROP));
								municipalDutyOnMovProp = Double.parseDouble(new CalculateDuty().calculateMunicipalDutyOnMovableProp(eForm.getInstDTO(), dutyDTO, ddto, CommonConstant.MUNICIPAL_MOV_PROP));
								regFeeOnMovProp = Double.parseDouble(new CalculateDuty().calculateRegFeeOnMovableProp(eForm.getInstDTO(), dutyDTO, CommonConstant.REG_FEE_MOV_PROP));
								ddto.setStampDutyOnMovProp(stampDutyOnMovProp);
								ddto.setJanpadDutyOnMovProp(janpadDutyOnMovProp);
								ddto.setUpkarDutyOnMovProp(upkarDutyOnMovProp);
								ddto.setMunicipalDutyOnMovProp(municipalDutyOnMovProp);
								ddto.setRegFeeOnMovProp(regFeeOnMovProp);
								ddto.setValueOfMovableProp(eForm.getInstDTO().getValueOfMovableProp());
								dutyDTO.setTotalStampDutyOnMovProp(dutyDTO.getTotalStampDutyOnMovProp() + stampDutyOnMovProp);
								dutyDTO.setTotalJanpadDutyOnMovProp(dutyDTO.getTotalJanpadDutyOnMovProp() + janpadDutyOnMovProp);
								dutyDTO.setTotalUpkarDutyOnMovProp(dutyDTO.getTotalUpkarDutyOnMovProp() + upkarDutyOnMovProp);
								dutyDTO.setTotalMunicipalDutyOnMovProp(dutyDTO.getTotalMunicipalDutyOnMovProp() + municipalDutyOnMovProp);
								dutyDTO.setTotalRegFeeOnMovProp(dutyDTO.getTotalRegFeeOnMovProp() + regFeeOnMovProp);
							}
						}
						// Added by ankit for plant and machinery - end
						String stampDuty = new CalculateDuty().calculateStampDuty(eForm.getInstDTO(), dutyDTO, ddto);
						double stamp = Double.parseDouble(stampDuty);
						// change in stamp duty by ankit 2275
						/*
						 * if (eForm.getInstDTO().getInstId() == 2275) { if (stamp > 1000) { stamp = 1000; stampDuty = "1000"; } }
						 */
						ddto.setStampDuty(Double.parseDouble(stampDuty));
						// String regFee=new
						// CalculateRegFee().calculateRegFee(eForm.getInstDTO(),
						// dutyDTO, ddto);
						double upkarDuty = new CalculateDuty().calculateUpkar(eForm.getInstDTO().getInstId(), Double.parseDouble(stampDuty));
						ddto.setUpkarDuty(upkarDuty);
						double nagarpalika = new CalculateDuty().calculateMuncipalDuty(eForm.getInstDTO(), dutyDTO, ddto);
						// if(nagarpalika<=stamp)
						{
							ddto.setNagarPalikaDuty(nagarpalika);
						}
						/*
						 * else { ddto.setNagarPalikaDuty(stamp); }
						 */
						double panchayat = new CalculateDuty().calculateJanPadDuty(eForm.getInstDTO(), dutyDTO, ddto);
						// if(panchayat<=stamp)
						{
							ddto.setPanchayatDuty(panchayat);
						}
						/*
						 * else { ddto.setPanchayatDuty(stamp); }
						 */
						double total = Double.parseDouble(stampDuty) + upkarDuty + nagarpalika + panchayat;
						ddto.setTotal(total);
						String regFee = new CalculateRegFee().calculateRegFee(eForm.getInstDTO(), dutyDTO, ddto);
						double paidRegFee = 0;
						double paidStampduty = 0;
						if (dutyDTO.getAlreadyPaidRegFee() != null && !dutyDTO.getAlreadyPaidRegFee().equalsIgnoreCase("")) {
							paidRegFee = Double.parseDouble(dutyDTO.getAlreadyPaidRegFee());
						}
						if (dutyDTO.getAlreadyPaidStampDuty() != null && !dutyDTO.getAlreadyPaidStampDuty().equalsIgnoreCase("")) {
							paidStampduty = Double.parseDouble(dutyDTO.getAlreadyPaidStampDuty());
						}
						if (((Double.parseDouble(regFee)) - paidRegFee) < 0) {
							ddto.setPayableRegFee(0);
						} else {
							ddto.setPayableRegFee((Double.parseDouble(regFee)) - paidRegFee);
						}
						if ((total - paidStampduty) < 0) {
							ddto.setPayableStampDuty(0);
						} else {
							ddto.setPayableStampDuty(total - paidStampduty);
						}
						DecimalFormat myformatter = new DecimalFormat("###.##");
						ddto.setPremium(dutyDTO.getPremium());
						ddto.setAdditionalPremium(dutyDTO.getAdditionalPremium());
						ddto.setOtherCharges(dutyDTO.getOtherCharges());
						ddto.setDevelopmentCharges(dutyDTO.getDevelopmentCharges());
						ddto.setRentArrayList(dutyDTO.getRentArrayList());
						ddto.setAlreadyPaidRegFee(dutyDTO.getAlreadyPaidRegFee());
						ddto.setAlreadyPaidStampDuty(dutyDTO.getAlreadyPaidStampDuty());
						ddto.setTotal(total);
						dto.setPaidStamp(paidStampduty);
						ddto.setPaidReg(paidRegFee);
						ddto.setRegFee(Double.parseDouble(regFee));
						ddto.setDashReg((myformatter.format(ddto.getRegFee())));
						ddto.setDashSatmp(myformatter.format(ddto.getStampDuty()));
						ddto.setDashMunicipal(myformatter.format(ddto.getNagarPalikaDuty()));
						ddto.setDashJanpad(myformatter.format(ddto.getPanchayatDuty()));
						ddto.setDashUpkar(myformatter.format(ddto.getUpkarDuty()));
						ddto.setDashTotal(myformatter.format(ddto.getTotal()));
						ddto.setDashPayTotal(myformatter.format(ddto.getPayableStampDuty()));
						ddto.setDashPayReg(myformatter.format(ddto.getPayableRegFee()));
						ddto.setGovValue(dutyDTO.getGovValue());
						double govtValue = 0;
						if (dutyDTO.getGovValue() != null && !dutyDTO.getGovValue().equalsIgnoreCase("")) {
							govtValue = Double.parseDouble(dutyDTO.getGovValue());
						}
						dutyDTO.setTotalGovValue(dutyDTO.getTotalGovValue() + govtValue);
						// dutyDTO.setTotalStampDuty(dutyDTO.getTotalStampDuty()
						// + Double.parseDouble(stampDuty));
						dutyDTO.setTotalregFee(dutyDTO.getTotalregFee() + Double.parseDouble(regFee));
						// dutyDTO.setTotalUpkar(dutyDTO.getTotalUpkar() +
						// upkarDuty);
						dutyDTO.setTotalNagarPalika(dutyDTO.getTotalNagarPalika() + nagarpalika);
						dutyDTO.setTotalPanchyat(dutyDTO.getTotalPanchyat() + panchayat);
						dutyDTO.setEntireTotal(dutyDTO.getEntireTotal() + total);
						dutyDTO.setTotalPaidStamp(dutyDTO.getTotalPaidStamp() + paidStampduty);
						dutyDTO.setTotalPaidReg(dutyDTO.getTotalPaidReg() + paidRegFee);
						// change in reg fee by ankit 2275
						System.out.println("Action 2275 change inst id " + eForm.getInstDTO().getInstId());
						if (eForm.getInstDTO().getInstId() == 2275) {
							dutyDTO.setTotalPayableReg(dutyDTO.getTotalPayableReg() + ddto.getPayableRegFee());
							if (dutyDTO.getTotalPayableReg() > 100) {
								dutyDTO.setTotalPayableReg(100.0);
							}
						} else {
							dutyDTO.setTotalPayableReg(dutyDTO.getTotalPayableReg() + ddto.getPayableRegFee());
						}
						// dutyDTO.setTotalPayableReg(dutyDTO.getTotalPayableReg(
						// ) + ddto.getPayableRegFee());
						// change in stamp duty by ankit 2275
						if (eForm.getInstDTO().getInstId() == 2275) {
							dutyDTO.setTotalStampDuty(dutyDTO.getTotalStampDuty() + Double.parseDouble(stampDuty));
							dutyDTO.setTotalUpkar(dutyDTO.getTotalUpkar() + upkarDuty);
							dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getPayableStampDuty());
							if (dutyDTO.getTotalStampDuty() >= 1000) {
								dutyDTO.setTotalStampDuty(1000);
								dutyDTO.setTotalUpkar(100);// 100
								dutyDTO.setTotalPayableStamp(dutyDTO.getTotalStampDuty() + dutyDTO.getTotalUpkar() + dutyDTO.getTotalNagarPalika() + dutyDTO.getTotalPanchyat());
							}
						} else {
							dutyDTO.setTotalStampDuty(dutyDTO.getTotalStampDuty() + Double.parseDouble(stampDuty));
							dutyDTO.setTotalUpkar(dutyDTO.getTotalUpkar() + upkarDuty);
							dutyDTO.setTotalPayableStamp(dutyDTO.getTotalPayableStamp() + ddto.getPayableStampDuty());
						}
						// dutyDTO.setTotalPayableStamp(dutyDTO.
						// getTotalPayableStamp() + ddto.getPayableStampDuty());
						dutyDTO.getPropertyDetailsList().add(ddto);
						dutyDTO.setValuationIdValidate("true");
						dutyDTO.setAddPropertyFlag("N");
						dutyDTO.setHvValIdFlag("N");
						dutyDTO.setHvValId("");
						dutyDTO.setActionName(null);
						eForm.setDutyCalculationDTO(dutyDTO);
						forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
						dto.setActionName(null);
						actionName = "";
					}
				}
			}
		}
		return mapping.findForward(forwardJsp);
	}
}
