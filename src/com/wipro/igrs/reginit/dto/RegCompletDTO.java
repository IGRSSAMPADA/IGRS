package com.wipro.igrs.reginit.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.upload.FormFile;

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
	//Start:===== Added by Ankita
	private String sheetnum;
	private String plotnum;
	private boolean cornerplot;
	//End:===== Added by Ankita
	public boolean isCornerplot() {
		return cornerplot;
	}
	public void setCornerplot(boolean cornerplot) {
		this.cornerplot = cornerplot;
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

	/*public int getPlotnum() {
		return plotnum;
	}
	public void setPlotnum(int plotnum) {
		this.plotnum = plotnum;
	}
	public int getSheetnum() {
		return sheetnum;
	}
	public void setSheetnum(int sheetnum) {
		this.sheetnum = sheetnum;
	}*/
	private String regNo;
	private String appNo;
	private String marketValue;
	private int addMoreCounter=0;
	// Added By Aruna
	private String regInitId;
	private String regStamp;
	private String regStampCode;
	//following added by roopam
	private int size;
	private float area;
	private ArrayList successMsglist=new ArrayList();
	private String khasraArea1;
	private String khasaraNum1;
	private String lagaan;
	private ArrayList khasraDetlsDisplay=new ArrayList();
	private int khasraPresentFlag=0;
	//private ArrayList exchangePropertyList=new ArrayList();
	private String selectedPropValId;
	private String selectedPropId;
	
	private ArrayList selectedSubClauseList = new ArrayList();
	private int propTypeL2Flag=0;
	
	private String[] selectedIndex;
	
	
	
	
	
	
	public String[] getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(String[] selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	public int getPropTypeL2Flag() {
		return propTypeL2Flag;
	}
	public void setPropTypeL2Flag(int propTypeL2Flag) {
		this.propTypeL2Flag = propTypeL2Flag;
	}
	public int getKhasraPresentFlag() {
		return khasraPresentFlag;
	}
	public void setKhasraPresentFlag(int khasraPresentFlag) {
		this.khasraPresentFlag = khasraPresentFlag;
	}
	public String getSelectedPropId() {
		return selectedPropId;
	}
	public void setSelectedPropId(String selectedPropId) {
		this.selectedPropId = selectedPropId;
	}
	public ArrayList getSelectedSubClauseList() {
		return selectedSubClauseList;
	}
	public void setSelectedSubClauseList(ArrayList selectedSubClauseList) {
		this.selectedSubClauseList = selectedSubClauseList;
	}
	public String getSelectedPropValId() {
		return selectedPropValId;
	}
	public void setSelectedPropValId(String selectedPropValId) {
		this.selectedPropValId = selectedPropValId;
	}
	/*public ArrayList getExchangePropertyList() {
		return exchangePropertyList;
	}
	public void setExchangePropertyList(ArrayList exchangePropertyList) {
		this.exchangePropertyList = exchangePropertyList;
	}*/
	public ArrayList getKhasraDetlsDisplay() {
		return khasraDetlsDisplay;
	}
	public void setKhasraDetlsDisplay(ArrayList khasraDetlsDisplay) {
		this.khasraDetlsDisplay = khasraDetlsDisplay;
	}
	public String getLagaan() {
		return lagaan;
	}
	public void setLagaan(String lagaan) {
		this.lagaan = lagaan;
	}
	public String getKhasraArea1() {
		return khasraArea1;
	}
	public void setKhasraArea1(String khasraArea1) {
		this.khasraArea1 = khasraArea1;
	}
	public String getKhasaraNum1() {
		return khasaraNum1;
	}
	public void setKhasaraNum1(String khasaraNum1) {
		this.khasaraNum1 = khasaraNum1;
	}
	

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
	private String totalSqMeterDisplay;
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
	
	//following added by roopam on 1 feb 2013
    private String propAddress;
    private String unit;
    private String municipalBodyName;
    
	
    
    private String totalCalculatedArea;
    private String propLandmark;
	
    //addded by shruti
    private String wardLabel;
    private String mahallaLabel;
    private String municipalBodyID;
    private String propTypeL1Id;
    private ArrayList propTypeL1List=new ArrayList();
    private String propTypeL1;
    private String hdnPropTypeL1;
    private String patwariStatus;
    //end of code by shruti
    //ROOPAM-TITLE DEED
    private String propTypeL2Id;
    //private ArrayList propTypeL1List=new ArrayList();
    private String propTypeL2;
    private String hdnPropTypeL2;
    private String unitId;
    private String unitType;
    private String unitTypeId;
    private String hdnUnitType;
    private String propertyId;
    private String khasraNoArray;
	private String khasraAreaArray;
	private String lagaanArray;
	private String rinPustikaArray;
	private double systemMV;
    //private HashMap mapProperty = new HashMap();
    
	private String considerAmtDisplay;
	
	
	
    
    public String getTotalSqMeterDisplay() {
		return totalSqMeterDisplay;
	}
	public void setTotalSqMeterDisplay(String totalSqMeterDisplay) {
		this.totalSqMeterDisplay = totalSqMeterDisplay;
	}
	public String getConsiderAmtDisplay() {
		return considerAmtDisplay;
	}
	public void setConsiderAmtDisplay(String considerAmtDisplay) {
		this.considerAmtDisplay = considerAmtDisplay;
	}
	/*public HashMap getMapProperty() {
		return mapProperty;
	}
	public void setMapProperty(HashMap mapProperty) {
		this.mapProperty = mapProperty;
	}*/
	public String getHdnUnitType() {
		return hdnUnitType;
	}
	public double getSystemMV() {
		return systemMV;
	}
	public void setSystemMV(double systemMV) {
		this.systemMV = systemMV;
	}
	public String getKhasraNoArray() {
		return khasraNoArray;
	}
	public void setKhasraNoArray(String khasraNoArray) {
		this.khasraNoArray = khasraNoArray;
	}
	public String getKhasraAreaArray() {
		return khasraAreaArray;
	}
	public void setKhasraAreaArray(String khasraAreaArray) {
		this.khasraAreaArray = khasraAreaArray;
	}
	public String getLagaanArray() {
		return lagaanArray;
	}
	public void setLagaanArray(String lagaanArray) {
		this.lagaanArray = lagaanArray;
	}
	public String getRinPustikaArray() {
		return rinPustikaArray;
	}
	public void setRinPustikaArray(String rinPustikaArray) {
		this.rinPustikaArray = rinPustikaArray;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public void setHdnUnitType(String hdnUnitType) {
		this.hdnUnitType = hdnUnitType;
	}

	private int addMoreFloorCounter=0;
    private String propTypeL1Name;
    
    
    private String floorId;
    private String floorType;
    private String floorTypeId;
    
    private float areaConstructed;
    
  //following added by roopam for compliance list
    
    
    
	//Property image taken from three different angles
    private FormFile propImage1 = null;
	private String propImage1DocumentName;
	private String propImage1DocumentSize;
	private byte[] propImage1DocContents;
	//private String prop1PhotoCheck;
	private String propImage1FilePath;
	private String propImage1PartyOrProp;
	private String propImage1UploadType;
	
	private FormFile propImage2 = null;
	private String propImage2DocumentName;
	private String propImage2DocumentSize;
	private byte[] propImage2DocContents;
	//private String prop1PhotoCheck;
	private String propImage2FilePath;
	private String propImage2PartyOrProp;
	private String propImage2UploadType;
	
	private FormFile propImage3 = null;
	private String propImage3DocumentName;
	private String propImage3DocumentSize;
	private byte[] propImage3DocContents;
	//private String prop1PhotoCheck;
	private String propImage3FilePath;
	private String propImage3PartyOrProp;
	private String propImage3UploadType;
	
	
	private FormFile propertyMap = null;
	private String propMapDocumentName;
	private String propMapDocumentSize;
	private byte[] propMapDocContents;
	//private String prop1PhotoCheck;
	private String propMapFilePath;
	private String propMapPartyOrProp;
	private String propMapUploadType;
	
	
	private FormFile propertyRin = null;
	private String propRinDocumentName;
	private String propRinDocumentSize;
	private byte[] propRinDocContents;
	//private String prop1PhotoCheck;
	private String propRinFilePath;
	private String propRinPartyOrProp;
	private String propRinUploadType;
	
	
	private FormFile propertyKhasra = null;
	private String propKhasraDocumentName;
	private String propKhasraDocumentSize;
	private byte[] propKhasraDocContents;
	//private String prop1PhotoCheck;
	private String propKhasraFilePath;
	private String propKhasraPartyOrProp;
	private String propKhasraUploadType;
	
	
	ArrayList partyListPdf=new ArrayList();                      //for pdf
	ArrayList propertyListExchngDeedPdf=new ArrayList();                      //for pdf
	private String guidelineValue;
	
	
	public ArrayList getPropertyListExchngDeedPdf() {
		return propertyListExchngDeedPdf;
	}
	public void setPropertyListExchngDeedPdf(ArrayList propertyListExchngDeedPdf) {
		this.propertyListExchngDeedPdf = propertyListExchngDeedPdf;
	}
	public String getPropTypeL1Name() {
		return propTypeL1Name;
	}
	public void setPropTypeL1Name(String propTypeL1Name) {
		this.propTypeL1Name = propTypeL1Name;
	}
	public int getAddMoreFloorCounter() {
		return addMoreFloorCounter;
	}
	public void setAddMoreFloorCounter(int addMoreFloorCounter) {
		this.addMoreFloorCounter = addMoreFloorCounter;
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
	public String getUnitTypeId() {
		return unitTypeId;
	}
	public void setUnitTypeId(String unitTypeId) {
		this.unitTypeId = unitTypeId;
	}
	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
	public String getFloorType() {
		return floorType;
	}
	public void setFloorType(String floorType) {
		this.floorType = floorType;
	}
	public String getFloorTypeId() {
		return floorTypeId;
	}
	public void setFloorTypeId(String floorTypeId) {
		this.floorTypeId = floorTypeId;
	}
	public float getAreaConstructed() {
		return areaConstructed;
	}
	public void setAreaConstructed(float areaConstructed) {
		this.areaConstructed = areaConstructed;
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
	public String getHdnPropTypeL2() {
		return hdnPropTypeL2;
	}
	public void setHdnPropTypeL2(String hdnPropTypeL2) {
		this.hdnPropTypeL2 = hdnPropTypeL2;
	}
	public String getGuidelineValue() {
		return guidelineValue;
	}
	public void setGuidelineValue(String guidelineValue) {
		this.guidelineValue = guidelineValue;
	}
	public ArrayList getPartyListPdf() {
		return partyListPdf;
	}
	public void setPartyListPdf(ArrayList partyListPdf) {
		this.partyListPdf = partyListPdf;
	}
	public FormFile getPropertyKhasra() {
		return propertyKhasra;
	}
	public void setPropertyKhasra(FormFile propertyKhasra) {
		this.propertyKhasra = propertyKhasra;
	}
	public String getPropKhasraDocumentName() {
		return propKhasraDocumentName;
	}
	public void setPropKhasraDocumentName(String propKhasraDocumentName) {
		this.propKhasraDocumentName = propKhasraDocumentName;
	}
	public String getPropKhasraDocumentSize() {
		return propKhasraDocumentSize;
	}
	public void setPropKhasraDocumentSize(String propKhasraDocumentSize) {
		this.propKhasraDocumentSize = propKhasraDocumentSize;
	}
	public byte[] getPropKhasraDocContents() {
		return propKhasraDocContents;
	}
	public void setPropKhasraDocContents(byte[] propKhasraDocContents) {
		this.propKhasraDocContents = propKhasraDocContents;
	}
	public String getPropKhasraFilePath() {
		return propKhasraFilePath;
	}
	public void setPropKhasraFilePath(String propKhasraFilePath) {
		this.propKhasraFilePath = propKhasraFilePath;
	}
	public String getPropKhasraPartyOrProp() {
		return propKhasraPartyOrProp;
	}
	public void setPropKhasraPartyOrProp(String propKhasraPartyOrProp) {
		this.propKhasraPartyOrProp = propKhasraPartyOrProp;
	}
	public String getPropKhasraUploadType() {
		return propKhasraUploadType;
	}
	public void setPropKhasraUploadType(String propKhasraUploadType) {
		this.propKhasraUploadType = propKhasraUploadType;
	}
	public FormFile getPropertyRin() {
		return propertyRin;
	}
	public void setPropertyRin(FormFile propertyRin) {
		this.propertyRin = propertyRin;
	}
	public String getPropRinDocumentName() {
		return propRinDocumentName;
	}
	public void setPropRinDocumentName(String propRinDocumentName) {
		this.propRinDocumentName = propRinDocumentName;
	}
	public String getPropRinDocumentSize() {
		return propRinDocumentSize;
	}
	public void setPropRinDocumentSize(String propRinDocumentSize) {
		this.propRinDocumentSize = propRinDocumentSize;
	}
	public byte[] getPropRinDocContents() {
		return propRinDocContents;
	}
	public void setPropRinDocContents(byte[] propRinDocContents) {
		this.propRinDocContents = propRinDocContents;
	}
	public String getPropRinFilePath() {
		return propRinFilePath;
	}
	public void setPropRinFilePath(String propRinFilePath) {
		this.propRinFilePath = propRinFilePath;
	}
	public String getPropRinPartyOrProp() {
		return propRinPartyOrProp;
	}
	public void setPropRinPartyOrProp(String propRinPartyOrProp) {
		this.propRinPartyOrProp = propRinPartyOrProp;
	}
	public String getPropRinUploadType() {
		return propRinUploadType;
	}
	public void setPropRinUploadType(String propRinUploadType) {
		this.propRinUploadType = propRinUploadType;
	}
	public FormFile getPropertyMap() {
		return propertyMap;
	}
	public void setPropertyMap(FormFile propertyMap) {
		this.propertyMap = propertyMap;
	}
	public String getPropMapDocumentName() {
		return propMapDocumentName;
	}
	public void setPropMapDocumentName(String propMapDocumentName) {
		this.propMapDocumentName = propMapDocumentName;
	}
	public String getPropMapDocumentSize() {
		return propMapDocumentSize;
	}
	public void setPropMapDocumentSize(String propMapDocumentSize) {
		this.propMapDocumentSize = propMapDocumentSize;
	}
	public byte[] getPropMapDocContents() {
		return propMapDocContents;
	}
	public void setPropMapDocContents(byte[] propMapDocContents) {
		this.propMapDocContents = propMapDocContents;
	}
	public String getPropMapFilePath() {
		return propMapFilePath;
	}
	public void setPropMapFilePath(String propMapFilePath) {
		this.propMapFilePath = propMapFilePath;
	}
	public String getPropMapPartyOrProp() {
		return propMapPartyOrProp;
	}
	public void setPropMapPartyOrProp(String propMapPartyOrProp) {
		this.propMapPartyOrProp = propMapPartyOrProp;
	}
	public String getPropMapUploadType() {
		return propMapUploadType;
	}
	public void setPropMapUploadType(String propMapUploadType) {
		this.propMapUploadType = propMapUploadType;
	}
	public FormFile getPropImage1() {
		return propImage1;
	}
	public void setPropImage1(FormFile propImage1) {
		this.propImage1 = propImage1;
	}
	public String getPropImage1DocumentName() {
		return propImage1DocumentName;
	}
	public void setPropImage1DocumentName(String propImage1DocumentName) {
		this.propImage1DocumentName = propImage1DocumentName;
	}
	public String getPropImage1DocumentSize() {
		return propImage1DocumentSize;
	}
	public void setPropImage1DocumentSize(String propImage1DocumentSize) {
		this.propImage1DocumentSize = propImage1DocumentSize;
	}
	public byte[] getPropImage1DocContents() {
		return propImage1DocContents;
	}
	public void setPropImage1DocContents(byte[] propImage1DocContents) {
		this.propImage1DocContents = propImage1DocContents;
	}
	public String getPropImage1FilePath() {
		return propImage1FilePath;
	}
	public void setPropImage1FilePath(String propImage1FilePath) {
		this.propImage1FilePath = propImage1FilePath;
	}
	public String getPropImage1PartyOrProp() {
		return propImage1PartyOrProp;
	}
	public void setPropImage1PartyOrProp(String propImage1PartyOrProp) {
		this.propImage1PartyOrProp = propImage1PartyOrProp;
	}
	public String getPropImage1UploadType() {
		return propImage1UploadType;
	}
	public void setPropImage1UploadType(String propImage1UploadType) {
		this.propImage1UploadType = propImage1UploadType;
	}
	public FormFile getPropImage2() {
		return propImage2;
	}
	public void setPropImage2(FormFile propImage2) {
		this.propImage2 = propImage2;
	}
	public String getPropImage2DocumentName() {
		return propImage2DocumentName;
	}
	public void setPropImage2DocumentName(String propImage2DocumentName) {
		this.propImage2DocumentName = propImage2DocumentName;
	}
	public String getPropImage2DocumentSize() {
		return propImage2DocumentSize;
	}
	public void setPropImage2DocumentSize(String propImage2DocumentSize) {
		this.propImage2DocumentSize = propImage2DocumentSize;
	}
	public byte[] getPropImage2DocContents() {
		return propImage2DocContents;
	}
	public void setPropImage2DocContents(byte[] propImage2DocContents) {
		this.propImage2DocContents = propImage2DocContents;
	}
	public String getPropImage2FilePath() {
		return propImage2FilePath;
	}
	public void setPropImage2FilePath(String propImage2FilePath) {
		this.propImage2FilePath = propImage2FilePath;
	}
	public String getPropImage2PartyOrProp() {
		return propImage2PartyOrProp;
	}
	public void setPropImage2PartyOrProp(String propImage2PartyOrProp) {
		this.propImage2PartyOrProp = propImage2PartyOrProp;
	}
	public String getPropImage2UploadType() {
		return propImage2UploadType;
	}
	public void setPropImage2UploadType(String propImage2UploadType) {
		this.propImage2UploadType = propImage2UploadType;
	}
	public FormFile getPropImage3() {
		return propImage3;
	}
	public void setPropImage3(FormFile propImage3) {
		this.propImage3 = propImage3;
	}
	public String getPropImage3DocumentName() {
		return propImage3DocumentName;
	}
	public void setPropImage3DocumentName(String propImage3DocumentName) {
		this.propImage3DocumentName = propImage3DocumentName;
	}
	public String getPropImage3DocumentSize() {
		return propImage3DocumentSize;
	}
	public void setPropImage3DocumentSize(String propImage3DocumentSize) {
		this.propImage3DocumentSize = propImage3DocumentSize;
	}
	public byte[] getPropImage3DocContents() {
		return propImage3DocContents;
	}
	public void setPropImage3DocContents(byte[] propImage3DocContents) {
		this.propImage3DocContents = propImage3DocContents;
	}
	public String getPropImage3FilePath() {
		return propImage3FilePath;
	}
	public void setPropImage3FilePath(String propImage3FilePath) {
		this.propImage3FilePath = propImage3FilePath;
	}
	public String getPropImage3PartyOrProp() {
		return propImage3PartyOrProp;
	}
	public void setPropImage3PartyOrProp(String propImage3PartyOrProp) {
		this.propImage3PartyOrProp = propImage3PartyOrProp;
	}
	public String getPropImage3UploadType() {
		return propImage3UploadType;
	}
	public void setPropImage3UploadType(String propImage3UploadType) {
		this.propImage3UploadType = propImage3UploadType;
	}
	public String getHdnPropTypeL1() {
		return hdnPropTypeL1;
	}
	public void setHdnPropTypeL1(String hdnPropTypeL1) {
		this.hdnPropTypeL1 = hdnPropTypeL1;
	}
	public String getPropTypeL1() {
		return propTypeL1;
	}
	public ArrayList getPropTypeL1List() {
		return propTypeL1List;
	}
	public void setPropTypeL1List(ArrayList propTypeL1List) {
		this.propTypeL1List = propTypeL1List;
	}
	public void setPropTypeL1(String propTypeL1) {
		this.propTypeL1 = propTypeL1;
	}
	public String getPropTypeL1Id() {
		return propTypeL1Id;
	}
	public void setPropTypeL1Id(String propTypeL1Id) {
		this.propTypeL1Id = propTypeL1Id;
	}
	public String getMahallaLabel() {
		return mahallaLabel;
	}
	public void setMahallaLabel(String mahallaLabel) {
		this.mahallaLabel = mahallaLabel;
	}
	public String getWardLabel() {
		return wardLabel;
	}
	public void setWardLabel(String wardLabel) {
		this.wardLabel = wardLabel;
	}
	public String getMunicipalBodyID() {
		return municipalBodyID;
	}
	public void setMunicipalBodyID(String municipalBodyID) {
		this.municipalBodyID = municipalBodyID;
	}
	
	public String getPropLandmark() {
		return propLandmark;
	}
	public void setPropLandmark(String propLandmark) {
		this.propLandmark = propLandmark;
	}
	public String getTotalCalculatedArea() {
		return totalCalculatedArea;
	}
	public void setTotalCalculatedArea(String totalCalculatedArea) {
		this.totalCalculatedArea = totalCalculatedArea;
	}
	public String getPatwariStatus() {
		return patwariStatus;
	}
	public void setPatwariStatus(String patwariStatus) {
		this.patwariStatus = patwariStatus;
	}
	public String getMunicipalBodyName() {
		return municipalBodyName;
	}
	public void setMunicipalBodyName(String municipalBodyName) {
		this.municipalBodyName = municipalBodyName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPropAddress() {
		return propAddress;
	}
	public void setPropAddress(String propAddress) {
		this.propAddress = propAddress;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}

	
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
	public String getRegInitId() {
		return regInitId;
	}
	public void setRegInitId(String regInitId) {
		this.regInitId = regInitId;
	}
	
	public ArrayList getSuccessMsglist() {
		return successMsglist;
	}
	public void setSuccessMsglist(ArrayList successMsglist) {
		this.successMsglist = successMsglist;
	}

	public String getRegStamp() {
		return regStamp;
	}
	public void setRegStamp(String regStamp) {
		this.regStamp = regStamp;
	}
	public String getRegStampCode() {
		return regStampCode;
	}
	public void setRegStampCode(String regStampCode) {
		this.regStampCode = regStampCode;
	}
	
	
}