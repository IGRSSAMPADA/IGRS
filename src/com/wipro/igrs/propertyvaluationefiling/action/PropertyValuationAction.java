package com.wipro.igrs.propertyvaluationefiling.action;

/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  PropertyValuationAction.java 
 * Author      :  Madan Mohan 
 * Description :   
*/


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.commonEfiling.IGRSCommon;
import com.wipro.igrs.propertyvaluationefiling.bo.PropertyValuationBO;
import com.wipro.igrs.propertyvaluationefiling.constant.CommonConstant;
import com.wipro.igrs.propertyvaluationefiling.dto.AreaDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.DistrictDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.FloorCalcTypeDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.InstrumentsDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.MahallaDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.MapDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.MunicipalDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.PropertyDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.PropertyValuationDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.TehsilDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.UsePlotDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.WardDTO;
import com.wipro.igrs.propertyvaluationefiling.form.PropertyValuationForm;
import com.wipro.igrs.propertyvaluationefiling.rule.PropertyValuationRule;
import com.wipro.igrs.propertyvaluationefiling.util.PropertiesFileReader;
import com.wipro.igrs.reginit.form.RegCommonForm;

/**
 * 
 * @author Madan Mohan
 * 
 */
public class PropertyValuationAction extends BaseAction {

	/**
	 * @see forwardJsp is used for redirecting
	 */
	private String forwardJsp;
	/**
	 * @author Madan Mohan
	 */
	private Logger logger = (Logger) Logger
			.getLogger(PropertyValuationAction.class);
	private int count = 0;
	private int count2 = 0;
	private HashMap map = null;
	private HashMap map1 = null;
	private int floorCount = 0;
	boolean bol = true;

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @exception Exception
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		if (logger.isDebugEnabled()) {
			System.out.println("execute(ActionMapping, ActionForm, "
					+ "HttpServletRequest, HttpServletResponse) -" + " start");
		}
		PropertiesFileReader pr = PropertiesFileReader
				.getInstance("resources.propertyvaluation");
		if (form != null) {

			logger.debug("request attribute:-"+request.getAttribute("multipleProps"));
			String languageLocale="hi";
			if(session.getAttribute("languageLocale")!=null){
				languageLocale=(String)session.getAttribute("languageLocale");
			}
	
			//following added by roopam
			if(request.getAttribute("regForm")!=null)
			{
			session.setAttribute("regFormProp",request.getAttribute("regForm"));
			}
			//end of addition by roopam		
			IGRSCommon common = new IGRSCommon();
			DecimalFormat myformatter=new DecimalFormat("############");
			PropertyValuationForm eForm = (PropertyValuationForm) form;
			PropertyValuationBO bo = new PropertyValuationBO();
			String formName = eForm.getFormName();
			String actionName = eForm.getActionName();
			String userId=(String)session.getAttribute("UserId");	
			String guidelineValue=null;
			eForm.getPropertyDTO().setUserId(userId);
			eForm.setBuildingShow("null");
			System.out.println("User Id"+userId);
			eForm.getPropertyDTO().setCurrentYear(bo.getCurrentYear());
			
			//FOLLOWING COMMENTED BY ROOPAM--- 12 JULY 2013 FOR IMPLEMENTING MULTIPLE PROPERTIES IN REG INIT DASHBOARD			
			/*if(request.getAttribute("multipleProps")!=null){
				if(request.getAttribute("multipleProps").toString().equalsIgnoreCase("multipleProps")){
					eForm.setIsMultipleProperty(1);
					RegCommonForm regForm=(RegCommonForm)form;
					regForm.setIsMultiplePropsFlag(1);
				}
				
				
			}*/
	if(request.getAttribute("multipleAdjuProps")!=null){
				if(request.getAttribute("multipleAdjuProps").toString().equalsIgnoreCase("multipleAdjuProps")){
					eForm.setIsMultiplePropertyAdju(1);
				}
				
				
			}	
			if (eForm.getDistList().isEmpty()) {
				eForm.setDistList(bo.getDistrict(languageLocale));
				System.out.println("district type:" + eForm.getDistList());
			}

			if (eForm.getAreaTypeList().isEmpty()) {
				eForm.setAreaTypeList(bo.getAreaType(languageLocale));
				System.out.println("area type:" + eForm.getAreaTypeList());
			}
			if (eForm.getMunicipalList().isEmpty()) {
				eForm.setMunicipalList(bo.getMunicipalBody(languageLocale));
				System.out.println("municipal type:" + eForm.getMunicipalList());
			}
			if (eForm.getPropertyType().isEmpty()) {
				eForm.setPropertyType(bo.getPropertyType(languageLocale));
				System.out.println("municipal type:" + eForm.getPropertyType());
			}
		

			String page = (String) request.getParameter("page");
			String modName = (String) request.getParameter("modName");
			String fnName = (String) request.getParameter("fnName");

			if (modName != null && fnName != null) {
				//session.setAttribute("modName", modName);
				//session.setAttribute("fnName", fnName);
				if(modName.equalsIgnoreCase("Property Valuation")){
					if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
					session.setAttribute("modName", CommonConstant.PV_MODNAME_ENGLISH);
					session.setAttribute("fnName", CommonConstant.PV_FUNCTION_ENGLISH);
					}else{
						session.setAttribute("modName", CommonConstant.PV_MODNAME_HINDI);
						session.setAttribute("fnName", CommonConstant.PV_FUNCTION_HINDI);
					}
					eForm.setIsMultipleProperty(0);
					eForm.setIsMultiplePropertyAdju(0);
					eForm.setFromAdjudication(0);
				}

			}
			System.out.println("formName:-" + formName);
			System.out.println("actionName:-" + actionName);
			System.out.println("page:-" + page);
			System.out.println("modName:-" + page);
			System.out.println("fnName:-" + page);
			
			

			if (CommonConstant.VALUATION_FIRST_PAGE.equals(page)) {

				// change 1
				System.out.println("Inside first page");
				eForm.setDistList(new ArrayList());
				eForm.setMahallaList(new ArrayList());
				eForm.setTehsilList(new ArrayList());
				eForm.setWardList(new ArrayList());
				eForm.setAreaTypeList(new ArrayList());
				eForm.setPropertyType(new ArrayList());
				eForm.setMunicipalList(new ArrayList());		
				eForm.setSubClauseList(new ArrayList());
				eForm.getPropertyDTO().setFirstName("");
				eForm.getPropertyDTO().setMiddleName("");
				eForm.getPropertyDTO().setLastName("");
				eForm.getPropertyDTO().setSex("");
				eForm.getPropertyDTO().setDob("");
				eForm.getPropertyDTO().setDobDay("");
				eForm.getPropertyDTO().setDobMonth("");
				eForm.getPropertyDTO().setDobYear("");
				eForm.setModuleName(null);
				eForm.getPropertyDTO().setAddParty(0);
				eForm.getPropertyDTO().setHdnSubClause(null);
				eForm.getPropertyDTO().setAddAnotherParty(0);
				eForm.getPropertyDTO().setMarketValue(0.0);
				eForm.setAddPartyLabel(null);
				eForm.getPropertyDTO().setPartyLabel(null);
				eForm.getPropertyDTO().setBiggestValue(0.0);
				eForm.getPropertyDTO().setSmallestValue(0.0);
				eForm.setMapParty(new HashMap());
				System.out.println("Inside first 2");
				// eForm.getPropertyDTO().setAge(0);
				eForm.getPropertyDTO().setPatwariStatus("");
				actionName = null;
				forwardJsp = CommonConstant.VALUATION_FIRST_PAGE;
				System.out.println("After forward jsp");
				
				eForm.setPreviousFlag(0);
				eForm.setParty2Added(0);
				eForm.getPropertyDTO().setHdnExtSubClause("");
			}
			
			if (CommonConstant.REGISTRATION_VALUATION.equals(page)) {
				
				//check for adjudication here
				
				if(request.getParameter("fromAdju")!=null)
				{
					if(request.getParameter("fromAdju").toString().equalsIgnoreCase("Y")){
						
						eForm.setFromAdjudication(1);
						if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
							session.setAttribute("modName", CommonConstant.MODNAME_ENGLISH);
							session.setAttribute("fnName", CommonConstant.FUNCTION_ADJU_ENGLISH);
							}else{
								session.setAttribute("modName", CommonConstant.MODNAME_HINDI);
								session.setAttribute("fnName", CommonConstant.FUNCTION_ADJU_HINDI);
							}
						
					}else{
						if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
							session.setAttribute("modName", CommonConstant.MODNAME_ENGLISH);
							session.setAttribute("fnName", CommonConstant.FUNCTION_REGINIT_ENGLISH);
							}else{
								session.setAttribute("modName", CommonConstant.MODNAME_HINDI);
								session.setAttribute("fnName", CommonConstant.FUNCTION_REGINIT_HINDI);
							}
					}
				}else{
					if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
						session.setAttribute("modName", CommonConstant.MODNAME_ENGLISH);
						session.setAttribute("fnName", CommonConstant.FUNCTION_REGINIT_ENGLISH);
						}else{
							session.setAttribute("modName", CommonConstant.MODNAME_HINDI);
							session.setAttribute("fnName", CommonConstant.FUNCTION_REGINIT_HINDI);
						}
				}
				/*if(request.getAttribute("fromAdju")!=null)
				{
					if(request.getAttribute("fromAdju").toString().equalsIgnoreCase("Y")){
						
						eForm.setFromAdjudication(1);
						
					}
				}*/
				
				/*if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
				session.setAttribute("modName", CommonConstant.MODNAME_ENGLISH);
				session.setAttribute("fnName", CommonConstant.FUNCTION_REGINIT_ENGLISH);
				}else{
					session.setAttribute("modName", CommonConstant.MODNAME_HINDI);
					session.setAttribute("fnName", CommonConstant.FUNCTION_REGINIT_HINDI);
				}*/
				
				
				formName="valuationHome";
				actionName="nextPageLand";
			  	eForm.getDutyDTO().setDeedId(0);
				eForm.getPropertyDTO().setDistrictID(0);
				eForm.setMahallaList(new ArrayList());
				eForm.setTehsilList(new ArrayList());
				eForm.setWardList(new ArrayList());
				eForm.setAreaTypeList(new ArrayList());
				eForm.setPropertyType(new ArrayList());
				eForm.setMunicipalList(new ArrayList());
				eForm.setSubClauseList(new ArrayList());
				eForm.getPropertyDTO().setPatwariStatus("");
				eForm.getPropertyDTO().setWardId(0);
				eForm.getDutyDTO().setDutyAlreadyPaid(0.0);
				eForm.getDutyDTO().setRegPaid(0.0);	
				eForm.getDutyDTO().setShareValue(0.0);
				eForm.getPropertyDTO().setAddParty(0);
				eForm.getPropertyDTO().setAddAnotherParty(0);
				eForm.setAddPartyLabel(null);
				eForm.getPropertyDTO().setPartyLabel(null);
				eForm.getPropertyDTO().setBiggestValue(0.0);
				eForm.getPropertyDTO().setSmallestValue(0.0);
				eForm.setMapParty(new HashMap());
				System.out.println("Inside first 2");
				// eForm.getPropertyDTO().setAge(0);
				eForm.getPropertyDTO().setPatwariStatus("");
				//FOLLOWING ADDED BY ROOPAM--- 12 JULY 2013 FOR IMPLEMENTING MULTIPLE PROPERTIES IN REG INIT DASHBOARD
				eForm.setIsMultipleProperty(0);
				eForm.setModuleName(CommonConstant.REGISTRATION_VALUATION);
				
				eForm.setPreviousFlag(0);
				eForm.setParty2Added(0);
								
			}
			//FOLLOWING ADDED BY ROOPAM--- 12 JULY 2013 FOR IMPLEMENTING MULTIPLE PROPERTIES IN REG INIT DASHBOARD
			if(request.getAttribute("multipleProps")!=null){
				if(request.getAttribute("multipleProps").toString().equalsIgnoreCase("multipleProps")){
					eForm.setIsMultipleProperty(1);
					/*RegCommonForm regForm=(RegCommonForm)form;
					regForm.setIsMultiplePropsFlag(1);*/
				}
				
				
			}
			//END OF ADDITION BY ROOPAM 12 JULY 2013 FOR IMPLEMENTING MULTIPLE PROPERTIES IN REG INIT DASHBOARD
			//For adjudication module
			
if (CommonConstant.ADJUDICATION_VALUATION.equals(page)) {
				
				formName="valuationHome";
				actionName="nextPageLand";
			  	eForm.getDutyDTO().setDeedId(0);
				eForm.getPropertyDTO().setDistrictID(0);
				eForm.setMahallaList(new ArrayList());
				eForm.setTehsilList(new ArrayList());
				eForm.setWardList(new ArrayList());
				eForm.setAreaTypeList(new ArrayList());
				eForm.setPropertyType(new ArrayList());
				eForm.setMunicipalList(new ArrayList());
				eForm.setSubClauseList(new ArrayList());
				eForm.getPropertyDTO().setPatwariStatus("");
				eForm.getPropertyDTO().setWardId(0);
				eForm.getDutyDTO().setDutyAlreadyPaid(0.0);
				eForm.getDutyDTO().setRegPaid(0.0);	
				eForm.getDutyDTO().setShareValue(0.0);
				eForm.setAddPartyLabel(null);
				eForm.setModuleName(CommonConstant.ADJUDICATION_VALUATION);
				
				eForm.setPreviousFlag(0);
				eForm.setParty2Added(0);
				
				if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
					session.setAttribute("modName", CommonConstant.MODNAME_ENGLISH);
					session.setAttribute("fnName", CommonConstant.FUNCTION_ADJU_ENGLISH);
					}else{
						session.setAttribute("modName", CommonConstant.MODNAME_HINDI);
						session.setAttribute("fnName", CommonConstant.FUNCTION_ADJU_HINDI);
					}
								
			}

			// Start PropertyValuation1.jsp
			if (CommonConstant.VALUATION_FIRST_PAGE.equals(formName)) {
				PropertyValuationDTO dto = eForm.getPropertyDTO();

				if (CommonConstant.CANCEL_ACTION.equals(actionName)) {

					session.removeAttribute("modName");
					session.removeAttribute("fnName");
					forwardJsp = "welcome";
				}

				if (CommonConstant.HOME_RESET_ACTION.equals(actionName)) {

					eForm.getPropertyDTO().setFirstName("");
					eForm.getPropertyDTO().setMiddleName("");
					eForm.getPropertyDTO().setLastName("");
					eForm.getPropertyDTO().setSex("");
					eForm.getPropertyDTO().setDob("");
					eForm.getPropertyDTO().setDobDay("");
					eForm.getPropertyDTO().setDobMonth("");
					eForm.getPropertyDTO().setDobYear("");
					// eForm.getPropertyDTO().setAge(0);
					forwardJsp = CommonConstant.VALUATION_FIRST_PAGE;
				}

				if (CommonConstant.HOME_NEXT_ACTION.equals(actionName)) {  //copy
					
					if(eForm.getPreviousFlag()==0){
					eForm.getPropertyDTO().setDistrictID(0);
					eForm.setMahallaList(new ArrayList());
					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setAreaTypeList(new ArrayList());
					eForm.setPropertyType(new ArrayList());
					eForm.setMunicipalList(new ArrayList());
					eForm.getPropertyDTO().setPatwariStatus("");
					eForm.getPropertyDTO().setWardId(0);
					}
					System.out.println("Age is " + dto.getAge());

					System.out.println("Name  is " + dto.getFirstName());
				    int deedId=eForm.getDutyDTO().getDeedId();
				    eForm.getDutyDTO().setDeedId(deedId);

					PropertyValuationRule rule = new PropertyValuationRule();

					ArrayList errorList = rule.validatePropertyPage(eForm);
					/*
					 * dto.setConsiderAmt(0.0); dto.setTotalSqMeter(0.0);
					 * dto.setConstructedArea(0.0);
					 */
					map = new HashMap();

					rule.setError(false);
					if (rule.isError()) {
						// Write the session for error
						request.setAttribute(
								CommonConstant.PROPERTY_ERROR_FLAG, "true");
						request.setAttribute(
								CommonConstant.PROPERTY_ERROR_LIST, errorList);
					} else {

						System.out.println("Age is " + dto.getAge());

						System.out.println("Name  is " + dto.getFirstName());

						String genderF;
						String genderM;
						
						if(languageLocale.equalsIgnoreCase(CommonConstant.LANGUAGE_ENGLISH)){
							genderF=CommonConstant.FEMALE_ENGLISH;
							genderM=CommonConstant.MALE_ENGLISH;
						}else{
							genderF=CommonConstant.FEMALE_HINDI;
							genderM=CommonConstant.MALE_HINDI;
						}
						
						String sex = "M".equals(dto.getSex()) ? genderM
								: genderF;
						dto.setChkSex(sex);

					//	session.setAttribute(
							//	CommonConstant.PROPERTY_DTO_SESSION, dto);

						System.out.println(" my next page is "
								+ CommonConstant.VALUATION_NEW_PAGE);
						count = 0;
						dto.setAddMoreCounter(count);
						forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
						// 1=1 is for Testing-------->

					}

					System.out.println("formName:-" + formName);
				}
			}

			if (CommonConstant.VALUATION_NEW_PAGE.equals(formName)) {
				PropertyValuationDTO dto = eForm.getPropertyDTO();
				DistrictDTO distDTO = eForm.getDistDTO();
				TehsilDTO tehsilDTO = eForm.getTehsilDTO();
				AreaDTO areaDTO = eForm.getAreaDTO();
				WardDTO wardDTO = eForm.getWardDTO();
				MahallaDTO mahallaDTO = eForm.getMahallaDTO();
				PropertyDTO propDTO = eForm.getPropDTO();
				MunicipalDTO municipalDTO = eForm.getMunicipalDTO();
				
				System.out.println("Name  value is " + eForm.getPropertyDTO().getFirstName());
				eForm.getPropertyDTO().setFirstName(eForm.getPropertyDTO().getFirstName());
				System.out.println("Name  value is " + eForm.getPropertyDTO().getFirstName());

				System.out.println("Inside new form-" + formName);
				request.setAttribute(CommonConstant.WARD_PATWARI_REQUEST, dto
						.getPatwariStatus());
				System.out.println("formName:-" + formName);
				System.out.println("actionName:-" + actionName);
				if (CommonConstant.CANCEL_ACTION.equals(actionName)) {
					System.out.println("Inside cancel");
					session.removeAttribute("modName");
					session.removeAttribute("fnName");
					forwardJsp = "welcome";
				}
				if (CommonConstant.PROPERTY_VALUATION_PREV_HOME
						.equals(actionName)) {
					System.out.println("Inside previous");
					eForm.setPreviousFlag(1);
					forwardJsp = CommonConstant.VALUATION_FIRST_PAGE;
				}

				if (CommonConstant.HOME_RESET_ACTION.equals(actionName)) {
					System.out.println("Inside RESET");
					eForm.getPropertyDTO().setPatwariStatus("");
					eForm.setMahallaList(new ArrayList());
					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setAreaTypeList(new ArrayList());
					eForm.setPropertyType(new ArrayList());
					eForm.setMunicipalList(new ArrayList());
					eForm.getPropertyDTO().setDistrictID(0);
					forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
				}

				if (CommonConstant.DISTRICT_ACTION.equals(actionName)) {
					System.out.println("District Id:-"
					+ eForm.getPropertyDTO().getDistrictID());
					eForm.setMahallaList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.getPropertyDTO().setPatwariStatus("");
					eForm.getPropertyDTO().setAreaId(0);
					eForm.getPropertyDTO().setAreaId(0);
					eForm.getPropertyDTO().setPropertyTypeID(0);
					eForm.getPropertyDTO().setMunicipalBodyID(0);
					String paramA = "";
					int param = eForm.getPropertyDTO().getDistrictID();
					System.out.println(" DISTRICT NAME IS:-" + distDTO.getDistrict());
					System.out.println("ACTION DISTRICT:-" + actionName);
					System.out.println("selected district:-" + param);
					/*
					 * if(param.length>0) { paramA=param[0]; }
					 */
					eForm.setTehsilList(bo.getTehsil(param,languageLocale));
					try{
					String guidelineduration=bo.getGuidelineDuration(param);
					
					eForm.getPropertyDTO().setDurationTO(guidelineduration);
						}
				catch(Exception e)
				{
					System.out.println(e);
				}
					System.out.println("Duration to from"+eForm.getPropertyDTO().getDurationTO());
					System.out.println("Tehsil Id :" + eForm.getTehsilList());
					forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
				}
				
				
				if (CommonConstant.THESIL_ACTION.equals(actionName)) {
					eForm.setWardList(new ArrayList());
					eForm.setMahallaList(new ArrayList());
					eForm.getPropertyDTO().setAreaId(0);
					eForm.getPropertyDTO().setPatwariStatus("");
					eForm.getPropertyDTO().setPropertyTypeID(0);
					eForm.getPropertyDTO().setMunicipalBodyID(0);
					forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
				}

				
				 if(CommonConstant.AREA_TYPE_ACTION.equals(actionName)) {
					 eForm.setWardList(new ArrayList());
					 eForm.setMahallaList(new ArrayList());
					 eForm.getPropertyDTO().setPatwariStatus("");
						eForm.getPropertyDTO().setPropertyTypeID(0);
						eForm.getPropertyDTO().setMunicipalBodyID(0);
						forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
				 }
				 

				if (CommonConstant.WARD_ACTION.equals(actionName)) {
					System.out.println("ACTION WARD:-" + actionName);

					int paramTeh = dto.getTehsilID();
					System.out.println(" Tehsil name is :-" + tehsilDTO.getTehsil());

					int paramA = dto.getAreaId();
					System.out.println(" Area name is :-" + areaDTO.getAreaType());

					int wardpatwaritypeid;
					
					System.out.println(" WARD STATUS is :-"+dto
							.getPatwariStatus());
					// IF w THEN wardpatwaritypeid=1 ELSE wardpatwaritypeid=2
					
					System.out.println(" WARD STATUS is :-" + pr.getValue("ward.number.label"));
					
					if (CommonConstant.WARD_STATUS.equals(dto
							.getPatwariStatus())) {
						eForm.getPropertyDTO().setWardLabel(
								pr.getValue("ward.number.label"));

						wardpatwaritypeid = 1;

						eForm.getPropertyDTO().setMahallaLabel(
								pr.getValue("mahalla.number.label"));
						eForm.setMahallaList(new ArrayList());
						System.out.println("getting ward id list");
						eForm.setWardList(bo.getWard(paramTeh, paramA,
								wardpatwaritypeid,languageLocale));

					} else if (CommonConstant.PATWARI_STATUS.equals(dto
							.getPatwariStatus())) {
						eForm.getPropertyDTO().setWardLabel(
								pr.getValue("patwari.number.label"));
						wardpatwaritypeid = 2;
						eForm.setMahallaList(new ArrayList());
						eForm.getPropertyDTO().setMahallaLabel(
								pr.getValue("gram.number.label"));
						eForm.setWardList(bo.getWard(paramTeh, paramA,
								wardpatwaritypeid,languageLocale));
					}
					
				}
				if (CommonConstant.WARD_PATWARI_ACTION.equals(actionName)) {

					int wardId = eForm.getPropertyDTO().getWardId();
					System.out.println(" Ward name is :-" + wardDTO.getWard());
					System.out.println("getting mahalla list");
					eForm.setMahallaList(bo.getMahalla(wardId,languageLocale));
					System.out.println(" after getting mahalla list");
					forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
				}

				if (CommonConstant.PROPERTY_TYPE_ACTION.equals(actionName)) {
					int propertyId = dto.getPropertyTypeID();
					String buildingId = pr.getValue(CommonConstant.BUILDING_ID);
		
					if (buildingId.equals(propertyId)) {
						eForm.setBuildingShow(buildingId);
					} else {
						eForm.setBuildingShow("null");
					}

				}

				
			}
			if (CommonConstant.HOME_NEW_ACTION.equals(actionName)) {   //copy
				PropertyValuationDTO dto = eForm.getPropertyDTO();
				int propertyId = dto.getPropertyTypeID();
				int deedId=eForm.getDutyDTO().getDeedId();
				if(eForm.getPreviousFlag()==0){
				dto.setConsiderAmt("0.0");
				dto.setTotalSqMeter(0.0);
				dto.setConstructedArea(0.0);
				dto.setUsePlotF("");
				dto.setCeilingTypeId("");
				eForm.getPropertyDTO().setFirstName(eForm.getPropertyDTO().getFirstName());
				//eForm.setPlotResidentList(bo.getUsePlot(propertyId));
				eForm.setSubClauseList(new ArrayList());
				eForm.setCeilingList(new ArrayList());
				eForm.setFloorList(new ArrayList());
				dto.setUsePlotId("");
				dto.setLandMeterId("");
				
				//for building
				eForm.setMapBuilding(new HashMap());
				eForm.setListBuilding(new ArrayList());
				eForm.getFloorCalcTypeDTO().setFloorCalcId(0);
				eForm.setHdnfloorCalcType(null);
				bol = false;
				
				//for agri land
				eForm.setLandTypeList(new ArrayList());
				}
				eForm.setPlotResidentList(bo.getUsePlot(propertyId,languageLocale));
				eForm.getDutyDTO().setDeedId(deedId);

				System.out.println("propertyId in Property Valuation:-" + propertyId);
				System.out.println("bo.getPropertyID(CommonConstant.PLOT_ID) -->"
						+ bo.getPropertyID(CommonConstant.PLOT_ID));
				System.out.println("bo.getPropertyID(CommonConstant.BUILDING_ID)-->"
						+ bo.getPropertyID(CommonConstant.BUILDING_ID));

				if (propertyId == (CommonConstant.PLOT_ID_NEW)) {
					logger
							.debug("propertyId.equals(bo.getPropertyID(CommonConstant.PLOT_ID)");
					eForm.setPropertyTypeName(CommonConstant.PROPERTY_TYPE_PLOT);
					forwardJsp = CommonConstant.VALUATION_PLOT_PAGE;

					System.out.println("Forwarding to Plot page");
				} else if (propertyId == (CommonConstant.BUILDING_ID_NEW)) {
					logger
							.debug("propertyId.equals(bo.getPropertyID(CommonConstant.BUILDING_ID)");
					eForm.setPropertyTypeName(CommonConstant.PROPERTY_TYPE_BUILDING);
					forwardJsp = CommonConstant.VALUATION_BUILDING_PAGE;
					/*eForm.setMapBuilding(new HashMap());
					eForm.setListBuilding(new ArrayList());
					eForm.getFloorCalcTypeDTO().setFloorCalcId(0);
					eForm.setHdnfloorCalcType(null);
					bol = false;*/
		    		System.out.println("Forwarding to Building page");
				} else if (propertyId == CommonConstant.AGRICULTURE_LAND_ID_NEW) {
					logger
							.debug("propertyId.equals(bo.getPropertyID(CommonConstant.AGRICULTURELAND_ID)");
					eForm.setPropertyTypeName(CommonConstant.PROPERTY_TYPE_AGRI_LAND);
					//eForm.setLandTypeList(new ArrayList());
					forwardJsp = CommonConstant.VALUATION_AGRI_PAGE;
					System.out.println("Forwarding to Agriculture Land" + " page");
				}

			}

			// End PropertyValuation1.jsp


			// Start PropertyValuationForAgriculturalLand1.jsp
			System.out.println("formName:-" + formName);
			System.out.println("actionName:-" + actionName);

			if (CommonConstant.VALUATION_AGRI_PAGE.equals(formName)) {
					if (CommonConstant.AGRI_USAGE_ACTION.equals(actionName)) {
						PropertyValuationDTO dto = eForm.getPropertyDTO();
						UsePlotDTO udto=eForm.getUsePlotDTO();
							String[] usePlot = dto.getUsePlotId().split("~");
							if(usePlot[0].equalsIgnoreCase("8")&& dto.getCheck().equalsIgnoreCase("NO"))
							{
								dto.setUsePlotF(usePlot[0]);
								dto.setCeilingTypeId(null);
								eForm.setCeilingList(bo.getL2Type(usePlot[0],languageLocale));
								eForm.setLandTypeList(bo.getLandType(dto.getUsePlotId(),languageLocale));
								eForm.setSubClauseList(new ArrayList());
								forwardJsp = CommonConstant.VALUATION_AGRI_PAGE;
							}
							else
							{	
							eForm.getPropertyDTO().setHdnSubClause("");
							//propertyDTO.considerAmt
							eForm.getPropertyDTO().setConsiderAmt("0.0");
							eForm.getPropertyDTO().setTotalSqMeter(0.0);
							
							eForm.setSubClauseList(bo.getSubClause(dto)); //for getting subclause list
							eForm.getCeilingDTO().setCeilingType("");
							eForm.getCeilingDTO().setCeilingTypeId(0);
							if(dto.getCeilingTypeId()==null || dto.getCeilingTypeId().equalsIgnoreCase(""))
							{	
							dto.setCeilingTypeId(null);
							}
							
							if (usePlot != null && usePlot.length == 3) {
								if (usePlot[2] != null && usePlot[2].trim() != "") {
									dto.setLblUsage(usePlot[2]);
									dto.setUsePlotF(usePlot[0]);
									udto.setUsePlotId(Integer.parseInt(usePlot[0]));
									//udto.setUsePlot(usePlot[2]);
									udto.setUsePlot(usePlot[1]);// for hindi/english display
									eForm.setCeilingList(bo.getL2Type(usePlot[0],languageLocale));
									eForm.setPropertyDTO(dto);
									}
							}
							eForm.setLandTypeList(bo.getLandType(dto.getUsePlotId(),languageLocale));
							forwardJsp = CommonConstant.VALUATION_AGRI_PAGE;
					}
				}
					if (CommonConstant.CANCEL_ACTION.equals(actionName)) {
							session.removeAttribute("modName");
							session.removeAttribute("fnName");
							forwardJsp = "welcome";
					}
					if (CommonConstant.RESET_ACTION.equals(actionName)) {

						PropertyValuationDTO dtoProperty = eForm.getPropertyDTO();
						dtoProperty.setTotalSqMeter(0.0);
						dtoProperty.setConsiderAmt("0.0");
						dtoProperty.setUsePlotId("");
						dtoProperty.setLandMeterId("");
						dtoProperty.setHdnSubClause("");
						eForm.setSubClauseList(new ArrayList());
						forwardJsp = CommonConstant.VALUATION_AGRI_PAGE;
				}
				if (CommonConstant.PLOT_SUBMIT_ACTION.equals(actionName)) {
					PropertyValuationDTO useDTO =eForm.getPropertyDTO();
					PropertyValuationDTO dto = eForm.getPropertyDTO();
					useDTO.setUsePlotId(dto.getUsePlotId());
					useDTO.setHdnSubClauseName(dto.getHdnSubClauseName());
					String[] marketValue=new String[3];
					marketValue[0]= bo.getMarketValueTotal(useDTO);
				
					if (marketValue != null) {
						String value = common.getCurrencyFormat(Double
								.parseDouble(marketValue[0]));
						if (value != null) {
							useDTO.setSystemMVDisplay(value);
							useDTO.setMarketValue(Double.parseDouble(value));
							useDTO.setMktValue(value);
							double consid=0.0;
							if(useDTO.getConsiderAmt()!=null && !useDTO.getConsiderAmt().equalsIgnoreCase("")){
								consid=Double.parseDouble(useDTO.getConsiderAmt());
							}
							
							if(useDTO.getMarketValue()<consid)
							{
							useDTO.setMarketValue(consid);	
							useDTO.setMktValue(useDTO.getConsiderAmt());
							}
							}
							System.out.println(marketValue[1] + ":" + marketValue[2]);
					}
					//useDTO.setMarketValueDisplay(myformatter.format(useDTO.getMarketValue()));  //commented for bug no. 893
					//useDTO.setMarketValueDisplay(common.getCurrencyFormat(useDTO.getMarketValue())); //added for bug no. 893
					useDTO.setMarketValueDisplay(useDTO.getMktValue());
					try {
						guidelineValue=	bo.getGuidelineValue(useDTO);
						System.out.println("Guideline Value condition"+(guidelineValue!=null));
						if(guidelineValue!=null)
							{
							useDTO.setGuidelineValue(Double.parseDouble(guidelineValue));
							System.out.println("Guideline Value"+guidelineValue);
							}
						}
						catch(Exception e)
						{
							System.out.println(e);
						}
					bo.captureUserDetails(useDTO);//Capture user details
					System.out.println("useDTO.getHdnExtSubClause() = "+useDTO.getHdnExtSubClause());
					if(!useDTO.getHdnExtSubClause().equals("")){
					bo.subClauseDetails(useDTO);//Capture sub clause details
					}
					if(eForm.getModuleName()!=null)
					{
						System.out.println("module name is "+eForm.getModuleName());
				        eForm.setDutycalculationDTOList(bo.getDeed(CommonConstant.REGISTRATION_DEED));//hindi missing
					}
					else
					{
						eForm.setDutycalculationDTOList(bo.getDeed(CommonConstant.PROPERTY_DEED));//hindi missing
					}
					if(eForm.getPreviousFlag()==0){
					eForm.getDutyDTO().setDeedId(0);
					eForm.setInstrumentDTOList(new ArrayList());
					eForm.setExemptionDTOList(new ArrayList());
					}
					   if(eForm.getAddPartyLabel()==null){
					DutyCalculationDTO dutyDTO = new DutyCalculationDTO();
				    InstrumentsDTO instDTO = new InstrumentsDTO();
					ExemptionDTO exemDTO = new ExemptionDTO();
					eForm.setDutyDTO(dutyDTO);
					eForm.setInstrumentDTO(instDTO);
					eForm.setExemptionDTO(exemDTO);
					   }
					System.out.println("Market value:-" + useDTO.getMarketValue());
					
					if(eForm.getIsMultipleProperty()==0 && eForm.getIsMultiplePropertyAdju()==0){
					forwardJsp = CommonConstant.DUTY_VALUATION_PAGE;                       //DEED PAGE
					}
					else{
						if(eForm.getIsMultipleProperty()==1){
						forwardJsp = CommonConstant.REGISTRATION_PAGE; 
						session.setAttribute("eform",eForm);				     		
			     		//following added by roopam
			     		request.setAttribute("regFormDashboard",session.getAttribute("regFormProp"));
			     		session.removeAttribute("regFormProp");
			     		//end of addition by roopam		
						}else 
						if(eForm.getIsMultiplePropertyAdju()==1){
							forwardJsp = CommonConstant.ADJUDICATION_PAGE; 
							session.setAttribute("eform",eForm);				     		
				     		//following added by roopam
				     		request.setAttribute("regFormDashboard",session.getAttribute("regFormProp"));
				     		session.removeAttribute("regFormProp");
				     		//end of addition by roopam		
							}				

					}
					
					
				}
				if (CommonConstant.PROPERTY_VALUATION_PREV_HOME
						.equals(actionName)) {
					System.out.println("Inside previous");
					eForm.setPreviousFlag(1);
					
					
					//check subclause list here.
					PropertyValuationDTO useDTO = eForm.getPropertyDTO();
					System.out.println("sub clause ids : "+useDTO.getHdnExtSubClause());
					
					forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
				}
			}

			// End PropertyValuationForAgriculturalLand1.jsp

			// Start PropertyValuationForBuilding.jsp

			if (CommonConstant.VALUATION_BUILDING_PAGE.equals(formName)) {

				if (CommonConstant.CANCEL_ACTION.equals(actionName)) {
					session.removeAttribute("modName");
					session.removeAttribute("fnName");
					forwardJsp = "welcome";
				}
				if (CommonConstant.USAGE_BUILDING_ACTION.equals(actionName)) {
					PropertyValuationDTO dto = eForm.getPropertyDTO();
					if(dto.getCheck().equalsIgnoreCase("BNO"))
					{

						eForm.setFloorcalctypeDTOList(bo.getFloorCalcType(dto,languageLocale));
						eForm.setSubClauseList(new ArrayList());
						String[] usePlot = dto.getUsePlotId().split("~");
						System.out.println("use plo1 1:" + usePlot[0]);
						if (usePlot != null && usePlot.length == 3) {
							if (usePlot[2] != null && usePlot[2].trim() != "") {
								dto.setLblUsage(usePlot[2]);
								eForm.setCeilingList(bo.getL2Type(usePlot[0],languageLocale));
								eForm.setFloorList(bo.getFloorType(usePlot[0],languageLocale));
								eForm.setPropertyDTO(dto);
							}
						}
						forwardJsp = CommonConstant.VALUATION_BUILDING_PAGE;
					
					}
					else
					{
					eForm.setFloorcalctypeDTOList(bo.getFloorCalcType(dto,languageLocale));
					eForm.setSubClauseList(bo.getSubClause(dto));
					String[] usePlot = dto.getUsePlotId().split("~");
					System.out.println("use plo1 1:" + usePlot[0]);
					if (usePlot != null && usePlot.length == 3) {
						if (usePlot[2] != null && usePlot[2].trim() != "") {
							dto.setLblUsage(usePlot[2]);
							eForm.setCeilingList(bo.getL2Type(usePlot[0],languageLocale));
							eForm.setFloorList(bo.getFloorType(usePlot[0],languageLocale));
							eForm.setPropertyDTO(dto);
						}
					}
					forwardJsp = CommonConstant.VALUATION_BUILDING_PAGE;
				}
				}
				if (CommonConstant.FLOOR_TYPE_CALC_ACTION.equals(actionName)) {
					String[] floorCalc = eForm.getHdnfloorCalcType().split("~");
					System.out.println("floorCalc 1:" + floorCalc[0]);
					FloorCalcTypeDTO dto =eForm.getFloorCalcTypeDTO();
					if (floorCalc != null && floorCalc.length == 2) {
						if (floorCalc[1] != null && floorCalc[1].trim() != "") {
							dto.setFloorCalcId(Integer.parseInt(floorCalc[0]));
							eForm.getPropertyDTO().setFloorCalcTypeID(Integer.parseInt(floorCalc[0]));
							System.out.println("PROPERTY VALUATION floor calc id  :-"
									+ eForm.getPropertyDTO().getFloorCalcTypeID());
							dto.setFloorCalcType(floorCalc[1]);
						}
					}
					forwardJsp = CommonConstant.VALUATION_BUILDING_PAGE;
				}
						
				if (CommonConstant.ADD_MORE_ACTION.equals(eForm.getActionName())) {
					count = count + 1;
					System.out.println("COUNT IS:" + count);
					PropertyValuationDTO mapdto = eForm.getPropertyDTO();
					PropertyValuationDTO hDTO = new PropertyValuationDTO();
					map = eForm.getMapBuilding();			
					mapdto.setAddMoreCounter(count);
					String[] marketValue = bo.getFloorWiseMarketValue(mapdto);
					String[] floor = mapdto.getTypeFloorID() == null ? null
							: mapdto.getTypeFloorID().split("~");
					String[] usagePlot = mapdto.getUsePlotId() == null ? null
							: mapdto.getUsePlotId().split("~");
					hDTO.setConsiderAmt(mapdto.getConsiderAmt());
					hDTO.setConstructedArea(mapdto.getConstructedArea());
					hDTO.setTotalSqMeter(mapdto.getTotalSqMeter());
					hDTO.setUsePlotId(mapdto.getUsePlotId());
					hDTO.setCeilingTypeId(mapdto.getCeilingTypeId());
					hDTO.setTypeFloorID(mapdto.getTypeFloorID());
					String[] ceilingType = mapdto.getCeilingTypeId() == null ? null
							: mapdto.getCeilingTypeId().split("~");
					
					hDTO.setTotalSqMeter(mapdto.getTotalSqMeter());
					hDTO.setConstructedArea(mapdto.getConstructedArea());
					hDTO.setHdnNoOfTree(mapdto.getHdnNoOfTree());
					hDTO.setHdnExtSubClause(mapdto.getHdnExtSubClause());
					hDTO.setConsiderAmt(mapdto.getConsiderAmt());
				
				
					if (ceilingType != null && ceilingType.length == 3) {
						//hDTO.setCeilingType(ceilingType[1]);
						hDTO.setCeilingType(ceilingType[2]);//FOR HINDI/ENGLISH
					}
					System.out.println("SubClause Name:-"
							+ mapdto.getHdnSubClauseName());
					hDTO.setHdnSubClauseName(mapdto.getHdnSubClauseName());

					
					
					String key = null;

					if (floor != null && floor.length == 3) {
						// map.containsKey(key);
						key = floor[1];
						if (map.containsKey(key)) {
							key = floor[1] + Integer.toString(count);
						}
						System.out.println("floor id is @@@@@:" + floor[1]);
						hDTO.setTypeFloorDesc(floor[2]);
						System.out.println("floor desc is @@@@@:" + floor[2]);
						hDTO.setFloorID(key);
						mapdto.setTypeFloorDesc(floor[2]);
						mapdto.setFloorID(key);
						System.out.println("Count:-" + count + ":" + key);
					}

					if (usagePlot != null && usagePlot.length == 3) {
						mapdto.setUsageBuilding(usagePlot[1]);
						hDTO.setUsageBuilding(usagePlot[1]);
					}
					if (marketValue != null && marketValue.length == 3) {
						double mktValue = marketValue[0] == null ? 0.0 : Double
								.parseDouble(marketValue[0]);
				
								hDTO.setSystemMVDisplay(Double.toString(mktValue));
								
								double consid=0.0;
								if(hDTO.getConsiderAmt()!=null && !hDTO.getConsiderAmt().equalsIgnoreCase("")){
									consid=Double.parseDouble(hDTO.getConsiderAmt());
								}
								
								if(mktValue<consid)
								{
									mktValue=consid;
								}
						
						logger.debug("@@@@MARKET VALUE IS @@@@@@@ :"
										+ mktValue);
						mapdto.setMarketValue(mktValue);
						hDTO.setMarketValue(mktValue);
						mapdto.setMktValue(common.getCurrencyFormat(mktValue));
					    hDTO.setMktValue(common.getCurrencyFormat(mktValue));
					}

					
					try{
						guidelineValue=	bo.getGuidelineValue(mapdto);
						logger.debug("outside Guideline Value");
						logger.debug("Guideline Value condition"+(guidelineValue!=null));
						if(guidelineValue!=null)
						{mapdto.setGuidelineValue(Double.parseDouble(guidelineValue));
						logger.debug("Guideline Value"+guidelineValue);
										}
						}
						catch(Exception e)
						{
							logger.debug(e);
						}
					
					if (!map.containsKey(key)) {
					bol = true;

					} else {
						Iterator I = map.entrySet().iterator();
						while (I.hasNext()) {
							Map.Entry me = (Map.Entry) I.next();
							String tempKey = (String) me.getKey();

							if (tempKey != null && tempKey.length() >= 3) {
								String str = tempKey.substring(0, 3);
								if (CommonConstant.THIRD_FLOOR.equals(str)
										|| CommonConstant.FOURTH_FLOOR
												.equals(str)) {
									floorCount++;
									key = key + floorCount;
									System.out.println("Key :-" + key);
									bol = true;
									break;
								}
							}
							if (tempKey != null && tempKey.equals(key)) {
								logger
										.debug("This floor calculation is already done.");
								break;
							}
						}
					}
					logger.debug("map.containsValue(mapdto):"
							+ map.containsValue(mapdto));
					HashMap mapBuilding = new HashMap();
					if (bol) {
						logger.debug("key:-" + bol + ":" + mapdto.getFloorID());
						logger.debug(hDTO.getMarketValue());
						logger.debug(hDTO.getMktValue());
						map.put(key, hDTO);
						logger.debug("KEY IS :" + key);
						mapBuilding.put(key, hDTO);

					}
					eForm.setMapBuilding(map);
					eForm.setMapBuildingDetails(map);
					logger.debug("MAP SIZE  after INSERTION:" + map.size());
					logger.debug("COUNT after INSERTION:" + count);
					PropertyValuationDTO dtoProperty = eForm.getPropertyDTO();
					dtoProperty.setTotalSqMeter(0.0);
					dtoProperty.setConsiderAmt("0.0");
					dtoProperty.setUsePlotId("");
					eForm.setCeilingList(new ArrayList());
					dtoProperty.setConstructedArea(0.0);
					eForm.setFloorList(new ArrayList());
					dtoProperty.setHdnSubClause("");
					dtoProperty.setTypeFloorID("");
					dtoProperty.setCeilingTypeId("");
					eForm.setSubClauseList(new ArrayList());
					eForm.setActionName("");
					actionName="";
					logger.debug("before accction actionName:-" + eForm.getActionName());
					logger.debug("Add More pressed:-" + count);
					forwardJsp = CommonConstant.VALUATION_BUILDING_PAGE;
					
					//clear sub-clause selection here
					eForm.getPropertyDTO().setHdnExtSubClause("");
					
				}
				if (CommonConstant.DELETE_MORE_ACTION.equals(actionName)) {
					// Changed for correcting floor id deletion
					PropertyValuationDTO dto = eForm.getPropertyDTO();
					// String[] floorID= dto.getDeleteFloorID().split(",");
					String[] floorID = dto.getHdnDeleteFloorID().split(",");
					count=dto.getAddMoreCounter();
					
					logger.debug("	floor id lenght : " + floorID.length);
					dto.setAddMoreCounter(count);
					logger.debug("COUNT IS:" + count);

					for (int i = 0; i < floorID.length; i++) {
						logger.debug(floorID[i] + " is removed...");
						map.remove(floorID[i]);
						count = count - 1;
						logger.debug("MAP SIZE  after deletion:" + map.size());
						logger.debug("COUNT after deletion:" + count);
					}
					dto.setAddMoreCounter(map.size());
					logger.debug("MAP IS :" + map);
					eForm.setMapBuilding(map);
					eForm.setActionName("");
					actionName="";
					forwardJsp = CommonConstant.VALUATION_BUILDING_PAGE;
				}
				if (CommonConstant.FLOOR_ACTION.equals(actionName)) {
					PropertyValuationDTO dto = eForm.getPropertyDTO();
					forwardJsp = CommonConstant.VALUATION_BUILDING_PAGE;
				}
				if (CommonConstant.RESET_ACTION.equals(actionName)) {

					PropertyValuationDTO dtoProperty = eForm.getPropertyDTO();
					dtoProperty.setTotalSqMeter(0.0);
					dtoProperty.setConsiderAmt("0.0");
					dtoProperty.setUsePlotId("");
					eForm.setCeilingList(new ArrayList());
					dtoProperty.setConstructedArea(0.0);
					eForm.setFloorList(new ArrayList());
					dtoProperty.setHdnSubClause("");
					dtoProperty.setTypeFloorID("");
					dtoProperty.setCeilingTypeId("");
					eForm.setSubClauseList(new ArrayList());
					eForm.getFloorCalcTypeDTO().setFloorCalcId(0);
					forwardJsp = CommonConstant.VALUATION_BUILDING_PAGE;
				}
				if (CommonConstant.BUILDING_SUBMIT_ACTION.equals(actionName)) {
					PropertyValuationDTO useDTO = eForm.getPropertyDTO();
					PropertyValuationDTO dto = eForm.getPropertyDTO();
					useDTO.setHdnSubClause(dto.getHdnSubClause());
					logger.debug("Use Plot:-" + dto.getUsePlotId() + ":"
							+ dto.getHdnSubClause());
					logger.debug(dto.getHdnSubClauseName());
					useDTO.setHdnSubClauseName(dto.getHdnSubClauseName());
				
					String[] marketValue = bo.getFloorWiseMarketValue(useDTO);
					HashMap map = eForm.getMapBuilding();
					String value = "0.0";
					logger.debug("MAP SIzE IS :" + map.size());
					logger.debug("COUNT VALUE IS :" + count);
					if (map.size() >= count) {
						count++;
					}
					if (marketValue != null) {
						value = common.getCurrencyFormat(Double
								.parseDouble(marketValue[0] == null ? "0.0"
										: marketValue[0]));
						if (value != null) {
							useDTO.setSystemMVDisplay(value);
							
							double consid=0.0;
							
							if(useDTO.getConsiderAmt()!=null && !useDTO.getConsiderAmt().equalsIgnoreCase("")){
								consid=Double.parseDouble(useDTO.getConsiderAmt());
							}
							
							if(Double.parseDouble(value)<consid)
							{
								value=useDTO.getConsiderAmt();
							}					
						}
						
								}
					logger.debug("Ceiling Type:-" + dto.getCeilingTypeId());
					String[] ceilingType = dto.getCeilingTypeId() == null ? null
							: dto.getCeilingTypeId().split("~");

					if (ceilingType != null && ceilingType.length == 3) {
						//useDTO.setCeilingType(ceilingType[1]);
						useDTO.setCeilingType(ceilingType[2]);//FOR HINDI/ENGLISH
					}
					logger.debug("dto.getTypeFloorID():-"
							+ dto.getTypeFloorID());
					String[] floor = dto.getTypeFloorID() == null ? null : dto
							.getTypeFloorID().split("~");
					String[] usagePlot = dto.getUsePlotId() == null ? null
							: dto.getUsePlotId().split("~");

					String key = null;

					
					
					if (floor != null && floor.length == 3) {
						key = floor[1];
						useDTO.setTypeFloorDesc(floor[2]);
						useDTO.setFloorID(key);
						logger.debug("Count:-" + count + ":" + key);
					}

					if (usagePlot != null && usagePlot.length == 3) {

						useDTO.setUsageBuilding(usagePlot[1]);
					}

					Iterator I = map.entrySet().iterator();
					double sumMarketValue = 0.0;
					while (I.hasNext()) {
						Map.Entry me = (Map.Entry) I.next();
						PropertyValuationDTO hDTO = (PropertyValuationDTO) me
								.getValue();
						sumMarketValue = sumMarketValue + hDTO.getMarketValue();
						logger.debug("MARKET VALUE IN HASH MAP  "
								+ hDTO.getMarketValue());
						logger.debug(" MARKET VALUE IN HASH MAP  sumMarketValue"
								+ sumMarketValue);

					}
					logger.debug(" MARKET VALUE before adding  value to sumMarketValue"
							+ sumMarketValue);
					sumMarketValue = sumMarketValue + Double.parseDouble(value);
					logger.debug(" MARKET VALUE before adding  value to sumMarketValue value is"
							+ value);
					logger.debug(" MARKET VALUE after adding  sumMarketValue"
							+ sumMarketValue);

					useDTO.setMktValue(common.getCurrencyFormat(Double
							.parseDouble(value)));
					
					
					
					useDTO.setMarketValue(sumMarketValue);
					logger.debug("before storing  MARKET VALUE :"
							+ useDTO.getMarketValue());

					
					//useDTO.setMarketValueDisplay(myformatter.format(sumMarketValue)); //commented for bug no. 893
					useDTO.setMarketValueDisplay(common.getCurrencyFormat(sumMarketValue)); //added for bug no. 893
					try{
						guidelineValue=	bo.getGuidelineValue(useDTO);
						logger.debug("outside Guideline Value");
						logger.debug("Guideline Value condition"+(guidelineValue!=null));
						if(guidelineValue!=null)
						{useDTO.setGuidelineValue(Double.parseDouble(guidelineValue));
						logger.debug("Guideline Value"+guidelineValue);
										}
						}
						catch(Exception e)
						{
							logger.debug(e);
						}
				
					bo.captureUserDetails(useDTO);//Capture user details
					
					//below condition added by roopam on 1 november 2013 for stopping redirection to reg init in case of insertion failure.
						if(useDTO.getValuationId()!=null){
							
						}else{
							return mapping.findForward("failure");	
						}
						
					
					HashMap hMap = eForm.getMapBuildingDetails();
					hMap.put("key", useDTO);
					eForm.setMapBuildingDetails(hMap);
					bo.captureFloorDetails(hMap,useDTO);	
					useDTO.setMarketValue(sumMarketValue);
					sumMarketValue = 0.0;
					eForm.setPropertyDTO(useDTO);
					if(eForm.getModuleName()!=null)
					{
				        eForm.setDutycalculationDTOList(bo.getDeed(CommonConstant.REGISTRATION_DEED));//hindi missing
					}
					else
					{
						eForm.setDutycalculationDTOList(bo.getDeed(CommonConstant.PROPERTY_DEED));//hindi missing
					}

					eForm.setInstrumentDTOList(new ArrayList());
					eForm.setExemptionDTOList(new ArrayList());
					if(eForm.getAddPartyLabel()==null){
					DutyCalculationDTO dutyDTO = new DutyCalculationDTO();
					InstrumentsDTO instDTO = new InstrumentsDTO();
					ExemptionDTO exemDTO = new ExemptionDTO();
					eForm.setDutyDTO(dutyDTO);
					eForm.setInstrumentDTO(instDTO);
					eForm.setExemptionDTO(exemDTO);
					eForm.setActionName("");
					
					   }

					logger.debug("Market value:-" + useDTO.getMarketValue());
		              if(eForm.getIsMultipleProperty()==0 && eForm.getIsMultiplePropertyAdju()==0){
						forwardJsp = CommonConstant.DUTY_VALUATION_PAGE;                       //DEED PAGE
						}
						else{
							
							if(eForm.getIsMultipleProperty()==1){
							forwardJsp = CommonConstant.REGISTRATION_PAGE; 
							session.setAttribute("eform",eForm);				     		
				     		//following added by roopam
				     		request.setAttribute("regFormDashboard",session.getAttribute("regFormProp"));
				     		session.removeAttribute("regFormProp");
				     		//end of addition by roopam		
							}else 
							if(eForm.getIsMultiplePropertyAdju()==1){
								forwardJsp = CommonConstant.ADJUDICATION_PAGE; 
								session.setAttribute("eform",eForm);				     		
					     		//following added by roopam
					     		request.setAttribute("regFormDashboard",session.getAttribute("regFormProp"));
					     		session.removeAttribute("regFormProp");
					     		//end of addition by roopam		
								}
						}				}
				if (CommonConstant.PROPERTY_VALUATION_PREV_HOME
						.equals(actionName)) {
					logger.debug("Inside previous");
					eForm.getPropertyDTO().setAddMoreCounter(0);
					eForm.getFloorCalcTypeDTO().setFloorCalcId(0);
					
					eForm.setPreviousFlag(1);
					
					forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
				}
			}

			// End PropertyValuationForBuilding.jsp

			// Start PropertyValuationForPlot1.jsp
			if (CommonConstant.VALUATION_PLOT_PAGE.equals(formName)) {
				if (CommonConstant.CANCEL_ACTION.equals(actionName)) {
					session.removeAttribute("modName");
					session.removeAttribute("fnName");
					forwardJsp = "welcome";
				}
				if (CommonConstant.PLOT_USAGE_ACTION.equals(actionName)) {
					PropertyValuationDTO dto = eForm.getPropertyDTO();
					eForm.setSubClauseList(bo.getSubClause(dto));
					eForm.getCeilingDTO().setCeilingType("");
					eForm.getCeilingDTO().setCeilingTypeId(0);
					dto.setCeilingTypeId(null);
					UsePlotDTO udto=eForm.getUsePlotDTO();
					String[] usePlot = dto.getUsePlotId().split("~");
					if (usePlot != null && usePlot.length == 3) {
						if (usePlot[2] != null && usePlot[2].trim() != "") {
							dto.setLblUsage(usePlot[2]);
							dto.setUsePlotF(usePlot[0]);
							udto.setUsePlotId(Integer.parseInt(usePlot[0]));
							//udto.setUsePlot(usePlot[2]);
							udto.setUsePlot(usePlot[1]);//for hindi/english display
							eForm.setCeilingList(bo.getL2Type(usePlot[0],languageLocale));
							eForm.setPropertyDTO(dto);
						}
					}
					forwardJsp = CommonConstant.VALUATION_PLOT_PAGE;
				}
				if (CommonConstant.RESET_ACTION.equals(actionName)) {

					PropertyValuationDTO dtoProperty = eForm.getPropertyDTO();
					dtoProperty.setTotalSqMeter(0.0);
					dtoProperty.setConsiderAmt("0.0");
					dtoProperty.setUsePlotId("");
					dtoProperty.setHdnSubClause("");
					dtoProperty.setCeilingTypeId("");
					eForm.setSubClauseList(new ArrayList());
					forwardJsp = CommonConstant.VALUATION_PLOT_PAGE;
				}
				if (CommonConstant.PLOT_SUBMIT_ACTION.equals(actionName)) {
					PropertyValuationDTO useDTO = eForm.getPropertyDTO();
					PropertyValuationDTO dto = eForm.getPropertyDTO();
					useDTO.setHdnSubClause(dto.getHdnSubClause());
					logger.debug("Use Plot:-" + dto.getUsePlotId());
					useDTO.setUsePlotId(dto.getUsePlotId());
					logger.debug(dto.getHdnSubClauseName());
					useDTO.setHdnSubClauseName(dto.getHdnSubClauseName());
					String[] marketValue=new String[3]; 
					marketValue[0]= bo.getMarketValueTotal(useDTO);
					logger.debug("HDN SUB CLAUSE VALUE IS :"+dto.getHdnSubClause());
					
					if (marketValue != null) {
						String value = common.getCurrencyFormat(Double
								.parseDouble(marketValue[0] == null ? "0.0"
										: marketValue[0]));
						if (value != null) {
							useDTO.setSystemMVDisplay(value);
							useDTO.setMarketValue(Double.parseDouble(value));
							
							double consid=0.0;
							
							if(useDTO.getConsiderAmt()!=null && !useDTO.getConsiderAmt().equalsIgnoreCase("")){
								consid=Double.parseDouble(useDTO.getConsiderAmt());
							}
							
							if(useDTO.getMarketValue()>consid)
							{
								useDTO.setMktValue(value);
							}else
							{
								useDTO.setMarketValue(consid);
								useDTO.setMktValue(useDTO.getConsiderAmt());
							}
					
						}
						logger.debug(marketValue[1] + ":" + marketValue[2]);
					}
				
					//useDTO.setMarketValueDisplay(myformatter.format(useDTO.getMarketValue()));  //commented for bug no. 893
					//useDTO.setMarketValueDisplay(common.getCurrencyFormat(useDTO.getMarketValue())); //added for bug no. 893
					//if(useDTO.getMktValue()!=null && !useDTO.getMktValue().equalsIgnoreCase("")){
					//useDTO.setMarketValueDisplay(common.getCurrencyFormat(Double.parseDouble(useDTO.getMktValue())));
					useDTO.setMarketValueDisplay(useDTO.getMktValue());
					//}else{
					//	useDTO.setMarketValueDisplay("0.0");
					//}
						try{
								guidelineValue=	bo.getGuidelineValue(useDTO);//for getting guideline value
								logger.debug("outside Guideline Value");
								logger.debug("Guideline Value condition"+(guidelineValue!=null));
								if(guidelineValue!=null)
								{
									useDTO.setGuidelineValue(Double.parseDouble(guidelineValue));
									logger.debug("Guideline Value"+guidelineValue);
								}
							}
							catch(Exception e)
							{
								logger.debug(e);
							}
					bo.captureUserDetails(useDTO);//Capture user details
					logger.debug("useDTO.getHdnExtSubClause() = "+useDTO.getHdnExtSubClause());
					if(!useDTO.getHdnExtSubClause().equals("")){
					bo.subClauseDetails(useDTO);//Capture sub clause details
					}
					if(eForm.getPreviousFlag()==0){
					eForm.getDutyDTO().setDeedId(0);
					eForm.setInstrumentDTOList(new ArrayList());
					eForm.setExemptionDTOList(new ArrayList());
					}
					
					if(eForm.getModuleName()!=null)
					{
				        eForm.setDutycalculationDTOList(bo.getDeed(CommonConstant.REGISTRATION_DEED));//hindi missing
					}
					else
					{
						eForm.setDutycalculationDTOList(bo.getDeed(CommonConstant.PROPERTY_DEED));//hindi missing
					}
					/*eForm.setInstrumentDTOList(new ArrayList());
					eForm.setExemptionDTOList(new ArrayList());*/
				   if(eForm.getAddPartyLabel()==null)
					{
					DutyCalculationDTO dutyDTO = new DutyCalculationDTO();
					InstrumentsDTO instDTO = new InstrumentsDTO();
					ExemptionDTO exemDTO = new ExemptionDTO();
					eForm.setDutyDTO(dutyDTO);
					eForm.setInstrumentDTO(instDTO);
					eForm.setExemptionDTO(exemDTO);
					}
					logger.debug("Market value:-" + useDTO.getMarketValue());
					if(eForm.getIsMultipleProperty()==0 && eForm.getIsMultiplePropertyAdju()==0){
						forwardJsp = CommonConstant.DUTY_VALUATION_PAGE;                       //DEED PAGE
					}
						else{
							
							if(eForm.getIsMultipleProperty()==1){
							forwardJsp = CommonConstant.REGISTRATION_PAGE; 
							session.setAttribute("eform",eForm);				     		
				     		//following added by roopam
				     		request.setAttribute("regFormDashboard",session.getAttribute("regFormProp"));
				     		session.removeAttribute("regFormProp");
				     		//end of addition by roopam		
							}else 
							if(eForm.getIsMultiplePropertyAdju()==1){
								forwardJsp = CommonConstant.ADJUDICATION_PAGE; 
								session.setAttribute("eform",eForm);				     		
					     		//following added by roopam
					     		request.setAttribute("regFormDashboard",session.getAttribute("regFormProp"));
					     		session.removeAttribute("regFormProp");
					     		//end of addition by roopam		
								}
						}
				}
				if (CommonConstant.PROPERTY_VALUATION_PREV_HOME
						.equals(actionName)) {
					logger.debug("Inside previous");
					eForm.setPreviousFlag(1);
					forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
				}
			}

			// End PropertyValutionForPlot1.jsp

			// Start PropertyValuation2.jsp
			if (CommonConstant.DUTY_VALUATION_FORM.equals(formName)) {
				if (CommonConstant.CANCEL_ACTION.equals(actionName)) {
					session.removeAttribute("modName");
					session.removeAttribute("fnName");
					forwardJsp = "welcome";
				}
				PropertyValuationDTO useDTO = eForm.getPropertyDTO();

				if (CommonConstant.RESET_ACTION.equals(actionName)) {
					eForm.setExemptionDTOList(new ArrayList());
					eForm.setInstrumentDTOList(new ArrayList());
					InstrumentsDTO instDTO = eForm.getInstrumentDTO();
					instDTO.setHdnAmount("");
					DutyCalculationDTO dutyDTO = eForm.getDutyDTO();
					dutyDTO.setDeedId(0);
					forwardJsp = CommonConstant.DUTY_VALUATION_PAGE;                             //DEED PAGE
				}
				if (CommonConstant.DEED_ACTION.equals(actionName)) {
				
					int deed = eForm.getDutyDTO().getDeedId();
					eForm.setDeedId(deed);
					eForm.getDutyDTO().setDutyAlreadyPaid(0.0);
					eForm.getDutyDTO().setRegPaid(0.0);
					eForm.getDutyDTO().setDutyAlreadyPaidFlag(null);
					eForm.getDutyDTO().setShareValue(0.0);
					eForm.setHdnAmount("");
					eForm.setInstrumentDTO(new InstrumentsDTO());
					eForm.setInstrumentDTOList(bo.getInstrument(deed,languageLocale));//hindi missing
					eForm.setExemptionDTOList(new ArrayList());
					forwardJsp = CommonConstant.DUTY_VALUATION_PAGE;                             //DEED PAGE
				}
				
				if (CommonConstant.ADD_MORE_PARTIES_ACTION.equals(actionName))
				{   
					formName="";
					
					PropertyValuationDTO mdto = eForm.getPropertyDTO();
					
					//For partition already paid
					if(eForm.getInstrumentDTO().getLabelAmountFlag().equals(CommonConstant.PARTY_LABEL_ADD_DUTY)){
						eForm.getInstrumentDTO().setLabelAmountFlag(CommonConstant.PARTY_LABEL);
						eForm.getDutyDTO().setDutyAlreadyPaidFlag(CommonConstant.PARTY_LABEL_ADD_DUTY)  ;
				    }
					eForm.setAddPartyLabel(eForm.getInstrumentDTO().getLabelAmountFlag());
					//For exchange 
					if(eForm.getInstrumentDTO().getLabelAmountFlag().equals(CommonConstant.PARTY_LABEL_EXCHANGE)){
						eForm.setAddPartyLabel(CommonConstant.PARTY_LABEL);
						eForm.getDutyDTO().setExchangeFlag(CommonConstant.PARTY_LABEL_EXCHANGE);
					   }
				
					MapDTO mapdto=new MapDTO();
					System.out.println(mdto.getMarketValueDisplay()+" String-----double "+mdto.getMarketValue());
					String partyValueDisplay=mdto.getMarketValueDisplay();
					double partyValue=mdto.getMarketValue();
				    int deedId=eForm.getDutyDTO().getDeedId();
				    eForm.getDutyDTO().setDeedId(deedId);
				    logger.debug("Count:-" + count + ":" + count);
				    map1 = eForm.getMapParty(); 
					count=mdto.getAddParty();
					count =count+1;
					mdto.setAddParty(count);
					logger.debug("Count:-" + count + ":" + count);
					logger.debug("Party Label" +eForm.getAddPartyLabel());
					String key = null;
					logger.debug("Intial Map size is "+map1.size());
					String partyName=eForm.getPropertyDTO().getFirstName();
					logger.debug("Name  value is " + eForm.getPropertyDTO().getFirstName());
					mapdto.setPartyName(eForm.getPropertyDTO().getFirstName());
					key =partyName+Integer.toString(count);
					mapdto.setPartyValuationId(eForm.getPropertyDTO().getValuationId());
					mapdto.setPartyID(key);
					logger.debug("key value is " + count);
					logger.debug("Market value is " + partyValueDisplay);
					mapdto.setPartyValue(partyValueDisplay);
					logger.debug("Market value is " + partyValueDisplay);
			
					
					while(map1.containsKey(key)){
							key =partyName+Integer.toString(count++);
							mapdto.setPartyID(key);
							}
					logger.debug("map.containsValue(mapdto):"
					+ map1.containsValue(mapdto));
			    	logger.debug("Mkt Value is "+mdto.getMktValue());
					logger.debug("Market Value is "+mdto.getMarketValue());
				
					count2=eForm.getPropertyDTO().getAddAnotherParty();
					if(eForm.getPropertyDTO().getPartyLabel().equalsIgnoreCase("Party2"))
					{
					count2=count2+1;
					eForm.getPropertyDTO().setAddAnotherParty(count2);
					mapdto.setPartyLabel("Party2");		
					}
					if(count2>1)
					{
					mapdto.setPartyLabel(eForm.getPropertyDTO().getPartyLabel());
					}
					else
					{
						mapdto.setPartyLabel("Party1");		
					}
					logger.debug("Final Map size is "+map1.size());
					map1.put(key, mapdto);
					logger.debug("KEY IS :" + key);
					eForm.setMapParty(map1);

					
					if (eForm.getModuleName()!=null){
					if (eForm.getModuleName().equals(CommonConstant.REGISTRATION_VALUATION)||eForm.getModuleName().equals(CommonConstant.ADJUDICATION_VALUATION))
					{
						
						//clear only property details
					forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
					//eForm.setDistList(new ArrayList());
					eForm.setDistList(bo.getDistrict(languageLocale));
					eForm.getPropertyDTO().setDistrictID(0);
					eForm.setMahallaList(new ArrayList());
					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setAreaTypeList(new ArrayList());
					eForm.setPropertyType(new ArrayList());
					eForm.setMunicipalList(new ArrayList());
					eForm.getPropertyDTO().setPatwariStatus("");
					}
						}
					else
					{
					if(count>=1&&eForm.getPropertyDTO().getPartyLabel().equalsIgnoreCase("Party1"))
					{
				    forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
				    eForm.setDistList(bo.getDistrict(languageLocale));
				    eForm.getPropertyDTO().setDistrictID(0);
					eForm.setMahallaList(new ArrayList());
					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setAreaTypeList(new ArrayList());
					eForm.setPropertyType(new ArrayList());
					eForm.setMunicipalList(new ArrayList());
					eForm.getPropertyDTO().setPatwariStatus("");
				    
				    //clear only property details
				    
					}
				    else if(count2>1&&eForm.getPropertyDTO().getPartyLabel().equalsIgnoreCase("Party2"))
			        {
				    	
				    	 //clear only property details
				    	
		            forwardJsp = CommonConstant.VALUATION_NEW_PAGE;
		            eForm.setDistList(bo.getDistrict(languageLocale));
		            eForm.getPropertyDTO().setDistrictID(0);
					eForm.setMahallaList(new ArrayList());
					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setAreaTypeList(new ArrayList());
					eForm.setPropertyType(new ArrayList());
					eForm.setMunicipalList(new ArrayList());
					eForm.getPropertyDTO().setPatwariStatus("");
		            }else
		            {
		        	  
		        	    eForm.getPropertyDTO().setFirstName("");
						eForm.getPropertyDTO().setMiddleName("");
						eForm.getPropertyDTO().setLastName("");
						eForm.getPropertyDTO().setSex("");
						eForm.getPropertyDTO().setConsiderAmt("0.0");
						eForm.getPropertyDTO().setDobDay("");
						eForm.getPropertyDTO().setDobMonth("");
						eForm.getPropertyDTO().setDobYear("");
						forwardJsp = CommonConstant.VALUATION_FIRST_PAGE;
		            }
					
						
					}	
					
				//CLEAR SUBCLAUSE SELECTION HERE
					eForm.getPropertyDTO().setHdnSubClause(null);
					eForm.getPropertyDTO().setHdnExtSubClause("");
					
					
				
				}	
				
				if (CommonConstant.DELETE_PARTIES_ACTION.equals(actionName)){
					
					MapDTO mapdto=new MapDTO();
					PropertyValuationDTO dto = eForm.getPropertyDTO();
					logger.debug("	SELECTED PARTY : " + dto.getHdnDeletePartyID());
					String[] partyID = dto.getHdnDeletePartyID().split(",");
					
					logger.debug("	party id lenght : " + partyID.length);
					count=dto.getAddParty();
					logger.debug("COUNT IS:" + count);

					for (int i = 0; i < partyID.length; i++) {
						logger.debug("	party id lenght : " + partyID.length);
						logger.debug(partyID[i] + " is removed...");
						map1.remove(partyID[i]);
						count = count - 1 ;
						dto.setAddParty(count);
						logger.debug("COUNT IS:" + count);
					}
					logger.debug("MAP SIZE IN DELTE IS :" + map1.size());
					eForm.setMapParty(map1);
					forwardJsp = CommonConstant.DUTY_VALUATION_PAGE;					          //DEED PAGE
				}
				
				
				if (CommonConstant.INSTRUMENT_ACTION.equals(actionName)) {
					eForm.getDutyDTO().setDutyAlreadyPaid(0.0);
					eForm.getDutyDTO().setRegPaid(0.0);
					eForm.getDutyDTO().setDutyAlreadyPaidFlag(null);
					eForm.getDutyDTO().setShareValue(0.0);
					int deedId = eForm.getDutyDTO().getDeedId();
					logger.debug(" deed value is :" + deedId);
					int instrumentId = eForm.getInstrumentDTO().getInstId();
					logger.debug(" inst id value is :" + instrumentId);
					eForm.getInstrumentDTO().setInstId(instrumentId);
					eForm.setExemptionDTOList(bo.getExemptions(deedId,
							instrumentId,languageLocale));//HINDI MISSING
					forwardJsp = CommonConstant.DUTY_VALUATION_PAGE;                               //DEED PAGE
				}
				if (CommonConstant.DISPLAY_ACTION.equals(actionName)) {
					DutyCalculationDTO dutyDTO = eForm.getDutyDTO();
					//For Partion and exchange	     
					
					//CONVER DOB HERE
					if(eForm.getPropertyDTO().getDobDay()!=null && eForm.getPropertyDTO().getDobMonth()!=null && eForm.getPropertyDTO().getDobYear()!=null){
					String dob=eForm.getPropertyDTO().getDobDay()+"/"+eForm.getPropertyDTO().getDobMonth()+"/"+eForm.getPropertyDTO().getDobYear();
					eForm.getPropertyDTO().setDob(bo.convertDOB(dob));
					}
				
					if(eForm.getAddPartyLabel().equals(CommonConstant.PARTY_LABEL))
					{	
						PropertyValuationDTO exchangeDTO = eForm.getPropertyDTO();
						logger.debug(" deed value is :" + eForm.getDutyDTO().getDeedId());
						logger.debug(" Instrument :" + 	eForm.getInstrumentDTO().getInstId());
						logger.debug("M inside p" + count);
						dutyDTO.setDeedId(eForm.getDeedId());
						MapDTO mapdto=new MapDTO();
                        double partyValue=useDTO.getMarketValue();
						double partytotal=useDTO.getTotalMarketValue();
						double sumparty=0.0;
						double biggestValue=0.0;
						double smallestValue=0.0;
						double diff;
						map1 = eForm.getMapParty(); 
						count=useDTO.getAddParty();
						count =count+1;
						useDTO.setAddParty(count);
						logger.debug("Count:-" + count + ":" + count);
						String key = null;
						logger.debug("Intial Map size is "+map1.size());
						String partyName=useDTO.getFirstName();
						logger.debug("Name  value is " + partyName);
						mapdto.setPartyName(partyName);
						mapdto.setPartyValuationId(eForm.getPropertyDTO().getValuationId());
						mapdto.setPartyLabel(eForm.getPropertyDTO().getPartyLabel());
						key =Integer.toString(count);
						mapdto.setPartyID(key);
						logger.debug("key value is " + count);
						//useDTO.setMarketValue(sumparty);
						logger.debug("Party value is " + partyValue);
						mapdto.setPartyValue(String.valueOf(partyValue));
						if(map1.containsKey(key)){
							key =Integer.toString(count++);
							useDTO.setPartyID(key);
						}
						logger.debug("map.containsValue(mapdto):"
						+ map1.containsValue(mapdto));
						logger.debug("Mkt Value is "+useDTO.getMktValue());
						logger.debug("Market Value is "+useDTO.getMarketValue());
						map1.put(key, mapdto);
						logger.debug("KEY IS :" + key);
						eForm.setMapParty(map1);
						int size=map1.size();
						int count=0;
						double[] parVal=new double[size];
						String[] partyLabel=new String[size];
						String[] valuationId=new String[size];
						logger.debug("Final Map size is "+size);
						Iterator I = map1.entrySet().iterator();
						while (I.hasNext()) {
							logger.debug(" Has next :" + I.hasNext());
							Map.Entry me = (Map.Entry) I.next();
							String tempKey = (String) me.getKey();
								try	{
									MapDTO mapdto1=(MapDTO) me.getValue();	
									logger.debug(" VALUE is below "+mapdto1.getPartyValue());
									if(mapdto1.getPartyValue()!=null){
										
									
									parVal[count]=Double.parseDouble(mapdto1.getPartyValue());
									sumparty=sumparty+Double.parseDouble(mapdto1.getPartyValue());
									}
									partyLabel[count]=mapdto1.getPartyLabel();				
									valuationId[count]=mapdto1.getPartyValuationId();
									count++;
								}
								catch(Exception e)
								{
									logger.debug("Error is "+e);	
								}
						}
						logger.debug("Market value after adding "+sumparty);	
						useDTO.setMarketValue(sumparty);
						logger.debug("MAP LENGTH"+parVal.length);	
						
						
						
					
					
						if(eForm.getDutyDTO().getExchangeFlag()!=null){
							if(eForm.getDutyDTO().getExchangeFlag().equals(CommonConstant.PARTY_LABEL_EXCHANGE))
							{
							//To get maximum value for exchange
								for(int i=0;i<parVal.length;i++)
								{
									logger.debug("Values at : "+i+"  :"+parVal[i]);	
								
									try	{
										double temp=parVal[i];
										if(partyLabel[i].equalsIgnoreCase("Party1") )
										{
										biggestValue=useDTO.getBiggestValue();
									    biggestValue=biggestValue+parVal[i];
									    useDTO.setBiggestValue(biggestValue);
										logger.debug(" biggestValue Values at : "+i+"  :"+biggestValue);	
										}
										else if(partyLabel[i].equalsIgnoreCase("Party2") )
										{		
											   smallestValue=useDTO.getSmallestValue();
											   smallestValue=smallestValue+parVal[i];
											   useDTO.setSmallestValue(smallestValue);
											   logger.debug(" smallestValue Values at : "+i+"  :"+smallestValue);	
										}
										
									}
									catch(Exception e)
									{
										logger.debug("Error is "+e);	
									}
								}
							if( useDTO.getBiggestValue()>useDTO.getSmallestValue())
							{
													
								useDTO.setMarketValue(biggestValue);	
								 logger.debug(" MarketValue Values at :  :"+biggestValue);	
														}
							else
							{
								useDTO.setBiggestValue(smallestValue);
								useDTO.setMarketValue(smallestValue);	
							}
										logger.debug("Biggest Value"+useDTO.getBiggestValue());
							
							}
						}else {

							// Getting the biggest Value
							for(int i=0;i<parVal.length-1;i++)
							{
								logger.debug("Values at : "+i+"  :"+parVal[i]);	
								logger.debug("Values at : "+i+1+"  :"+parVal[i+1]);	
								try	{
									double temp=parVal[i];
									if(parVal[i]<parVal[i+1])
									{
									parVal[i]=parVal[i+1];
									parVal[i+1]=temp;
									}
																
								}
								catch(Exception e)
								{
									logger.debug("Error is "+e);	
								}
							}
							//Logic for Partiton deed
							useDTO.setBiggestValue(parVal[0]);
							diff=useDTO.getMarketValue()-useDTO.getBiggestValue();
							useDTO.setMarketValue(diff);
							exchangeDTO.setMarketValue(diff);
							logger.debug("Difference Value"+useDTO.getBiggestValue());
							logger.debug("New Market Value AFTER SUBTRACTING BIGGEST"+useDTO.getMarketValue());
							}
													
							bo.captureExchangeValuation(exchangeDTO);
							
							logger.debug("Outside Party Length"+partyLabel.length);
							logger.debug("Outside valuation length"+valuationId.length);
							for(int i=0;i<valuationId.length;i++)
							{
								
								bo.updateUserDetails(valuationId[i],eForm.getPropertyDTO().getValuationId(),partyLabel[i]);
								
							}
						
                        logger.debug("Final Map size is "+map1.size());
				   	}
					
					
				     	double duty = bo.getDutyCalculation(eForm.getDutyDTO(),
							eForm.getInstrumentDTO(), eForm.getExemptionDTO(),
							useDTO);
				     	
	
					    String value=(myformatter.format(duty));
				     	
						logger.debug("Stamp Duty is after converstion  : " + value);
				     	if (duty > 0) {
							if (value != null) {
								dutyDTO.setStampDuty(Double
										.parseDouble(value));
								dutyDTO.setStampDutyDisplay(value);
							}
							logger.debug("Stamp Duty is : " + value);
						}else if (duty < 0)
						{
							dutyDTO.setStampDuty(0.0);
							dutyDTO.setStampDutyDisplay("0");
						}
				     	
				     	dutyDTO.setStampDuty(duty);
				    	double total = 0.0;
				     	double dutyArray[] = new double[3];
				     	double nagarPalikaDuty = 0.0;
				     	double panchayatDuty = 0.0;
				     	double upkarDuty = 0.0;

						logger.debug("Fetching municipal duties ");
				     	
				     	dutyArray = bo.getMunicipalDutyCalculation(eForm
							.getDutyDTO(), eForm.getInstrumentDTO(), eForm
							.getExemptionDTO(), useDTO);

				     	logger.debug("NAGAR PALIKA DUTY" + dutyArray[0]
							+ "PANCHAYAT DUTY" + dutyArray[1] + "UPKAR DUTY"
							+ dutyArray[2]);

				     	if (dutyArray.length >= 1) {
						nagarPalikaDuty = (dutyArray[0]);
						useDTO.setNagarPalikaDuty((nagarPalikaDuty));
						panchayatDuty = (dutyArray[1]);
						useDTO.setPanchayatDuty(dutyArray[1]);
						upkarDuty = (dutyArray[2]);
						useDTO.setUpkarDuty(dutyArray[2]);

				     	}

				     	dutyDTO.setDateCalculation(common.getCurrentDate());
				     	InstrumentsDTO instDTO = eForm.getInstrumentDTO();
				     	ExemptionDTO exemptionDTO = eForm.getExemptionDTO();
				     	String[] exemptions = exemptionDTO.getHdnExemptions() == null ? null
							: exemptionDTO.getHdnExemptions().split("~,");
				     	if(exemptions!=null)
				     	{
				     	exemptions[exemptions.length-1]=exemptions[exemptions.length-1].replace("~","");
				     	}
				     	logger.debug("Exemption List:-"
							+ exemptionDTO.getHdnExemptions());
				    	logger.debug("Exemption is "+exemptionDTO.getHdnExAmount());
				    	logger.debug("Exemption 2nd is "+exemptionDTO.getExemId());
				    	
				     	eForm.setSelectedExemptionList(bo
							.getExemptionList(exemptions));
				     	logger.debug(exemptions);
				     	eForm.setDutyDTO(dutyDTO);
				     	eForm.setInstrumentDTO(instDTO);
				     	eForm.setExemptionDTO(exemptionDTO);
				     	String[] subclauseary = useDTO.getHdnSubClauseName() == null ? null
							: useDTO.getHdnSubClauseName().split("~,");
						
						if(subclauseary!=null)
				     	{
							subclauseary[subclauseary.length-1]=subclauseary[subclauseary.length-1].replace("~","");
				     	}
				     	int propertyId = useDTO.getPropertyTypeID();
				     	HashMap mapsubclause = eForm.getMapBuilding();
				     	Iterator Itr = mapsubclause.entrySet().iterator();
				     	while (Itr.hasNext()) {
						Map.Entry mee = (Map.Entry) Itr.next();
						PropertyValuationDTO mapDTO = (PropertyValuationDTO) mee
								.getValue();
						logger.debug("display action get KKK:-" + mee.getKey()
								+ ":" + mapDTO.getHdnSubClauseName());
				     	}
				     	logger.debug("out side the plot option");
				     	logger.debug("subclause name:-"
							+ useDTO.getHdnSubClauseName());
				     	if (propertyId == CommonConstant.BUILDING_ID_NEW) {
						HashMap selectMap = bo.getSubClauseMap(mapsubclause,
						subclauseary, useDTO.getTypeFloorID());
						Iterator I = selectMap.entrySet().iterator();
						while (I.hasNext()) {
						Map.Entry me = (Map.Entry) I.next();
						logger.debug("Inside Action getKey :-"
							+ me.getKey());
						ArrayList list = (ArrayList) me.getValue();
						for (int i = 0; i < list.size(); i++) {
							PropertyValuationDTO dt = (PropertyValuationDTO) list
								.get(i);
							logger.debug("Inside Action :-"
								+ dt.getSubClause());
							}
						}
						eForm.setSelectedMap(selectMap);
				     	} else {
				     				eForm.setSelectedSubclause(bo
				     				.getSubClauseList(subclauseary));
				     	}
				     	for (int i = 0; i < subclauseary.length; i++) {
						logger.debug(subclauseary[i]);
				     	}
				     	double regFee = bo.getRegistrationFee(eForm.getDutyDTO(),
				 		eForm.getInstrumentDTO(), eForm.getExemptionDTO());		
				     			
				     			if (regFee > 0) {
									String valuea = common.getCurrencyFormat(regFee);
									if (valuea != null) {
										useDTO.setRegistrationFee(Double
												.parseDouble(valuea));
									}
									logger.debug("Registraion Fee is -----" + regFee);
								}else if (regFee <= 0)
								{
									useDTO.setRegistrationFee(0.0);
								}
									     		     	
				     	
				     /*	String[] municipalDuty = bo.getMunicipalDuty(useDTO);
				     	double mDuty = municipalDuty[2] == null ? 0.0 : Double
							.parseDouble(municipalDuty[2]);
				     	if (municipalDuty != null) {
						useDTO.setMunicipalDutyName(municipalDuty[1]);
						useDTO.setMunicipalDuty(common.getCurrencyFormat(mDuty));
				     	}
				     )*/
				     	nagarPalikaDuty = (dutyArray[0]);
				     	panchayatDuty = (dutyArray[1]);
				     	upkarDuty = (dutyArray[2]);
				     	 //Added for  new observations in duties upkar in case of plot and agriland only
				     	if (propertyId == CommonConstant.BUILDING_ID_NEW) {
		   		           	 upkarDuty=0;
           						   	}
				     	//others gov body 
				     	if (useDTO.getMunicipalBodyID()=='4') {
				     		panchayatDuty=0;
          						   	}
					    
				     	logger.debug("NAGAR PALIKA DUTY" + nagarPalikaDuty
							+ "PANCHAYAT DUTY" + panchayatDuty + "UPKAR DUTY"
							+ upkarDuty);
				     	
				     	total = nagarPalikaDuty + panchayatDuty + upkarDuty + duty;
			
				     	if (total > 0) {
							String valuea = common.getCurrencyFormat(total);
							if (valuea != null) {
								useDTO.setTotal(Double
										.parseDouble(valuea));
							}
							logger.debug("Total Value is "+total);
						}else if(total <= 0) {
							useDTO.setTotal(0.0);
						}
				     	
				       String totalDisplay=(myformatter.format(	useDTO.getTotal()));
					   dutyDTO.setTotalDisplay(totalDisplay);
				     	
				     	bo.captureStampDetails(eForm
								.getDutyDTO(), eForm.getInstrumentDTO(), eForm
								.getExemptionDTO(), useDTO);
				     
				     	eForm.setPropertyDTO(bo.getPropertyValuationDTO(useDTO));
				     	
				     	

						dutyDTO.setNagarPalikaDutyDisplay(myformatter.format(useDTO.getNagarPalikaDuty()));
						dutyDTO.setPanchayatDutyDisplay(myformatter.format(useDTO.getPanchayatDuty()));
						dutyDTO.setUpkarDutyDisplay(myformatter.format(useDTO.getUpkarDuty()));
						dutyDTO.setStampDutyDisplay(myformatter.format(dutyDTO.getStampDuty()));
						dutyDTO.setRegFeeDisplay(myformatter.format(useDTO.getRegistrationFee()));
				     	
				     	forwardJsp = CommonConstant.DISPLAY_PROPERTY_PAGE;
				     	
				     	if (eForm.getModuleName()!=null)
				     	{
				     	if(eForm.getModuleName().equals(CommonConstant.REGISTRATION_VALUATION))
				     			{
				     		forwardJsp  = CommonConstant.REGISTRATION_PAGE;
				     		session.setAttribute("eform",eForm);				     		
				     		//following added by roopam
				     		request.setAttribute("regFormDashboard",session.getAttribute("regFormProp"));
				     		session.removeAttribute("regFormProp");
				     		//end of addition by roopam					     	
				     		}
				     		if(eForm.getModuleName().equals(CommonConstant.ADJUDICATION_VALUATION))
				     		{
				     			forwardJsp  = CommonConstant.ADJUDICATION_PAGE;
				     			session.setAttribute("eform",eForm);				     			
		     					     	
		     				}
				     	}
				  
				}
					if (CommonConstant.PROPERTY_VALUATION_SUBCLAUSE_PREV_HOME
						.equals(actionName)) {
					String subpage =	eForm.getPropertyTypeName();
					useDTO = eForm.getPropertyDTO();
					useDTO.setHdnSubClause(null);
				eForm.setPreviousFlag(1);
					if (CommonConstant.PROPERTY_TYPE_PLOT.equals(subpage)) {

						forwardJsp = CommonConstant.VALUATION_PLOT_PAGE;
						} else if (CommonConstant.PROPERTY_TYPE_BUILDING
							.equals(subpage)) {
							useDTO = eForm.getPropertyDTO();
	
						//eForm.setHdnfloorCalcType(null);
	

						HashMap hMap = eForm.getMapBuildingDetails();
						hMap.remove("key");
						eForm.setMapBuilding(hMap);
						useDTO.setMarketValue(useDTO.getMarketValue()-Double.parseDouble(useDTO.getMktValue()));
						useDTO.setHdnSubClause(null);
						
						logger.debug("Inside previous");
						logger.debug("Forwarding to Building page");
						forwardJsp = CommonConstant.VALUATION_BUILDING_PAGE;
					} else if (CommonConstant.PROPERTY_TYPE_AGRI_LAND
							.equals(subpage)) {

						forwardJsp = CommonConstant.VALUATION_AGRI_PAGE;
						// forward to agricultureland page
						logger.debug("Forwarding to Agriculture Land page");
					}

				}

			}

			// End PropertyValuation2.jsp

		}
		ActionForward returnActionForward = mapping.findForward(forwardJsp);
		if (logger.isDebugEnabled()) {
			logger.debug("execute(ActionMapping, " + "ActionForm, "
					+ "HttpServletRequest, " + "HttpServletResponse) - end");
		}
		return returnActionForward;
	}
}
