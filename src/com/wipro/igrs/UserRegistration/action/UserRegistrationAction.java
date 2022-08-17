/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRegistrationAction.java
 * Author      :   Nihar Ranjan Mishra 
 * Description :   Represents the Action Class for User Registration.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra  26th Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.UserRegistration.action;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.octo.captcha.service.CaptchaServiceException;
import com.wipro.igrs.UserRegistration.bd.UserRegistrationBD;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.UserRegistration.dao.UserRegistrationDAO;
import com.wipro.igrs.UserRegistration.dto.UserRegistrationDTO;
import com.wipro.igrs.UserRegistration.form.UserRegistrationForm;
import com.wipro.igrs.UserRegistration.rule.UserRegistrationRule;
import com.wipro.igrs.common.IGRSCommon;


/**
 * @author nihraa
 *
 */
public class UserRegistrationAction extends Action {

	/**
	 * forwardJsp
	 */
	

	/** 
	 * Method execute for user registration.
	 * @param mapping 
	 * @param form 
	 * @param request 
	 * @param response 
	 * @return ActionForward
	 * @throws Exception 
	 */
	private Logger logger = (Logger) Logger.getLogger(UserRegistrationAction.class);
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) 
		throws Exception {
		String forwardJsp = new String("ureg");
		HttpSession requestSession = request.getSession(true);
		// POC for XSS 
		ActionForward forward = null;
		if(IGRSCommon.checkXSSData(form)){
			forward = new ActionForward("/jsp/FailureXSS.jsp");
			request.setAttribute("xssData", IGRSCommon.xssFieldName);
			logger.debug("Invalid XSS Data Token.. login..");
			requestSession.invalidate();
			return forward;
		}
		logger.info("we are in action "+form);
		if (form != null) {
			
			requestSession.setAttribute("langModule", "userReg");
			
			String languageLocale="hi";
			
			if(requestSession.getAttribute("languageLocale")!=null){
				languageLocale=(String)requestSession.getAttribute("languageLocale");
			}
			
			
			UserRegistrationForm eForm = (UserRegistrationForm) form;
			ArrayList listDistrict = new ArrayList();
			ArrayList listState = new ArrayList();
			UserRegistrationDTO pdto = eForm.getUserRegDTO();
			String action = eForm.getUserRegDTO().getUserRegisterAction();
			logger.debug("Action values are.........." +action);
			logger.debug("Registration Parameters are" + " inserted successfully....");
			String actionForm4 = eForm.getUserRegDTO().getUserSPRegLicenseDetailAction();
			UserRegistrationBD bd = new UserRegistrationBD();
			//String currYear=bd.getCurrentYear();
			eForm.getUserRegDTO().setCurrentYear("");
			logger.debug("current year :-"+"removed");
			ArrayList listCountry = bd.getCountry(languageLocale);   
			eForm.setUserCountryList(listCountry);    
			eForm.setOccupationList(bd.getOccupationList(languageLocale));
			eForm.setCategoryList(bd.getCategoryList(languageLocale));
			eForm.getUserRegDTO().setMinorityRad("N");
				eForm.getUserRegDTO().setUserCountryID("1");	
				String chosenCountry = eForm.getUserRegDTO().getUserCountryID();
			//String chosenCountry = "INDIA";
					if(chosenCountry!=null && chosenCountry!="" ){
							
							//if(chosenCountry.equalsIgnoreCase("india")){
								listState = bd.getState(chosenCountry,languageLocale);
							//}
							//else if(!chosenCountry.equalsIgnoreCase("india"))
							//{
							//	String others =new String("Others");
							//	pdto.setUserState(others);
							//	pdto.setUserStateID("OT");
							//	eForm.getUserRegDTO().setUserState(others);
							//	listState.add(pdto);
							//}
							eForm.setUserStateList(listState);
						}
					else{
						eForm.setUserStateList(new ArrayList());
						eForm.setUserCityList(new ArrayList());
					}
			
			eForm.setUserStateList(listState);
		
					String chosenState = eForm.getUserRegDTO().getUserStateID();
						if(chosenState!=null && chosenState!="" ){
							//if(chosenState.equalsIgnoreCase("MP")){
								listDistrict = bd.getDistrictCity(chosenState,languageLocale);
							//} 
							//else if(!chosenState.equalsIgnoreCase("MP"))
							//{
							//	String others =new String("Others");
							//	pdto.setUserCity(others);
							//	pdto.setUserCityID("OTH");
							//	eForm.getUserRegDTO().setUserCity(others);
							//	listDistrict.add(pdto);
							//}
							eForm.setUserCityList(listDistrict);
						}
						else{
							eForm.setUserCityList(new ArrayList());
						}
						eForm.setUserCityList(listDistrict);
			String chosenCity = eForm.getUserRegDTO().getUserCityID();
			pdto.setUserCityID(chosenCity);
			ArrayList listID = bd.getIDProof(languageLocale);
			eForm.setUserPhotoId(listID);
			String choosenID = eForm.getUserRegDTO().getIdProofCode();
			pdto.setIdProofCode(choosenID);
					
			ArrayList listHintQuestion = bd.getHintQuestions(languageLocale);
			eForm.setUserHintQuestion(listHintQuestion);
			String choosenHQues = eForm.getUserRegDTO().getHintQuestID();
			pdto.setHintQuestID(choosenHQues);
				
			if (CommonConstant.USER_REGISTRATION_FORM.equalsIgnoreCase(eForm.getUserRegDTO().getUserRegForm())) 
			{
				logger.debug("Action: Form values are matched    "+ CommonConstant.USER_REGISTRATION_FORM);
				UserRegistrationDTO dto = eForm.getUserRegDTO();
				String action2 = eForm.getUserRegDTO().getUserAvailabilityCheckAction();
				if ("userRegistration".equals(action)) {
					UserRegistrationRule  rule = new UserRegistrationRule();
					ArrayList errorList = rule.validateUserName(eForm.getUserRegDTO());
					
					//Added by Mohit Soni for Catcha
					
					  Boolean isResponseCorrect =Boolean.FALSE;
			           String captchaId = (String) request.getSession().getAttribute("QARFAD");
			           String responses = request.getParameter("j_captcha_response");
			           try {
			               isResponseCorrect = IGRSCommon.validateCaptcha(captchaId,responses); 
			           } catch (CaptchaServiceException e) {
			                logger.debug("Failed to get Captcha",e);
			           }
					
					
					if (rule.isError()) {
						logger.debug("is error");
						forwardJsp = "ureg";
						eForm.setUserRegDTO(eForm.getUserRegDTO());
						request.setAttribute(CommonConstant.USER_REG_ERROR_FLAG, "true");
						request.setAttribute(CommonConstant.USER_REG_USER_VALIDATION, errorList);
						logger.debug("Action: Inside Error");
					}
					else if (!isResponseCorrect)
					{
						
						// To set hint answer blank.
						eForm.getUserRegDTO().setUserHintAnswer("");
						
						logger.debug("is error");
						forwardJsp = "ureg";
						eForm.setUserRegDTO(eForm.getUserRegDTO());
						if(languageLocale.equalsIgnoreCase("en"))
						 eForm.setErroMessage("Invalid Captcha is entered. Please Enter the Captcha Again");
			        	   else
			        	   eForm.setErroMessage("अवैध कैप्चा दर्ज किया गया है। फिर से कैप्चा दर्ज करें");   
			        	   logger.debug("Action: Inside Error");	
						
					}
					else
					{
						//dto.setUserId(dto.getUserId().toUpperCase());
						boolean userregcomplete = bd.insertUserRegDetails(dto);
						if (userregcomplete) {
							String userParamID = dto.getUserId();
							String userParamName = dto.getUserFirstName();
							//HttpSession requestSession = request.getSession
							//(true);
							requestSession.setAttribute("userParamID", userParamID);
							requestSession.setAttribute("userParamName",userParamName);
							forwardJsp = CommonConstant.USER_REGISTRATION_SUCCESS;
						}
					}
				}
				else if("checkAvailability".equals(action2))
				{
					logger.debug("Action values are matched for"	+ " checkAvailability");
					UserRegistrationRule  rule = new UserRegistrationRule();
					ArrayList errorList = rule.validateUserName(eForm.getUserRegDTO());
					logger.debug("Outside error....");
					if (rule.isError()) {
						logger.debug("is error");
						forwardJsp = "ureg";
						eForm.setUserRegDTO(eForm.getUserRegDTO());
						request.setAttribute(CommonConstant.USER_REG_ERROR_FLAG, "true");
						request.setAttribute(CommonConstant.USER_REG_USER_VALIDATION, errorList);
						logger.debug("Inside error....");
					}
					else
					{
						request.setAttribute(CommonConstant.USER_REG_ERROR_FLAG, "false");
						request.setAttribute(CommonConstant.USER_REG_USER_VALIDATION, errorList);
					}
					forwardJsp = "availSuccess";
				}
				
			}

			if (CommonConstant.USER_LICENSING_INPUT_FORM.equalsIgnoreCase
			(eForm.getUserRegDTO().getInputUserLIcenseForm())){	 
			//	String actionForm = eForm.getUserRegDTO().getUserLicensingAction();
				UserRegistrationDTO dto = eForm.getUserRegDTO();	
				String userID = dto.getUserId();

				if ("userRegistration".equals(action)) {
					UserRegistrationDTO userDetails = bd.getUserDetails(userID);
					eForm.setUserRegDTO(userDetails);

					UserRegistrationDAO dao = new UserRegistrationDAO();
					ArrayList list = dao.getUserDetails(userID);
					if(list!=null) {
						for(int i = 0;i<list.size();i++) {
							ArrayList lst = (ArrayList)list.get(i);
							if("M".equals(lst.get(4).toString())){
								request.setAttribute(CommonConstant.GENDER_STATUS, "male");
							}
							else if ("F".equals(lst.get(4).toString())){
									request.setAttribute(CommonConstant.GENDER_STATUS, "female");
							}
						}
					}
				}
				UserRegistrationDTO dto2 = eForm.getUserRegDTO();
				String actionForm2 = eForm.getUserRegDTO().getUserLicenseGenerateAction();
				if ("userSPLicenseRegistration".equals(actionForm2)) {
					FormFile file = dto2.getUploadFile();
					boolean isValidFile = true;
					if (file.getFileSize() <= 0 || !file.getFileName()
						.endsWith(".doc")) {
						isValidFile = false;
					}
					if (isValidFile) {
						try {
							String tempFolder = "E:\\igrs\\temp\\";           
							File tempFile = new File(tempFolder);
							tempFile.mkdirs();
							File tempFileName = new File(tempFolder + file.getFileName());
								if (tempFileName.exists()) {
									tempFileName.delete();
								}
							tempFileName.createNewFile();
							InputStream stream = file.getInputStream();
							OutputStream bos = new FileOutputStream(tempFileName);
							int bytesRead = 0;
							byte[] buffer = new byte[8192];
								while ((bytesRead = stream.read(buffer, 0, 8192))!= -1) {
									bos.write(buffer, 0, bytesRead);
								}
							bos.close();
							stream.close(); 
						} catch (Exception e) {
							e.printStackTrace();
					}
				}
					boolean spLicenseGenerated = bd
						.insertSPRegistrationDetails(dto2);
					if (spLicenseGenerated) {
						logger.debug("Registration Parameters are" + " inserted successfully....");
					}
				}
			}

			if (CommonConstant.USER_SP_VIEW_LICENSE.equalsIgnoreCase(eForm
				.getUserRegDTO().getUserLicenseViewForm()))
			{
				String actionForm3 = eForm.getUserRegDTO().getUserLicenseViewAction();
				if ("userSPLicenseView".equals(actionForm3)) {
					UserRegistrationDTO dto = eForm.getUserRegDTO();
					String fromDate = dto.getDurationFrom();
					String toDate = dto.getDurationTo();
					ArrayList licensingViewDetails = bd.getSPLicenseView
					(fromDate, toDate);
					eForm.setViewLicenseList(licensingViewDetails);
				}
				forwardJsp = CommonConstant.LICENSE_VIEW_SUCCESS;
			}
			
		/*Action to reset the User Registration form values to the deafult value as it was on load time*/
			else if(CommonConstant.UREG_RESET_ACTION.equalsIgnoreCase(eForm.getUserRegDTO().getUserRegisterAction()))
			{
				String defSelect = new String("[Select]");
				if(eForm!= null){
						eForm.getUserRegDTO().setUserFirstName("");
						eForm.getUserRegDTO().setUserMiddleName("");
						eForm.getUserRegDTO().setUserLastName("");
						eForm.getUserRegDTO().setUserGender("");
						eForm.getUserRegDTO().setUserFatherName("");
						eForm.getUserRegDTO().setUserMotherName("");
						eForm.getUserRegDTO().setUserSpouseName("");
						eForm.getUserRegDTO().setUserDay("");
						eForm.getUserRegDTO().setUserMonth("");
						eForm.getUserRegDTO().setUserYear("");
						eForm.getUserRegDTO().setUserOccupation("");
						eForm.getUserRegDTO().setUserAddress("");
						eForm.getUserRegDTO().setUserCountryID("1");
						eForm.getUserRegDTO().setUserStateID(defSelect);
						eForm.getUserRegDTO().setUserState(defSelect);
						eForm.getUserRegDTO().setUserCity(defSelect);
						eForm.getUserRegDTO().setUserCityID(defSelect);
						eForm.getUserRegDTO().setUserPhoneNumber("");
						eForm.getUserRegDTO().setUserMobileNumber("");
						eForm.getUserRegDTO().setUserPostalCode("");
						eForm.getUserRegDTO().setUserPrimaryEmail("");
						eForm.getUserRegDTO().setUserSecondaryEmail("");
						eForm.getUserRegDTO().setIdProofCode(defSelect);
						eForm.getUserRegDTO().setIdProofNumber("");
						eForm.getUserRegDTO().setBankAddress("");
						eForm.getUserRegDTO().setBankName("");
						eForm.getUserRegDTO().setRegistrationNumber("");
						eForm.getUserRegDTO().setUserId("");
						eForm.getUserRegDTO().setUserPass("");
						eForm.getUserRegDTO().setUserConfirmPass("");
						eForm.getUserRegDTO().setHintQuestID(defSelect);
						eForm.getUserRegDTO().setUserHintAnswer("");
						eForm.getUserRegDTO().setTermsCond("");
						//eForm.getUserRegDTO().setUserCountryID("INDIA");	
						eForm.getUserRegDTO().setAge("");
						eForm.getUserRegDTO().setIndCategory(defSelect);
						eForm.getUserRegDTO().setMinorityRad("N");
						eForm.getUserRegDTO().setOccupationId(defSelect);
						eForm.setUserCityList(new ArrayList());
						
				}
				logger.debug("Page is reseted to default values successfully");
				forwardJsp = CommonConstant.UREG_RESET_SUCCESS;
			}
			
			
			/* Cancellation Action is to cancel the process and display the Main Page */
			else if(CommonConstant.CANCELLATION_ACTION.equalsIgnoreCase(eForm.getUserRegDTO().getUserRegisterAction()))		 
			{
				forwardJsp = CommonConstant.CANCEL_SUCCESS;
			}
			
			else if ("userSPLinIDRetrieval".equals(actionForm4)) {
				UserRegistrationDTO dto1 = eForm.getUserRegDTO();
				String userReg = (String) request.getParameter("refId");
				//HttpSession requestSession = request.getSession(true);
				requestSession.setAttribute("refId", userReg);
				if (userReg != null) {
					dto1 = bd.getUserSPLicenseDetails(userReg);
					eForm.setUserRegDTO(dto1);
					forwardJsp = CommonConstant.ID_DETAILS_RETRIEVAL_SUCCESS;
				}
			}
		}  
		return mapping.findForward(forwardJsp);
	}
	
}
