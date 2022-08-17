package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



public class VillageKhasraDTO implements Serializable{

	private String khasraNo;
	private String khasraId;
	
	@XmlElement(name="khasraNo")
	public String getKhasraNo() {
		return khasraNo;
	}
	public void setKhasraNo(String khasraNo) {
		this.khasraNo = khasraNo;
	}
	@XmlElement(name="khasraId")
	public String getKhasraId() {
		return khasraId;
	}
	public void setKhasraId(String khasraId) {
		this.khasraId = khasraId;
	}
}
