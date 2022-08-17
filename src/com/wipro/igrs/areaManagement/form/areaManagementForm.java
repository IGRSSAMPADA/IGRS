package com.wipro.igrs.areaManagement.form;

import java.util.ArrayList;

import com.wipro.igrs.areaManagement.dto.areaManagementDTO;

public class areaManagementForm extends org.apache.struts.action.ActionForm{

	private String actionName;
	private String getList;
	private String saveType;
	private String pageType;
	private String linkClickId;
	private areaManagementDTO appdto= new areaManagementDTO();
	
	
	private ArrayList stateList = new ArrayList();
	private ArrayList districtList = new ArrayList();
	private ArrayList tehsilList = new ArrayList();
	private ArrayList areaList = new ArrayList();
	private ArrayList subareaList = new ArrayList();
	private ArrayList wardList = new ArrayList();
	private ArrayList mohallaList = new ArrayList();
	private ArrayList subClauseList = new ArrayList();
	private ArrayList municipalBodyList = new ArrayList();
	private ArrayList wardorPatwariList = new ArrayList();
	private ArrayList constructionRatesList = new ArrayList();
	
	private String valueCheckBox ;
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setAppdto(areaManagementDTO appdto) {
		this.appdto = appdto;
	}

	public areaManagementDTO getAppdto() {
		return appdto;
	}



	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}

	public ArrayList getStateList() {
		return stateList;
	}

	public void setValueCheckBox(String valueCheckBox) {
		this.valueCheckBox = valueCheckBox;
	}

	public String getValueCheckBox() {
		return valueCheckBox;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public ArrayList getDistrictList() {
		return districtList;
	}

	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}

	public ArrayList getTehsilList() {
		return tehsilList;
	}

	public void setAreaList(ArrayList areaList) {
		this.areaList = areaList;
	}

	public ArrayList getAreaList() {
		return areaList;
	}

	public void setSubareaList(ArrayList subareaList) {
		this.subareaList = subareaList;
	}

	public ArrayList getSubareaList() {
		return subareaList;
	}

	public void setWardList(ArrayList wardList) {
		this.wardList = wardList;
	}

	public ArrayList getWardList() {
		return wardList;
	}

	public void setMohallaList(ArrayList mohallaList) {
		this.mohallaList = mohallaList;
	}

	public ArrayList getMohallaList() {
		return mohallaList;
	}

	public void setGetList(String getList) {
		this.getList = getList;
	}

	public String getGetList() {
		return getList;
	}

	public void setSubClauseList(ArrayList subClauseList) {
		this.subClauseList = subClauseList;
	}

	public ArrayList getSubClauseList() {
		return subClauseList;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public String getSaveType() {
		return saveType;
	}

	public void setMunicipalBodyList(ArrayList municipalBodyList) {
		this.municipalBodyList = municipalBodyList;
	}

	public ArrayList getMunicipalBodyList() {
		return municipalBodyList;
	}

	public void setWardorPatwariList(ArrayList wardorPatwariList) {
		this.wardorPatwariList = wardorPatwariList;
	}

	public ArrayList getWardorPatwariList() {
		return wardorPatwariList;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getPageType() {
		return pageType;
	}

	public void setLinkClickId(String linkClickId) {
		this.linkClickId = linkClickId;
	}

	public String getLinkClickId() {
		return linkClickId;
	}

	public void setConstructionRatesList(ArrayList constructionRatesList) {
		this.constructionRatesList = constructionRatesList;
	}

	public ArrayList getConstructionRatesList() {
		return constructionRatesList;
	}

	

}
