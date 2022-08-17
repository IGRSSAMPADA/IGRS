package com.wipro.igrs.payment.bo;

import java.util.ArrayList;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;


import org.apache.log4j.Logger;
import com.wipro.igrs.payment.constant.CommonConstant;
import com.wipro.igrs.payment.dao.CashCounterDAO;
import com.wipro.igrs.payment.dto.PhysicalChallanDTO;
import com.wipro.igrs.payment.form.CashCounterForm;
import com.wipro.igrs.payment.form.CashCounterLinkedForm;

public class CashCounterBO {
	private Logger logger = (Logger) Logger.getLogger(CashCounterBO.class);
	public CashCounterBO() {

	}

	
	 /*  Method Name : getChallanNoDetail()
	    *  Arguments   : PaymentForm Bean and ButtonNo
	    *  Return      : if it success return Success
	    *                other wise return Fail
	    *  Exception   : NullPointer or Exception*/   
	   public String  getChallanNoDetail(CashCounterLinkedForm _paymentForm,int buttonNo)
	                throws NullPointerException ,Exception{
		   
		   String returnFlag = "fail";
		   CashCounterDAO dao = new CashCounterDAO();
		   try{
			PhysicalChallanDTO challanDTO = null;
			
	        int sizeList = _paymentForm.getPaymentList().size();
	                   
			   String challanMsg = null;
			   double amt;
			   challanDTO = new PhysicalChallanDTO();
			   String[] challanNo = new String[4];		   
	   	         challanDTO = (PhysicalChallanDTO) _paymentForm.getPaymentList().get(buttonNo);
	             challanNo[0] = challanDTO.getScrollNumber();
	   	         challanNo[1] = challanDTO.getChallanDate();   	   
	   	         amt = Double.parseDouble(challanDTO.getAmount().trim());
	   	         challanMsg =dao.getChallanTxn(challanNo,amt);   	        
	   	         if(challanMsg == null){
	   	        	returnFlag = "fail";
	   	         }
	   	         else{
	   	         returnFlag = "success";
	   	         }		   
		   	 
	     }catch (NullPointerException ne) {
		  logger.error("Null Pointer Exception at getChallan Details in BO " + ne);
	    }catch (Exception e) {
	    	   logger.error("Exception at getChallan Details in BO " + e);
		}
	       return returnFlag;
	  }  
	
	   /* Method Name : getChallanDetail
	    *  Arguments   : PaymentForm Bean 
	    *  Return      : return form after setting values
	    *  Exception   : NullPointer or Exception*/
	   public CashCounterLinkedForm  getChallanDetail(CashCounterLinkedForm _paymentForm) throws NullPointerException ,Exception{
		  
		   
		   CashCounterDAO dao = new CashCounterDAO();
		      
		   ArrayList tid=new ArrayList();
		   PhysicalChallanDTO chdtoobj=new PhysicalChallanDTO();
		   try{
			   PhysicalChallanDTO challanDTO = null;
			   double totamt =0;
	       	   int sizeList = _paymentForm.getPaymentList().size();
	       	   for (int i = 0; i < _paymentForm.getPaymentList().size(); i++) {	   
	       		   String challanMsg = null;
	       		   double amt;
	       		   challanDTO = new PhysicalChallanDTO();
	       		   String[] challanNo = new String[4];		   
	       		   challanDTO = (PhysicalChallanDTO) _paymentForm.getPaymentList().get(i);
	       		   challanNo[0] = challanDTO.getScrollNumber();
	       		   challanNo[1] = challanDTO.getChallanDate();   	   
	       		   amt = Double.parseDouble(challanDTO.getAmount().trim());  
	   	        
	       		   challanMsg = dao.getChallanTxn(challanNo,amt);
	       		   if(challanMsg != null){
	       			   totamt=totamt+amt;
	       			   tid.add(challanMsg);
	       			logger.debug("total="+totamt+"tid"+tid);
	       		   }
	       		   
	       	   	}
	       	 
	       	String amount=totamt+"";
			 chdtoobj.setTransactionId(tid);
			 chdtoobj.setTotalAmt(amount);
			 _paymentForm.setChallanDTO(chdtoobj);
		     
	     }catch (NullPointerException ne) {
	    	 
	    	  logger.error("Null Pointer Exception at getChallan Details in BO " + ne);
	      }catch (Exception e) {
	    	  	 logger.error("Exception at getChallan Details in BO " + e);
	      	}   
	     
	       return _paymentForm;
	  }
		  
}
