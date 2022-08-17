package com.wipro.igrs.newreginit.clrPojo;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ownerDetailsMain")
public class KhasraOwnerDetails {
	
	
	private String riCircle;	
	private String khasraId;
	private OwnerDetails ownerDetails;
	
  @XmlElement
	public String getRiCircle() {
		return riCircle;
	}
	
	public void setRiCircle(String riCircle) {
		this.riCircle = riCircle;
	}
	@XmlElement
	public String getKhasraId() {
		return khasraId;
	}
	public void setKhasraId(String khasraId) {
		this.khasraId = khasraId;
	}
    public void setOwnerDetails(OwnerDetails ownerDetails) {
		this.ownerDetails = ownerDetails;
	}
    @XmlElement(name="ownerDetails")
    public OwnerDetails getOwnerDetails() {
		return ownerDetails;
	}
	
	
	

}
