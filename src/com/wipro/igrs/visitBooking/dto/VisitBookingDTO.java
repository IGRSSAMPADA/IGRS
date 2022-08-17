package com.wipro.igrs.visitBooking.dto;
/**
 * ===========================================================================
 * File           :   VisitBookingDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Pavani param
 * Created Date   :   Apr 08, 2008.

 * ===========================================================================
 */
import java.util.ArrayList;

public class VisitBookingDTO {
	private String visitRem;
   private String type;
   private String securedAmt;
   private String stampDuty;
   private String others;
   private String total;
   private  boolean blnPerVal;
   private String photoId;
   private String orgCountry;
   private String orgState;
   private String orgDistrict;
   private ArrayList judicialList;
   //deed type added
   private String deedType; 
   private ArrayList deedTypeList;
   private String name;
   private String value;
   private String state;
   private String country;
   private String district;
   private ArrayList districtList;           
   private ArrayList countryList;			
   private ArrayList stateList;
   private ArrayList photoIdList;
   private ArrayList orgCountryList;
   private ArrayList orgStateList;
   private ArrayList orgDistrictList;
   private ArrayList vstDistList;
   
   private String estampTxnID;
  
   private String applPartyName;
   private String txn1PartyName;
   private String txn2PartyName;
   private String instruments;
   private ArrayList instrList;
   private String exemptions;
   private ArrayList exempList;
   private String stampDet;
   private String upLoadDoc;
//--E-stamp Search Type
   private String estampSearchType="estampCode";
   private String estampStatus;
   private String estampValFrom;
   private String estampValTo;
   private String estampCode;
   private String estampPurpose;
   private String estampDate;
   private ArrayList estampSResult;
   private String petVal;
   private String units;
   private String checked="";
   
   
    public ArrayList getEstampSResult() {
	return estampSResult;
}

public void setEstampSResult(ArrayList estampSResult) {
	this.estampSResult = estampSResult;
}

	public String getEstampPurpose() {
	return estampPurpose;
}

public void setEstampPurpose(String estampPurpose) {
	this.estampPurpose = estampPurpose;
}


	public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    

	public String getStampDuty() {
		return stampDuty;
	}

	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList getJudicialList() {
		return judicialList;
	}

	public void setJudicialList(ArrayList judicialList) {
		this.judicialList = judicialList;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public ArrayList getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}

	public ArrayList getStateList() {
		return stateList;
	}

	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public ArrayList getPhotoIdList() {
		return photoIdList;
	}

	public void setPhotoIdList(ArrayList photoIdList) {
		this.photoIdList = photoIdList;
	}

	public String getEstampTxnID() {
		return estampTxnID;
	}

	public void setEstampTxnID(String estampTxnID) {
		this.estampTxnID = estampTxnID;
	}

	public String getEstampCode() {
		return estampCode;
	}

	public void setEstampCode(String estampCode) {
		this.estampCode = estampCode;
	}

	public String getEstampStatus() {
		return estampStatus;
	}

	public void setEstampStatus(String estampStatus) {
		this.estampStatus = estampStatus;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public ArrayList getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public String getOrgCountry() {
		return orgCountry;
	}

	public void setOrgCountry(String orgCountry) {
		this.orgCountry = orgCountry;
	}

	public String getOrgState() {
		return orgState;
	}

	public void setOrgState(String orgState) {
		this.orgState = orgState;
	}

	public String getOrgDistrict() {
		return orgDistrict;
	}

	public void setOrgDistrict(String orgDistrict) {
		this.orgDistrict = orgDistrict;
	}

	public ArrayList getOrgCountryList() {
		return orgCountryList;
	}

	public void setOrgCountryList(ArrayList orgCountryList) {
		this.orgCountryList = orgCountryList;
	}

	public ArrayList getOrgStateList() {
		return orgStateList;
	}

	public void setOrgStateList(ArrayList orgStateList) {
		this.orgStateList = orgStateList;
	}

	public ArrayList getOrgDistrictList() {
		return orgDistrictList;
	}

	public void setOrgDistrictList(ArrayList orgDistrictList) {
		this.orgDistrictList = orgDistrictList;
	}
	public String getApplPartyName() {
		return applPartyName;
	}

	public void setApplPartyName(String applPartyName) {
		this.applPartyName = applPartyName;
	}

	public String getTxn1PartyName() {
		return txn1PartyName;
	}

	public void setTxn1PartyName(String txn1PartyName) {
		this.txn1PartyName = txn1PartyName;
	}

	public String getTxn2PartyName() {
		return txn2PartyName;
	}

	public void setTxn2PartyName(String txn2PartyName) {
		this.txn2PartyName = txn2PartyName;
	}

	public String getDeedType() {
		return deedType;
	}

	public void setDeedType(String deedType) {
		this.deedType = deedType;
	}

	public ArrayList getDeedTypeList() {
		return deedTypeList;
	}

	public void setDeedTypeList(ArrayList deedTypeList) {
		this.deedTypeList = deedTypeList;
	}

	public String getInstruments() {
		return instruments;
	}

	public void setInstruments(String instruments) {
		this.instruments = instruments;
	}

	public ArrayList getInstrList() {
		return instrList;
	}

	public void setInstrList(ArrayList instrList) {
		this.instrList = instrList;
	}

	public String getExemptions() {
		return exemptions;
	}

	public void setExemptions(String exemptions) {
		this.exemptions = exemptions;
	}

	

	public ArrayList getExempList() {
		return exempList;
	}

	public void setExempList(ArrayList exempList) {
		this.exempList = exempList;
	}

	

	public String getSecuredAmt() {
		return securedAmt;
	}

	public void setSecuredAmt(String securedAmt) {
		this.securedAmt = securedAmt;
	}

	public String getEstampDate() {
		return estampDate;
	}

	public void setEstampDate(String estampDate) {
		this.estampDate = estampDate;
	}

	
	public String getEstampSearchType() {
		return estampSearchType;
	}

	public void setEstampSearchType(String estampSearchType) {
		this.estampSearchType = estampSearchType;
	}

	public String getEstampValFrom() {
		return estampValFrom;
	}

	public void setEstampValFrom(String estampValFrom) {
		this.estampValFrom = estampValFrom;
	}

	public String getEstampValTo() {
		return estampValTo;
	}

	public void setEstampValTo(String estampValTo) {
		this.estampValTo = estampValTo;
	}

	public String getStampDet() {
		return stampDet;
	}

	public void setStampDet(String stampDet) {
		this.stampDet = stampDet;
	}

	public String getUpLoadDoc() {
		return upLoadDoc;
	}

	public void setUpLoadDoc(String upLoadDoc) {
		this.upLoadDoc = upLoadDoc;
	}

	public boolean isBlnPerVal() {
		return blnPerVal;
	}

	public void setBlnPerVal(boolean blnPerVal) {
		this.blnPerVal = blnPerVal;
	}

	/**
	 * @return the petVal
	 */
	public String getPetVal() {
		return petVal;
	}

	/**
	 * @param petVal the petVal to set
	 */
	public void setPetVal(String petVal) {
		this.petVal = petVal;
	}

	/**
	 * @return the units
	 */
	public String getUnits() {
		return units;
	}

	/**
	 * @param units the units to set
	 */
	public void setUnits(String units) {
		this.units = units;
	}

	/**
	 * @return the checked
	 */
	public String getChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}

	/**
	 * @return the visitRem
	 */
	public String getVisitRem() {
		return visitRem;
	}

	/**
	 * @param visitRem the visitRem to set
	 */
	public void setVisitRem(String visitRem) {
		this.visitRem = visitRem;
	}

	public ArrayList getVstDistList() {
		return vstDistList;
	}

	public void setVstDistList(ArrayList vstDistList) {
		this.vstDistList = vstDistList;
	}
}
