package com.wipro.igrs.newPropvaluationefiling.form;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.newPropvaluationefiling.dto.KhasraClrDTO;
import com.wipro.igrs.newPropvaluationefiling.dto.PropertyValuationDTO;
import com.wipro.igrs.newPropvaluationefiling.dto.SampadaKhasraClrDTO;
import com.wipro.igrs.newPropvaluationefiling.dto.TreeDTO;
import com.wipro.igrs.newreginitefiling.form.RegCommonForm;

public class PropertyValuationForm extends ActionForm {

	public PropertyValuationDTO propDTO = new PropertyValuationDTO();
	private KhasraClrDTO clrDto = new KhasraClrDTO();
	private ArrayList<KhasraClrDTO> clrKhasraList = new ArrayList<KhasraClrDTO>();
	private SampadaKhasraClrDTO smapadaClrDTO = new SampadaKhasraClrDTO();
	private ArrayList<SampadaKhasraClrDTO> sampadaClrKhasraList = new ArrayList<SampadaKhasraClrDTO>();
	public ArrayList<KhasraClrDTO> getClrKhasraList() {
		return clrKhasraList;
	}

	public void setClrKhasraList(ArrayList<KhasraClrDTO> clrKhasraList) {
		this.clrKhasraList = clrKhasraList;
	}

	private String actionName;
	private String plotEduTcp;
	private String plotHealthTcp;

	private RegCommonForm regInitForm;
	private String regMultiProp;

	public String getRegMultiProp() {
		return regMultiProp;
	}

	public void setRegMultiProp(String regMultiProp) {
		this.regMultiProp = regMultiProp;
	}

	public RegCommonForm getRegInitForm() {
		return regInitForm;
	}

	public void setRegInitForm(RegCommonForm regInitForm) {
		this.regInitForm = regInitForm;
	}

	public String getPlotEduTcp() {
		return plotEduTcp;
	}

	public void setPlotEduTcp(String plotEduTcp) {
		this.plotEduTcp = plotEduTcp;
	}

	public String getPlotHealthTcp() {
		return plotHealthTcp;
	}

	public void setPlotHealthTcp(String plotHealthTcp) {
		this.plotHealthTcp = plotHealthTcp;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public PropertyValuationDTO getPropDTO() {
		return propDTO;
	}

	public void setPropDTO(PropertyValuationDTO propDTO) {
		this.propDTO = propDTO;
	}

	public KhasraClrDTO getClrDto() {
		return clrDto;
	}

	public void setClrDto(KhasraClrDTO clrDto) {
		this.clrDto = clrDto;
	}

	private ArrayList<PropertyValuationDTO> agriAddBuyerList = new ArrayList<PropertyValuationDTO>();
	private ArrayList<TreeDTO> agriTreeList = new ArrayList<TreeDTO>();
	private HashMap<ArrayList, ArrayList> agriPropertyDiffPustikaList = new HashMap<ArrayList, ArrayList>();

	public ArrayList<PropertyValuationDTO> getAgriAddBuyerList() {
		return agriAddBuyerList;
	}

	public void setAgriAddBuyerList(ArrayList<PropertyValuationDTO> agriAddBuyerList) {
		this.agriAddBuyerList = agriAddBuyerList;
	}

	public ArrayList<TreeDTO> getAgriTreeList() {
		return agriTreeList;
	}

	public void setAgriTreeList(ArrayList<TreeDTO> agriTreeList) {
		this.agriTreeList = agriTreeList;
	}

	public HashMap<ArrayList, ArrayList> getAgriPropertyDiffPustikaList() {
		return agriPropertyDiffPustikaList;
	}

	public void setAgriPropertyDiffPustikaList(HashMap<ArrayList, ArrayList> agriPropertyDiffPustikaList) {
		this.agriPropertyDiffPustikaList = agriPropertyDiffPustikaList;
	}

	// CLR work

	private String clrData;
	private String actionNameSetValue;

	private String searchDisplay;
	public void setClrData(String clrData) {
		this.clrData = clrData;
	}

	public String getClrData() {
		return clrData;
	}

	public void setSearchDisplay(String searchDisplay) {
		this.searchDisplay = searchDisplay;
	}

	public String getSearchDisplay() {
		return searchDisplay;
	}
	public String sampadaArea;

	public String getSampadaArea() {
		return sampadaArea;
	}
	public void setSampadaArea(String sampadaArea) {
		this.sampadaArea = sampadaArea;
	}

	public void setSampadaClrKhasraList(ArrayList<SampadaKhasraClrDTO> sampadaClrKhasraList) {
		this.sampadaClrKhasraList = sampadaClrKhasraList;
	}

	public ArrayList<SampadaKhasraClrDTO> getSampadaClrKhasraList() {
		return sampadaClrKhasraList;
	}

	public void setSmapadaClrDTO(SampadaKhasraClrDTO smapadaClrDTO) {
		this.smapadaClrDTO = smapadaClrDTO;
	}

	public SampadaKhasraClrDTO getSmapadaClrDTO() {
		return smapadaClrDTO;
	}

	public void setActionNameSetValue(String actionNameSetValue) {
		this.actionNameSetValue = actionNameSetValue;
	}

	public String getActionNameSetValue() {
		return actionNameSetValue;
	}

	public void setUniKhasraArray(ArrayList<String> uniKhasraArray) {
		this.uniKhasraArray = uniKhasraArray;
	}

	public ArrayList<String> getUniKhasraArray() {
		return uniKhasraArray;
	}

	public void setUniRinArrayClr(ArrayList<String> uniRinArrayClr) {
		this.uniRinArrayClr = uniRinArrayClr;
	}

	public ArrayList<String> getUniRinArrayClr() {
		return uniRinArrayClr;
	}

	private ArrayList<String> uniKhasraArray = new ArrayList<String>();

	private ArrayList<String> uniRinArrayClr = new ArrayList<String>();

	/*
	 * //development agreement private InstrumentsDTO instDTO; public
	 * InstrumentsDTO getInstDTO() { return instDTO; }
	 * 
	 * public void setInstDTO(InstrumentsDTO instDTO) { this.instDTO = instDTO;
	 * }
	 */

}
