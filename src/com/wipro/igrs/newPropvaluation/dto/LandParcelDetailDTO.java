package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class LandParcelDetailDTO implements Serializable {
	private String landParcelId;
	private String surveyNo;
	private String surveyNoArea;
	private String landUseType;
	private String LandRevenue;
	private ArrayList<MortgageRemarkList> mortgageRemarks;
	private ArrayList<CropDetailList> cropDetails;
	private ArrayList<KhasraRemarkDTO> khasraRemarks;
	@XmlElement
	public String getLandParcelId() {
		return landParcelId;
	}
	public void setLandParcelId(String landParcelId) {
		this.landParcelId = landParcelId;
	}
	@XmlElement
	public String getSurveyNo() {
		return surveyNo;
	}
	public void setSurveyNo(String surveyNo) {
		this.surveyNo = surveyNo;
	}
	@XmlElement
	public String getSurveyNoArea() {
		return surveyNoArea;
	}
	public void setSurveyNoArea(String surveyNoArea) {
		this.surveyNoArea = surveyNoArea;
	}
	@XmlElement
	public String getLandUseType() {
		return landUseType;
	}
	public void setLandUseType(String landUseType) {
		this.landUseType = landUseType;
	}
	@XmlElement(name="LandRevenue")
	public String getLandRevenue() {
		return LandRevenue;
	}
	public void setLandRevenue(String landRevenue) {
		LandRevenue = landRevenue;
	}
	@XmlElement(name="mortgageRemarks")
	public ArrayList<MortgageRemarkList> getMortgageRemarks() {
		return mortgageRemarks;
	}
	public void setMortgageRemarks(ArrayList<MortgageRemarkList> mortgageRemarks) {
		this.mortgageRemarks = mortgageRemarks;
	}
	@XmlElement(name="cropDetails")
	public ArrayList<CropDetailList> getCropDetails() {
		return cropDetails;
	}
	public void setCropDetails(ArrayList<CropDetailList> cropDetails) {
		this.cropDetails = cropDetails;
	}
	@XmlElement(name="khasraRemarks")
	public ArrayList<KhasraRemarkDTO> getKhasraRemarks() {
		return khasraRemarks;
	}
	public void setKhasraRemarks(ArrayList<KhasraRemarkDTO> khasraRemarks) {
		this.khasraRemarks = khasraRemarks;
	}

}
