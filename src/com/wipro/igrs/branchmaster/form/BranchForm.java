package com.wipro.igrs.branchmaster.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;

public class BranchForm extends BaseForm {

	private String bankId;
	private String branchId;
	private String branchName;
	private String branchCode;
	private String branchEmail;
	private String branchAddress;
	private String branchPhoneNumber;
	private String status;
	private String createdBy;
	private String createdDate;
	private String updateBy;
	private String updateDate;
	private String bankName;

	private ArrayList branchList;
	private String [] selected;
	private String oldName;
	private ArrayList allBanks;


	public BranchForm () {
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

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchEmail() {
		return branchEmail;
	}

	public void setBranchEmail(String branchEmail) {
		this.branchEmail = branchEmail;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	

	public String getBranchPhoneNumber() {
		return branchPhoneNumber;
	}

	public void setBranchPhoneNumber(String branchPhoneNumber) {
		this.branchPhoneNumber = branchPhoneNumber;
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

	public ArrayList getBranchList() {
		return branchList;
	}

	public void setBranchList(ArrayList branchList) {
		this.branchList = branchList;
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

	public ArrayList getAllBanks() {
		return allBanks;
	}

	public void setAllBanks(ArrayList allBanks) {
		this.allBanks = allBanks;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	

}