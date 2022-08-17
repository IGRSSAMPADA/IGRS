package com.wipro.igrs.newreginit.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.newPropvaluation.dto.SampadaKhasraClrDTO;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.dto.PropertyValuationDTO;
import com.wipro.igrs.newreginit.dto.RegCompletDTO;

/**
 * @author Hari Krishna G.V
 *
 */
public class RegCompletionForm extends ActionForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HashMap mapBuilding = new HashMap();
	
	private String mpcstAvailFlag;
	RegCompletDTO regcompletDto=new RegCompletDTO();
	private String propertyTxnId;
	private String formName = "";
	private String actionName = "";
	ArrayList khasraAreaList=new ArrayList();
	//following added by roopam
	RegCommonForm regInitForm=new RegCommonForm();
	private String khasraNoArray;
	private String khasraAreaArray;
	private String lagaanArray;
	private String rinPustikaArray;
	
	private String northArray;
	private String southArray;
	private String eastArray;
	private String westArray;
	
	private String hidnRegTxnId;
	private String propertyId;
	private int deedID;
	
	private int propertySelected=0;
	
	private ArrayList exchangePropertyList=new ArrayList();
	private ArrayList<String> exchangeAgriPropertyListDisp=new ArrayList<String>();
	private int allPropertiesAdded=0;
	private int isPropertyRemaining=0;
	
	private ArrayList selectedSubClauseList = new ArrayList();
	
	
	//added by shruti
	private ArrayList propTypeL1List=new ArrayList();
	private int propTypeL1Id;
	private String propTypeL1;
	private String landMeterId;
	private String landMeterType;
	//private String PropertyId;  
	//end of code by shruti
	//ADDED BY ROOPAM
	private ArrayList propTypeL2List=new ArrayList();
	private int propTypeL2Id;
	private String propTypeL2;
	private ArrayList floorTypeList=new ArrayList();
	private ArrayList unitList=new ArrayList();
	private String forwardJsp;
	//added by SHREERAJ
	private int fromAdjudication=0;
	//private int fromPropertyForm=0;
	
	private String mapAllPropsParties="";
	private String hdnMapAllPropsPartiesCheck="";
	private int propListInitialSize=0;
	
	private HashMap propWithMapping=new HashMap();
	private HashMap propWithoutMapping=new HashMap();
	private int instID;
	private String userID;
	private String checkParty;
	private String[] shareOfProp;
	private String checkProp;
	private String[] declareShare;
	private String[] mappingArray;
	
	private String IGRS_DATA_AVLBL;
	private String imgHash1;
	private String imgHash2;
	
	
	
	
	public String getMpcstAvailFlag() {
		return mpcstAvailFlag;
	}
	public void setMpcstAvailFlag(String mpcstAvailFlag) {
		this.mpcstAvailFlag = mpcstAvailFlag;
	}
	public String getImgHash1() {
		return imgHash1;
	}
	public void setImgHash1(String imgHash1) {
		this.imgHash1 = imgHash1;
	}
	public String getImgHash2() {
		return imgHash2;
	}
	public void setImgHash2(String imgHash2) {
		this.imgHash2 = imgHash2;
	}
	public String getIGRS_DATA_AVLBL() {
		return IGRS_DATA_AVLBL;
	}
	public void setIGRS_DATA_AVLBL(String igrs_data_avlbl) {
		IGRS_DATA_AVLBL = igrs_data_avlbl;
	}
	public String getNorthArray() {
		return northArray;
	}
	public void setNorthArray(String northArray) {
		this.northArray = northArray;
	}
	public String getSouthArray() {
		return southArray;
	}
	public void setSouthArray(String southArray) {
		this.southArray = southArray;
	}
	public String getEastArray() {
		return eastArray;
	}
	public void setEastArray(String eastArray) {
		this.eastArray = eastArray;
	}
	public String getWestArray() {
		return westArray;
	}
	public void setWestArray(String westArray) {
		this.westArray = westArray;
	}
	public String[] getShareOfProp() {
		return shareOfProp;
	}
	public void setShareOfProp(String[] shareOfProp) {
		this.shareOfProp = shareOfProp;
	}
	public String getDeclareShare(int index) {
		return declareShare[index];
	}
	public void setDeclareShare(int index, String value) {
		this.declareShare[index] = value;
	}
	
	public String getCheckProp() {
		return checkProp;
	}
	public void setCheckProp(String checkProp) {
		this.checkProp = checkProp;
	}
	
	public String getCheckParty() {
		return checkParty;
	}
	public void setCheckParty(String checkParty) {
		this.checkParty = checkParty;
	}
	
	public String getUserID() {
		return userID;
	}







	public void setUserID(String userID) {
		this.userID = userID;
	}







	public int getInstID() {
		return instID;
	}







	public void setInstID(int instID) {
		this.instID = instID;
	}







	public HashMap getPropWithMapping() {
		return propWithMapping;
	}







	public void setPropWithMapping(HashMap propWithMapping) {
		this.propWithMapping = propWithMapping;
	}







	public HashMap getPropWithoutMapping() {
		return propWithoutMapping;
	}







	public void setPropWithoutMapping(HashMap propWithoutMapping) {
		this.propWithoutMapping = propWithoutMapping;
	}







	public int getPropListInitialSize() {
		return propListInitialSize;
	}







	public void setPropListInitialSize(int propListInitialSize) {
		this.propListInitialSize = propListInitialSize;
	}







	public String getMapAllPropsParties() {
		return mapAllPropsParties;
	}







	public void setMapAllPropsParties(String mapAllPropsParties) {
		this.mapAllPropsParties = mapAllPropsParties;
	}







	public String getHdnMapAllPropsPartiesCheck() {
		return hdnMapAllPropsPartiesCheck;
	}







	public void setHdnMapAllPropsPartiesCheck(String hdnMapAllPropsPartiesCheck) {
		this.hdnMapAllPropsPartiesCheck = hdnMapAllPropsPartiesCheck;
	}







	public int getFromAdjudication() {
		return fromAdjudication;
	}







	public ArrayList getExchangeAgriPropertyListDisp() {
		return exchangeAgriPropertyListDisp;
	}







	public void setExchangeAgriPropertyListDisp(
			ArrayList exchangeAgriPropertyListDisp) {
		this.exchangeAgriPropertyListDisp = exchangeAgriPropertyListDisp;
	}







	public void setFromAdjudication(int fromAdjudication) {
		this.fromAdjudication = fromAdjudication;
	}

	/*public int getFromPropertyForm() {
		return fromPropertyForm;
	}







	public void setFromPropertyForm(int fromPropertyForm) {
		this.fromPropertyForm = fromPropertyForm;
	}







	


*/




	public String getForwardJsp() {
		return forwardJsp;
	}







	public void setForwardJsp(String forwardJsp) {
		this.forwardJsp = forwardJsp;
	}







	public ArrayList getPropTypeL2List() {
		return propTypeL2List;
	}







	public ArrayList getFloorTypeList() {
		return floorTypeList;
	}







	public void setFloorTypeList(ArrayList floorTypeList) {
		this.floorTypeList = floorTypeList;
	}







	public ArrayList getUnitList() {
		return unitList;
	}







	public void setUnitList(ArrayList unitList) {
		this.unitList = unitList;
	}







	public void setPropTypeL2List(ArrayList propTypeL2List) {
		this.propTypeL2List = propTypeL2List;
	}







	public int getPropTypeL2Id() {
		return propTypeL2Id;
	}







	public void setPropTypeL2Id(int propTypeL2Id) {
		this.propTypeL2Id = propTypeL2Id;
	}







	public String getPropTypeL2() {
		return propTypeL2;
	}







	public void setPropTypeL2(String propTypeL2) {
		this.propTypeL2 = propTypeL2;
	}







	/*public String getPropertyId() {
		return PropertyId;
	}
*/
	public String getHidnRegTxnId() {
		return hidnRegTxnId;
	}

	

	



	public int getDeedID() {
		return deedID;
	}







	public void setDeedID(int deedID) {
		this.deedID = deedID;
	}







	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public void setHidnRegTxnId(String hidnRegTxnId) {
		this.hidnRegTxnId = hidnRegTxnId;
	}

	/*public void setPropertyId(String propertyId) {
		PropertyId = propertyId;
	}*/

	public ArrayList getSelectedSubClauseList() {
		return selectedSubClauseList;
	}

	public ArrayList getPropTypeL1List() {
		return propTypeL1List;
	}

	public void setPropTypeL1List(ArrayList propTypeL1List) {
		this.propTypeL1List = propTypeL1List;
	}

	public int getPropTypeL1Id() {
		return propTypeL1Id;
	}

	public void setPropTypeL1Id(int propTypeL1Id) {
		this.propTypeL1Id = propTypeL1Id;
	}

	public String getPropTypeL1() {
		return propTypeL1;
	}

	public void setPropTypeL1(String propTypeL1) {
		this.propTypeL1 = propTypeL1;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setSelectedSubClauseList(ArrayList selectedSubClauseList) {
		this.selectedSubClauseList = selectedSubClauseList;
	}

	public int getIsPropertyRemaining() {
		return isPropertyRemaining;
	}

	public void setIsPropertyRemaining(int isPropertyRemaining) {
		this.isPropertyRemaining = isPropertyRemaining;
	}

	public int getAllPropertiesAdded() {
		return allPropertiesAdded;
	}

	public void setAllPropertiesAdded(int allPropertiesAdded) {
		this.allPropertiesAdded = allPropertiesAdded;
	}

	public ArrayList getExchangePropertyList() {
		return exchangePropertyList;
	}

	public void setExchangePropertyList(ArrayList exchangePropertyList) {
		this.exchangePropertyList = exchangePropertyList;
	}

	public int getPropertySelected() {
		return propertySelected;
	}

	public void setPropertySelected(int propertySelected) {
		this.propertySelected = propertySelected;
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

	public ArrayList getKhasraAreaList() {
		return khasraAreaList;
	}

	public void setKhasraAreaList(ArrayList khasraAreaList) {
		this.khasraAreaList = khasraAreaList;
	}

	public RegCommonForm getRegInitForm() {
		return regInitForm;
	}

	public void setRegInitForm(RegCommonForm regInitForm) {
		this.regInitForm = regInitForm;
	}

	/**
	 *@author Madan Mohan
	 */
	private ArrayList selectedSubclause =
		new ArrayList ();
	/**
	 * @author Madan Mohan
	 */
	private HashMap selectedMap = new HashMap();
	
	/**
	 * @author Madan Mohan
	 */
	private ArrayList distList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList propertyType = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList municipalList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList tehsilList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList areaTypeList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList wardList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList patwariList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList plotResidentList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList subClauseList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList mahallaList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList landTypeList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList floorList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList ceilingList = new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private String buildingShow = "null"; 
	/**
	 * @author Madan Mohan
	 */
	private ArrayList listBuilding = new ArrayList();
	private HashMap mapBuildingDetails = new HashMap(); 
	
	private String hdnExtSubClause = "";
	private String hdnSubClauseName = "";
	
	private PropertyValuationDTO propertyDTO 
						= new PropertyValuationDTO();
	
	
	/**
	 * @author Ankita
	 */
	
	//Start:==== for property details
	private String areaType;
	private int areaId;
	private Integer districtID;
	private String district;
	private Integer tehsilID;
	private String tehsil;
	private int propertyTypeID;
	private String propertyTypeId;
	private String propertyType1;
	private String municipalBody;
	private int municipalBodyID;
	private String mahalla;
	private int mahallaId;
	private int wardId;
	private int numfloors;
	private double totalSqMeter;
	private String totalSqMeterDisplay;
	private String patwariStatus;
	private String unit;
	private String gram;
	private ArrayList subCls = new ArrayList();
	
	private int addMoreCounter=0;
	private HashMap mapProperty = new HashMap();
	private String deletePropertyID;
	
	private String modifyKhasraList;
	
	private String valuationId;
	
	
	
	
	public void setShareOfPropSize(int size) {
		this.shareOfProp = new String[size];
	}
	public int getShareOfPropSize() {
		return this.shareOfProp.length;
	}
	public void setDeclareShareSize(int size) {
		this.declareShare = new String[size];
	}
	public int getDeclareShareSize() {
		return this.declareShare.length;
	}
	public String[] getMappingArray() {
		return mappingArray;
	}
	public void setMappingArray(String[] mappingArray) {
		this.mappingArray = mappingArray;
	}
	
	public String getValuationId() {
		return valuationId;
	}







	public void setValuationId(String valuationId) {
		this.valuationId = valuationId;
	}







	public String getPropertyTypeId() {
		return propertyTypeId;
	}







	public void setPropertyTypeId(String propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}







	public String getTotalSqMeterDisplay() {
		return totalSqMeterDisplay;
	}







	public void setTotalSqMeterDisplay(String totalSqMeterDisplay) {
		this.totalSqMeterDisplay = totalSqMeterDisplay;
	}







	public String getModifyKhasraList() {
		return modifyKhasraList;
	}







	public void setModifyKhasraList(String modifyKhasraList) {
		this.modifyKhasraList = modifyKhasraList;
	}







	public int getAddMoreCounter() {
		return addMoreCounter;
	}







	public String getDeletePropertyID() {
		return deletePropertyID;
	}







	public void setDeletePropertyID(String deletePropertyID) {
		this.deletePropertyID = deletePropertyID;
	}







	public HashMap getMapProperty() {
		return mapProperty;
	}







	public void setMapProperty(HashMap mapProperty) {
		this.mapProperty = mapProperty;
	}







	public void setAddMoreCounter(int addMoreCounter) {
		this.addMoreCounter = addMoreCounter;
	}







	public ArrayList getSubCls() {
		return subCls;
	}

	public void setSubCls(ArrayList subCls) {
		this.subCls = subCls;
	}

	public String getGram() {
		return gram;
	}

	public void setGram(String gram) {
		this.gram = gram;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPatwariStatus() {
		return patwariStatus;
	}

	public void setPatwariStatus(String patwariStatus) {
		this.patwariStatus = patwariStatus;
	}

	public double getTotalSqMeter() {
		return totalSqMeter;
	}

	public void setTotalSqMeter(double totalSqMeter) {
		this.totalSqMeter = totalSqMeter;
	}

	public int getNumfloors() {
		return numfloors;
	}

	public void setNumfloors(int numfloors) {
		this.numfloors = numfloors;
	}

	private String ward;
	private HashMap mapBuildingDetails1 = new HashMap(); 
	public HashMap getMapBuildingDetails1() {
		return mapBuildingDetails1;
	}

	public void setMapBuildingDetails1(HashMap mapBuildingDetails1) {
		this.mapBuildingDetails1 = mapBuildingDetails1;
	}

	public int getWardId() {
		return wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getMahalla() {
		return mahalla;
	}

	public void setMahalla(String mahalla) {
		this.mahalla = mahalla;
	}

	public int getMahallaId() {
		return mahallaId;
	}

	public void setMahallaId(int mahallaId) {
		this.mahallaId = mahallaId;
	}

	public String getMunicipalBody() {
		return municipalBody;
	}

	public void setMunicipalBody(String municipalBody) {
		this.municipalBody = municipalBody;
	}

	public int getMunicipalBodyID() {
		return municipalBodyID;
	}

	public void setMunicipalBodyID(int municipalBodyID) {
		this.municipalBodyID = municipalBodyID;
	}

	public int getPropertyTypeID() {
		return propertyTypeID;
	}

	public void setPropertyTypeID(int propertyTypeID) {
		this.propertyTypeID = propertyTypeID;
	}

	public String getPropertyType1() {
		return propertyType1;
	}

	public void setPropertyType1(String propertyType1) {
		this.propertyType1 = propertyType1;
	}

	public Integer getTehsilID() {
		return tehsilID;
	}

	public void setTehsilID(Integer tehsilID) {
		this.tehsilID = tehsilID;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public Integer getDistrictID() {
		return districtID;
	}

	public void setDistrictID(Integer districtID) {
		this.districtID = districtID;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	
	
	//End:==== for property details
	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
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

	/**
	 * @return
	 */
	public RegCompletDTO getRegcompletDto() {
		return regcompletDto;
	}

	/**
	 * @param regcompletDto
	 */
	public void setRegcompletDto(RegCompletDTO regcompletDto) {
		this.regcompletDto = regcompletDto;
	}

	public HashMap getMapBuilding() {
		return mapBuilding;
	}

	public void setMapBuilding(HashMap mapBuilding) {
		this.mapBuilding = mapBuilding;
	}

	public PropertyValuationDTO getPropertyValuation() {
		return propertyDTO;
	}

	public void setPropertyValuation(PropertyValuationDTO propertyDTO) {
		this.propertyDTO = propertyDTO;
	}

	public ArrayList getSelectedSubclause() {
		return selectedSubclause;
	}

	public void setSelectedSubclause(ArrayList selectedSubclause) {
		this.selectedSubclause = selectedSubclause;
	}

	public HashMap getSelectedMap() {
		return selectedMap;
	}

	public void setSelectedMap(HashMap selectedMap) {
		this.selectedMap = selectedMap;
	}

	public ArrayList getDistList() {
		return distList;
	}

	public void setDistList(ArrayList distList) {
		this.distList = distList;
	}

	public ArrayList getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(ArrayList propertyType) {
		this.propertyType = propertyType;
	}

	public ArrayList getMunicipalList() {
		return municipalList;
	}

	public void setMunicipalList(ArrayList municipalList) {
		this.municipalList = municipalList;
	}

	public ArrayList getTehsilList() {
		return tehsilList;
	}

	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}

	public ArrayList getAreaTypeList() {
		return areaTypeList;
	}

	public void setAreaTypeList(ArrayList areaTypeList) {
		this.areaTypeList = areaTypeList;
	}

	public ArrayList getWardList() {
		return wardList;
	}

	public void setWardList(ArrayList wardList) {
		this.wardList = wardList;
	}

	public ArrayList getPatwariList() {
		return patwariList;
	}

	public void setPatwariList(ArrayList patwariList) {
		this.patwariList = patwariList;
	}

	public ArrayList getPlotResidentList() {
		return plotResidentList;
	}

	public void setPlotResidentList(ArrayList plotResidentList) {
		this.plotResidentList = plotResidentList;
	}

	public ArrayList getSubClauseList() {
		return subClauseList;
	}

	public void setSubClauseList(ArrayList subClauseList) {
		this.subClauseList = subClauseList;
	}

	public ArrayList getMahallaList() {
		return mahallaList;
	}

	public void setMahallaList(ArrayList mahallaList) {
		this.mahallaList = mahallaList;
	}

	public ArrayList getLandTypeList() {
		return landTypeList;
	}

	public void setLandTypeList(ArrayList landTypeList) {
		this.landTypeList = landTypeList;
	}

	public ArrayList getFloorList() {
		return floorList;
	}

	public void setFloorList(ArrayList floorList) {
		this.floorList = floorList;
	}

	public ArrayList getCeilingList() {
		return ceilingList;
	}

	public void setCeilingList(ArrayList ceilingList) {
		this.ceilingList = ceilingList;
	}

	public String getBuildingShow() {
		return buildingShow;
	}

	public void setBuildingShow(String buildingShow) {
		this.buildingShow = buildingShow;
	}

	public ArrayList getListBuilding() {
		return listBuilding;
	}

	public void setListBuilding(ArrayList listBuilding) {
		this.listBuilding = listBuilding;
	}

	public HashMap getMapBuildingDetails() {
		return mapBuildingDetails;
	}

	public void setMapBuildingDetails(HashMap mapBuildingDetails) {
		this.mapBuildingDetails = mapBuildingDetails;
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

	public PropertyValuationDTO getPropertyDTO() {
		return propertyDTO;
	}

	public void setPropertyDTO(PropertyValuationDTO propertyDTO) {
		this.propertyDTO = propertyDTO;
	}

	public String getPropertyTxnId() {
		return propertyTxnId;
	}

	public void setPropertyTxnId(String propertyTxnId) {
		this.propertyTxnId = propertyTxnId;
	}	
	private String fileAUNameP;
	private String uniqueUploadIdP;
	
	private FormFile fileAUPathP = null;
	
	public String getFileAUNameP() {
		return fileAUNameP;
	}
	public void setFileAUNameP(String fileAUNameP) {
		this.fileAUNameP = fileAUNameP;
	}
	public String getUniqueUploadIdP() {
		return uniqueUploadIdP;
	}
	public void setUniqueUploadIdP(String uniqueUploadIdP) {
		this.uniqueUploadIdP = uniqueUploadIdP;
	}
	public FormFile getFileAUPathP() {
		return fileAUPathP;
	}
	public void setFileAUPathP(FormFile fileAUPathP) {
		this.fileAUPathP = fileAUPathP;
	}
	private ArrayList<CommonDTO> listDtoP = new ArrayList<CommonDTO>();
	public ArrayList<CommonDTO> getListDtoP() {
		return listDtoP;
	}
	public void setListDtoP(ArrayList<CommonDTO> listDtoP) {
		this.listDtoP = listDtoP;
	}

// clr integrations	
	private String clrFlag;
private String valTxnID;



	public String getValTxnID() {
	return valTxnID;
}
public void setValTxnID(String valTxnID) {
	this.valTxnID = valTxnID;
}
	public String getClrFlag() {
		return clrFlag;
	}
	public void setClrFlag(String clrFlag) {
		this.clrFlag = clrFlag;
	}
	
	// Added by Rakesh for clr integration:Start
	
	

	private ArrayList<RegCompletDTO> clrOwnerArray=new ArrayList<RegCompletDTO>();
	private ArrayList<String> clrKhasraViewDetls=new ArrayList<String>();

	private ArrayList<RegCompletDTO> khasraArrayList=new ArrayList<RegCompletDTO>();


	public ArrayList<RegCompletDTO> getClrOwnerArray() {
		return clrOwnerArray;
	}
	public ArrayList<RegCompletDTO> getKhasraArrayList() {
		return khasraArrayList;
	}
	public void setKhasraArrayList(ArrayList<RegCompletDTO> khasraArrayList) {
		this.khasraArrayList = khasraArrayList;
	}
	public void setClrOwnerArray(ArrayList<RegCompletDTO> clrOwnerArray) {
		this.clrOwnerArray = clrOwnerArray;
	}
	public ArrayList<String> getClrKhasraViewDetls() {
		return clrKhasraViewDetls;
	}
	public void setClrKhasraViewDetls(ArrayList<String> clrKhasraViewDetls) {
		this.clrKhasraViewDetls = clrKhasraViewDetls;
	}
	
	private String clrErrorFlag;
	private String clrError;
	public void setClrError(String clrError) {
		this.clrError = clrError;
	}
	public String getClrError() {
		return clrError;
	}
	public void setClrErrorFlag(String clrErrorFlag) {
		this.clrErrorFlag = clrErrorFlag;
	}
	public String getClrErrorFlag() {
		return clrErrorFlag;
	}
	
	
		
//Added by ankit for plant and machinery
private String movPropFlag;

public String getMovPropFlag() {
	return movPropFlag;
}
public void setMovPropFlag(String movPropFlag) {
	this.movPropFlag = movPropFlag;
}


}
