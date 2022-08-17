package com.wipro.igrs.officedeptmapping.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;

public class OfficeDeptMapMasterForm extends BaseForm {


	ArrayList fullOfficeDeptDTO = null;
	String[] officeDeptIds = null;
	public OfficeDeptMapMasterForm () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        return null;
    }

	public ArrayList getFullOfficeDeptDTO() {
		return fullOfficeDeptDTO;
	}

	public void setFullOfficeDeptDTO(ArrayList fullOfficeDeptDTO) {
		this.fullOfficeDeptDTO = fullOfficeDeptDTO;
	}

	public String[] getOfficeDeptIds() {
		return officeDeptIds;
	}

	public void setOfficeDeptIds(String[] officeDeptIds) {
		this.officeDeptIds = officeDeptIds;
	}

	

}