package com.wipro.igrs.officedeptmapping.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.officedeptmapping.dto.OfficeDeptDTO;

public class OfficeDeptMapForm extends BaseForm {

	
	ArrayList offices;
	ArrayList departments;
	OfficeDeptDTO officeDeptDTO;

    public OfficeDeptMapForm () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
       
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        return null;
    }

	public ArrayList getOffices() {
		return offices;
	}

	public void setOffices(ArrayList offices) {
		this.offices = offices;
	}

	public ArrayList getDepartments() {
		return departments;
	}

	public void setDepartments(ArrayList departments) {
		this.departments = departments;
	}

	public OfficeDeptDTO getOfficeDeptDTO() {
		return officeDeptDTO;
	}

	public void setOfficeDeptDTO(OfficeDeptDTO officeDeptDTO) {
		this.officeDeptDTO = officeDeptDTO;
	}


}