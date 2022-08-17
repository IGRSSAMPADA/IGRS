package com.wipro.igrs.suppleDoc.dto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.newPropvaluation.dto.PropertyValuationDTO;
import com.wipro.igrs.spotInsp.action.SpotInspAction;

public class SuppleDocDTO extends ActionForm{
	private String currentUserId;
	private String actionName; // Variable for accessing caller(e.g. Button, Component etc) from form component
	private String formName;
	private String referenceId;
	private String userId;
	private String errorDate;
	private String stampType;
	private String keysStringPurpose;
	private String recieptDate;
	private int counter=0;
	private int counterPStamp=0;
	private int counterEStamp=0;
	private HashMap mapBuilding = new HashMap();
	private int propTypeL2Flag;
	private String dummy;
	private String hdnDeleteFloorID;
	private String purposeNames1;
	private String documentNumber;
	private int days;
	private String wardIds;
	private String uniqueId;
	private String thumb;
	private String thumbPath;
	private String subAreaId;
	private ArrayList<SuppleDocDTO> wardPatwariList;
	private String subAreaName;
	public String  municipalCheckDisplay;
	public String municipalFlag;
	private String wardPatwariName;
	public String municipalCheck;
	private String colonyId;
	private String colonyName;
	private String datePStamps;
	private String nameVendorPStamps;
	private String exemptions;
	private String checkBoxVerified;
	private String noOfPages;
	public String getDatePStamps() {
		return datePStamps;
	}
	public void setDatePStamps(String datePStamps) {
		this.datePStamps = datePStamps;
	}
	public String getNameVendorPStamps() {
		return nameVendorPStamps;
	}
	public void setNameVendorPStamps(String nameVendorPStamps) {
		this.nameVendorPStamps = nameVendorPStamps;
	}
	private ArrayList<SuppleDocDTO> colonyList;
	public String getColonyId() {
		return colonyId;
	}
	public void setColonyId(String colonyId) {
		this.colonyId = colonyId;
	}
	public String getColonyName() {
		return colonyName;
	}
	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}
	public String getMunicipalCheckDisplay() {
		return municipalCheckDisplay;
	}
	public void setMunicipalCheckDisplay(String municipalCheckDisplay) {
		this.municipalCheckDisplay = municipalCheckDisplay;
	}
	public String getMunicipalFlag() {
		return municipalFlag;
	}
	public void setMunicipalFlag(String municipalFlag) {
		this.municipalFlag = municipalFlag;
	}
	public String getMunicipalCheck() {
		return municipalCheck;
	}
	public void setMunicipalCheck(String municipalCheck) {
		this.municipalCheck = municipalCheck;
	}
	public String getMunicipalCheckFlag() {
		return municipalCheckFlag;
	}
	public void setMunicipalCheckFlag(String municipalCheckFlag) {
		this.municipalCheckFlag = municipalCheckFlag;
	}
	private String municipalCheckFlag;
	private ArrayList<SuppleDocDTO> subAreaList;
	
	public String getSubAreaId() {
		return subAreaId;
	}
	public void setSubAreaId(String subAreaId) {
		this.subAreaId = subAreaId;
	}
	public String getSubAreaName() {
		return subAreaName;
	}
	public void setSubAreaName(String subAreaName) {
		this.subAreaName = subAreaName;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getThumbPath() {
		return thumbPath;
	}
	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
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
	public String getGuidUpload() {
		return guidUpload;
	}
	public void setGuidUpload(String guidUpload) {
		this.guidUpload = guidUpload;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	private ArrayList partyDetails;
	private ArrayList pstampDetails;
	private ArrayList estampDetails;
	
	
	public ArrayList getPstampDetails() {
		return pstampDetails;
	}
	public void setPstampDetails(ArrayList pstampDetails) {
		this.pstampDetails = pstampDetails;
	}
	public ArrayList getEstampDetails() {
		return estampDetails;
	}
	public void setEstampDetails(ArrayList estampDetails) {
		this.estampDetails = estampDetails;
	}
	private ArrayList pendingDetails;
	private String createdDate;
	private String status;
	private String pageNo;
	private String combineData;
	
	private String filingNumber;
	//Start-------------Partial Save Page 1
	//-------------party details variable
	
	
	
	//----Search and View Page
	
	
	private String searchType;
	private String searchFiling;
	private String searchCase;

	
	
	public String getSearchFiling() {
		return searchFiling;
	}
	public void setSearchFiling(String searchFiling) {
		this.searchFiling = searchFiling;
	}
	public String getSearchCase() {
		return searchCase;
	}
	public void setSearchCase(String searchCase) {
		this.searchCase = searchCase;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public int getPropTypeL2Flag() {
		return propTypeL2Flag;
	}
	public void setPropTypeL2Flag(int propTypeL2Flag) {
		this.propTypeL2Flag = propTypeL2Flag;
	}
	private String fname;
    private String mname;
    private String lname;
    private String casecheck;
    private String fatherName;
    private String motherName;
    private String spouseName;
    
    private String remarks;
    private String confirm;
    private String gender;
    private int age;
    
    private String impound;
    
    private String fname1;
    private String mname1;
    private String lname1;
    
    private String fatherName1;
    private String motherName1;
    private String spouseName1;
    
    private String gender1;
    private int age1;
    
    
    public String getFname1() {
		return fname1;
	}
	public void setFname1(String fname1) {
		this.fname1 = fname1;
	}
	public String getMname1() {
		return mname1;
	}
	public void setMname1(String mname1) {
		this.mname1 = mname1;
	}
	public String getLname1() {
		return lname1;
	}
	public void setLname1(String lname1) {
		this.lname1 = lname1;
	}
	public String getFatherName1() {
		return fatherName1;
	}
	public void setFatherName1(String fatherName1) {
		this.fatherName1 = fatherName1;
	}
	public String getMotherName1() {
		return motherName1;
	}
	public void setMotherName1(String motherName1) {
		this.motherName1 = motherName1;
	}
	public String getSpouseName1() {
		return spouseName1;
	}
	public void setSpouseName1(String spouseName1) {
		this.spouseName1 = spouseName1;
	}
	public String getGender1() {
		return gender1;
	}
	public void setGender1(String gender1) {
		this.gender1 = gender1;
	}
	public int getAge1() {
		return age1;
	}
	public void setAge1(int age1) {
		this.age1 = age1;
	}
	private String otherDocument;
    private String otherId;
    
    
    private String address;
    
    private String address1;
    
    private ArrayList countryList;
    private String countryName;
    private String countryId;
    
    private ArrayList stateList;
    private String stateName;
    private String stateId;
    
    private ArrayList districtList;
    private String districtName;
    private String districtId;
    
    private String districtId1;
    private String stateId1;
    private String countryId1;
    private String districtName1;
    
    public String getDistrictId1() {
		return districtId1;
	}
	public void setDistrictId1(String districtId1) {
		this.districtId1 = districtId1;
	}
	public String getStateId1() {
		return stateId1;
	}
	public void setStateId1(String stateId1) {
		this.stateId1 = stateId1;
	}
	public String getCountryId1() {
		return countryId1;
	}
	public void setCountryId1(String countryId1) {
		this.countryId1 = countryId1;
	}
	private String documentName;
    private String documentId;
    private ArrayList documentList;
    
    private String role1;
   
   

    
    private int postalCode;
    private String phoneNo;
    private String mobNo;
    private String mailId;
    
    private int postalCode1;
    public int getPostalCode1() {
		return postalCode1;
	}
	public void setPostalCode1(int postalCode1) {
		this.postalCode1 = postalCode1;
	}
	public String getPhoneNo1() {
		return phoneNo1;
	}
	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}
	public String getMobNo1() {
		return mobNo1;
	}
	public void setMobNo1(String mobNo1) {
		this.mobNo1 = mobNo1;
	}
	public String getMailId1() {
		return mailId1;
	}
	public void setMailId1(String mailId1) {
		this.mailId1 = mailId1;
	}
	private String phoneNo1;
    private String mobNo1;
    private String mailId1;
    
  

  //-------------Bank details variables
    
    
    private String bankName;
    private String bankAddress;
    
    
    private ArrayList bankCountryList;
    private String bankCountryName;
    private String bankCountryId;
    
    
    
    private String bankStateName;
    private String bankStateId;
    private ArrayList bankStateList;
    
    
    
    private String bankDistrictName;
    private String bankDistrictId;
    private ArrayList bankDistrictList;
    private String combineDataParty;
    
    private int bankPostalCode;
    
    private String bankPhone;
    
    //-------------Bank Person/Authority details variables
    
    private String bankPersonName;
    private String bankPersonDesi;
    private String bankPersonMob;
    private String bankPersonEmail;
    
    private String suppTxnId;
    private String suppTxnIdSearch;
    private String propertyTxnId;
    private String presentDate;
    private String executeDate;
    
    private double transactionAmount;
    
    
   
    //End-------------Partial Save Page 1
    
    
    
    
    
      //Start-------------Partial Save Page 2
    
   private String proptypeflag;
    private String stampDuty;
    private String stampDutyDisplay;
    private String stampTypeSelection;
    private double stampDutyNow;
    private String impoundcheck;
    	//--if e-stamp
    private String estampCode;
    private String estampCodeSearch;
    private String estampDuty;
    private String estampDate;
    private String estampPurpose;
 
    
    
    	//--if Physical Stamps
    
    public String getEstampPurpose() {
		return estampPurpose;
	}
	public void setEstampPurpose(String estampPurpose) {
		this.estampPurpose = estampPurpose;
	}
	private double denominationStamps;
    private String codeStamps;
    private String seriesNo;
    
    
    
    
    private String docNo;
    private String docDate;
    private String docStatus;
    
    	//--Purpose CheckBoxes
    private String purposeName;
    private String purposeId;
    private ArrayList purposeList;
    private ArrayList purposeList1;
    private String purposeCheckbox;
    private ArrayList<String> purposeNames;
    
    	//--AcqElemnts
    private String acqElements;
    
   //-Property Description Lists 
    	//--Property Type List
    private ArrayList propertyTypeList;
    private String propertyTypeId;
    private String propertyType;
    
    
    public ArrayList getDistListProperty() {
		return distListProperty;
	}
	public void setDistListProperty(ArrayList distListProperty) {
		this.distListProperty = distListProperty;
	}
	public String getDistrictProperty() {
		return districtProperty;
	}
	public void setDistrictProperty(String districtProperty) {
		this.districtProperty = districtProperty;
	}
	public String getDistrictIDProperty() {
		return districtIDProperty;
	}
	public void setDistrictIDProperty(String districtIDProperty) {
		this.districtIDProperty = districtIDProperty;
	}
	private String Type;
    private String TypeID;
    
    	//--District List
    private ArrayList distList;
    private String district;
    private String districtID;
    
    
    
    
  
    private String officeId;
    
    
    
    
  //--District List Page2
    private ArrayList distListProperty;
    private String districtProperty;
    private String districtPropertyName;
    private String districtIDProperty;
    
    	//--Tehshil List
    private ArrayList tehsilList;
    private String tehsil;
    private String tehsilID;
    
    
    	//--Mahalla List
    private ArrayList mahallaList;
    private String mahalla;
    private String mahallaId;
    
    	//--Ward List
    private ArrayList wardList;
    private String ward;
    private String wardId;
    private String wardPatwariId;
    
    	//--Muncipal Body List
    private ArrayList municipalList;
    private String municipalBody;
    private String municipalBodyID;
    
    	//--Area Type List
    private ArrayList areaTypeList;
    private String areaType;
    private String areaId;
    
    //--patwari
    
    private String patwariStatus;
	
    private ArrayList khasraDetlsDisplay=new ArrayList();
    private String vikasKhand;
    private String ricircle;
    private String layoutdet;
    private String khasaraNum;
    private String khasaraNum1;
  private String  khasraArea1;
  
  
  public ArrayList getKhasraDetlsDisplay() {
	return khasraDetlsDisplay;
}
public void setKhasraDetlsDisplay(ArrayList khasraDetlsDisplay) {
	this.khasraDetlsDisplay = khasraDetlsDisplay;
}
private String lagaan;
 private String propAddress;
 private String propLandmark;
    public String getPropAddress() {
	return propAddress;
}
public void setPropAddress(String propAddress) {
	this.propAddress = propAddress;
}
public String getPropLandmark() {
	return propLandmark;
}
public void setPropLandmark(String propLandmark) {
	this.propLandmark = propLandmark;
}
public String getTypefloor() {
	return typefloor;
}
public void setTypefloor(String typefloor) {
	this.typefloor = typefloor;
}
public String getTypeFloorId() {
	return typeFloorId;
}
public void setTypeFloorId(String typeFloorId) {
	this.typeFloorId = typeFloorId;
}
public String getPropTypeL1Name() {
	return propTypeL1Name;
}
public void setPropTypeL1Name(String propTypeL1Name) {
	this.propTypeL1Name = propTypeL1Name;
}
	private String khasraRowCount;
    private String nazoolstNum;
    private String rinpustikaNum;
    private String sheetnum;
    private String plotnum;
    
    //-- Four Boundary Details
    private String north;
    private String south;
    private String east;
    private String west;
    private String buildingFlag;
    
 // variables for integration with Scanner      					// Added by Lavi
	private String parentPathScan;
	private String fileNameScan;
	private String guidUpload;
	
	// end of variables for scanner integration

    
    
    //-- Property type list l2
    private ArrayList propTypeL2List;
    private String propTypeL2Id;
    private String propTypeL2;
    
    
    //-Unit List Hectare/ Square (KM)
    private ArrayList unitList;
    private String unitId;
    private String unitType;
    private String unitTypeId;
    private String unitNameTemp;
    
    public String getUnitNameTemp() {
		return unitNameTemp;
	}
	public void setUnitNameTemp(String unitNameTemp) {
		this.unitNameTemp = unitNameTemp;
	}
	private int area;
    private int areaConstructed;
    
    private String deleteFloorID;
    private String typeFloorName;
    private String typeFloorNameTemp;
    private String unit;
    private String typefloor;
    private String typeFloorId;
    
    private ArrayList propTypeL1List;
    private String propTypeL1;
    private String propTypeL1Id;
    private String propTypeL1Name;
    private String typeUsageTemp;
    
    
    public String getTypeUsageTemp() {
		return typeUsageTemp;
	}
	public void setTypeUsageTemp(String typeUsageTemp) {
		this.typeUsageTemp = typeUsageTemp;
	}
	private String rinPustikaArray;
    public String getRinPustikaArray() {
		return rinPustikaArray;
	}
	public void setRinPustikaArray(String rinPustikaArray) {
		this.rinPustikaArray = rinPustikaArray;
	}
	public String getLagaanArray() {
		return lagaanArray;
	}
	public void setLagaanArray(String lagaanArray) {
		this.lagaanArray = lagaanArray;
	}
	public String getKhasraNoArray() {
		return khasraNoArray;
	}
	public void setKhasraNoArray(String khasraNoArray) {
		this.khasraNoArray = khasraNoArray;
	}
	public String getTotalCalculatedArea() {
		return totalCalculatedArea;
	}
	public void setTotalCalculatedArea(String totalCalculatedArea) {
		this.totalCalculatedArea = totalCalculatedArea;
	}
	public String getKhasraAreaArray() {
		return khasraAreaArray;
	}
	public void setKhasraAreaArray(String khasraAreaArray) {
		this.khasraAreaArray = khasraAreaArray;
	}
	private String lagaanArray;
    private String khasraNoArray;
    private String totalCalculatedArea;
    private String khasraAreaArray;
    
    
    
    
    public HashMap getMapBuilding() {
		return mapBuilding;
	}
	public void setMapBuilding(HashMap mapBuilding) {
		this.mapBuilding = mapBuilding;
	}
	public String getDeleteFloorID() {
		return deleteFloorID;
	}
	public void setDeleteFloorID(String deleteFloorID) {
		this.deleteFloorID = deleteFloorID;
	}
	public String getTypeFloorName() {
		return typeFloorName;
	}
	public void setTypeFloorName(String typeFloorName) {
		this.typeFloorName = typeFloorName;
	}
	public ArrayList getPropTypeL1List() {
		return propTypeL1List;
	}
	public void setPropTypeL1List(ArrayList propTypeL1List) {
		this.propTypeL1List = propTypeL1List;
	}
	
	public String getPropTypeL1Id() {
		return propTypeL1Id;
	}
	public void setPropTypeL1Id(String propTypeL1Id) {
		this.propTypeL1Id = propTypeL1Id;
	}
	public String getPropTypeL1() {
		return propTypeL1;
	}
	public void setPropTypeL1(String propTypeL1) {
		this.propTypeL1 = propTypeL1;
	}

	public int getAddMoreFloorCounter() {
		return addMoreFloorCounter;
	}
	public void setAddMoreFloorCounter(int addMoreFloorCounter) {
		this.addMoreFloorCounter = addMoreFloorCounter;
	}
	private int addMoreFloorCounter;
  
    
    public ArrayList getUnitList() {
		return unitList;
	}
	public void setUnitList(ArrayList unitList) {
		this.unitList = unitList;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	
	
	public ArrayList getFloorTypeList() {
		return floorTypeList;
	}
	public void setFloorTypeList(ArrayList floorTypeList) {
		this.floorTypeList = floorTypeList;
	}
	public String getTypeFloorDesc() {
		return typeFloorDesc;
	}
	public void setTypeFloorDesc(String typeFloorDesc) {
		this.typeFloorDesc = typeFloorDesc;
	}
	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
	private ArrayList floorTypeList;
    private String typeFloorDesc;
    private String floorId;
    
    
    
    //---
    private  String dateOfPresentation;
    private String srName;
    private String sroName;
    
    
    private FormFile scannedCopy = null;
    private String scannedCopyName;
    private String scannedCopyPath;
    private String scannedCopySize;
    
    //--Book Types
    private String bookTypeId;
    public String getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(String bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	public String getBookTypeName() {
		return bookTypeName;
	}
	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}
	public ArrayList getBookTypeList() {
		return bookTypeList;
	}
	public void setBookTypeList(ArrayList bookTypeList) {
		this.bookTypeList = bookTypeList;
	}
	private String bookTypeName;
    private ArrayList bookTypeList;
    
    
    
	
	public FormFile getScannedCopy() {
		return scannedCopy;
	}
	public void setScannedCopy(FormFile scannedCopy) {
		this.scannedCopy = scannedCopy;
	}
	public String getScannedCopyName() {
		return scannedCopyName;
	}
	public void setScannedCopyName(String scannedCopyName) {
		this.scannedCopyName = scannedCopyName;
	}
	public String getScannedCopyPath() {
		return scannedCopyPath;
	}
	public void setScannedCopyPath(String scannedCopyPath) {
		this.scannedCopyPath = scannedCopyPath;
	}
	//Start Temp Variables
    private String countryName2;
    private String stateName2;
    private String districtName2;
    private String casteName2;
    private String religionName2;
    private String photoProofTypeName2;
    private String bankCountryName2;
    private String bankStateName2;
    private String bankDistrictName2;
    private String loanPurposeName2;
    
    //End Temp variables
    
    
	private String reportMsg;
	private String errorMsg=null;
	private String searchFromDate;
	private String searchToDate;
	private int serialNo;
    private String estampCodeSearch1;
	
	//Start-------------Partial Save Page 2
	
	/**
	 * @return the serialNo
	 */
	public int getSerialNo() {
		return serialNo;
	}
	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public String getCurrentUserId() {
		return currentUserId;
	}
	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
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
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the mname
	 */
	public String getMname() {
		return mname;
	}
	/**
	 * @param mname the mname to set
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}
	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}
	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	/**
	 * @return the spouseName
	 */
	public String getSpouseName() {
		return spouseName;
	}
	/**
	 * @param spouseName the spouseName to set
	 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
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
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the postalCode
	 */
	public int getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the mobNo
	 */
	public String getMobNo() {
		return mobNo;
	}
	/**
	 * @param mobNo the mobNo to set
	 */
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	/**
	 * @return the mailId
	 */
	public String getMailId() {
		return mailId;
	}
	/**
	 * @param mailId the mailId to set
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	/**
	 * @return the photoId
	 */

	/**
	 * @return the photoIdList
	 */
	
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the bankAddress
	 */
	public String getBankAddress() {
		return bankAddress;
	}
	/**
	 * @param bankAddress the bankAddress to set
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	/**
	 * @return the occupation
	 */
	
	/**
	 * @return the reportMsg
	 */
	public String getReportMsg() {
		return reportMsg;
	}
	/**
	 * @param reportMsg the reportMsg to set
	 */
	public void setReportMsg(String reportMsg) {
		this.reportMsg = reportMsg;
	}
	/**
	 * @return the bankCountryList
	 */
	public ArrayList getBankCountryList() {
		return bankCountryList;
	}
	/**
	 * @param bankCountryList the bankCountryList to set
	 */
	public void setBankCountryList(ArrayList bankCountryList) {
		this.bankCountryList = bankCountryList;
	}
	/**
	 * @return the bankCountryName
	 */
	public String getBankCountryName() {
		return bankCountryName;
	}
	/**
	 * @param bankCountryName the bankCountryName to set
	 */
	public void setBankCountryName(String bankCountryName) {
		this.bankCountryName = bankCountryName;
	}
	/**
	 * @return the bankCountryId
	 */
	public String getBankCountryId() {
		return bankCountryId;
	}
	/**
	 * @param bankCountryId the bankCountryId to set
	 */
	public void setBankCountryId(String bankCountryId) {
		this.bankCountryId = bankCountryId;
	}
	/**
	 * @return the bankStateName
	 */
	public String getBankStateName() {
		return bankStateName;
	}
	/**
	 * @param bankStateName the bankStateName to set
	 */
	public void setBankStateName(String bankStateName) {
		this.bankStateName = bankStateName;
	}
	/**
	 * @return the bankStateId
	 */
	public String getBankStateId() {
		return bankStateId;
	}
	/**
	 * @param bankStateId the bankStateId to set
	 */
	public void setBankStateId(String bankStateId) {
		this.bankStateId = bankStateId;
	}
	/**
	 * @return the bankStateList
	 */
	public ArrayList getBankStateList() {
		return bankStateList;
	}
	/**
	 * @param bankStateList the bankStateList to set
	 */
	public void setBankStateList(ArrayList bankStateList) {
		this.bankStateList = bankStateList;
	}
	/**
	 * @return the bankDistrictName
	 */
	public String getBankDistrictName() {
		return bankDistrictName;
	}
	/**
	 * @param bankDistrictName the bankDistrictName to set
	 */
	public void setBankDistrictName(String bankDistrictName) {
		this.bankDistrictName = bankDistrictName;
	}
	/**
	 * @return the bankDistrictId
	 */
	public String getBankDistrictId() {
		return bankDistrictId;
	}
	/**
	 * @param bankDistrictId the bankDistrictId to set
	 */
	public void setBankDistrictId(String bankDistrictId) {
		this.bankDistrictId = bankDistrictId;
	}
	/**
	 * @return the bankDistrictList
	 */
	public ArrayList getBankDistrictList() {
		return bankDistrictList;
	}
	/**
	 * @param bankDistrictList the bankDistrictList to set
	 */
	public void setBankDistrictList(ArrayList bankDistrictList) {
		this.bankDistrictList = bankDistrictList;
	}
	/**
	 * @return the loanPurposeId
	 */
	
	/**
	 * @return the bankPostalCode
	 */
	public int getBankPostalCode() {
		return bankPostalCode;
	}
	/**
	 * @param bankPostalCode the bankPostalCode to set
	 */
	public void setBankPostalCode(int bankPostalCode) {
		this.bankPostalCode = bankPostalCode;
	}
	/**
	 * @return the bankPhone
	 */
	public String getBankPhone() {
		return bankPhone;
	}
	/**
	 * @param bankPhone the bankPhone to set
	 */
	public void setBankPhone(String bankPhone) {
		this.bankPhone = bankPhone;
	}
	/**
	 * @return the bankPersonName
	 */
	public String getBankPersonName() {
		return bankPersonName;
	}
	/**
	 * @param bankPersonName the bankPersonName to set
	 */
	public void setBankPersonName(String bankPersonName) {
		this.bankPersonName = bankPersonName;
	}
	/**
	 * @return the bankPersonDesi
	 */
	public String getBankPersonDesi() {
		return bankPersonDesi;
	}
	/**
	 * @param bankPersonDesi the bankPersonDesi to set
	 */
	public void setBankPersonDesi(String bankPersonDesi) {
		this.bankPersonDesi = bankPersonDesi;
	}
	/**
	 * @return the bankPersonMob
	 */
	public String getBankPersonMob() {
		return bankPersonMob;
	}
	/**
	 * @param bankPersonMob the bankPersonMob to set
	 */
	public void setBankPersonMob(String bankPersonMob) {
		this.bankPersonMob = bankPersonMob;
	}
	/**
	 * @return the bankPersonEmail
	 */
	public String getBankPersonEmail() {
		return bankPersonEmail;
	}
	/**
	 * @param bankPersonEmail the bankPersonEmail to set
	 */
	public void setBankPersonEmail(String bankPersonEmail) {
		this.bankPersonEmail = bankPersonEmail;
	}
	/**
	 * @return the presentDate
	 */
	public String getPresentDate() {
		return presentDate;
	}
	/**
	 * @param presentDate the presentDate to set
	 */
	public void setPresentDate(String presentDate) {
		this.presentDate = presentDate;
	}
	/**
	 * @return the executeDate
	 */
	public String getExecuteDate() {
		return executeDate;
	}
	/**
	 * @param executeDate the executeDate to set
	 */
	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}
	/**
	 * @return the estampCode
	 */
	public String getEstampCode() {
		return estampCode;
	}
	/**
	 * @param estampCode the estampCode to set
	 */
	public void setEstampCode(String estampCode) {
		this.estampCode = estampCode;
	}
	/**
	 * @return the estampCodeSearch
	 */
	public String getEstampCodeSearch() {
		return estampCodeSearch;
	}
	/**
	 * @param estampCodeSearch the estampCodeSearch to set
	 */
	public void setEstampCodeSearch(String estampCodeSearch) {
		this.estampCodeSearch = estampCodeSearch;
	}
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	/**
	 * @return the estampDuty
	 */
	public String getEstampDuty() {
		return estampDuty;
	}
	/**
	 * @param estampDuty the estampDuty to set
	 */
	public void setEstampDuty(String estampDuty) {
		this.estampDuty = estampDuty;
	}
	/**
	 * @return the estampDate
	 */
	public String getEstampDate() {
		return estampDate;
	}
	/**
	 * @param estampDate the estampDate to set
	 */
	public void setEstampDate(String estampDate) {
		this.estampDate = estampDate;
	}
	/**
	 * @return the countryName2
	 */
	public String getCountryName2() {
		return countryName2;
	}
	/**
	 * @param countryName2 the countryName2 to set
	 */
	public void setCountryName2(String countryName2) {
		this.countryName2 = countryName2;
	}
	/**
	 * @return the stateName2
	 */
	public String getStateName2() {
		return stateName2;
	}
	/**
	 * @param stateName2 the stateName2 to set
	 */
	public void setStateName2(String stateName2) {
		this.stateName2 = stateName2;
	}
	/**
	 * @return the districtName2
	 */
	public String getDistrictName2() {
		return districtName2;
	}
	/**
	 * @param districtName2 the districtName2 to set
	 */
	public void setDistrictName2(String districtName2) {
		this.districtName2 = districtName2;
	}
	/**
	 * @return the casteName2
	 */
	public String getCasteName2() {
		return casteName2;
	}
	/**
	 * @param casteName2 the casteName2 to set
	 */
	public void setCasteName2(String casteName2) {
		this.casteName2 = casteName2;
	}
	/**
	 * @return the religionName2
	 */
	public String getReligionName2() {
		return religionName2;
	}
	/**
	 * @param religionName2 the religionName2 to set
	 */
	public void setReligionName2(String religionName2) {
		this.religionName2 = religionName2;
	}
	/**
	 * @return the photoProofTypeName2
	 */
	public String getPhotoProofTypeName2() {
		return photoProofTypeName2;
	}
	/**
	 * @param photoProofTypeName2 the photoProofTypeName2 to set
	 */
	public void setPhotoProofTypeName2(String photoProofTypeName2) {
		this.photoProofTypeName2 = photoProofTypeName2;
	}
	/**
	 * @return the bankCountryName2
	 */
	public String getBankCountryName2() {
		return bankCountryName2;
	}
	/**
	 * @param bankCountryName2 the bankCountryName2 to set
	 */
	public void setBankCountryName2(String bankCountryName2) {
		this.bankCountryName2 = bankCountryName2;
	}
	/**
	 * @return the bankStateName2
	 */
	public String getBankStateName2() {
		return bankStateName2;
	}
	/**
	 * @param bankStateName2 the bankStateName2 to set
	 */
	public void setBankStateName2(String bankStateName2) {
		this.bankStateName2 = bankStateName2;
	}
	/**
	 * @return the bankDistrictName2
	 */
	public String getBankDistrictName2() {
		return bankDistrictName2;
	}
	/**
	 * @param bankDistrictName2 the bankDistrictName2 to set
	 */
	public void setBankDistrictName2(String bankDistrictName2) {
		this.bankDistrictName2 = bankDistrictName2;
	}
	/**
	 * @return the deedTypeName2
	 */

	/**
	 * @return the photoBankName
	 */
	

	/**
	 * @return the docNo
	 */
	public String getDocNo() {
		return docNo;
	}
	/**
	 * @param docNo the docNo to set
	 */
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	/**
	 * @return the stampDuty
	 */
	public String getStampDuty() {
		return stampDuty;
	}
	/**
	 * @param stampDuty the stampDuty to set
	 */
	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}
	/**
	 * @return the searchFromDate
	 */
	public String getSearchFromDate() {
		return searchFromDate;
	}
	/**
	 * @param searchFromDate the searchFromDate to set
	 */
	public void setSearchFromDate(String searchFromDate) {
		this.searchFromDate = searchFromDate;
	}
	/**
	 * @return the searchToDate
	 */
	public String getSearchToDate() {
		return searchToDate;
	}
	/**
	 * @param searchToDate the searchToDate to set
	 */
	public void setSearchToDate(String searchToDate) {
		this.searchToDate = searchToDate;
	}
	/**
	 * @return the docDate
	 */
	public String getDocDate() {
		return docDate;
	}
	/**
	 * @param docDate the docDate to set
	 */
	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}
	/**
	 * @return the docStatus
	 */
	public String getDocStatus() {
		return docStatus;
	}
	/**
	 * @param docStatus the docStatus to set
	 */
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	/**
	 * @return the suppTxnId
	 */
	public String getSuppTxnId() {
		return suppTxnId;
	}
	/**
	 * @param suppTxnId the suppTxnId to set
	 */
	public void setSuppTxnId(String suppTxnId) {
		this.suppTxnId = suppTxnId;
	}
	/**
	 * @return the propertyTxnId
	 */
	public String getPropertyTxnId() {
		return propertyTxnId;
	}
	/**
	 * @param propertyTxnId the propertyTxnId to set
	 */
	public void setPropertyTxnId(String propertyTxnId) {
		this.propertyTxnId = propertyTxnId;
	}
	/**
	 * @return the suppTxnIdSearch
	 */
	public String getSuppTxnIdSearch() {
		return suppTxnIdSearch;
	}
	/**
	 * @param suppTxnIdSearch the suppTxnIdSearch to set
	 */
	public void setSuppTxnIdSearch(String suppTxnIdSearch) {
		this.suppTxnIdSearch = suppTxnIdSearch;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public ArrayList getDocumentList() {
		return documentList;
	}
	public void setDocumentList(ArrayList documentList) {
		this.documentList = documentList;
	}
	public String getOtherId() {
		return otherId;
	}
	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}
	public String getOtherDocument() {
		return otherDocument;
	}
	public void setOtherDocument(String otherDocument) {
		this.otherDocument = otherDocument;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	   
    public String getPurposeName() {
		return purposeName;
	}
	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}
	public String getPurposeId() {
		return purposeId;
	}
	public void setPurposeId(String purposeId) {
		this.purposeId = purposeId;
	}
	public ArrayList getPurposeList() {
		return purposeList;
	}
	public void setPurposeList(ArrayList purposeList) {
		this.purposeList = purposeList;
	}
		    
	
	public double getDenominationStamps() {
		return denominationStamps;
	}
	public void setDenominationStamps(double denominationStamps) {
		this.denominationStamps = denominationStamps;
	}
	public String getCodeStamps() {
		return codeStamps;
	}
	public void setCodeStamps(String codeStamps) {
		this.codeStamps = codeStamps;
	}
	public String getSeriesNo() {
		return seriesNo;
	}
	public void setSeriesNo(String seriesNo) {
		this.seriesNo = seriesNo;
	}
	public String getAcqElements() {
		return acqElements;
	}
	public void setAcqElements(String acqElements) {
		this.acqElements = acqElements;
	}
	
	
	public ArrayList getPropertyTypeList() {
		return propertyTypeList;
	}
	public void setPropertyTypeList(ArrayList propertyTypeList) {
		this.propertyTypeList = propertyTypeList;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getTypeID() {
		return TypeID;
	}
	public void setTypeID(String typeID) {
		TypeID = typeID;
	}
	public ArrayList getDistList() {
		return distList;
	}
	public void setDistList(ArrayList distList) {
		this.distList = distList;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDistrictID() {
		return districtID;
	}
	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}
	public ArrayList getTehsilList() {
		return tehsilList;
	}
	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}
	public String getTehsil() {
		return tehsil;
	}
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}
	public String getTehsilID() {
		return tehsilID;
	}
	public void setTehsilID(String tehsilID) {
		this.tehsilID = tehsilID;
	}
	public ArrayList getMahallaList() {
		return mahallaList;
	}
	public void setMahallaList(ArrayList mahallaList) {
		this.mahallaList = mahallaList;
	}
	public String getMahalla() {
		return mahalla;
	}
	public void setMahalla(String mahalla) {
		this.mahalla = mahalla;
	}
	public String getMahallaId() {
		return mahallaId;
	}
	public void setMahallaId(String mahallaId) {
		this.mahallaId = mahallaId;
	}
	public ArrayList getWardList() {
		return wardList;
	}
	public void setWardList(ArrayList wardList) {
		this.wardList = wardList;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getWardId() {
		return wardId;
	}
	public void setWardId(String wardId) {
		this.wardId = wardId;
	}
	public ArrayList getMunicipalList() {
		return municipalList;
	}
	public void setMunicipalList(ArrayList municipalList) {
		this.municipalList = municipalList;
	}
	public String getMunicipalBody() {
		return municipalBody;
	}
	public void setMunicipalBody(String municipalBody) {
		this.municipalBody = municipalBody;
	}
	public String getMunicipalBodyID() {
		return municipalBodyID;
	}
	public void setMunicipalBodyID(String municipalBodyID) {
		this.municipalBodyID = municipalBodyID;
	}
	public ArrayList getAreaTypeList() {
		return areaTypeList;
	}
	public void setAreaTypeList(ArrayList areaTypeList) {
		this.areaTypeList = areaTypeList;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getPatwariStatus() {
		return patwariStatus;
	}
	public void setPatwariStatus(String patwariStatus) {
		this.patwariStatus = patwariStatus;
	}
	public String getStampTypeSelection() {
		return stampTypeSelection;
	}
	public void setStampTypeSelection(String stampTypeSelection) {
		this.stampTypeSelection = stampTypeSelection;
	}
	
	public String getRicircle() {
		return ricircle;
	}
	public void setRicircle(String ricircle) {
		this.ricircle = ricircle;
	}
	public String getLayoutdet() {
		return layoutdet;
	}
	public void setLayoutdet(String layoutdet) {
		this.layoutdet = layoutdet;
	}
	public String getKhasaraNum() {
		return khasaraNum;
	}
	public void setKhasaraNum(String khasaraNum) {
		this.khasaraNum = khasaraNum;
	}
	public String getNazoolstNum() {
		return nazoolstNum;
	}
	public void setNazoolstNum(String nazoolstNum) {
		this.nazoolstNum = nazoolstNum;
	}
	public String getRinpustikaNum() {
		return rinpustikaNum;
	}
	public void setRinpustikaNum(String rinpustikaNum) {
		this.rinpustikaNum = rinpustikaNum;
	}
	public String getNorth() {
		return north;
	}
	public void setNorth(String north) {
		this.north = north;
	}
	public String getSouth() {
		return south;
	}
	public void setSouth(String south) {
		this.south = south;
	}
	public String getEast() {
		return east;
	}
	public void setEast(String east) {
		this.east = east;
	}
	public String getWest() {
		return west;
	}
	public void setWest(String west) {
		this.west = west;
	}
	public String getDateOfPresentation() {
		return dateOfPresentation;
	}
	public void setDateOfPresentation(String dateOfPresentation) {
		this.dateOfPresentation = dateOfPresentation;
	}
	public String getSrName() {
		return srName;
	}
	public void setSrName(String srName) {
		this.srName = srName;
	}
	public String getSroName() {
		return sroName;
	}
	public void setSroName(String sroName) {
		this.sroName = sroName;
	}
	public String getLoanPurposeName2() {
		return loanPurposeName2;
	}
	public void setLoanPurposeName2(String loanPurposeName2) {
		this.loanPurposeName2 = loanPurposeName2;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getStampType() {
		return stampType;
	}
	public void setStampType(String stampType) {
		this.stampType = stampType;
	}
	private String role;
	
	public String getErrorDate() {
		return errorDate;
	}
	public void setErrorDate(String errorDate) {
		this.errorDate = errorDate;
	}
	public String getPurposeCheckbox() {
		return purposeCheckbox;
	}
	public void setPurposeCheckbox(String purposeCheckbox) {
		this.purposeCheckbox = purposeCheckbox;
	}
	public ArrayList getPurposeList1() {
		return purposeList1;
	}
	public void setPurposeList1(ArrayList purposeList1) {
		this.purposeList1 = purposeList1;
	}
	public double getStampDutyNow() {
		return stampDutyNow;
	}
	public void setStampDutyNow(double stampDutyNow) {
		this.stampDutyNow = stampDutyNow;
	}
	public int getCounterPStamp() {
		return counterPStamp;
	}
	public void setCounterPStamp(int counterPStamp) {
		this.counterPStamp = counterPStamp;
	}
	public int getCounterEStamp() {
		return counterEStamp;
	}
	public void setCounterEStamp(int counterEStamp) {
		this.counterEStamp = counterEStamp;
	}
	public String getKeysStringPurpose() {
		return keysStringPurpose;
	}
	public void setKeysStringPurpose(String keysStringPurpose) {
		this.keysStringPurpose = keysStringPurpose;
	}
	public String getWardPatwariId() {
		return wardPatwariId;
	}
	public void setWardPatwariId(String wardPatwariId) {
		this.wardPatwariId = wardPatwariId;
	}
	public String getPropertyTypeId() {
		return propertyTypeId;
	}
	public void setPropertyTypeId(String propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	
	public ArrayList getPropTypeL2List() {
		return propTypeL2List;
	}
	public void setPropTypeL2List(ArrayList propTypeL2List) {
		this.propTypeL2List = propTypeL2List;
	}
	public String getPropTypeL2Id() {
		return propTypeL2Id;
	}
	public void setPropTypeL2Id(String propTypeL2Id) {
		this.propTypeL2Id = propTypeL2Id;
	}
	public String getPropTypeL2() {
		return propTypeL2;
	}
	public void setPropTypeL2(String propTypeL2) {
		this.propTypeL2 = propTypeL2;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getAreaConstructed() {
		return areaConstructed;
	}
	public void setAreaConstructed(int areaConstructed) {
		this.areaConstructed = areaConstructed;
	}
	public String getTypeFloorNameTemp() {
		return typeFloorNameTemp;
	}
	public void setTypeFloorNameTemp(String typeFloorNameTemp) {
		this.typeFloorNameTemp = typeFloorNameTemp;
	}
	public String getVikasKhand() {
		return vikasKhand;
	}
	public void setVikasKhand(String vikasKhand) {
		this.vikasKhand = vikasKhand;
	}
	public String getSheetnum() {
		return sheetnum;
	}
	public void setSheetnum(String sheetnum) {
		this.sheetnum = sheetnum;
	}
	public String getPlotnum() {
		return plotnum;
	}
	public void setPlotnum(String plotnum) {
		this.plotnum = plotnum;
	}
	public String getKhasraRowCount() {
		return khasraRowCount;
	}
	public void setKhasraRowCount(String khasraRowCount) {
		this.khasraRowCount = khasraRowCount;
	}
	public String getKhasaraNum1() {
		return khasaraNum1;
	}
	public void setKhasaraNum1(String khasaraNum1) {
		this.khasaraNum1 = khasaraNum1;
	}
	public String getKhasraArea1() {
		return khasraArea1;
	}
	public void setKhasraArea1(String khasraArea1) {
		this.khasraArea1 = khasraArea1;
	}
	public String getLagaan() {
		return lagaan;
	}
	public void setLagaan(String lagaan) {
		this.lagaan = lagaan;
	}
	public String getScannedCopySize() {
		return scannedCopySize;
	}
	public void setScannedCopySize(String scannedCopySize) {
		this.scannedCopySize = scannedCopySize;
	}
	public String getDistrictPropertyName() {
		return districtPropertyName;
	}
	public void setDistrictPropertyName(String districtPropertyName) {
		this.districtPropertyName = districtPropertyName;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getDummy() {
		return dummy;
	}
	public void setDummy(String dummy) {
		this.dummy = dummy;
	}
	public String getHdnDeleteFloorID() {
		return hdnDeleteFloorID;
	}
	public void setHdnDeleteFloorID(String hdnDeleteFloorID) {
		this.hdnDeleteFloorID = hdnDeleteFloorID;
	}
	public String getUnitTypeId() {
		return unitTypeId;
	}
	public void setUnitTypeId(String unitTypeId) {
		this.unitTypeId = unitTypeId;
	}
	public String getFilingNumber() {
		return filingNumber;
	}
	public void setFilingNumber(String filingNumber) {
		this.filingNumber = filingNumber;
	}

	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getCombineData() {
		return combineData;
	}
	public void setCombineData(String combineData) {
		this.combineData = combineData;
	}
	public ArrayList getPendingDetails() {
		return pendingDetails;
	}
	public void setPendingDetails(ArrayList pendingDetails) {
		this.pendingDetails = pendingDetails;
	}
	public ArrayList getPartyDetails() {
		return partyDetails;
	}
	public void setPartyDetails(ArrayList partyDetails) {
		this.partyDetails = partyDetails;
	}
	public String getCombineDataParty() {
		return combineDataParty;
	}
	public void setCombineDataParty(String combineDataParty) {
		this.combineDataParty = combineDataParty;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getRole1() {
		return role1;
	}
	public void setRole1(String role1) {
		this.role1 = role1;
	}
	public String getDistrictName1() {
		return districtName1;
	}
	public void setDistrictName1(String districtName1) {
		this.districtName1 = districtName1;
	}
	public ArrayList<String> getPurposeNames() {
		return purposeNames;
	}
	public void setPurposeNames(ArrayList<String> purposeNames) {
		this.purposeNames = purposeNames;
	}
	public String getPurposeNames1() {
		return purposeNames1;
	}
	public void setPurposeNames1(String purposeNames1) {
		this.purposeNames1 = purposeNames1;
	}
	public String getImpoundcheck() {
		return impoundcheck;
	}
	public void setImpoundcheck(String impoundcheck) {
		this.impoundcheck = impoundcheck;
	}
	public String getProptypeflag() {
		return proptypeflag;
	}
	public void setProptypeflag(String proptypeflag) {
		this.proptypeflag = proptypeflag;
	}
	public String getImpound() {
		return impound;
	}
	public void setImpound(String impound) {
		this.impound = impound;
	}
	public String getCasecheck() {
		return casecheck;
	}
	public void setCasecheck(String casecheck) {
		this.casecheck = casecheck;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getDays() {
		return days;
	}
	public void setEstampCodeSearch1(String estampCodeSearch1) {
		this.estampCodeSearch1 = estampCodeSearch1;
	}
	public String getEstampCodeSearch1() {
		return estampCodeSearch1;
	}
	public void setBuildingFlag(String buildingFlag) {
		this.buildingFlag = buildingFlag;
	}
	public String getBuildingFlag() {
		return buildingFlag;
	}
	public void setSubAreaList(ArrayList<SuppleDocDTO> subAreaList) {
		this.subAreaList = subAreaList;
	}
	public ArrayList<SuppleDocDTO> getSubAreaList() {
		return subAreaList;
	}
	public void setWardPatwariName(String wardPatwariName) {
		this.wardPatwariName = wardPatwariName;
	}
	public String getWardPatwariName() {
		return wardPatwariName;
	}
	public void setWardPatwariList(ArrayList<SuppleDocDTO> wardPatwariList) {
		this.wardPatwariList = wardPatwariList;
	}
	public ArrayList<SuppleDocDTO> getWardPatwariList() {
		return wardPatwariList;
	}
	public void setColonyList(ArrayList<SuppleDocDTO> colonyList) {
		this.colonyList = colonyList;
	}
	public ArrayList<SuppleDocDTO> getColonyList() {
		return colonyList;
	}
	public void setWardIds(String wardIds) {
		this.wardIds = wardIds;
	}
	public String getWardIds() {
		return wardIds;
	}
	public void setStampDutyDisplay(String stampDutyDisplay) {
		this.stampDutyDisplay = stampDutyDisplay;
	}
	public String getStampDutyDisplay() {
		return stampDutyDisplay;
	}
	public void setExemptions(String exemptions) {
		this.exemptions = exemptions;
	}
	public String getExemptions() {
		return exemptions;
	}
	public void setCheckBoxVerified(String checkBoxVerified) {
		this.checkBoxVerified = checkBoxVerified;
	}
	public String getCheckBoxVerified() {
		return checkBoxVerified;
	}
	public void setNoOfPages(String noOfPages) {
		this.noOfPages = noOfPages;
	}
	public String getNoOfPages() {
		return noOfPages;
	}
	public void setRecieptDate(String recieptDate) {
		this.recieptDate = recieptDate;
	}
	public String getRecieptDate() {
		return recieptDate;
	}


	
}