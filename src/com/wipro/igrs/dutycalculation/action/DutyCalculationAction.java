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
package com.wipro.igrs.dutycalculation.action;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.dutycalculation.bo.DutyCalculationBO;
import com.wipro.igrs.dutycalculation.constant.CommonConstant;
import com.wipro.igrs.dutycalculation.form.DutyCalculationForm;
import com.wipro.igrs.propertyvaluation.bo.PropertyValuationBO;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;

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
	private String forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
	/**
	 * @author Madan Mohan
	 */
	private Logger logger = (Logger) Logger
			.getLogger(DutyCalculationAction.class);

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
			HttpSession session )
		throws Exception {
		
		if (form != null) {
			
			DutyCalculationForm eForm = (DutyCalculationForm) form;
			DutyCalculationBO bo = new DutyCalculationBO();
			PropertyValuationBO pbo=new PropertyValuationBO();
			
			eForm.getDutyCalculationDTO().setCurrentYear(pbo.getCurrentYear());
			String page = (String) request.getParameter("page");
			
		    String userId=(String)session.getAttribute("UserId");	
			
			eForm.getDutyCalculationDTO().setUserId(userId);
			String locale=CommonConstant.LANGUAGE_HINDI;
			Locale currentLocale;
			if(session.getAttribute("org.apache.struts.action.LOCALE")!=null){
					currentLocale=(Locale)session.getAttribute("org.apache.struts.action.LOCALE");
					locale=currentLocale.toString();
					
				}
			logger.debug("Page  " + page);
			DutyCalculationDTO dto = eForm.getDutyCalculationDTO();
			IGRSCommon common = new IGRSCommon();
			logger.debug("Before action");
			String formName = dto.getFormName();
			String actionName = dto.getActionName();
	
			String modName = (String) request.getParameter("modName");
			logger.debug("modName:-"+modName);
			String fnName = (String) request.getParameter("fnName");
			logger.debug("modName:-"+modName);
		   			    			
    		
			if(modName != null && fnName != null){
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
				dto.setDob("");
				dto.setDobDay("");
				dto.setDobMonth("");
				dto.setDobYear("");
				dto.setSex("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setBaseValue("0.0");//double to string
				eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				dto.setActionName("");
				forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
			
			}
			if (CommonConstant.REGISTRATION_DUTY_PAGE.equals(page))
			{
				

				eForm.setExemptionDTOList(new ArrayList());
				eForm.setInstrumentDTOList(new ArrayList());
				eForm.setDutyCalculationDTO(new DutyCalculationDTO());
				//dto.setFirstName("");
				//dto.setMidName("");
				//dto.setLastName("");
				//dto.setDob("");
				//dto.setDobDay("");
				//dto.setDobMonth("");
				//dto.setDobYear("");
				//dto.setSex("");
				eForm.setExempDTO(new ExemptionDTO());
				eForm.getExempDTO().setHdnExempAmount("");
				eForm.getExempDTO().setHdnExAmount("");
				dto.setDutyPaid(0.0);
				dto.setDeedId(0);
				dto.setBaseValue("0.0");//double to string
				//eForm.setModuleName("");
				eForm.setDutyCalculationDTO(dto);
				eForm.setInstDTO(new InstrumentsDTO());
				dto.setActionName("");
				forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
			
			
				eForm.setModuleName(CommonConstant.REGISTRATION_DUTY_PAGE);
				formName="dutyHomePage";
				actionName ="nextPage";
			}
			
			logger.debug("actionName:-"+actionName+":"+formName);
			logger.debug("forwardJsp:-"+forwardJsp);
			
			if (CommonConstant.DUTY_HOME_PAGE.equals(formName)) {
				if(CommonConstant.CANCEL_ACTION.equals(actionName)) {
					forwardJsp = "welcome";
				}
				if(CommonConstant.RESET_PAGE_ACTION.equals(actionName)) {
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
					actionName="";
					forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
	
				}
				if (CommonConstant.DUTY_NEXT_ACTION.equals(actionName)) {

					
					//dto.setDeedId(dto.getDeedId());
					if (eForm.getModuleName()!=null&&eForm.getModuleName().equalsIgnoreCase(CommonConstant.REGISTRATION_DUTY_PAGE))
					{
						eForm.setDutycalculationDTOList(bo.getDeed(CommonConstant.NON_DUTY_REGISTRATION_DEED ));
						//added by lavi
						dto.setFromReg(1);
					}
					else
					{

						eForm.setDutycalculationDTOList(bo.getDeed(CommonConstant.NON_DUTY_DEED));
					}
				
					if(request.getParameter("fromAdju")!=null){
						//RegCommonForm rForm= new RegCommonForm();
		    			if(request.getParameter("fromAdju").equalsIgnoreCase("Y")){
		        			
		        			eForm.setFromAdjudication(1);                  			         			
		        			//session.setAttribute("fnName","Adjudication");
							//rForm.setFromAdjudication(eForm.getFromAdjudication());
		        		}else{
		        			eForm.setFromAdjudication(0);
		        			//rForm.setFromAdjudication(eForm.getFromAdjudication());
		        			//session.setAttribute("fnName","Initiation");
		        		}
		    			
		    			
		    		}else{
		    			eForm.setFromAdjudication(0);
		    		}
					
					if(locale.equalsIgnoreCase("hi_IN")){
						dto.setSex("M".equals(dto.getSex()) ? "पुस्र्ष" : "F"
							.equals(dto.getSex()) ? "महिला " : "");
					}else{
						dto.setSex("M".equals(dto.getSex()) ? "Male" : "F"
							.equals(dto.getSex()) ? "Female" : "");
					}

					
				
					dto.setActionName(null);
					actionName="";
					eForm.setDutyCalculationDTO(dto);
				
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					
				}

			}
			if (CommonConstant.DUTY_NEXT_PAGE.equals(formName)) {
			
				logger.debug("actionName:-"+actionName);
				if(CommonConstant.DUTY_PREV_ACTION.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					dto.setFirstName(dto1.getFirstName());
					dto.setMidName(dto1.getMidName());
					dto.setLastName(dto1.getLastName());
					dto.setAge(dto1.getAge());
					if(locale.equalsIgnoreCase("hi_IN")){
						dto.setSex("M".equals(dto.getSex()) ? "पुस्र्ष" : "F"
							.equals(dto.getSex()) ? "महिला " : "");
					}else{
						dto.setSex("Male".equals(dto1.getSex()) ? "M" : "Female"
							.equals(dto1.getSex()) ? "F" : "");
					}
				
					 
					
					logger.debug("Name:-"+dto1.getFirstName());
					eForm.setDutyCalculationDTO(dto);
					dto.setActionName(null);
					actionName="";
					forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
					
				}
				logger.debug(" before change action actionName:-"+actionName);
				if (CommonConstant.DEED_CHANGE_ACTION.equals(actionName)) {
					DutyCalculationDTO dto1 = eForm.getDutyCalculationDTO();
					int deedId=dto1.getDeedId();
					eForm.setHdnAmount("");
					eForm.getDutyCalculationDTO().setBaseValue("0.0");//double to string
					eForm.getDutyCalculationDTO().setAnnualRent(0.0);
					eForm.getDutyCalculationDTO().setDutyPaid(0.0);
					eForm.setInstDTO(new InstrumentsDTO());
					eForm.setExempDTO(new ExemptionDTO());
					eForm.setExemptionDTOList(new ArrayList());
					ArrayList  instList 
					= bo.getInstrument(deedId,locale);

				/*
				 * ArrayList<ExemptionDTO> exemDTOList
				 * =bo.getExemptions(dto.getDeedId());
				 */

					eForm.setInstrumentDTOList(instList);
				
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					dto.setActionName(null);
					actionName="";
				}
				
				if (CommonConstant.DUTY_CALCULATE_ACTION.equals(actionName)) {
					
					InstrumentsDTO instdto = eForm.getInstDTO();
					ExemptionDTO exedto = eForm.getExempDTO();
					DutyCalculationDTO dtoForm = eForm.getDutyCalculationDTO();
					
					//System.out.println("The exemption value is :"+exedto.getHdnExAmount());
					//System.out.println("The exemption value is for :"+exedto.getHdnExempAmount());
					logger.debug("The exemption NAME is :"+exedto.getHdnExemptions());
					//System.out.println("The exemption value is :"+exedto.getExemType());
					
					if (eForm.getModuleName()!=null && !eForm.getModuleName().equalsIgnoreCase(CommonConstant.REGISTRATION_DUTY_PAGE))
					{
					 Calendar calendar=new GregorianCalendar();
					 int currYear=calendar.get(Calendar.YEAR);
					 int currMonth=calendar.get(Calendar.MONTH)+1;
					 int currDate=calendar.get(Calendar.DATE);
					 
					int dobYear=Integer.parseInt(dtoForm.getDobYear());
					int dobMonth=Integer.parseInt(dtoForm.getDobMonth());
					int dobDate=Integer.parseInt(dtoForm.getDobDay());
					int age=0;
					age=(currYear-1)-dobYear;
					if(currMonth>dobMonth){
						age=age+1;
					}
					else if(currMonth==dobMonth){
						if(currDate>=dobDate){
							age=age+1;
						}
					}
					if(age>130){
						age=130;
					}
					eForm.getDutyCalculationDTO().setAge(age);
				}
					String[] exemptions = exedto.getHdnExemptions() == null ? null :
						exedto.getHdnExemptions().split("~");
				
										
					double stampDuty = bo.getDutyCalculation(dtoForm,
														instdto,
														exedto);
			
					dtoForm.setStampDuty(stampDuty);
					
					if  (stampDuty<0)
					{
						stampDuty=0.0;
						dtoForm.setStampDuty(stampDuty);
					}
					double dutyArray[]=new double[3];
					double nagarPalikaDuty=0.0;
					double panchayatDuty=0.0;
					double upkarDuty=0.0;
					
					dutyArray=bo.getMunicipalDutyCalculation(dtoForm,instdto,exedto);
					
					//System.out.println("NAGAR PALIKA DUTY"+dutyArray[0]+"PANCHAYAT DUTY"
							//+dutyArray[1]+"UPKAR DUTY"+dutyArray[2]);
					
			
					
					if( dutyArray.length>=1)
					{	
					   	nagarPalikaDuty=(dutyArray[0]);
					   	dtoForm.setNagarPalikaDuty((nagarPalikaDuty));
						panchayatDuty=(dutyArray[1]);
						dtoForm.setPanchayatDuty(dutyArray[1]);
						upkarDuty=(dutyArray[2]);
						dtoForm.setUpkarDuty(upkarDuty);
					}
					
					
				
					
					//System.out.println("NAGAR PALIKA DUTY"+nagarPalikaDuty+"PANCHAYAT DUTY"
							//+panchayatDuty+"UPKAR DUTY"+upkarDuty);
					
				double	total =nagarPalikaDuty+panchayatDuty+upkarDuty+ stampDuty;
				dtoForm.setTotal(total);
					
		
		     	
				//dtoForm.setTotal(common.getCurrencyFormat(total));
				
				
				//session.setAttribute("nagarPalikaDuty",nagarPalikaDuty);
				//session.setAttribute("panchayatDuty",panchayatDuty);
				//session.setAttribute("upkarDuty",upkarDuty);
				//session.setAttribute("total",total);
					
				//	session.setAttribute("DutyDTO", dtoForm);
				//	session.setAttribute("InstrumentDTO", instdto);
				//	session.setAttribute("ExemptionDTO", exedto);
				//	session.setAttribute("duty", duty);
					
					
					double regFee = bo.getRegistrationFee(dtoForm,instdto,exedto);
					dtoForm.setRegFee(regFee);
					//	session.setAttribute("regFee",regFee);
						if  (regFee<0)
						{
						regFee=0.0;
						dtoForm.setRegFee(regFee);
						}
						logger.debug("Reg Fee:-"+regFee);
					
					}
			     	
				}
				if (CommonConstant.DUTY_RADIO_ACTION.equals(actionName)) {
					InstrumentsDTO instdto = eForm.getInstDTO();
					DutyCalculationDTO dtoId =eForm.getDutyCalculationDTO();
					logger.debug("Inside radio butto");
				
					int deedId =  dtoId.getDeedId();
					int instId = instdto.getInstId();
			
		
					
					
					ArrayList  exemDTOList 
						= bo.getExemptions(deedId, instId);
				
				eForm.setExemptionDTOList(exemDTOList);
				
				logger.debug("After exemption list");

					// dtoId.setBaseValue(dto.getBaseValue());

					eForm.setDutyCalculationDTO(dtoId);

					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
				}
				if(CommonConstant.DUTY_CALCULATE_ACTION.equals(actionName)) {
					DutyCalculationBO boexemption = new DutyCalculationBO();
									DutyCalculationDTO dutyDTO = eForm.getDutyCalculationDTO();
					InstrumentsDTO instrumentDTO =  eForm.getInstDTO();
					ExemptionDTO exemptionDTO = 
						 eForm.getExempDTO();
					//RegCommonForm rForm= new RegCommonForm();
					//rForm.setFromAdjudication(eForm.getFromAdjudication());
					
					request.setAttribute("fromAdju",eForm.getFromAdjudication());
					
					logger.debug("from ADJU type"+	request.getAttribute("fromAdju"));
					/*if(rForm.getFromAdjudication()==1){
						session.setAttribute("fnName","Adjudication");
						}*/
						
						
					logger.debug(exemptionDTO);
						int inst = instrumentDTO.getInstId();
						int deed = dutyDTO.getDeedId();
						System.out.println(exemptionDTO.getHdnExemptions() );
					/*String[] exemptions = exemptionDTO.getHdnExemptions() == null ? null :
						exemptionDTO.getHdnExemptions().split(", ");*/
						
						String[] exemptions;
					
					if(exemptionDTO.getHdnExemptions()!=null && !exemptionDTO.getHdnExemptions().equalsIgnoreCase("")){
						exemptions=exemptionDTO.getHdnExemptions().split(", ");
					}else{
						exemptions=null;
					}
					
					//String[] exem = exemptionDTO.getHdnExempAmount().split("
					//~");
			
					
					
					eForm.setSelectedExemptionList(
							boexemption.getExemptionList(exemptions)); 
					
					if(eForm.getSelectedExemptionList().size()<=0){
						eForm.setSelectedExemptionList(null);
					}
					
					DecimalFormat myformatter=new DecimalFormat("############");     
					
					bo.captureNonPropStampDetails(eForm.getDutyCalculationDTO(), eForm.getInstDTO(), eForm.getExempDTO());
					
					//dutyDTO.setBaseValueDisplay(String.valueOf(dutyDTO.getBaseValue()));
				//	dutyDTO.setBaseValueDisplay(String.valueOf(dutyDTO.getBaseValue()));
				//	dutyDTO.setBaseValueDisplay(common.getCurrencyFormatBaseValue(dutyDTO.getBaseValue()));//double to string
					dutyDTO.setBaseValueDisplay(dutyDTO.getBaseValue());//double to string
				//	BigDecimal bigD=new BigDecimal(dutyDTO.getBaseValue()).setScale(20,RoundingMode.HALF_EVEN);
					
					//new BigDecimal(dutyDTO.getBaseValue()).setScale(7, 7);
					//dutyDTO.setBaseValueDisplay(String.valueOf(bigD.doubleValue()));
					//bigD.setScale(7, 7);
					
					dutyDTO.setAnnualRentDisplay(myformatter.format(dutyDTO.getAnnualRent()));
					//dutyDTO.setBaseValueDisplay(myformatter.format(dutyDTO.getBaseValue()));
					dutyDTO.setDutyAlreadyPaidDisplay(myformatter.format(dutyDTO.getDutyAlreadyPaid()));
					dutyDTO.setDutyPaidDisplay(myformatter.format(dutyDTO.getDutyPaid()));
					dutyDTO.setDateCalculation(common.getCurrentDate());
					dutyDTO.setTotalDisplay(myformatter.format(dutyDTO.getTotal()));
					dutyDTO.setNagarPalikaDutyDisplay(myformatter.format(dutyDTO.getNagarPalikaDuty()));
					dutyDTO.setPanchayatDutyDisplay(myformatter.format(dutyDTO.getPanchayatDuty()));
					dutyDTO.setUpkarDutyDisplay(myformatter.format(dutyDTO.getUpkarDuty()));
					dutyDTO.setStampDutyDisplay(myformatter.format(dutyDTO.getStampDuty()));
					dutyDTO.setRegFeeDisplay(myformatter.format(dutyDTO.getRegFee()));
				/*	session.setAttribute("baseValue",
							common.getCurrencyFormat(dto.getBaseValue()));
					session.setAttribute("duty", 
							common.getCurrencyFormat(Double.parseDouble(duty)));
					session.setAttribute("dateCalculation", 
							common.getCurrentDate());
								
					session.setAttribute("annualRent",
							common.getCurrencyFormat(dto.getAnnualRent()));
					
					session.setAttribute("dutyPaid",
							common.getCurrencyFormat(dto.getDutyPaid()));
					*/
					
					logger.debug("Stamp Id"+	dutyDTO.getStampId());
					request.setAttribute("stampId", 
							dutyDTO.getStampId());
					logger.debug("Stamp Id"+	request.getAttribute("stampId"));
					eForm.setDutyCalculationDTO(dutyDTO);
					eForm.setExempDTO(exemptionDTO);
					eForm.setInstDTO(instrumentDTO);
					
					logger.debug("base Value:-"+dutyDTO.getBaseValue());
					logger.debug("Deed Name:-"+dutyDTO.getDeedType());
					logger.debug("Instrument Name:-"
							+instrumentDTO.getInstType());
					logger.debug("Exemption Name:-"
							+exemptionDTO.getHdnExemptions());
					
					if (eForm.getModuleName()!=null&&eForm.getModuleName().equalsIgnoreCase(CommonConstant.REGISTRATION_DUTY_PAGE))
					{
						forwardJsp = CommonConstant.FORWARD_NONREGINIT_DISPLAY;
						dto.setActionName(null);
						actionName="";
					}
					else
					{

						forwardJsp = CommonConstant.FORWARD_PAGE_DISPLAY;
						dto.setActionName(null);
						actionName="";	
					}
								
				}
				if(CommonConstant.CANCEL_ACTION.equals(actionName)) {
					dto.setActionName("");
					forwardJsp = "welcome";
				}
				if(CommonConstant.RESET_PAGE_ACTION.equals(actionName)) {
					eForm.setExemptionDTOList(new ArrayList());
					eForm.setInstrumentDTOList(new ArrayList());
					DutyCalculationDTO dto1 = new DutyCalculationDTO();
					
					/*dto.setFirstName(dto1.getFirstName());
					dto.setMidName(dto1.getMidName());
					dto.setLastName(dto1.getLastName());
					dto.setAge(dto1.getAge());
					dto.setSex("Male".equals(dto1.getSex()) ? "M" : "Female"
						.equals(dto1.getSex()) ? "F" : "");
					*/
					dto.setDeedId(dto1.getDeedId());
					dto.setBaseValue("0.0");//double to string
			
					eForm.setInstDTO(new InstrumentsDTO());
					eForm.setDutyCalculationDTO(dto);
					forwardJsp = CommonConstant.FORWARD_PAGE_NEXT;
					dto.setActionName(null);
					actionName="";
				}
			}
			

		

		return mapping.findForward(forwardJsp);
	}
}
