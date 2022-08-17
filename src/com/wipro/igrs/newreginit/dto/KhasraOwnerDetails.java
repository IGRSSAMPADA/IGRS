package com.wipro.igrs.newreginit.dto;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class KhasraOwnerDetails {
	
	
	private String success;
	private String riCircle;	
	private String khasraId;
	private String message;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	
	
	

}
