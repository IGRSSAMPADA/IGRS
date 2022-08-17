package com.wipro.igrs.treasuryMaster.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.treasuryMaster.dto.TreasureDTO;

public class TreasuryForm extends org.apache.struts.action.ActionForm {

	private String pageName;
	private String treasuryId;
	private String treasuryName;
	private String treasuryAddress;
	private String treasuryPhone;
	private String status;
	private String createdBy;
	private String updatedBy;
	private String updateDate;
	private String creationDate;
	private TreasureDTO dto;
	private List statusList; 
	private String [] selected;
	
    public String[] getSelected() {
		return selected;
	}

	public void setSelected(String[] selected) {
		this.selected = selected;
	}

	public List getStatusList() {
		return statusList;
	}

	public void setStatusList(List statusList) {
		this.statusList = statusList;
	}

	public TreasuryForm () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
    	pageName="reset";
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        return null;
    }

	public String getTreasuryId() {
		return treasuryId;
	}

	public void setTreasuryId(String treasuryId) {
		this.treasuryId = treasuryId;
	}

	public String getTreasuryName() {
		return treasuryName;
	}

	public void setTreasuryName(String treasuryName) {
		this.treasuryName = treasuryName;
	}

	public String getTreasuryAddress() {
		return treasuryAddress;
	}

	public void setTreasuryAddress(String treasuryAddress) {
		this.treasuryAddress = treasuryAddress;
	}

	public String getTreasuryPhone() {
		return treasuryPhone;
	}

	public void setTreasuryPhone(String treasuryPhone) {
		this.treasuryPhone = treasuryPhone;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public TreasureDTO getDto() {
		return dto;
	}

	public void setDto(TreasureDTO dto) {
		this.dto = dto;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}


}