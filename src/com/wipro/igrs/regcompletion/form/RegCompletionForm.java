package com.wipro.igrs.regcompletion.form;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.regcompletion.dto.PropertyValuationDTO;
import com.wipro.igrs.regcompletion.dto.RegCompletDTO;

/**
 * @author Hari Krishna G.V
 *
 */
public class RegCompletionForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HashMap mapBuilding = new HashMap();
	
	RegCompletDTO regcompletDto=new RegCompletDTO();
	private String propertyTxnId;
	private String formName = "";
	private String actionName = "";
	
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

	
	
}
