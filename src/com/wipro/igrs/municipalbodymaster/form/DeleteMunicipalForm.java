package com.wipro.igrs.municipalbodymaster.form;

import java.util.ArrayList;

import com.wipro.igrs.baseaction.form.BaseForm;

public class DeleteMunicipalForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2098762148430097536L;
	
	public DeleteMunicipalForm() {
		
	}
	
	private String[] selectedMunicipalIds;

	public String[] getSelectedMunicipalIds() {
		return selectedMunicipalIds;
	}

	public void setSelectedMunicipalIds(String[] selectedMunicipalIds) {
		this.selectedMunicipalIds = selectedMunicipalIds;
	}

	
	

}
