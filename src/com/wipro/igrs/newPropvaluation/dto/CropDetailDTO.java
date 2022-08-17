package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="cropDetail")
public class CropDetailDTO implements Serializable{
	private String seasonType;
	private String cropType;
	private String cropArea;
	@XmlElement(name="seasonType")
	public String getSeasonType() {
		return seasonType;
	}
	public void setSeasonType(String seasonType) {
		this.seasonType = seasonType;
	}
	@XmlElement(name="cropType")
	public String getCropType() {
		return cropType;
	}
	public void setCropType(String cropType) {
		this.cropType = cropType;
	}
	@XmlElement(name="cropArea")
	public String getCropArea() {
		return cropArea;
	}
	public void setCropArea(String cropArea) {
		this.cropArea = cropArea;
	}
	
}
