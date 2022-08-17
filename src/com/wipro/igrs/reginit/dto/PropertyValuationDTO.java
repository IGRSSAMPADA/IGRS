package com.wipro.igrs.reginit.dto;

import java.io.Serializable;

/* Copyright (c) 2006-2008 WIPRO. All Rights Reserved.
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
 */


/**
 * @author Madan Mohan
 */
public class PropertyValuationDTO implements Serializable{
	
	/**
	 * @author Madan Mohan
	 */
	private String firstName;
	/**
	 * @author Madan Mohan
	 */
	private String middleName;
	/**
	 * @author Madan Mohan
	 */
	private String lastName;
	/**
	 * @author Madan Mohan
	 */
	private String sex;
	private String chkSex;
	/**
	 * @author Madan Mohan
	 */
	private String age;
	/**
	 * @author Madan Mohan
	 */
	private String district;
	/**
	 * @author Madan Mohan
	 */
	private String districtID;
	/**
	 * @author Madan Mohan
	 */
	private String hdnDistrict;
	/**
	 * @author Madan Mohan
	 */
	private String tehsil;
	/**
	 * @author Madan Mohan
	 */
	private String tehsilID;
	/**
	 * @author Madan Mohan
	 */
	private String hdnTehsil;
	/**
	 * @author Madan Mohan
	 */
	private String propertyType;
	/**
	 * @author Madan Mohan
	 */
	private String propertyTypeID;
	/**
	 * @author Madan Mohan
	 */
	private String hdnPropertyType;
	/**
	 * @author Madan Mohan
	 */
	private String areaType;
	/**
	 * @author Madan Mohan
	 */
	private String hdnAreaType;
	/**
	 * @author Madan Mohan
	 */
	private String municipalBody;
	/**
	 * @author Madan Mohan
	 */
	private String municipalBodyID;
	/**
	 * @author Madan Mohan
	 */
	private String hdnMunicipalBody;
	/**
	 * @author Madan Mohan
	 */
	private String wardId;
	/**
	 * @author Madan Mohan
	 */
	private String ward;
	/**
	 * @author Madan Mohan
	 */
	private String hdnWard;
	/**
	 * @author Madan Mohan
	 */
	private String halkaNumber;
	/**
	 * @author Madan Mohan
	 */
	private String mahalla;
	/**
	 * @author Madan Mohan
	 */
	private String mahallaId;
	/**
	 * @author Madan Mohan
	 */
	private String hdnMahalla;
	/**
	 * @author Madan Mohan
	 */
	private String gramId;
	/**
	 * @author Madan Mohan
	 */
	private String patwariStatus ;
	/**
	 * @author Madan Mohan
	 */
	private String areaId;
	/**
	 * @author Madan Mohan
	 */
	private String patwariId ;
	/**
	 * @author Madan Mohan
	 */
	private String wardLabel = "Ward Number";
	/**
	 * @author Madan Mohan
	 */
	private String mahallaLabel = "Mohalla/Colony name/Society/Road";
	/**
	 * @author Madan Mohan
	 */
	private String usePlotId;
	/**
	 * @author Madan Mohan
	 */
	private String usePlot;
	/**
	 * @author Madan Mohan 
	 */
	private String hdnUsePlot;
	/**
	 * @author Madan Mohan
	 */
	private String hdnExtSubClause;
	/**
	 * @author Madan Mohan
	 */
	private String hdnSubClauseName;
	/**
	 * @author Madan Mohan
	 */
	private double totalSqMeter;
	/**
	 * @author Madan Mohan
	 */
	private String subClause;
	/**
	 * @author Madan Mohan
	 */
	private double considerAmt;
	/**
	 * @author Madan Mohan
	 */
	private String subClauseId;
	private String hdnSubClause;
	private double marketValue;
	private String mktValue;
	private String landMeterId;
	private String landMeterType;
	private String hdnLandMeter;
	private String landMeter;
	private String agriUnitFlag;
	private int    noOfTree;
	private String hdnNoOfTree;
	private String registrationFee;
	private String total ;
	
	
	private String lblUsage;
	private String typeID;
	private String typeName;
	private String hdnType;
	private double constructedArea;
	private String ceilingTypeId;
	private String ceilingType;
	private String hdnCeilingName;
	private String typeFloorID;
	private String typeFloorName;
	private String typeFloorDesc;
	private String hdnTypeFloor;
	private boolean transactionBuilding;
	private int addMoreCounter; 
	private String floorID;
	private String usageBuilding;
	private String deleteFloorID;
	private String hdnDeleteFloorID;
	private String municipalDutyName;
	private String municipalDuty;
	 
	public String getHdnDeleteFloorID() {
		return hdnDeleteFloorID;
	}
	public void setHdnDeleteFloorID(String hdnDeleteFloorID) {
		this.hdnDeleteFloorID = hdnDeleteFloorID;
	}
	public String getDeleteFloorID() {
		return deleteFloorID;
	}
	public void setDeleteFloorID(String deleteFloorID) {
		this.deleteFloorID = deleteFloorID;
	}
	public String getFloorID() {
		return floorID;
	}
	public void setFloorID(String floorID) {
		this.floorID = floorID;
	}
	public String getUsageBuilding() {
		return usageBuilding;
	}
	public void setUsageBuilding(String usageBuilding) {
		this.usageBuilding = usageBuilding;
	}
	public int getAddMoreCounter() {
		return addMoreCounter;
	}
	public void setAddMoreCounter(int addMoreCounter) {
		this.addMoreCounter = addMoreCounter;
	}
	public String getLblUsage() {
		return lblUsage;
	}
	public void setLblUsage(String lblUsage) {
		this.lblUsage = lblUsage;
	}
	public String getTypeID() {
		return typeID;
	}
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getHdnType() {
		return hdnType;
	}
	public void setHdnType(String hdnType) {
		this.hdnType = hdnType;
	}
	public double getConstructedArea() {
		return constructedArea;
	}
	public void setConstructedArea(double constructedArea) {
		this.constructedArea = constructedArea;
	}
	public String getCeilingTypeId() {
		return ceilingTypeId;
	}
	public void setCeilingTypeId(String ceilingTypeId) {
		this.ceilingTypeId = ceilingTypeId;
	}
	public String getCeilingType() {
		return ceilingType;
	}
	public void setCeilingType(String ceilingType) {
		this.ceilingType = ceilingType;
	}
	public String getHdnCeilingName() {
		return hdnCeilingName;
	}
	public void setHdnCeilingName(String hdnCeilingName) {
		this.hdnCeilingName = hdnCeilingName;
	}
	public String getTypeFloorID() {
		return typeFloorID;
	}
	public void setTypeFloorID(String typeFloorID) {
		this.typeFloorID = typeFloorID;
	}
	public String getTypeFloorName() {
		return typeFloorName;
	}
	public void setTypeFloorName(String typeFloorName) {
		this.typeFloorName = typeFloorName;
	}
	public String getTypeFloorDesc() {
		return typeFloorDesc;
	}
	public void setTypeFloorDesc(String typeFloorDesc) {
		this.typeFloorDesc = typeFloorDesc;
	}
	public String getHdnTypeFloor() {
		return hdnTypeFloor;
	}
	public void setHdnTypeFloor(String hdnTypeFloor) {
		this.hdnTypeFloor = hdnTypeFloor;
	}
	public boolean isTransactionBuilding() {
		return transactionBuilding;
	}
	public void setTransactionBuilding(boolean transactionBuilding) {
		this.transactionBuilding = transactionBuilding;
	}
	public String getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}
	public String getHdnNoOfTree() {
		return hdnNoOfTree;
	}
	public void setHdnNoOfTree(String hdnNoOfTree) {
		this.hdnNoOfTree = hdnNoOfTree;
	}
	public int getNoOfTree() {
		return noOfTree;
	}
	public void setNoOfTree(int noOfTree) {
		this.noOfTree = noOfTree;
	}
	public String getAgriUnitFlag() {
		return agriUnitFlag;
	}
	public void setAgriUnitFlag(String agriUnitFlag) {
		this.agriUnitFlag = agriUnitFlag;
	}
	public String getLandMeter() {
		return landMeter;
	}
	public void setLandMeter(String landMeter) {
		this.landMeter = landMeter;
	}
	/**
	 * @return String
	 */
	public String getHdnSubClause() {
		return hdnSubClause;
	}
	/**
	 * @param hdnSubClause
	 */
	public void setHdnSubClause(String hdnSubClause) {
		this.hdnSubClause = hdnSubClause;
	}
	/**
	 * @return String
	 */
	public String getSubClauseId() {
		return subClauseId;
	}
	/**
	 * @param subClauseId
	 */
	public void setSubClauseId(String subClauseId) {
		this.subClauseId = subClauseId;
	}
	/**
	 * @return String
	 */
	public String getUsePlotId() {
		return usePlotId;
	}
	/**
	 * @param usePlotId
	 */
	public void setUsePlotId(String usePlotId) {
		this.usePlotId = usePlotId;
	}
	/**
	 * @return String
	 */
	public String getUsePlot() {
		return usePlot;
	}
	/**
	 * @param usePlot
	 */
	public void setUsePlot(String usePlot) {
		this.usePlot = usePlot;
	}
	/**
	 * @return String
	 */
	public String getHdnUsePlot() {
		return hdnUsePlot;
	}
	/**
	 * @param hdnUsePlot
	 */
	public void setHdnUsePlot(String hdnUsePlot) {
		this.hdnUsePlot = hdnUsePlot;
	}
	/**
	 * @return double
	 */
	public double getConsiderAmt() {
		return considerAmt;
	}
	/**
	 * @param considerAmt
	 */
	public void setConsiderAmt(double considerAmt) {
		this.considerAmt = considerAmt;
	}
	/**
	 * @return String
	 */
	public String getWardLabel() {
		return wardLabel;
	}
	/**
	 * @param wardLabel
	 */
	public void setWardLabel(String wardLabel) {
		this.wardLabel = wardLabel;
	}
	/**
	 * @return String
	 */
	public String getMahallaLabel() {
		return mahallaLabel;
	}
	/**
	 * @param mahallaLabel
	 */
	public void setMahallaLabel(String mahallaLabel) {
		this.mahallaLabel = mahallaLabel;
	}
	/**
	 * @return String
	 */
	public String getPatwariStatus() {
		return patwariStatus;
	}
	/**
	 * @param patwariStatus
	 */
	public void setPatwariStatus(String patwariStatus) {
		this.patwariStatus = patwariStatus;
	}
	/**
	 * @return String
	 */
	public String getWard() {
		return ward;
	}
	/**
	 * @param ward
	 */
	public void setWard(String ward) {
		this.ward = ward;
	}
	/**
	 * @return String
	 */
	public String getWardId() {
		return wardId;
	}
	/**
	 * @param wardId
	 */
	public void setWardId(String wardId) {
		this.wardId = wardId;
	}
	/**
	 * @return String
	 */
	public String getHalkaNumber() {
		return halkaNumber;
	}
	/**
	 * @param halkaNumber
	 */
	public void setHalkaNumber(String halkaNumber) {
		this.halkaNumber = halkaNumber;
	}
	/**
	 * @return String
	 */
	public String getMahalla() {
		return mahalla;
	}
	/**
	 * @param mahalla
	 */
	public void setMahalla(String mahalla) {
		this.mahalla = mahalla;
	}
	/**
	 * @return String
	 */
	public double getTotalSqMeter() {
		return totalSqMeter;
	}
	/**
	 * @param totalSqMeter
	 */
	public void setTotalSqMeter(double totalSqMeter) {
		this.totalSqMeter = totalSqMeter;
	}
	/**
	 * @return String
	 */
	public String getSubClause() {
		return subClause;
	}
	/**
	 * @param subClause
	 */
	public void setSubClause(String subClause) {
		this.subClause = subClause;
	}
	/**
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return String
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return String
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return int
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return String
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return String
	 */
	public String getTehsil() {
		return tehsil;
	}
	/**
	 * @param tehsil
	 */
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}
	/**
	 * @return String
	 */
	public String getPropertyType() {
		return propertyType;
	}
	/**
	 * @param propertyType
	 */
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	/**
	 * @return String
	 */
	public String getAreaType() {
		return areaType;
	}
	/**
	 * @param areaType
	 */
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	/**
	 * @return String
	 */
	public String getMunicipalBody() {
		return municipalBody;
	}
	/**
	 * @param municipalBody
	 */
	public void setMunicipalBody(String municipalBody) {
		this.municipalBody = municipalBody;
	}
	/**
	 * @return String
	 */
	public String getDistrictID() {
		return districtID;
	}
	/**
	 * @param districtID
	 */
	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}
	/**
	 * @return String
	 */
	public String getTehsilID() {
		return tehsilID;
	}
	/**
	 * @param tehsilID
	 */
	public void setTehsilID(String tehsilID) {
		this.tehsilID = tehsilID;
	}
	/**
	 * @return String
	 */
	public String getPropertyTypeID() {
		return propertyTypeID;
	}
	/**
	 * @param propertyTypeID
	 */
	public void setPropertyTypeID(String propertyTypeID) {
		this.propertyTypeID = propertyTypeID;
	}
	/**
	 * @return String
	 */
	public String getMunicipalBodyID() {
		return municipalBodyID;
	}
	/**
	 * @param municipalBodyID
	 */
	public void setMunicipalBodyID(String municipalBodyID) {
		this.municipalBodyID = municipalBodyID;
	}
	/**
	 * @return String
	 */
	public String getMahallaId() {
		return mahallaId;
	}
	/**
	 * @param mahallaId
	 */
	public void setMahallaId(String mahallaId) {
		this.mahallaId = mahallaId;
	}
	/**
	 * @return String
	 */
	public String getAreaId() {
		return areaId;
	}
	/**
	 * @param areaId
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	/**
	 * @return String
	 */
	public String getPatwariId() {
		return patwariId;
	}
	/**
	 * @param patwariId
	 */
	public void setPatwariId(String patwariId) {
		this.patwariId = patwariId;
	}
	/**
	 * @return String
	 */
	public String getGramId() {
		return gramId;
	}
	/**
	 * @param gramId
	 */
	public void setGramId(String gramId) {
		this.gramId = gramId;
	}
	/**
	 * @return String
	 */
	public String getHdnDistrict() {
		return hdnDistrict;
	}
	/**
	 * @param hdnDistrict
	 */
	public void setHdnDistrict(String hdnDistrict) {
		this.hdnDistrict = hdnDistrict;
	}
	/**
	 * @return String
	 */
	public String getHdnTehsil() {
		return hdnTehsil;
	}
	/**
	 * @param hdnTehsil
	 */
	public void setHdnTehsil(String hdnTehsil) {
		this.hdnTehsil = hdnTehsil;
	}
	/**
	 * @return String
	 */
	public String getHdnPropertyType() {
		return hdnPropertyType;
	}
	/**
	 * @param hdnPropertyType
	 */
	public void setHdnPropertyType(String hdnPropertyType) {
		this.hdnPropertyType = hdnPropertyType;
	}
	/**
	 * @return String
	 */
	public String getHdnAreaType() {
		return hdnAreaType;
	}
	/**
	 * @param hdnAreaType
	 */
	public void setHdnAreaType(String hdnAreaType) {
		this.hdnAreaType = hdnAreaType;
	}
	/**
	 * @return String
	 */
	public String getHdnMunicipalBody() {
		return hdnMunicipalBody;
	}
	/**
	 * @param hdnMunicipalBody
	 */
	public void setHdnMunicipalBody(String hdnMunicipalBody) {
		this.hdnMunicipalBody = hdnMunicipalBody;
	}
	/**
	 * @return String
	 */
	public String getHdnWard() {
		return hdnWard;
	}
	/**
	 * @param hdnWard
	 */
	public void setHdnWard(String hdnWard) {
		this.hdnWard = hdnWard;
	}
	/**
	 * @return String
	 */
	public String getHdnMahalla() {
		return hdnMahalla;
	}
	/**
	 * @param hdnMahalla
	 */
	public void setHdnMahalla(String hdnMahalla) {
		this.hdnMahalla = hdnMahalla;
	}
	/**
	 * @return String
	 */
	public String getHdnExtSubClause() {
		return hdnExtSubClause;
	}
	/**
	 * @param hdnExtSubClause
	 */
	public void setHdnExtSubClause(String hdnExtSubClause) {
		this.hdnExtSubClause = hdnExtSubClause;
	}
	/**
	 * @return String
	 */
	public String getHdnSubClauseName() {
		return hdnSubClauseName;
	}
	/**
	 * @param hdnSubClauseName
	 */
	public void setHdnSubClauseName(String hdnSubClauseName) {
		this.hdnSubClauseName = hdnSubClauseName;
	}
	/**
	 * @return double
	 */
	public double getMarketValue() {
		return marketValue;
	}
	/**
	 * @param marketValue
	 */
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	/**
	 * @return String
	 */
	public String getLandMeterId() {
		return landMeterId;
	}
	/**
	 * @param landMeterId
	 */
	public void setLandMeterId(String landMeterId) {
		this.landMeterId = landMeterId;
	}
	/**
	 * @return String
	 */
	public String getLandMeterType() {
		return landMeterType;
	}
	/**
	 * @param landMeterType
	 */
	public void setLandMeterType(String landMeterType) {
		this.landMeterType = landMeterType;
	}
	/**
	 * @return String
	 */
	public String getHdnLandMeter() {
		return hdnLandMeter;
	}
	/**
	 * @param hdnLandMeter
	 */
	public void setHdnLandMeter(String hdnLandMeter) {
		this.hdnLandMeter = hdnLandMeter;
	}
	public String getMunicipalDutyName() {
		return municipalDutyName;
	}
	public void setMunicipalDutyName(String municipalDutyName) {
		this.municipalDutyName = municipalDutyName;
	}
	public String getMunicipalDuty() {
		return municipalDuty;
	}
	public void setMunicipalDuty(String municipalDuty) {
		this.municipalDuty = municipalDuty;
	}
	public String getMktValue() {
		return mktValue;
	}
	public void setMktValue(String mktValue) {
		this.mktValue = mktValue;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getChkSex() {
		return chkSex;
	}
	public void setChkSex(String chkSex) {
		this.chkSex = chkSex;
	}
	
}
 
