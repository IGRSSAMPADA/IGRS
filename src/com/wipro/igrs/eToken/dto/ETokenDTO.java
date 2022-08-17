package com.wipro.igrs.eToken.dto;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class ETokenDTO extends ActionForm {
	private String language;
	private String districtId;
	private String applicationType;
	private String typeOfToken;
	private String noOfTokens;
	private String timeTaken;
	private String valueCounterList;
	private String CounterUserId;
	private String errorFlag;
	private String serialNo;
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	private String districtName;
	private String check;
	private String sroId;
	private String sroName;
	private String userType;
	private String counterName;
	private String counterDesc;
	private String actionName;
	private String counterId;
	private String status;
	private String statusChk;
	private String makerId;
	private String checkerId;
	private String makerName;
	private String checkerName;
	private String mappingId;
	private String delete;
	private String userId;
	private String userName;
	private String availableStatus;
	private String selectedUser;
	private String checkBox;
	private String checkBOxSelected;
	private String selectedUserMappingIds;
	private String counterType;
	private String counterOfficeMappingId;
	private String checkFlag;
	
	//Following added by Roopam
	private String regInitId;
	private String slotDate;
	private String slotFromTime;
	private String slotToTime;
	private int noPersons;
	private int category;  //1 for special, 0 for normal
	
	
	
	public String getEeTokenStatus() {
		return EeTokenStatus;
	}
	public void setEeTokenStatus(String eeTokenStatus) {
		EeTokenStatus = eeTokenStatus;
	}
	private String tokenNumber;
	private int appStatus;
	
	//Following added by tanushree
	
	
	private String EeTokenStatus;
	
	private String etokenNumberC;
	private String counterNumberC;
	private String flagC;
	private String statusC;
	private String createdDateC;
	private String createdByC;
	private String eTokenUpdateRemarks;
	
	private String counterNumber;
	private String counter;
	private String updateRemarks;
	private String cancelMsg;
	
	private String etokenNumberR;
	private String counterNumberR;
	private String regIDR;
	private String statusR;
	private String timeR;
	private ArrayList eTokenReport;
	private ArrayList userCounterMappingList;
	private ArrayList counterOfficeMappingList;
	private ArrayList counterTypeList;
	private ArrayList counterData;
	private ArrayList attendenceData;
	
	private String searchOffc;
	private String searchFromDate;
	private String searchToDate;
	public String getSearchOffc() {
		return searchOffc;
	}
	public void setSearchOffc(String searchOffc) {
		this.searchOffc = searchOffc;
	}
	public String getSearchFromDate() {
		return searchFromDate;
	}
	public void setSearchFromDate(String searchFromDate) {
		this.searchFromDate = searchFromDate;
	}
	public String getSearchToDate() {
		return searchToDate;
	}
	public void setSearchToDate(String searchToDate) {
		this.searchToDate = searchToDate;
	}
	
	
	public ArrayList getETokenReport() {
		return eTokenReport;
	}
	public void setETokenReport(ArrayList tokenReport) {
		eTokenReport = tokenReport;
	}
	public String getStatusR() {
		return statusR;
	}
	public void setStatusR(String statusR) {
		this.statusR = statusR;
	}
	public String getTimeR() {
		return timeR;
	}
	public void setTimeR(String timeR) {
		this.timeR = timeR;
	}
	public String getRegID() {
		return regIDR;
	}
	public void setRegID(String regID) {
		this.regIDR = regID;
	}
	public String getEtokenNumberR() {
		return etokenNumberR;
	}
	public void setEtokenNumberR(String etokenNumberR) {
		this.etokenNumberR = etokenNumberR;
	}
	public String getCounterNumberR() {
		return counterNumberR;
	}
	public void setCounterNumberR(String counterNumberR) {
		this.counterNumberR = counterNumberR;
	}
	public String getRegIDR() {
		return regIDR;
	}
	public void setRegIDR(String regIDR) {
		this.regIDR = regIDR;
	}
	private ArrayList etokenReportDetails;
	
	
	public ArrayList getEtokenReportDetails() {
		return etokenReportDetails;
	}
	public void setEtokenReportDetails(ArrayList etokenReportDetails) {
		this.etokenReportDetails = etokenReportDetails;
	}
	public String getCancelMsg() {
		return cancelMsg;
	}
	public void setCancelMsg(String cancelMsg) {
		this.cancelMsg = cancelMsg;
	}
	public String getUpdateRemarks() {
		return updateRemarks;
	}
	public void setUpdateRemarks(String updateRemarks) {
		this.updateRemarks = updateRemarks;
	}
	
	
	public String getCounter() {
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
	}
	public String getCounterNumber() {
		return counterNumber;
	}
	public void setCounterNumber(String counterNumber) {
		this.counterNumber = counterNumber;
	}
	
	public String getETokenUpdateRemarks() {
		return eTokenUpdateRemarks;
	}
	public void setETokenUpdateRemarks(String tokenUpdateRemarks) {
		eTokenUpdateRemarks = tokenUpdateRemarks;
	}
	public String getEtokenNumberC() {
		return etokenNumberC;
	}
	public void setEtokenNumberC(String etokenNumberC) {
		this.etokenNumberC = etokenNumberC;
	}
	public String getCounterNumberC() {
		return counterNumberC;
	}
	public void setCounterNumberC(String counterNumberC) {
		this.counterNumberC = counterNumberC;
	}
	public String getFlagC() {
		return flagC;
	}
	public void setFlagC(String flagC) {
		this.flagC = flagC;
	}
	public String getStatusC() {
		return statusC;
	}
	public void setStatusC(String statusC) {
		this.statusC = statusC;
	}
	public String getCreatedDateC() {
		return createdDateC;
	}
	public void setCreatedDateC(String createdDateC) {
		this.createdDateC = createdDateC;
	}
	public String getCreatedByC() {
		return createdByC;
	}
	public void setCreatedByC(String createdByC) {
		this.createdByC = createdByC;
	}
	
	

	
	
	public int getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(int appStatus) {
		this.appStatus = appStatus;
	}
	public String getTokenNumber() {
		return tokenNumber;
	}
	public void setTokenNumber(String tokenNumber) {
		this.tokenNumber = tokenNumber;
	}
	public int getNoPersons() {
		return noPersons;
	}
	public void setNoPersons(int noPersons) {
		this.noPersons = noPersons;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getSlotDate() {
		return slotDate;
	}
	public void setSlotDate(String slotDate) {
		this.slotDate = slotDate;
	}
	public String getSlotFromTime() {
		return slotFromTime;
	}
	public void setSlotFromTime(String slotFromTime) {
		this.slotFromTime = slotFromTime;
	}
	public String getSlotToTime() {
		return slotToTime;
	}
	public void setSlotToTime(String slotToTime) {
		this.slotToTime = slotToTime;
	}
	public String getRegInitId() {
		return regInitId;
	}
	public void setRegInitId(String regInitId) {
		this.regInitId = regInitId;
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
	public String getSroId() {
		return sroId;
	}
	public void setSroId(String sroId) {
		this.sroId = sroId;
	}
	public String getSroName() {
		return sroName;
	}
	public void setSroName(String sroName) {
		this.sroName = sroName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public String getCounterDesc() {
		return counterDesc;
	}
	public void setCounterDesc(String counterDesc) {
		this.counterDesc = counterDesc;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getCounterId() {
		return counterId;
	}
	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusChk() {
		return statusChk;
	}
	public void setStatusChk(String statusChk) {
		this.statusChk = statusChk;
	}
	public String getMakerId() {
		return makerId;
	}
	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}
	public String getCheckerId() {
		return checkerId;
	}
	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}
	public String getMakerName() {
		return makerName;
	}
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public String getMappingId() {
		return mappingId;
	}
	public void setMappingId(String mappingId) {
		this.mappingId = mappingId;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAvailableStatus() {
		return availableStatus;
	}
	public void setAvailableStatus(String availableStatus) {
		this.availableStatus = availableStatus;
	}
	public String getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(String selectedUser) {
		this.selectedUser = selectedUser;
	}
	public String getSelectedUserMappingIds() {
		return selectedUserMappingIds;
	}
	public void setSelectedUserMappingIds(String selectedUserMappingIds) {
		this.selectedUserMappingIds = selectedUserMappingIds;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}
	public String getCheckBox() {
		return checkBox;
	}
	public void setCheckBOxSelected(String checkBOxSelected) {
		this.checkBOxSelected = checkBOxSelected;
	}
	public String getCheckBOxSelected() {
		return checkBOxSelected;
	}
	public void setTypeOfToken(String typeOfToken) {
		this.typeOfToken = typeOfToken;
	}
	public String getTypeOfToken() {
		return typeOfToken;
	}
	public void setNoOfTokens(String noOfTokens) {
		this.noOfTokens = noOfTokens;
	}
	public String getNoOfTokens() {
		return noOfTokens;
	}
	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}
	public String getTimeTaken() {
		return timeTaken;
	}
	public String getCounterType() {
		return counterType;
	}
	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}
	public String getCounterOfficeMappingId() {
		return counterOfficeMappingId;
	}
	public void setCounterOfficeMappingId(String counterOfficeMappingId) {
		this.counterOfficeMappingId = counterOfficeMappingId;
	}
	public String getValueCounterList() {
		return valueCounterList;
	}
	public void setValueCounterList(String valueCounterList) {
		this.valueCounterList = valueCounterList;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public ArrayList getUserCounterMappingList() {
		return userCounterMappingList;
	}
	public void setUserCounterMappingList(ArrayList userCounterMappingList) {
		this.userCounterMappingList = userCounterMappingList;
	}
	public ArrayList getCounterOfficeMappingList() {
		return counterOfficeMappingList;
	}
	public void setCounterOfficeMappingList(ArrayList counterOfficeMappingList) {
		this.counterOfficeMappingList = counterOfficeMappingList;
	}
	public String getCounterUserId() {
		return CounterUserId;
	}
	public void setCounterUserId(String counterUserId) {
		CounterUserId = counterUserId;
	}
	public ArrayList getCounterTypeList() {
		return counterTypeList;
	}
	public void setCounterTypeList(ArrayList counterTypeList) {
		this.counterTypeList = counterTypeList;
	}
	public ArrayList getCounterData() {
		return counterData;
	}
	public void setCounterData(ArrayList counterData) {
		this.counterData = counterData;
	}
	public ArrayList getAttendenceData() {
		return attendenceData;
	}
	public void setAttendenceData(ArrayList attendenceData) {
		this.attendenceData = attendenceData;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(String errorFlag) {
		this.errorFlag = errorFlag;
	}
	
}
