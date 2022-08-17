package com.wipro.igrs.SROMapping.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.management.loading.PrivateClassLoader;

public class SROMappingDTO implements Serializable {
	
	private String logicBtn;
	private String errorFlag;
	private String type;
	private String check;
	private String linkClick;
	private String clickId;
	private String clickRadio;
	private String nowAdd;
	//for State Operations//
	private String stateId;
	private String stateName;
	// end //
	
	//for District Operations//
	private String districtId;
	private String districtName;
	// end //

	//for Tehsil Operations//
	private String tehsilListId;
	private String tehsilListName;
	private String tehsilId;
	private String tehsilName;
	// end //
	
	//for Area Operations//
	private String areaId;
	private String areaName;
	// end //
	
	//for subArea Operations//
	private String subAreaListId;
	private String subAreaListName;
	private String subAreaId;
	private String subAreaName;
	// end //
	
	//for Ward Operations//
	private String wardListId;
	private String wardListName;
	private String wardId;
	private String wardName;
	private String wardHindiName;
	private String WardorPatwariListId;
	private String WardorPatwariListName;
	private String wardListEdId;
	private String WardListNameEd;
	
	//end//
	//sro//
	private String sroListId;
	private String sroListName;
	private String sroName;
	//end//
	
	public String getWardListNameEd() {
		return WardListNameEd;
	}
	public void setWardListNameEd(String wardListNameEd) {
		WardListNameEd = wardListNameEd;
	}
	
	public String getSroListId() {
		return sroListId;
	}
	public String getWardListEdId() {
		return wardListEdId;
	}
	public void setWardListEdId(String wardListEdId) {
		this.wardListEdId = wardListEdId;
	}
	
	public void setSroListId(String sroListId) {
		this.sroListId = sroListId;
	}

	public String getSroName() {
		return sroName;
	}
	public void setSroName(String sroName) {
		this.sroName = sroName;
	}
	
	public String getSroListName() {
		return sroListName;
	}
	public void setSroListName(String sroListName) {
		this.sroListName = sroListName;
	}
	public String getWardorPatwariListName() {
		return WardorPatwariListName;
	}
	public void setWardorPatwariListName(String wardorPatwariListName) {
		WardorPatwariListName = wardorPatwariListName;
	}
	public String getWardorPatwariListId() {
		return WardorPatwariListId;
	}
	public void setWardorPatwariListId(String wardorPatwariListId) {
		WardorPatwariListId = wardorPatwariListId;
	}
	private String logic;
	public String getLogic() {
		return logic;
	}
	public void setLogic(String logic) {
		this.logic = logic;
	}
	public String getLogicBtn() {
		return logicBtn;
	}
	public void setLogicBtn(String logicBtn) {
		this.logicBtn = logicBtn;
	}
	public String getErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(String errorFlag) {
		this.errorFlag = errorFlag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getLinkClick() {
		return linkClick;
	}
	public void setLinkClick(String linkClick) {
		this.linkClick = linkClick;
	}
	public String getClickId() {
		return clickId;
	}
	public void setClickId(String clickId) {
		this.clickId = clickId;
	}
	public String getClickRadio() {
		return clickRadio;
	}
	public void setClickRadio(String clickRadio) {
		this.clickRadio = clickRadio;
	}
	public String getNowAdd() {
		return nowAdd;
	}
	public void setNowAdd(String nowAdd) {
		this.nowAdd = nowAdd;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getTehsilListId() {
		return tehsilListId;
	}
	public void setTehsilListId(String tehsilListId) {
		this.tehsilListId = tehsilListId;
	}
	public String getTehsilListName() {
		return tehsilListName;
	}
	public void setTehsilListName(String tehsilListName) {
		this.tehsilListName = tehsilListName;
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
	
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getSubAreaListId() {
		return subAreaListId;
	}
	public void setSubAreaListId(String subAreaListId) {
		this.subAreaListId = subAreaListId;
	}
	public String getSubAreaListName() {
		return subAreaListName;
	}
	public void setSubAreaListName(String subAreaListName) {
		this.subAreaListName = subAreaListName;
	}
	public String getSubAreaId() {
		return subAreaId;
	}
	public void setSubAreaId(String subAreaId) {
		this.subAreaId = subAreaId;
	}
	public String getSubAreaName() {
		return subAreaName;
	}
	public void setSubAreaName(String subAreaName) {
		this.subAreaName = subAreaName;
	}
	
	public String getWardListId() {
		return wardListId;
	}
	public void setWardListId(String wardListId) {
		this.wardListId = wardListId;
	}
	public String getWardListName() {
		return wardListName;
	}
	public void setWardListName(String wardListName) {
		this.wardListName = wardListName;
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
	public String getWardHindiName() {
		return wardHindiName;
	}
	public void setWardHindiName(String wardHindiName) {
		this.wardHindiName = wardHindiName;
	}
	

}
