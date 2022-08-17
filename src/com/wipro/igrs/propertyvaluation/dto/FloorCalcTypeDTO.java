package com.wipro.igrs.propertyvaluation.dto;

import java.io.Serializable;

public class FloorCalcTypeDTO implements Serializable{

	
	private String floorCalcType;
	private int floorCalcId;
	private String hdnfloorCalcType;
	
	
	
	public String getHdnfloorCalcType() {
		return hdnfloorCalcType;
	}
	public void setHdnfloorCalcType(String hdnfloorCalcType) {
		this.hdnfloorCalcType = hdnfloorCalcType;
	}
	public String getFloorCalcType() {
		return floorCalcType;
	}
	public void setFloorCalcType(String floorCalcType) {
		this.floorCalcType = floorCalcType;
	}
	public int getFloorCalcId() {
		return floorCalcId;
	}
	public void setFloorCalcId(int floorCalcId) {
		this.floorCalcId = floorCalcId;
	}
	

}
