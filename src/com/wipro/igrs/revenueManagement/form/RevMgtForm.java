package com.wipro.igrs.revenueManagement.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.revenueManagement.dto.RevMgtDTO;

public class RevMgtForm extends ActionForm {

	private static long serialVersionUID = 1L;
	RevMgtDTO rmDto = new RevMgtDTO();
	
	private String formName;
	private String actionName;
	private String uid;
	private String offid;
    private String toDate;
    private String fromDate;
    private ArrayList viewResult=new ArrayList();
  
	private String revenueFromDate;
	private String revenueToDate;

	
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @param serialVersionUID the serialVersionUID to set
	 */
	public static void setSerialVersionUID(long serialVersionUID) {
		RevMgtForm.serialVersionUID = serialVersionUID;
	}

	/**
	 * @return the revenueFromDate
	 */
	public String getRevenueFromDate() {
		return revenueFromDate;
	}

	/**
	 * @param revenueFromDate the revenueFromDate to set
	 */
	public void setRevenueFromDate(String revenueFromDate) {
		this.revenueFromDate = revenueFromDate;
	}

	/**
	 * @return the revenueToDate
	 */
	public String getRevenueToDate() {
		return revenueToDate;
	}

	/**
	 * @param revenueToDate the revenueToDate to set
	 */
	public void setRevenueToDate(String revenueToDate) {
		this.revenueToDate = revenueToDate;
	}

	/**
	 * @return the viewResult
	 */
	public ArrayList getViewResult() {
		return viewResult;
	}

	/**
	 * @param viewResult the viewResult to set
	 */
	public void setViewResult(ArrayList viewResult) {
		this.viewResult = viewResult;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param serialversionuid the serialversionuid to set
	 */
	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the offid
	 */
	public String getOffid() {
		return offid;
	}

	/**
	 * @param offid the offid to set
	 */
	public void setOffid(String offid) {
		this.offid = offid;
	}

	/**
	 * @return the rmDto
	 */
	public RevMgtDTO getRmDto() {
		return rmDto;
	}

	/**
	 * @param rmDto the rmDto to set
	 */
	public void setRmDto(RevMgtDTO rmDto) {
		this.rmDto = rmDto;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public void setDetailList(ArrayList list) {
		// TODO Auto-generated method stub
		
	}

	public void setCitizenDTO(RevMgtDTO dto1) {
		// TODO Auto-generated method stub
		
	}

	
}
