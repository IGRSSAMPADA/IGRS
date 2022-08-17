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
import java.io.InputStream;
import java.util.ArrayList;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import com.wipro.igrs.UserRegistration.bd.UserRegistrationBD;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import java.io.FileOutputStream;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import com.wipro.igrs.UserRegistration.dao.UserRegistrationDAO;
import com.wipro.igrs.UserRegistration.dto.UserRegistrationDTO;
import org.apache.struts.upload.FormFile;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import com.wipro.igrs.UserRegistration.form.UserRegistrationForm;
import com.wipro.igrs.UserRegistration.rule.UserRegistrationRule;


/**
 * @author nihraa
 *
 */
public class PasswordUpdateAction extends Action {

	/**
	 * forwardJsp
	 */
	String forwardJsp = new String("updatePass");

	/** 
	 * Method execute for user registration.
	 * @param mapping 
	 * @param form 
	 * @param request 
	 * @param response 
	 * @return ActionForward
	 * @throws Exception 
	 */
	private Logger logger = 
		(Logger) Logger.getLogger(PasswordUpdateAction.class);
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response) 
		throws Exception {
		
		
		if (form != null) {
			UserRegistrationForm eForm = (UserRegistrationForm) form;
			ArrayList listDistrict = new ArrayList();
			ArrayList listState = new ArrayList();
			UserRegistrationDTO pdto = eForm.getUserRegDTO();
			logger.debug("Action :   Inside Action Class");
			String action = eForm.getUserRegDTO().getUserRegisterAction();
			logger.debug("Action values are.........." +action);
			
			UserRegistrationBD bd = new UserRegistrationBD();	
						
			if (CommonConstant.USER_REGISTRATION_FORM.equalsIgnoreCase(eForm.getUserRegDTO().getUserRegForm())) 
			{
				logger.debug("Action: Form values are matched    "+ CommonConstant.USER_REGISTRATION_FORM);
				UserRegistrationDTO dto = eForm.getUserRegDTO();
				String actionForm2 = eForm.getUserRegDTO().getUserAvailabilityCheckAction();
				logger.debug("actionForm2:-    "+actionForm2);
					
			/* Action written to reset the Update Pasword Form to the default values same as at the time of the loading of page*/
			 if(CommonConstant.UPDATE_PSWD_RESET_ACTION.equalsIgnoreCase(eForm.getUserRegDTO().getUserRegisterAction()))
			{
				if(eForm!= null){
						eForm.getUserRegDTO().setUserId(null);
						eForm.getUserRegDTO().setUserPass(null);
						eForm.getUserRegDTO().setUserConfirmPass(null);
						eForm.getUserRegDTO().setNewPswd(null);
				}
				logger.debug("Page is reseted to default values successfully");
				
				forwardJsp = CommonConstant.UPDATE_PSWD_RESET_SUCCESS;
			}
			
			/* Cancellation Action is to cancel the process and display the Main Page */
			else if(CommonConstant.CANCELLATION_ACTION.equalsIgnoreCase(eForm.getUserRegDTO().getUserRegisterAction()))		 
			{
				logger.debug("Page is redirected back to Main page successfully");
				forwardJsp = CommonConstant.CANCEL_SUCCESS;
			}
			
			
			//Action call if the user wants to update the already existing Password corresponding to its User Name
			else if(CommonConstant.UPDATE_PSWD_ACTION.equalsIgnoreCase(eForm.getUserRegDTO().getUserRegisterAction())) 
			{	
				if(eForm!= null){
					logger.debug("Action values are matched    " + action);
				String userName = eForm.getUserRegDTO().getUserId();
				String oldpswd = eForm.getUserRegDTO().getUserPass();
				String newPswd = eForm.getUserRegDTO().getNewPswd();
				String pswdConfirm = eForm.getUserRegDTO().getUserConfirmPass();
			
				/*Call to the method to retrieve password from the database 
				 * corresponding to the entered UserId so as to check whether the old password 
				 * entered is same as that stored in the database for the UserID.If yes, user password will
				 * be updated else Msg comes saying login Credentials incorrect	 */
				
					String retrievedPswd = bd.matchPswd(userName);
				
				
				/*Update password will only happen if verification is successful otherwise error message is prompted.
				 * The confirmation is done to check if the newly entered password is confirmed by user and also the old pswd 
				 * same as that in database for that userid*/
				
					if(retrievedPswd.equals(oldpswd)&& newPswd.equals(pswdConfirm)){
					
					boolean updatedPinsList = bd.updatePswd(newPswd, userName,oldpswd);
					if(updatedPinsList){
						logger.debug("Values are sucessfully updated into database.  ");
					}
					forwardJsp = CommonConstant.UPDATE_PSWD_SUCCESS;
					pdto.setForwardPath(forwardJsp);
				}
				else
				{
					logger.debug("Passwords are not confirmed.");
					forwardJsp = CommonConstant.UPDATE_PSWD_FAILURE;
					pdto.setForwardPath(forwardJsp);
				}
			}	
		}
			}	
			
		}  
		return mapping.findForward(forwardJsp);
	}
}
