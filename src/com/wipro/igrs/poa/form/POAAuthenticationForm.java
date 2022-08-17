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
package com.wipro.igrs.poa.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.poa.dto.POAAuthenticationDTO;
import com.wipro.igrs.poa.dto.POAAuthenticationFormDTO;

public class POAAuthenticationForm extends ActionForm {
	
	POAAuthenticationDTO poaDto = new  POAAuthenticationDTO();
	POAAuthenticationFormDTO poaDTO = new  POAAuthenticationFormDTO();
	
	/*modified by Shruti */
	String actionType;
	ArrayList poaTypeList=new ArrayList();
	String poaTypeId;
	String poaTypeName;
	ArrayList ecodePoaTypeList=new ArrayList();
	String ecodePoaTypeId;
	String ecodePoaTypeName;
	ArrayList awrdrcountryList=new ArrayList();
	String awrdrcountryId;
	String awrdrcountryName;
	ArrayList acptrcountryList=new ArrayList();
	String acptrcountryId;
	String acptrcountryName;
	ArrayList awrdrstateList=new ArrayList();
	String awrdrstateId;
	String awrdrstateName;
	ArrayList acptrstateList=new ArrayList();
	String acptrstateId;
	String acptrstateName;
	ArrayList awrdrdistrictList=new ArrayList();
	String awrdrdistrictId;
	String awrdrdistrictName;
	ArrayList acptrdistrictList=new ArrayList();
	String acptrdistrictId;
	String acptrdistrictName;
	String manualStampchk;
	String currentDate;
	String poaAuthNo;
	
	//end of code modified by Shruti

	//***********************ADDED BY SIMRAN************************************//
	private ArrayList poaApplicntDetlsList = new ArrayList();
	private ArrayList poaAwardeeDetlsList = new ArrayList();
	private ArrayList poaAcceptorList = new ArrayList();
	private ArrayList poaCommonDetlsList = new ArrayList();
	
	public ArrayList getPoaApplicntDetlsList() {
		return poaApplicntDetlsList;
	}

	public void setPoaApplicntDetlsList(ArrayList poaApplicntDetlsList) {
		this.poaApplicntDetlsList = poaApplicntDetlsList;
	}

	public ArrayList getPoaAwardeeDetlsList() {
		return poaAwardeeDetlsList;
	}

	public void setPoaAwardeeDetlsList(ArrayList poaAwardeeDetlsList) {
		this.poaAwardeeDetlsList = poaAwardeeDetlsList;
	}

	public ArrayList getPoaAcceptorList() {
		return poaAcceptorList;
	}

	public void setPoaAcceptorList(ArrayList poaAcceptorList) {
		this.poaAcceptorList = poaAcceptorList;
	}

	public ArrayList getPoaCommonDetlsList() {
		return poaCommonDetlsList;
	}

	public void setPoaCommonDetlsList(ArrayList poaCommonDetlsList) {
		this.poaCommonDetlsList = poaCommonDetlsList;
	}
	
	//**************************************************************************//
	
	

	public String getPoaAuthNo() {
		return poaAuthNo;
	}

	public void setPoaAuthNo(String poaAuthNo) {
		this.poaAuthNo = poaAuthNo;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getManualStampchk() {
		return manualStampchk;
	}

	public void setManualStampchk(String manualStampchk) {
		this.manualStampchk = manualStampchk;
	}

	public ArrayList getAwrdrcountryList() {
		return awrdrcountryList;
	}

	public void setAwrdrcountryList(ArrayList awrdrcountryList) {
		this.awrdrcountryList = awrdrcountryList;
	}

	public String getAwrdrcountryId() {
		return awrdrcountryId;
	}

	public void setAwrdrcountryId(String awrdrcountryId) {
		this.awrdrcountryId = awrdrcountryId;
	}

	public String getAwrdrcountryName() {
		return awrdrcountryName;
	}

	public void setAwrdrcountryName(String awrdrcountryName) {
		this.awrdrcountryName = awrdrcountryName;
	}

	public ArrayList getAcptrcountryList() {
		return acptrcountryList;
	}

	public void setAcptrcountryList(ArrayList acptrcountryList) {
		this.acptrcountryList = acptrcountryList;
	}

	public String getAcptrcountryId() {
		return acptrcountryId;
	}

	public void setAcptrcountryId(String acptrcountryId) {
		this.acptrcountryId = acptrcountryId;
	}

	public String getAcptrcountryName() {
		return acptrcountryName;
	}

	public void setAcptrcountryName(String acptrcountryName) {
		this.acptrcountryName = acptrcountryName;
	}

	public ArrayList getAwrdrstateList() {
		return awrdrstateList;
	}

	public void setAwrdrstateList(ArrayList awrdrstateList) {
		this.awrdrstateList = awrdrstateList;
	}

	public String getAwrdrstateId() {
		return awrdrstateId;
	}

	public void setAwrdrstateId(String awrdrstateId) {
		this.awrdrstateId = awrdrstateId;
	}

	public String getAwrdrstateName() {
		return awrdrstateName;
	}

	public void setAwrdrstateName(String awrdrstateName) {
		this.awrdrstateName = awrdrstateName;
	}

	public ArrayList getAcptrstateList() {
		return acptrstateList;
	}

	public void setAcptrstateList(ArrayList acptrstateList) {
		this.acptrstateList = acptrstateList;
	}

	public String getAcptrstateId() {
		return acptrstateId;
	}

	public void setAcptrstateId(String acptrstateId) {
		this.acptrstateId = acptrstateId;
	}

	public String getAcptrstateName() {
		return acptrstateName;
	}

	public void setAcptrstateName(String acptrstateName) {
		this.acptrstateName = acptrstateName;
	}

	public ArrayList getAwrdrdistrictList() {
		return awrdrdistrictList;
	}

	public void setAwrdrdistrictList(ArrayList awrdrdistrictList) {
		this.awrdrdistrictList = awrdrdistrictList;
	}

	public String getAwrdrdistrictId() {
		return awrdrdistrictId;
	}

	public void setAwrdrdistrictId(String awrdrdistrictId) {
		this.awrdrdistrictId = awrdrdistrictId;
	}

	public String getAwrdrdistrictName() {
		return awrdrdistrictName;
	}

	public void setAwrdrdistrictName(String awrdrdistrictName) {
		this.awrdrdistrictName = awrdrdistrictName;
	}

	public ArrayList getAcptrdistrictList() {
		return acptrdistrictList;
	}

	public void setAcptrdistrictList(ArrayList acptrdistrictList) {
		this.acptrdistrictList = acptrdistrictList;
	}

	public String getAcptrdistrictId() {
		return acptrdistrictId;
	}

	public void setAcptrdistrictId(String acptrdistrictId) {
		this.acptrdistrictId = acptrdistrictId;
	}

	public String getAcptrdistrictName() {
		return acptrdistrictName;
	}

	public void setAcptrdistrictName(String acptrdistrictName) {
		this.acptrdistrictName = acptrdistrictName;
	}

	public ArrayList getEcodePoaTypeList() {
		return ecodePoaTypeList;
	}

	public void setEcodePoaTypeList(ArrayList ecodePoaTypeList) {
		this.ecodePoaTypeList = ecodePoaTypeList;
	}

	public String getEcodePoaTypeId() {
		return ecodePoaTypeId;
	}

	public void setEcodePoaTypeId(String ecodePoaTypeId) {
		this.ecodePoaTypeId = ecodePoaTypeId;
	}

	public String getEcodePoaTypeName() {
		return ecodePoaTypeName;
	}

	public void setEcodePoaTypeName(String ecodePoaTypeName) {
		this.ecodePoaTypeName = ecodePoaTypeName;
	}

	public String getPoaTypeId() {
		return poaTypeId;
	}

	public void setPoaTypeId(String poaTypeId) {
		this.poaTypeId = poaTypeId;
	}

	public String getPoaTypeName() {
		return poaTypeName;
	}

	public void setPoaTypeName(String poaTypeName) {
		this.poaTypeName = poaTypeName;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public POAAuthenticationFormDTO getPoaDTO() {
		return poaDTO;
	}

	public void setPoaDTO(POAAuthenticationFormDTO poaDTO) {
		this.poaDTO = poaDTO;
	}

	public ArrayList getPoaTypeList() {
		return poaTypeList;
	}

	public void setPoaTypeList(ArrayList poaTypeList) {
		this.poaTypeList = poaTypeList;
	}

	/**
	 * @return the poaDto
	 */
	public POAAuthenticationDTO getPoaDto() {
		return poaDto;
	}

	/**
	 * @param poaDto the poaDto to set
	 */
	public void setPoaDto(POAAuthenticationDTO poaDto) {
		this.poaDto = poaDto;
	}
	

}
