/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   AclcreateAction.java
 * Author      :   Mallikarjun.M
 * Description :   Represents the Action Class for ACL.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  24th March, 2008
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.ACL.action;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.ACL.bd.AclcreateBD;
import com.wipro.igrs.ACL.dto.AclcreateDTO;
import com.wipro.igrs.ACL.form.AclcreateForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class AclcreateAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(AclcreateAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		logger.info("we are in AclcreateAction");
		String target = "success";
		String frdName=request.getParameter("fwdName");
		String language = (String) session.getAttribute("languageLocale");
		String userid=request.getParameter("userId");
		/* Populate the activity form */
		AclcreateForm aclcreateForm = (AclcreateForm) form;
		AclcreateDTO dto = aclcreateForm.getAclcDTO();
		AclcreateBD aclbd = new AclcreateBD();
		String clearstatus=request.getParameter("clearstatus");
		String pageType="";
		String locale="";
		Locale currentLocale;
		if(session.getAttribute("org.apache.struts.action.LOCALE")!=null){
				currentLocale=(Locale)session.getAttribute("org.apache.struts.action.LOCALE");
				locale=currentLocale.toString();
				
			}
		aclcreateForm.setLanguage(language);
		//System.out.println( "UserID:" + userid);
		
//		if(frdName!=null && frdName.equalsIgnoreCase("start"))
//		{
//			dto.setFirstName("");
//			dto.setLastName("");
//			aclcreateForm.setFirstName("");
//			aclcreateForm.setLastName("");
//			return mapping.findForward("success");
//		}
		
		if (userid != null) {
			dto = aclbd.getUserId(userid,locale);
			aclcreateForm.setDto(dto);
			logger.debug("my ids=" + dto.getEmailId());
			session.setAttribute("testForm", aclcreateForm);
		}
		if(clearstatus!=null && clearstatus.equalsIgnoreCase("clearback"))
		{
			dto.setFirstName("");
			dto.setLastName("");
			aclcreateForm.setFirstName("");
			aclcreateForm.setLastName("");
			return mapping.findForward("success");
		}
		
		if(frdName!=null && frdName.equalsIgnoreCase("approveEmp"))
		{
			aclbd.approveUser(dto.getUserId());
			aclcreateForm.setUserId(dto.getUserId());
			//request.setAttribute("approval", "approve");
			return mapping.findForward("approveUser");
		}
		
		if(frdName!=null && frdName.equalsIgnoreCase("rejectEmp"))
		{	
			boolean boo=false;
			boo=aclbd.rejectUser(dto.getUserId());
			aclcreateForm.setUserId(dto.getUserId());
			//request.setAttribute("approval", "reject");
			return mapping.findForward("rejectUser");
		}
		String chosenCountry ="";
		//System.out.println("frdName :" + frdName );		
		if(frdName!=null && frdName.equalsIgnoreCase("create"))
		{		
			ArrayList countrylist = new ArrayList();
			countrylist = aclbd.getCountry(locale);
			dto.setUserCountryList(countrylist);
			aclcreateForm.setUserCountryList(countrylist);
//			chosenCountry = aclcreateForm.getAclcDTO().getUserCountryID();
//			dto.setCountry(chosenCountry);
			
			dto.setUserStateList(aclbd.getState(dto.getUserCountryID(),locale));
			aclcreateForm.setUserStateList(dto.getUserStateList());
			dto.setUserCityList(aclbd.getDistrictCity(dto.getUserStateID(),locale));
			aclcreateForm.setUserCityList(dto.getUserCityList());
			aclcreateForm.setAclcDTO(dto);
		}
		
		else if(frdName!=null && frdName.equalsIgnoreCase("State")){
			chosenCountry =request.getParameter("Country");
			dto.setUserCountryID(chosenCountry);
			String chosenCountryName = dto.getCountry();
			ArrayList statelist = new ArrayList();
				if(chosenCountry!=null && chosenCountry!="" ){		
					statelist = aclbd.getState(chosenCountry,locale);
				    aclcreateForm.setUserStateList(statelist);
			}
			return mapping.findForward(target);
		}

		//aclcreateForm.setUserStateList(statelist);
		
		else if(frdName!=null && frdName.equalsIgnoreCase("District")){
			
			
		System.out.println("Inside fwd: District condition....");
		String chosenState;
		chosenState=request.getParameter("State");
		dto.setStateName(chosenState);
		dto.setUserStateID(chosenState);
		ArrayList districtlist = new ArrayList();
		
		if(chosenState!=null && chosenState!=""){
			districtlist = aclbd.getDistrictCity(chosenState,locale);
			aclcreateForm.setUserCityList(districtlist);
		}
		return mapping.findForward(target);
		}
		
		
		if(request.getParameter("pageType")!=null){
		pageType=request.getParameter("pageType");
		session.setAttribute("PAGETYPE", pageType);
		dto.setFirstName("");
		dto.setLastName("");
		aclcreateForm.setFirstName("");
		aclcreateForm.setLastName("");
		dto.setUserId("");
		aclcreateForm.setUserId("");
		return mapping.findForward("success");
		}
		logger.debug("After DTO ****************88");
		
		logger.debug("Inside action---->  " + aclcreateForm);
		String strfname = "";
		String strlname = "";
		  session=request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		if (aclcreateForm != null) {
			if(aclcreateForm.getFirstName()!=null){
			strfname = aclcreateForm.getFirstName().trim();
			}else {
				strfname = aclcreateForm.getFirstName();
			}
			if(aclcreateForm.getLastName()!=null){
			strlname = aclcreateForm.getLastName().trim();
			}
			else {
				strlname = aclcreateForm.getLastName();
			}
		}
		ArrayList userList = null;
		ArrayList useraclList = null;

		if (aclcreateForm != null) {
			if (aclcreateForm.getPageName() != null)
				if (aclcreateForm.getPageName().equalsIgnoreCase("search")) {
					
					String pgtp=(String)session.getAttribute("PAGETYPE");
					if(pgtp.equalsIgnoreCase("approve")){
						userList = aclbd.getUserList1(strfname, strlname,locale);
					}
					else {
						userList = aclbd.getUserList(strfname, strlname,locale);
					}
					
					logger.debug("userList==================="
							+ userList.size());
					dto.setUserList(userList);
				
				} else {
					/* Update activity master */
					aclcreateForm.setResetPass(dto.getRepassword());
					aclbd.updateLogindetails(aclcreateForm,roleId,funId,userId);
				}

		}
		/* Parameter activityId */
		
		logger.debug("activity id in action.." + userid);
		String pgtyp=(String)session.getAttribute("PAGETYPE");
		if(pgtyp.equalsIgnoreCase("approve")){
			useraclList = aclbd.getUseraclList1(locale);
		}
		else {
			useraclList = aclbd.getUseraclList(locale);
		}
		
		logger.debug("useraclList++++++++++" + useraclList.size());
		dto.setUseraclList(useraclList);
		aclcreateForm.setDto(dto);
		/* Set the sessions */

		session.setAttribute("userList", aclcreateForm.getDto().getUserList());
		session.setAttribute("useraclList", aclcreateForm.getDto()
				.getUseraclList());
		session.setAttribute("AclcreateForm", aclcreateForm);

		return mapping.findForward(target);
	}
}
