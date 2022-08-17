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

//import com.wipro.igrs.dto.CommonItemsDTO;

/**
* 
* PersonalDetailsDTO.java <br>
* PersonalDetailsDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class PersonalDetailsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4558928082625035798L;
	//fields in the PersonlInfo.jsp
	private String employeeId;
	private String referenceId;
	private String referenceemployeeid;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;

	private String empDay;
	private String empMonth;
	private String empYear;
	private String dateOfBirthWords;

	//Family Details 
	private String fatherGaurdName;
	private String motherName;
	private String maritalStatus;

	//Child Details
	//private String chkChildName;
	/*
	private String childNames[];
	private String childGenders[];
	private String childDay[];
	private String childMonth[];
	private String childYear[];
	 */
	//private String childDOBs[];
	private String religion;
	private String homeState;

	private String homeDistrict;
	private String caste;
	private String chkPhysically;
	private String physicallyChallanged;
	private String height;
	private String nationality;
	private String identificationMarks;
	private String phoneNo;
	private String mobileNo;
	private String emailId;

	//Address
	private String permAddress;

	private String valueID;
	private String valueName;
	private String homeStateId;
	private String homeStateName;
	private String permCountry;
	private ArrayList permCountryList;
	private String permState;
	private ArrayList permStateList;
	private String permDistrict;
	private ArrayList permDistrictList;
	private String permPin;

	private String currAddress;
	private String currCountry;
	private ArrayList currCountryList;
	private String currState;
	private ArrayList currStateList;
	private String currDistrict;
	private ArrayList currDistrictList;
	private String currPin;

	private String personalForm;
	private String personalInsertAction;

	private String religionId;
	private String religionName;
	private ArrayList religionList;

	private String casteId;
	private String casteName;
	private ArrayList casteList;

	private String homeDistrictId;
	private String homeDistrictName;
	private ArrayList homeDistrictList;
	private ArrayList homeStateList;
	private String dateOfBirth;
	private String officename;
	private String officelocation;
	
	private String hintQuestion;
	private String hintQuestID;
	private String userHintAnswer;
	
	private String maritalStatusLabel;
	
	private String firstNameSearch;
	private String lastNameSearch;
	
	private String employeeIDDisplay;
	
	ArrayList<PersonalDetailsDTO> searchResultList;
	
	ArrayList<FamilyMemberDTO> familyMembers = new ArrayList<FamilyMemberDTO>();
	
	private String genderLabel;
	private String physicalChallengeLabel;
	
	public String getHintQuestion() {
		return hintQuestion;
	}

	public void setHintQuestion(String hintQuestion) {
		this.hintQuestion = hintQuestion;
	}

	public String getHintQuestID() {
		return hintQuestID;
	}

	public void setHintQuestID(String hintQuestID) {
		this.hintQuestID = hintQuestID;
	}

	public String getUserHintAnswer() {
		return userHintAnswer;
	}

	public void setUserHintAnswer(String userHintAnswer) {
		this.userHintAnswer = userHintAnswer;
	}

	

	/**
	 * @return the officename
	 */
	public String getOfficename() {
		return officename;
	}

	/**
	 * @param officename the officename to set
	 */
	public void setOfficename(String officename) {
		this.officename = officename;
	}

	/**
	 * @return the officelocation
	 */
	public String getOfficelocation() {
		return officelocation;
	}

	public String getHomeStateId() {
		return homeStateId;
	}

	public void setHomeStateId(String homeStateId) {
		this.homeStateId = homeStateId;
	}

	public String getHomeStateName() {
		return homeStateName;
	}

	public void setHomeStateName(String homeStateName) {
		this.homeStateName = homeStateName;
	}

	public String getHomeState() {
		return homeState;
	}

	public void setHomeState(String homeState) {
		this.homeState = homeState;
	}

	public void setEmployeeIDDisplay(String employeeIDDisplay) {
		this.employeeIDDisplay = employeeIDDisplay;
	}

	public ArrayList getHomeStateList() {
		return homeStateList;
	}

	public void setHomeStateList(ArrayList homeStateList) {
		this.homeStateList = homeStateList;
	}

	/**
	 * @param officelocation the officelocation to set
	 */
	public void setOfficelocation(String officelocation) {
		this.officelocation = officelocation;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public PersonalDetailsDTO() {
		
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	/*
	 public String[] getChildDay() {
	 return childDay;
	 }

	 public void setChildDay(String[] childDay) {
	 this.childDay = childDay;
	 }

	 public String[] getChildGenders() {
	 return childGenders;
	 }

	 public void setChildGenders(String[] childGenders) {
	 this.childGenders = childGenders;
	 }

	 public String[] getChildMonth() {
	 return childMonth;
	 }

	 public void setChildMonth(String[] childMonth) {
	 this.childMonth = childMonth;
	 }

	 public String[] getChildNames() {
	 return childNames;
	 }

	 public void setChildNames(String[] childNames) {
	 this.childNames = childNames;
	 }

	 public String[] getChildYear() {
	 return childYear;
	 }

	 public void setChildYear(String[] childYear) {
	 this.childYear = childYear;
	 }
	 */
	public String getChkPhysically() {
		return chkPhysically;
	}

	public void setChkPhysically(String chkPhysically) {
		this.chkPhysically = chkPhysically;
	}

	public String getCurrAddress() {
		return currAddress;
	}

	public void setCurrAddress(String currAddress) {
		this.currAddress = currAddress;
	}

	public String getCurrCountry() {
		return currCountry;
	}

	public void setCurrCountry(String currCountry) {
		this.currCountry = currCountry;
	}

	public String getCurrDistrict() {
		return currDistrict;
	}

	public void setCurrDistrict(String currDistrict) {
		this.currDistrict = currDistrict;
	}

	public String getCurrState() {
		return currState;
	}

	public void setCurrState(String currState) {
		this.currState = currState;
	}

	public String getDateOfBirthWords() {
		return dateOfBirthWords;
	}

	public void setDateOfBirthWords(String dateOfBirthWords) {
		this.dateOfBirthWords = dateOfBirthWords;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmpDay() {
		return empDay;
	}

	public void setEmpDay(String empDay) {
		this.empDay = empDay;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpMonth() {
		return empMonth;
	}

	public void setEmpMonth(String empMonth) {
		this.empMonth = empMonth;
	}

	public String getEmpYear() {
		return empYear;
	}

	public void setEmpYear(String empYear) {
		this.empYear = empYear;
	}

	public String getFatherGaurdName() {
		return fatherGaurdName;
	}

	public void setFatherGaurdName(String fatherGaurdName) {
		this.fatherGaurdName = fatherGaurdName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHomeDistrict() {
		return homeDistrict;
	}

	public void setHomeDistrict(String homeDistrict) {
		this.homeDistrict = homeDistrict;
	}

	public String getIdentificationMarks() {
		return identificationMarks;
	}

	public void setIdentificationMarks(String identificationMarks) {
		this.identificationMarks = identificationMarks;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPermAddress() {
		return permAddress;
	}

	public void setPermAddress(String permAddress) {
		this.permAddress = permAddress;
	}

	public String getPermCountry() {
		return permCountry;
	}

	public void setPermCountry(String permCountry) {
		this.permCountry = permCountry;
	}

	public String getPermDistrict() {
		return permDistrict;
	}

	public void setPermDistrict(String permDistrict) {
		this.permDistrict = permDistrict;
	}

	public String getPermState() {
		return permState;
	}

	public void setPermState(String permState) {
		this.permState = permState;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPhysicallyChallanged() {
		return physicallyChallanged;
	}

	public void setPhysicallyChallanged(String physicallyChallanged) {
		this.physicallyChallanged = physicallyChallanged;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getPersonalForm() {
		return personalForm;
	}

	public void setPersonalForm(String personalForm) {
		this.personalForm = personalForm;
	}

	public String getPersonalInsertAction() {
		return personalInsertAction;
	}

	public void setPersonalInsertAction(String personalInsertAction) {
		this.personalInsertAction = personalInsertAction;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public ArrayList getCurrCountryList() {
		return currCountryList;
	}

	public void setCurrCountryList(ArrayList currCountryList) {
		this.currCountryList = currCountryList;
	}

	public ArrayList getCurrDistrictList() {
		return currDistrictList;
	}

	public void setCurrDistrictList(ArrayList currDistrictList) {
		this.currDistrictList = currDistrictList;
	}

	public ArrayList getCurrStateList() {
		return currStateList;
	}

	public void setCurrStateList(ArrayList currStateList) {
		this.currStateList = currStateList;
	}

	public ArrayList getPermCountryList() {
		return permCountryList;
	}

	public void setPermCountryList(ArrayList permCountryList) {
		this.permCountryList = permCountryList;
	}

	public ArrayList getPermDistrictList() {
		return permDistrictList;
	}

	public void setPermDistrictList(ArrayList permDistrictList) {
		this.permDistrictList = permDistrictList;
	}

	public ArrayList getPermStateList() {
		return permStateList;
	}

	public void setPermStateList(ArrayList permStateList) {
		this.permStateList = permStateList;
	}

	public String getValueName() {
		return valueName;
	}

	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	public String getValueID() {
		return valueID;
	}

	public void setValueID(String valueID) {
		this.valueID = valueID;
	}

	public String getReligionId() {
		return religionId;
	}

	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}

	public String getReligionName() {
		return religionName;
	}

	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}

	public ArrayList getReligionList() {
		return religionList;
	}

	public void setReligionList(ArrayList religionList) {
		this.religionList = religionList;
	}

	public String getCasteId() {
		return casteId;
	}

	public void setCasteId(String casteId) {
		this.casteId = casteId;
	}

	public String getCasteName() {
		return casteName;
	}

	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}

	public ArrayList getCasteList() {
		return casteList;
	}

	public void setCasteList(ArrayList casteList) {
		this.casteList = casteList;
	}

	public String getHomeDistrictId() {
		return homeDistrictId;
	}

	public void setHomeDistrictId(String homeDistrictId) {
		this.homeDistrictId = homeDistrictId;
	}

	public String getHomeDistrictName() {
		return homeDistrictName;
	}

	public void setHomeDistrictName(String homeDistrictName) {
		this.homeDistrictName = homeDistrictName;
	}

	public ArrayList getHomeDistrictList() {
		return homeDistrictList;
	}

	public void setHomeDistrictList(ArrayList homeDistrictList) {
		this.homeDistrictList = homeDistrictList;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setPermPin(String permPin) {
		this.permPin = permPin;
	}

	public void setCurrPin(String currPin) {
		this.currPin = currPin;
	}

	public String getHeight() {
		return height;
	}

	public String getPermPin() {
		return permPin;
	}

	public String getCurrPin() {
		return currPin;
	}

	/**
	 * @return the referenceemployeeid
	 */
	public String getReferenceemployeeid() {
		return referenceemployeeid;
	}

	/**
	 * @param referenceemployeeid the referenceemployeeid to set
	 */
	public void setReferenceemployeeid(String referenceemployeeid) {
		this.referenceemployeeid = referenceemployeeid;
	}

	/**
	 * @return the maritalStatusLabel
	 */
	public String getMaritalStatusLabel() {
		return maritalStatusLabel;
	}

	/**
	 * @param maritalStatusLabel the maritalStatusLabel to set
	 */
	public void setMaritalStatusLabel(String maritalStatusLabel) {
		this.maritalStatusLabel = maritalStatusLabel;
	}

	/**
	 * @return the firstNameSearch
	 */
	public String getFirstNameSearch() {
		return firstNameSearch;
	}

	/**
	 * @param firstNameSearch the firstNameSearch to set
	 */
	public void setFirstNameSearch(String firstNameSearch) {
		this.firstNameSearch = firstNameSearch;
	}

	/**
	 * @return the lastNameSearch
	 */
	public String getLastNameSearch() {
		return lastNameSearch;
	}

	/**
	 * @param lastNameSearch the lastNameSearch to set
	 */
	public void setLastNameSearch(String lastNameSearch) {
		this.lastNameSearch = lastNameSearch;
	}

	/**
	 * @return the searchResultList
	 */
	public ArrayList<PersonalDetailsDTO> getSearchResultList() {
		return searchResultList;
	}

	/**
	 * @param searchResultList the searchResultList to set
	 */
	public void setSearchResultList(ArrayList<PersonalDetailsDTO> searchResultList) {
		this.searchResultList = searchResultList;
	}

	/**
	 * @return the familyMembers
	 */
	public ArrayList<FamilyMemberDTO> getFamilyMembers() {
		return familyMembers;
	}

	/**
	 * @param familyMembers the familyMembers to set
	 */
	public void setFamilyMembers(ArrayList<FamilyMemberDTO> familyMembers) {
		this.familyMembers = familyMembers;
	}

	/**
	 * @return the genderLabel
	 */
	public String getGenderLabel() {
		return genderLabel;
	}

	/**
	 * @param genderLabel the genderLabel to set
	 */
	public void setGenderLabel(String genderLabel) {
		this.genderLabel = genderLabel;
	}

	/**
	 * @return the physicalChallengeLabel
	 */
	public String getPhysicalChallengeLabel() {
		return physicalChallengeLabel;
	}

	/**
	 * @param physicalChallengeLabel the physicalChallengeLabel to set
	 */
	public void setPhysicalChallengeLabel(String physicalChallengeLabel) {
		this.physicalChallengeLabel = physicalChallengeLabel;
	}

	/**
	 * @return the employeeIDDisplay
	 */
	public String getEmployeeIDDisplay() {
		this.employeeIDDisplay = new String(employeeId);
		return employeeIDDisplay;
	}

//	/**
//	 * @param employeeIDDisplay the employeeIDDisplay to set
//	 */
//	public void setEmployeeIDDisplay(String employeeIDDisplay) {
//		this.employeeIDDisplay = employeeIDDisplay;
//	}

	/*
	public String getChkChildName() {
		return chkChildName;
	}

	public void setChkChildName(String chkChildName) {
		this.chkChildName = chkChildName;
	}
	 */

}
