package com.wipro.igrs.formmptc27.bo;

import java.util.ArrayList;

import com.wipro.igrs.formmptc27.dao.FormMPTC27DAO;
import com.wipro.igrs.formmptc27.dto.FormMPTC27DTO;

public class FormMPTC27BO {

	FormMPTC27DAO formMPTC27DAO = new FormMPTC27DAO();

	/**
	 * @return
	 * @see com.wipro.igrs.formmptc27.dao.FormMPTC27DAO#getAllIds()
	 */
	public ArrayList getAllIds() {
		return formMPTC27DAO.getAllIds();
	}

	/**
	 * @param id
	 * @return
	 * @see com.wipro.igrs.formmptc27.dao.FormMPTC27DAO#getEmpDetailsById(java.lang.String)
	 */
	public FormMPTC27DTO getEmpDetailsById(String id) {
		return formMPTC27DAO.getEmpDetailsById(id);
	}
	
	
}
