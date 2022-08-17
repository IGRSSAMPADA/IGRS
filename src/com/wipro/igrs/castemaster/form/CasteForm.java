package com.wipro.igrs.castemaster.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class CasteForm extends org.apache.struts.action.ActionForm {

	private String casteId;
	private String casteName;
	private String status;
	private String categoryId;

    public CasteForm () {
    }
    
    

    /**
	 * @return the casteName
	 */
	public String getCasteName() {
		return casteName;
	}



	/**
	 * @param casteName the casteName to set
	 */
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}



	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}



	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	


	/**
	 * @return the casteId
	 */
	public String getCasteId() {
		return casteId;
	}



	/**
	 * @param casteId the casteId to set
	 */
	public void setCasteId(String casteId) {
		this.casteId = casteId;
	}



	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        return null;
    }


}