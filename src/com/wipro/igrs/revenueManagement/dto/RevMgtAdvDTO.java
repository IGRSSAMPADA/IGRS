/**
 * 
 */
package com.wipro.igrs.revenueManagement.dto;

/**
 * @author SHREERAJ KHARE
 *
 */
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class RevMgtAdvDTO {
	
	//for search page
	 
	  private String licenseNo;
	  private String radio;
	  private String licenseTo;
	  private String licenseFrom;
	  private String spType;
	  private ArrayList licenseList = new ArrayList();	  
	  private String combDet;
	  private String spTypeId;
	  private String indvName;
	  private String accTypeRadio;
	  private String paymentdate;
	  private String transactionno;
	  private String paymenttypename;
	  private String txnstatus;
	  private String paymentamount;
	  private String txnpurpose;
	  private String accountbalance;
	  private String spcomm;
	  private ArrayList eStampList = new ArrayList();	
private int listFound;
	  
	  
	  
	  
	
	
	public int getListFound() {
	return listFound;
}
public void setListFound(int listFound) {
	this.listFound = listFound;
}
	/**
	 * @return the eStampList
	 */
	public ArrayList geteStampList() {
		return eStampList;
	}
	/**
	 * @param eStampList the eStampList to set
	 */
	public void seteStampList(ArrayList eStampList) {
		this.eStampList = eStampList;
	}
	/**
	 * @return the paymentdate
	 */
	public String getPaymentdate() {
		return paymentdate;
	}
	/**
	 * @param paymentdate the paymentdate to set
	 */
	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}
	/**
	 * @return the transactionno
	 */
	public String getTransactionno() {
		return transactionno;
	}
	/**
	 * @param transactionno the transactionno to set
	 */
	public void setTransactionno(String transactionno) {
		this.transactionno = transactionno;
	}
	/**
	 * @return the paymenttypename
	 */
	public String getPaymenttypename() {
		return paymenttypename;
	}
	/**
	 * @param paymenttypename the paymenttypename to set
	 */
	public void setPaymenttypename(String paymenttypename) {
		this.paymenttypename = paymenttypename;
	}
	/**
	 * @return the txnstatus
	 */
	public String getTxnstatus() {
		return txnstatus;
	}
	/**
	 * @param txnstatus the txnstatus to set
	 */
	public void setTxnstatus(String txnstatus) {
		this.txnstatus = txnstatus;
	}
	/**
	 * @return the paymentamount
	 */
	public String getPaymentamount() {
		return paymentamount;
	}
	/**
	 * @param paymentamount the paymentamount to set
	 */
	public void setPaymentamount(String paymentamount) {
		this.paymentamount = paymentamount;
	}
	/**
	 * @return the txnpurpose
	 */
	public String getTxnpurpose() {
		return txnpurpose;
	}
	/**
	 * @param txnpurpose the txnpurpose to set
	 */
	public void setTxnpurpose(String txnpurpose) {
		this.txnpurpose = txnpurpose;
	}
	/**
	 * @return the accountbalance
	 */
	public String getAccountbalance() {
		return accountbalance;
	}
	/**
	 * @param accountbalance the accountbalance to set
	 */
	public void setAccountbalance(String accountbalance) {
		this.accountbalance = accountbalance;
	}
	/**
	 * @return the spcomm
	 */
	public String getSpcomm() {
		return spcomm;
	}
	/**
	 * @param spcomm the spcomm to set
	 */
	public void setSpcomm(String spcomm) {
		this.spcomm = spcomm;
	}
	/**
	 * @return the accTypeRadio
	 */
	public String getAccTypeRadio() {
		return accTypeRadio;
	}
	/**
	 * @param accTypeRadio the accTypeRadio to set
	 */
	public void setAccTypeRadio(String accTypeRadio) {
		this.accTypeRadio = accTypeRadio;
	}
	/**
	 * @return the indvName
	 */
	public String getIndvName() {
		return indvName;
	}
	/**
	 * @param indvName the indvName to set
	 */
	public void setIndvName(String indvName) {
		this.indvName = indvName;
	}
	/**
	 * @return the spTypeId
	 */
	public String getSpTypeId() {
		return spTypeId;
	}
	/**
	 * @param spTypeId the spTypeId to set
	 */
	public void setSpTypeId(String spTypeId) {
		this.spTypeId = spTypeId;
	}
	/**
	 * @return the combDet
	 */
	public String getCombDet() {
		return combDet;
	}
	/**
	 * @param combDet the combDet to set
	 */
	public void setCombDet(String combDet) {
		this.combDet = combDet;
	}	
	/**
	 * @return the licenseList
	 */
	public ArrayList getLicenseList() {
		return licenseList;
	}
	/**
	 * @param licenseList the licenseList to set
	 */
	public void setLicenseList(ArrayList licenseList) {
		this.licenseList = licenseList;
	}
	/**
	 * @return the licenseTo
	 */
	public String getLicenseTo() {
		return licenseTo;
	}
	/**
	 * @param licenseTo the licenseTo to set
	 */
	public void setLicenseTo(String licenseTo) {
		this.licenseTo = licenseTo;
	}
	/**
	 * @return the licenseFrom
	 */
	public String getLicenseFrom() {
		return licenseFrom;
	}
	/**
	 * @param licenseFrom the licenseFrom to set
	 */
	public void setLicenseFrom(String licenseFrom) {
		this.licenseFrom = licenseFrom;
	}
	/**
	 * @return the spType
	 */
	public String getSpType() {
		return spType;
	}
	/**
	 * @param spType the spType to set
	 */
	public void setSpType(String spType) {
		this.spType = spType;
	}
	/**
	 * @return the licenseNo
	 */
	public String getLicenseNo() {
		return licenseNo;
	}
	/**
	 * @param licenseNo the licenseNo to set
	 */
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	/**
	 * @return the radio
	 */
	public String getRadio() {
		return radio;
	}
	/**
	 * @param radio the radio to set
	 */
	public void setRadio(String radio) {
		this.radio = radio;
	}
	

}
