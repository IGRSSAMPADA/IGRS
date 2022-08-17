package com.wipro.igrs.copyissuance.dto;

import java.io.Serializable;

/**
 * ===========================================================================
 * File           :   PaymentDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   
 * Created Date   :   Feb 07, 2008

 * ===========================================================================
 */



public class PaymentDTO implements Serializable{


    private boolean challanflag = false;

    private String challanNo;
    private String bankName;
    private String challanDate;
    private String amount;
	private String paymentTxnId;
	private String validPayment;
	
    //Citizen Challan Details
    private String citizenChallanNumber;
    private String citizenBankName;
    private String citizenChallanDate;
    private String citizenAmount;
    
        
    public PaymentDTO() {

    }

    public void setChallanNo(String challanNo) {
        this.challanNo = challanNo;
    }

    public String getChallanNo() {
        return challanNo;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setChallanDate(String challanDate) {
        this.challanDate = challanDate;
    }

    public String getChallanDate() {
        return challanDate;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

	public void setPaymentTxnId(String paymentTxnId) {
		this.paymentTxnId = paymentTxnId;
	}

	public String getPaymentTxnId() {
		return paymentTxnId;
	}
		
	public void setValidPayment(String validPayment) {
		this.validPayment = validPayment;
	}

	public String getValidPayment() {
		return validPayment;
	}
			
    public void setChallanflag(boolean challanflag) {
        this.challanflag = challanflag;
    }

    public boolean isChallanflag() {
        return challanflag;
    }

    public void setCitizenChallanNumber(String citizenChallanNumber) {
        this.citizenChallanNumber = citizenChallanNumber;
    }

    public String getCitizenChallanNumber() {
        return citizenChallanNumber;
    }

    public void setCitizenBankName(String citizenBankName) {
        this.citizenBankName = citizenBankName;
    }

    public String getCitizenBankName() {
        return citizenBankName;
    }

    public void setCitizenChallanDate(String citizenChallanDate) {
        this.citizenChallanDate = citizenChallanDate;
    }

    public String getCitizenChallanDate() {
        return citizenChallanDate;
    }

    public void setCitizenAmount(String citizenAmount) {
        this.citizenAmount = citizenAmount;
    }

    public String getCitizenAmount() {
        return citizenAmount;
    }
}
