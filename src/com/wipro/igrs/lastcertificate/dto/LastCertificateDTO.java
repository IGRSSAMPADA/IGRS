package com.wipro.igrs.lastcertificate.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class LastCertificateDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empName;
	private String empID;
	private String empPFNo;
	private String currentDate;
	private String salaryHead;
	private String salaryRate;
	private String pfAuthority;
	private String recoveryName;
	private String recoveryAmt;
	private String recoveryDuration;
	private String deductionDate;
	private String rate;
	private String totalRate;
	private String salary;
	private String componentAmount;
	private String componentName;
	private String remarks;
	
	
	private ArrayList salaryHeadList = new ArrayList();
	private ArrayList empDetails = new ArrayList();
	private ArrayList recoveryDetails = new ArrayList();
	private ArrayList monthlyDeduction = new ArrayList();
	
	public ArrayList getMonthlyDeduction() {
		return monthlyDeduction;
	}
	public void setMonthlyDeduction(ArrayList monthlyDeduction) {
		this.monthlyDeduction = monthlyDeduction;
	}
	public ArrayList getEmpDetails() {
		return empDetails;
	}
	public void setEmpDetails(ArrayList empDetails) {
		this.empDetails = empDetails;
	}
	public ArrayList getRecoveryDetails() {
		return recoveryDetails;
	}
	public void setRecoveryDetails(ArrayList recoveryDetails) {
		this.recoveryDetails = recoveryDetails;
	}
	public String getPfAuthority() {
		return pfAuthority;
	}
	public void setPfAuthority(String pfAuthority) {
		this.pfAuthority = pfAuthority;
	}
	public String getRecoveryName() {
		return recoveryName;
	}
	public void setRecoveryName(String recoveryName) {
		this.recoveryName = recoveryName;
	}
	public String getRecoveryAmt() {
		return recoveryAmt;
	}
	public void setRecoveryAmt(String recoveryAmt) {
		this.recoveryAmt = recoveryAmt;
	}
	public String getRecoveryDuration() {
		return recoveryDuration;
	}
	public void setRecoveryDuration(String recoveryDuration) {
		this.recoveryDuration = recoveryDuration;
	}
	 
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(String totalRate) {
		this.totalRate = totalRate;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	 
	public String getDeductionDate() {
		return deductionDate;
	}
	public void setDeductionDate(String deductionDate) {
		this.deductionDate = deductionDate;
	}
	public String getComponentAmount() {
		return componentAmount;
	}
	public void setComponentAmount(String componentAmount) {
		this.componentAmount = componentAmount;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpPFNo() {
		return empPFNo;
	}
	public void setEmpPFNo(String empPFNo) {
		this.empPFNo = empPFNo;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getSalaryHead() {
		return salaryHead;
	}
	public void setSalaryHead(String salaryHead) {
		this.salaryHead = salaryHead;
	}
	public String getSalaryRate() {
		return salaryRate;
	}
	public void setSalaryRate(String salaryRate) {
		this.salaryRate = salaryRate;
	}
	public ArrayList getSalaryHeadList() {
		return salaryHeadList;
	}
	public void setSalaryHeadList(ArrayList salaryHeadList) {
		this.salaryHeadList = salaryHeadList;
	}
	
	
}
