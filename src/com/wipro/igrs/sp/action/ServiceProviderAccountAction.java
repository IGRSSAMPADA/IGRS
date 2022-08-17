/**
 * ServiceProviderAccountAction.java
 */

package com.wipro.igrs.sp.action;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/*import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;*/
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.ServiceProviderConstants;
import com.wipro.igrs.payments.bd.PaymentBD;
import com.wipro.igrs.payments.form.PaymentForm;
import com.wipro.igrs.sp.bd.ServiceProviderAccountBD;
import com.wipro.igrs.sp.dto.ServiceProviderAccountDTO;
import com.wipro.igrs.sp.form.ServiceProviderAccountForm;
import com.wipro.igrs.sp.rule.ServiceProviderAccountRule;
import com.wipro.igrs.payments.dto.PaymentDTO;
/**
 * @author root
 * 
 */
public class ServiceProviderAccountAction extends BaseAction{

	ServiceProviderAccountBD providerBD = null;
	ServiceProviderAccountDTO accountDTO;
	ServiceProviderAccountDTO accountDTO1=new ServiceProviderAccountDTO();
	ServiceProviderAccountRule providerRule = null;
	String TxnFlag = ""; 
	//String sr_no="";
	String licenseno="";
	ArrayList errorlist = null;
	String forwardpage = "";
	
	private Logger logger = (Logger) Logger.getLogger(ServiceProviderAccountAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {
	
		String name = request.getParameter("name");
		ServiceProviderAccountForm providerAccountForm = (ServiceProviderAccountForm) form;
		
		//added by shruti-on click of menu item credit limit
		if (name.equalsIgnoreCase("creditlimit"))
		{
			try{
				forwardpage= executeSPCreditLimit(mapping, providerAccountForm,
						request, response, session);
			}
			catch(Exception e){
				logger.info("Exception in execute() Service Provider Action for credit limit");
			}
		}
		
		//added by shruti for ledger
		if (name.equalsIgnoreCase("ledger"))
		{
			try{
				forwardpage =executeSPCreditLimitStatement(mapping,
						providerAccountForm, request, response, session);
				/*forwardpage = executeSPAccountStatement(mapping,
						providerAccountForm, request, response, session);*/
			}
			catch(Exception e){
				logger.info("Exception in execute() Service Provider Action for credit limit");
			}
		}
		//end of code
	
		//payment success in case of availing credit limit
		if(name.equals("paymentsuccess")){
			try{	
				logger.info("IN PAYMENT SUCCESS----");

				/*forwardpage = executeSPCreditLimitBalance(mapping,
						providerAccountForm, request, response, session);*/
				forwardpage =executeSpPaymentSuccess(mapping,
						providerAccountForm, request, response, session);	
			}
			catch(Exception e)
			{
				logger.info("Error in Action Class");
			}
		}
		
		if (name.equalsIgnoreCase("payments")) {
			try {
				logger.info("In Payments");
				forwardpage = executeSPAccountPayment(mapping,
						providerAccountForm, request, response, session);
			} catch (Exception e) {
				logger.info("Exception in execute() ServiceProviderAction"
						+ e);
			}
		}
		if (name.equalsIgnoreCase("splaccstmtview")) {
			try {
				forwardpage=executeSPAccLdgr(mapping,
						form, request, response, session);
				/*forwardpage = executeSPAccstmtview(mapping,
						form, request, response, session);*/
			} catch (Exception e) {
				logger.info("Exception in execute() ServiceProviderAction"
						+ e);
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
	private String executeServiceProviderAccountsummary(ActionMapping mapping,
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
	private synchronized String executeSPAccountPayment(ActionMapping mapping,
			ServiceProviderAccountForm providerForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
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
			sr_no=providerBD.getCrdtLimitSerialNumber();
			accountDTO.setSrNo(sr_no);
			providerBD.spAcntBalUpdt(accountDTO, userid);
			request.setAttribute("TxnFlag", "Credit");
			request.setAttribute("parentModName","Service Provider");
			request.setAttribute("parentFunName","Avail Credit Limit");
			request.setAttribute("parentFunId","FUN_200");
			request.setAttribute("parentAmount",parentAmount);
			request.setAttribute("parentKey",sr_no);
			request.setAttribute("parentTable", "IGRS_CREDIT_LIMIT_TXN_LOG");
			request.setAttribute("parentColumnName","SR_NO");
			request.setAttribute("forwardPath", forwardPath);
			forwardpage = "payment";
		} catch (Exception e) {
			logger.info("Exception in execute() ServiceProviderAction"
					+ e);
		}
		return forwardpage;
	}

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
	private String executeSPAccountStatement(ActionMapping mapping,
			ServiceProviderAccountForm providerForm,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String forwardpage = null;
		try {
			logger.info("spaccountstatement");
			ArrayList userinfolist = null;
			String userid = (String) session.getAttribute("UserId");
			providerBD = new ServiceProviderAccountBD();
				logger.info("spaccountstatement");
				
				userinfolist = providerBD.getServiceProviderInfo(userid);
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
	}

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
			ArrayList userinfolist = null;
			String userid = (String) session.getAttribute("UserId");
				forwardpage ="spcreditlimitstatement";
		} catch (Exception e) {
			logger.info("Exception in execute() ServiceProviderAction"
					+ e);
		}
		logger.info("forwardpage");
		return forwardpage;
	}

	//added by shruti
	private String executeSPAccLdgr(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String forwardpage = null;
		try {
			ServiceProviderAccountForm providerAccountForm = (ServiceProviderAccountForm) form;
			accountDTO=providerAccountForm.getAccountDTO();				
			
			ArrayList userinfolist = null;
			ArrayList accountstmt = null;
			String userid = (String) session.getAttribute("UserId");
			providerBD = new ServiceProviderAccountBD();
			String licenseno=providerBD.getLicenseNumber(userid);
			providerBD = new ServiceProviderAccountBD();
			userinfolist=providerBD.getSpAcntDetails(userid, licenseno);
			accountDTO.setLicencenumber((String)userinfolist.get(0));
			accountDTO.setAccountantname((String)userinfolist.get(1));

			//accountDTO.setOpeningBal(providerBD.getOpeningBal(licenseno));
			
			//providerAccountForm.setAccountDTO(accountDTO);
			//logger.info("OPENING BALANCE INFO IN ACTION CLASS----"+providerAccountForm.getAccountDTO().getOpeningBal());
			ArrayList userinfolist1=new ArrayList();
			userinfolist1.add(accountDTO);
			request.setAttribute("myaccount", userinfolist1);
			
				accountstmt = providerBD.getServiceProviderStmt(licenseno,
						accountDTO);
				logger.info("SSS_____"+accountstmt.size());
				//request.setAttribute("myaccount", userinfolist);
				request.setAttribute("accountstmt", accountstmt);
				request.setAttribute("providerDTO", accountDTO);
				providerAccountForm.setOpeningBal(providerBD.getOpeningBal(licenseno));
				logger.info("FORM-----"+providerAccountForm.getOpeningBal());
				forwardpage = "accountstmtview1";
				/*forwardpage = "accountstmtview";	*/	
			
				}catch (Exception e) {
					logger.info("Exception in execute() ServiceProviderAction"
							+ e);
		}
		logger.info("forwardpage :"+forwardpage);
		return forwardpage;
	}
//end of code

		
		//added by shruti
		private String executeSPAvailCreditLimit(ActionMapping mapping,
				ServiceProviderAccountForm providerForm,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) {
			String forwardpage = null;
			try {
				accountDTO=providerForm.getAccountDTO();
				String userid = (String) session.getAttribute("UserId");
				providerBD = new ServiceProviderAccountBD();
				String licenseno=providerBD.getLicenseNumber(userid);
				ArrayList userinfolist=new ArrayList();
				ArrayList spdetails=new ArrayList();
				userinfolist = providerBD.getSpAcntDetails(userid,licenseno);
				accountDTO.setLicencenumber((String)userinfolist.get(0));
				accountDTO.setAccountantname((String)userinfolist.get(1));
				// for 1 step back balance
				String crdtamt=providerBD.getCreditAmt(licenseno);
				String debitamt=providerBD.getDebitAmt(licenseno);
				String curbal=Double.toString(Double.parseDouble(crdtamt)-Double.parseDouble(debitamt));
				accountDTO.setAccountbalance(curbal);
				accountDTO1=accountDTO;
				ArrayList list=new ArrayList();
				list.add(accountDTO);
				request.setAttribute("myaccount", list);
				String paymentFlag="";
				if(providerForm.getSr_no()!=null)
				paymentFlag=providerBD.getPaymentFlag(providerForm.getSr_no());
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
							boolean flag=providerBD.getUpdatedPaymentFlag(accountDTO.getSrNo());
							if(flag){
							forwardpage="spaccountbalance";
							}
							else{	
							forwardpage="spaccountbalanceupdated";
							}
						}
						else{
							userinfolist = providerBD.getSpAcntDetails(userid,licenseno);
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
		}
		
		//end of code
		//updated amount screen -failure chk
		private String executeSPUpdatedCreditLimit(ActionMapping mapping,
				ServiceProviderAccountForm providerForm,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) {
			String forwardpage = null;
			try {
				ServiceProviderAccountDTO accountDTO=providerForm.getAccountDTO();
				String userid = (String) session.getAttribute("UserId");
				providerBD = new ServiceProviderAccountBD();
				String licenseno=providerBD.getLicenseNumber(userid);
				ArrayList userinfolist=new ArrayList();
				ArrayList spdetails=new ArrayList();
				userinfolist = providerBD.getSpAcntDetails(userid,licenseno);
				accountDTO.setLicencenumber((String)userinfolist.get(0));
				accountDTO.setAccountantname((String)userinfolist.get(1));
				accountDTO.setAccountbalance((String)userinfolist.get(2));
				/*String crdtamt=providerBD.getCreditAmt(licenseno);
				logger.info("CRDIT AMT-------"+crdtamt);
				String debitamt=providerBD.getDebitAmt(licenseno);
				logger.info("DEBIT AMT-------"+debitamt);
				String curbal=Double.toString(Double.parseDouble(crdtamt)-Double.parseDouble(debitamt));
				logger.info("CURRENT BAL------"+curbal);
				accountDTO.setAccountbalance(curbal);*/

				accountDTO1=accountDTO;
				ArrayList list=new ArrayList();
				list.add(accountDTO);
				request.setAttribute("myaccount", list);
				forwardpage = "spaccountbalanceupdated";
			} catch (Exception e) {
				logger.info("Exception in Action" + e);
			}
			return forwardpage;
		}
		
		private synchronized String executeSPCreditLimit(ActionMapping mapping,
				ServiceProviderAccountForm providerForm,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) {
			String forwardpage = null;
			try {
				
				logger.debug("INSIDE CREDIT LIMIT FUNCTIONALITY");
				accountDTO=providerForm.getAccountDTO();
				String userid = (String) session.getAttribute("UserId");
				providerBD = new ServiceProviderAccountBD();
				String licenseno=providerBD.getLicenseNumber(userid);
				ArrayList userinfolist=new ArrayList();
				ArrayList spdetails=new ArrayList();
				userinfolist = providerBD.getSpAcntDetails(userid,licenseno);
				accountDTO.setLicencenumber((String)userinfolist.get(0));
				accountDTO.setAccountantname((String)userinfolist.get(1));
				accountDTO.setAccountbalance((String)userinfolist.get(2));
				ArrayList list=new ArrayList();
				list.add(accountDTO);
				request.setAttribute("myaccount", list);
				//check the status of payment flag
				/*String paymentFlag="";
				if(providerForm.getSr_no()!=null)
				{paymentFlag=providerBD.getPaymentFlag(providerForm.getSr_no());}
				
				logger.info("PAYMENT FLAG FOR SR_NO "+providerForm.getSr_no()+"IS -----"+paymentFlag);
				String paymentStatus=(String)request.getParameter("paymentStatus");*/
				forwardpage="spaccountbalance";
				
				//commented by shruti
				
					/*if(!paymentFlag.equalsIgnoreCase("P") && !paymentFlag.equalsIgnoreCase("C")){
						if(paymentFlag.equals(null)||paymentFlag.equalsIgnoreCase("")){
						//transaction is not initiated
						forwardpage = "spaccountbalance";
						}
						else if(paymentFlag.equalsIgnoreCase("I")){
						//if transaction is already initiated and a system failure occurs
						forwardpage = "spaccountbalance";
						}
					}*/
				//end of code by shruti
				
					/*else if(paymentFlag.equalsIgnoreCase("p")){
					//if payment was successful but payment success screen didnot appear-
					//passing control back to a screen in sp module
						if(paymentStatus.equalsIgnoreCase("P")){
						logger.debug("AFTER PAYMENTS FLAG----------");
						forwardpage=executeSPUpdatedCreditLimit(mapping, 
								providerForm, request, response, session);
							//boolean flag=providerBD.getUpdatedPaymentFlag(accountDTO.getSrNo());
						boolean flag=false;
						if(providerForm.getSr_no()!=null)
						flag=providerBD.getUpdatedPaymentFlag(providerForm.getSr_no());
							if(flag){
							forwardpage="spaccountbalance";
							}
							else{	
							forwardpage="spaccountbalanceupdated";
							}
						}
						else{
							userinfolist = providerBD.getSpAcntDetails(userid,licenseno);
							accountDTO.setLicencenumber((String)userinfolist.get(0));
							accountDTO.setAccountantname((String)userinfolist.get(1));
							accountDTO.setAccountbalance((String)userinfolist.get(2));
							//accountDTO1=accountDTO;
							String actbal=accountDTO.getAccountbalance();
							list.add(accountDTO);
							request.setAttribute("myaccount", list);
							forwardpage = "spaccountbalanceupdated";
							}
					}
					else if(paymentFlag.equalsIgnoreCase("C")){
					forwardpage="spaccountbalance";}
					else{
					forwardpage="failure";
					}*/
			} catch (Exception e) {
				logger.info("Exception in Action" + e);
			}
			return forwardpage;
		}
		
		
		private synchronized String executeSpPaymentSuccess(ActionMapping mapping,
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
				accountDTO.setPayamount("");
				forwardpage="spaccountbalance";
				
			} catch (Exception e) {
				logger.info("Exception in Action" + e);
			}
			return forwardpage;
		}
		
		
}
