package com.wipro.igrs.common.dto;


import java.io.Serializable;
import java.util.ArrayList;


public class CommonDTO implements Serializable {


	private ArrayList<CommonDTO>  plotDetailsList=new ArrayList<CommonDTO>();
	private ArrayList<CommonDTO> subClauseList=new ArrayList<CommonDTO>();
	private ArrayList<CommonDTO> agriAreaDetailsList=new ArrayList<CommonDTO>();
	private ArrayList<CommonDTO> agriPropDetailsList=new ArrayList<CommonDTO>();
	private ArrayList<CommonDTO> agriTreeDetailsList=new ArrayList<CommonDTO>();

	//--common varibales--

	private String district;
	private String tehsil;
	private String area;
	private String subArea;
	private String wardPatwari;
	private String colony;
	private String areaId;
	private String subClauseId;
	private String subClauseName;
	private String subClauseChkd;
	private String agriTreeChkd;
	CommonDTO cmDTO;
	//---end or common addition

	//--variables for plot view details---
	private String commonPlotTotalArea;
	private String commonPlotResidentialArea;
	private String commonPlotCommercialArea;
	private String commonPlotIndustrialArea;
	private String commonPlotEducationalArea;
	private String commonPlotHealthArea;
	private String commonPlotOtherArea;
	private String commonEducTncp;
	private String commonHealthTncp;
	private String commonResiFlag;
	private String commonResiArea;

	//-----end of addition---

	//--variables for agri view details---
	private String commonAgriSingleBuyer;
	private String commonAgriSameFamily;
	private String commonAgriBuyerCount;
	private String commonAgriTreePresent;
	private String commonAgriDiscloseShare;


	private String commonTotalUndivArea;
	private String commonTotalUnirriOneCrop;
	private String commonTotalUnirriTwoCrop;
	private String commonTotalIrrigatedArea;
	private String commonTotalDivArea;
	private String commonTotalResiArea;
	private String commonTotalCommArea;
	private String commonTotalIndArea;
	private String commonTotalEduArea;
	private String commonTotalHealthArea;
	private String commonTotalOtherArea;
	private String commonAgriSubTypeId;
	private String commonAgriConstruction;
	private String commonAgriEducationTcp;
	private String commonAgriHealthTcp;
	private String commonAgriIsConstruction;

	private String commonAgriPropSubTypeName;
	private String commonAgriPropSubTypeId;
	private String commonAgriTxnid;
	private String commonAgriTreeName;
	private String commonAgriTreeCount;
	private String olderSubClause;// added by akansha for construction cost
	private String onlyResi;
	//private String commonAgriBuildFloorArea;
	//private String commonAgriBuildAtFloor;
	//private String commonAgriBuildOld20;
	//private String commonAgriBuildOld50;
	//private String commonAgriBuildLiftPresent;
	//private String commonAgriBuildTransactOpenTerrace;
	//private String commonAgriBuildTerraceUsage;
	//private String commonAgriBuildTerraceArea;
	//private String commonAgriBuildMultiStroreySubType;
	//private String commonAgriBuildBuiltUpArea;
	//private String commonAgriBuildCommonArea;
	//private String commonAgriBuildCommPropType;
	//private String commonAgriBuildInMall;
	//private String commonAgriBuildNearRoad;
	//private String commonAgriBuildResiArea;
	//private String commonAgriBuildCommArea;
	//private String commonAgriBuildIndArea;
	//private String commonAgriBuildArea;
	//private String commonAgriBuildResiArea;
	//private String commonAgriBuildResiArea;
	//private String commonAgriBuildResiArea;
	//private String commonAgriBuildResiArea;


	//private String commonAgriBuildPropSubTypeName;

	//private String commonAgriBuildPropSubTypeName;

	//-----end of addition---


	public void setPlotDetailsList(ArrayList<CommonDTO> plotDetailsList) {
		this.plotDetailsList = plotDetailsList;
	}

	public CommonDTO getCmDTO() {
		return cmDTO;
	}

	public void setCmDTO(CommonDTO cmDTO) {
		this.cmDTO = cmDTO;
	}

	public ArrayList<CommonDTO> getPlotDetailsList() {
		return plotDetailsList;
	}

	public void setCommonPlotTotalArea(String commonPlotTotalArea) {
		this.commonPlotTotalArea = commonPlotTotalArea;
	}

	public String getCommonPlotTotalArea() {
		return commonPlotTotalArea;
	}

	public void setCommonPlotResidentialArea(String commonPlotResidentialArea) {
		this.commonPlotResidentialArea = commonPlotResidentialArea;
	}

	public String getCommonPlotResidentialArea() {
		return commonPlotResidentialArea;
	}

	public void setCommonPlotCommercialArea(String commonPlotCommercialArea) {
		this.commonPlotCommercialArea = commonPlotCommercialArea;
	}

	public String getCommonPlotCommercialArea() {
		return commonPlotCommercialArea;
	}

	public void setCommonPlotIndustrialArea(String commonPlotIndustrialArea) {
		this.commonPlotIndustrialArea = commonPlotIndustrialArea;
	}

	public String getCommonPlotIndustrialArea() {
		return commonPlotIndustrialArea;
	}

	public void setCommonPlotEducationalArea(String commonPlotEducationalArea) {
		this.commonPlotEducationalArea = commonPlotEducationalArea;
	}

	public String getCommonPlotEducationalArea() {
		return commonPlotEducationalArea;
	}

	public void setCommonPlotHealthArea(String commonPlotHealthArea) {
		this.commonPlotHealthArea = commonPlotHealthArea;
	}

	public String getCommonPlotHealthArea() {
		return commonPlotHealthArea;
	}

	public void setCommonPlotOtherArea(String commonPlotOtherArea) {
		this.commonPlotOtherArea = commonPlotOtherArea;
	}

	public String getCommonPlotOtherArea() {
		return commonPlotOtherArea;
	}

	public void setCommonEducTncp(String commonEducTncp) {
		this.commonEducTncp = commonEducTncp;
	}

	public String getCommonEducTncp() {
		return commonEducTncp;
	}

	public void setCommonHealthTncp(String commonHealthTncp) {
		this.commonHealthTncp = commonHealthTncp;
	}

	public String getCommonHealthTncp() {
		return commonHealthTncp;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrict() {
		return district;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return area;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	public String getSubArea() {
		return subArea;
	}

	public void setWardPatwari(String wardPatwari) {
		this.wardPatwari = wardPatwari;
	}

	public String getWardPatwari() {
		return wardPatwari;
	}

	public void setColony(String colony) {
		this.colony = colony;
	}

	public String getColony() {
		return colony;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaId() {
		return areaId;
	}
	public void setSubClauseList(ArrayList<CommonDTO> subClauseList) {
		this.subClauseList = subClauseList;
	}

	public ArrayList<CommonDTO> getSubClauseList() {
		return subClauseList;
	}

	public void setSubClauseId(String subClauseId) {
		this.subClauseId = subClauseId;
	}

	public String getSubClauseId() {
		return subClauseId;
	}

	public void setSubClauseName(String subClauseName) {
		this.subClauseName = subClauseName;
	}

	public String getSubClauseName() {
		return subClauseName;
	}

	public void setSubClauseChkd(String subClauseChkd) {
		this.subClauseChkd = subClauseChkd;
	}

	public String getSubClauseChkd() {
		return subClauseChkd;
	}

	public void setAgriAreaDetailsList(ArrayList<CommonDTO> agriAreaDetailsList) {
		this.agriAreaDetailsList = agriAreaDetailsList;
	}

	public ArrayList<CommonDTO> getAgriAreaDetailsList() {
		return agriAreaDetailsList;
	}


	public void setAgriPropDetailsList(ArrayList<CommonDTO> agriPropDetailsList) {
		this.agriPropDetailsList = agriPropDetailsList;
	}

	public ArrayList<CommonDTO> getAgriPropDetailsList() {
		return agriPropDetailsList;
	}

	public void setCommonAgriSingleBuyer(String commonAgriSingleBuyer) {
		this.commonAgriSingleBuyer = commonAgriSingleBuyer;
	}

	public String getCommonAgriSingleBuyer() {
		return commonAgriSingleBuyer;
	}

	public void setCommonAgriSameFamily(String commonAgriSameFamily) {
		this.commonAgriSameFamily = commonAgriSameFamily;
	}

	public String getCommonAgriSameFamily() {
		return commonAgriSameFamily;
	}

	public void setCommonAgriBuyerCount(String commonAgriBuyerCount) {
		this.commonAgriBuyerCount = commonAgriBuyerCount;
	}

	public String getCommonAgriBuyerCount() {
		return commonAgriBuyerCount;
	}

	public void setCommonAgriTreePresent(String commonAgriTreePresent) {
		this.commonAgriTreePresent = commonAgriTreePresent;
	}

	public String getCommonAgriTreePresent() {
		return commonAgriTreePresent;
	}

	public void setCommonAgriDiscloseShare(String commonAgriDiscloseShare) {
		this.commonAgriDiscloseShare = commonAgriDiscloseShare;
	}

	public String getCommonAgriDiscloseShare() {
		return commonAgriDiscloseShare;
	}

	public void setCommonTotalUndivArea(String commonTotalUndivArea) {
		this.commonTotalUndivArea = commonTotalUndivArea;
	}

	public String getCommonTotalUndivArea() {
		return commonTotalUndivArea;
	}

	public void setCommonTotalUnirriOneCrop(String commonTotalUnirriOneCrop) {
		this.commonTotalUnirriOneCrop = commonTotalUnirriOneCrop;
	}

	public String getCommonTotalUnirriOneCrop() {
		return commonTotalUnirriOneCrop;
	}

	public void setCommonTotalUnirriTwoCrop(String commonTotalUnirriTwoCrop) {
		this.commonTotalUnirriTwoCrop = commonTotalUnirriTwoCrop;
	}

	public String getCommonTotalUnirriTwoCrop() {
		return commonTotalUnirriTwoCrop;
	}

	public void setCommonTotalIrrigatedArea(String commonTotalIrrigatedArea) {
		this.commonTotalIrrigatedArea = commonTotalIrrigatedArea;
	}

	public String getCommonTotalIrrigatedArea() {
		return commonTotalIrrigatedArea;
	}

	public void setCommonTotalDivArea(String commonTotalDivArea) {
		this.commonTotalDivArea = commonTotalDivArea;
	}

	public String getCommonTotalDivArea() {
		return commonTotalDivArea;
	}

	public void setCommonTotalResiArea(String commonTotalResiArea) {
		this.commonTotalResiArea = commonTotalResiArea;
	}

	public String getCommonTotalResiArea() {
		return commonTotalResiArea;
	}

	public void setCommonTotalCommArea(String commonTotalCommArea) {
		this.commonTotalCommArea = commonTotalCommArea;
	}

	public String getCommonTotalCommArea() {
		return commonTotalCommArea;
	}

	public void setCommonTotalIndArea(String commonTotalIndArea) {
		this.commonTotalIndArea = commonTotalIndArea;
	}

	public String getCommonTotalIndArea() {
		return commonTotalIndArea;
	}

	public void setCommonTotalEduArea(String commonTotalEduArea) {
		this.commonTotalEduArea = commonTotalEduArea;
	}

	public String getCommonTotalEduArea() {
		return commonTotalEduArea;
	}

	public void setCommonTotalHealthArea(String commonTotalHealthArea) {
		this.commonTotalHealthArea = commonTotalHealthArea;
	}

	public String getCommonTotalHealthArea() {
		return commonTotalHealthArea;
	}

	public void setCommonTotalOtherArea(String commonTotalOtherArea) {
		this.commonTotalOtherArea = commonTotalOtherArea;
	}

	public String getCommonTotalOtherArea() {
		return commonTotalOtherArea;
	}

	public void setCommonAgriSubTypeId(String commonAgriSubTypeId) {
		this.commonAgriSubTypeId = commonAgriSubTypeId;
	}

	public String getCommonAgriSubTypeId() {
		return commonAgriSubTypeId;
	}

	public void setCommonAgriConstruction(String commonAgriConstruction) {
		this.commonAgriConstruction = commonAgriConstruction;
	}

	public String getCommonAgriConstruction() {
		return commonAgriConstruction;
	}

	public void setCommonAgriEducationTcp(String commonAgriEducationTcp) {
		this.commonAgriEducationTcp = commonAgriEducationTcp;
	}

	public String getCommonAgriEducationTcp() {
		return commonAgriEducationTcp;
	}

	public void setCommonAgriHealthTcp(String commonAgriHealthTcp) {
		this.commonAgriHealthTcp = commonAgriHealthTcp;
	}

	public String getCommonAgriHealthTcp() {
		return commonAgriHealthTcp;
	}



	/* addded by vinay Sharma*/
	private String totalArea;


	private String openTerraceUsage;

	private String commonArea;

	private String builtUpArea;

	private String older20;

	private String older50;

	private String nearRoad;

	private String isLiftMall;

	private String buildingTypeId;

	private String buildingTypeName;

	private String multiStoreyTypeId;

	private String multiStoreyTypeName;

	private String multiStoreyUsageName;

	private String floorName;

	private String older;

	private String openTerraceArea;

	private String openTerraceFlag;

	private String resiArea;

	private String commArea;

	private String indArea;

	private String isAkvn;

	private String isSuperConstruction;

	private String isHosingBoard;

	private ArrayList floorAreaList;

	private String rccArea;

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

	public String getFloorPropId() {
		return floorPropId;
	}

	public void setFloorPropId(String floorPropId) {
		this.floorPropId = floorPropId;
	}
	private String rbcArea;

	private String kacchaArea;

	private String tinArea;

	private String shopArea;

	private String officeArea;

	private String godownArea;

	private String floorPropId;

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

	public String getIsHosingBoard() {
		return isHosingBoard;
	}

	public void setIsHosingBoard(String isHosingBoard) {
		this.isHosingBoard = isHosingBoard;
	}

	public String getResiCommArea() {
		return resiCommArea;
	}

	public void setResiCommArea(String resiCommArea) {
		this.resiCommArea = resiCommArea;
	}
	private String schoolArea;

	private String healthArea;

	private String otherArea;

	public String resiCommArea;

	public String eduTCP;

	public String healthTCP;

	public String getEduTCP() {
		return eduTCP;
	}

	public void setEduTCP(String eduTCP) {
		this.eduTCP = eduTCP;
	}

	public String getHealthTCP() {
		return healthTCP;
	}

	public void setHealthTCP(String healthTCP) {
		this.healthTCP = healthTCP;
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

	public String getOtherArea() {
		return otherArea;
	}

	public void setOtherArea(String otherArea) {
		this.otherArea = otherArea;
	}

	public String getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}

	public String getOpenTerraceUsage() {
		return openTerraceUsage;
	}

	public void setOpenTerraceUsage(String openTerraceUsage) {
		this.openTerraceUsage = openTerraceUsage;
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

	public String getOlder20() {
		return older20;
	}

	public void setOlder20(String older20) {
		this.older20 = older20;
	}

	public String getOlder50() {
		return older50;
	}

	public void setOlder50(String older50) {
		this.older50 = older50;
	}

	public String getNearRoad() {
		return nearRoad;
	}

	public void setNearRoad(String nearRoad) {
		this.nearRoad = nearRoad;
	}

	public String getIsLiftMall() {
		return isLiftMall;
	}

	public void setIsLiftMall(String isLiftMall) {
		this.isLiftMall = isLiftMall;
	}

	public String getBuildingTypeId() {
		return buildingTypeId;
	}

	public void setBuildingTypeId(String buildingTypeId) {
		this.buildingTypeId = buildingTypeId;
	}

	public String getBuildingTypeName() {
		return buildingTypeName;
	}

	public void setBuildingTypeName(String buildingTypeName) {
		this.buildingTypeName = buildingTypeName;
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

	public String getMultiStoreyUsageName() {
		return multiStoreyUsageName;
	}

	public void setMultiStoreyUsageName(String multiStoreyUsageName) {
		this.multiStoreyUsageName = multiStoreyUsageName;
	}


	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setOlder(String older) {
		this.older = older;
	}

	public String getOlder() {
		return older;
	}

	public void setOpenTerraceArea(String openTerraceArea) {
		this.openTerraceArea = openTerraceArea;
	}

	public String getOpenTerraceArea() {
		return openTerraceArea;
	}

	public void setOpenTerraceFlag(String openTerraceFlag) {
		this.openTerraceFlag = openTerraceFlag;
	}

	public String getOpenTerraceFlag() {
		return openTerraceFlag;
	}

	public ArrayList getFloorAreaList() {
		return floorAreaList;
	}

	public void setFloorAreaList(ArrayList floorAreaList) {
		this.floorAreaList = floorAreaList;
	}




	public void setCommonAgriIsConstruction(String commonAgriIsConstruction) {
		this.commonAgriIsConstruction = commonAgriIsConstruction;
	}

	public String getCommonAgriIsConstruction() {
		return commonAgriIsConstruction;
	}

	public void setCommonAgriPropSubTypeName(String commonAgriPropSubTypeName) {
		this.commonAgriPropSubTypeName = commonAgriPropSubTypeName;
	}

	public String getCommonAgriPropSubTypeName() {
		return commonAgriPropSubTypeName;
	}

	public void setCommonAgriPropSubTypeId(String commonAgriPropSubTypeId) {
		this.commonAgriPropSubTypeId = commonAgriPropSubTypeId;
	}

	public String getCommonAgriPropSubTypeId() {
		return commonAgriPropSubTypeId;
	}

	public void setCommonAgriTxnid(String commonAgriTxnid) {
		this.commonAgriTxnid = commonAgriTxnid;
	}

	public String getCommonAgriTxnid() {
		return commonAgriTxnid;
	}

	public void setAgriTreeDetailsList(ArrayList<CommonDTO> agriTreeDetailsList) {
		this.agriTreeDetailsList = agriTreeDetailsList;
	}

	public ArrayList<CommonDTO> getAgriTreeDetailsList() {
		return agriTreeDetailsList;
	}

	public void setAgriTreeChkd(String agriTreeChkd) {
		this.agriTreeChkd = agriTreeChkd;
	}

	public String getAgriTreeChkd() {
		return agriTreeChkd;
	}

	public void setCommonAgriTreeName(String commonAgriTreeName) {
		this.commonAgriTreeName = commonAgriTreeName;
	}

	public String getCommonAgriTreeName() {
		return commonAgriTreeName;
	}

	public void setCommonAgriTreeCount(String commonAgriTreeCount) {
		this.commonAgriTreeCount = commonAgriTreeCount;
	}

	public String getCommonAgriTreeCount() {
		return commonAgriTreeCount;
	}

	public void setCommonResiFlag(String commonResiFlag) {
		this.commonResiFlag = commonResiFlag;
	}

	public String getCommonResiFlag() {
		return commonResiFlag;
	}

	public void setCommonResiArea(String commonResiArea) {
		this.commonResiArea = commonResiArea;
	}

	public String getCommonResiArea() {
		return commonResiArea;
	}

	// Added by Rakesh For Prop Value PopupAgriDetails
	private String irregatedAreaClr;
	private String unirreSingleCropClr;
	private String unirreDoubleCropClr;
	private String khasraAreaClr;
	private String khasraNoClr;
	//private String rinPustikaCLr;


	public String getKhasraNoClr() {
		return khasraNoClr;
	}

	public void setKhasraNoClr(String khasraNoClr) {
		this.khasraNoClr = khasraNoClr;
	}

	public String getIrregatedAreaClr() {
		return irregatedAreaClr;
	}

	public void setIrregatedAreaClr(String irregatedAreaClr) {
		this.irregatedAreaClr = irregatedAreaClr;
	}

	public String getUnirreSingleCropClr() {
		return unirreSingleCropClr;
	}

	public void setUnirreSingleCropClr(String unirreSingleCropClr) {
		this.unirreSingleCropClr = unirreSingleCropClr;
	}

	public String getUnirreDoubleCropClr() {
		return unirreDoubleCropClr;
	}

	public void setUnirreDoubleCropClr(String unirreDoubleCropClr) {
		this.unirreDoubleCropClr = unirreDoubleCropClr;
	}

	public String getKhasraAreaClr() {
		return khasraAreaClr;
	}

	public void setKhasraAreaClr(String khasraAreaClr) {
		this.khasraAreaClr = khasraAreaClr;
	}

	// Added by Rakesh For Prop Value PopupAgriDetails Sampada Undiverted

	private String sampadaSingleCropArea;
	private String sampadaDoubleCropArea;
	private String sampadaIrrigatedArea;
	private String sampdaTotalUndivertedArea;


	// Added by Rakesh For Prop Value PopupAgriDetails Sampada diverted

	private String sampadaTotalDivertedArea;
	private String sampadaDivertedResidentialArea;
	private String sampadaDivertedCommercialArea;
	private String sampadaDivertedIndustrialArea;
	private String sampadaDivertedEducationalArea;
	private String sampadaDivertedHealthArea;
	private String sampadaDivertedOthersArea;

	private String isConstructionKhasraNo;


	public String getIsConstructionKhasraNo() {
		return isConstructionKhasraNo;
	}

	public void setIsConstructionKhasraNo(String isConstructionKhasraNo) {
		this.isConstructionKhasraNo = isConstructionKhasraNo;
	}

	public String getSampadaSingleCropArea() {
		return sampadaSingleCropArea;
	}

	public void setSampadaSingleCropArea(String sampadaSingleCropArea) {
		this.sampadaSingleCropArea = sampadaSingleCropArea;
	}

	public String getSampadaDoubleCropArea() {
		return sampadaDoubleCropArea;
	}

	public void setSampadaDoubleCropArea(String sampadaDoubleCropArea) {
		this.sampadaDoubleCropArea = sampadaDoubleCropArea;
	}

	public String getSampadaIrrigatedArea() {
		return sampadaIrrigatedArea;
	}

	public void setSampadaIrrigatedArea(String sampadaIrrigatedArea) {
		this.sampadaIrrigatedArea = sampadaIrrigatedArea;
	}

	public String getSampdaTotalUndivertedArea() {
		return sampdaTotalUndivertedArea;
	}

	public void setSampdaTotalUndivertedArea(String sampdaTotalUndivertedArea) {
		this.sampdaTotalUndivertedArea = sampdaTotalUndivertedArea;
	}

	public String getSampadaTotalDivertedArea() {
		return sampadaTotalDivertedArea;
	}

	public void setSampadaTotalDivertedArea(String sampadaTotalDivertedArea) {
		this.sampadaTotalDivertedArea = sampadaTotalDivertedArea;
	}

	public String getSampadaDivertedResidentialArea() {
		return sampadaDivertedResidentialArea;
	}

	public void setSampadaDivertedResidentialArea(
	String sampadaDivertedResidentialArea) {
		this.sampadaDivertedResidentialArea = sampadaDivertedResidentialArea;
	}

	public String getSampadaDivertedCommercialArea() {
		return sampadaDivertedCommercialArea;
	}

	public void setSampadaDivertedCommercialArea(
	String sampadaDivertedCommercialArea) {
		this.sampadaDivertedCommercialArea = sampadaDivertedCommercialArea;
	}

	public String getSampadaDivertedIndustrialArea() {
		return sampadaDivertedIndustrialArea;
	}

	public void setSampadaDivertedIndustrialArea(
	String sampadaDivertedIndustrialArea) {
		this.sampadaDivertedIndustrialArea = sampadaDivertedIndustrialArea;
	}

	public String getSampadaDivertedEducationalArea() {
		return sampadaDivertedEducationalArea;
	}

	public void setSampadaDivertedEducationalArea(
	String sampadaDivertedEducationalArea) {
		this.sampadaDivertedEducationalArea = sampadaDivertedEducationalArea;
	}

	public String getSampadaDivertedHealthArea() {
		return sampadaDivertedHealthArea;
	}

	public void setSampadaDivertedHealthArea(String sampadaDivertedHealthArea) {
		this.sampadaDivertedHealthArea = sampadaDivertedHealthArea;
	}

	public String getSampadaDivertedOthersArea() {
		return sampadaDivertedOthersArea;
	}

	public void setSampadaDivertedOthersArea(String sampadaDivertedOthersArea) {
		this.sampadaDivertedOthersArea = sampadaDivertedOthersArea;
	}

	public void setOlderSubClause(String olderSubClause) {
		this.olderSubClause = olderSubClause;
	}

	public String getOlderSubClause() {
		return olderSubClause;
	}

	public void setOnlyResi(String onlyResi) {
		this.onlyResi = onlyResi;
	}

	public String getOnlyResi() {
		return onlyResi;
	}






	/* Addition End*/
}
