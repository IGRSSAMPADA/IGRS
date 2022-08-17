package com.wipro.igrs.empDeductions.dto;

public class EmpDTO
{
	private String amount="N/A";
	private String pf_loan="N/A";
	private String paid_emi="N/A";
	private String paid_amount="N/A";
	private String unpaid_emi="N/A";
	private String unpaid_amount="N/A";
	
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the pf_loan
	 */
	public String getPf_loan() {
		return pf_loan;
	}
	/**
	 * @param pf_loan the pf_loan to set
	 */
	public void setPf_loan(String pf_loan) {
		this.pf_loan = pf_loan;
	}
	/**
	 * @return the paid_emi
	 */
	public String getPaid_emi() {
		return paid_emi;
	}
	/**
	 * @param paid_emi the paid_emi to set
	 */
	public void setPaid_emi(String paid_emi) {
		this.paid_emi = paid_emi;
	}
	/**
	 * @return the paid_amount
	 */
	public String getPaid_amount() {
		return paid_amount;
	}
	/**
	 * @param paid_amount the paid_amount to set
	 */
	public void setPaid_amount(String paid_amount) {
		this.paid_amount = paid_amount;
	}
	/**
	 * @return the unpaid_emi
	 */
	public String getUnpaid_emi() {
		return unpaid_emi;
	}
	/**
	 * @param unpaid_emi the unpaid_emi to set
	 */
	public void setUnpaid_emi(String unpaid_emi) {
		this.unpaid_emi = unpaid_emi;
	}
	/**
	 * @return the unpaid_amount
	 */
	public String getUnpaid_amount() {
		return unpaid_amount;
	}
	/**
	 * @param unpaid_amount the unpaid_amount to set
	 */
	public void setUnpaid_amount(String unpaid_amount) {
		this.unpaid_amount = unpaid_amount;
	}
}
