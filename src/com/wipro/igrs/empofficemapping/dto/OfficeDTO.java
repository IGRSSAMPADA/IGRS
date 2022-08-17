package com.wipro.igrs.empofficemapping.dto;

import java.io.Serializable;

public class OfficeDTO implements Serializable{
	private String officeId;
	private String OfficeName;
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getOfficeName() {
		return OfficeName;
	}
	public void setOfficeName(String officeName) {
		OfficeName = officeName;
	}
}
