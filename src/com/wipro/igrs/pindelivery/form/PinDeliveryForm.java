package com.wipro.igrs.pindelivery.form;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.struts.action.ActionForm;
import com.wipro.igrs.pindelivery.dto.PinDeliveryDTO;
public class PinDeliveryForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String formName;
	private String actionName;
	private PinDeliveryDTO pinDTO = new PinDeliveryDTO();
	private ArrayList pinDTOList = new ArrayList();
	public ArrayList getPinDTOList() {
		return pinDTOList;
	}
	public void setPinDTOList(ArrayList pinDTOList) {
		this.pinDTOList = pinDTOList;
	}
	public PinDeliveryDTO getPinDTO() {
		return pinDTO;
	}
	public void setPinDTO(PinDeliveryDTO pinDTO) {
		this.pinDTO = pinDTO;
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
	 
	
	 
}
