package com.wipro.igrs.deedDraft.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class DeedDraftAppDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String appType;
	private String appTypeID;
	private String proprtyId;

	public String getProprtyId() {
		return proprtyId;
	}

	public void setProprtyId(String proprtyId) {
		this.proprtyId = proprtyId;
	}
	private String regTxnId;

	public String getRegTxnId() {
		return regTxnId;
	}

	public void setRegTxnId(String regTxnId) {
		this.regTxnId = regTxnId;
	}
	private String propType;

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getValId() {
		return valId;
	}

	public void setValId(String valId) {
		this.valId = valId;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppTypeID() {
		return appTypeID;
	}

	public void setAppTypeID(String appTypeID) {
		this.appTypeID = appTypeID;
	}
	private String dutyTxnId;
	private String stampDuty;
	private String municipalDuty;
	private String janpadDuty;
	private String upkarDuty;
	private String totalDuty;
	private String registrationFee;
	private String payableDuty;
	private String payableRegFee;
	private String isUrban;
	private String plotPatwariHalka;
	private String plotSubAreaType;

	public String getPlotPatwariHalka() {
		return plotPatwariHalka;
	}

	public void setPlotPatwariHalka(String plotPatwariHalka) {
		this.plotPatwariHalka = plotPatwariHalka;
	}

	public String getPlotSubAreaType() {
		return plotSubAreaType;
	}

	public void setPlotSubAreaType(String plotSubAreaType) {
		this.plotSubAreaType = plotSubAreaType;
	}

	public String getIsUrban() {
		return isUrban;
	}

	public void setIsUrban(String isUrban) {
		this.isUrban = isUrban;
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

	public String getJanpadDuty() {
		return janpadDuty;
	}

	public void setJanpadDuty(String janpadDuty) {
		this.janpadDuty = janpadDuty;
	}

	public String getUpkarDuty() {
		return upkarDuty;
	}

	public void setUpkarDuty(String upkarDuty) {
		this.upkarDuty = upkarDuty;
	}

	public String getTotalDuty() {
		return totalDuty;
	}

	public void setTotalDuty(String totalDuty) {
		this.totalDuty = totalDuty;
	}

	public String getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}

	public String getPayableDuty() {
		return payableDuty;
	}

	public void setPayableDuty(String payableDuty) {
		this.payableDuty = payableDuty;
	}

	public String getPayableRegFee() {
		return payableRegFee;
	}

	public void setPayableRegFee(String payableRegFee) {
		this.payableRegFee = payableRegFee;
	}

	public String getDutyTxnId() {
		return dutyTxnId;
	}

	public void setDutyTxnId(String dutyTxnId) {
		this.dutyTxnId = dutyTxnId;
	}
	private String deedType;
	private String instrumentType;
	private String subInstrumentType;
	private String subInstCheck;

	public String getSubInstCheck() {
		return subInstCheck;
	}

	public void setSubInstCheck(String subInstCheck) {
		this.subInstCheck = subInstCheck;
	}

	public String getDeedType() {
		return deedType;
	}

	public void setDeedType(String deedType) {
		this.deedType = deedType;
	}

	public String getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}

	public String getSubInstrumentType() {
		return subInstrumentType;
	}

	public void setSubInstrumentType(String subInstrumentType) {
		this.subInstrumentType = subInstrumentType;
	}
	//for property details
	private String vikasKhand;
	private String riCircle;
	private String layoutDetails;
	private String nazoolOrSheetNumber;
	private String plotNumber;
	private String propertyAddress;
	private String propertyLandmark;
	private String khashraNumber;
	private String khashraArea;
	private String lagan;
	private String rinPushtikaNumber;
	private String northBoundary;
	private String southBoundary;
	private String eastBoundary;
	private String westBoundary;

	public String getVikasKhand() {
		return vikasKhand;
	}

	public void setVikasKhand(String vikasKhand) {
		this.vikasKhand = vikasKhand;
	}

	public String getRiCircle() {
		return riCircle;
	}

	public void setRiCircle(String riCircle) {
		this.riCircle = riCircle;
	}

	public String getLayoutDetails() {
		return layoutDetails;
	}

	public void setLayoutDetails(String layoutDetails) {
		this.layoutDetails = layoutDetails;
	}

	public String getNazoolOrSheetNumber() {
		return nazoolOrSheetNumber;
	}

	public void setNazoolOrSheetNumber(String nazoolOrSheetNumber) {
		this.nazoolOrSheetNumber = nazoolOrSheetNumber;
	}

	public String getPlotNumber() {
		return plotNumber;
	}

	public void setPlotNumber(String plotNumber) {
		this.plotNumber = plotNumber;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getPropertyLandmark() {
		return propertyLandmark;
	}

	public void setPropertyLandmark(String propertyLandmark) {
		this.propertyLandmark = propertyLandmark;
	}

	public String getKhashraNumber() {
		return khashraNumber;
	}

	public void setKhashraNumber(String khashraNumber) {
		this.khashraNumber = khashraNumber;
	}

	public String getKhashraArea() {
		return khashraArea;
	}

	public void setKhashraArea(String khashraArea) {
		this.khashraArea = khashraArea;
	}

	public String getLagan() {
		return lagan;
	}

	public void setLagan(String lagan) {
		this.lagan = lagan;
	}

	public String getRinPushtikaNumber() {
		return rinPushtikaNumber;
	}

	public void setRinPushtikaNumber(String rinPushtikaNumber) {
		this.rinPushtikaNumber = rinPushtikaNumber;
	}

	public String getNorthBoundary() {
		return northBoundary;
	}

	public void setNorthBoundary(String northBoundary) {
		this.northBoundary = northBoundary;
	}

	public String getSouthBoundary() {
		return southBoundary;
	}

	public void setSouthBoundary(String southBoundary) {
		this.southBoundary = southBoundary;
	}

	public String getEastBoundary() {
		return eastBoundary;
	}

	public void setEastBoundary(String eastBoundary) {
		this.eastBoundary = eastBoundary;
	}

	public String getWestBoundary() {
		return westBoundary;
	}

	public void setWestBoundary(String westBoundary) {
		this.westBoundary = westBoundary;
	}
	private String valId;
	//valuation details
	private String districtName;
	private String tehsilName;
	private String areaTypeName;
	private String subAreaTypeName;
	private String governingMunipalBodyName;
	private String wardName;
	private String colonyName;
	private String totalArea;
	private String residentialArea;
	private String commercialArea;
	private String industrialArea;
	private String educationalArea;
	private String healthServiceArea;
	private String otherArea;
	private String resCumCommPurpose;
	private String resiCumCommPurposeArea;

	public String getResiCumCommPurposeArea() {
		return resiCumCommPurposeArea;
	}

	public void setResiCumCommPurposeArea(String resiCumCommPurposeArea) {
		this.resiCumCommPurposeArea = resiCumCommPurposeArea;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getAreaTypeName() {
		return areaTypeName;
	}

	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}

	public String getGoverningMunipalBodyName() {
		return governingMunipalBodyName;
	}

	public void setGoverningMunipalBodyName(String governingMunipalBodyName) {
		this.governingMunipalBodyName = governingMunipalBodyName;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getColonyName() {
		return colonyName;
	}

	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}

	public String getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}

	public String getResidentialArea() {
		return residentialArea;
	}

	public void setResidentialArea(String residentialArea) {
		this.residentialArea = residentialArea;
	}

	public String getCommercialArea() {
		return commercialArea;
	}

	public void setCommercialArea(String commercialArea) {
		this.commercialArea = commercialArea;
	}

	public String getIndustrialArea() {
		return industrialArea;
	}

	public void setIndustrialArea(String industrialArea) {
		this.industrialArea = industrialArea;
	}

	public String getEducationalArea() {
		return educationalArea;
	}

	public void setEducationalArea(String educationalArea) {
		this.educationalArea = educationalArea;
	}

	public String getHealthServiceArea() {
		return healthServiceArea;
	}

	public void setHealthServiceArea(String healthServiceArea) {
		this.healthServiceArea = healthServiceArea;
	}

	public String getOtherArea() {
		return otherArea;
	}

	public void setOtherArea(String otherArea) {
		this.otherArea = otherArea;
	}

	public String getResCumCommPurpose() {
		return resCumCommPurpose;
	}

	public void setResCumCommPurpose(String resCumCommPurpose) {
		this.resCumCommPurpose = resCumCommPurpose;
	}

	public String getSubAreaTypeName() {
		return subAreaTypeName;
	}

	public void setSubAreaTypeName(String subAreaTypeName) {
		this.subAreaTypeName = subAreaTypeName;
	}
	private String propTypeId;

	public String getPropTypeId() {
		return propTypeId;
	}

	public void setPropTypeId(String propTypeId) {
		this.propTypeId = propTypeId;
	}
	private String openTerraceUsage;
	private String openTerraceArea;
	private String floorName;
	private String older;
	private String isLiftMall;
	private String openTerraceFlag;
	private String multiStoreyTypeId;
	private String multiStoreyUsageName;
	private String nearRoad;
	private String resiArea;
	private String commArea;
	private String indArea;
	private String schoolArea;
	private String healthArea;
	private String resiCommArea;
	private String isAkvn;
	private String isSuperConstruction;
	private String healthTCP;
	private String eduTCP;
	private String isHosingBoard;
	private ArrayList floorAreaList;
	private String buildingTypeId;
	private ArrayList khashraList;

	public ArrayList getKhashraList() {
		return khashraList;
	}

	public void setKhashraList(ArrayList khashraList) {
		this.khashraList = khashraList;
	}

	public ArrayList getFloorAreaList() {
		return floorAreaList;
	}

	public void setFloorAreaList(ArrayList floorAreaList) {
		this.floorAreaList = floorAreaList;
	}

	public String getBuildingTypeId() {
		return buildingTypeId;
	}

	public void setBuildingTypeId(String buildingTypeId) {
		this.buildingTypeId = buildingTypeId;
	}

	public String getIsHosingBoard() {
		return isHosingBoard;
	}

	public void setIsHosingBoard(String isHosingBoard) {
		this.isHosingBoard = isHosingBoard;
	}

	public String getResiArea() {
		return resiArea;
	}

	public void setResiArea(String resiArea) {
		this.resiArea = resiArea;
	}

	public String getCommArea() {
		return commArea;
	}

	public void setCommArea(String commArea) {
		this.commArea = commArea;
	}

	public String getIndArea() {
		return indArea;
	}

	public void setIndArea(String indArea) {
		this.indArea = indArea;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getHealthArea() {
		return healthArea;
	}

	public void setHealthArea(String healthArea) {
		this.healthArea = healthArea;
	}

	public String getResiCommArea() {
		return resiCommArea;
	}

	public void setResiCommArea(String resiCommArea) {
		this.resiCommArea = resiCommArea;
	}

	public String getIsAkvn() {
		return isAkvn;
	}

	public void setIsAkvn(String isAkvn) {
		this.isAkvn = isAkvn;
	}

	public String getIsSuperConstruction() {
		return isSuperConstruction;
	}

	public void setIsSuperConstruction(String isSuperConstruction) {
		this.isSuperConstruction = isSuperConstruction;
	}

	public String getHealthTCP() {
		return healthTCP;
	}

	public void setHealthTCP(String healthTCP) {
		this.healthTCP = healthTCP;
	}

	public String getEduTCP() {
		return eduTCP;
	}

	public void setEduTCP(String eduTCP) {
		this.eduTCP = eduTCP;
	}

	public String getNearRoad() {
		return nearRoad;
	}

	public void setNearRoad(String nearRoad) {
		this.nearRoad = nearRoad;
	}

	public String getMultiStoreyTypeId() {
		return multiStoreyTypeId;
	}

	public void setMultiStoreyTypeId(String multiStoreyTypeId) {
		this.multiStoreyTypeId = multiStoreyTypeId;
	}

	public String getMultiStoreyUsageName() {
		return multiStoreyUsageName;
	}

	public void setMultiStoreyUsageName(String multiStoreyUsageName) {
		this.multiStoreyUsageName = multiStoreyUsageName;
	}

	public String getCommonArea() {
		return commonArea;
	}

	public void setCommonArea(String commonArea) {
		this.commonArea = commonArea;
	}

	public String getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(String builtUpArea) {
		this.builtUpArea = builtUpArea;
	}
	private String commonArea;
	private String builtUpArea;

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getOlder() {
		return older;
	}

	public void setOlder(String older) {
		this.older = older;
	}

	public String getIsLiftMall() {
		return isLiftMall;
	}

	public void setIsLiftMall(String isLiftMall) {
		this.isLiftMall = isLiftMall;
	}

	public String getOpenTerraceFlag() {
		return openTerraceFlag;
	}

	public void setOpenTerraceFlag(String openTerraceFlag) {
		this.openTerraceFlag = openTerraceFlag;
	}

	public String getOpenTerraceUsage() {
		return openTerraceUsage;
	}

	public void setOpenTerraceUsage(String openTerraceUsage) {
		this.openTerraceUsage = openTerraceUsage;
	}

	public String getOpenTerraceArea() {
		return openTerraceArea;
	}

	public void setOpenTerraceArea(String openTerraceArea) {
		this.openTerraceArea = openTerraceArea;
	}
	//Floor Details
	private String floorPropId;
	private String rccArea;
	private String kacchaArea;
	private String tinArea;
	private String rbcArea;
	private String shopArea;
	private String officeArea;
	private String godownArea;

	public String getFloorPropId() {
		return floorPropId;
	}

	public void setFloorPropId(String floorPropId) {
		this.floorPropId = floorPropId;
	}

	public String getRccArea() {
		return rccArea;
	}

	public void setRccArea(String rccArea) {
		this.rccArea = rccArea;
	}

	public String getKacchaArea() {
		return kacchaArea;
	}

	public void setKacchaArea(String kacchaArea) {
		this.kacchaArea = kacchaArea;
	}

	public String getTinArea() {
		return tinArea;
	}

	public void setTinArea(String tinArea) {
		this.tinArea = tinArea;
	}

	public String getRbcArea() {
		return rbcArea;
	}

	public void setRbcArea(String rbcArea) {
		this.rbcArea = rbcArea;
	}

	public String getShopArea() {
		return shopArea;
	}

	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}

	public String getOfficeArea() {
		return officeArea;
	}

	public void setOfficeArea(String officeArea) {
		this.officeArea = officeArea;
	}

	public String getGodownArea() {
		return godownArea;
	}

	public void setGodownArea(String godownArea) {
		this.godownArea = godownArea;
	}
	//agri details
	private String commonAgriSingleBuyer;
	private String commonAgriSameFamily;
	private String commonAgriBuyerCount;
	private String commonAgriTreePresent;
	private String commonAgriDiscloseShare;
	private String areaId;

	public String getCommonAgriSingleBuyer() {
		return commonAgriSingleBuyer;
	}

	public void setCommonAgriSingleBuyer(String commonAgriSingleBuyer) {
		this.commonAgriSingleBuyer = commonAgriSingleBuyer;
	}

	public String getCommonAgriSameFamily() {
		return commonAgriSameFamily;
	}

	public void setCommonAgriSameFamily(String commonAgriSameFamily) {
		this.commonAgriSameFamily = commonAgriSameFamily;
	}

	public String getCommonAgriBuyerCount() {
		return commonAgriBuyerCount;
	}

	public void setCommonAgriBuyerCount(String commonAgriBuyerCount) {
		this.commonAgriBuyerCount = commonAgriBuyerCount;
	}

	public String getCommonAgriTreePresent() {
		return commonAgriTreePresent;
	}

	public void setCommonAgriTreePresent(String commonAgriTreePresent) {
		this.commonAgriTreePresent = commonAgriTreePresent;
	}

	public String getCommonAgriDiscloseShare() {
		return commonAgriDiscloseShare;
	}

	public void setCommonAgriDiscloseShare(String commonAgriDiscloseShare) {
		this.commonAgriDiscloseShare = commonAgriDiscloseShare;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	private ArrayList agriAreaDetailsList;

	public ArrayList getAgriAreaDetailsList() {
		return agriAreaDetailsList;
	}

	public void setAgriAreaDetailsList(ArrayList agriAreaDetailsList) {
		this.agriAreaDetailsList = agriAreaDetailsList;
	}
	private ArrayList griPropDetailsList;
	private ArrayList griTreeDetailsList;

	public ArrayList getGriPropDetailsList() {
		return griPropDetailsList;
	}

	public void setGriPropDetailsList(ArrayList griPropDetailsList) {
		this.griPropDetailsList = griPropDetailsList;
	}

	public ArrayList getGriTreeDetailsList() {
		return griTreeDetailsList;
	}

	public void setGriTreeDetailsList(ArrayList griTreeDetailsList) {
		this.griTreeDetailsList = griTreeDetailsList;
	}
	private String commonAgriTxnid;
	private String commonAgriSubTypeId;
	private String commonAgriPropSubTypeName;
	private String commonTotalUndivArea;
	private String commonTotalUnirriOneCrop;
	private String commonTotalUnirriTwoCrop;
	private String commonTotalIrrigatedArea;
	private String commonAgriConstruction;
	private String commonTotalDivArea;
	private String commonTotalResiArea;
	private String commonTotalCommArea;
	private String commonTotalIndArea;
	private String commonTotalEduArea;
	private String commonTotalHealthArea;
	private String commonTotalOtherArea;
	private String commonAgriEducationTcp;
	private String commonAgriHealthTcp;

	public String getCommonAgriTxnid() {
		return commonAgriTxnid;
	}

	public void setCommonAgriTxnid(String commonAgriTxnid) {
		this.commonAgriTxnid = commonAgriTxnid;
	}

	public String getCommonAgriSubTypeId() {
		return commonAgriSubTypeId;
	}

	public void setCommonAgriSubTypeId(String commonAgriSubTypeId) {
		this.commonAgriSubTypeId = commonAgriSubTypeId;
	}

	public String getCommonAgriPropSubTypeName() {
		return commonAgriPropSubTypeName;
	}

	public void setCommonAgriPropSubTypeName(String commonAgriPropSubTypeName) {
		this.commonAgriPropSubTypeName = commonAgriPropSubTypeName;
	}

	public String getCommonTotalUndivArea() {
		return commonTotalUndivArea;
	}

	public void setCommonTotalUndivArea(String commonTotalUndivArea) {
		this.commonTotalUndivArea = commonTotalUndivArea;
	}

	public String getCommonTotalUnirriOneCrop() {
		return commonTotalUnirriOneCrop;
	}

	public void setCommonTotalUnirriOneCrop(String commonTotalUnirriOneCrop) {
		this.commonTotalUnirriOneCrop = commonTotalUnirriOneCrop;
	}

	public String getCommonTotalUnirriTwoCrop() {
		return commonTotalUnirriTwoCrop;
	}

	public void setCommonTotalUnirriTwoCrop(String commonTotalUnirriTwoCrop) {
		this.commonTotalUnirriTwoCrop = commonTotalUnirriTwoCrop;
	}

	public String getCommonTotalIrrigatedArea() {
		return commonTotalIrrigatedArea;
	}

	public void setCommonTotalIrrigatedArea(String commonTotalIrrigatedArea) {
		this.commonTotalIrrigatedArea = commonTotalIrrigatedArea;
	}

	public String getCommonAgriConstruction() {
		return commonAgriConstruction;
	}

	public void setCommonAgriConstruction(String commonAgriConstruction) {
		this.commonAgriConstruction = commonAgriConstruction;
	}

	public String getCommonTotalDivArea() {
		return commonTotalDivArea;
	}

	public void setCommonTotalDivArea(String commonTotalDivArea) {
		this.commonTotalDivArea = commonTotalDivArea;
	}

	public String getCommonTotalResiArea() {
		return commonTotalResiArea;
	}

	public void setCommonTotalResiArea(String commonTotalResiArea) {
		this.commonTotalResiArea = commonTotalResiArea;
	}

	public String getCommonTotalCommArea() {
		return commonTotalCommArea;
	}

	public void setCommonTotalCommArea(String commonTotalCommArea) {
		this.commonTotalCommArea = commonTotalCommArea;
	}

	public String getCommonTotalIndArea() {
		return commonTotalIndArea;
	}

	public void setCommonTotalIndArea(String commonTotalIndArea) {
		this.commonTotalIndArea = commonTotalIndArea;
	}

	public String getCommonTotalEduArea() {
		return commonTotalEduArea;
	}

	public void setCommonTotalEduArea(String commonTotalEduArea) {
		this.commonTotalEduArea = commonTotalEduArea;
	}

	public String getCommonTotalHealthArea() {
		return commonTotalHealthArea;
	}

	public void setCommonTotalHealthArea(String commonTotalHealthArea) {
		this.commonTotalHealthArea = commonTotalHealthArea;
	}

	public String getCommonTotalOtherArea() {
		return commonTotalOtherArea;
	}

	public void setCommonTotalOtherArea(String commonTotalOtherArea) {
		this.commonTotalOtherArea = commonTotalOtherArea;
	}

	public String getCommonAgriEducationTcp() {
		return commonAgriEducationTcp;
	}

	public void setCommonAgriEducationTcp(String commonAgriEducationTcp) {
		this.commonAgriEducationTcp = commonAgriEducationTcp;
	}

	public String getCommonAgriHealthTcp() {
		return commonAgriHealthTcp;
	}

	public void setCommonAgriHealthTcp(String commonAgriHealthTcp) {
		this.commonAgriHealthTcp = commonAgriHealthTcp;
	}
	private String commonAgriTreeCount;
	private String commonAgriTreeName;

	public String getCommonAgriTreeCount() {
		return commonAgriTreeCount;
	}

	public void setCommonAgriTreeCount(String commonAgriTreeCount) {
		this.commonAgriTreeCount = commonAgriTreeCount;
	}

	public String getCommonAgriTreeName() {
		return commonAgriTreeName;
	}

	public void setCommonAgriTreeName(String commonAgriTreeName) {
		this.commonAgriTreeName = commonAgriTreeName;
	}
	private ArrayList agriPropDetailsList;
	private ArrayList agriTreeDetailsList;
	private String agriTreeChkd;
	private ArrayList listOfTreePresent;

	public ArrayList getListOfTreePresent() {
		return listOfTreePresent;
	}

	public void setListOfTreePresent(ArrayList listOfTreePresent) {
		this.listOfTreePresent = listOfTreePresent;
	}

	public String getAgriTreeChkd() {
		return agriTreeChkd;
	}

	public void setAgriTreeChkd(String agriTreeChkd) {
		this.agriTreeChkd = agriTreeChkd;
	}

	public ArrayList getAgriPropDetailsList() {
		return agriPropDetailsList;
	}

	public void setAgriPropDetailsList(ArrayList agriPropDetailsList) {
		this.agriPropDetailsList = agriPropDetailsList;
	}

	public ArrayList getAgriTreeDetailsList() {
		return agriTreeDetailsList;
	}

	public void setAgriTreeDetailsList(ArrayList agriTreeDetailsList) {
		this.agriTreeDetailsList = agriTreeDetailsList;
	}
	private ArrayList agriAreaStore;
	private ArrayList agriPropStore;
	private ArrayList agriTreeStore;

	public ArrayList getAgriAreaStore() {
		return agriAreaStore;
	}

	public void setAgriAreaStore(ArrayList agriAreaStore) {
		this.agriAreaStore = agriAreaStore;
	}

	public ArrayList getAgriPropStore() {
		return agriPropStore;
	}

	public void setAgriPropStore(ArrayList agriPropStore) {
		this.agriPropStore = agriPropStore;
	}

	public ArrayList getAgriTreeStore() {
		return agriTreeStore;
	}

	public void setAgriTreeStore(ArrayList agriTreeStore) {
		this.agriTreeStore = agriTreeStore;
	}
	//for prop duty details
	private String propDutyStampDuty;
	private String propDutyMunicipalDuty;
	private String propDutyBlockDuty;
	private String propDutyUpkarDuty;
	private String propDutyTotal;
	private String propDutyRegFee;
	private String propDutyPayableDuty;
	private String propDutyPayableRegFee;
	private String propDutySysMv;
	private ArrayList propDutyUserPropWiseEnterableList;
	private String propDutyLabelName;
	private String propDutyLabelValue;
	private String propExempDuty;
	private String propExempRegFee;
	private String exempDuty;
	private String exempRegFee;

	public String getExempDuty() {
		return exempDuty;
	}

	public void setExempDuty(String exempDuty) {
		this.exempDuty = exempDuty;
	}

	public String getExempRegFee() {
		return exempRegFee;
	}

	public void setExempRegFee(String exempRegFee) {
		this.exempRegFee = exempRegFee;
	}

	public String getPropExempDuty() {
		return propExempDuty;
	}

	public void setPropExempDuty(String propExempDuty) {
		this.propExempDuty = propExempDuty;
	}

	public String getPropExempRegFee() {
		return propExempRegFee;
	}

	public void setPropExempRegFee(String propExempRegFee) {
		this.propExempRegFee = propExempRegFee;
	}

	public String getPropDutyLabelName() {
		return propDutyLabelName;
	}

	public void setPropDutyLabelName(String propDutyLabelName) {
		this.propDutyLabelName = propDutyLabelName;
	}

	public String getPropDutyLabelValue() {
		return propDutyLabelValue;
	}

	public void setPropDutyLabelValue(String propDutyLabelValue) {
		this.propDutyLabelValue = propDutyLabelValue;
	}

	public String getPropDutyStampDuty() {
		return propDutyStampDuty;
	}

	public void setPropDutyStampDuty(String propDutyStampDuty) {
		this.propDutyStampDuty = propDutyStampDuty;
	}

	public String getPropDutyMunicipalDuty() {
		return propDutyMunicipalDuty;
	}

	public void setPropDutyMunicipalDuty(String propDutyMunicipalDuty) {
		this.propDutyMunicipalDuty = propDutyMunicipalDuty;
	}

	public String getPropDutyBlockDuty() {
		return propDutyBlockDuty;
	}

	public void setPropDutyBlockDuty(String propDutyBlockDuty) {
		this.propDutyBlockDuty = propDutyBlockDuty;
	}

	public String getPropDutyUpkarDuty() {
		return propDutyUpkarDuty;
	}

	public void setPropDutyUpkarDuty(String propDutyUpkarDuty) {
		this.propDutyUpkarDuty = propDutyUpkarDuty;
	}

	public String getPropDutyTotal() {
		return propDutyTotal;
	}

	public void setPropDutyTotal(String propDutyTotal) {
		this.propDutyTotal = propDutyTotal;
	}

	public String getPropDutyRegFee() {
		return propDutyRegFee;
	}

	public void setPropDutyRegFee(String propDutyRegFee) {
		this.propDutyRegFee = propDutyRegFee;
	}

	public String getPropDutyPayableDuty() {
		return propDutyPayableDuty;
	}

	public void setPropDutyPayableDuty(String propDutyPayableDuty) {
		this.propDutyPayableDuty = propDutyPayableDuty;
	}

	public String getPropDutyPayableRegFee() {
		return propDutyPayableRegFee;
	}

	public void setPropDutyPayableRegFee(String propDutyPayableRegFee) {
		this.propDutyPayableRegFee = propDutyPayableRegFee;
	}

	public String getPropDutySysMv() {
		return propDutySysMv;
	}

	public void setPropDutySysMv(String propDutySysMv) {
		this.propDutySysMv = propDutySysMv;
	}

	public ArrayList getPropDutyUserPropWiseEnterableList() {
		return propDutyUserPropWiseEnterableList;
	}

	public void setPropDutyUserPropWiseEnterableList(ArrayList propDutyUserPropWiseEnterableList) {
		this.propDutyUserPropWiseEnterableList = propDutyUserPropWiseEnterableList;
	}
	//agri cosntruction details
	private ArrayList agriConstructionDetailsList;

	public ArrayList getAgriConstructionDetailsList() {
		return agriConstructionDetailsList;
	}

	public void setAgriConstructionDetailsList(ArrayList agriConstructionDetailsList) {
		this.agriConstructionDetailsList = agriConstructionDetailsList;
	}
	//clause list
	private String clauseId;
	private String clasueName;
	private ArrayList clauseList;

	public ArrayList getClauseList() {
		return clauseList;
	}

	public void setClauseList(ArrayList clauseList) {
		this.clauseList = clauseList;
	}

	public String getClauseId() {
		return clauseId;
	}

	public void setClauseId(String clauseId) {
		this.clauseId = clauseId;
	}

	public String getClasueName() {
		return clasueName;
	}

	public void setClasueName(String clasueName) {
		this.clasueName = clasueName;
	}
	//consenter details
	private String consenterName;
	private String consenterStampDuty;
	private String consenterMunicipalDuty;
	private String consenterJanpadDuty;
	private String consenterUpkarDuty;
	private String consenterTotal;
	private String consenterRegFee;
	private String consenterTotalAfterExemp;
	private String consenterRegFeeAfterExemp;
	private String consenterExempDuty;
	private String consenterExempRegFee;

	public String getConsenterName() {
		return consenterName;
	}

	public void setConsenterName(String consenterName) {
		this.consenterName = consenterName;
	}

	public String getConsenterStampDuty() {
		return consenterStampDuty;
	}

	public void setConsenterStampDuty(String consenterStampDuty) {
		this.consenterStampDuty = consenterStampDuty;
	}

	public String getConsenterMunicipalDuty() {
		return consenterMunicipalDuty;
	}

	public void setConsenterMunicipalDuty(String consenterMunicipalDuty) {
		this.consenterMunicipalDuty = consenterMunicipalDuty;
	}

	public String getConsenterJanpadDuty() {
		return consenterJanpadDuty;
	}

	public void setConsenterJanpadDuty(String consenterJanpadDuty) {
		this.consenterJanpadDuty = consenterJanpadDuty;
	}

	public String getConsenterUpkarDuty() {
		return consenterUpkarDuty;
	}

	public void setConsenterUpkarDuty(String consenterUpkarDuty) {
		this.consenterUpkarDuty = consenterUpkarDuty;
	}

	public String getConsenterTotal() {
		return consenterTotal;
	}

	public void setConsenterTotal(String consenterTotal) {
		this.consenterTotal = consenterTotal;
	}

	public String getConsenterRegFee() {
		return consenterRegFee;
	}

	public void setConsenterRegFee(String consenterRegFee) {
		this.consenterRegFee = consenterRegFee;
	}

	public String getConsenterTotalAfterExemp() {
		return consenterTotalAfterExemp;
	}

	public void setConsenterTotalAfterExemp(String consenterTotalAfterExemp) {
		this.consenterTotalAfterExemp = consenterTotalAfterExemp;
	}

	public String getConsenterRegFeeAfterExemp() {
		return consenterRegFeeAfterExemp;
	}

	public void setConsenterRegFeeAfterExemp(String consenterRegFeeAfterExemp) {
		this.consenterRegFeeAfterExemp = consenterRegFeeAfterExemp;
	}

	public String getConsenterExempDuty() {
		return consenterExempDuty;
	}

	public void setConsenterExempDuty(String consenterExempDuty) {
		this.consenterExempDuty = consenterExempDuty;
	}

	public String getConsenterExempRegFee() {
		return consenterExempRegFee;
	}

	public void setConsenterExempRegFee(String consenterExempRegFee) {
		this.consenterExempRegFee = consenterExempRegFee;
	}
	private String isPropDuty;

	public String getIsPropDuty() {
		return isPropDuty;
	}

	public void setIsPropDuty(String isPropDuty) {
		this.isPropDuty = isPropDuty;
	}
	private ArrayList agriConstClauseList;

	public ArrayList getAgriConstClauseList() {
		return agriConstClauseList;
	}

	public void setAgriConstClauseList(ArrayList agriConstClauseList) {
		this.agriConstClauseList = agriConstClauseList;
	}
	
	private String isConstSlabApplied;
	private String appliedConstSlabDesc;

	public String getIsConstSlabApplied() {
		return isConstSlabApplied;
	}

	public void setIsConstSlabApplied(String isConstSlabApplied) {
		this.isConstSlabApplied = isConstSlabApplied;
	}

	public String getAppliedConstSlabDesc() {
		return appliedConstSlabDesc;
	}

	public void setAppliedConstSlabDesc(String appliedConstSlabDesc) {
		this.appliedConstSlabDesc = appliedConstSlabDesc;
	}
	
}
