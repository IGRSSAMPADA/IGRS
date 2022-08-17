/**
 * 
 */
package com.wipro.igrs.payments.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.wipro.igrs.payments.dto.ChallanDTO;


/**
 * ===========================================================================
 * File           :   PaymentDTO.java
 * Description    :   Represents the Payment DTO Class. 
 * Author         :   Karteek P
 * Created Date   :   March 18, 2008

 * ===========================================================================
 */
public class PaymentDTO implements Serializable {
  
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String addButton;
   private String page;
   
   private String receiptID;
   private double amount;
   private String txnNO;
   private double totAmt;
   private String user;
   private String challancheck;
   private String ccheck;
   private String searchType;
   private String paybuttonType;
   private String buttonConfirm;
   private double diffAmt;
   private String delrow;
   private String actDelRow;
   private String delbutton;
   private String spLicenseNo;
   private String spCmsn;
   private String spAmt;
   private String spProcess;
   private String spFnlAmt;
   private String spLicenseTxnid;
   private String spName;
   private String selectButton = "";
   private String checkButton = "";
   private String selectType;
   private String searchRsptID;
   private String cashDate;
   private String office;
   private String payTxnSuccId;
   private String depositorName;
   ArrayList rsptList;
   private ArrayList paymentItems = new ArrayList();
   private ArrayList paymentList = new ArrayList();
   private ChallanDTO payDTO = new ChallanDTO();
   private ArrayList payList = new ArrayList();
   private String name;
   private String value;
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
public String getPayTxnSuccId() {
	return payTxnSuccId;
}
public void setPayTxnSuccId(String payTxnSuccId) {
	this.payTxnSuccId = payTxnSuccId;
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
/**
 * @return the ccheck
 */
public String getCcheck() {
	return ccheck;
}
/**
 * @param ccheck the ccheck to set
 */
public void setCcheck(String ccheck) {
	this.ccheck = ccheck;
}
/**
 * @return the delrow
 */
public String getDelrow() {
	return delrow;
}
/**
 * @param delrow the delrow to set
 */
public void setDelrow(String delrow) {
	this.delrow = delrow;
}
/**
 * @return the actDelRow
 */
public String getActDelRow() {
	return actDelRow;
}
/**
 * @param actDelRow the actDelRow to set
 */
public void setActDelRow(String actDelRow) {
	this.actDelRow = actDelRow;
}
/**
 * @return the delbutton
 */
public String getDelbutton() {
	return delbutton;
}
/**
 * @param delbutton the delbutton to set
 */
public void setDelbutton(String delbutton) {
	this.delbutton = delbutton;
}
/**
 * @return the spLicenseNo
 */
public String getSpLicenseNo() {
	return spLicenseNo;
}
/**
 * @param spLicenseNo the spLicenseNo to set
 */
public void setSpLicenseNo(String spLicenseNo) {
	this.spLicenseNo = spLicenseNo;
}
/**
 * @return the spCmsn
 */
public String getSpCmsn() {
	return spCmsn;
}
/**
 * @param spCmsn the spCmsn to set
 */
public void setSpCmsn(String spCmsn) {
	this.spCmsn = spCmsn;
}
/**
 * @return the spAmt
 */
public String getSpAmt() {
	return spAmt;
}
/**
 * @param spAmt the spAmt to set
 */
public void setSpAmt(String spAmt) {
	this.spAmt = spAmt;
}
/**
 * @return the spProcess
 */
public String getSpProcess() {
	return spProcess;
}
/**
 * @param spProcess the spProcess to set
 */
public void setSpProcess(String spProcess) {
	this.spProcess = spProcess;
}
/**
 * @return the spFnlAmt
 */
public String getSpFnlAmt() {
	return spFnlAmt;
}
/**
 * @param spFnlAmt the spFnlAmt to set
 */
public void setSpFnlAmt(String spFnlAmt) {
	this.spFnlAmt = spFnlAmt;
}
/**
 * @return the spLicenseTxnid
 */
public String getSpLicenseTxnid() {
	return spLicenseTxnid;
}
/**
 * @param spLicenseTxnid the spLicenseTxnid to set
 */
public void setSpLicenseTxnid(String spLicenseTxnid) {
	this.spLicenseTxnid = spLicenseTxnid;
}


public String getSpName() {
	return spName;
}
public void setSpName(String spName) {
	this.spName = spName;
}
public String getSearchRsptID() {
    return searchRsptID;
}
public void setSearchRsptID(String searchRsptID) {
    this.searchRsptID = searchRsptID;
}
public String getCashDate() {
    return cashDate;
}
public void setCashDate(String cashDate) {
    this.cashDate = cashDate;
}
public String getOffice() {
	return office;
}
public void setOffice(String office) {
	this.office = office;
}

public String getDepositorName() {
	return depositorName;
}
public void setDepositorName(String depositorName) {
	this.depositorName = depositorName;
}
public ArrayList getRsptList() {
    return rsptList;
}
public void setRsptList(ArrayList rsptList) {
    this.rsptList = rsptList;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}



	
}
