package com.wipro.igrs.officemaster.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.officemaster.dto.OfficeDTO;

public class OfficeMasterForm extends BaseForm {

	OfficeDTO officeDTO = null;
	ArrayList parents = null;
	ArrayList districts= null;
	ArrayList tehsils= null;
	ArrayList wards= null;
	ArrayList mohallaVillages= null;
	ArrayList officeTypes= null;
	ArrayList officeList = null;
	String [] selected = null;
    public OfficeMasterForm () {
    	//System.out.println("Ana fel constructor beta3 2l form bean");
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        //throw new UnsupportedOperationException("Method is not implemented");
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        return null;
    }

	public OfficeDTO getOfficeDTO() {
		return officeDTO;
	}

	public void setOfficeDTO(OfficeDTO officeDTO) {
		this.officeDTO = officeDTO;
	}

	public ArrayList getParents() {
		return parents;
	}

	public void setParents(ArrayList parents) {
		this.parents = parents;
	}

	public ArrayList getDistricts() {
		return districts;
	}

	public void setDistricts(ArrayList districts) {
		this.districts = districts;
	}

	public ArrayList getTehsils() {
		return tehsils;
	}

	public void setTehsils(ArrayList tehsils) {
		this.tehsils = tehsils;
	}

	public ArrayList getWards() {
		return wards;
	}

	public void setWards(ArrayList wards) {
		this.wards = wards;
	}

	public ArrayList getMohallaVillages() {
		return mohallaVillages;
	}

	public void setMohallaVillages(ArrayList mohallaVillages) {
		this.mohallaVillages = mohallaVillages;
	}

	public ArrayList getOfficeTypes() {
		return officeTypes;
	}

	public void setOfficeTypes(ArrayList officeTypes) {
		this.officeTypes = officeTypes;
	}

	public ArrayList getOfficeList() {
		return officeList;
	}

	public void setOfficeList(ArrayList officeList) {
		this.officeList = officeList;
	}

	public String[] getSelected() {
		return selected;
	}

	public void setSelected(String[] selected) {
		this.selected = selected;
	}

	public String getOfficeName()
	{
		return officeDTO.getOfficeName();
	}
	
	public void setOfficeName(String officeName)
	{
		officeDTO.setOfficeName(officeName);
	}


}