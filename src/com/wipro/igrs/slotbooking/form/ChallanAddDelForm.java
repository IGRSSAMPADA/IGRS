package com.wipro.igrs.slotbooking.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.slotbooking.dto.ChallanAddDelDTO;

/**
 * @author venshis
 *
 */
public class ChallanAddDelForm extends ActionForm {
	
	/**
	 * 
	 */
	private ChallanAddDelDTO challandto=new ChallanAddDelDTO();

	/**
	 * @return
	 */
	public ChallanAddDelDTO getChallandto() {
		return challandto;
	}

	/**
	 * @param challandto
	 */
	public void setChallandto(ChallanAddDelDTO challandto) {
		this.challandto = challandto;
	}
	
	

}
