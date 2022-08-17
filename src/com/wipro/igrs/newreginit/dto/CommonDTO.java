package com.wipro.igrs.newreginit.dto;

import java.io.Serializable;
import java.util.ArrayList;


public class CommonDTO implements Serializable{
	
	private ArrayList userPropWiseEnterableList= new ArrayList();
	
	private String name;
	private String id;
	private String middleName;
	private String lastName;
	private String checked="";
	//follwoing for confirmation screen
	private String role="";
	private String roleId="";
	// to display hindi english both name for POA. -- Akansha
	private String roleFullName ;
	
	
	//following for khasra details
	private String khasraNum="";
	private String khasraArea="";
	private String lagaan="";
	private String rinPustika="";
	
	private String north="";
	private String south="";
	private String east="";
	private String west="";
	
	//clr
	private String mutationShare;
	private String partyShare;
	
	private String NOHIYAT;
	private String HALKANUMBER;
	private String BASRANUMBER;
	private String KHASRAID_DEPT_CLR;
	private String LAND_USETYPE;
	private String LAND_USETYPE_DETL;
	private String KHASRA_UNQ_NO;
	public String getCROP_TYPE() {
		return CROP_TYPE;
	}
	public void setCROP_TYPE(String crop_type) {
		CROP_TYPE = crop_type;
	}
	public String getCROP_DESCRIIPTION() {
		return CROP_DESCRIIPTION;
	}
	public void setCROP_DESCRIIPTION(String crop_descriiption) {
		CROP_DESCRIIPTION = crop_descriiption;
	}
	public String getPLOT_TYPE() {
		return PLOT_TYPE;
	}
	public void setPLOT_TYPE(String plot_type) {
		PLOT_TYPE = plot_type;
	}
	public String getPLOT_DESCRIPTION() {
		return PLOT_DESCRIPTION;
	}
	public void setPLOT_DESCRIPTION(String plot_description) {
		PLOT_DESCRIPTION = plot_description;
	}
	public String getLAND_TYPE() {
		return LAND_TYPE;
	}
	public void setLAND_TYPE(String land_type) {
		LAND_TYPE = land_type;
	}
	private String ALPIN_NO;
	private String CROP_TYPE;
	private String CROP_DESCRIIPTION;
	private String PLOT_TYPE;
	private String PLOT_DESCRIPTION;
	private String LAND_TYPE;
	private String LANDOWNERID_DEPT_CLR;
	public String getNOHIYAT() {
		return NOHIYAT;
	}
	public void setNOHIYAT(String nohiyat) {
		NOHIYAT = nohiyat;
	}
	public String getHALKANUMBER() {
		return HALKANUMBER;
	}
	public void setHALKANUMBER(String halkanumber) {
		HALKANUMBER = halkanumber;
	}
	public String getBASRANUMBER() {
		return BASRANUMBER;
	}
	public void setBASRANUMBER(String basranumber) {
		BASRANUMBER = basranumber;
	}
	public String getKHASRAID_DEPT_CLR() {
		return KHASRAID_DEPT_CLR;
	}
	public void setKHASRAID_DEPT_CLR(String khasraid_dept_clr) {
		KHASRAID_DEPT_CLR = khasraid_dept_clr;
	}
	public String getLAND_USETYPE() {
		return LAND_USETYPE;
	}
	public void setLAND_USETYPE(String land_usetype) {
		LAND_USETYPE = land_usetype;
	}
	public String getLAND_USETYPE_DETL() {
		return LAND_USETYPE_DETL;
	}
	public void setLAND_USETYPE_DETL(String land_usetype_detl) {
		LAND_USETYPE_DETL = land_usetype_detl;
	}
	public String getKHASRA_UNQ_NO() {
		return KHASRA_UNQ_NO;
	}
	public void setKHASRA_UNQ_NO(String khasra_unq_no) {
		KHASRA_UNQ_NO = khasra_unq_no;
	}
	public String getALPIN_NO() {
		return ALPIN_NO;
	}
	public void setALPIN_NO(String alpin_no) {
		ALPIN_NO = alpin_no;
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
	private String uniqueIdUpload;
	public String getUniqueIdUpload() {
		return uniqueIdUpload;
	}
	public void setUniqueIdUpload(String uniqueIdUpload) {
		this.uniqueIdUpload = uniqueIdUpload;
	}
	private String documentName;
	private String documentSize;
	private byte[] docContents;
	private String documentPath;
	
	
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentSize() {
		return documentSize;
	}
	public void setDocumentSize(String documentSize) {
		this.documentSize = documentSize;
	}
	public byte[] getDocContents() {
		return docContents;
	}
	public void setDocContents(byte[] docContents) {
		this.docContents = docContents;
	}
	//FOLLOWING FOR EXCHANGE DEED
	private String valuationId;
	private String propertyId="";
	private String propertyName="";
	private String marketValue;
	private String partyType="";
	private String areaTypeId="";
	private String govBodyId="";
	private String propTypeId="";
	private String l1TypeId="";
	private String l2TypeId="";
	private String areaUnitId="";
	private String area="";
	private String distId="";
	private String tehsilId="";
	private String tehsilName="";
	private String wardId="";
	private String mohalaId="";
	
	private String regTxnId="";
	private String userId="";
	private String partyTxnId="";
	
	private String sysMv="";              //for system generated market value
	
	private String particularDesc="";
	
	private String shareOfProp;
	private String isApplicantFlag;
	private String partyCheck;
	private String type;
	private String value;
	
	private String areaId="";
	private String areaName="";
	
	private String stampDuty;
	private String municipalDuty;
	private String blockDuty;
	private String upkarDuty;
	private String total;
	private String regFee;
	private String paybleDuty;
	private String paybleFee;
	
	private String exempTotal;
	private String exempRegFee;
	
	private String calculatedShare;
	
	
	public String getExempTotal() {
		return exempTotal;
	}
	public void setExempTotal(String exempTotal) {
		this.exempTotal = exempTotal;
	}
	public String getExempRegFee() {
		return exempRegFee;
	}
	public void setExempRegFee(String exempRegFee) {
		this.exempRegFee = exempRegFee;
	}
	public String getStampDuty() {
		return stampDuty;
	}
	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}
	public String getMunicipalDuty() {
		return municipalDuty;
	}
	public void setMunicipalDuty(String municipalDuty) {
		this.municipalDuty = municipalDuty;
	}
	public String getBlockDuty() {
		return blockDuty;
	}
	public void setBlockDuty(String blockDuty) {
		this.blockDuty = blockDuty;
	}
	public String getUpkarDuty() {
		return upkarDuty;
	}
	public void setUpkarDuty(String upkarDuty) {
		this.upkarDuty = upkarDuty;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getRegFee() {
		return regFee;
	}
	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}
	public String getPaybleDuty() {
		return paybleDuty;
	}
	public void setPaybleDuty(String paybleDuty) {
		this.paybleDuty = paybleDuty;
	}
	public String getPaybleFee() {
		return paybleFee;
	}
	public void setPaybleFee(String paybleFee) {
		this.paybleFee = paybleFee;
	}
	public ArrayList getUserPropWiseEnterableList() {
		return userPropWiseEnterableList;
	}
	public void setUserPropWiseEnterableList(ArrayList userPropWiseEnterableList) {
		this.userPropWiseEnterableList = userPropWiseEnterableList;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPartyCheck() {
		return partyCheck;
	}
	public void setPartyCheck(String partyCheck) {
		this.partyCheck = partyCheck;
	}
	public String getIsApplicantFlag() {
		return isApplicantFlag;
	}
	public void setIsApplicantFlag(String isApplicantFlag) {
		this.isApplicantFlag = isApplicantFlag;
	}
	public String getShareOfProp() {
		return shareOfProp;
	}
	public void setShareOfProp(String shareOfProp) {
		this.shareOfProp = shareOfProp;
	}
	public String getParticularDesc() {
		return particularDesc;
	}
	public void setParticularDesc(String particularDesc) {
		this.particularDesc = particularDesc;
	}
	public String getSysMv() {
		return sysMv;
	}
	public void setSysMv(String sysMv) {
		this.sysMv = sysMv;
	}
	public String getAreaTypeId() {
		return areaTypeId;
	}
	public void setAreaTypeId(String areaTypeId) {
		this.areaTypeId = areaTypeId;
	}
	public String getGovBodyId() {
		return govBodyId;
	}
	public void setGovBodyId(String govBodyId) {
		this.govBodyId = govBodyId;
	}
	public String getPropTypeId() {
		return propTypeId;
	}
	public void setPropTypeId(String propTypeId) {
		this.propTypeId = propTypeId;
	}
	public String getL1TypeId() {
		return l1TypeId;
	}
	public void setL1TypeId(String typeId) {
		l1TypeId = typeId;
	}
	public String getL2TypeId() {
		return l2TypeId;
	}
	public void setL2TypeId(String typeId) {
		l2TypeId = typeId;
	}
	public String getAreaUnitId() {
		return areaUnitId;
	}
	public void setAreaUnitId(String areaUnitId) {
		this.areaUnitId = areaUnitId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDistId() {
		return distId;
	}
	public void setDistId(String distId) {
		this.distId = distId;
	}
	public String getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getWardId() {
		return wardId;
	}
	public void setWardId(String wardId) {
		this.wardId = wardId;
	}
	public String getMohalaId() {
		return mohalaId;
	}
	public void setMohalaId(String mohalaId) {
		this.mohalaId = mohalaId;
	}
	public String getRegTxnId() {
		return regTxnId;
	}
	public void setRegTxnId(String regTxnId) {
		this.regTxnId = regTxnId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPartyTxnId() {
		return partyTxnId;
	}
	public void setPartyTxnId(String partyTxnId) {
		this.partyTxnId = partyTxnId;
	}
	public String getValuationId() {
		return valuationId;
	}
	public void setValuationId(String valuationId) {
		this.valuationId = valuationId;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	public String getKhasraNum() {
		return khasraNum;
	}
	public void setKhasraNum(String khasraNum) {
		this.khasraNum = khasraNum;
	}
	public String getKhasraArea() {
		return khasraArea;
	}
	public void setKhasraArea(String khasraArea) {
		this.khasraArea = khasraArea;
	}
	public String getLagaan() {
		return lagaan;
	}
	public void setLagaan(String lagaan) {
		this.lagaan = lagaan;
	}
	public String getRinPustika() {
		return rinPustika;
	}
	public void setRinPustika(String rinPustika) {
		this.rinPustika = rinPustika;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	public String getDocumentPath() {
		return documentPath;
	}
	public void setRoleFullName(String roleFullName) {
		this.roleFullName = roleFullName;
	}
	public String getRoleFullName() {
		return roleFullName;
	}
	public void setMutationShare(String mutationShare) {
		this.mutationShare = mutationShare;
	}
	public String getMutationShare() {
		return mutationShare;
	}
	public void setPartyShare(String partyShare) {
		this.partyShare = partyShare;
	}
	public String getPartyShare() {
		return partyShare;
	}
	public void setCalculatedShare(String calculatedShare) {
		this.calculatedShare = calculatedShare;
	}
	public String getCalculatedShare() {
		return calculatedShare;
	}
// Added by Rakesh for CLR field display
	
	 
	
	private String clrFlag;
	private String riCircleClr;
	private String khasraNoClr;
	private String khasraAreaClr;
	private String northKhasraNoClr;
	private String southKhasraNoClr;
	private String eastKhasraNoClr;
	private String westKhasraNoClr;
	private String mapPathClr;
	private String khasraPathClr;
	private String khasraAddedDate;//added by akansha on 8th june 20
	
	public String getKhasraAddedDate() {
		return khasraAddedDate;
	}
	public void setKhasraAddedDate(String khasraAddedDate) {
		this.khasraAddedDate = khasraAddedDate;
	}
	private String casteOfParty;
	public String getCasteOfParty() {
		return casteOfParty;
	}
	public void setCasteOfParty(String casteOfParty) {
		this.casteOfParty = casteOfParty;
	}
	public String getShareOfParty() {
		return shareOfParty;
	}
	public void setShareOfParty(String shareOfParty) {
		this.shareOfParty = shareOfParty;
	}
	public String getPartyDetails() {
		return partyDetails;
	}
	public void setPartyDetails(String partyDetails) {
		this.partyDetails = partyDetails;
	}
	public ArrayList<CommonDTO> getClrOwnerArray() {
		return clrOwnerArray;
	}
	public void setClrOwnerArray(ArrayList<CommonDTO> clrOwnerArray) {
		this.clrOwnerArray = clrOwnerArray;
	}
	private String shareOfParty;
	private String partyDetails;
	private String fatherName;
	private String ownerAddress;
	private String ownershipType;
	
	private ArrayList<CommonDTO> clrOwnerArray=new ArrayList<CommonDTO>();

	public String getRiCircleClr() {
		return riCircleClr;
	}
	public void setRiCircleClr(String riCircleClr) {
		this.riCircleClr = riCircleClr;
	}
	public String getKhasraNoClr() {
		return khasraNoClr;
	}
	public void setKhasraNoClr(String khasraNoClr) {
		this.khasraNoClr = khasraNoClr;
	}
	public String getKhasraAreaClr() {
		return khasraAreaClr;
	}
	public void setKhasraAreaClr(String khasraAreaClr) {
		this.khasraAreaClr = khasraAreaClr;
	}
	public String getNorthKhasraNoClr() {
		return northKhasraNoClr;
	}
	public void setNorthKhasraNoClr(String northKhasraNoClr) {
		this.northKhasraNoClr = northKhasraNoClr;
	}
	public String getSouthKhasraNoClr() {
		return southKhasraNoClr;
	}
	public void setSouthKhasraNoClr(String southKhasraNoClr) {
		this.southKhasraNoClr = southKhasraNoClr;
	}
	public String getEastKhasraNoClr() {
		return eastKhasraNoClr;
	}
	public void setEastKhasraNoClr(String eastKhasraNoClr) {
		this.eastKhasraNoClr = eastKhasraNoClr;
	}
	public String getWestKhasraNoClr() {
		return westKhasraNoClr;
	}
	public void setWestKhasraNoClr(String westKhasraNoClr) {
		this.westKhasraNoClr = westKhasraNoClr;
	}
	public String getMapPathClr() {
		return mapPathClr;
	}
	public void setMapPathClr(String mapPathClr) {
		this.mapPathClr = mapPathClr;
	}
	public String getKhasraPathClr() {
		return khasraPathClr;
	}
	public void setKhasraPathClr(String khasraPathClr) {
		this.khasraPathClr = khasraPathClr;
	}
	public void setClrFlag(String clrFlag) {
		this.clrFlag = clrFlag;
	}
	public String getClrFlag() {
		return clrFlag;
	}
	
	
	
	private String selectedPropIdMap;
	public String getSelectedPropIdMap() {
		return selectedPropIdMap;
	}
	public void setSelectedPropIdMap(String selectedPropIdMap) {
		this.selectedPropIdMap = selectedPropIdMap;
	}
	private String selectedValIdMap;
	public String getSelectedValIdMap() {
		return selectedValIdMap;
	}
	public void setSelectedValIdMap(String selectedValIdMap) {
		this.selectedValIdMap = selectedValIdMap;
	}
	
	public void setKhasraNo(String khasraNo) {
		this.khasraNo = khasraNo;
	}
	public String getKhasraNo() {
		return khasraNo;
	}
	public void setOwnershipType(String ownershipType) {
		this.ownershipType = ownershipType;
	}
	public String getOwnershipType() {
		return ownershipType;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setLANDOWNERID_DEPT_CLR(String lANDOWNERID_DEPT_CLR) {
		LANDOWNERID_DEPT_CLR = lANDOWNERID_DEPT_CLR;
	}
	public String getLANDOWNERID_DEPT_CLR() {
		return LANDOWNERID_DEPT_CLR;
	}
	private String khasraNo;
	
	
}