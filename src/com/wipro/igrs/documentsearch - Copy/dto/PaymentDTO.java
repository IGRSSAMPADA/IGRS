/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.documentsearch.dto;

import java.io.Serializable;

public class PaymentDTO implements Serializable{


    private boolean challanflag = false;

    private String challanNo;
    private String bankName;
    private String challanDate;
    private String amount;

    
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
