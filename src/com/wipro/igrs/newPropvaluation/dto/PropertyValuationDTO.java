package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.org.apache.bcel.internal.generic.NEW;



public class PropertyValuationDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<PropertyValuationDTO> tehsilList;
	private ArrayList<PropertyValuationDTO> areaList;
	private ArrayList<PropertyValuationDTO> subAreaList;
	private ArrayList<PropertyValuationDTO> wardPatwariList;
	private ArrayList<PropertyValuationDTO> colonyList;
	private ArrayList<PropertyValuationDTO>  propertyTypeList;
	private ArrayList<PropertyValuationDTO>  buildingTypeList;
	private ArrayList<PropertyValuationDTO>  openTerraceUsageList;
	private ArrayList<PropertyValuationDTO>  floorTypeList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO>  multiStoreyTypeList; 
	private ArrayList<PropertyValuationDTO>  floorTypeListBuilding;
	private ArrayList<PropertyValuationDTO>  constructionSlabList;// added by Rustam
	private String rinPustikaCheckBoxSelected;
	private String transactionaArea;
	//added by akansha for lgd code change
	private ArrayList villageKhasraList;
	private String khasra;

	private String instClrFlag;

	public PropertyValuationDTO(){
		
		
	}

	//below added by Roopam on 11 feb 2014
	private double constCost;
	
	private int isMultipleProperty=0;  // added by roopam for reg init
	

	
	
	public int getIsMultipleProperty() {
		return isMultipleProperty;
	}
	public void setIsMultipleProperty(int isMultipleProperty) {
		this.isMultipleProperty = isMultipleProperty;
	}
	private int id;
	private String desc;
	private int area;
	private String allAreaZero;
	public String getAllAreaZero() {
		return allAreaZero;
	}
	public void setAllAreaZero(String allAreaZero) {
		this.allAreaZero = allAreaZero;
	}
	private String constructionCostFinal="0.0";
	public String getConstructionCostFinal() {
		return constructionCostFinal;
	}
	public void setConstructionCostFinal(String constructionCostFinal) {
		this.constructionCostFinal = constructionCostFinal;
	}
	public String getPlotValueFinal() {
		return plotValueFinal;
	}
	public void setPlotValueFinal(String plotValueFinal) {
		this.plotValueFinal = plotValueFinal;
	}
	private String plotValueFinal;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public double getConstCost() {
		return constCost;
	}
	public void setConstCost(double constCost) {
		this.constCost = constCost;
	}
	public ArrayList<PropertyValuationDTO> getMultiStoreyTypeList() {
		return multiStoreyTypeList;
	}
	public void setMultiStoreyTypeList(
			ArrayList<PropertyValuationDTO> multiStoreyTypeList) {
		this.multiStoreyTypeList = multiStoreyTypeList;
	}
	public ArrayList<PropertyValuationDTO> getBuildingTypeList() {
		return buildingTypeList;
	}
	public void setBuildingTypeList(ArrayList<PropertyValuationDTO> buildingTypeList) {
		this.buildingTypeList = buildingTypeList;
	}
	public ArrayList<PropertyValuationDTO> getPropertyTypeList() {
		return propertyTypeList;
	}
	public void setPropertyTypeList(ArrayList<PropertyValuationDTO> propertyTypeList) {
		this.propertyTypeList = propertyTypeList;
	}
	private String districtName;
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	private String buildingTypeName;
	private String buildingTypeId;
	public String getBuildingTypeName() {
		return buildingTypeName;
	}
	public void setBuildingTypeName(String buildingTypeName) {
		this.buildingTypeName = buildingTypeName;
	}
	public String getBuildingTypeId() {
		return buildingTypeId;
	}
	public void setBuildingTypeId(String buildingTypeId) {
		this.buildingTypeId = buildingTypeId;
	}

	private String districtId;

	private String tehsilName;
	private String tehsilId;
	private String areaName;
	private String areaId;
	private String subAreaName;
	private String subAreaId;
	private String wardPatwariName;
	private String wardPatwariId;
	private String colonyName;
	private String colonyId;
	private String radioButton;
	private String radioButton1;
	private String openTerraceUsageName;
	private String openTerraceUsageId;
	private String openTerraceArea; 
	private String floorId;
	private String floorName;
	private String multiStoreyTypeId;
	private String multiStoreyTypeName;
	private String commonArea;
	private String builtUpArea;
	private String onlyIndustrial;
	private String isAkvn;
	private String isSuperConstruction;
	private String isAkvnFlag;
	private String isSuperConstructionFlag;
	private String indAreaZero;
	private String resiCommFlag;
	private String resiCommId;
	private String resiCommName;
	private String resiCommArea;
	
	private String rinPustikaNoClr;
	//added by Rustam
	private int olderId;
	private String durationYear;
	private int olderOperand;
	private String operatorname;





	public int getOlderId() {
		return olderId;
	}
	public void setOlderId(int olderId) {
		this.olderId = olderId;
	}
	public int getOlderOperand() {
		return olderOperand;
	}
	public void setOlderOperand(int olderOperand) {
		this.olderOperand = olderOperand;
	}
	public String getDurationYear() {
		return durationYear;
	}
	public void setDurationYear(String durationYear) {
		this.durationYear = durationYear;
	}

	public String getOperatorname() {
		return operatorname;
	}
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}
	public String getResiCommId() {
		return resiCommId;
	}
	public void setResiCommId(String resiCommId) {
		this.resiCommId = resiCommId;
	}
	public String getResiCommName() {
		return resiCommName;
	}
	public void setResiCommName(String resiCommName) {
		this.resiCommName = resiCommName;
	}
	public String getResiCommFlag() {
		return resiCommFlag;
	}
	public void setResiCommFlag(String resiCommFlag) {
		this.resiCommFlag = resiCommFlag;
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
	//below added by roopam for new duty calculation flow: 26 feb 2014
	private String fromModule;
	
	
	
	public String getFromModule() {
		return fromModule;
	}
	public void setFromModule(String fromModule) {
		this.fromModule = fromModule;
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
	public String getMultiStoreyTypeId() {
		return multiStoreyTypeId;
	}
	public void setMultiStoreyTypeId(String multiStoreyTypeId) {
		this.multiStoreyTypeId = multiStoreyTypeId;
	}
	public String getMultiStoreyTypeName() {
		return multiStoreyTypeName;
	}
	public void setMultiStoreyTypeName(String multiStoreyTypeName) {
		this.multiStoreyTypeName = multiStoreyTypeName;
	}
	public ArrayList<PropertyValuationDTO> getFloorTypeList() {
		return floorTypeList;
	}
	public void setFloorTypeList(ArrayList<PropertyValuationDTO> floorTypeList) {
		this.floorTypeList = floorTypeList;
	}
	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public String getOpenTerraceArea() {
		return openTerraceArea;
	}
	public void setOpenTerraceArea(String openTerraceArea) {
		this.openTerraceArea = openTerraceArea;
	}
	public ArrayList<PropertyValuationDTO> getOpenTerraceUsageList() {
		return openTerraceUsageList;
	}
	public void setOpenTerraceUsageList(
			ArrayList<PropertyValuationDTO> openTerraceUsageList) {
		this.openTerraceUsageList = openTerraceUsageList;
	}
	public String getOpenTerraceUsageName() {
		return openTerraceUsageName;
	}
	public void setOpenTerraceUsageName(String openTerraceUsageName) {
		this.openTerraceUsageName = openTerraceUsageName;
	}
	public String getOpenTerraceUsageId() {
		return openTerraceUsageId;
	}
	public void setOpenTerraceUsageId(String openTerraceUsageId) {
		this.openTerraceUsageId = openTerraceUsageId;
	}
	public String getRadioButton() {
		return radioButton;
	}
	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}
	private String applicableSubClause;
	private String propertyId;
	private String propertyName;

	private String isOlder;
	private String isOlderFlag;
	public String getIsOlderFlag() {
		return isOlderFlag;
	}
	public void setIsOlderFlag(String isOlderFlag) {
		this.isOlderFlag = isOlderFlag;
	}
	private String older;
	private String isOpenTerrace;
	private String isOpenTerraceFlag;
	private String isLift;
	public String getIsLift() {
		return isLift;
	}
	public void setIsLift(String isLift) {
		this.isLift = isLift;
	}
	public String getIsOpenTerraceFlag() {
		return isOpenTerraceFlag;
	}
	public void setIsOpenTerraceFlag(String isOpenTerraceFlag) {
		this.isOpenTerraceFlag = isOpenTerraceFlag;
	}
	public String getIsOpenTerrace() {
		return isOpenTerrace;
	}
	public void setIsOpenTerrace(String isOpenTerrace) {
		this.isOpenTerrace = isOpenTerrace;
	}
	public String getOlder() {
		return older;
	}
	public void setOlder(String older) {
		this.older = older;
	}
	public String getIsOlder() {
		return isOlder;
	}
	public void setIsOlder(String isOlder) {
		this.isOlder = isOlder;
	}
// added by vinay
	private ArrayList<PropertyValuationDTO> resiFloorAreaList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> commFloorAreaList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> indFloorAreaList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> schoolFloorAreaList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> healthFloorAreaList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> otherFloorAreaList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> resiCommFloorAreaList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> subclauseList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> multiCommPropList;
	private ArrayList<PropertyValuationDTO> indpenBuildPropList;
	private ArrayList<PropertyValuationDTO> resiL2List;
	private ArrayList<PropertyValuationDTO> indL2List;
	private ArrayList<PropertyValuationDTO> otherL2List;
	private ArrayList<PropertyValuationDTO> commL2List;
	private ArrayList<PropertyValuationDTO> healthL2List;
	private ArrayList<PropertyValuationDTO> schoolL2List;
	private ArrayList<PropertyValuationDTO> resiCommL2List;
	
	private ArrayList<PropertyValuationDTO> floorMarketValueList=new ArrayList<PropertyValuationDTO>();
	private double floorValue;
	private String resiCommL2Area;
	private String subclauseId;
	private String subclauseName;
	private String rccArea;
	private String rbcArea;
	private String tinArea;
	private String kacchaArea;
	private String shopArea;
	private String officeArea;
	private String godownArea;
	private int addfloorcounter;
	public String getRccArea() {
		return rccArea;
	}
	public void setRccArea(String rccArea) {
		this.rccArea = rccArea;
	}
	public String getRbcArea() {
		return rbcArea;
	}
	public void setRbcArea(String rbcArea) {
		this.rbcArea = rbcArea;
	}
	public String getTinArea() {
		return tinArea;
	}
	public void setTinArea(String tinArea) {
		this.tinArea = tinArea;
	}
	public String getKacchaArea() {
		return kacchaArea;
	}
	public void setKacchaArea(String kacchaArea) {
		this.kacchaArea = kacchaArea;
	}
	public String getGodownArea() {
		return godownArea;
	}
	public void setGodownArea(String godownArea) {
		this.godownArea = godownArea;
	}
	public ArrayList<PropertyValuationDTO> getFloorMarketValueList() {
		return floorMarketValueList;
	}
	public void setFloorMarketValueList(
			ArrayList<PropertyValuationDTO> floorMarketValueList) {
		this.floorMarketValueList = floorMarketValueList;
	}
	public double getFloorValue() {
		return floorValue;
	}
	public void setFloorValue(double floorValue) {
		this.floorValue = floorValue;
	}
	private String resiL2Area;
	private String commL2Area;
	public String getResiL2Area() {
		return resiL2Area;
	}
	public void setResiL2Area(String resiL2Area) {
		this.resiL2Area = resiL2Area;
	}
	public String getCommL2Area() {
		return commL2Area;
	}
	public void setCommL2Area(String commL2Area) {
		this.commL2Area = commL2Area;
	}
	public String getSchoolL2Area() {
		return schoolL2Area;
	}
	public void setSchoolL2Area(String schoolL2Area) {
		this.schoolL2Area = schoolL2Area;
	}
	public String getOtherL2Area() {
		return otherL2Area;
	}
	public void setOtherL2Area(String otherL2Area) {
		this.otherL2Area = otherL2Area;
	}
	private String indL2Area;
	private String healthL2Area;
	private String plotCost;
	private String constructionCost;
	public String getIndL2Area() {
		return indL2Area;
	}
	public void setIndL2Area(String indL2Area) {
		this.indL2Area = indL2Area;
	}
	public String getHealthL2Area() {
		return healthL2Area;
	}
	public void setHealthL2Area(String healthL2Area) {
		this.healthL2Area = healthL2Area;
	}
	private String schoolL2Area;
	private String otherL2Area;
	private String isTncpSchool;
	private String isTncpHealth;
	private String isLiftCheck;
	private String nearRoadCheck;

	public String getIsTncpSchool() {
		return isTncpSchool;
	}
	public void setIsTncpSchool(String isTncpSchool) {
		this.isTncpSchool = isTncpSchool;
	}
	public String getIsTncpHealth() {
		return isTncpHealth;
	}
	public void setIsTncpHealth(String isTncpHealth) {
		this.isTncpHealth = isTncpHealth;
	}
	private String resiL2Id;
	private String resiL2Name;
	private String commL2Id;
	private String commL2Name;
	private String indL2Id;
	private String indL2Name;
	private String schoolL2Id;
	private String schoolL2Name;
	private String healthL2Id;
	private String healthL2Name;
	private String floorFreezeId;
	private String floorFreezeFlag;
	private String floorArea;
	private String housingFlag;
	private String housingCheck;
	private String housingCheckFlag;

	public String getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(String floorArea) {
		this.floorArea = floorArea;
	}
	private String otherL2Id;
	private String otherL2Name;
	private String guideLineId;
	private String guideLineValue;
	private String finalMarketValue;
	private String valuationId;
	public String getValuationId() {
		return valuationId;
	}
	public void setValuationId(String valuationId) {
		this.valuationId = valuationId;
	}
	public String getGuideLineId() {
		return guideLineId;
	}
	public void setGuideLineId(String guideLineId) {
		this.guideLineId = guideLineId;
	}
	public String getGuideLineValue() {
		return guideLineValue;
	}
	public void setGuideLineValue(String guideLineValue) {
		this.guideLineValue = guideLineValue;
	}
	public String getFinalMarketValue() {
		return finalMarketValue;
	}
	public void setFinalMarketValue(String finalMarketValue) {
		this.finalMarketValue = finalMarketValue;
	}
	public String getResiL2Id() {
		return resiL2Id;
	}
	public void setResiL2Id(String resiL2Id) {
		this.resiL2Id = resiL2Id;
	}
	public String getResiL2Name() {
		return resiL2Name;
	}
	public void setResiL2Name(String resiL2Name) {
		this.resiL2Name = resiL2Name;
	}
	public String getCommL2Id() {
		return commL2Id;
	}
	public void setCommL2Id(String commL2Id) {
		this.commL2Id = commL2Id;
	}
	public String getCommL2Name() {
		return commL2Name;
	}
	public void setCommL2Name(String commL2Name) {
		this.commL2Name = commL2Name;
	}
	public String getIndL2Id() {
		return indL2Id;
	}
	public void setIndL2Id(String indL2Id) {
		this.indL2Id = indL2Id;
	}
	public String getIndL2Name() {
		return indL2Name;
	}
	public void setIndL2Name(String indL2Name) {
		this.indL2Name = indL2Name;
	}
	public String getSchoolL2Id() {
		return schoolL2Id;
	}
	public void setSchoolL2Id(String schoolL2Id) {
		this.schoolL2Id = schoolL2Id;
	}
	public String getSchoolL2Name() {
		return schoolL2Name;
	}
	public void setSchoolL2Name(String schoolL2Name) {
		this.schoolL2Name = schoolL2Name;
	}
	public String getHealthL2Id() {
		return healthL2Id;
	}
	public void setHealthL2Id(String healthL2Id) {
		this.healthL2Id = healthL2Id;
	}
	public String getHealthL2Name() {
		return healthL2Name;
	}
	public void setHealthL2Name(String healthL2Name) {
		this.healthL2Name = healthL2Name;
	}
	public String getOtherL2Id() {
		return otherL2Id;
	}
	public void setOtherL2Id(String otherL2Id) {
		this.otherL2Id = otherL2Id;
	}
	public String getOtherL2Name() {
		return otherL2Name;
	}
	public void setOtherL2Name(String otherL2Name) {
		this.otherL2Name = otherL2Name;
	}
	public ArrayList<PropertyValuationDTO> getResiL2List() {
		return resiL2List;
	}
	public void setResiL2List(ArrayList<PropertyValuationDTO> resiL2List) {
		this.resiL2List = resiL2List;
	}
	public ArrayList<PropertyValuationDTO> getIndL2List() {
		return indL2List;
	}
	public void setIndL2List(ArrayList<PropertyValuationDTO> indL2List) {
		this.indL2List = indL2List;
	}
	public ArrayList<PropertyValuationDTO> getOtherL2List() {
		return otherL2List;
	}
	public void setOtherL2List(ArrayList<PropertyValuationDTO> otherL2List) {
		this.otherL2List = otherL2List;
	}
	public ArrayList<PropertyValuationDTO> getCommL2List() {
		return commL2List;
	}
	public void setCommL2List(ArrayList<PropertyValuationDTO> commL2List) {
		this.commL2List = commL2List;
	}
	public ArrayList<PropertyValuationDTO> getHealthL2List() {
		return healthL2List;
	}
	public void setHealthL2List(ArrayList<PropertyValuationDTO> healthL2List) {
		this.healthL2List = healthL2List;
	}
	public ArrayList<PropertyValuationDTO> getSchoolL2List() {
		return schoolL2List;
	}
	public void setSchoolL2List(ArrayList<PropertyValuationDTO> schoolL2List) {
		this.schoolL2List = schoolL2List;
	}
	private String disableEntry;
	private String isTncpSchoolCheck;
	private String isTncpHealthCheck;
	private String indenBuildPropId;
	private String indenBuildPropName;
	private String resiFlag;
	private String commFlag;
	private String indFlag;
	private String schoolFlag;
	private String healthFlag;
	private String otherFlag;
	private String deleteFloorNo;
	private String floorNo;
	private String constructedAreaBuilding;
	private String constructedValueBuilding;
	private String constructedBuildingType;
	private String buildingTxnId;
	private String buildingTxnIdAgri;
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	public String getDeleteFloorNo() {
		return deleteFloorNo;
	}
	public void setDeleteFloorNo(String deleteFloorNo) {
		this.deleteFloorNo = deleteFloorNo;
	}
	public String getResiFlag() {
		return resiFlag;
	}
	public void setResiFlag(String resiFlag) {
		this.resiFlag = resiFlag;
	}
	public String getCommFlag() {
		return commFlag;
	}
	public void setCommFlag(String commFlag) {
		this.commFlag = commFlag;
	}
	public String getIndFlag() {
		return indFlag;
	}
	public void setIndFlag(String indFlag) {
		this.indFlag = indFlag;
	}
	public String getSchoolFlag() {
		return schoolFlag;
	}
	public void setSchoolFlag(String schoolFlag) {
		this.schoolFlag = schoolFlag;
	}
	public String getHealthFlag() {
		return healthFlag;
	}
	public void setHealthFlag(String healthFlag) {
		this.healthFlag = healthFlag;
	}
	public String getOtherFlag() {
		return otherFlag;
	}
	public void setOtherFlag(String otherFlag) {
		this.otherFlag = otherFlag;
	}
	public ArrayList<PropertyValuationDTO> getIndpenBuildPropList() {
		return indpenBuildPropList;
	}
	public void setIndpenBuildPropList(
			ArrayList<PropertyValuationDTO> indpenBuildPropList) {
		this.indpenBuildPropList = indpenBuildPropList;
	}
	
	public String getIndenBuildPropId() {
		return indenBuildPropId;
	}
	public void setIndenBuildPropId(String indenBuildPropId) {
		this.indenBuildPropId = indenBuildPropId;
	}
	public String getIndenBuildPropName() {
		return indenBuildPropName;
	}
	public void setIndenBuildPropName(String indenBuildPropName) {
		this.indenBuildPropName = indenBuildPropName;
	}
	private String multiCommPropId;
	private String multiCommPropName;
	private String nearRoad;
	
	public String getNearRoad() {
		return nearRoad;
	}
	public void setNearRoad(String nearRoad) {
		this.nearRoad = nearRoad;
	}
	public ArrayList<PropertyValuationDTO> getMultiCommPropList() {
		return multiCommPropList;
	}
	public void setMultiCommPropList(
			ArrayList<PropertyValuationDTO> multiCommPropList) {
		this.multiCommPropList = multiCommPropList;
	}
	public String getMultiCommPropId() {
		return multiCommPropId;
	}
	public void setMultiCommPropId(String multiCommPropId) {
		this.multiCommPropId = multiCommPropId;
	}
	public String getMultiCommPropName() {
		return multiCommPropName;
	}
	public void setMultiCommPropName(String multiCommPropName) {
		this.multiCommPropName = multiCommPropName;
	}
// addition end	
	
	//--added by Satbir Kumar for AgriLand--
	
	private ArrayList<PropertyValuationDTO> agriLandTypeList= new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> agriTreeTypeList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> agriSubTreeTypeGrt45List=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> agriSubTreeTypeLes45List=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> agriSubClauseList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> agriTotalPropertyList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<PropertyValuationDTO> agriRefrncIdFinalMVList=new ArrayList<PropertyValuationDTO>();
	
	private String agriLandTypeId;
	private String agriLandTypeName;
	private String family;
	private String noOfBuyers;
	private String areMoreBuyers;
	private String isConstruction;
	private String isAgriTncpEducational;
	private String isAgriTncpHealth;
	private String fromAgri;
	public String getFromAgri() {
		return fromAgri;
	}
	public void setFromAgri(String fromAgri) {
		this.fromAgri = fromAgri;
	}
	private String isPropDiffPustika;
	private String agriTreeTypeId;
	private String agriTreeTypeName;
	private String agriSubTreeTypeId;
	private String agriSubTreeTypeName;
	private String agriTotalArea;
	private String agriTotalUnDivertedArea;
	private String agriUndivSingleCropArea;
	private String agriUndivDoubleCropArea;
	private String agriUndivIrrigatedArea;
	private String agriConstructFlag;
	private String undivTreeFlag;
	private String divTreeFlag;
	private String bothTreeFlag;
	private String greaterCircum45;
	private String lessCircum45;
	private String sagunTree;
	private String saalTree;
	private String fruitTree;
	private String less45Tree;
	private String noOfSubTreeTypeGrt45;
	private String noOfSubTreeTypeLes45;
	private String areThereTrees;
	private String agriTotalDivertedArea;
	private String agriTotalDivertedResidentialArea;
	private String agriTotalDivertedCommercialArea;
	private String agriTotalDivertedIndustrialArea;
	private String agriTotalDivertedEducationalArea;
	private String agriTotalDivertedHealthArea;
	private String agriTotalDivertedOthersArea;
	private int    agriNoOfBuyersCheck=1;
	private int    agriAddPropButtonCheck=0;
	private String agriSamePustikaBuyerCheck;
	private String agriDiffPustikaBuyerCheck;
	private String agriShowBuyerCheck;
	private String agriMainValTxnId;
	private String agriValTxnId;
	private String agriUndivertedValue;
	private String agriDivertedValue;
	private String agriWardPatwariId;
	private String agriColonyid;
	private String agriSubAreaWardMapId;
	private String agriColonyWardMapId;
	private String agriApplicableSubclauseId;
	private String agriTotalTreeValue;
	private String agriSubClauseId;
	private String agriSubClauseName;
	private String agriSelectedSubclauseId;
	private double agriIrrigatedRate;
	private String agriPageName;
	private String agriBuildingConstructTxnId;
	private String agriBuildingConstructCost;
	private String agriBuildingConstructArea;
	private String agriDiscloseShare;
	private String agriIndivPropertyValue;
	private String agriBuildingPlotValue;
	private String agriBuildingIndivConstValue;
	private String agriNoConstruction;
	private String agriRefrncId;
	private String agriIndivFinalMV;
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getApplicableSubClause() {
		return applicableSubClause;
	}
	public void setApplicableSubClause(String applicableSubClause) {
		this.applicableSubClause = applicableSubClause;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getSubAreaName() {
		return subAreaName;
	}
	public void setSubAreaName(String subAreaName) {
		this.subAreaName = subAreaName;
	}
	public String getSubAreaId() {
		return subAreaId;
	}
	public void setSubAreaId(String subAreaId) {
		this.subAreaId = subAreaId;
	}
	public String getWardPatwariName() {
		return wardPatwariName;
	}
	public void setWardPatwariName(String wardPatwariName) {
		this.wardPatwariName = wardPatwariName;
	}
	public String getWardPatwariId() {
		return wardPatwariId;
	}
	public void setWardPatwariId(String wardPatwariId) {
		this.wardPatwariId = wardPatwariId;
	}
	public String getColonyName() {
		return colonyName;
	}
	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}
	public String getColonyId() {
		return colonyId;
	}
	public void setColonyId(String colonyId) {
		this.colonyId = colonyId;
	}
	public ArrayList<PropertyValuationDTO> getTehsilList() {
		return tehsilList;
	}
	public void setTehsilList(ArrayList<PropertyValuationDTO> tehsilList) {
		this.tehsilList = tehsilList;
	}
	public ArrayList<PropertyValuationDTO> getAreaList() {
		return areaList;
	}
	public void setAreaList(ArrayList<PropertyValuationDTO> areaList) {
		this.areaList = areaList;
	}
	public ArrayList<PropertyValuationDTO> getSubAreaList() {
		return subAreaList;
	}
	public void setSubAreaList(ArrayList<PropertyValuationDTO> subAreaList) {
		this.subAreaList = subAreaList;
	}
	public ArrayList<PropertyValuationDTO> getWardPatwariList() {
		return wardPatwariList;
	}
	public void setWardPatwariList(ArrayList<PropertyValuationDTO> wardPatwariList) {
		this.wardPatwariList = wardPatwariList;
	}
	public ArrayList<PropertyValuationDTO> getColonyList() {
		return colonyList;
	}
	public void setColonyList(ArrayList<PropertyValuationDTO> colonyList) {
		this.colonyList = colonyList;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setAgriTotalArea(String agriTotalArea) {
		this.agriTotalArea = agriTotalArea;
	}
	public String getAgriTotalArea() {
		return agriTotalArea;
	}
	public void setAgriConstructFlag(String agriConstructFlag) {
		this.agriConstructFlag = agriConstructFlag;
	}
	public String getAgriConstructFlag() {
		return agriConstructFlag;
	}
	public void setUndivTreeFlag(String undivTreeFlag) {
		this.undivTreeFlag = undivTreeFlag;
	}
	public String getUndivTreeFlag() {
		return undivTreeFlag;
	}
	public void setDivTreeFlag(String divTreeFlag) {
		this.divTreeFlag = divTreeFlag;
	}
	public String getDivTreeFlag() {
		return divTreeFlag;
	}
	public void setBothTreeFlag(String bothTreeFlag) {
		this.bothTreeFlag = bothTreeFlag;
	}
	public String getBothTreeFlag() {
		return bothTreeFlag;
	}
	public void setGreaterCircum45(String greaterCircum45) {
		this.greaterCircum45 = greaterCircum45;
	}
	public String getGreaterCircum45() {
		return greaterCircum45;
	}
	public void setLessCircum45(String lessCircum45) {
		this.lessCircum45 = lessCircum45;
	}
	public String getLessCircum45() {
		return lessCircum45;
	}
	public void setSagunTree(String sagunTree) {
		this.sagunTree = sagunTree;
	}
	public String getSagunTree() {
		return sagunTree;
	}
	public void setSaalTree(String saalTree) {
		this.saalTree = saalTree;
	}
	public String getSaalTree() {
		return saalTree;
	}
	public void setFruitTree(String fruitTree) {
		this.fruitTree = fruitTree;
	}
	public String getFruitTree() {
		return fruitTree;
	}
	//added by Shreeraj
	private String plotTotArea;
	private String plotResiArea;
	private String plotCommArea;
	private String plotIndusArea;
	private String plotSchoolArea;
	private String plotHealthArea;
	private String plotOtherArea;
	private String plotId;
	private String plotEduTcp;
	private String plotHealthTcp;
	private String plotResicumComm;
	private String userId;
	private String colonyWardMappingId;
	private ArrayList plotSubClauseList; 
	private String plotSubClauseId;
	private String plotSubClauseName;
	private String plotSubId;
	private String plotMarketValue;
	private String plotValuationId;
	private String radioResiComm;
	
	
	public String getPlotResicumComm() {
		return plotResicumComm;
	}
	public void setPlotResicumComm(String plotResicumComm) {
		this.plotResicumComm = plotResicumComm;
	}
	public String getRadioResiComm() {
		return radioResiComm;
	}
	public void setRadioResiComm(String radioResiComm) {
		this.radioResiComm = radioResiComm;
	}
	public String getPlotValuationId() {
		return plotValuationId;
	}
	public void setPlotValuationId(String plotValuationId) {
		this.plotValuationId = plotValuationId;
	}
	public String getPlotMarketValue() {
		return plotMarketValue;
	}
	public void setPlotMarketValue(String plotMarketValue) {
		this.plotMarketValue = plotMarketValue;
	}
	public String getPlotSubId() {
		return plotSubId;
	}
	public void setPlotSubId(String plotSubId) {
		this.plotSubId = plotSubId;
	}
	public String getPlotSubClauseId() {
		return plotSubClauseId;
	}
	public void setPlotSubClauseId(String plotSubClauseId) {
		this.plotSubClauseId = plotSubClauseId;
	}
	public String getPlotSubClauseName() {
		return plotSubClauseName;
	}
	public void setPlotSubClauseName(String plotSubClauseName) {
		this.plotSubClauseName = plotSubClauseName;
	}
	public ArrayList getPlotSubClauseList() {
		return plotSubClauseList;
	}
	public void setPlotSubClauseList(ArrayList plotSubClauseList) {
		this.plotSubClauseList = plotSubClauseList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPlotEduTcp() {
		return plotEduTcp;
	}
	public void setPlotEduTcp(String plotEduTcp) {
		this.plotEduTcp = plotEduTcp;
	}
	public String getPlotHealthTcp() {
		return plotHealthTcp;
	}
	public void setPlotHealthTcp(String plotHealthTcp) {
		this.plotHealthTcp = plotHealthTcp;
	}
	public String getPlotId() {
		return plotId;
	}
	public void setPlotId(String plotId) {
		this.plotId = plotId;
	}
	public String getPlotTotArea() {
		return plotTotArea;
	}
	public void setPlotTotArea(String plotTotArea) {
		this.plotTotArea = plotTotArea;
	}
	public String getPlotResiArea() {
		return plotResiArea;
	}
	public void setPlotResiArea(String plotResiArea) {
		this.plotResiArea = plotResiArea;
	}
	public String getPlotCommArea() {
		return plotCommArea;
	}
	public void setPlotCommArea(String plotCommArea) {
		this.plotCommArea = plotCommArea;
	}
	public String getPlotIndusArea() {
		return plotIndusArea;
	}
	public void setPlotIndusArea(String plotIndusArea) {
		this.plotIndusArea = plotIndusArea;
	}
	public String getPlotSchoolArea() {
		return plotSchoolArea;
	}
	public void setPlotSchoolArea(String plotSchoolArea) {
		this.plotSchoolArea = plotSchoolArea;
	}
	public String getPlotHealthArea() {
		return plotHealthArea;
	}
	public void setPlotHealthArea(String plotHealthArea) {
		this.plotHealthArea = plotHealthArea;
	}
	public String getPlotOtherArea() {
		return plotOtherArea;
	}
	public void setPlotOtherArea(String plotOtherArea) {
		this.plotOtherArea = plotOtherArea;
	}
	public void setResiFloorAreaList(ArrayList<PropertyValuationDTO> resiFloorAreaList) {
		this.resiFloorAreaList = resiFloorAreaList;
	}
	public ArrayList<PropertyValuationDTO> getResiFloorAreaList() {
		return resiFloorAreaList;
	}
	public void setCommFloorAreaList(ArrayList<PropertyValuationDTO> commFloorAreaList) {
		this.commFloorAreaList = commFloorAreaList;
	}
	public ArrayList<PropertyValuationDTO> getCommFloorAreaList() {
		return commFloorAreaList;
	}
	public void setSchoolFloorAreaList(ArrayList<PropertyValuationDTO> schoolFloorAreaList) {
		this.schoolFloorAreaList = schoolFloorAreaList;
	}
	public ArrayList<PropertyValuationDTO> getSchoolFloorAreaList() {
		return schoolFloorAreaList;
	}
	public void setIndFloorAreaList(ArrayList<PropertyValuationDTO> indFloorAreaList) {
		this.indFloorAreaList = indFloorAreaList;
	}
	public ArrayList<PropertyValuationDTO> getIndFloorAreaList() {
		return indFloorAreaList;
	}
	public void setHealthFloorAreaList(ArrayList<PropertyValuationDTO> healthFloorAreaList) {
		this.healthFloorAreaList = healthFloorAreaList;
	}
	public ArrayList<PropertyValuationDTO> getHealthFloorAreaList() {
		return healthFloorAreaList;
	}
	public void setOtherFloorAreaList(ArrayList<PropertyValuationDTO> otherFloorAreaList) {
		this.otherFloorAreaList = otherFloorAreaList;
	}
	public ArrayList<PropertyValuationDTO> getOtherFloorAreaList() {
		return otherFloorAreaList;
	}
	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}
	public String getShopArea() {
		return shopArea;
	}
	public void setOfficeArea(String officeArea) {
		this.officeArea = officeArea;
	}
	public String getOfficeArea() {
		return officeArea;
	}
	public void setAddfloorcounter(int addfloorcounter) {
		this.addfloorcounter = addfloorcounter;
	}
	public int getAddfloorcounter() {
		return addfloorcounter;
	}
	public void setPlotCost(String plotCost) {
		this.plotCost = plotCost;
	}
	public String getPlotCost() {
		return plotCost;
	}
	public void setConstructionCost(String constructionCost) {
		this.constructionCost = constructionCost;
	}
	public String getConstructionCost() {
		return constructionCost;
	}

	public void setAgriLandTypeId(String agriLandTypeId) {
		this.agriLandTypeId = agriLandTypeId;
	}
	public String getAgriLandTypeId() {
		return agriLandTypeId;
	}
	public void setAgriLandTypeName(String agriLandTypeName) {
		this.agriLandTypeName = agriLandTypeName;
	}
	public String getAgriLandTypeName() {
		return agriLandTypeName;
	}
	public void setAgriLandTypeList(ArrayList<PropertyValuationDTO> agriLandTypeList) {
		this.agriLandTypeList = agriLandTypeList;
	}
	public ArrayList<PropertyValuationDTO> getAgriLandTypeList() {
		return agriLandTypeList;
	}
	public void setAreMoreBuyers(String areMoreBuyers) {
		this.areMoreBuyers = areMoreBuyers;
	}
	public String getAreMoreBuyers() {
		return areMoreBuyers;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getFamily() {
		return family;
	}
	public void setNoOfBuyers(String noOfBuyers) {
		this.noOfBuyers = noOfBuyers;
	}
	public String getNoOfBuyers() {
		return noOfBuyers;
	}
	public void setIsConstruction(String isConstruction) {
		this.isConstruction = isConstruction;
	}
	public String getIsConstruction() {
		return isConstruction;
	}
	public void setIsPropDiffPustika(String isPropDiffPustika) {
		this.isPropDiffPustika = isPropDiffPustika;
	}
	public String getIsPropDiffPustika() {
		return isPropDiffPustika;
	}
	public void setAgriTreeTypeList(ArrayList<PropertyValuationDTO> agriTreeTypeList) {
		this.agriTreeTypeList = agriTreeTypeList;
	}
	public ArrayList<PropertyValuationDTO> getAgriTreeTypeList() {
		return agriTreeTypeList;
	}
	public void setAgriTreeTypeId(String agriTreeTypeId) {
		this.agriTreeTypeId = agriTreeTypeId;
	}
	public String getAgriTreeTypeId() {
		return agriTreeTypeId;
	}
	public void setAgriTreeTypeName(String agriTreeTypeName) {
		this.agriTreeTypeName = agriTreeTypeName;
	}
	public String getAgriTreeTypeName() {
		return agriTreeTypeName;
	}
	public void setAgriSubTreeTypeId(String agriSubTreeTypeId) {
		this.agriSubTreeTypeId = agriSubTreeTypeId;
	}
	public String getAgriSubTreeTypeId() {
		return agriSubTreeTypeId;
	}
	public void setAgriSubTreeTypeName(String agriSubTreeTypeName) {
		this.agriSubTreeTypeName = agriSubTreeTypeName;
	}
	public String getAgriSubTreeTypeName() {
		return agriSubTreeTypeName;
	}
	public void setAgriSubTreeTypeGrt45List(ArrayList<PropertyValuationDTO> agriSubTreeTypeGrt45List) {
		this.agriSubTreeTypeGrt45List = agriSubTreeTypeGrt45List;
	}
	public ArrayList<PropertyValuationDTO> getAgriSubTreeTypeGrt45List() {
		return agriSubTreeTypeGrt45List;
	}
	public void setAgriSubTreeTypeLes45List(ArrayList<PropertyValuationDTO> agriSubTreeTypeLes45List) {
		this.agriSubTreeTypeLes45List = agriSubTreeTypeLes45List;
	}
	public ArrayList<PropertyValuationDTO> getAgriSubTreeTypeLes45List() {
		return agriSubTreeTypeLes45List;

	}
	public void setNoOfSubTreeTypeGrt45(String noOfSubTreeTypeGrt45) {
		this.noOfSubTreeTypeGrt45 = noOfSubTreeTypeGrt45;
	}
	public String getNoOfSubTreeTypeGrt45() {
		return noOfSubTreeTypeGrt45;
	}
	public void setNoOfSubTreeTypeLes45(String noOfSubTreeTypeLes45) {
		this.noOfSubTreeTypeLes45 = noOfSubTreeTypeLes45;
	}
	public String getNoOfSubTreeTypeLes45() {
		return noOfSubTreeTypeLes45;
	}

	
	public void setColonyWardMappingId(String colonyWardMappingId) {
		this.colonyWardMappingId = colonyWardMappingId;
	}
	public String getColonyWardMappingId() {
		return colonyWardMappingId;
	}
	
	public void setSubclauseList(ArrayList<PropertyValuationDTO> subclauseList) {
		this.subclauseList = subclauseList;
	}
	public ArrayList<PropertyValuationDTO> getSubclauseList() {
		return subclauseList;
	}
	public void setSubclauseId(String subclauseId) {
		this.subclauseId = subclauseId;
	}
	public String getSubclauseId() {
		return subclauseId;
	}
	public void setSubclauseName(String subclauseName) {
		this.subclauseName = subclauseName;
	}
	public String getSubclauseName() {
		return subclauseName;
	}	


	public void setAreThereTrees(String areThereTrees) {
		this.areThereTrees = areThereTrees;
	}
	public String getAreThereTrees() {
		return areThereTrees;
	}
	public void setAgriTotalDivertedArea(String agriTotalDivertedArea) {
		this.agriTotalDivertedArea = agriTotalDivertedArea;
	}
	public String getAgriTotalDivertedArea() {
		return agriTotalDivertedArea;
	}
	public void setAgriTotalUnDivertedArea(String agriTotalUnDivertedArea) {
		this.agriTotalUnDivertedArea = agriTotalUnDivertedArea;
	}
	public String getAgriTotalUnDivertedArea() {
		return agriTotalUnDivertedArea;
	}
	public void setAgriTotalDivertedResidentialArea(
			String agriTotalDivertedResidentialArea) {
		this.agriTotalDivertedResidentialArea = agriTotalDivertedResidentialArea;
	}
	public String getAgriTotalDivertedResidentialArea() {
		return agriTotalDivertedResidentialArea;
	}
	public void setAgriTotalDivertedCommercialArea(
			String agriTotalDivertedCommercialArea) {
		this.agriTotalDivertedCommercialArea = agriTotalDivertedCommercialArea;
	}
	public String getAgriTotalDivertedCommercialArea() {
		return agriTotalDivertedCommercialArea;
	}
	public void setAgriTotalDivertedIndustrialArea(
			String agriTotalDivertedIndustrialArea) {
		this.agriTotalDivertedIndustrialArea = agriTotalDivertedIndustrialArea;
	}
	public String getAgriTotalDivertedIndustrialArea() {
		return agriTotalDivertedIndustrialArea;
	}
	public void setAgriTotalDivertedEducationalArea(
			String agriTotalDivertedEducationalArea) {
		this.agriTotalDivertedEducationalArea = agriTotalDivertedEducationalArea;
	}
	public String getAgriTotalDivertedEducationalArea() {
		return agriTotalDivertedEducationalArea;
	}
	public void setAgriTotalDivertedHealthArea(
			String agriTotalDivertedHealthArea) {
		this.agriTotalDivertedHealthArea = agriTotalDivertedHealthArea;
	}
	public String getAgriTotalDivertedHealthArea() {
		return agriTotalDivertedHealthArea;
	}
	public void setAgriTotalDivertedOthersArea(
			String agriTotalDivertedOthersArea) {
		this.agriTotalDivertedOthersArea = agriTotalDivertedOthersArea;
	}
	public String getAgriTotalDivertedOthersArea() {
		return agriTotalDivertedOthersArea;
	}
	public void setIsAgriTncpHealth(String isAgriTncpHealth) {
		this.isAgriTncpHealth = isAgriTncpHealth;
	}
	public String getIsAgriTncpHealth() {
		return isAgriTncpHealth;
	}
	public void setIsAgriTncpEducational(String isAgriTncpEducational) {
		this.isAgriTncpEducational = isAgriTncpEducational;
	}
	public String getIsAgriTncpEducational() {
		return isAgriTncpEducational;
	}
	public void setAgriUndivSingleCropArea(String agriUndivSingleCropArea) {
		this.agriUndivSingleCropArea = agriUndivSingleCropArea;
	}
	public String getAgriUndivSingleCropArea() {
		return agriUndivSingleCropArea;
	}
	public void setAgriUndivDoubleCropArea(String agriUndivDoubleCropArea) {
		this.agriUndivDoubleCropArea = agriUndivDoubleCropArea;
	}
	public String getAgriUndivDoubleCropArea() {
		return agriUndivDoubleCropArea;
	}
	public void setAgriUndivIrrigatedArea(String agriUndivIrrigatedArea) {
		this.agriUndivIrrigatedArea = agriUndivIrrigatedArea;
	}
	public String getAgriUndivIrrigatedArea() {
		return agriUndivIrrigatedArea;
	}
	public void setAgriNoOfBuyersCheck(int agriNoOfBuyersCheck) {
		this.agriNoOfBuyersCheck = agriNoOfBuyersCheck;
	}
	public int getAgriNoOfBuyersCheck() {
		return agriNoOfBuyersCheck;
	}
	public void setAgriAddPropButtonCheck(int agriAddPropButtonCheck) {
		this.agriAddPropButtonCheck = agriAddPropButtonCheck;
	}
	public int getAgriAddPropButtonCheck() {
		return agriAddPropButtonCheck;
	}
	public void setAgriSamePustikaBuyerCheck(String agriSamePustikaBuyerCheck) {
		this.agriSamePustikaBuyerCheck = agriSamePustikaBuyerCheck;
	}
	public String getAgriSamePustikaBuyerCheck() {
		return agriSamePustikaBuyerCheck;
	}
	public void setAgriDiffPustikaBuyerCheck(String agriDiffPustikaBuyerCheck) {
		this.agriDiffPustikaBuyerCheck = agriDiffPustikaBuyerCheck;
	}
	public String getAgriDiffPustikaBuyerCheck() {
		return agriDiffPustikaBuyerCheck;
	}
	public void setAgriShowBuyerCheck(String agriShowBuyerCheck) {
		this.agriShowBuyerCheck = agriShowBuyerCheck;
	}
	public String getAgriShowBuyerCheck() {
		return agriShowBuyerCheck;
	}
	public void setLess45Tree(String less45Tree) {
		this.less45Tree = less45Tree;
	}
	public String getLess45Tree() {
		return less45Tree;
	}
	public void setAgriMainValTxnId(String agriMainValTxnId) {
		this.agriMainValTxnId = agriMainValTxnId;
	}
	public String getAgriMainValTxnId() {
		return agriMainValTxnId;
	}
	public void setAgriValTxnId(String agriValTxnId) {
		this.agriValTxnId = agriValTxnId;
	}
	public String getAgriValTxnId() {
		return agriValTxnId;
	}
	public void setAgriUndivertedValue(String agriUndivertedValue) {
		this.agriUndivertedValue = agriUndivertedValue;
	}
	public String getAgriUndivertedValue() {
		return agriUndivertedValue;
	}
	public void setAgriDivertedValue(String agriDivertedValue) {
		this.agriDivertedValue = agriDivertedValue;
	}
	public String getAgriDivertedValue() {
		return agriDivertedValue;
	}
	public void setAgriWardPatwariId(String agriWardPatwariId) {
		this.agriWardPatwariId = agriWardPatwariId;
	}
	public String getAgriWardPatwariId() {
		return agriWardPatwariId;
	}
	public void setAgriColonyid(String agriColonyid) {
		this.agriColonyid = agriColonyid;
	}
	public String getAgriColonyid() {
		return agriColonyid;
	}
	public void setAgriSubAreaWardMapId(String agriSubAreaWardMapId) {
		this.agriSubAreaWardMapId = agriSubAreaWardMapId;
	}
	public String getAgriSubAreaWardMapId() {
		return agriSubAreaWardMapId;
	}
	public void setAgriColonyWardMapId(String agriColonyWardMapId) {
		this.agriColonyWardMapId = agriColonyWardMapId;
	}
	public String getAgriColonyWardMapId() {
		return agriColonyWardMapId;
	}
	public void setAgriApplicableSubclauseId(String agriApplicableSubclauseId) {
		this.agriApplicableSubclauseId = agriApplicableSubclauseId;
	}
	public String getAgriApplicableSubclauseId() {
		return agriApplicableSubclauseId;
	}
	public void setAgriTotalTreeValue(String agriTotalTreeValue) {
		this.agriTotalTreeValue = agriTotalTreeValue;
	}
	public String getAgriTotalTreeValue() {
		return agriTotalTreeValue;
	}
	public void setAgriSubClauseList(ArrayList<PropertyValuationDTO> agriSubClauseList) {
		this.agriSubClauseList = agriSubClauseList;
	}
	public ArrayList<PropertyValuationDTO> getAgriSubClauseList() {
		return agriSubClauseList;
	}
	public void setAgriSubClauseId(String agriSubClauseId) {
		this.agriSubClauseId = agriSubClauseId;
	}
	public String getAgriSubClauseId() {
		return agriSubClauseId;
	}
	public void setAgriSubClauseName(String agriSubClauseName) {
		this.agriSubClauseName = agriSubClauseName;
	}
	public String getAgriSubClauseName() {
		return agriSubClauseName;
	}
	public void setAgriSelectedSubclauseId(String agriSelectedSubclauseId) {
		this.agriSelectedSubclauseId = agriSelectedSubclauseId;
	}
	public String getAgriSelectedSubclauseId() {
		return agriSelectedSubclauseId;
	}
	public void setAgriIrrigatedRate(double agriIrrigatedRate) {
		this.agriIrrigatedRate = agriIrrigatedRate;
	}
	public double getAgriIrrigatedRate() {
		return agriIrrigatedRate;
	}
	public void setAgriPageName(String agriPageName) {
		this.agriPageName = agriPageName;
	}
	public String getAgriPageName() {
		return agriPageName;
	}
	

	///ADDED BY SIMRAN
	
	private String buildingSubId;


	public String getBuildingSubId() {
		return buildingSubId;
	}
	public void setBuildingSubId(String buildingSubId) {
		this.buildingSubId = buildingSubId;
	}
	public void setConstructedAreaBuilding(String constructedAreaBuilding) {
		this.constructedAreaBuilding = constructedAreaBuilding;
	}
	public String getConstructedAreaBuilding() {
		return constructedAreaBuilding;
	}
	public void setConstructedValueBuilding(String constructedValueBuilding) {
		this.constructedValueBuilding = constructedValueBuilding;
	}
	public String getConstructedValueBuilding() {
		return constructedValueBuilding;
	}
	public void setConstructedBuildingType(String constructedBuildingType) {
		this.constructedBuildingType = constructedBuildingType;
	}
	public String getConstructedBuildingType() {
		return constructedBuildingType;
	}
	public void setBuildingTxnId(String buildingTxnId) {
		this.buildingTxnId = buildingTxnId;
	}
	public String getBuildingTxnId() {
		return buildingTxnId;
	}

	public void setAgriBuildingConstructTxnId(String agriBuildingConstructTxnId) {
		this.agriBuildingConstructTxnId = agriBuildingConstructTxnId;
	}
	public String getAgriBuildingConstructTxnId() {
		return agriBuildingConstructTxnId;
	}
	public void setAgriBuildingConstructCost(String agriBuildingConstructCost) {
		this.agriBuildingConstructCost = agriBuildingConstructCost;
	}
	public String getAgriBuildingConstructCost() {
		return agriBuildingConstructCost;
	}
	public void setAgriBuildingConstructArea(String agriBuildingConstructArea) {
		this.agriBuildingConstructArea = agriBuildingConstructArea;
	}
	public String getAgriBuildingConstructArea() {
		return agriBuildingConstructArea;
	}

	public void setBuildingTxnIdAgri(String buildingTxnIdAgri) {
		this.buildingTxnIdAgri = buildingTxnIdAgri;
	}
	public String getBuildingTxnIdAgri() {
		return buildingTxnIdAgri;
	}
	public void setRadioButton1(String radioButton1) {
		this.radioButton1 = radioButton1;
	}
	public String getRadioButton1() {
		return radioButton1;
	}
	public void setDisableEntry(String disableEntry) {
		this.disableEntry = disableEntry;
	}
	public String getDisableEntry() {
		return disableEntry;
	}
	public void setIsTncpSchoolCheck(String isTncpSchoolCheck) {
		this.isTncpSchoolCheck = isTncpSchoolCheck;
	}
	public String getIsTncpSchoolCheck() {
		return isTncpSchoolCheck;
	}
	public void setIsTncpHealthCheck(String isTncpHealthCheck) {
		this.isTncpHealthCheck = isTncpHealthCheck;
	}
	public String getIsTncpHealthCheck() {
		return isTncpHealthCheck;
	}
	public void setIsLiftCheck(String isLiftCheck) {
		this.isLiftCheck = isLiftCheck;
	}
	public String getIsLiftCheck() {
		return isLiftCheck;
	}
	public void setNearRoadCheck(String nearRoadCheck) {
		this.nearRoadCheck = nearRoadCheck;
	}
	public String getNearRoadCheck() {
		return nearRoadCheck;
	}
	public void setFloorFreezeId(String floorFreezeId) {
		this.floorFreezeId = floorFreezeId;
	}
	public String getFloorFreezeId() {
		return floorFreezeId;
	}
	public void setFloorFreezeFlag(String floorFreezeFlag) {
		this.floorFreezeFlag = floorFreezeFlag;
	}
	public String getFloorFreezeFlag() {
		return floorFreezeFlag;
	}
	public void setAgriDiscloseShare(String agriDiscloseShare) {
		this.agriDiscloseShare = agriDiscloseShare;
	}
	public String getAgriDiscloseShare() {
		return agriDiscloseShare;
	}
	public void setFloorTypeListBuilding(ArrayList<PropertyValuationDTO> floorTypeListBuilding) {
		this.floorTypeListBuilding = floorTypeListBuilding;
	}
	public ArrayList<PropertyValuationDTO> getFloorTypeListBuilding() {
		return floorTypeListBuilding;
	}
	public void setAgriTotalPropertyList(ArrayList<PropertyValuationDTO> agriTotalPropertyList) {
		this.agriTotalPropertyList = agriTotalPropertyList;
	}
	public ArrayList<PropertyValuationDTO> getAgriTotalPropertyList() {
		return agriTotalPropertyList;
	}
	public void setAgriIndivPropertyValue(String agriIndivPropertyValue) {
		this.agriIndivPropertyValue = agriIndivPropertyValue;
	}
	public String getAgriIndivPropertyValue() {
		return agriIndivPropertyValue;
	}

	public void setAgriBuildingPlotValue(String agriBuildingPlotValue) {
		this.agriBuildingPlotValue = agriBuildingPlotValue;
	}
	public String getAgriBuildingPlotValue() {
		return agriBuildingPlotValue;
	}
	public void setAgriBuildingIndivConstValue(
			String agriBuildingIndivConstValue) {
		this.agriBuildingIndivConstValue = agriBuildingIndivConstValue;
	}
	public String getAgriBuildingIndivConstValue() {
		return agriBuildingIndivConstValue;
	}
	public void setAgriNoConstruction(String agriNoConstruction) {
		this.agriNoConstruction = agriNoConstruction;
	}
	public String getAgriNoConstruction() {
		return agriNoConstruction;
	}

	public void setOnlyIndustrial(String onlyIndustrial) {
		this.onlyIndustrial = onlyIndustrial;
	}
	public String getOnlyIndustrial() {
		return onlyIndustrial;
	}
	public void setIsAkvnFlag(String isAkvnFlag) {
		this.isAkvnFlag = isAkvnFlag;
	}
	public String getIsAkvnFlag() {
		return isAkvnFlag;
	}
	public void setIsSuperConstructionFlag(String isSuperConstructionFlag) {
		this.isSuperConstructionFlag = isSuperConstructionFlag;
	}
	public String getIsSuperConstructionFlag() {
		return isSuperConstructionFlag;
	}
	public String getIndAreaZero() {
		return indAreaZero;
	}
	public void setIndAreaZero(String indAreaZero) {
		this.indAreaZero = indAreaZero;
	}
	public void setResiCommL2List(ArrayList<PropertyValuationDTO> resiCommL2List) {
		this.resiCommL2List = resiCommL2List;
	}
	public ArrayList<PropertyValuationDTO> getResiCommL2List() {
		return resiCommL2List;
	}
	/**
	 * @param resiCommL2Area the resiCommL2Area to set
	 */
	public void setResiCommL2Area(String resiCommL2Area) {
		this.resiCommL2Area = resiCommL2Area;
	}
	/**
	 * @return the resiCommL2Area
	 */
	public String getResiCommL2Area() {
		return resiCommL2Area;
	}
	/**
	 * @param resiCommArea the resiCommArea to set
	 */
	public void setResiCommArea(String resiCommArea) {
		this.resiCommArea = resiCommArea;
	}
	/**
	 * @return the resiCommArea
	 */
	public String getResiCommArea() {
		return resiCommArea;
	}
	/**
	 * @param resiCommFloorAreaList the resiCommFloorAreaList to set
	 */
	public void setResiCommFloorAreaList(ArrayList<PropertyValuationDTO> resiCommFloorAreaList) {
		this.resiCommFloorAreaList = resiCommFloorAreaList;
	}
	/**
	 * @return the resiCommFloorAreaList
	 */
	public ArrayList<PropertyValuationDTO> getResiCommFloorAreaList() {
		return resiCommFloorAreaList;
	}
	public void setHousingFlag(String housingFlag) {
		this.housingFlag = housingFlag;
	}
	public String getHousingFlag() {
		return housingFlag;
	}
	public void setHousingCheck(String housingCheck) {
		this.housingCheck = housingCheck;
	}
	public String getHousingCheck() {
		return housingCheck;
	}
	public void setHousingCheckFlag(String housingCheckFlag) {
		this.housingCheckFlag = housingCheckFlag;
	}
	public String getHousingCheckFlag() {
		return housingCheckFlag;
	}
	public void setAgriRefrncIdFinalMVList(ArrayList<PropertyValuationDTO> agriRefrncIdFinalMVList) {
		this.agriRefrncIdFinalMVList = agriRefrncIdFinalMVList;
	}
	public ArrayList<PropertyValuationDTO> getAgriRefrncIdFinalMVList() {
		return agriRefrncIdFinalMVList;
	}
	public void setAgriIndivFinalMV(String agriIndivFinalMV) {
		this.agriIndivFinalMV = agriIndivFinalMV;
	}
	public String getAgriIndivFinalMV() {
		return agriIndivFinalMV;
	}
	public void setAgriRefrncId(String agriRefrncId) {
		this.agriRefrncId = agriRefrncId;
	}
	public String getAgriRefrncId() {
		return agriRefrncId;
	}
	public String municipalFlag;
	public String municipalCheck;
	public String municipalCheckFlag;

	public String getMunicipalCheckFlag() {
		return municipalCheckFlag;
	}
	public void setMunicipalCheckFlag(String municipalCheckFlag) {
		this.municipalCheckFlag = municipalCheckFlag;
	}
	public String municipalCheckDisplay;




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
	public String freezeBuyer;




	public String getFreezeBuyer() {
		return freezeBuyer;
	}
	public void setFreezeBuyer(String freezeBuyer) {
		this.freezeBuyer = freezeBuyer;
	}
	public void setDeedId(String deedId) {
		this.deedId = deedId;
	}
	public String getDeedId() {
		return deedId;
	}
	private String deedId;
	
	private String plotOnlyResi;
	private String buildOnlyResi;


	private String instId;

	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getPlotOnlyResi() {
		return plotOnlyResi;
	}
	public void setPlotOnlyResi(String plotOnlyResi) {
		this.plotOnlyResi = plotOnlyResi;
	}
	public String getBuildOnlyResi() {
		return buildOnlyResi;
	}
	public void setBuildOnlyResi(String buildOnlyResi) {
		this.buildOnlyResi = buildOnlyResi;
	}
	
	
	// added by akansha for CLR khasra Details
	
    private String clrFlag;
	
	private String censusCode;
	private String khasraNoClr;
	
	private String khasraTotalArea;
	
	private String landIrriOrNot;
	private String landUnIrriOrNot;
	
	private String landUnirriDouble;
	private String padtiArea;
	
	private String padtiDuration;
	
	private String ownership;
	
	private String nohiyat;
	
	private String currentStatus;
	
	private String kehfiyat;
	
	private String clrKhasraId;
	
	
	public void setClrFlag(String clrFlag) {
		this.clrFlag = clrFlag;
	}
	public String getClrFlag() {
		return clrFlag;
	}
	
	
	public void setCensusCode(String censusCode) {
		this.censusCode = censusCode;
	}
	
	@XmlElement(name = "bhucode")
	public String getCensusCode() {
		return censusCode;
	}
	public void setKhasraNoClr(String khasraNoClr) {
		this.khasraNoClr = khasraNoClr;
				/*try {
			   this.khasraNoClr = new String(khasraNoClr.getBytes("ISO-8859-1"), "UTF-8");
			    }
			    catch (UnsupportedEncodingException ex) {
			    }*/
		
	}
	@XmlElement (name="khasraNo")
	public String getKhasraNoClr() {
		return khasraNoClr;
	}
	public void setKhasraTotalArea(String khasraTotalArea) {
		this.khasraTotalArea = khasraTotalArea;
	}
	
	@XmlElement (name="surveyArea")
	public String getKhasraTotalArea() {
		return khasraTotalArea;
	}
	public void setLandIrriOrNot(String landIrriOrNot) {
		this.landIrriOrNot = landIrriOrNot;
	}
	
	@XmlElement (name="isLandIrrigated")
	public String getLandIrriOrNot() {
		return landIrriOrNot;
	}
	public void setLandUnIrriOrNot(String landUnIrriOrNot) {
		this.landUnIrriOrNot = landUnIrriOrNot;
	}
	
	@XmlElement (name="isSingleCrop")
	public String getLandUnIrriOrNot() {
		return landUnIrriOrNot;
	}
	public void setLandUnirriDouble(String landUnirriDouble) {
		this.landUnirriDouble = landUnirriDouble;
	}
	@XmlElement (name="isDoubleCrop")
	public String getLandUnirriDouble() {
		return landUnirriDouble;
	}
	public void setPadtiArea(String padtiArea) {
		this.padtiArea = padtiArea;
	}
	public String getPadtiArea() {
		return padtiArea;
	}
	public void setPadtiDuration(String padtiDuration) {
		this.padtiDuration = padtiDuration;
	}
	public String getPadtiDuration() {
		return padtiDuration;
	}
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}
	@XmlElement (name="landOwnershipType")
	public String getOwnership() {
		return ownership;
	}
	public void setNohiyat(String nohiyat) {
		this.nohiyat = nohiyat;
	}
	public String getNohiyat() {
		return nohiyat;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setKehfiyat(String kehfiyat) {
		this.kehfiyat = kehfiyat;
	}
	
	@XmlElement (name="remarks")
	public String getKehfiyat() {
		return kehfiyat;
	}
	public void setClrKhasraId(String clrKhasraId) {
		this.clrKhasraId = clrKhasraId;
	}
	
	@XmlElement (name="khasraId")
	public String getClrKhasraId() {
		return clrKhasraId;
	}
	
	
	 public String getTermsCondPartyApp() {
		return termsCondPartyApp;
	}
	public void setTermsCondPartyApp(String termsCondPartyApp) {
		this.termsCondPartyApp = termsCondPartyApp;
	}

	public void setRinPustikaNoClr(String rinPustikaNoClr) {
		//this.rinPustikaNoClr = rinPustikaNoClr;
		
		this.rinPustikaNoClr = rinPustikaNoClr;
		/*try {
	   this.rinPustikaNoClr = new String(rinPustikaNoClr.getBytes("ISO-8859-1"), "UTF-8");
	    }
	    catch (UnsupportedEncodingException ex) {
	    }*/
	}
	public String getRinPustikaNoClr() {
		return rinPustikaNoClr;
	}

	private String termsCondPartyApp;
	
	//Added by Rakesh for multiple clr Sampda khasra 
	
	private double totAgriTotalUnDivertedArea;
	private double totAgriUndivSingleCropArea;
	private double totAgriUndivDoubleCropArea;
	private double totAgriUndivIrrigatedArea;
	private double TotalSaleableAreaPerKhasraClr;
    

	private double clrTotAgriUnirrigated ;
	private double clrTotAgriSingleCropArea;
	private double clrTotAgriDoubleCropArea;
	private double clrTotAgriIrrigated;
    private double clrTotalKhasraArea;

	public double getTotAgriTotalUnDivertedArea() {
		return totAgriTotalUnDivertedArea;
	}
	public void setTotAgriTotalUnDivertedArea(double totAgriTotalUnDivertedArea) {
		this.totAgriTotalUnDivertedArea = totAgriTotalUnDivertedArea;
	}
	public double getTotAgriUndivSingleCropArea() {
		return totAgriUndivSingleCropArea;
	}
	public void setTotAgriUndivSingleCropArea(double totAgriUndivSingleCropArea) {
		this.totAgriUndivSingleCropArea = totAgriUndivSingleCropArea;
	}
	public double getTotAgriUndivDoubleCropArea() {
		return totAgriUndivDoubleCropArea;
	}
	public void setTotAgriUndivDoubleCropArea(double totAgriUndivDoubleCropArea) {
		this.totAgriUndivDoubleCropArea = totAgriUndivDoubleCropArea;
	}
	public double getTotAgriUndivIrrigatedArea() {
		return totAgriUndivIrrigatedArea;
	}
	public void setTotAgriUndivIrrigatedArea(double totAgriUndivIrrigatedArea) {
		this.totAgriUndivIrrigatedArea = totAgriUndivIrrigatedArea;
	}
	public void setTotalSaleableAreaPerKhasraClr(
			double totalSaleableAreaPerKhasraClr) {
		TotalSaleableAreaPerKhasraClr = totalSaleableAreaPerKhasraClr;
	}
	public double getTotalSaleableAreaPerKhasraClr() {
		return TotalSaleableAreaPerKhasraClr;
	}
	
	public double getClrTotAgriUnirrigated() {
		return clrTotAgriUnirrigated;
	}
	public void setClrTotAgriUnirrigated(double clrTotAgriUnirrigated) {
		this.clrTotAgriUnirrigated = clrTotAgriUnirrigated;
	}
	public double getClrTotAgriSingleCropArea() {
		return clrTotAgriSingleCropArea;
	}
	public void setClrTotAgriSingleCropArea(double clrTotAgriSingleCropArea) {
		this.clrTotAgriSingleCropArea = clrTotAgriSingleCropArea;
	}
	public double getClrTotAgriDoubleCropArea() {
		return clrTotAgriDoubleCropArea;
	}
	public void setClrTotAgriDoubleCropArea(double clrTotAgriDoubleCropArea) {
		this.clrTotAgriDoubleCropArea = clrTotAgriDoubleCropArea;
	}
	public double getClrTotAgriIrrigated() {
		return clrTotAgriIrrigated;
	}
	public void setClrTotAgriIrrigated(double clrTotAgriIrrigated) {
		this.clrTotAgriIrrigated = clrTotAgriIrrigated;
	}
	public void setClrTotalKhasraArea(double clrTotalKhasraArea) {
		this.clrTotalKhasraArea = clrTotalKhasraArea;
	}
	public double getClrTotalKhasraArea() {
		return clrTotalKhasraArea;
	}

	

	private double totDivertedIndustrialArea;
	private double totDivertedEducationalArea;
	private double totDivertedHealthArea;
	private double totDivertedOthersArea;
	private double totDivertedArea;
	private double totDivertedResidentialArea;
	
	private double totDivertedCommercialArea;
	public double getTotDivertedCommercialArea() {
		return totDivertedCommercialArea;
	}
	public void setTotDivertedCommercialArea(double totDivertedCommercialArea) {
		this.totDivertedCommercialArea = totDivertedCommercialArea;
	}
	public double getTotDivertedIndustrialArea() {
		return totDivertedIndustrialArea;
	}
	public void setTotDivertedIndustrialArea(double totDivertedIndustrialArea) {
		this.totDivertedIndustrialArea = totDivertedIndustrialArea;
	}
	public double getTotDivertedEducationalArea() {
		return totDivertedEducationalArea;
	}
	public void setTotDivertedEducationalArea(double totDivertedEducationalArea) {
		this.totDivertedEducationalArea = totDivertedEducationalArea;
	}
	public double getTotDivertedHealthArea() {
		return totDivertedHealthArea;
	}
	public void setTotDivertedHealthArea(double totDivertedHealthArea) {
		this.totDivertedHealthArea = totDivertedHealthArea;
	}
	public double getTotDivertedOthersArea() {
		return totDivertedOthersArea;
	}
	public void setTotDivertedOthersArea(double totDivertedOthersArea) {
		this.totDivertedOthersArea = totDivertedOthersArea;
	}
	public double getTotDivertedArea() {
		return totDivertedArea;
	}
	public void setTotDivertedArea(double totDivertedArea) {
		this.totDivertedArea = totDivertedArea;
	}
	public void setTotDivertedResidentialArea(double totDivertedResidentialArea) {
		this.totDivertedResidentialArea = totDivertedResidentialArea;
	}
	public double getTotDivertedResidentialArea() {
		return totDivertedResidentialArea;
	}
	
	public void setRinPustikaArrayClr(ArrayList rinPustikaArrayClr) {
		this.rinPustikaArrayClr = rinPustikaArrayClr;
	}
	public ArrayList getRinPustikaArrayClr() {
		return rinPustikaArrayClr;
	}

	public void setIsConstructionKhasraNo(String isConstructionKhasraNo) {
		this.isConstructionKhasraNo = isConstructionKhasraNo;
	}
	public String getIsConstructionKhasraNo() {
		return isConstructionKhasraNo;
	}

	private ArrayList rinPustikaArrayClr;
	private String isConstructionKhasraNo;


	public ArrayList<PropertyValuationDTO> getConstructionSlabList() {
		return constructionSlabList;
	}
	public void setConstructionSlabList(
	ArrayList<PropertyValuationDTO> constructionSlabList) {
		this.constructionSlabList = constructionSlabList;
	}


	//added by akansha for construction cost change
	private String isOnlyResidential;
	public void setIsOnlyResidential(String isOnlyResidential) {
		this.isOnlyResidential = isOnlyResidential;
	}
	public String getIsOnlyResidential() {
		return isOnlyResidential;
	}
	
	//developer agreement 
	private double builderShare;
	private double builderShareTotalArea;
	private String builderSharePropValue;
	private String isDeveloperAgreement;
	private String instIdDeveloperAgrmnt;
	
	
	public double getBuilderShareTotalArea() {
		return builderShareTotalArea;
	}
	public void setBuilderShareTotalArea(double builderShareTotalArea) {
		this.builderShareTotalArea = builderShareTotalArea;
	}
	public String getInstIdDeveloperAgrmnt() {
		return instIdDeveloperAgrmnt;
	}
	public void setInstIdDeveloperAgrmnt(String instIdDeveloperAgrmnt) {
		this.instIdDeveloperAgrmnt = instIdDeveloperAgrmnt;
	}
	public String getIsDeveloperAgreement() {
		return isDeveloperAgreement;
	}
	public void setIsDeveloperAgreement(String isDeveloperAgreement) {
		this.isDeveloperAgreement = isDeveloperAgreement;
	}
	public double getBuilderShare() {
		return builderShare;
	}
	public void setBuilderShare(double builderShare) {
		this.builderShare = builderShare;
	}
	public String getBuilderSharePropValue() {
		return builderSharePropValue;
	}
	public void setBuilderSharePropValue(String builderSharePropValue) {
		this.builderSharePropValue = builderSharePropValue;
	}
	public void setRinPustikaCheckBoxSelected(String rinPustikaCheckBoxSelected) {
		this.rinPustikaCheckBoxSelected = rinPustikaCheckBoxSelected;
	}
	public String getRinPustikaCheckBoxSelected() {
		return rinPustikaCheckBoxSelected;
	}
	//ADDED BY SAURAV FOR INDEPENEDY INDUSTRIAL CHANGES
	private String industrialConstArea;
	private String floorConstructedArea;
	public String getIndustrialConstArea() {
		return industrialConstArea;
	}
	public void setIndustrialConstArea(String industrialConstArea) {
		this.industrialConstArea = industrialConstArea;
	}
	public String getFloorConstructedArea() {
		return floorConstructedArea;
	}
	public void setFloorConstructedArea(String floorConstructedArea) {
		this.floorConstructedArea = floorConstructedArea;
	}
	
	
		public void setVillageKhasraList(ArrayList villageKhasraList) {
		this.villageKhasraList = villageKhasraList;
	}
	public ArrayList getVillageKhasraList() {
		return villageKhasraList;
	}
	public void setKhasra(String khasra) {
		this.khasra = khasra;
	}
	public String getKhasra() {
		return khasra;
	}
	//sdded by akansha on 12/12/21 for clr changes
	
	
	private String clrChangedFlag;
	private String lgdCode;
	
	public void setClrChangedFlag(String clrChangedFlag) {
		this.clrChangedFlag = clrChangedFlag;
	}
	
	public String getClrChangedFlag() {
		return clrChangedFlag;
	}
	public void setLgdCode(String lgdCode) {
		this.lgdCode = lgdCode;
	}
	public String getLgdCode() {
		return lgdCode;
	}
	public void setInstClrFlag(String instClrFlag) {
		this.instClrFlag = instClrFlag;
	}
	public String getInstClrFlag() {
		return instClrFlag;
	}
	private String khasraIdFromClr;

	public String getKhasraIdFromClr() {
		return khasraIdFromClr;
	}
	public void setKhasraIdFromClr(String khasraIdFromClr) {
		this.khasraIdFromClr = khasraIdFromClr;
	}
	private String landRevenue;

	public String getLandRevenue() {
		return landRevenue;
	}
	public void setLandRevenue(String landRevenue) {
		this.landRevenue = landRevenue;
	}

	private ArrayList khasraDblist = new ArrayList ();
	
	public void setKhasraDblist(ArrayList khasraDblist) {
		this.khasraDblist = khasraDblist;
	}
	public ArrayList getKhasraDblist() {
		return khasraDblist;
	}
	
	public void setKhasraSelectedId(String khasraSelectedId) {
		this.khasraSelectedId = khasraSelectedId;
	}
	public String getKhasraSelectedId() {
		return khasraSelectedId;
	}

	private String khasraSelectedId ;
	
	private ArrayList<ClrKhasraDetailsDTO> clrKhasraDetailsDTO = new ArrayList<ClrKhasraDetailsDTO>();
	
	public void setClrKhasraDetailsDTO(ArrayList<ClrKhasraDetailsDTO> clrKhasraDetailsDTO) {
		this.clrKhasraDetailsDTO = clrKhasraDetailsDTO;
	}
	public ArrayList<ClrKhasraDetailsDTO> getClrKhasraDetailsDTO() {
		return clrKhasraDetailsDTO;
	}
	public void setTransactionaArea(String transactionaArea) {
		this.transactionaArea = transactionaArea;
	}
	public String getTransactionaArea() {
		return transactionaArea;
	}
}
