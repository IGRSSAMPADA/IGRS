package com.wipro.igrs.caveatsMaster.dto;

import java.util.ArrayList;

public class CaveatsDTO {
	// CaveatsDTO cdto = new CaveatsDTO();
	private String typeOfCaveat;
	private String caveatId;
	private ArrayList caveatTypeList;
	private String bankCourtName;
	private String bankCourtRepName;
	private String bankCourtAddress;
	private String cityDistrict;
	private String bankCourtCountry;
	private String countryId;
	private String countryName;
	private ArrayList countryMasterList;
	private String bankCourtStateName;
	private String stateId;
	private String stateName;
	private ArrayList stateMasterList;
	private String districtId;
	private String districtName;
	// private String districtMasterList;
	private ArrayList districtMasterList;
	private String bankCourtPostalCode;
	private String bankCourtPhoneNumber;
	private String caveatDetails;
	private String stayOrderNo;
	private String stayOrderDetails;
	private String stayOrderYesNo;
	private String remarks;
	private String registrationNumber;
	private String registrationNoSearch;
	private String docUrl;
	private String documentName;
	private String caveatsFormLog;
	private String caveatsLogInsert;
	private String flag;
	private String caveatType;
	private ArrayList recordsBuffer = new ArrayList();
	private String caveatSorderStatus;
	private String loggedDate;
	private String reasonForRelease;
	private String remarksForRelease;
	private String releaseDate;
	private String fromDate;
	private String toDate;

	private String referenceID;
	private String referenceIDSearch;
	private String pinNumber;
	private String errorMsg;
	private String ottNumber;
	private String ottExpiryDate;
	private String ottStatus;
	private String ottDate;
	private int hidlistten;

	// Getter Setter Variables for CAVEAT LOGGED BY BANK or INSTITUTE for --->
	// Caveats_Log_By_BankDetails.jsp
	private String nameOfInsti;
	private String addressOfInsti;
	private String contactnoOfInsti;
	private String emailOfInsti;
	private String nameOfBankPerson;
	private String mobOfBankPerson;
	private String emailOfBankPerson;
	private String loanAccountNumber;
	private float loanAmount;
	private float securedAmount;
	private float amountOfInstall;
	private int noOfInatalls;
	private String loanDueDate;
	private String loanStartDate;
	private String loanEndDate;
	private String bankLoggedDate;
	// Over
	private String nameOfCourtPerson;
	private String registrationDate;
	private long serialNo;
	private String actionName;
	private String formName;
	private String loggedIn;
	private String propertyTxnId;
	ArrayList errorList = new ArrayList();

	// /////////////////////mona caveat master
	private String caveatStatus;
	private String caveatCreatedBy;
	private String caveatCreatedDate;
	private String caveatUpdatedBy;
	private String caveatUpdatedDate;
	private String caveatStayOrderStatus;

	// /////////////////////////////////

	// public CaveatsDTO(){}

	public String getCaveatCreatedBy() {
		return caveatCreatedBy;
	}

	public void setCaveatCreatedBy(String caveatCreatedBy) {
		this.caveatCreatedBy = caveatCreatedBy;
	}

	public String getCaveatUpdatedBy() {
		return caveatUpdatedBy;
	}

	public void setCaveatUpdatedBy(String caveatUpdatedBy) {
		this.caveatUpdatedBy = caveatUpdatedBy;
	}

	public String getCaveatCreatedDate() {
		return caveatCreatedDate;
	}

	public void setCaveatCreatedDate(String caveatCreatedDate) {
		this.caveatCreatedDate = caveatCreatedDate;
	}

	public String getCaveatUpdatedDate() {
		return caveatUpdatedDate;
	}

	public void setCaveatUpdatedDate(String caveatUpdatedDate) {
		this.caveatUpdatedDate = caveatUpdatedDate;
	}

	public String getCaveatStatus() {
		return caveatStatus;
	}

	public void setCaveatStatus(String caveatStatus) {
		this.caveatStatus = caveatStatus;
	}

	public String getCaveatStayOrderStatus() {
		return caveatStayOrderStatus;
	}

	public void setCaveatStayOrderStatus(String caveatStayOrderStatus) {
		this.caveatStayOrderStatus = caveatStayOrderStatus;
	}

	public ArrayList getErrorList() {
		return errorList;
	}

	public void setErrorList(ArrayList errorList) {
		this.errorList = errorList;
	}

	public void setTypeOfCaveat(String typeOfCaveat) {
		this.typeOfCaveat = typeOfCaveat;
	}

	public String getTypeOfCaveat() {
		return typeOfCaveat;
	}

	public void setBankCourtName(String bankCourtName) {
		this.bankCourtName = bankCourtName;
	}

	public String getBankCourtName() {
		return bankCourtName;
	}

	public void setBankCourtRepName(String bankCourtRepName) {
		this.bankCourtRepName = bankCourtRepName;
	}

	public String getBankCourtRepName() {
		return bankCourtRepName;
	}

	public void setBankCourtAddress(String bankCourtAddress) {
		this.bankCourtAddress = bankCourtAddress;
	}

	public String getBankCourtAddress() {
		return bankCourtAddress;
	}

	public void setCityDistrict(String cityDistrict) {
		this.cityDistrict = cityDistrict;
	}

	public String getCityDistrict() {
		return cityDistrict;
	}

	public void setBankCourtCountry(String bankCourtCountry) {
		this.bankCourtCountry = bankCourtCountry;
	}

	public String getBankCourtCountry() {
		return bankCourtCountry;
	}

	/**
	 * @return the countryId
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId
	 *            the countryId to set
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName
	 *            the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void setBankCourtStateName(String bankCourtStateName) {
		this.bankCourtStateName = bankCourtStateName;
	}

	public String getBankCourtStateName() {
		return bankCourtStateName;
	}

	/**
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param stateId
	 *            the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName
	 *            the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the districtId
	 */
	public String getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId
	 *            the districtId to set
	 */
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**
	 * @param districtName
	 *            the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/**
	 * @return the districtMasterList
	 */
	public ArrayList getDistrictMasterList() {
		return districtMasterList;
	}

	/**
	 * @param districtMasterList
	 *            the districtMasterList to set
	 */
	public void setDistrictMasterList(ArrayList districtMasterList) {
		this.districtMasterList = districtMasterList;
	}

	public void setBankCourtPostalCode(String bankCourtPostalCode) {
		this.bankCourtPostalCode = bankCourtPostalCode;
	}

	public String getBankCourtPostalCode() {
		return bankCourtPostalCode;
	}

	public void setBankCourtPhoneNumber(String bankCourtPhoneNumber) {
		this.bankCourtPhoneNumber = bankCourtPhoneNumber;
	}

	public String getBankCourtPhoneNumber() {
		return bankCourtPhoneNumber;
	}

	public void setCaveatDetails(String caveatDetails) {
		this.caveatDetails = caveatDetails;
	}

	public String getCaveatDetails() {
		return caveatDetails;
	}

	public void setStayOrderNo(String stayOrderNo) {
		this.stayOrderNo = stayOrderNo;
	}

	public String getStayOrderNo() {
		return stayOrderNo;
	}

	public void setStayOrderDetails(String stayOrderDetails) {
		this.stayOrderDetails = stayOrderDetails;
	}

	public String getStayOrderDetails() {
		return stayOrderDetails;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {

		return remarks;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	public String getDocUrl() {
		return docUrl;
	}

	public void setCaveatsFormLog(String caveatsFormLog) {
		this.caveatsFormLog = caveatsFormLog;
	}

	public String getCaveatsFormLog() {
		return caveatsFormLog;
	}

	public void setCaveatsLogInsert(String caveatsLogInsert) {
		this.caveatsLogInsert = caveatsLogInsert;
	}

	public String getCaveatsLogInsert() {
		return caveatsLogInsert;
	}

	public void setStayOrderYesNo(String stayOrderYesNo) {
		this.stayOrderYesNo = stayOrderYesNo;
	}

	public String getStayOrderYesNo() {
		return stayOrderYesNo;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}

	public String getReferenceID() {
		return referenceID;
	}

	public void setCaveatTypeList(ArrayList caveatTypeList) {
		this.caveatTypeList = caveatTypeList;
	}

	public ArrayList getCaveatTypeList() {
		return caveatTypeList;
	}

	/**
	 * @return the countryMasterList
	 */
	public ArrayList getCountryMasterList() {
		return countryMasterList;
	}

	/**
	 * @param countryMasterList
	 *            the countryMasterList to set
	 */
	public void setCountryMasterList(ArrayList countryMasterList) {
		this.countryMasterList = countryMasterList;
	}

	/**
	 * @return the stateMasterList
	 */
	public ArrayList getStateMasterList() {
		return stateMasterList;
	}

	/**
	 * @param stateMasterList
	 *            the stateMasterList to set
	 */
	public void setStateMasterList(ArrayList stateMasterList) {
		this.stateMasterList = stateMasterList;
	}

	public void setCaveatType(String caveatType) {
		this.caveatType = caveatType;
	}

	public String getCaveatType() {
		return caveatType;
	}

	public void setCaveatId(String caveatId) {
		this.caveatId = caveatId;
	}

	public String getCaveatId() {
		return caveatId;
	}

	public void setCaveatSorderStatus(String caveatSorderStatus) {
		this.caveatSorderStatus = caveatSorderStatus;
	}

	public String getCaveatSorderStatus() {
		return caveatSorderStatus;
	}

	public void setLoggedDate(String loggedDate) {
		this.loggedDate = loggedDate;
	}

	public String getLoggedDate() {
		return loggedDate;
	}

	public void setReasonForRelease(String reasonForRelease) {
		this.reasonForRelease = reasonForRelease;
	}

	public String getReasonForRelease() {
		return reasonForRelease;
	}

	public void setRemarksForRelease(String remarksForRelease) {
		this.remarksForRelease = remarksForRelease;
	}

	public String getRemarksForRelease() {
		return remarksForRelease;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getPinNumber() {
		return pinNumber;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setOttNumber(String ottNumber) {
		this.ottNumber = ottNumber;
	}

	public String getOttNumber() {
		return ottNumber;
	}

	public void setOttExpiryDate(String ottExpiryDate) {
		this.ottExpiryDate = ottExpiryDate;
	}

	public String getOttExpiryDate() {
		return ottExpiryDate;
	}

	public void setOttStatus(String ottStatus) {
		this.ottStatus = ottStatus;
	}

	public String getOttStatus() {
		return ottStatus;
	}

	public void setOttDate(String ottDate) {
		this.ottDate = ottDate;
	}

	public String getOttDate() {
		return ottDate;
	}

	public void setNameOfInsti(String nameOfInsti) {
		this.nameOfInsti = nameOfInsti;
	}

	public String getNameOfInsti() {
		return nameOfInsti;
	}

	public void setAddressOfInsti(String addressOfInsti) {
		this.addressOfInsti = addressOfInsti;
	}

	public String getAddressOfInsti() {
		return addressOfInsti;
	}

	public void setContactnoOfInsti(String contactnoOfInsti) {
		this.contactnoOfInsti = contactnoOfInsti;
	}

	public String getContactnoOfInsti() {
		return contactnoOfInsti;
	}

	public void setEmailOfInsti(String emailOfInsti) {
		this.emailOfInsti = emailOfInsti;
	}

	public String getEmailOfInsti() {
		return emailOfInsti;
	}

	public void setNameOfBankPerson(String nameOfBankPerson) {
		this.nameOfBankPerson = nameOfBankPerson;
	}

	public String getNameOfBankPerson() {
		return nameOfBankPerson;
	}

	public void setMobOfBankPerson(String mobOfBankPerson) {
		this.mobOfBankPerson = mobOfBankPerson;
	}

	public String getMobOfBankPerson() {
		return mobOfBankPerson;
	}

	public void setEmailOfBankPerson(String emailOfBankPerson) {
		this.emailOfBankPerson = emailOfBankPerson;
	}

	public String getEmailOfBankPerson() {
		return emailOfBankPerson;
	}

	public void setNoOfInatalls(int noOfInatalls) {
		this.noOfInatalls = noOfInatalls;
	}

	public int getNoOfInatalls() {
		return noOfInatalls;
	}

	public void setLoanDueDate(String loanDueDate) {
		this.loanDueDate = loanDueDate;
	}

	public String getLoanDueDate() {
		return loanDueDate;
	}

	/**
	 * @return the loanStartDate
	 */
	public String getLoanStartDate() {
		return loanStartDate;
	}

	/**
	 * @param loanStartDate
	 *            the loanStartDate to set
	 */
	public void setLoanStartDate(String loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	/**
	 * @return the loanEndDate
	 */
	public String getLoanEndDate() {
		return loanEndDate;
	}

	/**
	 * @param loanEndDate
	 *            the loanEndDate to set
	 */
	public void setLoanEndDate(String loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	public void setBankLoggedDate(String bankLoggedDate) {
		this.bankLoggedDate = bankLoggedDate;
	}

	public String getBankLoggedDate() {
		return bankLoggedDate;
	}

	/**
	 * @return the loanAccountNumber
	 */
	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}

	/**
	 * @param loanAccountNumber
	 *            the loanAccountNumber to set
	 */
	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}

	public void setLoanAmount(float loanAmount) {
		this.loanAmount = loanAmount;
	}

	public float getLoanAmount() {
		return loanAmount;
	}

	public void setSecuredAmount(float securedAmount) {
		this.securedAmount = securedAmount;
	}

	public float getSecuredAmount() {
		return securedAmount;
	}

	public void setAmountOfInstall(float amountOfInstall) {
		this.amountOfInstall = amountOfInstall;
	}

	public float getAmountOfInstall() {
		return amountOfInstall;
	}

	public void setNameOfCourtPerson(String nameOfCourtPerson) {
		this.nameOfCourtPerson = nameOfCourtPerson;
	}

	public String getNameOfCourtPerson() {
		return nameOfCourtPerson;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setHidlistten(int hidlistten) {
		this.hidlistten = hidlistten;
	}

	public int getHidlistten() {
		return hidlistten;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentName() {
		return documentName;
	}

	/**
	 * @return the recordsBuffer
	 */
	public ArrayList getRecordsBuffer() {
		return recordsBuffer;
	}

	/**
	 * @param recordsBuffer
	 *            the recordsBuffer to set
	 */
	public void setRecordsBuffer(ArrayList recordsBuffer) {
		this.recordsBuffer = recordsBuffer;
	}

	/**
	 * @return the serialNo
	 */
	public long getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo
	 *            the serialNo to set
	 */
	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName
	 *            the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName
	 *            the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the loggedIn
	 */
	public String getLoggedIn() {
		return loggedIn;
	}

	/**
	 * @param loggedIn
	 *            the loggedIn to set
	 */
	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * @return the registrationNoSearch
	 */
	public String getRegistrationNoSearch() {
		return registrationNoSearch;
	}

	/**
	 * @param registrationNoSearch
	 *            the registrationNoSearch to set
	 */
	public void setRegistrationNoSearch(String registrationNoSearch) {
		this.registrationNoSearch = registrationNoSearch;
	}

	/**
	 * @return the referenceIDSearch
	 */
	public String getReferenceIDSearch() {
		return referenceIDSearch;
	}

	/**
	 * @param referenceIDSearch
	 *            the referenceIDSearch to set
	 */
	public void setReferenceIDSearch(String referenceIDSearch) {
		this.referenceIDSearch = referenceIDSearch;
	}

	/**
	 * @return the propertyTxnId
	 */
	public String getPropertyTxnId() {
		return propertyTxnId;
	}

	/**
	 * @param propertyTxnId
	 *            the propertyTxnId to set
	 */
	public void setPropertyTxnId(String propertyTxnId) {
		this.propertyTxnId = propertyTxnId;
	}
}
