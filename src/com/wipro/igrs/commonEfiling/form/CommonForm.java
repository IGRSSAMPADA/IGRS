package com.wipro.igrs.commonEfiling.form;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.commonEfiling.dto.CommonDTO;

public class CommonForm extends ActionForm{

	private CommonDTO commonDTO = new CommonDTO();
	
	private ArrayList<CommonDTO> dt = new ArrayList<CommonDTO>();
	public ArrayList<CommonDTO> getDt() {
		return dt;
	}

	public void setDt(ArrayList<CommonDTO> dt) {
		this.dt = dt;
	}

	private String actionName;

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setCommonDTO(CommonDTO commonDTO) {
		this.commonDTO = commonDTO;
	}

	public CommonDTO getCommonDTO() {
		return commonDTO;
	}
	
}