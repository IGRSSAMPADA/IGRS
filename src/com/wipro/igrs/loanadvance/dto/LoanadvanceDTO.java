/*
 * LoanadvanceDTO.java
 */

package com.wipro.igrs.loanadvance.dto;


import java.io.Serializable;


public class LoanadvanceDTO implements Serializable {
	private String	advancetypeid;

	private String	advancetypename;

	private String	advancedescription;

	private String	advancestatus;

	private String	advanceamount;

	private String	installmentno;

	private String	gradeid;

	private String	advancetype;

	private String	empid;

	private String	advancetxnid;

	private String	remarks;

	private String	createdDate;

	private String	paidInsallments;

	private String	paidAmounts;

	private String	pendingInstallments;

	private String	pendingAmounts;

	private String	advAmount;
	private String interestRate;
	private String condition;
	private String[] applicableClass={};
	private String empClass;
	public String getEmpClass() {
		return empClass;
	}

	public void setEmpClass(String empClass) {
		this.empClass = empClass;
	}

	private String salaryLimitation;

	public String getSalaryLimitation() {
		return salaryLimitation;
	}

	public void setSalaryLimitation(String salaryLimitation) {
		this.salaryLimitation = salaryLimitation;
	}


	public String[] getApplicableClass() {
		return applicableClass;
	}

	public void setApplicableClass(String[] applicableClass) {
		this.applicableClass = applicableClass;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the advAmount
	 */
	public String getAdvAmount() {
		return advAmount;
	}

	/**
	 * @param advAmount
	 *            the advAmount to set
	 */
	public void setAdvAmount(String advAmount) {
		this.advAmount = advAmount;
	}

	/**
	 * @return the paidInsallments
	 */
	public String getPaidInsallments() {
		return paidInsallments;
	}

	/**
	 * @param paidInsallments
	 *            the paidInsallments to set
	 */
	public void setPaidInsallments(String paidInsallments) {
		this.paidInsallments = paidInsallments;
	}

	/**
	 * @return the paidAmounts
	 */
	public String getPaidAmounts() {
		return paidAmounts;
	}

	/**
	 * @param paidAmounts
	 *            the paidAmounts to set
	 */
	public void setPaidAmounts(String paidAmounts) {
		this.paidAmounts = paidAmounts;
	}

	/**
	 * @return the pendingInstallments
	 */
	public String getPendingInstallments() {
		return pendingInstallments;
	}

	/**
	 * @param pendingInstallments
	 *            the pendingInstallments to set
	 */
	public void setPendingInstallments(String pendingInstallments) {
		this.pendingInstallments = pendingInstallments;
	}

	/**
	 * @return the pendingAmounts
	 */
	public String getPendingAmounts() {
		return pendingAmounts;
	}

	/**
	 * @param pendingAmounts
	 *            the pendingAmounts to set
	 */
	public void setPendingAmounts(String pendingAmounts) {
		this.pendingAmounts = pendingAmounts;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the advancetypeid
	 */
	public String getAdvancetypeid() {
		return advancetypeid;
	}

	/**
	 * @param advancetypeid
	 *            the advancetypeid to set
	 */
	public void setAdvancetypeid(String advancetypeid) {
		this.advancetypeid = advancetypeid;
	}

	/**
	 * @return the advancetypename
	 */
	public String getAdvancetypename() {
		return advancetypename;
	}

	/**
	 * @param advancetypename
	 *            the advancetypename to set
	 */
	public void setAdvancetypename(String advancetypename) {
		this.advancetypename = advancetypename;
	}

	/**
	 * @return the advancedescription
	 */
	public String getAdvancedescription() {
		return advancedescription;
	}

	/**
	 * @param advancedescription
	 *            the advancedescription to set
	 */
	public void setAdvancedescription(String advancedescription) {
		this.advancedescription = advancedescription;
	}

	/**
	 * @return the advancestatus
	 */
	public String getAdvancestatus() {
		return advancestatus;
	}

	/**
	 * @param advancestatus
	 *            the advancestatus to set
	 */
	public void setAdvancestatus(String advancestatus) {
		this.advancestatus = advancestatus;
	}

	/**
	 * @return the advanceamount
	 */
	public String getAdvanceamount() {
		return advanceamount;
	}

	/**
	 * @param advanceamount
	 *            the advanceamount to set
	 */
	public void setAdvanceamount(String advanceamount) {
		this.advanceamount = advanceamount;
	}

	/**
	 * @return the installmentno
	 */
	public String getInstallmentno() {
		return installmentno;
	}

	/**
	 * @param installmentno
	 *            the installmentno to set
	 */
	public void setInstallmentno(String installmentno) {
		this.installmentno = installmentno;
	}

	/**
	 * @return the gradeid
	 */
	public String getGradeid() {
		return gradeid;
	}

	/**
	 * @param gradeid
	 *            the gradeid to set
	 */
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}

	/**
	 * @return the advancetype
	 */
	public String getAdvancetype() {
		return advancetype;
	}

	/**
	 * @param advancetype
	 *            the advancetype to set
	 */
	public void setAdvancetype(String advancetype) {
		this.advancetype = advancetype;
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
	 * @return the advancetxnid
	 */
	public String getAdvancetxnid() {
		return advancetxnid;
	}

	/**
	 * @param advancetxnid
	 *            the advancetxnid to set
	 */
	public void setAdvancetxnid(String advancetxnid) {
		this.advancetxnid = advancetxnid;
	}

}
