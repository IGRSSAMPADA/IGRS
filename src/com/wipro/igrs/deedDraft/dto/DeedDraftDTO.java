package com.wipro.igrs.deedDraft.dto;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

public class DeedDraftDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionName;
	private String deedContent;
	private String pageId;
	private String deedName;
	private String firstPageChk;
	private String deedId;
	private String saveCheck;
	private String deedStatus;
	private String deedType;
	private String deedDate;
	private String editFlag;
	private String lastPageChk;
	private String editCheck;
	private String addMorePage;
	private String firstPageLimit;
	private String restPagesLimit;
	private String radioValue;
	private String selectedDeed;
	private String checkSign = "";
	private String validDeed;
	private String deedPage;
	private FormFile deedUploadPath;
	private String uploadCheck = "1";
	private String uploadPath;
	private String upOrNot;
	private String initiationNumber;
	private String errorFlag;
	private String deedNameCh;
	private String instName;
	private String instId;
	private String instType;
	private String appTypeId;
	private String regTxnId;
	private String deedCheck;
	private String deedAppType;

	public String getDeedAppType() {
		return deedAppType;
	}

	public void setDeedAppType(String deedAppType) {
		this.deedAppType = deedAppType;
	}

	public String getDeedCheck() {
		return deedCheck;
	}

	public void setDeedCheck(String deedCheck) {
		this.deedCheck = deedCheck;
	}

	public String getRegTxnId() {
		return regTxnId;
	}

	public void setRegTxnId(String regTxnId) {
		this.regTxnId = regTxnId;
	}

	public String getAppTypeId() {
		return appTypeId;
	}

	public void setAppTypeId(String appTypeId) {
		this.appTypeId = appTypeId;
	}

	public String getDeedNameCh() {
		return deedNameCh;
	}

	public void setDeedNameCh(String deedNameCh) {
		this.deedNameCh = deedNameCh;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getInstType() {
		return instType;
	}

	public void setInstType(String instType) {
		this.instType = instType;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUpOrNot() {
		return upOrNot;
	}

	public void setUpOrNot(String upOrNot) {
		this.upOrNot = upOrNot;
	}

	public FormFile getDeedUploadPath() {
		return deedUploadPath;
	}

	public void setDeedUploadPath(FormFile deedUploadPath) {
		this.deedUploadPath = deedUploadPath;
	}

	public String getDeedPage() {
		return deedPage;
	}

	public void setDeedPage(String deedPage) {
		this.deedPage = deedPage;
	}

	public String getUploadCheck() {
		return uploadCheck;
	}

	public void setUploadCheck(String uploadCheck) {
		this.uploadCheck = uploadCheck;
	}

	public String getValidDeed() {
		return validDeed;
	}

	public void setValidDeed(String validDeed) {
		this.validDeed = validDeed;
	}

	public String getCheckSign() {
		return checkSign;
	}

	public void setCheckSign(String checkSign) {
		this.checkSign = checkSign;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDeedContent() {
		return deedContent;
	}

	public void setDeedContent(String deedContent) {
		this.deedContent = deedContent;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getDeedName() {
		return deedName;
	}

	public void setDeedName(String deedName) {
		this.deedName = deedName;
	}

	public String getFirstPageChk() {
		return firstPageChk;
	}

	public void setFirstPageChk(String firstPageChk) {
		this.firstPageChk = firstPageChk;
	}

	public String getDeedId() {
		return deedId;
	}

	public void setDeedId(String deedId) {
		this.deedId = deedId;
	}

	public String getSaveCheck() {
		return saveCheck;
	}

	public void setSaveCheck(String saveCheck) {
		this.saveCheck = saveCheck;
	}

	public String getDeedStatus() {
		return deedStatus;
	}

	public void setDeedStatus(String deedStatus) {
		this.deedStatus = deedStatus;
	}

	public String getDeedType() {
		return deedType;
	}

	public void setDeedType(String deedType) {
		this.deedType = deedType;
	}

	public String getDeedDate() {
		return deedDate;
	}

	public void setDeedDate(String deedDate) {
		this.deedDate = deedDate;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getLastPageChk() {
		return lastPageChk;
	}

	public void setLastPageChk(String lastPageChk) {
		this.lastPageChk = lastPageChk;
	}

	public String getEditCheck() {
		return editCheck;
	}

	public void setEditCheck(String editCheck) {
		this.editCheck = editCheck;
	}

	public String getAddMorePage() {
		return addMorePage;
	}

	public void setAddMorePage(String addMorePage) {
		this.addMorePage = addMorePage;
	}

	public String getFirstPageLimit() {
		return firstPageLimit;
	}

	public void setFirstPageLimit(String firstPageLimit) {
		this.firstPageLimit = firstPageLimit;
	}

	public String getRestPagesLimit() {
		return restPagesLimit;
	}

	public void setRestPagesLimit(String restPagesLimit) {
		this.restPagesLimit = restPagesLimit;
	}

	public String getRadioValue() {
		return radioValue;
	}

	public void setRadioValue(String radioValue) {
		this.radioValue = radioValue;
	}

	public String getSelectedDeed() {
		return selectedDeed;
	}

	public void setSelectedDeed(String selectedDeed) {
		this.selectedDeed = selectedDeed;
	}

	public String getInitiationNumber() {
		return initiationNumber;
	}

	public void setInitiationNumber(String initiationNumber) {
		this.initiationNumber = initiationNumber;
	}

	public String getErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(String errorFlag) {
		this.errorFlag = errorFlag;
	}
	private String deedAppStatus;

	public String getDeedAppStatus() {
		return deedAppStatus;
	}

	public void setDeedAppStatus(String deedAppStatus) {
		this.deedAppStatus = deedAppStatus;
	}
}
