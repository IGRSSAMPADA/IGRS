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
package com.wipro.igrs.documentsearch.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.documentsearch.dto.DocumentSearchDTO;
import com.wipro.igrs.documentsearch.dto.OldDocSearchDashboardDTO;
import com.wipro.igrs.documentsearch.dto.PartyDetailsDTO;
import com.wipro.igrs.documentsearch.dto.PaymentDTO;

public class DocumentSearchForm extends ActionForm {

private DocumentSearchDTO dsearchdto=new DocumentSearchDTO();
private CaveatsDTO caveatsDTO=new CaveatsDTO();
private ArrayList paymentList = new ArrayList();
private PartyDetailsDTO partyDto = new PartyDetailsDTO();  
private String fromDate;
private String toDate;
private String dateOfReg;
private String payment;
private String docTxnId;
private String flag;
private String fromDate1;
private String toDate1;
//added by shruti
private String language;
//end
private String regNumberDownloadFinal;
private String printNumber;

private OldDocSearchDashboardDTO oldDocSearchDTO;
private String courtName;
private String courtOrderNo;
private String courtOrderDate;
private String financialYear;
public String getRegNumberDownloadFinal() {
	return regNumberDownloadFinal;
}

public void setRegNumberDownloadFinal(String regNumberDownloadFinal) {
	this.regNumberDownloadFinal = regNumberDownloadFinal;
}

public String getPrintNumber() {
	return printNumber;
}

public void setPrintNumber(String printNumber) {
	this.printNumber = printNumber;
}

public String getLanguage() {
	return language;
}

public void setLanguage(String language) {
	this.language = language;
}

public CaveatsDTO getCaveatsDTO() {
	return caveatsDTO;
}

public void setCaveatsDTO(CaveatsDTO caveatsDTO) {
	this.caveatsDTO = caveatsDTO;
}

public String getFromDate1() {
	return fromDate1;
}

public void setFromDate1(String fromDate1) {
	this.fromDate1 = fromDate1;
}

public String getToDate1() {
	return toDate1;
}

public void setToDate1(String toDate1) {
	this.toDate1 = toDate1;
}
public String getFlag() {
	return flag;
}

public void setFlag(String flag) {
	this.flag = flag;
}

public CaveatsDTO getCaveatDTO() {
	return caveatsDTO;
}

public void setCaveatDTO(CaveatsDTO caveatsDTO) {
	this.caveatsDTO = caveatsDTO;
}

public PartyDetailsDTO getPartyDto() {
	return partyDto;
}

public void setPartyDto(PartyDetailsDTO partyDto) {
	this.partyDto = partyDto;
}

/**
 * @return
 */
public String getPayment() {
	return payment;
}

/**
 * @param payment
 */
public void setPayment(String payment) {
	this.payment = payment;
}

/**
 * @return
 */
public String getDateOfReg() {
	return dateOfReg;
}

/**
 * @param dateOfReg
 */
public void setDateOfReg(String dateOfReg) {
	this.dateOfReg = dateOfReg;
}

/**
 * @return
 */
public String getFromDate() {
	return fromDate;
}

/**
 * @param fromDate
 */
public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}

/**
 * @return
 */
public String getToDate() {
	return toDate;
}

/**
 * @param toDate
 */
public void setToDate(String toDate) {
	this.toDate = toDate;
}

/**
 * @return
 */
public ArrayList getPaymentList() {
	return paymentList;
}

/**
 * @param paymentList
 */
public void setPaymentList(ArrayList paymentList) {
	this.paymentList = paymentList;
}



/**
 * @return
 */
public DocumentSearchDTO getDsearchdto() {
	return dsearchdto;
}

/**
 * @param dsearchdto
 */
public void setDsearchdto(DocumentSearchDTO dsearchdto) {
	this.dsearchdto = dsearchdto;
}


/**
 * @param index
 * @param vo
 */
public void setPaymentItems(int index, PaymentDTO vo){
	
	for (; index >= paymentList.size(); paymentList.add(new PaymentDTO()));
	paymentList.set(index, vo);
}

/**
 * @param index 
 * @return PaymentDTO
 */

public PaymentDTO getPaymentItems(int index){
	
	for(; index >= paymentList.size(); paymentList.add(new PaymentDTO()));
	return (PaymentDTO) paymentList.get(index);
}

public String getDocTxnId() {
    return docTxnId;
}

public void setDocTxnId(String docTxnId) {
    this.docTxnId = docTxnId;
}

/**
 * @param oldDocSearchDTO the oldDocSearchDTO to set
 */
public void setOldDocSearchDTO(OldDocSearchDashboardDTO oldDocSearchDTO) {
	this.oldDocSearchDTO = oldDocSearchDTO;
}

/**
 * @return the oldDocSearchDTO
 */
public OldDocSearchDashboardDTO getOldDocSearchDTO() {
	return oldDocSearchDTO;
}

public String getCourtName() {
	return courtName;
}

public void setCourtName(String courtName) {
	this.courtName = courtName;
}

public String getCourtOrderNo() {
	return courtOrderNo;
}

public void setCourtOrderNo(String courtOrderNo) {
	this.courtOrderNo = courtOrderNo;
}

public String getCourtOrderDate() {
	return courtOrderDate;
}

public void setCourtOrderDate(String courtOrderDate) {
	this.courtOrderDate = courtOrderDate;
}

public String getFinancialYear() {
	return financialYear;
}

public void setFinancialYear(String financialYear) {
	this.financialYear = financialYear;
}

}
