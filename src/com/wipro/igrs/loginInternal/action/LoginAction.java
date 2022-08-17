package com.wipro.igrs.loginInternal.action;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.octo.captcha.service.CaptchaServiceException;
import com.wipro.igrs.ACL.bd.MenuBD;
import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.baseaction.bd.LoginBD;
import com.wipro.igrs.baseaction.constant.WebConstants;
import com.wipro.igrs.baseaction.dto.UserDTO;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.dao.CommonDAO;
import com.wipro.igrs.device.servlet.CaptchaServiceSingleton;
import com.wipro.igrs.login.constant.LoginConstant;
import com.wipro.igrs.login.dto.MasterListDTO;
import com.wipro.igrs.login.form.LoginForm;
import com.wipro.igrs.login.loadSingleton.LoadMenuExternal;
import com.wipro.igrs.login.rule.LoginRule;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
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

public class LoginAction extends BaseAction {
	
		Locale currentLocale;
		String country;
		ResourceBundle messages;
		private int noAttempt = 0;
		/**
		 * @author Madan Mohan
		 */
		private Logger logger = (Logger) Logger.getLogger(LoginAction.class);
		public LoginAction() {	}
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
                                 HttpServletResponse response,HttpSession session) throws Exception {
        
		
		logger.info("Login Starts ");
		String forwardTo = "login";
		long start = 0, end = 0;
		PropertiesFileReader prObj = PropertiesFileReader.getInstance("resources.igrs");
        LoginForm loginForm;
        if (form == null)
    	{
    	 logger.error("LoginForm is null ");
    	 loginForm = new LoginForm();
    	}
        else 
    	{
        loginForm = (LoginForm) form;
    	}
        UserDTO userDO = null;
        String userStatus=null;
        MasterListDTO masterList = loginForm.getMasterList();
        
        Date currSysDate = new Date();
        Calendar sysCal = Calendar.getInstance();
        String lang=request.getParameter("language");
        
        session.setAttribute("DB_USER_BLOCKED", "");
        session.setAttribute("currSysYear", sysCal.get(Calendar.YEAR));
		session.setAttribute("currSysDate", currSysDate );
		session.setAttribute("loggedToOffice", loginForm.getMasterList().getOfficeID());
		
		userDO = new UserDTO();
        CryptoLibrary crpt = new CryptoLibrary();
        MenuBD menubd = new MenuBD();
        
        LoginBD loginBO = new LoginBD();
        loginForm.setListRole(loginBO.getRoleName());
        String actionName = loginForm.getActionName();
		if(actionName!=null){
			
		}else{
			actionName="";
		}
        
        try {
        	//loginForm.setUserId(loginForm.getUserId().toUpperCase())	;
        	userDO.setUserId(loginForm.getUserId());
            userDO.setPassword(loginForm.getPassword());
           
            if(lang==null)
			{
			lang = new String("hi");
			country = new String("IN");
			currentLocale = new Locale(lang,country);
			session.setAttribute("languageLocale", lang);
			session.setAttribute("org.apache.struts.action.LOCALE", currentLocale);
			}
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
		session.setAttribute("userDetls", loginBO.getLoggedInUserDetlsInternal(loginForm.getUserId(),loginForm.getMasterList().getOfficeID(),lang));          
                
         /*if(request.getParameter("parameter")!=null)
            {
         if((request.getParameter("parameter")).equalsIgnoreCase("usrofficeselect"))    //change
            {
            ArrayList internalUserOfcList = loginBO.getInternalUsersOffice(loginForm.getUserId());
            loginForm.setOfficeList(internalUserOfcList);
            }
            }*/
         //above commented and below added by roopam for resolving bug no. 925 date: 16 nov. 2013
         if(actionName.equalsIgnoreCase("GET_OFFICE_ACTION")){
        	 loginForm.setActionName("");
        	 logger.debug("Into the get office action -- "+actionName);
             ArrayList internalUserOfcList = loginBO.getInternalUsersOffice(loginForm.getUserId());
             logger.debug("fetching internal user ofc list, size of list is -- "+internalUserOfcList.size());
             loginForm.setOfficeList(internalUserOfcList);
              
         }
         //userDO.setUserId(userDO.getUserId().toUpperCase());
          loginForm.setUserId(userDO.getUserId());
          //logger.debug("actionName"+loginForm.getActionName());    
            
         if(session.getAttribute("UserId")!=null)
         	{
         if("".equals(userDO.getUserId()) || userDO.getUserId()==null )
         	{
         session.removeAttribute("UserId");
    		}
         if("".equals(userDO.getPassword()) ||	userDO.getPassword()==null )
         	{
    	 session.removeAttribute("UserId");
    		}
            }     
         
         
         /*String fromModule="";
         if(request.getParameter("fromModule")!=null && request.getParameter("fromModule").toString().equalsIgnoreCase("UserReg")){
        	 fromModule="UserReg";
         }*/
         String userTypeId="";
         //if(fromModule.equalsIgnoreCase(""))
         //{
         ArrayList errorList=new ArrayList();
         RegCommonBO commonBo=new RegCommonBO();
         userTypeId=commonBo.getUserTypeId(loginForm.getUserId());
         //if(userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)){
        	 
          if("loginAction".equals(actionName) && userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS))
          {
           logger.debug("entered in login action for internal user"); 	
          userDO.setUserId(loginForm.getUserId());
          userDO.setPassword(loginForm.getPassword());
                
          // String noAttem =(String)session.getAttribute("noAttempt");

          logger.debug("fetching number of failed attemprt"); 
          String noAttem = loginBO.getNoAttempt(loginForm.getUserId());
          int n =0; 
        	  
        	  if(noAttem!=null && !noAttem.equalsIgnoreCase("")){
        		  
        		n = Integer.parseInt(noAttem);
        	  }else{
        		  noAttem="0";  
        	  }
        	  
          /*if (n>=3)
          {
        	  boolean lockAccount = loginBO.lockAccount(loginForm.getUserId());
        	  System.out.println("lock updated : "+lockAccount);
          }*/
          if(noAttem == null )
          {
           noAttem = String.valueOf(0);
           }

           start = System.currentTimeMillis();
           // the password entered by user at the time of login has to be encrypted and validated from back end
           // this is not done now
           CryptoLibrary crypt = new CryptoLibrary();                      //uncommented by roopam
           //String encryptpswd = crypt.encrypt(userDO.getPassword());       //commented by roopam
           String encryptpswd =userDO.getPassword();//added by Mohit
           String roleID = "";
                
            String salt1 =   loginForm.getSalt1();
            String salt2 = loginForm.getSalt2();
            
         //   String newpass = salt1+encryptpswd+salt2;
          //  System.out.println(newpass);
          
            
            
           /*String[] status =loginBO.authenticateUser(userDO.getUserId(),
                 					encryptpswd, noAttem,roleID,masterList.getOfficeID()); */
           Boolean isResponseCorrect =Boolean.FALSE;
           String captchaId = (String) request.getSession().getAttribute("QARFAD");
           String responses = request.getParameter("j_captcha_response");
           try {
               isResponseCorrect = IGRSCommon.validateCaptcha(captchaId,responses); 
               logger.debug("captcha validation -- "+isResponseCorrect);
           } catch (CaptchaServiceException e) {
                logger.debug("Failed to get Captcha",e);
           }
           String[] status = null;
           
           if(isResponseCorrect)
           {
        	   logger.debug("inside validating password");
               status = 	loginBO.authenticateUserSalting(userDO.getUserId(), encryptpswd, noAttem, roleID, salt1, salt2);  
           }
           else
           {
        	   status = new String[1];
        	   status[0] = LoginConstant.DB_INVALID_CAPTCHA;
           }
                //userDO.getPassword() replaced by encryptpswd by roopam
                logger.debug("status:-"+status[0]+":"+noAttem);

                String loginStatus = status[0];//added by Ankita
                
                LoginRule rule = new LoginRule();
                errorList = rule.validateUser(status[0], userDO);
            	
	            request.removeAttribute(LoginConstant.USER_ERROR);
	            request.removeAttribute(LoginConstant.USER_ERROR_LIST);
                
	            if(LoginConstant.DB_INVALID_CAPTCHA.equalsIgnoreCase(loginStatus))
	            {
	            	
	            	 ArrayList internalUserOfcList = loginBO.getInternalUsersOffice(loginForm.getUserId());
                     loginForm.setOfficeList(internalUserOfcList);
	            	
	            	
	            }
	            
                if(LoginConstant.DB_INVALID_PWD.equalsIgnoreCase(loginStatus)) {
                	//noAttempt = noAttempt+1;
                	logger.debug("attempt number is"+noAttempt);
                	String getCurrTimeStamp = loginBO.getCurrTimeStamp();
                	
                	int noAttempt = loginBO.addNoAttempt(loginForm.getUserId(),getCurrTimeStamp);
                	if (noAttempt>=3)
                    {
                  	 boolean lockAccount = loginBO.lockAccount(loginForm.getUserId());
                  	logger.debug("lock updated : "+lockAccount);
                  	if(lockAccount==true){
                  	status[0]=LoginConstant.DB_USER_LOCKED;
                  	}
                    }
                	//session.setAttribute("noAttempt",String.valueOf(noAttempt));
                	 ArrayList internalUserOfcList = loginBO.getInternalUsersOffice(loginForm.getUserId());
                     loginForm.setOfficeList(internalUserOfcList);
                }
               
                if(LoginConstant.DB_USER_BLOCKED.equals(status[0])) {
	            	String errormsg=(String)errorList.get(0);
	                request.setAttribute(LoginConstant.USER_ERROR, "true" );
	                request.setAttribute(LoginConstant.USER_ERROR_LIST, status[0]);
	                session.setAttribute(LoginConstant.USER_ERROR_LIST, status[0]);
	                request.setAttribute(LoginConstant.USER_ALERT, errormsg);
	                session.setAttribute("UserId", userDO.getUserId());
	                session.setAttribute("DB_USER_BLOCKED", status[0]);
	                userStatus=status[0];
	             //   forwardTo = "securityques";
	            }else if(LoginConstant.DB_USER_LOCKED.equals(status[0])) {

	                request.setAttribute(LoginConstant.USER_ERROR, "true" );
	                
	            //    if(n>=3){
	                	errorList=new ArrayList();
	                	//errorList.add(prObj.getValue("error.header"));
	                	errorList.add(prObj.getValue("DB_LOCK_PWD"));
	                	request.setAttribute(LoginConstant.USER_ERROR_LIST, errorList);
	               /* }
	                else{
	                request.setAttribute(LoginConstant.USER_ERROR_LIST, errorList);
	               }*/
	                session.removeAttribute("UserId");
	                forwardTo = "login";
	            
	            }
	            else if(rule.isError()) {
	            	
	                request.setAttribute(LoginConstant.USER_ERROR, "true" );
	                request.setAttribute(LoginConstant.USER_ERROR_LIST, errorList);
	             
	                session.removeAttribute("UserId");
	                forwardTo = "login";
	                
	            }else {
	            	
	            	//reset attempt count and status.
	            	//boolean resetAccount = loginBO.resetAttemptCount(loginForm.getUserId());
	            	
	                request.removeAttribute(LoginConstant.USER_ERROR);
	                request.removeAttribute(LoginConstant.USER_ERROR_LIST);
	                session.setAttribute(WebConstants.SES_USER_DO, userDO);
	                String userID = userDO.getUserId();
	                session.setAttribute("UserId", userID);
	                noAttem = String.valueOf(0);
	                noAttempt = 0;
	                session.removeAttribute("noAttempt");
	                if(noAttem == null ) {
	                	noAttem = String.valueOf(0);
	                }
					//Creating Menu
	                               
					//HashMap mapRoleId = menubd.getEmpRole(userID);       //check
				    //String roleName = menubd.getEmpRoleName(userID);     //check
					roleID = status[1];
				    userDO.setRoleID(roleID);
	                //userDO.setRoleName(roleName);
					//session.setAttribute("roleName", roleName);
					//logger.debug("user role Name"+roleName);
					session.setAttribute("roleId", roleID);
					session.setAttribute("role", roleID);
					session.setAttribute("loggedInRole", roleID);
					
					/*ArrayList combinedSMList= new ArrayList();
					ArrayList combinedFList= new ArrayList();
					ArrayList combinedAList = new ArrayList();
						
					ArrayList moduleList = menubd.getUserModules(mapRoleId);
						
					if(moduleList!=null && moduleList.size()>0){
						ArrayList subModList = new ArrayList();
						ArrayList mIdList = new ArrayList();
						for(int k=0;k<moduleList.size();k++){
							ArrayList tmpList = (ArrayList)moduleList.get(k);
							String modID=(String)tmpList.get(0);
							mIdList.add(modID);
							subModList = menubd.getUserSubMods(modID, mapRoleId);
							combinedSMList.addAll(subModList);
						}
					  //logger.debug("combined list==>"+combinedSMList);
					  //logger.debug("Final Module IDs List -->"+mIdList);
					  session.setAttribute("ModList", mIdList);
					}		
					if(combinedSMList.size()>0 && combinedSMList!=null){
						ArrayList fncList = new ArrayList();
						ArrayList smIdList = new ArrayList();
						for(int s =0; s < combinedSMList.size();s++){
							ArrayList tmpSMList = (ArrayList)combinedSMList.get(s);
							String smID =(String) tmpSMList.get(0);
							smIdList.add(smID);
							if(smID!=null){
							fncList = menubd.getUserFunctions(smID,mapRoleId); //problem area...
							combinedFList.addAll(fncList);    
							}//problem area...
						}
						//logger.debug("combined function list==>" +combinedFList);
						//logger.debug("Final Submodule IDs List -->"+smIdList);
						session.setAttribute("SubModList", smIdList);
					}
					if(combinedFList!= null && combinedFList.size()>0){
						ArrayList activityList =new ArrayList();
						ArrayList fncIdList = new ArrayList();
						for(int f=0; f<combinedFList.size();f++){
							ArrayList tmpfncList = (ArrayList)combinedFList.get(f);
							String fncId = (String)tmpfncList.get(0);
							fncIdList.add(fncId);
							if (fncId!=null){
							activityList = menubd.getUserActivities(fncId, mapRoleId);
							combinedAList.addAll(activityList); 
						}//problem
						}
						//logger.debug("combined activity list is:   "+combinedAList);
						//logger.debug("Function Ids List is:===>"+fncIdList);
						session.setAttribute("fncList", fncIdList);
					}
						
					if(combinedAList!= null && combinedAList.size()>0){
						ArrayList actIdList = new ArrayList();
						Set uniqueActIds = new TreeSet();
						for(int f=0; f<combinedAList.size();f++){
							ArrayList tmpactList = (ArrayList)combinedAList.get(f);
							String actId = (String)tmpactList.get(0);
							actIdList.add(actId);
							uniqueActIds.addAll(actIdList);
							actIdList.clear();
						}
						actIdList.addAll(uniqueActIds);
						//logger.debug("Final Activity Ids List is:===>"+actIdList);
						
						session.setAttribute("activityList", actIdList);
					}
	                
	                
	                //session.setAttribute("menu", "rolegroup"+id+".js");
	                forwardTo = "welcome";*/
					//above code commented by Roopam
					
					//FOLLOWING CODE ADDED BY ROOPAM FOR STORING OFFICE , DISTRICT, ROLE IDS
					//OF LOGGED IN USER INTO SESSION 11 April 2013
					ArrayList officeIdList=loginBO.getOfficeIdOfLoggedInUser(session.getAttribute("UserId").toString());
					
					if(officeIdList!=null && officeIdList.size()>0){
						session.setAttribute("officeIdList", officeIdList);
						logger.debug("office id of logged in user :-------"+session.getAttribute("officeIdList").toString());
					}
					
					//end of code FOR STORING OFFICE , DISTRICT, ROLE IDS
	            }
            }  
          
        //}         
         else if(userTypeId.equalsIgnoreCase("") && "loginAction".equals(actionName)){
        	 
        	 
        	 if("".equals(loginForm.getUserId()) 
 					|| loginForm.getUserId()==null ){
 				
        		 errorList.add(prObj.getValue("DB_ENTER_THE_USER_NAME"));
        		
 			}
 			if("".equals(loginForm.getPassword()) 
 					|| loginForm.getPassword()==null ){
 				
 				errorList.add(prObj.getValue("DB_ENTER_THE_PASSWORD"));
 				
 			}else{

        	
         	errorList.add(prObj.getValue("DB_INVALID_USER_PWD"));
         	loginForm.setUserId("");
         	loginForm.setPassword("");
 			}
         	request.setAttribute(LoginConstant.USER_ERROR, "true" );
             request.setAttribute(LoginConstant.USER_ERROR_LIST, errorList);
          
             session.removeAttribute("UserId");
             forwardTo = "login";
         	userStatus=" ";
         
        	 
         }
         else if(userTypeId.equalsIgnoreCase("") && !"loginAction".equals(actionName)){

        	//session.removeAttribute("UserId");
             forwardTo = "login";
         	userStatus=" ";
         
         }
         else if(!userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)){

        	//errorList=new ArrayList();
        	//errorList.add(prObj.getValue("error.header"));
        	errorList.add(prObj.getValue("DB_EXTERNAL_USER"));
        	request.setAttribute(LoginConstant.USER_ERROR, "true" );
            request.setAttribute(LoginConstant.USER_ERROR_LIST, errorList);
         
            session.removeAttribute("UserId");
            forwardTo = "login";
        	userStatus=" ";
        
        }
         /*}else{
         	//session.removeAttribute("UserId");
             forwardTo = "login";
         	userStatus=" ";
         }*/
            
            
            if(session !=null && session.getAttribute("UserId") !=null && userStatus==null) {
            	
            	String userID = (String)  session.getAttribute("UserId");
            	//userID=userID.toUpperCase();
            	//logger.debug("User ID session:-"+userID);
            	//HashMap roleId = menubd.getEmpRole(userID);
            	HashMap roleId = menubd.getEmpRole(userID,loginForm.getMasterList().getOfficeID());
            	
            	 String role = loginForm.getMasterList().getRoleID();
            	 
            	 String roleID = "";
                 String roleName = "";
                 if(role!=null) {
                 	String[] roleDetail = role.split("~");
                 	if(roleDetail !=null && roleDetail[0]!=null) {
                 		roleID = roleDetail[0];
                 	}
                 	if(roleDetail !=null && roleDetail[1]!=null) {
                 		roleName = roleDetail[1];
                 	}
                 }
				//String roleName = menubd.getEmpRoleName(userID);
				
				session.setAttribute("roleName", roleName);
				//logger.debug("user role==>"+roleId+"  user roleName==>"+roleName);
				session.setAttribute("roleId", roleID);
				session.setAttribute("role", roleID);	
				ArrayList combinedSMList= new ArrayList();
				ArrayList combinedFList= new ArrayList();
				ArrayList combinedAList = new ArrayList();
				
				String[] roles = getRoleID(roleId);
				//session.setAttribute("ModList", mIdList);
				//session.setAttribute("SubModList", smIdList);
				//session.setAttribute("fncList", fncIdList);
				//session.setAttribute("activityList", actIdList);
				
//				System.gc();
				HashMap <String, String> empDtls = loginBO.getUserEmpIDAndDesignation(userID);
				logger.debug("Before offcAndActivityDetails call");
				try{
				HashMap  offcDtls = loginBO.offcAndActivityDetails(userID);
				logger.debug("after offcAndActivityDetails call");
				logger.debug("offcDtls map size "+offcDtls.size());
				session.setAttribute("officeactivitydata",offcDtls);
				}catch(Exception e)
				{
					logger.error("Exception"+e);
				}
				
				session.setAttribute("loggedInEMPName", "");
				session.setAttribute("loggedInEMPDesignation", "");
				if (empDtls.get("name")!=null) {
					session.setAttribute("loggedInEMPName", empDtls.get("name"));
				}
				if (empDtls.get("designation")!= null) {
					session.setAttribute("loggedInEMPDesignation",
							empDtls.get("designation"));
				}
				
				
				
				ArrayList<Map<String, String>> compList = menubd.getCompleteMenuDetails(roles );
				end = System.currentTimeMillis();
				logger.info("Start at : " + start);
				logger.info("End at : " + end);
				logger.info("Diff : " + (end-start));
				Map<String, String> mdlMap = compList.get(0);
				Map<String, String> smlMap = compList.get(1);
				Map<String, String> fncMap = compList.get(2);
				Map<String, String> actMap = compList.get(3);
				ArrayList<String> mdlList = new ArrayList<String>(mdlMap.keySet());
				ArrayList<String> smlList =  new ArrayList<String>(smlMap.keySet());
				ArrayList<String> fncIList = new ArrayList<String>(fncMap.keySet());
				ArrayList<String> actList =  new ArrayList<String>(actMap.keySet());
				session.setAttribute("ModList", mdlList);
				session.setAttribute("SubModList", smlList);
				session.setAttribute("fncList", fncIList);
				session.setAttribute("activityList", actList);
				compList.clear();
				mdlMap.clear();
				smlMap.clear();
				fncMap.clear();
				actMap.clear();
				empDtls.clear();
				if("1".equals(loginBO.checkProbation(userID))){
					session.setAttribute("viewPOC", "yes");
				}else{
					session.setAttribute("viewPOC", "no");
				}
//				System.gc();
//				if( 2 > 1) {
//					Enumeration<String> attrs=  session.getAttributeNames();
//					while(attrs.hasMoreElements()) {
//						String at = attrs.nextElement();
//						Object se = session.getAttribute(at);
//					}
//					 return mapping.findForward("welcome");
//				}
//				
//				ArrayList moduleList = menubd.getUserModules(roleId);
//					
//				
//				if(moduleList!=null && moduleList.size()>0){
//					ArrayList subModList = new ArrayList();
//					ArrayList mIdList = new ArrayList();
//					for(int k=0;k<moduleList.size();k++){
//						ArrayList tmpList = (ArrayList)moduleList.get(k);
//						String modID=(String)tmpList.get(0);
//						mIdList.add(modID);
//						if( modID !=null){
//						subModList = menubd.getUserSubMods(modID, roleId);
//						combinedSMList.addAll(subModList);
//						}
//						}
//				  //logger.debug("combined list==>"+combinedSMList);
//				  //logger.debug("Final Module IDs List -->"+mIdList);
//				  session.setAttribute("ModList", mIdList);
//				}	
//				
//				
//				if(combinedSMList.size()>0 && combinedSMList!=null){
//					ArrayList fncList = new ArrayList();
//					ArrayList smIdList = new ArrayList();
//					for(int s =0; s < combinedSMList.size();s++){
//						ArrayList tmpSMList = (ArrayList)combinedSMList.get(s);
//						String smID =(String) tmpSMList.get(0);
//						smIdList.add(smID);
//						fncList = menubd.getUserFunctions(smID,roleId);
//						combinedFList.addAll(fncList);
//					}
//					//logger.debug("combined function list==>" +combinedFList);
//					//logger.debug("Final Submodule IDs List -->"+smIdList);
//					session.setAttribute("SubModList", smIdList);
//				}
//				
//				
//				if(combinedFList!= null && combinedFList.size()>0){
//					ArrayList activityList =new ArrayList();
//					ArrayList fncIdList = new ArrayList();
//					for(int f=0; f<combinedFList.size();f++){
//						ArrayList tmpfncList = (ArrayList)combinedFList.get(f);
//						String fncId = (String)tmpfncList.get(0);
//						fncIdList.add(fncId);
//						activityList = menubd.getUserActivities(fncId, roleId);
//						combinedAList.addAll(activityList);
//					}
//					//logger.debug("combined activity list is:   "+combinedAList);
//					//logger.debug("Function Ids List is:===>"+fncIdList);
//					session.setAttribute("fncList", fncIdList);
//				}
//				
//					
//				if(combinedAList!= null && combinedAList.size()>0){
//					ArrayList actIdList = new ArrayList();
//					Set uniqueActIds = new TreeSet();
//					for(int f=0; f<combinedAList.size();f++){
//						ArrayList tmpactList = (ArrayList)combinedAList.get(f);
//						String actId = (String)tmpactList.get(0);
//						actIdList.add(actId);
//						uniqueActIds.addAll(actIdList);
//						actIdList.clear();
//					}
//					actIdList.addAll(uniqueActIds);
//					//logger.debug("Final Activity Ids List is:===>"+actIdList);
//					
//					session.setAttribute("activityList", actIdList);
//				}
//                
//				Enumeration<String> attrs=  session.getAttributeNames();
//				while(attrs.hasMoreElements()) {
//					String at = attrs.nextElement();
//					Object se = session.getAttribute(at);
//				}
                //session.setAttribute("menu", "rolegroup"+id+".js");
				session.setAttribute("userTypeId", RegInitConstant.LOGGED_IN_USER_DRS);
                forwardTo = "welcome";
            }else if(LoginConstant.DB_USER_BLOCKED.equals(userStatus)){
            	forwardTo = "securityques";
            }
            else{
            	forwardTo = "login";
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            forwardTo = "login";
        }
        return mapping.findForward(forwardTo);
    }
	
	private String[] getRoleID(HashMap rID) {
		String[] param = null;
		if(rID != null ) {
			 param = new String[rID.size()];
			
			Iterator I = rID.entrySet().iterator();
			int i = 0;
			while (I.hasNext()) { 
				Map.Entry me = (Map.Entry) I.next();
				logger.debug("key:-"+me.getKey());
				param[i] = (String) me.getKey();
				i++;
			}
			
		}
		return param;
	}
}
