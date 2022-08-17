package com.wipro.igrs.SMSAlerts.form;

import com.wipro.igrs.SMSAlerts.dto.SMSAlertsDTO;
import java.util.ArrayList;
import java.util.HashMap;


public class SMSAlertsForm  extends org.apache.struts.action.ActionForm{
	
  private String userType;
  private String userZone;
  private String user_district;
  private String userDesignation;
  private String userName;
  private String userMobileNumber;
  private String actionName;
  private String saveType;
  private String userTypes;
  private String zoneId;
  private String zoneName;
  private String districtName;
  private String serialNo;
  private String stateName;
  private String addMoreState;
  private String smsHindiContent;
  private String userId;
  private String checkBoxFlag;
  private String checkFlag;
  private String intValue;
  private String smsEnglishContent;
  private String checkBox;
  private String errorFlag;
  private ArrayList districtList = new ArrayList();
  private ArrayList zoneList = new ArrayList();
  private SMSAlertsDTO smsdto = new SMSAlertsDTO();
  private ArrayList districtData = new ArrayList();
  private ArrayList stateData = new ArrayList();
  private ArrayList zoneData = new ArrayList();
  
  private ArrayList smsData = new ArrayList();
  private ArrayList stateUsersData = new ArrayList();
  private ArrayList zoneUsersData = new ArrayList();
  private ArrayList districtUsersData = new ArrayList();
  
  
//Added by Neeti --- SMS ALerts
	
	private String groupName;
	private HashMap groupInfo = new HashMap();
	private HashMap groupMembersInfo = new HashMap();
	private int addMoreCounter;
	private HashMap mapGroupMembers = new HashMap();
	private ArrayList smsGrpMembers = new ArrayList();
	private String deleteTrnsPrtyID;
	
	private String deleteTrnsPrtyZone;
	private String deleteTrnsPrtyDist;
	private String deleteTrnsPrtyRole;
	
	private String deleteGrpMember;
	private int mapKeyCount=0;
	private String hdnDeleteGroup;
	private String hdnDeleteGrpMember;
	private String hdnRoleList;
	private String hdnZoneList;
	private String hdnDistrictList;
	private String durationFrom;
	private String durationTo;
	private int groupListCounter=0;
	private int groupMemberCounter=0;
	private int saveGrpSuccess =0;
	private String groupMemberId;
	private int systemUsers =0;
	private int nonSystemUsers =0;
	private int stateCounter =0;
	private int zoneCounter =0;
	private int districtCounter =0;
	private int roleViewCounter =0;
	private int smsSendSuccess = 0;
	private HashMap mapZoneList = new HashMap();
	private HashMap mapDistrictList = new HashMap();
	private HashMap nonSystemUserList = new HashMap();
	private HashMap mapRoleList = new HashMap();
	private String zoneFlag="";
	private String districtFlag="";
	
	private SMSAlertsDTO smsAlertsDTO = new SMSAlertsDTO();
  
  
  public ArrayList getStateUsersData()
  {
    return stateUsersData;
  }
  
  public void setStateUsersData(ArrayList stateUsersData) { this.stateUsersData = stateUsersData; }
  
  public ArrayList getZoneUsersData() {
    return zoneUsersData;
  }
  
  public void setZoneUsersData(ArrayList zoneUsersData) { this.zoneUsersData = zoneUsersData; }
  
  public ArrayList getDistrictUsersData() {
    return districtUsersData;
  }
  
  public void setDistrictUsersData(ArrayList districtUsersData) { this.districtUsersData = districtUsersData; }
  
  public ArrayList getSmsData() {
    return smsData;
  }
  
  public void setSmsData(ArrayList smsData) { this.smsData = smsData; }
  
  public String getErrorFlag() {
    return errorFlag;
  }
  
  public void setErrorFlag(String errorFlag) { this.errorFlag = errorFlag; }
  
  public String getCheckBox() {
    return checkBox;
  }
  
  public void setCheckBox(String string) { checkBox = string; }
  
  public String getSmsHindiContent() {
    return smsHindiContent;
  }
  
  public void setSmsHindiContent(String smsHindiContent) { this.smsHindiContent = smsHindiContent; }
  
  public String getSmsEnglishContent() {
    return smsEnglishContent;
  }
  
  public void setSmsEnglishContent(String smsEnglishContent) { this.smsEnglishContent = smsEnglishContent; }
  
  public String getCheckFlag() {
    return checkFlag;
  }
  
  public void setCheckFlag(String checkFlag) { this.checkFlag = checkFlag; }
  
  public String getIntValue() {
    return intValue;
  }
  
  public void setIntValue(String intValue) { this.intValue = intValue; }
  
  public String getCheckBoxFlag()
  {
    return checkBoxFlag;
  }
  
  public void setCheckBoxFlag(String checkBoxFlag) { this.checkBoxFlag = checkBoxFlag; }
  
  public String getSerialNo() {
    return serialNo;
  }
  
  public void setSerialNo(String serialNo) { this.serialNo = serialNo; }
  
  public String getUserId() {
    return userId;
  }
  
  public void setUserId(String userId) { this.userId = userId; }
  
  public String getAddMoreState()
  {
    return addMoreState;
  }
  
  public void setAddMoreState(String addMoreState) { this.addMoreState = addMoreState; }
  
  public ArrayList getStateData() {
    return stateData;
  }
  
  public void setStateData(ArrayList stateData) { this.stateData = stateData; }
  
  public ArrayList getZoneData() {
    return zoneData;
  }
  
  public void setZoneData(ArrayList zoneData) { this.zoneData = zoneData; }
  
  public String getStateName() {
    return stateName;
  }
  
  public void setStateName(String stateName) { this.stateName = stateName; }
  
  public String getUserMobileNumber() {
    return userMobileNumber;
  }
  
  public void setUserMobileNumber(String userMobileNumber) { this.userMobileNumber = userMobileNumber; }
  
  public String getDistrictName()
  {
    return districtName;
  }
  
  public void setDistrictName(String districtName) { this.districtName = districtName; }
  
  public ArrayList getDistrictData() {
    return districtData;
  }
  
  public void setDistrictData(ArrayList districtData) { this.districtData = districtData; }
  
  public String getZoneId() {
    return zoneId;
  }
  
  public void setZoneId(String zoneId) { this.zoneId = zoneId; }
  
  public String getZoneName() {
    return zoneName;
  }
  
  public void setZoneName(String zoneName) { this.zoneName = zoneName; }
  
  public String getUserTypes() {
    return userTypes;
  }
  
  public void setUserTypes(String userTypes) { this.userTypes = userTypes; }
  
  public String getActionName() {
    return actionName;
  }
  
  public void setActionName(String actionName) { this.actionName = actionName; }
  
  public String getSaveType() {
    return saveType;
  }
  
  public void setSaveType(String saveType) { this.saveType = saveType; }
  
  public SMSAlertsDTO getSmsdto()
  {
    return smsdto;
  }
  
  public void setSmsdto(SMSAlertsDTO smsdto) { this.smsdto = smsdto; }
  
  public String getUserType() {
    return userType;
  }
  
  public void setUserType(String userType) { this.userType = userType; }
  
  public String getUserZone() {
    return userZone;
  }
  
  public void setUserZone(String userZone) { this.userZone = userZone; }
  
  public String getUser_district() {
    return user_district;
  }
  
  public void setUser_district(String user_district) { this.user_district = user_district; }
  
  public String getUserDesignation() {
    return userDesignation;
  }
  
  public void setUserDesignation(String userDesignation) { this.userDesignation = userDesignation; }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName(String userName) { this.userName = userName; }
  
  public ArrayList getDistrictList()
  {
    return districtList;
  }
  
  public void setDistrictList(ArrayList districtList) { this.districtList = districtList; }
  
  public ArrayList getZoneList() {
    return zoneList;
  }
  
  public void setZoneList(ArrayList zoneList) { this.zoneList = zoneList; }

public String getGroupName() {
	return groupName;
}

public void setGroupName(String groupName) {
	this.groupName = groupName;
}

public HashMap getGroupInfo() {
	return groupInfo;
}

public void setGroupInfo(HashMap groupInfo) {
	this.groupInfo = groupInfo;
}

public HashMap getGroupMembersInfo() {
	return groupMembersInfo;
}

public void setGroupMembersInfo(HashMap groupMembersInfo) {
	this.groupMembersInfo = groupMembersInfo;
}

public int getAddMoreCounter() {
	return addMoreCounter;
}

public void setAddMoreCounter(int addMoreCounter) {
	this.addMoreCounter = addMoreCounter;
}

public HashMap getMapGroupMembers() {
	return mapGroupMembers;
}

public void setMapGroupMembers(HashMap mapGroupMembers) {
	this.mapGroupMembers = mapGroupMembers;
}

public ArrayList getSmsGrpMembers() {
	return smsGrpMembers;
}

public void setSmsGrpMembers(ArrayList smsGrpMembers) {
	this.smsGrpMembers = smsGrpMembers;
}

public String getDeleteTrnsPrtyID() {
	return deleteTrnsPrtyID;
}

public void setDeleteTrnsPrtyID(String deleteTrnsPrtyID) {
	this.deleteTrnsPrtyID = deleteTrnsPrtyID;
}

public String getDeleteTrnsPrtyZone() {
	return deleteTrnsPrtyZone;
}

public void setDeleteTrnsPrtyZone(String deleteTrnsPrtyZone) {
	this.deleteTrnsPrtyZone = deleteTrnsPrtyZone;
}

public String getDeleteTrnsPrtyDist() {
	return deleteTrnsPrtyDist;
}

public void setDeleteTrnsPrtyDist(String deleteTrnsPrtyDist) {
	this.deleteTrnsPrtyDist = deleteTrnsPrtyDist;
}

public String getDeleteTrnsPrtyRole() {
	return deleteTrnsPrtyRole;
}

public void setDeleteTrnsPrtyRole(String deleteTrnsPrtyRole) {
	this.deleteTrnsPrtyRole = deleteTrnsPrtyRole;
}

public String getDeleteGrpMember() {
	return deleteGrpMember;
}

public void setDeleteGrpMember(String deleteGrpMember) {
	this.deleteGrpMember = deleteGrpMember;
}

public int getMapKeyCount() {
	return mapKeyCount;
}

public void setMapKeyCount(int mapKeyCount) {
	this.mapKeyCount = mapKeyCount;
}

public String getHdnDeleteGroup() {
	return hdnDeleteGroup;
}

public void setHdnDeleteGroup(String hdnDeleteGroup) {
	this.hdnDeleteGroup = hdnDeleteGroup;
}

public String getHdnDeleteGrpMember() {
	return hdnDeleteGrpMember;
}

public void setHdnDeleteGrpMember(String hdnDeleteGrpMember) {
	this.hdnDeleteGrpMember = hdnDeleteGrpMember;
}

public String getHdnRoleList() {
	return hdnRoleList;
}

public void setHdnRoleList(String hdnRoleList) {
	this.hdnRoleList = hdnRoleList;
}

public String getHdnZoneList() {
	return hdnZoneList;
}

public void setHdnZoneList(String hdnZoneList) {
	this.hdnZoneList = hdnZoneList;
}

public String getHdnDistrictList() {
	return hdnDistrictList;
}

public void setHdnDistrictList(String hdnDistrictList) {
	this.hdnDistrictList = hdnDistrictList;
}

public String getDurationFrom() {
	return durationFrom;
}

public void setDurationFrom(String durationFrom) {
	this.durationFrom = durationFrom;
}

public String getDurationTo() {
	return durationTo;
}

public void setDurationTo(String durationTo) {
	this.durationTo = durationTo;
}

public int getGroupListCounter() {
	return groupListCounter;
}

public void setGroupListCounter(int groupListCounter) {
	this.groupListCounter = groupListCounter;
}

public int getGroupMemberCounter() {
	return groupMemberCounter;
}

public void setGroupMemberCounter(int groupMemberCounter) {
	this.groupMemberCounter = groupMemberCounter;
}

public int getSaveGrpSuccess() {
	return saveGrpSuccess;
}

public void setSaveGrpSuccess(int saveGrpSuccess) {
	this.saveGrpSuccess = saveGrpSuccess;
}

public String getGroupMemberId() {
	return groupMemberId;
}

public void setGroupMemberId(String groupMemberId) {
	this.groupMemberId = groupMemberId;
}

public int getSystemUsers() {
	return systemUsers;
}

public void setSystemUsers(int systemUsers) {
	this.systemUsers = systemUsers;
}

public int getNonSystemUsers() {
	return nonSystemUsers;
}

public void setNonSystemUsers(int nonSystemUsers) {
	this.nonSystemUsers = nonSystemUsers;
}

public int getStateCounter() {
	return stateCounter;
}

public void setStateCounter(int stateCounter) {
	this.stateCounter = stateCounter;
}

public int getZoneCounter() {
	return zoneCounter;
}

public void setZoneCounter(int zoneCounter) {
	this.zoneCounter = zoneCounter;
}

public int getDistrictCounter() {
	return districtCounter;
}

public void setDistrictCounter(int districtCounter) {
	this.districtCounter = districtCounter;
}

public int getRoleViewCounter() {
	return roleViewCounter;
}

public void setRoleViewCounter(int roleViewCounter) {
	this.roleViewCounter = roleViewCounter;
}

public int getSmsSendSuccess() {
	return smsSendSuccess;
}

public void setSmsSendSuccess(int smsSendSuccess) {
	this.smsSendSuccess = smsSendSuccess;
}

public HashMap getMapZoneList() {
	return mapZoneList;
}

public void setMapZoneList(HashMap mapZoneList) {
	this.mapZoneList = mapZoneList;
}

public HashMap getMapDistrictList() {
	return mapDistrictList;
}

public void setMapDistrictList(HashMap mapDistrictList) {
	this.mapDistrictList = mapDistrictList;
}

public HashMap getNonSystemUserList() {
	return nonSystemUserList;
}

public void setNonSystemUserList(HashMap nonSystemUserList) {
	this.nonSystemUserList = nonSystemUserList;
}

public HashMap getMapRoleList() {
	return mapRoleList;
}

public void setMapRoleList(HashMap mapRoleList) {
	this.mapRoleList = mapRoleList;
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

public void setSmsAlertsDTO(SMSAlertsDTO smsAlertsDTO) {
	this.smsAlertsDTO = smsAlertsDTO;
}

public SMSAlertsDTO getSmsAlertsDTO() {
	return smsAlertsDTO;
}
}
