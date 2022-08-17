package com.wipro.igrs.bankingestamp.dto;
/**
 * ===========================================================================
 * File           :   EstampDetailsDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Pavani param
 * Created Date   :   Dec 08, 2007

 * ===========================================================================
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.upload.FormFile;

public class EstampDetailsDTO implements Serializable{
   private String type;
   private String stampTypeName; // To disply stamp type name in the view page.
   private String securedAmt;
   private String stampDuty;
   private String others;
   private Double total;
   private String stampFee;
   private ArrayList districtListCourt;
   private String courtType;
   private String court;
   private ArrayList courtList;
   private String courtDistrict;
   
private  boolean blnPerVal;
   private String photoId;
   private String orgCountry;
   private String orgState;
   private String orgDistrict;
   private ArrayList manDistList;
   private ArrayList trNameList;
   private ArrayList judicialList;
   private ArrayList sroList;


//deed type added
   private String deedType; 
   private ArrayList deedTypeList;
  // private ArrayList nonJudicialList;
   private String name;
   private String value;
   private String state = "Select";
   private String country;
   private String district = "Select";
   private String orgDistName;
   private String distName;
   private String photoIdName;
   private String purposeName;
   private String applPartyType;
   private String txn1PartyType;
   private String txn2PartyType;
   private ArrayList applPartyTypeList;
   private ArrayList txn1PartyTypeList;
   private ArrayList txn2PartyTypeList;
   //private ArrayList districtList;           
   //private ArrayList countryList;			
   //private ArrayList stateList;
   private ArrayList photoIdList;
   private ArrayList orgCountryList;
   private ArrayList purposeList;
   private ArrayList orgStateList;
   private ArrayList orgDistrictList;
   
   private String estampTxnID;
  
   private String applPartyName;
   private String txn1PartyName;
   private String txn2PartyName;
   private String instruments;
   private String instName;
   private String instId;
   private ArrayList instrList;
   private String ExemptionId;
   private String ExemptionName;
   
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
   private ArrayList pstList =  new ArrayList();
   private ArrayList formList =  new ArrayList();
   
   
   //--manual stamp
   private String mstampCode ;
   private String denomination ;
   private String stampCodeFrom;
   private String stampCodeTo;
   private String srDenom;
   private String upLoadDocName;
   private String filePath;
   
   // --Dynamically  Deed details 
   private String inputAlt ;
   private String inputTypeId ;
   private String inputLabel;
   private String inputType;
   private String inputScript;
   private String deedValReq ;
   private String dutyCalReq ;
   private String propRelDeed;
   
   //-- ADDED BY LAVI FOR JUDICIAL ESTAMP
   private ArrayList pendingApps = new ArrayList();
   private Object docName;
   private Object paidAmount;
   private Object balanceAmount;
   private Object lastTransactionDate;
   private Object hdntransactionID;
   private Object transactionID;
   private Object appType;
   private String appOrgName;
   private String appAuthFirstName;
   private String appAuthMiddleName;
   private String appAuthLastName;
   private String appCountry;
   private String appState;
   private String appDistrict;
   private Object appAddress;
   private Object appPostalCode;
   private Object appPhno;
   private Object appMobno;
   private Object appEmailID;
   private Object appPersons;
   private Object appFirsName;
   private Object appMiddleName;
   private Object appLastName;
   private Object appGender;
   private Object appGenderDisp;
   private Object appDay;
   private Object appMonth;
   private Object appYear;
   private Object appFatherName;
   private Object appMotherName;
   private Object appPhotoId;
   private Object appPhotoIdno;
   private String appBankName;
   private String appBankAddress;
	
   private Object partyType;
   private String partyOrgName;
   private String partyAuthFirstName;
   private String partyAuthMiddleName;
   private String partyAuthLastName;
   private String partyCountry;
   private String partyState;
   private String partyDistrict;
   private Object partyAddress;
   private Object partyPostalCode;
   private Object partyPhno;
   private Object partyMobno;
   private Object partyEmailID;
   private Object partyPersons;
   private Object partyGender;
   private Object partyGenderDisp;
   private Object partyDay;
   private Object partyMonth;
   private Object partyYear;
   private Object partyFatherName;
   private Object partyMotherName;
   private Object partyPhotoId;
   private Object partyTypeName;
   private Object partyAge;
   private Object partyPhotoIdno;
   private String partyBankName;
   private String partyBankAddress;
	
   private Object courtName;
   private Object courtPlace;
   private String cntry;
   private String stateCourt;
   private String distt;
   private Object amount;
   private Object date;
   private Object estampPurps;
   private Object doc;
   private Object partyFirsName;
   private Object partyMiddleName;
   private Object partyLastName;
   private Object docname;
   private String formName;
   private Object appAge;
   private Object appTypeName;
   
   
   FormFile filePhoto2=null;
	private Object uid;
	private Object mainTxnId;
	private Object uniqKey;
	private Object currentYear;
	private Object currentDate;
	private String userName;
	private String officeName;
	private String issuedPlace;
	private String actionName;
	private int errMsg=0;
	private int isModify=0;
	private int isAuthapp=0;
	private int isAuthparty=0;
	private int isPartial=0;
	private Object appNamedsply;
	private Object partyNamedsply;
	private int pay;
	private Object name1;
	private Object value1;
	private String appCountryName;
	private String appStateName;
	private String appDistrictName;
	private String partyCountryName;
	private String partyStateName;
	private String partyDistrictName;
	private Object appPhotoIdName;
	private Object partyPhotoIdName;
	
	private Object courtDocType;
	private Object courtDocTypeName;
	private String cntryName;
	private String stateCourtName;
	private String disttName;
	
	private Object estampValidity;
	private Object estampType;
	private Object ecode;
	private Object partyAuthPersonName;
	private Object appAuthPersonName;
	
	private Object hidnEstampTxnId;
	private Object hidnUserId;
	private Object DeedDuration;
	private Object appStatus;
	
	private Object partyFirstName;
	private Object applicant_ind;
	private String docPath;
	private String role;
	
	//added by lavi for scanner integration
	private String guidUpload;
	private String parentPathScan;
	private String fileNameScan;
	private int isInternalUser=0;
	private String docPathComplete;
	//end if variables added by lavi for scanner integration
	
	//added by satbir kumar
	
	private String districtid;
	private String districtname;
	private String iuofficeid;
	private String iuofficename;
	
	 private FormFile image;
		
	    private byte[] image1;
	
   private ArrayList countryList = new ArrayList();
   private ArrayList stateList = new ArrayList();
   private ArrayList districtList = new ArrayList();
   private ArrayList appellate = new ArrayList();
   private ArrayList docTypeList = new ArrayList();
   private ArrayList detailsTxnID = new ArrayList();
   private ArrayList partyDetails = new ArrayList();
   private ArrayList docDetails = new ArrayList();
   
   private String allowPrint ="N";
   
   private String printFlag ="N";
   
   public ArrayList getDistrictListCourt() {
		return districtListCourt;
	}

	public void setDistrictListCourt(ArrayList arrayList) {
		this.districtListCourt = arrayList;
	}
   
   public String getPrintFlag() {
	return printFlag;
}

public void setPrintFlag(String printFlag) {
	this.printFlag = printFlag;
}

public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getOrgDistName() {
		return orgDistName;
	}

	public void setOrgDistName(String orgDistName) {
		this.orgDistName = orgDistName;
	}

	public String getPhotoIdName() {
		return photoIdName;
	}

	public void setPhotoIdName(String photoIdName) {
		this.photoIdName = photoIdName;
	}
   
	 public String getPurposeName() {
			return purposeName;
		}

		public void setPurposeName(String purposeName) {
			this.purposeName = purposeName;
		}

   public String getStampFee() {
	return stampFee;
}

public void setStampFee(String stampFee) {
	this.stampFee = stampFee;
}

private HashMap mapForm = new HashMap(); 
    /**
 * @return the mapForm
 */
public HashMap getMapForm() {
    return mapForm;
}

/**
 * @param mapForm the mapForm to set
 */
public void setMapForm(HashMap mapForm) {
    this.mapForm = mapForm;
}

    /**
 * @return the stampCodeFrom
 */
public String getStampCodeFrom() {
	return stampCodeFrom;
}

/**
 * @param stampCodeFrom the stampCodeFrom to set
 */
public void setStampCodeFrom(String stampCodeFrom) {
	this.stampCodeFrom = stampCodeFrom;
}

/**
 * @return the stampCodeTo
 */
public String getStampCodeTo() {
	return stampCodeTo;
}

/**
 * @param stampCodeTo the stampCodeTo to set
 */
public void setStampCodeTo(String stampCodeTo) {
	this.stampCodeTo = stampCodeTo;
}

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
	

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public ArrayList getJudicialList() {
		return judicialList;
	}

	public void setJudicialList(ArrayList judicialList) {
		this.judicialList = judicialList;
	}

//	public ArrayList getNonJudicialList() {
//		return nonJudicialList;
//	}
//
//	public void setNonJudicialList(ArrayList nonJudicialList) {
//		this.nonJudicialList = nonJudicialList;
//	}

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

	public ArrayList getPurposeList() {
		return purposeList;
	}

	public void setPurposeList(ArrayList purposeList) {
		this.purposeList = purposeList;
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

	

	public ArrayList getApplPartyTypeList() {
		return applPartyTypeList;
	}

	public void setApplPartyTypeList(ArrayList applPartyTypeList) {
		this.applPartyTypeList = applPartyTypeList;
	}

	public String getApplPartyType() {
		return applPartyType;
	}

	public void setApplPartyType(String applPartyType) {
		this.applPartyType = applPartyType;
	}

	public String getTxn1PartyType() {
		return txn1PartyType;
	}

	public void setTxn1PartyType(String txn1PartyType) {
		this.txn1PartyType = txn1PartyType;
	}

	public ArrayList getTxn1PartyTypeList() {
		return txn1PartyTypeList;
	}

	public void setTxn1PartyTypeList(ArrayList txn1PartyTypeList) {
		this.txn1PartyTypeList = txn1PartyTypeList;
	}

	public String getTxn2PartyType() {
		return txn2PartyType;
	}

	public void setTxn2PartyType(String txn2PartyType) {
		this.txn2PartyType = txn2PartyType;
	}

	public ArrayList getTxn2PartyTypeList() {
		return txn2PartyTypeList;
	}

	public void setTxn2PartyTypeList(ArrayList txn2PartyTypeList) {
		this.txn2PartyTypeList = txn2PartyTypeList;
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
	 * @return the stampTypeName
	 */
	public String getStampTypeName() {
		return stampTypeName;
	}

	/**
	 * @param stampTypeName the stampTypeName to set
	 */
	public void setStampTypeName(String stampTypeName) {
		this.stampTypeName = stampTypeName;
	}

	/**
	 * @return the manDistList
	 */
	public ArrayList getManDistList() {
		return manDistList;
	}

	/**
	 * @param manDistList the manDistList to set
	 */
	public void setManDistList(ArrayList manDistList) {
		this.manDistList = manDistList;
	}

	/**
	 * @return the sroList
	 */
	public ArrayList getSroList() {
		return sroList;
	}

	/**
	 * @param sroList the sroList to set
	 */
	public void setSroList(ArrayList sroList) {
		this.sroList = sroList;
	}

	/**
	 * @return the trNameList
	 */
	public ArrayList getTrNameList() {
		return trNameList;
	}

	/**
	 * @param trNameList the trNameList to set
	 */
	public void setTrNameList(ArrayList trNameList) {
		this.trNameList = trNameList;
	}

	/**
	 * @return the mstampCode
	 */
	public String getMstampCode() {
		return mstampCode;
	}

	/**
	 * @param mstampCode the mstampCode to set
	 */
	public void setMstampCode(String mstampCode) {
		this.mstampCode = mstampCode;
	}

	/**
	 * @return the denomination
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * @param denomination the denomination to set
	 */
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	/**
	 * @return the pstList
	 */
	public ArrayList getPstList() {
		return pstList;
	}

	/**
	 * @param pstList the pstList to set
	 */
	public void setPstList(ArrayList pstList) {
		this.pstList = pstList;
	}

	/**
	 * @return the srDenom
	 */
	public String getSrDenom() {
		return srDenom;
	}

	/**
	 * @param srDenom the srDenom to set
	 */
	public void setSrDenom(String srDenom) {
		this.srDenom = srDenom;
	}

	/**
	 * @return the upLoadDocName
	 */
	public String getUpLoadDocName() {
		return upLoadDocName;
	}

	/**
	 * @param upLoadDocName the upLoadDocName to set
	 */
	public void setUpLoadDocName(String upLoadDocName) {
		this.upLoadDocName = upLoadDocName;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the formList
	 */
	public ArrayList getFormList() {
	    return formList;
	}

	/**
	 * @param formList the formList to set
	 */
	public void setFormList(ArrayList formList) {
	    this.formList = formList;
	}

	/**
	 * @return the inputAlt
	 */
	public String getInputAlt() {
	    return inputAlt;
	}

	/**
	 * @param inputAlt the inputAlt to set
	 */
	public void setInputAlt(String inputAlt) {
	    this.inputAlt = inputAlt;
	}

	/**
	 * @return the inputTypeId
	 */
	public String getInputTypeId() {
	    return inputTypeId;
	}

	/**
	 * @param inputTypeId the inputTypeId to set
	 */
	public void setInputTypeId(String inputTypeId) {
	    this.inputTypeId = inputTypeId;
	}

	/**
	 * @return the inputType
	 */
	public String getInputType() {
	    return inputType;
	}

	/**
	 * @param inputType the inputType to set
	 */
	public void setInputType(String inputType) {
	    this.inputType = inputType;
	}

	/**
	 * @return the inputLabel
	 */
	public String getInputLabel() {
	    return inputLabel;
	}

	/**
	 * @param inputLabel the inputLabel to set
	 */
	public void setInputLabel(String inputLabel) {
	    this.inputLabel = inputLabel;
	}

	/**
	 * @return the inputScript
	 */
	public String getInputScript() {
	    return inputScript;
	}

	/**
	 * @param inputScript the inputScript to set
	 */
	public void setInputScript(String inputScript) {
	    this.inputScript = inputScript;
	}

	/**
	 * @return the deedValReq
	 */
	public String getDeedValReq() {
	    return deedValReq;
	}

	/**
	 * @param deedValReq the deedValReq to set
	 */
	public void setDeedValReq(String deedValReq) {
	    this.deedValReq = deedValReq;
	}

	/**
	 * @return the dutyCalReq
	 */
	public String getDutyCalReq() {
	    return dutyCalReq;
	}

	/**
	 * @param dutyCalReq the dutyCalReq to set
	 */
	public void setDutyCalReq(String dutyCalReq) {
	    this.dutyCalReq = dutyCalReq;
	}

	/**
	 * @return the propRelDeed
	 */
	public String getPropRelDeed() {
	    return propRelDeed;
	}

	/**
	 * @param propRelDeed the propRelDeed to set
	 */
	public void setPropRelDeed(String propRelDeed) {
	    this.propRelDeed = propRelDeed;
	}

	/**
	 * @return the instName
	 */
	public String getInstName() {
	    return instName;
	}

	/**
	 * @param instName the instName to set
	 */
	public void setInstName(String instName) {
	    this.instName = instName;
	}

	/**
	 * @return the instId
	 */
	public String getInstId() {
	    return instId;
	}

	/**
	 * @param instId the instId to set
	 */
	public void setInstId(String instId) {
	    this.instId = instId;
	}

	/**
	 * @return the exemptionId
	 */
	public String getExemptionId() {
	    return ExemptionId;
	}

	/**
	 * @param exemptionId the exemptionId to set
	 */
	public void setExemptionId(String exemptionId) {
	    ExemptionId = exemptionId;
	}

	/**
	 * @return the exemptionName
	 */
	public String getExemptionName() {
	    return ExemptionName;
	}

	/**
	 * @param exemptionName the exemptionName to set
	 */
	public void setExemptionName(String exemptionName) {
	    ExemptionName = exemptionName;
	}

	public ArrayList getPendingApps() {
		return pendingApps;
	}

	public void setPendingApps(ArrayList pendingApps) {
		this.pendingApps = pendingApps;
	}

	

	

	public Object getDocName() {
		return docName;
	}

	public void setDocName(Object docName) {
		this.docName = docName;
	}

	public Object getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Object paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Object getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Object balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Object getLastTransactionDate() {
		return lastTransactionDate;
	}

	public void setLastTransactionDate(Object lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}

	public Object getHdntransactionID() {
		return hdntransactionID;
	}

	public void setHdntransactionID(Object hdntransactionID) {
		this.hdntransactionID = hdntransactionID;
	}

	public Object getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Object transactionID) {
		this.transactionID = transactionID;
	}

	public Object getAppType() {
		return appType;
	}

	public void setAppType(Object appType) {
		this.appType = appType;
	}

	

	public String getAppOrgName() {
		return appOrgName;
	}

	public void setAppOrgName(String appOrgName) {
		this.appOrgName = appOrgName;
	}

	

	public String getAppAuthFirstName() {
		return appAuthFirstName;
	}

	public void setAppAuthFirstName(String appAuthFirstName) {
		this.appAuthFirstName = appAuthFirstName;
	}

	

	

	public Object getAppAddress() {
		return appAddress;
	}

	public void setAppAddress(Object appAddress) {
		this.appAddress = appAddress;
	}

	public void setAppPhno(Object appPhno) {
		this.appPhno = appPhno;
	}

	public void setAppMobno(Object appMobno) {
		this.appMobno = appMobno;
	}

	public void setAppEmailID(Object appEmailID) {
		this.appEmailID = appEmailID;
	}

	public void setAppPersons(Object appPersons) {
		this.appPersons = appPersons;
	}

	public void setAppFirsName(Object appFirsName) {
		this.appFirsName = appFirsName;
	}

	public void setAppMiddleName(Object appMiddleName) {
		this.appMiddleName = appMiddleName;
	}

	public void setAppLastName(Object appLastName) {
		this.appLastName = appLastName;
	}

	public void setAppGender(Object appGender) {
		this.appGender = appGender;
	}

	public void setAppDay(Object appDay) {
		this.appDay = appDay;
	}

	public void setAppMonth(Object appMonth) {
		this.appMonth = appMonth;
	}

	public void setAppYear(Object appYear) {
		this.appYear = appYear;
	}

	public void setAppFatherName(Object appFatherName) {
		this.appFatherName = appFatherName;
	}

	public void setAppMotherName(Object appMotherName) {
		this.appMotherName = appMotherName;
	}

	public void setAppPhotoId(Object appPhotoId) {
		this.appPhotoId = appPhotoId;
	}

	public void setAppPhotoIdno(Object appPhotoIdno) {
		this.appPhotoIdno = appPhotoIdno;
	}

	public void setPartyType(Object partyType) {
		this.partyType = partyType;
	}

	

	public void setPartyAddress(Object partyAddress) {
		this.partyAddress = partyAddress;
	}

	public void setPartyPhno(Object partyPhno) {
		this.partyPhno = partyPhno;
	}

	public void setPartyMobno(Object partyMobno) {
		this.partyMobno = partyMobno;
	}

	public void setPartyEmailID(Object partyEmailID) {
		this.partyEmailID = partyEmailID;
	}

	public void setPartyPersons(Object partyPersons) {
		this.partyPersons = partyPersons;
	}

	public void setPartyGender(Object partyGender) {
		this.partyGender = partyGender;
	}

	public void setPartyDay(Object partyDay) {
		this.partyDay = partyDay;
	}

	public void setPartyMonth(Object partyMonth) {
		this.partyMonth = partyMonth;
	}

	public void setPartyYear(Object partyYear) {
		this.partyYear = partyYear;
	}

	public void setPartyFatherName(Object partyFatherName) {
		this.partyFatherName = partyFatherName;
	}

	public void setPartyMotherName(Object partyMotherName) {
		this.partyMotherName = partyMotherName;
	}

	public void setPartyPhotoId(Object partyPhotoId) {
		this.partyPhotoId = partyPhotoId;
	}

	public void setPartyTypeName(Object partyTypeName) {
		this.partyTypeName = partyTypeName;
	}

	public void setPartyAge(Object partyAge) {
		this.partyAge = partyAge;
	}

	public void setPartyPhotoIdno(Object partyPhotoIdno) {
		this.partyPhotoIdno = partyPhotoIdno;
	}

	public void setCourtName(Object courtName) {
		this.courtName = courtName;
	}

	public void setCourtPlace(Object courtPlace) {
		this.courtPlace = courtPlace;
	}

	

	public void setAmount(Object amount) {
		this.amount = amount;
	}

	public void setDate(Object date) {
		this.date = date;
	}

	public Object getAppPhno() {
		return appPhno;
	}

	public Object getAppMobno() {
		return appMobno;
	}

	public Object getAppEmailID() {
		return appEmailID;
	}

	public Object getAppPersons() {
		return appPersons;
	}

	public Object getAppFirsName() {
		return appFirsName;
	}

	public Object getAppMiddleName() {
		return appMiddleName;
	}

	public Object getAppLastName() {
		return appLastName;
	}

	public Object getAppGender() {
		return appGender;
	}

	public Object getAppDay() {
		return appDay;
	}

	public Object getAppMonth() {
		return appMonth;
	}

	public Object getAppYear() {
		return appYear;
	}

	public Object getAppFatherName() {
		return appFatherName;
	}

	public Object getAppMotherName() {
		return appMotherName;
	}

	public Object getAppPhotoId() {
		return appPhotoId;
	}

	public Object getAppPhotoIdno() {
		return appPhotoIdno;
	}

	public Object getPartyType() {
		return partyType;
	}

	

	public String getPartyOrgName() {
		return partyOrgName;
	}

	public void setPartyOrgName(String partyOrgName) {
		this.partyOrgName = partyOrgName;
	}

	

	public String getPartyCountry() {
		return partyCountry;
	}

	public String getPartyState() {
		return partyState;
	}

	public String getPartyDistrict() {
		return partyDistrict;
	}

	public Object getPartyAddress() {
		return partyAddress;
	}

	public Object getPartyPhno() {
		return partyPhno;
	}

	public Object getPartyMobno() {
		return partyMobno;
	}

	public Object getPartyEmailID() {
		return partyEmailID;
	}

	public Object getPartyPersons() {
		return partyPersons;
	}

	public Object getPartyGender() {
		return partyGender;
	}

	public Object getPartyDay() {
		return partyDay;
	}

	public Object getPartyMonth() {
		return partyMonth;
	}

	public Object getPartyYear() {
		return partyYear;
	}

	public Object getPartyFatherName() {
		return partyFatherName;
	}

	public Object getPartyMotherName() {
		return partyMotherName;
	}

	public Object getPartyPhotoId() {
		return partyPhotoId;
	}

	public Object getPartyTypeName() {
		return partyTypeName;
	}

	public Object getPartyAge() {
		return partyAge;
	}

	public Object getPartyPhotoIdno() {
		return partyPhotoIdno;
	}

	public Object getCourtName() {
		return courtName;
	}

	public Object getCourtPlace() {
		return courtPlace;
	}

	

	public String getCntry() {
		return cntry;
	}

	public String getStateCourt() {
		return stateCourt;
	}

	public String getDistt() {
		return distt;
	}

	public Object getAmount() {
		return amount;
	}

	public Object getDate() {
		return date;
	}

	public Object getEstampPurps() {
		return estampPurps;
	}

	public Object getPartyFirsName() {
		return partyFirsName;
	}

	public Object getPartyMiddleName() {
		return partyMiddleName;
	}

	public Object getPartyLastName() {
		return partyLastName;
	}

	public Object getDocname() {
		return docname;
	}

	public Object getAppAge() {
		return appAge;
	}

	public Object getAppTypeName() {
		return appTypeName;
	}

	public Object getUid() {
		return uid;
	}

	public Object getMainTxnId() {
		return mainTxnId;
	}

	public Object getUniqKey() {
		return uniqKey;
	}

	public Object getCurrentYear() {
		return currentYear;
	}

	public Object getCurrentDate() {
		return currentDate;
	}
	
	public Object getAppNamedsply() {
		return appNamedsply;
	}

	public Object getPartyNamedsply() {
		return partyNamedsply;
	}

	public Object getName1() {
		return name1;
	}

	public Object getValue1() {
		return value1;
	}

	

	public String getAppCountryName() {
		return appCountryName;
	}

	public String getAppStateName() {
		return appStateName;
	}

	public String getAppDistrictName() {
		return appDistrictName;
	}

	public String getPartyCountryName() {
		return partyCountryName;
	}

	public String getPartyStateName() {
		return partyStateName;
	}

	public String getPartyDistrictName() {
		return partyDistrictName;
	}

	public Object getAppPhotoIdName() {
		return appPhotoIdName;
	}

	public Object getPartyPhotoIdName() {
		return partyPhotoIdName;
	}

	public Object getCourtDocType() {
		return courtDocType;
	}

	public Object getCourtDocTypeName() {
		return courtDocTypeName;
	}

	

	public String getCntryName() {
		return cntryName;
	}

	public String getStateCourtName() {
		return stateCourtName;
	}

	public String getDisttName() {
		return disttName;
	}

	public Object getEstampValidity() {
		return estampValidity;
	}

	public Object getEstampType() {
		return estampType;
	}

	public Object getEcode() {
		return ecode;
	}

	public Object getPartyAuthPersonName() {
		return partyAuthPersonName;
	}

	public Object getAppAuthPersonName() {
		return appAuthPersonName;
	}

	public Object getHidnEstampTxnId() {
		return hidnEstampTxnId;
	}

	public Object getHidnUserId() {
		return hidnUserId;
	}

	public Object getDeedDuration() {
		return DeedDuration;
	}

	public Object getAppStatus() {
		return appStatus;
	}

	public Object getPartyFirstName() {
		return partyFirstName;
	}
	public void setEstampPurps(Object estampPurps) {
		this.estampPurps = estampPurps;
	}

	public void setPartyFirsName(Object partyFirsName) {
		this.partyFirsName = partyFirsName;
	}

	public void setPartyMiddleName(Object partyMiddleName) {
		this.partyMiddleName = partyMiddleName;
	}

	public void setPartyLastName(Object partyLastName) {
		this.partyLastName = partyLastName;
	}

	public void setDocname(Object docname) {
		this.docname = docname;
	}

	public void setAppAge(Object appAge) {
		this.appAge = appAge;
	}

	public void setAppTypeName(Object appTypeName) {
		this.appTypeName = appTypeName;
	}

	public void setUid(Object uid) {
		this.uid = uid;
	}

	public void setMainTxnId(Object mainTxnId) {
		this.mainTxnId = mainTxnId;
	}

	public void setUniqKey(Object uniqKey) {
		this.uniqKey = uniqKey;
	}

	public void setCurrentYear(Object currentYear) {
		this.currentYear = currentYear;
	}

	public void setCurrentDate(Object currentDate) {
		this.currentDate = currentDate;
	}
	public void setAppNamedsply(Object appNamedsply) {
		this.appNamedsply = appNamedsply;
	}

	public void setPartyNamedsply(Object partyNamedsply) {
		this.partyNamedsply = partyNamedsply;
	}

	public void setName1(Object name1) {
		this.name1 = name1;
	}

	public void setValue1(Object value1) {
		this.value1 = value1;
	}

	

	public void setAppPhotoIdName(Object appPhotoIdName) {
		this.appPhotoIdName = appPhotoIdName;
	}

	public void setPartyPhotoIdName(Object partyPhotoIdName) {
		this.partyPhotoIdName = partyPhotoIdName;
	}

	public void setCourtDocType(Object courtDocType) {
		this.courtDocType = courtDocType;
	}

	public void setCourtDocTypeName(Object courtDocTypeName) {
		this.courtDocTypeName = courtDocTypeName;
	}

	public void setEstampValidity(Object estampValidity) {
		this.estampValidity = estampValidity;
	}

	public void setEstampType(Object estampType) {
		this.estampType = estampType;
	}

	public void setEcode(Object ecode) {
		this.ecode = ecode;
	}

	public void setPartyAuthPersonName(Object partyAuthPersonName) {
		this.partyAuthPersonName = partyAuthPersonName;
	}

	public void setAppAuthPersonName(Object appAuthPersonName) {
		this.appAuthPersonName = appAuthPersonName;
	}

	public void setHidnEstampTxnId(Object hidnEstampTxnId) {
		this.hidnEstampTxnId = hidnEstampTxnId;
	}

	public void setHidnUserId(Object hidnUserId) {
		this.hidnUserId = hidnUserId;
	}

	public void setDeedDuration(Object deedDuration) {
		DeedDuration = deedDuration;
	}

	public void setAppStatus(Object appStatus) {
		this.appStatus = appStatus;
	}

	public void setPartyFirstName(Object partyFirstName) {
		this.partyFirstName = partyFirstName;
	}

	public Object getAppPostalCode() {
		return appPostalCode;
	}

	public void setAppPostalCode(Object appPostalCode) {
		this.appPostalCode = appPostalCode;
	}

	

	
	public Object getPartyPostalCode() {
		return partyPostalCode;
	}

	public void setPartyPostalCode(Object partyPostalCode) {
		this.partyPostalCode = partyPostalCode;
	}

	

	

	public Object getDoc() {
		return doc;
	}

	public void setDoc(Object doc) {
		this.doc = doc;
	}

	

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public int getIsModify() {
		return isModify;
	}

	public void setIsModify(int isModify) {
		this.isModify = isModify;
	}

	public int getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(int errMsg) {
		this.errMsg = errMsg;
	}

	public FormFile getFilePhoto2() {
		return filePhoto2;
	}

	public void setFilePhoto2(FormFile filePhoto2) {
		this.filePhoto2 = filePhoto2;
	}

	public int getIsAuthapp() {
		return isAuthapp;
	}

	public void setIsAuthapp(int isAuthapp) {
		this.isAuthapp = isAuthapp;
	}

	public int getIsAuthparty() {
		return isAuthparty;
	}

	public void setIsAuthparty(int isAuthparty) {
		this.isAuthparty = isAuthparty;
	}

	public int getIsPartial() {
		return isPartial;
	}

	public void setIsPartial(int isPartial) {
		this.isPartial = isPartial;
	}

	

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public ArrayList getAppellate() {
		return appellate;
	}

	public void setAppellate(ArrayList appellate) {
		this.appellate = appellate;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	

	public ArrayList getDocTypeList() {
		return docTypeList;
	}

	public void setDocTypeList(ArrayList docTypeList) {
		this.docTypeList = docTypeList;
	}

	

	public void setHidnUserId(String hidnUserId) {
		this.hidnUserId = hidnUserId;
	}

	public ArrayList getDetailsTxnID() {
		return detailsTxnID;
	}

	public void setDetailsTxnID(ArrayList detailsTxnID) {
		this.detailsTxnID = detailsTxnID;
	}

	public ArrayList getPartyDetails() {
		return partyDetails;
	}

	public void setPartyDetails(ArrayList partyDetails) {
		this.partyDetails = partyDetails;
	}

	public ArrayList getDocDetails() {
		return docDetails;
	}

	public void setDocDetails(ArrayList docDetails) {
		this.docDetails = docDetails;
	}

	public Object getApplicant_ind() {
		return applicant_ind;
	}

	public void setApplicant_ind(Object applicant_ind) {
		this.applicant_ind = applicant_ind;
	}

	public String getAppCountry() {
		return appCountry;
	}

	public void setAppCountry(String appCountry) {
		this.appCountry = appCountry;
	}

	public String getAppState() {
		return appState;
	}

	public void setAppState(String appState) {
		this.appState = appState;
	}
	
	public String getAppDistrict() {
		return appDistrict;
	}

	public void setAppDistrict(String appDistrict) {
		this.appDistrict = appDistrict;
	}

	public void setPartyCountry(String partyCountry) {
		this.partyCountry = partyCountry;
	}

	public void setPartyState(String partyState) {
		this.partyState = partyState;
	}

	public void setPartyDistrict(String partyDistrict) {
		this.partyDistrict = partyDistrict;
	}

	public void setCntry(String cntry) {
		this.cntry = cntry;
	}

	public void setStateCourt(String stateCourt) {
		this.stateCourt = stateCourt;
	}

	public void setDistt(String distt) {
		this.distt = distt;
	}

	public void setAppCountryName(String appCountryName) {
		this.appCountryName = appCountryName;
	}

	public void setAppStateName(String appStateName) {
		this.appStateName = appStateName;
	}

	public void setAppDistrictName(String appDistrictName) {
		this.appDistrictName = appDistrictName;
	}

	public void setPartyCountryName(String partyCountryName) {
		this.partyCountryName = partyCountryName;
	}

	public void setPartyStateName(String partyStateName) {
		this.partyStateName = partyStateName;
	}

	public void setPartyDistrictName(String partyDistrictName) {
		this.partyDistrictName = partyDistrictName;
	}

	public void setCntryName(String cntryName) {
		this.cntryName = cntryName;
	}

	public void setStateCourtName(String stateCourtName) {
		this.stateCourtName = stateCourtName;
	}

	public void setDisttName(String disttName) {
		this.disttName = disttName;
	}

	public String getAppAuthMiddleName() {
		return appAuthMiddleName;
	}

	public void setAppAuthMiddleName(String appAuthMiddleName) {
		this.appAuthMiddleName = appAuthMiddleName;
	}

	public String getAppAuthLastName() {
		return appAuthLastName;
	}

	public void setAppAuthLastName(String appAuthLastName) {
		this.appAuthLastName = appAuthLastName;
	}

	public String getAppBankName() {
		return appBankName;
	}

	public void setAppBankName(String appBankName) {
		this.appBankName = appBankName;
	}

	public String getAppBankAddress() {
		return appBankAddress;
	}

	public void setAppBankAddress(String appBankAddress) {
		this.appBankAddress = appBankAddress;
	}

	public String getPartyAuthFirstName() {
		return partyAuthFirstName;
	}

	public void setPartyAuthFirstName(String partyAuthFirstName) {
		this.partyAuthFirstName = partyAuthFirstName;
	}

	public String getPartyAuthMiddleName() {
		return partyAuthMiddleName;
	}

	public void setPartyAuthMiddleName(String partyAuthMiddleName) {
		this.partyAuthMiddleName = partyAuthMiddleName;
	}

	public String getPartyAuthLastName() {
		return partyAuthLastName;
	}

	public void setPartyAuthLastName(String partyAuthLastName) {
		this.partyAuthLastName = partyAuthLastName;
	}

	public String getPartyBankName() {
		return partyBankName;
	}

	public void setPartyBankName(String partyBankName) {
		this.partyBankName = partyBankName;
	}

	public String getPartyBankAddress() {
		return partyBankAddress;
	}

	public void setPartyBankAddress(String partyBankAddress) {
		this.partyBankAddress = partyBankAddress;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getIssuedPlace() {
		return issuedPlace;
	}

	public void setIssuedPlace(String issuedPlace) {
		this.issuedPlace = issuedPlace;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDistrictid() {
		return districtid;
	}

	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public String getIuofficeid() {
		return iuofficeid;
	}

	public void setIuofficeid(String iuofficeid) {
		this.iuofficeid = iuofficeid;
	}

	public String getIuofficename() {
		return iuofficename;
	}

	public void setIuofficename(String iuofficename) {
		this.iuofficename = iuofficename;
	}

	public FormFile getImage() {
		return image;
	}

	public void setImage(FormFile image) {
		this.image = image;
	}

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public int getIsInternalUser() {
		return isInternalUser;
	}

	public void setIsInternalUser(int isInternalUser) {
		this.isInternalUser = isInternalUser;
	}

	public String getGuidUpload() {
		return guidUpload;
	}

	public void setGuidUpload(String guidUpload) {
		this.guidUpload = guidUpload;
	}

	public String getParentPathScan() {
		return parentPathScan;
	}

	public void setParentPathScan(String parentPathScan) {
		this.parentPathScan = parentPathScan;
	}

	public String getFileNameScan() {
		return fileNameScan;
	}

	public void setFileNameScan(String fileNameScan) {
		this.fileNameScan = fileNameScan;
	}

	public String getDocPathComplete() {
		return docPathComplete;
	}

	public void setDocPathComplete(String docPathComplete) {
		this.docPathComplete = docPathComplete;
	}

	public Object getAppGenderDisp() {
		return appGenderDisp;
	}

	public void setAppGenderDisp(Object appGenderDisp) {
		this.appGenderDisp = appGenderDisp;
	}

	public Object getPartyGenderDisp() {
		return partyGenderDisp;
	}

	public void setPartyGenderDisp(Object partyGenderDisp) {
		this.partyGenderDisp = partyGenderDisp;
	}
	
	
	
	private String owmFlag;
	
	private String owmFile;



	public String getOwmFlag() {
		return owmFlag;
	}

	public void setOwmFlag(String owmFlag) {
		this.owmFlag = owmFlag;
	}

	public String getOwmFile() {
		return owmFile;
	}

	public void setOwmFile(String owmFile) {
		this.owmFile = owmFile;
	}
	
	private String deedDraftId;
	
	private String deedConsumeFlag;
	
	public String getDeedConsumeFlag() {
		return deedConsumeFlag;
	}

	public void setDeedConsumeFlag(String deedConsumeFlag) {
		this.deedConsumeFlag = deedConsumeFlag;
	}

	private String deedDraftError;
	
	private String deedDraftStatus;

	private String deedDraftErrorFlag;


	public String getDeedDraftErrorFlag() {
		return deedDraftErrorFlag;
	}

	public void setDeedDraftErrorFlag(String deedDraftErrorFlag) {
		this.deedDraftErrorFlag = deedDraftErrorFlag;
	}

	public String getDeedDraftId() {
		return deedDraftId;
	}

	public void setDeedDraftId(String deedDraftId) {
		this.deedDraftId = deedDraftId;
	}

	public String getDeedDraftError() {
		return deedDraftError;
	}

	public void setDeedDraftError(String deedDraftError) {
		this.deedDraftError = deedDraftError;
	}

	public String getDeedDraftStatus() {
		return deedDraftStatus;
	}

	public void setDeedDraftStatus(String deedDraftStatus) {
		this.deedDraftStatus = deedDraftStatus;
	}
	
private String validateOtpFlag;
	
	private String otp;

	public String getValidateOtpFlag() {
		return validateOtpFlag;
	}

	public void setValidateOtpFlag(String validateOtpFlag) {
		this.validateOtpFlag = validateOtpFlag;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public void setAllowPrint(String allowPrint) {
		this.allowPrint = allowPrint;
	}

	public String getAllowPrint() {
		return allowPrint;
	}

	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}

	public String getCourtType() {
		return courtType;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public String getCourt() {
		return court;
	}

	public void setCourtList(ArrayList courtList) {
		this.courtList = courtList;
	}

	public ArrayList getCourtList() {
		return courtList;
	}

	public String getCourtDistrict() {
		return courtDistrict;
	}

	public void setCourtDistrict(String courtDistrict) {
		this.courtDistrict = courtDistrict;
	}

	
	
}
