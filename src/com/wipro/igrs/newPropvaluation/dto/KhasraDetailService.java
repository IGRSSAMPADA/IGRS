package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "response")
public class KhasraDetailService implements Serializable{
	private String success;
	private String message;
	private String khasraId;
	private ArrayList<LandParcelDetailDTO> landParcelDetails;
	@XmlElement
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	@XmlElement
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@XmlElement
	public String getKhasraId() {
		return khasraId;
	}
	public void setKhasraId(String khasraId) {
		this.khasraId = khasraId;
	}
	@XmlElement
	public ArrayList<LandParcelDetailDTO> getLandParcelDetails() {
		return landParcelDetails;
	}
	public void setLandParcelDetails(ArrayList<LandParcelDetailDTO> landParcelDetails) {
		this.landParcelDetails = landParcelDetails;
	}
	
}
