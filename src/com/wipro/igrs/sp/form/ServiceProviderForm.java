/**
 * ServiceProviderForm.java
 */


package com.wipro.igrs.sp.form;


import java.util.ArrayList;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.sp.dto.ServiceProviderDTO;


public class ServiceProviderForm extends BaseForm {

	ServiceProviderDTO providerDTO = new ServiceProviderDTO();
	
	
	public ServiceProviderForm() {

	}

	// added by shruti
	private ArrayList spUserTypeList = new ArrayList();
	private ArrayList tehsilList=new ArrayList();
	private ArrayList districts=new ArrayList();
	private ArrayList tehsils=new ArrayList();
	private ArrayList spbanks=new ArrayList();
	private ArrayList userDetailsList=new ArrayList(); 
	private ArrayList licenceuserlist=new ArrayList();
	
	private String spusername;
	
	public String getSpusername() {
		return spusername;
	}



	public void setSpusername(String spusername) {
		this.spusername = spusername;
	}



	public ArrayList getLicenceuserlist() {
		return licenceuserlist;
	}



	public void setLicenceuserlist(ArrayList licenceuserlist) {
		this.licenceuserlist = licenceuserlist;
	}



	public ArrayList getUserDetailsList() {
		return userDetailsList;
	}



	public void setUserDetailsList(ArrayList userDetailsList) {
		this.userDetailsList = userDetailsList;
	}

	private String durationFrom;
	private String durationTo;
	private String currentDate;
	private String licensenumber; 

public String getLicensenumber() {
		return licensenumber;
	}



	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}



public String getCurrentDate() {
	return currentDate;
}



public void setCurrentDate(String currentDate) {
	this.currentDate = currentDate;
}



public String getDurationFrom() {
		return durationFrom;
	}



	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
	}



	public String getDurationTo() {
		return durationTo;
	}



	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
	}



public ArrayList getSpbanks() {
		return spbanks;
	}



	public void setSpbanks(ArrayList spbanks) {
		this.spbanks = spbanks;
	}



public ArrayList getTehsils() {
		return tehsils;
	}



	public void setTehsils(ArrayList tehsils) {
		this.tehsils = tehsils;
	}



public ArrayList getDistricts() {
		return districts;
	}



	public void setDistricts(ArrayList districts) {
		this.districts = districts;
	}



public ArrayList getTehsilList() {
		return tehsilList;
	}



	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}

private	String actionName;

	public String getActionName() {
	return actionName;
}



public void setActionName(String actionName) {
	this.actionName = actionName;
}



	public ArrayList getSpUserTypeList() {
		return spUserTypeList;
	}



	public void setSpUserTypeList(ArrayList spUserTypeList) {
		this.spUserTypeList = spUserTypeList;
	}



	/**
	 * @return the providerDTO
	 */
	public ServiceProviderDTO getProviderDTO() {
		return providerDTO;
	}



	/**
	 * @param providerDTO
	 *            the providerDTO to set
	 */
	public void setProviderDTO(ServiceProviderDTO providerDTO) {
		this.providerDTO = providerDTO;
		
	}


private ArrayList deacLicencedUserDetailsList;

public ArrayList getDeacLicencedUserDetailsList() {
	return deacLicencedUserDetailsList;
}

public void setDeacLicencedUserDetailsList(ArrayList deacLicencedUserDetailsList) {
	this.deacLicencedUserDetailsList = deacLicencedUserDetailsList;
}

}