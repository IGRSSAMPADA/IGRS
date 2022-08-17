/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   WillDepositForm.java
 * Author      :   Madan Mohan Mohanty
 * Description :   Represents the DTO Class for User Registration.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra       28th Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.willdeposit.form;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.willdeposit.dto.WillDepositDTO;
import com.wipro.igrs.willdeposit.dto.ChallanDTO;
import com.wipro.igrs.willdeposit.dto.WillWithdrawDashBoard;


/**
 * @author NIHAR M.
 *
 */
public class WillDepositForm extends ActionForm {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private WillDepositDTO willDTO = new WillDepositDTO();
    private ArrayList willCountry = new ArrayList();
    private ArrayList willState = new ArrayList();
    private ArrayList willDistrict = new ArrayList();
    private ArrayList willAgent = new ArrayList();
    private ArrayList willId = new ArrayList();
    private ArrayList paymentList = new ArrayList();
    private ChallanDTO payDTO = new ChallanDTO();
    private String radioSelect;
    private ArrayList willAgentState = new ArrayList();
    private ArrayList willAgentDistrict = new ArrayList();
    private ArrayList willAgentCountry = new ArrayList();
    private ArrayList retrieveList;
    private ArrayList willViewsList;
    private ArrayList withdrawalViewList;
    private ArrayList willRemarksList;
    
    private FormFile filePhoto = null;
    private FormFile fileThumb = null;
    private FormFile fileSignature = null;
    private FormFile fileCertificate = null;
	private WillWithdrawDashBoard objDashboard= new WillWithdrawDashBoard();

    public WillWithdrawDashBoard getObjDashboard() {
		return objDashboard;
	}
	public void setObjDashboard(WillWithdrawDashBoard objDashboard) {
		this.objDashboard = objDashboard;
	}
	/**
     * @param willDTO
     */
    public void setWillDTO(WillDepositDTO willDTO) {
        this.willDTO = willDTO;
    }
    /**
     * @return WillDepositDTO
     */
    public WillDepositDTO getWillDTO() {
        return willDTO;
    }
    /**
     * @param willCountry
     */
    public void setWillCountry(ArrayList willCountry) {
        this.willCountry = willCountry;
    }
    /**
     * @return ArrayList
     */
    public ArrayList getWillCountry() {
        return willCountry;
    }
    /**
     * @param willState
     */
    public void setWillState(ArrayList willState) {
        this.willState = willState;
    }
    /**
     * @return ArrayList
     */
    public ArrayList getWillState() {
        return willState;
    }
    /**
     * @param willAgent
     */
    public void setWillAgent(ArrayList willAgent) {
        this.willAgent = willAgent;
    }
    /**
     * @return ArrayList
     */
    public ArrayList getWillAgent() {
        return willAgent;
    }
    /**
     * @param willId
     */
    public void setWillId(ArrayList willId) {
        this.willId = willId;
    }
    /**
     * @return ArrayList
     */
    public ArrayList getWillId() {
        return willId;
    }
    /**
     * @param retrieveList
     */
    public void setRetrieveList(ArrayList retrieveList) {
        this.retrieveList = retrieveList;
    }
    /**
     * @return ArrayList
     */
    public ArrayList getRetrieveList() {
        return retrieveList;
    }
	/**
	 * @return ArrayList
	 */
	public ArrayList getPaymentList() {
		return paymentList;
	}
	/**
	 * @param paymentList
	 */
	public void setPaymentList(ArrayList paymentList) {
		this.paymentList = paymentList;
	}
	/**
	 * @param index
	 * @param vo
	 */
	public void setPaymentItems(int index, ChallanDTO vo){
		for (; index >= paymentList.size(); paymentList.add(new ChallanDTO()));
		System.out.println("in setter -->"+vo.getChallanDate());
		System.out.println(vo.getAmount());
		System.out.println(vo.getChallanNo());
		paymentList.set(index, vo);
	}

	/**
	 * @param index 
	 * @return PaymentDTO
	 */

	public ChallanDTO getPaymentItems(int index){
		for(; index >= paymentList.size(); paymentList.add(new ChallanDTO()));
		return (ChallanDTO) paymentList.get(index);
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getWillViewsList() {
		return willViewsList;
	}
	/**
	 * @param willViewsList
	 */
	public void setWillViewsList(ArrayList willViewsList) {
		this.willViewsList = willViewsList;
	}
	/**
	 * @return willDistrict
	 */
	public ArrayList getWillDistrict() {
		return willDistrict;
	}
	/**
	 * @param willDistrict
	 */
	public void setWillDistrict(ArrayList willDistrict) {
		this.willDistrict = willDistrict;
	}
	
	/**
	 * @return ChallanDTO
	 */
	public ChallanDTO getPayDTO() {
		return payDTO;
	}
	
	/**
	 * @param payDTO
	 */
	public void setPayDTO(ChallanDTO payDTO) {
		this.payDTO = payDTO;
	}
	public String getRadioSelect() {
		return radioSelect;
	}
	public void setRadioSelect(String radioSelect) {
		this.radioSelect = radioSelect;
	}
	public ArrayList getWithdrawalViewList() {
		return withdrawalViewList;
	}
	public void setWithdrawalViewList(ArrayList withdrawalViewList) {
		this.withdrawalViewList = withdrawalViewList;
	}
	public ArrayList getWillAgentState() {
		return willAgentState;
	}
	public void setWillAgentState(ArrayList willAgentState) {
		this.willAgentState = willAgentState;
	}
	public ArrayList getWillAgentDistrict() {
		return willAgentDistrict;
	}
	public void setWillAgentDistrict(ArrayList willAgentDistrict) {
		this.willAgentDistrict = willAgentDistrict;
	}
	public ArrayList getWillRemarksList() {
		return willRemarksList;
	}
	public void setWillRemarksList(ArrayList willRemarksList) {
		this.willRemarksList = willRemarksList;
	}
	public FormFile getFilePhoto() {
		return filePhoto;
	}
	public void setFilePhoto(FormFile filePhoto) {
		this.filePhoto = filePhoto;
	}
	public FormFile getFileThumb() {
		return fileThumb;
	}
	public void setFileThumb(FormFile fileThumb) {
		this.fileThumb = fileThumb;
	}
	public FormFile getFileSignature() {
		return fileSignature;
	}
	public void setFileSignature(FormFile fileSignature) {
		this.fileSignature = fileSignature;
	}
	public FormFile getFileCertificate() {
		return fileCertificate;
	}
	public void setFileCertificate(FormFile fileCertificate) {
		this.fileCertificate = fileCertificate;
	}
	public ArrayList getWillAgentCountry() {
		return willAgentCountry;
	}
	public void setWillAgentCountry(ArrayList willAgentCountry) {
		this.willAgentCountry = willAgentCountry;
	}
}
