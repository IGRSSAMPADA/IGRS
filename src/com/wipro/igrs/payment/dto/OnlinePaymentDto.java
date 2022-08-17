package com.wipro.igrs.payment.dto;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class OnlinePaymentDto implements Serializable{

	
	@Override
	public String toString() {
		return "OnlinePaymentDto [challanNumber=" + challanNumber + ", moduleId=" + moduleId + ", otherId=" + otherId
				+ ", userId=" + userId + ", formPath=" + formPath + ", formName=" + formName + ", modName=" + modName
				+ ", fnName=" + fnName + ", paymentFormPath=" + paymentFormPath + ", oldformName=" + oldformName
				+ ", funcId=" + funcId + ", parentAmount=" + parentAmount + ", parentTable=" + parentTable
				+ ", parentKey=" + parentKey + ", forwardPath=" + forwardPath + ", parentColumnName=" + parentColumnName
				+ ", parentOffId=" + parentOffId + ", parentOffName=" + parentOffName + ", parentDistId=" + parentDistId
				+ ", parentDistName=" + parentDistName + ", parentRefId=" + parentRefId + ", crnNumber=" + crnNumber
				+ ", cin=" + cin + ", brn=" + brn + ", scrollNumber=" + scrollNumber + ", scrollDate=" + scrollDate
				+ ", trasaction_date_time=" + trasaction_date_time + ", payingAmout=" + payingAmout + ", language="
				+ language + ", statusTrs=" + statusTrs + ", statusDesc=" + statusDesc + ", urn=" + urn + ", amount="
				+ amount + ", challandate=" + challandate + ", checksum=" + checksum + "]";
	}
	private String challanNumber;
	private String moduleId;
	private String otherId;
	private String userId;
	private String formPath;
	private String formName;
	private String modName ;
	private String fnName;
	private String paymentFormPath;
	private String oldformName;
	public String getModName() {
		return modName;
	}
	public void setModName(String modName) {
		this.modName = modName;
	}
	public String getFnName() {
		return fnName;
	}
	public void setFnName(String fnName) {
		this.fnName = fnName;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getFormPath() {
		return formPath;
	}
	public void setFormPath(String formPath) {
		this.formPath = formPath;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	private String funcId;
	private String parentAmount;
	private String parentTable;
	private String parentKey;
	private String forwardPath;
	private String parentColumnName;
	private String parentOffId;
	private String parentOffName;
	private String parentDistId;
	private String parentDistName;
	private String parentRefId;
	private String crnNumber;
	private String cin;
	private String brn;
	private String scrollNumber;
	private String scrollDate;
	private String trasaction_date_time;
	private String payingAmout;
	private String language;
	//Ajit
	private String statusTrs;
	private String statusDesc;
	private String urn;
	//added
	private String amount;
	private String challandate;
	private String checksum;
	
	
	public String getStatusTrs() {
		return statusTrs;
	}
	public void setStatusTrs(String statusTrs) {
		this.statusTrs = statusTrs;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getUrn() {
		return urn;
	}
	public void setUrn(String urn) {
		this.urn = urn;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getChallandate() {
		return challandate;
	}
	public void setChallandate(String challandate) {
		this.challandate = challandate;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getBrn() {
		return brn;
	}
	public void setBrn(String brn) {
		this.brn = brn;
	}
	public String getScrollNumber() {
		return scrollNumber;
	}
	public void setScrollNumber(String scrollNumber) {
		this.scrollNumber = scrollNumber;
	}
	public String getScrollDate() {
		return scrollDate;
	}
	public void setScrollDate(String scrollDate) {
		this.scrollDate = scrollDate;
	}
	public String getTrasaction_date_time() {
		return trasaction_date_time;
	}
	public void setTrasaction_date_time(String trasaction_date_time) {
		this.trasaction_date_time = trasaction_date_time;
	}
	public String getCrnNumber() {
		return crnNumber;
	}
	public void setCrnNumber(String crnNumber) {
		this.crnNumber = crnNumber;
	}
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	public String getParentAmount() {
		return parentAmount;
	}
	public void setParentAmount(String parentAmount) {
		this.parentAmount = parentAmount;
	}
	public String getParentTable() {
		return parentTable;
	}
	public void setParentTable(String parentTable) {
		this.parentTable = parentTable;
	}
	public String getParentKey() {
		return parentKey;
	}
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	public String getForwardPath() {
		return forwardPath;
	}
	public void setForwardPath(String forwardPath) {
		this.forwardPath = forwardPath;
	}
	public String getParentColumnName() {
		return parentColumnName;
	}
	public void setParentColumnName(String parentColumnName) {
		this.parentColumnName = parentColumnName;
	}
	public String getParentOffId() {
		return parentOffId;
	}
	public void setParentOffId(String parentOffId) {
		this.parentOffId = parentOffId;
	}
	public String getParentOffName() {
		return parentOffName;
	}
	public void setParentOffName(String parentOffName) {
		this.parentOffName = parentOffName;
	}
	public String getParentDistId() {
		return parentDistId;
	}
	public void setParentDistId(String parentDistId) {
		this.parentDistId = parentDistId;
	}
	public String getParentDistName() {
		return parentDistName;
	}
	public void setParentDistName(String parentDistName) {
		this.parentDistName = parentDistName;
	}
	public String getParentRefId() {
		return parentRefId;
	}
	public void setParentRefId(String parentRefId) {
		this.parentRefId = parentRefId;
	}
	public String getChallanNumber() {
		return challanNumber;
	}
	public void setChallanNumber(String challanNumber) {
		this.challanNumber = challanNumber;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getOtherId() {
		return otherId;
	}
	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}
	public void setPayingAmout(String payingAmout) {
		this.payingAmout = payingAmout;
	}
	public String getPayingAmout() {
		return payingAmout;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLanguage() {
		return language;
	}
	public void setPaymentFormPath(String paymentFormPath) {
		this.paymentFormPath = paymentFormPath;
	}
	public String getPaymentFormPath() {
		return paymentFormPath;
	}
	public void setOldformName(String oldformName) {
		this.oldformName = oldformName;
	}
	public String getOldformName() {
		return oldformName;
	}
	
}
