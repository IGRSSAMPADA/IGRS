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

import com.wipro.igrs.caseMonitoring.dto.CaseMonDTO;

public class CaseMonForm extends ActionForm{
	
	private String radioNotice;
	private String actionChk;
	private String checkbox;
	
	//added by shruti
	private String paymentAction;
    private String paymentway;
    private String partyresponse;
    private String partypaydec;
    public String lastAction;
    public String revcommdec;
    public String nxtActionId;
    public String revboarddec;
    public String nxtActionName;
    public String authDec;
    private String paidAmt;
    private String pymtresponse;
    private String errorchk;
    private String axnDescrDate;
    private String receivingDate;
    private String estampType;
    private String refundDate;
    private String nxtHearDate;
    //added
    private String fromDate;
    
    private String language;
    private String hiLastAction;
    private String hiNxtActionName;
    
    private String hdnRecAmt;
    
   
	
	public String getHdnRecAmt() {
		return hdnRecAmt;
	}
	public void setHdnRecAmt(String hdnRecAmt) {
		this.hdnRecAmt = hdnRecAmt;
	}
	public String getHiNxtActionName() {
		return hiNxtActionName;
	}
	public void setHiNxtActionName(String hiNxtActionName) {
		this.hiNxtActionName = hiNxtActionName;
	}
	public String getHiLastAction() {
		return hiLastAction;
	}
	public void setHiLastAction(String hiLastAction) {
		this.hiLastAction = hiLastAction;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate1() {
		return fromDate1;
	}
	public void setFromDate1(String fromDate1) {
		this.fromDate1 = fromDate1;
	}
	public String getToDate1() {
		return toDate1;
	}
	public void setToDate1(String toDate1) {
		this.toDate1 = toDate1;
	}
	public String getFromDate2() {
		return fromDate2;
	}
	public void setFromDate2(String fromDate2) {
		this.fromDate2 = fromDate2;
	}
	public String getToDate2() {
		return toDate2;
	}
	public void setToDate2(String toDate2) {
		this.toDate2 = toDate2;
	}


	private String toDate;
    private String fromDate1;
    private String toDate1;
    private String fromDate2;
    private String toDate2;
    
  
  

	public String getNxtHearDate() {
		return nxtHearDate;
	}
	public void setNxtHearDate(String nxtHearDate) {
		this.nxtHearDate = nxtHearDate;
	}
	public String getRefundDate() {
	return refundDate;
}
public void setRefundDate(String refundDate) {
	this.refundDate = refundDate;
}
	public String getEstampType() {
	return estampType;
}
public void setEstampType(String estampType) {
	this.estampType = estampType;
}
	public String getAxnDescrDate() {
	return axnDescrDate;
}
public void setAxnDescrDate(String axnDescrDate) {
	this.axnDescrDate = axnDescrDate;
}
public String getReceivingDate() {
	return receivingDate;
}
public void setReceivingDate(String receivingDate) {
	this.receivingDate = receivingDate;
}
	public String getErrorchk() {
		return errorchk;
	}
	public void setErrorchk(String errorchk) {
		this.errorchk = errorchk;
	}
	public String getPymtresponse() {
		return pymtresponse;
	}
	public void setPymtresponse(String pymtresponse) {
		this.pymtresponse = pymtresponse;
	}


	ArrayList pymtdtls=new ArrayList();
    
	public ArrayList getPymtdtls() {
		return pymtdtls;
	}
	public void setPymtdtls(ArrayList pymtdtls) {
		this.pymtdtls = pymtdtls;
	}
	public String getPaidAmt() {
		return paidAmt;
	}
	public void setPaidAmt(String paidAmt) {
		this.paidAmt = paidAmt;
	}
	public String getAuthDec() {
		return authDec;
	}
	public void setAuthDec(String authDec) {
		this.authDec = authDec;
	}
	public String getNxtActionName() {
		return nxtActionName;
	}
	public void setNxtActionName(String nxtActionName) {
		this.nxtActionName = nxtActionName;
	}
	public String getRevboarddec() {
		return revboarddec;
	}
	public void setRevboarddec(String revboarddec) {
		this.revboarddec = revboarddec;
	}
	public String getRevcommdec() {
		return revcommdec;
	}
	public void setRevcommdec(String revcommdec) {
		this.revcommdec = revcommdec;
	}
	public String getLastAction() {
		return lastAction;
	}
	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}
	public String getNxtActionId() {
		return nxtActionId;
	}
	public void setNxtActionId(String nxtActionId) {
		this.nxtActionId = nxtActionId;
	}
	public String getPartypaydec() {
		return partypaydec;
	}
	public void setPartypaydec(String partypaydec) {
		this.partypaydec = partypaydec;
	}
	public String getPartyresponse() {
		return partyresponse;
	}
	public void setPartyresponse(String partyresponse) {
		this.partyresponse = partyresponse;
	}
	public String getPaymentway() {
		return paymentway;
	}
	public void setPaymentway(String paymentway) {
		this.paymentway = paymentway;
	}
	public String getPaymentAction() {
		return paymentAction;
	}
	public void setPaymentAction(String paymentAction) {
		this.paymentAction = paymentAction;
	}
	
	
	private CaseMonDTO caseMonDTO = new CaseMonDTO();

	/**
	 * @return the caseMonDTO
	 */
	public CaseMonDTO getCaseMonDTO() {
		return caseMonDTO;
	}

	/**
	 * @param caseMonDTO the caseMonDTO to set
	 */
	public void setCaseMonDTO(CaseMonDTO caseMonDTO) {
		this.caseMonDTO = caseMonDTO;
	}

	/**
	 * @return the radioNotice
	 */
	public String getRadioNotice() {
		return radioNotice;
	}

	/**
	 * @param radioNotice the radioNotice to set
	 */
	public void setRadioNotice(String radioNotice) {
		this.radioNotice = radioNotice;
	}

	/**
	 * @return the actionChk
	 */
	public String getActionChk() {
		return actionChk;
	}

	/**
	 * @param actionChk the actionChk to set
	 */
	public void setActionChk(String actionChk) {
		this.actionChk = actionChk;
	}

	/**
	 * @return the checkbox
	 */
	public String getCheckbox() {
		return checkbox;
	}

	/**
	 * @param checkbox the checkbox to set
	 */
	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}



}
