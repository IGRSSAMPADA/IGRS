package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class OwnerDetlsDTO   implements Serializable{
	
	private String ownerId;
	private String ownerShare;
	private String ownername;
	private String fatherName;
	private String ownerType;
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerShare() {
		return ownerShare;
	}
	public void setOwnerShare(String ownerShare) {
		this.ownerShare = ownerShare;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

}
