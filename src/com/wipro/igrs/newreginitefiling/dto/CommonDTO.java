package com.wipro.igrs.newreginitefiling.dto;

import java.io.Serializable;
import java.util.ArrayList;


public class CommonDTO implements Serializable{
	
	private ArrayList userPropWiseEnterableList= new ArrayList();
	
	
	
	private String indtehsil;
	private String name;
	private String id;
	
	private String tehsildropname;
	private String tehsildropid;
	private String tehsildropname1;
	private String tehsildropid1;
	//private String tehsilName;
	//private String tehsilId;
	private String middleName;
	private String lastName;
	private String checked="";
	//follwoing for confirmation screen
	private String role="";
	private String roleId="";
	// to display hindi english both name for POA. -- RAhul
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
	private Float areaEfile;
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
	public void setIndtehsil(String indtehsil) {
		this.indtehsil = indtehsil;
	}
	public String getIndtehsil() {
		return indtehsil;
	}
	public void setAreaEfile(Float areaEfile) {
		this.areaEfile = areaEfile;
	}
	public Float getAreaEfile() {
		return areaEfile;
	}
	public void setTehsildropname(String tehsildropname) {
		this.tehsildropname = tehsildropname;
	}
	public String getTehsildropname() {
		return tehsildropname;
	}
	public void setTehsildropid(String tehsildropid) {
		this.tehsildropid = tehsildropid;
	}
	public String getTehsildropid() {
		return tehsildropid;
	}
	public void setTehsildropname1(String tehsildropname1) {
		this.tehsildropname1 = tehsildropname1;
	}
	public String getTehsildropname1() {
		return tehsildropname1;
	}
	public void setTehsildropid1(String tehsildropid1) {
		this.tehsildropid1 = tehsildropid1;
	}
	public String getTehsildropid1() {
		return tehsildropid1;
	}
	
	
	
}