package com.wipro.igrs.regcompletion.action;


/**
 * ===========================================================================
 * File           :   CommonAction1.java
 * Description    :   Represents the Common Action1 Class
 * @author        :   Piyush
 * Created Date   :   May 02, 2008
 * ===========================================================================
 */

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.regcompletion.bd.RegComp1BD;
import com.wipro.igrs.regcompletion.constants.RegCompConstant;
import com.wipro.igrs.regcompletion.dto.Common1DTO;
import com.wipro.igrs.regcompletion.form.ApplicantForm1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;


public class CommonAction1 extends 
BaseAction  {
	private  Logger logger = 
		(Logger) Logger.getLogger(CommonAction1.class);
    /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException, 
                                                                      ServletException,Exception {
    	
    	String FORWAD_SUCCESS="";
    	ApplicantForm1 fm = (ApplicantForm1) form;
    	logger.debug("WE ARE IN CommonAction Debug");
		logger.info("WE ARE IN  CommonAction INFO");
		String strPartyId = request.getParameter("partyId1");
		RegComp1BD refBD=new RegComp1BD();
    	Common1DTO cDTO=fm.getCommonDTO();
    	String formName=cDTO.getFormName();
    	String actionName=cDTO.getActionName();
        if(form!=null)
        {
        	try{		        	
        		String param = (String) request.getParameter("param") ;
        		if("start".equals(param)) {
        			// Edited by Aruna
        			//cDTO.setFormName("");
        			//cDTO.setActionName("");
        			FORWAD_SUCCESS=listShow(mapping, form, request, response);
        			ArrayList partyTxnList=cDTO.getRecordsBuffer();        			
        			if(partyTxnList==null || partyTxnList.size()==0)
        			{
	        			cDTO.setCountryList(refBD.countryStackBD());
		            	cDTO.setPhotoIdList(refBD.photoIdStackBD());
		            	cDTO.setCasteList(refBD.casteStackBD());
		            	cDTO.setReligionList(refBD.religionStackBD());
		            	// Added By Aruna
		            	resetValues(cDTO, refBD);
		               /* if(RegCompConstant.REGCOMP_FORM_MODIFY.equalsIgnoreCase(formName))
		                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_MODIFYPARTY;
		                else*/
		                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_ADDPARTY;
        			}
        			
        		}
        		logger.debug("formName:-"+formName);
        		logger.debug("actionName:-"+actionName);
        		
        		// Added By Aruna
        		String partyIDValue = (String) request.getParameter("partyID") ;
        		if(partyIDValue!=null) {
        			cDTO=refBD.viewParty(partyIDValue);
	            	if(cDTO==null)
	            	{
	            		FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_FAILURE;
	            	}
	            	else
	            	{
	            		logger.info("Party_Id is not found");
	            		fm.setCommonDTO(cDTO);
	            		if(cDTO.getOrganizationName()==null || cDTO.getOrganizationName().equalsIgnoreCase(""))
	            		{
	            			logger.info("Organization Details are avail");
	            			//FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_VIEWPARTY;
	            			 FORWAD_SUCCESS="CompleteIndPartyDtls";
	            		}
	            		else
	            		{
	            			logger.info("Organization Details NOT NULL");
	            			//FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_VIEWORGA;
	            			FORWAD_SUCCESS="CompleteOrgPartyDtls";
	            		}
	            	}
	            	  return mapping.findForward(FORWAD_SUCCESS);
        		}
        		/*if(actionName==null || "".equalsIgnoreCase(actionName))
	            {
	            	logger.info("Inside COUTNRY Action");
	            	cDTO.setCountryList(refBD.countryStackBD());
	            	cDTO.setPhotoIdList(refBD.photoIdStackBD());
	            	cDTO.setCasteList(refBD.casteStackBD());
	            	cDTO.setReligionList(refBD.religionStackBD());
	                if(RegCompConstant.REGCOMP_FORM_MODIFY.equalsIgnoreCase(formName))
	                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_MODIFYPARTY;
	                else
	                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_ADDPARTY;
	            }*/
	            /*if(RegCompConstant.REGCOMP_STATE.equalsIgnoreCase(actionName))
	            {
	            	logger.info("Inside STATE Action");
	            	saveToken(request);
	            	cDTO.setStateList(refBD.stateStackBD(cDTO.getCountryName()));
	            	cDTO.setDistrictList(null);
	            	if(RegCompConstant.REGCOMP_FORM_MODIFY.equalsIgnoreCase(formName))
	                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_MODIFYPARTY;
	                else
	                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_ADDPARTY;
	            }
	            else if(RegCompConstant.REGCOMP_DISTRICT.equalsIgnoreCase(actionName))
	            {
	            	saveToken(request);
	            	logger.info("Inside DISTRICT Action");
	            	cDTO.setDistrictList(refBD.districtStackBD(cDTO.getStateName()));
	            	if(RegCompConstant.REGCOMP_FORM_MODIFY.equalsIgnoreCase(formName))
	                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_MODIFYPARTY;
	                else
	                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_ADDPARTY;
	            }*/
	            if(RegCompConstant.REGCOMP_FORM_ADD.equalsIgnoreCase(formName))
	            {
	            		
	            	if(RegCompConstant.REGCOMP_STATE.equalsIgnoreCase(actionName))
		            {
		            	logger.info("Inside STATE Action");
		            	saveToken(request);
		            	cDTO.setStateList(refBD.stateStackBD(cDTO.getCountryName()));
		            	cDTO.setDistrictList(null);
		             
		                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_ADDPARTY;
		            }
		            if(RegCompConstant.REGCOMP_DISTRICT.equalsIgnoreCase(actionName))
		            {
		            	saveToken(request);
		            	logger.info("Inside DISTRICT Action");
		            	cDTO.setDistrictList(refBD.districtStackBD(cDTO.getStateName()));
		            	 
		                FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_ADDPARTY;
		            }
	            	if(RegCompConstant.REGCOMP_ACTION_SAVEPARTY.equalsIgnoreCase(actionName)|| RegCompConstant.REGCOMP_ACTION_SAVEORGA.equalsIgnoreCase(actionName))
	            	{ 
	            		logger.debug("isTokenValid(request):-"+isTokenValid(request));
	            		if(isTokenValid(request)) {
		            		
	            			logger.info("Inside Save_Party Action");
			            	boolean flag=false;
			            	//String appNo=(String)request.getSession().getAttribute("App_No");
			            	String appNo=(String)session.getAttribute("sessionRegTxnId");
			             	cDTO.setTempApplNo(appNo);
			            	if(RegCompConstant.REGCOMP_ACTION_SAVEPARTY.equalsIgnoreCase(actionName))
			            	{
			            	flag=refBD.addParty(cDTO,fm.getFilePhoto(),fm.getFileSignature(),fm.getFileThumb());
			            		cDTO.setReportMsg("Party has not been added.Please Try again");
			            	}
			            	else if(RegCompConstant.REGCOMP_ACTION_SAVEORGA.equalsIgnoreCase(actionName)&&
			            			!("".equalsIgnoreCase(actionName)))
			            	{
			            		flag=refBD.addOrga(cDTO);
			            	}
			            	logger.debug("flag value->"+flag);
			            	if(flag)
			            	{
			            		FORWAD_SUCCESS=listShow(mapping, form, request, response);
			            	}
			            	else
			            	{
			            		FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_FAILURE;
			            	}
			            	resetToken(request);
			             	/*cDTO.setFormName("");
			            	cDTO.setActionName(""); */
	            		}else {
	            			ApplicantForm1 fm1 = (ApplicantForm1) form;
	        		    	Common1DTO cDTO1=fm.getCommonDTO();
	        		    	RegComp1BD refBD1=new RegComp1BD();
	        		    	ArrayList partySearchList=new ArrayList();
	        		    	String appNo=(String)session.getAttribute("sessionRegTxnId");
	        		   
	        		    	/*if(appNo==null || "".equalsIgnoreCase(appNo))
	                    		appNo="MP123456";*/
	                    	cDTO1.setTempApplNo(appNo);
	        				partySearchList = refBD1.selectParties(appNo);
	        				cDTO1.setReportMsg("Party has been added successfully");
	        				cDTO1.setRecordsBuffer(partySearchList);
	        				request.setAttribute("partyFm", fm1);
	            			FORWAD_SUCCESS = "partyList";
	            		}
	            	} if(RegCompConstant.RESET_ACTION.equalsIgnoreCase(actionName))
		            {
	            		cDTO.setCountryList(refBD.countryStackBD());
		            	cDTO.setPhotoIdList(refBD.photoIdStackBD());
		            	cDTO.setCasteList(refBD.casteStackBD());
		            	cDTO.setReligionList(refBD.religionStackBD());
		            	resetValues(cDTO, refBD);
		            	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_ADDPARTY;		            	
		            } if((RegCompConstant.VIEW_PARTY_LIST.equalsIgnoreCase(actionName)))
		            {
		            	FORWAD_SUCCESS=listShow(mapping, form, request, response);
		            }
	            }
	            else if(RegCompConstant.REGCOMP_FORM_LIST.equalsIgnoreCase(formName))
	            {
	            	if(RegCompConstant.REGCOMP_ACTION_VIEWPARTY.equalsIgnoreCase(actionName))
	            	{
	            		logger.info("Inside View Party");
		            	String partyId=cDTO.getPartyId();
		            	cDTO=refBD.viewParty(partyId);
		            	if(cDTO==null)
		            	{
		            		FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_FAILURE;
		            	}
		            	else
		            	{
		            		logger.info("Party_Id is not found");
		            		fm.setCommonDTO(cDTO);
		            		if(cDTO.getOrganizationName()==null || cDTO.getOrganizationName().equalsIgnoreCase(""))
		            		{
		            			logger.info("Organization Details are avail");
		            			FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_VIEWPARTY;
		            		}
		            		else
		            		{
		            			logger.info("Organization Details NOT NULL");
		            			FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_VIEWORGA;
		            		}
		            	}
	            	}
	                else if(RegCompConstant.REGCOMP_ACTION_DELETEPARTY.equalsIgnoreCase(actionName))
	                {
		            	logger.info("Inside Delete Party");
		            	String partyId=cDTO.getPartyId();
		            	if(partyId!=null) {
		            		refBD.deleteParty(partyId);
		            	}
		            	 
		            	FORWAD_SUCCESS=listShow(mapping, form, request, response);
		            	 
	                }
	                else if(RegCompConstant.REGCOMP_ACTION_ADDMORE.equalsIgnoreCase(actionName))
	            	{
		            	cDTO=new Common1DTO();
		            	logger.info("Inside Adding More Party");
		                //cDTO.setCountryList(refDAO.countryStack());
		                cDTO.setCountryList(refBD.countryStackBD());
		                cDTO.setStateList(null);
		                cDTO.setPhotoIdList(refBD.photoIdStackBD());
		                cDTO.setCasteList(refBD.casteStackBD());
		                cDTO.setReligionList(refBD.religionStackBD());
		                fm.setCommonDTO(cDTO);
		                FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_ADDPARTY;
	            	}
	                else if(RegCompConstant.REGCOMP_ACTION_MODIFYPARTY.equalsIgnoreCase(actionName))
	            	{
	                	logger.info("Inside Modify Party");
		            	String partyId=cDTO.getPartyId();
		            	cDTO=refBD.retrieveParty(partyId);
		            	if(cDTO==null)
		            	{
		            		logger.info("No Party Details");
		            		FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_FAILURE;
		            	}
		            	else
		            	{
		            		cDTO.setCasteList(refBD.casteStackBD());
		            		cDTO.setReligionList(refBD.religionStackBD());
		            		cDTO.setPhotoIdList(refBD.photoIdStackBD());
		            		//cDTO.setCountryList(refDAO.countryStack());
		            		cDTO.setCountryList(refBD.countryStackBD());
		            		//cDTO.setStateList(refDAO.stateStack(cDTO.getCountryName()));
		            		cDTO.setStateList(refBD.stateStackBD(cDTO.getCountryName()));
		            		cDTO.setDistrictList(refBD.districtStackBD(cDTO.getStateId()));
		            		fm.setCommonDTO(cDTO);
		            		FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_MODIFYPARTY;
		            		// Added By Aruna
		            		saveToken(request);
		            	}
	            	}
	                else 
	                {
	                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_FAILURE;
	                }
	            }
	            else if (RegCompConstant.REGCOMP_FORM_MODIFY.equalsIgnoreCase(formName))
	            {
	            	if(RegCompConstant.REGCOMP_STATE.equalsIgnoreCase(actionName))
		            {
		            	logger.info("Inside STATE Action");
		            	saveToken(request);
		            	cDTO.setStateList(refBD.stateStackBD(cDTO.getCountryName()));
		            	cDTO.setDistrictList(null);
		                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_MODIFYPARTY;
		                 
		            }
		            else if(RegCompConstant.REGCOMP_DISTRICT.equalsIgnoreCase(actionName))
		            {
		            	saveToken(request);
		            	logger.info("Inside DISTRICT Action");
		            	cDTO.setDistrictList(refBD.districtStackBD(cDTO.getStateName()));
		                	FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_MODIFYPARTY;
		                 
		            }
	            	if(RegCompConstant.REGCOMP_ACTION_UPDATEPARTY.equalsIgnoreCase(actionName))
	            	{
	            		logger.info("Inside Updating Party Details");
	            		if(isTokenValid(request)) {
			            	if(refBD.updateParty(cDTO))
			            		FORWAD_SUCCESS=listShow(mapping, form, request, response);
			            	else
			            		FORWAD_SUCCESS=RegCompConstant.REGCOMP_FWDSUCCESS_FAILURE;
			            	resetToken(request);
	            		}
	            	}
	            	// Added By Aruna
	            	if(RegCompConstant.CANCEL_ACTION.equalsIgnoreCase(actionName))
	            	{
	            		FORWAD_SUCCESS=listShow(mapping, form, request, response);
	            	}
	            }
	            
	        }catch(Exception EXE)
	        {
	        	logger.error(EXE);
	        	return mapping.findForward(RegCompConstant.REGCOMP_FWDSUCCESS_FAILURE);
	        }
        }
        if(strPartyId != null )
        {
        	cDTO=refBD.viewParty(strPartyId);
        	fm.setCommonDTO(cDTO);
        	FORWAD_SUCCESS = RegCompConstant.REGCOMP_FWDSUCCESS_VIEWPARTY1;
        }
        request.setAttribute("regcomp1", fm);
        
        return mapping.findForward(FORWAD_SUCCESS);
    }
    public String listShow(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws IOException, 
                                                 ServletException,Exception {
    	try{
    		if(form!=null)
    		{
    			logger.debug("listShow Method");
		    	ApplicantForm1 fm = (ApplicantForm1) form;
		    	Common1DTO cDTO=fm.getCommonDTO();
		    	RegComp1BD refBD=new RegComp1BD();
		    	ArrayList partySearchList=new ArrayList();
		    	String appNo=(String)request.getSession().getAttribute("sessionRegTxnId");
		   
		    	/*if(appNo==null || "".equalsIgnoreCase(appNo))
            		appNo="MP123456";*/
            	cDTO.setTempApplNo(appNo);
				partySearchList=refBD.selectParties(appNo);
				cDTO.setReportMsg("Party has been added successfully");
				cDTO.setRecordsBuffer(partySearchList);
				request.setAttribute("partyFm", fm);
				logger.info(".................Buffer set Successfully.........");
		    	//return "partyList";
    		}
    		
    	}catch (Exception e) {
    		logger.error(e);
			//return RegCompConstant.REGCOMP_FWDSUCCESS_FAILURE;
		}
    	return "partyList";
    }
    
    public static void resetValues(Common1DTO cDTO,RegComp1BD refBD)
    {
    	cDTO.setFormName("");
		cDTO.setActionName("");
    	cDTO.setPartyType("");
    	cDTO.setFname("");
    	cDTO.setLname("");
    	cDTO.setMname("");
    	cDTO.setFatherName("");
    	cDTO.setMotherName("");
    	cDTO.setSpouseName("");
    	cDTO.setGender("");
    	cDTO.setAge(0);
    	cDTO.setCasteName("");
    	cDTO.setReligionName("");
    	cDTO.setNationality("");
    	cDTO.setPhyChallanged("");
    	cDTO.setPhotoProofTypeName("");
    	cDTO.setPhotoId("");
    	cDTO.setBankName("");
    	cDTO.setBankAddress("");
    	cDTO.setOccupation("");
    	cDTO.setOrganizationName("");
    	cDTO.setAuthPersonName("");
    	cDTO.setAddress("");
    	cDTO.setCountryName("");
    	cDTO.setStateName("");
    	cDTO.setDistrictName("");
    	cDTO.setPostalCode(0);
    	cDTO.setPhoneNo("");
    	cDTO.setMobNo("");
    	cDTO.setMailId("");
    }
}



