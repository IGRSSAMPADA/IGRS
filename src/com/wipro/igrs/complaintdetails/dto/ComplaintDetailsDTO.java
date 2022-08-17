package com.wipro.igrs.complaintdetails.dto;

import java.io.Serializable;

public class ComplaintDetailsDTO implements Serializable{

	private String empName;
	private String empDesignation;
	private String office;
	
	private String ddoName;
	private String ddoDesignation;
	
	private String head;

	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getEmpDesignation() {
		return empDesignation;
	}
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
	public String getDdoName() {
		return ddoName;
	}
	public void setDdoName(String ddoName) {
		this.ddoName = ddoName;
	}
	public String getDdoDesignation() {
		return ddoDesignation;
	}
	public void setDdoDesignation(String ddoDesignation) {
		this.ddoDesignation = ddoDesignation;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}

}
