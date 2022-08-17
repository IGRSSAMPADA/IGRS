package com.wipro.igrs.newreginit.clrPojo;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="khasraAdjDetails")
public class KhasraAdjDetails {
	
	private String north;
	private String south;
	private String east;
	private String west;
	public String getNorth() {
		return north;
	}
	public void setNorth(String north) {
		this.north = north;
	}
	public String getSouth() {
		return south;
	}
	public void setSouth(String south) {
		this.south = south;
	}
	public String getEast() {
		return east;
	}
	public void setEast(String east) {
		this.east = east;
	}
	public String getWest() {
		return west;
	}
	public void setWest(String west) {
		this.west = west;
	}
	
	

}
