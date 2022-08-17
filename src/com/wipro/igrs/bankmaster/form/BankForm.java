package com.wipro.igrs.bankmaster.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;

public class BankForm extends BaseForm {

	private String bankId;
	private String bankName;
	private String bankAddress;
	private String bankPhoneNumber;
	private String status;
	private String createdBy;
	private String createdDate;
	private String updateBy;
	private String updateDate;
	private String pageName;
	private ArrayList bankList;
	private String [] selected;
	private String oldName;

    public ArrayList getBankList() {
		return bankList;
	}

	public void setBankList(ArrayList bankList) {
		this.bankList = bankList;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public BankForm () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
   
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {

    	return null;
    }

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankPhoneNumber() {
		return bankPhoneNumber;
	}

	public void setBankPhoneNumber(String bankPhoneNumber) {
		this.bankPhoneNumber = bankPhoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String[] getSelected() {
		return selected;
	}

	public void setSelected(String[] selected) {
		this.selected = selected;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}


}