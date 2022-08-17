/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.caseMonitoring.form;


import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.caseMonitoring.dto.CaseMonitoringDTO;
import com.wipro.igrs.caseMonitoring.dto.PartyDetailsDTO;



public class CaseMonitoringForm extends ActionForm {
    private CaseMonitoringDTO caseDTO = new CaseMonitoringDTO();



    //ArrayList for Challan/ Payment Detils
    private ArrayList paymentList = new ArrayList();

    private String formName = "";
    private String actionName = "";
 
    //Added by Nihar
    private ArrayList retrieveList;
    public String getCaseregdate() {
		return caseregdate;
	}
	public void setCaseregdate(String caseregdate) {
		this.caseregdate = caseregdate;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	private ArrayList revenueList = new ArrayList();
    private ArrayList sectionList = new ArrayList();
    
    //added by shruti
    private String caseregdate;
    private String districtName;
    private String noticeDate;
    private String recDate;
    private String challanDate;
    private String orderDate;
    private String orderRecDate;
    private String stampLabel;
    private String refundfeeflag;
    
    private String dueDate;
    
    private String language;
    
    public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	private PartyDetailsDTO partyDto = new PartyDetailsDTO(); 
    
    
   
	
    public PartyDetailsDTO getPartyDto() {
		return partyDto;
	}
	public void setPartyDto(PartyDetailsDTO partyDto) {
		this.partyDto = partyDto;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	private ArrayList reportlist=new ArrayList();
    
    
	public ArrayList getReportlist() {
		return reportlist;
	}
	public void setReportlist(ArrayList reportlist) {
		this.reportlist = reportlist;
	}
	public String getRefundfeeflag() {
		return refundfeeflag;
	}
	public void setRefundfeeflag(String refundfeeflag) {
		this.refundfeeflag = refundfeeflag;
	}
	public String getStampLabel() {
		return stampLabel;
	}
	public void setStampLabel(String stampLabel) {
		this.stampLabel = stampLabel;
	}
	public String getOrderRecDate() {
		return orderRecDate;
	}
	public void setOrderRecDate(String orderRecDate) {
		this.orderRecDate = orderRecDate;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getChallanDate() {
		return challanDate;
	}
	public void setChallanDate(String challanDate) {
		this.challanDate = challanDate;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	public String getRecDate() {
		return recDate;
	}
	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}
	public void setRevenueList(ArrayList revenueList)
    {
        this.revenueList = revenueList;
    }
    public ArrayList getRevenueList(){
        return revenueList;
    }

    public void setSectionList(ArrayList sectionList)
    {
        this.sectionList = sectionList;
    }
    public ArrayList getSectionList(){
        return sectionList;
    }



    public void setCaseDTO(CaseMonitoringDTO caseDTO) {
        this.caseDTO = caseDTO;
    }

    public CaseMonitoringDTO getCaseDTO() {
        return caseDTO;
    }
   

    //Added by Nihar.

    public void setRetrieveList(ArrayList retrieveList) {
        this.retrieveList = retrieveList;
    }

    public ArrayList getRetrieveList() {
        return retrieveList;
    }

    public ArrayList getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(ArrayList paymentList) {
        this.paymentList = paymentList;
    }

    /**
     * Return the value of the SCHEME_CODE collection.
     * @return SchemeItem at a particular location of the itemList
     */
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
}
