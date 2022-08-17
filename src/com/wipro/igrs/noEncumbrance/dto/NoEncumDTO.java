package com.wipro.igrs.noEncumbrance.dto;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

public class NoEncumDTO implements Serializable{
	private String currentUserId;
	private String actionName; // Variable for accessing caller(e.g. Button, Component etc) from form component
	private String formName;
	private String regUserId;
	private String payTxnId;
	private String txnId;
	private String balanceAmount;
	private String paidAmount;
	private String lastTransactionDate;
	private String status;
	private String disDate;
	private String postalTrakingNum;
	private String updtdRemarks;
	private String purposeDownload;
	private String dispatchType;
	private String updateRequestAction;
	private String dateOfRequest;
	private String radioButton;
	private String downloadProperty;
	
	private String regTxnId;
	private String fromDate;
	private String toDate;
	
	//added by satbir kumar--
	
	private transient FormFile attachedDoc;
    private transient byte[] docContents;
    private String docFileSize;
    private String uploaded_doc_path;
    private String documentName;
    private byte[] photo;
    private String documentSavePath;
    //--end of addition----
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getRegTxnId() {
		return regTxnId;
	}
	public void setRegTxnId(String regTxnId) {
		this.regTxnId = regTxnId;
	}
	public String getRadioButton() {
		return radioButton;
	}
	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}
	public String getDownloadProperty() {
		return downloadProperty;
	}
	public void setDownloadProperty(String downloadProperty) {
		this.downloadProperty = downloadProperty;
	}
	//private String status;
	public String getUpdateRequestAction() {
		return updateRequestAction;
	}
	public String getDateOfRequest() {
		return dateOfRequest;
	}
	public void setDateOfRequest(String dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}
	public void setUpdateRequestAction(String updateRequestAction) {
		this.updateRequestAction = updateRequestAction;
	}
	public String getDispatchType() {
		return dispatchType;
	}
	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}
	public String getPurposeDownload() {
		return purposeDownload;
	}
	public void setPurposeDownload(String purposeDownload) {
		this.purposeDownload = purposeDownload;
	}
	public String getDisDate() {
		return disDate;
	}
	public void setDisDate(String disDate) {
		this.disDate = disDate;
	}
	public String getPostalTrakingNum() {
		return postalTrakingNum;
	}
	public void setPostalTrakingNum(String postalTrakingNum) {
		this.postalTrakingNum = postalTrakingNum;
	}
	public String getUpdtdRemarks() {
		return updtdRemarks;
	}
	public void setUpdtdRemarks(String updtdRemarks) {
		this.updtdRemarks = updtdRemarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastTransactionDate() {
		return lastTransactionDate;
	}
	public void setLastTransactionDate(String lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	//-------------party details variable
	private String cerCopyTxnId;
	private String cerCopyTxnIdSearch;
	private String fname;
    private String mname;
    private String lname;
    private String fullName;
    private String fatherName;
    private String motherName;
    private String spouseName;
    private String gender;
    private String age;
    private String casteId;
    private String casteName;
    private ArrayList casteList;
    private String religionId;
    private String religionName;
    private ArrayList religionList;
    private String nationality;
    private String phyChallanged;
    private String photoBankName;
    private String photoBankAddress;
    private String address;
    private String occupation;
    private String countryName;
    private String countryId;
    private ArrayList countryList;
    
    private String stateName;
    private String stateId;
    private ArrayList stateList;
    
    private String districtName;
    private String districtId;
    private ArrayList districtList;
    
    private String postalCode;
    private String phoneNo;
    private String mobNo;
    private String mailId;
    private String photoProofTypeName;
    private String photoProofTypeId;
    private String photoId;
    private ArrayList photoIdList;
    private String bankName;
    private String bankAddress;
    
    private String regNo;
    private String regNoSearch;
    
    //Start Temp Variables
    private String countryName2;
    private String stateName2;
    private String districtName2;
    private String casteName2;
    private String religionName2;
    private String photoProofTypeName2;
    
    //End Temp variables
    
	private String reportMsg;
	private String errorMsg=null;
	private String searchFromDate;
	private String searchToDate;
	private int serialNo;
	private String useIdFlag="no";
	private String userMode;
    private String createdDate;
	//Flag Variables
	private String propertyLockFlag;
	private String caveatFlag;
	private String caseFlag;
	private String poaFlag;
	private String courtOrderFlag;
	
	//Fee
	private String totalFee;
	private String serviceFee;
	private String otherFee;
	
	//Property
    private String propertyId;
    private String propertyName;
    private ArrayList propertyList;
    
    private String propCountryName;
    private String propCountryId;
    private ArrayList propCountryList;
    private String statePropName;
    private String statePropId;
    private ArrayList statePropList;
    
    private String propDistrictName;
    private String propDistrictId;
    private ArrayList propDistrictList;
    
    private String propTehesilName;
	private String propTehesilId;
    private ArrayList propTehesilList;
    
    private String propPatwariName;
 	private String propPatwariId;
    private ArrayList propPatwariList;
    
    private String propGramName;
 	private String propGramId;
    private ArrayList propGramList;
	private String viewAction;
	private String viewGender;
	private String regDate;
	private String purposeReq;
private String encRegNo;
private String encRegDate;


	//Agriculture
     private String agViKhand;
     private String agRicircle;
     private String agpatwari;
     private String agGram;
     private String agKhasraNo;
     private String agLayoutDtls;
     private String agPustikaNo;
     private String agNorthboundryDtls;
     private String agSouthboundryDtls;
     private String agEastboundryDtls;
     private String agWestboundryDtls;
     private String agSize;
     private String agSizeLength;
     private String agSizeBreadth;
     public String getAgSizeLength() {
		return agSizeLength;
	}
	public void setAgSizeLength(String agSizeLength) {
		this.agSizeLength = agSizeLength;
	}
	public String getAgSizeBreadth() {
		return agSizeBreadth;
	}
	public void setAgSizeBreadth(String agSizeBreadth) {
		this.agSizeBreadth = agSizeBreadth;
	}
	public String getPltSizeLength() {
		return pltSizeLength;
	}
	public void setPltSizeLength(String pltSizeLength) {
		this.pltSizeLength = pltSizeLength;
	}
	public String getPltSizeBreadth() {
		return pltSizeBreadth;
	}
	public void setPltSizeBreadth(String pltSizeBreadth) {
		this.pltSizeBreadth = pltSizeBreadth;
	}
	private String agArea;
     private String agIsIrigated;
    
     //PLot     

     private String pltViKhand;
     private String pltRicircle;
     private String pltPatwari;
     private String pltGram;
     private String pltMohala;
     private String pltKhasraNo;
     private String pltLayout;
     private String pltNazSheetNo;
     private String pltPlotNo;
     private String pltNorthboundryDtls;
     private String pltSouthboundryDtls;
     private String pltEastboundryDtls;
     private String pltWestboundryDtls;
     private String pltSize;
     private String pltSizeLength;
     private String pltSizeBreadth;
     private String pltArea;
     private String pltCorner;
     private String pltResiCom;


     //Building
   
     private String buildViKhand;
     private String buildRicircle;
     private String buildPatwari;
     private String buildGram;
     private String buildMohalla;
     private String buildKhasraNo;
     private String buildLayout;
     private String buildNazSheetNo;
     private String buildPlotNo;
     private String builsPlotArea;
     private String buildResiCom;
     private String buildNoOfFloors;
     private String buildFloorType;
     private String buildSize;
     private String buildArea;
     private String buildingNoAndShopOffice;
     private String buildCeilingType;
     private String buildNorthboundryDtls;
     private String buildSouthboundryDtls;
     private String buildEastboundryDtls;
     private String buildWestboundryDtls;
     
     private String parentFunName;
     private String parentModName;
     private String fee;
     private String postalFee;
	private String isSplit;
	private String plotArea;
	private String noOfShop;
	private String buildingType;
	private String noOfConstFloor;
	private String showGender;
	private String showRegNo;
	private String floorTypeId;
	private String isPlotSplit;
	private String showPropName;
	private String municipalty;
	private ArrayList municipaltyList;
	private String municipaltyId;
	private String municipaltyName;
	private String areaType;
	private ArrayList areaTypeList;
	private String areaTypeName;
	private String areaTypeId;
	private String showpCountyName;
	private String showpStateName;
	private String showpDistName;
	private String showpTehesilName;
	private String showpPatwariName;
	private String showpMuncpaltyName;
	private String showpUrbanName;
	private String showpGramName;
	private String floorLabel;
	private String roleId;
	private String funId;
	private String userId;
	private String documentId;
	private String  khasraNumber;
	private String rinPustika;
	private String propertyTxnId;
    private String propertyTypeId;
    private String propertyTypeLabel;
    private ArrayList cloneSelectedItems=new ArrayList();
    private ArrayList selectedItems=new ArrayList();
	
	public ArrayList getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(ArrayList selectedItems) {
		this.selectedItems = selectedItems;
	}
	public ArrayList getCloneSelectedItems() {
		return cloneSelectedItems;
	}
	public void setCloneSelectedItems(ArrayList cloneSelectedItems) {
		this.cloneSelectedItems = cloneSelectedItems;
	}
	public String getPropertyTxnId() {
		return propertyTxnId;
	}
	public void setPropertyTxnId(String propertyTxnId) {
		this.propertyTxnId = propertyTxnId;
	}
	public String getPropertyTypeId() {
		return propertyTypeId;
	}
	public void setPropertyTypeId(String propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}
	public String getPropertyTypeLabel() {
		return propertyTypeLabel;
	}
	public void setPropertyTypeLabel(String propertyTypeLabel) {
		this.propertyTypeLabel = propertyTypeLabel;
	}
	public String getKhasraNumber() {
		return khasraNumber;
	}
	public void setKhasraNumber(String khasraNumber) {
		this.khasraNumber = khasraNumber;
	}
	public String getRinPustika() {
		return rinPustika;
	}
	public void setRinPustika(String rinPustika) {
		this.rinPustika = rinPustika;
	}
	private ArrayList<KhasraDTO> khasraList=new ArrayList<KhasraDTO>();
    
	
	public ArrayList<KhasraDTO> getKhasraList() {
	return this.khasraList;
}
public void setKhasraList(ArrayList<KhasraDTO> khasraList) {
	this.khasraList = khasraList;
}
	public String getDocumentId() {
	return documentId;
}
public void setDocumentId(String documentId) {
	this.documentId = documentId;
}
	public String getRoleId() {
	return roleId;
}
public void setRoleId(String roleId) {
	this.roleId = roleId;
}
public String getFunId() {
	return funId;
}
public void setFunId(String funId) {
	this.funId = funId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
	public String getFloorLabel() {
		return floorLabel;
	}
	public void setFloorLabel(String floorLabel) {
		this.floorLabel = floorLabel;
	}
	public String getEncRegNo() {
	return encRegNo;
}
public void setEncRegNo(String encRegNo) {
	this.encRegNo = encRegNo;
}
public String getEncRegDate() {
	return encRegDate;
}
public void setEncRegDate(String encRegDate) {
	this.encRegDate = encRegDate;
}
	public String getPurposeReq() {
		return purposeReq;
	}
	public void setPurposeReq(String purposeReq) {
		this.purposeReq = purposeReq;
	}
	public String getRegDate() {
	return regDate;
}
public void setRegDate(String regDate) {
	this.regDate = regDate;
}
	public String getViewGender() {
	return viewGender;
}
public void setViewGender(String viewGender) {
	this.viewGender = viewGender;
}
	public String getViewAction() {
	return viewAction;
}
public void setViewAction(String viewAction) {
	this.viewAction = viewAction;
}
	public String getPropGramName() {
		return propGramName;
	}
	public void setPropGramName(String propGramName) {
		this.propGramName = propGramName;
	}
	public String getPropGramId() {
		return propGramId;
	}
	public void setPropGramId(String propGramId) {
		this.propGramId = propGramId;
	}
	public ArrayList getPropGramList() {
		return propGramList;
	}
	public void setPropGramList(ArrayList propGramList) {
		this.propGramList = propGramList;
	}
	
	public String getShowpGramName() {
		return showpGramName;
	}
	public void setShowpGramName(String showpGramName) {
		this.showpGramName = showpGramName;
	}
	public String getShowpCountyName() {
		return showpCountyName;
	}
	public void setShowpCountyName(String showpCountyName) {
		this.showpCountyName = showpCountyName;
	}
	public String getShowpStateName() {
		return showpStateName;
	}
	public void setShowpStateName(String showpStateName) {
		this.showpStateName = showpStateName;
	}
	public String getShowpDistName() {
		return showpDistName;
	}
	public void setShowpDistName(String showpDistName) {
		this.showpDistName = showpDistName;
	}
	public String getShowpTehesilName() {
		return showpTehesilName;
	}
	public void setShowpTehesilName(String showpTehesilName) {
		this.showpTehesilName = showpTehesilName;
	}
	public String getShowpPatwariName() {
		return showpPatwariName;
	}
	public void setShowpPatwariName(String showpPatwariName) {
		this.showpPatwariName = showpPatwariName;
	}
	public String getShowpMuncpaltyName() {
		return showpMuncpaltyName;
	}
	public void setShowpMuncpaltyName(String showpMuncpaltyName) {
		this.showpMuncpaltyName = showpMuncpaltyName;
	}
	public String getShowpUrbanName() {
		return showpUrbanName;
	}
	public void setShowpUrbanName(String showpUrbanName) {
		this.showpUrbanName = showpUrbanName;
	}
	public ArrayList getAreaTypeList() {
		return areaTypeList;
	}
	public void setAreaTypeList(ArrayList areaTypeList) {
		this.areaTypeList = areaTypeList;
	}
	public String getAreaTypeName() {
		return areaTypeName;
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
	public String getMunicipalty() {
		return municipalty;
	}
	public void setMunicipalty(String municipalty) {
		this.municipalty = municipalty;
	}
	public ArrayList getMunicipaltyList() {
		return municipaltyList;
	}
	public void setMunicipaltyList(ArrayList municipaltyList) {
		this.municipaltyList = municipaltyList;
	}
	public String getMunicipaltyId() {
		return municipaltyId;
	}
	public void setMunicipaltyId(String municipaltyId) {
		this.municipaltyId = municipaltyId;
	}
	public String getMunicipaltyName() {
		return municipaltyName;
	}
	public void setMunicipaltyName(String municipaltyName) {
		this.municipaltyName = municipaltyName;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getShowPropName() {
		return showPropName;
	}
	public void setShowPropName(String showPropName) {
		this.showPropName = showPropName;
	}
	public String getIsPlotSplit() {
		return isPlotSplit;
	}
	public void setIsPlotSplit(String isPlotSplit) {
		this.isPlotSplit = isPlotSplit;
	}
	ArrayList<FloorDetailsDTO> floordetails = new ArrayList<FloorDetailsDTO>();
	
	public ArrayList<FloorDetailsDTO> getFloordetails() {
		return floordetails;
	}
	public void setFloordetails(ArrayList<FloorDetailsDTO> floordetails) {
		this.floordetails = floordetails;
	}
	public String getFloorTypeId() {
		return floorTypeId;
	}
	public void setFloorTypeId(String floorTypeId) {
		this.floorTypeId = floorTypeId;
	}
	public String getShowGender() {
		return showGender;
	}
	public void setShowGender(String showGender) {
		this.showGender = showGender;
	}
	public String getShowRegNo() {
		return showRegNo;
	}
	public void setShowRegNo(String showRegNo) {
		this.showRegNo = showRegNo;
	}
	public String getPlotArea() {
		return plotArea;
	}
	public void setPlotArea(String plotArea) {
		this.plotArea = plotArea;
	}
	public String getNoOfShop() {
		return noOfShop;
	}
	public void setNoOfShop(String noOfShop) {
		this.noOfShop = noOfShop;
	}
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	public String getNoOfConstFloor() {
		return noOfConstFloor;
	}
	public void setNoOfConstFloor(String noOfConstFloor) {
		this.noOfConstFloor = noOfConstFloor;
	}
	public String getIsSplit() {
		return isSplit;
	}
	public void setIsSplit(String isSplit) {
		this.isSplit = isSplit;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getPostalFee() {
		return postalFee;
	}
	public void setPostalFee(String postalFee) {
		this.postalFee = postalFee;
	}
	public String getParentFunName() {
		return parentFunName;
	}
	public void setParentFunName(String parentFunName) {
		this.parentFunName = parentFunName;
	}
	public String getParentModName() {
		return parentModName;
	}
	public void setParentModName(String parentModName) {
		this.parentModName = parentModName;
	}
	public String getAgViKhand() {
		return agViKhand;
	}
	public void setAgViKhand(String agViKhand) {
		this.agViKhand = agViKhand;
	}
	public String getAgRicircle() {
		return agRicircle;
	}
	public void setAgRicircle(String agRicircle) {
		this.agRicircle = agRicircle;
	}
	public String getAgpatwari() {
		return agpatwari;
	}
	public void setAgpatwari(String agpatwari) {
		this.agpatwari = agpatwari;
	}
	public String getAgGram() {
		return agGram;
	}
	public void setAgGram(String agGram) {
		this.agGram = agGram;
	}
	public String getAgKhasraNo() {
		return agKhasraNo;
	}
	public void setAgKhasraNo(String agKhasraNo) {
		this.agKhasraNo = agKhasraNo;
	}
	public String getAgLayoutDtls() {
		return agLayoutDtls;
	}
	public void setAgLayoutDtls(String agLayoutDtls) {
		this.agLayoutDtls = agLayoutDtls;
	}
	public String getAgPustikaNo() {
		return agPustikaNo;
	}
	public void setAgPustikaNo(String agPustikaNo) {
		this.agPustikaNo = agPustikaNo;
	}
	public String getAgNorthboundryDtls() {
		return agNorthboundryDtls;
	}
	public void setAgNorthboundryDtls(String agNorthboundryDtls) {
		this.agNorthboundryDtls = agNorthboundryDtls;
	}
	public String getAgSouthboundryDtls() {
		return agSouthboundryDtls;
	}
	public void setAgSouthboundryDtls(String agSouthboundryDtls) {
		this.agSouthboundryDtls = agSouthboundryDtls;
	}
	public String getAgEastboundryDtls() {
		return agEastboundryDtls;
	}
	public void setAgEastboundryDtls(String agEastboundryDtls) {
		this.agEastboundryDtls = agEastboundryDtls;
	}
	public String getAgWestboundryDtls() {
		return agWestboundryDtls;
	}
	public void setAgWestboundryDtls(String agWestboundryDtls) {
		this.agWestboundryDtls = agWestboundryDtls;
	}
	public String getAgSize() {
		return agSize;
	}
	public void setAgSize(String agSize) {
		this.agSize = agSize;
	}
	public String getAgArea() {
		return agArea;
	}
	public void setAgArea(String agArea) {
		this.agArea = agArea;
	}
	public String getAgIsIrigated() {
		return agIsIrigated;
	}
	public void setAgIsIrigated(String agIsIrigated) {
		this.agIsIrigated = agIsIrigated;
	}
	public String getPltViKhand() {
		return pltViKhand;
	}
	public void setPltViKhand(String pltViKhand) {
		this.pltViKhand = pltViKhand;
	}
	public String getPltRicircle() {
		return pltRicircle;
	}
	public void setPltRicircle(String pltRicircle) {
		this.pltRicircle = pltRicircle;
	}
	public String getPltPatwari() {
		return pltPatwari;
	}
	public void setPltPatwari(String pltPatwari) {
		this.pltPatwari = pltPatwari;
	}
	public String getPltGram() {
		return pltGram;
	}
	public void setPltGram(String pltGram) {
		this.pltGram = pltGram;
	}
	public String getPltMohala() {
		return pltMohala;
	}
	public void setPltMohala(String pltMohala) {
		this.pltMohala = pltMohala;
	}
	public String getPltKhasraNo() {
		return pltKhasraNo;
	}
	public void setPltKhasraNo(String pltKhasraNo) {
		this.pltKhasraNo = pltKhasraNo;
	}
	public String getPltLayout() {
		return pltLayout;
	}
	public void setPltLayout(String pltLayout) {
		this.pltLayout = pltLayout;
	}
	public String getPltNazSheetNo() {
		return pltNazSheetNo;
	}
	public void setPltNazSheetNo(String pltNazSheetNo) {
		this.pltNazSheetNo = pltNazSheetNo;
	}
	public String getPltPlotNo() {
		return pltPlotNo;
	}
	public void setPltPlotNo(String pltPlotNo) {
		this.pltPlotNo = pltPlotNo;
	}
	public String getPltNorthboundryDtls() {
		return pltNorthboundryDtls;
	}
	public void setPltNorthboundryDtls(String pltNorthboundryDtls) {
		this.pltNorthboundryDtls = pltNorthboundryDtls;
	}
	public String getPltSouthboundryDtls() {
		return pltSouthboundryDtls;
	}
	public void setPltSouthboundryDtls(String pltSouthboundryDtls) {
		this.pltSouthboundryDtls = pltSouthboundryDtls;
	}
	public String getPltEastboundryDtls() {
		return pltEastboundryDtls;
	}
	public void setPltEastboundryDtls(String pltEastboundryDtls) {
		this.pltEastboundryDtls = pltEastboundryDtls;
	}
	public String getPltWestboundryDtls() {
		return pltWestboundryDtls;
	}
	public void setPltWestboundryDtls(String pltWestboundryDtls) {
		this.pltWestboundryDtls = pltWestboundryDtls;
	}
	public String getPltSize() {
		return pltSize;
	}
	public void setPltSize(String pltSize) {
		this.pltSize = pltSize;
	}
	public String getPltArea() {
		return pltArea;
	}
	public void setPltArea(String pltArea) {
		this.pltArea = pltArea;
	}
	public String getPltCorner() {
		return pltCorner;
	}
	public void setPltCorner(String pltCorner) {
		this.pltCorner = pltCorner;
	}
	public String getPltResiCom() {
		return pltResiCom;
	}
	public void setPltResiCom(String pltResiCom) {
		this.pltResiCom = pltResiCom;
	}
	public String getBuildViKhand() {
		return buildViKhand;
	}
	public void setBuildViKhand(String buildViKhand) {
		this.buildViKhand = buildViKhand;
	}
	public String getBuildRicircle() {
		return buildRicircle;
	}
	public void setBuildRicircle(String buildRicircle) {
		this.buildRicircle = buildRicircle;
	}
	public String getBuildPatwari() {
		return buildPatwari;
	}
	public void setBuildPatwari(String buildPatwari) {
		this.buildPatwari = buildPatwari;
	}
	public String getBuildGram() {
		return buildGram;
	}
	public void setBuildGram(String buildGram) {
		this.buildGram = buildGram;
	}
	public String getBuildMohalla() {
		return buildMohalla;
	}
	public void setBuildMohalla(String buildMohalla) {
		this.buildMohalla = buildMohalla;
	}
	public String getBuildKhasraNo() {
		return buildKhasraNo;
	}
	public void setBuildKhasraNo(String buildKhasraNo) {
		this.buildKhasraNo = buildKhasraNo;
	}
	public String getBuildLayout() {
		return buildLayout;
	}
	public void setBuildLayout(String buildLayout) {
		this.buildLayout = buildLayout;
	}
	public String getBuildNazSheetNo() {
		return buildNazSheetNo;
	}
	public void setBuildNazSheetNo(String buildNazSheetNo) {
		this.buildNazSheetNo = buildNazSheetNo;
	}
	public String getBuildPlotNo() {
		return buildPlotNo;
	}
	public void setBuildPlotNo(String buildPlotNo) {
		this.buildPlotNo = buildPlotNo;
	}
	public String getBuilsPlotArea() {
		return builsPlotArea;
	}
	public void setBuilsPlotArea(String builsPlotArea) {
		this.builsPlotArea = builsPlotArea;
	}
	public String getBuildResiCom() {
		return buildResiCom;
	}
	public void setBuildResiCom(String buildResiCom) {
		this.buildResiCom = buildResiCom;
	}
	public String getBuildNoOfFloors() {
		return buildNoOfFloors;
	}
	public void setBuildNoOfFloors(String buildNoOfFloors) {
		this.buildNoOfFloors = buildNoOfFloors;
	}
	public String getBuildFloorType() {
		return buildFloorType;
	}
	public void setBuildFloorType(String buildFloorType) {
		this.buildFloorType = buildFloorType;
	}
	public String getBuildSize() {
		return buildSize;
	}
	public void setBuildSize(String buildSize) {
		this.buildSize = buildSize;
	}
	public String getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(String buildArea) {
		this.buildArea = buildArea;
	}
	public String getBuildingNoAndShopOffice() {
		return buildingNoAndShopOffice;
	}
	public void setBuildingNoAndShopOffice(String buildingNoAndShopOffice) {
		this.buildingNoAndShopOffice = buildingNoAndShopOffice;
	}
	public String getBuildCeilingType() {
		return buildCeilingType;
	}
	public void setBuildCeilingType(String buildCeilingType) {
		this.buildCeilingType = buildCeilingType;
	}
	public String getBuildNorthboundryDtls() {
		return buildNorthboundryDtls;
	}
	public void setBuildNorthboundryDtls(String buildNorthboundryDtls) {
		this.buildNorthboundryDtls = buildNorthboundryDtls;
	}
	public String getBuildSouthboundryDtls() {
		return buildSouthboundryDtls;
	}
	public void setBuildSouthboundryDtls(String buildSouthboundryDtls) {
		this.buildSouthboundryDtls = buildSouthboundryDtls;
	}
	public String getBuildEastboundryDtls() {
		return buildEastboundryDtls;
	}
	public void setBuildEastboundryDtls(String buildEastboundryDtls) {
		this.buildEastboundryDtls = buildEastboundryDtls;
	}
	public String getBuildWestboundryDtls() {
		return buildWestboundryDtls;
	}
	public void setBuildWestboundryDtls(String buildWestboundryDtls) {
		this.buildWestboundryDtls = buildWestboundryDtls;
	}
	public String getPropPatwariName() {
		return propPatwariName;
	}
	public void setPropPatwariName(String propPatwariName) {
		this.propPatwariName = propPatwariName;
	}
	public String getPropPatwariId() {
		return propPatwariId;
	}
	public void setPropPatwariId(String propPatwariId) {
		this.propPatwariId = propPatwariId;
	}
	public ArrayList getPropPatwariList() {
		return propPatwariList;
	}
	public void setPropPatwariList(ArrayList propPatwariList) {
		this.propPatwariList = propPatwariList;
	}
	public String getPropTehesilName() {
		return propTehesilName;
	}
	public void setPropTehesilName(String propTehesilName) {
		this.propTehesilName = propTehesilName;
	}
	public String getPropTehesilId() {
		return propTehesilId;
	}
	public void setPropTehesilId(String propTehesilId) {
		this.propTehesilId = propTehesilId;
	}
	public ArrayList getPropTehesilList() {
		return propTehesilList;
	}
	public void setPropTehesilList(ArrayList propTehesilList) {
		this.propTehesilList = propTehesilList;
	}

    
	public String getPropDistrictName() {
		return propDistrictName;
	}
	public void setPropDistrictName(String propDistrictName) {
		this.propDistrictName = propDistrictName;
	}
	public String getPropDistrictId() {
		return propDistrictId;
	}
	public void setPropDistrictId(String propDistrictId) {
		this.propDistrictId = propDistrictId;
	}
	public ArrayList getPropDistrictList() {
		return propDistrictList;
	}
	public void setPropDistrictList(ArrayList propDistrictList) {
		this.propDistrictList = propDistrictList;
	}
	public String getStatePropName() {
		return statePropName;
	}
	public void setStatePropName(String statePropName) {
		this.statePropName = statePropName;
	}
	public String getStatePropId() {
		return statePropId;
	}
	public void setStatePropId(String statePropId) {
		this.statePropId = statePropId;
	}
	public ArrayList getStatePropList() {
		return statePropList;
	}
	public void setStatePropList(ArrayList statePropList) {
		this.statePropList = statePropList;
	}
	public String getPropCountryName() {
		return propCountryName;
	}
	public void setPropCountryName(String propCountryName) {
		this.propCountryName = propCountryName;
	}
	public String getPropCountryId() {
		return propCountryId;
	}
	public void setPropCountryId(String propCountryId) {
		this.propCountryId = propCountryId;
	}
	public ArrayList getPropCountryList() {
		return propCountryList;
	}
	public void setPropCountryList(ArrayList propCountryList) {
		this.propCountryList = propCountryList;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public ArrayList getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(ArrayList propertyList) {
		this.propertyList = propertyList;
	}
	/**
	 * @return the totalFee
	 */
	public String getTotalFee() {
		return totalFee;
	}
	/**
	 * @param totalFee the totalFee to set
	 */
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * @return the serviceFee
	 */
	public String getServiceFee() {
		return serviceFee;
	}
	/**
	 * @param serviceFee the serviceFee to set
	 */
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	/**
	 * @return the otherFee
	 */
	public String getOtherFee() {
		return otherFee;
	}
	/**
	 * @param otherFee the otherFee to set
	 */
	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}
	/**
	 * @return the propertyLockFlag
	 */
	public String getPropertyLockFlag() {
		return propertyLockFlag;
	}
	/**
	 * @param propertyLockFlag the propertyLockFlag to set
	 */
	public void setPropertyLockFlag(String propertyLockFlag) {
		this.propertyLockFlag = propertyLockFlag;
	}
	/**
	 * @return the caveatFlag
	 */
	public String getCaveatFlag() {
		return caveatFlag;
	}
	/**
	 * @param caveatFlag the caveatFlag to set
	 */
	public void setCaveatFlag(String caveatFlag) {
		this.caveatFlag = caveatFlag;
	}
	/**
	 * @return the caseFlag
	 */
	public String getCaseFlag() {
		return caseFlag;
	}
	/**
	 * @param caseFlag the caseFlag to set
	 */
	public void setCaseFlag(String caseFlag) {
		this.caseFlag = caseFlag;
	}
	/**
	 * @return the poaFlag
	 */
	public String getPoaFlag() {
		return poaFlag;
	}
	/**
	 * @param poaFlag the poaFlag to set
	 */
	public void setPoaFlag(String poaFlag) {
		this.poaFlag = poaFlag;
	}
	/**
	 * @return the courtOrderFlag
	 */
	public String getCourtOrderFlag() {
		return courtOrderFlag;
	}
	/**
	 * @param courtOrderFlag the courtOrderFlag to set
	 */
	public void setCourtOrderFlag(String courtOrderFlag) {
		this.courtOrderFlag = courtOrderFlag;
	}
	//End of Flag Variables
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
	 * @return the casteId
	 */
	public String getCasteId() {
		return casteId;
	}
	/**
	 * @param casteId the casteId to set
	 */
	public void setCasteId(String casteId) {
		this.casteId = casteId;
	}
	/**
	 * @return the casteName
	 */
	public String getCasteName() {
		return casteName;
	}
	/**
	 * @param casteName the casteName to set
	 */
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	/**
	 * @return the casteList
	 */
	public ArrayList getCasteList() {
		return casteList;
	}
	/**
	 * @param casteList the casteList to set
	 */
	public void setCasteList(ArrayList casteList) {
		this.casteList = casteList;
	}
	/**
	 * @return the religionId
	 */
	public String getReligionId() {
		return religionId;
	}
	/**
	 * @param religionId the religionId to set
	 */
	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}
	/**
	 * @return the religionName
	 */
	public String getReligionName() {
		return religionName;
	}
	/**
	 * @param religionName the religionName to set
	 */
	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}
	/**
	 * @return the religionList
	 */
	public ArrayList getReligionList() {
		return religionList;
	}
	/**
	 * @param religionList the religionList to set
	 */
	public void setReligionList(ArrayList religionList) {
		this.religionList = religionList;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the phyChallanged
	 */
	public String getPhyChallanged() {
		return phyChallanged;
	}
	/**
	 * @param phyChallanged the phyChallanged to set
	 */
	public void setPhyChallanged(String phyChallanged) {
		this.phyChallanged = phyChallanged;
	}
	/**
	 * @return the address
	 */
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
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
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
	 * @return the photoProofTypeName
	 */
	public String getPhotoProofTypeName() {
		return photoProofTypeName;
	}
	/**
	 * @param photoProofTypeName the photoProofTypeName to set
	 */
	public void setPhotoProofTypeName(String photoProofTypeName) {
		this.photoProofTypeName = photoProofTypeName;
	}
	/**
	 * @return the photoProofTypeId
	 */
	public String getPhotoProofTypeId() {
		return photoProofTypeId;
	}
	/**
	 * @param photoProofTypeId the photoProofTypeId to set
	 */
	public void setPhotoProofTypeId(String photoProofTypeId) {
		this.photoProofTypeId = photoProofTypeId;
	}
	/**
	 * @return the photoId
	 */
	public String getPhotoId() {
		return photoId;
	}
	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	/**
	 * @return the photoIdList
	 */
	public ArrayList getPhotoIdList() {
		return photoIdList;
	}
	/**
	 * @param photoIdList the photoIdList to set
	 */
	public void setPhotoIdList(ArrayList photoIdList) {
		this.photoIdList = photoIdList;
	}
	
	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
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
	 * @return the regNo
	 */
	public String getRegNo() {
		return regNo;
	}
	/**
	 * @param regNo the regNo to set
	 */
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	/**
	 * @return the regNoSearch
	 */
	public String getRegNoSearch() {
		return regNoSearch;
	}
	/**
	 * @param regNoSearch the regNoSearch to set
	 */
	public void setRegNoSearch(String regNoSearch) {
		this.regNoSearch = regNoSearch;
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
	 * @return the photoBankName
	 */
	public String getPhotoBankName() {
		return photoBankName;
	}
	/**
	 * @param photoBankName the photoBankName to set
	 */
	public void setPhotoBankName(String photoBankName) {
		this.photoBankName = photoBankName;
	}
	/**
	 * @return the photoBankAddress
	 */
	public String getPhotoBankAddress() {
		return photoBankAddress;
	}
	/**
	 * @param photoBankAddress the photoBankAddress to set
	 */
	public void setPhotoBankAddress(String photoBankAddress) {
		this.photoBankAddress = photoBankAddress;
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
	public String getRegUserId() {
		return regUserId;
	}
	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}
	/**
	 * @return the useIdFlag
	 */
	public String getUseIdFlag() {
		return useIdFlag;
	}
	/**
	 * @param useIdFlag the useIdFlag to set
	 */
	public void setUseIdFlag(String useIdFlag) {
		this.useIdFlag = useIdFlag;
	}
	/**
	 * @return the userMode
	 */
	public String getUserMode() {
		return userMode;
	}
	/**
	 * @param userMode the userMode to set
	 */
	public void setUserMode(String userMode) {
		this.userMode = userMode;
	}
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
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the cerCopyTxnId
	 */
	public String getCerCopyTxnId() {
		return cerCopyTxnId;
	}
	/**
	 * @param cerCopyTxnId the cerCopyTxnId to set
	 */
	public void setCerCopyTxnId(String cerCopyTxnId) {
		this.cerCopyTxnId = cerCopyTxnId;
	}
	/**
	 * @return the cerCopyTxnIdSearch
	 */
	public String getCerCopyTxnIdSearch() {
		return cerCopyTxnIdSearch;
	}
	/**
	 * @param cerCopyTxnIdSearch the cerCopyTxnIdSearch to set
	 */
	public void setCerCopyTxnIdSearch(String cerCopyTxnIdSearch) {
		this.cerCopyTxnIdSearch = cerCopyTxnIdSearch;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the payTxnId
	 */
	public String getPayTxnId() {
		return payTxnId;
	}
	/**
	 * @param payTxnId the payTxnId to set
	 */
	public void setPayTxnId(String payTxnId) {
		this.payTxnId = payTxnId;
	}
	public void setAttachedDoc(FormFile attachedDoc) {
		this.attachedDoc = attachedDoc;
	}
	public FormFile getAttachedDoc() {
		return attachedDoc;
	}
	public void setDocContents(byte[] docContents) {
		this.docContents = docContents;
	}
	public byte[] getDocContents() {
		return docContents;
	}
	public void setDocFileSize(String docFileSize) {
		this.docFileSize = docFileSize;
	}
	public String getDocFileSize() {
		return docFileSize;
	}
	public void setUploaded_doc_path(String uploaded_doc_path) {
		this.uploaded_doc_path = uploaded_doc_path;
	}
	public String getUploaded_doc_path() {
		return uploaded_doc_path;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setDocumentSavePath(String documentSavePath) {
		this.documentSavePath = documentSavePath;
	}
	public String getDocumentSavePath() {
		return documentSavePath;
	}
	
		    
}