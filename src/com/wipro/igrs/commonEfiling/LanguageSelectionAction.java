package com.wipro.igrs.commonEfiling;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ActionController;
import org.apache.taglibs.standard.lang.jstl.test.PageContextImpl;
import com.wipro.igrs.ACL.bd.MenuBD;
import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.UserRegistration.form.UserRegistrationForm;
//import com.wipro.igrs.baseaction.action.BaseAction;
//import com.wipro.igrs.baseaction.bd.LoginBD;
//import com.wipro.igrs.baseaction.constant.WebConstants;
//import com.wipro.igrs.baseaction.dto.UserDTO;
import com.wipro.igrs.login.constant.LoginConstant;
import com.wipro.igrs.login.dto.MasterListDTO;
import com.wipro.igrs.login.form.LoginForm;
import com.wipro.igrs.login.rule.LoginRule;
import com.wipro.igrs.util.PropertiesFileReader;


/**
 * @since 12 Dec 2007	
 * @author Madan Mohan
 *
 */
/**
 * Modified By:	  Roopam Mehta
 * Modified Date: 10th October, 2012
 *
 */
@SuppressWarnings("all")

public class LanguageSelectionAction extends Action {

		Locale currentLocale;
		String country;
		ResourceBundle messages;
		private int noAttempt = 0;
		/**
		 * @author Madan Mohan
		 */
		private Logger logger = (Logger) Logger.getLogger(LanguageSelectionAction.class);
		public LanguageSelectionAction() {	}
		/**
		 * @author Madan Mohan
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @param session
		 * @exception Exception
		 * @return ActionForward
		 */
		public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
                                 HttpServletResponse response) throws Exception {
        
			HttpSession session = request.getSession(true);
		
		String forwardTo = (String)session.getAttribute("langModule");
		
		if(forwardTo==null){
			forwardTo="login";
		}
		
		PropertiesFileReader prObj = PropertiesFileReader.getInstance("resources.igrs");
		UserRegistrationForm loginForm;
        if (form == null)
    	{
    	 logger.error("LoginForm is null ");
    	 loginForm = new UserRegistrationForm();
    	}
        else 
    	{
        loginForm = (UserRegistrationForm) form;
    	}
       
        String lang=request.getParameter("lang");
        
        
      //  String actionName = loginForm.getActionName();
		
        
        try {
        	        	
        	
           
         //   if(lang==null)
		//	{
		//	lang = new String("hi");
		//	country = new String("IN");
		//	currentLocale = new Locale(lang,country);
		//	session.setAttribute("org.apache.struts.action.LOCALE", currentLocale);
		//	}
		if(lang.equalsIgnoreCase("hi"))
			{
			lang = new String("hi");
			country = new String("IN");
			currentLocale = new Locale(lang,country);
			session.setAttribute("languageLocale", lang);
			session.setAttribute("org.apache.struts.action.LOCALE", currentLocale);
			messages =ResourceBundle.getBundle("resources.MessagesBundle",currentLocale);
			}
		else
			{
			lang = new String("en");
			country = new String("US");
			currentLocale = new Locale(lang,country);
			session.setAttribute("languageLocale", lang);
			session.setAttribute("org.apache.struts.action.LOCALE", currentLocale);
			messages =ResourceBundle.getBundle("resources.MessagesBundle",currentLocale);
			}
                       
         
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
            forwardTo = "userReg";
        }
        return mapping.findForward(forwardTo);
    }
	

}
