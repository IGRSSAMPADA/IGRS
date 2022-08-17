/**
 * ServiceProviderAccountAction.java
 */

/**
 * ServiceProviderAccountAction.java
 * Date			Author				Description
 * 21-10-2016	Aakash Agarwal		Validate user id based on license number
 */

package com.wipro.igrs.serviceProvider.action;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.serviceProvider.bd.JudicialServiceProviderAccountBD;
import com.wipro.igrs.serviceProvider.constant.ServiceProviderConstant;
import com.wipro.igrs.serviceProvider.dto.ServiceProviderAccountDTO;
import com.wipro.igrs.serviceProvider.form.ServiceProviderAccountForm;


public class JudicialServiceProviderAccountAction extends BaseAction{

	JudicialServiceProviderAccountBD jProviderBD = null;
	ServiceProviderAccountDTO accountDTO;
	//ServiceProviderAccountDTO accountDTO1=new ServiceProviderAccountDTO();
	String TxnFlag = "";
	String licenseno="";
	String forwardpage = "";
	
	private Logger logger = (Logger) Logger.getLogger(JudicialServiceProviderAccountAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
		String language=(String)session.getAttribute("languageLocale");
		String name = request.getParameter("name");
		ServiceProviderAccountForm providerAccountForm = (ServiceProviderAccountForm) form;
		providerAccountForm.getAccountDTO().setJudLedgerViewSearchMsg("");
		if("hi".equalsIgnoreCase(language))
		{	session.setAttribute("fnName", "क्रेडिट लिमिट");
		}
		if (request.getParameter("funId")!=null)
		{
			providerAccountForm.getAccountDTO().setFunctionId(request.getParameter("funId"));
			logger.debug("Function id is:"+providerAccountForm.getAccountDTO().getFunctionId());
		}
		
		if (name.equalsIgnoreCase("creditlimitForEstamp"))		//for credit limit of Estamp
		{
			try{
				forwardpage= executeSPCreditLimitEstamp(mapping, providerAccountForm,request, response, session);
			}
			catch(Exception e){
				logger.info("Exception in execute() Service Provider Action for credit limit");
			}
		}
		
		//for credit limit of Other Services
		
		/*if (name.equalsIgnoreCase("creditlimitForOtherServices"))		
		{
			try{
				forwardpage= executeSPCreditLimitOthers(mapping, providerAccountForm,request, response, session);
			}
			catch(Exception e){
				logger.info("Exception in execute() Service Provider Action for credit limit");
			}
		}*/
		
		
		if (name.equalsIgnoreCase("ledger"))						
		{
			try{
				forwardpage =executeSPCreditLimitStatement(mapping,providerAccountForm, request, response, session);
			}
			catch(Exception e){
				logger.info("Exception in execute() Service Provider Action for credit limit");
			}
		}
		
		//payment success in case of availing credit limit
		if(name.equals("paymentsuccess")){
			try{	
				logger.info("IN PAYMENT SUCCESS----");
				if (providerAccountForm.getAccountDTO().getFunctionId().equalsIgnoreCase("FUN_504"))
				{
				forwardpage =executeSpPaymentSuccessEstamp(mapping,providerAccountForm, request, response, session);	
				}
				/*else if (providerAccountForm.getAccountDTO().getFunctionId().equalsIgnoreCase("FUN_300"))
				{
					forwardpage =executeSpPaymentSuccessOthers(mapping,
							providerAccountForm, request, response, session);
				}*/
			}
			catch(Exception e)
			{
				logger.info("Error in Action Class");
			}
		}
		
		
		if (name.equalsIgnoreCase("payments")) {
			try {
				logger.info("In Payments");
				
				if (providerAccountForm.getAccountDTO().getFunctionId().equalsIgnoreCase("FUN_504"))			// added by Lavi for payment of avail credit limit for estamps
				{
					forwardpage = executeSPAccountPaymentEstamp(mapping,
						providerAccountForm, request, response, session, providerAccountForm);
				}
				// added by Lavi for payment of avail credit limit for other services
				/*else if (providerAccountForm.getAccountDTO().getFunctionId().equalsIgnoreCase("FUN_300"))
				{
					forwardpage = executeSPAccountPaymentOthers(mapping,
							providerAccountForm, request, response, session, providerAccountForm);
				}*/
				// end of addition by Lavi for payment of avail credit limit for other services
			} catch (Exception e) {
				logger.info("Exception in execute() ServiceProviderAction"
						+ e);
			}
		}
		
		if (name.equalsIgnoreCase("splaccstmtview")) {
			
			
			try {
				if (providerAccountForm.getAccountDTO().getFunctionId().equalsIgnoreCase("FUN_504"))
				{
					
				// START | judicial credit limit ledger duration changes by santosh
				  if(null!=providerAccountForm.getAccountDTO().getFromdate() 
						  && !"".equalsIgnoreCase(providerAccountForm.getAccountDTO().getFromdate())
					     && null!=providerAccountForm.getAccountDTO().getTodate()
					     && !"".equalsIgnoreCase(providerAccountForm.getAccountDTO().getTodate())){
					  JudicialServiceProviderAccountBD judSPProviderBD = new JudicialServiceProviderAccountBD();
					  String judLedgerParam = judSPProviderBD.getJudLedgerParam();
					  if(null!=judLedgerParam && !"".equalsIgnoreCase(judLedgerParam)){
						  
					  int judLedgerParam1 = Integer.parseInt(judLedgerParam);

					  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

					    Date d1 = null;
					    Date d2 = null;
						try{
						    d1 = format.parse(providerAccountForm.getAccountDTO().getFromdate());
					        d2 = format.parse(providerAccountForm.getAccountDTO().getTodate());
					     } catch (ParseException e) {
					        e.printStackTrace();
					    }
					     
					     int diffInDays = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	
							 if(diffInDays>judLedgerParam1){
								
								 if(language.equalsIgnoreCase("en")){
									 providerAccountForm.getAccountDTO().setJudLedgerViewSearchMsg("Please select duration of " + judLedgerParam + " days or less than that.");
								 }else{
									 providerAccountForm.getAccountDTO().setJudLedgerViewSearchMsg("सर्च की अवधी "+ judLedgerParam +" दिन या उस से कम की रखें।");
								 }
									 
								 return mapping.findForward("spcreditlimitstatement");
							 } 
					  }  
				  }
				// END | judicial credit limit ledger duration changes by santosh
				forwardpage=executeSPAccLdgrEstamp(mapping,
						form, request, response, session);
				}
				// added by Lavi for payment of avail credit limit for other services
				/*else if (providerAccountForm.getAccountDTO().getFunctionId().equalsIgnoreCase("FUN_300"))
				{
					forwardpage=executeSPAccLdgrOtherServices(mapping,
							form, request, response, session);
				}*/
				// end of addition by Lavi for payment of avail credit limit for other services
			} catch (Exception e) {
				// Start Validate user id based on license number
				if ("/jsp/SessionExpired.jsp".equals(e.getMessage())) {
					return new ActionForward("/jsp/SessionExpired.jsp");
				}
				// End Validate user id based on license number
				logger.info("Exception in execute() ServiceProviderAction" + e);
			}
		}

		logger.info("FORWARDPAGE"+forwardpage);
		return mapping.findForward(forwardpage);
	}

	/**
	 * Method name :executeServiceProviderAccountsummary
	 * 
	 * @param mapping
	 * @param providerForm
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * 
	 */
	/*private String executeServiceProviderAccountsummary(ActionMapping mapping,
			ServiceProviderAccountForm providerForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String forwardpage = null;
		try {
			ArrayList userinfolist = null;
			String userid = (String) session.getAttribute("UserId");
			providerBD = new ServiceProviderAccountBD();			
			userinfolist = providerBD.getServiceProviderInfo(userid);
			request.setAttribute("myaccount", userinfolist);
			forwardpage = "spaccountsummary";
					
		} catch (Exception e) {
			logger.info("Exception in execute() ServiceProviderAction"
					+ e);
		}
		return forwardpage;
	}*/
	
	/**
	 * Method name :executeSPAccountPaymentEstamp
	 * 
	 * @param mapping
	 * @param providerForm
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * 
	 */
	private synchronized String executeSPAccountPaymentEstamp(ActionMapping mapping,
			ServiceProviderAccountForm providerForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session, ServiceProviderAccountForm providerAccountForm) {
		
		String language=(String)session.getAttribute("languageLocale");
		String forwardpage = null;
		String role = "";
		if(session.getAttribute("role")!=null){
			role=(String)session.getAttribute("roleId");
		}else{
			role="SP";
		}
		try {
			String forwardPath = "./spaccount_judicial.do?name=paymentsuccess&TRFS=NGI";
			String userid = (String) session.getAttribute("UserId");
			session.setAttribute("role", role);
			providerAccountForm.getAccountDTO().setUserId(userid);
			
			//MODIFIED ACC TO NEW TRANSACTION CONCEPT IN PAYMENTS
			logger.info("MISSING LICENSE NUMBER-----"+accountDTO.getLicencenumber());
			accountDTO.setPayamount(providerForm.getAccountDTO().getPayamount());
			logger.info("PERSISTED LICENSE NUMBER-----"+accountDTO.getLicencenumber());
			String parentAmount=accountDTO.getPayamount();
			jProviderBD = new JudicialServiceProviderAccountBD();
			
			String sr_no="";
			  /* COMMENTED BY SHRUTI
			if(providerForm.getSr_no()==null){
			sr_no=providerBD.getCrdtLimitSerialNumber();
			providerForm.setSr_no(sr_no);
			}
			else{
				sr_no=providerForm.getSr_no();	
			}*/
			// added by Lavi for integration with new payment module.
			providerAccountForm=jProviderBD.getOfficeId(providerAccountForm);
			// end of addition by Lavi for integration with new payment module.
			sr_no=jProviderBD.getCrdtLimitSerialNumberEstamp();
			if(sr_no!=null && !"".equalsIgnoreCase(sr_no))
			{
			accountDTO.setSrNo(sr_no);
			boolean check = jProviderBD.spAcntBalUpdtEstamp(accountDTO, userid);
			if(check)
			{
			request.setAttribute("TxnFlag", "Credit");
			request.setAttribute("parentModName","Service Provider");
			request.setAttribute("parentFunName","AVAIL JUDICIAL CREDIT LIMIT ESTAMP");
			request.setAttribute("parentFunId","FUN_504");
			request.setAttribute("parentAmount",parentAmount);
			request.setAttribute("parentKey",sr_no);
			request.setAttribute("parentTable", "IGRS_CREDIT_LIMIT_TXN_J_ESTAMP");
			request.setAttribute("parentColumnName","SR_NO");
			request.setAttribute("forwardPath", forwardPath);
			
			// added by Lavi for integration with new payment module.
			request.setAttribute("parentOfficeId", providerAccountForm.getAccountDTO().getOfcId());
			request.setAttribute("parentOfficeName", providerAccountForm.getAccountDTO().getOfcName());
			request.setAttribute("parentDistrictId", providerAccountForm.getAccountDTO().getDisttId());
			request.setAttribute("parentDistrictName", providerAccountForm.getAccountDTO().getDisttName());
			request.setAttribute("parentReferenceId", providerAccountForm.getAccountDTO().getLicencenumber()); // get from session neeraj
			// end by Lavi for integration with new payment module.
			//modified by shruti---19 nov 2014-payment changes
			request.setAttribute("formName","spaccountForm");  
			//end
			
			//added by shruti--27 feb 2014 for breadcum issue;
			if("en".equalsIgnoreCase(language)){
				session.setAttribute("modName",ServiceProviderConstant.MODNAME_ENGLISH);	
			}else{
				session.setAttribute("modName",ServiceProviderConstant.MODNAME_HINDI);
			}
			if("en".equalsIgnoreCase(language)){
				session.setAttribute("fnName",ServiceProviderConstant.FUNCTION_ENGLISH);
			}else{
				session.setAttribute("fnName",ServiceProviderConstant.FUNCTION_HINDI);
			}
			
			//end
			forwardpage = "payment";
			}
			else
			{
				forwardpage="failure";
			}
			}
			else
			{
				forwardpage="failure";
			}
		} catch (Exception e) {
			logger.info("Exception in execute() ServiceProviderAction"
					+ e);
		}
		return forwardpage;
	}

	/**
	 * Method name :executeSPAccountPayment
	 * 
	 * @param mapping
	 * @param providerForm
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * 
	 */
	/*private synchronized String executeSPAccountPaymentOthers(ActionMapping mapping,
			ServiceProviderAccountForm providerForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session, ServiceProviderAccountForm providerAccountForm) {
		
		String language=(String)session.getAttribute("languageLocale");
		String forwardpage = null;
		String role = "";
		if(session.getAttribute("role")!=null){
			role=(String)session.getAttribute("roleId");
		}else{
			role="SP";
		}
		try {
			String forwardPath = "./spaccount.do?name=paymentsuccess&TRFS=NGI";
			String userid = (String) session.getAttribute("UserId");
			session.setAttribute("role", role);
			providerAccountForm.getAccountDTO().setUserId(userid);
			
			//MODIFIED ACC TO NEW TRANSACTION CONCEPT IN PAYMENTS
			logger.info("MISSING LICENSE NUMBER-----"+accountDTO.getLicencenumber());
			accountDTO.setPayamount(providerForm.getAccountDTO().getPayamount());
			logger.info("PERSISTED LICENSE NUMBER-----"+accountDTO.getLicencenumber());
			String parentAmount=accountDTO.getPayamount();
			providerBD = new ServiceProviderAccountBD();
			
			String sr_no="";
			  /* COMMENTED BY SHRUTI
			if(providerForm.getSr_no()==null){
			sr_no=providerBD.getCrdtLimitSerialNumber();
			providerForm.setSr_no(sr_no);
			}
			else{
				sr_no=providerForm.getSr_no();	
			}*/
			// added by Lavi for integration with new payment module.
			/*providerAccountForm=providerBD.getOfficeId(providerAccountForm);
			// end of addition by Lavi for integration with new payment module.
			sr_no=providerBD.getCrdtLimitSerialNumber();
			if(sr_no!=null && !"".equalsIgnoreCase(sr_no))
			{
			
			accountDTO.setSrNo(sr_no);
			boolean check = providerBD.spAcntBalUpdt(accountDTO, userid);
			if(check)
			{
			request.setAttribute("TxnFlag", "Credit");
			request.setAttribute("parentModName","Service Provider");
			request.setAttribute("parentFunName","Avail Credit Limit Other Services");
			request.setAttribute("parentFunId","FUN_300");
			request.setAttribute("parentAmount",parentAmount);
			request.setAttribute("parentKey",sr_no);
			request.setAttribute("parentTable", "IGRS_CREDIT_LIMIT_TXN_LOG");
			request.setAttribute("parentColumnName","SR_NO");
			request.setAttribute("forwardPath", forwardPath);
			
			// added by Lavi for integration with new payment module.
			request.setAttribute("parentOfficeId", providerAccountForm.getAccountDTO().getOfcId());
			request.setAttribute("parentOfficeName", providerAccountForm.getAccountDTO().getOfcName());
			request.setAttribute("parentDistrictId", providerAccountForm.getAccountDTO().getDisttId());
			request.setAttribute("parentDistrictName", providerAccountForm.getAccountDTO().getDisttName());
			request.setAttribute("parentReferenceId", providerAccountForm.getAccountDTO().getLicencenumber());
			
			//modified by shruti---19 nov 2014-payment changes
			request.setAttribute("formName","spaccountForm");  
			//end
			
			
			// end by Lavi for integration with new payment module.
			//added by shruti--27 feb 2014 for breadcum issue;
			if("en".equalsIgnoreCase(language)){
				session.setAttribute("modName",ServiceProviderConstant.MODNAME_ENGLISH);	
			}else{
				session.setAttribute("modName",ServiceProviderConstant.MODNAME_HINDI);
			}
			if("en".equalsIgnoreCase(language)){
				session.setAttribute("fnName",ServiceProviderConstant.FUNCTION_ENGLISH);
			}else{
				session.setAttribute("fnName",ServiceProviderConstant.FUNCTION_HINDI);
			}
			//end
			forwardpage = "payment";
			}
			else
			{
				forwardpage="failure";
			}
			}
			else
			{
				forwardpage="failure";
			}
		} catch (Exception e) {
			logger.info("Exception in execute() ServiceProviderAction"
					+ e);
		}
		return forwardpage;
	}
*/
	/**
	 * Method name :executeSPAccountStatement
	 * 
	 * @param mapping
	 * @param providerForm
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * 
	 */
	/*private String executeSPAccountStatement(ActionMapping mapping,
			ServiceProviderAccountForm providerForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String forwardpage = null;
		try {
			logger.info("spaccountstatement");
			ArrayList userinfolist = null;
			String userid = (String) session.getAttribute("UserId");
			jProviderBD = new JudicialServiceProviderAccountBD();
				logger.info("spaccountstatement");
				
				userinfolist = jProviderBD.getServiceProviderInfo(userid);
				request.setAttribute("myaccount", userinfolist);
				forwardpage = "accountstatement";
		} catch (Exception e) {
			logger.info("Exception in execute() ServiceProviderAction"
					+ e);
			//logger.info("Exception in Action" + e);
		}
		logger.info("forwardpage");
		//logger.info("forwardpage");
		return forwardpage;
	}*/

	/**
	 * Method name :executeSPAccountStatement
	 * 
	 * @param mapping
	 * @param providerForm
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * 
	 */
	private String executeSPCreditLimitStatement(ActionMapping mapping,
			ServiceProviderAccountForm providerForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String forwardpage = null;
		try {
			logger.info("spcredit limit statement");
			//String userid = (String) session.getAttribute("UserId");
				forwardpage ="spcreditlimitstatement";
		} catch (Exception e) {
			logger.info("Exception in execute() ServiceProviderAction"
					+ e);
		}
		logger.info("forwardpage");
		return forwardpage;
	}

	//added by shruti
	//changed by Shreeraj
	/*private String executeSPAccLdgrOtherServices(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String forwardpage = null;
		//Changes by preeti on 30 nov 2015
		String sessionLicenseNo="";
		sessionLicenseNo=(String) session.getAttribute("licNo");
		logger.debug("SP Licenese No of session"+sessionLicenseNo);
		//
		try {
			
			ServiceProviderAccountForm providerAccountForm = (ServiceProviderAccountForm) form;
			accountDTO=providerAccountForm.getAccountDTO();				
			
			ArrayList userinfolist = null;
			ArrayList accountstmt = null;
			String userid = (String) session.getAttribute("UserId");
			providerBD = new ServiceProviderAccountBD();
			String licenseno=providerBD.getLicenseNumber(userid);
			
			if(sessionLicenseNo.equalsIgnoreCase(licenseno)){
			providerBD = new ServiceProviderAccountBD();
			userinfolist=providerBD.getSpAcntDetails(userid, sessionLicenseNo);
			accountDTO.setLicencenumber((String)userinfolist.get(0));
			accountDTO.setAccountantname((String)userinfolist.get(1));
			providerAccountForm.getAccountDTO().setLicencenumber((String)userinfolist.get(0));
			providerAccountForm.getAccountDTO().setAccountantname((String)userinfolist.get(1));
			providerAccountForm.setOpeningBal((String)userinfolist.get(2));
			providerAccountForm.getAccountDTO().setUserId((String)userinfolist.get(3));
			//accountDTO.setOpeningBal(providerBD.getOpeningBal(licenseno));
			
			//providerAccountForm.setAccountDTO(accountDTO);
			//logger.info("OPENING BALANCE INFO IN ACTION CLASS----"+providerAccountForm.getAccountDTO().getOpeningBal());
		/*	ArrayList userinfolist1=new ArrayList();
			userinfolist1.add(accountDTO);
			request.setAttribute("myaccount", userinfolist1);*/
			
				/*String language=(String)session.getAttribute("languageLocale");
				accountstmt = providerBD.getServiceProviderStmt(sessionLicenseNo,accountDTO,language);
				providerAccountForm.getAccountDTO().setLedgerList(accountstmt);
				logger.info("SSS_____"+accountstmt.size());
				//request.setAttribute("myaccount", userinfolist);
				/*request.setAttribute("accountstmt", accountstmt);
				request.setAttribute("providerDTO", accountDTO);
				providerAccountForm.setOpeningBal(providerBD.getOpeningBal(sessionLicenseNo));*/
				/*logger.info("FORM-----"+providerAccountForm.getOpeningBal());
				forwardpage = "accountstmtview1";
				/*forwardpage = "accountstmtview";	*/	
			
			/*}
			else{
				forwardpage = "SPCreditLimitFail";
				logger.debug("forwared page in case of sp mismatch"+forwardpage);
			}
		
		}catch (Exception e) {
					logger.info("Exception in execute() ServiceProviderAction"
							+ e);
		}
		logger.info("forwardpage :"+forwardpage);
		return forwardpage;
	}*/
//end of code
	
	//added by Lavi for ledger of estamp payment
	private String executeSPAccLdgrEstamp(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		String forwardpage = null;
		//Changes by preeti on 30 nov 2015
		String sessionLicenseNo="";
		sessionLicenseNo=(String) session.getAttribute("licNo");
		logger.debug("SP Licenese No of session"+sessionLicenseNo);
		//
		
		try {
			String language=(String)session.getAttribute("languageLocale");
			ServiceProviderAccountForm providerAccountForm = (ServiceProviderAccountForm) form;
			accountDTO=providerAccountForm.getAccountDTO();				
			
			ArrayList userinfolist = null;
			ArrayList accountstmt = null;
			String userid = (String) session.getAttribute("UserId");
			jProviderBD = new JudicialServiceProviderAccountBD();
			String licenseno=jProviderBD.getLicenseNumber(userid);
			//providerBD = new ServiceProviderAccountBD();
			//Changes by preeti on 30 nov 2015
			if(sessionLicenseNo.equalsIgnoreCase(licenseno)){
			//
			userinfolist=jProviderBD.getSpAcntDetailsEstamp(userid, sessionLicenseNo);
			accountDTO.setLicencenumber((String)userinfolist.get(0));
			accountDTO.setAccountantname((String)userinfolist.get(1));
			providerAccountForm.getAccountDTO().setLicencenumber((String)userinfolist.get(0));
			providerAccountForm.getAccountDTO().setAccountantname((String)userinfolist.get(1));
			providerAccountForm.setOpeningBal((String)userinfolist.get(2));
			providerAccountForm.getAccountDTO().setUserId((String)userinfolist.get(3));
			//accountDTO.setOpeningBal(providerBD.getOpeningBal(licenseno));
			
			//providerAccountForm.setAccountDTO(accountDTO);
			//logger.info("OPENING BALANCE INFO IN ACTION CLASS----"+providerAccountForm.getAccountDTO().getOpeningBal());
			//ArrayList userinfolist1=new ArrayList();
		//	userinfolist1.add(accountDTO);
		//	request.setAttribute("myaccount", userinfolist1);
			
			// Start Validate user id based on license number
			accountstmt = jProviderBD.getServiceProviderStmtEstamp(
					sessionLicenseNo, accountDTO, language, session);
			// End Validate user id based on license number
				providerAccountForm.getAccountDTO().setLedgerList(accountstmt);
				logger.info("SSS_____"+accountstmt.size());
				//request.setAttribute("myaccount", userinfolist);
				//request.setAttribute("accountstmt", accountstmt);
				//request.setAttribute("providerDTO", accountDTO);
				//providerAccountForm.setOpeningBal(providerBD.getOpeningBalEstamp(sessionLicenseNo));
				logger.info("FORM-----"+providerAccountForm.getOpeningBal());
				request.setAttribute("function", "FUN_504");
				forwardpage = "accountstmtview1";
				/*forwardpage = "accountstmtview";	*/	
			
			}
			
			else{
				forwardpage = "SPCreditLimitFail";
				logger.debug("forwared page in case of sp mismatch"+forwardpage);
			}
			}
		
		
		
		
			catch (Exception e) {
				// Start Validate user id based on license number
				if ("/jsp/SessionExpired.jsp".equals(e.getMessage())) {
					throw e;
				}
				// End Validate user id based on license number
				logger.info("Exception in execute() ServiceProviderAction" + e);
			}
			
			
			
		logger.info("forwardpage :"+forwardpage);
		return forwardpage;
	}
//end of code

		
		//added by shruti
	/*	private String executeSPAvailCreditLimit(ActionMapping mapping,
				ServiceProviderAccountForm providerForm,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) {
			String forwardpage = null;
			try {
				accountDTO=providerForm.getAccountDTO();
				String userid = (String) session.getAttribute("UserId");
				jProviderBD = new JudicialServiceProviderAccountBD();
				String licenseno=jProviderBD.getLicenseNumber(userid);
				ArrayList userinfolist=new ArrayList();
				ArrayList spdetails=new ArrayList();
				userinfolist = jProviderBD.getSpAcntDetails(userid,licenseno);
				accountDTO.setLicencenumber((String)userinfolist.get(0));
				accountDTO.setAccountantname((String)userinfolist.get(1));
				// for 1 step back balance
				String crdtamt=jProviderBD.getCreditAmt(licenseno);
				String debitamt=jProviderBD.getDebitAmt(licenseno);
				String curbal=Double.toString(Double.parseDouble(crdtamt)-Double.parseDouble(debitamt));
				accountDTO.setAccountbalance(curbal);
				accountDTO1=accountDTO;
				ArrayList list=new ArrayList();
				list.add(accountDTO);
				request.setAttribute("myaccount", list);
				String paymentFlag="";
				if(providerForm.getSr_no()!=null)
				paymentFlag=jProviderBD.getPaymentFlag(providerForm.getSr_no());
				String paymentStatus=(String)request.getParameter("paymentStatus");
					if(!paymentFlag.equalsIgnoreCase("P") && !paymentFlag.equalsIgnoreCase("C")){
						if(paymentFlag.equals(null)||paymentFlag.equalsIgnoreCase("")){
						//transaction is not initiated
						forwardpage = "spaccountbalance";
						}
						else if(paymentFlag.equalsIgnoreCase("I")){
						//if transaction is already initiated and a system failure occurs
						forwardpage = "spaccountbalance";
						}
					}
					else if(paymentFlag.equalsIgnoreCase("p")){
					//if payment was successful but payment success screen didnot appear-
					//passing control back to a screen in sp module
						if(paymentStatus.equalsIgnoreCase("P")){
						logger.debug("AFTER PAYMENTS FLAG----------");
						forwardpage=executeSPUpdatedCreditLimit(mapping, 
								providerForm, request, response, session);
							boolean flag=jProviderBD.getUpdatedPaymentFlag(accountDTO.getSrNo());
							if(flag){
							forwardpage="spaccountbalance";
							}
							else{	
							forwardpage="spaccountbalanceupdated";
							}
						}
						else{
							userinfolist = jProviderBD.getSpAcntDetails(userid,licenseno);
							accountDTO.setLicencenumber((String)userinfolist.get(0));
							accountDTO.setAccountantname((String)userinfolist.get(1));
							//accountDTO1=accountDTO;
							String actbal=accountDTO.getAccountbalance();
							list.add(accountDTO);
							request.setAttribute("myaccount", list);
							forwardpage = "spaccountbalanceupdated";
							}
					}
					else if(paymentFlag.equalsIgnoreCase("C")){
						//for empty textbox after returning from successful payment
						accountDTO.setPayamount("");
					forwardpage="spaccountbalance";}
					else{
					forwardpage="failure";
					}
			} catch (Exception e) {
				logger.info("Exception in Action" + e);
			}
			return forwardpage;
		}*/
		
		//end of code
		//updated amount screen -failure chk
	/*	private String executeSPUpdatedCreditLimit(ActionMapping mapping,
				ServiceProviderAccountForm providerForm,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) {
			String forwardpage = null;
			try {
				ServiceProviderAccountDTO accountDTO=providerForm.getAccountDTO();
				String userid = (String) session.getAttribute("UserId");
				jProviderBD = new JudicialServiceProviderAccountBD();
				String licenseno=jProviderBD.getLicenseNumber(userid);
				ArrayList userinfolist=new ArrayList();
				ArrayList spdetails=new ArrayList();
				userinfolist = jProviderBD.getSpAcntDetails(userid,licenseno);
				accountDTO.setLicencenumber((String)userinfolist.get(0));
				accountDTO.setAccountantname((String)userinfolist.get(1));
				accountDTO.setAccountbalance((String)userinfolist.get(2));
				accountDTO1=accountDTO;
				ArrayList list=new ArrayList();
				list.add(accountDTO);
				request.setAttribute("myaccount", list);
				forwardpage = "spaccountbalanceupdated";
			} catch (Exception e) {
				logger.info("Exception in Action" + e);
			}
			return forwardpage;
		}*/
		
		//changes on 21 dec for other services 
	/*	private synchronized String executeSPCreditLimitOthers(ActionMapping mapping,
				ServiceProviderAccountForm providerForm,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) {
			String forwardpage = null;
			try {
				//Changes by preeti 
				String sessionLicenseNo="";
				sessionLicenseNo=(String) session.getAttribute("licNo");
				logger.debug("SP Licenese No of session"+sessionLicenseNo);
				//
				logger.debug("INSIDE CREDIT LIMIT FUNCTIONALITY");
				accountDTO=providerForm.getAccountDTO();
				String userid = (String) session.getAttribute("UserId");
				providerBD = new ServiceProviderAccountBD();
				String licenseno=providerBD.getLicenseNumber(userid);
				
				//changes on 21 dec 
				if(sessionLicenseNo.equalsIgnoreCase(licenseno)){
				
				ArrayList userinfolist=new ArrayList();
				ArrayList spdetails=new ArrayList();
				userinfolist = providerBD.getSpAcntDetails(userid,sessionLicenseNo);
				accountDTO.setLicencenumber((String)userinfolist.get(0));
				accountDTO.setAccountantname((String)userinfolist.get(1));
				accountDTO.setAccountbalance((String)userinfolist.get(2));
				accountDTO.setPayamount("");
				ArrayList list=new ArrayList();
				list.add(accountDTO);
				request.setAttribute("myaccount", list);
				forwardpage="spaccountbalance";
				
				}
				
				else{
					forwardpage = "SPCreditLimitFail";
					logger.debug("forwared page in case of sp mismatch"+forwardpage);
				}
			
			} catch (Exception e) {
				logger.info("Exception in Action" + e);
			}
			return forwardpage;
		}*/
		
		//changes on 21 dec 2015
		private synchronized String executeSPCreditLimitEstamp(ActionMapping mapping,
				ServiceProviderAccountForm providerForm,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) {
			String forwardpage = null;
			try {
				//Changes by preeti 
				String sessionLicenseNo="";
				sessionLicenseNo=(String) session.getAttribute("licNo");
				logger.debug("SP Licenese No of session"+sessionLicenseNo);
				//
				logger.debug("INSIDE CREDIT LIMIT ESTAMP FUNCTIONALITY");
				accountDTO=providerForm.getAccountDTO();
				String userid = (String) session.getAttribute("UserId");
				jProviderBD = new JudicialServiceProviderAccountBD();
				String licenseno=jProviderBD.getLicenseNumber(userid);
				
				if(sessionLicenseNo.equalsIgnoreCase(licenseno)){
				
				ArrayList userinfolist=new ArrayList();
				ArrayList spdetails=new ArrayList();
				userinfolist = jProviderBD.getSpAcntDetailsEstamp(userid,sessionLicenseNo);
				accountDTO.setLicencenumber((String)userinfolist.get(0));
				accountDTO.setAccountantname((String)userinfolist.get(1));
				accountDTO.setAccountbalance((String)userinfolist.get(2));
				accountDTO.setPayamount("");
				ArrayList list=new ArrayList();
				list.add(accountDTO);
				request.setAttribute("myaccount", list);
				forwardpage="spaccountbalance";
				
			}
				else{
					forwardpage = "SPCreditLimitFail";
					logger.debug("forwared page in case of sp mismatch"+forwardpage);
				}
			}
			
				catch (Exception e) {
				logger.info("Exception in Action" + e);
			}
			return forwardpage;
		}
		
		
		private synchronized String executeSpPaymentSuccessEstamp(ActionMapping mapping,
				ServiceProviderAccountForm providerForm,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) {
			try{
				String userid = (String) session.getAttribute("UserId");
				accountDTO=providerForm.getAccountDTO();
				jProviderBD = new JudicialServiceProviderAccountBD();
				String licenseno=jProviderBD.getLicenseNumber(userid);
				ArrayList userinfolist=new ArrayList();
				ArrayList spdetails=new ArrayList();
				userinfolist = jProviderBD.getSpAcntDetailsEstamp(userid,licenseno);
				accountDTO.setLicencenumber((String)userinfolist.get(0));
				accountDTO.setAccountantname((String)userinfolist.get(1));
				accountDTO.setAccountbalance((String)userinfolist.get(2));
				//accountDTO1=accountDTO;
				ArrayList list=new ArrayList();
				list.add(accountDTO);
				request.setAttribute("myaccount", list);
				forwardpage="spaccountbalance";
				
			} catch (Exception e) {
				logger.info("Exception in Action" + e);
			}
			return forwardpage;
		}
		
		/*private synchronized String executeSpPaymentSuccessOthers(ActionMapping mapping,
				ServiceProviderAccountForm providerForm,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) {
			try{
				String userid = (String) session.getAttribute("UserId");
				accountDTO=providerForm.getAccountDTO();
				providerBD = new ServiceProviderAccountBD();
				String licenseno=providerBD.getLicenseNumber(userid);
				ArrayList userinfolist=new ArrayList();
				ArrayList spdetails=new ArrayList();
				userinfolist = providerBD.getSpAcntDetails(userid,licenseno);
				accountDTO.setLicencenumber((String)userinfolist.get(0));
				accountDTO.setAccountantname((String)userinfolist.get(1));
				accountDTO.setAccountbalance((String)userinfolist.get(2));
				accountDTO1=accountDTO;
				ArrayList list=new ArrayList();
				list.add(accountDTO);
				request.setAttribute("myaccount", list);
				forwardpage="spaccountbalance";
				
			} catch (Exception e) {
				logger.info("Exception in Action" + e);
			}
			return forwardpage;
		}*/
		
		
}
