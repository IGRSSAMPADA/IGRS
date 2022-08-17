package com.wipro.igrs.spotinspslabmaster.actionforms;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InspectionActionForm extends ActionForm{

	private Vector allDTOs;
	
	private String slabMinRange;
	private String slabMaxRange;
	private String status;
	private String percentage;
	private String CreatedBy;
	private String modifiedBy;
	private String createdDate;
	private String minSpotInsp;
	private String timeFrom;
	private String timeTo;
	private String remarks;
	private String approvalStatus;
	private String approvalRemarks;
	private String succMsg;
	 private String pageTitle;
	 private String pageName;
	 private String statusdesc;
	 private String slabId;
	private String language;
	
	
	public String getSlabId() {
		return slabId;
	}

	public void setSlabId(String slabId) {
		this.slabId = slabId;
	}

	public String getStatusdesc() {
		return statusdesc;
	}

	public void setStatusdesc(String statusdesc) {
		this.statusdesc = statusdesc;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getSuccMsg() {
		return succMsg;
	}

	public void setSuccMsg(String succMsg) {
		this.succMsg = succMsg;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalRemarks() {
		return approvalRemarks;
	}

	public void setApprovalRemarks(String approvalRemarks) {
		this.approvalRemarks = approvalRemarks;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	private String[] selected;
	
	public String[] getSelected() {
		return selected;
	}

	public void setSelected(String[] selected) {
		this.selected = selected;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}

	public Vector getAllDTOs() {
		return allDTOs;
	}

	public void setAllDTOs(Vector allDTOs) {
		this.allDTOs = allDTOs;
	}

	public String getSlabMinRange() {
		return slabMinRange;
	}

	public void setSlabMinRange(String slabMinRange) {
		this.slabMinRange = slabMinRange;
	}

	public String getSlabMaxRange() {
		return slabMaxRange;
	}

	public void setSlabMaxRange(String slabMaxRange) {
		this.slabMaxRange = slabMaxRange;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getMinSpotInsp() {
		return minSpotInsp;
	}

	public void setMinSpotInsp(String minSpotInsp) {
		this.minSpotInsp = minSpotInsp;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}



}
