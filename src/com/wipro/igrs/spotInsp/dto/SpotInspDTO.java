package com.wipro.igrs.spotInsp.dto;
/**
 * ===========================================================================
 * File           :   VisitBookingDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Pavani param
 * Created Date   :   Apr 08, 2008.

 * ===========================================================================
 */
import java.util.ArrayList;

import com.wipro.igrs.suppleDoc.dto.SuppleDocDTO;

public class SpotInspDTO implements Cloneable {
	
   @Override
	public SpotInspDTO clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return ((SpotInspDTO)super.clone());
	}
private  boolean blnPerVal;
   private String deedType; 
   private ArrayList deedTypeList;
   private String name;
   private String postInspectionComment;
   private String reInspectionComments;
   public String getPostInspectionComment() {
	return postInspectionComment;
}
public void setPostInspectionComment(String postInspectionComment) {
	this.postInspectionComment = postInspectionComment;
}
private String status;
   private String noOfDocuments;
   private String foundStatus;
   private String value;
   private String country;
   private String userName;
   private String userId;
   private String language;
   private String wardIds;
   private String wardPatwariName;
   private String subAreaId;
   private String colonyId;
	private String colonyName;
	private ArrayList<SuppleDocDTO> wardPatwariList;
	private String subAreaName;
   
	
	private String jandpadDuty;
	private String municipalDuty;
	public String getNewJandpadDuty() {
		return newJandpadDuty;
	}
	public void setNewJandpadDuty(String newJandpadDuty) {
		this.newJandpadDuty = newJandpadDuty;
	}
	public String getNewMunicipalDuty() {
		return newMunicipalDuty;
	}
	public void setNewMunicipalDuty(String newMunicipalDuty) {
		this.newMunicipalDuty = newMunicipalDuty;
	}
	public String getNewGovStampDuty() {
		return newGovStampDuty;
	}
	public void setNewGovStampDuty(String newGovStampDuty) {
		this.newGovStampDuty = newGovStampDuty;
	}
	public String getNewUpkarDuty() {
		return newUpkarDuty;
	}
	public void setNewUpkarDuty(String newUpkarDuty) {
		this.newUpkarDuty = newUpkarDuty;
	}
	public String getNewTotalDuty() {
		return newTotalDuty;
	}
	public void setNewTotalDuty(String newTotalDuty) {
		this.newTotalDuty = newTotalDuty;
	}
	private String govStampDuty;
	private String upkarDuty;
	private String totalDuty;
	
	private String newJandpadDuty;
	private String newMunicipalDuty;
	private String newGovStampDuty;
	private String newUpkarDuty;
	private String newTotalDuty;
	
   public String getJandpadDuty() {
		return jandpadDuty;
	}
	public void setJandpadDuty(String jandpadDuty) {
		this.jandpadDuty = jandpadDuty;
	}
	public String getMunicipalDuty() {
		return municipalDuty;
	}
	public void setMunicipalDuty(String municipalDuty) {
		this.municipalDuty = municipalDuty;
	}
	public String getGovStampDuty() {
		return govStampDuty;
	}
	public void setGovStampDuty(String govStampDuty) {
		this.govStampDuty = govStampDuty;
	}
	public String getUpkarDuty() {
		return upkarDuty;
	}
	public void setUpkarDuty(String upkarDuty) {
		this.upkarDuty = upkarDuty;
	}
public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
private String newArea;
   private String crossArea;
   private String newConstructedArea;
   private String crossConstructedArea;
   private String newTypeOfConstruction;
   private String crossTypeOfConstruction;
   private String newPropertyUse;
   private String crossPropertyUse;
   private String crossMarketValue;
   private String crossGuideLineValue;
   public String getCrossArea() {
	return crossArea;
}
public void setCrossArea(String crossArea) {
	this.crossArea = crossArea;
}
public String getCrossConstructedArea() {
	return crossConstructedArea;
}
public void setCrossConstructedArea(String crossConstructedArea) {
	this.crossConstructedArea = crossConstructedArea;
}
public String getCrossTypeOfConstruction() {
	return crossTypeOfConstruction;
}
public void setCrossTypeOfConstruction(String crossTypeOfConstruction) {
	this.crossTypeOfConstruction = crossTypeOfConstruction;
}
public String getCrossPropertyUse() {
	return crossPropertyUse;
}
public void setCrossPropertyUse(String crossPropertyUse) {
	this.crossPropertyUse = crossPropertyUse;
}
public String getCrossMarketValue() {
	return crossMarketValue;
}
public void setCrossMarketValue(String crossMarketValue) {
	this.crossMarketValue = crossMarketValue;
}
public String getCrossGuideLineValue() {
	return crossGuideLineValue;
}
public void setCrossGuideLineValue(String crossGuideLineValue) {
	this.crossGuideLineValue = crossGuideLineValue;
}
private String reArea;
   private String newRegfee;
   private String newStampDuty;
   private String remark;
   private String newRemark;
   private String digName;
   private String complaintId;
   
   private String crossRegFee;
   private String crossStampDuty;
   private String crossRemark;
   private String crossMuncipalDuty;
   private String crossJanpadDuty;
   private String crossUpkarDuty;
   private String crossTotalDuty;
   //added by saurav
   private String docCompletionSR;
   
   public String getCrossMuncipalDuty() {
	return crossMuncipalDuty;
}
public void setCrossMuncipalDuty(String crossMuncipalDuty) {
	this.crossMuncipalDuty = crossMuncipalDuty;
}
public String getCrossJanpadDuty() {
	return crossJanpadDuty;
}
public void setCrossJanpadDuty(String crossJanpadDuty) {
	this.crossJanpadDuty = crossJanpadDuty;
}
public String getCrossUpkarDuty() {
	return crossUpkarDuty;
}
public void setCrossUpkarDuty(String crossUpkarDuty) {
	this.crossUpkarDuty = crossUpkarDuty;
}
public String getCrossTotalDuty() {
	return crossTotalDuty;
}
public void setCrossTotalDuty(String crossTotalDuty) {
	this.crossTotalDuty = crossTotalDuty;
}
public String getCrossRegFee() {
	return crossRegFee;
}
public void setCrossRegFee(String crossRegFee) {
	this.crossRegFee = crossRegFee;
}
public String getCrossStampDuty() {
	return crossStampDuty;
}
public void setCrossStampDuty(String crossStampDuty) {
	this.crossStampDuty = crossStampDuty;
}
public String getCrossRemark() {
	return crossRemark;
}
public void setCrossRemark(String crossRemark) {
	this.crossRemark = crossRemark;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getNewRemark() {
	return newRemark;
}
public void setNewRemark(String newRemark) {
	this.newRemark = newRemark;
}
private String reConstructedArea;
   private String reTypeOfConstruction;
   private String rePropertyUse;
   private String oldArea;
   private String oldConstructedArea;
   private String oldTypeOfConstruction;
   private String oldPropertyUse;
   private String spotId;
   private String druserId;
   private String druserName;
   private String regId;
   private String regIdcompletion;
   private String regDistrictId;
   private String regZoneId;
   private String srName;
   private String registrationOfficeId;
   private String villageId;
   private String floorId;
   private String areaType;
   String count;
   String selectedId;
   String selectedName;
   String zoneId;
   String zoneName;
   String districtId;
   String districtName;
   String tehsilId;
   String tehsilName;
   String marketValue;
   String oldMarketValue;
   String newMarketValue;
   String regFee;
   String stampDuty;
   public String getOldMarketValue() {
	return oldMarketValue;
}
public void setOldMarketValue(String oldMarketValue) {
	this.oldMarketValue = oldMarketValue;
}
public String getNewMarketValue() {
	return newMarketValue;
}
public void setNewMarketValue(String newMarketValue) {
	this.newMarketValue = newMarketValue;
}
String minRange;
   public String getMohallaId() {
	return mohallaId;
}
public void setMohallaId(String mohallaId) {
	this.mohallaId = mohallaId;
}
public String getMohallaName() {
	return mohallaName;
}
public void setMohallaName(String mohallaName) {
	this.mohallaName = mohallaName;
}
String maxRange;
   private String district = "Select";
   private String applPartyType;
   private String orderDate;
   private ArrayList districtList;           
   private ArrayList countryList;			
   private ArrayList stateList;
   private ArrayList sroList;
   private ArrayList propList;
   private String propId;
   private String propStatus;
   private String drName;
   private String totalDocument;
   private String noOfInspected;
   private String noOfPending;
   private String dateinsp;
   private String datetodo;
   private String office_name;
   private String office_id;
   private ArrayList officeList;
   private ArrayList srRoleList;
   private String srRoleName;
   private String srRoleId;
   public String getSrRoleName() {
	return srRoleName;
}
public void setSrRoleName(String srRoleName) {
	this.srRoleName = srRoleName;
}
public String getSrRoleId() {
	return srRoleId;
}
public void setSrRoleId(String srRoleId) {
	this.srRoleId = srRoleId;
}
public ArrayList getSrRoleList() {
	return srRoleList;
}
public void setSrRoleList(ArrayList srRoleList) {
	this.srRoleList = srRoleList;
}
public ArrayList getOfficeList() {
	return officeList;
}
public void setOfficeList(ArrayList officeList) {
	this.officeList = officeList;
}
public String getOffice_name() {
	return office_name;
}
public void setOffice_name(String office_name) {
	this.office_name = office_name;
}
public String getOffice_id() {
	return office_id;
}
public void setOffice_id(String office_id) {
	this.office_id = office_id;
}
public String getTotalDocument() {
	return totalDocument;
}
public void setTotalDocument(String totalDocument) {
	this.totalDocument = totalDocument;
}
public String getNoOfInspected() {
	return noOfInspected;
}
public void setNoOfInspected(String noOfInspected) {
	this.noOfInspected = noOfInspected;
}
public String getNoOfPending() {
	return noOfPending;
}
public void setNoOfPending(String noOfPending) {
	this.noOfPending = noOfPending;
}
private String assignPersonName;
private String reassignPersonName;
   public String getDigName() {
	return digName;
}
public void setDigName(String digName) {
	this.digName = digName;
}
private String assginPersonUserId;
   public String getAssignPersonName() {
	return assignPersonName;
}
public void setAssignPersonName(String assignPersonName) {
	this.assignPersonName = assignPersonName;
}
public String getAssginPersonUserId() {
	return assginPersonUserId;
}
public void setAssginPersonUserId(String assginPersonUserId) {
	this.assginPersonUserId = assginPersonUserId;
}
private String drId;
  private String  areaTypeName;
  private String propertyId;
  private String propertyname;
  private String serialNo;
  private String checkError;
  private String wardPatwariId;
  private String vardPatwariName;
  private String srId;
  private String mohallaId;
  private String mohallaName;
  private String oldguidelineValue;
  private String newguidelineValue;
  private String sortCheck="Y";  
  
  //Added by Akansha - STQC
  private String changeInVal;
  private String uploadPath;
  private String photoFilename;
  
   public String getChangeInVal() {
	return changeInVal;
}
public void setChangeInVal(String changeInVal) {
	this.changeInVal = changeInVal;
}
public String getUploadPath() {
	return uploadPath;
}
public void setUploadPath(String uploadPath) {
	this.uploadPath = uploadPath;
}
public String getOldguidelineValue() {
	return oldguidelineValue;
}
public void setOldguidelineValue(String oldguidelineValue) {
	this.oldguidelineValue = oldguidelineValue;
}
public String getNewguidelineValue() {
	return newguidelineValue;
}
public void setNewguidelineValue(String newguidelineValue) {
	this.newguidelineValue = newguidelineValue;
}
public String getWardPatwariId() {
	return wardPatwariId;
}
public void setWardPatwariId(String wardPatwariId) {
	this.wardPatwariId = wardPatwariId;
}
public String getVardPatwariName() {
	return vardPatwariName;
}
public void setVardPatwariName(String vardPatwariName) {
	this.vardPatwariName = vardPatwariName;
}
public String getAreaTypeName() {
	return areaTypeName;
}

private ArrayList<SpotInspDTO> floorList = new ArrayList<SpotInspDTO>();
public ArrayList<SpotInspDTO> getFloorList() {
	return floorList;
}
public void setFloorList(ArrayList<SpotInspDTO> floorList) {
	this.floorList = floorList;
}
public void setAreaTypeName(String areaTypeName) {
	this.areaTypeName = areaTypeName;
}
public String getAreaTypeId() {
	return areaTypeId;
}
public void setAreaTypeId(String areaTypeId) {
	this.areaTypeId = areaTypeId;
}
private String areaTypeId;
   
   
public String getAreaType() {
	return areaType;
}
public void setAreaType(String areaType) {
	this.areaType = areaType;
}
public String getMinRange() {
	return minRange;
}
public void setMinRange(String minRange) {
	this.minRange = minRange;
}
public String getMaxRange() {
	return maxRange;
}
public void setMaxRange(String maxRange) {
	this.maxRange = maxRange;
}
public String getDrId() {
	return drId;
}
public void setDrId(String drId) {
	this.drId = drId;
}
public String getDrName() {
	return drName;
}
public void setDrName(String drName) {
	this.drName = drName;
}
public String getMarketValue() {
	return marketValue;
}
public void setMarketValue(String marketValue) {
	this.marketValue = marketValue;
}
public String getRegFee() {
	return regFee;
}
public void setRegFee(String regFee) {
	this.regFee = regFee;
}
public String getStampDuty() {
	return stampDuty;
}
public void setStampDuty(String stampDuty) {
	this.stampDuty = stampDuty;
}
public String getTehsilId() {
	return tehsilId;
}
public void setTehsilId(String tehsilId) {
	this.tehsilId = tehsilId;
}
public String getTehsilName() {
	return tehsilName;
}
public void setTehsilName(String tehsilName) {
	this.tehsilName = tehsilName;
}
public String getFoundStatus() {
	return foundStatus;
}
public void setFoundStatus(String foundStatus) {
	this.foundStatus = foundStatus;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getReArea() {
	return reArea;
}
public void setReArea(String reArea) {
	this.reArea = reArea;
}
public String getReConstructedArea() {
	return reConstructedArea;
}
public void setReConstructedArea(String reConstructedArea) {
	this.reConstructedArea = reConstructedArea;
}
public String getReTypeOfConstruction() {
	return reTypeOfConstruction;
}
public void setReTypeOfConstruction(String reTypeOfConstruction) {
	this.reTypeOfConstruction = reTypeOfConstruction;
}
public String getRePropertyUse() {
	return rePropertyUse;
}
public void setRePropertyUse(String rePropertyUse) {
	this.rePropertyUse = rePropertyUse;
}
public String getFloorId() {
	return floorId;
}
public void setFloorId(String floorId) {
	this.floorId = floorId;
}
public String getNewArea() {
	return newArea;
}
public void setNewArea(String newArea) {
	this.newArea = newArea;
}
public String getNewConstructedArea() {
	return newConstructedArea;
}
public void setNewConstructedArea(String newConstructedArea) {
	this.newConstructedArea = newConstructedArea;
}
public String getNewTypeOfConstruction() {
	return newTypeOfConstruction;
}
public void setNewTypeOfConstruction(String newTypeOfConstruction) {
	this.newTypeOfConstruction = newTypeOfConstruction;
}
public String getNewPropertyUse() {
	return newPropertyUse;
}
public void setNewPropertyUse(String newPropertyUse) {
	this.newPropertyUse = newPropertyUse;
}
public String getOldArea() {
	return oldArea;
}
public void setOldArea(String oldArea) {
	this.oldArea = oldArea;
}
public String getOldConstructedArea() {
	return oldConstructedArea;
}
public void setOldConstructedArea(String oldConstructedArea) {
	this.oldConstructedArea = oldConstructedArea;
}
public String getOldTypeOfConstruction() {
	return oldTypeOfConstruction;
}
public void setOldTypeOfConstruction(String oldTypeOfConstruction) {
	this.oldTypeOfConstruction = oldTypeOfConstruction;
}
public String getOldPropertyUse() {
	return oldPropertyUse;
}
public void setOldPropertyUse(String oldPropertyUse) {
	this.oldPropertyUse = oldPropertyUse;
}
public String getSpotId() {
	return spotId;
}
public void setSpotId(String spotId) {
	this.spotId = spotId;
}
public String getDruserId() {
	return druserId;
}
public void setDruserId(String druserId) {
	this.druserId = druserId;
}
public String getDruserName() {
	return druserName;
}
public void setDruserName(String druserName) {
	this.druserName = druserName;
}
public String getRegId() {
	return regId;
}
public void setRegId(String regId) {
	this.regId = regId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}
public String getZoneId() {
	return zoneId;
}
public void setZoneId(String zoneId) {
	this.zoneId = zoneId;
}
public String getZoneName() {
	return zoneName;
}
public void setZoneName(String zoneName) {
	this.zoneName = zoneName;
}
public String getDistrictId() {
	return districtId;
}
public void setDistrictId(String districtId) {
	this.districtId = districtId;
}
public String getDistrictName() {
	return districtName;
}
public void setDistrictName(String districtName) {
	this.districtName = districtName;
}
public String getSelectedId() {
	return selectedId;
}
public void setSelectedId(String selectedId) {
	this.selectedId = selectedId;
}
public String getSelectedName() {
	return selectedName;
}
public void setSelectedName(String selectedName) {
	this.selectedName = selectedName;
}
public String getPropStatus() {
	return propStatus;
}
public void setPropStatus(String propStatus) {
	this.propStatus = propStatus;
}
/**
 * @return the propId
 */
public String getPropId() {
    return propId;
}
/**
 * @param propId the propId to set
 */
public void setPropId(String propId) {
    this.propId = propId;
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
 * @return the blnPerVal
 */
public boolean isBlnPerVal() {
	return blnPerVal;
}
/**
 * @param blnPerVal the blnPerVal to set
 */
public void setBlnPerVal(boolean blnPerVal) {
	this.blnPerVal = blnPerVal;
}
/**
 * @return the deedType
 */
public String getDeedType() {
	return deedType;
}
/**
 * @param deedType the deedType to set
 */
public void setDeedType(String deedType) {
	this.deedType = deedType;
}
/**
 * @return the deedTypeList
 */
public ArrayList getDeedTypeList() {
	return deedTypeList;
}
/**
 * @param deedTypeList the deedTypeList to set
 */
public void setDeedTypeList(ArrayList deedTypeList) {
	this.deedTypeList = deedTypeList;
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the value
 */
public String getValue() {
	return value;
}
/**
 * @param value the value to set
 */
public void setValue(String value) {
	this.value = value;
}
/**
 * @return the country
 */
public String getCountry() {
	return country;
}
/**
 * @param country the country to set
 */
public void setCountry(String country) {
	this.country = country;
}
/**
 * @return the district
 */
public String getDistrict() {
	return district;
}
/**
 * @param district the district to set
 */
public void setDistrict(String district) {
	this.district = district;
}
/**
 * @return the applPartyType
 */
public String getApplPartyType() {
	return applPartyType;
}
/**
 * @param applPartyType the applPartyType to set
 */
public void setApplPartyType(String applPartyType) {
	this.applPartyType = applPartyType;
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
 * @return the propList
 */
public ArrayList getPropList() {
	return propList;
}
/**
 * @param propList the propList to set
 */
public void setPropList(ArrayList propList) {
	this.propList = propList;
}
public void setRegIdcompletion(String regIdcompletion) {
	this.regIdcompletion = regIdcompletion;
}
public String getRegIdcompletion() {
	return regIdcompletion;
}
public void setRegDistrictId(String regDistrictId) {
	this.regDistrictId = regDistrictId;
}
public String getRegDistrictId() {
	return regDistrictId;
}
public void setRegZoneId(String regZoneId) {
	this.regZoneId = regZoneId;
}
public String getRegZoneId() {
	return regZoneId;
}
public void setSrName(String srName) {
	this.srName = srName;
}
public String getSrName() {
	return srName;
}
public void setRegistrationOfficeId(String registrationOfficeId) {
	this.registrationOfficeId = registrationOfficeId;
}
public String getRegistrationOfficeId() {
	return registrationOfficeId;
}
public void setVillageId(String villageId) {
	this.villageId = villageId;
}
public String getVillageId() {
	return villageId;
}
public void setPropertyId(String propertyId) {
	this.propertyId = propertyId;
}
public String getPropertyId() {
	return propertyId;
}
public void setPropertyname(String propertyname) {
	this.propertyname = propertyname;
}
public String getPropertyname() {
	return propertyname;
}
public void setSerialNo(String serialNo) {
	this.serialNo = serialNo;
}
public String getSerialNo() {
	return serialNo;
}
public void setCheckError(String checkError) {
	this.checkError = checkError;
}
public String getCheckError() {
	return checkError;
}
public void setSrId(String srId) {
	this.srId = srId;
}
public String getSrId() {
	return srId;
}
public void setNoOfDocuments(String noOfDocuments) {
	this.noOfDocuments = noOfDocuments;
}
public String getNoOfDocuments() {
	return noOfDocuments;
}
public void setNewStampDuty(String newStampDuty) {
	this.newStampDuty = newStampDuty;
}
public String getNewStampDuty() {
	return newStampDuty;
}
public void setNewRegfee(String newRegfee) {
	this.newRegfee = newRegfee;
}
public String getNewRegfee() {
	return newRegfee;
}
public void setDatetodo(String datetodo) {
	this.datetodo = datetodo;
}
public String getDatetodo() {
	return datetodo;
}
public void setDateinsp(String dateinsp) {
	this.dateinsp = dateinsp;
}
public String getDateinsp() {
	return dateinsp;
}
public void setComplaintId(String complaintId) {
	this.complaintId = complaintId;
}
public String getComplaintId() {
	return complaintId;
}
public void setReassignPersonName(String reassignPersonName) {
	this.reassignPersonName = reassignPersonName;
}
public String getReassignPersonName() {
	return reassignPersonName;
}
public void setOrderDate(String orderDate) {
	this.orderDate = orderDate;
}
public String getOrderDate() {
	return orderDate;
}
public void setSortCheck(String sortCheck) {
	this.sortCheck = sortCheck;
}
public String getSortCheck() {
	return sortCheck;
}
public void setSubAreaId(String subAreaId) {
	this.subAreaId = subAreaId;
}
public String getSubAreaId() {
	return subAreaId;
}
public void setSubAreaName(String subAreaName) {
	this.subAreaName = subAreaName;
}
public String getSubAreaName() {
	return subAreaName;
}
public void setWardIds(String wardIds) {
	this.wardIds = wardIds;
}
public String getWardIds() {
	return wardIds;
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
public void setColonyId(String colonyId) {
	this.colonyId = colonyId;
}
public String getColonyId() {
	return colonyId;
}
public void setColonyName(String colonyName) {
	this.colonyName = colonyName;
}
public String getColonyName() {
	return colonyName;
}
public void setReInspectionComments(String reInspectionComments) {
	this.reInspectionComments = reInspectionComments;
}
public String getReInspectionComments() {
	return reInspectionComments;
}
public void setTotalDuty(String totalDuty) {
	this.totalDuty = totalDuty;
}
public String getTotalDuty() {
	return totalDuty;
}
public void setPhotoFilename(String photoFilename) {
	this.photoFilename = photoFilename;
}
public String getPhotoFilename() {
	return photoFilename;
}

//added by saurav

public String getDocCompletionSR() {
	return docCompletionSR;
}
public void setDocCompletionSR(String docCompletionSR) {
	this.docCompletionSR = docCompletionSR;
}
   
   
}