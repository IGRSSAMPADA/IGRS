/**
 * 
 */
package com.wipro.igrs.willdeposit.dto;

import java.util.ArrayList;
import com.wipro.igrs.willdeposit.dto.ChallanDTO;

public class PaymentDTO {
  
   private String addButton;
   private String page;
   
   private String receiptID;
   private double amount;
   private String txnNO;
   private double totAmt;
   private String user;
   
   private String challancheck;
   
   private String searchType;
   private String paybuttonType;
   private String buttonConfirm;
   private double diffAmt;
   
   private String selectButton = "";
   private String checkButton = "";
   
   private String selectType;
   
   private ArrayList paymentItems = new ArrayList();
   private ArrayList paymentList = new ArrayList();
   private ChallanDTO payDTO = new ChallanDTO();
   
   private ArrayList payList = new ArrayList();
   
/**
 * @return the addButton
 */
public String getAddButton() {
	return addButton;
}
/**
 * @param addButton the addButton to set
 */
public void setAddButton(String addButton) {
	this.addButton = addButton;
}
/**
 * @return the amount
 */
public double getAmount() {
	return amount;
}
/**
 * @param amount the amount to set
 */
public void setAmount(double amount) {
	this.amount = amount;
}
/**
 * @return the receiptID
 */
public String getReceiptID() {
	return receiptID;
}
/**
 * @param receiptID the receiptID to set
 */
public void setReceiptID(String receiptID) {
	this.receiptID = receiptID;
}
/**
 * @return the searchType
 */
public String getSearchType() {
	return searchType;
}
/**
 * @param searchType the searchType to set
 */
public void setSearchType(String searchType) {
	this.searchType = searchType;
}
/**
 * @return the paybuttonType
 */
public String getPaybuttonType() {
	return paybuttonType;
}
/**
 * @param paybuttonType the paybuttonType to set
 */
public void setPaybuttonType(String paybuttonType) {
	this.paybuttonType = paybuttonType;
}

/**
 * @return the paymentItems
 */
public ArrayList getPaymentItems() {
	return paymentItems;
}
/**
 * @param paymentItems the paymentItems to set
 */
public void setPaymentItems(ArrayList paymentItems) {
	this.paymentItems = paymentItems;
}
/**
 * @return the paymentList
 */
public ArrayList getPaymentList() {
	return paymentList;
}
/**
 * @param paymentList the paymentList to set
 */
public void setPaymentList(ArrayList paymentList) {
	this.paymentList = paymentList;
}
/**
 * @return the payDTO
 */
public ChallanDTO getPayDTO() {
	return payDTO;
}
/**
 * @param payDTO the payDTO to set
 */
public void setPayDTO(ChallanDTO payDTO) {
	this.payDTO = payDTO;
}
/**
 * @param index
 * @param vo
 */
public void setPaymentItems(int index, ChallanDTO vo){
	for (; index >= paymentList.size(); paymentList.add(new ChallanDTO()));
	paymentList.set(index, vo);
}

/**
 * @param index 
 * @return PaymentDTO
 */

public ChallanDTO getPaymentItems(int index){
	for(; index >= paymentList.size(); paymentList.add(new ChallanDTO()));
	return (ChallanDTO) paymentList.get(index);
}
/**
 * @return the selectType
 */
public String getSelectType() {
	return selectType;
}
/**
 * @param selectType the selectType to set
 */
public void setSelectType(String selectType) {
	this.selectType = selectType;
}

/**
 * @return the payList
 */
public ArrayList getPayList() {
	return payList;
}
/**
 * @param payList the payList to set
 */
public void setPayList(ArrayList payList) {
	this.payList = payList;
}

/**
 * @return the challancheck
 */
public String getChallancheck() {
	return challancheck;
}
/**
 * @param challancheck the challancheck to set
 */
public void setChallancheck(String challancheck) {
	this.challancheck = challancheck;
}

/**
 * @return the txnNO
 */
public String getTxnNO() {
	return txnNO;
}
/**
 * @param txnNO the txnNO to set
 */
public void setTxnNO(String txnNO) {
	this.txnNO = txnNO;
}
/**
 * @return the totAmt
 */
public double getTotAmt() {
	return totAmt;
}
/**
 * @param totAmt the totAmt to set
 */
public void setTotAmt(double totAmt) {
	this.totAmt = totAmt;
}
/**
 * @return the selectButton
 */
public String getSelectButton() {
	return selectButton;
}
/**
 * @param selectButton the selectButton to set
 */
public void setSelectButton(String selectButton) {
	this.selectButton = selectButton;
}
/**
 * @return the user
 */
public String getUser() {
	return user;
}
/**
 * @param user the user to set
 */
public void setUser(String user) {
	this.user = user;
}
/**
 * @return the buttonConfirm
 */
public String getButtonConfirm() {
	return buttonConfirm;
}
/**
 * @param buttonConfirm the buttonConfirm to set
 */
public void setButtonConfirm(String buttonConfirm) {
	this.buttonConfirm = buttonConfirm;
}
/**
 * @return the diffAmt
 */
public double getDiffAmt() {
	return diffAmt;
}
/**
 * @param diffAmt the diffAmt to set
 */
public void setDiffAmt(double diffAmt) {
	this.diffAmt = diffAmt;
}
/**
 * @return the checkButton
 */
public String getCheckButton() {
	return checkButton;
}
/**
 * @param checkButton the checkButton to set
 */
public void setCheckButton(String checkButton) {
	this.checkButton = checkButton;
}
/**
 * @return the page
 */
public String getPage() {
	return page;
}
/**
 * @param page the page to set
 */
public void setPage(String page) {
	this.page = page;
}
	
}
