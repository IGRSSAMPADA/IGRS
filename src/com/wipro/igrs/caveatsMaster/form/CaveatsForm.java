package com.wipro.igrs.caveatsMaster.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.caveatsMaster.dto.CaveatsDTO;

public class CaveatsForm extends ActionForm {
	private CaveatsDTO caveatsDTO = new CaveatsDTO();

	private String typeOfCaveat;
	private String bankCourtName;
	private String bankCourtRepName;
	private String bankCourtAddress;
	private String cityDistrict;
	private String bankCourtCountry;
	private String bankCourtStateName;
	private int bankCourtPostalCode;
	private String bankCourtPhoneNumber;
	private String caveatDetails;
	private String stayOrderNo;
	private String stayOrderDetails;
	private String stayOrderYesNo;
	private String remarks;

	private String registrationNumber;
	private String docUrl;
	private String flag;
	private String caveatType;
	private String caveatId;
	private int referenceID;

	private String fromDate;
	private String toDate;
	private ArrayList caveatsData;// mona this arraylist to get the caveat
									// master table recodrds

	// ////////mona

	// /////////////////////////////
	public ArrayList getCaveatsData() {
		return caveatsData;
	}

	public void setCaveatsData(ArrayList caveatsData) {
		this.caveatsData = caveatsData;
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

	public void setBankCourtStateName(String bankCourtStateName) {
		this.bankCourtStateName = bankCourtStateName;
	}

	public String getBankCourtStateName() {
		return bankCourtStateName;
	}

	public void setBankCourtPostalCode(int bankCourtPostalCode) {
		this.bankCourtPostalCode = bankCourtPostalCode;
	}

	public int getBankCourtPostalCode() {
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

	public void setCaveatsDTO(CaveatsDTO caveatsDTO) {
		this.caveatsDTO = caveatsDTO;
	}

	public CaveatsDTO getCaveatsDTO() {
		return caveatsDTO;
	}

	public void setReferenceID(int referenceID) {
		this.referenceID = referenceID;
	}

	public int getReferenceID() {
		return referenceID;
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

}
