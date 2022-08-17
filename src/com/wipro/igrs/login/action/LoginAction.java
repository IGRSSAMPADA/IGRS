package com.wipro.igrs.login.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.login.constant.LoginConstant;
import com.wipro.igrs.login.dao.LoginDAO;
import com.wipro.igrs.login.dto.MasterListDTO;
import com.wipro.igrs.login.form.LoginForm;
import com.wipro.igrs.login.loadSingleton.LoadMenuExternal;
import com.wipro.igrs.login.rule.LoginRule;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.payment.dto.OnlinePaymentDto;
import com.wipro.igrs.payment.util.AESEncrypt;
import com.wipro.igrs.payments.bd.PaymentBD;
import com.wipro.igrs.serviceProvider.bd.ServiceProviderAccountBD;
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
        
		
		logger.info("Login Starts uiiiiasffjk ");
		boolean isDoubleVerification=false;
		String forwardTo = "login";
		String doubleVerificationResponse="";
		String enc1 = 	"";
	
	/*	Enumeration attrs =  request.getAttributeNames();
		while(attrs.hasMoreElements()) {
		    logger.debug("Attribute---"+attrs.nextElement());
		}
		
		Enumeration paramms =  request.getParameterNames();
		while(paramms.hasMoreElements()) {
		    logger.debug("Parameters---"+paramms.nextElement());
		  //  logger.debug("Parameter value--"+request.getParameter((String) paramms.nextElement()));
		   // logger.debug("parameter value---"+request.getParameter((String) paramms.nextElement()));
		}
		
		request.getParameterNames();
		logger.debug("Parameters----"+request.getParameterNames());*/
		
		String dataIFMIS = request.getParameter("enc_data");
		logger.debug("dataIFMIS---"+dataIFMIS);
		String data = request.getParameter("enc_data");
		String DoubleVerificationData = request.getParameter("encdata");
		
		if(data!=null)
		{
			enc1=data;
		//enc1 = URLDecoder.decode(data);
		
		logger.debug("Data recieved from the MP treasury Website : "+enc1);
		}
		else if(DoubleVerificationData!=null && !DoubleVerificationData.equals(""))
		{
			enc1 = DoubleVerificationData;
		}else{
			enc1=null;
		}
		
		
		
		
		logger.info("encoded Data is : "+enc1);
	
	
		String onlineFlag = "";
		
		
		
		   String lang=request.getParameter("language");
		
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
        if(loginForm.getUserId()!=null)
        logger.debug(loginForm.getUserId());
       //ActionForm f =  (ActionForm) session.getAttribute("estampFormBean");
        
      //Ajit Change for Double Verification response Start
        
    	if(request.getParameter("encdata")!=null && !request.getParameter("encdata").equals("")){
			 doubleVerificationResponse = request.getParameter("encdata");
			logger.debug("doubleVerificationResponse :"+doubleVerificationResponse);
			
		}
    	
    	
		if(doubleVerificationResponse!=null && !doubleVerificationResponse.equals("")){
			
			logger.debug("Found some data.... Now Decrypting.........");
			AESEncrypt crypt = new AESEncrypt();
			String keyPath=prObj.getValue("mp_treasury_key_ifmis");
			
			crypt.setSecretKey(keyPath);
			
			logger.debug("Cyber Treasury Key Path :"+keyPath);
			
			String decryptdoubleVerificationResponse = crypt.decryptFile(doubleVerificationResponse);

			logger.debug("decryptdoubleVerificationResponse--"+decryptdoubleVerificationResponse);
		}
		//Ajit Change for Double Verification response End
        
        
        if(enc1!=null && !"".equalsIgnoreCase(enc1))
		{
        	
        	logger.debug("Found some data.... Now Decrypting.........");
			AESEncrypt crypt = new AESEncrypt();
			String keyPath=prObj.getValue("mp_treasury_key_ifmis");
			
			crypt.setSecretKey(keyPath);
			
			logger.debug("Cyber Treasury Key Path :"+keyPath);
			
			String decryptString = crypt.decryptFile(enc1);
			
			logger.debug("Decrypted Text :"+decryptString);
			
			
			//decryptString ="crn=IGR091014035755018|amount=50|challan_number=00302|challan_date=24092014|cin=BARB0BANEAS00302409201400002|brn=106301|scroll_number=2409201401|scroll_date=24092014|transaction_date_time=24/09/2014 12:46:16|checkSum=922d489c951d8a41b752764c20677978"
			
			
			//Preeti Changes on 24 Nov 2015
			
			//String decryptString= "" ;
		    String crn = "" ;
		    String brn = "" ;
		    String cinNumber="";
		    String amount="";
		    String challanNumber="";
		  
		   
		   
		   
		 //  decryptString ="crn=IGR091014035755018|amount=50|challan_number=00302|challan_date=24092014|cin=BARB0BANEAS00302409201400002|brn=106301|scroll_number=2409201401|scroll_date=24092014|transaction_date_time=24/09/2014 12:46:16|checkSum=922d489c951d8a41b752764c20677978";
		   String ar[] = decryptString.split("\\|");
		   ArrayList adbsa = new ArrayList();
		   
		   
		   for(int i=0;i<ar.length;i++){
			   
			   
			   if(ar[i].contains("crn"))
		           
		       {
		                       String a[] = ar[i].split("\\=");
		                       if(a[1]!=null)
		                       crn = a[1];
		                       adbsa.add(crn);
		                      // dsfshff.add(adbsa);
		                     
		       }
			    
			   
			   
			   if(ar[i].contains("total_amount")) //total_amount Ajit amount
		       {
		            String a[]=ar[i].split("\\=");
		            if(a[1]!=null)
		            amount=a[1];
		           adbsa.add(amount);
		       } 
			   //For double verification
			 /*  if(ar[i].contains("amount")) //total_amount Ajit amount
		       {
		            String a[]=ar[i].split("\\=");
		            if(a[1]!=null)
		            amount=a[1];
		           adbsa.add(amount);
		           isDoubleVerification=true;
		           logger.debug("Making isDoubleVerification true");
		       } */
			   
			 
			   if(ar[i].contains("brn"))
		       {
		                       String a[]=ar[i].split("\\=");
		                     //  if(a[1]!=null)
		                       if(a[1]!=null)
		                       brn=a[1];
		                       adbsa.add(brn);
		                      
		       }
			   if(ar[i].contains("cin"))
					
				{
					String a[] = ar[i].split("\\=");
					if(a[1]!=null){
	                       cinNumber=a[1];
	                       adbsa.add(cinNumber);
					logger.debug("Cin"+a[1]);
					}
				}
			   if(ar[i].contains("Challan_number"))
					
				{
					String a[] = ar[i].split("\\=");
					if(a[1]!=null){
						challanNumber=a[1];
	                       adbsa.add(challanNumber);
					logger.debug("Challan_number"+a[1]);
					}
				}
			   if(ar[i].contains("challan_number"))
					
				{
					String a[] = ar[i].split("\\=");
					if(a[1]!=null){
						challanNumber=a[1];
	                       adbsa.add(challanNumber);
					logger.debug("challan_number"+a[1]);
					}
				}
			    }
		   PaymentBD paymentBD = new PaymentBD();
		   
		  // paymentBD.getTreasurydata(adbsa);
		   paymentBD.insertURL(crn,"I",decryptString); // updating IGRS_OFFLINE_CYBER
			
			
			//Preeti changes end here on 24 Nov 2015
			
           String arr[] = decryptString.split("\\|");
			
		/*	for(int i=0;i<arr.length;i++)
			{
				if(arr[i].contains("crn"))
					
				{
					String a[] = arr[i].split("\\=");
					crn = a[1];
					break;
				}
			}*/
			
			
			//Changes by preeti to check brn number
			
			/*String brn="";
			for(int j=0;j<arr.length;j++){
				if(arr[j].contains("brn"))
				{
					String a[]=arr[j].split("\\=");
					brn=a[1];
					break;
				}
			}
			*/
			logger.debug("amount to tally with database---"+amount);
			/*Long amountInt=Long.parseLong(amount);
			amount=String.valueOf(amountInt);*/
			logger.debug("amount before tally---"+amount);
			if (amount.contains(".")){
				amount=amount.substring(0, amount.indexOf("."));
			}
           boolean boo=paymentBD.checkAmount(crn,amount);//Change by Ajit for floating number
			
			logger.debug("CRN fetched : "+crn);
			logger.debug("BRN fetched : "+brn);
			logger.debug("boo ::: "+boo);
			
			
		
			if((crn!="" || crn!=null)&& (cinNumber!="" || cinNumber!=null) && (brn!="" || brn!=null) && (amount!="" ||amount!=null) && boo)
			{
				logger.debug("All is fine in payment");
				OnlinePaymentDto dto = LoadMenuExternal.getInstance().getOnlinePaymentDto(crn);
				logger.debug("OnlinePaymentDto from LoadMenuExternal----"+dto);
				//Added by Ajit for dto problem on production start
				if(dto==null){
				 dto = getPaymentDTO(crn);  // LoadMenuExternal.getInstance().getOnlinePaymentDto(crn);
				 logger.debug("dto from payment data verification table----------"+dto);
				 if(dto!=null){
				 LoadMenuExternal.getInstance().getDto().put(crn, dto); 
				 }
				}
				//Added by Ajit for dto problem on production End
				
			/*	 boolean userCheck=false;
				if(!isDoubleVerification){
					userCheck=paymentBD.checkUser(crn, dto.getUserId());
				}else{
					userCheck=true;
				}
				  
				if(userCheck==false){

					logger.debug("CRN and BRN number not found 1");
					forwardTo = "errorCRNandBRN";
					return mapping.findForward(forwardTo);
				
				}*/
				if(dto!=null)
				{
					logger.debug("Got the dto with user Id "+dto.getUserId());
					for(int i=0;i<arr.length;i++)
					{
						if(arr[i].contains("crn"))
							
						{
							String a[] = arr[i].split("\\=");
							dto.setCrnNumber(a[1]);
							logger.debug("CRN"+a[1]);
						}
						
						if(arr[i].contains("Challan_number")) //challan_number
	
						{
							String a[] = arr[i].split("\\=");
							dto.setChallanNumber(a[1]);
							logger.debug("Challan"+a[1]);
						}
						if(arr[i].contains("challan_number")) //challan_number
							
						{
							String a[] = arr[i].split("\\=");
							dto.setChallanNumber(a[1]);
							logger.debug("challan_number"+a[1]);
						}
						if(arr[i].contains("cin"))
	
						{
							String a[] = arr[i].split("\\=");
							dto.setCin(a[1]);
							logger.debug("Cin"+a[1]);
						}
						if(arr[i].contains("brn"))
							
						{
							String a[] = arr[i].split("\\=");
							dto.setBrn(a[1]);
							logger.debug("Brn"+a[1]);
						}
						if(arr[i].contains("scroll_number"))
							
						{
							String a[] = arr[i].split("\\=");
							dto.setScrollNumber(a[1]);
							logger.debug("Scroll Number"+a[1]);
						}
						
						if(arr[i].contains("scroll_date"))
							
						{
							String a[] = arr[i].split("\\=");
							dto.setScrollDate(a[1]);
							logger.debug("Scroll Date"+a[1]);
						}
						if(arr[i].contains("Transaction_date_time"))//Transaction_date_time Ajit transaction_date_time
							
						{
							String a[] = arr[i].split("\\=");
							dto.setTrasaction_date_time(a[1]);
							logger.debug("Trasaction Date Time"+a[1]);
						}
						if(arr[i].contains("total_amount")) // total_amount Ajit amount
													
												{
													String a[] = arr[i].split("\\=");
													dto.setAmount(a[1]);
													logger.debug("amount "+a[1]);
												}
						//Double Verification
						if(arr[i].contains("amount")) // total_amount Ajit amount
							
						{
							String a[] = arr[i].split("\\=");
							dto.setAmount(a[1]);
							logger.debug("amount "+a[1]);
						}
						if(arr[i].contains("Transaction_date_time")) // Ajit this field not coming  challan_date
							
						{
							String a[] = arr[i].split("\\=");
							dto.setChallandate(a[1]);
							logger.debug("challan_date"+a[1]);
						}
						if(arr[i].contains("checkSum"))
						{
							String a[] = arr[i].split("\\=");
							dto.setChecksum(a[1]);
							logger.debug("checkSum"+a[1]);
						}
                       if(arr[i].contains("status"))
							
						{
							String a[] = arr[i].split("\\=");
							dto.setStatusTrs(a[1]);
							//dto.setCrnNumber(a[1]);
							logger.debug("status"+a[1]);
						}
                       if(arr[i].contains("status_desc"))
							
						{
							String a[] = arr[i].split("\\=");
							dto.setStatusDesc(a[1]);
							logger.debug("status_desc"+a[1]);
						}
                       if(arr[i].contains("urn"))
							
						{
							String a[] = arr[i].split("\\=");
							dto.setUrn(a[1]);
							logger.debug("urn"+a[1]);
						}
						
						
					}
					
					onlineFlag="Online";
					//dto.setUserId(dto.getUserId().toUpperCase());
					loginForm.setUserId(dto.getUserId());
					loginForm.setPassword("asdasdsaddsa");
					request.setAttribute("crn", crn);
					
					//Added by Ajit 
					File fi =null;
					FileInputStream in =null;
					ObjectInputStream readers = null;
					ActionForm formData = null	;
					ActionForm paymentForm = null	;
					
					if(dto.getFormPath()!=null){
						logger.debug("Found getFormPath");
					fi = new File(dto.getFormPath());
					
					if(fi.exists())
					{
						logger.debug("Found File Module");
						 in = new FileInputStream(dto.getFormPath());
						 readers = new ObjectInputStream(in);
						 formData = (ActionForm) readers.readObject();
						 if(formData==null)
						 {
							 onlineFlag="";
						 }
						 logger.debug("Got the Object Module");
					}
					
					}
					if(dto.getPaymentFormPath()!=null){
						logger.debug("Found getPaymentFormPath");
					 fi = new File(dto.getPaymentFormPath());
					if(fi.exists())
					{
						logger.debug("Found File Payment");
						 in = new FileInputStream(dto.getPaymentFormPath());
						 readers = new ObjectInputStream(in);
						 paymentForm = (ActionForm) readers.readObject();
						 if(paymentForm==null)
						 {
							 onlineFlag="";
						 }
						 logger.debug("Got the Object Payment");
					}
					}
					session.setAttribute("modName",dto.getModName());
					 
					session.setAttribute("fnName",dto.getFnName());
					session.setAttribute(dto.getFormName(), formData);
					session.setAttribute("paymentForm", paymentForm);
					lang = dto.getLanguage();
				}
			}
			
		//this else is for forwarding new page when brn , crn are empty
			else{
				logger.debug("CRN and BRN number not found 2");
				forwardTo = "errorCRNandBRN";
				return mapping.findForward(forwardTo);
			}
			
			//end of else 
		}
        
        
        
        Date currSysDate = new Date();
        Calendar sysCal = Calendar.getInstance();
     
        LoginBD loginBO = new LoginBD();
        
        session.setAttribute("DB_USER_BLOCKED", "");
        session.setAttribute("currSysYear", sysCal.get(Calendar.YEAR));
		session.setAttribute("currSysDate", currSysDate );
		session.setAttribute("loggedToOffice", loginForm.getMasterList().getOfficeID());
	
		userDO = new UserDTO();
        CryptoLibrary crpt = new CryptoLibrary();
        MenuBD menubd = new MenuBD();
        
       
      //commented  loginForm.setListRole(loginBO.getRoleName());
        String actionName = loginForm.getActionName();
		if(actionName!=null){
			
		}else{
			
			if(onlineFlag.equalsIgnoreCase("Online"))
				actionName ="loginAction";
			else
			actionName="";
			
		}
        
        try {
        	//loginForm.setUserId(loginForm.getUserId().toUpperCase());
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
		
		//lang parameter added by shruti in order to remove compilation issue--11 sep 2014
			session.setAttribute("userDetls", loginBO.getLoggedInUserDetls(loginForm.getUserId(),lang));   
			//end
			
			
			
			
			
			
			
                
         /*if(request.getParameter("parameter")!=null)
            {
         if((request.getParameter("parameter")).equalsIgnoreCase("usrofficeselect"))    //change
            {
            ArrayList internalUserOfcList = loginBO.getInternalUsersOffice(loginForm.getUserId());
            loginForm.setOfficeList(internalUserOfcList);
            }
            }*/
         //above commented and below added by roopam for resolving bug no. 925 date: 16 nov. 2013
         /*if(actionName.equalsIgnoreCase("GET_OFFICE_ACTION")){
        	 loginForm.setActionName("");
             ArrayList internalUserOfcList = loginBO.getInternalUsersOffice(loginForm.getUserId());
             loginForm.setOfficeList(internalUserOfcList);
              
         }*/
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
        /* String fromModule="";
         if(request.getParameter("fromModule")!=null && request.getParameter("fromModule").toString().equalsIgnoreCase("UserReg")){
        	 fromModule="UserReg";
         }*/
         String userNo[]=loginBO.getUserTypeNoAttempt(loginForm.getUserId());
         String userTypeId="";
       //  if(fromModule.equalsIgnoreCase(""))
       //  {
         ArrayList errorList=new ArrayList();
         RegCommonBO commonBo=new RegCommonBO();
         if(userNo[0]!=null)
         {	 
         userTypeId=userNo[0];
         }
        // if(userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) ||
		//			userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)){
         if("loginAction".equals(actionName) && (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) ||
					userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)
					|| userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PUBOFC) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_TEHSILDAR) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_HONORABLE_JUDEG)) )
         {
            	
          userDO.setUserId(loginForm.getUserId());
          userDO.setPassword(loginForm.getPassword());
                
          // String noAttem =(String)session.getAttribute("noAttempt");
         
          String noAttem = userNo[1];
          int n =0; 
        	  
        	  if(noAttem!=null && !noAttem.equalsIgnoreCase("")){
        		  
        		n = Integer.parseInt(noAttem);
        	  }else{
        		  noAttem="0";  
        	  }
        	  
          /*if (n>=3)
          {
        	  boolean lockAccount = loginBO.lockAccount(loginForm.getUserId());
        	  logger.debug("lock updated : "+lockAccount);
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
           String encryptpswd = userDO.getPassword();//added by Mohit
           String roleID = "";
                
            String salt1 =   loginForm.getSalt1();
            String salt2 = loginForm.getSalt2();
            
           // String newpass = salt1+encryptpswd+salt2;
           // logger.debug(newpass);
           // String finalencrytPassword = crypt.SHAencryption512CryptoJS(newpass);
            
            
           /*String[] status =loginBO.authenticateUser(userDO.getUserId(),
                 					encryptpswd, noAttem,roleID,masterList.getOfficeID()); */
           Boolean isResponseCorrect =Boolean.FALSE;
           String captchaId = (String) request.getSession().getAttribute("QARFAD");
           String responses = request.getParameter("j_captcha_response");
           try {
              isResponseCorrect = IGRSCommon.validateCaptcha(captchaId,responses);
              //isResponseCorrect = true; 
           } catch (CaptchaServiceException e) {
                logger.debug("Failed to get Captcha",e);
           }
           String[] status = null;
                if(!onlineFlag.equalsIgnoreCase("Online"))
                	if(isResponseCorrect)
                    {
                    status = 	loginBO.authenticateUserSalting(userDO.getUserId(), encryptpswd, noAttem, roleID, salt1, salt2);  
                    }
                    else
                    {
                 	   status = new String[1];
                 	   status[0] = LoginConstant.DB_INVALID_CAPTCHA;
                    }
     
                else
                {
                	logger.debug("Bypassing User Authentication and Reistablishing session for Online User");
                	status =new String[3];
             LoginDAO dao = new LoginDAO();
           String al[] =   dao.getRole(userDO.getUserId());
                status[0] ="IGRS_DB_USER_004";
                status[1] = al[0];
                status[2] = al[1];
                }
                //userDO.getPassword() replaced by encryptpswd by roopam
                logger.debug("status:-"+status[0]+":"+noAttem);

                String loginStatus = status[0];//added by Ankita
                
                LoginRule rule = new LoginRule();
                errorList = rule.validateUser(status[0], userDO);
            	
	            request.removeAttribute(LoginConstant.USER_ERROR);
	            request.removeAttribute(LoginConstant.USER_ERROR_LIST);
	            
	            
                if(LoginConstant.DB_INVALID_PWD.equalsIgnoreCase(loginStatus)) {
                	//noAttempt = noAttempt+1;
                	logger.debug("attempt number is"+noAttempt);
                	//String getCurrTimeStamp = loginBO.getCurrTimeStamp();
                	
                //	int noAttempt = loginBO.addNoAttempt(loginForm.getUserId(),getCurrTimeStamp);
                	if (noAttempt>=3)
                    {
                  	 boolean lockAccount = loginBO.lockAccount(loginForm.getUserId());
                  	logger.debug("lock updated : "+lockAccount);
                  	if(lockAccount==true){
                  	status[0]=LoginConstant.DB_USER_LOCKED;
                  	}
                    }
                	//session.setAttribute("noAttempt",String.valueOf(noAttempt));
                //	 ArrayList internalUserOfcList = loginBO.getInternalUsersOffice(loginForm.getUserId());
                  //   loginForm.setOfficeList(internalUserOfcList);
                }
               
                /*if(LoginConstant.DB_USER_BLOCKED.equals(status[0])) {
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
	                }
	                else{
	                request.setAttribute(LoginConstant.USER_ERROR_LIST, errorList);
	               }
	                session.removeAttribute("UserId");
	                forwardTo = "login";
	            
	            }
	            else */if(rule.isError()) {
	            	
	                request.setAttribute(LoginConstant.USER_ERROR, "true" );
	                request.setAttribute(LoginConstant.USER_ERROR_LIST, errorList);
	             
	                session.removeAttribute("UserId");
	                logger.debug("First login 1");
	                forwardTo = "login";
	                
	            }else {
	            	
	            	//reset attempt count and status.
	            	//boolean resetAccount = loginBO.resetAttemptCount(loginForm.getUserId());
	            	
	                request.removeAttribute(LoginConstant.USER_ERROR);
	                request.removeAttribute(LoginConstant.USER_ERROR_LIST);
	                session.setAttribute(WebConstants.SES_USER_DO, userDO);
	               // String userID = userDO.getUserId().toUpperCase();
	                session.setAttribute("UserId", userDO.getUserId());
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
					
					
	/*				CSRF cs = new CSRF();
					
					
					String token = cs.addCSRFToken();
					session.setAttribute("ESLMNP", token);*/
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
					//ArrayList officeIdList=loginBO.getOfficeIdOfLoggedInUser(session.getAttribute("UserId").toString());
					
					//if(officeIdList!=null && officeIdList.size()>0){
					//	session.setAttribute("officeIdList", officeIdList);
					//	logger.debug("office id of logged in user :-------"+session.getAttribute("officeIdList").toString());
					//}
					
					//end of code FOR STORING OFFICE , DISTRICT, ROLE IDS
	            }
            }  
       // }
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
             logger.debug("First login 2");
             forwardTo = "login";
         	userStatus=" ";
         
        	 
         }
          //Ajit Double Verification isDoubleVerification
         else if( isDoubleVerification){

          	//session.removeAttribute("UserId");
         	    logger.debug("First login 5");
               forwardTo = "login";//"urnDetail";
           	userStatus=" ";
           
           }
         else if(userTypeId.equalsIgnoreCase("") && !"loginAction".equals(actionName)){

         	//session.removeAttribute("UserId");
        	    logger.debug("First login 4");
              forwardTo = "login";
          	userStatus=" ";
          
          }
         
         
         else if(!(userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) ||
					userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_TEHSILDAR) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_HONORABLE_JUDEG))){
            //errorList=new ArrayList();
        	//errorList.add(prObj.getValue("error.header"));
        	errorList.add(prObj.getValue("DB_INTERNAL_USER"));
        	request.setAttribute(LoginConstant.USER_ERROR, "true" );
            request.setAttribute(LoginConstant.USER_ERROR_LIST, errorList);
         
            session.removeAttribute("UserId");
            logger.debug("First login 6");
            forwardTo = "login";
        	userStatus=" ";
        }
       /* }else{
        	//session.removeAttribute("UserId");
            forwardTo = "login";
        	userStatus=" ";
        }*/
         
            if(session !=null && session.getAttribute("UserId") !=null && userStatus==null ) {
            	
            	String userID = (String)  session.getAttribute("UserId");
            	//userID=userID.toUpperCase();
            	//logger.debug("User ID session:-"+userID);
            	//HashMap roleId = menubd.getEmpRole(userID);
            	HashMap roleId = loginBO.getEmpRole(userID,loginForm.getMasterList().getOfficeID());
            	
            	//changes by preeti 0n 26 oct 2015
            	if(roleId!=null && (roleId.containsKey("RL1378207223595") ||roleId.containsKey("RL1378207223710")|| roleId.containsKey("RL1378207223838") || roleId.containsKey("RL1378207223774") ))
            	{
            		ServiceProviderAccountBD serviceObj= new ServiceProviderAccountBD();
            		//String licNo=serviceObj.getLicenseNumber(userID);
            		String licNo=serviceObj.getLicenseId(userID);
            		logger.debug("Lin No at the of login in case of Service provider"   +licNo);
            		logger.debug("License No at the of login in case of Service provider"   +    licNo);
            		 session.setAttribute("licNo", licNo);
            	}
            	
            	//end changes by preeti 0n 26 oct 2015
            	
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
	// commented	HashMap <String, String> empDtls = loginBO.getUserEmpIDAndDesignation(userID);
				logger.debug("Before offcAndActivityDetails call");
				try{
			//COMMENTED 	HashMap  offcDtls = loginBO.offcAndActivityDetails(userID);
			//	logger.debug("after offcAndActivityDetails call");
			//	logger.debug("offcDtls map size "+offcDtls.size());
			//	session.setAttribute("officeactivitydata",offcDtls);
				}catch(Exception e)
				{
					logger.error("Exception"+e);
				}
				
				session.setAttribute("loggedInEMPName", "");
				session.setAttribute("loggedInEMPDesignation", "");
			/* commented	if (empDtls.get("name")!=null) {
					session.setAttribute("loggedInEMPName", empDtls.get("name"));
				}
				if (empDtls.get("designation")!= null) {
					session.setAttribute("loggedInEMPDesignation",
							empDtls.get("designation"));
				}*/
				
				ArrayList<Map<String, String>> compList = LoadMenuExternal.getInstance().loadMenu(roles[0]);
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
				
				
				 String ipAddress = request.getHeader("X-FORWARDED-FOR");  
				   if (ipAddress == null) {  
					   ipAddress = request.getRemoteAddr();  
				   }
				
			HashMap<String , String> ipAddressMap =  LoadMenuExternal.getInstance().getMapIP();
			
			ipAddressMap.put(userID, ipAddress);
				//compList.clear();
				//mdlMap.clear();
				//smlMap.clear();
				//fncMap.clear();
				//actMap.clear();
				//empDtls.clear();
				/*if("1".equals(loginBO.checkProbation(userID))){
					session.setAttribute("viewPOC", "yes");
				}else*/{
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
				//session.setAttribute("userTypeId", userTypeId);
				session.setAttribute("userTypeId", "E");
                if(onlineFlag.equalsIgnoreCase("Online")){
                	onlineFlag = "";
                	forwardTo = "online";
                }
                else
                {
                	forwardTo = "welcome";
                }
            }else if(LoginConstant.DB_USER_BLOCKED.equals(userStatus)){
            	forwardTo = "securityques";
            }
            else if(isDoubleVerification){
            	   //Double Verification
            	forwardTo = "login";//"urnDetail";
            }
            else{
            	logger.debug("First login 7");
            	forwardTo = "login";
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            forwardTo = "login";
        }
        logger.debug("forwardTo in LoginAction---"+forwardTo);
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
	
	public  OnlinePaymentDto getPaymentDTO( String crn) throws Exception{
  		OnlinePaymentDto dto =null;
  		DBUtility dbUtil = null;
  		Connection conn=null;
		   dbUtil = new DBUtility();
		   String GET_PAYMENT_DATA_VERIFICATION="SELECT CHALLAN_SERIAL_NUMBER,FUNCTION_ID,PARENT_AMOUNT,PARENT_TABLE,PARENT_KEY,FORWARD_PATH,PARENT_COLUMN,PARENT_OFFICE_ID,PARENT_OFFICE_NAME,PARENT_DISTRICT_ID,PARENT_DISTRICT_NAME,PARENT_REF_ID,MODULE_NAME,FUNCTION_NAME,CRN_NUMBER,PAYING_AMOUNT,LANGUAGE,USERID,FORM_PATH,PAYMENT_FORM_PATH,FORM_NAME,OLD_FORM_NAME,CREATED_DATE FROM IGRS_PAYMENT_DATA_VERIFICATION WHERE CRN_NUMBER=?";
		String   sql=GET_PAYMENT_DATA_VERIFICATION;  
		try{
		 conn=dbUtil.getDBConnection();
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, crn);
		ResultSet rset=pst.executeQuery();
		while(rset.next())
		{     
			 dto=new OnlinePaymentDto();
			    dto.setChallanNumber(rset.getString(1)); 
			    dto.setFuncId(rset.getString(2));
			   dto.setParentAmount(rset.getString(3));
			   dto.setParentTable(rset.getString(4));
			   dto.setParentKey(rset.getString(5));
			   dto.setForwardPath(rset.getString(6));
			   dto.setParentColumnName(rset.getString(7));
			   dto.setParentOffId(rset.getString(8));
			   dto.setParentOffName(rset.getString(9));
			   dto.setParentDistId(rset.getString(10));
			   dto.setParentDistName(rset.getString(11));
			   dto.setParentRefId(rset.getString(12));
			   dto.setModName(rset.getString(13));
			   dto.setFnName(rset.getString(14));
			   dto.setCrnNumber(rset.getString(15));
			   dto.setPayingAmout(rset.getString(16));
			   dto.setLanguage(rset.getString(17));
			   dto.setUserId(rset.getString(18));
			   dto.setFormPath(rset.getString(19));
			   dto.setPaymentFormPath(rset.getString(20));
			   dto.setFormName(rset.getString(21));
			   dto.setOldformName(rset.getString(22));
		}
		
		}finally{
			if(conn!=null)conn.close();
		}
		return dto;
  		
  	}
}
