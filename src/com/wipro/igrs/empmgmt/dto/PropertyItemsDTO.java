package com.wipro.igrs.empmgmt.dto;
import java.io.Serializable;
import java.util.ArrayList;
public class PropertyItemsDTO implements Serializable

{
	private String countryId;
	private String countryName;
	private ArrayList countryList;
	
	private String stateId;
	private String stateName;
	private ArrayList stateList;

	private String districtId;
	private String districtName;
	private ArrayList districtList;
	
		
	//constructor
	public PropertyItemsDTO() {
		
	}


	public String getCountryId() {
		return countryId;
	}


	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}


	public ArrayList getCountryList() {
		return countryList;
	}


	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public String getDistrictId() {
		return districtId;
	}


	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}


	public ArrayList getDistrictList() {
		return districtList;
	}


	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}


	public String getDistrictName() {
		return districtName;
	}


	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}


	public String getStateId() {
		return stateId;
	}


	public void setStateId(String stateId) {
		this.stateId = stateId;
	}


	public ArrayList getStateList() {
		return stateList;
	}


	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}
