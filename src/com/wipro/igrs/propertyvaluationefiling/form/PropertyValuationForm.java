package com.wipro.igrs.propertyvaluationefiling.form;
/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  PropertyValuationForm.java
 * Author      :  Madan Mohan 
 * Description :   
*/


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;


import com.wipro.igrs.propertyvaluationefiling.dto.AreaDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.CeilingDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.DistrictDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.FloorCalcTypeDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.InstrumentsDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.MahallaDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.MapDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.MunicipalDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.PropertyDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.PropertyValuationDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.TehsilDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.UsePlotDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.WardDTO;

/**
 * @author Madan Mohan
 */
public class PropertyValuationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author Madan Mohan
	 */
	private PropertyValuationDTO propertyDTO = new PropertyValuationDTO();
	/**
	 * @author Rishab
	 */
	private DistrictDTO  distDTO= new DistrictDTO();
	/**
	 * @author Rishab
	 */
	CeilingDTO ceilingDTO=new CeilingDTO();
	
   private int instId;
   
   private int deedId;
   
   private int floorCalcId;
   
   private String  hdnfloorCalcType;
   
   private String hdnAmount;
   
   private String addPartyLabel;
   
   private String moduleName;
   
   private String propertyTypeName;
   
   //added by roopam
   
   private int isMultipleProperty=0;
   private int isMultiplePropertyAdju=0;
   
   
   private int fromAdjudication=0;
   
   //below flags added by roopam
   private int previousFlag=0;
   private int party2Added=0;
   
   
   
   
     public int getParty2Added() {
	return party2Added;
}

public void setParty2Added(int party2Added) {
	this.party2Added = party2Added;
}

	public int getPreviousFlag() {
	return previousFlag;
}

public void setPreviousFlag(int previousFlag) {
	this.previousFlag = previousFlag;
}

	public int getFromAdjudication() {
	return fromAdjudication;
}

public void setFromAdjudication(int fromAdjudication) {
	this.fromAdjudication = fromAdjudication;
}

	public int getIsMultiplePropertyAdju() {
	return isMultiplePropertyAdju;
}

public void setIsMultiplePropertyAdju(int isMultiplePropertyAdju) {
	this.isMultiplePropertyAdju = isMultiplePropertyAdju;
}

	public int getIsMultipleProperty() {
	return isMultipleProperty;
}

public void setIsMultipleProperty(int isMultipleProperty) {
	this.isMultipleProperty = isMultipleProperty;
}

	/**
	 * @author Madan Mohan
	 */
	private ArrayList instrumentDTOList = 
		new ArrayList();
	/**  
	 * @author Madan Mohan
	 */
	
	private ArrayList floorcalctypeDTOList = 
		new ArrayList();
	/**  
	 * @author Madan Mohan
	 */
	private ArrayList exemptionDTOList = 
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList selectedExemptionList = 
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList dutycalculationDTOList =
		new ArrayList();
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
	private DutyCalculationDTO dutyDTO = new DutyCalculationDTO();
	/**
	 * @author Madan Mohan
	 */
	private InstrumentsDTO instrumentDTO = new InstrumentsDTO();
	/**
	 * @author Madan Mohan
	 */
	private ExemptionDTO exemptionDTO = new ExemptionDTO();
	
	private MapDTO mapDTO = new MapDTO();
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
	private String actionName;
	/**
	 * @author Madan Mohan
	 */
	private String formName;
	/**
	 * @author Madan Mohan
	 */
	private String buildingShow = "null"; 
	/**
	 * @author Madan Mohan
	 */
	private HashMap mapBuilding = new HashMap();
	private ArrayList listBuilding = new ArrayList();
	private HashMap mapBuildingDetails = new HashMap(); 
	
	private HashMap mapParty = new HashMap(); 
	
	private HashMap newMapParty = new HashMap(); 
	
private TehsilDTO  tehsilDTO= new TehsilDTO();
	
	private AreaDTO areaDTO= new AreaDTO();
	
	private PropertyDTO propDTO= new PropertyDTO();
	
	private WardDTO wardDTO= new WardDTO();
	
	private MunicipalDTO municipalDTO=new MunicipalDTO();
	
	private MahallaDTO mahallaDTO=new MahallaDTO();
	
	private UsePlotDTO usePlotDTO=new UsePlotDTO();
	
	FloorCalcTypeDTO floorCalcTypeDTO = new FloorCalcTypeDTO();
	
	
	
	public FloorCalcTypeDTO getFloorCalcTypeDTO() {
		return floorCalcTypeDTO;
	}

	public void setFloorCalcTypeDTO(FloorCalcTypeDTO floorCalcTypeDTO) {
		this.floorCalcTypeDTO = floorCalcTypeDTO;
	}

	public ArrayList getFloorcalctypeDTOList() {
		return floorcalctypeDTOList;
	}

	public void setFloorcalctypeDTOList(ArrayList floorcalctypeDTOList) {
		this.floorcalctypeDTOList = floorcalctypeDTOList;
	}

	public MapDTO getMapDTO() {
		return mapDTO;
	}

	public void setMapDTO(MapDTO mapDTO) {
		this.mapDTO = mapDTO;
	}

	public UsePlotDTO getUsePlotDTO() {
		return usePlotDTO;
	}
	public void setUsePlotDTO(UsePlotDTO usePlotDTO) {
		this.usePlotDTO = usePlotDTO;
	}
	public MahallaDTO getMahallaDTO() {
		return mahallaDTO;
	}
	public void setMahallaDTO(MahallaDTO mahallaDTO) {
		this.mahallaDTO = mahallaDTO;
	}
	
	
	
	public MunicipalDTO getMunicipalDTO() {
		return municipalDTO;
	}
	public void setMunicipalDTO(MunicipalDTO municipalDTO) {
		this.municipalDTO = municipalDTO;
	}
	public WardDTO getWardDTO() {
		return wardDTO;
	}
	public void setWardDTO(WardDTO wardDTO) {
		this.wardDTO = wardDTO;
	}
	public PropertyDTO getPropDTO() {
		return propDTO;
	}
	public void setPropDTO(PropertyDTO propDTO) {
		this.propDTO = propDTO;
	}
	public AreaDTO getAreaDTO() {
		return areaDTO;
	}
	public DistrictDTO getDistDTO() {
		return distDTO;
	}
	public void setDistDTO(DistrictDTO distDTO) {
		this.distDTO = distDTO;
	}

	public TehsilDTO getTehsilDTO() {
		return tehsilDTO;
	}
	public void setTehsilDTO(TehsilDTO tehsilDTO) {
		this.tehsilDTO = tehsilDTO;
	}
	public void setAreaDTO(AreaDTO areaDTO) {
		this.areaDTO = areaDTO;
	}
	
	
	public CeilingDTO getCeilingDTO() {
		return ceilingDTO;
	}
	public void setCeilingDTO(CeilingDTO ceilingDTO) {
		this.ceilingDTO = ceilingDTO;
	}
	
	
	
	public HashMap getNewMapParty() {
		return newMapParty;
	}

	public void setNewMapParty(HashMap newMapParty) {
		this.newMapParty = newMapParty;
	}

	public HashMap getMapParty() {
		return mapParty;
	}

	public void setMapParty(HashMap mapParty) {
		this.mapParty = mapParty;
	}

	public HashMap getMapBuildingDetails() {
		return mapBuildingDetails;
	}
	public void setMapBuildingDetails(HashMap mapBuildingDetails) {
		this.mapBuildingDetails = mapBuildingDetails;
	}
	public ArrayList getListBuilding() {
		return listBuilding;
	}
	public void setListBuilding(ArrayList listBuilding) {
		this.listBuilding = listBuilding;
	}
	public HashMap getMapBuilding() {
		return mapBuilding;
	}
	public void setMapBuilding(HashMap mapBuilding) {
		this.mapBuilding = mapBuilding;
	}
	
	
	
	public String getHdnfloorCalcType() {
		return hdnfloorCalcType;
	}

	public void setHdnfloorCalcType(String hdnfloorCalcType) {
		this.hdnfloorCalcType = hdnfloorCalcType;
	}

	/**
	 * @return String
	 */
	public String getBuildingShow() {
		return buildingShow;
	}
	/**
	 * @param buildingShow
	 */
	public void setBuildingShow(String buildingShow) {
		this.buildingShow = buildingShow;
	}
	/**
	 * @return String
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionForm
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * @return String
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * @param formName
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	/**
	 * 
	 * @return ArrayList
	 */
	public ArrayList getDistList() {
		return distList;
	}
	/**
	 * 
	 * @param distList
	 */
	public void setDistList(ArrayList distList) {
		this.distList = distList;
	}
	/**
	 * 
	 * @return  PropertyValuationDTO
	 */	
	public PropertyValuationDTO getPropertyDTO() {
		return propertyDTO;
	}
	/**
	 * @param propertyDTO
	 */
	public void setPropertyDTO(PropertyValuationDTO propertyDTO) {
		this.propertyDTO = propertyDTO;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getPropertyType() {
		return propertyType;
	}
	/**
	 * @param propertyType
	 */
	public void setPropertyType(ArrayList propertyType) {
		this.propertyType = propertyType;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getMunicipalList() {
		return municipalList;
	}
	/**
	 * @param municipalList
	 */
	public void setMunicipalList(ArrayList municipalList) {
		this.municipalList = municipalList;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getTehsilList() {
		return tehsilList;
	}
	/**
	 * @param tehsilList
	 */
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
	/**
	 * @param save deed while complex deed
	 */	
	
	
	public int getDeedId() {
		return deedId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	
	
	public int getFloorCalcId() {
		return floorCalcId;
	}

	public void setFloorCalcId(int floorCalcId) {
		this.floorCalcId = floorCalcId;
	}

	public void setDeedId(int deedId) {
		this.deedId = deedId;
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
	public ArrayList getMahallaList() {
		return mahallaList;
	}
	public void setMahallaList(ArrayList mahallaList) {
		this.mahallaList = mahallaList;
	}
	/**
	 * @return String
	 */
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
	public DutyCalculationDTO getDutyDTO() {
		return dutyDTO;
	}
	public void setDutyDTO(DutyCalculationDTO dutyDTO) {
		this.dutyDTO = dutyDTO;
	}
	public InstrumentsDTO getInstrumentDTO() {
		return instrumentDTO;
	}
	public void setInstrumentDTO(InstrumentsDTO instrumentDTO) {
		this.instrumentDTO = instrumentDTO;
	}
	public ExemptionDTO getExemptionDTO() {
		return exemptionDTO;
	}
	public void setExemptionDTO(ExemptionDTO exemptionDTO) {
		this.exemptionDTO = exemptionDTO;
	}
	public ArrayList getInstrumentDTOList() {
		return instrumentDTOList;
	}
	public void setInstrumentDTOList(ArrayList
		 instrumentDTOList) {
		this.instrumentDTOList = instrumentDTOList;
	}
	public ArrayList getExemptionDTOList() {
		return exemptionDTOList;
	}
	public void setExemptionDTOList(ArrayList exemptionDTOList) {
		this.exemptionDTOList = exemptionDTOList;
	}
	public ArrayList getSelectedExemptionList() {
		return selectedExemptionList;
	}
	public void setSelectedExemptionList(
			ArrayList selectedExemptionList) {
		this.selectedExemptionList = selectedExemptionList;
	}
	public ArrayList getDutycalculationDTOList() {
		return dutycalculationDTOList;
	}
	public void setDutycalculationDTOList(
			ArrayList dutycalculationDTOList) {
		this.dutycalculationDTOList = dutycalculationDTOList;
	}
	public String getAddPartyLabel() {
		return addPartyLabel;
	}
		
	public void setAddPartyLabel(String addPartyLabel) {
		this.addPartyLabel = addPartyLabel;
	}

		public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	public String getHdnAmount() {
		return hdnAmount;
	}
	public void setHdnAmount(String hdnAmount) {
		this.hdnAmount = hdnAmount;
	}
	public ArrayList getSelectedSubclause() {
		return selectedSubclause;
	}
	public void setSelectedSubclause(
			ArrayList selectedSubclause) {
		this.selectedSubclause = selectedSubclause;
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
	public HashMap getSelectedMap() {
		return selectedMap;
	}
	public void setSelectedMap(HashMap selectedMap) {
		this.selectedMap = selectedMap;
	}
	
	// added by prakpp for all the ID of the drop down to get in the action class to pass to other layers
	private String districtID;
	private String tehsilID;
	private String newProperty;
	private String wardpatwariID;
	private String mohallavillageID;
	
	private String district;
	

	
	public String getNewProperty() {
		return newProperty;
	}

	public void setNewProperty(String newProperty) {
		this.newProperty = newProperty;
	}

	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDistrictID() {
	    return districtID;
	}
	public void setDistrictID(String districtID) {
	    this.districtID = districtID;
	}
	public String getTehsilID() {
	    return tehsilID;
	}
	public void setTehsilID(String tehsilID) {
	    this.tehsilID = tehsilID;
	}
	public String getWardpatwariID() {
	    return wardpatwariID;
	}
	public void setWardpatwariID(String wardpatwariID) {
	    this.wardpatwariID = wardpatwariID;
	}
	public String getMohallavillageID() {
	    return mohallavillageID;
	}
	public void setMohallavillageID(String mohallavillageID) {
	    this.mohallavillageID = mohallavillageID;
	}

	public String getPropertyTypeName() {
		return propertyTypeName;
	}

	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}

	
	
}
