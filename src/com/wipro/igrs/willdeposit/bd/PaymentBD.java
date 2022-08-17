/**
 * 
 */
package com.wipro.igrs.willdeposit.bd;

import org.apache.log4j.Logger;

import com.wipro.igrs.willdeposit.bo.PaymentBO;
import com.wipro.igrs.willdeposit.form.PaymentForm;

public class PaymentBD {

	PaymentBO paymentBO;
	private Logger logger = (Logger) Logger.getLogger(PaymentBD.class);
	public PaymentBD() {
		try{
			paymentBO = new PaymentBO();
		}
		catch(Exception e){
			logger.error("DAO obj creation at BD construtor   " + e);
		}
	}
	
	
	/**
	 * @param _paymentForm
	 * @return String
	 * @throws Exception
	 */
	public String getCashDetails(PaymentForm _paymentForm) throws Exception {
		String returnFlag  = paymentBO.getCashDetails(_paymentForm);
		if("cashbutton".equalsIgnoreCase(_paymentForm.getPaymentDTO().getCheckButton())){
			return returnFlag;
		}
		if(returnFlag == "success"){			
			returnFlag = paymentBO.setCashTxn(_paymentForm); 
		}
		
		return returnFlag;
	}


	/**
	 * All CHALLANS validation check. If true come to setChallanDetail();
	 * @param _paymentForm
	 * @return String
	 * @throws Exception
	 */
	public String getChallanDetails(PaymentForm _paymentForm) throws Exception {
		
		String returnFlag = paymentBO.getChallanDetail(_paymentForm);		 
		if(returnFlag=="success"){
			String str = "Challan";
			logger.debug("BD before calling set Value is --> " + returnFlag);
			returnFlag = paymentBO.setChallanDetail(_paymentForm,str);
			logger.debug("BD after calling set Value is --> " + returnFlag);
		}
		logger.debug("BD the Final Return Value is " + returnFlag);
		return returnFlag;
	}


	/**
	 * @param _paymentForm
	 * @param buttonNo
	 * @return String
	 * @throws Exception
	 */
	public String  getChallanNoDetails(PaymentForm _paymentForm,int buttonNo) throws Exception {
		String returnFlag = paymentBO.getChallanNoDetail(_paymentForm,buttonNo);			 
		return returnFlag;
	}




}
