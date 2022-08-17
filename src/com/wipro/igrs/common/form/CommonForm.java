package com.wipro.igrs.common.form;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.common.dto.CommonDTO;
import com.wipro.igrs.newPropvaluation.dto.KhasraClrDTO;
import com.wipro.igrs.newreginit.dto.RegCompletDTO;

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
	
	//Added by rakesh to view PropVal
	private KhasraClrDTO clrDto=new KhasraClrDTO();
	private String clrFlag;
	private String rinPustikaCLr;
	//private String PropertyId;
	
	public KhasraClrDTO getClrDto() {
		return clrDto;
	}

	public void setClrDto(KhasraClrDTO clrDto) {
		this.clrDto = clrDto;
	}

	public String getClrFlag() {
		return clrFlag;
	}

	public void setClrFlag(String clrFlag) {
		this.clrFlag = clrFlag;
	}
	public void setClrKhasraDetails(ArrayList<CommonDTO> clrKhasraDetails) {
		this.clrKhasraDetails = clrKhasraDetails;
	}

	public ArrayList<CommonDTO> getClrKhasraDetails() {
		return clrKhasraDetails;
	}

	public void setRinPustikaCLr(String rinPustikaCLr) {
		this.rinPustikaCLr = rinPustikaCLr;
	}

	public String getRinPustikaCLr() {
		return rinPustikaCLr;
	}

	private ArrayList<CommonDTO> clrKhasraDetails=new ArrayList<CommonDTO>();

	
	
	
	
}