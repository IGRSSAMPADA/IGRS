package com.wipro.igrs.pinverification.form;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.pinverification.dto.PinVerificationDTO;

public class PinVerificationForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PinVerificationDTO pinVerificationDTO = new PinVerificationDTO();
	private ArrayList listPropertyID = new ArrayList();
	private ArrayList listUser = new ArrayList();
	private HashMap mapProperty = new HashMap();
	private String hdnPinNumber;
	private String formName;
	private String actionName;

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

	public PinVerificationDTO getPinVerificationDTO() {
		return pinVerificationDTO;
	}

	public void setPinVerificationDTO(PinVerificationDTO pinVerificationDTO) {
		this.pinVerificationDTO = pinVerificationDTO;
	}

	public ArrayList getListPropertyID() {
		return listPropertyID;
	}

	public void setListPropertyID(ArrayList listPropertyID) {
		this.listPropertyID = listPropertyID;
	}

	public ArrayList getListUser() {
		return listUser;
	}

	public void setListUser(ArrayList listUser) {
		this.listUser = listUser;
	}

	/**
	 * @param index
	 * @return GuidelineDTO
	 */
	public PinVerificationDTO getPinVerification(int index) {
		int size = this.getListUser().size();
		PinVerificationDTO pinVerificationDTO = new PinVerificationDTO();
		if (index < size) {
			return (PinVerificationDTO) this.getListUser().get(index);
		}
		return pinVerificationDTO;
	}

	/**
	 * @param index
	 * @param PinVerificationDTO
	 */
	public void setPinVerification(int index,
			PinVerificationDTO pinVerificationDTO) {
		int size = this.getListUser().size();
		if (index < size) {
			this.getListUser().set(index, pinVerificationDTO);
		}
	}

	public String getHdnPinNumber() {
		return hdnPinNumber;
	}

	public void setHdnPinNumber(String hdnPinNumber) {
		this.hdnPinNumber = hdnPinNumber;
	}

	public HashMap getMapProperty() {
		return mapProperty;
	}

	public void setMapProperty(HashMap mapProperty) {
		this.mapProperty = mapProperty;
	}
}
