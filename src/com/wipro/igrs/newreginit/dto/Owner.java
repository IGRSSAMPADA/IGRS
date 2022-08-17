package com.wipro.igrs.newreginit.dto;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Owner")
public class Owner {
	/*private String partyDetails;
	private String caste;
	private String ownerShare;*/
	
	private String ownerName;
	private String fatherName;
	private String address;
	private String ownerShare;
	private String ownershiptype;
	private String ownerCaste;
	private String columnno;
	
	@XmlElement
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	@XmlElement
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	@XmlElement
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@XmlElement
	public String getOwnerShare() {
		return ownerShare;
	}
	public void setOwnerShare(String ownerShare) {
		this.ownerShare = ownerShare;
	}
	@XmlElement
	public String getOwnershiptype() {
		return ownershiptype;
	}
	public void setOwnershiptype(String ownershiptype) {
		this.ownershiptype = ownershiptype;
	}
	@XmlElement
	public String getOwnerCaste() {
		return ownerCaste;
	}
	public void setOwnerCaste(String ownerCaste) {
		this.ownerCaste = ownerCaste;
	}
	@XmlElement
	public String getColumnno() {
		return columnno;
	}
	public void setColumnno(String columnno) {
		this.columnno = columnno;
	}
	
	
	/*@XmlElement
	public String getPartyDetails() {
		return partyDetails;
	}
	public void setPartyDetails(String partyDetails) {
		this.partyDetails = partyDetails;
	}
	

	@XmlElement
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	@XmlElement
	public String getOwnerShare() {
		return ownerShare;
	}
	public void setOwnerShare(String ownerShare) {
		this.ownerShare = ownerShare;
	}*/
	/*public String toString() {
		return partyDetails+":"+caste+":"+ownerShare;
	} */

	
}
