package com.wipro.igrs.UserRegistration.action;



import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.UserRegistration.bd.UserResetPasswordBD;
import com.wipro.igrs.UserRegistration.form.UserResetPasswordForm;
import com.wipro.igrs.login.action.LoginAction;
import com.wipro.igrs.login.constant.LoginConstant;
import com.wipro.igrs.newuser.bd.UserForgetPasswordBD;
import com.wipro.igrs.newuser.dto.UserForgetPasswordDTO;
//import com.wipro.igrs.newuser.form.UserForgetPasswordForm;
import com.wipro.igrs.newuser.util.PropertiesFileReader;



public class UserResetPasswordAction extends Action {
	private Logger logger = 
		(Logger) Logger.getLogger(LoginAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request,HttpServletResponse response) throws Exception 
    {
		
		HttpSession session = request.getSession();
		String target = "";
		String usermsg="";
		UserResetPasswordBD userresetbd = new UserResetPasswordBD();
		
		//below if condition added by roopam for reset password check
		String label = (String) request.getParameter("label");
		//this label will be the random number key for the userID.
		//after fetching the label, respective UserId will be set fetched from the database and displayed on jsp.
		if("2".equals(label)) {
			//target="resetpassword";
			
		}
		//end
		
		//below if condition added by roopam for reset password check
		//String key = (String) request.getParameter("key");
		//logger.debug("fffffffff"+key);
		PropertiesFileReader pr = 
    		PropertiesFileReader.
    		 getInstance("resources.igrs");
		//this label will be the random number key for the userID.
		/*//after fetching the label, respective UserId will be set fetched from the database and displayed on jsp.
		if(key!="") {
			String userId=userresetbd.getUserId(key); //fetching userId from database corresponding to the given key.
			logger.debug("fffffffff"+key);
			
			if(userId.equalsIgnoreCase("")){
				
				//usermsg="Incorrect Key";
				request.setAttribute(LoginConstant.USER_INFORMATION, "true" );
            	request.setAttribute(LoginConstant.USER_ALERT, pr.getValue("error.7"));
				
			}
			else{
				session.setAttribute("UserId", userId);
			}
			
			target="resetpassword";
		}
		//end
*/		
		
		if(form !=null ) {
			
			//HttpSession requestSession = request.getSession(true);
			session.setAttribute("langModule", "resetPasswrd");
			
			String languageLocale="hi";
			
			if(session.getAttribute("languageLocale")!=null){
				languageLocale=(String)session.getAttribute("languageLocale");
			}
			
			UserResetPasswordForm userResetPasswordForm = (UserResetPasswordForm)form;
			//UserResetPasswordBD userresetbd = new UserResetPasswordBD();
			String formName = userResetPasswordForm.getFormName();
			String actionName = userResetPasswordForm.getActionName();
			
			String key="";
			
			if(request.getParameter("key")!=null && !request.getParameter("key").equalsIgnoreCase("")){
			key = (String) request.getParameter("key");
			
			
			Locale locale = request.getLocale();
			   String language = locale.getLanguage();
			  
			   String country = locale.getCountry();
			    language = new String("hi");
				country = new String("IN");
			 	locale = new Locale(language,country);
			 	
			 	//session.setAttribute("languageLocale", language);
				session.setAttribute("org.apache.struts.action.LOCALE", locale);
              	session.setAttribute("languageLocale",CommonConstant.LANGUAGE_ENGLISH);
              	
              	
              	userResetPasswordForm.setNewPaswrd("");
				userResetPasswordForm.setConfmPaswrd("");
				userResetPasswordForm.setPhotoIdProof("");
				userResetPasswordForm.setTopMenuFlag(1);
				userResetPasswordForm.setKey(key);
              	
			}else if( userResetPasswordForm.getKey()!=null   && !userResetPasswordForm.getKey().equalsIgnoreCase("")){
				key = userResetPasswordForm.getKey();
				
				
				//userResetPasswordForm.setNewPaswrd("");
				//userResetPasswordForm.setConfmPaswrd("");
				//userResetPasswordForm.setPhotoIdProof("");
				
			}
			
			
			
			//after fetching the label, respective UserId will be set fetched from the database and displayed on jsp.
			if(key!="") {
				String userId=userresetbd.getUserId(key); //fetching userId from database corresponding to the given key.
				logger.debug("fffffffff"+key);
				
				if(userId.equalsIgnoreCase("")){
					
					//usermsg="Incorrect Key";
					request.setAttribute(LoginConstant.USER_INFORMATION, "true" );
	            	request.setAttribute(LoginConstant.USER_ALERT, pr.getValue("error.7"));
	            	userResetPasswordForm.setKey("");
					
				}
				else{
					//session.setAttribute("UserId", userId);
					userResetPasswordForm.setUserName(userId);
					//userResetPasswordForm.setKey(key);
				}
				
				//target="resetpassword";
			}else{
				userResetPasswordForm.setKey("");
			}
			//end
			
			
			
			if("userResetPassword".equals(formName)) {
				if("ResetPasswordAction".equals(actionName)) {
					String strUserName=userResetPasswordForm.getUserName();
					String strNewPswd=userResetPasswordForm.getNewPaswrd();
					String srtConfmPswd=userResetPasswordForm.getConfmPaswrd();
					String srtPhotoIdProof=userResetPasswordForm.getPhotoIdProof();
					boolean updatePswdStatus = false;
					if(strUserName!="" && strNewPswd!="" && srtConfmPswd!="" && srtPhotoIdProof!="")
					{
						updatePswdStatus = userresetbd.updateUserPassword(strUserName,strNewPswd, srtPhotoIdProof);
						
					}	
					if(updatePswdStatus) {
						logger.debug("password updated.........");
						logger.debug("inside if condition.........");
						//usermsg="Your password is successfully reset.";
						request.setAttribute(LoginConstant.USER_INFORMATION, "true" );
	                	request.setAttribute(LoginConstant.USER_ALERT, pr.getValue("info.5"));
	                	userResetPasswordForm.setTopMenuFlag(0); // zero for non hindi option top menu
	                	userResetPasswordForm.setNewPaswrd("");
	    				userResetPasswordForm.setConfmPaswrd("");
	    				userResetPasswordForm.setPhotoIdProof("");
	    				userResetPasswordForm.setKey("");
	                	//target="success";
					}
					else{
						//usermsg="Could not reset password due to incorrect PhotoProof ID No.";
						userResetPasswordForm.setNewPaswrd("");
						userResetPasswordForm.setConfmPaswrd("");
						userResetPasswordForm.setPhotoIdProof("");
						request.setAttribute(LoginConstant.USER_INFORMATION, "false" );
	                	request.setAttribute(LoginConstant.USER_ALERT, pr.getValue("error.6"));
	                	//target="success";
					}
					    
					    
					    
					    
					userResetPasswordForm.setActionName("");
					    
					    
					    
				
				}
			}
			
			request.setAttribute("UserResetPasswordForm", userResetPasswordForm);
		}			
		target="resetpassword";
		return mapping.findForward(target);	
	}

}
