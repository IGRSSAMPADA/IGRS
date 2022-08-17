package com.wipro.igrs.noEncumbrance.dto;

import java.io.Serializable;

public class FloorDetailsDTO  implements Serializable{
	private String floorTypeId;
	private String floorRowID;
	private String radioId;
	private String propId;
	private String buildingSize;
	private String buildingArea;
	private String floorLabel;
    private String	buildingSizeBreadth;
	public String getBuildingSizeBreadth() {
		return buildingSizeBreadth;
	}
	public void setBuildingSizeBreadth(String buildingSizeBreadth) {
		this.buildingSizeBreadth = buildingSizeBreadth;
	}
	public String getFloorLabel() {
		return floorLabel;
	}
	public void setFloorLabel(String floorLabel) {
		this.floorLabel = floorLabel;
	}
	public String getBuildingSize() {
		return buildingSize;
	}
	public void setBuildingSize(String buildingSize) {
		this.buildingSize = buildingSize;
	}
	public String getBuildingArea() {
		return buildingArea;
	}
	public void setBuildingArea(String buildingArea) {
		this.buildingArea = buildingArea;
	}
	public String getFloorTypeId() {
		return floorTypeId;
	}
	public void setFloorTypeId(String floorTypeId) {
		this.floorTypeId = floorTypeId;
	}
	public String getFloorRowID() {
		return floorRowID;
	}
	public void setFloorRowID(String floorRowID) {
		this.floorRowID = floorRowID;
	}
	public String getRadioId() {
		return radioId;
	}
	public void setRadioId(String radioId) {
		this.radioId = radioId;
	}
	public String getPropId() {
		return propId;
	}
	public void setPropId(String propId) {
		this.propId = propId;
	}
	

}
