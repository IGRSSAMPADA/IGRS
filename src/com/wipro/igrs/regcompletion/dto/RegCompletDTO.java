package com.wipro.igrs.regcompletion.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class RegCompletDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	private String adjustAppNo;
	private String poaNo;
	private String oldRegNo;
	private String formName;
	private String actionName;
	private String propertyTxnNo;
	private String loanAmt;
	private String pendingPropTax;
	private String propCharges;
	private String splitLocation;
	private String estampNo;
	private String selectedFloor;
	private String floorTxnId;
	private int floorCount;
	private String regNo;
	private String appNo;
	private String marketValue;
	private int addMoreCounter;
	// Added By Aruna
	private String registerID;
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
	private String usePlotId;
	
	private String lblUsage;
	private String typeID;
	private String typeName;
	private String hdnType;
	private String hdnCeilingName;
	private String typeFloorID;
	private String typeFloorName;
	private String typeFloorDesc;
	private String hdnTypeFloor;
	private boolean transactionBuilding;
	private String floorID;
	private String usageBuilding;
	private String deleteFloorID;
	private String hdnDeleteFloorID;
	private String municipalDutyName;
	private String municipalDuty;
	
	//private String propUsageTypeId;
	//private String propUsageTypeName;
	private String subclauseString;
	
	private String subclauseId;
	private String subclauseName;
    private String propertyTypeId;
    private String propertyTypeName;
    private String distId;
    
    private String distName;
    private String tehsilId;
    private String tehsilName;
    private String areaTypeId;
    private String areaTypeName;
    
    private String wardpatwarId;
    private String wardpatwarName;
    private String govmunciplId;
    private String govmunciplName;
    private String wardId;
    
    private String wardName;
    private String patwariId;
    private String patwariName;
    private String mohallaId;
    private String mohallaName;
    
    private String villageId;
    private String villageName;
    private String propertyUsageTypeId;
    private String propertyUsageTypeName;
    private String vikasId;
    private String propertyL2;
    private String ricircle;
    private String layoutdet;
    private String khasaraNum;
    private String nazoolstNum;
    private String rinpustikaNum;
    
    private String address;
    private String north;
    private String south;
    private String east;
    private String west;
    
    private String totalArea;
    private String considearAmt;
    private String constructedArea;
    private String commercialType;
    private String commercialTypeId;
    private String moreFloors;
    
    private String splitPartId;
    private String splitPartName;    
    private String totalFloors;
    private HashMap mapBuilding = new HashMap();
    private String floorSubClases;
    private String floorDetails;
    private String typeOfloor;
    private String floorNo;
    private String[] subClauses;
    private String floorDesc;
    private String floorName;
    private String ceilingType;
    private String ceilingTypeId;
    private String ceilinghidType;
    private boolean poaStatus;
    private boolean audjuStatus;
    private ArrayList propDetailsList= new ArrayList();
	private ArrayList propUsedList= new ArrayList();
	private ArrayList propertyList= new ArrayList();
    private String wardStatus = "";
    
	private ArrayList deedList= new ArrayList();
	private ArrayList instList= new ArrayList();
	private ArrayList exmpList= new ArrayList();	
	private ArrayList distList= new ArrayList();
	private ArrayList tehsilList= new ArrayList();
	
	private ArrayList areaList= new ArrayList();
	private ArrayList govrnList= new ArrayList();
	private ArrayList wardpatwariList= new ArrayList();
	private ArrayList wardList= new ArrayList();
	private ArrayList mohallList= new ArrayList();
	private HashMap floorValuesList= new HashMap();
	private ArrayList floorList= new ArrayList();
	private ArrayList propertyType2List= new ArrayList();	
	private ArrayList patwariList= new ArrayList();
	private ArrayList villageList= new ArrayList();
	private ArrayList propertyTypeList= new ArrayList();
	
	private ArrayList propertyDispList= new ArrayList();
	private ArrayList propertySubclauseList;//= new ArrayList();   
	private ArrayList splitPartList= new ArrayList();
	
	// Added By Aruna
	private ArrayList registerList= new ArrayList();
    
	
	public String getCeilingType() {
		return ceilingType;
	}
	public void setCeilingType(String ceilingType) {
		this.ceilingType = ceilingType;
	}
	public String getDistId() {
		return distId;
	}
	public void setDistId(String distId) {
		this.distId = distId;
	}
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
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
	public String getAreaTypeId() {
		return areaTypeId;
	}
	public void setAreaTypeId(String areaTypeId) {
		this.areaTypeId = areaTypeId;
	}
	public String getAreaTypeName() {
		return areaTypeName;
	}
	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}
	public String getWardpatwarId() {
		return wardpatwarId;
	}
	public void setWardpatwarId(String wardpatwarId) {
		this.wardpatwarId = wardpatwarId;
	}
	public String getWardpatwarName() {
		return wardpatwarName;
	}
	public void setWardpatwarName(String wardpatwarName) {
		this.wardpatwarName = wardpatwarName;
	}
	public String getGovmunciplId() {
		return govmunciplId;
	}
	public void setGovmunciplId(String govmunciplId) {
		this.govmunciplId = govmunciplId;
	}
	public String getGovmunciplName() {
		return govmunciplName;
	}
	public void setGovmunciplName(String govmunciplName) {
		this.govmunciplName = govmunciplName;
	}
	public String getWardId() {
		return wardId;
	}
	public void setWardId(String wardId) {
		this.wardId = wardId;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getPatwariName() {
		return patwariName;
	}
	public void setPatwariName(String patwariName) {
		this.patwariName = patwariName;
	}
	public String getPatwariId() {
		return patwariId;
	}
	public void setPatwariId(String patwariId) {
		this.patwariId = patwariId;
	}
	public String getVikasId() {
		return vikasId;
	}
	public void setVikasId(String vikasId) {
		this.vikasId = vikasId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name){
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
	 * @return the deedList
	 */
	public ArrayList getDeedList() {
		return deedList;
	}
	/**
	 * @param deedList the deedList to set
	 */
	public void setDeedList(ArrayList deedList) {
		this.deedList = deedList;
	}
	/**
	 * @return the instList
	 */
	public ArrayList getInstList() {
		return instList;
	}
	/**
	 * @param instList the instList to set
	 */
	public void setInstList(ArrayList instList) {
		this.instList = instList;
	}
	/**
	 * @return the exmpList
	 */
	public ArrayList getExmpList() {
		return exmpList;
	}
	/**
	 * @param exmpList the exmpList to set
	 */
	public void setExmpList(ArrayList exmpList) {
		this.exmpList = exmpList;
	}
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
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public ArrayList getDistList() {
		return distList;
	}
	public void setDistList(ArrayList distList) {
		this.distList = distList;
	}
	public ArrayList getTehsilList() {
		return tehsilList;
	}
	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}
	public ArrayList getAreaList() {
		return areaList;
	}
	public void setAreaList(ArrayList areaList) {
		this.areaList = areaList;
	}
	public ArrayList getGovrnList() {
		return govrnList;
	}
	public void setGovrnList(ArrayList govrnList) {
		this.govrnList = govrnList;
	}
	public ArrayList getWardpatwariList() {
		return wardpatwariList;
	}
	public void setWardpatwariList(ArrayList wardpatwariList) {
		this.wardpatwariList = wardpatwariList;
	}
	public ArrayList getWardList() {
		return wardList;
	}
	public void setWardList(ArrayList wardList) {
		this.wardList = wardList;
	}
	public ArrayList getMohallList() {
		return mohallList;
	}
	public void setMohallList(ArrayList mohallList) {
		this.mohallList = mohallList;
	}
	public ArrayList getPatwariList() {
		return patwariList;
	}
	public void setPatwariList(ArrayList patwariList) {
		this.patwariList = patwariList;
	}
	public ArrayList getVillageList() {
		return villageList;
	}
	public void setVillageList(ArrayList villageList) {
		this.villageList = villageList;
	}
	public ArrayList getPropertyTypeList() {
		return propertyTypeList;
	}
	public void setPropertyTypeList(ArrayList propertyTypeList) {
		this.propertyTypeList = propertyTypeList;
	}
	public String getPropertyTypeId() {
		return propertyTypeId;
	}
	public void setPropertyTypeId(String propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}
	public String getPropertyTypeName() {
		return propertyTypeName;
	}
	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}
	public ArrayList getPropertyDispList() {
		return propertyDispList;
	}
	public void setPropertyDispList(ArrayList propertyDispList) {
		this.propertyDispList = propertyDispList;
	}
	public String getTotalArea() {
		return totalArea;
	}
	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}
	public String getConsidearAmt() {
		return considearAmt;
	}
	public void setConsidearAmt(String considearAmt) {
		this.considearAmt = considearAmt;
	}
	public ArrayList getPropertySubclauseList() {
		return propertySubclauseList;
	}
	public void setPropertySubclauseList(ArrayList propertySubclauseList) {
		this.propertySubclauseList = propertySubclauseList;
	}
	public String getSubclauseId() {
		return subclauseId;
	}
	public void setSubclauseId(String subclauseId) {
		this.subclauseId = subclauseId;
	}
	public String getSubclauseName() {
		return subclauseName;
	}
	public void setSubclauseName(String subclauseName) {
		this.subclauseName = subclauseName;
	}
	public String getPropertyUsageTypeId() {
		return propertyUsageTypeId;
	}
	public void setPropertyUsageTypeId(String propertyUsageTypeId) {
		this.propertyUsageTypeId = propertyUsageTypeId;
	}
	public String getPropertyUsageTypeName() {
		return propertyUsageTypeName;
	}
	public void setPropertyUsageTypeName(String propertyUsageTypeName) {
		this.propertyUsageTypeName = propertyUsageTypeName;
	}
	public String getCommercialType() {
		return commercialType;
	}
	public void setCommercialType(String commercialType) {
		this.commercialType = commercialType;
	}
	public String getMoreFloors() {
		return moreFloors;
	}
	public void setMoreFloors(String moreFloors) {
		this.moreFloors = moreFloors;
	}
	public String getTotalFloors() {
		return totalFloors;
	}
	public void setTotalFloors(String totalFloors) {
		this.totalFloors = totalFloors;
	}
	public String getTypeOfloor() {
		return typeOfloor;
	}
	public void setTypeOfloor(String typeOfloor) {
		this.typeOfloor = typeOfloor;
	}
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	public String getConstructedArea() {
		return constructedArea;
	}
	public void setConstructedArea(String constructedArea) {
		this.constructedArea = constructedArea;
	}
	public String getAdjustAppNo() {
		return adjustAppNo;
	}
	public void setAdjustAppNo(String adjustAppNo) {
		this.adjustAppNo = adjustAppNo;
	}
	public String getPoaNo() {
		return poaNo;
	}
	public void setPoaNo(String poaNo) {
		this.poaNo = poaNo;
	}
	public String getOldRegNo() {
		return oldRegNo;
	}
	public void setOldRegNo(String oldRegNo) {
		this.oldRegNo = oldRegNo;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getPendingPropTax() {
		return pendingPropTax;
	}
	public void setPendingPropTax(String pendingPropTax) {
		this.pendingPropTax = pendingPropTax;
	}
	public String getPropCharges() {
		return propCharges;
	}
	public void setPropCharges(String propCharges) {
		this.propCharges = propCharges;
	}
	public String getSplitLocation() {
		return splitLocation;
	}
	public void setSplitLocation(String splitLocation) {
		this.splitLocation = splitLocation;
	}
	public String getEstampNo() {
		return estampNo;
	}
	public void setEstampNo(String estampNo) {
		this.estampNo = estampNo;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public int getFloorCount() {
		return floorCount;
	}
	public void setFloorCount(int floorCount) {
		this.floorCount = floorCount;
	}
	public String getFloorDesc() {
		return floorDesc;
	}
	public void setFloorDesc(String floorDesc) {
		this.floorDesc = floorDesc;
	}
/*	public String getPropUsageTypeId() {
		return propUsageTypeId;
	}
	public void setPropUsageTypeId(String propUsageTypeId) {
		this.propUsageTypeId = propUsageTypeId;
	}
	public String getPropUsageTypeName() {
		return propUsageTypeName;
	}
	public void setPropUsageTypeName(String propUsageTypeName) {
		this.propUsageTypeName = propUsageTypeName;
	}*/
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public ArrayList getFloorList() {
		return floorList;
	}
	public void setFloorList(ArrayList floorList) {
		this.floorList = floorList;
	}
	public String getCeilingTypeId() {
		return ceilingTypeId;
	}
	public void setCeilingTypeId(String ceilingTypeId) {
		this.ceilingTypeId = ceilingTypeId;
	}
	public String getCeilinghidType() {
		return ceilinghidType;
	}
	public void setCeilinghidType(String ceilinghidType) {
		this.ceilinghidType = ceilinghidType;
	}
	public ArrayList getPropertyType2List() {
		return propertyType2List;
	}
	public void setPropertyType2List(ArrayList propertyType2List) {
		this.propertyType2List = propertyType2List;
	}
	public String getSplitPartId() {
		return splitPartId;
	}
	public void setSplitPartId(String splitPartId) {
		this.splitPartId = splitPartId;
	}
	public String getSplitPartName() {
		return splitPartName;
	}
	public void setSplitPartName(String splitPartName) {
		this.splitPartName = splitPartName;
	}
	public ArrayList getSplitPartList() {
		return splitPartList;
	}
	public void setSplitPartList(ArrayList splitPartList) {
		this.splitPartList = splitPartList;
	}
	public String getSubclauseString() {
		return subclauseString;
	}
	public void setSubclauseString(String subclauseString) {
		this.subclauseString = subclauseString;
	}
	public HashMap getMapBuilding() {
		return mapBuilding;
	}
	public void setMapBuilding(HashMap mapBuilding) {
		this.mapBuilding = mapBuilding;
	}
	public String getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	public ArrayList getPropDetailsList() {
		return propDetailsList;
	}
	public void setPropDetailsList(ArrayList propDetailsList) {
		this.propDetailsList = propDetailsList;
	}
	public ArrayList getPropUsedList() {
		return propUsedList;
	}
	public void setPropUsedList(ArrayList propUsedList) {
		this.propUsedList = propUsedList;
	}
	public String[] getSubClauses() {
		return subClauses;
	}
	public void setSubClauses(String[] subClauses) {
		this.subClauses = subClauses;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getPropertyTxnNo() {
		return propertyTxnNo;
	}
	public void setPropertyTxnNo(String propertyTxnNo) {
		this.propertyTxnNo = propertyTxnNo;
	}
	public ArrayList getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(ArrayList propertyList) {
		this.propertyList = propertyList;
	}
	public String getFloorDetails() {
		return floorDetails;
	}
	public void setFloorDetails(String floorDetails) {
		this.floorDetails = floorDetails;
	}
	public String getFloorSubClases() {
		return floorSubClases;
	}
	public void setFloorSubClases(String floorSubClases) {
		this.floorSubClases = floorSubClases;
	}
	public HashMap getFloorValuesList() {
		return floorValuesList;
	}
	public void setFloorValuesList(HashMap floorValuesList) {
		this.floorValuesList = floorValuesList;
	}
	public String getCommercialTypeId() {
		return commercialTypeId;
	}
	public void setCommercialTypeId(String commercialTypeId) {
		this.commercialTypeId = commercialTypeId;
	}
	public boolean isPoaStatus() {
		return poaStatus;
	}
	public void setPoaStatus(boolean poaStatus) {
		this.poaStatus = poaStatus;
	}


	public boolean isAudjuStatus() {
		return audjuStatus;
	}
	public void setAudjuStatus(boolean audjuStatus) {
		this.audjuStatus = audjuStatus;
	}
	public String getSelectedFloor() {
		return selectedFloor;
	}
	public void setSelectedFloor(String selectedFloor) {
		this.selectedFloor = selectedFloor;
	}
	public String getFloorTxnId() {
		return floorTxnId;
	}
	public void setFloorTxnId(String floorTxnId) {
		this.floorTxnId = floorTxnId;
	}
	public String getWardStatus() {
		return wardStatus;
	}
	public void setWardStatus(String wardStatus) {
		this.wardStatus = wardStatus;
	}
	public int getAddMoreCounter() {
		return addMoreCounter;
	}
	public void setAddMoreCounter(int addMoreCounter) {
		this.addMoreCounter = addMoreCounter;
	}
	public String getUsePlot() {
		return usePlot;
	}
	public void setUsePlot(String usePlot) {
		this.usePlot = usePlot;
	}
	public String getHdnUsePlot() {
		return hdnUsePlot;
	}
	public void setHdnUsePlot(String hdnUsePlot) {
		this.hdnUsePlot = hdnUsePlot;
	}
	public String getHdnExtSubClause() {
		return hdnExtSubClause;
	}
	public void setHdnExtSubClause(String hdnExtSubClause) {
		this.hdnExtSubClause = hdnExtSubClause;
	}
	public String getHdnSubClauseName() {
		return hdnSubClauseName;
	}
	public void setHdnSubClauseName(String hdnSubClauseName) {
		this.hdnSubClauseName = hdnSubClauseName;
	}
	public double getTotalSqMeter() {
		return totalSqMeter;
	}
	public void setTotalSqMeter(double totalSqMeter) {
		this.totalSqMeter = totalSqMeter;
	}
	public String getSubClause() {
		return subClause;
	}
	public void setSubClause(String subClause) {
		this.subClause = subClause;
	}
	public double getConsiderAmt() {
		return considerAmt;
	}
	public void setConsiderAmt(double considerAmt) {
		this.considerAmt = considerAmt;
	}
	public String getSubClauseId() {
		return subClauseId;
	}
	public void setSubClauseId(String subClauseId) {
		this.subClauseId = subClauseId;
	}
	public String getHdnSubClause() {
		return hdnSubClause;
	}
	public void setHdnSubClause(String hdnSubClause) {
		this.hdnSubClause = hdnSubClause;
	}
	public String getMktValue() {
		return mktValue;
	}
	public void setMktValue(String mktValue) {
		this.mktValue = mktValue;
	}
	public String getLandMeterId() {
		return landMeterId;
	}
	public void setLandMeterId(String landMeterId) {
		this.landMeterId = landMeterId;
	}
	public String getLandMeterType() {
		return landMeterType;
	}
	public void setLandMeterType(String landMeterType) {
		this.landMeterType = landMeterType;
	}
	public String getHdnLandMeter() {
		return hdnLandMeter;
	}
	public void setHdnLandMeter(String hdnLandMeter) {
		this.hdnLandMeter = hdnLandMeter;
	}
	public String getLandMeter() {
		return landMeter;
	}
	public void setLandMeter(String landMeter) {
		this.landMeter = landMeter;
	}
	public String getAgriUnitFlag() {
		return agriUnitFlag;
	}
	public void setAgriUnitFlag(String agriUnitFlag) {
		this.agriUnitFlag = agriUnitFlag;
	}
	public int getNoOfTree() {
		return noOfTree;
	}
	public void setNoOfTree(int noOfTree) {
		this.noOfTree = noOfTree;
	}
	public String getHdnNoOfTree() {
		return hdnNoOfTree;
	}
	public void setHdnNoOfTree(String hdnNoOfTree) {
		this.hdnNoOfTree = hdnNoOfTree;
	}
	public String getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
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
	public String getDeleteFloorID() {
		return deleteFloorID;
	}
	public void setDeleteFloorID(String deleteFloorID) {
		this.deleteFloorID = deleteFloorID;
	}
	public String getHdnDeleteFloorID() {
		return hdnDeleteFloorID;
	}
	public void setHdnDeleteFloorID(String hdnDeleteFloorID) {
		this.hdnDeleteFloorID = hdnDeleteFloorID;
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
	public String getUsePlotId() {
		return usePlotId;
	}
	public void setUsePlotId(String usePlotId) {
		this.usePlotId = usePlotId;
	}
	public String getPropertyL2() {
		return propertyL2;
	}
	public void setPropertyL2(String propertyL2) {
		this.propertyL2 = propertyL2;
	}
	public String getRegisterID() {
		return registerID;
	}
	public void setRegisterID(String registerID) {
		this.registerID = registerID;
	}
	public ArrayList getRegisterList() {
		return registerList;
	}
	public void setRegisterList(ArrayList registerList) {
		this.registerList = registerList;
	}
	
	
}