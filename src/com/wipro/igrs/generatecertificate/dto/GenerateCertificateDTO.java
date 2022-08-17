package com.wipro.igrs.generatecertificate.dto;

import java.io.Serializable;

public class GenerateCertificateDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empID;
	private String empName;
	private String header;
	private String content;
	private String footer;
	private String confirmEmpID;
	private String confirmEmpName;
	private String confirmHdnEmp;
	private String confirmDate ;
	
	public String getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	public String getConfirmEmpID() {
		return confirmEmpID;
	}
	public void setConfirmEmpID(String confirmEmpID) {
		this.confirmEmpID = confirmEmpID;
	}
	public String getConfirmEmpName() {
		return confirmEmpName;
	}
	public void setConfirmEmpName(String confirmEmpName) {
		this.confirmEmpName = confirmEmpName;
	}
	public String getConfirmHdnEmp() {
		return confirmHdnEmp;
	}
	public void setConfirmHdnEmp(String confirmHdnEmp) {
		this.confirmHdnEmp = confirmHdnEmp;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	
	 
	
}
