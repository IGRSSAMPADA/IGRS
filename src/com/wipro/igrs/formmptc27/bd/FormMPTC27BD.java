package com.wipro.igrs.formmptc27.bd;

import java.util.ArrayList;

import com.wipro.igrs.formmptc27.bo.FormMPTC27BO;
import com.wipro.igrs.formmptc27.dto.FormMPTC27DTO;

public class FormMPTC27BD {
	
	FormMPTC27BO formMPTC27BO = new FormMPTC27BO();

	/**
	 * @return
	 * @see com.wipro.igrs.formmptc27.bo.FormMPTC27BO#getAllIds()
	 */
	public ArrayList getAllIds() {
		return formMPTC27BO.getAllIds();
	}

	/**
	 * @param id
	 * @return
	 * @see com.wipro.igrs.formmptc27.bo.FormMPTC27BO#getEmpDetailsById(java.lang.String)
	 */
	public FormMPTC27DTO getEmpDetailsById(String id) {
		return formMPTC27BO.getEmpDetailsById(id);
	}
	

}
