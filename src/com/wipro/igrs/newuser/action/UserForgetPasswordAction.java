/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserForgetPasswordAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for UserForgetPassword.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Madan Mohan   27th June, 2008	 		 
 * --------------------------------------------------------------------------------
 * 2.0             Roopam Mehta  10th October, 2012	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.newuser.action;


import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.octo.captcha.service.CaptchaServiceException;
import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.login.action.LoginAction;
import com.wipro.igrs.login.constant.LoginConstant;
import com.wipro.igrs.newuser.util.SendApp;
import com.wipro.igrs.newuser.bd.UserForgetPasswordBD;
import com.wipro.igrs.newuser.dto.UserForgetPasswordDTO;
import com.wipro.igrs.newuser.form.UserForgetPasswordForm;
//import com.wipro.igrs.Log4J.LoggerMsg;
import com.wipro.igrs.newuser.util.PropertiesFileReader;
//added by roopam
import com.wipro.igrs.UserRegistration.constant.*;

public class UserForgetPasswordAction extends Action {
	
	private Logger logger = 
		(Logger) Logger.getLogger(LoginAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request,HttpServletResponse response) throws Exception 
    {
		//LoggerMsg.info("we are in UserForgetPasswordAction");
		HttpSession session = request.getSession();
		String target = "success";
		String user_status=(String)session.getAttribute(LoginConstant.USER_ERROR_LIST);
		System.out.println("ttttttt--->"+user_status);
		System.out.println("rrrrrrr--->"+request.getAttribute(LoginConstant.VALUE_INSERTED_FLAG));
		
		
		
		//request.setAttribute(LoginConstant.USER_ERROR_LIST, status[0]);
		/* Populate the userForgetPasswordForm form */
		if(form !=null ) {
			
			
			HttpSession requestSession = request.getSession(true);
			requestSession.setAttribute("langModule", "forgotPasswrd");
			
			String languageLocale="hi";
			
			if(requestSession.getAttribute("languageLocale")!=null){
				languageLocale=(String)requestSession.getAttribute("languageLocale");
			}
			
			UserForgetPasswordForm userForgetPasswordForm = (UserForgetPasswordForm)form;
			UserForgetPasswordBD userforgetbd = new UserForgetPasswordBD();
			UserForgetPasswordDTO dto = new UserForgetPasswordDTO();
			ArrayList  hquestionList = userforgetbd.GetHquestionList(languageLocale);
			
			if(session.getAttribute("UserId")!=null){
				
				userForgetPasswordForm.setUserSessionName((String)session.getAttribute("UserId"));
				
				
			}else{
				userForgetPasswordForm.setUserSessionName("");
			}
			       
			String label = (String) request.getParameter("label");
			if("1".equalsIgnoreCase(label)) {
				dto.setAnswer("");
				dto.setHquestionId("");
				dto.setUserName("");
				userForgetPasswordForm.setUserName("");
				userForgetPasswordForm.setHquestionId("");
				userForgetPasswordForm.setAnswer("");
					userForgetPasswordForm.setErroMessage("");;
			}
			/*//below if condition added by roopam for reset password check
			if("2".equals(label)) {
				target="resetpassword";
				
			}
			//end
*/			
			
			userForgetPasswordForm.setListForgotPassword(hquestionList);
			
			String formName = userForgetPasswordForm.getFormName();
			String actionName = userForgetPasswordForm.getActionName();
			String pwd = "";
			String userStatus = "";
			String msg = "";
			String usermsg="";
			System.out.println("tttrrrr"+LoginConstant.VALUE_INSERTED_FLAG);
			
			if("userForgetPassword".equals(formName)) {
				if("PasswordAction".equals(actionName)) {
					//userForgetPasswordForm.setUserName(userForgetPasswordForm.getUserName().toUpperCase());
					String strUserName=userForgetPasswordForm.getUserName();
					String strhQustion=userForgetPasswordForm.getHquestionId();
					String  srtAnswer=userForgetPasswordForm.getAnswer();
					String[] param = new String[4];
					if(strUserName!="" && strhQustion!="" && srtAnswer!="")
					{
						
						
						param = userforgetbd.getUserName(strUserName,strhQustion,srtAnswer,userForgetPasswordForm.getSalt1(),userForgetPasswordForm.getSalt2());
						
						  Boolean isResponseCorrect =Boolean.FALSE;
				           String captchaId = (String) request.getSession().getAttribute("QARFAD");
				           String responses = request.getParameter("j_captcha_response");
				           try {
				               isResponseCorrect = IGRSCommon.validateCaptcha(captchaId,responses); 
				           } catch (CaptchaServiceException e) {
				                logger.debug("Failed to get Captcha",e);
				           }
						
						if(!isResponseCorrect)
						{
							param[3] = "INVALID_CAPTHCA_MSG";
							
						}
						
					}	
					if(param != null ) {
						
						dto.setEmailId(param[0]);
					    msg = param[3];
					    PropertiesFileReader pr = 
				    		PropertiesFileReader.
				    		 getInstance("resources.igrs");
					    //String smtpHost = pr.getValue("SMPT_HOST");
					    //String smtpPort = pr.getValue("SMPT_PORT");
					    //String from = pr.getValue("WEBMASTER_EMAIL");
					    
					    
					    ArrayList list = new ArrayList();
					    if("L".equals(userStatus)) {
					    	request.removeAttribute("status");
					    	list.add(pr.getValue("error.header"));
					    	list.add(pr.getValue("DB_LOCK_PWD"));
					    	request.setAttribute("status", list );
					    	
					    }
					    /*below if condition modified by Roopam for implementing forgot & 
					    reset password functionality.*/
					    if("DB_NO_DATA_FOUND".equals(msg) || " TO MANY VALUES FOR THE SAME DATA".equals(msg)) {
					    	request.removeAttribute("status");
					    	//list.add(pr.getValue("error.header"));
					    	//list.add(pr.getValue("INVALID_PARAMETER_FOUND"));
					    	//request.setAttribute("status", list );
					    	//usermsg="Incorrect Security question or answer";
					    	request.setAttribute(LoginConstant.USER_ERROR, "true" );
					    	
					    	//modify for hindi...
					    	request.setAttribute(LoginConstant.USER_ALERT, pr.getValue("error.1"));
					    	userForgetPasswordForm.setFormName("");         
					    	userForgetPasswordForm.setActionName("");
					    	userForgetPasswordForm.setHquestionId("");
					    	userForgetPasswordForm.setAnswer("");
					    	userForgetPasswordForm.setErroMessage("");
					    	//userForgetPasswordForm=null;
					    	target="result";
					    	
					    }
					    else if("INVALID_CAPTHCA_MSG".equals(msg))
					    {
					    	request.removeAttribute("status");
					    	//list.add(pr.getValue("error.header"));
					    	//list.add(pr.getValue("INVALID_PARAMETER_FOUND"));
					    	//request.setAttribute("status", list );
					    	//usermsg="Incorrect Security question or answer";
					    	request.setAttribute(LoginConstant.USER_ERROR, "true" );
					    	
					    	//modify for hindi...
					    	request.setAttribute(LoginConstant.USER_ALERT, pr.getValue("error.8"));
					    	userForgetPasswordForm.setFormName("");         
					    	userForgetPasswordForm.setActionName("");
					    	userForgetPasswordForm.setHquestionId("");
					    	userForgetPasswordForm.setAnswer("");
					    	//userForgetPasswordForm=null;
					    	target="result";
					    	
					    	
					    	
					    }
					    
					    else{
					    	String subject=CommonConstant.SUB_RESET_PSWRD;
					    	String content=CommonConstant.CONTENT_RESET_PSWRD;
					    		    	
					    	UUID key = UUID.randomUUID(); 
					    	String uniqueUserKey=key.toString();
					    	// Above code is for Generating unique key for identifying reset password request. 
					    	logger.debug("key----->"+uniqueUserKey);
					        
					        String emailContent = MessageFormat.format(content, uniqueUserKey); 
					        // Above code is creating dynamic emailContent string

					      //  CryptoLibrary  crpt = new CryptoLibrary();
						  //  pwd = crpt.decrypt(param[1]);
						  //  pwd = param[1];
						  //  userStatus = param[2];
						  //  String content = "User Name :-" +strUserName +"<br> Password:-"+pwd;
					   // 	SendApp.send(smtpHost, Integer.parseInt(smtpPort)
					   // 			, from, dto.getEmailId(), "Forget Password", content);
					        String blockFlag="";
					        if(session.getAttribute("DB_USER_BLOCKED")==null){
					        	session.removeAttribute("DB_USER_BLOCKED");
					        	blockFlag="N";
					        	logger.debug("xxxxxxxxxxxxxxxxx INSIDE IF-->>"+blockFlag);
					        }else if(session.getAttribute("DB_USER_BLOCKED").toString().equalsIgnoreCase(LoginConstant.DB_USER_BLOCKED)){
					        blockFlag="Y";
					        logger.debug("xxxxxxxxxxxxxxxxx INSIDE ELSE-->>"+blockFlag);
					        }
					        
					
					        String status =	userforgetbd.insertEmailData(strUserName, subject, emailContent, uniqueUserKey, blockFlag);                     //userDO.getPassword() replaced by encryptpswd by roopam
			                logger.debug("status:-"+status);
			                if(status.equalsIgnoreCase("y")){
			                  	//usermsg="You will shortly receive an E-Mail for resetting your password.";
			                	request.setAttribute(LoginConstant.USER_INFORMATION, "true" );
			                	request.setAttribute(LoginConstant.USER_ALERT, pr.getValue("info.2"));
			                	target="result";
			                }
			                else if(status.equalsIgnoreCase("n")){
			                	//usermsg="Error inserting in Email Table.";
			                	request.setAttribute(LoginConstant.USER_INFORMATION, "true" );
			                	request.setAttribute(LoginConstant.USER_ALERT, pr.getValue("error.3"));
			                	target="result";
			                }else if(status.equalsIgnoreCase("e")){
			                	//usermsg="Error inserting in Reset Password Table.";
			                	request.setAttribute(LoginConstant.USER_INFORMATION, "true" );
			                	request.setAttribute(LoginConstant.USER_ALERT, pr.getValue("error.4"));
			                	target="result";
			                }
					    }
					    
					    
					    
					}
				}
			}
			userForgetPasswordForm.setDto(dto);
			request.setAttribute("UserForgetPasswordForm", userForgetPasswordForm);
		}			
		return mapping.findForward(target);	
	}
}
