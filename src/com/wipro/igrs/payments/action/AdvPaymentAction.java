package com.wipro.igrs.payments.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.payments.bd.PaymentBD;
import com.wipro.igrs.payments.dto.PaymentDTO;
import com.wipro.igrs.payments.form.PaymentForm;

public class AdvPaymentAction extends BaseAction {


	/**
	 * ===========================================================================
	 * File           :   AdvPaymentAction.java
	 * Description    :   Represents the Advanced Payment Action Class
	 * Author         :   Karteek P
	 * Created Date   :   Feb 28, 2008

	 * ===========================================================================
	 */

	public ActionForward execute(ActionMapping mapping,
			                     ActionForm form,
			                     HttpServletRequest request,
			                     HttpServletResponse response,HttpSession session) throws NullPointerException,IOException {
	PaymentForm paymentForm =(PaymentForm) form;
	PaymentDTO paymentDtoList = paymentForm.getPaymentDTO();
	//paymentDtoList.setDurationFrom(paymentForm.getDurationFrom());
    //paymentDtoList.setDurationTo(paymentForm.getDurationTo());
    
	String txnID = request.getParameter("txnID");
	System.out.println("the select ID   " + txnID);
	PaymentBD paymentBd = new PaymentBD();
	
	/*
	 	if("Back".equalsIgnoreCase(paymentForm.getPaymentDTO().getAdvButton())){	  	
				return mapping.findForward("payment");	
			
	      }
	       if("OK".equalsIgnoreCase(paymentForm.getPaymentDTO().getAdvButton())){
	    	 	return mapping.findForward("payment");	      }
	
	try{
	ArrayList payList = paymentBd.getSimpleTXNNo(txnID);
	System.out.println("ths size of list is " + payList.size());
	System.out.println(payList);
	if(payList.size()>0){
		paymentForm.getPaymentDTO().setPayReceiptList(payList);
		 request.setAttribute("paymentForm", paymentForm);	
		 paymentForm.getPaymentDTO().setAdvButton("AdvReceipt");
		 return mapping.findForward("payReceipt");
		
	}
	else{
		return mapping.findForward("nonReceipt");
	 }
	}
    catch(NullPointerException ne){
    	LoggerMsg.error("Null Pointer Exception at Adv PaymentAction " + ne); 
    	
    }
	catch(Exception e){
		LoggerMsg.error("Exception at AdvPayemtAction " + e);
	}
	*/
		return mapping.findForward("success");
	}
 }
