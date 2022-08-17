/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.empmgmt.dto;


import java.io.Serializable;
import java.util.ArrayList;


/**
* 
* PropertyDTO.java <br>
* PropertyDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class PropertyDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5120214993859637957L;

	private String empId;

	private String propertyaddress;

	private String propertycountry;

	private String propertystate;

	private String propertydistrict;

	private String propertypostalcode;

	private String propertyshare;

	private String propertyregid;

	private String propertyregdate;

	private String countryId;

	private String countryName;

	private ArrayList countryList;

	private String stateId;

	private String stateName;

	private ArrayList stateList;

	private String districtId;

	private String districtName;

	private ArrayList districtList;

	private String employeeId;
	
	private String stateIdHidden;

	private String districtIdHidden;

	/**
	 * @return the countryId
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the countryList
	 */
	public ArrayList getCountryList() {
		return countryList;
	}

	/**
	 * @param countryList the countryList to set
	 */
	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
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
	 * @return the districtList
	 */
	public ArrayList getDistrictList() {
		return districtList;
	}

	/**
	 * @param districtList the districtList to set
	 */
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**
	 * @param districtName the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the propertyaddress
	 */
	public String getPropertyaddress() {
		return propertyaddress;
	}

	/**
	 * @param propertyaddress the propertyaddress to set
	 */
	public void setPropertyaddress(String propertyaddress) {
		this.propertyaddress = propertyaddress;
	}

	/**
	 * @return the propertycountry
	 */
	public String getPropertycountry() {
		return propertycountry;
	}

	/**
	 * @param propertycountry the propertycountry to set
	 */
	public void setPropertycountry(String propertycountry) {
		this.propertycountry = propertycountry;
	}

	/**
	 * @return the propertydistrict
	 */
	public String getPropertydistrict() {
		return propertydistrict;
	}

	/**
	 * @param propertydistrict the propertydistrict to set
	 */
	public void setPropertydistrict(String propertydistrict) {
		this.propertydistrict = propertydistrict;
	}

	/**
	 * @return the propertypostalcode
	 */
	public String getPropertypostalcode() {
		return propertypostalcode;
	}

	/**
	 * @param propertypostalcode the propertypostalcode to set
	 */
	public void setPropertypostalcode(String propertypostalcode) {
		this.propertypostalcode = propertypostalcode;
	}

	/**
	 * @return the propertyregdate
	 */
	public String getPropertyregdate() {
		return propertyregdate;
	}

	/**
	 * @param propertyregdate the propertyregdate to set
	 */
	public void setPropertyregdate(String propertyregdate) {
		this.propertyregdate = propertyregdate;
	}

	/**
	 * @return the propertyregid
	 */
	public String getPropertyregid() {
		return propertyregid;
	}

	/**
	 * @param propertyregid the propertyregid to set
	 */
	public void setPropertyregid(String propertyregid) {
		this.propertyregid = propertyregid;
	}

	/**
	 * @return the propertyshare
	 */
	public String getPropertyshare() {
		return propertyshare;
	}

	/**
	 * @param propertyshare the propertyshare to set
	 */
	public void setPropertyshare(String propertyshare) {
		this.propertyshare = propertyshare;
	}

	/**
	 * @return the propertystate
	 */
	public String getPropertystate() {
		return propertystate;
	}

	/**
	 * @param propertystate the propertystate to set
	 */
	public void setPropertystate(String propertystate) {
		this.propertystate = propertystate;
	}

	/**
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the stateList
	 */
	public ArrayList getStateList() {
		return stateList;
	}

	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the stateIdHidden
	 */
	public String getStateIdHidden() {
		return stateIdHidden;
	}

	/**
	 * @param stateIdHidden the stateIdHidden to set
	 */
	public void setStateIdHidden(String stateIdHidden) {
		this.stateIdHidden = this.propertystate;
		//this.stateIdHidden = stateIdHidden;
	}

	/**
	 * @return the districtIdHidden
	 */
	public String getDistrictIdHidden() {
		return districtIdHidden;
	}

	/**
	 * @param districtIdHidden the districtIdHidden to set
	 */
	public void setDistrictIdHidden(String districtIdHidden) {
		this.districtIdHidden = this.propertydistrict;
//		this.districtIdHidden = districtIdHidden;
	}


}
