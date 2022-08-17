package com.wipro.igrs.deedparammapping.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class DeedParamBean extends org.apache.struts.action.ActionForm {

	
	
	
	private String[] selections;
	private String deedType;
	private String deedInstrument;
	private String deedExemption;
	private String deedParamMaster;
	private List deedTypeList;
	private List deedInstrumentList;
	private List deedExemptionList;
	private List deedParamMasterList;
   
	
	
	/**
	 * @return the deedParamMaster
	 */
	public String getDeedParamMaster() {
		return deedParamMaster;
	}

	/**
	 * @param deedParamMaster the deedParamMaster to set
	 */
	public void setDeedParamMaster(String deedParamMaster) {
		this.deedParamMaster = deedParamMaster;
	}

	
	/**
	 * @return the deedParamMasterList
	 */
	public List getDeedParamMasterList() {
		return deedParamMasterList;
	}

	/**
	 * @param deedParamMasterList the deedParamMasterList to set
	 */
	public void setDeedParamMasterList(List deedParamMasterList) {
		this.deedParamMasterList = deedParamMasterList;
	}

	/**
	 * @return the deedType
	 */
	public String getDeedType() {
		return deedType;
	}

	/**
	 * @param deedType the deedType to set
	 */
	public void setDeedType(String deedType) {
		this.deedType = deedType;
	}

	/**
	 * @return the deedInstrument
	 */
	public String getDeedInstrument() {
		return deedInstrument;
	}

	/**
	 * @param deedInstrument the deedInstrument to set
	 */
	public void setDeedInstrument(String deedInstrument) {
		this.deedInstrument = deedInstrument;
	}

	/**
	 * @return the deedExemption
	 */
	public String getDeedExemption() {
		return deedExemption;
	}

	/**
	 * @param deedExemption the deedExemption to set
	 */
	public void setDeedExemption(String deedExemption) {
		this.deedExemption = deedExemption;
	}

	/**
	 * @return the deedTypeList
	 */
	public List getDeedTypeList() {
		return deedTypeList;
	}

	/**
	 * @param deedTypeList the deedTypeList to set
	 */
	public void setDeedTypeList(List deedTypeList) {
		this.deedTypeList = deedTypeList;
	}

	/**
	 * @return the deedInstrumentList
	 */
	public List getDeedInstrumentList() {
		return deedInstrumentList;
	}

	/**
	 * @param deedInstrumentList the deedInstrumentList to set
	 */
	public void setDeedInstrumentList(List deedInstrumentList) {
		this.deedInstrumentList = deedInstrumentList;
	}

	/**
	 * @return the deedExemptionList
	 */
	public List getDeedExemptionList() {
		return deedExemptionList;
	}

	/**
	 * @param deedExemptionList the deedExemptionList to set
	 */
	public void setDeedExemptionList(List deedExemptionList) {
		this.deedExemptionList = deedExemptionList;
	}

	/**
	 * @return the selections
	 */
	public String[] getSelections() {
		return selections;
	}

	/**
	 * @param selections the selections to set
	 */
	public void setSelections(String[] selections) {
		this.selections = selections;
	}

	public DeedParamBean () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        return null;
    }


}