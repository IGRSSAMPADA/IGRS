package com.wipro.igrs.payment.action;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

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
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.payment.bd.CashLinkedBD;
import com.wipro.igrs.payment.bd.EODChallanBD;
import com.wipro.igrs.payment.constant.CommonConstant;
import com.wipro.igrs.documentsearch.bd.DocumentSearchBD;
import com.wipro.igrs.documentsearch.dto.DocumentSearchDTO;

import com.wipro.igrs.payment.dto.CashCounterLinkedDTO;
import com.wipro.igrs.payment.dto.PhysicalChallanDTO;

import com.wipro.igrs.payment.form.CashCounterLinkedForm;
import com.wipro.igrs.payments.dto.ChallanDTO;
import com.wipro.igrs.payments.form.PaymentForm;
import com.wipro.igrs.reginit.constant.RegInitConstant;

/**
 * ===========================================================================
 * File           :   CashPaymentAction.java
 * Description    :   Represents the Cash Payment Action Class
 * Author         :   vengamamba P
 * Created Date   :    Feb 06, 2008

 * ===========================================================================
 */

public class CashLinkedAction extends BaseAction {
    /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	 /*private int count = 0;
	 private HashMap map = null;
	 private HashMap h1 = new HashMap();
	 ArrayList finList = new ArrayList();
	 boolean bol = true;
	 String flag = null;*/
	 private static final Logger logger = Logger
	.getLogger(CashLinkedAction.class);
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws Exception {    
    	
    	int count = 0;
    	
   	  HashMap map = null;
   	  HashMap h1 = new HashMap();
   
   	 boolean bol = true;
   	 String flag = null;
    	String FORWARD_JSP="Pay";
    	ArrayList list2 = new ArrayList();
    	String userId = (String)session.getAttribute("UserId");
    	String officeId = (String)session.getAttribute("loggedToOffice");
    //	HttpSession session=request.getSession(false);
    	ActionMessages messages = new ActionMessages();
    	
    	String languageLocale=RegInitConstant.LANGUAGE_HINDI;
		if(session.getAttribute("languageLocale")!=null){
			languageLocale=(String)session.getAttribute("languageLocale");
		}
		
		/*if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			session.setAttribute("modName",RegInitConstant.MODNAME_ENGLISH);	
		}else{
			session.setAttribute("modName",RegInitConstant.MODNAME_HINDI);
		}*/
    	
    	if (form != null) {
    		 ArrayList finList = new ArrayList();
    		CashCounterLinkedForm cashform = (CashCounterLinkedForm)form;
    		PaymentForm eForm = new PaymentForm() ;
    		//akansha
    		//String actionNextNew = cashform.getActionNext();
    		
    		if ("Cancelbutton".equalsIgnoreCase(cashform.getActionValue())){
				
				FORWARD_JSP="Cancel";
				cashform.setActionValue("");
				return mapping.findForward(FORWARD_JSP);	
			}
			if ("submitCashConfirmation".equalsIgnoreCase(cashform.getActionNext())){
			String transactionId= 	cashform.getTransactionId();
			String serviceFeeNew =	cashform.getServFee();
			
			
			//String balAmnt =(String) session.getAttribute("parentAmount");
			
			double d = Math.ceil(Double.parseDouble(serviceFeeNew));
		    int entAmtnew = (int) d;
		    
		  String serviceFee=  BigDecimal.valueOf(d).toPlainString();
			
		 // cashform.setServFee(serviceFee);
		  
		  
			request.setAttribute("transactionId", transactionId);
			request.setAttribute("serviceFee", serviceFee);
			//eForm.setTransId(transactionId);
			//eForm.setReceiptID(transactionId);
			//eForm.setEntrAmt(serviceFee);
				FORWARD_JSP="cash";
				cashform.setActionNext("");
				//.removeAttribute("transactionId");
				//session.removeAttribute("transactionId");
				return mapping.findForward(FORWARD_JSP);
			}
    		
    		count=cashform.getCashdto().getAddMoreCounter();
    		request.setAttribute("cashform", cashform);
    		String par = request.getParameter("Prnt");
    		if("Y".equalsIgnoreCase(par)){
    			FORWARD_JSP="printPop";	
    		}
    		
    		if(cashform.getBankAddr()!=""){
    			String bankAddress = cashform.getBankAddr();
    			cashform.setBankAddr(bankAddress);
    		}
    		if(cashform.getBankName()!=""){
    			String bankName = cashform.getBankName();
    			cashform.setBankName(bankName);
    		}
    		//akansha
    		if (request.getParameter("flag") != null) {
    			if (request.getParameter("flag").equalsIgnoreCase(
    					"cashCreate")||(request.getParameter("flag").equalsIgnoreCase(
    					"Back"))) {
    				
    				
    				CashLinkedBD cashBD = new CashLinkedBD();	
    				
    				
    				//akansha bypass method
					if(session.getAttribute("reg_id")!=null){
						
						String regTxnId = (String) session.getAttribute("reg_id");
						cashform.setCheckerRegInitId(regTxnId);
					}
					
					
					if(session.getAttribute("parentFunId")!=null ){
						String functionId = (String) session.getAttribute("parentFunId");
					cashform.setListService(functionId)	;
					
					}
					//Start-----done by akansha on 5thfeb for cash automation issue
					if(session.getAttribute("parentAmountNew")!=null){
						
						String payableAmt = cashBD.cashPayableAmt(cashform);
						String sessionAmt = (String)session.getAttribute("parentAmountNew");	
    					cashform.setServFee(payableAmt);
    					session.removeAttribute("parentAmountNew");
    					
    					if(cashform.getServFee().equals("0")||cashform.getServFee().equals("0.0")){
    						FORWARD_JSP="invalidReceipt";
    						return mapping.findForward(FORWARD_JSP);
    					}
    					
    				}	
					boolean	cashStatusConsumed = false;
					cashStatusConsumed = cashBD.cashExistConsumed(cashform,userId);
					if (cashStatusConsumed){
						
						FORWARD_JSP="Failurepage";
						return mapping.findForward(FORWARD_JSP);
						
					}
					//end
					boolean	cashStatus = false;
				
					
				
					cashStatus = cashBD.cashExist(cashform,userId);
					
					if(cashStatus){
						String cashReceipt = cashBD.getCashReceipt(cashform);
						
						cashform.setExistingCash(cashReceipt);
						
						String receipt = cashform.getExistingCash();
						String combineId = cashBD.getCombineId(receipt);
						
					ArrayList	existList = cashBD.getTransPrintList(combineId,languageLocale);
					PhysicalChallanDTO chdto=(PhysicalChallanDTO)existList.get(0);
						cashform.setDisplayList(existList);
						cashform.setTransactionId(chdto.getTxnid());
						cashform.setServFee(chdto.getTotalAmt());
					}
    				
					else{
						cashform.setExistingCash(null);
					}
    				if(cashform.getExistingCash()==null ||cashform.getExistingCash().equalsIgnoreCase("")){
    					
    				
    				
    				FORWARD_JSP="cashCreate";
    			  
    				//session.removeAttribute("transactionId");
    				
   				//session.removeAttribute("serviceFee");
    				if(eForm.getParentAmount()!=null){
    			String abc =	eForm.getParentAmount();
    				}
    				if(session.getAttribute("parentFunId")!=null ){
    					
    					
    					String actionValue =cashform.getActionValue();
    	    			String functionId = (String) session.getAttribute("parentFunId");
    	    			
    	    			String  service =cashBD.getService(languageLocale,functionId);//geting functionid list//HINDI
    	    			
    	    			
    	    		      cashform.setFunName(service);
    	    			
    	    			System.out.println("shgadhgashdg  "+cashform.getFunName());
    	    			

    					
    					
    				cashform.setLanguage(languageLocale);
    				//CashLinkedBD cashBD = new CashLinkedBD();
    				EODChallanBD bd  = new EODChallanBD();
    				PhysicalChallanDTO chdtoobj=new PhysicalChallanDTO();
    				ArrayList  photoidList = cashBD.getPhotoIDList(languageLocale);//getting list photoids//HINDI
    				ArrayList  serviceList =cashBD.getServiceList(languageLocale);//geting functionid list//HINDI
    				ArrayList bankidList=cashBD.getBankidList();
    				CashCounterLinkedDTO dto= new CashCounterLinkedDTO();
    				//String oid	      = cashBD.getOfficeid(userId);
    				String ofName    = bd.getOfficeName(officeId,languageLocale);// HINDI
    				String did	     = cashBD.getDistrictId(officeId);
    				String dname	 = cashBD.getDistrictName(did,languageLocale); //HINDI
    				String empnm     = cashBD.getempName(userId);
    				cashform.setOffName(ofName);
    				cashform.setOffId(officeId);
    				cashform.setDistId(did);
    				cashform.setDistName(dname);
    				cashform.setEmpName(empnm);
    				//setting list of values to photoids,services.
    				cashform.getCashdto().setPhotoIDList(photoidList);
    				cashform.getCashdto().setFuncIDList(serviceList);
    				cashform.getCashdto().setBankIDList(bankidList);
    				//cashform.setCashdto(dto);
    				cashform.setActionValue(null);
    				cashform.setCashForm(null);
    				cashform.setListID(functionId);
    				cashform.setOldNewReceipt("Y");
					String fees= null;
					//feeDsdto=docbd.getOthersFeeDuty(servId, null, userId);
					//feesDsdto1=cashbd.getOthersFeeDuty(servId, null, userId);//CHANGE_INTEGRATE WITH SERVICE FEE MATRIX
					dto.setListService(functionId);
					String[] values=cashBD.getRevHeadsFee(functionId);
					String fees1 = "0";
					cashform.setFirstName("");
					cashform.setMiddleName("");
					cashform.setLastName("");
					cashform.setAge("");
					cashform.setFatherName("");
					cashform.setListID("");
					cashform.setIdNo("");
					cashform.setRemarks("");
					cashform.setServiceMap(new HashMap());
					cashform.setServiceMapDetails(new HashMap());
					if(values!=null){
						
						
						if(values[6].trim().equalsIgnoreCase("Y")){
							
							if(values[3]!=null && !values[3].trim().equalsIgnoreCase("") 
									&& !values[3].trim().equalsIgnoreCase("null")){
							fees = values[3].trim();
							if(fees.indexOf(".")!=-1){
							fees1 = fees.substring(0,fees.indexOf("."));
							int cielFee=Integer.parseInt(fees1);
							cielFee++;
							fees1=Integer.toString(cielFee);
							}else{
								fees1=fees;
							}
							}else{
								fees = "0";
							}
							//String fees1 = fees.substring(0,fees.indexOf("."));
							
								
                          String balAmnt =cashform.getServFee();
								
								double d = Math.ceil(Double.parseDouble(balAmnt));
		        			    int entAmtnew = (int) d;
		        			  //Start-----done by akansha on 5thfeb for cash automation issue
		        			  //  request.removeAttribute("BalanceparentAmount");
		        			    //end
		        			    
		        			    //BigDecimal.valueOf(d).toPlainString();
		        			 // String amount=  Integer.toString(entAmtnew);
								
								cashform.setServFee(BigDecimal.valueOf(d).toPlainString());
							
							
							/*if(session.getAttribute("parentAmount")!=null){
								
								String balAmnt =(String) session.getAttribute("parentAmount");
								
								double d = Math.ceil(Double.parseDouble(balAmnt));
		        			    int entAmtnew = (int) d;
		        			    
		        			  String amount=  Integer.toString(entAmtnew);
								
								cashform.setServFee(amount);
							}*/
							//cashform.setServFee(fees1);
							cashform.setDrpFee(fees1);
							
						}
						else{//employe will enter
							if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
							cashform.setDrpFee(CommonConstant.CAPTURE_FEE_MSG_ENGLISH +values[7].trim());
							}else{
								cashform.setDrpFee(CommonConstant.CAPTURE_FEE_MSG_HINDI+values[8].trim());
							}
							
								
							 String balAmnt =cashform.getServFee();
										
										double d = Math.ceil(Double.parseDouble(balAmnt));
				        			    int entAmtnew = (int) d;
				        			  //Start-----done by akansha on 5thfeb for cash automation issue
				        			   // request.removeAttribute("BalanceparentAmount");
				        			    //end
				        			  String amount=  Integer.toString(entAmtnew);
										
										cashform.setServFee(BigDecimal.valueOf(d).toPlainString());
									
							//cashform.setServFee("");
                            /*  if(session.getAttribute("parentAmount")!=null){
								
                            	  String balAmnt =(String) session.getAttribute("parentAmount");
  								
  								double d = Math.ceil(Double.parseDouble(balAmnt));
  		        			    int entAmtnew = (int) d;
  		        			    
  		        			  String amount=  Integer.toString(entAmtnew);
  								
  								cashform.setServFee(amount);
							}*/
						}
						
						if(values[0]!=null){
							if(!values[0].trim().equalsIgnoreCase("null") && !values[0].trim().equalsIgnoreCase("")){
							cashform.setRevMjrHd(values[0].trim());
							}
							else{
								cashform.setRevMjrHd("-");
							}
						}else{
							cashform.setRevMjrHd("-");
						}
						if(values[1]!=null){
							
							if(!values[1].trim().equalsIgnoreCase("null") && !values[1].trim().equalsIgnoreCase("")){
								cashform.setRevSubMjrHd(values[1].trim());
								}
								else{
									cashform.setRevSubMjrHd("-");
								}
							
							
						}else{
							cashform.setRevSubMjrHd("-");
						} 
						if(values[2]!=null){
							
							if(!values[2].trim().equalsIgnoreCase("null") && !values[2].trim().equalsIgnoreCase("")){
								cashform.setRevMnrHd(values[2].trim());
								}
								else{
									cashform.setRevMnrHd("-");
								}
							
						}else{
							cashform.setRevMnrHd("-");
						} 
						
						
						
						
					}else{
						cashform.setServFee("0");
						cashform.setDrpFee("0");
						cashform.setRevMjrHd("-");   
						cashform.setRevSubMjrHd("-");
						cashform.setRevMnrHd("-");
					}
					logger.debug("RevMjrHd is............................................."+cashform.getRevMjrHd());
					logger.debug("RevSubMjrHd is............................................."+cashform.getRevSubMjrHd());
					logger.debug("RevMnrHd is............................................."+cashform.getRevMnrHd());
					
					//fees = feeDsdto.getTotalFee();
					//fees = feesDsdto1.getTotalFees1();
					//logger.debug("the fetched fees is............................................."+fees);
					//String fees1 = fees.substring(0,fees.indexOf("."));
					//logger.debug("the substring fees is............................................."+fees1);
					//cashform.setServFee(fees1);
					//cashform.setDrpFee(fees1);
					//cashform.setRevMjrHd("0030");
					//cashform.setRevSubMjrHd("03");
					//cashform.setRevMnrHd("800");
					cashform.setCashAmt("");
					FORWARD_JSP="cashCreate";
				
	    			
	    		}
    				return mapping.findForward(FORWARD_JSP);
    			}
    				
    				else
    				{
    				
    					session.setAttribute("ExistingId", cashform.getTransactionId());
    					session.setAttribute("amt", cashform.getServFee());
    					FORWARD_JSP="FinalPayment";
    					return mapping.findForward(FORWARD_JSP);
    				}
    			}
    			
    			if(request.getParameter("flag").equalsIgnoreCase(
				"homePage")){
    	    		
    				FORWARD_JSP = "Cancel";
    	    		return mapping.findForward(FORWARD_JSP);
    	    	}
    		}
    		
    	
    		
    		cashform.setLanguage(languageLocale);
			CashLinkedBD cashBD = new CashLinkedBD();
			EODChallanBD bd  = new EODChallanBD();
			PhysicalChallanDTO chdtoobj=new PhysicalChallanDTO();
			ArrayList  photoidList = cashBD.getPhotoIDList(languageLocale);//getting list photoids//HINDI
			ArrayList  serviceList =cashBD.getServiceList(languageLocale);//geting functionid list//HINDI
			ArrayList bankidList=cashBD.getBankidList();
			CashCounterLinkedDTO dto= new CashCounterLinkedDTO();
			//String oid	      = cashBD.getOfficeid(userId);
			String ofName    = bd.getOfficeName(officeId,languageLocale);// HINDI
			String did	     = cashBD.getDistrictId(officeId);
			String dname	 = cashBD.getDistrictName(did,languageLocale); //HINDI
			String empnm     = cashBD.getempName(userId);
			cashform.setOffName(ofName);
			cashform.setOffId(officeId);
			cashform.setDistId(did);
			cashform.setDistName(dname);
			cashform.setEmpName(empnm);
			//setting list of values to photoids,services.
			cashform.getCashdto().setPhotoIDList(photoidList);
			cashform.getCashdto().setFuncIDList(serviceList);
			cashform.getCashdto().setBankIDList(bankidList);
			//cashform.setCashdto(dto);
			
			logger.debug("Action for Logger Msg Debug ");
			
			if(request.getParameter("axn")!=null && request.getParameter("axn").equalsIgnoreCase("create"))
			{System.out.println("in first call");
			CashCounterLinkedDTO dt2 = new CashCounterLinkedDTO();
			    cashform.setFirstName("");
				cashform.setListService("");
			    cashform.getCashdto().setServFee("");
			    cashform.getCashdto().setDeleteService("");
			    cashform.getCashdto().setFunName("");
			    cashform.getCashdto().setListService("");
			    cashform.getCashdto().setAddMoreCounter(0);
			    count=0;
				cashform.setListID("");
				cashform.setIdNo("");
				cashform.setAge("");
				cashform.setBankAddr("");
				cashform.setBankName("");
				cashform.setServiceMap(new HashMap());
				cashform.setServiceMapDetails(new HashMap());
				cashform.setServFee("");
				cashform.setDrpFee("");
				cashform.setFatherName("");
				cashform.setFirstName("");
				cashform.setMiddleName("");
				cashform.setLastName("");
				cashform.setCashAmt("");
				cashform.setRemarks("");
				cashform.setFunName("");
				cashform.setActionValue("");
				cashform.setIscalculated(0);
				cashform.setRevMjrHd("");
				cashform.setRevMnrHd("");
				cashform.setRevSubMjrHd("");
				cashform.setPosRefNo("");
				cashform.setOldNewReceipt("Y");
				flag = null;
				cashform.getCashdto().setAddMoreCounter(0);
		//		session.setAttribute("cashform",cashform);
				FORWARD_JSP="Pay";
			}
			else if (request.getParameter("axn")!=null && request.getParameter("axn").equalsIgnoreCase("view")){
				
				
				cashform.setTransId("");
				cashform.setChdate("");
				cashform.setMinDate("");
				FORWARD_JSP = "cashView";
				
				
			}
			else if ("cashForm".equalsIgnoreCase(cashform.getCashForm()))
			{
				System.out.println("value of the title is............"+cashform.getCashForm());
				String actionValue =cashform.getActionValue();
				String actionName =cashform.getActionName();
				String actionNext = cashform.getActionNext();
				String checkFlag = null;
				String getStatus = null;
				/* Start--If condition for the next button of cashForm
				 * @author Aakriti
				 * */
				if ("addMoreActionNext".equalsIgnoreCase(actionNext)){
					
					
					/*if(isTokenValid(request)){
						resetToken(request);*///csrf
					logger.debug("action next is...................."+actionNext);
					//String ofcid = cashBD.getOffice(userId);// commented by roopam because table being referred for retrieving offc id corresponding to user id is not valid any more.
					String ofcid = officeId;                  // added by roopam for removing above issue.  29th Oct 2013    
															  // officeId retrieved from session attribute
					String sum = cashBD.getSum(ofcid); 
					String limit = cashBD.getLimit(ofcid);
					//akansha
					//String amt = cashform.getCashAmt();
					String amt = cashform.getServFee();
					double dlimit = Double.parseDouble(limit);
					double dsum = Double.parseDouble(sum);
					double damt = Double.parseDouble(amt);
					double total = dsum+damt;
					
					if (total>dlimit){
						double ddiff = dlimit - dsum;
						String diff = Double.toString(ddiff);
						logger.debug("the difference is"+diff);
						NumberFormat obj1=new DecimalFormat("#0");
						cashform.setDiff(obj1.format(ddiff));
						//cashform.setDiff(Double.toString(ddiff));
						if (ddiff == 0.0){
							FORWARD_JSP ="PayZero";
						}else{
						FORWARD_JSP ="PayLimit";}
					}
					else{
					flag = "nextAction";
					actionValue = "addMoreAction";
					}
					//resetToken(request);
					//saveToken(request);
				/*}
					else{
					FORWARD_JSP = "Failurepage";
					mapping.findForward(FORWARD_JSP);
				}*///csrf
				}
				/*  end--If condition for the next button of cashForm
				 * @author Aakriti
				 * */
				
				if ("resetForm".equalsIgnoreCase(actionValue)){
					//CashCounterDTO dt2 = new CashCounterDTO();
				    //cashform.setFirstName("");
					//cashform.setListService("");
				   // cashform.getCashdto().setServFee("");
				    //cashform.getCashdto().setDeleteService("");
				    //cashform.getCashdto().setFunName("");
				    //cashform.getCashdto().setListService("");
				   // cashform.getCashdto().setAddMoreCounter(0);
					//cashform.setListID("");
					//cashform.setIdNo("");
					//cashform.setAge("");
					//count=0;
					//cashform.setBankAddr("");
					//cashform.setBankName("");
					//cashform.setServiceMap(new HashMap());
					//cashform.setServiceMapDetails(new HashMap());
					//cashform.setServFee("");
					//cashform.setDrpFee("");
					//cashform.setFatherName("");
					//cashform.setFirstName("");
					//cashform.setMiddleName("");
					//cashform.setLastName("");
					//cashform.setCashAmt("");
					//cashform.setRemarks("");
					//cashform.setFunName("");
				//	cashform.setActionValue("");
					//cashform.setIscalculated(0);
					//cashform.setRevMjrHd("");
					//cashform.setRevMnrHd("");
					//cashform.setRevSubMjrHd("");
					//cashform.setPosRefNo("");
					//cashform.setOldNewReceipt("Y");
					//flag = null;
					//cashform.getCashdto().setAddMoreCounter(0);
					FORWARD_JSP="Back";
					
				}
				
				logger.debug("ACtion STARTS HERE  ");
					ArrayList challanList = new ArrayList();			 
					challanList = cashform.getPaymentList();
					//session.setAttribute("challanList", challanList);	
					logger.debug("assch="+cashform.getCheckboxAssociateChallan());
					//int rowsize = cashform.getPaymentList().size();			
					if("on".equalsIgnoreCase(cashform.getCheckboxAssociateChallan())){				 
						logger.debug("check flag seting ");  
						checkFlag = "true";				   
					 }
					else
					{ 	ArrayList challanList1 = new ArrayList();
						cashform.setPaymentList(challanList1);
					}
					//session.setAttribute("checkFlag", checkFlag);	
					//String rowSize = String.valueOf(rowsize);
					//request.getSession().setAttribute("rowsize",rowSize);		
										
					//start
				
					 if(cashform.getPaymentList().size()>0){	
						 //paymentForm.getPaymentDTO().setDelbutton("");
						 logger.debug("966666666xxxxxxxxxx");
						 String delRow = cashform.getDelrow();
						 logger.debug("aaaaaaaaaa  ->  " + delRow);
						 StringTokenizer rowTokenizer = new StringTokenizer(delRow);
						 logger.debug("bbbbbbbbb    "  + rowTokenizer);
						 int totDelRows= 0;	
						 logger.debug("966666666");
						  while(rowTokenizer.hasMoreTokens()){
							  int row = Integer.parseInt(rowTokenizer.nextToken());	
							  logger.debug("77777  " + row);
							  			  
						      challanList.remove(row);
						     logger.debug("deleted successfuly");
						      totDelRows++;
						    }		
						  logger.debug("totoal deleted rows are " + totDelRows);
						  logger.debug("Button Type " + cashform.getCheckButton());
						  if(!(cashform.getCheckButton().equalsIgnoreCase(""))){			
						for(int i = 0;i<cashform.getPaymentList().size();i++){
							 String sbutton = String.valueOf(i);					 
							logger.debug("Button Type " + cashform.getCheckButton());
							 
							 int selectButton = Integer.parseInt(cashform.getCheckButton());
							 
							 
							 String actdelrow = cashform.getActDelRow();				 
							 StringTokenizer actDelRowToken = new StringTokenizer(actdelrow);
							 int totbutns = 0;
							  while(actDelRowToken.hasMoreTokens()){
								   int actRow = Integer.parseInt(actDelRowToken.nextToken());					   
								   if ( selectButton > actRow ){						   
									   totbutns++;
								   }
							  }	
							  
							  selectButton =  selectButton - totbutns;				
							 String scbutton = String.valueOf(selectButton);
							 logger.debug("buttn  paymentForm.getPaymentDTO().getCcheck() --> " + cashform.getCcheck());
							 logger.debug(" String scbutton   " + scbutton);
							 logger.debug("sbutton   " + sbutton);
						 /**********checking perticular challan ********************/
						 
						 if(sbutton.equalsIgnoreCase(scbutton)&& 
						    "challancheck".equalsIgnoreCase(cashform.getCcheck())){
							
							 // Check the particular challan Details.
							logger.debug("BD Calss here ---------->");
						  getStatus =cashBD.getChallanNoDetails(cashform,i);
						  cashform.setCheckButton("");	
						  cashform.setCcheck("");			  
									
							//session.setAttribute("status", getStatus);	
							for(int j =0;j<cashform.getPaymentList().size();j++){
								PhysicalChallanDTO challanDTO1 = new PhysicalChallanDTO();
								challanDTO1 = (PhysicalChallanDTO)challanList.get(j);		    			    	
							}
							logger.debug("12121212121");
							FORWARD_JSP ="Pay";
							return mapping.findForward(FORWARD_JSP);
							
					    }
						 else{
						  logger.debug("your not selected challan check buttion -->");				 	 
						 }
							 
						 
					 }
					 }		
					 }
					 								
					//end
					
					 
				
				//action belong to next button
				if ("paymentNext".equalsIgnoreCase(actionValue)) {
					
					
					
					ArrayList physicalChallan =null;
					ArrayList tid;
					//String temp = cashform.getTemp();
					cashform=cashBD.amtCalc(cashform);
					String a=cashform.getChallanDTO().getTotalAmt();
					logger.debug("Amount(In next button operation)"+a);
					tid=cashform.getChallanDTO().getTransactionId();
					logger.debug("Tid list(next)"+tid);
					chdtoobj.setTransactionId(tid);
					chdtoobj.setTotalAmt(a);
					physicalChallan = new ArrayList();
					String checkchallan=cashform.getCheckboxAssociateChallan();	
					logger.debug("challan status="+checkchallan);
					if(checkchallan!=null){
						 if (checkchallan.equals("on")){
							 for (int i = 0; i < cashform.getPaymentList().size(); i++)
						    	{					    					    	
								 	PhysicalChallanDTO chdto=new PhysicalChallanDTO();
								 	 chdto = (PhysicalChallanDTO) cashform.getPaymentList().get(i);
								 	 chdto.setScrollNumber(chdto.getScrollNumber());
								 	 chdto.setChallanDate(chdto.getChallanDate());
								 	 chdto.setAmount(chdto.getAmount());
								 	physicalChallan.add(chdto);
						    	}
							 chdtoobj.setPhysicalChallan(physicalChallan);
						     cashform.setChallanDTO(chdtoobj);
							 //HttpSession session=request.getSession();
							  request.setAttribute("scrolllist",cashform);
						}
					}
				     
					if (a != null){
						double aDouble = Double.parseDouble(a);
					    				    
						int amt=(int)aDouble;
						logger.debug("amount in integer"+amt);
						if (amt > 50000){
							String lValue =cashform.getListID();
							if (lValue != null && lValue.equals("0")){
								logger.debug("photo id proof null");
	                			messages.add("msg1",new ActionMessage("error.phoidproof"));
	     						saveMessages(request, messages);
	     						request.setAttribute("error2",cashform);
								FORWARD_JSP = "Pay";
							}
							else{
								FORWARD_JSP = "Confirmpage";
							}
						}
						else{
							FORWARD_JSP = "Confirmpage";
						}
					}
					logger.debug("reached");
					cashform.setChallanDTO(chdtoobj);
					logger.debug("reached1");
					request.setAttribute("cashform",cashform);
				}//end of next button action
				
				
				//action belong to cancel button
			    if ("Cancel".equalsIgnoreCase(actionValue))
			    {
			    	FORWARD_JSP="Cancel";
	
			    }
			    
	
			  /*start--action belong to Add More Button for multiple services
			   * @author Aakriti
			   * */
				
				if ("addMoreAction".equalsIgnoreCase(actionValue)) { 
					count =0;
					cashform.setServiceMap(new HashMap());
					cashform.setServiceMapDetails(new HashMap());
					if(flag==null){
						System.out.println("value of the flag is"+flag);
					cashform.setCashAmt("");
					cashform.setDrpFee("");
					
					}
					count = count + 1;
				logger.debug("COUNT IS:"+count);
				CashCounterLinkedDTO mapdto = cashform.getCashdto();
				map = cashform.getServiceMap();
				CashCounterLinkedDTO ndto = new CashCounterLinkedDTO();
				mapdto.setAddMoreCounter(count);
				ndto.setFunName(cashform.getFunName());
				ndto.setServFee(cashform.getServFee());
				
				
				ndto.setListService(cashform.getListService());
				ndto.setRevenueMjrHd(cashform.getRevMjrHd());
				ndto.setRevenueMnrHd(cashform.getRevMnrHd());
				ndto.setRevenueSubMjrHd(cashform.getRevSubMjrHd());
				
				if(cashform.getServFee().equals("0")||cashform.getServFee().equals("0.0")){
					FORWARD_JSP="invalidReceipt";
					return mapping.findForward(FORWARD_JSP);
				}
 			    
 			 
			

				logger.debug("add more RevMjrHd is............................................."+ndto.getRevenueMjrHd());
				logger.debug("add more RevSubMjrHd is............................................."+ndto.getRevenueMnrHd());
				logger.debug("add more RevMnrHd is............................................."+ndto.getRevenueSubMjrHd());
				String name =  ndto.getFunName();
				String key = null;
				
				key = name+Integer.toString(count);
				  mapdto.setKeyId(key);
				  cashform.setFormkeyId(key);
				
				if(!map.containsKey(key) ) {
					bol = true;
				}else {
					Iterator I = map.entrySet().iterator();
					while(I.hasNext()) {
						Map.Entry me = (Map.Entry) I.next();
						String tempKey = (String)me.getKey();
						
						
								key = key  ;
								logger.debug("Key :-"+key);
								//map.put(key, mapdto);
								bol = true;
								//eForm.setMapBuilding(map);
							}
				}
				HashMap serviceMap = new HashMap();
								
				if(bol ) {
					map.put(key, ndto);
					serviceMap.put(key, ndto);
				}
				ndto.setKeyId(key);
				cashform.setServiceMap(map);
				cashform.setServiceMapDetails(map);
				System.out.println("before frward");
				if ("addMoreActionNext".equalsIgnoreCase(actionNext)) {
					cashform.setActionNext(null);
					cashform.setActionNext("");
					FORWARD_JSP = "Confirmpage"; 
					//resetToken(request);//CSRF
					//saveToken(request);//CSRF
					return mapping.findForward(FORWARD_JSP);
				}else{
					cashform.setFunName("");
					cashform.setListService("");
					cashform.setServFee("");
					cashform.setRevMjrHd("");
					cashform.setRevMnrHd("");
					cashform.setRevSubMjrHd("");
					FORWARD_JSP = "Pay"; 
				}
				cashform.setActionValue(null);
				cashform.setActionNext(null);
				cashform.setCashForm(null);
				cashform.setActionNext("");
				cashform.setActionValue("");
				cashform.setCashForm("");
				
				
			}	
			/*end--action Add More Button for multiple services
			 * @author Aakriti
			 * */
//action for getting service fee uploaded according to the value selected in the dropdown
				
				if ("getServiceFee".equalsIgnoreCase(actionValue)){
					//CashCounterDTO deldto1 = cashform.getCashdto();
					//deldto1.setAddMoreCounter(count);
					//DocumentSearchBD docbd = new DocumentSearchBD();
					CashLinkedBD cashbd = new CashLinkedBD();
					//DocumentSearchDTO feeDsdto = new DocumentSearchDTO();
					CashCounterLinkedDTO feesDsdto1 = new CashCounterLinkedDTO(); 
					String servSelected = cashform.getFunName();
					String servId = cashform.getListService();
					String fees= null;
					//feeDsdto=docbd.getOthersFeeDuty(servId, null, userId);
					//feesDsdto1=cashbd.getOthersFeeDuty(servId, null, userId);//CHANGE_INTEGRATE WITH SERVICE FEE MATRIX
					
					String[] values=cashbd.getRevHeadsFee(servId);
					String fees1 = "0";
					
					if(values!=null){
						
						
						if(values[6].trim().equalsIgnoreCase("Y")){
							
							if(values[3]!=null && !values[3].trim().equalsIgnoreCase("") 
									&& !values[3].trim().equalsIgnoreCase("null")){
							fees = values[3].trim();
							if(fees.indexOf(".")!=-1){
							fees1 = fees.substring(0,fees.indexOf("."));
							int cielFee=Integer.parseInt(fees1);
							cielFee++;
							fees1=Integer.toString(cielFee);
							}else{
								fees1=fees;
							}
							}else{
								fees = "0";
							}
							//String fees1 = fees.substring(0,fees.indexOf("."));
							cashform.setServFee(fees1);
							cashform.setDrpFee(fees1);
							
						}
						else{//employe will enter
							if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
							cashform.setDrpFee(CommonConstant.CAPTURE_FEE_MSG_ENGLISH +values[7].trim());
							}else{
								cashform.setDrpFee(CommonConstant.CAPTURE_FEE_MSG_HINDI+values[8].trim());
							}
							cashform.setServFee("");
						}
						
						if(values[0]!=null){
							if(!values[0].trim().equalsIgnoreCase("null") && !values[0].trim().equalsIgnoreCase("")){
							cashform.setRevMjrHd(values[0].trim());
							}
							else{
								cashform.setRevMjrHd("-");
							}
						}else{
							cashform.setRevMjrHd("-");
						}
						if(values[1]!=null){
							
							if(!values[1].trim().equalsIgnoreCase("null") && !values[1].trim().equalsIgnoreCase("")){
								cashform.setRevSubMjrHd(values[1].trim());
								}
								else{
									cashform.setRevSubMjrHd("-");
								}
							
							
						}else{
							cashform.setRevSubMjrHd("-");
						} 
						if(values[2]!=null){
							
							if(!values[2].trim().equalsIgnoreCase("null") && !values[2].trim().equalsIgnoreCase("")){
								cashform.setRevMnrHd(values[2].trim());
								}
								else{
									cashform.setRevMnrHd("-");
								}
							
						}else{
							cashform.setRevMnrHd("-");
						} 
						
						
						
						
					}else{
						cashform.setServFee("0");
						cashform.setDrpFee("0");
						cashform.setRevMjrHd("-");   
						cashform.setRevSubMjrHd("-");
						cashform.setRevMnrHd("-");
					}
					logger.debug("RevMjrHd is............................................."+cashform.getRevMjrHd());
					logger.debug("RevSubMjrHd is............................................."+cashform.getRevSubMjrHd());
					logger.debug("RevMnrHd is............................................."+cashform.getRevMnrHd());
					
					//fees = feeDsdto.getTotalFee();
					//fees = feesDsdto1.getTotalFees1();
					//logger.debug("the fetched fees is............................................."+fees);
					//String fees1 = fees.substring(0,fees.indexOf("."));
					//logger.debug("the substring fees is............................................."+fees1);
					//cashform.setServFee(fees1);
					//cashform.setDrpFee(fees1);
					//cashform.setRevMjrHd("0030");
					//cashform.setRevSubMjrHd("03");
					//cashform.setRevMnrHd("800");
					cashform.setCashAmt("");
					FORWARD_JSP="Pay";
				}
			/* start--action belong to deleteMoreAction to delete services
			 * @author Aakriti
			 * */
				if ("deleteMoreAction".equalsIgnoreCase(actionValue)){
					map = cashform.getServiceMap();
					CashCounterLinkedDTO deldto = cashform.getCashdto();
					cashform.setCashAmt("");
					String[] servIDary=  cashform.getDelArry().split(",");
					for(int i = 0 ;i < servIDary.length ;i++) {
						if(map.containsKey(servIDary[i])){
							count = count-1;
							
					deldto.setAddMoreCounter(count);
					cashform.getCashdto().setAddMoreCounter(count);
					map.remove(servIDary[i]);
					}
						logger.debug("MAP IS :"+map);
					    cashform.setServiceMap(map);
					}
					FORWARD_JSP = "Pay";
					
				}
			
				if ("emptyValue".equalsIgnoreCase(actionValue)){
					cashform.setCashAmt("");
					cashform.getCashdto().setAddMoreCounter(cashform.getServiceMap().size());
					FORWARD_JSP = "Pay";
				}
				/* end--action belongs to empty the total amount
				 * @author Aakriti
				 * */
				cashform.setActionValue(null);
				cashform.setActionNext(null);
				cashform.setCashForm(null);
				cashform.setActionNext("");
				cashform.setActionValue("");
				cashform.setCashForm("");
           	}   //end of  cash form
			
			
			
			
			
			else if ("cashConfirmForm".equalsIgnoreCase(cashform.getCashForm()))
			{
				
				
				
				
				IGRSCommon common=new IGRSCommon();
				request.setAttribute("errorMesgFail", null);
				request.removeAttribute("errorMesgFail");
				String actionValue =cashform.getActionValue();
				logger.debug("the action value in the cash payment action of confirm page is ===> " + actionValue);
				//start--Added by Aakriti--
               	map= cashform.getServiceMap();
				Collection col=map.values();
				Object[] objary=col.toArray();
				CashCounterLinkedDTO cashdto1 = new CashCounterLinkedDTO() ;
				// end--addition--
				if ("modify".equalsIgnoreCase(actionValue)) {
					flag = null;
					cashform.setCashAmt("");
					CashCounterLinkedDTO mdto = cashform.getCashdto();
					String mkey = cashform.getFormkeyId();
					count = count-1;
					map.remove(mkey);
					cashform.setServiceMap(map);
					cashform.setServiceMapDetails(map);
					mdto.setAddMoreCounter(count);
					cashform.getCashdto().setAddMoreCounter(count);
					cashform.setIscalculated(0);
					FORWARD_JSP = "Pay";
				}
				if ("paymentConfirm".equalsIgnoreCase(actionValue)) {
			boolean cashAddStatus =false;
					/*if(isTokenValid(request))
					{
						resetToken(request);*///CSRF
					if(session.getAttribute("parentFunId")!=null){
					String functionid =  (String) session.getAttribute("parentFunId");
					cashform.setListService(functionid);
					}
					
					
                   if(session.getAttribute("reg_id")!=null){
						
						String regTxnId = (String) session.getAttribute("reg_id");
						cashform.setCheckerRegInitId(regTxnId);
					}
					
					
					request.setAttribute("errorMesgFail", null);
					request.removeAttribute("errorMesgFail");
					boolean firststatus = false;
					boolean status = false;
				try{
		
		                    cashAddStatus = cashBD.cashExist(cashform,userId);
		
		                    if(cashAddStatus==false){
						String combid=common.getSequenceNumber("IGRS_PAYMENT_TXNID","COM" );
					    cashform.setCombId(combid);
						firststatus = cashBD.addCombineDetails(cashform,userId);
						if (firststatus){
					
						status =cashBD.addFinalCashDetails(cashform, map, userId);
						if (status) {
		                     //int i=0;   
							logger.debug("Successfully inserted into paymentmodedetails table.");
							try{
							
								finList = cashBD.getTransPrintList(combid,languageLocale);
								
								//akansha
								String transactionidDetls = cashBD.getTransactiionId(combid,languageLocale);
								//chdtoobj.setDepositorName(depositorName)
								
								cashform.setTransactionId(transactionidDetls);
								
								cashform.setDisplayList(finList);
								request.setAttribute("cashform",cashform);
								cashform.setActionValue(null);
								cashform.setActionNext(null);
								cashform.setCashForm(null);
								cashform.setActionName("");
								cashform.setActionNext("");
								cashform.setActionValue("");
								cashform.setCashForm("");
								finList = new ArrayList();
								//String av= finList.get(0).toString();
								actionValue="";
								FORWARD_JSP = "Printpage";
								
								
								
							
							}catch(Exception e){
								request.setAttribute("errorMesgFail2", "Error Occured.Kindly refer transaction history");
					    		logger.error("exception is: " + e);	
					    		e.printStackTrace();
					    		FORWARD_JSP = "Confirmpage";
							}
							
							
						}
		                else{
		                	
							FORWARD_JSP = "Failurepage";
		               }
						
					}else{
						FORWARD_JSP = "Failurepage";
					}
		                    }
		                    else{
								FORWARD_JSP = "Failurepage";
							}
		                    
					}catch (Exception x) {
					
		    		logger.error("Exception in addCombineDetails(): " + x);	
		    		FORWARD_JSP = "Failurepage";
			        }
				
					
					
			
					
				}
				
				logger.debug("the values at the end are....."+cashform.getActionValue());
				logger.debug("the values at the end are....2"+cashform.getCashForm());
				request.setAttribute("cashform",cashform);
				//end of paymentConfirm action
				//resetToken(request);//CSRF
				
			
			}
			//end of cashConfirm Form
			else if ("printForm".equalsIgnoreCase(cashform.getCashForm()))
			{
				String actionValue =cashform.getActionValue();
				//action belong to cancel button
			    if ("Cancel".equalsIgnoreCase(actionValue))
			    {
			    	FORWARD_JSP="Cancel";
			    	
			    	
			    }
			    if ("printPop".equalsIgnoreCase(actionValue))
			    {
			    	FORWARD_JSP="printPop";
			    	
			    	
			    }
			}//end of printform
			
			else if ("cashLimit".equalsIgnoreCase(cashform.getCashForm())){
				String actionValue =cashform.getActionValue();	
				if ("payLimit".equalsIgnoreCase(actionValue)){
					flag = null;
					cashform.setCashAmt("");
					cashform.setServFee("");
					CashCounterLinkedDTO mdto = cashform.getCashdto();
					String mkey = cashform.getFormkeyId();
					//count = count-1;
					//map.remove(mkey);
					cashform.setServiceMap(map);
					cashform.setServiceMapDetails(map);
					mdto.setAddMoreCounter(count);
					cashform.setIscalculated(0);
					FORWARD_JSP = "Pay";
				}
			}
			
			else if("cashFormView".equalsIgnoreCase(cashform.getCashForm())){
				String actionValue =cashform.getActionValue();	
				//action belong to cancel button
			    if ("Cancel".equalsIgnoreCase(actionValue))
			    {
			    	FORWARD_JSP="Cancel";
	
			    }
			    
	//below for view		    
			    if ("CashViewDash".equalsIgnoreCase(actionValue))
			    {
			    	
			    	
			    	//search code
			    	String transId = cashform.getTransId();
					String chdate=cashform.getChdate();
					String minDate = cashform.getMinDate();
					cashform.setOffId(officeId);
					ArrayList status=cashBD.getData(cashform);
					request.setAttribute("chashlist",cashform);
					//request.setAttribute("alist", alist);
					request.setAttribute("alist", status);
					FORWARD_JSP="showDetails";
					
			    	
			    	
	
			    }
				
				
			}
			
			String stat = (String)request.getParameter("status");
			if("showDetails".equalsIgnoreCase(stat)){
				String transId = (String)request.getParameter("transId");
				//String combid ="";
				cashform.setTransId(transId);
				finList = new ArrayList();
				finList = cashBD.getTransPrintListView(transId,languageLocale);
				cashform.setDisplayList(finList);
				request.setAttribute("cashform",cashform);
				cashform.setActionValue(null);
				cashform.setActionNext(null);
				cashform.setCashForm(null);
				cashform.setActionName("");
				cashform.setActionNext("");
				cashform.setActionValue("");
				cashform.setCashForm("");
				FORWARD_JSP = "Printpage";
				
			}
			cashform.setActionValue(null);
			cashform.setCashForm(null);
		
		}	//end of form not null
		
		request.removeAttribute("serviceFee");
		request.removeAttribute("transactionId");
		
    	System.out.println("final forward page"+FORWARD_JSP);
    	return mapping.findForward(FORWARD_JSP);
    } //end of execute
   
}//end of class
