package com.wipro.igrs.newreginit.clrPojo;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Owner")
public class Owner {
	private String partyDetails;
	private String caste;
	private String ownerShare;
	
	@XmlElement
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
	}
	/*public String toString() {
		return partyDetails+":"+caste+":"+ownerShare;
	} */
    
	
}
