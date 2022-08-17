package com.wipro.igrs.officearea.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class OfficeMappingForm extends org.apache.struts.action.ActionForm {

	private String officeId;
	private String districtId;
	private String tehsilId;
	private String wardPatwariId;
	private String mohallaVillageId;
	
	private String officeName;
	
	private List officeList = new ArrayList();
	private List districtList = new ArrayList();
	private List tehsilList = new ArrayList();
	private List wardPatwariList = new ArrayList();
	private List mohallaVillageList = new ArrayList();

    public OfficeMappingForm () {
    }
    
    

    /**
	 * @return the officeId
	 */
	public String getOfficeId() {
		return officeId;
	}



	/**
	 * @param officeId the officeId to set
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}



	/**
	 * @return the districtId
	 */
	public String getDistrictId() {
		return districtId;
	}



	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}



	/**
	 * @return the tehsilId
	 */
	public String getTehsilId() {
		return tehsilId;
	}



	/**
	 * @param tehsilId the tehsilId to set
	 */
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}



	/**
	 * @return the wardPatwariId
	 */
	public String getWardPatwariId() {
		return wardPatwariId;
	}



	/**
	 * @param wardPatwariId the wardPatwariId to set
	 */
	public void setWardPatwariId(String wardPatwariId) {
		this.wardPatwariId = wardPatwariId;
	}



	/**
	 * @return the mohallaVilligeId
	 */
	public String getMohallaVillageId() {
		return mohallaVillageId;
	}



	/**
	 * @param mohallaVilligeId the mohallaVilligeId to set
	 */
	public void setMohallaVillageId(String mohallaVilligeId) {
		this.mohallaVillageId = mohallaVilligeId;
	}
	
	



	/**
	 * @return the officeList
	 */
	public List getOfficeList() {
		return officeList;
	}



	/**
	 * @param officeList the officeList to set
	 */
	public void setOfficeList(List officeList) {
		this.officeList = officeList;
	}


	

	/**
	 * @return the districtList
	 */
	public List getDistrictList() {
		return districtList;
	}



	/**
	 * @param districtList the districtList to set
	 */
	public void setDistrictList(List districtList) {
		this.districtList = districtList;
	}



	/**
	 * @return the tehsilList
	 */
	public List getTehsilList() {
		return tehsilList;
	}



	/**
	 * @param tehsilList the tehsilList to set
	 */
	public void setTehsilList(List tehsilList) {
		this.tehsilList = tehsilList;
	}



	/**
	 * @return the wardPatwariList
	 */
	public List getWardPatwariList() {
		return wardPatwariList;
	}



	/**
	 * @param wardPatwariList the wardPatwariList to set
	 */
	public void setWardPatwariList(List wardPatwariList) {
		this.wardPatwariList = wardPatwariList;
	}



	/**
	 * @return the mohallaVillageList
	 */
	public List getMohallaVillageList() {
		return mohallaVillageList;
	}



	/**
	 * @param mohallaVillageList the mohallaVillageList to set
	 */
	public void setMohallaVillageList(List mohallaVillageList) {
		this.mohallaVillageList = mohallaVillageList;
	}
	
	



	/**
	 * @return the officeName
	 */
	public String getOfficeName() {
		return officeName;
	}



	/**
	 * @param officeName the officeName to set
	 */
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}



	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
    	return null;
    }


}