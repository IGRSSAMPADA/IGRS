/**
 * ===========================================================================
 * File           :   PaymentDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Madan  Mohan
 * Created Date   :   Nov 28, 2007

 * ===========================================================================
 */

package com.wipro.igrs.intimationtransaction.dto;

public class PaymentDTO {

    private boolean challanflag = false;

    private String challanNo;
    private String bankName;
    private String challanDate;
    private String amount;
    private String paymentTxnId;

    //Citizen Challan Details
    private String citizenChallanNumber;
    private String citizenBankName;
    private String citizenChallanDate;
    private String citizenAmount;

    private String paymentValidateAction;
    private String paymentModifyAction;


    //default constructor   
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

    public void setPaymentValidateAction(String paymentValidateAction) {
        this.paymentValidateAction = paymentValidateAction;
    }

    public String getPaymentValidateAction() {
        return paymentValidateAction;
    }

    public void setPaymentModifyAction(String paymentModifyAction) {
        this.paymentModifyAction = paymentModifyAction;
    }

    public String getPaymentModifyAction() {
        return paymentModifyAction;
    }
}
