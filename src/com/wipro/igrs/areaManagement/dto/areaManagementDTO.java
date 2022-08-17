package com.wipro.igrs.areaManagement.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class areaManagementDTO implements Serializable {
	private String logic;
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
	private String tehsilHindiName;
	private String tehsilDesc;
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
	private String subAreaHindiName;
	private String subAreaDesc;
	// end //
	
	
	//for Ward Operations//
	private String wardListId;
	private String wardListName;
	private String wardId;
	private String wardName;
	private String wardHindiName;
	private String wardDesc;
	// end //
	
	//for Subclause Operations//
	private String subClauseListId;
	private String subClauseListName;
	// end //
	
	//for Ward or Patwari Operations//
	private String wardorPatwariListId;
	private String wardorPatwariListName;
	// end //
	
	//for Municipal Body Operations//
	private String munciBodyListId;
	private String munciBodyListName;
	// end //
	
	//for Mohalla Operations//
	private String mohallaListId;
	private String mohallaListName;
	private String mohallaId;
	private String mohallaName;
	private String mohallaHindiName;
	private String mohallaDesc;
	private String mohallaSubClauseName;
	// end //
	
	//for Construction Operations//
	
	private String rcc41;
	private String rcc42;
	private String rcc43;
	private String rcc44;
	private String rccOthers;
	
	private String rbc41;
	private String rbc42;
	private String rbc43;
	private String rbc44;
	private String rbcOthers;
	
	private String tin41;
	public String getRcc41() {
		return rcc41;
	}

	public void setRcc41(String rcc41) {
		this.rcc41 = rcc41;
	}

	public String getRcc42() {
		return rcc42;
	}

	public void setRcc42(String rcc42) {
		this.rcc42 = rcc42;
	}

	public String getRcc43() {
		return rcc43;
	}

	public void setRcc43(String rcc43) {
		this.rcc43 = rcc43;
	}

	public String getRcc44() {
		return rcc44;
	}

	public void setRcc44(String rcc44) {
		this.rcc44 = rcc44;
	}

	public String getRccOthers() {
		return rccOthers;
	}

	public void setRccOthers(String rccOthers) {
		this.rccOthers = rccOthers;
	}

	public String getRbc41() {
		return rbc41;
	}

	public void setRbc41(String rbc41) {
		this.rbc41 = rbc41;
	}

	public String getRbc42() {
		return rbc42;
	}

	public void setRbc42(String rbc42) {
		this.rbc42 = rbc42;
	}

	public String getRbc43() {
		return rbc43;
	}

	public void setRbc43(String rbc43) {
		this.rbc43 = rbc43;
	}

	public String getRbc44() {
		return rbc44;
	}

	public void setRbc44(String rbc44) {
		this.rbc44 = rbc44;
	}

	public String getRbcOthers() {
		return rbcOthers;
	}

	public void setRbcOthers(String rbcOthers) {
		this.rbcOthers = rbcOthers;
	}

	public String getTin41() {
		return tin41;
	}

	public void setTin41(String tin41) {
		this.tin41 = tin41;
	}

	public String getTin42() {
		return tin42;
	}

	public void setTin42(String tin42) {
		this.tin42 = tin42;
	}

	public String getTin43() {
		return tin43;
	}

	public void setTin43(String tin43) {
		this.tin43 = tin43;
	}

	public String getTin44() {
		return tin44;
	}

	public void setTin44(String tin44) {
		this.tin44 = tin44;
	}

	public String getTinOthers() {
		return tinOthers;
	}

	public void setTinOthers(String tinOthers) {
		this.tinOthers = tinOthers;
	}

	public String getKabelu41() {
		return kabelu41;
	}

	public void setKabelu41(String kabelu41) {
		this.kabelu41 = kabelu41;
	}

	public String getKabelu42() {
		return kabelu42;
	}

	public void setKabelu42(String kabelu42) {
		this.kabelu42 = kabelu42;
	}

	public String getKabelu43() {
		return kabelu43;
	}

	public void setKabelu43(String kabelu43) {
		this.kabelu43 = kabelu43;
	}

	public String getKabelu44() {
		return kabelu44;
	}

	public void setKabelu44(String kabelu44) {
		this.kabelu44 = kabelu44;
	}

	public String getKabeluOthers() {
		return kabeluOthers;
	}

	public void setKabeluOthers(String kabeluOthers) {
		this.kabeluOthers = kabeluOthers;
	}

	private String tin42;
	private String tin43;
	private String tin44;
	private String tinOthers;

	private String kabelu41;
	private String kabelu42;
	private String kabelu43;
	private String kabelu44;
	private String kabeluOthers;
	
	// end //
	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getLogic() {
		return logic;
	}


	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateName() {
		return stateName;
	}


	public void setLogicBtn(String logicBtn) {
		this.logicBtn = logicBtn;
	}

	public String getLogicBtn() {
		return logicBtn;
	}


	public void setErrorFlag(String errorFlag) {
		this.errorFlag = errorFlag;
	}

	public String getErrorFlag() {
		return errorFlag;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}

	public String getTehsilId() {
		return tehsilId;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilHindiName(String tehsilHindiName) {
		this.tehsilHindiName = tehsilHindiName;
	}

	public String getTehsilHindiName() {
		return tehsilHindiName;
	}

	public void setTehsilDesc(String tehsilDesc) {
		this.tehsilDesc = tehsilDesc;
	}

	public String getTehsilDesc() {
		return tehsilDesc;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setTehsilListId(String tehsilListId) {
		this.tehsilListId = tehsilListId;
	}

	public String getTehsilListId() {
		return tehsilListId;
	}

	public void setTehsilListName(String tehsilListName) {
		this.tehsilListName = tehsilListName;
	}

	public String getTehsilListName() {
		return tehsilListName;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setSubAreaListId(String subAreaListId) {
		this.subAreaListId = subAreaListId;
	}

	public String getSubAreaListId() {
		return subAreaListId;
	}

	public void setSubAreaListName(String subAreaListName) {
		this.subAreaListName = subAreaListName;
	}

	public String getSubAreaListName() {
		return subAreaListName;
	}

	public void setSubAreaId(String subAreaId) {
		this.subAreaId = subAreaId;
	}

	public String getSubAreaId() {
		return subAreaId;
	}

	public void setSubAreaName(String subAreaName) {
		this.subAreaName = subAreaName;
	}

	public String getSubAreaName() {
		return subAreaName;
	}

	public void setSubAreaHindiName(String subAreaHindiName) {
		this.subAreaHindiName = subAreaHindiName;
	}

	public String getSubAreaHindiName() {
		return subAreaHindiName;
	}

	public void setSubAreaDesc(String subAreaDesc) {
		this.subAreaDesc = subAreaDesc;
	}

	public String getSubAreaDesc() {
		return subAreaDesc;
	}

	public void setWardListId(String wardListId) {
		this.wardListId = wardListId;
	}

	public String getWardListId() {
		return wardListId;
	}

	public void setWardListName(String wardListName) {
		this.wardListName = wardListName;
	}

	public String getWardListName() {
		return wardListName;
	}

	public void setWardId(String wardId) {
		this.wardId = wardId;
	}

	public String getWardId() {
		return wardId;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardHindiName(String wardHindiName) {
		this.wardHindiName = wardHindiName;
	}

	public String getWardHindiName() {
		return wardHindiName;
	}

	public void setWardDesc(String wardDesc) {
		this.wardDesc = wardDesc;
	}

	public String getWardDesc() {
		return wardDesc;
	}

	public void setMohallaListId(String mohallaListId) {
		this.mohallaListId = mohallaListId;
	}

	public String getMohallaListId() {
		return mohallaListId;
	}

	public void setMohallaListName(String mohallaListName) {
		this.mohallaListName = mohallaListName;
	}

	public String getMohallaListName() {
		return mohallaListName;
	}

	public void setMohallaId(String mohallaId) {
		this.mohallaId = mohallaId;
	}

	public String getMohallaId() {
		return mohallaId;
	}

	public void setMohallaName(String mohallaName) {
		this.mohallaName = mohallaName;
	}

	public String getMohallaName() {
		return mohallaName;
	}

	public void setMohallaHindiName(String mohallaHindiName) {
		this.mohallaHindiName = mohallaHindiName;
	}

	public String getMohallaHindiName() {
		return mohallaHindiName;
	}

	public void setMohallaDesc(String mohallaDesc) {
		this.mohallaDesc = mohallaDesc;
	}

	public String getMohallaDesc() {
		return mohallaDesc;
	}

	public void setSubClauseListId(String subClauseListId) {
		this.subClauseListId = subClauseListId;
	}

	public String getSubClauseListId() {
		return subClauseListId;
	}

	public void setSubClauseListName(String subClauseListName) {
		this.subClauseListName = subClauseListName;
	}

	public String getSubClauseListName() {
		return subClauseListName;
	}


	public void setWardorPatwariListId(String wardorPatwariListId) {
		this.wardorPatwariListId = wardorPatwariListId;
	}

	public String getWardorPatwariListId() {
		return wardorPatwariListId;
	}

	public void setWardorPatwariListName(String wardorPatwariListName) {
		this.wardorPatwariListName = wardorPatwariListName;
	}

	public String getWardorPatwariListName() {
		return wardorPatwariListName;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getCheck() {
		return check;
	}

	public void setMunciBodyListId(String munciBodyListId) {
		this.munciBodyListId = munciBodyListId;
	}

	public String getMunciBodyListId() {
		return munciBodyListId;
	}

	public void setMunciBodyListName(String munciBodyListName) {
		this.munciBodyListName = munciBodyListName;
	}

	public String getMunciBodyListName() {
		return munciBodyListName;
	}

	public void setLinkClick(String linkClick) {
		this.linkClick = linkClick;
	}

	public String getLinkClick() {
		return linkClick;
	}

	public void setClickId(String clickId) {
		this.clickId = clickId;
	}

	public String getClickId() {
		return clickId;
	}

	public void setNowAdd(String nowAdd) {
		this.nowAdd = nowAdd;
	}

	public String getNowAdd() {
		return nowAdd;
	}

	public void setMohallaSubClauseName(String mohallaSubClauseName) {
		this.mohallaSubClauseName = mohallaSubClauseName;
	}

	public String getMohallaSubClauseName() {
		return mohallaSubClauseName;
	}

	public void setClickRadio(String clickRadio) {
		this.clickRadio = clickRadio;
	}

	public String getClickRadio() {
		return clickRadio;
	}

	//added by saurav for adding LGD Code
	private String lgdCode;
	public String getLgdCode() {
		return lgdCode;
	}

	public void setLgdCode(String lgdCode) {
		this.lgdCode = lgdCode;
	}
	
}
