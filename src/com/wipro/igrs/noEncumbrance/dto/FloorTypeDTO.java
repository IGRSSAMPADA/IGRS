package com.wipro.igrs.noEncumbrance.dto;

import java.io.Serializable;

public class FloorTypeDTO implements Serializable {
	
	private String floorID;
	private String floorType;
	public String getFloorID() {
		return floorID;
	}
	public void setFloorID(String floorID) {
		this.floorID = floorID;
	}
	public String getFloorType() {
		return floorType;
	}
	public void setFloorType(String floorType) {
		this.floorType = floorType;
	}

}
