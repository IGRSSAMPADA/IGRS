package com.wipro.igrs.rcms.entity;

import javax.xml.bind.annotation.XmlElement;


public class PropertyDetails {
	
	private String propertyId;
	private String totalSellableArea;
	private KhasraRecordList khasraRecordList;
	private String TotalKhasraArea;
	
	
	@XmlElement(name="propertyId")
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	
	@XmlElement(name="totalSellableArea")
	public void setTotalSellableArea(String totalSellableArea) {
		this.totalSellableArea = totalSellableArea;
	}
	public String getTotalSellableArea() {
		return totalSellableArea;
	}
	public KhasraRecordList getKhasraRecordList() {
		return khasraRecordList;
	}
	public void setKhasraRecordList(KhasraRecordList khasraRecordList) {
		this.khasraRecordList = khasraRecordList;
	}
	@XmlElement(name="TotalKhasraArea")
	public String getTotalKhasraArea() {
		return TotalKhasraArea;
	}
	public void setTotalKhasraArea(String totalKhasraArea) {
		TotalKhasraArea = totalKhasraArea;
	}

	
}
