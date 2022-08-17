package com.wipro.igrs.expmptc28.dto;

import java.io.Serializable;

public class EmpDetailsDTO implements Serializable{
	private String empDesignationName;
	private String empOfficeName;
	private String empActualPay;
	
	public String getEmpDesignationName() {
		return empDesignationName;
	}
	public void setEmpDesignationName(String empDesignationName) {
		this.empDesignationName = empDesignationName;
	}
	
	public String getEmpOfficeName() {
		return empOfficeName;
	}
	public void setEmpOfficeName(String empOfficeName) {
		this.empOfficeName = empOfficeName;
	}
	public String getEmpActualPay() {
		return empActualPay;
	}
	public void setEmpActualPay(String empActualPay) {
		this.empActualPay = empActualPay;
	}
}
