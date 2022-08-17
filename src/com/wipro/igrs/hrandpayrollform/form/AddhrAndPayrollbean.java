package com.wipro.igrs.hrandpayrollform.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class AddhrAndPayrollbean extends org.apache.struts.action.ActionForm {

	private String startDate;
	private String leasePeriod;
	private String amountPaid;
	private String paymentMode;
	private ArrayList paymentModeCollection;
	private String addressRented;
	private String countryRented;
	private ArrayList countryCollectionRented;
	private String stateRented;
	private ArrayList stateCollectionRented;
	private String districtRented;
	private ArrayList districtCollectionRented;
	private String postalCodeRented;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String addressLandLord;
	private String countryLandLord;
	private ArrayList countryCollectionLandLord;
	private String stateLandLord;
	private ArrayList stateCollectionLandLord;
	private String districtLandLord;
	private ArrayList districtCollectionLandLord;
	private String postalCodeLandLord;



	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	
	
	
	/**
	 * @return the leasePeriod
	 */
	public String getLeasePeriod() {
		return leasePeriod;
	}

	/**
	 * @param leasePeriod the leasePeriod to set
	 */
	public void setLeasePeriod(String leasePeriod) {
		this.leasePeriod = leasePeriod;
	}

	/**
	 * @return the amountPaid
	 */
	public String getAmountPaid() {
		return amountPaid;
	}

	/**
	 * @param amountPaid the amountPaid to set
	 */
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * @return the postalCodeRented
	 */
	public String getPostalCodeRented() {
		return postalCodeRented;
	}

	/**
	 * @param postalCodeRented the postalCodeRented to set
	 */
	public void setPostalCodeRented(String postalCodeRented) {
		this.postalCodeRented = postalCodeRented;
	}

	/**
	 * @return the postalCodeLandLord
	 */
	public String getPostalCodeLandLord() {
		return postalCodeLandLord;
	}

	/**
	 * @param postalCodeLandLord the postalCodeLandLord to set
	 */
	public void setPostalCodeLandLord(String postalCodeLandLord) {
		this.postalCodeLandLord = postalCodeLandLord;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the paymentModeCollection
	 */
	public ArrayList getPaymentModeCollection() {
		return paymentModeCollection;
	}

	/**
	 * @param paymentModeCollection the paymentModeCollection to set
	 */
	public void setPaymentModeCollection(ArrayList paymentModeCollection) {
		this.paymentModeCollection = paymentModeCollection;
	}

	/**
	 * @return the addressRented
	 */
	public String getAddressRented() {
		return addressRented;
	}

	/**
	 * @param addressRented the addressRented to set
	 */
	public void setAddressRented(String addressRented) {
		this.addressRented = addressRented;
	}

	/**
	 * @return the countryRented
	 */
	public String getCountryRented() {
		return countryRented;
	}

	/**
	 * @param countryRented the countryRented to set
	 */
	public void setCountryRented(String countryRented) {
		this.countryRented = countryRented;
	}

	/**
	 * @return the countryCollectionRented
	 */
	public ArrayList getCountryCollectionRented() {
		return countryCollectionRented;
	}

	/**
	 * @param countryCollectionRented the countryCollectionRented to set
	 */
	public void setCountryCollectionRented(ArrayList countryCollectionRented) {
		this.countryCollectionRented = countryCollectionRented;
	}

	/**
	 * @return the stateRented
	 */
	public String getStateRented() {
		return stateRented;
	}

	/**
	 * @param stateRented the stateRented to set
	 */
	public void setStateRented(String stateRented) {
		this.stateRented = stateRented;
	}

	/**
	 * @return the stateCollectionRented
	 */
	public ArrayList getStateCollectionRented() {
		return stateCollectionRented;
	}

	/**
	 * @param stateCollectionRented the stateCollectionRented to set
	 */
	public void setStateCollectionRented(ArrayList stateCollectionRented) {
		this.stateCollectionRented = stateCollectionRented;
	}

	/**
	 * @return the districtRented
	 */
	public String getDistrictRented() {
		return districtRented;
	}

	/**
	 * @param districtRented the districtRented to set
	 */
	public void setDistrictRented(String districtRented) {
		this.districtRented = districtRented;
	}

	/**
	 * @return the districtCollectionRented
	 */
	public ArrayList getDistrictCollectionRented() {
		return districtCollectionRented;
	}

	/**
	 * @param districtCollectionRented the districtCollectionRented to set
	 */
	public void setDistrictCollectionRented(ArrayList districtCollectionRented) {
		this.districtCollectionRented = districtCollectionRented;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the addressLandLord
	 */
	public String getAddressLandLord() {
		return addressLandLord;
	}

	/**
	 * @param addressLandLord the addressLandLord to set
	 */
	public void setAddressLandLord(String addressLandLord) {
		this.addressLandLord = addressLandLord;
	}

	/**
	 * @return the countryLandLord
	 */
	public String getCountryLandLord() {
		return countryLandLord;
	}

	/**
	 * @param countryLandLord the countryLandLord to set
	 */
	public void setCountryLandLord(String countryLandLord) {
		this.countryLandLord = countryLandLord;
	}

	/**
	 * @return the countryCollectionLandLord
	 */
	public ArrayList getCountryCollectionLandLord() {
		return countryCollectionLandLord;
	}

	/**
	 * @param countryCollectionLandLord the countryCollectionLandLord to set
	 */
	public void setCountryCollectionLandLord(ArrayList countryCollectionLandLord) {
		this.countryCollectionLandLord = countryCollectionLandLord;
	}

	/**
	 * @return the stateLandLord
	 */
	public String getStateLandLord() {
		return stateLandLord;
	}

	/**
	 * @param stateLandLord the stateLandLord to set
	 */
	public void setStateLandLord(String stateLandLord) {
		this.stateLandLord = stateLandLord;
	}

	
	/**
	 * @return the stateCollectionLandLord
	 */
	public ArrayList getStateCollectionLandLord() {
		return stateCollectionLandLord;
	}

	/**
	 * @param stateCollectionLandLord the stateCollectionLandLord to set
	 */
	public void setStateCollectionLandLord(ArrayList stateCollectionLandLord) {
		this.stateCollectionLandLord = stateCollectionLandLord;
	}

	/**
	 * @return the districtLandLord
	 */
	public String getDistrictLandLord() {
		return districtLandLord;
	}

	/**
	 * @param districtLandLord the districtLandLord to set
	 */
	public void setDistrictLandLord(String districtLandLord) {
		this.districtLandLord = districtLandLord;
	}

	/**
	 * @return the districtCollectionLandLord
	 */
	public ArrayList getDistrictCollectionLandLord() {
		return districtCollectionLandLord;
	}

	/**
	 * @param districtCollectionLandLord the districtCollectionLandLord to set
	 */
	public void setDistrictCollectionLandLord(ArrayList districtCollectionLandLord) {
		this.districtCollectionLandLord = districtCollectionLandLord;
	}

	public AddhrAndPayrollbean () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        return null;
    }


}