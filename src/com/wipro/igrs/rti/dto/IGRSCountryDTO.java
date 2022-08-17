package com.wipro.igrs.rti.dto;

import java.util.ArrayList;

public class IGRSCountryDTO {
    private String countryId;
    private String stateId;
    private String country;
    private String stateName;
    private String districtId;
    private String districtName;
    private String rtiRequestOneAction;

    private ArrayList districtList = new ArrayList();
    private ArrayList CountryList;
    private ArrayList StateList;

    public IGRSCountryDTO() {
    }
/**
 * This method is used to set CountryId
 * @param countryId
 */
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
/**
 * This method is used to return CountryId
 * @return
 */
    public String getCountryId() {
        return countryId;
    }
/**
 * This method is used to set Country Name
 * @param country
 */
    public void setCountry(String country) {
        this.country = country;
    }
 /**
  * This method is used to return Country Name
  * @return
  */   

    public String getCountry() {
        return country;
    }
    
    /**
     * This method is used to set CountryList
     * @param countryList
     */

    public void setCountryList(ArrayList countryList) {
        this.CountryList = countryList;
    }
/**
 * This method is used to return CountryList
 * @return
 */
    public ArrayList getCountryList() {
        return CountryList;
    }
/**
 * This method is used to set State Name
 * @param stateName
 */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
/**
 * This method is used to return State Name
 * @return
 */
    public String getStateName() {
        return stateName;
    }
    
/**
 * This method is used to set State List
 * @param stateList
 */
    public void setStateList(ArrayList stateList) {
        this.StateList = stateList;
    }
/**
 * This method is used to return State List
 * @return
 */
    public ArrayList getStateList() {
        return StateList;
    }
/**
 * This method is used to set StateId
 * @param stateId
 */
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }
/**
 * This method is used to return StateId
 * @return
 */
    public String getStateId() {
        return stateId;
    }

/**
 * This method is used to set DistrictId
 * @param districtId
 */
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
/**
 * This method is used to return DistrictId
 * @return
 */
    public String getDistrictId() {
        return districtId;
    }
/**
 * 
 * @param districtName
 */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }

    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setRtiRequestOneAction(String rtiRequestOneAction) {
        this.rtiRequestOneAction = rtiRequestOneAction;
    }

    public String getRtiRequestOneAction() {
        return rtiRequestOneAction;
    }
}
