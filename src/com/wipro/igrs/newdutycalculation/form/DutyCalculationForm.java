
package com.wipro.igrs.newdutycalculation.form;


import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;


/**
 * @since 14 jan 2008
 * File Name: DutyCalculationForm.java
 * @author Madan Mohan
 * @version 1.0
 * Created on 14 jan 2008
 *
 */
public class DutyCalculationForm extends ActionForm {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author Madan Mohan
	 */
	
	private  String constantLabelValue  ;
	
	private  String moduleName  ;
	
	private String hdnAmount;
	
	
	//added by Shreeraj
	private int fromAdjudication=0;
	
	
	public int getFromAdjudication() {
		return fromAdjudication;
	}

	public void setFromAdjudication(int fromAdjudication) {
		this.fromAdjudication = fromAdjudication;
	}
	
	private DutyCalculationDTO dutyCalculationDTO =
		new DutyCalculationDTO();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList instrumentDTOList = 
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList exemptionDTOList = 
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList selectedExemptionList = 
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList dutycalculationDTOList =
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private InstrumentsDTO instDTO = 
		new InstrumentsDTO();
	/**
	 * @author Madan Mohan
	 */
	private ExemptionDTO exempDTO = 
		new ExemptionDTO();
	/**
	 * @author Madan Mohan
	 */
	private String baseValue;
	public ArrayList getDeedCategoryDTOList() {
		return deedCategoryDTOList;
	}

	public void setDeedCategoryDTOList(ArrayList deedCategoryDTOList) {
		this.deedCategoryDTOList = deedCategoryDTOList;
	}

	/* Added by Vinay */
	private ArrayList deedCategoryDTOList =
		new ArrayList();
	
	/* Adiition end*/
	public String getBaseValue() {
		return baseValue;
	}
	
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setBaseValue(String baseValue) {
		this.baseValue = baseValue;
	}
	/**
	 * 
	 * @return InstrumentsDTO
	 */
	public InstrumentsDTO getInstDTO() {
		return instDTO;
	}
	/**
	 * 
	 * @param instDTO
	 */
	public void setInstDTO(InstrumentsDTO instDTO) {
		this.instDTO = instDTO;
	}
	/**
	 * 
	 * @return DutyCalculationDTO
	 */
	public DutyCalculationDTO getDutyCalculationDTO() {
		return dutyCalculationDTO;
	}
	/**
	 * 
	 * @param dutyCalculationDTO
	 */
	public void setDutyCalculationDTO(DutyCalculationDTO dutyCalculationDTO) {
		this.dutyCalculationDTO = dutyCalculationDTO;
	}
	/**
	 * 
	 * @return ArrayList
	 */
	public ArrayList getInstrumentDTOList() {
		return instrumentDTOList;
	}
	/**
	 * 
	 * @param instrumentDTOList
	 */
	public void setInstrumentDTOList(
			ArrayList instrumentDTOList) {
		this.instrumentDTOList = instrumentDTOList;
	}
	/**
	 * 
	 * @return ArrayList
	 */ 
	public ArrayList getDutycalculationDTOList() {
		return dutycalculationDTOList;
	}
	/**
	 * 
	 * @param dutycalculationDTOList
	 */
	public void setDutycalculationDTOList(
			ArrayList dutycalculationDTOList) {
		this.dutycalculationDTOList = dutycalculationDTOList;
	}
	/**
	 * 
	 * @return ExemptionDTO
	 */
	public ExemptionDTO getExempDTO() {
		return exempDTO;
	}
	/**
	 * 
	 * @param exempDTO
	 */
	public void setExempDTO(ExemptionDTO exempDTO) {
		this.exempDTO = exempDTO;
	}
	
	public String getHdnAmount() {
		return hdnAmount;
	}
	public void setHdnAmount(String hdnAmount) {
		this.hdnAmount = hdnAmount;
	}
	public String getConstantLabelValue() {
		return constantLabelValue;
	}
	public void setConstantLabelValue(String constantLabelValue) {
		this.constantLabelValue = "Y";
	}
	/**
	 * 
	 * @return ArrayList
	 */
	public ArrayList getExemptionDTOList() {
		return exemptionDTOList;
	}
	/**
	 * @param exemptionDTOList
	 */
	public void setExemptionDTOList(ArrayList exemptionDTOList) {
		this.exemptionDTOList = exemptionDTOList;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getSelectedExemptionList() {
		return selectedExemptionList;
	}
	/**
	 * @param selectedExemptionList
	 */
	public void setSelectedExemptionList(
			ArrayList selectedExemptionList) {
		this.selectedExemptionList = selectedExemptionList;
	}
	public void setMineralSelected(String mineralSelected) {
		this.mineralSelected = mineralSelected;
	}

	public String getMineralSelected() {
		return mineralSelected;
	}

	private String mineralSelected;
}
