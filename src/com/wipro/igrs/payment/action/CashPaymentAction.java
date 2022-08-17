package com.wipro.igrs.payment.action;


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
import com.wipro.igrs.payment.bd.CashCounterBD;
import com.wipro.igrs.payment.bd.EODChallanBD;
import com.wipro.igrs.payment.constant.CommonConstant;
import com.wipro.igrs.documentsearch.bd.DocumentSearchBD;
import com.wipro.igrs.documentsearch.dto.DocumentSearchDTO;
import com.wipro.igrs.payment.dto.CashCounterDTO;
import com.wipro.igrs.payment.dto.PhysicalChallanDTO;
import com.wipro.igrs.payment.form.CashCounterForm;
import com.wipro.igrs.payments.dto.ChallanDTO;
import com.wipro.igrs.reginit.constant.RegInitConstant;

/**
 * ===========================================================================
 * File           :   CashPaymentAction.java
 * Description    :   Represents the Cash Payment Action Class
 * Author         :   vengamamba P
 * Created Date   :    Feb 06, 2008

 * ===========================================================================
 */

public class CashPaymentAction extends BaseAction {
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
	.getLogger(CashPaymentAction.class);
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws Exception {    
    	
    	int count = 0;
    	
   	  HashMap map = null;
   	  HashMap h1 = new HashMap();
   	 ArrayList finList = new ArrayList();
   	 boolean bol = true;
   	 String flag = null;
   	
    	String FORWARD_JSP="Pay";
    	ArrayList list2 = new ArrayList();
    	String userId = (String)session.getAttribute("UserId");
    	String officeId = (String)session.getAttribute("loggedToOffice");
    //	HttpSession session=request.getSession(false);
    	ActionMessages messages = new ActionMessages();
    	String cashFormViewForm="cashFormView";
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
    		CashCounterForm cashform = (CashCounterForm)form;
    		
    		count=cashform.getCashdto().getAddMoreCounter();
    		request.setAttribute("cashform", cashform);
    		String par = request.getParameter("Prnt");
    		
    		
    		if("Y".equalsIgnoreCase(par)){
    			 cashFormViewForm="";
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
    		cashform.setLanguage(languageLocale);
			CashCounterBD cashBD = new CashCounterBD();
			EODChallanBD bd  = new EODChallanBD();
			PhysicalChallanDTO chdtoobj=new PhysicalChallanDTO();
			ArrayList  photoidList = cashBD.getPhotoIDList(languageLocale);//getting list photoids//HINDI
			ArrayList  serviceList =cashBD.getServiceList(languageLocale);//geting functionid list//HINDI
			ArrayList bankidList=cashBD.getBankidList();
			CashCounterDTO dto= new CashCounterDTO();
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
			CashCounterDTO dt2 = new CashCounterDTO();
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
					String amt = cashform.getCashAmt();
					
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
					CashCounterDTO dt2 = new CashCounterDTO();
				    cashform.setFirstName("");
					cashform.setListService("");
				    cashform.getCashdto().setServFee("");
				    cashform.getCashdto().setDeleteService("");
				    cashform.getCashdto().setFunName("");
				    cashform.getCashdto().setListService("");
				    cashform.getCashdto().setAddMoreCounter(0);
					cashform.setListID("");
					cashform.setIdNo("");
					cashform.setAge("");
					count=0;
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
					FORWARD_JSP="Pay";
					
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
					
					if(flag==null){
						System.out.println("value of the flag is"+flag);
					cashform.setCashAmt("");
					cashform.setDrpFee("");
					
					}
					count = count + 1;
				logger.debug("COUNT IS:"+count);
				CashCounterDTO mapdto = cashform.getCashdto();
				map = cashform.getServiceMap();
				CashCounterDTO ndto = new CashCounterDTO();
				mapdto.setAddMoreCounter(count);
				ndto.setFunName(cashform.getFunName());
				ndto.setServFee(cashform.getServFee());
				ndto.setListService(cashform.getListService());
				ndto.setRevenueMjrHd(cashform.getRevMjrHd());
				ndto.setRevenueMnrHd(cashform.getRevMnrHd());
				ndto.setRevenueSubMjrHd(cashform.getRevSubMjrHd());
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
					CashCounterBD cashbd = new CashCounterBD();
					//DocumentSearchDTO feeDsdto = new DocumentSearchDTO();
					CashCounterDTO feesDsdto1 = new CashCounterDTO(); 
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
					CashCounterDTO deldto = cashform.getCashdto();
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
			/* end--action deleteMoreAction to delete services
			 * @author Aakriti
			 * */
				
				/* start--action belongs to calculate total amount of fees
				 * @author Aakriti
				 * */
				if ("CalculateTotal".equalsIgnoreCase(actionValue)){
					
					CashCounterDTO calc = cashform.getCashdto();
					map= cashform.getServiceMap();
					Collection col=map.values();
					Object[] objary=col.toArray();
					double total =0.0;
					double newTotal=0.0;
					for(int n=0;n<objary.length;n++){
						calc=(CashCounterDTO)objary[n];
						String amt = calc.getServFee();
						double value = 0.0;
						value = Double.parseDouble(amt);
						total = total+value;
									
				      }
					String newamt = cashform.getServFee();
					double newvalue = 0.0;
					newvalue = Double.parseDouble(newamt);
					newTotal = total+newvalue;
					// exactTotal =Double.toString(newTotal);
					
					NumberFormat obj=new DecimalFormat("#0");
            		//regForm.setTotalMarketValue(obj.format(totalMarketVal));
					
					
					//String exactTotal1 = exactTotal.substring(0, exactTotal.indexOf("."));
					cashform.setCashAmt(obj.format(newTotal));
					cashform.getCashdto().setAddMoreCounter(cashform.getServiceMap().size());
					cashform.setIscalculated(1);
					FORWARD_JSP = "Pay";
					//saveToken(request);//CSRF
					}
				/* end--action belongs to calculate total amount of fees
				 * @author Aakriti
				 * */
				
				/* start--action belongs to empty the value of total amount
				 * @author Aakriti
				 * */
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
				CashCounterDTO cashdto1 = new CashCounterDTO() ;
				// end--addition--
				if ("modify".equalsIgnoreCase(actionValue)) {
					flag = null;
					cashform.setCashAmt("");
					CashCounterDTO mdto = cashform.getCashdto();
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
					
					/*if(isTokenValid(request))
					{
						resetToken(request);*///CSRF
					
					request.setAttribute("errorMesgFail", null);
					request.removeAttribute("errorMesgFail");
					boolean firststatus = false;
					boolean status = false;
					try{
						String combid=common.getSequenceNumber("IGRS_PAYMENT_TXNID","COM" );
					    cashform.setCombId(combid);
						firststatus = cashBD.addCombineDetails(cashform,userId);
						if (firststatus){
					//FOR Loop added by Aakriti
					/*for(int n=0;n<objary.length;n++){
						cashdto1=(CashCounterDTO)objary[n];
					 
					//action belong to submit button
						String a = cashdto1.getServFee();
						cashform.setListService(cashdto1.getListService());
						//logger.debug("amount"+a);
						ArrayList tids=cashform.getChallanDTO().getTransactionId();
						logger.debug("tid in action"+tids);
						//adding automated seq number
						//String tid=common.getSequenceNumber("IGRS_PAYMENT_TXNID","cash" );
						//logger.debug("transaction id"+tid);
						String strOutDt = "";
						Date d=new Date();
						logger.debug("date"+d);
						strOutDt = new SimpleDateFormat("dd/MMM/yyyy").format(d);
						logger.debug("date converted="+strOutDt);
						//String d=cashBD.dateConvertion();
						chdtoobj.setTransactionId(tids);
						chdtoobj.setTotalAmt(a);
						//chdtoobj.setTxnid(tid);
						chdtoobj.setCurrentdate(strOutDt);
						cashform.setChallanDTO(chdtoobj);
						//String txnid=cashform.getChallanDTO().getTxnid();
						//logger.debug("txnid="+txnid);
						String funcname=cashBD.getFuncName(cashform.getListService());
						String mode=cashBD.getPaymode("IGRS_PT_001");
						String checkchallan=cashform.getCheckboxAssociateChallan();
						chdtoobj.setFuncName(funcname);
						chdtoobj.setMode(mode);
						chdtoobj.setUsername(userId);
						cashform.setChallanDTO(chdtoobj);
						list2.add(n,chdtoobj);
						chdtoobj = new PhysicalChallanDTO();
						cashform.setDisplayList(list2);
					}*/
						//inserting into paymentmode details
						
						//status =cashBD.addCashDetails(cashform,userId);
						status =cashBD.addFinalCashDetails(cashform, map, userId);
						if (status) {
		                     //int i=0;   
							logger.debug("Successfully inserted into paymentmodedetails table.");
							try{
								finList = cashBD.getTransPrintList(combid,languageLocale);
								//chdtoobj.setDepositorName(depositorName)
								cashform.setDisplayList(finList);
								request.setAttribute("cashform",cashform);
								cashform.setActionValue(null);
								cashform.setActionNext(null);
								cashform.setCashForm(null);
								cashform.setActionName("");
								cashform.setActionNext("");
								cashform.setActionValue("");
								cashform.setCashForm("");
								actionValue="";
								FORWARD_JSP = "Printpage";
								
							
							}catch(Exception e){
								request.setAttribute("errorMesgFail2", "Error Occured.Kindly refer transaction history");
					    		logger.error("exception is: " + e);	
					    		FORWARD_JSP = "Confirmpage";
							}
							
							//inserting into mapping table if challans exists
							/*if (tids!=null){
							for(i=0;i<tids.size();i++){
								status = cashBD.addTransidDetails(tids.get(i).toString(),txnid);
								
								if (status) { 
									logger.debug("Successfully inserted into paymentmodemapping table.");
									boolean updatestatus=cashBD.updateStatus(tids.get(i).toString());
									if (updatestatus) { 
										logger.debug("Successfully updated status flag in paymentmodedetails table.");
										
									
									}
									else{
										logger.debug("UnSuccessfully updated status flag in paymentmodedetails table.");
										cashBD.cancelTrasanction();
										FORWARD_JSP = "Failurepage";
										break;
									}
								
								
								}
								else{
									logger.debug("UnSuccessfully inserted into paymentmodemapping table.");
									cashBD.cancelTrasanction();
									FORWARD_JSP = "Failurepage";
									break;
								}
								
							}//end of inserting into mapping table	
							}*/
							//logger.debug("size"+tids.size());
							//if (tids!=null)
						
						}// end if inserting cash data
		                else{
		                	/*logger.debug("UnSuccessfully inserted into paymentmodedetails table.");
		                	cashBD.cancelTrasanction();*/
							FORWARD_JSP = "Failurepage";
		               }
						
					}/*if (status){
						logger.debug("calling commit procedure");
						cashBD.commitingTrasanction();
						String funcname=cashBD.getFuncName(cashform.getListService());
						//String mode=cashBD.getPaymode("01");
						String mode=cashBD.getPaymode("IGRS_PT_001");
						String checkchallan=cashform.getCheckboxAssociateChallan();
						if (checkchallan==null)
						     mode=cashBD.getPaymode("01");
						 else
						     mode=cashBD.getPaymode("04");
						//String uname=cashBD.getUserName(uid);
						chdtoobj.setFuncName(funcname);
						chdtoobj.setMode(mode);
						chdtoobj.setUsername(userId);
						cashform.setChallanDTO(chdtoobj);
						list2.add(n,chdtoobj);
						chdtoobj = new PhysicalChallanDTO();
						cashform.setDisplayList(list2);
						FORWARD_JSP = "Printpage";
					}*/
				
					}catch (Exception x) {
					//request.setAttribute("errorMesgFail", "Data Could not be saved. Kindly try again");
		    		logger.error("Exception in addCombineDetails(): " + x);	
		    		FORWARD_JSP = "Failurepage";
			        }
				
					
					
					
				/*}else{
					FORWARD_JSP = "FailureMsg";
					mapping.findForward(FORWARD_JSP);				
				}*///CSRF
					
					
					
					
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
					CashCounterDTO mdto = cashform.getCashdto();
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
			
			else if("cashFormView".equalsIgnoreCase(cashFormViewForm)){
				String actionValue ="CashViewDash";	
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
    	
    	System.out.println("final forward page"+FORWARD_JSP);
    	return mapping.findForward(FORWARD_JSP);
    } //end of execute
   
}//end of class
