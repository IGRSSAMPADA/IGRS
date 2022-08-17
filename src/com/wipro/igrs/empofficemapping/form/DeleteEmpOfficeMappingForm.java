package com.wipro.igrs.empofficemapping.form;

import com.wipro.igrs.baseaction.form.BaseForm;

public class DeleteEmpOfficeMappingForm extends BaseForm{
	
	private String[] selectedMappingsIds;

	public String[] getSelectedMappingsIds() {
		return selectedMappingsIds;
	}

	public void setSelectedMappingsIds(String[] selectedMappingsIds) {
		this.selectedMappingsIds = selectedMappingsIds;
	}

}
