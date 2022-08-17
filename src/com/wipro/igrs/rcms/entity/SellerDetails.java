package com.wipro.igrs.rcms.entity;

import javax.xml.bind.annotation.XmlElement;

public class SellerDetails {

	
	private String firstName;
	private String middleName;
	private String lastName;
	private String ownershipTypeIdSeller;
	private String deptCode;
	private String fatherName;
	
	public SellerDetails() {
		super();
	}
	
	@XmlElement
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@XmlElement
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	@XmlElement
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@XmlElement
	public String getOwnershipTypeIdSeller() {
		return ownershipTypeIdSeller;
	}
	public void setOwnershipTypeIdSeller(String ownershipTypeIdSeller) {
		this.ownershipTypeIdSeller = ownershipTypeIdSeller;
	}
	@XmlElement
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	@XmlElement
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	
	
}
