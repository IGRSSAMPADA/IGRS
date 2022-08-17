package com.wipro.igrs.expform76b.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class ExpForm76BForm extends ActionForm{
	
	private String billNumber;
	private String grantNumber;
	private String employeeName;
	private String employeeJobType; //option
	private String payWay;
	private String suretyTaken; //option 
	private String amountRequiredforPayment;
	
	private ArrayList districts;
	private ArrayList fiscalYear;
	private ArrayList fiscalMonth;
	private ArrayList accounts;
	private ArrayList majors;
	private ArrayList minors;
	private ArrayList subMajors;
	
	private String selectedDistrict;
	private String selectedYFiscal;
	private String selectedMFiscal;
	private String selectedAccount;
	private String selectedMajor;
	private String selectedMinor;
	private String selectedSubMajor;
	
	
	private String searched=null;
	
	
	
	
	public String getSearched() {
		return searched;
	}
	public void setSearched(String searched) {
		this.searched = searched;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public String getGrantNumber() {
		return grantNumber;
	}
	public void setGrantNumber(String grantNumber) {
		this.grantNumber = grantNumber;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeJobType() {
		return employeeJobType;
	}
	public void setEmployeeJobType(String employeeJobType) {
		this.employeeJobType = employeeJobType;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getSuretyTaken() {
		return suretyTaken;
	}
	public void setSuretyTaken(String suretyTaken) {
		this.suretyTaken = suretyTaken;
	}
	public String getAmountRequiredforPayment() {
		return amountRequiredforPayment;
	}
	public void setAmountRequiredforPayment(String amountRequiredforPayment) {
		this.amountRequiredforPayment = amountRequiredforPayment;
	}
	public ArrayList getDistricts() {
		return districts;
	}
	public void setDistricts(ArrayList districts) {
		this.districts = districts;
	}
	public ArrayList getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(ArrayList fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public ArrayList getFiscalMonth() {
		return fiscalMonth;
	}
	public void setFiscalMonth(ArrayList fiscalMonth) {
		this.fiscalMonth = fiscalMonth;
	}
	public ArrayList getMajors() {
		return majors;
	}
	public void setMajors(ArrayList majors) {
		this.majors = majors;
	}
	public ArrayList getMinors() {
		return minors;
	}
	public void setMinors(ArrayList minors) {
		this.minors = minors;
	}
	public ArrayList getSubMajors() {
		return subMajors;
	}
	public void setSubMajors(ArrayList subMajors) {
		this.subMajors = subMajors;
	}
	public String getSelectedDistrict() {
		return selectedDistrict;
	}
	public void setSelectedDistrict(String selectedDistrict) {
		this.selectedDistrict = selectedDistrict;
	}
	public String getSelectedYFiscal() {
		return selectedYFiscal;
	}
	public void setSelectedYFiscal(String selectedYFiscal) {
		this.selectedYFiscal = selectedYFiscal;
	}
	public String getSelectedMFiscal() {
		return selectedMFiscal;
	}
	public void setSelectedMFiscal(String selectedMFiscal) {
		this.selectedMFiscal = selectedMFiscal;
	}
	public String getSelectedMajor() {
		return selectedMajor;
	}
	public void setSelectedMajor(String selectedMajor) {
		this.selectedMajor = selectedMajor;
	}
	public String getSelectedMinor() {
		return selectedMinor;
	}
	public void setSelectedMinor(String selectedMinor) {
		this.selectedMinor = selectedMinor;
	}
	public String getSelectedSubMajor() {
		return selectedSubMajor;
	}
	public void setSelectedSubMajor(String selectedSubMajor) {
		this.selectedSubMajor = selectedSubMajor;
	}
	public ArrayList getAccounts() {
		return accounts;
	}
	public void setAccounts(ArrayList accounts) {
		this.accounts = accounts;
	}
	public String getSelectedAccount() {
		return selectedAccount;
	}
	public void setSelectedAccount(String selectedAccount) {
		this.selectedAccount = selectedAccount;
	}
	

}
