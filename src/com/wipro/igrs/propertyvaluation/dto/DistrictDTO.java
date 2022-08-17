package com.wipro.igrs.propertyvaluation.dto;

import java.io.Serializable;

public class DistrictDTO implements Serializable {
	private Integer districtID;
	/**
	 * @author Rishab
	 */
	
	private String district;
	/**
	 * @author Rishab
	 */

	public Integer getDistrictID() {
		return districtID;
	}

	public void setDistrictID(Integer districtID) {
		this.districtID = districtID;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}




}
