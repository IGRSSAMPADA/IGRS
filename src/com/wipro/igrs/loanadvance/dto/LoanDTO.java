/**
 * LoanDTO.java
 */


package com.wipro.igrs.loanadvance.dto;


import java.io.Serializable;


/**
 * @author jagadish
 * 
 */
public class LoanDTO implements Serializable {
	private String	loanid;

	private String	loantype;

	private String	loanamount;

	private String	loaninterestrate;

	private String	loaninteresttype;

	private String	loanmaxamount;

	private String	loanminamount;

	private String	loandescription;

	private String	loanstatus;

	private String	loanmininstalment;

	private String	loanmaxinstalment;

	private String	fixedloanamount;

	private String	fixedloaninstallments;

	private String	hiddenloantype;

	private String	userinstallment;

	private String	loantxnid;

	private String	empid;

	private String	comments;

	private String	requestedDate;

	private String	paidAmount;

	private String	pendingAmount;

	private String	paidinstallments;

	private String	pendinginstallments;

	private String	approverid;

	/**
	 * @return the approverid
	 */
	public String getApproverid() {
		return approverid;
	}

	/**
	 * @param approverid
	 *            the approverid to set
	 */
	public void setApproverid(String approverid) {
		this.approverid = approverid;
	}

	/**
	 * @return the paidAmount
	 */
	public String getPaidAmount() {
		return paidAmount;
	}

	/**
	 * @param paidAmount
	 *            the paidAmount to set
	 */
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	/**
	 * @return the pendingAmount
	 */
	public String getPendingAmount() {
		return pendingAmount;
	}

	/**
	 * @param pendingAmount
	 *            the pendingAmount to set
	 */
	public void setPendingAmount(String pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	/**
	 * @return the paidinstallments
	 */
	public String getPaidinstallments() {
		return paidinstallments;
	}

	/**
	 * @param paidinstallments
	 *            the paidinstallments to set
	 */
	public void setPaidinstallments(String paidinstallments) {
		this.paidinstallments = paidinstallments;
	}

	/**
	 * @return the pendinginstallments
	 */
	public String getPendinginstallments() {
		return pendinginstallments;
	}

	/**
	 * @param pendinginstallments
	 *            the pendinginstallments to set
	 */
	public void setPendinginstallments(String pendinginstallments) {
		this.pendinginstallments = pendinginstallments;
	}

	/**
	 * @return the requestedDate
	 */
	public String getRequestedDate() {
		return requestedDate;
	}

	/**
	 * @param requestedDate
	 *            the requestedDate to set
	 */
	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the empid
	 */
	public String getEmpid() {
		return empid;
	}

	/**
	 * @param empid
	 *            the empid to set
	 */
	public void setEmpid(String empid) {
		this.empid = empid;
	}

	/**
	 * @return the loantxnid
	 */
	public String getLoantxnid() {
		return loantxnid;
	}

	/**
	 * @param loantxnid
	 *            the loantxnid to set
	 */
	public void setLoantxnid(String loantxnid) {
		this.loantxnid = loantxnid;
	}

	/**
	 * @return the userinstallment
	 */
	public String getUserinstallment() {
		return userinstallment;
	}

	/**
	 * @param userinstallment
	 *            the userinstallment to set
	 */
	public void setUserinstallment(String userinstallment) {
		this.userinstallment = userinstallment;
	}

	/**
	 * @return the hiddenloantype
	 */
	public String getHiddenloantype() {
		return hiddenloantype;
	}

	/**
	 * @param hiddenloantype
	 *            the hiddenloantype to set
	 */
	public void setHiddenloantype(String hiddenloantype) {
		this.hiddenloantype = hiddenloantype;
	}

	/**
	 * @return the fixedloanamount
	 */
	public String getFixedloanamount() {
		return fixedloanamount;
	}

	/**
	 * @param fixedloanamount
	 *            the fixedloanamount to set
	 */
	public void setFixedloanamount(String fixedloanamount) {
		this.fixedloanamount = fixedloanamount;
	}

	/**
	 * @return the loanid
	 */
	public String getLoanid() {
		return loanid;
	}

	/**
	 * @param loanid
	 *            the loanid to set
	 */
	public void setLoanid(String loanid) {
		this.loanid = loanid;
	}

	/**
	 * @return the loantype
	 */
	public String getLoantype() {
		return loantype;
	}

	/**
	 * @param loantype
	 *            the loantype to set
	 */
	public void setLoantype(String loantype) {
		this.loantype = loantype;
	}

	/**
	 * @return the loanamount
	 */
	public String getLoanamount() {
		return loanamount;
	}

	/**
	 * @param loanamount
	 *            the loanamount to set
	 */
	public void setLoanamount(String loanamount) {
		this.loanamount = loanamount;
	}

	/**
	 * @return the loaninterestrate
	 */
	public String getLoaninterestrate() {
		return loaninterestrate;
	}

	/**
	 * @param loaninterestrate
	 *            the loaninterestrate to set
	 */
	public void setLoaninterestrate(String loaninterestrate) {
		this.loaninterestrate = loaninterestrate;
	}

	/**
	 * @return the loaninteresttype
	 */
	public String getLoaninteresttype() {
		return loaninteresttype;
	}

	/**
	 * @param loaninteresttype
	 *            the loaninteresttype to set
	 */
	public void setLoaninteresttype(String loaninteresttype) {
		this.loaninteresttype = loaninteresttype;
	}

	/**
	 * @return the loanmaxamount
	 */
	public String getLoanmaxamount() {
		return loanmaxamount;
	}

	/**
	 * @param loanmaxamount
	 *            the loanmaxamount to set
	 */
	public void setLoanmaxamount(String loanmaxamount) {
		this.loanmaxamount = loanmaxamount;
	}

	/**
	 * @return the loanminamount
	 */
	public String getLoanminamount() {
		return loanminamount;
	}

	/**
	 * @param loanminamount
	 *            the loanminamount to set
	 */
	public void setLoanminamount(String loanminamount) {
		this.loanminamount = loanminamount;
	}

	/**
	 * @return the loandescription
	 */
	public String getLoandescription() {
		return loandescription;
	}

	/**
	 * @param loandescription
	 *            the loandescription to set
	 */
	public void setLoandescription(String loandescription) {
		this.loandescription = loandescription;
	}

	/**
	 * @return the loanstatus
	 */
	public String getLoanstatus() {
		return loanstatus;
	}

	/**
	 * @param loanstatus
	 *            the loanstatus to set
	 */
	public void setLoanstatus(String loanstatus) {
		this.loanstatus = loanstatus;
	}

	/**
	 * @return the loanmininstalment
	 */
	public String getLoanmininstalment() {
		return loanmininstalment;
	}

	/**
	 * @param loanmininstalment
	 *            the loanmininstalment to set
	 */
	public void setLoanmininstalment(String loanmininstalment) {
		this.loanmininstalment = loanmininstalment;
	}

	/**
	 * @return the loanmaxinstalment
	 */
	public String getLoanmaxinstalment() {
		return loanmaxinstalment;
	}

	/**
	 * @param loanmaxinstalment
	 *            the loanmaxinstalment to set
	 */
	public void setLoanmaxinstalment(String loanmaxinstalment) {
		this.loanmaxinstalment = loanmaxinstalment;
	}

	/**
	 * @return the fixedloaninstallments
	 */
	public String getFixedloaninstallments() {
		return fixedloaninstallments;
	}

	/**
	 * @param fixedloaninstallments
	 *            the fixedloaninstallments to set
	 */
	public void setFixedloaninstallments(String fixedloaninstallments) {
		this.fixedloaninstallments = fixedloaninstallments;
	}

}
