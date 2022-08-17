package com.wipro.igrs.expmptc28.dto;

import java.io.Serializable;

public class DistrictDTO implements Serializable{
	private String districtId;
	private String districtName;
	
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
}
