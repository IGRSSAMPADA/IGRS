package com.wipro.igrs.SMSAlerts.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class SMSAlertsDTO implements Serializable{
	
  private String logicBtn;
  private String errorFlag;
  private String type;
  private String check;
  private String linkClick;
  private String clickId;
  private String clickRadio;
  private String nowAdd;
  private String stateId;
  private String stateName;
  private String districtId;
  private String districtName;
  private String zoneId;
  private String zoneName;
  private String userType;
  private String userDesignation;
  private String userName;
  private String userMobileNumber;
  private String userTypes;
  private String userId;
  private String serialNo;
  private String checkFlag;
  private String smsEnglishContent;
  private String smsHindiContent;
  private ArrayList districtData = new ArrayList();
  private ArrayList smsData = new ArrayList();
  
  
  //Added by neeti
	private String groupName;
	private ArrayList groupMembersInfo = new ArrayList();
	private String createdDate;
	private String groupId;
	private String groupMemberId;
	private String groupMemberName;
	private String roleName;
	private String officeId;
	private String officeName;
	
	private String zoneFlag="";
	private String districtFlag="";
	
	private String zoneCheckList="";
	private String districtCheckList="";
	
	private ArrayList zoneList = new ArrayList();
	private ArrayList checkedZone = new ArrayList();
	private ArrayList districtList = new ArrayList();
	private ArrayList checkedDistrict = new ArrayList();
  
  
  public String getSmsEnglishContent() { return smsEnglishContent; }
  
  public void setSmsEnglishContent(String smsEnglishContent) {
    this.smsEnglishContent = smsEnglishContent;
  }
  
  public String getSmsHindiContent() { return smsHindiContent; }
  
  public void setSmsHindiContent(String smsHindiContent) {
    this.smsHindiContent = smsHindiContent;
  }
  
  public ArrayList getSmsData() { return smsData; }
  
  public void setSmsData(ArrayList smsData) {
    this.smsData = smsData;
  }
  
  public String getCheckFlag() { return checkFlag; }
  
  public void setCheckFlag(String checkFlag) {
    this.checkFlag = checkFlag;
  }
  
  public String getSerialNo() { return serialNo; }
  
  public void setSerialNo(String serialNo) {
    this.serialNo = serialNo;
  }
  
  public String getUserMobileNumber() { return userMobileNumber; }
  
  public void setUserMobileNumber(String userMobileNumber) {
    this.userMobileNumber = userMobileNumber;
  }
  
  public ArrayList getDistrictData() { return districtData; }
  
  public void setDistrictData(ArrayList districtData) {
    this.districtData = districtData;
  }
  
  public String getUserId() {
    return userId;
  }
  
  public void setUserId(String userId) { this.userId = userId; }
  
  public String getUserTypes() {
    return userTypes;
  }
  
  public void setUserTypes(String userTypes) { this.userTypes = userTypes; }
  
  public String getUserDesignation() {
    return userDesignation;
  }
  
  public void setUserDesignation(String userDesignation) { this.userDesignation = userDesignation; }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName(String userName) { this.userName = userName; }
  
  public String getUserType()
  {
    return userType;
  }
  
  public void setUserType(String userType) { this.userType = userType; }
  
  public String getLogicBtn() {
    return logicBtn;
  }
  
  public String getZoneId() { return zoneId; }
  
  public void setZoneId(String zoneId) {
    this.zoneId = zoneId;
  }
  
  public String getZoneName() { return zoneName; }
  
  public void setZoneName(String zoneName) {
    this.zoneName = zoneName;
  }
  
  public void setLogicBtn(String logicBtn) { this.logicBtn = logicBtn; }
  
  public String getErrorFlag() {
    return errorFlag;
  }
  
  public void setErrorFlag(String errorFlag) { this.errorFlag = errorFlag; }
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) { this.type = type; }
  
  public String getCheck() {
    return check;
  }
  
  public void setCheck(String check) { this.check = check; }
  
  public String getLinkClick() {
    return linkClick;
  }
  
  public void setLinkClick(String linkClick) { this.linkClick = linkClick; }
  
  public String getClickId() {
    return clickId;
  }
  
  public void setClickId(String clickId) { this.clickId = clickId; }
  
  public String getClickRadio() {
    return clickRadio;
  }
  
  public void setClickRadio(String clickRadio) { this.clickRadio = clickRadio; }
  
  public String getNowAdd() {
    return nowAdd;
  }
  
  public void setNowAdd(String nowAdd) { this.nowAdd = nowAdd; }
  
  public String getStateId() {
    return stateId;
  }
  
  public void setStateId(String stateId) { this.stateId = stateId; }
  
  public String getStateName() {
    return stateName;
  }
  
  public void setStateName(String stateName) { this.stateName = stateName; }
  
  public String getDistrictId() {
    return districtId;
  }
  
  public void setDistrictId(String districtId) { this.districtId = districtId; }
  
  public String getDistrictName() {
    return districtName;
  }
  
  public void setDistrictName(String districtName) { this.districtName = districtName; }

public String getGroupName() {
	return groupName;
}

public void setGroupName(String groupName) {
	this.groupName = groupName;
}

public ArrayList getGroupMembersInfo() {
	return groupMembersInfo;
}

public void setGroupMembersInfo(ArrayList groupMembersInfo) {
	this.groupMembersInfo = groupMembersInfo;
}

public String getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
}

public String getGroupId() {
	return groupId;
}

public void setGroupId(String groupId) {
	this.groupId = groupId;
}

public String getGroupMemberId() {
	return groupMemberId;
}

public void setGroupMemberId(String groupMemberId) {
	this.groupMemberId = groupMemberId;
}

public String getGroupMemberName() {
	return groupMemberName;
}

public void setGroupMemberName(String groupMemberName) {
	this.groupMemberName = groupMemberName;
}

public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}

public String getOfficeId() {
	return officeId;
}

public void setOfficeId(String officeId) {
	this.officeId = officeId;
}

public String getOfficeName() {
	return officeName;
}

public void setOfficeName(String officeName) {
	this.officeName = officeName;
}

public String getZoneFlag() {
	return zoneFlag;
}

public void setZoneFlag(String zoneFlag) {
	this.zoneFlag = zoneFlag;
}

public String getDistrictFlag() {
	return districtFlag;
}

public void setDistrictFlag(String districtFlag) {
	this.districtFlag = districtFlag;
}

public void setZoneList(ArrayList zoneList) {
	this.zoneList = zoneList;
}

public ArrayList getZoneList() {
	return zoneList;
}

public void setZoneCheckList(String zoneCheckList) {
	this.zoneCheckList = zoneCheckList;
}

public String getZoneCheckList() {
	return zoneCheckList;
}

public void setDistrictCheckList(String districtCheckList) {
	this.districtCheckList = districtCheckList;
}

public String getDistrictCheckList() {
	return districtCheckList;
}

public void setCheckedZone(ArrayList checkedZone) {
	this.checkedZone = checkedZone;
}

public ArrayList getCheckedZone() {
	return checkedZone;
}

public ArrayList getDistrictList() {
	return districtList;
}

public void setDistrictList(ArrayList districtList) {
	this.districtList = districtList;
}

public ArrayList getCheckedDistrict() {
	return checkedDistrict;
}

public void setCheckedDistrict(ArrayList checkedDistrict) {
	this.checkedDistrict = checkedDistrict;
}
}
