package com.wipro.igrs.eToken.form;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.eToken.dto.ETokenDTO;

public class ETokenForm extends ActionForm {
	
	ETokenDTO etokenDTO = new ETokenDTO(); 
	
	
	private String language;
	private ArrayList districtList = new ArrayList();
	private ArrayList activeMakerCheckerList = new ArrayList();
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	private String errorMsg ;
	private ArrayList sroList = new ArrayList();
	private ArrayList counterDetlsList = new ArrayList();
	private ArrayList countersList = new ArrayList();
	private ArrayList makersList = new ArrayList();
	private ArrayList makerCounterMappingList = new ArrayList();
	private ArrayList checkersList = new ArrayList();
	private ArrayList checkerCounterMappingList = new ArrayList();
	private ArrayList availableUsersList = new ArrayList();
	private ArrayList userCounterMappingList=new ArrayList();
	private ArrayList counterOfficeMappingList=new ArrayList();
	private String checkDisplay;
	private String valueCounterList;
	private String counterOfficeMappingId;
	private String userId;
	private String serialNo;
	private String check;
	private String counterType;
	private String counterUserId;
	private Queue<String> makerNormalQueue=new LinkedList<String>();
	private Queue<String> makerSpecialQueue=new LinkedList<String>();
	private Queue<String> checkerNormalQueue=new LinkedList<String>();
	private Queue<String> checkerSpecialQueue=new LinkedList<String>();
	private ArrayList counterTypeList;
	private ArrayList counterData;
	private ArrayList attendenceData;
	private String actionName;
	private String saveType;
	private String checkBoxFlag;
	private String UncheckBoxFlag;
	private String counterName;
	private String checkFlag;
	private String counterId;
	private String districtId;
	
    private ArrayList etokenReportDetails = new ArrayList();//added by tanushree on 10 jan 2014
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
	
	
	public ArrayList getEtokenReportDetails() {
		return etokenReportDetails;
	}
	public void setEtokenReportDetails(ArrayList etokenReportDetails) {
		this.etokenReportDetails = etokenReportDetails;
	}
	public Queue<String> getMakerNormalQueue() {
		return makerNormalQueue;
	}
	public void setMakerNormalQueue(Queue<String> makerNormalQueue) {
		this.makerNormalQueue = makerNormalQueue;
	}
	public Queue<String> getMakerSpecialQueue() {
		return makerSpecialQueue;
	}
	public void setMakerSpecialQueue(Queue<String> makerSpecialQueue) {
		this.makerSpecialQueue = makerSpecialQueue;
	}
	public Queue<String> getCheckerNormalQueue() {
		return checkerNormalQueue;
	}
	public void setCheckerNormalQueue(Queue<String> checkerNormalQueue) {
		this.checkerNormalQueue = checkerNormalQueue;
	}
	public Queue<String> getCheckerSpecialQueue() {
		return checkerSpecialQueue;
	}
	public void setCheckerSpecialQueue(Queue<String> checkerSpecialQueue) {
		this.checkerSpecialQueue = checkerSpecialQueue;
	}
	public ETokenDTO getEtokenDTO() {
		return etokenDTO;
	}
	public void setEtokenDTO(ETokenDTO etokenDTO) {
		this.etokenDTO = etokenDTO;
	}
	public ArrayList getDistrictList() {
		return districtList;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	public ArrayList getSroList() {
		return sroList;
	}
	public void setSroList(ArrayList sroList) {
		this.sroList = sroList;
	}
	public String getCheckDisplay() {
		return checkDisplay;
	}
	public void setCheckDisplay(String checkDisplay) {
		this.checkDisplay = checkDisplay;
	}
	public ArrayList getCounterDetlsList() {
		return counterDetlsList;
	}
	public void setCounterDetlsList(ArrayList counterDetlsList) {
		this.counterDetlsList = counterDetlsList;
	}
	public ArrayList getCountersList() {
		return countersList;
	}
	public void setCountersList(ArrayList countersList) {
		this.countersList = countersList;
	}
	public ArrayList getMakersList() {
		return makersList;
	}
	public void setMakersList(ArrayList makersList) {
		this.makersList = makersList;
	}
	public ArrayList getMakerCounterMappingList() {
		return makerCounterMappingList;
	}
	public void setMakerCounterMappingList(ArrayList makerCounterMappingList) {
		this.makerCounterMappingList = makerCounterMappingList;
	}
	public ArrayList getCheckersList() {
		return checkersList;
	}
	public void setCheckersList(ArrayList checkersList) {
		this.checkersList = checkersList;
	}
	public ArrayList getCheckerCounterMappingList() {
		return checkerCounterMappingList;
	}
	public void setCheckerCounterMappingList(ArrayList checkerCounterMappingList) {
		this.checkerCounterMappingList = checkerCounterMappingList;
	}
	public ArrayList getAvailableUsersList() {
		return availableUsersList;
	}
	public void setAvailableUsersList(ArrayList availableUsersList) {
		this.availableUsersList = availableUsersList;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getErrorMsg() {
		return errorMsg;
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
	public String getValueCounterList() {
		return valueCounterList;
	}
	public void setValueCounterList(String valueCounterList) {
		this.valueCounterList = valueCounterList;
	}
	public String getCounterOfficeMappingId() {
		return counterOfficeMappingId;
	}
	public void setCounterOfficeMappingId(String counterOfficeMappingId) {
		this.counterOfficeMappingId = counterOfficeMappingId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getCounterType() {
		return counterType;
	}
	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public String getCounterUserId() {
		return counterUserId;
	}
	public void setCounterUserId(String counterUserId) {
		this.counterUserId = counterUserId;
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
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	public String getCheckBoxFlag() {
		return checkBoxFlag;
	}
	public void setCheckBoxFlag(String checkBoxFlag) {
		this.checkBoxFlag = checkBoxFlag;
	}
	public String getCounterName() {
		return counterName;
	}
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	public String getCounterId() {
		return counterId;
	}
	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getUncheckBoxFlag() {
		return UncheckBoxFlag;
	}
	public void setUncheckBoxFlag(String uncheckBoxFlag) {
		UncheckBoxFlag = uncheckBoxFlag;
	}
	public ArrayList getActiveMakerCheckerList() {
		return activeMakerCheckerList;
	}
	public void setActiveMakerCheckerList(ArrayList activeMakerCheckerList) {
		this.activeMakerCheckerList = activeMakerCheckerList;
	}
	
	
}
