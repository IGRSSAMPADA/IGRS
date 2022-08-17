/**
 * ServiceProviderAccountDTO.java
 */


package com.wipro.igrs.sp.dto;

/**
 * @author root
 * 
 */
public class ServiceProviderAccountDTO {

	private String licencetxn;

	private String licencenumber;

	private String place;

	private String accountantname;

	private String accountbalance;

	private String payamount;

	private String fromdate;

	private String todate;

	private String paymentdate;

	private String transactionno;

	private String paymentamount;

	private String paymenttypename;
	
	private String displayoption;
	
	private String paymonth;
	
	private String payyear;
	
	private String txnid;
	
	//added by shruti
	private String txnpurpose;
	
	private String spcomm; 
	
	private String txnstatus; 
	
	private String srNo;
	
	private String openingBal;
	
	
	public String getOpeningBal() {
		return openingBal;
	}

	public void setOpeningBal(String openingBal) {
		this.openingBal = openingBal;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getTxnstatus() {
		return txnstatus;
	}

	public void setTxnstatus(String txnstatus) {
		this.txnstatus = txnstatus;
	}

	public String getSpcomm() {
		return spcomm;
	}

	public void setSpcomm(String spcomm) {
		this.spcomm = spcomm;
	}

	public String getTxnpurpose() {
		return txnpurpose;
	}

	public void setTxnpurpose(String txnpurpose) {
		this.txnpurpose = txnpurpose;
	}

	public String getTxnid() {
		return txnid;
	}

	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}

	/**
	 * @return the paymonth
	 */
	public String getPaymonth() {
		return paymonth;
	}

	/**
	 * @param paymonth the paymonth to set
	 */
	public void setPaymonth(String paymonth) {
		this.paymonth = paymonth;
	}

	/**
	 * @return the payyear
	 */
	public String getPayyear() {
		return payyear;
	}

	/**
	 * @param payyear the payyear to set
	 */
	public void setPayyear(String payyear) {
		this.payyear = payyear;
	}

	/**
	 * @return the displayoption
	 */
	public String getDisplayoption() {
		return displayoption;
	}

	/**
	 * @param displayoption the displayoption to set
	 */
	public void setDisplayoption(String displayoption) {
		this.displayoption = displayoption;
	}

	/**
	 * @return the fromdate
	 */
	public String getFromdate() {
		return fromdate;
	}

	/**
	 * @param fromdate
	 *            the fromdate to set
	 */
	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	/**
	 * @return the todate
	 */
	public String getTodate() {
		return todate;
	}

	/**
	 * @param todate
	 *            the todate to set
	 */
	public void setTodate(String todate) {
		this.todate = todate;
	}

	/**
	 * @return the payamount
	 */
	public String getPayamount() {
		return payamount;
	}

	/**
	 * @param payamount
	 *            the payamount to set
	 */
	public void setPayamount(String payamount) {
		this.payamount = payamount;
	}

	/**
	 * @return the accountantname
	 */
	public String getAccountantname() {
		return accountantname;
	}

	/**
	 * @param accountantname
	 *            the accountantname to set
	 */
	public void setAccountantname(String accountantname) {
		this.accountantname = accountantname;
	}

	/**
	 * @return the accountbalance
	 */
	public String getAccountbalance() {
		return accountbalance;
	}

	/**
	 * @param accountbalance
	 *            the accountbalance to set
	 */
	public void setAccountbalance(String accountbalance) {
		this.accountbalance = accountbalance;
	}

	/**
	 * @return the licencetxn
	 */
	public String getLicencetxn() {
		return licencetxn;
	}

	/**
	 * @param licencetxn
	 *            the licencetxn to set
	 */
	public void setLicencetxn(String licencetxn) {
		this.licencetxn = licencetxn;
	}

	/**
	 * @return the licencenumber
	 */
	public String getLicencenumber() {
		return licencenumber;
	}

	/**
	 * @param licencenumber
	 *            the licencenumber to set
	 */
	public void setLicencenumber(String licencenumber) {
		this.licencenumber = licencenumber;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place
	 *            the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @return the paymentdate
	 */
	public String getPaymentdate() {
		return paymentdate;
	}

	/**
	 * @param paymentdate
	 *            the paymentdate to set
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
	 * @param transactionno
	 *            the transactionno to set
	 */
	public void setTransactionno(String transactionno) {
		this.transactionno = transactionno;
	}

	/**
	 * @return the paymentamount
	 */
	public String getPaymentamount() {
		return paymentamount;
	}

	/**
	 * @param paymentamount
	 *            the paymentamount to set
	 */
	public void setPaymentamount(String paymentamount) {
		this.paymentamount = paymentamount;
	}

	/**
	 * @return the paymenttypename
	 */
	public String getPaymenttypename() {
		return paymenttypename;
	}

	/**
	 * @param paymenttypename
	 *            the paymenttypename to set
	 */
	public void setPaymenttypename(String paymenttypename) {
		this.paymenttypename = paymenttypename;
	}
}
