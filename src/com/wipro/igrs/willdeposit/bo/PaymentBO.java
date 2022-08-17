package com.wipro.igrs.willdeposit.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wipro.igrs.willdeposit.dao.PaymentDAO;
import com.wipro.igrs.willdeposit.dto.ChallanDTO;
import com.wipro.igrs.willdeposit.form.PaymentForm;

public class PaymentBO {
	PaymentDAO paymentDao;
	private Logger logger = (Logger) Logger.getLogger(PaymentBO.class);

	public PaymentBO() {

		try{
			paymentDao = new PaymentDAO();
		}
		catch(Exception e){
			logger.error("DAO obj creation at BO construtor   " + e);   	
		}
	}
	public String getCashDetails(PaymentForm _paymentForm) throws Exception {
		double Totamt;
		String cashStatus="";
		ArrayList returnList = new ArrayList();
		double totAmt=0.0;
		boolean setTxn = true;
		System.out.println("BO Starts Here ");
		String returnFlag = "success";
		try{
			String challanStatus;
			String[] challanmsg = new String[15] ;		
			String[] cashlist = new String[4];
			cashlist[0] = _paymentForm.getPaymentDTO().getReceiptID();
			cashlist[1] = Double.toString(_paymentForm.getPaymentDTO().getAmount());
			cashlist[2] = _paymentForm.getCashDate();		   
			if("".equalsIgnoreCase(_paymentForm.getChallancheck())){
				cashlist[3] = "challan" ;
			}
			//***Starts here Check The Cash Details
			cashStatus = paymentDao.getCashDetails(cashlist);
			if("success".equalsIgnoreCase(cashStatus)){

				if("".equalsIgnoreCase(_paymentForm.getChallancheck())){
					//*** Starts here check Challan Details

					String getChallnflg = getChallanDetail(_paymentForm);
					if("fail".equalsIgnoreCase(getChallnflg)){

						return getChallnflg;
					}
					else{
						returnFlag = getChallnflg ;
					}
					returnFlag = cashStatus;
				}
			}else{				   
				return cashStatus;
			}
		}
		catch(NullPointerException ne){
			logger.error("Null Pointer Exception at getCash Details in BO " + ne);

		}
		catch(Exception e){
			logger.error(" Exception at getCash Details in BO " + e);    	  
		}      
		System.out.println("BO FINAL RETURN VALUE -->   " + cashStatus);
		return returnFlag;
	}

	public String setCashTxn(PaymentForm _paymentForm){
		System.out.println("Starting BO setCash ");
		double totAmt = 0.0;
		String returnFlag = "success";

		//  String amt = (String)session.getAttribute("amt");
		String amt = "1000";

		totAmt = totAmt + _paymentForm.getPaymentDTO().getAmount();	 
		if("".equalsIgnoreCase(_paymentForm.getChallancheck())){
			for (int i = 0; i < _paymentForm.getPaymentList().size(); i++) {
				ChallanDTO challanDTO = new ChallanDTO();
				totAmt = totAmt + Double.valueOf(challanDTO.getAmount().trim()).doubleValue(); 
			}
			double amtdbl = Double.parseDouble(amt);
			if(totAmt < amtdbl ){
				return "fail";
			}
		}
		else{
			double amtdbl = Double.parseDouble(amt);
			if(totAmt < amtdbl ){
				return "fail";
			}
		}
		String setTxn = paymentDao.setTxnID(totAmt);
		if(!(setTxn.equalsIgnoreCase("fasle"))){
			_paymentForm.setTotAmt(totAmt);		 
			boolean setTxnMping = paymentDao.setPaymentTxn(_paymentForm.getPaymentDTO().getReceiptID(),setTxn);
			if("".equalsIgnoreCase(_paymentForm.getChallancheck())){
				// code for insert challan values in to Txn Details table
				String setChallnFlag = setChallanDetail(_paymentForm,setTxn);
				if(setChallnFlag != "fail"){
					returnFlag = setChallnFlag; 
				}
				else{
					return setChallnFlag;
				}
			}
			returnFlag = setTxn;

		}
		else{
			return "fail";
		}
		System.out.println("the final BO return set is " + returnFlag);
		return returnFlag;
	}

	/*
	 * @param _paymentForm
	 * @return String
	 */
	public String  getChallanDetail(PaymentForm _paymentForm){
		boolean  flag = true;
		ArrayList returnList =new ArrayList();
		String[] returnflag = new  String[15] ;
		String returnFlag = null;
		try{
			ChallanDTO challanDTO = null;
			double totamt =0;
			int n=0;
			int sizeList = _paymentForm.getPaymentList().size();
			String[] challanNos = new String[15];  
			for (int i = 0; i < _paymentForm.getPaymentList().size(); i++) {	   
				String challanMsg = null;
				double amt;
				challanDTO = new ChallanDTO();
				String[] challanNo = new String[4];		   
				challanDTO = (ChallanDTO) _paymentForm.getPaymentList().get(i);
				challanNo[0] = challanDTO.getChallanNo();
				challanNo[1] = challanDTO.getChallanDate();   	   
				amt = Double.parseDouble(challanDTO.getAmount().trim()) ;  
				if("cash".equalsIgnoreCase(_paymentForm.getPaymentDTO().getSelectType())){   	    	  
					challanNo[2] = "cash";
					challanNo[3] =  _paymentForm.getPaymentDTO().getReceiptID();   	    	  
				}
				logger.debug("BO before calling getChalln Details();");
				challanMsg = paymentDao.getChallanDetails(challanNo,amt);
				logger.debug("BO after calling getChalln Details();");
				if("fail".equalsIgnoreCase(challanMsg)){
					return challanMsg;
				}
				else{
					returnFlag = challanMsg;
				}		   
			}
		}catch (NullPointerException ne) {
			System.out.println("the Return type at BO is " + returnFlag);
			logger.error("Null Pointer Exception at getChallan Details in BO " + ne);
		}catch (Exception e) {
			logger.error("Exception at getChallan Details in BO " + e);
		}   
		System.out.println("the Return type at BO is " + returnFlag);
		return returnFlag;
	}
	
	
	
	/**
	 * @param _paymentForm
	 * @param _TxnNo
	 * @return String
	 */
	public String  setChallanDetail(PaymentForm _paymentForm,String _TxnNo){
		boolean  flag = true;
		ArrayList returnList =new ArrayList();
		String[] returnflag = new  String[15] ;
		String returnFlag = null;
		HttpSession session = null;
		String txnNo = _TxnNo;
		//String sessionamt = (String)session.getAttribute("amt");
		String sessionamt = "1000";
		try{
			System.out.println("BO start setChallan Details() " + _TxnNo);
			double totAmt =0;
			int n=0;
			int sizeList = _paymentForm.getPaymentList().size();
			String[] challanNos = new String[15];
			System.out.println("calling dto objects at BO " + _paymentForm.getPaymentList().size());
			for (int i = 0; i < _paymentForm.getPaymentList().size(); i++) {
				System.out.println("for loop starts here --> " + _paymentForm.getPaymentList());
				ChallanDTO challanDTO = new ChallanDTO();
				challanDTO = (ChallanDTO) _paymentForm.getPaymentList().get(i);
				System.out.println("after created ChallanDto object ---> " + challanDTO.getAmount() );
				System.out.println("the Amount at BO is -->   " + Double.valueOf(challanDTO.getAmount()));
				totAmt = totAmt + Double.parseDouble(challanDTO.getAmount()); 
			}
			if(totAmt < Double.parseDouble(sessionamt)){
				return "fail";
			}        			
			System.out.println("the txn no in BO is " + _TxnNo);
			if("Challan".equalsIgnoreCase(_TxnNo)){
				_paymentForm.setTotAmt(totAmt);
				txnNo = paymentDao.setTxnID(totAmt);
			}
			ChallanDTO challanDTO = null;
			for (int i = 0; i < _paymentForm.getPaymentList().size(); i++) {	   
				String challanTxnId = null;
				double amt;
				challanDTO = new ChallanDTO();
				String[] challanNo = new String[4];		   
				challanDTO = (ChallanDTO) _paymentForm.getPaymentList().get(i);
				challanNo[0] = challanDTO.getChallanNo();
				challanNo[1] = challanDTO.getChallanDate();   	   
				amt = Double.parseDouble(challanDTO.getAmount().trim()) ;
				//Call the DAO method 
				System.out.println("Before get Txn NO in BO");
				
				challanTxnId = paymentDao.getChallanTxn(challanNo,amt);
				System.out.println("After get Txn NO in BO " + challanTxnId);
				if(challanTxnId == null ||challanTxnId.equalsIgnoreCase("")){
					return "fail";
				}
				else{
					System.out.println("before calling setPay()");
					flag = paymentDao.setPaymentTxn(challanTxnId,txnNo);
					System.out.println("after Calling SEt Pay()  " + flag);
					if(flag == true){
						returnFlag = txnNo;
					}
					else{
						return "fail";
					}
				}
			} 		   
			System.out.println("the Txn NO is " + txnNo);

		}catch (NullPointerException ne) {
			logger.error("Null Pointer Exception at getChallan Details in BO " + ne);
		}catch (Exception e) {
			logger.error("Exception at getChallan Details in BO " + e);
		}   
		System.out.println("Bo Final Set Challan Return value " + returnFlag);
		return returnFlag;
	}




	public String  getChallanNoDetail(PaymentForm _paymentForm,int buttonNo){
		boolean  flag = true;
		ArrayList returnList =new ArrayList();
		String[] returnflag = new  String[15] ;
		String returnFlag = null;
		try{
			ChallanDTO challanDTO = null;
			double totamt =0;
			int n=0;
			int sizeList = _paymentForm.getPaymentList().size();
			String[] challanNos = new String[5];            
			String challanMsg = null;
			double amt;
			challanDTO = new ChallanDTO();
			String[] challanNo = new String[4];		   
			challanDTO = (ChallanDTO) _paymentForm.getPaymentList().get(buttonNo);
			challanNo[0] = challanDTO.getChallanNo();
			challanNo[1] = challanDTO.getChallanDate();   	   
			amt = Double.parseDouble(challanDTO.getAmount().trim()) ;
			challanMsg = paymentDao.getChallanTxn(challanNo,amt);

			if("null".equalsIgnoreCase(challanMsg)){
				return "fail";
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



}
